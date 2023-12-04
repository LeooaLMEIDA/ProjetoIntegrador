<template>
    <div class="toast-container position-fixed top-0 end-0 p-3">
      <div ref="toastPattern" data-bs-autohide="true" :data-bs-delay="3000" class="toast hide text-bg-info" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex flex-row align-items-center justify-content-between">
          <div class="toast-body d-flex align-items-center">
            <i class="bi bi-info-circle-fill me-1"></i>
            {{ message }}
          </div>
          <div>
            <button type="button" class="btn-close btn-close-white me-3" data-bs-dismiss="toast" aria-label="Close"></button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
  export default {
    name: "TheToastInfo",
    props: {
      message: String
    }
  }
</script>