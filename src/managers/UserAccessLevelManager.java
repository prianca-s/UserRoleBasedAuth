package managers;

import enums.ActionType;
import enums.ResourceName;

import java.util.Map;
import java.util.Set;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface UserAccessLevelManager {

    void assignAccess(int userId, int resourceAccessId) throws Exception;

    void revokeAccess(int userId, int resourceAccessId) throws Exception;

    boolean canAccess(int userId, ActionType actionType, ResourceName resourceName) throws Exception;

    Map<Integer, Set<Integer>> getUserAccessLevelList();
}
