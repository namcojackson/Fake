/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB134001;

import static com.canon.cusa.s21.batch.NFB.NFBB134001.NFBB134001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NPZC004001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Accrual Write-off PO Close Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/04/28   Hitachi         A.Kohinata      Create          QC#57934
 *</pre>
 */

public class NFBB134001 extends S21BatchMain {

    /** User Profile */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** Termination Code */
    private TERM_CD termCd;

    /** Normal Count */
    private int normalRcCnt;

    /** Error Count */
    private int errRcCnt;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFBB134001().executeBatch(NFBB134001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        S21InfoLogOutput.println("initRoutine Method Start");

        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        this.slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BATCH_ID);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.termCd = TERM_CD.NORMAL_END;
        this.normalRcCnt = 0;
        this.errRcCnt = 0;

        S21InfoLogOutput.println("initRoutine Method End");
    }

    @Override
    protected void mainRoutine() {
        S21InfoLogOutput.println("mainRoutine Method Start");

        List<Map<String, Object>> poDtlMapList = getTargetPoDtl();

        for (Map<String, Object> poDtlMap : poDtlMapList) {
            NPZC004001PMsg pMsg = new NPZC004001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, (String) poDtlMap.get("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.poOrdDtlLineNum, (String) poDtlMap.get("PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.wrtOffFlg, ZYPConstant.FLG_ON_Y);
            NPZC004001 api = new NPZC004001();
            api.execute(pMsg, ONBATCH_TYPE.BATCH);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("PO_ORD_NUM=").append((String) poDtlMap.get("PO_ORD_NUM"));
                sb.append(",PO_ORD_DTL_LINE_NUM=").append((String) poDtlMap.get("PO_ORD_DTL_LINE_NUM"));
                S21InfoLogOutput.printlnv(NFBM0208E, BATCH_ID, pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), sb.toString());

                this.errRcCnt++;
                rollback();
                continue;
            }

            this.normalRcCnt++;
            commit();
        }

        S21InfoLogOutput.println("mainRoutine Method End");
    }

    @Override
    protected void termRoutine() {
        S21InfoLogOutput.println("termRoutine Method Start");

        if (this.errRcCnt > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalRcCnt, this.errRcCnt);

        S21InfoLogOutput.println("termRoutine Method End");
    }

    private List<Map<String, Object>> getTargetPoDtl() {
        Map<String, Object> queryMap = new HashMap<String, Object>();

        queryMap.put("glblCmpyCd", this.glblCmpyCd);
        queryMap.put("poLineStsCdList", new String[] {PO_LINE_STS.OPEN, PO_LINE_STS.OPEN_FOR_INVOICE, PO_LINE_STS.OPEN_FOR_RECEIPT });
        queryMap.put("poMdseCmpsnTpRegular", PO_MDSE_CMPSN_TP.REGULAR);
        queryMap.put("flgOnY", ZYPConstant.FLG_ON_Y);
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(NFBL1130_COA_ACCT_CD, this.glblCmpyCd);
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(ARRAY_DELIMITER));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        queryMap.put("coaAcctCdList", coaAcctCdList);
        queryMap.put("dateFormat", ZYPDateUtil.TYPE_YYYYMMDD);
        BigDecimal pastDays = ZYPCodeDataUtil.getNumConstValue(NFBB1340_WRT_OFF_PAST_DAYS, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(pastDays)) {
            pastDays = DEFAULT_PAST_DAYS;
        }
        queryMap.put("pastDays", pastDays);
        queryMap.put("slsDt", this.slsDt);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTargetPoDtl", queryMap);
    }
}
