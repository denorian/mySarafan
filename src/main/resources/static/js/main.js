var messageApi = Vue.resource('/message{/id}');
Vue.component('message-row', {
    props: ['message'],
    template: '<div><i>{{message.id}}</i> {{message.text}}</div>'
});


Vue.component('message-list', {
    props: ['messages'],
    template: '<div>' +
        '<message-row v-for="message in messages" :message="message" :key="message.id"></message-row>' +
        '</div>',
    created: function () {
        messageApi.get().then(result =>
            result.json().then( data =>
               data.forEach(message => this.messages.push(message))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<message-list :messages="messages"/>',
    data: {
        messages: []
    }
});