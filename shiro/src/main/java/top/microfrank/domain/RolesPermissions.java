package top.microfrank.domain;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Frank Local on 2017/9/4.
 */
@Entity
public class RolesPermissions {
    @Id
    private int id;
    private String roleName;
    private String permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
