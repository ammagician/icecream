<template>
  <div ref="rightPanel" :class="{show:show}" class="rightPanel-container">
    <div class="rightPanel">
      <div class="handle-button" :style="{'top':buttonTop+'px','background-color':'#409eff'}" @click="openSetting">
        <i :class="show?'el-icon-close':'el-icon-setting'" />
      </div>
      <div class="rightPanel-items">
        <el-scrollbar style="height:100%;">
          <div
            v-for="item in portlets"
            :key="item.id"
            draggable="true"
            @dragstart="dragstart(item, $event)"
            class="item"
          >
            <el-image :src="item.thumbnail" fit="contain">
              <div slot="error" class="image-slot">
                <i class="el-icon-picture-outline" style="font-size: 40px;"></i>
              </div>
            </el-image>
            <span style="font-size:12px;height: 20px; line-height: 20px">{{ item.name }}</span>
          </div>
        </el-scrollbar>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import logo from '@/assets/logo.png'
export default {
  name: 'PortletSelector',
  props: {
    clickNotClose: {
      default: false,
      type: Boolean
    },
    buttonTop: {
      default: 250,
      type: Number
    }
  },
  data() {
    return {
      show: false,
      portlets2: [
        {
          name: '123',
          title: '123',
          thumbnail: logo
        },
        {
          name: '456',
          title: '123',
          thumbnail: logo
        },
        {
          name: '4526',
          title: '123',
          thumbnail: logo
        },
        {
          name: '4356',
          title: '123',
          thumbnail: logo
        },
        {
          name: '4546',
          title: '123',
          thumbnail: logo
        }
      ]
    }
  },
  computed: {
    ...mapGetters([
      'currentPortal', 'portals'
    ]),

    portlets(){
      if(this.currentPortal && this.portals.length > 0){

        const portal = this.portals.find(p=>{
          return p.id === this.currentPortal
        })
        if(portal){
          return portal.portletList || []
        }
      }
      return []
    }
  },
  watch: {
    show(value) {
      if (value && !this.clickNotClose) {
        this.addEventClick()
      }
    }
  },
  methods: {
    addEventClick() {
      window.addEventListener('click', this.closeSidebar)
    },
    closeSidebar(evt) {
      const parent = evt.target.closest('.rightPanel')
      if (!parent) {
        this.show = false
        window.removeEventListener('click', this.closeSidebar)
        this.$emit("change", this.show)
      }
    },

    openSetting(){
      this.show = !this.show
      this.$emit("change", this.show)
    },

    dragstart(item, e){
      e.dataTransfer.setData("dragItem", JSON.stringify(item));
    }
  }
}
</script>

<style lang="scss" scoped>
.rightPanel {
  width: 100%;
  max-width: 260px;
  height: 100vh;
  position: fixed;
  top: 50px;
  right: 0;
  box-shadow: 0px 0px 15px 0px rgba(0, 0, 0, .05);
  transition: all .25s cubic-bezier(.7, .3, .1, 1);
  transform: translate(100%);
  background: #fff;
  z-index: 100;
}

.rightPanel-items {
  height: 100%;
  padding-bottom: 30px;
}
.item{
  display: flex;
  justify-content: center;
  flex-direction: column;
  height: 120px;
  align-items: center;
  cursor: move;
  padding: 10px 0px;
}
.item:hover{
  background-color: rgb(188, 224, 241);
}

.show {
  transition: all .3s cubic-bezier(.7, .3, .1, 1);
  .rightPanel {
    transform: translate(0);
  }
}

.handle-button {
  width: 48px;
  height: 48px;
  position: absolute;
  left: -48px;
  text-align: center;
  font-size: 24px;
  border-radius: 6px 0 0 6px !important;
  z-index: 0;
  pointer-events: auto;
  cursor: pointer;
  color: #fff;
  line-height: 48px;
  i {
    font-size: 24px;
    line-height: 48px;
  }
}
</style>
