<template>
  <div class="body">
      <h2>게시판 글쓰기</h2>
      <b-form @submit="contentSubmit()">
        <b-form-group>
        <b-form-input
          id="title"
          v-model="title"
          type="text"
          required
          placeholder="제목을 입력해주세요."
        ></b-form-input>
        </b-form-group>
        <b-form-group>
          <ckedit :editor="editor" v-model="editorData" :config="editorConfig"/>
        </b-form-group>
         <button type="submit" class="btn btn-primary">등록</button>
      </b-form>
     
  </div>
</template>

<script>
import classicEditor from '@ckeditor/ckeditor5-build-classic'
import CKEditor from '@ckeditor/ckeditor5-vue'

export default {
  name:"CKEditor",
  components:{
    ckedit:CKEditor.component
  },
  data(){
    return{
      editor:classicEditor,
      title:'',
      editorData:'<p>내용을 입력해주세요.</p>',
      editorConfig:{
        height:'500px',
        language:'ko'
      }
    }
  },
  methods:{
    contentSubmit:function(){
      var form = new FormData();
      axios.post('http://localhost:8081/v1/api/board/dandan',form)
        .then()
        .catch()
    }
  }
}
</script>

<style>
  .ck-editor__editable{height:300px;}
</style>