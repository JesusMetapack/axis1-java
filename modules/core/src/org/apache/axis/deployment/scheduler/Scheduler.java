/*
* Copyright 2004,2005 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.apache.axis.deployment.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    private final Timer timer = new Timer();

    public class SchedulerTimerTask extends TimerTask {

        private SchedulerTask schedulerTask;
        private DeploymentIterator iterator;


        public SchedulerTimerTask(SchedulerTask schedulerTask, DeploymentIterator iterator) {
            this.schedulerTask = schedulerTask;
            this.iterator = iterator;
        }

        public void run() {
            schedulerTask.run();
            reschedule(schedulerTask, iterator);
        }
    }

    /**
     * Schedules the specified task for execution according to the specified schedule.
     * If times specified by the <code>ScheduleIterator</code> are in the past they are
     * scheduled for immediate execution.
     *
     * @param schedulerTask task to be scheduled
     * @param iterator      iterator that describes the schedule
     * @throws IllegalStateException if task was already scheduled or cancelled,
     *                               scheduler was cancelled, or scheduler thread terminated.
     */

    public void schedule(SchedulerTask schedulerTask, DeploymentIterator iterator) {

        Date time = iterator.next();
        if (time == null) {
            schedulerTask.cancel();
        } else {
            synchronized (schedulerTask.lock) {
                schedulerTask.state = SchedulerTask.SCHEDULED;
                schedulerTask.timerTask =
                        new SchedulerTimerTask(schedulerTask, iterator);
                timer.schedule(schedulerTask.timerTask, time);
            }
        }
    }

    private void reschedule(SchedulerTask schedulerTask, DeploymentIterator iterator) {
        Date time = iterator.next();
        if (time == null) {
            schedulerTask.cancel();
        } else {
            synchronized (schedulerTask.lock) {
                if (schedulerTask.state != SchedulerTask.CANCELLED) {
                    schedulerTask.timerTask =
                            new SchedulerTimerTask(schedulerTask, iterator);
                    timer.schedule(schedulerTask.timerTask, time);
                }
            }
        }
    }

}