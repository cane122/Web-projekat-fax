<template>
  <tr>
    <td>{{ employee.firstName }}</td>
    <td>{{ employee.lastName }}</td>
    <td>{{ employee.position }}</td>
    <td>
      <button class="btnSeeMore" v-on:click="seeMore">See more</button>
    </td>
    <td>
      <button class="delete" v-on:click="deleteEmployee">
        Delete
      </button>
    </td>
    <td>
      <button class="Update" v-on:click="updateEmployee">
        Update
      </button>
    </td>
  </tr>
</template>

<script>
export default {
  name: "EmployeeComp",
  props: ["employee"],
  data: function () {
    return {
      employees: [],
    };
  },
  mounted: function () {

//primer axios poziva
/*axios
  .get("http://localhost:8081/api/employees")
  .then((res) => {
    this.employees = res.data
  })
  .catch((err) =>{
    console.log(err)
  })*/

  fetch('http://localhost:8081/api/employees')
    .then(response => response.json())
    .then(data => {console.log("Success:", data); this.employees = data})
    .catch((error) => {
      console.error("Error:", error);
    });
},
  methods: {
    seeMore: function () {
      this.$router.push("/employee?id=" + this.employee.id);
    },
    deleteEmployee: function() {
      fetch("http://localhost:8081/api/employees/" + this.employee.id, {
        method: "DELETE",
      }).then((res) => {
        if (res.ok) {
          window.location.reload();
        }
      });
    },
    updateEmployee: function () {
      this.$router.push("/update-employee?id=" + this.employee.id);
    },
  },
};
</script>


<style>
</style>
