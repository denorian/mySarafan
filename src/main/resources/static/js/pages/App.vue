<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-btn text
                   v-if="profile"
                   :disabled="$route.path === '/'"
                   @click="showMessages"
            >Messages</v-btn>
            <v-spacer></v-spacer>
            <v-btn text
                   v-if="profile"
                   :disabled="$route.path === '/profile'"
                   @click="showProfile"
            >{{profile.name}}</v-btn>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import {addHandler} from 'util/ws'

    export default {
        computed: mapState(['profile', 'messages']),
        methods: {
            ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
            showMessages(){
                this.$router.push('/');
            },
            showProfile(){
                this.$router.push('/profile');
            },

        },
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    const index = this.messages.findIndex(item => item.id === data.body.id)

                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body);
                            break;
                        case 'UPDATE':
                            this.updateMessageMutation(data.body);
                            break
                        case 'REMOVE':
                            this.messages.splice(index, 1)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        },
        beforeMount() {
            if(!this.profile){
                this.$router.replace('/auth');
            }
        }
    }
</script>

<style>

</style>