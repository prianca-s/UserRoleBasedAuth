package managers;

import enums.ActionType;
import enums.ResourceName;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface UserAccessLevelManager {

    void assignAccess(int userId, int resourceAuthId) throws Exception;

    void revokeAccess(int userId, int resourceAuthId) throws Exception;

    boolean canAccess(int userId, ActionType actionType, ResourceName resourceName) throws Exception;
}
