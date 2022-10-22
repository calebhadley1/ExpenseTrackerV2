package com.hadleynet.ExpenseTrackerV2.repository;

import com.hadleynet.ExpenseTrackerV2.model.AppUser;
import com.hadleynet.ExpenseTrackerV2.model.AppUserRole;
import com.hadleynet.ExpenseTrackerV2.model.Expense;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AppUserRepositoryIntegrationTests {
    AppUser appUser1;
    AppUser appUser2;
    List<AppUser> appUsers;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        appUser1 = new AppUser("T", "Scott", "tscott@gmail.com", "password", AppUserRole.USER);
        appUser2 = new AppUser("A", "LName", "123@gmail.com", "password", AppUserRole.USER);

        appUsers = Arrays.asList(appUser1, appUser2);

    }

    /*
    Positive Test Cases
     */

    @Test
    public void testFindAllExpenses() {
        entityManager.persist(appUser1);
        entityManager.persist(appUser2);

        List<AppUser> result = appUserRepository.findAll();
        assertThat(result).hasSize(2).containsAll(appUsers);
    }

    @Test
    void testFindByEmail() {
        AppUser expected = appUser1;
        entityManager.persist(appUser1);

        AppUser result = appUserRepository.findByEmail(appUser1.getUsername()).orElseThrow();
        assertEquals(expected, result);
    }

    @Test
    void testFindById() {
        AppUser expected = appUser1;
        entityManager.persist(appUser1);

        AppUser result = appUserRepository.findById(appUser1.getId()).get();
        assertEquals(expected, result);
    }

    @Test
    void testUpdateById() {
        entityManager.persist(appUser1);
        AppUser appUser = new AppUser("New Fname", "New LName", "email@gmail.com", "pw", AppUserRole.ADMIN);

        AppUser savedAppUser = appUserRepository.findById(appUser1.getId()).get();
        savedAppUser.setFirstName(appUser.getFirstName());
        savedAppUser.setLastName(appUser.getLastName());
        savedAppUser.setEmail(appUser.getUsername());
        savedAppUser.setPassword(appUser.getPassword());
        savedAppUser.setAppUserRole(appUser.getAppUserRole());
        appUserRepository.save(savedAppUser);

        AppUser result = appUserRepository.findById(appUser1.getId()).get();

        assertThat(result.getFirstName()).isEqualTo(appUser.getFirstName());
        assertThat(result.getLastName()).isEqualTo(appUser.getLastName());
        assertThat(result.getUsername()).isEqualTo(appUser.getUsername());
        assertThat(result.getPassword()).isEqualTo(appUser.getPassword());
        assertThat(result.getAppUserRole()).isEqualTo(appUser.getAppUserRole());
    }

    @Test
    void testDeleteById() {
        entityManager.persist(appUser1);
        entityManager.persist(appUser2);

        appUserRepository.deleteById(appUser1.getId());
        List<AppUser> result = appUserRepository.findAll();
        assertThat(result).hasSize(1).contains(appUser2);
    }

    @Test
    void testDeleteAll() {
        entityManager.persist(appUser1);
        entityManager.persist(appUser2);

        appUserRepository.deleteAll();
        List<AppUser> result = appUserRepository.findAll();
        assertThat(result).hasSize(0);
    }


    /*
    Negative Test Cases
     */
    @Test
    public void testFindAllExpenses_EmptyDb() {
        List<AppUser> appUsers = appUserRepository.findAll();

        assertThat(appUsers).isEmpty();
    }
}
