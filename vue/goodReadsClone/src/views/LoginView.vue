<template>
    <div class="login-container">
        <h1>Login</h1>
        <form @submit.prevent="login">
            <div class="form-group">
                <label for="usernameOrEmail">Username or Email</label>
                <input type="text" id="usernameOrEmail" v-model="loginData.usernameOrEmail" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" v-model="loginData.password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
    </div>
</template>
  
<script>
import axios from 'axios';

export default {
    name: "LoginView",
    data() {
        return {
            loginData: {
                usernameOrEmail: "",
                password: ""
            }
        };
    },
    methods: {
        login() {
            axios.post('http://localhost:9090/signin', this.loginData)
                .then(response => {
                    // Handle successful login response
                    const loggedInMessage = "User successfully logged in";
                    alert(loggedInMessage);

                    localStorage.setItem('user_type', response.data);

                    // Redirect to a specific route
                    this.$router.push('/');
                })
                .catch(error => {
                    // Handle login error
                    if (error.response && error.response.data) {
                        const errorMessage = error.response.data;
                        alert(errorMessage);
                        // Display error message or perform any necessary actions
                    } else {
                        alert("An error occurred during login.");
                    }
                });

        }
    }
};
</script>
  
<style>
.login-container {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    text-align: center;
}

h1 {
    font-size: 24px;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    padding: 10px 20px;
    background-color: #4caf50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}
</style>