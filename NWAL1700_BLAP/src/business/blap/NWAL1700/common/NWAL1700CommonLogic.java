/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1700.common;

import static business.blap.NWAL1700.constant.NWAL1700Constant.BIZ_ID;
import static business.blap.NWAL1700.constant.NWAL1700Constant.DB_FLAG_INSERT;
import static business.blap.NWAL1700.constant.NWAL1700Constant.FUNCTION_FULL;
import static business.blap.NWAL1700.constant.NWAL1700Constant.MODE_CREATE;
import static business.blap.NWAL1700.constant.NWAL1700Constant.NWAM0008E;
import static business.blap.NWAL1700.constant.NWAL1700Constant.GLOBAL_CMPY_CODE01;
import static business.blap.NWAL1700.constant.NWAL1700Constant.BILL_TO_CUST01;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1700.NWAL1700CMsg;
import business.blap.NWAL1700.NWAL1700Query;
import business.blap.NWAL1700.NWAL1700SMsg;
import business.db.AJE_ACCT_BATTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CARR_SVC_LVLTMsg;
import business.db.DS_INV_TPTMsg;
import business.db.PRC_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NWAL1700CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/04   Fujitsu         M.Suzuki        Update          QS21_NA#5919
 * 2016/04/06   Fujitsu         M.Suzuki        Update          QS21_NA#6353
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QS21_NA#12143
 * 2016/08/10   Fujitsu         M.Yamada        Update          QC#11418
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 *</pre>
 */
