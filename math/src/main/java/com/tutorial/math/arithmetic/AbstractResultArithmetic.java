package com.tutorial.math.arithmetic;

import com.tutorial.math.exception.NotSupportedException;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

/**
 * class which defines required arithmetic for calculations
 * class which defines required arithmetic for calculations
 * @param <T> number class of arithmetic input
 * @param <R> number class of arithmetic result
 * @since 1.0.0
 */
public abstract class AbstractResultArithmetic<T extends Number, R extends Number> implements Serializable {

    private final AbstractArithmetic<T> tArithmetic;
    private final AbstractArithmetic<R> rArithmetic;

    /**
     * creates a new {@link AbstractResultArithmetic} with given two arithmetic
     *
     * @param tArithmetic t arithmetic
     * @param rArithmetic r arithmetic
     * @since 1.0.0
     */
    public AbstractResultArithmetic(
        AbstractArithmetic<T> tArithmetic, AbstractArithmetic<R> rArithmetic
    ) {
        this.tArithmetic = tArithmetic;
        this.rArithmetic = rArithmetic;
    }

    /**
     * @return stored t {@link AbstractArithmetic}
     * @since 1.0.0
     */
    public AbstractArithmetic<T> getTArithmetic() {
        return tArithmetic;
    }

    /**
     * @return stored r {@link AbstractArithmetic}
     * @since 1.0.0
     */
    public AbstractArithmetic<R> getRArithmetic() {
        return rArithmetic;
    }

    /**
     * @param a value to convert
     * @return value as {@code R}
     * @since 1.0.0
     */
    public abstract R fromT(T a);

    // region sum, difference, product, quotient and modulo

    /**
     * @param a first value of sum
     * @param b second value of sum
     * @return {@code a+b}
     * @see AbstractArithmetic#sum(Number, Number)
     * @since 1.0.0
     */
    public R sum(T a, T b) {
        return getRArithmetic().sum(fromT(a), fromT(b));
    }

    /**
     * @param a first value of sum
     * @param b second value of sum
     * @param c third value of sum
     * @return {@code a+b+c}
     * @see #sum(Number, Number)
     * @see AbstractArithmetic#sum(Number, Number, Number)
     * @since 1.0.0
     */
    public R sum(T a, T b, T c) {
        return getRArithmetic().sum(fromT(a), fromT(b), fromT(c));
    }

    /**
     * @param a first value of difference
     * @param b second value of difference
     * @return {@code a-b}
     * @see AbstractArithmetic#difference(Number, Number)
     * @since 1.0.0
     */
    public R difference(T a, T b) {
        return getRArithmetic().difference(fromT(a), fromT(b));
    }

    /**
     * @param a first value of product
     * @param b second value of product
     * @return {@code a*b}
     * @see AbstractArithmetic#product(Number, Number)
     * @since 1.0.0
     */
    public R product(T a, T b) {
        return getRArithmetic().product(fromT(a), fromT(b));
    }

    /**
     * @param a first value of product
     * @param b second value of product
     * @param c third value of product
     * @return {@code a*b*c}
     * @see #product(Number, Number)
     * @see AbstractArithmetic#product(Number, Number, Number)
     * @since 1.0.0
     */
    public R product(T a, T b, T c) {
        return getRArithmetic().product(fromT(a), fromT(b), fromT(c));
    }

    /**
     * @param a first value of quotient
     * @param b second value of quotient
     * @return {@code a/b}
     * @see AbstractArithmetic#quotient(Number, Number)
     * @since 1.0.0
     */
    public R quotient(T a, T b) {
        return getRArithmetic().quotient(fromT(a), fromT(b));
    }

    /**
     * @param a first value of quotient
     * @param b second value of quotient
     * @return reminder of division like {@code r=a-q*b}
     * @throws NotSupportedException if operation is not supported
     * @see AbstractArithmetic#modulo(Number, Number)
     * @since 1.0.0
     */
    public R modulo(T a, T b) {
        return getRArithmetic().modulo(fromT(a), fromT(b));
    }

    // endregion

    // region map

    /**
     * @param arithmetic arithmetic for source
     * @param map        mapping function to convert current source to new one
     * @param <NT>       number class of new source
     * @return new {@link AbstractResultArithmetic} wich uses {@code V}
     * @since 1.0.0
     */
    public <NT extends Number> AbstractResultArithmetic<NT, R> map(
        AbstractArithmetic<NT> arithmetic, Function<NT, R> map
    ) {
        return AbstractResultArithmetic.of(
            arithmetic, getRArithmetic(), map
        );
    }

    /**
     * @param arithmetic arithmetic for target
     * @param map        mapping function to convert current result to new one
     * @param <NR>       number class of target
     * @return new {@link AbstractResultArithmetic} wich returns {@code V}
     * @since 1.0.0
     */
    public <NR extends Number> AbstractResultArithmetic<T, NR> mapResult(
        AbstractArithmetic<NR> arithmetic, Function<T, NR> map
    ) {
        return AbstractResultArithmetic.of(getTArithmetic(), arithmetic, map);
    }

    // endregion

    // region static of

    /**
     * @param tArithmetic {@link AbstractArithmetic} for calculations inside {@code T}
     * @param rArithmetic {@link AbstractArithmetic} for calculations to result
     * @param map         mapping function to convert {@code T} to {@code R}
     * @param <T>         number class for storing
     * @param <R>         number class for result
     * @return {@link AbstractResultArithmetic} with given values
     * @since 1.0.0
     */
    public static <T extends Number, R extends Number> AbstractResultArithmetic<T, R> of(
        AbstractArithmetic<T> tArithmetic,
        AbstractArithmetic<R> rArithmetic,
        Function<T, R> map
    ) {
        return new AbstractResultArithmetic<>(tArithmetic, rArithmetic) {
            @Override
            public R fromT(T a) {
                return map.apply(a);
            }
        };
    }

    // endregion

    // region override

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractResultArithmetic)) return false;
        AbstractResultArithmetic<?, ?> that = (AbstractResultArithmetic<?, ?>) o;
        return Objects.equals(getTArithmetic(), that.getTArithmetic()) &&
            Objects.equals(getRArithmetic(), that.getRArithmetic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTArithmetic(), getRArithmetic());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
            "tArithmetic=" + getTArithmetic() +
            ", rArithmetic=" + getRArithmetic() +
            '}';
    }

    // endregion
}
