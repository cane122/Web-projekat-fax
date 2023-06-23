<template>
  <div>
    <h2>Add book</h2>
    <form @submit.prevent="addbook">
      <label for="title">Title:</label>
      <input type="text" id="title" v-model="bookDTO.title" required>

      <label for="image">Image URL:</label>
      <input type="text" id="image" v-model="bookDTO.image" required>

      <label for="isbn">ISBN:</label>
      <input type="text" id="isbn" v-model="bookDTO.isbn" required>

      <label for="datePublished">Date Published:</label>
      <input type="date" id="datePublished" v-model="bookDTO.datePublished" required>

      <label for="pages">Pages:</label>
      <input type="number" id="pages" v-model="bookDTO.pages" required>

      <label for="description">Description:</label>
      <textarea id="description" v-model="bookDTO.description" required></textarea>

      <label for="genre">Genre:</label>
      <input type="text" id="genre" v-model="bookDTO.genre" required>

      <label for="authors">Authors:</label>
      <input type="text" id="authors" v-model="bookDTO.authors" required>

      <button type="submit">Add book</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      bookDTO: {
        title: '',
        image: '',
        isbn: '',
        datePublished: '',
        pages: '',
        description: '',
        genre: '',
        authors: '',
      },
    };
  },
  methods: {
    async addbook() {
      try {
        console.log(this.bookDTO)
        const response = await axios.post('http://localhost:9090/book/addBook/', this.bookDTO, { withCredentials: true });

        if (response.status === 200) {
          console.log('book added!');
          alert("book added")
          this.$router.push('/books');
        } else {
          console.error('Error adding book');
          alert("error adding book")
          alert(response)
        }
      } catch (error) {
        console.error('Error adding book', error);
        alert(error)
      }
    },
  },
};
</script>

