package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaluate();
    }

    public record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            return -value.evaluate();
        }
    }

    public record Exponent(Expr firstValue, Expr secondValue) implements Expr {

        Exponent(Expr value1, double value2) {
            this(value1, new Constant(value2));
        }

        @Override
        public double evaluate() {
            return Math.pow(firstValue.evaluate(), secondValue.evaluate());
        }
    }

    public record Addition(Expr firstValue, Expr secondValue) implements Expr {
        @Override
        public double evaluate() {
            return firstValue.evaluate() + secondValue.evaluate();
        }
    }

    public record Multiplication(Expr firstValue, Expr secondValue) implements Expr {
        @Override
        public double evaluate() {
            return firstValue.evaluate() * secondValue.evaluate();
        }
    }


}
