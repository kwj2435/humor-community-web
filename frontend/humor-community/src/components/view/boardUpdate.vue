<template>
  <div class="boardUpdateBody">
      <main-header></main-header>
      <board-body></board-body>
      <main-footer></main-footer>
  </div>
</template>

<script>
import header from '../commons/header'
import footer from '../commons/footer'
import boardBody from '../vues/boardUpdateBody'

export default {
    components:{
        'main-header':header,
        'main-footer':footer,
        'board-body':boardBody    
    }
}
</script>

<style>

</style>