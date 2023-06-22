<template>
  <div>
    <h2>Add Book</h2>
    <form @submit.prevent="addBook">
      <label for="title">Title:</label>
      <input type="text" id="title" v-model="book.title" required>

      <label for="image">Image URL:</label>
      <input type="text" id="image" v-model="book.image" required>

      <label for="isbn">ISBN:</label>
      <input type="text" id="isbn" v-model="book.isbn" required>

      <label for="datePublished">Date Published:</label>
      <input type="date" id="datePublished" v-model="book.datePublished" required>

      <label for="pages">Pages:</label>
      <input type="number" id="pages" v-model="book.pages" required>

      <label for="description">Description:</label>
      <textarea id="description" v-model="book.description" required></textarea>

      <label for="genre">Genre:</label>
      <input type="text" id="genre" v-model="book.genre" required>

      <label for="authors">Authors:</label>
      <input type="text" id="authors" v-model="book.authors" required>

      <button type="submit">Add Book</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      book: {
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
    async addBook() {
      try {
        const response = await fetch('/api/books', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.book),
        });

        if (response.ok) {
          // Book added successfully
          console.log('Book added!');
          // You can redirect or show a success message here
        } else {
          console.error('Error adding book');
          // Handle the error condition
        }
      } catch (error) {
        console.error('Error adding book', error);
        // Handle the error condition
      }
    },
  },
};
</script>
