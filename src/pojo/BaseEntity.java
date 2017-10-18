package pojo;

import java.util.Objects;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class BaseDTO {
    private int id;
    private String createdBy;

    public BaseDTO() {
    }

    public BaseDTO(int id, String createdBy) {
        this.id = id;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDTO)) return false;
        BaseDTO baseDTO = (BaseDTO) o;
        return id == baseDTO.id &&
                Objects.equals(createdBy, baseDTO.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdBy);
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id=" + id +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
