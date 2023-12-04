<template>
  <div class="modal fade" ref="modalPattern" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header bg-warning">
          <span class="fs-5">Atenção !</span>
          <!-- <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
        </div>
        <div class="modal-body text-dark">
          <span>Deseja realmente excluir o registro ?</span>
        </div>
        <div class="modal-footer d-flex justify-content-between">
          <button type="button" class="btn btn-sm btn-primary" data-bs-dismiss="modal" @click="emitCancel">
            <span class="mx-1">Cancelar</span>
          </button>
          <button type="button" class="btn btn-sm btn-outline-danger" data-bs-dismiss="modal"  @click="emitConfirm">
            <span class="mx-1">Excluir</span>
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

    emitCancel() {
      this.$emit('cancel')
    }
  },
}
</script>

<style></style>