/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_CREATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_ADDR;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6820.NMAL6820CMsg;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Reset
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class NMAL6820Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        NMAL6820CommonLogic.clearScreenMsgForReset(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.whCd_H1);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        NMAL6820CMsg bizMsg = new NMAL6820CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {

            if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                // column and button input protection
                NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_CREATE);
            } else {

                // clear html attribute
                scrnMsg.clearAllGUIAttribute(SCRN_ID);

                // column and button input protection
                NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_UPDATE);
            }
        } else {

            // column and button input protection
            NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_CREATE);
        }
        // Tab focus
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ADDR);
        // cursor focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
    }
}