<template>
  <div class="m-3">
    <div class="row" v-if="!zoom">
      <div class="col-8">
        <s-title title="Usuários" :breadcrumb="true" />
      </div>
    </div>
    <s-input-filter @index="handleIndex" @filter="filterAll" @clear="loadItems" name="filterWorkMeasurement"
      :filters="filterObject" />
    <div class="card card-body mx-2">
      <div class="row">
        <div class="col-12">
          <s-table v-model="actualPage" :headers="headers" :items="items" :totalPages="pages" v-if="!loader">
            <template v-slot:id="{ item }">
              <div class="text-center">
                {{ item.id }}
              </div>
            </template>
            <template v-slot:descricao="{ item }">
              {{ item.nome }}
            </template>
            <template v-slot:email="{ item }">
              {{ item.email }}
            </template>
            <template v-slot:sexo="{ item }">
              <div class="text-center">
                {{ item.sexo }}
              </div>
            </template>
            <template v-slot:dtNascimento="{ item }">
              <div class="text-center">
                {{ formatDate(item.dtNascimento) }}
              </div>
            </template>
            <template v-slot:tpUsuario="{ item }">
              <div class="text-center">
                {{ item.tpUsuario }}
              </div>
            </template>
            <template v-slot:status="{ item }">
              <div class="text-center">
                <s-chip :color="getStatusColor(item.status)" :text="translateStatusText(item.status)">
                  {{ item.status }}
                </s-chip>
              </div>
            </template>
            <template v-slot:actions="{ item }">
              <div class="text-center" v-if="!zoom">
                <!-- <i class="bi bi-lock-fill text-secondary px-1" style="cursor: pointer"
                  @click="showModalUpdatePassword(item)"></i> -->
                <i class="bi bi-pencil-fill text-secondary px-1" style="cursor: pointer" @click="edit(item.id)"></i>
                <i class="bi bi-trash-fill text-danger px-1" style="cursor: pointer" @click="removeConfirm(item)"></i>
              </div>
              <div class="text-center" v-if="zoom">
                <s-button label="Selecionar" color="primary" type="button" @click="emitSelectedItem(item)" />
              </div>
            </template>
          </s-table>
        </div>
        <div class="col-12" v-if="!loader">
          <s-button type="button" v-if="!zoom" label="Novo" color="primary" icon="plus-lg"
            @click="this.$router.push({ name: 'usuarioNew' })" />
        </div>
      </div>
      <!-- <TheLoader v-if="loader" /> -->
    </div>
    <div class="modal fade" ref="modalUpdatePassword" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
      aria-hidden="true">
      <form ref="form" @submit.prevent="submitForm">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
          <div class="modal-content">
            <div class="modal-header bg-primary">
              <span class="fs-5 text-white">Atualização de Senha</span>
            </div>
            <div class="modal-body text-dark">
              <div class="row">
                <div class="col-12">
                  <s-input-password v-model="currenntPassword" ref="currenntPassword" divClass="col-12"
                    label="Senha Atual" placeholder="••••••••" required />
                  <s-input-password v-model="object.senha" ref="password" divClass="col-12" label="Nova Senha"
                    placeholder="••••••••" required />
                  <s-input-password v-model="passwordConfirm" ref="passwordConfirm" divClass="col-12"
                    label="Confirmação Nova Senha" placeholder="••••••••" required />
                  <div class="row">
                    <s-label-required />
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer d-flex justify-content-between">
              <s-button type="submit" label="Salvar" color="primary" icon="check2" />
              <s-button type="button" label="Cancelar" color="outline-danger" icon="x-lg" data-bs-dismiss="modal"
                @click="clearData" />
            </div>
          </div>
        </div>
      </form>
    </div>
    <s-modal-delete ref="modalDelete" @confirm="remove" />
    <s-modal-notlogged ref="modalNotLogged" @confirm="logout" />
    <s-modal-error ref="modalError" modalTitle="Falha ao atualizar a senha!" :modalBody="modalErrorBody" />
  </div>
</template>

<script>
import { logout, validateForm } from '@/rule/functions.js'
import { get, remove, update, validateCurrentPassword, search } from '@/crud.js'

