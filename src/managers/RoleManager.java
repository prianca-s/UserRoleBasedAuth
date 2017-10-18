package managers;

import pojo.Role;

import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface RoleManager {

    List<Role> getRoleList();

    int addRole(Role role) throws Exception;

    void removeRole(int id);

    void updateRole(Role updatedRole);
}
