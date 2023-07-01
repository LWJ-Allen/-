<template>
  <div class="container" ref="_container">
    <div class="forms-container">
      <div class="signin-signup">
        <form action="#" class="sign-in-form">
          <h2 class="title">
            登录<img src="../../assets/icon.svg" class="icon" />
          </h2>
          <div class="input-field">
            <i class="fas fa-user"></i>
            <input placeholder="用户名" v-model="loginForm.userName" />
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input
              type="password"
              placeholder="密码"
              v-model="loginForm.password"
            />
          </div>
          <div class="input-field">
            <i class="fas fa-envelope"></i>
            <input placeholder="验证码" v-model="loginForm.vertifyCode" />
          </div>
          <input value="立即登录" class="btn solid" @click="login" />
        </form>
        <form action="#" class="sign-up-form">
          <h2 class="title">
            注册<img src="../../assets/icon.svg" class="icon" />
          </h2>
          <div class="input-field">
            <i class="fas fa-user"></i>
            <input placeholder="用户名" v-model="registerForm.userName" />
          </div>
          <div class="input-field">
            <i class="fas fa-envelope"></i>
            <input placeholder="邮箱" v-model="registerForm.email" />
          </div>
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <input
              type="password"
              placeholder="密码"
              v-model="registerForm.password"
            />
          </div>
          <input value="立即注册" class="btn solid" @click="register" />
        </form>
      </div>
    </div>

    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h3>加入我们</h3>
          <p>加入云食坊吃粑粑！</p>
          <button
            class="btn transparent"
            id="sign-up-btn"
            @click="goRegister"
          >
            去注册
          </button>
        </div>
      </div>
      <div class="panel right-panel">
        <div class="content">
          <h3>已有帐号？</h3>
          <p>傻逼孙高锦</p>
          <button
            class="btn transparent"
            id="sign-in-btn"
            @click="goLogin"
          >
            去登录
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { nextTick, reactive, ref } from "vue";
import { getJoke } from "../../api/public/index";
import { setData, getData } from "../../utils/session.js";
import { useRouter } from "vue-router";
const _container = ref();
const router = useRouter();
let loginForm = reactive({
  userName: "",
  password: "",
  vertifyCode: "1919810",
});
let registerForm = reactive({
  userName: "",
  password: "",
  email: "114514",
});

async function goRegister() {
  await nextTick();
  _container.value.classList.add("sign-up-mode");
}

async function goLogin() {
  await nextTick();
  _container.value.classList.remove("sign-up-mode");
}

async function login() {
  // 建议后端使用jst生成token 此处用公共api的Id作为token
  const res = await getJoke();
  if (res.status === 200) {
    setData("userData", {
      token: res.data.Data[0].Id,
      name: res.data.Data[0].Type,
    });
  }
  // 此处调后端的登录接口 如果登录成功携带token跳到主页 登陆失败就提示登录失败，不跳转 我这里假设登陆成功
  if (getData("userData")?.token) router.push({ path: "/home" });
}

