import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import VueResource from 'vue-resource'
import App from './components/App.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-vue/dist/bootstrap-vue.css"
import jQuery from 'jquery'

global.jQuery = jQuery;
global.$ = jQuery;
Vue.use(BootstrapVue);

Vue.use(VueResource);

Vue.prototype.$eventHub = new Vue(); // Global event bus






new Vue({
  el: '#app',
  render: h => h(App)
})
