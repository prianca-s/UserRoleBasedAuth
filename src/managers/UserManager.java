package managers;

import pojo.User;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface UserManager {
    int addUser(User user) throws Exception;

    void removeUser(int id);

    void updateUser(User updatedUser);
}
