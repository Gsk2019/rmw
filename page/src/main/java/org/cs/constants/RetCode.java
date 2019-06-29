package org.cs.constants;

//import com.fasterxml.jackson.annotation.JsonValue;

import jd.util.PropUtil;

/**
 * 定义接口返回状态码
 * 1      - 499 网络错误
 * 500  - 1000 数据错误
 * 1001 - 2000 服务器执行错误
 * 1001 - 2000 
 * 	1001 - 1100  登录注册相关
 * 1001 - 2000
 * 
 * 除返回成功外，其他常量声明实例：
 * EC_{值} 
 * @ClassName: RetCode 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2017年5月5日 上午10:43:37
 */
public enum RetCode {
	
	SUCCESS(1, "执行成功"),
	EC_1000(1000, "请先关联账号"),
	EC_0600(600, "已有关联账号"),
	EC_0500(500, "操作失败,请稍后重试"),
	EC_0501(501, "用户已存在"),
	EC_0502(502, "未知的验证码获取类型"),
    EC_0503(503, "错误的验证码"),
    EC_0504(504, "验证码已超时"),

	EC_0505(505, "密码错误"),

	EC_0401(401, "未获取到登录信息"),
	EC_0402(402, "账号异常，请联系客服"),

	EC_0400(400, "信息不全"),

	EC_1503(1503,"验证码错误");

	private int key;
	private String value;
	
	RetCode(int key, String value){
		this.key = key;
		this.value = value;
	}
	
	

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return key + "";
	}

	
	
}
