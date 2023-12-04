<template>
  <div class="m-3">
    <s-title :title="title" :breadcrumb="true" />
    <div class="card card-body mx-2">
      <form ref="form" @submit.prevent="submitForm">
        <div class="row">
          <s-input-text v-model="object.descricao" v-mask="'##/##/####'" ref="descricao" maxlength="10"
            divClass="col-md-4" label="Descrição" required />
          <s-input-zoom v-model="idAluno" @blur="loadDescription" ref="idAluno" divClass="col-12 col-md-2" label="Aluno"
            required>
            <template #default>
              <Usuario :zoom="true" @selectedItem="handleSelectedAluno" />
            </template>
          </s-input-zoom>
          <s-input-text v-model="nomeAluno" ref="nomeAluno" maxlength="40" divClass="col-md-6" isDisabled
            label="Nome Aluno" placeholder="" />
          <s-input-textarea v-model="object.observacao" ref="descricao" divClass="col-md-12" label="Observação"
            placeholder="" />
          <s-input-file :selectedFile="object.file" @fileSelected="handleSelectedFile" ref="image" divClass="col-md-12"
            label="Imagem" :acceptedTypes="['.pdf']" :required="!object.id" />
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

import Usuario from '@/views/administracao/usuario/Usuario.vue'
import { validateForm } from '@/rule/functions'
import { insert, getById, update } from '@/crud'

export default {
  name: 'avaliacoesNew',

  components: {
    Usuario
  },

  data: () => ({
    object: {
      arqAvaliacao: null
    },
    valid: false,
    Modal: null,
    modalError: null,
    modalNotLogged: null,
    modalBody: null,
    title: null,
    route: 'avaliacao',
    idAluno: null,
    nomeAluno: "",

  }),

  methods: {
    async loadItem(id) {
      if (await this.$checkSession()) {
        await getById(this.route, id)
          .then((res) => {
            this.object = res
            this.idAluno = res.usuario.id
            this.nomeAluno = res.usuario.nome
          })
          .catch((err) => {
            this.$router.push({ name: 'avaliacao' })
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

          this.object.idUsuario = this.idAluno

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.errors[0]
              this.modalError.show()
            }
            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Avaliação criada com sucesso !')
              this.object = {}
              this.idAluno = null
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
          this.object.usuario.id = this.idAluno
          this.object.usuario.nome = this.nomeAluno
          this.object.idUsuario = this.idAluno

          const newObj = { ...this.object }
          delete newObj.file

          const result = await update(this.route, newObj)

          if (result.status && (result.status == 204 || result.status == 200)) {
            this.$store.dispatch('setShowToast', true)
            this.$store.dispatch('setToastMessage', 'Aparelho alterado com sucesso !')
            this.$router.back()
          }
          else {
            this.modalBody = result.response.data.errors[0]
            this.modalError.show()
          }
        }

        else {
          this.object.idUsuario = this.idAluno

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.errors[0]
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

    logout() { logout(this) },

    async loadDescription() {
      if (this.idAluno) {
        await getById("usuario", this.idAluno)
          .then((res) => {
            this.nomeAluno = res.nome
          })
          .catch((err) => {
            console.log(err.erros)
            this.modalBody = `Usuário ${this.idAluno} não foi encontrado`
            this.modalError.show()
          })
        this.idAluno
      }
    },

    handleSelectedFile(file) {
      this.object.file = file;
      const reader = new FileReader();

      if (file) {
        reader.onload = (event) => {
          const base64String = event.target.result.split(',')[1]
          this.object.arqAvaliacao = base64String;
        }
      }

      reader.readAsDataURL(file);
    },

    async handleSelectedAluno(item) {
      this.$refs.idAluno.modalZoom.hide()
      this.idAluno = item.id.toString()
      if (this.idAluno) {
        await getById("usuario", this.idAluno)
          .then((res) => {
            this.nomeAluno = res.nome
          })
          .catch((err) => {
            console.log(err.erros)
            this.modalBody = `Usuário ${this.idAluno} não foi encontrado`
            this.modalError.show()
          })
        this.idAluno
      }
    },
  },

  mounted() {
    this.$route.name == 'avaliacaoUpdate' ? this.title = 'Edição de Avaliação' : this.title = 'Cadastro de Avaliação'
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