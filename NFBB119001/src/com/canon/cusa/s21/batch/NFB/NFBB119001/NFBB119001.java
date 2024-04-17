/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB119001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CM_AP_INV_HDRTMsg;
import business.parts.DFBEZDItemValueSetter;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Receive Payment Status
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/10/2016   CSAI            Y.Miyauchi      Create          N/A
 * 10/05/2016   Hitachi         T.Tsuchida      Update          QC#14498
 * 10/14/2016   Hitachi         T.Tsuchida      Update          QC#14498
 * 10/17/2016   Hitachi         Y.Tsuchimoto    Update          QC#14498
 * 10/18/2017   CITS            T.Kikuhara      Update          QC#21650
 * 01/26/2018   CITS            T.Tokutomi      Update          QC#18685
 * 03/19/2018   CITS            T.Kikuhara      Update          QC#20316
 * 2018/04/17   Fujitsu         H.Ikeda         Update          QC#22767
 *</pre>
 */
public class NFBB119001 extends S21BatchMain implements NFBB119001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Commit Count */
    private int commitCount;

	@Override
	protected void initRoutine() {
        S21InfoLogOutput.println("initRoutine Method Start");

        /** Initialize SSM Batch client. */
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Initialize Commit Count. */
        this.commitCount = 0;

        S21InfoLogOutput.println("initRoutine Method End");
	}

    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {
        S21InfoLogOutput.println("mainRoutine Method Start");

        //Get VAR_CHAR_CONST value for Checking Status.
        String paidStsCd = ZYPCodeDataUtil.getVarCharConstValue(AP_CHECK_STS_CD_FOR_PAID, this.glblCmpyCd);
        // START 2016/10/14 T.Tsuchida [QC#14498,ADD]
        String paidStsNm = ZYPCodeDataUtil.getVarCharConstValue(AP_CHECK_STS_NM_FOR_PAID, this.glblCmpyCd);
        // START 2016/10/17 Y.Tsuchimoto [QC#14498,ADD]
        if (!ZYPCommonFunc.hasValue(paidStsCd)) {
            throw new S21AbendException(NFBM0268E, new String[] {AP_CHECK_STS_CD_FOR_PAID });
        }
        if (!ZYPCommonFunc.hasValue(paidStsNm)) {
            throw new S21AbendException(NFBM0268E, new String[] {AP_CHECK_STS_NM_FOR_PAID });
        }
        // END   2016/10/17 Y.Tsuchimoto [QC#14498,ADD]
        String[] paidStsCdArray = paidStsCd.split(",");
        String[] paidStsNmArray = paidStsNm.split(",");
        // END 2016/10/14 T.Tsuchida [QC#14498,ADD]

        //Main Process
        //Get PO Information
        /** set queryMap */
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put(GLBL_CMPY_CD_J, this.glblCmpyCd);
        // START 2016/10/14 T.Tsuchida [QC#14498,MOD]
        //queryMap.put(ACCT_INV_STS_CD_J, ACCT_INV_STS_CD_LINK_ORA);
        //queryMap.put(ARCS_PMT_STS_CD_L, paidStsCd.split(","));

        // START 2018/01/26 T.Tokutomi [QC#18685,MOD]
        // START 2018/04/17 H.Ikeda [QC#22767,DEL]
        //String targetDate = ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(this.glblCmpyCd), 1);
        //queryMap.put(TARGET_DATE, targetDate);
        // END   2018/04/17 H.Ikeda [QC#22767,DEL]
        String[] acctInvStsCdList = new String[] {ACCT_INV_STS.LINKED_TO_COST_CALCULATION, ACCT_INV_STS.PAID };
        queryMap.put(ACCT_INV_STS_CD_J, acctInvStsCdList);
        // END 2018/01/26 T.Tokutomi [QC#18685,MOD]

        queryMap.put(ARCS_PMT_STS_CD_L, paidStsNmArray);
        // END 2016/10/14 T.Tsuchida [QC#14498,MOD]
        /** getPOInformation */
        List<Map> ssmResult = (List<Map>) this.ssmBatchClient.queryObjectList("getUpdateTargetData", queryMap);

        if (ssmResult.size() <= 0) {
            return;
        } else {

            // CM_AP_INV_HDR TMessage
            CM_AP_INV_HDRTMsg cmApInvHdr = new CM_AP_INV_HDRTMsg();
            CM_AP_INV_HDRTMsg updateTMsg = new CM_AP_INV_HDRTMsg();

            for (int iCnt=0; iCnt<ssmResult.size(); iCnt++){
                cmApInvHdr = new CM_AP_INV_HDRTMsg();
                DFBEZDItemValueSetter.setValue(cmApInvHdr.glblCmpyCd, glblCmpyCd);
                DFBEZDItemValueSetter.setValue(cmApInvHdr.apVndCd, (String) ssmResult.get(iCnt).get(AP_VND_CD));
                DFBEZDItemValueSetter.setValue(cmApInvHdr.apVndInvNum, (String) ssmResult.get(iCnt).get(AP_VND_INV_NUM));
                DFBEZDItemValueSetter.setValue(cmApInvHdr.apVndInvSqNum, (String) ssmResult.get(iCnt).get(AP_VND_INV_SQ_NUM));

                //Get ForUpdate data	
                updateTMsg = (CM_AP_INV_HDRTMsg) EZDTBLAccessor.findByKeyForUpdate(cmApInvHdr);

                //Set Update Information
                // START 2016/10/14 T.Tsuchida [QC#14498,ADD]
                //ZYPEZDItemValueSetter.setValue(updateTMsg.acctInvStsCd, ACCT_INV_STS_CD_PAID);
                ZYPEZDItemValueSetter.setValue(updateTMsg.acctInvStsCd, getAcctInvStsCd((String) ssmResult.get(iCnt).get(ARCS_PMT_STS_CD), paidStsCdArray, paidStsNmArray));
                // END 2016/10/14 T.Tsuchida [QC#14498,ADD]
                // START 2016/10/05 T.Tsuchida [QC#14498,ADD]
                ZYPEZDItemValueSetter.setValue(updateTMsg.apPmtChkNum, (String) ssmResult.get(iCnt).get(AP_PMT_CHK_NUM));
                ZYPEZDItemValueSetter.setValue(updateTMsg.arcsPmtDt, (String) ssmResult.get(iCnt).get(PMT_DT));
                // START 2017/10/18 T.Kikuhara [QC#21650,UPD]
                ZYPEZDItemValueSetter.setValue(updateTMsg.arcsPmtAmt, (BigDecimal) ssmResult.get(iCnt).get(AP_INV_AMT));
                // END 2017/10/18 T.Kikuhara [QC#21650,UPD]
                ZYPEZDItemValueSetter.setValue(updateTMsg.arcsPmtMethCd, (String) ssmResult.get(iCnt).get(ARCS_PMT_METH_CD));
                // END 2016/10/05 T.Tsuchida [QC#14498,ADD]

                EZDTBLAccessor.update(updateTMsg);

                if (EZDTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                    commitCount++;
                } else {
                    rollback();
                    throw new S21AbendException(ZZBM0074E);
                }
            }

            commit();
        }

        S21InfoLogOutput.println("mainRoutine Method Start");
    }

    // START 2016/10/14 T.Tsuchida [QC#14498,ADD]
    private String getAcctInvStsCd(String paidStsNm, String[] paidStsCdArray, String[] paidStsNmArray) {
        String rtnVal = "";
        int cntSts = 0;
        if (paidStsCdArray.length >= paidStsNmArray.length) {
            cntSts = paidStsCdArray.length;
        }
        for (int i = 0; i < cntSts; i++) {
            if (paidStsNm.equals(paidStsNmArray[i])) {
                rtnVal = paidStsCdArray[i];
            }
        }
        return rtnVal;
    }
    // END 2016/10/14 T.Tsuchida [QC#14498,ADD]

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");
        /** Normal End this process */
        setTermState(TERM_CD.NORMAL_END, commitCount, 0, commitCount);
        S21InfoLogOutput.println("termRoutine Method End");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Main Method Start");

        /** Initialize S21BatchMain */
        new NFBB119001().executeBatch(NFBB119001.class.getSimpleName());

        S21InfoLogOutput.println("Main Method End");
    }
}
