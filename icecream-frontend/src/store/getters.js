const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  portals: state => state.user.portals,
  permission_routes: state => state.permission.routes,
  currentPortal: state => state.user.setting? state.user.setting.currentPortal : ''
}
export default getters
