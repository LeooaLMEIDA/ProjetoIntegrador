<template>
  <nav
    style="--bs-breadcrumb-divider: url(&#34;data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='%236c757d'/%3E%3C/svg%3E&#34;)"
    aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item active" @click="goBack" :class="{ 'theActive': actualRoute == 'osDetail' }">{{ getRoute(actualRoute) }}</li>
      <li class="breadcrumb-item active" :class="{ 'theActive': items.path == actualRoute && items.childrenName }"
        v-if="items.path == actualRoute || items.childrenPath == actualRoute || items.UpdatePath == actualRoute" aria-current="page"><a href="#"
          @click="$router.push({ name: items.path })">{{ items.name }}</a></li>
      <li class="breadcrumb-item theActive" v-if="items.childrenPath == actualRoute || items.UpdatePath == actualRoute" aria-current="page">{{
        actualRoute == items.childrenPath ? items.childrenName : items.UpdateName }}</li>
    </ol>
  </nav>
  <!-- <div class="container-fluid ">
    <nav
      style="--bs-breadcrumb-divider: url(&#34;data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='%236c757d'/%3E%3C/svg%3E&#34;);"
      aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active">{{ getRoute($route.name) }}</li>
        <li class="breadcrumb-item active" :class="{ 'theActive': items.path == $route.name }"
          v-if="items.path == $route.name || items.childrenPath == $route.name" aria-current="page"><a href="#"
            @click="$router.push({ name: items.path })">{{ items.name }}</a></li>
        <li class="breadcrumb-item theActive" v-if="items.childrenPath == $route.name" aria-current="page">{{
          items.childrenName }}</li>
      </ol>
    </nav>
  </div> -->
</template>

<script>
import { getRouteName } from '@/rule/functions'

export default {
  name: 'TheBreadcrumb',

  computed: {
    actualRoute() {
      return this.$route.name
    }
  },

  methods: {
    getRoute(route) {
      this.items = getRouteName(route);
      return this.items.parent
    },

    goBack() {
      if (this.actualRoute == 'osDetail') {
        this.$router.push({ name: 'dashboard' })
      } else if (this.actualRoute == 'osChildren') {
        this.$router.push({ name: 'os' })
      }
    }
  }
}
</script>

<style>
.theActive {
  color: rgb(9, 132, 247) !important;
  cursor: pointer;
}

a {
  color: inherit;
  text-decoration: none;
}
</style>