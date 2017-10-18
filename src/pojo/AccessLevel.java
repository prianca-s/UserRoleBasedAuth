package pojo;

import enums.ActionType;

import java.util.Objects;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class AccessLevel extends BaseDTO {
    private ActionType actionType;

    public AccessLevel() {
    }

    public AccessLevel(int id, String createdBy, ActionType actionType) {
        super(id, createdBy);
        this.actionType = actionType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessLevel)) return false;
        if (!super.equals(o)) return false;
        AccessLevel that = (AccessLevel) o;
        return actionType == that.actionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), actionType);
    }

    @Override
    public String toString() {
        return "AccessLevel{" +
                "actionType=" + actionType +
                "} " + super.toString();
    }
}
