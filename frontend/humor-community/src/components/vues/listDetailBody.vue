<template>
  <div class="listDetailBody">
      <div class="body">
          <h2>게시판</h2>
          <div>
            <b-table hover :items="contentList" :fields="fields" @row-clicked="rowClick"></b-table>
          </div>
          <b-button v-if="userEmail != null" type="button" variant="primary" @click="link">글쓰기</b-button>
      </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  beforeMount(){
     axios.get("http://localhost:8081/v1/api/board/" + this.boardName)
      .then(res =>{
        this.contentList = res.data;
      })
      .catch(err =>{
        alert("fail");
        console.log(err);
      })
  },
  methods:{
    link:function(){
      this.$router.push("/board/" + this.boardName + "/write");
    },
    rowClick(item){
      this.$router.push({
        path:'/board/'+ this.boardName + '/' + item.boardIdx
        });
    }
  },
  data(){
    return{
      userEmail : window.sessionStorage.getItem("userEmail"),
      boardName : this.$route.params.boardName,
      contentList : [],
      fields:[
        { key:'boardIdx', label:'인덱스'},
        { key: 'boardContentTitle', label: '제목' },
        { key: 'boardContentWriter',label: '글쓴이'},
        { key: 'boardViewCount', label: '조회수'},
        { key: 'boardContentBegin', label: '시간'}
      ],
      items:[
      ]
    }
  }
}
</script>
<style>
.board-body {
  width:80vw;
  margin-left:15vw;
  margin-top:2vw;
  margin-bottom:2vw;
}
</style>