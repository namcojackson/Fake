/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6860.common;

import static business.blap.NMAL6860.constant.NMAL6860Constant.ACC_TYPE_LIABILITY;
import static business.blap.NMAL6860.constant.NMAL6860Constant.ACC_TYPE_PREPAY;
import static business.blap.NMAL6860.constant.NMAL6860Constant.ACC_TYPE_VNDRETURN;
import static business.blap.NMAL6860.constant.NMAL6860Constant.ADDRESS_LENGTH;
import static business.blap.NMAL6860.constant.NMAL6860Constant.BUSINESS_ID;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_ACCT_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_ACCT_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_AFFL_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_AFFL_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_BR_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_BR_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_CC_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_CC_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_CH_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_CH_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_CMPY_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_CMPY_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_EXTN_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_EXTN_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_PROD_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_PROD_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_PROJ_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DB_COLUMN_COA_PROJ_DPLY_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.DEF_INTL_POST_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.LIABILITY_ACCOUNT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_ACCT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_AFFL;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_BR;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_CC;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_CH;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_CMPY;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_EXTN;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_PROD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_PROJ;
import static business.blap.NMAL6860.constant.NMAL6860Constant.MSG_PARAM_SEGMENT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NLCM0065E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NLCM0210E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NLCM0211E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAL6860_ACCT_INV_STS_CD_OPEN;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAL6860_ADDR_CHK_FLG;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0013E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0163E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0308E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM0803E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM8359E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM8454E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMAM8662E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMZM0292E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.NMZM0293E;
import static business.blap.NMAL6860.constant.NMAL6860Constant.PREPAY_ACCOUNT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_ACCT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_AFFL;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_BR;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_CC;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_CH;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_CMPY;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_EXTN;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_PROD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_ELEMENT_LENGTH_PROJ;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD;
import static business.blap.NMAL6860.constant.NMAL6860Constant.SEGMENT_TOKEN_LIST_SIZE;
import static business.blap.NMAL6860.constant.NMAL6860Constant.VAR_CHAR_KEY_DELIMITER;
import static business.blap.NMAL6860.constant.NMAL6860Constant.VENDOR_RETURN_ACCOUNT;
import static business.blap.NMAL6860.constant.NMAL6860Constant.ZZZM9025E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.CREATE_MODE;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.DEF_THRU_DT;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZC2010_PROC_MODE_CRAT;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZC2010_PROC_MODE_UPD;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0127E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.NMZM0333E;
import static com.canon.cusa.s21.api.NMZ.NMZC201001.constant.NMZC201001Constant.UPDATE_MODE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6860.NMAL6860CMsg;
import business.blap.NMAL6860.NMAL6860Query;
import business.blap.NMAL6860.NMAL6860SMsg;
import business.blap.NMAL6860.NMAL6860_ACMsg;
import business.blap.NMAL6860.NMAL6860_BCMsg;
import business.blap.NMAL6860.NMAL6860_ESMsg;
import business.db.CTRYTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.PRNT_VNDTMsg;
import business.db.PRNT_VND_TPTMsg;
import business.db.STTMsg;
import business.db.VNDTMsg;
import business.db.VND_TP_RELNTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC003001PMsg;
import business.parts.NMZC201001PMsg;
import business.parts.NMZC201002PMsg;
import business.parts.NMZC201003PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_DefaultBillShipListPMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC201001.NMZC201001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * NMAL6860 Supplier Entry
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/04   CITS            M.Ouchi         Create          N/A
 * 2016/08/04   Hitachi         Y.Takeno        Update          QC#12765
 * 08/09/2016   CITS            S.Endo          Update          QC#10839
 * 2016/08/22   CITS            T.Gotoda        Update          QC#12215
 * 09/06/2016   CITS            T.Gotoda        Update          QC#13264
 * 09/15/2016   CITS            T.Gotoda        Update          QC#13313
 * 09/26/2016   CITS            T.Gotoda        Update          QC#13163
 * 10/03/2016   CITS            R.Shimamoto     Update          QC#12768
 * 02/09/2017   CITS            R.Shimamoto     Update          QC#16594
 * 06/11/2018   CITS            K.Ogino         Update          QC#26498
 * 2018/08/07   CITS            K.Ogino         Update          QC#27280
 * 2019/03/20   Fujitsu         H.Ikeda         Update          QC#23696
 * 2019/12/06   Fujitsu         R.Nakamura      Update          QC#54971
 * 2019/12/23   Fujitsu         R.Nakamura      Update          QC#54971-1
 * 2020/02/28   Fujitsu         C.Hara          Update          QC#55971
 * 2020/08/31   CITS            R.Kurahashi     Update          QC#57546
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2021/01/28   CITS            A.Marte         Update          QC#58163
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * 2022/11/14   CITS            D.Mamaril       Update          QC#60617
 * </pre>
 */
public class NMAL6860CommonLogic {

    @SuppressWarnings("unchecked")
    public static void createPulldownList(Class codeTableInterfaceClass, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {

        try {
            ZYPCodeDataUtil.createPulldownList(codeTableInterfaceClass, valueItemArray, dispItemArray);
        } catch (S21AbendException e) {
            String noRecoreException = "There is no retrieval result. Table is [" + codeTableInterfaceClass.getSimpleName() + "].";
            String errDetail = e.getErrorStr();
            if (errDetail.contains(noRecoreException)) {
                // Ignore getNotFoundException
            } else {
                throw e;
            }
        }
    }

// 2020/02/28 QC#55971 Del Start
    /**
     * Create Pulldown Parent Vendor Type
     * @param bizMsg NMAL6860CMsg
     */
//    public static void createPullDownPrntVndTp(NMAL6860CMsg bizMsg) {
//
//        // Clear Pulldown Data
//        bizMsg.prntVndTpCd_L.clear();
//        bizMsg.prntVndTpDescTxt_L.clear();
//        bizMsg.prntVndTpCd_L1.clear();
//        bizMsg.vndTpCd_L1.clear();
//
//        // Execute
//        S21SsmEZDResult result = NMAL6860Query.getInstance().getPrntVndTpList(bizMsg.glblCmpyCd.getValue());
//
//        if (result.isCodeNormal()) {
//            List<Map> prntVndTpList = (List<Map>) result.getResultObject();
//
//            for (int i = 0; i < prntVndTpList.size(); i++) {
//                Map recode = prntVndTpList.get(i);
//
//                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd_L.no(i), (String) recode.get("PRNT_VND_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpDescTxt_L.no(i), (String) recode.get("PRNT_VND_TP_DESC_TXT"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndTpCd_L1.no(i), (String) recode.get("PRNT_VND_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(bizMsg.vndTpCd_L1.no(i), (String) recode.get("VND_TP_CD"));
//
//                if (i >= bizMsg.prntVndTpCd_L.length() - 1) {
//                    break;
//                }
//            }
//        }
//    }
// 2020/02/28 QC#55971 Del End

    /**
     * Create Pulldown Contact Type
     * @param bizMsg NMAL6860CMsg
     */
    public static void createPullDownCtacTp(NMAL6860CMsg bizMsg) {

        // Clear Pulldown Data
        bizMsg.ctacTpCd_L.clear();
        bizMsg.ctacTpDescTxt_L.clear();

        // Execute
        S21SsmEZDResult result = NMAL6860Query.getInstance().getCtacTpList(bizMsg.glblCmpyCd.getValue());

        if (result.isCodeNormal()) {
            List<Map> ctacTpList = (List<Map>) result.getResultObject();

            for (int i = 0; i < ctacTpList.size(); i++) {
                Map recode = ctacTpList.get(i);

                ZYPEZDItemValueSetter.setValue(bizMsg.ctacTpCd_L.no(i), (String) recode.get("CTAC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ctacTpDescTxt_L.no(i), (String) recode.get("CTAC_TP_DESC_TXT"));

                if (i >= bizMsg.ctacTpCd_L.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * Check for General Tab
     * @param bizMsg
     * @param glblMsg
     * @return boolean
     */
    public static boolean checkGeneralTab(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg, String glblCmpyCd) {

        boolean chkFlg = true;

        // Account Customer Check
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1)) {
            S21SsmEZDResult chkAccntCust = NMAL6860Query.getInstance().checkAccountCustomer(bizMsg);

            if (!chkAccntCust.isCodeNormal() || chkAccntCust.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NMAM0013E, new String[] {"Account Customer" });
                bizMsg.dsAcctNum_H1.setErrorInfo(1, NMAM0013E, new String[] {"Account Customer" });
                chkFlg = false;
            }
        }

        // QC#13163 Start
        // Payment Term Check
        if (!ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt_H1)) {

            bizMsg.vndPmtTermCd_H1.clear();
        } else {

            String vndPmtTermCd = (String) NMAL6860Query.getInstance().getVndPmtTermCd(bizMsg.glblCmpyCd.getValue(), bizMsg.vndPmtTermDescTxt_H1.getValue()).getResultObject();

            if (!ZYPCommonFunc.hasValue(vndPmtTermCd)) {
                bizMsg.setMessageInfo(NMAM0013E, new String[] {"Payment Term" });
                bizMsg.vndPmtTermDescTxt_H1.setErrorInfo(1, NMAM0013E, new String[] {"Payment Term" });
                chkFlg = false;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermCd_H1, vndPmtTermCd);
            }
        }
        // QC#13163 End

        // Address Check
        if(!checkAddress(bizMsg.glblCmpyCd.getValue(), bizMsg, glblMsg)) {
            bizMsg.setMessageInfo(NMAM8359E);
            chkFlg = false;
        }

        // LiabilityAccount Check
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!NMAL6860CommonLogic.validateSegmentStringForLiabilityAccount(glblCmpyCd, bizMsg, true, i)) {
                chkFlg = false;
            }
        }

        return chkFlg;
    }

