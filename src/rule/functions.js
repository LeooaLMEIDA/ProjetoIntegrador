import { nextTick } from 'vue'
import store from '@/store/index'

export async function validateForm(ref) {
  if (!ref) { return false }

  const elementInput = ref.getElementsByTagName('input')
  const elementSelect = ref.getElementsByTagName('select')
  let valid = true
  let countInvalid = 0

  for (let i = 0; i < elementInput.length; i++) {
    const element = elementInput[i]
    element.focus()
    element.blur()
    await nextTick()

    if (element.className.includes('is-invalid') || element.className.includes('dp-custom-input')) {
      countInvalid++
    }
  }

  for (let i = 0; i < elementSelect.length; i++) {
    const element = elementSelect[i]
    element.focus()
    element.blur()
    await nextTick()

    if (element.className.includes('is-invalid')) {
      countInvalid++
    }
  }

  if (countInvalid > 0) { valid = false }
  return valid
}

export function getRouteName(route) {
  let theRoute = {
    usuario: {
      parent: 'Administração',
      name: 'Usuários',
      path: 'usuario',
      childrenName: 'Cadastro de Usuário',
      childrenPath: 'usuarioNew',
      UpdateName: 'Edição de Usuário',
      UpdatePath: 'usuarioUpdate',
    },
    aparelho: {
      parent: 'Cadastros',
      name: 'Aparelhos',
      path: 'aparelho',
      childrenName: 'Cadastro de Aparelho',
      childrenPath: 'aparelhoNew',
      UpdateName: 'Edição de Aparelho',
      UpdatePath: 'aparelhoUpdate',
    },
    exercicio: {
      parent: 'Cadastros',
      name: 'Exercícios',
      path: 'exercicio',
      childrenName: 'Cadastro de Exercício',
      childrenPath: 'exercicioNew',
      UpdateName: 'Edição de Exercício',
      UpdatePath: 'exercicioUpdate',
    },
    treino: {
      parent: 'Cadastros',
      name: 'Treinos',
      path: 'treinos',
      childrenName: 'Cadastro de Treinos',
      childrenPath: 'treinosNew',
      UpdateName: 'Edição de Treino',
      UpdatePath: 'treinoUpdate',
    },
    avaliacao: {
      parent: 'Cadastros',
      name: 'Avalições',
      path: 'avaliacoes',
      childrenName: 'Cadastro de Avaliações',
      childrenPath: 'avaliacoesNew',
      UpdateName: 'Edição de Avaliação',
      UpdatePath: 'avaliacaoUpdate',
    },
    myProfile: {
      parent: '',
      name: '',
      path: '',
      childrenName: '',
      childrenPath: '',
      UpdateName: '',
      UpdatePath: '',
    }
  }

  if (route in theRoute) { return theRoute[route] }
  else if (route.split('New')[0] in theRoute) { return theRoute[route.split('New')[0]] }
  else { return theRoute[route.split('Update')[0]] }
}

export function logout(vueInstance) {
  vueInstance.$store.dispatch('setLogged', false)
  vueInstance.$router.push({ name: 'login' })
}

export function cleanObject(object) {
  if (object.status && !object.password) {
    delete object.id
    delete object.createdAt
    delete object.deletedAt
    delete object.updatedAt
  }

  else {
    delete object.id
    delete object.status
    delete object.createdAt
    delete object.deletedAt
    delete object.updatedAt
  }
}

// let firstTry = false
// import { get } from '@/crud.js'
// export async function checkSession() {

//   let rawUser = null

//   if (!firstTry) {
//     rawUser = await get('me')
//     firstTry = true
//   }

//   else {
//     const state = JSON.parse(JSON.stringify(store.state))
//     rawUser = state.user
//   }

//   if (!rawUser || rawUser == '') { return false }
//   else {
//     store.dispatch('setLogged', true)
//     store.dispatch('setUser', rawUser)
//     return true
//   }
// }

export async function checkSession() {
  let rawUser = null
  const storedUserData = sessionStorage.getItem('userData');

  if (storedUserData) {
    rawUser = JSON.parse(storedUserData);
  }

  if (!rawUser || rawUser === '') {
    return false;
  } else {
    store.dispatch('setLogged', true);
    store.dispatch('setUser', rawUser);
    return true;
  }
}