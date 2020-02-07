<template>
    <v-layout align-space-around justify-start column>
        <message-form :messageAttr="message"/>
        <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :editMessage="editMessage"/>
    </v-layout>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(["sortedMessages"]),
        methods: {
            ...mapActions(['removeMessageAction']),
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {

            }
        }
    }
</script>

<style>
</style>