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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * PO Requisition List
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 * 06/26/2018   CITS            Y.Iwasaki       Update          QC#26548
 * 09/20/2019   CITS            R.Shimamoto     Update          QC#52362
 *</pre>
 */
public class NPAL1270_NMAL6760 extends S21CommonHandler {

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

            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_PG);
            // 2019/09/20 QC#52362 Mod Start
//            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm, scrnMsg.xxPopPrm_P1);
            String shipToCustLocNm = "";
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P1)) {
            	shipToCustLocNm = scrnMsg.xxPopPrm_P1.getValue();
            	if (shipToCustLocNm.length() > 60) {
            		shipToCustLocNm = shipToCustLocNm.substring(0, 60);
            	}
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm, shipToCustLocNm);
            // 2019/09/20 QC#52362 Mod End

            scrnMsg.setFocusItem(scrnMsg.shipToCustCd);
        }

    }
}
