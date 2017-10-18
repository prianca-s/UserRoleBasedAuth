package pojo;

import enums.ResourceName;

import java.util.Objects;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class ResourceAuthorization {
    private int id;
    private ResourceName resourceName;
    private Role roleSet;
    private AccessLevel accessLevel;

    public ResourceAuthorization() {
    }

    public ResourceAuthorization(int id, ResourceName resourceName, Role roleSet, AccessLevel accessLevel) {
        this.id = id;
        this.resourceName = resourceName;
        this.roleSet = roleSet;
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
        return roleSet;
    }

    public void setRoleSet(Role roleSet) {
        this.roleSet = roleSet;
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
        if (!(o instanceof ResourceAuthorization)) return false;
        ResourceAuthorization that = (ResourceAuthorization) o;
        return id == that.id &&
                resourceName == that.resourceName &&
                Objects.equals(roleSet, that.roleSet) &&
                Objects.equals(accessLevel, that.accessLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourceName, roleSet, accessLevel);
    }

    @Override
    public String toString() {
        return "ResourceAuthorization{" +
                "id=" + id +
                ", resourceName=" + resourceName +
                ", roleSet=" + roleSet +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
