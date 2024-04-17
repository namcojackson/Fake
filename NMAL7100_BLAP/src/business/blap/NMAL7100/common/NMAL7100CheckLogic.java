package business.blap.NMAL7100.common;

import static business.blap.NMAL7100.constant.NMAL7100Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM0043E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM0163E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM0265E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8191E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8197E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8213E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8215E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8216E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8296E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.NMAM8368E;
import static business.blap.NMAL7100.constant.NMAL7100Constant.SAME_EFF_DATE;
import static business.blap.NMAL7100.constant.NMAL7100Constant.SAME_PRMO_ITEM;
import static business.blap.NMAL7100.constant.NMAL7100Constant.SAME_PRMO_VAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDCDateItem;
import parts.common.EZDCSVInFile;
import parts.common.EZDCStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSDateItem;
import parts.common.EZDSStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.blap.NMAL7100.NMAL7100Query;
import business.blap.NMAL7100.NMAL7100SMsg;
import business.blap.NMAL7100.NMAL7100_ASMsg;
import business.blap.NMAL7100.NMAL7100_XCMsg;
import business.blap.NMAL7100.NMAL7100_YCMsg;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRC_MKT_PRMOTMsg;
import business.db.PRC_MKT_PRMO_AUDC_CTRLTMsg;
import business.db.PRC_MKT_PRMO_DTLTMsg;
import business.db.PRC_MKT_PRMO_EXCLTMsg;
import business.file.NMAL7100F01FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_BR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_CH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_PRMO_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7100CheckLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   Fujitsu         M.Hara          Create          N/A
 * 01/19/2016   Fujitsu         M.Hara          Update          QC#3002
 * 01/21/2016   Fujitsu         M.Hara          Update          QC#3386
 * 02/23/2016   Fujitsu         W.Honda         Update          QC#4251
 * 03/24/2016   Fujitsu         Y.Kanefusa      Update          QC#5787
 * 03/31/2016   Fujitsu         M.Nakamura      Update          QC#6234
 * 04/15/2016   Fujitsu         W.Honda         Update          QC#6154
 * 09/12/2016   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2017/01/11   Fujitsu         C.Yokoi         Update          QC#17071
 * 2019/12/06   Fujitsu         C.Hara          Update          QC#54216
 *</pre>
 */
public class NMAL7100CheckLogic {

