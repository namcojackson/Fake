package business.blap.NMAL7440;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * NMAL7440BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/05   Fujitsu         T.Noguchi       Create          QC#29324
 *</pre>
 */
public class NMAL7440BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7440CMsg bizMsg = (NMAL7440CMsg) cMsg;
            NMAL7440SMsg glblMsg = (NMAL7440SMsg) sMsg;

            if ("NMAL7440_INIT".equals(screenAplID)) {
                doProcess_NMAL7440_INIT(bizMsg, glblMsg);

            } else if ("NMAL7440Scrn00_OnChange_TargetContext".equals(screenAplID)) {
                doProcess_NMAL7440Scrn00_OnChange_TargetContext(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7440_INIT(NMAL7440CMsg bizMsg, NMAL7440SMsg glblMsg) {
        createPullDownTargetContext(bizMsg);
        createPullDownTargetAttributeName(bizMsg);
    }

    /**
     * OnChange_TargetContext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7440Scrn00_OnChange_TargetContext(NMAL7440CMsg bizMsg, NMAL7440SMsg glblMsg) {
        bizMsg.prcGrpTrgtAttrbCd.clear();
        createPullDownTargetAttributeName(bizMsg);
    }
    
    /**
     * Create PullDown Target Context
     * @param bizMsg Business Msg
     */
    private void createPullDownTargetContext(NMAL7440CMsg bizMsg) {

        bizMsg.prcGrpTrgtTpCd_P.clear();
        bizMsg.prcGrpTrgtTpDescTxt_P.clear();

        S21SsmEZDResult ssmResult = NMAL7440Query.getInstance().getPrcGrpTrgtTp(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int i = 0; i < ssmResult.getQueryResultCount(); i++) {

                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrgtTpCd_P.no(i), (String) resultMap.get("PRC_GRP_TRGT_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrgtTpDescTxt_P.no(i), (String) resultMap.get("PRC_GRP_TRGT_TP_DESC_TXT"));
            }
        }
    }

    /**
     * Create PullDown Target Attribute Name
     * @param bizMsg Business Msg
     */
    private void createPullDownTargetAttributeName(NMAL7440CMsg bizMsg) {

        bizMsg.prcGrpTrgtAttrbCd_P.clear();
        bizMsg.prcGrpTrgtAttrbDescTxt_P.clear();

        S21SsmEZDResult ssmResult = NMAL7440Query.getInstance().getPrcGrpAttrb(bizMsg);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int i = 0; i < ssmResult.getQueryResultCount(); i++) {

                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrgtAttrbCd_P.no(i), (String) resultMap.get("PRC_GRP_TRGT_ATTRB_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcGrpTrgtAttrbDescTxt_P.no(i), (String) resultMap.get("PRC_GRP_TRGT_ATTRB_DESC_TXT"));
            }
        }
    }

}
