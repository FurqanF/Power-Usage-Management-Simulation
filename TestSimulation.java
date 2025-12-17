import java.util.*;
public class TestSimulation{
  public static void main(String[] args){
    System.out.println("=== Testing Simulation Class ===");
    
    Map<Integer, List<Appliance>> map = new HashMap<>();
    List<Appliance> appliances = new ArrayList<>();
    appliances.add(new Appliance(12345678, "Lamp", 60, 1, 0.5));
    appliances.add(new Appliance(12345678, "TV", 300, 5, 0.7));
    map.put(12345679, appliances);

    Simulation sim = new Simulation();
    sim.setApplianceData(map);
    sim.run(2);
    sim.printSummary();
  }
}