    /**
     * Check for Detail Tab
     * @param bizMsg
     * @param glblMsg
     * @return boolean
     */
    public static boolean checkDetailTab(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg, String glblCmpyCd) {

        boolean chkFlg = true;

        // Check Customer Code
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_H2)) {
            S21SsmEZDResult chkBillToCust = NMAL6860Query.getInstance().checkBillToCustomer(bizMsg);

            if (!chkBillToCust.isCodeNormal() || chkBillToCust.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NMAM0013E, new String[] {"Customer Location" });
                bizMsg.billToCustCd_H2.setErrorInfo(1, NMAM0013E, new String[] {"Customer Location" });
                chkFlg = false;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1) && ZYPCommonFunc.hasValue(bizMsg.billToCustCd_H2)) {
            S21SsmEZDResult chkCustLocationAndNumber = NMAL6860Query.getInstance().checkCustomerLocationAndNumber(bizMsg);

            if (!chkCustLocationAndNumber.isCodeNormal() || chkCustLocationAndNumber.getQueryResultCount() == 0) {
                bizMsg.setMessageInfo(NMAM0163E, new String[] {"Customer Location", "the related Customer Number" });
                bizMsg.billToCustCd_H2.setErrorInfo(1, NMAM0163E, new String[] {"Customer Location", "the related Customer Number" });
                chkFlg = false;
            }
        }

        // QC#13163 Start
        // Payment Term Check
        if (!ZYPCommonFunc.hasValue(bizMsg.vndPmtTermDescTxt_H2)) {

            bizMsg.vndPmtTermCd_H2.clear();
        } else {

            String vndPmtTermCd = (String) NMAL6860Query.getInstance().getVndPmtTermCd(bizMsg.glblCmpyCd.getValue(), bizMsg.vndPmtTermDescTxt_H2.getValue()).getResultObject();

            if (!ZYPCommonFunc.hasValue(vndPmtTermCd)) {
                bizMsg.setMessageInfo(NMAM0013E, new String[] {"Payment Term" });
                bizMsg.vndPmtTermDescTxt_H2.setErrorInfo(1, NMAM0013E, new String[] {"Payment Term" });
                chkFlg = false;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.vndPmtTermCd_H2, vndPmtTermCd);
            }
        }
        // QC#13163 End

        // PrePay Account Check
        if (!NMAL6860CommonLogic.validateSegmentStringForPrePayAccount(glblCmpyCd, bizMsg, true, 0)) {
            chkFlg = false;
        }
        // Vendor Return Account Check
        if (!NMAL6860CommonLogic.validateSegmentStringForVendorReturnAccount(glblCmpyCd, bizMsg, true, 0)) {
            chkFlg = false;
        }

        return chkFlg;
    }

    /**
     * Copy Detail Items to General
     * @param bizMsg
     * @param glblMsg
     */
    public static void copyDetailToGeneral(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        // copies from Header of Detail Tab to Detail of General Tab.
        int index = bizMsg.xxRadioBtn_A.getValueInt();

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).vndPk_A, bizMsg.vndPk_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).vndCd_A, bizMsg.vndCd_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).invMatchTpCd_A, bizMsg.invMatchTpCd_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).invTolRate_A, bizMsg.invTolRate_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).rcvTolRate_A, bizMsg.rcvTolRate_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).vndPmtTermDescTxt_A, bizMsg.vndPmtTermDescTxt_H2);
        // QC#13163 Start
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).vndPmtTermCd_A, bizMsg.vndPmtTermCd_H2);
        // QC#13163 End
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).vndPmtMethCd_A, bizMsg.vndPmtMethCd_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).payGrpCd_A, bizMsg.payGrpCd_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).splyEdiLocCd_A, bizMsg.splyEdiLocCd_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).splyEdiNum_A, bizMsg.splyEdiNum_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).inacDt_A, bizMsg.inacDt_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).trsmtMethTpCd_A, bizMsg.trsmtMethTpCd_H2);
        // PrePay Account
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xxComnScrFirstValTxt_AP, bizMsg.xxComnScrFirstValTxt_H2);
        // Vendor Return Account
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xxComnScrFirstValTxt_AV, bizMsg.xxComnScrScdValTxt_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).xtrnlRefNum_A, bizMsg.xtrnlRefNum_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).endCustNum_A, bizMsg.endCustNum_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).arcsSplySiteCd_A, bizMsg.arcsSplySiteCd_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).arcsSplySiteId_A, bizMsg.arcsSplySiteId_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).billToCustCd_A, bizMsg.billToCustCd_H2);
        // QC#16594 Mod.
