<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-if="profile">
            <div>{{profile.name}}&nbsp; <a href="/logout">Logout</a></div>
            <messages-list v-if="profile" :messages="messages"/>
        </div>
    </div>
</template>
<script>
    import MessagesList from "../components/messages/MessageList.vue";
    export default {
        components:{
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        }
    }
</script>
<style></style>