/*     / \____  _    _  ____   ______  / \ ____  __    _ _____
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  / /  _  \   Javaslang
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/  \__/  /   Copyright 2014-now Daniel Dietrich
 * /___/\_/  \_/\____/\_/  \_/\__\/__/___\_/  \_//  \__/_____/    Licensed under the Apache License, Version 2.0
 */
package javaslang;

import javaslang.control.None;
import javaslang.control.Option;
import javaslang.control.Some;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class FunctionsDemo {

    @Test
    public void createFunctionWithLambda(){
        // tag::createFunctionWithLambda[]
        Function2<Integer, Integer, Integer> sum =  (a, b) -> a + b;
        then(sum.apply(1,2)).isEqualTo(3);
        // end::createFunctionWithLambda[]
    }

    @Test
    public void createFunctionWithAnonymousClass(){
        // tag::createFunctionWithAnonymousClass[]
        Function2<Integer, Integer, Integer> sum =  new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        // end::createFunctionWithAnonymousClass[]
        then(sum.apply(1,2)).isEqualTo(3);
    }

    @Test
    public void createFunctionWithFactoryMethod(){
        // tag::createFunctionWithFactoryMethod[]
        Function3<String, String, String, String> function3 = Function3.of(TestClass::methodWhichAccepts3Parameters);
        // end::createFunctionWithFactoryMethod[]
    }

    @Test
    public void liftPartialFunctionShouldReturnSome(){
        // tag::liftPartialFunctionShouldReturnSome[]
        Function2<Integer, Integer, Option<Integer>> liftedPartialSumFunction = Function2.lift(TestClass::sum);

        Option<Integer> optionalResult = liftedPartialSumFunction.apply(1, 2); //<1>

        then(optionalResult).isInstanceOf(Some.class);
        then(optionalResult.isDefined()).isTrue();
        then(optionalResult.get()).isEqualTo(3);
        // end::liftPartialFunctionShouldReturnSome[]
    }

    @Test
    public void liftPartialFunctionShouldReturnNone(){
        // tag::liftPartialFunctionShouldReturnNone[]
        Function2<Integer, Integer, Option<Integer>> liftedPartialSumFunction = Function2.lift(TestClass::sum);

        Option<Integer> optionalResult = liftedPartialSumFunction.apply(-1, 2); //<1>

        then(optionalResult).isInstanceOf(None.class);
        then(optionalResult.isEmpty()).isTrue();
        // end::liftPartialFunctionShouldReturnNone[]
    }

    @Test
    public void memoizedFunction(){
        // tag::memoizedFunction[]
        Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        then(randomValue1).isEqualTo(randomValue2);
        // end::memoizedFunction[]
    }

    @Test
    public void composeFunctions1(){
        // tag::composeFunctions1[]
        Function1<Integer, Integer> plusOne = (a) -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = (a) -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        then(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
        // end::composeFunctions1[]
    }

    @Test
    public void composeFunctions2(){
        Function1<Integer, Integer> plusOne = (a) -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = (a) -> a * 2;
        // tag::composeFunctions2[]
        Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);

        then(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
        // end::composeFunctions2[]
    }

    @Test
    public void curryingFunction(){
        // tag::curryingFunction[]
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2); //<1>

        then(add2.apply(4)).isEqualTo(6);
        // end::curryingFunction[]
    }

    public static class TestClass {
        public static String methodWhichAccepts3Parameters(String one, String two, String three) {
            return one + two + three;
        }

        // tag::partialFunctionExample[]
        public static int sum(int first, int second) {
            if (first >= 0 && second >= 0) {
                return first + second;
            } else {
                throw new IllegalArgumentException("Only positive integers are allowed"); //<1>
            }
        }
        // end::partialFunctionExample[]
    }

}
