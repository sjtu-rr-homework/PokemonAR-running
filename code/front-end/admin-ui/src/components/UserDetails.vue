<template>
    <div class="p-3">
        <div class="bg-light p-2 row">
            <span class="col-3">{{user.username}} (UID: {{user.uid}})</span>
            <span class="col-5">Lv. {{user.level}} | Exp: {{user.exp}} / {{user.upgradeExp}}</span>
            <button class="col-4 btn" v-bind:class="user.banned?'btn-outline-primary':'btn-outline-danger'"
                    v-on:click="switchBan()">{{banText}}</button>
        </div>
        <div v-if="user.banned" class="bg-white p-2 text-danger">
            <span>该用户处于受限制状态，无法获得奖励</span>
        </div>
        <div v-else class="bg-white p-2"></div>
        <div class="bg-light p-2 h5">跑步历史</div>
        <div class="bg-white p-2">
            <div v-for="(rec, index) in user.runHistory" v-bind:key="index" class="row">
                <span class="col-3">{{rec.startTime}}</span>
                <span class="col-1">-</span>
                <span class="col-3">{{rec.endTime}}</span>
                <span class="col-2">{{rec.courseLength}}米</span>
                <span class="col-3">捕获：{{rec.petCaptured}}</span>
            </div>
        </div>
        <div class="bg-light p-2 h5">宠物信息</div>
        <div v-for="(pet, index) in user.pets" v-bind:key="index" class="bg-white p-2">
            <div class="row">
                <span class="col-3">{{pet.name}} (id: {{pet.id}}) [ Lv. {{pet.level}} ]</span>
                <span class="col-3">Exp: {{pet.exp}} / {{pet.upgradeExp}}</span>
                <span class="col-3">体力：{{pet.abilities.stamina}} / {{pet.abilities.maxStamina}}</span>
                <span class="col-3">攻防：{{pet.abilities.offense}} | {{pet.abilities.defense}}</span>
            </div>
            <div title="查看技能" v-on:click="showPetSkills(pet)">（点此查看技能）</div>
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
                if(this.user.banned){
                    return '解禁';
                }
                return '封禁';
            }
        },
        methods: {
            showPetSkills: function (pet) {
                let msg = pet.name + '掌握的技能：';
                for(let skill of pet.abilities.skills){
                    msg += '\n' + skill.name + '（熟练度' + skill.mastery + '）';
                }
                alert(msg);
            },
            switchBan: function () {
                // TODO: temp
                if(this.user.banned){
                    this.$emit('unban');
                }else{
                    this.$emit('ban');
                }
            }
        }
    }
</script>

<style scoped>

</style>
