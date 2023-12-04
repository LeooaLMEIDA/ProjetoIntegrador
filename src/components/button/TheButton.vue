<template>
  <button :class="`btn btn-${this.color} ${size} me-2`">
    <i :class="`pe-1 bi bi-${this.icon}`" v-if="icon"></i>{{ label }}
  </button>
</template>

<script>
export default {
  props: {
    label: String,
    color: String,
    icon: String,
    size: {
      type: String,
      default: ''
    }
  },
}
</script>

<style></style>