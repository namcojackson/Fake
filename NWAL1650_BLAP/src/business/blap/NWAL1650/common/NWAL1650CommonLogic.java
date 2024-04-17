/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1650.common;

import static business.blap.NWAL1650.constant.NWAL1650Constant.BLLG_ATTRB_NM;
import static business.blap.NWAL1650.constant.NWAL1650Constant.BLLG_ATTRB_VAL_TXT;
import static business.blap.NWAL1650.constant.NWAL1650Constant.NZZM0000E;

import java.util.List;
import java.util.Map;

import business.blap.NWAL1650.NWAL1650CMsg;
import business.blap.NWAL1650.NWAL1650Query;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC610001_xxMsgIdListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1650CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         M.Yamada        Create          N/A
 * 2015/12/03   Fujitsu         Y.Kanefusa      Update          #1309
 * 2018/05/23   Fujitsu         H.Tomimatsu     Update          #26145
 *</pre>
 */
public class NWAL1650CommonLogic {

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param bizMsg        NWAL1650CMsg
     * </pre>
     */
    public static void setBizMsg(String glblCmpyCd, NWAL1650CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, bizMsg.cpoOrdNum_P);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlLineNum //
                , NWXC150001DsCheck.editDtlLineNum(bizMsg.dsOrdPosnNum_P.getValue(), bizMsg.dsCpoLineNum_P.getValue(), bizMsg.dsCpoLineSubNum_P.getValue()));

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_P)
             // 2018/05/23 S21_NA#26145 Add Start
                || (ZYPCommonFunc.hasValue(bizMsg.bllgAttrbNm_P1))
             // 2018/05/23 S21_NA#26145 Add End
        ) {
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_1, bizMsg.bllgAttrbNm_P1);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_2, bizMsg.bllgAttrbNm_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_3, bizMsg.bllgAttrbNm_P3);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_4, bizMsg.bllgAttrbNm_P4);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_5, bizMsg.bllgAttrbNm_P5);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_6, bizMsg.bllgAttrbNm_P6);

            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_1, bizMsg.bllgAttrbValTxt_P1);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_2, bizMsg.bllgAttrbValTxt_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_3, bizMsg.bllgAttrbValTxt_P3);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_4, bizMsg.bllgAttrbValTxt_P4);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_5, bizMsg.bllgAttrbValTxt_P5);
            ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_6, bizMsg.bllgAttrbValTxt_P6);

            return;
        }

        String dsAcctNum = NWXC150001DsCheck.getDefaultAcctFromShipToCust(glblCmpyCd, bizMsg.shipToCustCd_P.getValue());
        S21SsmEZDResult rslt = NWAL1650Query.getInstance().getDsAcctRefAttrb(glblCmpyCd, dsAcctNum);
        if (rslt == null || rslt.isCodeNotFound()) {
            rslt = getRelationInfo(glblCmpyCd, bizMsg, dsAcctNum);
            if (rslt == null || rslt.isCodeNotFound()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }
        // MOD START 2015/12/03 #1309
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_P, dsAcctNum);
        // MOD END 2015/12/03 #1309
        }
        @SuppressWarnings("unchecked")
        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        bizMsg.bllgAttrbNm_1.clear();
        bizMsg.bllgAttrbValTxt_1.clear();
        bizMsg.bllgAttrbNm_2.clear();
        bizMsg.bllgAttrbValTxt_2.clear();
        bizMsg.bllgAttrbNm_3.clear();
        bizMsg.bllgAttrbValTxt_3.clear();
        bizMsg.bllgAttrbNm_4.clear();
        bizMsg.bllgAttrbValTxt_4.clear();
        bizMsg.bllgAttrbNm_5.clear();
        bizMsg.bllgAttrbValTxt_5.clear();
        bizMsg.bllgAttrbNm_6.clear();
        bizMsg.bllgAttrbValTxt_6.clear();

        bizMsg.bllgAttrbNm_P1.clear();
        bizMsg.bllgAttrbValTxt_P1.clear();
        bizMsg.bllgAttrbNm_P2.clear();
        bizMsg.bllgAttrbValTxt_P2.clear();
        bizMsg.bllgAttrbNm_P3.clear();
        bizMsg.bllgAttrbValTxt_P3.clear();
        bizMsg.bllgAttrbNm_P4.clear();
        bizMsg.bllgAttrbValTxt_P4.clear();
        bizMsg.bllgAttrbNm_P5.clear();
        bizMsg.bllgAttrbValTxt_P5.clear();
        bizMsg.bllgAttrbNm_P6.clear();
        bizMsg.bllgAttrbValTxt_P6.clear();

        int ixMap = 0;
        Map<String, String> rsltMap = rsltList.get(ixMap++);
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_1, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_1, rsltMap.get(BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_P1, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_P1, rsltMap.get(BLLG_ATTRB_VAL_TXT));

        if (ixMap >= rsltList.size()) {
            return;
        }
        rsltMap = rsltList.get(ixMap++);
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_2, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_2, rsltMap.get(BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_P2, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_P2, rsltMap.get(BLLG_ATTRB_VAL_TXT));

        if (ixMap >= rsltList.size()) {
            return;
        }
        rsltMap = rsltList.get(ixMap++);
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_3, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_3, rsltMap.get(BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_P3, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_P3, rsltMap.get(BLLG_ATTRB_VAL_TXT));

        if (ixMap >= rsltList.size()) {
            return;
        }
        rsltMap = rsltList.get(ixMap++);
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_4, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_4, rsltMap.get(BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_P4, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_P4, rsltMap.get(BLLG_ATTRB_VAL_TXT));

        if (ixMap >= rsltList.size()) {
            return;
        }
        rsltMap = rsltList.get(ixMap++);
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_5, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_5, rsltMap.get(BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_P5, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_P5, rsltMap.get(BLLG_ATTRB_VAL_TXT));

        if (ixMap >= rsltList.size()) {
            return;
        }
        rsltMap = rsltList.get(ixMap++);
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_6, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_6, rsltMap.get(BLLG_ATTRB_VAL_TXT));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbNm_P6, rsltMap.get(BLLG_ATTRB_NM));
        ZYPEZDItemValueSetter.setValue(bizMsg.bllgAttrbValTxt_P6, rsltMap.get(BLLG_ATTRB_VAL_TXT));
    }

    private static S21SsmEZDResult getRelationInfo(String glblCmpyCd, NWAL1650CMsg bizMsg, String dsAcctNum) {

        NMZC610001PMsg pMsg = getRelnAcct(glblCmpyCd, bizMsg, dsAcctNum);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            NMZC610001_xxMsgIdListPMsg msgIdInfo = pMsg.xxMsgIdList.no(0);
            bizMsg.setMessageInfo(//
                    msgIdInfo.xxMsgId.getValue() //
                    , new String[] {//
                    msgIdInfo.xxMsgPrmTxt_0.getValue() //
                            , msgIdInfo.xxMsgPrmTxt_1.getValue() //
                            , msgIdInfo.xxMsgPrmTxt_2.getValue() //
                            , msgIdInfo.xxMsgPrmTxt_3.getValue() //
                            , msgIdInfo.xxMsgPrmTxt_4.getValue() });
            return null;
        }
        for (int i = 0; i < pMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg bsPMsg = pMsg.EligibleCheckList.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(bsPMsg.dsAcctRelnRecipFlg.getValue())) {
                S21SsmEZDResult rslt = NWAL1650Query.getInstance().getDsAcctRefAttrb(glblCmpyCd, bsPMsg.relnDsAcctNum.getValue());
                if (rslt.isCodeNormal()) {
                    // MOD START 2015/12/03 #1309
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_P, bsPMsg.relnDsAcctNum.getValue());
                    // MOD END 2015/12/03 #1309
                    return rslt;
                }
            }
        }
        for (int i = 0; i < pMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg bsPMsg = pMsg.EligibleCheckList.no(i);

            if (ZYPConstant.FLG_OFF_N.equals(bsPMsg.dsAcctRelnRecipFlg.getValue())) {
                S21SsmEZDResult rslt = NWAL1650Query.getInstance().getDsAcctRefAttrb(glblCmpyCd, bsPMsg.relnDsAcctNum.getValue());
                if (rslt.isCodeNormal()) {
                    // MOD START 2015/12/03 #1309
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_P, bsPMsg.relnDsAcctNum.getValue());
                    // MOD END 2015/12/03 #1309
                    return rslt;
                }
            }
        }
        return null;
    }

    private static NMZC610001PMsg getRelnAcct(String glblCmpyCd, NWAL1650CMsg bizMsg, String dsAcctNum) {
        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        ZYPEZDItemValueSetter.setValue(pMsg.xxChildRelnFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, bizMsg.shipToCustCd_P);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I2, dsAcctNum);

        NMZC610001 cbaApi = new NMZC610001();
        cbaApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }


}
