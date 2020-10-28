<template>
  <!-- Body S -->
    <div class="body">
        <div class="body-wrapper">
            <div class="dandan">
                <h2><a href="/board/dandan">단단한유머</a></h2>
                <table class="table">
                <tbody>
                    <tr v-for="(i,item) in items" :key="item">
                    <td>{{i.age}}</td>
                    </tr>
                </tbody>
                </table>
            </div>
            <div class="table23">
                <div class="reading">
                    <h2><a href="/board/reading">읽을거리 판</a></h2>
                    <table class="table">
                    <tbody>
                        <tr v-for="(i,item1) in items" :key="item1">
                        <td>{{i.age}}</td>
                        </tr>
                    </tbody>
                    </table>
                </div>
                <div class="excercise">
                    <h2><a href="/board/excercise">운동 판</a></h2>
                    <table class="table">
                    <tbody>
                        <tr v-for="(i,item2) in items" :key="item2">
                        <td>{{i.age}}</td>
                        </tr>
                    </tbody>
                    </table>
                </div>
            </div>
            <div class="it">
                <h2><a href="/board/it">IT/프로그래밍 판</a></h2>
                <table class="table">
                <tbody>
                    <tr v-for="(i,item3) in items" :key="item3">
                    <td>{{i.age}}</td>
                    </tr>
                </tbody>
                </table>
            </div>
            <div class="consulting">
                <h2><a href="/board/consulting">고민상담 판</a></h2>
                <table class="table">
                <tbody>
                    <tr v-for="(i,item4) in items" :key="item4">
                    <td>{{i.age}}</td>
                    </tr>
                </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Body E -->
</template>

<script>
export default {
    data() {
    return {
      fields:['test'],
      items: [
        { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
        { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
        { age: 38, first_name: 'Jami', last_name: 'Carney' }
      ]
    }
  }
}
</script>

<style>
.body {
  width:80vw;
  margin-bottom:2vw;
  background-color: white;
  margin-top:-0px;
  padding-top:5vh;
}
.body-wrapper{
    margin-left:7vw;
}
.dandan {width:32.5vw;height:30%;float:left;}
.table23{
    width:32.5vw;
    height:30%;
    float:left;
    margin-left:20px;
}
.reading{width:32.5vw;}
.excercise{width:32.5vw;}
.it{
    width:32.5vw;
    display:inline-block;
    clear:both;
}
.consulting{
    width:32.5vw;
    display:inline-block;
    clear:both;
    margin-left:20px;
}
</style>