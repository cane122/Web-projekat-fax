<template>
  <div>
    <h1>Books</h1>
    <button @click="addBook">Add new book</button>
    <br /><br />
    <div class="container-books">
      <table id="books">
        <tr>
          <th>Title</th>
          <th>ISBN</th>
          <th>Reviews</th>
          <th></th>
          <th></th>
          <th></th>
        </tr>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.title }}</td>
          <td>{{ book.isbn }}</td>
          <td>{{ book.reviews }}</td>
          <td>
            <button class="btnSeeMore" @click="seeMore(book)">See more</button>
          </td>
          <td>
            <button class="delete" @click="deleteBook(book.id)">Delete</button>
          </td>
          <td>
            <button class="update" @click="updateBook(book.id)">Update</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "BooksView",
  data() {
    return {
      books: [],
    };
  },
  mounted() {
    fetch("http://localhost:9090/api/books")
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);
        this.books = data;
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  },
  methods: {
    addBook() {
      this.$router.push("/add-book");
    },
    seeMore(book) {
      this.$router.push("/book?id=" + book.id);
    },
    deleteBook(id) {
      fetch("http://localhost:9090/api/books/" + id, {
        method: "DELETE",
      })
        .then((res) => {
          if (res.ok) {
            window.location.reload();
          }
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    },
    updateBook(id) {
      this.$router.push("/update-book?id=" + id);
    },
  },
};
</script>

<style>
/* Add your custom styles here */
</style>
