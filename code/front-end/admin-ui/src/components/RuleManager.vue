<template>
    <div>
        <div v-if="modifierOn">
            <button class="btn btn-block btn-outline-success" v-on:click="closeModifier()">完成配置</button>
            <div class="h5 p-3">基本配置</div>
            <div class="row bg-light">
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend col-4">总里程数（m）：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.mileageGoal"/>
                </div>
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend offset-2 col-6">最低合法配速（m/s）：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.minSpeed"/>
                </div>
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend offset-2 col-6">最高合法配速（m/s）：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.maxSpeed"/>
                </div>
            </div>
            <div class="row">
                <div class="col-4 p-2">路线范围（经度）：</div>
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend col-4">最小经度：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.minLongitude"/>
                </div>
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend col-4">最大经度：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.maxLongitude"/>
                </div>
            </div>
            <div class="row">
                <div class="col-4 p-2">路线范围（纬度）：</div>
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend col-4">最小纬度：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.minLatitude"/>
                </div>
                <div class="col-4 p-2 input-group row">
                    <span class="input-group-prepend col-4">最大纬度：</span>
                    <input class="form-control col-4" type="number" v-model.number="modifier.maxLatitude"/>
                </div>
            </div>
            <hr/>
            <!-- TODO: Should be displayed on a map -->
            <div class="h5 p-3">必经点配置</div>
            <div v-for="(cp, index) in checkPoints" v-bind:key="index">
                <span>{{index+1}}：经度={{cp.longitude}}，纬度={{cp.latitude}}</span>
            </div>
        </div>
        <div v-else>
            <button class="btn btn-block btn-outline-primary" v-on:click="openModifier()">修改规则</button>
            <div class="h5 p-3">基本配置</div>
            <div class="row bg-light">
                <div class="col-4 p-2">总里程数（m）：{{mileageGoal}}</div>
                <div class="col-4 p-2">合法配速（m/s）：({{minSpeed}}, {{maxSpeed}})</div>
            </div>
            <div class="row">
                <div class="col-4 p-2">路线范围：</div>
                <div class="col-4 p-2">经度：{{minLongitude}} - {{maxLongitude}}</div>
                <div class="col-4 p-2">纬度：{{minLatitude}} - {{maxLatitude}}</div>
            </div>
            <hr/>
            <!-- TODO: Should be displayed on a map -->
            <div class="h5 p-3">必经点配置</div>
            <div v-for="(cp, index) in checkPoints" v-bind:key="index">
                <span>{{index+1}}：经度={{cp.longitude}}，纬度={{cp.latitude}}</span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'RuleManager',
        data: function () {
            return {
                modifierOn: false,
                modifier: {
                    mileageGoal: 80000,
                    minSpeed: 1.8,
                    maxSpeed: 6,
                    checkPoints: [
                        // should be displayed and modified on a map
                        {longitude: 131.100, latitude: 32.001},
                        {longitude: 131.101, latitude: 32.010},
                        {longitude: 131.101, latitude: 32.005},
                        {longitude: 131.085, latitude: 31.999},
                        {longitude: 131.062, latitude: 31.997}
                    ],
                    // should be displayed and modified on a map
                    minLongitude: 131.051,
                    maxLongitude: 131.105,
                    minLatitude: 31.996,
                    maxLatitude: 32.011
                },
                mileageGoal: 80000,
                minSpeed: 1.8,
                maxSpeed: 6,
                checkPoints: [
                    // should be displayed and modified on a map
                    {longitude: 131.100, latitude: 32.001},
                    {longitude: 131.101, latitude: 32.010},
                    {longitude: 131.101, latitude: 32.005},
                    {longitude: 131.085, latitude: 31.999},
                    {longitude: 131.062, latitude: 31.997}
                ],
                // should be displayed and modified on a map
                minLongitude: 131.051,
                maxLongitude: 131.105,
                minLatitude: 31.996,
                maxLatitude: 32.011
            };
        },
        methods: {
            openModifier: function () {
                this.modifierOn = true;
            },
            closeModifier: function () {
                this.modifierOn = false;
            }
        }
    }
</script>

<style scoped>

</style>
