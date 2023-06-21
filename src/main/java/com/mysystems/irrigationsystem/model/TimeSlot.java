package com.mysystems.irrigationsystem.model;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimeSlot extends TimerTask {

    int cnt =0;
    int duration ;

    public TimeSlot(int duration)  {
        Timer timer = new Timer();
        timer.schedule(this,5000,500);
        this.duration = duration;
    }


    @Override
    public void run() {

      while(cnt!=duration) {
          System.out.println("watering......");
          cnt++;

      }
            System.out.println("finished");
            System.exit(0);

        }

//        //Timer timer = new Timer();
//         ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(100);
//        //timer.schedule(t,time,500);
//          ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(
//          this,5L,7,
//           TimeUnit.SECONDS);
//          future.cancel(false);
//    }
}

