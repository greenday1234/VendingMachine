package termProject.vendingmachine.config;

import java.io.*;

import static termProject.vendingmachine.VendingMachineThread.*;

public class Config {

    public static void socketConfig(int PORT) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        dailyFileWriter = new FileWriter("./sales" + "/" + PORT + "/daily" + "/dailySales.txt");
        dailyFileReader = new FileReader("./sales" + "/" + PORT + "/daily" + "/dailySales.txt");
        monthlyFileWriter = new FileWriter("./sales" + "/" + PORT + "/monthly" + "/monthlySales.txt");
        monthlyFileReader = new FileReader("./sales" + "/" + PORT + "/monthly" + "/monthlySales.txt");
        dailyReader = new BufferedReader(dailyFileReader);
        monthlyReader = new BufferedReader(monthlyFileReader);
    }
}
