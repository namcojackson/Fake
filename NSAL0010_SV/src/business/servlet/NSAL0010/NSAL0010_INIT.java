/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;
import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;


import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/20   Hitachi         T.Tomita        Update          QC#959
 * 2015/11/24   Hitachi         T.Tomita        Update          QC#948
 * 2015/11/25   Hitachi         T.Tomita        Update          QC#952
 * 2016/02/22   Hitachi         T.Tomita        Update          QC#969
 * 2016/03/24   Hitachi         K.Yamada        Update          QC#5400
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6543
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/09/26   Hitachi         Y.Zhang         Update          QC#12582
 * 2018/05/28   Hitachi         K.Kitachi       Update          QC#26298
 * 2023/07/31   Hitachi         Y.nagasawa      Update          QC#61632
 *</pre>
 */
public class NSAL0010_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_H2, param01);
            }
        }
        scrnMsg.xxDplyTab_01.setValue(TAB_MACH_CONFIG);

        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        boolean isSearch = false;
        if (ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1.getValue())) {
            isSearch = true;
        }
        NSAL0010CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID(), isSearch);
        scrnMsg.setFocusItem(scrnMsg.serNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        // Header
        scrnMsg.serNum_H1.setNameForMessage("Serial#");
        // START 2015/11/25 T.Tomita [QC#952, ADD]
        scrnMsg.svcMachMstrPk_H1.setNameForMessage("IB ID");
        // END 2015/11/25 T.Tomita [QC#952, ADD]
        // START 2016/03/24 K.Yamada [QC#5400, MOD]
        scrnMsg.mdseCd_H1.setNameForMessage("Item Number");
     // START 2016/09/21 N.Arai [QC#11616, MOD]
     // scrnMsg.mdseNm_H1.setNameForMessage("Item Description");
        scrnMsg.mdseDescShortTxt_H1.setNameForMessage("Item Description");
     // END 2016/09/21 N.Arai [QC#11616, MOD]
        // END 2016/03/24 K.Yamada [QC#5400, MOD]
        scrnMsg.mktMdlNm_H1.setNameForMessage("Marketing Model");
        scrnMsg.t_MdlNm_H1.setNameForMessage("Service Model");
        scrnMsg.svcMachQty_H1.setNameForMessage("Quantity");
        scrnMsg.svcMachMstrStsCd_H3.setNameForMessage("Machine Status");
        scrnMsg.istlDt_H1.setNameForMessage("Install Date");
        scrnMsg.startDt_H1.setNameForMessage("Start Date");
        scrnMsg.svcMachRmvDt_H1.setNameForMessage("End Date");
        scrnMsg.svcMachMstrStsCd_H3.setNameForMessage("IB Status");
        scrnMsg.svcMachUsgStsCd_H3.setNameForMessage("IB Usage");
        scrnMsg.stkStsCd_H3.setNameForMessage("Stock Status");
        scrnMsg.prntSerNum_H1.setNameForMessage("Parent Serial#");
        scrnMsg.custMachCtrlNum_H1.setNameForMessage("External Ref#");
        // START 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        scrnMsg.swLicId_H1.setNameForMessage("Software ID");
        // END 2023/07/31 Y.Nagasawa [QC#61632, ADD]
        scrnMsg.svcMachTrxTpCd_H3.setNameForMessage("Transaction Type");
        scrnMsg.xxChkBox_H1.setNameForMessage("Serial# Change");
        scrnMsg.serNum_H2.setNameForMessage("New Serial#");
        // START 2018/05/28 K.Kitachi [QC#26298, ADD]
        scrnMsg.wtyAutoCratFlg_H1.setNameForMessage("Create Warranty");
        // END 2018/05/28 K.Kitachi [QC#26298, ADD]

        // START 2016/05/16 T.Tomita [QC#4578, MOD]
        scrnMsg.xxLocRoleTpCdListTxt_M1.setNameForMessage("Relationship");
        scrnMsg.ownrAcctNum_M1.setNameForMessage("Account#");
        scrnMsg.dsAcctNm_M1.setNameForMessage("Account Name");
//        scrnMsg.xxComnScrColValTxt_M1.setNameForMessage("Address");
//        scrnMsg.ownrLocNum_M1.setNameForMessage("Loc#");

        scrnMsg.xxLocRoleTpCdListTxt_M2.setNameForMessage("Relationship");
        scrnMsg.billToAcctNum_M2.setNameForMessage("Account#");
        scrnMsg.dsAcctNm_M2.setNameForMessage("Account Name");
        scrnMsg.xxComnScrColValTxt_M2.setNameForMessage("Address");
        scrnMsg.indBillToLocNum_M2.setNameForMessage("Loc#");

        scrnMsg.xxLocRoleTpCdListTxt_M3.setNameForMessage("Relationship");
        scrnMsg.curLocAcctNum_M3.setNameForMessage("Account#");
        scrnMsg.dsAcctNm_M3.setNameForMessage("Account Name");
        scrnMsg.xxComnScrColValTxt_M3.setNameForMessage("Address");
        scrnMsg.indCurLocNum_M3.setNameForMessage("Loc#");
        // END 2016/05/16 T.Tomita [QC#4578, MOD]

        //Header(Image Ware Remote Meter Details)
        // START 2016/06/10 [QC#9591, MOD]
        scrnMsg.xxYesNoNm_H1.setNameForMessage("Enabled");
        scrnMsg.iwrCondDescTxt_H1.setNameForMessage("Status");
        scrnMsg.lastUpdDt_H1.setNameForMessage("Last Communication Date");
        // END   2016/06/10 [QC#9591, MOD]
        scrnMsg.iwrRgtnDt_H1.setNameForMessage("Registration Date");
        scrnMsg.iwrDeinsDt_H1.setNameForMessage("Deregistration Date");

        //Mach Config Tab
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL0010_ABMsg detailMsg = scrnMsg.A.no(i);
            detailMsg.svcConfigMstrPk_A.setNameForMessage("Config ID");
            // START 2016/03/24 K.Yamada [QC#5400, MOD]
            detailMsg.mdseCd_A.setNameForMessage("Item Number");
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         // detailMsg.mdseNm_A.setNameForMessage("Mdse Name");
            // START 2016/09/26 Y.Zhang [QC#12582, MOD]
            detailMsg.mdseDescShortTxt_A.setNameForMessage("Item Name");
            // END 2016/09/26 Y.Zhang [QC#12582, MOD]
         // END 2016/09/21 N.Arai [QC#11616, MOD]
            // END 2016/03/24 K.Yamada [QC#5400, MOD]
            detailMsg.mktMdlNm_A.setNameForMessage("Marketing Model");
            detailMsg.serNum_A.setNameForMessage("Serial#");
            detailMsg.istlDt_A.setNameForMessage("Install Date");
            detailMsg.svcMachMstrStsDescTxt_A.setNameForMessage("IB Status");
            detailMsg.svcMachMstrPk_A1.setNameForMessage("IB ID");
            detailMsg.svcMachMstrPk_A2.setNameForMessage("Parent ID");
            detailMsg.rddDt_A.setNameForMessage("Sche Install Date");
            detailMsg.svcMachRmvDt_A.setNameForMessage("Remove Date");
        }

        //Contacts Tab
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NSAL0010_CBMsg ctacMsg = scrnMsg.C.no(i);
            ctacMsg.ctacPsnLastNm_C.setNameForMessage("Last Name");
            ctacMsg.ctacPsnFirstNm_C.setNameForMessage("First Name");
            ctacMsg.dsCtacPntTpCd_CS.setNameForMessage("Contact Point");
            ctacMsg.dsCtacPntValTxt_C.setNameForMessage("Contact Value");
            ctacMsg.dsCtacPsnExtnNum_C.setNameForMessage("Ext");
            ctacMsg.svcCtacTpCd_CS.setNameForMessage("IB Contact Type");
            ctacMsg.ctacPsnPk_C.setNameForMessage("Contact ID");
            ctacMsg.xxChkBox_C.setNameForMessage("Primary");
            ctacMsg.effFromDt_C.setNameForMessage("Start Date");
            ctacMsg.effThruDt_C.setNameForMessage("End Date");
        }

        // add start 2016/04/20 CSA Defect#6543
        // Additional Tab
        scrnMsg.svcLicCnt_D.setNameForMessage("# of Licenses");
        // add end 2016/04/20 CSA Defect#6543
    }
}
