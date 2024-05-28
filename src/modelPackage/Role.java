package modelPackage;

import exceptionPackage.RoleIDValueException;
import exceptionPackage.RoleIDNullException;
import exceptionPackage.RoleNameLengthException;
import exceptionPackage.RoleNameNullException;

import java.util.HashSet;

public class Role {
    private Integer roleId;
    private String roleName;
    private HashSet<UserAccount> userAccounts;

    public Role(Integer roleId, String roleName) throws RoleIDValueException, RoleNameLengthException, RoleIDNullException, RoleNameNullException {
        setRoleId(roleId);
        setRoleName(roleName);
        this.userAccounts = new HashSet<>();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleId(Integer roleId) throws RoleIDValueException, RoleIDNullException {
        if (roleId == null || roleId < 0) {
            if (roleId == null) {
                throw new RoleIDNullException("ID can't be null.");
            }
            throw new RoleIDValueException("ID must be positive");
        }
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) throws RoleNameLengthException, RoleNameNullException {
        if (roleName == null || roleName.length() > 32) {
            if (roleName == null) {
                throw new RoleNameNullException("The name can't be null");
            }
            throw new RoleNameLengthException("The size of Name exceed the allocated size in the database");
        }
        this.roleName = roleName;
    }

    public HashSet<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void addUser(UserAccount userAccount) {
        if (userAccount != null && !userAccounts.contains(userAccount)) {
            userAccounts.add(userAccount);
            if (!userAccount.getRoles().contains(this)) {
                userAccount.addRole(this);
            }
        }
    }

    public void removeUser(UserAccount userAccount) {
        if (userAccount != null && userAccounts.contains(userAccount)) {
            userAccounts.remove(userAccount);
            if (userAccount.getRoles().contains(this)) {
                userAccount.removeRole(this);
            }
        }
    }

}
