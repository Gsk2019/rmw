package org.cs.task;

import org.cs.rmw.service.IPriceTrendService;
import org.cs.rmw.service.impl.PriceTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestTask {

	@Autowired
	private IPriceTrendService priceTrendService;

	@Transactional(readOnly = false)
	@Scheduled(cron = "0 0 1 * * ?")
	public void setCode(){
		System.out.println("set code execute ..............................");
	}

	//每天07:15点执行
	@Transactional(readOnly = false)
	@Scheduled(cron = "0 50 23 * * ?")
//	@Scheduled(cron = "0 33 18 * * ?")
	public void doParserTask(){
		System.out.println("行情数据生成开始 ..............................");
		priceTrendService.doPriceTrend_admin();
	}
	
}
