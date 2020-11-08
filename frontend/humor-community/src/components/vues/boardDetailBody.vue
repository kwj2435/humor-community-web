<template>
  <div class="body">
      <div class="body-content">
        <h3><a @click = "link">{{boardNameTitle}}</a></h3>
        <div class="content">
            <h4 class="board-title">{{boardTitle}}</h4>
                <div class="content-area" v-html="boardContent">
                </div>
                <div class="btn-group">
                    <b-button class="content-btn" id="goList" variant="info" @click="link">목록</b-button>
                    <b-button v-if="loginCheck" class="content-btn" variant="info" @click="modifyContent">수정</b-button>
                    <b-button v-if="loginCheck" class="content-btn" variant="danger" @click="deleteContent">삭제</b-button>
                </div>
        </div>
        <div  class="comment-wrapper">
            <h3>{{commentLength}} 개의 댓글</h3>
            <div v-if="loginCheck">
                <b-form-textarea v-model="commentText" rows="3"></b-form-textarea>
                <b-button  class="comment-btn" variant="info" @click="writeComment">입력</b-button>
            </div>
        <div class="comment-content-wrapper">
            <div v-for="(item,index) in commentList" :key="index">
                <div class="comment-info">
                    <span class="comment-nickname">{{item.commentUserNickname}}</span>
                    <span class="comment-date">{{item.commentBeginDate}}</span>
                </div>
                <p>{{item.commentContent}}</p>
                <b-form-textarea rows="3" v-model="item.commentContent"></b-form-textarea>
                <div class="comment-btn-group">
                    <b-button class="content-btn" variant="info" @click="modifyContent">수정</b-button>
                    <b-button class="" variant="danger" @click="deleteComment(item)">삭제</b-button>
                </div>
            </div>
        </div>
        </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import $ from 'jquery'
export default {

    beforeMount(){
        if(this.boardName == 'dandan'){ this.boardNameTitle = '단단한유머'}
        else if(this.boardName == 'reading'){ this.boardNameTitle = '읽을거리 판'}
        else if(this.boardName == 'exercise'){ this.boardNameTitle = '운동 판'}
        else if(this.boardName == 'it'){ this.boardNameTitle = 'IT/프로그래밍 판'}
        else if(this.boardName == 'consulting'){ this.boardNameTitle = '고민상담 판'}

        if(window.sessionStorage.getItem("userEmail") != null){
            this.loginCheck = true;
            alert($('#goList').attr('class'));
        }
        // Get 게시글 정보
        axios.get("http://localhost:8081/v1/api/board/" + this.boardName + "/" + this.boardIdx)
        .then(res => {
            console.log(res);
            this.boardTitle = res.data.boardContentTitle;
            this.boardContent = res.data.boardContent;
        })
        .catch(err => {
            console.log(err);
        })

        // Get 댓글 정보
        this.getCommentList();
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
        },
        getCommentList:function(){
            axios.get("http://localhost:8081/v1/api/comment/" + this.boardIdx)
            .then(res => {
                console.log(res);
                this.commentList = res.data;
                this.commentLength = res.data.length;
            })
        },
        writeComment:function(){
            axios.post("http://localhost:8081/v1/api/comment/",{
                "boardIdx":this.boardIdx,
                "commentNo":this.commentLength + 1,
                "commentDepth":0,
                "commentContent":this.commentText,
                "commentUserEmail": this.userEmail,
                "commentUserNickname" : this.userNickname
            })
            .then(res => {
                this.getCommentList();
                console.log(res);
            })
            .catch()
        },
        deleteComment:function(item){
            axios.delete("http://localhost:8081/v1/api/comment/" + item.commentIdx)
            .then(res => {
                if(res.data.result == 1){
                    alert("삭제되었습니다.");
                }else{
                    alert("삭제 실패, 관리자 문의 바람");
                }
            })
            .catch(err =>{
                alert("Fail" + err);
            })
        }
    }
    ,
    data(){
        return{
            loginCheck : false,
            userEmail : window.sessionStorage.getItem("userEmail"),
            userNickname : window.sessionStorage.getItem("userNickname"),
            boardName : this.$route.params.boardName,
            boardIdx : this.$route.params.boardIdx,
            boardTitle : '',
            boardContent:'',
            boardNameTitle :'',
            commentText : '',
            commentList : [],
            commentLength:0,
        }
    }
}
</script>

<style>
.comment-wrapper{
    margin-top:2vh;
}
.body-content{
    margin:0 auto;
    width:90%;
}
.comment-btn{
    margin-top:5px;
    float:right;
}
.comment-content-wrapper{
    margin-top:10vh;
}
.comment-date{
    margin-right:1vw;
    float:right;
}
.comment-info{
    background-color:lightblue;
}
.comment-nickname{
    margin-left:5px;
}
.content-btn{
    margin-right:5px;
}
.btn-group{
    margin-left : 50vw;
}
.content-area{
    margin-top:3vh;
    min-height: 30vh;
}
.board-title{
    margin-top:5vh;
}
</style>