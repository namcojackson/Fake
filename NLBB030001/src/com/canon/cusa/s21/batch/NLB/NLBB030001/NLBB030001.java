/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLB.NLBB030001;

import static com.canon.cusa.s21.batch.NLB.NLBB030001constant.NLBB030001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NLB.NLBB030001constant.NLBB030001Constant.LOCAL;
import static com.canon.cusa.s21.batch.NLB.NLBB030001constant.NLBB030001Constant.MSG_STR_GLBL_COMP_CODE;
import static com.canon.cusa.s21.batch.NLB.NLBB030001constant.NLBB030001Constant.NLGM0011E;
import static com.canon.cusa.s21.batch.NLB.NLBB030001constant.NLBB030001Constant.ZZM9000E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.parts.NLZC402001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC402001.NLZC402001;
import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.POD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLBB030001
 * Function Name : Auto Delivery Confirmation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/07/2016   CITS            T.Hakodate      Create          N/A
 * 07/11/2016   CSAI            Y.Imazu         Update          QC#10917
 * 02/26/2018   CITS            S.Katsuma       Update          QC#20394
 * 09/11/2023   Hitachi         S.Dong          Update          QC#61274
 *</pre>
 */

public class NLBB030001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Global Company Code */
    private String schdDelyExcdDays;

    /** total normal count. */
    private int normalRecCnt;

    /** total error count. */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** PreparedStatement */
    private PreparedStatement prdStmt = null;

    /** TERM_CD */
    private TERM_CD termCd = null;

    /** Batch Date */
    private String batchDate = null;

    @Override
    protected void initRoutine() {

        // Initialization of ssmLLClient
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // Check on parameter
        checkParameter();

        // Get batch date
        batchDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);

        // Get Varchar Constant
        schdDelyExcdDays = ZYPCodeDataUtil.getVarCharConstValue("NLBB0300_SCHD_DELY_EXCD_DAYS", glblCmpyCd);

        this.termCd = TERM_CD.NORMAL_END;
    }

    @Override
    protected void mainRoutine() {

        S21SsmExecutionParameter execParam = null;
        ResultSet rsSoShpgList = null;

        // Set the fetch size.
        execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);

        try {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("batchDt", this.batchDate);
            // START 2018/02/26 S.Katsuma [QC#20394,DEL]
//            ssmParam.put("revRecogFlg", ZYPConstant.FLG_OFF_N);
            // END 2018/02/26 S.Katsuma [QC#20394,DEL]
            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
            ssmParam.put("shipPlnCancFlg", ZYPConstant.FLG_OFF_N);
            ssmParam.put("soPrintFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("shipFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("sceOrdTpCd", SCE_ORD_TP.DIRECT_SALES);
            ssmParam.put("soCustDataTpCd", SO_CUST_DATA_TP.SHIP_TO);
            ssmParam.put("schdDelyExcdDays", this.schdDelyExcdDays);
            // START 2023/09/11 S.Dong [QC#61274,ADD]
            String avalRtlWhCd = ZYPCodeDataUtil.getVarCharConstValue("NLBL2020_AUTO_RTL_WH_CD", this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(avalRtlWhCd)) {
                String[] avalRtlWhCdArray = avalRtlWhCd.split(",");
                ssmParam.put("avalRtlWhCdArray", avalRtlWhCdArray);
            }
            // END 2023/09/11 S.Dong [QC#61274,ADD]

            prdStmt = ssmLLClient.createPreparedStatement("findShpgList", ssmParam, execParam);
            rsSoShpgList = prdStmt.executeQuery();

            while (rsSoShpgList.next()) {

                NLZC402001PMsg apiPamam = new NLZC402001PMsg();

                ZYPEZDItemValueSetter.setValue(apiPamam.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(apiPamam.ediShpprNm, rsSoShpgList.getString("EDI_SHPPR_NM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediShpprAddr, rsSoShpgList.getString("EDI_SHPPR_ADDR"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediShpprCtyNm, rsSoShpgList.getString("EDI_SHPPR_CTY_NM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediShpprStCd, rsSoShpgList.getString("EDI_SHPPR_ST_CD"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediShpprPostCd, rsSoShpgList.getString("EDI_SHPPR_POST_CD"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediCnsgnNm, rsSoShpgList.getString("EDI_CNSGN_NM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediCnsgnAddr, rsSoShpgList.getString("EDI_CNSGN_ADDR"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediCnsgnCtyNm, rsSoShpgList.getString("EDI_CNSGN_CTY_NM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediCnsgnStCd, rsSoShpgList.getString("EDI_CNSGN_ST_CD"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediCnsgnPostCd, rsSoShpgList.getString("EDI_CNSGN_POST_CD"));
                ZYPEZDItemValueSetter.setValue(apiPamam.podSrcTpCd, POD_SRC_TP.AUTO);
                ZYPEZDItemValueSetter.setValue(apiPamam.ediStsCd, POD_STS.COMPLETED_DEPARTED_DELIVERY_LOCATION);
                ZYPEZDItemValueSetter.setValue(apiPamam.ediStsDt, batchDate);
                ZYPEZDItemValueSetter.setValue(apiPamam.ediTmCd, LOCAL);
                ZYPEZDItemValueSetter.setValue(apiPamam.ediStsCtyNm, rsSoShpgList.getString("EDI_STS_CTY_NM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.ediStsStCd, rsSoShpgList.getString("EDI_STS_ST_CD"));
                ZYPEZDItemValueSetter.setValue(apiPamam.soNum, rsSoShpgList.getString("SO_NUM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.soSlpNum, rsSoShpgList.getString("SO_SLP_NUM"));
                ZYPEZDItemValueSetter.setValue(apiPamam.carrCd, rsSoShpgList.getString("CARR_CD"));

                // call NLZC4020 Delivery Confirmation API
                NLZC402001 api = new NLZC402001();
                api.execute(apiPamam, S21ApiInterface.ONBATCH_TYPE.BATCH);

                if (S21ApiUtil.isXxMsgId(apiPamam)) {

                    List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPamam);

                    for (String xxMsgId : xxMsgIdList) {

                        S21InfoLogOutput.println(xxMsgId);
                    }

                    this.termCd = TERM_CD.WARNING_END;
                    errRecCnt++;
                    rollback();

                } else {

                    normalRecCnt++;
                    commit();
                }
            }

        } catch (SQLException e) {

            rollback();
            sqlExceptionHandler(e);
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NLGM0011E, new String[] {"NLZC4020 Delivery Confirmation API" });

        } finally {

            S21SsmLowLevelCodingClient.closeResource(prdStmt, rsSoShpgList);
        }
    }

    @Override
    protected void termRoutine() {

        // Setting of termination code
        setTermState(this.termCd, normalRecCnt, errRecCnt);
    }

    /**
     * checkParameter
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            S21InfoLogOutput.println(ZZM9000E, new String[] {MSG_STR_GLBL_COMP_CODE });
            throw new S21AbendException(ZZM9000E, new String[] {MSG_STR_GLBL_COMP_CODE });
        }
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NLBB030001().executeBatch(NLBB030001.class.getSimpleName());
    }
}
