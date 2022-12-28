<template>

    <v-card outlined>
        <v-card-title>
            CampingView # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="CampsiteId" v-model="item.campsiteId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CampsiteDescription" v-model="item.campsiteDescription" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CampsiteStatus" v-model="item.campsiteStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="ReservationId" v-model="item.reservationId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReservationStatus" v-model="item.reservationStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="PayId" v-model="item.payId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PaymentStatus" v-model="item.paymentStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'CampingViewViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/campingViews/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

