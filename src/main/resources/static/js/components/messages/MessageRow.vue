<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <i>({{message.id}})</i>
            {{message.text}}
        </v-card-text>
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
    export default {
        props: ['message', 'editMessage', 'deleteMessage', 'messages'],
        methods: {
            edit: function () {
                this.editMessage(this.message)
            },
            del: function () {
                this.deleteMessage(this.message)
            }
        }
    }
</script>

<style></style>