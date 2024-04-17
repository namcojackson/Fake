package com.canon.cusa.s21.batch.NWA.NWAB265001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;

/** <pre>
 * EdiOrdImptBaseBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class EdiOrdImptBaseBean {

    final public Map<String, List<EZDTMsg>> intfcTableMap;

    final public List<NWAB265001ErrorInfo> errorInfoList;

    public EdiOrdImptBaseBean() {
        this.intfcTableMap = new HashMap<String, List<EZDTMsg>>();
        this.errorInfoList = new ArrayList<NWAB265001ErrorInfo>();
    }

    public void addInterfaceTMsgList(String tableNum, List<EZDTMsg> itfcTMsgList) {
        if (itfcTMsgList != null) {
            this.intfcTableMap.put(tableNum, itfcTMsgList);
            //this.intfcTableMap.put(itfcTMsgList.get(0).getTableName(), itfcTMsgList);
        }
    }

    public boolean hasError() {
        return (this.errorInfoList.size() > 0);
    }

    public String getOrdSrcRefNum() {
        return "";
    }

}
