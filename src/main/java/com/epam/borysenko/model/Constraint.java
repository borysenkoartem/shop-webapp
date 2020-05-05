package com.epam.borysenko.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "constraint")
@XmlAccessorType(XmlAccessType.FIELD)
public class Constraint {

    public Constraint() {
    }

    @XmlElement(name = "url-pattern")
    private String urlPattern;
    @XmlElement(name = "role")
    private List<String> role;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }


    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
