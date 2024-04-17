/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0640;

import static business.blap.NSAL0640.constant.NSAL0640Constant.DATE_FORMAT;
import static business.blap.NSAL0640.constant.NSAL0640Constant.DEF_EFF_THRU_DT;
import static business.blap.NSAL0640.constant.NSAL0640Constant.NSAM0032E;
import static business.blap.NSAL0640.constant.NSAL0640Constant.SVC_MEMO;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0640.common.NSAL0640CommonLogic;
import business.db.DS_CONTRTMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_FUNC_ASGTMsgArray;
import business.db.S21_PSNTMsg;
import business.db.S21_PSNTMsgArray;
import business.db.SVC_BR_RESRC_RELNTMsg;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/01   Hitachi         M.Gotou         Update          QC#4916
 * 2016/10/11   Hitachi         T.Mizuki        Update          QC#14606
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2018/05/08   Hitachi         U.Kim           Update          QC#25852
 * 2019/02/12   Hitachi         S.Kitamura      Update          QC#30264
 *</pre>
 */
public final class NSAL0640Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0640Query INSTANCE = new NSAL0640Query();

    /**
     * Constructor.
     */
    private NSAL0640Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0640Query
     */
    public static NSAL0640Query getInstance() {
        return INSTANCE;
    }

    // mod start 2016/12/08 CSA QC#4210
    /**
     * get Contract Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSAL0640_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(Map<String, Object> ssmParam, NSAL0640_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getContrInfo", ssmParam, aSMsgArray);
    }
    // mod end 2016/12/08 CSA QC#4210
    /**
     * get Contract By Contract Primary Key
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    /**
     * Find Branch Name Info By Service Contract Branch Code
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @return SVC_CONTR_BRTMsg
     */
    public SVC_CONTR_BRTMsg findBrNmInfo(String glblCmpyCd, String svcContrBrCd) {
        SVC_CONTR_BRTMsg prmTMsg = new SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcContrBrCd, svcContrBrCd);
        return (SVC_CONTR_BRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    // add start 2016/04/01 Defect#4916
    /**
     * Find Branch Name Info By Service Contract Branch Code
     * @param glblCmpyCd String
     * @param lob String
     * @param branchName String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findBranchNameInfo(String glblCmpyCd, String lob, String branchName) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("lob", lob);
        ssmPrm.put("branchName", branchName);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        return getSsmEZDClient().queryObjectList("findBranchNameInfo", ssmPrm);
    }
    // add end 2016/04/01 Defect#4916

    // mod start 2016/03/28 CSA Defect#4702,4703,4915
//    /**
//     * Find Representative Info By TOC Code
//     * @param glblCmpyCd String
//     * @param tocCd String
//     * @return TOCTMsg
//     */
//    public TOCTMsg findRepInfo(String glblCmpyCd, String tocCd) {
//        TOCTMsg prmTMsg = new TOCTMsg();
//        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(prmTMsg.tocCd, tocCd);
//        return (TOCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
//    }

    // mod start 2016/04/04 CSA Defect#4916
    // mod start 2016/10/11 CSA QC#14606
    /**
     * Find Representative Info By PSN Code
     * @param glblCmpyCd String
     * @param psnCd String
     * @return S21_PSNTMsg
     */
    // START 2018/05/08 U.Kim [QC#25852, MOD]
    // public S21_PSNTMsg findRepInfo(String glblCmpyCd, String psnCd) {
    //     S21_PSNTMsg prmTMsg = new S21_PSNTMsg();
    //     prmTMsg.setSQLID("003");
    //     prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     prmTMsg.setConditionValue("psnCd01", psnCd);
    //     prmTMsg.setConditionValue("effFromDt01", ZYPDateUtil.getSalesDate(glblCmpyCd));
    //     prmTMsg.setConditionValue("effThruDt01", ZYPDateUtil.getSalesDate(glblCmpyCd));
    //     S21_PSNTMsgArray outArray = (S21_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    //     if (outArray.getValidCount() > 0) {
    //         return (S21_PSNTMsg) outArray.get(0);
    //     }
    //     return null;
    // }
    // mod end 2016/10/11 CSA QC#14606
    public S21SsmEZDResult findRepInfo(String glblCmpyCd, String psnCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("psnCd", psnCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        return getSsmEZDClient().queryObject("findRepInfo", ssmPrm);
    }

    /**
     * Find Representative Info By PSN Code
     * @param glblCmpyCd String
     * @param firstNm String
     * @param lastNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRepNameInfo(String glblCmpyCd, String firstNm, String lastNm) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("firstName", firstNm);
        ssmPrm.put("lastName", lastNm);
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // ssmPrm.put("psnTpCd", PSN_TP.EMPLOYEE);
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        // mod start 2016/10/11 CSA QC#14606
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        // mod end 2016/10/11 CSA QC#14606
        return getSsmEZDClient().queryObjectList("findRepNameInfo", ssmPrm);
    }

    /**
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @param psnCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSvcBrResrcReln(String glblCmpyCd, String svcContrBrCd, String psnCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcContrBrCd", svcContrBrCd);
        ssmPrm.put("psnCd", psnCd);
        ssmPrm.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmPrm.put("defEffThruDt", DEF_EFF_THRU_DT);
        return getSsmEZDClient().queryObject("countSvcBrResrcReln", ssmPrm);
    }
 // mod end 2016/03/28 CSA Defect#4702,4703,4915
 // mod end 2016/04/04 CSA Defect#4916

    /**
     * Find Person Code By TOC Code
     * @param glblCmpyCd String
     * @param tocCd String
     * @return ORG_FUNC_ASGTMsgArray
     */
    public ORG_FUNC_ASGTMsg findOrgFuncAsgByToc(String glblCmpyCd, String tocCd) {
        ORG_FUNC_ASGTMsg prmTMsg = new ORG_FUNC_ASGTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("tocCd01", tocCd);
        ORG_FUNC_ASGTMsgArray tMsgArray = (ORG_FUNC_ASGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
        if (tMsgArray.getValidCount() > 0) {
            return (ORG_FUNC_ASGTMsg) tMsgArray.get(0);
        }
        return null;
    }

    /**
     * Find TOC Code By Person Code
     * @param glblCmpyCd String
     * @param psnCd String
     * @return ORG_FUNC_ASGTMsg
     */
    public ORG_FUNC_ASGTMsg findOrgFuncAsgByPsnCd(String glblCmpyCd, String psnCd) {
        ORG_FUNC_ASGTMsg prmTMsg = new ORG_FUNC_ASGTMsg();
        prmTMsg.setSQLID("006");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("psnCd01", psnCd);
        ORG_FUNC_ASGTMsgArray tMsgArray = (ORG_FUNC_ASGTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(prmTMsg);
        if (tMsgArray.getValidCount() > 0) {
            return (ORG_FUNC_ASGTMsg) tMsgArray.get(0);
        }
        return null;
    }

    /**
     * Find Service Branch Resource Relation
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @param psnCd String
     * @return int
     */
    public int findSvcBrResrcReln(String glblCmpyCd, String svcContrBrCd, String psnCd) {
        SVC_BR_RESRC_RELNTMsg tMsg = new SVC_BR_RESRC_RELNTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcContrBrCd01", svcContrBrCd);
        tMsg.setConditionValue("psnCd01", psnCd);
        return (int) S21ApiTBLAccessor.count(tMsg);
    }

    /**
     * Find Service Memo Reason PulldownList
     * @param glblCmpyCd String
     * @param svcMemoTpCd String
     * @return SVC_MEMO_RSNTMsgArray
     */
    public SVC_MEMO_RSNTMsgArray findSvcMemoRsnPulldownList(String glblCmpyCd, String svcMemoTpCd) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMemoTpCd01", svcMemoTpCd);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // mod start 2016/12/08 CSA QC#4210
    /**
     * Insert Service Memo
     * @param cMsg NSAL0640CMsg
     * @param sMsg NSAL0640SMsg
     * @param index integer
     */
    public void insertSvcMemo(NSAL0640CMsg cMsg, NSAL0640SMsg sMsg, int index) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        setValue(tmsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tmsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.CONTRACT_INVOICE);
        setValue(tmsg.svcCmntTxt, cMsg.svcCmntTxt_H);
        setValue(tmsg.dsContrPk, sMsg.A.no(index).dsContrPk_A1);
        setValue(tmsg.lastUpdUsrId, cMsg.getUserID());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        setValue(tmsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H);
        S21FastTBLAccessor.insert(tmsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsMsgTxt_A1, NSAL0640CommonLogic.getRtnMsg(NSAM0032E, new String[] {SVC_MEMO }));
        }
    }
    // mod end 2016/12/08 CSA QC#4210
}
