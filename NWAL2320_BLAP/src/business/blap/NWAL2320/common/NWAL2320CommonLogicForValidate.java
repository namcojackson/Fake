package business.blap.NWAL2320.common;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import business.blap.NWAL2320.NWAL2320CMsg;
import business.blap.NWAL2320.NWAL2320SMsg;
import business.blap.NWAL2320.NWAL2320_ASMsg;
import business.blap.NWAL2320.NWAL2320_BSMsg;
import business.blap.NWAL2320.NWAL2320_CSMsg;
import business.blap.NWAL2320.NWAL2320_DSMsg;
import business.blap.NWAL2320.bean.NWAL2320_ImptDetailBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptHeaderBean;
import business.blap.NWAL2320.bean.NWAL2320_ImptRtrnDetailBean;
import business.blap.NWAL2320.constant.NWAL2320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_UPLD_TMPL_TP;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320CommonLogicForValidate {

    /**
     * checkValidate
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @return boolean
     */
    public static boolean checkValidate(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        if (NWAL2320CommonLogic.getCMsgArray(bizMsg).getValidCount() == 0) {
            bizMsg.setMessageInfo(NWAL2320Constant.NWAM0794E);
            return false;
        }

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            checkValidateForNewOrd(bizMsg, glblMsg);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            checkValidateForExsOrd(bizMsg, glblMsg);
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            checkValidateForNewRma(bizMsg, glblMsg);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            checkValidateForExsRma(bizMsg, glblMsg);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxErrNum_UL, getValidateCheckErrorNum(glblMsg, ordUpldTmplTpCd));

        return (bizMsg.xxErrNum_UL.getValueInt() == 0);
    }

    private static boolean checkValidateForNewOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;
        NWAL2320_ASMsg glblASMsg;
        String uploadErr, status;

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblASMsg = glblMsg.A.no(i);
            uploadErr = glblASMsg.xxComnScrColValTxt_CO.getValue();
            if (ZYPCommonFunc.hasValue(uploadErr)) {
                isSuccess = false;
            }
            status = uploadErr.length() == 0 ? NWAL2320Constant.LINE_STS_SUCCESS : NWAL2320Constant.LINE_STS_ERROR;
            ZYPEZDItemValueSetter.setValue(glblASMsg.ordUpldVldStsDescTxt_CO, status);
            ZYPEZDItemValueSetter.setValue(glblASMsg.xxDsMultMsgDplyTxt_CO, uploadErr);
        }
        return isSuccess;
    }

    private static boolean checkValidateForExsOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;
        NWAL2320_BSMsg glblBSMsg;
        String uploadErr, status;

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            glblBSMsg = glblMsg.B.no(i);
            uploadErr = glblBSMsg.xxComnScrColValTxt_EO.getValue();
            if (ZYPCommonFunc.hasValue(uploadErr)) {
                isSuccess = false;
            }
            status = uploadErr.length() == 0 ? NWAL2320Constant.LINE_STS_SUCCESS : NWAL2320Constant.LINE_STS_ERROR;
            ZYPEZDItemValueSetter.setValue(glblBSMsg.ordUpldVldStsDescTxt_EO, status);
            ZYPEZDItemValueSetter.setValue(glblBSMsg.xxDsMultMsgDplyTxt_EO, uploadErr);
        }
        return isSuccess;
    }

    private static boolean checkValidateForNewRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;
        NWAL2320_CSMsg glblCSMsg;
        String uploadErr, status;

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblCSMsg = glblMsg.C.no(i);
            uploadErr = glblCSMsg.xxComnScrColValTxt_NR.getValue();
            if (ZYPCommonFunc.hasValue(uploadErr)) {
                isSuccess = false;
            }
            status = uploadErr.length() == 0 ? NWAL2320Constant.LINE_STS_SUCCESS : NWAL2320Constant.LINE_STS_ERROR;
            ZYPEZDItemValueSetter.setValue(glblCSMsg.ordUpldVldStsDescTxt_NR, status);
            ZYPEZDItemValueSetter.setValue(glblCSMsg.xxDsMultMsgDplyTxt_NR, uploadErr);
        }
        return isSuccess;
    }

    private static boolean checkValidateForExsRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;
        NWAL2320_DSMsg glblDSMsg;
        String uploadErr, status;

        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            glblDSMsg = glblMsg.D.no(i);
            uploadErr = glblDSMsg.xxComnScrColValTxt_ER.getValue();
            if (ZYPCommonFunc.hasValue(uploadErr)) {
                isSuccess = false;
            }
            status = uploadErr.length() == 0 ? NWAL2320Constant.LINE_STS_SUCCESS : NWAL2320Constant.LINE_STS_ERROR;
            ZYPEZDItemValueSetter.setValue(glblDSMsg.ordUpldVldStsDescTxt_ER, status);
            ZYPEZDItemValueSetter.setValue(glblDSMsg.xxDsMultMsgDplyTxt_ER, uploadErr);
        }
        return isSuccess;
    }

    /**
     * setValidateErr
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @param imptBeansMap LinkedHashMap<String, NWAL2320_ImptHeaderBean>
     */
    public static void setValidateErr(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, LinkedHashMap<String, NWAL2320_ImptHeaderBean> imptBeansMap) {
        String errString, status, ordUpldTmplTpCd;
        NWAL2320_ASMsg glblASMsg;
        NWAL2320_BSMsg glblBSMsg;
        NWAL2320_CSMsg glblCSMsg;
        NWAL2320_DSMsg glblDSMsg;

        ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        for (NWAL2320_ImptHeaderBean hdrBean : imptBeansMap.values()) {

            if (hdrBean.isUpldTpSlsOrd()) {
                for (NWAL2320_ImptDetailBean dtlBean : hdrBean.detailBeanList) {
                    errString = NWAL2320CommonLogic.createErrString(dtlBean.rowErrList, bizMsg.maxLgNum.getValueInt());
                    status = errString.length() == 0 ? NWAL2320Constant.LINE_STS_SUCCESS : NWAL2320Constant.LINE_STS_ERROR;

                    if (hdrBean.isUpldTpNew()) {
                        glblASMsg = (NWAL2320_ASMsg) dtlBean.rowData;
                        ZYPEZDItemValueSetter.setValue(glblASMsg.xxDsMultMsgDplyTxt_CO, errString);
                        ZYPEZDItemValueSetter.setValue(glblASMsg.ordUpldVldStsDescTxt_CO, status);
                    } else {
                        glblBSMsg = (NWAL2320_BSMsg) dtlBean.rowData;
                        ZYPEZDItemValueSetter.setValue(glblBSMsg.xxDsMultMsgDplyTxt_EO, errString);
                        ZYPEZDItemValueSetter.setValue(glblBSMsg.ordUpldVldStsDescTxt_EO, status);
                    }
                }
            } else {
                for (NWAL2320_ImptRtrnDetailBean rtnDtlBean : hdrBean.rtrnDtlBeanList) {
                    errString = NWAL2320CommonLogic.createErrString(rtnDtlBean.rowErrList, bizMsg.maxLgNum.getValueInt());
                    status = errString.length() == 0 ? NWAL2320Constant.LINE_STS_SUCCESS : NWAL2320Constant.LINE_STS_ERROR;

                    if (hdrBean.isUpldTpNew()) {
                        glblCSMsg = (NWAL2320_CSMsg) rtnDtlBean.rowData;
                        ZYPEZDItemValueSetter.setValue(glblCSMsg.xxDsMultMsgDplyTxt_NR, errString);
                        ZYPEZDItemValueSetter.setValue(glblCSMsg.ordUpldVldStsDescTxt_NR, status);
                    } else {
                        glblDSMsg = (NWAL2320_DSMsg) rtnDtlBean.rowData;
                        ZYPEZDItemValueSetter.setValue(glblDSMsg.xxDsMultMsgDplyTxt_ER, errString);
                        ZYPEZDItemValueSetter.setValue(glblDSMsg.ordUpldVldStsDescTxt_ER, status);
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxErrNum_UL, getValidateCheckErrorNum(glblMsg, ordUpldTmplTpCd));
    }

    private static BigDecimal getValidateCheckErrorNum(NWAL2320SMsg glblMsg, String ordUpldTmplTpCd) {
        BigDecimal errNum = BigDecimal.ZERO;

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxDsMultMsgDplyTxt_CO)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).xxDsMultMsgDplyTxt_EO)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).xxDsMultMsgDplyTxt_NR)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).xxDsMultMsgDplyTxt_ER)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        }

        return errNum;
    }



}
