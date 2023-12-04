<template>
  <div :class="`${divClass} mb-3`">
    <div class="card">
      <div class="card-header h5 text-bg-primary text-center" v-if="header">
        {{ header }}
      </div>
      <div class="card-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TheCard',

  props: {
    divClass: String,
    header: String,
  }
}
</script>

<style></style>