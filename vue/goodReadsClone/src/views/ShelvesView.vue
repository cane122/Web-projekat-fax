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
export default {
    name: "ShelvesView",

    data() {
    return {
      shelves: [
        { id: 1, name: 'Shelf 1', books: [{ id: 1, title: 'Book 1' }, { id: 2, title: 'Book 2' }] },
        { id: 2, name: 'Shelf 2', books: [{ id: 3, title: 'Book 3' }, { id: 4, title: 'Book 4' }] },
        { id: 3, name: 'Shelf 3', books: [{ id: 5, title: 'Book 5' }, { id: 6, title: 'Book 6' }] }
      ],
      newBook: ''
    };
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
          title: this.newBook
        };
        shelf.books.push(newBook);
        this.newBook = '';
      }
    }
  }
}
</script>