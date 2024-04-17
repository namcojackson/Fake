package business.blap.NWAL2320.bean;

import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public interface INWAL2320_ImptBean {
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
     * hasError
     * @return boolean
     */
    boolean hasError();

}
