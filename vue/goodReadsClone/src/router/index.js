import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import BookView from '../views/BookView.vue'
import AuthorView from '../views/AuthorView.vue'
import BooksView from '../views/BooksView.vue'
import AddBookView from '../views/AddBookView.vue'
import UpdateBookView from '../views/UpdateBookView.vue'
import UnsignedUsersView from '../views/UnsignedUsers.vue'
import RegisterView from '../views/RegisterView.vue'
import SearchBookView from '../views/SearchBookView.vue'
import GenresView from '../views/GenresView.vue'
import ShelvesView from '../views/Shelves.vue'
import UserDetailsView from '../views/UserDetailsView.vue'
import UpdateUserView from '../views/UpdateUserView.vue'
import NewRequestView from '../views/NewRequestView.vue'
import RequestsView from '../views/RequestsView.vue'
// dodati request i new request

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
    path: '/update-book',
    name: 'update-book',
    component: UpdateBookView
  },
  {
    path: '/add-book',
    name: 'add-book',
    component: AddBookView
  },
  {
    path: '/unsigned-users',
    name: 'unsigned-users',
    component: UnsignedUsersView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/search-book',
    name: 'search-book',
    component: SearchBookView
  },
  {
    path: '/genres',
    name: 'genres',
    component: GenresView
  },
  {
    path: '/shelves',
    name: 'shelves',
    component: ShelvesView
  },
  {
    path: '/user-details',
    name: 'user-details',
    component: UserDetailsView
  },
  {
    path: '/update-user',
    name: 'update-user',
    component: UpdateUserView
  },
  {
    path: '/requests',
    name: 'requests',
    component: RequestsView
  },
  {
    path: '/new-req',
    name: 'new-req',
    component: NewRequestView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
