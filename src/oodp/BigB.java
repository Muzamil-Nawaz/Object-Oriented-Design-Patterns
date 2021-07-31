package oodp;

import java.util.Random;

public class BigB implements Company {

    // Initializing a string variable for storing all transactions of company B
    public String outputLog = "**************************** Company B Transactions ****************************\n\n";

    // Initialzing 50 depots for company B
    public Depot[] depots = new Depot[50];

    BigB() {
        // When BigB object is initialized, create each depot with random statistics
        for (int i = 0; i < this.depots.length; i++) {
            this.depots[i] = new Depot();
        }
    }

    // Method for buying products from Company A
    public boolean buyFromA(BigA b) {
        // Loop through depots of company B and try each depot to trade with all depots of Company A
        for (int i = 0; i < this.depots.length; i++) {
            Depot depotB = this.depots[i];
            for (int j = 0; j < b.depots.length; j++) {
                Random r = new Random();
                // Checking what's the maximum vacancy in current depot's external stock of company B
                int max = depotB.MAX_EXTERNAL - depotB.getExternalProduct();

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
                    depotB.setExternalProduct(depotB.getExternalProduct() + num);
                }
            }
        }

        return true;
    }

    // Method for buying products from Company C
    public boolean buyFromC(BigC b) {
        // Loop through depots of company B and try each depot to trade with all depots of Company C
        for (int i = 0; i < this.depots.length; i++) {
            Depot depotA = this.depots[i];
            for (int j = 0; j < b.depots.length; j++) {
                Random r = new Random();
                // Checking what's the maximum vacancy in curret depot's external stock of company B
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
        return "Company[B]";
    }

}
