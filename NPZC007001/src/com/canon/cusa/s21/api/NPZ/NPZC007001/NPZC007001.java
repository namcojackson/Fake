/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC007001;

import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.AMOUNT_SCALE;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BLANK;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BLANK_ONE;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.COMMA;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_DP_PRNT_VND_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_DP_RQST_TECH_TOC_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_DP_VND_PMT_TERM_DESC_TXT;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_P_PO_APVL_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_P_PO_PRINT_FLG;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_P_PO_STS_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_P_PO_SUBMT_PSN_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_GNRN_TP_CD_01;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_GNRN_TP_CD_02;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_ORG_STRU_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_PO_ORD_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_PO_RPT_INFO_TP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_PO_STS_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_PROC_DT;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_PSN_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_COLUMN_DP_PRCH_GRP_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.DB_PARAM_PO_LINE_STS_CD;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.FIRST_LINE_ADDR_LENGTH_MAX;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_CMPY_ADDR1;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_CMPY_ADDR2;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_CMPY_ADDR3;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_CMPY_ADDR4;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_CMPY_ADDR5;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_GLBL_CMPY_NM;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_GLBL_CMPY_TEL_NUM;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_GLBL_FULL_CMPY_ADDR;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_LINE_NUM_OF_1ST_PAGE;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_LINE_NUM_OF_2ND_PAGE;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPAF0010_NUM_OF_DESC_LTR;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0001E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0018E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0078E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0079E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0080E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0081E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0082E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0083E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0084E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0085E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0086E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0087E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0298E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.NPZM0299E;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.PO_COMMENT_LENGTH_END;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.PO_COMMENT_LENGTH_STR;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.PO_ORD_TERM_TXT_01_SUFIX;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.PO_ORD_TERM_TXT_02_SUFIX;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.PO_ORD_TERM_TXT_03_SUFIX;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.PRCH_GRP_CD_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_NM_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ATTN_NM_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR1_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR2_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR3_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR4_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR5_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR6_ESS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_NM_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ATTN_NM_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR1_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR2_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR3_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR4_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR5_LFS_PPS;
import static com.canon.cusa.s21.api.NPZ.NPZC007001.NPZC007001Constant.BILL_TO_ADDR6_LFS_PPS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.CCYTMsgArray;
import business.db.FRT_CONDTMsg;
import business.db.MDSETMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.PO_DTLTMsg;
import business.db.PO_DTLTMsgArray;
import business.db.PO_MSGTMsg;
import business.db.PO_RPT_INFOTMsg;
import business.db.PO_RPT_INFO_WRKTMsg;
import business.db.PO_RPT_WRKTMsg;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SHPG_SVC_LVLTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.parts.NPZC007001PMsg;

import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_RPT_INFO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Create PO Report API
 * Date          Company     Name          Create/Update     Defect No
 * ----------------------------------------------------------------------
 * 05/08/2012    Fujitsu     K.Matsumura   Create            N/A
 * 26/06/2012    Fujitsu     S.Nakai       Update            #405
 * 07/03/2012    SRA         E.Inada       Update            #419
 * 07/12/2012    Fujitsu     S.Nakai       Update            #456
 * 11/15/2012    Hitachi     T.Tomita      Update            V1.1
 * 02/06/2013    Hitachi     T.Tomita      Update            REF01
 * 02/15/2013    Hitachi     T.Tomita      Update            REF01
 * 04/04/2013    Hitachi     T.Tomita      Update            QC1003
 * 02/01/2016    CITS        R.Shimamoto   Update            V1.5
 * 11/28/2016    CITS        Y.Fujii       Update            R350
 * 01/06/2017    CITS        Y.Fujii       Update            QC#17002
 * 08/08/2017    CITS        S.Endo        Update            Sol#035(QC#18108)
 * 10/16/2017    CITS        T.Kikuhara    Update            QC#20246(Sol#454)
 * 10/20/2017    CITS        T.Hakodate    UPDATE            QC#20246
 * 11/29/2017    CITS        K.Kameoka     UPDATE            QC#22666
 * 12/04/2017    CITS        K.Kameoka     UPDATE            QC#14858(Sol#060)
 * 01/09/2018    CITS        K.Kameoka     Update            QC#18602(Sol#102)
 * 01/25/2018    CITS        K.Ogino       Update            QC#23617
 * 01/30/2018    CITS        K.Ogino       Update            QC#23616
 * 02/07/2018    CITS        K.Kameoka     Update            QC#23929
 * 04/23/2018    CITS        K.Kameoka     Update            QC#21354
 * 04/27/2018    CITS        K.Kameoka     Update            QC#25841
 * 05/08/2018    CITS        K.Fukumura    Update            QC#25973
 * 05/09/2018    CITS        K.Fukumura    Update            QC#25976
 * 05/31/2018    CITS        K.Kameoka     Update            QC#2632811/07
 * 06/13/2018    CITS        K.Fukumura    Update            QC#25820
 * 03/22/2019    Fujitsu     T.Ogura       Update            QC#30565
 * 11/07/2019    CITS        K.Fukumura    Update            QC#54292
 * 01/27/2020    CITS        K.Fukumura    Update            QC#55571
 *</pre>
 */
public class NPZC007001 extends S21ApiCommonBase {

    /** PO_DTLTMsg. */
    private PO_DTLTMsg poDtlTMsg = null;
    
    /** poRptInfoNTList. */
    private List<PO_RPT_INFOTMsg> poRptInfoNTList = null;

    /** poRptInfoTCList. */
    private List<PO_RPT_INFOTMsg> poRptInfoTCList = null;

    /**
     * <pre>Constructor</pre>
     * @param none
     * @throws none
     */
    public NPZC007001() {
        super();

    }

