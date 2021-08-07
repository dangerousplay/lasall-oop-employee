package edu.lasalle.oop.employee.repository;

import edu.lasalle.oop.employee.entity.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, BigInteger> {

    @Transactional
    @Modifying
    @Query("UPDATE EmployeeEntity as e SET e.salary = e.salary * (1.0 + ?2) WHERE e.department = ?1")
    int increaseSalary(String department, double percent);

}
