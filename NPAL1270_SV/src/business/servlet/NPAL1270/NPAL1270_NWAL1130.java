/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1270.NPAL1270CMsg;
//import business.servlet.NPAL1270.constant.NPAL1270Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 03/01/2016   CITS            K.Ogino         Update          QC#4602
 *</pre>
 */
public class NPAL1270_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {

            NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
            String scrEventNm = scrnMsg.xxScrEventNm_P1.getValue();

            if ("OpenWin_Supplier".equals(scrEventNm)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndCd, scrnMsg.P.no(2).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndNm, scrnMsg.P.no(3).xxComnScrColValTxt.getValue());

                scrnMsg.setFocusItem(scrnMsg.vndCd);

            } else if ("OpenWin_Requester".equals(scrEventNm)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqCratByPsnCd, scrnMsg.P.no(0).xxComnScrColValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm, scrnMsg.P.no(1).xxComnScrColValTxt.getValue());

                scrnMsg.setFocusItem(scrnMsg.fullPsnNm);
            }
        }

    }
}
