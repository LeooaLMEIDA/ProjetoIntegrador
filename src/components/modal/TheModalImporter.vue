<template>
  <div class="modal fade" ref="modalPattern" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable .modal-fullscreen-md-down">
      <div class="modal-content">
        <div class="modal-header bg-primary">
          <span class="fs-5 text-white">{{ title }}</span>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body text-dark">
          <div class="m-3">
            <div class="mx-2">
                <slot></slot>
              <TheModalError
              ref="modalError"
              modalTitle="Falha ao importar !"
              :modalBody="modalBody"
            />
            <TheModalNotLogged
              ref="modalNotLogged"
              @confirm="logout"
            />
            <TheLoader v-if="loader"  text="Processando os dados da importação, aguarde..."/>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <TheButton
            :disabled="btnDisabled"
            type="submit"
            label="Enviar"
            color="primary"
            icon="check2"
            @click="emitConfirm"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { logout } from '@/rule/functions'

export default {
  name: 'Importer',

  props: {
    loader: false,
    title: {
      type: String,
      default: 'Importar Arquivo'
    },
    btnDisabled: {
      type: Boolean,
      default: false
    }
  },

  methods: {
    emitConfirm() {
      this.$emit('confirm')
    },

    logout() { logout(this) }
  },

  data: () => ({
    modalError: null,
    modalNotLogged: null,
    modalBody: null,
  }),

  mounted() {
    this.modalNotLogged = new this.$Modal(this.$refs.modalNotLogged.$refs.modalPattern)
    this.modalError = new this.$Modal(this.$refs.modalError.$refs.modalPattern)
  }
}
</script>

<style></style>