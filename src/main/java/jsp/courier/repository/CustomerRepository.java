package jsp.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courier.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}