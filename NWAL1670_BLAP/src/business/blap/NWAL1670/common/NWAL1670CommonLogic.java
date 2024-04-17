/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1670.common;

import static business.blap.NWAL1670.constant.NWAL1670Constant.MDG_PARAM_ATTR_VAL;
import static business.blap.NWAL1670.constant.NWAL1670Constant.MDG_PARAM_END_DATE;
import static business.blap.NWAL1670.constant.NWAL1670Constant.MDG_PARAM_SLS_DT;
import static business.blap.NWAL1670.constant.NWAL1670Constant.MDG_PARAM_START_DATE;
import static business.blap.NWAL1670.constant.NWAL1670Constant.MDG_PARAM_TEAM_NAME;
import static business.blap.NWAL1670.constant.NWAL1670Constant.MDG_PARAM_USER_ID;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0006I;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0223E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0496W;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NWAM0559E;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NZZM0002I;
import static business.blap.NWAL1670.constant.NWAL1670Constant.NZZM0010E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.CHKBOX_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1670.NWAL1670CMsg;
import business.blap.NWAL1670.NWAL1670Query;
import business.blap.NWAL1670.NWAL1670SMsg;
import business.blap.NWAL1670.NWAL1670_ACMsg;
import business.blap.NWAL1670.NWAL1670_ASMsg;
import business.blap.NWAL1670.NWAL1670_BCMsg;
import business.blap.NWAL1670.NWAL1670_CCMsg;
import business.blap.NWAL1670.NWAL1670_CSMsg;
import business.blap.NWAL1670.NWAL1670_DSMsg;
import business.db.COA_BRTMsg;
import business.db.LINE_BIZ_TPTMsg;
import business.db.ORD_TEAM_ATTRB_TPTMsg;
import business.db.ORD_TEAM_ATTRB_TPTMsgArray;
import business.db.ORD_TEAM_MSTR_DTLTMsg;
import business.db.ORD_TEAM_MSTR_DTLTMsgArray;
import business.db.ORD_TEAM_MSTR_HDRTMsg;
import business.db.ORD_TEAM_MSTR_HDRTMsgArray;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ZN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Order Team Set up
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 * 2016/04/20   Hitachi         O.Okuma         Update          CSA QC#7261
 *</pre>
 */
public class NWAL1670CommonLogic {

    /**
     * Clear Message
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     */
    public static void clearMsg(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        cMsg.clear();
        clearMsgFoSearch(cMsg, sMsg);

        setValue(cMsg.xxChkBox, CHKBOX_ON_Y);
    }

