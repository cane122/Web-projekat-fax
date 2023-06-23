<template>
  <div id="app">
    <h1>Shelf View</h1>
    <div v-for="shelf in shelves" :key="shelf.id">
      <h2>{{ shelf.name }}</h2>
      <ul>
        <li v-for="book in shelf.books" :key="book.id">
          {{ book.title }}
          <button @click="deleteBook(shelf, book)">Delete</button>
        </li>
      </ul>
      <input type="text" v-model="newBook" placeholder="Enter id of book to add to shelf">
      <button @click="addBook(shelf)">Add Book</button>
    </div>
    <input type="text" v-model="shelfName" placeholder="Enter a new shelf name">
    <button @click="addShelf">Add Shelf</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ShelvesView',

  data() {
    return {
      shelves: [],
    };
  },

  mounted() {
    axios.get('http://localhost:9090/shelves/user', { withCredentials: true })
      .then(response => {
        this.shelves = response.data;
        console.log("Success, data: ", response.data);
      })
      .catch(error => {
        console.error(error);
      });

  },

  methods: {
    deleteBook(shelf, book) {
      const index = shelf.books.findIndex(b => b.id === book.id);
      if (index !== -1) {
        shelf.books.splice(index, 1);
      }
    },
    addBook(shelf) {
      axios.put(`http://localhost:9090/shelves/${shelf.id}/putBook/${this.newBook}`, { withCredentials: true })
          .then(response => {
            // Handle successful response
            console.log("Book added to shelf:", response.data);
            shelf.books.push(response.data);
          })
          .catch(error => {
            console.error("Error adding book to shelf:", error);
            // Handle error case
          });
    },
    addShelf() {
      axios.post('http://localhost:9090/reader/shelves/add', {"name":this.shelfName} ,{ withCredentials: true })
        .then(response => {
          // Handle successful response
          console.log("Shelf added:", response.data);
          // You can update the shelves list by fetching them again
          window.location.reload();
        })
        .catch(error => {
          console.error("Error adding shelf:", error);
          // Handle error case
        });
      },
  },
};
</script>
