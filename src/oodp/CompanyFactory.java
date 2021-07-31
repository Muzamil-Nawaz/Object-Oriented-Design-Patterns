package oodp;

public class CompanyFactory {

    // Initializing each company's object single time to further implement the singleton design pattern
    static BigA bigA = null;
    static BigB bigB = null;
    static BigC bigC = null;

    // Making constructor private to restrict class to be instantiated outside 
    private CompanyFactory() {
    }

    // Method to get object of any specific company
    public static Company getCompany(String name) {
        // If passed company name is null, return null
        if (name == null) {
            return null;
        } // If passed company name is bigA, return biga object
        else if (name.equalsIgnoreCase("BigA")) {
            // If biga object is already not created, create new one
            if (bigA == null) {
                bigA = new BigA();
                return bigA;
            }
            return bigA;
        } else if (name.equalsIgnoreCase("BigB")) {
            if (bigB == null) {
                bigB = new BigB();
                return bigB;
            }
            return bigB;
        } else if (name.equalsIgnoreCase("BigC")) {
            if (bigC == null) {
                bigC = new BigC();
                return bigC;
            }
            return bigC;
        }
        // Otherwise, return null for any other input
        return null;
    }
}
