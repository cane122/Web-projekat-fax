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
      <input type="text" v-model="newBook" placeholder="Enter a new book title">
      <button @click="addBook(shelf)">Add Book</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ShelvesView',

  data() {
    return {
      shelves: [],
      newBook: '',
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
      if (this.newBook) {
        const newId = Math.max(...shelf.books.map(b => b.id)) + 1;
        const newBook = {
          id: newId,
          title: this.newBook,
        };
        shelf.books.push(newBook);
        this.newBook = '';
      }
    },
  },
};
</script>
