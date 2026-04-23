package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.*;
import org.junit.Assert;
import org.junit.Test;

public class RegistryTest {

    @Test
    public void shouldRejectDeadPerson() {
        Registry registry = new Registry();
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(dead);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldRejectUnderageAt17() {
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, 17, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

     @Test
    public void shouldRejectNegativeAge() {
        Registry registry = new Registry();
        Person person = new Person("Ana", 1, -5, Gender.FEMALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldRejectDuplicatedId() {
        Registry registry = new Registry();

        Person p1 = new Person("Ana", 1, 25, Gender.FEMALE, true);
        Person p2 = new Person("Ana", 1, 30, Gender.FEMALE, true); // mismo ID

        registry.registerVoter(p1);
        RegisterResult result = registry.registerVoter(p2);

        Assert.assertEquals(RegisterResult.DUPLICATED, result);
    }
}