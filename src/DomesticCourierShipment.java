public class DomesticCourierShipment extends ShipmentOrder {

    private double packageWeightKg;
    private boolean weekendDelivery;

    public DomesticCourierShipment(String orderNumber, String customerName,double distanceKm, double baseFee, boolean insured,double packageWeightKg, boolean weekendDelivery) {

        super(orderNumber, customerName, distanceKm, baseFee, insured);

        this.packageWeightKg = packageWeightKg;
        this.weekendDelivery = weekendDelivery;

    }

    public String getShipmentType() {
        return "Domestic courier";
    }

    protected double calculateBasePrice() {
        return getBaseFee() + getDistanceKm() * 1.20;
    }

    protected double calculateAdditionalFee() {
        double fee = packageWeightKg * 4.00;

        if (weekendDelivery) {
            fee += 25;
        }
        return fee;
    }

    protected double applyBusinessDiscount(double price) {

        if (getDistanceKm() >= 300) {
            return price * 0.95;
        }

         price;
    }
}