/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.ASL_MDSE_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.COA_PROD_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.DEST_RTL_SWH_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.DEST_RTL_WH_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.DS_PO_TP_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.FULL_PSN_NM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.MDSE_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.COA_MDSE_TP_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.NMAM0288E;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.NPAM0225E;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.ORD_LAST_UPD_TS_FROM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.ORD_LAST_UPD_TS_TO;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_ACK_NUM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_APVL_STS_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_HDR_STS_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_LINE_STS_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_ORD_NUM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_ORD_SRC_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_SEND_TS_FROM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_SEND_TS_TO;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_SUBMT_PSN_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_SUBMT_TS_FROM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PO_SUBMT_TS_TO;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PRCH_REQ_NUM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PRNT_VND_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PRNT_VND_NM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.PRO_NUM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.SHIP_TO_CUST_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.SHPG_SVC_LVL_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.TRX_REF_NUM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.VND_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.VND_INVTY_LOC_CD;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.VND_ISS_ORD_NUM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.VND_NM;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.VND_PO_ACK_STS_DESC_TXT;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.VND_SO_NUM;
//START 2023/02/09 TZ.Win [QC#60966, ADD]
import static business.servlet.NPAL1510.constant.NPAL1510Constant.RQST_SHIP_DT_FR;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.RQST_SHIP_DT_TO;
// END 2023/02/09 TZ.Win [QC#60966, ADD]
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDCommonFunc;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1510.NPAL1510CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : CMN_Download
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2017/08/24   CUSA            S.Katsuma       Update          QC#20678
 * 2018/01/31   CITS            K.Mishiro       Update          QC#22521
 * 2018/02/27   CITS            T.Gotoda        Update          QC#22521-2
 * 2023/02/09   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */
