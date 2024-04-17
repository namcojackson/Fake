package business.blap.NSAL1060.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NSAL1060.NSAL1060CMsg;
import business.blap.NSAL1060.NSAL1060Query;
import business.blap.NSAL1060.NSAL1060SMsg;
import business.blap.NSAL1060.NSAL1060_ASMsg;
import business.blap.NSAL1060.constant.NSAL1060Constant;

/**
 * <pre>
 * Meter Reading Popup.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   Fujitsu         M.Hara          Create          N/A
 * 11/27/2015   Hitachi         T.Iwamoto       Update          QC#1235
 * 12/16/2016   Hitachi         K.Ochiai        Update          QC#16584
 * 2019/11/05   Hitachi         K.Kitachi       Update          QC#54164
 * </pre>
 */
public class NSAL1060CommonLogic {

    /**
     * @param cMsg NSAL1060CMsg
     * @param sMsg NSAL1060SMsg
     * @param glblCmpyCd String
     */
    public static void searchMtrReadList(NSAL1060CMsg cMsg, NSAL1060SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", cMsg.svcMachMstrPk.getValue());
        ssmParam.put("mtrReadDt", cMsg.xxFromDt.getValue());
        // START 2016/12/16 K.Ochiai [QC#16584, MOD]
//        ssmParam.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        ssmParam.put("vldMtrFlg", ZYPConstant.FLG_ON_Y);
        // END 2016/12/16 K.Ochiai [QC#16584, MOD]
        // START 2019/11/05 K.Kitachi [QC#54164, ADD]
        ssmParam.put("dsContrDtlStsCd", DS_CONTR_DTL_STS.CANCELLED);
        // END 2019/11/05 K.Kitachi [QC#54164, ADD]

        S21SsmEZDResult ssmResult = NSAL1060Query.getInstance().searchMtrReadList(ssmParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            copyCMsgFromSMsg(cMsg, sMsg);
        } else {
            cMsg.setMessageInfo(NSAL1060Constant.NZZM0000E);
        }
    }

    /**
     * copyCMsgFromSMsg
     * @param cMsg NSAL1060CMsg
     * @param sMsg NSAL1060SMsg
     */
    public static void copyCMsgFromSMsg(NSAL1060CMsg cMsg, NSAL1060SMsg sMsg) {
        LinkedHashMap<String, NSAL1060_ASMsg> mtrReadMap = new LinkedHashMap<String, NSAL1060_ASMsg>();
        String key;
        NSAL1060_ASMsg asMsg;
        int val;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            asMsg = sMsg.A.no(i);
            val = asMsg.svcPhysMtrPk_A1.getValueInt();
            // [QC#1235,MOD]START
            key = String.format("%s-%d", asMsg.svcPhysMtrReadGrpSq_A1.getValueInt(), val);
            // [QC#1235,MOD]END

            mtrReadMap.put(key, asMsg);
        }
        // [QC#1235,MOD]START
        int readGrpSq = 0;
        int cnt = 0;
        for (NSAL1060_ASMsg distAsMsg : mtrReadMap.values()) {
            if (!(ZYPCommonFunc.hasValue(cMsg.serNum)) && ZYPCommonFunc.hasValue(distAsMsg.serNum_A1)) {
                ZYPEZDItemValueSetter.setValue(cMsg.serNum, distAsMsg.serNum_A1);
            }

            EZDMsg.copy(distAsMsg, null, cMsg.A.no(cnt), null);

            if (readGrpSq == (distAsMsg.svcPhysMtrReadGrpSq_A1.getValueInt())) {
                cMsg.A.no(cnt).mtrReadDt_A1.clear();
            } else {
                readGrpSq = distAsMsg.svcPhysMtrReadGrpSq_A1.getValueInt();
        // [QC#1235,MOD]END
            }

            cnt++;
        }

        cMsg.A.setValidCount(cnt);
    }
}
