<template>
    <div class="text-center row">
        <!-- Management Entries -->
        <div v-if="isLogin()" class="col-2">
            <button v-for="(entry, index) in entries" v-bind:key="index"
                    class="btn btn-block btn-implicit-primary p-4" v-on:click="switchEntry(index)">{{ entry }}</button>
        </div>
        <!-- Management Component -->
        <div class="col-10">
            <UserManager v-if="isUserManager"></UserManager>
            <RuleManager v-else-if="isRuleManager"></RuleManager>
            <span v-else-if="isUnLogin">请先登录。</span>
            <span v-else>请从左侧选择一项功能 :)</span>
        </div>
    </div>
</template>

<script>
    import UserManager from '@/components/UserManager.vue'
    import RuleManager from '@/components/RuleManager.vue'
    import * as login from '@/js/login.js'

    export default {
        name: 'Manager',
        components: {
            UserManager, RuleManager
        },
        props: {
            // 0 = user manager, 1 = rule manager
            managerType: Number
        },
        computed: {
            isUserManager: function () {
                return this.managerType === 0;
            },
            isRuleManager: function () {
                return this.managerType === 1;
            },
            isUnLogin: function () {
                return this.managerType === -1 || login.isLogin();
            }
        },
        data: function () {
            return {
                entries: ['用户管理', '跑步规则管理']
            };
        },
        methods: {
            switchEntry: function(index) {
                this.managerType = index;
            },
            isLogin: function () {
                return login.isLogin();
            }
        }
    }
</script>

<style scoped>

</style>
