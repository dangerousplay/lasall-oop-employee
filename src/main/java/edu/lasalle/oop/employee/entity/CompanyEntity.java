package edu.lasalle.oop.employee.entity;

import edu.lasalle.oop.employee.entity.employee.EmployeeEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;



@Entity
@Table(name = "COMPANY")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    private String name;

    @Column(unique = true)
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    private List<EmployeeEntity> employees;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

    public static CompanyEntityBuilder builder() {
        return CompanyEntityBuilder.builder();
    }

    public static final class CompanyEntityBuilder {
        private BigInteger id;
        private String name;
        private String cnpj;
        private List<EmployeeEntity> employees;

        private CompanyEntityBuilder() {
        }

        private static CompanyEntityBuilder builder() {
            return new CompanyEntityBuilder();
        }

        public CompanyEntityBuilder withId(BigInteger id) {
            this.id = id;
            return this;
        }

        public CompanyEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CompanyEntityBuilder withCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public CompanyEntityBuilder withEmployees(List<EmployeeEntity> employees) {
            this.employees = employees;
            return this;
        }

        public CompanyEntity build() {
            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setId(id);
            companyEntity.setName(name);
            companyEntity.setCnpj(cnpj);
            companyEntity.setEmployees(employees);
            return companyEntity;
        }
    }
}
