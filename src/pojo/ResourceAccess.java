package pojo;

import enums.ResourceName;

import java.util.Objects;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class ResourceAccess {
    private int id;
    private ResourceName resourceName;
    private Role role;
    private AccessLevel accessLevel;

    public ResourceAccess() {
    }

    public ResourceAccess(int id, ResourceName resourceName, Role role, AccessLevel accessLevel) {
        this.id = id;
        this.resourceName = resourceName;
        this.role = role;
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResourceName getResourceName() {
        return resourceName;
    }

    public void setResourceName(ResourceName resourceName) {
        this.resourceName = resourceName;
    }

    public Role getRoleSet() {
        return role;
    }

    public void setRoleSet(Role role) {
        this.role = role;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceAccess)) return false;
        ResourceAccess that = (ResourceAccess) o;
        return id == that.id &&
                resourceName == that.resourceName &&
                Objects.equals(role, that.role) &&
                Objects.equals(accessLevel, that.accessLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourceName, role, accessLevel);
    }

    @Override
    public String toString() {
        return "ResourceAuthorization{" +
                "id=" + id +
                ", resourceName=" + resourceName +
                ", role=" + role +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
