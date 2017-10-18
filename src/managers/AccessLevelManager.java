package managers;

import pojo.AccessLevel;

import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface AccessLevelManager {
    List<AccessLevel> getAccessLevelList();

    int addAccessLevel(AccessLevel accessLevel) throws Exception;

    void removeAccessLevel(int id);

    void updateAccessLevel(AccessLevel updatedAccessLevel);
}
