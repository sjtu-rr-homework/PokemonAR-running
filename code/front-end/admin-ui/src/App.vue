<template>
    <div id="app">
        <div class="row p-3 bg-light">
            <!-- admin part -->
            <div v-if="isAdmin" class="col-2">
                <!-- click here to exit -->
                <router-link to='/login' class="text-decoration-none">
                    <button class="btn btn-implicit-dark btn-block"
                            v-on:mouseover="setExitText()"
                            v-on:mouseout="resetExitText()"
                            v-on:click="exitLogin()">{{ exitText }}</button>
                </router-link>
            </div>
            <div v-if="isAdmin&&isLogin()" class="col-2">
                <router-link to='/manager' class="text-decoration-none">
                    <button class="btn btn-implicit-dark btn-block">管理</button>
                </router-link>
            </div>

            <!-- visitor part -->
            <div v-if="isVisitor" class="col-2">
                <router-link to='/login' class="text-decoration-none">
                    <button class="btn btn-implicit-dark btn-block">登录</button>
                </router-link>
            </div>
        </div>
        <router-view v-on:login-success="doLogin()" v-bind:manager-type="isAdmin?0:-1"/>
    </div>
</template>

<script>
    import * as login from '@/js/login.js'

    export default {
        data: function () {
            return {
                userGroup: 'visitor',
                username: '',
                exitHover: false
            };
        },
        methods: {
            setExitText: function () {
                this.exitHover = true;
            },
            resetExitText: function () {
                this.exitHover = false;
            },
            doLogin: function () {
                this.userGroup = 'admin';
                login.doLogin();
            },
            exitLogin: function () {
                this.userGroup = 'visitor';
                login.exitLogin();
            },
            isLogin: function () {
                return login.isLogin();
            }
        },
        computed: {
            isVisitor: function () {
                return this.userGroup === 'visitor';
            },
            isAdmin: function () {
                return this.userGroup === 'admin';
            },
            welcomeMsg: function () {
                if(this.isAdmin){
                    return '欢迎，管理员 ' + this.username;
                }
                return '';
            },
            exitText: function () {
                return this.exitHover ? '退出登录' : this.welcomeMsg;
            }
        }
    };
</script>

<style>
</style>
