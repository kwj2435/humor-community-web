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
          <editor height="400px" initialEditType="wysiwyg" ref="editorRef"/>
        </b-form-group>
        <b-form-group>
          <b-form-file 
          multiple
          id="files"
          ref="files" 
          placeholder="파일 첨부" 
          @change="handleUploadFile($event.target.files)">
          </b-form-file>
        </b-form-group>
        <b-form-group>
          <button type="submit" class="btn btn-primary submitBtn">등록</button>
            <div v-for="(item,index) in fileList" :key="index">
              <span :id="index">{{item.name}}<img class="deleteIcon" src="../../assets/deleteIcon.png" @click="deleteFile(index)"/></span>
            </div>
        </b-form-group>
      </b-form>
  </div>
</template>

<script>
import axios from 'axios'
import $ from 'jquery'

import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';


export default {
  components:{
    'editor':Editor
  },
  mounted(){
   // $(".wysiwyg").trigger('click');
  },
  data(){
    return{
      contentTitle : '',
      contentWriter : window.sessionStorage.getItem("userNickname"),
      contentFile : [],
      loginCheck : false,
      boardName : this.$route.params.boardName,
      fileList:[],
      fileCount:0,
    }
  },
  methods:{
    handleUploadFile:function(files){
      this.fileList[this.fileCount] = files.item(0);
      this.fileCount += 1;
      this.$forceUpdate();
    },
    contentSubmit:function(){
      var form = new FormData();
      var content = this.$refs.editorRef.invoke("getMarkdown");

      if(this.fileCount == 0){
        form.append('boardFile',null);
      }
        for(let i = 0; i < this.fileList.length; i++){
        form.append('boardFile',this.fileList[i]);
      }
      form.append('boardName',this.boardName);
      form.append('boardContentTitle',this.contentTitle);
      form.append('boardContent',content);
      form.append('boardContentWriter',this.contentWriter);
      form.append('fileGubun',1);
      axios.post('http://localhost:8081/v1/api/board/',form,{
        headers: {
          'Content-Type': 'multipart/form-data'
        }
        })
        .then(() =>{
          alert("등록되었습니다.");
          this.$router.push("/board/" + this.boardName);
        })
        .catch(err =>{
          alert("실패, 관리자 문의");
          console.log(err);
        })
    },
    deleteFile:function(index){
      this.fileList[index] = '';
      console.log(this.fileList[index]);
      $("#"+index).remove();
    }
  }
}
</script>

<style>
  .submitBtn{float:right;}
  .deleteIcon{
    width:20px;
    height:20px;
    margin-left:10px;
    cursor: pointer;
  }
</style>