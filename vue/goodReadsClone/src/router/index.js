import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import BookView from '../views/BookView.vue'
import AuthorView from '../views/AuthorView.vue'
import BooksView from '../views/BooksView.vue'
import AddBookView from '../views/AddBookView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
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
    path: '/add-book-view',
    name: 'add-book',
    component: AddBookView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
