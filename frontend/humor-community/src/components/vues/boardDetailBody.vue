<template>
  <div class="body board-detail">
      <div class="body-content">
        <h4><a @click = "link" href="#">{{boardNameTitle}}</a></h4>
        <div class="content">
            <h4 class="board-title">{{boardTitle}}</h4>
            <p class="board-nickname">{{boardNickname}}</p>
            <p>
                <b-dropdown class="attached-file" text="첨부파일">
                <b-dropdown-item 
                v-for="(item,index) in attachedfileList" 
                :key="index" href="#" 
                @click="fileDownloadLink(item)">
                {{item.fileOriginalName}}
                </b-dropdown-item>
                </b-dropdown>
            </p>
                <div class="content-area" v-html="boardContent">
                </div>
                <div class="btn-group">
                    <b-button v-if="loginCheck" class="content-btn" variant="info" @click="modifyContent">수정</b-button>
                    <b-button v-if="loginCheck" class="content-btn" variant="danger" @click="deleteContent">삭제</b-button>
                    <b-button class="content-btn" id="goList" variant="info" @click="link">목록</b-button>
                </div>
        </div>
        <!-- 댓글 시작-->
        <div  class="comment-wrapper">
            <h3>{{commentLength}} 개의 댓글</h3>
            <div v-if="loginCheck">
                <b-form-textarea v-model="commentText" rows="3"></b-form-textarea>
                <b-button  class="comment-btn" variant="info" @click="writeComment">입력</b-button>
            </div> 
        <div class="comment-content-wrapper">
            <div v-for="(item,index) in commentList" :key="index" :id="'comment'+index">
                <div class="comment-info">
                    <span class="comment-nickname">{{item.commentUserNickname}}</span>
                    <span class="comment-date">{{item.commentBeginDate}}</span>
                </div>
                <p class="comment-content">{{item.commentContent}}</p>
                <div :class="item.commentIdx.toString()" style="display:none">
                    <b-form-textarea rows="3" v-model="item.commentContent"></b-form-textarea>
                </div>
                <div v-if="checkCommentUser(item.commentUserNickname)" class="comment-btn-group" >
                    <a href="" @click="clickModifyBtn('comment'+index,item.commentIdx.toString())">수정</a>
                    <span> / </span>
                    <a href="" @click="deleteComment(item)">삭제</a>
                </div>
                <div v-if="checkCommentUser(item.commentUserNickname)" class="comment-update-btn-group">
                    <b-button variant="danger" @click="modifyComment(item)">작성</b-button>
                    <b-button variant="info" @click="cancelModifyComment(item.commentIdx.toString())">취소</b-button>
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

    mounted(){
        if(this.boardName == 'dandan'){ this.boardNameTitle = '단단한유머'}
        else if(this.boardName == 'reading'){ this.boardNameTitle = '읽을거리 판'}
        else if(this.boardName == 'exercise'){ this.boardNameTitle = '운동 판'}
        else if(this.boardName == 'it'){ this.boardNameTitle = 'IT/프로그래밍 판'}
        else if(this.boardName == 'consulting'){ this.boardNameTitle = '고민상담 판'}

        if(window.sessionStorage.getItem("userNickname") != null){
            this.loginCheck = true;
        }else{
            $(".btn-group").css("margin-left","57vw");
        }
        // Get 게시글 정보
        axios.get("http://localhost:8081/v1/api/board/" + this.boardName + "/" + this.boardIdx)
        .then(res => {
            this.boardTitle = res.data.boardContentTitle;
            this.boardContent = res.data.boardContent;
            this.boardNickname = res.data.boardContentWriter;
        })
        .catch(err => {
            console.log(err);
        })
        // Get 댓글 정보
        this.getCommentList();
        //Get 첨부파일 목록
        this.getAttachedFileList();
        //조회수 증가
        this.increaseViewCount();
    },
    methods:{
        increaseViewCount:function(){
            axios.put("http://localhost:8081://v1/api/" +this.boardName+"/"+this.boardIdx+"/counts")
            .then(res =>{
                console.log(res);
            })
        },
        checkCommentUser:function(commentNickname){
            return this.userNickname == commentNickname;
        },
        fileDownloadLink:function(item){
            location.href="http://localhost:8081/v1/api/download/" + item.fileNo;
        },
        getAttachedFileList:function(){
            axios.get("http://localhost:8081/v1/api/board/"+this.boardIdx+"/files")
            .then(res =>{
                this.attachedfileList = res.data;
            })
            .catch(err=>{
                alert("페이지 로드 실패, 관리자 문의");
                console.log(err);
            })
        },
        link:function(){
            this.$router.push("/board/"+this.boardName);
        },
        /* 게시글 수정 */
        modifyContent:function(){
            this.$router.push("/board/modify/" + this.boardName+"/" + this.boardIdx);
        },
        /* 게시글 삭제 */
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
        /* 댓글 목록 가져오기 */
        getCommentList:function(){
            axios.get("http://localhost:8081/v1/api/comment/" + this.boardIdx)
            .then(res => {
                console.log(res);
                this.commentList = res.data;
                this.commentLength = res.data.length;
            })
        },
        /* 댓글 작성*/
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
                    this.getCommentList();
                }else{
                    alert("삭제 실패, 관리자 문의");
                }
            })
            .catch(err =>{
                alert("Fail" + err);
            })
        },
        clickModifyBtn:function(commentId,commentIdx){
            $("#"+commentId).children(".comment-content").css("display","none");
            $("#"+commentId).children(".comment-btn-group").css("display","none");
            $("#"+commentId).children("." + commentIdx).css("display","block");
            $("#"+commentId).children(".comment-update-btn-group").css("display","block");
        },
        cancelModifyComment:function(commentIdx){
            $(".comment-content").css("display","block");
            $(".comment-btn-group").css("display","block");
            $("." + commentIdx).css("display","none");
            $(".comment-update-btn-group").css("display","none");
        },
        modifyComment:function(item){
            console.log(item.commentContent);
            axios.put("http://localhost:8081/v1/api/comment/" + item.commentIdx,
            {
                    commentContent:item.commentContent
            })
            .then(() => {
                this.getCommentList();
                this.cancelModifyComment(item.commentIdx);
            })
            .catch(() =>{
                alert("Error!");
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
            boardNickname:'',
            commentText : '',
            commentList : [],
            commentLength:0,
            attachedfileList:[],
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
    margin-top:9vh;
    min-height: 30vh;
}
.board-title{
    margin-top:6vh;
    padding-bottom: 4px;
    margin-bottom: 0px;
    border-bottom: 2px solid black;
}
.comment-update-btn-group{
    display:none;
}
.board-nickname{
    margin-top:-2;
    padding-top:0;
    background-color: #CCFFFF;
    padding-left:5px;
    margin-bottom: 0;
}
.attached-file{
    float:right;
    margin-top:1vh;
}
.comment-btn-group{
    margin-left:56vw;
    margin-bottom: 10px;
}
.comment-update-btn-group{
    margin-left:54vw;
    margin-bottom: 10px;
}
.comment-content{
    padding-top:1vh;
    min-height: 10vh;
}
</style>