<template>
  <div class="m-3">
    <s-title :title="title" :breadcrumb="true" />
    <div class="card card-body mx-2">
      <form ref="form" @submit.prevent="submitForm">
        <div class="row">
          <s-input-text v-model="object.descricao" ref="descricao" maxlength="40" divClass="col-md-5" label="Descrição"
            placeholder="" required />
          <s-select v-model="object.status" divClass="col-md-2" label="Status" :items="status" :clearable="false" />
          <s-select v-model="object.grpMusculos" divClass="col-md-5" label="Grupo Muscular" :items="grupoMusculares"
            :clearable="false" required />
          <s-input-zoom v-model="idAparelho" @blur="loadDescription" ref="idAparelho" divClass="col-12 col-md-2"
            label="Aparelho" required>
            <template #default>
              <Aparelho :zoom="true" @selectedItem="handleSelectedAparelho" />
            </template>
          </s-input-zoom>
          <s-input-text v-model="descricaoAparelho" ref="descricaoAparelho" maxlength="40" divClass="col-md-10" isDisabled
            label="Descrição Aparelho" placeholder="" />
          <s-input-file :selectedFile="object.file" @fileSelected="handleSelectedFile" ref="image" divClass="col-md-12"
            label="Imagem" :acceptedTypes="['.gif']" :required="!object.id" />
          <s-input-textarea v-model="object.orientacao" ref="orientacao" divClass="col-12 col-md-12 col-xxl-12"
            label="Orientação" maxlength="140" />
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
import Aparelho from '@/views/cadastros/aparelho/Aparelho.vue'
import { validateForm } from '@/rule/functions'
import { insert, getById, update } from '@/crud'

export default {
  name: 'exercicioNew',

  components: {
    Aparelho
  },

  data: () => ({
    object: {
      imgIlustracao: null
    },
    valid: false,
    Modal: null,
    modalError: null,
    modalNotLogged: null,
    modalBody: null,
    title: null,
    route: 'exercicio',
    idAparelho: null,
    descricaoAparelho: null,

    status: [
      { label: "ATIVO", value: 1 },
      { label: "INATIVO", value: 0 }
    ],

    grupoMusculares: [
      { label: "BICEPS", value: 'BICEPS' },
      { label: "DELTOIDE", value: 'DELTOIDE' },
      { label: "DORSAL", value: 'DORSAL' },
      { label: "GEMEOS", value: 'GEMEOS' },
      { label: "GLUTEO", value: 'GLUTEO' },
      { label: "ISQUOTIBIAL", value: 'ISQUOTIBIAL' },
      { label: "OBLIQUOS", value: 'OBLIQUOS' },
      { label: "PEITORAL", value: 'PEITORAL' },
      { label: "QUADRICEPS", value: 'QUADRICEPS' },
      { label: "RETO ABDOMINAL", value: 'RETO_ABDOMINAL' },
      { label: "TRAPEZIO", value: 'TRAPEZIO' },
      { label: "TRICEPS", value: 'TRICEPS' }
    ],
  }),

  methods: {
    async loadItem(id) {
      if (await this.$checkSession()) {
        await getById(this.route, id)
          .then((res) => {
            this.object = res
            this.object.status ? this.object.status = 1 : this.object.status = 0
            this.idAparelho = res.aparelho.id
            this.descricaoAparelho = res.aparelho.descricao
          })
          .catch((err) => {
            this.$router.push({ name: 'exercicio' })
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
          this.object.idAparelho = this.idAparelho

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.data[0]
              this.modalError.show()
            }
            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Avaliação criada com sucesso !')
              this.object = {}
              this.object.file = null
              this.idAparelho = null
              this.descricaoAparelho = null
              this.object.imgIlustracao = null
            }
          }

          else {
            this.modalBody = result.response.data.errors[0]
            this.modalError.show()
          }
        }
      }
      else { this.modalNotLogged.show() }
    },

    async save() {
      if (await this.$checkSession()) {
        if (this.object.id) {
          this.object.aparelho.id = this.idAparelho
          this.object.aparelho.descricao = this.descricaoAparelho
          this.object.idAparelho = this.idAparelho

          const newObj = { ...this.object }
          delete newObj.file

          newObj.status ? newObj.status = true : newObj.status = false

          const result = await update(this.route, newObj)

          if (result.status && (result.status == 204 || result.status == 200)) {
            this.$store.dispatch('setShowToast', true)
            this.$store.dispatch('setToastMessage', 'Exercício alterado com sucesso !')
            this.$router.back()
          }
          else {
            this.modalBody = result.response.data
            this.modalError.show()
          }
        }
        else {
          this.object.idAparelho = this.idAparelho

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.data[0]
              this.modalError.show()
            }

            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Avaliação criada com sucesso !')
              this.$router.back()
            }
          }

          else {
            this.modalBody = result.response.data.errors[0]
            this.modalError.show()
          }
        }
      }
      else { this.modalNotLogged.show() }
    },

    async loadDescription() {
      if (this.idAparelho) {
        await getById("aparelho", this.idAparelho)
          .then((res) => {
            this.descricaoAparelho = res.descricao
          })
          .catch((err) => {

            console.log(err.erros)
            this.modalBody = `Aparelho ${this.idAparelho} não foi encontrado`
            this.modalError.show()
          })
        this.idAparelho
      }
    },

    async handleSelectedAparelho(item) {
      this.$refs.idAparelho.modalZoom.hide()
      this.idAparelho = item.id.toString()
      if (this.idAparelho) {
        await getById("aparelho", this.idAparelho)
          .then((res) => {
            this.descricaoAparelho = res.descricao
          })
          .catch((err) => {

            console.log(err.erros)
            this.modalBody = `Aparelho ${this.idAparelho} não foi encontrado`
            this.modalError.show()
          })
        this.idAparelho
      }
    },

    logout() { logout(this) },

    handleSelectedFile(file) {
      this.object.file = file;
      const reader = new FileReader();

      if (file) {
        reader.onload = (event) => {
          const base64String = event.target.result.split(',')[1]
          this.object.imgIlustracao = base64String;
        }
        reader.readAsDataURL(file);
      }
    }
  },
  mounted() {
    this.$route.name == 'exercicioUpdate' ? this.title = 'Edição de Exercício' : this.title = 'Cadastro de Exercício'
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