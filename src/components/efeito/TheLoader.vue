<template>
  <div class="text-center text-secondary">
    <h5>{{ text }}</h5>
    <div class="spinner-border" role="status"></div>
  </div>
</template>

<script>
  export default ({
    name: 'TheLoader',

    props: {
      text: {
        type: String,
        default: 'Carregando dados, aguarde...'
      },
    }
  })
</script>

<style>
.spinner-border {
  width: 3rem;
  height: 3rem;
}
</style>