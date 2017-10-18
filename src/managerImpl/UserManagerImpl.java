package managerImpl;

import managers.PopulateManager;
import managers.UserManager;
import pojo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserManagerImpl implements UserManager, PopulateManager {
    private static List<User> userList;

    private static final UserManagerImpl INSTANCE = new UserManagerImpl();
    public static UserManagerImpl getInstance() {
        return INSTANCE;
    }

    private UserManagerImpl() {
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUserList(List<User> userList) {
        UserManagerImpl.userList = userList;
    }

    @Override
    public void populate() {
        User user1 = new User(1, "test", "Joseph", "joseph@gmail.com");
        User user2 = new User(2, "test", "Robert", "robert@gmail.com");

        userList = new ArrayList<>();
        userList.addAll(Arrays.asList(user1, user2));
    }

    @Override
    public int addUser(User user) throws Exception {
        try {
            userList.add(user);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("Unable to add user to list. Error: {}"+ e.getMessage());
        }

        return userList.get(userList.size()-1).getId();
    }

    @Override
    public void removeUser(int id) {
        userList.removeIf(User -> User.getId() == id);
    }

    @Override
    public void updateUser(User updatedUser) {
        User existingUser=null;
        for (User user : userList) {
            if (user.getId() == updatedUser.getId()) {
                existingUser = user;
            }
        }
        if (existingUser != null) {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setName(updatedUser.getName());
        }
    }
}