//        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).locNum_A, bizMsg.locNum_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).locNum_A1, bizMsg.locNum_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).locNum_A2, bizMsg.locNum_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).invVndCd_A, bizMsg.invVndCd_H2);

        // Contact Info copies CMsg to SMsg.
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            int glblMsgIndex = bizMsg.B.no(i).xxCellIdx_BS.getValueInt();
            glblMsg.B.no(glblMsgIndex).clear();
            EZDMsg.copy(bizMsg.B.no(i), null, glblMsg.B.no(glblMsgIndex), null);
        }
    }

    /**
     * Call Customer Information API
     * @param bizMsg
     */
    public static NMZC610001PMsg callCustomerInfomationGetApi(NMAL6860CMsg bizMsg) {

        final NMZC610001 custInfoGetApi = new NMZC610001();

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, bizMsg.dsAcctNum_P);
        // START 2020/08/31 R.Kurahashi [QC#57546,MOD]
        //custInfoGetApi.execute(pMsg, ONBATCH_TYPE.ONLINE);
        getCustInfoForBillShip(pMsg);
        // END 2020/08/31 R.Kurahashi [QC#57546,MOD]
        
        return pMsg;
    }

    /**
     * Call Global Address Service API
     * @param bizMsg
     * @param index
     */
    public static NMZC003001PMsg callGlobalAddressServiceApi(NMAL6860CMsg bizMsg, int index) {

        final NMZC003001 globalAddressServiceApi = new NMZC003001();

        NMZC003001PMsg pMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, bizMsg.A.no(index).xxComnScrFirstValTxt_A);
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, bizMsg.A.no(index).xxComnScrScdValTxt_A);
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, bizMsg.A.no(index).ctyAddr_A);
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, bizMsg.A.no(index).stCd_A);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, bizMsg.A.no(index).postCd_A);
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, bizMsg.A.no(index).ctryCd_A);
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, bizMsg.A.no(index).cntyNm_A);

        globalAddressServiceApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * Execute Update Supplier
     * @param bizMsg
     * @param glblMsg
     */
    public static boolean executeUpdateSupplier(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        final NMZC201001 updateSupplierApi = new NMZC201001();

        NMZC201001PMsg pMsg01 = new NMZC201001PMsg();

        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        String slsDt = ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());
        // END 2021/03/01 G.Delgado [QC#56057,ADD]

        // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
        List resultList = null;

        if (ZYPCommonFunc.hasValue(bizMsg.prntVndTpCd_DF) && bizMsg.prntVndTpCd_DF.getValue().equals(bizMsg.prntVndTpCd.getValue())
                || PRNT_VND_TP.LEASE_BUYOUT.equals(bizMsg.prntVndTpCd.getValue())) {
            resultList = (List) NMAL6860Query.getInstance().getPrntVndNm(bizMsg).getResultObject();
        } else {
            resultList = (List) NMAL6860Query.getInstance().getPrntVndNmForPrntVndTpCd(bizMsg).getResultObject();
        }

        if (resultList.size() > 0) {
            bizMsg.setMessageInfo("NMAM0010E", new String[] {"Supplier Name" });
            bizMsg.prntVndNm.setErrorInfo(1, "NMAM0010E", new String[] {"Supplier Name" });
            return false;
        }
        // END 2020/10/28 R.Kurahashi [QC#57732,ADD]

        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        String prevPrntVndTpCd = null;
        // END 2021/03/01 G.Delgado [QC#56057,ADD]

        if (!ZYPCommonFunc.hasValue(bizMsg.prntVndPk_P)) {
            ZYPEZDItemValueSetter.setValue(pMsg01.xxModeCd, CREATE_MODE);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg01.xxModeCd, UPDATE_MODE);
            ZYPEZDItemValueSetter.setValue(pMsg01.prntVndCd, bizMsg.prntVndCd);

            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            // Check if changing supplier type and with open PO/AP
            PRNT_VNDTMsg prntVnd = new PRNT_VNDTMsg();
            ZYPEZDItemValueSetter.setValue(prntVnd.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prntVnd.prntVndPk, bizMsg.prntVndPk_P);
            prntVnd = (PRNT_VNDTMsg) EZDTBLAccessor.findByKey(prntVnd);

            if (prntVnd != null) {
                if (!prntVnd.prntVndTpCd.getValue().equals(bizMsg.prntVndTpCd.getValue()) && !NMAL6860CommonLogic.checkSupplierOpenPoAp(bizMsg)) {
                    return false;
                }
                prevPrntVndTpCd = prntVnd.prntVndTpCd.getValue();
            }
            // END 2021/03/01 G.Delgado [QC#56057,ADD]
        }
        ZYPEZDItemValueSetter.setValue(pMsg01.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg01.procDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndNm, bizMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg01.prntVndTpCd, bizMsg.prntVndTpCd);

        // QC#26498 Start
        // Execute
        S21SsmEZDResult result = NMAL6860Query.getInstance().getPrntVndPkforNm(bizMsg);
        if (result.isCodeNormal()) {
            // Duplicate Supplier
            String splyNmSfx = "";
            if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_P)) {
                splyNmSfx = " - " + bizMsg.dsAcctNum_P.getValue();
            }

            // get digit 240 bytes
            String prntVndNm = pMsg01.prntVndNm.getValue();
            int digit = bizMsg.getAttr("prntVndNm").getDigit();
            String tmpPrntVndNm = prntVndNm + splyNmSfx;
            if (digit >= tmpPrntVndNm.length()) {
                ZYPEZDItemValueSetter.setValue(pMsg01.prntVndNm, tmpPrntVndNm);
            } else if (digit < tmpPrntVndNm.length()) {
                ZYPEZDItemValueSetter.setValue(pMsg01.prntVndNm, tmpPrntVndNm.substring(0, digit));
            }
        }
        // QC#26498 End
        ZYPEZDItemValueSetter.setValue(pMsg01.taxPayerId, bizMsg.taxPayerId_H1);
        if (ZYPCommonFunc.hasValue(bizMsg.inacDt_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg01.inacDt, bizMsg.inacDt_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg01.inacDt, DEF_THRU_DT);
        }
        ZYPEZDItemValueSetter.setValue(pMsg01.taxPayerRgNum, bizMsg.taxPayerRgNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.arcsSplyNum, bizMsg.arcsSplyNum_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.arcsSplyId, bizMsg.arcsSplyId_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.indyTpCd, bizMsg.indyTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.mnrtyOwndTpCd, bizMsg.mnrtyOwndTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.splyOrgTpCd, bizMsg.splyOrgTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.splyOneTmFlg, bizMsg.splyOneTmFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.smBizFlg, bizMsg.smBizFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.womenOwndFlg, bizMsg.womenOwndFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.payAloneFlg, bizMsg.payAloneFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.discTakeFlg, bizMsg.discTakeFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.poPmtHldFlg, bizMsg.poPmtHldFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.fdRptFlg, bizMsg.fdRptFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.incTaxTpCd, bizMsg.incTaxTpCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.stTaxFlg, bizMsg.stTaxFlg_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.splyRptNm, bizMsg.splyRptNm_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.coaAfflCd, bizMsg.coaAfflCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.splyHubZnCd, bizMsg.splyHubZnCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.dsAcctNum, bizMsg.dsAcctNum_H1);

        ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtTermDescTxt, bizMsg.vndPmtTermDescTxt_H1);
        // QC#13163 Start
        ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtTermCd, bizMsg.vndPmtTermCd_H1);
        // QC#13163 End
        ZYPEZDItemValueSetter.setValue(pMsg01.vndPmtMethCd, bizMsg.vndPmtMethCd_H1);
        ZYPEZDItemValueSetter.setValue(pMsg01.payGrpCd, bizMsg.payGrpCd_H1);

        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        // Check if supplier was active
        boolean activeSupplier = false;
        if (ZYPCommonFunc.hasValue(bizMsg.inacDt_G1) && ZYPDateUtil.compare(slsDt, bizMsg.inacDt_G1.getValue()) < 0) {
            activeSupplier = true;
        }

        // Get Send ARCS Flag
        String sendArcsFlg = NMAL6860CommonLogic.getSendArcsFlgForPrntVndTp(bizMsg.glblCmpyCd.getValue(), bizMsg.prntVndTpCd.getValue());

        String acctInvStsCdConst = ZYPCodeDataUtil.getVarCharConstValue(NMAL6860_ACCT_INV_STS_CD_OPEN, bizMsg.glblCmpyCd.getValue());
        // END 2021/03/01 G.Delgado [QC#56057,ADD]

        List<NMZC201002PMsg> pMsg02List = new ArrayList<NMZC201002PMsg>();
        for (int index = 0; index < bizMsg.A.getValidCount(); index++) {

            NMZC201002PMsg pMsg02 = new NMZC201002PMsg();
            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            String prevVndInacDt = null;
            String prevSplyPoFlg = null;
            String prevSplyPmtFlg = null;
            // END 2021/03/01 G.Delgado [QC#56057,ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(index).vndCd_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.xxModeCd, CREATE_MODE);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.xxModeCd, UPDATE_MODE);
                ZYPEZDItemValueSetter.setValue(pMsg02.prntVndCd, bizMsg.prntVndCd);
                ZYPEZDItemValueSetter.setValue(pMsg02.vndCd, bizMsg.A.no(index).vndCd_A);

                // START 2021/03/01 G.Delgado [QC#56057,ADD]
                // Check previous supplier site info
                VNDTMsg vnd = new VNDTMsg();
                ZYPEZDItemValueSetter.setValue(vnd.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(vnd.vndPk, bizMsg.A.no(index).vndPk_A);
                vnd = (VNDTMsg) EZDTBLAccessor.findByKey(vnd);

                if (vnd != null) {
                    prevVndInacDt = vnd.inacDt.getValue();
                    prevSplyPoFlg = vnd.splyPoFlg.getValue();
                    prevSplyPmtFlg = vnd.splyPmtFlg.getValue();
                }
                // END 2021/03/01 G.Delgado [QC#56057,ADD]
            }

            ZYPEZDItemValueSetter.setValue(pMsg02.taxPayerId, bizMsg.taxPayerId_H1);
            String firstValText = bizMsg.A.no(index).xxComnScrFirstValTxt_A.getValue();

            if (firstValText.length() > ADDRESS_LENGTH) {
                ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, firstValText.substring(0, ADDRESS_LENGTH));
                ZYPEZDItemValueSetter.setValue(pMsg02.scdLineAddr, firstValText.substring(ADDRESS_LENGTH));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.firstLineAddr, firstValText);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).xxComnScrScdValTxt_A)) {
                String scdValText = bizMsg.A.no(index).xxComnScrScdValTxt_A.getValue();

                if (scdValText.length() > ADDRESS_LENGTH) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.thirdLineAddr, scdValText.substring(0, ADDRESS_LENGTH));
                    ZYPEZDItemValueSetter.setValue(pMsg02.frthLineAddr, scdValText.substring(ADDRESS_LENGTH));
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg02.thirdLineAddr, scdValText);
                }
            }

            // START 2022/11/14 D.Mamaril [QC#60617,ADD]
            if (!validateCtryAndStCd(bizMsg, index)) {
                return false;
            }
            // END 2022/11/14 D.Mamaril [QC#60617,ADD]

            ZYPEZDItemValueSetter.setValue(pMsg02.ctyAddr, bizMsg.A.no(index).ctyAddr_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.cntyNm, bizMsg.A.no(index).cntyNm_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.stCd, bizMsg.A.no(index).stCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.postCd, bizMsg.A.no(index).postCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.ctryCd, bizMsg.A.no(index).ctryCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.locNm, bizMsg.A.no(index).locNm_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            ZYPEZDItemValueSetter.setValue(pMsg02.locRoleTpCd, LOC_ROLE_TP.VENDOR);
            ZYPEZDItemValueSetter.setValue(pMsg02.locGrpCd, LOC_GRP.VENDOR);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).vndCd_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.effFromDt, bizMsg.A.no(index).effFromDt_A);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.effFromDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
            }
            // START 2021/03/01 G.Delgado [QC#56057,MOD]
            // ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, bizMsg.A.no(index).inacDt_A.getValue());

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).inacDt_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, bizMsg.A.no(index).inacDt_A.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, DEF_THRU_DT);
            }

            // Supplier site was inactive
            if (ZYPCommonFunc.hasValue(prevVndInacDt) && ZYPDateUtil.compare(slsDt, prevVndInacDt) >= 0) {
                // Site inactive date > supplier inactive date
                if (ZYPDateUtil.compare(pMsg02.inacDt.getValue(), pMsg01.inacDt.getValue()) > 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg01.inacDt, pMsg02.inacDt);
                }
                // Supplier type Send ARCS Flag = Y
                if (ZYPCommonFunc.hasValue(sendArcsFlg) && ZYPConstant.FLG_ON_Y.equals(sendArcsFlg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(index).sendArcsFlg_A, ZYPConstant.FLG_OFF_N);
                }
            // Supplier site and supplier was active
            } else if (activeSupplier && ZYPDateUtil.compare(pMsg02.inacDt.getValue(), pMsg01.inacDt.getValue()) > 0) {
                ZYPEZDItemValueSetter.setValue(pMsg02.inacDt, pMsg01.inacDt);
            }
            // END 2021/03/01 G.Delgado [QC#56057,MOD]

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).inacDt_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, ZYPDateUtil.addDays(bizMsg.A.no(index).inacDt_A.getValue(), -1));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.effThruDt, DEF_THRU_DT);
            }
            ZYPEZDItemValueSetter.setValue(pMsg02.coaAfflCd, bizMsg.coaAfflCd_H1);
            //QC#12215 Start
            if (!CTRY.UNITED_STATES_OF_AMERICA.equals(bizMsg.A.no(index).ctryCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg02.intlVndFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.intlVndFlg, ZYPConstant.FLG_OFF_N);
            }
            //QC#12215 End
            ZYPEZDItemValueSetter.setValue(pMsg02.asnReqFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg02.thirdPtyVndFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg02.trsmtMethTpCd, bizMsg.A.no(index).trsmtMethTpCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.invVndCd, bizMsg.A.no(index).invVndCd_A);

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).splyPmtFlg_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyPmtFlg, bizMsg.A.no(index).splyPmtFlg_A);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyPmtFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).splyPoFlg_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyPoFlg, bizMsg.A.no(index).splyPoFlg_A);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.splyPoFlg, ZYPConstant.FLG_OFF_N);
            }

            // START 2021/03/01 G.Delgado [QC#56057,ADD]
            // Check if site has open PO and purchase flag changed from checked to unchecked
            if (ZYPCommonFunc.hasValue(prevSplyPoFlg) && ZYPConstant.FLG_ON_Y.equals(prevSplyPoFlg) && ZYPConstant.FLG_OFF_N.equals(pMsg02.splyPoFlg.getValue())
                    && NMAL6860Query.getInstance().checkSupplierOpenPo(bizMsg, pMsg02.vndCd.getValue()).getQueryResultCount() > 0) {
                bizMsg.setMessageInfo(NMAM0308E);
                return false;
            }
            // Check if site has open AP and pay flag changed from checked to unchecked
            if (ZYPCommonFunc.hasValue(acctInvStsCdConst)) {
                if (ZYPCommonFunc.hasValue(prevSplyPmtFlg) && ZYPConstant.FLG_ON_Y.equals(prevSplyPmtFlg) && ZYPConstant.FLG_OFF_N.equals(pMsg02.splyPmtFlg.getValue())
                        && NMAL6860Query.getInstance().checkSupplierOpenAp(bizMsg, acctInvStsCdConst, pMsg02.vndCd.getValue()).getQueryResultCount() > 0) {
                    bizMsg.setMessageInfo(NMAM0308E);
                    return false;
                }
            }
            // END 2021/03/01 G.Delgado [QC#56057,ADD]

            // Liability Account
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).xxComnScrFirstValTxt_AL)) {
                String accountStr = bizMsg.A.no(index).xxComnScrFirstValTxt_AL.getValue();
                String coaCds[] = accountStr.split("\\.");
                int count = coaCds.length;
// START 2016/08/04 [QC#12765,MOD]
                if (count > 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaCmpyCd, coaCds[0]);
                }
                if (count > 1) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaBrCd, coaCds[1]);
                }
                if (count > 2) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaCcCd, coaCds[2]);
                }
                if (count > 3) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaAcctCd, coaCds[3]);
                }
                if (count > 4) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProdCd, coaCds[4]);
                }
                if (count > 5) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaChCd, coaCds[5]);
                }
                if (count > 6) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaAfflCd, coaCds[6]);
                }
                if (count > 7) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaProjCd, coaCds[7]);
                }
                if (count > 8) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.splyCoaExtnCd, coaCds[8]);
                }
