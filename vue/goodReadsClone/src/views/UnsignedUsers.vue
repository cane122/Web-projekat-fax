<template>
  <div id="searchUsers">
    <h1>Search Users</h1>
    <div class="search-container">
      <input type="text" v-model="searchQueryGenre" placeholder="Search users genre..." />
      <button @click="searchUsersGenre">Search genre</button>
      <input type="text" v-model="searchQueryTitle" placeholder="Search users title..." />
      <button @click="searchUsersTitle">Search Title</button>
    </div>
    <div v-if="users.length > 0" class="user-list">
      <ul>
        <li v-for="user in users" :key="user.username">
          <div class="user-card">
            <div class="user-info">
              <h3>{{ user.name }} {{ user.lastname }}</h3>
              <p>Username: {{ user.username }}</p>
              <p>Email: {{ user.email }}</p>
              <p>Date of Birth: {{ user.dateBirth }}</p>
              <p>Description: {{ user.description }}</p>
            </div>
            <div class="user-image">
              <img :src="user.image" alt="User Image" />
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>No users found.</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "SearchUsers",
  data() {
    return {
      searchQuery: '',
      users: [],
    };
  },
  methods: {
    searchUsersGenre() {
      axios.get(`http://localhost:9090/api/findbookbytitle/${this.searchQueryGenre}`)
        .then(response => {
          console.log("Success:", response.data);
          this.users = response.data;
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    },
    searchUsersTitle() {
      axios.get(`http://localhost:9090/api/findbookbytitle/${this.searchQueryTitle}`)
        .then(response => {
          console.log("Success:", response.data);
          this.users = response.data;
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    },
  },
};
</script>

<style>
h1 {
  color: #4caf50;
}

.search-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.search-container input {
  padding: 5px;
  margin-right: 10px;
}

.user-list {
  display: flex;
  justify-content: center;
}

.user-card {
  display: flex;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 10px;
}

.user-card .user-info {
  flex: 1;
}

.user-card .user-image img {
  max-width: 100px;
  max-height: 100px;
}

.user-card h3 {
  margin: 0;
  font-size: 18px;
}

.user-card p {
  margin: 0;
}

p.no-users {
  text-align: center;
  color: gray;
}
</style>
