/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC201001;

import java.math.BigDecimal;
import java.util.List;

import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DRCT_SHIP_TPTMsg;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INVTY_LOC_VTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.INBD_VISTMsg;
import business.db.LOC_TPTMsg;
import business.db.MDSETMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_RCV_DTLTMsg;
import business.db.PO_RCV_HDRTMsg;
import business.db.PO_RCV_MSGTMsg;
import business.db.RMNF_ORDTMsg;
import business.db.RMNF_ORD_DTLTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.RTL_WHTMsg;
import business.db.RWS_HDRTMsg;
import business.db.RWS_HDRTMsgArray;
import business.db.SCE_ORD_TPTMsg;
import business.db.SHPG_ORDTMsg;
import business.db.SHPG_ORD_DTLTMsg;
import business.db.STK_STSTMsg;
import business.db.SYS_SRCTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.WH_SCHDTMsg;
import business.db.WRK_ORD_DTLTMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NPZC109001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NLX.NLXC017001.NLXCMsgHelper;
import com.canon.cusa.s21.common.NLX.NLXC025001.NLXC025001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_VIS_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RWS_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VIS_LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;


/**
 * <pre>
 * PO Receiving API
 * Date          Company     Name          Create/Update     Defect No
 * ----------------------------------------------------------------------
 * 06/28/2009    Fujitsu     A.Akabane     Create            N/A
 * 10/16/2009    Fujitsu     A.Akabane     Update            760
 * 02/19/2010    Fujistu     T.Motoyama    Update            WorkShop RQ233-237
 * 03/18/2010    Fujistu     S.Uehara      Update            3641,4824
 * 06/22/2010    CSAI        D.Fukaya      Update            5015
 * 12/28/2012    CSAI        K.Lee         Update            #141
 * 01/07/2013    Hitachi     T.Kanasaka    Update            WDS#145
 * 04/18/2013    Hitachi     K.Kasai       Update            Defect No.1030
 * 05/07/2013    Hitachi     Y.Igarashi    Update            QC1092
 * 07/17/2013    Hitachi     A.Kohinata    Update            QC1234
 * 07/18/2013    Hitachi     T.Arakawa     Update            QC1403
 * 07/25/2013    Hitachi     A.Kohinata    Update            QC1388
 * 09/30/2015    Hitachi     Y.Tsuchimoto  Update            CSA(9.1.10, 9.7.1, 9.11.12)
 * 12/16/2015    Fujitsu     Y.Taoka       Update            CSA UNIT TEST#130
 * 03/23/2016    CITS        T.Tokutomi    Update            QC#5905
 * 04/29/2016    CSAI        Y.Imazu       Update            QC#5905
 * 06/24/2016    CSAI        Y.Imazu       Update            QC#10596
 * 03/02/2017    CITS        T.Hakodate    Update            QC#13811
 * 07/11/2017    CITS        K.Ogino       Update            QC#19835
 * 12/13/2017    CITS        T.Wada        Update            QC#23029
 * 02/05/2021    CITS        A.Marte       Update            QC#58256
 *</pre>
 */

public class NLZC201001 extends S21ApiCommonBase {

    /* */
    private static final String ONBATCH_TYPE = "ONBATCH_TYPE";

    /* */
    private static final String NLZM1001E = "NLZM1001E";

    /* */
    private static final String NLZM1020E = "NLZM1020E";

    /* */
    private static final String NLZM1021E = "NLZM1021E";

    /* */
    private static final String NLZM1022E = "NLZM1022E";

    /* */
    private static final String NLZM1023E = "NLZM1023E";

    /* */
    private static final String NLZM1024E = "NLZM1024E";

    /* */
    private static final String NLZM1025E = "NLZM1025E";

    /* */
    private static final String NLZM1026E = "NLZM1026E";

    /* */
    private static final String NLZM1027E = "NLZM1027E";

    /* */
    private static final String NLZM1028E = "NLZM1028E";

    /* */
    private static final String NLZM1029E = "NLZM1029E";

    /* */
    private static final String NLZM1035E = "NLZM1035E";

    /* */
    private static final String NLZM1039E = "NLZM1039E";

    /* */
    private static final String NLZM2016E = "NLZM2016E";

    /* */
    private static final String NLZM2017E = "NLZM2017E";

    /* */
    private static final String NLZM2022E = "NLZM2022E";

    /* */
    private static final String NLAM1001E = "NLAM1001E";

    /* */
    private static final String NLAM1006E = "NLAM1006E";

    /* */
    private static final String NLAM1013E = "NLAM1013E";

    /* */
    private static final String NLAM1131E = "NLAM1131E";

    /* */
    private static final String NLAM1133E = "NLAM1133E";

    /* */
    private static final String NLAM1138E = "NLAM1138E";

    //START 2013/04/18 K.Kasai [QC1030,ADD]
    /* An error occurred in POYO Update API. */
    private static final String NPZM0132E = "NPZM0132E";
    //END 2013/04/18 K.Kasai [QC1030,ADD]

    /* */
    private static final String CUST_PO_NUM = "CUST_PO_NUM";

    /* */
    private static final String DOM_INV_NUM = "DOM_INV_NUM";

    /* */
    private static final String DRCT_SHIP_TP_CD = "DRCT_SHIP_TP_CD";

    /* */
    private static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /* */
    private static final String SYS_SRC_CD = "SYS_SRC_CD";

    /* */
    private static final String INBD_OTBD_CD = "INBD_OTBD_CD";

    /* */
    private static final String INBD_VIS_PK = "INBD_VIS_PK";

    /* */
    private static final String WH_SCHD_PK = "WH_SCHD_PK";

    /* */
    private static final String LOC_TP_CD = "LOC_TP_CD";

    /* */
    private static final String MDSE_CD = "MDSE_CD";

    /* */
    private static final String ORD_INFO = "ORD_INFO";

    /* */
    private static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /* */
    private static final String PO_ORD_NUM = "PO_ORD_NUM";

    /* */
    private static final String RMNF_ORD_NUM = "RMNF_ORD_NUM";

    /* */
    private static final String RMNF_ORD_DTL_LINE_NUM = "RMNF_ORD_DTL_LINE_NUM";

    /* */
    private static final String PO_RCV_DRCT_SHIP_TP_CD = "PO_RCV_DRCT_SHIP_TP_CD";

    /* */
    private static final String PO_RCV_FROM_LOC_CD = "PO_RCV_FROM_LOC_CD";

    /* */
    private static final String PO_RCV_FROM_LOC_TP_CD = "PO_RCV_FROM_LOC_TP_CD";

    /* */
    private static final String PO_RCV_ETA_DT = "PO_RCV_ETA_DT";

    /* */
    private static final String PO_RCV_NUM = "PO_RCV_NUM";

    /* */
    private static final String PO_RCV_LINE_NUM = "PO_RCV_LINE_NUM";

    /* */
    private static final String PO_RCV_MSG_SQ_NUM = "PO_RCV_MSG_SQ_NUM";

    /* */
    private static final String PO_RCV_QTY = "PO_RCV_QTY";

    /* */
    private static final String PO_RCV_TRX_HDR_NUM = "PO_RCV_TRX_HDR_NUM";

    /* */
    private static final String PO_RCV_TRX_LINE_NUM = "PO_RCV_TRX_LINE_NUM";

    /* */
    private static final String WRK_ORD_DTL = "WRK_ORD_DTL";

    /* */
    private static final String WRK_ORD_NUM = "WRK_ORD_NUM";

    /* */
    private static final String WRK_ORD_DTL_LINE_NUM = "WRK_ORD_DTL_LINE_NUM";

