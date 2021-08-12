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

    public static AnalystEmployeeEntityBuilder builder() {
        return AnalystEmployeeEntityBuilder.builder();
    }

    public static final class AnalystEmployeeEntityBuilder extends EmployeeEntity.EmployeeEntityBuilder {

        private static AnalystEmployeeEntityBuilder builder() {
            return new AnalystEmployeeEntityBuilder();
        }

        @Override
        public EmployeeEntity build() {
            final var employeeEntity = new AnalystEmployeeEntity();

            employeeEntity.setId(id);
            employeeEntity.setName(name);
            employeeEntity.setSalary(salary);
            employeeEntity.setDepartment(department);
            employeeEntity.setCompany(company);

            return employeeEntity;
        }
    }
}
