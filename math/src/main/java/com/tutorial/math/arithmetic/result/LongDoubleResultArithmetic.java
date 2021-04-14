package com.tutorial.math.arithmetic.result;

import io.rala.math.arithmetic.AbstractResultArithmetic;
import io.rala.math.arithmetic.core.DoubleArithmetic;
import io.rala.math.arithmetic.core.LongArithmetic;

/**
 * class which defines required arithmetic for calculations
 *class which defines required arithmetic for calculations
 * which calculates {@link Long} to {@link Double}
 *
 * @since 1.0.0
 */
public class LongDoubleResultArithmetic extends AbstractResultArithmetic<Long, Double> {
    // region singleton

    private static LongDoubleResultArithmetic instance;

    /**
     * @return default instance
     * @since 1.0.0
     */
    public static LongDoubleResultArithmetic getInstance() {
        if (instance == null) instance = new LongDoubleResultArithmetic();
        return instance;
    }

    // endregion

    /**
     * creates a new {@link AbstractResultArithmetic} with
     * {@link LongArithmetic} and {@link DoubleArithmetic}
     *
     * @since 1.0.0
     */
    public LongDoubleResultArithmetic() {
        super(LongArithmetic.getInstance(), DoubleArithmetic.getInstance());
    }

    @Override
    public Double fromT(Long a) {
        return (double) a;
    }
}
