<template>
  <main class="mt-3">
    <div class="container">
      <h2 class="text-center">제품 등록</h2>
      <div class="mb-3 row">
        <label class="col-md-3 col-form-label">제품명</label>
        <div class="col-md-9">
          <input type="text" class="form-control" v-model="product.productName">
        </div>
      </div>
      <div class="mb-3 row">
        <label class="col-md-3 col-form-label">제품가격</label>
        <div class="col-md-9">
          <div class="input-group mb-3">
            <input type="number" class="form-control" v-model="product.productPrice">
            <span class="input-group-text">원</span>
          </div>
        </div>
      </div>
      <div class="mb-3 row">
        <label class="col-md-3 col-form-label">배송비</label>
        <div class="col-md-9">
          <div class="input-group mb-3">
            <input type="number" class="form-control" v-model="product.deliveryPrice">
            <span class="input-group-text">원</span>
          </div>
        </div>
      </div>
      <div class="mb-3 row">
        <label class="col-md-3 col-form-label">추가배송비(도서산간)</label>
        <div class="col-md-9">
          <div class="input-group mb-3">
            <input type="number" class="form-control" v-model="product.addDeliveryPrice">
            <span class="input-group-text">원</span>
          </div>
        </div>
      </div>

      <div class="mb-3 row">
        <label class="col-md-3 col-form-label">출고일</label>
        <div class="col-md-9">
          <div class="input-group mb-3">
            <input type="number" class="form-control" v-model="product.outboundDays">
            <span class="input-group-text">일 이내 출고</span>
          </div>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-6 d-grid p-1">
          <button type="button" class="btn btn-lg btn-dark" @click="goToList">취소하기</button>
        </div>
        <div class="col-6 d-grid p-1">
          <button type="button" class="btn btn-lg btn-danger" @click="productInsert">저장하기</button>
        </div>
      </div>
    </div>
  </main>
</template>
<script>
export default {
  data() {
    return {
      product: {
        productName: "",
        productPrice: 0,
        deliveryPrice: 0,
        addDeliveryPrice: 0,
        outboundDays: 0,
      }
    };
  },
  computed: {

  },
  created() {
  },
  mounted() {

  },
  methods: {
    goToList() {
      this.$router.push({ path: '/' });
    },
    productInsert() {
      if (this.product.productName == "") {
        return this.$swal("제품명은 필수 입력값입니다.");
      }

      if (this.product.productPrice == "" || this.product.productPrice == 0) {
        return this.$swal("제품 가격을 입력하세요.");
      }

      if (this.product.deliveryPrice == "" || this.product.deliveryPrice == 0) {
        return this.$swal("배송료를 입력하세요.");
      }

      if (this.product.outboundDays == "" || this.product.outboundDays == 0) {
        return this.$swal("출고일을 입력하세요.");
      }

      this.$swal.fire({
        title: '정말 등록 하시겠습니까?',
        showCancelButton: true,
        confirmButtonText: `생성`,
        cancelButtonText: `취소`
      }).then(async (result) => {
        if (result.isConfirmed) {
          await this.$api("POST", "/api/product/create", this.product);
          this.$swal.fire('저장되었습니다!', '', 'success');
          this.$router.push({ path: '/' });
        }
      });
    }
  }
}
</script>