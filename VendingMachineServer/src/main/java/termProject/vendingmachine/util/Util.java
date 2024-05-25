package termProject.vendingmachine.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static termProject.vendingmachine.VendingMachineThread.reader;

public class Util {

    public static final List<String> DRINK = new ArrayList<>(List.of("water", "coffee", "sportsDrink", "highQualityCoffee", "soda", "specialDrink"));

    public static String updateDate() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(now);
    }

    public static String[] checkClicked() throws IOException {
        String clicked = reader.readLine();
        String[] split = clicked.split(" ");
        return split;
    }
}
