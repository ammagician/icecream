<template>
  <div class="portlet">
    <h1 :class="classStr">{{ (data.title || data.name || '标题') + data.i }}<span class="portlet-close">X</span></h1>
    <div class="portlet-content">
      <component v-bind:is="data.name"></component>
    </div>
  </div>
</template>

<script>
//加载所有widgets
const files = require.context('./widgets', false, /\.vue/)
const widgets = {}
files.keys().forEach(f => {
  const name = f.replace('./', '').replace('.vue', '')
  widgets[name] = files(f).default
})

export default {
  props: ['data'],
  components: {
    ...widgets
  },

  data() {
    const clss = ['portlet-title']
    if (this.data.class) {
      clss.push(this.data.class)
    }
    return {
      classStr: clss.join(' ')
    }
  },

  methods: {}
}
</script>

<style scoped>
.portlet {
  position: relative;
  height: 100%;
}

.portlet-title {
  position: absolute;
  top: 0px;
  left: 0px;
  height: 40px;
  margin: 0px;
  line-height: 40px;
  padding: 0 36px 0 10px;
  border-bottom: solid 1px #ccc;
  width: 100%;
  box-sizing: border-box;
  font-size: 16px;
}

.portlet-close {
  position: absolute;
  top: 0px;
  right: 12px;
  font-size: 16px;
  cursor: pointer;
  font-weight: normal;
}
.portlet-close:hover {
  color: red;
}

.portlet-content {
  height: 100%;
  padding-top: 40px;
  box-sizing: border-box;
}
</style>
