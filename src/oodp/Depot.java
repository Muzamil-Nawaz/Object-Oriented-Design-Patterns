package oodp;

import java.util.Random;

public class Depot {

    Random r;
    String outputString = "";

    int nativeProduct;
    int externalProduct;
    int allowance;
    int deliveryPrice;
    int productPrice;
    final int MIN_NATIVE = 15;
    final int MAX_NATIVE = 50;
    final int MIN_EXTERNAL = 3;
    final int MAX_EXTERNAL = 40;

    public Depot() {
        r = new Random();

        // Initializing native product stock randomly within it's minimal and maximum limits
        this.nativeProduct = r.nextInt(MAX_NATIVE - MIN_NATIVE) + MIN_NATIVE;

        // Initializing external product stock randomly within it's minimal and maximum limits
        this.externalProduct = r.nextInt(MAX_EXTERNAL - MIN_EXTERNAL) + MIN_EXTERNAL;

        // Initializing allowance randomly within 50 and 100 limits
        this.allowance = r.nextInt(100 - 50) + 50;

        // Initializing delivery price randomly within 1 and 10 limits
        this.deliveryPrice = r.nextInt(10 - 1) + 1;

        // Initializing product price randomly within 1 and 10 limits
        this.productPrice = r.nextInt(10 - 1) + 1;
    }

    public int getNativeProduct() {
        return nativeProduct;
    }

    public void setNativeProduct(int nativeProduct) {
        this.nativeProduct = nativeProduct;
    }

    public int getExternalProduct() {
        return externalProduct;
    }

    public void setExternalProduct(int externalProduct) {
        this.externalProduct = externalProduct;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    // Method for simulating transaction of selling native product of depot of any company
    String sellNativeProduct(int quantity) {
        // if required quantity is greater than availabe stock of native product then display a message and and cancel transaction
        if (quantity > nativeProduct) {
            return " Required quantity is above available stock, can't trade for now.\n";

        } // if required quantity will affect stock of native product to be less than minimum stock then display a message and cancel transaction
        else if (nativeProduct - quantity < MIN_NATIVE) {
            return " Desired quantity exceeds the tradeable stock, can't trade for now.\n";

        }
        // Add transaction amount to the company's allowance
        setAllowance(getAllowance() + quantity * productPrice);
        // Update amount of stock of native product
        setNativeProduct(getNativeProduct() - quantity);

        return "Trade has been successful for " + quantity + " native products\n";
    }

    // Method for getting estimation cost of buying an amount of product to check if company have enough cash allowance
    int getEstimation(int quantity) {
        // return estimated cost with additional delivery price
        return quantity * productPrice + deliveryPrice;
    }

    @Override
    public String toString() {
        return "Depot{" + "nativeProduct=" + nativeProduct + ", externalProduct=" + externalProduct + ", allowance=" + allowance + ", deliveryPrice=" + deliveryPrice + ", productPrice=" + productPrice + ", MIN_NATIVE=" + MIN_NATIVE + ", MAX_NATIVE=" + MAX_NATIVE + ", MIN_EXTERNAL=" + MIN_EXTERNAL + ", MAX_EXTERNAL=" + MAX_EXTERNAL + '}';
    }

}
