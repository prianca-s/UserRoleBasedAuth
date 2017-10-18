package managerImpl;

import enums.ResourceName;
import managers.AccessLevelManager;
import managers.PopulateManager;
import managers.ResourceAuthManager;
import managers.RoleManager;
import pojo.AccessLevel;
import pojo.ResourceAuthorization;
import pojo.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class ResourceAuthManagerImpl implements ResourceAuthManager, PopulateManager{
    private static List<ResourceAuthorization> resourceAuthorizationList = new ArrayList<>();

    RoleManager roleManager;
    AccessLevelManager accessLevelManager;

    private static final ResourceAuthManagerImpl INSTANCE = new ResourceAuthManagerImpl();
    public static ResourceAuthManagerImpl getInstance() {
        return INSTANCE;
    }

    private ResourceAuthManagerImpl() {
        roleManager = RoleManagerImpl.getInstance();
        accessLevelManager = AccessLevelManagerImpl.getInstance();
    }

    @Override
    public void populate() {
        List<Role> roleList = roleManager.getRoleList();
        List<AccessLevel> accessLevelList = accessLevelManager.getAccessLevelList();

//      READ ACCESS
        ResourceAuthorization resourceAuthorization1 = new ResourceAuthorization(1, ResourceName.BOOKING, roleList.get(0), accessLevelList.get(0));
//      WRITE ACCESS
        ResourceAuthorization resourceAuthorization2 = new ResourceAuthorization(2, ResourceName.BOOKING, roleList.get(0), accessLevelList.get(1));
//      READ AND WRITE ACCESS
        ResourceAuthorization resourceAuthorization3 = new ResourceAuthorization(3, ResourceName.BOOKING, roleList.get(1), accessLevelList.get(1));
//      READ, WRITE, DELETE ACCESS
        ResourceAuthorization resourceAuthorization4 = new ResourceAuthorization(4, ResourceName.PAYMENT, roleList.get(2), accessLevelList.get(2));
//      READ ACCESS
        ResourceAuthorization resourceAuthorization5 = new ResourceAuthorization(5, ResourceName.PRICING, roleList.get(0), accessLevelList.get(0));
//      SUPER ADMIN ALL ACCESS
        ResourceAuthorization resourceAuthorization6 = new ResourceAuthorization(6, ResourceName.ALLOW_ALL, roleList.get(0), accessLevelList.get(2));
        resourceAuthorizationList.addAll(Arrays.asList(resourceAuthorization1, resourceAuthorization2, resourceAuthorization3, resourceAuthorization4, resourceAuthorization5, resourceAuthorization6));
    }

    public static List<ResourceAuthorization> getResourceAuthorizationList() {
        return resourceAuthorizationList;
    }

    /** TODO:
     * FURTHER METHODS CAN BE IMPLEMENTED TO ADD/UPDATE/ROMOVE RESOURCE AUTHORIZATION FROM RESOURCE_AUTH_LIST
    * */
}
