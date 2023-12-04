import { createStore } from 'vuex'

export default createStore({
  state: {
    logged: false,
    user: null,
    showToast: false,
    toastMessage: null
  },

  getters: {},

  mutations: {
    setLogged(state, statusLogged) {
      state.logged = statusLogged
    },
    setUser(state, user) {
      state.user = user
    },
    setShowToast(state, showToast) {
      state.showToast = showToast
    },
    setToastMessage(state, toastMessage) {
      state.toastMessage = toastMessage
    },
  },

  actions: {
    setLogged({ commit }, statusLogged) {
      commit('setLogged', statusLogged)
    },
    setUser({ commit }, user) {
      commit('setUser', user)
    },
    setShowToast({ commit }, showToast) {
      commit('setShowToast', showToast)
    },
    setToastMessage({ commit }, toastMessage) {
      commit('setToastMessage', toastMessage)
    },
  },

  modules: {},
})
