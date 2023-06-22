<template>
  <div id="allBooks">
    <h1>Books</h1>
    <button v-on:click="addBook">Add new book</button>
    <br /><br />
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
import BooksComp from "../components/BooksComp.vue";

export default {
  name: "BooksView",
  components: { BooksComp },
  data() {
    return {
      books: [],
    };
  },
  mounted() {
    fetch('http://localhost:9090/api/books')
      .then(response => response.json())
      .then(data => { console.log("Success:", data); this.books = data; })
      .catch((error) => {
        console.error("Error:", error);
      });
  },
  methods: {
    addBook() {
      this.$router.push("/add-book");
    },
    deleteBook(id) {
      fetch("http://localhost:9090/deleteBook/" + id, {
        method: "DELETE",
      })
        .then((res) => {
          if (res.ok) {
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
