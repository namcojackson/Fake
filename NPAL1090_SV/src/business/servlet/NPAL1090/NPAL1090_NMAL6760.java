/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Return Action from NMAL6760(Account Search Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/08/2016   CITS       Takeshi Tokutomi       Create          N/A
 * 09/26/2019   Fujitsu    T.Ogura                Update          QC#52362
 *</pre>
 */
public class NPAL1090_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            // scrnMsg.O.no(16).xxPopPrm_O1, scrnMsg.shipToCustCd_H1
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, scrnMsg.O.no(16).xxPopPrm_O1);
            // scrnMsg.O.no(1).xxPopPrm_O1 =  scrnMsg.shipToLocNm_P3
            // START 2019/09/26 T.Ogura [QC#52362,MOD]
//            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, scrnMsg.O.no(1).xxPopPrm_O1);
            String shipToCustLocNm = "";
            if (ZYPCommonFunc.hasValue(scrnMsg.O.no(1).xxPopPrm_O1)) {
                shipToCustLocNm = scrnMsg.O.no(1).xxPopPrm_O1.getValue();
                if (shipToCustLocNm.length() > 60) {
                    shipToCustLocNm = shipToCustLocNm.substring(0, 60);
                }
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, shipToCustLocNm);
            // END   2019/09/26 T.Ogura [QC#52362,MOD]
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd_SE);
        }
    }
}
