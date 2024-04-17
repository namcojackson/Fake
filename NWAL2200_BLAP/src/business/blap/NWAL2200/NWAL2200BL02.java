/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2200;

import static business.blap.NWAL2200.constant.NWAL2200Constant.COMMA;
import static business.blap.NWAL2200.constant.NWAL2200Constant.EVENT_NM_NMAL2340_LINE;
import static business.blap.NWAL2200.constant.NWAL2200Constant.EVENT_NM_NMAL2340_RMA;
import static business.blap.NWAL2200.constant.NWAL2200Constant.HDR_ID_ERRORS;
import static business.blap.NWAL2200.constant.NWAL2200Constant.HDR_ID_LINE;
import static business.blap.NWAL2200.constant.NWAL2200Constant.HDR_ID_RMA;
import static business.blap.NWAL2200.constant.NWAL2200Constant.MODE_REFERENCE;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0021E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0181E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0235E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0300E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0667E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0683E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0757W;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NWAM0981W;
import static business.blap.NWAL2200.constant.NWAL2200Constant.NZZM0000E;
import static business.blap.NWAL2200.constant.NWAL2200Constant.SPACE;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_HEADER;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL2200.constant.NWAL2200Constant.TAB_RMA;
import static business.blap.NWAL2200.constant.NWAL2200Constant.XX_MODE_CD_EMSD;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL2200.common.NWAL2200CommonLogic;
import business.blap.NWAL2200.constant.NWAL2200Constant;
import business.db.CCYTMsg;
import business.db.CPO_SRC_TPTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PRC_CATGTMsg;
import business.db.RTL_SWHTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.TOCTMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC611001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomer;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DefaultCustomerBean;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001NumberingUtil;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001SomWebService;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2200BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/04/27   Fujitsu         M.Hara          Update          QC#6695
 * 2016/08/31   CITS            S.Tanikawa      Update          Unit Test#202
 * 2016/09/14   CITS            S.Tanikawa      Update          QC#13769
 * 2016/09/29   Fujitsu         S.Ohki          Update          QC#8659
 * 2016/09/30   SRAA            K.Aratani       Update          QC#14929
 * 10/05/2016   Fujitsu         T.Ishii         Update          S21_NA#15004
 * 11/28/2016   Fujitsu         T.Ishii         Update          S21_NA#15796
 * 01/17/2017   Fujitsu         H.Nagashima     Update          QC#17124
 * 01/26/2017   Fujitsu         M.Ohno          Update          QC#17119
 * 03/09/2017   Fujitsu         S.Ohki          Update          QC#16790
 * 03/14/2017   Fujitsu         M.Ohno          Update          QC#16855
 * 03/21/2017   Fujitsu         S.Ohki          Update          QC#18104
 * 06/27/2017   Fujitsu         H.Sugawara      Update          QC#18363
 * 07/13/2017   Fujitsu         K.Ishizuka      Update          QC#18806(L3#372)
 * 07/18/2017   Fujitsu         R.Nakamura      Update          QC#19937
 * 08/23/2017   Fujitsu         S.Iidaka        Update          S21_NA#20097
 * 08/30/2017   Fujitsu         S.Iidaka        Update          S21_NA#20792
 * 09/13/2017   Fujitsu         R.Nakamura      Update          S21_NA#21065
 * 09/15/2017   Fujitsu         R.Nakamura      Update          S21_NA#21118
 * 11/28/2017   Fujitsu         M.Ohno          Update          S21_NA#22782
 * 12/14/2017   Fujitsu         K.Ishizuka      Update          QC#19804(L3#349)
 * 01/23/2018   Fujitsu         T.Aoi           Update          S21_NA#18798(L3#173)
 * 01/25/2018   Fujitsu         K.Ishizuka      Update          S21_NA#23334
 * 01/29/2018   Fujitsu         K.Ishizuka      Update          S21_NA#23139
 * 02/06/2018   Fujitsu         K.Ishizuka      Update          S21_NA#23926
 * 03/12/2018   Fujitsu         K.Ishizuka      Update          S21_NA#24090
 * 03/15/2018   Fujitsu         S.Ohki          Update          S21_NA#20153 
 * 03/20/2018   Fujitsu         A.Kosai         Update          S21_NA#24840
 * 04/13/2018   Fujitsu         R.Nakamura      Update          S21_NA#24076
 * 06/27/2018   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 07/10/2018   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 07/24/2018   Fujitsu         Mz.Takahashi    Update          S21_NA#14307
 * 2018/08/10   Fujitsu         M.Yamada        Update          QC#26909
 * 08/15/2018   Fujitsu         K.Ishizuka      Update          QC#27771
 * 08/31/2018   Fujitsu         K.Ishizuka      Update          S21_NA#26022
 * 09/01/2018   Fujitsu         Hd.Sugawara     Update          QC#27489
 * 11/19/2018   Fujitsu         Hd.Sugawara     Update          QC#28683
 * 12/13/2018   Fujitsu         T.Noguchi       Update          S21_NA#29315
 * 01/30/2019   Fujitsu         K.Kato          Update          S21_NA#30036
 * 02/01/2019   Fujitsu         M.Ishii         Update          S21_NA#30097
 * 02/08/2019   Fujitsu         M.Ishii         Update          S21_NA#30327
 * 02/08/2019   Fujitsu         K.Kato          Update          S21_NA#30327
 * 02/15/2019   Fujitsu         K.Kato          Update          QC#30308
 * 03/15/2019   Fujitsu         Hd.Sugawara     Update          QC#30730
 * 03/26/2019   Fujitsu         K.Ishizuka      Update          S21_NA#30924
 * 03/28/2019   Fujitsu         K.Ishizuka      Update          S21_NA#30765
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 *</pre>
 */
