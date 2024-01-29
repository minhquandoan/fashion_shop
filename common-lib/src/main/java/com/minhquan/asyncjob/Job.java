package com.minhquan.asyncjob;

import io.quarkus.logging.Log;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.UUID;

import static com.minhquan.asyncjob.constant.RetryTime.DEFAULT_RETRY_TIME;


@AllArgsConstructor
@Getter
@Builder
public class Job implements IJob {

    private UUID id;

    JobConfig config;

    JobState jobState;

    JobHandler jobHandler;

    int retryIndex;


    @Override
    public void execute() {
        this.jobState = JobState.StateInit;

        try {
            jobHandler.handle();
        } catch(Exception ex) {
            Log.errorv("async-job is failed with Exception: {} ", ex.toString());
            this.jobState = JobState.StateFailed;
            return;
        }

        this.jobState = JobState.StateCompleted;
    }

    @Override
    public void retry() throws InterruptedException {
        //TODO
        this.retryIndex += 1;
        Thread.sleep(DEFAULT_RETRY_TIME[this.retryIndex].getSeconds());

        this.execute();

        if (this.retryIndex == DEFAULT_RETRY_TIME.length - 1) {
            this.jobState = JobState.StateRetryFailed;
        }
    }

    @Override
    public JobState getState() {
        return this.jobState;
    }

    @Override
    public void setRetryDuration(Duration[] retryDuration) {
        this.config.setRetries(retryDuration);
    }

    @Setter
    public static class JobConfig {
        Duration maxTimeout;
        Duration[] retries;
    }
}
