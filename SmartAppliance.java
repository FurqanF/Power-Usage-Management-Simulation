public class SmartAppliance extends Appliance {
    private double reducePercentage;

    public SmartAppliance() {
        super();
        this.reducePercentage = 0.0;
    }
    @Override
    public SmartAppliance clone() {
        try{
            return new SmartAppliance(this.getLocation(), this.getName(), this.getOnWatts(), this.getOffWatts(), this.getProbOn(), this.reducePercentage);
        }catch (SmartApplianceException e){
            return null;
        }
    }

    public SmartAppliance(long location, String name, int onWattage, int offWattage, double probOn, double reducePercentage) throws SmartApplianceException{
        super(location, name, onWattage, offWattage, probOn);
        try{
            setReducePercentage(reducePercentage);
        }catch(SmartApplianceException e){
            decrementID();
            throw e;
        }
    }


    public double getReducePercentage() {
        return reducePercentage;
    }

    public void setReducePercentage(double reducePercentage) throws SmartApplianceException{
        if((reducePercentage >= 0)&&(reducePercentage <= 1)){
            this.reducePercentage = reducePercentage ;
        }else{
            throw new SmartApplianceException("Invalid Reduce Percentage(" + reducePercentage + ")");
        }
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof SmartAppliance)) return false;
        SmartAppliance oth = (SmartAppliance) obj ;
        return super.equals(oth) && Double.compare(this.reducePercentage, oth.reducePercentage) == 0;
    }
    @Override
    public String toString(){
        return super.toString() + String.format(" Reduce%%=%s", (reducePercentage * 100) % 10 == 0 ? String.format("%.1f", reducePercentage) : String.format("%.2f", reducePercentage));
    }
}