/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import static business.servlet.NPBL0010.constant.NPBL0010Constant.BIZ_APP_ID;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0010.constant.NPBL0010Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0010.NPBL0010CMsg;
import business.servlet.NPBL0010.common.NPBL0010CommonLogic; // import
// business.servlet.NPBL0010.constant.NPBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0010 Inventory Request List
 * Function Name : Reset
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPBL0010Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        EZDBStringItem str = scrnMsg.xxComnColOrdTxt;

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.clear();

        NPBL0010CMsg bizMsg = new NPBL0010CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Set UserID
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, str);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        NPBL0010CMsg bizMsg = (NPBL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPBL0010CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);
    }
}
