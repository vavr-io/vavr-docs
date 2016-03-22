/*     / \____  _    _  ____   ______  / \ ____  __    _ _____
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  / /  _  \   Javaslang
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/  \__/  /   Copyright 2014-now Daniel Dietrich
 * /___/\_/  \_/\____/\_/  \_/\__\/__/___\_/  \_//  \__/_____/    Licensed under the Apache License, Version 2.0
 */
package javaslang;

import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class LazyDemo {

    @Test
    public void createLazy(){
        // tag::createLazy[]
        Lazy<Double> lazy = Lazy.of(Math::random);

        then(lazy.isEvaluated()).isFalse();
        Double value = lazy.get(); // = 0.123 (random generated)
        then(lazy.isEvaluated()).isTrue();
        Double value2 = lazy.get(); // = 0.123 (memoized)
        then(value).isEqualTo(value2);
        // end::createLazy[]
    }
}
