/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6870;

import static business.servlet.NMAL6870.constant.NMAL6870Constant.SCRN_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6870.NMAL6870CMsg;
import business.servlet.NMAL6870.common.NMAL6870CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 *</pre>
 */
public class NMAL6870_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no check function id
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.H);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.S);
        ZYPTableUtil.clear(scrnMsg.R);

        if (arg instanceof Object[]) {
            NMAL6870CommonLogic.setInputParam(scrnMsg, (Object[]) arg, getGlobalCompanyCode());
        }

        EZDDebugOutput.println(1, "NMAL6870 Input Parameters:" + scrnMsg.toString(), scrnMsg.toString());
        NMAL6870CommonLogic.convLabelNames(scrnMsg);
        NMAL6870CommonLogic.prepareScreenItem(scrnMsg);

        if (NMAL6870CommonLogic.needsInitialSearch(scrnMsg)) {
            // prepare business logic call
            NMAL6870CMsg bizMsg = NMAL6870CommonLogic.setRequestData_NMAL6870Scrn00_Search();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;
        NMAL6870CMsg bizMsg = (NMAL6870CMsg) cMsg;

        if (bizMsg != null) {
            // copy data from CMsg onto BMsg
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.setDisplayPageName(scrnMsg.xxScrNm.getValue());

        NMAL6870CommonLogic.setTableBGColor(scrnMsg);

        NMAL6870CommonLogic.initDisplayInfo(this, scrnMsg);

        NMAL6870CommonLogic.selection(scrnMsg);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.xxComnScrCondValTxt_H0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        scrnMsg.xxComnScrCondValTxt_H0.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H0.getValue());
        scrnMsg.xxComnScrCondValTxt_H1.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H1.getValue());
        scrnMsg.xxComnScrCondValTxt_H2.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H2.getValue());
        scrnMsg.xxComnScrCondValTxt_H3.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H3.getValue());
        scrnMsg.xxComnScrCondValTxt_H4.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H4.getValue());
        scrnMsg.xxComnScrCondValTxt_H5.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H5.getValue());
        scrnMsg.xxComnScrCondValTxt_H6.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H6.getValue());
        scrnMsg.xxComnScrCondValTxt_H7.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H7.getValue());
        scrnMsg.xxComnScrCondValTxt_H8.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H8.getValue());
        scrnMsg.xxComnScrCondValTxt_H9.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H9.getValue());
        scrnMsg.xxComnScrCondValTxt_HA.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_HA.getValue());
        scrnMsg.xxComnScrCondValTxt_HB.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_HB.getValue());
    }
}
