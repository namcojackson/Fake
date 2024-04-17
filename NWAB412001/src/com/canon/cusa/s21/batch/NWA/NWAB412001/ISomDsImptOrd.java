package com.canon.cusa.s21.batch.NWA.NWAB412001;

/**
 * <pre>
 * SOM Quote Interface to S21 Import Data Batch
 * ISomErrorInfo
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 08/25/2016   FUJITSU         M.Hara          CREATE          NEW
 *</pre>
 */
public interface ISomDsImptOrd {
    public boolean doImptMapping(String glblCmpyCd, String salesDate);
}
