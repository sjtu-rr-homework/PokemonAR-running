<template>
    <div>
        <div v-if="modifierOn">
            <div class="row col-10 offset-1">
                <button class="btn btn-block btn-outline-success col-5" v-on:click="submitModification()">提交修改</button>
                <button class="btn btn-block btn-outline-secondary col-5 offset-2" v-on:click="cancelModification()">放弃修改</button>
            </div>
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
            <div class="h5 p-3">必经点预设</div>
            <div v-if="markers.length<=0">（暂无预设必经点）</div>
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
            <div class="h5 p-3">必经点预设</div>
            <div v-if="markers.length<=0">（暂无预设必经点）</div>
        </div>
        <div v-if="modifierOn">右键新建标记，左键拖动标记，左键双击删除标记</div>
        <div v-else>可以点击上方“修改规则”按钮进行设置</div>
        <div class="amap-wrapper m-auto pt-3">
            <el-amap class="amap-box" :vid="'amap-vue'" :amap-manager="amapManager" :zoom="zoom" :center="center"
                     :events="mapEvents">
                <div v-if="modifierOn">
                    <el-amap-marker v-for="(marker, index) in modifier.markers" :position="marker.position"
                                    :events="marker.events" :visible="marker.visible" :draggable="marker.draggable"
                                    :vid="index" :key="index"></el-amap-marker>
                </div>
                <div v-else>
                    <el-amap-marker v-for="(marker, index) in markers" :position="marker.position"
                                    :events="marker.events" :visible="marker.visible" :draggable="marker.draggable"
                                    :vid="index" :key="index"></el-amap-marker>
                </div>
            </el-amap>
        </div>
    </div>
</template>

<script>
    import * as VueAMap from "vue-amap";

    export default {
        name: 'RuleManager',
        created: function () {
            this.amapManager = new VueAMap.AMapManager();
        },
        mounted: function () {
            this.requestFlags();
        },
        data: function () {
            return {
                amapManager: null,
                zoom: 15,
                center: [121.439054, 31.025713],
                mapEvents: {
                    rightclick: (e) => {
                        if(this.modifierOn){
                            this.addFlag(e.lnglat.lng, e.lnglat.lat);
                        }
                    }
                },
                markers: [],
                nextFlagID: 0,
                modifierOn: false,
                modifier: {
                    mileageGoal: 80000,
                    minSpeed: 1.8,
                    maxSpeed: 6,
                    markers: [],
                    // should be displayed and modified on a map
                    minLongitude: 131.051,
                    maxLongitude: 131.105,
                    minLatitude: 31.996,
                    maxLatitude: 32.011
                },
                mileageGoal: 80000,
                minSpeed: 1.8,
                maxSpeed: 6,
                // should be displayed and modified on a map
                minLongitude: 131.051,
                maxLongitude: 131.105,
                minLatitude: 31.996,
                maxLatitude: 32.011
            };
        },
        methods: {
            deepCopy: function (o) {
                if (o instanceof Array) {  //先判断Array
                    let n = [];
                    for (let i = 0; i < o.length; ++i) {
                        n[i] = this.deepCopy(o[i]);
                    }
                    return n;

                } else if (o instanceof Object) {
                    let n = {};
                    for (let i in o) {
                        n[i] = this.deepCopy(o[i]);
                    }
                    return n;
                } else {
                    return o;
                }
            },
            openModifier: function () {
                // deep copy
                console.log(this.markers);
                this.modifier.markers = this.deepCopy(this.markers);
                console.log(this.modifier.markers);
                for(let i = 0; i < this.modifier.markers.length; i++){
                    this.modifier.markers[i].draggable = true;
                }
                this.modifierOn = true;
            },
            closeModifier: function () {
                this.modifierOn = false;
            },
            getMapCenter: function () {
                return this.amapManager.getMap().getCenter();
            },
            addFlag: function (lng, lat) {
                let flag = {
                    id: this.nextFlagID++,
                    position: [lng, lat],
                    events: {
                        dblclick: (e) => {
                            if(!this.modifierOn){
                                return;
                            }
                            let del = confirm('是否删除此标记点？');
                            if(del){
                                this.removeFlag(flag.id);
                            }
                        }/*,
                        dragend: (e) => {
                            // update lnglat in vue model
                            flag.position[0] = e.target.getPosition().getLng();
                            flag.position[1] = e.target.getPosition().getLat();
                        }*/
                    },
                    visible: true,
                    draggable: true
                };
                this.modifier.markers.push(flag);
            },
            removeFlag: function (flagID) {
                for(let i = 0; i < this.markers.length; i++){
                    if(this.markers[i].id === flagID){
                        this.markers.splice(i, 1);
                        break;
                    }
                }
            },
            getModifiedFlags: function () {
                let flags = [];
                for(let i = 0; i < this.modifier.markers.length; i++){
                    flags.push({
                        lng: this.modifier.markers[i].position[0],
                        lat: this.modifier.markers[i].position[1]
                    });
                }
                return flags;
            },
            requestFlags: function () {
                this.$http.get('api/admin/rule/flags')
                    .then((resp) => {
                        this.markers = [];
                        this.nextFlagID = 0;
                        for(let i = 0; i < resp.data.length; i++){
                            let lng = resp.data[i].lng;
                            let lat = resp.data[i].lat;
                            let flag = {
                                id: this.nextFlagID++,
                                position: [lng, lat],
                                events: {
                                    dblclick: (e) => {
                                        if(!this.modifierOn){
                                            return;
                                        }
                                        let del = confirm('是否删除此标记点？');
                                        if(del){
                                            this.removeFlag(flag.id);
                                        }
                                    }/*,
                                    dragend: (e) => {
                                        // update lnglat in vue model
                                        flag.position[0] = e.target.getPosition().getLng();
                                        flag.position[1] = e.target.getPosition().getLat();
                                    }*/
                                },
                                visible: true,
                                // default not draggable
                                draggable: false
                            };
                            this.markers.push(flag);
                        }
                    }, () => {
                        alert('获取预设点位失败');
                    });
            },
            modifyFlags: function () {
                this.$http.put('api/admin/rule/flags', this.getModifiedFlags())
                    .then((resp) => {
                        this.closeModifier();
                        this.requestFlags();
                    }, () => {
                        alert('提交预设点位失败，请检查网络并重试，或联系服务器管理员');
                    });
            },
            submitModification: function () {
                this.modifyFlags();
            },
            cancelModification: function () {
                this.closeModifier();
            }
        }
    }
</script>

<style scoped>
    .amap-wrapper {
        width: 1200px;
        height: 800px;
    }
</style>
