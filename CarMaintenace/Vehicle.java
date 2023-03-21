//Name: Jourdan Rampoldi
//Date: 12/14/2022
//Project: Final with vehicle class, maintenance class, and application

package CarMaintenance;

public class Vehicle
{
    //data attributes
    private String VIN;
    private String Type;
    private String ownerPhoneNumber;
    private String insuranceCompany;
    private int maintenance[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int numberOfMaintenance = 10;
    private int nextID = 0;

    public Vehicle(String vVin, String vType, String vPhone, String vinsurance)
    {
        this.VIN = vVin;
        this.Type = vType;
        this.ownerPhoneNumber = vPhone;
        this.insuranceCompany = vinsurance;
    }//end of vehicle constructor
    
    public void setInsurance(String company)
    {
        this.insuranceCompany = company;
    }//assigns new insurance

    public boolean addMaintenance(int vMaintenance)
    {
        for (int i = 0; i < this.numberOfMaintenance; i++)
        {
            if (maintenance[i] == 0)
            {
                this.maintenance[i] = vMaintenance;
                return true;
            }
        }
        return false;
    }//adds new maintenance id and returns if successful

    public int[] getAllMaintenance()
    {
        return this.maintenance;
    }//returns array of maintenance

    public int getNextMaintenance()
    {
        int nextCall;
        if (nextID < 10)
        {
            nextCall = this.maintenance[this.nextID];
            nextID++;
            return nextCall;
        }
        else 
            System.out.println("Please reset nextID to review the records again.");
            return 0;
    }//returns next id

    public String getVIN()
    {
        return this.VIN;
    }//returns vin

    public String getInsurance()
    {
        return this.insuranceCompany;
    }//returns insurance company

    public static boolean verifyVIN(String vVin)
    {
        char[] indexedString = new char[4];
        for (int i = 0; i < vVin.length(); i++)
        {
            indexedString[i] = vVin.charAt(i);

        }
        if (Character.isUpperCase(indexedString[0]) && Character.isDigit(indexedString[1]) && Character.isDigit(indexedString[2]) && (Character.isDigit(indexedString[3])))
        {
            return true;
        }
        else
            return false;

    }//user inputs vin and method returns boolean based on validation

    public String toString()
    {
        String returnStatement = "VIN: " + this.VIN + " Type: " + this.Type + " Phone: " + this.ownerPhoneNumber;
        return returnStatement;
    }//returns formatted information of vehicle as string

    public int resetNextID()
    {
        int nullCount = 0;
        this.nextID = 0;
        for (int i = 0; i < this.maintenance.length; i++)
        {
            if (this.maintenance[i] == 0)
            {
                nullCount++;
            }
        }
        
        if (nullCount == 10)
        {
            return -1;
        }
        else 
            return 0;
    }//resets next ID data
}//end of vehicle class