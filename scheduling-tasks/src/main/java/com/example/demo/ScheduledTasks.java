package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("每隔五秒执行一次 {}", dateFormat.format(new Date()));
    }
    
    @Scheduled(cron = "0 25 10 ? * *")
    public void cronTest() {
    	log.info("每天10点25执行 {}", dateFormat.format(new Date()));
    }
    
}
