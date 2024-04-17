package com.canon.cusa.s21.batch.NWA.NWAB415001;

import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;

/**
 * <pre>
 * EOPS Interface to S21 Import Data Batch
 * IEopsErrorInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/12   Fujitsu         M.Yamada        Create          QC#18798(L3#173)
 *</pre>
 */
public interface IEopsErrorInfo {
    /**
     * addErrorInfo
     * @param errorInfo NWXC220001ErrorInfo
     */
    void addErrorInfo(NWXC220001ErrorInfo errorInfo);

    /**
     * addErrorInfo
     * @param errorInfoList List<NWXC220001ErrorInfo>
     */
    void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList);

    /**
     * getErrorInfo
     * @return List<NWAB415001ErrorInfo>
     */
    List<NWAB415001ErrorInfo> getErrorInfo();

    /**
     * hasError
     * @return boolean
     */
    boolean hasError();

}
