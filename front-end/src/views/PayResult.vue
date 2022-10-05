<template>
  <div class="about">
    <h1>支付结果页面</h1>
  </div>
</template>
<script>

export default {
  name: 'PayResult',
  components: {

  },

  methods:{
    getResult:function (query) {
      var query=this.$route.query;
    }
  }
}
</script>