<template>
  <div class="header">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand href="/"><h4>DanDan.net</h4></b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse topnav" is-nav>
          <b-navbar-nav>
            <b-nav-item href="/board/dandan">단단한유머</b-nav-item>
            <b-nav-item href="/board/reading">읽을거리 판</b-nav-item>
            <b-nav-item href="/board/exercise">운동 판</b-nav-item>
            <b-nav-item href="/board/it">IT/프로그래밍 판</b-nav-item>
             <b-nav-item href="/board/consulting">고민상담 판</b-nav-item>
          </b-navbar-nav>
          <!-- Right aligned nav items -->
        </b-collapse>
        <div>
          <div v-if="jwtUserEmail == null">
            <b-button  variant="light" v-b-modal.modal-1>로그인</b-button>
          </div>
          <div v-else>
            <p class="welcom">{{jwtUserNickName}}님 안녕하세요.</p>
            <b-button variant="light" @click="logout()">로그아웃</b-button>
          </div>
          <!-- 로그인 modal S -->
          <b-modal id="modal-1" title="로그인" hide-footer hide-header>
            <form v-on:submit.prevent="login">
                <h3>로그인</h3>
                <div class="form-group">
                    <label>Email address</label>
                    <input v-model="userEmail" type="email" class="form-control form-control-lg" />
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input v-model="userPassword" type="password" class="form-control form-control-lg" />
                </div>
                <div v-if="wrongInfo" class="form-group"><span class="wrongInfo">아이디 또는 비밀번호가 잘못 되었습니다.</span></div>
                <button type="submit" class="btn btn-dark btn-lg btn-block" v-on:click="login()">Sign In</button>
                <p class="forgot-password text-right mt-2 mb-4">
                    <router-link to="/forgot-password">Forgot password ?</router-link>
                </p>
                <span>아직 회원이 아니신가요?</span><a href="/signup">회원가입</a>
            </form>
          </b-modal>
          <!-- 로그인 modal E -->
        </div>
      </b-navbar>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const storage = window.sessionStorage;
export default {
  created(){
    document.title="DanDan.net";
  },
  data:function(){
    return{
      userEmail:'',
      userPassword:'',
      wrongInfo:false,
      jwt:window.sessionStorage.getItem("X-AUTH-TOKEN"),
      jwtUserEmail:window.sessionStorage.getItem("userEmail"),
      jwtUserNickName:window.sessionStorage.getItem("userNickname"),
    }
  },
  ready(){
    this.jwt = window.sessionStorage.getItem("X-AUTH-TOKEN");
    console.log(this.jwt);
  },
  methods:{
    logout:function(){
      window.sessionStorage.removeItem("X-AUTH-TOKEN");
      window.sessionStorage.removeItem("userEmail");
      alert("로그아웃 되었습니다.")
      this.$router.go(this.$router.currentRouter);
    },
    login:function(){
      var form = new FormData();
      form.append('userEmail',this.userEmail);
      form.append('userPassword',this.userPassword);
      axios.post('http://localhost:8081/v1/api/user/login',form)
      .then(res => {
        console.log(res);
        storage.setItem("X-AUTH-TOKEN",res.data.token);
        storage.setItem("userEmail",res.data.userInfo.userEmail);
        storage.setItem("userNickname",res.data.userInfo.userNickname);
        location.reload();
      })
      .catch(error => {
        console.log(error);
        this.wrongInfo = true;
      })  
    }
  }
}
</script>

<style>
.wrongInfo{
  color:red;
}
.welcom{
  display:inline-block;
  padding-top:1vh;
  padding-right:1vw;
}
.navbar{
  padding-left:10vw;
  padding-right:5vw;
}
.bg-info{
  background-color:#3c6cd9 !important;
}
</style>