    /**
     * checkHeader
     * @param bizMsg NMAL7100CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkHeader(NMAL7100CMsg bizMsg, String glblCmpyCd) {
        Boolean isSuccess = true;

        // *********************************************************************
        // Unique Check
        // *********************************************************************

        // 2017/01/11 CSA-QC#17071 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoPk_BK)
                && !ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoPk_H1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcMktPrmoPk_H1, bizMsg.prcMktPrmoPk_BK);
        }
        // 2017/01/11 CSA-QC#17071 Add End

        BigDecimal prcMktPrmoPk = bizMsg.prcMktPrmoPk_H1.getValue();

        if (!ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoPk_BK) || prcMktPrmoPk.compareTo(bizMsg.prcMktPrmoPk_BK.getValue()) != 0) {
            PRC_MKT_PRMOTMsg tMsg = new PRC_MKT_PRMOTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.prcMktPrmoPk, prcMktPrmoPk);

            PRC_MKT_PRMOTMsg findMsg = (PRC_MKT_PRMOTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (findMsg != null) {
                bizMsg.prcMktPrmoPk_H1.setErrorInfo(1, NMAM8296E, new String[] {"Marketing Program ID"});
                isSuccess = false;
            }
        }

        // *********************************************************************
        // Header Date Compare Check
        // *********************************************************************
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_H1)) {
            if (ZYPDateUtil.compare(bizMsg.effFromDt_H1.getValue(), bizMsg.effThruDt_H1.getValue()) > 0) {
                bizMsg.effFromDt_H1.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                isSuccess = false;
            }
        }

        // *********************************************************************
        // Marketing Program Type Master Check
        // *********************************************************************
        S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getPrcMktPrmoTp(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.effFromDt_H1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.prcMktPrmoTpCd_H1.getValue(), "Marketing Program Type"});
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * checkCustAudTab
     * @param bizMsg NMAL7100CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkCustAudTab(NMAL7100CMsg bizMsg, String glblCmpyCd) {

        boolean isSuccess = true;

        // Del Start #4032 02/12/2016
//        // *********************************************************************
//        // CustAud Count Check
//        // *********************************************************************
//        if (bizMsg.X.getValidCount() <= 0) {
//            bizMsg.setMessageInfo(NMAM8214E, new String[] {"Marketing Program Audience"});
//
//            return false;
//        }
        // Del End #4032 02/12/2016

        // *********************************************************************
        // CustAud Value Check
        // *********************************************************************
        String val1 = "";
        String val2 = "";
        String val3 = "";
        for (int i = 0; i < bizMsg.X.getValidCount(); i++) {

            // START QC#3002 01/19/2016 MOD
            // Effective Date
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).effThruDt_MX)) {
                if (ZYPDateUtil.compare(bizMsg.X.no(i).effFromDt_MX.getValue(), NMAL7100CommonLogic.convertDateTo(bizMsg.X.no(i).effThruDt_MX.getValue())) > 0) {
                    bizMsg.X.no(i).effFromDt_MX.setErrorInfo(1, NMAM0043E, new String[] {"Effective Date From", "Effective Date To" });
                    isSuccess = false;
                }
            }

            // Business
            // Value 1
            if (MKT_PRMO_AUDC_CATG.PUBLIC.equals(bizMsg.X.no(i).mktPrmoAudcCatgCd_X1.getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).mktPrmoAudcCatgCd_X2)
                        || ZYPCommonFunc.hasValue(bizMsg.X.no(i).xxScrItem30Txt_X2)
                        || ZYPCommonFunc.hasValue(bizMsg.X.no(i).mktPrmoAudcCatgCd_X3)
                        || ZYPCommonFunc.hasValue(bizMsg.X.no(i).xxScrItem30Txt_X3)) {
                    bizMsg.X.no(i).mktPrmoAudcCatgCd_X2.setErrorInfo(1, NMAM8215E);
                    bizMsg.X.no(i).xxScrItem30Txt_X2.setErrorInfo(1, NMAM8215E);
                    bizMsg.X.no(i).mktPrmoAudcCatgCd_X3.setErrorInfo(1, NMAM8215E);
                    bizMsg.X.no(i).xxScrItem30Txt_X3.setErrorInfo(1, NMAM8215E);
                    isSuccess = false;
                   continue;
                }
            } else {
                val1 = getNameMstMktAudc(bizMsg.X.no(i).mktPrmoAudcCatgCd_X1.getValue(), bizMsg.X.no(i).xxScrItem30Txt_X1, glblCmpyCd);
                if (val1 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.X.no(i).xxRecNmTxt_X1, val1);
                }
            }
            // Value 2
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).mktPrmoAudcCatgCd_X2)) {
                val2 = getNameMstMktAudc(bizMsg.X.no(i).mktPrmoAudcCatgCd_X2.getValue(), bizMsg.X.no(i).xxScrItem30Txt_X2, glblCmpyCd);
                if (val2 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.X.no(i).xxRecNmTxt_X2, val2);
                }
            }

            // Value 3
            if (ZYPCommonFunc.hasValue(bizMsg.X.no(i).mktPrmoAudcCatgCd_X3)) {
                val3 = getNameMstMktAudc(bizMsg.X.no(i).mktPrmoAudcCatgCd_X3.getValue(), bizMsg.X.no(i).xxScrItem30Txt_X3, glblCmpyCd);
                if (val3 == null) {
                    isSuccess = false;
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.X.no(i).xxRecNmTxt_X3, val3);
                }
            }
        }

        // END QC#3002 01/19/2016 MOD

        return isSuccess;
    }

    private static String getNameMstMktAudc(String prmoAudcCatg, EZDCStringItem valueFiled, String glblCmpyCd) {

        String rtrnVal = "";

        if (MKT_PRMO_AUDC_CATG.BUSINESS_LINE.equals(prmoAudcCatg)) {
            rtrnVal = ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Line Business Type"});
                return null;
            }

        } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(prmoAudcCatg)) {
            // START QC#3002 01/19/2016 MOD
            S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getDsAcctNm(valueFiled.getValue());
            // END QC#3002 01/19/2016 MOD
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Account Number"});
                return null;
            }
            rtrnVal =  (String) ssmResult.getResultObject();

        } else if (MKT_PRMO_AUDC_CATG.COA_CHANNEL.equals(prmoAudcCatg)) {
            rtrnVal = ZYPCodeDataUtil.getName(COA_CH.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "COA Channel"});
                return null;
            }
        } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(prmoAudcCatg)) {
            rtrnVal = ZYPCodeDataUtil.getName(DS_ACCT_GRP.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Account Group"});
                return null;
            }
        } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(prmoAudcCatg)) {
            rtrnVal = ZYPCodeDataUtil.getName(COA_BR.class, glblCmpyCd, valueFiled.getValue());
            if (rtrnVal == null) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "COA Branch"});
                return null;
            }
        } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(prmoAudcCatg)) {
            S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getDsAcctNmByCsmp(valueFiled.getValue());
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "CSMP Number"});
                return null;
            }
            rtrnVal =  (String) ssmResult.getResultObject();
        } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(prmoAudcCatg)) {
            if (!ZYPCommonFunc.hasValue(valueFiled)
                    || !ZYPCommonFunc.isNumberCheck(valueFiled.getValue())) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Price Group"});
                return null;
            }
            S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getPrcGrp(valueFiled.getValue(), PRC_GRP_TP.CUSTOMER_PRICING_GROUP, true);
            if (ssmResult.isCodeNotFound()) {
                valueFiled.setErrorInfo(1, NMAM0163E, new String[] {valueFiled.getValue(), "Price Group"});
                return null;
            }
            rtrnVal = (String) ((Map< ? , ? >) ssmResult.getResultObject()).get("PRC_GRP_NM");
       }
        return rtrnVal;
    }

    /**
     * checkCanNotBeUsedTab
     * @param bizMsg NMAL7100CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkCanNotBeUsedTab(NMAL7100CMsg bizMsg, String glblCmpyCd) {

        boolean isSuccess = true;
        String fldVal;
        PRC_CATGTMsg inTMsg = new PRC_CATGTMsg();

        // *********************************************************************
        // CanNotBeUsed Value Check
        // *********************************************************************
        for (int i = 0; i < bizMsg.Y.getValidCount(); i++) {
            fldVal = bizMsg.Y.no(i).prcCatgCd_CX.getValue();

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, fldVal);
            PRC_CATGTMsg outTMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(inTMsg);
            if (outTMsg == null) {
                bizMsg.Y.no(i).prcCatgCd_CX.setErrorInfo(1, NMAM0163E, new String[] {fldVal, "Price Category"});
                isSuccess = false;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.Y.no(i).prcCatgNm_CX, outTMsg.prcCatgNm);
            }
        }

        return isSuccess;
    }

    // START QC#15842 11/10/2016 MOD
    /**
     * boolean
     * @param bizMsg NMAL7100CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
//    public static boolean checkDetailList(NMAL7100CMsg bizMsg, String glblCmpyCd) {// QC#15842 11/10/2016 MOD
    public static boolean checkDetailList(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, String glblCmpyCd) {// QC#15842 11/10/2016 MOD

        boolean isSuccess = true;
        String fldQlfyTypeCd;
//        NMAL7100_ACMsg bizACMsg;// QC#15842 11/10/2016 MOD
        NMAL7100_ASMsg glblASMsg;// QC#15842 11/10/2016 MOD
        int errIdx = -1;// QC#15842 11/10/2016 ADD

        // *********************************************************************
        // Header & Detail Date Range Check
        // *********************************************************************
/*        String hdrFromDt = bizMsg.effFromDt_H1.getValue();
        String hdrThruDt = NMAL7100CommonLogic.convertDateTo(bizMsg.effThruDt_H1.getValue());
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizACMsg = bizMsg.A.no(i);

            if (!isRange(hdrFromDt, bizACMsg.effFromDt_DA.getValue(), hdrThruDt)) {
                bizACMsg.effFromDt_DA.setErrorInfo(1, NMAM8213E);
                isSuccess = false;
            }

            if (!isRange(hdrFromDt, NMAL7100CommonLogic.convertDateTo(bizACMsg.effThruDt_DA.getValue()), hdrThruDt)) {
                bizACMsg.effThruDt_DA.setErrorInfo(1, NMAM8213E);
                isSuccess = false;
            }
        }

        if (!isSuccess) {
            return false;
        }*/

