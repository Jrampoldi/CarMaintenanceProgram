//Name: Jourdan Rampoldi
//Date: 12/14/2022
//Project: Final with vehicle class, maintenance class, and application

package CarMaintenance;

public class Maintenance 
{
    //data attributes
    private int maintenanceID;
    private static int nextMaintenanceID = 1;
    private String maintenanceDate;
    private String maintenanceDesc;
    private double startTime;
    private double endTime;
    private String vehicleVIN;
    private double costPerHour;

    public Maintenance(String mDate,String mDesc, String sTime, String eTime, String VIN, double cost)
    {
        this.maintenanceDate = mDate;
        this.maintenanceDesc = mDesc;
        startTime = Double.parseDouble(sTime);
        endTime = Double.parseDouble(eTime);
        vehicleVIN = VIN;
        costPerHour = cost;
        this.maintenanceID = nextMaintenanceID;
        nextMaintenanceID++;
    }//end of maintenance constructor

    public int getID()
    {
        return this.maintenanceID;
    }//returns maintenance id

    public String getVehicleVIN()
    {
        return this.vehicleVIN;
    }//returns vin 

    public double getTotalCost()
    {
        double totalCost = this.getTotalTime() * this.costPerHour;
        return totalCost;
    }//calculates and returns total cost 

    public double getTotalTime()
    {
        double totalTime = this.endTime - this.startTime;
        return totalTime;
    }//calculates and returns total time

    public void setCost(double cost)
    {
        this.costPerHour = cost;
    }//assigns cost per hour

    public void setStartTime(double time)
    {
        this.startTime = time;
    }//assigns start time

    public void setEndTime(double time)
    {
        this.endTime = time;
    }//assigns end time

    public String toString()
    {
        String returnStatement ="ID: " + this.maintenanceID + " VIN: " + this.vehicleVIN + " Date: " + this.maintenanceDate + " Cost: " + this.getTotalCost();
        return returnStatement;
    }//returns formatted information about maintenance
}
