<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <div>
                <v-avatar
                        v-if="message.author && message.author.userPic"
                        size="36px"
                >
                    <img
                            :src="message.author.userPic"
                            :alt="message.author.name"
                    >
                </v-avatar>
                <v-avatar
                        v-else
                        size="36px"
                        color="indigo">
                    <v-icon dark>mdi-account-circle</v-icon>
                </v-avatar>
                <span class="pl-3">{{this.authorName}}</span>
            </div>
            <div class="pt-3">{{message.text}}</div>
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
        <comment-list
                :comments="message.comments"
                :message-id="message.id"></comment-list>
    </v-card>
</template>

<script>
    import {mapActions} from 'vuex';
    import Media from "../media/Media.vue";
    import CommentList from "../comment/CommentList.vue";
    import messages from "../../api/messages";

    export default {
        props: ['message', 'editMessage'],
        components: {CommentList, Media},
        computed: {
            authorName() {
                return this.message.author ? this.message.author.name : 'unknown';
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