<template>
  <div class="header">
    <div>
      <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand href="#">NavBar</b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="#">Link</b-nav-item>
            <b-nav-item href="#" disabled>Disabled</b-nav-item>
          </b-navbar-nav>
          <!-- Right aligned nav items -->
        </b-collapse>
        <div>
          <b-button v-if=storage.getItem('userEmail') variant="light" v-b-modal.modal-1>로그인</b-button>
          <!-- 로그인 modal S -->
          <b-modal id="modal-1" title="로그인" hide-footer="true" hide-header="true">
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
const ai = axios.create({
  baseURL:"http:localhost:8081/v1/api"
});
export default {
  data:function(){
    return{
      userEmail:'',
      userPassword:'',
      wrongInfo:false
    }
  },
  methods:{
    login:function(){
      var form = new FormData();
      form.append('userEmail',this.userEmail);
      form.append('userPassword',this.userPassword);
      ai.post('/user/login',form)
      .then(res => {
        console.log(res);
        storage.setItem("X-AUTH-TOKEN",res.headers["token"]);
        storage.setItem("userEmail",res.headers["userEmail"]);
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
</style>