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
    //    private boolean checkTimerZero() {
//    }
    private void decreaseTimer() {
    }
    private void mappingTimerState() {}
    private void mappingTimerRunning() {}
    private void mappingTimerPause() {}
}
