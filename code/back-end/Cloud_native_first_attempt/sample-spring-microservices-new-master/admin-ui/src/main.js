import Vue from 'vue'
import App from './App.vue'
import VueAMap from 'vue-amap'
import router from './router'
import VueResource from '../node_modules/vue-resource/dist/vue-resource.min.js'
import '../node_modules/jquery/dist/jquery.min.js'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/bootstrap/dist/js/bootstrap.min.js'
import '../src/css/public.css'

Vue.config.productionTip = false;

Vue.use(VueResource);

Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: '54bdf74adf747b16855862d6c2a32c33',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor'],
  v: '1.4.15'
});


new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
