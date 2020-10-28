<template>
    <div class="main">
        <main-header></main-header>
        <list-detail></list-detail>
        <main-footer></main-footer>
    </div>
</template>

<script>
import header from '../commons/header';
import listDetail from '../vues/listDetail';
import footer from '../commons/footer';

export default {
    components:{
        'main-header':header,
        'list-detail':listDetail,
        'main-footer':footer
    }
}
</script>

<style>

</style>