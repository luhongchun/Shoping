package com.example.demo1.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class YzTrade {

    @SerializedName(value = "trade")
    /**
     * 交易详情
     */
    private TradeDetailV2 trade;

    public void setTrade(TradeDetailV2 trade) {
        this.trade = trade;
    }

    public TradeDetailV2 getTrade() {
        return this.trade;
    }

    public static class HotelInfo {
        @SerializedName(value = "check_in_time")
        /**
        * 入住时间
        */
        private String checkInTime;
        @SerializedName(value = "check_out_time")
        /**
        * 退房时间
        */
        private String checkOutTime;
        @SerializedName(value = "accommodates")
        /**
        * 入住人信息
        */
        private String[] accommodates;
    
        public void setCheckInTime(String checkInTime) {
            this.checkInTime = checkInTime;
        }

        public String getCheckInTime() {
            return this.checkInTime;
        }

        public void setCheckOutTime(String checkOutTime) {
            this.checkOutTime = checkOutTime;
        }

        public String getCheckOutTime() {
            return this.checkOutTime;
        }

        public void setAccommodates(String[] accommodates) {
            this.accommodates = accommodates;
        }

        public String[] getAccommodates() {
            return this.accommodates;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class TradeDetailV2 {
        @SerializedName(value = "tid")
        /**
        * 交易编号
        */
        private String tid;
        @SerializedName(value = "num")
        /**
        * 商品购买数量。当一个trade对应多个order的时候，值为所有商品购买数量之和
        */
        private Long num;
        @SerializedName(value = "item_id")
        /**
        * 商品数字编号。当一个trade对应多个order的时候，值为第一个交易明细中的商品的编号
        */
        private Long itemId;
        @SerializedName(value = "price")
        /**
        * 商品价格。精确到2位小数；单位：元。当一个trade对应多个order的时候，值为第一个交易明细中的商品的价格
        */
        private Float price;
        @SerializedName(value = "pic_path")
        /**
        * 商品主图片地址。当一个trade对应多个order的时候，值为第一个交易明细中的商品的图片地址
        */
        private String picPath;
        @SerializedName(value = "pic_thumb_path")
        /**
        * 商品主图片缩略图地址
        */
        private String picThumbPath;
        @SerializedName(value = "title")
        /**
        * 交易标题，以首个商品标题作为此标题的值
        */
        private String title;
        @SerializedName(value = "type")
        /**
        * 交易类型。取值范围：<br>
        * FIXED （一口价）<br>
        * GIFT （送礼）<br>
        * BULK_PURCHASE（来自分销商的采购）<br>
        * PRESENT （赠品领取）<br>
        * GROUP （拼团订单）<br>
        * PIFA （批发订单）<br>
        * COD （货到付款）<br>
        * PEER （代付）<br>
        * QRCODE（扫码商家二维码直接支付的交易）<br>
        * QRCODE_3RD（线下收银台二维码交易)
        */
        private String type;
        @SerializedName(value = "buyer_message")
        /**
        * 买家购买附言
        */
        private String buyerMessage;
        @SerializedName(value = "seller_flag")
        /**
        * 卖家备注星标，取值范围 1、2、3、4、5；<br>如果为0，表示没有备注星标
        */
        private Long sellerFlag;
        @SerializedName(value = "trade_memo")
        /**
        * 卖家对该交易的备注
        */
        private String tradeMemo;
        @SerializedName(value = "receiver_city")
        /**
        * 收货人的所在城市。<br>
        * PS：如果订单类型是送礼订单，收货地址在sub_trades字段中；如果物流方式是到店自提，收货地址在fetch_detail字段中
        */
        private String receiverCity;
        @SerializedName(value = "receiver_district")
        /**
        * 收货人的所在地区
        */
        private String receiverDistrict;
        @SerializedName(value = "receiver_name")
        /**
        * 收货人的姓名
        */
        private String receiverName;
        @SerializedName(value = "receiver_state")
        /**
        * 收货人的所在省份
        */
        private String receiverState;
        @SerializedName(value = "receiver_address")
        /**
        * 收货人的详细地址
        */
        private String receiverAddress;
        @SerializedName(value = "receiver_zip")
        /**
        * 收货人的邮编
        */
        private String receiverZip;
        @SerializedName(value = "receiver_mobile")
        /**
        * 收货人的手机号码
        */
        private String receiverMobile;
        @SerializedName(value = "feedback")
        /**
        * 交易维权状态。<br>
				0 无维权，1 顾客发起维权，2 顾客拒绝商家的处理结果，3 顾客接受商家的处理结果，9 商家正在处理,101 维权处理中,110 维权结束。<br>
		        备注：1到10的状态码是微信维权状态码，100以上的状态码是有赞维权状态码
        */
        private Long feedback;
        @SerializedName(value = "refund_state")
        /**
        * 退款状态。取值范围：<br>
        * NO_REFUND（无退款）<br>
        * PARTIAL_REFUNDING（部分退款中）<br>
        * PARTIAL_REFUNDED（已部分退款）<br>
        * PARTIAL_REFUND_FAILED（部分退款失败）<br>
        * FULL_REFUNDING（全额退款中）<br>
        * FULL_REFUNDED（已全额退款）<br>
        * FULL_REFUND_FAILED（全额退款失败）<br>
        */
        private String refundState;
        @SerializedName(value = "outer_tid")
        /**
        * 外部交易编号。比如，如果支付方式是微信支付，就是财付通的交易单号
        */
        private String outerTid;
        @SerializedName(value = "transaction_tid")
        /**
        * 支付流水号
        */
        private String transactionTid;
        @SerializedName(value = "status")
        /**
        * 交易状态。取值范围：<br>
        * TRADE_NO_CREATE_PAY (没有创建支付交易) <br>
        * WAIT_BUYER_PAY (等待买家付款) <br>
        * WAIT_PAY_RETURN (等待支付确认) <br>
        * WAIT_GROUP（等待成团，即：买家已付款，等待成团）<br>
        * WAIT_SELLER_SEND_GOODS (等待卖家发货，即：买家已付款) <br>
        * WAIT_BUYER_CONFIRM_GOODS (等待买家确认收货，即：卖家已发货) <br>
        * TRADE_BUYER_SIGNED (买家已签收) <br>
        * TRADE_CLOSED (付款以后用户退款成功，交易自动关闭) <br>
        * TRADE_CLOSED_BY_USER (付款以前，卖家或买家主动关闭交易)<br>
        */
        private String status;
        @SerializedName(value = "shipping_type")
        /**
        * 创建交易时的物流方式。取值范围：express（快递），fetch（到店自提），local（同城配送）
        */
        private String shippingType;
        @SerializedName(value = "post_fee")
        /**
        * 运费。单位：元，精确到分
        */
        private Float postFee;
        @SerializedName(value = "total_fee")
        /**
        * 商品总价（商品价格乘以数量的总金额）。单位：元，精确到分
        */
        private Float totalFee;
        @SerializedName(value = "refunded_fee")
        /**
        * 交易完成后退款的金额。单位：元，精确到分
        */
        private Float refundedFee;
        @SerializedName(value = "discount_fee")
        /**
        * 交易优惠金额（不包含交易明细中的优惠金额）。单位：元，精确到分
        */
        private Float discountFee;
        @SerializedName(value = "payment")
        /**
        * 实付金额。单位：元，精确到分
        */
        private Float payment;
        @SerializedName(value = "created")
        /**
        * 交易创建时间
        */
        private Date created;
        @SerializedName(value = "update_time")
        /**
        * 交易更新时间。当交易的：状态改变、备注更改、星标更改 等情况下都会刷新更新时间
        */
        private Date updateTime;
        @SerializedName(value = "pay_time")
        /**
        * 买家付款时间
        */
        private Date payTime;
        @SerializedName(value = "pay_type")
        /**
        * 支付类型。取值范围：<br>
        * WEIXIN (微信自有支付)<br>
        * WEIXIN_DAIXIAO (微信代销支付)<br>
        * ALIPAY (支付宝支付)<br>
        * BANKCARDPAY (银行卡支付)<br>
        * PEERPAY (代付)<br>
        * CODPAY (货到付款)<br>
        * BAIDUPAY (百度钱包支付)<br>
        * PRESENTTAKE (直接领取赠品)<br>
        * COUPONPAY（优惠券/码全额抵扣）<br>
        * BULKPURCHASE（来自分销商的采购）<br>
        * MERGEDPAY (合并付货款) <br>
        * ECARD（有赞E卡支付）
        */
        private String payType;
        @SerializedName(value = "consign_time")
        /**
        * 卖家发货时间
        */
        private Date consignTime;
        @SerializedName(value = "sign_time")
        /**
        * 买家签收时间
        */
        private Date signTime;
        @SerializedName(value = "buyer_area")
        /**
        * 买家下单的地区
        */
        private String buyerArea;
        @SerializedName(value = "orders")
        /**
        * 交易明细数据结构
        */
        private TradeOrderV2[] orders;
        @SerializedName(value = "fetch_detail")
        /**
        * 到店自提详情
        */
        private TradeFetch fetchDetail;
        @SerializedName(value = "coupon_details")
        /**
        * 订单中使用到的卡券的数据结构
        */
        private UmpTradeCoupon[] couponDetails;
        @SerializedName(value = "fans_info")
        /**
        * 用户信息
        */
        private FansInfo fansInfo;
        /*@SerializedName(value = "hotel_info")
        private HotelInfo hotelInfo;*/
        @SerializedName(value = "promotion_details")
        /**
        * 订单中使用到的优惠活动的数据结构
        */
        private TradePromotion[] promotionDetails;
        @SerializedName(value = "adjust_fee")
        /**
        * 改价信息
        */
        private AdjustFee adjustFee;
        @SerializedName(value = "sub_trades")
        /**
        * 交易数据结构
        */
        private TradeDetailV2[] subTrades;
        @SerializedName(value = "relation_type")
        /**
        * 分销/采购单:source:采购单;fenxiao:分销单 空串则为非分销/采购单
        */
        private String relationType;
        @SerializedName(value = "relations")
        /**
        * relation_type返回source时,为分销订单号列表<br>
        * 返回fenxiao时,为供应商订单号列表<br>
        * 返回空时,列表返回空
        */
        private String[] relations;
        @SerializedName(value = "out_trade_no")
        /**
        * 代付订单外部交易号列表,非代付订单类型返回空
        */
        private String[] outTradeNo;
        @SerializedName(value = "profit")
        /**
        * 利润（分销订单特有）。格式：5.20；单位：元；精确到：分
        */
        private Float profit;
        @SerializedName(value = "handled")
        /**
        * 结算状态（分销订单特有）。1：已结算，0：未结算
        */
        private Long handled;
        @SerializedName(value = "outer_user_id")
        /**
        * 三方APP用户id
        */
        private String outerUserId;
        @SerializedName(value = "shop_id")
        /**
        * 多门店订单的门店id 非多门店订单则默认为0 
        */
        private Long shopId;
        @SerializedName(value = "offline_id")
        /**
        * 表示线下网点id，包含自提点和门店
        */
        private Long offlineId;
        @SerializedName(value = "points_price")
        /**
        * 积分兑换订单，数值代表消耗的积分 非积分兑换订单默认为0 
        */
        private Long pointsPrice;
        @SerializedName(value = "tuan_no")
        /**
        * 拼团订单对应的团编号
        */
        private String tuanNo;
        @SerializedName(value = "is_tuan_head")
        /**
        * 是否为团长订单 1 团长订单 0 非拼团订单 或 非团长订单
        */
        private Long isTuanHead;
        @SerializedName(value = "qr_id")
        /**
        * 收银台订单的二维码id号
        */
        private Long qrId;
        @SerializedName(value = "delivery_time_display")
        /**
        * 同城送订单送达时间
        */
        private String deliveryTimeDisplay;
    
        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTid() {
            return this.tid;
        }

        public void setNum(Long num) {
            this.num = num;
        }

        public Long getNum() {
            return this.num;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getItemId() {
            return this.itemId;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getPrice() {
            return this.price;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getPicPath() {
            return this.picPath;
        }

        public void setPicThumbPath(String picThumbPath) {
            this.picThumbPath = picThumbPath;
        }

        public String getPicThumbPath() {
            return this.picThumbPath;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setBuyerMessage(String buyerMessage) {
            this.buyerMessage = buyerMessage;
        }

        public String getBuyerMessage() {
            return this.buyerMessage;
        }

        public void setSellerFlag(Long sellerFlag) {
            this.sellerFlag = sellerFlag;
        }

        public Long getSellerFlag() {
            return this.sellerFlag;
        }

        public void setTradeMemo(String tradeMemo) {
            this.tradeMemo = tradeMemo;
        }

        public String getTradeMemo() {
            return this.tradeMemo;
        }

        public void setReceiverCity(String receiverCity) {
            this.receiverCity = receiverCity;
        }

        public String getReceiverCity() {
            return this.receiverCity;
        }

        public void setReceiverDistrict(String receiverDistrict) {
            this.receiverDistrict = receiverDistrict;
        }

        public String getReceiverDistrict() {
            return this.receiverDistrict;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getReceiverName() {
            return this.receiverName;
        }

        public void setReceiverState(String receiverState) {
            this.receiverState = receiverState;
        }

        public String getReceiverState() {
            return this.receiverState;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getReceiverAddress() {
            return this.receiverAddress;
        }

        public void setReceiverZip(String receiverZip) {
            this.receiverZip = receiverZip;
        }

        public String getReceiverZip() {
            return this.receiverZip;
        }

        public void setReceiverMobile(String receiverMobile) {
            this.receiverMobile = receiverMobile;
        }

        public String getReceiverMobile() {
            return this.receiverMobile;
        }

        public void setFeedback(Long feedback) {
            this.feedback = feedback;
        }

        public Long getFeedback() {
            return this.feedback;
        }

        public void setRefundState(String refundState) {
            this.refundState = refundState;
        }

        public String getRefundState() {
            return this.refundState;
        }

        public void setOuterTid(String outerTid) {
            this.outerTid = outerTid;
        }

        public String getOuterTid() {
            return this.outerTid;
        }

        public void setTransactionTid(String transactionTid) {
            this.transactionTid = transactionTid;
        }

        public String getTransactionTid() {
            return this.transactionTid;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }

        public void setShippingType(String shippingType) {
            this.shippingType = shippingType;
        }

        public String getShippingType() {
            return this.shippingType;
        }

        public void setPostFee(Float postFee) {
            this.postFee = postFee;
        }

        public Float getPostFee() {
            return this.postFee;
        }

        public void setTotalFee(Float totalFee) {
            this.totalFee = totalFee;
        }

        public Float getTotalFee() {
            return this.totalFee;
        }

        public void setRefundedFee(Float refundedFee) {
            this.refundedFee = refundedFee;
        }

        public Float getRefundedFee() {
            return this.refundedFee;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }

        public void setPayment(Float payment) {
            this.payment = payment;
        }

        public Float getPayment() {
            return this.payment;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public Date getCreated() {
            return this.created;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public Date getUpdateTime() {
            return this.updateTime;
        }

        public void setPayTime(Date payTime) {
            this.payTime = payTime;
        }

        public Date getPayTime() {
            return this.payTime;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayType() {
            return this.payType;
        }

        public void setConsignTime(Date consignTime) {
            this.consignTime = consignTime;
        }

        public Date getConsignTime() {
            return this.consignTime;
        }

        public void setSignTime(Date signTime) {
            this.signTime = signTime;
        }

        public Date getSignTime() {
            return this.signTime;
        }

        public void setBuyerArea(String buyerArea) {
            this.buyerArea = buyerArea;
        }

        public String getBuyerArea() {
            return this.buyerArea;
        }

        public void setOrders(TradeOrderV2[] orders) {
            this.orders = orders;
        }

        public TradeOrderV2[] getOrders() {
            return this.orders;
        }

        public void setFetchDetail(TradeFetch fetchDetail) {
            this.fetchDetail = fetchDetail;
        }

        public TradeFetch getFetchDetail() {
            return this.fetchDetail;
        }

        public void setCouponDetails(UmpTradeCoupon[] couponDetails) {
            this.couponDetails = couponDetails;
        }

        public UmpTradeCoupon[] getCouponDetails() {
            return this.couponDetails;
        }

        public void setFansInfo(FansInfo fansInfo) {
            this.fansInfo = fansInfo;
        }

        public FansInfo getFansInfo() {
            return this.fansInfo;
        }

        /*public void setHotelInfo(HotelInfo hotelInfo) {
            this.hotelInfo = hotelInfo;
        }

        public HotelInfo getHotelInfo() {
            return this.hotelInfo;
        }*/

        public void setPromotionDetails(TradePromotion[] promotionDetails) {
            this.promotionDetails = promotionDetails;
        }

        public TradePromotion[] getPromotionDetails() {
            return this.promotionDetails;
        }

        public void setAdjustFee(AdjustFee adjustFee) {
            this.adjustFee = adjustFee;
        }

        public AdjustFee getAdjustFee() {
            return this.adjustFee;
        }

        public void setSubTrades(TradeDetailV2[] subTrades) {
            this.subTrades = subTrades;
        }

        public TradeDetailV2[] getSubTrades() {
            return this.subTrades;
        }

        public void setRelationType(String relationType) {
            this.relationType = relationType;
        }

        public String getRelationType() {
            return this.relationType;
        }

        public void setRelations(String[] relations) {
            this.relations = relations;
        }

        public String[] getRelations() {
            return this.relations;
        }

        public void setOutTradeNo(String[] outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String[] getOutTradeNo() {
            return this.outTradeNo;
        }

        public void setProfit(Float profit) {
            this.profit = profit;
        }

        public Float getProfit() {
            return this.profit;
        }

        public void setHandled(Long handled) {
            this.handled = handled;
        }

        public Long getHandled() {
            return this.handled;
        }

        public void setOuterUserId(String outerUserId) {
            this.outerUserId = outerUserId;
        }

        public String getOuterUserId() {
            return this.outerUserId;
        }

        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        public Long getShopId() {
            return this.shopId;
        }

        public void setOfflineId(Long offlineId) {
            this.offlineId = offlineId;
        }

        public Long getOfflineId() {
            return this.offlineId;
        }

        public void setPointsPrice(Long pointsPrice) {
            this.pointsPrice = pointsPrice;
        }

        public Long getPointsPrice() {
            return this.pointsPrice;
        }

        public void setTuanNo(String tuanNo) {
            this.tuanNo = tuanNo;
        }

        public String getTuanNo() {
            return this.tuanNo;
        }

        public void setIsTuanHead(Long isTuanHead) {
            this.isTuanHead = isTuanHead;
        }

        public Long getIsTuanHead() {
            return this.isTuanHead;
        }

        public void setQrId(Long qrId) {
            this.qrId = qrId;
        }

        public Long getQrId() {
            return this.qrId;
        }

        public void setDeliveryTimeDisplay(String deliveryTimeDisplay) {
            this.deliveryTimeDisplay = deliveryTimeDisplay;
        }

        public String getDeliveryTimeDisplay() {
            return this.deliveryTimeDisplay;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class TradeFetch {
        @SerializedName(value = "fetcher_name")
        /**
        * 领取人（即：预约人）的姓名
        */
        private String fetcherName;
        @SerializedName(value = "fetcher_mobile")
        /**
        * 领取人的手机号
        */
        private String fetcherMobile;
        @SerializedName(value = "fetch_time")
        /**
        * 预约的领取时间。新版到店自提的数据格式:<br>
1: 2016-04-18 17:00-17:15<br>
2: 尽快到店提货
        */
        private String fetchTime;
        @SerializedName(value = "shop_id")
        /**
        * 自提点id
        */
        private Long shopId;
        @SerializedName(value = "shop_name")
        /**
        * 自提点名称
        */
        private String shopName;
        @SerializedName(value = "shop_mobile")
        /**
        * 自提点联系方式
        */
        private String shopMobile;
        @SerializedName(value = "shop_state")
        /**
        * 自提点所在省份
        */
        private String shopState;
        @SerializedName(value = "shop_city")
        /**
        * 自提点所在城市
        */
        private String shopCity;
        @SerializedName(value = "shop_district")
        /**
        * 自提点所在地区
        */
        private String shopDistrict;
        @SerializedName(value = "shop_address")
        /**
        * 自提点详细地址
        */
        private String shopAddress;
    
        public void setFetcherName(String fetcherName) {
            this.fetcherName = fetcherName;
        }

        public String getFetcherName() {
            return this.fetcherName;
        }

        public void setFetcherMobile(String fetcherMobile) {
            this.fetcherMobile = fetcherMobile;
        }

        public String getFetcherMobile() {
            return this.fetcherMobile;
        }

        public void setFetchTime(String fetchTime) {
            this.fetchTime = fetchTime;
        }

        public String getFetchTime() {
            return this.fetchTime;
        }

        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        public Long getShopId() {
            return this.shopId;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopName() {
            return this.shopName;
        }

        public void setShopMobile(String shopMobile) {
            this.shopMobile = shopMobile;
        }

        public String getShopMobile() {
            return this.shopMobile;
        }

        public void setShopState(String shopState) {
            this.shopState = shopState;
        }

        public String getShopState() {
            return this.shopState;
        }

        public void setShopCity(String shopCity) {
            this.shopCity = shopCity;
        }

        public String getShopCity() {
            return this.shopCity;
        }

        public void setShopDistrict(String shopDistrict) {
            this.shopDistrict = shopDistrict;
        }

        public String getShopDistrict() {
            return this.shopDistrict;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopAddress() {
            return this.shopAddress;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class TradePromotion {
        @SerializedName(value = "promotion_id")
        /**
        * 该优惠活动的ID
        */
        private Long promotionId;
        @SerializedName(value = "promotion_name")
        /**
        * 该优惠活动的名称
        */
        private String promotionName;
        @SerializedName(value = "promotion_type")
        /**
        * 优惠的类型。可选值：<br> FULLREDUCE（满减满送）<br> ORDERCASHBACK（订单返现）<br> GROUPBUYING（商品团购）<br> GROUPON（多人拼团）<br> SECKILL（秒杀）<br> AUCTION（降价拍）
        */
        private String promotionType;
        @SerializedName(value = "promotion_condition")
        /**
        * 优惠使用条件说明
        */
        private String promotionCondition;
        @SerializedName(value = "used_at")
        /**
        * 使用时间
        */
        private Date usedAt;
        @SerializedName(value = "discount_fee")
        /**
        * 优惠的金额，单位：元，精确到小数点后两位
        */
        private Float discountFee;
    
        public void setPromotionId(Long promotionId) {
            this.promotionId = promotionId;
        }

        public Long getPromotionId() {
            return this.promotionId;
        }

        public void setPromotionName(String promotionName) {
            this.promotionName = promotionName;
        }

        public String getPromotionName() {
            return this.promotionName;
        }

        public void setPromotionType(String promotionType) {
            this.promotionType = promotionType;
        }

        public String getPromotionType() {
            return this.promotionType;
        }

        public void setPromotionCondition(String promotionCondition) {
            this.promotionCondition = promotionCondition;
        }

        public String getPromotionCondition() {
            return this.promotionCondition;
        }

        public void setUsedAt(Date usedAt) {
            this.usedAt = usedAt;
        }

        public Date getUsedAt() {
            return this.usedAt;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class TradeBuyerMessage {
        @SerializedName(value = "title")
        /**
        * 留言的标题
        */
        private String title;
        @SerializedName(value = "content")
        /**
        * 留言的内容
        */
        private String content;
    
        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class FansInfo {
        @SerializedName(value = "fans_nickname")
        /**
        * 粉丝昵称 订单信息中存在三方(例如微信)粉丝昵称则取粉丝昵称;取不到粉丝昵称时则使用买家手机号;以上两点未满足时取买家收货人信息;无收货人信息时返回[匿名]
        */
        private String fansNickname;
        @SerializedName(value = "fans_id")
        /**
        * 粉丝id
        */
        private Long fansId;
        @SerializedName(value = "buyer_id")
        /**
        * 有赞买家ID
        */
        private Long buyerId;
        @SerializedName(value = "fans_type")
        /**
        * 0:未知、1:微信自有粉丝
        */
        private Long fansType;
    
        public void setFansNickname(String fansNickname) {
            this.fansNickname = fansNickname;
        }

        public String getFansNickname() {
            return this.fansNickname;
        }

        public void setFansId(Long fansId) {
            this.fansId = fansId;
        }

        public Long getFansId() {
            return this.fansId;
        }

        public void setBuyerId(Long buyerId) {
            this.buyerId = buyerId;
        }

        public Long getBuyerId() {
            return this.buyerId;
        }

        public void setFansType(Long fansType) {
            this.fansType = fansType;
        }

        public Long getFansType() {
            return this.fansType;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class UmpTradeCoupon {
        @SerializedName(value = "coupon_id")
        /**
        * 该组卡券的ID
        */
        private Long couponId;
        @SerializedName(value = "coupon_name")
        /**
        * 该组卡券的名称
        */
        private String couponName;
        @SerializedName(value = "coupon_type")
        /**
        * 卡券的类型。可选值：PROMOCARD（优惠券）、PROMOCODE（优惠码）
        */
        private String couponType;
        @SerializedName(value = "coupon_content")
        /**
        * 卡券内容。当卡券类型为优惠码时，值为优惠码字符串
        */
        private String couponContent;
        @SerializedName(value = "coupon_description")
        /**
        * 卡券的说明
        */
        private String couponDescription;
        @SerializedName(value = "coupon_condition")
        /**
        * 卡券使用条件说明
        */
        private String couponCondition;
        @SerializedName(value = "used_at")
        /**
        * 使用时间
        */
        private Date usedAt;
        @SerializedName(value = "discount_fee")
        /**
        * 优惠的金额，单位：元，精确到小数点后两位
        */
        private Float discountFee;
    
        public void setCouponId(Long couponId) {
            this.couponId = couponId;
        }

        public Long getCouponId() {
            return this.couponId;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getCouponName() {
            return this.couponName;
        }

        public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public String getCouponType() {
            return this.couponType;
        }

        public void setCouponContent(String couponContent) {
            this.couponContent = couponContent;
        }

        public String getCouponContent() {
            return this.couponContent;
        }

        public void setCouponDescription(String couponDescription) {
            this.couponDescription = couponDescription;
        }

        public String getCouponDescription() {
            return this.couponDescription;
        }

        public void setCouponCondition(String couponCondition) {
            this.couponCondition = couponCondition;
        }

        public String getCouponCondition() {
            return this.couponCondition;
        }

        public void setUsedAt(Date usedAt) {
            this.usedAt = usedAt;
        }

        public Date getUsedAt() {
            return this.usedAt;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class TradeOrderV2 {
        @SerializedName(value = "oid")
        /**
        * 交易明细编号。该编号并不唯一，只用于区分交易内的多条明细记录
        */
        private Long oid;
        @SerializedName(value = "item_id")
        /**
        * 商品数字编号
        */
        private Long itemId;
        @SerializedName(value = "sku_id")
        /**
        * Sku的ID，sku_id 在系统里<span style="color: #ff0000;">并不是唯一的</span>，结合商品ID一起使用才是唯一的。
        */
        private Long skuId;
        @SerializedName(value = "sku_unique_code")
        /**
        * Sku在系统中的唯一编号，可以在开发者的系统中用作 Sku 的唯一ID，但不能用于调用接口
        */
        private String skuUniqueCode;
        @SerializedName(value = "num")
        /**
        * 商品购买数量
        */
        private Long num;
        @SerializedName(value = "outer_sku_id")
        /**
        * 商家编码（商家为Sku设置的外部编号）
        */
        private String outerSkuId;
        @SerializedName(value = "outer_item_id")
        /**
        * 商品货号（商家为商品设置的外部编号）
        */
        private String outerItemId;
        @SerializedName(value = "title")
        /**
        * 商品标题
        */
        private String title;
        @SerializedName(value = "seller_nick")
        /**
        * 卖家昵称
        */
        private String sellerNick;
        @SerializedName(value = "fenxiao_price")
        /**
        * 商品在分销商那边的出售价格。精确到2位小数；单位：元。如果是采购单才有值，否则值为 0
        */
        private Float fenxiaoPrice;
        @SerializedName(value = "fenxiao_payment")
        /**
        * 商品在分销商那边的实付金额。精确到2位小数；单位：元。如果是采购单才有值，否则值为 0
        */
        private Float fenxiaoPayment;
        @SerializedName(value = "price")
        /**
        * 商品价格。精确到2位小数；单位：元
        */
        private Float price;
        @SerializedName(value = "total_fee")
        /**
        * 应付金额（商品价格乘以数量的总金额）
        */
        private Float totalFee;
        @SerializedName(value = "discount_fee")
        /**
        * 交易明细内的优惠金额。精确到2位小数，单位：元
        */
        private Float discountFee;
        @SerializedName(value = "payment")
        /**
        * 实付金额。精确到2位小数，单位：元
        */
        private Float payment;
        @SerializedName(value = "sku_properties_name")
        /**
        * SKU的值，即：商品的规格。如：机身颜色:黑色;手机套餐:官方标配
        */
        private String skuPropertiesName;
        @SerializedName(value = "pic_path")
        /**
        * 商品主图片地址
        */
        private String picPath;
        @SerializedName(value = "pic_thumb_path")
        /**
        * 商品主图片缩略图地址
        */
        private String picThumbPath;
        @SerializedName(value = "item_type")
        /**
        * 商品类型。<br>0：普通商品；<br>10：分销商品;
        */
        private Long itemType;
        @SerializedName(value = "buyer_messages")
        /**
        * 交易明细中买家留言的数据结构
        */
        private TradeBuyerMessage[] buyerMessages;
        @SerializedName(value = "order_promotion_details")
        /**
        * 交易明细中的优惠信息的数据结构
        */
        private TradeOrderPromotion[] orderPromotionDetails;
        @SerializedName(value = "state_str")
        /**
        * 商品状态
        */
        private String stateStr;
        @SerializedName(value = "item_refund_state")
        /**
        * 商品退款状态
        */
        private String itemRefundState;
        @SerializedName(value = "is_virtual")
        /**
        * 1 虚拟商品 0 非虚拟商品
        */
        private Long isVirtual;
        @SerializedName(value = "is_present")
        /**
        * 1 赠品商品 0 普通商品
        */
        private Long isPresent;
        @SerializedName(value = "refunded_fee")
        /**
        * 退款金额
        */
        private Float refundedFee;
        @SerializedName(value = "allow_send")
        /**
        * 是否允许发货  1 可以发货 0 不能发货
        */
        private Long allowSend;
    
        public void setOid(Long oid) {
            this.oid = oid;
        }

        public Long getOid() {
            return this.oid;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getItemId() {
            return this.itemId;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public Long getSkuId() {
            return this.skuId;
        }

        public void setSkuUniqueCode(String skuUniqueCode) {
            this.skuUniqueCode = skuUniqueCode;
        }

        public String getSkuUniqueCode() {
            return this.skuUniqueCode;
        }

        public void setNum(Long num) {
            this.num = num;
        }

        public Long getNum() {
            return this.num;
        }

        public void setOuterSkuId(String outerSkuId) {
            this.outerSkuId = outerSkuId;
        }

        public String getOuterSkuId() {
            return this.outerSkuId;
        }

        public void setOuterItemId(String outerItemId) {
            this.outerItemId = outerItemId;
        }

        public String getOuterItemId() {
            return this.outerItemId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setSellerNick(String sellerNick) {
            this.sellerNick = sellerNick;
        }

        public String getSellerNick() {
            return this.sellerNick;
        }

        public void setFenxiaoPrice(Float fenxiaoPrice) {
            this.fenxiaoPrice = fenxiaoPrice;
        }

        public Float getFenxiaoPrice() {
            return this.fenxiaoPrice;
        }

        public void setFenxiaoPayment(Float fenxiaoPayment) {
            this.fenxiaoPayment = fenxiaoPayment;
        }

        public Float getFenxiaoPayment() {
            return this.fenxiaoPayment;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getPrice() {
            return this.price;
        }

        public void setTotalFee(Float totalFee) {
            this.totalFee = totalFee;
        }

        public Float getTotalFee() {
            return this.totalFee;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }

        public void setPayment(Float payment) {
            this.payment = payment;
        }

        public Float getPayment() {
            return this.payment;
        }

        public void setSkuPropertiesName(String skuPropertiesName) {
            this.skuPropertiesName = skuPropertiesName;
        }

        public String getSkuPropertiesName() {
            return this.skuPropertiesName;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getPicPath() {
            return this.picPath;
        }

        public void setPicThumbPath(String picThumbPath) {
            this.picThumbPath = picThumbPath;
        }

        public String getPicThumbPath() {
            return this.picThumbPath;
        }

        public void setItemType(Long itemType) {
            this.itemType = itemType;
        }

        public Long getItemType() {
            return this.itemType;
        }

        public void setBuyerMessages(TradeBuyerMessage[] buyerMessages) {
            this.buyerMessages = buyerMessages;
        }

        public TradeBuyerMessage[] getBuyerMessages() {
            return this.buyerMessages;
        }

        public void setOrderPromotionDetails(TradeOrderPromotion[] orderPromotionDetails) {
            this.orderPromotionDetails = orderPromotionDetails;
        }

        public TradeOrderPromotion[] getOrderPromotionDetails() {
            return this.orderPromotionDetails;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public String getStateStr() {
            return this.stateStr;
        }

        public void setItemRefundState(String itemRefundState) {
            this.itemRefundState = itemRefundState;
        }

        public String getItemRefundState() {
            return this.itemRefundState;
        }

        public void setIsVirtual(Long isVirtual) {
            this.isVirtual = isVirtual;
        }

        public Long getIsVirtual() {
            return this.isVirtual;
        }

        public void setIsPresent(Long isPresent) {
            this.isPresent = isPresent;
        }

        public Long getIsPresent() {
            return this.isPresent;
        }

        public void setRefundedFee(Float refundedFee) {
            this.refundedFee = refundedFee;
        }

        public Float getRefundedFee() {
            return this.refundedFee;
        }

        public void setAllowSend(Long allowSend) {
            this.allowSend = allowSend;
        }

        public Long getAllowSend() {
            return this.allowSend;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class AdjustFee {
        @SerializedName(value = "change")
        /**
        * 总改价金额
        */
        private Float change;
        @SerializedName(value = "pay_change")
        /**
        * 订单改价
        */
        private Float payChange;
        @SerializedName(value = "post_change")
        /**
        * 邮费改价
        */
        private Float postChange;
    
        public void setChange(Float change) {
            this.change = change;
        }

        public Float getChange() {
            return this.change;
        }

        public void setPayChange(Float payChange) {
            this.payChange = payChange;
        }

        public Float getPayChange() {
            return this.payChange;
        }

        public void setPostChange(Float postChange) {
            this.postChange = postChange;
        }

        public Float getPostChange() {
            return this.postChange;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    public static class TradeOrderPromotion {
        @SerializedName(value = "promotion_name")
        /**
        * 优惠的名称
        */
        private String promotionName;
        @SerializedName(value = "promotion_type")
        /**
        * 优惠的类型。可选值：<br>MEMBER_CARD_DISCOUNT（会员卡折扣）
						<br>SCAN_DISCOUNT（扫码折扣）
						<br>SCAN_DECREASE（扫码减额优惠）
						<br>TIMELIMITED_DISCOUNT（限时折扣）
        */
        private String promotionType;
        @SerializedName(value = "apply_at")
        /**
        * 应用优惠的时间
        */
        private Date applyAt;
        @SerializedName(value = "discount_fee")
        /**
        * 优惠的金额，单位：元，精确到小数点后两位
        */
        private Float discountFee;
    
        public void setPromotionName(String promotionName) {
            this.promotionName = promotionName;
        }

        public String getPromotionName() {
            return this.promotionName;
        }

        public void setPromotionType(String promotionType) {
            this.promotionType = promotionType;
        }

        public String getPromotionType() {
            return this.promotionType;
        }

        public void setApplyAt(Date applyAt) {
            this.applyAt = applyAt;
        }

        public Date getApplyAt() {
            return this.applyAt;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}
/* {"num":1,
 * "goods_kind":1,
 * "num_iid":"328949561",
 * "price":"30.00",
 * "pic_path":"https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg",
 * "pic_thumb_path":"https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg?imageView2/2/w/200/h/0/q/75/format/jpg",
 * "title":"萌宝直播",
 * "type":"FIXED",
 * "discount_fee":"0.00",
 * "order_type":"0",
 * "status":"WAIT_BUYER_PAY",
 * "status_str":"待付款",
 * "refund_state":"NO_REFUND",
 * "shipping_type":"express",
 * "post_fee":"0.00",
 * "total_fee":"30.00",
 * "refunded_fee":"0.00",
 * "payment":"30.00",
 * "created":"2017-04-14 17:33:34",
 * "update_time":"2017-04-14 17:33:34",
 * "pay_time":"",
 * "pay_type":"",
 * "consign_time":"",
 * "sign_time":"",
 * "buyer_area":"",
 * "seller_flag":0,
 * "buyer_message":"",
 * "orders":[
 *     {"alias":"2xf9nkvju8frr",
 *     "oid":17906433,
 *     "outer_sku_id":"",
 *     "outer_item_id":"",
 *     "title":"萌宝直播",
 *     "seller_nick":"智启萌宝日记",
 *     "fenxiao_price":"0.00",
 *     "fenxiao_payment":"0.00",
 *     "price":"30.00",
 *     "total_fee":"30.00",
 *     "payment":"30.00",
 *     "discount_fee":"0.00",
 *     "sku_id":"36136582",
 *     "sku_unique_code":"32894956136136582",
 *     "sku_properties_name":"套餐:1个月;规格:单人",
 *     "pic_path":"https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg",
 *     "pic_thumb_path":"https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg?imageView2/2/w/200/h/0/q/75/format/jpg",
 *     "item_type":0,
 *     "buyer_messages":[{"title":"手机号","content":"15110007268"},{"title":"孩子姓名","content":"姜小宝"},{"title":"所在班级","content":"托一班"},{"title":"手机号2","content":""},{"title":"手机号3","content":""}],
 *     "order_promotion_details":[],
 *     "state_str":"待付款",
 *     "allow_send":0,
 *     "is_send":0,
 *     "item_refund_state":"NO_REFUND",
 *     "is_virtual":1,
 *     "is_present":0,
 *     "refunded_fee":"0.00",
 *     "unit":"件",
 *     "num_iid":"328949561",
 *     "num":"1"}
 * ],
 * "fetch_detail":null,
 * "coupon_details":[],
 * "promotion_details":[],
 * "adjust_fee":{"change":"0.00","pay_change":"0.00","post_change":"0.00"},
 * "sub_trades":[],
 * "weixin_user_id":"2465691097",
 * "button_list":[{"tool_icon":"https://img.yzcdn.cn/upload_files/2015/08/28/Ftodf5cdSlo-vaH1Jty4GylCJLr5.png","tool_title":"改价","tool_value":"","tool_type":"goto_native:trade_change_price","tool_parameter":"{}","new_sign":"0","create_time":""},{"tool_icon":"https://img.yzcdn.cn/upload_files/2015/08/28/Flv3HyyRB2DGyXsPwRqnZvA4pHla.png","tool_title":"关闭","tool_value":"","tool_type":"goto_native:trade_close","tool_parameter":"","new_sign":"","create_time":""},{"tool_icon":"https://img.yzcdn.cn/upload_files/2015/08/28/FpO1UIXyOEZO026tWIgUOm9uZnT2.png","tool_title":"备注","tool_value":"","tool_type":"goto_native:trade_memo","tool_parameter":"{}","new_sign":"0","create_time":""}],
 * "feedback_num":0,
 * "trade_memo":"",
 * "fans_info":{"fans_nickname":"海子蓝","fans_id":"2465691097","buyer_id":"136847012","fans_type":"1"},
 * "buy_way_str":"",
 * "pf_buy_way_str":"",
 * "send_num":0,
 * "user_id":"136847012",
 * "kind":1,
 * "relation_type":"",
 * "relations":[],
 * "out_trade_no":[],
 * "group_no":"",
 * "outer_user_id":0,
 * "shop_id":"0",
 * "shop_type":"1",
 * "points_price":0,
 * "delivery_start_time":0,
 * "delivery_end_time":0,
 * "tuan_no":"",
 * "is_tuan_head":0,
 * "delivery_time_display":"",
 * "hotel_info":"",
 * "order_mark":"",
 * "qr_id":0,
 * "buyer_nick":"海子蓝",
 * "tid":"E20170414173334067618241",
 * "buyer_type":"1",
 * "buyer_id":"2465691097",
 * "receiver_city":"",
 * "receiver_district":"",
 * "receiver_name":"",
 * "receiver_state":"",
 * "receiver_address":"",
 * "receiver_zip":"",
 * "receiver_mobile":"",
 * "feedback":0,
 * "outer_tid":"",
 * "service_phone":""
 * } */
