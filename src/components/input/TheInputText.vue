<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <input
      :ref="ref"
      v-model="inputValue"
      :disabled="isDisabled"
      type="text"
      @input="updateValue"
      :placeholder="placeholder"
      :minlength="minlength"
      :maxlength="maxlength"
      class="form-control"
      :class="{ 'is-invalid': hasError }"
      @blur="updateValue"
      @keydown.enter.prevent="emitEnter"
    />
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
    modelValue: [String, Number],
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

    emitEnter() {
      this.$emit("enter")
    }
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