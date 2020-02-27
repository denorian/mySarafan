<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <user-link :user="message.author" size="48"/>
            <div class="pt-3">{{message.text}}</div>
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions v-if="isAuthor">
            <v-btn small rounded value="Edit" @click="edit">
                Edit
            </v-btn>
            <v-btn small icon @click="del">
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list
                :comments="message.comments"
                :message-id="message.id"/>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex';
    import Media from "../media/Media.vue";
    import CommentList from "../comment/CommentList.vue";
    import UserLink from "../UserLink.vue";

    export default {
        props: ['message', 'editMessage'],
        components: {UserLink, CommentList, Media},
        computed: {
            isAuthor() {
                return this.message.author.id === this.$store.state.profile.id
            }
        },
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