    // QC1092 Add start
    // S-QC1388-MOD-20130725
    ///* */
    //private static final String TRX_HDR_LINE_NUM = "TRX_HDR_NUM / TRX_LINE_NUM";
    //
    ///* */
    //private static final String TRX_HDR_NUM = "TRX_HDR_NUM";
    //
    ///* */
    //private static final String TRX_LINE_NUM = "TRX_LINE_NUM";
    //
    /* */
    private static final String ASN_SO_LINE_NUM = "ASN_SO_NUM / ASN_LINE_NUM";

    /* */
    private static final String ASN_SO_NUM = "ASN_SO_NUM";

    /* */
    private static final String ASN_LINE_NUM = "ASN_LINE_NUM";
    // E-QC1388-MOD-20130725

    // QC1092 Add end

    /* */
    private static final String RWS_REF_NUM = "RWS_REF_NUM";

    /* */
    private static final String SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /* */
    private static final String SO_NUM = "SO_NUM";

    /* */
    private static final String SO_SLP_NUM = "SO_SLP_NUM";

    /* */
    private static final String STK_STS_CD = "STK_STS_CD";

    /* */
    private static final String VND_CD = "VND_CD";

    /* */
    private static final String WH_CD = "WH_CD";

    // S-QC1403-ADD-20130718
    /** Column name */
    private static final String INVTY_LOC_CD = "INVTY_LOC_CD";
    // E-QC1403-ADD-20130718

    // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add Start
    /** Column name : SHIP_FROM_INVTY_LOC_CD */
    private static final String SHIP_FROM_INVTY_LOC_CD = "SHIP_FROM_INVTY_LOC_CD";
    // 2015/09/25 CSA(9.115, 9.11.12) Y.Tsuchimoto Add End

    /** SQL Bind Name for S21ApiTBLAccessor : GLBL_CMPY_CD */
    private static final String BIND_GLBL_CMPY_CD = "glblCmpyCd01";

    /** SQL Bind Name for S21ApiTBLAccessor : WH_CD */
    private static final String BIND_WH_CD = "whCd01";

    /** SQL Bind Name for S21ApiTBLAccessor : RWS_REF_NUM */
    private static final String BIND_RWS_REF_NUM = "rwsRefNum01";

    /** SQL Bind Name for S21ApiTBLAccessor : VND_CD */
    private static final String BIND_VND_CD = "vndCd01";

    //START K.Lee ADD
    /** SQL Bind Name for S21ApiTBLAccessor : INVTY_LOC_CD */
    private static final String BIND_INVTY_LOC_CD = "invtyLocCd01";
    //END K.Lee ADD
    
    // S-QC1403-ADD-20130718
    /** SQL Bind Name for S21ApiTBLAccessor : RGTN_STS_CD */
    private static final String BIND_RGTN_STS_CD = "rgtnStsCd01";
    // E-QC1403-ADD-20130718

    //START ADD UNIT TEST#130
    /** SQL Bind Name for S21ApiTBLAccessor : RTL_WH_CD */
    private static final String BIND_RTL_WH_CD = "rtlWhCd01";
    //END ADD UNIT TEST#130

    /* */
    private static final String PO_RCV_NUM_HEADER = "PR";

    /* */
    private static final int PO_RCV_NUM_SQ_DIGIT = 8;

    /* */
    private static final int PO_RCV_LINE_NUM_LENGTH = 3;

    /* */
    private static final String RWS_RCV_LINE_NUM_PUT_NUM = "0";

    /* */
    private static final String ONBATCH_NULL = "NULL";

    /** SQLID:RWS_HDR */
    private static final String SQLID_RWS_HDR = "001";

    /** SQLID:VND */
    private static final String SQLID_VND = "016";

    // S-QC1403-DEL-20130718
//    /** SQLID:WH */
//    private static final String SQLID_WH = "027";
    // E-QC1403-DEL-20130718

    // Del Start CSA No Use
//    //START K.Lee ADD
//    /** SQLID:DS_INVTY_LOC_V */
//    private static final String SQLID_DS_INVTY_LOC_V = "002";
//    //END K.Lee ADD
    // Del End CSA No Use
    
    // S-QC1403-ADD-20130718
    /** SQLID:DS_INVTY_LOC_V:001 */
    private static final String SQLID_DS_INVTY_LOC_V_001 = "001";
    // E-QC1403-ADD-20130718

    //START ADD UNIT TEST#130
    /** SQLID:DS_INVTY_LOC_V */
    private static final String SQLID_DS_INVTY_LOC_V_003 = "003";
    //END ADD UNIT TEST#130

    /** Locale: YYYYMMDDHHMMSSsss */
    String TIMESTAMP_PATTERN = "yyyyMMddHHmmssSSS";

    // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del Start
    ///** LOC_NM */
    //private String locNm = "";
    // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del End

    // START 2021/02/05 A.Marte [QC#58256, ADD]
    private static final String MULTIPLE_CARRIER = "*";
    // END 2021/02/05 A.Marte [QC#58256, ADD]

    /**
     * <pre>
     * </pre>
     */
    public NLZC201001() {
        super();
    }

    /**
     * <pre>
     * execute.
     * </pre>
     * @param param NLZC201001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NLZC201001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doPoRcvApi(msgMap, onBatchType);

        msgMap.flush();
    }

    /**
     * execute.
     * @see com.canon.cusa.s21.api.NLZ.NLZC201001.execute
     */
    public void execute(final List<NLZC201001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NLZC201001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * doPoRcvApi
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    protected void doPoRcvApi(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC201001PMsg param = (NLZC201001PMsg) msgMap.getPmsg();

        boolean returnValue = checkParam(msgMap, onBatchType);

        if (!returnValue) {
            return;
        }

        String prePoRcvNum = EZDSeqTable.getNumberString(ZYPOracleSeqConstant.PO_RCV_NUM_SQ, PO_RCV_NUM_SQ_DIGIT);
        String poRcvNum = new StringBuilder(PO_RCV_NUM_HEADER).append(prePoRcvNum).toString();

        PO_RCV_HDRTMsg poRcvHdrT = new PO_RCV_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.glblCmpyCd, param.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvNum, poRcvNum);
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.sceOrdTpCd, param.sceOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvFromLocTpCd, param.poRcvFromLocTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvFromLocCd, param.poRcvFromLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.whCd, param.whCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.rwsRefNum, param.rwsRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvDrctShipTpCd, param.poRcvDrctShipTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.inlndCarrCd, param.inlndCarrCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvEtaDt, param.poRcvEtaDt.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.poRcvTrxHdrNum, param.poRcvTrxHdrNum.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.domInvNum, param.domInvNum.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.custPoNum, param.custPoNum.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.sysSrcCd, param.sysSrcCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.rwsStsCd, RWS_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.shipFromSoNum, param.shipFromSoNum.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvHdrT.svcConfigMstrPk, param.svcConfigMstrPk.getValue());
        

