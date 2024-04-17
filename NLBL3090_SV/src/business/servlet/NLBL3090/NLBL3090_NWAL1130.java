/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3090.NLBL3090CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NLBL3090.constant.NLBL3090Constant.BUSINESS_APPLICATION_ID;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 * 04/12/2016   CSAI            D.Fukaya        Update          QC#6088
 *</pre>
 */
public class NLBL3090_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm_X.getValue();

        if (!"CMN_Close".equals(getLastGuard())) {

            String rtnVal = scrnMsg.P.no(0).xxComnScrColValTxt.getValue();

            if ("OpenWin_S21PersonM".equals(scrEventNm)) {
                int selectIdx = getButtonSelectNumber();
                scrnMsg.A.no(selectIdx).mgrPsnCd_AM.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).mgrPsnCd_AM);

            } else if ("OpenWin_S21PersonS".equals(scrEventNm)) {
                int selectIdx = getButtonSelectNumber();
                scrnMsg.A.no(selectIdx).supvPsnCd_AS.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).supvPsnCd_AS);

            } else if ("OpenWin_CdPerson".equals(scrEventNm)) {
                int selectIdx = getButtonSelectNumber();
                if ("AC".equals(scrnMsg.xxPopPrm_X.getValue())) {
                    scrnMsg.A.no(selectIdx).schdCoordPsnCd_AC.setValue(rtnVal);
                    scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).schdCoordPsnCd_AC);

                } else if ("BC".equals(scrnMsg.xxPopPrm_X.getValue())) {
                    scrnMsg.B.no(selectIdx).schdCoordPsnCd_BC.setValue(rtnVal);
                    scrnMsg.setFocusItem(scrnMsg.B.no(selectIdx).schdCoordPsnCd_BC);
                }

            } else if ("OpenWin_Carrier".equals(scrEventNm)) {
                int selectIdx = getButtonSelectNumber();
                scrnMsg.B.no(selectIdx).carrCd_B.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.B.no(selectIdx).carrCd_B);

            } else if ("NLBL3090Scrn00_OpenWin_Manager".equals(scrEventNm)) {
                scrnMsg.mgrPsnCd_H1.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.mgrPsnCd_H1);

            } else if ("NLBL3090Scrn00_OpenWin_Supervisor".equals(scrEventNm)) {
                scrnMsg.supvPsnCd_H1.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.supvPsnCd_H1);

            } else if ("NLBL3090Scrn00_OpenWin_Coordinator".equals(scrEventNm)) {
                scrnMsg.schdCoordPsnCd_H1.setValue(rtnVal);
                scrnMsg.setFocusItem(scrnMsg.schdCoordPsnCd_H1);
            }
        }

        NLBL3090CMsg bizMsg = new NLBL3090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3090BMsg scrnMsg = (NLBL3090BMsg) bMsg;
        NLBL3090CMsg bizMsg = (NLBL3090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }

}
