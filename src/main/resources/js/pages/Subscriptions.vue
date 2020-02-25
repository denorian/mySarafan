<template>
    <v-container>
        <v-layout justify-space-around="true">
            <v-list elevation="">
                <v-list-tile v-for="item in subscriptions" class="py-6">
                    <user-link :user="item.subscriber" size="24"/>
                    <v-btn @click="changeSubscriptionStatus(item.subscriber.id)">
                        {{item.active ? "Dismiss" : "Approve"}}
                    </v-btn>
                    <br>
                </v-list-tile>
            </v-list>
        </v-layout>
    </v-container>
</template>

<script>
    import profileApi from "../api/profile.js";
    import UserLink from "../components/UserLink.vue";

    export default {
        name: "Subscriptions",
        components: {UserLink},
        data() {
            return {
                subscriptions: []
            }
        },
        methods: {
            async changeSubscriptionStatus(subscriberId) {
                await profileApi.changeSubscriptionStatus(subscriberId);
                const subscriberIndex = this.subscriptions.findIndex(item =>
                    item.subscriber.id === subscriberId
                );

                const subscription = this.subscriptions[subscriberIndex];
                this.subscriptions = [
                    ...this.subscriptions.slice(0, subscriberIndex),
                    {
                        ...subscription,
                        active: !subscription.active
                    },
                    ...this.subscriptions.slice(subscriberIndex + 1),
                ];

            }
        },
        async beforeMount() {
            const response = await profileApi.subscriberList(this.$store.state.profile.id);
            this.subscriptions = await response.json();
        }
    }
</script>

<style scoped>

</style>