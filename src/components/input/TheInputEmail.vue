<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <input
      :ref="ref"
      v-model="inputValue"
      type="email"
      :disabled="isDisabled"
      :placeholder="placeholder"
      class="form-control"
      :class="{ 'is-invalid': hasError }"
      @blur="updateValue"
    />
    <div class="invalid-feedback" v-if="hasError">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { validateRequired, validateEmail } from '@/rule/validate'

export default defineComponent({
  props: {
    ref: String,
    divClass: String,
    label: String,
    isDisabled: Boolean,
    placeholder: String,
    required: Boolean,
    modelValue: String,
  },

  inheritAttrs: false,

  data: () => ({
    inputValue: '',
    error: null,
    hasError: false,
  }),

  methods: {
    update() { this.$emit('update:modelValue', this.inputValue) },

    updateValue() {
      if (validateRequired(this.inputValue, this.required, this, `${this.label} é obrigatório.`)) {
        validateEmail(this.inputValue, this, `${this.label} informado é inválido.`)
      }

      this.update()
    },
  },

  emmits: [ "update:modelValue" ],

  created() { this.inputValue = this.value },

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

<style></style>