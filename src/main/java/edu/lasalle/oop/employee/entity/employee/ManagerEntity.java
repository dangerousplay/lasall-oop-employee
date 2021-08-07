package edu.lasalle.oop.employee.entity.employee;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("2")
public class ManagerEntity extends EmployeeEntity {

    @Override
    public String getRole() {
        return "Manager";
    }
}
