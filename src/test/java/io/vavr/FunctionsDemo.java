/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr;

import io.vavr.*;
import io.vavr.control.Option;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class FunctionsDemo {

    @Test
    public void createFunctionWithLambda() {
        // tag::createFunctionWithLambda[]
        // sum.apply(1, 2) = 3
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        // end::createFunctionWithLambda[]
        then(sum.apply(1, 2)).isEqualTo(3);
    }

    @Test
    public void createFunctionWithAnonymousClass() {
        // tag::createFunctionWithAnonymousClass[]
        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        // end::createFunctionWithAnonymousClass[]
        then(sum.apply(1, 2)).isEqualTo(3);
    }

    @Test
    public void createFunctionWithFactoryMethod() {
        // tag::createFunctionWithFactoryMethod[]
        Function3<String, String, String, String> function3 =
                Function3.of(this::methodWhichAccepts3Parameters);
        // end::createFunctionWithFactoryMethod[]
    }

    @Test
    public void liftMethodReference() {
        // tag::liftMethodReference[]
        Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum);

        // = None
        Option<Integer> optionalResult = sum.apply(-1, 2); //<1>
        // end::liftMethodReference[]
        then(optionalResult).isEqualTo(Option.none());
    }

    @Test
    public void liftPartialFunction() {

        // tag::partialDivideFunction[]
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        // end::partialDivideFunction[]

        // tag::liftedDivideFunction[]
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        // = None
        Option<Integer> i1 = safeDivide.apply(1, 0); //<1>

        // = Some(2)
        Option<Integer> i2 = safeDivide.apply(4, 2); //<2>
        // end::liftedDivideFunction[]

        then(i1).isEqualTo(Option.none());
        then(i2).isEqualTo(Option.some(2));
    }

    @Test
    public void memoizedFunction() {
        // tag::memoizedFunction[]
        Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        then(randomValue1).isEqualTo(randomValue2);
        // end::memoizedFunction[]
    }

    @Test
    public void composeFunctions1() {
        // tag::composeFunctions1[]
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        then(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
        // end::composeFunctions1[]
    }

    @Test
    public void composeFunctions2() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        // tag::composeFunctions2[]
        Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);

        then(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
        // end::composeFunctions2[]
    }

    @Test
    public void partialApplicationFunction() {
        // tag::partialApplicationFunction[]
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.apply(2); //<1>

        then(add2.apply(4)).isEqualTo(6);
        // end::partialApplicationFunction[]
    }

    @Test
    public void partialApplicationFunctionArity5() {
        // tag::partialApplicationFunctionArity5[]
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sum.apply(2, 3, 1); //<1>

        then(add6.apply(4, 3)).isEqualTo(13);
        // end::partialApplicationFunctionArity5[]
    }

    @Test
    public void curryingFunction() {
        // tag::curryingFunction[]
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2); //<1>

        then(add2.apply(4)).isEqualTo(6);
        // end::curryingFunction[]
    }

    @Test
    public void curryingFunctionArity3() {
        // tag::curryingFunctionArity3[]
        Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;
        final Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(2);//<1>

        then(add2.apply(4).apply(3)).isEqualTo(9);//<2>
        // end::curryingFunctionArity3[]
    }

    String methodWhichAccepts3Parameters(String one, String two, String three) {
        return one + two + three;
    }

    // tag::partialFunctionExample[]
    int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed"); //<1>
        }
        return first + second;
    }
    // end::partialFunctionExample[]

}
