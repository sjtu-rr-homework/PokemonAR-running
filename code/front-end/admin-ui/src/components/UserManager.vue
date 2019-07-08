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
                v-on:click="showUserDetails(user.uid)">
                <td>{{ user.username }}</td>
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
                <UserDetails v-bind:user="detailedUser" v-on:ban="detailedUser.banned=true"
                             v-on:unban="detailedUser.banned=false"></UserDetails>
            </div>
        </div>
    </div>
</template>

<script>
    import UserDetails from '@/components/UserDetails.vue'

    export default {
        name: 'UserManager',
        components: {
            UserDetails
        },
        data: function () {
            return {
                users: [
                    {username: 'Alice', uid: 1},
                    {username: 'Bob', uid: 2}
                ],
                detailed: false,
                detailedUser: {},
                // TODO: temp, should only get one user
                userDetails: [
                    {
                        username: 'Alice',
                        uid: 1,
                        level: 3,
                        exp: 168,
                        upgradeExp: 300,
                        banned: false,
                        runHistory: [
                            // should use local time
                            // course length (meters)
                            {
                                startTime: '2019.7.1 Fri 09:06:32',
                                endTime: '2019.7.1 Fri 09:16:01',
                                courseLength: 2331,
                                petCaptured: '杰尼龟'
                            },
                            {
                                startTime: '2019.7.2 Fri 08:50:16',
                                endTime: '2019.7.2 Fri 09:00:05',
                                courseLength: 2368,
                                petCaptured: '小火龙'
                            }
                        ],
                        pets: [
                            {
                                name: '杰尼龟',
                                id: 1,
                                timeCaptured: '2019.7.1 Fri 09:16:01',
                                level: 10,
                                exp: 592,
                                upgradeExp: 1000,
                                abilities: {
                                    stamina: 15,
                                    maxStamina: 15,
                                    offense: 8,
                                    defense: 8,
                                    skills: [
                                        {name: '撞击', mastery: 3},
                                        {name: '甩尾', mastery: 2},
                                        {name: '泡泡', mastery: 1}
                                    ]
                                }
                            },
                            {
                                name: '小火龙',
                                id: 2,
                                timeCaptured: '2019.7.2 Fri 09:00:05',
                                level: 8,
                                exp: 12,
                                upgradeExp: 800,
                                abilities: {
                                    stamina: 15,
                                    maxStamina: 16,
                                    offense: 8,
                                    defense: 6,
                                    skills: [
                                        {name: '抓', mastery: 2},
                                        {name: '火苗', mastery: 1}
                                    ]
                                }
                            }
                        ]
                    },
                    {
                        username: 'Bob',
                        uid: 1,
                        level: 2,
                        exp: 12,
                        upgradeExp: 200,
                        banned: true,
                        runHistory: [
                            // should use local time
                            // course length (meters)
                            {
                                startTime: '2019.7.1 Fri 09:06:32',
                                endTime: '2019.7.1 Fri 09:07:01',
                                courseLength: 4000,
                                petCaptured: '喷火龙'
                            }
                        ],
                        pets: [
                            {
                                name: '喷火龙',
                                id: 1,
                                timeCaptured: '2019.7.1 Fri 09:07:01',
                                level: 98,
                                exp: 149999,
                                upgradeExp: 150000,
                                abilities: {
                                    stamina: 86,
                                    maxStamina: 86,
                                    offense: 70,
                                    defense: 52,
                                    skills: [
                                        {name: '龙之怒', mastery: 5},
                                        {name: '火旋涡', mastery: 5}
                                    ]
                                }
                            }
                        ]
                    }
                ]
            }
        },
        methods: {
            showUserDetails: function (uid) {
                // this.requestUserDetails(uid);
                this.detailedUser = this.userDetails[uid - 1];
                this.detailed = true;
            },
            hideUserDetails: function () {
                this.detailed = false;
            }
        }
    }
</script>

<style scoped>

</style>
