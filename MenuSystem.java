import java.io.*;
import java.util.*;

public class MenuSystem {
    private Map<Integer, List<Appliance>> applianceMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public MenuSystem(Simulation sim) {
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Find Appliance");
            System.out.println("2. Add Appliance");
            System.out.println("3. Delete Appliance");
            System.out.println("4. View Appliances by Location");
            System.out.println("5. View Appliances by Type");
            System.out.println("6. Run Simulation");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": findAppliance(); break;
                case "2": addAppliance(); break;
                case "3": deleteAppliance(); break;
                case "4": viewByLocation(); break;
                case "5": viewByType(); break;
                case "6":
                    Simulation sim = new Simulation();
                    sim.setApplianceData(applianceMap);
                    sim.run(3);
                    sim.printSummary();
                    break;

                case "7": return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    public Map<Integer, List<Appliance>> getAllAppliances() {
        return applianceMap;
    }

    private void findAppliance() {
        System.out.print("Enter appliance ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (List<Appliance> appliances : applianceMap.values()) {
            for (Appliance a : appliances) {
                if (a.getApplianceID() == id) {
                    System.out.println(a);
                    return;
                }
            }
        }
        System.out.println("Appliance not found.");
    }

    private void addAppliance() {
        System.out.print("Add manually or from file? (m/f): ");
        String option = scanner.nextLine();
        if (option.equalsIgnoreCase("m")) {
            addApplianceManually();
        } else {
            System.out.print("Enter filename: ");
            loadFromFile(scanner.nextLine());
        }
    }

    private void addApplianceManually() {
        System.out.print("Enter location (8-digit): ");
        int location = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter appliance name: ");
        String name = scanner.nextLine();
        System.out.print("Enter on wattage: ");
        int onW = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter off wattage: ");
        int offW = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter probability ON (0.0 - 1.0): ");
        double probOn = Double.parseDouble(scanner.nextLine());

        Appliance a = new Appliance(location, name, onW, offW, probOn);
        applianceMap.computeIfAbsent(location, k -> new ArrayList<>()).add(a);
        System.out.println("Appliance added.");
    }

    private void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int location = Integer.parseInt(parts[0]);
                String name = parts[1];
                int onW = Integer.parseInt(parts[2]);
                int offW = Integer.parseInt(parts[3]);
                double probOn = Double.parseDouble(parts[4]);

                Appliance a = new Appliance(location, name, onW, offW, probOn);
                applianceMap.computeIfAbsent(location, k -> new ArrayList<>()).add(a);
            }
            System.out.println("Appliances loaded.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    private void deleteAppliance() {
        System.out.print("Enter appliance ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (List<Appliance> appliances : applianceMap.values()) {
            Iterator<Appliance> it = appliances.iterator();
            while (it.hasNext()) {
                Appliance a = it.next();
                if (a.getApplianceID() == id) {
                    it.remove();
                    System.out.println("Appliance deleted.");
                    return;
                }
            }
        }
        System.out.println("Appliance not found.");
    }

    private void viewByLocation() {
        System.out.print("Enter location: ");
        int location = Integer.parseInt(scanner.nextLine());
        List<Appliance> list = applianceMap.get(location);
        if (list != null) {
            list.forEach(System.out::println);
        } else {
            System.out.println("No appliances at this location.");
        }
    }

    private void viewByType() {
        System.out.print("Enter appliance name/type: ");
        String type = scanner.nextLine();
        boolean found = false;
        for (List<Appliance> appliances : applianceMap.values()) {
            for (Appliance a : appliances) {
                if (a.getName().equalsIgnoreCase(type)) {
                    System.out.println(a);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No appliances of this type found.");
    }
}