public class NWAL2200BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
            NWAL2200SMsg glblMsg = (NWAL2200SMsg) sMsg;

            if ("NWAL2200_INIT".equals(screenAplID)) {
                doProcess_NWAL2200_INIT(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_ColClear".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_ColClear(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_ColSave".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_ColSave(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Reject".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Reject(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_CMN_Approve".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_CMN_Approve(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Line_All_Collapsed".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_Line_All_Collapsed(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Line_All_Expand".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_Line_All_Expand(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Line_Collapsed".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_Line_Collapsed(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Line_Expand".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_Line_Expand(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_MoveWin_OrderEntry".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_MoveWin_OrderEntry(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_ChangeWH".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_ChangeWH(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromBillToAccount".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromBillToAccount(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromBillToLocation".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromBillToName".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromBillToName(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromCategory".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromShipToAccount".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromShipToAccount(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromShipToLocation".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromShipToName".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromShipToName(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromSoldToAccount".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromSoldToAccount(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromSoldToLocation".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromSoldToName".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromSoldToName(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnBlur_DeriveFromFreightTerms".equals(screenAplID)) {  // QC#13688 2017/02/24 Add
                doProcess_NWAL2200Scrn00_OnBlur_DeriveFromFreightTerms(bizMsg);

            } else if ("NWAL2200Scrn00_OnChange_ShpgSvcLvlCd".equals(screenAplID)) { // QC#13688 2017/02/24 Add
                doProcess_NWAL2200Scrn00_OnChange_ShpgSvcLvlCd(bizMsg);

            } else if ("NWAL2200Scrn00_OnChange_LineCategory".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnChange_LineCategory(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnChange_OrderReason".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OnChange_OrderReason(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OpenWin_Buyout".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OpenWin_Buyout(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OpenWin_PriceChanges".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OpenWin_PriceChanges(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OpenWin_SpecialInstruction".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OpenWin_SpecialInstruction(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OpenWin_ShipToChange".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OpenWin_ShipToChange(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Order_Search".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_Order_Search(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_TAB_Addl_Header".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_TAB_Addl_Header(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_TAB_Errors".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_TAB_Errors(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_TAB_Header".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_TAB_Header(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_TAB_Line_Config".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_TAB_Line_Config(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_TAB_RMA".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_TAB_RMA(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_Validate".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_Validate(bizMsg, glblMsg);

            } else if ("NWAL2200_NMAL6050".equals(screenAplID)) {         // 2017/01/26 S21_NA#17119 Add
                doProcess_NWAL2200_NMAL6050(bizMsg, glblMsg);

            } else if ("NWAL2200_NMAL6760".equals(screenAplID)) {
                doProcess_NWAL2200_NMAL6760(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL0140".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL0140(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1130(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1500".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1500(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1600".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1600(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1630".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1630(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1640".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1640(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1660".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1660(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL1760".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL1760(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL2240".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL2240(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL2250".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL2250(bizMsg, glblMsg);

            } else if ("NWAL2200_NWAL2340".equals(screenAplID)) {
                doProcess_NWAL2200_NWAL2340(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_OnChange_DclnSvcCdConfig".equals(screenAplID)) { // QC#13769
                doProcess_NWAL2200Scrn00_OnChange_DclnSvcCdConfig(bizMsg);

            } else if ("NWAL2200Scrn00_OnChange_DclnSvcCdHdr".equals(screenAplID)) { // QC#13769
                doProcess_NWAL2200Scrn00_OnChange_DclnSvcCdHdr(bizMsg);

            } else if ("NWAL2200Scrn00_PageJump".equals(screenAplID)) { // QC#13769
                doProcess_NWAL2200Scrn00_PageJump(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_PageNext".equals(screenAplID)) { // QC#13769
                doProcess_NWAL2200Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_PagePrev".equals(screenAplID)) { // QC#13769
                doProcess_NWAL2200Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NWAL2200Scrn00_ConfigJump".equals(screenAplID)) { // QC#13769
                doProcess_NWAL2200Scrn00_ConfigJump(bizMsg, glblMsg);

            // 2018/12/13 S21_NA#29315 Add Start
            } else if ("NWAL2200Scrn00_OpenWin_CarrierServiceLevel".equals(screenAplID)) {
                doProcess_NWAL2200Scrn00_OpenWin_CarrierServiceLevel(bizMsg, glblMsg);
            // 2018/12/13 S21_NA#29315 Add End

            } else {
                return;
            }
            
            // has error
            //if (glblMsg.E.getValidCount() > 0) {
            //    bizMsg.setCommitSMsg(true);
            //}
            bizMsg.setCommitSMsg(true);
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_INIT(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        initialize(bizMsg, glblMsg);
    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Clear(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        doProcess_NWAL2200_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_ColClear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_ColClear(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * CMN_ColSave Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_ColSave(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * CMN_Reject Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Reject(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        searchOrder(bizMsg, glblMsg);
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Reset(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        doProcess_NWAL2200_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Save Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Save(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        searchOrder(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Submit(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        searchOrder(bizMsg, glblMsg);
    }

    /**
     * CMN_Approve Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_CMN_Approve(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        searchOrder(bizMsg, glblMsg);
    }

    /**
     * Line_All_Collapsed Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_Line_All_Collapsed(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // no process
    }

    /**
     * Line_All_Expand Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_Line_All_Expand(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // no process
    }

    /**
     * Line_Collapsed Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_Line_Collapsed(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // 2018/08/31 S21_NA#26022 Add Start
        final int eventLine = bizMsg.xxCellIdx.getValueInt();
        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                NWAL2200_BCMsg lineMsg = bizMsg.B.no(i);
                NWAL2200CommonLogic.copyLineDataToSMsg(lineMsg, glblMsg);
            }
            
            int page = bizMsg.xxPageShowToNum_LC.getValueInt();
            NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, page);
            NWAL2200_ASMsg configLineSMsg = glblMsg.A.no(eventLine);
            configLineSMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_ON_Y);
            NWAL2200_ACMsg configLineCMsg = bizMsg.A.no(eventLine);
            configLineCMsg.xxSmryLineFlg_L.setValue(ZYPConstant.FLG_ON_Y);
        } else if (TAB_RMA.equals(dplyTab)) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                NWAL2200_DCMsg rmaLineMsg = bizMsg.D.no(i);
                NWAL2200CommonLogic.copyLineDataToSMsg(rmaLineMsg, glblMsg);
            }
            
            int page = bizMsg.xxPageShowToNum_RC.getValueInt();
            NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, page);
            NWAL2200_CSMsg rmaConfigLineSMsg = glblMsg.C.no(eventLine);
            rmaConfigLineSMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_ON_Y);
            NWAL2200_CCMsg rmaConfigLineCMsg = bizMsg.C.no(eventLine);
            rmaConfigLineCMsg.xxSmryLineFlg_R.setValue(ZYPConstant.FLG_ON_Y);
        }
        // 2018/08/31 S21_NA#26022 Add End
    }

    /**
     * Line_Expand Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_Line_Expand(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // 2018/08/31 S21_NA#26022 Add Start
        final int eventLine = bizMsg.xxCellIdx.getValueInt();
        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            int page = bizMsg.xxPageShowToNum_LC.getValueInt();
            NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, page);
            NWAL2200_ASMsg configLineMsg = glblMsg.A.no(eventLine);
            configLineMsg.xxSmryLineFlg_L.clear();
            NWAL2200_ACMsg configLineCMsg = bizMsg.A.no(eventLine);
            configLineCMsg.xxSmryLineFlg_L.clear();
        } else if (TAB_RMA.equals(dplyTab)) {
            int page = bizMsg.xxPageShowToNum_RC.getValueInt();
            NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, page);
            NWAL2200_CSMsg rmaConfigLineMsg = glblMsg.C.no(eventLine);
            rmaConfigLineMsg.xxSmryLineFlg_R.clear();
            NWAL2200_CCMsg rmaConfigLineCMsg = bizMsg.C.no(eventLine);
            rmaConfigLineCMsg.xxSmryLineFlg_R.clear();
        }
        // 2018/08/31 S21_NA#26022 Add End
    }

    /**
     * MoveWin_OrderEntry Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_MoveWin_OrderEntry(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // no process
    }

    /**
     * OnBlur_ChangeWH Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_ChangeWH(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        int slctLineIndex = bizMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL2200_BCMsg lineMsg = bizMsg.B.no(slctLineIndex);
            // S21_NA#11561 add start
            if (!S21StringUtil.isEquals(lineMsg.imptLineFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {

                // skip excluded import line.
                return;
            }
            // S21_NA#11561 add end
            checkExistWh(bizMsg, lineMsg);
        } else {
            NWAL2200_DCMsg rmaLineMsg = bizMsg.D.no(slctLineIndex);
            Map<String, String> whInfo = getWhInfo(bizMsg, rmaLineMsg);
            if (whInfo == null) {
                return;
            }
            String whCd = whInfo.get("RTL_WH_CD");
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhCd_RL, whCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlWhNm_RL, whInfo.get("RTL_WH_NM"));

            // call NLZC2150 Get Default Sub Warehouse API
            NLZC215001PMsg defSubWhApiPMsg = callDefSubWhApi(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), whCd);
            if (defSubWhApiPMsg == null) {
                return;
            }
            String rtlSwhCd = defSubWhApiPMsg.destRtlSwhCd.getValue();
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, getSubWhNm(bizMsg, whCd, rtlSwhCd));
            // ZYPEZDItemValueSetter.setValue(rmaLineMsg.thirdPtyDspTpCd_RL,
            // defSubWhApiPMsg.thirdPtyDspTpCd);
        }
    }

    /**
     * OnBlur_DeriveFromBillToAccount Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromBillToAccount(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        SELL_TO_CUSTTMsg dsAcctCustTMsg = getDsAcctCustInfo(bizMsg, false, bizMsg.billToCustAcctCd, "Bill To Number");
        if (dsAcctCustTMsg == null) {
            return;
        }

        // 2019/02/08 QC#30237 Mod Start
        //cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);
        cmnProcForDeriveFromBillTo(bizMsg, glblMsg, dsAcctCustTMsg);
        // 2019/02/08 QC#30237 Mod End

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, sellToCustCd_BK, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307

    }

    /**
     * OnBlur_DeriveFromBillToLocation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromBillToLocation(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String shipToCustCd_BK = "";
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        // Mod Start 2017/09/20 QC#21118
        String dplyTab = bizMsg.xxDplyTab.getValue();
        int idx = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem billToCustCd = null;
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            billToCustCd = bizMsg.A.no(idx).billToCustLocCd_LC;
            shipToCustCd_BK = bizMsg.A.no(idx).shipToCustLocCd_LC.getValue(); 
        } else if (TAB_RMA.equals(dplyTab)) {
            billToCustCd = bizMsg.C.no(idx).billToCustLocCd_RC;
            shipToCustCd_BK = bizMsg.C.no(idx).shipToCustLocCd_RC.getValue();
        } else {
            billToCustCd = bizMsg.billToCustCd;
            shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        }
//        Map<String, String> billToInfo = getBillToInfo(bizMsg, bizMsg.billToCustCd, "Bill To Location");
        Map<String, String> billToInfo = getBillToInfo(bizMsg, billToCustCd, "Bill To Location");
        if (billToInfo == null) {
            return;
        }

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).billToCustAcctCd_LC, billToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).billToCustLocCd_LC, billToInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxAllLineAddr_LB, billToInfo.get("BILL_TO_ADDR"));

            // Add Start 2018/07/25 S21_NA#14307
            NWAL2200CommonLogic.cmnProcForSpecialInstructionForLine(bizMsg, bizMsg.A.no(idx), null, shipToCustCd_BK);
            // Add End 2018/07/25 S21_NA#14307
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).billToCustAcctCd_RC, billToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).billToCustLocCd_RC, billToInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxAllLineAddr_RB, billToInfo.get("BILL_TO_ADDR"));

            // Add Start 2018/07/25 S21_NA#14307
            NWAL2200CommonLogic.cmnProcForSpecialInstructionForLine(bizMsg, bizMsg.C.no(idx), null, shipToCustCd_BK);
            // Add End 2018/07/25 S21_NA#14307
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToInfo.get("BILL_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr, billToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr, billToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.thirdLineAddr, billToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.frthLineAddr, billToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr, billToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.stCd, billToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.postCd, billToInfo.get("POST_CD"));
            deriveDefaultPmtTerm(bizMsg);
            // 2019/02/08 QC#30237 Mod Start
            //deriveDefaultBillToLocForDtlFld(bizMsg);
            deriveDefaultBillToLocForDtlFld(bizMsg, glblMsg);
            // 2019/02/08 QC#30237 Mod End

            // Add Start 2018/07/25 S21_NA#14307
            NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, sellToCustCd_BK, null, shipToCustCd_BK);
            // Add End 2018/07/25 S21_NA#14307
        }
        // Mod End 2017/09/20 QC#21118

    }

    /**
     * OnBlur_DeriveFromBillToName Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromBillToName(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        SELL_TO_CUSTTMsg dsAcctCustTMsg = getDsAcctCustInfo(bizMsg, true, bizMsg.billToCustAcctNm, "Bill To Name");
        if (dsAcctCustTMsg == null) {
            return;
        }

        // 2019/02/08 QC#30237 Mod Start
        //cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);
        cmnProcForDeriveFromBillTo(bizMsg, glblMsg, dsAcctCustTMsg);
        // 2019/02/08 QC#30237 Mod End

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, sellToCustCd_BK, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307

    }

    /**
     * OnBlur_DeriveFromCategory Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromCategory(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        if (!checkCatg(bizMsg)) {
            return;
        }

        // create pull down
        String slsDt = bizMsg.slsDt.getValue();
        createRsnCdPulldown(bizMsg);
        createSubRsnCdPulldown(bizMsg, slsDt);
        String primaryLineCatgCd = createLineCatgPulldown(bizMsg, slsDt);
        String primaryLineCatgRmaCd = createLineCatgPulldownForRma(bizMsg, slsDt);

        // PullDown Initial Value
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, bizMsg.dsOrdTpCd_CD.no(0));
        bizMsg.dsOrdRsnCd.clear();
        setInitValueForLineCatgPulldown(bizMsg, primaryLineCatgCd, primaryLineCatgRmaCd);

        // set Default Data
        deriveDefaultData(bizMsg, slsDt);

        // 2018/01/23 QC#18798 Add Start
        getAllOrdCatgBizCtx(bizMsg);
        // 2018/01/23 QC#18798 Add End

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg);
        // Add End 2018/07/25 S21_NA#14307
    }

    /**
     * OnBlur_DeriveFromShipToAccount Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromShipToAccount(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        SELL_TO_CUSTTMsg dsAcctCustTMsg = getDsAcctCustInfo(bizMsg, false, bizMsg.shipToCustAcctCd, "Ship To Number");
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        cmnProcForDeriveFromShipTo(bizMsg, glblMsg, true, -1);

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, sellToCustCd_BK, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307

    }

    /**
     * OnBlur_DeriveFromShipToLocation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromShipToLocation(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = "";
        //String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        Map<String, String> shipToInfo = getShipToInfo(bizMsg);
        if (shipToInfo == null) {
            return;
        }

        // Mod Start 2017/09/15 QC#21118
        String dplyTab = bizMsg.xxDplyTab.getValue();
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToCustAcctCd_LC, shipToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToCustLocCd_LC, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToAddlLocNm_LC, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).xxAllLineAddr_LS, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToFirstLineAddr_LC, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToScdLineAddr_LC, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToThirdLineAddr_LC, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToFrthLineAddr_LC, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToFirstRefCmntTxt_LC, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToScdRefCmntTxt_LC, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToCtyAddr_LC, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToStCd_LC, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToPostCd_LC, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToProvNm_LC, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToCtryCd_LC, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).shipToCntyNm_LC, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).dropShipFlg_LC, ZYPConstant.FLG_OFF_N);

            // 2019/02/08 QC#30237 Add Start
            cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false, idx);
            // 2019/02/08 QC#30237 Add End

            // Add Start 2018/07/25 S21_NA#14307
            billToCustCd_BK = bizMsg.A.no(idx).billToCustLocCd_LC.getValue();
            NWAL2200CommonLogic.cmnProcForSpecialInstructionForLine(bizMsg, bizMsg.A.no(idx), billToCustCd_BK, null);
            // Add End 2018/07/25 S21_NA#14307
        } else if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToCustAcctCd_RC, shipToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToCustLocCd_RC, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToAddlLocNm_RC, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).xxAllLineAddr_RS, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToFirstLineAddr_RC, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToScdLineAddr_RC, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToThirdLineAddr_RC, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToFrthLineAddr_RC, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToFirstRefCmntTxt_RC, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToScdRefCmntTxt_RC, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToCtyAddr_RC, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToStCd_RC, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToPostCd_RC, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToProvNm_RC, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToCtryCd_RC, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).shipToCntyNm_RC, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(idx).dropShipFlg_RC, ZYPConstant.FLG_OFF_N);

            // 2019/02/08 QC#30237 Add Start
            cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false, idx);
            // 2019/02/08 QC#30237 Add End

            // Add Start 2018/07/25 S21_NA#14307
            billToCustCd_BK = bizMsg.C.no(idx).billToCustLocCd_RC.getValue();
            NWAL2200CommonLogic.cmnProcForSpecialInstructionForLine(bizMsg, bizMsg.C.no(idx), billToCustCd_BK, null);
            // Add End 2018/07/25 S21_NA#14307
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToInfo.get("SHIP_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToInfo.get("ADDL_LOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToInfo.get("SHIP_TO_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToInfo.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToInfo.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToInfo.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToInfo.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipTo01RefCmntTxt, shipToInfo.get("FIRST_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipTo02RefCmntTxt, shipToInfo.get("SCD_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToInfo.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToInfo.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToInfo.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToInfo.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToInfo.get("CTRY_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToInfo.get("CNTY_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);

            cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false, -1);

            // 2019/02/08 QC#30237 Add Start
            deriveDefaultShipToLocForDtlFld(bizMsg, glblMsg);
            // 2019/02/08 QC#30237 Add End

            // Add Start 2018/07/25 S21_NA#14307
            billToCustCd_BK = bizMsg.billToCustCd.getValue();
            NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, sellToCustCd_BK, billToCustCd_BK, null);
            // Add End 2018/07/25 S21_NA#14307
        }
        // Mod End 2017/09/15 QC#21118

    }

    /**
     * OnBlur_DeriveFromShipToName Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromShipToName(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        //String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        SELL_TO_CUSTTMsg dsAcctCustTMsg = getDsAcctCustInfo(bizMsg, true, bizMsg.shipToCustAcctNm, "Ship To Name");
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        cmnProcForDeriveFromShipTo(bizMsg, glblMsg, true, -1);

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, null, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307
    }

    /**
     * OnBlur_DeriveFromSoldToAccount Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromSoldToAccount(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        //String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        SELL_TO_CUSTTMsg dsAcctCustTMsg = getDsAcctCustInfo(bizMsg, false, bizMsg.sellToCustCd, "Sold To Number");
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, true);

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, null, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307
    }

    /**
     * OnBlur_DeriveFromSoldToLocation Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromSoldToLocation(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        //String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        Map<String, String> billToInfo = getBillToInfo(bizMsg, bizMsg.soldToCustLocCd, "Sold To Location");
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, billToInfo.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToInfo.get("BILL_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr, billToInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr, billToInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.thirdLineAddr, billToInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.frthLineAddr, billToInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr, billToInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.stCd, billToInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.postCd, billToInfo.get("POST_CD"));

        cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, false);

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, null, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307
    }

    /**
     * OnBlur_DeriveFromSoldToName Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromSoldToName(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // Add Start 2018/07/25 S21_NA#14307
        String sellToCustCd_BK = bizMsg.sellToCustCd.getValue();
        String billToCustCd_BK = bizMsg.billToCustCd.getValue();
        String shipToCustCd_BK = bizMsg.shipToCustCd.getValue();
        bizMsg.xxRqstFlg.clear();
        // Add End 2018/07/25 S21_NA#14307

        SELL_TO_CUSTTMsg dsAcctCustTMsg = getDsAcctCustInfo(bizMsg, true, bizMsg.soldToCustAcctNm, "Sold To Name");
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, true);

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg, sellToCustCd_BK, billToCustCd_BK, shipToCustCd_BK);
        // Add End 2018/07/25 S21_NA#14307
    }

    // QC#13688 2017/02/24 Add Start
    private void doProcess_NWAL2200Scrn00_OnBlur_DeriveFromFreightTerms(NWAL2200CMsg bizMsg) {

        Map<String, String> freightTermInfo = getFreightTermInfo(bizMsg);
        if (freightTermInfo == null) {
            bizMsg.frtCondCd.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, freightTermInfo.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, freightTermInfo.get("FRT_COND_DESC_TXT"));

        setShpgSvcLvlPullDown(bizMsg);
        // 2018/12/21 QC#29315 Del Start
//        deriveDefaultCarrSvcLvl(bizMsg);
        // 2018/12/21 QC#29315 Del End
    }

    private void doProcess_NWAL2200Scrn00_OnChange_ShpgSvcLvlCd(NWAL2200CMsg bizMsg) {
        // 2018/12/21 QC#29315 Del Start
//        deriveDefaultCarrSvcLvl(bizMsg);
        // 2018/12/21 QC#29315 Del End
    }

    // QC#13688 2017/02/24 Add End

    /**
     * OnChange_LineCategory Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnChange_LineCategory(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

    }

    /**
     * OnChange_OrderReason Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OnChange_OrderReason(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        createSubRsnCdPulldown(bizMsg, bizMsg.slsDt.getValue());

        String primaryLineCatgCd = createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        String primaryLineCatgRmaCd = createLineCatgPulldownForRma(bizMsg, bizMsg.slsDt.getValue());

        // PullDown Initial Value
        bizMsg.dsOrdRsnCd.clear();
        setInitValueForLineCatgPulldown(bizMsg, primaryLineCatgCd, primaryLineCatgRmaCd);

        // set Default Data
        deriveDefaultData(bizMsg, bizMsg.slsDt.getValue());

        // 2018/01/23 QC#18798 Add Start
        getAllOrdCatgBizCtx(bizMsg);
        // 2018/01/23 QC#18798 Add End

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg);
        // Add End 2018/07/25 S21_NA#14307
    }

    /**
     * OpenWin_Buyout Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OpenWin_Buyout(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // 2019/02/08 S21_NA#30327 Mod Start
//        NWAL2200_BCMsg lineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());
//
//        // Add Start 2017/06/27 QC#18363
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, MODE_REFERENCE);
//        // Add End 2017/06/27 QC#18363
//        
//        // 2019/02/01 Mod Start S21_NA#30097
////        setSoldToInfo(bizMsg);
//        setSoldToInfo(bizMsg, false, null, ZYPConstant.FLG_ON_Y);
//        // 2019/02/01 Mod End S21_NA#30097
//        setBillToInfo(bizMsg, lineMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, MODE_REFERENCE);
        // 2019/02/08 S21_NA#30327 Mod End
    }

    /**
     * OpenWin_PriceChanges Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OpenWin_PriceChanges(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        List<Integer> selectedRows = new ArrayList<Integer>();
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        if (selectedRows.size() == 0) {
            bizMsg.setMessageInfo(NWAM0667E, new String[] {"Line" });
            return;
        }

        if (selectedRows.size() > 1) {
            bizMsg.setMessageInfo(NWAM0683E);
            return;
        }

        if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            if (isSetComponent(bizMsg.D.no(selectedRows.get(0)))) {
                bizMsg.setMessageInfo(NWAM0667E, new String[] {"Price Change" });
                return;
            }
        }
// QC#17124 add Start
        if (!NWAL2200CommonLogic.refreshInputData(bizMsg, glblMsg, false)) {
            return;
        }

        if (!NWAL2200CommonLogic.deriveCustomerAccount(bizMsg, glblMsg)) {
            return;
        }

        String shipToCustAcctCd;
        String billToCustAcctCd;
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL2200_ACMsg config = getConfigLine(bizMsg.A, bizMsg.B.no(selectedRows.get(0)).dsOrdPosnNum_LL.getValue());
            shipToCustAcctCd = config.shipToCustAcctCd_LC.getValue();
            billToCustAcctCd = config.billToCustAcctCd_LC.getValue();
        } else {
            NWAL2200_CCMsg config = getConfigLine(bizMsg.C, bizMsg.D.no(selectedRows.get(0)).dsOrdPosnNum_RL.getValue());
            shipToCustAcctCd = config.shipToCustAcctCd_RC.getValue();
            billToCustAcctCd = config.billToCustAcctCd_RC.getValue();
        }
        if (!ZYPCommonFunc.hasValue(shipToCustAcctCd)) {
            bizMsg.setMessageInfo(NWAM0021E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(billToCustAcctCd)) {
            bizMsg.setMessageInfo(NWAM0235E);
            return;
        }
// QC#17124 add End
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            setParametersForPriceChanges(bizMsg, bizMsg.B.no(selectedRows.get(0)), glblMsg.I);
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            setParametersForPriceChanges(bizMsg, bizMsg.D.no(selectedRows.get(0)), glblMsg.I);
        }
    }

    /**
     * OpenWin_SpecialInstruction
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OpenWin_SpecialInstruction(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCategoryId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(2).xxPopPrm, funcCategoryId);
        // 2018/07/10 S21_NA#26661,26713 Add End
        String transactionType = NWAL2200CommonLogic.getTrxRuleTp(getGlobalCompanyCode(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(3).xxPopPrm, transactionType);
        // 2018/07/24 S21_NA#14307 Mod End

        // Add Start 2018/07/25 S21_NA#14307
        NWAL2200CommonLogic.cmnProcForSpecialInstruction(bizMsg);
        // Add End 2018/07/25 S21_NA#14307
    }

    /**
     * OpenWin_ShipToChange
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_OpenWin_ShipToChange(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        checkExistCustomer(bizMsg);
    }

    /**
     * Order_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_Order_Search(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        searchOrder(bizMsg, glblMsg);
    }

    /**
     * TAB_Addl_Header Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_TAB_Addl_Header(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * TAB_Errors Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_TAB_Errors(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, HDR_ID_ERRORS);
    }

    /**
     * TAB_Header Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_TAB_Header(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * TAB_Line_Config Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_TAB_Line_Config(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, HDR_ID_LINE);
        // 2018/08/31 S21_NA#26022 Add Start
        NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, 1);
        // 2018/08/31 S21_NA#26022 Add End
    }

    /**
     * TAB_RMA Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_TAB_RMA(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, HDR_ID_RMA);
        // 2018/08/31 S21_NA#26022 Add Start
        NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, 1);
        // 2018/08/31 S21_NA#26022 Add End
    }

    /**
     * Validate Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_Validate(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        searchOrder(bizMsg, glblMsg);
    }

    // 2017/01/26 S21_NA#17119 Add Start
    /**
     * NMAL6050 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NMAL6050(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        if ("OpenWin_FreightTerms".equals(scrEventNm) || "OnBlur_DeriveFromFreightTerms".equals(scrEventNm)){
            setShpgSvcLvlPullDown(bizMsg);
            // 2018/12/21 QC#29315 Del Start
//            deriveDefaultCarrSvcLvl(bizMsg);
            // 2018/12/21 QC#29315 Del End
        }
    }
    // 2017/01/26 S21_NA#17119 Add End
    /**
     * NMAL6760 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NMAL6760(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        String scrEventNm = bizMsg.xxScrEventNm.getValue();
        int slctLine = bizMsg.xxCellIdx.getValueInt();

        // Mod Start 2017/09/15 QC#21118
        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (("OpenWin_BillTo".equals(scrEventNm) && slctLine == -1) || "OnBlur_DeriveFromBillToName".equals(scrEventNm) || "OnBlur_DeriveFromBillToAccount".equals(scrEventNm) || "OnBlur_DeriveFromBillToLocation".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, cmbnAddr(bizMsg));
            deriveDefaultPmtTerm(bizMsg);
            // 2019/02/08 QC#30237 Mod Start
            //deriveDefaultBillToLocForDtlFld(bizMsg);
            deriveDefaultBillToLocForDtlFld(bizMsg, glblMsg);
            // 2019/02/08 QC#30237 Mod End
        } else if (("OpenWin_ShipTo".equals(scrEventNm) && slctLine == -1) || "OnBlur_DeriveFromShipToName".equals(scrEventNm) || "OnBlur_DeriveFromShipToAccount".equals(scrEventNm) || "OnBlur_DeriveFromShipToLocation".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, cmbnAddr(bizMsg));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            cmnProcForDeriveFromShipTo(bizMsg, glblMsg, false, slctLine);
        } else if ("OpenWin_SoldTo".equals(scrEventNm) || "OnBlur_DeriveFromSoldToName".equals(scrEventNm) || "OnBlur_DeriveFromSoldToAccount".equals(scrEventNm) || "OnBlur_DeriveFromSoldToLocation".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, cmbnAddr(bizMsg));
            cmnProcForDeriveFromSoldTo(bizMsg, glblMsg, false);
        } else if (("OpenWin_BillTo".equals(scrEventNm) && slctLine > -1) || ("OnBlur_DeriveFromBillToLocation".equals(scrEventNm) && slctLine > -1)) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctLine).xxAllLineAddr_LB, cmbnAddr(bizMsg));
            } else if (TAB_RMA.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctLine).xxAllLineAddr_RB, cmbnAddr(bizMsg));
            }
        } else if (("OpenWin_ShipTo".equals(scrEventNm) && slctLine > -1) || ("OnBlur_DeriveFromShipToLocation".equals(scrEventNm) && slctLine > -1)) {
            // Add Start 2019/03/15 QC#30730
            boolean isSoldToSet = false;
            if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
                isSoldToSet = true;
            }
            // Add End 2019/03/15 QC#30730

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctLine).xxAllLineAddr_LS, cmbnAddr(bizMsg));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(slctLine).dropShipFlg_LC, ZYPConstant.FLG_OFF_N);

                // Add Start 2019/03/15 QC#30730
                String shipToCustCd = bizMsg.A.no(slctLine).shipToCustLocCd_LC.getValue();
                setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
                // Add End 2019/03/15 QC#30730
            } else if (TAB_RMA.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctLine).xxAllLineAddr_RS, cmbnAddr(bizMsg));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(slctLine).dropShipFlg_RC, ZYPConstant.FLG_OFF_N);

                // Add Start 2019/03/15 QC#30730
                String shipToCustCd = bizMsg.C.no(slctLine).shipToCustLocCd_RC.getValue();
                setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
                // Add End 2019/03/15 QC#30730
            }
        }
        // Mod End 2017/09/15 QC#21118
    }

    /**
     * NWAL0140 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL0140(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        // no process
    }

    /**
     * NWAL1130 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1130(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            doProcess_NWAL2200Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);
        } else if ("OpenWin_Salesrep".equals(scrEventNm)) {
            deriveDefaultSlsRepForHdr(bizMsg, bizMsg.slsRepTocCd.getValue());
        } else if ("OpenWin_Warehouse".equals(scrEventNm)) {
            NWAL2200_DCMsg rmaLineMsg = bizMsg.D.no(bizMsg.xxCellIdx.getValueInt());
            // default WH
            NLZC215001PMsg defSubWhApiPMsg = callDefSubWhApi(bizMsg, rmaLineMsg.mdseCd_RL.getValue(), rmaLineMsg.rtlWhCd_RL.getValue());
            if (defSubWhApiPMsg == null) {
                return;
            }
            String rtlSwhCd = defSubWhApiPMsg.destRtlSwhCd.getValue();
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhCd_RL, rtlSwhCd);
            ZYPEZDItemValueSetter.setValue(rmaLineMsg.rtlSwhNm_RL, getSubWhNm(bizMsg, rmaLineMsg.rtlWhCd_RL.getValue(), rtlSwhCd));
            // ZYPEZDItemValueSetter.setValue(rmaLineMsg.thirdPtyDspTpCd_RL,
            // defSubWhApiPMsg.thirdPtyDspTpCd);
        }
    }

    /**
     * NWAL1500 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1500(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL1600 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1600(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL1630 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1630(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL1640 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1640(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL1660 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1660(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL1760 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL1760(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL2240 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL2240(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    /**
     * NWAL2250 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL2250(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        //
    }

    // UPDATE START 2016/08/31 Unit Test#202
    /**
     * NWAL2340 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200_NWAL2340(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        if (EVENT_NM_NMAL2340_LINE.equals(bizMsg.xxScrEventNm.getValue())) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

                if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).shipToCustLocCd_LC)) {
                    continue;
                }
                S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getShipToCustAddr(bizMsg, bizMsg.A.no(i).shipToCustLocCd_LC.getValue());
                if (ssmResult.isCodeNotFound()) {
                    return;
                }

                @SuppressWarnings("unchecked")
                Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToCustLocCd_LC, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
                // Add Start 2017/09/20 QC#21118
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).xxAllLineAddr_LS, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
                // Add End 2017/09/20 QC#21118
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToLocNm_LC, shipToCustAddrInfo.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToFirstLineAddr_LC, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToScdLineAddr_LC, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToThirdLineAddr_LC, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToFrthLineAddr_LC, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToCtyAddr_LC, shipToCustAddrInfo.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToStCd_LC, shipToCustAddrInfo.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToPostCd_LC, shipToCustAddrInfo.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToProvNm_LC, shipToCustAddrInfo.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToCtryCd_LC, shipToCustAddrInfo.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).shipToCntyNm_LC, shipToCustAddrInfo.get("CNTY_NM"));
            }
        } else if (EVENT_NM_NMAL2340_RMA.equals(bizMsg.xxScrEventNm.getValue())) {
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {

                if (!ZYPCommonFunc.hasValue(bizMsg.C.no(i).shipToCustLocCd_RC)) {
                    continue;
                }

                S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getShipToCustAddr(bizMsg, bizMsg.C.no(i).shipToCustLocCd_RC.getValue());
                if (ssmResult.isCodeNotFound()) {
                    return;
                }

                @SuppressWarnings("unchecked")
                Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToCustLocCd_RC, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
                // Add Start 2017/09/20 QC#21118
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).xxAllLineAddr_RS, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
                // Add End 2017/09/20 QC#21118
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToLocNm_RC, shipToCustAddrInfo.get("LOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToFirstLineAddr_RC, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToScdLineAddr_RC, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToThirdLineAddr_RC, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToFrthLineAddr_RC, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToCtyAddr_RC, shipToCustAddrInfo.get("CTY_ADDR"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToStCd_RC, shipToCustAddrInfo.get("ST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToPostCd_RC, shipToCustAddrInfo.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToProvNm_RC, shipToCustAddrInfo.get("PROV_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToCtryCd_RC, shipToCustAddrInfo.get("CTRY_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).shipToCntyNm_RC, shipToCustAddrInfo.get("CNTY_NM"));
            }
        }
    }

    // UPDATE END 2016/08/31 Unit Test#202

    // UPDATE START 2016/09/14 QC#13769
    private void doProcess_NWAL2200Scrn00_OnChange_DclnSvcCdConfig(NWAL2200CMsg bizMsg) {

        int dclnOffCnt = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_1.equals(bizMsg.A.no(i).dclnSvcCd_LC.getValue())) {
                dclnOffCnt++;
            }
        }

        if (dclnOffCnt <= 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dclnSvcCd, ZYPConstant.CHKBOX_ON_1);
        } else {
            bizMsg.dclnSvcCd.clear();
        }
    }

    private void doProcess_NWAL2200Scrn00_OnChange_DclnSvcCdHdr(NWAL2200CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_1.equals(bizMsg.dclnSvcCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dclnSvcCd_LC, ZYPConstant.CHKBOX_ON_1);
            } else {
                bizMsg.A.no(i).dclnSvcCd_LC.clear();
            }
        }
    }

    // UPDATE END 2016/09/14 QC#13769

    // QC#19808 2018/01/25 Add Start
    private void doProcess_NWAL2200Scrn00_PageJump(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        
        String dplyTab = bizMsg.xxDplyTab.getValue();
        // copy data from glblMsg onto bizMsg
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForOutbound(glblMsg, bizMsg);
            int page = ((bizMsg.xxPageShowCurNum_LC.getValueInt() - 1) * bizMsg.B.length()) + 1;
            NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, page);
            if (bizMsg.A.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DL, bizMsg.A.no(0).dsOrdPosnNum_LC);
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForInbound(glblMsg, bizMsg);
            int page = ((bizMsg.xxPageShowCurNum_RC.getValueInt() - 1) * bizMsg.D.length()) + 1;
            NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, page);
            if (bizMsg.C.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DR, bizMsg.C.no(0).dsOrdPosnNum_RC);
            }
        } else {
            int page = ((bizMsg.xxPageShowCurNum_EL.getValueInt() - 1) * bizMsg.E.length()) + 1;
            NWAL2200CommonLogic.loadOnePageToCMsgForError(glblMsg, bizMsg, page);
        }
    }

    private void doProcess_NWAL2200Scrn00_PageNext(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        String dplyTab = bizMsg.xxDplyTab.getValue();
        // copy data from glblMsg onto bizMsg
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForOutbound(glblMsg, bizMsg);
            int page = bizMsg.xxPageShowToNum_LC.getValueInt() + 1;
            NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, page);
            if (bizMsg.A.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DL, bizMsg.A.no(0).dsOrdPosnNum_LC);
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForInbound(glblMsg, bizMsg);
            int page = bizMsg.xxPageShowToNum_RC.getValueInt() + 1;
            NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, page);
            if (bizMsg.C.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DR, bizMsg.C.no(0).dsOrdPosnNum_RC);
            }
        } else {
            int page = bizMsg.xxPageShowToNum_EL.getValueInt() + 1;
            NWAL2200CommonLogic.loadOnePageToCMsgForError(glblMsg, bizMsg, page);
        }

    }

    /**
     * Page Previous Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL2200Scrn00_PagePrev(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        S21StopWatch sw = new S21StopWatch();

        String dplyTab = bizMsg.xxDplyTab.getValue();
        // copy data from glblMsg onto bizMsg
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForOutbound(glblMsg, bizMsg);
            int page = bizMsg.xxPageShowFromNum_LC.getValueInt() - bizMsg.B.length();
            NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, page);
            if (bizMsg.A.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DL, bizMsg.A.no(0).dsOrdPosnNum_LC);
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForInbound(glblMsg, bizMsg);
            int page = bizMsg.xxPageShowFromNum_RC.getValueInt() - bizMsg.D.length();
            NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, page);
            if (bizMsg.C.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DR, bizMsg.C.no(0).dsOrdPosnNum_RC);
            }
        } else {
            int page = bizMsg.xxPageShowToNum_EL.getValueInt() - bizMsg.D.length();
            NWAL2200CommonLogic.loadOnePageToCMsgForError(glblMsg, bizMsg, page);
        }

        sw.stop();
        System.out.println("$$$$$$ " + "NWAL2200Scrn00_PagePrevt" + sw.getElapsedMilliSec());
    }

    private void doProcess_NWAL2200Scrn00_ConfigJump(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        S21StopWatch sw = new S21StopWatch();

        int page = 1;
        int posn = 0;
        int linePosn = 0;

        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForOutbound(glblMsg, bizMsg);
            int dsOrdPosnNum = Integer.parseInt(bizMsg.dsOrdPosnNum_DL.getValue());
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                linePosn = Integer.parseInt(glblMsg.B.no(i).dsOrdPosnNum_LL.getValue());
                if (linePosn < dsOrdPosnNum) {
                    posn++;
                }
            }
            page = posn;
            NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, page);
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200CommonLogic.storeOnePageToSMsglblMsgForInbound(glblMsg, bizMsg);
            int dsOrdPosnNum = Integer.parseInt(bizMsg.dsOrdPosnNum_DR.getValue());
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                linePosn = Integer.parseInt(glblMsg.D.no(i).dsOrdPosnNum_RL.getValue());
                if (linePosn < dsOrdPosnNum) {
                    posn++;
                }
            }
            page = posn;
            NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, page);
        }

        sw.stop();
        System.out.println("$$$$$$ " + "NWAL2200Scrn00_ConfigJump" + sw. getElapsedMilliSec());
    }
    // QC#19808 2018/01/25 Add End

    // 2018/12/13 S21_NA#29315 Add Start
    private void doProcess_NWAL2200Scrn00_OpenWin_CarrierServiceLevel(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue()));
    }
    // 2018/12/13 S21_NA#29315 Add End

    private void initialize(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        glblMsg.clear();

        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(glblMsg.E);
        ZYPTableUtil.clear(glblMsg.F);
        ZYPTableUtil.clear(glblMsg.G);
        ZYPTableUtil.clear(glblMsg.H);
        ZYPTableUtil.clear(glblMsg.I);
        ZYPTableUtil.clear(glblMsg.J);
        ZYPTableUtil.clear(glblMsg.K);

        // log type
        ZYPCodeDataUtil.createPulldownList("ORD_LOG_TP", bizMsg.ordLogTpCd_CD, bizMsg.ordLogTpDescTxt_NM);
        // Special Handling
        ZYPCodeDataUtil.createPulldownList("SPCL_HDLG_TP", bizMsg.spclHdlgTpCd_CD, bizMsg.spclHdlgTpDescTxt_NM);
        // Service Level
        ZYPCodeDataUtil.createPulldownList("SHPG_SVC_LVL", bizMsg.shpgSvcLvlCd_CD, bizMsg.shpgSvcLvlDescTxt_NM);
        // Payment Method
        ZYPCodeDataUtil.createPulldownList("DS_PMT_METH", bizMsg.dsPmtMethCd_CD, bizMsg.dsPmtMethDescTxt_NM);
        // PRE Payment Type
        ZYPCodeDataUtil.createPulldownList("PRE_PMT_TP", bizMsg.prePmtTpCd_CD, bizMsg.prePmtTpDescTxt_NM);
        // End of term purchase option
        ZYPCodeDataUtil.createPulldownList("LEASE_PRCH_OPT", bizMsg.leasePrchOptCd_CD, bizMsg.leasePrchOptDescTxt_NM);
        // Lease Payment Frequency
        ZYPCodeDataUtil.createPulldownList("LEASE_PMT_FREQ", bizMsg.leasePmtFreqCd_CD, bizMsg.leasePmtFreqDescTxt_NM);
        // Return Reason
        ZYPCodeDataUtil.createPulldownList("RTRN_RSN", bizMsg.rtrnRsnCd_CD, bizMsg.rtrnRsnDescTxt_NM);
        // HDD Removable
        ZYPCodeDataUtil.createPulldownList("HDD_RMV", bizMsg.hddRmvCd_CD, bizMsg.hddRmvDescTxt_NM);
        // Source
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getOrdLineSrcDataList(queryParam);
        if (ssmResult.isCodeNormal()) {

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int n = 0; n < rsltList.size() && n < bizMsg.ordLineSrcCd_CD.length(); n++) {
                Map<String, Object> rsltMap = rsltList.get(n);
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcCd_CD.no(n), (String) rsltMap.get("ORD_LINE_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcNm_NM.no(n), (String) rsltMap.get("ORD_LINE_SRC_NM"));
            }
        }

        GLBL_CMPYTMsg glblCmpy = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpy.glblCmpyCd, bizMsg.glblCmpyCd);
        glblCmpy = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblCmpy);
        if (glblCmpy != null) {
            CCYTMsg ccy = new CCYTMsg();
            ZYPEZDItemValueSetter.setValue(ccy.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ccy.ccyCd, glblCmpy.stdCcyCd);
            ccy = (CCYTMsg) S21FastTBLAccessor.findByKey(ccy);
            if (ccy != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccy.aftDeclPntDigitNum);
            }
        }

        EZDMsg.copy(bizMsg, null, glblMsg, null);

        // QC#19808 2018/01/25 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DL, String.valueOf(1));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_TL, String.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_DR, String.valueOf(1));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_TR, String.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum_LC, BigDecimal.valueOf(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum_LC, BigDecimal.valueOf(0));
        // QC#19808 2018/01/25 Add End

        if (ZYPCommonFunc.hasValue(bizMsg.ordSrcRefNum) || ZYPCommonFunc.hasValue(bizMsg.dsImptOrdPk)) {
            searchOrder(bizMsg, glblMsg);
        }
    }

    private void searchOrder(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        glblMsg.clear();

        // QC#6695
        if (!ZYPCommonFunc.hasValue(bizMsg.ordSrcRefNum) && !ZYPCommonFunc.hasValue(bizMsg.dsImptOrdPk)) {
            bizMsg.ordSrcRefNum.setErrorInfo(1, NWAL2200Constant.ZZM9000E, new String[] {"Source Reference Num" });
            return;
        }

        /**************************************************
         * IMPT_ORD
         **************************************************/
        List<Map<String, Object>> imptOrd = getImptOrd(bizMsg.glblCmpyCd.getValue(), bizMsg.ordSrcRefNum.getValue(), bizMsg.dsImptOrdPk.getValue());
        if (imptOrd.size() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }
        // 2017/03/09 S21_NA#16790 Add Start
        // 2017/03/21 S21_NA#18104 Mod Start
//        String cpoOrdNum = getOrigCpoOrdNum(bizMsg.glblCmpyCd.getValue(), bizMsg.dsImptOrdPk.getValue());
        BigDecimal dsImptOrdPk = (BigDecimal) imptOrd.get(0).get("DS_IMPT_ORD_PK");
        // 2019/03/28 S21_NA#30765 Mod Start
        // String cpoOrdNum = getOrigCpoOrdNum(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk);
        List<String> cpoOrdNumList = getOrigCpoOrdNum(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk);
        String cpoOrdNum = null;
        if (cpoOrdNumList != null && cpoOrdNumList.size() > 0) {
            cpoOrdNum = cpoOrdNumList.get(0);
        }
        // 2019/03/28 S21_NA#30765 Mod End
        // 2017/03/21 S21_NA#18104 Mod End

        // 2019/03/25 S21_NA#30924 Mod Start
        // imptOrd.get(0).put("CPO_ORD_NUM", cpoOrdNum);
        if (ZYPCommonFunc.hasValue(cpoOrdNum)) {
            imptOrd.get(0).put("CPO_ORD_NUM", cpoOrdNum);
        }
        // 2019/03/25 S21_NA#30924 Mod End
        // 2017/03/09 S21_NA#16790 Add End

        // 2017/03/21 S21_NA#18104 Del Start
//        BigDecimal dsImptOrdPk = (BigDecimal) imptOrd.get(0).get("DS_IMPT_ORD_PK");
        // 2017/03/21 S21_NA#18104 Del End

        /**************************************************
         * IMPT_ORD_CONFIG
         **************************************************/
        // out bound
        List<Map<String, Object>> imptOrdConfig = getImptOrdConfig(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, CONFIG_CATG.OUTBOUND, glblMsg.A.length() + 1);

        // in bound
        List<Map<String, Object>> imptOrdRtrnConfig = getImptOrdConfig(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, CONFIG_CATG.INBOUND, glblMsg.C.length() + 1);
        if (imptOrdConfig.size() == 0 && imptOrdRtrnConfig.size() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        /**************************************************
         * IMPT_ORD_DTL, IMPT_ORD_RTRN_DTL
         **************************************************/
        // out bound
        List<Map<String, Object>> imptOrdDtl = getImptOrdDtl(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, glblMsg.B.length() + 1);

        // in bound
        List<Map<String, Object>> imptOrdRtrnDtl = getImptOrdRtrnDtl(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, glblMsg.D.length() + 1);
        if (imptOrdDtl.size() == 0 && imptOrdRtrnDtl.size() == 0) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        /**************************************************
         * DS_IMPT_ORD_SLS_CR
         **************************************************/
        // header
        List<Map<String, Object>> imptOrdSlsCrHeader = getImptOrdSlsCr(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, null, bizMsg.F.length() + 1);

        // out bound
        List<Map<String, Object>> imptOrdSlsCrOutBound = getImptOrdSlsCr(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, CONFIG_CATG.OUTBOUND, bizMsg.G.length() + 1);

        // in bound
        List<Map<String, Object>> imptOrdSlsCrInbound = getImptOrdSlsCr(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, CONFIG_CATG.INBOUND, bizMsg.H.length() + 1);

        /**************************************************
         * DS_IMPT_PRC_CALC_BASE, DS_IMPT_RTRN_PRC_CALC
         **************************************************/
        // out bound
        // in bound
        List<Map<String, Object>> imptPrcCalcBase = getImptPrcCalcBase(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, glblMsg.I.length() + 1);

        /**************************************************
         * DS_IMPT_ORD_ERR
         **************************************************/
        List<Map<String, Object>> imptOrdErr = getImptOrdErr(bizMsg.glblCmpyCd.getValue(), dsImptOrdPk, glblMsg.E.length() + 1);

        // set result

        /**************************************************
         * IMPT_ORD
         **************************************************/
        int index = 0;
        for (Map<String, Object> dbResult : imptOrd) {
            NWAL2200CommonLogic.setDbResult(glblMsg, "", dbResult);
            index++;
        }
        EZDMsg.copy(glblMsg, null, bizMsg, null);
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue()));         // QC#17474 2017/02/21 Add

        String ordSrcImptTs = ZYPCommonFunc.rightPad(bizMsg.ordSrcImptTs.getValue(), 17, "0");
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDtTm, ZYPDateUtil.formatEzd17ToDisp(ordSrcImptTs));

        // reason
        createRsnCdPulldown(bizMsg);
        
        // Order Log Type Code
        ZYPEZDItemValueSetter.setValue(bizMsg.ordLogTpCd,(String) imptOrd.get(0).get("ORD_LOG_TP_CD"));//S21_NA#18806(L3#372) ADD

        // sub reason
        createSubRsnCdPulldown(bizMsg, bizMsg.slsDt.getValue());

        // force edit flag
        CPO_SRC_TPTMsg dsCpoOrdTp = new CPO_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoOrdTp.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoOrdTp.cpoSrcTpCd, bizMsg.cpoSrcTpCd);
        dsCpoOrdTp = (CPO_SRC_TPTMsg) S21FastTBLAccessor.findByKey(dsCpoOrdTp);
        if (dsCpoOrdTp != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdFrceEdtFlg, dsCpoOrdTp.dsImptOrdFrceEdtFlg);
        } else {

            ZYPEZDItemValueSetter.setValue(bizMsg.dsImptOrdFrceEdtFlg, ZYPConstant.FLG_OFF_N);
        }
        
        // 2019/03/28 S21_NA#30765 Add Start
        if (S21StringUtil.isEquals(CPO_SRC_TP.DEAL_CONFIGURATOR, bizMsg.cpoSrcTpCd.getValue()) && cpoOrdNumList != null && cpoOrdNumList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2019/03/28 S21_NA#30765 Add End

        // 2017/08/30 S21_NA#20792 Add Start
        // 2018/01/29 S21_NA#23139 Mod Start
        //BigDecimal xxTotBaseAmt_SVC_PRC = (BigDecimal) imptOrd.get(0).get("TOT_BASE_AMT_SVC_PRC");
        BigDecimal xxTotBaseAmt_MT = (BigDecimal) imptOrd.get(0).get("TOT_BASE_AMT_SVC_PRC");
        //BigDecimal xxTotBaseAmt_SVC_ADDL_BASE = (BigDecimal) imptOrd.get(0).get("TOT_BASE_AMT_SVC_ADDL_BASE");
        //BigDecimal xxTotBaseAmt_SVC_ADDL_CHRG = (BigDecimal) imptOrd.get(0).get("TOT_BASE_AMT_SVC_ADDL_CHRG");
        //BigDecimal xxTotBaseAmt_MT = xxTotBaseAmt_SVC_PRC.add(xxTotBaseAmt_SVC_ADDL_BASE).add(xxTotBaseAmt_SVC_ADDL_CHRG);
        // 2018/01/29 S21_NA#23139 Mod End
        BigDecimal xxTotBaseAmt_LN = (BigDecimal) imptOrd.get(0).get("TOT_BASE_AMT_LN");
        BigDecimal xxTotBaseAmt_RT = (BigDecimal) imptOrd.get(0).get("TOT_BASE_AMT_RT");
        // Mod Start 2018/04/13 QC#24076
        BigDecimal xxTotBaseAmt;
        BigDecimal negoDealAmt = (BigDecimal) imptOrd.get(0).get("NEGO_DEAL_AMT");
        if (ZYPCommonFunc.hasValue(negoDealAmt)) {
            xxTotBaseAmt = negoDealAmt;
        } else {
            xxTotBaseAmt = xxTotBaseAmt_MT.add(xxTotBaseAmt_LN).add(xxTotBaseAmt_RT);
        }
        // Mod End 2018/04/13 QC#24076
        BigDecimal xxTotFrtAmt = BigDecimal.ZERO;
        BigDecimal xxTotTaxAmt = BigDecimal.ZERO;
        BigDecimal xxTotAmt = xxTotBaseAmt.add(xxTotFrtAmt).add(xxTotTaxAmt);

        imptOrd.get(0).put("XX_TOT_BASE_AMT_LN", xxTotBaseAmt_LN);
        imptOrd.get(0).put("XX_TOT_BASE_AMT_MT", xxTotBaseAmt_MT);
        imptOrd.get(0).put("XX_TOT_BASE_AMT_RT", xxTotBaseAmt_RT);
        imptOrd.get(0).put("XX_TOT_BASE_AMT", xxTotBaseAmt);
        imptOrd.get(0).put("XX_TOT_FRT_AMT", xxTotFrtAmt);
        imptOrd.get(0).put("XX_TOT_TAX_AMT", xxTotTaxAmt);
        imptOrd.get(0).put("XX_TOT_AMT", xxTotAmt);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt_LN, xxTotBaseAmt_LN);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt_MT, xxTotBaseAmt_MT);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt_RT, xxTotBaseAmt_RT);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotBaseAmt, xxTotBaseAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotFrtAmt, xxTotFrtAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt, xxTotTaxAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, xxTotAmt);
        // 2017/08/30 S21_NA#20792 Add End

        // location address
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, (String) imptOrd.get(0).get("BILL_TO_CUST_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, (String) imptOrd.get(0).get("SHIP_TO_CUST_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, (String) imptOrd.get(0).get("SOLD_TO_CUST_LOC_ADDR"));
        // 2017/01/26 S21_NA#17119 Add Start
        if (!ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm) && imptOrd.get(0).get("BILL_TO_ACCT_NM") != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, (String) imptOrd.get(0).get("BILL_TO_ACCT_NM"));
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm) && imptOrd.get(0).get("SHIP_TO_ACCT_NM") != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, (String) imptOrd.get(0).get("SHIP_TO_ACCT_NM"));
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && imptOrd.get(0).get("SLD_TO_ACCT_NM") != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, (String) imptOrd.get(0).get("SLD_TO_ACCT_NM"));
        }
        // 2017/01/26 S21_NA#17119 Add End

        // interface creation date
        String cratDt = bizMsg.ordSrcImptTs.getValue();
        if (cratDt.length() >= 8) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxCratDt, NWAL2200CommonLogic.leftString(cratDt, bizMsg, "xxCratDt"));
        }

        // sales rep
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, NWAL2200CommonLogic.leftString(String.format("%s : %s", bizMsg.coaBrCd.getValue(), bizMsg.coaBrDescTxt.getValue()), bizMsg, "xxScrItem54Txt_CB"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, NWAL2200CommonLogic.leftString(String.format("%s : %s", bizMsg.coaExtnCd.getValue(), bizMsg.coaExtnDescTxt.getValue()), bizMsg, "xxScrItem54Txt_CB"));

        setShpgSvcLvlPullDown(bizMsg); // QC#13688 2017/02/24 Add
        /**************************************************
         * IMPT_ORD_CONFIG
         **************************************************/
        index = 0;
        for (Map<String, Object> dbResult : imptOrdConfig) {
            NWAL2200CommonLogic.setDbResult(glblMsg.A.no(index), "LC", dbResult);
            // Add Start 2017/09/19 QC#21118
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).xxAllLineAddr_LB, dbResult.get("BILL_TO_CUST_LOC_ADDR").toString());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(index).xxAllLineAddr_LS, dbResult.get("SHIP_TO_CUST_LOC_ADD").toString());
            // Add End 2017/09/19 QC#21118
            index++;
            if (index >= glblMsg.A.length()) {
                break;
            }
        }
        glblMsg.A.setValidCount(index);
        // EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);

        index = 0;
        for (Map<String, Object> dbResult : imptOrdRtrnConfig) {
            NWAL2200CommonLogic.setDbResult(glblMsg.C.no(index), "RC", dbResult);
            // Add Start 2017/09/19 QC#21118
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(index).xxAllLineAddr_RB, dbResult.get("BILL_TO_CUST_LOC_ADDR").toString());
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(index).xxAllLineAddr_RS, dbResult.get("SHIP_TO_CUST_LOC_ADD").toString());
            // Add End 2017/09/19 QC#21118
            index++;
            if (index >= glblMsg.C.length()) {
                break;
            }
        }
        glblMsg.C.setValidCount(index);
        //EZDMsg.copy(glblMsg.C, null, bizMsg.C, null);

        // 2017/11/28 S21_NA#22782 add start
        // Search OrigCpoNum from Config
     // 2018/03/15 QC#20153 Mod Start
//        if (!ZYPCommonFunc.hasValue(cpoOrdNum) && IMPT_STS.SUCCESS.equals(bizMsg.imptStsCd.getValue()) && bizMsg.A.getValidCount() + bizMsg.C.getValidCount() > 0) {
//        	S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getOrigCpoNumFromConfig(bizMsg);
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) && IMPT_STS.SUCCESS.equals(bizMsg.imptStsCd.getValue()) && glblMsg.A.getValidCount() + glblMsg.C.getValidCount() > 0) {
        	S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getOrigCpoNumFromConfig(bizMsg.glblCmpyCd.getValue(), glblMsg);
        	// 2018/03/15 QC#20153 Mod End
            if (ssmResult != null && !ssmResult.isCodeNotFound()) {
                List<String> result = (List<String>) ssmResult.getResultObject();
                if (result.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, result.get(0));
                }
            // 2019/03/25 S21_NA#30924 Add Start
            } else if (ZYPCommonFunc.hasValue((String)imptOrd.get(0).get("CPO_ORD_NUM"))) {
                ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum, (String)imptOrd.get(0).get("CPO_ORD_NUM"));
            }
            // 2019/03/25 S21_NA#30924 Add Ends
        }
        // 2017/11/28 S21_NA#22782 add end

        /**************************************************
         * IMPT_ORD_DTL, IMPT_ORD_RTRN_DTL
         **************************************************/
        index = 0;
        for (Map<String, Object> dbResult : imptOrdDtl) {
            NWAL2200CommonLogic.setDbResult(glblMsg.B.no(index), "LL", dbResult);
            index++;
            if (index >= glblMsg.J.length()) {
                break;
            }
        }
        glblMsg.B.setValidCount(index);
        //EZDMsg.copy(glblMsg.J, null, bizMsg.B, null);
        numberingDsCpoLineNum(glblMsg.B);
        EZDMsg.copy(glblMsg.B, null, glblMsg.J, null);
        NWAL2200CommonLogic.loadOnePageToCMsgForOutbound(glblMsg, bizMsg, 1);

        index = 0;
        for (Map<String, Object> dbResult : imptOrdRtrnDtl) {
            NWAL2200CommonLogic.setDbResult(glblMsg.D.no(index), "RL", dbResult);
            index++;
            if (index >= glblMsg.K.length()) {
                break;
            }
        }
        glblMsg.D.setValidCount(index);
        //EZDMsg.copy(glblMsg.K, null, bizMsg.D, null);
        EZDMsg.copy(glblMsg.D, null, glblMsg.K, null);
        numberingDsCpoLineNum(glblMsg.D);
        NWAL2200CommonLogic.loadOnePageToCMsgForInbound(glblMsg, bizMsg, 1);

        // line category
        createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        createLineCatgPulldownForRma(bizMsg, bizMsg.slsDt.getValue());

        /**************************************************
         * DS_IMPT_ORD_SLS_CR
         **************************************************/
        index = 0;
        for (Map<String, Object> dbResult : imptOrdSlsCrHeader) {
            NWAL2200CommonLogic.setDbResult(glblMsg.F.no(index), "FS", dbResult);
            index++;
            if (index >= glblMsg.F.length()) {
                break;
            }
        }
        glblMsg.F.setValidCount(index);
        EZDMsg.copy(glblMsg.F, null, bizMsg.F, null);

        // 2018/01/23 QC#18798 Add Start
        List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            NWAL2200_FCMsg slsCrMsg = bizMsg.F.no(i);

            String fstBizCtxAttbTxt = NWAL2200Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                if (fstBizCtxAttbTxt.equals(slsCrMsg.slsRepRoleTpCd_FS.getValue()) && ZYPConstant.FLG_OFF_N.equals(slsCrMsg.slsCrQuotFlg_FS.getValue())) {

                    List<Map<String, Object>> slsRepList = NWAL2200CommonLogic.getSalesRepList(bizMsg.glblCmpyCd.getValue(), slsCrMsg.slsRepTocCd_FS.getValue());
                    if (slsRepList != null && slsRepList.size() > 0) {
                        for (Map<String, Object> slsRepMap : slsRepList) {
                            dummyRepList.add(slsRepMap);
                        }
                    }
                }
            }
        }
        NWAL2200CommonLogic.clearGLSegment(bizMsg);
        if (dummyRepList != null && dummyRepList.size() > 0) {
            NWAL2200CommonLogic.setGLSegment(bizMsg, dummyRepList.get(0));
        }
        // 2018/01/23 QC#18798 Add End

        index = 0;
        for (Map<String, Object> dbResult : imptOrdSlsCrOutBound) {
            NWAL2200CommonLogic.setDbResult(glblMsg.G.no(index), "GS", dbResult);
            index++;
            if (index >= glblMsg.G.length()) {
                break;
            }
        }
        glblMsg.G.setValidCount(index);
        EZDMsg.copy(glblMsg.G, null, bizMsg.G, null);

        index = 0;
        for (Map<String, Object> dbResult : imptOrdSlsCrInbound) {
            NWAL2200CommonLogic.setDbResult(glblMsg.H.no(index), "HS", dbResult);
            index++;
            if (index >= glblMsg.H.length()) {
                break;
            }
        }
        glblMsg.H.setValidCount(index);
        EZDMsg.copy(glblMsg.H, null, bizMsg.H, null);

        /**************************************************
         * DS_IMPT_PRC_CALC_BASE, DS_IMPT_RTRN_PRC_CALC
         **************************************************/
        index = 0;
        // QC#26909 mod start
        xxTotFrtAmt = BigDecimal.ZERO;
        List<String> prcDtlGrpList = S21StringUtil.toList(ZYPCodeDataUtil.getVarCharConstValue("PRC_DTL_GRP_FOR_CHARGE", getGlobalCompanyCode()));
        for (Map<String, Object> dbResult : imptPrcCalcBase) {
            NWAL2200CommonLogic.setDbResult(glblMsg.I.no(index), "LP", dbResult);
            index++;
            if (index >= glblMsg.I.length()) {
                break;
            }
            String prcDtlGrpCd = (String) dbResult.get("PRC_DTL_GRP_CD");
            if (prcDtlGrpList.contains(prcDtlGrpCd)) {
                xxTotFrtAmt = xxTotFrtAmt.add((BigDecimal) dbResult.get("CALC_PRC_AMT_RATE"));
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotFrtAmt, xxTotFrtAmt);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt, xxTotAmt.add(xxTotFrtAmt));
        // QC#26909 mod end

        glblMsg.I.setValidCount(index);
        //EZDMsg.copy(glblMsg.I, null, bizMsg.I, null);

        /**************************************************
         * DS_IMPT_ORD_ERR
         **************************************************/
        index = 0;
        for (Map<String, Object> dbResult : imptOrdErr) {
            NWAL2200CommonLogic.setDbResult(glblMsg.E.no(index), "EL", dbResult);
            index++;
            if (index >= glblMsg.E.length()) {
                break;
            }
        }
        glblMsg.E.setValidCount(index);
        //EZDMsg.copy(glblMsg.E, null, bizMsg.E, null);
        // numbering line
        numberingErrorLineNum(glblMsg.E, glblMsg.B, glblMsg.D);
        NWAL2200CommonLogic.loadOnePageToCMsgForError(glblMsg, bizMsg, 0);

        // header error
        String headerError = getHeaderError(bizMsg.E);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDsMultMsgDplyTxt, NWAL2200CommonLogic.leftString(headerError, bizMsg, "xxDsMultMsgDplyTxt"));

        // 2018/01/23 QC#18798 Add Start
        getAllOrdCatgBizCtx(bizMsg);
        // 2018/01/23 QC#18798 Add End

        // UPDATE START 2016/09/14 Unit Test#202
        if (CPO_SRC_TP.SOM.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.PENDING_OM_REVIEW.equals(bizMsg.imptStsCd.getValue())) {
            // QC#14929
            // String messageId =
            // NWXC412001SomWebService.review(bizMsg.ordSrcRefNum.getValue(),
            // bizMsg.slsRepPsnNum.getValue(), null);
            String messageId = NWXC412001SomWebService.review(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue(), " ");

            if (S21StringUtil.isNotEmpty(messageId)) {
                // QC#14929
                // bizMsg.setMessageInfo(messageId);
                bizMsg.setMessageInfo(messageId, new String[] {bizMsg.ordSrcRefNum.getValue() });
                return;
            }
        }
        // UPDATE START 2016/09/14 Unit Test#202

        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(bizMsg.cpoSrcTpCd.getValue()) && IMPT_STS.PENDING_OM_REVIEW.equals(bizMsg.imptStsCd.getValue())) {
            String messageId = NWXC412001SomWebService.review(bizMsg.ordSrcRefNum.getValue(), bizMsg.slsRepPsnNum.getValue(), "");

            if (S21StringUtil.isNotEmpty(messageId)) {
                bizMsg.setMessageInfo(messageId, new String[] {"Submitted to OM"});
                return;
            }
        }
        // 2018/01/23 QC#18798 Add End

    }

    /**
     * Check Input Category
     * @param bizMsg NWAL2200CMsg
     * @return check OK : true
     */
    private static boolean checkCatg(NWAL2200CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsOrdCatgCdList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Category" });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        @SuppressWarnings("unchecked")
        List<Map<String, String>> dsOrdCatgCdList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (dsOrdCatgCdList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_CD"));

        ssmResult = NWAL2200Query.getInstance().getDsOrdTpCd(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Category" });
            return false;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgDescTxt, dsOrdCatgCdList.get(0).get("DS_ORD_CATG_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, (String) ssmResult.getResultObject());

        return true;
    }

    /**
     * create Reason Code PullDown
     * @param bizMsg NWAL2200CMsg
     */
    private static void createRsnCdPulldown(NWAL2200CMsg bizMsg) {

        bizMsg.dsOrdTpCd_CD.clear();
        bizMsg.dsOrdTpDescTxt_NM.clear();

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsOrdTpList(bizMsg);

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_CD.no(i), resultMap.get("DS_ORD_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpDescTxt_NM.no(i), resultMap.get("DS_ORD_TP_DESC_TXT"));
            }
        }
    }

    /**
     * create Sub Reason Code PullDown
     * @param bizMsg NWAL2200CMsg
     * @param effDt Effective Date (YYYYMMDD)
     */
    private static void createSubRsnCdPulldown(NWAL2200CMsg bizMsg, String effDt) {

        bizMsg.dsOrdRsnCd_CD.clear();
        bizMsg.dsOrdRsnDescTxt_NM.clear();

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsOrdRsnList(bizMsg, effDt);

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnCd_CD.no(i), resultMap.get("DS_ORD_RSN_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdRsnDescTxt_NM.no(i), resultMap.get("DS_ORD_RSN_DESC_TXT"));
            }
        }
    }

    /**
     * Set Initial Value For Line Category Pulldown
     * @param bizMsg NWAL2200CMsg
     * @param primaryLineCatgCd Primary Line Category Code
     * @param primaryLineCatgRmaCd Primary Line Category Code For RMA
     */
    private static void setInitValueForLineCatgPulldown(NWAL2200CMsg bizMsg, String primaryLineCatgCd, String primaryLineCatgRmaCd) {

        if (ZYPCommonFunc.hasValue(primaryLineCatgCd)) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsOrdLineCatgCd_LL, primaryLineCatgCd);
            }
        }

        if (ZYPCommonFunc.hasValue(primaryLineCatgRmaCd)) {
            for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).dsCpoLineCatgCd_RL, primaryLineCatgRmaCd);
            }
        }
    }

    /**
     * create Line Category PullDown
     * @param bizMsg NWAL2200CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Primary Line Category Code
     */
    private static String createLineCatgPulldown(NWAL2200CMsg bizMsg, String effDt) {

        bizMsg.dsOrdLineCatgCd_CD.clear();
        bizMsg.dsOrdLineCatgDescTxt_NM.clear();

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsOrdLineCatgList(bizMsg, effDt, DS_ORD_LINE_DRCTN.OUTBOUND);
        String primaryLineCatg = null;

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_CD.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgDescTxt_NM.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));

                if (!ZYPCommonFunc.hasValue(primaryLineCatg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(resultMap.get("PRIM_LINE_CATG_FLG"))) {
                        primaryLineCatg = resultMap.get("DS_ORD_LINE_CATG_CD");
                    }
                }
            }
        }

        return primaryLineCatg;
    }

    /**
     * create Line Category PullDown For RMA
     * @param bizMsg NWAL2200CMsg
     * @param effDt Effective Date (YYYYMMDD)
     * @return Primary Line Category Code
     */
    private static String createLineCatgPulldownForRma(NWAL2200CMsg bizMsg, String effDt) {

        bizMsg.dsOrdLineCatgCd_CR.clear();
        bizMsg.dsOrdLineCatgDescTxt_NR.clear();

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsOrdLineCatgList(bizMsg, effDt, DS_ORD_LINE_DRCTN.INBOUND);
        String primaryLineCatg = null;

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_CR.no(i), resultMap.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgDescTxt_NR.no(i), resultMap.get("DS_ORD_LINE_CATG_DESC_TXT"));

                if (!ZYPCommonFunc.hasValue(primaryLineCatg)) {
                    if (ZYPConstant.FLG_ON_Y.equals(resultMap.get("PRIM_LINE_CATG_FLG"))) {
                        primaryLineCatg = resultMap.get("DS_ORD_LINE_CATG_CD");
                    }
                }
            }
        }

        return primaryLineCatg;
    }

    /**
     * Derive Default Data
     * @param bizMsg NWAL2200CMsg
     * @param slsDt Sales Date
     */
    private static void deriveDefaultData(NWAL2200CMsg bizMsg, String slsDt) {

        deriveDefaultCustAddl(bizMsg, slsDt);
    }

    /**
     * Derive Default Customer And Additional Data
     * @param bizMsg NWAL2200CMsg
     * @param slsDt Sales Date
     */
    private static void deriveDefaultCustAddl(NWAL2200CMsg bizMsg, String slsDt) {

        bizMsg.frtCondDescTxt.clear();
        bizMsg.carrSvcLvlDescTxt.clear();
        bizMsg.shpgSvcLvlCd.clear();
        // bizMsg.prcCatgNm.clear();
        // bizMsg.flPrcListDescTxt.clear();
        // for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
        // bizMsg.B.no(i).prcCatgNm_LL.clear();
        // bizMsg.B.no(i).flPrcListDescTxt_LL.clear();
        // }
        // for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
        // bizMsg.D.no(i).prcCatgNm_RL.clear();
        // bizMsg.D.no(i).flPrcListDescTxt_RL.clear();
        // }

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCustAddlInfo(bizMsg, slsDt);

        if (ssmResult.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd, resultMap.get("LINE_BIZ_TP_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, resultMap.get("FRT_COND_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, resultMap.get("CARR_SVC_LVL_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd, resultMap.get("DEF_SHPG_SVC_LVL_CD"));

            // String prcList = resultMap.get("PRC_CATG_NM");
            // ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgNm,
            // prcList);
            // ZYPEZDItemValueSetter.setValue(bizMsg.flPrcListDescTxt,
            // prcList);
            // for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            // ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).prcCatgNm_LL,
            // prcList);
            // ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).flPrcListDescTxt_LL,
            // prcList);
            // }
            // for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).prcCatgNm_RL,
            // prcList);
            // ZYPEZDItemValueSetter.setValue(bizMsg.D.no(i).flPrcListDescTxt_RL,
            // prcList);
            // }
        }
    }

    private List<Map<String, Object>> getImptOrd(String glblCmpyCd, String ordSrcRefNum, BigDecimal dsImptOrdPk) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("ordSrcRefNum", ordSrcRefNum);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdViewInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getImptOrdConfig(String glblCmpyCd, BigDecimal dsImptOrdPk, String configCatgCd, int rownum) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("configCatgCd", configCatgCd);
        queryParam.put("rowNum", rownum);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdConfigInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getImptOrdDtl(String glblCmpyCd, BigDecimal dsImptOrdPk, int rownum) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("rowNum", rownum);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdDtlInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getImptOrdRtrnDtl(String glblCmpyCd, BigDecimal dsImptOrdPk, int rownum) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("rowNum", rownum);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdRtrnDtlInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getImptOrdSlsCr(String glblCmpyCd, BigDecimal dsImptOrdPk, String configCatgCd, int rownum) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("configCatgCd", configCatgCd);
        queryParam.put("rowNum", rownum);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdSlsCrInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getImptPrcCalcBase(String glblCmpyCd, BigDecimal dsImptOrdPk, int rownum) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("rowNum", rownum);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptPrcCalcBaseInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    private List<Map<String, Object>> getImptOrdErr(String glblCmpyCd, BigDecimal dsImptOrdPk, int rownum) {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        queryParam.put("rowNum", rownum);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getDsImptOrdErrInfo(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return resultList;
        }
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    /**
     * Get DS Account Customer Info
     * @param bizMsg NWAL2200CMsg
     * @param isCallNameFld Called Name Field
     * @param targetItem Target Item
     * @param msgParm Message Parameter
     * @return DS Account Customer Info
     */
    private static SELL_TO_CUSTTMsg getDsAcctCustInfo(NWAL2200CMsg bizMsg, boolean isCallNameFld, EZDCStringItem targetItem, String msgParm) {

        SELL_TO_CUSTTMsgArray dsAcctCustTMsgArray = null;
        if (isCallNameFld) {
            dsAcctCustTMsgArray = getDsSlsAcctCustForName(bizMsg, targetItem.getValue());
        } else {
            dsAcctCustTMsgArray = getDsSlsAcctCustForAcct(bizMsg, targetItem.getValue());
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (dsAcctCustTMsgArray.getValidCount() == 0) {
            targetItem.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        } else if (dsAcctCustTMsgArray.getValidCount() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2018/11/19 QC#28683
            NWAL2200CommonLogic.setParamForSpecialInstruction(bizMsg);
            // Add End 2018/11/19 QC#28683
            return null;
        }

        return dsAcctCustTMsgArray.no(0);
    }

    /**
     * Get Direct Sales Account Customer For Name
     * @param bizMsg NWAL2200CMsg
     * @param custNm Customer Name
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForName(NWAL2200CMsg bizMsg, String custNm) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("502");
        condition.setSQLID("506");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("dsAcctNm01", custNm);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Direct Sales Account Customer For Account
     * @param bizMsg NWAL2200CMsg
     * @param acctNum Account Number
     * @return Direct Sales Account Customer
     */
    private static SELL_TO_CUSTTMsgArray getDsSlsAcctCustForAcct(NWAL2200CMsg bizMsg, String acctNum) {

        final SELL_TO_CUSTTMsg condition = new SELL_TO_CUSTTMsg();
        // 2018/03/12 S21_NA#24090 Mod Start
        // condition.setSQLID("503");
        condition.setSQLID("507");
        // 2018/03/12 S21_NA#24090 Mod End
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        // Mod Start 2017/09/13 QC#21065
//        condition.setConditionValue("dsAcctNum01", acctNum);
        condition.setConditionValue("sellToCustCd01", acctNum);
        // Mod End 2017/09/13 QC#21065
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Common Process For Derive From Bill To
     * @param bizMsg NWAL2200CMsg
     * @param glblMsg NWAL2200SMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     */
    // 2019/02/08 QC#30237 Mod Start
    //private static void cmnProcForDeriveFromBillTo(NWAL2200CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg) {
    private static void cmnProcForDeriveFromBillTo(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg) {
    // 2019/02/08 QC#30237 Mod End
        // 2019/02/01 Mod Start S21_NA#30097

//        // Del Start 2018/07/25 S21_NA#14307
//        // 2016/09/29 S21_NA#8659 Mod Start
//        // NMZC610001PMsg nMZC610001PMsg =
//        // callCustInfoGetApiForDefaultMode(bizMsg,
//        // dsAcctCustTMsg.dsAcctNum.getValue());
//        // Add Start 2018/07/25 S21_NA#14307
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), null, bizMsg.billToCustCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
//        // 2016/09/29 S21_NA#8659 Mod End
//        // Del End 2018/07/25 S21_NA#14307

        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, dsAcctCustTMsg.sellToCustCd.getValue(), null, bizMsg.billToCustCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
        // 2019/02/01 Mod End S21_NA#30097
        if (nMZC610001PMsg == null) {
            return;
        }

        setBillToInfo(bizMsg, dsAcctCustTMsg, nMZC610001PMsg);
        deriveDefaultPmtTerm(bizMsg);
        // 2019/02/08 QC#30237 Mod Start
        //deriveDefaultBillToLocForDtlFld(bizMsg);
        deriveDefaultBillToLocForDtlFld(bizMsg, glblMsg);
        // 2019/02/08 QC#30237 Mod End
    }

    // 2016/09/29 S21_NA#8659 Add Start
    /**
     * <pre>
     * Call Customer Information Get API For Default Mode
     * </pre>
     * @param bizMsg Business Message
     * @param dsAcctNum Direct Sales Account Number
     * @param shipToCustCd Ship to Customer Code (if you set bill to,
     * this parameter should be null)
     * @param billToCustCd Bill To Customer Code (if you set ship to,
     * this parameter should be null)
     * @param xxMode API calling mode
     * @param xxSelFlg Select Flag
     * @return NMZC610001PMsg Default Customer API return value
     */
    // 2019/02/01 Mod Start S21_NA#30097 
//    private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL2200CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode) {
        private static NMZC610001PMsg callCustInfoGetApiForDefaultMode(NWAL2200CMsg bizMsg, String dsAcctNum, String shipToCustCd, String billToCustCd, String xxMode, String xxSelFlg) {
    // 2019/02/01 Mod End S21_NA#30097

        NWXC150001DefaultCustomerBean paramBean = new NWXC150001DefaultCustomerBean();
        paramBean.setGlblCmpyCd(bizMsg.glblCmpyCd.getValue());
        paramBean.setDsTrxRuleTpCd(getDsTrxRuleTpCd(bizMsg));
        paramBean.setDsAcctNum(dsAcctNum);

        // 2019/02/01 Mod Start S21_NA#30097 

//        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
//            // Mod Start 2018/07/25 S21_NA#14307
//            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
//                paramBean.setShipToCustCd(shipToCustCd);
//            }
//            //paramBean.setShipToCustCd(shipToCustCd);
//            // Mod End 2018/07/25 S21_NA#14307
//        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
//            // Mod Start 2018/07/25 S21_NA#14307
//            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, xxMode)) {
//                paramBean.setBillToCustCd(billToCustCd);
//            }
//            //paramBean.setBillToCustCd(billToCustCd);
//            // Mod End 2018/07/25 S21_NA#14307
//        }
        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            paramBean.setShipToCustCd(shipToCustCd);
        } else if (ZYPCommonFunc.hasValue(billToCustCd)) {
            if (!S21StringUtil.isEquals(NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, xxMode)) {
                paramBean.setBillToCustCd(billToCustCd);
            }
        }
        // 2019/02/01 Mod End S21_NA#30097 

        paramBean.setXxMode(xxMode);
        // 2019/02/01 Add Start S21_NA#30097
        paramBean.setXxSelFlg(xxSelFlg);
        // 2019/02/01 Add End S21_NA#30097
        paramBean.setOnBatchType(ONBATCH_TYPE.ONLINE);

        NMZC610001PMsg pMsg = NWXC150001DefaultCustomer.getDefaultCustomerData(paramBean);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return pMsg;
    }

    // 2016/09/29 S21_NA#8659 Add End

    /**
     * Get Direct Sales Transaction Rule Type Code
     * @param bizMsg NWAL2200CMsg
     * @return Direct Sales Transaction Rule Type Code
     */
    private static String getDsTrxRuleTpCd(NWAL2200CMsg bizMsg) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("003");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());
        condition.setConditionValue("dsOrdTpCd01", bizMsg.dsOrdTpCd.getValue());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01", bizMsg.dsOrdCatgCd.getValue());

        tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).firstBizCtxAttrbTxt.getValue();
        }

        return "";
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL2200CMsg
     * @param dsAcctCustTMsg SELL_TO_CUSTTMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setBillToInfo(NWAL2200CMsg bizMsg, SELL_TO_CUSTTMsg dsAcctCustTMsg, NMZC610001PMsg nMZC610001PMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, dsAcctCustTMsg.dsAcctNm);

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, defBillToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, (String) ssmResult.getResultObject());
    }

    /**
     * Derive Default Payment Term Data
     * @param bizMsg NWAL2200CMsg
     */
    private static void deriveDefaultPmtTerm(NWAL2200CMsg bizMsg) {

        bizMsg.pmtTermCashDiscDescTxt.clear();
        String pmtTermCashDiscCd = getPmtTermCashDiscCd(bizMsg);

        if (ZYPCommonFunc.hasValue(pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);
            tMsg = (PMT_TERM_CASH_DISCTMsg) EZDTBLAccessor.findByKey(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscDescTxt, tMsg.pmtTermCashDiscDescTxt);
                // 2018/02/06 S21_NA#23926 Add Start
                if(ZYPConstant.FLG_ON_Y.equals(tMsg.pmtCcFlg.getValue())){
                    ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd, DS_PMT_METH.CREDIT_CARD);
                }
                // 2018/02/06 S21_NA#23926 Add End
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue()));         // QC#17474 2017/02/21 Add
    }

    /**
     * Get Payment Term Cash Discount Code
     * @param bizMsg NWAL2200CMsg
     * @return Payment Term Cash Discount Code
     */
    private static String getPmtTermCashDiscCd(NWAL2200CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getPmtTermCashDiscCd(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, bizMsg.billToCustAcctCd);
            tMsg = (DS_ACCT_CR_PRFLTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg == null) {
                return null;
            }
            return tMsg.pmtTermCashDiscCd.getValue();
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * Derive Default Bill To Location For Detail Detail Fields
     * @param bizMsg NWAL2200CMsg
     */
    private static void deriveDefaultBillToLocForDtlFld(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // 2019/02/08 QC#30237 Mod Start
        //for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
        //    // Add Start 2017/07/18 QC#19937
        //    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
        //    // Add End 2017/07/18 QC#19937
        //    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustLocCd_LC, bizMsg.billToCustCd);
        //    // Add Start 2017/09/19 QC#21118
        //    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxAllLineAddr_LB, cmbnAddr(bizMsg));
        //    // Add Start 2017/09/19 QC#21118
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustAcctCd_LC, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).billToCustLocCd_LC, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxAllLineAddr_LB, bizMsg.xxAllLineAddr_BT);
        }
        // 2019/02/08 QC#30237 Mod End

        // 2019/02/08 QC#30237 Mod Start
        //for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
        //    // Add Start 2017/07/18 QC#19937
        //    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
        //    // Add End 2017/07/18 QC#19937
        //    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustLocCd_RC, bizMsg.billToCustCd);
        //    // Add Start 2017/09/19 QC#21118
        //    ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).xxAllLineAddr_RB, cmbnAddr(bizMsg));
        //    // Add Start 2017/09/19 QC#21118
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustAcctCd_RC, bizMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).billToCustLocCd_RC, bizMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).xxAllLineAddr_RB, bizMsg.xxAllLineAddr_BT);
        }
        // 2019/02/08 QC#30237 Mod End
    }

    // 2019/02/08 QC#30237 Add Start
    /**
     * Derive Default Ship To Location For Detail Detail Fields
     * @param bizMsg NWAL2200CMsg
     */
    private static void deriveDefaultShipToLocForDtlFld(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustAcctCd_LC, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustLocCd_LC, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxAllLineAddr_LS, bizMsg.xxAllLineAddr_SH);
        }

        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCustAcctCd_RC, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).shipToCustLocCd_RC, bizMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(glblMsg.C.no(i).xxAllLineAddr_RS, bizMsg.xxAllLineAddr_SH);
        }
    }
    // 2019/02/08 QC#30237 Add End

    /**
     * Get Bill To Info
     * @param bizMsg NWAL2200CMsg
     * @param custLocNum Customer Location Number
     * @param msgParm Message Parameter
     * @return Bill To Info
     */
    private static Map<String, String> getBillToInfo(NWAL2200CMsg bizMsg, EZDCStringItem custLocNum, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustInfoList(bizMsg, custLocNum.getValue());

        if (ssmResult.isCodeNotFound()) {
            custLocNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            return null;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (billToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2018/11/19 QC#28683
            NWAL2200CommonLogic.setParamForSpecialInstruction(bizMsg);
            // Add End 2018/11/19 QC#28683
            return null;
        }

        return billToCustInfoList.get(0);
    }

    /**
     * Get Ship To Info
     * @param bizMsg NWAL2200CMsg
     * @return Ship To Info
     */
    private static Map<String, String> getShipToInfo(NWAL2200CMsg bizMsg) {

        // Mod Start 2017/09/15 QC#21118
        String glblCmpyCd = bizMsg.glblCmpyCd.getValue();
        String dplyTab = bizMsg.xxDplyTab.getValue();
        String shipToCustCd = null;
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            shipToCustCd = bizMsg.A.no(idx).shipToCustLocCd_LC.getValue();
        } else if(TAB_RMA.equals(dplyTab)) {
            shipToCustCd = bizMsg.C.no(idx).shipToCustLocCd_RC.getValue();
        } else {
            shipToCustCd = bizMsg.shipToCustCd.getValue();
        }
//        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getShipToCustInfoList(bizMsg);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getShipToCustInfoList(glblCmpyCd, shipToCustCd);

        if (ssmResult.isCodeNotFound()) {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                bizMsg.A.no(idx).shipToCustLocCd_LC.setErrorInfo(1, NWAM0181E, new String[] {"Ship To Location" });
            } else if(TAB_RMA.equals(dplyTab)) {
                bizMsg.C.no(idx).shipToCustLocCd_RC.setErrorInfo(1, NWAM0181E, new String[] {"Ship To Location" });
            } else {
                bizMsg.shipToCustCd.setErrorInfo(1, NWAM0181E, new String[] {"Ship To Location" });
            }
            return null;
        }
        // Mod End 2017/09/15 QC#21118

        @SuppressWarnings("unchecked")
        List<Map<String, String>> shipToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (shipToCustInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            // Add Start 2018/11/19 QC#28683
            NWAL2200CommonLogic.setParamForSpecialInstruction(bizMsg);
            // Add End 2018/11/19 QC#28683
            return null;
        }

        return shipToCustInfoList.get(0);
    }

    /**
     * Combine Customer Address
     * @param bizMsg NWAL2200CMsg
     * @return Customer Address
     */
    private static String cmbnAddr(NWAL2200CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.firstLineAddr)) {
            return null;
        }

        StringBuilder addr = new StringBuilder(bizMsg.firstLineAddr.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.scdLineAddr)) {
            addr.append(SPACE);
            addr.append(bizMsg.scdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.thirdLineAddr)) {
            addr.append(SPACE);
            addr.append(bizMsg.thirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.frthLineAddr)) {
            addr.append(SPACE);
            addr.append(bizMsg.frthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ctyAddr)) {
            addr.append(SPACE);
            addr.append(bizMsg.ctyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.stCd)) {
            addr.append(COMMA);
            addr.append(bizMsg.stCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.postCd)) {
            addr.append(SPACE);
            addr.append(bizMsg.postCd.getValue());
        }

        return addr.toString();
    }

    /**
     * Common Process For Derive From Ship To
     * @param bizMsg NWAL2200CMsg
     * @param glblMsg NWAL2200SMsg
     * @param isSetShipToFlg Set Ship To Flag
     * @param slctLine Select Line
     */
    private static void cmnProcForDeriveFromShipTo(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, boolean isSetShipToFlg, int slctLine) {

        // 2019/02/01 Mod Start S21_NA#30097

//        // 2016/09/29 S21_NA#8659 Mod Start
//        // NMZC610001PMsg nMZC610001PMsg =
//        // callCustInfoGetApiForDefaultMode(bizMsg,
//        // bizMsg.shipToCustAcctCd.getValue());
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO);
//        // 2016/09/29 S21_NA#8659 Mod End
//
//        if (nMZC610001PMsg == null) {
//            return;
//        }
//
//        if (isSetShipToFlg) {
//            setShipToInfo(bizMsg, nMZC610001PMsg);
//        }
        // 2019/02/08 QC#30237 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = slctLine;
        String shipToCustAcctCd = null;
        String shipToCustCd = null;
        NWAL2200_ACMsg configMsg = null;
        NWAL2200_CCMsg rmaConfigMsg = null;
        int bizMsgCnt = bizMsg.xxCellIdx.getValueInt();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            shipToCustAcctCd = bizMsg.A.no(slctIndex).shipToCustAcctCd_LC.getValue();
            shipToCustCd = bizMsg.A.no(slctIndex).shipToCustLocCd_LC.getValue();
            configMsg = bizMsg.A.no(slctIndex);
            slctIndex = NWAL2200CommonLogic.getDetectedDetailIndexOfGlobalMessage(configMsg, glblMsg);
        } else if (TAB_RMA.equals(dplyTab)) {
            shipToCustAcctCd = bizMsg.C.no(slctIndex).shipToCustAcctCd_RC.getValue();
            shipToCustCd = bizMsg.C.no(slctIndex).shipToCustLocCd_RC.getValue();
            rmaConfigMsg = bizMsg.C.no(slctIndex);
            slctIndex = NWAL2200CommonLogic.getDetectedDetailIndexOfGlobalMessage(rmaConfigMsg, glblMsg);
        } else {
            shipToCustAcctCd = bizMsg.shipToCustAcctCd.getValue();
            shipToCustCd = bizMsg.shipToCustCd.getValue();
        }
        // 2019/02/08 QC#30237 Add End

        if (isSetShipToFlg) {
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_OFF_N);
            if (nMZC610001PMsg == null) {
                return;
            }
            setShipToInfo(bizMsg, nMZC610001PMsg);
            // 2019/02/08 QC#30237 Add Start
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                shipToCustCd = bizMsg.A.no(slctIndex).shipToCustLocCd_LC.getValue();
            } else if (TAB_RMA.equals(dplyTab)) {
                shipToCustCd = bizMsg.C.no(slctIndex).shipToCustLocCd_RC.getValue();
            } else {
                shipToCustCd = bizMsg.shipToCustCd.getValue();
            }
            // 2019/02/08 QC#30237 Add End
        }
        // 2019/02/08 QC#30237 Mod Start
        //NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.shipToCustAcctCd.getValue(), bizMsg.shipToCustCd.getValue(), null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_ON_Y);
        // Del Start 2019/03/15 QC#30730
        //NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, shipToCustAcctCd, shipToCustCd, null, NWXC150001DefaultCustomer.MODE_SHIP_ACCT_TO_BILL_TO, ZYPConstant.FLG_ON_Y);
        // Del End 2019/03/15 QC#30730
        // 2019/02/08 QC#30237 Mod End
        // Del Start 2019/03/15 QC#30730
        //if (nMZC610001BSPMsg == null) {
        //    return;
        //}
        // Del End 2019/03/15 QC#30730
        // 2019/02/01 Mod End S21_NA#30097

        // 2019/02/01 Mod Start S21_NA#30097