    /**
     * <pre>
     * Create PO Report API
     * Call execute(APAF007001PMsg, ONBATCH_TYPE) method by each PMsg.
     * </pre>
     * @param inpPrmMsg APAF007001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NPZC007001PMsg> inpPrmMsg, final ONBATCH_TYPE onBatchType) {
        for (int i = 0; i < inpPrmMsg.size(); i++) {
            execute(inpPrmMsg.get(i), onBatchType);
        }
    }

    /**
     * <pre>
     * Create PO Report API
     * </pre>
     * @param inpPrmPMsg APAF007001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC007001PMsg inpPrmPMsg, final ONBATCH_TYPE onBatchType) {

        final S21ApiMessageMap msgMap = new S21ApiMessageMap(inpPrmPMsg);

        try {
            if (!checkInputCommon(msgMap)) {
                return;
            }
            // STR 2012/11/19 T.Tomita [V1.1 MOD]
            // STR 2016/02/03 R.Shimamoto [V1.5 DEL]
            // POTMsg poTMsg = findPoByKeyForUpdate(inpPrmPMsg);
            // END 2016/02/03 R.Shimamoto [V1.5 DEL]
            Map<String, Object> poMap = findPoAndDsPo(inpPrmPMsg);
            if (poMap == null) {
                this.addMsgId(msgMap, NPZM0082E);
                return;
            }
            if (!checkPoSts(msgMap, poMap)) {
                return;
            }

            SHIP_TO_CUSTTMsg shipToCustTMsg = findShipToCustData(inpPrmPMsg, msgMap);
            if (shipToCustTMsg == null) {
                this.addMsgId(msgMap, NPZM0085E);
                return;
            }

            VNDTMsg vndTMsg = findVndByVndCd(inpPrmPMsg);
            if (vndTMsg == null) {
                this.addMsgId(msgMap, NPZM0083E);
                return;
            }

            // STR 2016/02/01 R.Shimamoto [V1.5 MOD]
            // PO_CHRGTMsg chrgTMsg = findChrgByChrgCd(inpPrmPMsg);
            // END 2016/02/01 R.Shimamoto [V1.5 MOD]

            PO_DTLTMsgArray poDtlLst = findPoDtlByPoOdrNum(inpPrmPMsg);
            if (poDtlLst == null || poDtlLst.length() == 0) {
                this.addMsgId(msgMap, NPZM0084E);
                return;
            }

            // PO Report Information Note get
            poRptInfoNTList = findPoRptInfo(inpPrmPMsg, PO_RPT_INFO_TP.NOTE);
            if (poRptInfoNTList == null || poRptInfoNTList.size() == 0) {
                this.addMsgId(msgMap, NPZM0298E);
                return;
            }

            // PO Report Information Terms and conditions get
            poRptInfoTCList = findPoRptInfo(inpPrmPMsg, PO_RPT_INFO_TP.TERMS_AND_CONDITIONS);
            if (poRptInfoTCList == null || poRptInfoTCList.size() == 0) {
                this.addMsgId(msgMap, NPZM0299E);
                return;
            }

            // STR 2016/02/03 R.Shimamoto [V1.5 MOD]
            createPoRptWrk(inpPrmPMsg, poMap, shipToCustTMsg, vndTMsg, poDtlLst, msgMap);
            // END 2016/02/03 R.Shimamoto [V1.5 MOD]

            // END 2012/11/19 T.Tomita [V1.1 MOD]

        } finally {
            msgMap.flush();
        }
    }

    /**
     * <pre>
     * common input parameter check
     * </pre>
     * @param msgMap Message Map
     * @return check result(OK:true, NG:false)
     */
    private boolean checkInputCommon(S21ApiMessageMap msgMap) {

        // IN-parameter PMsg
        NPZC007001PMsg inpPrmPMsg = (NPZC007001PMsg) msgMap.getPmsg();

        // input parameter null check
        // STR 2012/11/19 T.Tomita [V1.1 MOD]
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.glblCmpyCd)) {
            this.addMsgId(msgMap, NPZM0001E);
            return false;
        }

        // Warehouse Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.whCd)) {
            this.addMsgId(msgMap, NPZM0078E);
            return false;
        }

        // Vendor Code
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.vndCd)) {
            this.addMsgId(msgMap, NPZM0079E);
            return false;
        }

        // PO#
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.poOrdNum)) {
            this.addMsgId(msgMap, NPZM0018E);
            return false;
        }

        // User ID
        if (!ZYPCommonFunc.hasValue(inpPrmPMsg.usrId)) {
            this.addMsgId(msgMap, NPZM0087E);
            return false;
        }
        // END 2012/11/19 T.Tomita [V1.1 MOD]
        return true;
    }

    // STR 2016/02/03 R.Shimamoto [V1.5 DEL]
    // private boolean checkPoSts(S21ApiMessageMap msgMap, POTMsg
    // poTsg) {
    // // STR 2012/11/19 T.Tomita [V1.1 ADD]
    // if (PO_STS.SAVED.equals(poTsg.poStsCd.getValue())) {
    // msgMap.addXxMsgId(NPZM0080E);
    // return false;
    // }
    // if
    // (PO_STS.WAITING_FOR_APPROVAL.equals(poTsg.poStsCd.getValue()))
    // {
    // msgMap.addXxMsgId(NPZM0081E);
    // return false;
    // }
    // // END 2012/11/19 T.Tomita [V1.1 ADD]
    //
    // return true;
    // }
    // END 2016/02/03 R.Shimamoto [V1.5 DEL]

    // STR 2016/02/03 R.Shimamoto [V1.5 ADD]
    private boolean checkPoSts(S21ApiMessageMap msgMap, Map<String, Object> poMap) {

        if (PO_STS.SAVED.equals(poMap.get(DB_COLUMN_P_PO_STS_CD))) {
            msgMap.addXxMsgId(NPZM0080E);
            return false;
        }
        if (PO_STS.WAITING_FOR_APPROVAL.equals(poMap.get(DB_COLUMN_P_PO_STS_CD))) {
            msgMap.addXxMsgId(NPZM0081E);
            return false;
        }

        return true;
    }

    // END 2016/02/03 R.Shimamoto [V1.5 ADD]

    // STR 2016/02/03 R.Shimamoto [V1.5 DEL]
    // private POTMsg findPoByKeyForUpdate(NPZC007001PMsg bizMsg) {
    //
    // POTMsg poTMsg = new POTMsg();
    // poTMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
    // poTMsg.poOrdNum.setValue(bizMsg.poOrdNum.getValue());
    //
    // // STR 2013/02/06 T.Tomita [REF01 UPD]
    // return (POTMsg) S21ApiTBLAccessor.findByKeyForUpdate(poTMsg);
    // // END 2013/02/06 T.Tomita [REF01 UPD]
    // }
    // END 2016/02/03 R.Shimamoto [V1.5 DEL]

    // STR 2016/02/03 R.Shimamoto [V1.5 ADD]
    private Map<String, Object> findPoAndDsPo(NPZC007001PMsg bizMsg) {

        Map<String, Object> recode = null;
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_PO_ORD_NUM, bizMsg.poOrdNum.getValue());

        // Execute
        S21SsmEZDResult result = NPZC007001Query.getInstance().getPo(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            recode = resultList.get(0);
        }
        return recode;
    }

    // END 2016/02/03 R.Shimamoto [V1.5 ADD]

    private VNDTMsg findVndByVndCd(NPZC007001PMsg bizMsg) {
        // STR 2012/11/21 T.Tomita [V1.1 MOD]
        VNDTMsg inMsg = new VNDTMsg();
        inMsg.setSQLID("010");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("vndCd01", bizMsg.vndCd.getValue());
        inMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        // STR 2013/02/06 T.Tomita [REF01 UPD]
        VNDTMsgArray resultList = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        // END 2013/02/06 T.Tomita [REF01 UPD]
        VNDTMsg vndTMsg = null;
        if (resultList.getValidCount() == 0) {
            return vndTMsg;
        }

        vndTMsg = (VNDTMsg) resultList.get(0);

        // END 2012/11/19 T.Tomita [V1.1 MOD]
        return vndTMsg;
    }

    private PO_DTLTMsgArray findPoDtlByPoOdrNum(NPZC007001PMsg bizMsg) {

        PO_DTLTMsg inMsg = new PO_DTLTMsg();
        // STR 2016/02/01 R.Shimamoto [V1.5 MOD]
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("poOrdNum01", bizMsg.poOrdNum.getValue());
        inMsg.setConditionValue("poStsCd01", PO_STS.CANCELLED);
        // END 2016/02/01 R.Shimamoto [V1.5 MOD]

        // STR 2013/02/06 T.Tomita [REF01 UPD]
        return (PO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        // END 2013/02/06 T.Tomita [REF01 UPD]
    }

    private List<PO_RPT_INFOTMsg> findPoRptInfo(NPZC007001PMsg bizMsg, String poRptInfoTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_PO_RPT_INFO_TP_CD, poRptInfoTpCd);
        ssmParam.put(DB_PARAM_PROC_DT, bizMsg.procDt.getValue());

        // Execute
        S21SsmEZDResult result = NPZC007001Query.getInstance().getPoRptInfo(ssmParam);

        List<PO_RPT_INFOTMsg> outArray = null;
        if (result.isCodeNormal()) {
            outArray =  (List<PO_RPT_INFOTMsg>) result.getResultObject();
        }
        return outArray;
    }

    // STR 2012/11/19 T.Tomita [V1.1 MOD]
    private void createPoRptWrk(NPZC007001PMsg bizMsg, Map<String, Object> poMap, SHIP_TO_CUSTTMsg shipToCustTMsg, VNDTMsg vndTMsg, PO_DTLTMsgArray poDtlLst, S21ApiMessageMap msgMap) {

        // Set current Time for print.
        String currentTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS");
        bizMsg.rcvRptTs.setValue(currentTime);

        PO_RPT_WRKTMsg header = new PO_RPT_WRKTMsg();

        header.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        header.usrId.setValue(bizMsg.usrId.getValue());
        header.rcvRptTs.setValue(currentTime);
        header.vndCd.setValue(bizMsg.vndCd.getValue());
        // QC#22666 MOD START 2017/11/29 K.Kameoka
        //header.locNm.setValue(shipToCustTMsg.locNm.getValue());
        // QC#22666 MOD END  2017/11/29 K.Kameoka
        BigDecimal poRptWrkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_RPT_WRK_SQ);
        bizMsg.poRptPrintRqstSq.setValue(poRptWrkSq);
        header.poRptWrkPk.setValue(poRptWrkSq);
        header.poRptPrintRqstSq.setValue(poRptWrkSq);
        Boolean frstWrkFlg = true;
        header.poRptPgNum.setValue(BigDecimal.ONE);

        header.glblCmpyNm.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_GLBL_CMPY_NM, bizMsg.glblCmpyCd.getValue()));
        header.glblFullCmpyAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_GLBL_FULL_CMPY_ADDR, bizMsg.glblCmpyCd.getValue()));
        header.glblCmpyTelNum.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_GLBL_CMPY_TEL_NUM, bizMsg.glblCmpyCd.getValue()));

        if (ZYPConstant.FLG_ON_Y.equals(poMap.get(DB_COLUMN_P_PO_PRINT_FLG).toString())) {
            header.poPrintTxt.setValue(NPZC007001Constant.REPRINT);
        }
        header.poOrdNum.setValue(bizMsg.poOrdNum.getValue());

        // page num calc
        BigDecimal lineNumOf1stPage = ZYPCodeDataUtil.getNumConstValue(NPAF0010_LINE_NUM_OF_1ST_PAGE, bizMsg.glblCmpyCd.getValue());
        BigDecimal lineNumOf2ndPage = ZYPCodeDataUtil.getNumConstValue(NPAF0010_LINE_NUM_OF_2ND_PAGE, bizMsg.glblCmpyCd.getValue());
        BigDecimal numOfDescLtr = ZYPCodeDataUtil.getNumConstValue(NPAF0010_NUM_OF_DESC_LTR, bizMsg.glblCmpyCd.getValue());
        Map<String, BigDecimal> pageCalcConstMap = new HashMap<String, BigDecimal>();
        pageCalcConstMap.put(NPAF0010_LINE_NUM_OF_1ST_PAGE, lineNumOf1stPage);
        pageCalcConstMap.put(NPAF0010_LINE_NUM_OF_2ND_PAGE, lineNumOf2ndPage);
        pageCalcConstMap.put(NPAF0010_NUM_OF_DESC_LTR, numOfDescLtr);
        BigDecimal pdfLineNum = new BigDecimal("0");

        /*
         * Temporary Modify for Build AP1!!!!!!!!!!!!!!!!!!!!!!!!!
         * Please check Revision 1
         * header.poOrdDt.setValue(poTMsg.poApvlDt.getValue());
         * Temporary Modify for Build AP1!!!!!!!!!!!!!!!!!!!!!!!!!
         * Please check Revision 1
         */
        // ** Edit Vendor **
        ZYPEZDItemValueSetter.setValue(header.vndFirstLineAddr, vndTMsg.firstLineAddr);
        // ---- add 2010/04/01 Defect 4675
        ZYPEZDItemValueSetter.setValue(header.vndAttnNm, vndTMsg.attnNm);

        // ZYPEZDItemValueSetter.setValue(header.vndNm,
        // vndTMsg.locNm);

        ZYPEZDItemValueSetter.setValue(header.vndNm, getPrntVndNm(vndTMsg));

        editVndAddress(vndTMsg, header);

        /*
         * STR 2016/02/01 R.Shimamoto [V1.5 MOD] // Edit Charge To if
         * (poChrgTMsg != null) {
         * ZYPEZDItemValueSetter.setValue(header.poChrgNm,
         * poChrgTMsg.poChrgNm);
         * ZYPEZDItemValueSetter.setValue(header.chrgAttnNm,
         * poChrgTMsg.attnNm);
         * ZYPEZDItemValueSetter.setValue(header.chrgFirstLineAddr,
         * poChrgTMsg.firstLineAddr); editChrgToAddress(poChrgTMsg,
         * header); } END 2016/02/01 R.Shimamoto [V1.5 MOD]
         */
        header.glblCmpyFirstLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_CMPY_ADDR1, bizMsg.glblCmpyCd.getValue()));
        header.glblCmpyScdLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_CMPY_ADDR2, bizMsg.glblCmpyCd.getValue()));
        header.glblCmpyThirdLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_CMPY_ADDR3, bizMsg.glblCmpyCd.getValue()));
        header.glblCmpyFrthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_CMPY_ADDR4, bizMsg.glblCmpyCd.getValue()));
        header.glblCmpyFifthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(NPAF0010_CMPY_ADDR5, bizMsg.glblCmpyCd.getValue()));

        // STR 2016/02/01 R.Shimamoto [V1.5 DEL]
        // ** Edit PO ord term
        // header.poOrdTermTxt_01.setValue(bizMsg.poOrdTermTxt_01.getValue());
        // header.poOrdTermTxt_02.setValue(bizMsg.poOrdTermTxt_02.getValue());
        // header.poOrdTermTxt_03.setValue(bizMsg.poOrdTermTxt_03.getValue());
        // header.poOrdTermTxt_04.setValue(bizMsg.poOrdTermTxt_04.getValue());
        // header.poOrdTermTxt_05.setValue(bizMsg.poOrdTermTxt_05.getValue());
        // END 2016/02/01 R.Shimamoto [V1.5 DEL]
        // STR 2016/02/01 R.Shimamoto [V1.5 DEL]
        // ** Edit PO ord cmnt
        // header.poOrdCmntTxt.setValue(bizMsg.poOrdCmntTxt.getValue());
        // header.poOrdCmntTxt_02.setValue(bizMsg.poOrdCmntTxt_02.getValue());
        // header.poOrdCmntTxt_03.setValue(bizMsg.poOrdCmntTxt_03.getValue());
        // header.poOrdCmntTxt_04.setValue(bizMsg.poOrdCmntTxt_04.getValue());
        // header.poOrdCmntTxt_05.setValue(bizMsg.poOrdCmntTxt_05.getValue());
        // END 2016/02/01 R.Shimamoto [V1.5 DEL]

        // STR 2016/02/01 R.Shimamoto [V1.5 DEL]
        // header.poOrdShpgInstnTxt.setValue(poTMsg.poOrdShpgInstnTxt.getValue());
        // END 2016/02/01 R.Shimamoto [V1.5 DEL]

        // STR 2016/02/01 R.Shimamoto [V1.5 ADD] Find FULL_PSN_NM
        S21_PSN_VTMsg psnTMsg_poSubmtPsn = findS21PsnV(bizMsg, (String) poMap.get(DB_COLUMN_P_PO_SUBMT_PSN_CD));
        S21_PSN_VTMsg psnTMsg_dpRqstTechToc = findS21PsnV(bizMsg, (String) poMap.get(DB_COLUMN_DP_RQST_TECH_TOC_CD));

        // STR 2016/02/01 R.Shimamoto [V1.5 ADD] Find
        // VND_PMT_TERM_DESC_TXT
        // DS_POTMsg dsPoTMsg = findDsPo(bizMsg,
        // poMap.poOrdNum.getValue());

        String poRptPrintDtTxt=(String) poMap.get(DB_COLUMN_P_PO_APVL_DT);
        String buNm = getDepartment(bizMsg, poMap);
        
        //QC#21354 START
        
        //Buyer Phone#
        // get tel# from AUTH_PSN
        ZYPEZDItemValueSetter.setValue(header.byrTelNum, getByrTelNum(bizMsg, (String) poMap.get(DB_COLUMN_P_PO_SUBMT_PSN_CD)));
        
        //Customer PO#
        //header.vndIssOrdNum.setValue(poMap.get(DB_COLUMN_VND_ISS_ORD_NUM));
        //QC#26328 Mod Start
        //ZYPEZDItemValueSetter.setValue(header.vndIssOrdNum, (String)poMap.get(DB_COLUMN_VND_ISS_ORD_NUM));
        //QC#26328 Mod End
        
        //Bill to Address (LOB)
        if(PRCH_GRP_CD_ESS.equals(poMap.get(DB_COLUMN_DP_PRCH_GRP_CD))){
            header.billToLocNm.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_NM_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToAttnNm.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ATTN_NM_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToFirstLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR1_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToScdLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR2_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToThirdLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR3_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToFrthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR4_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToFifthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR5_ESS, bizMsg.glblCmpyCd.getValue()));
            header.billToSixthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR6_ESS, bizMsg.glblCmpyCd.getValue()));
        }else{
            header.billToLocNm.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_NM_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToAttnNm.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ATTN_NM_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToFirstLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR1_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToScdLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR2_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToThirdLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR3_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToFrthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR4_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToFifthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR5_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
            header.billToSixthLineAddr.setValue(ZYPCodeDataUtil.getVarCharConstValue(BILL_TO_ADDR6_LFS_PPS, bizMsg.glblCmpyCd.getValue()));
        }
        
        //QC#21354 END
        
        // #detail
        for (int i = 0; i < poDtlLst.length(); i++) {
            PO_DTLTMsg poDtlTMsg = poDtlLst.no(i);

            //QC#18602(Sol#102) ADD Start
            if (ZYPCommonFunc.hasValue(bizMsg.openFlg) //
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.openFlg.getValue()) //
                    && PO_LINE_STS.CLOSED.equals(poDtlTMsg.poLineStsCd.getValue()) || PO_LINE_STS.CANCELLED.equals(poDtlTMsg.poLineStsCd.getValue())) {
                continue;
                
            }
            //QC#18602(Sol#102) ADD End

            PO_RPT_WRKTMsg poRptWrkTMsg = new PO_RPT_WRKTMsg();
            EZDMsg.copy(header, null, poRptWrkTMsg, null);


            if (!frstWrkFlg) {
                poRptWrkSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_RPT_WRK_SQ);
                poRptWrkTMsg.poRptWrkPk.setValue(poRptWrkSq);
            } else {
                frstWrkFlg = false;
            }

            // STR 2016/02/01 R.Shimamoto [V1.5 DEL]
            // VND_PROD_XREFTMsg vndProdXrefTMsg =
            // getVndProdXrefData(bizMsg, poDtlTMsg);
            // END 2016/02/01 R.Shimamoto [V1.5 DEL]

            MDSETMsg mdseTMsg = getMdseTMsg(poDtlTMsg);
            
            //QC#26328 Mod Start
            //Customer PO#
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.custIssPoNum, poDtlTMsg.custIssPoNum);
            //QC#26328 Mod End

            //QC#21354 START
            //CSA Sales Order#
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.cpoOrdNum, poDtlTMsg.cpoOrdNum);
            //QC#21354 END

            // ** Edit Ship To **
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.custPoNum, poDtlTMsg.cpoOrdNum);
            editShipToAddressFromPoDtl(poDtlTMsg, shipToCustTMsg, poRptWrkTMsg);

            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdDtlLineNum, poDtlTMsg.poOrdDtlLineNum);
            // QC#25976 ADD 2018/05/09 Start
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.dispPoDtlLineNum, poDtlTMsg.dispPoDtlLineNum);
            // QC#25976 ADD 2018/05/09 End

            // QC#14858(sol#060) MOD START 2017/12/04 K.Kameoka
            // set Item Description from PO. 
            // QC#23616
            if (MDSE_ITEM_TP.TEXT_ITEM.equals(mdseTMsg.mdseItemTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.mdseDescShortTxt, poDtlTMsg.mdseDescShortTxt);
            } else {
                // QC#21354 Start
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poRptMdseTxt, poDtlTMsg.mdseCd);
//                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poRptMdseTxt, poDtlTMsg.aslMdseCd);
                // QC#21354 End
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.mdseDescShortTxt, mdseTMsg.mdseDescShortTxt);
            }
            //ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.mdseDescShortTxt, mdseTMsg.mdseDescShortTxt);
            // QC#14858(sol#060) MOD END   2017/11/04 K.Kameoka

            if (ZYPCommonFunc.hasValue(poDtlTMsg.poDispQty)) {
                BigDecimal poDispQty = poDtlTMsg.poDispQty.getValue();
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poQtyTxt, ZYPFormatUtil.formatNumToSysDisp(poDispQty));
            }
            if (ZYPCommonFunc.hasValue(poDtlTMsg.poDispUomCd)) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poDispUomCd, poDtlTMsg.poDispUomCd);
            }
            if (ZYPCommonFunc.hasValue(poDtlTMsg.entDealNetUnitPrcAmt)) {
                BigDecimal entDealNetUnitPrcAmt = poDtlTMsg.entDealNetUnitPrcAmt.getValue().setScale(AMOUNT_SCALE);
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.thisMthFobCostAmtTxt, ZYPFormatUtil.formatNumToSysDisp(entDealNetUnitPrcAmt));
            }

            if (ZYPCommonFunc.hasValue(poDtlTMsg.entPoDtlDealNetAmt)) {
                BigDecimal entPoDtlDealNetAmt = poDtlTMsg.entPoDtlDealNetAmt.getValue().setScale(AMOUNT_SCALE);
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poAmtTxt, ZYPFormatUtil.formatNumToSysDisp(entPoDtlDealNetAmt));
            }

            if (psnTMsg_poSubmtPsn != null && ZYPCommonFunc.hasValue(psnTMsg_poSubmtPsn.fullPsnNm)) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.chrgAttnNm, psnTMsg_poSubmtPsn.fullPsnNm);
            }

            if (psnTMsg_dpRqstTechToc != null && ZYPCommonFunc.hasValue(psnTMsg_dpRqstTechToc.fullPsnNm)) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.reqByUsrNm, psnTMsg_dpRqstTechToc.fullPsnNm);
            }

            if (ZYPCommonFunc.hasValue(buNm)) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poRptDeptNm, buNm);
            }

            if (poRptPrintDtTxt != null) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poRptPrintDtTxt, ZYPDateUtil.formatEzd8ToDisp(poRptPrintDtTxt));
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdDtTxt, ZYPDateUtil.formatEzd8ToDisp(poRptPrintDtTxt, true));
            }

            // QC#22666 MOD START 2017/11/29 K.Kameoka
            if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToLocNm)) {
                ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.locNm, poDtlTMsg.shipToLocNm);
            }
            // QC#22666 MOD END 2017/11/29 K.Kameoka

            // STR 2016/02/01 R.Shimamoto [V1.5 ADD] Find SHPG_SVC_LVL
            String shpgSvcLvlDescTxt = BLANK;
            if (ZYPCommonFunc.hasValue(poDtlTMsg.shpgSvcLvlCd)) {
                SHPG_SVC_LVLTMsg sslTMsg = findShpgSvcLvl(bizMsg, poDtlTMsg.shpgSvcLvlCd.getValue());
                if (sslTMsg != null && ZYPCommonFunc.hasValue(sslTMsg.shpgSvcLvlDescTxt)) {
                    shpgSvcLvlDescTxt = sslTMsg.shpgSvcLvlDescTxt.getValue();
                }
            }
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdTermTxt_01, PO_ORD_TERM_TXT_01_SUFIX + shpgSvcLvlDescTxt);

            // QC#54292 MOD START 2019/11/07 K.Fukumura
            // <<Get (Carr Code + Carr Name)>>
            String carrCodeAndName = BLANK;
            if (ZYPCommonFunc.hasValue(poDtlTMsg.carrCd)){
                OTBD_CARR_VTMsg outMsg = findCarrName(bizMsg, poDtlTMsg.carrCd.getValue());
                // CarrCd
                if (outMsg != null){
                    // QC#55571 MOD START 2020/01/27
                    // carrCodeAndName = AddCarrCmtText(carrCodeAndName, outMsg.carrCd.getValue());
                    // END QC#55571 MOD START 2020/01/27
                    carrCodeAndName = AddCarrCmtText(carrCodeAndName, outMsg.carrNm.getValue());
                }
               
            }
            carrCodeAndName = AddCarrCmtText(carrCodeAndName, shpgSvcLvlDescTxt);
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.carrCmntTxt, carrCodeAndName);
            // QC#54292 MOD END 2019/11/07 K.Fukumura

            
            // STR 2016/02/01 R.Shimamoto [V1.5 ADD] Find FRT_COND
            String frtCondDescTxt = BLANK;
            if (ZYPCommonFunc.hasValue(poDtlTMsg.frtCondCd)) {
                FRT_CONDTMsg frtTMsg = findFrtCond(bizMsg, poDtlTMsg.frtCondCd.getValue());
                if (frtTMsg != null && ZYPCommonFunc.hasValue(frtTMsg.frtCondDescTxt)) {
                    frtCondDescTxt = frtTMsg.frtCondDescTxt.getValue();
                }
            }
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdTermTxt_02, PO_ORD_TERM_TXT_02_SUFIX + frtCondDescTxt);

            String vndPmtTermDescTxt = BLANK;
            if (poMap.get(DB_COLUMN_DP_VND_PMT_TERM_DESC_TXT) != null) {
                vndPmtTermDescTxt = poMap.get(DB_COLUMN_DP_VND_PMT_TERM_DESC_TXT).toString();
            }
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdTermTxt_03, PO_ORD_TERM_TXT_03_SUFIX + vndPmtTermDescTxt);

            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.shpgSvcLvlDescTxt, shpgSvcLvlDescTxt);
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.frtCondDescTxt, frtCondDescTxt);
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.vndPmtTermDescTxt, vndPmtTermDescTxt);

            String aslMdseCd = poDtlTMsg.aslMdseCd.getValue();
            if (aslMdseCd.length() > 20) {
                // START 2019/03/22 T.Ogura [QC#30565,MOD]
//                aslMdseCd = aslMdseCd.substring(0, 19);
                aslMdseCd = aslMdseCd.substring(0, 20);
                // END   2019/03/22 T.Ogura [QC#30565,MOD]
            }
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.vndSkuCd, aslMdseCd);

            String etaDt = BLANK;
            if (ZYPCommonFunc.hasValue(poDtlTMsg.etaDt)) {
                etaDt = ZYPDateUtil.formatEzd8ToDisp(poDtlTMsg.etaDt.getValue(), true);
            }
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdRqstDelyDtTxt, etaDt);
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.uomCd, poDtlTMsg.poDispUomCd);

            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.reqByUsrId, (String) poMap.get(DB_COLUMN_DP_RQST_TECH_TOC_CD));
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.prntVndCd, (String) poMap.get(DB_COLUMN_DP_PRNT_VND_CD));

            if (ZYPCommonFunc.hasValue(poDtlTMsg.ccyCd)) {
                CCYTMsg ccy = findCcy(bizMsg, poDtlTMsg.ccyCd.getValue());
                if (ccy != null) {
                    ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.ccySgnTxt, ccy.ccySgnTxt);
                }
            }

            /*
             * Temporary Modify for Build AP1!!!!!!!!!!!!!!!!!!!!!!!!!
             * Please check Revision 1
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.mdseCd,
             * poDtlTMsg.mdseCd);
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg
             * .poOrdRqstDelyDt, poDtlTMsg.etaDt); // 2013/04/04
             * QC1003 T.Tomita Update Start //
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.uomCd,
             * poDtlTMsg.custUomCd);
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.uomCd,
             * VND_UOM.EACH); if (vndProdXrefTMsg != null &&
             * ZYPCommonFunc
             * .hasValue(vndProdXrefTMsg.vndUomCd.getValue())) {
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.uomCd,
             * vndProdXrefTMsg.vndUomCd); } // 2013/04/04 QC1003
             * T.Tomita Update End
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.ccyCd,
             * poDtlTMsg.ccyCd); // 2013/04/08 QC1003 T.Tomita Update
             * Start
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poQty,
             * poDtlTMsg.poQty); if (vndProdXrefTMsg != null) { if
             * (ZYPCommonFunc
             * .hasValue(vndProdXrefTMsg.vndUomQty.getValue()) &&
             * BigDecimal
             * .ZERO.compareTo(vndProdXrefTMsg.vndUomQty.getValue())
             * != 0 &&
             * ZYPCommonFunc.hasValue(vndProdXrefTMsg.baseOrdQty
             * .getValue()) &&
             * BigDecimal.ZERO.compareTo(vndProdXrefTMsg
             * .baseOrdQty.getValue()) != 0) { BigDecimal poQty =
             * poDtlTMsg.poQty.getValue(); BigDecimal baseOrdQty =
             * vndProdXrefTMsg.baseOrdQty.getValue(); BigDecimal
             * vndUomQty = vndProdXrefTMsg.vndUomQty.getValue();
             * BigDecimal calcQty = poQty.divide(baseOrdQty, 3,
             * RoundingMode.UP).multiply(vndUomQty);
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poQty,
             * calcQty.setScale(0, RoundingMode.UP)); } } //
             * 2013/04/08 QC1003 T.Tomita Update End // STR 2013/02/15
             * T.Tomita [REF01 UPD] //
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg
             * .thisMthFobCostAmt, dsPoDtlTMsg.poFrmUnitPrcAmt);
             * ZYPEZDItemValueSetter
             * .setValue(poRptWrkTMsg.thisMthFobCostAmt,
             * poDtlTMsg.thisMthFobCostAmt); //
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poAmt,
             * poDtlTMsg
             * .poQty.getValue().multiply(dsPoDtlTMsg.poFrmUnitPrcAmt
             * .getValue()));
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poAmt,
             * poDtlTMsg
             * .poQty.getValue().multiply(poDtlTMsg.thisMthFobCostAmt
             * .getValue())); // END 2013/02/15 T.Tomita [REF01 UPD]
             * ZYPEZDItemValueSetter
             * .setValue(poRptWrkTMsg.poOrdDtlCmntTxt,
             * poDtlTMsg.poOrdDtlCmntTxt.getValue()); if
             * (vndProdXrefTMsg != null) {
             * ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.vndSkuCd,
             * vndProdXrefTMsg.vndPoSkuCd.getValue()); } Temporary
             * Modify for Build AP1!!!!!!!!!!!!!!!!!!!!!!!!! Please
             * check Revision 1
             */

            // STR 2016/02/01 R.Shimamoto [V1.5 ADD]
            editPoComment(bizMsg, poDtlTMsg, poRptWrkTMsg);

            String poOrdDtlCmntTxt = BLANK;
            if (ZYPCommonFunc.hasValue(poDtlTMsg.poOrdDtlCmntTxt)) {
                poOrdDtlCmntTxt = poDtlTMsg.poOrdDtlCmntTxt.getValue();
            }
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdDtlCmntRptTxt, poOrdDtlCmntTxt);

            //QC#23929 ADD START
            //If print open line only flag is checked, closed line is excepted.
            if (ZYPCommonFunc.hasValue(bizMsg.openFlg) //
                    && ZYPConstant.FLG_ON_Y.equals(bizMsg.openFlg.getValue())) {
                BigDecimal poTotalAmount = getPoTotalAmountforOpen(bizMsg, poDtlTMsg, poRptWrkTMsg);
                if (poTotalAmount != null) {
                    poTotalAmount = poTotalAmount.setScale(AMOUNT_SCALE);
                    ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poTotAmtTxt, ZYPFormatUtil.formatNumToSysDisp(poTotalAmount));
                }
            } else {
                BigDecimal poTotalAmount = getPoTotalAmount(bizMsg, poDtlTMsg, poRptWrkTMsg);
                if (poTotalAmount != null) {
                    poTotalAmount = poTotalAmount.setScale(AMOUNT_SCALE);
                    ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poTotAmtTxt, ZYPFormatUtil.formatNumToSysDisp(poTotalAmount));
                }
            }
            //QC#23929 ADD END

            // END 2016/02/01 R.Shimamoto [V1.5 ADD]

            // Calculation page Number
            pdfLineNum = setPdfPageNumber(poRptWrkTMsg, pageCalcConstMap, pdfLineNum);
            // page Number header copy
            ZYPEZDItemValueSetter.setValue(header.poRptPgNum, poRptWrkTMsg.poRptPgNum);

            // STR 2013/02/06 T.Tomita [REF01 UPD]
            S21ApiTBLAccessor.insert(poRptWrkTMsg);
            // END 2013/02/06 T.Tomita [REF01 UPD]

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRptWrkTMsg.getReturnCode())) {
                this.addMsgId(msgMap, NPZM0086E);
                return;
            }
        }
        // create PO_RPT_INFO_WRK(NOTE)
        createPoRptInfoWrk(bizMsg, msgMap, header, PO_RPT_INFO_TP.NOTE);

        // create PO_RPT_INFO_WRK(TERMS_AND_CONDITIONS)
        createPoRptInfoWrk(bizMsg, msgMap, header, PO_RPT_INFO_TP.TERMS_AND_CONDITIONS);
    }

    // END 2012/11/19 T.Tomita [V1.1 MOD]

    // STR 2012/11/19 T.Tomita [V1.1 MOD]
    private void editShipToAddressFromPoDtl(PO_DTLTMsg poDtlTMsg, SHIP_TO_CUSTTMsg shipToCustTMsg, PO_RPT_WRKTMsg poRptTMsg) {

        ZYPEZDItemValueSetter.setValue(poRptTMsg.attnNm, poDtlTMsg.shipToLocNm);

        //QC#21354 START
        //set shipToAddlLocNm
        ZYPEZDItemValueSetter.setValue(poRptTMsg.shipToAddlLocNm, poDtlTMsg.shipToAddlLocNm);
        //QC#21354 END
        
        // ship to address list
        List<String> addList = new ArrayList<String>();

        // QC#20246 ADD START
        // check FirstLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.ctacPsnNm)) {

            String ctacPsnNm = "ATTN: " + poDtlTMsg.ctacPsnNm.getValue();

            // QC#20246 MOD START 2017/10/20 START
            if (ctacPsnNm.length() > FIRST_LINE_ADDR_LENGTH_MAX) {
                ctacPsnNm = ctacPsnNm.substring(0, FIRST_LINE_ADDR_LENGTH_MAX);
            }
            addList.add(ctacPsnNm);
            // addList.add("ATTN: " + poDtlTMsg.ctacPsnNm.getValue());
            // QC#20246 MOD START 2017/10/20 START
        }
        // QC#20246 ADD END

        // check FirstLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToFirstLineAddr) ) {
            addList.add(poDtlTMsg.shipToFirstLineAddr.getValue());
        }
        // check ScdLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToScdLineAddr) ) {
            addList.add(poDtlTMsg.shipToScdLineAddr.getValue());
        }
        // check ThirdLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToThirdLineAddr) ) {
            addList.add(poDtlTMsg.shipToThirdLineAddr.getValue());
        }
        // check FrthLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToFrthLineAddr) ) {
            addList.add(poDtlTMsg.shipToFrthLineAddr.getValue());
        }
        // check FifthLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToCtyAddr) || 
                ZYPCommonFunc.hasValue(poDtlTMsg.shipToStCd) || 
                ZYPCommonFunc.hasValue(poDtlTMsg.shipToPostCd) || 
                ZYPCommonFunc.hasValue(poDtlTMsg.shipToCtyAddr)) {
            String addr5 = 
                poDtlTMsg.shipToCtyAddr.getValue() + COMMA + BLANK_ONE + 
                poDtlTMsg.shipToStCd.getValue() + BLANK_ONE + 
                poDtlTMsg.shipToPostCd.getValue();
            addList.add(addr5);
        }
        // check SixthLineAddr
        if (ZYPCommonFunc.hasValue(poDtlTMsg.shipToCtryCd) ) {
            addList.add(poDtlTMsg.shipToCtryCd.getValue());
        }

        int addrLine = 0;
        // set FirstLineAddr
        if (addList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.firstLineAddr, addList.get(addrLine));
            addrLine++;
        }
        // set ScdLineAddr
        if (addList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.scdLineAddr, addList.get(addrLine));
            addrLine++;
        }
        // set ThirdLineAddr
        if (addList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.thirdLineAddr, addList.get(addrLine));
            addrLine++;
        }
        // set FrthLineAddr
        if (addList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.frthLineAddr, addList.get(addrLine));
            addrLine++;
        }
        // set FifthLineAddr
        if (addList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.fifthLineAddr, addList.get(addrLine));
            addrLine++;
        }
        // set SixthLineAddr
        if (addList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.sixthLineAddr, addList.get(addrLine));
        }
    }

    // END 2012/11/19 T.Tomita [V1.1 MOD]

    // STR 2012/11/19 T.Tomita [V1.1 MOD]
    private void editVndAddress(VNDTMsg vndTMsg, PO_RPT_WRKTMsg poRptTMsg) {

        // vender address list
        List<String> vndAddList = new ArrayList<String>();
        // check FirstLineAddr
        if (ZYPCommonFunc.hasValue(vndTMsg.firstLineAddr) ) {
            vndAddList.add(vndTMsg.firstLineAddr.getValue());
        }
        // check ScdLineAddr
        if (ZYPCommonFunc.hasValue(vndTMsg.scdLineAddr) ) {
            vndAddList.add(vndTMsg.scdLineAddr.getValue());
        }
        // check ThirdLineAddr
        if (ZYPCommonFunc.hasValue(vndTMsg.thirdLineAddr) ) {
            vndAddList.add(vndTMsg.thirdLineAddr.getValue());
        }
        // check FrthLineAddr
        if (ZYPCommonFunc.hasValue(vndTMsg.frthLineAddr) ) {
            vndAddList.add(vndTMsg.frthLineAddr.getValue());
        }
        // check FifthLineAddr
        if (ZYPCommonFunc.hasValue(vndTMsg.ctyAddr) || 
                ZYPCommonFunc.hasValue(vndTMsg.stCd) || 
                ZYPCommonFunc.hasValue(vndTMsg.postCd) || 
                ZYPCommonFunc.hasValue(vndTMsg.ctryCd)) {
            String addr5 = 
                vndTMsg.ctyAddr.getValue() + BLANK_ONE + 
                vndTMsg.stCd.getValue() + BLANK_ONE + 
                vndTMsg.postCd.getValue() + BLANK_ONE + 
                vndTMsg.ctryCd.getValue();
            vndAddList.add(addr5);
        }

        int addrLine = 0;
        // set FirstLineAddr
        if (vndAddList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.vndFirstLineAddr, vndAddList.get(addrLine));
            addrLine++;
        }
        // set ScdLineAddr
        if (vndAddList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.vndScdLineAddr, vndAddList.get(addrLine));
            addrLine++;
        }
        // set ThirdLineAddr
        if (vndAddList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.vndThirdLineAddr, vndAddList.get(addrLine));
            addrLine++;
        }
        // set FrthLineAddr
        if (vndAddList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.vndFrthLineAddr, vndAddList.get(addrLine));
            addrLine++;
        }
        // set FifthLineAddr
        if (vndAddList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(poRptTMsg.vndFifthLineAddr, vndAddList.get(addrLine));
            addrLine++;
        }
    }

    // END 2012/11/19 T.Tomita [V1.1 MOD]

    /**
     * <pre>
     * Add Message ID to MessageMap, and print debug log.
     * </pre>
     * @param msgMap Message Manager
     * @param msgId String setting value for Message ID
     * @throws none
     */
    private void addMsgId(S21ApiMessageMap msgMap, String msgId) {

        msgMap.addXxMsgId(msgId);

        printDebugLog("setMsgId:" + msgId);
    }

    /**
     * <pre>
     * Print debug log.
     * </pre>
     * @param debugMsg
     */
    private void printDebugLog(String debugMsg) {
        if (EZDDebugOutput.isDebug()) {
            EZDDebugOutput.println(NPZC007001Constant.CST_DEBUG_MSG_LVL, debugMsg, this);
        }
    }

    // STR 2012/11/19 T.Tomita [V1.1 ADD]
    /**
     * Get SHIP_TO_CUST Data
     * @param pMsg Parameter
     * @param msgMap S21ApiMessageMap
     * @return SHIP_TO_CUSTTMsg
     */
    private SHIP_TO_CUSTTMsg findShipToCustData(NPZC007001PMsg pMsg, S21ApiMessageMap msgMap) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("shipToCustCd01", pMsg.whCd.getValue());

        // STR 2013/02/06 T.Tomita [REF01 UPD]
        SHIP_TO_CUSTTMsgArray resultList = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        // END 2013/02/06 T.Tomita [REF01 UPD]
        SHIP_TO_CUSTTMsg rtnMsg = null;

        // Check SHIP_TO_CUST Data
        if (resultList.getValidCount() == 0) {
            return rtnMsg;
        }

        rtnMsg = (SHIP_TO_CUSTTMsg) resultList.get(0);
        return rtnMsg;
    }

    // STR 2016/02/01 R.Shimamoto [V1.5 DEL]
    // /**
    // * Get VND_PROD_XREF Data
    // * @param pMsg NPZC007001PMsg
    // * @param poDtlTMsg PO_DTLTMsg
    // * @return VND_PROD_XREFTMsg
    // */
    // private VND_PROD_XREFTMsg getVndProdXrefData(NPZC007001PMsg
    // pMsg, PO_DTLTMsg poDtlTMsg) {
    // VND_PROD_XREFTMsg inMsg = new VND_PROD_XREFTMsg();
    // inMsg.setSQLID("001");
    // inMsg.setConditionValue("glblCmpyCd01",
    // pMsg.glblCmpyCd.getValue());
    // inMsg.setConditionValue("vndCd01", pMsg.vndCd.getValue());
    // inMsg.setConditionValue("mdseCd01",
    // poDtlTMsg.mdseCd.getValue());
    //
    // // STR 2013/02/06 T.Tomita [REF01 UPD]
    // VND_PROD_XREFTMsgArray resultList = (VND_PROD_XREFTMsgArray)
    // S21ApiTBLAccessor.findByCondition(inMsg);
    // // END 2013/02/06 T.Tomita [REF01 UPD]
    // VND_PROD_XREFTMsg rtnMsg = null;
    // if (resultList.getValidCount() > 0) {
    // rtnMsg = (VND_PROD_XREFTMsg) resultList.get(0);
    // }
    //
    // return rtnMsg;
    // }
    // END 2016/02/01 R.Shimamoto [V1.5 DEL]

    // STR 2016/02/01 R.Shimamoto [V1.5]
    // STR 2013/02/15 T.Tomita [REF01 DEL]

    /**
     * Get MDSE Data
     * @param poDtlTMsg PO_DTLTMsg
     * @return MDSETMsg
     */
    private MDSETMsg getMdseTMsg(PO_DTLTMsg poDtlTMsg) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, poDtlTMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, poDtlTMsg.mdseCd.getValue());

        return (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    // END 2013/02/15 T.Tomita [REF01 DEL]
    // END 2016/02/01 R.Shimamoto [V1.5]

    // STR 2016/02/01 R.Shimamoto [V1.5 ADD]
    private void editPoComment(NPZC007001PMsg bizMsg, PO_DTLTMsg poDtlTMsg, PO_RPT_WRKTMsg poRptWrkTMsg) {

        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String poOrdNum = poDtlTMsg.poOrdNum.getValue();

        List<PO_MSGTMsg> spMsgList = NPXC001001PoMsg.getPoMsg(glblCmpyCd, PO_MSG_TP.SPECIAL_INSTRUCTIONS, poOrdNum, null);

        // 05/08/2017 QC#25973 Start
        //////08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
        //////String spMsgAll = NPXC001001PoMsg.concatenatePoMsg(spMsgList);
        ////String spMsgAll = concatenatePoMsg(spMsgList);
        //////08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
        String spMsgAll = NPXC001001PoMsg.concatenatePoMsg(spMsgList);
        // 05/08/2017 QC#25973 End

        List<String> msgList = new ArrayList<String>();
        String spMsgWork = spMsgAll;
        String spMsg;

        // message split 01
        // START 2019/03/22 T.Ogura [QC#30565,MOD]
//        if (ZYPCommonFunc.hasValue(spMsgWork) && PO_COMMENT_LENGTH_END <= spMsgWork.length()) {
        if (ZYPCommonFunc.hasValue(spMsgWork) && PO_COMMENT_LENGTH_END < spMsgWork.length()) {
        // END   2019/03/22 T.Ogura [QC#30565,MOD]
            spMsg = spMsgWork.substring(PO_COMMENT_LENGTH_STR, PO_COMMENT_LENGTH_END);
            spMsgWork = spMsgWork.substring(PO_COMMENT_LENGTH_END);
        } else {
            spMsg = spMsgWork;
            //08/08/2017 CITS S.Endo Add Sol#035(QC#18108) START
            spMsgWork = "";
            //08/08/2017 CITS S.Endo Add Sol#035(QC#18108) END
        }
        msgList.add(spMsg);

        //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
        // message split 02
        //if (ZYPCommonFunc.hasValue(spMsgWork) && PO_COMMENT_LENGTH_END <= spMsgWork.length()) {
        if (ZYPCommonFunc.hasValue(spMsgWork)) {
            // START 2019/03/22 T.Ogura [QC#30565,MOD]
//            if (PO_COMMENT_LENGTH_END <= spMsgWork.length()) {
            if (PO_COMMENT_LENGTH_END < spMsgWork.length()) {
            // END   2019/03/22 T.Ogura [QC#30565,MOD]
                spMsg = spMsgWork.substring(PO_COMMENT_LENGTH_STR, PO_COMMENT_LENGTH_END);
            } else {
                spMsg = spMsgWork;
            }
            msgList.add(spMsg);
            //spMsgWork = spMsgWork.substring(PO_COMMENT_LENGTH_END);
        }

        // message split 03
//      if (ZYPCommonFunc.hasValue(spMsgWork) && PO_COMMENT_LENGTH_END <= spMsgWork.length()) {
//          spMsg = spMsgWork.substring(PO_COMMENT_LENGTH_STR, PO_COMMENT_LENGTH_END);
//          msgList.add(spMsg);
//          spMsgWork = spMsgWork.substring(PO_COMMENT_LENGTH_END);
//      }
        //08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
        int cnt = 0;
        if (cnt < msgList.size()) {
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdCmntTxt, msgList.get(cnt));
            cnt++;
        }
        if (cnt < msgList.size()) {
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdCmntTxt_02, msgList.get(cnt));
            cnt++;
        }
        //08/08/2017 CITS S.Endo Del Sol#035(QC#18108) START
