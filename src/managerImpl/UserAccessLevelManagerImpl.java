package managerImpl;

import enums.ActionType;
import enums.ResourceName;
import managers.ResourceAccessManager;
import managers.UserAccessLevelManager;
import managers.UserManager;
import pojo.ResourceAccess;

import java.util.*;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserAccessLevelManagerImpl implements UserAccessLevelManager{
    private Map<Integer, Set<Integer>> userAccessLevelList = new HashMap<>();

    private ResourceAccessManager resourceAccessManager;
    private UserManager userManager;
    private static final UserAccessLevelManagerImpl INSTANCE = new UserAccessLevelManagerImpl();
    public static UserAccessLevelManagerImpl getInstance() {
        return INSTANCE;
    }

    private UserAccessLevelManagerImpl() {
        resourceAccessManager = ResourceAccessManagerImpl.getInstance();
        userManager = UserManagerImpl.getInstance();
    }

    @Override
    public void assignAccess(int userId, int resourceAccessId) throws Exception {
        validateArguments(userId, resourceAccessId);

        try {
            if (userAccessLevelList.containsKey(userId)) {
                /**
                 * RESOURCE ID WILL BE ADDED TO EXISTING RESOURCE_SET
                 * */
                Set<Integer> resourceAccessIdSet = userAccessLevelList.get(userId);
                resourceAccessIdSet.add(resourceAccessId);
            } else {
                /**
                 * RESOURCE ID WILL BE ADDED TO EMPTY RESOURCE_SET
                 * */
                Set<Integer> resourceAccessIdSet = new HashSet<>();
                resourceAccessIdSet.add(resourceAccessId);
                userAccessLevelList.put(userId, resourceAccessIdSet);
            }
        } catch (ClassCastException | NullPointerException | UnsupportedOperationException | IllegalArgumentException e) {
            System.out.println("Exception occur while assigning access to user: " + userId);
            e.printStackTrace();
        }
    }

    @Override
    public void revokeAccess(int userId, int resourceAccessId) throws Exception {
        validateArguments(userId, resourceAccessId);

        if (userAccessLevelList.containsKey(userId)) {
            /**
             * REMOVE FROM LIST
             * */
            try {
                Set<Integer> resourceAccessIdSet = userAccessLevelList.get(userId);
                resourceAccessIdSet.remove(resourceAccessId);
            } catch (ClassCastException | NullPointerException | UnsupportedOperationException e) {
                System.out.println("Unable to revoke access from user user: " + userId);
                e.printStackTrace();
            }
        } else {
            /**NO USER_ACCESS LEVEL ENTRY PRESENT IN LIST*/
            throw new NoSuchElementException("No UserAccessLevel present in list for given userId: " + userId);
        }

    }

    @Override
    public boolean canAccess(int userId, ActionType actionType, ResourceName resourceName) throws Exception {
        validateUserId(userId);

        Set<Integer> userResourceAccessIdSet = userAccessLevelList.get(userId);

        /**
         * IF NO RESOURCE ACCESS IS ASSIGNED TO USER i.e , MAP IS ENTRY IN NOT PRESENT
         * */
        if (userResourceAccessIdSet == null || userResourceAccessIdSet.isEmpty())
        {
            return false;
        }

        List<ResourceAccess> totalResourceAccessList = resourceAccessManager.getResourceAccessList();

        /**
         * FIND RESOURCE_ACCESS FROM RESOURCE_ACCESS_LIST WHERE
         * - RESOURCE_ACCESS SHOULD BE PRESENT IN USER_ACCESS_LEVEL LIST
         * - GIVEN RESOURCE_NAME == RESOURCE_ACCESS_LIST RESOURCE NAME
         * - ACTION_TYPE_LEVEL >= GIVEN ACTION_TYPE_LEVEL
         * Eg: User having WRITE access can also READ
         */

         try {
             for (ResourceAccess resourceAccess : totalResourceAccessList) {
                 if (userResourceAccessIdSet.contains(resourceAccess.getId()) && (resourceAccess.getResourceName().equals(resourceName) || resourceAccess.getResourceName().equals(ResourceName.ALLOW_ALL))) {
                     if (resourceAccess.getAccessLevel().getActionType().value() >= actionType.value()) {
                         return true;
                     }
                 }
             }
         } catch (ClassCastException | NullPointerException e) {
             e.printStackTrace();
             System.out.println("Unable to check User Resource access for user: " + userId);
         }

        return false;
    }

    @Override
    public Map<Integer, Set<Integer>> getUserAccessLevelList()
    {
        return userAccessLevelList;
    }

    /**
     * CHECK WHETHER USER_ID AND RESOURCE_ID IS PRESENT IN USER AND RESOURCE_ACCESS LIST OR NOT
     * */
    private void validateArguments(int userId, int resourceId) throws Exception {
        if (resourceId <= 0)
        {
            throw new Exception("Invalid resource id:" + userId);
        }

        validateUserId(userId);

        boolean resourceExists = resourceAccessManager.getResourceAccessList()
                .stream()
                .anyMatch(p -> p.getId() == resourceId);

        if (!resourceExists)
        {
            throw new NoSuchElementException("Resource not present for id:" + resourceId);
        }
    }

    private void validateUserId(int userId) throws Exception {
        if (userId <= 0)
        {
            throw new Exception("Invalid user id:" + userId);
        }

        boolean userExists = userManager.getUserList()
                .stream()
                .anyMatch(p -> p.getId() == userId);

        if (!userExists)
        {
            throw new NoSuchElementException("User not present for id:" + userId);
        }
    }
}
