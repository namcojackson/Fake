/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import static business.servlet.NPAL1270.constant.NPAL1270Constant.BIZ_APP_ID;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1270.NPAL1270CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 * 08/23/2017   CITS            H.Naoi          Update          Sol#097(QC#18398)
 * 02/01/2023   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1270Scrn00_SaveSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#10621 add start
        // Check Search Header Area
        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.prchReqNum);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_TO);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.trxRefNum);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        scrnMsg.addCheckItem(scrnMsg.mrpPlnNm);
        //08/10/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        scrnMsg.addCheckItem(scrnMsg.prchReqCratByPsnCd);
        scrnMsg.addCheckItem(scrnMsg.prchGrpCd_SL);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_TO);
        // START 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rqstShipDt_TO);
        // END 2023/02/01 S.Dong [QC#60966, ADD]
        scrnMsg.addCheckItem(scrnMsg.prchReqLineStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum);
        scrnMsg.addCheckItem(scrnMsg.prchReqApvlStsCd_SL);
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
        scrnMsg.addCheckItem(scrnMsg.carrNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        scrnMsg.addCheckItem(scrnMsg.vndNm);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhNm);
        scrnMsg.addCheckItem(scrnMsg.shipToLocNm);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
        scrnMsg.putErrorScreen();
        // QC#10621 add end
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;

        NPAL1270CMsg bizMsg = new NPAL1270CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        NPAL1270CMsg bizMsg  = (NPAL1270CMsg) cMsg;

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
