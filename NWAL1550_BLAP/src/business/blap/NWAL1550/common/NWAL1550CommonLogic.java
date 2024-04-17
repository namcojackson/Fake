/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1550.common;

import static business.blap.NWAL1550.constant.NWAL1550Constant.BIZ_ID;
import static business.blap.NWAL1550.constant.NWAL1550Constant.ITEM_VRSN_NUM_NAME;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0006I;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0142E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NWAM0715E;
import static business.blap.NWAL1550.constant.NWAL1550Constant.NZZM0001W;
import static business.blap.NWAL1550.constant.NWAL1550Constant.ORD_INFO_FLG_EXIST;

import java.math.BigDecimal;
import java.util.List;

import business.blap.NWAL1550.NWAL1550CMsg;
import business.blap.NWAL1550.NWAL1550Query;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_LVL;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NWAL1550CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/17   Fujitsu         S.Ohki          Create          N/A
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2022/09/20   Hitachi         N.Takatsu       Update          QC#54883
 *</pre>
 */
public class NWAL1550CommonLogic {

    /**
     * GetCheck Result
     * @param bizMsg NWAL1550CMsg
     */
    public static void getOrderInfo(NWAL1550CMsg bizMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        
        // Get Order Information
        S21SsmEZDResult ssmResult = NWAL1550Query.getInstance().getOrderInfoAndPricingSummary(bizMsg);

        // has search result
        if (!ssmResult.isCodeNormal()) {
            bizMsg.setMessageInfo(NWAM0142E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdDplyFlg, ORD_INFO_FLG_EXIST);

        // Version Number PullDown Create
        ssmResult = NWAL1550Query.getInstance().getVersionNum(bizMsg);

        // has search result
        if (ssmResult.isCodeNormal()) {

            List<BigDecimal> pullList = (List<BigDecimal>) ssmResult.getResultObject();
            
            if (pullList.size() > bizMsg.diChkVrsnNum_CD.length()) {
                bizMsg.setMessageInfo(NWAM0715E, new String[] {ITEM_VRSN_NUM_NAME, String.valueOf(bizMsg.diChkVrsnNum_CD.length()) });
                return;
            }

            for (int i = 0; i < pullList.size(); i++) {
                BigDecimal diChkVrsnNum = (BigDecimal) pullList.get(i);
                
                bizMsg.diChkVrsnNum_CD.no(i).setValue(diChkVrsnNum);
                bizMsg.diChkVrsnNum_NM.no(i).setValue(diChkVrsnNum);

                if (i == 0) {
                    bizMsg.diChkVrsnNum_SL.setValue(diChkVrsnNum);
                    bizMsg.diChkVrsnNum_BK.setValue(diChkVrsnNum);
                }
            }
        } else {
            bizMsg.setMessageInfo(NWAM0006I);
            return;
        }

        NWAL1550CommonLogic.getCheckResult(bizMsg);
    }
    
    /**
     * GetCheck Result
     * @param bizMsg NWAL1550CMsg
     */
    public static void getCheckResult(NWAL1550CMsg bizMsg) {

        // Get Data Integrity Check Information
        S21SsmEZDResult ssmResult = NWAL1550Query.getInstance().getDataIntegrityCheckInfo(bizMsg, bizMsg.diChkVrsnNum_SL.getValue());
        
        // Get Data Integrity Results
        if (!ZYPCommonFunc.hasValue(bizMsg.dsCpoDiChkRsltHdrPk)) {
            return;
        }
       
        ssmResult = NWAL1550Query.getInstance().getDataIntegrityResult(bizMsg, bizMsg.dsCpoDiChkRsltHdrPk.getValue());

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NWAM0006I);
        } else if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
            bizMsg.setMessageInfo(NZZM0001W);
            bizMsg.A.setValidCount(bizMsg.A.length());
        }

        // Get Data Integrity Summary
        int warningHead = 0;
        int warningLine = 0;
        int errorHead = 0;
        int errorLine = 0;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            //set LineNum
            String lineNum = editDtlLineNum(bizMsg.A.no(i).diErrOrdPosnNum_A.getValue(), bizMsg.A.no(i).diErrCpoLineNum_A.getValue(), bizMsg.A.no(i).diErrCpoLineSubNum_A.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxLineNum_A, lineNum);

            // Warning
            if (DI_CHK_DTL_STS.WARNING.equals(bizMsg.A.no(i).diChkDtlStsCd_A.getValue())) {
                // Header
                if (DI_CHK_LVL.HEADER.equals(bizMsg.A.no(i).diChkLvlCd_A.getValue())) {
                    warningHead++;
                    // Line
                // 2022/09/20 QC#54883 Mod Start N.Takatsu
                //} else { if (DI_CHK_LVL.LINE.equals(bizMsg.A.no(i).diChkLvlCd_A.getValue())) {
                } else {    
                // 2022/09/20 QC#54883 Mod END N.Takatsu
                    warningLine++;
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_A, ZYPConstant.FLG_OFF_N);
            // Error
            } else if (DI_CHK_DTL_STS.ERROR.equals(bizMsg.A.no(i).diChkDtlStsCd_A.getValue())) {
                // Header
                if (DI_CHK_LVL.HEADER.equals(bizMsg.A.no(i).diChkLvlCd_A.getValue())) {
                    errorHead++;
                    // Line
                // 2022/09/20 QC#54883 Mod Start N.Takatsu
                } else {
                // } else { if (DI_CHK_LVL.LINE.equals(bizMsg.A.no(i).diChkLvlCd_A.getValue())) {
                // 2022/09/20 QC#54883 Mod Start N.Takatsu
                    errorLine++;
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_A, ZYPConstant.FLG_OFF_N);
            // Accept
            } else if (DI_CHK_DTL_STS.ACCEPTED.equals(bizMsg.A.no(i).diChkDtlStsCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxChkBox_A, ZYPConstant.FLG_ON_Y);
            }
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_WH, new BigDecimal(warningHead));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_WL, new BigDecimal(warningLine));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_EH, new BigDecimal(errorHead));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxNum_EL, new BigDecimal(errorLine));
    }


    /**
     * <pre>
     * @param dsOrdPosnNum      dsOrdPosnNum
     * @param dsCpoLineNum      dsCpoLineNum
     * @param dsCpoLineSubNum   dsCpoLineSubNum
     * @return  edited line# dsOrdPosnNum.dsCpoLineNum.dsCpoLineSubNum or dsOrdPosnNum.dsCpoLineNum
     * </pre>
     */
    public static String editDtlLineNum(String dsOrdPosnNum, String dsCpoLineNum, String dsCpoLineSubNum) {

        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            return "";
        }
        StringBuffer lineNum = new StringBuffer();
        concatWithSeparator(lineNum, dsOrdPosnNum, ".");

        if (!ZYPCommonFunc.hasValue(dsCpoLineNum)) {
            return lineNum.toString();
        }
        concatWithSeparator(lineNum, dsCpoLineNum, ".");

        if (ZYPCommonFunc.hasValue(dsCpoLineSubNum)) {
            concatWithSeparator(lineNum, dsCpoLineSubNum, ".");
        }
        return lineNum.toString();
    }

    private static void concatWithSeparator(StringBuffer addressBuffer, String element, String separator) {

        if (!S21StringUtil.isEmpty(element)) {
            if (addressBuffer.length() > 0) {
                addressBuffer.append(separator);
            }
            addressBuffer.append(element);
        }
    }

    // S21_NA#16035 Add Start
    /**
     * Set Authority
     * @param bizMsg NWAL1550CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1550CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }
    // S21_NA#16035 Add End
}
