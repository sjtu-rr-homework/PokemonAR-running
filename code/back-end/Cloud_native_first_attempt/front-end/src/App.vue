<template>
  <div id="app" class="bg-black text-light">
    <div class="row p-3">
      <div class="col-2">
        <router-link to='/' class="text-decoration-none">
          <button class="btn btn-implicit-dark btn-block">模拟</button>
        </router-link>
      </div>
      <div class="col-2">
        <router-link to='/user' class="text-decoration-none">
          <button class="btn btn-implicit-dark btn-block">用户数据</button>
        </router-link>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
