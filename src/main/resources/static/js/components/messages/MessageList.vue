<template>
    <div style="position: relative; width: 300px">
        <message-form :messages="messages" :messageAttr="message"/>
        <message-row v-for="message in messages"
                     :message="message"
                     :key="message.id"
                     :editMessage="editMessage"
                     :deleteMessage="deleteMessage"
                     :messages="messages">
        </message-row>
    </div>
</template>
<script>
    import MessageRow from "components/messages/MessageRow.vue";
    import MessageForm from "components/messages/MessageForm.vue";
    export default {
        props: ['messages'],
        components:{
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message;
            },
            deleteMessage(message) {
                this.$resource("/message{/id}").remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(message), 1)
                    }
                })
            }
        }
    }
</script>
<style></style>

