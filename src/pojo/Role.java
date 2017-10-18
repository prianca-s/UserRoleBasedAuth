package pojo;

import enums.RoleName;

import java.util.Objects;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class Role extends BaseDTO {
    private RoleName name;

    public Role() {
    }

    public Role(int id, String createdBy, RoleName name) {
        super(id, createdBy);
        this.name = name;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return name == role.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                "} " + super.toString();
    }
}
