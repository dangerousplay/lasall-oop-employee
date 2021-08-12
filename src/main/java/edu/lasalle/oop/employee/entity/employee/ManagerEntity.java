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

    public static ManagerEntityBuilder builder() {
        return ManagerEntityBuilder.builder();
    }

    public static final class ManagerEntityBuilder extends EmployeeEntity.EmployeeEntityBuilder {

        private static ManagerEntityBuilder builder() {
            return new ManagerEntityBuilder();
        }

        @Override
        public EmployeeEntity build() {
            final var employeeEntity = new ManagerEntity();

            employeeEntity.setId(id);
            employeeEntity.setName(name);
            employeeEntity.setSalary(salary);
            employeeEntity.setDepartment(department);
            employeeEntity.setCompany(company);

            return employeeEntity;
        }
    }
}