// END   2016/08/04 [QC#12765,MOD]
            }

            ZYPEZDItemValueSetter.setValue(pMsg02.invMatchTpCd, bizMsg.A.no(index).invMatchTpCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.invTolRate, bizMsg.A.no(index).invTolRate_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.rcvTolRate, bizMsg.A.no(index).rcvTolRate_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermDescTxt, bizMsg.A.no(index).vndPmtTermDescTxt_A);
            // QC#13163 Start
            ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtTermCd, bizMsg.A.no(index).vndPmtTermCd_A);
            // QC#13163 End
            ZYPEZDItemValueSetter.setValue(pMsg02.vndPmtMethCd, bizMsg.A.no(index).vndPmtMethCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.payGrpCd, bizMsg.A.no(index).payGrpCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.splyEdiLocCd, bizMsg.A.no(index).splyEdiLocCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.splySiteDealCd, bizMsg.A.no(index).splySiteDealCd_A);

            // PrePay Account
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).xxComnScrFirstValTxt_AP)) {
                String accountStr = bizMsg.A.no(index).xxComnScrFirstValTxt_AP.getValue();
                String coaCds[] = accountStr.split("\\.");
                int count = coaCds.length;

// START 2016/08/04 [QC#12765,MOD]
                if (count > 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaCmpyCd, coaCds[0]);
                }
                if (count > 1) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaBrCd, coaCds[1]);
                }
                if (count > 2) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaCcCd, coaCds[2]);
                }
                if (count > 3) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaAcctCd, coaCds[3]);
                }
                if (count > 4) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaProdCd, coaCds[4]);
                }
                if (count > 5) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaChCd, coaCds[5]);
                }
                if (count > 6) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaAfflCd, coaCds[6]);
                }
                if (count > 7) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaProjCd, coaCds[7]);
                }
                if (count > 8) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.prePmtCoaExtnCd, coaCds[8]);
                }
// END   2016/08/04 [QC#12765,MOD]
            }

            // Vendor Return Account
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).xxComnScrFirstValTxt_AV)) {
                String accountStr = bizMsg.A.no(index).xxComnScrFirstValTxt_AV.getValue();
                String coaCds[] = accountStr.split("\\.");
                int count = coaCds.length;

// START 2016/08/04 [QC#12765,MOD]
                if (count > 0) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaCmpyCd, coaCds[0]);
                }
                if (count > 1) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaBrCd, coaCds[1]);
                }
                if (count > 2) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaCcCd, coaCds[2]);
                }
                if (count > 3) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaAcctCd, coaCds[3]);
                }
                if (count > 4) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaProdCd, coaCds[4]);
                }
                if (count > 5) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaChCd, coaCds[5]);
                }
                if (count > 6) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaAfflCd, coaCds[6]);
                }
                if (count > 7) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaProjCd, coaCds[7]);
                }
                if (count > 8) {
                    ZYPEZDItemValueSetter.setValue(pMsg02.vndRtrnCoaExtnCd, coaCds[8]);
                }
// END   2016/08/04 [QC#12765,MOD]
            }
            ZYPEZDItemValueSetter.setValue(pMsg02.xtrnlRefNum, bizMsg.A.no(index).xtrnlRefNum_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.endCustNum, bizMsg.A.no(index).endCustNum_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.arcsSplySiteCd, bizMsg.A.no(index).arcsSplySiteCd_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.arcsSplySiteId, bizMsg.A.no(index).arcsSplySiteId_A);
            ZYPEZDItemValueSetter.setValue(pMsg02.billToCustCd, bizMsg.A.no(index).billToCustCd_A);
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(index).sendArcsFlg_A)) {
                ZYPEZDItemValueSetter.setValue(pMsg02.sendArcsFlg, bizMsg.A.no(index).sendArcsFlg_A);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg02.sendArcsFlg, ZYPConstant.FLG_OFF_N);
            }

            pMsg02List.add(pMsg02);
        }

        List<NMZC201003PMsg> pMsg03List = new ArrayList<NMZC201003PMsg>();
        // Add Start 2019/12/06 QC#54971
        String effFromDt = ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()), -1);
        // Add End 2019/12/06 QC#54971

        for (int index = 0; index < glblMsg.B.getValidCount(); index++) {

            // Add Start 2019/12/23 QC#54971-1
            if (!S21StringUtil.isEquals(glblMsg.B.no(index).xxDsComitFlg_B.getValue(), ZYPConstant.FLG_ON_Y)) {
                continue;
            }
            // Add End 2019/12/23 QC#54971-1

            if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).vndCd_B)) {
                // Update Contact info
                NMZC201003PMsg pMsg03 = new NMZC201003PMsg();

                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).ctacPsnPk_B)) {
                    ZYPEZDItemValueSetter.setValue(pMsg03.xxModeCd, NMZC2010_PROC_MODE_UPD);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnPk, glblMsg.B.no(index).ctacPsnPk_B);
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg03.xxModeCd, NMZC2010_PROC_MODE_CRAT);
                }
                ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnPk, glblMsg.B.no(index).ctacPsnPk_B);

                ZYPEZDItemValueSetter.setValue(pMsg03.locNum, glblMsg.B.no(index).locNum_B);
                // Mod Start 2019/12/09 QC#54971
