package com.example.hello.IpBan;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PricingPlanService {


    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String clientIp) {
        //없으면 새로 넣어준다.
        return cache.computeIfAbsent(clientIp, this::newBucket);
    }

    //해당 ip가 과다요청을 한 경우 ban 한다.
    private Bucket newBucket(String apiKey) {
        return Bucket4j.builder()
                .addLimit(Bandwidth.simple(300, Duration.ofMinutes(1)))
                .addLimit(Bandwidth.simple(20, Duration.ofSeconds(1)))
                .build();
    }
}