package enums;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public enum ActionType {
    READ(0),
    WRITE(1),
    DELETE(2);

    private final int value;

    ActionType(int value) {
        this.value = value;
    }

    public int value(){
        return value;
    }

    public static ActionType fromValue(int actionType) throws Exception {
        for (ActionType status : ActionType.values()){
            if(status.value == actionType){
                return status;
            }
        }
        throw new Exception("Unable to find any action type from input: " + actionType);
    }
}
