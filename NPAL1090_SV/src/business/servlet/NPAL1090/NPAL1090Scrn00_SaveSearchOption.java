/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1090.NPAL1090CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Button Action to Save Search option
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 *</pre>
 */
public class NPAL1090Scrn00_SaveSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#10621 add start
        // Check Search Header Area
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqStsCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqApvlStsCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HF);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HT);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.fsrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HF);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HT);
        scrnMsg.addCheckItem(scrnMsg.techNm_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.locNm_H1);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SE);
        scrnMsg.addCheckItem(scrnMsg.carrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
        scrnMsg.putErrorScreen();
        // QC#10621 add end
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;

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
