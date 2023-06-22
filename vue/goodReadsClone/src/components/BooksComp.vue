<template>
    <tr>
      <td>{{ book.title }}</td>
      <td>{{ book.author }}</td>
      <td>{{ book.genre.name }}</td>
      <td>{{ book.description }}</td>
      <td>
        <button @click="deleteBook(book.id)">Delete</button>
      </td>
      <td>
        <button @click="updateBook(book.id)">Update</button>
      </td>
      <td>
        <button @click="viewBook(book.id)">View</button>
      </td>
    </tr>
  </template>
  
  <script>
  export default {
    props: ['book'],
    methods: {
      deleteBook(id) {
        this.$emit('delete', id);
      },
      updateBook(id) {
        this.$emit('update', id);
      },
      viewBook(id) {
        this.$emit('view', id);
      },
    },
  };
  </script>
  