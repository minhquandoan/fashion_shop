package com.minhquan.asyncjob;

import java.time.Duration;

public interface IJob {
    void execute();
    void retry() throws InterruptedException;
    JobState getState();
    void setRetryDuration(Duration[] retryDuration);
}
