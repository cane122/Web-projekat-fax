<template>
    <div class="user-details">
        <h1>User Details</h1>
        <div v-if="user">
            <div class="user-info">
                <h3>{{ user.name }} {{ user.lastname }}</h3>
                <p>Username: {{ user.username }}</p>
                <p>Email: {{ user.email }}</p>
                <p>Date of Birth: {{ user.dateBirth }}</p>
                <p>Description: {{ user.description }}</p>
                <router-link :to="{ path: '/update-user', query: { id: user.id } }" class="btn">Update</router-link>

            </div>
            <div class="user-image">
                <img :src="user.image" alt="User Image" />
            </div>
        </div>
        <div v-else>
            <p>User not found.</p>
        </div>
        <div v-if="shelves.length > 0">
            <h2>User Shelves</h2>
            <ul>
                <li v-for="shelf in shelves" :key="shelf.id">
                    {{ shelf.name }}
                </li>
            </ul>
        </div>
        <div v-else>
            <p>No shelves found for this user.</p>
        </div>
    </div>
</template>
  
<script>
import axios from 'axios';

export default {
    name: 'UserDetails',
    data() {
        return {
            user: null,
            shelves: [],
        };
    },
    created() {
        this.fetchUserDetails();
        this.fetchUserShelves();
    },
    methods: {
        fetchUserDetails() {
            const id = this.$route.query.id; // Get the username from the query parameter
            axios.get(`http://localhost:9090/api/users/${id}`)
                .then(response => {
                    console.log("Success:", response.data);
                    this.user = response.data;
                })
                .catch((error) => {
                    console.error("Error:", error);
                });
        },
        fetchUserShelves() {
            const userId = this.$route.params.id; // Get the user ID from the route parameter
            axios.get(`http://localhost:9090/api/users/${userId}/shelfs/`)
                .then(response => {
                    console.log("Success:", response.data);
                    this.shelves = response.data;
                })
                .catch((error) => {
                    console.error("Error:", error);
                });
        }
    },
};
</script>
  
<style>
/* CSS styles for user details view */
</style>
  