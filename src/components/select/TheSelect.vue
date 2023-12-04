<template>
   <div :class="`mb-3 ${divClass}`">
    <s-label :label="label" :required="required" />
    <div class="position-relative">
      <select
        ref="theSelect"
        v-model="inputValue"
        class="form-select"
        @change="updateValue"
        :disabled="isDisabled"
        :class="{ 'is-invalid': hasError }"
        @blur="updateValue"
      >
        <option v-for="(item, index) in items" :key="index" :value="item.value">{{ item.label }}</option>
      </select>
      <button class="clear" @click="clear" v-if="inputValue != null && !isDisabled && clearable">
        <i class="bi bi-x-lg"></i>
      </button>
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
      required: Boolean,
      modelValue: [String, Number],
      isDisabled: Boolean,
      items: Array,
      clearable: Boolean,
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
        )

        this.update()
        },

        clear() {
          this.inputValue = null
          this.updateValue()
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

<style>
  .clear {
    position: absolute;
    top: 50%;
    right: 25px;
    transform: translateY(-50%);
    border: none;
    background-color: transparent;
  }
</style>