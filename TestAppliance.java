public class TestAppliance{
  public static void main(String[] args){
    System.out.println("=== Testing Appliance Class ===");
    //valid appliance
    Appliance a1 = new Appliance(12345678, "Fridge", 150, 2, 0.8);
    System.out.println("Created appliance: " + a1);
    //invalid location
    Appliance a2 = new Appliance(123, "Toaster", 800, 1, 0.5);
    System.out.println("Invalid location set to default: " + a2);
    //Invalid probability
    Appliance a3 = new Appliance(876543210, "Microwave", 1200, 10, 1.5);
    System.out.println("Invalid probOn set to default: " + a3);
    //clone test
    Appliance clone = a1.clone();
    System.out.println("Cloned appliance: " + clone);
    //eq test
    System.out.println("a1 equals clon: " + a1.equals(clone));
    System.out.println("a1 equals a2: " + a1.equals(a2));
  }
}
