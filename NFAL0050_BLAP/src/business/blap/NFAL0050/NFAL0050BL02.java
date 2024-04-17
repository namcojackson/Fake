/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0050;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0050.common.NFAL0050CommonLogic;
import business.blap.NFAL0050.constant.NFAL0050Constant;
import business.db.COA_ACCTTMsg;
import business.db.COA_ACCTTMsgArray;
import business.db.COA_AFFLTMsg;
import business.db.COA_AFFLTMsgArray;
import business.db.COA_BRTMsg;
import business.db.COA_BRTMsgArray;
import business.db.COA_CCTMsg;
import business.db.COA_CCTMsgArray;
import business.db.COA_PRODTMsg;
import business.db.COA_PRODTMsgArray;
import business.db.COA_PROJTMsg;
import business.db.COA_PROJTMsgArray;
import business.db.COA_SEG_LKUP_TPTMsg;
import business.db.COA_SEG_LKUP_TPTMsgArray;
import business.db.ELIG_COA_SEG_PTRNTMsg;
import business.db.ELIG_COA_SEG_PTRNTMsgArray;
import business.db.JRNL_CATGTMsg;
import business.db.JRNL_CATGTMsgArray;
import business.db.SYS_SRCTMsg;
import business.db.SYS_SRCTMsgArray;
import business.db.TRXTMsg;
import business.db.TRXTMsgArray;
import business.db.TRX_RSNTMsg;
import business.db.TRX_RSNTMsgArray;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

