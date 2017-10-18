# UserRoleBasedAuth
A role based access control authentication system

## Getting started

Dependencies:
Java >= 1.8.0

Required:
junit - 4.12

## Getting started
- Clone repository
- Complie and run the code
- Provide option to Assign/Revoke/check access
- Singleton design pattern is used

## Static data filled:
User
- id | name | email

AccessLevel
- id | actionType
- actionType: READ, WRITE, DELETE

Role
- id | name
- name: MANAGER, SENIOR_MANAGER, ADMIN, SUPER_ADMIN

ResourceAuthorization
- It creates resource authorization with a specific role and define access level
- id | resourceName | roleSet | accessLevel
- resourceName: BOOKING, PAYMENT, PRICING, REFUND
- Eg: (1, "BOOKING", Role(MANAGER), AccessLevel(WRITE))

UserAccessLevel
- userId | Set<Integer> resourceAuthIdSet;
- UserAccessLevel List is be modified on allowing/deleting access
- Resource Auth access given to user is being stored in userAccessLevel list

Example:
- ENTER NO. OF TIMES YOU WANT TO SCAN ACTION
4

- ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)
0

- ENTER USER_ID AND RESOURCE_AUTH_ID
1 1
UserAccessLevel: UserAccessLevel{userId=1, resourceAuthIdSet=[1]}

- ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)
1 2
UserAccessLevel: UserAccessLevel{userId=1, resourceAuthIdSet=[1, 2]}

- ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)
1

- ENTER USER_ID AND RESOURCE_AUTH_ID
1 1
UserAccessLevel: UserAccessLevel{userId=1, resourceAuthIdSet=[2]}

- ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)
2
- ENTER USER_ID, ACTION_TYPE AND RESOURCE_NAME
1 1
BOOKING
Can user 1 WRITE? true

### NOTE/Assumptions:
- No database is being used. It is a simple java application that is implemented in modular way to extend/modify any funtionality easily
- Static data is being filled on system start.
- Please check populate method of PopulateManager for finding/updating static filled data
- No separate resources data/class is being implemented. Code is implement under the assumption that BOOKING/PRICING/PAYMENT resources exist in system. 
- User can have multiple roles as well as aceess levels
- Code is implemented in modular way to easily add/remove/update AccessLevel(ActionType), Role, User, ResourceAuthorization with exception handling
- No duplicate data will be added to the UserAuthSystem
- Proper validations are present while assigning/revoking/checking access. Basic validations (Min/Max/Length) of POJO has been ignored.
- Some of the methods are not implemented and are added as TODO
- Test cases are written using junit
- Priority has been set in accessLevels
READ(0),
WRITE(1),
DELETE(2)
Eg: If a user having Booking resource with WRITE access should also have Booking resource with WRITE access
- ALLOW_ALL(Resource) resourceAuth entry if added to any user can access any resource with that particular accessLevel

ENTER NO. OF TIMES YOU WANT TO PERFORM ACTION
10

ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)
0
ENTER USER_ID AND RESOURCE_AUTH_ID
1 6
UserAccessLevel: UserAccessLevel{userId=1, resourceAuthIdSet=[6]}

CurrentUserAccessLevelList: [UserAccessLevel{userId=1, resourceAuthIdSet=[6]}]

ENTER OPTION 0(ASSIGN ACCESS), 1(REVOKE ACCESS), 2(CHECK ACCESS)
2
ENTER USER_ID, ACTION_TYPE AND RESOURCE_NAME
1 2
PRICING
Can user 1 DELETE? true

CurrentUserAccessLevelList: [UserAccessLevel{userId=1, resourceAuthIdSet=[6]}]
