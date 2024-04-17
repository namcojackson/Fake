/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600;

import static business.servlet.NLCL0600.constant.NLCL0600Constant.BIZ_APP_ID;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.SCRN_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0600.NLCL0600CMsg;
import business.servlet.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : Reset
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0600Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        String physInvtyNum = scrnMsg.physInvtyNum_IP.getValue();

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyNum_IP, physInvtyNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyNum, physInvtyNum);

        NLCL0600CMsg bizMsg = new NLCL0600CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;
        NLCL0600CMsg bizMsg  = (NLCL0600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NLCL0600CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

    }
}
