<template>
  <main class="mt-3">
    <div class="container">
      <div class="row">
        <div class="col-md-5">
          <div id="carouselExampleIndicators" class="carousel carousel-dark slide" data-bs-ride="carousel">
            <ol class="carousel-indicators">
              <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" class="active"></li>
              <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
              <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"></li>
            </ol>
            <div class="carousel-inner">
              <div :class="`carousel-item active`">
                <img :src="`https://picsum.photos/200/300?random=1`" class="d-block w-100" alt="...">
              </div>
              <div :class="`carousel-item`">
                <img :src="`https://picsum.photos/200/300?random=2`" class="d-block w-100" alt="...">
              </div>
              <div :class="`carousel-item`">
                <img :src="`https://picsum.photos/200/300?random=3`" class="d-block w-100" alt="...">
              </div>

            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </a>
          </div>
        </div>
        <div class="col-md-7">
          <div class="card shadow-sm">
            <div class="card-body">
              <h5 class="card-title">{{ productDetail.productName }}</h5>
              <h5 class="card-title pt-3 pb-3 border-top">{{ getCurrencyFormat(productDetail.productPrice) }}원</h5>
              <p class="card-text pb-3">
                배송비 {{ getCurrencyFormat(productDetail.deliveryPrice) }}원 | 도서산간(제주도) 배송비 추가
                {{ getCurrencyFormat(productDetail.addDeliveryPrice) }}원 | 택배배송 | {{ productDetail.outboundDays }}일
                이내 출고
                (주말,공휴일 제외)
              </p>
              <p class="card-text pb-3">
                {{ productDetail.sellerName }}
              </p>
              <div class="card-text border-top pb-3">
                <div class="row">
                  <div class="col-auto">
                    <label class="col-form-label">구매수량</label>
                  </div>
                  <div class="col-auto">
                    <div class="input-group">
                      <span class="input-group-text" style="cursor:pointer;" @click="calculatePrice(-1);">-</span>
                      <input type="text" class="form-control" style="width:40px;" v-model="total">
                      <span class="input-group-text" style="cursor:pointer;" @click="calculatePrice(1);">+</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row pt-3 pb-3 border-top">
                <div class="col-6">
                  <h3>총 상품 금액</h3>
                </div>
                <div class="col-6" style="text-align: right;">
                  <h3>{{ getCurrencyFormat(totalPrice) }}원</h3>
                </div>
              </div>
              <div class="d-flex justify-content-between align-items-center">
                <div class="col-6 d-grid p-1">
                  <button type="button" class="btn btn-lg btn-dark">장바구니 담기</button>
                </div>
                <div class="col-6 d-grid p-1">
                  <button type="button" class="btn btn-lg btn-danger">주문하기</button>
                </div>

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
      productId: 0,
      productDetail: {},
      total: 0,
      totalPrice: 0
    };
  },
  created() {
    this.productId = this.$route.query.product_id;
    this.getProductDetail();
  },
  methods: {
    calculatePrice(cnt) {
      let total = this.total + cnt;
      if (total < 1) total = 1;
      this.total = total;
      this.totalPrice = this.productDetail.productPrice * this.total;
    },
    getCurrencyFormat(value) {

      return this.$currencyFormat(value);
    },
    async getProductDetail() {
      let productDetail = await this.$api("GET", "/api/product/detail?id=" + this.productId);
      this.productDetail = productDetail;
      this.totalPrice = this.totalPrice = this.productDetail.product_price * this.total;
    }
  }
}
</script>