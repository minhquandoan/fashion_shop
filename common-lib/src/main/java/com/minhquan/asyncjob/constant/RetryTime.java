package com.minhquan.asyncjob.constant;

import java.time.Duration;

public class RetryTime {
    public static final Duration[] DEFAULT_RETRY_TIME = {Duration.ofSeconds(1), Duration.ofSeconds(5), Duration.ofSeconds(10)};
    public static final Duration DEFAULT_MAX_TIMEOUT = Duration.ofSeconds(10);
    public static final int DEFAULT_MAX_RETRY_TIMEOUT = 3;
}
