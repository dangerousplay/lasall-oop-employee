package edu.lasalle.oop.employee.repository;


import edu.lasalle.oop.employee.entity.CompanyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, BigInteger> {

    CompanyEntity findByCnpj(String cnpj);

}
