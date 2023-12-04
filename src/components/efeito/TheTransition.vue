<template>
  <Transition name="my-transition">
    <slot></slot>
  </Transition>
</template>

<script>

</script>

<style>
  .my-transition-enter-active {
    transition: all 0.2s ease-in;
  }

  .my-transition-leave-active {
    transition: all 0.1s ease-out;
  }

  .my-transition-enter-from {
    transform: translateY(30px);
    opacity: 0;
  }

  .my-transition-leave-to {
    opacity: 0;
    transform: translateY(-100px);
  }
</style>