<template>
  <div class="body">
      <h3>게시판 상세</h3>
      <div class="content">
          <h4>제목</h4>
            <div v-html="boardContent">
            </div>
            <b-button variant="danger" @click="deleteContent">삭제</b-button>
      </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
    beforeMount(){
        axios.get("http://localhost:8081/v1/api/board/" + this.boardName + "/" + this.boardIdx)
        .then(res => {
            this.boardContent = res.data.boardContent;
        })
        .catch(err => {
            console.log(err);
        })
    },
    methods:{
        deleteContent:function(){
            var deleteConfirm = confirm("게시글을 삭제하시겠습니까?");
            if(deleteConfirm == true){
                axios.delete("http://localhost:8081/v1/api/board/" + this.boardName + "/" + this.boardIdx)
                .then(res => {
                    alert("삭제되었습니다.");
                    this.$router.push("/board/" + this.boardName);
                    console.log(res);
                })
                .catch(err => {
                    console.log(err);
                    alert("Delete Error!");
                })
            }else{
                console.log("fail");
            }
        }
    }
    ,
    data(){
        return{
            boardName : this.$route.params.boardName,
            boardIdx : this.$route.params.boardIdx,
            boardContent:'',

        }
    }
}
</script>

<style>

</style>