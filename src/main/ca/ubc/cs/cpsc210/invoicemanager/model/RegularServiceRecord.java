package ca.ubc.cs.cpsc210.invoicemanager.model;

public class RegularServiceRecord extends AbstractServiceRecord {

    public static int REG_CALLOUT = 80;
    public static int REG_SERVICE_HOURLY = 80;
    public static int REG_SERVICEPTS_BASE = 10;
    public static int REG_SERVICEPTS_HOURLY = 2;

    public RegularServiceRecord(ServiceType serviceType, int hours) {
        super(hours, serviceType);
        this.recordID = ++nextRecordID;
        buildInvoice();
    }


    @Override
    public int getServicePoints() {
        int servicePoints = 0;
        servicePoints = RegularServiceRecord.REG_SERVICEPTS_BASE + hours * RegularServiceRecord.REG_SERVICEPTS_HOURLY;
        return servicePoints;
    }

    @Override
    public int getCalloutFee() {
        int calloutFee = 0;
        calloutFee = RegularServiceRecord.REG_CALLOUT;
        return calloutFee;
    }

    @Override
    public int getServiceFee() {
        int serviceFee = 0;
        serviceFee = RegularServiceRecord.REG_SERVICE_HOURLY * hours;
        return serviceFee;
    }
}
