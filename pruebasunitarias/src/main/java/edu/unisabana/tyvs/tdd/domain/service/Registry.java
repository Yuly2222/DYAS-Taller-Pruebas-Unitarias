package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private static final int MIN_AGE = 18;

    public RegisterResult registerVoter(Person p) {
        if (p == null) {
            return RegisterResult.INVALID;
        }

        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }

        if (p.getAge() < MIN_AGE) {
            return RegisterResult.UNDERAGE;
        }

        return RegisterResult.VALID;
    }
}