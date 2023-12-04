<template>
  <div class="m-3">
    <div class="row">
      <div class="col-8">
        <s-title title="Treinos" :breadcrumb="true" />
      </div>
    </div>
    <s-input-filter @index="handleIndex" @filter="filterAll" @clear="loadItems" name="filterWorkMeasurement"
      :filters="filterObject" />
    <div class="card card-body mx-2">
      <div class="row">
        <div class="col-12">
          <s-table v-model="actualPage" :headers="headers" :items="items" :totalPages="pages" v-if="!loader">
            <template v-slot:treino="{ item }">
              {{ item.cdTreino }}
            </template>
            <template v-slot:exercicio="{ item }">
              {{ item.exercicio.descricao }}
            </template>
            <template v-slot:aluno="{ item }">
              {{ item.usuario.nome }}
            </template>
            <template v-slot:series="{ item }">
              <div class="text-center">
                {{ item.series }}
              </div>
            </template>
            <template v-slot:repeticoes="{ item }">
              <div class="text-center">
                {{ item.repeticoes }}
              </div>
            </template>
            <template v-slot:descanso="{ item }">
              <div class="text-center">
                {{ item.descanso }}
              </div>
            </template>
            <template v-slot:peso="{ item }">
              <div class="text-center">
                {{ item.peso }}
              </div>
            </template>
            <template v-slot:status="{ item }">
              <div class="text-center">
                <s-chip :color="getStatusColor(item.status)" :text="translateStatusText(item.status)">
                  {{ item.status }}
                </s-chip>
              </div>
            </template>
            <template v-slot:actions="{ item }">
              <div class="text-center">
                <i class="bi bi-pencil-fill text-secondary px-1" style="cursor: pointer" @click="edit(item.id)"></i>
                <i class="bi bi-trash-fill text-danger px-1" style="cursor: pointer" @click="removeConfirm(item)"></i>
              </div>
            </template>
          </s-table>
        </div>
        <div class="col-12" v-if="!loader">
          <s-button type="button" label="Novo" color="primary" icon="plus-lg"
            @click="this.$router.push({ name: 'treinoNew' })" />
        </div>
      </div>
      <!-- <TheLoader v-if="loader" /> -->
    </div>
    <s-modal-delete ref="modalDelete" @confirm="remove" />
    <s-modal-notlogged ref="modalNotLogged" @confirm="logout" />
  </div>
</template>

<script>
import { logout } from '@/rule/functions.js'
import { get, remove, update, search } from '@/crud.js'

