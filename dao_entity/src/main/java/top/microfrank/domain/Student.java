package top.microfrank.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by Frank Local on 2017/9/3.
 */
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Student {
    @Id
    private int sid;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cid")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cid")
    @JsonIdentityReference(alwaysAsId = true)
    private Claz claz;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Claz getClaz() {
        return claz;
    }

    public void setClaz(Claz claz) {
        this.claz = claz;
    }
}
