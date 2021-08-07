package edu.lasalle.oop.employee.entity.employee;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("4")
public class TechnicalEmployeeEntity extends EmployeeEntity {

    @Override
    public String getRole() {
        return "Technical";
    }
}
