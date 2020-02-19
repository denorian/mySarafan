<template>
    <router-link
        :to="`/user/${user.id}`"
        class="px-3"
    >
        <v-avatar
                v-if="user && user.userPic"
                :size="`${size || 36}px`"
        >
            <img :src="user.userPic"
                 :alt="user.name"
            >
        </v-avatar>
        <v-avatar
                v-else
                :size="`${size || 36}px`"
                color="indigo">
            <v-icon dark>mdi-account-circle</v-icon>
        </v-avatar>
        <span class="ml-1">{{userName}}</span>
    </router-link>
</template>

<script>
    export default {
        name: "UserLink",
        props: ["user", "size"],
        computed: {
            userName() {
                return this.user ? this.user.name : 'unknown';
            }
        },
    }
</script>

<style scoped>

</style>