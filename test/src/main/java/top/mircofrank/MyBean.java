package top.mircofrank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Frank Local on 2017/9/15.
 */
@Component
public class MyBean {
    @Value("${mybean.p1}")
    public String p1;

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }
}
