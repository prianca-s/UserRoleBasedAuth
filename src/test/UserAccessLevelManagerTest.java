package test;

import enums.ActionType;
import enums.ResourceName;
import managerImpl.*;
import managers.PopulateManager;
import managers.UserAccessLevelManager;
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

    @Before
    public void setUp() throws Exception {
        PopulateManager userManager = UserManagerImpl.getInstance();
        PopulateManager roleManager = RoleManagerImpl.getInstance();
        PopulateManager accessLevelManager = AccessLevelManagerImpl.getInstance();
        PopulateManager resourceAuthManager = ResourceAuthManagerImpl.getInstance();
        userAccessLevelManager = UserAccessLevelManagerImpl.getInstance();
        userManager.populate();
        roleManager.populate();
        accessLevelManager.populate();
        resourceAuthManager.populate();
    }

    /**
     * - ASIIGN ROLE
     * - SHOULD NOT INSERT EXTRA DUPLICATE ENTRY OF RESOURCE_LIST
     * */
    @Test
    public void assignRoleAccessToUser() throws Exception {
        int userId = UserManagerImpl.getUserList().get(0).getId();
        int resourceAuthId = ResourceAuthManagerImpl.getResourceAuthorizationList().get(0).getId();

        userAccessLevelManager.assignAccess(userId, resourceAuthId);

        boolean resourceIdPresent = UserAccessLevelManagerImpl.getUserAccessLevel(userId).getResourceAuthIdSet().contains(resourceAuthId);

        Assert.assertEquals(resourceIdPresent, true);

        int oldResourceIdsCount = UserAccessLevelManagerImpl.getUserAccessLevel(userId).getResourceAuthIdSet().size();

        userAccessLevelManager.assignAccess(userId, resourceAuthId);

        int newResourceIdsCount = UserAccessLevelManagerImpl.getUserAccessLevel(userId).getResourceAuthIdSet().size();

        Assert.assertEquals(oldResourceIdsCount, newResourceIdsCount);
    }

    @Test
    public void revokeRoleAccessFromUser() throws Exception {
        int userId = UserManagerImpl.getUserList().get(0).getId();
        int resourceAuthId = ResourceAuthManagerImpl.getResourceAuthorizationList().get(0).getId();

        userAccessLevelManager.assignAccess(userId, resourceAuthId);

        boolean resourceIdPresent = UserAccessLevelManagerImpl.getUserAccessLevel(userId).getResourceAuthIdSet().contains(resourceAuthId);

        Assert.assertEquals(resourceIdPresent, true);

        userAccessLevelManager.revokeAccess(userId, resourceAuthId);

        resourceIdPresent = UserAccessLevelManagerImpl.getUserAccessLevel(userId).getResourceAuthIdSet().contains(resourceAuthId);

        Assert.assertEquals(resourceIdPresent, false);
    }

    @Test
    public void canAccessResource() throws Exception {
        int userId = UserManagerImpl.getUserList().get(0).getId();
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
