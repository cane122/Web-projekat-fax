<template>
  <div>
    <h2>Update book</h2>
    <form @submit.prevent="editBook">
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

      <button type="submit">update book</button>
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
  async created() {
    const bookId = this.$route.query.id; // Replace with the desired book ID
    try {
      const response = await axios.get(`http://localhost:9090/api/book/${bookId}`);
      const book = response.data;
      for (const prop in book) {
        if (prop in this.bookDTO) {
          this.bookDTO[prop] = book[prop];
          console.log("emituje se" + prop + book[prop])
          this.$emit(prop,book[prop]);
        }
      }
      console.log(this.bookDTO);
    } catch (error) {
      console.error('Error fetching book details', error);
      // Display the error message or perform any other necessary actions
    }
  },
  methods: {
 
  
  async editBook(id) {
    try {
      const response = await axios.put(`http://localhost:9090/book/editBook/${id}`, this.bookDTO, { withCredentials: true });

      if (response.status === 200) {
        console.log('Book updated!');
        alert('Book updated');
        this.$router.push('/books');
      } else {
        console.error('Error updating book');
        alert('Error updating book');
        console.log(response.data);
      }
    } catch (error) {
      console.error('Error updating book', error);
      alert(error);
    }
  },
},
}
</script>
