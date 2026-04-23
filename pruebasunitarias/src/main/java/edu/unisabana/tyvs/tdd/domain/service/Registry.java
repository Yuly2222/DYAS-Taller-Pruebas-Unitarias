package edu.unisabana.tyvs.domain.service;

import java.util.HashSet;
import java.util.Set;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 120;

    private Set<Integer> registeredIds = new HashSet<>();

    public RegisterResult registerVoter(Person p) {
        if (p == null) return RegisterResult.INVALID;
        if (!p.isAlive()) return RegisterResult.DEAD;
        if (isDuplicated(p)) return RegisterResult.DUPLICATED;

        RegisterResult ageValidation = validateAge(p.getAge());
        if (ageValidation != RegisterResult.VALID) {
            return ageValidation;
        }

        registeredIds.add(p.getId());
        return RegisterResult.VALID;
    }

    private RegisterResult validateAge(int age) {
        if (age < 0) return RegisterResult.INVALID_AGE;
        if (age > MAX_AGE) return RegisterResult.INVALID_AGE;
        if (age < MIN_AGE) return RegisterResult.UNDERAGE;
        return RegisterResult.VALID;
    }

    private boolean isDuplicated(Person p) {
        return registeredIds.contains(p.getId());
    }
}