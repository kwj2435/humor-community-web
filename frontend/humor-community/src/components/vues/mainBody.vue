<template>
  <!-- Body S -->
    <div class="body">
        <div class="body-wrapper">
            <div class="dandan">
                <h5><a href="/board/dandan">단단한유머</a></h5>
                <b-table hover :items="dandanBoard" :fields="fields" @row-clicked="rowClick" thead-class="hidden_header"></b-table>
            </div>
            <div class="dandan-right">
                <div class="reading">
                    <h5><a href="/board/reading">읽을거리 판</a></h5>
                    <b-table hover :items="readingBoard" :fields="fields" @row-clicked="rowClick" thead-class="hidden_header"></b-table>
                </div>
                <div class="exercise">
                    <h5><a href="/board/exercise">운동 판</a></h5>
                    <b-table hover :items="exerciseBoard" :fields="fields" @row-clicked="rowClick" thead-class="hidden_header"></b-table>
                </div>
            </div>
            <div class="it">
                <h5><a href="/board/it">IT/프로그래밍 판</a></h5>
                    <b-table hover :items="itBoard" :fields="fields" @row-clicked="rowClick" thead-class="hidden_header"></b-table>
            </div>
            <div class="consulting">
                <h5><a href="/board/consulting">고민상담 판</a></h5>
                <b-table hover :items="consultingBoard" :fields="fields" @row-clicked="rowClick" thead-class="hidden_header"></b-table>
            </div>
        </div>
    </div>
    <!-- Body E -->
</template>

<script>
import axios from 'axios'
export default {
    data() {
    return {
      fields:[
        { 
            key:'boardContentTitle', label:'제목',
        },
      ],
      items: [
        { boardContentTitle: 40 }
      ],
      dandanBoard : [],
      readingBoard :[],
      exerciseBoard : [],
      itBoard : [],
      consultingBoard :[]
    }
  },
  beforeMount(){
      this.getBoardContentList('dandan',13),
      this.getBoardContentList('reading',6),
      this.getBoardContentList('exercise',6),
      this.getBoardContentList('it',9),
      this.getBoardContentList('consulting',9)
  },
  methods:{
      getBoardContentList:function(boardName,limit){
          axios.get("http://localhost:8081/v1/api/board/" + boardName + "/list",{params: {
      limitNum: limit
      }
    })
      .then(res =>{
          if(boardName == "dandan"){ this.dandanBoard = res.data;}
          else if(boardName == "reading"){ this.readingBoard = res.data;}
          else if(boardName == "exercise"){ 
              console.log(res);this.exerciseBoard = res.data;
              console.log(this.exerciseBoard);
              }
          else if(boardName == "it"){ this.itBoard = res.data;}
          else if(boardName == "consulting"){ this.consultingBoard = res.data;}
      })
    },
    rowClick(item){
        console.log(item);
        this.$router.push({
        path:'/board/'+ item.boardName + '/' + item.boardIdx
    });
    }
  }
}
</script>

<style>
.dandan{
    width: 30vw;
    float:left;
}
.dandan-right{
    width:30vw;
    float:left;
    margin-left:3vw;
}
.it{
    display:inline-block;
    width: 30vw;
}
.consulting{
    display:inline-block;
    width:30vw;
    margin-left:3vw;
}
.body {
  width:70vw;
  margin-bottom:2vw;
  background-color: white;
  margin-top:-0px;
  padding-top:8vh;
  padding-bottom: 3vh;
  margin-left:14vw;
}
.body-wrapper{
    margin-left:3.5vw;
}
tr { line-height: 8px;}
.hidden_header{
    display:none
}
td{
    cursor: pointer;
}
</style>