        // *********************************************************************
        // Qualifier Type Check
        // *********************************************************************
        ORD_TAKE_MDSETMsg ordTakeMdse;
        // START QC#6154 04/15/2016 ADD
        boolean isAllSuccess = true;
        // END QC#6154 04/15/2016 ADD
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {// QC#15842 11/10/2016 MOD
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {// QC#15842 11/10/2016 MOD
//            bizACMsg = bizMsg.A.no(i);// QC#15842 11/10/2016 MOD
            glblASMsg = glblMsg.A.no(i);// QC#15842 11/10/2016 MOD

//            fldQlfyTypeCd = bizACMsg.prcPrmoQlfyTpCd_DA.getValue();// QC#15842 11/10/2016 MOD
            fldQlfyTypeCd = glblASMsg.prcPrmoQlfyTpCd_DA.getValue();// QC#15842 11/10/2016 MOD

            if (PRC_PRMO_QLFY_TP.ITEM_CODE.equals(fldQlfyTypeCd)) {
//                isSuccess = checkDetailItemList(bizACMsg, glblCmpyCd);// QC#15842 11/10/2016 MOD
                isSuccess = checkDetailItemList(glblASMsg, glblCmpyCd);// QC#15842 11/10/2016 MOD
            } else if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(fldQlfyTypeCd)) {
//                isSuccess = checkDetailListForSvrModel(bizACMsg.prcQlfyValTxt_DA, glblCmpyCd);// QC#15842 11/10/2016 MOD
                isSuccess = checkDetailListForSvrModel(glblASMsg.prcQlfyValTxt_DA, glblCmpyCd);// QC#15842 11/10/2016 MOD
            } else if (PRC_PRMO_QLFY_TP.BUNDLE.equals(fldQlfyTypeCd)) {
             // START QC#3386 01/21/2016 MOD
//                ordTakeMdse = getOrdTakeMdse(bizACMsg.prcQlfyValTxt_DA.getValue(), glblCmpyCd);// QC#15842 11/10/2016 MOD
//                isSuccess = checkPromotionExist(bizACMsg.prcQlfyValTxt_DA, ordTakeMdse, null, glblCmpyCd, "<>");// QC#15842 11/10/2016 MOD
                ordTakeMdse = getOrdTakeMdse(glblASMsg.prcQlfyValTxt_DA.getValue(), glblCmpyCd);// QC#15842 11/10/2016 MOD
                isSuccess = checkPromotionExist(glblASMsg.prcQlfyValTxt_DA, ordTakeMdse, null, glblCmpyCd, "<>");// QC#15842 11/10/2016 MOD
             // END QC#3386 01/21/2016 MOD
            }

            // *********************************************************************
            // Promotion Item Code Exist Check
            // *********************************************************************
            // START QC#3386 01/21/2016 MOD
//            ordTakeMdse = getOrdTakeMdse(bizACMsg.mdseCd_DA.getValue(), glblCmpyCd);// QC#15842 11/10/2016 MOD
            ordTakeMdse = getOrdTakeMdse(glblASMsg.mdseCd_DA.getValue(), glblCmpyCd);// QC#15842 11/10/2016 MOD
            // START QC#5787 03/24/2016 MOD
            //if (!checkPromotionExist(bizACMsg.mdseCd_DA, ordTakeMdse, bizACMsg.mdseNm_DA, glblCmpyCd, "<>")) {
            // Mod Start 2016/09/12 QC#11615
//            if (!checkPromotionExist(bizACMsg.mdseCd_DA, ordTakeMdse, bizACMsg.mdseDescShortTxt_DA, glblCmpyCd, "=")) {// QC#15842 11/10/2016 MOD
            if (!checkPromotionExist(glblASMsg.mdseCd_DA, ordTakeMdse, glblASMsg.mdseDescShortTxt_DA, glblCmpyCd, "=")) {// QC#15842 11/10/2016 MOD
                isSuccess = false;
            }
            // Mod End 2016/09/12 QC#11615
            // START QC#5787 03/24/2016 MOD
            // END QC#3386 01/21/2016 MOD
            if (!isSuccess) {
                if (errIdx == -1) {
                    errIdx = i;
                }
                isAllSuccess = false;
            }
        }

        if (!isAllSuccess) {
            bizMsg.xxPageShowFromNum.setValue(errIdx);// QC#15842 11/10/2016 ADD
            NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);// QC#15842 11/10/2016 ADD
            return false;
        }

        // *********************************************************************
        // Promotion Item Code Unique & Date Check
        // *********************************************************************
//        isSuccess = checkPrmoUniqDate(bizMsg, glblCmpyCd);// QC#15842 11/10/2016 MOD
        isSuccess = checkPrmoUniqDate(glblMsg, bizMsg, glblCmpyCd);// QC#15842 11/10/2016 MOD

        return isSuccess;
    }
    // END QC#15842 11/10/2016 MOD

    /**
     * checkDetailItemList
     * @param bizACMsg NMAL7100_ACMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkDetailItemList(NMAL7100_ASMsg glblASMsg, String glblCmpyCd) {

        // *********************************************************************
        // Qualifier Type Value Mdse Exist Check
        // *********************************************************************
//        boolean isSuccess = checkMdseNm(bizACMsg.prcQlfyValTxt_DA, null, glblCmpyCd);// QC#15842 11/10/2016 MOD
        boolean isSuccess = checkMdseNm(glblASMsg.prcQlfyValTxt_DA, null, glblCmpyCd);// QC#15842 11/10/2016 MOD

        // *********************************************************************
        // Qualifier Type Value Promotion Item Code Exist Check
        // *********************************************************************
     // START QC#3386 01/21/2016 MOD
//        ORD_TAKE_MDSETMsg ordTakeMdse = getOrdTakeMdse(bizACMsg.prcQlfyValTxt_DA.getValue(), glblCmpyCd);// QC#15842 11/10/2016 MOD
        ORD_TAKE_MDSETMsg ordTakeMdse = getOrdTakeMdse(glblASMsg.prcQlfyValTxt_DA.getValue(), glblCmpyCd);// QC#15842 11/10/2016 MOD
//        if (!checkPromotionExist(bizACMsg.prcQlfyValTxt_DA, ordTakeMdse, null, glblCmpyCd, "<>")) {// QC#15842 11/10/2016 MOD
        if (!checkPromotionExist(glblASMsg.prcQlfyValTxt_DA, ordTakeMdse, null, glblCmpyCd, "<>")) {// QC#15842 11/10/2016 MOD
            isSuccess = false;
        }
        // END QC#3386 01/21/2016 MOD

        return isSuccess;
    }


    /**
     * checkMdseNm
     * @param checkCdFiled EZDCStringItem
     * @param setNmFiled EZDCStringItem
     * @param glblCmpyCd String
     * @return Boolean
     */
//    public static Boolean checkMdseNm(EZDCStringItem checkCdFiled, EZDCStringItem setNmFiled, String glblCmpyCd) {// QC#15842 11/10/2016 MOD
    public static Boolean checkMdseNm(EZDSStringItem checkCdFiled, EZDSStringItem setNmFiled, String glblCmpyCd) {// QC#15842 11/10/2016 MOD

        String mdseCd = checkCdFiled.getValue();
        String mdseCd8 = mdseCd;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();

        if (mdseCd.length() > 8) {
            mdseCd8 = mdseCd.substring(0, 8);
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd8);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg != null) {
            if (mdseCd.length() > 8) {
                checkCdFiled.setErrorInfo(1, NMAM8216E);
                return false;
            } else if (setNmFiled == null) {
                return true;
            }

            // START QC#3386 01/21/2016 ADD
            mdseCd = ordTakeOutTMsg.mdseCd.getValue();
            // END QC#3386 01/21/2016 ADD
        }

        // Mod Start 2016/09/12 QC#11615
//        MDSETMsg mdseInTMsg = new MDSETMsg();
        MDSETMsg mdseInTMsg = new MDSETMsg();
        // Mod End 2016/09/12 QC#11615
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);
        // Mod Start 2016/09/12 QC#11615
