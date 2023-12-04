<template>
  <div class="d-flex flex-column justify-content-between">
    <div class="d-flex flex-column">
      <ul class="nav nav-pills flex-column">
        <li v-for="(menuItem, i) in menuList" :key="i">
          <a href="#" :class="menuItemClasses(menuItem, menuItem.code)" data-bs-toggle="collapse"
            :aria-expanded="menuItem.status" @click.prevent="navItemCollapse(i), goTo(menuItem.code)">
            <i :class="menuItemIcon(menuItem)"></i>
            <span class="sidebar-item-title">{{ menuItem.title }}</span>
            <span class="ms-auto"></span>
          </a>
          <div v-if="menuItem.children.length > 0" :class="divSubItemClasses(menuItem)">
            <ul class="btn-toggle-nav list-unstyled fw-normal p-1 ms-3">
              <li v-for="(subItem, j) in menuItem.children" :key="j">
                <a href="#" :class="menuSubItemClasses(subItem.code, subItem.child)" @click.prevent="goTo(subItem.code)">
                  <i :class="menuSubItemIcon(subItem.code, subItem.child, subItem.icon)"></i>
                  {{ subItem.title }}
                </a>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  data() {
    return {
      subMenu: null,
    }
  },

  computed: {
    ...mapState(['logged']),
    route() {
      return this.$route.name
    },
  },

  props: {
    menuList: Array,
  },

  methods: {
    navItemCollapse(index) {
      this.menuItems = this.menuList.map((item, i) => {
        item.status = !item.status

        if (i !== index) item.status = false

        return item
      })
    },

    divSubItemClasses(menuItem) {
      let classes = 'collapse'

      if (menuItem.status) classes += 'show'
      return classes
    },

    menuItemClasses(menuItem, code) {
      let classes = 'item-firstLevel nav-link text-white hstack gap-1 '

      if (code === this.route) {
        this.subMenu = null
        classes += 'active'
      } else {
        classes += ''
      }

      if (menuItem.children.length > 0) {
        menuItem.children.find(e => e.code == this.subMenu) ? classes += 'btn-toggle active' : classes += ''
      }

      return classes
    },

    menuSubItemClasses(code, child) {
      let classes = 'nav-link text-white hstack gap-3 '

      if (code === this.route) {
        this.subMenu = code
        classes += 'subItemActive'
      } else if (this.route) {
        if (this.route.includes('New') && (this.route.split('New')[0] == code || this.route.split('New')[0] == child)) {
          this.subMenu = code
          classes += 'subItemActive'
        }

        if (this.route.includes('Update') && (this.route.split('Update')[0] == code || this.route.split('Update')[0] == child)) {
          this.subMenu = code
          classes += 'subItemActive'
        }

        if (this.route.includes('Children') && (this.route.split('Children')[0] == child || this.route.split('Children')[0] == code)) {
          this.subMenu = code
          classes += 'subItemActive'
        }
      }
      else {
        classes += ''
      }

      return classes
    },

    menuSubItemIcon(code, child, icon) {
      if (code === this.route) {
        this.subMenu = code
        icon = 'bi bi-circle-fill'
      } else if (this.route) {
        if (this.route.includes('New') && (this.route.split('New')[0] == code || this.route.split('New')[0] == child)) {
          this.subMenu = code
          icon = 'bi bi-circle-fill'
        }

        if (this.route.includes('Update') && (this.route.split('Update')[0] == code || this.route.split('Update')[0] == child)) {
          this.subMenu = code
          icon = 'bi bi-circle-fill'
        }

        if (this.route.includes('Children') && (this.route.split('Children')[0] == child || this.route.split('Children')[0] == code)) {
          this.subMenu = code
          icon = 'bi bi-circle-fill'
        }
      }
      else {
        icon += ''
      }

      return icon
    },

    menuItemIcon(menuItem) {
      let classes = `${menuItem.icon} me-2`
      return classes
    },

    goTo(code) {
      this.$router.push({ name: code })
    },
  },

  mounted() {
    this.menuSubItemClasses(this.menu)
    this.menuSubItemClasses(this.menu)
  }
}
</script>

<style>
.subItemActive {
  background-color: #acacac !important;
}
</style>
