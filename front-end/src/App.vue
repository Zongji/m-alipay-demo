<template>
  <div id="app">
    <router-view/>
    <van-tabbar v-model="active">
      <van-tabbar-item icon="home-o" replace to="/">首页</van-tabbar-item>
      <van-tabbar-item icon="goods-collect-o" replace to="/products">商品列表</van-tabbar-item>
      <van-tabbar-item icon="orders-o" replace to="/orders">订单列表</van-tabbar-item>
      <van-tabbar-item icon="https://b.yzcdn.cn/vant/icon-demo-1126.png" replace to="/about">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import { Tabbar, TabbarItem } from 'vant';

export default {
  comments:{
    Tabbar,
    TabbarItem
  },
  data(){
    return {
      active: 0,
    };
  },

}
</script>

<style>

</style>
