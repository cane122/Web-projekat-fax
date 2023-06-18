import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EmployeesView from '../views/EmployeesView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import AddEmployeeView from '../views/AddEmployeeView.vue'
import UpdateEmployeeView from '../views/UpdateEmployeeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/employees',
    name: 'employees',
    component: EmployeesView
  },
  {
    path: '/employee',
    name: 'employee',
    component: EmployeeView
  },
  {
    path: '/add-employee',
    name: 'add-employee',
    component: AddEmployeeView
  },
  {
    path: '/update-employee',
    name: 'update-employee',
    component: UpdateEmployeeView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
