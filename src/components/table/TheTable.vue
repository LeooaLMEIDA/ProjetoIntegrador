<template>
  <div v-if="show">
    <table class="table table-striped table-hover table-bordered table-sm">
      <thead class="text-center table-light">
        <tr v-if="headers && items">
          <th v-for="header in headers" :key="header" :class="{ 'col-md-1': header.field === 'action' }">
            {{ header.title }}
          </th>
        </tr>
      </thead>
      <tbody v-if="items">
        <tr v-for="(item, index) in items" :key="index">
          <td v-for="header in headers" :key="header" :class="header.align"
            :style="header.width ? `width: ${header.width};` : ''">
            <slot :name="header.field" :item="item">{{ item[header.field] }}</slot>
          </td>
        </tr>
      </tbody>
    </table>
    <h5 class="text-secondary text-center mt-3" v-if="!items">Nenhum item a ser exibido.</h5>
    <nav v-if="items">
      <div class="row">
        <div class="col-6 d-flex justify-content-end">
          <ul class="pagination pagination-sm">
            <li class="page-item" :class="{ disabled: currentPage === 1 }">
              <a class="page-link" href="#" aria-label="Anterior" @click.prevent="previousPage">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li class="page-item" :class="{ active: true }">
              <a class="page-link" href="#">{{ currentPage }}</a>
            </li>
            <li class="page-item" :class="{ disabled: items.length < 10 || items.length == 0 }">
              <a class="page-link" href="#" aria-label="Proxima" @click.prevent="nextPage">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </div>
        <div class="col-6 d-flex justify-content-end">
          <span class="ml-auto">{{ `Total de Páginas: ${totalPages}` }}</span>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>

export default {
  props: {
    show: { type: Boolean, default: true },
    headers: { type: Array, required: true },
    items: Array,
    path: String,
    itemsPerPage: { type: String, default: '20' },
    totalPages: { type: Number, default: 1 },
    realTotalPages: Number
  },

  data: () => ({
    currentPage: 1,
  }),

  computed: {
    totalItems() {
      if (this.items) { return this.items.length }
    },

    displayedItems() {
      if (this.items) { return this.items }
    },

    pagesToShow() {
      let pages = [1]

      if (this.totalPages > 5) {
        if (this.currentPage > 3) {
          pages.push('...')
        }

        if (this.currentPage > 2 && !pages.includes(this.currentPage - 1)) {
          pages.push(this.currentPage - 1)
        }

        if (!pages.includes(this.currentPage)) {
          pages.push(this.currentPage)
        }

        if (this.currentPage < this.totalPages - 1 && !pages.includes(this.currentPage + 1)) {
          pages.push(this.currentPage + 1)
        }

        if (this.currentPage < this.totalPages - 2 && !pages.includes(this.totalPages)) {
          pages.push('...')
        }
      } else {
        for (let i = 2; i <= this.totalPages - 1; i++) {
          pages.push(i)
        }
      }
      if (this.totalPages > 1 && !pages.includes(this.totalPages)) {
        pages.push(this.totalPages)
      }

      return pages
    },
  },

  methods: {
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--
      }
    },

    nextPage() {
      this.currentPage++
    },

    // goToPage(pageNumber) {
    //   if (pageNumber === '...') {
    //     return
    //   }

    //   this.currentPage = pageNumber
    // },
  },

  watch: {
    currentPage() {
      this.$emit('update:modelValue', this.currentPage)
    },

    items() {
      if (this.items) {
        if (this.items.length == 0) {
          this.previousPage()
        }
      }
    }
  },
}
</script>
