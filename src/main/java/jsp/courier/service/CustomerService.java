package jsp.courier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courier.entity.Customer;
import jsp.courier.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<String> saveCustomer(Customer customer) {
         customerRepository.save(customer);
         return  new ResponseEntity<> ("data save",HttpStatus.CREATED);
    }

    
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
    }
    
//    Delete Data Using Id
    private CustomerRepository customerRepository1;

    public ResponseEntity<String> deleteCustomer(Integer id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }
    
//    Update Data using Id
    
    private CustomerRepository customerRepository2;

    public ResponseEntity<Customer> updateCustomer(Integer id, Customer customer) {

        Optional<Customer> opt = customerRepository.findById(id);

        if (opt.isPresent()) {

            Customer existingCustomer = opt.get();

            // Update fields
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNo(customer.getPhoneNo());

            Customer updatedCustomer = customerRepository.save(existingCustomer);

            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    
    
 }
