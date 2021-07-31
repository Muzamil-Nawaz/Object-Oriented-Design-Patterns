package oodp;

import java.util.Random;

public class BigC implements Company {

    // Initializing a string variable for storing all transactions of company C
    public String outputLog = "**************************** Company C Transactions ****************************\n\n";
// Initialzing 50 depots for company A
    public Depot[] depots = new Depot[50];

    BigC() {
        // When BigC object is initialized, create each depot with random statistics
        for (int i = 0; i < depots.length; i++) {
            depots[i] = new Depot();
        }
    }

    // Method for buying products from Company A
    public boolean buyFromA(BigA b) {
        // Loop through depots of company C and try each depot to trade with all depots of Company A
        for (int i = 0; i < this.depots.length; i++) {
            Depot depotC = this.depots[i];
            for (int j = 0; j < b.depots.length; j++) {
                Random r = new Random();
                // Checking what's the maximum vacancy in current depot's external stock of company C
                int max = depotC.MAX_EXTERNAL - depotC.getExternalProduct();
                // Generate a random number in depot's external stock limit
                int num = r.nextInt(max);
                // if num=0, this might mean depot vacancy is full, so end the trade for current depot
                if (num == 0) {
                    outputLog += "Current depot foreign stock has reached it's limit.\n";
                    break;
                }
                // Getting the transaction message 
                String result = b.depots[j].sellNativeProduct(num);
                // Checking if above transaction was successful.
                if (!result.contains("can't trade for now")) {
                    // Adding transaction message to company's transaction records
                    outputLog += " " + this.toString() + " bought " + num + " native product from " + b.toString() + "\n";
                    // Updating depot's external stock after successful transaction
                    depotC.setExternalProduct(depotC.getExternalProduct() + num);
                }
            }
        }

        return true;
    }

    // Method for buying products from Company B
    public boolean buyFromB(BigB b) {
        // Loop through depots of company C and try each depot to trade with all depots of Company B
        for (int i = 0; i < this.depots.length; i++) {
            Depot depotA = this.depots[i];
            for (int j = 0; j < b.depots.length; j++) {
                Random r = new Random();
                // Checking what's the maximum vacancy in curret depot's external stock of company C
                int max = depotA.MAX_EXTERNAL - depotA.getExternalProduct();
                // Generate a random number in depot's external stock limit
                int num = r.nextInt(max);
                // if num=0, this might mean depot vacancy is full, so end the trade for current depot
                if (num == 0) {
                    outputLog += "Current depot foreign stock has reached it's limit.\n";
                    break;
                }
                // Getting the transaction message    
                String result = b.depots[j].sellNativeProduct(num);
                // Checking if above transaction was successful.
                if (!result.contains("can't trade for now")) {
                    // Adding transaction message to company's transaction records
                    outputLog += " " + this.toString() + " bought " + num + " native product from " + b.toString() + "\n";
                    // Updating depot's external stock after successful transaction
                    depotA.setExternalProduct(depotA.getExternalProduct() + num);
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Company[C]";
    }

}
