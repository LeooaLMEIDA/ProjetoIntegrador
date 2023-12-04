<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <div class="input-group">
      <input
        :ref="ref"
        v-model="inputValue"
        :type="typePasswordInput"
        :placeholder="placeholder"
        class="form-control"
        :class="{ 'is-invalid': hasError }"
        @blur="updateValue"
        @keydown.enter.prevent="emitEnter"
      />
      <span class="input-group-text iconButton" @click="toggleVisibility">
        <i :class="iconClass"></i>
      </span>
      <div class="invalid-feedback" v-if="hasError">
        {{ error }}
      </div>
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
    placeholder: String,
    required: Boolean,
    modelValue: String,
  },

  inheritAttrs: false,

  data: () => ({
    view: false,
    inputValue: '',
    error: null,
    hasError: false,
    typePasswordInput: 'password',
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
        `${this.label} deve ter no mínimo ${this.minLength} caracteres.`
      )

      this.update()
    },

    toggleVisibility() {
      this.view = !this.view;
      this.view ? this.typePasswordInput = "text" : this.typePasswordInput = "password"
    },

    emitEnter() {
      this.$emit('enter')
    }
  },

  emmits: [ "update:modelValue" ],

  created() { this.inputValue = this.value },

  computed: {
    iconClass() {
      return this.view ? 'bi bi-eye' : 'bi bi-eye-slash'
    }
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
.iconButton {
  cursor: pointer;
}
</style>