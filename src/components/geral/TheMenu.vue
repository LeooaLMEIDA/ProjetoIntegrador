<template>
  <nav class="navbar navbar-expand-lg bg-primary d-flex justify-content-between" v-if="logged">
    <div>
      <i class="bi bi-list icon_action ps-3" style="filter: grayscale(0) brightness(100)" @click="toggleSidebar"></i>
      <img src="@/assets/Logo4.png" class="ps-3" style="width: 200px;" />
    </div>
    <div>
      <i class="bi bi-person-fill-gear icon" @click="$router.push({ name: 'myProfile' })"></i>
      <span class="px-2 me-1">{{ user.name }}</span>
      <s-button type="button" label="Sair" size="btn-sm me-3" color="danger" icon="bi bi-box-arrow-in-right me-2"
        @click="logout" />
    </div>
  </nav>
  <div class="wrapper">
    <nav ref="sidebar" class="sidebar bg-primary" v-if="logged">
      <s-sidemenu :menuList="menuList" class="px-3" />
    </nav>
    <div id="content" class="content text-bg-light p-0">
      <router-view />
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import { baseApiUrl } from '@/global'
import { mapState } from 'vuex'
import axios from 'axios'

export default {
  setup() {
    const sidebar = ref(null);

    const toggleSidebar = () => {
      sidebar.value.classList.toggle('active');
    }

    return { sidebar, toggleSidebar }
  },

  computed: {
    ...mapState(['logged', 'user']),
  },

  data: () => ({
    menuList: [
      {
        code: null,
        title: 'Cadastros',
        status: false,
        icon: 'bi bi-plus-circle-fill',
        children: [
          {
            code: "aparelho",
            title: "Aparelhos",
            status: false,
            icon: "bi bi-circle",
            children: [],
          },
          {
            code: "exercicio",
            title: "Exercícios",
            status: false,
            icon: "bi bi-circle",
            children: [],
          },
          {
            code: "treino",
            title: "Treinos",
            status: false,
            icon: "bi bi-circle",
            children: [],
          },
          {
            code: "avaliacao",
            title: "Avaliação",
            status: false,
            icon: "bi bi-circle",
            children: [],
          },
        ],
      },
      {
        code: null,
        title: 'Administração',
        status: false,
        icon: 'bi bi-gear-fill',
        children: [
          {
            code: 'usuario',
            title: 'Usuários',
            icon: 'bi bi-circle',
            child: 'userGroup',
            status: false,
          },
        ],
      },
      // {
      //   code: 'myProfile',
      //   title: 'Meu Perfil',
      //   status: false,
      //   icon: 'bi-person-fill',
      //   children: [],
      // },
    ],
  }),

  methods: {
    logout() {
      // const url = `${baseApiUrl}/logout`;
      axios.defaults.withCredentials = true;
      sessionStorage.setItem('userData', '');

      location.reload();

      // this.$router.push({ name: "login" });

      // const result = axios
      //   .post(url)
      //   .then((res) => {
      //     this.$store.dispatch("setLogged", false);
      //     this.$router.push({ name: "login" });
      //   })
      //   .catch((err) => {
      //     return err;
      //   });

      // return result;
    },
  },
}
</script>

<style>
.wrapper {
  display: flex;
  width: 100%;
  height: 100%;
  align-items: stretch;
}

.sidebar {
  min-width: 250px;
  max-width: 250px;
  transition: all 0.3s;
  position: relative;
}

.sidebar .sidebarBottom {
  position: fixed;
  bottom: 0;
  left: 0;
  max-height: 10%;
  width: 250px;
  padding: 10px;
}

.sidebar.active {
  margin-left: -250px;
}

.content {
  width: 100%;
  height: 100%;
  min-height: 95vh;
  transition: all 0.3s;
}

.navbar {
  height: 5vh;
}

.item-firstLevel:hover {
  color: #000;
  background-color: #8dc4f1;
}

.btn-toggle-nav a:hover,
.btn-toggle-nav a:focus {
  background-color: #acacac;
}

.btn-toggle::after {
  width: 1.25em;
  line-height: 0;
  content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%280,0,0,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
  transition: transform 0.35s ease;
  transform-origin: 0.5em 50%;
}

.btn-toggle[aria-expanded='true']::after {
  transform: rotate(450deg);
}

@media (max-width: 768px) {
  .sidebar {
    margin-left: -250px;
  }

  .sidebar.active {
    margin-left: 0;
  }

  .sidebarCollapse span {
    display: none;
  }
}

@media screen and (width < 1550px) {
  .content {
    min-height: 90vh;
  }

  .navbar {
    height: 10vh;
  }

  .icon {
    font-size: 18px;
    cursor: pointer;
  }
}
</style>