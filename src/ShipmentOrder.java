public abstract class ShipmentOrder  implements SummaryPrintable {

    private String orderNumber;
    private String customerName;
    private double distanceKm;
    private double baseFee;
    private boolean insured;
    private double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, double distanceKm,double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.isEmpty()) {
            throw new IllegalArgumentException("Order number cannot be empty.");
        }
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive.");
        }
    }

    protected void validateSpecificRules() {
    }

    private double applyInsurance(double price) {
        if (insured) {
            return price * 1.07;
        }
        return price;
    }

    protected double applyBusinessDiscount(double price) {
        return price;
    }

    private void printProcessingResult() {
        System.out.println("Processed order: " + orderNumber);
    }

    public String buildSummaryLine() {
        return orderNumber + " | " + customerName + " | " +
                getShipmentType() + " | Price: " +
                String.format("%.2f PLN", lastCalculatedPrice);
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public double getBaseFee() {
        return baseFee;
    }

    protected abstract double calculateBasePrice();

    protected abstract double calculateAdditionalFee();

    public abstract String getShipmentType();
}