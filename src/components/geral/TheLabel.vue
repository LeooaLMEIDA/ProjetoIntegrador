<template>
  <label class="form-label">{{ label }}
    <span v-if="required" class="text-danger">
      <strong>*</strong>
    </span>
  </label>
</template>

<script>
export default {
  name: "TheLabel",

  props: {
    label: String,
    required: Boolean,
  }
}
</script>

<style></style>