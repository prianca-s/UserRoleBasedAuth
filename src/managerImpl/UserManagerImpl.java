package managerImpl;

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
public class UserManagerImpl implements UserManager {
    private List<User> userList;

    private static final UserManagerImpl INSTANCE = new UserManagerImpl();
    public static UserManagerImpl getInstance() {
        return INSTANCE;
    }

    private UserManagerImpl() {
        populate();
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public int addUser(User user) throws Exception {
        try {
            userList.add(user);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
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

    private void populate() {
        User user1 = new User(1, "test", "Joseph", "joseph@gmail.com");
        User user2 = new User(2, "test", "Robert", "robert@gmail.com");

        userList = new ArrayList<>();

        try {
            userList.addAll(Arrays.asList(user1, user2));
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Unable to add user to the list. Error: " + e.getLocalizedMessage());
        }
    }
}
