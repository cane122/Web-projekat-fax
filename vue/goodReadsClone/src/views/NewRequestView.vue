<template>
    <div>
      <h1>New Request</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="formData.email" required>
        </div>
        <div>
          <label for="phone">Phone Number:</label>
          <input type="text" id="phone" v-model="formData.phone" required>
        </div>
        <div>
          <label for="message">Message:</label>
          <textarea id="message" v-model="formData.message" required></textarea>
        </div>
        <div>
          <label for="date">Date:</label>
          <input type="date" id="date" v-model="formData.date" required>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        formData: {
          email: '',
          phone: '',
          message: '',
          date: ''
        }
      };
    },
    methods: {
      submitForm() {
        axios.post('http://localhost:9090/postRequest/2', this.formData) // Replace with your backend API endpoint for creating requests
          .then(response => {
            alert("Zahtev poslat na uvid!");
            console.log(response)
          })
          .catch(error => {
            console.error(error);
          });
      }
    }
  };
  </script>
  