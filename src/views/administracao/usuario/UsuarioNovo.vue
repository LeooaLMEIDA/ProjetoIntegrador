<template>
  <div class="m-3">
    <s-title :title="title" :breadcrumb="true" />
    <div class="card card-body mx-2">
      <form ref="form" @submit.prevent="submitForm">
        <div class="row">
          <s-input-text v-model="object.nome" ref="nome" divClass="col-12 col-md-4 col-xxl-4" label="Nome"
            placeholder="Nome Completo" required />
          <s-input-email v-model="object.email" ref="email" divClass="col-12 col-md-4 col-xxl-4" label="E-mail"
            placeholder="email@email.com" required />
          <s-input-password v-model="object.senha" ref="password" divClass="col-12 col-md-2" label="Senha"
            placeholder="••••••••" :required="!object.id" />
          <s-select v-model="object.sexo" divClass="col-md-2" label="Sexo" :items="sexos" :clearable="false" required />
          <s-input-date v-model="object.dtNascimento" divClass="col-md-2" label="Nascimento" required />
          <s-input-text v-model="object.celular" ref="celular" divClass="col-12 col-md-2" label="Celular"
            placeholder="Celular" v-mask="'(##) #####-####'" required />
          <s-select v-model="object.tpUsuario" divClass="col-md-2" label="Tipo" :items="tipo" :clearable="false"
            required />
          <s-select v-model="object.status" divClass="col-md-2" label="Status" :items="status" :clearable="false" />
          <s-input-file :selectedFile="object.file" @fileSelected="handleSelectedFile" ref="image" divClass="col-md-12"
            label="Imagem" :acceptedTypes="['.png']" :required="!object.id" />
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
  name: 'UsuarioNew',

  data: () => ({
    object: { urlAvatar: null },
    valid: false,
    Modal: null,
    modalError: null,
    modalNotLogged: null,
    modalBody: null,
    title: null,
    route: 'usuario',

    headersGroup: ['Grupo', 'Ações'],
    sexos: [
      { label: "Masculino", value: 'MASCULINO' },
      { label: "Feminino", value: 'FEMININO' }
    ],
    tipo: [
      { label: "Admin", value: 'ADMIN' },
      { label: "Aluno", value: 'ALUNO' }
    ],
    status: [
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
            console.error(err)
            this.$router.push({ name: 'usuario' })
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
          this.object.status ? this.object.status = true : this.object.status = false

          const newObj = { ...this.object }
          delete newObj.file

          newObj.status ? newObj.status = true : newObj.status = false

          const result = await insert(this.route, newObj)

          if (result.status) {
            if (result.status == 204 && result.status == 200) {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Usuário alterado com sucesso !')
              this.$router.back()
            }
            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Usuário criado com sucesso !')
              this.object = {}
              newObj = {}
            }
          }

          else {
            this.modalBody = result.response.data.errors
            this.modalError.show()
          }
        }
      }

      else { this.modalNotLogged.show() }
    },

    async save() {
      if (await this.$checkSession()) {
        if (this.object.id) {
          const newObj = { ...this.object }
          delete newObj.file

          newObj.status ? newObj.status = true : newObj.status = false

          const result = await update(this.route, newObj)

          if (result.status && (result.status == 204 || result.status == 200)) {
            this.$store.dispatch('setShowToast', true)
            this.$store.dispatch('setToastMessage', 'Usuário alterado com sucesso !')
            this.$router.back()
          }
          else {
            this.modalBody = result.response.data.errors[0]
            this.modalError.show()
          }
        }

        else {
          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.data.errors[0]
              this.modalError.show()
            }

            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Usuário criado com sucesso !')
              this.object = {}
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

    logout() { logout(this) },

    handleSelectedFile(file) {
      this.object.file = file;
      const reader = new FileReader();

      if (file) {
        reader.onload = (event) => {
          const base64String = event.target.result.split(',')[1]
          this.object.urlAvatar = base64String;
        }
      }

      reader.readAsDataURL(file);
    }
  },

  mounted() {
    this.$route.name == 'usuarioUpdate' ? this.title = 'Edição de Usuário' : this.title = 'Cadastro de Usuário'
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