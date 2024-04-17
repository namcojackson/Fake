/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2260.common;

import static business.blap.NWAL2260.constant.NWAL2260Constant.DS_IMPT_DTL_EXT_ATTRB;
import static business.blap.NWAL2260.constant.NWAL2260Constant.DS_IMPT_EXT_ATTRB;
import static business.blap.NWAL2260.constant.NWAL2260Constant.NUM_LEN_2;
import static business.blap.NWAL2260.constant.NWAL2260Constant.NWAL2260_ATTRB_CTRL_ID;
import static business.blap.NWAL2260.constant.NWAL2260Constant.NWAM0790E;
import static business.blap.NWAL2260.constant.NWAL2260Constant.NWAM0791E;
import static business.blap.NWAL2260.constant.NWAL2260Constant.NWZM1107E;
import static business.blap.NWAL2260.constant.NWAL2260Constant.NZZM0002I;
import static business.blap.NWAL2260.constant.NWAL2260Constant.PRM_NM_CPO_SRC_TP_CD;
import static business.blap.NWAL2260.constant.NWAL2260Constant.PRM_NM_DS_IMPT_ORD_PK;
import static business.blap.NWAL2260.constant.NWAL2260Constant.PRM_NM_ORD_SRC_REF_NUM;
import static business.blap.NWAL2260.constant.NWAL2260Constant.SCR_LB_NM_HEADER;
import static business.blap.NWAL2260.constant.NWAL2260Constant.STR_PERIOD;
import static business.blap.NWAL2260.constant.NWAL2260Constant.STR_ZERO;
import static business.blap.NWAL2260.constant.NWAL2260Constant.ZZZM9004E;
import static business.blap.NWAL2260.constant.NWAL2260Constant.ZZZM9005W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2260.NWAL2260CMsg;
import business.blap.NWAL2260.NWAL2260Query;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Import Attribute Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma         Create          N/A
 * 2018/11/28   Fujitsu         K.Ishizuka      Update          S21_NA#28899
 *</pre>
 */
public class NWAL2260CommonLogic {

    /**
     * Clear Message
     * @param cMsg NWAL2260CMsg
     */
    public static void clearMsg(NWAL2260CMsg cMsg) {

        NWAL2260CMsg cloneCMsg = (NWAL2260CMsg) cMsg.clone();
        cMsg.clear();

        setValue(cMsg.ordSrcRefNum, cloneCMsg.ordSrcRefNum);
        setValue(cMsg.cpoSrcTpCd, cloneCMsg.cpoSrcTpCd);
        setValue(cMsg.dsImptOrdPk, cloneCMsg.dsImptOrdPk);
        setValue(cMsg.xxReadOnlyFlg, cloneCMsg.xxReadOnlyFlg);
        setValue(cMsg.dsImptOrdDtlPk, cloneCMsg.dsImptOrdDtlPk);
        setValue(cMsg.ordSrcRefLineNum, cloneCMsg.ordSrcRefLineNum);
        setValue(cMsg.ordSrcRefLineSubNum, cloneCMsg.ordSrcRefLineSubNum);
        setValue(cMsg.glblCmpyCd, cloneCMsg.glblCmpyCd);
        setValue(cMsg.xxEdtModeFlg, cloneCMsg.xxEdtModeFlg);
    }

