package managerImpl;

import enums.RoleName;
import managers.RoleManager;
import pojo.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class RoleManagerImpl implements RoleManager {
    private List<Role> roleList;

    private static final RoleManagerImpl INSTANCE = new RoleManagerImpl();
    public static RoleManagerImpl getInstance() {
        return INSTANCE;
    }

    private RoleManagerImpl() {
        populate();
    }

    @Override
    public List<Role> getRoleList()
    {
        return roleList;
    }

    @Override
    public int addRole(Role role) throws Exception {
        try {
            roleList.add(role);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return roleList.get(roleList.size()-1).getId();
    }

    @Override
    public void removeRole(int id) {
        roleList.removeIf(Role -> Role.getId() == id);
    }

    @Override
    public void updateRole(Role updatedRole) {
        Role existingRole=null;
        for (Role user : roleList) {
            if (user.getId() == updatedRole.getId()) {
                existingRole = user;
            }
        }
        if (existingRole != null) {
            existingRole.setName(updatedRole.getName());
        }
    }

    private void populate() {
        Role managerRole = new Role(1, "test", RoleName.MANAGER);
        Role seniorManagerRole = new Role(2, "test", RoleName.SENIOR_MANAGER);
        Role adminRole = new Role(3, "test", RoleName.ADMIN);
        Role superAdminRole = new Role(4, "test", RoleName.SUPER_ADMIN);

        roleList = new ArrayList<>();

        try {
            roleList.addAll(Arrays.asList(managerRole, seniorManagerRole, adminRole, superAdminRole));
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Unable to add role to the list. Error: " + e.getLocalizedMessage());
        }
    }
}
