import enums.ActionType;
import enums.ResourceName;
import managerImpl.*;
import managers.UserAccessLevelManager;

import java.util.Scanner;

/**
 * @author priyanka_s
 *         Part of UserAuthSystem
 *         on 18/10/17.
 */
public class UserAuthSystem {
    public static void main(String[] args) throws Exception {

        UserAccessLevelManager userAccessLevelManager = UserAccessLevelManagerImpl.getInstance();

        /**
         * POPULATE:
         * - USER,
         * - ROLE
         * - ACCESS_LEVEL DATA
         * - RESOURCE_ACCESS DATA
         * */

        Scanner scan = new Scanner(System.in);
        int userId, resourceAccessId;
        int option;
        System.out.println("ENTER NO. OF TIMES YOU WANT TO PERFORM ACTION");
        int t = scan.nextInt();

        while (t>0) {
            System.out.println("ENTER OPTION 0-ASSIGN ACCESS, 1-REVOKE ACCESS, 2-CHECK ACCESS");

            option = scan.nextInt();

            switch (option) {
                case 0:
                    //      ASSIGN ACCESS TO USER
                    System.out.println("ENTER USER_ID AND RESOURCE_ACCESS_ID");
                    userId = scan.nextInt();
                    resourceAccessId = scan.nextInt();
                    userAccessLevelManager.assignAccess(userId, resourceAccessId);
                    System.out.println("UserAccessLevel for userId: " + userId + " is: " + userAccessLevelManager.getUserAccessLevelList().get(userId) + "\n");
                    break;
                case 1:
                    //      REVOKE ACCESS TO USER
                    System.out.println("ENTER USER_ID AND RESOURCE_ACCESS_ID");
                    userId = scan.nextInt();
                    resourceAccessId = scan.nextInt();
                    userAccessLevelManager.revokeAccess(userId, resourceAccessId);
                    System.out.println("UserAccessLevel for userId: " + userId + " is: " + userAccessLevelManager.getUserAccessLevelList().get(userId) + "\n");
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
            System.out.println("CurrentUserAccessLevelList: " + userAccessLevelManager.getUserAccessLevelList() + "\n");

            t--;
        }
    }
}
