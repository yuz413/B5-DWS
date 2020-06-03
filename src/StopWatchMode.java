public class StopWatchMode {
    private boolean is_stop_watch_running;
    private Thread stop_watch_running;
    private Time elapsed_time;  // 스톱워치에 설정된 현재 상태 분과 초로만! (밀리초는 버리는걸로)
    private ButtonActionCallback button_a;
    private ButtonActionCallback button_b;
    private ButtonActionCallback button_c;
    private Segment segment;

    StopWatchMode() {
        is_stop_watch_running = false;
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startStopWatch();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // pause, continue 비활성화
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // init 비활성화
            }
        };
        elapsed_time = new Time();
    }

    public void initStopWatchMode() {
        stop_watch_running = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(!testStopWatchMax()) {
                        Thread.sleep(1000);
                        increaseStopWatchSeconds();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void startStopWatch() {
        is_stop_watch_running = true;
        stop_watch_running.start();
        mappingStopWatchRunning();
    }
    private void pauseStopWatch() {
        is_stop_watch_running = false;
        try {
            stop_watch_running.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mappingStopWatchPause();
    }
    private void continueStopWatch() {
        is_stop_watch_running = true;
        stop_watch_running.notify();
        mappingStopWatchRunning();
    }
    private void increaseStopWatchSeconds() {
        int set_seconds;
        int set_minute;

        // 현재 초와 분을 얻어오기
        set_seconds = elapsed_time.getSeconds();
        set_minute = elapsed_time.getMinute();

        // 1초 증가해주기
        set_seconds++;

        // 60초 될 때, 초는 0으로 바꿔주고 분을 1 증가해주기
        if(set_seconds == 60) {
            elapsed_time.setSeconds(0);
            elapsed_time.setMinute(set_minute + 1);
        } else {
            elapsed_time.setSeconds(set_seconds);
        }
    }
    // 스탑워치의 한계치 59분 59초
    private boolean testStopWatchMax() {
        if(elapsed_time.getMinute() == 59 && elapsed_time.getSeconds() == 59) {
            return true;
        } else {
            return false;
        }
    }
    // 중단한 후에만 reset 가능
    private void resetStopWatch() {
        is_stop_watch_running = false;
        try {
            stop_watch_running.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elapsed_time.setSeconds(0);
        elapsed_time.setMinute(0);
        mappingStopWatchState();
    }
    private void mappingStopWatchState() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startStopWatch();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
    }
    private void mappingStopWatchRunning() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                pauseStopWatch();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
    }
    private void mappingStopWatchPause() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                continueStopWatch();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                resetStopWatch();
            }
        };
    }

}