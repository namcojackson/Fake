/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.BTN_CMN_CLOSE_EVENT_NM;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/02   Hitachi         Y.Takeno        Create          QC#21849(Sol#218)
 *</pre>
 */
public class NPAL1500_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500CMsg bizMsg = null;
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        if ((!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard()))) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd,scrnMsg.xxPopPrm_PG);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm, scrnMsg.xxPopPrm_P3);

            bizMsg = new NPAL1500CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
        if ((!BTN_CMN_CLOSE_EVENT_NM.equals(getLastGuard()))) {
            if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_PG)) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
            scrnMsg.setFocusItem(scrnMsg.shipToCustCd);
        }
    }
}
