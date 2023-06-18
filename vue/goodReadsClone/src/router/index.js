import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EmployeesView from '../views/EmployeesView.vue'
import EmployeeView from '../views/EmployeeView.vue'
import AddEmployeeView from '../views/AddEmployeeView.vue'
import UpdateEmployeeView from '../views/UpdateEmployeeView.vue'
import LoginView from '../views/LoginView'
import BookView from '../views/BookView.vue'
import AuthorView from '../views/AuthorView.vue'
import BooksView from '../views/BooksView.vue'


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
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/book',
    name: 'book',
    component: BookView
  },
  {
    path: '/books',
    name: 'books',
    component: BooksView
  },
  {
    path: '/author',
    name: 'author',
    component: AuthorView
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
