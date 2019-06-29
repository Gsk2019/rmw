package jd.qq.sms;

import java.util.ArrayList;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

/**
 * 腾讯短信
 */
public class SmsUtil {
	//答案茶短信
	final static int			appid		= 1400085000;				
	final static String			appkey	= "61f762383fcbc9edfac39946bfb3b257";	
	final static int			tmplId			= 111162;

	
	public static int sendCode(String phone, String code, int type) {
        //初始化单发
        SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
        SmsSingleSenderResult singleSenderResult = null;

        ArrayList<String> params = new ArrayList<String>();
        params.add(code);
        params.add("5");
        
        //指定模板单发
        try {
        	singleSenderResult = singleSender.sendWithParam("86", phone, tmplId, params, "", "", "");
        } catch (Exception e) {
			return 500;
		}

		return singleSenderResult.result;
	}
	
	public static void main(String[] args) {
		int sendCode = SmsUtil.sendCode("13716938054","0292",0);
		System.out.println(sendCode);
	}
}
