package com.example.demo1.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dao.ZhiqiShopRepository;
import com.example.demo1.entity.Employee;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradeGetResult.TradeBuyerMessage;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradeGetResult.TradeDetailV2;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradeGetResult.TradeOrderV2;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetResult.TradeFetch;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetResult.TradeBuyerMessage;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetResult.FansInfo;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetResult.HotelInfo;
//import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradeGetResult;
import com.example.demo1.entity.MsgPushEntity;
import com.example.demo1.entity.YzTrade;
import com.example.demo1.entity.YzTrade.TradeDetailV2;
import com.example.demo1.entity.YzTrade.TradeOrderV2;
import com.example.demo1.entity.Shoping;
import com.example.demo1.util.Md5Util;
import com.google.gson.Gson;


import net.minidev.json.JSONObject;
import net.sf.json.JSONException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.*;  
import org.springframework.web.bind.annotation.*;  
import org.springframework.ui.Model;

import java.io.BufferedReader;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.util.logging.Level;  
import com.example.demo1.entity.StreamToString;
/**
 * PushDemoController
 * 推送服务消息接收示例
 * 依赖SPRING 3.0或以上版本
 * @param <TradeBuyerMessage>
 * @auther ChengZi
 * @data 16/9/10
 */

@Controller
public class PushDemoController<TradeBuyerMessage> {

	private Logger logger                    = LoggerFactory.getLogger(PushDemoController.class);
    private static final int mode = 0 ; //服务商
    private static final String clientId="9c12b0922903453a09"; //服务商的秘钥证书
    private static final String clientSecret="b8cc6354bee29efa55d0fe12b30e85a5";//服务商的秘钥证书
    private static final String TYPE_TRADE       = "TRADE";
    private static final String STATUS_DELIVER   = "WAIT_BUYER_CONFIRM_GOODS";
    private static final String STATUS_WAIT_PAY  = "WAIT_BUYER_PAY";
    
    private static final String BUYER_INFO_PHONENUM   = "手机号";
    private static final String PRODUCT_NAME_LIVE     = "萌宝直播-测试";
    private static final String PRODUCT_NAME_RECORD   = "萌宝视频 记录版";
    private static final String PRODUCT_NAME_SELECTED = "萌宝视频 精装版"; 
    private static final String BUYER_INFO_CHILDNAME   = "孩子姓名";
    private static final String BUYER_INFO_CLASS   = "班级";
    private static final String BUYER_INFO_MINDING   = "幼儿园";


