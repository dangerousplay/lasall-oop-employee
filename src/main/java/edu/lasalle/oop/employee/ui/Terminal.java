package edu.lasalle.oop.employee.ui;


import edu.lasalle.oop.employee.entity.employee.EmployeeEntity;
import edu.lasalle.oop.employee.service.CompanyService;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
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

        IntStream.range(0, amount)
                .mapToObj(i -> EmployeeEntity.builder()
                        .withCompany(company)
                        .withName(RandomString.make(10))
                        .withDepartment(department)
                        .withSalary(BigDecimal.valueOf(random.nextDouble() * 10000))
                        .build()
                ).map(e -> this.companyService.admitEmployee(e, cnpj))
                .forEach(this::printEmployee);


        LOGGER.info("Salário aumentado em 10% no departamento");

        this.companyService.increaseSalary(department, new BigDecimal("0.10"));

        this.companyService.listEmployees().forEach(this::printEmployee);

    }

    private void printEmployee(EmployeeEntity e) {
        LOGGER.info("---------------------");
        LOGGER.info("Nome: {} ", e.getName());
        LOGGER.info("Salário: {}", e.getSalary());
        LOGGER.info("Departamento: {}", e.getDepartment());
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
                break;
            default:
                LOGGER.warn("Exercício não reconhecido");
        }
    }

}
