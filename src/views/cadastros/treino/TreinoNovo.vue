<template>
  <div class="m-3">
    <s-title :title="title" :breadcrumb="true" />
    <div class="card card-body mx-2">
      <form ref="form" @submit.prevent="submitForm">
        <div class="row">
          <s-input-zoom v-model="idExercicio" @blur="loadDescriptionExercicio" ref="idExercicio"
            divClass="col-12 col-md-2" label="Exercício" required>
            <template #default>
              <Exercicio :zoom="true" @selectedItem="handleSelectedExercicio" />
            </template>
          </s-input-zoom>
          <s-input-text v-model="descricaoExercicio" ref="descricaoExercicio" maxlength="40" divClass="col-md-4"
            isDisabled label="Descrição Exercício" placeholder="" />
          <s-input-zoom v-model="idAluno" @blur="loadDescriptionAluno" ref="idAluno" divClass="col-12 col-md-2"
            label="Aluno" required>
            <template #default>
              <Usuario :zoom="true" @selectedItem="handleSelectedAluno" />
            </template>
          </s-input-zoom>
          <s-input-text v-model="nomeAluno" ref="nomeAluno" maxlength="40" divClass="col-md-4" isDisabled
            label="Nome Aluno" placeholder="" />
          <s-select v-model="object.cdTreino" divClass="col-md-1" label="Treino" :items="treinos" :clearable="false"
            required />
          <s-input-text v-model="object.series" ref="serie" divClass="col-md-2" label="Série" placeholder="" required />
          <s-input-text v-model="object.repeticoes" ref="repeticoes" divClass="col-md-2" label="Repetições" placeholder=""
            required />
          <s-input-text v-model="object.peso" ref="peso" maxlength="4" divClass="col-md-1" label="Peso" placeholder=""
            required />
          <s-input-text v-model="object.descanso" ref="intervalo" v-mask="'##:##:##'" divClass="col-md-2"
            label="Intervalo" minlength="8" placeholder="00:00:30" required />
          <s-select v-model="object.status" divClass="col-md-2" label="Status" :items="status" :clearable="false" />
          <s-input-check v-model="object.alternativo" divClass="col-md-2 mt-3" label="Alternativo" />
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

import Exercicio from '@/views/cadastros/exercicio/Exercicio.vue'
import Usuario from '@/views/administracao/usuario/Usuario.vue'
import { validateForm } from '@/rule/functions'
import { insert, getById, update } from '@/crud'

export default {
  name: 'treinoNew',

  components: {
    Exercicio,
    Usuario
  },

  data: () => ({
    object: {},
    valid: false,
    Modal: null,
    modalError: null,
    modalNotLogged: null,
    modalBody: null,
    title: null,
    route: 'treino',

    idExercicio: null,
    descricaoExercicio: "",
    idAluno: null,
    nomeAluno: "",

    status: [
      { label: "Ativo", value: 1 },
      { label: "Inativo", value: 0 },
    ],

    treinos: [
      { label: "A", value: 'A' },
      { label: "B", value: 'B' },
      { label: "C", value: 'C' }
    ]

  }),

  methods: {
    async loadItem(id) {
      if (await this.$checkSession()) {
        await getById(this.route, id)
          .then((res) => {
            this.object = res
            this.object.status ? this.object.status = 1 : this.object.status = 0
            this.idExercicio = res.exercicio.id
            this.descricaoExercicio = res.exercicio.descricao
            this.idAluno = res.usuario.id
            this.nomeAluno = res.usuario.nome
          })
          .catch((err) => {
            this.$router.push({ name: 'treino' })
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
          this.object.idExercicio = this.idExercicio
          this.object.idUsuario = this.idAluno

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.data[0]
              this.modalError.show()
            }

            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Treino criado com sucesso !')
              this.object = {}
              this.idAluno = null
              this.nomeAluno = null
              this.idExercicio = null
              this.descricaoExercicio = null
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

          this.object.exercicio.id = this.idExercicio
          this.object.exercicio.descricao = this.descricaoExercicio
          this.object.idExercicio = this.idExercicio

          const newObj = { ...this.object }

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
          this.object.idExercicio = this.idExercicio
          this.object.idUsuario = this.idAluno

          const result = await insert(this.route, this.object)

          if (result.status) {
            if (result.status != 204 && result.status != 200) {
              this.modalBody = result.response.data[0]
              this.modalError.show()
            }

            else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Treino criado com sucesso !')
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

    async loadDescriptionExercicio() {
      if (this.idExercicio) {
        await getById("exercicio", this.idExercicio)
          .then((res) => {
            this.descricaoExercicio = res.descricao
          })
          .catch((err) => {
            console.log(err.erros)
            this.modalBody = `Exercício ${this.idExercicio} não foi encontrado`
            this.modalError.show()
          })
        this.idAparelho
      }
    },

    async loadDescriptionAluno() {
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

    async handleSelectedExercicio(item) {
      this.$refs.idExercicio.modalZoom.hide()
      this.idExercicio = item.id.toString()
      if (this.idExercicio) {
        await getById("exercicio", this.idExercicio)
          .then((res) => {
            this.descricaoExercicio = res.descricao
          })
          .catch((err) => {
            console.log(err.erros)
            this.modalBody = `Exercício ${this.idExercicio} não foi encontrado`
            this.modalError.show()
          })
        this.idAparelho
      }
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

    logout() { logout(this) }
  },

  mounted() {
    this.$route.name == 'treinoUpdate' ? this.title = 'Edição de Treino' : this.title = 'Cadastro de Treino'
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