package top.microfrank.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Frank Local on 2017/9/4.
 */
@Entity
public class UserRoles {
    @Id
    private int id;
    private String username;
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
