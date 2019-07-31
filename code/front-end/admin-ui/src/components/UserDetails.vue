<template>
    <div class="p-3">
        <div class="bg-light p-2 row">
            <span class="col-3">{{user.info.username}} (UID: {{user.info.userID}})</span>
            <span class="col-5"><!--Lv. {{user.level}} | -->Exp: {{user.info.exp}}<!-- / {{user.upgradeExp}}--></span>
            <button class="col-4 btn" v-bind:class="user.info.star<0?'btn-outline-primary':'btn-outline-danger'"
                    v-on:click="switchBan()">{{banText}}</button>
        </div>
        <div v-if="user.info.star<0" class="bg-white p-2 text-danger">
            <span>该用户处于受限制状态，无法获得奖励</span>
        </div>
        <div v-else class="bg-white p-2"></div>
        <div class="bg-light p-2 h5">约束跑信息</div>
        <div class="bg-white p-2 row">
            <div v-if="user.info.star!=1" class="col-12">（该用户无需约束跑）</div>
            <div v-else class="row col-12">
                <div class="offset-3 col-3">里程（m）：</div>
                <div class="col-3">{{user.campus.mileage}} / {{user.campus.mileageGoal}}</div>
            </div>
        </div>
        <div class="bg-light p-2 h5">跑步历史</div>
        <div class="bg-white p-2">
            <div v-for="(rec, index) in user.history" v-bind:key="index" class="row">
                <span class="col-3">{{toDate(Number(rec.startTime))}}</span>
                <span class="col-1">-</span>
                <span class="col-3">{{toDate(Number(rec.startTime)+1000*Number(rec.duration))}}</span>
                <span class="col-2">{{Number(rec.courseLength).toFixed(2)}}米</span>
                <!--span class="col-3">捕获：{{rec.petCaptured}}</span-->
            </div>
        </div>
        <div class="bg-light p-2 h5">宠物信息</div>
        <div v-for="(pet, index) in user.pets" v-bind:key="index" class="bg-white p-2">
            <div class="row">
                <span class="col-3">{{petNames[Number(pet.typeID)][Number(pet.grade)]}} (id: {{pet.petID}})</span>
                <span class="col-3">Exp: {{pet.exp}} | 卡片数：{{pet.num}}</span>
                <!--span class="col-3">体力：{{pet.abilities.stamina}} / {{pet.abilities.maxStamina}}</span>
                <span class="col-3">攻防：{{pet.abilities.offense}} | {{pet.abilities.defense}}</span-->
            </div>
            <!--div title="查看技能" v-on:click="showPetSkills(pet)">（点此查看技能）</div-->
            <hr/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'UserDetails',
        props: ['user'],
        computed: {
            banText: function () {
                if(this.user.info.star < 0){
                    return '解禁';
                }
                return '封禁';
            }
        },
        data: function () {
            return {
                petNames: [[],
                    ['','妙蛙种子','妙蛙花'],
                    ['','小火龙','火恐龙','喷火龙'],
                    ['','杰尼龟','卡米龟','水箭龟'],
                    ['','皮卡丘','雷丘'],
                    ['','可达鸭','哥达鸭']]
            };
        },
        methods: {
            showPetSkills: function (pet) {
                /*let msg = pet.name + '掌握的技能：';
                for(let skill of pet.abilities.skills){
                    msg += '\n' + skill.name + '（熟练度' + skill.mastery + '）';
                }
                alert(msg);*/
            },
            switchBan: function () {
                // TODO: temp
                if(this.user.info.star < 0){
                    this.$emit('unban');
                }else{
                    this.$emit('ban');
                }
            },
            toDate: function (millis) {
                let date = new Date();
                date.setTime(millis);
                return date;
            }
        }
    }
</script>

<style scoped>

</style>
