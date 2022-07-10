<template>
    <div id="product-list">

    </div>    
</template>

<script>
import axios from "axios"
export default {
  name: 'ProductList',
  props: {
    msg: String
  },
  data(){
    return {
      list:[]
    }
  },
  created() {
    axios.get("/api/product/products")
    .then(res => {
        console.info(res.data);
    }).catch(err => {
        console.error(err);
    })

  },

}
</script>

<style scoped>

</style>