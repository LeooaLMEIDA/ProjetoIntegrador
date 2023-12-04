<template>
  <section class="mx-2">
    <h4>{{ title }}</h4>
    <s-breadcumb v-if="breadcrumb"/>
  </section>
</template>

<script>
export default {
  props: {
    title: String,
    breadcrumb: Boolean
  },
}
</script>

<style></style>