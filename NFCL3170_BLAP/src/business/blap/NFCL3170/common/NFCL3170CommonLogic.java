package business.blap.NFCL3170.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFCL3170.NFCL3170CMsg;
import business.blap.NFCL3170.NFCL3170Query;
import business.blap.NFCL3170.NFCL3170SMsg;
import business.blap.NFCL3170.constant.NFCL3170Constant;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.DS_BANK_ACCTTMsg;
import business.db.DS_BANK_BRTMsg;
import business.db.DS_CUST_BANK_ACCT_RELNTMsg;
import business.db.STTMsg;
import business.parts.NMZC003001PMsg;

import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;
/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 * 02/16/2016   Fujitsu         T.Tanaka        Update          Def#2601
 * 03/21/2016   Fujitsu         T.Tanaka        Update          Def#5743
 * 04/27/2016   Fujitsu         S.Fujita        Update          QC#5743
 * 05/27/2016   Fujitsu         S.Fujita        Update          QC#8534
 * 2016/12/16   Fujitsu         T.Murai         Update          QC#16533
 * 2018/01/12   Fujitsu         T.Murai         Update          QC#21290
 * 2023/02/08   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */

public class NFCL3170CommonLogic implements NFCL3170Constant {
    
    /**
     * 
     * @param pulldownCd
     * @param pulldownName
     * @param pullDownList
     * @param dbColumn
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
        }
    }

    private static void initPullDownCreate(EZDCBigDecimalItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((BigDecimal) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));
        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListDsBank(NFCL3170CMsg bizMsg) {

//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult dsBank = NFCL3170Query.getInstance().getDsBankPullDownList(bizMsg, ssmParam);
//
//        if (dsBank.isCodeNormal()) {
//            List<Map> dsBankList = (List<Map>) dsBank.getResultObject();
//            initPullDownCreate(bizMsg.dsBankCd_LC, bizMsg.dsBankNm_LD, dsBankList, new String[] {"DS_BANK_CD", "DS_BANK_NM" });
//        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListDsBankBr(NFCL3170CMsg bizMsg) {

//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("dsBankCd", bizMsg.dsBankCd_H1.getValue());
//        S21SsmEZDResult dsBankBr = NFCL3170Query.getInstance().getDsBankBrPullDownList(bizMsg, ssmParam);
//
//        if (dsBankBr.isCodeNormal()) {
//            List<Map> dsBankBrList = (List<Map>) dsBankBr.getResultObject();
//            initPullDownCreate(bizMsg.bankRteNum_LC, bizMsg.dsBankBrNm_LD, dsBankBrList, new String[] {"BANK_RTE_NUM", "DS_BANK_BR_NM" });
//        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListArRcptSrc(NFCL3170CMsg bizMsg) {

//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult arRcptSrc = NFCL3170Query.getInstance().getArRcptSrcPullDownList(bizMsg, ssmParam);
//        
//        if (arRcptSrc.isCodeNormal()) {
//            List<Map> arRcptSrcList = (List<Map>) arRcptSrc.getResultObject();
//            initPullDownCreate(bizMsg.arRcptSrcCd_LC, bizMsg.arRcptSrcNm_LD, arRcptSrcList, new String[] {"AR_RCPT_SRC_CD", "AR_RCPT_SRC_NM" });
//        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListArLockBox(NFCL3170CMsg bizMsg) {

//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult arLockBox = NFCL3170Query.getInstance().getArLockBoxPullDownList(bizMsg, ssmParam);
//
//        if (arLockBox.isCodeNormal()) {
//            List<Map> arLockBoxList = (List<Map>) arLockBox.getResultObject();
//            initPullDownCreate(bizMsg.arLockBoxCd_LC, bizMsg.arLockBoxNm_LD, arLockBoxList, new String[] {"AR_LOCK_BOX_CD", "AR_LOCK_BOX_NM" });
//        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListCcy(NFCL3170CMsg bizMsg) {

//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult ccy = NFCL3170Query.getInstance().getCcyPullDownList(bizMsg, ssmParam);
//
//        if (ccy.isCodeNormal()) {
//            List<Map> ccyList = (List<Map>) ccy.getResultObject();
//            initPullDownCreate(bizMsg.ccyCd_LC, bizMsg.ccyNm_LD, ccyList, new String[] {"CCY_CD", "CCY_NM" });
//        }
    }


    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListCtry(NFCL3170CMsg bizMsg) {

//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        S21SsmEZDResult ctry = NFCL3170Query.getInstance().getCtryPullDownList(bizMsg, ssmParam);
//
//        if (ctry.isCodeNormal()) {
//            List<Map> ctryList = (List<Map>) ctry.getResultObject();
//            initPullDownCreate(bizMsg.ctryCd_LC, bizMsg.ctryNm_LD, ctryList, new String[] {"CTRY_CD", "CTRY_NM" });
//        }
    }

    /**
     * 
     * @param glblCmpyCd
     * @param bizMsg
     */
    public static void createPulldownListSt(NFCL3170CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult st = NFCL3170Query.getInstance().getStPullDownList(bizMsg, ssmParam);

        if (st.isCodeNormal()) {
            List<Map> stList = (List<Map>) st.getResultObject();
            initPullDownCreate(bizMsg.stCd_LC, bizMsg.stNm_LD, stList, new String[] {"ST_CD", "ST_NM" });
        }
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean checkAccount(NFCL3170CMsg bizMsg) {
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(bizMsg.dsAcctNm_H1.getValue().equals(bizMsg.A.no(i).dsAcctNm_A1.getValue())) {
                if(bizMsg.locNum_H1.getValue().equals(bizMsg.A.no(i).locNum_A1.getValue())) {
                    bizMsg.setMessageInfo("NFCM0580E", new String[] {"Customer"});
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static BigDecimal getDsAcctCustPK(NFCL3170CMsg bizMsg, int idx) {

        if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).dsAcctNum_A1.getValue())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("dsAcctNum", bizMsg.A.no(idx).dsAcctNum_A1.getValue());
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult dsAcct = NFCL3170Query.getInstance().getDsAcctCustPK(null, ssmParam);
            if (dsAcct.isCodeNormal()) {
                return (BigDecimal) new BigDecimal(dsAcct.getResultObject().toString());
            } else {
                return null;
            }
        }

        return null;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static BigDecimal getCntyPK(NFCL3170CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.cntyNm_H1.getValue())) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("cntyNm", bizMsg.cntyNm_H1.getValue());
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult cnty = NFCL3170Query.getInstance().getCntyPK(null, ssmParam);
            if (cnty.isCodeNormal()) {
                return (BigDecimal) new BigDecimal(cnty.getResultObject().toString());
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean createDSBankBr(NFCL3170CMsg bizMsg) {
        
        BigDecimal cntyPk = new BigDecimal(0);
        if(ZYPCommonFunc.hasValue(bizMsg.cntyNm_H1.getValue())) {
            cntyPk =  (BigDecimal) NFCL3170CommonLogic.getCntyPK(bizMsg);
        }
        
        if(!NFCL3170CommonLogic.checkPostalCode(bizMsg)) {
            return false;
        }
        
        if(!NFCL3170CommonLogic.checkCounty(bizMsg)) {
            return false;
        }

        // START 2016/05/27 S.Fujita [QC#8534,ADD]
        if (isInputAddress(bizMsg)) { // ADD 2016/12/13 T.Murai [QC#16533]
        if(!NFCL3170CommonLogic.callAddressValidationAPI(bizMsg)) {
            return false;
        }
        } // ADD 2016/12/13 T.Murai [QC#16533]
        // END   2016/05/27 S.Fujita [QC#8534,ADD]

        DS_BANK_BRTMsg dsBankBrTMsg = new DS_BANK_BRTMsg();
        
//        BigDecimal dsBankBrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BANK_BR_SQ);
        BigDecimal dsBankBrPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_BANK_BR_SQ");

        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.dsBankBrPk, dsBankBrPk);
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.dsBankBrNm, bizMsg.dsBankBrNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.dsBankCd, DS_BANK_COMMON);
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.firstLineAddr, bizMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.scdLineAddr, bizMsg.scdLineAddr_H1.getValue());
        if(!cntyPk.equals(BigDecimal.ZERO)) {
            ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.cntyPk, cntyPk);
        }
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.ctyAddr, bizMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.stCd, bizMsg.stCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.postCd, bizMsg.postCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.ctryCd, bizMsg.ctryCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankBrTMsg.bankRteNum, bizMsg.bankRteNum_H1.getValue());

        EZDTBLAccessor.create(dsBankBrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsBankBrTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBankBrPk_H1, dsBankBrPk);

        return true;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean updateDSBankBr(NFCL3170CMsg bizMsg) {
        
        
//        BigDecimal cntyPk = new BigDecimal(0);
//        if(ZYPCommonFunc.hasValue(bizMsg.cntyNm_H1.getValue())) {
//            cntyPk =  (BigDecimal) NFCL3170CommonLogic.getCntyPK(bizMsg);
//        }
        
        if(!NFCL3170CommonLogic.checkPostalCode(bizMsg)) {
            return false;
        }
        
        if(!NFCL3170CommonLogic.checkState(bizMsg)) {
            return false;
        }
        
        if(!NFCL3170CommonLogic.checkCounty(bizMsg)) {
            return false;
        }

        // START 2016/05/27 S.Fujita [QC#8534,ADD]
        if (isInputAddress(bizMsg)) { // ADD 2016/12/13 T.Murai [QC#16533]
        if(!NFCL3170CommonLogic.callAddressValidationAPI(bizMsg)) {
            return false;
        }
        } // ADD 2016/12/13 T.Murai [QC#16533]
        // END   2016/05/27 S.Fujita [QC#8534,ADD]

        DS_BANK_BRTMsg inMsg = new DS_BANK_BRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsBankBrPk, bizMsg.dsBankBrPk_H1.getValue());

        DS_BANK_BRTMsg updMsg = (DS_BANK_BRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

        ZYPEZDItemValueSetter.setValue(updMsg.dsBankBrNm, bizMsg.dsBankBrNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg.dsBankCd, DS_BANK_COMMON);
        ZYPEZDItemValueSetter.setValue(updMsg.firstLineAddr, bizMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg.scdLineAddr, bizMsg.scdLineAddr_H1.getValue());
        // if(ZYPCommonFunc.hasValue(bizMsg.cntyPk_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(updMsg.cntyPk, bizMsg.cntyPk_H1.getValue());
        // }
        ZYPEZDItemValueSetter.setValue(updMsg.ctyAddr, bizMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg.stCd, bizMsg.stCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg.postCd, bizMsg.postCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg.ctryCd, bizMsg.ctryCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(updMsg.bankRteNum, bizMsg.bankRteNum_H1.getValue());

        EZDTBLAccessor.update(updMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }

        return true;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean createDSBankAcct(NFCL3170CMsg bizMsg) {
        
        DS_BANK_ACCTTMsg dsBankAcctTMsg = new DS_BANK_ACCTTMsg();

        BigDecimal dsBankAcctPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BANK_ACCT_SQ);

        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctPk, dsBankAcctPk);
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctNum, bizMsg.dsBankAcctNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctNm, bizMsg.dsBankAcctNm_H1.getValue());
        if (BigDecimal.ONE.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctTpCd, DS_BANK_ACCT_TP.EXTERNAL);
        } else {
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctTpCd, DS_BANK_ACCT_TP.INTERNAL);
        }
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankMicrNum, bizMsg.bankRteNum_H1.getValue().concat(bizMsg.dsBankAcctNum_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.effFromDt, bizMsg.effFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.effThruDt, bizMsg.effThruDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankCd, DS_BANK_COMMON);
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.bankRteNum, bizMsg.bankRteNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.ccyCd, bizMsg.ccyCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankBrPk, bizMsg.dsBankBrPk_H1.getValue());

        EZDTBLAccessor.create(dsBankAcctTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsBankAcctTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBankAcctPk_H1, dsBankAcctPk);
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean updateDSBankAcct(NFCL3170CMsg bizMsg) {
        
        DS_BANK_ACCTTMsg inMsg = new DS_BANK_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsBankAcctPk, bizMsg.dsBankAcctPk_H1.getValue());

        DS_BANK_ACCTTMsg dsBankAcctTMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctNum, bizMsg.dsBankAcctNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctNm, bizMsg.dsBankAcctNm_H1.getValue());
        if (BigDecimal.ONE.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctTpCd, DS_BANK_ACCT_TP.EXTERNAL);
        } else {
            ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankAcctTpCd, DS_BANK_ACCT_TP.INTERNAL);
        }
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankMicrNum, bizMsg.bankRteNum_H1.getValue().concat(bizMsg.dsBankAcctNum_H1.getValue()));
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.effFromDt, bizMsg.effFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.effThruDt, bizMsg.effThruDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.dsBankCd, DS_BANK_COMMON);
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.bankRteNum, bizMsg.bankRteNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.ccyCd, bizMsg.ccyCd_H1.getValue());

        EZDTBLAccessor.update(dsBankAcctTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsBankAcctTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }
        
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean createDsCustBankAcctReln(NFCL3170CMsg bizMsg, int idx) {

        BigDecimal dsAcctCustPk = (BigDecimal) NFCL3170CommonLogic.getDsAcctCustPK(bizMsg, idx);
        if(!ZYPCommonFunc.hasValue(dsAcctCustPk)) {
            return true;
        }
        
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).dsAcctCustPk_A1, dsAcctCustPk);

        DS_CUST_BANK_ACCT_RELNTMsg dsCustBankAcctTMsg = new DS_CUST_BANK_ACCT_RELNTMsg();

        BigDecimal dsCustBankAcctRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_BANK_ACCT_RELN_SQ);

        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.dsCustBankAcctRelnPk, dsCustBankAcctRelnPk);
        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.dsAcctCustPk, bizMsg.A.no(idx).dsAcctCustPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.dsBankAcctPk, bizMsg.dsBankAcctPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.effFromDt, bizMsg.effFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.effThruDt, bizMsg.effThruDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCustBankAcctTMsg.locNum, bizMsg.A.no(idx).locNum_A1.getValue());

        EZDTBLAccessor.create(dsCustBankAcctTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCustBankAcctTMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param no
     * @return
     */
    public static boolean updateDsCustBankAcctReln(NFCL3170CMsg bizMsg, int idx) {

        BigDecimal dsAcctCustPk = (BigDecimal) NFCL3170CommonLogic.getDsAcctCustPK(bizMsg, idx);
        if(!ZYPCommonFunc.hasValue(dsAcctCustPk)) {
            return true;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).dsAcctCustPk_A1, dsAcctCustPk);
        
        DS_CUST_BANK_ACCT_RELNTMsg inMsg = new DS_CUST_BANK_ACCT_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsCustBankAcctRelnPk, bizMsg.A.no(idx).dsCustBankAcctRelnPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsAcctCustPk, bizMsg.A.no(idx).dsAcctCustPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsBankAcctPk, bizMsg.dsBankAcctPk_H1.getValue());

        DS_CUST_BANK_ACCT_RELNTMsg outMsg = (DS_CUST_BANK_ACCT_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);

        ZYPEZDItemValueSetter.setValue(outMsg.dsAcctCustPk, bizMsg.A.no(idx).dsAcctCustPk_A1.getValue());
        ZYPEZDItemValueSetter.setValue(outMsg.effFromDt, bizMsg.effFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(outMsg.effThruDt, bizMsg.effThruDt_H1.getValue());

        EZDTBLAccessor.update(outMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }
        return true;
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    public static boolean deleteDsCustBankAcctReln(NFCL3170CMsg bizMsg, int idx) {

        DS_CUST_BANK_ACCT_RELNTMsg inMsg = new DS_CUST_BANK_ACCT_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.dsCustBankAcctRelnPk, bizMsg.A.no(idx).dsCustBankAcctRelnPk_A1.getValue());

        DS_CUST_BANK_ACCT_RELNTMsg outMsg = (DS_CUST_BANK_ACCT_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        
        EZDTBLAccessor.logicalRemove(outMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
            bizMsg.setMessageInfo("NFCM0041E");
            return false;
        }
        return true;
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    public static boolean deleteDsCustBankAcctReln2(NFCL3170CMsg bizMsg, NFCL3170SMsg globalMsg) {

        for(int i = 0; i < globalMsg.B.getValidCount(); i++) {
            DS_CUST_BANK_ACCT_RELNTMsg inMsg = new DS_CUST_BANK_ACCT_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(inMsg.dsCustBankAcctRelnPk, globalMsg.B.no(i).dsCustBankAcctRelnPk_B1.getValue());

            DS_CUST_BANK_ACCT_RELNTMsg outMsg = (DS_CUST_BANK_ACCT_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            
            EZDTBLAccessor.logicalRemove(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                bizMsg.setMessageInfo("NFCM0041E");
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @return
     */
//    public static boolean deleteDsCustBankAcctReln2(NFCL3170CMsg bizMsg) {
//        
//        for(int i = 0; i < bizMsg.B.getValidCount(); i++) {
//            DS_CUST_BANK_ACCT_RELNTMsg inMsg = new DS_CUST_BANK_ACCT_RELNTMsg();
//            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
//            ZYPEZDItemValueSetter.setValue(inMsg.dsCustBankAcctRelnPk, bizMsg.B.no(i).dsCustBankAcctRelnPk_B1.getValue());
//
//            DS_CUST_BANK_ACCT_RELNTMsg outMsg = (DS_CUST_BANK_ACCT_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
//            
//            EZDTBLAccessor.logicalRemove(outMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
//                bizMsg.setMessageInfo("NFCM0041E");
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean doSerch(NFCL3170CMsg bizMsg) {

        if(!ZYPCommonFunc.hasValue(bizMsg.dsBankAcctPk_H1.getValue())) {
            NFCL3170CommonLogic.clearNew(bizMsg);
            return true;
        }
        

        NFCL3170CommonLogic.searchDsBankInfo(bizMsg);

        // DS_CUST_BANK_ACCT_RELN
        if(!searchDsBankBankAcctReln(bizMsg)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     */
    public static boolean searchDsBankBankAcctReln(NFCL3170CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsBankAcctPk", bizMsg.dsBankAcctPk_H1.getValue());
        S21SsmEZDResult ssmResult = NFCL3170Query.getInstance().searchDsCustBankAcctReln(bizMsg, ssmParam);
        
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                return true;
            }
            else if (queryResCnt > 1) {
////                bizMsg.setMessageInfo("NZZM0001W");
////            } else {
                bizMsg.setMessageInfo("NZZM0002I");
            }
        } else {
//                bizMsg.setMessageInfo("NZZM0000E");
        }
        return true;
    }
    
    public static boolean callAddressValidationAPI(NFCL3170CMsg bizMsg) {
        boolean isSuccess = true;
        
        NMZC003001PMsg pMsg = new NMZC003001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, bizMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, bizMsg.scdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, bizMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, bizMsg.cntyNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, bizMsg.stCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, bizMsg.ctryCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, bizMsg.postCd_H1.getValue());
        
        NMZC003001 api = new NMZC003001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        // START 2016/05/27 S.Fujita [QC#8534,MOD]
/*
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            bizMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }
        if(pMsg.xxVldStsCd_01.getValue().equals(ADDR_ERROR)) {
            bizMsg.firstLineAddr_H1.setErrorInfo(1, "NFCM0029E");
            isNotError = false;
        }
        if(pMsg.xxVldStsCd_02.getValue().equals(ADDR_ERROR)) {
            bizMsg.scdLineAddr_H1.setErrorInfo(1, "NFCM0029E");
            isNotError = false;
        }
        if(pMsg.xxVldStsCd_03.getValue().equals(ADDR_ERROR)) {
            bizMsg.ctyAddr_H1.setErrorInfo(1, "NFCM0029E");
            isNotError = false;
        }
        if(pMsg.xxVldStsCd_04.getValue().equals(ADDR_ERROR)) {
            bizMsg.stCd_H1.setErrorInfo(1, "NFCM0029E");
            isNotError = false;
        }
        if(pMsg.xxVldStsCd_05.getValue().equals(ADDR_ERROR)) {
            bizMsg.postCd_H1.setErrorInfo(1, "NFCM0029E");
            isNotError = false;
        }
        if(pMsg.xxVldStsCd_07.getValue().equals(ADDR_ERROR)) {
            bizMsg.cntyNm_H1.setErrorInfo(1, "NFCM0029E");
            isNotError = false;
        }
*/
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();

                boolean hasItemError = false;
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_01.getValue())) {
                    bizMsg.firstLineAddr_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_02.getValue())) {
                    bizMsg.scdLineAddr_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_03.getValue())) {
                    bizMsg.ctyAddr_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_04.getValue())) {
                    bizMsg.stCd_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_05.getValue())) {
                    bizMsg.postCd_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_06.getValue())) {
                    bizMsg.ctryCd_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_07.getValue())) {
                    bizMsg.cntyNm_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (!hasItemError) {
                    bizMsg.setMessageInfo(msgId, msgPrms);
                }

                return false;
            }
        }
        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_01.getValue())) {
            bizMsg.firstLineAddr_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_01.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H1, pMsg.firstLineAddr);
            bizMsg.firstLineAddr_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_02.getValue())) {
            bizMsg.scdLineAddr_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H1, pMsg.scdLineAddr);
            bizMsg.scdLineAddr_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_03.getValue())) {
            bizMsg.ctyAddr_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_03.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_H1, pMsg.ctyAddr);
            bizMsg.ctyAddr_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_04.getValue())) {
            bizMsg.stCd_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_04.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.stCd_H1, pMsg.stCd);
            bizMsg.stCd_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_05.getValue())) {
            bizMsg.postCd_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd_H1, pMsg.postCd);
            bizMsg.postCd_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_06.getValue())) {
            bizMsg.ctryCd_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_06.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H1, pMsg.ctryCd);
            bizMsg.ctryCd_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        if (RTRN_CD_ERROR.equals(pMsg.xxVldStsCd_07.getValue())) {
            bizMsg.cntyNm_H1.setErrorInfo(1, NMAM8359E);
            isSuccess = false;
        } else if (RTRN_CD_SUGGEST.equals(pMsg.xxVldStsCd_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cntyNm_H1, pMsg.cntyNm);
            bizMsg.cntyNm_H1.setErrorInfo(2, NMAM8360W);
            bizMsg.setMessageInfo(NMAM8360W);
            isSuccess = false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.cntyPk_H1, pMsg.cntyPk);
        // END   2016/05/27 S.Fujita [QC#8534,MOD]
        return isSuccess;
    }

    /**
     * 
     * @param bizMsg
     */
    public static void clearNew(NFCL3170CMsg bizMsg) {

        bizMsg.dsBankAcctNm_H1.clear();
        bizMsg.dsBankBrNm_H1.clear();
        bizMsg.bankRteNum_H1.clear();
        bizMsg.dsBankAcctNum_H1.clear();

        bizMsg.dsBankMicrNum_H1.clear();
        

        bizMsg.xxRadioBtn.setValue(BigDecimal.ONE);
        bizMsg.firstLineAddr_H1.clear();
        bizMsg.scdLineAddr_H1.clear();
        bizMsg.ctyAddr_H1.clear();
        bizMsg.cntyNm_H1.clear();
        bizMsg.cntyPk_H1.clear();
        bizMsg.stCd_H1.clear();
        bizMsg.postCd_H1.clear();

        bizMsg.dsAcctNum_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.locNum_H1.clear();
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
        
        bizMsg.dsAcctCustPk_H1.clear();
        bizMsg.dsBankBrPk_H1.clear();
        bizMsg.dsBankAcctPk_H1.clear();
        bizMsg.dsCustBankAcctRelnPk_H1.clear();
        
        bizMsg.effFromDt_H1.setValue(bizMsg.procDt.getValue());
        bizMsg.effFromDt_BK.setValue(bizMsg.procDt.getValue());
        
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

    }

    /**
     * 
     * @param bizMsg
     */
    public static void clearBank(NFCL3170CMsg bizMsg) {

        bizMsg.bankRteNum_H1.clear();
    }

    /**
     * 
     * @param bizMsg
     */
    public static void clearBankBr(NFCL3170CMsg bizMsg) {
        bizMsg.dsBankBrNm_H1.clear();
        bizMsg.firstLineAddr_H1.clear();
        bizMsg.scdLineAddr_H1.clear();
        bizMsg.ctyAddr_H1.clear();
        bizMsg.cntyNm_H1.clear();
        bizMsg.cntyPk_H1.clear();
        bizMsg.stCd_H1.clear();
        bizMsg.postCd_H1.clear();
    }
    
    /**
     * 
     * @param bizMsg
     */
    public static void clearBankAcct(NFCL3170CMsg bizMsg) {
        bizMsg.dsBankAcctNum_H1.clear();
        bizMsg.dsBankAcctNm_H1.clear();
        bizMsg.dsBankMicrNum_H1.clear();
        bizMsg.xxRadioBtn.setValue(BigDecimal.ONE);
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
    }

    /**
     * 
     * @param bizMsg
     */
    public static void clearCustomer(NFCL3170CMsg bizMsg) {
        bizMsg.dsAcctNum_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.locNum_H1.clear();
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean getDsBankAcct(NFCL3170CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsBankAcctNum", bizMsg.dsBankAcctNum_H1.getValue());
        ssmParam.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());

        S21SsmEZDResult bankAcct = NFCL3170Query.getInstance().getDsBankAcct(bizMsg, ssmParam);
        if (bankAcct.isCodeNormal()) {
            if(bankAcct.getQueryResultCount()>0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean checkDsBank(NFCL3170CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsBankAcctNm", bizMsg.dsBankAcctNm_H1.getValue());
        // START 2016/04/27 S.Fujita [QC#5743,MOD]
        ssmParam.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
        ssmParam.put("dsBankAcctNum", bizMsg.dsBankAcctNum_H1.getValue());
        // END 2016/04/27 S.Fujita [QC#5743,MOD]
        S21SsmEZDResult bankAcct3 = NFCL3170Query.getInstance().checkDsBankAcct(bizMsg, ssmParam);
        if (bankAcct3.isCodeNormal()) {
            if(bankAcct3.getQueryResultCount()>0) {
                // START 2016/04/27 S.Fujita [QC#5743,MOD]
//                bizMsg.dsBankAcctNm_H1.setErrorInfo(1, "NFCM0580E", new String[] {"Account Name"});
                bizMsg.setMessageInfo("NFCM0835E");
                // END 2016/04/27 S.Fujita [QC#5743,MOD]
                return false;
            }
        }

        // START 2016/04/27 S.Fujita [QC#5743,MOD]
//        ssmParam.clear();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
//        S21SsmEZDResult bankAcct2 = NFCL3170Query.getInstance().checkDsBankAcct(bizMsg, ssmParam);
//        if (bankAcct2.isCodeNormal()) {
//            if(bankAcct2.getQueryResultCount()>0) {
//                bizMsg.bankRteNum_H1.setErrorInfo(1, "NFCM0580E", new String[] {"Routing Number"});
//                return false;
//            }
//        }
//
//        ssmParam.clear();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
//        S21SsmEZDResult bankBr = NFCL3170Query.getInstance().checkDsBankBr(bizMsg, ssmParam);
//        if (bankBr.isCodeNormal()) {
//            if(bankBr.getQueryResultCount()>0) {
//                bizMsg.bankRteNum_H1.setErrorInfo(1, "NFCM0580E", new String[] {"Routing Number"});
//                return false;
//            }
//        }
//
//        ssmParam.clear();
//        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
//        ssmParam.put("dsBankAcctNum", bizMsg.dsBankAcctNum_H1.getValue());
//        S21SsmEZDResult bankAcct1 = NFCL3170Query.getInstance().checkDsBankAcct(bizMsg, ssmParam);
//        if (bankAcct1.isCodeNormal()) {
//            if(bankAcct1.getQueryResultCount()>0) {
//                bizMsg.dsBankAcctNum_H1.setErrorInfo(1, "NFCM0580E", new String[] {"Account Number"});
//                return false;
//            }
//        }
        // END 2016/04/27 S.Fujita [QC#5743,MOD]
        return true;
    }

    // START 2023/02/08 R.Takau [QC#55645,ADD]
    /**
     * 
     * @param bizMsg
     * @return
     */
    public static Map<String, Object>  getAccountInfo(NFCL3170CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("billToCustCd", bizMsg.billToCustCd.getValue());

        Map<String, Object> bankAcct = NFCL3170Query.getInstance().getAccountInfo(bizMsg, ssmParam);

        return bankAcct;
    }
    //END 2023/02/08 R.Takau [QC#55645,ADD]
    
    // START 2018/01/12 [QC#21290,ADD]
    /**
     * @param bizMsg NFCL3170CMsg
     * @return boolean input :true
     */
    public static boolean checkCustReln(NFCL3170CMsg bizMsg) {
        boolean success = true;
        String firstRelnAcctNum = null;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(firstRelnAcctNum)) {
                firstRelnAcctNum = bizMsg.A.no(i).dsAcctNum_A1.getValue();
                continue;
            }
            if (!S21StringUtil.isEquals(firstRelnAcctNum, bizMsg.A.no(i).dsAcctNum_A1.getValue())) {
                success = false;
            }
        }

        if (!success) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NFCL3170Constant.NFCM0879E);
            }
        }
        return success;
    }
    // END   2018/01/12 [QC#21290,ADD]
    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean searchDsBankInfo(NFCL3170CMsg bizMsg) {
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsBankAcctPk", bizMsg.dsBankAcctPk_H1.getValue());

        S21SsmEZDResult bankInfo = NFCL3170Query.getInstance().searchDsBankInfo(bizMsg, ssmParam);
        if (bankInfo.isCodeNormal()) {
            if(bankInfo.getQueryResultCount()>0) {
                return true;
            }
        }
        
        return true;
    }
    
    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean checkPostalCode(NFCL3170CMsg bizMsg) {
        
        if(!ZYPCommonFunc.hasValue(bizMsg.postCd_H1.getValue())) {
            return true;
        }

        if(bizMsg.postCd_H1.getValue().length() < 5) {
            bizMsg.postCd_H1.setErrorInfo(1, "NFCM0029E");
            return false;
        }
        if(bizMsg.postCd_H1.getValue().length() > 5) {
            if(!bizMsg.postCd_H1.getValue().substring(5, 6).equals("-")) {
                bizMsg.postCd_H1.setErrorInfo(1, "NFCM0029E");
                return false;
            }
            if(bizMsg.postCd_H1.getValue().length() != 10) {
                bizMsg.postCd_H1.setErrorInfo(1, "NFCM0029E");
                return false;
            }
            String number = bizMsg.postCd_H1.getValue().substring(0,5);
            if(!NFCL3170CommonLogic.checkNumber(number)) {
                bizMsg.postCd_H1.setErrorInfo(1, "NFCM0029E");
                return false;
            }
            number = bizMsg.postCd_H1.getValue().substring(6,10);
            if(!NFCL3170CommonLogic.checkNumber(number)) {
                bizMsg.postCd_H1.setErrorInfo(1, "NFCM0029E");
                return false;
            }
        }
        
        return true;
    }

    /**
     * 
     * @param number
     * @return
     */
    public static boolean checkNumber(String number) {

        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    
    /**
     * 
     * @param bizMsg
     * @return
     */
    public static boolean checkState(NFCL3170CMsg bizMsg) {

        if(!ZYPCommonFunc.hasValue(bizMsg.stCd_H1.getValue())) {
            return true;
        }
        
        STTMsg inMsg = new STTMsg();
        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.stCd.setValue(bizMsg.stCd_H1.getValue());
        
        STTMsg outMsg = (STTMsg) EZDTBLAccessor.findByKey(inMsg);
        
        if(!outMsg.getReturnCode().equals(EZDTBLAccessor.RTNCD_NORMAL)) {
            bizMsg.stCd_H1.setErrorInfo(1, "NMAM8121E", new String[]{bizMsg.stCd_H1.getValue()});
            return false;
        }
        return true;
    }

    public static boolean checkCounty(NFCL3170CMsg bizMsg) {
        if(!ZYPCommonFunc.hasValue(bizMsg.cntyNm_H1.getValue())) {
            bizMsg.cntyPk_H1.clear(); // ADD 2016/12/13 [QC#16533]
            return true;
        }

        CNTYTMsg inMsg = new CNTYTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("cntyNm01", bizMsg.cntyNm_H1.getValue());
        inMsg.setConditionValue("stCd01", bizMsg.stCd_H1.getValue());
        CNTYTMsgArray outMsg = (CNTYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg != null && outMsg.length() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cntyPk_H1, outMsg.no(0).cntyPk.getValue());
        } else {
            bizMsg.cntyNm_H1.setErrorInfo(1, "NFCM0150E");
            return false;
        }
        return true;
    }
    

    /**
     * 
     * @param bizMsg
     * @param globalMsg
     * @return
     */
    public static boolean deleteCustomer(NFCL3170CMsg bizMsg, NFCL3170SMsg globalMsg) {
        
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);

        int idx_A = 0;
        int idx_B = globalMsg.B.getValidCount();
        for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxChkBox_A1.getValue())) {
                if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).dsCustBankAcctRelnPk_A1.getValue())) {
                    globalMsg.B.no(idx_B).dsCustBankAcctRelnPk_B1.setValue(bizMsg.A.no(i).dsCustBankAcctRelnPk_A1.getValue());
                    idx_B++;
                }
            } else {
                EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(idx_A), null);
                idx_A++;
            }
        }
        globalMsg.A.setValidCount(idx_A);
        globalMsg.B.setValidCount(idx_B);
        
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        for( int i = 0; i < globalMsg.A.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(globalMsg.A.getValidCount());
        return true;
    }

    // START 2016/12/13 T.Murai [QC#16533,ADD]
    /**
     * @param bizMsg NFCL3170CMsg
     * @return boolean input :true
     */
    public static boolean isInputAddress(NFCL3170CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.firstLineAddr_H1)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.scdLineAddr_H1)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctyAddr_H1)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.cntyNm_H1)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.stCd_H1)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bizMsg.postCd_H1)) {
            return true;
        }
        return false;
    }
    // END   2016/12/13 T.Murai [QC#16533,ADD]
}
