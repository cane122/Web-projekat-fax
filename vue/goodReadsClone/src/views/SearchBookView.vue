<template>
    <h1>Book Search</h1>
    <input type="text" v-model="searchQuery" placeholder="Enter search query">
    <ul>
      <li v-for="book in filteredBooks" :key="book.id">{{ book.title }}</li>
    </ul>
</template>

<script>
import axios from 'axios';

export default {
    name: "SearchBookView",
    data() {
        return {
          searchQuery: '',
          books: []
        };
      },
      mounted() {
        this.fetchBooks();
      },
      methods: {
        fetchBooks() {
          axios.get('http://localhost:9090/api/findbookbytitle/'+this.searchQuery)
            .then(response => {
              this.books = response.data;
            })
            .catch(error => {
              console.error(error);
            });
        }
      },
      computed: {
        filteredBooks() {
          const query = this.searchQuery.toLowerCase();
          return this.books.filter(book => book.title.toLowerCase().includes(query));
        }
      }
};
</script>