/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1260.NPAL1260CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Button Action to Save Search option
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura        Create          N/A
 * 01/24/2017   CITS            T.Kikuhara      Update          QC#10621
 * 03/17/2020   Fujitsu         R.Nakamura      Update          QC#56104
 *</pre>
 */
public class NPAL1260Scrn00_SaveSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#10621 add start
        // Check Search Header Area
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqLineTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqLineStsCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HF);
        scrnMsg.addCheckItem(scrnMsg.prchReqCratDt_HT);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HF);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_HT);
        scrnMsg.addCheckItem(scrnMsg.shipDt_HF);
        scrnMsg.addCheckItem(scrnMsg.shipDt_HT);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.prchReqSrcTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.fsrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum_H1);
        // Mod Start 2020/03/17 QC#56104
//        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rwsRefNum_H1);
        // Mod End 2020/03/17 QC#56104
        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H2);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm_H1);
        scrnMsg.addCheckItem(scrnMsg.locNm_H1);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.techNm_H1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_H1);
        scrnMsg.addCheckItem(scrnMsg.carrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SE);
        scrnMsg.addCheckItem(scrnMsg.proNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H3);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H4);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
        scrnMsg.putErrorScreen();
        // QC#10621 add end
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        NPAL1260CMsg bizMsg = new NPAL1260CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        NPAL1260CMsg bizMsg = (NPAL1260CMsg) cMsg;

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
