import java.util.*;
public class TestReportGenerator{
  public static void main(String[] args){
    System.out.println("=== Testing ReportGenerator Class ===");
    
    List<Appliance> list = new ArrayList<>();
    list.add(new Appliance(12345678, "Light", 40, 1, 0.5));
    list.add(new Appliance(87654321, "Fan", 75, 2, 0.6));

    try{
      SmartAppliance sa = new SmartAppliance(87654321, "SmartTV", 500, 10, 0.9, 0.3);
      list.add(sa);
    }catch (SmartApplianceException e){
      System.out.println("Error creating smart appliance: " + e.getMessage());
    }
    
    ReportGenerator rg = new ReportGenerator(list);
    rg.printSummary();

    try{
      rg.generateReport("test_report.txt");
      System.out.println("Report written to test_report.txt");
    }catch(Exception e){
      System.out.println("Error Writing report: " + e.getMessage());
    }
  }
}
