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

          axios.post('/signup', this.user,{withCredentials:true})
            .then(response => {
              console.log(response.data);
              // Reset the form after successful registration
              this.user = {
                name: '',
                lastname: '',
                username: '',
                password: '',
                email: ''
              };
            })
            .catch(error => {
              console.error(error);
              // Handle any error that occurred during registration
            });
        }

        }
};
</script>