import Vue from 'vue'
import Vuetify from "vuetify"
import 'api/resource'
import '@babel/polyfill'
import App from "./pages/App.vue"
import store from "./store/store";
import router from "./router/router";
import {connect} from "./util/ws"
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect();
}

Vue.use(Vuetify);

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    store: store,
    router,
    render: a => a(App)
});