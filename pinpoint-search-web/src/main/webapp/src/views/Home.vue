<template>
  <el-container class="container">
    <el-header>
      <h1>Pinpoint Search</h1>
    </el-header>
    <el-main>
      <Main/>
    </el-main>
  </el-container>
</template>
<script>
import Main from '@/views/main'

export default {
  name: 'Home',
  components: { Main }
}
</script>

<style scoped>
.container {
  width: 1400px;
  margin: auto;
  padding: 0;
}
</style>
