<template>
  <div class="row vh-100 vw-100" v-if="!logged">
    <s-login-form />
    <s-login-content />
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState(['logged']),
  },
};
</script>

<style></style>