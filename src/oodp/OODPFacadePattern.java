package oodp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OODPFacadePattern {

    // Declaring scanner instance for processing user input
    static Scanner sc;

    // Declaring all type of company instances for dealing with operations regarding each company
    static BigA bigA;
    static BigB bigB;
    static BigC bigC;

    OODPFacadePattern() throws IOException {
        // Initializing scanner instance
        sc = new Scanner(System.in);

        /* Getting singleton object of different companies from CompanyFactory class, 
        which is using singleton pattern to avoid creation of new object each time*/
        bigA = (BigA) CompanyFactory.getCompany("biga");
        bigB = (BigB) CompanyFactory.getCompany("bigb");
        bigC = (BigC) CompanyFactory.getCompany("bigc");

        // Simulating operation of company A buying product from Company B
        bigA.buyFromB(bigB);

        // Simulating operation of company A buying product from Company C
        bigA.buyFromC(bigC);

        // Simulating operation of company B buying product from Company A
        bigB.buyFromA(bigA);

        // Simulating operation of company B buying product from Company C
        bigB.buyFromC(bigC);

        // Simulating operation of company C buying product from Company A
        bigC.buyFromA(bigA);

        // Simulating operation of company C buying product from Company B
        bigC.buyFromB(bigB);

        // Storing all the records to the file for later use.
        saveToFile();
        // Invoking the method display menu for user 
        displayMenu();
    }

    // Method to process menu representing different possible input choices for user
    private static void displayMenu() {
        // Integer variable for tracking user input
        int choice = -1;
        // Display the menu iteratively, until quit from inside
        while (true) {
            System.out.println("**************************** OODP System Menu ****************************");
            // Input option for displaying all the transaction done between all companies
            System.out.println("1- See all transactions");
            // Input option for displaying all the transaction done by any particular company
            System.out.println("\n2- See all transactions for a particular company");
            // Input option for displaying statistics of all depots of any particular company 
            System.out.println("\n3- For a given company, get detailed information about each depot's statistics");
            // For Qutting the program
            System.out.println("\n4- Quit the program");
            System.out.print("\n\nEnter your choice: ");
            choice = sc.nextInt();
            // Switch statements to map different input to different possible implementations
            switch (choice) {
                // If choice input is 1 go for it's body
                case 1:
                    // Method for displaying all the transaction done between all companies
                    seeAllTransactions();
                    break;
                // If choice input is 2 go for it's body
                case 2:
                    // Method for displaying all the transaction done by any particular company
                    seeAllTransactionsForSpecificCompany();
                    break;
                // If choice input is 3 go for it's body
                case 3:
                    // Method for displaying statistics of all depots of any particular company
                    getDetailedInfoAboutCompany();
                    break;
                // If choice input is 4, then exit the program
                case 4:
                    System.out.println("Thank you for using this program.");
                    System.exit(0);
            }
        }
    }

    private static void seeAllTransactions() {
        // Print all the transaction records for company A 
        System.out.println(bigA.outputLog);
        // Print all the transaction records for company B
        System.out.println(bigB.outputLog);
        // Print all the transaction records for company C
        System.out.println(bigC.outputLog);
        // Again display menu, if user want to chose other option
        displayMenu();
    }

    private static void seeAllTransactionsForSpecificCompany() {
        // Message for chosing company's name whose transaction user wants to see
        System.out.print("Enter name of company to see transactions :\nA- For company A\nB- For company B\nC- For company C\nEnter your choice: ");
        String str = sc.next();
        // If input is a, print Company A's transactions and go back to menu
        if (str.equalsIgnoreCase("a")) {
            System.out.println(bigA.outputLog);
            displayMenu();
        } // If input is b, print Company B's transactions and go back to menu
        else if (str.equalsIgnoreCase("b")) {
            System.out.println(bigB.outputLog);
            displayMenu();
        } // If input is c, print Company C's transactions and go back to menu
        else if (str.equalsIgnoreCase("c")) {
            System.out.println(bigC.outputLog);
            displayMenu();
        } // Otherwise just indicate invalid input and go back to menu
        else {
            System.out.println("Invalid input, returning to menu.");
            displayMenu();
        }

    }

    private static void getDetailedInfoAboutCompany() {
        // Message for chosing company's name whose depot statistics user want to see
        System.out.print("Enter name of company to see detailed info about depots :\nA- For company A\nB- For company B\nC- For company C\nEnter your choice: ");
        String str = sc.next();
        // If input is a, print Company C's depots' statistics and go back to menu
        if (str.equalsIgnoreCase("a")) {
            for (int i = 0; i < bigA.depots.length; i++) {
                System.out.println((i + 1) + "=>" + "Depot{" + "nativeProduct=" + bigA.depots[i].getNativeProduct() + ", externalProduct=" + bigA.depots[i].getExternalProduct() + ", Cash balance=" + bigA.depots[i].getAllowance() + "}");
            }
            displayMenu();
        } // If input is b, print Company C's depots' statistics and go back to menu
        else if (str.equalsIgnoreCase("b")) {
            for (int i = 0; i < bigB.depots.length; i++) {
                System.out.println((i + 1) + "=>" + "Depot{" + "nativeProduct=" + bigB.depots[i].getNativeProduct() + ", externalProduct=" + bigB.depots[i].getExternalProduct() + ", Cash balance=" + bigB.depots[i].getAllowance() + "}");
            }
            displayMenu();
        } // If input is c, print Company C's depots' statistics and go back to menu
        else if (str.equalsIgnoreCase("c")) {
            for (int i = 0; i < bigC.depots.length; i++) {
                System.out.println((i + 1) + "=>" + "Depot{" + "nativeProduct=" + bigC.depots[i].getNativeProduct() + ", externalProduct=" + bigC.depots[i].getExternalProduct() + ", Cash balance=" + bigC.depots[i].getAllowance() + "}");
            }
            displayMenu();
        } // Otherwise, indicate invalid input, and return menu
        else {
            System.out.println("Invalid input, returning to menu.");
            displayMenu();
        }
    }

    public static void saveToFile() throws IOException {
        File f = new File("output.txt");
        FileWriter fileWriter = new FileWriter(f);

        String finalOutput = "Company A Statistics :\n";
        for (int i = 0; i < bigA.depots.length; i++) {
            finalOutput += "\n" + (i + 1) + "=>" + "Depot{" + "nativeProduct=" + bigA.depots[i].getNativeProduct() + ", externalProduct=" + bigA.depots[i].getExternalProduct() + ", Cash balance=" + bigA.depots[i].getAllowance() + "}";
        }
        finalOutput += "\nCompany A Transactions :\n" + bigA.outputLog;
        finalOutput += "\n\nCompany B Statistics :\n";
        for (int i = 0; i < bigB.depots.length; i++) {
            finalOutput += "\n" + (i + 1) + "=>" + "Depot{" + "nativeProduct=" + bigB.depots[i].getNativeProduct() + ", externalProduct=" + bigB.depots[i].getExternalProduct() + ", Cash balance=" + bigB.depots[i].getAllowance() + "}";
        }

        finalOutput += "\nCompany B Transactions :\n" + bigB.outputLog;
        finalOutput += "\n\nCompany C Statistics :\n";
        for (int i = 0; i < bigC.depots.length; i++) {
            finalOutput += "\n" + (i + 1) + "=>" + "Depot{" + "nativeProduct=" + bigC.depots[i].getNativeProduct() + ", externalProduct=" + bigC.depots[i].getExternalProduct() + ", Cash balance=" + bigC.depots[i].getAllowance() + "}";
        }

        finalOutput += "\nCompany C Transactions :\n" + bigC.outputLog;
        fileWriter.write(finalOutput);
        fileWriter.close();

    }
}
