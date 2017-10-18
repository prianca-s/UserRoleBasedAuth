package test;

import enums.ActionType;
import enums.ResourceName;
import managerImpl.*;
import managers.ResourceAccessManager;
import managers.UserAccessLevelManager;
import managers.UserManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserAccessLevelManagerTest {
    private UserAccessLevelManager userAccessLevelManager;
    private UserManager userManager;
    private ResourceAccessManager resourceAccessManager;

    @Before
    public void setUp() throws Exception {
        userManager = UserManagerImpl.getInstance();
        userAccessLevelManager = UserAccessLevelManagerImpl.getInstance();
        resourceAccessManager = ResourceAccessManagerImpl.getInstance();
    }

    /**
     * - ASIIGN ROLE
     * - SHOULD NOT INSERT EXTRA DUPLICATE ENTRY OF RESOURCE_LIST
     * */
    @Test
    public void assignRoleAccessToUser() throws Exception {
        int userId = userManager.getUserList().get(0).getId();
        int resourceAccessId = resourceAccessManager.getResourceAccessList().get(0).getId();

        userAccessLevelManager.assignAccess(userId, resourceAccessId);

        boolean resourceIdPresent = userAccessLevelManager.getUserAccessLevelList().get(userId).contains(resourceAccessId);

        Assert.assertEquals(resourceIdPresent, true);

        int oldResourceIdsCount = userAccessLevelManager.getUserAccessLevelList().get(userId).size();

        userAccessLevelManager.assignAccess(userId, resourceAccessId);

        int newResourceIdsCount = userAccessLevelManager.getUserAccessLevelList().get(userId).size();

        Assert.assertEquals(oldResourceIdsCount, newResourceIdsCount);
    }

    @Test
    public void revokeRoleAccessFromUser() throws Exception {
        int userId = userManager.getUserList().get(0).getId();
        int resourceAccessId = resourceAccessManager.getResourceAccessList().get(0).getId();

        userAccessLevelManager.assignAccess(userId, resourceAccessId);

        boolean resourceIdPresent = userAccessLevelManager.getUserAccessLevelList().get(userId).contains(resourceAccessId);

        Assert.assertEquals(resourceIdPresent, true);

        userAccessLevelManager.revokeAccess(userId, resourceAccessId);

        resourceIdPresent = userAccessLevelManager.getUserAccessLevelList().get(userId).contains(resourceAccessId);

        Assert.assertEquals(resourceIdPresent, false);
    }

    @Test
    public void canAccessResource() throws Exception {
        int userId = userManager.getUserList().get(0).getId();
        ResourceName resourceName = ResourceName.BOOKING;

//      Provide write access
        userAccessLevelManager.assignAccess(userId, 2);

        boolean canAccess = userAccessLevelManager.canAccess(userId, ActionType.READ, resourceName);

        Assert.assertEquals(canAccess, true);

        canAccess = userAccessLevelManager.canAccess(userId, ActionType.WRITE, resourceName);
        Assert.assertEquals(canAccess, true);

        canAccess = userAccessLevelManager.canAccess(userId, ActionType.DELETE, resourceName);
        Assert.assertEquals(canAccess, false);


        canAccess = userAccessLevelManager.canAccess(userId, ActionType.READ, ResourceName.PAYMENT);
        Assert.assertEquals(canAccess, false);

    }

    @After
    public void tearDown() throws Exception {
    }
}
