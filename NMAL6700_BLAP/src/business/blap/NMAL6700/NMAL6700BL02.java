/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6700;

import static business.blap.NMAL6700.constant.NMAL6700Constant.ACTIVE_STS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.BUSINESS_ID;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CHAR_SEMICOLON;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CHAR_SLASH;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CHILDREN_MAX_LEVEL;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CONST_DS_CUST_MSG_TP_NOTE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CONTROL_STR;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CSV_FILE_NAME;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CSV_FILE_NAME_CTAC;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CSV_FILE_NAME_RELNSHIP;
import static business.blap.NMAL6700.constant.NMAL6700Constant.CSV_FILE_NAME_OPEN_TRX;
import static business.blap.NMAL6700.constant.NMAL6700Constant.FUNC_ID_ACCT_CUST_UPDATE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.FUNC_ID_ACCT_PROS_UPDATE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.INACTIVE_STS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0835E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8111E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8121E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8181W;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8186E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8187E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8622E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NZZM0000E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NZZM0001W;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_ACTIVE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_CD_ACTIVE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_CD_INACTIVE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_INACTIVE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.RGTN_STS_INPROCESS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_ADDRESSES;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_BANK_ACCT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_CONTACTS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_CR_CLT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_HIERARCHY;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_INV_BLLG;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_MARKETING;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_MSG_NOTE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_RELNSHIPS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_SHIPPING;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_TAXING;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_TRANSACTIONS;

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
import business.blap.NMAL6700.common.NMAL6700CommonLogic;
import business.blap.NMAL6700.constant.NMAL6700Constant;
import business.db.CCYTMsg;
import business.db.CLT_CUST_TPTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.DS_SPCL_HDLG_TPTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.file.NMAL6700F00FMsg;
import business.file.NMAL6700F01FMsg;
import business.file.NMAL6700F02FMsg;
import business.file.NMAL6700F03FMsg;
import business.file.NMAL6700F04FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_STMT_ISS_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AUTO_CASH_HRCH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CCY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_CR_RISK_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_RISK_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_CR_RTG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_BASE_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_BASE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_USG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_USG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_DLR;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CLT_ACCT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_GRP_EXEM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TAX_PRNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/20   Fujitsu         N.Sugiura       Update          N/A
 * 2017/07/10   Hitachi         J.Kim           Update          QC#16966
 * 2017/08/08   Fujitsu         N.Sugiura       Update          QC#8598
 * 2017/10/23   Fujitsu         M.Ohno          Update          QC#21559
 * 2017/12/06   Fujitsu         Hd.Sugawara     Update          QC#21897
 * 2017/12/11   Fujitsu         Hd.Sugawara     Update          QC#20357
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/01/30   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/02/14   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/03/16   Fujitsu         Hd.Sugawara     Update          QC#20357-1
 * 2018/03/20   Fujitsu         Hd.Sugawara     Update          QC#25000
 * 2018/04/16   Hitachi         E.Kameishi      Update          QC#21037
 * 2018/04/20   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/04/27   Fujitsu         Mz.Takahashi    Update          QC#25154
 * 2018/05/16   Fujitsu         Hd.Sugawara     Update          QC#26041
 * 2018/06/04   Fujitsu         N.Sugiura       Update          QC#26450
 * 2018/07/13   Fujitsu         M.Ishii         Update          QC#26613
 * 2018/07/30   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2018/12/12   Fujitsu         Hd.Sugawara     Update          QC#29486
 * 2018/12/10   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/02/13   Fujitsu         R.Nakamura      Update          QC#30293
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2020/07/22   CITS            K.Ogino         Update          QC#57316
 * 2021/05/20   CITS            M.Furugoori     Update          QC#58743
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/07/21   Hitachi         K.Kim           Update          QC#60111
 * 2022/07/28   CITS            A.Cullano       Update          QC#60173
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/01/20   Hitachi         S.Fujita        Update          QC#61011
 * 2023/07/18   Hitachi         T.Doi           Update          QC#61421
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6700BL02 extends S21BusinessHandler {

    /**
     * Method name: doProcess <dd>The method explanation: Call each
     * process by screen id. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;
        NMAL6700SMsg sharedMsg = (NMAL6700SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            // +++++ [START] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();
            bizMsg.setCommitSMsg(true);

            if ("NMAL6700_INIT".equals(screenAplID)) {
                doProcess_NMAL6700_INIT(bizMsg, sharedMsg);
                getColData(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddGrpAsg".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddGrpAsg(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteGrpAsg".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteGrpAsg(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddCertificationReq".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddCertificationReq((NMAL6700CMsg) bizMsg, (NMAL6700SMsg) sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteCertificationReq".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteCertificationReq((NMAL6700CMsg) bizMsg, (NMAL6700SMsg) sharedMsg);
            } else if ("NMAL6700Scrn00_AddInvRule".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddInvRule(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteInvRule".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteInvRule(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddAttribute".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddAttribute(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteAttribute".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteAttribute(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddRelnship".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddRelnship(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteRelnship".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteRelnship(bizMsg, sharedMsg);
            // 2018/12/10 QC#29315 Del Start
//            } else if ("NMAL6700Scrn00_InsertRowCarrierAcct".equals(screenAplID)) {
//                doProcess_NMAL6700Scrn00_InsertRowCarrierAcct(bizMsg, sharedMsg);
//            } else if ("NMAL6700Scrn00_DeleteRowCarrierAcct".equals(screenAplID)) {
//                doProcess_NMAL6700Scrn00_DeleteRowCarrierAcct(bizMsg, sharedMsg);
            // 2018/12/10 QC#29315 Del End
            } else if ("NMAL6700Scrn00_AddMsgNote".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddMsgNote(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteMsgNote".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteMsgNote(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddTransactionRule".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddTransactionRule(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteTransactionRule".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteTransactionRule(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddSpecialHandling".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddSpecialHandling(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteSpecialHandling".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteSpecialHandling(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_AddShipping".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_AddShipping(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_DeleteShipping".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_DeleteShipping(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_GetCoaChNm".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetCoaChNm(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_GetInterCompanyNm".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetInterCompanyNm(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_ShowAcct".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_ShowAcct(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_CMN_Reset(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_PageNext(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_PagePrev(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_PageJump(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL6700_CMN_Download((NMAL6700CMsg) bizMsg, (NMAL6700SMsg) sharedMsg);
            } else if ("NMAL6700Scrn00_Search".equals(screenAplID)) {
                // Mod Start 2018/12/20 QC#29486
                //doProcess_NMAL6700Scrn00_Search(bizMsg, sharedMsg);
                doProcess_NMAL6700Scrn00_Search(bizMsg, sharedMsg, true);
                // Mod End 2018/12/20 QC#29486
            } else if ("NMAL6700Scrn00_TAB_Addresses".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Addresses(bizMsg, sharedMsg);
                getColData(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_BankAcct".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_BankAcct(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_Contacts".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Contacts(bizMsg, sharedMsg);
                getColData(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_CrClt".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_CrClt(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_Hierarchy".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Hierarchy(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_InvBllg".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_InvBllg(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_Marketing".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Marketing(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_MsgNote".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_MsgNote(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_Relnships".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Relnships(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_Transactions".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Transactions(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_TAB_Shipping".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Shipping(bizMsg, sharedMsg);
            // Add Start 2018/07/30 QC#27222
            } else if ("NMAL6700Scrn00_TAB_Taxing".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_TAB_Taxing(bizMsg, sharedMsg);
            // Add End 2018/07/30 QC#27222
            } else if ("NMAL6700_NMAL2570".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL2570(bizMsg, sharedMsg);
            } else if ("NMAL6700_NMAL6720".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL6720(bizMsg, sharedMsg);
            } else if ("NMAL6700_NMAL6750".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL6750(bizMsg, sharedMsg);
            } else if ("NMAL6700_NMAL6760".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL6760(bizMsg, sharedMsg);
            } else if ("NMAL6700_NMAL6770".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL6770(bizMsg, sharedMsg);
            } else if ("NMAL6700_NMAL6050".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetTemplate(bizMsg, sharedMsg);
            } else if ("NMAL6700_NMAL6140".equals(screenAplID)) {
                // Mod Start 2018/12/20 QC#29486
                //doProcess_NMAL6700Scrn00_Search(bizMsg, sharedMsg);
                doProcess_NMAL6700Scrn00_Search(bizMsg, sharedMsg, true);
                // Mod End 2018/12/20 QC#29486
            } else if ("NMAL6700_NMAL2550".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL2550(bizMsg, sharedMsg);
            // START 2017/07/06 J.Kim [QC#16966,ADD]
            } else if ("NMAL6700_NMAL6170".equals(screenAplID)) {
                doProcess_NMAL6700_NMAL6170(bizMsg, sharedMsg);
            // END 2017/07/06 J.Kim [QC#16966,ADD]
            } else if ("NMAL6700Scrn00_GetCltCustTpNm".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetCltCustTpNm(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_GetCltPtfoNm".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetCltPtfoNm(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_GetCoaCcNm".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetCoaCcNm(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_ApplyTemplate".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_ApplyTemplate(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_GetDsAcctNm".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_GetDsAcctNm(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_OnChange_dsAcctItrlFlg".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_OnChange_dsAcctItrlFlg(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_OnChange_dsSpclHdlgTp".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_OnChange_dsSpclHdlgTp(bizMsg, sharedMsg);
            } else if (screenAplID.endsWith("NMAL6700Scrn00_DownloadLocationTemplate")) {
                doProcess_NMAL6700Scrn00_DownloadLocationTemplate(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_OnChange_frtCond".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_OnChange_frtCond(bizMsg, sharedMsg);
            } else if ("NMAL6700Scrn00_OnChange_lineBizTp".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_OnChange_lineBizTp(bizMsg, sharedMsg);
            } else if (screenAplID.endsWith("CMN_ColSave")) {
                doNothing();
            } else if (screenAplID.endsWith("CMN_ColClear")) {
                doNothing();
            // Add QC#57316 Start
            } else if ("NMAL6700Scrn00_OpenTrx_Download".equals(screenAplID)) {
                doProcess_NMAL6700_OpenTrx_Download((NMAL6700CMsg) bizMsg, (NMAL6700SMsg) sharedMsg);
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
     * Method name: doProcess_NMAL6700_NMAL6750 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700_NMAL6750(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Mod Start 2018/12/20 QC#29486
        //doProcess_NMAL6700Scrn00_Search(cMsg, sMsg);
        doProcess_NMAL6700Scrn00_Search(cMsg, sMsg, true);
        // Mod End 2018/12/20 QC#29486
        // Del Start 2018/12/20 QC#29486
        //NMAL6700CommonLogic.getPageFromSMsg_TabContacts(cMsg, sMsg, sMsg.D.getValidCount());
        // Del End 2018/12/20 QC#29486
    }

    private void doProcess_NMAL6700_NMAL6760(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Mod Start 2018/12/20 QC#29486
        //search(cMsg, sMsg);
        search(cMsg, sMsg, true);
        // Mod End 2018/12/20 QC#29486
    }

    private void doProcess_NMAL6700_NMAL6770(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        if (NMAL6700Constant.BTN_OPEN_WIN_CTAC_SEARCH.equals(cMsg.xxScrEventNm.getValue())) {
            // Mod Start 2018/12/20 QC#29486
            //searchContacts(cMsg, sMsg);
            searchContacts(cMsg, sMsg, true);
            // Mod End 2018/12/20 QC#29486
        } else {
            searchCtacPsnNm(cMsg, sMsg, cMsg.xxCellIdx.getValueInt());
        }
    }

    private void searchCtacPsnNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int rowIndex) {
        String ctacPsnPkList = cMsg.G.no(rowIndex).xxGenlFldAreaTxt_G2.getValue();
        String[] ctacPsnPkArray = NMAL6700CommonLogic.splitByComma(ctacPsnPkList);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ctacPsnPkArray.length; i++) {
            String ctacPsnPk = ctacPsnPkArray[i];
            String ctacPsnNm = NMAL6700CommonLogic.getCtacPsnNm(new BigDecimal(ctacPsnPk), getGlobalCompanyCode());
            if (sb.length() > 0) {
                sb.append(NMAL6700Constant.CHAR_COMMA);
            }
            sb.append(ctacPsnNm);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.G.no(rowIndex).xxCustInvRuleRcpntTxt_G2, sb.toString());
    }

    private void doProcess_NMAL6700_NMAL2550(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        String scrEventNm = cMsg.xxScrEventNm.getValue();

        if ("OpenWin_InterCompany".equals(scrEventNm)) {
            doProcess_NMAL6700Scrn00_GetInterCompanyNm(cMsg, sMsg);
        } else if ("OpenWin_Coa".equals(scrEventNm)) {
            doProcess_NMAL6700Scrn00_GetCoaCcNm(cMsg, sMsg);
        } else if ("OpenWin_Acct".equals(scrEventNm)) {
            doProcess_NMAL6700Scrn00_GetCoaChNm(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6700_INIT <dd>The method
     * explanation: Initializing. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700_INIT(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Add Start 2018/01/22 QC#20291(Sol#348)
        S21UserProfileService userProfileService = getUserProfileService();
        // Add End 2018/01/22 QC#20291(Sol#348)

        clearMsg(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDRESSES);
        // START 2022/07/21 [QC#60111, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_CY, ZYPConstant.FLG_ON_Y);
        // END   2022/07/21 [QC#60111, ADD]

        createPullDownList(cMsg, sMsg);

        // Mod Start 2018/12/20 QC#29486
        //initSearch(cMsg, sMsg);
        initSearch(cMsg, sMsg, true);
        // Mod End 2018/12/20 QC#29486

        // Add Start 2018/01/22 QC#20291(Sol#348)
        changeCategory(userProfileService, cMsg);
        // Add End 2018/01/22 QC#20291(Sol#348)
    }

    // Add Start 2018/01/22 QC#20291(Sol#348)
    /**
     * changeCategory
     * @param userProfileService S21UserProfileService
     * @param cMsg NMAL6700CMsg
     */
    private void changeCategory(S21UserProfileService userProfileService, NMAL6700CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1)) {
            return;
        }

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        // Mod Start 2018/05/16 QC#26041
        //// When user add new account, user is not able to select
        //// category.
        //// It is not available to define FUNC_ID_ACCT_CUST_UPDATE and
        //// FUNC_ID_ACCT_PROS_UPDATE for one user.
        //// One user has either of one of FUNC_ID_ACCT_CUST_UPDATE or
        //// FUNC_ID_ACCT_PROS_UPDATE.
        //if (functionIds.contains(FUNC_ID_ACCT_CUST_UPDATE)) {
        //    ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H3, DS_ACCT_TP.CUSTOMER);
        //} else if (functionIds.contains(FUNC_ID_ACCT_PROS_UPDATE)) {
        // Del Start 2018/06/04 QC#26450
        // if (functionIds.contains(FUNC_ID_ACCT_PROS_UPDATE)) {
        //     // Mod End 2018/05/16 QC#26041
        //     ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H3, DS_ACCT_TP.PROSPECT);
        // }
        // Del End 2018/06/04 QC#26450
        // Add Start 2018/06/04 QC#26450
        if (functionIds.contains(FUNC_ID_ACCT_CUST_UPDATE)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H3, DS_ACCT_TP.CUSTOMER);
        } else if (functionIds.contains(FUNC_ID_ACCT_PROS_UPDATE)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H3, DS_ACCT_TP.PROSPECT);
        }
        // Add End 2018/06/04 QC#26450
    }
    // Add End 2018/01/22 QC#20291(Sol#348)

    /**
     * createPullDownList
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void createPullDownList(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
     // Header pulldown
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_TP.class, cMsg.dsAcctTpCd_H1, cMsg.dsAcctTpNm_H2);
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_CLS.class, cMsg.dsAcctClsCd_H1, cMsg.dsAcctClsNm_H2);
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_DLR.class, cMsg.dsAcctDlrCd_H1, cMsg.dsAcctDlrNm_H2);
        NMAL6700CommonLogic.createDsAcctItrlFlgPulldownList(cMsg);
        NMAL6700CommonLogic.createReasonPulldownList(cMsg);

        // Credit/Collection Tab pulldowna
        CCYTMsg ccyTMsg = null;
        ccyTMsg = (CCYTMsg) ZYPCodeDataUtil.findByCode(CCY.class, getGlobalCompanyCode(), CCY.US_DOLLAR);
        cMsg.ccyCd_U1.no(0).setValue(ccyTMsg.ccyCd.getValue());
        cMsg.ccyNm_U2.no(0).setValue(ccyTMsg.ccyNm.getValue());

        ZYPCodeDataUtil.createPulldownList(CUST_CR_RTG.class, cMsg.custCrRtgCd_U1, cMsg.custCrRtgNm_U2);
        ZYPCodeDataUtil.createPulldownList(CR_CHK_REQ_TP.class, cMsg.crChkReqTpCd_U1, cMsg.crChkReqTpNm_U2);
        ZYPCodeDataUtil.createPulldownList(CR_RISK_CLS.class, cMsg.crRiskClsCd_U1, cMsg.crRiskClsNm_U2);
        // Add Start 2018/01/25 QC#22095
        ZYPCodeDataUtil.createPulldownList(CONTR_CR_RISK_CLS.class, cMsg.contrCrRiskClsCd_U1, cMsg.contrCrRiskClsNm_U2);
        // Add End   2018/01/25 QC#22095
        ZYPCodeDataUtil.createPulldownList(PMT_TERM_CASH_DISC.class, cMsg.pmtTermCashDiscCd_U1, cMsg.pmtTermCashDiscNm_U2);
        ZYPCodeDataUtil.createPulldownList(DS_CLT_ACCT_STS.class, cMsg.dsCltAcctStsCd_U1, cMsg.dsCltAcctStsNm_U2);
        // Del Start 2018/07/30 QC#27222
//        ZYPCodeDataUtil.createPulldownList(DS_CUST_TAX_CALC.class, cMsg.dsCustTaxCalcCd_U1, cMsg.dsCustTaxCalcNm_U2);
        // Del End 2018/07/30 QC#27222
        ZYPCodeDataUtil.createPulldownList(DS_TAX_GRP_EXEM.class, cMsg.dsTaxGrpExemCd_U1, cMsg.dsTaxGrpExemNm_U2);
        ZYPCodeDataUtil.createPulldownList(DS_TAX_PRNT_TP.class, cMsg.dsTaxPrntTpCd_U1, cMsg.dsTaxPrntTpNm_U2);
        ZYPCodeDataUtil.createPulldownList(AR_STMT_ISS_CYCLE.class, cMsg.arStmtIssCycleCd_U1, cMsg.arStmtIssCycleNm_U2);

        // START 2018/04/16 E.Kameishi [QC#21037, ADD]
        ZYPCodeDataUtil.createPulldownList(AUTO_CASH_HRCH.class, cMsg.autoCashHrchCd_U1, cMsg.autoCashHrchNm_U2);
        // END 2018/04/16 E.Kameishi [QC#21037, ADD]
        // Invoice/Billing Tab pulldown
        ZYPCodeDataUtil.createPulldownList(DEF_BASE_TP.class, cMsg.defBaseTpCd_V1, cMsg.defBaseTpNm_V2);
        ZYPCodeDataUtil.createPulldownList(DEF_BASE_CYCLE.class, cMsg.defBaseCycleCd_V1, cMsg.defBaseCycleNm_V2);
        ZYPCodeDataUtil.createPulldownList(DEF_USG_TP.class, cMsg.defUsgTpCd_V1, cMsg.defUsgTpNm_V2);
        ZYPCodeDataUtil.createPulldownList(DEF_USG_CYCLE.class, cMsg.defUsgCycleCd_V1, cMsg.defUsgCycleNm_V2);
        // START 2022/03/22 [QC#59683, ADD]
        ZYPCodeDataUtil.createPulldownList(DS_INV_TGTR_TP.class, cMsg.dsInvTgtrTpCd_V2, cMsg.dsInvTgtrTpDescTxt_V1);
        // END   2022/03/22 [QC#59683, ADD]

        // set default value
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H3, DS_ACCT_TP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctItrlFlg_H3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.coaChCd_H1, NMAL6700CommonLogic.getDefCoaChCd());
        doProcess_NMAL6700Scrn00_GetCoaChNm(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_H1, NMAL6700CommonLogic.getDefCoaAfflCd());
        doProcess_NMAL6700Scrn00_GetInterCompanyNm(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddAttribute
     * method explanation: Add Attribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddAttribute(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        int rowNum = cMsg.K.getValidCount();
        if (rowNum == cMsg.K.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Attributes", String.valueOf(cMsg.K.length()) });
            return;
        }

        NMAL6700_KCMsg newKcMsg = new NMAL6700_KCMsg();

        int addNum = NMAL6700CommonLogic.getAddAttributeNum(cMsg.K);
        NMAL6700CommonLogic.createAttributePulldownList(newKcMsg, getGlobalCompanyCode());
        String addNumStr = String.valueOf(addNum);
        ZYPEZDItemValueSetter.setValue(newKcMsg.xxCtlNm_K1, CONTROL_STR + addNumStr);
        ZYPEZDItemValueSetter.setValue(newKcMsg.dsAcctRefAttrbNum_K1, addNumStr);
        newKcMsg.custEffLvlCd_K3.setValue(CUST_EFF_LVL.ACCOUNT_ONLY);

        NMAL6700CommonLogic.setNewAttribute(cMsg.K , newKcMsg , addNum);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteAttribute
     * method explanation: DeleteAttribute.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteAttribute(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.K, "xxChkBox_K1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        int index = 0;
        for (int i = 0; i < checkList.size(); i++) {
            index = checkList.get(i);
            if (ZYPCommonFunc.hasValue(cMsg.K.no(index).dsAcctRefAttrbPk_K1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(sMsg.T.getValidCount()).dsAcctRefAttrbPk_T1, cMsg.K.no(index).dsAcctRefAttrbPk_K1);
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(sMsg.T.getValidCount()).ezUpTime_T1, cMsg.K.no(index).ezUpTime_K1);
                ZYPEZDItemValueSetter.setValue(sMsg.T.no(sMsg.T.getValidCount()).ezUpTimeZone_T1, cMsg.K.no(index).ezUpTimeZone_K1);
                sMsg.T.setValidCount(sMsg.T.getValidCount() + 1);
            }
        }
        ZYPTableUtil.deleteRows(cMsg.K, checkList);
        ZYPTableUtil.deleteRows(sMsg.K, checkList);

    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddSpecialHandling <dd>
     * The method explanation: AddRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddSpecialHandling(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        int rowNum = cMsg.S.getValidCount();
        if (rowNum == cMsg.S.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Special Handling", String.valueOf(cMsg.S.length()) });
            return;
        }

        NMAL6700_SCMsg scMsg = cMsg.S.no(rowNum);
        scMsg.clear();
        NMAL6700CommonLogic.createSpecialHandlingPulldownList(scMsg, getGlobalCompanyCode());
        scMsg.custEffLvlCd_S3.setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        scMsg.effFromDt_S1.setValue(NMAL6700CommonLogic.getDefaultDate());

        cMsg.S.setValidCount(rowNum + 1);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteSpecialHandling
     * explanation: Delete SpecialHandling.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteSpecialHandling(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.S, "xxChkBox_S1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        int index = 0;
        for (int i = 0; i < checkList.size(); i++) {
            index = checkList.get(i);
            if (ZYPCommonFunc.hasValue(cMsg.S.no(index).dsCustSpclHdlgPk_S1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.H.no(sMsg.H.getValidCount()).dsCustSpclHdlgPk_H1, cMsg.S.no(index).dsCustSpclHdlgPk_S1);
                ZYPEZDItemValueSetter.setValue(sMsg.H.no(sMsg.H.getValidCount()).ezUpTime_H1, cMsg.S.no(index).ezUpTime_S1);
                ZYPEZDItemValueSetter.setValue(sMsg.H.no(sMsg.H.getValidCount()).ezUpTimeZone_H1, cMsg.S.no(index).ezUpTimeZone_S1);
                sMsg.H.setValidCount(sMsg.H.getValidCount() + 1);
            }
        }
        ZYPTableUtil.deleteRows(cMsg.S, checkList);
        ZYPTableUtil.deleteRows(sMsg.S, checkList);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddGrpAsg <dd>The method
     * explanation: AddRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddGrpAsg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Save current input
        saveCurrentPageToSMsg_TabMarketing(cMsg, sMsg);
        int rowNum = sMsg.E.getValidCount();
        if (rowNum == sMsg.E.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Group Assignments", String.valueOf(sMsg.E.length()) });
            return;
        }

        // Clear new row
        int addedRowIndex = sMsg.E.getValidCount();
        sMsg.E.no(addedRowIndex).clear();
        NMAL6700_ESMsg esMsg = sMsg.E.no(addedRowIndex);
        esMsg.effFromDt_E1.setValue(NMAL6700CommonLogic.getDefaultDate());

        sMsg.E.setValidCount(addedRowIndex + 1);
        // Display page of first added row
        NMAL6700CommonLogic.getPageFromSMsg_TabMarketing(cMsg, sMsg, addedRowIndex);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteGrpAsg <dd>The method
     * explanation: DeleteRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteGrpAsg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        //-----------
        // Save current input
        saveCurrentPageToSMsg_TabMarketing(cMsg, sMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.E, "xxChkBox_E1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        // Delete selected rows from screen
        int rearrangeRowIndex = 0;
        int pageNum = -1;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(i).xxChkBox_E1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.E.no(i).dsAcctGrpAsgPk_E1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.Q.no(sMsg.Q.getValidCount()).dsAcctGrpAsgPk_Q1, sMsg.E.no(i).dsAcctGrpAsgPk_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.Q.no(sMsg.Q.getValidCount()).ezUpTime_Q1, sMsg.E.no(i).ezUpTime_E1);
                    ZYPEZDItemValueSetter.setValue(sMsg.Q.no(sMsg.Q.getValidCount()).ezUpTimeZone_Q1, sMsg.E.no(i).ezUpTimeZone_E1);
                    sMsg.Q.setValidCount(sMsg.Q.getValidCount() + 1);
                }
                if (pageNum < 0) {
                    pageNum = i;
                } else {
                    if ((pageNum / cMsg.E.length()) != (i / cMsg.E.length())) {
                        pageNum = cMsg.xxPageShowFromNum_M1.getValueInt();
                    }
                }
                sMsg.E.no(i).clear();
            } else {
                if (rearrangeRowIndex < i) {
                    EZDMsg.copy(sMsg.E.no(i), null, sMsg.E.no(rearrangeRowIndex), null);
                }
                rearrangeRowIndex++;
            }
        }
        sMsg.E.setValidCount(rearrangeRowIndex);

        // Show first page
        NMAL6700CommonLogic.getPageFromSMsg_TabMarketing(cMsg, sMsg, pageNum);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddCertificationReq <dd>The method
     * explanation: AddCertificationReq.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddCertificationReq(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        int rowNum = cMsg.N.getValidCount();
        if (rowNum == cMsg.N.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Certification required", String.valueOf(cMsg.E.length()) });
            return;
        }

        NMAL6700_NCMsg ncMsg = cMsg.N.no(rowNum);
        ncMsg.clear();
        ncMsg.effFromDt_N1.setValue(NMAL6700CommonLogic.getDefaultDate());
        cMsg.N.setValidCount(rowNum + 1);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteCertificationReq <dd>The method
     * explanation: DeleteCertificationReq.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteCertificationReq(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.N, "xxChkBox_N1", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }

        int index = 0;
        for (int i = 0; i < selectedRows.size(); i++) {
            index = selectedRows.get(i);
            if (ZYPCommonFunc.hasValue(cMsg.N.no(index).svcAccsPmitPk_N1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).svcAccsPmitPk_R1, cMsg.N.no(index).svcAccsPmitPk_N1);
                ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).ezUpTime_R1, cMsg.N.no(index).ezUpTime_N1);
                ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).ezUpTimeZone_R1, cMsg.N.no(index).ezUpTimeZone_N1);
                sMsg.R.setValidCount(sMsg.R.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(sMsg.N, selectedRows);
        ZYPTableUtil.deleteRows(cMsg.N, selectedRows);

    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddMsgNote <dd>The method
     * explanation: Add Msg Note.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Save current input
        saveCurrentPageToSMsg_TabMsgNote(cMsg, sMsg);
        int rowNum = sMsg.J.getValidCount();
        if (rowNum == sMsg.J.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Msgs Notes", String.valueOf(sMsg.J.length()) });
            return;
        }

        // Clear new row
        int addedRowIndex = sMsg.J.getValidCount();
        sMsg.J.no(addedRowIndex).clear();
        NMAL6700_JSMsg jsMsg = sMsg.J.no(addedRowIndex);
        jsMsg.custEffLvlCd_J3.setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        String constString = ZYPCodeDataUtil.getVarCharConstValue(CONST_DS_CUST_MSG_TP_NOTE, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(jsMsg.dsCustMsgTpCd_J3, constString);

        sMsg.J.setValidCount(addedRowIndex + 1);
        // Display page of first added row
        NMAL6700CommonLogic.getPageFromSMsg_TabMsgNote(cMsg, sMsg, addedRowIndex);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteMsgNote <dd>The method
     * explanation: DeleteMsgNote.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Save current input
        saveCurrentPageToSMsg_TabMsgNote(cMsg, sMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.J, "xxChkBox_J1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        // Delete selected rows from screen
        int rearrangeRowIndex = 0;
        int pageNum = -1;
        for (int i = 0; i < sMsg.J.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.J.no(i).xxChkBox_J1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.J.no(i).dsCustSpclMsgPk_J1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsCustSpclMsgPk_X1, sMsg.J.no(i).dsCustSpclMsgPk_J1);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X1, sMsg.J.no(i).ezUpTime_J1);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X1, sMsg.J.no(i).ezUpTimeZone_J1);
                    sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
                }
                if (pageNum < 0) {
                    pageNum = i;
                } else {
                    if ((pageNum / cMsg.J.length()) != (i / cMsg.J.length())) {
                        pageNum = cMsg.xxPageShowFromNum_J1.getValueInt();
                    }
                }
                sMsg.J.no(i).clear();
            } else {
                if (rearrangeRowIndex < i) {
                    EZDMsg.copy(sMsg.J.no(i), null, sMsg.J.no(rearrangeRowIndex), null);
                }
                rearrangeRowIndex++;
            }
        }
        sMsg.J.setValidCount(rearrangeRowIndex);

        // Show first page
        NMAL6700CommonLogic.getPageFromSMsg_TabMsgNote(cMsg, sMsg, pageNum);
    }

     // 2018/12/10 QC#29315 Del Start
//    /**
//     * Method name: doProcess_NMAL6700Scrn00_InsertRowCarrierAcct <dd>The method
//     * explanation: InsertRowCarrierAcct.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NMAL6700Scrn00_InsertRowCarrierAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
//
//        int rowNum = cMsg.W.getValidCount();
//        if (rowNum == cMsg.W.length()) {
//            cMsg.setMessageInfo(NMAM8187E, new String[] {"CARRIER ACCOUNTS", String.valueOf(cMsg.W.length()) });
//            return;
//        }
//
//        NMAL6700_WCMsg ncMsg = cMsg.W.no(rowNum);
//        ncMsg.clear();
//
//        // Create Carrire pull down
//        S21SsmEZDResult resPullDown = NMAL6700Query.getInstance().getBankAcctPulldownList(getGlobalCompanyCode());
//        List<Map<String, Object>> resPullDownList = null;
//        if (resPullDown.isCodeNormal()) {
//            resPullDownList = (List<Map<String, Object>>) resPullDown.getResultObject();
//        }
//        int cnt = resPullDownList.size();
//        for (int i = 0; i < cnt; i++) {
//            Map<String, Object> carr = resPullDownList.get(i);
//            ZYPEZDItemValueSetter.setValue(ncMsg.vndCd_W1.no(i), (String) carr.get("VND_CD"));
//            ZYPEZDItemValueSetter.setValue(ncMsg.locNm_W2.no(i), (String) carr.get("LOC_NM"));
//        }
//        String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(cMsg.W.no(rowNum).effFromDt_W1, slsDt);
//        cMsg.W.setValidCount(rowNum + 1);
//    }
    // 2018/12/10 QC#29315 Del End

    // 2018/12/10 QC#29315 Del Start
//    /**
//     * Method name: doProcess_NMAL6700Scrn00_DeleteRowCarrierAcct <dd>The method
//     * explanation: DeleteRowCarrierAcct.
//     * @param cMsg Business Component Interface Message
//     * @param sMsg Global area information
//     */
//    private void doProcess_NMAL6700Scrn00_DeleteRowCarrierAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
//
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.W, "xxChkBox_W1", ZYPConstant.CHKBOX_ON_Y);
//        if (checkList.isEmpty()) {
//            cMsg.setMessageInfo(NMAM0835E);
//            return;
//        }
//        int index = 0;
//        for (int i = 0; i < checkList.size(); i++) {
//            index = checkList.get(i);
//            if (ZYPCommonFunc.hasValue(cMsg.W.no(index).dsAcctCarrPk_W1)) {
//                ZYPEZDItemValueSetter.setValue(sMsg.U.no(sMsg.U.getValidCount()).dsAcctCarrPk_U1, cMsg.W.no(index).dsAcctCarrPk_W1);
//                ZYPEZDItemValueSetter.setValue(sMsg.U.no(sMsg.U.getValidCount()).ezUpTime_U1, cMsg.W.no(index).ezUpTime_W1);
//                ZYPEZDItemValueSetter.setValue(sMsg.U.no(sMsg.U.getValidCount()).ezUpTimeZone_U1, cMsg.W.no(index).ezUpTimeZone_W1);
//                sMsg.U.setValidCount(sMsg.U.getValidCount() + 1);
//            }
//        }
//        ZYPTableUtil.deleteRows(cMsg.W, checkList);
//        ZYPTableUtil.deleteRows(sMsg.W, checkList);
//
//    }
    // 2018/12/10 QC#29315 Del End

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddRelnship <dd>The
     * method explanation: AddRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddRelnship(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        // Save current input
        saveCurrentPageToSMsg_TabRelnship(cMsg, sMsg);
        int rowNum = sMsg.C.getValidCount();
        if (rowNum == sMsg.C.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Relationships", String.valueOf(sMsg.C.length()) });
            return;
        }

        // Clear new row
        int addedRowIndex = sMsg.C.getValidCount();
        sMsg.C.no(addedRowIndex).clear();
        NMAL6700_CSMsg csMsg = sMsg.C.no(addedRowIndex);
        csMsg.dsAcctRelnTpCd_C3.setValue(DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        csMsg.effFromDt_C1.setValue(NMAL6700CommonLogic.getDefaultDate());
        sMsg.C.setValidCount(addedRowIndex + 1);
        // Display page of first added row
        getPageFromSMsg_TabRelnship(cMsg, sMsg, addedRowIndex);

    }

    private void doProcess_NMAL6700Scrn00_DeleteRelnship(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        // Save current input
        saveCurrentPageToSMsg_TabRelnship(cMsg, sMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.C, "xxChkBox_C1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }

        // Add Start 2017/12/11 QC#20357
        String glblCmpyCd = getGlobalCompanyCode();

        // Get Related Account for Related BillTo / ShipTo
        // Mod Start 2019/08/07 QC#52385
        //NMZC610001PMsg custInfoGetApiPMsg = NMAL6700CommonLogic.callCustomerInfomationGetApi(cMsg, glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        List<Map<String, Object>> relCustInfoDefBillShip = //
            NMAL6700CommonLogic.getRelatedCustomerInfoForDefaultBillShip(cMsg, glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        // Mod End 2019/08/07 QC#52385

        // Del Start 2019/08/07 QC#52385
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                cMsg.setMessageInfo(msgId, msgPrms);
//
//                if (msgId.endsWith("E")) {
//                    return;
//                }
//            }
//        }
        // Del End 2019/08/07 QC#52385

        boolean errorFlag = false;
        // Del Start 2018/03/20 QC#25000
        //List<String> checkedRelatedAccoutNumList = new ArrayList<String>();
        // Del End 2018/03/20 QC#25000

        // Check delete relation and Default Bill To / Default Ship To
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue())) {
                continue;
            }

            String relatedAccountNum = sMsg.C.no(i).dsAcctNum_C1.getValue();

            // Del Start 2018/03/20 QC#25000
            //if (checkedRelatedAccoutNumList.contains(relatedAccountNum)) {
            //    continue;
            //}
            // Del End 2018/03/20 QC#25000

            boolean deleteCustCdErr = false;
            String deleteBillToCustCd = null;
            String deleteShipToCustCd = null;

            // Get Bill To Code / Ship To Code
            // Mod Start 2019/08/07 QC#52385
            //for (int j = 0; j < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); j++) {
            //    NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(j);
            //    if (NMAL6700CommonLogic.hasValueAndEquals(relatedAccountNum, inPmsg.relnDsAcctNum.getValue())) {
            //        deleteBillToCustCd = inPmsg.billToCustCd.getValue();
            //        deleteShipToCustCd = inPmsg.shipToCustCd.getValue();
            for (int j = 0; j < relCustInfoDefBillShip.size(); j++) {
                Map<String, Object> result = relCustInfoDefBillShip.get(j);
                String relnDsAcctNum = (String) result.get("R_RELN_DS_ACCT_NUM");

                if (NMAL6700CommonLogic.hasValueAndEquals(relatedAccountNum, relnDsAcctNum)) {
                    deleteBillToCustCd = (String) result.get("R_BILL_TO_CUST_CD");
                    deleteShipToCustCd = (String) result.get("R_SHIP_TO_CUST_CD");
                    // Mod End 2019/08/07 QC#52385
                    // Del Start 2018/03/16 QC#20357-1
                    //break;
                //}
            //}
            // Del End 2018/03/16 QC#20357-1

            if (NMAL6700CommonLogic.isDafaultBillTo(cMsg, deleteBillToCustCd)) {
                // If Bill To Code is used for Default Bill To
                if (!NMAL6700CommonLogic.isCorrectBillTo(sMsg, relatedAccountNum)) {
                    deleteCustCdErr = true;
                }
            }

            if (NMAL6700CommonLogic.isDafaultShipTo(cMsg, deleteShipToCustCd)) {
                // If Ship To Code is used for Default Ship To
                if (!NMAL6700CommonLogic.isCorrectShipTo(sMsg, relatedAccountNum)) {
                    deleteCustCdErr = true;
                }
            }

            if (deleteCustCdErr) {
                sMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAM8622E, new String[] {"Default Bill To or Default Ship To" });
                errorFlag = true;
                // Add Start 2018/03/16 QC#20357-1
                break;
                // Add End 2018/03/16 QC#20357-1
            }
                // Add Start 2018/03/16 QC#20357-1
                }
            }
            // Add End 2018/03/16 QC#20357-1

            // Del Start 2018/03/20 QC#25000
            //checkedRelatedAccoutNumList.add(relatedAccountNum);
            // Del End 2018/03/20 QC#25000
        }

        if(errorFlag){
            NMAL6700CommonLogic.showFirstErrorPage_TabRelnShips(cMsg, sMsg);
            return;
        }
        // Add End 2017/12/11 QC#20357

        // Delete selected rows from screen
        int rearrangeRowIndex = 0;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).xxChkBox_C1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).dsAcctRelnPk_C1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(sMsg.O.getValidCount()).dsAcctRelnPk_O1, sMsg.C.no(i).dsAcctRelnPk_C1);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(sMsg.O.getValidCount()).ezUpTime_O1, sMsg.C.no(i).ezUpTime_C1);
                    ZYPEZDItemValueSetter.setValue(sMsg.O.no(sMsg.O.getValidCount()).ezUpTimeZone_O1, sMsg.C.no(i).ezUpTimeZone_C1);
                    sMsg.O.setValidCount(sMsg.O.getValidCount() + 1);
                }
                sMsg.C.no(i).clear();
            } else {
                if (rearrangeRowIndex < i) {
                    EZDMsg.copy(sMsg.C.no(i), null, sMsg.C.no(rearrangeRowIndex), null);
                }
                rearrangeRowIndex++;
            }
        }
        sMsg.C.setValidCount(rearrangeRowIndex);

        // Show first page
        getPageFromSMsg_TabRelnship(cMsg, sMsg, 0);

    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddTransactionRule <dd>
     * The method explanation: AddRelnship.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddTransactionRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        // Save current input
        saveCurrentPageToSMsg_TabTransactionRule(cMsg, sMsg);
        int rowNum = cMsg.F.getValidCount();
        if (rowNum == cMsg.F.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Transaction Rule", String.valueOf(cMsg.F.length()) });
            return;
        }

        // =======================================================
        // Set new row's value
        // =======================================================

        // Clear new row
        int addedRowIndex = sMsg.F.getValidCount();
        sMsg.F.no(addedRowIndex).clear();
        NMAL6700_FSMsg fsMsg = sMsg.F.no(addedRowIndex);
        fsMsg.custEffLvlCd_F3.setValue(CUST_EFF_LVL.ACCOUNT_ONLY);

        sMsg.F.setValidCount(addedRowIndex + 1);
        // Display page of first added row
        getPageFromSMsg_TabTransaction(cMsg, sMsg, addedRowIndex);

    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteTransactionRule
     * explanation: DeleteTransaction.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteTransactionRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        // Save current input
        saveCurrentPageToSMsg_TabTransactionRule(cMsg, sMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.F, "xxChkBox_F1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        // Delete selected rows from screen
        int rearrangeRowIndex = 0;
        for (int i = 0; i < sMsg.F.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.F.no(i).xxChkBox_F1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.F.no(i).dsCustTrxRulePk_F1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.V.no(sMsg.V.getValidCount()).dsCustTrxRulePk_V1, sMsg.F.no(i).dsCustTrxRulePk_F1);
                    ZYPEZDItemValueSetter.setValue(sMsg.V.no(sMsg.V.getValidCount()).ezUpTime_V1, sMsg.F.no(i).ezUpTime_F1);
                    ZYPEZDItemValueSetter.setValue(sMsg.V.no(sMsg.V.getValidCount()).ezUpTimeZone_V1, sMsg.F.no(i).ezUpTimeZone_F1);
                    sMsg.V.setValidCount(sMsg.V.getValidCount() + 1);
                }
                sMsg.F.no(i).clear();
            } else {
                if (rearrangeRowIndex < i) {
                    EZDMsg.copy(sMsg.F.no(i), null, sMsg.F.no(rearrangeRowIndex), null);
                }
                rearrangeRowIndex++;
            }
        }
        sMsg.F.setValidCount(rearrangeRowIndex);

        // Show first page
        getPageFromSMsg_TabTransaction(cMsg, sMsg, 0);
    }

    private void saveCurrentPageToSMsg_TabTransactionRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {


        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            NMAL6700_FCMsg fcMsg = cMsg.F.no(i);
            // New insert line
            if (!ZYPCommonFunc.hasValue(fcMsg.dsCustTrxRulePk_F1)) {
                NMAL6700CommonLogic.createTransactionRulePulldownList(fcMsg, getGlobalCompanyCode());

            }
        }

        // Save CMsg to SMsg
        savePageToSMsg_TabTransactionRule(cMsg, sMsg, cMsg.xxPageShowFromNum_F1.getValueInt() - 1);
    }

    private static void savePageToSMsg_TabTransactionRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            EZDMsg.copy(cMsg.F.no(i), null, sMsg.F.no(startIndexOfSMsg + i), null);
        }
    }
    private void getPageFromSMsg_TabTransaction(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.F);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.F.length()) * cMsg.F.length();

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.F.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.F.getValidCount()) {
                EZDMsg.copy(sMsg.F.no(startIndexOfSMsg + i), null, cMsg.F.no(i), null);
                NMAL6700_FCMsg fcMsg = cMsg.F.no(i);
                NMAL6700CommonLogic.createTransactionRulePulldownList(fcMsg, getGlobalCompanyCode());
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.F.setValidCount(copiedRows);

        // Set page number
        if (sMsg.F.getValidCount() > 0) {
            int totalRecordCount = sMsg.F.getValidCount();
            int maxRowPerPage = cMsg.F.length();

            // Record count
            cMsg.xxPageShowFromNum_F1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_F1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_F1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_F1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_F1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_F1.clear();
            cMsg.xxPageShowFromNum_F1.clear();
            cMsg.xxPageShowToNum_F1.clear();
            cMsg.xxPageShowCurNum_F1.clear();
            cMsg.xxPageShowOfNum_F1.clear();
        }
    }

    private void saveCurrentPageToSMsg_TabMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        for (int i = 0; i < cMsg.J.getValidCount(); i++) {
            NMAL6700_JCMsg jcMsg = cMsg.J.no(i);
            // New insert line
            if (!ZYPCommonFunc.hasValue(jcMsg.dsCustSpclMsgPk_J1)) {
                NMAL6700CommonLogic.createMsgNotePulldownList(jcMsg, getGlobalCompanyCode());
            }
        }

        // Save CMsg to SMsg
        savePageToSMsg_TabMsgNote(cMsg, sMsg, cMsg.xxPageShowFromNum_J1.getValueInt() - 1);
    }

    private static void savePageToSMsg_TabMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.J.getValidCount(); i++) {
            EZDMsg.copy(cMsg.J.no(i), null, sMsg.J.no(startIndexOfSMsg + i), null);
        }
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddInvRule <dd>The method
     * explanation: Add Invoice Rule.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddInvRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        // Save current input
        saveCurrentPageToSMsg_TabInvRule(cMsg, sMsg);
        int rowNum = sMsg.G.getValidCount();
        if (rowNum == sMsg.G.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Invoice Rule", String.valueOf(sMsg.G.length()) });
            return;
        }

        // Clear new row
        int addedRowIndex = sMsg.G.getValidCount();
        sMsg.G.no(addedRowIndex).clear();
        NMAL6700_GSMsg gsMsg = sMsg.G.no(addedRowIndex);
        gsMsg.custEffLvlCd_G3.setValue(CUST_EFF_LVL.ACCOUNT_ONLY);

        sMsg.G.setValidCount(addedRowIndex + 1);
        // Display page of first added row
        getPageFromSMsg_TabInvRule(cMsg, sMsg, addedRowIndex);

    }
    /**
     * Method name: doProcess_NMAL6700Scrn00_DeleteInvRule
     * method explanation: DeleteInvoiceRule.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteInvRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(cMsg.G, "xxChkBox_G1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        int index = 0;
        for (int i = 0; i < checkList.size(); i++) {
            index = checkList.get(i);
            if (ZYPCommonFunc.hasValue(cMsg.G.no(index).dsCustInvRulePk_G1)) {
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).dsCustInvRulePk_Y1, cMsg.G.no(index).dsCustInvRulePk_G1);
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTime_Y1, cMsg.G.no(index).ezUpTime_G1);
                ZYPEZDItemValueSetter.setValue(sMsg.Y.no(sMsg.Y.getValidCount()).ezUpTimeZone_Y1, cMsg.G.no(index).ezUpTimeZone_G1);
                sMsg.Y.setValidCount(sMsg.Y.getValidCount() + 1);
            }
        }
        ZYPTableUtil.deleteRows(cMsg.G, checkList);
        ZYPTableUtil.deleteRows(sMsg.G, checkList);

    }

    private void saveCurrentPageToSMsg_TabInvRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        PMT_TERM_CASH_DISCTMsgArray pmtTermCashDiscTMsgArray = NMAL6700CommonLogic.getPmtTermCashDisc(cMsg, glblCmpyCd);

        if (pmtTermCashDiscTMsgArray.length() == 0) {
            cMsg.setMessageInfo(NMAM8111E, new String[] {"PMT_TERM_CASH_DISC" });
            return;
        }
        for (int i = 0; i < cMsg.G.getValidCount(); i++) {
            NMAL6700_GCMsg gcMsg = cMsg.G.no(i);
            // New insert line
            if (!ZYPCommonFunc.hasValue(gcMsg.dsCustInvRulePk_G1)) {
                NMAL6700CommonLogic.createInvRulePulldownList(gcMsg, glblCmpyCd);
            }

            for (int k = 0; k < pmtTermCashDiscTMsgArray.getValidCount(); k++) {
                ZYPEZDItemValueSetter.setValue(gcMsg.pmtTermCashDiscCd_G1.no(k), pmtTermCashDiscTMsgArray.no(k).pmtTermCashDiscCd.getValue());
                ZYPEZDItemValueSetter.setValue(gcMsg.pmtTermCashDiscNm_G2.no(k), pmtTermCashDiscTMsgArray.no(k).pmtTermCashDiscNm.getValue());
            }
        }

        // Save CMsg to SMsg
        savePageToSMsg_TabInvRule(cMsg, sMsg, cMsg.xxPageShowFromNum_G1.getValueInt() - 1);
    }

    private static void savePageToSMsg_TabInvRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.G.getValidCount(); i++) {
            EZDMsg.copy(cMsg.G.no(i), null, sMsg.G.no(startIndexOfSMsg + i), null);
        }
    }

    private void getPageFromSMsg_TabInvRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.G);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.G.length()) * cMsg.G.length();

        PMT_TERM_CASH_DISCTMsgArray pmtTermCashDiscTMsgArray = NMAL6700CommonLogic.getPmtTermCashDisc(cMsg, getGlobalCompanyCode());

        if (pmtTermCashDiscTMsgArray.length() == 0) {
            cMsg.setMessageInfo(NMAM8111E, new String[] {"PMT_TERM_CASH_DISC" });
            return;
        }
        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.G.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.G.getValidCount()) {
                EZDMsg.copy(sMsg.G.no(startIndexOfSMsg + i), null, cMsg.G.no(i), null);
                NMAL6700_GCMsg gcMsg = cMsg.G.no(i);
                NMAL6700CommonLogic.createInvRulePulldownList(gcMsg, getGlobalCompanyCode());
                for (int k = 0; k < pmtTermCashDiscTMsgArray.getValidCount(); k++) {
                    ZYPEZDItemValueSetter.setValue(gcMsg.pmtTermCashDiscCd_G1.no(k), pmtTermCashDiscTMsgArray.no(k).pmtTermCashDiscCd.getValue());
                    ZYPEZDItemValueSetter.setValue(gcMsg.pmtTermCashDiscNm_G2.no(k), pmtTermCashDiscTMsgArray.no(k).pmtTermCashDiscNm.getValue());
                }
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.G.setValidCount(copiedRows);

        // Set page number
        if (sMsg.G.getValidCount() > 0) {
            int totalRecordCount = sMsg.G.getValidCount();
            int maxRowPerPage = cMsg.G.length();

            // Record count
            cMsg.xxPageShowFromNum_G1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_G1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_G1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_G1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_G1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_G1.clear();
            cMsg.xxPageShowFromNum_G1.clear();
            cMsg.xxPageShowToNum_G1.clear();
            cMsg.xxPageShowCurNum_G1.clear();
            cMsg.xxPageShowOfNum_G1.clear();
        }
    }

    private void saveCurrentPageToSMsg_TabRelnship(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            NMAL6700_CCMsg ccMsg = cMsg.C.no(i);
            // New insert line
            if (!ZYPCommonFunc.hasValue(ccMsg.dsAcctRelnPk_C1)) {
                NMAL6700CommonLogic.createDsAcctRelnTpPulldownList(ccMsg);
            }
        }

        // Save CMsg to SMsg
        savePageToSMsg_TabRelnship(cMsg, sMsg, cMsg.xxPageShowFromNum_C1.getValueInt() - 1);
    }

    private static void savePageToSMsg_TabRelnship(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            EZDMsg.copy(cMsg.C.no(i), null, sMsg.C.no(startIndexOfSMsg + i), null);
        }
    }

    private void getPageFromSMsg_TabRelnship(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int indexOfSMsg) {
        ZYPTableUtil.clear(cMsg.C);

        int startIndexOfSMsg = (indexOfSMsg / cMsg.C.length()) * cMsg.C.length();

        // Copy page from SMsg to CMsg
        int copiedRows = 0;
        for (int i = 0; i < cMsg.C.length(); i++) {
            if (startIndexOfSMsg + i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.no(startIndexOfSMsg + i), null, cMsg.C.no(i), null);
                NMAL6700_CCMsg ccMsg = cMsg.C.no(i);
                NMAL6700CommonLogic.createDsAcctRelnTpPulldownList(ccMsg);
                copiedRows++;
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(copiedRows);

        // Set page number
        if (sMsg.C.getValidCount() > 0) {
            int totalRecordCount = sMsg.C.getValidCount();
            int maxRowPerPage = cMsg.C.length();

            // Record count
            cMsg.xxPageShowFromNum_C1.setValue(startIndexOfSMsg + 1);
            cMsg.xxPageShowToNum_C1.setValue(Math.min(totalRecordCount, startIndexOfSMsg + maxRowPerPage));
            cMsg.xxPageShowOfNum_C1.setValue(totalRecordCount);
            // Page count
            cMsg.xxPageShowCurNum_C1.setValue(Math.min((totalRecordCount - 1) / maxRowPerPage, startIndexOfSMsg / maxRowPerPage) + 1);
            cMsg.xxPageShowTotNum_C1.setValue(startIndexOfSMsg / maxRowPerPage + 1);

        } else {
            cMsg.xxPageShowTotNum_C1.clear();
            cMsg.xxPageShowFromNum_C1.clear();
            cMsg.xxPageShowToNum_C1.clear();
            cMsg.xxPageShowCurNum_C1.clear();
            cMsg.xxPageShowOfNum_C1.clear();
        }
    }

    private void saveCurrentPageToSMsg_TabMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            NMAL6700_ECMsg ecMsg = cMsg.E.no(i);
            // New insert line
            if (!ZYPCommonFunc.hasValue(ecMsg.dsAcctGrpAsgPk_E1)) {
                // mod start 2023/07/18 QC#61421
                //ZYPCodeDataUtil.createPulldownList(DS_BIZ_AREA.class, ecMsg.dsBizAreaCd_E1, ecMsg.dsBizAreaNm_E2);
                NMAL6700CommonLogic.createDsBizAreaPulldownForMarketingTab(getGlobalCompanyCode(), ecMsg);
                // mod end 2023/07/18 QC#61421
              }
        }

        // Save CMsg to SMsg
        savePageToSMsg_TabMarketing(cMsg, sMsg, cMsg.xxPageShowFromNum_M1.getValueInt() - 1);
    }

    private static void savePageToSMsg_TabMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            EZDMsg.copy(cMsg.E.no(i), null, sMsg.E.no(startIndexOfSMsg + i), null);
        }
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_GetCoaChNm <dd>The
     * method explanation: GetCoaChNm.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_GetCoaChNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        COA_CHTMsg coaChTMsg = NMAL6700CommonLogic.findCoaCh(getGlobalCompanyCode(), cMsg.coaChCd_H1.getValue());
        if (coaChTMsg == null) {
            cMsg.coaChCd_H1.setErrorInfo(1, NMAM8186E, new String[] {"GL Sales Channel" });
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.coaChNm_H1, coaChTMsg.coaChNm);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_GetInterCompanyNm <dd>The
     * method explanation: GetInterCompanyNm.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_GetInterCompanyNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        COA_AFFLTMsg coaAfflTMsg = NMAL6700CommonLogic.findCoaAffl(getGlobalCompanyCode(), cMsg.coaAfflCd_H1.getValue());
        if (coaAfflTMsg == null) {
            cMsg.coaAfflCd_H1.setErrorInfo(1, NMAM8186E, new String[] {"GL Intercompany Code" });
            return;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm_H1, coaAfflTMsg.coaAfflNm);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_GetCltCustTpNm
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_GetCltCustTpNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        CLT_CUST_TPTMsg cltCustTpTMsg = new CLT_CUST_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltCustTpTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cltCustTpTMsg.cltCustTpCd, cMsg.cltCustTpCd_U1.getValue());
        cltCustTpTMsg = (CLT_CUST_TPTMsg) S21CacheTBLAccessor.findByKey(cltCustTpTMsg);
        if (cltCustTpTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpNm_U1, cltCustTpTMsg.cltCustTpNm);
        }
    }
    /**
     * Method name: doProcess_NMAL6700Scrn00_GetCltPtfoNm
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_GetCltPtfoNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.getCltPtfoNm(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_GetCoaCcNm
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_GetCoaCcNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        COA_CCTMsg coaCcTMsg = new COA_CCTMsg();
        ZYPEZDItemValueSetter.setValue(coaCcTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(coaCcTMsg.coaCcCd, cMsg.coaCcCd_F1.getValue());
        coaCcTMsg = (COA_CCTMsg) S21CacheTBLAccessor.findByKey(coaCcTMsg);
        if (coaCcTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.coaCcNm_F1, coaCcTMsg.coaCcNm);
        } else {
            cMsg.coaCcCd_F1.setErrorInfo(1, NZZM0000E);
        }
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Addresses <dd>The
     * method explanation: TAB_Addresses.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Addresses(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ADDRESSES);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_BankAcct <dd>The
     * method explanation: TAB_BankAcct.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_BankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_BANK_ACCT);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Contacts <dd>The
     * method explanation: TAB_Contacts.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Contacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_CONTACTS);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_CrClt <dd>The method
     * explanation: TAB_CrClt.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_CrClt(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_CR_CLT);
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H3.getValue())) {
            NMAL6700CommonLogic.setTemplateForCredit(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Hierarchy <dd>The
     * method explanation: TAB_Hierarchy.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Hierarchy(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Mod Start 2018/12/20 QC#29486
        //searchHierarchy(cMsg, sMsg);
        searchHierarchy(cMsg, sMsg, true);
        // Mod End 2018/12/20 QC#29486

        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_HIERARCHY);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_InvBllg <dd>The
     * method explanation: TAB_InvBllg.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_InvBllg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_INV_BLLG);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Marketing <dd>The
     * method explanation: TAB_Marketing.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Marketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_MARKETING);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_MsgNote <dd>The
     * method explanation: TAB_MsgNote.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_MsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_MSG_NOTE);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Relnships <dd>The
     * method explanation: TAB_Relnships.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Relnships(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_RELNSHIPS);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Transactions <dd>The
     * method explanation: TAB_Transactions.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Transactions(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_TRANSACTIONS);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Shipping <dd>The
     * method explanation: TAB_Shipping
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Shipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SHIPPING);
    }

    // Add Start 2018/07/30 QC#27222
    /**
     * Method name: doProcess_NMAL6700Scrn00_TAB_Taxing <dd>The
     * method explanation: TAB_Taxing
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_TAB_Taxing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_TAXING);
    }
    // Add End 2018/07/30 QC#27222

    /**
     * Method name: doProcess_NMAL6700Scrn00_CMN_Reset <dd>The method
     * explanation: Reset values.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_CMN_Reset(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        doProcess_NMAL6700_INIT(cMsg, sMsg);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_CMN_Submit <dd>The method
     * explanation: Reset values.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_CMN_Submit(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        if ("W".equals(cMsg.getMessageKind())) {
            return;
        }

        clearMsg(cMsg, sMsg);

        createPullDownList(cMsg, sMsg);

        // Mod Start 2018/12/20 QC#29486
        //initSearch(cMsg, sMsg);
        initSearch(cMsg, sMsg, false);
        // Mod End 2018/12/20 QC#29486

        if (TAB_HIERARCHY.equals(cMsg.xxDplyTab.getValue())) {
            // Mod Start 2018/12/20 QC#29486
            //searchHierarchy(cMsg, sMsg);
            searchHierarchy(cMsg, sMsg, false);
            // Mod End 2018/12/20 QC#29486
        }
    }

    // Mod Start 2018/12/20 QC#29486
    ///**
    // * Method name: doProcess_NMAL6700Scrn00_Search <dd>The method
    // * explanation: Serch Information. <dd>Remarks:
    // * @param cMsg Business Component Interface Message
    // * @param sMsg Global area information
    // */
    //private void doProcess_NMAL6700Scrn00_Search(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    //    search(cMsg, sMsg);
    //}
    /**
     * Method name: doProcess_NMAL6700Scrn00_Search <dd>The method
     * explanation: Serch Information. <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param isDispWarnInfo Warning / Information Display Flag
     */
    private void doProcess_NMAL6700Scrn00_Search(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        search(cMsg, sMsg, isDispWarnInfo);
    }
    // Mod End 2018/12/20 QC#29486

    /**
     * Method name: doProcess_NMAL6700Scrn00_PageNext <dd>The method
     * explanation: PageNext.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700Scrn00_PageNext(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        String xxDplyTab = cMsg.xxDplyTab.getValue();
        if (TAB_RELNSHIPS.equals(xxDplyTab)) {
            pageNextRelnships(cMsg, sMsg);
        } else if (TAB_CONTACTS.equals(xxDplyTab)) {
            pageNextContacts(cMsg, sMsg);
        } else if (TAB_ADDRESSES.equals(xxDplyTab)) {
            pageNextAddresses(cMsg, sMsg);
        } else if (TAB_MARKETING.equals(xxDplyTab)) {
            pageNextMarketing(cMsg, sMsg);
        } else if (TAB_BANK_ACCT.equals(xxDplyTab)) {
            pageNextBankAcct(cMsg, sMsg);
        } else if (TAB_MSG_NOTE.equals(xxDplyTab)) {
            pageNextMsgNote(cMsg, sMsg);
        } else if (TAB_SHIPPING.equals(xxDplyTab)) { // 2018/02/14 QC#20297(Sol#379) add start
            pageNextShipping(cMsg, sMsg);
         // 2018/02/14 QC#20297(Sol#379) add end
        }
    }

    /**
     * Method name: pageNextRelnships <dd>The method explanation:
     * PageNext For TAB_RELNSHIPS.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextRelnships(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabRelnships(cMsg, sMsg, cMsg.xxPageShowFromNum_C1.getValueInt(), cMsg.xxPageShowFromNum_C1.getValueInt() + cMsg.C.length());

    }

    /**
     * Method name: pageNextContacts
     * The method explanation: PageNext For TAB_CONTACTS.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.changePage_TabContacts(cMsg, sMsg, cMsg.xxPageShowFromNum_D1.getValueInt(), cMsg.xxPageShowFromNum_D1.getValueInt() + cMsg.D.length());

    }

    /**
     * Method name: pageNextAddresses <dd>The method explanation:
     * PageNext For TAB_ADDRESSES.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextAddresses(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.copyBizToShareA(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.C);

        int pageFrom = cMsg.xxPageShowToNum_A1.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_A1.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A1.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * Method name: doProcess_NMAL6700_CMN_Download <dd>The method
     * explanation: Address Tab Download.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700_CMN_Download(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6700Query.getInstance().getClass());
            if (TAB_ADDRESSES.equals(cMsg.xxDplyTab.getValue())) {
                //create csv file
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");
                Map<String, Object> ssMParam = NMAL6700Query.getSsmParam(getGlobalCompanyCode(), cMsg, sMsg, LIMIT_DL_ROWNUM + 1);
                ps = ssmLLClient.createPreparedStatement("getLocationList", ssMParam, execParam);
            } else if (TAB_CONTACTS.equals(cMsg.xxDplyTab.getValue())) {
                //create csv file
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_CTAC), ".csv");
                Map<String, Object> ssMParam = NMAL6700Query.getSsmParamCtac(getGlobalCompanyCode(), cMsg, sMsg, LIMIT_DL_ROWNUM + 1);
                ps = ssmLLClient.createPreparedStatement("getCtacPsnList", ssMParam, execParam);
            // Add Start 2023/01/20 QC#61011
            } else if (TAB_RELNSHIPS.equals(cMsg.xxDplyTab.getValue())) {
                savePageToSMsg_TabRelnship(cMsg, sMsg, cMsg.xxPageShowFromNum_C1.getValueInt() - 1 );
                //create csv file
                cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_RELNSHIP), ".csv");
            }
            // Add End 2023/01/20 QC#61011
            // Mod Start 2023/01/20 QC#61011
            //}
            //rs = ps.executeQuery();
            //if (TAB_ADDRESSES.equals(cMsg.xxDplyTab.getValue())) {
            //    writeCsvFile(cMsg, rs);
            //} else if (TAB_CONTACTS.equals(cMsg.xxDplyTab.getValue())) {
            //    writeCsvFileCtac(cMsg, rs);
            //}
            if (TAB_ADDRESSES.equals(cMsg.xxDplyTab.getValue())) {
                rs = ps.executeQuery();
                writeCsvFile(cMsg, rs);
            } else if (TAB_CONTACTS.equals(cMsg.xxDplyTab.getValue())){
                rs = ps.executeQuery();
                writeCsvFileCtac(cMsg, rs);
            // Mod End 2023/01/20 QC#61011
            // Add Start 2023/01/20 QC#61011
            } else if (TAB_RELNSHIPS.equals(cMsg.xxDplyTab.getValue())) {
                writeCsvFileReln(cMsg, sMsg);
            }
            // Add End 2023/01/20 QC#61011
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * writeCsvFile
     * @param cMsg NMAL6700CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public void writeCsvFile(NMAL6700CMsg cMsg, ResultSet rs) throws SQLException {

        // Add Start 2019/05/23 QC#50101
        ArrayList<String> allLocList = new ArrayList<String>();
        ArrayList<String> allNotInactivePrimLocList = new ArrayList<String>();
        // Add End 2019/05/23 QC#50101
        NMAL6700F00FMsg fMsg = new NMAL6700F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            // Add Start 2019/05/23 QC#50101
            String locNum = rs.getString("LOC_NUM");
            // Add End 2019/05/23 QC#50101

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.locNum_A0, rs.getString("LOC_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr_A0, rs.getString("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr_A0, rs.getString("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_A0, rs.getString("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.stCd_A0, rs.getString("ST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.postCd_A0, rs.getString("POST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AB, rs.getString("BILL_TO_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AS, rs.getString("SHIP_TO_FLG"));
            // Del Start 2019/05/23 QC#50101
            //ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AP, rs.getString("PRIMARY_FLG"));
            // Del End 2019/05/23 QC#50101
            ZYPEZDItemValueSetter.setValue(fMsg.xxRelnDsAcctTxt_A0, rs.getString("XX_RELN_DS_ACCT_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AF, NMAL6700CommonLogic.formatDt(rs.getString("EFF_FROM_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_AT, NMAL6700CommonLogic.formatDt(rs.getString("EFF_THRU_DT")));
            String rgtnStsCd = (String) rs.getString("RGTN_STS_CD");
            String status = "";
            if (RGTN_STS.PENDING_COMPLETION.equals(rgtnStsCd)) {
                status = RGTN_STS_INPROCESS;
            } else if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)) {
                status = RGTN_STS_ACTIVE;
            } else if (RGTN_STS.TERMINATED.equals(rgtnStsCd)) {
                status = RGTN_STS_INACTIVE;
            }

            // Add Start 2019/05/23 QC#50101
            String primaryFlg = (String) rs.getString("PRIMARY_FLG");
            if (allLocList.contains(locNum)) {

                if (RGTN_STS_INACTIVE.equals(status) || ZYPConstant.FLG_OFF_N.equals(primaryFlg)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AP, ZYPConstant.FLG_OFF_N);
                } else {
                    if (allNotInactivePrimLocList.contains(locNum)) {
                        ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AP, ZYPConstant.FLG_OFF_N);
                    } else {
                        allNotInactivePrimLocList.add(locNum);
                        ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AP, primaryFlg);
                    }
                }
            } else {
                allLocList.add(locNum);
                if (RGTN_STS_INACTIVE.equals(status)) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AP, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AP, primaryFlg);

                    if (ZYPConstant.FLG_ON_Y.equals(primaryFlg)) {
                        allNotInactivePrimLocList.add(locNum);
                    }
                }
            }
            // Add End 2019/05/23 QC#50101

            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctStsNm_A0, status);

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * writeCsvFileCtac
     * @param cMsg NMAL6700CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public void writeCsvFileCtac(NMAL6700CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL6700F01FMsg fMsg = new NMAL6700F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        //write header
        writeCsvFileHeaderCtac(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnPk_D0, rs.getBigDecimal("CTAC_PSN_PK"));
            // Mod Start 2017/12/06 QC#21897
            //ZYPEZDItemValueSetter.setValue(fMsg.ctacTpNm_D0, rs.getString("CTAC_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctacTpDescTxt_D0, rs.getString("CTAC_TP_DESC_TXT"));
            // Mod End 2017/12/06 QC#21897
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnFirstNm_D0, rs.getString("CTAC_PSN_FIRST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnLastNm_D0, rs.getString("CTAC_PSN_LAST_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPsnDeptNm_D0, rs.getString("DS_CTAC_PSN_DEPT_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_D0, rs.getString("CTAC_PSN_TEL_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_D1, rs.getString("CTAC_PSN_EML_ADDR"));
            ZYPEZDItemValueSetter.setValue(fMsg.ctacPsnExtnNum_D0, rs.getString("CTAC_PSN_EXTN_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D0, NMAL6700CommonLogic.formatDt(rs.getString("EFF_FROM_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_D1, NMAL6700CommonLogic.formatDt(rs.getString("EFF_THRU_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.dsPrimLocFlg_D0, rs.getString("DS_PRIM_LOC_FLG"));
            if (ZYPConstant.FLG_ON_Y.equals(rs.getString("CTAC_PSN_ACTV_FLG"))) {
                ZYPEZDItemValueSetter.setValue(fMsg.dplStsNm_D0, ACTIVE_STS);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.dplStsNm_D0, INACTIVE_STS);
            }
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    // Add Start 2023/01/20 QC#61011
    /**
     * writeCsvFileReln
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     * @throws SQLException SQLException
     */
    public void writeCsvFileReln(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) throws SQLException {

        NMAL6700F04FMsg fMsg = new NMAL6700F04FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        writeCsvFileHeaderReln(csvOutFile, fMsg, cMsg);
        String glblCmpyCd = getGlobalCompanyCode();
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            String dsAcctRelnTpNm = NMAL6700Query.getInstance().getDsAcctRelnTpNm(glblCmpyCd, sMsg.C.no(i).dsAcctRelnTpCd_C3.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctRelnTpNm_C1, dsAcctRelnTpNm);
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_C1, sMsg.C.no(i).dsAcctNum_C1);
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_C1, sMsg.C.no(i).dsAcctNm_C1);

            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).xxChkBox_CB)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_CB, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_CB, sMsg.C.no(i).xxChkBox_CB);
            }

            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).xxChkBox_CS)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_CS, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_CS, sMsg.C.no(i).xxChkBox_CS);
            }

            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).xxChkBox_CR)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_CR, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_CR, sMsg.C.no(i).xxChkBox_CR);
            }

            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_C1, sMsg.C.no(i).effFromDt_C1);
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_C1, sMsg.C.no(i).effThruDt_C1);

            csvOutFile.write();
        }

        csvOutFile.close();
    }
    // Add End 2023/01/20 QC#61011
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NMAL6700F00FMsg fMsg, NMAL6700CMsg cMsg) {
        final String[] csvHeader = new String[] {
                "Location#"
                , "Address1"
                , "Address2"
                , "City"
                , "State"
                , "Postal Code"
                , "Bill To"
                , "Ship To"
                , "Primary"
                , "Related Acct"
                , "Start Date"
                , "End Date"
                , "Status"
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    private void writeCsvFileHeaderCtac(ZYPCSVOutFile csvOutFile, NMAL6700F01FMsg fMsg, NMAL6700CMsg cMsg) {
        final String[] csvHeader = new String[] {
                "Contact ID"
                , "Relationship to CSA"
                , "First Name"
                , "Last Name"
                , "Department"
                , "Email-Work"
                , "Phone-Work"
                , "Ext"
                , "Start Date"
                , "End Date"
                , "Primary"
                , "Status"
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    // Add Start 2023/01/20 QC#61011
      private void writeCsvFileHeaderReln(ZYPCSVOutFile csvOutFile, NMAL6700F04FMsg fMsg, NMAL6700CMsg cMsg) {
        final String[] csvHeader = new String[] {
                "Relationship Type"
                , "Related Account#"
                , "Related Account Name"
                , "Bill To"
                , "Ship To"
                , "Reciprocal"
                , "Start Date"
                , "End Date"
        };
        csvOutFile.writeHeader(csvHeader);
    }

    // Add End 2023/01/20 QC#61011
    /**
     * Method name: pageNextMarketing <dd>The method explanation:
     * PageNext For TAB_MARKETING.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        changePage_TabMarketing(cMsg, sMsg, cMsg.xxPageShowFromNum_M1.getValueInt(), cMsg.xxPageShowFromNum_M1.getValueInt() + cMsg.E.length());
    }

    /**
     * Method name: pageNextMsgNote <dd>The method explanation:
     * PageNext For TAB_MSG_NOTE.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabMsgNote(cMsg, sMsg, cMsg.xxPageShowFromNum_J1.getValueInt(), cMsg.xxPageShowFromNum_J1.getValueInt() + cMsg.J.length());

    }

    /**
     * Method name: pageNextBankAcct <dd>The method explanation:
     * PageNext For TAB_BANK_ACCT.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.copyBizToShareI(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.I);

        int pageFrom = cMsg.xxPageShowToNum_I1.getValueInt();
        int i = pageFrom;
        for (; i < pageFrom + cMsg.I.length(); i++) {
            if (i < sMsg.I.getValidCount()) {
                EZDMsg.copy(sMsg.I.no(i), null, cMsg.I.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.I.setValidCount(i - pageFrom);

        cMsg.xxPageShowFromNum_I1.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_I1.setValue(pageFrom + cMsg.I.getValidCount());
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_PagePrev <dd>The method
     * explanation: PagePrev.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700Scrn00_PagePrev(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        String xxDplyTab = cMsg.xxDplyTab.getValue();
        if (TAB_RELNSHIPS.equals(xxDplyTab)) {
            pagePrevRelnships(cMsg, sMsg);
        } else if (TAB_CONTACTS.equals(xxDplyTab)) {
            pagePrevContacts(cMsg, sMsg);
        } else if (TAB_ADDRESSES.equals(xxDplyTab))  {
            pagePrevAddresses(cMsg, sMsg);
        } else if (TAB_MARKETING.equals(xxDplyTab)) {
            pagePrevMarketing(cMsg, sMsg);
        } else if (TAB_BANK_ACCT.equals(xxDplyTab)) {
            pagePrevBankAcct(cMsg, sMsg);
        } else if (TAB_MSG_NOTE.equals(xxDplyTab)) {
            pagePrevMsgNote(cMsg, sMsg);
        } else if (TAB_SHIPPING.equals(xxDplyTab)) {  // 2018/02/14 QC#20297(Sol#379) add start
            pagePrevShipping(cMsg, sMsg);
            // 2018/02/14 QC#20297(Sol#379) add end
        }
    }

    /**
     * Method name: pagePrevAddresses <dd>The method explanation:
     * PagePrev For TAB_ADDRESSES.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevAddresses(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.copyBizToShareA(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);

        int pageFrom = cMsg.xxPageShowFromNum_A1.getValueInt() - cMsg.A.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum_A1.setValue(pageFrom);
        cMsg.xxPageShowToNum_A1.setValue(pageFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Method name: pagePrevMarketing <dd>The method explanation:
     * PagePrev For TAB_MARKETING.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabMarketing(cMsg, sMsg, cMsg.xxPageShowFromNum_M1.getValueInt(), cMsg.xxPageShowFromNum_M1.getValueInt() - cMsg.E.length());

    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_PageJump <dd>The method
     * explanation: PagePrev.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700Scrn00_PageJump(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        String xxDplyTab = cMsg.xxDplyTab.getValue();
        if (TAB_ADDRESSES.equals(xxDplyTab)) {
            pageJumpAddresses(cMsg, sMsg);
        } else if (TAB_RELNSHIPS.equals(xxDplyTab)) {
            pageJumpRelnShips(cMsg, sMsg);
        } else if (TAB_CONTACTS.equals(xxDplyTab)) {
                pageJumpContacts(cMsg, sMsg);
        } else if (TAB_BANK_ACCT.equals(xxDplyTab)) {
            pageJumpBankAcct(cMsg, sMsg);
        } else if (TAB_MSG_NOTE.equals(xxDplyTab)) {
            pageJumpMsgNote(cMsg, sMsg);
        } else if (TAB_SHIPPING.equals(xxDplyTab)) { // 2018/02/14 QC#20297(Sol#379) add start
            pageJumpShipping(cMsg, sMsg);
            // 2018/02/14 QC#20297(Sol#379) add end
        }

    }

    private void doProcess_NMAL6700Scrn00_ShowAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        int index = cMsg.xxCellIdx.getValueInt();
        String dsAcctNum = NMAL6700CommonLogic.getDsAcctNm(cMsg.C.no(index).dsAcctNum_C1.getValue(), getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            cMsg.C.no(index).dsAcctNum_C1.setErrorInfo(1, NMAM8121E, new String[] {"Account#" });
        }
    }

    /**
     * Method name: pageJumpAddresses <dd>The method explanation:
     * PagePrev For TAB_ADDRESSES.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageJumpAddresses(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.copyBizToShareA(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.A);

        int pageFrom = cMsg.A.length() * (cMsg.xxPageShowCurNum_A1.getValueInt() - 1);
        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pageFrom);

        // set value to pagenation items
        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum_A1.setValue(pageFrom);
        cMsg.xxPageShowToNum_A1.setValue(pageFrom + cMsg.A.getValidCount() - 1);
    }

    /**
     * Method name: pageJumpRelnShips
     * The method explanation: PageJump For TAB_RelationShips.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageJumpRelnShips(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabRelnships(cMsg, sMsg, cMsg.xxPageShowFromNum_C1.getValueInt(), (cMsg.xxPageShowCurNum_C1.getValueInt() - 1) * cMsg.C.length() + 1);
    }

    /**
     * Method name: pageJumpContacts
     * The method explanation: PageJump For TAB_Contacts.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageJumpContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.changePage_TabContacts(cMsg, sMsg, cMsg.xxPageShowFromNum_D1.getValueInt(), (cMsg.xxPageShowCurNum_D1.getValueInt() - 1) * cMsg.D.length() + 1);
    }

    /**
     * Method name: pagePrevMsgNote <dd>The method explanation:
     * PagePrev For TAB_MSG_NOTE.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabMsgNote(cMsg, sMsg, cMsg.xxPageShowFromNum_J1.getValueInt(), cMsg.xxPageShowFromNum_J1.getValueInt() - cMsg.J.length());

    }

    /**
     * Method name: pageJumpMsgNote <dd>The method explanation:
     * PagePrev For TAB_MSG_NOTE.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageJumpMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabMsgNote(cMsg, sMsg, cMsg.xxPageShowFromNum_J1.getValueInt(), (cMsg.xxPageShowCurNum_J1.getValueInt() - 1) * cMsg.J.length() + 1);

    }

    /**
     * Method name: pagePrevBankAcct <dd>The method explanation:
     * PagePrev For TAB_BANK_ACCT.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.copyBizToShareI(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.I);

        int pageFrom = cMsg.xxPageShowFromNum_I1.getValueInt() - cMsg.I.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + cMsg.I.length(); i++) {
            if (i < sMsg.I.getValidCount()) {
                EZDMsg.copy(sMsg.I.no(i), null, cMsg.I.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.I.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum_I1.setValue(pageFrom);
        cMsg.xxPageShowToNum_I1.setValue(pageFrom + cMsg.I.getValidCount() - 1);
    }

    /**
     * Method name: pageJumpBankAcct <dd>The method explanation:
     * PagePrev For TAB_BANK_ACCT.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageJumpBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700CommonLogic.copyBizToShareI(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.I);

        int pageFrom = cMsg.I.length() * (cMsg.xxPageShowCurNum_I1.getValueInt() - 1);
        int i = pageFrom;
        for (; i < pageFrom + cMsg.I.length(); i++) {
            if (i < sMsg.I.getValidCount()) {
                EZDMsg.copy(sMsg.I.no(i), null, cMsg.I.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.I.setValidCount(i - pageFrom);

        // set value to pagenation items
        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum_I1.setValue(pageFrom);
        cMsg.xxPageShowToNum_I1.setValue(pageFrom + cMsg.I.getValidCount() - 1);
    }

    /**
     * Method name: pagePrevRelnships
     * The method explanation: PagePrev For TAB_RELNSHIPS.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevRelnships(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        changePage_TabRelnships(cMsg, sMsg, cMsg.xxPageShowFromNum_C1.getValueInt(), cMsg.xxPageShowFromNum_C1.getValueInt() - cMsg.C.length());
    }

    /**
     * Method name: pagePrevContacts
     * The method explanation: PagePrev For TAB_CONTACTS.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        NMAL6700CommonLogic.changePage_TabContacts(cMsg, sMsg, cMsg.xxPageShowFromNum_D1.getValueInt(), cMsg.xxPageShowFromNum_D1.getValueInt() - cMsg.D.length());
    }

    // Mod Start 2018/12/20 QC#29486
    //private void initSearch(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void initSearch(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486

        if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1)) {
            return;
        }
        String glblCmpyCd = getGlobalCompanyCode();

        sMsg.xxDplyTab.setValue(cMsg.xxDplyTab.getValue());

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsAcctInfo(glblCmpyCd, cMsg.dsAcctNum_H1.getValue(), cMsg.xxChkBox_AX.getValue());
        if (!res.isCodeNormal()) {
            String[] args = {"DS_ACCT_CUST, DS_ACCT_PROS" };
            cMsg.setMessageInfo(NMAM8111E, args);
            return;
        }

        List list = (List) res.getResultObject();
        Map map = (Map) list.get(0);
        setDsAcctInfo(cMsg, sMsg, map);

        res = NMAL6700Query.getInstance().getPrinCust(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            list = (List) res.getResultObject();
            map = (Map) list.get(0);
            setPrinCust(cMsg, map);
        }

        // Mod Start 2018/12/20 QC#29486
        //searchAddresses(cMsg, sMsg);
        //searchRelnships(cMsg, sMsg);
        //searchContacts(cMsg, sMsg);
        //searchMarketing(cMsg, sMsg);
        //searchTransactionRule(cMsg, sMsg);
        searchAddresses(cMsg, sMsg, isDispWarnInfo);
        searchRelnships(cMsg, sMsg, isDispWarnInfo);
        searchContacts(cMsg, sMsg, isDispWarnInfo);
        searchMarketing(cMsg, sMsg, isDispWarnInfo);
        searchTransactionRule(cMsg, sMsg, isDispWarnInfo);
        // Mod End 2018/12/20 QC#29486
        searchCrClt(cMsg, sMsg);
        // Mod Start 2018/12/20 QC#29486
        //searchInvBilling(cMsg, sMsg);
        //searchBankAcct(cMsg, sMsg);
        //searchMsgNote(cMsg, sMsg);
        searchInvBilling(cMsg, sMsg, isDispWarnInfo);
        searchBankAcct(cMsg, sMsg, isDispWarnInfo);
        searchMsgNote(cMsg, sMsg, isDispWarnInfo);
        // Mod End 2018/12/20 QC#29486
        // 2018/02/14 QC#20297(Sol#379) add start
        // Mod Start 2018/12/20 QC#29486
        //searchShipping(cMsg, sMsg);
        searchShipping(cMsg, sMsg, isDispWarnInfo);
        // Mod End 2018/12/20 QC#29486
        // 2018/02/14 QC#20297(Sol#379) add end

    }
    // Mod Start 2018/12/20 QC#29486
    //private void search(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void search(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486

        if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1)) {
            return;
        }

        sMsg.xxDplyTab.setValue(cMsg.xxDplyTab.getValue());

        String xxDplyTab = cMsg.xxDplyTab.getValue();
        if (TAB_ADDRESSES.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchAddresses(cMsg, sMsg);
            searchAddresses(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_HIERARCHY.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchHierarchy(cMsg, sMsg);
            searchHierarchy(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_RELNSHIPS.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchRelnships(cMsg, sMsg);
            searchRelnships(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_CONTACTS.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchContacts(cMsg, sMsg);
            searchContacts(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_MARKETING.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchMarketing(cMsg, sMsg);
            searchMarketing(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_TRANSACTIONS.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchTransactionRule(cMsg, sMsg);
            searchTransactionRule(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_CR_CLT.equals(xxDplyTab)) {
             searchCrClt(cMsg, sMsg);
        } else if (TAB_INV_BLLG.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchInvBilling(cMsg, sMsg);
            searchInvBilling(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_BANK_ACCT.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchBankAcct(cMsg, sMsg);
            searchBankAcct(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        } else if (TAB_MSG_NOTE.equals(xxDplyTab)) {
            // Mod Start 2018/12/20 QC#29486
            //searchMsgNote(cMsg, sMsg);
            searchMsgNote(cMsg, sMsg, isDispWarnInfo);
            // Mod End 2018/12/20 QC#29486
        }

    }

    // Mod Start 2018/12/20 QC#29486
    //@SuppressWarnings("unchecked")
    //private void searchAddresses(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    @SuppressWarnings("unchecked")
    private void searchAddresses(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        // Add Start 2019/05/23 QC#50101
        ArrayList<String> allLocList = new ArrayList<String>();
        ArrayList<String> allNotInactivePrimLocList = new ArrayList<String>();
        // Add End 2019/05/23 QC#50101

        S21SsmEZDResult res = null;
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_HD.getValue())) {
            res = NMAL6700Query.getInstance().getLocationList(glblCmpyCd, cMsg, sMsg);
        } else {
            res = NMAL6700Query.getInstance().getProspectLocationList(glblCmpyCd, cMsg, sMsg);
        }
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > sMsg.A.length()) {
                listSize = sMsg.A.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_ADDRESSES);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            // Mod Start 2018/04/27 QC#25154
            //Cache
            Map<String, String> cache = new HashMap<String, String>();

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                // Mod Start 2019/05/23 QC#50101
                //setLocationInfo(map, sMsg.A.no(i), cache);
                setLocationInfo(map, sMsg.A.no(i), cache, allLocList, allNotInactivePrimLocList);
                // Mod End 2019/05/23 QC#50101
            }

            //Cache Clear
            cache.clear();
            // Mod End 2018/04/27 QC#25154
            sMsg.A.setValidCount(listSize);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A1, BigDecimal.ZERO);
        NMAL6700CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void searchCrClt(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsAcctCrPrflInfo(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            Map map = (Map) list.get(0);
            setDsAcctCrPrflInfo(glblCmpyCd, map, cMsg);
        }
    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchInvBilling(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void searchInvBilling(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();

        // Mod Start 2018/12/20 QC#29486
        //searchDsCustInvRuleList(cMsg, sMsg, glblCmpyCd);
        searchDsCustInvRuleList(cMsg, sMsg, glblCmpyCd, isDispWarnInfo);
        // Mod End 2018/12/20 QC#29486

        // Mod Start 2018/12/20 QC#29486
        //searchDsAcctRefAttrbList(cMsg, sMsg, glblCmpyCd);
        searchDsAcctRefAttrbList(cMsg, sMsg, glblCmpyCd, isDispWarnInfo);
        // Mod End 2018/12/20 QC#29486
    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchDsCustInvRuleList(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, String glblCmpyCd) {
    private void searchDsCustInvRuleList(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, String glblCmpyCd, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        ZYPTableUtil.clear(cMsg.G);
        ZYPTableUtil.clear(sMsg.G);

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsCustInvRuleList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > cMsg.G.length()) {
                listSize = cMsg.G.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_INV_BLLG);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_GSMsg gsMsg = sMsg.G.no(i);
                setDsCustInvRuleInfo(map, gsMsg);
            }
            sMsg.G.setValidCount(listSize);
            EZDMsg.copy(sMsg.G, null, cMsg.G, null);
        }
        NMAL6700CommonLogic.getNamePsnAndCtac(sMsg, getGlobalCompanyCode());
        getPageFromSMsg_TabInvRule(cMsg, sMsg, 0);

    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchDsAcctRefAttrbList(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, String glblCmpyCd) {
    private void searchDsAcctRefAttrbList(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, String glblCmpyCd, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        ZYPTableUtil.clear(cMsg.K);
        ZYPTableUtil.clear(sMsg.K);
        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsAcctRefAttrbList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > cMsg.K.length()) {
                listSize = cMsg.K.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_INV_BLLG);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_KSMsg ksMsg = sMsg.K.no(i);
                setDsAcctRefAttrbInfo(map, i + 1, ksMsg);

            }
            sMsg.K.setValidCount(listSize);
            EZDMsg.copy(sMsg.K, null, cMsg.K, null);
            for (int i = 0; i < listSize; i++) {
                NMAL6700_KCMsg kcMsg = cMsg.K.no(i);
                NMAL6700CommonLogic.createAttributePulldownList(kcMsg, glblCmpyCd);
            }
        }
    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void searchBankAcct(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.I);
        ZYPTableUtil.clear(sMsg.I);
        // 2018/12/10 QC#29315 Del Start
//        ZYPTableUtil.clear(cMsg.W);
//        ZYPTableUtil.clear(sMsg.W);
//
//        S21SsmEZDResult resCarrier = NMAL6700Query.getInstance().getDsAcctCarrList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
//        if (resCarrier.isCodeNormal()) {
//            List list = (List) resCarrier.getResultObject();
//            int listSize = list.size();
//            if (listSize > sMsg.W.length()) {
//                listSize = sMsg.W.length();
//                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
//                // Add Start 2018/12/12 QC#29486
//                cMsg.xxDplyTab.setValue(TAB_BANK_ACCT);
//                // Add End 2018/12/12 QC#29486
//            }
//            // Create Carrire pull down
//            S21SsmEZDResult resPullDown = NMAL6700Query.getInstance().getBankAcctPulldownList(glblCmpyCd);
//            List<Map<String, Object>> resPullDownList = null;
//            if (resPullDown.isCodeNormal()) {
//                resPullDownList = (List<Map<String, Object>>) resPullDown.getResultObject();
//            }
//            for (int i = 0; i < listSize; i++) {
//                Map map = (Map) list.get(i);
//                NMAL6700_WSMsg wsMsg = sMsg.W.no(i);
//                setDsAcctCarr(map, wsMsg, resPullDownList);
//
//            }
//            sMsg.W.setValidCount(listSize);
//            EZDMsg.copy(sMsg.W, null, cMsg.W, null);
//        }
        // 2018/12/10 QC#29315 Del End

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsCustBankAcctList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > sMsg.I.length()) {
                listSize = sMsg.I.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_BANK_ACCT);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_ISMsg isMsg = sMsg.I.no(i);
                setDsCustBankAcctInfo(map, isMsg);

            }
            sMsg.I.setValidCount(listSize);
            EZDMsg.copy(sMsg.I, null, cMsg.I, null);

        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_I1, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_I1, BigDecimal.ZERO);
        NMAL6700CommonLogic.copyFromSMsgOntoCmsgI(cMsg, sMsg);
    }

    // Mod Start 2018/12/20 QC#29486
    //@SuppressWarnings("unchecked")
    //private void searchMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    @SuppressWarnings("unchecked")
    private void searchMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.J);
        ZYPTableUtil.clear(sMsg.J);

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsCustSpclMsgList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue(), sMsg);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            // Mod Start 2018/12/20 QC#29486
            if (listSize > sMsg.J.length()) {
                listSize = sMsg.J.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_MSG_NOTE);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_JSMsg jsMsg = sMsg.J.no(i);
                setDsCustSpclMsgInfo(cMsg, map, jsMsg);
            }
            sMsg.J.setValidCount(listSize);
        }
        // Show first page to screen
        NMAL6700CommonLogic.getPageFromSMsg_TabMsgNote(cMsg, sMsg, 0);
    }

    // Mod Start 2018/12/20 QC#29486
    //@SuppressWarnings("unchecked")
    //private void searchTransactionRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    @SuppressWarnings("unchecked")
    private void searchTransactionRule(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.F);
        ZYPTableUtil.clear(sMsg.F);

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsCustTrxRuleList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > cMsg.F.length()) {
                listSize = cMsg.F.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_FSMsg fsMsg = sMsg.F.no(i);
                setDsCustTrxRuleInfo(glblCmpyCd, map, fsMsg);
            }
            sMsg.F.setValidCount(listSize);
        }
        // Show first page to screen
        getPageFromSMsg_TabTransaction(cMsg, sMsg, 0);

        ZYPTableUtil.clear(cMsg.S);
        ZYPTableUtil.clear(sMsg.S);

        res = NMAL6700Query.getInstance().getDsCustSpclHdlgList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > cMsg.S.length()) {
                listSize = cMsg.S.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_SSMsg ssMsg = sMsg.S.no(i);
                setDsCustSpclHdlgInfo(glblCmpyCd, map, ssMsg);
            }
            sMsg.S.setValidCount(listSize);
            EZDMsg.copy(sMsg.S, null, cMsg.S, null);

            for (int i = 0; i < listSize; i++) {
                NMAL6700_SCMsg scMsg = cMsg.S.no(i);
                NMAL6700CommonLogic.createSpecialHandlingPulldownList(scMsg, getGlobalCompanyCode());
                if (ZYPCommonFunc.hasValue(scMsg.dsSpclHdlgTpCd_S3.getValue())) {
                    DS_SPCL_HDLG_TPTMsgArray dsSpclHdlgTpArry = NMAL6700CommonLogic.getDsSpclHdlgTp(scMsg.dsSpclHdlgTpCd_S3.getValue(), glblCmpyCd);
                    for (int idx = 0; idx < dsSpclHdlgTpArry.getValidCount(); idx++) {
                        scMsg.dsSpclHdlgTpValCd_S1.no(idx).setValue(dsSpclHdlgTpArry.no(idx).dsSpclHdlgTpValCd.getValue());
                        scMsg.dsSpclHdlgTpValNm_S2.no(idx).setValue(dsSpclHdlgTpArry.no(idx).dsSpclHdlgTpValNm.getValue());
                    }
                }
            }
        }

    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void searchContacts(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.D);
        ZYPTableUtil.clear(sMsg.D);

        S21SsmEZDResult res = NMAL6700Query.getInstance().getCtacPsnList(glblCmpyCd, cMsg, sMsg);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > sMsg.D.length()) {
                listSize = sMsg.D.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_CONTACTS);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }

            // Mod Start 2018/04/27 QC#25154
            Map<String, String> cache = new HashMap<String, String>();

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_DSMsg dsMsg = sMsg.D.no(i);
                setCtacPsnInfo(map, dsMsg, cache);
            }

            cache.clear();
            // Mod End 2018/04/27 QC#25154
            sMsg.D.setValidCount(listSize);

            // Show first page to screen
            NMAL6700CommonLogic.getPageFromSMsg_TabContacts(cMsg, sMsg, 0);

        }

    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void searchMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.E);
        ZYPTableUtil.clear(sMsg.E);
        ZYPTableUtil.clear(cMsg.N);
        ZYPTableUtil.clear(sMsg.N);

        // get Acct, Business Hours
        S21SsmEZDResult resAcct = NMAL6700Query.getInstance().getDsAcctList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        Map acctMap = null;
        if (resAcct.isCodeNormal()) {
            List list = (List) resAcct.getResultObject();
            if (list.size() > 0) {
                acctMap = (Map) list.get(0);
                setAcctInfo(cMsg, acctMap);
            }
        }

        // get CERTIFICATION REQUIRED
        S21SsmEZDResult resCertificationReq = NMAL6700Query.getInstance().getCertificationReqList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        if (resCertificationReq.isCodeNormal()) {
            List list = (List) resCertificationReq.getResultObject();
            int listSize = list.size();
            if (listSize > sMsg.N.length()) {
                listSize = sMsg.N.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_MARKETING);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_NSMsg nsMsg = sMsg.N.no(i);
                setCertificationReq(glblCmpyCd, map, nsMsg);
            }
            sMsg.N.setValidCount(listSize);
            EZDMsg.copy(sMsg.N, null, cMsg.N, null);

        }

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsAcctGrpAsgList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue(), sMsg.E.length() + 1);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > sMsg.E.length()) {
                listSize = sMsg.E.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_MARKETING);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_ESMsg esMsg = sMsg.E.no(i);
                setDsAcctGrpAsgInfo(glblCmpyCd, map, esMsg);
            }
            sMsg.E.setValidCount(listSize);
        }
        // Show first page to screen
        NMAL6700CommonLogic.getPageFromSMsg_TabMarketing(cMsg, sMsg, 0);

    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchRelnships(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void searchRelnships(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486

        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.C);
        ZYPTableUtil.clear(sMsg.C);

        S21SsmEZDResult res = NMAL6700Query.getInstance().getRelnshipList(glblCmpyCd, cMsg, sMsg);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (list.size() > sMsg.C.length()) {
                listSize = sMsg.C.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize)});
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_RELNSHIPS);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }

            // Mod Start 2018/04/27 QC#25154
            Map<String, String> cache = new HashMap<String, String>();

            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_CSMsg csMsg = sMsg.C.no(i);
                setRelnshipInfo(glblCmpyCd, map, csMsg, cache);
            }
            cache.clear();
            // Mod End 2018/04/27 QC#25154
            sMsg.C.setValidCount(listSize);
        }
        // Show first page to screen
        getPageFromSMsg_TabRelnship(cMsg, sMsg, 0);

    }

    // Mod Start 2018/12/20 QC#29486
    //private void searchHierarchy(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    private void searchHierarchy(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.B);

        String tagetDsAcctNum = cMsg.dsAcctNum_H1.getValue();
        boolean inActiveFlag = ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_BI.getValue());
        boolean showAllAddressFlg = ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_BH.getValue());

        int locSortNumInAcct = 0;
        String preDsAcctNum = "";
        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getHierarchyList(cMsg, glblCmpyCd, tagetDsAcctNum, inActiveFlag, showAllAddressFlg);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                // Mod Start 2018/12/12 QC#29486
                //if (!chkMaxLineSize(i + 1, cMsg.B.length(), cMsg)) {
                // Mod Start 2018/12/20 QC#29486
                //if (!chkMaxLineSize(i + 1, cMsg.B.length(), cMsg, TAB_HIERARCHY)) {
                if (!chkMaxLineSize(i + 1, cMsg.B.length(), cMsg, TAB_HIERARCHY, isDispWarnInfo)) {
                    // Mod End 2018/12/20 QC#29486
                    // Mod End 2018/12/12 QC#29486
                    return;
                }
                Map map = (Map) resultList.get(i);

                String dsAcctNum = (String) map.get("DS_ACCT_NUM");
                if (!dsAcctNum.equals(preDsAcctNum)) {
                    preDsAcctNum = dsAcctNum;
                    locSortNumInAcct = 0;
                } else {
                    locSortNumInAcct++;
                }

                setHierarchyTreeData(cMsg.B.no(i), map, locSortNumInAcct);
                cMsg.B.setValidCount(i + 1);
            }
        }
    }

    // Mod Start 2018/12/12 QC#29486
    //private boolean chkMaxLineSize(int lineSize, int maxLineSize, NMAL6700CMsg cMsg) {
    // Mod Start 2018/12/20 QC#29486
    //private boolean chkMaxLineSize(int lineSize, int maxLineSize, NMAL6700CMsg cMsg, String tabName) {
    private boolean chkMaxLineSize(int lineSize, int maxLineSize, NMAL6700CMsg cMsg, String tabName, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        // Mod End 2018/12/12 QC#29486
        if (lineSize > maxLineSize) {
            // Add Start 2018/12/20 QC#29486
            if (isDispWarnInfo) {
            // Add End 2018/12/20 QC#29486
            cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(maxLineSize), String.valueOf(maxLineSize) });
            // Add Start 2018/12/12 QC#29486
            cMsg.xxDplyTab.setValue(tabName);
            // Add End 2018/12/12 QC#29486
            // Add Start 2018/12/20 QC#29486
            }
            // Add End 2018/12/20 QC#29486
            return false;
        }
        return true;
    }

    private void setHierarchyTreeData(NMAL6700_BCMsg bcMsg, Map map, int locSortNumInAcct) {
        String treePath = (String) map.get("TREE_PATH");
        String dsAcctNum = (String) map.get("DS_ACCT_NUM");
        List<String> list = convertTreePathToList(treePath, dsAcctNum);

        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B1, "");
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B2, list.get(0));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B3, list.get(1));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B4, list.get(2));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B5, list.get(3));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B6, list.get(4));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B7, list.get(5));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B8, list.get(6));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B9, list.get(7));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_BA, list.get(8));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_BB, list.get(9));
        if (locSortNumInAcct > 0) {
            ZYPEZDItemValueSetter.setValue(bcMsg.locNum_BM, dsAcctNum);
        }

        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNum_B, (String) map.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctNm_B, (String) map.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.xxAllLineAddr_B, (String) map.get("ADDRESS"));
        ZYPEZDItemValueSetter.setValue(bcMsg.ctyAddr_B, (String) map.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bcMsg.stCd_B, (String) map.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bcMsg.postCd_B, (String) map.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(bcMsg.locNum_B, (String) map.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(bcMsg.xxExstFlg_B, (String) map.get("PRIM_FLG"));
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctTpNm_B, (String) map.get("DS_ACCT_TP_NM"));

        String dsAcctActvCustFlg = (String) map.get("DS_ACCT_ACTV_CUST_FLG");
        String locRgtnStsCd = (String) map.get("LOC_RGTN_STS_CD");
        String ptyRgtnStsCd = (String) map.get("PTY_RGTN_STS_CD");
        String stsNm = convertTreeNodeStatus(dsAcctActvCustFlg, locRgtnStsCd, ptyRgtnStsCd);
        ZYPEZDItemValueSetter.setValue(bcMsg.dsAcctStsNm_B, stsNm);
    }

    private List<String> convertTreePathToList(String treePath, String dsAcctNum) {
        List<String> list = new ArrayList<String>();
        boolean isPrntReln = true;
        if (treePath.endsWith(CHAR_SEMICOLON)) {
            treePath = treePath.replaceAll(CHAR_SEMICOLON, "");
            isPrntReln = false;
        }

        String[] arr = treePath.split(CHAR_SLASH);
        for (String node : arr) {
            if (ZYPCommonFunc.hasValue(node)) {
                list.add(node);
            }
        }

        if (!isPrntReln) {
            if (list.size() > 0) {
                list.set(list.size() - 1, dsAcctNum);
            }
        }

        for (int i = 0; i < CHILDREN_MAX_LEVEL; i++) {
            if (i >= list.size()) {
                list.add("");
            }
        }
        return list;
    }

    private String convertTreeNodeStatus(String dsAcctActvCustFlg, String locRgtnStsCd, String ptyRgtnStsCd) {
        if (!dsAcctActvCustFlg.equals(ZYPConstant.FLG_ON_Y)) {
            return RGTN_STS_CD_INACTIVE;
        }
        if (ZYPCommonFunc.hasValue(locRgtnStsCd) && !locRgtnStsCd.equals(RGTN_STS.READY_FOR_ORDER_TAKING)) {
            return RGTN_STS_CD_INACTIVE;
        }
        if (ZYPCommonFunc.hasValue(ptyRgtnStsCd) && !ptyRgtnStsCd.equals(RGTN_STS.READY_FOR_ORDER_TAKING)) {
            return RGTN_STS_CD_INACTIVE;
        }
        return RGTN_STS_CD_ACTIVE;
    }

    private void setDsAcctInfo(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, Map map) {
        ZYPEZDItemValueSetter.setValue(cMsg.sellToCustPk_H1, (BigDecimal) map.get("DS_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_H1, (String) map.get("DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.dsAcctNum_BK, cMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) map.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_BK, cMsg.dsAcctNm_H1);
        // START 2018/01/26 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.rgtnStsCd_H1, (String) map.get("RGTN_STS_CD"));
        // END   2018/01/26 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_H3, (String) map.get("DS_ACCT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctTpCd_HD, (String) map.get("DS_ACCT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctItrlFlg_H3, (String) map.get("DS_ACCT_ITRL_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctClsCd_H3, (String) map.get("DS_ACCT_CLS_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_H1, (String) map.get("COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm_H1, (String) map.get("COA_AFFL_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaChCd_H1, (String) map.get("COA_CH_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaChNm_H1, (String) map.get("COA_CH_NM"));
        ZYPEZDItemValueSetter.setValue(sMsg.coaAfflCd_H1, (String) map.get("COA_AFFL_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.coaChCd_H1, (String) map.get("COA_CH_CD"));

        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctDlrCd_H3, (String) map.get("DS_ACCT_DLR_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_H1, (String) map.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.effThruDt_H1, (String) map.get("EFF_THRU_DT"));

        ZYPEZDItemValueSetter.setValue(sMsg.xxChkBox_H1, (String) map.get("DS_ACCT_ACTV_CUST_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, sMsg.xxChkBox_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctInacRsnCd_H3, (String) map.get("DS_ACCT_INAC_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H2, (String) map.get("ACCT_TRX_EXST_FLG"));
        // 2023/11/06 QC#61924 Add Start
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H3, (String) map.get("DEAC_NEW_TRX_FLG"));
        // 2023/11/06 QC#61924 Add End
        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg_H1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg_H2, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctLegalNm_H1, (String) map.get("DS_ACCT_LEGAL_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dbaNm_H1, (String) map.get("DBA_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctDunsNm_H1, (String) map.get("DS_ACCT_DUNS_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctAltNm_H1, (String) map.get("DS_ACCT_ALT_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsXtrnlRefTxt_H1, (String) map.get("DS_XTRNL_REF_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsDataSrcTxt_H1, (String) map.get("DS_DATA_SRC_TXT"));

        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_H1, (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_H1, (String) map.get("EZUPTIMEZONE"));

        // Marketing Tab
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctDunsNum_M1, (String) map.get("DS_ACCT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsUltDunsNum_M1, (String) map.get("DS_ULT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsLocEmpNum_M1, (BigDecimal) map.get("DS_LOC_EMP_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsLocRevAmt_M1, (BigDecimal) map.get("DS_LOC_REV_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsCustSicCd_M1, (String) map.get("DS_CUST_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsCustSicDescTxt_M1, (String) map.get("DS_CUST_SIC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsLastUpdDunsDt_M1, (String) map.get("DS_LAST_UPD_DUNS_DT"));

        // Credit/Collection Tab
        ZYPEZDItemValueSetter.setValue(cMsg.arStmtFlg_U1, (String) map.get("AR_STMT_FLG"));
        // START 2018/04/20 [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.sendCrBalStmtFlg_U1, (String) map.get("SEND_CR_BAL_STMT_FLG"));
        // END   2018/04/20 [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.arStmtIssCycleCd_U3, (String) map.get("AR_STMT_ISS_CYCLE_CD"));
        // START 2018/01/16 [QC#21734, DEL]
        // ZYPEZDItemValueSetter.setValue(cMsg.dunFlg_U1, (String) map.get("DUN_FLG"));
        // END   2018/01/16 [QC#21734, DEL]
        ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpCd_U1, (String) map.get("CLT_CUST_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpNm_U1, (String) map.get("CLT_CUST_TP_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoPk_U1, (BigDecimal) map.get("CLT_PTFO_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoCd_U1, (String) map.get("CLT_PTFO_CD"));
        // 2018/07/13 QC#26613 Mod Start
//        ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoNm_U1, (String) map.get("CLT_PTFO_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoDescTxt_U1, (String) map.get("CLT_PTFO_DESC_TXT"));
        // 2018/07/13 QC#26613 Mod End
        
        ZYPEZDItemValueSetter.setValue(cMsg.dsCltAcctStsCd_U3, (String) map.get("DS_CLT_ACCT_STS_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.lateFeeAmt_U1, (BigDecimal) map.get("LATE_FEE_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.lateFeeFlg_U1, (String) map.get("LATE_FEE_FLG"));
        // Del Start 2018/07/30 QC#27222
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCd_U1, (String) map.get("DS_CUST_TAX_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCalcCd_U3, (String) map.get("DS_CUST_TAX_CALC_CD"));
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxExemFlg_U1, (String) map.get("DS_TAX_EXEM_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsExemExprDt_U1, (String) map.get("DS_EXEM_EXPR_DT"));
        // Mod Start 2018/08/21 QC#27222-2
        // Del End 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxGrpExemCd_U3, (String) map.get("DS_TAX_GRP_EXEM_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxPrntTpCd_U3, (String) map.get("DS_TAX_PRNT_TP_CD"));

        // Invoice/Billing Tab
        ZYPEZDItemValueSetter.setValue(cMsg.defBaseTpCd_V3, (String) map.get("DEF_BASE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.defBaseCycleCd_V3, (String) map.get("DEF_BASE_CYCLE_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.defUsgTpCd_V3, (String) map.get("DEF_USG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.defUsgCycleCd_V3, (String) map.get("DEF_USG_CYCLE_CD"));
        // START 2022/03/22 [QC#59683, MOD]
//        ZYPEZDItemValueSetter.setValue(cMsg.dsBillTgtrFlg_V1, (String) map.get("DS_BILL_TGTR_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsInvTgtrTpCd_V1, (String) map.get("DS_INV_TGTR_TP_CD"));
        // END   2022/03/22 [QC#59683, MOD]
        
        ZYPEZDItemValueSetter.setValue(cMsg.cmpyPk_H1, (BigDecimal) map.get("CMPY_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.ptyLocPk_H1, (BigDecimal) map.get("PTY_LOC_PK"));

        // Transaction Tab
        ZYPEZDItemValueSetter.setValue(cMsg.coaCcCd_F1, (String) map.get("COA_CC_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaCcNm_F1, (String) map.get("COA_CC_NM"));

        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs, (String) map.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs, (String) map.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm, ZYPRecHistUtil.getFullNameForRecHist((String) map.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm, (String) map.get("XX_REC_HIST_TBL_NM"));

    }

    private void setPrinCust(NMAL6700CMsg cMsg, Map map) {
        ZYPEZDItemValueSetter.setValue(cMsg.prinCustPk_H2, (BigDecimal) map.get("PRIN_CUST_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_H2, (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_H2, (String) map.get("EZUPTIMEZONE"));
    }

    // Mod Start 2019/05/23 QC#50101
    //private void setLocationInfo(Map lineMap, NMAL6700_ASMsg asMsg, Map<String, String> cache) {
    private void setLocationInfo(Map lineMap, NMAL6700_ASMsg asMsg, Map<String, String> cache, //
            ArrayList<String> allLocList, ArrayList<String> allNotInactivePrimLocList) {
        // Mod End 2019/05/23 QC#50101
        // Add Start 2019/05/23 QC#50101
        String locNum = (String) lineMap.get("LOC_NUM");
        // Add End 2019/05/23 QC#50101

        asMsg.clear();
        ZYPEZDItemValueSetter.setValue(asMsg.locNum_A1, (String) lineMap.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(asMsg.firstLineAddr_A1, (String) lineMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(asMsg.scdLineAddr_A1, (String) lineMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(asMsg.ctyAddr_A1, (String) lineMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(asMsg.stCd_A1, (String) lineMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(asMsg.postCd_A1, (String) lineMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AB, (String) lineMap.get("BILL_TO_FLG"));
        ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AS, (String) lineMap.get("SHIP_TO_FLG"));
        // Del Start 2019/05/23 QC#50101
        //ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AP, (String) lineMap.get("PRIMARY_FLG"));
        //ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AH, (String) lineMap.get("PRIMARY_FLG"));
        // Del End 2019/05/23 QC#50101
        ZYPEZDItemValueSetter.setValue(asMsg.xxRelnDsAcctTxt_A1, (String) lineMap.get("XX_RELN_DS_ACCT_TXT"));
        ZYPEZDItemValueSetter.setValue(asMsg.effFromDt_A1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(asMsg.effThruDt_A1, (String) lineMap.get("EFF_THRU_DT"));
        String rgtnStsCd = (String) lineMap.get("RGTN_STS_CD");
        String dplyFlg = (String) lineMap.get("DPLY_FLG");
        String status = "";
        if (RGTN_STS.PENDING_COMPLETION.equals(rgtnStsCd) && ZYPConstant.FLG_ON_Y.equals(dplyFlg)) {
            status = RGTN_STS_INPROCESS;
        } else if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd)  && ZYPConstant.FLG_ON_Y.equals(dplyFlg)) {
            status = RGTN_STS_ACTIVE;
        } else {
            status = RGTN_STS_INACTIVE;
        }
        ZYPEZDItemValueSetter.setValue(asMsg.dsAcctStsNm_A1, status);

        // Add Start 2019/05/23 QC#50101
        String primaryFlg = (String) lineMap.get("PRIMARY_FLG");
        if (allLocList.contains(locNum)) {

            if (RGTN_STS_INACTIVE.equals(status) || ZYPConstant.FLG_OFF_N.equals(primaryFlg)) {
                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AP, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AH, ZYPConstant.FLG_OFF_N);
            } else {
                if (allNotInactivePrimLocList.contains(locNum)) {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AP, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AH, ZYPConstant.FLG_OFF_N);
                } else {
                    allNotInactivePrimLocList.add(locNum);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AP, primaryFlg);
                    ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AH, primaryFlg);
                }
            }
        } else {
            allLocList.add(locNum);
            if (RGTN_STS_INACTIVE.equals(status)) {
                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AP, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AH, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AP, primaryFlg);
                ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_AH, primaryFlg);

                if (ZYPConstant.FLG_ON_Y.equals(primaryFlg)) {
                    allNotInactivePrimLocList.add(locNum);
                }
            }
        }
        // Add End 2019/05/23 QC#50101

        ZYPEZDItemValueSetter.setValue(asMsg.ctryCd_A1, (String) lineMap.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(asMsg.thirdLineAddr_A1, (String) lineMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(asMsg.frthLineAddr_A1, (String) lineMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(asMsg.cntyNm_A1, (String) lineMap.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(asMsg.provNm_A1, (String) lineMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(asMsg.billToEffFromDtTxt_A1, (String) lineMap.get("BILL_TO_EFF_FROM_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(asMsg.billToEffThruDtTxt_A1, (String) lineMap.get("BILL_TO_EFF_THRU_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(asMsg.shipToEffFromDtTxt_A1, (String) lineMap.get("SHIP_TO_EFF_FROM_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(asMsg.shipToEffThruDtTxt_A1, (String) lineMap.get("SHIP_TO_EFF_THRU_DT_TXT"));
        ZYPEZDItemValueSetter.setValue(asMsg.dsLocNm_A1, (String) lineMap.get("DS_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(asMsg.lineBizTpCd_A1, (String) lineMap.get("LINE_BIZ_TP_CD"));

        ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratTs_A1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        // Mod Start 2018/04/27 QC#25154
        ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistCratByNm_A1, getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM"), cache));
        ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdTs_A1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistUpdByNm_A1, getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM"), cache));
        // Mod End 2018/04/27 QC#25154
        ZYPEZDItemValueSetter.setValue(asMsg.xxRecHistTblNm_A1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }

    // Add Start 2018/04/27 QC#25154
    private String getFullNameForRecHist(String userId, Map<String, String> cache){
        String ret = "";

        if (cache.containsKey(userId)){
            ret = cache.get(userId);
        } else {
            ret = ZYPRecHistUtil.getFullNameForRecHist(userId);
            cache.put(userId, ret);
        }
        
        return ret;
    }
    // Add End 2018/04/27 QC#25154
    
    private void setRelnshipInfo(String glblCmpyCd, Map lineMap, NMAL6700_CSMsg csMsg, Map<String, String> cache) {
        csMsg.clear();
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctRelnPk_C1, (BigDecimal) lineMap.get("DS_ACCT_RELN_PK"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctRelnTpCd_C3, (String) lineMap.get("DS_ACCT_RELN_TP_CD"));
        ZYPEZDItemValueSetter.setValue(csMsg.sellToCustPk_C1, (BigDecimal) lineMap.get("DS_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNm_C1, (String) lineMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(csMsg.dsAcctNum_C1, (String) lineMap.get("RELN_DS_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxChkBox_CB, (String) lineMap.get("DS_ACCT_RELN_BILL_TO_FLG"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxChkBox_CS, (String) lineMap.get("DS_ACCT_RELN_SHIP_TO_FLG"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxChkBox_CR, (String) lineMap.get("DS_ACCT_RELN_RECIP_FLG"));
        ZYPEZDItemValueSetter.setValue(csMsg.effFromDt_C1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(csMsg.effThruDt_C1, (String) lineMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(csMsg.ezUpTime_C1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(csMsg.ezUpTimeZone_C1, (String) lineMap.get("EZUPTIMEZONE"));

        ZYPEZDItemValueSetter.setValue(csMsg.xxRecHistCratTs_C1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        // Mod Start 2018/04/27 QC#25154
        ZYPEZDItemValueSetter.setValue(csMsg.xxRecHistCratByNm_C1, getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM"), cache));
        ZYPEZDItemValueSetter.setValue(csMsg.xxRecHistUpdTs_C1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(csMsg.xxRecHistUpdByNm_C1, getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM"), cache));
        // Mod End 2018/04/27 QC#25154
        ZYPEZDItemValueSetter.setValue(csMsg.xxRecHistTblNm_C1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }

    @SuppressWarnings("unchecked")
    private void setDsAcctCrPrflInfo(String glblCmpyCd, Map lineMap, NMAL6700CMsg cMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_U1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_U1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(cMsg.ccyCd_U3, (String) lineMap.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.custCrRtgCd_U3, (String) lineMap.get("CUST_CR_RTG_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.crLimitAmt_U1, (BigDecimal) lineMap.get("CR_LIMIT_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.crChkReqTpCd_U3, (String) lineMap.get("CR_CHK_REQ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.crRiskClsCd_U3, (String) lineMap.get("CR_RISK_CLS_CD"));
        // Add Start 2018/01/25 QC#22095
        ZYPEZDItemValueSetter.setValue(cMsg.contrCrRiskClsCd_U3, (String) lineMap.get("CONTR_CR_RISK_CLS_CD"));
        // Add End   2018/01/25 QC#22095
        ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscCd_U3, (String) lineMap.get("PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.ovrdPmtTermFlg_U1, (String) lineMap.get("OVRD_PMT_TERM_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.cashOrCcReqFlg_U1, (String) lineMap.get("CASH_OR_CC_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.custHardHldFlg_U1, (String) lineMap.get("CUST_HARD_HLD_FLG"));

        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs_CR, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm_CR, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs_CR, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm_CR, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm_CR, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
        // START 2018/04/16 E.Kameishi [QC#21037, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.autoCashHrchCd_U3, (String) lineMap.get("AUTO_CASH_HRCH_CD"));
        // END 2018/04/16 E.Kameishi [QC#21037, ADD]
    }

    @SuppressWarnings("unchecked")
    private void setDsCustTrxRuleInfo(String glblCmpyCd, Map lineMap, NMAL6700_FSMsg fsMsg) {
        fsMsg.clear();
        ZYPEZDItemValueSetter.setValue(fsMsg.ezUpTime_F1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(fsMsg.ezUpTimeZone_F1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsCustTrxRulePk_F1, (BigDecimal) lineMap.get("DS_CUST_TRX_RULE_PK"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsTrxRuleTpCd_F3, (String) lineMap.get("DS_TRX_RULE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsPoReqFlg_F1, (String) lineMap.get("DS_PO_REQ_FLG"));
        // 2022/11/25 QC#60398 Add Start
        ZYPEZDItemValueSetter.setValue(fsMsg.hardCopyReqFlg_F1, (String) lineMap.get("HARD_COPY_REQ_FLG"));
        // 2022/11/25 QC#60398 Add End
        ZYPEZDItemValueSetter.setValue(fsMsg.dsBlktPoNum_F1, (String) lineMap.get("DS_BLKT_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsPoExprDt_F1, (String) lineMap.get("DS_PO_EXPR_DT"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsDefBillToCd_F1, (String) lineMap.get("DS_DEF_BILL_TO_CD"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsDefShipToCd_F1, (String) lineMap.get("DS_DEF_SHIP_TO_CD"));
        ZYPEZDItemValueSetter.setValue(fsMsg.custEffLvlCd_F3, (String) lineMap.get("CUST_EFF_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsCrCardReqFlg_F1, (String) lineMap.get("DS_CR_CARD_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(fsMsg.dsOvngtAllwFlg_F1, (String) lineMap.get("DS_OVNGT_ALLW_FLG"));

        ZYPEZDItemValueSetter.setValue(fsMsg.xxRecHistCratTs_F1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(fsMsg.xxRecHistCratByNm_F1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(fsMsg.xxRecHistUpdTs_F1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(fsMsg.xxRecHistUpdByNm_F1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(fsMsg.xxRecHistTblNm_F1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));

    }

    @SuppressWarnings("unchecked")
    private void setDsCustSpclHdlgInfo(String glblCmpyCd, Map lineMap, NMAL6700_SSMsg ssMsg) {
        ssMsg.clear();
        ZYPEZDItemValueSetter.setValue(ssMsg.ezUpTime_S1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(ssMsg.ezUpTimeZone_S1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(ssMsg.dsCustSpclHdlgPk_S1, (BigDecimal) lineMap.get("DS_CUST_SPCL_HDLG_PK"));
        ZYPEZDItemValueSetter.setValue(ssMsg.dsSpclHdlgTpCd_S3, (String) lineMap.get("DS_SPCL_HDLG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(ssMsg.dsSpclHdlgTpValCd_S3, (String) lineMap.get("DS_SPCL_HDLG_TP_VAL_CD"));
        ZYPEZDItemValueSetter.setValue(ssMsg.effFromDt_S1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(ssMsg.effThruDt_S1, (String) lineMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(ssMsg.ezUpTime_S1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(ssMsg.ezUpTimeZone_S1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(ssMsg.custEffLvlCd_S3, (String) lineMap.get("CUST_EFF_LVL_CD"));

        ZYPEZDItemValueSetter.setValue(ssMsg.xxRecHistCratTs_S1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(ssMsg.xxRecHistCratByNm_S1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(ssMsg.xxRecHistUpdTs_S1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(ssMsg.xxRecHistUpdByNm_S1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(ssMsg.xxRecHistTblNm_S1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }
    @SuppressWarnings("unchecked")
    private void setDsCustInvRuleInfo(Map lineMap, NMAL6700_GSMsg gsMsg) {
        gsMsg.clear();
        ZYPEZDItemValueSetter.setValue(gsMsg.ezUpTime_G1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(gsMsg.ezUpTimeZone_G1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(gsMsg.dsCustInvRulePk_G1, (BigDecimal) lineMap.get("DS_CUST_INV_RULE_PK"));
        ZYPEZDItemValueSetter.setValue(gsMsg.custEffLvlCd_G3, (String) lineMap.get("CUST_EFF_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(gsMsg.custInvSrcCd_G3, (String) lineMap.get("CUST_INV_SRC_CD"));
        ZYPEZDItemValueSetter.setValue(gsMsg.custBllgTpCd_G3, (String) lineMap.get("CUST_BLLG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(gsMsg.custConslTermCd_G3, (String) lineMap.get("CUST_CONSL_TERM_CD"));
        ZYPEZDItemValueSetter.setValue(gsMsg.custBllgVcleCd_G3, (String) lineMap.get("CUST_BLLG_VCLE_CD"));
        ZYPEZDItemValueSetter.setValue(gsMsg.custEmlMsgTxt_G1, (String) lineMap.get("CUST_EML_MSG_TXT"));
        ZYPEZDItemValueSetter.setValue(gsMsg.defInvGrpCd_G3, (String) lineMap.get("DEF_INV_GRP_CD"));
        ZYPEZDItemValueSetter.setValue(gsMsg.invGrpNum_G1, (String) lineMap.get("INV_GRP_NUM"));

        ZYPEZDItemValueSetter.setValue(gsMsg.xxRecHistCratTs_G1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(gsMsg.xxRecHistCratByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(gsMsg.xxRecHistUpdTs_G1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(gsMsg.xxRecHistUpdByNm_G1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(gsMsg.xxRecHistTblNm_G1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));

    }

    private void setDsAcctRefAttrbInfo(Map lineMap, int i, NMAL6700_KSMsg ksMsg) {
        ksMsg.clear();
        ZYPEZDItemValueSetter.setValue(ksMsg.ezUpTime_K1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(ksMsg.ezUpTimeZone_K1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(ksMsg.dsAcctRefAttrbPk_K1, (BigDecimal) lineMap.get("DS_ACCT_REF_ATTRB_PK"));
        ZYPEZDItemValueSetter.setValue(ksMsg.bllgAttrbNm_K1, (String) lineMap.get("BLLG_ATTRB_NM"));
        ZYPEZDItemValueSetter.setValue(ksMsg.bllgAttrbValTxt_K1, (String) lineMap.get("BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(ksMsg.bllgAttrbEnblFlg_K1, (String) lineMap.get("BLLG_ATTRB_ENBL_FLG"));
        ZYPEZDItemValueSetter.setValue(ksMsg.bllgAttrbReqFlg_K1, (String) lineMap.get("BLLG_ATTRB_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(ksMsg.custEffLvlCd_K3, (String) lineMap.get("CUST_EFF_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(ksMsg.xxCtlNm_K1, CONTROL_STR + (String) lineMap.get("DS_ACCT_REF_ATTRB_NUM"));
        ZYPEZDItemValueSetter.setValue(ksMsg.dsAcctRefAttrbNum_K1, (String) lineMap.get("DS_ACCT_REF_ATTRB_NUM"));

        ZYPEZDItemValueSetter.setValue(ksMsg.xxRecHistCratTs_K1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(ksMsg.xxRecHistCratByNm_K1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(ksMsg.xxRecHistUpdTs_K1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(ksMsg.xxRecHistUpdByNm_K1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(ksMsg.xxRecHistTblNm_K1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }

    private void setDsCustBankAcctInfo(Map lineMap, NMAL6700_ISMsg isMsg) {
        isMsg.clear();
        ZYPEZDItemValueSetter.setValue(isMsg.dsBankAcctPk_I1, (BigDecimal) lineMap.get("DS_BANK_ACCT_PK"));
        ZYPEZDItemValueSetter.setValue(isMsg.ezUpTime_I1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(isMsg.ezUpTimeZone_I1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(isMsg.dsCustBankAcctRelnPk_I1, (BigDecimal) lineMap.get("DS_CUST_BANK_ACCT_RELN_PK"));
        ZYPEZDItemValueSetter.setValue(isMsg.dsBankAcctNm_I1, (String) lineMap.get("DS_BANK_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(isMsg.dsBankAcctNum_I1, (String) lineMap.get("MASK_BANK_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(isMsg.bankRteNum_I1, (String) lineMap.get("MASK_BANK_RTE_NUM"));
        ZYPEZDItemValueSetter.setValue(isMsg.ccyNm_I1, (String) lineMap.get("CCY_NM"));
        ZYPEZDItemValueSetter.setValue(isMsg.effFromDt_I1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(isMsg.effThruDt_I1, (String) lineMap.get("EFF_THRU_DT"));

        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistCratTs_I1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistCratByNm_I1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistUpdTs_I1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistUpdByNm_I1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistTblNm_I1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }
    // 2018/12/13 QC#29315 Del Start
//    private void setDsAcctCarr(Map lineMap, NMAL6700_WSMsg isMsg, List<Map<String, Object>> resPullDownList) {
//        isMsg.clear();
//        ZYPEZDItemValueSetter.setValue(isMsg.vndCd_W3, (String) lineMap.get("VND_CD"));
//        ZYPEZDItemValueSetter.setValue(isMsg.dsCarrAcctNum_W1, (String) lineMap.get("DS_CARR_ACCT_NUM"));
//        ZYPEZDItemValueSetter.setValue(isMsg.effFromDt_W1, (String) lineMap.get("EFF_FROM_DT"));
//        ZYPEZDItemValueSetter.setValue(isMsg.effThruDt_W1, (String) lineMap.get("EFF_THRU_DT"));
//        ZYPEZDItemValueSetter.setValue(isMsg.xxChkBox_WD, (String) lineMap.get("DEF_ACCT_CARR_FLG"));
//        ZYPEZDItemValueSetter.setValue(isMsg.dsAcctCarrPk_W1, (BigDecimal) lineMap.get("DS_ACCT_CARR_PK"));
//        ZYPEZDItemValueSetter.setValue(isMsg.ezUpTime_W1, (String) lineMap.get("EZUPTIME"));
//        ZYPEZDItemValueSetter.setValue(isMsg.ezUpTimeZone_W1, (String) lineMap.get("EZUPTIMEZONE"));
//
//        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistCratTs_W1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
//        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistCratByNm_W1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
//        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistUpdTs_W1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
//        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistUpdByNm_W1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
//        ZYPEZDItemValueSetter.setValue(isMsg.xxRecHistTblNm_W1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
//
//        // Create Carrire pull down
//        int cnt = resPullDownList.size();
//        if (cnt > isMsg.vndCd_W1.length()) {
//            cnt = isMsg.vndCd_W1.length();
//        }
//        for (int i = 0; i < cnt; i++) {
//            Map<String, Object> carr = resPullDownList.get(i);
//            ZYPEZDItemValueSetter.setValue(isMsg.vndCd_W1.no(i), (String) carr.get("VND_CD"));
//            ZYPEZDItemValueSetter.setValue(isMsg.locNm_W2.no(i), (String) carr.get("LOC_NM"));
//        }
//    }
    // 2018/12/13 QC#29315 Del End

    private void setDsCustSpclMsgInfo(NMAL6700CMsg cMsg, Map lineMap, NMAL6700_JSMsg jsMsg) {
        jsMsg.clear();
        ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTime_J1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(jsMsg.ezUpTimeZone_J1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(jsMsg.dsCustSpclMsgPk_J1, (BigDecimal) lineMap.get("DS_CUST_SPCL_MSG_PK"));
        ZYPEZDItemValueSetter.setValue(jsMsg.dsAcctNum_J1, cMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(jsMsg.dsAcctNm_J1, cMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(jsMsg.lineBizTpCd_J3, (String) lineMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(jsMsg.dsBizAreaCd_J3, (String) lineMap.get("DS_BIZ_AREA_CD"));
        ZYPEZDItemValueSetter.setValue(jsMsg.dsCustMsgTpCd_J3, (String) lineMap.get("DS_CUST_MSG_TP_CD"));
        ZYPEZDItemValueSetter.setValue(jsMsg.dsCustMsgTxt_J1, (String) lineMap.get("DS_CUST_MSG_TXT"));
        ZYPEZDItemValueSetter.setValue(jsMsg.dsPrintOnInvFlg_J1, (String) lineMap.get("DS_PRINT_ON_INV_FLG"));
        ZYPEZDItemValueSetter.setValue(jsMsg.custEffLvlCd_J3, (String) lineMap.get("CUST_EFF_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(jsMsg.effFromDt_J1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(jsMsg.effThruDt_J1, (String) lineMap.get("EFF_THRU_DT"));

        ZYPEZDItemValueSetter.setValue(jsMsg.xxRecHistCratTs_J1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(jsMsg.xxRecHistCratByNm_J1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(jsMsg.xxRecHistUpdTs_J1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(jsMsg.xxRecHistUpdByNm_J1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(jsMsg.xxRecHistTblNm_J1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));

    }

    private void setDsAcctGrpAsgInfo(String glblCmpyCd, Map lineMap, NMAL6700_ESMsg esMsg) {
        esMsg.clear();
        ZYPEZDItemValueSetter.setValue(esMsg.ezUpTime_E1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(esMsg.ezUpTimeZone_E1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(esMsg.dsAcctGrpAsgPk_E1, (BigDecimal) lineMap.get("DS_ACCT_GRP_ASG_PK"));
        ZYPEZDItemValueSetter.setValue(esMsg.dsBizAreaCd_E3, (String) lineMap.get("DS_BIZ_AREA_CD"));
        ZYPEZDItemValueSetter.setValue(esMsg.dsAcctGrpCd_E3, (String) lineMap.get("DS_ACCT_GRP_CD"));
        ZYPEZDItemValueSetter.setValue(esMsg.dsAcctGrpDescTxt_E3, (String) lineMap.get("DS_ACCT_GRP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(esMsg.effFromDt_E1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(esMsg.effThruDt_E1, (String) lineMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(esMsg.xxRecHistCratTs_E1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(esMsg.xxRecHistCratByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(esMsg.xxRecHistUpdTs_E1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(esMsg.xxRecHistUpdByNm_E1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(esMsg.xxRecHistTblNm_E1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }

    private void setCtacPsnInfo(Map lineMap, NMAL6700_DSMsg dsMsg, Map<String, String> cache) {
        dsMsg.clear();
        ZYPEZDItemValueSetter.setValue(dsMsg.ezUpTime_DR, (String) lineMap.get("RELN_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ezUpTimeZone_DR, (String) lineMap.get("RELN_EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(dsMsg.dsCtacPsnRelnPk_D1, (BigDecimal) lineMap.get("DS_CTAC_PSN_RELN_PK"));
        ZYPEZDItemValueSetter.setValue(dsMsg.effFromDt_D1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(dsMsg.effThruDt_D1, (String) lineMap.get("EFF_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(dsMsg.dsPrimLocFlg_D1, (String) lineMap.get("DS_PRIM_LOC_FLG"));
        ZYPEZDItemValueSetter.setValue(dsMsg.dsPrimLocFlg_DB, (String) lineMap.get("DS_PRIM_LOC_FLG")); // 2017/08/08 S21_NA#8598 Add

        ZYPEZDItemValueSetter.setValue(dsMsg.ezUpTime_D1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ezUpTimeZone_D1, (String) lineMap.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ezUpTime_D2, (String) lineMap.get("DS_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ezUpTimeZone_D2, (String) lineMap.get("DS_EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacPsnPk_D1, (BigDecimal) lineMap.get("CTAC_PSN_PK"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacPsnNum_D1, (String) lineMap.get("CTAC_PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacPsnFirstNm_D1, (String) lineMap.get("CTAC_PSN_FIRST_NM"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacPsnLastNm_D1, (String) lineMap.get("CTAC_PSN_LAST_NM"));
        // Mod Start 2017/12/06 QC#21897
        //ZYPEZDItemValueSetter.setValue(dsMsg.ctacTpNm_D1, (String) lineMap.get("CTAC_TP_NM"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacTpDescTxt_D1, (String) lineMap.get("CTAC_TP_DESC_TXT"));
        // Mod End 2017/12/06 QC#21897
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacTpCd_D1, (String) lineMap.get("CTAC_TP_CD")); // 2017/08/08 S21_NA#8598 Add
        ZYPEZDItemValueSetter.setValue(dsMsg.dsCtacPntValTxt_D1, (String) lineMap.get("CTAC_PSN_EML_ADDR"));
        ZYPEZDItemValueSetter.setValue(dsMsg.dsCtacPntValTxt_D2, (String) lineMap.get("CTAC_PSN_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(dsMsg.ctacPsnExtnNum_D1, (String) lineMap.get("CTAC_PSN_EXTN_NUM"));
        if (ZYPConstant.FLG_ON_Y.equals((String) lineMap.get("CTAC_PSN_ACTV_FLG"))) {
            ZYPEZDItemValueSetter.setValue(dsMsg.dplStsNm_D1, ACTIVE_STS);
        } else {
            ZYPEZDItemValueSetter.setValue(dsMsg.dplStsNm_D1, INACTIVE_STS);
        }
        ZYPEZDItemValueSetter.setValue(dsMsg.dsCtacPsnDeptNm_D1, (String) lineMap.get("DS_CTAC_PSN_DEPT_NM"));
        ZYPEZDItemValueSetter.setValue(dsMsg.xxRecHistCratTs_D1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        // Mod Start 2018/04/27 QC#25154
        ZYPEZDItemValueSetter.setValue(dsMsg.xxRecHistCratByNm_D1, getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM"), cache));
        ZYPEZDItemValueSetter.setValue(dsMsg.xxRecHistUpdTs_D1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(dsMsg.xxRecHistUpdByNm_D1, getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM"), cache));
        // Mod End 2018/04/27 QC#25154
        ZYPEZDItemValueSetter.setValue(dsMsg.xxRecHistTblNm_D1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }

    private void setAcctInfo(NMAL6700CMsg cMsg, Map map) {
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctDunsNum_M1, (String) map.get("DS_ACCT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsUltDunsNum_M1, (String) map.get("DS_ULT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsLocEmpNum_M1, (BigDecimal) map.get("DS_LOC_EMP_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsLocRevAmt_M1, (BigDecimal) map.get("DS_LOC_REV_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsCustSicCd_M1, (String) map.get("DS_CUST_SIC_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsCustSicDescTxt_M1, (String) map.get("DS_CUST_SIC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsLastUpdDunsDt_M1, (String) map.get("DS_LAST_UPD_DUNS_DT"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctUrl_M1, (String) map.get("DS_ACCT_URL"));
        // BUSINESS HOURS
        ZYPEZDItemValueSetter.setValue(cMsg.bizHrsSunFromTm_M1, (String) map.get("BIZ_HRS_SUN_FROM_TM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bizHrsSunToTm_M1, (String) map.get("BIZ_HRS_SUN_TO_TM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bizHrsMonFriFromTm_M1, (String) map.get("BIZ_HRS_MON_FRI_FROM_TM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bizHrsMonFriToTm_M1, (String) map.get("BIZ_HRS_MON_FRI_TO_TM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bizHrsSatFromTm_M1, (String) map.get("BIZ_HRS_SAT_FROM_TM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bizHrsSatToTm_M1, (String) map.get("BIZ_HRS_SAT_TO_TM"));
        // Hidden
        ZYPEZDItemValueSetter.setValue(cMsg.cmpyPk_M1, (BigDecimal) map.get("CMPY_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_M1, (String) map.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_M1, (String) map.get("EZUPTIMEZONE"));
    }

    private void setCertificationReq(String glblCmpyCd, Map lineMap, NMAL6700_NSMsg nsMsg) {
        nsMsg.clear();
        ZYPEZDItemValueSetter.setValue(nsMsg.svcAccsPmitNum_N1, (String) lineMap.get("SVC_ACCS_PMIT_NUM"));
        ZYPEZDItemValueSetter.setValue(nsMsg.effFromDt_N1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(nsMsg.effToDt_N1, (String) lineMap.get("EFF_TO_DT"));
        ZYPEZDItemValueSetter.setValue(nsMsg.svcAccsPmitDescTxt_N1, (String) lineMap.get("SVC_ACCS_PMIT_DESC_TXT"));
        // Hidden
        ZYPEZDItemValueSetter.setValue(nsMsg.svcAccsPmitPk_N1, (BigDecimal) lineMap.get("SVC_ACCS_PMIT_PK"));
        ZYPEZDItemValueSetter.setValue(nsMsg.ezUpTime_N1, (String) lineMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(nsMsg.ezUpTimeZone_N1, (String) lineMap.get("EZUPTIMEZONE"));

        ZYPEZDItemValueSetter.setValue(nsMsg.xxRecHistCratTs_N1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(nsMsg.xxRecHistCratByNm_N1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(nsMsg.xxRecHistUpdTs_N1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(nsMsg.xxRecHistUpdByNm_N1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(nsMsg.xxRecHistTblNm_N1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));
    }

    private void doProcess_NMAL6700Scrn00_GetTemplate(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        S21SsmEZDResult res = NMAL6700Query.getInstance().getTemplate(cMsg, sMsg);
        if (!res.isCodeNormal()) {
            String[] args = {"Template" };
            cMsg.setMessageInfo(NMAM8111E, args);
            return;
        }

        Map map = (Map) res.getResultObject();

        ZYPEZDItemValueSetter.setValue(cMsg.dsCustArTmplNm_U1, (String) map.get("DS_CUST_AR_TMPL_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.ccyCd_U3, (String) map.get("CCY_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.custCrRtgCd_U3, (String) map.get("CUST_CR_RTG_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.crLimitAmt_U1, (BigDecimal) map.get("CR_LIMIT_AMT"));
        ZYPEZDItemValueSetter.setValue(cMsg.crChkReqTpCd_U3, (String) map.get("CR_CHK_REQ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.crRiskClsCd_U3, (String) map.get("CR_RISK_CLS_CD"));
        // START 2018/01/26 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.contrCrRiskClsCd_U3, (String) map.get("CONTR_CR_RISK_CLS_CD"));
        // END   2018/01/26 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCd_U3, (String) map.get("PMT_TERM_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.ovrdPmtTermFlg_U1, (String) map.get("OVRD_PMT_TERM_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.cashOrCcReqFlg_U1, (String) map.get("CASH_OR_CC_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.custHardHldFlg_U1, (String) map.get("CUST_HARD_HLD_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.arStmtFlg_U1, (String) map.get("AR_STMT_FLG"));
        // START 2018/04/20 [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.sendCrBalStmtFlg_U1, (String) map.get("SEND_CR_BAL_STMT_FLG"));
        // END   2018/04/20 [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.arStmtIssCycleCd_U3, (String) map.get("AR_STMT_ISS_CYCLE_CD"));
        // START 2018/01/16 [QC#21734, DEL]
        // ZYPEZDItemValueSetter.setValue(cMsg.dunFlg_U1, (String) map.get("DUN_FLG"));
        // END   2018/01/16 [QC#21734, DEL]
        ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpCd_U1, (String) map.get("CLT_CUST_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoCd_U1, (String) map.get("CLT_CUST_TP_CD"));
        // Del Start 2018/07/30 QC#27222
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCd_U1, (String) map.get("DS_TAX_CD"));
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCalcCd_U3, (String) map.get("DS_CUST_TAX_CALC_CD"));
        // Del Start 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxPrntTpCd_U3, (String) map.get("DS_TAX_PRNT_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.lateFeeFlg_U1, (String) map.get("LATE_FEE_FLG"));
        ZYPEZDItemValueSetter.setValue(cMsg.lateFeeAmt_U1, (BigDecimal) map.get("LATE_FEE_AMT"));
    }

    private void doProcess_NMAL6700Scrn00_ApplyTemplate(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        if ("W".equals(cMsg.getMessageKind()) || "E".equals(cMsg.getMessageKind())) {
            return;
        }

        S21SsmEZDResult res = NMAL6700Query.getInstance().getTemplate(cMsg, sMsg);
        if (res.isCodeNormal() && res.getQueryResultCount() == 1) {
            setTemplateInfo(cMsg, sMsg);
            doProcess_NMAL6700Scrn00_GetCltCustTpNm(cMsg, sMsg);
            doProcess_NMAL6700Scrn00_GetCltPtfoNm(cMsg, sMsg);
        } else {
            cMsg.dsCustArTmplNm_U1.setErrorInfo(1, NZZM0000E);
            cMsg.setMessageInfo(NZZM0000E);
        }
    }

    private void doProcess_NMAL6700_NMAL2570(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        searchPsnNm(cMsg, sMsg, cMsg.xxCellIdx.getValueInt());
    }

    private void doProcess_NMAL6700_NMAL6720(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxValUpdFlg.getValue())) {
            // Mod Start 2018/12/20 QC#29486
            //initSearch(cMsg, sMsg);
            initSearch(cMsg, sMsg, true);
            // Mod End 2018/12/20 QC#29486
        } else {
            // Mod Start 2018/12/20 QC#29486
            //doProcess_NMAL6700Scrn00_Search(cMsg, sMsg);
            doProcess_NMAL6700Scrn00_Search(cMsg, sMsg, true);
            // Mod End 2018/12/20 QC#29486
        }
    }

    private void searchPsnNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int rowIndex) {
        String psnCdList = cMsg.G.no(rowIndex).xxGenlFldAreaTxt_G1.getValue();
        String[] psnCdArray = NMAL6700CommonLogic.splitByComma(psnCdList);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < psnCdArray.length; i++) {
            String psnCd = psnCdArray[i];
            String psnNm = NMAL6700CommonLogic.getPsnNm(psnCd, getGlobalCompanyCode());
            if (sb.length() > 0) {
                sb.append(NMAL6700Constant.CHAR_COMMA);
            }
            sb.append(psnNm);
        }
        ZYPEZDItemValueSetter.setValue(cMsg.G.no(rowIndex).xxCustInvRuleRcpntTxt_G1, sb.toString());
    }

    private void doProcess_NMAL6700Scrn00_GetDsAcctNm(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        String dsAcctNum = NMAL6700CommonLogic.getDsAcctNm(cMsg.C.no(cMsg.xxCellIdx.getValueInt()).dsAcctNum_C1.getValue(), getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.C.no(cMsg.xxCellIdx.getValueInt()).dsAcctNm_C1, dsAcctNum);
    }

    /**
     * doProcess_NMAL6700Scrn00_OnChange_dsAcctItrlFlg
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700Scrn00_OnChange_dsAcctItrlFlg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

       if (cMsg.dsAcctItrlFlg_H3.getValue().equals(ZYPConstant.FLG_ON_Y)) {
          ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_H1, "");
            ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm_H1, "");
       } else {
          ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_H1, NMAL6700CommonLogic.getDefCoaAfflCd());
            ZYPEZDItemValueSetter.setValue(cMsg.coaAfflNm_H1, "");
            doProcess_NMAL6700Scrn00_GetInterCompanyNm(cMsg, sMsg);
       }

    }

    /**
     * NMAL6700Scrn00_OnChange_dsSpclHdlgTp
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700Scrn00_OnChange_dsSpclHdlgTp(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
       int rowNum = cMsg.xxRowNum.getValueInt();
       cMsg.S.no(rowNum).dsSpclHdlgTpValCd_S1.clear();
       cMsg.S.no(rowNum).dsSpclHdlgTpValNm_S2.clear();

       if (ZYPCommonFunc.hasValue(cMsg.S.no(rowNum).dsSpclHdlgTpCd_S3)) {
          DS_SPCL_HDLG_TPTMsgArray dsSpclHdlgTpArry = NMAL6700CommonLogic.getDsSpclHdlgTp(cMsg.S.no(rowNum).dsSpclHdlgTpCd_S3.getValue(), getGlobalCompanyCode());
          if (dsSpclHdlgTpArry != null) {
              for (int i = 0; i < dsSpclHdlgTpArry.getValidCount(); i++) {
                  ZYPEZDItemValueSetter.setValue(cMsg.S.no(rowNum).dsSpclHdlgTpValCd_S1.no(i), dsSpclHdlgTpArry.no(i).dsSpclHdlgTpValCd);
                  ZYPEZDItemValueSetter.setValue(cMsg.S.no(rowNum).dsSpclHdlgTpValNm_S2.no(i), dsSpclHdlgTpArry.no(i).dsSpclHdlgTpValNm);
              }
          }
       }
    }

    private void doProcess_NMAL6700Scrn00_DownloadLocationTemplate(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        NMAL6700F02FMsg fMsg = new NMAL6700F02FMsg();
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("UploadTemplate"), ".csv");
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(getLocationUpdateTemplateHeader());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NMAL6700_ASMsg asMsg = sMsg.A.no(i);

            String locActvFlg = ZYPConstant.FLG_OFF_N;
            if (RGTN_STS_ACTIVE.equals(asMsg.dsAcctStsNm_A1.getValue())) {
                locActvFlg = ZYPConstant.FLG_ON_Y;
            }

            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, cMsg.dsAcctNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, cMsg.dsAcctNm_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.ctryCd, asMsg.ctryCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr, asMsg.firstLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr, asMsg.scdLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.thirdLineAddr, asMsg.thirdLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.frthLineAddr, asMsg.frthLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr, asMsg.ctyAddr_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.stCd, asMsg.stCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.postCd, asMsg.postCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.cntyNm, asMsg.cntyNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.provNm, asMsg.provNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.billToAvalFlg, nvl(asMsg.xxChkBox_AB.getValue(), ZYPConstant.FLG_OFF_N));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToAvalFlg, nvl(asMsg.xxChkBox_AS.getValue(), ZYPConstant.FLG_OFF_N));
            ZYPEZDItemValueSetter.setValue(fMsg.billToEffFromDtTxt, asMsg.billToEffFromDtTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.billToEffThruDtTxt, asMsg.billToEffThruDtTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipToEffFromDtTxt, asMsg.shipToEffFromDtTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.shipToEffThruDtTxt, asMsg.shipToEffThruDtTxt_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.dsLocNm, asMsg.dsLocNm_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpCd, asMsg.lineBizTpCd_A1);
            ZYPEZDItemValueSetter.setValue(fMsg.locPrimFlg, nvl(asMsg.xxChkBox_AP.getValue(), ZYPConstant.FLG_OFF_N));
            ZYPEZDItemValueSetter.setValue(fMsg.locActvFlg, locActvFlg);
            ZYPEZDItemValueSetter.setValue(fMsg.locNum, asMsg.locNum_A1);
            // 2017/10/23 QC#21599 add start
            SHIP_TO_CUSTTMsg shipMsg = new SHIP_TO_CUSTTMsg();
            shipMsg.setSQLID("048");
            shipMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            shipMsg.setConditionValue("locNum01", asMsg.locNum_A1.getValue());

            SHIP_TO_CUSTTMsgArray shipMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipMsg);
            if (shipMsgArray != null && shipMsgArray.length() > 0) {
                ZYPEZDItemValueSetter.setValue(fMsg.relnBillToCustCd, shipMsgArray.no(0).relnBillToCustCd);
            }
            // 2017/10/23 QC#21599 add end
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private String nvl(String origValue, String altValue) {
        if (ZYPCommonFunc.hasValue(origValue)) {
            return origValue;
        }
        return altValue;
    }

    private String[] getLocationUpdateTemplateHeader() {
        List<String> list = new ArrayList<String>();
        list.add("<*Account#>");
        list.add("<Account Name>");
        list.add("<*Country>");
        list.add("<*Address1>");
        list.add("<Address2>");
        list.add("<Address3>");
        list.add("<Address4>");
        list.add("<*City>");
        list.add("<*State>");
        list.add("<*Postal Code>");
        list.add("<County>");
        list.add("<Providence>");
        list.add("<*Bill To(Y or N)>");
        list.add("<*Ship To(Y or N)>");
        list.add("<Bill To Start Date(MM/DD/YYYY)>");
        list.add("<Bill To End Date(MM/DD/YYYY)>");
        list.add("<Ship To Start Date(MM/DD/YYYY)>");
        list.add("<Ship To End Date(MM/DD/YYYY)>");
        list.add("<Location Name>");
        list.add("<*Source Business Unit>");
        list.add("<*Primary(Y or N)>");
        list.add("<*Active(Y or N)>");
        list.add("<Location #>");
        // 2017/10/23 QC#21599 add start
        list.add("<Related Bill To Code>");
        // 2017/10/23 QC#21599 add end
        return list.toArray(new String[]{});
    }

    private void changePage_TabRelnships(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int oldDisplayStartRowNum, int newDisplayStartRowNum) {
        // Save current page to sMsg
        savePageToSMsg_TabRelnship(cMsg, sMsg, oldDisplayStartRowNum - 1);

        // Display next page from sMsg
        int sMsgStartIndexOfNextPage = newDisplayStartRowNum - 1;
        getPageFromSMsg_TabRelnship(cMsg, sMsg, sMsgStartIndexOfNextPage);

        // Set new page number
        cMsg.xxPageShowFromNum_C1.setValue(sMsgStartIndexOfNextPage + 1);
        cMsg.xxPageShowToNum_C1.setValue(sMsgStartIndexOfNextPage + cMsg.C.getValidCount());
    }

    private void changePage_TabMsgNote(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int oldDisplayStartRowNum, int newDisplayStartRowNum) {
        // Save current page to sMsg
        savePageToSMsg_TabMsgNote(cMsg, sMsg, oldDisplayStartRowNum - 1);

        // Display next page from sMsg
        int sMsgStartIndexOfNextPage = newDisplayStartRowNum - 1;
        NMAL6700CommonLogic.getPageFromSMsg_TabMsgNote(cMsg, sMsg, sMsgStartIndexOfNextPage);

        // Set new page number
        cMsg.xxPageShowFromNum_J1.setValue(sMsgStartIndexOfNextPage + 1);
        cMsg.xxPageShowToNum_J1.setValue(sMsgStartIndexOfNextPage + cMsg.J.getValidCount());
    }

    private void changePage_TabMarketing(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int oldDisplayStartRowNum, int newDisplayStartRowNum) {
        // Save current page to sMsg
        savePageToSMsg_TabMarketing(cMsg, sMsg, oldDisplayStartRowNum - 1);

        // Display next page from sMsg
        int sMsgStartIndexOfNextPage = newDisplayStartRowNum - 1;
        NMAL6700CommonLogic.getPageFromSMsg_TabMarketing(cMsg, sMsg, sMsgStartIndexOfNextPage);

        // Set new page number
        cMsg.xxPageShowFromNum_M1.setValue(sMsgStartIndexOfNextPage + 1);
        cMsg.xxPageShowToNum_M1.setValue(sMsgStartIndexOfNextPage + cMsg.E.getValidCount());
    }

    private void clearMsg(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);
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
        ZYPTableUtil.clear(sMsg.I);
        ZYPTableUtil.clear(cMsg.I);
        ZYPTableUtil.clear(sMsg.J);
        ZYPTableUtil.clear(cMsg.J);
        ZYPTableUtil.clear(sMsg.K);
        ZYPTableUtil.clear(cMsg.K);
        ZYPTableUtil.clear(sMsg.N);
        ZYPTableUtil.clear(cMsg.N);
        ZYPTableUtil.clear(sMsg.S);
        ZYPTableUtil.clear(cMsg.S);
        // 2018/12/10 QC#29315 Del Start
//        ZYPTableUtil.clear(cMsg.W);
//        ZYPTableUtil.clear(sMsg.W);
        // 2018/12/10 QC#29315 Del End
        ZYPTableUtil.clear(cMsg.M);
        ZYPTableUtil.clear(sMsg.M);

        ZYPTableUtil.clear(sMsg.O);
        ZYPTableUtil.clear(sMsg.Q);
        ZYPTableUtil.clear(sMsg.R);
        ZYPTableUtil.clear(sMsg.V);
        ZYPTableUtil.clear(sMsg.H);
        ZYPTableUtil.clear(sMsg.Y);
        ZYPTableUtil.clear(sMsg.T);
        ZYPTableUtil.clear(sMsg.U);
        ZYPTableUtil.clear(sMsg.X);
        ZYPTableUtil.clear(sMsg.Z);

        sMsg.A.setValidCount(0);
        cMsg.A.setValidCount(0);
        cMsg.B.setValidCount(0);
        sMsg.C.setValidCount(0);
        cMsg.C.setValidCount(0);
        sMsg.D.setValidCount(0);
        cMsg.D.setValidCount(0);
        sMsg.E.setValidCount(0);
        cMsg.E.setValidCount(0);
        sMsg.F.setValidCount(0);
        cMsg.F.setValidCount(0);
        sMsg.G.setValidCount(0);
        cMsg.G.setValidCount(0);
        sMsg.I.setValidCount(0);
        cMsg.I.setValidCount(0);
        sMsg.J.setValidCount(0);
        cMsg.J.setValidCount(0);
        sMsg.K.setValidCount(0);
        cMsg.K.setValidCount(0);
        sMsg.N.setValidCount(0);
        cMsg.N.setValidCount(0);
        sMsg.S.setValidCount(0);
        cMsg.S.setValidCount(0);
        // 2018/12/10 QC#29315 Del Start
//        cMsg.W.setValidCount(0);
//        sMsg.W.setValidCount(0);
        // 2018/12/10 QC#29315 Del End
        cMsg.M.setValidCount(0);
        sMsg.M.setValidCount(0);

        sMsg.O.setValidCount(0);
        sMsg.Q.setValidCount(0);
        sMsg.R.setValidCount(0);
        sMsg.V.setValidCount(0);
        sMsg.H.setValidCount(0);
        sMsg.Y.setValidCount(0);
        sMsg.T.setValidCount(0);
        sMsg.U.setValidCount(0);
        sMsg.X.setValidCount(0);
        sMsg.Z.setValidCount(0);

        if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum_H1)) {
            cMsg.clear();
        }
        cMsg.xxChkBox_BI.clear();
        cMsg.xxChkBox_BH.clear();
        cMsg.xxChkBox_DX.clear();

        cMsg.ctacPsnPk_DH.clear();

        cMsg.locNum_AD.clear();

    }

    /**
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void setTemplateInfo(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.ccyCd_U3, sMsg.ccyCd_U3);
        ZYPEZDItemValueSetter.setValue(cMsg.custCrRtgCd_U3, sMsg.custCrRtgCd_U3);
        ZYPEZDItemValueSetter.setValue(cMsg.crLimitAmt_U1, sMsg.crLimitAmt_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.crChkReqTpCd_U3, sMsg.crChkReqTpCd_U3);
        ZYPEZDItemValueSetter.setValue(cMsg.crRiskClsCd_U3, sMsg.crRiskClsCd_U3);
        // START 2018/01/26 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.contrCrRiskClsCd_U3, sMsg.contrCrRiskClsCd_U3);
        // END   2018/01/26 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.ovrdPmtTermFlg_U1, sMsg.ovrdPmtTermFlg_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.cashOrCcReqFlg_U1, sMsg.cashOrCcReqFlg_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.custHardHldFlg_U1, sMsg.custHardHldFlg_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.arStmtFlg_U1, sMsg.arStmtFlg_U1);
        // START 2018/01/26 [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.sendCrBalStmtFlg_U1, sMsg.sendCrBalStmtFlg_U1);
        // END   2018/01/26 [QC#23882, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.arStmtIssCycleCd_U3, sMsg.arStmtIssCycleCd_U3);
        // START 2018/01/16 [QC#21734, DEL]
        // ZYPEZDItemValueSetter.setValue(cMsg.dunFlg_U1, sMsg.dunFlg_U1);
        // END   2018/01/16 [QC#21734, DEL]
        ZYPEZDItemValueSetter.setValue(cMsg.cltCustTpCd_U1, sMsg.cltCustTpCd_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsCltAcctStsCd_U3, sMsg.dsCltAcctStsCd_U3);
        ZYPEZDItemValueSetter.setValue(cMsg.lateFeeFlg_U1, sMsg.lateFeeFlg_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.lateFeeAmt_U1, sMsg.lateFeeAmt_U1);
        // Del Start 2018/07/30 QC#27222
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCd_U1, sMsg.dsCustTaxCd_U1);
//        ZYPEZDItemValueSetter.setValue(cMsg.dsCustTaxCalcCd_U3, sMsg.dsCustTaxCalcCd_U3);
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxExemFlg_U1, sMsg.dsTaxExemFlg_U1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsExemExprDt_U1, sMsg.dsExemExprDt_U1);
        // Mod End 2018/08/21 QC#27222-2
//        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxGrpExemCd_U3, sMsg.dsTaxGrpExemCd_U3);
        // Del End 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(cMsg.dsTaxPrntTpCd_U3, sMsg.dsTaxPrntTpCd_U3);
        ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscCd_U3, sMsg.pmtTermCashDiscCd_U3);
        ZYPEZDItemValueSetter.setValue(cMsg.cltPtfoCd_U1, sMsg.cltPtfoCd_U1);
        // Add Start 2019/02/13 QC#30293
        ZYPEZDItemValueSetter.setValue(cMsg.autoCashHrchCd_U3, sMsg.autoCashHrchCd_U3);
        // Add End 2019/02/13 QC#30293
    }

    private static void getColData(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        if (TAB_ADDRESSES.equals(cMsg.xxDplyTab.getValue())) {
            ZYPGUITableColumn.getColData((NMAL6700CMsg) cMsg, (NMAL6700SMsg) sMsg, "AHEAD");
        } else if (TAB_CONTACTS.equals(cMsg.xxDplyTab.getValue())) {
            ZYPGUITableColumn.getColData((NMAL6700CMsg) cMsg, (NMAL6700SMsg) sMsg, "DHEAD");
        }
    }

    private void doNothing() {
        return;
    }

    // START 2017/07/06 J.Kim [QC#16966,ADD]
    private void doProcess_NMAL6700_NMAL6170(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_RELNSHIPS);
        // Mod Start 2018/12/20 QC#29486
        //doProcess_NMAL6700Scrn00_Search(cMsg, sMsg);
        doProcess_NMAL6700Scrn00_Search(cMsg, sMsg, true);
        // Mod End 2018/12/20 QC#29486
    }
    // END 2017/07/06 J.Kim [QC#16966,ADD]

    // 2018/02/14 QC#20297(Sol#379) add start
    // Mod Start 2018/12/20 QC#29486
    //@SuppressWarnings("unchecked")
    //private void searchShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
    @SuppressWarnings("unchecked")
    private void searchShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, boolean isDispWarnInfo) {
        // Mod End 2018/12/20 QC#29486
        String glblCmpyCd = getGlobalCompanyCode();
        ZYPTableUtil.clear(cMsg.M);
        ZYPTableUtil.clear(sMsg.M);

        S21SsmEZDResult res = NMAL6700Query.getInstance().getDsCustShpgDefList(glblCmpyCd, cMsg.dsAcctNum_H1.getValue(), sMsg);
        if (res.isCodeNormal()) {
            List list = (List) res.getResultObject();
            int listSize = list.size();
            if (listSize > sMsg.M.length()) {
                listSize = sMsg.M.length();

                // Add Start 2018/12/20 QC#29486
                if (isDispWarnInfo) {
                    // Add End 2018/12/20 QC#29486
                cMsg.setMessageInfo(NMAM8181W, new String[] {String.valueOf(listSize), String.valueOf(listSize) });
                // Add Start 2018/12/12 QC#29486
                cMsg.xxDplyTab.setValue(TAB_SHIPPING);
                // Add End 2018/12/12 QC#29486
                // Add Start 2018/12/20 QC#29486
                }
                // Add End 2018/12/20 QC#29486
            }
            // 2018/12/10 QC#29315 Add Start
            // 2023/01/13 QC#60860 Del Start
//          // Create Carrire pull down   
//            S21SsmEZDResult resPullDown = NMAL6700Query.getInstance().getBankAcctPulldownList(glblCmpyCd);
//            List<Map<String, Object>> resPullDownList = null;
//            if (resPullDown.isCodeNormal()) {
//                resPullDownList = (List<Map<String, Object>>) resPullDown.getResultObject();
//            }
            // 2023/01/13 QC#60860 Del End
            // 2018/12/10 QC#29315 Add End
            for (int i = 0; i < listSize; i++) {
                Map map = (Map) list.get(i);
                NMAL6700_MSMsg msMsg = sMsg.M.no(i);
                // 2018/12/10 QC#29315 Mod Start
//                setShippingInfo(cMsg, map, jsMsg);
                // 2023/01/13 QC#60860 Mod Start
//                setShippingInfo(cMsg, map, msMsg, resPullDownList);
                setShippingInfo(cMsg, map, msMsg);
                // 2023/01/13 QC#60860 Mod End
             // 2018/12/10 QC#29315 Mod End
            }
            sMsg.M.setValidCount(listSize);
        }
        // Show first page to screen
        NMAL6700CommonLogic.getPageFromSMsg_TabShipping(cMsg, sMsg, 0);
    }
    // 2018/12/10 QC#29315 Mod Start
//    private void setShippingInfo(NMAL6700CMsg cMsg, Map lineMap, NMAL6700_MSMsg msMsg) {
// 2023/01/13 QC#60860 Mod Start
//    private void setShippingInfo(NMAL6700CMsg cMsg, Map lineMap, NMAL6700_MSMsg msMsg, List<Map<String, Object>> resPullDownList) {
    private void setShippingInfo(NMAL6700CMsg cMsg, Map lineMap, NMAL6700_MSMsg msMsg) {
// 2023/01/13 QC#60860 Mod End
     // 2018/12/10 QC#29315 Mod End
        msMsg.clear();
        // 2018/12/10 QC#29315 Del Start
//        ZYPEZDItemValueSetter.setValue(msMsg.ezUpTime_M2, (String) lineMap.get("EZUPTIME"));
//        ZYPEZDItemValueSetter.setValue(msMsg.ezUpTimeZone_M2, (String) lineMap.get("EZUPTIMEZONE"));
        // 2018/12/10 QC#29315 Del End
        ZYPEZDItemValueSetter.setValue(msMsg.dsCustShpgDefPk_M1, (BigDecimal) lineMap.get("DS_CUST_SHPG_DEF_PK"));
        ZYPEZDItemValueSetter.setValue(msMsg.lineBizTpCd_M3, (String) lineMap.get("LINE_BIZ_TP_CD"));
        ZYPEZDItemValueSetter.setValue(msMsg.dsBizAreaCd_M3, (String) lineMap.get("DS_BIZ_AREA_CD"));
        ZYPEZDItemValueSetter.setValue(msMsg.frtCondCd_M3, (String) lineMap.get("FRT_COND_CD"));
        ZYPEZDItemValueSetter.setValue(msMsg.shpgSvcLvlCd_M3, (String) lineMap.get("SHPG_SVC_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(msMsg.custEffLvlCd_M3, (String) lineMap.get("CUST_EFF_LVL_CD"));
        ZYPEZDItemValueSetter.setValue(msMsg.effThruDt_M1, (String) lineMap.get("EFF_THRU_DT"));
        // 2018/12/10 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(msMsg.effFromDt_M1, (String) lineMap.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(msMsg.dsCarrAcctNum_M1, (String) lineMap.get("DS_CARR_ACCT_NUM"));
        ZYPEZDItemValueSetter.setValue(msMsg.xxChkBox_MD, (String) lineMap.get("DEF_FLG"));
        ZYPEZDItemValueSetter.setValue(msMsg.dsAcctCarrPk_M1, (BigDecimal) lineMap.get("DS_ACCT_CARR_PK"));
        // 2023/01/13 QC#60860 Mod Start
//        ZYPEZDItemValueSetter.setValue(msMsg.vndCd_M3, (String) lineMap.get("VND_CD"));
        ZYPEZDItemValueSetter.setValue(msMsg.carrNm_M3, (String) lineMap.get("VND_NM"));
        // 2023/01/13 QC#60860 Mod End
        ZYPEZDItemValueSetter.setValue(msMsg.ezUpTime_MS, (String) lineMap.get("DCSD_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(msMsg.ezUpTime_MC, (String) lineMap.get("DAC_EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(msMsg.ezUpTimeZone_MS, (String) lineMap.get("DCSD_EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(msMsg.ezUpTimeZone_MC, (String) lineMap.get("DAC_EZUPTIMEZONE"));
        // 2018/12/10 QC#29315 Add End

        ZYPEZDItemValueSetter.setValue(msMsg.xxRecHistCratTs_M1, (String) lineMap.get("XX_REC_HIST_CRAT_TS"));
        ZYPEZDItemValueSetter.setValue(msMsg.xxRecHistCratByNm_M1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_CRAT_BY_NM")));
        ZYPEZDItemValueSetter.setValue(msMsg.xxRecHistUpdTs_M1, (String) lineMap.get("XX_REC_HIST_UPD_TS"));
        ZYPEZDItemValueSetter.setValue(msMsg.xxRecHistUpdByNm_M1, ZYPRecHistUtil.getFullNameForRecHist((String) lineMap.get("XX_REC_HIST_UPD_BY_NM")));
        ZYPEZDItemValueSetter.setValue(msMsg.xxRecHistTblNm_M1, (String) lineMap.get("XX_REC_HIST_TBL_NM"));

        // 2018/12/10 QC#29315 Add Start
        // 2023/01/13 QC#60860 Del Start
        // Create Carrire pull down
//        int cnt = resPullDownList.size();
//        if (cnt > msMsg.vndCd_M1.length()) {
//            cnt = msMsg.vndCd_M1.length();
//        }
//        for (int i = 0; i < cnt; i++) {
//            Map<String, Object> carr = resPullDownList.get(i);
//            ZYPEZDItemValueSetter.setValue(msMsg.vndCd_M1.no(i), (String) carr.get("VND_CD"));
//            ZYPEZDItemValueSetter.setValue(msMsg.locNm_M2.no(i), (String) carr.get("LOC_NM"));
//        }
        // 2023/01/13 QC#60860 Del End
        // 2018/12/10 QC#29315 Add End
    }
    

    /**
     * Method name: doProcess_NMAL6700Scrn00_AddShipping <dd>The method
     * explanation: Add Shipping.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_AddShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Save current input
        saveCurrentPageToSMsg_TabShipping(cMsg, sMsg);
        int rowNum = sMsg.M.getValidCount();
        if (rowNum == sMsg.M.length()) {
            cMsg.setMessageInfo(NMAM8187E, new String[] {"Msgs Notes", String.valueOf(sMsg.M.length()) });
            return;
        }

        // Clear new row
        int addedRowIndex = sMsg.M.getValidCount();
        sMsg.M.no(addedRowIndex).clear();

        // 2018/12/10 QC#29315 Add Start 
        // Create carrier pulldown
        S21SsmEZDResult resPullDown = NMAL6700Query.getInstance().getBankAcctPulldownList(getGlobalCompanyCode());
        List<Map<String, Object>> resPullDownList = null;
        if (resPullDown.isCodeNormal()) {
            resPullDownList = (List<Map<String, Object>>) resPullDown.getResultObject();
        }
        // 2018/12/10 QC#29315 Add End
        NMAL6700_MSMsg msMsg = sMsg.M.no(addedRowIndex);
        
        // 2018/12/10 QC#29315 Add Start
        int cnt = resPullDownList.size();
        for (int i = 0; i < cnt; i++) {
            Map<String, Object> carr = resPullDownList.get(i);
            ZYPEZDItemValueSetter.setValue(msMsg.vndCd_M1.no(i), (String) carr.get("VND_CD"));
            ZYPEZDItemValueSetter.setValue(msMsg.locNm_M2.no(i), (String) carr.get("LOC_NM"));
        }
        // 2018/12/10 QC#29315 Add End
        
        msMsg.custEffLvlCd_M3.setValue(CUST_EFF_LVL.ACCOUNT_ONLY);
        sMsg.M.setValidCount(addedRowIndex + 1);
        // Display page of first added row
        NMAL6700CommonLogic.getPageFromSMsg_TabShipping(cMsg, sMsg, addedRowIndex);
    }

    /**
     * Method name: doProcess_NMAL6700Scrn00_Shipping <dd>The method
     * explanation: DeleteShipping.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_DeleteShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        // Save current input
        saveCurrentPageToSMsg_TabShipping(cMsg, sMsg);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sMsg.M, "xxChkBox_M1", ZYPConstant.CHKBOX_ON_Y);
        if (checkList.isEmpty()) {
            cMsg.setMessageInfo(NMAM0835E);
            return;
        }
        // Delete selected rows from screen
        int rearrangeRowIndex = 0;
        int pageNum = -1;
        for (int i = 0; i < sMsg.M.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.M.no(i).xxChkBox_M1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.M.no(i).dsCustShpgDefPk_M1)) {
                    // 2018/12/10 QC#29315 Mod Start
//                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).dsCustShpgDefPk_Z1, sMsg.M.no(i).dsCustShpgDefPk_M1);
//                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_Z2, sMsg.M.no(i).ezUpTime_M2);
//                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_Z2, sMsg.M.no(i).ezUpTimeZone_M2);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).dsAcctCarrPk_Z1, sMsg.M.no(i).dsAcctCarrPk_M1);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).dsCustShpgDefPk_Z1, sMsg.M.no(i).dsCustShpgDefPk_M1);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_ZS, sMsg.M.no(i).ezUpTime_MS);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_ZS, sMsg.M.no(i).ezUpTimeZone_MS);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_ZC, sMsg.M.no(i).ezUpTime_MC);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_ZC, sMsg.M.no(i).ezUpTimeZone_MC);
                    // 2018/12/10 QC#29315 Mod End
                    sMsg.Z.setValidCount(sMsg.Z.getValidCount() + 1);
                }
                if (pageNum < 0) {
                    pageNum = i;
                } else {
                    if ((pageNum / cMsg.M.length()) != (i / cMsg.M.length())) {
                        pageNum = cMsg.xxPageShowFromNum_M2.getValueInt();
                    }
                }
                sMsg.M.no(i).clear();
            } else {
                if (rearrangeRowIndex < i) {
                    EZDMsg.copy(sMsg.M.no(i), null, sMsg.M.no(rearrangeRowIndex), null);
                }
                rearrangeRowIndex++;
            }
        }
        sMsg.M.setValidCount(rearrangeRowIndex);

        // Show first page
        NMAL6700CommonLogic.getPageFromSMsg_TabShipping(cMsg, sMsg, pageNum);
    }

    private void saveCurrentPageToSMsg_TabShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        for (int i = 0; i < cMsg.M.getValidCount(); i++) {
            NMAL6700_MCMsg mcMsg = cMsg.M.no(i);
            // New insert line
            if (!ZYPCommonFunc.hasValue(mcMsg.dsCustShpgDefPk_M1)) {
                NMAL6700CommonLogic.createShippingPulldownList(mcMsg, getGlobalCompanyCode());
            }
        }

        // Save CMsg to SMsg
        savePageToSMsg_TabShipping(cMsg, sMsg, cMsg.xxPageShowFromNum_M2.getValueInt() - 1);
    }

    private static void savePageToSMsg_TabShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int startIndexOfSMsg) {
        for (int i = 0; i < cMsg.M.getValidCount(); i++) {
            EZDMsg.copy(cMsg.M.no(i), null, sMsg.M.no(startIndexOfSMsg + i), null);
        }
    }

    /**
     * Method name: pageNextShipping <dd>The method explanation:
     * PageNext For TAB_SHIPPING.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageNextShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabShipping(cMsg, sMsg, cMsg.xxPageShowFromNum_M2.getValueInt(), cMsg.xxPageShowFromNum_M2.getValueInt() + cMsg.M.length());

    }

    private void changePage_TabShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, int oldDisplayStartRowNum, int newDisplayStartRowNum) {
        // Save current page to sMsg
        savePageToSMsg_TabShipping(cMsg, sMsg, oldDisplayStartRowNum - 1);

        // Display next page from sMsg
        int sMsgStartIndexOfNextPage = newDisplayStartRowNum - 1;
        NMAL6700CommonLogic.getPageFromSMsg_TabShipping(cMsg, sMsg, sMsgStartIndexOfNextPage);

        // Set new page number
        cMsg.xxPageShowFromNum_M2.setValue(sMsgStartIndexOfNextPage + 1);
        cMsg.xxPageShowToNum_M2.setValue(sMsgStartIndexOfNextPage + cMsg.M.getValidCount());
    }

    /**
     * Method name: pagePrevShipping <dd>The method explanation:
     * PagePrev For TAB_SHIPPING.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pagePrevShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabShipping(cMsg, sMsg, cMsg.xxPageShowFromNum_M2.getValueInt(), cMsg.xxPageShowFromNum_M2.getValueInt() - cMsg.M.length());

    }

    /**
     * Method name: pageJumpShipping <dd>The method explanation:
     * PagePrev For TAB_SHIPPING.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void pageJumpShipping(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        changePage_TabShipping(cMsg, sMsg, cMsg.xxPageShowFromNum_M2.getValueInt(), (cMsg.xxPageShowCurNum_M2.getValueInt() - 1) * cMsg.M.length() + 1);

    }

    /**
     * Method name: doProcess_NMAL6700_NMAL6750 
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_OnChange_frtCond(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        NMAL6700_MCMsg mcMsg = cMsg.M.no(cMsg.xxRowNum.getValueInt());
        mcMsg.shpgSvcLvlCd_M3.clear();
        NMAL6700CommonLogic.createShpgSvcLvlPulldown(getGlobalCompanyCode(), mcMsg);
    }

    /**
     * Method name: NMAL6700Scrn00_OnChange_lineBizTp
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6700Scrn00_OnChange_lineBizTp(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {
        NMAL6700_MCMsg mcMsg = cMsg.M.no(cMsg.xxRowNum.getValueInt());
        mcMsg.frtCondCd_M3.clear();
        NMAL6700CommonLogic.createFrtCondPulldown(getGlobalCompanyCode(), mcMsg);
    }
    // 2018/02/14 QC#20297(Sol#379) add end

    /**
     * QC#57316 Add Method
     * doProcess_NMAL6700_OpenTrx_Download explanation: Open trx
     * Download Button.
     * @param cMsg NMAL6700CMsg
     * @param sMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700_OpenTrx_Download(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        String glblCmpyCd = getGlobalCompanyCode();
        String acctNum = cMsg.dsAcctNum_H1.getValue();

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL6700Query.getInstance().getClass());

            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_OPEN_TRX), ".csv");
            Map<String, Object> query = new HashMap<String, Object>();

            query.put("glblCmpyCd", glblCmpyCd);
            query.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            query.put("acctNum", acctNum);

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
            query.put("locRoleTpShip", LOC_ROLE_TP.SHIP_TO);
            // START 2022/07/28 A.Cullano [QC#60173, ADD]
            query.put("skipRecovTpSkip", SKIP_RECOV_TP.SKIP);
            // END 2022/07/28 A.Cullano [QC#60173, ADD]
            query.put("limitRownum", LIMIT_DL_ROWNUM + 1);

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
     * @param cMsg NMAL6700CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public void writeCsvFileOpenTrx(NMAL6700CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL6700F03FMsg fMsg = new NMAL6700F03FMsg();
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
}