//        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del Start 2019/03/15 QC#30730
        //String defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // Del End 2019/03/15 QC#30730
        // 2019/02/01 Mod End S21_NA#30097
        // Del Start 2019/03/15 QC#30730
        //setBillToInfo(bizMsg, defBillToCustCd);
        // Del End 2019/03/15 QC#30730

        // Add Start 2019/03/15 QC#30730
        boolean isSoldToSet = false;
        if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            isSoldToSet = true;
        }
        // Add End 2019/03/15 QC#30730

        // 2019/02/08 QC#30237 Mod Start
        if (TAB_HEADER.equals(dplyTab)) {
            // 2019/02/01 Mod Start S21_NA#30097
//          if (!setSoldToInfo(bizMsg)) {
            // Mod Start 2019/03/15 QC#30730
            //if (!setSoldToInfo(bizMsg, false, nMZC610001BSPMsg, ZYPConstant.FLG_ON_Y)) {
            if (!setSoldToInfo(bizMsg)) {
                // Mod End 2019/03/15 QC#30730
                // 2019/02/01 Mod End S21_NA#30097
                    return;
                }

            // Add Start 2019/03/15 QC#30730
            setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
            // Add End 2019/03/15 QC#30730

                if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
                    deriveDefaultPmtTerm(bizMsg);
                }

                if (!ZYPCommonFunc.hasValue(bizMsg.slsRepPsnNum) || !ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
                    if (!deriveDefaultSlsRep(bizMsg, glblMsg)) {
                        return;
                    }
                }
                if (!deriveDefaultCarrSvcLvl(bizMsg)) {
                    return;
                }

                // if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
                // deriveDefaultBillToLocForDtlFld(bizMsg);
                // }

                if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                    deriveDefaultShipToLocForDtlFld(bizMsg, glblMsg);
                }

                // 2019/02/15 QC#30308 Add Start
                if (!deriveDefaultPO(bizMsg)) {
                    return;
                }
                // 2019/02/15 QC#30308 Add End

                // if (slctLine == -1) {
                // if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) &&
                // ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
                // if (!deriveDefaultWh(bizMsg, glblMsg)) {
                // return;
                // }
                // }
                // }
        } else if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // Add Start 2019/03/15 QC#30730
            setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
            // Add End 2019/03/15 QC#30730

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(bizMsgCnt).shipToLocNm_LC)) {
                NWAL2200CommonLogic.copyConfigDataToSMsg(configMsg, glblMsg);
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            // Add Start 2019/03/15 QC#30730
            setBillToInfoForShipTo(bizMsg, shipToCustCd, isSoldToSet);
            // Add End 2019/03/15 QC#30730

            if (ZYPCommonFunc.hasValue(bizMsg.C.no(bizMsgCnt).shipToLocNm_RC)) {
                NWAL2200CommonLogic.copyRmaConfigDataToSMsg(rmaConfigMsg, glblMsg);
            }
        }
        // 2019/02/08 QC#30237 Mod End
    }

    /**
     * Set Ship To Info
     * @param bizMsg NWAL2200CMsg
     * @param nMZC610001PMsg NMZC610001PMsg
     */
    private static void setShipToInfo(NWAL2200CMsg bizMsg, NMZC610001PMsg nMZC610001PMsg) {

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return;
        }

        String defShipToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        if (!ZYPCommonFunc.hasValue(defShipToCustCd)) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getShipToCustAddr(bizMsg, defShipToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        Map<String, String> shipToCustAddrInfo = (Map<String, String>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToCustAddrInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToCustAddrInfo.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToCustAddrInfo.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToCustAddrInfo.get("SHIP_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToCustAddrInfo.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToCustAddrInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToCustAddrInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToCustAddrInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToCustAddrInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipTo01RefCmntTxt, shipToCustAddrInfo.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipTo02RefCmntTxt, shipToCustAddrInfo.get("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToCustAddrInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToCustAddrInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToCustAddrInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToCustAddrInfo.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToCustAddrInfo.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToCustAddrInfo.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL2200CMsg
     * @param defBillToCustCd Default Bill To Customer Code
     */
    private static void setBillToInfo(NWAL2200CMsg bizMsg, String defBillToCustCd) {

        if (!ZYPCommonFunc.hasValue(defBillToCustCd)) {
            return;
        // 2019/02/08 QC#30237 Del Start
        //} else if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
        //    return;
        // 2019/02/08 QC#30237 Del End
        }

        // 2019/02/08 QC#30237 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        int slctIndex = bizMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(slctIndex).billToCustLocCd_LC)) {
                return;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            if (ZYPCommonFunc.hasValue(bizMsg.C.no(slctIndex).billToCustLocCd_RC)) {
                return;
            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd)) {
                return;
            }
        }
        // 2019/02/08 QC#30237 Add End

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);

        if (ssmResult.isCodeNotFound()) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToCustInfo = billToCustInfoList.get(0);
        // 2019/02/08 QC#30237 Mod Start
        //ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToCustInfo.get("DS_ACCT_NUM"));
        //ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToCustInfo.get("DS_ACCT_NM"));
        //ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToCustInfo.get("BILL_TO_CUST_CD"));
        //ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL2200_ACMsg confMsg = bizMsg.A.no(slctIndex);
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustAcctCd_LC, billToCustInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(confMsg.billToCustLocCd_LC, billToCustInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(confMsg.xxAllLineAddr_LB, billToCustInfo.get("BILL_TO_ADDR"));
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL2200_CCMsg rmaConfMsg = bizMsg.C.no(slctIndex);
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustAcctCd_RC, billToCustInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.billToCustLocCd_RC, billToCustInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(rmaConfMsg.xxAllLineAddr_RB, billToCustInfo.get("BILL_TO_ADDR"));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToCustInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToCustInfo.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToCustInfo.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToCustInfo.get("BILL_TO_ADDR"));
        }
        // 2019/02/08 QC#30237 Mod End
    }

    // Add Start 2019/03/15 QC#30730
    /**
     * setBillToInfoForShipTo
     * @param bizMsg NWAL1500CMsg
     * @param shipToCustCd String
     * @param isSoldToSet boolean
     */
    private static void setBillToInfoForShipTo(NWAL2200CMsg bizMsg, String shipToCustCd, boolean isSoldToSet) {
        String defBillToCustCd = null;

        defBillToCustCd = NWXC150001DefaultCustomer.getRelatedBillTo(bizMsg.glblCmpyCd.getValue(), shipToCustCd);
        if (!isSoldToSet && !ZYPCommonFunc.hasValue(defBillToCustCd)) {
            NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, //
                    bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), //
                    NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);

            if (nMZC610001BSPMsg == null) {
                return;
            }

            defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        }

        setBillToInfo(bizMsg, defBillToCustCd);
    }

    /**
     * Set Ship To Info
     * @param bizMsg NWAL2200CMsg
     * @return has API error : false
     */
    private static boolean setSoldToInfo(NWAL2200CMsg bizMsg) {
        return setSoldToInfo(bizMsg, false, null, ZYPConstant.FLG_OFF_N);
    }
    // Add End 2019/03/15 QC#30730

    /**
     * Set Ship To Info
     * @param bizMsg NWAL2200CMsg
     * @return has API error : false
     */
    // 2019/02/01 Mod Start S21_NA#30097
