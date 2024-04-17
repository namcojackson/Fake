/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3130.NLBL3130CMsg;
import business.db.SHPG_ORD_DELY_INSTNTMsg;
import business.servlet.NLBL3130.common.NLBL3130CommonLogic;
import business.servlet.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 01/15/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 03/14/2019   Fujitsu         T.Ogura         Update          QC#30707
 * 03/24/2020   Fujitsu         T.Ogura         Update          QC#56288
 *</pre>
 */
public class NLBL3130Scrn00_CMN_Submit extends S21CommonHandler implements NLBL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;

        NLBL3130CommonLogic.checkDate(scrnMsg);
        NLBL3130CommonLogic.chkBoxBothOn(scrnMsg);

        // Site Information
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_OF);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_OT);
        scrnMsg.addCheckItem(scrnMsg.secClncReqTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.secClncReqTxt_N);
        scrnMsg.addCheckItem(scrnMsg.insCertReqTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.insCertReqTxt_N);
        scrnMsg.addCheckItem(scrnMsg.flCovTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.trctrAndTrailAccsTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.trctrAndTrailAccsTxt_N);
        scrnMsg.addCheckItem(scrnMsg.loadDockAvalTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.loadDockAvalTxt_N);
        scrnMsg.addCheckItem(scrnMsg.rampAvalTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.rampAvalTxt_N);
        scrnMsg.addCheckItem(scrnMsg.stepAvalTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.stepAvalTxt_N);
        scrnMsg.addCheckItem(scrnMsg.insdStepNum_H1);
        scrnMsg.addCheckItem(scrnMsg.otsdStepNum_H1);
        scrnMsg.addCheckItem(scrnMsg.frontDoorAvalTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.frontDoorAvalTxt_N);
        scrnMsg.addCheckItem(scrnMsg.backDoorAvalTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.backDoorAvalTxt_N);
        scrnMsg.addCheckItem(scrnMsg.pwrOtltConfigTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.pwrOtltConfigTxt_N);
        scrnMsg.addCheckItem(scrnMsg.sgnOnBldgRdsdTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.sgnOnBldgRdsdTxt_N);
        scrnMsg.addCheckItem(scrnMsg.bldgStryNum_H1);
        scrnMsg.addCheckItem(scrnMsg.bldgGurdNtfyTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.bldgGurdNtfyTxt_N);
        scrnMsg.addCheckItem(scrnMsg.indlParkTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.indlParkTxt_N);
        scrnMsg.addCheckItem(scrnMsg.bizParkTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.bizParkTxt_N);
        scrnMsg.addCheckItem(scrnMsg.proBldgTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.proBldgTxt_N);
        scrnMsg.addCheckItem(scrnMsg.shpngCtrTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.shpngCtrTxt_N);
        scrnMsg.addCheckItem(scrnMsg.pvtResTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.pvtResTxt_N);

        // Elevator Information & Dimensions
        scrnMsg.addCheckItem(scrnMsg.elevAvalTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.elevAvalTxt_N);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_EF);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ET);
        scrnMsg.addCheckItem(scrnMsg.elevApptReqTxt_Y);
        scrnMsg.addCheckItem(scrnMsg.elevApptReqTxt_N);
        scrnMsg.addCheckItem(scrnMsg.elevCtacPsnNm_C1);
        scrnMsg.addCheckItem(scrnMsg.elevCtacTelNum_C1);
        scrnMsg.addCheckItem(scrnMsg.bldgEntDoorHgt_A1);
        scrnMsg.addCheckItem(scrnMsg.bldgEntDoorWdt_A1);
        scrnMsg.addCheckItem(scrnMsg.crdrWdt_A1);
        scrnMsg.addCheckItem(scrnMsg.doorWdt_A1);
        scrnMsg.addCheckItem(scrnMsg.elevWdt_C1);
        scrnMsg.addCheckItem(scrnMsg.elevDepthNum_C1);
        scrnMsg.addCheckItem(scrnMsg.elevCapWt_C1);
        scrnMsg.addCheckItem(scrnMsg.elevDoorHgt_C1);
        scrnMsg.addCheckItem(scrnMsg.elevDoorWdt_C1);

        // Instructions & Additional Comments
        scrnMsg.addCheckItem(scrnMsg.shpgInstnCmntTxt_B1);
        scrnMsg.addCheckItem(scrnMsg.istlInstnCmntTxt_B1);
        // START 03/24/2020 T.Ogura [QC#56288,ADD]
        int delyInstnCmntTxtDigit = new SHPG_ORD_DELY_INSTNTMsg().getAttr("delyInstnCmntTxt").getDigit();
        String delyInstnCmntTxt = scrnMsg.delyInstnCmntTxt_B1.getValue();
        if (ZYPCommonFunc.hasValue(delyInstnCmntTxt) && delyInstnCmntTxt.length() > delyInstnCmntTxtDigit) {
            scrnMsg.delyInstnCmntTxt_B1.setErrorInfo(1, ZZM9001E, new String[] {"Delivery Instructions"});
        }
        // END   03/24/2020 T.Ogura [QC#56288,ADD]
        scrnMsg.addCheckItem(scrnMsg.delyInstnCmntTxt_B1);
        scrnMsg.addCheckItem(scrnMsg.techInstnCmntTxt_B1);
        scrnMsg.addCheckItem(scrnMsg.delyInstnAddlCmntTxt_D1);

        // QC#18460_Sol#087 Add column
        // Header
        scrnMsg.addCheckItem(scrnMsg.tmZoneCd_H1);
        // Site Information
        scrnMsg.addCheckItem(scrnMsg.xxTmFrameTxt_OF);
        scrnMsg.addCheckItem(scrnMsg.xxTmFrameTxt_OT);
        scrnMsg.addCheckItem(scrnMsg.cmpyInfoFlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.loadDockHgt_H1);
        scrnMsg.addCheckItem(scrnMsg.delyTrnspOptCd_H1);
        scrnMsg.addCheckItem(scrnMsg.stairCrawReqFlg_Y);
        scrnMsg.addCheckItem(scrnMsg.stairCrawReqFlg_N);
        scrnMsg.addCheckItem(scrnMsg.stairAndLdgWdt_H1);
        // Elevator Information & Dimensions
        scrnMsg.addCheckItem(scrnMsg.xxTmFrameTxt_EF);
        scrnMsg.addCheckItem(scrnMsg.xxTmFrameTxt_ET);
        // Validation
        scrnMsg.addCheckItem(scrnMsg.custInfoBoFlg_Y);
        scrnMsg.addCheckItem(scrnMsg.custInfoBoFlg_N);
        scrnMsg.addCheckItem(scrnMsg.pickUpXtrTonerFlg_Y);
        scrnMsg.addCheckItem(scrnMsg.pickUpXtrTonerFlg_N);
        scrnMsg.addCheckItem(scrnMsg.pickUpAtSameTmFlg_Y);
        scrnMsg.addCheckItem(scrnMsg.pickUpAtSameTmFlg_N);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        NLBL3130CMsg bizMsg = new NLBL3130CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) bMsg;
        NLBL3130CMsg bizMsg  = (NLBL3130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2019/03/14 T.Ogura [QC#30707,ADD]
        scrnMsg.addCheckItem(scrnMsg.shpgInstnCmntTxt_B1);
        scrnMsg.addCheckItem(scrnMsg.istlInstnCmntTxt_B1);
        scrnMsg.addCheckItem(scrnMsg.delyInstnCmntTxt_B1);
        scrnMsg.addCheckItem(scrnMsg.techInstnCmntTxt_B1);
        scrnMsg.putErrorScreen();
        // END   2019/03/14 T.Ogura [QC#30707,ADD]

        NLBL3130CommonLogic.cntrlScrnItemDispSearch(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
    }
}