//      if (cnt < msgList.size()) {
//          ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdCmntTxt_03, msgList.get(cnt));
//          cnt++;
//        }
        //08/08/2017 CITS S.Endo Del Sol#035(QC#18108) END
        //08/08/2017 CITS S.Endo Add Sol#035(QC#18108) START
        List<PO_MSGTMsg> shprMsgList = NPXC001001PoMsg.getPoMsg(glblCmpyCd, PO_MSG_TP.SHIPPER_NOTE, poOrdNum, null);
        // 05/08/2017 QC#25973 Start
        // String shprMsgAll = concatenatePoMsg(shprMsgList);
        String shprMsgAll = NPXC001001PoMsg.concatenatePoMsg(shprMsgList);
        // 05/08/2017 QC#25973 Start
        String shprMsgWork = shprMsgAll;
        String shprMsg;
        cnt = 0;
        msgList.clear();

        if (ZYPCommonFunc.hasValue(shprMsgWork) && PO_COMMENT_LENGTH_END <= shprMsgWork.length()) {
            shprMsg = shprMsgWork.substring(PO_COMMENT_LENGTH_STR, PO_COMMENT_LENGTH_END);
            shprMsgWork = shprMsgWork.substring(PO_COMMENT_LENGTH_END);
        } else {
            shprMsg = shprMsgWork;
            shprMsgWork = "";
        }
        msgList.add(shprMsg);
        // message split 04
        if (ZYPCommonFunc.hasValue(shprMsgWork)) {
            if (PO_COMMENT_LENGTH_END <= shprMsgWork.length()) {
                shprMsg = shprMsgWork.substring(PO_COMMENT_LENGTH_STR, PO_COMMENT_LENGTH_END);
            } else {
                shprMsg = shprMsgWork;
            }
            msgList.add(shprMsg);
        }
        if (cnt < msgList.size()) {
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdCmntTxt_03, msgList.get(cnt));
            cnt++;
        }
        if (cnt < msgList.size()) {
            ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poOrdCmntTxt_04, msgList.get(cnt));
            cnt++;
        }
        //08/08/2017 CITS S.Endo Add Sol#035(QC#18108) END
    }

    private BigDecimal getPoTotalAmount(NPZC007001PMsg bizMsg, PO_DTLTMsg poDtlTMsg, PO_RPT_WRKTMsg poRptWrkTMsg) {

        BigDecimal result = new BigDecimal(0);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_PO_ORD_NUM, bizMsg.poOrdNum.getValue());
        ssmParam.put(DB_PARAM_PO_STS_CD, PO_STS.CANCELLED);

        if (NPZC007001Query.getInstance().getPoTotalAmount(ssmParam) != null) {
            result = NPZC007001Query.getInstance().getPoTotalAmount(ssmParam);
        }

        return result;

    }

    private S21_PSN_VTMsg findS21PsnV(NPZC007001PMsg bizMsg, String poSubmtPsnCd) {
        S21_PSN_VTMsg inMsg = new S21_PSN_VTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("psnCd01", poSubmtPsnCd);

        S21_PSN_VTMsgArray resultList = (S21_PSN_VTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        S21_PSN_VTMsg psnTMsg = null;
        if (resultList.getValidCount() == 0) {
            return psnTMsg;
        }

        psnTMsg = (S21_PSN_VTMsg) resultList.get(0);

        return psnTMsg;
    }

    private SHPG_SVC_LVLTMsg findShpgSvcLvl(NPZC007001PMsg bizMsg, String shpgSvcLvlCd) {
        SHPG_SVC_LVLTMsg inMsg = new SHPG_SVC_LVLTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("shpgSvcLvlCd01", shpgSvcLvlCd);

        SHPG_SVC_LVLTMsgArray resultList = (SHPG_SVC_LVLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        SHPG_SVC_LVLTMsg sslTMsg = null;
        if (resultList.getValidCount() == 0) {
            return sslTMsg;
        }

        sslTMsg = (SHPG_SVC_LVLTMsg) resultList.get(0);

        return sslTMsg;
    }

    // QC#54292 MOD START 2019/11/07 K.Fukumura
    private OTBD_CARR_VTMsg findCarrName(NPZC007001PMsg bizMsg, String carrCd) {
        OTBD_CARR_VTMsg inMsg = new OTBD_CARR_VTMsg();
        OTBD_CARR_VTMsg outMsg = new OTBD_CARR_VTMsg();

        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("carrCd01", carrCd);

        OTBD_CARR_VTMsgArray resultList = (OTBD_CARR_VTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultList.getValidCount() == 0) {
            return outMsg;
        }

        outMsg = (OTBD_CARR_VTMsg) resultList.get(0);

        return outMsg;
    }
     /**
     * AddCarrCmtText
     * @param carrCmtText
	 * @param addValue
     * @return
     */
    private String AddCarrCmtText(String carrCmtText, String addValue){
        String crlf = System.getProperty("line.separator");
        String retVal = null;
        if (ZYPCommonFunc.hasValue(carrCmtText)){
            retVal = carrCmtText + crlf + addValue;
        }
        else {
            retVal = addValue;
        }
        return retVal;
    }
    // QC#54292 MOD END 2019/11/07 K.Fukumura

    private FRT_CONDTMsg findFrtCond(NPZC007001PMsg bizMsg, String frtCondCd) {
        FRT_CONDTMsg inMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.frtCondCd, frtCondCd);

        FRT_CONDTMsg rtnTMsg = (FRT_CONDTMsg) EZDTBLAccessor.findByKey(inMsg);

        return rtnTMsg;
    }

    // END 2016/02/01 R.Shimamoto [V1.5 ADD]

    private CCYTMsg findCcy(NPZC007001PMsg bizMsg, String ccyCd) {
        CCYTMsg inMsg = new CCYTMsg();
        inMsg.setSQLID("800");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("ccyCd01", ccyCd);

        CCYTMsgArray resultList = (CCYTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        CCYTMsg ccyTMsg = null;
        if (resultList.getValidCount() == 0) {
            return ccyTMsg;
        }

        ccyTMsg = (CCYTMsg) resultList.get(0);

        return ccyTMsg;
    }

    /**
     * getPrntVndNm
     * @param vndTMsg
     * @return
     */
    private String getPrntVndNm(VNDTMsg vndTMsg) {

        String prntVndNm = "";
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", vndTMsg.glblCmpyCd.getValue());
        ssmParam.put("vndCd", vndTMsg.vndCd.getValue());

        prntVndNm = NPZC007001Query.getInstance().getPrntVndNm(ssmParam);

        return prntVndNm;

    }

    private void createPoRptInfoWrk(NPZC007001PMsg bizMsg, S21ApiMessageMap msgMap, PO_RPT_WRKTMsg poRptWrkTMsg, String poRptInfoTp) {

        List<PO_RPT_INFOTMsg> poRptInfoList = null;
        if (PO_RPT_INFO_TP.NOTE.equals(poRptInfoTp)) {
            poRptInfoList = poRptInfoNTList;
        } else {
            poRptInfoList = poRptInfoTCList;
        }

        for (int i = 0; i < poRptInfoList.size(); i++) {
            PO_RPT_INFOTMsg poRptInfo = poRptInfoList.get(i);

            PO_RPT_INFO_WRKTMsg poRptInfoWrk = new PO_RPT_INFO_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.poRptInfoWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_RPT_INFO_WRK_SQ));
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.poRptPrintRqstSq, poRptWrkTMsg.poRptPrintRqstSq);
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.poRptInfoTpCd, poRptInfo.poRptInfoTpCd);
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.poRptInfoNum, poRptInfo.poRptInfoNum);
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.ttlValTxt, poRptInfo.ttlValTxt);
            ZYPEZDItemValueSetter.setValue(poRptInfoWrk.descValTxt, poRptInfo.descValTxt);

            S21ApiTBLAccessor.create(poRptInfoWrk);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRptInfoWrk.getReturnCode())) {
                this.addMsgId(msgMap, NPZM0086E);
                return;
            }
        }
    }

    private BigDecimal setPdfPageNumber(PO_RPT_WRKTMsg poRptWrkTMsg, Map<String, BigDecimal> calcMap, BigDecimal curLine) {

        BigDecimal maxLineNum = null;
        if (BigDecimal.ONE.compareTo(poRptWrkTMsg.poRptPgNum.getValue()) == 0) {
            maxLineNum= calcMap.get(NPAF0010_LINE_NUM_OF_1ST_PAGE);
        } else {
            maxLineNum= calcMap.get(NPAF0010_LINE_NUM_OF_2ND_PAGE);
        }
        BigDecimal curPageNum = poRptWrkTMsg.poRptPgNum.getValue();

        // QC#25820 2018/06/13 Start
//        // calculation add line
//        BigDecimal numOfDescLtr = calcMap.get(NPAF0010_NUM_OF_DESC_LTR);
//        String lineDescTxt = poRptWrkTMsg.mdseDescShortTxt.getValue();
//        BigDecimal lineDescLength = new BigDecimal(lineDescTxt.length());
//        BigDecimal addLine = lineDescLength.divide(numOfDescLtr, 0, BigDecimal.ROUND_UP);
//        // add line
//        BigDecimal afterLine = curLine.add(addLine);
        BigDecimal addLine = BigDecimal.ONE;
        BigDecimal afterLine = curLine.add(addLine);
        // QC#25820 2018/06/13 Start
        
        // Page break decision
        BigDecimal returnLine = null;
        BigDecimal afterPageNum = null;
        if (maxLineNum.compareTo(afterLine) < 0) {
            afterPageNum = curPageNum.add(BigDecimal.ONE);
            returnLine = addLine;
        } else {
            afterPageNum = curPageNum;
            returnLine = afterLine;
        }
        ZYPEZDItemValueSetter.setValue(poRptWrkTMsg.poRptPgNum, afterPageNum);

        return returnLine;
    }

    /**
     * getDepartment
     * @param NPZC007001PMsg bizMsg
     * @param Map<String, Object> poMap
     * @return department
     */
    private String getDepartment(NPZC007001PMsg bizMsg, Map<String, Object> poMap) {

        String department = "";
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_ORG_STRU_TP_CD, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put(DB_PARAM_GNRN_TP_CD_01, GNRN_TP.CURRENT);
        ssmParam.put(DB_PARAM_GNRN_TP_CD_02, GNRN_TP.FUTURE);
        ssmParam.put(DB_PARAM_PSN_CD, (String) poMap.get(DB_COLUMN_P_PO_SUBMT_PSN_CD));
        ssmParam.put(DB_PARAM_PROC_DT, bizMsg.procDt.getValue());

        department = NPZC007001Query.getInstance().getDepartment(ssmParam);

        return department;

    }

    //08/08/2017 CITS S.Endo Add Sol#035(QC#18108) START
    private String concatenatePoMsg(List<PO_MSGTMsg> poMsgList) {
        if (poMsgList == null || poMsgList.isEmpty()) {
            return null;
        }
        BigDecimal maxBytes = NPXC001001PoMsg.getPoMsgMaxBytes();
        int length = maxBytes.intValueExact();
        StringBuilder buf = new StringBuilder(length);
        String segment;
        for (PO_MSGTMsg tMsg : poMsgList) {
            segment = tMsg.poMsgTxt.getValue();
            /**
             * See NPXC001001PoMsg#splitPoMsg
             */
            buf.append(segment.substring(0, segment.length()));
        }
        return buf.toString();
    }
    //08/08/2017 CITS S.Endo Add Sol#035(QC#18108) END
    
    //QC#23929 ADD START
    private BigDecimal getPoTotalAmountforOpen(NPZC007001PMsg bizMsg, PO_DTLTMsg poDtlTMsg, PO_RPT_WRKTMsg poRptWrkTMsg) {

        BigDecimal result = new BigDecimal(0);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_PO_ORD_NUM, bizMsg.poOrdNum.getValue());
        //QC#25841 MOD START
        String[] poLineStsCdList =  {PO_LINE_STS.CLOSED,  PO_LINE_STS.CANCELLED};
        ssmParam.put(DB_PARAM_PO_LINE_STS_CD, poLineStsCdList);
//        String[] poStsCdList =  {PO_STS.CLOSED,  PO_STS.CANCELLED};
//        ssmParam.put(DB_PARAM_PO_STS_CD, poStsCdList);
        //QC#25841 MOD END

        if (NPZC007001Query.getInstance().getPoTotalAmountforOpen(ssmParam) != null) {
            result = NPZC007001Query.getInstance().getPoTotalAmountforOpen(ssmParam);
        }

        return result;
    }
    //QC#23929 ADD END
    
    //QC#21354 ADD START
    /**
     * getByrTelNum
     * @param vndTMsg
     * @return
     */
    private String getByrTelNum(NPZC007001PMsg bizMsg, String poSubmtPsnCd) {

        String byrTelNum = "";
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("poSubmtPsnCd", poSubmtPsnCd);

        byrTelNum = NPZC007001Query.getInstance().getByrTelNum(ssmParam);

        return byrTelNum;

    }
    //QC#21354 ADD END

}
