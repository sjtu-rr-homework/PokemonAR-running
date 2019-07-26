<template>
    <div>
        <div v-if="modifierOn">
            <div class="row col-10 offset-1">
                <button class="btn btn-block btn-outline-success col-5" v-on:click="submitModification()">提交修改</button>
                <button class="btn btn-block btn-outline-secondary col-5 offset-2" v-on:click="cancelModification()">
                    {{ modificationFail ? '放弃未成功提交的修改' : '放弃修改' }}
                </button>
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
            <hr/>
            <div class="h5 p-3">跑步范围、必经点预设</div>
            <div v-if="basicRuleModifySubmitting" class="text-info">提交基本规则信息……</div>
            <div v-else-if="basicRuleModifyFail" class="text-danger">提交基本规则信息失败</div>
            <div v-if="borderModifySubmitting" class="text-info">提交跑步范围信息……</div>
            <div v-else-if="borderModifyFail" class="text-danger">提交跑步范围信息失败</div>
            <div v-if="flagModifySubmitting" class="text-info">提交必经点信息……</div>
            <div v-else-if="flagModifyFail" class="text-danger">提交必经点信息失败</div>
        </div>
        <div v-else>
            <button class="btn btn-block btn-outline-primary" v-on:click="openModifier()">修改规则</button>
            <div class="h5 p-3">基本配置</div>
            <div class="row bg-light">
                <div class="col-4 p-2">总里程数（m）：{{mileageGoal}}</div>
                <div class="col-4 p-2">合法配速（m/s）：({{minSpeed}}, {{maxSpeed}})</div>
            </div>
            <hr/>
            <div class="h5 p-3">跑步范围、必经点预设</div>
            <div v-if="gettingBasicRule" class="text-info">获取基本规则信息……</div>
            <div v-else-if="getBasicRuleFail" class="text-danger">获取基本规则信息失败</div>
            <div v-if="gettingBorder" class="text-info">获取跑步范围信息……</div>
            <div v-else-if="getBorderFail" class="text-danger">获取跑步范围信息失败</div>
            <div v-else-if="borderInfoError" class="text-warning">获取的跑步范围不合法，已重新生成默认范围</div>
            <div v-if="gettingFlags" class="text-info">获取必经点信息……</div>
            <div v-else-if="getFlagsFail" class="text-danger">获取必经点信息失败</div>
            <div v-else>
                <div v-if="markers.length<=0">（暂未预设必经点）</div>
            </div>
        </div>
        <div v-if="modifierOn">
            <div>右键新建标记，左键按下拖动标记，左键双击删除标记</div>
            <div>左键按下拖动跑步范围（多边形）或其顶点，左键点击多边形顶点以添加/删除该顶点</div>
        </div>
        <div v-else>可以点击上方“修改规则”按钮进行设置</div>
        <div class="amap-wrapper m-auto pt-3">
            <el-amap class="amap-box" :vid="'amap-vue'" :amap-manager="amapManager" :zoom="zoom" :center="center"
                     :events="mapEvents">
                <div v-if="modifierOn">
                    <el-amap-polygon :path="modifier.border.path" :events="modifier.border.events"
                                     :draggable="true" :editable="true" :strokeOpacity="0.5" :fillOpacity="0.1"></el-amap-polygon>
                    <el-amap-marker v-for="(marker, index) in modifier.markers" :position="marker.position"
                                    :events="marker.events" :visible="marker.visible" :draggable="true"
                                    :vid="index" :key="index"></el-amap-marker>
                </div>
                <div v-else>
                    <el-amap-polygon :path="border.path" :events="border.events"
                                     :draggable="false" :editable="false" :strokeOpacity="0.5" :fillOpacity="0.1"></el-amap-polygon>
                    <el-amap-marker v-for="(marker, index) in markers" :position="marker.position"
                                    :events="marker.events" :visible="marker.visible" :draggable="false"
                                    :vid="index" :key="index"></el-amap-marker>
                </div>
            </el-amap>
        </div>
    </div>
</template>

