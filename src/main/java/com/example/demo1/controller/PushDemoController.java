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
import org.springframework.scheduling.annotation.EnableAsync;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * PushDemoController 推送服务消息接收示例 依赖SPRING 3.0或以上版本
 * 
 * @param <TradeBuyerMessage>
 * @auther ChengZi
 * @data 16/9/10
 */

@Controller
@Component
public class PushDemoController<TradeBuyerMessage> {

	private Logger logger = LoggerFactory.getLogger(PushDemoController.class);
	private static final int mode = 0; // 服务商
	private static final String clientId = "9c12b0922903453a09"; // 服务商的秘钥证书
	private static final String clientSecret = "b8cc6354bee29efa55d0fe12b30e85a5";// 服务商的秘钥证书
	private static final String TYPE_TRADE = "TRADE";
	private static final String STATUS_DELIVER = "WAIT_BUYER_CONFIRM_GOODS";
	private static final String STATUS_WAIT_PAY = "WAIT_BUYER_PAY";

	private static final String BUYER_INFO_PHONENUM = "手机号";
	private static final String PRODUCT_NAME_LIVE = "萌宝直播";
	private static final String PRODUCT_NAME_RECORD = "萌宝视频 记录版";
	private static final String PRODUCT_NAME_SELECTED = "萌宝视频 精彩剪辑版";
	private static final String PRODUCT_NAME_BOOK = "萌宝视频 精彩剪辑版";


	@Autowired
	Gson gson;
	@Autowired
	ZhiqiShopRepository zhiqiShopRepository;

	/**
	 * 订单推送处理
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String test(@RequestBody MsgPushEntity entity) {
		//logger.info("test, entity:" + entity);
		JSONObject result = new JSONObject();
		try {
			result.put("code", 0);
			result.put("msg", "success");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		String res = gson.toJson(result);

		/**
		 * 判断是否为心跳检查消息 1.是则直接返回
		 */

		if (entity.isTest()) {
			return res;
		}

