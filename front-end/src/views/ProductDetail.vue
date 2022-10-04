<template>
    <div class="detail">
      <img alt="Vue logo" src="../assets/logo.png">
      {{code}}
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  
  export default {
    name: 'ProductDetail',
    data(){
        return{
            itemMy:{},
        };
    },
    props: {
        code: String
    },
    components: {
        
    },
    created() {
        let that = this;
        this.$axios.get("/api/product/detail?code=" + this.code)
        .then(res => {
            that.itemMy = res.data.data;
            console.info(res.data);
        }).catch(err => {
            console.error(err);
        })

    },
  }
  </script>
  