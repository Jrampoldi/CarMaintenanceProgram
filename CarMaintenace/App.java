//Name: Jourdan Rampoldi
//Date: 12/14/2022
//Project: Final with vehicle class, maintenance class, and application

package CarMaintenance;
import java.util.Scanner;

public class App
{
    static Vehicle vehicles[] = new Vehicle[50];
    static int vehicleCount = 0;
    static Maintenance maintenanceLog[] = new Maintenance[50];
    static int maintenanceCount = 0;
    public static void main(String[] args)
    {
        //main code
        showMenu();
        int userResponse = verifyUserInput();
        while (userResponse != 8)
        {
            switch (userResponse)
            {
                case 1: addVehicle(); break;
                case 2: addInsurance(); break;
                case 3: addMaintenance(); break;
                case 4: modifyMaintenanceTime(); break;
                case 5: modifyMaintenanceCost(); break;
                case 6: listVehicles(); break;
                case 7: listVehicleRecord(); break;
                case 8: break;
            }
            showMenu();
            userResponse = verifyUserInput();
        } 
    }//end of main method

    public static void showMenu()
    {//displays user menu options
        System.out.println("*** Vehicle Maintenance System ***");
        System.out.printf("%28s\n", "1.  Add a new vehicle.");
        System.out.printf("%47s\n", "2.  Add insurance to an existing vehicle.");
        System.out.printf("%39s\n", "3.  Add a new maintenance record.");
        System.out.printf("%42s\n", "4.  Modify maintenance record times.");
        System.out.printf("%41s\n", "5.  Modify maintenance record cost.");
        System.out.printf("%28s\n", "6.  List all vehicles.");
        System.out.printf("%45s\n", "7.  List a vehicles maintenance record.");
        System.out.printf("%14s\n", "8.  Quit");
    }//end of show menu method

    public static int verifyUserInput()
    {//checks user input for menu selection
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter Option: ");
        int userResponse = userInput.nextInt();
        while (userResponse > 8 || userResponse < 1)
        {
            System.out.print("ERROR: Not a valid response. Please choose from options 1 - 8: ");
            userResponse = userInput.nextInt();
        }
        return userResponse;
    }//end of verify user input method

    public static void addVehicle()
    {//will allow user to create a vehicle object
        Scanner userInput = new Scanner(System.in);
        System.out.print("What is the vehicles VIN: ");
        String VIN = userInput.nextLine();
        System.out.print("What is the vehicle type: ");
        String vType = userInput.nextLine();
        System.out.print("What is the owner's phone number: ");
        String oPhone = userInput.nextLine();
        System.out.print("What is the vehicle insurance: ");
        String vInsurance = userInput.nextLine();
        //verifies vin
        if (Vehicle.verifyVIN(VIN))
        {
            vehicles[vehicleCount] = new Vehicle(VIN, vType, oPhone, vInsurance);
            vehicleCount++;
        }
    }//end of add vehicle method

    public static void addInsurance()
    {//allows user to modify insurance of vehicle object
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please enter the vehicles VIN: ");
        String VIN = userInput.nextLine();
        
        for (int i = 0; i < vehicles.length; i++)
        {
            if (vehicles[i] == null)
            {
                System.out.println("ERROR: Vehicle not found. Please try again or add new vehicle.");
                return;
            }
            else
            {
                if (vehicles[i].getVIN().equalsIgnoreCase(VIN))
                {
                    System.out.print("Please enter the vehicles insurance: ");
                    String insurance = userInput.nextLine();
                    vehicles[i].setInsurance(insurance);
                    return;
                }
            }
        }
    }//end of add insurance method

    public static void addMaintenance()
    {//allows user to create maintenance object
        
        Scanner userInput = new Scanner(System.in);
        
        //check for appropriate date entry
        System.out.print("Please enter maintenance date: ");
        String mDate = userInput.nextLine();
        while (mDate.charAt(2) != '/' && mDate.charAt(5) != '/' && mDate.length() != 10)
        {
            System.out.print("Date must be in format MM/DD/YYYY. Please re-enter maintenance date: ");
            mDate = userInput.nextLine();
        }

        System.out.print("Please enter description: ");
        String mDesc = userInput.nextLine();
        
        System.out.print("Please enter the start time: ");
        String sTime = userInput.nextLine();
        sTime = checkTime(sTime);        
        System.out.print("Please enter the end time: ");
        String eTime = userInput.nextLine();
        eTime = checkTime(eTime);
        
        System.out.print("Please enter the vehicle VIN: ");
        String VIN = userInput.nextLine();
        
        System.out.print("Please enter the cost per hour: ");
        double cost = userInput.nextDouble();
        
        //check if added successfully
        boolean added = false;
        maintenanceLog[maintenanceCount] = new Maintenance(mDate, mDesc, sTime, eTime, VIN, cost);
        for (int i = 0; i < vehicles.length; i++)
        {
            if (vehicles[i] != null){
                if (vehicles[i].getVIN().equalsIgnoreCase(VIN))
                {
                    added = vehicles[i].addMaintenance(maintenanceLog[maintenanceCount].getID());
                }
            }
        }
        if (added)
            System.out.println("Maintenance added successfully. ");
        else
            System.out.println("Not added successfully. Maintenance record full or vehicle does not exist. ");
        maintenanceCount++;

    }//end of maintenance add method

