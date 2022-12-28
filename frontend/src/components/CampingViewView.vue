<template>

    <v-data-table
        :headers="headers"
        :items="campingView"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'CampingViewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "campsiteId", value: "campsiteId" },
                { text: "campsiteDescription", value: "campsiteDescription" },
                { text: "campsiteStatus", value: "campsiteStatus" },
                { text: "reservationId", value: "reservationId" },
                { text: "reservationStatus", value: "reservationStatus" },
                { text: "payId", value: "payId" },
                { text: "paymentStatus", value: "paymentStatus" },
            ],
            campingView : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/campingViews'))

            temp.data._embedded.campingViews.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.campingView = temp.data._embedded.campingViews;
        },
        methods: {
        }
    }
</script>

