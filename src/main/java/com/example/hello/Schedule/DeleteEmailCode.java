package com.example.hello.Schedule;

import com.example.hello.Service.SignUpCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteEmailCode {

//    @Autowired
//    SignUpCodeService signUpCodeService;
//
//    //20분 간격으로 삭제
//   @Scheduled(cron = "* */20 * * * * ")
//    public void scheduleFixedDelayTask()  {
//        //signUpCodeService.deleteExpired();
//        log.info("Fixed delay task - {}", System.currentTimeMillis() / 1000);
//        //Thread.sleep(1000);
//    }
}