//    private static boolean setSoldToInfo(NWAL2200CMsg bizMsg) {
    private static boolean setSoldToInfo(NWAL2200CMsg bizMsg, boolean soldToMode, NMZC610001PMsg nMZC610001PMsg, String xxSelFlg) {
    // 2019/02/01 Mod End S21_NA#30097

        if (!soldToMode) { // 2019/02/01 Add S21_NA#30097
            if (ZYPCommonFunc.hasValue(bizMsg.soldToCustAcctNm) && ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
                return true;
            } else if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd)) {
                return true;
            }
        } // 2019/02/01 Add S21_NA#30097

        if (!soldToMode) { // 2019/02/01 Add S21_NA#30097
            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, bizMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, bizMsg.shipToCustAcctNm);
        } // 2019/02/01 Add S21_NA#30097
        
        // 2019/02/01 Mod Start S21_NA#30097

//        // 2016/09/29 S21_NA#8659 Mod Start
//        // NMZC610001PMsg nMZC610001PMsg =
//        // callCustInfoGetApiForDefaultMode(bizMsg,
//        // bizMsg.sellToCustCd.getValue());
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
//        // 2016/09/29 S21_NA#8659 Mod End
        if (nMZC610001PMsg == null) {
            nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, xxSelFlg);
        }
        // 2019/02/01 Mod End S21_NA#30097

        if (nMZC610001PMsg == null) {
            return false;
        }

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return true;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2019/02/01 Mod Start S21_NA#30097
//        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustInfoList(bizMsg, defBillToCustCd);
        // 2019/02/01 Mod End S21_NA#30097
        if (ssmResult.isCodeNotFound()) {
            return true;
        }
        
        // 2019/02/01 Mod Start S21_NA#30097
