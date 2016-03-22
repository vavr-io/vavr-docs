/*     / \____  _    _  ____   ______  / \ ____  __    _ _____
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  / /  _  \   Javaslang
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/  \__/  /   Copyright 2014-now Daniel Dietrich
 * /___/\_/  \_/\____/\_/  \_/\__\/__/___\_/  \_//  \__/_____/    Licensed under the Apache License, Version 2.0
 */
package javaslang;

import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class TupleDemo {

    @Test
    public void createTuple() {
        // tag::createTuple[]
        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8); //<1>

        // "Java"
        String s = java8._1; //<2>

        // 8
        Integer i = java8._2; //<3>
        // end::createTuple[]
        then(s).isEqualTo("Java");
        then(i).isEqualTo(8);
    }

    @Test
    public void bimapTuple() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // tag::bimapTuple[]
        // (Javaslang, 2)
        Tuple2<String, Integer> that = java8.map(
                s -> s + "slang",
                i -> i / 4
        );
        // end::bimapTuple[]
        then(that._1).isEqualTo("Javaslang");
        then(that._2).isEqualTo(2);
    }

    @Test
    public void mapTuple() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // tag::mapTuple[]
        // (Javaslang, 2)
        Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s + "slang", i / 4)
        );
        // end::mapTuple[]
        then(that._1).isEqualTo("Javaslang");
        then(that._2).isEqualTo(2);
    }

    @Test
    public void transformTuple() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // tag::transformTuple[]
        // "Javaslang 2"
        String that = java8.transform(
                (s, i) -> s + "slang " + i / 4
        );
        // end::transformTuple[]
        then(that).isEqualTo("Javaslang 2");
    }

    @Test
    public void createEmptyTuple() {
        Tuple0 emptyTuple = Tuple.empty();
    }

}
