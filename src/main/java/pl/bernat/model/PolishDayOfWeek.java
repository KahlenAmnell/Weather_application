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
        switch (number){
            case 1:
                return "Poniedziałek";
            case 2:
                return "Wtorek";
            case 3:
                return "Środa";
            case 4:
                return "Czwartek";
            case 5:
                return "Piątek";
            case 6:
                return "Sobota";
            case 7:
                return "Niedziela";
            default:
                return "Błąd";
        }
    }
}