		/**
		 * 解析消息推送的模式 这步判断可以省略 0-商家自由消息推送 1-服务商消息推送 以服务商 举例 判断是否为服务商类型的消息 否则直接返回
		 * 
		 * if (entity.getMode() != mode ){ return res; }
		 */
		/**
		 * 判断消息是否合法 解析sign MD5 工具类开发者可以自行引入
		 */
		String sign = Md5Util.md5Encode(clientId + entity.getMsg() + clientSecret);
		//logger.info("test, sign:" + sign + ", origin:" + entity.getSign());
		if (!sign.equals(entity.getSign())) {
			return res;
		}
		doOrder(entity);
		logger.info("test, return");
		/**
		 * 返回结果
		 */
		return res;
	}// main_thread end

	@Async
	// MsgPushEntity msg;
	public void doOrder(MsgPushEntity entity) {
		/**
		 * 对于msg 先进行URI解码
		 */
		String msg = "";
		try {
			msg = URLDecoder.decode(entity.getMsg(), "utf-8");
			String time = "time\":\"\"";
			String timeReplacement = "time\":\"2017-05-13 10:00:30\"";
			msg = msg.replace(time, timeReplacement);
		//	logger.info("test, msg:" + msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/**
		 * 接下来是一些业务处理 判断当前消息的类型 比如交易
		 *
		 */

		logger.info("test, type:" + entity.getType());
		if ("TRADE".equals(entity.getType())) {
			// doOrder(msg); // 开启新线程处理订单

			// TODO: 参考文档对应的交易对象 进行JSON解码 业务处理等
			YzTrade yt = gson.fromJson(msg, YzTrade.class);
			TradeDetailV2 td = yt.getTrade();
			logger.info("test, yt:" + ToStringBuilder.reflectionToString(yt) + ", td:"
					+ ToStringBuilder.reflectionToString(td));
			String status = td.getStatus();
			logger.info("test, status:" + status);
			try {
				logger.info("test, buyer name:" + td.getFansInfo());// .getFansNickname()
			} catch (Exception e) {
				logger.info("test, e:" + e);
				e.printStackTrace();
			}
			String productName = td.getTitle();
			Date createdTime = td.getCreated();
			Date updateTime = td.getUpdateTime();
			String productTid = td.getTid();
			// Float productPrice = td.getPrice();
			if (STATUS_WAIT_PAY.equals(status)) {
				TradeOrderV2[] tos = td.getOrders();
				logger.info("test, to cnt:" + tos.length);
				String phoneNum = "";
				String childName = "";
				String childClass = "";
				String phoneNum2 = "";
				String phoneNum3 = "";
				String childMinding = "";
				String productPicPath = "";
				String skuUniqueCode = "";// 订单唯一标号
				Float productSumPrice = null;
				Date endTime = null;
				int productMonth = 0;
				int num = 0;
				int userCnt = 0;
       
				for (TradeOrderV2 to : tos) {
					
					
					if (productName.equals(PRODUCT_NAME_LIVE)) {
						com.example.demo1.entity.YzTrade.TradeBuyerMessage[] message = to.getBuyerMessages();
						phoneNum = message[0].getContent();// 联系电话
						childName = message[1].getContent();// 孩子姓名
						childClass = message[2].getContent();// 班级
						phoneNum2 = message[3].getContent();// 手机号2
						phoneNum3 = message[4].getContent();// 手机号3
						
						/**
						 * 规格人数处理
						 */
						String productParam = to.getSkuPropertiesName();// "套餐：1个月;规格：单人"
						logger.info("productParam:" + productParam);
						num = Integer.parseInt(String.valueOf(to.getNum()));// 购买数量
						logger.info("购买数量num:" + num);
						productName = to.getTitle();
						productSumPrice = to.getPrice();
						productPicPath = to.getPicPath();
						skuUniqueCode = to.getSkuUniqueCode();
						userCnt = getUserCnt(productParam);
						logger.info("规格userCnt:" + userCnt + "人");
						/**
						 * 套餐时间处理
						 */
						productMonth = getDurationMonth(productParam, num);
						Calendar cal = Calendar.getInstance();
						cal.setTime(createdTime);
						cal.add(Calendar.MONTH, productMonth); // 当前时间加月份
						endTime = cal.getTime();
					} else if (productName.equals( PRODUCT_NAME_RECORD) || productName.equals( PRODUCT_NAME_SELECTED)) {

						com.example.demo1.entity.YzTrade.TradeBuyerMessage[] message = to.getBuyerMessages();
						phoneNum = message[0].getContent();// 联系电话
						childMinding = message[1].getContent();//幼儿园
						childName = message[2].getContent();// 孩子姓名
						childClass = message[3].getContent();// 班级
						
						
						/**
						 * 规格时间处理
						 */
						String productParam = to.getSkuPropertiesName();// 规格：5分钟"
						logger.info("productParam:" + productParam);
						num = Integer.parseInt(String.valueOf(to.getNum()));// 购买数量
						logger.info("购买数量num:" + num);
						productName = to.getTitle();
						productSumPrice = to.getPrice();
						productPicPath = to.getPicPath();
						skuUniqueCode = to.getSkuUniqueCode();
						if(productName.equals( PRODUCT_NAME_RECORD)){
							productMonth = getRecordDurationMonth(productParam, num);
							userCnt = getRecordFilmCnt(productParam);
						}else{
							productMonth = getLiteDurationMonth(productParam, num);
							userCnt = getLiteFilmCnt(productParam);
						}
						logger.info("规格getFilmCnt:" + userCnt);
						Calendar cal = Calendar.getInstance();
						cal.setTime(createdTime);
						cal.add(Calendar.MONTH, productMonth); // 当前时间加月份
						endTime = cal.getTime();
					
					} else if (productName.equals(PRODUCT_NAME_BOOK)) {
						com.example.demo1.entity.YzTrade.TradeBuyerMessage[] message = to.getBuyerMessages();
						phoneNum = message[0].getContent();// 联系电话
						/**
						 * 规格时间处理
						 */
						num = Integer.parseInt(String.valueOf(to.getNum()));// 购买数量
						logger.info("购买数量num:" + num);
						productName = to.getTitle();
						productSumPrice = to.getPrice();
						productPicPath = to.getPicPath();
						skuUniqueCode = to.getSkuUniqueCode();
						productMonth = getBookDurationMonth(num);
						userCnt = 0;
						logger.info("规格getFilmCnt:" + userCnt);
						Calendar cal = Calendar.getInstance();
						cal.setTime(createdTime);
						cal.add(Calendar.MONTH, productMonth); // 当前时间加月份
						endTime = cal.getTime();
					}
					logger.info("订单信息:" + "电话" + phoneNum + ",姓名:" + childName + ",班级:" + childClass + ",手机号2："
							+ phoneNum2 + ",手机号3：" + phoneNum3 + "<br>" + ",产品名称：" + productName + ",产品图片路径："
							+ productPicPath + ",规格人数：" + userCnt + "人,套餐时间：" + productMonth + ",购买数量：" + num
							+ ",付款时间：" + createdTime + ",到期时间：" + endTime + "订单号：" + productTid + ",订单唯一标号："
							+ skuUniqueCode + ",产品总价：" + productSumPrice);
				Shoping message;
				message = getByPhoneNumAndProductName(phoneNum, productName);
				
				if (message == null) {
					productNameLive(skuUniqueCode, productTid, phoneNum, phoneNum2, phoneNum3, childMinding,productName,
							productPicPath, childName, childClass, createdTime, endTime, productMonth, userCnt, num,
							productSumPrice);

				} else{
					String Tid = message.getProductTid();
					logger.info("Tid:"+Tid+"start_Tid:"+productTid);
					if(!(Tid.equals(productTid))){
						Date updateEndTime = message.getEndTime();
						Boolean m = compareDate(updateEndTime,createdTime);
						logger.info("m:"+m);
						Calendar cal1 = Calendar.getInstance();
						if(m == true){						
							cal1.setTime(updateEndTime);
						}else{
							cal1.setTime(createdTime);
							}
						cal1.add(Calendar.MONTH, productMonth); // 当前时间加月份
						updateEndTime = cal1.getTime();
						logger.info("更新到期时间后:" + updateEndTime);
						message.setEndTime(updateEndTime);
						message.setProductTid(productTid);
						zhiqiShopRepository.save(message);
						logger.info("产品续费成功!");						
					 }
					 else {
						logger.info("重复订单!");
					 }
				  }
				} // for end
			}
		}
	}// thread_2 end

	/**
	 * 根据电话和产品查找个人订单
	 * http://127.0.0.1:8008/getOrder?phoneNum=123456&productName=A
	 * 
	 * @param phoneNum
	 * @param productName
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getOrder")
	@ResponseBody
	public Shoping getByPhoneNumAndProductName(String phoneNum, String productName) {
		System.out.println("phoneNum:" + phoneNum + "productName:" + productName);
		Shoping user = zhiqiShopRepository.findByPhoneNumAndProductName(phoneNum, productName);
		return user;
	}

	/**
	 * 根据电话查询已购买产品
	 * 
	 * @param phoneNum
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getProduct")
	@ResponseBody
	public List<Shoping> getByPhoneNum(String phoneNum) {
		logger.info("phoneNum:" + phoneNum);
		return (List<Shoping>) zhiqiShopRepository.findAllByPhoneNum(phoneNum);
	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getAll")
	@ResponseBody
	public List<Shoping> getAll() {
		return (List<Shoping>) zhiqiShopRepository.findAll();
	}

	/**
	 * 已购产品入口地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "product")
	public String index(HttpServletRequest request) {
		// 测试
		// request.setAttribute("key", "HelloWord");
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "renew")
	public String home(HttpServletRequest request) {
		return "home";
	}

	/**
	 * 数据录入
	 */
	public void productNameLive(String skuUniqueCode, String productTid, String phoneNum, String phoneNum2,
			String phoneNum3,String childMinding, String productName, String productPicPath, String childName, String childClass,
			Date createdTime, Date endTime, int productMonth, int userCnt, int num, Float productSumPrice) {
		Shoping message;
		message = getByPhoneNumAndProductName(phoneNum, productName);
		if(message == null){
		Shoping zhiqishop1 = new Shoping();
		zhiqishop1.setSkuUniqueCode(skuUniqueCode);
		zhiqishop1.setProductTid(productTid);
		zhiqishop1.setPhoneNum(phoneNum);
		zhiqishop1.setPhoneNum2(phoneNum2);
		zhiqishop1.setPhoneNum3(phoneNum3);
		zhiqishop1.setChildMinding(childMinding);
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
		}else{
			logger.info("订单已存在!");
		}
	}

	/**
	 * 萌宝直播时间套餐处理
	 * 
	 * @param pp
	 * @param num
	 * @return
	 */
	private int getDurationMonth(String pp, int num) {
		logger.info("pp:" + pp + "num:" + num);
		if (StringUtils.isEmpty(pp) || !pp.contains("套餐:"))
			return 0;
		int unit = 0;
		if (pp.contains("1个月")) {
			unit = 1;
		} else if (pp.contains("半年")) {
			unit = 6;
		} else if (pp.contains("一年")) {
			unit = 12;
		} else {
		}
		logger.info("unit:" + unit);
		logger.info("总时间：" + num * unit);
		return num * unit;
	}

	/**
	 * 萌宝直播规格人数处理
	 * 
	 * @param pp
	 * @return
	 */
	private int getUserCnt(String pp) {
		if (StringUtils.isEmpty(pp))
			return 0;
		// "套餐:1个月;规格:单人"
		int start = pp.indexOf("规格:") + "规格:".length();
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
	 * 视频剪辑师-记录板套餐处理
	 * 
	 * @param pp
	 * @param num
	 * @return
	 */
	private int getRecordDurationMonth(String pp, int num) {
		logger.info("pp:" + pp + "num:" + num);
		if (StringUtils.isEmpty(pp) || !pp.contains("规格:"))
			return 0;
		int unit = 1;//默认一个月
		logger.info("总时间：" + num * unit);
		return num * unit;
	}
	/**
	 * 视频剪辑师-记录板规格处理
	 * 
	 * @param pp
	 * @return
	 */
	private int getRecordFilmCnt(String pp) {
		if (StringUtils.isEmpty(pp))
			return 0;
		// "套餐:1个月;规格:单人"
		int start = pp.indexOf("规格:") + "规格:".length();
		String userCnt = pp.substring(start);
		if (userCnt.equals("5分钟")) {
			return 1;
		} else if (userCnt.equals("10分钟")) {
			return 2;
		} else if (userCnt.equals("15分钟")) {
			return 3;
		} else {
			return 0;
		}
	}
	/**
	 * 视频剪辑师-精简板套餐处理
	 * 
	 * @param pp
	 * @param num
	 * @return
	 */
	private int getLiteDurationMonth(String pp, int num) {
		logger.info("pp:" + pp + "num:" + num);
		if (StringUtils.isEmpty(pp) || !pp.contains("规格:"))
			return 0;
		int unit = 1;//默认一个月
		logger.info("总时间：" + num * unit);
		return num * unit;
	}
	/**
	 * 视频剪辑师-精简板规格处理
	 * 
	 * @param pp
	 * @return
	 */
	private int getLiteFilmCnt(String pp) {
		if (StringUtils.isEmpty(pp))
			return 0;
		// "套餐:1个月;规格:单人"
		int start = pp.indexOf("规格:") + "规格:".length();
		String userCnt = pp.substring(start);
		if (userCnt.equals("1分钟")) {
			return 1;
		} else if (userCnt.equals("3分钟")) {
			return 2;
		} else if (userCnt.equals("5分钟")) {
			return 3;
		} else {
			return 0;
		}
	}
	/**
	 * 视频书-套餐处理
	 * 
	 * @param pp
	 * @param num
	 * @return
	 */
	private int getBookDurationMonth(int num) {
		int unit = 6;//默认半年
		logger.info("总时间：" + num * unit);
		return num * unit;
	}
	
	/**
	 * 判断日期大小
	 * @param d1
	 * @param d2
	 * @return
	 */
	public boolean compareDate(Date d1, Date d2) {  
	    Calendar c1 = Calendar.getInstance();  
	    Calendar c2 = Calendar.getInstance();  
	    c1.setTime(d1);  
	    c2.setTime(d2);  
	  
	    int result = c1.compareTo(c2);  
	    if (result >= 0)  
	        return true;  
	    else  
	        return false;  
	}  

}