//                ZYPEZDItemValueSetter.setValue(pMsg03.effFromDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(pMsg03.effFromDt, effFromDt);
                // Mod End 2019/12/09 QC#54971
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).inacDt_B)) {
                    ZYPEZDItemValueSetter.setValue(pMsg03.effThruDt, ZYPDateUtil.addDays(glblMsg.B.no(index).inacDt_B.getValue(), -1));
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg03.effThruDt, DEF_THRU_DT);
                }
                ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnFirstNm, glblMsg.B.no(index).ctacPsnFirstNm_B);
                ZYPEZDItemValueSetter.setValue(pMsg03.ctacPsnLastNm, glblMsg.B.no(index).ctacPsnLastNm_B);
                ZYPEZDItemValueSetter.setValue(pMsg03.ctacTpCd, glblMsg.B.no(index).ctacTpCd_B);
                ZYPEZDItemValueSetter.setValue(pMsg03.dsCtacPsnDeptCd, glblMsg.B.no(index).dsCtacPsnDeptCd_B);
                ZYPEZDItemValueSetter.setValue(pMsg03.dsCtacPsnTtlCd, glblMsg.B.no(index).dsCtacPsnTtlCd_B);

                int i = 0;
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntValTxt_BT)) {
                    if (!ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntPk_BT)) {
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_CRAT);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_UPD);
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, glblMsg.B.no(index).dsCtacPntPk_BT);
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, glblMsg.B.no(index).dsCtacPntValTxt_BT);
                    i++;
                } else if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntPk_BT)) {
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_UPD);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, glblMsg.B.no(index).dsCtacPntPk_BT);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, "");
                    i++;
                }

                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntValTxt_BF)) {
                    if (!ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntPk_BF)) {
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_CRAT);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_UPD);
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, glblMsg.B.no(index).dsCtacPntPk_BF);
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, glblMsg.B.no(index).dsCtacPntValTxt_BF);
                    i++;
                } else if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntPk_BF)) {
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_UPD);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, glblMsg.B.no(index).dsCtacPntPk_BF);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, "");
                    i++;
                }

                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntValTxt_BE)) {
                    if (!ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntPk_BE)) {
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_CRAT);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_UPD);
                        ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, glblMsg.B.no(index).dsCtacPntPk_BE);
                    }
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, glblMsg.B.no(index).dsCtacPntValTxt_BE);
                    i++;
                } else if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntPk_BE)) {
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_UPD);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntPk, glblMsg.B.no(index).dsCtacPntPk_BE);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                    ZYPEZDItemValueSetter.setValue(pMsg03.ContactPointInfoList.no(i).dsCtacPntValTxt, "");
                    i++;
                }

                if (i > 0) {
                    pMsg03.ContactPointInfoList.setValidCount(i);
                }

                pMsg03List.add(pMsg03);
            }
        }

        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        // Workaround for API call when updating supplier type
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndPk_P)) {
            // Update supplier type
            String prntVndTpCd = bizMsg.prntVndTpCd.getValue();
            if (!prevPrntVndTpCd.equals(prntVndTpCd)) {
                NMAL6860CommonLogic.updateSupplierPrntVndTpCd(bizMsg, prntVndTpCd);
            }

            // Update VND_TP_RELN
            String prevVndTpCd = NMAL6860CommonLogic.getVndTpCd(bizMsg.glblCmpyCd.getValue(), prevPrntVndTpCd);
            String vndTpCd = NMAL6860CommonLogic.getVndTpCd(bizMsg.glblCmpyCd.getValue(), prntVndTpCd);
            NMAL6860CommonLogic.updateVndTpReln(bizMsg, prevVndTpCd, vndTpCd);
        }
        // END 2021/03/01 G.Delgado [QC#56057,ADD]

        updateSupplierApi.execute(pMsg01, pMsg02List, pMsg03List, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg01)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg01);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                if (msgId.endsWith("E")) {
                    EZDConnectionMgr.getInstance().rollback();
                    return false;
                }
            }
        }

        // Set PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(bizMsg.prntVndCd, pMsg01.prntVndCd);

        // Set PRNT_VND_PK
        if (!ZYPCommonFunc.hasValue(glblMsg.prntVndPk_P)) {
            BigDecimal prntVndPk = (BigDecimal) NMAL6860Query.getInstance().getPrntVndPk(pMsg01).getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.prntVndPk_P, prntVndPk);
        }

        List<NMZC002001PMsg> cotactIofoList = new ArrayList<NMZC002001PMsg>();

        for (int index = 0; index < glblMsg.B.getValidCount(); index++) {

            // Add Start 2019/12/23 QC#54971-1
            if (!S21StringUtil.isEquals(glblMsg.B.no(index).xxDsComitFlg_B.getValue(), ZYPConstant.FLG_ON_Y)) {
                continue;
            }
            // Add End 2019/12/23 QC#54971-1

            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(index).vndCd_B)) {

                NMZC002001PMsg cotactIofo = new NMZC002001PMsg();

                ZYPEZDItemValueSetter.setValue(cotactIofo.xxModeCd, NMZC2010_PROC_MODE_CRAT);
                ZYPEZDItemValueSetter.setValue(cotactIofo.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(cotactIofo.slsDt, ZYPDateUtil.getSalesDate());

                String locNum = (String) NMAL6860Query.getInstance().getCreatedLocNum(bizMsg, pMsg02List.get(glblMsg.B.no(index).xxCellIdx_BA.getValueInt())).getResultObject();

                ZYPEZDItemValueSetter.setValue(cotactIofo.locNum, locNum);

                // Mod Start 2019/12/06 QC#54971
//                ZYPEZDItemValueSetter.setValue(cotactIofo.effFromDt, ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(cotactIofo.effFromDt, effFromDt);
                // Mod End 2019/12/06 QC#54971

                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).inacDt_B)) {
                    ZYPEZDItemValueSetter.setValue(cotactIofo.effThruDt, ZYPDateUtil.addDays(glblMsg.B.no(index).inacDt_B.getValue(), -1));
                } else {
                    ZYPEZDItemValueSetter.setValue(cotactIofo.effThruDt, DEF_THRU_DT);
                }

                ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnFirstNm, glblMsg.B.no(index).ctacPsnFirstNm_B);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ctacPsnLastNm, glblMsg.B.no(index).ctacPsnLastNm_B);
                ZYPEZDItemValueSetter.setValue(cotactIofo.ctacTpCd, glblMsg.B.no(index).ctacTpCd_B);
                ZYPEZDItemValueSetter.setValue(cotactIofo.dsCtacPsnDeptCd, glblMsg.B.no(index).dsCtacPsnDeptCd_B);
                ZYPEZDItemValueSetter.setValue(cotactIofo.dsCtacPsnTtlCd, glblMsg.B.no(index).dsCtacPsnTtlCd_B);

                int i = 0;
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntValTxt_BT)) {
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_CRAT);
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntValTxt, glblMsg.B.no(index).dsCtacPntValTxt_BT);
                    i++;
                }

                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntValTxt_BF)) {
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_CRAT);
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntValTxt, glblMsg.B.no(index).dsCtacPntValTxt_BF);
                    i++;
                }

                if (ZYPCommonFunc.hasValue(glblMsg.B.no(index).dsCtacPntValTxt_BE)) {
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).xxModeCd, NMZC2010_PROC_MODE_CRAT);
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
                    ZYPEZDItemValueSetter.setValue(cotactIofo.ContactPointInfoList.no(i).dsCtacPntValTxt, glblMsg.B.no(index).dsCtacPntValTxt_BE);
                    i++;
                }

                if (i > 0) {
                    cotactIofo.ContactPointInfoList.setValidCount(i);
                }

                cotactIofoList.add(cotactIofo);
            }
        }

        if (cotactIofoList.size() > 0) {
            NMZC002001 cotactIofoApi = new NMZC002001();

            // execute
            cotactIofoApi.execute(cotactIofoList, ONBATCH_TYPE.ONLINE);

            // check result
            boolean apiChkFlg = false;
            for (NMZC002001PMsg pMsg : cotactIofoList) {
                if (S21ApiUtil.isXxMsgId(pMsg)) {
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (int i = 0; i < msgList.size(); i++) {
                        S21ApiMessage msg = msgList.get(i);
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        bizMsg.setMessageInfo(msgId, msgPrms);
                        if (msgId.endsWith("E")) {
                            // Error
                            apiChkFlg = true;
                        }
                    }
                }
            }

            if (apiChkFlg) {
                // api Error
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
        }

        return true;
    }

    /**
     * check Address values using API.
     * @param glblCmpyCd String
     * @param cMsg NMAL6860CMsg
     * @param sMsg NMAL6860SMsg
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    public static boolean checkAddress(String glblCmpyCd, NMAL6860CMsg cMsg, NMAL6860SMsg sMsg) {
        boolean rtrnCd = true;

        String addrChkFlg = ZYPCodeDataUtil.getVarCharConstValue(NMAL6860_ADDR_CHK_FLG, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(addrChkFlg) //
                && ZYPConstant.FLG_OFF_N.equals(addrChkFlg)) {
            // Unnecessary address check.
            return rtrnCd;
        }

        // Check Address
        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            if (!callAddrValidApi(cMsg, glblCmpyCd, index)) {
                rtrnCd = false;
            } else {
                // Set sMsg.
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxComnScrFirstValTxt_A, cMsg.A.no(index).xxComnScrFirstValTxt_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxComnScrScdValTxt_A, cMsg.A.no(index).xxComnScrScdValTxt_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ctyAddr_A, cMsg.A.no(index).ctyAddr_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).stCd_A, cMsg.A.no(index).stCd_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).postCd_A, cMsg.A.no(index).postCd_A);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).cntyNm_A, cMsg.A.no(index).cntyNm_A);
            }
        }
        return rtrnCd;
    }

    /**
     * callAddrValidApi
     * @param msg NMAL6860CMsg
     * @param glblCmpyCd String
     * @param index int
     * @return boolean checkResult(NoError:true/HasError:false)
     */
    private static boolean callAddrValidApi(NMAL6860CMsg cMsg, String glblCmpyCd, int idxNum) {
        boolean rtrnCd = true;

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, cMsg.A.no(idxNum).xxComnScrFirstValTxt_A);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.scdLineAddr, cMsg.A.no(idxNum).xxComnScrScdValTxt_A);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, cMsg.A.no(idxNum).ctyAddr_A);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, cMsg.A.no(idxNum).stCd_A);
        //QC#12215 Start
        if (!CTRY.UNITED_STATES_OF_AMERICA.equals(cMsg.A.no(idxNum).ctryCd_A.getValue())
                && !ZYPCommonFunc.hasValue(cMsg.A.no(idxNum).postCd_A)) {

            // Default International Postal Code
            String defIntlPostCd = ZYPCodeDataUtil.getVarCharConstValue(DEF_INTL_POST_CD, glblCmpyCd);

            ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, defIntlPostCd);
        } else {
            ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, cMsg.A.no(idxNum).postCd_A);
        }
        //QC#12215 End
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, cMsg.A.no(idxNum).ctryCd_A);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.cntyNm, cMsg.A.no(idxNum).cntyNm_A);

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        List<String> msgIdList = S21ApiUtil.getXxMsgIdList(addrValidApiPMsg);

        // check Error
        // Address Line
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
            cMsg.A.no(idxNum).xxComnScrFirstValTxt_A.setErrorInfo(1, NMAM8454E, new String[] {"Address 1" });
            rtrnCd = false;
        }
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_02.getValue())) {
            cMsg.A.no(idxNum).xxComnScrScdValTxt_A.setErrorInfo(1, NMAM8454E, new String[] {"Address 2" });
            rtrnCd = false;
        }
        // City
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            cMsg.A.no(idxNum).ctyAddr_A.setErrorInfo(1, NMAM8454E, new String[] {"City" });
            rtrnCd = false;
        }
        // State
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            cMsg.A.no(idxNum).stCd_A.setErrorInfo(1, NMAM8454E, new String[] {"State" });
            rtrnCd = false;
        }
        // Postal code
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            cMsg.A.no(idxNum).postCd_A.setErrorInfo(1, NMAM8454E, new String[] {"PostalCode" });
            rtrnCd = false;
        }
        // Ctry
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_06.getValue())) {
            cMsg.A.no(idxNum).ctryCd_A.setErrorInfo(1, NMAM8454E, new String[] {"Country" });
            rtrnCd = false;
        }
        // Cnty
        if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            cMsg.A.no(idxNum).cntyNm_A.setErrorInfo(1, NMAM8454E, new String[] {"County" });
            rtrnCd = false;
        }

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).xxComnScrFirstValTxt_A, addrValidApiPMsg.xxVldStsCd_01);
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).xxComnScrScdValTxt_A, addrValidApiPMsg.xxVldStsCd_02);
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).ctyAddr_A, addrValidApiPMsg.xxVldStsCd_03);
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).stCd_A, addrValidApiPMsg.xxVldStsCd_04);
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).postCd_A, addrValidApiPMsg.xxVldStsCd_05);
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).ctryCd_A, addrValidApiPMsg.xxVldStsCd_06);
                setAddrValidResult((String) msgIdList.get(i), cMsg.A.no(idxNum).cntyNm_A, addrValidApiPMsg.xxVldStsCd_07);
                rtrnCd = false;
            }
        }

        // Not Error => replace address data.
        if (rtrnCd) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).xxComnScrFirstValTxt_A, addrValidApiPMsg.firstLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).xxComnScrScdValTxt_A, addrValidApiPMsg.scdLineAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).ctyAddr_A, addrValidApiPMsg.ctyAddr);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).stCd_A, addrValidApiPMsg.stCd);
            //QC#12215 Start
            if (ZYPCommonFunc.hasValue(cMsg.A.no(idxNum).postCd_A)) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).postCd_A, addrValidApiPMsg.postCd);
            }
            //QC#12215 End
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).ctryCd_A, addrValidApiPMsg.ctryCd);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(idxNum).cntyNm_A, addrValidApiPMsg.cntyNm);
        }
        return rtrnCd;
    }

    /**
     * <pre>
     * setAddrValidResult
     * </pre>
     * @param msgId String
     * @param checkItem EZDCStringItem
     * @param rtnStsCd EZDPStringItem
     */
    public static void setAddrValidResult(String msgId, EZDCStringItem checkItem, EZDPStringItem rtnStsCd) {
        if (RTRN_CD_ERROR.equals(rtnStsCd.getValue())) {
            // Replace Error Message.
            checkItem.clearErrorInfo();
            checkItem.setErrorInfo(1, msgId);
        }
    }

    /**
     * Get Default Display COA Information
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    public static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String appFuncId, String glblCmpyCd) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * Validate Segment string for Liability Account.
     * @param glblCmpyCd
     * @param cMsg
     * @param sMsg
     * @param submitFlg
     * @param winFlg
     * @return boolean
     */
    public static boolean validateSegmentStringForLiabilityAccount(String glblCmpyCd, NMAL6860CMsg cMsg, boolean submitFlg, int index) {

        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (!submitFlg) {
            
            NMAL6860_ACMsg ACMsg = cMsg.A.no(index);

            String accountTxt = ACMsg.xxComnScrFirstValTxt_AL.getValue();

            if (!hasValue(accountTxt)) {
                return true;
            }
            String[] list = accountTxt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // validate check.
            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, tokenList, index, false, LIABILITY_ACCOUNT)) {
                return false;
            }

        } else {

            NMAL6860_ACMsg ACMsg = cMsg.A.no(index);

            String accountTxt = ACMsg.xxComnScrFirstValTxt_AL.getValue();

            if (!hasValue(accountTxt)) {
                return true;
            }
            String[] list = accountTxt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // validate check.
            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, tokenList, index, true, LIABILITY_ACCOUNT)) {
                return false;
            }

        }

        return true;
    }
    
    /**
     * Validate Segment string for PrePay Account.
     * @param glblCmpyCd
     * @param cMsg
     * @param submitFlg
     * @param winFlg
     * @return boolean
     */
    public static boolean validateSegmentStringForPrePayAccount(String glblCmpyCd, NMAL6860CMsg cMsg, boolean submitFlg, int index) {

        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (!submitFlg) {

            String accountTxt = cMsg.xxComnScrFirstValTxt_H2.getValue();

            if (!hasValue(accountTxt)) {
                return true;
            }
            String[] list = accountTxt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // validate check.
            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, tokenList, index, false, PREPAY_ACCOUNT)) {
                return false;
            }

        } else {

            String accountTxt = cMsg.xxComnScrFirstValTxt_H2.getValue();

            if (!hasValue(accountTxt)) {
                return true;
            }
            String[] list = accountTxt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // validate check.
            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, tokenList, index, true, PREPAY_ACCOUNT)) {
                return false;
            }

        }

        return true;
    }
    
    /**
     * Validate Segment string for Vendor Return Account.
     * @param glblCmpyCd
     * @param cMsg
     * @param submitFlg
     * @param winFlg
     * @return boolean
     */
    public static boolean validateSegmentStringForVendorReturnAccount(String glblCmpyCd, NMAL6860CMsg cMsg, boolean submitFlg, int index) {

        String delimiter = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_KEY_DELIMITER, glblCmpyCd);

        if (!submitFlg) {

            String accountTxt = cMsg.xxComnScrScdValTxt_H2.getValue();

            if (!hasValue(accountTxt)) {
                return true;
            }
            String[] list = accountTxt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // validate check.
            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, tokenList, index, false, VENDOR_RETURN_ACCOUNT)) {
                return false;
            }

        } else {

            String accountTxt = cMsg.xxComnScrScdValTxt_H2.getValue();

            if (!hasValue(accountTxt)) {
                return true;
            }
            String[] list = accountTxt.split(Pattern.quote(delimiter), -1);
            List<String> tokenList = new ArrayList<String>();
            for (String val : list) {
                tokenList.add(val);
            }

            // validate check.
            if (!validateSegmentStringTokenList(glblCmpyCd, cMsg, tokenList, index, true, VENDOR_RETURN_ACCOUNT)) {
                return false;
            }

        }

        return true;
    }

    /**
     * Validate Segment string token list.
     * 
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NMAL6860CMsg
     * @param tokenList tokenList
     * @return true/false
     */
    public static boolean validateSegmentStringTokenList(String glblCmpyCd, NMAL6860CMsg cMsg, List<String> tokenList, int index, boolean submitFlg, String checkAccount) {

        if (!submitFlg) {

            if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
                cMsg.setMessageInfo(NLCM0065E);
                if (LIABILITY_ACCOUNT.equals(checkAccount)) {
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
                } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
                } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
                }
                return false;
            }

            StringBuilder acctNum = new StringBuilder();
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ));
            acctNum.append(".");
            acctNum.append(splitSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN));

            if (LIABILITY_ACCOUNT.equals(checkAccount)) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxComnScrFirstValTxt_AL, acctNum.toString());
            } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrFirstValTxt_H2, acctNum.toString());
