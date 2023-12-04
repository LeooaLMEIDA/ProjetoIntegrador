<template>
  <div :class="`${this.divClass}`">
    <s-label :label="label" :required="required" />
    <div class="input-group mb-3">
      <VueDatePicker
        v-mask="'##/##/#### - ##/##/####'"
        ref="datePicker"
        model-auto
        range
        text-input
        :format="'dd/MM/yyyy'"
        model-type="yyyy-MM-dd"
        v-model="inputValue"
        select-text="Selecionar"
        cancel-text="Cancelar"
        locale="pt-BR"
        :teleport-center="teleportCenter"
        :disabled="isDisabled"
        :class="{ 'is-invalid': hasError }"
        :input-class-name="hasError ? 'dp-custom-input' : ''"
        @update:model-value="updateValue"
        @blur="updateValue"
        :enable-time-picker="false"
        :day-names="['D', 'S', 'T', 'Q', 'Q', 'S', 'S']"
        :min-date="new Date(1900, 1, 1)"
        :max-date="new Date(2023, 12, 31)"
        @keydown.enter.prevent
      >
        <template #action-buttons>
          <TheButton
            color="light"
            class="btn-sm"
            icon="x-lg"
            @click="closePicker"
          />
          <TheButton
            color="primary"
            icon="check-lg"
            class="btn-sm"
            @click="selectDate"
          />
        </template>
        <template #input-icon>
          <i class="bi bi-calendar3 ms-2"></i>
        </template>
      </VueDatePicker>
      <div class="form-group right-inner-addon">
        <b
          ><i class="bi bi-exclamation-circle text-danger" v-if="hasError"></i
        ></b>
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
import { validateDate } from "@/rule/validate";

export default defineComponent({

  data: () => ({
    inputValue: "",
    error: null,
    hasError: false,
    textInputOptions: {
      format: "dd/MM/yyyy",
    },
    teleportCenter: false,
  }),

  props: {
    divClass: String,
    label: String,
    isDisabled: Boolean,
    required: Boolean,
    placeholder: String,
    modelValue: [String, Array],
  },

  methods: {
    update() {
      this.$emit("update:modelValue", this.inputValue);
    },

    updateValue() {
      validateDate(
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

    selectDate() {
      this.$refs.datePicker.selectDate()
    },

    closePicker() {
      this.$refs.datePicker.closeMenu()
    }
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

<style></style>