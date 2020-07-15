package com.flexon.restAPImysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "/Demo")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    //Get all customer
    @GetMapping(path="/get")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    //Add a new customer
    @PostMapping(path="/add")
    public ResponseEntity addNewCustomer(@RequestParam String firstName,
                                         @RequestParam String lastName,
                                         @RequestParam String email,
                                         @RequestParam long phoneNumber) {
        Customer c = new Customer();
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setEmail(email);
        c.setPhoneNumber(phoneNumber);
        customerRepository.save(c);

        return new ResponseEntity<>("Customer created",HttpStatus.OK);
    }

    //Delete a customer
    @DeleteMapping(path = "/delete/{accountID}")
    public ResponseEntity deleteCustomer(@PathVariable long accountID){
        List <Customer> c = customerRepository.findByID(accountID);
        if (c.isEmpty()){
            return new ResponseEntity<>("Customer not found",HttpStatus.OK);
        }
        customerRepository.deleteById(accountID);
        return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
    }

    //find a specific customer
    @GetMapping(path = "/find/{accountID}")
    public ResponseEntity findCustomer(@PathVariable long accountID){
        List <Customer> c = customerRepository.findByID(accountID);
        if (c.isEmpty()){
            return new ResponseEntity<>("Customer not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    @PutMapping(path = "update/{accountID}")
    public ResponseEntity updateCustomer( @PathVariable long accountID,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName,
                                          @RequestParam String email,
                                          @RequestParam long phoneNumber){

        List <Customer> customer = customerRepository.findByID(accountID);
        if (customer.isEmpty()){
            return new ResponseEntity<>("Customer not found", HttpStatus.OK);
        }
        Customer c = new Customer();
        c.setAccountID(accountID);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setEmail(email);
        c.setPhoneNumber(phoneNumber);
        customerRepository.save(c);
        return new ResponseEntity<>("Customer info updated", HttpStatus.OK);
    }
}