//                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxComnScrFirstValTxt_AP, acctNum.toString());
            } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrScdValTxt_H2, acctNum.toString());
//                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxComnScrFirstValTxt_AV, acctNum.toString());
            }

        } else {

            // Check segment mandatory.
            if (!checkSegmentMandatory(checkAccount, index, cMsg, tokenList)) {
                return false;
            }

            if (LIABILITY_ACCOUNT.equals(checkAccount)) {

                // Liability Account
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_EXTN});
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_BR });
                    return false;
                }

            } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                // PrePay Account
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_EXTN});
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_BR });
                    return false;
                }

            } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                // Vendor Return Account
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD), SEGMENT_ELEMENT_LENGTH_CMPY)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD), SEGMENT_ELEMENT_LENGTH_EXTN)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_EXTN});
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD), SEGMENT_ELEMENT_LENGTH_CC)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD), SEGMENT_ELEMENT_LENGTH_ACCT)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD), SEGMENT_ELEMENT_LENGTH_PROJ)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD), SEGMENT_ELEMENT_LENGTH_PROD)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD), SEGMENT_ELEMENT_LENGTH_AFFL)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD), SEGMENT_ELEMENT_LENGTH_CH)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                if (!validateSegmentElement(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD), SEGMENT_ELEMENT_LENGTH_BR)) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0211E, new String[] {MSG_PARAM_BR });
                    return false;
                }
            }

            // QC#15816 Add.
            if (!validateSegmentDefault(cMsg, tokenList, checkAccount, submitFlg, index)) {
                return false;
            }

            // GL Code Combination Check API NFZC102001
            NFZC102001 afzc102001 = new NFZC102001();
            NFZC102001PMsg paramMsg = new NFZC102001PMsg();

            paramMsg.glblCmpyCd.setValue(glblCmpyCd);
            paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
            paramMsg.xxArcsApiChkFlg.setValue("");
            paramMsg.coaCmpyCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD));
            paramMsg.coaAfflCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD));
            paramMsg.coaBrCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD));
            paramMsg.coaCcCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD));
            paramMsg.coaAcctCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD));
            paramMsg.coaProdCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD));
            paramMsg.coaChCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD));
            paramMsg.coaProjCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD));
            paramMsg.coaExtnCd.setValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD));
            // QC#15816 Mod.
//            paramMsg.resrcObjNm.setValue(BUSINESS_ID);
            if (LIABILITY_ACCOUNT.equals(checkAccount)) {
                paramMsg.resrcObjNm.setValue(BUSINESS_ID + ACC_TYPE_LIABILITY);
            } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                paramMsg.resrcObjNm.setValue(BUSINESS_ID + ACC_TYPE_PREPAY);
            } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                paramMsg.resrcObjNm.setValue(BUSINESS_ID + ACC_TYPE_VNDRETURN);
            }

            afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                // START 2019/03/20 H.Ikeda [QC#23696, ADD]
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);

                if (LIABILITY_ACCOUNT.equals(checkAccount)) {
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, msgPrms);
                } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, msgPrms);
                } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, msgPrms);
                }
                // END   2019/03/20 H.Ikeda [QC#23696, ADD]
                // START 2019/03/20 H.Ikeda [QC#23696, DEL]
