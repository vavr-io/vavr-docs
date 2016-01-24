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
    public void createTuple(){
        // tag::createTuple[]
        // (Java, 8)
        Tuple2<String, Integer> java8tuple = Tuple.of("Java", 8); //<1>

        then(java8tuple._1).isEqualTo("Java"); //<2>
        then(java8tuple._2).isEqualTo(8); //<3>
        // end::createTuple[]
    }

    @Test
    public void bimapTuple(){

        // tag::bimapTuple[]
        // (Java, 8)
        Tuple2<String, Integer> javaslang2tuple = Tuple.of("Java", 8).map(
                (s) -> s + "slang", (i) -> i/4
        );
        // (Javaslang, 2)
        then(javaslang2tuple._1).isEqualTo("Javaslang");
        then(javaslang2tuple._2).isEqualTo(2);
        // end::bimapTuple[]
    }

    @Test
    public void mapTuple(){

        // tag::mapTuple[]
        // (Java, 8)
        Tuple2<String, Integer> javaslang2tuple = Tuple.of("Java", 8).map(
                (s, i) -> Tuple.of(s + "slang", i / 4)
        );
        // (Javaslang, 2)
        then(javaslang2tuple._1).isEqualTo("Javaslang");
        then(javaslang2tuple._2).isEqualTo(2);
        // end::mapTuple[]
    }

    @Test
    public void transformTuple(){
        // tag::transformTuple[]
        String javaslang2 = Tuple.of("Java", 8).transform(
                (s, i) -> s + "slang " + i/4
        );
        then(javaslang2).isEqualTo("Javaslang 2");
        // end::transformTuple[]
    }

    @Test
    public void createEmptyTuple(){
        Tuple0 emptyTuple = Tuple.empty();
    }

}
