package ca.ubc.cs.cpsc210.invoicemanager.tests;

import ca.ubc.cs.cpsc210.invoicemanager.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Unit tests for ServiceRecord
public class ServiceRecordTest {
    private AbstractServiceRecord testServiceRecord;

    @Test
    public void testRegular() {
        testServiceRecord = AbstractServiceRecord.createServiceRecord(ServiceType.REGULAR, 2);

        assertEquals(RegularServiceRecord.REG_SERVICEPTS_BASE + 2 * RegularServiceRecord.REG_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = RegularServiceRecord.REG_CALLOUT;
        int serviceFee = RegularServiceRecord.REG_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }

    @Test
    public void testAfterHours() {
        testServiceRecord = AbstractServiceRecord.createServiceRecord(ServiceType.AFTER_HOURS, 2);

        assertEquals(AfterHoursServiceRecord.AFTER_HOURS_SERVICEPTS_BASE + 2 * AfterHoursServiceRecord.AFTER_HOURS_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = AfterHoursServiceRecord.AFTER_HOURS_CALLOUT;
        int serviceFee = AfterHoursServiceRecord.AFTER_HOURS_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }

    @Test
    public void testEmergency() {
        testServiceRecord = AbstractServiceRecord.createServiceRecord(ServiceType.EMERGENCY, 2);

        assertEquals(EmergencyServiceRecord.EMERG_SERVICEPTS_BASE + 2 * EmergencyServiceRecord.EMERG_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = EmergencyServiceRecord.EMERG_CALLOUT;
        int serviceFee = EmergencyServiceRecord.EMERG_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }

    @Test
    public void testDiscount() {
        testServiceRecord = AbstractServiceRecord.createServiceRecord(ServiceType.DISCOUNT, 2);

        assertEquals(0, testServiceRecord.getServicePoints());
        assertEquals(0, testServiceRecord.getCalloutFee());
        assertEquals(DiscountServiceRecord.DIS_SERVICE_HOURLY * 2, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(0 + DiscountServiceRecord.DIS_SERVICE_HOURLY * 2, invoice.getAmountOwing());
    }
}