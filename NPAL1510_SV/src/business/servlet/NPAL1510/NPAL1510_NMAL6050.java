/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : INMAL6050NIT
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2016/08/12   CITS            R.Shimamoto     Update          QC#13136
 * 2018/01/30   CITS            K.Mishiro       Update          QC#22521
 * 2018/02/27   CITS            T.Gotoda        Update          QC#22521-2
 * 
 *</pre>
 */
public class NPAL1510_NMAL6050 extends S21CommonHandler {

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
        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        String event = scrnMsg.Q.no(29).xxPopPrm.getValue();
        if (!CMN_CLOSE.equals(getLastGuard())) {
            //PRODUCT CODE
            if ("NPAL1510Scrn00_COA_Product_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd, scrnMsg.Q.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdNm, scrnMsg.Q.no(10).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.coaProdCd);
            //MERCHANDISE TYPE CODE
            } else if ("NPAL1510Scrn00_Merchandise_Type_Link".equals(event)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaMdseTpCd, scrnMsg.Q.no(9).xxPopPrm);
                ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjDescTxt, scrnMsg.Q.no(10).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.coaMdseTpCd);
            }
        }
    }
}
