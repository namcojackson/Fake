package business.blap.NWAL1840;

import static business.blap.NWAL1840.constant.NWAL1840Constant.BILL_EVENT_LIST;
import static business.blap.NWAL1840.constant.NWAL1840Constant.CONST_NWAL1840_DEF_CTAC_FOR_PULLDOWN;
import static business.blap.NWAL1840.constant.NWAL1840Constant.IDX_3;
import static business.blap.NWAL1840.constant.NWAL1840Constant.NEW_LINE;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SHIP_EVENT_LIST;
import static business.blap.NWAL1840.constant.NWAL1840Constant.SOLD_EVENT_LIST;
import static business.blap.NWAL1840.constant.NWAL1840Constant.TAB_SA_HISTORY;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BILL_TO_LOC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BILL_TO_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_BILL_TO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SHIP_TO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SLS_REP_CD;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SLS_REP_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SOLD_TO_NM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.MSG_PARAM_SOLD_TO_NUM;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0037E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0100E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0181E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0659E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0797E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0831E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0839E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.ZZM8100I;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.ZZZM9001E;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWZM2274W;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.common.NWAL1840CommonLogic;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForCustomer;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForItemLine;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForPriceChanges;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForPricing;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForSaHistory;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForSalesCredit;
import business.blap.NWAL1840.common.NWAL1840CommonLogicForSaveSubmit;
import business.db.CCYTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.parts.NWZC157001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         T.Murai         Create          N/A
 * 2016/05/13   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/06/13   Fujitsu         Y.Kanefusa      Update          QC#6480
 * 2016/07/15   Fujitsu         H.Ikeda         Update          QC#11493
 * 2016/07/15   Fujitsu         H.Ikeda         Update          QC#11578
 * 2016/07/15   Fujitsu         H.Ikeda         Update          QC#11340
 * 2016/08/30   Fujitsu         M.Yamada        Update          QC#10754
 * 2016/11/16   Fujitsu         H.Ikeda         Update          QC#15875
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/03/13   Fujitsu         T.Aoi           Update          S21_NA#16987
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#18243
 * 2017/08/04   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/12/21   Fujitsu         K.Ishizuka      Update          S21_NA#20164(Sol#356)
 * 2018/02/23   Fujitsu         K.Ishizuka      Update          S21_NA#22399
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/04/17   Fujitsu         H.Nagashima     Update          S21_NA#22965
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#14307
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#26661,26713
 * 2018/11/21   Fujitsu         Hd.Sugawara     Update          QC#28683
 * 2018/12/11   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2018/12/13   Fujitsu         C.Hara          Update          QC#29547
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
            NWAL1840SMsg glblMsg = (NWAL1840SMsg) sMsg;

            if ("NWAL1840_INIT".equals(screenAplID)) {
                if (ZYPCommonFunc.hasValue(bizMsg.schdAgmtNum)) {
                    doProcess_NWAL1840Scrn00_Search(bizMsg, glblMsg);
                    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
                    NWAL1840CommonLogic.initStartTs(bizMsg);
                    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
                } else {
                    doProcess_NWAL1840_INIT(bizMsg, glblMsg);
                }
            } else if ("NWAL1840Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Search(bizMsg, glblMsg);
                // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
                NWAL1840CommonLogic.initStartTs(bizMsg);
                // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]

            } else if ("NWAL1840Scrn00_Add_ContactLine".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Add_ContactLine(bizMsg);

            } else if ("NWAL1840Scrn00_Add_line".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Add_line(bizMsg);

            } else if ("NWAL1840Scrn00_Add_Schd_line".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Add_Schd_line(bizMsg);

            } else if ("NWAL1840Scrn00_Calculation_PriceAmount".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Calculation_PriceAmount(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_Cancel_Line".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Cancel_Line(bizMsg);

            } else if ("NWAL1840Scrn00_Delete_ContactLine".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Delete_ContactLine(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_Line_All_Collapsed".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Line_All_Collapsed(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_Line_All_Expand".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Line_All_Expand(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_Line_Collapsed".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Line_Collapsed(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_Line_Expand".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_Line_Expand(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromCategory".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_OnChange_Reason".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnChange_Reason(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_OnBlur_FreightTerms".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_FreightTerms(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromBillToAccount".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToAccount(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromBillToName".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToName(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromContract".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromContract(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromItem".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromItem(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSalesRepCode".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSalesRepName".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSalesRepName(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSchdAllwQty".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSchdAllwQty(bizMsg, glblMsg);

            // QC#6480 2016/06/13 Add Start
            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromDealNetUnitPrcAmt".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromDealNetUnitPrcAmt(bizMsg, glblMsg);
            // QC#6480 2016/06/13 Add End

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSerNum".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSerNum(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromShipToAccount".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToAccount(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromShipToLocation".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromShipToName".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToName(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSoldToAccount".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToAccount(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSoldToLocation".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_DeriveFromSoldToName".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToName(bizMsg);

            } else if ("NWAL1840Scrn00_OnBlur_FreightTerms".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OnBlur_FreightTerms(bizMsg);

            } else if ("NWAL1840Scrn00_OnChange_ShpgSvcLvlCd".equals(screenAplID)) { // QC#13688 2017/02/24 Add
                doProcess_NWAL1840Scrn00_OnChange_ShpgSvcLvlCd(bizMsg);

            } else if ("NWAL1840Scrn00_OpenWin_CarrierServiceLevel".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OpenWin_CarrierServiceLevel(bizMsg);

            } else if ("NWAL1840Scrn00_OpenWin_PriceChanges".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OpenWin_PriceChanges(bizMsg);
             // 2018/07/26 QC#14307 Add Start
            } else if ("NWAL1840Scrn00_OpenWin_SpecialInstruction".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OpenWin_SpecialInstruction(bizMsg);
             // 2018/07/26 QC#14307 Add End
            } else if ("NWAL1840Scrn00_TAB_AdditionalData".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_TAB_AdditionalData(bizMsg, glblMsg);

            } else if ("NWAL1840Scrn00_TAB_Comments".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_TAB_Comments(bizMsg);

            } else if ("NWAL1840Scrn00_TAB_Lines".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_TAB_Lines(bizMsg);

            // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
            } else if ("NWAL1840Scrn00_TAB_SaHistory".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_TAB_SaHistory(bizMsg);
            // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]

            } else if ("NWAL1840_NSAL1240".equals(screenAplID)) {
                doProcess_NWAL1840_NSAL1240(bizMsg, glblMsg);

            } else if ("NWAL1840_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1840_NWAL1130(bizMsg, glblMsg);

            } else if ("NWAL1840_NWAL1600".equals(screenAplID)) {
                doProcess_NWAL1840_NWAL1600(bizMsg);

            } else if ("NWAL1840_NWAL1660".equals(screenAplID)) {
                doProcess_NWAL1840_NWAL1660(bizMsg);

            } else if ("NWAL1840_NWAL1860".equals(screenAplID)) {
                doProcess_NWAL1840_NWAL1860(bizMsg);

            } else if ("NWAL1840_NMAL6800".equals(screenAplID)) {
                doProcess_NWAL1840_NMAL6800(bizMsg);

            // QC#11578 2016/07/15 Add Start
            } else if ("NWAL1840_NMAL6760".equals(screenAplID)) {
                doProcess_NWAL1840_NMAL6760(bizMsg);
            // QC#11578 2016/07/15 Add End
                
            // QC#15875 2016/11/15 Add Start
            } else if ("NWAL1840_NSAL0110".equals(screenAplID)) {
                doProcess_NWAL1840_NSAL0110(bizMsg, glblMsg);
            // QC#15875 2016/11/15 Add End

            // QC#16452 Add Start
            } else if ("NWAL1840_NMAL6770".equals(screenAplID)) {
                doProcess_NWAL1840_NMAL6770(bizMsg);
            // QC#16452 Add End
            // 2018/07/26 QC#14307 Add Start
            } else if ("NWAL1840Scrn00_OpenWin_SoldTo".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OpenWin_SoldTo(bizMsg);
            } else if ("NWAL1840Scrn00_OpenWin_BillTo".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OpenWin_BillTo(bizMsg);
            } else if ("NWAL1840Scrn00_OpenWin_ShipTo".equals(screenAplID)) {
                doProcess_NWAL1840Scrn00_OpenWin_ShipTo(bizMsg);
            // 2018/07/26 QC#14307 Add End
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840_INIT(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        // Initial Screen Objects
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.C);
        ZYPTableUtil.clear(bizMsg.D);
        ZYPTableUtil.clear(bizMsg.E);
        ZYPTableUtil.clear(bizMsg.F);
        ZYPTableUtil.clear(bizMsg.G);
        ZYPTableUtil.clear(bizMsg.N);
        ZYPTableUtil.clear(bizMsg.Q);
        ZYPTableUtil.clear(bizMsg.R);
        ZYPTableUtil.clear(bizMsg.U);
        ZYPTableUtil.clear(bizMsg.V);
        ZYPTableUtil.clear(bizMsg.W);
        ZYPTableUtil.clear(bizMsg.X);
        ZYPTableUtil.clear(bizMsg.Y);
        ZYPTableUtil.clear(bizMsg.Z);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(glblMsg.C);
        ZYPTableUtil.clear(glblMsg.D);
        ZYPTableUtil.clear(glblMsg.E);
        ZYPTableUtil.clear(glblMsg.F);
        ZYPTableUtil.clear(glblMsg.G);
        ZYPTableUtil.clear(glblMsg.F);

        // Setting Initial Values
        String glblCmpyCd = getGlobalCompanyCode();

        // Set Function Currency Digit Number
        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblTMsg.glblCmpyCd, glblCmpyCd);
        glblTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblTMsg);
        if (glblTMsg != null) {
            CCYTMsg ccyMsg = new CCYTMsg();
            ZYPEZDItemValueSetter.setValue(ccyMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ccyMsg.ccyCd, glblTMsg.stdCcyCd);
            ccyMsg = (CCYTMsg) S21FastTBLAccessor.findByKey(ccyMsg);
            if (ccyMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, ccyMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccyMsg.aftDeclPntDigitNum);
            }
        }
        
        NWAL1840CommonLogic.setInitialValues(bizMsg, glblCmpyCd, getContextUserInfo().getUserId());
        NWAL1840CommonLogic.setAuthority(bizMsg, getUserProfileService());

        // Create Pulldown
        NWAL1840CommonLogic.createPulldown(bizMsg);
    }

    /**
     * do Process (CMN_Clear)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_CMN_Clear(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {
        doProcess_NWAL1840_INIT(bizMsg, glblMsg);
    }

    /**
     * do Process (CMN_Reset)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_CMN_Reset(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.schdAgmtNum)) {
            doProcess_NWAL1840Scrn00_Search(bizMsg, glblMsg);
            // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
            NWAL1840CommonLogic.initStartTs(bizMsg);
            // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        } else {
            doProcess_NWAL1840_INIT(bizMsg, glblMsg);
        }
    }

    /**
     * do Process (CMN_Save)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_CMN_Save(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        doProcess_NWAL1840Scrn00_Search(bizMsg, glblMsg);

    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_CMN_Submit(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        doProcess_NWAL1840Scrn00_Search(bizMsg, glblMsg);
        // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        NWAL1840CommonLogic.initStartTs(bizMsg);
        // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]

    }

    /**
     * do Process (Search)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Search(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if ("W".equals(bizMsg.getMessageKind())) {
            return;
        }
        // Initial Screen Objects
        String schdAgmtNum = bizMsg.schdAgmtNum.getValue();
        doProcess_NWAL1840_INIT(bizMsg, glblMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.schdAgmtNum, schdAgmtNum);

        // Get Header
        S21SsmEZDResult ssmResult = NWAL1840QueryForSearch.getInstance().getSchdAgmtHdr(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(ZZZM9001E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustLocCd.getValue()));         // QC#17474 2017/02/21 Add
        // 2018/12/11 S21_NA#29315 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWXC150001DsCheck.getDsBizArea(bizMsg.glblCmpyCd.getValue(), bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue(), bizMsg.dsOrdRsnCd.getValue()));
        // 2018/12/11 S21_NA#29315 Add End

        // Create Rsn Pulldown
        NWAL1840CommonLogic.createRsnCdPulldown(bizMsg);

        NWAL1840CommonLogic.setShpgSvcLvlPullDown(bizMsg);

        // Get Line
        // NWAL1840CommonLogicForSearch.searchSchdAgmtLine(bizMsg);
        ssmResult = NWAL1840QueryForSearch.getInstance().getSchdAgmtLine(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        //List<String> afterShippedStsList = NWAL1840CommonLogic.getAfterShippedSts(); //2018/12/13 QC#29547 Del
        // Get Plan Line
        // NWAL1840CommonLogicForSearch.searchSchdAgmtPln(bizMsg);
        //2018/12/13 QC#29547 Mod Start
        //ssmResult = NWAL1840QueryForSearch.getInstance().getSchdAgmtPln(bizMsg, afterShippedStsList);
        ssmResult = NWAL1840QueryForSearch.getInstance().getSchdAgmtPln(bizMsg);
        //2018/12/13 QC#29547 Mod End
        if (ssmResult.isCodeNotFound()) {
            ZYPTableUtil.clear(bizMsg.B);
        }

        // Get Sales Credit
        NWAL1840QueryForSearch.getInstance().getSchdAgmtSalesCredit(bizMsg);

        // Get Contact Person
        NWAL1840QueryForSearch.getInstance().getSchdAgmtCtacPsn(bizMsg);

        // Get Price Info
        NWAL1840QueryForSearch.getInstance().getPriceInfo(bizMsg);

        // Get Additional Data
        NWAL1840QueryForSearch.getInstance().getSchdAgmtData(bizMsg);

        // Get Ship To Customer Address Info (For Header Hidden)
        NWAL1840CommonLogic.getShipToCustAddrInfo(bizMsg);

        // Calculation Line
        NWAL1840CommonLogic.calcLineInfo(bizMsg);

        // Get Summary For Header
        NWAL1840CommonLogic.getSummary(bizMsg);

        // Get Plan Create Date List
        NWAL1840CommonLogic.getOrderSubmitDtList(bizMsg);

        // Store Backup Data For $ Button
        NWAL1840CommonLogic.getBackUp(bizMsg, glblMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, bizMsg.schdAgmtNum);
    }

    /**
     * do Process (Add_ContactLine)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_Add_ContactLine(NWAL1840CMsg bizMsg) {

        if (bizMsg.D.getValidCount() == bizMsg.D.length()) {
            bizMsg.setMessageInfo(NWAM0100E);
            return;
        }

        bizMsg.D.setValidCount(bizMsg.D.getValidCount() + 1);
        
        // 2017/12/21 S21_NA#20164(Sol#356) ADD START
        String defaultContactTpCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NWAL1840_DEF_CTAC_FOR_PULLDOWN, bizMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(defaultContactTpCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(bizMsg.D.getValidCount() - 1).ctacPsnTpCd_D, defaultContactTpCd);
        }
        // 2017/12/21 S21_NA#20164(Sol#356) ADD END
        
        // QC#16452 add Start
        // set default
        int lastIdx = bizMsg.D.getValidCount() - 1;
        // 2017/12/21 S21_NA#20164(Sol#356) MOD START
        //bizMsg.D.no(lastIdx).ctacPsnTpCd_D.setValue(bizMsg.ctacTpCd_CD.no(0).getValue());
        //bizMsg.D.no(lastIdx).ctacCustRefTpCd_D.setValue(bizMsg.L.no(0).ctacCustRefTpCd_L.getValue());
        
        String ctacPsnTpCd = bizMsg.D.no(lastIdx).ctacPsnTpCd_D.getValue();
        for (int i = 0; i < bizMsg.ctacTpCd_CD.length(); ++i) {
            if (ctacPsnTpCd.equals(bizMsg.ctacTpCd_CD.no(i).getValue())) {
                bizMsg.D.no(lastIdx).ctacCustRefTpCd_D.setValue(bizMsg.L.no(i).ctacCustRefTpCd_L.getValue());
                break;
            }
        }
        // 2017/12/21 S21_NA#20164(Sol#356) MOD END
        // QC#16452 add End
    }

    /**
     * do Process (Add_ContactLine)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_Add_line(NWAL1840CMsg bizMsg) {

        if (bizMsg.A.getValidCount() == bizMsg.A.length()) {
            bizMsg.setMessageInfo(NWAM0659E);
            return;
        }

        NWAL1840CommonLogic.setNewLine(bizMsg);
    }

    /**
     * do Process (Add_ContactLine)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_Add_Schd_line(NWAL1840CMsg bizMsg) {

        if (bizMsg.B.getValidCount() == bizMsg.B.length()) {
            bizMsg.setMessageInfo(NWAM0659E);
            return;
        }

        NWAL1840CommonLogic.setNewSchdLine(bizMsg);
    }

    /**
     * do Process (Calculation_QuoteAmount)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Calculation_PriceAmount(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        // Check Mandatory
        if (!NWAL1840CommonLogicForSaveSubmit.checkMandatoryField(bizMsg, true)) {
//      if (!NWAL1840CommonLogicForSaveSubmit.checkMandatoryField(bizMsg)) { // Mod 2016/09/15 S21_NA#13914
            return;
        }

        // Set Manual Price
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1840_ACMsg itemLineMsg = bizMsg.A.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg_A.getValue())) {
                continue;
            }

            // Skip Set Component
            String[] xxLineNumArray = itemLineMsg.xxLineNum_A.getValue().split("\\.");
            if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new BigDecimal(xxLineNumArray[2])) != 0) {
                continue;
            }

            // QC#6480 2016/06/06 Del Start
//            NWAL1840_ECMsg basePriceLine = NWAL1840CommonLogicForPricing.getBasePrcData(bizMsg, itemLineMsg);
//
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.manPrcFlg_A, ZYPConstant.FLG_OFF_N);
//            if (ZYPCommonFunc.hasValue(itemLineMsg.dealNetUnitPrcAmt_A) && basePriceLine != null) {
//                if (itemLineMsg.dealNetUnitPrcAmt_A.getValue().compareTo(basePriceLine.autoPrcAmtRate_E.getValue()) != 0) {
//                    ZYPEZDItemValueSetter.setValue(basePriceLine.manPrcAmtRate_E, itemLineMsg.dealNetUnitPrcAmt_A);
//                    ZYPEZDItemValueSetter.setValue(basePriceLine.prcCondManEntryFlg_E, ZYPConstant.FLG_ON_Y);
//                    ZYPEZDItemValueSetter.setValue(itemLineMsg.manPrcFlg_A, ZYPConstant.FLG_ON_Y);
//                }
//            }
            // QC#6480 2016/06/06 Del End
        }

        // Call Pricing API
        NWZC157001PMsg prcApiPMsg = NWAL1840CommonLogicForPricing.callPricingApiGetOrderAllMode(bizMsg);
        if (prcApiPMsg == null) {
            return;
        }

        // Setup Price Elements
        NWAL1840CommonLogicForPricing.setPriceElement(bizMsg, prcApiPMsg);

        // Get Summary For Header
        NWAL1840CommonLogic.getScheduleSummary(bizMsg);

        // Store Backup Data For $ Button
        NWAL1840CommonLogic.getBackUp(bizMsg, glblMsg);

        bizMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * do Process (Add_ContactLine)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_Cancel_Line(NWAL1840CMsg bizMsg) {

        if (!NWAL1840CommonLogic.orderCooperateCheck(bizMsg)) {
            return;
        }

        // Schedule Cancel
        NWAL1840CommonLogic.scheduleCancel(bizMsg);

        // Line Cancel
        NWAL1840CommonLogic.lineCancel(bizMsg);

    }

    /**
     * do Process (Delete_ContactLine)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Delete_ContactLine(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.D, "xxChkBox_D", ZYPConstant.FLG_ON_Y);

        for (int checkIndex : checkList) {
            NWAL1840_DCMsg ctacLineMsg = bizMsg.D.no(checkIndex);

            if (ZYPCommonFunc.hasValue(ctacLineMsg.schdAgmtCtacPsnPk_D)) {
                NWAL1840_DSMsg ctacLineGlblMsg = glblMsg.D.no(glblMsg.D.getValidCount());
                EZDMsg.copy(ctacLineMsg, "D", ctacLineGlblMsg, "D");
                glblMsg.D.setValidCount(glblMsg.D.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.D, checkList);
    }

    /**
     * do Process (Line_Collapsed)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Line_All_Collapsed(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.xxCellIdx.setValue(i);
            doProcess_NWAL1840Scrn00_Line_Collapsed(bizMsg, glblMsg);
        }

    }

    /**
     * do Process (Line_Expand)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Line_All_Expand(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            bizMsg.xxCellIdx.setValue(i);
            doProcess_NWAL1840Scrn00_Line_Expand(bizMsg, glblMsg);
        }
    }

    /**
     * do Process (Line_Collapsed)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Line_Collapsed(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        final int eventLine = bizMsg.xxCellIdx.getValueInt();

        NWAL1840_ACMsg lineMsg = bizMsg.A.no(eventLine);
        if (ZYPConstant.FLG_ON_Y.equals(lineMsg.xxSmryLineFlg_A.getValue())) {
            return;
        }

        lineMsg.xxSmryLineFlg_A.setValue(ZYPConstant.FLG_ON_Y);
    }

    /**
     * do Process (Line_Expand)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_Line_Expand(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        final int eventLine = bizMsg.xxCellIdx.getValueInt();

        NWAL1840_ACMsg lineMsg = bizMsg.A.no(eventLine);
        if (ZYPConstant.FLG_OFF_N.equals(lineMsg.xxSmryLineFlg_A.getValue())) {
            return;
        }

        lineMsg.xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);

    }

    /**
     * do Process (ConBlur_Category)
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromCategory(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {
        if (!NWAL1840CommonLogic.checkExistCatg(bizMsg)) {
            return;
        }

        // Create Reason Code Pulldown
        NWAL1840CommonLogic.createRsnCdPulldown(bizMsg);
        // QC#11493 2016/07/15 Del Start
        //ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdTpCd, bizMsg.dsOrdTpCd_CD.no(0));
        //ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdTpCd, NWAL1840CommonLogic.getCpoOrdTpCd(bizMsg));
        // QC#11493 2016/07/15 Del End
        // Set Default Data
        NWAL1840CommonLogic.deriveDefaultData(bizMsg, glblMsg);
    }

    /**
     * do Process (OnChange_Reason)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_OnChange_Reason(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {
        // Set Default Data
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdTpCd, NWAL1840CommonLogic.getCpoOrdTpCd(bizMsg));
        NWAL1840CommonLogic.deriveDefaultData(bizMsg, glblMsg);
    }

    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSalesRepCode(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.psnNum)) { // 2016/05/13 S21_NA#7861 Mod slsRepPsnCd -> psnNum
            bizMsg.slsRepTocCd.clear();
            bizMsg.slsRepTocNm.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1840CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getDsOrdCatgList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }

        String writerSlsRepTocCd = NWAL1840CommonLogicForSalesCredit.getSlsRepCd(bizMsg, false, MSG_PARAM_SLS_REP_CD);
        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
            return;
        }

        // Delete All Sales Credit
        NWAL1840CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

        // Set Writer Sales Credit
        NWAL1840CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, writerSlsRepTocCd);
    }

    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSalesRepName(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
            bizMsg.psnNum.clear();  // 2016/05/13 S21_NA#7861 Mod slsRepPsnCd -> psnNum
            bizMsg.slsRepTocCd.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1840CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getDsOrdCatgList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }

        String writerSlsRepTocCd = NWAL1840CommonLogicForSalesCredit.getSlsRepCd(bizMsg, true, MSG_PARAM_SLS_REP_NM);
        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
            return;
        }

        // Delete All Sales Credit
        NWAL1840CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

        // Set Writer Sales Credit
        NWAL1840CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, writerSlsRepTocCd);
    }

    /**
     * do Process (OnBlur_SchdAllwQty)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSchdAllwQty(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        int lineNum = bizMsg.xxCellIdx.getValueInt();
        int schdQtySum = 0;

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {

            if (bizMsg.A.no(lineNum).schdAgmtLineNum_A.getValue().compareTo(bizMsg.B.no(i).schdAgmtLineNum_B.getValue()) == 0) {

                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).xxExstFlg_B.getValue()) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.B.no(i).schdAgmtPlnCancFlg_B.getValue())) {
                    schdQtySum += bizMsg.B.no(i).ordQty_B.getValueInt();
                }
            }
        }

        if (bizMsg.A.no(lineNum).schdAllwQty_A.getValueInt() < schdQtySum) {
            bizMsg.A.no(lineNum).schdAllwQty_A.setErrorInfo(1, NWAM0797E);
            return;
        }

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(lineNum).mdseCd_A.getValue());

        if (mdseTMsg == null) {
            bizMsg.A.no(lineNum).mdseCd_A.setErrorInfo(1, NWAM0037E);
            return;
        }
        bizMsg.A.no(lineNum).manPrcFlg_A.setValue(ZYPConstant.FLG_OFF_N);

        NWAL1840CommonLogicForItemLine.deriveLinePrice(bizMsg, true);
    }

    // QC#6480 2016/06/13 Add Start
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromDealNetUnitPrcAmt(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {
        int lineNum = bizMsg.xxCellIdx.getValueInt();

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), bizMsg.A.no(lineNum).mdseCd_A.getValue());

        if (mdseTMsg == null) {
            bizMsg.A.no(lineNum).mdseCd_A.setErrorInfo(1, NWAM0037E);
            return;
        }
        bizMsg.A.no(lineNum).manPrcFlg_A.setValue(ZYPConstant.FLG_ON_Y);

        NWAL1840CommonLogicForItemLine.deriveLinePrice(bizMsg, false);
    }
    // QC#6480 2016/06/13 Add End

    /**
     * do Process (OnBlur_SerNum)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSerNum(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        // get Default Customer Info
        // NWAL1840CommonLogic.getSoldCustInfo(bizMsg);
        NWAL1840CommonLogic.getCustInfoFor1240(bizMsg);
        // get Default Other Customer Info
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg); // QC#11654 2016/09/02 Add
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg);
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg); // QC#11654 2016/09/02 Add

        // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        if (TAB_SA_HISTORY.equals(bizMsg.xxDplyTab.getValue())) {
            NWAL1840CommonLogicForSaHistory.searchSaHistory(bizMsg);
        }
        // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    }

    /**
     * do Process (OnBlur_FreightTerms)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_FreightTerms(NWAL1840CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            bizMsg.frtCondCd.clear();
            return;
        }

        Map<String, String> freightTermInfo = NWAL1840CommonLogic.getFreightTermInfo(bizMsg);
        if (freightTermInfo == null) {
            bizMsg.frtCondCd.clear();
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, freightTermInfo.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondDescTxt, freightTermInfo.get("FRT_COND_DESC_TXT"));

        NWAL1840CommonLogic.setShpgSvcLvlPullDown(bizMsg);
        // 2018/12/21 S21_NA#29315 Del Start
//        NWAL1840CommonLogic.deriveDefaultCarrSvcLvl(bizMsg); // QC#13688 2017/02/24 Add
        // 2018/12/21 S21_NA#29315 Del End
    }

    // QC#13688 2017/02/24 Add Start
    /**
     * do Process (OnChange_ShpgSvcLvlCd)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnChange_ShpgSvcLvlCd(NWAL1840CMsg bizMsg) {
        // 2018/12/21 S21_NA#29315 Del Start
//        NWAL1840CommonLogic.deriveDefaultCarrSvcLvl(bizMsg);
        // 2018/12/21 S21_NA#29315 Del End
    }
    // QC#13688 2017/02/24 Add End

    /**
     * do Process (OnBlur_DeriveFromBillToAccount)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToAccount(NWAL1840CMsg bizMsg) {
        // 2018/07/26 QC#14307 Add Start

        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/07/26 QC#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1840CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.billToCustAcctCd, MSG_PARAM_BILL_TO_NUM);
        if (dsAcctCustTMsg == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        bizMsg.billToCustLocCd.clear();
        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromBillToLocation)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.billToCustLocCd_BK);
        // 2018/07/26 QC#14307 Add End

        Map<String, String> billToInfo = NWAL1840CommonLogicForCustomer.getBillToInfo(bizMsg, bizMsg.billToCustLocCd, MSG_PARAM_BILL_TO_LOC);
        if (billToInfo == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        String billToCustCd = billToInfo.get("BILL_TO_CUST_CD");
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustLocCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToInfo.get("BILL_TO_ADDR"));
        // 2018/03/09 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum, billToInfo.get("BILL_TO_LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Add End
        NWAL1840CommonLogic.deriveDefaultPmtTerm(bizMsg, billToCustCd);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromBillToName)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToName(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/07/26 QC#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1840CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.billToCustAcctNm, MSG_PARAM_BILL_TO_NM);
        if (dsAcctCustTMsg == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        bizMsg.billToCustLocCd.clear();
        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromContract)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromContract(NWAL1840CMsg bizMsg) {

        //        // get Default Customer Info
//        NWAL1840CommonLogic.getBillCustInfo(bizMsg);
//        // get Default Other Customer Info
//        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg);
        // get contract End Date
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrNum)) {
            DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
            dsContrTmsg.setSQLID("003");
            dsContrTmsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
            dsContrTmsg.setConditionValue("dsContrNum01", bizMsg.dsContrNum.getValue());

            DS_CONTRTMsgArray dsContrTmsgArray = (DS_CONTRTMsgArray) EZDTBLAccessor.findByCondition(dsContrTmsg);

            if(dsContrTmsgArray != null && dsContrTmsgArray.length() > 0){
                dsContrTmsg = dsContrTmsgArray.no(0);
                ZYPEZDItemValueSetter.setValue(bizMsg.contrVrsnEffFromDt, dsContrTmsg.contrVrsnEffFromDt);
                ZYPEZDItemValueSetter.setValue(bizMsg.contrVrsnEffThruDt, dsContrTmsg.contrVrsnEffThruDt);
            }
        } else {
            bizMsg.contrVrsnEffFromDt.clear();
            bizMsg.contrVrsnEffThruDt.clear();
        }
    }

    /**
     * do Process (OnBlur_DeriveFromItem)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromItem(NWAL1840CMsg bizMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();

        String inputMdseCd = NWAL1840CommonLogicForItemLine.checkMdseCd(bizMsg);
        if (!ZYPCommonFunc.hasValue(inputMdseCd)) {
            return;
        }
        MDSETMsg baseMdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), inputMdseCd);
        // 2017/03/13 S21_NA#16987 Mod Start

        if (baseMdseTMsg != null) {
            //if (baseMdseTMsg == null || ZYPConstant.FLG_ON_Y.equals(baseMdseTMsg.sellHldFlg.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(baseMdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(baseMdseTMsg.custOrdEnblFlg.getValue())) {
                bizMsg.A.no(slctLine).mdseCd_A.setErrorInfo(1, NWAM0037E);
                return;
            }
        } else {
            bizMsg.A.no(slctLine).mdseCd_A.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/03/13 S21_NA#16987 Mod End

        if (MDSE_TP.SET.equals(baseMdseTMsg.mdseTpCd.getValue())) {
            bizMsg.A.no(slctLine).mdseCd_A.setErrorInfo(1, NWAM0839E);
            return;
        }
        // QC#11340 2016/08/03 Del Start
        //if (!NWAL1840CommonLogicForItemLine.otherCheck(bizMsg)) {
        //    return;
        //}
        // QC#11340 2016/08/03 Del End
        NWAL1840CommonLogicForItemLine.deriveItemLineInfo(bizMsg, inputMdseCd, baseMdseTMsg);
        // NWAL1840CommonLogicForItemLine.setEachQty(bizMsg, baseMdseTMsg.mdseCd.getValue());
        // NWAL1840CommonLogicForItemLine.deriveLinePrice(bizMsg);
    }

    /**
     * do Process (OnBlur_DeriveFromShipToAccount)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToAccount(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/07/26 QC#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1840CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.shipToCustAcctCd, MSG_PARAM_SHIP_TO_NUM);
        if (dsAcctCustTMsg == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        // 2017/07/27 S21_NA#18243 Add Start
        bizMsg.shipToCustLocCd.clear();
        // 2017/07/27 S21_NA#18243 Add End
        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, true);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End

    }

    /**
     * do Process (OnBlur_DeriveFromShipToLocation)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToLocation(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.shipToCustLocCd_BK);
        // 2018/07/26 QC#14307 Add End

        Map<String, String> shipToInfo = NWAL1840CommonLogicForCustomer.getShipToInfo(bizMsg);
        if (shipToInfo == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustLocCd, shipToInfo.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToInfo.get("LOC_NM"));
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
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum, shipToInfo.get("LOC_NUM"));
        bizMsg.shipToLocNm_DS.clear(); // 2018/02/23 S21_NA#22399 Add
        // 2018/03/09 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNum, shipToInfo.get("LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Add End

        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, false);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromShipToName)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToName(NWAL1840CMsg bizMsg) {


        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/07/26 QC#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1840CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.shipToCustAcctNm, MSG_PARAM_SHIP_TO_NM);
        if (dsAcctCustTMsg == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        bizMsg.shipToCustLocCd.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, true);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromSoldToAccount)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToAccount(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.sellToCustCd_BK);
        // 2018/07/26 QC#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1840CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.sellToCustCd, MSG_PARAM_SOLD_TO_NUM);
        if (dsAcctCustTMsg == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        // 2017/07/27 S21_NA#18243 Add Start
        bizMsg.soldToCustLocCd.clear();
        // 2017/07/27 S21_NA#18243 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, true);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End

    }

    /**
     * do Process (OnBlur_DeriveFromSoldToLocation)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToLocation(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/07/26 QC#14307 Add End

        Map<String, String> billToInfo = NWAL1840CommonLogicForCustomer.getBillToInfo(bizMsg, bizMsg.soldToCustLocCd, MSG_PARAM_SHIP_TO_LOC);
        if (billToInfo == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, billToInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToInfo.get("BILL_TO_ADDR"));
        // 2018/03/09 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToLocNum, billToInfo.get("BILL_TO_LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Add End

        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, false);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromSoldToName)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToName(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/07/26 QC#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1840CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.soldToCustAcctNm, MSG_PARAM_SOLD_TO_NM);
        if (dsAcctCustTMsg == null) {
            // Add Start 2018/11/21 QC#28683
            NWAL1840CommonLogicForCustomer.setParamForNMAL6760SpecialInstruction(bizMsg);
            // Add End 2018/11/21 QC#28683
            return;
        }

        bizMsg.soldToCustLocCd.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, dsAcctCustTMsg.dsAcctNm);

        NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, true);

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/07/26 QC#14307 Add End
    }

    /**
     * do Process (OpenWin_CarrierServiceLevel)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OpenWin_CarrierServiceLevel(NWAL1840CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getFrtCondCd(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
            return;
        }

        String frtCondCd = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, frtCondCd);
    }

    /**
     * do Process (OpenWin_PriceChanges)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OpenWin_PriceChanges(NWAL1840CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        //QC#22965 mod Start
//        if (checkList.size() == 0) {
//            return;
//        }
//        NWAL1840CommonLogicForPriceChanges.setPrcChangeParam(bizMsg, bizMsg.A.no(checkList.get(0)));
        if (checkList.size() == 0) {
            NWAL1840CommonLogicForPriceChanges.setPrcChangeParamForHeader(bizMsg);
        } else {
            NWAL1840CommonLogicForPriceChanges.setPrcChangeParam(bizMsg, bizMsg.A.no(checkList.get(0)));
        }
        //QC#22965 mod End
    }

    /**
     * do Process (OpenWin_PriceChanges)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_TAB_AdditionalData(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, "FHEAD");

        if (!NWAL1840CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
            return;
        }
    }

    /**
     * do Process (OpenWin_PriceChanges)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_TAB_Comments(NWAL1840CMsg bizMsg) {

        if (!NWAL1840CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
            return;
        }
    }

    /**
     * do Process (OpenWin_PriceChanges)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_TAB_Lines(NWAL1840CMsg bizMsg) {

        if (!NWAL1840CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
            return;
        }

        if (bizMsg.A.getValidCount() == 0) {

            // Create New Configuration Line.
            NWAL1840CommonLogic.setNewLine(bizMsg);

        }
    }

    /**
     * do Process (NWAL1840_NSAL1240)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840_NSAL1240(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        // get Default Customer Info
        NWAL1840CommonLogic.getCustInfoFor1240(bizMsg);
        // get Default Other Customer Info
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg);
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg);
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg); // QC#11654 2016/09/02 Add

        // get contract End Date
        if (ZYPCommonFunc.hasValue(bizMsg.contrPk_QO)) {
            DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrPk, bizMsg.contrPk_QO);

            dsContrTmsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTmsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.contrVrsnEffFromDt, dsContrTmsg.contrVrsnEffFromDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.contrVrsnEffThruDt, dsContrTmsg.contrVrsnEffThruDt);
        } else {
            bizMsg.contrVrsnEffFromDt.clear();
            bizMsg.contrVrsnEffThruDt.clear();
        }
    }

    // QC#15875 2016/11/16 Add Start
    /**
     * do Process (NWAL1840_NSAL0110)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840_NSAL0110(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        // get Machine Info
        NWAL1840CommonLogic.getMachineInfoFor0110(bizMsg);
        // get Default Customer Info
        NWAL1840CommonLogic.getCustInfoFor1240(bizMsg);
        // get Default Other Customer Info
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg);
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg);
        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg);

        // get contract End Date
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrPk)) {
            DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrPk, bizMsg.dsContrPk);

            dsContrTmsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTmsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.contrVrsnEffFromDt, dsContrTmsg.contrVrsnEffFromDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.contrVrsnEffThruDt, dsContrTmsg.contrVrsnEffThruDt);
        } else {
            bizMsg.contrVrsnEffFromDt.clear();
            bizMsg.contrVrsnEffThruDt.clear();
        }
    }
    // QC#15875 2016/11/16 Add End

    /**
     * do Process (NWAL1840_NWAL1130)
     * @param bizMsg NWAL1840CMsg
     * @param glblMsg NWAL1840SMsg
     */
    private void doProcess_NWAL1840_NWAL1130(NWAL1840CMsg bizMsg, NWAL1840SMsg glblMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if ("OpenWin_Category".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            doProcess_NWAL1840Scrn00_OnBlur_DeriveFromCategory(bizMsg, glblMsg);
        } else if ("OpenWin_Salesrep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            doProcess_NWAL1840Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg, glblMsg);

        } else if ("OpenWin_FreightTerms".equals(scrEventNm) || "OnBlur_FreightTerms".equals(scrEventNm)) { // QC#10754
            doProcess_NWAL1840Scrn00_OnBlur_FreightTerms(bizMsg);
        }
    }

    /**
     * do Process (NWAL1840_NWAL1600)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840_NWAL1600(NWAL1840CMsg bizMsg) {

        NWAL1840CommonLogicForSalesCredit.copySlsCrFromPopup(bizMsg);

        // Header All Deleted
        if (NWAL1840CommonLogicForSalesCredit.isParamDtAllDeleted(bizMsg.U)) {
            bizMsg.slsRepTocCd.clear();
            bizMsg.slsRepTocNm.clear();
            bizMsg.psnNum.clear(); // S21_NA#7861 Mod
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1840CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }
    }

    /**
     * do Process (NWAL1840_NWAL1660)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840_NWAL1660(NWAL1840CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        // QC#22965 mod Start
//        NWAL1840_ACMsg lineMsg = bizMsg.A.no(checkList.get(0));
//        
//        if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.schdAgmtLineCancFlg_A.getValue())) {
//            NWAL1840CommonLogicForPriceChanges.setScreenItemsFromPrcChange(bizMsg, lineMsg);
//            NWAL1840CommonLogicForPriceChanges.checkPriceRange(lineMsg, NWAL1840CommonLogicForPricing.getBasePrcData(bizMsg, lineMsg));
//        }
        if (checkList.size() == 0) {
            NWAL1840CommonLogicForPriceChanges.setScreenItemsFromPrcChangeForHeader(bizMsg);
       } else {
            NWAL1840_ACMsg lineMsg = bizMsg.A.no(checkList.get(0));
            
            if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.schdAgmtLineCancFlg_A.getValue())) {
                NWAL1840CommonLogicForPriceChanges.setScreenItemsFromPrcChange(bizMsg, lineMsg);
                NWAL1840CommonLogicForPriceChanges.checkPriceRange(lineMsg, NWAL1840CommonLogicForPricing.getBasePrcData(bizMsg, lineMsg));
            }
        }
        // QC#22965 mod End
    }

    /**
     * do Process (NWAL1840_NWAL1860)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840_NWAL1860(NWAL1840CMsg bizMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        // create New Schedule Line
        for (int selectRow : selectedRows) {
            BigDecimal selectLineNum = bizMsg.A.no(selectRow).schdAgmtLineNum_A.getValue();

            int lineMaxQty = 0;
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                if (bizMsg.B.no(i).schdAgmtLineNum_B.getValue().compareTo(selectLineNum) == 0) {

                    //lineMaxQty += bizMsg.B.no(i).ordQty_B.getValueInt();
                    lineMaxQty += bizMsg.B.no(i).ordQty_BS.getValueInt();
                }
            }

            for (int i = 0; i < bizMsg.R.getValidCount(); i++) {

                // Check Line Max
                if (bizMsg.B.getValidCount() == bizMsg.B.length()) {
                    bizMsg.setMessageInfo(NWAM0831E);
                    break;

                }

                int nextQty = bizMsg.A.no(selectRow).otmShipQty_A.getValueInt();

                // schdLine Qty Amount = line Qty
                if (lineMaxQty == bizMsg.A.no(selectRow).schdAllwQty_A.getValueInt()) {
                    break;
                }
                // schdLine Qty Amount > line Qty
                if (lineMaxQty + nextQty > bizMsg.A.no(selectRow).schdAllwQty_A.getValueInt()) {
                    nextQty = bizMsg.A.no(selectRow).schdAllwQty_A.getValueInt() - lineMaxQty;

                }

                lineMaxQty += nextQty;

                int maxSchdNum = NWAL1840CommonLogic.getMaxLineNum(bizMsg.B, selectLineNum);
                int newShcdRowLineNum = NWAL1840CommonLogic.getAddLineRow(bizMsg.B, selectLineNum, maxSchdNum);

                NWAL1840_BCMsg newSchdLine = (NWAL1840_BCMsg) NWAL1840CommonLogic.insertNewLine(bizMsg.B, newShcdRowLineNum);

                ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtNum_B, bizMsg.schdAgmtNum);
                ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtLineNum_B, selectLineNum);
                ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtSchdLineNum_B, new BigDecimal(maxSchdNum + 1));
                ZYPEZDItemValueSetter.setValue(newSchdLine.xxLineNum_B, NWAL1840CommonLogic.getAddSchdLineNum(selectLineNum, bizMsg.B, newShcdRowLineNum, maxSchdNum));
                ZYPEZDItemValueSetter.setValue(newSchdLine.rddDt_B, bizMsg.R.no(i).xxPopPrm_R.getValue());
                ZYPEZDItemValueSetter.setValue(newSchdLine.ordQty_B, new BigDecimal(nextQty));
                ZYPEZDItemValueSetter.setValue(newSchdLine.schdAgmtPlnCancFlg_B, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(newSchdLine.ordQty_BD, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(newSchdLine.ordQty_BS, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(newSchdLine.xxExstFlg_B, ZYPConstant.FLG_OFF_N);
            }
        }

    }

    /**
     * do Process (NWAL1840_NMAL6800)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840_NMAL6800(NWAL1840CMsg bizMsg) {

        doProcess_NWAL1840Scrn00_OnBlur_DeriveFromItem(bizMsg);
    }

    // QC#11578 2016/07/15 Add Start
    /**
     * do Process (NWAL1840_NMAL6760)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840_NMAL6760(NWAL1840CMsg bizMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, NWAL1840CommonLogicForCustomer.cmbnAddr(bizMsg, NEW_LINE));
            Map<String, String> billToInfo = NWAL1840CommonLogicForCustomer.getBillToInfo(bizMsg, bizMsg.billToCustLocCd, MSG_PARAM_BILL_TO_LOC);
            if (billToInfo != null) {
                NWAL1840CommonLogic.deriveDefaultPmtTerm(bizMsg, billToInfo.get("BILL_TO_CUST_CD"));
            }

        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, NWAL1840CommonLogicForCustomer.cmbnAddr(bizMsg, NEW_LINE));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            bizMsg.shipToLocNm_DS.clear(); // 2018/02/23 S21_NA#22399 Add
            NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, false);

        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, NWAL1840CommonLogicForCustomer.cmbnAddr(bizMsg, NEW_LINE));
            NWAL1840CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, false);
        }
    }
    // QC#11578 2016/07/15 Add End
    // QC#16452 Add Start
    /**
     * do Process (NWAL1840_NMAL6770)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840_NMAL6770(NWAL1840CMsg bizMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        bizMsg.D.no(idx).ctacCustRefTpCd_DP.clear();
        String ctacTpCd = bizMsg.xxPopPrm_01.getValue();
        String ctacCustRefTpCd = "";
        String locNum = "";
        if (ZYPCommonFunc.hasValue(bizMsg.D.no(idx).ctacCustRefTpCd_D)) {
            ctacCustRefTpCd = bizMsg.D.no(idx).ctacCustRefTpCd_D.getValue();
        } else {
            ctacCustRefTpCd = CTAC_CUST_REF_TP.SHIP_TO;
        }

        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            locNum = bizMsg.billToCustLocCd.getValue();

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            locNum = bizMsg.shipToCustLocCd.getValue();

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            locNum = bizMsg.soldToCustLocCd.getValue();
        }

        S21SsmEZDResult ssmResult = NWAL1840QueryForSearch.getInstance().getCtacCustRefTp(bizMsg, ctacTpCd, locNum);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).ctacCustRefTpCd_DP, ctacCustRefTpCd);// not null -> protect
        }

    }
    // QC#16452 Add End

    // 2018/07/26 QC#14307 Add Start
    /**
     * do Process (OpenWin_SpecialInstruction)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OpenWin_SpecialInstruction(NWAL1840CMsg bizMsg) {

        // 2018/07/26 QC#14307 Add Start
        NWAL1840CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.billToCustLocCd_BK);

        boolean isDisplay = NWAL1840CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        if (!isDisplay) {
            bizMsg.setMessageInfo(NWZM2274W);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_SI, ZYPConstant.FLG_OFF_N);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg_SI, ZYPConstant.FLG_ON_Y);
        // 2018/07/26 QC#14307 Add End
        // 2018/07/26 QC#26661,26713 Add Start
        String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_02, funcCatgId);
        // 2018/07/26 QC#26661,26713 Add End
        String trxRuleTp = NWAL1840CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_03, trxRuleTp);
    }

    /**
     * do Process (OpenWin_SoldTo)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OpenWin_SoldTo(NWAL1840CMsg bizMsg) {

        NWAL1840CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
    }

    /**
     * do Process (OpenWin_SoldTo)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OpenWin_BillTo(NWAL1840CMsg bizMsg) {

        NWAL1840CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
    }

    /**
     * do Process (OpenWin_SoldTo)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_OpenWin_ShipTo(NWAL1840CMsg bizMsg) {

        NWAL1840CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
    }
    // 2018/07/26 QC#14307 Add End

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /**
     * do Process (TAB_SaHistory)
     * @param bizMsg NWAL1840CMsg
     */
    private void doProcess_NWAL1840Scrn00_TAB_SaHistory(NWAL1840CMsg bizMsg) {

        if (!NWAL1840CommonLogic.checkMandatoryHeaderTab(bizMsg)) {
            return;
        }

        NWAL1840CommonLogicForSaHistory.searchSaHistory(bizMsg);
    }
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
}
