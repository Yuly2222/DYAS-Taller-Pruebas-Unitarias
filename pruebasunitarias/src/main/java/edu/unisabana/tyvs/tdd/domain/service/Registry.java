package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;

    public RegisterResult registerVoter(Person p) {
        if (p == null) return RegisterResult.INVALID;
        if (!p.isAlive()) return RegisterResult.DEAD;

        return validateAge(p.getAge());
    }

    private RegisterResult validateAge(int age) {
        if (age < 0) return RegisterResult.INVALID_AGE;
        if (age > MAX_AGE) return RegisterResult.INVALID_AGE;
        if (age < MIN_AGE) return RegisterResult.UNDERAGE;
        return RegisterResult.VALID;
    }
}