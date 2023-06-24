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
        axios.get('http://localhost:9090/get/requests', {withCredentials: true}) // Replace with your backend API endpoint for fetching requests
          .then(response => {
            this.requests = response.data;
            console.log(this.requests)
          })
          .catch(error => {
            console.error(error);
          });
      },
      acceptRequest(requestId) {
        axios.patch(`http://localhost:9090/accrequest/${requestId}`, {withCredentials: true}) // Replace with your backend API endpoint for accepting a request
          .then(response => {
            alert("Prihvacen zahtev!")
            console.log(response)
          })
          .catch(error => {
            console.error(error);
          });
      },
      denyRequest(requestId) {
        axios.patch(`http://localhost:9090/denyrequest/${requestId}`, {withCredentials: true}) // Replace with your backend API endpoint for denying a request
          .then(response => {
            alert("Zahtev uspesno odbijen")
            console.log(response)
          })
          .catch(error => {
            console.error(error);
          });
      }
    }
  };
  </script>
  