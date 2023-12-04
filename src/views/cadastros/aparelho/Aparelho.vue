<template>
  <div class="m-3">
    <div class="row" v-if="!zoom">
      <div class="col-8">
        <s-title title="Aparelhos" :breadcrumb="true" />
      </div>
    </div>
    <s-input-filter @index="handleIndex" @filter="filterAll" @clear="loadItems" name="filterWorkMeasurement"
      :filters="filterObject" />
    <div class="card card-body mx-2">
      <div class="row">
        <div class="col-12">
          <s-table v-model="actualPage" :headers="headers" :items="items" :totalPages="pages" v-if="!loader">
            <template v-slot:descricao="{ item }">
              {{ item.descricao }}
            </template>
            <template v-slot:status="{ item }">
              <div class="text-center">
                <s-chip :color="getStatusColor(item.status)" :text="translateStatusText(item.status)">
                  {{ item.status }}
                </s-chip>
              </div>
            </template>
            <template v-slot:actions="{ item }">
              <div class="text-center" v-if="!zoom">
                <i class="bi bi-pencil-fill text-secondary px-1" style="cursor: pointer" @click="edit(item.id)"></i>
                <i class="bi bi-trash-fill text-danger px-1" style="cursor: pointer" @click="removeConfirm(item)"></i>
              </div>
              <div class="text-center" v-if="zoom">
                <s-button label="Selecionar" color="primary" type="button" @click="emitSelectedItem(item)" />
              </div>
            </template>
          </s-table>
        </div>
        <div class="col-12" v-if="!loader">
          <s-button type="button" v-if="!zoom" label="Novo" color="primary" icon="plus-lg"
            @click="this.$router.push({ name: 'aparelhoNew' })" />
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
import { get, remove, search } from '@/crud.js'

export default {
  name: 'aparelho',

  props: {
    zoom: {
      type: Boolean,
      default: false,
    },
    valueZoom: String,
  },

  data: () => ({
    route: 'aparelho',
    headers: [
      { title: 'Descrição', field: 'descricao' },
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
        label: 'Nome',
        ref: 'aparelhoDesc',
        route: 'aparelho/pages/filter/str',
        subRoute: '',
        param: 'value',
        column: 'descricao',
        type: 'text',
        signal: '',
        operator: '',
        index: 1
      },
      {
        label: 'Status',
        ref: 'aparelhoStatus',
        route: 'aparelho/pages/filter/bool',
        subRoute: '',
        param: 'value',
        column: 'status',
        type: 'select',
        items: [
          { label: 'Ativo', value: 'true' },
          { label: 'Inativo', value: 'false' }
        ],
        index: 2
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
          raw = await get('aparelho/pages', query)
        }
        this.items = raw.data
        this.pages = Math.ceil(raw.total / this.limit)
      } else {
        this.modalNotLogged.show()
      }
    },


    async edit(id) {
      const route = {
        name: 'aparelhoUpdate',
        params: { id: id },
      }

      this.$router.push(route)
    },

    async remove() {
      if (await this.$checkSession()) {
        await remove(this.route, this.choosed.id)

        this.$store.dispatch('setShowToast', true)
        this.$store.dispatch('setToastMessage', 'Aparelho inativado com sucesso !')
        this.loadItems()
      } else {
        this.modalNotLogged.show()
      }
    },

    removeConfirm(item) {
      this.choosed = item
      this.modalDelete.show()
    },

    emitSelectedItem(item) {
      this.$emit("selectedItem", item)
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
          { title: 'Descrição', field: 'descricao' },
          { title: 'Status', field: 'status' },
          { title: 'Ações', field: 'actions' },
        ]
      }
      else if (this.filterOption == 2) {
        this.headers = [
          { title: 'Status', field: 'status' },
          { title: 'Descrição', field: 'descricao' },
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
