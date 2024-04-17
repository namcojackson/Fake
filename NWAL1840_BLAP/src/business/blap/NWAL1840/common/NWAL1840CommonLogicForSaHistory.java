package business.blap.NWAL1840.common;

import java.util.List;
import java.util.Map;

import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840QueryForSaHistory;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/10   Hitachi         T.Fukuta        Create          CSA-QC#61730
 * </pre>
 */
public class NWAL1840CommonLogicForSaHistory {

    /**
     * Search SA History
     * @param bizMsg NWAL1840CMsg
     */
    @SuppressWarnings("unchecked")
    public static void searchSaHistory(NWAL1840CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.H);

        if (!ZYPCommonFunc.hasValue(bizMsg.serNum)) {
            return;
        }

        NWAL1840QueryForSaHistory query = NWAL1840QueryForSaHistory.getInstance();

        S21SsmEZDResult ssmResult = query.getSaHistory(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, Object>> histList = (List<Map<String, Object>>) ssmResult.getResultObject();
        int validCount = 0;
        for (Map<String, Object> hist : histList) {
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).schdAgmtNum_H, (String)hist.get("SCHD_AGMT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).serNum_H, (String)hist.get("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).dsContrNum_H, (String)hist.get("DS_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).locNm_H, (String)hist.get("LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).schdAgmtStsNm_H, (String)hist.get("SCHD_AGMT_STS_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).schdAgmtCratDt_H, (String)hist.get("SCHD_AGMT_CRAT_DT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).schdAgmtVldFromDt_H, (String)hist.get("SCHD_AGMT_VLD_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(validCount).schdAgmtVldThruDt_H, (String)hist.get("SCHD_AGMT_VLD_THRU_DT"));
            validCount++;
        }
        bizMsg.H.setValidCount(validCount);
    }
}
