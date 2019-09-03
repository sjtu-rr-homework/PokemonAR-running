<template>
    <div>
        <div class="text-center">
            <div class="input-group p-1 text-center">
                <input type="text" class="form-control col-4 m-auto" placeholder="用户名"
                        v-model="username"/>
            </div>
            <div class="input-group p-1">
                <input type="password" class="form-control col-4 m-auto" placeholder="密码"
                       v-model="password"/>
            </div>
            <div class="input-group pt-3">
                <label class="col-4 m-auto">
                    <input type="checkbox"/>
                    记住我
                </label>
            </div>
            <div class="input-group p-1 mt-2">
                <button class="btn btn-outline-primary btn-block col-4 m-auto" v-on:click="submitForm()">
                    {{ submitText }}
                </button>
            </div>
        </div>
    </div>
</template>

<script>
    import * as login from '@/js/login.js';

    export default {
        name: 'LoginForm',
        data: function () {
            return {
                username: '',
                password: '',
                message: ''
            };
        },
        computed: {
            submitText: function () {
                return '登录';
            }
        },
        methods: {
            submitForm: function () {
                if(this.username === 'admin' && this.password === 'admin'){
                    this.$emit('login-success');
                    // redirect to the manager page
                    this.$router.push({name: 'manager'});
                } else {
                    alert('请输入正确的管理员用户名与密码');
                }
            }
        }
    }
</script>

<style scoped>

</style>
