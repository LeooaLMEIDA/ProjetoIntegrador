import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  /* ########## USUARIO ########## */
  {
    path: '/administracao/usuario',
    name: 'usuario',
    component: () => import('@/views/administracao/usuario/Usuario.vue')
  },
  {
    path: '/administracao/usuario/cadastro/novo',
    name: 'usuarioNew',
    component: () => import('@/views/administracao/usuario/UsuarioNovo.vue')
  },
  {
    path: '/administracao/usuario/:id/editar',
    name: 'usuarioUpdate',
    component: () => import('@/views/administracao/usuario/UsuarioNovo.vue')
  },
  /* ########## APARELHO ########## */
  {
    path: '/cadastros/aparelho',
    name: 'aparelho',
    component: () => import('@/views/cadastros/aparelho/Aparelho.vue')
  },
  {
    path: '/cadastros/aparelho/cadastro/novo',
    name: 'aparelhoNew',
    component: () => import('@/views/cadastros/aparelho/AparelhoNovo.vue')
  },
  {
    path: '/cadastros/aparelho/:id/editar',
    name: 'aparelhoUpdate',
    component: () => import('@/views/cadastros/aparelho/AparelhoNovo.vue')
  },
  /* ########## EXERCÍCIO ########## */
  {
    path: '/cadastros/exercicio',
    name: 'exercicio',
    component: () => import('@/views/cadastros/exercicio/Exercicio.vue')
  },
  {
    path: '/cadastros/exercicio/cadastro/novo',
    name: 'exercicioNew',
    component: () => import('@/views/cadastros/exercicio/ExercicioNovo.vue')
  },
  {
    path: '/cadastros/exercicio/:id/editar',
    name: 'exercicioUpdate',
    component: () => import('@/views/cadastros/exercicio/ExercicioNovo.vue')
  },
  /* ########## TREINO ########## */
  {
    path: '/cadastros/treino',
    name: 'treino',
    component: () => import('@/views/cadastros/treino/Treino.vue')
  },
  {
    path: '/cadastros/treino/cadastro/novo',
    name: 'treinoNew',
    component: () => import('@/views/cadastros/treino/TreinoNovo.vue')
  },
  {
    path: '/cadastros/treino/:id/editar',
    name: 'treinoUpdate',
    component: () => import('@/views/cadastros/treino/TreinoNovo.vue')
  },
  
  /* ########## AVALIAÇÃO ########## */
  {
    path: '/cadastros/avaliacao',
    name: 'avaliacao',
    component: () => import('@/views/cadastros/avaliacao/Avaliacao.vue')
  },
  {
    path: '/cadastros/avaliacao/cadastro/novo',
    name: 'avaliacaoNew',
    component: () => import('@/views/cadastros/avaliacao/AvaliacaoNovo.vue')
  },
  {
    path: '/cadastros/avaliacao/:id/editar',
    name: 'avaliacaoUpdate',
    component: () => import('@/views/cadastros/avaliacao/AvaliacaoNovo.vue')
  },
  /* ########## PERFIL ########## */
  // {
  //   path: '/meu_perfil',
  //   name: 'myProfile',
  //   component: () => import('@/views/Perfil.vue')
  // },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