async function register() {
  // 调用注册函数
  // const res = await regisTer()
  // 如果注册成功
  // if (res.status === 200 && res.data.code === success code) 
  // alert('注册成功')
  // 跳转到登录界面
     goLogin()
  // 如果注册失败 打印失败原因 这个原因应该从服务器返回
  // else alert(res.data.msg)
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body,
input {
  font-family: "Poppins", sans-serif;
}

.container {
  position: relative;
  width: 100%;
  background-color: #fff;
  min-height: 100vh;
  overflow: hidden;
}

.forms-container {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.signin-signup {
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  left: 75%;
  width: 50%;
  transition: 1s 0.7s ease-in-out;
  display: grid;
  grid-template-columns: 1fr;
  z-index: 5;
}

form {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0rem 5rem;
  transition: all 0.2s 0.7s;
  overflow: hidden;
  grid-column: 1 / 2;
  grid-row: 1 / 2;
}

form.sign-up-form {
  opacity: 0;
  z-index: 1;
}

form.sign-in-form {
  z-index: 2;
}

.title {
  font-size: 2.2rem;
  display: flex;
  flex: 1;
  height: 60px;
  line-height: 60px;
  color: #444;
  margin-bottom: 10px;
}

.input-field {
  max-width: 380px;
  width: 100%;
  background-color: #f0f0f0;
  margin: 10px 0;
  height: 55px;
  border-radius: 55px;
  display: grid;
  grid-template-columns: 15% 85%;
  padding: 0 0.4rem;
  position: relative;
}

.input-field i {
  text-align: center;
  line-height: 55px;
  color: #acacac;
  transition: 0.5s;
  font-size: 1.1rem;
}

.input-field input {
  background: none;
  outline: none;
  border: none;
  line-height: 1;
  font-weight: 600;
  font-size: 1.1rem;
  color: #333;
}

.input-field input::placeholder {
  color: #aaa;
  font-weight: 500;
}

.social-text {
  padding: 0.7rem 0;
  font-size: 1rem;
}

.social-media {
  display: flex;
  justify-content: center;
}

.social-icon {
  height: 46px;
  width: 46px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 0.45rem;
  color: #333;
  border-radius: 50%;
  border: 1px solid #333;
  text-decoration: none;
  font-size: 1.1rem;
  transition: 0.3s;
}

.social-icon:hover {
  color: #009999;
  border-color: #009999;
}

.btn {
  width: 150px;
  background-color: #009999;
  border: none;
  outline: none;
  height: 49px;
  border-radius: 49px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 600;
  margin: 10px 0;
  cursor: pointer;
  transition: 0.5s;
  text-align: center;
}

.btn:hover {
  background-color: #4d84e2;
}

.panels-container {
  position: absolute;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}

.container:before {
  content: "";
  position: absolute;
  height: 2000px;
  width: 2000px;
  top: -10%;
  right: 48%;
  transform: translateY(-50%);
  background-image: linear-gradient(-45deg, #009999 0%, #82ee82 100%);
  transition: 1s ease-in-out;
  border-radius: 50%;
  z-index: 6;
}

.image {
  width: 100%;
  transition: transform 1s ease-in-out;
  transition-delay: 0.2s;
}

.panel {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: space-around;
  text-align: center;
  z-index: 6;
}

.left-panel {
  pointer-events: all;
  padding: 3rem 17% 2rem 12%;
}

.right-panel {
  pointer-events: none;
  padding: 3rem 12% 2rem 17%;
}

.panel .content {
  color: #fff;
  transition: transform 0.9s ease-in-out;
  transition-delay: 0.6s;
}

.panel h3 {
  font-weight: 600;
  line-height: 1;
  font-size: 1.5rem;
}

.panel p {
  font-size: 0.95rem;
  padding: 0.7rem 0;
}

.btn.transparent {
  margin: 0;
  background: none;
  border: 2px solid #fff;
  width: 130px;
  height: 41px;
  font-weight: 600;
  font-size: 0.8rem;
}

.right-panel .image,
.right-panel .content {
  transform: translateX(800px);
}

.container.sign-up-mode:before {
  transform: translate(100%, -50%);
  right: 52%;
}

.container.sign-up-mode .left-panel .image,
.container.sign-up-mode .left-panel .content {
  transform: translateX(-800px);
}

.container.sign-up-mode .signin-signup {
  left: 25%;
}

.container.sign-up-mode form.sign-up-form {
  opacity: 1;
  z-index: 2;
}

.container.sign-up-mode form.sign-in-form {
  opacity: 0;
  z-index: 1;
}

.container.sign-up-mode .right-panel .image,
.container.sign-up-mode .right-panel .content {
  transform: translateX(0%);
}

.container.sign-up-mode .left-panel {
  pointer-events: none;
}

.container.sign-up-mode .right-panel {
  pointer-events: all;
}

.icon {
  width: 60px;
  height: 100%;
}

@media (max-width: 870px) {
  .container {
    min-height: 800px;
    height: 100vh;
  }
  .signin-signup {
    width: 100%;
    top: 95%;
    transform: translate(-50%, -100%);
    transition: 1s 0.8s ease-in-out;
  }

  .signin-signup,
  .container.sign-up-mode .signin-signup {
    left: 50%;
  }

  .panels-container {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 2fr 1fr;
  }

  .panel {
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    padding: 2.5rem 8%;
    grid-column: 1 / 2;
  }

  .right-panel {
    grid-row: 3 / 4;
  }

  .left-panel {
    grid-row: 1 / 2;
  }

  .image {
    width: 200px;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.6s;
  }

  .panel .content {
    padding-right: 15%;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.8s;
  }

  .panel h3 {
    font-size: 1.2rem;
  }

  .panel p {
    font-size: 0.7rem;
    padding: 0.5rem 0;
  }

  .btn.transparent {
    width: 110px;
    height: 35px;
    font-size: 0.7rem;
  }

  .container:before {
    width: 1500px;
    height: 1500px;
    transform: translateX(-50%);
    left: 30%;
    bottom: 68%;
    right: initial;
    top: initial;
    transition: 2s ease-in-out;
  }

  .container.sign-up-mode:before {
    transform: translate(-50%, 100%);
    bottom: 32%;
    right: initial;
  }

  .container.sign-up-mode .left-panel .image,
  .container.sign-up-mode .left-panel .content {
    transform: translateY(-300px);
  }

  .container.sign-up-mode .right-panel .image,
  .container.sign-up-mode .right-panel .content {
    transform: translateY(0px);
  }

  .right-panel .image,
  .right-panel .content {
    transform: translateY(300px);
  }

  .container.sign-up-mode .signin-signup {
    top: 5%;
    transform: translate(-50%, 0);
  }
}

@media (max-width: 570px) {
  form {
    padding: 0 1.5rem;
  }

  .image {
    display: none;
  }
  .panel .content {
    padding: 0.5rem 1rem;
  }
  .container {
    padding: 1.5rem;
  }

  .container:before {
    bottom: 72%;
    left: 50%;
  }

  .container.sign-up-mode:before {
    bottom: 28%;
    left: 50%;
  }
}
</style>
