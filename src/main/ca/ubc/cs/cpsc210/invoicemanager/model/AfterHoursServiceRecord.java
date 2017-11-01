package ca.ubc.cs.cpsc210.invoicemanager.model;

public class AfterHoursServiceRecord extends AbstractServiceRecord {


    public static int AFTER_HOURS_CALLOUT = 120;
    public static int AFTER_HOURS_SERVICE_HOURLY = 100;
    public static int AFTER_HOURS_SERVICEPTS_BASE = 5;
    public static int AFTER_HOURS_SERVICEPTS_HOURLY = 1;

    public AfterHoursServiceRecord(ServiceType serviceType, int hours) {
        super(hours, serviceType);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }

    @Override
    public int getServicePoints() {
        int servicePoints = 0;
        servicePoints = AFTER_HOURS_SERVICEPTS_BASE + hours * AFTER_HOURS_SERVICEPTS_HOURLY;
        return servicePoints;
    }

    @Override
    public int getCalloutFee() {
        int calloutFee = 0;
        calloutFee = AFTER_HOURS_CALLOUT;
        return calloutFee;
    }

    @Override
    public int getServiceFee() {
        int serviceFee = 0;
        serviceFee = serviceFee = AFTER_HOURS_SERVICE_HOURLY * hours;
        return serviceFee;
    }
}
