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

        assertEquals(AbstractServiceRecord.AFTER_HOURS_SERVICEPTS_BASE + 2 * AbstractServiceRecord.AFTER_HOURS_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = AbstractServiceRecord.AFTER_HOURS_CALLOUT;
        int serviceFee = AbstractServiceRecord.AFTER_HOURS_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }

    @Test
    public void testEmergency() {
        testServiceRecord = AbstractServiceRecord.createServiceRecord(ServiceType.EMERGENCY, 2);

        assertEquals(AbstractServiceRecord.EMERG_SERVICEPTS_BASE + 2 * AbstractServiceRecord.EMERG_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = AbstractServiceRecord.EMERG_CALLOUT;
        int serviceFee = AbstractServiceRecord.EMERG_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }
}