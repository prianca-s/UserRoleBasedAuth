package managers;

import pojo.User;

import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface UserManager {
    List<User> getUserList();

    int addUser(User user) throws Exception;

    void removeUser(int id);

    void updateUser(User updatedUser);
}
