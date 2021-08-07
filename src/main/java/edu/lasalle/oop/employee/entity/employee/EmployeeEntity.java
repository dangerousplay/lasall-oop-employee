package edu.lasalle.oop.employee.entity.employee;


import edu.lasalle.oop.employee.entity.CompanyEntity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.BigInteger;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="EMPLOYEE_TYPE",
        discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    private String name;
    private BigDecimal salary;
    private String department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cnpj")
    private CompanyEntity company;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getRole() {
        return "Employee";
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", company=" + company +
                '}';
    }

    public static EmployeeEntityBuilder builder() {
        return EmployeeEntityBuilder.builder();
    }

    public static final class EmployeeEntityBuilder {
        private BigInteger id;
        private String name;
        private BigDecimal salary;
        private String department;
        private CompanyEntity company;

        private EmployeeEntityBuilder() {
        }

        private static EmployeeEntityBuilder builder() {
            return new EmployeeEntityBuilder();
        }

        public EmployeeEntityBuilder withId(BigInteger id) {
            this.id = id;
            return this;
        }

        public EmployeeEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeEntityBuilder withSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeEntityBuilder withDepartment(String department) {
            this.department = department;
            return this;
        }

        public EmployeeEntityBuilder withCompany(CompanyEntity company) {
            this.company = company;
            return this;
        }

        public EmployeeEntity build() {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setId(id);
            employeeEntity.setName(name);
            employeeEntity.setSalary(salary);
            employeeEntity.setDepartment(department);
            employeeEntity.setCompany(company);
            return employeeEntity;
        }
    }
}