    @Autowired
    Gson gson;
    @Autowired
    ZhiqiShopRepository zhiqiShopRepository;
    @RequestMapping(value = "/testPost", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testPost(@RequestBody String entity) {

    	System.out.println("******testPost***********");
    	System.out.println("******testPost***********");
    	System.out.println("******testPost***********");
    	System.out.println("******testPost***********");
    	return "hello world!";
    }
    
 
    @RequestMapping(value = "/test", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String test(@RequestBody MsgPushEntity entity) {
    	System.out.println("******test_start***********");
    	logger.info("test, entity:"+entity);
        JSONObject result = new JSONObject();
        try {
        	result.put("code", 0);
        	result.put("msg", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        String res = gson.toJson(result);
      
        /**
         *  判断是否为心跳检查消息
         *  1.是则直接返回
         */
     
        if (entity.isTest()) {
            return res;
        }

        /**
         * 解析消息推送的模式  这步判断可以省略
         * 0-商家自由消息推送 1-服务商消息推送
         * 以服务商 举例
         * 判断是否为服务商类型的消息
         * 否则直接返回
         */
        if (entity.getMode() != mode ){
            return res;
        }

        /**
         * 判断消息是否合法
         * 解析sign
         * MD5 工具类开发者可以自行引入
         */
        String sign = Md5Util.md5Encode(clientId + entity.getMsg() + clientSecret);
        logger.info("test, sign:"+sign + ", origin:"+entity.getSign());
        if (!sign.equals(entity.getSign())){
            return res;
        }
        
        /**
         * 对于msg 先进行URI解码
         */
        String msg="";
        try {
        	msg = URLDecoder.decode(entity.getMsg(), "utf-8");
        	String time = "time\":\"\"";
        	String timeReplacement = "time\":\"2017-05-13 10:00:30\"";
        	msg = msg.replace(time, timeReplacement);
            logger.info("test, msg:"+msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         *  ..........
         *  接下来是一些业务处理
         *  判断当前消息的类型 比如交易
         *
         */
        logger.info("test, type:"+entity.getType());
        if ("TRADE".equals(entity.getType())) {


            //TODO: 参考文档对应的交易对象 进行JSON解码  业务处理等
        	System.out.println("******TRADEs_start***********");
        	YzTrade yt = gson.fromJson(msg, YzTrade.class);
        	TradeDetailV2 td = yt.getTrade();
        	logger.info("test, yt:"+ToStringBuilder.reflectionToString(yt) + ", td:"+ToStringBuilder.reflectionToString(td));
        	String status = td.getStatus();
        	logger.info("test, status:"+status);
        	try {
        		logger.info("test, buyer name:"+td.getFansInfo());//.getFansNickname()
			} catch (Exception e) {
				logger.info("test, e:"+e);
				e.printStackTrace();
			}
            String productName = td.getTitle();
            Date createdTime = td.getCreated();
            Date updateTime = td.getUpdateTime();
            String productTid = td.getTid();
            //Float productPrice = td.getPrice();
            if (STATUS_WAIT_PAY.equals(status)) {
            	TradeOrderV2[] tos = td.getOrders();
            	logger.info("test, to cnt:"+tos.length);
            	logger.info("***********message_start*************");
            	String phoneNum = "";
        		String childName = "";
        		String childClass = "";
        		String phoneNum2 = "";
        		String phoneNum3 = "";    		
        		String productPicPath= "";
        		String skuUniqueCode = "";//订单唯一标号
        		Float  productSumPrice = null;
        		Date endTime = null;
        		int productMonth=0;       		
        		int num = 0;
        		int userCnt=0;
        		
            	for (TradeOrderV2 to:tos) {   
            		/**
            		 * 订单信息处理
            		 */
            		com.example.demo1.entity.YzTrade.TradeBuyerMessage[] message = to.getBuyerMessages();
        				phoneNum = message[0].getContent();//联系电话
        				childName = message[1].getContent();//孩子姓名
        				childClass = message[2].getContent();//班级
        				phoneNum2 = message[3].getContent();//手机号2
        				phoneNum3 = message[4].getContent();//手机号3
        				/**
        				 * 规格人数处理
        				 */
        				String productParam = to.getSkuPropertiesName();//"套餐：1个月;规格：单人"
        				logger.info("productParam:"+productParam);
        				num = Integer.parseInt(String.valueOf(to.getNum()));//购买数量
                		logger.info("购买数量num:"+num);
                		productName = to.getTitle();
                		productSumPrice = to.getPrice();
                		productPicPath = to.getPicPath();
                		skuUniqueCode = to.getSkuUniqueCode();
                		userCnt = getUserCnt(productParam);
                		logger.info("规格userCnt:"+userCnt+"人");
                		
                		
                		/**
                		 * 套餐时间处理
                		 */
                		if (productName.equals(PRODUCT_NAME_LIVE)) {
            				productMonth = getDurationMonth(productParam, num);
            				Calendar cal=Calendar.getInstance();
            				cal.setTime(createdTime);
            		     	cal.add(Calendar.MONTH, productMonth); // 当前时间加月份   
            		     	endTime = cal.getTime();            	
            		     	logger.info("订单信息:"+"电话"+phoneNum+",姓名:"+childName+",班级:"+childClass+",手机号2："+phoneNum2+",手机号3："+phoneNum3+"<br>"+",产品名称："+
            		     			productName+",产品图片路径："+productPicPath+",规格人数："+userCnt+"人,套餐时间："+productMonth+",购买数量："+num+",付款时间："+createdTime
            		            	+",到期时间："+endTime+"订单号："+productTid+",订单唯一标号："+skuUniqueCode+",产品总价："+productSumPrice);
            		            	logger.info("***********message_end*************");
            		     	
            			} else if (productName.equals(PRODUCT_NAME_SELECTED)) {
            				getDurationMonth(productParam, num);
    					} else if (productName.equals(PRODUCT_NAME_RECORD)) {
            				getDurationMonth(productParam, num);
    					}
            	}
                		logger.info("********************************");
                		try{  
                            URL url = new URL("http://218.61.208.68:8008/getOrder?phoneNum="+phoneNum+"&productName="+productName);  
                            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();  
                            //GET Request Define:   
                            urlConnection.setRequestMethod("GET");  
                            urlConnection.connect();  
                              
                            //Connection Response From Test Servlet  
                            logger.info("Connection Response From Test Servlet");  
                            InputStream inputStream = urlConnection.getInputStream();  
                              
                            //Convert Stream to String  
                            String responseStr = StreamToString.ConvertToString(inputStream);  
                            logger.info("responseStr:"+responseStr);  
                        }catch(IOException e){  
                        	logger.info("faluse");  
                        }  
                		
                		productNameLive(skuUniqueCode,productTid,phoneNum,phoneNum2,phoneNum3,productName,productPicPath,childName,childClass,createdTime,endTime,productMonth,userCnt,num,productSumPrice);
                		logger.info("********************************");
            }
            
        }


        /**
         * 返回结果
         */
        return res;
    }
   
    
    private int getDurationMonth(String pp, int num) {
    	logger.info("pp:"+pp+"num:"+num);
    	if (StringUtils.isEmpty(pp) || !pp.contains("套餐:"))
    		return 0;
    	int unit = 0;
    	if (pp.contains("1个月")) {
    		unit = 1;
    	} else if (pp.contains("半年")) {
    		unit = 6;
		} else if (pp.contains("一年")) {
			unit = 12;
		}  else {
		}
    	logger.info("unit:"+unit);
    	logger.info("总时间："+num*unit);
    	return num * unit;
    }

    private int getUserCnt(String pp) {
    	if (StringUtils.isEmpty(pp))
    		return 0;
    	//"套餐:1个月;规格:单人"
    	int start = pp.indexOf("规格:")+"规格:".length();
    	String userCnt = pp.substring(start);
    	if (userCnt.equals("单人")) {
    		return 1;
    	} else if (userCnt.equals("双人")) {
    		return 2;
		} else if (userCnt.equals("三人")) {
			return 3;
		} else {
			return 0;
		}
    }
    /**
     * 数据录入
     */
    public void productNameLive(
	    String skuUniqueCode,
	    String productTid,
	    String phoneNum,
		String phoneNum2,
		String phoneNum3,
		String productName,     	
		String productPicPath,
		String childName,
		String childClass, 
	    Date createdTime,
	    Date endTime,
	    int productMonth,  
	    int userCnt,
		int num,
		Float  productSumPrice
	
	
	){
    	logger.info("*******插入数据***********");
    	Shoping zhiqishop1 = new Shoping();
    	zhiqishop1.setSkuUniqueCode(skuUniqueCode);
    	zhiqishop1.setProductTid(productTid);
    	zhiqishop1.setPhoneNum(phoneNum);
    	zhiqishop1.setPhoneNum2(phoneNum2);
    	zhiqishop1.setPhoneNum3(phoneNum3);
    	zhiqishop1.setProductName(productName);
    	zhiqishop1.setProductPicPath(productPicPath);
    	zhiqishop1.setChildName(childName);
    	zhiqishop1.setChildClass(childClass);
    	zhiqishop1.setCreatedTime(createdTime);
    	zhiqishop1.setEndTime(endTime);
    	zhiqishop1.setProductMonth(productMonth);
    	zhiqishop1.setUserCnt(userCnt);
    	zhiqishop1.setNum(num);
    	zhiqishop1.setProductSumPrice(productSumPrice);
    	zhiqiShopRepository.save(zhiqishop1);
    	
    	logger.info("*******录入数据成功***********");
    	
		
	}
    /** 
     * 根据电话和产品查找个人订单
     * http://127.0.0.1:8008/getOrder?phoneNum=123456&productName=A 
     * @param phoneNum
     * @param productName
     */
    @RequestMapping(method = RequestMethod.GET,value = "getOrder") 
    @ResponseBody
    public Shoping getByPhoneNumAndProductName(String phoneNum,String productName) { 
      System.out.println("phoneNum:" + phoneNum+"productName:"+productName); 
      Shoping user = zhiqiShopRepository.findByPhoneNumAndProductName(phoneNum,productName); 
      return user;
      /* if (null == user) { 
    	  return "暂无数据"; 
      } else { 
    	  String message = user.getProductName();
    	  logger.info("message:"+message);
    	  return message; 
    	 
      } 
    */
    } 
    /**
     * 根据电话查询已购买产品
     * @param phoneNum
     */
    @RequestMapping(method = RequestMethod.GET,value = "getProduct") 
    @ResponseBody
    public List<Shoping> getByPhoneNum(String phoneNum) { 
      logger.info("phoneNum:" + phoneNum); 
      return (List<Shoping>)zhiqiShopRepository.findAllByPhoneNum(phoneNum); 
    }
    /**
     * 查询全部
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "getAll") 
    @ResponseBody
    public List<Shoping> getAll() {
    	return (List<Shoping>)zhiqiShopRepository.findAll();
    }
    /**
     * 页面处理
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value ="product")
    public String index(HttpServletRequest request) {
		//逻辑处理
		request.setAttribute("key", "HelloWord");
		return "index";
	}  
    @RequestMapping(method = RequestMethod.GET,value ="renew")
    public String home(HttpServletRequest request) {
		return "home";
	}  
}

 
  
/* {"app_id":"9c12b0922903453a09",
 * "id":"E20170414124545067626557",
 * "kdt_id":18566071,
 * "kdt_name":"智启萌宝日记",
 * "mode":0,
 * "msg":"%7B%22trade%22:%7B%22num%22:1,%22goods_kind%22:1,%22num_iid%22:%22328949561%22,%22price%22:%2230.00%22,%22pic_path%22:%22https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg%22,%22pic_thumb_path%22:%22https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg%3FimageView2/2/w/200/h/0/q/75/format/jpg%22,%22title%22:%22%E8%90%8C%E5%AE%9D%E7%9B%B4%E6%92%AD%22,%22type%22:%22FIXED%22,%22discount_fee%22:%220.00%22,%22order_type%22:%220%22,%22status%22:%22WAIT_BUYER_PAY%22,%22status_str%22:%22%E5%BE%85%E4%BB%98%E6%AC%BE%22,%22refund_state%22:%22NO_REFUND%22,%22shipping_type%22:%22express%22,%22post_fee%22:%220.00%22,%22total_fee%22:%2230.00%22,%22refunded_fee%22:%220.00%22,%22payment%22:%2230.00%22,%22created%22:%222017-04-14%2012:45:45%22,%22update_time%22:%222017-04-14%2012:45:45%22,%22pay_time%22:%22%22,%22pay_type%22:%22%22,%22consign_time%22:%22%22,%22sign_time%22:%22%22,%22buyer_area%22:%22%22,%22seller_flag%22:0,%22buyer_message%22:%22%22,%22orders%22:[%7B%22alias%22:%222xf9nkvju8frr%22,%22oid%22:17906402,%22outer_sku_id%22:%22%22,%22outer_item_id%22:%22%22,%22title%22:%22%E8%90%8C%E5%AE%9D%E7%9B%B4%E6%92%AD%22,%22seller_nick%22:%22%E6%99%BA%E5%90%AF%E8%90%8C%E5%AE%9D%E6%97%A5%E8%AE%B0%22,%22fenxiao_price%22:%220.00%22,%22fenxiao_payment%22:%220.00%22,%22price%22:%2230.00%22,%22total_fee%22:%2230.00%22,%22payment%22:%2230.00%22,%22discount_fee%22:%220.00%22,%22sku_id%22:%2236136582%22,%22sku_unique_code%22:%2232894956136136582%22,%22sku_properties_name%22:%22%E5%A5%97%E9%A4%90:1%E4%B8%AA%E6%9C%88;%E8%A7%84%E6%A0%BC:%E5%8D%95%E4%BA%BA%22,%22pic_path%22:%22https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg%22,%22pic_thumb_path%22:%22https://img.yzcdn.cn/upload_files/2017/03/08/FrcCAtGJUGl24P-OeAaPHTkKuduI.jpg%3FimageView2/2/w/200/h/0/q/75/format/jpg%22,%22item_type%22:0,%22buyer_messages%22:[%7B%22title%22:%22%E6%89%8B%E6%9C%BA%E5%8F%B7%22,%22content%22:%2215110007268%22%7D,%7B%22title%22:%22%E5%AD%A9%E5%AD%90%E5%A7%93%E5%90%8D%22,%22content%22:%22%E5%A7%9C%E5%B0%8F%E5%AE%9D%22%7D,%7B%22title%22:%22%E6%89%80%E5%9C%A8%E7%8F%AD%E7%BA%A7%22,%22content%22:%22%E6%89%98%E4%B8%80%E7%8F%AD%22%7D,%7B%22title%22:%22%E6%89%8B%E6%9C%BA%E5%8F%B72%22,%22content%22:%22%22%7D,%7B%22title%22:%22%E6%89%8B%E6%9C%BA%E5%8F%B73%22,%22content%22:%22%22%7D],%22order_promotion_details%22:[],%22state_str%22:%22%E5%BE%85%E4%BB%98%E6%AC%BE%22,%22allow_send%22:0,%22is_send%22:0,%22item_refund_state%22:%22NO_REFUND%22,%22is_virtual%22:1,%22is_present%22:0,%22refunded_fee%22:%220.00%22,%22unit%22:%22%E4%BB%B6%22,%22num_iid%22:%22328949561%22,%22num%22:%221%22%7D],%22fetch_detail%22:null,%22coupon_details%22:[],%22promotion_details%22:[],%22adjust_fee%22:%7B%22change%22:%220.00%22,%22pay_change%22:%220.00%22,%22post_change%22:%220.00%22%7D,%22sub_trades%22:[],%22weixin_user_id%22:%222465691097%22,%22button_list%22:[%7B%22tool_icon%22:%22https://img.yzcdn.cn/upload_files/2015/08/28/Ftodf5cdSlo-vaH1Jty4GylCJLr5.png%22,%22tool_title%22:%22%E6%94%B9%E4%BB%B7%22,%22tool_value%22:%22%22,%22tool_type%22:%22goto_native:trade_change_price%22,%22tool_parameter%22:%22%7B%7D%22,%22new_sign%22:%220%22,%22create_time%22:%22%22%7D,%7B%22tool_icon%22:%22https://img.yzcdn.cn/upload_files/2015/08/28/Flv3HyyRB2DGyXsPwRqnZvA4pHla.png%22,%22tool_title%22:%22%E5%85%B3%E9%97%AD%22,%22tool_value%22:%22%22,%22tool_type%22:%22goto_native:trade_close%22,%22tool_parameter%22:%22%22,%22new_sign%22:%22%22,%22create_time%22:%22%22%7D,%7B%22tool_icon%22:%22https://img.yzcdn.cn/upload_files/2015/08/28/FpO1UIXyOEZO026tWIgUOm9uZnT2.png%22,%22tool_title%22:%22%E5%A4%87%E6%B3%A8%22,%22tool_value%22:%22%22,%22tool_type%22:%22goto_native:trade_memo%22,%22tool_parameter%22:%22%7B%7D%22,%22new_sign%22:%220%22,%22create_time%22:%22%22%7D],%22feedback_num%22:0,%22trade_memo%22:%22%22,%22fans_info%22:%7B%22fans_nickname%22:%22%E6%B5%B7%E5%AD%90%E8%93%9D%22,%22fans_id%22:%222465691097%22,%22buyer_id%22:%22136847012%22,%22fans_type%22:%221%22%7D,%22buy_way_str%22:%22%22,%22pf_buy_way_str%22:%22%22,%22send_num%22:0,%22user_id%22:%22136847012%22,%22kind%22:1,%22relation_type%22:%22%22,%22relations%22:[],%22out_trade_no%22:[],%22group_no%22:%22%22,%22outer_user_id%22:0,%22shop_id%22:%220%22,%22shop_type%22:%221%22,%22points_price%22:0,%22delivery_start_time%22:0,%22delivery_end_time%22:0,%22tuan_no%22:%22%22,%22is_tuan_head%22:0,%22delivery_time_display%22:%22%22,%22hotel_info%22:%22%22,%22order_mark%22:%22%22,%22qr_id%22:0,%22buyer_nick%22:%22%E6%B5%B7%E5%AD%90%E8%93%9D%22,%22tid%22:%22E20170414124545067626557%22,%22buyer_type%22:%221%22,%22buyer_id%22:%222465691097%22,%22receiver_city%22:%22%22,%22receiver_district%22:%22%22,%22receiver_name%22:%22%22,%22receiver_state%22:%22%22,%22receiver_address%22:%22%22,%22receiver_zip%22:%22%22,%22receiver_mobile%22:%22%22,%22feedback%22:0,%22outer_tid%22:%22%22,%22service_phone%22:%22%22%7D%7D",
 * "sendCount":0,
 * "sign":"efa4ec9821d8785fed393e9fcc081563",
 * "status":"WAIT_BUYER_PAY",
 * "test":false,
 * "type":"TRADE",
 * "version":1492145146} */

/*{
  "response": {
    "trade": {
      "num": 0,
      "goods_kind": 0,
      "item_id": "",
      "price": "0.00",
      "pic_path": "",
      "pic_thumb_path": "",
      "title": "",
      "type": "FIXED",
      "discount_fee": "0.00",
      "order_type": "0",
      "status": "WAIT_SELLER_SEND_GOODS",
      "status_str": "待发货",
      "refund_state": "NO_REFUND",
      "shipping_type": "express",
      "post_fee": "0.00",
      "total_fee": "0.01",
      "refunded_fee": "0.00",
      "payment": "0.01",
      "created": "2017-05-27 10:16:29",
      "update_time": "2017-05-27 10:16:29",
      "pay_time": "2017-05-27 10:16:31",
      "pay_type": "ECARD",
      "consign_time": "",
      "sign_time": "",
      "buyer_area": "安徽省滁州市",
      "seller_flag": 0,
      "buyer_message": "",
      "orders": [
        {
          "alias": "277n0fdjk8gpx",
          "oid": 17987350,
          "outer_sku_id": "",
          "outer_item_id": "",
          "title": "好一朵美丽的茉莉花",
          "seller_nick": "Qi码是家馆子",
          "fenxiao_price": "0.00",
          "fenxiao_payment": "0.00",
          "price": "0.01",
          "total_fee": "0.01",
          "payment": "0.01",
          "discount_fee": "0.00",
          "sku_id": "36152472",
          "sku_unique_code": "33886248436152472",
          "sku_properties_name": "颜色:蓝色",
          "pic_path": "https://img.yzcdn.cn/upload_files/2017/04/20/FmPQ1xZ7-6ncBjfi9jnnArLsdXRl.jpg",
          "pic_thumb_path": "https://img.yzcdn.cn/upload_files/2017/04/20/FmPQ1xZ7-6ncBjfi9jnnArLsdXRl.jpg?imageView2/2/w/200/h/0/q/75/format/jpg",
          "item_type": 0,
          "buyer_messages": [],
          "order_promotion_details": [],
          "state_str": "待发货",
          "allow_send": 1,
          "is_send": 0,
          "item_refund_state": "NO_REFUND",
          "is_virtual": 0,
          "is_present": 0,
          "refunded_fee": "0.00",
          "refund_id": "",
          "unit": "件",
          "item_id": "338862484",
          "num": "1"
        }
      ],
      "fetch_detail": null,
      "coupon_details": [],
      "promotion_details": [],
      "adjust_fee": {
        "change": "0.00",
        "pay_change": "0.00",
        "post_change": "0.00"
      },
      "sub_trades": [],
      "button_list": [
        {
          "tool_icon": "https://img.yzcdn.cn/upload_files/2015/08/28/FjeHlaPYCjO0KAgbJliv1FwCFSoj.png",
          "tool_title": "周期购物流",
          "tool_value": "",
          "tool_type": "goto_webview:web",
          "tool_parameter": "{\"detail_url\":\"https://h5.youzan.com/v2/kdtapp/PeriodOrder/PeriodBuyExpress?order_no=E20170527101629037200001&kdt_id=63077\",\"required_keys\":[\"access_token\"]}",
          "new_sign": "0",
          "create_time": ""
        },
        {
          "tool_icon": "https://img.yzcdn.cn/upload_files/2015/08/28/FpFY_MeJXzLCA3lwIV6br6qUbClv.png",
          "tool_title": "发货",
          "tool_value": "",
          "tool_type": "goto_native:trade_send_goods",
          "tool_parameter": "{}",
          "new_sign": "0",
          "create_time": ""
        },
        {
          "tool_icon": "https://img.yzcdn.cn/upload_files/2015/08/28/FpO1UIXyOEZO026tWIgUOm9uZnT2.png",
          "tool_title": "备注",
          "tool_value": "",
          "tool_type": "goto_native:trade_memo",
          "tool_parameter": "{}",
          "new_sign": "0",
          "create_time": ""
        }
      ],
      "feedback_num": 0,
      "trade_memo": "",
      "fans_info": {
        "fans_nickname": "13739202295",
        "fans_id": -301792628,
        "buyer_id": "301792628",
        "fans_type": "0"
      },
      "buy_way_str": "",
      "pf_buy_way_str": "运费到付",
      "send_num": 0,
      "kind": 1,
      "relation_type": "",
      "relations": [],
      "out_trade_no": [],
      "group_no": "",
      "outer_user_id": 0,
      "offline_id": "63077",
      "shop_id": "0",
      "shop_type": "1",
      "points_price": 0,
      "delivery_start_time": 0,
      "delivery_end_time": 0,
      "tuan_no": "",
      "is_tuan_head": 0,
      "delivery_time_display": "",
      "hotel_info": "",
      "qr_id": 0,
      "tid": "E20170527101629037200001",
      "receiver_city": "滁州市",
      "receiver_district": "全椒县",
      "receiver_name": "安徽张驰",
      "receiver_state": "安徽省",
      "receiver_address": "全椒县汽车站22号",
      "receiver_zip": "239500",
      "receiver_mobile": "13722957845",
      "feedback": 0,
      "outer_tid": "170527101631263573",
      "transaction_tid": "170527101631159231",
      "period_order_detail": {
        "issue": 1,
        "title": "好一朵美丽的茉莉花",
        "num": 1,
        "advanced": true,
        "kdt_id": 63077,
        "order_no": "E20170527101629037200001",
        "item_id": 17987350,
        "express_name": "",
        "express_id": 0,
        "express_no": "",
        "total_pay": 1,
        "plan_express_time": 1496224800,
        "is_delay": 0,
        "delivery_state": 1,
        "actual_express_time": 0,
        "allow_update": false
      },
      "service_phone": "13167897654"
    }
  }
}*/
/*
 * trade_status = "WAIT_BUYER_PAY"      等待买家付款

trade_status = "WAIT_SELLER_SEND_GOODS"       买家付款，等待买家发货

trade_status = "WAIT_BUYER_CONFIRM_GOODS"     卖家付款，等待买家确认

rade_status = "TRADE_FINISHED" 交易完成
*/
