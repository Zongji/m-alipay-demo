<template>
  <div class="detail">
    <van-nav-bar
        title="商品详情"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
        @click-right="onClickRight"
    />
    <div class="pictures">
      <van-swipe :autoplay="3000">
        <van-swipe-item v-for="(image, index) in images" :key="index">
          <img :src="image" width="400" height="300"/>
        </van-swipe-item>
      </van-swipe>
    </div>
    <div class="basic-info">
      <van-row>
        <van-col span="8">
          <span style="font-size: larger;font-weight: bolder;color: red">
            ￥{{ itemMy.price }}
          </span>
        </van-col>
        <van-col span="8"></van-col>
        <van-col span="8"><van-button type="danger" size="small" @click="buy(itemMy)">立即购买</van-button></van-col>
      </van-row>
      <p style="font-size:large;font-weight: bold">{{ itemMy.name }}

      </p>
      <p>规格:<span style="font-weight: bold">8G+256G</span></p>
    </div>

    <van-tabs v-model="active" scrollspy sticky>
      <van-tab title="商品详情">
        <div class="detail-content">
          商品详情
          可以看见com1组件里的路由参数为 name=zhangsan&job=teacher

          使用 this.$route.query 来获取路由参数

          现在就是利用query直接获取路由参数并且以对象的形式展现出来
          可以看见com1组件里的路由参数为 name=zhangsan&job=teacher

          使用 this.$route.query 来获取路由参数

          现在就是利用query直接获取路由参数并且以对象的形式展现出来
          可以看见com1组件里的路由参数为 name=zhangsan&job=teacher

          使用 this.$route.query 来获取路由参数

          现在就是利用query直接获取路由参数并且以对象的形式展现出来

        </div>
      </van-tab>
      <van-tab title="评论">
        评论区
        评论区评论区评论区评论区评论区评论区评论区评论区评论区评论区评论区
      </van-tab>
    </van-tabs>

    <van-action-sheet v-model="show" title="购买内容确认">
      <van-form action='/pay/submit' target="_blank">
        <input type="hidden"  v-model="trade.productId">
        <input type="hidden"  v-model="trade.tradeNo">
        <van-field
            v-model="trade.subject"
            name="商品名称"
            label="商品名称"
            placeholder="商品名称"
            readonly
        />
        <van-field
            v-model="trade.amount"
            name="订单金额"
            label="订单金额"
            placeholder="订单金额"
            size="large"
            error
            readonly
        />
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">提交</van-button>
        </div>
      </van-form>
      <div class="content">商品内容</div>
      <form name=alipayment action='/pay/submit' method=post target="_blank">
        <div id="body" style="clear: left">
          <input name="productId" hidden v-model="trade.productId"/>
          <input name="WIDout_trade_no"  hidden v-model="trade.tradeNo"/>
          <input name="WIDbody" hidden  v-model="trade.subject"/>

          <dl class="content">
            <hr class="one_line">
            <dt>商品名称：</dt>
            <dd>
              <input name="WIDsubject" readonly v-model="trade.subject"/>
            </dd>
            <hr class="one_line">
            <dt>金额：</dt>
            <dd>
              <input name="WIDtotal_amount"  readonly v-model="trade.amount"/>
            </dd>
            <hr class="one_line"/>
            <hr class="one_line">
            <dd id="btn-dd">
              <span class="new-btn-login-sp">
                <button class="new-btn-login" type="submit" style="text-align: center;">确 认</button>
              </span>
            </dd>
          </dl>
        </div>
      </form>
<!--      <van-button type="danger" size="small" @click="buyConfirm(itemMy)">确认</van-button>-->
    </van-action-sheet>
  </div>
</template>

<script>
// @ is an alias to /src
import {Dialog, NavBar,Swipe, SwipeItem,Lazyload,Button, Col, Row,Form,Field   } from 'vant';

export default {
  name: 'ProductDetail',
  data() {
    return {
      itemMy: {},
      images:[
        'https://img01.yzcdn.cn/vant/apple-1.jpg',
        'https://img01.yzcdn.cn/vant/apple-2.jpg',
      ],
      trade:{},
      active:0,
      show:false,
    };
  },
  // props: {
  //   id: String
  // },
  components: {
    NavBar,
    [Dialog.Component.name]: Dialog.Component,
    Swipe,
    SwipeItem,
    Lazyload,
    Button, Col, Row
  },
  created() {
    let query = this.$route.query;
    console.info("query:", query);

    let that = this;

    console.info("id:" + query.id);

    if (query.id == null || query.id === '') {
      Dialog.alert({
        message: '请求参数缺失！',
      }).then(() => {
        // on close
        this.$router.replace("/");
      });
      return;
    }
    this.$axios.get("/api/product/detail?id=" + query.id)
        .then(res => {
          that.itemMy = res.data.data;
          console.info(res.data);
        }).catch(err => {
      console.error(err);
    })

  },
  methods: {
    onClickLeft: function () {
      console.info("onClickLeft");
      this.$router.go(-1);
    },
    onClickRight: function () {
      console.info("onClickRight")

    },
    buy: function (item){
      this.trade = {
        tradeNo: "T" + new Date().valueOf(),
        subject: this.itemMy.name,
        amount: this.itemMy.price,
        productId: this.itemMy.id
      };
      this.show = true;
    },
    buyConfirm: function (item) {
      console.info("确认购买");
    }
  }
}
</script>

<style>
.basic-info{
  margin: 10px;
}
.detail-content{
  margin: 10px;
  padding-bottom: 10px;
}
</style>