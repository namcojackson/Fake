/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : NMAL1370 Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1070_NPAL1370 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg = new NPAL1070CMsg();

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C0) && !ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C1) && !ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C2) && !ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C3)) {
            return null;
        }

        // From WH / SWH
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.xxPopPrm_C0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.xxPopPrm_C1);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C4)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mrpEnblFlg, scrnMsg.xxPopPrm_C4);
        }

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg  = (NPAL1070CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C0) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C1) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C2) && ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_C3)) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1070CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Focus (Plan Name)
        scrnMsg.setFocusItem(scrnMsg.mrpPlnNm);

    }
}
