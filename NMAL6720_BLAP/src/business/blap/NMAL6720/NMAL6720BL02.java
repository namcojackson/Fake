/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6720;

import static business.blap.NMAL6720.constant.NMAL6720Constant.ACTIVE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_A;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_B;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_D;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_F;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_G;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CHKBOX_I;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CSV_FILE_NAME;
import static business.blap.NMAL6720.constant.NMAL6720Constant.CSV_FILE_NAME_OPEN_TRX;
import static business.blap.NMAL6720.constant.NMAL6720Constant.IN_ACTIVE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.IN_PROCESS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MAX_DT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_BILL_TO_CD;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_MST;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM0039E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM0147I;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM0163E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8666E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8674I;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NZZM0000E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NZZM0001W;
import static business.blap.NMAL6720.constant.NMAL6720Constant.PRNT_ACCT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING;
import static business.blap.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_EDIT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_NEW;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_ACCOUNT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_CLASSIFICATIONS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_CONTACTS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_MSG_NOTE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_SHIPPING;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_SVC_ATTRB;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_TRANSACTIONS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.ZZM8000I;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6720.common.NMAL6720CommonLogic;
import business.db.RTL_WHTMsg;
import business.db.RTL_WHTMsgArray;
import business.db.STTMsg;
import business.db.TECH_MSTRTMsg;
import business.file.NMAL6720F00FMsg;
import business.file.NMAL6720F02FMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DPL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_XREF_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PMIT_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824, #4760
 * 2016/05/10   SRAA            Y.Chen          Update          QC#4505, QC#5360
 * 2016/07/05   Fujitsu         C.Tanaka        Update          QC#10677
 * 2016/12/27   Fujitsu         C.Yokoi         Update          QC#14924
 * 2017/02/20   Fujitsu         T.Aoi           Update          QC#16846
 * 2017/07/13   Fujitsu         R.Nakamura      Update          QC#19059
 * 2017/11/27   Fujitsu         A.Kosai         Update          QC#20828
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2018/02/19   Fujitsu         Hd.Sugawara     Update          QC#20291-1
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 * 2018/07/24   Fujitsu         W.Honda         Update          QC#27169
 * 2018/07/31   Fujitsu         W.Honda         Update          QC#27169
 * 2018/08/29   Fujitsu         W.Honda         Update          QC#27869
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2020/03/24   Fujitsu         M.Ohno          Update          QC#55673
 * 2020/07/22   CITS            K.Ogino         Update          QC#57316
 * 2021/05/20   CITS            M.Furugoori     Update          QC#58743
 * 2022/07/28   CITS            A.Cullano       Update          QC#60173
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6720BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;
        NMAL6720SMsg sharedMsg = (NMAL6720SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6720_INIT".equals(screenAplID)) {
                doProcess_NMAL6720_INIT(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_CMN_ColSave".equals(screenAplID)) {
                // do nothing
            } else if ("NMAL6720Scrn00_CMN_ColClear".equals(screenAplID)) {
                // do nothing
            } else if ("NMAL6720Scrn00_Search_PrimTechNm".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_Search_PrimTechNm(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_Search_ReqTechNm".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_Search_ReqTechNm(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_CMN_Download(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_CMN_Reset(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddAccount".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_AddAccount(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteAccount".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteAccount(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_TAB_Account".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_TAB_Account(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_TAB_Classifications".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_TAB_Classifications(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_TAB_Contacts".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_TAB_Contacts(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_TAB_Transactions".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_TAB_Transactions(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_TAB_Instructions".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_TAB_MsgNote(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_TAB_Shipping".equals(screenAplID)) { // 2018/02/19 QC#20297(Sol#379) add start
                doProcess_NMAL6720Scrn00_TAB_Shipping(bizMsg, sharedMsg);
                // 2018/02/19 QC#20297(Sol#379) add end
            } else if ("NMAL6720Scrn00_TAB_SrvAttrb".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_TAB_SrvAttrb(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddMsgNote".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_AddMsgNote(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteMsgNote".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteMsgNote(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddRef".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_AddRef(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteRef".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteRef(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddTransaction".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_AddTransaction(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteTransaction".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteTransaction(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddShipping".equals(screenAplID)) { // 2018/02/19 QC#20297(Sol#379) add start
                doProcess_NMAL6720Scrn00_AddShipping(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteShipping".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteShipping(bizMsg, sharedMsg);
                // 2018/02/19 QC#20297(Sol#379) add end
            } else if ("NMAL6720_NMAL6750".equals(screenAplID)) {
                doProcess_NMAL6720_NMAL6750(bizMsg, sharedMsg);
            } else if ("NMAL6720_NMAL6770".equals(screenAplID)) {
                doProcess_NMAL6720_NMAL6770(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddCertReq".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_AddCertReq(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteCertReq".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteCertReq(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_AddTech".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_AddTech(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_DeleteTech".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_DeleteTech(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_Select_Country".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_Select_Country(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_OnChange_ShowInactive".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_OnChange_ShowInactive(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_OnChange_frtCond".equals(screenAplID)) { // 2018/02/19 QC#20297(Sol#379) add start
                doProcess_NMAL6720Scrn00_OnChange_frtCond(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_OnChange_lineBizTp".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_OnChange_lineBizTp(bizMsg, sharedMsg);
                // 2018/02/19 QC#20297(Sol#379) add end
            } else if ("NMAL6720Scrn00_GetAcctNm".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_GetAcctNm(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_PageNext(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_PagePrev(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_PageJump(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_ValidateAddress".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_ValidateAddress(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_GetAddress".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_GetAddress(bizMsg, sharedMsg);
            // 2018/07/11 Update Start QC#26422
            } else if ("NMAL6720Scrn00_ShowRelatedBillToDetails".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_ShowRelatedBillToDetails(bizMsg, sharedMsg);
            // 2018/07/11 Update Start QC#26422
            // Add QC#57316 Start
            } else if ("NMAL6720Scrn00_OpenTrx_Download".equals(screenAplID)) {
                doProcess_NMAL6720_OpenTrx_Download(bizMsg, sharedMsg);
            // ADd QC#57316 End
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(bizMsg, sharedMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6720_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6720_INIT(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (!searchAccount(cMsg, sMsg)) {
            return;
        }

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.F);
        ZYPTableUtil.clear(cMsg.F);
        ZYPTableUtil.clear(sMsg.G);
        ZYPTableUtil.clear(cMsg.G);
        ZYPTableUtil.clear(sMsg.T);
        ZYPTableUtil.clear(sMsg.U);
        ZYPTableUtil.clear(sMsg.V);
        ZYPTableUtil.clear(sMsg.W);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Y);
        // 2018/02/19 QC#20297(Sol#379) add start
        ZYPTableUtil.clear(cMsg.I);
        ZYPTableUtil.clear(sMsg.I);
        ZYPTableUtil.clear(sMsg.J);
        ZYPTableUtil.clear(sMsg.K);
        // 2018/02/19 QC#20297(Sol#379) add end

        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ACCOUNT);

        ZYPCodeDataUtil.createPulldownList(CTRY.class, cMsg.ctryCd_H1, cMsg.ctryNm_H1);
        ZYPCodeDataUtil.createPulldownList(DPL_STS.class, cMsg.dplStsCd_H1, cMsg.dplStsNm_H1);
        createStatePulldownList(cMsg, CTRY.UNITED_STATES_OF_AMERICA);
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_H1, cMsg.lineBizTpNm_H1);
        // set default value
        ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_P1, CTRY.UNITED_STATES_OF_AMERICA);
        ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd_P1, LINE_BIZ_TP.ALL);
        cMsg.ctacPsnPk_H1.clear();

        ZYPGUITableColumn.getColData((NMAL6720CMsg) cMsg, (NMAL6720SMsg) sMsg);

        ZYPCodeDataUtil.createPulldownList(DS_XREF_ACCT_TP.class, cMsg.dsXrefAcctTpCd_B1, cMsg.dsXrefAcctTpNm_B1);

        ZYPCodeDataUtil.createPulldownList(DS_TRX_RULE_TP.class, cMsg.dsTrxRuleTpCd_D1, cMsg.dsTrxRuleTpNm_D1);

        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_E1, cMsg.lineBizTpDescTxt_E1);
        ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, cMsg.dsBizAreaCd_E1, cMsg.dsBizAreaNm_E1);
        ZYPCodeDataUtil.createPulldownList(DS_CUST_MSG_TP.class, cMsg.dsCustMsgTpCd_E1, cMsg.dsCustMsgTpNm_E1);
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_I1, cMsg.lineBizTpNm_I1);
        // mod start 2023/07/18 QC#61421
        //ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, cMsg.dsBizAreaCd_I1, cMsg.dsBizAreaNm_I1);
        NMAL6720CommonLogic.createDsBizAreaPulldownForShippingTab(getGlobalCompanyCode(), cMsg);
        // mod end 2023/07/18 QC#61421

        search(cMsg, sMsg);

    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_Search_PrimTechNm <dd>The
     * method explanation: Address Tab Download.
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     */
    private void doProcess_NMAL6720Scrn00_Search_PrimTechNm(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        cMsg.xxAllPsnNm_S1.clear();
        if (ZYPCommonFunc.hasValue(cMsg.prfTechCd_SA)) {
            TECH_MSTRTMsg techTMsg = NMAL6720CommonLogic.findTechMstr(getGlobalCompanyCode(), cMsg.prfTechCd_SA.getValue());
            if (techTMsg == null) {
                cMsg.prfTechCd_SA.setErrorInfo(1, NZZM0000E);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.xxAllPsnNm_S1, techTMsg.techNm);
            }
        }
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_Search_ReqTechNm <dd>The
     * method explanation: Address Tab Download.
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     */
    private void doProcess_NMAL6720Scrn00_Search_ReqTechNm(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        cMsg.xxAllPsnNm_S2.clear();
        if (ZYPCommonFunc.hasValue(cMsg.reqTechCd_SA)) {
            TECH_MSTRTMsg techTMsg = NMAL6720CommonLogic.findTechMstr(getGlobalCompanyCode(), cMsg.reqTechCd_SA.getValue());
            if (techTMsg == null) {
                cMsg.reqTechCd_SA.setErrorInfo(1, NZZM0000E);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.xxAllPsnNm_S2, techTMsg.techNm);
            }
        }
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_CMN_Download <dd>The
     * method explanation: Address Tab Download.
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     */
    private void doProcess_NMAL6720Scrn00_CMN_Download(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6720Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> queryParam = getSerchContactListParam(cMsg, sMsg);
            ps = ssmLLClient.createPreparedStatement("getContactList", queryParam, execParam);
            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    /**
     * writeCsvFile
     * @param cMsg NMAL6720CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public void writeCsvFile(NMAL6720CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL6720F00FMsg fMsg = new NMAL6720F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnPk_C1, rs.getBigDecimal("CTAC_PSN_PK"));
            // Mod Start 2017/12/06 QC#21897
            //ZYPEZDItemValueSetter.setValue(fMsg.ctacTpNm_C1, rs.getString("CTAC_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctacTpDescTxt_C1, rs.getString("CTAC_TP_DESC_TXT"));
            // Mod End 2017/12/06 QC#21897
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnFirstNm_C1, rs.getString("CTAC_PSN_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnLastNm_C1, rs.getString("CTAC_PSN_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPsnDeptNm_C1, rs.getString("DS_CTAC_PSN_DEPT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_C1, rs.getString("EMAIL"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_C2, rs.getString("PHONE"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPsnExtnNum_C1, rs.getString("EXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_C1, rs.getString("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_C1, rs.getString("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsPrimLocFlg_C1, rs.getString("DS_PRIM_LOC_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctStsNm_C1, rs.getString("DS_ACCT_STS_NM"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NMAL6720F00FMsg fMsg, NMAL6720CMsg cMsg) {
        final String[] csvHeader = new String[] {"Contact ID", "Relationship To CSA", "First Name", "Last Name", "Department", "Email-Work", "Phone-Work", "Ext", "Start Date", "End Date", "Primary", "Status" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_CMN_Submit <dd>The method
     * explanation: Reset values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_CMN_Submit(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if ("W".equals(cMsg.getMessageKind())) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxScrEventNm, SCRN_EVENT_EDIT);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.B);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.F);
        ZYPTableUtil.clear(cMsg.F);
        ZYPTableUtil.clear(sMsg.G);
        ZYPTableUtil.clear(cMsg.G);
        ZYPTableUtil.clear(sMsg.T);
        ZYPTableUtil.clear(sMsg.U);
        ZYPTableUtil.clear(sMsg.V);
        ZYPTableUtil.clear(sMsg.W);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Y);
        // 2018/02/19 QC#20297(Sol#379) add start
        ZYPTableUtil.clear(cMsg.I);
        ZYPTableUtil.clear(sMsg.I);
        ZYPTableUtil.clear(sMsg.J);
        ZYPTableUtil.clear(sMsg.K);
        // 2018/02/19 QC#20297(Sol#379) add end

        search(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_CMN_Reset <dd>The method
     * explanation: Reset values. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_CMN_Reset(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String col = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NMAL6720_INIT(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, col);
    }

    private void doProcess_NMAL6720Scrn00_AddAccount(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        addAccountInfo(cMsg, sMsg);

        // 2017/11/27 QC#20828 Add Start
        if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.prosToCustChngStsCd_H1, PROS_TO_CUST_CHNG_STS_PENDING);
            cMsg.setMessageInfo(NMAM8674I);
        }
        // 2017/11/27 QC#20828 Add End
    }

    private void doProcess_NMAL6720Scrn00_DeleteAccount(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.A, CHKBOX_A, ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            NMAL6720_ACMsg acMsg = cMsg.A.no(index);
            if (ZYPCommonFunc.hasValue(acMsg.acctLocPk_A1)) {
                int validCnt = sMsg.T.getValidCount();
                NMAL6720_TSMsg tsMsg = sMsg.T.no(validCnt);
                ZYPEZDItemValueSetter.setValue(tsMsg.acctLocPk_T1, acMsg.acctLocPk_A1.getValue());
                ZYPEZDItemValueSetter.setValue(tsMsg.ezUpTime_T1, acMsg.ezUpTime_A1.getValue());
                ZYPEZDItemValueSetter.setValue(tsMsg.ezUpTimeZone_T1, acMsg.ezUpTimeZone_A1.getValue());
                sMsg.T.setValidCount(validCnt + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.A, checkList);
        ZYPTableUtil.deleteRows(sMsg.A, checkList);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_TAB_Linkage <dd>The
     * method explanation: TAB Linkage.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_TAB_Account(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ACCOUNT);
    }

    private void doProcess_NMAL6720Scrn00_TAB_Classifications(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_CLASSIFICATIONS);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_TAB_Contacts <dd>The
     * method explanation: TAB Contacts.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_TAB_Contacts(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_CONTACTS);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_TAB_Transactions <dd>The
     * method explanation: TAB Transactions.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_TAB_Transactions(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_TRANSACTIONS);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_TAB_MsgNote <dd>The
     * method explanation: TAB MsgNote.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_TAB_MsgNote(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_MSG_NOTE);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_TAB_SrvAttrb <dd>The
     * method explanation: TAB Service Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_TAB_SrvAttrb(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SVC_ATTRB);
    }

    /**
     * Method name: doProcess_NMAL6720_NMAL6750 <dd>The method
     * explanation: TAB Service Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720_NMAL6750(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        doProcess_NMAL6720Scrn00_CMN_Reset(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_CONTACTS);
    }

    /**
     * Method name: doProcess_NMAL6720_NMAL6770 <dd>The method
     * explanation: TAB Service Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720_NMAL6770(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        searchContactList(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_GetAcctNm <dd>The method
     * explanation: Get Account Name.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_GetAcctNm(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        int index = cMsg.xxCellIdx_AI.getValueInt();
        NMAL6720_ASMsg aSMsg = sMsg.A.no(index);
        NMAL6720_ACMsg aCMsg = cMsg.A.no(index);
        // 2017/11/27 QC#20828 Mod Start
        //S21SsmEZDResult ssmResult = NMAL6720CommonLogic.getAcct(aCMsg.dsAcctNum_A1.getValue(), getGlobalCompanyCode(), cMsg.dsAcctTpCd_H1.getValue());
        S21SsmEZDResult ssmResult = NMAL6720CommonLogic.getAcct(aCMsg.dsAcctNum_A1.getValue(), getGlobalCompanyCode(), aCMsg.dsAcctTpCd_A1.getValue());
        // 2017/11/27 QC#20828 Mod End
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(aCMsg.dsAcctNm_A1, (String) result.get("DS_ACCT_NM"));
        } else {
            aCMsg.dsAcctNum_A1.setErrorInfo(1, NZZM0000E);
        }
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_Search <dd>The method
     * explanation: Serch Information. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    private void search(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (SCRN_EVENT_EDIT.equals(cMsg.xxScrEventNm.getValue())) {
            searchHeader(cMsg, sMsg);
            searchAccountList(cMsg, sMsg);
            searchClassificationInfo(cMsg, sMsg);
            getCrossReferenceList(cMsg, sMsg);
            searchContactList(cMsg, sMsg);
            searchTransactionList(cMsg, sMsg);
            searchMsgNoteList(cMsg, sMsg);
            searchCertReqList(cMsg, sMsg);
            searchDoNotSentTechList(cMsg, sMsg);
            showAccountList(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
            // 2018/02/19 QC#20297(Sol#379) add start
            searchShippingList(cMsg, sMsg);
            // 2018/02/19 QC#20297(Sol#379) add END
            // 2020/03/02 QC#55673 Add Start
            searchRtlWh(cMsg, sMsg);
            // 2020/03/02 QC#55673 Add End

        } else if (SCRN_EVENT_NEW.equals(cMsg.xxScrEventNm.getValue())) {
            cMsg.dsAcctNum_AI.setValue(cMsg.dsAcctNum_H1.getValue());
            cMsg.actvFlg_H1.setValue(ZYPConstant.CHKBOX_ON_Y);

            addAccountInfo(cMsg, sMsg);
            if (sMsg.A.getValidCount() > 0) {
                NMAL6720_ASMsg aSMsg = sMsg.A.no(0);
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, aSMsg.dsAcctNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H1, aSMsg.dsAcctTpCd_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.cmpyPk, sMsg.cmpyPk);
            }
            
            // 2020/03/02 QC#55673 Add Start
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_AL, ZYPConstant.FLG_OFF_N);
            // 2020/03/02 QC#55673 Add End
        } else {
            throw new S21AbendException("It's illegal Screen Event ID : " + cMsg.xxScrEventNm.getValue());
        }
    }

    private void searchHeader(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rgtnStsCd", RGTN_STS.TERMINATED);

        S21SsmEZDResult ssmResult = getQuery().getHeaderInfo(queryParam, sMsg);
        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm_BI, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm_BI.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm_BI, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm_BI.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm_SH, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistCratByNm_SH.getValue()));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm_SH, ZYPRecHistUtil.getFullNameForRecHist(sMsg.xxRecHistUpdByNm_SH.getValue()));

            EZDMsg.copy(sMsg, null, cMsg, null);
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(sMsg.rgtnStsCd_H1.getValue())) {
                cMsg.actvFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
                sMsg.actvFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                cMsg.actvFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
                sMsg.actvFlg_H1.setValue(ZYPConstant.FLG_OFF_N);
            }

            // 2023/11/06 QC#61924 Add Start
            ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, sMsg.xxChkBox_H1.getValue());
            // 2023/11/06 QC#61924 Add End

        } else if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
        }
        createStatePulldownList(cMsg, cMsg.ctryCd_P1.getValue());
    }

    private boolean searchAccount(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());

        S21SsmEZDResult ssmResult = getQuery().getDsAcctTpCd(queryParam);
        if (ssmResult.isCodeNormal()) {
            String dsAcctTpCd = (String) ssmResult.getResultObject();
            if (DS_ACCT_TP.PROSPECT.equals(dsAcctTpCd) || DS_ACCT_TP.CUSTOMER.equals(dsAcctTpCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H1, dsAcctTpCd);
                return true;

            } else {
                cMsg.setMessageInfo(NZZM0000E);

            }
        }
        return false;
    }

    private void searchAccountList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rgtnStsCdPrc", RGTN_STS.PENDING_COMPLETION);
        queryParam.put("rgtnStsCdAct", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("process", IN_PROCESS);
        queryParam.put("active", ACTIVE);
        queryParam.put("inActive", IN_ACTIVE);
        queryParam.put("parent", PRNT_ACCT);
        queryParam.put("rowNum", sMsg.A.length());
        queryParam.put("dsAcctTpCd", cMsg.dsAcctTpCd_H1.getValue());

        String[] rgtnStsList = new String[] {RGTN_STS.PENDING_COMPLETION, RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED };
        queryParam.put("rgtnStsCdList", rgtnStsList);

        S21SsmEZDResult ssmResult = getQuery().getAccountList(queryParam, sMsg.A);
        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistCratByNm_A1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.A.no(i).xxRecHistUpdByNm_A1.getValue()));
            }

        }
    }

    private void showAccountList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, String copyFlg) {
        if (ZYPConstant.FLG_ON_Y.equals(copyFlg)) {
            NMAL6720CommonLogic.copyAcctListInput(cMsg, sMsg);
        }
        ZYPTableUtil.clear(cMsg.A);
        int cnt = 0;
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_AI.getValue())) {
            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
            cnt = sMsg.A.getValidCount();
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (!IN_ACTIVE.equals(sMsg.A.no(i).dsAcctStsNm_A1.getValue())) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cnt), null);
                    cnt++;
                }
            }
        }
        cMsg.A.setValidCount(cnt);
    }

    /**
     * <pre>
     * Search Detail.
     * </pre>
     * @param bizMsg
     * @param sMsg
     */
    @SuppressWarnings("unchecked")
    private void addAccountInfo(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        int index = sMsg.A.getValidCount();
        NMAL6720_ASMsg aSMsg = sMsg.A.no(index);
        if (index == 0) {
            NMAL6720_ACMsg aCMsg = cMsg.A.no(index);
            S21SsmEZDResult ssmResult = NMAL6720CommonLogic.getAcct(cMsg.dsAcctNum_H1.getValue(), getGlobalCompanyCode(), cMsg.dsAcctTpCd_H1.getValue());
            if (ssmResult.isCodeNormal()) {
                String salesDt = ZYPDateUtil.getSalesDate();
                ZYPEZDItemValueSetter.setValue(aSMsg.effFromDt_A1, salesDt);
                ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctNum_A1, cMsg.dsAcctNum_H1.getValue());

                Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctNm_A1, (String) result.get("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctTpCd_A1, (String) result.get("DS_ACCT_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.cmpyPk, (BigDecimal) result.get("CMPY_PK"));
            } else {
                aCMsg.dsAcctNum_A1.setErrorInfo(1, NZZM0000E);
            }
        }
        sMsg.A.setValidCount(index + 1);
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_AI.getValue()) || !ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
            ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctRelnTpNm_A1, PRNT_ACCT);
            // 2017/11/27 QC#20828 Add Start
            // Add Start 2018/02/19 QC#20291-1
            if (!SCRN_EVENT_NEW.equals(cMsg.xxScrEventNm.getValue())) {
            // Add End 2018/02/19 QC#20291-1

            ZYPEZDItemValueSetter.setValue(aSMsg.dsAcctTpCd_A1, DS_ACCT_TP.CUSTOMER);

            // Add Start 2018/02/19 QC#20291-1
            }
            // Add End 2018/02/19 QC#20291-1

            if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_H1.getValue()) && !PROS_TO_CUST_CHNG_STS_PENDING.equals(cMsg.prosToCustChngStsCd_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(aSMsg.effFromDt_A1, ZYPDateUtil.getSalesDate());
            }
            // 2017/11/27 QC#20828 Add End
            showAccountList(cMsg, sMsg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.A.getValidCount()).dsAcctRelnTpNm_A1, PRNT_ACCT);
            // 2017/11/27 QC#20828 Add Start
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.A.getValidCount()).dsAcctTpCd_A1, DS_ACCT_TP.CUSTOMER);
            if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_H1.getValue()) && !PROS_TO_CUST_CHNG_STS_PENDING.equals(cMsg.prosToCustChngStsCd_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.A.getValidCount()).effFromDt_A1, ZYPDateUtil.getSalesDate());
            }
            // 2017/11/27 QC#20828 Add End
            showAccountList(cMsg, sMsg, ZYPConstant.FLG_ON_Y);
        }
    }

    private void searchClassificationInfo(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("dsAcctTpCd", cMsg.dsAcctTpCd_H1.getValue());

        S21SsmEZDResult ssmResult = getQuery().getClassificationsInfo(queryParam, sMsg);

        if (ssmResult.isCodeNormal()) {
            EZDMsg.copy(sMsg, null, cMsg, null);
        }
    }

    private void getCrossReferenceList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rowNum", sMsg.B.length());

        S21SsmEZDResult ssmResult = getQuery().getCrossReferenceList(queryParam, sMsg.B);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistCratByNm_B1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.B.no(i).xxRecHistUpdByNm_B1.getValue()));
            }

            EZDMsg.copy(sMsg.B, null, cMsg.B, null);
        }
    }

    private void doProcess_NMAL6720Scrn00_AddRef(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        int index = sMsg.B.getValidCount();

        sMsg.B.setValidCount(index + 1);
        cMsg.B.setValidCount(index + 1);
    }

    private void doProcess_NMAL6720Scrn00_DeleteRef(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.B, CHKBOX_B, ZYPConstant.CHKBOX_ON_Y);

        int cnt = sMsg.X.getValidCount();
        NMAL6720_BCMsg bCMsg;
        NMAL6720_XSMsg xSMsg;
        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            xSMsg = sMsg.X.no(cnt);
            bCMsg = cMsg.B.no(index);
            if (ZYPCommonFunc.hasValue(sMsg.B.no(index).dsXrefAcctPk_B1)) {
                ZYPEZDItemValueSetter.setValue(xSMsg.dsXrefAcctPk_X1, bCMsg.dsXrefAcctPk_B1);
                ZYPEZDItemValueSetter.setValue(xSMsg.ezUpTime_X1, bCMsg.ezUpTime_B1);
                ZYPEZDItemValueSetter.setValue(xSMsg.ezUpTimeZone_X1, bCMsg.ezUpTimeZone_B1);
                cnt++;
            }
        }
        sMsg.X.setValidCount(cnt);
        ZYPTableUtil.deleteRows(cMsg.B, checkList);
        ZYPTableUtil.deleteRows(sMsg.B, checkList);
    }

    private void searchContactList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        Map<String, Object> queryParam = getSerchContactListParam(cMsg, sMsg);

        S21SsmEZDResult ssmResult = getQuery().getContactList(queryParam, sMsg.C);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.C.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistCratByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistCratByNm_C1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).xxRecHistUpdByNm_C1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.C.no(i).xxRecHistUpdByNm_C1.getValue()));
            }
            EZDMsg.copy(sMsg.C, null, sMsg.R, null);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_CONTACTS, 0);
        }
    }

    private Map<String, Object> getSerchContactListParam(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("dsCtacPntTpCd_P", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("dsCtacPntTpCd_E", DS_CTAC_PNT_TP.EMAIL_WORK);
        queryParam.put("rowNum", sMsg.C.length());
        queryParam.put("showInactiveFlg", cMsg.xxChkBox_CI.getValue());
        queryParam.put("active", ACTIVE);
        queryParam.put("inActive", IN_ACTIVE);
        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_H1)) {
            queryParam.put("ctacPsnPk", cMsg.ctacPsnPk_H1.getValue());
        }
        // Add Start 2017/12/18 QC#22286
        queryParam.put("ctacTpCd", CTAC_TP.DELIVERY_INSTALL);
        // Add End 2017/12/18 QC#22286
        // Add Start 2018/08/29 QC#27869
        queryParam.put("maxDt", MAX_DT);
        queryParam.put("salesDt", ZYPDateUtil.getSalesDate());
        // Add End 2018/08/29 QC#27869
        return queryParam;
    }

    private void searchTransactionList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rowNum", sMsg.D.length());

        S21SsmEZDResult ssmResult = getQuery().getTransactionList(queryParam, sMsg.D);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.D.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.D.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).xxRecHistCratByNm_D1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.D.no(i).xxRecHistCratByNm_D1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).xxRecHistUpdByNm_D1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.D.no(i).xxRecHistUpdByNm_D1.getValue()));
            }
            EZDMsg.copy(sMsg, null, cMsg, null);
        }
    }

    private void searchMsgNoteList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rowNum", sMsg.E.length());

        S21SsmEZDResult ssmResult = getQuery().getMsgNoteList(queryParam, sMsg.E);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.E.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistCratByNm_E1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.E.no(i).xxRecHistCratByNm_E1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.E.no(i).xxRecHistUpdByNm_E1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.E.no(i).xxRecHistUpdByNm_E1.getValue()));
            }
            EZDMsg.copy(sMsg.E, null, sMsg.S, null);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, 0);
        }
    }

    private void doProcess_NMAL6720Scrn00_AddMsgNote(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        int index = sMsg.E.getValidCount();
        sMsg.E.setValidCount(index + 1);

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.E, sMsg.E, cMsg.xxPageShowFromNum_E);
        ZYPEZDItemValueSetter.setValue(sMsg.E.no(index).dsCustMsgTpCd_P1, DS_CUST_MSG_TP.MSG);
        NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, index);
    }

    private void doProcess_NMAL6720Scrn00_DeleteMsgNote(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.E, sMsg.E, cMsg.xxPageShowFromNum_E);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.E, CHKBOX_E, ZYPConstant.CHKBOX_ON_Y);

        NMAL6720_ESMsg eSMsg;
        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            eSMsg = sMsg.E.no(index);
            if (ZYPCommonFunc.hasValue(eSMsg.dsCustSpclMsgPk_E1)) {
                int validCnt = sMsg.W.getValidCount();
                NMAL6720_WSMsg wsMsg = sMsg.W.no(validCnt);
                ZYPEZDItemValueSetter.setValue(wsMsg.dsCustSpclMsgPk_W1, eSMsg.dsCustSpclMsgPk_E1.getValue());
                ZYPEZDItemValueSetter.setValue(wsMsg.ezUpTime_W1, eSMsg.ezUpTime_E1.getValue());
                ZYPEZDItemValueSetter.setValue(wsMsg.ezUpTimeZone_W1, eSMsg.ezUpTimeZone_E1.getValue());
                sMsg.W.setValidCount(validCnt + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.E, checkList);
        ZYPTableUtil.deleteRows(sMsg.E, checkList);

        int page = cMsg.xxPageShowFromNum_E.getValueInt();
        if (page > sMsg.E.getValidCount()) {
            int prev = cMsg.xxPageShowCurNum_E.getValueInt() - 2;
            page = prev * cMsg.E.length() + 1;
            if (prev <= 0) {
                page = 1;
            }
        }
        NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, page);
    }

    private void doProcess_NMAL6720Scrn00_AddTransaction(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        int index = cMsg.D.getValidCount();

        cMsg.D.setValidCount(index + 1);
        sMsg.D.setValidCount(index + 1);
    }

    private void doProcess_NMAL6720Scrn00_DeleteTransaction(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.D, CHKBOX_D, ZYPConstant.CHKBOX_ON_Y);

        int cnt = sMsg.Y.getValidCount();
        NMAL6720_DCMsg dCMsg;
        NMAL6720_YSMsg ySMsg;
        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            ySMsg = sMsg.Y.no(cnt);
            dCMsg = cMsg.D.no(index);
            if (ZYPCommonFunc.hasValue(sMsg.D.no(index).dsCustTrxRulePk_D1)) {
                ZYPEZDItemValueSetter.setValue(ySMsg.dsCustTrxRulePk_Y1, dCMsg.dsCustTrxRulePk_D1);
                ZYPEZDItemValueSetter.setValue(ySMsg.ezUpTime_Y1, dCMsg.ezUpTime_D1);
                ZYPEZDItemValueSetter.setValue(ySMsg.ezUpTimeZone_Y1, dCMsg.ezUpTimeZone_D1);
                cnt++;
            }
        }
        sMsg.Y.setValidCount(cnt);
        ZYPTableUtil.deleteRows(cMsg.D, checkList);
        ZYPTableUtil.deleteRows(sMsg.D, checkList);
    }

    private void searchCertReqList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("lvlTpCd", SVC_PMIT_LVL_TP.SITE);
        queryParam.put("rowNum", sMsg.F.length());

        S21SsmEZDResult ssmResult = getQuery().getCertReqList(queryParam, sMsg.F);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.F.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.F.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxRecHistCratByNm_F1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.F.no(i).xxRecHistCratByNm_F1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxRecHistUpdByNm_F1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.F.no(i).xxRecHistUpdByNm_F1.getValue()));
            }
            EZDMsg.copy(sMsg.F, null, cMsg.F, null);
        }
    }

    private void searchDoNotSentTechList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rowNum", sMsg.G.length());

        S21SsmEZDResult ssmResult = getQuery().getDoNotSentTechList(queryParam, sMsg.G);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.G.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.F.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxRecHistCratByNm_F1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.F.no(i).xxRecHistCratByNm_F1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.F.no(i).xxRecHistUpdByNm_F1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.F.no(i).xxRecHistUpdByNm_F1.getValue()));
            }
            EZDMsg.copy(sMsg.G, null, cMsg.G, null);
        }
    }

    private void doProcess_NMAL6720Scrn00_AddCertReq(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        int index = cMsg.F.getValidCount();

        String salesDt = ZYPDateUtil.getSalesDate();
        sMsg.F.no(index).effFromDt_F1.setValue(salesDt);

        EZDMsg.copy(sMsg.F.no(index), null, cMsg.F.no(index), null);
        cMsg.F.setValidCount(index + 1);
        sMsg.F.setValidCount(index + 1);
    }

    private void doProcess_NMAL6720Scrn00_DeleteCertReq(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.F, CHKBOX_F, ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            NMAL6720_FCMsg fcMsg = cMsg.F.no(index);
            if (ZYPCommonFunc.hasValue(fcMsg.svcAccsPmitPk_F1)) {
                int validCnt = sMsg.U.getValidCount();
                NMAL6720_USMsg usMsg = sMsg.U.no(validCnt);
                ZYPEZDItemValueSetter.setValue(usMsg.svcAccsPmitPk_U1, fcMsg.svcAccsPmitPk_F1.getValue());
                ZYPEZDItemValueSetter.setValue(usMsg.ezUpTime_U1, fcMsg.ezUpTime_F1.getValue());
                ZYPEZDItemValueSetter.setValue(usMsg.ezUpTimeZone_U1, fcMsg.ezUpTimeZone_F1.getValue());
                sMsg.U.setValidCount(validCnt + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.F, checkList);
        ZYPTableUtil.deleteRows(sMsg.F, checkList);
    }

    private void doProcess_NMAL6720Scrn00_AddTech(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        int index = cMsg.G.getValidCount();

        cMsg.G.setValidCount(index + 1);
        sMsg.G.setValidCount(index + 1);
    }

    private void doProcess_NMAL6720Scrn00_DeleteTech(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.G, CHKBOX_G, ZYPConstant.CHKBOX_ON_Y);

        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            NMAL6720_GCMsg gcMsg = cMsg.G.no(index);
            if (ZYPCommonFunc.hasValue(gcMsg.dsCustNonPrfTechPk_G1)) {
                int validCnt = sMsg.V.getValidCount();
                NMAL6720_VSMsg vsMsg = sMsg.V.no(validCnt);
                ZYPEZDItemValueSetter.setValue(vsMsg.dsCustNonPrfTechPk_V1, gcMsg.dsCustNonPrfTechPk_G1.getValue());
                ZYPEZDItemValueSetter.setValue(vsMsg.ezUpTime_V1, gcMsg.ezUpTime_G1.getValue());
                ZYPEZDItemValueSetter.setValue(vsMsg.ezUpTimeZone_V1, gcMsg.ezUpTimeZone_G1.getValue());
                sMsg.V.setValidCount(validCnt + 1);
            }
        }

        ZYPTableUtil.deleteRows(cMsg.G, checkList);
        ZYPTableUtil.deleteRows(sMsg.G, checkList);
    }

    /**
     * <pre>
     * Select Country
     * </pre>
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    void doProcess_NMAL6720Scrn00_Select_Country(NMAL6720CMsg bizMsg, NMAL6720SMsg sMsg) {

        createStatePulldownList(bizMsg, bizMsg.ctryCd_P1.getValue());
    }

    private void createStatePulldownList(NMAL6720CMsg bizMsg, String ctryCd) {
        bizMsg.stCd_H1.clear();
        bizMsg.stNm_H1.clear();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("ctryCd", ctryCd);
        S21SsmEZDResult ssmResult = getQuery().getStateList(queryParam);
        if (ssmResult.isCodeNormal()) {
            List<STTMsg> stTMsgList = (List) ssmResult.getResultObject();
            for (int i = 0; i < stTMsgList.size(); i++) {
                bizMsg.stCd_H1.no(i).setValue(stTMsgList.get(i).stCd.getValue());
                bizMsg.stNm_H1.no(i).setValue(stTMsgList.get(i).stNm.getValue());
            }
        }
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_OnChange_ShowInactive
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6720Scrn00_OnChange_ShowInactive(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (TAB_ACCOUNT.equals(cMsg.xxDplyTab.getValue())) {
            showAccountList(cMsg, sMsg, ZYPConstant.FLG_ON_Y);
        } else if (TAB_CONTACTS.equals(cMsg.xxDplyTab.getValue())) {
            ZYPTableUtil.clear(sMsg.C);
            ZYPTableUtil.clear(cMsg.C);
            searchContactList(cMsg, sMsg);
        }
    }

    /**
     * Get Instance
     * @return
     */
    private static NMAL6720Query getQuery() {
        return NMAL6720Query.getInstance();
    }

    private void doProcess_NMAL6720Scrn00_PageJump(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String tab = cMsg.xxDplyTab.getValue();
        if (TAB_CONTACTS.equals(tab)) {
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.C, sMsg.C, cMsg.xxPageShowFromNum_C);
            cMsg.xxPageShowFromNum_C.setValue((cMsg.C.length() * (cMsg.xxPageShowCurNum_C.getValueInt() - 1)) + 1);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_CONTACTS, cMsg.xxPageShowFromNum_C.getValueInt());
        } else if (TAB_MSG_NOTE.equals(tab)) {
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.E, sMsg.E, cMsg.xxPageShowFromNum_E);
            cMsg.xxPageShowFromNum_E.setValue((cMsg.E.length() * (cMsg.xxPageShowCurNum_E.getValueInt() - 1)) + 1);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, cMsg.xxPageShowFromNum_E.getValueInt());
        } else if (TAB_SHIPPING.equals(tab)) { // 2018/02/19 QC#20297(Sol#379) add start
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.I, sMsg.I, cMsg.xxPageShowFromNum_I);
            cMsg.xxPageShowFromNum_I.setValue((cMsg.I.length() * (cMsg.xxPageShowCurNum_I.getValueInt() - 1)) + 1);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, cMsg.xxPageShowFromNum_I.getValueInt());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                NMAL6720CommonLogic.createFrtCondPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
                NMAL6720CommonLogic.createShpgSvcLvlPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
            }
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    private void doProcess_NMAL6720Scrn00_PageNext(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String tab = cMsg.xxDplyTab.getValue();
        if (TAB_CONTACTS.equals(tab)) {
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.C, sMsg.C, cMsg.xxPageShowFromNum_C);
            cMsg.xxPageShowFromNum_C.setValue(cMsg.xxPageShowToNum_C.getValueInt() + 1);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_CONTACTS, cMsg.xxPageShowFromNum_C.getValueInt());
        } else if (TAB_MSG_NOTE.equals(tab)) {
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.E, sMsg.E, cMsg.xxPageShowFromNum_E);
            cMsg.xxPageShowFromNum_E.setValue(cMsg.xxPageShowToNum_E.getValueInt() + 1);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, cMsg.xxPageShowFromNum_E.getValueInt());
        } else if (TAB_SHIPPING.equals(tab)) { // 2018/02/19 QC#20297(Sol#379) add start
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.I, sMsg.I, cMsg.xxPageShowFromNum_I);
            cMsg.xxPageShowFromNum_I.setValue(cMsg.xxPageShowToNum_I.getValueInt() + 1);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, cMsg.xxPageShowFromNum_I.getValueInt());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                NMAL6720CommonLogic.createFrtCondPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
                NMAL6720CommonLogic.createShpgSvcLvlPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
            }
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    private void doProcess_NMAL6720Scrn00_PagePrev(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String tab = cMsg.xxDplyTab.getValue();
        if (TAB_CONTACTS.equals(tab)) {
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.C, sMsg.C, cMsg.xxPageShowFromNum_C);
            cMsg.xxPageShowFromNum_C.setValue(cMsg.xxPageShowFromNum_C.getValueInt() - cMsg.C.length());
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_CONTACTS, cMsg.xxPageShowFromNum_C.getValueInt());
        } else if (TAB_MSG_NOTE.equals(tab)) {
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.E, sMsg.E, cMsg.xxPageShowFromNum_E);
            cMsg.xxPageShowFromNum_E.setValue(cMsg.xxPageShowFromNum_E.getValueInt() - cMsg.E.length());
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, cMsg.xxPageShowFromNum_E.getValueInt());
        } else if (TAB_SHIPPING.equals(tab)) { // 2018/02/19 QC#20297(Sol#379) add start
            NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.I, sMsg.I, cMsg.xxPageShowFromNum_I);
            cMsg.xxPageShowFromNum_I.setValue(cMsg.xxPageShowFromNum_I.getValueInt() - cMsg.I.length());
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, cMsg.xxPageShowFromNum_I.getValueInt());
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                NMAL6720CommonLogic.createFrtCondPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
                NMAL6720CommonLogic.createShpgSvcLvlPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
            }
            // 2018/02/19 QC#20297(Sol#379) add end
        }
    }

    private void doProcess_NMAL6720Scrn00_ValidateAddress(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        cMsg.xxRsltCd_V1.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_S1, cMsg.firstLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_S1, cMsg.scdLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.thirdLineAddr_S1, cMsg.thirdLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.frthLineAddr_S1, cMsg.frthLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_S1, cMsg.ctyAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.stCd_S1, cMsg.stCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.postCd_S1, cMsg.postCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, cMsg.cntyNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.provNm_S1, cMsg.provNm_H1);

        boolean isError = false;
        boolean isWarning = false;

        NMZC003001PMsg param = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(param.xxAddrVldFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(param.firstLineAddr, cMsg.firstLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(param.scdLineAddr, cMsg.scdLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(param.ctyAddr, cMsg.ctyAddr_H1);
        ZYPEZDItemValueSetter.setValue(param.stCd, cMsg.stCd_P1);
        ZYPEZDItemValueSetter.setValue(param.postCd, cMsg.postCd_H1);
        ZYPEZDItemValueSetter.setValue(param.ctryCd, cMsg.ctryCd_P1);
        ZYPEZDItemValueSetter.setValue(param.cntyNm, cMsg.cntyNm_H1);

        new NMZC003001().execute(param, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(param)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(param);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                isError = true;
            }
        } else {
            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_01.getValue())) {
                cMsg.firstLineAddr_H1.setErrorInfo(1, NMAM8666E);
                isError = true;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_01.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_S1, param.firstLineAddr);
                isWarning = true;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_02.getValue())) {
                cMsg.scdLineAddr_H1.setErrorInfo(1, NMAM8666E);
                isError = true;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_02.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_S1, param.scdLineAddr);
                isWarning = true;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_03.getValue())) {
                cMsg.ctyAddr_H1.setErrorInfo(1, NMAM8666E);
                isError = true;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_03.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_S1, param.ctyAddr);
                isWarning = true;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_04.getValue())) {
                cMsg.stCd_P1.setErrorInfo(1, NMAM8666E);
                isError = true;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_04.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.stCd_S1, param.stCd);
                isWarning = true;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_05.getValue())) {
                cMsg.postCd_H1.setErrorInfo(1, NMAM8666E);
                isError = true;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_05.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.postCd_S1, param.postCd);
                isWarning = true;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_06.getValue())) {
                cMsg.ctryCd_P1.setErrorInfo(1, NMAM8666E);
                isError = true;
            }

            if (RTRN_CD_ERROR.equals(param.xxVldStsCd_07.getValue())) {
                cMsg.cntyNm_H1.setErrorInfo(1, NMAM8666E);
                isError = true;
            } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_07.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, param.cntyNm);
                isWarning = true;
            }
        }

        if (isError) {
            cMsg.xxRsltCd_V1.setValue("E");
        } else if (isWarning) {
            cMsg.xxRsltCd_V1.setValue("W");
        } else {
            cMsg.setMessageInfo(ZZM8000I);
        }
    }

    private void doProcess_NMAL6720Scrn00_GetAddress(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (!CTRY.UNITED_STATES_OF_AMERICA.equals(cMsg.ctryCd_P1.getValue())) {
            cMsg.setMessageInfo(NMAM0147I);
            return;
        }

        // Mod Start 2018/07/24 QC#27169
        List<Map<String, Object>> list = null;
//        Map<String, Object> queryParam = new HashMap<String, Object>();
        Map<String, Object> queryParamWithCnty = new HashMap<String, Object>();
        queryParamWithCnty.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParamWithCnty.put("postCd", cMsg.postCd_H1.getValue());
        queryParamWithCnty.put("ctyAddr", cMsg.ctyAddr_H1.getValue());
        queryParamWithCnty.put("stCd", cMsg.stCd_P1.getValue());
        queryParamWithCnty.put("cntyNm", cMsg.cntyNm_H1.getValue());
        Map<String, Object> queryParamWithoutCnty = new HashMap<String, Object>();
        queryParamWithoutCnty.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParamWithoutCnty.put("postCd", cMsg.postCd_H1.getValue());
        queryParamWithoutCnty.put("ctyAddr", cMsg.ctyAddr_H1.getValue());
        queryParamWithoutCnty.put("stCd", cMsg.stCd_P1.getValue());

        list = NMAL6720CommonLogic.getAddress(queryParamWithCnty, queryParamWithoutCnty, cMsg.xxRsltCd);
//        S21SsmEZDResult res = NMAL6720Query.getInstance().getAddrByPost(queryParamWithCnty);
//
//        if (res.isCodeNormal()) {
//            list = (List<Map<String, Object>>) res.getResultObject();
//        } else {
//            if (cMsg.postCd_H1.getValue().length() > 5) {
//                queryParam.put("postCd", cMsg.postCd_H1.getValue().subSequence(0, 5));
//                res = NMAL6720Query.getInstance().getAddrByPost(queryParam);
//                if (res.isCodeNormal()) {
//                    list = (List<Map<String, Object>>) res.getResultObject();
//                }
//            }
//        }

        if (list == null) {
            cMsg.postCd_H1.setErrorInfo(1, NMAM0039E, new String[] {"Postal Code" });
            return;
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            // Del Start 2017/07/31 QC#27488
            // Add Start 2017/07/13 QC#19059
//            if (list.size() > 1) {
//                cMsg.setMessageInfo(NMAM8670E);
//            }
            // Add End 2017/07/13 QC#19059
            // Del End 2017/07/31 QC#27488

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_H1, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.stCd_P1, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_H1, listCntyNm.get(0));
            }
        }
    }

    // 2018/07/11 Update Start QC#26422
    private void doProcess_NMAL6720Scrn00_ShowRelatedBillToDetails(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg){
        List<Map<String, Object>> list = null;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("billToCustCd", cMsg.billToCustCd_SH.getValue());

        S21SsmEZDResult ssmResult = NMAL6720Query.getInstance().getBillToCustCd(queryParam);

        if (ssmResult.isCodeNormal()) {
            if ((Integer) ssmResult.getResultObject() < 1) {
                cMsg.billToCustCd_SH.setErrorInfo(1, NMAM0163E, new String[] {MSG_BILL_TO_CD, MSG_MST });
            }
        }
    }
    // 2018/07/11 Update End QC#26422

    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }

    // 2018/02/19 QC#20297(Sol#379) add start
    /**
     * Method name: doProcess_NMAL6720Scrn00_TAB_MsgNote <dd>The
     * method explanation: TAB MsgNote.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6720Scrn00_TAB_Shipping(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SHIPPING);
    }

    private void searchShippingList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("rowNum", sMsg.I.length());
        queryParam.put("maxEffThruDt", MAX_DT);

        S21SsmEZDResult ssmResult = getQuery().getDsCustShpgDefList(queryParam, sMsg.I);

        if (ssmResult.isCodeNormal()) {
            if (ssmResult.getQueryResultCount() > sMsg.I.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.I.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxRecHistCratByNm_I1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.I.no(i).xxRecHistCratByNm_I1.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.I.no(i).xxRecHistUpdByNm_I1, ZYPRecHistUtil.getFullNameForRecHist(sMsg.I.no(i).xxRecHistUpdByNm_I1.getValue()));
            }

            // 2018/12/13 QC#29315 Add Start
            // 2023/01/13 QC#60860 Del Start
//            S21SsmEZDResult resPullDown = NMAL6720Query.getInstance().getBankAcctPulldownList(getGlobalCompanyCode());
//            List<Map<String, Object>> resPullDownList = null;
//            if (resPullDown.isCodeNormal()) {
//                resPullDownList = (List<Map<String, Object>>) resPullDown.getResultObject();
//            }
            // 2023/01/13 QC#60860 Del End
            // 2018/12/13 QC#29315 Add End

            EZDMsg.copy(sMsg.I, null, sMsg.K, null);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, 0);
            for (int i = 0; i < cMsg.I.getValidCount(); i++) {
                NMAL6720CommonLogic.createFrtCondPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
                NMAL6720CommonLogic.createShpgSvcLvlPulldown(getGlobalCompanyCode(), cMsg.I.no(i));
                // 2018/12/13 QC#29315 Add Start
                // 2023/01/13 QC#60860 Del Start
//                NMAL6720CommonLogic.createCarrierPulldown(getGlobalCompanyCode(), cMsg.I.no(i), resPullDownList);
                // 2023/01/13 QC#60860 Del End
                // 2018/12/13 QC#29315 Add End
            }
        }
    }

    private void doProcess_NMAL6720Scrn00_AddShipping(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        int index = sMsg.I.getValidCount();
        sMsg.I.setValidCount(index + 1);
        // 2018/12/13 QC#29315 Add Start
        S21SsmEZDResult resPullDown = NMAL6720Query.getInstance().getBankAcctPulldownList(getGlobalCompanyCode());
        List<Map<String, Object>> resPullDownList = null;
        if (resPullDown.isCodeNormal()) {
            resPullDownList = (List<Map<String, Object>>) resPullDown.getResultObject();
        }

        for (int i = 0; i < sMsg.I.getValidCount(); i++) {
            NMAL6720CommonLogic.createCarrierPulldown(getGlobalCompanyCode(), sMsg.I.no(i), resPullDownList);
        }
        // 2018/12/13 QC#29315 Add End

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.I, sMsg.I, cMsg.xxPageShowFromNum_I);
        NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, index);
    }

    private void doProcess_NMAL6720Scrn00_DeleteShipping(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.I, sMsg.I, cMsg.xxPageShowFromNum_I);

        // 2018/12/13 QC#29315 Mod Start
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.I, CHKBOX_I, ZYPConstant.CHKBOX_ON_Y);
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.I, CHKBOX_I, ZYPConstant.CHKBOX_ON_Y);
        // 2018/12/13 QC#29315 Mod End

        NMAL6720_ISMsg iSMsg;
        for (int i = 0; i < checkList.size(); i++) {
            int index = checkList.get(i);
            iSMsg = sMsg.I.no(index);
            if (ZYPCommonFunc.hasValue(iSMsg.dsCustShpgDefPk_I1)) {
                int validCnt = sMsg.J.getValidCount();
                NMAL6720_JSMsg jsMsg = sMsg.J.no(validCnt);
                // 2018/12/13 QC#29315 Mod Start
//                ZYPEZDItemValueSetter.setValue(jsMsg.dsCustShpgDefPk_J1, iSMsg.dsCustShpgDefPk_I1.getValue());
//                ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTime_J1, iSMsg.ezUpTime_I1.getValue());
//                ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTimeZone_J1, iSMsg.ezUpTimeZone_I1.getValue());
                ZYPEZDItemValueSetter.setValue(jsMsg.dsCustShpgDefPk_J1, iSMsg.dsCustShpgDefPk_I1.getValue());
                ZYPEZDItemValueSetter.setValue(jsMsg.dsAcctCarrPk_J1, iSMsg.dsAcctCarrPk_I1.getValue());
                ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTime_JS, iSMsg.ezUpTime_IS.getValue());
                ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTimeZone_JS, iSMsg.ezUpTimeZone_IS.getValue());
                ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTime_JC, iSMsg.ezUpTime_IC.getValue());
                ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTimeZone_JC, iSMsg.ezUpTimeZone_IC.getValue());
                // 2018/12/13 QC#29315 Mod End
                sMsg.J.setValidCount(validCnt + 1);
            }
        }
        // 2018/12/13 QC#29315 Del Start
//        ZYPTableUtil.deleteRows(cMsg.I, checkList);
        // 2018/12/13 QC#29315 Del End
        ZYPTableUtil.deleteRows(sMsg.I, checkList);

        int page = cMsg.xxPageShowFromNum_I.getValueInt();
        if (page > sMsg.I.getValidCount()) {
            int prev = cMsg.xxPageShowCurNum_I.getValueInt() - 2;
            page = prev * cMsg.I.length() + 1;
            if (prev <= 0) {
                page = 1;
            }
        }
        NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, page);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_OnChange_lineBizTp
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6720Scrn00_OnChange_lineBizTp(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        NMAL6720_ICMsg icMsg = cMsg.I.no(cMsg.xxRowNum_I.getValueInt());
        icMsg.frtCondCd_P1.clear();
        NMAL6720CommonLogic.createFrtCondPulldown(getGlobalCompanyCode(), icMsg);
    }

    /**
     * Method name: doProcess_NMAL6720Scrn00_OnChange_frtCond
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NMAL6720Scrn00_OnChange_frtCond(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        NMAL6720_ICMsg icMsg = cMsg.I.no(cMsg.xxRowNum_I.getValueInt());
        icMsg.shpgSvcLvlCd_P1.clear();
        NMAL6720CommonLogic.createShpgSvcLvlPulldown(getGlobalCompanyCode(), icMsg);
    }
    // 2018/02/19 QC#20297(Sol#379) add END

    // 2020/03/24 QC#55673 Add Start
    private void searchRtlWh(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        RTL_WHTMsg inTMsg = new RTL_WHTMsg();
        inTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inTMsg.setConditionValue("shipToCustCd01", cMsg.shipToCustCd_SH.getValue());
        inTMsg.setSQLID("003");

        RTL_WHTMsgArray tMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (tMsgArray.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_AL, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_AL, ZYPConstant.FLG_OFF_N);
        }

        return;
    }
    // 2020/03/24 QC#55673 Add End

    /**
     * QC#57316 Add Method
     * doProcess_NMAL6720_OpenTrx_Download explanation: Open trx Download Button.
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     */
    private void doProcess_NMAL6720_OpenTrx_Download(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        String glblCmpyCd = getGlobalCompanyCode();
        String locNum = cMsg.locNum_H1.getValue();
        String dsAcctNum = cMsg.dsAcctNum_H1.getValue();

        String billToCd = cMsg.billToCustCd_BI.getValue();
        String shipToCd = cMsg.shipToCustCd_SH.getValue();

        if (!ZYPCommonFunc.hasValue(billToCd) && !ZYPCommonFunc.hasValue(shipToCd)) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6720Query.getInstance().getClass());

            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_OPEN_TRX), ".csv");
            Map<String, Object> query = new HashMap<String, Object>();

            query.put("glblCmpyCd", glblCmpyCd);
            query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            query.put("acctNum", dsAcctNum);
            query.put("locNum", locNum);
            if (ZYPCommonFunc.hasValue(billToCd)) {
                query.put("billToCustCd", billToCd);
            }
            if (ZYPCommonFunc.hasValue(shipToCd)) {
                query.put("shipToCustCd", shipToCd);
            }
            // 2021/05/20 QC#58743 Add Start
            if (RGTN_STS.TERMINATED.equals(cMsg.rgtnStsCd_BI.getValue())) {
                query.put("billToTerminated", ZYPConstant.FLG_ON_Y);
            }
            if (RGTN_STS.TERMINATED.equals(cMsg.rgtnStsCd_SH.getValue())) {
                query.put("shipToTerminated", ZYPConstant.FLG_ON_Y);
            }
            // 2021/05/20 QC#58743 Add End

            query.put("salesDate", ZYPDateUtil.getSalesDate(glblCmpyCd));
            query.put("flgY", ZYPConstant.FLG_ON_Y);
            query.put("flgN", ZYPConstant.FLG_OFF_N);
            query.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
            query.put("svcMachStsW4I", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
            query.put("svcMachStsInstl", SVC_MACH_MSTR_STS.INSTALLED);
            query.put("svcMachStsW4R", SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
            query.put("svcMachStsDlrSvc", SVC_MACH_MSTR_STS.DEALER_SERVICE);
            query.put("dsContrCtrlStsCancelled", DS_CONTR_CTRL_STS.CANCELLED);
            query.put("dsContrCtrlStsDraft", DS_CONTR_CTRL_STS.DRAFT);
            // 2021/05/20 QC#58743 Add Start
            query.put("dsContrCtrlStsTerminated", DS_CONTR_CTRL_STS.TERMINATED);
            // 2021/05/20 QC#58743 Add End
            query.put("svcTaskStsClosed", SVC_TASK_STS.CLOSED);
            query.put("svcTaskStsCancelled", SVC_TASK_STS.CANCELLED);
            query.put("maxThruDt", "99991231");
            // START 2022/07/28 A.Cullano [QC#60173, ADD]
            query.put("skipRecovTpSkip", SKIP_RECOV_TP.SKIP);
            // END 2022/07/28 A.Cullano [QC#60173, ADD]
            query.put("limitRownum", LIMIT_DL_ROWNUM);

            ps = ssmLLClient.createPreparedStatement("getOpenTransaction", query, execParam);

            rs = ps.executeQuery();
            writeCsvFileOpenTrx(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * QC#57316 Add Method
     * writeCsvFileOpenTrx
     * @param cMsg NMAL6720CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public void writeCsvFileOpenTrx(NMAL6720CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL6720F02FMsg fMsg = new NMAL6720F02FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        String[] csvHeader = new String[] {"Use", "Key Name", "Key#" };
        csvOutFile.writeHeader(csvHeader, fMsg, null);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.xxCmntTxt_US, rs.getString("TRX_PAT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxCmntTxt_NM, rs.getString("KEY_NAME"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxCmntTxt_ID, rs.getString("KEY_VAL"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * getCurrentAccount
     * @param cMsg NMAL6720CMsg
     * @return String
     */
    private String getCurrentAccount(NMAL6720CMsg cMsg) {
        String curAcctNum = null;

        String glblCmpyCd = getGlobalCompanyCode();
        String dsAcctTpCd = cMsg.dsAcctTpCd_H1.getValue();
        String locNum = cMsg.locNum_H1.getValue();

        S21SsmEZDResult res = NMAL6720Query.getInstance().getCurrentAccount(glblCmpyCd, dsAcctTpCd, locNum);

        if (res.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();
            Map<String, Object> result = resultList.get(0);

            curAcctNum = (String) result.get("DS_ACCT_NUM");
        }

        return curAcctNum;
    }

    private boolean isEquals(String newValue, String oldValue) {
        if (oldValue == null & newValue == null) {
            return true;
        }
        if (oldValue == null || newValue == null) {
            return false;
        }
        return oldValue.equals(newValue);
    }
}
