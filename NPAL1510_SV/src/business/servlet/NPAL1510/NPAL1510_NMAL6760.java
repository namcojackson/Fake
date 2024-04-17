/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * Function Name : NMAL6760
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2018/03/09   CITS            M.Naito         Update          QC#22590
 * 2019/09/20   CITS            R.Shimamoto     Update          QC#52362
 *</pre>
 */
public class NPAL1510_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        // QC#22590 mod start
        // 2019/09/20 QC#52362 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocNm, scrnMsg.xxPopPrm_P1);
        String shipToCustLocNm = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P1)) {
        	shipToCustLocNm = scrnMsg.xxPopPrm_P1.getValue();
        	if (shipToCustLocNm.length() > 60) {
        		shipToCustLocNm = shipToCustLocNm.substring(0, 60);
        	}
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocNm, shipToCustLocNm);
        // 2019/09/20 QC#52362 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_PG);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_P2);
        // QC#22590 mod end
        scrnMsg.setFocusItem(scrnMsg.shipToCustCd);

    }
}
