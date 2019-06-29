package org.cs.notify.ctl;

import org.cs.core.ctl.BaseCtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/*redis信息的发布
* 和RedisMessageListener.java订阅者组成发布/订阅*/
@Controller
@RequestMapping("/notify")
public class RedisCtl extends BaseCtl {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 广播发送消息
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/sendMsg",method = RequestMethod.POST)
    @ResponseBody
    public String toBroadCastMsg(String data, ModelMap modelMap){
    	Map<String,String> map = getMapFromJson(data);
    	String msg = map.get("msg");

    	/*对应redis中的注册的channel*/
        redisTemplate.convertAndSend("topic", msg);

        return AJAX_SUCCESS;
    }
	
}
