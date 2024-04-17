/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB130001;

import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.CARR_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.CINC_CO_NUM_NA;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.CINC_GLBL_WH_CD_CLMBUS_DEFAULT;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.CINC_PO_GLBL_CATG_CD_A1;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.CINC_PO_GLBL_CATG_CD_AZ;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.COMMIT_CNT;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.DATE_TRIM_END_IDX;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.DATE_TRIM_START_IDX;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.DELETE_DAYS;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.EFF_FROM_DT_DEFALUT;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.FROM_LOC_TP_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.FROM_RTL_WH_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.FROM_WH_OWNR_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.FRT_COND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.INS_UNI_CNT;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.LOC_TP_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.MSG_ID_NPAM0020E;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.MSG_ID_NPAM1178E;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.MSG_ID_NPAM1341E;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.MSG_ID_NPAM1342E;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.MSG_ID_NPAM1343E;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.MSG_ID_ZZZM9025E;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.NUM_ONE;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.PRT_CHRG_IND_C;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.QTY_ZERO;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.RTL_WH_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.TABLE_INTFC_PO_RMN_WRK;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.TO_LOC_TP_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.TO_RTL_WH_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.TO_WH_OWNR_CD_AST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_CINC_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_EXCL_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_INCL_TECH_FLG;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.VAR_CHAR_CONST_KEY_SHPG_METH_OTHER;
import static com.canon.cusa.s21.batch.NPA.NPAB130001.constant.NPAB130001Constant.WH_OWNR_CD_AST;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.INTFC_PO_RMN_WRKTMsg;

import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCd;
import com.canon.cusa.s21.common.NMX.NMXC104001.NMXC104001ConvertPartsMdseCdBean;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NPAB1300 Outstanding PO
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/14   Fujitsu         C.Naito         Create          N/A
 * 2013/11/20   Fujitsu         C.Naito         Update          Def.3154
 * 2013/12/13   CSAI            Y.Imazu         Update          QC3258
 * 2014/03/04   SRA             Y.Chen          Update          SR506276
 * 2016/04/20   CITS            R.Shimamoto     Update          V2.0
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 02/04/2020   Fujitsu         S.Iidaka        Update          QC#55572
 * 06/27/2023   CITS            R.Kurahashi     Update          QC#61578
 *</pre>
 */

public class NPAB130001 extends S21BatchMain {

    // -- Variavle --------------
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** BatchProc Date */
    private String batchProcDt = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** Total count */
    private int totalCount = 0;

    /** Normal count */
    int normalRecCnt = 0;

    /** Error count */
    int errRecCnt = 0;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * @param args Input parameter.
     */
    public static void main(String[] args) {

        // Initialization S21BatchMain
        new NPAB130001().executeBatch(NPAB130001.class.getSimpleName());
    }

    /**
     * <pre>
     * Initialization routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    protected void initRoutine() {

        // Initialization
        profileService = S21UserProfileServiceFactory.getInstance().getService();
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get and check the input parameters.
        getInParams();

    }

    /**
     * <pre>
     * Main routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#mainRoutine()
     */
    protected void mainRoutine() {

        /* 12/16/2013 CSAI Y.Imazu Add QC3258 START */
        // Delete Work Date of Batch Date (For Rerun)
        executeDeleteWorkOldData(ZYPConstant.FLG_ON_Y);
        /* 12/16/2013 CSAI Y.Imazu Add QC3258 END */

        // Search POdata VendorReturndata and Insert WorkTBL
        executeSearchPOVendorReturnAndInsert();

        if (normalRecCnt > 0) {

            // Commit(WorkTBL)
            commit();

            // Delete WorkTBL OldData
            /* 12/16/2013 CSAI Y.Imazu Add QC3258 START */
            // executeDeleteWorkOldData();
            executeDeleteWorkOldData(ZYPConstant.FLG_OFF_N);
            /* 12/16/2013 CSAI Y.Imazu Add QC3258 END */
        }

    }

