/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLEL0060.common;

import static business.blap.NLEL0060.constant.NLEL0060Constant.ACCT_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.AFFL_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.blap.NLEL0060.constant.NLEL0060Constant.BR_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CC_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CHECK_BOX_A;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CHECK_BOX_B;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CHECK_BOX_C;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CH_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.CMPY_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_CUR_VAL_AMT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_DEPC_CNT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_DEPC_MTH_NUM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_DS_ASSET_MSTR_PK;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_LAST_DEPC_YR_MTH;
import static business.blap.NLEL0060.constant.NLEL0060Constant.COL_RSDL_VAL_AMT;
import static business.blap.NLEL0060.constant.NLEL0060Constant.EXTN_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.MODE_DEPC;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLBM0009E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NLEM0047E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0000E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0001W;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0002I;
import static business.blap.NLEL0060.constant.NLEL0060Constant.NZZM0011E;
import static business.blap.NLEL0060.constant.NLEL0060Constant.PROD_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.PROJ_CD;
import static business.blap.NLEL0060.constant.NLEL0060Constant.STR_COMMA;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_ASG;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_ASSET_HIST;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_DEP_SIM;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_DTL;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_FIN;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TAB_TRX;
import static business.blap.NLEL0060.constant.NLEL0060Constant.TBL_DS_ASSET_MSTR;
import static business.blap.NLEL0060.constant.NLEL0060Constant.ZZZM9006E;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDFastTBLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.blap.NLEL0060.NLEL0060Query;
import business.blap.NLEL0060.NLEL0060SMsg;
import business.blap.NLEL0060.NLEL0060_ASMsg;
import business.blap.NLEL0060.NLEL0060_BSMsg;
import business.blap.NLEL0060.constant.NLEL0060Constant;
import business.db.AMT_CHNG_RSN_TPTMsg;
import business.db.ASSET_TPTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VNDTMsgArray;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC305001PMsg;
import business.parts.NSZC033001PMsg;
import business.parts.NFACommonJrnlEntryConstant.CoaSegment;

import com.canon.cusa.s21.api.NLZ.NLZC305001.NLZC305001;
import com.canon.cusa.s21.api.NLZ.NLZC305001.constant.NLZC305001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_SEG_LKUP_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * NLEL0060CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/19   Hitachi         J.Kim           Update          QC#6774
 * 2016/05/10   Hitachi         K.Kojima        Update          QC#7144
 * 2016/05/12   Hitachi         K.Kojima        Update          QC#7113
 * 2016/06/09   Hitachi         T.Tsuchida      Update          QC#9376
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9757
 * 2016/09/26   Fujitsu         C.Tanaka        Update          QC#12697, QC#11899
 * 2016/11/29   Fujitsu         T.Murai         Update          QC#15823
 * 2017/02/14   Hitachi         J.Kim           Update          QC#17440
 * 2017/02/21   Hitachi         J.Kim           Update          QC#17589
 * 2017/05/15   CITS            M.Naito         Update          Merge DS Lv2
 * 2017/10/26   CITS            M.Naito         Update          QC#22052
 * 2017/11/07   Hitachi         J.Kim           Update          QC#16345
 * 2018/07/24   Hitachi         J.Kim           Update          QC#24950
 * 2018/07/31   Hitachi         J.Kim           Update          QC#27021
 * 2018/07/25   Hitachi         K.Kojima        Update          QC#27233
 * 2018/08/17   CITS            Y.Iwasaki       Update          QC#24426
 *</pre>
 */
public class NLEL0060CommonLogic {

