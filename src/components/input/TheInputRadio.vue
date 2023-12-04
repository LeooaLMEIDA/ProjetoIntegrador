<template>
  <div class="form-check form-check-inline">
    <input
      :ref="ref"
      v-model="inputValue"
      class="form-check-input"
      type="radio"
      :name="name"
      :value="value"
      @change="update"
      :id="inputId"
    />
    <label class="form-check-label" :for="inputId"> {{ label }} </label>
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'TheRadio',
  inheritAttrs: false,

  props: {
    ref: String,
    name: String,
    value: Number,
    divClass: String,
    divLabel: String,
    label: String,
    inputId: String,
    required: Boolean,
    modelValue: Number,
  },

  data: () => ({
    inputValue: '',
  }),

  methods: {
    update() { this.$emit('update:modelValue', this.inputValue) },
  },

  emmits: [ "update:modelValue" ],

  created() {
    if (this.value != 9) {
      this.inputValue = 1
    } else {
      this.inputValue = 9
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
.form-check-input {
  cursor: pointer;
}
</style>