/**
 * <Pre>Copyright (c) 2016-2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC226001;

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
