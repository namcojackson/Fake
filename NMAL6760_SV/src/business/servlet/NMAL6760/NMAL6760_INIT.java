/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6760;

import static business.servlet.NMAL6760.constant.NMAL6760Constant.*;
import static business.servlet.NMAL6760.constant.NMAL6760Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6760.NMAL6760CMsg;
import business.servlet.NMAL6760.common.NMAL6760CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL6760_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/06   Fujitsu         N.Sugiura       Update          QC#6248
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19574
 * 2018/06/18   Fujitsu         T.Noguchi       Update          QC#14307
 * 2018/07/10   Fujitsu         T.Noguchi       Update          QC#26661
 *</pre>
 */
public class NMAL6760_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        NMAL6760CMsg bizMsg = new NMAL6760CMsg();

        Object[] params = (Object[]) getArgForSubScreen();
        if (params instanceof Object[]) {

            EZDBStringItem param4 = null;
            EZDBStringItem param17 = null;
            EZDBStringItem param18 = null;
            EZDBStringItem param19 = null;

            if (params.length >= 1) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_01, (EZDBStringItem) params[0]);
            }
            if (params.length >= 2) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_01, (EZDBStringItem) params[1]);
            }
            if (params.length >= 3) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_01, (EZDBStringItem) params[2]);
            }
            if (params.length >= 4) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsLocNm_01, (EZDBStringItem) params[3]);
            }
            if (params.length >= 5) {
                param4 = (EZDBStringItem) params[4]; // First Line Address
            }
            if (params.length >= 6) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_01, (EZDBStringItem) params[5]);
            }
            if (params.length >= 7) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_DP, (EZDBStringItem) params[6]);
            }
            if (params.length >= 8) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_01, (EZDBStringItem) params[7]);
            }
            if (params.length >= 9) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAcctSrchDplyRelnCd_D4, (EZDBStringItem) params[8]);
            }
            if (params.length >= 10) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAcctSrchModeCd_D1, (EZDBStringItem) params[9]);
            }
            if (params.length >= 11) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_01, (EZDBStringItem) params[10]);
            }
            if (params.length >= 12) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAcctSrchStsCd_D2, (EZDBStringItem) params[11]);
            }
            if (params.length >= 13) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxAcctSrchDplyHrchCd_D3, (EZDBStringItem) params[12]);
            }
            if (params.length >= 16) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_01, (EZDBStringItem) params[15]);
            }
            if (params.length >= 17) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_01, (EZDBStringItem) params[16]);
            }
            if (params.length >= 18) {
                param17 = (EZDBStringItem) params[17]; // Second Line Address
            }
            if (params.length >= 19) {
                param18 = (EZDBStringItem) params[18]; // Third Line Address
            }
            if (params.length >= 20) {
                param19 = (EZDBStringItem) params[19]; // Fourth Line Address
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_HC, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_AC, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_SC, ZYPConstant.FLG_ON_Y);

            if (params.length >= 25) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_HC, (EZDBStringItem) params[24]);
            }
            if (params.length >= 26) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_AC, (EZDBStringItem) params[25]);
            }
            if (params.length >= 27) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_SC, (EZDBStringItem) params[26]);
            }

            if (params.length >= 28) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_02, (EZDBStringItem) params[27]); // Display Inactive Location Flag
            }
            if (params.length >= 29) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_03, (EZDBStringItem) params[28]); // Display Inactive Bill to /Ship to Flag 
            }
            if (params.length >= 30) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_02, (EZDBStringItem) params[29]); // Display Inactive Location UI Control
            }
            if (params.length >= 31) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_03, (EZDBStringItem) params[30]); // Display Inactive Bill to /Ship to UI Control 
            }
            if (params.length >= 34) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_CT, (EZDBStringItem) params[33]); // Display Inactive Control - Category
            }
            if (params.length >= 35) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctTpCd_DP, (EZDBStringItem) params[34]); // Category
            }
            if (params.length >= 36) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_RA, (EZDBStringItem) params[35]); // Display Inactive Control - Relation Account
            }

            // 2018/06/18 QC#14307 Add Start
            if (params.length >= 37) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_SP, (EZDBStringItem) params[36]); // Display Control Customer Special Instruction
            }
            if (params.length >= 38) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsTrxRuleTpCd_SP, (EZDBStringItem) params[37]); // Transaction Type
            }
            if (params.length >= 39) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsBizAreaCd_SP, (EZDBStringItem) params[38]); // Business Area
            }
            if (params.length >= 40) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.funcMstrId_SP, (EZDBStringItem) params[39]); // Function ID
            }
            if (params.length >= 41) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.funcMstrIdDescTxt_SP, (EZDBStringItem) params[40]); // Function Category ID
            }
            // 2018/06/18 QC#14307 Add End

            // 2018/07/10 QC#26661 Add Start
            if (params.length >= 42) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd_SP, (EZDBStringItem) params[41]); // Line of Business Code
            }
            // 2018/07/10 QC#26661 Add End

            // Set Line Address
            String allLineAddr = "";
            if (ZYPCommonFunc.hasValue(param4)) {
                allLineAddr = param4.getValue();
            }
            if (ZYPCommonFunc.hasValue(param17)) {
                allLineAddr = S21StringUtil.concatStrings(allLineAddr, " ", param17.getValue());
            }
            if (ZYPCommonFunc.hasValue(param18)) {
                allLineAddr = S21StringUtil.concatStrings(allLineAddr, " ", param18.getValue());
            }
            if (ZYPCommonFunc.hasValue(param19)) {
                allLineAddr = S21StringUtil.concatStrings(allLineAddr, " ", param19.getValue());
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxAllLineAddr_01, allLineAddr);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;
        NMAL6760CMsg bizMsg = (NMAL6760CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6760CommonLogic.initCmnBtnProp(this);
        NMAL6760CommonLogic.controlDetailScreenFields(this, scrnMsg);
        scrnMsg.dsAcctGrpDescTxt_DP.setInputProtected(true);
        scrnMsg.xxAcctSrchModeCd_D1.setInputProtected(true);

        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_HC) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_HC.getValue())) {
            scrnMsg.xxAcctSrchDplyHrchCd_D3.setInputProtected(true);
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_AC) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_AC.getValue())) {
            scrnMsg.dsAcctNum_01.setInputProtected(true);
            scrnMsg.dsAcctNum_L1.clear();
        } else {
            scrnMsg.dsAcctNum_L1.setValue(ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_SC) && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_SC.getValue())) {
            scrnMsg.xxAcctSrchStsCd_D2.setInputProtected(true);
        }

        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_01);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL6760BMsg scrnMsg = (NMAL6760BMsg) bMsg;

        scrnMsg.xxAcctSrchModeCd_D1.setNameForMessage("Search Mode");
        scrnMsg.xxAcctSrchStsCd_D2.setNameForMessage("Status");
        scrnMsg.xxChkBox_01.setNameForMessage("Display Addresses");
        scrnMsg.xxAcctSrchDplyHrchCd_D3.setNameForMessage("Display Hierarchy Account");

        scrnMsg.dsAcctNm_01.setNameForMessage("Account Name");
        scrnMsg.xxAllLineAddr_01.setNameForMessage("Address");
        scrnMsg.ctyAddr_01.setNameForMessage("City");
        scrnMsg.stCd_DP.setNameForMessage("State");
        scrnMsg.postCd_01.setNameForMessage("Postal Code");
        scrnMsg.dsAcctNum_01.setNameForMessage("Account#");
        scrnMsg.locNum_01.setNameForMessage("Location#");
        scrnMsg.dbaNm_01.setNameForMessage("DBA Name");
        scrnMsg.dsAcctLegalNm_01.setNameForMessage("Account Legal Name");
        scrnMsg.dsAcctGrpCd_DP.setNameForMessage("Account Group Code");
        scrnMsg.dsAcctClsCd_DP.setNameForMessage("Account Classification");
        scrnMsg.dsLocNm_01.setNameForMessage("Location Name");

        scrnMsg.dsAcctDunsNum_01.setNameForMessage("DUNS#");
        scrnMsg.dsUltDunsNum_01.setNameForMessage("UDUNS#");
        scrnMsg.dsCustSicDescTxt_01.setNameForMessage("Industry");

        scrnMsg.ctacPsnLastNm_01.setNameForMessage("Contact Last Name");
        scrnMsg.ctacPsnFirstNm_01.setNameForMessage("Contact First Name");
        scrnMsg.ctacPsnTelNum_01.setNameForMessage("Contact Phone#");
        // START 2017/08/07 T.Tsuchida [QC#19574,ADD]
        scrnMsg.ctacPsnEmlAddr_01.setNameForMessage("Contact Email Address#");
        // END 2017/08/07 T.Tsuchida [QC#19574,ADD]
        scrnMsg.dsXrefAcctTpCd_DP.setNameForMessage("Cross Reference Category");
        scrnMsg.dsXrefAcctCd_01.setNameForMessage("Cross Reference#");
        scrnMsg.billToCustCd_01.setNameForMessage("Bill To Code");
        scrnMsg.shipToCustCd_01.setNameForMessage("Ship To Code");

        scrnMsg.dsXtrnlRefTxt_01.setNameForMessage("External Reference#");
        scrnMsg.dsAcctTpCd_DP.setNameForMessage("Category");
        scrnMsg.dsAcctItrlFlg_C1.setNameForMessage("Internal / External");

        scrnMsg.xxAcctSrchDplyRelnCd_D4.setNameForMessage("Display Related Accounts");
        scrnMsg.dsAcctNm_RT.setNameForMessage("Related Account Name");
        scrnMsg.dsAcctNum_RT.setNameForMessage("Related Account#");
        scrnMsg.xxAllLineAddr_RT.setNameForMessage("Related Address");

        scrnMsg.xxChkBox_02.setNameForMessage("Display Inactive Location");
        scrnMsg.xxChkBox_03.setNameForMessage("Display Inactive Bill to / Ship to");
    }
}
