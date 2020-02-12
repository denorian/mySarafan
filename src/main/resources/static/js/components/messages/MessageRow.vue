<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <i>({{message.id}})</i>
            {{message.text}}
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions>
            <v-btn small rounded value="Edit" @click="edit">
                Edit
            </v-btn>
            <v-btn small icon @click="del">
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex';
    import Media from "../media/Media.vue";

    export default {
        props: ['message', 'editMessage'],
        components: {Media},
        methods: {
            ...mapActions(['removeMessageAction']),
            edit: function () {
                this.editMessage(this.message)
            },
            del: function () {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style></style>