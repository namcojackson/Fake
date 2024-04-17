/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/20   CITS            K.Kameoka       Create          QC#26990
 * 2019/09/20   CITS            R.Shimamoto     Update          QC#52362
 *</pre>
 */
public class NPAL1280_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

     }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280CMsg bizMsg = null;
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        if ((!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard()))) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd,scrnMsg.xxPopPrm_PG);
            // 2019/09/20 QC#52362 Mod Start
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocNm, scrnMsg.xxPopPrm_P1.getValue());
            String shipToCustLocNm = "";
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_P1)) {
            	shipToCustLocNm = scrnMsg.xxPopPrm_P1.getValue();
            	if (shipToCustLocNm.length() > 60) {
            		shipToCustLocNm = shipToCustLocNm.substring(0, 60);
            	}
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocNm, shipToCustLocNm);
            // 2019/09/20 QC#52362 Mod End

            bizMsg = new NPAL1280CMsg();
            bizMsg.setBusinessID(BUSINESS_APPL_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        if ((!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard()))) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_PG)) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
            scrnMsg.setFocusItem(scrnMsg.shipToCustCd);
        }

    }
}
