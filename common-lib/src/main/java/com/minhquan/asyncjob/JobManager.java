package com.minhquan.asyncjob;

import io.quarkus.logging.Log;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@AllArgsConstructor
@RequiredArgsConstructor
public class JobManager {
    boolean isConcurrent;
    List<Job> jobs;
    private ExecutorService executorService;

    public JobManager(boolean isConcurrent, List<Job> jobs) {
        this.isConcurrent = isConcurrent;
        this.executorService = Executors.newFixedThreadPool(jobs.size());
    }

    public void run() {
        for (Job job : this.jobs) {
            this.executorService.execute(() -> {
                runJob(job);
            });
        }
    }

    public void runJob(Job job) {
        job.execute();

        if (job.getState() == JobState.StateFailed) {
            Log.errorf("Job %s is failed, retrying ...", job.getId().toString());
            while(true) {
                Log.infof("Job %s: %s retry...", job.getId().toString(), String.valueOf(job.getRetryIndex() + 1));
                if (job.getJobState() == JobState.StateRetryFailed) {
                    throw new RuntimeException(String.format("Job %s retried failed!!", job.getId().toString()));
                }

                try {
                    job.retry();
                    if (job.getState() != JobState.StateFailed) {
                        return;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

//    func (g *group) Run(ctx context.Context) error {
//        g.wg.Add(len(g.jobs))
//
//        errChan := make(chan error, len(g.jobs))
//
//        for i := range g.jobs {
//            if g.isConcurrent {
//                // Do this instead
//                go func(aj Job) {
//                    errChan <- g.runJob(ctx, aj)
//                    g.wg.Done()
//                }(g.jobs[i])
//
//                continue
//            }
//
//            job := g.jobs[i]
//            errChan <- g.runJob(ctx, job)
//            g.wg.Done()
//        }
//
//        var err error
//
//        for i := 1; i <= len(g.jobs); i++ {
//            if v := <-errChan; v != nil {
//                err = v
//            }
//        }
//
//        g.wg.Wait()
//        return err
//    }

//
//    // Retry if needed
//    func (g *group) runJob(ctx context.Context, j Job) error {
//        if err := j.Execute(ctx); err != nil {
//            for {
//                log.Println(err)
//                if j.State() == StateRetryFailed {
//                    return err
//                }
//
//                if j.Retry(ctx) == nil {
//                    return nil
//                }
//            }
//        }
//
//        return nil
//    }

}
