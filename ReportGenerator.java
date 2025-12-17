import java.io.*;
import java.util.*;

public class ReportGenerator {
    private List<Appliance> appliances;

    public ReportGenerator(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void generateReport(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Appliance app : appliances) {
                writer.println(app);
            }
        }
    }

    public void printSummary() {
        int total = appliances.size();
        int smartCount = 0;
        double totalOn = 0, totalOff = 0;

        for (Appliance app : appliances) {
            if (app instanceof SmartAppliance) {
                smartCount++;
            }
            totalOn += app.getOnWatts();
            totalOff += app.getOffWatts();
        }

        System.out.printf("Total Appliances: %d%n", total);
        System.out.printf("Smart Appliances: %d%n", smartCount);
        System.out.printf("Average On Wattage: %.2f%n", totalOn / total);
        System.out.printf("Average Off Wattage: %.2f%n", totalOff / total);
    }
}
