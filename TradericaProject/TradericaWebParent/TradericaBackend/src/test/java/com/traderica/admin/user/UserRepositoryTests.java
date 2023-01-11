package com.traderica.admin.user;

import com.traderica.common.entity.Role;
import com.traderica.common.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRole(){
        Role roleAdmin = entityManager.find(Role.class, 1);
        User userAj = new User("ajnasbinali@gmail.com", "0088","Ajnas","Bin Ali");
        userAj.addRole(roleAdmin);

        User saveUser = repo.save(userAj);
        assertThat(saveUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateUserWithTwoRole(){
        User userAm = new User("mehvi@gmail.com","1234","Ayisha","Mehvin");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userAm.addRole(roleEditor);
        userAm.addRole(roleAssistant);

        User savedUser = repo.save(userAm);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers(){
        Iterable<User> listusers = repo.findAll();
        listusers.forEach(user -> System.out.println(user));


    }

    @Test
    public void testGetUserById(){
        User userAj = repo.findById(1).get();
        System.out.println(userAj);
        assertThat(userAj).isNotNull();

    }

    @Test
    public void testUpdateUserDetails(){
        User userAj = repo.findById(4).get();
        userAj.setEnabled(false);
        repo.save(userAj);

    }

    @Test
    public void testGetUserByEmail(){
        String email = "ajnasi@gmail.com";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testCountCyId(){
        Integer id =2;
        Long countById = repo.countById(id);
        assertThat(countById).isNotNull().isGreaterThan(0);
    }

    @Test
    public void testDisableUser(){
        Integer id = 15;
        repo.updateEnabledStatues(id,false);

    }
}
