import java.time.LocalDateTime;

public class Time {
    private int seconds, minute, hour;
    private int year, mounth, day;

    public Time(){
        this.seconds = 0;
        this.minute = 0;
        this.hour = 0;
        this.year = 0;
        this.mounth = 0;
        this.day = 0;
    }

    public Time(LocalDateTime time){
        this.seconds = time.getSecond();
        this.minute = time.getMinute();
        this.hour = time.getHour();
        this.day = time.getDayOfMonth();
        this.mounth = time.getMonthValue();
        this.year = time.getYear();
    }

    public Time(Time time){
        this.seconds = time.seconds;
        this.day = time.day;
        this.hour = time.hour;
        this.minute = time.minute;
        this.year = time.year;
        this.mounth = time.mounth;
    }


    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }






    public static int convertMinuteToSeconds(int minute){
        return minute * 60;
    }

    public static int convertHourToMinute(int hour){
        return hour * 60;
    }

    public static int convertSecondsToMinute(int seconds){
        return seconds / 60;
    }

    public static int convertMinuteToHour(int minute){
        return minute / 60;
    }
}