//        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, defBillToCustCd);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, (String) ssmResult.getResultObject());
        List<Map<String, String>> billToCustInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        Map<String, String> billToAddrMap = billToCustInfoList.get(0);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, defBillToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToAddrMap.get("BILL_TO_ADDR"));

        // Del Start 2019/03/15 QC#30730
        //String sellToCustCd = billToAddrMap.get("DS_ACCT_NUM");
        //String soldToCustAcctNm = billToAddrMap.get("DS_ACCT_NM");
        //if (!S21StringUtil.isEquals(bizMsg.sellToCustCd.getValue(), sellToCustCd)) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, sellToCustCd);
        //    ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, soldToCustAcctNm);
        //}
        // Del End 2019/03/15 QC#30730
        // 2019/02/01 Mod End S21_NA#30097
        return true;
    }
    // 2019/02/01 Mod Start S21_NA#30097
//    private static boolean setSoldToInfoDeriveFromSoldTo(NWAL2200CMsg bizMsg) {
        private static boolean setSoldToInfoDeriveFromSoldTo(NWAL2200CMsg bizMsg, String xxSelFlg) {    
    // 2019/02/01 Mod End S21_NA#30097

        // 2019/02/01 Mod Start S21_NA#30097
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, xxSelFlg);
        // 2019/02/01 Mod End S21_NA#30097

        if (nMZC610001PMsg == null) {
            return false;
        }

        if (nMZC610001PMsg.DefaultBillShipList.getValidCount() == 0) {
            return true;
        }

        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustAddr(bizMsg, defBillToCustCd);
        if (ssmResult.isCodeNotFound()) {
            return true;
        }

        String addr = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, defBillToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, addr);

        return true;
    }
    
    /**
     * Derive Default Sales Rep Data
     * @param bizMsg Business Message
     * @param glblMsg global message
     * @return Succeed drive : true
     */
    private static boolean deriveDefaultSlsRep(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg) {

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bizMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, bizMsg.dsOrdTpCd); // S21_NA#15004
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd); // 2018/08/15 S21_NA#27771 Add
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2020/04/27 QC#56638 Add End
        // 2018/01/23 QC#18798 Add Start
        String resultFlg = NWAL2200Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.xxModeCd, XX_MODE_CD_EMSD);
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustAcctCd, bizMsg.shipToCustAcctCd);
        }
        // 2018/01/23 QC#18798 Add End

        if (!callDefSlsRepApi(bizMsg, nMZC260001PMsg)) {
            return false;
        }

        String curLineBizTpCd = bizMsg.lineBizTpCd.getValue();
        // 2017/03/14 S21_NA#16855 Add Start
        //String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(bizMsg); // 2017/12/14 S21_NA#19804(Sol#349) MOD 
        List<String> trtyGrpTpList = getTrtyGrpTpCdFromDsOrdTpCd(bizMsg);// 2017/12/14 S21_NA#19804(Sol#349) MOD
        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray TMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (TMsgArray != null && TMsgArray.length() > 0) {
            for (int i = 0; i < TMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = TMsgArray.no(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }
        // 2017/03/14 S21_NA#16855 Add End
        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        // 2018/01/23 QC#18798 Add Start
        List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
        // 2018/01/23 QC#18798 Add End
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
            
            // 2017/12/14 S21_NA#19804(Sol#349) MOD START 
            if((trtyGrpTpList != null && trtyGrpTpList.size() > 0 && trtyGrpTpList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))//
                    || (trtyGrpTpList == null || trtyGrpTpList.size() == 0) && (defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd))){
                matchLobSlsRepPMsgList.add(defSlsRepPMsg);
                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                    deriveDefaultSlsRepForHdr(bizMsg, defSlsRepPMsg.tocCd.getValue());
                }
            }

            // 2017/03/14 S21_NA#16855 Mod Start
