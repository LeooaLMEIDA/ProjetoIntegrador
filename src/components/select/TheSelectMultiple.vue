<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <select
      :ref="ref"
      v-model="inputValue"
      class="form-select"
      :class="{ 'is-invalid': hasError }"
      @blur="updateValue"
      multiple
      size="3"
    >
      <option v-for="(item, index) in items" :key="index">{{ item }}</option>
    </select>
    <div class="invalid-feedback" v-if="hasError">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  props: {
    ref: String,
    divClass: String,
    label: String,
    required: Boolean,
    items: Array,
  },

  inheritAttrs: false,

  data: () => ({
    inputValue: [],
    error: null,
    hasError: false,
  }),

  methods: {
    update() { this.$emit('update:modelValue', this.inputValue) },

    updateValue() {
      this.update()
    },
  },

  emmits: [ "update:modelValue" ],

  created() { this.inputValue = this.modelValue },

  watch: {
    inputValue() { this.update() },

    modelValue() {
      if (this.modelValue != this.inputValue) {
        this.inputValue = this.modelValue
      }
    }
  }
})
</script>

<style></style>
