public class CurrentTimeMode {
    private TimeManager timeManager;    // 현재 시간을 계산해주는 모듈을 저장하고 있는 변수.
    private Thread currentTimeUpdater;
    private Segment segment;

    public void InitCurrentTimeMode() {
        currentTimeUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(1000);
                        SyncWithCurrentTime();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void SyncWithCurrentTime() {
        Time curr_time;
        curr_time = timeManager.getCurrentTime();

        segment.setSegmentUpper(curr_time.getYear() +"년 " + curr_time.getMounth() + "월 " +curr_time.getDay() + "일", true);
        segment.setSegmentLower(curr_time.getHour()+"시 " + curr_time.getMinute() + "분 " + curr_time.getSeconds()+ "초 ", true);
    }

}
