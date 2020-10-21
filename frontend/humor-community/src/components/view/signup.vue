<template>
    <div class="signup">
        <main-header></main-header>
        <div class="body">
            <div class="signup-form">
                <form action="/examples/actions/confirmation.php" method="post">
                    <h2>회원가입</h2>
                    <p>Please fill in this form to create an account!</p>
                    <hr>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input type="text" class="form-control" name="username" placeholder="Username" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-paper-plane"></i></span>
                            <input type="email" class="form-control" name="email" placeholder="Email Address" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input type="text" class="form-control" name="password" placeholder="Password" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-lock"></i>
                                <i class="fa fa-check"></i>
                            </span>
                            <input type="text" class="form-control" name="confirm_password" placeholder="Confirm Password" required="required">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg">가입</button>
                    </div>
                </form>
            <div class="text-center">이미 아이디가 있으신가요? <a href="#">Login here</a></div>
            </div>
        </div>
        <main-footer></main-footer>
    </div>
</template>
<script>
import header from "../../components/commons/header.vue"
import footer from "../../components/commons/footer.vue"

export default {
     components: {
    'main-header':header,
    'main-footer':footer
  },  
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