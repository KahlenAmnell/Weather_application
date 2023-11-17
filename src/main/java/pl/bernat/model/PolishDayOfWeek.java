package pl.bernat.model;

import java.time.LocalDate;

public class PolishDayOfWeek {
    public static String polishName(String weatherDate){
        weatherDate = weatherDate.substring(0, 10);
        LocalDate localDate = LocalDate.parse(weatherDate);
        int number = localDate.getDayOfWeek().getValue();
        return nameOfDay(number);
    }

    private static String nameOfDay(int number) {
        String result;
        switch (number){
            case 1: result = "Poniedziałek"; break;
            case 2: result = "Wtorek"; break;
            case 3: result = "Środa"; break;
            case 4: result = "Czwartek"; break;
            case 5: result = "Piątek"; break;
            case 6: result = "Sobota"; break;
            case 7: result = "Niedziela"; break;
            default: result = "Błąd"; break;
        }
        return result;
    }
}
