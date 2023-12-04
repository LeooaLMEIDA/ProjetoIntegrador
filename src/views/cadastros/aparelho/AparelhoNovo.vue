<template>
  <div class="m-3">
    <s-title :title="title" :breadcrumb="true" />
    <div class="card card-body mx-2">
      <form ref="form" @submit.prevent="submitForm">
        <div class="row">
          <s-input-text v-model="object.descricao" ref="descricao" maxlength="40" divClass="col-md-6" label="Descrição"
            placeholder="" required />
          <s-select v-model="object.status" divClass="col-md-3" label="Status" :items="statusData" :clearable="false" />
        </div>
        <div class="row">
          <s-label-required />
        </div>
        <hr />
        <div class="row">
          <div class="col-12 d-flex justify-content-between">
            <div>
              <s-button type="submit" label="Salvar" color="primary" icon="check2" />
              <s-button type="button" label="Salvar e Continuar" color="secondary" icon="check2" v-if="!object.id"
                @click="saveAndKeep" />
            </div>
            <div>
              <s-button type="button" label="Cancelar" color="outline-danger" icon="x-lg" @click="$router.back" />
            </div>
          </div>
        </div>
      </form>
    </div>
    <s-modal-error ref="modalError" modalTitle="Falha ao adicionar o registro !" :modalBody="modalBody" />
    <s-modal-notlogged ref="modalNotLogged" @confirm="logout" />
  </div>
</template>
  
<script>
import { validateForm } from '@/rule/functions'
import { insert, getById, update } from '@/crud'

export default {
  name: 'aparelhoNew',

  data: () => ({
    object: {},
    valid: false,
    Modal: null,
    modalError: null,
    modalNotLogged: null,
    modalBody: null,
    title: null,
    route: 'aparelho',

    statusData: [
      { label: "Ativo", value: 1 },
      { label: "Inativo", value: 0 },
    ],

  }),

  methods: {
    async loadItem(id) {
      if (await this.$checkSession()) {
        await getById(this.route, id)
          .then((res) => {
            this.object = res
            this.object.status ? this.object.status = 1 : this.object.status = 0
          })
          .catch((err) => {
            this.$router.push({ name: 'aparelho' })
          })
      }

      else { this.modalMessage.show() }
    },

    async submitForm() {
      if (await validateForm(this.$refs.form)) { this.save() }
    },

    async saveAndKeep() {
      if (await this.$checkSession()) {
        if (await validateForm(this.$refs.form)) {

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.data
              this.modalError.show()
            }
            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Aparelho criado com sucesso !')
              this.object = {}
            }
          }

          else {
            this.modalBody = result.response.data
            this.modalError.show()
          }
        }
      }
      else { this.modalNotLogged.show() }
    },

    async save() {
      if (await this.$checkSession()) {
        if (this.object.id) {
          const result = await update(this.route, this.object)

          if (result.status && (result.status == 204 || result.status == 200)) {
            this.$store.dispatch('setShowToast', true)
            this.$store.dispatch('setToastMessage', 'Aparelho alterado com sucesso !')
            this.$router.back()
          }
          else {
            this.modalBody = result.response.data
            this.modalError.show()
          }
        }
        else {
          const result = await insert(this.route, this.object)

          if (result.status && (result.status == 204 || result.status == 200)) {
            this.$store.dispatch('setShowToast', true)
            this.$store.dispatch('setToastMessage', 'Aparelho criado com sucesso !')
            this.$router.back()
          }
          else {
            this.modalBody = result.response.data
            this.modalError.show()
          }
        }
      }

      else { this.modalNotLogged.show() }
    },

    logout() { logout(this) }
  },

  mounted() {
    this.$route.name == 'aparelhoUpdate' ? this.title = 'Edição de Aparelho' : this.title = 'Cadastro de Aparelho'
    this.modalNotLogged = new this.$Modal(this.$refs.modalNotLogged.$refs.modalPattern)
    this.modalError = new this.$Modal(this.$refs.modalError.$refs.modalPattern)
  },

  async created() {
    const id = this.$route.params.id

    if (id) { await this.loadItem(id) }
  },
}
</script>
  
<style></style>