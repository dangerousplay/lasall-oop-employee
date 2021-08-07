package edu.lasalle.oop.employee.service;


import edu.lasalle.oop.employee.entity.CompanyEntity;
import edu.lasalle.oop.employee.entity.employee.EmployeeEntity;
import edu.lasalle.oop.employee.repository.CompanyRepository;
import edu.lasalle.oop.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CompanyService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public CompanyService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public CompanyEntity createCompany(final String name, final String cpnj) {
        return this.companyRepository.save(CompanyEntity.builder().withCnpj(cpnj).withName(name).build());
    }

    public CompanyEntity findCompany(final String cnpj) {
        return this.companyRepository.findByCnpj(cnpj);
    }

    public Iterable<CompanyEntity> listCompanies() {
        return this.companyRepository.findAll();
    }

    public Iterable<EmployeeEntity> listEmployees() {
        return this.employeeRepository.findAll();
    }

    public EmployeeEntity admitEmployee(EmployeeEntity employee, String cnpj) {
        employee.setCompany(this.companyRepository.findByCnpj(cnpj));

        return this.employeeRepository.save(employee);
    }

    public void increaseSalary(String department, BigDecimal bigDecimal) {
        this.employeeRepository.increaseSalary(department, bigDecimal.doubleValue());
    }
}
