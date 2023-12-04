<template>
  <div class="modal fade" ref="modalPattern" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <span class="fs-5 text-white">Filtrar Registros</span>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body text-dark">
          <slot></slot>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-sm btn-primary" data-bs-dismiss="modal" @click="emitConfirm">
            Filtrar
          </button>
          <button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="modal" @click="emitClear">
            Limpar
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "modalPattern",

  methods: {
    emitConfirm() {
      this.$emit('confirm')
    },

    emitClear() {
      this.$emit('clear')
    },
  }
}
</script>

<style></style>