<template>
  <div class="main">
      <main-header></main-header>
      <boardDetailBody></boardDetailBody>
      <main-footer></main-footer>
  </div>
</template>

<script>
import header from '../commons/header'
import footer from '../commons/footer'
import boardDetailbody from '../vues/boardDetailBody'

export default {
    components:{
        "main-header":header,
        "main-footer":footer,
        "boardDetailBody":boardDetailbody
    }
}
</script>

<style>

</style>