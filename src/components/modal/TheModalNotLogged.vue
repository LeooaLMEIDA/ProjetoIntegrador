<template>
  <div class="modal fade" ref="modalPattern" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <span class="fs-5 text-white">Conexão Expirada!</span>
        </div>
        <div class="modal-body text-dark">
          <span>Autentique-se novamente para utilizar a aplicação.</span>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-sm btn-primary" data-bs-dismiss="modal" @click="emitConfirm">
            Entendi
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
  }
}
</script>

<style></style>