/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0662E;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.ORD_POSN_NUM_VIEW_SELECT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_0;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_1;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_2;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_3;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_LENGTH_4;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.TAB_INSTALL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;
import business.servlet.NWAL1510.common.NWAL1510CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/09   Fujitsu         S.Ohki          Update          S21_NA#1622
 * 2016/05/13   Fujitsu         M.Suzuki        Update          S21_NA#7088
 * 2016/09/20   Fujitsu         R.Nakamura      Update          QC#13738
 * 2016/10/12   Fujitsu         W.Honda         Update          S21_NA#13021
 * 2016/10/21   Fujitsu         S.Takami        Update          S21_NA#15472
 * 2017/02/27   Fujitsu         T.Yoshida       Update          S21_NA#16035
 * 2018/01/05   Fujitsu         K.Ishizuka      Update          S21_NA#18460(Sol#087)
 * 2018/07/19   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1510_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.cpoOrdNum_H0, (EZDBStringItem) params[POP_PAR_0]);
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//            setValue(scrnMsg.configCatgCd_H0, (EZDBStringItem) params[POP_PAR_2]);
            if (params[POP_PAR_2] instanceof String) {
                setValue(scrnMsg.configCatgCd_H0, (String)params[POP_PAR_2]);
            } else {
                setValue(scrnMsg.configCatgCd_H0, (EZDBStringItem)params[POP_PAR_2]);
            }
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]

            if (params[POP_PAR_1] instanceof EZDMsgArray) {
                EZDMsg.copy((EZDMsgArray) params[POP_PAR_1], null, scrnMsg.H, null);
            } else {
                List<Object> paramList = (List<Object>) params[POP_PAR_1];

                if (paramList != null && paramList.size() > 0) {
                    int index = 0;
                    // 2016/10/21 S21_NA#15472 Mod Start
//                    for (Object param : paramList) {
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index++).dsOrdPosnNum_H1, (String) param);
//                    }
                    for (; index < paramList.size() && index < scrnMsg.H.length(); index++) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.H.no(index).dsOrdPosnNum_H1, (String) paramList.get(index));
                    }
                    // 2016/10/21 S21_NA#15472 Mod End
                    scrnMsg.H.setValidCount(index);

                    if (index == 1) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_H0, scrnMsg.H.no(0).dsOrdPosnNum_H1);
                    } else {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.configTpNm_LK, ORD_POSN_NUM_VIEW_SELECT);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_H0)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd_H0)) {
                    scrnMsg.setMessageInfo(NWAM0662E);
                }
            }

            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            if (params.length >= POP_PAR_LENGTH_4) {
                String initTab = (String)params[POP_PAR_3];
                if (!S21StringUtil.isEmpty(initTab)) {
                    scrnMsg.xxDplyTab_P.setValue(initTab);
                }
            }
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }
        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        NWAL1510CommonLogic.initControlFields(this, scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NWAL1510CommonLogic.setDelyIstlDispFlg(scrnMsg);

        scrnMsg.cpoOrdNum_H0.setInputProtected(true);
        scrnMsg.dsOrdPosnNum_H0.setInputProtected(true);
        scrnMsg.configCatgCd_H0.setInputProtected(true);
        scrnMsg.tmZoneCd_H0.setInputProtected(true); //2018/01/05 S21_NA#18460(Sol#087) ADD

        scrnMsg.dsOrdCatgDescTxt_H0.setInputProtected(true);
        scrnMsg.dsOrdTpDescTxt_H0.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_H0.setInputProtected(true);
        scrnMsg.shipToCustLocCd_H0.setInputProtected(true);
        // 2016/10/12 S21_NA#13021 Mod Start
//        scrnMsg.shipToLocNm_H0.setInputProtected(true);
        scrnMsg.dsAcctNm_H0.setInputProtected(true);
        // 2016/10/12 S21_NA#13021 Mod End
        scrnMsg.firstLineAddr_H0.setInputProtected(true);
        scrnMsg.scdLineAddr_H0.setInputProtected(true);

        // 2016/10/12 S21_NA#13021 Mod Start
//        scrnMsg.shipToLocNm_SS.setInputProtected(true);
        scrnMsg.dsAcctNm_SS.setInputProtected(true);
        // 2016/10/12 S21_NA#13021 Mod End
        scrnMsg.shipToCustLocCd_SS.setInputProtected(true);
        scrnMsg.shipToFirstLineAddr_SS.setInputProtected(true);
        scrnMsg.shipToCtyAddr_SS.setInputProtected(true);
        scrnMsg.shipToStCd_SS.setInputProtected(true);
        scrnMsg.shipToPostCd_SS.setInputProtected(true);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//        scrnMsg.xxDplyTab.setValue(TAB_INSTALL);
        String tabParam = scrnMsg.xxDplyTab_P.getValue();
        if (S21StringUtil.isEmpty(tabParam)) {
            scrnMsg.xxDplyTab.setValue(TAB_INSTALL);
        } else {
            scrnMsg.xxDplyTab.setValue(tabParam);
        }
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
        // 2018/07/19 S21_NA#26188 Add Start
        scrnMsg.xxEdtModeFlg_SS.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxEdtModeFlg_CT.setValue(ZYPConstant.FLG_ON_Y);
        // 2018/07/19 S21_NA#26188 Add End

        NWAL1510CommonLogic.setNameForMessageDeryDisp(scrnMsg);
        NWAL1510CommonLogic.initCommonButton(this);
        NWAL1510CommonLogic.setTabProtect(this, scrnMsg);
        NWAL1510CommonLogic.setAppFracDigit(scrnMsg);
        NWAL1510CommonLogic.convTimeforScreen(scrnMsg, bizMsg);
        NWAL1510CommonLogic.setScrnTm(scrnMsg);
        NWAL1510CommonLogic.setProtectByAuthority(this, scrnMsg); // S21_NA#16035 Add

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        scrnMsg.cpoOrdNum_H0.setNameForMessage("Order Number");
        scrnMsg.dsOrdPosnNum_H0.setNameForMessage("Configuration Number");
        scrnMsg.configCatgCd_H0.setNameForMessage("Category Type");

        scrnMsg.opsFromHourMn_DI.setNameForMessage("Hours of Operation(From)");
        scrnMsg.opsToHourMn_DI.setNameForMessage("Hours of Operation(To)");
        scrnMsg.loadDockAvalFlg_DI.setNameForMessage("Loading Dock");
        scrnMsg.stairCrawReqFlg_DI.setNameForMessage("Stairs");
        scrnMsg.stairCrawNum_DI.setNameForMessage("Number of Stairs");
        scrnMsg.elevAvalFlg_DI.setNameForMessage("Elevator");

        // 2016/10/12 S21_NA#13021 Mod Start
//        scrnMsg.shipToLocNm_SS.setNameForMessage("Company Name");
        scrnMsg.dsAcctNm_SS.setNameForMessage("Company Name");
        // 2016/10/12 S21_NA#13021 Mod End
        scrnMsg.shipToCustLocCd_SS.setNameForMessage("Customer Number");
        scrnMsg.shipToFirstLineAddr_SS.setNameForMessage("Street");
        scrnMsg.shipToCtyAddr_SS.setNameForMessage("City");
        scrnMsg.cmpyInfoAptBldgNm_SS.setNameForMessage("Apt. or Building");
        scrnMsg.shipToStCd_SS.setNameForMessage("State");
        scrnMsg.cmpyInfoFlNm_SS.setNameForMessage("Floor");
        scrnMsg.shipToPostCd_SS.setNameForMessage("Postal");
        scrnMsg.cmpyInfoDeptNm_SS.setNameForMessage("Department");
        scrnMsg.elevProtReqFlg_SS.setNameForMessage("Floor Protection Req?");
        // Mod Start 2016/09/20 QC#13738
//        scrnMsg.siteSrvyAddlCmntTxt_SS.setNameForMessage("Additional Comments");
        scrnMsg.xxFldValTxt_SS.setNameForMessage("Additional Comments");
        // Mod End 2016/09/20 QC#13738
        scrnMsg.otsdStepNum_SS.setNameForMessage("No of Steps Outside");
        scrnMsg.insdStepNum_SS.setNameForMessage("No of Steps Inside");
        scrnMsg.stairCrawReqFlg_SS.setNameForMessage("Step Crawler required?");
        scrnMsg.flgtStairNum_SS.setNameForMessage("No of flights");
        scrnMsg.stairAndLdgWdt_SS.setNameForMessage("width of stairs and landings(in,)");
        scrnMsg.loadDockAvalFlg_SS.setNameForMessage("Loading Dock Available");
        scrnMsg.loadDockHgt_SS.setNameForMessage("Dock Height(in,)");
        ///2016/05/13 S21_NA#7088 Add Start ------------
        scrnMsg.xxSvcFromHourMnTxt_LD.setNameForMessage("Delivery Hours From");
        scrnMsg.xxSvcToHourMnTxt_LD.setNameForMessage("Delivery Hours To");
        scrnMsg.xxSvcFromHourMnTxt_EA.setNameForMessage("Elevator hours From");
        scrnMsg.xxSvcToHourMnTxt_EA.setNameForMessage("Elevator hours To");
        ///2016/05/13 S21_NA#7088 Add End --------------
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
            NWAL1510_CBMsg cBMsg = scrnMsg.C.no(i);
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
