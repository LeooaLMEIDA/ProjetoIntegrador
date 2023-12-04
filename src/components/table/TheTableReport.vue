<template>
  <div>
    <table class="table table-striped table-hover table-bordered table-sm ">
      <thead class="text-center table-light">
        <tr>
          <th
            v-for="header in headers"
            :key="header"
            v-bind:class="{'col-md-1': header === 'Ações'}">
              {{ header }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in items" :key="index">
          <td v-for="header in headers" :key="header" class="align-middle">
            <slot :name="header" :item="item">{{ item[header] }}</slot>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

export default {
  props: {
    headers: { type: Array, required: true },
    items: Array,
    path: String,
  },

  data: () => ({
  }),
}
</script>

<style></style>

<!-- <template>
  <div class="row table-responsive px-3 pt-1">
    <table class="table table-striped table-hover table-bordered table-sm ">
      <thead class="text-center table-light">
        <tr>
          <th v-for="header in headers" :key="header">
            {{ header }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, index) in displayedItems" :key="item.id || index">
          <td v-for="header in headers" :key="header" class="align-middle">
            <slot :name="header" :item="item">{{ item[header] }}</slot>
          </td>
        </tr>
      </tbody>
    </table>
    <h5 class="text-secondary text-center mt-3" v-if="displayedItems.length < 1">Nenhum item a ser exibido.</h5>
    <nav>
      <ul class="pagination pagination-sm justify-content-end">
        <li class="page-item" :class="{ disabled: currentPage === 1 }">
          <a class="page-link" href="#" @click.prevent="prevPage">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li class="page-item"
          v-for="page in totalPages"
          :key="page"
          :class="{ active: page === currentPage }"
        >
          <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
        </li>
        <li class="page-item" :class="{ disabled: currentPage === totalPages }">
          <a class="page-link" href="#" @click.prevent="nextPage">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
  <div class="row mt-3" v-if="path">
    <div class="col-6 col-sm-6 col-md-2 px-3">
      <TheButton type="button" label="Novo" color="primary" icon="plus-lg" @click="goTo(path)" />
    </div>
  </div>
</template>

<script>
import TheButton from '@/components/formComponents/TheButton.vue'

export default {
  components: {
    TheButton,
  },

  props: {
    headers: { type: Array, required: true },
    items: Array,
    path: String,
    itemsPerPage: { type: String, default: "10"  },
  },

  data: () => ({
    currentPage: 1,
  }),

  computed: {
    displayedItems() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.items.slice(start, end);
    },

    totalPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
  },

  methods: {
    changePage(page) {
      this.currentPage = page;
    },

    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },

    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },

    goTo(path) {
      this.$router.push({ name: path });
    },
  },
}
</script>

<style></style> -->