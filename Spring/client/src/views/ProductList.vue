<template>
  <main class="mt-3">
    <div class="container">

      <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-6" :key="i" v-for="(product, i) in productList">
          <div class="card" style="width: 18rem;">
            <img :src="`https://picsum.photos/200/300?random=${product.id}`" class="card-img-top" alt="...">
            <div class="card-body">
              <a @click="goToDetail(product.id);" style="cursor:pointer;">
                <h5 class="card-title">{{ product.productName }}</h5>
              </a>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group" role="group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">장바구니 담기</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">주문하기</button>
                </div>
                <small class="text-dark">{{ product.productPrice }}원</small>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      productList: []
    };
  },
  created() {
    this.getProductList();
  },
  methods: {
    async getProductList() {
      this.productList = await this.$api("GET", "/api/product/list", {});
    },
    goToDetail(product_id) {
      this.$router.push({ path: '/detail', query: { product_id: product_id } });
    }
  }
}
</script>