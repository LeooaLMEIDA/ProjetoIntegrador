<template>
  <div class="modal fade" ref="modalPattern" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header bg-danger">
          <span class="fs-5 text-white">{{ modalTitle }}</span>
          <!-- <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
        </div>
        <div class="modal-body text-dark">
          <span>{{ modalBody }}</span>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-sm btn-primary" data-bs-dismiss="modal">
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

  props: {
    modalTitle: String,
    modalBody: String,
  },
}
</script>

<style></style>