export default {
  name: 'usuario',

  props: {
    zoom: {
      type: Boolean,
      default: false,
    },
    valueZoom: String,
  },

  data: () => ({
    route: 'usuario',
    headers: [
      { title: 'Id', field: 'id' },
      { title: 'Descrição', field: 'descricao' },
      { title: 'Email', field: 'email' },
      { title: 'Sexo', field: 'sexo' },
      { title: 'Nascimento', field: 'dtNascimento' },
      { title: 'Tipo Usuário', field: 'tpUsuario' },
      { title: 'Status', field: 'status' },
      { title: 'Ações', field: 'actions' },
    ],
    items: [],
    object: {},
    passwordConfirm: null,
    currenntPassword: null,
    selectedItem: null,
    Modal: null,
    choosed: null,
    loader: false,
    modalDelete: null,
    modalNotLogged: null,
    modalUpdatePassword: null,
    modalError: null,
    modalErrorBody: null,
    pages: null,
    actualPage: 1,
    limit: 10,

    filterObject: [
      {
        label: 'Nome',
        ref: 'usuarName',
        route: 'usuario/pages/filter',
        subRoute: '',
        param: 'descricao',
        type: 'text',
        signal: '',
        operator: '',
        index: 1
      },
      /*{
        label: 'Gênero',b
        ref: 'bookGender',
        route: 'book',
        subRoute: 'by-gender',
        param: 'gender',
        type: 'text',
        signal: '',
        operator: 'LIKE',
        index: 2
      },
      {
        label: 'Autor',
        ref: 'bookAuthor',
        route: 'book',
        subRoute: 'by-author',
        param: 'author',
        type: 'text',
        signal: '',
        operator: 'LIKE',
        index: 3
      },*/
    ],

    filterOption: 1,
    filterParam: null,
  }),

  methods: {
    async loadItems(page = 1) {
      if (await this.$checkSession()) {
        const query = { params: { page: page, limit: this.limit } }
        let raw = []
        if (this.filterParam) {
          this.filterParam.params.page = page
          this.filterParam.params.limit = this.limit
          raw = await search(this.filterParam.route, this.filterParam.params)
        } else {
          raw = await get('usuario/pages', query)
        }
        this.items = raw.data
        this.pages = Math.ceil(raw.total / this.limit)
      } else {
        this.modalNotLogged.show()
      }
    },

    async submitForm() {
      if (await validateForm(this.$refs.form)) {
        this.save()
      }
    },

    async save() {
      if (this.currenntPassword) {
        const validCurrentUpdateObject = {
          id: this.object.id,
          senha: this.currenntPassword,
        }

        const result = await validateCurrentPassword('usuario/validatePassword', validCurrentUpdateObject)

        if (result.status == 204) {
          this.updatePassword()
        } else {
          this.modalErrorBody = 'A senha atual informada está incorreta. Por favor, verifique.'
          this.modalError.show()
        }
      } else {
        this.updatePassword()
      }
    },

    async edit(id) {
      const route = {
        name: 'usuarioUpdate',
        params: { id: id },
      }

      this.$router.push(route)
    },

    async remove() {
      if (await this.$checkSession()) {
        await remove(this.route, this.choosed.id)

        this.$store.dispatch('setShowToast', true)
        this.$store.dispatch('setToastMessage', 'Usuário inativado com sucesso !')
        this.loadItems()
      } else {
        this.modalNotLogged.show()
      }
    },

    removeConfirm(item) {
      this.choosed = item
      this.modalDelete.show()
    },

    emitSelectedItem(item) {
      this.$emit("selectedItem", item)
    },

    getStatusColor(status) {
      return status == 1 ? "bg-success" : "bg-danger";
    },

    translateStatusText(status) {
      return status == 1 ? "Ativo" : "Inativo";
    },

    async filterAll(event) {
      if (await this.$checkSession()) {
        this.filterParam = event
        this.loadItems(1)
      } else {
        this.modalNotLogged.show()
      }
    },

    handleIndex(event) {
      this.filterOption = event
    },

    formatDate(datetime) {
      if (datetime) {
        const date = new Date(datetime);
        return date.toLocaleDateString();
      }
    },

    showModalUpdatePassword(item) {
      this.modalUpdatePassword.show()
      this.object = item
    },

    async updatePassword() {
      if (this.object.senha && this.passwordConfirm) {
        if (this.object.senha !== this.passwordConfirm) {
          this.modalErrorBody = 'As senhas informadas não são iguais. Por favor, verifique.'
          this.modalError.show()
        } else {
          const validCurrentUpdateObject = {
            ...this.object
          }


          delete validateCurrentPassword.urlAvatar
          console.log(validCurrentUpdateObject)

          const result = await update(this.route, this.object.id, validCurrentUpdateObject)

          if (result.status) {
            if (result.status != '204') {
              this.modaErrorlBody = result.response.data
              this.modalError.show()
            } else {
              this.$store.dispatch('setShowToast', true)
              this.$store.dispatch('setToastMessage', 'Senha alterada com sucesso !')
            }
          } else {
            this.modalErrorBody = result.response.data
            this.modalError.show()
          }
        }

        this.clearData()
        this.modalUpdatePassword.hide()
      }
    },

    clearData() {
      this.object.password = null
      this.passwordConfirm = null
      this.selectedItem = null
      this.currenntPassword = null
    },

    // changeHeaders() {
    //   if (this.filterOption == 1) {
    //     this.headers = [
    //       { title: 'Nome', field: 'name' },
    //       { title: 'Gênero', field: 'gender' },
    //       { title: 'Autor', field: 'author' },
    //       { title: 'Páginas', field: 'quantityPages' },
    //       { title: 'Data Aquisição', field: 'dateAcquisition' },
    //       { title: 'Ações', field: 'actions' },
    //     ]
    //   }
    //  
    logout() {
      logout(this)
    },
  },

  async mounted() {
    this.modalDelete = new this.$Modal(this.$refs.modalDelete.$refs.modalPattern)
    this.modalNotLogged = new this.$Modal(this.$refs.modalNotLogged.$refs.modalPattern)
    this.modalError = new this.$Modal(this.$refs.modalError.$refs.modalPattern)
    this.modalUpdatePassword = new this.$Modal(this.$refs.modalUpdatePassword)

    await this.loadItems()
  },

  watch: {
    filterOption() {
      this.filterParam = null
      this.loadItems()
      this.changeHeaders()
    },
    actualPage() {
      this.loadItems(this.actualPage)
    },
  },
}
</script>

<style></style>
