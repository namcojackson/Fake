/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_RSN;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;

import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForCategory;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;

import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/15   Fujitsu         Y.Taoka         Update          QC#1676
 * 2016/02/23   Fujitsu         Y.Taoka         Update          QC#1694
 * 2016/03/05   Fujitsu         S.Takami        Update          S21_NA#5000#85
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2018/01/24   Fujitsu         K.Ishizuka      Update          S21_NA#22428
 * 2018/01/30   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/12   Fujitsu         A.Kosai         Update          S21_NA#20297(Sol#379)
 * </pre>
 */
public class NWAL1500CommonLogicForCategory {

    /**
     * Check Input Category
     * @param bizMsg NWAL1500CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static boolean checkCatg(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdCatgCdList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.clear();
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, String>> dsOrdCatgCdList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (dsOrdCatgCdList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_DESC_TXT"));

        return true;
    }

    /**
     * Check Input Reason
     * @param bizMsg NWAL1500CMsg
     * @return check OK : true
     */
    @SuppressWarnings("unchecked")
    public static boolean checkRsn(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getDsOrdTpList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdTpDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_RSN });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, String>> dsOrdTpList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (dsOrdTpList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, dsOrdTpList.get(0).get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt, dsOrdTpList.get(0).get("DS_ORD_TP_DESC_TXT"));

        return true;
    }

    /**
     * Set Initial Value For Line Category Pulldown
     * @param bizMsg NWAL1500CMsg
     * @param primaryLineCatgCd Primary Line Catgory Code
     * @param primaryLineCatgRmaCd Primary Line Catgory Code For RMA
     */
    public static void setInitValueForLineCatgPulldown(NWAL1500SMsg glblMsg, String primaryLineCatgCd, String primaryLineCatgRmaCd) { // 2018/01/30 S21_NA#19808

        if (ZYPCommonFunc.hasValue(primaryLineCatgCd)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                // 2018/01/24 S21_NA#22428 Mod Start
                //ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgCd_LL, primaryLineCatgCd);
                if(!ZYPCommonFunc.hasValue(glblMsg.B.no(i).dsOrdLineCatgCd_LL)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).dsOrdLineCatgCd_LL, primaryLineCatgCd);
                }
                // 2018/01/24 S21_NA#22428 Mod End
            }
        }

        if (ZYPCommonFunc.hasValue(primaryLineCatgRmaCd)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                // 2018/01/24 S21_NA#22428 Mod Start
                //ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL, primaryLineCatgRmaCd);
                if(!ZYPCommonFunc.hasValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).dsOrdLineCatgCd_RL, primaryLineCatgRmaCd);
                }
                // 2018/01/24 S21_NA#22428 Mod End
            }
        }
    }

    /**
     * Derive Default Data
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     * @param slsDt Sales Date
     */
    public static void deriveDefaultData(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String slsDt) { // 2018/01/30 S21_NA#19808

        if (deriveDefaultCustAddl(bizMsg, glblMsg, slsDt)) {
            deriveDefaultPmtTerm(bizMsg);
        }

        // 2018/03/12 S21_NA#20297(Sol#379) Add Start
        if (!NWAL1500CommonLogic.deriveDefaultShippingInfo(bizMsg)) {
            return;
        }
        // 2018/03/12 S21_NA#20297(Sol#379) Add End

        if (!NWAL1500CommonLogic.deriveDefaultSlsRep(bizMsg)) {
            return;
        }

        deriveDefaultDtlBillToFromHdr(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808

        if (!NWAL1500CommonLogic.deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }

        NWAL1500CommonLogic.deriveDefaultPrcList(bizMsg, glblMsg); // 2018/01/30 S21_NA#19808

        // 2016/03/04 S21_NA#1679 Add Start
        setShpgSvcLvlPullDown(bizMsg);
        // 2016/03/04 S21_NA#1679 Add End

    }

    /**
     * Derive Default Customer And Additional Data
     * @param bizMsg NWAL1500CMsg
     * @param slsDt Sales Date
     * @return boolean If found default custmer address:true
     */
    @SuppressWarnings("unchecked")
    private static boolean deriveDefaultCustAddl(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String slsDt) { // 2018/01/30 S21_NA#19808

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getCustAddlInfo(bizMsg, slsDt);

        if (ssmResult.isCodeNormal()) {
            boolean rc = false;
            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            if (ZYPCommonFunc.hasValue(resultMap.get("LINE_BIZ_TP_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, resultMap.get("LINE_BIZ_TP_CD"));
            }
            if (ZYPCommonFunc.hasValue(resultMap.get("DEF_BILL_TO_CUST_ACCT_CD")) || ZYPCommonFunc.hasValue(resultMap.get("DEF_BILL_TO_CUST_CD"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, resultMap.get("DEF_BILL_TO_CUST_ACCT_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, resultMap.get("DEF_BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, resultMap.get("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, resultMap.get("BILL_TO_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.entBillToCustLocAddr, resultMap.get("BILL_TO_ADDR"));
                rc = true;
            }
            // 2018/03/12 S21_NA#20297(Sol#379) Del Start
//            // 2016/03/04 S21_NA#1679 Add Start
//            if (ZYPCommonFunc.hasValue(resultMap.get("FRT_COND_CD"))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, resultMap.get("FRT_COND_CD"));
//            }
//            // 2016/03/04 S21_NA#1679 Add End
//            if (ZYPCommonFunc.hasValue(resultMap.get("FRT_COND_DESC_TXT"))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, resultMap.get("FRT_COND_DESC_TXT"));
//            }
            // 2018/03/12 S21_NA#20297(Sol#379) Del End
            if (ZYPCommonFunc.hasValue(resultMap.get("CARR_SVC_LVL_DESC_TXT"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, resultMap.get("CARR_SVC_LVL_DESC_TXT"));
            }
            // 2018/03/12 S21_NA#20297(Sol#379) Del Start
//            if (ZYPCommonFunc.hasValue(resultMap.get("DEF_SHPG_SVC_LVL_CD"))) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultMap.get("DEF_SHPG_SVC_LVL_CD"));
//            }
            // 2018/03/12 S21_NA#20297(Sol#379) Del End

            String prcList = resultMap.get("PRC_CATG_NM");
            // 2018/01/30 S21_NA#19808 bizMsg.B, D => glblMsg.B, D
            if (ZYPCommonFunc.hasValue(prcList)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm, prcList);
                ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListNm, prcList);
                for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcCatgNm_LL, prcList);
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).flPrcListNm_LL, prcList);
                }
                for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).prcCatgNm_RL, prcList);
                    ZYPEZDItemValueSetter.setValue(glblMsg.D.no(i).flPrcListNm_RL, prcList);
                }
            }
            return rc;
        }

        return false;
    }

    /**
     * Derive Default Payment Term Data
     * @param bizMsg NWAL1500CMsg
     */
    public static void deriveDefaultPmtTerm(NWAL1500CMsg bizMsg) {

        bizMsg.pmtTermCashDiscDescTxt.clear();
        String pmtTermCashDiscCd = getPmtTermCashDiscCd(bizMsg);

        if (ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
            tMsg = (PMT_TERM_CASH_DISCTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscDescTxt, tMsg.pmtTermCashDiscDescTxt);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue())); // QC#17474 2017/02/21 Add
    }

    /**
     * Derive Default Sales Rep Data
     * @param bizMsg NWAL1500CMsg
     * @param glblMsg NWAL1500SMsg
     */
    public static void deriveDefaultSlsRep(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {

        String curLineBizTpCd = bizMsg.lineBizTpCd.getValue();
        String bkLineBizTpCd = glblMsg.lineBizTpCd.getValue();

        if (curLineBizTpCd.equals(bkLineBizTpCd) || !ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(glblMsg.lineBizTpCd, curLineBizTpCd);

        NWAL1500CommonLogic.deriveDefaultSlsRep(bizMsg);
    }

    /**
     * <pre>
     * set Shipping Service Level Pull Down 
     * @param bizMsg NWAL1500CMsg
     */
    public static void setShpgSvcLvlPullDown(NWAL1500CMsg bizMsg) {
        S21SsmEZDResult resltShpgSvcLvlRec = NWAL1500QueryForCategory.getInstance().getShpgSvcLvlDataList(bizMsg.glblCmpyCd.getValue(), bizMsg.lineBizTpCd.getValue(), bizMsg.frtCondCd.getValue());
        if (resltShpgSvcLvlRec.isCodeNormal()) {
            List<Map<String, Object>> shpgSvcLvlRecList = (List<Map<String, Object>>) resltShpgSvcLvlRec.getResultObject();

            bizMsg.shpgSvcLvlCd_CD.clear();
            bizMsg.shpgSvcLvlDescTxt_NM.clear();
            int validCnt = 0;
            for (Map<String, Object> shpgSvcLvlRec : shpgSvcLvlRecList) {
                bizMsg.shpgSvcLvlCd_CD.no(validCnt).setValue((String) shpgSvcLvlRec.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlDescTxt_NM.no(validCnt), (String) shpgSvcLvlRec.get("SHPG_SVC_LVL_DESC_TXT"));
                validCnt++;
            }
        }
    }

    /**
     * Derive Default Detail Bill To Location From Header
     * @param bizMsg NWAL1500CMsg
     */
    private static void deriveDefaultDtlBillToFromHdr(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) { // 2018/01/30 S21_NA#19808

        String billToCustCd = bizMsg.billToCustCd.getValue();
        String billToCustAcctCd = bizMsg.billToCustAcctCd.getValue(); // 2016/03/05 S21_NA#5000#85
        String billToCustAcctNm = bizMsg.billToCustAcctNm.getValue(); // 2016/03/05 S21_NA#5000#85
        String billToCustLocAddr = bizMsg.entBillToCustLocAddr.getValue(); // 2016/03/05 S21_NA#5000#85
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return;
        }

        // 2018/01/30 S21_NA#19808 bizMsg.A, C => glblMsg.A, C
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustCd_LC, billToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustAcctCd_LC, billToCustAcctCd); // 2016/03/05 S21_NA#5000#85
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustAcctNm_LC, billToCustAcctNm); // 2016/03/05 S21_NA#5000#85
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustLocAddr_LC, billToCustLocAddr); // 2016/03/05 S21_NA#5000#85
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustCd_RC, billToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustAcctCd_RC, billToCustAcctCd); // 2016/03/05 S21_NA#5000#85
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustAcctNm_RC, billToCustAcctNm); // 2016/03/05 S21_NA#5000#85
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustLocAddr_RC, billToCustLocAddr); // 2016/03/05 S21_NA#5000#85
        }
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL1500CMsg
     * @return Payment Term Cash Discount Code
     */
    private static String getPmtTermCashDiscCd(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForCategory.getInstance().getPmtTermCashDiscCd(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, bizMsg.billToCustAcctCd);
            tMsg = (DS_ACCT_CR_PRFLTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                return null;
            }
            return tMsg.pmtTermCashDiscCd.getValue();
        }

        return (String) ssmResult.getResultObject();
    }
}
