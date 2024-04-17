/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1510.NPAL1510CMsg;
import business.servlet.NPAL1510.common.NPAL1510CommonLogic;
import business.servlet.NPAL1510.constant.NPAL1510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : INIT
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2017/08/24   CUSA            S.Katsuma       Update          QC#20678
 * 2017/10/24   CITS            K.Ogino         Update          QC#20737
 * 2017/10/24   CITS            K.Ogino         Update          QC#20737
 * 2017/11/16   CITS            S.Katsuma       Update          QC#22296
 * 12/25/2017   CITS            K.Ogino         Update          QC#21908
 * 2018/01/31   CITS            K.Mishiro       Update          QC#22521
 * 2018/02/27   CITS            T.Gotoda        Update          QC#22521-2
 * 2023/02/07   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */
public class NPAL1510_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NPAL1510Constant.BUSINESS_APPL_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        // QC#20737. mod QC#21908
        if (params != null && params.length > 0) {
            // CPO Parameter
            if (params.length == 1) {
                Object param01 = (Object) params[0];
                if (param01 instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum, (EZDBStringItem) param01);
                }
            } else if (params.length == 4) {
                // Tech Req Parameters
                Object param01 = (Object) params[0];
                if (param01 instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdNum, (EZDBStringItem) param01);
                }
                Object param02 = (Object) params[1];
                if (param02 instanceof String) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.poOrdSrcCd_SL, (String) param02);
                }
                Object param03 = (Object) params[2];
                if (param03 instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.trxRefNum, (EZDBStringItem) param03);
                }
                Object param04 = (Object) params[3];
                if (param04 instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.vndSoNum, (EZDBStringItem) param04);
                }
            }
        }

        NPAL1510CMsg bizMsg = new NPAL1510CMsg();
        if (ZYPCommonFunc.hasValue(scrnMsg.poOrdNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.poOrdNum, scrnMsg.poOrdNum);
        }
        bizMsg.setBusinessID(NPAL1510Constant.BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(NPAL1510Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // QC#20737 mod start
        if (ZYPCommonFunc.hasValue(bizMsg.xxDplyByCtrlItemCdNm)) {
            scrnMsg.xxDplyByCtrlItemCdNm.setValue(bizMsg.xxDplyByCtrlItemCdNm.getValue());
        } else {
            scrnMsg.xxDplyByCtrlItemCdNm.setValue(NPAL1510Constant.DISPLAY_LEVEL_DEFAULT);
        }
        // QC#20737 mod end
        scrnMsg.setFocusItem(scrnMsg.poOrdNum);
        NPAL1510CommonLogic.initCommonButton(this);
        // START 2017/11/16 S.Katsuma [QC#22296,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.poOrdNum)) {
            NPAL1510CommonLogic.setTableSettings(scrnMsg, scrnMsg.xxDplyByCtrlItemCdNm.getValue());
            NPAL1510CommonLogic.setPoDetailFocus(scrnMsg);

            int length = scrnMsg.A.getValidCount();
            for (int i = 0; i < length; i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrTrkUrl_A1.getValue())) {
                    scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(true);
                    EZDGUIAttribute link = new EZDGUIAttribute(BUSINESS_SCREEN_ID,"carrTrk"+i);
                    link.setStyleAttribute("color", "black");
                    link.setStyleAttribute("text-decoration", "none");
                    scrnMsg.addGUIAttribute(link);
                }
            }
        } else {
         // Clear Attribute
            scrnMsg.clearAllGUIAttribute(NPAL1510Constant.BUSINESS_SCREEN_ID);
        }
        // END 2017/11/16 S.Katsuma [QC#22296,ADD]
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        scrnMsg.coaProjDescTxt.setInputProtected(true);
        scrnMsg.coaProdNm.setInputProtected(true);
        // END 2018/01/31 K.Mishiro [QC#22521,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        // QC#10621 mod start
        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) arg0;
        scrnMsg.poOrdNum.setNameForMessage(NPAL1510Constant.PO_ORD_NUM);
        scrnMsg.poOrdSrcCd_SL.setNameForMessage(NPAL1510Constant.PO_ORD_SRC_DESC_TXT);
        scrnMsg.dsPoTpCd_SL.setNameForMessage(NPAL1510Constant.DS_PO_TP_DESC_TXT);
        scrnMsg.poHdrStsCd_SL.setNameForMessage(NPAL1510Constant.PO_HDR_STS_DESC_TXT);
        scrnMsg.poApvlStsCd_SL.setNameForMessage(NPAL1510Constant.PO_APVL_STS_DESC_TXT);
        scrnMsg.trxRefNum.setNameForMessage(NPAL1510Constant.TRX_REF_NUM);
        scrnMsg.shpgSvcLvlCd_SL.setNameForMessage(NPAL1510Constant.SHPG_SVC_LVL_DESC_TXT);
        scrnMsg.prntVndCd.setNameForMessage(NPAL1510Constant.PRNT_VND_CD);
        scrnMsg.prntVndNm.setNameForMessage(NPAL1510Constant.PRNT_VND_NM);
        scrnMsg.vndCd.setNameForMessage(NPAL1510Constant.VND_CD);
        scrnMsg.vndNm.setNameForMessage(NPAL1510Constant.VND_NM);
        scrnMsg.destRtlWhCd.setNameForMessage(NPAL1510Constant.DEST_RTL_WH_CD);
        scrnMsg.rtlWhNm.setNameForMessage(NPAL1510Constant.RTL_WH_NM);
        scrnMsg.destRtlSwhCd.setNameForMessage(NPAL1510Constant.DEST_RTL_SWH_CD);
        scrnMsg.rtlSwhNm.setNameForMessage(NPAL1510Constant.RTL_SWH_NM);
        scrnMsg.shipToCustCd.setNameForMessage(NPAL1510Constant.SHIP_TO_CUST_CD);
        scrnMsg.shipToCustLocNm.setNameForMessage(NPAL1510Constant.SHIP_TO_CUST_LOC_NM);
        scrnMsg.poSubmtPsnCd.setNameForMessage(NPAL1510Constant.PO_SUBMT_PSN_CD);
        scrnMsg.fullPsnNm.setNameForMessage(NPAL1510Constant.FULL_PSN_NM);
        scrnMsg.poLineStsCd_SL.setNameForMessage(NPAL1510Constant.PO_LINE_STS_DESC_TXT);
        scrnMsg.xxDt10Dt_FR.setNameForMessage(NPAL1510Constant.PO_SEND_TS_FROM);
        scrnMsg.xxDt10Dt_TO.setNameForMessage(NPAL1510Constant.PO_SEND_TS_TO);
        scrnMsg.poSubmtDt_FR.setNameForMessage(NPAL1510Constant.PO_SUBMT_TS_FROM);
        scrnMsg.poSubmtDt_TO.setNameForMessage(NPAL1510Constant.PO_SUBMT_TS_TO);
        scrnMsg.vndPoAckStsCd_SL.setNameForMessage(NPAL1510Constant.VND_PO_ACK_STS_DESC_TXT);
        scrnMsg.poAckNum.setNameForMessage(NPAL1510Constant.PO_ACK_NUM);
        scrnMsg.vndIssOrdNum.setNameForMessage(NPAL1510Constant.VND_ISS_ORD_NUM);
        scrnMsg.proNum.setNameForMessage(NPAL1510Constant.PRO_NUM);
        scrnMsg.vndSoNum.setNameForMessage(NPAL1510Constant.VND_SO_NUM);
        scrnMsg.vndInvtyLocCd.setNameForMessage(NPAL1510Constant.VND_INVTY_LOC_CD);
        scrnMsg.xxOrdDt_FR.setNameForMessage(NPAL1510Constant.ORD_LAST_UPD_TS_FROM);
        scrnMsg.xxOrdDt_TO.setNameForMessage(NPAL1510Constant.ORD_LAST_UPD_TS_TO);
        // START 2023/02/07 TZ.Win [QC#60966, ADD]
        scrnMsg.rqstShipDt_FR.setNameForMessage(NPAL1510Constant.RQST_SHIP_DT_FR);
        scrnMsg.rqstShipDt_TO.setNameForMessage(NPAL1510Constant.RQST_SHIP_DT_TO);
        // END 2023/02/07 TZ.Win [QC#60966, ADD]
        scrnMsg.mdseCd.setNameForMessage(NPAL1510Constant.MDSE_CD);
        scrnMsg.aslMdseCd.setNameForMessage(NPAL1510Constant.ASL_MDSE_CD);
        scrnMsg.mdseCd_SB.setNameForMessage(NPAL1510Constant.MDSE_CD_SB);
        scrnMsg.srchOptPk_SL.setNameForMessage(NPAL1510Constant.SRCH_OPT);
        scrnMsg.srchOptNm_TX.setNameForMessage(NPAL1510Constant.SRCH_OPT_NM);
        // QC#10621 mod end
        // START 2017/08/24 S.Katsuma [QC#20678,ADD]
        scrnMsg.prchReqNum.setNameForMessage(NPAL1510Constant.PRCH_REQ_NUM);
        // END 2017/08/24 S.Katsuma [QC#20678,ADD]
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        scrnMsg.coaMdseTpCd.setNameForMessage(NPAL1510Constant.COA_MDSE_TP_CD);
        scrnMsg.coaProdCd.setNameForMessage(NPAL1510Constant.COA_PROD_CD);
        // ENDT 2018/01/31 K.Mishiro [QC#22521,ADD]
    }
}
