package managerImpl;

import enums.ActionType;
import managers.AccessLevelManager;
import pojo.AccessLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author priyanka_s
 *         Part of AccessLevelAuthSystem
 *         on 18/10/17.
 */
public class AccessLevelManagerImpl implements AccessLevelManager {
    private List<AccessLevel> accessLevelList;

    private static final AccessLevelManagerImpl INSTANCE = new AccessLevelManagerImpl();
    public static AccessLevelManagerImpl getInstance() {
        return INSTANCE;
    }

    private AccessLevelManagerImpl() {
        populate();
    }

    @Override
    public List<AccessLevel> getAccessLevelList()
    {
        return accessLevelList;
    }

    @Override
    public int addAccessLevel(AccessLevel accessLevel) throws Exception {
        try {
            accessLevelList.add(accessLevel);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            throw new Exception("Unable to add user to list. Error: {}"+ e.getMessage());
        }
        return accessLevelList.get(accessLevelList.size()-1).getId();
    }

    @Override
    public void removeAccessLevel(int id) {
        accessLevelList.removeIf(AccessLevel -> AccessLevel.getId() == id);
    }

    @Override
    public void updateAccessLevel(AccessLevel updatedAccessLevel) {
        AccessLevel existingAccessLevel=null;
        for (AccessLevel user : accessLevelList) {
            if (user.getId() == updatedAccessLevel.getId()) {
                existingAccessLevel = user;
            }
        }
        if (existingAccessLevel != null) {
            existingAccessLevel.setActionType(updatedAccessLevel.getActionType());
        }
    }

    public AccessLevel findAccessLevel(ActionType actionType) {
        return accessLevelList
                .stream()
                .filter(p -> p.getActionType().equals(actionType))
                .collect(Collectors.toList())
                .get(0);
    }

    private void populate() {
        AccessLevel readAccessLevel = new AccessLevel(1, "test", ActionType.READ );
        AccessLevel writeAccessLevel = new AccessLevel(2, "test", ActionType.WRITE);
        AccessLevel deleteAccessLevel = new AccessLevel(3, "test", ActionType.DELETE);

        accessLevelList = new ArrayList<>();

        try {
            accessLevelList.addAll(Arrays.asList(readAccessLevel, writeAccessLevel, deleteAccessLevel));
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Unable to add accessLevel to the list. Error: " + e.getLocalizedMessage());
        }
    }

}
