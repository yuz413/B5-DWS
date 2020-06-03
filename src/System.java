import java.awt.*;

public class System {

    private Segment segment;
    private ModeManager mode_manager;


    public System(){
        segment = new Segment();
        mode_manager = new ModeManager();

    }

    public void pressButtonA(){

    }

    public void pressButtonB(){

    }

    public void pressButtonC(){

    }

    public void pressButtonD(){

    }

    public String getSegmentContentLower(){
        return segment.getSegmentLower();
    }

    public String getSegmentContentUpper(){
        return segment.getSegmentUpper();
    }

    public Color getTextColor(){
        return null;
    }

    public Color getBackGroundColor(){
        return null;
    }

    public void initWatch(){

    }
}
