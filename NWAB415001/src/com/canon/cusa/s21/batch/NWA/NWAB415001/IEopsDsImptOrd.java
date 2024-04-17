package com.canon.cusa.s21.batch.NWA.NWAB415001;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * IEopsDsImptOrd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public interface IEopsDsImptOrd {
    /**
     * doImptMapping
     * @param glblCmpyCd global company code
     * @param salesDate sales date
     * @return boolean
     */
    public boolean doImptMapping(String glblCmpyCd, String salesDate);
}
