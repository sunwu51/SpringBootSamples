package top.microfrank.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Frank Local on 2017/9/9.
 */
@Entity
public class Authorities {
    @Id
    private int id;
    private String username;
    private String authority;

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
