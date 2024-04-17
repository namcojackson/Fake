/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2240;

import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.convTimeforScreen;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.initCommonButton;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.initControlFields;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setAppFracDigit;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setDelyIstlDispFlg;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setNameForMessageDeryDisp;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setScrnTm;
import static business.servlet.NWAL2240.common.NWAL2240CommonLogic.setTabProtect;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.BUSINESS_ID;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.NWAM0662E;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_0;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_1;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_2;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.POP_PAR_3;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.TAB_INSTALL;
import static business.servlet.NWAL2240.constant.NWAL2240Constant.XX_EDT_MODE_CD_NO_EDIT;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2240.NWAL2240CMsg;
import business.servlet.NWAL2240.common.NWAL2240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#5503
 * 2017/08/30   Fujitsu         S.Takami        Update          S21_NA#20842
 *</pre>
 */
public class NWAL2240_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.ordSrcRefNum_H0, (EZDBStringItem) params[POP_PAR_0]);
            setValue(scrnMsg.dsOrdPosnNum_H0, (EZDBStringItem) params[POP_PAR_1]);
            setValue(scrnMsg.configCatgCd_H0, (EZDBStringItem) params[POP_PAR_2]);
            // START 2016/04/26 K.Kojima [QC#5503,ADD]
            setValue(scrnMsg.xxEdtModeFlg, (EZDBStringItem) params[POP_PAR_3]);
            // END 2016/04/26 K.Kojima [QC#5503,ADD]

            if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_H0)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd_H0)) {
                    scrnMsg.setMessageInfo(NWAM0662E);
                }
            }
        }

        NWAL2240CMsg bizMsg = new NWAL2240CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        initControlFields(this, scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;
        NWAL2240CMsg bizMsg = (NWAL2240CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        setDelyIstlDispFlg(scrnMsg);

        scrnMsg.dsOrdCatgDescTxt_H0.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_H0.setInputProtected(true);
        scrnMsg.tmZoneCd_H0.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_H0.setInputProtected(true);
        scrnMsg.shipToCustLocCd_H0.setInputProtected(true);
        // 2017/08/30 S21_NA#20842 Mod Start
//        scrnMsg.shipToLocNm_H0.setInputProtected(true);
        scrnMsg.shipToCustAcctNm_H0.setInputProtected(true);
        // 2017/08/30 S21_NA#20842 Mod End
        scrnMsg.firstLineAddr_H0.setInputProtected(true);
        scrnMsg.scdLineAddr_H0.setInputProtected(true);

        scrnMsg.shipToLocNm_SS.setInputProtected(true);
        scrnMsg.shipToCustLocCd_SS.setInputProtected(true);
        scrnMsg.shipToFirstLineAddr_SS.setInputProtected(true);
        scrnMsg.shipToCtyAddr_SS.setInputProtected(true);
        scrnMsg.shipToStCd_SS.setInputProtected(true);
        scrnMsg.shipToPostCd_SS.setInputProtected(true);

        scrnMsg.xxDplyTab.setValue(TAB_INSTALL);

        setNameForMessageDeryDisp(scrnMsg);
        initCommonButton(this);
        setTabProtect(this, scrnMsg);
        setAppFracDigit(scrnMsg);
        convTimeforScreen(scrnMsg, bizMsg);
        setScrnTm(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ordSrcRefNum_H0);

        // START 2016/04/26 K.Kojima [QC#5503,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.xxEdtModeFlg) && XX_EDT_MODE_CD_NO_EDIT.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            NWAL2240CommonLogic.searchOrderDisabled(this, scrnMsg);
        }
        // END 2016/04/26 K.Kojima [QC#5503,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL2240BMsg scrnMsg = (NWAL2240BMsg) bMsg;

        scrnMsg.ordSrcRefNum_H0.setNameForMessage("Order Ref Num");
        scrnMsg.dsOrdPosnNum_H0.setNameForMessage("Configuration Num");
        scrnMsg.configCatgCd_H0.setNameForMessage("Category Type");

        scrnMsg.opsFromHourMn_DI.setNameForMessage("Hours of Operation(From)");
        scrnMsg.opsToHourMn_DI.setNameForMessage("Hours of Operation(To)");
        scrnMsg.loadDockAvalFlg_DI.setNameForMessage("Loading Dock");
        scrnMsg.stairCrawReqFlg_DI.setNameForMessage("Staors");
        scrnMsg.stairCrawNum_DI.setNameForMessage("Number of Stairs");
        scrnMsg.elevAvalFlg_DI.setNameForMessage("Elevator");

        scrnMsg.shipToLocNm_SS.setNameForMessage("Company Name");
        scrnMsg.shipToCustLocCd_SS.setNameForMessage("Customer Number");
        scrnMsg.shipToFirstLineAddr_SS.setNameForMessage("Street");
        scrnMsg.shipToCtyAddr_SS.setNameForMessage("City");
        scrnMsg.cmpyInfoAptBldgNm_SS.setNameForMessage("Apt. or Building");
        scrnMsg.shipToStCd_SS.setNameForMessage("State");
        scrnMsg.cmpyInfoFlNm_SS.setNameForMessage("Floor");
        scrnMsg.shipToPostCd_SS.setNameForMessage("Postal");
        scrnMsg.cmpyInfoDeptNm_SS.setNameForMessage("Department");
        scrnMsg.elevProtReqFlg_SS.setNameForMessage("Floor Protection Req?");
        scrnMsg.siteSrvyAddlCmntTxt_SS.setNameForMessage("Additional Comments");
        scrnMsg.otsdStepNum_SS.setNameForMessage("No of Steps Outside");
        scrnMsg.insdStepNum_SS.setNameForMessage("No of Steps Inside");
        scrnMsg.stairCrawReqFlg_SS.setNameForMessage("Step Crawler required?");
        scrnMsg.flgtStairNum_SS.setNameForMessage("No of flights");
        scrnMsg.stairAndLdgWdt_SS.setNameForMessage("width of stairs and landings(in,)");
        scrnMsg.loadDockAvalFlg_SS.setNameForMessage("Loading Dock Available");
        scrnMsg.loadDockHgt_SS.setNameForMessage("Dock Height(in,)");
        scrnMsg.loadDockAvalFromHourMn_SS.setNameForMessage("Delivery Hours From");
        scrnMsg.loadDockAvalFromHourMn_AP.setNameForMessage("Delivery Hours From");
        scrnMsg.loadDockAvalToHourMn_SS.setNameForMessage("Delivery Hours To");
        scrnMsg.loadDockAvalToHourMn_AP.setNameForMessage("Delivery Hours To");
        scrnMsg.trctrAndTrailAccsFlg_SS.setNameForMessage("Tractor Trailer Accessible");
        scrnMsg.carrDelyTmHourMn_SS.setNameForMessage("Timestop");
        scrnMsg.carrDelyTmHourMn_AP.setNameForMessage("Timestop");
        scrnMsg.delyTrnspOptCd_SS.setNameForMessage("Transport Option");
        scrnMsg.elevAvalFlg_SS.setNameForMessage("Elevator Available");
        scrnMsg.elevAvalFromHourMn_SS.setNameForMessage("Elevator hours From");
        scrnMsg.elevAvalFromHourMn_AP.setNameForMessage("Elevator hours From");
        scrnMsg.elevAvalToHourMn_SS.setNameForMessage("Elevator hours To");
        scrnMsg.elevAvalToHourMn_AP.setNameForMessage("Elevator hours To");
        scrnMsg.elevApptReqFlg_SS.setNameForMessage("Elevator Appointment Needed?");
        scrnMsg.elevCtacPsnNm_SS.setNameForMessage("Elevator Contact");
        scrnMsg.elevCtacTelNum_SS.setNameForMessage("Elevator Phone");
        scrnMsg.bldgEntDoorHgt_SS.setNameForMessage("Building Entrance Height(in,)");
        scrnMsg.bldgEntDoorWdt_SS.setNameForMessage("Building Entrance Width(in.)");
        scrnMsg.crdrWdt_SS.setNameForMessage("Building Entrance Corridor Width(in.)");
        scrnMsg.doorWdt_SS.setNameForMessage("Building Entrance Door Width(in.)");
        scrnMsg.elevWdt_SS.setNameForMessage("Elevator Width(in.)");
        scrnMsg.elevDepthNum_SS.setNameForMessage("Elevator Depth(in.)");
        scrnMsg.elevCapWt_SS.setNameForMessage("Elevator Capacity(Lbs.)");
        scrnMsg.elevDoorHgt_SS.setNameForMessage("Door Opening Height(in.)");
        scrnMsg.elevDoorWdt_SS.setNameForMessage("Door Opening Width (in.)");

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL2240_CBMsg cBMsg = scrnMsg.C.no(i);
            cBMsg.ctacPsnTpCd_C0.setNameForMessage("Role");
            cBMsg.ctacPsnFirstNm_C0.setNameForMessage("First Name");
            cBMsg.ctacPsnLastNm_C0.setNameForMessage("Last Name");
            cBMsg.ctacPsnTelNum_C0.setNameForMessage("Phone");
            cBMsg.ctacPsnExtnNum_C0.setNameForMessage("EXT");
            cBMsg.ctacPsnEmlAddr_C0.setNameForMessage("Email");
            cBMsg.ctacPsnFaxNum_C0.setNameForMessage("Fax");
        }
    }
}
