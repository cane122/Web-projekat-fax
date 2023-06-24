<template>
    <div>
      <h2>Create Author</h2>
      <form @submit.prevent="editUser">
        <div>
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="authorDTO.name" required>
        </div>
        <div>
        <label for="lastname">Lastname:</label>
        <input type="text" id="lastname" v-model="authorDTO.lastname" required>
        </div>
        <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="authorDTO.username" required>
        </div>
        <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="authorDTO.email" required>
        </div>
        <div>
        <label for="password">Password:</label>
        <input type="text" id="password" v-model="authorDTO.password" required>
        </div>
        <div>
        <label for="dateBirth">Date of Birth:</label>
        <input type="date" id="dateBirth" v-model="authorDTO.dateBirth" required>
        </div>
        <div>
        <label for="image">Image:</label>
        <input type="text" id="image" v-model="authorDTO.image" required>
        </div>
        <div>
        <label for="description">Description:</label>
        <input type="text" id="description" v-model="authorDTO.description" required>
        </div>
        <div>
        <label for="active">Confirmation Email:</label>
        <input type="text" id="active" v-model="authorDTO.confirmationEmail" required>
        </div>
        <button type="submit">update user</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        authorDTO: {
          name: '',
          lastname: '',
          username: '',
          email: '',
          password: '',
          dateBirth: '1990-01-01', //default
          image: '',
          description: '',
          confirmationEmail: ''
        },
      };
    },
    mounted() {
    axios.get('http://localhost:9090/admin/createauthor' , this.authorDTO,{ withCredentials: true })
      .then(response => {
        console.log("Success, data: ", response.data);
      })
      .catch(error => {
        console.error(error);
      });

  },
  }
  </script>
  