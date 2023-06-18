<template>
  <label for="firstName">First name:</label>
  <input v-model="employee.firstName" /><br />
  <label for="lastName">Last name:</label>
  <input v-model="employee.lastName" /><br />
  <label for="position">Position:</label>
  <input v-model="employee.position" /><br />
  <button v-on:click="submit()">submit</button>
</template>

<script>
import axios from "axios"
export default {
  name: "UpdateEmployeeView",
  data: function () {
    return {
      employee: {
        id: {},
        firstName: {},
        lastName: {},
        position: {},
      },
    };
  },
  mounted: function() {
        /*axios
            .get("http://localhost:8081/api/employees/" + this.$route.query.id)
            .then((res) => {
                this.employee = res.data
            })
            .catch((err) => {
                console.log(err)
            })*/

        fetch('http://localhost:8081/api/employees/' + this.$route.query.id)
            .then(response => response.json())
            .then(data => {console.log("Success:", data); this.employee = data})
            .catch((error) => {
                console.error("Error:", error);
            });
    },
    methods: {
    submit: function () {

      axios
        .put("http://localhost:8081/api/employees/update/" + this.$route.query.id, this.employee)
        .then((res) => {
          console.log(res);
          this.$router.push("/employees");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });

      /*fetch("http://localhost:8081/api/employees", {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-type": "application/json",
        },
        body: JSON.stringify(this.employee),
      })
        .then((response) => response.json)
        .then((data) => {
          console.log("Success : " + data);
          this.$router.push("/employees");
        })
        .catch((err) => {
          console.log("Error : " + err);
          alert(err);
        });*/
    },
  },
};
</script>
