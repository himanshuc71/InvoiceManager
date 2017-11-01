package ca.ubc.cs.cpsc210.invoicemanager.tests;

import ca.ubc.cs.cpsc210.invoicemanager.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit tests for the Customer class
public class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    public void runBefore() {
        testCustomer = new Customer("Test Customer");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Customer", testCustomer.getName());
        assertEquals(0, testCustomer.getServicePoints());
        assertEquals(0, testCustomer.getServiceRecords().size());
        assertTrue(testCustomer.isInGoodStanding());
    }

    @Test
    public void testAddServiceRecord() {
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);

        assertEquals(1, testCustomer.getServiceRecords().size());
        assertEquals(AbstractServiceRecord.REG_SERVICEPTS_BASE + AbstractServiceRecord.REG_SERVICEPTS_HOURLY,
                testCustomer.getServicePoints());
    }

    @Test
    public void testAddServiceRecords() {
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);
        testCustomer.addServiceRecord(ServiceType.AFTER_HOURS, 1);
        testCustomer.addServiceRecord(ServiceType.EMERGENCY, 1);

        assertEquals(3, testCustomer.getServiceRecords().size());
        assertEquals(AbstractServiceRecord.REG_SERVICEPTS_BASE + AbstractServiceRecord.REG_SERVICEPTS_HOURLY
                        + AbstractServiceRecord.AFTER_HOURS_SERVICEPTS_BASE + AbstractServiceRecord.AFTER_HOURS_SERVICEPTS_HOURLY
                        + AbstractServiceRecord.EMERG_SERVICEPTS_BASE + AbstractServiceRecord.EMERG_SERVICEPTS_HOURLY,
                testCustomer.getServicePoints());
    }

    @Test
    public void testIsInGoodStandingNonePaidInFull() {
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);

        assertFalse(testCustomer.isInGoodStanding());
    }

    @Test
    public void testIsInGoodStandingNotAllPaidInFull() {
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);

        AbstractServiceRecord firstRecord = testCustomer.getServiceRecords().first();
        Invoice firstRecordInvoice = firstRecord.getInvoice();
        firstRecordInvoice.makePayment(firstRecordInvoice.getAmountOwing());

        assertFalse(testCustomer.isInGoodStanding());
    }

    @Test
    public void testIsInGoodStandingAllPaidInFull() {
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);
        testCustomer.addServiceRecord(ServiceType.REGULAR, 1);

        for (AbstractServiceRecord next : testCustomer.getServiceRecords()) {
            Invoice nextInvoice = next.getInvoice();
            nextInvoice.makePayment(nextInvoice.getAmountOwing());
        }

        assertTrue(testCustomer.isInGoodStanding());
    }
}