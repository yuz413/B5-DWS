import java.net.URL;

public class Test {
    public static void main(String[] args) {
        Buzzer buzzer = new Buzzer();
        buzzer.OnBuzzer();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buzzer.OffBuzzer();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buzzer.OnBuzzer();
        buzzer.OnBuzzer();
    }
}
