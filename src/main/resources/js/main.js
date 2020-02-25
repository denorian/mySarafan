import Vue from 'vue'
import Vuetify from "vuetify"
import './api/resource.js'
import '@babel/polyfill'
import App from "./pages/App.vue"
import store from "./store/store";
import router from "./router/router";
import {connect} from "./util/ws"
import 'vuetify/dist/vuetify.min.css'
import * as Sentry from '@sentry/browser';
import * as Integrations from '@sentry/integrations';

Sentry.init({
    dsn: 'https://8651c09ae9b547f29e3442e29deabb31@sentry.io/2744329',
    integrations: [new Integrations.Vue({Vue, attachProps: true})],
});

Sentry.configureScope(function (scope) {
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name,
    });
});

if (profile) {
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