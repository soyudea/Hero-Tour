package co.com.udea.tourofhero.persistence.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "HERO")
public class HeroEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String code;

    private Integer id;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
