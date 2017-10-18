package enums;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public enum ResourceName {
    BOOKING("BOOKING"),
    PAYMENT("PAYMENT"),
    PRICING("PRICING"),
    REFUND("REFUND");

    private String value;

    ResourceName(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ResourceName fromValue(String value) throws Exception {
        for (ResourceName resourceName : ResourceName.values()){
            if (resourceName.value.equals(value)) {
                return resourceName;
            }
        }
        throw new Exception("Unable to find any ResourceName from input:" + value);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
