<template>
  <div class="body">
      <h2>게시판 글 수정</h2>
      <b-form @submit.prevent="contentSubmit()">
        <b-form-group>
        <b-form-input
          id="title"
          v-model="contentTitle"
          type="text"
          required
          placeholder="제목을 입력해주세요."
        ></b-form-input>
        </b-form-group>
        <b-form-group>
          <editor height="400px" initialEditType="wysiwyg" ref="editorRef" v-if="editorData != null" :initialValue="editorData"/>
        </b-form-group>
        <b-form-group>
          <b-form-file v-model="contentFile" placeholder="파일 첨부"></b-form-file>
        </b-form-group>
        <b-form-group>
          <button type="button" class="btn btn-primary submitBtn" @click="modifyContent">등록</button>
        </b-form-group>
      </b-form>
  </div>
</template>

<script>
import axios from 'axios'
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';

export default {
  components:{
    "editor":Editor
  },
  mounted(){
      axios.get("http://localhost:8081/v1/api/board/" + this.boardName +"/" + this.boardIdx)
      .then(res => {
          this.boardInfo = res.data;
          this.contentTitle = res.data.boardContentTitle;
          this.editorData = res.data.boardContent;
          console.log(res);
      })
      axios.get("http://localhost:8081/v1/api/file/" + this.boardIdx)
      .then(res =>{
        this.fileNo = res.data.fileNo;
        this.fileName = res.data.fileOriginalName;
      })
      .catch()
  },
  data(){
    return{
      contentTitle : '',
      contentWriter : window.sessionStorage.getItem("userNickname"),
      contentFile : null,
      loginCheck : false,
      editorData : null,
      boardName : this.$route.params.boardName,
      boardIdx : this.$route.params.boardIdx,
      boardInfo :'',
      fileNo : 0,
      fileName : ''
    }
  },
  methods:{
    modifyContent:function(){
      var boardContent = this.$refs.editorRef.invoke("getMarkdown");

      axios.put('http://localhost:8081/v1/api/board/' + this.boardName + "/" + this.boardIdx,{
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        boardTitle: this.contentTitle,
        boardContent:boardContent
        })
        .then(() =>{
          alert("수정되었습니다.");
          this.$router.push("/board/" + this.boardName);
        })
        .catch(() =>{
          alert("실패, 관리자 문의");
        })
    }
  }
}
</script>

<style>
  .ck-editor__editable{height:300px;}
  .submitBtn{float:right;}
</style>