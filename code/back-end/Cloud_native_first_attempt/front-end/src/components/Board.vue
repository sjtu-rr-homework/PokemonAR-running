<template>
  <div class="p-2">
    <div class="p-2">
      <div class="input-group p-2 col-5">
        <span class="input-group-prepend offset-1 col-2">模拟步数</span>
        <input v-model="stepAmount" type="number" class="form-control offset-1 col-7" placeholder="模拟步数"/>
      </div>
      <div class="input-group p-2 col-5">
        <span class="input-group-prepend offset-1 col-2">单位时间间隔（毫秒）</span>
        <input v-model="simulationStepInterval" type="number" class="form-control offset-1 col-7" placeholder="单位时间间隔（毫秒）"/>
      </div>
      <div class="input-group p-2 col-5">
        <span class="input-group-prepend offset-1 col-2">宽度（不超过36）</span>
        <input v-model="width" type="number" class="form-control offset-1 col-7" placeholder="宽度（不超过36）"/>
      </div>
      <div class="input-group p-2 col-5">
        <span class="input-group-prepend offset-1 col-2">高度（不超过18）</span>
        <input v-model="height" type="number" class="form-control offset-1 col-7" placeholder="高度（不超过18）"/>
      </div>
      <button class="btn btn-block btn-outline-warning" v-on:click="resetBoard()">重置</button>
      <button class="btn btn-block btn-outline-primary" v-on:click="submitBoard()">获取模拟结果</button>
    </div>
    <div class="p-2">Tip1: 点击下面的小方格来改变方格的状态</div>
    <div class="p-2">Tip2: 点击上方的按钮获取模拟结果</div>
    <div class="p-2">Tip3: 点击最下方的按钮播放模拟结果</div>
    <div v-for="(row, i) in board" v-bind:key="i" class="pb-1">
      <div v-if="i < height">
        <span v-for="(cell, j) in row" v-bind:key="j" class="pl-1">
          <img v-if="isInBorder(i, j) && cell === 0" src="@/assets/dead.png" width="32px" height="32px" v-on:click="switchAlive(i, j)"/>
          <img v-else-if="isInBorder(i, j) && cell === 1" src="@/assets/alive.png" width="32px" height="32px" v-on:click="switchAlive(i, j)"/>
        </span>
      </div>
    </div>
    <div class="p-2">第 {{ generation }} 代</div>
    <div class="p-2">
      <div v-if="simulationFail" class="p-2">获取模拟结果失败</div>
      <button v-bind:disabled="!simulationReady || simulationPlaying" class="btn btn-block btn-outline-success" v-on:click="showSimulation()">播放！</button>
      <button v-bind:disabled="!simulationReady || simulationPlaying" class="btn btn-block btn-outline-warning" v-on:click="finishSimulation()">结束模拟</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Board',
  created: function () {
    this.resetBoard()
  },
  data: function () {
    return {
      width: 2, // 24
      height: 2, // 12
      maxWidth: 36,
      maxHeight: 18,
      stepAmount: 3,
      refreshing: false,
      board: [[]], // 0 = dead, 1 = alive
      initBoard: [[]],
      simulationStepInterval: 500,
      generation: 0,
      simulationFail: false,
      simulationReady: false,
      simulationPlaying: false,
      simulation: [[[]]] // simulation[0] is the first step after the initial board
    }
  },
  methods: {
    switchAlive: function (i, j) {
      this.board[i][j] = 1 - this.board[i][j]
      this.refreshBoard()
    },
    refreshBoard: function () {
      this.refreshing = true
      let tmp = this.board
      this.board = []
      this.board = tmp
      this.refreshing = false
    },
    isInBorder: function (i, j) {
      return i >= 0 && i < this.height && j >= 0 && j < this.width
    },
    resetBoard: function () {
      this.board = []
      for (let i = 0; i < this.maxHeight; i++) {
        let row = []
        for (let j = 0; j < this.maxWidth; j++) {
          row.push(0)
        }
        this.board.push(row)
      }
    },
    getInitialBoard: function () {
      let tmp = []
      for (let i = 0; i < this.height; i++) {
        let row = []
        for (let j = 0; j < this.width; j++) {
          row.push(this.board[i][j])
        }
        tmp.push(row)
      }
      this.initBoard = tmp
      return tmp
    },
    submitBoard: function () {
      this.simulationFail = false
      this.simulationReady = false
      this.$http.post(
        '/simulation/simulate', {
          steps: this.stepAmount,
          initialBoard: this.getInitialBoard()
        }
      ).then(
        (resp) => {
          this.simulation = resp.data.simulation
          this.simulationReady = true
          this.simulationFail = false
        }, () => {
          this.simulationReady = false
          this.simulationFail = true
        }
      )

      this.$http.post('/statistics/ChangeData', {
        steps: this.stepAmount,
        initialBoard: this.getInitialBoard()
      }
      ).then(

      )

      this.sleep(200).then(
        () => {
          this.$http.post('/save/SaveResult', {
            result: this.simulation
          }
          ).then(

          )
        }
      )
      /* this.getInitialBoard()
      this.simulation = [[[0, 1], [1, 0]], [[0, 0], [0, 1]], [[1, 0], [1, 0]]]
      this.simulationReady = true */
    },
    setBoard: function (trimmedBoard) {
      for (let i = 0; i < this.height; i++) {
        for (let j = 0; j < this.width; j++) {
          this.board[i][j] = trimmedBoard[i][j]
        }
      }
      this.refreshBoard()
    },
    sleep: function (time) {
      return new Promise((resolve) => setTimeout(resolve, time))
    },
    showSimulation: function () {
      this.simulationPlaying = true
      this.setBoard(this.initBoard)
      this.generation = 0
      for (let s = 0; s < this.simulation.length; s++) {
        this.sleep((s + 1) * this.simulationStepInterval).then(
          () => {
            this.setBoard(this.simulation[s])
            this.generation = s + 1
          }
        )
      }
      this.sleep(this.simulation.length * this.simulationStepInterval).then(
        () => {
          this.simulationPlaying = false
        }
      )
    },
    finishSimulation: function () {
      this.setBoard(this.initBoard)
      this.simulationReady = false
      this.generation = 0
    }
  }
}
</script>

<style scoped>
</style>
