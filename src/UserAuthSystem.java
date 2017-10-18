import enums.ActionType;
import enums.ResourceName;
import managerImpl.*;
import managers.PopulateManager;
import managers.UserAccessLevelManager;

import java.util.Scanner;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserAuthSystem {
    public static void main(String[] args) throws Exception {

        PopulateManager userManager = UserManagerImpl.getInstance();
        PopulateManager roleManager = RoleManagerImpl.getInstance();
        PopulateManager accessLevelManager = AccessLevelManagerImpl.getInstance();
        PopulateManager resourceAuthManager = ResourceAuthManagerImpl.getInstance();
        UserAccessLevelManager userAccessLevelManager = UserAccessLevelManagerImpl.getInstance();

        /**
         * POPULATE:
         * - USER,
         * - ROLE
         * - ACCESS_LEVEL DATA
         * - RESOURCE_AUTH DATA
         * */
        userManager.populate();
        roleManager.populate();
        accessLevelManager.populate();
        resourceAuthManager.populate();

        Scanner scan = new Scanner(System.in);
        int userId, resourceAuthId;
        int option;
        System.out.println("ENTER NO. OF TIMES YOU WANT TO SCAN ACTION");
        int t = scan.nextInt();

        while (t>0) {
            System.out.println("ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)");

            option = scan.nextInt();

            switch (option) {
                case 0:
                    //      ASSIGN ACCESS TO USER
                    System.out.println("ENTER USER_ID AND RESOURCE_AUTH_ID");
                    userId = scan.nextInt();
                    resourceAuthId = scan.nextInt();
                    userAccessLevelManager.assignAccess(userId, resourceAuthId);
                    System.out.println("UserAccessLevel: " + UserAccessLevelManagerImpl.getUserAccessLevel(userId) + "\n");
                    break;
                case 1:
                    //      REVOKE ACCESS TO USER
                    System.out.println("ENTER USER_ID AND RESOURCE_AUTH_ID");
                    userId = scan.nextInt();
                    resourceAuthId = scan.nextInt();
                    userAccessLevelManager.revokeAccess(userId, resourceAuthId);
                    System.out.println("UserAccessLevel: " + UserAccessLevelManagerImpl.getUserAccessLevel(userId) + "\n");
                    break;
                case 2:
                    //      CHECK WHETHER THE USER HAS ACCESS TO RESOURCE OR NOT
                    System.out.println("ENTER USER_ID, ACTION_TYPE AND RESOURCE_NAME");
                    userId = scan.nextInt();
                    int action = scan.nextInt();
                    ActionType actionType = ActionType.fromValue(action);
                    scan.nextLine();
                    String resource = scan.nextLine();
                    ResourceName resourceName = ResourceName.fromValue(resource);
                    boolean canAccess = userAccessLevelManager.canAccess(userId, actionType, resourceName);
                    System.out.println("Can user " + userId + " " + actionType + "? " + canAccess + "\n");
                    break;
            }
            t--;
        }
    }
}