export default {
  name: 'treino',

  data: () => ({
    route: 'treino',
    headers: [
      { title: 'Treino', field: 'treino' },
      { title: 'Exercício', field: 'exercicio' },
      { title: 'Aluno', field: 'aluno' },
      { title: 'Séries', field: 'series' },
      { title: 'Repetições', field: 'repeticoes' },
      { title: 'Intervalo/Descanso', field: 'descanso' },
      { title: 'Peso', field: 'peso' },
      { title: 'Status', field: 'status' },
      { title: 'Ações', field: 'actions' },
    ],
    items: [],
    object: {},
    selectedItem: null,
    Modal: null,
    choosed: null,
    loader: false,
    modalDelete: null,
    modalNotLogged: null,
    pages: null,
    actualPage: 1,
    limit: 10,

    filterObject: [
      {
        label: 'Treino',
        ref: 'codTreino',
        route: 'treino/pages/filter/str',
        subRoute: '',
        param: 'value',
        column: 'codigo_treino',
        type: 'text',
        signal: '',
        operator: '',
        index: 1
      },
      {
        label: 'Exercício',
        ref: 'exercicioName',
        route: 'treino/pages/filter/str',
        subRoute: '',
        param: 'value',
        column: 'exercicio',
        type: 'text',
        signal: '',
        operator: '',
        index: 2
      },
      {
        label: 'Aluno',
        ref: 'autorName',
        route: 'treino/pages/filter/str',
        subRoute: '',
        param: 'value',
        column: 'usuario',
        type: 'text',
        signal: '',
        operator: 'LIKE',
        index: 3
      },
      {
        label: 'Status',
        ref: 'aparelhoStatus',
        route: 'treino/pages/filter/bool',
        subRoute: '',
        param: 'value',
        column: 'status',
        type: 'select',
        items: [
          { label: 'Ativo', value: 'true' },
          { label: 'Inativo', value: 'false' }
        ],
        index: 4
      }
    ],

    filterOption: 1,
    filterParam: null,
  }),

  methods: {
    async loadItems(page = 1) {
      if (await this.$checkSession()) {
        const query = { params: { page: page, limit: this.limit } }
        let raw = []
        if (this.filterParam) {
          this.filterParam.params.page = page
          this.filterParam.params.limit = this.limit
          raw = await search(this.filterParam.route, this.filterParam.params)
        } else {
          raw = await get('treino/pages', query)
        }
        this.items = raw.data
        this.pages = Math.ceil(raw.total / this.limit)
      } else {
        this.modalNotLogged.show()
      }
    },

    async edit(id) {
      const route = {
        name: 'treinoUpdate',
        params: { id: id },
      }

      this.$router.push(route)
    },

    async remove() {
      if (await this.$checkSession()) {
        await remove(this.route, this.choosed.id)

        this.$store.dispatch('setShowToast', true)
        this.$store.dispatch('setToastMessage', 'Treino inativado com sucesso !')
        this.loadItems()
      } else {
        this.modalNotLogged.show()
      }
    },

    removeConfirm(item) {
      this.choosed = item
      this.modalDelete.show()
    },

    getStatusColor(status) {
      return status == 1 ? "bg-success" : "bg-danger";
    },

    translateStatusText(status) {
      return status == 1 ? "Ativo" : "Inativo";
    },

    async filterAll(event) {
      if (await this.$checkSession()) {
        this.filterParam = event
        this.loadItems(1)
      } else {
        this.modalNotLogged.show()
      }
    },

    handleIndex(event) {
      this.filterOption = event
    },

    changeHeaders() {
      if (this.filterOption == 1) {
        this.headers = [
          { title: 'Treino', field: 'treino' },
          { title: 'Exercício', field: 'exercicio' },
          { title: 'Aluno', field: 'aluno' },
          { title: 'Séries', field: 'series' },
          { title: 'Repetições', field: 'repeticoes' },
          { title: 'Intervalo/Descanso', field: 'descanso' },
          { title: 'Peso', field: 'peso' },
          { title: 'Status', field: 'status' },
          { title: 'Ações', field: 'actions' },
        ]
      }
      else if (this.filterOption == 2) {
        this.headers = [
          { title: 'Exercício', field: 'exercicio' },
          { title: 'Treino', field: 'treino' },
          { title: 'Aluno', field: 'aluno' },
          { title: 'Séries', field: 'series' },
          { title: 'Repetições', field: 'repeticoes' },
          { title: 'Intervalo/Descanso', field: 'descanso' },
          { title: 'Peso', field: 'peso' },
          { title: 'Status', field: 'status' },
          { title: 'Ações', field: 'actions' },
        ]
      }
      else if (this.filterOption == 3) {
        this.headers = [
          { title: 'Aluno', field: 'aluno' },
          { title: 'Exercício', field: 'exercicio' },
          { title: 'Treino', field: 'treino' },
          { title: 'Séries', field: 'series' },
          { title: 'Repetições', field: 'repeticoes' },
          { title: 'Intervalo/Descanso', field: 'descanso' },
          { title: 'Peso', field: 'peso' },
          { title: 'Status', field: 'status' },
          { title: 'Ações', field: 'actions' },
        ]
      }
      else if (this.filterOption == 4) {
        this.headers = [
          { title: 'Status', field: 'status' },
          { title: 'Aluno', field: 'aluno' },
          { title: 'Exercício', field: 'exercicio' },
          { title: 'Treino', field: 'treino' },
          { title: 'Séries', field: 'series' },
          { title: 'Repetições', field: 'repeticoes' },
          { title: 'Intervalo/Descanso', field: 'descanso' },
          { title: 'Peso', field: 'peso' },
          { title: 'Ações', field: 'actions' },
        ]
      }
    },
    logout() {
      logout(this)
    },
  },

  async mounted() {
    this.modalDelete = new this.$Modal(this.$refs.modalDelete.$refs.modalPattern)
    this.modalNotLogged = new this.$Modal(this.$refs.modalNotLogged.$refs.modalPattern)

    await this.loadItems()
  },

  watch: {
    filterOption() {
      this.filterParam = null
      this.loadItems()
      this.changeHeaders()
    },
    actualPage() {
        this.loadItems(this.actualPage)
    },
  },
}
</script>

<style></style>