public class NPAL1510Scrn00_CMN_Download extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;

        // Input Attribute Check Area
        scrnMsg.addCheckItem(scrnMsg.poOrdNum);
        scrnMsg.addCheckItem(scrnMsg.poOrdSrcCd_SL);
        scrnMsg.addCheckItem(scrnMsg.dsPoTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.poHdrStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.poApvlStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.trxRefNum);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.vndNm);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustLocNm);
        scrnMsg.addCheckItem(scrnMsg.poSubmtPsnCd);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
        scrnMsg.addCheckItem(scrnMsg.poLineStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_TO);
        scrnMsg.addCheckItem(scrnMsg.poSubmtDt_FR);
        scrnMsg.addCheckItem(scrnMsg.poSubmtDt_TO);
        scrnMsg.addCheckItem(scrnMsg.vndPoAckStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.poAckNum);
        scrnMsg.addCheckItem(scrnMsg.vndIssOrdNum);
        scrnMsg.addCheckItem(scrnMsg.proNum);
        scrnMsg.addCheckItem(scrnMsg.vndSoNum);
        scrnMsg.addCheckItem(scrnMsg.vndInvtyLocCd);
        scrnMsg.addCheckItem(scrnMsg.xxOrdDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxOrdDt_TO);
        // START 2023/02/09 TZ.Win [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_TO);
        // END 2023/02/09 TZ.Win [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.aslMdseCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_SB);
        // START 2017/08/24 S.Katsuma [QC#20678,ADD]
        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        // END 2017/08/24 S.Katsuma [QC#20678,ADD]
        // START 2018/01/31 K.Mishiro [QC#22511,ADD]
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        // END 2018/01/31 K.Mishiro [QC#22511,ADD]

        // Business Specific Check Area
        if (!ZYPCommonFunc.hasValue(scrnMsg.poOrdNum) && !ZYPCommonFunc.hasValue(scrnMsg.poOrdSrcCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.dsPoTpCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.poHdrStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.poApvlStsCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.trxRefNum) && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.prntVndNm) && !ZYPCommonFunc.hasValue(scrnMsg.vndCd) && !ZYPCommonFunc.hasValue(scrnMsg.vndNm) && !ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd) && !ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm) && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd) && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustLocNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.poSubmtPsnCd) && !ZYPCommonFunc.hasValue(scrnMsg.poLineStsCd_SL) && !ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_TO)
                && !ZYPCommonFunc.hasValue(scrnMsg.poSubmtDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.poSubmtDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.fullPsnNm) && !ZYPCommonFunc.hasValue(scrnMsg.vndPoAckStsCd_SL)
                && !ZYPCommonFunc.hasValue(scrnMsg.poAckNum) && !ZYPCommonFunc.hasValue(scrnMsg.vndIssOrdNum) && !ZYPCommonFunc.hasValue(scrnMsg.proNum) && !ZYPCommonFunc.hasValue(scrnMsg.vndSoNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.vndInvtyLocCd) && !ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_TO)
                // START 2023/02/09 TZ.Win [QC#60966, ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstShipDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rqstShipDt_TO)
                // END 2023/02/09 TZ.Win [QC#60966, ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd) && !ZYPCommonFunc.hasValue(scrnMsg.aslMdseCd) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd_SB)
                // START 2018/01/31 K.Mishiro [QC#22521,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd) && !ZYPCommonFunc.hasValue(scrnMsg.coaProdCd)
                // END 2018/01/31 K.Mishiro [QC#22511,ADD]
                // START 2017/08/24 S.Katsuma [QC#20678,ADD]
                && !ZYPCommonFunc.hasValue(scrnMsg.prchReqNum)) {
                // END 2017/08/24 S.Katsuma [QC#20678,ADD]   
            scrnMsg.poOrdNum.setErrorInfo(1, NMAM0288E, new String[] {PO_ORD_NUM });
            scrnMsg.addCheckItem(scrnMsg.poOrdNum);
            scrnMsg.poOrdSrcCd_SL.setErrorInfo(1, NMAM0288E, new String[] {PO_ORD_SRC_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.poOrdSrcCd_SL);
            scrnMsg.dsPoTpCd_SL.setErrorInfo(1, NMAM0288E, new String[] {DS_PO_TP_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.dsPoTpCd_SL);
            scrnMsg.poHdrStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {PO_HDR_STS_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.poHdrStsCd_SL);
            scrnMsg.poApvlStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {PO_APVL_STS_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.poApvlStsCd_SL);
            scrnMsg.trxRefNum.setErrorInfo(1, NMAM0288E, new String[] {TRX_REF_NUM });
            scrnMsg.addCheckItem(scrnMsg.trxRefNum);
            scrnMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NMAM0288E, new String[] {SHPG_SVC_LVL_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
            scrnMsg.prntVndCd.setErrorInfo(1, NMAM0288E, new String[] {PRNT_VND_CD });
            scrnMsg.addCheckItem(scrnMsg.prntVndCd);
            scrnMsg.prntVndNm.setErrorInfo(1, NMAM0288E, new String[] {PRNT_VND_NM });
            scrnMsg.addCheckItem(scrnMsg.prntVndNm);
            scrnMsg.vndCd.setErrorInfo(1, NMAM0288E, new String[] {VND_CD });
            scrnMsg.addCheckItem(scrnMsg.vndCd);
            scrnMsg.vndNm.setErrorInfo(1, NMAM0288E, new String[] {VND_NM });
            scrnMsg.addCheckItem(scrnMsg.vndNm);
            scrnMsg.destRtlWhCd.setErrorInfo(1, NMAM0288E, new String[] {DEST_RTL_WH_CD });
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
            scrnMsg.rtlWhNm.setErrorInfo(1, NMAM0288E, new String[] {DEST_RTL_WH_CD });
            scrnMsg.addCheckItem(scrnMsg.rtlWhNm);
            scrnMsg.rtlSwhNm.setErrorInfo(1, NMAM0288E, new String[] {DEST_RTL_WH_CD });
            scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
            scrnMsg.destRtlSwhCd.setErrorInfo(1, NMAM0288E, new String[] {DEST_RTL_SWH_CD });
            scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
            scrnMsg.rtlSwhNm.setErrorInfo(1, NMAM0288E, new String[] {DEST_RTL_SWH_CD });
            scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
            scrnMsg.shipToCustCd.setErrorInfo(1, NMAM0288E, new String[] {SHIP_TO_CUST_CD });
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
            scrnMsg.shipToCustLocNm.setErrorInfo(1, NMAM0288E, new String[] {SHIP_TO_CUST_CD });
            scrnMsg.addCheckItem(scrnMsg.shipToCustLocNm);
            scrnMsg.poSubmtPsnCd.setErrorInfo(1, NMAM0288E, new String[] {PO_SUBMT_PSN_CD });
            scrnMsg.addCheckItem(scrnMsg.poSubmtPsnCd);
            scrnMsg.poLineStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {PO_LINE_STS_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.poLineStsCd_SL);
            scrnMsg.xxDt10Dt_FR.setErrorInfo(1, NMAM0288E, new String[] {PO_SEND_TS_FROM });
            scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_FR);
            scrnMsg.xxDt10Dt_TO.setErrorInfo(1, NMAM0288E, new String[] {PO_SEND_TS_TO });
            scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_TO);
            scrnMsg.fullPsnNm.setErrorInfo(1, NMAM0288E, new String[] {FULL_PSN_NM });
            scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
            scrnMsg.poSubmtDt_FR.setErrorInfo(1, NMAM0288E, new String[] {PO_SUBMT_TS_FROM });
            scrnMsg.addCheckItem(scrnMsg.poSubmtDt_FR);
            scrnMsg.poSubmtDt_TO.setErrorInfo(1, NMAM0288E, new String[] {PO_SUBMT_TS_TO });
            scrnMsg.addCheckItem(scrnMsg.poSubmtDt_TO);
            scrnMsg.vndPoAckStsCd_SL.setErrorInfo(1, NMAM0288E, new String[] {VND_PO_ACK_STS_DESC_TXT });
            scrnMsg.addCheckItem(scrnMsg.vndPoAckStsCd_SL);
            scrnMsg.poAckNum.setErrorInfo(1, NMAM0288E, new String[] {PO_ACK_NUM });
            scrnMsg.addCheckItem(scrnMsg.poAckNum);
            scrnMsg.vndIssOrdNum.setErrorInfo(1, NMAM0288E, new String[] {VND_ISS_ORD_NUM });
            scrnMsg.addCheckItem(scrnMsg.vndIssOrdNum);
            scrnMsg.proNum.setErrorInfo(1, NMAM0288E, new String[] {PRO_NUM });
            scrnMsg.addCheckItem(scrnMsg.proNum);
            scrnMsg.vndSoNum.setErrorInfo(1, NMAM0288E, new String[] {VND_SO_NUM });
            scrnMsg.addCheckItem(scrnMsg.vndSoNum);
            scrnMsg.vndInvtyLocCd.setErrorInfo(1, NMAM0288E, new String[] {VND_INVTY_LOC_CD });
            scrnMsg.addCheckItem(scrnMsg.vndInvtyLocCd);
            scrnMsg.xxOrdDt_FR.setErrorInfo(1, NMAM0288E, new String[] {ORD_LAST_UPD_TS_FROM });
            scrnMsg.addCheckItem(scrnMsg.xxOrdDt_FR);
            scrnMsg.xxOrdDt_TO.setErrorInfo(1, NMAM0288E, new String[] {ORD_LAST_UPD_TS_TO });
            scrnMsg.addCheckItem(scrnMsg.xxOrdDt_TO);
            // START 2023/02/09 TZ.Win [QC#60966, ADD]
            scrnMsg.rqstShipDt_FR.setErrorInfo(1, NMAM0288E, new String[] {RQST_SHIP_DT_FR});
            scrnMsg.rqstShipDt_TO.setErrorInfo(1, NMAM0288E, new String[] {RQST_SHIP_DT_TO});
            // END 2023/02/09 TZ.Win [QC#60966, ADD]
            scrnMsg.mdseCd.setErrorInfo(1, NMAM0288E, new String[] {MDSE_CD });
            scrnMsg.addCheckItem(scrnMsg.mdseCd);
            scrnMsg.aslMdseCd.setErrorInfo(1, NMAM0288E, new String[] {ASL_MDSE_CD });
            scrnMsg.addCheckItem(scrnMsg.aslMdseCd);
            scrnMsg.mdseCd_SB.setErrorInfo(1, NMAM0288E);
            scrnMsg.addCheckItem(scrnMsg.mdseCd_SB);
            // START 2017/08/24 S.Katsuma [QC#20678,ADD]
            scrnMsg.prchReqNum.setErrorInfo(1, NMAM0288E, new String[] {PRCH_REQ_NUM });
            scrnMsg.addCheckItem(scrnMsg.prchReqNum);
            // END 2017/08/24 S.Katsuma [QC#20678,ADD]
            // START 2018/01/31 K.Mishiro [QC#22521,ADD]
            scrnMsg.coaMdseTpCd.setErrorInfo(1, NMAM0288E, new String[] {COA_MDSE_TP_CD });
            scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd);
            scrnMsg.coaProdCd.setErrorInfo(1, NMAM0288E, new String[] {COA_PROD_CD });
            scrnMsg.addCheckItem(scrnMsg.coaProdCd);
            // END 2018/01/31 K.Mishiro [QC#22521,ADD]
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.poSubmtDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.poSubmtDt_TO)) {
            String poSubmtTsFr = scrnMsg.poSubmtDt_FR.getValue();
            String poSubmtTsTo = scrnMsg.poSubmtDt_TO.getValue();
            if (EZDCommonFunc.cmpDate(poSubmtTsFr, poSubmtTsTo) == 1) {
                scrnMsg.poSubmtDt_FR.setErrorInfo(1, NPAM0225E, new String[] {PO_SUBMT_TS_FROM });
                scrnMsg.addCheckItem(scrnMsg.poSubmtDt_FR);
                scrnMsg.poSubmtDt_TO.setErrorInfo(1, NPAM0225E, new String[] {PO_SUBMT_TS_TO });
                scrnMsg.addCheckItem(scrnMsg.poSubmtDt_TO);
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxDt10Dt_TO)) {
            String poSendTsFr = scrnMsg.xxDt10Dt_FR.getValue();
            String poSendTsTo = scrnMsg.xxDt10Dt_TO.getValue();
            if (EZDCommonFunc.cmpDate(poSendTsFr, poSendTsTo) == 1) {
                scrnMsg.xxDt10Dt_FR.setErrorInfo(1, NPAM0225E, new String[] {PO_SEND_TS_FROM });
                scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_FR);
                scrnMsg.xxDt10Dt_TO.setErrorInfo(1, NPAM0225E, new String[] {PO_SEND_TS_TO });
                scrnMsg.addCheckItem(scrnMsg.xxDt10Dt_TO);
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxOrdDt_TO)) {
            String ordLastUpdTsFo = scrnMsg.xxOrdDt_FR.getValue();
            String ordLastUpdTsTo = scrnMsg.xxOrdDt_TO.getValue();
            if (EZDCommonFunc.cmpDate(ordLastUpdTsFo, ordLastUpdTsTo) == 1) {
                scrnMsg.xxOrdDt_FR.setErrorInfo(1, NPAM0225E, new String[] {ORD_LAST_UPD_TS_FROM });
                scrnMsg.addCheckItem(scrnMsg.xxOrdDt_FR);
                scrnMsg.xxOrdDt_TO.setErrorInfo(1, NPAM0225E, new String[] {ORD_LAST_UPD_TS_TO });
                scrnMsg.addCheckItem(scrnMsg.xxOrdDt_TO);
            }
        }
        // START 2023/02/09 TZ.Win [QC#60966, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstShipDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rqstShipDt_TO)) {
            String rqstShipDtFr = scrnMsg.rqstShipDt_FR.getValue();
            String rqstShipDtTo = scrnMsg.rqstShipDt_TO.getValue();
            if (EZDCommonFunc.cmpDate(rqstShipDtFr, rqstShipDtTo) == 1) {
                scrnMsg.rqstShipDt_FR.setErrorInfo(1, NPAM0225E, new String[] {RQST_SHIP_DT_FR });
                scrnMsg.addCheckItem(scrnMsg.rqstShipDt_FR);
                scrnMsg.rqstShipDt_TO.setErrorInfo(1, NPAM0225E, new String[] {RQST_SHIP_DT_TO });
                scrnMsg.addCheckItem(scrnMsg.rqstShipDt_TO);
            }
        }
        // END 2023/02/09 TZ.Win [QC#60966, ADD]
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd)){
            scrnMsg.coaProjDescTxt.clear();
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.coaProdCd)){
            scrnMsg.coaProdNm.clear();
        }
        // END 2018/01/31 K.Mishiro [QC#22521,ADD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Call Blap.
        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        NPAL1510CMsg bizMsg = new NPAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!"E".equals(bizMsg.getMessageKind())) {
            // execute file download
            String tempFilePath = scrnMsg.xxFileData.getTempFilePath();
            executeDownload(tempFilePath, true, ZYPCSVOutFile.getDialogFileName(tempFilePath));
        }

        // Set focus.
        scrnMsg.setFocusItem(scrnMsg.poOrdNum);

    }
}