<script>
    import * as VueAMap from "vue-amap";
    import * as api from '@/js/api_prefix.js';
    var $ = require('jquery');

    export default {
        name: 'RuleManager',
        created: function () {
            this.amapManager = new VueAMap.AMapManager();
        },
        mounted: function () {
            this.requestBasicRule();
            this.requestBorder();
            this.requestFlags();
        },
        computed: {
            modifierOn: function () {
                return this.flagModifying || this.basicRuleModifying || this.borderModifying;
            },
            modificationFail: function () {
                return this.flagModifyFail || this.basicRuleModifyFail || this.borderModifyFail;
            },
            getInfoFail: function () {
                return this.getFlagsFail || this.getBasicRuleFail || this.getBorderFail;
            }
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
                // getting
                gettingFlags: false,
                gettingBasicRule: false,
                gettingBorder: false,
                // get fail
                getFlagsFail: false,
                getBasicRuleFail: false,
                getBorderFail: false,
                borderInfoError: false,
                // modifying
                flagModifying: false,
                basicRuleModifying: false,
                borderModifying: false,
                // modify submitting
                flagModifySubmitting: false,
                basicRuleModifySubmitting: false,
                borderModifySubmitting: false,
                // modify submit fail
                flagModifyFail: false,
                basicRuleModifyFail: false,
                borderModifyFail: false,
                modifier: {
                    mileageGoal: 0,
                    minSpeed: 0,
                    maxSpeed: 0,
                    markers: [],
                    border: this.newBorder([])
                },
                mileageGoal: 0,
                minSpeed: 0,
                maxSpeed: 0,
                border: this.newBorder([])
            };
        },
        methods: {
            deepCopy: function (o) {
                if (o instanceof Array) {
                    return $.extend(true, [], o);
                } else if (o instanceof Object) {
                    return $.extend(true, {}, o);
                } else {
                    return o;
                }
            },
            openModifier: function () {
                // deep copy
                this.modifier.markers = this.deepCopy(this.markers);
                this.modifier.mileageGoal = this.mileageGoal;
                this.modifier.minSpeed = this.minSpeed;
                this.modifier.maxSpeed = this.maxSpeed;
                console.log(this.border);
                this.modifier.border = this.deepCopy(this.border);
                console.log(this.modifier.border);
                // modifying flags
                this.flagModifying = true;
                this.basicRuleModifying = true;
                this.borderModifying = true;
            },
            closeModifier: function () {
                this.flagModifying = false;
                this.basicRuleModifying = false;
                this.borderModifying = false;
            },
            getMapCenter: function () {
                return this.amapManager.getMap().getCenter();
            },
            newFlag: function (lng, lat) {
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
                        },
                        dragend: (e) => {
                            // update lnglat in vue model
                            this.modifyFlagPos(flag.id, e.target.getPosition().lng, e.target.getPosition().lat);
                        }
                    },
                    visible: true
                };
                return flag;
            },
            addFlag: function (lng, lat) {
                this.modifier.markers.push(this.newFlag(lng, lat));
            },
            removeFlag: function (flagID) {
                for(let i = 0; i < this.modifier.markers.length; i++){
                    if(this.modifier.markers[i].id === flagID){
                        this.modifier.markers.splice(i, 1);
                        break;
                    }
                }
            },
            modifyFlagPos: function (flagID, lng, lat) {
                for(let i = 0; i < this.modifier.markers.length; i++){
                    if(this.modifier.markers[i].id === flagID){
                        this.modifier.markers[i].position = [lng, lat];
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
                this.gettingFlags = true;
                this.getFlagsFail = false;
                this.$http.get(api.ruleApi('admin/rule/flags'))
                    .then((resp) => {
                        this.markers = [];
                        this.nextFlagID = 0;
                        for(let i = 0; i < resp.data.length; i++){
                            let lng = resp.data[i].lng;
                            let lat = resp.data[i].lat;
                            this.markers.push(this.newFlag(lng, lat));
                        }
                        this.gettingFlags = false;
                    }, () => {
                        this.getFlagsFail = true;
                        this.gettingFlags = false;
                    });
            },
            modifyFlags: function () {
                this.flagModifySubmitting = true;
                this.flagModifyFail = false;
                this.$http.post(api.ruleApi('admin/rule/flags'), this.getModifiedFlags())
                    .then((resp) => {
                        this.flagModifying = false;
                        this.flagModifySubmitting = false;
                        this.requestFlags();
                    }, () => {
                        this.flagModifyFail = true;
                        this.flagModifySubmitting = false;
                    });
            },
            requestBasicRule: function () {
                this.gettingBasicRule = true;
                this.getBasicRuleFail = false;
                this.$http.get(api.ruleApi('admin/rule/basic'))
                    .then((resp) => {
                        this.mileageGoal = resp.data.mileageRequirement;
                        this.minSpeed = resp.data.minSpeed;
                        this.maxSpeed = resp.data.maxSpeed;
                        this.gettingBasicRule = false;
                    }, () => {
                        this.getBasicRuleFail = true;
                        this.gettingBasicRule = false;
                    });
            },
            modifyBasicRule: function () {
                this.basicRuleModifySubmitting = true;
                this.basicRuleModifyFail = false;
                this.$http.post(api.ruleApi('admin/rule/basic'), {
                    mileageRequirement: this.modifier.mileageGoal,
                    minSpeed: this.modifier.minSpeed,
                    maxSpeed: this.modifier.maxSpeed
                }).then((resp) => {
                    this.basicRuleModifying = false;
                    this.basicRuleModifySubmitting = false;
                    this.requestBasicRule();
                }, () => {
                    this.basicRuleModifyFail = true;
                    this.basicRuleModifySubmitting = false;
                });
            },
            newBorder: function (path) {
                let border = {
                    path: path,
                    events: {
                        rightclick: (e) => {
                            // right click the polygon is the same as right click on the map
                            if(this.modifierOn){
                                this.addFlag(e.lnglat.lng, e.lnglat.lat);
                            }
                        },
                        dragend: (e) => {
                            if(this.modifierOn){
                                // only keep one border object
                                this.modifier.border.path = e.target.getPath();
                            }
                        }
                    }
                };
                return border;
            },
            addInitialBorder: function () {
                let path = [[121.455746, 31.037906], [121.460161, 31.026424],
                    [121.427936, 31.016467], [121.42311, 31.027518]];
                this.border = this.newBorder(path);
            },
            requestBorder: function () {
                this.gettingBorder = true;
                this.getBorderFail = false;
                this.borderInfoError = false;
                this.$http.get(api.ruleApi('admin/rule/border'))
                    .then((resp) => {
                        if(resp.data.length < 3){
                            this.addInitialBorder();
                            this.borderInfoError = true;
                        }else{
                            let path = [];
                            for (let i = 0; i < resp.data.length; i++) {
                                path.push([resp.data[i].lng, resp.data[i].lat]);
                            }
                            this.border = this.newBorder(path);
                        }
                        this.gettingBorder = false;
                    }, () => {
                        this.addInitialBorder();
                        this.getBorderFail = true;
                        this.gettingBorder = false;
                    });
            },
            modifyBorder: function () {
                this.borderModifySubmitting = true;
                this.borderModifyFail = false;
                let path = [];
                for (let i = 0; i < this.modifier.border.path.length; i++) {
                    path.push({
                        lng: this.modifier.border.path[i].lng,
                        lat: this.modifier.border.path[i].lat
                    });
                }
                console.log('path');
                console.log(path);
                this.$http.post(api.ruleApi('admin/rule/border'),
                    path
                ).then((resp) => {
                    this.borderModifying = false;
                    this.borderModifySubmitting = false;
                    this.requestBorder();
                }, () => {
                    this.borderModifyFail = true;
                    this.borderModifySubmitting = false;
                });
            },
            submitModification: function () {
                this.modifyFlags();
                this.modifyBasicRule();
                this.modifyBorder();
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