//          if (defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd)) {
            //if (defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd) //
            //        && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
            //        || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            // 2017/03/14 S21_NA#16855 Mod End
                //matchLobSlsRepPMsgList.add(defSlsRepPMsg);

                //String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();

                // 2017/03/14 S21_NA#16855 Mod Start
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
                //if (targetWriterList.contains(lineBizRoleTpCd)) {
                // 2017/03/14 S21_NA#16855 Mod End
                //    deriveDefaultSlsRepForHdr(bizMsg, defSlsRepPMsg.tocCd.getValue());
                //}
            //}
            // 2017/12/14 S21_NA#19804(Sol#349) MOD END 

            // 2018/01/23 QC#18798 Add Start
            String fstBizCtxAttbTxt = NWAL2200Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                if (fstBizCtxAttbTxt.equals(defSlsRepPMsg.lineBizRoleTpCd.getValue())
                        && ZYPConstant.FLG_OFF_N.equals(defSlsRepPMsg.slsCrQuotFlg.getValue())) {

                    List<Map<String, Object>> slsRepList = getSalesRepList(bizMsg.glblCmpyCd.getValue(), defSlsRepPMsg.tocCd.getValue());
                    if (slsRepList != null && slsRepList.size() != 0) {
                        for (Map<String, Object> slsRepMap : slsRepList) {
                            dummyRepList.add(slsRepMap);
                        }
                    }
                }
            }
            // 2018/01/23 QC#18798 Add End
        }
        // 2018/01/23 QC#18798 Add Start
        NWAL2200CommonLogic.clearGLSegment(bizMsg);
        if (dummyRepList != null && dummyRepList.size() != 0) {
            NWAL2200CommonLogic.setGLSegment(bizMsg, dummyRepList.get(0));
        }
        // 2018/01/23 QC#18798 Add End
        if (matchLobSlsRepPMsgList.size() > 0) {
            // setDefaultSlsRep(bizMsg, matchLobSlsRepPMsgList);
        }

        return true;
    }

    /**
     * Call NMZC2600 Default Sales Rep API
     * @param bizMsg NWAL2200CMsg
     * @param pMsg NMZC260001PMsg
     * @return has API error : false
     */
    private static boolean callDefSlsRepApi(NWAL2200CMsg bizMsg, NMZC260001PMsg pMsg) {

        // call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                // 2020/04/27 QC#56638 Add Start
                if (msgId.endsWith("E")) {
                    bizMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                } else if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && msgId.endsWith(NWAM0757W)) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else if (ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith(NWAM0981W)) {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                boolean isShipBase = NWAL2200CommonLogic.isSlsReqDef(bizMsg);
                if (isShipBase && ZYPCommonFunc.hasValue(bizMsg.shipToCustCd) && msgId.endsWith("W")) {
                    bizMsg.setMessageInfo(NWAM0757W);
                } else if (!isShipBase && ZYPCommonFunc.hasValue(pMsg.billToCustCd) && msgId.endsWith("W")) {
                    bizMsg.setMessageInfo(NWAM0981W);
                }
                // 2020/04/27 QC#56638 Add End
            }
        }

        return true;
    }

    /**
     * Derive Default Sales Rep Data For Header
     * @param bizMsg NWAL2200CMsg
     * @param primRepTocCd Primary Rep TOC Code
     */
    @SuppressWarnings("unchecked")
    private static void deriveDefaultSlsRepForHdr(NWAL2200CMsg bizMsg, String primRepTocCd) {

        if (ZYPCommonFunc.hasValue(primRepTocCd)) {
            S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getPrimSlsRepInfo(bizMsg, primRepTocCd);

            if (ssmResult.isCodeNormal()) {
                Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, resultMap.get("TOC_NM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, primRepTocCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, resultMap.get("COA_BR_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, resultMap.get("COA_BR_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, resultMap.get("COA_EXTN_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, resultMap.get("COA_EXTN_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, resultMap.get("COA_BR_ITEM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, resultMap.get("COA_EXTN_ITEM"));
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepPsnNum, resultMap.get("PSN_NUM"));
            }
        }
    }

    /**
     * Get Freight Term Information
     * @param bizMsg NWAL2200CMsg
     * @return Freight Term Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getFreightTermInfo(NWAL2200CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getFreightTermInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Freight Term" });
            return null;
        }

        List<Map<String, String>> freightTermInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (freightTermInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return freightTermInfoList.get(0);
    }
    /**
     * <pre>
     * set Shipping Service Level Pull Down 
     * @param bizMsg NWAL2200CMsg
     */
    public static void setShpgSvcLvlPullDown(NWAL2200CMsg bizMsg) {
        S21SsmEZDResult resltShpgSvcLvlRec = NWAL2200Query.getInstance().getShpgSvcLvlDataList(bizMsg.glblCmpyCd.getValue(), bizMsg.lineBizTpCd.getValue(), bizMsg.frtCondCd.getValue());
        if (resltShpgSvcLvlRec.isCodeNormal()) {
            List<Map<String, Object>> shpgSvcLvlRecList = (List<Map<String, Object>>) resltShpgSvcLvlRec.getResultObject();

            bizMsg.shpgSvcLvlCd_CD.clear();
            bizMsg.shpgSvcLvlDescTxt_NM.clear();
            int validCnt = 0;
            for (Map<String, Object> shpgSvcLvlRec : shpgSvcLvlRecList) {
                bizMsg.shpgSvcLvlCd_CD.no(validCnt).setValue((String) shpgSvcLvlRec.get("SHPG_SVC_LVL_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlDescTxt_NM.no(validCnt), (String) shpgSvcLvlRec.get("SHPG_SVC_LVL_DESC_TXT"));
                validCnt++;
            }
        }
    }
    /**
     * Derive Default Carrier Service Level
     * @param bizMsg NWAL2200CMsg
     * @return No API Error : true
     */
    private static boolean deriveDefaultCarrSvcLvl(NWAL2200CMsg bizMsg) {

        bizMsg.carrSvcLvlDescTxt.clear(); // QC#13688 2017/02/24 Add
        bizMsg.carrAcctNum.clear();       // QC#13688 2017/02/24 Add
        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(bizMsg.shpgSvcLvlCd)) {
            return true;
        }
        // QC#23726 2018/06/27 del Start
//        if (!FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) { // QC#13688 2017/02/24 Add
//            return true;
//        }
        // QC#23726 2018/06/27 del End

        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(bizMsg);

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            // QC#23726 2018/06/27 mod Start
            if (FRT_COND.COLLECT.equals(bizMsg.frtCondCd.getValue())) {
                // QC#13688 2017/02/24 Mod Start
                // ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, vndCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum);
                // QC#13688 2017/02/24 Mod End
            }
            // QC#23726 2018/06/27 mod End
            S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getCarrSvcLvlDescTxt(bizMsg, vndCd);
            if (ssmResult.isCodeNormal()) {
                String carrSvcLvlDescTxt = (String) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcLvlDescTxt, carrSvcLvlDescTxt);
            }
        }

        return true;
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param bizMsg NWAL2200CMsg
     * @return NMZC611001PMsg
     */
    private static NMZC611001PMsg callDefaultCarrierApi(NWAL2200CMsg bizMsg) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, bizMsg.shipToCustAcctCd);
        // 2018/12/13 S21_NA#29315 Add Start
        SHIP_TO_CUSTTMsg tMsg = NWAL2200CommonLogic.getShipToCustByCondition(bizMsg.glblCmpyCd.getValue(), bizMsg.shipToCustCd.getValue());

        ZYPEZDItemValueSetter.setValue(pMsg.locNum, tMsg.locNum);
        ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue()));
        // 2018/12/13 S21_NA#29315 Add End
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    /**
     * Derive Default PO No.
     * @param bizMsg NWAL2200CMsg
     * @return No API Error : true
     */
    private static boolean deriveDefaultPO(NWAL2200CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.sellToCustCd)) {
            return true;
        }
        NMZC610001PMsg custInfoApiPMsg = callCustomerInfomationGetApi(bizMsg);

        if (S21ApiUtil.isXxMsgId(custInfoApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }
        if (custInfoApiPMsg.TransactionRuleList.getValidCount() > 0) {

            // 2019/02/15 QC#30308 Mod Start
            //ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum, custInfoApiPMsg.TransactionRuleList.no(0).dsBlktPoNum);
            String defCustIssPoNum = custInfoApiPMsg.TransactionRuleList.no(0).dsBlktPoNum.getValue();
            if (ZYPCommonFunc.hasValue(defCustIssPoNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum, defCustIssPoNum);
            }
            // 2019/02/15 QC#30308 Mod End
        }

        return true;
    }

    private static NMZC610001PMsg callCustomerInfomationGetApi(NWAL2200CMsg bizMsg) {

        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, NWAL2200CommonLogic.getTrxRuleTp(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, bizMsg.sellToCustCd);
        // 2019/01/30 S21_NA#30036 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bizMsg.soldToCustLocCd);
        // 2019/01/30 S21_NA#30036 Add End
        // 2019/02/15 QC#30308 Add Start
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, bizMsg.slsDt);
        // 2019/02/15 QC#30308 Add End
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        return pMsg;
    }


    // Del Start 2018/07/25 S21_NA#14307
    //private static String getTrxRuleTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

    //    ORD_CATG_BIZ_CTXTMsg tMsg = getOrdCatgBizCtx(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
    //    if (tMsg == null) {
    //        return null;
    //    }
    //    return tMsg.firstBizCtxAttrbTxt.getValue();
    //}

    //private static ORD_CATG_BIZ_CTXTMsg getOrdCatgBizCtx(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {

    //    ORD_CATG_BIZ_CTXTMsg inTMsg = new ORD_CATG_BIZ_CTXTMsg();
    //    inTMsg.setSQLID("005");
    //    inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //    inTMsg.setConditionValue("ordCatgCtxTpCd01A", NWAL2200Constant.EQUIPMENT_ORDER);
    //    inTMsg.setConditionValue("ordCatgCtxTpCd01B", NWAL2200Constant.SUPPLIES_ORDER);
    //    inTMsg.setConditionValue("dsOrdCatgCd01", dsOrdCatgCd);
    //    inTMsg.setConditionValue("dsOrdTpCd01", dsOrdTpCd);
    //    ORD_CATG_BIZ_CTXTMsgArray array = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
    //    if (array == null || array.length() == 0) {
    //        return null;
    //    }
    //    return array.no(0);
    //}
    // Del End 2018/07/25 S21_NA#14307

    /**
     * Common Process For Derive From Sold To
     * @param bizMsg NWAL2200CMsg
     * @param glblMsg NWAL2200SMsg
     * @param isSetSoldToFlg Set Sold To Flag
     */
    private static void cmnProcForDeriveFromSoldTo(NWAL2200CMsg bizMsg, NWAL2200SMsg glblMsg, boolean isSetSoldToFlg) {

        // 2019/02/01 Mod Start S21_NA#30097

//        // 2016/09/29 S21_NA#8659 Mod Start
//        // NMZC610001PMsg nMZC610001PMsg =
//        // callCustInfoGetApiForDefaultMode(bizMsg,
//        // bizMsg.sellToCustCd.getValue());
//        NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT);
//        // 2016/09/29 S21_NA#8659 Mod End
//
//        if (nMZC610001PMsg == null) {
//            return;
//        }
        if (isSetSoldToFlg) {
            NMZC610001PMsg nMZC610001PMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_OFF_N);
            if (nMZC610001PMsg == null) {
                return;
            }
            if (!setSoldToInfo(bizMsg, true, nMZC610001PMsg, ZYPConstant.FLG_OFF_N)) {
               return; 
            }
        }
        NMZC610001PMsg nMZC610001BSPMsg = callCustInfoGetApiForDefaultMode(bizMsg, bizMsg.sellToCustCd.getValue(), null, bizMsg.soldToCustLocCd.getValue(), NWXC150001DefaultCustomer.MODE_BILL_TO_ACCT, ZYPConstant.FLG_ON_Y);
        if (nMZC610001BSPMsg == null) {
            return;
        }
        // 2019/02/01 Mod End S21_NA#30097

        // 2019/02/01 Mod Start S21_NA#30097
