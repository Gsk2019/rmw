package org.cs.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliMsgUtils {

    /*模版类型: 验证码
    模版名称: 用户注册验证码
    模版CODE: SMS_140670066
    模版内容: 验证码${code}，您正在注册成为新用户，感谢您的支持！

    模版类型: 验证码
    模版名称: 修改密码验证码
    模版CODE: SMS_140670065
    模版内容: 验证码${code}，您正在尝试修改登录密码，请妥善保管账户信息。

    模版类型: 短信通知
    模版名称: 认证审核通过通知
    模版CODE: SMS_146290198
    模版内容: 尊敬的乳买网用户，您的身份认证已通过审核，赶快去看看吧
    申请说明: 用户进行身份认证时使用
    */

    static final String product = "Dysmsapi";//产品名称:云通信短信API产品,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";//产品域名,开发者无需替换
    static final String accessKeyId = "LTAItgt6bKwd7m0Q";
    static final String accessKeySecret = "uATbIueZp4FVwYr2cKrvs0klquTEmi";

    public static boolean sendSms(String code,String phone,String model) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("乳买网");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(model);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":"+code+"}");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode().equals("OK"))
            return true;
        return false;
    }

    public static boolean sendSms(String phone,String model) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("乳买网");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(model);
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode().equals("OK"))
            return true;
        return false;
    }
}
