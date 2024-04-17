package com.canon.cusa.s21.api.NWZ.NWZC229001;


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * <pre>
 *  eCheck Interface API.
 * This class defines the constant used in the Test Module application
 * program of BusinessID NWZC229001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/19/2023   Hitachi         M.Hashino       Create          QC#55645
 * </pre>
 */

public class PaymentechTimestamp {
    /** the singleton instance of this class. */
    private static PaymentechTimestamp  instance;
    private String                              retryTraceValue = null;
    private long                                retryTrace = 0l;
    private long                                orderID = 0l;

    /**
     * Private constructor. This constructor looks up the MDM data source in JNDI
     * under the key ogcMDMPool.
     *
     */
    private PaymentechTimestamp() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.retryTraceValue = Long.toString(gregorianCalendar.getTimeInMillis());
        this.retryTrace = (long) gregorianCalendar.getTimeInMillis();
        String order =
            Integer.toString(gregorianCalendar.get(Calendar.YEAR))
            + Integer.toString(gregorianCalendar.get(Calendar.MONTH))
            + Integer.toString(gregorianCalendar.get(Calendar.DAY_OF_MONTH))
            + Integer.toString(gregorianCalendar.get(Calendar.MINUTE))
            + Integer.toString(gregorianCalendar.get(Calendar.SECOND));
        orderID = Long.parseLong(order);
        }

    public static synchronized PaymentechTimestamp getInstance() {
        if (PaymentechTimestamp.instance == null) {
            PaymentechTimestamp.instance = new PaymentechTimestamp();
            }
        return PaymentechTimestamp.instance;
        }

    public String getRetryTraceValue() {
        return this.retryTraceValue;
        }

    public long getNextRetryTrace() {
        return retryTrace++;
        }
    public long getOrderID() {
        return this.orderID;
        }
    public long getNextOrderID() {
        return orderID++;
    }
}
