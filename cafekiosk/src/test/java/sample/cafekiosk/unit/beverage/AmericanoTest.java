package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.unit.beverage.Americano;

import static org.assertj.core.api.Assertions.assertThat;


class AmericanoTest {

    @Test
    void getName(){
        Americano americano = new Americano();
        assertThat(new Americano().getName()).isEqualTo("아메리카노");
    }

    @Test
    void getPrice(){
        Americano americano = new Americano();
        assertThat(americano.getPrice()).isEqualTo(4000);
    }

}