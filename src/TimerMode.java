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

    private int total_seconds;  // 분을 초로 바꾼 timer 시간

    TimerMode() {
        is_timer_running = false;
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerMinute();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerSeconds();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startTimer();
            }
        };
        setted_time = new Time();
    }
    public void initTimerMode() {
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
    private void startTimer() {
        int set_minute;
        int set_seconds;

        if(checkTimerZero() == true) {
            // 00 : 00 일 땐, 아무일도 안 일어난다.
        } else {
            set_minute = setted_time.getMinute();
            set_seconds = setted_time.getSeconds();

            // setted_time 에 사용자가 입력한 정보를 backup_time에 저장한다.
            backup_time.setMinute(set_minute);
            backup_time.setSeconds(set_seconds);

            // 분을 초 단위로 바꾸고, curr_seconds 에 더하기
            total_seconds = Time.convertMinuteToSeconds(set_minute) + set_seconds;

            // 타이머 시작
            timer_runner.start();
            is_timer_running = true;

            // startTimer 눌렀을 때의, A, B, C 의 번호 매핑이 바뀜.
            mappingTimerRunning();
        }
    }
    private void pauseTimer()  {
        if(is_timer_running == true) {
            try {
                timer_runner.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            is_timer_running = false;
            mappingTimerPause();
        }
    }
    private void continueTimer() {
        if(checkTimerZero() == false) {
            timer_runner.notify();
            is_timer_running = true;
            mappingTimerRunning();
        }
    }
    private void cancelTimer() {
        is_timer_running = false;
        //loadSettingTime();
        mappingTimerState();
    }
    private void increaseTimerMinute() {
        if(setted_time.getMinute() < 59) {
            int set_minute = setted_time.getMinute();
            set_minute++;
            setted_time.setMinute(set_minute);
        } else {
            setted_time.setMinute(0);
        }
    }
    private void increaseTimerSeconds() {
        if(setted_time.getSeconds() < 59) {
            int set_seconds = setted_time.getSeconds();
            set_seconds++;
            setted_time.setSeconds(set_seconds);
        } else {
            setted_time.setSeconds(0);
        }
    }
//    private void loadSettingTime() {
//
//    }
    private boolean checkTimerZero() {
        if(setted_time.getMinute() == 0 && setted_time.getSeconds() == 0) {
            return true;
        }
        return false;
    }
    private void decreaseTimer() {
        total_seconds--;
    }
    private void mappingTimerState() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerMinute();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerSeconds();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startTimer();
            }
        };
    }
    private void mappingTimerRunning() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                pauseTimer();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
    }
    private void mappingTimerPause() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                continueTimer();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                cancelTimer();
            }
        };
    }
}
