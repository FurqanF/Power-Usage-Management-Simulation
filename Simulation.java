import java.util.*;

public class Simulation {
    private Map<Integer, List<Appliance>> applianceMap;
    private Random rand = new Random();

    public void setApplianceData(Map<Integer, List<Appliance>> applianceMap) {
        this.applianceMap = applianceMap;
    }

    public void run(int intervals) {
        if (applianceMap == null) {
            System.out.println("No appliance data provided for simulation.");
            return;
        }

        for (int t = 0; t < intervals; t++) {
            System.out.println("Interval " + (t + 1));
            for (Map.Entry<Integer, List<Appliance>> entry : applianceMap.entrySet()) {
                int location = entry.getKey();
                for (Appliance a : entry.getValue()) {
                    boolean isOn = rand.nextDouble() < a.getProbOn();
                    int power = isOn ? a.getOnWatts() : a.getOffWatts();

                    if (a instanceof SmartAppliance && isOn) {
                        SmartAppliance sa = (SmartAppliance) a;
                        power = (int) (power * (1 - sa.getReducePercentage()));
                    }

                    System.out.printf("Loc %d: %s is %s using %dW\n",
                            location, a.getName(), isOn ? "ON" : "OFF", power);
                }
            }
            System.out.println();
        }
    }

    public void printSummary() {
        if (applianceMap == null) {
            System.out.println("No appliance data to summarize.");
            return;
        }

        Map<String, Integer> applianceCount = new HashMap<>();
        Set<Integer> locations = new HashSet<>();

        for (Map.Entry<Integer, List<Appliance>> entry : applianceMap.entrySet()) {
            locations.add(entry.getKey());
            for (Appliance a : entry.getValue()) {
                applianceCount.put(a.getName(), applianceCount.getOrDefault(a.getName(), 0) + 1);
            }
        }

        System.out.println("Summary Report:");
        System.out.println("Total locations: " + locations.size());
        for (Map.Entry<String, Integer> entry : applianceCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
