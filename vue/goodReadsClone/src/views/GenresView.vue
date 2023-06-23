<template>
  <div id="allGenres">
    <h1>Genres add</h1>
    <input type="text" v-model="newName" placeholder="Enter a new name">
    <button @click="addName">Add Name</button>
    <br /><br />
    <div>
      <h1>Genre Names</h1>
      <ul>
        <li v-for="genre in genres" :key="genre.id">{{ genre.name }}</li>
      </ul>
    </div>
  </div>
</template>
  
<script>
import axios from "axios";
export default {
  name: "GenresView",
  data() {
    return {
      newGenre: '',
      genres: [],
      newName: ''
    };
  },
  mounted() {
    axios.get('http://localhost:9090/api/genres')
      .then(response => {
        this.genres = response.data;
        console.log("Success, data: ", response.data);
      })
      .catch(error => {
        console.error(error);
      });

  },
  methods: {
    addName() {
      if (this.newName) {
        const newId = this.categories.length + 1;  // new id
        const newCategory = {
          id: newId,
          name: this.newName
        };
        this.categories.push(newCategory);
        this.newName = '';
      }
    }
  },
};
</script>
  
<style>
h1 {
  color: #4caf50;
}

.container-books {
  display: flex;
  justify-content: center;
}

#books th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4caf50;
  color: white;
}

#books td,
#books th {
  border: 1px solid #ddd;
  padding: 8px;
}

#books tr:nth-child(even) {
  background-color: #f2f2f2;
}

#books tr:hover {
  background-color: #ddd;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  margin: auto;
  text-align: center;
}

.title {
  color: grey;
  font-size: 18px;
}
</style>
  