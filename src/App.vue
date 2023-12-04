<template>
  <div>
    <s-menu />
    <s-toast-success ref="toastSuccess" :message="toastMessage" />
  </div>
</template>

<script>
import { mapState } from "vuex"
import bootstrap from "bootstrap/dist/js/bootstrap.bundle.min.js"

export default {
  computed: {
    ...mapState(["logged", "showToast", "toastMessage", "user"]),
  },

  methods: {
    logout() {
      new bootstrap.Toast(this.$refs.toastSuccess.$refs.toastPattern).show()
      this.$store.dispatch("setShowToast", false);
    },
  },

  async beforeMount() {
    if (!(await this.$checkSession())) {
      this.$router.push({ name: "login" })
    }
  },

  async mounted() {
    if (!(await this.$checkSession())) {
      this.$router.push({ name: "login" })
    }
  },

  watch: {
    showToast() {
      if (this.showToast) {
        this.logout()
      }
    },
  },
};
</script>

<style lang="scss">
@import "@/scss/app.scss";
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

* {
  font-family: "Poppins", sans-serif !important;
}

.icon_action {
  cursor: pointer;
}
</style>
