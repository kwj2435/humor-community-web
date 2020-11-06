<template>
    <div class="footer">
        <div class="footer-content">
            <ul class="footer-ul">
                <li><a href="/board/dandan">단단한 유머</a></li>
                <li><a href="/board/reading">읽을거리 판</a></li>
                <li><a href="/board/exercise">운동 판</a></li>
                <li><a href="/board/it">IT/프로그래밍 판</a></li>
                <li><a href="/board/consulting">고민상담 판</a></li>
            </ul>
            <p class="copyright">Copyright (c) UiJin Kim All Right Reserved.</p>
            <p>Contact me, dmlwls2435@naver.com  010 - 3428 - 9302</p>
        </div>
    </div>
</template>
<script>
export default {

}
</script>
<style>
    .footer-content{
        margin-left:15vw;
    }
    .footer{
        background-color: darkgrey;
        width: 100%;
        height:24vh;
    }
    .footer-ul{
        list-style:none;
        padding-left:0px;
    }
    .footer-ul > li{
        float:left;
        margin-top:1vh;
        margin-right:1vw;
        margin-bottom: 2vh;
    }
    .copyright{
        clear:both;
    }
</style>