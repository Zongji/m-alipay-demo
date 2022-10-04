<template>
    <div class="product-lists">
      <OrderList></OrderList>
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import OrderList from '@/components/OrderList.vue'
  
  export default {
    name: 'Orders',
    components: {
      OrderList
    }
  }
  </script>
  