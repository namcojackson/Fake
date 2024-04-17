package com.canon.cusa.s21.batch.NWA.NWAB221001;

import java.util.List;

/** <pre>
 * IImportBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public interface IImportBean {
    public List<DsImptOrdErrBean> getDsImptOrdErrList();

    public boolean hasError(boolean doSearchChild);

    public DsImptOrdConfigBean getDsImptOrdConfigBean();

    public boolean isSameErrorExists(DsImptOrdErrBean dsImptOrdErrBean);
}
