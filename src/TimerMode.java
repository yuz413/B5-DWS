public class TimerMode {
    private boolean is_timer_running;
    private Segment segment;
    private ButtonActionCallback button_a;
    private ButtonActionCallback button_b;
    private ButtonActionCallback button_c;
    private Time setted_time;
    private Time backup_time;
    private Thread timer_runner;
    private Buzzer buzzer;

    TimerMode() {
        setted_time = new Time();
        timer_runner = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!checkTimerZero()) {
                        Thread.sleep(1000);
                        decreaseTimer();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void initTimerMode() {
        decreaseTimer();
    }
    private void startTimer() {}
    private void pauseTimer() {}
    private void continueTimer() {}
    private void cancelTimer() {}
    private void increaseTimeMinute() {}
    private void increaseTimeSeconds() {}
    private void loadSettingTime() {}
    private boolean checkTimerZero() {
        if(setted_time.getMinute() == 0 && setted_time.getSeconds() == 0) {
            return true;
        }
        return false;
    }
    private void decreaseTimer() {
        int curr_seconds = setted_time.getSeconds();
        int curr_minute = setted_time.getMinute();
    }
    private void mappingTimerState() {}
    private void mappingTimerRunning() {}
    private void mappingTimerPause() {}
}
