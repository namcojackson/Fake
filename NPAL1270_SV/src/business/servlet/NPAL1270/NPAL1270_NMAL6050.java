/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 01/29/2018   CITS            T.Gotoda        Update          QC#22521
 *</pre>
 */
public class NPAL1270_NMAL6050 extends S21CommonHandler {

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

            if ("OpenWin_Carrier".equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd, scrnMsg.xxCondCd_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm, scrnMsg.xxCondNm_P1);
                scrnMsg.setFocusItem(scrnMsg.carrNm);

            } else if ("OpenWin_Supplier".equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.xxCondCd_P1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm, scrnMsg.xxCondNm_P1);
                scrnMsg.setFocusItem(scrnMsg.prntVndCd);

            } else if ("OpenWin_MerchandiseType".equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaMdseTpCd, scrnMsg.Q.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjDescTxt, scrnMsg.Q.no(10).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.coaMdseTpCd);

            } else if ("OpenWin_ProductCode".equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd, scrnMsg.Q.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm, scrnMsg.Q.no(10).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.coaProdCd);
            }
        }


    }
}
