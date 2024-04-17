/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_SWH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : SWH TAB
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 *</pre>
 */
public class NMAL6820Scrn00_TAB_SWH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_H1);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {

            // column and button input protection
            NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
        } else {

            // column and button input protection
            NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
        }

        // Set Screen Properties for Sub WH Detail
        NMAL6820CommonLogic.setScreenPropertiesForSwhDetail(this, scrnMsg);

        // Tab focus
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SWH);

        // cursor focus
        // Focus control
        if (scrnMsg.A.getValidCount() > 0) {

            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_P1);
        }
    }
}