//        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        // Mod End 2016/09/12 QC#11615
        if (mdseOutTMsg == null) {
            checkCdFiled.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise"});
            return false;
        } else if (setNmFiled != null) {
            // Mod Start 2016/09/12 QC#11615
//            ZYPEZDItemValueSetter.setValue(setNmFiled, mdseOutTMsg.mdseNm.getValue());
            ZYPEZDItemValueSetter.setValue(setNmFiled, mdseOutTMsg.mdseDescShortTxt.getValue());
            // Mod End 2016/09/12 QC#11615
        }

        return true;
    }

    /**
     * getOrdTakeMdse
     * @param mdseCd String
     * @param glblCmpyCd String
     * @return ORD_TAKE_MDSETMsg
     */
    public static ORD_TAKE_MDSETMsg getOrdTakeMdse(String mdseCd, String glblCmpyCd) {

        String mdseCd8 = mdseCd;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();

        if (mdseCd.length() > 8) {
            mdseCd8 = mdseCd.substring(0, 8);
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd8);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg != null) {
            // Over 8Chars Exist
            if (mdseCd.length() > 8) {
                return null;
            }
        }

        return ordTakeOutTMsg;
    }

    /**
     * checkPromotionExist
     * @param checkCdFiled EZDCStringItem
     * @param useMdse String
     * @param setNmFiled EZDCStringItem
     * @param glblCmpyCd String
     * @param sign String
     * @return boolean
     */
//    public static boolean checkPromotionExist(EZDCStringItem checkCdFiled, ORD_TAKE_MDSETMsg useMdse, EZDCStringItem setNmFiled, String glblCmpyCd, String sign) {// QC#15842 11/10/2016 MOD
    public static boolean checkPromotionExist(EZDSStringItem checkCdFiled, ORD_TAKE_MDSETMsg useMdse, EZDSStringItem setNmFiled, String glblCmpyCd, String sign) {// QC#15842 11/10/2016 MOD
        boolean isSuccess = true;

        // START QC#3386 01/21/2016 MOD
        String mdseCd;

        if (useMdse != null && ZYPCommonFunc.hasValue(useMdse.mdseCd)) {
            mdseCd = useMdse.mdseCd.getValue();
        } else {
            mdseCd = checkCdFiled.getValue();
        }

        // END QC#3386 01/21/2016 MOD
        S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getPromItmCd(glblCmpyCd, mdseCd, sign);

        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            // Not Found
            checkCdFiled.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise"});
            isSuccess = false;
        } else if (setNmFiled != null) {
            String rtrnVal = (String) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(setNmFiled, rtrnVal);
        }

        return isSuccess;
    }

    /**
     * checkDetailListForSvrModel
     * @param checkCdFiled EZDCStringItem
     * @param glblCmpyCd String
     * @return boolean
     */
//    public static boolean checkDetailListForSvrModel(EZDCStringItem checkCdFiled, String glblCmpyCd) {// QC#15842 11/10/2016 MOD
    public static boolean checkDetailListForSvrModel(EZDSStringItem checkCdFiled, String glblCmpyCd) {// QC#15842 11/10/2016 MOD
        // START QC#6154 04/15/2016 MOD
//        boolean isSuccess = true;
//
//        // START QC#3002 01/19/2016 MOD
//        S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getMdlId(checkCdFiled.getValue());
//        // END QC#3002 01/19/2016 MOD
//
//        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
//            checkCdFiled.setErrorInfo(1, NMAM0163E, new String[] {checkCdFiled.getValue(), "Model Name"});
//            isSuccess = false;
//        }else{
//            BigDecimal mdlId = (BigDecimal) ssmResult.getResultObject();
//            if(mdlId != null){
//                // START QC#6234 03/31/2016 MOD
////                ZYPEZDItemValueSetter.setValue(checkCdFiled, String.valueOf(mdlId.intValue()));
//                ZYPEZDItemValueSetter.setValue(checkCdFiled, mdlId.toString());
//                // END   QC#6234 03/31/2016 MOD
//            }
//        }
//
//        return isSuccess;
        return NMAL7100CommonLogic.searchSvrModel(checkCdFiled, glblCmpyCd, false);
    }
    // END QC#6154 04/15/2016 MOD

    /**
     * CreateKey
     * @param bizACMsg NMAL7100_ACMsg
     * @return String
     */
//    public static String createKey(NMAL7100_ACMsg bizACMsg) {// QC#15842 11/10/2016 MOD
    public static String createKey(NMAL7100_ASMsg glblASMsg) {// QC#15842 11/10/2016 MOD
        String key;

//        key = String.format("%s,%s,%s"
//                                , bizACMsg.mdseCd_DA.getValue()
//                                , bizACMsg.effFromDt_DA.getValue()
//                                , bizACMsg.effThruDt_DA.getValue());

//        key = String.format("%s,%s,%s"
//                                , bizACMsg.prcPrmoQlfyTpCd_DA.getValue()// QC#15842 11/10/2016 MOD
//                                , bizACMsg.prcQlfyValTxt_DA.getValue()// QC#15842 11/10/2016 MOD
//                                , bizACMsg.mdseCd_DA.getValue());// QC#15842 11/10/2016 MOD

        key = String.format("%s,%s,%s"
                                , glblASMsg.prcPrmoQlfyTpCd_DA.getValue()
                                , glblASMsg.prcQlfyValTxt_DA.getValue()
                                , glblASMsg.mdseCd_DA.getValue());

        return key;
    }

    // START QC#15842 11/10/2016 MOD