    /**
     * Create Save Option pulldown list
     * @param bizMsg NLEL0060CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createSaveOptPulldownList(NLEL0060CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_L.clear();
        bizMsg.srchOptNm_L.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_L.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_L.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * Call NSZC0330 to save Search Option
     * @param bizMsg NLEL0060CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callNszc0330(NLEL0060CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NLEL0060CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk) && bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NLEL0060CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NLEL0060CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm.getValue().equals(bizMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NLEL0060CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk.getValue().compareTo(bizMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_L.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDSMsg sMsg) {

        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;
        NLEL0060SMsg glblMsg = (NLEL0060SMsg) sMsg;

        EZDCMsgArray cMsgArray = setCMsgArray(bizMsg);
        EZDSMsgArray sMsgArray = setSMsgArray(bizMsg, glblMsg);

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
        // START 2016/06/09 T.Tsuchida [QC#9376,ADD]
        showPageUpdate(bizMsg);
        // END 2016/06/09 T.Tsuchida [QC#9376,ADD]
    }

    /**
     * Jump to error page
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    public static void jumpToErrorPage(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        int errPage = 1;
        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (glblMsg.A.no(i).xxChkBox_A1.isError() || glblMsg.A.no(i).depcMthNum_A1.isError() //
                        || glblMsg.A.no(i).totAssetQty_A1.isError() || glblMsg.A.no(i).dsAssetGrpInitBookAmt_A1.isError() //
                        || glblMsg.A.no(i).coaMdseTpCd_A1.isError() || glblMsg.A.no(i).vndCd_A1.isError() //
                        || glblMsg.A.no(i).dsAcctNm_A1.isError()) {
                    errPage = (i / bizMsg.A.length()) * bizMsg.A.length();
                    break;
                }
            }
        } else if (TAB_ASG.equals(tab)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (glblMsg.B.no(i).xxChkBox_B1.isError() || glblMsg.B.no(i).xxScrItem10Txt_B1.isError()) {
                    errPage = (i / bizMsg.B.length()) * bizMsg.B.length();
                    break;
                }
            }
        } else if (TAB_FIN.equals(tab)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (glblMsg.C.no(i).xxChkBox_C1.isError() || glblMsg.C.no(i).prntDsAssetMstrPk_C1.isError() //
                        || glblMsg.C.no(i).curValAmt_C1.isError()) {
                    errPage = (i / bizMsg.C.length()) * bizMsg.C.length();
                    break;
                }
            }
        }

        if (errPage == 0) {
            errPage = 1;
        }
        bizMsg.xxPageShowFromNum.setValue(errPage);
        loadOnePageToCMsg(bizMsg, glblMsg);
    }

    /**
     * Update the global Message.
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    public static void updateGlblMsg(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        EZDCMsgArray cMsgArray = setCMsgArray(bizMsg);
        EZDSMsgArray sMsgArray = setSMsgArray(bizMsg, glblMsg);

        String tab = bizMsg.xxDplyTab.getValue();

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsgArray.getValidCount(); i++) {
            EZDMessageInfo infoA1 = null;
            EZDMessageInfo infoA2 = null;
            EZDMessageInfo infoA3 = null;
            EZDMessageInfo infoA4 = null;
            EZDMessageInfo infoA5 = null;
            EZDMessageInfo infoA6 = null;
            EZDMessageInfo infoA7 = null;
            EZDMessageInfo infoB1 = null;
            EZDMessageInfo infoB2 = null;
            EZDMessageInfo infoC1 = null;
            EZDMessageInfo infoC2 = null;
            EZDMessageInfo infoC3 = null;
            if (TAB_DTL.equals(tab)) {
                infoA1 = getErrorInfo(glblMsg.A.no(ixG + i), CHECK_BOX_A);
                infoA2 = getErrorInfo(glblMsg.A.no(ixG + i), "depcMthNum_A1");
                infoA3 = getErrorInfo(glblMsg.A.no(ixG + i), "totAssetQty_A1");
                infoA4 = getErrorInfo(glblMsg.A.no(ixG + i), "dsAssetGrpInitBookAmt_A1");
                infoA5 = getErrorInfo(glblMsg.A.no(ixG + i), "coaMdseTpCd_A1");
                infoA6 = getErrorInfo(glblMsg.A.no(ixG + i), "vndCd_A1");
                infoA7 = getErrorInfo(glblMsg.A.no(ixG + i), "dsAcctNm_A1");
            } else if (TAB_ASG.equals(tab)) {
                infoB1 = getErrorInfo(glblMsg.B.no(ixG + i), CHECK_BOX_B);
                infoB2 = getErrorInfo(glblMsg.B.no(ixG + i), "xxScrItem10Txt_B1");
            } else if (TAB_FIN.equals(tab)) {
                infoC1 = getErrorInfo(glblMsg.C.no(ixG + i), CHECK_BOX_C);
                infoC2 = getErrorInfo(glblMsg.C.no(ixG + i), "prntDsAssetMstrPk_C1");
                infoC3 = getErrorInfo(glblMsg.C.no(ixG + i), "curValAmt_C1");
            }
            EZDMsg.copy(cMsgArray.get(i), null, sMsgArray.get(ixG + i), null);

            if (infoA1 != null) {
                glblMsg.A.no(ixG + i).xxChkBox_A1.setErrorInfo(infoA1.getErrorKbn(), infoA1.getCode(), infoA1.getParameter());
            }
            if (infoA2 != null) {
                glblMsg.A.no(ixG + i).depcMthNum_A1.setErrorInfo(infoA2.getErrorKbn(), infoA2.getCode(), infoA2.getParameter());
            }
            if (infoA3 != null) {
                glblMsg.A.no(ixG + i).totAssetQty_A1.setErrorInfo(infoA3.getErrorKbn(), infoA3.getCode(), infoA3.getParameter());
            }
            if (infoA4 != null) {
                glblMsg.A.no(ixG + i).dsAssetGrpInitBookAmt_A1.setErrorInfo(infoA4.getErrorKbn(), infoA4.getCode(), infoA4.getParameter());
            }
            if (infoA5 != null) {
                glblMsg.A.no(ixG + i).coaMdseTpCd_A1.setErrorInfo(infoA5.getErrorKbn(), infoA5.getCode(), infoA5.getParameter());
            }
            if (infoA6 != null) {
                glblMsg.A.no(ixG + i).vndCd_A1.setErrorInfo(infoA6.getErrorKbn(), infoA6.getCode(), infoA6.getParameter());
            }
            if (infoA7 != null) {
                glblMsg.A.no(ixG + i).dsAcctNm_A1.setErrorInfo(infoA7.getErrorKbn(), infoA7.getCode(), infoA7.getParameter());
            }
            if (infoB1 != null) {
                glblMsg.B.no(ixG + i).xxChkBox_B1.setErrorInfo(infoB1.getErrorKbn(), infoB1.getCode(), infoB1.getParameter());
            }
            if (infoB2 != null) {
                glblMsg.B.no(ixG + i).xxScrItem10Txt_B1.setErrorInfo(infoB2.getErrorKbn(), infoB2.getCode(), infoB2.getParameter());
            }
            if (infoC1 != null) {
                glblMsg.C.no(ixG + i).xxChkBox_C1.setErrorInfo(infoC1.getErrorKbn(), infoC1.getCode(), infoC1.getParameter());
            }
            if (infoC2 != null) {
                glblMsg.C.no(ixG + i).prntDsAssetMstrPk_C1.setErrorInfo(infoC2.getErrorKbn(), infoC2.getCode(), infoC2.getParameter());
            }
            if (infoC3 != null) {
                glblMsg.C.no(ixG + i).curValAmt_C1.setErrorInfo(infoC3.getErrorKbn(), infoC3.getCode(), infoC3.getParameter());
            }
        }
    }

    private static EZDMessageInfo getErrorInfo(EZDSMsg smsg, String key) {
        EZDMessageInfo info;
        try {
            // Invoke EZDSMsg#getErrorInfo(String, int)
            Method method = EZDSMsg.class.getDeclaredMethod("getErrorInfo", String.class, int.class);
            method.setAccessible(true);
            info = (EZDMessageInfo) method.invoke(smsg, key, -1);
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
        return info;
    }

    /**
     * Set EZDCMsgArray for tab
     * @param bizMsg NLEL0060CMsg
     * @return EZDCMsgArray
     */
    public static EZDCMsgArray setCMsgArray(NLEL0060CMsg bizMsg) {

        EZDCMsgArray cMsgArray = null;

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            cMsgArray = bizMsg.A;
        } else if (TAB_ASG.equals(tab)) {
            cMsgArray = bizMsg.B;
        } else if (TAB_FIN.equals(tab)) {
            cMsgArray = bizMsg.C;
        } else if (TAB_TRX.equals(tab)) {
            cMsgArray = bizMsg.D;
        } else if (TAB_DEP_SIM.equals(tab)) {
            cMsgArray = bizMsg.E;
            // START 2016/09/26 J.Kim [QC#13372,ADD]
        } else if (TAB_ASSET_HIST.equals(tab)) {
            cMsgArray = bizMsg.F;
        }
        // END 2016/09/26 J.Kim [QC#13372,ADD]

