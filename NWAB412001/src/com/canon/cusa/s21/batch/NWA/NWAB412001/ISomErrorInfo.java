package com.canon.cusa.s21.batch.NWA.NWAB412001;

import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;

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
public interface ISomErrorInfo {
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
     * @return List<NWAB412001ErrorInfo>
     */
    List<NWAB412001ErrorInfo> getErrorInfo();

    /**
     * hasError
     * @return boolean
     */
    boolean hasError();

}
