<template>
    <div class="main">
        <!-- Header S -->
        <main-header></main-header>
        <!-- Header E -->
        <!-- Body S -->
        <div class="body">
        <div class="table1">
            <h2>게시판 1</h2>
            <table class="table">
            <tbody>
                <tr v-for="item in items" :key="item">
                <td>{{item.age}}</td>
                </tr>
            </tbody>
            </table>
        </div>
        <div class="table2">
            <h2>게시판 2</h2>
            <table class="table">
            <tbody>
                <tr v-for="item in items" :key="item">
                <td>{{item.age}}</td>
                </tr>
            </tbody>
            </table>
        </div>
        <div class="table3">
            <h2>게시판 3</h2>
            <table class="table">
            <tbody>
                <tr v-for="item in items" :key="item">
                <td>{{item.age}}</td>
                </tr>
            </tbody>
            </table>
        </div>
        <div class="table4">
            <h2>게시판 4</h2>
            <table class="table">
            <tbody>
                <tr v-for="item in items" :key="item">
                <td>{{item.age}}</td>
                </tr>
            </tbody>
            </table>
        </div>
        </div>
        <!-- Body E -->
        <!-- Footer S -->
        <main-footer></main-footer>
        <!-- Footer E -->
    </div>
</template>
<script>
import header from "../../components/commons/header.vue"
import footer from "../../components/commons/footer.vue"

export default {
  name: 'App',
  components: {
    'main-header':header,
    'main-footer':footer
  },
  data() {
    return {
      fields:['test'],
      items: [
        { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
        { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
        { age: 38, first_name: 'Jami', last_name: 'Carney' }
      ]
    }
  }
}
</script>
<style>
.body {
  width:80vw;
  margin-left:10vw;
  margin-top:2vw;
  margin-bottom:2vw;
}
.table1{width:50%;display:inline-block}
.table2{width:50%;display:inline-block}
</style>