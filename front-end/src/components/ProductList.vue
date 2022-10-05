  <template>
      <div cla="list">
        <van-card v-for="item in items" :key="item.id"
            num="2"
            v-bind:price="item.price"
            v-bind:desc="item.description"
            v-bind:title="item.name"
            origin-price="10.00"
            thumb="https://img01.yzcdn.cn/vant/ipad.jpeg"
            @click="toDetailPage(item)"
          />
      </div>    
  </template>

  <script>
  import { Card } from 'vant';

  export default {
    name: 'ProductList',
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
      this.$axios.get("/api/product/list?page=1&pageSize=10&status=1")
      .then(res => {
        that.items = res.data.list;
        console.info(res.data);
          
      }).catch(err => {
          console.error(err);
      })

    },
    methods:{
      toDetailPage: function(item) {
        console.log("toDetailPage:" + JSON.stringify(item));
        this.$router.push("/detail?id="+item.id);
      }
    },  
  
  }
  </script>

  <style scoped>

  </style>