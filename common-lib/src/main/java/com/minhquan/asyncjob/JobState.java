package com.minhquan.asyncjob;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum JobState {
    StateInit("Init"),
    StateRunning("Running"),
    StateFailed("Failed"),
    StateTimeout("Timeout"),
    StateCompleted("Completed"),
    StateRetryFailed("RetryFailed"),
    ;
    private final String name;
}
