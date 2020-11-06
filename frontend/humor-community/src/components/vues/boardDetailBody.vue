<template>
  <div class="body">
      <h3><a @click = "link">{{boardNameTitle}}</a></h3>
      <div class="content">
          <h4>{{boardTitle}}</h4>
            <div class="content-area" v-html="boardContent">
            </div>
            <b-button variant="info" @click="link">목록</b-button>
            <b-button variant="info" @click="modifyContent">수정</b-button>
            <b-button variant="danger" @click="deleteContent">삭제</b-button>
      </div>
      <div class="comment-wrapper">
          <b-form-textarea rows="3"></b-form-textarea>
          <b-button variant="info" @click="link">입력</b-button>
      </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
    beforeMount(){
        if(this.boardName == 'dandan'){ this.boardNameTitle = '단단한유머'}
        else if(this.boardName == 'reading'){ this.boardNameTitle = '읽을거리 판'}
        else if(this.boardName == 'exercise'){ this.boardNameTitle = '운동 판'}
        else if(this.boardName == 'it'){ this.boardNameTitle = 'IT/프로그래밍 판'}
        else if(this.boardName == 'consulting'){ this.boardNameTitle = '고민상담 판'}

        axios.get("http://localhost:8081/v1/api/board/" + this.boardName + "/" + this.boardIdx)
        .then(res => {
            console.log(res);
            this.boardTitle = res.data.boardContentTitle;
            this.boardContent = res.data.boardContent;
        })
        .catch(err => {
            console.log(err);
        })
    },
    methods:{
        link:function(){
            this.$router.push("/board/"+this.boardName);
        },
        modifyContent:function(){

        },
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
            boardTitle : '',
            boardContent:'',
            boardNameTitle :''

        }
    }
}
</script>

<style>
.comment-wrapper{
    margin-top:2vh;
}
</style>