public class NWAL1700CommonLogic {

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumnCd String
     * @param dbColumnValue String
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String dbColumnCd, String dbColumnValue) {

        for (int i = 0; i < pullDownList.size(); i++) {

            // 2016/04/04 S21_NA#5919 MOD Start --------------
            if (i >= cd.length()) {
                break;
            }
            // 2016/04/04 S21_NA#5919 MOD Start --------------
            Map pullDownData = pullDownList.get(i);
            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumnCd));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumnValue));
        }
    }

    /**
     * updateGlblMsg
     * @param bizMsg NWAL1700CMsg
     * @param glblMsg NWAL1700SMsg
     */
    public static void updateGlblMsg(NWAL1700CMsg bizMsg, NWAL1700SMsg glblMsg) {
        EZDMsg.copy(bizMsg.A, null, glblMsg.A, null);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isFullUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNCTION_FULL);
    }

    /**
     * <pre>
     * Add new line to detail section
     * @param bizMsg Business Message
     * </pre>
     */
    public static void createNewDetailLine(NWAL1700CMsg bizMsg) {
        int nextIdx = bizMsg.A.getValidCount();
        final String slsDt = ZYPDateUtil.getSalesDate();

        S21SsmEZDResult maxResult = NWAL1700Query.getInstance().getMaxLineSortNum(bizMsg);
        Map<String, Object> maxvalue = (Map<String, Object>) maxResult.getResultObject();
        BigDecimal maxSortNum = (BigDecimal) maxvalue.get("MAX_SORT_NUM");

        int max = maxSortNum.intValue() + 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).lineProcDfnSortNum_A.getValue())) {
                int sortNum = bizMsg.A.no(i).lineProcDfnSortNum_A.getValueInt();
                if (max <= sortNum) {
                    max = sortNum + 1;
                }
            }
        }
        bizMsg.A.setValidCount(nextIdx + 1);
        bizMsg.A.no(nextIdx).clear();
        bizMsg.A.no(nextIdx).lineProcDfnSortNum_A.setValue(max);
        bizMsg.A.no(nextIdx).xxRowId_A.setValue(DB_FLAG_INSERT);
        bizMsg.A.no(nextIdx).effFromDt_A.setValue(slsDt);
    }

    /**
     * <pre>
     * Clear Biz Message
     * @param bizMsg Business Message
     * </pre>
     */
    public static void clearMsg(NWAL1700CMsg bizMsg) {
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(bizMsg.F);

        bizMsg.xxModeCd.setValue(MODE_CREATE);
    }

    /**
     * <pre>
     * Set up Biz Message with Initial Data
     * @param bizMsg Business Message
     * </pre>
     */
    public static void setInitScreenValue(NWAL1700CMsg bizMsg) {
        bizMsg.vldApvlNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.diChkNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.prftApvlNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.crApvlNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.assetNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.outOfWhNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.splyAbuseNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        // add start 2023/04/25 QC#61337
        bizMsg.manPrcNodeUsgFlg.setValue(ZYPConstant.FLG_OFF_N);
        // add end 2023/04/25 QC#61337
        bizMsg.effFromDt.setValue(ZYPDateUtil.getSalesDate());
    }

    /**
     * Search AJE
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @param selectIndex int
     * @return boolean
     */
    public static boolean searchAJE(NWAL1700CMsg bizMsg, String glblCmpyCd, int selectIndex) {

        AJE_ACCT_BATTMsg ajeAcctBatTMsg = new AJE_ACCT_BATTMsg();
        ajeAcctBatTMsg.glblCmpyCd.setValue(glblCmpyCd);
        ajeAcctBatTMsg.ajeAcctBatCd.setValue(bizMsg.A.no(selectIndex).ajeAcctBatCd_A.getValue());
        // 2016/04/04 S21_NA#5919 MOD Start --------------
        // AJE_ACCT_BATTMsg outMsg = (AJE_ACCT_BATTMsg)
        // EZDTBLAccessor.findByKey(ajeAcctBatTMsg);
        AJE_ACCT_BATTMsg outMsg = (AJE_ACCT_BATTMsg) S21CodeTableAccessor.findByKey(ajeAcctBatTMsg);
        // 2016/04/04 S21_NA#5919 MOD End --------------

        if (outMsg != null) {
            // 2016/04/04 S21_NA#6353 Mod Start ----------
            bizMsg.A.no(selectIndex).ajeAcctBatDescTxt_A.setValue(outMsg.ajeAcctBatDescTxt.getValue());
            // 2016/04/04 S21_NA#6353 Mod End   ----------
            return true;
        } else {
            bizMsg.A.no(selectIndex).ajeAcctBatDescTxt_A.clear();
            bizMsg.A.no(selectIndex).ajeAcctBatCd_A.setErrorInfo(1, NWAM0008E);
            return false;
        }
    }

    /**
     * Search ARTran
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchARTran(NWAL1700CMsg bizMsg, String glblCmpyCd) {

        DS_INV_TPTMsg inDsInvTpTMsg = new DS_INV_TPTMsg();
        inDsInvTpTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inDsInvTpTMsg.dsInvTpCd.setValue(bizMsg.dsInvTpCd.getValue());
        // 2016/04/04 S21_NA#5919 MOD Start ------------
        // DS_INV_TPTMsg outMsg = (DS_INV_TPTMsg)
        // EZDTBLAccessor.findByKey(inDsInvTpTMsg);
        DS_INV_TPTMsg outMsg = (DS_INV_TPTMsg) S21CodeTableAccessor.findByKey(inDsInvTpTMsg);
        // 2016/04/04 S21_NA#5919 MOD End --------------

        // 2016/04/04 S21_NA#6353 MOD Start ------------
        if (outMsg != null) {
            // Mod Start 2016/08/04 QC#12143
//            bizMsg.dsInvTpDescTxt.setValue(outMsg.dsInvTpDescTxt.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTpNm, outMsg.dsInvTpNm.getValue());
            // Mod End 2016/08/04 QC#12143
            return true;
        } else {
            // Mod Start 2016/08/04 QC#12143
//            bizMsg.dsInvTpDescTxt.clear();
            bizMsg.dsInvTpNm.clear();
            // Mod End 2016/08/04 QC#12143
            bizMsg.dsInvTpCd.setErrorInfo(1, NWAM0008E);
            return false;
        }
        // 2016/04/04 S21_NA#6353 MOD END ------------
    }

    /**
     * Search BillToAccount
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchBillToAccount(NWAL1700CMsg bizMsg, String glblCmpyCd) {

        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String dsAcctNm = NWAL1700Query.getInstance().getBillToAccountNm(bizMsg.defBillToCustAcctCd.getValue(), salesDate);
        if (ZYPCommonFunc.hasValue(dsAcctNm)) {
            bizMsg.dsAcctNm.setValue(dsAcctNm);
            return true;
        } else {
            bizMsg.dsAcctNm.clear();
            bizMsg.defBillToCustAcctCd.setErrorInfo(1, NWAM0008E);
            return false;
        }
    }

    /**
     * Search BillToLocation
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchBillToLocation(NWAL1700CMsg bizMsg, String glblCmpyCd) {
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setConditionValue(GLOBAL_CMPY_CODE01, glblCmpyCd);
        billToCustTMsg.setConditionValue(BILL_TO_CUST01, bizMsg.defBillToCustCd.getValue());
        billToCustTMsg.setSQLID("003");
        BILL_TO_CUSTTMsgArray billToCustList = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustTMsg);

        if (billToCustList.length() > 0) {
            billToCustTMsg = (BILL_TO_CUSTTMsg) billToCustList.get(0);
            bizMsg.locDescTxt.setValue(billToCustTMsg.locNm.getValue());
            return true;
        } else {
            bizMsg.locDescTxt.clear();
            bizMsg.defBillToCustCd.setErrorInfo(1, NWAM0008E);
            return false;
        }
    }

    /**
     * Search Price
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchPriceList(NWAL1700CMsg bizMsg, String glblCmpyCd) {
        PRC_CATGTMsg prcCatgTMsg = new PRC_CATGTMsg();
        prcCatgTMsg.glblCmpyCd.setValue(glblCmpyCd);
        prcCatgTMsg.prcCatgCd.setValue(bizMsg.defPrcCatgCd.getValue());
        PRC_CATGTMsg outMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(prcCatgTMsg);

        if (outMsg != null) {
            bizMsg.prcCatgDescTxt_PR.setValue(outMsg.prcCatgDescTxt.getValue());
            return true;
        } else {
            bizMsg.prcCatgDescTxt_PR.clear();
            bizMsg.defPrcCatgCd.setErrorInfo(1, NWAM0008E);
            return false;
        }
    }

    /**
     * Search ServicePrice
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchServicePriceList(NWAL1700CMsg bizMsg, String glblCmpyCd) {
        PRC_CATGTMsg inPrcCatgTMsg = new PRC_CATGTMsg();
        inPrcCatgTMsg.glblCmpyCd.setValue(glblCmpyCd);
        inPrcCatgTMsg.prcCatgCd.setValue(bizMsg.defMaintPrcCatgCd.getValue());

        PRC_CATGTMsg outMsg = (PRC_CATGTMsg) EZDTBLAccessor.findByKey(inPrcCatgTMsg);

        if (outMsg != null) {
            bizMsg.prcCatgDescTxt_SP.setValue(outMsg.prcCatgDescTxt.getValue());
            return true;
        } else {
            bizMsg.prcCatgDescTxt_SP.clear();
            bizMsg.defMaintPrcCatgCd.setErrorInfo(1, NWAM0008E);
            return false;
        }
    }

    /**
     * Search Carrier
     * @param bizMsg Business Msg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchCarrier(NWAL1700CMsg bizMsg, String glblCmpyCd) {
        CARR_SVC_LVLTMsg carrSvcLvlTMsg = new CARR_SVC_LVLTMsg();
        carrSvcLvlTMsg.glblCmpyCd.setValue(glblCmpyCd);
        carrSvcLvlTMsg.carrSvcLvlCd.setValue(bizMsg.defCarrSvcLvlCd.getValue());
        CARR_SVC_LVLTMsg outMsg = (CARR_SVC_LVLTMsg) EZDTBLAccessor.findByKey(carrSvcLvlTMsg);

        if (outMsg != null) {
            // 2016/04/04 S21_NA#6353 Add Start ------------
            bizMsg.carrSvcLvlDescTxt.setValue(outMsg.carrSvcLvlDescTxt.getValue());
            // 2016/04/04 S21_NA#6353 Add Start ------------
            return true;
        } else {
            bizMsg.carrSvcLvlDescTxt.clear();
            bizMsg.defCarrSvcLvlCd.setErrorInfo(1, NWAM0008E);
            return false;
        }
    }
}
