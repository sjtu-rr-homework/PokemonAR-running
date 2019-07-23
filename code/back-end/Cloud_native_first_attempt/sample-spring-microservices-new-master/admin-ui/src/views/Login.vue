<template>
    <div class="text-center">
        <div class="p-3">
            <img class="mb-4" width="72" height="72" alt="ebook logo" src="../assets/logo.png">
            <h1 class="mb-2">登录</h1>
        </div>
        <LoginForm v-on:login-success="login()"></LoginForm>
    </div>
</template>

<script>
    import LoginForm from '@/components/LoginForm.vue'

    export default {
        name: 'Login',
        components: {
            LoginForm
        },
        methods: {
            login: function() {
                this.$emit('login-success');
            }
        }
    }
</script>
