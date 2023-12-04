<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <textarea
      :ref="ref"
      v-model="inputValue"
      :disabled="isDisabled"
      type="text"
      :placeholder="placeholder"
      :minlength="minlength"
      :maxlength="maxlength"
      class="form-control"
      :class="{ 'is-invalid': hasError }"
      @blur="updateValue"
      rows="3"
      @keydown.enter.prevent
    >
    </textarea>
    <div class="invalid-feedback" v-if="hasError">
      {{ error }}
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { validateRequired } from '@/rule/validate'

export default defineComponent({
  props: {
    ref: String,
    divClass: String,
    label: String,
    isDisabled: Boolean,
    placeholder: String,
    required: Boolean,
    minlength: String,
    maxlength: String,
    modelValue: String,
  },

  inheritAttrs: false,

  data: () => ({
    inputValue: '',
    error: null,
    hasError: false,
  }),

  methods: {
    update() {
      if (this.inputValue == "") this.inputValue = null
      this.$emit('update:modelValue', this.inputValue)
    },

    updateValue() {
      validateRequired(
        this.inputValue,
        this.required,
        this,
        `${this.label} é obrigatório.`,
        this.minlength,
        `${this.label} deve ter no mínimo ${this.minlength} caracteres.`
      )

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
  },
})
</script>

<style></style>