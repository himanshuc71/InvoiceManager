package ca.ubc.cs.cpsc210.invoicemanager.model;

public abstract class AbstractServiceRecord implements Comparable<AbstractServiceRecord> {
    public static int REG_CALLOUT = 80;
    public static int REG_SERVICE_HOURLY = 80;
    public static int REG_SERVICEPTS_BASE = 10;
    public static int REG_SERVICEPTS_HOURLY = 2;
    public static int AFTER_HOURS_CALLOUT = 120;
    public static int AFTER_HOURS_SERVICE_HOURLY = 100;
    public static int AFTER_HOURS_SERVICEPTS_BASE = 5;
    public static int AFTER_HOURS_SERVICEPTS_HOURLY = 1;
    public static int EMERG_CALLOUT = 150;
    public static int EMERG_SERVICE_HOURLY = 100;
    public static int EMERG_SERVICEPTS_BASE = 0;
    public static int EMERG_SERVICEPTS_HOURLY = 0;
    protected static int nextRecordID = 0;
    protected ServiceType serviceType;
    protected int hours;
    protected int recordID;
    private Invoice invoice;

    public AbstractServiceRecord(int hours, ServiceType serviceType) {
        this.hours = hours;
        this.serviceType = serviceType;
    }

    public static AbstractServiceRecord createServiceRecord(ServiceType serviceType, int hours) {
        switch(serviceType) {
            case REGULAR:
            case AFTER_HOURS:
            case EMERGENCY:
                return new ServiceRecord(serviceType, hours);
        }
        return null;  // shouldn't get here
    }

    public int getRecordID() {
        return recordID;
    }

    public int getHours() {
        return hours;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    // EFFECTS: returns number of service points earned with this service record
    public abstract int getServicePoints();

    // EFFECTS: returns callout fee in $ for this service record
    public abstract int getCalloutFee();

    // EFFECTS: returns service fee in $ for this service record
    public abstract int getServiceFee();

    @Override
    // NOTE: this class has a natural ordering that is inconsistent with equals()
    public int compareTo(AbstractServiceRecord other) {
        return recordID - other.recordID;
    }

    // MODIFIES: this
    // EFFECTS:  create invoice for this service record
    protected void buildInvoice() {
        invoice = new Invoice(recordID, getCalloutFee(), getServiceFee(), hours);
    }
}
