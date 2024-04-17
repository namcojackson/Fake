/*
 * <pre>Copyright (c) 2014 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import business.db.FSR_EVENTTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Response Time
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2014/03/12   Fujitsu         S.Takami        Create          S21_NA Defect#0099
 *</pre>
 */

/**
 * This class implements update FSR_EVENT Table methods
 * @author q05163
 *
 */
public class NSXC001001UpdateFsrEvent {
    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001UpdateFsrEvent.class);

    /**
     * Updated FSR_EVENT which is (are) directed fsrNum, fsrVisitNum as "Processed"
     * @param glblCmpyCd Global Company Code
     * @param fsrNum FSR Number
     * @param fsrVisitNum FSR Visit Number
     * @return updated FSR_EVENT record numbers.
     */
    public static int updateFsrEventToProcessed(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        Map<String, String> queryPrm = new HashMap<String, String>();
        queryPrm.put("glblCmpyCd", glblCmpyCd);
        queryPrm.put("fsrNum", fsrNum);
        queryPrm.put("fsrVisitNum", fsrVisitNum);
        queryPrm.put("notProcessed", MBL_INTFC_PROC.NOT_PROCESSED);

        List<Map<String, Object>> fsrEventList = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getUpdateFsrEventList", queryPrm);

        int upObjCnt = 0;
        for (Map<String, Object>fsrEvent : fsrEventList) {
            String dataGlblCmpyCd = (String) fsrEvent.get("GLBL_CMPY_CD");
            BigDecimal fsrEventPk = (BigDecimal) fsrEvent.get("FSR_EVENT_PK");

            FSR_EVENTTMsg fsrEventTMsgKey = new FSR_EVENTTMsg();
            fsrEventTMsgKey.glblCmpyCd.setValue(dataGlblCmpyCd);
            fsrEventTMsgKey.fsrEventPk.setValue(fsrEventPk);

            FSR_EVENTTMsg fsrEventTMsg = (FSR_EVENTTMsg) S21FastTBLAccessor.findByKeyForUpdate(fsrEventTMsgKey);

            if (null != fsrEventTMsg) {
                fsrEventTMsg.mblIntfcProcCd.setValue(MBL_INTFC_PROC.PROCESSED);
                S21FastTBLAccessor.update(fsrEventTMsg);
                upObjCnt++;
            }
        }
        return upObjCnt;
    }
}
