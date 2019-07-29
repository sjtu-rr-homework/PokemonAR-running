<template>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>用户名</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(user, index) in users" v-bind:key="index" class="btn-implicit-primary"
                v-on:click="showUserDetails(user)">
                <td>{{ user }}</td>
            </tr>
            </tbody>
            <tfoot></tfoot>
        </table>
        <!-- Detail & mask -->
        <div v-if="detailed">
            <div class="mask"></div>
            <div class="on-mask bg-white window-md p-4">
                <button class="btn btn-outline-danger col-2 offset-10 mb-3"
                        v-on:click="hideUserDetails()">关闭</button>
                <UserDetails v-bind:user="detailedUser" v-on:ban="requestBan(detailedUser.info.username)"
                             v-on:unban="requestBan(detailedUser.info.username)"></UserDetails>
            </div>
        </div>
    </div>
</template>

<script>
    import UserDetails from '@/components/UserDetails.vue';
    import * as api from '@/js/api_prefix.js';

    export default {
        name: 'UserManager',
        components: {
            UserDetails
        },
        mounted: function () {
            this.requestUserList();
        },
        data: function () {
            return {
                users: [],
                detailed: false,
                detailedUser: {
                    info: {},
                    campus: {},
                    history: [],
                    pets: []
                }
            }
        },
        methods: {
            showUserDetails: function (username) {
                this.requestUserDetails(username);
            },
            hideUserDetails: function () {
                this.detailed = false;
            },
            requestPetInfo: function (username) {
                this.$http.get(api.petApi('user/' + username + '/getpets')
                ).then((resp) => {
                    this.detailedUser.pets = resp.data;
                    this.detailed = true;
                }, () => {
                    alert('get pet info fail');
                });
            },
            requestRunningHistory: function (username) {
                this.$http.get(api.recordApi('running/record/user/' + username)
                ).then((resp) => {
                    this.detailedUser.history = resp.data;
                    this.requestPetInfo(username);
                }, () => {
                    alert('get running history fail');
                    this.requestPetInfo(username);
                });
            },
            /*requestCampusRunningInfo: function (username) {
                this.$http.get(api.ruleApi('rule/campus/user/' + username)
                ).then((resp) => {
                    console.log(typeof resp.data[0].startTime);
                    console.log(typeof resp.data[0].duration);
                    this.detailedUser.campus = resp.data; // {mileage, mileageGoal}
                    this.requestRunningHistory(username);
                }, () => {
                    alert('get campus running info fail');
                });
            },*/
            requestUserInfo: function (username) {
                this.$http.get(api.userApi('admingetuserinfo/username/' + username)
                ).then((resp) => {
                    this.detailedUser.info = resp.data;
                    //this.requestCampusRunningInfo(username);
                    this.requestRunningHistory(username);
                }, () => {
                    alert('get user info fail');
                    this.requestRunningHistory(username);
                });
            },
            requestUserDetails: function (username) {
                this.requestUserInfo(username);
            },
            requestUserList: function () {
                this.$http.get(api.userApi('getallusername')
                ).then((resp) => {
                    this.users = resp.data;
                }, () => {
                    alert('get user list fail');
                });
            },
            requestBan: function (username) {
                this.$http.get(api.userApi('blockuser/username/' + username)
                ).then((resp) => {
                    // refresh
                    this.requestUserDetails(username);
                }, () => {
                    alert('ban/unban user fail');
                });
            }
        }
    }
</script>

<style scoped>

</style>
