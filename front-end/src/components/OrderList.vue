<template>
    <div cla="list">
      <van-card v-for="item in items" :key="item.id"
          v-bind:price="item.price"
          v-bind:desc="item.description"
          v-bind:title="item.subject"
          origin-price="10.00"
          thumb="https://img01.yzcdn.cn/vant/ipad.jpeg"
        />
    </div>    
</template>

<script>
// import axios from "axios"
import { Card } from 'vant';

export default {
  name: 'OrderList',
  comments:{
    Card
  },
  data(){
    return {
      items:[]
    }
  },
  created() {
    let that = this;
    this.$axios.get("/api/order/list?page=1&pageSize=10")
    .then(res => {
      that.items = res.data.list;
      console.info(res.data);
        
    }).catch(err => {
        console.error(err);
    })

  },
  methods:{
    toDetailPage: function(code) {
      console.log(code);
      this.$router.push("/detail")
    }
  },  
 
}
</script>

<style scoped>

</style>