/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7080.common;

import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM0163E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8216E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8296E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8393E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.NMAM8394E;
import static business.blap.NMAL7080.constant.NMAL7080Constant.STS_ACTIVE;
import static business.blap.NMAL7080.constant.NMAL7080Constant.STS_DELETED;
import static business.blap.NMAL7080.constant.NMAL7080Constant.STS_INACTIVE;
import static business.blap.NMAL7080.constant.NMAL7080Constant.YYYYMMDD_LENGTH;
import static business.blap.NMAL7080.constant.NMAL7080Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttrDefines;
import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7080.NMAL7080CMsg;
import business.blap.NMAL7080.NMAL7080Query;
import business.blap.NMAL7080.NMAL7080SMsg;
import business.blap.NMAL7080.NMAL7080_ACMsg;
import business.blap.NMAL7080.NMAL7080_ASMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SPLY_AGMT_DOC_TPTMsg;
import business.db.SPLY_AGMT_FREQ_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#23336
 *</pre>
 */
public class NMAL7080CommonLogic {
    /**
     * setSeachResult
     * @param resultList List<Map<?, ?>>
     * @param glblMsg NMAL7080SMsg
     * @param bizMsg NMAL7080CMsg
     */
    // 2018/04/04 S21_NA#23336 Mod Start
    // public static void setSeachResult(List<Map<?, ?>> resultList, NMAL7080SMsg glblMsg, NMAL7080CMsg bizMsg) {
    public static void setSeachResult(List<Map<?, ?>> resultList, NMAL7080SMsg glblMsg, NMAL7080CMsg bizMsg, String glblCmpyCd) {
    // 2018/04/04 S21_NA#23336 Mod End
        Map<?, ?> resultHeader = resultList.get(0);
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnPk, (BigDecimal) resultHeader.get("SPLY_AGMT_PLN_PK"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnNm, (String) resultHeader.get("SPLY_AGMT_PLN_NM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnShortNm, (String) resultHeader.get("SPLY_AGMT_PLN_SHORT_NM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnDescTxt, (String) resultHeader.get("SPLY_AGMT_PLN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.annTermCapNum, (BigDecimal) resultHeader.get("ANN_TERM_CAP_NUM"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnTpCd, (String) resultHeader.get("SPLY_AGMT_PLN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtDocTpCd, (String) resultHeader.get("SPLY_AGMT_DOC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(glblMsg.xxChkBox_AF, (String) resultHeader.get("ACTV_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.effFromDt, (String) resultHeader.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(glblMsg.effThruDt, (String) resultHeader.get("EFF_THRU_DT"));
        String actvFlg = (String) resultHeader.get("ACTV_FLG");
        String delFlg = (String) resultHeader.get("DEL_FLG");
        if (ZYPConstant.FLG_ON_Y.equals(delFlg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnStsNm_ST, STS_DELETED);
        } else if (ZYPConstant.FLG_ON_Y.equals(actvFlg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnStsNm_ST, STS_ACTIVE);
        } else if (ZYPConstant.FLG_OFF_N.equals(actvFlg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.splyAgmtPlnStsNm_ST, STS_INACTIVE);
        }
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTime, (String) resultHeader.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(glblMsg.ezUpTimeZone, (String) resultHeader.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(glblMsg.hdrLvlQtyEntryFlg, (String) resultHeader.get("HDR_LVL_QTY_ENTRY_FLG"));
        ZYPEZDItemValueSetter.setValue(glblMsg.dtlLvlQtyEntryFlg, (String) resultHeader.get("DTL_LVL_QTY_ENTRY_FLG"));
        setAnnTermCapNum(glblMsg, glblCmpyCd); // 2018/04/04 S21_NA#23336 Add
        EZDMsg.copy(glblMsg, null, glblMsg.H.no(0), null);
        int i = 0;
        for (Map<?, ?> result : resultList) {
            NMAL7080_ASMsg glblLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnDtlPk_A, (BigDecimal) result.get("SPLY_AGMT_PLN_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseCd_A, (String) result.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.mdseDescShortTxt_A, (String) result.get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtFreqTpCd_A, (String) result.get("SPLY_AGMT_FREQ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnFirstQty_A, (BigDecimal) result.get("SPLY_AGMT_PLN_FIRST_QTY"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnQty_A, (BigDecimal) result.get("SPLY_AGMT_PLN_QTY"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.splyAgmtPlnSqNum_A, (BigDecimal) result.get("SPLY_AGMT_PLN_SQ_NUM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.effFromDt_A, (String) result.get("DTL_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.effThruDt_A, (String) result.get("DTL_EFF_THRU_DT"));
            if (result.get("EZINTIME") != null) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxDt10Dt_AC, ((String) result.get("EZINTIME")).substring(0, 8));
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxFullNm_AC, (String) result.get("CREATED_BY"));
            if (result.get("DTL_EZUPTIME") != null) {
                ZYPEZDItemValueSetter.setValue(glblLineMsg.xxDt10Dt_AU, ((String) result.get("DTL_EZUPTIME")).substring(0, 8));
            }
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxFullNm_AU, (String) result.get("UPDATE_BY"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.ezUpTime_A, (String) result.get("DTL_EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.ezUpTimeZone_A, (String) result.get("DTL_EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.delFlg_A, (String) result.get("DTL_DEL_FLG"));
            i++;
            if (i >= glblMsg.A.length()) {
                break;
            }
        }
        glblMsg.A.setValidCount(i);
        EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);
        EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
        EZDMsg.copy(glblMsg, null, bizMsg, null);
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL7080CMsg
     * @param glblMsg NMAL7080SMsg
     */
    public static void updateGlblMsg(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {

        EZDMsg.copy(bizMsg, null, glblMsg, null);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(i), null);
        }
    }

    /**
     * <pre>
     * checkDupAttrbByGrp
     * </pre>
     * @param msgAry EZDMsgArray
     * @param chkItemNmList Message Item Name for check
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupAttrb(EZDMsgArray msgAry, String[] chkItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (chkItemNmList == null || chkItemNmList.length == 0) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());
        List<String> compList = new ArrayList<String>();

        for (int i = 0; i < msgAry.getValidCount(); i++) {
            EZDMsg msg = msgAry.get(i);
            String compVal = makeCompVal(msg, chkItemNmList);
            NMAL7080_ACMsg bizMsg = (NMAL7080_ACMsg) msg;
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.delFlg_A.getValue())) {
                continue;
            }
            if (compList.contains(compVal)) {
                errIdxList.add(i);
                continue;
            }
            compList.add(compVal);

            continue;
        }

        return errIdxList.toArray(new Integer[errIdxList.size()]);
    }

    /**
     * <pre>
       * checkDupAttrbByGrp
       * </pre>
     * @param msgAry EZDMsgArray
     * @param effFromItemNm Effective From Item Name
     * @param effThruItemNm Effective Thru Item Name
     * @param grpItemNmList Message Item name for grouping
     * @return Duplicate Index
     */
    public static Integer[] checkDupTermByGrp(EZDMsgArray msgAry, String effFromItemNm, String effThruItemNm, String[] grpItemNmList) {

        if (msgAry.getValidCount() <= 1) {
            return new Integer[0];
        }

        if (!ZYPCommonFunc.hasValue(effFromItemNm) || !ZYPCommonFunc.hasValue(effThruItemNm)) {
            Integer[] errIdxList = new Integer[msgAry.getValidCount()];
            for (int i = 0; i < errIdxList.length; i++) {
                errIdxList[i] = i;
            }
            return errIdxList;
        }

        List<Integer> errIdxList = new ArrayList<Integer>(msgAry.getValidCount());

        for (int i = 0; i < msgAry.getValidCount() - 1; i++) {

            if (errIdxList.contains(i)) {
                continue;
            }

            EZDMsg msg1 = msgAry.get(i);
            NMAL7080_ACMsg bizMsg1 = (NMAL7080_ACMsg) msg1;
            if (SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER.equals(bizMsg1.splyAgmtFreqTpCd_A.getValue())) {
                continue;
            }

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg1.delFlg_A.getValue())) {
                continue;
            }
            String grpKey1 = makeCompVal(msg1, grpItemNmList);
            String effFromDt1 = msg1.getValueString(effFromItemNm, 0);
            String effThruDt1 = msg1.getValueString(effThruItemNm, 0);
            if (!ZYPCommonFunc.hasValue(effThruDt1)) {
                effThruDt1 = "99991231";
            }

            boolean dupFlg = false;
            for (int j = i + 1; j < msgAry.getValidCount(); j++) {

                EZDMsg msg2 = msgAry.get(j);
                NMAL7080_ACMsg bizMsg2 = (NMAL7080_ACMsg) msg2;
                String grpKey2 = makeCompVal(msg2, grpItemNmList);
                String effFromDt2 = msg2.getValueString(effFromItemNm, 0);
                String effThruDt2 = msg2.getValueString(effThruItemNm, 0);
                if (!ZYPCommonFunc.hasValue(effThruDt2)) {
                    effThruDt2 = "99991231";
                }

                if (!grpKey1.equals(grpKey2)) {
                    continue;
                }

                if (SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER.equals(bizMsg2.splyAgmtFreqTpCd_A.getValue())) {
                    continue;
                }

                if (ZYPConstant.FLG_ON_Y.equals(bizMsg2.delFlg_A.getValue())) {
                    continue;
                }

                if (effFromDt1.compareTo(effFromDt2) < 0) {

                    if (effThruDt1.compareTo(effFromDt2) >= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else if (effFromDt1.compareTo(effFromDt2) > 0) {

                    if (effFromDt1.compareTo(effThruDt2) <= 0) {
                        // error
                        errIdxList.add(j);
                        dupFlg = true;
                    }

                } else {
                    // error
                    errIdxList.add(j);
                    dupFlg = true;
                }

            }

            if (dupFlg) {
                errIdxList.add(0, i);
            }
        }

        return errIdxList.toArray(new Integer[0]);
    }

    /**
     * <pre>
       * makeCompVal
       * </pre>
     * @param msg EZDMsg
     * @param itemNmList Message Item Name
     * @return
     */
    private static String makeCompVal(EZDMsg msg, String[] itemNmList) {

        if (itemNmList == null) {
            return "";
        }

        StringBuilder compVal = new StringBuilder();

        for (String chkAttrbNm : itemNmList) {
            EZDItemAttribute itemAttrb = msg.getAttr(chkAttrbNm);

            String attrbVal = "";
            switch (itemAttrb.getType()) {
                case EZDItemAttrDefines.TYPE_SEISU_SYOSU:
                    if (msg.getValueBigDecimal(chkAttrbNm, 0) == null) {
                        break;
                    } else {
                        attrbVal = msg.getValueBigDecimal(chkAttrbNm, 0).toString();
                        break;
                    }
                default:
                    attrbVal = msg.getValueString(chkAttrbNm, 0);
            }
            compVal.append(attrbVal);
            compVal.append(",");
        }

        return compVal.toString();
    }

    /**
     * checkPlnNmUnique
     * @param bizMsg NMAL7080SMsg
     * @param glblMsg NMAL7080SMsg
     * @return boolean
     */
    public static Boolean checkPlnNmUnique(NMAL7080CMsg bizMsg, NMAL7080SMsg glblMsg) {
        S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getSupplyAgreementName(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            if (!ZYPCommonFunc.hasValue(bizMsg.splyAgmtPlnPk)) {
                bizMsg.splyAgmtPlnNm.setErrorInfo(1, NMAM8296E, new String[] {"Plan Name" });
                return false;
            } else {
                List<BigDecimal> resultList = (List<BigDecimal>) ssmResult.getResultObject();
                for (BigDecimal resultPk : resultList) {
                    if (!(bizMsg.splyAgmtPlnPk.getValue().compareTo(resultPk) == 0)) {
                        bizMsg.splyAgmtPlnNm.setErrorInfo(1, NMAM8296E, new String[] {"Plan Name" });

                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * checkMdse
     * @param glblCmpyCd String
     * @param bizMsg NMAL7080CMsg
     * @return String
     */
    public static boolean checkMdse(String glblCmpyCd, NMAL7080CMsg bizMsg) {

        String mdseCd = "";
        boolean isOver = false;
        boolean checkFlag = true;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            isOver = false;
            ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();
            NMAL7080_ACMsg acMsg = bizMsg.A.no(i);

            mdseCd = acMsg.mdseCd_A.getValue();
            if (mdseCd.length() > 8) {
                mdseCd = mdseCd.substring(0, 8);
                isOver = true;
            }
            ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd);
            ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
            if (ordTakeOutTMsg == null) {
                mdseCd = acMsg.mdseCd_A.getValue();

                MDSETMsg mdseInTMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);
                MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
                if (mdseOutTMsg == null) {
                    acMsg.mdseCd_A.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise" });
                    checkFlag = false;
                    continue;
                }
            } else {
                if (isOver) {
                    acMsg.mdseCd_A.setErrorInfo(1, NMAM8216E);
                    checkFlag = false;
                    continue;
                }

            }

        }

        return checkFlag;

    }

    /**
     * checkDocTp
     * @param glblCmpyCd String
     * @param bizMsg NMAL7080CMsg
     * @return String
     */
    public static boolean checkDocTp(String glblCmpyCd, NMAL7080CMsg bizMsg) {
        SPLY_AGMT_DOC_TPTMsg docInMsg = new SPLY_AGMT_DOC_TPTMsg();
        SPLY_AGMT_DOC_TPTMsg docOutMsg = new SPLY_AGMT_DOC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(docInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docInMsg.splyAgmtDocTpCd, bizMsg.splyAgmtDocTpCd);
        docOutMsg = (SPLY_AGMT_DOC_TPTMsg) S21CodeTableAccessor.findByKey(docInMsg);
        String hdrFlag = docOutMsg.hdrLvlQtyEntryFlg.getValue();
        String dtlFlg = docOutMsg.dtlLvlQtyEntryFlg.getValue();
        boolean checkFlag = true;
        if (ZYPConstant.FLG_ON_Y.equals(hdrFlag)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.annTermCapNum)) {
                bizMsg.annTermCapNum.setErrorInfo(1, ZZM9000E, new String[] {"Annual Term Cap"});
                return false;
            }
        }
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL7080_ACMsg acMsg = bizMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(hdrFlag)) {
                if (!SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER.equals(acMsg.splyAgmtFreqTpCd_A.getValue())) {
                    acMsg.splyAgmtFreqTpCd_A.setErrorInfo(1, NMAM8393E, new String[] {ZYPCodeDataUtil.getName(SPLY_AGMT_DOC_TP.class, glblCmpyCd, bizMsg.splyAgmtDocTpCd.getValue()), ZYPCodeDataUtil.getName(SPLY_AGMT_FREQ_TP.class, glblCmpyCd, SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER) });
                    checkFlag = false;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(dtlFlg)) {
                if (SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER.equals(acMsg.splyAgmtFreqTpCd_A.getValue())) {
                    acMsg.splyAgmtFreqTpCd_A.setErrorInfo(1, NMAM8394E, new String[] {ZYPCodeDataUtil.getName(SPLY_AGMT_DOC_TP.class, glblCmpyCd, bizMsg.splyAgmtDocTpCd.getValue()), ZYPCodeDataUtil.getName(SPLY_AGMT_FREQ_TP.class, glblCmpyCd, SPLY_AGMT_FREQ_TP.UPON_REQUEST_BY_CUSTOMER) });
                    checkFlag = false;
                }
            }
        }
        return checkFlag;
    }

    /**
     * checkDocTp
     * @param glblMsg NMAL7080SMsg
     * @return String
     */
    public static Boolean checkChangedHeaderValueExist(NMAL7080SMsg glblMsg) {
        if (checkChangeValue(glblMsg.splyAgmtPlnNm.getValue(), glblMsg.H.no(0).splyAgmtPlnNm.getValue()) || checkChangeValue(glblMsg.splyAgmtPlnShortNm.getValue(), glblMsg.H.no(0).splyAgmtPlnShortNm.getValue())
                || checkChangeValue(glblMsg.splyAgmtPlnTpCd.getValue(), glblMsg.H.no(0).splyAgmtPlnTpCd.getValue()) || checkChangeValue(glblMsg.effFromDt.getValue(), glblMsg.H.no(0).effFromDt.getValue())
                || checkChangeValue(glblMsg.effThruDt.getValue(), glblMsg.H.no(0).effThruDt.getValue()) || checkChangeValue(glblMsg.annTermCapNum.getValue(), glblMsg.H.no(0).annTermCapNum.getValue())
                || checkChangeValue(glblMsg.xxChkBox_AF.getValue(), glblMsg.H.no(0).xxChkBox_AF.getValue()) || checkChangeValue(glblMsg.splyAgmtPlnDescTxt.getValue(), glblMsg.H.no(0).splyAgmtPlnDescTxt.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Changed Value Exist Check
     * @param glblMsgALine NMAL7080_ASMsg
     * @param glblMsg NMAL7080SMsg
     * @return boolean
     */
    public static Boolean checkChangedDetailValueExist(NMAL7080_ASMsg glblMsgALine, NMAL7080SMsg glblMsg) {
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (checkChangeValue(glblMsgALine.splyAgmtPlnDtlPk_A.getValue(), glblMsg.B.no(i).splyAgmtPlnDtlPk_A.getValue())) {
                continue;
            }

            if (checkChangeValue(glblMsgALine.mdseCd_A.getValue(), glblMsg.B.no(i).mdseCd_A.getValue()) //
                    || checkChangeValue(glblMsgALine.splyAgmtFreqTpCd_A.getValue(), glblMsg.B.no(i).splyAgmtFreqTpCd_A.getValue()) //
                    || checkChangeValue(glblMsgALine.splyAgmtPlnFirstQty_A.getValue(), glblMsg.B.no(i).splyAgmtPlnFirstQty_A.getValue()) //
                    || checkChangeValue(glblMsgALine.splyAgmtPlnQty_A.getValue(), glblMsg.B.no(i).splyAgmtPlnQty_A.getValue()) //
                    || checkChangeValue(glblMsgALine.splyAgmtPlnSqNum_A.getValue(), glblMsg.B.no(i).splyAgmtPlnSqNum_A.getValue()) //
                    || checkChangeValue(glblMsgALine.effFromDt_A.getValue(), glblMsg.B.no(i).effFromDt_A.getValue()) //
                    || checkChangeValue(glblMsgALine.effThruDt_A.getValue(), glblMsg.B.no(i).effThruDt_A.getValue())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Changed Value Exist Check
     * @param str1 String
     * @param str2 String
     * @return boolean
     */
    public static Boolean checkChangeValue(String str1, String str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Changed Value Exist Check
     * @param str1 BigDecimal
     * @param str2 BigDecimal
     * @return boolean
     */
    public static Boolean checkChangeValue(BigDecimal str1, BigDecimal str2) {
        if (str1 == null) {
            if (str2 == null) {
                return false;
            } else {
                return true;
            }
        } else if (str1.equals(str2)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }
    
    // 2018/04/04 S21_NA#23336 Add Start
    /**
     * insert Price Group Detail
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     * @param index Global Msg index
     */
    public static BigDecimal calcAnnualTermCap(BigDecimal splyAgmtPlnPk, String glblCmpyCd) {
        S21SsmEZDResult ssmResult = NMAL7080Query.getInstance().getSupplyAgreementDetailFreq(splyAgmtPlnPk);
        if (ssmResult.isCodeNotFound()) {
            return new BigDecimal(0);
        }
        List<Map<?, ?>> resultList = (List<Map<?, ?>>) ssmResult.getResultObject();
        BigDecimal total = new BigDecimal(0);
        for (Map<?, ?> result : resultList) {
            SPLY_AGMT_FREQ_TPTMsg freqInMsg = new SPLY_AGMT_FREQ_TPTMsg();
            SPLY_AGMT_FREQ_TPTMsg freqOutMsg = new SPLY_AGMT_FREQ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(freqInMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(freqInMsg.splyAgmtFreqTpCd, (String) result.get("SPLY_AGMT_FREQ_TP_CD"));
            freqOutMsg = (SPLY_AGMT_FREQ_TPTMsg) S21CodeTableAccessor.findByKey(freqInMsg);
            total = total.add(((BigDecimal) result.get("SPLY_AGMT_PLN_QTY")).multiply((new BigDecimal(12)).divide(freqOutMsg.splyAgmtFreqMthAot.getValue())));
        }
        return total;
    }
    
    public static void setAnnTermCapNum(NMAL7080SMsg glblMsg, String glblCmpyCd) {
        if (ZYPCommonFunc.hasValue(glblMsg.annTermCapNum)) {
            return;
        }
        SPLY_AGMT_DOC_TPTMsg docInMsg = new SPLY_AGMT_DOC_TPTMsg();
        SPLY_AGMT_DOC_TPTMsg docOutMsg = new SPLY_AGMT_DOC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(docInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(docInMsg.splyAgmtDocTpCd, glblMsg.splyAgmtDocTpCd);
        docOutMsg = (SPLY_AGMT_DOC_TPTMsg) S21CodeTableAccessor.findByKey(docInMsg);
        String dtlFlg = docOutMsg.dtlLvlQtyEntryFlg.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(dtlFlg)) {
            ZYPEZDItemValueSetter.setValue(glblMsg.annTermCapNum, NMAL7080CommonLogic.calcAnnualTermCap(glblMsg.splyAgmtPlnPk.getValue(), glblCmpyCd));
        }
    }
    // 2018/04/04 S21_NA#23336 Add End
}
