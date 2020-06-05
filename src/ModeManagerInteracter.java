public class ModeManagerInteracter {
    private static ModeManagerInteracter instance = null;

    private MethodCallback forced_action = null;
    private StandardCallback cancel_forced = null;


    private ModeManagerInteracter(){}

    public static ModeManagerInteracter getInstance(){
        if(instance == null){
            instance = new ModeManagerInteracter();
        }
        return instance;
    }


    public void registerReserveForcedAction(MethodCallback callback){
        this.forced_action = callback;
    }

    public void registerCancelForceAction(StandardCallback callback){
        this.cancel_forced = callback;
    }


    public void activeReserveForcedAction(StandardCallback callback){
        forced_action.IvokeMethod(callback);
    }

    public void activeCancelForceAction(){
        cancel_forced.CallbackFunction();
    }
}
