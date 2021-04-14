package com.tutorial.math.arithmetic.core;

import com.tutorial.math.MathX;
import com.tutorial.math.arithmetic.AbstractArithmetic;

/**
 * class which handles {@link Long} arithmetic
 *
 * @since 1.0.0
 */
/* This is a singleton class for performing operations on Long Variables */

public class LongArithmetic extends AbstractArithmetic<Long> {
    // region singleton

    private static LongArithmetic instance;

    /**
     * @return default instance
     * @since 1.0.0
     */
    public static LongArithmetic getInstance() {
        if (instance == null) instance = new LongArithmetic();
        return instance;
    }

    // endregion

    // region fromInt, fromDouble and signum

    @Override
    public Long fromInt(int a) {
        return (long) a;
    }

    @Override
    public Long fromDouble(double a) {
        return (long) a;
    }

    @Override
    public double signum(Long a) {
        return Math.signum(a);
    }

    // endregion

    // region sum, difference, product, quotient and modulo

    @Override
    public Long sum(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long difference(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long product(Long a, Long b) {
        return a * b;
    }

    @Override
    public Long quotient(Long a, Long b) {
        return a / b;
    }

    @Override
    public Long modulo(Long a, Long b) {
        return a % b;
    }

    // endregion

    // region power and root

    @Override
    public Long power(Long a, int b) {
        return (long) Math.pow(a, b);
    }

    @Override
    public Long root(Long a, int b) {
        return (long) MathX.root(a, b);
    }

    // endregion

    // region gcd

    @Override
    public Long gcd(Long a, Long b) {
        return MathX.gcd(a, b);
    }

    // endregion
}
