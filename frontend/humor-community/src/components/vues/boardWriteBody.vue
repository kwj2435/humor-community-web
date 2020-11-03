<template>
  <div class="body">
      <h2>게시판 글쓰기</h2>
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
          <button type="submit" class="btn btn-primary submitBtn">등록</button>
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
  data(){
    return{
      editor : classicEditor,
      contentTitle : '',
      contentWriter : window.sessionStorage.getItem("userEmail"),
      contentFile : null,
      loginCheck : false,
      editorData : '<p>내용을 입력해주세요.</p>',
      editorConfig:{
        height:'500px',
        language:'ko',
        extraPlugins: [this.uploader]
      },
      boardName : this.$route.params.boardName,
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
    contentSubmit:function(){
      var form = new FormData();
      form.append('boardContentTitle',this.contentTitle);
      form.append('boardContent',this.editorData);
      form.append('boardContentWriter',this.contentWriter);
      form.append('boardFile',this.contentFile);

      axios.post('http://localhost:8081/v1/api/board/' + this.boardName,form,{
        headers: {
          'Content-Type': 'multipart/form-data'
        }
        })
        .then(res =>{
          
          console.log(res);
          console.log(this.boardName);
          alert("등록되었습니다.");
          this.$router.push("/board/" + this.boardName);
        })
        .catch(err =>{
          alert("fail");
          console.log(err);
        })
    }
  }
}
</script>

<style>
  .ck-editor__editable{height:300px;}
  .submitBtn{float:right;}
</style>