public class TestSmartAppliance {
  public static void main(String[] args){
    System.out.println("=== testing SmartAppliance class ===");
    try{
      SmartAppliance sa1 = new SmartAppliance(12345678, "AC", 2000, 50, 0.9, 0.25);
      System.out.println("Created smart appliance: " + sa1);
      //clone test
      SmartAppliance clone = sa1.clone();
      System.out.println("Cloned smart appliance: " + clone);
      //Equality test
      System.out.println("sa1 equals clone: " + sa1.equals(clone));
      //invalid reduce percentage
      SmartAppliance sa2 = new SmartAppliance(11112222, "Heater", 1800, 10, 0.7, 1.5);
      System.out.println("Should not see this: " + sa2);
    }catch (SmartApplianceException e){
      System.out.println("caught SmartApplianceException: " + e.getMessage()0;
    }
  }
}
