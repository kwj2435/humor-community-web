<template>
    <div class="signup">
        <main-header></main-header>
        <div class="body">
            <div class="signup-form">
                <form v-on:submit.prevent="signup">
                    <h2>회원가입</h2>
                    <p>Please fill in this form to create an account!</p>
                    <hr>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input type="text" v-model="userNickname" class="form-control" name="username" placeholder="Username" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-paper-plane"></i></span>
                            <input type="email" v-model="userEmail" class="form-control" name="email" placeholder="Email Address" required="required">
                            <b-button @click="checkEmail">중복확인</b-button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input type="password" v-model="userPassword" class="form-control" name="password" placeholder="Password" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-lock"></i>
                                <i class="fa fa-check"></i>
                            </span>
                            <input type="password" v-model="checkUserPassword" class="form-control" name="confirm_password" placeholder="Confirm Password" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg">가입</button>
                    </div>
                </form>
            <div class="text-center">이미 아이디가 있으신가요? <a v-b-modal.modal-1 href="#">Login here</a></div>
            </div>
        </div>
        <main-footer></main-footer>
    </div>
</template>
<script>
import header from "../../components/commons/header.vue"
import footer from "../../components/commons/footer.vue"
import axios from 'axios'

export default {
     components: {
    'main-header':header,
    'main-footer':footer
  },
  data :function(){
    return {
        userEmail:'',
        userPassword:'',
        checkUserPassword:'',
        userNickname:'',
        emailCheck : false,
    }
  },
  methods:{
      checkEmail:function(){
          if(this.userEmail.length <6){ 
              alert("Email을 다시 입력해주세요.");
              return;
          }
          axios.get('http://localhost:8081/v1/api/user/emailCheck',{
              params:{
                  userEmail:this.userEmail
              }
          })
          .then(res =>{
              if(res.data.result == 0){ 
                  this.emailCheck = true;
                  alert("사용가능한 Email 입니다!");
              }else{
                  alert("이미 존재하는 Email 입니다.");
              }
          })
          .catch(()=>{
              console.log("error");
          })
      },
      signup:function(){
          var form = new FormData();

          if(this.emailCheck == false){
              alert("Email 중복검사 필요!");
              return;
          }else if(this.userEmail.length < 6){
              alert("Email을 다시 입력해주세요!");
              return;
          }else if(this.userPassword.length < 8){
              alert("패스워드의 길이는 9자 이상입니다.");
              return;
          }else if(this.userPassword != this.checkUserPassword){
              alert("패스워드가 일치하지 않습니다.");
              return;
          }

          form.append('userNickname',this.userNickname);
          form.append('userEmail',this.userEmail);
          form.append('userPassword',this.userPassword);
          axios.post('http://localhost:8081/v1/api/user/signup'
          ,form)
            .then(res =>{
                console.log(res);
                this.$router.push('/');
            })
            .catch(err =>{
                console.log(err);
            })
      }
  }  
}
</script>
<style>
.body {
  width:80vw;
  margin-left:10vw;
  margin-top:2vw;
  margin-bottom:2vw;
}
.form-control, .form-control:focus, .input-group-addon {
	border-color: #e1e1e1;
}
.form-control, .btn {        
    border-radius: 3px;
}
.signup-form {
    width: 390px;
    margin: 0 auto;
    padding: 30px 0;
}
.signup-form form {
    color: #999;
    border-radius: 3px;
    margin-bottom: 15px;
    background: #fff;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.3);
    padding: 30px;
}
.signup-form h2 {
    color: #333;
    font-weight: bold;
    margin-top: 0;
}
.signup-form hr {
    margin: 0 -30px 20px;
}
.signup-form .form-group {
    margin-bottom: 20px;
}
.signup-form label {
    font-weight: normal;
    font-size: 13px;
}
</style>