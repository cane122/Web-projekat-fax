<template>
  <h1>User Registration</h1>
  <form @submit.prevent="registerUser">
    <div>
      <label for="name">Name:</label>
      <input type="text" id="name" v-model="user.name" required>
    </div>
    <div>
      <label for="lastname">Lastname:</label>
      <input type="text" id="lastname" v-model="user.lastname" required>
    </div>
    <div>
      <label for="username">Username:</label>
      <input type="text" id="username" v-model="user.username" required>
    </div>
    <div>
      <label for="password">Password:</label>
      <input type="password" id="password" v-model="user.password" required>
    </div>
    <div>
      <label for="email">Email:</label>
      <input type="email" id="email" v-model="user.email" required>
    </div>
    <button type="submit">Register</button>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  name: "RegisterView",
  data() {
    return {
      user: {
        name: "",
        lastname: "",
        username: "",
        password: "",
        email: ""
      }
    };
  },
  methods: {
    registerUser() {
      console.log(this.user);

      axios.post('http://localhost:9090/signup', this.user, { withCredentials: true })
        .then(response => {
          const loggedInMessage = "User successfully registered and logged in";
          alert(loggedInMessage);

          localStorage.setItem('user_type', response.data);

          // Redirect to a specific route
          this.$router.push('/');
        })
        .catch(error => {
          if (error.response && error.response.data) {
            const errorMessage = error.response.data;
            alert(errorMessage);
            // Display error message or perform any necessary actions
          } else {
            alert("An error occurred during login.");
          }
        });
    }

  }
};
</script>