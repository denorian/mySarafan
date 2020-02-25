<template>
    <v-container v-if="!profile">
        Необходимо авторизоваться через
        <a href="/login">Google</a>
    </v-container>
</template>
<script>
    import {mapState} from "vuex";

    export default {
        computed: mapState(['profile']),
        name: 'Auth'
    }
</script>
<style></style>
