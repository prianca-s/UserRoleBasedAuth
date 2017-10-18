package managers;

import pojo.ResourceAuthorization;

import java.util.List;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public interface ResourceAuthManager {
    List<ResourceAuthorization> getResourceAuthorizationList();
}