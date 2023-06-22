<template>
    <div>
      <h2>Book Profile</h2>
      <div class="card">
        <img alt="Book" :src="book.image" />
        <h1>{{ book.title }}</h1>
        <p class="title">{{ book.genre }}</p>
        <p>{{ book.description }}</p>
        <p>ISBN: {{ book.isbn }}</p>
        <p>Date Published: {{ formatDate(book.datePublished) }}</p>
        <p>Pages: {{ book.pages }}</p>
        <p>Authors: {{ book.authors }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'BookView',
    data() {
      return {
        book: {}
      };
    },
    mounted() {
      fetch('http://localhost:9090/api/book/' + this.$route.query.id)
        .then(response => response.json())
        .then(data => {
          console.log("Success:", data);
          this.book = data;
        })
        .catch(error => {
          console.error("Error:", error);
        });
    },
    methods: {
      formatDate(date) {
        return new Date(date).toLocaleDateString();
      }
    }
  };
  </script>
  