<template>
  <div
    class="modal modal-lg fade"
    ref="modalPattern"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <span class="fs-5 text-white">
            Exportar Arquivos
          </span>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body text-dark">
          <slot></slot>
        </div>
          <hr class="border border-1">
          <div class="row m-1">
            <div class="col-12 text-start" v-if="show">
              <a
                class="link-opacity-75 link-opacity-100-hover"
                @click="emitExport"
                style="color: #0777f8; cursor: pointer;"
                >
                <u>Clique Aqui</u>
              </a>
              para realizar o download do arquivo.
            </div>


            <div class="col-12 text-start" v-if="button">
              <TheButton type="button" label="Exportar" color="primary" icon="bi bi-download" @click="emitExport" />
            </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'modalPattern',

  props: {
    show: {
      type: Boolean,
      default: false
    },
    button: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    emitExport() {
      this.$emit('export')
    }
  }
}
</script>

<style></style>
