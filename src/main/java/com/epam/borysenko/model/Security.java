package com.epam.borysenko.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "security")
@XmlAccessorType(XmlAccessType.FIELD)
public class Security {

    public Security() {
    }

    @XmlElement(name = "constraint")
    private List<Constraint> constraintList;

    public List<Constraint> getConstraintList() {
        return constraintList;
    }

    public void setConstraintList(List<Constraint> constraintList) {
        this.constraintList = constraintList;
    }

    public Security(List<Constraint> constraintList) {
        this.constraintList = constraintList;
    }
}
