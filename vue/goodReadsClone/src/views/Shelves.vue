<template>
  <div id="app">
    <h1>Shelf View</h1>
    <div v-for="shelf in shelves" :key="shelf.id">
      <h2>{{ shelf.name }}</h2>
      <ul>
        <li v-for="shelfInstance in shelf.shelfInstance" :key="shelfInstance.id">
          {{ shelfInstance.book.title }}
          <div v-if="shelfInstance.review !== null">
            {{ shelfInstance.review }}
            <button @click="openReviewModal(shelfInstance, shelf)">edit Review</button>
            <div v-if="selectedShelfInstance === shelfInstance">
              <h3>Edit Review</h3>
              <input type="number" v-model="editGrade" placeholder="Grade">
              <textarea v-model="editReviewText" placeholder="Review Text"></textarea>
              <button @click="editReview">edit Review</button>
            </div>
          </div>
          <button @click="deleteBook(shelf, shelfInstance.book)">Delete</button>
          <div v-if="(shelfInstance.review === null)">
            <button @click="openReviewModal(shelfInstance, shelf)">Add Review</button>
          </div>
          <div v-if="(selectedShelfInstance === shelfInstance)">
            <div v-if="(shelfInstance.review === null)">
              <h3>Add Review</h3>
              <input type="number" v-model="review.grade" placeholder="Grade">
              <textarea v-model="review.text" placeholder="Review Text"></textarea>
              <button @click="saveReview">Save Review</button>
            </div>
          </div>
        </li>
      </ul>
      <input type="text" v-model="newBook" placeholder="Enter id of book to add to shelf">
      <button @click="addBook(shelf)">Add Book</button>
    </div>
    <!-- Review Modal -->

    <input type="text" v-model="shelfName" placeholder="Enter a new shelf name">
    <button @click="addShelf">Add Shelf</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ShelvesView',

  data() {
    return {
      shelves: [],
      selectedShelfInstance: null,
      selectedShelf: null,
      review: {
        grade: null,
        text: '',
        reviewDate: null
      },
    };
  },

  mounted() {
    this.fetchShelves();
  },

  methods: {
    fetchShelves() {
      axios.get('http://localhost:9090/shelves/user', { withCredentials: true })
        .then(response => {
          this.shelves = response.data;
          console.log(this.shelves)
          console.log("Success, data: ", response.data);
        })
        .catch(error => {
          console.error(error);
        });
    },
    deleteBook(shelf, book) {
      const shelfId = shelf.id;
      const bookId = book.id;

      axios.delete(`http://localhost:9090/shelves/${shelfId}/deleteBook/${bookId}`, { withCredentials: true })
        .then(response => {
          // Handle successful deletion
          console.log("Book deleted from shelf:", response.data);
          this.fetchShelves();
          window.location.reload();
          // Update the shelves list or perform any necessary actions
        })
        .catch(error => {
          console.error("Error deleting book from shelf:", error);
          // Handle error case
        });
    },
    openReviewModal(shelfInstance, shelf) {
      this.selectedShelfInstance = shelfInstance;
      this.selectedShelf = shelf;
    },
    addBook(shelf) {
      fetch(`http://localhost:9090/shelves/${shelf.id}/putBook/${this.newBook}`, {
        method: 'PUT',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json'
        },
      })
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Error adding book to shelf');
          }
        })
        .then(data => {
          console.log("Book added to shelf:", data);
          this.fetchShelves();
          window.location.reload();
        })
        .catch(error => {
          console.error("Error adding book to shelf:", error);
          this.fetchShelves();
        });
    },
    addShelf() {
      axios.post('http://localhost:9090/reader/shelves/add', { "name": this.shelfName }, { withCredentials: true })
        .then(response => {
          // Handle successful response
          console.log("Shelf added:", response.data);
          // You can update the shelves list by fetching them again
          this.fetchShelves();
          window.location.reload();
        })
        .catch(error => {
          console.error("Error adding shelf:", error);
          // Handle error case
        });
    },
    saveReview() {
      const shelfId = this.selectedShelf.id;
      const shelfInstanceId = this.selectedShelfInstance.id;
      const reviewData = {
        grade: this.review.grade,
        text: this.review.text,
        reviewDate: new Date().toISOString().split('T')[0] // Get current date
      };

      axios.put(`http://localhost:9090/shelves/${shelfId}/addreview/${shelfInstanceId}`, reviewData, { withCredentials: true })
        .then(response => {
          // Handle successful review addition
          console.log("Review added:", response.data);
          console.log(reviewData);
          this.selectedShelfInstance = null;
          this.selectedShelf = null;
          this.fetchShelves();
          window.location.reload();
        })
        .catch(error => {
          console.error("Error adding review:", error);
        });
    },
    editReview() {
      const shelfInstanceId = this.selectedShelfInstance.id;
      const reviewData = {
        grade: this.editGrade,
        text: this.editReviewText,
        reviewDate: new Date().toISOString().split('T')[0] // Get current date
      };

      axios.put(`http://localhost:9090/user/edit/review/${shelfInstanceId}`, reviewData, { withCredentials: true })
        .then(response => {
          // Handle successful review addition
          console.log("Review edited:", response.data);
          console.log(reviewData);
          this.selectedShelfInstance = null;
          this.selectedShelf = null;
          this.fetchShelves();
          window.location.reload();
        })
        .catch(error => {
          console.error("Error adding review:", error);
        });
    },
  },
};
</script>