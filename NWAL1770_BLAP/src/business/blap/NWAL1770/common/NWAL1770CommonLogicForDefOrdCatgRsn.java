/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770.common;

import java.util.List;
import java.util.Map;

import business.blap.NWAL1770.NWAL1770CMsg;
import business.blap.NWAL1770.NWAL1770QueryForDefOrdCatgRsn;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/20   Hitachi         T.Fukuta        Create          CSA-QC#61467
 * </pre>
 */
public class NWAL1770CommonLogicForDefOrdCatgRsn {

    /**
     * Set Category/Reason Code Default Values
     * @param bizMsg NWAL1770CMsg
     * @param usrId User ID
     * @param roleIdList List of Role ID
     * @return set reason code or not
     */
    @SuppressWarnings("unchecked")
    public static boolean setCatgRsnDefaultValue(NWAL1770CMsg bizMsg, String usrId, List<String> roleIdList) {

        NWAL1770QueryForDefOrdCatgRsn query = NWAL1770QueryForDefOrdCatgRsn.getInstance();
        S21SsmEZDResult ssmResult1 = query.getDefOrdCatgRsn(bizMsg, usrId, roleIdList);
        if (ssmResult1.isCodeNotFound()) {
            return false;
        }

        // Always use 1st record
        List<Map<String, Object>> defOrdCatgRsnList = (List<Map<String, Object>>) ssmResult1.getResultObject();
        Map<String, Object> defOrdCatgRsn = defOrdCatgRsnList.get(0);

        String defCatgCd = (String)defOrdCatgRsn.get("DEF_CATG_CD");
        String defRsnCd = (String)defOrdCatgRsn.get("DEF_RSN_CD");
        S21SsmEZDResult ssmResult2 = query.getDsOrdCatg(bizMsg, defCatgCd);
        if (ssmResult2.isCodeNotFound()) {
            return false;
        }

        // Always use 1st record
        List<Map<String, Object>> dsOrdCatgList = (List<Map<String, Object>>) ssmResult2.getResultObject();
        Map<String, Object> dsOrdCatg = dsOrdCatgList.get(0);

        String dsOrdCatgCd = (String)dsOrdCatg.get("DS_ORD_CATG_CD");
        String dsOrdCatgDescTxt = (String)dsOrdCatg.get("DS_ORD_CATG_DESC_TXT");
        if (!S21StringUtil.isEmpty(dsOrdCatgCd) && !S21StringUtil.isEmpty(dsOrdCatgDescTxt)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteCatgCd, dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgDescTxt);

            // Refered to doProcess_NWAL1770Scrn00_OnBlur_DeriveFromCategory: start
            NWAL1770CommonLogicForCustomer.initProcSpecialInstructionForClear(bizMsg);

            NWAL1770CommonLogic.createRsnCdPulldown(bizMsg);
            bizMsg.dsOrdTpCd.clear();

            bizMsg.lineBizTpCd.clear();
            NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWAL1770CommonLogic.getBizAreaCd(bizMsg));
            // Refered to doProcess_NWAL1770Scrn00_OnBlur_DeriveFromCategory: end

            if (!S21StringUtil.isEmpty(defRsnCd)) {
                for (int i = 0; i < bizMsg.dsOrdTpCd_PL.length(); i++) {
                    String rsnCd = bizMsg.dsOrdTpCd_PL.no(i).getValue();
                    if (defRsnCd.equals(rsnCd)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, defRsnCd);
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
