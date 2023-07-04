<template>
  <el-container>
    <el-main>
      <el-container style="align-items: center">
        <el-header height="120px">
            <el-container>
                <el-image src="/icon.svg" style="width: 100px; height: 100px"></el-image>
                <el-text style="letter-spacing: 5px; font-size: 30px; margin-left: 20px">订单管理</el-text>
            </el-container>
        </el-header>
      </el-container>
      <el-main>
        <el-form >
          <el-form-item>
            <!-- 搜索订单 -->
              <el-image src="/icon.svg" style="width: 50px; height: 50px"></el-image>
              <el-text style="letter-spacing: 5px; font-size: 20px; margin-left: 20px">查询订单</el-text>
              <el-input  v-model="orderId" class="parameter-input" placeholder="请输入订单号"></el-input>
              <p>输入的值：{{ orderId }}</p>
              <el-button @click="findOrder" class="parameter-button">搜索订单</el-button>       
          </el-form-item>

          <el-form-item>
            <!-- 新建订单 -->
            <el-image src="/icon.svg" style="width: 50px; height: 50px"></el-image>
            <el-text style="letter-spacing: 5px; font-size: 20px; margin-left: 20px">新建订单</el-text>
            <el-input v-model="userId" class="parameter-input" placeholder="请输入用户ID"></el-input>
            <p>输入的值：{{ userId }}</p>
            <el-input v-model="storeId" class="parameter-input" placeholder="请输入商家ID"></el-input>
            <p>输入的值：{{ storeId }}</p>
            <el-input v-model="foodName" class="parameter-input" placeholder="请输入食物名"></el-input>
            <p>输入的值：{{ foodName }}</p>
            <el-input v-model="price" class="parameter-input" placeholder="请输入价格"></el-input>
            <p>输入的值：{{ price }}</p>
            <el-button @click="createOrder" class="parameter-button">创建订单</el-button>
          </el-form-item>

          <el-form-item>
            <el-image src="/icon.svg" style="width: 50px; height: 50px"></el-image>
            <el-text style="letter-spacing: 5px; font-size: 20px; margin-left: 20px">订单列表</el-text>
            <el-button @click="getStoreOrders" class="parameter-button">全部订单</el-button>
            <ul>
              <li v-for="order in orders" :key="order.orderId">{{ order.foodName }}</li>
            </ul>
            <div class="pagination">
              <el-button @click="previousPage" :disabled="currentPage === 1" class="parameter-button">上一页</el-button>
              <span> {{ currentPage }} </span>
              <el-button @click="nextPage" :disabled="currentPage === totalPages" class="parameter-button">下一页</el-button>
            </div>
          </el-form-item>
        </el-form>
        
      </el-main>
      
    </el-main>
  </el-container>
  
</template>
<style>
  .form{
    white-space: pre-wrap;
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
  }
  .pagination {
    display: flex;
    align-items: center;
  }
  .parameter-input{
    width: 200px;
  }
  .parameter-button{
    position: relative;
  }
</style>
<script>
import axios from 'axios';

export 默认 {
  data() {
    return {
      orderId: '',
      userId: '',
      storeId: '',
      foodName: '',
      price: '',
      orders: [],        // 存储所有订单数据的数组
      currentPage: 1,   // 当前页码
      pageSize: 10,     // 每页显示的订单数量
      totalPages: 0     // 总页数
    };
  },
  methods: {
    findOrder() {
      // 获取输入参数的值
      const param = this。orderId;
      axios.get('/src', param)
        。then(response => {
          this。orders = response.data;
        })
        。catch(error => {
          console.error(error);
        });
    },
    createOrder() {
      // 构造新订单的数据
      const newOrder = {
        userId: this。userId,
        storeId: this。userId,
        foodName: this。foodName,
        price: this。price
      };

      axios.post('/src', newOrder)
        。then(response => {
          console.log('Order created:', response.data);
          // 执行其他操作或更新页面
        })
        。catch(error => {
          console.error(error);
        });
    },
    completeOrder() {
      // 获取输入参数的值
      const param = this。orderId;
      axios.put('/src', param)
        。then(response => {
          console.log('Order completed:', response.data);
          // 执行其他操作或更新页面
        })
        。catch(error => {
          console.error(error);
        });
    },
    getStoreOrders() {
      // 获取输入参数的值
      const param = this。storeId;

      axios.get('/src', param)
        。then(response => {
          // 处理后端响应
          console.log(response.data);
        })
        。catch(error => {
          console.error(error);
        });
    },
    previousPage() {
      // 上一页按钮点击事件处理函数
      if (this。currentPage > 1) {
        this。currentPage--;
        this。getStoreOrders();
      }
    },
    nextPage() {
      // 下一页按钮点击事件处理函数
      if (this。currentPage < this。totalPages) {
        this。currentPage++;
        this。getStoreOrders();
      }
    }
  },
  mounted() {
    this。getStoreOrders();
  }
}
</script>
