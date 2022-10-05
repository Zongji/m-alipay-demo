<template>
    <div cla="list">
      <van-card v-for="item in items" :key="item.id"
                v-if="!showEmpty"
          v-bind:price="item.price"
          v-bind:desc="item.description"
          v-bind:title="item.subject"
          thumb="https://img01.yzcdn.cn/vant/ipad.jpeg">
        <template #footer>
          <van-button size="mini" @click="showRefund">退款</van-button>
        </template>
      </van-card>
      <van-empty v-else description="还没有订单信息哦" />
    </div>    
</template>

<script>
// import axios from "axios"
import { Card,Empty } from 'vant';

export default {
  name: 'OrderList',
  comments:{
    Card,
    Empty
  },
  data(){
    return {
      items:[],
      showEmpty: false,
      showRefundActionSheet: false,
    }
  },
  created() {
    let that = this;
    this.$axios.get("/api/order/list?page=1&pageSize=10")
    .then(res => {
      that.items = res.data.list;
      console.info(that.items);
      if (that.items.length == 0) {
        that.showEmpty = true;
      }else {
        that.showEmpty = false;
      }
    }).catch(err => {
        console.error(err);
    })

  },
  methods:{
    toDetailPage: function(code) {
      console.log(code);
      this.$router.push("/detail")
    },
    showRefund:function ()  {
      console.log("showRefundActionSheet");

    }
  },  
 
}
</script>

<style scoped>

</style>