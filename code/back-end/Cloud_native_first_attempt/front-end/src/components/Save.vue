<template>
  <div>
    <div class="input-group p-2 col-5">
      <span class="input-group-prepend offset-1 col-2">单位时间间隔（毫秒）</span>
      <input v-model="simulationStepInterval" type="number" class="form-control offset-1 col-7" placeholder="单位时间间隔（毫秒）"/>
    </div>
    <div v-for="(row, i) in board" v-bind:key="i" class="pb-1">
      <span v-for="(cell, j) in row" v-bind:key="j" class="pl-1">
        <img v-if="cell === 0" src="@/assets/dead.png" width="32px" height="32px"/>
        <img v-else-if="cell === 1" src="@/assets/alive.png" width="32px" height="32px"/>
      </span>
    </div>
    <div class="p-2">第 {{ generation }} 代</div>
    <div class="p-2">
      <button v-bind:disabled="simulationPlaying" class="btn btn-block btn-outline-success" v-on:click="showSimulation()">播放！</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Save',
  props: ['simulation'],
  mounted: function () {
    this.setBoard(this.simulation[0])
  },
  data: function () {
    return {
      board: [],
      simulationStepInterval: 500,
      simulationPlaying: false,
      generation: 0
    }
  },
  methods: {
    setBoard: function (board) {
      this.board = []
      this.board = board
    },
    sleep: function (time) {
      return new Promise((resolve) => setTimeout(resolve, time))
    },
    showSimulation: function () {
      this.simulationPlaying = true
      this.setBoard(this.simulation[0])
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
    }
  }
}
</script>

<style scoped>

</style>
