<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="divLabel" :required="required" />
    <div>
      <div class="form-check form-check-inline align-middle">
        <input
          :ref="ref"
          v-model="inputValue"
          class="form-check-input"
          type="checkbox"
          :disabled="isDisabled"
          @change="update"
        />
        <label class="form-check-label"> {{ label }} </label>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'TheCheck',
  inheritAttrs: false,

  props: {
    ref: String,
    divClass: String,
    divLabel: String,
    isDisabled: Boolean,
    label: String,
    required: Boolean,
    modelValue: Boolean,
  },

  data: () => ({
    inputValue: '',
  }),

  methods: {
    update() { this.$emit('update:modelValue', this.inputValue) },
  },

  emmits: [ "update:modelValue" ],

  created() {
    if (!this.value) this.value = false
    this.inputValue = this.value
  },

  watch: {
    inputValue() { this.update() },

    modelValue() {
      if (this.modelValue != this.inputValue) {
        this.inputValue = this.modelValue
      }
    }
  },
})
</script>

<style>
.form-check-input {
  cursor: pointer;
}
</style>