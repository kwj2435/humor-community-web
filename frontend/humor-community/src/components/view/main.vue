<template>
    <div class="main">
        <!-- Header S -->
        <main-header></main-header>
        <!-- Header E -->
        <main-body></main-body>
        <!-- Footer S -->
        <main-footer></main-footer>
        <!-- Footer E -->
    </div>
</template>
<script>
import header from "../../components/commons/header.vue"
import footer from "../../components/commons/footer.vue"
import mainBody from "../../components/vues/mainBody"

export default {
  name: 'App',
  components: {
    'main-header':header,
    'main-footer':footer,
    'main-body':mainBody
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
main-footer { clear:right;}
.main{background-color: darkgrey;}
</style>