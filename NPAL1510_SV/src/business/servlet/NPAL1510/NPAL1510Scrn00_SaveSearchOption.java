/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1510.constant.NPAL1510Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1510.NPAL1510CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : SaveSearchOption
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2017/01/24   CITS            T.Kikuhara      Update          QC#10621
 * 2017/08/24   CITS            S.Katsuma       Update          QC#20678
 * 2018/01/31   CITS            K.Mishiro       Update          QC#22521
 * 2018/02/27   CITS            T.Gotoda        Update          QC#22521-2
 * 2023/02/07   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */
public class NPAL1510Scrn00_SaveSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#10621 add start
        // Check Search Header Area
        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
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
        // START 2023/02/07 TZ.Win [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_TO);
        // END 2023/02/07 TZ.Win [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.aslMdseCd);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_SB);
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SL);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
     // START 2017/08/24 S.Katsuma [QC#20678,ADD]
        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        // END 2017/08/24 S.Katsuma [QC#20678,ADD]
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        // END 2018/01/31 K.Mishiro [QC#22521,ADD]
        scrnMsg.putErrorScreen();
        // QC#10621 add end
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd)){
            scrnMsg.coaProjDescTxt.clear();
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.coaProdCd)){
            scrnMsg.coaProdNm.clear();
        }
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;

        NPAL1510CMsg bizMsg = new NPAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.srchOptNm_TX.isError()) {
            scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
            scrnMsg.putErrorScreen();
        } else {
            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.srchOptNm_TX);
        }

    }
}
