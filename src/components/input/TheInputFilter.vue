<template>
  <div class="card card-body mx-2 mb-3">
    <div class="row">
      <div class="col-1">
        <h5>Filtro:</h5>
      </div>
      <div class="col-8">
        <s-input-radio v-for="filter in filters" :key="filter.value" :name="name" :value="filter.index"
          :label="filter.label" v-model="option" :inputId="filter.label" />
      </div>
      <div class="col-2 text-end">
        <span class="small text-primary">
          <b>{{ allFilterStatus }}</b>
        </span>
      </div>
      <div class="col-1 text-end">
        <i :class="filterToggle ? 'bi bi-chevron-up icon' : 'bi bi-chevron-down icon'" @click="toggleFilter"></i>
      </div>
      <hr class="border border-1" />
    </div>
    <s-transition>
      <div class="row align-items-center" v-if="filterToggle">
        <div class="col-xs-9 col-sm-9 col-md-9 col-xxl-9">
          <s-input-text v-if="object.type == 'text'" v-model="inputValue" :ref="object.ref" :label="object.label"
            @enter="filterAll" />
          <s-select v-else-if="object.type == 'select'" :items="object.items" v-model="inputValue" :ref="object.ref"
            :label="object.label" />
          <s-input-daterange v-else-if="object.type == 'range'" v-model="inputValue" :ref="object.ref"
            :label="object.label" />
          <s-input-date v-else v-model="inputValue" :ref="object.ref" :label="object.label" />
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-xxl-3 mt-2 text-end">
          <s-button type="button" color="primary" label="Buscar" icon="bi bi-search" @click="emitConfirm" />
          <s-button type="button" color="outline-danger" label="Limpar" icon="bi bi-x-lg" @click="emitClear" />
        </div>
      </div>
    </s-transition>

    <s-modal-notlogged ref="modalNotLogged" @confirm="logout" />
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { logout } from '@/rule/functions.js'
import { get, search } from '@/crud.js'
import { Modal } from 'bootstrap'

export default defineComponent({
  name: 'TheFilter',

  props: {
    name: String,
    filters: Array,
  },

  data: () => ({
    option: null,
    inputValue: null,
    object: {},
    items: [],
    modalNotLogged: null,
    allFilterStatus: null,
    refArray: [],
    filterToggle: false,
  }),

  methods: {
    async loadItems() {
      if (await this.$checkSession()) {
        this.items = await get(this.object.route)
      } else {
        this.modalNotLogged.show()
      }
    },

    validateField() {
      this.object = this.filters.find((e) => e.index == this.option)
      this.clearFilterAll()
      this.$emit('index', this.option)
    },

    emitConfirm() {
      this.filterAll()
    },

    emitClear() {
      this.clearFilterAll()
      this.$emit('filter', null)
    },

    toggleFilter() {
      this.filterToggle = !this.filterToggle
    },

    async filterAll() {
      if (await this.$checkSession()) {
        let subRoute = this.object.subRoute
        let obParam = this.object.param
        let obValue = this.object.paramValue
        let params = {}

        if (this.object.type == 'select') {
          if (this.inputValue == 2) {
            this.emitClear()
            return
          }
        }

        if (this.object.paramValue) {
          params[obParam[0]] = obValue
          params[obParam[1]] = this.inputValue
        } else {
          params[obParam] = this.inputValue
        }

        if (this.inputValue != '' || this.inputValue != null) {
          if (this.object.column) {
            params.column = this.object.column
          }

          //params.operator = this.object.operator
          //params.signal = this.object.signal
          //params.value = this.inputValue

          const filterObject = {
            route: `${this.object.route}`,
            params,
          }
          this.$emit('filter', filterObject)

          if (!this.items) {
            this.items = []
          }
        } else {
          this.clearFilterAll()
        }

        this.updateAllStatus()
      } else {
        this.modalNotLogged.show()
      }
    },

    updateAllStatus() {
      let allFilterFields = []

      allFilterFields.push(this.object.label.toUpperCase())

      if (allFilterFields.length > 0) {
        this.allFilterStatus = `Filtrando por: ${allFilterFields}`
      } else {
        this.allFilterStatus = null
      }
    },

    async clearFilterAll() {
      this.inputValue = null
      this.allFilterStatus = null
    },

    logout() {
      logout(this)
    },
  },

  async mounted() {
    this.modalNotLogged = new this.$Modal(this.$refs.modalNotLogged.$refs.modalPattern)
  },

  watch: {
    option: {
      handler() {
        this.validateField()
      },
      deep: true,
    },
  },
})
</script>

<style>
.icon {
  cursor: pointer;
}
</style>