//                String[] msgPrms = msg.getXxMsgPrmArray();
//
//                if (msgPrms != null && msgPrms.length > 0) {
//
//                    cMsg.setMessageInfo(msgId, msgPrms);
//                    if (msgPrms[0].equals(DB_COLUMN_COA_CMPY_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CMPY });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_BR_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_BR });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_CC_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CC });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_ACCT_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_ACCT });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_PROD_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROD });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_CH_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_CH });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_AFFL_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_AFFL });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_PROJ_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_PROJ });
//                        }
//                    }
//                    if (msgPrms[0].equals(DB_COLUMN_COA_EXTN_CD)) {
//                        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
//                        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
//                        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
//                            cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, msgId, new String[] {MSG_PARAM_EXTN });
//                        }
//                    } else {
//                        cMsg.setMessageInfo(msgId, new String[] {msgPrms[0] });
//                    }
//                } else {
//                    cMsg.setMessageInfo(msgId);
//                }
                  // END   2019/03/20 H.Ikeda [QC#23696, DEL]

                return false;
            }

        }

        return true;
    }

    private static boolean validateSegmentElement( String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }

    private static String splitSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return "";
        }
        if (element.length() > len) {
            element = element.substring(0, len);
        }
        return element;
    }
    
    /**
     * getAccountEnabled
     * @param bizMsg NMAL6860CMsg
     * @param glblCmpyCd String
     */
    public static void getAccountEnabled(NMAL6860CMsg bizMsg, String glblCmpyCd) {

        // get 9seg Default
        
        // Liability Account 
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            DEF_DPLY_COA_INFOTMsg liTMsg = new DEF_DPLY_COA_INFOTMsg();
            liTMsg.glblCmpyCd.setValue(glblCmpyCd);
            liTMsg.appFuncId.setValue(BUSINESS_ID + ACC_TYPE_LIABILITY);
            liTMsg = (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(liTMsg);
            if (liTMsg == null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
            } else {
                if (ZYPCommonFunc.hasValue(bizMsg.prntVndTpCd_DF) && bizMsg.prntVndTpCd_DF.getValue().equals(bizMsg.prntVndTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_OFF_N);

                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaCmpyDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaAfflDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaBrDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaCcDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaAcctDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaProdDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaChDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaProjDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                    if (!ZYPConstant.FLG_OFF_N.equals(liTMsg.coaExtnDplyFlg.getValue())) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).coaEnblFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                }
            }
        }

        // PrePay Account
        DEF_DPLY_COA_INFOTMsg prTMsg = new DEF_DPLY_COA_INFOTMsg();
        prTMsg.glblCmpyCd.setValue(glblCmpyCd);
        prTMsg.appFuncId.setValue(BUSINESS_ID + ACC_TYPE_PREPAY);
        prTMsg = (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(prTMsg);
        if (prTMsg == null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_OFF_N);

            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaCmpyDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaAfflDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaBrDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaCcDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaAcctDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaProdDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaChDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaProjDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(prTMsg.coaExtnDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_PR, ZYPConstant.FLG_ON_Y);
            }
        }

        // Vnd Return Account
        DEF_DPLY_COA_INFOTMsg vrTMsg = new DEF_DPLY_COA_INFOTMsg();
        vrTMsg.glblCmpyCd.setValue(glblCmpyCd);
        vrTMsg.appFuncId.setValue(BUSINESS_ID + ACC_TYPE_VNDRETURN);
        vrTMsg = (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(vrTMsg);
        if (vrTMsg == null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_OFF_N);

            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaCmpyDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaAfflDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaBrDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaCcDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaAcctDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaProdDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaChDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaProjDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
            if (!ZYPConstant.FLG_OFF_N.equals(vrTMsg.coaExtnDplyFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaEnblFlg_VR, ZYPConstant.FLG_ON_Y);
            }
        }

    }

    /**
     * validateSegmentDefault
     * @param cMsg NMAL6860CMsg
     * @param tokenList List<String>
     * @param checkAccount String
     * @param submitFlg  boolean
     * @param index int
     * @return boolean
     */
    private static boolean validateSegmentDefault(NMAL6860CMsg cMsg, List<String> tokenList, String checkAccount, boolean submitFlg, int index) {

        Map<String, String> defaultRecord = new HashMap<String, String>();
        if (LIABILITY_ACCOUNT.equals(checkAccount)) {
            defaultRecord = getDefaultSegment(cMsg, BUSINESS_ID + ACC_TYPE_LIABILITY, cMsg.glblCmpyCd.getValue());
        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
            defaultRecord = getDefaultSegment(cMsg, BUSINESS_ID + ACC_TYPE_PREPAY, cMsg.glblCmpyCd.getValue());
        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
            defaultRecord = getDefaultSegment(cMsg, BUSINESS_ID + ACC_TYPE_VNDRETURN, cMsg.glblCmpyCd.getValue());
        }

        if (!submitFlg) {

            return true;

        } else {

            if (LIABILITY_ACCOUNT.equals(checkAccount)) {
                // COA_CMPY_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD).equals(defaultRecord.get(DB_COLUMN_COA_CMPY_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                // COA_AFFL_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD).equals(defaultRecord.get(DB_COLUMN_COA_AFFL_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                // COA_BR_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD).equals(defaultRecord.get(DB_COLUMN_COA_BR_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_BR });
                    return false;
                }
                // COA_CC_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD).equals(defaultRecord.get(DB_COLUMN_COA_CC_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                // COA_ACCT_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD).equals(defaultRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                // COA_PROD_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD).equals(defaultRecord.get(DB_COLUMN_COA_PROD_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                // COA_CH_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CH_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD).equals(defaultRecord.get(DB_COLUMN_COA_CH_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                // COA_PROJ_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD).equals(defaultRecord.get(DB_COLUMN_COA_PROJ_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                // COA_EXTN_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD).equals(defaultRecord.get(DB_COLUMN_COA_EXTN_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_EXTN });
                    return false;
                }

            } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                // COA_CMPY_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD).equals(defaultRecord.get(DB_COLUMN_COA_CMPY_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                // COA_AFFL_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD).equals(defaultRecord.get(DB_COLUMN_COA_AFFL_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                // COA_BR_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD).equals(defaultRecord.get(DB_COLUMN_COA_BR_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_BR });
                    return false;
                }
                // COA_CC_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD).equals(defaultRecord.get(DB_COLUMN_COA_CC_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                // COA_ACCT_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD).equals(defaultRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                // COA_PROD_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD).equals(defaultRecord.get(DB_COLUMN_COA_PROD_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                // COA_CH_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CH_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD).equals(defaultRecord.get(DB_COLUMN_COA_CH_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                // COA_PROJ_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD).equals(defaultRecord.get(DB_COLUMN_COA_PROJ_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                // COA_EXTN_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD).equals(defaultRecord.get(DB_COLUMN_COA_EXTN_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_EXTN });
                    return false;
                }

            } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                // COA_CMPY_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CMPY_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD).equals(defaultRecord.get(DB_COLUMN_COA_CMPY_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CMPY });
                    return false;
                }
                // COA_AFFL_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_AFFL_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD).equals(defaultRecord.get(DB_COLUMN_COA_AFFL_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_AFFL });
                    return false;
                }
                // COA_BR_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_BR_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD).equals(defaultRecord.get(DB_COLUMN_COA_BR_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_BR });
                    return false;
                }
                // COA_CC_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CC_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD).equals(defaultRecord.get(DB_COLUMN_COA_CC_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CC });
                    return false;
                }
                // COA_ACCT_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_ACCT_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD).equals(defaultRecord.get(DB_COLUMN_COA_ACCT_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_ACCT });
                    return false;
                }
                // COA_PROD_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_PROD_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD).equals(defaultRecord.get(DB_COLUMN_COA_PROD_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_PROD });
                    return false;
                }
                // COA_CH_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_CH_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD).equals(defaultRecord.get(DB_COLUMN_COA_CH_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_CH });
                    return false;
                }
                // COA_PROJ_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_PROJ_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD).equals(defaultRecord.get(DB_COLUMN_COA_PROJ_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_PROJ });
                    return false;
                }
                // COA_EXTN_CD
                if (ZYPCommonFunc.hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD)) && ZYPConstant.FLG_OFF_N.equals(defaultRecord.get(DB_COLUMN_COA_EXTN_DPLY_FLG))
                        && !tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD).equals(defaultRecord.get(DB_COLUMN_COA_EXTN_CD))) {
                    cMsg.setMessageInfo(NLCM0065E);
                    cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NMAM8662E, new String[] {MSG_PARAM_EXTN });
                    return false;
                }

            }

        }

        return true;
    }

    /**
     * getDefaultSegment
     * @param cMsg NMAL6860CMsg
     * @param bizAppId String
     * @param glblCmpyCd String
     * @return Map<String, String>
     */
    private static Map<String, String> getDefaultSegment(NMAL6860CMsg cMsg, String bizAppId, String glblCmpyCd) {

        Map<String, String> defaultRecord = new HashMap<String, String>();
        // get 9seg Default
        DEF_DPLY_COA_INFOTMsg tMsg = getDefDplyCoaInfo(bizAppId, glblCmpyCd);
        if (tMsg != null) {

            defaultRecord.put(DB_COLUMN_COA_CMPY_DPLY_FLG, tMsg.coaCmpyDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_AFFL_DPLY_FLG, tMsg.coaAfflDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_BR_DPLY_FLG, tMsg.coaBrDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_CC_DPLY_FLG, tMsg.coaCcDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_ACCT_DPLY_FLG, tMsg.coaAcctDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROD_DPLY_FLG, tMsg.coaProdDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_CH_DPLY_FLG, tMsg.coaChDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROJ_DPLY_FLG, tMsg.coaProjDplyFlg.getValue());
            defaultRecord.put(DB_COLUMN_COA_EXTN_DPLY_FLG, tMsg.coaExtnDplyFlg.getValue());

            defaultRecord.put(DB_COLUMN_COA_CMPY_CD, tMsg.coaCmpyCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_AFFL_CD, tMsg.coaAfflCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_BR_CD, tMsg.coaBrCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_CC_CD, tMsg.coaCcCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_ACCT_CD, tMsg.coaAcctCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROD_CD, tMsg.coaProdCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_CH_CD, tMsg.coaChCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_PROJ_CD, tMsg.coaProjCd.getValue());
            defaultRecord.put(DB_COLUMN_COA_EXTN_CD, tMsg.coaExtnCd.getValue());

        }

        return defaultRecord;
    }

    /**
     * checkSegmentMandatory
     * @param checkAccount String
     * @param index int
     * @param cMsg NMAL6860CMsg
     * @param tokenList List<String>
     * @return boolean
     */
    private static boolean checkSegmentMandatory(String checkAccount, int index, NMAL6860CMsg cMsg, List<String> tokenList) {

        if (tokenList.size() != SEGMENT_TOKEN_LIST_SIZE) {
            cMsg.setMessageInfo(NLCM0065E);
            if (LIABILITY_ACCOUNT.equals(checkAccount)) {
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
            } else if (PREPAY_ACCOUNT.equals(checkAccount)) {
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
            } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, NLCM0210E, new String[] {MSG_PARAM_SEGMENT });
            }
            return false;
        }

        if (LIABILITY_ACCOUNT.equals(checkAccount)) {

            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CMPY });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_BR });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CC });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_ACCT });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_PROD });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CH });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_AFFL });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_PROJ });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.A.no(index).xxComnScrFirstValTxt_AL.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

        } else if (PREPAY_ACCOUNT.equals(checkAccount)) {

            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CMPY });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_BR });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CC });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_ACCT });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_PROD });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CH });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_AFFL });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_PROJ });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrFirstValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

        } else if (VENDOR_RETURN_ACCOUNT.equals(checkAccount)) {

            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CMPY_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CMPY });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_BR_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_BR });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CC_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CC });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_ACCT_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_ACCT });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROD_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_PROD });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_CH_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_CH });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_AFFL_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_AFFL });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_PROJ_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_PROJ });
                return false;
            }
            if (!hasValue(tokenList.get(SEGMENT_TOKEN_LIST_INDEX_COA_EXTN_CD))) {
                cMsg.setMessageInfo(NLCM0065E);
                cMsg.xxComnScrScdValTxt_H2.setErrorInfo(1, ZZZM9025E, new String[] {MSG_PARAM_EXTN });
                return false;
            }

        }

        return true;
    }

    // Add Start 2019/12/23 QC#54971-1
    /**
     * getUpdateLine
     * @param bizMsg NMAL6860CMsg
     * @param glblMsg NMAL6860SMsg
     */
    public static void getUpdateLine(NMAL6860CMsg bizMsg, NMAL6860SMsg glblMsg) {

        int defValidCount = glblMsg.E.getValidCount();
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            NMAL6860_BCMsg bizBMsg = bizMsg.B.no(i);

            if (i < defValidCount) {
                NMAL6860_ESMsg glblEMsg = glblMsg.E.no(i);

                if (!S21StringUtil.isEquals(bizBMsg.ctacPsnLastNm_B.getValue(), glblEMsg.ctacPsnLastNm_B.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.ctacPsnFirstNm_B.getValue(), glblEMsg.ctacPsnFirstNm_B.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.dsCtacPsnTtlCd_B.getValue(), glblEMsg.dsCtacPsnTtlCd_B.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.dsCtacPntValTxt_BT.getValue(), glblEMsg.dsCtacPntValTxt_BT.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.dsCtacPntValTxt_BF.getValue(), glblEMsg.dsCtacPntValTxt_BF.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.dsCtacPsnDeptCd_B.getValue(), glblEMsg.dsCtacPsnDeptCd_B.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.ctacTpCd_B.getValue(), glblEMsg.ctacTpCd_B.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.dsCtacPntValTxt_BE.getValue(), glblEMsg.dsCtacPntValTxt_BE.getValue()) //
                        || !S21StringUtil.isEquals(bizBMsg.inacDt_B.getValue(), glblEMsg.inacDt_B.getValue())) {

                    ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxDsComitFlg_B, ZYPConstant.FLG_ON_Y);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxDsComitFlg_B, ZYPConstant.FLG_ON_Y);
            }
        }
    }

    /**
     * validatePastDate
     * @param bizMsg NMAL6860CMsg
     * @return boolean
     */
    public static boolean validatePastDate(NMAL6860CMsg bizMsg) {

        boolean isPastDate = false;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            NMAL6860_BCMsg bizBMsg = bizMsg.B.no(i);

            if (!S21StringUtil.isEquals(bizBMsg.xxDsComitFlg_B.getValue(), ZYPConstant.FLG_ON_Y)) {
                continue;
            }

            // START 2021/01/26 A.Marte [QC#58163, MOD]
            String salesDate =  ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(bizBMsg.inacDt_B) && (ZYPDateUtil.isPastDate(bizBMsg.inacDt_B.getValue()) || bizBMsg.inacDt_B.getValue().compareTo(salesDate) == 0)) {
                bizBMsg.inacDt_B.setErrorInfo(1, NMAM0803E);
                isPastDate = true;
            }
            // END 2021/01/26 A.Marte [QC#58163, MOD]
        }

        return isPastDate;
    }
    // Add End 2019/12/23 QC#54971-1
    // START 2020/08/31 R.Kurahashi [QC#57546,ADD]
    /**
     * Get customer information for Bill To and Ship To (Mode : 04)
     * @param param NMZC610001PMsg
     */
    private static void getCustInfoForBillShip(NMZC610001PMsg param) {

        String billToCustCd = "";
        String shipToCustCd = "";
        String acctNum = param.dsAcctNum_I1.getValue();
        

        // Get Primary Bill To Customer Code.
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            billToCustCd = (String) NMAL6860Query.getInstance().getCustInfoForPrimBill(param.glblCmpyCd.getValue(), acctNum, RGTN_STS.READY_FOR_ORDER_TAKING).getResultObject();
        }
        // Get Primary Ship To Customer Code.
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            shipToCustCd = (String) NMAL6860Query.getInstance().getCustInfoForPrimShip(param.glblCmpyCd.getValue(), acctNum, RGTN_STS.READY_FOR_ORDER_TAKING).getResultObject();
        }

        // Get Bill To and Ship To From Primary Location
        if (!ZYPCommonFunc.hasValue(billToCustCd) || !ZYPCommonFunc.hasValue(shipToCustCd)) {
            if (ZYPCommonFunc.hasValue(acctNum)) {
                Map<String, Object> rsltMap = getBillToShipToFromPrimLoc(param);
                if (rsltMap != null) { 
                    if (!ZYPCommonFunc.hasValue(billToCustCd) && ZYPCommonFunc.hasValue((String) rsltMap.get("BILL_TO_CUST_CD"))) {
                        billToCustCd = (String) rsltMap.get("BILL_TO_CUST_CD");
                    }
                    if (!ZYPCommonFunc.hasValue(shipToCustCd) && ZYPCommonFunc.hasValue((String) rsltMap.get("SHIP_TO_CUST_CD"))) {
                        shipToCustCd = (String) rsltMap.get("SHIP_TO_CUST_CD");
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(billToCustCd) || ZYPCommonFunc.hasValue(shipToCustCd)) {
            int idx = 0;
            ZYPEZDItemValueSetter.setValue(param.dsAcctNum_O, acctNum);
            NMZC610001_DefaultBillShipListPMsg outPut = param.DefaultBillShipList.no(idx);
            ZYPEZDItemValueSetter.setValue(outPut.billToCustCd, billToCustCd);
            ZYPEZDItemValueSetter.setValue(outPut.shipToCustCd, shipToCustCd);
            idx++;
            param.DefaultBillShipList.setValidCount(idx);
        }

    }
    
    /**
     * @param pMsg NMZC610001PMsg
     * @return Map<String, String> KEY: BILL_TO_CUST_CD, SHIP_TO_CUST_CD
     */
    private static Map<String, Object> getBillToShipToFromPrimLoc(NMZC610001PMsg pMsg) {

        List<Map<String, Object>> rsBillShipList = (List<Map<String, Object>>) NMAL6860Query.getInstance().getBillToShipToFromPrimLoc(
                pMsg.glblCmpyCd.getValue(), pMsg.dsAcctNum_I1.getValue(), RGTN_STS.READY_FOR_ORDER_TAKING, 2).getResultObject();
            
        if (rsBillShipList != null
                && rsBillShipList.size() == 1) {
            return rsBillShipList.get(0);
        } else if (rsBillShipList != null
                && rsBillShipList.size() == 2){
            for (Map<String, Object> rsBillShip : rsBillShipList) {
                if (ZYPCommonFunc.hasValue((BigDecimal) rsBillShip.get("PRIN_CUST_PK"))) {
                    return rsBillShip;
                }
            }
        }

        return null;
    }
    // END 2020/08/31 R.Kurahashi [QC#57546,ADD]

    // START 2021/03/01 G.Delgado [QC#56057,ADD]
    /**
     * Check if supplier has open PO or AP
     * @param bizMsg NMAL6860CMsg
     * @return boolean
     */
    public static boolean checkSupplierOpenPoAp(NMAL6860CMsg bizMsg) {
        // Check for open PO
        if (NMAL6860Query.getInstance().checkSupplierOpenPo(bizMsg, null).getQueryResultCount() > 0) {
            bizMsg.setMessageInfo(NMAM0308E);
            return false;
        }
        // Check for open AP
        String acctInvStsCdConst = ZYPCodeDataUtil.getVarCharConstValue(NMAL6860_ACCT_INV_STS_CD_OPEN, bizMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(acctInvStsCdConst) && NMAL6860Query.getInstance().checkSupplierOpenAp(bizMsg, acctInvStsCdConst, null).getQueryResultCount() > 0) {
            bizMsg.setMessageInfo(NMAM0308E);
            return false;
        }
        return true;
    }

    /**
     * Get VndTpCd
     * @param glblCmpyCd String
     * @param prntVndTpCd String
     * @return vndTpCd or null
     */
    public static String getVndTpCd(String glblCmpyCd, String prntVndTpCd) {
        PRNT_VND_TPTMsg prntVndTp = new PRNT_VND_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prntVndTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVndTp.prntVndTpCd, prntVndTpCd);

        prntVndTp = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(prntVndTp);

        if (prntVndTp != null) {
            return prntVndTp.vndTpCd.getValue();
        } else {
            return null;
        }
    }

    /**
     * Update supplier prntVndTpCd
     * @param bizMsg NMAL6860CMsg
     * @param newPrntVndTpCd String
     */
    public static void updateSupplierPrntVndTpCd(NMAL6860CMsg bizMsg, String newPrntVndTpCd) {
        PRNT_VNDTMsg prntVnd = new PRNT_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(prntVnd.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVnd.prntVndPk, bizMsg.prntVndPk_P);
        prntVnd = (PRNT_VNDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prntVnd);

        if (prntVnd != null) {
            ZYPEZDItemValueSetter.setValue(prntVnd.prntVndTpCd, bizMsg.prntVndTpCd);
            EZDTBLAccessor.update(prntVnd);
        }
    }

    /**
     * Update VND_TP_RELN
     * @param bizMsg NMAL6860CMsg
     * @param prevVndTpCd String
     * @param newVndTpCd String
     * @return boolean
     */
    public static boolean updateVndTpReln(NMAL6860CMsg bizMsg, String prevVndTpCd, String newVndTpCd) {
        if (prevVndTpCd.equals(newVndTpCd)) {
            return true;
        }

        NMAL6860_ACMsg siteInfo = null;

        for (int index = 0; index < bizMsg.A.getValidCount(); index++) {
            siteInfo = bizMsg.A.no(index);
            if (ZYPCommonFunc.hasValue(siteInfo.vndCd_A)) {
                // Remove previous VND_TP_RELN
                VND_TP_RELNTMsg vndTpReln = new VND_TP_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(vndTpReln.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(vndTpReln.vndCd, siteInfo.vndCd_A);
                ZYPEZDItemValueSetter.setValue(vndTpReln.vndTpCd, prevVndTpCd);
                vndTpReln = (VND_TP_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(vndTpReln);

                if (vndTpReln != null) {
                    EZDTBLAccessor.remove(vndTpReln);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                        bizMsg.setMessageInfo(NMZM0333E, null);
                        return false;
                    }
                }

                vndTpReln = new VND_TP_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(vndTpReln.glblCmpyCd, bizMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(vndTpReln.vndCd, siteInfo.vndCd_A);
                ZYPEZDItemValueSetter.setValue(vndTpReln.vndTpCd, newVndTpCd);
                VND_TP_RELNTMsg rsltTMsg = (VND_TP_RELNTMsg) EZDTBLAccessor.findByKey(vndTpReln);

                // Create new VND_TP_RELN
                if (rsltTMsg == null) {
                    EZDTBLAccessor.create(vndTpReln);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndTpReln.getReturnCode())) {
                        bizMsg.setMessageInfo(NMZM0127E, null);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Get sendArcsFlg for prntVndTpCd
     * @param glblCmpyCd String
     * @param prntVndTpCd String
     * @return sendArcsFlg or null
     */
    public static String getSendArcsFlgForPrntVndTp(String glblCmpyCd, String prntVndTpCd) {
        PRNT_VND_TPTMsg prntVndTp = new PRNT_VND_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prntVndTp.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prntVndTp.prntVndTpCd, prntVndTpCd);

        prntVndTp = (PRNT_VND_TPTMsg) EZDTBLAccessor.findByKey(prntVndTp);

        if (prntVndTp != null) {
            return prntVndTp.sendArcsFlg.getValue();
        } else {
            return null;
        }
    }

    // START 2022/11/14 D.Mamaril [QC#60617,ADD]
    /**
    * Check if Country Code and State Code are valid
    * @param bizMsg NMAL6860CMsg
    * @param index int
    * @return boolean
    */
   private static boolean validateCtryAndStCd(NMAL6860CMsg bizMsg, int index) {

       CTRYTMsg ctry = new CTRYTMsg();
       ZYPEZDItemValueSetter.setValue(ctry.glblCmpyCd, bizMsg.glblCmpyCd);
       ZYPEZDItemValueSetter.setValue(ctry.ctryCd, bizMsg.A.no(index).ctryCd_A);
       ctry = (CTRYTMsg) S21ApiTBLAccessor.findByKey(ctry);
       if (ctry == null) {
           bizMsg.setMessageInfo(NMZM0293E);
           bizMsg.A.no(index).ctryCd_A.setErrorInfo(1, NMZM0293E);
           return false;
       }

       if (CTRY.UNITED_STATES_OF_AMERICA.equals(bizMsg.A.no(index).ctryCd_A.getValue())) {
           STTMsg st = new STTMsg();
           ZYPEZDItemValueSetter.setValue(st.glblCmpyCd, bizMsg.glblCmpyCd);
           ZYPEZDItemValueSetter.setValue(st.stCd, bizMsg.A.no(index).stCd_A);
           st = (STTMsg) S21ApiTBLAccessor.findByKey(st);
           if (st == null || !CTRY.UNITED_STATES_OF_AMERICA.equals(st.ctryCd.getValue())) {
               bizMsg.setMessageInfo(NMZM0292E);
               bizMsg.A.no(index).stCd_A.setErrorInfo(1, NMZM0292E);
               return false;
           }
       }

       return true;
   }
   // END 2022/11/14 D.Mamaril [QC#60617,ADD]
}
