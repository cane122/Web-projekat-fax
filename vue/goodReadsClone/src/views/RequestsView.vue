<template>
    <div>
      <h1>Request View</h1>
      <ul>
        <li v-for="request in requests" :key="request.id">
          <span>{{ request.email }}</span>
          <span>{{ request.date }}</span>
          <button @click="acceptRequest(request.id)">Accept</button>
          <button @click="denyRequest(request.id)">Deny</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        requests: []
      };
    },
    mounted() {
      this.fetchRequests();
    },
    methods: {
      fetchRequests() {
        axios.get('/get/requests') // Replace with your backend API endpoint for fetching requests
          .then(response => {
            this.requests = response.data;
          })
          .catch(error => {
            console.error(error);
          });
      },
      acceptRequest(requestId) {
        axios.post(`/accrequest/${requestId}`) // Replace with your backend API endpoint for accepting a request
          .then(response => {
            console.log("Prihvacen zahtev!")
          })
          .catch(error => {
            console.error(error);
          });
      },
      denyRequest(requestId) {
        axios.post(`/denyrequest/${requestId}`) // Replace with your backend API endpoint for denying a request
          .then(response => {
            console.log("Zahtev uspesno odbijen")
          })
          .catch(error => {
            console.error(error);
          });
      }
    }
  };
  </script>
  