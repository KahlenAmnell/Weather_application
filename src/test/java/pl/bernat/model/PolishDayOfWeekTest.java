package pl.bernat.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class PolishDayOfWeekTest {
    @Test
    void givenWeatherDateShouldReturnPolishNameOfDay(){
        //given
        String weatherDate = "2023-11-17 15:00:00";

        //when
        String result = PolishDayOfWeek.polishName(weatherDate);

        //then
        assertThat(result, equalTo("Piątek"));
    }
}