//        String defBillToCustCd = nMZC610001PMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        String defBillToCustCd = nMZC610001BSPMsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        // 2019/02/01 Mod End S21_NA#30097

        setBillToInfo(bizMsg, defBillToCustCd);

        if (!ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctNm) || !ZYPCommonFunc.hasValue(bizMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
            // 2019/02/01 Mod Start S21_NA#30097
//            setShipToInfo(bizMsg, nMZC610001PMsg);
            setShipToInfo(bizMsg, nMZC610001BSPMsg);
            // 2019/02/01 Mod End S21_NA#30097
        }
        // 2019/02/01 Del Start S21_NA#30097
//        if (isSetSoldToFlg && !setSoldToInfoDeriveFromSoldTo(bizMsg)) {
//            return;
//        }
        // 2019/02/01 Del End S21_NA#30097

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustAcctCd) || ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
            deriveDefaultPmtTerm(bizMsg);
        }

        if (!deriveDefaultSlsRep(bizMsg, glblMsg)) {
            return;
        }

        if (!deriveDefaultCarrSvcLvl(bizMsg)) {
            return;
        }

        deriveDefaultPO(bizMsg);

        // if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd)) {
        // deriveDefaultBillToLocForDtlFld(bizMsg);
        // }

        // if (ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
        // deriveDefaultShipToLocForDtlFld(bizMsg);
        // }

        // if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd) &&
        // ZYPCommonFunc.hasValue(bizMsg.shipToCustCd)) {
        // if (!deriveDefaultWh(bizMsg, glblMsg)) {
        // return;
        // }
        // }

        // if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd)) {
        // deriveDefaultPrcList(bizMsg);
        // }
    }

    /**
     * Check Exist Warehouse
     * @param bizMsg NWAL2200CMsg
     * @param lineMsg NWAL2200_BCMsg
     */
    private static void checkExistWh(NWAL2200CMsg bizMsg, NWAL2200_BCMsg lineMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            if (!NWAL2200Query.getInstance().isExistWhWithDsOrdTp(bizMsg, lineMsg)) {
                lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {"Warehouse" });
            }
        } else {
            if (!NWAL2200Query.getInstance().isExistWhWithOutDsOrdTp(bizMsg, lineMsg)) {
                lineMsg.rtlWhNm_LL.setErrorInfo(1, NWAM0181E, new String[] {"Warehouse" });
            }
        }
    }

    /**
     * Get Warehouse Code
     * @param bizMsg NWAL2200CMsg
     * @param rmaLineMsg NWAL2200_DCMsg
     * @return Warehouse Code
     */
    private static Map<String, String> getWhInfo(NWAL2200CMsg bizMsg, NWAL2200_DCMsg rmaLineMsg) {

        S21SsmEZDResult ssmResult = null;
        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            ssmResult = NWAL2200Query.getInstance().getWhInfoWithRsn(bizMsg, rmaLineMsg);
        } else {
            ssmResult = NWAL2200Query.getInstance().getWhInfo(bizMsg, rmaLineMsg);
        }

        if (ssmResult.isCodeNotFound()) {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
                rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0181E, new String[] {"Warehouse" });
            } else {
                rmaLineMsg.rtlWhNm_RL.setErrorInfo(1, NWAM0300E, new String[] {"Warehouse" });
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, String>> whInfoList = (List<Map<String, String>>) ssmResult.getResultObject();

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        if (whInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return whInfoList.get(0);
    }

    /**
     * Call NLZC2150 Get Default Sub Warehouse API
     * @param bizMsg NWAL2200CMsg
     * @param mdseCd MDSE Code
     * @param rtlWhCd Retail Warehouse Code
     * @return NLZC215001PMsg
     */
    private static NLZC215001PMsg callDefSubWhApi(NWAL2200CMsg bizMsg, String mdseCd, String rtlWhCd) {

        NLZC215001PMsg defSubWhApiPMsg = new NLZC215001PMsg();
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.slsDt, bizMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.xxModeCd, NLZC215001Constant.MODE_RMA);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.dsOrdCatgCd, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.destRtlWhCd, rtlWhCd);

        // call NLZC2150 Get Default Sub Warehouse API
        new NLZC215001().execute(defSubWhApiPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(defSubWhApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defSubWhApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                bizMsg.setMessageInfo(msgId, msgPrms);
                return null;
            }
        }

        return defSubWhApiPMsg;
    }

    /**
     * Get Sub Warehouse Name
     * @param bizMsg NWAL2200CMsg
     * @param rtlWhCd Retail Warehouse Code
     * @param rtlSwhCd Retail Sub Warehouse Code
     * @return Sub Warehouse Name
     */
    private static String getSubWhNm(NWAL2200CMsg bizMsg, String rtlWhCd, String rtlSwhCd) {

        RTL_SWHTMsg rtlSwhTmsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(rtlSwhTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTmsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtlSwhTmsg.rtlSwhCd, rtlSwhCd);
        rtlSwhTmsg = (RTL_SWHTMsg) EZDTBLAccessor.findByKey(rtlSwhTmsg);

        if (rtlSwhTmsg == null) {
            return null;
        }

        return rtlSwhTmsg.rtlSwhNm.getValue();
    }

    /**
     * Set Bill To Info
     * @param bizMsg NWAL2200CMsg
     * @param lineMsg NWAL2200_BCMsg
     */
    @SuppressWarnings("unchecked")
    public static void setBillToInfo(NWAL2200CMsg bizMsg, NWAL2200_BCMsg lineMsg) {

        String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();
        String billToLocCd = null;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2200_ACMsg confMsg = bizMsg.A.no(i);
            if (dsOrdPosnNum.equals(confMsg.dsOrdPosnNum_LC.getValue())) {
                billToLocCd = confMsg.billToCustLocCd_LC.getValue();
                break;
            }
        }

        if (!ZYPCommonFunc.hasValue(billToLocCd)) {
            return;
        }

        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getBillToCustInfo(bizMsg, billToLocCd);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        Map<String, String> billToCustInfo = (Map<String, String>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(5).xxPopPrm, billToCustInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(6).xxPopPrm, billToCustInfo.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(7).xxPopPrm, billToCustInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(8).xxPopPrm, billToCustInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(9).xxPopPrm, billToCustInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(10).xxPopPrm, billToCustInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.P.no(11).xxPopPrm, billToLocCd);
    }

    private boolean isSetComponent(NWAL2200_DCMsg line) {

        return ZYPCommonFunc.hasValue(line.dsCpoLineSubNum_RL);
    }

    private static void clearParametersForPriceChanges(NWAL2200CMsg bizMsg) {

        // clear parameters
        bizMsg.xxModeCd_N.clear();
        //bizMsg.trxHdrNum_N.clear();
        bizMsg.ordSrcRefNum_N.clear();// 2018/01/25 S21_NA#23334 Mod
        bizMsg.prcBaseDt_N.clear();
        bizMsg.prcCalcDt_N.clear();
        bizMsg.dsOrdTpCd_N.clear();
        bizMsg.dsOrdCatgCd_N.clear();
        bizMsg.lineBizTpCd_N.clear();
        bizMsg.cpoSrcTpCd_N.clear();
        bizMsg.custIssPoNum_N.clear();
        bizMsg.dsPmtMethCd_N.clear();
        bizMsg.spclHdlgTpCd_N.clear();
        bizMsg.leasePrchOptCd_N.clear();
        bizMsg.dsOrdPosnNum_N.clear();
        bizMsg.trxLineNum_N.clear();
        bizMsg.trxLineSubNum_N.clear();
        bizMsg.configCatgCd_N.clear();
        bizMsg.inEachQty_N.clear();
        bizMsg.shipToFirstLineAddr_N.clear();
        bizMsg.shipToScdLineAddr_N.clear();
        bizMsg.shipToCtyAddr_N.clear();
        bizMsg.shipToStCd_N.clear();
        bizMsg.shipToCntyNm_N.clear();
        bizMsg.shipToPostCd_N.clear();
        bizMsg.shipToCtryCd_N.clear();
        bizMsg.prcCatgCd_N.clear();
        bizMsg.csmpNum_N.clear();
        bizMsg.dlrRefNum_N.clear();
        bizMsg.prcContrNum_N.clear();
        bizMsg.coaBrCd_N.clear();
        bizMsg.mdseCd_N.clear();
        bizMsg.billToCustCd_N.clear();
        bizMsg.billToCustAcctCd_N.clear();
        bizMsg.sellToCustCd_N.clear();
        bizMsg.soldToCustLocCd_N.clear();
        bizMsg.shipToCustCd_N.clear();
        bizMsg.shipToCustAcctCd_N.clear();
        bizMsg.frtCondCd_N.clear();
        bizMsg.shpgSvcLvlCd_N.clear();
        bizMsg.ccyCd_N.clear();
        bizMsg.uomCd_N.clear();
        bizMsg.ordCustUomQty_N.clear();
        bizMsg.dsCpoLineCatgCd_N.clear();
        bizMsg.invQty_N.clear();
        bizMsg.mdlId_N.clear();
        bizMsg.rtrnRsnCd_N.clear();
        bizMsg.coaExtnCd_N.clear();
        bizMsg.slsRepOrSlsTeamTocCd_N.clear();
        bizMsg.rtlWhCd_N.clear();
        bizMsg.xxTotBaseAmt_N.clear();
        bizMsg.xxSubTotCalcPrcAmt_N.clear();
        bizMsg.xxTotChrgPrcAmt_N.clear();
        bizMsg.xxTotDiscAmt_N.clear();
        bizMsg.xxTotSpclPrcAmt_N.clear();
        bizMsg.xxTotNetDiscAmt_N.clear();
        bizMsg.xxUnitNetPrcAmt_N.clear();
        bizMsg.xxUnitGrsPrcAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N.clear();
        bizMsg.xxGrsAmt_N.clear();
        bizMsg.xxUnitFrtAmt_N.clear();
        bizMsg.xxTotFrtAmt_N.clear();
        bizMsg.xxUnitSpclChrgAmt_N.clear();
        bizMsg.xxTotSpclChrgAmt_N.clear();
        bizMsg.xxTotFrtSubAmt_N.clear();
        bizMsg.xxUnitRestkFeeAmt_N.clear();
        bizMsg.xxTotRestkFeeAmt_N.clear();
        bizMsg.xxTotNetPrcAmt_N2.clear();
        bizMsg.xxTotTaxAmt_N.clear();
        bizMsg.xxUnitPrc_N.clear();
        bizMsg.xxTotAmt_N.clear();
        bizMsg.dealPrcListPrcAmt_N.clear();
        bizMsg.dsCpoLineNum_N.clear();
        bizMsg.dsCpoLineSubNum_N.clear();
        bizMsg.dealSvcRevTrnsfAmt_N.clear();
        bizMsg.dealSvcCostTrnsfAmt_N.clear();
        bizMsg.dealManFlAdjAmt_N.clear();
        bizMsg.dealManRepRevAdjAmt_N.clear();
        bizMsg.xxSfxKeyTxt_N.clear();
        ZYPTableUtil.clear(bizMsg.N);
    }

    private static void setParametersForPriceChanges(NWAL2200CMsg bizMsg, NWAL2200_BCMsg line, NWAL2200_ISMsgArray priceCalcBaseList) {

        // clear
        clearParametersForPriceChanges(bizMsg);

        // order header information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, "10");
        bizMsg.xxViewChngLogSrcCd_N.clear(); // QC#9700  2018/09/03 Add 
        //ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefNum_N, bizMsg.ordSrcRefNum);// 2018/01/25 S21_NA#23334 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, line.prcBaseDt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, bizMsg.dsPmtMethCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.leasePrchOptCd_N, bizMsg.leasePrchOptCd);

        // order line information
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_N, line.dsOrdPosnNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineNum_N, line.trxLineNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineSubNum_N, line.trxLineSubNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, line.ordQty_LL);
        NWAL2200_ACMsg config = getConfigLine(bizMsg.A, line.dsOrdPosnNum_LL.getValue());
        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_N, config.shipToFirstLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_N, config.shipToScdLineAddr_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_N, config.shipToCtyAddr_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_N, config.shipToStCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_N, config.shipToCntyNm_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_N, config.shipToPostCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_N, config.shipToCtryCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_N, config.billToCustLocCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_N, config.billToCustAcctCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_N, config.mdlId_LC);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, line.prcCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpNum_N, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum_N, line.dlrRefNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        // 2018/01/23 QC#18798 Mod Start
        //NWAL2200_GCMsg salesCredit = getSalesCredit(bizMsg.G, line.dsOrdPosnNum_LL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER });
        NWAL2200_GCMsg salesCredit = getSalesCredit(bizMsg.G, line.dsOrdPosnNum_LL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER });
        // 2018/01/23 QC#18798 Mod End
        if (salesCredit != null) {
            TOCTMsg salesRep = getSalesRep(bizMsg.glblCmpyCd.getValue(), salesCredit.slsRepTocCd_GS.getValue());
            if (salesRep != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_N, salesRep.coaBrCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_N, salesRep.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_N, salesRep.tocCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_N, line.mdseCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);

        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_N, config.shipToCustLocCd_LC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, config.shipToCustAcctCd_LC);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);
        // Price List
        S21SsmEZDResult ssmRslt = NWAL2200Query.getInstance().checkPriceCatgNm(bizMsg, bizMsg.prcCatgNm.getValue());
        if (ssmRslt.isCodeNormal()) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmRslt.getResultObject();
            PRC_CATGTMsg priceCategory = getPriceCategory(bizMsg.glblCmpyCd.getValue(), rsltMapList.get(0).get("PRC_CATG_CD"));
            if (priceCategory != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, priceCategory.ccyCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_N, line.custUomCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_N, line.ordCustUomQty_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineCatgCd_N, line.dsOrdLineCatgCd_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtrnRsnCd_N, "");
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_N, line.rtlWhCd_LL);

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_N, line.entDealNetUnitPrcAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_N, line.cpoDtlDealSlsAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, line.cpoDtlDealTaxAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, line.xxLineTotPrcAmt_LL);

        ZYPEZDItemValueSetter.setValue(bizMsg.dealPrcListPrcAmt_N, line.dealPrcListPrcAmt_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_N, line.dsCpoLineNum_LL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineSubNum_N, line.dsCpoLineSubNum_LL);

        // 2017/08/23 S21_NA#20097 Add Start
        // ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N,
        // BigDecimal.ZERO);
        // ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N,
        // BigDecimal.ZERO);

        setTrnsfAmt(bizMsg, line);
        // 2017/08/23 S21_NA#20097 Add End

        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);

        // price condition
        String configCatgCd = CONFIG_CATG.OUTBOUND;
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        BigDecimal dsImptOrdDtlPk = line.dsImptOrdDtlPk_LL.getValue();
        setPriceConditionList(bizMsg.N, priceCalcBaseList, configCatgCd, cpoOrdNum, dsImptOrdDtlPk, null);
    }

    private static void setParametersForPriceChanges(NWAL2200CMsg bizMsg, NWAL2200_DCMsg line, NWAL2200_ISMsgArray prcBaseCalcList) {

        // clear
        clearParametersForPriceChanges(bizMsg);

        // order header information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxModeCd_N, "10");
        bizMsg.xxViewChngLogSrcCd_N.clear(); // QC#9700  2018/09/03 Add 
        //ZYPEZDItemValueSetter.setValue(bizMsg.trxHdrNum_N, bizMsg.ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordSrcRefNum_N, bizMsg.ordSrcRefNum); // 2018/01/25 S21_NA#23334 Mod
        ZYPEZDItemValueSetter.setValue(bizMsg.prcBaseDt_N, line.prcBaseDt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCalcDt_N, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd_N, bizMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdCatgCd_N, bizMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.lineBizTpCd_N, bizMsg.lineBizTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoSrcTpCd_N, bizMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_N, bizMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsPmtMethCd_N, bizMsg.dsPmtMethCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.spclHdlgTpCd_N, bizMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.leasePrchOptCd_N, bizMsg.leasePrchOptCd);

        // order line information
        ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdPosnNum_N, line.dsOrdPosnNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineNum_N, line.trxLineNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.trxLineSubNum_N, line.trxLineSubNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.configCatgCd_N, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(bizMsg.inEachQty_N, line.ordQty_RL);
        NWAL2200_CCMsg config = getConfigLine(bizMsg.C, line.dsOrdPosnNum_RL.getValue());
        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr_N, config.shipToFirstLineAddr_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr_N, config.shipToScdLineAddr_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_N, config.shipToCtyAddr_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_N, config.shipToStCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_N, config.shipToCntyNm_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd_N, config.shipToPostCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd_N, config.shipToCtryCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_N, config.billToCustLocCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd_N, config.billToCustAcctCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdlId_N, config.mdlId_RC);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.prcCatgCd_N, line.prcCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.csmpNum_N, bizMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(bizMsg.dlrRefNum_N, line.dlrRefNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrNum_N, bizMsg.prcContrNum);
        // 2018/01/23 QC#18798 Mod Start
        //NWAL2200_HCMsg salesCredit = getSalesCredit(bizMsg.H, line.dsOrdPosnNum_RL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER });
        NWAL2200_HCMsg salesCredit = getSalesCredit(bizMsg.H, line.dsOrdPosnNum_RL.getValue(), new String[] {LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER });
        // 2018/01/23 QC#18798 Mod End
        if (salesCredit != null) {
            TOCTMsg salesRep = getSalesRep(bizMsg.glblCmpyCd.getValue(), salesCredit.slsRepTocCd_HS.getValue());
            if (salesRep != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd_N, salesRep.coaBrCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd_N, salesRep.coaExtnCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.slsRepOrSlsTeamTocCd_N, salesRep.tocCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.mdseCd_N, line.mdseCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd_N, bizMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd_N, bizMsg.soldToCustLocCd);

        if (config != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd_N, config.shipToCustLocCd_RC);
            ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd_N, config.shipToCustAcctCd_RC);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd_N, bizMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_N, bizMsg.shpgSvcLvlCd);
        PRC_CATGTMsg priceCategory = getPriceCategory(bizMsg.glblCmpyCd.getValue(), line.prcCatgCd_RL.getValue());
        if (priceCategory != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_N, priceCategory.ccyCd);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.uomCd_N, line.custUomCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.ordCustUomQty_N, line.ordCustUomQty_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineCatgCd_N, line.dsCpoLineCatgCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.invQty_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtrnRsnCd_N, line.rtrnRsnCd_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd_N, line.rtlWhCd_RL);

        // summary information
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitNetPrcAmt_N, line.entDealNetUnitPrcAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxUnitGrsPrcAmt_N, line.cpoDtlDealSlsAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotTaxAmt_N, line.cpoDtlDealTaxAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_N, line.xxLineTotPrcAmt_RL);

        ZYPEZDItemValueSetter.setValue(bizMsg.dealPrcListPrcAmt_N, line.dealPrcListPrcAmt_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineNum_N, line.dsCpoLineNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dsCpoLineSubNum_N, line.dsCpoLineSubNum_RL);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManFlAdjAmt_N, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(bizMsg.dealManRepRevAdjAmt_N, BigDecimal.ZERO);

        // price condition
        String configCatgCd = CONFIG_CATG.INBOUND;
        String cpoOrdNum = bizMsg.cpoOrdNum.getValue();
        BigDecimal dsImptOrdRtrnDtlPk = line.dsImptOrdRtrnDtlPk_RL.getValue();
        setPriceConditionList(bizMsg.N, prcBaseCalcList, configCatgCd, cpoOrdNum, null, dsImptOrdRtrnDtlPk);
    }

    // 2017/08/23 S21_NA#20097 Add Start
    private static void setTrnsfAmt(NWAL2200CMsg bizMsg, NWAL2200_BCMsg line) {
        if (bizMsg == null || line == null ) {
            return;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsImptOrdDtlPk", line.dsImptOrdDtlPk_LL.getValue());

        Map<String, Object> trnsfAmtSet = NWAL2200Query.getInstance().queryMap("getTrnsfAmt", ssmParam);

        if (trnsfAmtSet == null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, BigDecimal.ZERO);
        } else {

            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcRevTrnsfAmt_N, (BigDecimal) trnsfAmtSet.get("SUM_DEAL_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.dealSvcCostTrnsfAmt_N, (BigDecimal) trnsfAmtSet.get("SUM_DEAL_SVC_COST_TRNSF_AMT"));
        }
    }
    // 2017/08/23 S21_NA#20097 Add End

    private static PRC_CATGTMsg getPriceCategory(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg prcCatg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prcCatg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcCatg.prcCatgCd, prcCatgCd);
        return (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prcCatg);
    }

    private static TOCTMsg getSalesRep(String glblCmpyCd, String tocCd) {
        TOCTMsg toc = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(toc.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(toc.tocCd, tocCd);
        return (TOCTMsg) S21FastTBLAccessor.findByKey(toc);
    }

    private static List<NWAL2200_ISMsg> getPriceConditionList(NWAL2200_ISMsgArray priceConditionArray, String configCatgCd, String cpoOrdNum, BigDecimal dsImptOrdDtlPk, BigDecimal dsImptOrdRtrnDtlPk) {

        List<NWAL2200_ISMsg> priceConditionList = new ArrayList<NWAL2200_ISMsg>();
        for (int i = 0; i < priceConditionArray.getValidCount(); i++) {
            NWAL2200_ISMsg priceCondition = priceConditionArray.no(i);
            if (isSameLineForPriceCondition(priceCondition, configCatgCd, dsImptOrdDtlPk, dsImptOrdRtrnDtlPk)) { // S21_NA#2320
                priceConditionList.add(priceCondition);
            }
        }
        return priceConditionList;
    }

    private static boolean isSameLineForPriceCondition(NWAL2200_ISMsg priceCondition, String configCatgCd, BigDecimal dsImptOrdDtlPk, BigDecimal dsImptOrdRtrnDtlPk) { // S21_NA#2320

        if (!S21StringUtil.isEquals(configCatgCd, priceCondition.configCatgCd_LP.getValue())) {
            return false;
        }
        if (dsImptOrdDtlPk != null) {
            if (dsImptOrdDtlPk.compareTo(priceCondition.dsImptOrdDtlPk_LP.getValue()) != 0) {
                return false;
            }
        }
        if (dsImptOrdRtrnDtlPk != null) {
            if (dsImptOrdDtlPk.compareTo(priceCondition.dsImptOrdRtrnDtlPk_LP.getValue()) != 0) {
                return false;
            }
        }
        return true;
    }

    private static void setPriceConditionList(NWAL2200_NCMsgArray paramPriceConditionArray, NWAL2200_ISMsgArray priceConditionArray, String configCatgCd, String cpoOrdNum, BigDecimal dsImptOrdDtlPk, BigDecimal dsImptOrdRtrnDtlPk) {

        int paramCount = 0;
        paramPriceConditionArray.clear();
        List<NWAL2200_ISMsg> priceConditionList = getPriceConditionList(priceConditionArray, configCatgCd, cpoOrdNum, dsImptOrdDtlPk, dsImptOrdRtrnDtlPk);
        for (NWAL2200_ISMsg priceCondition : priceConditionList) {
            EZDMsg.copy(priceCondition, "LP", paramPriceConditionArray.no(paramCount), "NL");
            paramCount++;
        }
        paramPriceConditionArray.setValidCount(priceConditionList.size());
        return;
    }

    private static NWAL2200_ACMsg getConfigLine(NWAL2200_ACMsgArray aMsgArray, String configNum) {
        for (int i = 0; i < aMsgArray.getValidCount(); i++) {
            NWAL2200_ACMsg aMsg = aMsgArray.no(i);
            if (S21StringUtil.isEquals(aMsg.dsOrdPosnNum_LC.getValue(), configNum)) {
                return aMsg;
            }
        }
        return null;
    }

    private static NWAL2200_CCMsg getConfigLine(NWAL2200_CCMsgArray cMsgArray, String configNum) {
        for (int i = 0; i < cMsgArray.getValidCount(); i++) {
            NWAL2200_CCMsg cMsg = cMsgArray.no(i);
            if (S21StringUtil.isEquals(cMsg.dsOrdPosnNum_RC.getValue(), configNum)) {
                return cMsg;
            }
        }
        return null;
    }

    private static NWAL2200_GCMsg getSalesCredit(NWAL2200_GCMsgArray gMsgArray, String configNum, String[] lineBizRoleTp) {
        for (int i = 0; i < gMsgArray.getValidCount(); i++) {
            NWAL2200_GCMsg gMsg = gMsgArray.no(i);
            if (S21StringUtil.isEquals(gMsg.dsOrdPosnNum_GS.getValue(), configNum)) {
                if (Arrays.asList(lineBizRoleTp).contains(gMsg.slsRepRoleTpCd_GS.getValue())) {
                    return gMsg;
                }
            }
        }
        return null;
    }

    private static NWAL2200_HCMsg getSalesCredit(NWAL2200_HCMsgArray hMsgArray, String configNum, String[] lineBizRoleTp) {
        for (int i = 0; i < hMsgArray.getValidCount(); i++) {
            NWAL2200_HCMsg hMsg = hMsgArray.no(i);
            if (S21StringUtil.isEquals(hMsg.dsOrdPosnNum_HS.getValue(), configNum)) {
                if (Arrays.asList(lineBizRoleTp).contains(hMsg.slsRepRoleTpCd_HS.getValue())) {
                    return hMsg;
                }
            }
        }
        return null;
    }

    /**
     * Check Exist Customer
     * @param bizMsg NWAL2200CMsg
     */
    private static void checkExistCustomer(NWAL2200CMsg bizMsg) {

        int selectIdx = bizMsg.xxCellIdx.getValueInt();
        String shipToCustCd = null;

        if (selectIdx == -1) {
            shipToCustCd = bizMsg.shipToCustCd.getValue();
        } else {
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                shipToCustCd = bizMsg.A.no(selectIdx).shipToCustLocCd_LC.getValue();
            } else {
                shipToCustCd = bizMsg.C.no(selectIdx).shipToCustLocCd_RC.getValue();
            }
        }

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("shipToCustCd01", shipToCustCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        SHIP_TO_CUSTTMsgArray tmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return;
        }

        if (selectIdx == -1) {
            bizMsg.shipToCustCd.setErrorInfo(1, NWAM0181E, new String[] {"Ship To Location" });
        } else {
            if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
                bizMsg.A.no(selectIdx).shipToCustLocCd_LC.setErrorInfo(1, NWAM0181E, new String[] {"Ship To Location" });
            } else {
                bizMsg.C.no(selectIdx).shipToCustLocCd_RC.setErrorInfo(1, NWAM0181E, new String[] {"Ship To Location" });
            }
        }
    }

    private void numberingDsCpoLineNum(NWAL2200_BSMsgArray lineArray) {

        String dsOrdPosnNum = null;
        BigDecimal dsCpoLineNum = null;
        // String ordSrcRefLineNum = null;
        List<Integer> subLineList = new ArrayList<Integer>();
        String trxLineNum = "000";
        String trxLineSubNum = "001";
        for (int i = 0; i < lineArray.getValidCount(); i++) {

            NWAL2200_BSMsg line = lineArray.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_LL.getValue(), dsOrdPosnNum)) {
                // for SET Component
                // if
                // (S21StringUtil.isEquals(line.ordSrcRefLineNum_LL.getValue(),
                // ordSrcRefLineNum)) {
                //
                // // skip subline
                // subLineList.add(i);
                // continue;
                // }
                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE);
            } else {

                dsOrdPosnNum = line.dsOrdPosnNum_LL.getValue();
                dsCpoLineNum = BigDecimal.ONE;
            }
            line.dsCpoLineNum_LL.setValue(dsCpoLineNum);
            ZYPEZDItemValueSetter.setValue(line.xxLineNum_LL, NWXC150001DsCheck.editDtlLineNum(dsOrdPosnNum, dsCpoLineNum, null));
            // ordSrcRefLineNum = line.ordSrcRefLineNum_LL.getValue();

            trxLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(trxLineNum);
            ZYPEZDItemValueSetter.setValue(line.trxLineNum_LL, trxLineNum);
            ZYPEZDItemValueSetter.setValue(line.trxLineSubNum_LL, trxLineSubNum);
        }
        ZYPTableUtil.deleteRows(lineArray, subLineList);
    }

    private void numberingDsCpoLineNum(NWAL2200_DSMsgArray lineArray) {

        String dsOrdPosnNum = null;
        BigDecimal dsCpoLineNum = null;
        // Del Start 2018/09/01 QC#27489
        //String ordSrcRefLineNum = null;
        // Del End 2018/09/01 QC#27489
        List<Integer> subLineList = new ArrayList<Integer>();
        String trxLineNum = "000";
        String trxLineSubNum = "001";
        for (int i = 0; i < lineArray.getValidCount(); i++) {

            NWAL2200_DSMsg line = lineArray.no(i);
            if (S21StringUtil.isEquals(line.dsOrdPosnNum_RL.getValue(), dsOrdPosnNum)) {
                // Del Start 2018/09/01 QC#27489
                //if (S21StringUtil.isEquals(line.ordSrcRefLineNum_RL.getValue(), ordSrcRefLineNum)) {

                //    // skip subline
                //    subLineList.add(i);
                //    continue;
                //}
                // Del End 2018/09/01 QC#27489
                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE);
            } else {

                dsOrdPosnNum = line.dsOrdPosnNum_RL.getValue();
                dsCpoLineNum = BigDecimal.ONE;
            }
            line.dsCpoLineNum_RL.setValue(dsCpoLineNum);
            ZYPEZDItemValueSetter.setValue(line.xxLineNum_RL, NWXC150001DsCheck.editDtlLineNum(dsOrdPosnNum, dsCpoLineNum, null));
            // Del Start 2018/09/01 QC#27489
            //ordSrcRefLineNum = line.ordSrcRefLineNum_RL.getValue();
            // Del End 2018/09/01 QC#27489

            trxLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(trxLineNum);
            ZYPEZDItemValueSetter.setValue(line.trxLineNum_RL, trxLineNum);
            ZYPEZDItemValueSetter.setValue(line.trxLineSubNum_RL, trxLineSubNum);
        }
        ZYPTableUtil.deleteRows(lineArray, subLineList);
    }

    private void numberingErrorLineNum(NWAL2200_ESMsgArray errorArray, NWAL2200_BSMsgArray lineArray, NWAL2200_DSMsgArray rmaArray) {

        for (int i = 0; i < errorArray.getValidCount(); i++) {

            NWAL2200_ESMsg error = errorArray.no(i);
            if (ZYPCommonFunc.hasValue(error.dsImptSvcDtlPk_EL)) { // S21_NA#15796

                // service
                if (ZYPCommonFunc.hasValue(error.dsImptSvcLineNum_EL)) {

                    // service line
                    ZYPEZDItemValueSetter.setValue(error.xxLineNum_EL, error.dsImptSvcLineNum_EL.getValue().toPlainString());
                }
            } else if (ZYPCommonFunc.hasValue(error.dsImptOrdDtlPk_EL)) {

                // line
                for (int j = 0; j < lineArray.getValidCount(); j++) {

                    NWAL2200_BSMsg line = lineArray.no(j);
                    if (NWAL2200CommonLogic.compareBigDecimal(line.dsImptOrdDtlPk_LL.getValue(), error.dsImptOrdDtlPk_EL.getValue()) == 0) {

                        ZYPEZDItemValueSetter.setValue(error.xxLineNum_EL, line.xxLineNum_LL);
                        break;
                    }
                }
            } else if (ZYPCommonFunc.hasValue(error.dsImptOrdRtrnDtlPk_EL)) {

                // RMA
                for (int j = 0; j < rmaArray.getValidCount(); j++) {

                    NWAL2200_DSMsg line = rmaArray.no(j);
                    if (NWAL2200CommonLogic.compareBigDecimal(line.dsImptOrdRtrnDtlPk_RL.getValue(), error.dsImptOrdRtrnDtlPk_EL.getValue()) == 0) {

                        ZYPEZDItemValueSetter.setValue(error.xxLineNum_EL, line.xxLineNum_RL);
                        break;
                    }
                }
            } else if (ZYPCommonFunc.hasValue(error.dsImptOrdConfigPk_EL)) {

                // configuration
                ZYPEZDItemValueSetter.setValue(error.xxLineNum_EL, error.dsOrdPosnNum_EL);
            } else {

                // header
                error.xxLineNum_EL.clear();
            }
        }
    }

    private String getHeaderError(NWAL2200_ECMsgArray errorArray) {

        StringBuilder headerError = new StringBuilder();
        for (int i = 0; i < errorArray.getValidCount(); i++) {

            NWAL2200_ECMsg error = errorArray.no(i);
            if (ZYPCommonFunc.hasValue(error.dsImptOrdConfigPk_EL)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(error.dsImptOrdDtlPk_EL)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(error.dsImptOrdRtrnDtlPk_EL)) {
                continue;
            }
            if (headerError.length() > 0) {
                headerError.append("\r\n");
            }
            headerError.append(error.dsImptOrdErrTxt_EL.getValue());
        }
        return headerError.toString();
    }

    // 2017/03/09 S21_NA#16790 Add Start
    // 2019/03/28 S21_NA#30765 Mod Start
    // private String getOrigCpoOrdNum(String glblCmpyCd, BigDecimal dsImptOrdPk) {
    @SuppressWarnings("unchecked")
    private List<String> getOrigCpoOrdNum(String glblCmpyCd, BigDecimal dsImptOrdPk) {

        final Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("dsImptOrdPk", dsImptOrdPk);
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getOrigCpoOrdNum(queryParam);

        // return (String) ssmResult.getResultObject();
        return (List<String>) ssmResult.getResultObject();
        // 2019/03/28 S21_NA#30765 Mod End
    }
    // 2017/03/09 S21_NA#16790 Add End

    // 2017/03/14 S21_NA#16855 Add Start
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param bizMsg
     * </pre>
     */
    //public static String getTrtyGrpTpCdFromDsOrdTpCd(NWAL2200CMsg bizMsg) { // 2017/12/14 S21_NA#19804(Sol#349) MOD
    public static List<String> getTrtyGrpTpCdFromDsOrdTpCd(NWAL2200CMsg bizMsg) { // 2017/12/14 S21_NA#19804(Sol#349) MOD

        if (!ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null && ZYPCommonFunc.hasValue(dsOrdTpProcDfnTMsg.trtyGrpTpTxt)) {
            //return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();// 2017/12/14 S21_NA#19804(Sol#349) MOD
            return Arrays.asList(dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue().split(","));// 2017/12/14 S21_NA#19804(Sol#349) MOD
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End

    // 2018/01/23 S21_NA#18798 Add Start
    private static void getAllOrdCatgBizCtx(NWAL2200CMsg bizMsg){
        // 2018/03/20 S21_NA#24840 Mod Start
        //String resultFlg = NWAL2200Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        String resultFlg = NWAL2200Query.getInstance().getOrdCatgBizCtxScdAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        // 2018/03/20 S21_NA#24840 Mod End
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_EM, ZYPConstant.FLG_ON_Y);
            bizMsg.xxDplyCtrlFlg_LD.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_LD, ZYPConstant.FLG_ON_Y);
            bizMsg.xxDplyCtrlFlg_EM.clear();
        }
        resultFlg = NWAL2200Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_GS, ZYPConstant.FLG_ON_Y);
            bizMsg.xxDplyCtrlFlg_LD.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_LD, ZYPConstant.FLG_ON_Y);
            bizMsg.xxDplyCtrlFlg_GS.clear();
        }
    }

    public static List<Map<String, Object>> getSalesRepList(String glblCmpyCd, String slsRepTocCd) {
        if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
            return null;
        }
        S21SsmEZDResult ssmResult = NWAL2200Query.getInstance().getSalesRepList(glblCmpyCd, slsRepTocCd);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }
    // 2018/01/23 S21_NA#18798 Add End

}
