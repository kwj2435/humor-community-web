<template>
  <div class="listDetail">
      <div class="body">
          <h2>게시판</h2>
          <div>
            <b-table hover :items="items" :fields="fields"></b-table>
          </div>
          <b-button type="button" variant="primary" @click="link">글쓰기</b-button>
      </div>
  </div>
</template>

<script>
export default {
  methods:{
    link:function(){
      this.$router.push("/board/dandan/write");
    }
  },
  data(){
    return{
      fields:[
        {
          key: 'title',
          label: '제목'
        },
        {
          key: 'content',
          label: '내용'
        },
        {
          key: 'writer',
          label: '글쓴이'
        },
        {
          key: 'test',
          label: '시간'
        }
      ],
      items:[
          {title:"testTitle",content:"testContent",writer:"writer",test:"test"},
          {title:"testTitle",content:"testContent",writer:"writer",test:"test"}
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