/**
 * Class name: NFAL0050BL02 <dd>The class explanation: Business
 * processing for Component ID : NFAL0050BL02 <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0050BL02 extends S21BusinessHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0050_INIT".equals(screenAplID)) {
                doProcess_NFAL0050_INIT(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_SearchAjeIdBtn".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_SearchAjeIdBtn(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_SYS_SRC_CD".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_SYS_SRC_CD(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_TRX_CD".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_TRX_CD(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_TRX_RSN_CD".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_TRX_RSN_CD(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_IND_TP_NM_01".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_01(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_ACTL_NM_01".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_01(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_IND_TP_NM_02".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_02(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_ACTL_NM_02".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_02(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_IND_TP_NM_03".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_03(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_OnChange_ACTL_NM_03".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_03(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_AddRowBtn".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_AddRowBtn(cMsg, sMsg);
            } else if ("NFAL0050Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NFAL0050_NFAL9020".equals(screenAplID)) {
                doProcess_NFAL0050_NFAL9020(cMsg, sMsg);
                // ---- start add 2016/01/22
            } else if ("NFAL0050Scrn00_CatgSearchBtn".equals(screenAplID)) {
                doProcess_NFAL0050Scrn00_CatgSearchBtn(cMsg, sMsg);
                // ---- end 2016/01/22
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_CatgSearchBtn <dd>The
     * method explanation: Business processing for Category Search
     * Button
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_CatgSearchBtn(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CatgSearchBtnt================================START", null);
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        // existance check
        if (!common.jrnlCatgSearch(bizMsg)) {
            bizMsg.jrnlCatgCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.jrnlCatgCd.getValue(), "Journal Category Code" });
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CatgSearchBtnt================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050_INIT <dd>The method
     * explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050_INIT================================START", null);

        common.initListBoxes(cMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0050_INIT================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_CMN_Reset <dd>The method
     * explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CMN_Reset================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        threeCodeValid(bizMsg);
        setActlNm01ListBox(bizMsg);
        setActlNm02ListBox(bizMsg);
        setActlNm03ListBox(bizMsg);

        common.setSearchedResult(bizMsg, RESET);

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_CMN_Reset================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_AddRowBtn <dd>The method
     * explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_AddRowBtn(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_AddRowBtn================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        // --- start add Br 2016/01/22
        if (!bizMsg.ajeCoaBrCd.isClear() && !brCdExist(bizMsg)) {
            bizMsg.ajeCoaBrCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaCcCd.getValue(), "a Branch Code" });
        }
        if (!bizMsg.ajeCoaCcCd.isClear() && !ccCdExist(bizMsg)) {
            bizMsg.ajeCoaCcCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaCcCd.getValue(), "a Cost Center Code" });
        }
        if (!bizMsg.ajeCoaAcctCd.isClear() && !acctCdExist(bizMsg)) {
            bizMsg.ajeCoaAcctCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaAcctCd.getValue(), "an Account Code" });
        }
        if (!bizMsg.ajeCoaProdCd.isClear() && !prodCdExist(bizMsg)) {
            bizMsg.ajeCoaProdCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaProdCd.getValue(), "a Product Code" });
        }
        if (!bizMsg.ajeCoaAfflCd.isClear() && !afflCdExist(bizMsg)) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // bizMsg.ajeCoaAfflCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaAfflCd.getValue(), "an Affiliate Code" });
            bizMsg.ajeCoaAfflCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaAfflCd.getValue(), "an Intercompany Code" });
            // END 2016/11/25 J.Kim [QC#16240,MOD]
        }
        if (!bizMsg.ajeCoaProjCd.isClear() && !projCdExist(bizMsg)) {
            // START 2016/11/25 J.Kim [QC#16240,MOD]
            // bizMsg.ajeCoaProjCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaProjCd.getValue(), "a Project Code" });
            bizMsg.ajeCoaProjCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.ajeCoaProjCd.getValue(), "a Merchandise Type" });
            // END 2016/11/25 J.Kim [QC#16240,MOD]
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_AddRowBtn================================END", null);
    }

    private boolean ccCdExist(NFAL0050CMsg bizMsg) {

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // COA_CC_CD = ?coaCcCd01?
        // ORDER BY
        // COA_CC_CD ASC
        COA_CCTMsg tMsgCoaCc = new COA_CCTMsg();
        tMsgCoaCc.setSQLID("801");
        tMsgCoaCc.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsgCoaCc.setConditionValue("coaCcCd01", bizMsg.ajeCoaCcCd.getValue());

        COA_CCTMsgArray tMsgCoaCcArr = (COA_CCTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaCc);
        if (tMsgCoaCcArr != null && tMsgCoaCcArr.length() > 0) {
            return true;
        }

        // ---- start mod 2016/01/22
        /*
         * if (existInEligCoaSegPtrn(COA_CC_CD,
         * bizMsg.ajeCoaCcCd.getValue())) { // Exists in
         * ELIG_COA_SEG_PTRN // such as #WH, @TOC return true; }
         */
        return chkWithLkupTp(bizMsg.ajeCoaCcCd.getValue());
        // ---- end mod 2016/01/22
    }

    private boolean acctCdExist(NFAL0050CMsg bizMsg) {

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // COA_ACCT_CD = ?coaAcctCd01?
        // ORDER BY COA_ACCT_CD ASC

        // if first character of account cd is @ then, check with
        // COA_SEG_LKUP_TP
        if (bizMsg.ajeCoaAcctCd.getValue().substring(0, 1).equals("@")) {
            COA_SEG_LKUP_TPTMsg tMsg = new COA_SEG_LKUP_TPTMsg();

            tMsg.setSQLID("801");
            tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            tMsg.setConditionValue("coaSegLkupTpCd01", bizMsg.ajeCoaAcctCd.getValue());

            COA_SEG_LKUP_TPTMsgArray resultArrayCoaSegLkup = (COA_SEG_LKUP_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

            if (resultArrayCoaSegLkup == null || resultArrayCoaSegLkup.length() == 0) {
                return false;
            } else {
                bizMsg.ajeLineIdxDescTxt.setValue(((COA_SEG_LKUP_TPTMsg) resultArrayCoaSegLkup.get(0)).coaSegLkupTpNm.getValue());
                return true;
            }

        } else {

            COA_ACCTTMsg tMsg = new COA_ACCTTMsg();
            tMsg.setSQLID("801");
            tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            tMsg.setConditionValue("coaAcctCd01", bizMsg.ajeCoaAcctCd.getValue());

            COA_ACCTTMsgArray resultArrayAcctCcy = (COA_ACCTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

            if (resultArrayAcctCcy != null && resultArrayAcctCcy.length() > 0) {
                bizMsg.ajeLineIdxDescTxt.setValue(((COA_ACCTTMsg) resultArrayAcctCcy.get(0)).coaAcctNm.getValue());
                return true;
            } else {
                return false;
            }

        }

        // ---- start mod 2016/01/22
        /*
         * if (existInEligCoaSegPtrn(COA_ACCT_CD,
         * bizMsg.ajeCoaAcctCd.getValue())) { // Exists in
         * ELIG_COA_SEG_PTRN // such as #WH, @TOC return true; }
         */

        // return true;
        // --- end 2016/01/22
    }

    // ---- start add 2016/01/22
    private boolean chkWithLkupTp(String val) {
        COA_SEG_LKUP_TPTMsg tMsg = new COA_SEG_LKUP_TPTMsg();

        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("coaSegLkupTpCd01", val);

        COA_SEG_LKUP_TPTMsgArray resultArrayCoaSegLkup = (COA_SEG_LKUP_TPTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (resultArrayCoaSegLkup == null || resultArrayCoaSegLkup.length() == 0) {
            return false;
        }
        return true;

    }

    // ---- end 2016/01/22

    private boolean prodCdExist(NFAL0050CMsg bizMsg) {

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // COA_PROD_CD = ?coaProdCd01?
        COA_PRODTMsg tMsg = new COA_PRODTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("coaProdCd01", bizMsg.ajeCoaProdCd.getValue());

        COA_PRODTMsgArray resultArrayAcctCcy = (COA_PRODTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (resultArrayAcctCcy != null && resultArrayAcctCcy.length() > 0) {
            return true;
        }

        // ---- start mod 2016/01/22
        /*
         * if (existInEligCoaSegPtrn(COA_PROD_CD,
         * bizMsg.ajeCoaProdCd.getValue())) { // Exists in
         * ELIG_COA_SEG_PTRN // such as #WH, @TOC return true; }
         */
        return chkWithLkupTp(bizMsg.ajeCoaProdCd.getValue());
        // ---- end 2016/01/22

    }

    private boolean afflCdExist(NFAL0050CMsg bizMsg) {

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // COA_AFFL_CD = ?coaAfflCd01?
        // ORDER BY
        // COA_AFFL_CD ASC
        COA_AFFLTMsg tMsg = new COA_AFFLTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("coaAfflCd01", bizMsg.ajeCoaAfflCd.getValue());

        COA_AFFLTMsgArray resultArrayAcctCcy = (COA_AFFLTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (resultArrayAcctCcy != null && resultArrayAcctCcy.length() > 0) {
            return true;
        }

        // ---- start mod 2016/01/22
        /*
         * if (existInEligCoaSegPtrn(COA_AFFL_CD,
         * bizMsg.ajeCoaAfflCd.getValue())) { // Exists in
         * ELIG_COA_SEG_PTRN // such as #WH, @TOC return true; }
         */
        return chkWithLkupTp(bizMsg.ajeCoaAfflCd.getValue());
        // ---- end 2016/01/22

    }

    private boolean projCdExist(NFAL0050CMsg bizMsg) {

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // COA_PROJ_CD = ?coaProjCd01?
        // ORDER BY
        // COA_PROJ_CD ASC
        COA_PROJTMsg tMsg = new COA_PROJTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("coaProjCd01", bizMsg.ajeCoaProjCd.getValue());

        COA_PROJTMsgArray resultArrayAcctCcy = (COA_PROJTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (resultArrayAcctCcy != null && resultArrayAcctCcy.length() > 0) {
            return true;
        }

        // ---- start mod 2016/01/22
        /*
         * if (existInEligCoaSegPtrn(COA_PROJ_CD,
         * bizMsg.ajeCoaProjCd.getValue())) { // Exists in
         * ELIG_COA_SEG_PTRN // such as #WH, @TOC return true; }
         */
        return chkWithLkupTp(bizMsg.ajeCoaProjCd.getValue());
        // ---- end 2016/01/22

    }

    private boolean brCdExist(NFAL0050CMsg bizMsg) {

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // COA_PROJ_CD = ?coaProjCd01?
        // ORDER BY
        // COA_PROJ_CD ASC
        COA_BRTMsg tMsg = new COA_BRTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("coaBrCd01", bizMsg.ajeCoaBrCd.getValue());

        COA_BRTMsgArray rsltArry = (COA_BRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (rsltArry != null && rsltArry.length() > 0) {
            return true;
        }

        return chkWithLkupTp(bizMsg.ajeCoaBrCd.getValue());

    }

    private boolean existInEligCoaSegPtrn(String segName, String code) {

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // EZCANCELFLAG = '0' AND
        // ELIG_COA_SEG_PTRN_CD = ?eligCoaSegPtrnCd01? AND
        // COA_SEG_LKUP_TP_CD = ?coaSegLkupTpCd01?
        ELIG_COA_SEG_PTRNTMsg tMsgEligCoaSegPtrn = new ELIG_COA_SEG_PTRNTMsg();
        tMsgEligCoaSegPtrn.setSQLID("802");
        tMsgEligCoaSegPtrn.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsgEligCoaSegPtrn.setConditionValue("eligCoaSegPtrnCd01", segName);
        tMsgEligCoaSegPtrn.setConditionValue("coaSegLkupTpCd01", code);
        ELIG_COA_SEG_PTRNTMsgArray tMsgEligCoaSegPtrnArr = (ELIG_COA_SEG_PTRNTMsgArray) EZDTBLAccessor.findByCondition(tMsgEligCoaSegPtrn);

        if (tMsgEligCoaSegPtrnArr != null && tMsgEligCoaSegPtrnArr.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_SearchAjeIdBtn <dd>The
     * method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_SearchAjeIdBtn(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_SearchAjeIdBtn================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        // Validation for SysSrc, Trx, TrxRsn
        if (!threeCodeValid(bizMsg)) {
            return;
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);

        // if (bizMsg.eventId_P.getValue().equals(EVT_PASTE) &&
        // numberOfAjePtrn > 0) {
        // // NFAM0070E=0, @ already exists in @
        // bizMsg.ajeId.setErrorInfo(1, "NFAM0070E", new String[]
        // {bizMsg.ajeId.getValue(), "AJE Pattern" });
        // clearIndTp(bizMsg);
        // return;
        // }

        if (numberOfAjePtrn == 0) {
            // There is no Aje Ptrn
            // Default val will be set
            setIndTpDefaultVal(bizMsg);
            bizMsg.setMessageInfo("NFAM0042W", new String[] {AJE_ID });
            bizMsg.xxSetFlg.setValue(DEFAULT);
        } else if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                setActlNm01ListBox(bizMsg);
                setActlNm02ListBox(bizMsg);
                setActlNm03ListBox(bizMsg);

                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
            }
        } else {
            clearIndTp(bizMsg);
            setMessageForResultCount(bizMsg, numberOfAjePtrn);
            setMessageForIndType(bizMsg, MSG_SELECT_IND_TP_OR_POPUP);
        }

        if (bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            setIndTpDefaultVal(bizMsg);
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_SearchAjeIdBtn================================END", null);
    }

    private boolean threeCodeValid(NFAL0050CMsg bizMsg) {

        StringTokenizer tk = new StringTokenizer(bizMsg.ajeId.getValue(), HYPHEN);

        boolean valid = true;
        for (int i = 0; tk.hasMoreTokens(); i++) {
            String code = tk.nextToken();
            if (i == 0) {
                bizMsg.sysSrcNm.clear();
                if (!isValidSysSrcCd(bizMsg, code)) {
                    valid = false;
                    bizMsg.sysSrcCd_3.setErrorInfo(1, "NFAM0044E", new String[] {SYS_SRC_CD });
                } else {
                    bizMsg.sysSrcCd_3.setValue(code);
                    common.setSysSrcNm(bizMsg);
                }
            } else if (i == 1) {
                bizMsg.trxNm.clear();
                if (!isValidTrxCd(bizMsg, code)) {
                    valid = false;
                    bizMsg.trxCd_3.setErrorInfo(1, "NFAM0044E", new String[] {TRX_CD });
                } else {
                    bizMsg.trxCd_3.setValue(code);
                    // Create Trx Rsn code list box
                    common.getTrxRsnListBox(bizMsg);
                    common.setTrxNm(bizMsg);
                }
            } else {
                bizMsg.trxRsnNm.clear();
                if (!isValidTrxRsnCd(bizMsg, code)) {
                    valid = false;
                    bizMsg.trxRsnCd_3.setErrorInfo(1, "NFAM0044E", new String[] {TRX_RSN_CD });
                } else {
                    bizMsg.trxRsnCd_3.setValue(code);
                    common.setTrxRsnNm(bizMsg);
                }
            }
        }
        return valid;
    }

    private boolean isValidSysSrcCd(NFAL0050CMsg bizMsg, String code) {

        for (int i = 0; i < bizMsg.sysSrcCd_1.length(); i++) {
            if (bizMsg.sysSrcCd_1.no(i).getValue().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidTrxCd(NFAL0050CMsg bizMsg, String code) {

        for (int i = 0; i < bizMsg.trxCd_1.length(); i++) {
            if (bizMsg.trxCd_1.no(i).getValue().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidTrxRsnCd(NFAL0050CMsg bizMsg, String code) {

        for (int i = 0; i < bizMsg.trxRsnCd_1.length(); i++) {
            if (bizMsg.trxRsnCd_1.no(i).getValue().equals(code)) {
                return true;
            }
        }
        return false;
    }

    private void setIndTpDefaultVal(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnIndTpCd_1V.setValue(DEFAULT_VAL_CD_3);
        bizMsg.ajePtrnIndTpCd_2V.setValue(DEFAULT_VAL_CD_3);
        bizMsg.ajePtrnIndTpCd_3V.setValue(DEFAULT_VAL_CD_3);

        setActlNm01ListBox(bizMsg);
        setActlNm02ListBox(bizMsg);
        setActlNm03ListBox(bizMsg);

        bizMsg.ajePtrnActlCd_1V.setValue(DEFAULT_VAL_CD_2);
        bizMsg.ajePtrnActlCd_2V.setValue(DEFAULT_VAL_CD_2);
        bizMsg.ajePtrnActlCd_3V.setValue(DEFAULT_VAL_CD_2);
    }

    private void clearIndTp(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnIndTpCd_1V.clear();
        bizMsg.ajePtrnIndTpCd_2V.clear();
        bizMsg.ajePtrnIndTpCd_3V.clear();

        bizMsg.ajePtrnActlCd_1V.clear();
        bizMsg.ajePtrnActlCd_2V.clear();
        bizMsg.ajePtrnActlCd_3V.clear();
    }

    // private int getNumberOfAjePtrn(NFAL0050CMsg bizMsg) {
    //
    // S21SsmEZDResult ssmResult =
    // NFAL0050Query.getInstance().getAjeIdsGroupByIndTp(bizMsg);
    // common.clearFields(bizMsg);
    //
    // int resultCnt = 0;
    // if (ssmResult.isCodeNormal()) {
    // List resultList = (List) ssmResult.getResultObject();
    //
    // resultCnt = ssmResult.getQueryResultCount();
    // if (resultCnt == 1) {
    // Map map = (Map) resultList.get(0);
    // bizMsg.ajePtrnIndTpCd_1V.setValue((String)
    // map.get("AJE_PTRN_IND_TP_CD_01"));
    // bizMsg.ajePtrnActlCd_1V.setValue((String)
    // map.get("AJE_PTRN_ACTL_CD_01"));
    // bizMsg.ajePtrnIndTpCd_2V.setValue((String)
    // map.get("AJE_PTRN_IND_TP_CD_02"));
    // bizMsg.ajePtrnActlCd_2V.setValue((String)
    // map.get("AJE_PTRN_ACTL_CD_02"));
    // bizMsg.ajePtrnIndTpCd_3V.setValue((String)
    // map.get("AJE_PTRN_IND_TP_CD_03"));
    // bizMsg.ajePtrnActlCd_3V.setValue((String)
    // map.get("AJE_PTRN_ACTL_CD_03"));
    // // NFAM0051I=0,Showing @ of @ record for @
    // // bizMsg.setMessageInfo("NFAM0051I", new String[]
    // // {"1", Integer.toString(resultCnt), "AJE ID:" +
    // // bizMsg.ajeId.getValue() });
    // }
    // } else {
    // bizMsg.ezUpTime.clear();
    // bizMsg.ezUpTimeZone.clear();
    // }
    // bizMsg.xxTotCnt_P.setValue(new BigDecimal(resultCnt));
    // return resultCnt;
    // }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_SYS_SRC_CD <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_SYS_SRC_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_SYS_SRC_CD================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        if (bizMsg.sysSrcCd_3.isClear()) {
            return;
        }

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // SYS_SRC_CD = ?sysSrcCd01?
        SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("sysSrcCd01", bizMsg.sysSrcCd_3.getValue());
        SYS_SRCTMsgArray tMsgArr = (SYS_SRCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.sysSrcNm.setValue(tMsgArr.no(0).sysSrcNm.getValue());
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_SYS_SRC_CD================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_TRX_CD <dd>The
     * method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_TRX_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_TRX_CD================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        if (bizMsg.trxCd_3.isClear()) {
            return;
        }

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        TRXTMsg tMsg = new TRXTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRXTMsgArray tMsgArr = (TRXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.trxNm.setValue(tMsgArr.no(0).trxNm.getValue());
        }

        getTrxRsnListBox(cMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_TRX_CD================================END", null);
    }

    private void getTrxRsnListBox(EZDCMsg cMsg) {

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.trxRsnCd_1.clear();
        bizMsg.trxRsnCd_2.clear();
        bizMsg.trxRsnCd_3.clear();
        bizMsg.trxRsnNm.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        // ORDER BY
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxRsnCd_1.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
                bizMsg.trxRsnCd_2.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
            }
        }
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_TRX_RSN_CD <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_TRX_RSN_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_TRX_RSN_CD================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        bizMsg.trxRsnNm.clear();

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01? AND
        // TRX_RSN_CD = ?trxRsnCd01?
        // ORDER BY
        // TRX_CD ASC,
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        tMsg.setConditionValue("trxRsnCd01", bizMsg.trxRsnCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.trxRsnNm.setValue(tMsgArr.no(0).trxRsnNm.getValue());
        }
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_TRX_RSN_CD================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_01 <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_01(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_01================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        clearMessageOnScrn(bizMsg);

        // Set Code Val list box for Type 1
        setActlNm01ListBoxOnChange(bizMsg);

        // Check if any same combination of
        // Indicator Type exists in different
        // order
        if (common.fourIndTpSelected(bizMsg)) {
            if (samePtrnExistInFourPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        } else if (common.sixIndTpSelected(bizMsg)) {
            if (samePtrnExistInSixPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);
        if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                // Selected Indicator type(s)
                // has only ONE result,
                // then showing the AJE pattern
                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
                setActlNm02ListBoxOnChange(bizMsg);
                setActlNm03ListBoxOnChange(bizMsg);
            }
        }
        // Set Message on Screen
        setMessageForResultCount(bizMsg, numberOfAjePtrn);
        setMessageForIndType(bizMsg, MSG_IN + getIndTpNm01(bizMsg, bizMsg.ajePtrnIndTpCd_1V.getValue()));

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_01================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_01 <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_01(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_01================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        clearMessageOnScrn(bizMsg);

        // Check if any same combination of
        // Indicator Type exists in different
        // order
        if (common.fourIndTpSelected(bizMsg)) {
            if (samePtrnExistInFourPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        } else if (common.sixIndTpSelected(bizMsg)) {
            if (samePtrnExistInSixPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);

        if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {

                // Selected Indicator type(s)
                // has only ONE result,
                // then showing the AJE pattern
                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
                setActlNm02ListBoxOnChange(bizMsg);
                setActlNm03ListBoxOnChange(bizMsg);
            }
        }
        // Set Message on Screen
        setMessageForResultCount(bizMsg, numberOfAjePtrn);
        setMessageForIndType(bizMsg, MSG_IN + MSG_THIS_COMB);

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_01================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_02 <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_02(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_02================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        clearMessageOnScrn(bizMsg);

        // Set Code Val list box for Type 2
        setActlNm02ListBoxOnChange(bizMsg);

        // Check if any same combination of
        // Indicator Type exists in different
        // order
        if (common.fourIndTpSelected(bizMsg)) {
            if (samePtrnExistInFourPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        } else if (common.sixIndTpSelected(bizMsg)) {
            if (samePtrnExistInSixPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);
        if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                // Selected Indicator type(s)
                // has only ONE result,
                // then showing the AJE pattern
                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
                setActlNm03ListBoxOnChange(bizMsg);
            }
        }
        // Set Message on Screen
        setMessageForResultCount(bizMsg, numberOfAjePtrn);
        setMessageForIndType(bizMsg, MSG_IN + getIndTpNm02(bizMsg, bizMsg.ajePtrnIndTpCd_2V.getValue()));

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_02================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_02 <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_02(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_02================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        clearMessageOnScrn(bizMsg);

        // Check if any same combination of
        // Indicator Type exists in different
        // order
        if (common.fourIndTpSelected(bizMsg)) {
            if (samePtrnExistInFourPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        } else if (common.sixIndTpSelected(bizMsg)) {
            if (samePtrnExistInSixPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);
        if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                // Selected Indicator type(s)
                // has only ONE result,
                // then showing the AJE pattern
                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
                setActlNm03ListBoxOnChange(bizMsg);
            }
        }
        // Set Message on Screen
        setMessageForResultCount(bizMsg, numberOfAjePtrn);
        // setMessageForIndType(bizMsg, MSG_IN +
        // getActlNm02(bizMsg,
        // bizMsg.ajePtrnActlCd02_3.getValue()));
        setMessageForIndType(bizMsg, MSG_IN + MSG_THIS_COMB);

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_02================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_03 <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_03(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_03================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        clearMessageOnScrn(bizMsg);

        // Set Code Val list box for Type 3
        setActlNm03ListBoxOnChange(bizMsg);

        // Check if any same combination of
        // Indicator Type exists in different
        // order
        if (common.fourIndTpSelected(bizMsg)) {
            if (samePtrnExistInFourPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        } else if (common.sixIndTpSelected(bizMsg)) {
            if (samePtrnExistInSixPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);
        if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                // Selected Indicator type(s)
                // has only ONE result,
                // then showing the AJE pattern
                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
            }
        }
        // Set Message on Screen
        setMessageForResultCount(bizMsg, numberOfAjePtrn);
        setMessageForIndType(bizMsg, MSG_IN + getIndTpNm03(bizMsg, bizMsg.ajePtrnIndTpCd_3V.getValue()));

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_IND_TP_NM_03================================END", null);
    }

    /**
     * Method name: doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_03 <dd>
     * The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_03(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_03================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;
        clearMessageOnScrn(bizMsg);

        // Check if any same combination of
        // Indicator Type exists in different
        // order
        if (common.fourIndTpSelected(bizMsg)) {
            if (samePtrnExistInFourPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        } else if (common.sixIndTpSelected(bizMsg)) {
            if (samePtrnExistInSixPtrn(bizMsg)) {
                bizMsg.setMessageInfo("NFAM0061E", new String[] {"Pattern" });
                return;
            }
        }

        int numberOfAjePtrn = common.getNumberOfAjePtrn(bizMsg);
        if (numberOfAjePtrn == 1) {
            if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
                // Selected Indicator type(s)
                // has only ONE result,
                // then showing the AJE pattern
                common.setResetFields(bizMsg);
                common.setSearchedResult(bizMsg, NOT_RESET);
            }
        }
        // Set Message on Screen
        setMessageForResultCount(bizMsg, numberOfAjePtrn);
        // setMessageForIndType(bizMsg, MSG_IN +
        // getActlNm03(bizMsg,
        // bizMsg.ajePtrnActlCd03_3.getValue()));
        setMessageForIndType(bizMsg, MSG_IN + MSG_THIS_COMB);

        EZDDebugOutput.println(1, "doProcess_NFAL0050Scrn00_OnChange_ACTL_NM_03================================END", null);
    }

    private String getIndTpNm01(NFAL0050CMsg bizMsg, String code) {
        // Get Indicator Type Name based on code
        for (int i = 0; i < bizMsg.ajePtrnIndTpCd_1C.length(); i++) {
            if (bizMsg.ajePtrnIndTpCd_1C.no(i).getValue().equals(code)) {
                return bizMsg.ajePtrnIndTpNm_1D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getIndTpNm02(NFAL0050CMsg bizMsg, String code) {
        // Get Indicator Type Name based on code
        for (int i = 0; i < bizMsg.ajePtrnIndTpCd_2C.length(); i++) {
            if (bizMsg.ajePtrnIndTpCd_2C.no(i).getValue().equals(code)) {
                return bizMsg.ajePtrnIndTpNm_2D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private String getIndTpNm03(NFAL0050CMsg bizMsg, String code) {
        // Get Indicator Type Name based on code
        for (int i = 0; i < bizMsg.ajePtrnIndTpCd_3C.length(); i++) {
            if (bizMsg.ajePtrnIndTpCd_3C.no(i).getValue().equals(code)) {
                return bizMsg.ajePtrnIndTpNm_3D.no(i).getValue();
            }
        }
        return BLANK;
    }

    private void setMessageForResultCount(NFAL0050CMsg bizMsg, int count) {

        if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            if (count == 0) {
                bizMsg.msgTxtInfoTxt_A.setValue(MSG_NO + MSG_FOUND_MORE_THAN_ONE);
            } else if (count == 1) {
                bizMsg.msgTxtInfoTxt_A.setValue(MSG_FOUND_ONE);
            } else if (count > 1) {
                bizMsg.msgTxtInfoTxt_A.setValue(Integer.toString(count) + MSG_FOUND_MORE_THAN_ONE);
            } else {
                bizMsg.msgTxtInfoTxt_A.setValue(BLANK);
            }
        }
    }

    private void setMessageForIndType(NFAL0050CMsg bizMsg, String message) {

        if (!bizMsg.eventId_P.getValue().equals(EVT_PASTE)) {
            bizMsg.msgTxtInfoTxt_B.setValue(message);
        }
    }

    private void clearMessageOnScrn(NFAL0050CMsg bizMsg) {
        bizMsg.msgTxtInfoTxt_A.clear();
        bizMsg.msgTxtInfoTxt_B.clear();
    }

    private void setActlNm01ListBox(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_1C.clear();
        bizMsg.ajePtrnActlNm_1D.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_1V.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_1C.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlNm_1D.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
            }
        }
    }

    private void setActlNm02ListBox(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_2C.clear();
        bizMsg.ajePtrnActlNm_2D.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_2V.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_2C.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlNm_2D.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
            }
        }
    }

    private void setActlNm03ListBox(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_3C.clear();
        bizMsg.ajePtrnActlNm_3D.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_3V.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_3C.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlNm_3D.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
            }
        }
    }

    private void setActlNm01ListBoxOnChange(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_1C.clear();
        bizMsg.ajePtrnActlNm_1D.clear();
        bizMsg.ajePtrnActlCd_1V.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_1V.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            String selectedVal = BLANK;
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_1C.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlNm_1D.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
                selectedVal = parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD"));
            }
            // Set selected value when only one code in the list
            if (i == 1) {
                bizMsg.ajePtrnActlCd_1V.setValue(selectedVal);
            }
        }
    }

    private void setActlNm02ListBoxOnChange(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_2C.clear();
        bizMsg.ajePtrnActlNm_2D.clear();
        bizMsg.ajePtrnActlCd_2V.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_2V.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            String selectedVal = BLANK;
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_2C.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlNm_2D.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
                selectedVal = parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD"));
            }
            // Set selected value when only one code in the list
            if (i == 1) {
                bizMsg.ajePtrnActlCd_2V.setValue(selectedVal);
            }
        }
    }

    private void setActlNm03ListBoxOnChange(NFAL0050CMsg bizMsg) {

        bizMsg.ajePtrnActlCd_3C.clear();
        bizMsg.ajePtrnActlNm_3D.clear();
        bizMsg.ajePtrnActlCd_3V.clear();

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getActlCdByIndTpCd(bizMsg, bizMsg.ajePtrnIndTpCd_3V.getValue());

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            String selectedVal = BLANK;
            int i = 0;
            for (; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.ajePtrnActlCd_3C.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD")));
                bizMsg.ajePtrnActlNm_3D.no(i).setValue(parts.checkNull((String) map.get("AJE_PTRN_ACTL_NM")));
                selectedVal = parts.checkNull((String) map.get("AJE_PTRN_ACTL_CD"));
            }
            // Set selected value when only one code in the list
            if (i == 1) {
                bizMsg.ajePtrnActlCd_3V.setValue(selectedVal);
            }
        }
    }

    private boolean samePtrnExistInSixPtrn(NFAL0050CMsg bizMsg) {

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hMp = new HashMap<String, String>();
            if (i == 0) {
                hMp.put(TYPE_1, bizMsg.ajePtrnIndTpCd_1V.getValue());
                hMp.put(CODE_1, bizMsg.ajePtrnActlCd_1V.getValue());

                hMp.put(TYPE_2, bizMsg.ajePtrnIndTpCd_3V.getValue());
                hMp.put(CODE_2, bizMsg.ajePtrnActlCd_3V.getValue());

                hMp.put(TYPE_3, bizMsg.ajePtrnIndTpCd_2V.getValue());
                hMp.put(CODE_3, bizMsg.ajePtrnActlCd_2V.getValue());
            } else if (i == 1) {
                hMp.put(TYPE_1, bizMsg.ajePtrnIndTpCd_2V.getValue());
                hMp.put(CODE_1, bizMsg.ajePtrnActlCd_2V.getValue());

                hMp.put(TYPE_2, bizMsg.ajePtrnIndTpCd_1V.getValue());
                hMp.put(CODE_2, bizMsg.ajePtrnActlCd_1V.getValue());

                hMp.put(TYPE_3, bizMsg.ajePtrnIndTpCd_3V.getValue());
                hMp.put(CODE_3, bizMsg.ajePtrnActlCd_3V.getValue());
            } else if (i == 2) {
                hMp.put(TYPE_1, bizMsg.ajePtrnIndTpCd_2V.getValue());
                hMp.put(CODE_1, bizMsg.ajePtrnActlCd_2V.getValue());

                hMp.put(TYPE_2, bizMsg.ajePtrnIndTpCd_3V.getValue());
                hMp.put(CODE_2, bizMsg.ajePtrnActlCd_3V.getValue());

                hMp.put(TYPE_3, bizMsg.ajePtrnIndTpCd_1V.getValue());
                hMp.put(CODE_3, bizMsg.ajePtrnActlCd_1V.getValue());
            } else if (i == 3) {
                hMp.put(TYPE_1, bizMsg.ajePtrnIndTpCd_3V.getValue());
                hMp.put(CODE_1, bizMsg.ajePtrnActlCd_3V.getValue());

                hMp.put(TYPE_2, bizMsg.ajePtrnIndTpCd_1V.getValue());
                hMp.put(CODE_2, bizMsg.ajePtrnActlCd_1V.getValue());

                hMp.put(TYPE_3, bizMsg.ajePtrnIndTpCd_2V.getValue());
                hMp.put(CODE_3, bizMsg.ajePtrnActlCd_2V.getValue());
            } else if (i == 4) {
                hMp.put(TYPE_1, bizMsg.ajePtrnIndTpCd_3V.getValue());
                hMp.put(CODE_1, bizMsg.ajePtrnActlCd_3V.getValue());

                hMp.put(TYPE_2, bizMsg.ajePtrnIndTpCd_2V.getValue());
                hMp.put(CODE_2, bizMsg.ajePtrnActlCd_2V.getValue());

                hMp.put(TYPE_3, bizMsg.ajePtrnIndTpCd_1V.getValue());
                hMp.put(CODE_3, bizMsg.ajePtrnActlCd_1V.getValue());
            }

            S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getAjeIdsGroupByIndTpIfExists(bizMsg, hMp);
            if (ssmResult.isCodeNormal()) {
                int resultCnt = ssmResult.getQueryResultCount();
                if (resultCnt > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean samePtrnExistInFourPtrn(NFAL0050CMsg bizMsg) {

        HashMap<String, String> hMp = new HashMap<String, String>();
        // TODO test
        hMp.put(TYPE_1, bizMsg.ajePtrnIndTpCd_2V.getValue());
        hMp.put(CODE_1, bizMsg.ajePtrnActlCd_2V.getValue());

        hMp.put(TYPE_2, bizMsg.ajePtrnIndTpCd_1V.getValue());
        hMp.put(CODE_2, bizMsg.ajePtrnActlCd_1V.getValue());

        hMp.put(TYPE_3, BLANK);
        hMp.put(CODE_3, BLANK);

        S21SsmEZDResult ssmResult = NFAL0050Query.getInstance().getAjeIdsGroupByIndTpIfExists(bizMsg, hMp);
        if (ssmResult.isCodeNormal()) {
            int resultCnt = ssmResult.getQueryResultCount();
            if (resultCnt > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method name: doProcess_NFAL0050_NFAL9020 <dd>The method
     * explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0050_NFAL9020(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0050_NFAL9020================================START", null);

        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        // SEt Sys, Trx, TrxRsn by returned AJE ID
        setSysTrxTrxRsnVal(bizMsg);

        setActlNm01ListBox(bizMsg);
        setActlNm02ListBox(bizMsg);
        setActlNm03ListBox(bizMsg);

        common.setResetFields(bizMsg);
        common.setSearchedResult(bizMsg, NOT_RESET);

        EZDDebugOutput.println(1, "doProcess_NFAL0050_NFAL9020================================END", null);
    }

    private void setSysTrxTrxRsnVal(NFAL0050CMsg bizMsg) {

        StringTokenizer tk = new StringTokenizer(bizMsg.ajeId.getValue(), HYPHEN);
        for (int i = 0; tk.hasMoreTokens(); i++) {
            String code = tk.nextToken();
            if (i == 0) {
                bizMsg.sysSrcCd_3.setValue(code);
            } else if (i == 1) {
                bizMsg.trxCd_3.setValue(code);
                common.getTrxRsnListBox(bizMsg);
            } else {
                bizMsg.trxRsnCd_3.setValue(code);
            }
        }
    }
}
