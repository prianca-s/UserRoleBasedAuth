package managerImpl;

import enums.ActionType;
import enums.ResourceName;
import managers.ResourceAuthManager;
import managers.UserAccessLevelManager;
import pojo.ResourceAuthorization;
import pojo.UserAccessLevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserAccessLevelManagerImpl implements UserAccessLevelManager{
    private static List<UserAccessLevel> userAccessLevelList = new ArrayList<>();

    ResourceAuthManager resourceAuthManager;
    private static final UserAccessLevelManagerImpl INSTANCE = new UserAccessLevelManagerImpl();
    public static UserAccessLevelManagerImpl getInstance() {
        return INSTANCE;
    }

    private UserAccessLevelManagerImpl() {
        resourceAuthManager = ResourceAuthManagerImpl.getInstance();
    }

    @Override
    public void assignAccess(int userId, int resourceAuthId) throws Exception {
        validateArguments(userId, resourceAuthId);

        List<UserAccessLevel> givenUserAccessLevelList = getUserAccessLevelList(userId);

        /**
         * RESOURCE ID WILL BE ADDED TO EMPTY RESOURCE_SET
         * */
        if(givenUserAccessLevelList.isEmpty()) {
            Set<Integer> resourceAuthIdSet = new HashSet<>();
            resourceAuthIdSet.add(resourceAuthId);
            UserAccessLevel userAccessLevel = new UserAccessLevel(userId, resourceAuthIdSet);
            userAccessLevelList.add(userAccessLevel);
        } else {
          /**
           * RESOURCE ID WILL BE ADDED TO EXISTING RESOURCE_SET
           * */
            UserAccessLevel userAccessLevel = givenUserAccessLevelList.get(0);
            Set<Integer> resourceAuthIdSet = userAccessLevel.getResourceAuthIdSet();
            resourceAuthIdSet.add(resourceAuthId);
        }
    }

    @Override
    public void revokeAccess(int userId, int resourceAuthId) throws Exception {
        validateArguments(userId, resourceAuthId);
        List<UserAccessLevel> givenUserAccessLevelList = getUserAccessLevelList(userId);
        /**NO USER_ACCESS LEVEL ENTRY PRESENT IN LIST*/
        if(givenUserAccessLevelList.isEmpty()) {
            throw new Exception("No UserAccessLevel present in list for given userId: " + userId);
        } else {
            /**
             * REMOVE FROM LIST
             * */
            UserAccessLevel userAccessLevel = givenUserAccessLevelList.get(0);
            Set<Integer> resourceAuthIdSet = userAccessLevel.getResourceAuthIdSet();
            resourceAuthIdSet.remove(resourceAuthId);
        }
    }

    @Override
    public boolean canAccess(int userId, ActionType actionType, ResourceName resourceName) throws Exception {
        validateUserId(userId);
        /**
         * NO USER_ACCESS LEVEL ENTRY PRESENT IN LIST
         */
        List<UserAccessLevel> givenUserAccessLevelList = getUserAccessLevelList(userId);

        if(givenUserAccessLevelList.isEmpty()) {
            throw new Exception("No UserAccessLevel present in list for given userId: " + userId);
        }

        UserAccessLevel userAccessLevel = givenUserAccessLevelList.get(0);

        List<ResourceAuthorization> resourceAuthorizationList = ResourceAuthManagerImpl.getResourceAuthorizationList();

        /**
         * FIND RESOURCE_AUTH FROM RESOURCE_AUTH_LIST WHERE
         * - RESOURCE_AUTH SHOULD BE PRESENT IN USER_ACCESS_LEVEL LIST
         * - GIVEN RESOURCE_NAME == RESOURCE_AUTH_LIST RESOURCE NAME
         * - ACTION_TYPE_LEVEL >= GIVEN ACTION_TYPE_LEVEL
         * Eg: User having WRITE access can also READ
         */

         try {
             Set<Integer> resourceAuthIdSet = userAccessLevel.getResourceAuthIdSet();
             if (!resourceAuthIdSet.isEmpty()) {
                 for (ResourceAuthorization resourceAuth : resourceAuthorizationList) {
                     if (resourceAuthIdSet.contains(resourceAuth.getId()) && resourceAuth.getResourceName().equals(resourceName)) {
                         if (resourceAuth.getAccessLevel().getActionType().value() >= actionType.value()) {
                             return true;
                         }
                     }
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
             throw new Exception("Exception occurs while checking User Resource access");
         }

        return false;
    }

    public static UserAccessLevel getUserAccessLevel(int userId)
    {
        return userAccessLevelList
                .stream()
                .filter(p -> p.getUserId() == userId)
                .collect(Collectors.toList())
                .get(0);

    }

    private static List<UserAccessLevel> getUserAccessLevelList(int userId)
    {
        return userAccessLevelList
                .stream()
                .filter(p -> p.getUserId() == userId)
                .collect(Collectors.toList());
    }

    /**
     * CHECK WHETHER USER_ID AND RESOURCE_ID IS PRESENT IN USER AND RESOURCE_AUTH LIST OR NOT
     * */
    private static void validateArguments(int userId, int resourceId) throws Exception {
        validateUserId(userId);

        boolean resourceExists = ResourceAuthManagerImpl.getResourceAuthorizationList()
                .stream()
                .anyMatch(p -> p.getId() == resourceId);

        if (!resourceExists)
        {
            throw new Exception("Resource/User not present for id:" + resourceId);
        }
    }

    private static void validateUserId(int userId) throws Exception {
        boolean userExists = UserManagerImpl.getUserList()
                .stream()
                .anyMatch(p -> p.getId() == userId);

        if (!userExists)
        {
            throw new Exception("User not present for id:" + userId);
        }
    }
}
