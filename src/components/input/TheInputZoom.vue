<template>
  <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <div class="input-group">
      <input
        :ref="ref"
        :disabled="isDisabled"
        v-model="inputValue"
        type="text"
        :placeholder="placeholder"
        class="form-control"
        :class="{ 'is-invalid': hasError }"
        @blur="updateValue"
        @keydown.enter.prevent
      />
      <span class="input-group-text iconButton" @click="showModalZoom">
        <i class="bi bi-search"></i>
      </span>
      <div class="invalid-feedback" v-if="hasError">
        {{ error }}
      </div>
    </div>
  </div>
  <s-modal-zoom ref="modalZoom">
    <slot></slot>
  </s-modal-zoom>
</template>

<script>
import { defineComponent } from 'vue'
import { validateRequired } from '@/rule/validate'

export default defineComponent({
  components: {
    setValue: {
      type: Function,
      default: () => null,
    },
  },

  props: {
    ref: String,
    divClass: String,
    label: String,
    isDisabled: Boolean,
    placeholder: String,
    required: Boolean,
    modelValue: [String, Number],
  },

  inheritAttrs: false,

  data: () => ({
    inputValue: '',
    error: null,
    hasError: false,
    Modal: null,
    modalZoom: null,
  }),

  methods: {
    update() {
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
      this.$emit('blur')
      this.update()
    },

    showModalZoom() {
      if (!this.isDisabled) {
        this.modalZoom.show()
      }
    },
  },

  emmits: [ "update:modelValue" ],

  mounted() {
    this.modalZoom = new this.$Modal(this.$refs.modalZoom.$refs.modalPattern)
  },

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