    /**
     * <pre>
     * Term routine.
     * </pre>
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
     */
    protected void termRoutine() {

        // Set term code and total commit count.
        setTermState(termCd, normalRecCnt, errRecCnt, totalCount);
    }

    /**
     * <pre>
     *  Check the input parameters.
     *  If an error occurs, throws Exception.
     * </pre>
     */
    private void getInParams() {

        // Get the Global Company Code.
        glblCmpyCd = profileService.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Global Company Code" });
        }

        // Get BatchProc Date
        batchProcDt = ZYPDateUtil.getBatProcDate();

        if (!ZYPCommonFunc.hasValue(batchProcDt)) {

            throw new S21AbendException(MSG_ID_ZZZM9025E, new String[] {"Batch Process Date" });
        }
    }

    /**
     * <pre>
     * Search POdata VendorReturndata and Insert WorkTBL
     * </pre>
     */
    private void executeSearchPOVendorReturnAndInsert() {

        PreparedStatement stmt = null;
        ResultSet resSet = null;

        try {

            // POdata,VendorReturndata Search Condition
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", glblCmpyCd);
            params.put("poStsValidated", PO_STS.VALIDATED);
            params.put("poStsReceiving", PO_STS.RECEIVING);
            params.put("poStsConfirmed", PO_STS.PO_CONFIRMED);
            params.put("poStsError", PO_STS.PO_ERROR);
            params.put("mdseS21Parts", MDSE_RGTN_TP.S21_PARTS);
            params.put("mdseManual", MDSE_RGTN_TP.MANUAL);
            params.put("mdseCatgParts", MDSE_CATG.PARTS);
            params.put("carrCd", CARR_CD);

            // STR 05/09/2016 R.Shimamoto [V2.0 ADD]
            params.put("effFromDtDefalut", EFF_FROM_DT_DEFALUT);
            params.put("rtlWhCdAst", RTL_WH_CD_AST);
            params.put("whOwnrCdAst", WH_OWNR_CD_AST);
            params.put("locTpCdAst", LOC_TP_CD_AST);
            params.put("frtCondCd", FRT_COND_CD);
            params.put("rwsAutoCratFlg", ZYPConstant.FLG_ON_Y);
            params.put("cmpyInvtyFlg", ZYPConstant.FLG_ON_Y);
            params.put("prchReqStsCdClosed", PRCH_REQ_STS.CLOSED);
            params.put("prchReqStsCdCancelled", PRCH_REQ_STS.CANCELLED);
            params.put("prchReqRecTpCdInventoryRequest", PRCH_REQ_REC_TP.INVENTORY_REQUEST);
            params.put("prchReqTpCdVendorReturn", PRCH_REQ_TP.VENDOR_RETURN);
            params.put("prchReqApvlFlg", ZYPConstant.FLG_ON_Y);
            params.put("dateTrimStartIdx", DATE_TRIM_START_IDX);
            params.put("dateTrimEndIdx", DATE_TRIM_END_IDX);
            params.put("fromRtlWhCdAst", FROM_RTL_WH_CD_AST);
            params.put("fromWhOwnrCdAst", FROM_WH_OWNR_CD_AST);
            params.put("fromLocTpCdAst", FROM_LOC_TP_CD_AST);
            params.put("toRtlWhCdAst", TO_RTL_WH_CD_AST);
            params.put("toWhOwnrCdAst", TO_WH_OWNR_CD_AST);
            params.put("toLocTpCdAst", TO_LOC_TP_CD_AST);
            params.put("prchReqTpCdWhTransfer", PRCH_REQ_TP.WH_TRANSFER);
            params.put("rwsOpenFlg", ZYPConstant.FLG_ON_Y);
            params.put("prchReqRecTpCdTechRequest", PRCH_REQ_REC_TP.TECH_REQUEST);
            params.put("prchReqRecTpCdTechReturn", PRCH_REQ_REC_TP.TECH_RETURN);
            params.put("qtyZero", QTY_ZERO);
            params.put("numOne", NUM_ONE);
            
            // QC#61578 Add Start
            params.put("mnx", WH_OWNR.MNX);
            // QC#61578 Add End

            // get dsCondConstGrpId
            // String dsCondConstGrpId =
            // ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD,
            // glblCmpyCd);
            // checkNull(dsCondConstGrpId, "VAR_CHAR_CONST : " +
            // VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD, "");
            params.put("dsCondConstGrpId", VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD);

            // get vndSysTpCdList
            String vndSysTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD, glblCmpyCd);
            String[] vndSysTpCdList = null;

            if (ZYPCommonFunc.hasValue(vndSysTpCd)) {

                vndSysTpCdList = vndSysTpCd.split(",");
            }

            params.put("vndSysTpCdList", vndSysTpCdList);

            // END 05/09/2016 R.Shimamoto [V2.0 ADD]

            // get vndCdCinc
            String vndCdCinc = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_VND_CD, glblCmpyCd);
            checkNull(vndCdCinc, "VAR_CHAR_CONST : " + VAR_CHAR_CONST_KEY_CINC_VND_CD, "");
            params.put("vndCdCinc", vndCdCinc);

            // get inclTechFlg
            String inclTechFlg = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_INCL_TECH_FLG, glblCmpyCd);
            checkNull(inclTechFlg, "VAR_CHAR_CONST : " + VAR_CHAR_CONST_KEY_INCL_TECH_FLG, "");

            if (ZYPConstant.FLG_OFF_N.equals(inclTechFlg)) {

                params.put("technician", LOC_TP.TECHNICIAN);

            } else {

                params.put("technician", null);
            }

            // get exclLocCdList
            String exclLocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_EXCL_LOC_CD, glblCmpyCd);
            String[] exclLocCdList = null;

            if (ZYPCommonFunc.hasValue(exclLocCd)) {

                exclLocCdList = exclLocCd.split(",");
            }

            params.put("exclLocCdList", exclLocCdList);

            // QC#30964 Add SCUBE_EXCL_MDSE_CD_LIST.
            String orgExclMdseCommaList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST, this.glblCmpyCd);

            if (ZYPCommonFunc.hasValue(orgExclMdseCommaList)) {
                String[] exclMdseList = orgExclMdseCommaList.split(",");

                params.put("exclMdseCdList", exclMdseList);
            }

            // Add Start 2020/02/04 QC#55572
            String scubeExclSwhCdList = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST, this.glblCmpyCd);

            if (ZYPCommonFunc.hasValue(scubeExclSwhCdList)) {
                String[] exclSwhCdList = scubeExclSwhCdList.split(",");

                params.put("exclSwhCdList", exclSwhCdList);
            }
            // Add End 2020/02/04 QC#55572

            // STR 05/11/2016 R.Shimamoto [V2.0 ADD]
            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            // END 05/11/2016 R.Shimamoto [V2.0 ADD]

            // Execute Search POdata VendorReturndata.
            stmt = ssmLLClient.createPreparedStatement("getPoAndVendorReturnData", params, ssmEexcParam);
            resSet = stmt.executeQuery();

            List<INTFC_PO_RMN_WRKTMsg> insList = new ArrayList<INTFC_PO_RMN_WRKTMsg>();
            List<String> msgList = new ArrayList<String>();

            //QC#26966 Update.
            String cincGlblWhCdOfClumbus = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS, glblCmpyCd);
            if (!ZYPCommonFunc.hasValue(cincGlblWhCdOfClumbus)) {
                cincGlblWhCdOfClumbus = CINC_GLBL_WH_CD_CLMBUS_DEFAULT;
            }

            while (resSet.next()) {

                INTFC_PO_RMN_WRKTMsg intfcPoRmnWrkTMsg = new INTFC_PO_RMN_WRKTMsg();
                S21ResultSetMapper.map(resSet, intfcPoRmnWrkTMsg);

                // Set Result data
                String poOrdNum = resSet.getString("PO_ORD_NUM");
                // SR506276

                String vndCd = resSet.getString("VND_CD");
                checkNull(vndCd, "VND_CD", poOrdNum);

                String poApvlDt = resSet.getString("PO_APVL_DT");
                checkNull(poApvlDt, "PO_APVL_DT", poOrdNum);

                intfcPoRmnWrkTMsg.glblCmpyCd.setValue(glblCmpyCd);
                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
                // intfcPoRmnWrkTMsg.cincGlblWhCd.setValue(CINC_GLBL_WH_CD_NA);
                String cincGlblWhCd = resSet.getString("CINC_GLBL_WH_CD");
                intfcPoRmnWrkTMsg.cincGlblWhCd.setValue(cincGlblWhCd);
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]
                intfcPoRmnWrkTMsg.intfcPoOrdDt.setValue(poApvlDt);
                intfcPoRmnWrkTMsg.intfcPoOrdNum.setValue(poOrdNum);
                intfcPoRmnWrkTMsg.cincCoNum.setValue(CINC_CO_NUM_NA);

                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
                // ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincSplyGlblCmpyCd,
                // resSet.getString("CINC_SPLY_GLBL_CMPY_CD"));
                String cincSplyGlblCmpyCd = resSet.getString("CINC_SPLY_GLBL_CMPY_CD");
                if (!ZYPCommonFunc.hasValue(cincSplyGlblCmpyCd)) {
                    ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincSplyGlblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincVndGlblCmpyCd, this.glblCmpyCd);

                } else {
                    ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincSplyGlblCmpyCd, resSet.getString("CINC_SPLY_GLBL_CMPY_CD"));
                    ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincVndGlblCmpyCd, resSet.getString("CINC_SPLY_GLBL_CMPY_CD"));
                }
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]

                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
                // intfcPoRmnWrkTMsg.cincSplyGlblWhCd.setValue("");
                String cincSplyGlblWhCd = resSet.getString("CINC_SPLY_GLBL_WH_CD");
                if (!ZYPCommonFunc.hasValue(cincSplyGlblWhCd)) {
                    intfcPoRmnWrkTMsg.cincSplyGlblWhCd.setValue("");
                } else {
                    ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincSplyGlblWhCd, cincSplyGlblWhCd);
                }

                // ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.cincVndGlblCmpyCd,
                // resSet.getString("CINC_SPLY_GLBL_CMPY_CD"));
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]
                intfcPoRmnWrkTMsg.intfcPrtVndCd.setValue(vndCd);

                String mdseCd = resSet.getString("MDSE_CD");
                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
                String cusaVndCd = resSet.getString("CUSA_VND_CD");
                // NMXC104001ConvertPartsMdseCdBean bean =
                // getIntfcPrtMdseCd(mdseCd, poOrdNum);
                NMXC104001ConvertPartsMdseCdBean bean = getIntfcPrtMdseCd(mdseCd, poOrdNum, cusaVndCd);
                ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.intfcPrtMdseCd, bean.getXtrnlSysPrtCd());
                ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.prtSizeTxt, bean.getXtrnlSysSize());
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]

                BigDecimal rmnQty = resSet.getBigDecimal("PO_BAL_QTY");
                String rmnSgn = getPlusMinusSign(rmnQty);
                ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.intfcPoRmnSgnTxt, rmnSgn);
                ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.intfcPoRmnQty, rmnQty.abs());

                ZYPEZDItemValueSetter.setValue(intfcPoRmnWrkTMsg.intfcEstStkInDt, (resSet.getString("ARV_DT")));

                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
                String cincGlblShpgMethCd = null;
                String shpgSvcLvlCd = resSet.getString("SHPG_SVC_LVL_CD");
                if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
                    cincGlblShpgMethCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SHPG_METH_OTHER, glblCmpyCd);
                    checkNull(cincGlblShpgMethCd, "VAR_CHAR_CONST : " + VAR_CHAR_CONST_KEY_SHPG_METH_OTHER, "");

                    // msgList.add(S21MessageFunc.clspGetMessage(MSG_ID_DPAM1343E,
                    // new String[] {"TRX_HDR_NUM(PO or VND_RTRN):" +
                    // poOrdNum + " MDSE_CD:" + mdseCd +
                    // " SHPG_SVC_LVL_CD:" + shpgSvcLvlCd }));

                } else {
                    cincGlblShpgMethCd = resSet.getString("TRD_PTNR_SHPG_METH_CD");

                    if (!ZYPCommonFunc.hasValue(cincGlblShpgMethCd)) {

                        cincGlblShpgMethCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SHPG_METH_OTHER, glblCmpyCd);
                        checkNull(cincGlblShpgMethCd, "VAR_CHAR_CONST : " + VAR_CHAR_CONST_KEY_SHPG_METH_OTHER, "");
                        // SR506276
                        msgList.add(S21MessageFunc.clspGetMessage(MSG_ID_NPAM1343E, new String[] {"TRX_HDR_NUM(PO or VND_RTRN):" + poOrdNum + " MDSE_CD:" + mdseCd + " SHPG_SVC_LVL_CD:" + shpgSvcLvlCd }));
                        // msgList.add(S21MessageFunc.clspGetMessage(MSG_ID_DPAM1343E,
                        // new String[]
                        // {"TRX_HDR_NUM(PO or VND_RTRN):" + poOrdNum
                        // + " MDSE_CD:" + mdseCd }));
                    }
                }
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]

                intfcPoRmnWrkTMsg.cincGlblShpgMethCd.setValue(cincGlblShpgMethCd);

                // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
                // intfcPoRmnWrkTMsg.cincPoGlblOrdCatgCd.setValue(CINC_PO_GLBL_CATG_CD_A1);
                String cincPoGlblCatgCd = resSet.getString("CINC_PO_GLBL_CATG_CD");
                if (!ZYPCommonFunc.hasValue(cincPoGlblCatgCd)) {
                    intfcPoRmnWrkTMsg.cincPoGlblOrdCatgCd.setValue(CINC_PO_GLBL_CATG_CD_A1);
                } else {
                    intfcPoRmnWrkTMsg.cincPoGlblOrdCatgCd.setValue(cincPoGlblCatgCd);
                }
                // END 05/09/2016 R.Shimamoto [V2.0 MOD]

                // QC#26966 Update.
                if (!cincGlblWhCdOfClumbus.equals(intfcPoRmnWrkTMsg.cincGlblWhCd.getValue()) //
                        && CINC_PO_GLBL_CATG_CD_A1.equals(intfcPoRmnWrkTMsg.cincPoGlblOrdCatgCd.getValue())) {
                    intfcPoRmnWrkTMsg.cincPoGlblOrdCatgCd.setValue(CINC_PO_GLBL_CATG_CD_AZ);
                }

                intfcPoRmnWrkTMsg.prtChrgInd.setValue(PRT_CHRG_IND_C);

                intfcPoRmnWrkTMsg.intfcCratDt.setValue(batchProcDt);

                insList.add(intfcPoRmnWrkTMsg);
                totalCount++;

                if (insList.size() == INS_UNI_CNT) {

                    int insListCnt = insList.size();

                    int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new INTFC_PO_RMN_WRKTMsg[0]));
                    insList.clear();

                    if (insListCnt != rsltCnt) {

                        throw new S21AbendException(MSG_ID_NPAM1341E, new String[] {TABLE_INTFC_PO_RMN_WRK });
                    }

                }
            }

            if (insList.size() > 0) {

                int insListCnt = insList.size();
                // INTFC_PO_RMN_WRKTMsg[] intfc_po_rmn_wrktmsgs = new
                // INTFC_PO_RMN_WRKTMsg[insList.size()];
                // intfc_po_rmn_wrktmsgs =
                // insList.toArray(intfc_po_rmn_wrktmsgs);
                // //
                // for (int i = 0; i < insList.size(); i++) {
                // intfc_po_rmn_wrktmsgs[i] = insList.get(i);
                // }

                // INTFC_PO_RMN_WRKTMsg[] p =
                // (INTFC_PO_RMN_WRKTMsg[])insList.toArray(new
                // INTFC_PO_RMN_WRKTMsg[0]);

                // int rsltCnt = S21FastTBLAccessor.insert(p);
                int rsltCnt = S21FastTBLAccessor.insert(insList.toArray(new INTFC_PO_RMN_WRKTMsg[0]));
                // int rsltCnt =
                // S21FastTBLAccessor.insert(intfc_po_rmn_wrktmsgs);
                // int rsltCnt = 0;
                // for(INTFC_PO_RMN_WRKTMsg intfc_po_rmn_wrktmsg :
                // intfc_po_rmn_wrktmsgs){
                // EZDTBLAccessor.insert((INTFC_PO_RMN_WRKTMsg)intfc_po_rmn_wrktmsg);
                // if
                // (EZDTBLAccessor.RTNCD_NORMAL.equals(intfc_po_rmn_wrktmsg.getReturnCode()))
                // {
                // rsltCnt++;
                // }
                // }

                insList.clear();

                if (insListCnt != rsltCnt) {

                    throw new S21AbendException(MSG_ID_NPAM1341E, new String[] {TABLE_INTFC_PO_RMN_WRK });
                }
            }

            // Insert NomalEnd
            normalRecCnt = totalCount;

            // Insert has Warning
            if (msgList.size() > 0) {

                termCd = TERM_CD.WARNING_END;

                // send Worning Mail
                callSendMailForWorningInfo(msgList);
            }

        } catch (SQLException e) {

            sqlExceptionHandler((SQLException) e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, resSet);
        }
    }

    /**
     * <pre>
     * Delete Work Data for 2days ago or Batch Date (For Rerun)
     * @param forRerunFlg String
     * </pre>
     */
    /* 12/16/2013 CSAI Y.Imazu Add QC3258 START */
    // private void executeDeleteWorkOldData() {
    private void executeDeleteWorkOldData(String forRerunFlg) {
        /* 12/16/2013 CSAI Y.Imazu Add QC3258 END */

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet resSet = null;

        try {

            // WorkTbl Search Condition
            Map<String, String> params = new HashMap<String, String>();
            params.put("glblCmpyCd", glblCmpyCd);

            // STR 05/11/2016 R.Shimamoto [V2.0 ADD]
            execParam = new S21SsmExecutionParameter();

            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            // END 05/11/2016 R.Shimamoto [V2.0 ADD]

            /* 12/16/2013 CSAI Y.Imazu Add QC3258 START */
            if (ZYPConstant.FLG_ON_Y.equals(forRerunFlg)) {

                params.put("batchProcDt", batchProcDt);
                stmt = ssmLLClient.createPreparedStatement("getWrkDeleteDataForRerun", params, execParam);

            } else {
                /* 12/16/2013 CSAI Y.Imazu Add QC3258 END */

                params.put("2daysAgo", ZYPDateUtil.addBusinessDay(glblCmpyCd, batchProcDt, DELETE_DAYS));
                stmt = ssmLLClient.createPreparedStatement("getWrkDeleteData", params, execParam);
                /* 12/16/2013 CSAI Y.Imazu Add QC3258 START */
            }
            /* 12/16/2013 CSAI Y.Imazu Add QC3258 END */

            // Execute search for delete query.
            resSet = stmt.executeQuery();

            List<INTFC_PO_RMN_WRKTMsg> delList = new ArrayList<INTFC_PO_RMN_WRKTMsg>();

            int execCnt = 0;

            while (resSet.next()) {

                // Delete the VND_PRC_WRKtbl.
                INTFC_PO_RMN_WRKTMsg intfcPoRmnWrkTMsg = new INTFC_PO_RMN_WRKTMsg();
                S21ResultSetMapper.map(resSet, intfcPoRmnWrkTMsg);
                delList.add(intfcPoRmnWrkTMsg);

                if (delList.size() == COMMIT_CNT) {

                    execCnt = S21FastTBLAccessor.removePhysical(delList.toArray(new INTFC_PO_RMN_WRKTMsg[0]));

                    if (execCnt != delList.size()) {

                        throw new S21AbendException(MSG_ID_NPAM1342E, new String[] {TABLE_INTFC_PO_RMN_WRK });
                    }

                    commit();
                    delList.clear();
                }
            }

            if (delList.size() > 0) {

                execCnt = S21FastTBLAccessor.removePhysical(delList.toArray(new INTFC_PO_RMN_WRKTMsg[0]));

                if (execCnt != delList.size()) {

                    throw new S21AbendException(MSG_ID_NPAM1342E, new String[] {TABLE_INTFC_PO_RMN_WRK });
                }

                commit();
                delList = null;
            }

        } catch (SQLException e) {

            sqlExceptionHandler((SQLException) e);

        } finally {

            S21SsmLowLevelCodingClient.closeResource(stmt, resSet);
        }
    }

    /**
     * <pre>
     * Check POdata or VendorReturnData are notNull
     * </pre>
     */
    private void checkNull(String data, String columnNm, String trxHdrNum) {

        if (!ZYPCommonFunc.hasValue(data)) {

            S21InfoLogOutput.println("TRX_HDR_NUM (PO or VND_RTRN) = " + trxHdrNum);
            throw new S21AbendException(MSG_ID_NPAM0020E, new String[] {columnNm });
        }
    }

    /**
     * <pre>
     * Get IntfcPrtMdseCd
     * </pre>
     */
    // STR 05/09/2016 R.Shimamoto [V2.0 MOD]
    // private NMXC104001ConvertPartsMdseCdBean
    // getIntfcPrtMdseCd(String mdseCd, String trxHdrNum) {
    private NMXC104001ConvertPartsMdseCdBean getIntfcPrtMdseCd(String mdseCd, String trxHdrNum, String cusaVndCd) {
        // END 05/09/2016 R.Shimamoto [V2.0 MOD]

        // Get Parts Mdse Code
        NMXC104001ConvertPartsMdseCdBean bean = new NMXC104001ConvertPartsMdseCdBean();
        String mode = NMXC104001ConvertPartsMdseCd.MODE_MDSE;
        bean.setMode(mode);
        bean.setGlblCmpyCd(glblCmpyCd);
        bean.setMdseCd(mdseCd);
        // STR 05/09/2016 R.Shimamoto [V2.0 ADD]
        bean.setCusaVndCd(cusaVndCd);
        // END 05/09/2016 R.Shimamoto [V2.0 ADD]

        NMXC104001ConvertPartsMdseCd.convertPartsMdseCd(bean);

        if (ZYPCommonFunc.hasValue(bean.getErrCd())) {

            // has Error
            S21InfoLogOutput.println("TRX_HDR_NUM (PO or VND_RTRN) = " + trxHdrNum + ", MDSE_CD = " + mdseCd);
            S21InfoLogOutput.println(S21MessageFunc.clspGetMessage(bean.getErrCd()));
            throw new S21AbendException(MSG_ID_NPAM1178E, new String[] {"NMXC104001ConvertPartsMdseCd" });

        } else {

            return bean;
        }
    }

    /**
     * <pre>
     * Get plus [minus] sign
     * </pre>
     */
    private String getPlusMinusSign(BigDecimal rmnQty) {

        String rmnSgn = null;

        if (rmnQty != null) {

            switch (rmnQty.signum()) {
                case -1:
                    rmnSgn = "-";
                    break;
                default:
                    rmnSgn = "+";
            }
        }

        return rmnSgn;
    }

    /**
     * <pre>
     * CallSendMailForWorningInfo
     * </pre>
     */
    private void callSendMailForWorningInfo(List<String> msgList) {

        NWXC100001SendMailForErrorInfo sendMail = new NWXC100001SendMailForErrorInfo();
        sendMail.addErrMsgList(msgList);
        String errorCd = sendMail.sendMail(glblCmpyCd, NPAB130001.class.getSimpleName());

        // DWXC100001SendMailForErrorInfo has Error
        if (ZYPCommonFunc.hasValue(errorCd)) {

            throw new S21AbendException(errorCd);
        }
    }
}
