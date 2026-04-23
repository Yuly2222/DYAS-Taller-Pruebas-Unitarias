package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Gender;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;
import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {

    @Test
    public void shouldRejectNullPerson() {
        Registry registry = new Registry();

        RegisterResult result = registry.registerVoter(null);

        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldRejectDeadPerson() {
        Registry registry = new Registry();
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);

        RegisterResult result = registry.registerVoter(dead);

        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldRejectNegativeAge() {
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, -5, Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectAgeOver120() {
        Registry registry = new Registry();
        Person person = new Person("Pedro", 3, 121, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectUnderageAt17() {
        Registry registry = new Registry();
        Person person = new Person("Laura", 4, 17, Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldAllowAdultAt18() {
        Registry registry = new Registry();
        Person person = new Person("Luis", 5, 18, Gender.MALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldAllowValidAge120() {
        Registry registry = new Registry();
        Person person = new Person("Marta", 6, 120, Gender.FEMALE, true);

        RegisterResult result = registry.registerVoter(person);

        Assert.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldRejectDuplicatedId() {
        Registry registry = new Registry();

        Person first = new Person("Ana", 7, 25, Gender.FEMALE, true);
        Person duplicate = new Person("Maria", 7, 30, Gender.FEMALE, true);

        registry.registerVoter(first);
        RegisterResult result = registry.registerVoter(duplicate);

        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }

    @Test
    public void shouldNotStoreIdWhenAgeIsInvalid() {
        Registry registry = new Registry();

        Person invalid = new Person("Ana", 8, -1, Gender.FEMALE, true);
        Person validLater = new Person("Ana", 8, 25, Gender.FEMALE, true);

        registry.registerVoter(invalid);
        RegisterResult result = registry.registerVoter(validLater);

        Assert.assertEquals(RegisterResult.VALID, result);
    }
}