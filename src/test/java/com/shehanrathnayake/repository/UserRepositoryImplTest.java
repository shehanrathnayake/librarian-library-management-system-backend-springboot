package com.shehanrathnayake.repository;

import com.shehanrathnayake.entity.User;
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
class UserRepositoryImplTest {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    void save() {
        User user = new User(
                "Kelum Srimal",
                "email@gmail.com",
                "123456",
                "Matara",
                "0711313951"
        );
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
//        assertEquals(savedBook.getId(), 1);
        assertEquals(savedUser.getName(), "Kelum Srimal");
        assertEquals(savedUser.getEmail(), "email@gmail.com");
        assertEquals(savedUser.getPassword(), "123456");
        assertEquals(savedUser.getAddress(), "Matara");
        assertEquals(savedUser.getContact(), "0711313951");
    }
    @Test
    void update() {
        User user = new User(
                "Kelum Srimal",
                "email@gmail.com",
                "123456",
                "Matara",
                "0711313951"
        );
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);

        savedUser.setName("Jagath Fernando");
        savedUser.setEmail("email2@gmail.com");
        savedUser.setPassword("123456789");
        savedUser.setAddress("Gampaha");
        savedUser.setContact("0715789632");
        User updatedUser = userRepository.save(savedUser);

        User foundUser = em.find(User.class, updatedUser.getId());

//        assertEquals(updatedBook.getId(), 1);
        assertEquals(updatedUser.getName(), foundUser.getName());
        assertEquals(updatedUser.getEmail(), foundUser.getEmail());
        assertEquals(updatedUser.getPassword(), foundUser.getPassword());
        assertEquals(updatedUser.getAddress(), foundUser.getAddress());
        assertEquals(updatedUser.getContact(), foundUser.getContact());
    }
    @Test
    void deleteById() {
        User user = new User(
                "Kelum Srimal",
                "email@gmail.com",
                "123456",
                "Matara",
                "0711313951"
        );
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);

        User foundUser = em.find(User.class, savedUser.getId());
        assertNotNull(foundUser);
        assertEquals(savedUser.getId(), foundUser.getId());

        userRepository.delete(savedUser);
        User foundUser2 = em.find(User.class, savedUser.getId());
        assertNull(foundUser2);
    }
    @Test
    void findById() {
        User user = new User(
                "Kelum Srimal",
                "email@gmail.com",
                "123456",
                "Matara",
                "0711313951"
        );
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);

        Optional<User> optFoundUser = userRepository.findById(savedUser.getId());
        assertNotNull(optFoundUser.get());

        assertEquals(savedUser.getName(), optFoundUser.get().getName());
        assertEquals(savedUser.getEmail(), optFoundUser.get().getEmail());
        assertEquals(savedUser.getPassword(), optFoundUser.get().getPassword());
        assertEquals(savedUser.getAddress(), optFoundUser.get().getAddress());
        assertEquals(savedUser.getContact(), optFoundUser.get().getContact());
    }
    @Test
    void existById() {
        User user = new User(
                "Kelum Srimal",
                "email@gmail.com",
                "123456",
                "Matara",
                "0711313951"
        );
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);

        assertTrue(userRepository.existsById(savedUser.getId()));
        assertFalse(userRepository.existsById(1254));
    }
    @Test
    void findAll() {
        for (int i = 0; i < 10; i++) {
            User user = new User(
                    "Kelum Srimal",
                    "email" + i + "@gmail.com",
                    "123456",
                    "Matara",
                    "0711313951"
            );
            userRepository.save(user);
        }
        assertTrue(userRepository.findAll().size() == 10);
    }
    @Test
    void count() {
        for (int i = 0; i < 10; i++) {
            User user = new User(
                    "Kelum Srimal",
                    "email" + i + "@gmail.com",
                    "123456",
                    "Matara",
                    "0711313951"
            );
            userRepository.save(user);
        }
        assertTrue(userRepository.count() == 10);
    }
}