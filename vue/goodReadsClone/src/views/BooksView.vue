<template>
  <div id="allBooks">
    <h1>Books</h1>
    <button v-if="author" v-on:click="addBook">Add new book</button>
    <br /><br />
    <div class="search-container">
      <input v-model="searchTitle" type="text" placeholder="Search by Title" />
      <button @click="searchByTitle">Search</button>
    </div>
    <div class="search-container">
      <input v-model="searchAuthor" type="text" placeholder="Search by Author" />
      <button @click="searchByAuthor">Search</button>
    </div>
    <div class="container-books">
      <table id="books">
        <tr>
          <th>Title</th>
          <th>Author</th>
          <th>Genre</th>
          <th>Description</th>
          <th>Delete</th>
          <th>Update</th>
          <th>View</th>
        </tr>
        <books-comp v-for="book in books" :key="book.id" :book="book" @delete="deleteBook" @update="updateBook"
          @view="viewBook">
        </books-comp>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import BooksComp from "../components/BooksComp.vue";

export default {
  name: "BooksView",
  components: { BooksComp },
  data() {
    return {
      books: [],
      author: localStorage.getItem('user_type') === 'Author'
    };
  },
  beforeMount() {
    axios.get('http://localhost:9090/api/books')
      .then(response => {
        console.log("Success:", response.data);
        this.books = response.data;
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  },
  methods: {
    addBook() {
      this.$router.push("/add-book");
    },
    deleteBook(id) {
      axios.delete("http://localhost:9090/deleteBook/" + id, { withCredentials: true })
        .then((res) => {
          if (res.status === 200) {
            window.location.reload();
          } else {
            alert(res.status);
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          console.log("Failed to delete book:", error.message);
          // Display the error message or perform any other necessary actions
        });
    },
    updateBook(id) {
      this.$router.push({ path: '/update-book', query: { id: id } });
    },
    viewBook(id) {
      this.$router.push({ path: '/book', query: { id: id } });
    },
    searchByTitle() {
      if (this.searchTitle) {
        axios.get(`http://localhost:9090/api/findbookbytitle/${this.searchTitle}`)
          .then(response => {
            console.log("Search Results:", response.data);
            this.books = response.data;
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      } else {
        // If searchTitle is empty, fetch all books
        this.fetchBooks();
      }
    },
    searchByAuthor() {
      if (this.searchAuthor) {
        axios.get(`http://localhost:9090/api/findbookbyauthor/${this.searchAuthor}`)
          .then(response => {
            console.log("Search Results:", response.data);
            this.books = response.data;
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      } else {
        // If searchAuthor is empty, fetch all books
        this.fetchBooks();
      }
    }
  }
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