    /**
     * Create Pull Down
     * @param cMsg NWAL1670CMsg
     */
    public static void createPullDown(NWAL1670CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(ORD_ZN.class, cMsg.ordZnCd_01, cMsg.ordZnDescTxt_01);

        ORD_TEAM_ATTRB_TPTMsgArray tMsgAry = getOrdTeamAttrbTpPulldownList(cMsg);

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "ordTeamAttrbTpCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "ordTeamAttrbTpDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.ordTeamAttrbTpCd_01, cMsg.ordTeamAttrbTpDescTxt_01);
    }

    /**
     * Get Search Data
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     * @param isSubmit boolean
     */
    public static void getSearchData(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, boolean isSubmit) {

        clearMsgFoSearch(cMsg, sMsg);

        S21SsmEZDResult ssmResult = NWAL1670Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);

        if (isSubmit) {
            if (ssmResult.isCodeNormal()) {
                copyToDSMsg(sMsg);
            }
        } else {
            if (ssmResult.isCodeNormal()) {
                // Result > 1000
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NWAM0496W);
                } else {
                    cMsg.setMessageInfo(NZZM0002I);
                }
                copyToDSMsg(sMsg);
            } else {
                // No result
                cMsg.setMessageInfo(NWAM0006I);
            }
        }
    }

    /**
     * Get Detail Data
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     */
    public static void getDetailData(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        clearMsgForRightTable(cMsg, sMsg);

        int rowNum = cMsg.xxRadioBtn.getValueInt();
        NWAL1670_ACMsg acMsg = cMsg.A.no(rowNum);

        if (!hasValue(acMsg.ordTeamMstrHdrPk_A)) {
            return;
        }

        S21SsmEZDResult ssmResultForMember = NWAL1670Query.getInstance().getMemberData(cMsg, rowNum, cMsg.B.length() + 1);
        S21SsmEZDResult ssmResultForAttrb = NWAL1670Query.getInstance().getAttributeData(cMsg, sMsg, rowNum, sMsg.C.length() + 1);

        if (ssmResultForMember.isCodeNormal() || ssmResultForAttrb.isCodeNormal()) {
            if (ssmResultForMember.getQueryResultCount() > cMsg.B.length() || ssmResultForAttrb.getQueryResultCount() > sMsg.C.length()) {
                // Result > 200
                cMsg.setMessageInfo(NWAM0496W);
                return;
            }
        }
        if (ssmResultForMember.isCodeNotFound() || ssmResultForAttrb.isCodeNotFound()) {
            // No result
            cMsg.setMessageInfo(NWAM0006I);
            return;
        }
        if (ssmResultForMember.isCodeNormal() && ssmResultForAttrb.isCodeNormal()) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int pagenationFrom) {

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
     * @param itemIndex int
     */
    public static void pagenation(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(startIndex + 1));
        setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(sMsg.A.getValidCount()));
        setValue(cMsg.xxPageShowCurNum, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        setValue(cMsg.xxPageShowTotNum, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param cMsg NWAL1670CMsg
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(NWAL1670CMsg cMsg) {
        if (cMsg.xxPageShowCurNum.getValueInt() <= 0) {
            return 0;
        } else if (cMsg.xxPageShowTotNum.getValueInt() < cMsg.xxPageShowCurNum.getValueInt()) {
            setValue(cMsg.xxPageShowCurNum, cMsg.xxPageShowTotNum);
        }
        if (cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1) > (cMsg.xxPageShowOfNum.getValueInt())) {
            return cMsg.xxPageShowCurNum.getValueInt() - 1;
        }
        return cMsg.A.length() * (cMsg.xxPageShowCurNum.getValueInt() - 1);
    }

    /**
     * cheackTeamList
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @return boolean
     */
    public static boolean cheackTeamList(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        boolean errFlg = true;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NWAL1670_ACMsg acMsg = cMsg.A.no(i);

            if (!cheackOrdTeamMstrNm(cMsg, sMsg, i, acMsg.xxRowNum_A.getValueInt())) {
                acMsg.ordTeamMstrNm_A.setErrorInfo(1, NWAM0559E, new String[] {MDG_PARAM_TEAM_NAME });
                errFlg = false;
            }

            if (hasValue(acMsg.effFromDt_A)) {
                if (hasValue(acMsg.effThruDt_A) && ZYPDateUtil.compare(acMsg.effFromDt_A.getValue(), acMsg.effThruDt_A.getValue()) > 0) {
                    acMsg.effFromDt_A.setErrorInfo(1, NWAM0223E, new String[] {MDG_PARAM_END_DATE, MDG_PARAM_START_DATE });
                    acMsg.effThruDt_A.setErrorInfo(1, NWAM0223E, new String[] {MDG_PARAM_END_DATE, MDG_PARAM_START_DATE });
                    errFlg = false;
                }
                if (!hasValue(acMsg.ordTeamMstrHdrPk_A) && ZYPDateUtil.compare(cMsg.slsDt.getValue(), acMsg.effFromDt_A.getValue()) > 0) {
                    acMsg.effFromDt_A.setErrorInfo(1, NWAM0223E, new String[] {MDG_PARAM_START_DATE, MDG_PARAM_SLS_DT });
                    errFlg = false;
                }
            }
        }
        return errFlg;
    }

    /**
     * cheackMemberList
     * @param cMsg Business Component Interface Message
     * @return boolean
     */
    public static boolean cheackMemberList(NWAL1670CMsg cMsg) {

        boolean errFlg = true;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NWAL1670_BCMsg bcMsg = cMsg.B.no(i);

            if (hasValue(bcMsg.ordTeamMstrDtlPk_B)) {
                continue;
            }

            if (!checkPsnCd(cMsg, i)) {
                cMsg.B.no(i).ordTeamAttrbValTxt_B.setErrorInfo(1, NZZM0010E, new String[] {MDG_PARAM_USER_ID });
                errFlg = false;
            }

            if (!checkOrdTeamAttrbValTxt(cMsg, ORD_TEAM_ATTRB_TP.USER_ID, bcMsg.ordTeamAttrbValTxt_B.getValue())) {
                cMsg.B.no(i).ordTeamAttrbValTxt_B.setErrorInfo(1, NWAM0559E, new String[] {MDG_PARAM_USER_ID });
                errFlg = false;
                continue;
            }

            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                if (!hasValue(cMsg.B.no(j).ordTeamMstrDtlPk_B)) {
                    if (bcMsg.ordTeamAttrbValTxt_B.getValue().equals(cMsg.B.no(j).ordTeamAttrbValTxt_B.getValue()) && bcMsg.xxRowNum_B.getValueInt() != cMsg.B.no(j).xxRowNum_B.getValueInt()) {
                        cMsg.B.no(i).ordTeamAttrbValTxt_B.setErrorInfo(1, NWAM0559E, new String[] {MDG_PARAM_USER_ID });
                        errFlg = false;
                        break;
                    }
                }
            }
        }
        return errFlg;
    }

    /**
     * cheackAttributeList
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @return boolean
     */
    public static boolean cheackAttributeList(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        boolean errFlg = true;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            NWAL1670_CSMsg csMsg = sMsg.C.no(i);

            if (hasValue(csMsg.ordTeamMstrDtlPk_C)) {
                continue;
            }

            if (ORD_TEAM_ATTRB_TP.CUSTOMER_NUMBER.equals(csMsg.ordTeamAttrbTpCd_C.getValue())) {
                if (!cheakDsAcctCust(cMsg, sMsg, i)) {
                    sMsg.C.no(i).ordTeamAttrbValTxt_C.setErrorInfo(1, NZZM0010E, new String[] {MDG_PARAM_ATTR_VAL });
                    errFlg = false;
                }
            } else if (ORD_TEAM_ATTRB_TP.LINE_OF_BUSINESS.equals(csMsg.ordTeamAttrbTpCd_C.getValue())) {
                if (!checkLineBizTp(cMsg, sMsg, i)) {
                    sMsg.C.no(i).ordTeamAttrbValTxt_C.setErrorInfo(1, NZZM0010E, new String[] {MDG_PARAM_ATTR_VAL });
                    errFlg = false;
                }
            } else if (ORD_TEAM_ATTRB_TP.BRANCH.equals(csMsg.ordTeamAttrbTpCd_C.getValue())) {
                if (!checkCoaBr(cMsg, sMsg, i)) {
                    sMsg.C.no(i).ordTeamAttrbValTxt_C.setErrorInfo(1, NZZM0010E, new String[] {MDG_PARAM_ATTR_VAL });
                    errFlg = false;
                }
            }

            if (!checkOrdTeamAttrbValTxt(cMsg, csMsg.ordTeamAttrbTpCd_C.getValue(), csMsg.ordTeamAttrbValTxt_C.getValue())) {
                sMsg.C.no(i).ordTeamAttrbValTxt_C.setErrorInfo(1, NWAM0559E, new String[] {MDG_PARAM_ATTR_VAL });
                errFlg = false;
                continue;
            }

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                if (!hasValue(sMsg.C.no(j).ordTeamMstrDtlPk_C) && csMsg.ordTeamAttrbTpCd_C.getValue().equals(sMsg.C.no(j).ordTeamAttrbTpCd_C.getValue())) {
                    if (csMsg.ordTeamAttrbValTxt_C.getValue().equals(sMsg.C.no(j).ordTeamAttrbValTxt_C.getValue()) && csMsg.xxRowNum_C.getValueInt() != sMsg.C.no(j).xxRowNum_C.getValueInt()) {
                        sMsg.C.no(i).ordTeamAttrbValTxt_C.setErrorInfo(1, NWAM0559E, new String[] {MDG_PARAM_ATTR_VAL });
                        errFlg = false;
                        break;
                    }
                }
            }
        }
        return errFlg;
    }

    /**
     * checkRadioButton
     * @param cMsg NWAL1670CMsg
     * @return boolean
     */
    public static boolean checkRadioButton(NWAL1670CMsg cMsg) {
        if (hasValue(cMsg.xxRadioBtn)) {
            return true;
        }
        return false;
    }

    /**
     * clearRadioButton
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     */
    public static void clearRadioButton(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        cMsg.xxRadioBtn.clear();
        sMsg.xxRadioBtn.clear();
        clearMsgForRightTable(cMsg, sMsg);
    }

    /**
     * copyToCSmsg
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     */
    public static void copyToCSmsg(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            NWAL1670_CCMsg ccMsg = cMsg.C.no(i);
            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                NWAL1670_CSMsg csMsg = sMsg.C.no(j);
                if (hasValue(ccMsg.ordTeamMstrDtlPk_C)) {
                    if (hasValue(csMsg.ordTeamMstrDtlPk_C) && ccMsg.ordTeamMstrDtlPk_C.getValue().compareTo(csMsg.ordTeamMstrDtlPk_C.getValue()) == 0) {
                        EZDMsg.copy(ccMsg, null, csMsg, null);
                    }
                } else {
                    if (ccMsg.xxRowNum_C.getValueInt() == csMsg.xxRowNum_C.getValueInt()) {
                        EZDMsg.copy(ccMsg, null, csMsg, null);
                    }
                }
            }
        }
    }

    /**
     * doFilterForAttribute
     * @param cMsg NWAL1670CMsg
     * @param sMsg NWAL1670SMsg
     */
    public static void doFilterForAttribute(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {

        if (hasValue(cMsg.ordTeamAttrbTpCd)) {
            int num = 0;
            ZYPTableUtil.clear(cMsg.C);
            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                NWAL1670_CSMsg csMsg = sMsg.C.no(i);

                if (cMsg.ordTeamAttrbTpCd.getValue().equals(csMsg.ordTeamAttrbTpCd_C.getValue())) {
                    EZDMsg.copy(sMsg.C.get(i), null, cMsg.C.get(num), null);
                    num++;
                }
            }
            cMsg.C.setValidCount(num);
        } else {
            EZDMsg.copy(sMsg.C, null, cMsg.C, null);
        }
    }

    /**
     * getChangeDataNumList
     * @param sMsg NWAL1670SMsg
     * @return List<Integer>
     */
    public static List<Integer> getChangeDataNumList(NWAL1670SMsg sMsg) {

        List<Integer> indexList = new ArrayList<Integer>(sMsg.A.getValidCount());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NWAL1670_ASMsg asMsg = sMsg.A.no(i);
            if (!hasValue(asMsg.ordTeamMstrHdrPk_A)) {
                indexList.add(i);
                continue;
            }
            for (int j = 0; j < sMsg.D.getValidCount(); j++) {
                NWAL1670_DSMsg dsMsg = sMsg.D.no(j);
                if (asMsg.xxRowNum_A.getValueInt() == dsMsg.xxRowNum_D.getValueInt()) {
                    if (isChangeData(asMsg, dsMsg)) {
                        indexList.add(i);
                        break;
                    }
                }
            }
        }
        return indexList;
    }

    /**
     * getOrdTeamMstrHdr
     * @param glblCmpyCd String
     * @param ordTeamMstrHdrPk BigDecimal
     * @param isUpdate boolean
     * @return ORD_TEAM_MSTR_HDRTMsg
     */
    public static ORD_TEAM_MSTR_HDRTMsg getOrdTeamMstrHdr(String glblCmpyCd, BigDecimal ordTeamMstrHdrPk, boolean isUpdate) {

        ORD_TEAM_MSTR_HDRTMsg inTmsg = new ORD_TEAM_MSTR_HDRTMsg();
        setValue(inTmsg.glblCmpyCd, glblCmpyCd);
        setValue(inTmsg.ordTeamMstrHdrPk, ordTeamMstrHdrPk);

        if (isUpdate) {
            return (ORD_TEAM_MSTR_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTmsg);
        }
        return (ORD_TEAM_MSTR_HDRTMsg) EZDTBLAccessor.findByKey(inTmsg);
    }

    /**
     * getOrdTeamMstrDtl
     * @param glblCmpyCd String
     * @param ordTeamMstrDtlPk BigDecimal
     * @return ORD_TEAM_MSTR_DTLTMsg
     */
    public static ORD_TEAM_MSTR_DTLTMsg getOrdTeamMstrDtl(String glblCmpyCd, BigDecimal ordTeamMstrDtlPk) {

        ORD_TEAM_MSTR_DTLTMsg inTmsg = new ORD_TEAM_MSTR_DTLTMsg();
        setValue(inTmsg.glblCmpyCd, glblCmpyCd);
        setValue(inTmsg.ordTeamMstrDtlPk, ordTeamMstrDtlPk);

        return (ORD_TEAM_MSTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inTmsg);
    }

    /**
     * getPsnV
     * @param glblCmpyCd String
     * @param psnCd String
     * @return S21_PSN_VTMsg
     */
    public static S21_PSN_VTMsg getPsnV(String glblCmpyCd, String psnCd) {
        S21_PSN_VTMsg inMsg = new S21_PSN_VTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("psnCd01", psnCd);

        S21_PSN_VTMsgArray tMsgAry = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return null;
        }
        return tMsgAry.no(0);
    }

    private static ORD_TEAM_ATTRB_TPTMsgArray getOrdTeamAttrbTpPulldownList(NWAL1670CMsg cMsg) {
        ORD_TEAM_ATTRB_TPTMsg inMsg = new ORD_TEAM_ATTRB_TPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("ordTeamAttrbTpCd01", ORD_TEAM_ATTRB_TP.USER_ID);
        return (ORD_TEAM_ATTRB_TPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private static void copyToDSMsg(NWAL1670SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), "A", sMsg.D.no(i), "D");
        }
        sMsg.D.setValidCount(sMsg.A.getValidCount());
    }

    private static void clearMsgFoSearch(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(cMsg.A);
        clearRadioButton(cMsg, sMsg);
        cMsg.ordTeamAttrbTpCd.clear();

    }

    private static void clearMsgForRightTable(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(sMsg.E);
    }

    private static boolean isChangeData(NWAL1670_ASMsg asMsg, NWAL1670_DSMsg dsMsg) {

        if (!asMsg.ordTeamMstrNm_A.getValue().equals(dsMsg.ordTeamMstrNm_D.getValue())) {
            return true;
        }
        if (!asMsg.ordZnCd_A.getValue().equals(dsMsg.ordZnCd_D.getValue())) {
            return true;
        }
        if (!asMsg.effFromDt_A.getValue().equals(dsMsg.effFromDt_D.getValue())) {
            return true;
        }
        if (!asMsg.effThruDt_A.getValue().equals(dsMsg.effThruDt_D.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean cheackOrdTeamMstrNm(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int index, int rowNum) {

        NWAL1670_ACMsg acMsg = cMsg.A.no(index);
        String ordTeamMstrNm = acMsg.ordTeamMstrNm_A.getValue();
        ORD_TEAM_MSTR_HDRTMsg tMsg = getOrdTeamMstrNm(cMsg, ordTeamMstrNm);
        boolean errFlg = false;

        if (tMsg == null) {
            errFlg = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NWAL1670_ASMsg asMsg = sMsg.A.no(i);
            if (ordTeamMstrNm.equals(asMsg.ordTeamMstrNm_A.getValue()) && rowNum != asMsg.xxRowNum_A.getValueInt()) {
                return false;
            }

            if (tMsg != null && tMsg.ordTeamMstrHdrPk.getValue().equals(asMsg.ordTeamMstrHdrPk_A.getValue())) {
                errFlg = true;
            }
        }

        return errFlg;
    }

    private static ORD_TEAM_MSTR_HDRTMsg getOrdTeamMstrNm(NWAL1670CMsg cMsg, String ordTeamMstrNm) {
        ORD_TEAM_MSTR_HDRTMsg inMsg = new ORD_TEAM_MSTR_HDRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("ordTeamMstrNm01", ordTeamMstrNm);

        ORD_TEAM_MSTR_HDRTMsgArray tMsgAry = (ORD_TEAM_MSTR_HDRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return null;
        }
        return tMsgAry.no(0);
    }

    private static boolean checkPsnCd(NWAL1670CMsg cMsg, int rowNum) {
        S21_PSN_VTMsg tMsg = getPsnV(cMsg.glblCmpyCd.getValue(), cMsg.B.no(rowNum).ordTeamAttrbValTxt_B.getValue());

        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private static boolean checkOrdTeamAttrbValTxt(NWAL1670CMsg cMsg, String ordTeamAttrbTpCd, String ordTeamAttrbValTxt) {
        ORD_TEAM_MSTR_DTLTMsg inMsg = new ORD_TEAM_MSTR_DTLTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("ordTeamMstrHdrPk01", cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).ordTeamMstrHdrPk_A.getValue());
        inMsg.setConditionValue("ordTeamAttrbTpCd01", ordTeamAttrbTpCd);
        inMsg.setConditionValue("ordTeamAttrbValTxt01", ordTeamAttrbValTxt);

        ORD_TEAM_MSTR_DTLTMsgArray tMsgAry = (ORD_TEAM_MSTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return true;
        }
        return false;
    }

    private static boolean cheakDsAcctCust(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int rowNum) {
        S21SsmEZDResult ssmResult = NWAL1670Query.getInstance().getDsAcctCust(cMsg, sMsg, rowNum);

        if (ssmResult.isCodeNormal()) {
            return true;
        }
        return false;
    }

    private static boolean checkLineBizTp(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int rowNum) {
        LINE_BIZ_TPTMsg inMsg = new LINE_BIZ_TPTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        // START 2016/04/20 O.Okuma [QC#7261,MOD]
        // setValue(inMsg.lineBizTpCd, cMsg.C.no(rowNum).ordTeamAttrbValTxt_C.getValue());
        setValue(inMsg.lineBizTpCd, sMsg.C.no(rowNum).ordTeamAttrbValTxt_C.getValue());
        // END 2016/04/20 O.Okuma [QC#7261,MOD]

        LINE_BIZ_TPTMsg tMsg = (LINE_BIZ_TPTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private static boolean checkCoaBr(NWAL1670CMsg cMsg, NWAL1670SMsg sMsg, int rowNum) {
        COA_BRTMsg inMsg = new COA_BRTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        // START 2016/04/20 O.Okuma [QC#7261,MOD]
        // setValue(inMsg.coaBrCd, cMsg.C.no(rowNum).ordTeamAttrbValTxt_C.getValue());
        setValue(inMsg.coaBrCd, sMsg.C.no(rowNum).ordTeamAttrbValTxt_C.getValue());
        // END 2016/04/20 O.Okuma [QC#7261,MOD]

        COA_BRTMsg tMsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (tMsg == null) {
            return false;
        }
        return true;
    }
}
