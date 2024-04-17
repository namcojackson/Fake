/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCRN_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.TAB_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Clear
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/22/2016   CITS            Makoto Okigami  Create          N/A
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0020Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        EZDBStringItem str = scrnMsg.xxComnColOrdTxt;
        String prchReqNumIP = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_IP)) {
            prchReqNumIP = scrnMsg.prchReqNum_IP.getValue();
        }

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.clear();

        NPBL0020CMsg bizMsg = new NPBL0020CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, str);
        ZYPEZDItemValueSetter.setValue(bizMsg.prchReqNum_IP, prchReqNumIP);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPBL0020CommonLogic.setCtrlScrnItemDispInit(this, scrnMsg);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

    }
}