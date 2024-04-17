/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0600.common;

import static business.blap.NSAL0600.constant.NSAL0600Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import parts.common.EZDMsg;
import parts.common.EZDSItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0600.NSAL0600CMsg;
import business.blap.NSAL0600.NSAL0600Query;
import business.blap.NSAL0600.NSAL0600SMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

/**
 *<pre>
 * Cascade Date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 * 2016/01/25   Hitachi         T.Tomita        Update          CSA QC#2721
 * 2016/02/16   Hitachi         T.Tomita        Update          CSA QC#3192
 *</pre>
 */
public class NSAL0600CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL0600CMsg
     * @param sMsg NSAL0600SMsg
     */
    public static void clearMsg(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        // CMsg
        cMsg.slsDt_P.clear();
        cMsg.xxFromDt_H.clear();
        cMsg.xxThruDt_H.clear();
        cMsg.svcMemoRsnCd_HS.clear();
        cMsg.svcCmntTxt_H.clear();
        cMsg.dsContrNum.clear();
        cMsg.contrVrsnEffFromDt.clear();
        cMsg.contrVrsnEffThruDt.clear();
        cMsg.contrVrsnEffFromDt_N.clear();
        cMsg.contrVrsnEffThruDt_N.clear();
        cMsg.ezUpTime_H.clear();
        cMsg.ezUpTimeZone_H.clear();

        ZYPTableUtil.clear(cMsg.A);

        // SMsg
        sMsg.slsDt_P.clear();
        sMsg.xxFromDt_H.clear();
        sMsg.xxThruDt_H.clear();
        sMsg.svcMemoRsnCd_HS.clear();
        sMsg.svcCmntTxt_H.clear();
        sMsg.dsContrNum.clear();
        sMsg.contrVrsnEffFromDt.clear();
        sMsg.contrVrsnEffThruDt.clear();
        sMsg.contrVrsnEffFromDt_N.clear();
        sMsg.contrVrsnEffThruDt_N.clear();
        sMsg.ezUpTime_H.clear();
        sMsg.ezUpTimeZone_H.clear();

        ZYPTableUtil.clear(sMsg.A);
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0600CMsg
     * @param glblCmpyCd String
     */
    public static void createPullDown(NSAL0600CMsg cMsg, String glblCmpyCd) {
        createSvcMemoRsnPullDown(cMsg, glblCmpyCd);
    }

    private static void createSvcMemoRsnPullDown(NSAL0600CMsg cMsg, String glblCmpyCd) {
        SVC_MEMO_RSNTMsgArray tMsgAry = getSvcMemoRsnPulldownList(glblCmpyCd, SVC_MEMO_TP.CASCADE_DATE);
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcMemoRsnCd_HC, cMsg.svcMemoRsnDescTxt_HD);
    }

    private static SVC_MEMO_RSNTMsgArray getSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSAL0600CMsg
     * @param sMsg NSAL0600SMsg
     * @param glblCmpyCd String
     */
    public static void setInitParams(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, String glblCmpyCd) {
        copyCMsgToSMsg(cMsg, sMsg);
        setValue(sMsg.slsDt_P, ZYPDateUtil.getSalesDate(glblCmpyCd));

        // Get Contract Data
        if (!setDsContr(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        // Get Machine Data
        if (!setDsContrDtl(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    /**
     * Copy CMsg to SMsg
     * @param cMsg NSAL0600CMsg
     * @param sMsg NSAL0600SMsg
     */
    public static void copyCMsgToSMsg(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        setValue(sMsg.dsContrPk_P, cMsg.dsContrPk_P);
        setValue(sMsg.slsDt_P, cMsg.slsDt_P);
        setValue(sMsg.xxFromDt_H, cMsg.xxFromDt_H);
        setValue(sMsg.xxThruDt_H, cMsg.xxThruDt_H);
        setValue(sMsg.svcMemoRsnCd_HS, cMsg.svcMemoRsnCd_HS);
        setValue(sMsg.svcCmntTxt_H, cMsg.svcCmntTxt_H);
        setValue(sMsg.ezUpTime_H, cMsg.ezUpTime_H);
        setValue(sMsg.ezUpTimeZone_H, cMsg.ezUpTimeZone_H);
        setValue(sMsg.dsContrNum, cMsg.dsContrNum);
        setValue(sMsg.contrVrsnEffFromDt, cMsg.contrVrsnEffFromDt);
        setValue(sMsg.contrVrsnEffThruDt, cMsg.contrVrsnEffThruDt);
        setValue(sMsg.contrVrsnEffFromDt_N, cMsg.contrVrsnEffFromDt_N);
        setValue(sMsg.contrVrsnEffThruDt_N, cMsg.contrVrsnEffThruDt_N);
        setValue(sMsg.dsContrCtrlStsCd, cMsg.dsContrCtrlStsCd);
    }

    private static boolean setDsContr(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, String glblCmpyCd) {
        if (!hasValue(sMsg.dsContrPk_P)) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No input parameter" });
            return false;
        }
        Map<String, Object> dsContrMap = NSAL0600Query.getInstance().getDsContr(sMsg, glblCmpyCd);
        if (dsContrMap == null) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No Contract Data" });
            return false;
        }

        // Set Parameter
        setValue(sMsg.dsContrNum, (String) dsContrMap.get("DS_CONTR_NUM"));
        setValue(sMsg.contrVrsnEffFromDt, (String) dsContrMap.get("CONTR_VRSN_EFF_FROM_DT"));
        setValue(sMsg.contrVrsnEffThruDt, (String) dsContrMap.get("CONTR_VRSN_EFF_THRU_DT"));
        sMsg.contrVrsnEffFromDt_N.clear();
        sMsg.contrVrsnEffThruDt_N.clear();
        setValue(sMsg.dsContrCtrlStsCd, (String) dsContrMap.get("DS_CONTR_CTRL_STS_CD"));
        setValue(sMsg.ezUpTime_H, (String) dsContrMap.get("EZUPTIME"));
        setValue(sMsg.ezUpTimeZone_H, (String) dsContrMap.get("EZUPTIMEZONE"));

        // START 2016/02/16 T.Tomita [QC#3192, ADD]
        String invDt = NSAL0600Query.getInstance().getInvDt(glblCmpyCd, cMsg.dsContrPk_P.getValue());
        if (hasValue(invDt)) { 
            setValue(sMsg.invFlg_H, ZYPConstant.FLG_ON_Y);
        } else {
            setValue(sMsg.invFlg_H, ZYPConstant.FLG_OFF_N);
        }
        // END 2016/02/16 T.Tomita [QC#3192, ADD]

        return true;
    }

    private static boolean setDsContrDtl(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, String glblCmpyCd) {
        NSAL0600Query.getInstance().getDsContrDtl(sMsg, glblCmpyCd);
        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0353E, new String[] {"No Machine Data" });
            return false;
        }
        return true;
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL0600CMsg
     * @param sMsg NSAL0600SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * setChkBox
     * @param sMsg NSAL0600SMsg
     * @param flg String
     */
    public static void setChkBox(NSAL0600SMsg sMsg, String flg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String dsContrDtlTpCd = sMsg.A.no(i).dsContrDtlTpCd_A.getValue();
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                continue;
            }
            setValue(sMsg.A.no(i).xxChkBox_A, flg);
        }
    }

    /**
     * getErrPageFromNum
     * @param cMsg NSAL0600CMsg
     * @param sMsg NSAL0600SMsg
     * @return int
     */
    public static int getErrPageFromNum(NSAL0600CMsg cMsg, NSAL0600SMsg sMsg) {
        if (!isError(sMsg)) {
            return cMsg.xxPageShowFromNum.getValueInt() - 1;
        }

        int errIndex = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Error
            if (sMsg.A.no(i).xxChkBox_A.isError()) {
                errIndex = i;
                break;
            }
        }

        return errIndex / cMsg.A.length() * cMsg.A.length();
    }

    /**
     * Check whether the sMsg has an error.
     * @param sMsg NSAL0600SMsg
     * @return boolean true: If sMsg has error. false: otherwise.
     */
    public static boolean isError(NSAL0600SMsg sMsg) {
        // line item
        List<EZDSItem> inputLineItem = getSMsgInputItem(sMsg);
        for (EZDSItem item : inputLineItem) {
            if (item.isError()) {
                return true;
            }
        }
        return false;
    }

    private static List<EZDSItem> getSMsgInputItem(NSAL0600SMsg sMsg) {
        List<EZDSItem> list = new ArrayList<EZDSItem>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            list.add(sMsg.A.no(i).xxChkBox_A);
        }
        return list;
    }
}
