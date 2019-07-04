<template>
  <div class="p-2">
    <div class="mb-3">欢迎，{{ username }}！以下是您的统计数据：</div>
    <div v-if="loading">加载中……</div>
    <div v-else-if="loadFail">加载失败</div>
    <div v-else>
      <div>模拟数：{{ boardAmount }}</div>
      <div>最长步数：{{ maxStepAmount }}</div>
      <div>成功的模拟数（模拟中没有出现全部死亡的情况即算作成功）：{{ boardSuccessAmount }}</div>
      <div></div>
    </div>
    <div class="p-2">
      <div class="p-2">历史记录（存档id列表）：</div>
      <div v-for="(save, i) in saves" v-bind:key="i">
        {{ save }}
      </div>
      <hr class="bg-light"/>
      <div class="input-group p-2">
        <span class="offset-1 col-2">存档id</span>
        <input v-model="selectedSave" class="offset-1 col-7" type="number" placeholder="存档id"/>
      </div>
      <button class="btn btn-block btn-outline-info" v-on:click="fetchSave()">查看存档</button>
    </div>
    <div v-if="loadSaveFail">加载存档失败</div>
    <div v-else-if="loadingSave">加载存档中……</div>
    <Save v-else-if="saveSelected" v-bind:simulation="simulation"/>
  </div>
</template>

<script>
import Save from '@/components/Save.vue'

export default {
  name: 'User',
  components: {
    Save
  },
  created: function () {
    this.loading = true
    this.$http.post(
      '/statistics/user'
    ).then(
      (resp) => {
        this.boardAmount = resp.data.boardAmount
        this.maxStepAmount = resp.data.maxStepAmount
        this.boardSuccessAmount = resp.data.boardSuccessAmount
        this.loadFail = false
        this.loading = false
      }, () => {
        this.loadFail = true
        this.loading = false
      }
    )
  },
  data: function () {
    return {
      username: '',
      saves: [],
      selectedSave: -1,
      simulation: [[[]]],
      saveSelected: false,
      loading: false,
      loadFail: false,
      loadingSave: false,
      loadSaveFail: false,
      boardAmount: 0, // 该用户做过多少次模拟
      maxStepAmount: 0, // 最长的一次模拟做了多少步
      boardSuccessAmount: 0 // 在过程中没有出现全部死亡的模拟数
    }
  },
  methods: {
    fetchSave: function () {
      this.loadingSave = true
      this.saveSelected = true
      this.$http.post(
        '/save/get', {
          id: this.selectedSave
        }
      ).then(
        (resp) => {
          this.simulation = resp.data.simulation
          this.loadSaveFail = false
          this.loadingSave = false
        }, () => {
          this.loadSaveFail = true
          this.loadingSave = false
        }
      )
      // this.simulation = [[[0, 1], [1, 0]], [[0, 0], [0, 1]], [[1, 0], [1, 0]]]
    }
  }
}
</script>

<style scoped>

</style>