        return cMsgArray;
    }

    /**
     * Set EZDSMsgArray for tab
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return EZDSMsgArray
     */
    public static EZDSMsgArray setSMsgArray(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        EZDSMsgArray sMsgArray = null;

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            sMsgArray = glblMsg.A;
        } else if (TAB_ASG.equals(tab)) {
            sMsgArray = glblMsg.B;
        } else if (TAB_FIN.equals(tab)) {
            sMsgArray = glblMsg.C;
        } else if (TAB_TRX.equals(tab)) {
            sMsgArray = glblMsg.D;
        } else if (TAB_DEP_SIM.equals(tab)) {
            sMsgArray = glblMsg.E;
            // START 2016/09/26 J.Kim [QC#13372,ADD]
        } else if (TAB_ASSET_HIST.equals(tab)) {
            sMsgArray = glblMsg.F;
        }
        // END 2016/09/26 J.Kim [QC#13372,ADD]

        return sMsgArray;
    }

    /**
     * Show Paging
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     */
    public static void showPage(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        EZDSMsgArray sMsgArray = setSMsgArray(bizMsg, glblMsg);

        int cnt = sMsgArray.getValidCount();
        if (cnt == 0) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            // START 2016/06/09 T.Tsuchida [QC#9376,ADD]
            showPageUpdate(bizMsg);
            // END 2016/06/09 T.Tsuchida [QC#9376,ADD]

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (cnt == sMsgArray.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
            }

            // START 2017/10/26 M.Naito [QC#22052,ADD]
            bizMsg.setMessageInfo(NZZM0002I);
            // END 2017/10/26 M.Naito [QC#22052,ADD]
            // START 2016/06/09 T.Tsuchida [QC#9376,MOD]
            // bizMsg.xxPageShowFromNum.setValue(1);
            showPageSet(bizMsg);
            // END 2016/06/09 T.Tsuchida [QC#9376,MOD]
            loadOnePageToCMsg(bizMsg, glblMsg);
        }
    }

    // START 2016/06/09 T.Tsuchida [QC#9376,ADD]
    /**
     * Show Paging Clear
     * @param bizMsg NLEL0060CMsg
     */
    public static void showPageClear(NLEL0060CMsg bizMsg) {

        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowFromNum_B.clear();
        bizMsg.xxPageShowFromNum_C.clear();
        bizMsg.xxPageShowFromNum_D.clear();
        bizMsg.xxPageShowFromNum_E.clear();
        bizMsg.xxPageShowFromNum_F.clear();
    }

    /**
     * Show Paging Set
     * @param bizMsg NLEL0060CMsg
     */
    public static void showPageSet(NLEL0060CMsg bizMsg) {

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, bizMsg.xxPageShowFromNum_A);
        } else if (TAB_ASG.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, bizMsg.xxPageShowFromNum_B);
        } else if (TAB_FIN.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, bizMsg.xxPageShowFromNum_C);
        } else if (TAB_TRX.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, bizMsg.xxPageShowFromNum_D);
        } else if (TAB_DEP_SIM.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, bizMsg.xxPageShowFromNum_E);
        } else if (TAB_ASSET_HIST.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, bizMsg.xxPageShowFromNum_F);
        }
    }

    /**
     * Show Paging Update
     * @param bizMsg NLEL0060CMsg
     */
    public static void showPageUpdate(NLEL0060CMsg bizMsg) {

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_A, bizMsg.xxPageShowFromNum);
        } else if (TAB_ASG.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_B, bizMsg.xxPageShowFromNum);
        } else if (TAB_FIN.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_C, bizMsg.xxPageShowFromNum);
        } else if (TAB_TRX.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_D, bizMsg.xxPageShowFromNum);
        } else if (TAB_DEP_SIM.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_E, bizMsg.xxPageShowFromNum);
        } else if (TAB_ASSET_HIST.equals(tab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_F, bizMsg.xxPageShowFromNum);
        }
    }

    /**
     * Show Paging Update
     * @param bizMsg NLEL0060CMsg
     * @param showPage Integer
     */
    public static void showPageUpdate(NLEL0060CMsg bizMsg, int showPage) {

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            bizMsg.xxPageShowFromNum_A.setValue(showPage);
        } else if (TAB_ASG.equals(tab)) {
            bizMsg.xxPageShowFromNum_B.setValue(showPage);
        } else if (TAB_FIN.equals(tab)) {
            bizMsg.xxPageShowFromNum_C.setValue(showPage);
        } else if (TAB_TRX.equals(tab)) {
            bizMsg.xxPageShowFromNum_D.setValue(showPage);
        } else if (TAB_DEP_SIM.equals(tab)) {
            bizMsg.xxPageShowFromNum_E.setValue(showPage);
            // START 2016/09/26 J.Kim [QC#13372,ADD]
        } else if (TAB_ASSET_HIST.equals(tab)) {
            bizMsg.xxPageShowFromNum_F.setValue(showPage);
        }
        // END 2016/09/26 J.Kim [QC#13372,ADD]
    }

    // END 2016/06/09 T.Tsuchida [QC#9376,ADD]

    // START 2016/06/14 T.Tsuchida [QC#9757,MOD]
    /**
     * Get Asset Master Info
     * @param glblCmpyCd String
     * @param dsAssetMstrPk BigDecimal
     * @return DS_ASSET_MSTRTMsg
     */
    public static DS_ASSET_MSTRTMsg getDsAssetMstrInfo(String glblCmpyCd, BigDecimal dsAssetMstrPk) {

        DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);
        return (DS_ASSET_MSTRTMsg) EZDTBLAccessor.findByKey(dsAssetMstrTMsg);
    }

    // END 2016/06/14 T.Tsuchida [QC#9757,MOD]

    // START 2016/04/19 J.Kim [QC#6774,ADD]
    /**
     * Get Ship To Cust Info
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsgArray
     */
    public static SHIP_TO_CUSTTMsgArray getShipToCustInfo(String glblCmpyCd, String shipToCustCd) {

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        return (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
    }

    /**
     * Get Ship To Cust Info
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    public static SVC_MACH_MSTRTMsg getSvcMachMstrInfo(String glblCmpyCd, BigDecimal svcMachMstrPk) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(svcMachMstrTMsg);
    }

    /**
     * Set Assignment Address
     * @param glblMsg NLEL0060SMsg
     * @param glblCmpyCd String
     */
    public static void setAssignmentAddress(NLEL0060SMsg glblMsg, String glblCmpyCd) {

        for (int index = 0; index < glblMsg.B.getValidCount(); index++) {
            NLEL0060_BSMsg sMsg = glblMsg.B.no(index);

            // Create full address
            EZDSStringItem[] sAddrs = new EZDSStringItem[] {
                    sMsg.firstLineAddr_B1,
                    sMsg.scdLineAddr_B1,
                    sMsg.thirdLineAddr_B1,
                    sMsg.frthLineAddr_B1
            };
            StringBuilder sSb = new StringBuilder();
            for (EZDSStringItem addr : sAddrs) {
                if (ZYPCommonFunc.hasValue(addr.getValue())) {
                    sSb.append(" ").append(addr.getValue());
                }
            }
            if (sSb.length() > 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.xxAllLineAddr_B1, sSb.substring(1));
            }


            if (ZYPCommonFunc.hasValue(sMsg.xxAllLineAddr_B1) || ZYPCommonFunc.hasValue(sMsg.ctyAddr_B1) || ZYPCommonFunc.hasValue(sMsg.stCd_B1) || ZYPCommonFunc.hasValue(sMsg.postCd_B1)) {
                continue;
            }

            String shipToCustCd = "";
            if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd_B1)) {
                shipToCustCd = sMsg.shipToCustCd_B1.getValue();
            } else if (ZYPCommonFunc.hasValue(sMsg.svcMachMstrPk_B1)) {
                SVC_MACH_MSTRTMsg svcMachMtr = getSvcMachMstrInfo(glblCmpyCd, sMsg.svcMachMstrPk_B1.getValue());
                if (svcMachMtr == null) {
                    continue;
                }
                shipToCustCd = svcMachMtr.curLocNum.getValue();
            }

            SHIP_TO_CUSTTMsgArray shipToCustInfoList = getShipToCustInfo(glblCmpyCd, shipToCustCd);
            if (shipToCustInfoList != null && shipToCustInfoList.getValidCount() > 0) {
                SHIP_TO_CUSTTMsg shipToCustInfo = (SHIP_TO_CUSTTMsg) shipToCustInfoList.get(0);

                // Create full address
                EZDTStringItem[] tAddrs = new EZDTStringItem[] {
                        shipToCustInfo.firstLineAddr,
                        shipToCustInfo.scdLineAddr,
                        shipToCustInfo.thirdLineAddr,
                        shipToCustInfo.frthLineAddr
                };
                StringBuilder tSb = new StringBuilder();
                for (EZDTStringItem addr : tAddrs) {
                    if (ZYPCommonFunc.hasValue(addr)) {
                        tSb.append(" ").append(addr.getValue());
                    }
                }
                if (tSb.length() > 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.xxAllLineAddr_B1, tSb.substring(1));
                }

                //ZYPEZDItemValueSetter.setValue(sMsg.xxAllLineAddr_B1, shipToCustInfo.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(sMsg.ctyAddr_B1, shipToCustInfo.ctyAddr);
                ZYPEZDItemValueSetter.setValue(sMsg.stCd_B1, shipToCustInfo.stCd);
                ZYPEZDItemValueSetter.setValue(sMsg.postCd_B1, shipToCustInfo.postCd);
                //ZYPEZDItemValueSetter.setValue(sMsg.firstLineAddr_BB, shipToCustInfo.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(sMsg.ctyAddr_BB, shipToCustInfo.ctyAddr);
                ZYPEZDItemValueSetter.setValue(sMsg.stCd_BB, shipToCustInfo.stCd);
                ZYPEZDItemValueSetter.setValue(sMsg.postCd_BB, shipToCustInfo.postCd);
            }
        }
    }

    // START 2016/05/10 K.Kojima [QC#7113,ADD]
    /**
     * compareToEqual
     * @param decimal1 BigDecimal
     * @param decimal2 BigDecimal
     * @return boolean
     */
    public static boolean compareToEqual(BigDecimal decimal1, BigDecimal decimal2) {
        if (decimal1 == null && decimal2 == null) {
            return true;
        }
        if (decimal1 == null || decimal2 == null) {
            return false;
        }
        return (decimal1.compareTo(decimal2) == 0);
    }

    // END 2016/05/10 K.Kojima [QC#7113,ADD]

    /**
     * Get vender by vender code
     * @param glblCmpyCd String
     * @param vndCd String
     * @return PRNT_VNDTMsg
     */
    public static PRNT_VNDTMsg getVnd(String glblCmpyCd, String vndCd) {

        PRNT_VNDTMsg prntVndTMsg = new PRNT_VNDTMsg();
        prntVndTMsg.setSQLID("001");
        prntVndTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prntVndTMsg.setConditionValue("prntVndCd01", vndCd);
        prntVndTMsg.setConditionValue("inacDt01", ZYPDateUtil.getSalesDate());

        PRNT_VNDTMsgArray prntVndTMsgArray = (PRNT_VNDTMsgArray) EZDTBLAccessor.findByCondition(prntVndTMsg);
        if (prntVndTMsgArray.length() > 0) {
            return prntVndTMsgArray.no(0);
        }
        return null;
    }

    /**
     * Check input error
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return boolean
     */
    public static boolean checkInput(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        updateGlblMsg(bizMsg, glblMsg);

        EZDSMsgArray sMsgArray = null;
        String chkBoxNm = null;

        String tab = bizMsg.xxDplyTab.getValue();
        if (TAB_DTL.equals(tab)) {
            sMsgArray = glblMsg.A;
            chkBoxNm = CHECK_BOX_A;
        } else if (TAB_ASG.equals(tab)) {
            sMsgArray = glblMsg.B;
            chkBoxNm = CHECK_BOX_B;
        } else if (TAB_FIN.equals(tab)) {
            sMsgArray = glblMsg.C;
            chkBoxNm = CHECK_BOX_C;
        }

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsgArray, chkBoxNm, ZYPConstant.CHKBOX_ON_Y);

        if (checkList == null || checkList.isEmpty()) {
            for (int i = 0; i < sMsgArray.getValidCount(); i++) {
                if (TAB_DTL.equals(tab)) {
                    glblMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NZZM0011E);
                } else if (TAB_ASG.equals(tab)) {
                    glblMsg.B.no(i).xxChkBox_B1.setErrorInfo(1, NZZM0011E);
                } else if (TAB_FIN.equals(tab)) {
                    glblMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NZZM0011E);
                }
            }
            NLEL0060CommonLogic.jumpToErrorPage(bizMsg, glblMsg);
            return false;
        }
        return true;
    }

    // START 2016/10/27 J.Kim [QC#11026,ADD]
    /**
     * Check AssetManualEntry
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060SMsg
     * @return boolean
     */
    public static boolean checkAssetManualEntry(NLEL0060CMsg bizMsg, NLEL0060SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, CHECK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (checkList == null || checkList.isEmpty()) {
            return true;
        }

        boolean isCheckFlg = true;
        NLEL0060_ASMsg asMsg;
        for (int i : checkList) {
            asMsg = glblMsg.A.no(i);
            String assetStsCd = asMsg.assetStsCd_A1.getValue();
            String manEntryFlg = asMsg.manEntryFlg_A1.getValue();

            if (ZYPConstant.FLG_ON_Y.equals(manEntryFlg) && ASSET_STS.PENDING.equals(assetStsCd) && ZYPCommonFunc.hasValue(asMsg.prntDsAssetMstrPk_A1)) {
                asMsg.xxChkBox_A1.setErrorInfo(1, NLEM0047E);
                isCheckFlg = false;
            }
        }

        return isCheckFlg;
    }

    // END 2016/10/27 J.Kim [QC#11026,MOD]

    // START 2017/02/21 J.Kim [QC#17589,ADD]
    /**
     * updateAssetDepreciation
     * @param glblCmpyCd String
     * @param updDtlMap Map<String, Object>
     * @return boolean
     */
    public static boolean updateAssetDepreciation(String glblCmpyCd, Map<String, Object> updDtlMap) {

        List<DS_ASSET_MSTRTMsg> tMsgList = new ArrayList<DS_ASSET_MSTRTMsg>();
        List<Map<String, Object>> assetInfoMapList = setCalculateDepreciation(glblCmpyCd, updDtlMap);
        for (Map<String, Object> assetInfoMap : assetInfoMapList) {
            BigDecimal dsAssetMstrPk = (BigDecimal) assetInfoMap.get(COL_DS_ASSET_MSTR_PK);
            BigDecimal curValAmt = (BigDecimal) assetInfoMap.get(COL_CUR_VAL_AMT);
            DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.dsAssetMstrPk, dsAssetMstrPk);
            dsAssetMstrTMsg = (DS_ASSET_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsAssetMstrTMsg);

            ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.initBookAmt, curValAmt);
            ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.curValAmt, curValAmt);
            tMsgList.add(dsAssetMstrTMsg);
        }

        int result = EZDFastTBLAccessor.update(tMsgList.toArray(new DS_ASSET_MSTRTMsg[tMsgList.size()]));
        if (result != tMsgList.size()) {
            return false;
        }
        return true;
    }

    /**
     * Set Asset Depreciation
     * @param glblCmpyCd String
     * @param mapList Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> setAssetDepreciation(String glblCmpyCd, Map<String, Object> mapList) {

        // Get Asset Information
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> assetMstInfoMapList = NLEL0060Query.getInstance().getAssetDepreciation(glblCmpyCd, mapList);
        if (assetMstInfoMapList == null || assetMstInfoMapList.isEmpty()) {
            return rtnList;
        }

        for (int i = 0; i < assetMstInfoMapList.size(); i++) {

            Map<String, Object> assetDtlInfoMap = assetMstInfoMapList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(COL_DS_ASSET_MSTR_PK, assetDtlInfoMap.get(COL_DS_ASSET_MSTR_PK));
            map.put(COL_DEPC_CNT, (BigDecimal) assetDtlInfoMap.get(COL_DEPC_CNT));
            map.put(COL_LAST_DEPC_YR_MTH, (String) assetDtlInfoMap.get(COL_LAST_DEPC_YR_MTH));
            if (MODE_DEPC.equals(mapList.get(MODE_DEPC))) {
                map.put(COL_CUR_VAL_AMT, (BigDecimal) assetDtlInfoMap.get("INIT_BOOK_AMT"));
            } else {
                map.put(COL_CUR_VAL_AMT, (BigDecimal) assetDtlInfoMap.get(COL_CUR_VAL_AMT));
                map.put(COL_DEPC_MTH_NUM, assetDtlInfoMap.get(COL_DEPC_MTH_NUM));
                map.put(COL_RSDL_VAL_AMT, assetDtlInfoMap.get(COL_RSDL_VAL_AMT));
                map.put(COL_CUR_VAL_AMT, culcCurValAmt(map));
            }
            rtnList.add(map);
        }

        return rtnList;
    }

    /**
     * Set Calculate Depreciation
     * @param glblCmpyCd String
     * @param mapList Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> setCalculateDepreciation(String glblCmpyCd, Map<String, Object> mapList) {

        // 1. Get Asset Information
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> assetMstInfoMapList = NLEL0060Query.getInstance().getAssetDepreciation(glblCmpyCd, mapList);
        if (assetMstInfoMapList == null || assetMstInfoMapList.isEmpty()) {
            return rtnList;
        }

        BigDecimal totcurValAmt = BigDecimal.ZERO;
        for (Map<String, Object> assetMstInfoMap : assetMstInfoMapList) {
            totcurValAmt = totcurValAmt.add((BigDecimal) assetMstInfoMap.get(COL_CUR_VAL_AMT));
        }
        BigDecimal initCurValAmt = (BigDecimal) mapList.get(COL_CUR_VAL_AMT);

        // 2. Get Constant for Asset Cost Calculation
        int carcDeclPlace = ZYPCodeDataUtil.getNumConstValue(NLZC305001Constant.ASSET_COST_CARC_DECIMAL_PLACE, glblCmpyCd).intValue();
        String costRoundMode = ZYPCodeDataUtil.getVarCharConstValue(NLZC305001Constant.ASSET_COST_ROUNDING_MODE, glblCmpyCd);

        int roundingMode = BigDecimal.ROUND_DOWN;
        BigDecimal totSplAmt = BigDecimal.ZERO;
        if (NLZC305001Constant.ROUND_UP.equals(costRoundMode)) {
            roundingMode = BigDecimal.ROUND_UP;
        } else if (NLZC305001Constant.ROUND_HALF_UP.equals(costRoundMode)) {
            roundingMode = BigDecimal.ROUND_HALF_UP;
        }

        for (int i = 0; i < assetMstInfoMapList.size(); i++) {

            Map<String, Object> assetDtlInfoMap = assetMstInfoMapList.get(i);
            BigDecimal curValAmt = (BigDecimal) assetDtlInfoMap.get(COL_CUR_VAL_AMT);

            BigDecimal calcBookAmt = (initCurValAmt.multiply(curValAmt.divide(totcurValAmt, carcDeclPlace, roundingMode))).setScale(carcDeclPlace, roundingMode);
            totSplAmt = totSplAmt.add(calcBookAmt);

            if (i == assetMstInfoMapList.size() - 1) {
                if (initCurValAmt.compareTo(totSplAmt) < 0) {
                    calcBookAmt = calcBookAmt.subtract(totSplAmt.subtract(initCurValAmt));
                }

                if (initCurValAmt.compareTo(totSplAmt) > 0) {
                    calcBookAmt = calcBookAmt.add(initCurValAmt.subtract(totSplAmt));
                }
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(COL_DS_ASSET_MSTR_PK, assetDtlInfoMap.get(COL_DS_ASSET_MSTR_PK));
            map.put(COL_DEPC_CNT, (BigDecimal) assetDtlInfoMap.get(COL_DEPC_CNT));
            map.put(COL_LAST_DEPC_YR_MTH, (String) assetDtlInfoMap.get(COL_LAST_DEPC_YR_MTH));
            map.put(COL_CUR_VAL_AMT, calcBookAmt);
            map.put(COL_DEPC_MTH_NUM, assetDtlInfoMap.get(COL_DEPC_MTH_NUM));
            map.put(COL_RSDL_VAL_AMT, assetDtlInfoMap.get(COL_RSDL_VAL_AMT));
            map.put(COL_CUR_VAL_AMT, culcCurValAmt(map));
            rtnList.add(map);
        }
        return rtnList;
    }

    /**
     * Set Initial Cost Change
     * @param glblCmpyCd String
     * @param mapList Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> setInitialCostChange(String glblCmpyCd, Map<String, Object> mapList) {

        // Get Asset Information
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> assetMstInfoMapList = NLEL0060Query.getInstance().getAssetDepreciation(glblCmpyCd, mapList);
        if (assetMstInfoMapList == null || assetMstInfoMapList.isEmpty()) {
            return rtnList;
        }

        for (int i = 0; i < assetMstInfoMapList.size(); i++) {
            Map<String, Object> assetDtlInfoMap = assetMstInfoMapList.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(COL_DS_ASSET_MSTR_PK, assetDtlInfoMap.get(COL_DS_ASSET_MSTR_PK));
            map.put(COL_CUR_VAL_AMT, (BigDecimal) assetDtlInfoMap.get("INIT_BOOK_AMT"));
            rtnList.add(map);
        }

        return rtnList;
    }

    /**
     * Calculate Current Value
     * @param map Map<String, Object>
     * @return BigDecimal
     */
    private static BigDecimal culcCurValAmt(Map<String, Object> map) {

        BigDecimal depcMthNum = new BigDecimal((String) map.get(COL_DEPC_MTH_NUM));
        BigDecimal prevValAmt = (BigDecimal) map.get(COL_CUR_VAL_AMT);
        BigDecimal depcCnt = (BigDecimal) map.get(COL_DEPC_CNT);
        BigDecimal rsdlValAmt = BigDecimal.ZERO;
        BigDecimal valAmt = null;
        BigDecimal curValAmt = null;

        if (ZYPCommonFunc.hasValue((BigDecimal) map.get(COL_RSDL_VAL_AMT))) {
            rsdlValAmt = (BigDecimal) map.get(COL_RSDL_VAL_AMT);
        }
        BigDecimal depcValAmt = prevValAmt.subtract(rsdlValAmt);
        valAmt = depcValAmt.divide(depcMthNum, 2, RoundingMode.DOWN);
        curValAmt = valAmt.multiply(depcCnt);
        curValAmt = prevValAmt.subtract(curValAmt);

        return curValAmt;
    }

    // END 2017/02/21 J.Kim [QC#17589,ADD]

    /**
     * Call NLZC305001
     * @param bizMsg NLEL0060CMsg
     * @param pMsg NLZC305001PMsg
     * @return boolean
     */
    public static boolean callAssetUpdateApi(NLEL0060CMsg bizMsg, NLZC305001PMsg pMsg) {

        String msgId = null;

        NLZC305001 assetUpdateAPI = new NLZC305001();
        // START 2018/07/20 J.Kim [QC#24950,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxBizAppId, BIZ_ID);
        // END 2018/07/20 J.Kim [QC#24950,ADD]
        assetUpdateAPI.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    bizMsg.setMessageInfo(msgId);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Call NLZC305001
     * @param bizMsg NLEL0060CMsg
     * @param glblCmpyCd String
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @param targetPk BigDecimal
     * @return boolean
     */
    public static boolean checkUpdatedByAnotherUser(NLEL0060CMsg bizMsg, String glblCmpyCd, String ezUpTime, String ezUpTimeZone, BigDecimal targetPk) {

        DS_ASSET_MSTRTMsg dsAssetMstrTMsg = new DS_ASSET_MSTRTMsg();

        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAssetMstrTMsg.dsAssetMstrPk, targetPk);
        dsAssetMstrTMsg = (DS_ASSET_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsAssetMstrTMsg);
        if (dsAssetMstrTMsg == null) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {TBL_DS_ASSET_MSTR });
            return false;
        }

        if (!ZYPDateUtil.isSameTimeStamp(ezUpTime, ezUpTimeZone, dsAssetMstrTMsg.ezUpTime.getValue(), dsAssetMstrTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(NLBM0009E);
            return false;
        }
        return true;
    }

    // START 2016/11/01 J.Kim [QC#16345,ADD]
    /**
     * editDefaultExpenseCOA
     * @param bizMsg NLEL0060CMsg
     * @param glblMsg NLEL0060_BSMsg
     * @param glblCmpyCd String
     * @return String
     */
    public static String editDefaultExpenseCOA(NLEL0060CMsg bizMsg, NLEL0060_BSMsg glblMsg, String glblCmpyCd) {

        String defExpCoa = ZYPCodeDataUtil.getVarCharConstValue(NLEL0060Constant.DEF_EXP_COA, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defExpCoa)) {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"Default Expense COA" });
            return null;
        }

        String[] defaultExpenseCoaList = defExpCoa.split(",");
        StringBuffer expAcct = new StringBuffer();
        Map<String, String> defaultCoaMap = NLEL0060Query.getInstance().getDefaultCoaMapList(glblCmpyCd, glblMsg);
        if (defaultCoaMap == null) {
            return "";
        }

        expAcct.append(getDefaultCoa(defaultExpenseCoaList[0], defaultCoaMap, CMPY_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[1], defaultCoaMap, BR_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[2], defaultCoaMap, CC_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[3], defaultCoaMap, ACCT_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[4], defaultCoaMap, PROD_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[5], defaultCoaMap, CH_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[6], defaultCoaMap, AFFL_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[7], defaultCoaMap, PROJ_CD));
        expAcct.append(STR_COMMA);
        expAcct.append(getDefaultCoa(defaultExpenseCoaList[8], defaultCoaMap, EXTN_CD));
        return expAcct.toString();
    }

    /**
     * setExpenseCOA
     * @param bSMsg NLEL0060_BSMsg
     * @param glblCmpyCd String
     * @return String
     */
    public static String setExpenseCOA(NLEL0060_BSMsg bSMsg, String glblCmpyCd) {

        StringBuffer expAcct = new StringBuffer();
        expAcct.append(bSMsg.expCoaCmpyCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaBrCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaCcCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaAcctCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaProdCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaChCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaAfflCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaProjCd_B1.getValue());
        expAcct.append(STR_COMMA);
        expAcct.append(bSMsg.expCoaExtnCd_B1.getValue());
        return expAcct.toString();
    }

    private static String getDefaultCoa(String defaultExpenseCoa, Map<String, String> defaultCoaMap, String coa) {

        String defaultExp = "";
        // START 2018/03/05 J.Kim [QC#24565,MOD]
        if (defaultExpenseCoa.startsWith("@", 0)) {
            defaultExp = defaultCoaMap.get(defaultExpenseCoa.substring(1) + coa);
            if (!ZYPCommonFunc.hasValue(defaultExp)) {
                defaultExp = "";
            }
        } else {
            defaultExp = defaultExpenseCoa;
        }
        // END 2018/03/05 J.Kim [QC#24565,MOD]
        return defaultExp;
    }

    /**
     * getAssetTpInfo
     * @param glblCmpyCd String
     * @param assetTpCd String
     * @return ASSET_TPTMsg
     */
    public static ASSET_TPTMsg getAssetTpInfo(String glblCmpyCd, String assetTpCd) {

        ASSET_TPTMsg assetTpTMsg = new ASSET_TPTMsg();
        ZYPEZDItemValueSetter.setValue(assetTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(assetTpTMsg.assetTpCd, assetTpCd);
        ASSET_TPTMsg outAssetTMsg = (ASSET_TPTMsg) EZDTBLAccessor.findByKey(assetTpTMsg);
        return outAssetTMsg;
    }

    /**
     * getAjeDefaultCoa
     * @param coa CoaSegment
     * @param glblMsg NLEL0060_BSMsg
     * @return String
     */
    public static String getAjeDefaultCoa(CoaSegment coa, NLEL0060_BSMsg glblMsg) {

        // START 2018/02/08 J.Kim [QC#23123,DEL]
        // if (assetTpTMsg == null) {
        //    return null;
        // }
        // String ajeCoaBrcd = assetTpTMsg.ajeCoaBrCd.getValue();
        // String ajeCoaCccd = assetTpTMsg.ajeCoaCcCd.getValue();
        // String ajeCoaBucd = assetTpTMsg.ajeCoaBuCd.getValue();
        String ajeCoaBrcd = glblMsg.ajeCoaBrCd_B1.getValue();
        String ajeCoaCccd = glblMsg.ajeCoaCcCd_B1.getValue();
        String ajeCoaBucd = glblMsg.ajeCoaBuCd_B1.getValue();
        // END 2018/02/08 J.Kim [QC#23123,DEL]

        String val = "";
        switch (coa) {
            case BR:
                val = glblMsg.getValueString(getPattren(ajeCoaBrcd, "Br"), -1);
                break;
            case CC:
                val = glblMsg.getValueString(getPattren(ajeCoaCccd, "Cc"), -1);
                break;
            case EXTN:
                val = glblMsg.getValueString(getPattren(ajeCoaBucd, "Extn"), -1);
                break;
            default:
                val = null;
                break;
        }

        return val;
    }

    private static String getPattren(String defaultCoa, String coa) {

        if (COA_SEG_LKUP_TP.NEWCORE.equals(defaultCoa)) {
            defaultCoa = "_B1";
        } else if (COA_SEG_LKUP_TP.FROM_ORGANIZATION_MASTER_USING_TOC_CODE.equals(defaultCoa)) {
            defaultCoa = "_B2";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("coa");
        sb.append(coa);
        sb.append("Cd");
        sb.append(defaultCoa);
        return sb.toString();
    }
    // END 2016/11/01 J.Kim [QC#16345,ADD]

    // START 2018/02/26 J.Kim [QC#22792,ADD]
    /**
     * checkExpenseStr
     * @param expenseStr String[]
     * @param bSMsg NLEL0060_BSMsg
     * @param pMsg NLZC305001PMsg
     */
    public static void checkExpenseStr(String[] expenseStr, NLEL0060_BSMsg bSMsg, NLZC305001PMsg pMsg) {

        if (!expenseStr[0].equals(bSMsg.expCoaCmpyCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCmpyCd, expenseStr[0]);
        }
        if (!expenseStr[1].equals(bSMsg.expCoaBrCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaBrCd, expenseStr[1]);
        }
        if (!expenseStr[2].equals(bSMsg.expCoaCcCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCcCd, expenseStr[2]);
        }
        if (!expenseStr[3].equals(bSMsg.expCoaAcctCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaAcctCd, expenseStr[3]);
        }
        if (!expenseStr[4].equals(bSMsg.expCoaProdCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaProdCd, expenseStr[4]);
        }
        if (!expenseStr[5].equals(bSMsg.expCoaChCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaChCd, expenseStr[5]);
        }
        if (!expenseStr[6].equals(bSMsg.expCoaAfflCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaAfflCd, expenseStr[6]);
        }
        if (!expenseStr[7].equals(bSMsg.expCoaProjCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaProjCd, expenseStr[7]);
        }
        if (!expenseStr[8].equals(bSMsg.expCoaExtnCd_B1.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaExtnCd, expenseStr[8]);
        }
    }
    // END 2018/02/26 J.Kim [QC#22792,ADD]

    // START 2018/04/12 J.Kim [QC#22807,ADD]
    public static String existCoaFlg(EZDCStringItem itemFrom, EZDCStringItem itemTo) {

        if (ZYPCommonFunc.hasValue(itemFrom) || ZYPCommonFunc.hasValue(itemTo)) {
            return ZYPConstant.FLG_ON_Y;
        }
        return null;
    }

    public static String existCoaFlg(NLEL0060CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_S1) || ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_S2) //
                || ZYPCommonFunc.hasValue(bizMsg.expCoaBrCd_S1) || ZYPCommonFunc.hasValue(bizMsg.expCoaBrCd_S2) //
                || ZYPCommonFunc.hasValue(bizMsg.expCoaCcCd_S1) || ZYPCommonFunc.hasValue(bizMsg.expCoaCcCd_S2) //
                || ZYPCommonFunc.hasValue(bizMsg.expCoaExtnCd_S1) || ZYPCommonFunc.hasValue(bizMsg.expCoaExtnCd_S2)) {
            return ZYPConstant.FLG_ON_Y;
        }
        return null;
    }
    // END 2018/04/12 J.Kim [QC#22807,ADD]

    /**
     * setSearchCondBacup
     */
    public static void setSearchCondBackUp(NLEL0060CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.assetTpCd_S1, bizMsg.assetTpCd_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.assetStsCd_S1, bizMsg.assetStsCd_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.assetTagNum_S1, bizMsg.assetTagNum_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.serNum_S1, bizMsg.serNum_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_S1, bizMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseDescShortTxt_S1, bizMsg.mdseDescShortTxt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_S1, bizMsg.cpoOrdNum_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtnWhCd_S1, bizMsg.rtnWhCd_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhNm_S1, bizMsg.rtlWhNm_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetDescTxt_S1, bizMsg.dsAssetDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_S1, bizMsg.shipToCustAcctCd_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_S1, bizMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_S1, bizMsg.custIssPoNum_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndCd_S1, bizMsg.vndCd_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.vndNm_S1, bizMsg.vndNm_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetMstrPk_S1, bizMsg.dsAssetMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.depcStartDt_S1, bizMsg.depcStartDt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.depcStartDt_S2, bizMsg.depcStartDt_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.svcConfigMstrPk_S1, bizMsg.svcConfigMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.depcCoaAcctCd_S1, bizMsg.depcCoaAcctCd_F);
        ZYPEZDItemValueSetter.setValue(bizMsg.depcCoaAcctCd_S2, bizMsg.depcCoaAcctCd_T);
        ZYPEZDItemValueSetter.setValue(bizMsg.expCoaBrCd_S1, bizMsg.expCoaBrCd_F);
        ZYPEZDItemValueSetter.setValue(bizMsg.expCoaBrCd_S2, bizMsg.expCoaBrCd_T);
        ZYPEZDItemValueSetter.setValue(bizMsg.expCoaCcCd_S1, bizMsg.expCoaCcCd_F);
        ZYPEZDItemValueSetter.setValue(bizMsg.expCoaCcCd_S2, bizMsg.expCoaCcCd_T);
        ZYPEZDItemValueSetter.setValue(bizMsg.expCoaExtnCd_S1, bizMsg.expCoaExtnCd_F);
        ZYPEZDItemValueSetter.setValue(bizMsg.expCoaExtnCd_S2, bizMsg.expCoaExtnCd_T);
    }

    // START 2018/07/31 J.Kim [QC#27021,ADD]
    public static void setPrntDsAssetMstrPk(NLEL0060CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsAssetMstrPk_H1)) {
            BigDecimal dsAssetMstrPk = bizMsg.dsAssetMstrPk_H1.getValue();
            DS_ASSET_MSTRTMsg assetMstrtTMsg = NLEL0060Query.getInstance().getPrntDsAssetMstrPk(dsAssetMstrPk);
            if (assetMstrtTMsg == null) {
                return;
            }

            if (dsAssetMstrPk.compareTo(assetMstrtTMsg.prntDsAssetMstrPk.getValue()) != 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsAssetMstrPk_S1, assetMstrtTMsg.prntDsAssetMstrPk);
            }
        }
    }
    // END 2018/07/31 J.Kim [QC#27021,ADD]

    // START 2018/08/01 J.Kim [QC#26901,ADD]
    public static AMT_CHNG_RSN_TPTMsg setValueChangeReason(String amtChngRsnTpCd) {
        return NLEL0060Query.getInstance().setValueChangeReason(amtChngRsnTpCd);
    }
    // END 2018/08/01 J.Kim [QC#26901,ADD]

    // START 2018/07/25 K.Kojima [QC#27233,ADD]
    public static String getSellToCustSearchFlag(NLEL0060CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_S1)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.depcCoaAcctCd_S2)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaBrCd_S1)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaBrCd_S2)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaCcCd_S1)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaCcCd_S2)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaExtnCd_S1)) {
            return ZYPConstant.FLG_ON_Y;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.expCoaExtnCd_S2)) {
            return ZYPConstant.FLG_ON_Y;
        }
        return ZYPConstant.FLG_OFF_N;
    }

    public static List<BigDecimal> getDsAssetMstrPkList(NLEL0060SMsg glblMsg) {
        if (glblMsg.A.getValidCount() == 0) {
            return null;
        }
        List<BigDecimal> dsAssetMstrPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).dsAssetMstrPk_A1)) {
                dsAssetMstrPkList.add(glblMsg.A.no(i).dsAssetMstrPk_A1.getValue());
            }
        }
        return dsAssetMstrPkList;
    }
    // END 2018/07/25 K.Kojima [QC#27233,ADD]
}
