package pojo;

import java.util.Objects;
import java.util.Set;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserAccessLevel {
    private int userId;
    private Set<Integer> resourceAuthIdSet;

    public UserAccessLevel() {
    }

    public UserAccessLevel(int userId, Set<Integer> resourceAuthIdSet) {
        this.userId = userId;
        this.resourceAuthIdSet = resourceAuthIdSet;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<Integer> getResourceAuthIdSet() {
        return resourceAuthIdSet;
    }

    public void setResourceAuthIdSet(Set<Integer> resourceAuthIdSet) {
        this.resourceAuthIdSet = resourceAuthIdSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccessLevel)) return false;
        UserAccessLevel that = (UserAccessLevel) o;
        return userId == that.userId &&
                Objects.equals(resourceAuthIdSet, that.resourceAuthIdSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, resourceAuthIdSet);
    }

    @Override
    public String toString() {
        return "UserAccessLevel{" +
                "userId=" + userId +
                ", resourceAuthIdSet=" + resourceAuthIdSet +
                '}';
    }
}