    public static void modifyMaintenanceTime()
    {//will allow user to modify Maintenance time
        Scanner userInput = new Scanner(System.in);
        listVehicleRecord();
        System.out.print("What is the mID: ");
        int maintenanceID = userInput.nextInt();
        userInput.nextLine();//clear input for next 
        
        for (int i = 0; i < maintenanceLog.length; i++)
        {
            if (maintenanceLog[i] != null)
            {
                if (maintenanceLog[i].getID() == maintenanceID)
                {
                    System.out.print("Input new startTime: ");
                    double sTime = userInput.nextDouble();
                    sTime = Double.parseDouble(checkTime("" + sTime));
                    System.out.print("Input new endTime: ");
                    double eTime = userInput.nextDouble();
                    eTime = Double.parseDouble(checkTime("" + eTime));
                    maintenanceLog[i].setStartTime(sTime);
                    maintenanceLog[i].setEndTime(eTime);
                }
            }
        }    
    }//end of modify maintenance time method

    public static void modifyMaintenanceCost()
    {//will allow user to modify cost of maintenance
        Scanner userInput = new Scanner(System.in);
        listVehicleRecord();
        System.out.print("What is the mID: ");
        int maintenanceID = userInput.nextInt();
        userInput.nextLine();//clear input 
        for (int i = 0; i < maintenanceLog.length; i++)
        {
            if (maintenanceLog[i] != null)
            {
                if (maintenanceLog[i].getID() == maintenanceID)
                {
                    System.out.print("Input the new cost: ");
                    double cost = userInput.nextDouble();
                    maintenanceLog[i].setCost(cost);
                }
            }
        }
    }//end of modify maintenance cost method

    public static void listVehicles()
    {
        for (int i = 0; i < vehicles.length; i++)
        {
            if (vehicles[i] != null)
            {
                System.out.println(vehicles[i].toString());
            }
        }
    }//end of list vehicles method

    public static void listVehicleRecord()
    {//will list all records for each vehicle by vin
        Scanner userInput = new Scanner(System.in);
        System.out.print("What is the VIN of the vehicle: ");
        String vVin = userInput.nextLine();
        for (int i = 0; i < vehicles.length; i++)
        {
            if (vehicles[i] != null)
            {
                if (vVin.equalsIgnoreCase(vehicles[i].getVIN()))
                {
                    System.out.println("The vehicle with VIN: " + vVin + " has the following: ");
                    int[] currentRecord = vehicles[i].getAllMaintenance();
                    for (int j = 0; j < currentRecord.length; j++)
                    {
                        if (currentRecord[j] != 0)
                        {
                            for (int k = 0; k < maintenanceLog.length; k++)
                            {
                                if (maintenanceLog[k] != null)
                                {
                                    if (maintenanceLog[k].getID() == currentRecord[j])
                                    {
                                        System.out.println(maintenanceLog[k].toString());
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        System.out.println();
    }//end of vehicle records method

    public static String checkTime(String time)
    {//method will ask user if time they entered is correct
        Scanner userInput = new Scanner(System.in);
        String userResponse = "";
        boolean timeCorrect = false;
        int hour = (int)Double.parseDouble(time);
        double minute = Double.parseDouble(time) % hour;
        int convertedMin = (int)(minute * 60);
        if (hour > 12)
        {
            while(!timeCorrect)
            {
                System.out.println("The time you entered is " + (hour - 12) + ":" + convertedMin + " PM");
                System.out.print("Is this the correct time? (y/n)");
                userResponse = userInput.nextLine();
                while (!(userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("N")))
                {
                    System.out.print("Please enter 'y' or 'n': ");
                    userResponse = userInput.nextLine();
                }
                if (userResponse.equalsIgnoreCase("Y"))
                    timeCorrect = true;
                else
                {
                    timeCorrect = false;
                    System.out.print("Please re-enter the time: ");
                    time = userInput.nextLine();
                    hour = (int)Double.parseDouble(time);
                    minute = Double.parseDouble(time) % hour;
                    convertedMin = (int)(minute * 60);
                }
            }
        }
        else
        {
            while(!timeCorrect)
            {
                System.out.println("The time you entered is " + hour + ":" + convertedMin + " AM");
                System.out.print("Is this the correct time? (y/n)");
                userResponse = userInput.nextLine();
                while (!(userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("N")))
                {
                    System.out.print("Please enter 'y' or 'n': ");
                    userResponse = userInput.nextLine();
                }
                if (userResponse.equalsIgnoreCase("Y"))
                    timeCorrect = true;
                else
                {
                    timeCorrect = false;
                    System.out.print("Please re-enter the time: ");
                    time = userInput.nextLine();
                    hour = (int)Double.parseDouble(time);
                    minute = Double.parseDouble(time) % hour;
                    convertedMin = (int)(minute * 60);
                }
            }
        }
        return time;
    }//end of check time function
}//end of application class
