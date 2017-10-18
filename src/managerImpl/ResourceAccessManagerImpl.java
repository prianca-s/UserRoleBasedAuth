package managerImpl;

import enums.ResourceName;
import managers.AccessLevelManager;
import managers.ResourceAccessManager;
import managers.RoleManager;
import pojo.AccessLevel;
import pojo.ResourceAccess;
import pojo.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class ResourceAccessManagerImpl implements ResourceAccessManager {
    private List<ResourceAccess> resourceAccessList = new ArrayList<>();

    private RoleManager roleManager;
    private AccessLevelManager accessLevelManager;

    private static final ResourceAccessManagerImpl INSTANCE = new ResourceAccessManagerImpl();
    public static ResourceAccessManagerImpl getInstance() {
        return INSTANCE;
    }

    private ResourceAccessManagerImpl() {
        roleManager = RoleManagerImpl.getInstance();
        accessLevelManager = AccessLevelManagerImpl.getInstance();
        populate();
    }

    @Override
    public List<ResourceAccess> getResourceAccessList() {
        return resourceAccessList;
    }

    private void populate() {
        List<Role> roleList = roleManager.getRoleList();
        List<AccessLevel> accessLevelList = accessLevelManager.getAccessLevelList();

//      READ ACCESS
        ResourceAccess resourceAccess1 = new ResourceAccess(1, ResourceName.BOOKING, roleList.get(0), accessLevelList.get(0));
//      WRITE ACCESS
        ResourceAccess resourceAccess2 = new ResourceAccess(2, ResourceName.BOOKING, roleList.get(0), accessLevelList.get(1));
//      READ AND WRITE ACCESS
        ResourceAccess resourceAccess3 = new ResourceAccess(3, ResourceName.BOOKING, roleList.get(1), accessLevelList.get(1));
//      READ, WRITE, DELETE ACCESS
        ResourceAccess resourceAccess4 = new ResourceAccess(4, ResourceName.PAYMENT, roleList.get(2), accessLevelList.get(2));
//      READ ACCESS
        ResourceAccess resourceAccess5 = new ResourceAccess(5, ResourceName.PRICING, roleList.get(0), accessLevelList.get(0));
//      SUPER ADMIN ALL ACCESS
        ResourceAccess resourceAccess6 = new ResourceAccess(6, ResourceName.ALLOW_ALL, roleList.get(0), accessLevelList.get(2));

        try {
            resourceAccessList.addAll(Arrays.asList(resourceAccess1, resourceAccess2, resourceAccess3, resourceAccess4, resourceAccess5, resourceAccess6));
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
        e.printStackTrace();
        System.out.println("Unable to add resourceAccess to the list. Error: " + e.getLocalizedMessage());
    }
    }
    /** TODO:
     * FURTHER METHODS CAN BE IMPLEMENTED TO ADD/UPDATE/REMOVE RESOURCE ACCESS FROM RESOURCE_AUTH_LIST
    * */
}
