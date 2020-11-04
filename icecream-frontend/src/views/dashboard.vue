<template>
  <div class="icecream-dashboard">
    <grid-layout
      :layout.sync="layout"
      :col-num="12"
      :row-height="30"
      :is-draggable="true"
      :is-resizable="true"
      :is-mirrored="false"
      :vertical-compact="true"
      :margin="[20, 20]"
      :use-css-transforms="true"
      style="height:100%;"
    >
        <grid-item v-for="item in layout"
                   :x="item.x"
                   :y="item.y"
                   :w="item.w"
                   :h="item.h"
                   :i="item.i"
                   :key="item.i">
            <Portlet :data="item" />
        </grid-item>
    </grid-layout>
    <div v-if="false" class="icecream-dashboard-mask" @mousedown="addStart" @mouseup="addEnd" @mousemove="moveItem"></div>
  </div>
</template>

<script>
import VueGridLayout from 'vue-grid-layout'
import Portlet from '../portlets/index'

var testLayout = [
        {"x":0,"y":0,"w":3,"h":6,local: true,i: '001',name: 'Test'},
        {"x":2,"y":0,"w":2,"h":4,local: true,i: '002',name: 'Test2'}
    ]

export default {
  components: {
    GridLayout: VueGridLayout.GridLayout,
    GridItem: VueGridLayout.GridItem,
    Portlet
  },
  data: function(){
    return {
      layout: testLayout,
      addFlag: false
    }
  },
  methods: {
    addStart(e){
      if(!this.addFlag){
        this.addFlag = true
        this.showProxy(e)
      }

    },
    addEnd(){
      if(this.addFlag){
        this.hideProxy()
        this.addItem()
      }
      this.addFlag = false
    },
    moveItem(e){
      if(!this.addFlag){
        return
      }
      var event = document.createEvent('MouseEvents')
      event.initMouseEvent('mousemove', true, false, window, 0, e.screenX, e.screenY, e.clientX, e.clientY,
                     e.ctrlKey, e.altKey, e.shiftKey, e.metaKey, 0, null)
      document.querySelector(".grid-item-proxy").dispatchEvent(event)
    },

    showProxy(e){
      const rect = e.target.getBoundingClientRect()
      const x = Math.floor((e.clientX-20) / (rect.width / 12))
      const y = Math.floor((e.clientY-80) / (rect.height / 12))
      const proxy = {"x":x,"y":y,"w":2,"h":2,local: true,i: 'proxy',name: 'Proxy', class:"grid-item-proxy"}
      this.layout.push(proxy)

      this.$nextTick(()=>{
        var event = document.createEvent('MouseEvents')
        event.initMouseEvent('mousedown', true, false, window, 0, e.screenX, e.screenY, e.clientX, e.clientY,
                      e.ctrlKey, e.altKey, e.shiftKey, e.metaKey, 0, null)
        document.querySelector(".grid-item-proxy").dispatchEvent(event)
      })
    },

    hideProxy(){
      const index = this.layout.findIndex(item=>{
        return item.i === 'proxy'
      })
      this.layout.splice(index)
    },

    addItem(){

    }
  }
}
</script>

<style scoped>
.icecream-dashboard{
  height: 100%;
  position: relative;
}
.icecream-dashboard-mask{
  position: absolute;
  background: #ccc;
  opacity: 0.3;
  z-index: 10;
  top: 0px;
  bottom: 0px;
  left: 0px;
  right: 0px;
}
.vue-grid-item{
  border: solid 1px #ccc;
  border-radius:5px;
  box-shadow: 0px 0px 6px 1px rgba(0, 0, 0, 0.2);
}
</style>>
