package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EmployeeRepositoryImplTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    void save() {
        Employee employee = new Employee(
                "Kelum Srimal",
                "Librarian",
                "Colombo",
                "0771223456",
                "123456"
        );
        Employee savedEmployee = employeeRepository.save(employee);
        assertNotNull(savedEmployee);
//        assertEquals(savedBook.getId(), 1);
        assertEquals(savedEmployee.getName(), "Kelum Srimal");
        assertEquals(savedEmployee.getDesignation(), "Librarian");
        assertEquals(savedEmployee.getAddress(), "Colombo");
        assertEquals(savedEmployee.getContact(), "0771223456");
        assertEquals(savedEmployee.getPassword(), "123456");
    }
    @Test
    void update() {
        Employee employee = new Employee(
                "Kelum Srimal",
                "Librarian",
                "Colombo",
                "0771223456",
                "123456"
        );
        Employee savedEmployee = employeeRepository.save(employee);
        assertNotNull(savedEmployee);

        savedEmployee.setName("Jagath Fernando");
        savedEmployee.setDesignation("Assistant Librarian");
        savedEmployee.setAddress("Matara");
        savedEmployee.setContact("0717895147");
        savedEmployee.setPassword("1234587");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        Employee foundEmployee = em.find(Employee.class, updatedEmployee.getId());

//        assertEquals(updatedBook.getId(), 1);
        assertEquals(updatedEmployee.getName(), foundEmployee.getName());
        assertEquals(updatedEmployee.getDesignation(), foundEmployee.getDesignation());
        assertEquals(updatedEmployee.getAddress(), foundEmployee.getAddress());
        assertEquals(updatedEmployee.getContact(), foundEmployee.getContact());
        assertEquals(updatedEmployee.getPassword(), foundEmployee.getPassword());
    }
    @Test
    void deleteById() {
        Employee employee = new Employee(
                "Kelum Srimal",
                "Librarian",
                "Colombo",
                "0771223456",
                "123456"
        );
        Employee savedEmployee = employeeRepository.save(employee);
        assertNotNull(savedEmployee);
        Employee foundEmployee = em.find(Employee.class, savedEmployee.getId());
        assertNotNull(foundEmployee);
        assertEquals(savedEmployee.getId(), foundEmployee.getId());

        employeeRepository.delete(savedEmployee);
        Employee foundEmployee2 = em.find(Employee.class, savedEmployee.getId());
        assertNull(foundEmployee2);
    }
    @Test
    void findById() {
        Employee employee = new Employee(
                "Kelum Srimal",
                "Librarian",
                "Colombo",
                "0771223456",
                "123456"
        );
        Employee savedEmployee = employeeRepository.save(employee);
        assertNotNull(savedEmployee);
        Optional<Employee> optFoundEmployee = employeeRepository.findById(savedEmployee.getId());
        assertNotNull(optFoundEmployee.get());

        assertEquals(savedEmployee.getName(), optFoundEmployee.get().getName());
        assertEquals(savedEmployee.getDesignation(), optFoundEmployee.get().getDesignation());
        assertEquals(savedEmployee.getAddress(), optFoundEmployee.get().getAddress());
        assertEquals(savedEmployee.getContact(), optFoundEmployee.get().getContact());
        assertEquals(savedEmployee.getPassword(), optFoundEmployee.get().getPassword());
    }
    @Test
    void existById() {
        Employee employee = new Employee(
                "Kelum Srimal",
                "Librarian",
                "Colombo",
                "0771223456",
                "123456"
        );
        Employee savedEmployee = employeeRepository.save(employee);
        assertNotNull(savedEmployee);

        assertTrue(employeeRepository.existsById(savedEmployee.getId()));
        assertFalse(employeeRepository.existsById(1254));
    }
    @Test
    void findAll() {
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee(
                    "Kelum Srimal",
                    "Librarian",
                    "Colombo",
                    "0771223456",
                    "123456"
            );
            employeeRepository.save(employee);
        }
        assertTrue(employeeRepository.findAll().size() == 10);
    }
    @Test
    void count() {
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee(
                    "Kelum Srimal",
                    "Librarian",
                    "Colombo",
                    "0771223456",
                    "123456"
            );
            employeeRepository.save(employee);
        }
        assertTrue(employeeRepository.count() == 10);
    }
}