package edu.lasalle.oop.employee.ui;


import edu.lasalle.oop.employee.entity.CompanyEntity;
import edu.lasalle.oop.employee.entity.employee.AnalystEmployeeEntity;
import edu.lasalle.oop.employee.entity.employee.EmployeeEntity;
import edu.lasalle.oop.employee.entity.employee.ManagerEntity;
import edu.lasalle.oop.employee.entity.employee.TechnicalEmployeeEntity;
import edu.lasalle.oop.employee.service.CompanyService;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

@Component
public class Terminal {

    private static final Logger LOGGER = LoggerFactory.getLogger(Terminal.class);
    private final CompanyService companyService;

    public Terminal(CompanyService companyService) {
        this.companyService = companyService;
    }

    private void exerciseOne(final Scanner scanner) {
        LOGGER.info("Nome da empresa: ");
        final var name = scanner.nextLine();

        LOGGER.info("CNPJ: ");
        final var cnpj = scanner.nextLine();

        final var company = this.companyService.createCompany(name, cnpj);

        LOGGER.info("Empresa criada");

        LOGGER.info("Adicionar quantos funcionários?");
        final var amount = scanner.nextInt();

        final var department = RandomString.make(10);

        final var random = new Random();

        LOGGER.info("Funcionários criados: ");

        createEmployees(cnpj, company, department, random, amount, EmployeeEntity.builder());


        LOGGER.info("Salário aumentado em 10% no departamento");

        this.companyService.increaseSalary(department, new BigDecimal("0.10"));

        this.companyService.listEmployees().forEach(this::printEmployee);

    }

    private void exerciseTwo(final Scanner scanner) {
        LOGGER.info("Nome da empresa: ");
        final var name = scanner.nextLine();

        LOGGER.info("CNPJ: ");
        final var cnpj = scanner.nextLine();

        final var company = this.companyService.createCompany(name, cnpj);
        final var department = RandomString.make(10);
        final var random = new Random();

        LOGGER.info("Empresa criada");

        LOGGER.info("Quantos analistas? ");
        createEmployees(cnpj, company, department, random, scanner.nextInt(), AnalystEmployeeEntity.builder());

        LOGGER.info("Quantos técnicos? ");
        createEmployees(cnpj, company, department, random, scanner.nextInt(), TechnicalEmployeeEntity.builder());

        LOGGER.info("Quantos gerentes? ");
        createEmployees(cnpj, company, department, random, scanner.nextInt(), ManagerEntity.builder());

        LOGGER.info("Salários dos gerentes aumentados em 15%");
        this.companyService.increaseManagerSalary(new BigDecimal("0.15"));

        this.companyService.listEmployees().forEach(this::printEmployee);

    }

    private void createEmployees(String cnpj,
                                 CompanyEntity company,
                                 String department,
                                 Random random,
                                 int amount,
                                 EmployeeEntity.EmployeeEntityBuilder employeeEntityBuilder) {

        IntStream.range(0, amount)
                .mapToObj(i -> employeeEntityBuilder
                        .withCompany(company)
                        .withName(RandomString.make(10))
                        .withDepartment(department)
                        .withSalary(BigDecimal.valueOf(random.nextDouble() * 10000))
                        .build()
                ).map(e -> this.companyService.admitEmployee(e, cnpj))
                .forEach(this::printEmployee);
    }

    private void printEmployee(EmployeeEntity e) {
        LOGGER.info("---------------------");
        LOGGER.info("Nome: {} ", e.getName());
        LOGGER.info("Salário: {}", e.getSalary());
        LOGGER.info("Departamento: {}", e.getDepartment());
        LOGGER.info("Role: {}", e.getRole());
        LOGGER.info("---------------------");
    }

    @PostConstruct
    public void run() {

        var scanner = new Scanner(System.in);

        LOGGER.info("Selecione o exercício: ");
        LOGGER.info("1 ou 2");

        var ex = scanner.nextInt();

        switch (ex) {
            case 1:
                this.exerciseOne(scanner);
                break;
            case 2:
                this.exerciseTwo(scanner);
                break;
            default:
                LOGGER.warn("Exercício não reconhecido");
        }
    }

}
