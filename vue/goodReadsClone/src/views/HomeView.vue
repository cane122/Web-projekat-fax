<template>
  <div class="container">
    <h1>Welcome to the Goodreads Clone</h1>

    <div class="button-group">
      <router-link to="/books" class="btn">Show Books</router-link>
      <router-link to="/login" class="btn">Login</router-link>
      <router-link to="/unsigned-users" class="btn">Search users</router-link>
      <router-link v-if="author" to="/add-book" class="btn">Add Book</router-link>
      <router-link to="/register" class="btn">Register</router-link>
      <router-link to="/search-book" class="btn">Search</router-link>
      <router-link to="/genres" class="btn">Genres</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      author: localStorage.getItem('user_type') === 'Author' // Replace this with your logic to determine if the user is an author or not
    };
  },
};
</script>

