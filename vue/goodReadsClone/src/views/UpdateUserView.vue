<template>
    <div>
      <h2>Update User</h2>
      <form @submit.prevent="editUser">
        <div>
        <label for="name">Name:</label>
        <input type="text" id="name" v-model="userDTO.name" required>
        </div>
        <div>
        <label for="lastname">Lastname:</label>
        <input type="text" id="lastname" v-model="userDTO.lastname" required>
        </div>
        <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="userDTO.username" required>
        </div>
        <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="userDTO.email" required>
        </div>
        <div>
        <label for="password">Password:</label>
        <input type="text" id="password" v-model="userDTO.password" required>
        </div>
        <div>
        <label for="dateBirth">Date of Birth:</label>
        <input type="date" id="dateBirth" v-model="userDTO.dateBirth" required>
        </div>
        <div>
        <label for="image">Image:</label>
        <input type="text" id="image" v-model="userDTO.image" required>
        </div>
        <div>
        <label for="description">Description:</label>
        <input type="text" id="description" v-model="userDTO.description" required>
        </div>
        <div>
        <label for="confirmationEmail">Confirmation Email:</label>
        <input type="email" id="confirmationEmail" v-model="userDTO.confirmationEmail" required>
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
        userDTO: {
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
    async created() {
      try {
        const userId = this.$route.query.id;
        const response = await axios.get(`http://localhost:9090/api/users/${userId}`);
        const user = response.data;
        console.log("provera usera");
        for (const prop in user) {
          if (prop in this.userDTO) {
            this.userDTO[prop] = user[prop];
            console.log("emituje se" + prop + user[prop])
            this.$emit(prop,user[prop]);
          }
        }
        console.log(this.userDTO);
      } catch (error) {
        console.error('Error fetching user details', error);
        // Display the error message or perform any other necessary actions
      }
    },
    methods: {
   
    
    async editUser() {
      try {
        const response = await axios.put('http://localhost:9090/user/update', this.userDTO,{ withCredentials: true });
  
        if (response.status === 200) {
          console.log('User updated!');
          alert('User updated');
          this.$router.push('/user-details');
        } else {
          console.error('Error updating the user');
          alert('Error updating the user');
          console.log(response.data);
        }
      } catch (error) {
        console.error('Error updating the user', error);
        alert(error);
      }
    },
  },
  }
  </script>
  