    /**
     * Check Parameters
     * @param cMsg NWAL2260CMsg
     * @return boolean
     */
    public static boolean checkParams(NWAL2260CMsg cMsg) {

        if (!hasValue(cMsg.dsImptOrdPk)) {
            if (!hasValue(cMsg.ordSrcRefNum) && !hasValue(cMsg.cpoSrcTpCd)) {
                cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_DS_IMPT_ORD_PK });
                return false;
            } else if (!hasValue(cMsg.ordSrcRefNum)) {
                cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_ORD_SRC_REF_NUM });
                return false;
            } else if (!hasValue(cMsg.cpoSrcTpCd)) {
                cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_CPO_SRC_TP_CD });
                return false;
            }
        }
        return true;
    }

    /**
     * Check Parameters For After Search
     * @param cMsg NWAL2260CMsg
     * @return boolean
     */
    public static boolean checkParamsAftrSearch(NWAL2260CMsg cMsg) {

        if (ZYPConstant.FLG_ON_1.equals(cMsg.xxEdtModeFlg.getValue())) {
            if (!hasValue(cMsg.ezUpTime_H)) {
                if (!hasValue(cMsg.dsImptOrdPk)) {
                    cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_DS_IMPT_ORD_PK });
                    return false;
                } else if (!hasValue(cMsg.ordSrcRefNum)) {
                    cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_ORD_SRC_REF_NUM });
                    return false;
                } else if (!hasValue(cMsg.cpoSrcTpCd)) {
                    cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_CPO_SRC_TP_CD });
                    return false;
                }
            }
        } else {
            if (!hasValue(cMsg.ezUpTime_D)) {
                if (!hasValue(cMsg.dsImptOrdPk)) {
                    cMsg.setMessageInfo(NWZM1107E, new String[] {PRM_NM_DS_IMPT_ORD_PK });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NWAL2260CMsg
     */
    public static void setInitParams(NWAL2260CMsg cMsg) {

        if (!hasValue(cMsg.dsImptOrdDtlPk)) {
            setValue(cMsg.xxEdtModeFlg, ZYPConstant.FLG_ON_1);
        } else {
            setValue(cMsg.xxEdtModeFlg, ZYPConstant.FLG_OFF_0);
        }
    }

    /**
     * Get Search Data
     * @param cMsg NWAL2260CMsg
     * @param isSubmit boolean
     */
    public static void getSearchData(NWAL2260CMsg cMsg, boolean isSubmit) {

        S21SsmEZDResult ssmResult;
        if (ZYPConstant.FLG_ON_1.equals(cMsg.xxEdtModeFlg.getValue())) {
            ssmResult = NWAL2260Query.getInstance().getSearchDataForHeader(cMsg);
        } else {
            ssmResult = NWAL2260Query.getInstance().getSearchDataForDetail(cMsg);
        }

        if (!isSubmit) {
            if (ssmResult.isCodeNormal()) {
                cMsg.setMessageInfo(NZZM0002I);
            } else {
                // No result
                cMsg.setMessageInfo(ZZZM9005W);
            }
        }
        setHeader(cMsg);
        seXxRefNumTxt(cMsg);
        setScrLbNm(cMsg);
    }

    /**
     * Set Header
     * @param cMsg NWAL2260CMsg
     */
    private static void setHeader(NWAL2260CMsg cMsg) {

        if (!hasValue(cMsg.dsImptOrdPk_H)) {
            setValue(cMsg.dsImptOrdPk_H, cMsg.dsImptOrdPk);
        }
        if (!hasValue(cMsg.ordSrcRefNum_H)) {
            setValue(cMsg.ordSrcRefNum_H, cMsg.ordSrcRefNum);
        }

        if (ZYPConstant.FLG_OFF_0.equals(cMsg.xxEdtModeFlg.getValue())) {
            if (!hasValue(cMsg.ordSrcRefLineNum_H)) {
                setValue(cMsg.ordSrcRefLineNum_H, cMsg.ordSrcRefLineNum);
            }
            if (!hasValue(cMsg.ordSrcRefLineSubNum_H)) {
                setValue(cMsg.ordSrcRefLineSubNum_H, cMsg.ordSrcRefLineSubNum);
            }
        }
    }

    /**
     * Set xxRefNumTxt
     * @param cMsg NWAL2260CMsg
     */
    private static void seXxRefNumTxt(NWAL2260CMsg cMsg) {
        if (!hasValue(cMsg.dsOrdPosnNum) || !hasValue(cMsg.dsCpoLineNum)) {
            return;
        }
        String xxRefNumTxt = cMsg.dsOrdPosnNum.getValue() + STR_PERIOD + cMsg.dsCpoLineNum.getValue().toString();
        setValue(cMsg.xxRefNumTxt, xxRefNumTxt);
    }

    /**
     * Set scrLbNm
     * @param cMsg NWAL2260CMsg
     */
    private static void setScrLbNm(NWAL2260CMsg cMsg) {

        int i = 1;
        if (!hasValue(cMsg.scrLbNm_01)) {
            setValue(cMsg.scrLbNm_01, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), 2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_02)) {
            setValue(cMsg.scrLbNm_02, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_03)) {
            setValue(cMsg.scrLbNm_03, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_04)) {
            setValue(cMsg.scrLbNm_04, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_05)) {
            setValue(cMsg.scrLbNm_05, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_06)) {
            setValue(cMsg.scrLbNm_06, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_07)) {
            setValue(cMsg.scrLbNm_07, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_08)) {
            setValue(cMsg.scrLbNm_08, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_09)) {
            setValue(cMsg.scrLbNm_09, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_10)) {
            setValue(cMsg.scrLbNm_10, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_11)) {
            setValue(cMsg.scrLbNm_11, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_12)) {
            setValue(cMsg.scrLbNm_12, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_13)) {
            setValue(cMsg.scrLbNm_13, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_14)) {
            setValue(cMsg.scrLbNm_14, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
        if (!hasValue(cMsg.scrLbNm_15)) {
            setValue(cMsg.scrLbNm_15, SCR_LB_NM_HEADER + ZYPCommonFunc.leftPad(Integer.toString(i++), NUM_LEN_2, STR_ZERO));
        } else {
            i++;
        }
    }

    /**
     * Do Save Data
     * @param cMsg NWAL2260CMsg
     */
    public static void doSave(NWAL2260CMsg cMsg) {

        if (ZYPConstant.FLG_ON_1.equals(cMsg.xxEdtModeFlg.getValue())) {
            if (hasValue(cMsg.ezUpTime_H)) {
                updateForHeader(cMsg);
            } else {
                insertForHeader(cMsg);
            }
        } else {
            if (hasValue(cMsg.ezUpTime_D)) {
                updateForDetail(cMsg);
            } else {
                insertForDetail(cMsg);
            }
        }
    }

    /**
     * Insert For Header
     * @param cMsg NWAL2260CMsg
     */
    private static void insertForHeader(NWAL2260CMsg cMsg) {

        DS_IMPT_EXT_ATTRBTMsg insMsg = new DS_IMPT_EXT_ATTRBTMsg();

        setParamForHeader(cMsg, insMsg, true);

        S21FastTBLAccessor.insert(insMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0790E, new String[] {DS_IMPT_EXT_ATTRB });
            return;
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    /**
     * Update For Header
     * @param cMsg NWAL2260CMsg
     */
    private static void updateForHeader(NWAL2260CMsg cMsg) {

        DS_IMPT_EXT_ATTRBTMsg inMsg = new DS_IMPT_EXT_ATTRBTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.dsImptOrdPk, cMsg.dsImptOrdPk_H);

        DS_IMPT_EXT_ATTRBTMsg updTMsg = (DS_IMPT_EXT_ATTRBTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_H.getValue(), cMsg.ezUpTimeZone_H.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        setParamForHeader(cMsg, updTMsg, false);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0791E, new String[] {DS_IMPT_EXT_ATTRB });
            return;
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    /**
     * Insert For Detail
     * @param cMsg NWAL2260CMsg
     */
    private static void insertForDetail(NWAL2260CMsg cMsg) {

        DS_IMPT_DTL_EXT_ATTRBTMsg insMsg = new DS_IMPT_DTL_EXT_ATTRBTMsg();

        setParamForDetail(cMsg, insMsg, true);

        S21FastTBLAccessor.insert(insMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0790E, new String[] {DS_IMPT_DTL_EXT_ATTRB });
            return;
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    /**
     * Update For Detail
     * @param cMsg NWAL2260CMsg
     */
    private static void updateForDetail(NWAL2260CMsg cMsg) {

        DS_IMPT_DTL_EXT_ATTRBTMsg inMsg = new DS_IMPT_DTL_EXT_ATTRBTMsg();
        setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(inMsg.dsImptOrdDtlPk, cMsg.dsImptOrdDtlPk);

        DS_IMPT_DTL_EXT_ATTRBTMsg updTMsg = (DS_IMPT_DTL_EXT_ATTRBTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (updTMsg == null) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_D.getValue(), cMsg.ezUpTimeZone_D.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }
        setParamForDetail(cMsg, updTMsg, false);
        S21FastTBLAccessor.update(updTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NWAM0791E, new String[] {DS_IMPT_DTL_EXT_ATTRB });
            return;
        }
        cMsg.setMessageInfo(NZZM0002I);
    }

    /**
     * Set Parameter For Header
     * @param cMsg NWAL2260CMsg
     * @param tMsg DS_IMPT_EXT_ATTRBTMsg
     * @param isIns boolean
     */
    private static void setParamForHeader(NWAL2260CMsg cMsg, DS_IMPT_EXT_ATTRBTMsg tMsg, boolean isIns) {

        if (isIns) {
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.dsImptOrdPk, cMsg.dsImptOrdPk_H);
            setValue(tMsg.cpoSrcTpCd, cMsg.cpoSrcTpCd);
            setValue(tMsg.ordSrcRefNum, cMsg.ordSrcRefNum_H);
            setValue(tMsg.attrbCtrlId, ZYPCodeDataUtil.getVarCharConstValue(NWAL2260_ATTRB_CTRL_ID, cMsg.glblCmpyCd.getValue()));
        }

        setValue(tMsg.dsImptAttrbTxt_01, cMsg.dsImptAttrbTxt_01);
        setValue(tMsg.dsImptAttrbTxt_02, cMsg.dsImptAttrbTxt_02);
        setValue(tMsg.dsImptAttrbTxt_03, cMsg.dsImptAttrbTxt_03);
        setValue(tMsg.dsImptAttrbTxt_04, cMsg.dsImptAttrbTxt_04);
        setValue(tMsg.dsImptAttrbTxt_05, cMsg.dsImptAttrbTxt_05);
        setValue(tMsg.dsImptAttrbTxt_06, cMsg.dsImptAttrbTxt_06);
        setValue(tMsg.dsImptAttrbTxt_07, cMsg.dsImptAttrbTxt_07);
        setValue(tMsg.dsImptAttrbTxt_08, cMsg.dsImptAttrbTxt_08);
        setValue(tMsg.dsImptAttrbTxt_09, cMsg.dsImptAttrbTxt_09);
        setValue(tMsg.dsImptAttrbTxt_10, cMsg.dsImptAttrbTxt_10);
        setValue(tMsg.dsImptAttrbTxt_11, cMsg.dsImptAttrbTxt_11);
        setValue(tMsg.dsImptAttrbTxt_12, cMsg.dsImptAttrbTxt_12);
        setValue(tMsg.dsImptAttrbTxt_13, cMsg.dsImptAttrbTxt_13);
        setValue(tMsg.dsImptAttrbTxt_14, cMsg.dsImptAttrbTxt_14);
        setValue(tMsg.dsImptAttrbTxt_15, cMsg.dsImptAttrbTxt_15);

        setValue(tMsg.idocPoDocNum, cMsg.idocPoDocNum);
        setValue(tMsg.idocPoOrgValTxt_01, cMsg.idocPoOrgValTxt_01);
        setValue(tMsg.idocPoOrgValTxt_02, cMsg.idocPoOrgValTxt_02);
        setValue(tMsg.idocPoOrdRsnCd, cMsg.idocPoOrdRsnCd);
        setValue(tMsg.idocPoCustRefNum, cMsg.idocPoCustRefNum);
        setValue(tMsg.idocPoCustRefDt, cMsg.idocPoCustRefDt);
        setValue(tMsg.idocPoPtnrTpCd_01, cMsg.idocPoPtnrTpCd_01);
        setValue(tMsg.idocPoPtnrTpCd_02, cMsg.idocPoPtnrTpCd_02);
        setValue(tMsg.idocPoPtnrTpCd_03, cMsg.idocPoPtnrTpCd_03);
        setValue(tMsg.idocPoPtnrTpCd_04, cMsg.idocPoPtnrTpCd_04);
        setValue(tMsg.idocPoPtnrTpCd_05, cMsg.idocPoPtnrTpCd_05);
        setValue(tMsg.idocPoPtnrTpCd_06, cMsg.idocPoPtnrTpCd_06);
        setValue(tMsg.idocPoPtnrNum_01, cMsg.idocPoPtnrNum_01);
        setValue(tMsg.idocPoPtnrNum_02, cMsg.idocPoPtnrNum_02);
        setValue(tMsg.idocPoPtnrNum_03, cMsg.idocPoPtnrNum_03);
        setValue(tMsg.idocPoPtnrNum_04, cMsg.idocPoPtnrNum_04);
        setValue(tMsg.idocPoPtnrNum_05, cMsg.idocPoPtnrNum_05);
        setValue(tMsg.idocPoPtnrNum_06, cMsg.idocPoPtnrNum_06);
        setValue(tMsg.idocPtnrCustRefTxt_01, cMsg.idocPtnrCustRefTxt_01);
        setValue(tMsg.idocPtnrCustRefTxt_02, cMsg.idocPtnrCustRefTxt_02);
        setValue(tMsg.idocPtnrCustRefTxt_03, cMsg.idocPtnrCustRefTxt_03);
        setValue(tMsg.idocPtnrCustRefTxt_04, cMsg.idocPtnrCustRefTxt_04);
        setValue(tMsg.idocPtnrCustRefTxt_05, cMsg.idocPtnrCustRefTxt_05);
        setValue(tMsg.idocPtnrCustRefTxt_06, cMsg.idocPtnrCustRefTxt_06);
        setValue(tMsg.idocPoPtnrId_01, cMsg.idocPoPtnrId_01);
        setValue(tMsg.idocPoPtnrId_02, cMsg.idocPoPtnrId_02);
        setValue(tMsg.idocPoPtnrId_03, cMsg.idocPoPtnrId_03);
        setValue(tMsg.idocPoPtnrId_04, cMsg.idocPoPtnrId_04);
        setValue(tMsg.idocPoPtnrId_05, cMsg.idocPoPtnrId_05);
        setValue(tMsg.idocPoPtnrId_06, cMsg.idocPoPtnrId_06);
        setValue(tMsg.idocPtnrCtacNm_01, cMsg.idocPtnrCtacNm_01);
        setValue(tMsg.idocPtnrCtacNm_02, cMsg.idocPtnrCtacNm_02);
        setValue(tMsg.idocPtnrCtacNm_03, cMsg.idocPtnrCtacNm_03);
        setValue(tMsg.idocPtnrCtacNm_04, cMsg.idocPtnrCtacNm_04);
        setValue(tMsg.idocPtnrCtacNm_05, cMsg.idocPtnrCtacNm_05);
        setValue(tMsg.idocPtnrCtacNm_06, cMsg.idocPtnrCtacNm_06);
        setValue(tMsg.idocPtnrTelNum_01, cMsg.idocPtnrTelNum_01);
        setValue(tMsg.idocPtnrTelNum_02, cMsg.idocPtnrTelNum_02);
        setValue(tMsg.idocPtnrTelNum_03, cMsg.idocPtnrTelNum_03);
        setValue(tMsg.idocPtnrTelNum_04, cMsg.idocPtnrTelNum_04);
        setValue(tMsg.idocPtnrTelNum_05, cMsg.idocPtnrTelNum_05);
        setValue(tMsg.idocPtnrTelNum_06, cMsg.idocPtnrTelNum_06);
        setValue(tMsg.idocFirstLineAddr, cMsg.idocFirstLineAddr);
        setValue(tMsg.idocScdLineAddr, cMsg.idocScdLineAddr);
        setValue(tMsg.idocPtnrCtyNm, cMsg.idocPtnrCtyNm);
        setValue(tMsg.idocPtnrStCd, cMsg.idocPtnrStCd);
        setValue(tMsg.idocPtnrPostCd, cMsg.idocPtnrPostCd);
        setValue(tMsg.idocPtnrCtryCd, cMsg.idocPtnrCtryCd);
        setValue(tMsg.idocPtnrCntyNm, cMsg.idocPtnrCntyNm); // 2018/11/28 S21_NA#28899 Add
        setValue(tMsg.idocPoDtValTxt, cMsg.idocPoDtValTxt);
        setValue(tMsg.idocPoDelyCondCd, cMsg.idocPoDelyCondCd);
        setValue(tMsg.idocPoDelyCondNm, cMsg.idocPoDelyCondNm);
        setValue(tMsg.idocPoNoteTxt, cMsg.idocPoNoteTxt);
        setValue(tMsg.idocPoRcpntPtnrNum, cMsg.idocPoRcpntPtnrNum);

    }

    /**
     * Set Parameter For Detail
     * @param cMsg NWAL2260CMsg
     * @param tMsg DS_IMPT_DTL_EXT_ATTRBTMsg
     * @param isIns boolean
     */
    private static void setParamForDetail(NWAL2260CMsg cMsg, DS_IMPT_DTL_EXT_ATTRBTMsg tMsg, boolean isIns) {

        if (isIns) {
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.dsImptOrdDtlPk, cMsg.dsImptOrdDtlPk);
            setValue(tMsg.dsImptOrdPk, cMsg.dsImptOrdPk_H);
            setValue(tMsg.ordSrcRefLineNum, cMsg.ordSrcRefLineNum_H);
            setValue(tMsg.ordSrcRefLineSubNum, cMsg.ordSrcRefLineSubNum_H);
            setValue(tMsg.attrbCtrlId, ZYPCodeDataUtil.getVarCharConstValue(NWAL2260_ATTRB_CTRL_ID, cMsg.glblCmpyCd.getValue()));
        }

        setValue(tMsg.dsImptDtlAttrbTxt_01, cMsg.dsImptDtlAttrbTxt_01);
        setValue(tMsg.dsImptDtlAttrbTxt_02, cMsg.dsImptDtlAttrbTxt_02);
        setValue(tMsg.dsImptDtlAttrbTxt_03, cMsg.dsImptDtlAttrbTxt_03);
        setValue(tMsg.dsImptDtlAttrbTxt_04, cMsg.dsImptDtlAttrbTxt_04);
        setValue(tMsg.dsImptDtlAttrbTxt_05, cMsg.dsImptDtlAttrbTxt_05);
        setValue(tMsg.dsImptDtlAttrbTxt_06, cMsg.dsImptDtlAttrbTxt_06);
        setValue(tMsg.dsImptDtlAttrbTxt_07, cMsg.dsImptDtlAttrbTxt_07);
        setValue(tMsg.dsImptDtlAttrbTxt_08, cMsg.dsImptDtlAttrbTxt_08);
        setValue(tMsg.dsImptDtlAttrbTxt_09, cMsg.dsImptDtlAttrbTxt_09);
        setValue(tMsg.dsImptDtlAttrbTxt_10, cMsg.dsImptDtlAttrbTxt_10);
        setValue(tMsg.dsImptDtlAttrbTxt_11, cMsg.dsImptDtlAttrbTxt_11);
        setValue(tMsg.dsImptDtlAttrbTxt_12, cMsg.dsImptDtlAttrbTxt_12);
        setValue(tMsg.dsImptDtlAttrbTxt_13, cMsg.dsImptDtlAttrbTxt_13);
        setValue(tMsg.dsImptDtlAttrbTxt_14, cMsg.dsImptDtlAttrbTxt_14);
        setValue(tMsg.dsImptDtlAttrbTxt_15, cMsg.dsImptDtlAttrbTxt_15);
        setValue(tMsg.idocPoDtlLineRefNum, cMsg.idocPoDtlLineRefNum);
        setValue(tMsg.idocPoDtlMatNum_01, cMsg.idocPoDtlMatNum_01);
        setValue(tMsg.idocPoDtlMatNum_02, cMsg.idocPoDtlMatNum_02);
        setValue(tMsg.idocPoDtlOrdQty, cMsg.idocPoDtlOrdQty);
        setValue(tMsg.idocPoDtlUomCd, cMsg.idocPoDtlUomCd);
        setValue(tMsg.idocPoDtlDelyPrtyNm, cMsg.idocPoDtlDelyPrtyNm);
    }
}
