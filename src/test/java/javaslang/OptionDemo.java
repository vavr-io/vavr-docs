/*     / \____  _    _  ____   ______  / \ ____  __    _ _____
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  / /  _  \   Javaslang
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/  \__/  /   Copyright 2014-now Daniel Dietrich
 * /___/\_/  \_/\____/\_/  \_/\__\/__/___\_/  \_//  \__/_____/    Licensed under the Apache License, Version 2.0
 */
package javaslang;

import javaslang.control.Option;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;


/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class OptionDemo {

    @Test
    public void javaOptionalWithMappedNull() {
        // tag::javaOptionalWithMappedNull[]
        Optional<String> maybeFoo = Optional.of("foo"); //<1>
        then(maybeFoo.get()).isEqualTo("foo");
        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null)  //<2>
                                               .map(s -> s.toUpperCase() + "bar");
        then(maybeFooBar.isPresent()).isFalse();
        // end::javaOptionalWithMappedNull[]
    }

    @Test
    public void javaslangOptionWithMappedNull() {
        // tag::javaslangOptionWithMappedNull[]
        Option<String> maybeFoo = Option.of("foo"); //<1>
        then(maybeFoo.get()).isEqualTo("foo");
        try {
            maybeFoo.map(s -> (String)null) //<2>
                    .map(s -> s.toUpperCase() + "bar"); //<3>
            Assert.fail();
        } catch (NullPointerException e) {
            // this is clearly not the correct approach
        }
        // end::javaslangOptionWithMappedNull[]
    }

    @Test
    public void flatMapNullParameter() {
        // tag::flatMapNullParameter[]
        Option<String> maybeFoo = Option.of("foo"); //<1>
        then(maybeFoo.get()).isEqualTo("foo");
        Option<String> maybeFooBar = maybeFoo.map(s -> (String)null) //<2>
                                             .flatMap(s -> Option.of(s) //<3>
                                                                 .map(t -> t.toUpperCase() + "bar"));
        then(maybeFooBar.isEmpty()).isTrue();
        // end::flatMapNullParameter[]
    }

    @Test
    public void mapOptionParameter() {
        // tag::mapOptionParameter[]
        Option<String> maybeFoo = Option.of("foo"); //<1>
        then(maybeFoo.get()).isEqualTo("foo");
        Option<String> maybeFooBar = maybeFoo.flatMap(s -> Option.of((String)null)) //<2>
                                             .map(s -> s.toUpperCase() + "bar");
        then(maybeFooBar.isEmpty()).isTrue();
        // end::mapOptionParameter[]
    }
}
