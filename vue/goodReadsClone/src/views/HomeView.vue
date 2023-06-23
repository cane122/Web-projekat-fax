<template>
  <div class="container">
    <h1>Welcome to the Goodreads Clone</h1>

    <div class="button-group">
      <!-- Existing router links -->
      <router-link to="/books" class="btn">Show Books</router-link>
      <router-link to="/unsigned-users" class="btn">Search users</router-link>
      <router-link v-if="author" to="/add-book" class="btn">Add Book</router-link>
      <router-link to="/search-book" class="btn">Search</router-link>
      <router-link to="/genres" class="btn">Genres</router-link>
      <router-link to="/shelves" class="btn">Shelves</router-link>
      
    </div>
    <br><br>
      <!-- Logout button -->
      <router-link to="/login" class="btn">Login</router-link>
      <router-link to="/register" class="btn">Register</router-link>
      <button class="btn" @click="logout">Logout</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "HomeView",
  data() {
    return {
      author: localStorage.getItem('user_type') === 'Author'
    };
  },
  methods: {
    logout() {
      axios.get('http://localhost:9090/logout',{withCredentials:true}) // Make a GET request to the "/logout" endpoint
        .then(() => {
          // Handle successful logout
          localStorage.clear();
          this.$router.push('/login');
        })
        .catch(error => {
          console.error('Logout error:', error);
        });
    }
  }
};
</script>
