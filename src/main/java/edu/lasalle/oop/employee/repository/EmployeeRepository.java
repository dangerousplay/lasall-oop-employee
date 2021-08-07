package edu.lasalle.oop.employee.repository;

import edu.lasalle.oop.employee.entity.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, BigInteger> {

    @Query("UPDATE EmployeeEntity as e SET e.salary = e.salary * (1 + ?2) WHERE e.department = ?1")
    int increaseSalary(String department, BigDecimal percent);

}
