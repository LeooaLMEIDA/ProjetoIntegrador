<template>
  <div :class="`${this.divClass}`">
    <s-label :label="label" :required="required" />
    <div class="input-group mb-3">
      <s-datepicker v-mask="'##/##/####'" text-input :text-input-options="textInputOptions" format="dd/MM/yyyy"
        model-type="yyyy-MM-dd" v-model="inputValue" now-button-label="Hoje" locale="pt-BR" :disabled="isDisabled"
        :teleport-center="teleportCenter" close-on-scroll auto-apply show-now-button
        :input-class-name="hasError ? 'dp-custom-input' : ''" :class="{ 'is-invalid': hasError }"
        @update:model-value="updateValue" @blur="updateValue" :enable-time-picker="false"
        :day-names="['S', 'T', 'Q', 'Q', 'S', 'S', 'D']" :min-date="new Date(1900, 1, 1)"
        :max-date="new Date(2023, 12, 31)" @keydown.enter.prevent>
        <template #input-icon>
          <i class="bi bi-calendar3 ms-2"></i>
        </template>
      </s-datepicker>
      <div class="form-group right-inner-addon">
        <b><i class="bi bi-exclamation-circle text-danger" v-if="hasError"></i></b>
      </div>
      <div class="invalid-feedback" v-if="hasError">
        {{ error }}
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { defineComponent } from "vue";
import { validateRequired } from "@/rule/validate";

export default defineComponent({
  data: () => ({
    inputValue: "",
    error: null,
    hasError: false,
    textInputOptions: {
      enterSubmit: true,
    },
    teleportCenter: false,
  }),

  props: {
    divClass: String,
    label: String,
    isDisabled: Boolean,
    required: Boolean,
    placeholder: String,
    modelValue: String,
  },

  methods: {
    update() {
      if (this.inputValue == "") this.inputValue = null;
      this.$emit("update:modelValue", this.inputValue);
    },

    updateValue() {
      validateRequired(
        this.inputValue,
        this.required,
        this,
        `${this.label} é obrigatório.`
      );

      this.update();
    },

    checkScreenSize() {
      this.teleportCenter = window.innerHeight < 600;
    },
  },

  emmits: ["update:modelValue"],

  created() {
    if (this.modelValue) {
      this.inputValue = moment(this.modelValue).format("YYYY-MM-DD");
    }
  },

  mounted() {
    this.checkScreenSize();
    window.addEventListener("resize", this.checkScreenSize);
  },

  watch: {
    inputValue() {
      this.update();
    },

    modelValue() {
      if (this.modelValue == undefined) {
        this.inputValue = null;
      }

      if (this.modelValue != null) {
        if (this.modelValue != this.inputValue) {
          this.inputValue = moment(this.modelValue).format("YYYY-MM-DD");
        }
      }
    },
  },
});
</script>

<style>
.dp-custom-input {
  border-color: #dc3545;
}

.input-slot-image {
  font-size: 17px;
  color: #dc3545;
}

.right-inner-addon {
  position: relative;
}

.right-inner-addon i {
  position: absolute;
  padding: 10px 12px;
  right: 0;
}

.right-inner-addon input.form-control {
  padding-right: 30px;
}
</style>