public class ModeManager implements Mode{
    //Fields
    private Segment segment;
    private ButtonActionCallback on_button_d_pressed;
    private Mode[] unused_modes = new Mode[2];
    private Mode[] used_modes = new Mode[4];
    private Mode current_mode = used_modes[0];
    private int mode_index = 0;
    private StandardCallback forced_action = null;
    private boolean overwrite_action_exist = false;
    private boolean is_main_screen = true;



    public ModeManager(Segment segment){
        this.segment = segment;
    }

    public void initWatch(){

    }

    public void requestButtonA(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                decreaseModeIndex();
            }else{
                current_mode.OnButtonA();
            }
        }
    }

    public void requestButtonB(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                increaseModeIndex();
            }else{
                current_mode.OnButtonB();
            }
        }
    }

    public void requestButtonC(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                useCurrentMode();
            }else{
                current_mode.OnButtonC();
            }
        }
    }

    public void onButtonD(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                useConfigMode();
            }else{
                current_mode.OnEndOfThisMode();
                is_main_screen = true;
                displayCurrentMode();
            }
        }
    }

    public void RegisterCallback(){

    }

    public void increaseModeIndex(){
        mode_index ++;
        if(mode_index > 3){mode_index = 0;}
        displayCurrentMode();
    }

    public void decreaseModeIndex(){
        mode_index --;
        if(mode_index < 0){mode_index = 3;}
        displayCurrentMode();
    }

    public void useConfigMode(){
        useThisMode(this);
    }

    public void useCurrentMode(){
        useThisMode(used_modes[mode_index]);
    }

    public void useThisMode(Mode mode){
        current_mode = mode;
        current_mode.OnInitThisMode();
        is_main_screen = false;
    }

    public void displayCurrentMode(){
        String upperContent, lowerContent;
        upperContent = "      " + mode_index + "     ";
        lowerContent = used_modes[mode_index].getClass().getName();
        segment.setSegmentLower(lowerContent, true);
        segment.setSegmentUpper(upperContent, true);
    }

    public void reserveForcedAction(StandardCallback sc){
        if(sc != null){
            forced_action = sc;
            overwrite_action_exist = true;
        }
    }

    public void cancelForcedAction(){
        forced_action = null;
        overwrite_action_exist = false;
    }

    @Override
    public void OnButtonA() {
        Mode temp = used_modes[mode_index];
        used_modes[mode_index] = unused_modes[0];
        unused_modes[0] = temp;
        current_mode.OnEndOfThisMode();
    }

    @Override
    public void OnButtonB() {
        Mode temp = used_modes[mode_index];
        used_modes[mode_index] = unused_modes[1];
        unused_modes[1] = temp;
        current_mode.OnEndOfThisMode();
    }

    @Override
    public void OnButtonC() {
        // Do Nothing!! -> DummyMethod
    }

    @Override
    public void OnEndOfThisMode() {
        is_main_screen = true;
        displayCurrentMode();
    }

    @Override
    public void OnInitThisMode() {
        String upperContent, lowerContent;
        upperContent = unused_modes[0].getClass().getName().substring(0,4) + "    " + unused_modes[1].getClass().getName().substring(0,4);
        lowerContent = used_modes[mode_index].getClass().getName();
        segment.setSegmentLower(lowerContent, true);
        segment.setSegmentUpper(upperContent, true);
    }



}
