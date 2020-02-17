<template>
    <v-list-item>
        <v-list-item-avatar>
            <img :src="comment.author.userPic">
        </v-list-item-avatar>
        <v-list-item-content>
            <v-list-item-title>{{comment.text}}</v-list-item-title>
            <v-list-item-subtitle>{{comment.author.name}}</v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>
</template>

<script>
    export default {
        name: "CommentItem",
        props:["comment"]
    }
</script>

<style scoped>

</style>