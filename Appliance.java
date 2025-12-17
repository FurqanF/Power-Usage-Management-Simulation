public class Appliance implements Comparable<Appliance>{
    private static int idCounter = 1 ;
    private final int applianceID;
    private int location;
    private String name;
    private int onWattage;
    private int offWattage;
    private double probOn;

    protected Appliance() {
        this.applianceID = idCounter++;
        this.location = 0;
        this.name = "";
        this.onWattage = 0;
        this.offWattage = 0;
        this.probOn = 0.0;
    }

    public Appliance clone() {
        Appliance copy = new Appliance(this.location, this.name, this.onWattage, this.offWattage, this.probOn);
        return copy;
    }

    public Appliance(long location, String name, int onWattage, int offWattage, double probOn){
        this.applianceID = idCounter++ ;
        setLocation((int) location); // Cast long to int
        setName(name);
        setOnWatts(onWattage);
        setOffWatts(offWattage);
        setProbOn(probOn);
    }


    public int getApplianceID() {
        return applianceID;
    }

    public int getLocation(){
        return location;
    }

    public String getName(){
        return name;
    }

    public void setLocation(int location){
        if(String.valueOf(Math.abs(location)).length() == 8) {
            this.location = location ;
        }else{
            this.location = 99999999;
        }
    }

    public void setName(String name){
        if( name != null && !name.isEmpty()){
            this.name = name ;
        }else{
            this.name = "UNKNOWN";
        }
    }

    public int getOnWatts() {
        return onWattage;
    }

    public void setOnWatts(int onWattage){
        this.onWattage = Math.max(onWattage , 1);
    }

    public int getOffWatts(){
        return offWattage;
    }

    public void setOffWatts(int offWattage){
        this.offWattage = Math.max( offWattage, 0);
    }

    public double getProbOn(){
        return probOn;
    }

    public void setProbOn(double probOn) {
        if(probOn >= 0 && probOn <=1){
            this.probOn = probOn;
        }else{
            this.probOn = 0.0;
        }
    }

    protected static void decrementID() {
        idCounter--;
    }

    @Override
    public int compareTo(Appliance other) {
        if (this.onWattage != other.onWattage) {
            return Integer.compare(other.onWattage, this.onWattage); // Sort descending
        }
        return Integer.compare(this.offWattage, other.offWattage); // Sort ascending
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Appliance)) return false;
        Appliance oth = (Appliance) obj;
        return this.name.equals(oth.name) &&
                this.onWattage == oth.onWattage &&
                this.offWattage == oth.offWattage &&
                Double.compare(this.probOn, oth.probOn) == 0;
    }

    @Override
    public String toString() {
        return applianceID + " Loc=" + location + " Name=" + name + " OnW=" + onWattage + " OffW=" + offWattage + " ProbOn=" + probOn;
    }
}