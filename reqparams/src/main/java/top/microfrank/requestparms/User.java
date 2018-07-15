package top.microfrank.requestparms;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Frank on 2018/7/15.
 */
public class User {
    @NotNull(message="名字不能为空")
    String username;
    @NotNull(message="密码不能为空")
    @Pattern(regexp = "\\d+")
    String password;
    @Min(45)
    int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
