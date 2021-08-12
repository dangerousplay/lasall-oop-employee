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

    public static TechnicalEmployeeEntityBuilder builder() {
        return TechnicalEmployeeEntityBuilder.builder();
    }

    public static final class TechnicalEmployeeEntityBuilder extends EmployeeEntity.EmployeeEntityBuilder {

        private static TechnicalEmployeeEntityBuilder builder() {
            return new TechnicalEmployeeEntityBuilder();
        }

        @Override
        public EmployeeEntity build() {
            final var employeeEntity = new TechnicalEmployeeEntity();

            employeeEntity.setId(id);
            employeeEntity.setName(name);
            employeeEntity.setSalary(salary);
            employeeEntity.setDepartment(department);
            employeeEntity.setCompany(company);

            return employeeEntity;
        }
    }
}
