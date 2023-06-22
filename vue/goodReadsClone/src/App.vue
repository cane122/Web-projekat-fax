<template>
  <!--<nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>-->
  <router-view/>
</template>


<script>
export default {
  name: 'App',
  created() {
    // Check if a session exists
    const sessionData = sessionStorage.getItem('session');
    if (sessionData) {
      // Session exists, retrieve session data and perform necessary actions
      const session = JSON.parse(sessionData);
      // Do something with the session data, such as setting it to a reactive data property
      this.$data.session = session;
    } else {
      // Session does not exist, initialize a new session
      const newSession = {
        // Define your session properties here
        // For example, you can have properties like 'user', 'isLoggedIn', etc.
        user: null,
        user_type: null,
      };
      // Save the new session to session storage
      sessionStorage.setItem('session', JSON.stringify(newSession));
      // Set the session data to a reactive data property
      this.$data.session = newSession;
    }
  },
  // Other component options
  data() {
    return {
      session: null, // Initialize session as null initially
      // Other data properties
    };
  },
};
</script>

<style>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.btn:hover {
  background-color: #45a049;
}

/* Other styles specific to your application */
</style>