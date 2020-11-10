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
          <ckedit :editor="editor" v-model="editorData" :config="editorConfig"/>
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
import classicEditor from '@ckeditor/ckeditor5-build-classic'
import CKEditor from '@ckeditor/ckeditor5-vue'
import UploadAdapter from '../../UploadAdapter'
import axios from 'axios'

export default {
  name:"CKEditor",
  components:{
    ckedit:CKEditor.component
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
      editor : classicEditor,
      contentTitle : '',
      contentWriter : window.sessionStorage.getItem("userNickname"),
      contentFile : null,
      loginCheck : false,
      editorData : '<p>내용을 입력해주세요.</p>',
      editorConfig:{
        height:'500px',
        language:'ko',
        extraPlugins: [this.uploader]
      },
      boardName : this.$route.params.boardName,
      boardIdx : this.$route.params.boardIdx,
      boardInfo :'',
      fileNo : 0,
      fileName : ''
    }
  },
  methods:{
    store()
    {
        // Some code
    },

    uploader(editor)
    {
        editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
            return new UploadAdapter( loader );
        };
    },
    modifyContent:function(){
      axios.put('http://localhost:8081/v1/api/board/' + this.boardName + "/" + this.boardIdx,{
        headers: {
          'Content-Type': 'multipart/form-data'
        },
        boardTitle: this.contentTitle,
        boardContent: this.editorData
        })
        .then(res =>{
          console.log(res);
          console.log(this.boardName);
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