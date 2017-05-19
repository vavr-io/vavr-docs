/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr;

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
        // (vavr, 1)
        Tuple2<String, Integer> that = java8.map(
                s -> s.substring(2) + "vr",
                i -> i / 8
        );
        // end::bimapTuple[]
        then(that._1).isEqualTo("vavr");
        then(that._2).isEqualTo(1);
    }

    @Test
    public void mapTuple() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // tag::mapTuple[]
        // (vavr, 1)
        Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );
        // end::mapTuple[]
        then(that._1).isEqualTo("vavr");
        then(that._2).isEqualTo(1);
    }

    @Test
    public void applyTuple() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        // tag::transformTuple[]
        // "vavr 1"
        String that = java8.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );
        // end::transformTuple[]
        then(that).isEqualTo("vavr 1");
    }

    @Test
    public void createEmptyTuple() {
        Tuple0 emptyTuple = Tuple.empty();
    }

}
