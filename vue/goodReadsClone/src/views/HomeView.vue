<template>
  <div class="container">
    <h1>Welcome to the Goodreads Clone</h1>

    <div class="button-group">
      <router-link to="/books" class="btn">Show Books</router-link>
      <router-link to="/login" class="btn">Login</router-link>
      <router-link v-if="author" to="/add-book" class="btn">Add Book</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      author: true // Replace this with your logic to determine if the user is an author or not
    };
  },
};
</script>


<style>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.btn:hover {
  background-color: #45a049;
}
</style>
