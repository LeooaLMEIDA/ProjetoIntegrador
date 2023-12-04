import 'bootstrap/dist/css/bootstrap.css'
import { createApp } from 'vue'
import App from './App.vue'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import router from './router'
import * as Filtros from './plugins/filter'

import store from './store'
import axios from 'axios'
import VueTheMask from 'vue-the-mask'
import money from 'v-money'
import { Modal } from 'bootstrap'
import { checkSession } from './rule/functions'

// button
import TheButton from '@/components/button/TheButton.vue'
// card
import TheCard from '@/components/card/TheCard.vue'
import TheCardKPI from '@/components/card/TheCardKPI.vue'
// efeito
import TheLoader from '@/components/efeito/TheLoader.vue'
import TheToolTip from '@/components/efeito/TheToolTip.vue'
import TheTransition from '@/components/efeito/TheTransition.vue'
// geral
import TheBreadcrumb from '@/components/geral/TheBreadcrumb.vue'
import TheChip from '@/components/geral/TheChip.vue'
import TheLabel from '@/components/geral/TheLabel.vue'
import TheLabelRequired from '@/components/geral/TheLabelRequired.vue'
import TheMenu from '@/components/geral/TheMenu.vue'
import TheSideMenu from '@/components/geral/TheSideMenu.vue'
import TheStatusCircle from '@/components/geral/TheStatusCircle.vue'
import TheTitle from '@/components/geral/TheTitle.vue'
// grafico
import TheDoughnutChart from '@/components/grafico/TheDoughnutChart.vue'
// input
import TheInputCheck from '@/components/input/TheInputCheck.vue'
import TheInputDate from '@/components/input/TheInputDate.vue'
import TheInputDateRange from '@/components/input/TheInputDateRange.vue'
import TheInputEmail from '@/components/input/TheInputEmail.vue'
import TheInputFile from '@/components/input/TheInputFile.vue'
import TheInputFilter from '@/components/input/TheInputFilter.vue'
import TheInputPassword from '@/components/input/TheInputPassword.vue'
import TheInputRadio from '@/components/input/TheInputRadio.vue'
import TheInputText from '@/components/input/TheInputText.vue'
import TheInputTextArea from '@/components/input/TheInputTextArea.vue'
import TheInputZoom from '@/components/input/TheInputZoom.vue'
// login
import TheLoginContent from '@/components/login/LoginContent.vue'
import TheLoginForm from '@/components/login/LoginForm.vue'
// modal
import TheModalDelete from '@/components/modal/TheModalDelete.vue'
import TheModalError from '@/components/modal/TheModalError.vue'
import TheModalExporter from '@/components/modal/TheModalExporter.vue'
import TheModalFilter from '@/components/modal/TheModalFilter.vue'
import TheModalImporter from '@/components/modal/TheModalImporter.vue'
import TheModalNotLogged from '@/components/modal/TheModalNotLogged.vue'
import TheModalZoom from '@/components/modal/TheModalZoom.vue'
// select
import TheSelect from '@/components/select/TheSelect.vue'
import TheSelectMultiple from '@/components/select/TheSelectMultiple.vue'
// table
import TheTable from '@/components/table/TheTable.vue'
import TheTableReport from '@/components/table/TheTableReport.vue'
// toast
import TheToastSuccess from '@/components/toast/TheToastSuccess.vue'
import TheToastInfo from '@/components/toast/TheToastInfo.vue'


import { cleanObject } from '@/rule/functions'

axios.defaults.withCredentials = true

const app = createApp(App)

app.config.globalProperties.$filters = {}

Object.keys(Filtros).forEach((filtro) => {
  app.config.globalProperties.$filters[filtro] = Filtros[filtro]
})

app.use(VueTheMask)
app.use(money)
app.use(store)
app
  .use(router)
  .component('s-datepicker', VueDatePicker)
  // button
  .component('s-button', TheButton)
  // card
  .component('s-card', TheCard)
  .component('s-card-kpi', TheCardKPI)
  // efeito
  .component('s-loader', TheLoader)
  .component('s-tooltip', TheToolTip)
  .component('s-transition', TheTransition)
  // geral
  .component('s-breadcumb', TheBreadcrumb)
  .component('s-chip', TheChip)
  .component('s-label', TheLabel)
  .component('s-label-required', TheLabelRequired)
  .component('s-menu', TheMenu)
  .component('s-sidemenu', TheSideMenu)
  .component('s-status-circle', TheStatusCircle)
  .component('s-title', TheTitle)
  // grafico
  .component('s-chart-doughnut', TheDoughnutChart)
  // input
  .component('s-input-check', TheInputCheck)
  .component('s-input-date', TheInputDate)
  .component('s-input-daterange', TheInputDateRange)
  .component('s-input-email', TheInputEmail)
  .component('s-input-file', TheInputFile)
  .component('s-input-filter', TheInputFilter)
  .component('s-input-password', TheInputPassword)
  .component('s-input-radio', TheInputRadio)
  .component('s-input-text', TheInputText)
  .component('s-input-textarea', TheInputTextArea)
  .component('s-input-zoom', TheInputZoom)
  // login
  .component('s-login-content', TheLoginContent)
  .component('s-login-form', TheLoginForm)
  // modal
  .component('s-modal-delete', TheModalDelete)
  .component('s-modal-error', TheModalError)
  .component('s-modal-exporter', TheModalExporter)
  .component('s-modal-filter', TheModalFilter)
  .component('s-modal-importer', TheModalImporter)
  .component('s-modal-notlogged', TheModalNotLogged)
  .component('s-modal-zoom', TheModalZoom)
  // select
  .component('s-select', TheSelect)
  .component('s-select-multiple', TheSelectMultiple)
  // table
  .component('s-table', TheTable)
  .component('s-table-report', TheTableReport)
  // toast
  .component('s-toast-success', TheToastSuccess)
  .component('s-toast-info', TheToastInfo)

app.config.globalProperties.$cleanObject = cleanObject
app.config.globalProperties.$Modal = Modal
app.config.globalProperties.$checkSession = checkSession

app.mount('#app')

import 'bootstrap/dist/js/bootstrap.js'
import 'bootstrap-icons/font/bootstrap-icons.css'