//    /**
//     * checkPrmoUniqDate
//     * @param bizMsg NMAL7100CMsg
//     * @param glblCmpyCd String
//     * @return boolean
//     */
//    public static boolean checkPrmoUniqDate(NMAL7100CMsg bizMsg, String glblCmpyCd) {
//        boolean isSuccess = true;
//        NMAL7100_ACMsg bizACMsg;
//        String key, bundleKey, fldQlfyTypeCd;
//        LinkedHashMap<String, List<NMAL7100_ACMsg>> mapPrmoDate = new LinkedHashMap<String, List<NMAL7100_ACMsg>>();
//        LinkedHashMap<String, List<NMAL7100_ACMsg>> mapPrmoBundle = new LinkedHashMap<String, List<NMAL7100_ACMsg>>();
//
//        List<NMAL7100_ACMsg> lstUniq, lstBundle;
//
//        // *********************************************************************
//        // Promotion Item Code Unique Check
//        // *********************************************************************
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            bizACMsg = bizMsg.A.no(i);
//            fldQlfyTypeCd = bizACMsg.prcPrmoQlfyTpCd_DA.getValue();
//
//            // Mod Start 2016/02/23 CSA-QC#4251
////            // Prepare For Date Range Check
////            prmoItmCd = bizACMsg.mdseCd_DA.getValue();
////            lstDate = mapPrmoDate.get(prmoItmCd);
////            if (lstDate == null) {
////                lstDate = new ArrayList<NMAL7100_ACMsg>();
////                mapPrmoDate.put(prmoItmCd, lstDate);
////            }
////            lstDate.add(bizACMsg);
////
////            // Prepare For BUNDLE Check
////            if (PRC_PRMO_QLFY_TP.BUNDLE.equals(fldQlfyTypeCd)) {
////                bundleKey = String.format("%s-%s", bizACMsg.prcQlfyValTxt_DA.getValue(), bizACMsg.mdseCd_DA.getValue());
////                lstBundle = mapPrmoBundle.get(bundleKey);
////                if (lstBundle == null) {
////                    lstBundle = new ArrayList<NMAL7100_ACMsg>();
////                    mapPrmoBundle.put(bundleKey, lstBundle);
////                }
////                lstBundle.add(bizACMsg);
////            }
////
////            // Unique Check
////            key = createKey(bizACMsg);
////            lstUniq = mapPrmoUniq.get(key);
////
////            if (lstUniq == null) {
////                lstUniq = new ArrayList<NMAL7100_ACMsg>();
////                lstUniq.add(bizACMsg);
////                mapPrmoUniq.put(key, lstUniq);
////            } else {
////                if (PRC_PRMO_QLFY_TP.BUNDLE.equals(fldQlfyTypeCd)) {
////                    lstUniq.add(bizACMsg);
////                } else {
////                    bizACMsg.mdseCd_DA.setErrorInfo(1, NMAM8296E, new String[] {"Promotion Item Code"});
////                    lstUniq.get(0).mdseCd_DA.setErrorInfo(1, NMAM8296E, new String[] {"Promotion Item Code"});
////
////                    isSuccess = false;
////                }
////            }
////        }
////
////        if (!isSuccess) {
////            return false;
////        }
//
//            // Prepare For BUNDLE Check
//            if (PRC_PRMO_QLFY_TP.BUNDLE.equals(fldQlfyTypeCd)) {
//                bundleKey = bizACMsg.prcMktPrmoCd_DA.getValue();
//                lstBundle = mapPrmoBundle.get(bundleKey);
//                if (lstBundle == null) {
//                    lstBundle = new ArrayList<NMAL7100_ACMsg>();
//                    mapPrmoBundle.put(bundleKey, lstBundle);
//                }
//                lstBundle.add(bizACMsg);
//            }
//
//            // Prepare For Date Range Check
//            key = createKey(bizACMsg);
//            lstUniq = mapPrmoDate.get(key);
//
//            if (lstUniq == null) {
//                lstUniq = new ArrayList<NMAL7100_ACMsg>();
//                lstUniq.add(bizACMsg);
//                mapPrmoDate.put(key, lstUniq);
//            }
//            lstUniq.add(bizACMsg);
//        }
//        // Mod End 2016/02/23 CSA-QC#4251
//
//        if (!isSuccess) {
//            return false;
//        }
//
//        // Mod Start 2016/02/23 CSA-QC#4251
//        // *********************************************************************
//        // BUNDLE Check
//        // *********************************************************************
////        BigDecimal prmoVal = BigDecimal.ZERO;
////        Boolean isBundleSuccess;
//
////        for (Entry<String, List<NMAL7100_ACMsg>> lstACMsg : mapPrmoBundle.entrySet()) {
////            isBundleSuccess = true;
////            if (lstACMsg.getValue().size() > 0) {
////                prmoVal = lstACMsg.getValue().get(0).prmoAmt_DA.getValue();
////            }
////            for (NMAL7100_ACMsg acMsg : lstACMsg.getValue()) {
////                if (prmoVal.compareTo(acMsg.prmoAmt_DA.getValue()) != 0) {
////                    isBundleSuccess = false;
////                    break;
////                }
////            }
////
////            if (!isBundleSuccess) {
////                for (NMAL7100_ACMsg acMsg : lstACMsg.getValue()) {
////                    acMsg.prmoAmt_DA.setErrorInfo(1, NMAM0265E);
////                }
////                isSuccess = false;
////            }
////        }
//        BigDecimal prmoAmtVal = BigDecimal.ZERO;
//        List<String> prmoQlfyValList = new ArrayList<String>();
//        String prmoItemVal = "";
//        String fromDate = "";
//        String toDate = "";
//        Boolean isPrmoAmtSuccess, isPrmoItemSuccess, isPrmoQlfyValSuccess, isDateMatch;
//
//        for (Entry<String, List<NMAL7100_ACMsg>> lstACMsg : mapPrmoBundle.entrySet()) {
//            isPrmoAmtSuccess = true;
//            isPrmoItemSuccess = true;
//            isPrmoQlfyValSuccess = true;
//            isDateMatch = true;
//            if (lstACMsg.getValue().size() > 0) {
//                prmoAmtVal = lstACMsg.getValue().get(0).prmoAmt_DA.getValue();
//                prmoItemVal = lstACMsg.getValue().get(0).mdseCd_DA.getValue();
//                fromDate = lstACMsg.getValue().get(0).effFromDt_DA.getValue();
//                toDate = NMAL7100CommonLogic.convertDateTo(lstACMsg.getValue().get(0).effThruDt_DA.getValue());
//                prmoQlfyValList = new ArrayList<String>();
//            }
//            for (NMAL7100_ACMsg acMsg : lstACMsg.getValue()) {
//                if (prmoAmtVal.compareTo(acMsg.prmoAmt_DA.getValue()) != 0) {
//                    isPrmoAmtSuccess = false;
//                }
//
//                if (!prmoItemVal.equals(acMsg.mdseCd_DA.getValue())) {
//                    isPrmoItemSuccess = false;
//                }
//
//                if (!prmoQlfyValList.contains(acMsg.prcQlfyValTxt_DA.getValue())) {
//                    prmoQlfyValList.add(acMsg.prcQlfyValTxt_DA.getValue());
//                } else {
//                    isPrmoQlfyValSuccess = false;
//                }
//
//                
//                if (!fromDate.equals(acMsg.effFromDt_DA.getValue())
//                        || !toDate.equals(NMAL7100CommonLogic.convertDateTo(acMsg.effThruDt_DA.getValue()))) {
//                    isDateMatch = false;
//                }
//            }
//
//            if (!isPrmoAmtSuccess || !isPrmoItemSuccess || !isPrmoQlfyValSuccess || !isDateMatch) {
//                for (NMAL7100_ACMsg acMsg : lstACMsg.getValue()) {
//                    if (!isPrmoAmtSuccess) {
//                        acMsg.prmoAmt_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_PRMO_VAL});
//                    }
//
//                    if (!isPrmoItemSuccess) {
//                        acMsg.mdseCd_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_PRMO_ITEM});
//                    }
//
//                    if (!isPrmoQlfyValSuccess) {
//                        acMsg.prcQlfyValTxt_DA.setErrorInfo(1, NMAM0265E);
//                    }
//
//                    if (!isDateMatch) {
//                        acMsg.effFromDt_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_EFF_DATE});
//                        acMsg.effThruDt_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_EFF_DATE});
//                    }
//                }
//
//                isSuccess = false;
//            }
//        }
//
//        if (!isSuccess) {
//            return false;
//        }
//        // Mod End 2016/02/23 CSA-QC#4251
//
//        // *********************************************************************
//        // Promotion Item Code Date Range Check
//        // *********************************************************************
//        String srcFromDt, srcThruDt, targetFromDt, targetThruDt;
//
//        for (Entry<String, List<NMAL7100_ACMsg>> lstACMsg : mapPrmoDate.entrySet()) {
//            // get Min FromDate & Max ThruDate
//            for (NMAL7100_ACMsg acSrcMsg : lstACMsg.getValue()) {
//                srcFromDt = acSrcMsg.effFromDt_DA.getValue();
//                srcThruDt = NMAL7100CommonLogic.convertDateTo(acSrcMsg.effThruDt_DA.getValue());
//                for (NMAL7100_ACMsg acTargetMsg : lstACMsg.getValue()) {
//                    if (acSrcMsg != acTargetMsg) {
//                        targetFromDt = acTargetMsg.effFromDt_DA.getValue();
//                        targetThruDt = NMAL7100CommonLogic.convertDateTo(acTargetMsg.effThruDt_DA.getValue());
//
//                        if (ZYPDateUtil.compare(targetFromDt, srcThruDt) <= 0
//                                && ZYPDateUtil.compare(srcFromDt, targetThruDt) <= 0) {
//                            acSrcMsg.effFromDt_DA.setErrorInfo(1, NMAM8213E);
//                            acSrcMsg.effThruDt_DA.setErrorInfo(1, NMAM8213E);
//                            acTargetMsg.effFromDt_DA.setErrorInfo(1, NMAM8213E);
//                            acTargetMsg.effThruDt_DA.setErrorInfo(1, NMAM8213E);
//
//                            isSuccess = false;
//                        }
//                    }
//                }
//            }
//        }
//
//        return isSuccess;
//    }

    /**
     * checkPrmoUniqDate
     * @param glblMsg NMAL7100SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkPrmoUniqDate(NMAL7100SMsg glblMsg, NMAL7100CMsg bizMsg, String glblCmpyCd) {
        boolean isSuccess = true;
        NMAL7100_ASMsg glblASMsg;
        String key, bundleKey, fldQlfyTypeCd;
        LinkedHashMap<String, List<NMAL7100_ASMsg>> mapPrmoDate = new LinkedHashMap<String, List<NMAL7100_ASMsg>>();
        LinkedHashMap<String, List<NMAL7100_ASMsg>> mapPrmoBundle = new LinkedHashMap<String, List<NMAL7100_ASMsg>>();

        List<NMAL7100_ASMsg> lstUniq, lstBundle;

        // *********************************************************************
        // Promotion Item Code Unique Check
        // *********************************************************************
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblASMsg = glblMsg.A.no(i);
            fldQlfyTypeCd = glblASMsg.prcPrmoQlfyTpCd_DA.getValue();

            // Prepare For BUNDLE Check
            if (PRC_PRMO_QLFY_TP.BUNDLE.equals(fldQlfyTypeCd)) {
                bundleKey = glblASMsg.prcMktPrmoCd_DA.getValue();
                lstBundle = mapPrmoBundle.get(bundleKey);
                if (lstBundle == null) {
                    lstBundle = new ArrayList<NMAL7100_ASMsg>();
                    mapPrmoBundle.put(bundleKey, lstBundle);
                }
                lstBundle.add(glblASMsg);
            }

            // Prepare For Date Range Check
            key = createKey(glblASMsg);
            lstUniq = mapPrmoDate.get(key);

            if (lstUniq == null) {
                lstUniq = new ArrayList<NMAL7100_ASMsg>();
                lstUniq.add(glblASMsg);
                mapPrmoDate.put(key, lstUniq);
            }
            lstUniq.add(glblASMsg);
        }

        // *********************************************************************
        // BUNDLE Check
        // *********************************************************************
        BigDecimal prmoAmtVal = BigDecimal.ZERO;
        List<String> prmoQlfyValList = new ArrayList<String>();
        String prmoItemVal = "";
        String fromDate = "";
        String toDate = "";
        Boolean isPrmoAmtSuccess, isPrmoItemSuccess, isPrmoQlfyValSuccess, isDateMatch;

        for (Entry<String, List<NMAL7100_ASMsg>> lstASMsg : mapPrmoBundle.entrySet()) {
            isPrmoAmtSuccess = true;
            isPrmoItemSuccess = true;
            isPrmoQlfyValSuccess = true;
            isDateMatch = true;
            if (lstASMsg.getValue().size() > 0) {
                prmoAmtVal = lstASMsg.getValue().get(0).prmoAmt_DA.getValue();
                prmoItemVal = lstASMsg.getValue().get(0).mdseCd_DA.getValue();
                fromDate = lstASMsg.getValue().get(0).effFromDt_DA.getValue();
                toDate = NMAL7100CommonLogic.convertDateTo(lstASMsg.getValue().get(0).effThruDt_DA.getValue());
                prmoQlfyValList = new ArrayList<String>();
            }
            for (NMAL7100_ASMsg asMsg : lstASMsg.getValue()) {
                if (prmoAmtVal.compareTo(asMsg.prmoAmt_DA.getValue()) != 0) {
                    isPrmoAmtSuccess = false;
                }

                if (!prmoItemVal.equals(asMsg.mdseCd_DA.getValue())) {
                    isPrmoItemSuccess = false;
                }

                if (!prmoQlfyValList.contains(asMsg.prcQlfyValTxt_DA.getValue())) {
                    prmoQlfyValList.add(asMsg.prcQlfyValTxt_DA.getValue());
                } else {
                    isPrmoQlfyValSuccess = false;
                }

                
                if (!fromDate.equals(asMsg.effFromDt_DA.getValue())
                        || !toDate.equals(NMAL7100CommonLogic.convertDateTo(asMsg.effThruDt_DA.getValue()))) {
                    isDateMatch = false;
                }
            }

            if (!isPrmoAmtSuccess || !isPrmoItemSuccess || !isPrmoQlfyValSuccess || !isDateMatch) {
                for (NMAL7100_ASMsg asMsg : lstASMsg.getValue()) {
                    if (!isPrmoAmtSuccess) {
                        asMsg.prmoAmt_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_PRMO_VAL});
                    }

                    if (!isPrmoItemSuccess) {
                        asMsg.mdseCd_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_PRMO_ITEM});
                    }

                    if (!isPrmoQlfyValSuccess) {
                        asMsg.prcQlfyValTxt_DA.setErrorInfo(1, NMAM0265E);
                    }

                    if (!isDateMatch) {
                        asMsg.effFromDt_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_EFF_DATE});
                        asMsg.effThruDt_DA.setErrorInfo(1, NMAM8368E, new String[] {SAME_EFF_DATE});
                    }
                }

                if (isSuccess) {
                    for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                        if (lstASMsg.getKey().equals(glblMsg.A.no(i).prcMktPrmoCd_DA.getValue())) {
                            // first error record.
                            bizMsg.xxPageShowFromNum.setValue(i);
                            break;
                        }
                    }
                    isSuccess = false;
                }
            }
        }

        if (!isSuccess) {
            NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
            return false;
        }

        // *********************************************************************
        // Promotion Item Code Date Range Check
        // *********************************************************************
        String srcFromDt, srcThruDt, targetFromDt, targetThruDt;

        for (Entry<String, List<NMAL7100_ASMsg>> lstASMsg : mapPrmoDate.entrySet()) {
            // get Min FromDate & Max ThruDate
            for (NMAL7100_ASMsg asSrcMsg : lstASMsg.getValue()) {
                srcFromDt = asSrcMsg.effFromDt_DA.getValue();
                srcThruDt = NMAL7100CommonLogic.convertDateTo(asSrcMsg.effThruDt_DA.getValue());
                for (NMAL7100_ASMsg asTargetMsg : lstASMsg.getValue()) {
                    if (asSrcMsg != asTargetMsg) {
                        targetFromDt = asTargetMsg.effFromDt_DA.getValue();
                        targetThruDt = NMAL7100CommonLogic.convertDateTo(asTargetMsg.effThruDt_DA.getValue());

                        if (ZYPDateUtil.compare(targetFromDt, srcThruDt) <= 0
                                && ZYPDateUtil.compare(srcFromDt, targetThruDt) <= 0) {
                            asSrcMsg.effFromDt_DA.setErrorInfo(1, NMAM8213E);
                            asSrcMsg.effThruDt_DA.setErrorInfo(1, NMAM8213E);
                            asTargetMsg.effFromDt_DA.setErrorInfo(1, NMAM8213E);
                            asTargetMsg.effThruDt_DA.setErrorInfo(1, NMAM8213E);

                            if (isSuccess) {
                                String[] values = lstASMsg.getKey().split(",");
                                for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                                    if (values[0].equals(glblMsg.A.no(i).prcPrmoQlfyTpCd_DA.getValue())
                                            && values[1].equals(glblMsg.A.no(i).prcQlfyValTxt_DA.getValue())
                                            && values[2].equals(glblMsg.A.no(i).mdseCd_DA.getValue())) {
                                        // first error record.
                                        bizMsg.xxPageShowFromNum.setValue(i);
                                        break;
                                    }
                                }

                                isSuccess = false;
                            }
                        }
                    }
                }
            }
        }


        if (!isSuccess) {
            NMAL7100CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg, glblMsg.A);
        }
        return isSuccess;
    }
    // END QC#15842 11/10/2016 MOD

    /**
     * isRange
     * @param fromDate String
     * @param chkDate  String
     * @param thruDate String
     * @return Boolean
     */
    public static Boolean isRange(String fromDate, String chkDate, String thruDate) {
        if (ZYPDateUtil.compare(chkDate, fromDate) >= 0) {
            if (ZYPDateUtil.compare(chkDate, thruDate) <= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * readHeaderCsvFile.
     * @param mappedFile EZDCSVInFile
     * @param bizMsg NMAL7100CMsg
     * @return boolean
     */
    public static boolean readHeaderCsvFile(EZDCSVInFile mappedFile, NMAL7100CMsg bizMsg) {
        int header = mappedFile.read();
        if (header == 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[] {"Header Record"});
            return false;
        }
        return true;
    }

    /**
     * validateUpldCust.
     * @param bizMsg NMAL7100CMsg
     * @param status int
     * @param totCount int
     * @param uploadCount int
     * @param length int
     * @return boolean
     */
    public static boolean validateUpldCust(NMAL7100CMsg bizMsg, int status, int totCount, int uploadCount, int length) {
        if (totCount > length) {
            bizMsg.setMessageInfo(NMAM8197E);
            return true;
        }

        if (status == 1000) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Line=" + String.valueOf(uploadCount)});
            return true;
        }
        int errCol1 = status - 1000;
        int errCol2 = status - 2000;
        // Account Number
        if (errCol1 == 1 || errCol2 == 1) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Account Number(Line=" + String.valueOf(uploadCount) + ")"});
            return true;
        // Account Name
        } else if (errCol1 == 2 || errCol2 == 2) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Account Name(Line=" + String.valueOf(uploadCount) + ")"});
            return true;
        }

        return false;
    }


    /**
     * validateUpldCust.
     * @param fMsg NMAL7100F01FMsg
     * @param bizMsg NMAL7100CMsg
     * @param status int
     * @param totCount int
     * @param uploadCount int
     * @param length int
     * @return boolean
     */
    public static boolean validateUpldMktPgm(NMAL7100F01FMsg fMsg, NMAL7100CMsg bizMsg, int status, int totCount, int uploadCount, int length) {
        if (totCount > length) {
            bizMsg.setMessageInfo(NMAM8197E);
            return true;
        }

        if (status == 1000) {
            bizMsg.setMessageInfo(NMAM8191E, new String[]{"Line=" + String.valueOf(uploadCount)});
            return true;
        }
        int errCol = status % 1000;
        String errColNm, errArgs;

        if (errCol > 0) {
            errColNm = CSV_DOWNLOAD_HEADER[errCol - 1];
            errArgs = String.format("%s(Line=%d)", errColNm, uploadCount);
            bizMsg.setMessageInfo(NMAM8191E, new String[]{errArgs});
            return true;
        }

//        if (ZYPCommonFunc.hasValue(fMsg.effFromDt)) {
//            if (!ZYPDateUtil.isValidDate(fMsg.effFromDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
//                bizMsg.setMessageInfo(NMAM8191E, new String[]{"Line=" + String.valueOf(uploadCount)});
//                return true;
//            }
//        }
//        if (ZYPCommonFunc.hasValue(fMsg.effThruDt)) {
//            if (!ZYPDateUtil.isValidDate(fMsg.effThruDt.getValue(), ZYPDateUtil.TYPE_YYYYMMDD)) {
//                bizMsg.setMessageInfo(NMAM8191E, new String[]{"Line=" + String.valueOf(uploadCount)});
//                return true;
//            }
//        }
        return false;
    }

    // 2019/12/06 QC#54216 Add Start
    /**
     * isChangeForPrcMktPrmo
     * @param bizMsg NMAL7100CMsg
     * @param tMsg PRC_MKT_PRMOTMsg
     * @return boolean
     */
    public static boolean isChangeForPrcMktPrmo(NMAL7100CMsg bizMsg, PRC_MKT_PRMOTMsg tMsg) {
        if (!isEqual(bizMsg.prcMktPrmoNm_H1, tMsg.prcMktPrmoNm)) {
            return true;
        }
        if (!isEqual(bizMsg.prcMktPrmoDescTxt_H1, tMsg.prcMktPrmoDescTxt)) {
            return true;
        }
        if (!isEqual(bizMsg.prcMktPrmoCmntTxt_H1, tMsg.prcMktPrmoCmntTxt)) {
            return true;
        }
        if (!isEqual(bizMsg.prcMktPrmoTpCd_H1, tMsg.prcMktPrmoTpCd)) {
            return true;
        }
        if (!isEqual(bizMsg.effFromDt_H1, tMsg.effFromDt)) {
            return true;
        }
        if (!isEqual(bizMsg.effThruDt_H1, tMsg.effThruDt)) {
            return true;
        }
        if (!isEqual(bizMsg.actvFlg_H1, tMsg.actvFlg)) {
            return true;
        }

        return false;
    }

    /**
     * isChangeForAudcCtrl
     * @param xcMsg NMAL7100_XCMsg
     * @param tMsg PRC_MKT_PRMO_AUDC_CTRLTMsg
     * @return boolean
     */
    public static boolean isChangeForAudcCtrl(NMAL7100_XCMsg xcMsg, PRC_MKT_PRMO_AUDC_CTRLTMsg tMsg) {
         String catgCdX1 = xcMsg.mktPrmoAudcCatgCd_X1.getValue();
         EZDCStringItem catgValX1 = xcMsg.xxScrItem30Txt_X1;
         if (MKT_PRMO_AUDC_CATG.PUBLIC.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.pubFlg_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.BUSINESS_LINE.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.lineBizTpCd_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.dsAcctNum_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.COA_CHANNEL.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.coaChCd_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.dsAcctGrpCd_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.coaBrCd_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(catgCdX1)) {
             if (!isEqual(catgValX1, tMsg.csmpNum_01)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(catgCdX1)) {
             if (!ZYPCommonFunc.hasValue(tMsg.prcGrpPk_01)) {
                 return true;
             }
             if (NMAL7100CommonLogic.convToBigDecimal(catgValX1).compareTo(tMsg.prcGrpPk_01.getValue()) != 0) {
                 return true;
             }
         }

         String catgCdX2 = xcMsg.mktPrmoAudcCatgCd_X2.getValue();
         EZDCStringItem catgValX2 = xcMsg.xxScrItem30Txt_X2;
         if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(catgCdX2)) {
             if (!isEqual(catgValX2, tMsg.dsAcctNum_02)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(catgCdX2)) {
             if (!isEqual(catgValX2, tMsg.dsAcctGrpCd_02)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(catgCdX2)) {
             if (!isEqual(catgValX2, tMsg.coaBrCd_02)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(catgCdX2)) {
             if (!isEqual(catgValX2, tMsg.csmpNum_02)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(catgCdX2)) {
             if (!ZYPCommonFunc.hasValue(tMsg.prcGrpPk_02)) {
                 return true;
             }
             if (NMAL7100CommonLogic.convToBigDecimal(catgValX2).compareTo(tMsg.prcGrpPk_02.getValue()) != 0) {
                 return true;
             }
         } else {
             if (ZYPCommonFunc.hasValue(tMsg.dsAcctNum_02)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.dsAcctGrpCd_02)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.coaBrCd_02)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.csmpNum_02)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.prcGrpPk_02)) {
                 return true;
             }
         }

         String catgCdX3 = xcMsg.mktPrmoAudcCatgCd_X3.getValue();
         EZDCStringItem catgValX3 = xcMsg.xxScrItem30Txt_X3;
         if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(catgCdX3)) {
             if (!isEqual(catgValX3, tMsg.dsAcctNum_03)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(catgCdX3)) {
             if (!isEqual(catgValX3, tMsg.dsAcctGrpCd_03)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(catgCdX3)) {
             if (!isEqual(catgValX3, tMsg.coaBrCd_03)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(catgCdX3)) {
             if (!isEqual(catgValX3, tMsg.csmpNum_03)) {
                 return true;
             }
         } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(catgCdX3)) {
             if (!ZYPCommonFunc.hasValue(tMsg.prcGrpPk_03)) {
                 return true;
             }
             if (NMAL7100CommonLogic.convToBigDecimal(catgValX3).compareTo(tMsg.prcGrpPk_03.getValue()) != 0) {
                 return true;
             }
         } else {
             if (ZYPCommonFunc.hasValue(tMsg.dsAcctNum_03)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.dsAcctGrpCd_03)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.coaBrCd_03)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.csmpNum_03)) {
                 return true;
             }
             if (ZYPCommonFunc.hasValue(tMsg.prcGrpPk_03)) {
                 return true;
             }
         }
         if (!isEqual(xcMsg.effFromDt_MX, tMsg.effFromDt)) {
             return true;
         }
         if (!isEqual(xcMsg.effThruDt_MX, tMsg.effThruDt)) {
             return true;
         }
        return false;
    }

    /**
     * isEqual
     * @param item1 EZDCStringItem
     * @param item2 EZDCStringItem
     * @return boolean
     */
    private static boolean isEqual(EZDCStringItem item1, EZDTStringItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().equals(item2.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isEqual
     * @param item1 EZDCDateItem
     * @param item2 EZDCDateItem
     * @return boolean
     */
    private static boolean isEqual(EZDCDateItem item1, EZDTDateItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().compareTo(item2.getValue()) == 0) {
            return true;
        }
        return false;
    }

    /**
     * isChangeForExcl
     * @param ycMsg NMAL7100_YCMsg
     * @param tMsg PRC_MKT_PRMO_EXCLTMsg
     * @return boolean
     */
    public static boolean isChangeForExcl(NMAL7100_YCMsg ycMsg, PRC_MKT_PRMO_EXCLTMsg tMsg) {
        if (!isEqual(ycMsg.prcCatgCd_CX, tMsg.prcCatgCd)) {
            return true;
        }
        if (!isEqual(ycMsg.effFromDt_CX, tMsg.effFromDt)) {
            return true;
        }
        if (!isEqual(ycMsg.effThruDt_CX, tMsg.effThruDt)) {
            return true;
        }

        return false;
    }

    /**
     * isChangeForDtl
     * @param asMsg NMAL7100_ASMsg
     * @param tMsg PRC_MKT_PRMO_DTLTMsg
     * @return boolean
     */
    public static boolean isChangeForDtl(NMAL7100_ASMsg asMsg, PRC_MKT_PRMO_DTLTMsg tMsg, String glblCmpyCd) {
        if (!isEqual(asMsg.prcMktPrmoCd_DA, tMsg.prcMktPrmoCd)) {
            return true;
        }
        if (!isEqual(asMsg.prcPrmoQlfyTpCd_DA, tMsg.prcQlfyTpCd)) {
            return true;
        }
        if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(asMsg.prcPrmoQlfyTpCd_DA.getValue())) {
            S21SsmEZDResult ssmResult = NMAL7100Query.getInstance().getMdlId(asMsg.prcQlfyValTxt_DA.getValue());
            if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
                BigDecimal mdlId = (BigDecimal) ssmResult.getResultObject();
                if(mdlId != null){
                    if (!S21StringUtil.isEquals(mdlId.toString(), tMsg.prcQlfyValTxt.getValue())) {
                        return true;
                    }
                }
            }
        }
        if (!isEqual(asMsg.mdseCd_DA, tMsg.mdseCd)) {
            return true;
        }
        if (!isEqual(asMsg.prmoAmt_DA, tMsg.prmoAmt)) {
            return true;
        }
        if (!isEqual(asMsg.effFromDt_DA, tMsg.effFromDt)) {
            return true;
        }
        if (!isEqual(asMsg.effThruDt_DA, tMsg.effThruDt)) {
            return true;
        }

        return false;
    }

    /**
     * isEqual
     * @param item1 EZDSStringItem
     * @param item2 EZDSStringItem
     * @return boolean
     */
    private static boolean isEqual(EZDSStringItem item1, EZDTStringItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().equals(item2.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isEqual
     * @param item1 EZDSBigDecimalItem
     * @param item2 EZDSBigDecimalItem
     * @return boolean
     */
    private static boolean isEqual(EZDSBigDecimalItem item1, EZDTBigDecimalItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().compareTo(item2.getValue()) == 0) {
            return true;
        }
        return false;
    }

    /**
     * isEqual
     * @param item1 EZDSDateItem
     * @param item2 EZDSDateItem
     * @return boolean
     */
    private static boolean isEqual(EZDSDateItem item1, EZDTDateItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().compareTo(item2.getValue()) == 0) {
            return true;
        }
        return false;
    }
    // 2019/12/06 QC#54216 Add End
}
