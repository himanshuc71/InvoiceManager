package ca.ubc.cs.cpsc210.invoicemanager.model;

public class DiscountServiceRecord extends AbstractServiceRecord {
    public static int DIS_SERVICE_HOURLY = 80;

    public DiscountServiceRecord(ServiceType serviceType, int hours) {
        super(hours, serviceType);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }

    @Override
    public int getServicePoints() {
        return 0;
    }

    @Override
    public int getCalloutFee() {
        return 0;
    }

    @Override
    public int getServiceFee() {
        int serviceFee = 0;
        serviceFee = DiscountServiceRecord.DIS_SERVICE_HOURLY * hours;
        return serviceFee;
    }
}
