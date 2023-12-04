<template>
  <div class="d-flex justify-content-center">
    <div :class="['circle', { 'bg-success': status, 'bg-danger': !status }]" :style="`--size:${size}px;`"></div>
  </div>
</template>

<script>
export default {
  props: {
    status: {
      type: Boolean,
      required: true,
    },
    size: {
      type: String,
      default: '18',
    },
  },
}
</script>

<style>
.circle {
  --size: 24px;
  width: var(--size);
  height: var(--size);
  border-radius: 50%;
}

.green {
  background-color: green;
}

.red {
  background-color: red;
}
</style>