        S21ApiTBLAccessor.insert(poRcvHdrT);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvHdrT.getReturnCode())) {

            throw new S21AbendException(NLAM1133E, new String[] {NLXSceConst.TBL_PO_RCV_HDR, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM)
                    , NLXCMsgHelper.toListedString(poRcvHdrT.glblCmpyCd, poRcvHdrT.poRcvNum) });
        }

        if (param.MsgInfoLIst.getValidCount() > 0) {

            for (int i = 0; i < param.MsgInfoLIst.getValidCount(); i++) {

                PO_RCV_MSGTMsg poRcvMsgT = new PO_RCV_MSGTMsg();
                ZYPEZDItemValueSetter.setValue(poRcvMsgT.glblCmpyCd, param.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(poRcvMsgT.poRcvNum, poRcvNum);
                ZYPEZDItemValueSetter.setValue(poRcvMsgT.poRcvMsgSqNum, Integer.toString(i + 1));
                ZYPEZDItemValueSetter.setValue(poRcvMsgT.poRcvMsgTxt, param.MsgInfoLIst.no(i).poRcvMsgTxt.getValue());

                S21ApiTBLAccessor.insert(poRcvMsgT);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvMsgT.getReturnCode())) {

                    String[] list = new String[] {NLXSceConst.TBL_PO_RCV_MSG, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_MSG_SQ_NUM)
                            , NLXCMsgHelper.toListedString(poRcvMsgT.glblCmpyCd, poRcvMsgT.poRcvNum, poRcvMsgT.poRcvMsgSqNum) };
                    throw new S21AbendException(NLAM1133E, list);
                }

                poRcvMsgT.clear();
            }
        }

        int poRcvLineNum = 1;
        WH_SCHDTMsg whSchdT = new WH_SCHDTMsg();
        BigDecimal whSchdRefKeyNum = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WH_SCHD_REF_KEY_NUM_SQ);

        String sysDateTs = ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN);

        for (int i = 0; i < param.OrdInfoLIst.getValidCount(); i++) {

            PO_RCV_DTLTMsg poRcvDtlT = new PO_RCV_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvNum, poRcvNum);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvLineNum, ZYPCommonFunc.leftPad(Integer.toString(poRcvLineNum), PO_RCV_LINE_NUM_LENGTH, RWS_RCV_LINE_NUM_PUT_NUM));
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.mdseCd, param.OrdInfoLIst.no(i).mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.stkStsCd, param.OrdInfoLIst.no(i).stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvQty, param.OrdInfoLIst.no(i).poRcvQty.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.actlRcvQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.poRcvTrxLineNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.rwsStsCd, RWS_STS.SAVED);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.lossQty, BigDecimal.ZERO);

            ZYPEZDItemValueSetter.setValue(poRcvDtlT.asnSoNum, param.OrdInfoLIst.no(i).asnSoNum);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.asnLineNum, param.OrdInfoLIst.no(i).asnLineNum);
            // E-QC1388-MOD-20130725
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.carrCd, param.OrdInfoLIst.no(i).carrCd);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.bolNum, param.OrdInfoLIst.no(i).bolNum);
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.proNum, param.OrdInfoLIst.no(i).proNum);
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Add Start
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.rtlWhCd , getRtlWhCd(param.glblCmpyCd.getValue(), param.OrdInfoLIst.no(i).invtyLocCd.getValue(), param.poRcvEtaDt.getValue()));
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.rtlSwhCd, getRtlSwhCd(param.glblCmpyCd.getValue(), param.OrdInfoLIst.no(i).invtyLocCd.getValue(), param.poRcvEtaDt.getValue()));
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.invtyLocCd, param.OrdInfoLIst.no(i).invtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.shipFromRtlWhCd, getShipFromRtlWhCd(param.glblCmpyCd.getValue(), param.OrdInfoLIst.no(i).shipFromInvtyLocCd.getValue(), param.poRcvEtaDt.getValue(), param.poRcvFromLocTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.shipFromRtlSwhCd, getShipFromRtlSwhCd(param.glblCmpyCd.getValue(), param.OrdInfoLIst.no(i).shipFromInvtyLocCd.getValue(), param.poRcvEtaDt.getValue(), param.poRcvFromLocTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.shipFromInvtyLocCd, param.OrdInfoLIst.no(i).shipFromInvtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.prchReqNum, param.OrdInfoLIst.no(i).prchReqNum.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.prchReqLineNum, param.OrdInfoLIst.no(i).prchReqLineNum.getValue());
            ZYPEZDItemValueSetter.setValue(poRcvDtlT.prchReqLineSubNum, param.OrdInfoLIst.no(i).prchReqLineSubNum.getValue());

            
            S21ApiTBLAccessor.insert(poRcvDtlT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(poRcvDtlT.getReturnCode())) {

                String[] list = new String[] {NLXSceConst.TBL_PO_RCV_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_RCV_NUM, PO_RCV_LINE_NUM)
                        , NLXCMsgHelper.toListedString(poRcvDtlT.glblCmpyCd, poRcvDtlT.poRcvNum, poRcvDtlT.poRcvLineNum) };
                throw new S21AbendException(NLAM1133E, list);
            }

            poRcvDtlT.clear();

            // QC1092 Add start
            // S-QC1388-MOD-20130725
            //if (hasTrxNumber(param.OrdInfoLIst.no(i).trxHdrNum.getValue(), param.OrdInfoLIst.no(i).trxLineNum.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del Start
            //if (hasAsnNumber(param.OrdInfoLIst.no(i).asnSoNum.getValue(), param.OrdInfoLIst.no(i).asnLineNum.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del End
            // E-QC1388-MOD-20130725
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del Start
            //}
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del End
            // QC1092 Add end

            BigDecimal inbdVisPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INBD_VIS_SQ);

            INBD_VISTMsg inbdVisT = new INBD_VISTMsg();
            ZYPEZDItemValueSetter.setValue(inbdVisT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisPk, inbdVisPk);

            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
            //         || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue())) {
            if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DN.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_BB.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.DOMESTIC_PO_RECEIVE);

            // 06/22/2010 D.Fukaya modify start
            //} else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //} else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue()) ||
            //        NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())) {
            } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_TR.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End
            // 06/22/2010 D.Fukaya modify end

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.DC_TRANSFER_RECEIVE);

                // QC#13811 START
                // } else if
                // (NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue()))
                // {
            } else if (NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_RM.equals(param.sceOrdTpCd.getValue())) {

                // QC#13811 END

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.REFURBISH_RECEIVE);

            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //} else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue())) {
            } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.KITTING_RECEIVE);

            } else if (NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.KITTING_COMPONENT_RETURN);

            } else if (NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.ORIGINAL_RETURN);

            // QC#23029 Add Start
            } else if (NLXSceConst.SCE_ORD_TP_CD_RU.equals(param.sceOrdTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisEventCd, INBD_VIS_EVENT.REFURBISH_USAGE_RETURN);
            }
            // QC#23029 Add End

            ZYPEZDItemValueSetter.setValue(inbdVisT.inbdLtstRecFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisDataTpCd, INBD_VIS_DATA_TP.STOCK_IN_DC);
            ZYPEZDItemValueSetter.setValue(inbdVisT.inbdVisActlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inbdVisT.mdseCd, param.OrdInfoLIst.no(i).mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.poRcvNum, poRcvNum);
            ZYPEZDItemValueSetter.setValue(inbdVisT.poRcvLineNum, ZYPCommonFunc.leftPad(Integer.toString(poRcvLineNum), PO_RCV_LINE_NUM_LENGTH, RWS_RCV_LINE_NUM_PUT_NUM));
            ZYPEZDItemValueSetter.setValue(inbdVisT.rwsRefNum, param.rwsRefNum.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.visLocTpCd, VIS_LOC_TP.DC);
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //ZYPEZDItemValueSetter.setValue(inbdVisT.visLocCd, param.whCd.getValue());
            //ZYPEZDItemValueSetter.setValue(inbdVisT.visLocNm, locNm);
            ZYPEZDItemValueSetter.setValue(inbdVisT.visLocCd, param.OrdInfoLIst.no(i).invtyLocCd.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.visLocNm, getVisLocNm(param.glblCmpyCd.getValue(), param.OrdInfoLIst.no(i).invtyLocCd.getValue()));
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End
            ZYPEZDItemValueSetter.setValue(inbdVisT.visQty, param.OrdInfoLIst.no(i).poRcvQty.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.etaEtdDt, param.poRcvEtaDt.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.calcFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inbdVisT.cratTs, sysDateTs);
            ZYPEZDItemValueSetter.setValue(inbdVisT.regdTs, sysDateTs);
            ZYPEZDItemValueSetter.setValue(inbdVisT.inlndCarrCd, param.inlndCarrCd.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.invtyStkStsCd, param.OrdInfoLIst.no(i).stkStsCd.getValue());
            ZYPEZDItemValueSetter.setValue(inbdVisT.imptCustPoNum, param.custPoNum.getValue());

            S21ApiTBLAccessor.insert(inbdVisT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inbdVisT.getReturnCode())) {

                String[] list = new String[] {NLXSceConst.TBL_INBD_VIS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, INBD_VIS_PK), NLXCMsgHelper.toListedString(inbdVisT.glblCmpyCd, inbdVisT.inbdVisPk) };
                throw new S21AbendException(NLAM1133E, list);
            }

            inbdVisT.clear();

            // START 2013/04/18 K.Kasai [QC1030,ADD]
            // QC#19835 Add
            if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DN.equals(param.sceOrdTpCd.getValue())) {

                updateInbdVis(param, i, onBatchType);
            }
            // QC#19835 End
            // END 2013/04/18 K.Kasai [QC1030,ADD]

            // Insert WH_SCHD
            ZYPEZDItemValueSetter.setValue(whSchdT.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.whSchdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.WH_SCHD_SQ));
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //ZYPEZDItemValueSetter.setValue(whSchdT.invtyLocCd, param.whCd.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.invtyLocCd, param.OrdInfoLIst.no(i).invtyLocCd.getValue());
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End
            ZYPEZDItemValueSetter.setValue(whSchdT.whSchdRefKeyNumSq, whSchdRefKeyNum);
            ZYPEZDItemValueSetter.setValue(whSchdT.rwsRefNum, param.rwsRefNum.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.poRcvNum, poRcvNum);
            ZYPEZDItemValueSetter.setValue(whSchdT.poRcvLineNum, ZYPCommonFunc.leftPad(Integer.toString(poRcvLineNum), PO_RCV_LINE_NUM_LENGTH, RWS_RCV_LINE_NUM_PUT_NUM));
            ZYPEZDItemValueSetter.setValue(whSchdT.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
            ZYPEZDItemValueSetter.setValue(whSchdT.sceOrdTpCd, param.sceOrdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.schdTpCd, SCHD_TP.DOMESTIC);
            ZYPEZDItemValueSetter.setValue(whSchdT.whInPrtyFlg, ZYPConstant.FLG_OFF_N);

            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
            //         || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue())) {
            if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DN.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_BB.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

                ZYPEZDItemValueSetter.setValue(whSchdT.inbdVisEventCd, INBD_VIS_EVENT.DOMESTIC_PO_RECEIVE);

            // 06/22/2010 D.Fukaya modify start
            //s} else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //} else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue()) ||
            //        NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())) {
            } else if (NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_TR.equals(param.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End
            // 06/22/2010 D.Fukaya modify end

                ZYPEZDItemValueSetter.setValue(whSchdT.inbdVisEventCd, INBD_VIS_EVENT.DC_TRANSFER_RECEIVE);

                // QC#13811 START
                // } else if
                // (NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue()))
                // {

            } else if (NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue()) 
                    || NLXSceConst.SCE_ORD_TP_CD_RM.equals(param.sceOrdTpCd.getValue())) {
                // QC#13811 END

                ZYPEZDItemValueSetter.setValue(whSchdT.inbdVisEventCd, INBD_VIS_EVENT.REFURBISH_RECEIVE);

            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
            //} else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue())) {
            } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())) {
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

                ZYPEZDItemValueSetter.setValue(whSchdT.inbdVisEventCd, INBD_VIS_EVENT.KITTING_RECEIVE);

            } else if (NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(whSchdT.inbdVisEventCd, INBD_VIS_EVENT.KITTING_COMPONENT_RETURN);

            } else if (NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {

                ZYPEZDItemValueSetter.setValue(whSchdT.inbdVisEventCd, INBD_VIS_EVENT.ORIGINAL_RETURN);
            }

            ZYPEZDItemValueSetter.setValue(whSchdT.orgWhInEtaDt, param.poRcvEtaDt.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.ltstWhInEtaDt, param.poRcvEtaDt.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.carrCd, param.inlndCarrCd.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.mdseCd, param.OrdInfoLIst.no(i).mdseCd.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.reqStkInQty, param.OrdInfoLIst.no(i).poRcvQty.getValue());
            ZYPEZDItemValueSetter.setValue(whSchdT.whChngRqstFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(whSchdT.asnNotRcvFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(whSchdT.ltstRecFlg, ZYPConstant.FLG_ON_Y);

            S21ApiTBLAccessor.insert(whSchdT);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(whSchdT.getReturnCode())) {

                String[] list = new String[] {NLXSceConst.TBL_WH_SCHD, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WH_SCHD_PK), NLXCMsgHelper.toListedString(whSchdT.glblCmpyCd, whSchdT.whSchdPk) };
                throw new S21AbendException(NLAM1133E, list);
            }

            whSchdT.clear();

            poRcvLineNum++;
        }

        param.poRcvNum.setValue(poRcvNum);
    }

    // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Add Start
    /**
     * getRtlWhCd
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @param poRcvEtaDt String
     * @return String
     */
    private String getRtlWhCd(String glblCmpyCd, String invtyLocCd, String poRcvEtaDt) {

        String rtnRtlWhCd = null;
        RTL_SWHTMsg tMsg = getSvcMod(glblCmpyCd, invtyLocCd, poRcvEtaDt);

        if (tMsg != null) {

            rtnRtlWhCd = tMsg.rtlWhCd.getValue();
        }

        return rtnRtlWhCd;
    }

    /**
     * getRtlSwhCd
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @param poRcvEtaDt String
     * @return String
     */
    private String getRtlSwhCd(String glblCmpyCd, String invtyLocCd, String poRcvEtaDt) {

        String rtnRtlSwhCd = null;
        RTL_SWHTMsg tMsg = getSvcMod(glblCmpyCd, invtyLocCd, poRcvEtaDt);

        if (tMsg != null) {

            rtnRtlSwhCd = tMsg.rtlSwhCd.getValue();
        }

        return rtnRtlSwhCd;
    }

    /**
     * getShipFromRtlWhCd
     * @param glblCmpyCd String
     * @param shipFromInvtyLocCd String
     * @param poRcvEtaDt String
     * @param poRcvFromLocTpCd String
     * @return String
     */
    private String getShipFromRtlWhCd(String glblCmpyCd, String shipFromInvtyLocCd, String poRcvEtaDt, String poRcvFromLocTpCd) {

        String rtnRtlWhCd = null;

        if (LOC_TP.WAREHOUSE.equals(poRcvFromLocTpCd) || LOC_TP.TECHNICIAN.equals(poRcvFromLocTpCd)) {

            RTL_SWHTMsg tMsg = getSvcMod(glblCmpyCd, shipFromInvtyLocCd, poRcvEtaDt);

            if (tMsg != null) {

                rtnRtlWhCd = tMsg.rtlWhCd.getValue();
            }
        }

        return rtnRtlWhCd;
    }

    /**
     * getShipFromRtlSwhCd
     * @param glblCmpyCd String
     * @param shipFromInvtyLocCd String
     * @param poRcvEtaDt String
     * @param poRcvFromLocTpCd String
     * @return String
     */
    private String getShipFromRtlSwhCd(String glblCmpyCd, String shipFromInvtyLocCd, String poRcvEtaDt, String poRcvFromLocTpCd) {

        String rtnRtlSwhCd = null;

        if (LOC_TP.WAREHOUSE.equals(poRcvFromLocTpCd) || LOC_TP.TECHNICIAN.equals(poRcvFromLocTpCd)) {

            RTL_SWHTMsg tMsg = getSvcMod(glblCmpyCd, shipFromInvtyLocCd, poRcvEtaDt);

            if (tMsg != null) {

                rtnRtlSwhCd = tMsg.rtlSwhCd.getValue();
            }
        }

        return rtnRtlSwhCd;
    }

    /**
     * getSvcMod
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @param poRcvEtaDt String
     * @return RTL_SWHTMsg
     */
    private RTL_SWHTMsg getSvcMod(String glblCmpyCd, String invtyLocCd, String poRcvEtaDt) {

        RTL_SWHTMsg param = new RTL_SWHTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("invtyLocCd01", invtyLocCd);
        param.setConditionValue("effFromDt01", poRcvEtaDt);
        param.setConditionValue("effThruDt01", poRcvEtaDt);

        RTL_SWHTMsgArray result = (RTL_SWHTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {

            return (RTL_SWHTMsg) result.get(0);
        }

        return null;
    }

    /**
     * getVisLocNm
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return String
     */
    private String getVisLocNm(String glblCmpyCd, String invtyLocCd) {

        String rtnVisLocNm = null;
        DS_INVTY_LOC_VTMsg tMsg = getDsInvtyLocV(glblCmpyCd, invtyLocCd);

        if (tMsg != null) {

            rtnVisLocNm = tMsg.invtyLocNm.getValue();
        }

        return rtnVisLocNm;
    }

    /**
     * getDsInvtyLocV
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @return DS_INVTY_LOC_VTMsg
     */
    private DS_INVTY_LOC_VTMsg getDsInvtyLocV(String glblCmpyCd, String invtyLocCd) {

        DS_INVTY_LOC_VTMsg param = new DS_INVTY_LOC_VTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("invtyLocCd01", invtyLocCd);

        DS_INVTY_LOC_VTMsgArray result = (DS_INVTY_LOC_VTMsgArray) S21ApiTBLAccessor.findByCondition(param);

        if (result.getValidCount() > 0) {

            return (DS_INVTY_LOC_VTMsg) result.get(0);
        }

        return null;
    }
    // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Add End

    /**
     * <pre>
     * checkParam
     * </pre>
     * @param onBatchType ONBATCH_TYPE
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    protected boolean checkParam(final S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NLZC201001PMsg param = (NLZC201001PMsg) msgMap.getPmsg();

        boolean returnValue = true;

        if (onBatchType == null) {

            String[] list = new String[] {NLXCMsgHelper.toListedString(ONBATCH_TYPE), NLXCMsgHelper.toListedString(ONBATCH_NULL) };
            throw new S21AbendException(NLAM1131E, list);
        }

        String mndtryFlg = ZYPConstant.FLG_OFF_N;
        String existFlg = ZYPConstant.FLG_OFF_N;
        String countFlg = ZYPConstant.FLG_OFF_N;
        String settingFlg = ZYPConstant.FLG_OFF_N;

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {GLBL_CMPY_CD }, onBatchType, this);
            msgMap.addXxMsgId(NLZM1001E);
            returnValue = false;
            return returnValue;

        } else {

            GLBL_CMPYTMsg glblCmpyT = (GLBL_CMPYTMsg) ZYPCodeDataUtil.findByCode(NLXSceConst.TBL_GLBL_CMPY, param.glblCmpyCd.getValue(), param.glblCmpyCd.getValue());

            if (glblCmpyT == null) {

                msgMap.addXxMsgId(NLZM1035E);
                NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_GLBL_CMPY, NLXCMsgHelper.toListedString(GLBL_CMPY_CD), NLXCMsgHelper.toListedString(param.glblCmpyCd) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.sysSrcCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {SYS_SRC_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            SYS_SRCTMsg sysSrcT = (SYS_SRCTMsg) ZYPCodeDataUtil.findByCode(NLXSceConst.TBL_SYS_SRC, param.glblCmpyCd.getValue(), param.sysSrcCd.getValue());

            if (sysSrcT == null) {

                msgMap.addXxMsgId(NLZM1020E);
                NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_SYS_SRC, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SYS_SRC_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.sysSrcCd) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.sceOrdTpCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {SCE_ORD_TP_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            SCE_ORD_TPTMsg sceOrdTpT = new SCE_ORD_TPTMsg();
            sceOrdTpT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
            sceOrdTpT.sceOrdTpCd.setValue(param.sceOrdTpCd.getValue());
            sceOrdTpT.inbdOtbdCd.setValue(INBD_OTBD.INBOUND);
            sceOrdTpT = (SCE_ORD_TPTMsg) S21ApiTBLAccessor.findByKey(sceOrdTpT);

            if (sceOrdTpT == null) {

                msgMap.addXxMsgId(NLZM2016E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SCE_ORD_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SCE_ORD_TP_CD, INBD_OTBD_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.sceOrdTpCd, INBD_OTBD.INBOUND) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.whCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {WH_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            //Start K.Lee UPDATE
            //WHTMsg whT = new WHTMsg();

            //whT.setSQLID(SQLID_WH);

            //whT.setConditionValue(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            //whT.setConditionValue(BIND_WH_CD, param.whCd.getValue());
            //WHTMsgArray msgArrWh = (WHTMsgArray) S21ApiTBLAccessor.findByCondition(whT);

            //if (msgArrWh.getValidCount() == 0) {
            //    msgMap.addXxMsgId(NLZM2017E);
            //    NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_WH, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WH_CD)
            //            , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.whCd) }, onBatchType, this);
            //    existFlg = ZYPConstant.FLG_ON_Y;
            //} else {
            //    locNm = msgArrWh.no(0).locNm.getValue();
            //}
            DS_INVTY_LOC_VTMsg dsInvtyLocT = new DS_INVTY_LOC_VTMsg();

            //START MOD UNIT TEST#130
            //dsInvtyLocT.setSQLID(SQLID_DS_INVTY_LOC_V);
            dsInvtyLocT.setSQLID(SQLID_DS_INVTY_LOC_V_003);
            //END MOD UNIT TEST#130

            dsInvtyLocT.setConditionValue(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            //START MOD UNIT TEST#130
            //dsInvtyLocT.setConditionValue(BIND_INVTY_LOC_CD, param.whCd.getValue());
            dsInvtyLocT.setConditionValue(BIND_RTL_WH_CD, param.whCd.getValue());
            //END MOD UNIT TEST#130
            DS_INVTY_LOC_VTMsgArray msgArrDsInvtyLoc = (DS_INVTY_LOC_VTMsgArray) S21ApiTBLAccessor.findByCondition(dsInvtyLocT);

            if (msgArrDsInvtyLoc.getValidCount() == 0) {
                msgMap.addXxMsgId(NLZM2017E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_WH, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WH_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.whCd) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;

            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del Start
            //} else {
            //    locNm = msgArrDsInvtyLoc.no(0).invtyLocNm.getValue();
            // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Del End
            }
            //END K.Lee UPDATE

            if (ZYPCommonFunc.hasValue(param.rwsRefNum)) {

                RWS_HDRTMsg rwsHdrT = new RWS_HDRTMsg();
                rwsHdrT.setSQLID(SQLID_RWS_HDR);
                rwsHdrT.setConditionValue(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                rwsHdrT.setConditionValue(BIND_RWS_REF_NUM, param.rwsRefNum.getValue());
                rwsHdrT.setConditionValue(BIND_WH_CD, param.whCd.getValue());
                RWS_HDRTMsgArray msgArrRwsHdr = (RWS_HDRTMsgArray) S21ApiTBLAccessor.findByCondition(rwsHdrT);

                if (msgArrRwsHdr.length() >= 1) {

                    msgMap.addXxMsgId(NLZM1025E);
                    NLXC025001.outputLog(1, NLAM1138E, new String[] {NLXCMsgHelper.toListedString(param.rwsRefNum, param.whCd) }, onBatchType, this);
                    existFlg = ZYPConstant.FLG_ON_Y;
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(param.rwsRefNum)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {RWS_REF_NUM }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;
        }

        if (!ZYPCommonFunc.hasValue(param.poRcvFromLocTpCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_FROM_LOC_TP_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            LOC_TPTMsg locTpT = (LOC_TPTMsg) ZYPCodeDataUtil.findByCode(NLXSceConst.TBL_LOC_TP, param.glblCmpyCd.getValue(), param.poRcvFromLocTpCd.getValue());

            if (locTpT == null) {
                msgMap.addXxMsgId(NLZM1021E);
                NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_LOC_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, LOC_TP_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvFromLocTpCd) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;

            // S-QC1403-MOD-20130718
            } else if (!(LOC_TP.WAREHOUSE.equals(param.poRcvFromLocTpCd.getValue())
                    || LOC_TP.VENDOR.equals(param.poRcvFromLocTpCd.getValue())
                    || LOC_TP.TECHNICIAN.equals(param.poRcvFromLocTpCd.getValue()))) {
            // E-QC1403-MOD-20130718

                msgMap.addXxMsgId(NLZM1039E);
                NLXC025001.outputLog(1, NLAM1131E, new String[] {NLXCMsgHelper.toListedString(PO_RCV_FROM_LOC_TP_CD), NLXCMsgHelper.toListedString(param.poRcvFromLocTpCd) }, onBatchType, this);
                settingFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.poRcvFromLocCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_FROM_LOC_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            // S-QC1403-MOD-20130718
            DS_INVTY_LOC_VTMsg cond = new DS_INVTY_LOC_VTMsg();
            cond.setSQLID(SQLID_DS_INVTY_LOC_V_001);
            cond.setConditionValue(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            cond.setConditionValue(BIND_INVTY_LOC_CD, param.poRcvFromLocCd.getValue());
            cond.setConditionValue(BIND_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
            cond.setMaxCount(1);
            DS_INVTY_LOC_VTMsgArray results = (DS_INVTY_LOC_VTMsgArray) S21ApiTBLAccessor.findByCondition(cond);

            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(rtlWhTMsg.rtlWhCd, param.poRcvFromLocCd.getValue());
            rtlWhTMsg = (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhTMsg);

            if (0 == results.getValidCount() && rtlWhTMsg == null) {

                msgMap.addXxMsgId(NLZM1024E);
                NLXC025001.outputLog(1, NLAM1001E, new String[] {cond.getTableName(), NLXCMsgHelper.toListedString(GLBL_CMPY_CD, INVTY_LOC_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvFromLocCd) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;
            }
            // E-QC1403-MOD-20130718
        }

        if (!ZYPCommonFunc.hasValue(param.poRcvDrctShipTpCd)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_DRCT_SHIP_TP_CD }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            DRCT_SHIP_TPTMsg drctShipTpCdT = (DRCT_SHIP_TPTMsg) ZYPCodeDataUtil.findByCode(NLXSceConst.TBL_DRCT_SHIP_TP, param.glblCmpyCd.getValue(), param.poRcvDrctShipTpCd.getValue());

            if (drctShipTpCdT == null) {
                msgMap.addXxMsgId(NLZM1022E);
                NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_DRCT_SHIP_TP, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, DRCT_SHIP_TP_CD)
                        , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvDrctShipTpCd) }, onBatchType, this);
                existFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (!ZYPCommonFunc.hasValue(param.poRcvEtaDt)) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_ETA_DT }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;
        }

        // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
//        if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue())) {
//
//            if (!ZYPCommonFunc.hasValue(param.domInvNum)) {
//                NLXC025001.outputLog(1, NLAM1006E, new String[] {DOM_INV_NUM }, onBatchType, this);
//                mndtryFlg = ZYPConstant.FLG_ON_Y;
//            }
//         } else if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())) {
//
//            if (!ZYPCommonFunc.hasValue(param.poRcvTrxHdrNum)) {
//                NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_TRX_HDR_NUM }, onBatchType, this);
//                mndtryFlg = ZYPConstant.FLG_ON_Y;
//            }
//
//            if (!ZYPCommonFunc.hasValue(param.domInvNum)) {
//                NLXC025001.outputLog(1, NLAM1006E, new String[] {DOM_INV_NUM }, onBatchType, this);
//                mndtryFlg = ZYPConstant.FLG_ON_Y;
//            }
//
//        } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue()) ||
//                    NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue()) ||
//                    NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue()) ||
//                    // 06/22/2010 D.Fukaya append start
//                    NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue()) ||
//                    // 06/22/2010 D.Fukaya append end
//                    NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue()) ||
//                    NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {
//
//            if (!ZYPCommonFunc.hasValue(param.poRcvTrxHdrNum)) {
//                NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_TRX_HDR_NUM }, onBatchType, this);
//                mndtryFlg = ZYPConstant.FLG_ON_Y;
//            }
//        }
        // param poRcvTrxHdrNum check
        if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue()) 
                || NLXSceConst.SCE_ORD_TP_CD_DN.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_RP.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_BB.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_TR.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_RM.equals(param.sceOrdTpCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(param.poRcvTrxHdrNum)) {

                NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_TRX_HDR_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }

        }

        // param domInvNum check
        if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
                || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(param.domInvNum)) {

                NLXC025001.outputLog(1, NLAM1006E, new String[] {DOM_INV_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
        }
        // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

        if (DRCT_SHIP_TP.CPPD.equals(param.poRcvDrctShipTpCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(param.custPoNum)) {

                NLXC025001.outputLog(1, NLAM1006E, new String[] {CUST_PO_NUM }, onBatchType, this);
                mndtryFlg = ZYPConstant.FLG_ON_Y;
            }
        }

        if (ZYPCommonFunc.hasValue(param.inlndCarrCd)) {

            // START 2021/02/05 A.Marte [QC#58256, MOD]
            // Do not verify VND_CD if multiple carrier
            if (!param.inlndCarrCd.getValue().equals(MULTIPLE_CARRIER)) {

                VNDTMsg vndT = new VNDTMsg();
                vndT.setSQLID(SQLID_VND);
                vndT.setConditionValue(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                vndT.setConditionValue(BIND_VND_CD, param.inlndCarrCd.getValue());
                VNDTMsgArray msgArr = (VNDTMsgArray) S21ApiTBLAccessor.findByCondition(vndT);

                if (msgArr.getValidCount() == 0) {

                    msgMap.addXxMsgId(NLZM1024E);
                    NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_VND, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, VND_CD)
                            , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.inlndCarrCd) }, onBatchType, this);
                     existFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            //END 2021/02/05 A.Marte [QC#58256, MOD]

        }

        String soTrxHdrNum = "";

        if (ZYPConstant.FLG_OFF_N.equals(mndtryFlg)) {

            if (ZYPCommonFunc.hasValue(param.poRcvTrxHdrNum)) {

                if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
                         || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue())
                         || NLXSceConst.SCE_ORD_TP_CD_BB.equals(param.sceOrdTpCd.getValue())) { // BB Add 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto

                    POTMsg poT = new POTMsg();
                    poT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    poT.poOrdNum.setValue(param.poRcvTrxHdrNum.getValue());
                    poT = (POTMsg) S21ApiTBLAccessor.findByKey(poT);

                    if (poT == null) {

                        msgMap.addXxMsgId(NLZM1026E);
                        NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_PO, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_ORD_NUM)
                                , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvTrxHdrNum) }, onBatchType, this);
                        existFlg = ZYPConstant.FLG_ON_Y;
                    }

                } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())
                        // 06/22/2010 D.Fukaya append start
                        || NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())
                        // 06/22/2010 D.Fukaya append end
                        || NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue())
                        // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
                        //NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {
                        || NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_TR.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {
                        // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

                    SHPG_ORDTMsg shpgOrdT = new SHPG_ORDTMsg();
                    shpgOrdT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    shpgOrdT.soNum.setValue(param.poRcvTrxHdrNum.getValue());
                    shpgOrdT = (SHPG_ORDTMsg) S21ApiTBLAccessor.findByKey(shpgOrdT);

                    if (shpgOrdT == null) {

                        msgMap.addXxMsgId(NLZM1028E);
                        NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SHPG_ORD, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM)
                                , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvTrxHdrNum) }, onBatchType, this);
                        existFlg = ZYPConstant.FLG_ON_Y;

                    } else {

                        soTrxHdrNum = shpgOrdT.trxHdrNum.getValue();
                    }
                } else if(NLXSceConst.SCE_ORD_TP_CD_RM.equals(param.sceOrdTpCd.getValue())){
                    // Master check
                    RMNF_ORDTMsg roTMsg = new RMNF_ORDTMsg();
                    roTMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    roTMsg.rmnfOrdNum.setValue(param.poRcvTrxHdrNum.getValue());
                    roTMsg = (RMNF_ORDTMsg) S21ApiTBLAccessor.findByKey(roTMsg);

                    if (roTMsg == null) {

                        msgMap.addXxMsgId(NLZM1026E);
                        NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_RMNF_ORD, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RMNF_ORD_NUM)
                                , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvTrxHdrNum) }, onBatchType, this);
                        existFlg = ZYPConstant.FLG_ON_Y;
                    }
                }
            }
        }

        int ordInfoListValidCount = param.OrdInfoLIst.getValidCount();

        if (ordInfoListValidCount == 0) {

            NLXC025001.outputLog(1, NLAM1006E, new String[] {ORD_INFO }, onBatchType, this);
            mndtryFlg = ZYPConstant.FLG_ON_Y;

        } else {

            // String stkStsErrFlg = ZYPConstant.FLG_OFF_N;
            String mdseExistFlg = ZYPConstant.FLG_OFF_N;
            String stkStsExistFlg = ZYPConstant.FLG_OFF_N;
            String poDtlExistFlg = ZYPConstant.FLG_OFF_N;
            String shpgOrdDtlExistFlg = ZYPConstant.FLG_OFF_N;

            for (int i = 0; i < ordInfoListValidCount; i++) {

                if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).mdseCd)) {

                    NLXC025001.outputLog(1, NLAM1006E, new String[] {MDSE_CD }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;

                } else {

                    MDSETMsg mdseT = new MDSETMsg();
                    mdseT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
                    mdseT.mdseCd.setValue(param.OrdInfoLIst.no(i).mdseCd.getValue());
                    mdseT = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseT);

                    if (mdseT == null) {

                        NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_MDSE, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, MDSE_CD),
                                NLXCMsgHelper.toListedString(param.glblCmpyCd, param.OrdInfoLIst.no(i).mdseCd) }, onBatchType, this);
                        existFlg = ZYPConstant.FLG_ON_Y;
                        mdseExistFlg = ZYPConstant.FLG_ON_Y;
                    }
                }

                if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).stkStsCd)) {

                    NLXC025001.outputLog(1, NLAM1006E, new String[] {STK_STS_CD }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;

                } else {

                    STK_STSTMsg stkStsT = (STK_STSTMsg) ZYPCodeDataUtil.findByCode(NLXSceConst.TBL_STK_STS, param.glblCmpyCd.getValue(), param.OrdInfoLIst.no(i).stkStsCd.getValue());

                    if (stkStsT == null) {

                        NLXC025001.outputLog(1, NLAM1013E, new String[] {NLXSceConst.TBL_STK_STS, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, STK_STS_CD)
                                , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.OrdInfoLIst.no(i).stkStsCd) }, onBatchType, this);
                        // stkStsErrFlg = ZYPConstant.FLG_ON_Y;
                        existFlg = ZYPConstant.FLG_ON_Y;
                        stkStsExistFlg = ZYPConstant.FLG_ON_Y;
                    }
                }

                if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).poRcvQty)) {

                    NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_QTY }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                }

                // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Add Start
                if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).invtyLocCd)) {

                    NLXC025001.outputLog(1, NLAM1006E, new String[] {INVTY_LOC_CD }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                }

                if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).shipFromInvtyLocCd)) {

                    NLXC025001.outputLog(1, NLAM1006E, new String[] {SHIP_FROM_INVTY_LOC_CD }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                }
                // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Add End

                if (DRCT_SHIP_TP.SELL_THEN_BUY.equals(param.poRcvDrctShipTpCd.getValue())) {

                    if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).poRcvTrxLineNum)) {

                        NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_TRX_LINE_NUM }, onBatchType, this);
                        mndtryFlg = ZYPConstant.FLG_ON_Y;
                    }
                }

                if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_TR.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_DN.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_BB.equals(param.sceOrdTpCd.getValue())
                        || NLXSceConst.SCE_ORD_TP_CD_RM.equals(param.sceOrdTpCd.getValue())) {

                    if (!ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).poRcvTrxLineNum)) {

                        NLXC025001.outputLog(1, NLAM1006E, new String[] {PO_RCV_TRX_LINE_NUM }, onBatchType, this);
                        mndtryFlg = ZYPConstant.FLG_ON_Y;
                    }
                }

                if (ZYPConstant.FLG_OFF_N.equals(mndtryFlg)) {

                    if (ZYPCommonFunc.hasValue(param.poRcvTrxHdrNum) && ZYPCommonFunc.hasValue(param.OrdInfoLIst.no(i).poRcvTrxLineNum)) {

                        if (NLXSceConst.SCE_ORD_TP_CD_DO.equals(param.sceOrdTpCd.getValue())
                                || NLXSceConst.SCE_ORD_TP_CD_DP.equals(param.sceOrdTpCd.getValue())
                                 || NLXSceConst.SCE_ORD_TP_CD_DG.equals(param.sceOrdTpCd.getValue())
                                 || NLXSceConst.SCE_ORD_TP_CD_BB.equals(param.sceOrdTpCd.getValue())) { // BB Add 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto

                            PO_DTLTMsg poDtlT = new PO_DTLTMsg();
                            ZYPEZDItemValueSetter.setValue(poDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
                            ZYPEZDItemValueSetter.setValue(poDtlT.poOrdNum, param.poRcvTrxHdrNum.getValue());
                            ZYPEZDItemValueSetter.setValue(poDtlT.poOrdDtlLineNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum.getValue());
                            poDtlT = (PO_DTLTMsg) S21ApiTBLAccessor.findByKey(poDtlT);

                            if (poDtlT == null) {

                                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_PO_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, PO_ORD_NUM, PO_ORD_DTL_LINE_NUM),
                                        NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvTrxHdrNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum) }, onBatchType, this);
                                existFlg = ZYPConstant.FLG_ON_Y;
                                poDtlExistFlg = ZYPConstant.FLG_ON_Y;
                            }

                        } else if (NLXSceConst.SCE_ORD_TP_CD_KT.equals(param.sceOrdTpCd.getValue())
                                || NLXSceConst.SCE_ORD_TP_CD_RF.equals(param.sceOrdTpCd.getValue())
                                || NLXSceConst.SCE_ORD_TP_CD_DT.equals(param.sceOrdTpCd.getValue())
                                // 06/22/2010 D.Fukaya append start
                                || NLXSceConst.SCE_ORD_TP_CD_LT.equals(param.sceOrdTpCd.getValue())
                                // 06/22/2010 D.Fukaya append end
                                || NLXSceConst.SCE_ORD_TP_CD_KC.equals(param.sceOrdTpCd.getValue())
                                // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod Start
                                //NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {
                                || NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())
                                || NLXSceConst.SCE_ORD_TP_CD_TR.equals(param.sceOrdTpCd.getValue())
                                || NLXSceConst.SCE_ORD_TP_CD_RC.equals(param.sceOrdTpCd.getValue())) {
                                // 2015/09/30 CSA(9.1.10, 9.7.1, 9.11.12) Y.Tsuchimoto Mod End

                            if (NLXSceConst.SCE_ORD_TP_CD_KU.equals(param.sceOrdTpCd.getValue())) {

                                WRK_ORD_DTLTMsg wrkOrdDtlT = new WRK_ORD_DTLTMsg();
                                ZYPEZDItemValueSetter.setValue(wrkOrdDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
                                ZYPEZDItemValueSetter.setValue(wrkOrdDtlT.wrkOrdNum, soTrxHdrNum);
                                ZYPEZDItemValueSetter.setValue(wrkOrdDtlT.wrkOrdDtlLineNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum.getValue());
                                wrkOrdDtlT = (WRK_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(wrkOrdDtlT);

                                if (wrkOrdDtlT == null) {

                                    NLXC025001.outputLog(1, NLAM1001E, new String[] {WRK_ORD_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, WRK_ORD_NUM, WRK_ORD_DTL_LINE_NUM)
                                            , NLXCMsgHelper.toListedString(param.glblCmpyCd, soTrxHdrNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum) }, onBatchType, this);
                                    existFlg = ZYPConstant.FLG_ON_Y;
                                    shpgOrdDtlExistFlg = ZYPConstant.FLG_ON_Y;
                                }

                            } else {

                                SHPG_ORD_DTLTMsg shpgOrdDtlT = new SHPG_ORD_DTLTMsg();
                                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.glblCmpyCd, param.glblCmpyCd.getValue());
                                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.soNum, param.poRcvTrxHdrNum.getValue());
                                ZYPEZDItemValueSetter.setValue(shpgOrdDtlT.soSlpNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum.getValue());
                                shpgOrdDtlT = (SHPG_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(shpgOrdDtlT);

                                if (shpgOrdDtlT == null) {

                                    NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_SHPG_ORD_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, SO_NUM, SO_SLP_NUM)
                                            , NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvTrxHdrNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum) }, onBatchType, this);
                                    existFlg = ZYPConstant.FLG_ON_Y;
                                    shpgOrdDtlExistFlg = ZYPConstant.FLG_ON_Y;
                                }
                            }
                        } else if (NLXSceConst.SCE_ORD_TP_CD_RM.equals(param.sceOrdTpCd.getValue())){
                            RMNF_ORD_DTLTMsg rmnfOrdDtlTMsg = new RMNF_ORD_DTLTMsg();
                            ZYPEZDItemValueSetter.setValue(rmnfOrdDtlTMsg.glblCmpyCd, param.glblCmpyCd.getValue());
                            ZYPEZDItemValueSetter.setValue(rmnfOrdDtlTMsg.rmnfOrdNum, param.poRcvTrxHdrNum.getValue());
                            ZYPEZDItemValueSetter.setValue(rmnfOrdDtlTMsg.rmnfOrdDtlLineNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum.getValue());
                            rmnfOrdDtlTMsg = (RMNF_ORD_DTLTMsg) S21ApiTBLAccessor.findByKey(rmnfOrdDtlTMsg);

                            if (rmnfOrdDtlTMsg == null) {

                                NLXC025001.outputLog(1, NLAM1001E, new String[] {NLXSceConst.TBL_RMNF_ORD_DTL, NLXCMsgHelper.toListedString(GLBL_CMPY_CD, RMNF_ORD_NUM, RMNF_ORD_DTL_LINE_NUM),
                                        NLXCMsgHelper.toListedString(param.glblCmpyCd, param.poRcvTrxHdrNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum) }, onBatchType, this);
                                existFlg = ZYPConstant.FLG_ON_Y;
                                poDtlExistFlg = ZYPConstant.FLG_ON_Y;
                            }
                        }
                    }
                }

                // QC1092 Add start
                // S-QC1388-MOD-20130725
                //if (!checkTrxNumber(param.OrdInfoLIst.no(i).trxHdrNum.getValue(), param.OrdInfoLIst.no(i).trxLineNum.getValue())) {
                //    NLXC025001.outputLog(1, NLAM1006E, new String[] {TRX_HDR_LINE_NUM }, onBatchType, this);
                //    mndtryFlg = ZYPConstant.FLG_ON_Y;
                //}
                if (!checkAsnNumber(param.OrdInfoLIst.no(i).asnSoNum.getValue(), param.OrdInfoLIst.no(i).asnLineNum.getValue())) {

                    NLXC025001.outputLog(1, NLAM1006E, new String[] {ASN_SO_LINE_NUM }, onBatchType, this);
                    mndtryFlg = ZYPConstant.FLG_ON_Y;
                }
                // E-QC1388-MOD-20130725
                // QC1092 Add end
            }

            if (ZYPConstant.FLG_ON_Y.equals(mdseExistFlg)) {

                msgMap.addXxMsgId(NLZM1023E);
            }

            if (ZYPConstant.FLG_ON_Y.equals(stkStsExistFlg)) {

                msgMap.addXxMsgId(NLZM2022E);
            }

            if (ZYPConstant.FLG_ON_Y.equals(poDtlExistFlg)) {

                msgMap.addXxMsgId(NLZM1027E);
            }

            if (ZYPConstant.FLG_ON_Y.equals(shpgOrdDtlExistFlg)) {

                msgMap.addXxMsgId(NLZM1029E);
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(mndtryFlg)) {

            msgMap.addXxMsgId(NLZM1001E);
        }

        // if (ZYPConstant.FLG_ON_Y.equals(countFlg)) {
        // msgMap.addXxMsgId(NLZM1019E);
        // }
        if (ZYPConstant.FLG_ON_Y.equals(mndtryFlg) || ZYPConstant.FLG_ON_Y.equals(countFlg) || ZYPConstant.FLG_ON_Y.equals(existFlg) || ZYPConstant.FLG_ON_Y.equals(settingFlg)) {

            returnValue = false;
        }

        return returnValue;
    }

    // START 2013/04/18 K.Kasai [QC1030,ADD]
    /**
     * update InbdVis
     * @param param NLZC201001PMsg
     * @param i Int
     * @param oBatchType 
     */
    private void updateInbdVis(NLZC201001PMsg param, int i, ONBATCH_TYPE onBatchType) {

        String mode = NPZC109001Constant.POYO_HISTORY_MODE;

        NPZC109001PMsg pMsg = new NPZC109001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, mode);
        ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(0).poOrdNum, param.poRcvTrxHdrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(0).poOrdDtlLineNum, param.OrdInfoLIst.no(i).poRcvTrxLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.detailList.no(0).xxQty10Num, param.OrdInfoLIst.no(i).poRcvQty);
        pMsg.detailList.setValidCount(1);

        NPZC109001 dPZC109001 = new NPZC109001();
        dPZC109001.execute(pMsg, onBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {

            throw new S21AbendException(NPZM0132E);
        }
    }
    // END 2013/04/18 K.Kasai [QC1030,ADD]

    // QC1092 Add start
    // S-QC1388-MOD-20130725
    /**
     * checkAsnNumber
     * @param asnSoNum ASN_SO_NUM
     * @param asnLineNum ASN_LINE_NUM
     * @return check result
     */
    private boolean checkAsnNumber(String asnSoNum, String asnLineNum) {

        if (hasAsnNumber(asnSoNum, asnLineNum)) {

            return true;
        }

        if (!ZYPCommonFunc.hasValue(asnSoNum) && !ZYPCommonFunc.hasValue(asnLineNum)) {

            return true;
        }

        return false;
    }

    /**
     * hasAsnNumber
     * @param asnSoNum ASN_SO_NUM
     * @param asnLineNum ASN_LINE_NUM
     * @return true : has data / false : other
     */
    private boolean hasAsnNumber(String asnSoNum, String asnLineNum) {

        if (ZYPCommonFunc.hasValue(asnSoNum) && ZYPCommonFunc.hasValue(asnLineNum)) {

            return true;
        }

        return false;
    }
    // E-QC1388-MOD-20130725
    // QC1092 Add end
}
