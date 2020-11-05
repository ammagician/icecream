<template>
  <div class="icecream-dashboard">
    <grid-layout
      :layout.sync="layout"
      :col-num="24"
      :row-height="30"
      :is-draggable="true"
      :is-resizable="true"
      :is-mirrored="false"
      :vertical-compact="false"
      :autoSize="false"
      :responsive="false"
      :preventCollision="true"
      :margin="[10, 10]"
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
    <div v-show="showMask" class="icecream-dashboard-mask" @dragover.prevent="dragover" @drop="addItem"></div>
    <portlet-selector @change="openSelector">

    </portlet-selector>
  </div>
</template>

<script>
import VueGridLayout from 'vue-grid-layout'
import PortletSelector from '@/components/PortletSelector'
import Portlet from '../portlets/index'

var testLayout = [
        {"x":0,"y":0,"w":3,"h":6,local: true,i: '001',name: 'Test'},
        {"x":2,"y":0,"w":2,"h":4,local: true,i: '002',name: 'Test2'}
    ]

export default {
  components: {
    GridLayout: VueGridLayout.GridLayout,
    GridItem: VueGridLayout.GridItem,
    Portlet,
    PortletSelector
  },
  data: function(){
    return {
      layout: testLayout,
      showMask:false,
      addFlag: false
    }
  },
  methods: {
    dragover(){

    },
    addEnd(e){
      if(this.addFlag){
        this.addItem(e)
      }
      this.addFlag = false
    },

    addItem(e){
      var item=JSON.parse(e.dataTransfer.getData("dragItem"));
      console.info(item)
      const rect = e.target.getBoundingClientRect()
      const columnWidth = (rect.width-25*10) / 24 + 10
      const x = Math.floor((e.offsetX-10) / columnWidth)
      const rowHeight = 30+10
      const y = Math.floor((e.offsetY-10) / rowHeight)
      const proxy = {"x":x,"y":y,"w":2,"h":2,local: true,i: item.id,name: item.url, class:"grid-item-proxy"}
      this.layout.push(proxy)
    },
    openSelector(show){
      this.showMask = show
    }
  }
}
</script>

<style scoped>
.icecream-dashboard{
  height: calc(100vh - 50px);
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
