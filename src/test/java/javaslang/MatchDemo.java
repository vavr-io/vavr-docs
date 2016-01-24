/*     / \____  _    _  ____   ______  / \ ____  __    _ _____
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  / /  _  \   Javaslang
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/  \__/  /   Copyright 2014-now Daniel Dietrich
 * /___/\_/  \_/\____/\_/  \_/\__\/__/___\_/  \_//  \__/_____/    Licensed under the Apache License, Version 2.0
 */
package javaslang;

import javaslang.control.Match;
import org.junit.Test;

import java.util.function.Consumer;

public class MatchDemo {

    @Test
    public void shouldMatchValueAsEffect() {
        // tag::matchValueAsEffect[]
        // 1 is a Number!
        Match.of(1)
                .whenType(String.class).thenRun(s -> println(s, "is a String!"))
                .whenType(Number.class).thenRun(n -> println(n, "is a Number!"))
                .otherwiseRun(o -> println(o, "has an unknown type."));
        // end::matchValueAsEffect[]
    }

    @Test
    public void shouldMatchFunctionAsEffect() {
        // tag::matchFunctionAsEffect[]
        final Consumer<Object> action = Match
                .whenType(String.class).thenRun(s -> println(s, "is a String!"))
                .whenType(Number.class).thenRun(n -> println(n, "is a Number!"))
                .otherwiseRun(o -> println(o, "has an unknown type."));

        // 1 is a Number!
        action.accept(1);

        // A is a String!
        action.accept("A");
        // end::matchFunctionAsEffect[]
    }

    // tag::effect[]
    static void println(Object o, String message) {
        System.out.println(o + " " + message);
    }
    // end::effect[]
}
