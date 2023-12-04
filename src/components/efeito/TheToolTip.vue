<template>
  <div class="l-tooltip"
    @mouseover="open = true"
    @mouseout="open = false"
    :style="`--main-bg-color: ${color};`"
  >
    <slot></slot>
    <div class="tooltip-label" v-if="(open || active) && label">{{ label }}</div>
  </div>
</template>

<script>
export default {
  props: {
    label: String,
    color: {
      type: String,
      default: '#0d6efd',
    },
    active: {
      type: Boolean,
      default: false,
    },
  },
  data: () => ({
    open: false,
  }),
}
</script>

<style>
.l-tooltip {
  display: inline-block;
  position: relative;
}

.tooltip-label {
  display: block;
  position: absolute;
  font-size: 12px;
  text-align: left;
  min-height: 22px;
  min-width: 100px;
  max-width: 220px;
  bottom: calc(100% + 15px);
  left: -10px;
  border-radius: 5px;
  padding: 5px 8px;
  z-index: 10;
  color: white;
  background: var(--main-bg-color);
  overflow-wrap: break-word;
}

.tooltip-label:before {
  content: '';
  position: absolute;
  top: calc(100%);
  left: 10px;
  border-style: solid;
  border-width: 10px;
  border-color: var(--main-bg-color) transparent transparent transparent;
}
</style>
