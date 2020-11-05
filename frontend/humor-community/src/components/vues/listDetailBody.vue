<template>
  <div class="listDetailBody">
      <div class="body">
          <h3 class="table-title">{{boardNameTitle}}</h3>
          <div class="table">
            {{currentPage}}
            <b-table 
              id="boardTable" 
              hover
              :per-page="perPage"
              :current-page="currentPage" 
              :items="contentList" 
              :fields="fields" 
              @row-clicked="rowClick"
              ></b-table>
            <b-button class="writerBtn" v-if="userEmail != null" type="button" variant="primary" @click="link">글쓰기</b-button>
          </div>
          <div class="pagenation">
            <b-pagination
              v-model="currentPage"
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="boardTable"
              @page-click="pageClick"
              align="center"
            ></b-pagination>
          </div>
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

     axios.get("http://localhost:8081/v1/api/board/" + this.boardName)
      .then(res =>{
        this.contentList = res.data;
      })
      .catch(err =>{
        alert("fail");
        console.log(err);
      })
  },
  updated(){
    console.log(this.currentPage)
  },
  methods:{
    link:function(){
      this.$router.push("/board/" + this.boardName + "/write");
    },
    rowClick(item){
      this.$router.push({
        path:'/board/'+ this.boardName + '/' + item.boardIdx
        });
    },
    pageClick:function(){
    }
  },
  data(){
    return{
      perPage : 15,
      currentPage : 1,
      userEmail : window.sessionStorage.getItem("userEmail"),
      boardName : this.$route.params.boardName,
      boardNameTitle : '',
      contentList : [],
      fields:[
        { key: 'boardContentTitle', label: '제목', thClass: 'boardTitle'},
        { key: 'boardContentWriter',label: '글쓴이', thClass: 'boardWriter'},
        { key: 'boardContentBegin', label: '날짜', thClass: 'boardDate',tdClass:'boardTdDate'},
        { key: 'boardViewCount', label: '조회수', thClass:'boardCount', tdClass:'boardTdCount'}
      ],
      items:[
      ]
    }
  },
  computed: {
    rows() {
      return this.contentList.length
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
.table{
  padding-left:2vw;
  padding-right:2vw;
}
.table-title{
  padding-left:2vw;
  padding-bottom: 3vw;
  font-weight: bold;
}
.boardTdDate{
  text-align: center;
}
.boardDate{
  text-align: center;
}
.boardCount{
  text-align: center;
}
.boardTdCount{
  text-align: center;
}
.boardWriter{
  text-align:center;
  width:8vw;
}
.writerBtn{
  float:right;
  margin-right:2vw;
}
</style>