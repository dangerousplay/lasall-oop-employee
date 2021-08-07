package edu.lasalle.oop.employee.entity.employee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("3")
public class AnalystEmployeeEntity extends EmployeeEntity {

    @Override
    public String getRole() {
        return "Analyst";
    }
}
