<template>
  <div :class="color + ' chip'">
    {{ text }}
    <i class="bi bi-x-lg icon" @click="emitRemove" v-if="clearable"></i>
  </div>
</template>

<script>
export default {
  props: {
    text: {
      type: String,
      required: true,
    },
    color: {
      type: String,
      required: true,
    },
    clearable: {
      type: Boolean,
      default: false,
    }
  },

  methods: {
    emitRemove() {
      this.$emit('remove')
    }
  }
}
</script>

<style>
.chip {
  display: inline-block;
  padding: 4px 8px;
  border-radius: 16px;
  color: white;
  font-size: 12px;
  font-weight: bold;
  text-transform: uppercase;
  text-align: center;
}

.icon {
  cursor: pointer
}
</style>
