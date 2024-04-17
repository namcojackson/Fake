/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NWAL1130;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1130.NWAL1130CMsg;
import business.servlet.NWAL1130.common.NWAL1130CommonLogic;
import business.servlet.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 *  Common PopUp NWAL1130_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2012   Fujitsu         T.Ishii         Create          N/A
 * 12/18/2012   Fujitsu         F.Saito         Update          WDS Defect#9
 * 09/20/2013   Hitachi         T.Arakawa       Update          WDS Defect#2453
 * 11/18/2013   Fujitsu         T.Tozuka        Update          WDS Defect#2852
 * 02/19/2016   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/07/25   Hitachi         T.Tomita        Update          QC#11471
 *</pre>
 */
public class NWAL1130_INIT extends S21INITCommonHandler implements NWAL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no check function id
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.H);
        ZYPTableUtil.clear(scrnMsg.C);
        ZYPTableUtil.clear(scrnMsg.S);
        ZYPTableUtil.clear(scrnMsg.R);

        if (arg instanceof Object[]) {

            NWAL1130CommonLogic.setInputParam(scrnMsg, (Object[]) arg, getGlobalCompanyCode());
        }
        EZDDebugOutput.println(1, "NWAL1130 Input Parameters:" + scrnMsg.toString(), scrnMsg.toString());
        // START 2013/11/19 T.Tozuka [Defect#2852 Add]
        NWAL1130CommonLogic.convLabelNames(scrnMsg);
        // END   2013/11/19 T.Tozuka [Defect#2852 Add]
        NWAL1130CommonLogic.prepareScreenItem(scrnMsg);

        if (NWAL1130CommonLogic.needsInitialSearch(scrnMsg)) {
            // prepare business logic call
            NWAL1130CMsg bizMsg = NWAL1130CommonLogic.setRequestData_NWAL1130Scrn00_Search();

            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;
        NWAL1130CMsg bizMsg = (NWAL1130CMsg) cMsg;

        if (bizMsg != null) {
            // copy data from CMsg onto BMsg
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // START 2013/11/19 T.Tozuka [Defect#2852 Del]
        // (Move to setRequestData)
//        // S-ADD-20130920 WDS Defect#2453
//        NWAL1130CommonLogic.convLabelNames(scrnMsg);
//        // E-ADD-20130920 WDS Defect#2453
        // END   2013/11/19 T.Tozuka [Defect#2852 Del]

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        scrnMsg.setDisplayPageName(scrnMsg.xxScrNm.getValue());

        NWAL1130CommonLogic.setTableBGColor(scrnMsg);

        NWAL1130CommonLogic.initDisplayInfo(this, scrnMsg);

        // set focus
        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
        //scrnMsg.setFocusItem(scrnMsg.xxComnScrCondValTxt_H0);
        scrnMsg.setFocusItem(scrnMsg.xxScrItem500Txt_H0);
        // End 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        // START 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
//        scrnMsg.xxComnScrCondValTxt_H0.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H0.getValue());
//        scrnMsg.xxComnScrCondValTxt_H1.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H1.getValue());
//        scrnMsg.xxComnScrCondValTxt_H2.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H2.getValue());
//        scrnMsg.xxComnScrCondValTxt_H3.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H3.getValue());
//        scrnMsg.xxComnScrCondValTxt_H4.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H4.getValue());
//        // S21 CSA Add Start
//        scrnMsg.xxComnScrCondValTxt_H5.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H5.getValue());
//        scrnMsg.xxComnScrCondValTxt_H6.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H6.getValue());
//        scrnMsg.xxComnScrCondValTxt_H7.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H7.getValue());
//        scrnMsg.xxComnScrCondValTxt_H8.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H8.getValue());
//        scrnMsg.xxComnScrCondValTxt_H9.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H9.getValue());
//        scrnMsg.xxComnScrCondValTxt_HA.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_HA.getValue());
//        scrnMsg.xxComnScrCondValTxt_HB.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_HB.getValue());
//        // S21 CSA Add End
        scrnMsg.xxScrItem500Txt_H0.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H0.getValue());
        scrnMsg.xxScrItem500Txt_H1.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H1.getValue());
        scrnMsg.xxScrItem500Txt_H2.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H2.getValue());
        scrnMsg.xxScrItem500Txt_H3.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H3.getValue());
        scrnMsg.xxScrItem500Txt_H4.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H4.getValue());
        scrnMsg.xxScrItem500Txt_H5.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H5.getValue());
        scrnMsg.xxScrItem500Txt_H6.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H6.getValue());
        scrnMsg.xxScrItem500Txt_H7.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H7.getValue());
        scrnMsg.xxScrItem500Txt_H8.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H8.getValue());
        scrnMsg.xxScrItem500Txt_H9.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_H9.getValue());
        scrnMsg.xxScrItem500Txt_HA.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_HA.getValue());
        scrnMsg.xxScrItem500Txt_HB.setNameForMessage(scrnMsg.xxComnScrCondLbTxt_HB.getValue());
        // End 2016/02/19 W.Honda [S21 CSA QC#1130 Mod]
    }
}
