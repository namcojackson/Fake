/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6700;

import static business.blap.NMAL6700.constant.NMAL6700Constant.BUSINESS_ID;
import static business.blap.NMAL6700.constant.NMAL6700Constant.COL_LOC_NM_MAX_DIGIT_LENGTH;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_CONTRACT_GRACE_PERIOD;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_GRACE_PERIOD;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_NAME_CCY;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_NAME_CR_HOLD;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_NAME_CR_LIMIT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_NAME_DEF_COLLECTOR;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DPLY_NAME_PMT_TERM;
import static business.blap.NMAL6700.constant.NMAL6700Constant.DUP_MSG_BIZ_AREA_AND_GRP_NM;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MAX_EFF_THRU_DT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MAX_SPCL_MSG_LEN;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MSG_CR_PRFL_INFO;
import static business.blap.NMAL6700.constant.NMAL6700Constant.MSG_DUP;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NAMM0016E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0072E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0076E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0176E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0177E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0208E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0293E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0294E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0306E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0834E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0835E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM0836E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8111E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8115E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8121E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8139W;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8177E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8178E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8179E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8180E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8185W;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8186E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8200E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8250E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8251E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8252E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8254E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8287E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8333I;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8343E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8362E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8363E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8371E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8372E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8409E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8638E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8648I;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8658I;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8682E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8689E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8690E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8691E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NMAM8701E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NWAM0984E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.NZZM0003E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.PROS_TO_CUST_CHNG_STS_PENDING;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_ADDRESSES;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_BANK_ACCT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_CONTACTS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_CR_CLT;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_INV_BLLG;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_MARKETING;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_MSG_NOTE;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_RELNSHIPS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_SHIPPING;
import static business.blap.NMAL6700.constant.NMAL6700Constant.TAB_TRANSACTIONS;
import static business.blap.NMAL6700.constant.NMAL6700Constant.ZZIM0094E;
import static business.blap.NMAL6700.constant.NMAL6700Constant.ZZM9000E;

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
import parts.common.EZDSStringItem;
import parts.common.EZDStringUtil;
import parts.common.EZDTDateItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6700.common.NMAL6700CommonLogic;
import business.blap.NMAL6700.constant.NMAL6700Constant;
import business.db.ACCT_LOCTMsg;
import business.db.ALT_PAYERTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CMPYTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.CUST_BLLG_VCLETMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.DS_ACCT_CARRTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_ACCT_GRPTMsg;
import business.db.DS_ACCT_GRP_ASGTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_PROSTMsgArray;
import business.db.DS_ACCT_REF_ATTRBTMsg;
import business.db.DS_ACCT_RELNTMsg;
import business.db.DS_BANK_ACCTTMsg;
import business.db.DS_CTAC_PSN_RELNTMsg;
import business.db.DS_CUST_INV_RULETMsg;
import business.db.DS_CUST_INV_RULE_RCPNTTMsg;
import business.db.DS_CUST_SHPG_DEFTMsg;
import business.db.DS_CUST_SPCL_HDLGTMsg;
import business.db.DS_CUST_SPCL_MSGTMsg;
import business.db.DS_CUST_TRX_RULETMsg;

import business.db.DS_INV_TGTR_TPTMsg;
import business.db.INV_RCPNTTMsg;
import business.db.PRIN_CUSTTMsg;
import business.db.PROS_PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_ACCS_PMITTMsg;
import business.db.SVC_ACCS_PMIT_VALTMsg;
import business.db.SVC_ACCS_PMIT_VALTMsgArray;
import business.parts.NFZC102001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_BLLG_VCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DEF_INV_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CHNG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_RULE_RCPNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRIN_CUST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PMIT_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TAX_ORG_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.USR_DLR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/21   Fujitsu         N.Sugiura       Update          QC#15971
 * 2017/06/29   Fujitsu         M.Yamada        Update          QC#18961
 * 07/18/2017   CITS            K.Ogino         Update          QC#19433
 * 2017/08/08   Fujitsu         N.Sugiura       Update          QC#8598
 * 2017/08/09   Hitachi         J.Kim           Update          QC#20184
 * 2017/10/03   Fujitsu         T.Aoi           Update          QC#21198
 * 2017/11/16   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 * 2017/12/11   Fujitsu         Hd.Sugawara     Update          QC#20357
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/02/14   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/03/16   Fujitsu         Hd.Sugawara     Update          QC#20357-1
 * 2018/04/16   Hitachi         E.Kameishi      Update          QC#21037
 * 2018/04/19   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/05/07   Fujitsu         H.Nagashima     Update          QC#23604
 * 2018/06/13   Fujitsu         Mz.Takahashi    Update          QC#25457
 * 2018/06/25   Hitachi         U.Kim           Update          QC#26703
 * 2018/07/04   Fujitsu         Mz.Takahashi    Update          QC#25339
 * 2018/07/30   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/17   Fujitsu         H.Kumagai       Update          QC#27786
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2018/08/30   Fujitsu         W.Honda         Update          QC#27869
 * 2018/09/12   Fujitsu         Mz.Takahashi    Update          QC#28165
 * 2018/10/09   Fujitsu         Hd.Sugawara     Update          QC#27598
 * 2018/11/13   Fujitsu         M.Ohno          Update          QC#27954
 * 2018/12/10   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/01/07   Fujitsu         Hd.Sugawara     Update          QC#29749
 * 2019/01/19   Fujitsu         S.Kosaka        Update          QC#29940
 * 2019/04/05   Fujitsu         T.Noguchi       Update          QC#31030
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2019/12/13   Fujitsu         M.Nakamura      Update          QC#54882
 * 2020/07/22   CITS            K.Ogino         Update          QC#57316
 * 2020/12/14   CITS            J.Evangelista   Update          QC#57937
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/05/31   CITS            K.Ogino         Update          QC#59957
 * 2022/07/21   Hitachi         K.Kim           Update          QC#60111
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2024/03/11   Hitachi         S.Ikariya       Update          QC#63499
 * 2024/03/12   Hitachi         T.Nagae         Update          QC#63552
 *</pre>
 */
public class NMAL6700BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6700CMsg bizMsg = (NMAL6700CMsg) cMsg;
        NMAL6700SMsg sharedMsg = (NMAL6700SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            bizMsg.setCommitSMsg(true);

            if ("NMAL6700Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6700Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else if (screenAplID.endsWith("CMN_ColSave")) {
                ZYPGUITableColumn.setColData((NMAL6700CMsg) cMsg, (NMAL6700SMsg) sMsg);
            } else if (screenAplID.endsWith("CMN_ColClear")) {
                ZYPGUITableColumn.clearColData((NMAL6700CMsg) cMsg, (NMAL6700SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private boolean checkInputTabMandatory(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        boolean errorFlg = true;

        // TAB_RELNSHIPS
        for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
            NMAL6700_CSMsg csMsg = sharedMsg.C.no(i);
            if (!ZYPCommonFunc.hasValue(csMsg.dsAcctRelnTpCd_C3)) {
                csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, ZZM9000E, new String[]{"Relationship Type"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(csMsg.dsAcctNum_C1)) {
                csMsg.dsAcctNum_C1.setErrorInfo(1, ZZM9000E, new String[]{"Related Account#"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(csMsg.effFromDt_C1)) {
                csMsg.effFromDt_C1.setErrorInfo(1, ZZM9000E, new String[]{"Start Date"});
                errorFlg = false;
            }
            if (!ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CB.getValue()) && !ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CS.getValue())) {
                csMsg.xxChkBox_CB.setErrorInfo(1, NMAM0835E);
                csMsg.xxChkBox_CS.setErrorInfo(1, NMAM0835E);
                errorFlg = false;
            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_RELNSHIPS);
            return errorFlg;
        }
        // TAB_MARKETING
        for (int i = 0; i < sharedMsg.E.getValidCount(); i++) {
             NMAL6700_ESMsg esMsg = sharedMsg.E.no(i);
            if (!ZYPCommonFunc.hasValue(esMsg.dsBizAreaCd_E3)) {
                esMsg.dsBizAreaCd_E3.setErrorInfo(1, ZZM9000E, new String[]{"Business Area"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(esMsg.dsAcctGrpCd_E3)) {
                esMsg.dsAcctGrpCd_E3.setErrorInfo(1, ZZM9000E, new String[]{"Group Code"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(esMsg.effFromDt_E1)) {
                esMsg.effFromDt_E1.setErrorInfo(1, ZZM9000E, new String[]{"Start Date"});
                errorFlg = false;
            }

        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_MARKETING);
            return errorFlg;
        }
        // TAB_TRANSACTIONS
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
             NMAL6700_FCMsg fcMsg = bizMsg.F.no(i);
            if (!ZYPCommonFunc.hasValue(fcMsg.dsTrxRuleTpCd_F3)) {
                fcMsg.dsTrxRuleTpCd_F3.setErrorInfo(1, ZZM9000E, new String[]{"Transaction Type"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(fcMsg.custEffLvlCd_F3)) {
                fcMsg.custEffLvlCd_F3.setErrorInfo(1, ZZM9000E, new String[]{"Effective Level"});
                errorFlg = false;
            }
        }
        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
            NMAL6700_SCMsg scMsg = bizMsg.S.no(i);
           if (!ZYPCommonFunc.hasValue(scMsg.dsSpclHdlgTpCd_S3)) {
               scMsg.dsSpclHdlgTpCd_S3.setErrorInfo(1, ZZM9000E, new String[]{"Type"});
               errorFlg = false;
           }
           if (!ZYPCommonFunc.hasValue(scMsg.dsSpclHdlgTpValCd_S3)) {
               scMsg.dsSpclHdlgTpValCd_S3.setErrorInfo(1, ZZM9000E, new String[]{"Value"});
               errorFlg = false;
           }
           if (!ZYPCommonFunc.hasValue(scMsg.effFromDt_S1)) {
               scMsg.effFromDt_S1.setErrorInfo(1, ZZM9000E, new String[]{"Start Date"});
               errorFlg = false;
           }
           if (!ZYPCommonFunc.hasValue(scMsg.custEffLvlCd_S3)) {
               scMsg.custEffLvlCd_S3.setErrorInfo(1, ZZM9000E, new String[]{"Effective Level"});
               errorFlg = false;
           }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
            return errorFlg;
        }
        // TAB_CR_CLT
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H3.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.ccyCd_U3)) {
                bizMsg.ccyCd_U3.setErrorInfo(1, ZZM9000E, new String[]{DPLY_NAME_CCY});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crLimitAmt_U1)) {
                bizMsg.crLimitAmt_U1.setErrorInfo(1, ZZM9000E, new String[]{DPLY_NAME_CR_LIMIT});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crChkReqTpCd_U3)) {
                bizMsg.crChkReqTpCd_U3.setErrorInfo(1, ZZM9000E, new String[]{DPLY_NAME_CR_HOLD});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crRiskClsCd_U3)) {
                bizMsg.crRiskClsCd_U3.setErrorInfo(1, ZZM9000E, new String[]{DPLY_GRACE_PERIOD});
                errorFlg = false;
            }
            // START 2018/01/25 [QC#22095, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.contrCrRiskClsCd_U3)) {
                bizMsg.contrCrRiskClsCd_U3.setErrorInfo(1, ZZM9000E, new String[]{DPLY_CONTRACT_GRACE_PERIOD});
                errorFlg = false;
            }
            // END   2018/01/25 [QC#22095, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscCd_U3)) {
                bizMsg.pmtTermCashDiscCd_U3.setErrorInfo(1, ZZM9000E, new String[]{DPLY_NAME_PMT_TERM});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.cltPtfoCd_U1)) {
                bizMsg.cltPtfoCd_U1.setErrorInfo(1, ZZM9000E, new String[]{DPLY_NAME_DEF_COLLECTOR});
                errorFlg = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.ccyCd_U3)
                || ZYPCommonFunc.hasValue(bizMsg.crLimitAmt_U1)
                || ZYPCommonFunc.hasValue(bizMsg.crChkReqTpCd_U3)
                || ZYPCommonFunc.hasValue(bizMsg.crRiskClsCd_U3)
                // START 2018/01/25 [QC#22095, ADD]
                || ZYPCommonFunc.hasValue(bizMsg.contrCrRiskClsCd_U3)
                // END   2018/01/25 [QC#22095, ADD]
                || ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscCd_U3)) {

            if (!ZYPCommonFunc.hasValue(bizMsg.ccyCd_U3)) {
                bizMsg.ccyCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_CCY, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crLimitAmt_U1)) {
                bizMsg.crLimitAmt_U1.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_CR_LIMIT, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crChkReqTpCd_U3)) {
                bizMsg.crChkReqTpCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_CR_HOLD, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crRiskClsCd_U3)) {
                bizMsg.crRiskClsCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_GRACE_PERIOD, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            // START 2018/01/25 [QC#22095, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.contrCrRiskClsCd_U3)) {
                bizMsg.contrCrRiskClsCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_CONTRACT_GRACE_PERIOD, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            // END   2018/01/25 [QC#22095, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscCd_U3)) {
                bizMsg.pmtTermCashDiscCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_PMT_TERM, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.ovrdPmtTermFlg_U1) && ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.ovrdPmtTermFlg_U1.getValue())
                || ZYPCommonFunc.hasValue(bizMsg.cashOrCcReqFlg_U1) && ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.cashOrCcReqFlg_U1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.ccyCd_U3)) {
                bizMsg.ccyCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_CCY, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crLimitAmt_U1)) {
                bizMsg.crLimitAmt_U1.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_CR_LIMIT, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crChkReqTpCd_U3)) {
                bizMsg.crChkReqTpCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_CR_HOLD, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            if (!ZYPCommonFunc.hasValue(bizMsg.crRiskClsCd_U3)) {
                bizMsg.crRiskClsCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_GRACE_PERIOD, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            // START 2018/01/25 [QC#22095, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.contrCrRiskClsCd_U3)) {
                bizMsg.contrCrRiskClsCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_CONTRACT_GRACE_PERIOD, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
            // END   2018/01/25 [QC#22095, ADD]
            if (!ZYPCommonFunc.hasValue(bizMsg.pmtTermCashDiscCd_U3)) {
                bizMsg.pmtTermCashDiscCd_U3.setErrorInfo(1, NAMM0016E, new String[] {DPLY_NAME_PMT_TERM, MSG_CR_PRFL_INFO});
                errorFlg = false;

            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_CR_CLT);
            return errorFlg;
        }
        // TAB_INV_BLLG
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
             NMAL6700_GCMsg gcMsg = bizMsg.G.no(i);
            if (!ZYPCommonFunc.hasValue(gcMsg.custInvSrcCd_G3)) {
                gcMsg.custInvSrcCd_G3.setErrorInfo(1, ZZM9000E, new String[]{"Invoice Source"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(gcMsg.custBllgTpCd_G3)) {
                gcMsg.custBllgTpCd_G3.setErrorInfo(1, ZZM9000E, new String[]{"Bill Type"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(gcMsg.custBllgVcleCd_G3)) {
                gcMsg.custBllgVcleCd_G3.setErrorInfo(1, ZZM9000E, new String[]{"Bill Vehicle"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(gcMsg.custEffLvlCd_G3)) {
                gcMsg.custEffLvlCd_G3.setErrorInfo(1, ZZM9000E, new String[]{"Effective Level"});
                errorFlg = false;
            }
        }
        for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
            NMAL6700_KCMsg kcMsg = bizMsg.K.no(i);
           if (!ZYPCommonFunc.hasValue(kcMsg.bllgAttrbNm_K1)) {
               kcMsg.bllgAttrbNm_K1.setErrorInfo(1, ZZM9000E, new String[]{"Billing Attribute Name"});
               errorFlg = false;
           }
           if (!ZYPCommonFunc.hasValue(kcMsg.custEffLvlCd_K3)) {
               kcMsg.custEffLvlCd_K3.setErrorInfo(1, ZZM9000E, new String[]{"Effective Level"});
               errorFlg = false;
           }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return errorFlg;
        }
        // 2018/12/10 QC#29315 Del Start
        // TAB_BANK_ACCT
//        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
//             NMAL6700_WCMsg wcMsg = bizMsg.W.no(i);
//            if (!ZYPCommonFunc.hasValue(wcMsg.vndCd_W3)) {
//                wcMsg.vndCd_W3.setErrorInfo(1, ZZM9000E, new String[]{"Carrier"});
//                errorFlg = false;
//            }
//        }
//        if (!errorFlg) {
//            bizMsg.xxDplyTab.setValue(TAB_BANK_ACCT);
//            return errorFlg;
//        }
        // 2018/12/10 QC#29315 Del End

        // TAB_MSG_NOTE
        for (int i = 0; i < sharedMsg.J.getValidCount(); i++) {
             NMAL6700_JSMsg jsMsg = sharedMsg.J.no(i);
            if (!ZYPCommonFunc.hasValue(jsMsg.lineBizTpCd_J3)) {
                jsMsg.lineBizTpCd_J3.setErrorInfo(1, ZZM9000E, new String[]{"Line Of Business"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(jsMsg.dsBizAreaCd_J3)) {
                jsMsg.dsBizAreaCd_J3.setErrorInfo(1, ZZM9000E, new String[]{"Business Area"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(jsMsg.dsCustMsgTpCd_J3)) {
                jsMsg.dsCustMsgTpCd_J3.setErrorInfo(1, ZZM9000E, new String[]{"Type"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(jsMsg.dsCustMsgTxt_J1)) {
                jsMsg.dsCustMsgTxt_J1.setErrorInfo(1, ZZM9000E, new String[]{"Message Body"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(jsMsg.custEffLvlCd_J3)) {
                jsMsg.custEffLvlCd_J3.setErrorInfo(1, ZZM9000E, new String[]{"Effective Level"});
                errorFlg = false;
            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_MSG_NOTE);
            return errorFlg;
        }

        // 2018/02/14 QC#20297(Sol#379) add start
        // TAB_SHIPPING
        // 2023/01/13 QC#60860 Add Start
        sharedMsg = NMAL6700CommonLogic.vndCdHasValue(getGlobalCompanyCode(), sharedMsg);
        // 2023/01/13 QC#60860 Add End
        for (int i = 0; i < sharedMsg.M.getValidCount(); i++) {
             NMAL6700_MSMsg msMsg = sharedMsg.M.no(i);
            if (!ZYPCommonFunc.hasValue(msMsg.lineBizTpCd_M3)) {
                msMsg.lineBizTpCd_M3.setErrorInfo(1, ZZM9000E, new String[]{"Line Of Business"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(msMsg.dsBizAreaCd_M3)) {
                msMsg.dsBizAreaCd_M3.setErrorInfo(1, ZZM9000E, new String[]{"Business Area"});
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(msMsg.custEffLvlCd_M3)) {
                msMsg.custEffLvlCd_M3.setErrorInfo(1, ZZM9000E, new String[]{"Effective Level"});
                errorFlg = false;
            }
            // 2018/12/10 QC#29315 Add Start
            if (!ZYPCommonFunc.hasValue(msMsg.effFromDt_M1)) {
                msMsg.effFromDt_M1.setErrorInfo(1, ZZM9000E, new String[]{"Start Date"});
                errorFlg = false;
            }
            if (ZYPCommonFunc.hasValue(msMsg.dsCarrAcctNum_M1) && !ZYPCommonFunc.hasValue(msMsg.vndCd_M3)) {
            // 2023/01/13 QC#60860 Mod Start
//                msMsg.vndCd_M3.setErrorInfo(1, ZZIM0094E, new String[] {"If Account Number is entered, Carrier" });
                msMsg.carrNm_M3.setErrorInfo(1, ZZIM0094E, new String[] {"If Account Number is entered, Carrier" });
            // 2023/01/13 QC#60860 Mod Start
                errorFlg = false;
            }
            // 2018/12/10 QC#29315 Add End
            // 2023/01/13 QC#60860 Add Start
            if ( ZYPCommonFunc.hasValue(msMsg.carrNm_M3) && !ZYPCommonFunc.hasValue(msMsg.vndCd_M3)) {
                msMsg.carrNm_M3.setErrorInfo(1, NMAM8121E, new String[] {"Carrier" });
                errorFlg = false;
            }
            // 2023/01/13 QC#60860 Add End
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_SHIPPING);
            return errorFlg;
        }
        // 2018/02/14 QC#20297(Sol#379) add end
        return errorFlg;
    }
    /**
     * Method name: doProcess_NMAL6700Scrn00_CMN_Submit <dd>
     * The method explanation: submit button event
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     */
    private void doProcess_NMAL6700Scrn00_CMN_Submit(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!submit(bizMsg, sharedMsg)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H1, sharedMsg.dsAcctNum_BK);
        }
    }
    /**
     * submit
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     */
    private boolean submit(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_OFF_N);

        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H3.getValue())) {
            NMAL6700CommonLogic.setTemplateForCredit(bizMsg, sharedMsg);
        }
        // Input check Header
        if (!inputCheckForHeader(glblCmpyCd, bizMsg, sharedMsg)) {
            return false;
        }
        // #################
        // Mandatory Check
        // #################
        NMAL6700CommonLogic.savePageToSMsg_TabRelnShips(bizMsg, sharedMsg, bizMsg.xxPageShowFromNum_C1.getValueInt() - 1);
        NMAL6700CommonLogic.copyBizToShareE(bizMsg, sharedMsg);
        NMAL6700CommonLogic.copyCMsgToSMsgJ(bizMsg, sharedMsg);
        // 2018/02/14 QC#20297(Sol#379) add start
        NMAL6700CommonLogic.copyCMsgToSMsgM(bizMsg, sharedMsg);
        // 2018/02/14 QC#20297(Sol#379) add end
        if (!checkInputTabMandatory(bizMsg, sharedMsg)) {
            if (bizMsg.xxDplyTab.getValue().equals(TAB_RELNSHIPS)) {
                NMAL6700CommonLogic.showFirstErrorPage_TabRelnShips(bizMsg, sharedMsg);
            }
            if (bizMsg.xxDplyTab.getValue().equals(TAB_MARKETING)) {
                NMAL6700CommonLogic.showFirstErrorPage_TabMarketing(bizMsg, sharedMsg);
            }
            if (bizMsg.xxDplyTab.getValue().equals(TAB_MSG_NOTE)) {
                NMAL6700CommonLogic.showFirstErrorPage_TabMsgNote(bizMsg, sharedMsg);
            }
            // 2018/02/14 QC#20297(Sol#379) add start
            if (bizMsg.xxDplyTab.getValue().equals(TAB_SHIPPING)) {
                NMAL6700CommonLogic.showFirstErrorPage_TabShipping(bizMsg, sharedMsg);
            }
            // 2018/02/14 QC#20297(Sol#379) add end
            return false;
        }

        // Add Start 2018/10/09 QC#27598
        // Mod Start QC#57316
//        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_H1.getValue())) {
        if (!openTrnCheck(bizMsg, sharedMsg)) {
            bizMsg.xxChkBox_H1.setErrorInfo(1, NWAM0984E);
            bizMsg.setMessageInfo(NWAM0984E);
            // bizMsg.xxBtnFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
            return false;
        }
//        }
        // Add End 2018/10/09 QC#27598

        // Create/Update Header
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1)) {
            updateAcctInfo(bizMsg, sharedMsg, glblCmpyCd);
            updateAcctNmForLocInfo(bizMsg, sharedMsg, glblCmpyCd);
            updateCoAForLoc(bizMsg, sharedMsg, glblCmpyCd);
        } else {
            createAcctInfo(bizMsg, glblCmpyCd);
        }

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            return false;
        }

        // Input check Tab
        // Input check for Address
        int pageFromNum = bizMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        NMAL6700CommonLogic.setPagenation(bizMsg, sharedMsg, pageFromNum);
        if (!inputCheckForAddress(glblCmpyCd, bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabAddress(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_ADDRESSES);
            return false;
        }
        // Input check for RelnShips
        NMAL6700CommonLogic.savePageToSMsg_TabRelnShips(bizMsg, sharedMsg, bizMsg.xxPageShowFromNum_C1.getValueInt() - 1);
        if (!inputCheckForRelnships(bizMsg, sharedMsg, glblCmpyCd)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabRelnShips(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_RELNSHIPS);
            return false;
        }

        // Input check for Contacts
        NMAL6700CommonLogic.savePageToSMsg_TabContacts(bizMsg, sharedMsg, bizMsg.xxPageShowFromNum_D1.getValueInt() - 1);
        if (!inputCheckForContacts(glblCmpyCd, bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabContacts(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_CONTACTS);
            return false;
        }

        // Input check for Marketing
        NMAL6700CommonLogic.copyBizToShareE(bizMsg, sharedMsg);
        if (!inputCheckForMarketing(bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabMarketing(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_MARKETING);
            return false;
        }
        if (!masterCheckForMarketing(bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabMarketing(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_MARKETING);
            return false;
        }

        // Input check for Transactions
        if (!inputCheckForTransactions(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
            return false;
        }
        if (!masterCheckForTransactions(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
            return false;
        }

        // Input check for Collections
        if (!inputCheckForCollections(bizMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_CR_CLT);
            return false;
        }
        if (!masterCheckForCollections(bizMsg, glblCmpyCd)) {
            bizMsg.xxDplyTab.setValue(TAB_CR_CLT);
            return false;
        }

        // Input check for Invoice/Billing
        if (!inputCheckForInvBllg(bizMsg, glblCmpyCd)) {
            bizMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return false;
        }
        if (!masterCheckForInvBllg(bizMsg, glblCmpyCd)) {
            bizMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return false;
        }

        // Input check for Bank Accounts
        // 2018/12/10 QC#29315 Del Start
//        EZDMsg.copy(bizMsg.W, null, sharedMsg.W, null);
        // 2018/12/10 QC#29315 Del End
        // START 2020/12/14 J.Evangelista [QC#57937,ADD]
        NMAL6700CommonLogic.copyBizToShareI(bizMsg, sharedMsg);
        // END   2020/12/14 J.Evangelista [QC#57937,ADD]        
        if (!inputCheckForBankAcct(bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabBankAcct(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_BANK_ACCT);
            return false;
        }

        // Input check for Instructions
        NMAL6700CommonLogic.copyCMsgToSMsgJ(bizMsg, sharedMsg);
        if (!inputCheckForMsgNote(bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabMsgNote(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_MSG_NOTE);
            return false;
        }

        // 2018/02/14 QC#20297(Sol#379) add start
        // Input check for Shipping
        NMAL6700CommonLogic.copyCMsgToSMsgM(bizMsg, sharedMsg);
        if (!inputCheckForShipping(bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabShipping(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_SHIPPING);
            return false;
        }
        // 2018/02/14 QC#20297(Sol#379) add end

        // Update Address
        if (!updateAddress(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_ADDRESSES);
            return false;
        }
        // Create/Update RelnShips
        if (!updateRelnShips(glblCmpyCd, bizMsg, sharedMsg)) {
            NMAL6700CommonLogic.showFirstErrorPage_TabRelnShips(bizMsg, sharedMsg);
            bizMsg.xxDplyTab.setValue(TAB_RELNSHIPS);
            return false;
        }

        // Update Contacts
        if (!updateContacts(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_CONTACTS);
            return false;
        }

        // Update Marketing
        if (!updateMarketing(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_MARKETING);
            return false;
        }

        // Update Transactions
        if (!updateTransactions(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
            return false;
        }

        // Update Collections
        if (!updateCollections(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_CR_CLT);
            return false;
        }

        // Update Invoice/Billing
        if (!updateInvBllg(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_INV_BLLG);
            return false;
        }

        // Update Bank Accounts
        if (!updateBankAcct(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_BANK_ACCT);
            return false;
        }

        // Update Instructions
        if (!updateInstructions(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_MSG_NOTE);
            return false;
        }

        // 2018/02/14 QC#20297(Sol#379) add start
        // Update Sipping
        if (!updateShipping(glblCmpyCd, bizMsg, sharedMsg)) {
            bizMsg.xxDplyTab.setValue(TAB_SHIPPING);
            return false;
        }
        // 2018/02/14 QC#20297(Sol#379) add end
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxExistCmntFlg.getValue())) {
            bizMsg.setMessageInfo(NMAM8333I);
        }

        return true;
    }

    private boolean inputCheckForHeader(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        boolean rtnFlg = true;
        if (ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
            String defCoaAfflCd = NMAL6700CommonLogic.getDefCoaAfflCd();
            // Add Start 2019/01/07 QC#29749
            ArrayList<String> itrlNonAfflAcctNumList = NMAL6700CommonLogic.getItrlNonAfflAcct(glblCmpyCd);
            // Add End 2019/01/07 QC#29749

            // Mod Start 2019/01/07 QC#29749
            //if (bizMsg.dsAcctItrlFlg_H3.getValue().equals(ZYPConstant.FLG_ON_Y)
            //    && bizMsg.coaAfflCd_H1.getValue().equals(defCoaAfflCd)) {
            if (bizMsg.dsAcctItrlFlg_H3.getValue().equals(ZYPConstant.FLG_ON_Y) && //
                    bizMsg.coaAfflCd_H1.getValue().equals(defCoaAfflCd) && //
                    !itrlNonAfflAcctNumList.contains(bizMsg.dsAcctNum_H1.getValue())) {
                // Mod End 2019/01/07 QC#29749
                bizMsg.coaAfflCd_H1.setErrorInfo(1, NMAM8371E, new String[] {defCoaAfflCd});
                rtnFlg = false;
            }

            COA_AFFLTMsg coaAfflTMsg = NMAL6700CommonLogic.findCoaAffl(getGlobalCompanyCode(), bizMsg.coaAfflCd_H1.getValue());
            if (coaAfflTMsg == null) {
                bizMsg.coaAfflCd_H1.setErrorInfo(1, NMAM8186E, new String[] {"GL Intercompany Code" });
                rtnFlg = false;
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.coaChCd_H1)) {
            COA_CHTMsg coaChTMsg = NMAL6700CommonLogic.findCoaCh(getGlobalCompanyCode(), bizMsg.coaChCd_H1.getValue());
            if (coaChTMsg == null) {
                bizMsg.coaChCd_H1.setErrorInfo(1, NMAM8186E, new String[] {"GL Sales Channel Code" });
                rtnFlg = false;
            }
        }

        if (!coaCmbnCheck(bizMsg)) {
            rtnFlg = false;
        }

        // Del Start 2018/10/09 QC#27598
        //if (!openTrnCheck(bizMsg, sharedMsg)) {
        //    bizMsg.xxChkBox_H1.setErrorInfo(1, NMAM8374E);
        //    rtnFlg = false;
        //}
        // Del End 2018/10/09 QC#27598

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
                && ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctInacRsnCd_H3)) {
                bizMsg.dsAcctInacRsnCd_H3.setErrorInfo(1, NMAM0836E, new String[] {"Reason" });
                rtnFlg = false;
            }
        }

        if (!"E".equals(bizMsg.getMessageKind())) {
            if (!bizMsg.dsAcctNm_H1.getValue().equals(bizMsg.dsAcctNm_BK.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxBtnFlg_H2.getValue())) {
                    String dsAcctNm = NMAL6700Query.getInstance().getDsAcctNumByAcctNm(glblCmpyCd, bizMsg.dsAcctNm_H1.getValue(), bizMsg.dsAcctNum_H1.getValue());
                    if (ZYPCommonFunc.hasValue(dsAcctNm)) {
                        String[] args = {"Account Name", "the system", "Submit" };
                        bizMsg.setMessageInfo(NMAM8185W, args);
                        bizMsg.xxBtnFlg_H2.setValue(ZYPConstant.FLG_ON_Y);
                        rtnFlg = false;
                    }
                }
            }
        }

        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H1)) {
                String[] args = {"Active Flg", "N", "Account Name" };
                bizMsg.setMessageInfo(NMAM8139W, args);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctTpCd_H3)) {
                String[] args = {"Active Flg", "N", "Category" };
                bizMsg.setMessageInfo(NMAM8139W, args);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctItrlFlg_H3)) {
                String[] args = {"Active Flg", "N", "Internal/External" };
                bizMsg.setMessageInfo(NMAM8139W, args);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctClsCd_H3)) {
                String[] args = {"Active Flg", "N", "Classification" };
                bizMsg.setMessageInfo(NMAM8139W, args);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.coaChCd_H1)) {
                String[] args = {"Active Flg", "N", "GL Sales Channel" };
                bizMsg.setMessageInfo(NMAM8139W, args);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
                String[] args = {"Active Flg", "N", "GL Intercompany Code" };
                bizMsg.setMessageInfo(NMAM8139W, args);
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctInacRsnCd_H3)) {
                String[] args = {"Reason"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }

        } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H1)) {
                String[] args = {"Account Name"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctTpCd_H3)) {
                String[] args = {"Category"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctItrlFlg_H3)) {
                String[] args = {"Internal/External"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctClsCd_H3)) {
                String[] args = {"Classification"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.coaChCd_H1)) {
                String[] args = {"GL Sales Channel Code"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(bizMsg.coaAfflCd_H1)) {
                String[] args = {"GL Intercompany Code"};
                bizMsg.setMessageInfo(NMAM0836E, args);
                rtnFlg = false;
            }

        }

        return rtnFlg;

    }

    private boolean inputCheckForAddress(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        boolean rtnFlg = true;

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1) && bizMsg.A.getValidCount() > 0) {
            int checkedCnt = 0;
            for (int i = 0; i < sharedMsg.A.getValidCount(); i++) {
                NMAL6700_ASMsg asmsg = (NMAL6700_ASMsg) sharedMsg.A.get(i);
                if ((ZYPCommonFunc.hasValue(asmsg.xxChkBox_AP) && ZYPConstant.FLG_ON_Y.equals(asmsg.xxChkBox_AP.getValue()))) {
                    checkedCnt++;
                }
            }
            if (checkedCnt > 1) {
                for (int i = 0; i < sharedMsg.A.getValidCount(); i++) {
                    NMAL6700_ASMsg asmsg = (NMAL6700_ASMsg) sharedMsg.A.get(i);
                    if ((ZYPConstant.FLG_ON_Y.equals(asmsg.xxChkBox_AP.getValue()))) {
                        asmsg.xxChkBox_AP.setErrorInfo(1, NMAM8287E, new String[] {"Primary" });
                    }
                }
                rtnFlg = false;
            }

            String salesDate = ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode(), BUSINESS_ID);
            for (int i = 0; i < sharedMsg.A.getValidCount(); i++) {
                NMAL6700_ASMsg asmsg = (NMAL6700_ASMsg) sharedMsg.A.get(i);
                if ((ZYPConstant.FLG_ON_Y.equals(asmsg.xxChkBox_AP.getValue())) && (ZYPCommonFunc.hasValue(asmsg.effThruDt_A1.getValue()) && (asmsg.effThruDt_A1.getValue()).compareTo(salesDate) < 0)) {
                    asmsg.xxChkBox_AP.setErrorInfo(1, NMAM8363E);
                    rtnFlg = false;
                }
            }

            // Add Start 2019/05/23 QC#50101
            for (int i = 0; i < sharedMsg.A.getValidCount(); i++) {
                NMAL6700_ASMsg asmsg = (NMAL6700_ASMsg) sharedMsg.A.get(i);
                if (ZYPConstant.FLG_ON_Y.equals(asmsg.xxChkBox_AP.getValue()) && (asmsg.effFromDt_A1.getValue()).compareTo(salesDate) > 0) {
                    S21SsmEZDResult result = NMAL6700Query.getInstance().getCmpyPk(glblCmpyCd, asmsg.locNum_A1.getValue());

                    if (result.isCodeNotFound()) {
                        continue;
                    }
                    if (!result.isCodeNormal()) {
                        continue;
                    }

                    BigDecimal cmpyPk = (BigDecimal) result.getResultObject();
                    if (bizMsg.cmpyPk_H1.getValue().compareTo(cmpyPk) != 0) {
                        asmsg.xxChkBox_AP.setErrorInfo(1, NMAM8701E);
                        rtnFlg = false;
                    }
                }
            }
            // Add End 2019/05/23 QC#50101
        }
        return rtnFlg;
    }

    private boolean inputCheckForRelnships(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        boolean normalEndFlg = true;
        // 2018/06/13 QC#25457 add start
        S21LRUMap<String, Map<String, Integer>> cache = new S21LRUMap<String, Map<String, Integer>>();
        // 2018/06/13 QC#25457 add end

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        // 2018/06/13 QC#25457 add start
        String preSlsDt = ZYPDateUtil.addDays(slsDt, -1);
        // 2018/06/13 QC#25457 add end

        for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
            NMAL6700_CSMsg csMsg = sharedMsg.C.no(i);
            if (bizMsg.dsAcctNum_H1.getValue().equals(csMsg.dsAcctNum_C1.getValue())) {
                csMsg.dsAcctNum_C1.setErrorInfo(1, NMAM0294E);
                normalEndFlg = false;
            }
            // Mod Start 2018/03/16 QC#20357-1
            //String dsAcctNum = NMAL6700CommonLogic.getDsAcctNm(sharedMsg.C.no(bizMsg.xxCellIdx.getValueInt()).dsAcctNum_C1.getValue(), getGlobalCompanyCode());
            String dsAcctNum = NMAL6700CommonLogic.getDsAcctNm(csMsg.dsAcctNum_C1.getValue(), getGlobalCompanyCode());
            // Mod End 2018/03/16 QC#20357-1
            if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
                csMsg.dsAcctNum_C1.setErrorInfo(1, NMAM8121E, new String[] {"Account#" });
                normalEndFlg = false;
            }

        }

        for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
            NMAL6700_CSMsg csMsg = sharedMsg.C.no(i);

            String effThruDt = "";
            if (ZYPCommonFunc.hasValue(csMsg.effThruDt_C1)) {
                effThruDt = csMsg.effThruDt_C1.getValue();
            } else {
                effThruDt = MAX_EFF_THRU_DT;
            }
            // Date Check
            // 2018/06/13 QC#25457 mod start
            if (dateCompare(csMsg.effFromDt_C1.getValue(), effThruDt, cache) > 0) {
             // 2018/06/13 QC#25457 mod end
                csMsg.effFromDt_C1.setErrorInfo(1, NMAM8115E);
                csMsg.effThruDt_C1.setErrorInfo(1, NMAM8115E);
                normalEndFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(csMsg.dsAcctRelnPk_C1)) {
                // Effective From Date
                if (ZYPCommonFunc.hasValue(csMsg.effFromDt_C1.getValue())) {
                    // 2018/06/13 QC#25457 mod start
                    if (dateCompare(csMsg.effFromDt_C1.getValue(), slsDt, cache) < 0) {
                    // 2018/06/13 QC#25457 mod end
                        csMsg.effFromDt_C1.setErrorInfo(1, NMAM8250E);
                        normalEndFlg = false;
                    }
                }
                // Effective Through Date
                // 2018/06/13 QC#25457 del start
                //String preSlsDt = ZYPDateUtil.addDays(slsDt, -1);
                // 2018/06/13 QC#25457 del end
                if (ZYPCommonFunc.hasValue(effThruDt)) {

                    // 2018/06/13 QC#25457 mod start
                    if (dateCompare(effThruDt, preSlsDt, cache) < 0) {
                    // 2018/06/13 QC#25457 mod end
                        csMsg.effThruDt_C1.setErrorInfo(1, NMAM8251E);
                        normalEndFlg = false;
                    }
                }
            } else {
                // Check effective through date when the value is changed.
                if (ZYPCommonFunc.hasValue(csMsg.effThruDt_C1)) {
                    DS_ACCT_RELNTMsg tMsg = new DS_ACCT_RELNTMsg();
                    tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                    tMsg.dsAcctRelnPk.setValue(csMsg.dsAcctRelnPk_C1.getValue());
                    tMsg = (DS_ACCT_RELNTMsg) S21FastTBLAccessor.findByKey(tMsg);
                    if (tMsg != null) {
                        if (!tMsg.effThruDt.getValue().equals(csMsg.effThruDt_C1.getValue())) {
                            //String preSlsDt = ZYPDateUtil.addDays(slsDt, -1);
                            // 2018/06/13 QC#25457 mod start
                            if (dateCompare(csMsg.effThruDt_C1.getValue(), preSlsDt, cache) < 0) {
                             // 2018/06/13 QC#25457 mod end
                                csMsg.effThruDt_C1.setErrorInfo(1, NMAM8251E);
                                normalEndFlg = false;
                            }
                        }
                    }
                }
            }

            // START 2022/07/21 [QC#60111, MOD]
            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_CY.getValue())) {
                // 2018/06/13 QC#25457 mod start
                if (dateCompare(effThruDt, slsDt, cache) >= 0 && !NMAL6700CommonLogic.chkActiveAccount(csMsg.dsAcctNum_C1.getValue(), glblCmpyCd)) {
                // 2018/06/13 QC#25457 mod end
                    csMsg.xxChkBox_C1.setErrorInfo(1, NMAM8362E);
                    normalEndFlg = false;
                }
            }
            // END   2022/07/21 [QC#60111, MOD]

            // Check Reciprocal
            if (DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
                    // 2018/06/13 QC#25457 mod start
                    if (dateCompare(csMsg.effThruDt_C1.getValue(), slsDt, cache) < 0) {
                     // 2018/06/13 QC#25457 mod end
                        csMsg.xxChkBox_CR.setErrorInfo(1, NMAM8252E);
                        normalEndFlg = false;
                    }
                }
            // 2018/10/02 QC#28165 add start
            } else if (DS_ACCT_RELN_TP.LEASE_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
                    csMsg.xxChkBox_CR.setErrorInfo(1, NMAM8690E);
                    csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM8690E);
                    normalEndFlg = false;
                }
            } else if (DS_ACCT_RELN_TP.MYCSA_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
                    csMsg.xxChkBox_CR.setErrorInfo(1, NMAM8691E);
                    csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM8691E);
                    normalEndFlg = false;
                }
            }
            // 2018/10/02 QC#28165 add end

            // Duplicate Check
            String fromDt1 = csMsg.effFromDt_C1.getValue();
            String thruDt1 = effThruDt;

            boolean effDup = false;
            for (int j = 0; j < sharedMsg.C.getValidCount(); j++) {
                effDup = false;
                NMAL6700_CSMsg csMsg2 = sharedMsg.C.no(j);
                String fromDt2 = csMsg2.effFromDt_C1.getValue();
                String thruDt2 = csMsg2.effThruDt_C1.getValue();
                if (thruDt2.isEmpty()) {
                    thruDt2 = MAX_EFF_THRU_DT;
                }

                // 2018/06/13 QC#25457 mod start
                if (i != j && 0 <= dateCompare(fromDt1, fromDt2, cache) && 0 >= dateCompare(fromDt1, thruDt2, cache)) {
                    effDup = true;
                } else if (i != j && 0 <= dateCompare(thruDt1, fromDt2, cache) && 0 >= dateCompare(thruDt1, thruDt2, cache)) {
                    effDup = true;
                }
                // 2018/06/13 QC#25457 mod end

                if (i != j && effDup && csMsg.dsAcctNum_C1.getValue().equals(csMsg2.dsAcctNum_C1.getValue()) && csMsg.dsAcctRelnTpCd_C3.getValue().equals(csMsg2.dsAcctRelnTpCd_C3.getValue())) {
                    String[] msg = new String[] {MSG_DUP };
                    csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM0072E, msg);
                    csMsg.effFromDt_C1.setErrorInfo(1, NMAM0072E, msg);
                    csMsg.effThruDt_C1.setErrorInfo(1, NMAM0072E, msg);
                    csMsg2.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM0072E, msg);
                    csMsg2.effFromDt_C1.setErrorInfo(1, NMAM0072E, msg);
                    csMsg2.effThruDt_C1.setErrorInfo(1, NMAM0072E, msg);
                    normalEndFlg = false;
                }

                if (i != j
                        && DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())
                        && DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(csMsg2.dsAcctRelnTpCd_C3.getValue())) {
                    if (effDup) {
                        String[] msg = new String[] {MSG_DUP };
                        csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM0072E, msg);
                        csMsg.effFromDt_C1.setErrorInfo(1, NMAM0072E, msg);
                        csMsg.effThruDt_C1.setErrorInfo(1, NMAM0072E, msg);
                        csMsg2.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM0072E, msg);
                        csMsg2.effFromDt_C1.setErrorInfo(1, NMAM0072E, msg);
                        csMsg2.effThruDt_C1.setErrorInfo(1, NMAM0072E, msg);
                        normalEndFlg = false;
                    }
                }
            }
        }

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1)) {
            if (!inputCheckForRelnshipsDuplicateInDB(bizMsg, sharedMsg, glblCmpyCd)) {
                normalEndFlg = false;
            }
        }

        // Get Related Account for Related BillTo/ShipTo
        // Mod Start 2019/08/07 QC#52385
        //NMZC610001PMsg custInfoGetApiPMsg = NMAL6700CommonLogic.callCustomerInfomationGetApi(bizMsg, glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        List<Map<String, Object>> relCustInfoDefBillShip = //
            NMAL6700CommonLogic.getRelatedCustomerInfoForDefaultBillShip(bizMsg, glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        // Mod End 2019/08/07 QC#52385

        // Del Start 2019/08/07 QC#52385
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                bizMsg.setMessageInfo(msgId, msgPrms);
//
//                if (msgId.endsWith("E")) {
//                    return false;
//                }
//            }
//        }
        // Del End 2019/08/07 QC#52385

        for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
            NMAL6700_CSMsg csMsg = sharedMsg.C.no(i);
            if (DS_ACCT_RELN_TP.LEASE_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().countLeaseCmpy(csMsg.dsAcctNum_C1.getValue(), glblCmpyCd);
                BigDecimal count = BigDecimal.ZERO;
                if (!ssmResult.isCodeNotFound()) {
                    count = (BigDecimal) ssmResult.getResultObject();
                }

                if (BigDecimal.ZERO.compareTo(count) == 0) {
                    String[] args = {csMsg.dsAcctNum_C1.getValue()};
                    csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM8254E, args);
                    normalEndFlg = false;
                }
            }
            if (DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())
                    && (ZYPConstant.CHKBOX_ON_Y.equals(csMsg.xxChkBox_CR.getValue()))) {
                String[] args = {"ACTIVE " + ZYPCodeDataUtil.getName(DS_ACCT_RELN_TP.class, glblCmpyCd, DS_ACCT_RELN_TP.PARENT_ACCOUNT) };
                csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM0072E, args);
                normalEndFlg = false;
            }
        }

        for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
            NMAL6700_CSMsg chkCsMsg = sharedMsg.C.no(i);
            String chkEffFromDt = chkCsMsg.effFromDt_C1.getValue();
            String chkEffThruDt = getEffThruDt(chkCsMsg.effThruDt_C1.getValue());
            for (int j = 0; j < sharedMsg.C.getValidCount(); j++) {
                NMAL6700_CSMsg csMsg  = sharedMsg.C.no(j);
                if (i != j) {
                    if (chkCsMsg.dsAcctRelnTpCd_C3.getValue().equals(csMsg.dsAcctRelnTpCd_C3.getValue())
                            && chkCsMsg.dsAcctNum_C1.getValue().equals(csMsg.dsAcctNum_C1.getValue())) {

                        String effFromDt = csMsg.effFromDt_C1.getValue();
                        String effThruDt = getEffThruDt(csMsg.effThruDt_C1.getValue());

                        if (dateCompare(effFromDt, chkEffThruDt, cache) <= 0
                                && dateCompare(chkEffFromDt, effThruDt, cache) <= 0) {
                            normalEndFlg = false;
                            chkCsMsg.effFromDt_C1.setErrorInfo(1, NMAM8343E);
                            chkCsMsg.effThruDt_C1.setErrorInfo(1, NMAM8343E);
                            csMsg.effFromDt_C1.setErrorInfo(1, NMAM8343E);
                            csMsg.effThruDt_C1.setErrorInfo(1, NMAM8343E);
                        }
                    }
                }
            }
        }

        // Add Start 2017/12/11 QC#20357
        // Mod Start 2019/08/07 QC#52385
        //if(!NMAL6700CommonLogic.checkRelationAndDafaultBillShip(bizMsg, sharedMsg, custInfoGetApiPMsg)){
        if (!NMAL6700CommonLogic.checkRelationAndDafaultBillShip(bizMsg, sharedMsg, relCustInfoDefBillShip)) {
            // Mod End 2019/08/07 QC#52385
            normalEndFlg = false;
        }
        // Add End 2017/12/11 QC#20357

        // check nest relation.
        if (normalEndFlg) {
            if (!NMAL6700CommonLogic.chkNestRelation(sharedMsg.C, bizMsg.dsAcctNum_H1.getValue(), glblCmpyCd)) {
                normalEndFlg = false;
            }
        }
        return normalEndFlg;
    }

    // 2018/06/13 QC#25457 add start
    private int dateCompare(String yyyyMMdd1, String yyyyMMdd2, S21LRUMap<String, Map<String, Integer>> cache){
        Map<String, Integer> map = null;

        if (cache.contains(yyyyMMdd1)){
            map = cache.get(yyyyMMdd1);

            if (map.containsKey(yyyyMMdd2)){
                return map.get(yyyyMMdd2);
            }
        } else {
            map = new HashMap<String, Integer>();
            cache.put(yyyyMMdd1, map);
        }

        int ret = ZYPDateUtil.compare(yyyyMMdd1, yyyyMMdd2);

        map.put(yyyyMMdd2, ret);

        return ret;
    }
    // 2018/06/13 QC#25457 add end

    private boolean inputCheckForRelnshipsDuplicateInDB(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        boolean isError = false;
        S21SsmEZDResult res = NMAL6700Query.getInstance().getAllRelnshipList(glblCmpyCd, bizMsg, sharedMsg);
        if (res.isCodeNormal()) {
            List<Map<String, Object>> listMap = (List<Map<String, Object>>) res.getResultObject();

            // get records not in screen
            List<Map<String, Object>> listMapNoInScreen = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : listMap) {
                BigDecimal dsAcctRelnPk = (BigDecimal) map.get("DS_ACCT_RELN_PK");
                if (!isRelationInInitSearch(sharedMsg, dsAcctRelnPk)) {
                    listMapNoInScreen.add(map);
                }
            }

            // check screen duplicate with above list
            for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
                NMAL6700_CSMsg csMsg = sharedMsg.C.no(i);

                boolean isLineError = false;
                for (Map<String, Object> map : listMapNoInScreen) {
                    String fromDt = (String) map.get("EFF_FROM_DT");
                    String thruDt = (String) map.get("EFF_THRU_DT");
                    String dsAcctRelnTpCd = (String) map.get("DS_ACCT_RELN_TP_CD");
                    String relnDsAcctNum = (String) map.get("RELN_DS_ACCT_NUM");

                    if (isPeriodDuplicate(csMsg.effFromDt_C1.getValue(), csMsg.effThruDt_C1.getValue(), fromDt, thruDt)) {
                        if (csMsg.dsAcctRelnTpCd_C3.getValue().equals(dsAcctRelnTpCd)) {
                            if (DS_ACCT_RELN_TP.PARENT_ACCOUNT.equals(dsAcctRelnTpCd)) {
                                isLineError = true;
                                break;
                            } else {
                                if (csMsg.dsAcctNum_C1.getValue().equals(relnDsAcctNum)) {
                                    isLineError = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (isLineError) {
                    String[] msg = new String[] {"Duplicated Effective Date", "Database"};
                    csMsg.dsAcctRelnTpCd_C3.setErrorInfo(1, NMAM0834E, msg);
                    csMsg.effFromDt_C1.setErrorInfo(1, NMAM0834E, msg);
                    csMsg.effThruDt_C1.setErrorInfo(1, NMAM0834E, msg);
                    isError = true;
                }
            }
        }
        if (isError) {
            return false;
        }
        return true;
    }

    private boolean isPeriodDuplicate(String fromDt1, String thruDt1, String fromDt2, String thruDt2) {
        if (!ZYPCommonFunc.hasValue(thruDt1)) {
            thruDt1 = MAX_EFF_THRU_DT;
        }
        if (!ZYPCommonFunc.hasValue(thruDt2)) {
            thruDt2 = MAX_EFF_THRU_DT;
        }
        if (isDateInPeriod(fromDt1, fromDt2, thruDt2)) {
            return true;
        }
        if (isDateInPeriod(thruDt1, fromDt2, thruDt2)) {
            return true;
        }
        if (isDateInPeriod(fromDt2, fromDt1, thruDt1)) {
            return true;
        }
        return false;
    }

    private boolean isDateInPeriod(String targetDate, String fromDt, String thruDt) {
        if (targetDate.compareTo(fromDt) >= 0 && targetDate.compareTo(thruDt) <= 0) {
            return true;
        }
        return false;
    }

    private boolean isRelationInInitSearch(NMAL6700SMsg sharedMsg, BigDecimal dsAcctRelnPk) {
        // records still in screen 
        for (int i = 0; i < sharedMsg.C.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sharedMsg.C.no(i).dsAcctRelnPk_C1) && sharedMsg.C.no(i).dsAcctRelnPk_C1.getValue().compareTo(dsAcctRelnPk) == 0) {
                return true;
            }
        }
        // records deleted from screen
        for (int i = 0; i < sharedMsg.O.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sharedMsg.O.no(i).dsAcctRelnPk_O1) && sharedMsg.O.no(i).dsAcctRelnPk_O1.getValue().compareTo(dsAcctRelnPk) == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean inputCheckForContacts(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        boolean normalEndFlg = true;
        int cnt = sharedMsg.D.getValidCount();
        // Duplicate Check
        List<BigDecimal> dsCtacPsnRelnPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cnt; i++) {
            if (!sharedMsg.D.no(i).dsPrimLocFlg_D1.getValue().equals(sharedMsg.D.no(i).dsPrimLocFlg_DB.getValue())) {// 2017/08/04 S21_NA#8598 Add
                dsCtacPsnRelnPkList.add(sharedMsg.D.no(i).dsCtacPsnRelnPk_D1.getValue()); // 2017/08/04 S21_NA#8598 Add
                if (ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.D.no(i).dsPrimLocFlg_D1.getValue())) {
                    String chkEffFromDt = sharedMsg.D.no(i).effFromDt_D1.getValue();
                    String chkEffThruDt = "";
                    if (ZYPCommonFunc.hasValue(sharedMsg.D.no(i).effThruDt_D1)) {
                        chkEffThruDt = sharedMsg.D.no(i).effThruDt_D1.getValue();
                    } else {
                        chkEffThruDt = MAX_EFF_THRU_DT;
                    }

                    for (int j = 0; j < cnt; j++) {
                        if (ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.D.no(j).dsPrimLocFlg_D1.getValue())
                                && sharedMsg.D.no(i).ctacPsnPk_D1.getValue().compareTo(sharedMsg.D.no(j).ctacPsnPk_D1.getValue()) != 0
                                // 2017/08/04 S21_NA#8598 Add
                                && sharedMsg.D.no(i).ctacTpCd_D1.getValue().equals(sharedMsg.D.no(j).ctacTpCd_D1.getValue())) {
                            String effFromDt = sharedMsg.D.no(j).effFromDt_D1.getValue();
                            String effThruDt = "";
                            if (ZYPCommonFunc.hasValue(sharedMsg.D.no(j).effThruDt_D1)) {
                                effThruDt = sharedMsg.D.no(j).effThruDt_D1.getValue();
                            } else {
                                effThruDt = MAX_EFF_THRU_DT;
                            }
                            if (ZYPDateUtil.compare(effFromDt, chkEffFromDt) <= 0
                                    && ZYPDateUtil.compare(effThruDt, chkEffFromDt) >= 0) {
                                normalEndFlg = false;
                                sharedMsg.D.no(i).dsPrimLocFlg_D1.setErrorInfo(1, NMAM8343E);
                            }
                            if (ZYPDateUtil.compare(effFromDt, chkEffThruDt) <= 0
                                    && ZYPDateUtil.compare(effThruDt, chkEffThruDt) >= 0) {
                                normalEndFlg = false;
                                sharedMsg.D.no(j).dsPrimLocFlg_D1.setErrorInfo(1, NMAM8343E);
                            }
                        }
                    }
                }
            }
        }
        // Duplicate Check for DB.
        if (normalEndFlg) {
            for (int i = 0; i < cnt; i++) {
                NMAL6700_DSMsg dsMsg = sharedMsg.D.no(i);
                if (!dsMsg.dsPrimLocFlg_D1.getValue().equals(dsMsg.dsPrimLocFlg_DB.getValue())) {
                    if (ZYPConstant.CHKBOX_ON_Y.equals(dsMsg.dsPrimLocFlg_D1.getValue())) {
                        String chkEffFromDt = sharedMsg.D.no(i).effFromDt_D1.getValue();
                        String chkEffThruDt = "";
                        if (ZYPCommonFunc.hasValue(sharedMsg.D.no(i).effThruDt_D1)) {
                            chkEffThruDt = sharedMsg.D.no(i).effThruDt_D1.getValue();
                        } else {
                            chkEffThruDt = MAX_EFF_THRU_DT;
                        }
                        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getPrimaryContactForDuplicateCheck(bizMsg.dsAcctNum_H1.getValue(), dsMsg.ctacPsnPk_D1.getValue()
                                , dsMsg.ctacTpCd_D1.getValue(), glblCmpyCd, chkEffFromDt, chkEffThruDt);
                        if (!ssmResult.isCodeNormal()) {
                            continue;
                        }
                        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                        for (Map<String, Object> resultMap : resultList) {
                            if (!dsCtacPsnRelnPkList.contains(resultMap.get("DS_CTAC_PSN_RELN_PK"))) {
                                normalEndFlg = false;
                                dsMsg.dsPrimLocFlg_D1.setErrorInfo(1, NMAM8343E);
                            }
                        }
                        
                    }
                }
            }
        }

        return normalEndFlg;
    }
    private boolean inputCheckForCollections(NMAL6700CMsg bizMsg) {
        boolean rtnFlg = true;
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.lateFeeFlg_U1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.lateFeeAmt_U1)) {
                String[] args = {"Minimum Balance to Calculate Late Fee" };
                bizMsg.lateFeeAmt_U1.setErrorInfo(1, NMAM0836E, args);
                rtnFlg = false;
            }
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.arStmtFlg_U1.getValue())) {
            if (!ZYPCommonFunc.hasValue(bizMsg.arStmtIssCycleCd_U3)) {
                String[] args = {"Statements Issue Day" };
                bizMsg.arStmtIssCycleCd_U3.setErrorInfo(1, NMAM0836E, args);
                rtnFlg = false;
            }
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.custHardHldFlg_U1.getValue())) {
            if (!CR_CHK_REQ_TP.HOLD.equals(bizMsg.crChkReqTpCd_U3.getValue())) {
                bizMsg.crChkReqTpCd_U3.setErrorInfo(1, NMAM8638E);
                rtnFlg = false;
            }
        }
        return rtnFlg;
    }
    private boolean masterCheckForCollections(NMAL6700CMsg bizMsg, String glblCmpyCd) {

        boolean rtnFlg = true;

        if (!NMAL6700CommonLogic.getCltCustTpNm(glblCmpyCd, bizMsg)) {
            rtnFlg = false;
        }

        if (!NMAL6700CommonLogic.getCltPtfoNm(bizMsg)) {
            rtnFlg = false;
        }

        return rtnFlg;
    }

    private boolean inputCheckForTransactions(NMAL6700CMsg bizMsg) {
        boolean rtnFlg = true;
        String glblCmpyCd = getGlobalCompanyCode();
        int cnt = bizMsg.F.getValidCount();
        Map<String, NMAL6700_FCMsg> map = new HashMap<String, NMAL6700_FCMsg>();

        // Get Related Account for Related BillTo/ShipTo
        // Mod Start 2019/08/07 QC#52385
        //NMZC610001PMsg custInfoGetApiPMsg = NMAL6700CommonLogic.callCustomerInfomationGetApi(bizMsg, glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        List<Map<String, Object>> relCustInfoDefBillShip = //
            NMAL6700CommonLogic.getRelatedCustomerInfoForDefaultBillShip(bizMsg, glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        // Mod End 2019/08/07 QC#52385

        // Del Start 2019/08/07 QC#52385
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                bizMsg.setMessageInfo(msgId, msgPrms);
//
//                if (msgId.endsWith("E")) {
//                    return false;
//                }
//            }
//        }
        // Del End 2019/08/07 QC#52385
        for (int i = 0; i < cnt; i++) {
            // Default Bill To Check
            if (ZYPCommonFunc.hasValue(bizMsg.F.no(i).dsDefBillToCd_F1)) {
                BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
                billToCustTMsg.setSQLID("003");
                billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                billToCustTMsg.setConditionValue("billToCustCd01", bizMsg.F.no(i).dsDefBillToCd_F1.getValue());

                BILL_TO_CUSTTMsgArray billTmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustTMsg);
                if (billTmsgArray.length() == 0) {
                    bizMsg.F.no(i).dsDefBillToCd_F1.setErrorInfo(1, NMAM8111E, new String[] {"BILL_TO_CUST" });
                    rtnFlg = false;
                }
            }

            // Default Ship To Check
            if (ZYPCommonFunc.hasValue(bizMsg.F.no(i).dsDefShipToCd_F1)) {
                SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
                shipToCustTMsg.setSQLID("004");
                shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                shipToCustTMsg.setConditionValue("shipToCustCd01", bizMsg.F.no(i).dsDefShipToCd_F1.getValue());

                SHIP_TO_CUSTTMsgArray shipTmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
                if (shipTmsgArray.length() == 0) {
                    bizMsg.F.no(i).dsDefShipToCd_F1.setErrorInfo(1, NMAM8111E, new String[] {"SHIP_TO_CUST" });
                    rtnFlg = false;
                }
            }

            NMAL6700_FCMsg fcMsg = bizMsg.F.no(i);
            String dsTrxRuleTpCd = fcMsg.dsTrxRuleTpCd_F3.getValue();
            if (map.containsKey(dsTrxRuleTpCd)) {
                String[] args = {"Transaction Type" };
                fcMsg.dsTrxRuleTpCd_F3.setErrorInfo(1, NMAM0072E, args);
                rtnFlg = false;
            } else {
                map.put(dsTrxRuleTpCd, fcMsg);
            }
            // Check Related BillTo/ShipTo
            if (ZYPCommonFunc.hasValue(bizMsg.F.no(i).dsDefBillToCd_F1)) {
                boolean chkDsDefBillToFlg = false;

                // Mod Start 2019/08/07 QC#52385
                //for (int j = 0; j < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); j++) {
                //    NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(j);
                //    if (inPmsg.billToCustCd.getValue().equals(bizMsg.F.no(i).dsDefBillToCd_F1.getValue())) {
                for (int j = 0; j < relCustInfoDefBillShip.size(); j++) {
                    Map<String, Object> result = relCustInfoDefBillShip.get(j);
                    String billToCustCd = (String) result.get("R_BILL_TO_CUST_CD");
                    if (bizMsg.F.no(i).dsDefBillToCd_F1.getValue().equals(billToCustCd)) {
                        // Mod End 2019/08/07 QC#52385
                        chkDsDefBillToFlg = true;
                    }
                }
                if (!chkDsDefBillToFlg) {
                    bizMsg.F.no(i).dsDefBillToCd_F1.setErrorInfo(1, NMAM8111E, new String[] {"BILL_TO_CUST" });
                    rtnFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(bizMsg.F.no(i).dsDefShipToCd_F1)) {
                boolean chkDsDefShipToFlg = false;

                // Mod Start 2019/08/07 QC#52385
                //for (int j = 0; j < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); j++) {
                //    NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(j);
                //    if (inPmsg.shipToCustCd.getValue().equals(bizMsg.F.no(i).dsDefShipToCd_F1.getValue())) {
                for (int j = 0; j < relCustInfoDefBillShip.size(); j++) {
                    Map<String, Object> result = relCustInfoDefBillShip.get(j);
                    String shipToCustCd = (String) result.get("R_SHIP_TO_CUST_CD");
                    if (bizMsg.F.no(i).dsDefShipToCd_F1.getValue().equals(shipToCustCd)) {
                        // Mod End 2019/08/07 QC#52385
                        chkDsDefShipToFlg = true;
                    }
                }
                if (!chkDsDefShipToFlg) {
                    bizMsg.F.no(i).dsDefShipToCd_F1.setErrorInfo(1, NMAM8111E, new String[] {"SHIP_TO_CUST" });
                    rtnFlg = false;
                }
            }
        }
        return rtnFlg;
    }

    private boolean masterCheckForTransactions(NMAL6700CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.coaCcCd_F1)) {
            COA_CCTMsg coaCcTMsg = new COA_CCTMsg();
            ZYPEZDItemValueSetter.setValue(coaCcTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(coaCcTMsg.coaCcCd, bizMsg.coaCcCd_F1.getValue());
            coaCcTMsg = (COA_CCTMsg) EZDTBLAccessor.findByKey(coaCcTMsg);
            if (coaCcTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.coaCcNm_F1, coaCcTMsg.coaCcNm);
            } else {
                bizMsg.coaCcCd_F1.setErrorInfo(1, NMAM8111E, new String[] {"DEPT#" });
                return false;
            }
        }

        return true;
    }

    private boolean inputCheckForMarketing(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        boolean rtnFlg = true;

        for (int i = 0; i < sharedMsg.E.getValidCount(); i++) {
            NMAL6700_ESMsg esmsg = (NMAL6700_ESMsg) sharedMsg.E.get(i);
            String targetDsBizAreaCd = esmsg.dsBizAreaCd_E3.getValue();
            String targetDsAcctGrpCd = esmsg.dsAcctGrpCd_E3.getValue();
            String targetEffThruDt = "";
            if (ZYPCommonFunc.hasValue(esmsg.effThruDt_E1)) {
                targetEffThruDt = esmsg.effThruDt_E1.getValue();
            } else {
                targetEffThruDt = MAX_EFF_THRU_DT;
            }
            for (int j = i; j < sharedMsg.E.getValidCount(); j++) {
                NMAL6700_ESMsg otherEsmsg = (NMAL6700_ESMsg) sharedMsg.E.get(j);
                if (i == j) {
                    continue;
                }
                String otherDsBizAreaCd = otherEsmsg.dsBizAreaCd_E3.getValue();
                String otherDsAcctGrpCd = otherEsmsg.dsAcctGrpCd_E3.getValue();
                String otherEffThruDt = "";
                if (ZYPCommonFunc.hasValue(otherEsmsg.effThruDt_E1)) {
                    otherEffThruDt = otherEsmsg.effThruDt_E1.getValue();
                } else {
                    otherEffThruDt = MAX_EFF_THRU_DT;
                }

                if (targetDsBizAreaCd.equals(otherDsBizAreaCd) && targetDsAcctGrpCd.equals(otherDsAcctGrpCd)) {
                    if (!((esmsg.effFromDt_E1.getValue().compareTo(otherEsmsg.effFromDt_E1.getValue()) > 0 && targetEffThruDt.compareTo(otherEffThruDt) > 0) || (targetEffThruDt.compareTo(
                            otherEsmsg.effFromDt_E1.getValue()) < 0 && targetEffThruDt.compareTo(otherEffThruDt) < 0))) {
                        String[] args = {DUP_MSG_BIZ_AREA_AND_GRP_NM};
                        ((NMAL6700_ESMsg) sharedMsg.E.get(i)).effFromDt_E1.setErrorInfo(1, NMAM0072E, args);
                        ((NMAL6700_ESMsg) sharedMsg.E.get(i)).effThruDt_E1.setErrorInfo(1, NMAM0072E, args);
                        ((NMAL6700_ESMsg) sharedMsg.E.get(j)).effFromDt_E1.setErrorInfo(1, NMAM0072E, args);
                        ((NMAL6700_ESMsg) sharedMsg.E.get(j)).effThruDt_E1.setErrorInfo(1, NMAM0072E, args);

                        rtnFlg = false;
                    }
                }
            }
        }

        return rtnFlg;
    }
    /**
     * masterCheckForMarketing
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     * @return boolean
     */
    private boolean masterCheckForMarketing(NMAL6700CMsg  bizMsg, NMAL6700SMsg sharedMsg) {

        boolean rtnFlg = true;

        for (int i = 0; i < sharedMsg.E.getValidCount(); i++) {
            NMAL6700_ESMsg esmsg = (NMAL6700_ESMsg) sharedMsg.E.get(i);
            if (!isExistGroupCode(esmsg.dsAcctGrpCd_E3.getValue())) {
                String[] args = {"Group Code" };
                esmsg.dsAcctGrpCd_E3.setErrorInfo(1, NMAM8111E, args);
                rtnFlg = false;
            }
        }
        SVC_ACCS_PMIT_VALTMsg svcAccsPmitValTMsg = new SVC_ACCS_PMIT_VALTMsg();
        SVC_ACCS_PMIT_VALTMsgArray svcAccsPmitValTMsgArray;
        svcAccsPmitValTMsg.setSQLID("001");
        svcAccsPmitValTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        for (int i = 0; i < bizMsg.N.getValidCount(); i++) {
            svcAccsPmitValTMsg.setConditionValue("svcAccsPmitNum01", bizMsg.N.no(i).svcAccsPmitNum_N1.getValue());

            svcAccsPmitValTMsgArray = (SVC_ACCS_PMIT_VALTMsgArray) EZDTBLAccessor.findByCondition(svcAccsPmitValTMsg);
            if (svcAccsPmitValTMsgArray.length() == 0) {
                bizMsg.N.no(i).svcAccsPmitNum_N1.setErrorInfo(1, NMAM8111E, new String[] {"Access Permit No" });
                rtnFlg = false;
            }
        }

        return rtnFlg;
    }
    private boolean inputCheckForBankAcct(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        boolean rtnFlg = true;
        // 2018/12/10 QC#29315 Del Start
//        if (bizMsg.W.getValidCount() > 0) {
//            int checkedCnt = 0;
//            for (int i = 0; i < sharedMsg.W.getValidCount(); i++) {
//                NMAL6700_WCMsg wcMsg = bizMsg.W.no(i);
//                if ((ZYPCommonFunc.hasValue(wcMsg.xxChkBox_WD) && ZYPConstant.FLG_ON_Y.equals(wcMsg.xxChkBox_WD.getValue()))) {
//                    checkedCnt++;
//                }
//            }
//            if (checkedCnt != 1) {
//                bizMsg.setMessageInfo(NMAM8287E, new String[]{"Default"});
//                rtnFlg = false;
//            }
//        }
        for (int i = 0; i < sharedMsg.I.getValidCount(); i++) {
            NMAL6700_ISMsg isMsg = sharedMsg.I.no(i);
            String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

            slsDt = ZYPDateUtil.addDays(slsDt, -1);
            if (ZYPCommonFunc.hasValue(isMsg.effThruDt_I1.getValue())) {

                // START 2020/12/14 J.Evangelista [QC#57937,ADD]
                if (ZYPCommonFunc.hasValue(isMsg.dsBankAcctPk_I1)) {
                    DS_BANK_ACCTTMsg beforeDsBankAcctTMsg = NMAL6700CommonLogic.findDsBankAcctForUpdate(getGlobalCompanyCode(), isMsg.dsBankAcctPk_I1.getValue());
                    if (beforeDsBankAcctTMsg != null) {
                        DS_BANK_ACCTTMsg dsBankAcctTMsg = new DS_BANK_ACCTTMsg();
                        DS_BANK_ACCTTMsg.copy(beforeDsBankAcctTMsg, null, dsBankAcctTMsg, null);
                        ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.effThruDt, isMsg.effThruDt_I1.getValue());
                        if (!changeFieldsCheckForDsBankAcct(dsBankAcctTMsg, beforeDsBankAcctTMsg)) {
                            continue; // No change in effThruDt, skip check.
                        }
                    }
                }
                // END   2020/12/14 J.Evangelista [QC#57937,ADD]

                if (isMsg.effFromDt_I1.getValue().compareTo(isMsg.effThruDt_I1.getValue()) > 0) {
                    isMsg.effThruDt_I1.setErrorInfo(1, NMAM8115E);
                    rtnFlg = false;
                }

                if (ZYPDateUtil.compare(isMsg.effThruDt_I1.getValue(), slsDt) < 0) {
                    isMsg.effThruDt_I1.setErrorInfo(1, NMAM8251E);
                    rtnFlg = false;
                }
            }
        }
        // 2018/12/10 QC#29315 Del End
        // 2018/12/10 QC#29315 Del Start
//
//        for (int i = 0; i < bizMsg.W.getValidCount(); i++) {
//            NMAL6700_WCMsg wcMsg = bizMsg.W.no(i);
//            String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
//
//            slsDt = ZYPDateUtil.addDays(slsDt, -1);
//            if (ZYPCommonFunc.hasValue(wcMsg.effThruDt_W1.getValue()) && !ZYPCommonFunc.hasValue(wcMsg.dsAcctCarrPk_W1.getValue())) {
//
//                if (ZYPDateUtil.compare(wcMsg.effThruDt_W1.getValue(), slsDt) < 0) {
//                    wcMsg.effThruDt_W1.setErrorInfo(1, NMAM8251E);
//                    rtnFlg = false;
//                }
//            }
//        }
//
//        List<Integer> checkList = ZYPTableUtil.getSelectedRows(sharedMsg.W, CHKBOX_WD, ZYPConstant.CHKBOX_ON_Y);
//        if (checkList.size() <= 0) {
//            for (int i = 0; i < sharedMsg.W.getValidCount(); i++) {
//                sharedMsg.W.no(i).xxChkBox_WD.setErrorInfo(1, NMAM8372E);
//                rtnFlg = false;
//            }
//        } else if (checkList.size() > 1) {
//            for (int index : checkList) {
//                sharedMsg.W.no(index).xxChkBox_WD.setErrorInfo(1, NMAM8195E);
//                rtnFlg = false;
//            }
//        }
//
        // 2018/12/10 QC#29315 Del End
        return rtnFlg;
    }
    private boolean inputCheckForMsgNote(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        boolean rtnFlg = true;
        int cnt = sharedMsg.J.getValidCount();
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        for (int i = 0; i < cnt; i++) {
            NMAL6700_JSMsg jsMsg = sharedMsg.J.no(i);
            // QC#18961
            if (ZYPCommonFunc.hasValue(jsMsg.dsCustSpclMsgPk_J1)) {
                DS_CUST_SPCL_MSGTMsg beforeDsCustSpclMsgTMsg // no lock
                = NMAL6700CommonLogic.findDsCustSpclMsgForUpdate(getGlobalCompanyCode(), jsMsg.dsCustSpclMsgPk_J1.getValue(), false);
                if (beforeDsCustSpclMsgTMsg != null) {
                    DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg = new DS_CUST_SPCL_MSGTMsg();
                    setDsCustSpclMsgData(dsCustSpclMsgTMsg, bizMsg, jsMsg, false);
                    //Change check
                    if (!changeFieldsCheckForDsCustSpclMsg(dsCustSpclMsgTMsg, beforeDsCustSpclMsgTMsg)) {
                        continue; // no change.
                    }
                    // 2019/01/19 QC#29940 Add Start
                    // If don't change effThruDt, pass NMAM8200E check.
                    String effThruDt = jsMsg.effThruDt_J1.getValue();
                    if (!ZYPCommonFunc.hasValue(effThruDt) && !ZYPCommonFunc.hasValue(beforeDsCustSpclMsgTMsg.effThruDt.getValue())) {
                        continue; // No change effThruDt(Null pattern).
                    } else if (ZYPCommonFunc.hasValue(effThruDt) && effThruDt.equals(beforeDsCustSpclMsgTMsg.effThruDt.getValue())) {
                        continue; // No change effThruDt(Not null pattern).
                    }
                    // 2019/01/19 QC#29940 Add End
                }
            }
            //
            String effThruDt = jsMsg.effThruDt_J1.getValue();
            if (ZYPCommonFunc.hasValue(salesDate) && ZYPCommonFunc.hasValue(effThruDt) && salesDate.compareTo(effThruDt) > 0) {
                String[] args = {"End Date" };
                jsMsg.effThruDt_J1.setErrorInfo(1, NMAM8200E, args);
                rtnFlg = false;
            }

            // 2018/11/13 QC#27954 Del Start
            // 2018/02/14 QC#20297(Sol#379) add start
//            if (DS_BIZ_AREA.PARTS.equals(jsMsg.dsBizAreaCd_J3.getValue()) && !DS_CUST_MSG_TP.SHIP.equals(jsMsg.dsCustMsgTpCd_J3.getValue())) {
//                String[] args = {"PARTS", "SHIP" };
//                jsMsg.dsCustMsgTpCd_J3.setErrorInfo(1, NAMM0016E, args);
//                rtnFlg = false;
//            }
            // 2018/02/14 QC#20297(Sol#379) add end
            // 2018/11/13 QC#27954 Del End
        }
        return rtnFlg;
    }

    private boolean updateAddress(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H3.getValue())
                && bizMsg.dsAcctTpCd_H3.getValue().equals(bizMsg.dsAcctTpCd_HD.getValue())) {
            // Get PrimaryInfo
            Map<String, Object> primaryInfo = getPrimaryInfo(bizMsg, sharedMsg);

            if (primaryInfo != null) {
                    // Update PRIN_CUST
                    if (!updatePrinCust(bizMsg, sharedMsg, glblCmpyCd, primaryInfo)) {
                        return false;
                    }

                    // QC#59957 clear duplicate Prin cust
                    if (ZYPCommonFunc.hasValue(bizMsg.prinCustPk_H2) && ZYPCommonFunc.hasValue((String) primaryInfo.get("LOC_NUM"))) {
                        clearExistsPrimLocPrinCust(glblCmpyCd, (String) primaryInfo.get("LOC_NUM"), bizMsg.prinCustPk_H2.getValue());
                    }

                    // Update SELL_TO_CUST
                    if (!updateSellToCustFromPrinCust(bizMsg, sharedMsg, glblCmpyCd, primaryInfo)) {
                        return false;
                    }
            } else {
                // If primary location flag is removed from screen, clear locNum, ptyLocPk, prinCustPk, address for account level tables.
                boolean isPrimLocFlgExstAtSrch = false;
                for (int i = 0; i < sharedMsg.A.getValidCount(); i++) {
                    NMAL6700_ASMsg smsg = (NMAL6700_ASMsg) sharedMsg.A.get(i);
                    if (ZYPConstant.FLG_ON_Y.equals(smsg.xxChkBox_AH.getValue())) {
                        isPrimLocFlgExstAtSrch = true;
                        break;
                    }
                }
                if (isPrimLocFlgExstAtSrch) {
                    if (!clearPrimLocPrinCust(bizMsg, sharedMsg, glblCmpyCd)) {
                        return false;
                    }
                    if (!clearPrimLocSellToCust(bizMsg, sharedMsg, glblCmpyCd)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean updateRelnShips(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        int cntRelnShips = sharedMsg.C.getValidCount();
        for (int i = 0; i < cntRelnShips; i++) {
            NMAL6700_CSMsg csMsg = sharedMsg.C.no(i);

            if (ZYPCommonFunc.hasValue(csMsg.dsAcctRelnPk_C1)) {

                DS_ACCT_RELNTMsg dsAcctRelnTMsg = NMAL6700CommonLogic.findDsAcctRelnForUpdate(glblCmpyCd, csMsg.dsAcctRelnPk_C1.getValue());
                if (dsAcctRelnTMsg == null) {
                    String[] args = {"DS_ACCT_RELN" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(csMsg.ezUpTime_C1.getValue(), csMsg.ezUpTimeZone_C1.getValue(), dsAcctRelnTMsg.ezUpTime.getValue(), dsAcctRelnTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                // START 2017/08/09 J.Kim [QC#20184,DEL]
                // String prevEffFromDt = dsAcctRelnTMsg.effFromDt.getValue();
                // String prevEffThruDt = dsAcctRelnTMsg.effThruDt.getValue();
                // END 2017/08/09 J.Kim [QC#20184,DEL]

                DS_ACCT_RELNTMsg beforeDsAcctRelnTMsg = new DS_ACCT_RELNTMsg();
                DS_ACCT_RELNTMsg.copy(dsAcctRelnTMsg, null, beforeDsAcctRelnTMsg, null);
                setDsAcctRelnData(dsAcctRelnTMsg, bizMsg, csMsg, false);
                //Change check
                if (changeFieldsCheckForAcctReln(dsAcctRelnTMsg, beforeDsAcctRelnTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsAcctRelnTMsg);
                    if (!RTNCD_NORMAL.equals(dsAcctRelnTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                        return false;
                    }

                    if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
                        // START 2017/08/09 J.Kim [QC#20184,MOD]
                        // if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())
                        //    && !dsAcctRelnTMsg.dsAcctRelnRecipFlg.getValue().equals(csMsg.xxChkBox_CR.getValue())) {
                        // Mod Start 2017/11/16 QC#17322(Sol#174)
                        //if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                        if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                            // Mod End 2017/11/16 QC#17322(Sol#174)
                        // END 2017/08/09 J.Kim [QC#20184,MOD]
                            if (!createOrUpdateRecipAcctReln(glblCmpyCd, bizMsg, csMsg, dsAcctRelnTMsg, beforeDsAcctRelnTMsg)) {
                                return false;
                            }
                        }
                    } else {
                        if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                            if (!createReverseRelnShip(glblCmpyCd, bizMsg, csMsg)) {
                                return false;
                            }
                        }
                    }

                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }
            } else {
                DS_ACCT_RELNTMsg dsAcctRelnTMsg = new DS_ACCT_RELNTMsg();
                setDsAcctRelnData(dsAcctRelnTMsg, bizMsg, csMsg, true);

                S21FastTBLAccessor.create(dsAcctRelnTMsg);
                if (!RTNCD_NORMAL.equals(dsAcctRelnTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
                    if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                        if (!createOrUpdateRecipAcctReln(glblCmpyCd, bizMsg, csMsg, dsAcctRelnTMsg, null)) {
                            return false;
                        }
                    }
                // START 2018/09/12 [QC#28165, ADD]
                } else {
                    if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                        if (!createReverseRelnShip(glblCmpyCd, bizMsg, csMsg)) {
                            return false;
                        }
                    }
                }
                // END 2018/09/12 [QC#28165, ADD]
            }
        }
        // Delete DS_ACCT_RELN
        int cnt = sharedMsg.O.getValidCount();
        // Add Start 2017/11/16 QC#17322(Sol#174)
        boolean relatedFlg = false;
        // Add End 2017/11/16 QC#17322(Sol#174)
        for (int i = 0; i < cnt; i++) {
            NMAL6700_OSMsg osMsg = sharedMsg.O.no(i);
            DS_ACCT_RELNTMsg dsAcctRelnTMsg = NMAL6700CommonLogic.findDsAcctRelnForUpdate(glblCmpyCd, osMsg.dsAcctRelnPk_O1.getValue());
            if (dsAcctRelnTMsg == null) {
                String[] args = {"DS_ACCT_RELN" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }

            if (!ZYPDateUtil.isSameTimeStamp(osMsg.ezUpTime_O1.getValue(), osMsg.ezUpTimeZone_O1.getValue(), dsAcctRelnTMsg.ezUpTime.getValue(), dsAcctRelnTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsAcctRelnTMsg);
            if (!RTNCD_NORMAL.equals(dsAcctRelnTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            // 2017/10/3 QC#21198 Add Start
            // Add Start 2017/11/16 QC#17322(Sol#174)
            if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(dsAcctRelnTMsg.dsAcctRelnTpCd.getValue())) {
                relatedFlg = true;
            } else {
                relatedFlg = false;
            }
            // Add End 2017/11/16 QC#17322(Sol#174)

            // Mod Start 2017/11/16 QC#17322(Sol#174)
            //if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(dsAcctRelnTMsg.dsAcctRelnTpCd.getValue()) && ZYPConstant.FLG_ON_Y.equals(dsAcctRelnTMsg.dsAcctRelnRecipFlg.getValue())) {
            //    S21SsmEZDResult result = NMAL6700Query.getInstance().getRelnAcct(glblCmpyCd, dsAcctRelnTMsg.dsAcctNum.getValue(), dsAcctRelnTMsg.relnDsAcctNum.getValue());
            if (relatedFlg && ZYPConstant.FLG_ON_Y.equals(dsAcctRelnTMsg.dsAcctRelnRecipFlg.getValue())) {

                S21SsmEZDResult result = NMAL6700Query.getInstance().getRelnAcct(glblCmpyCd, dsAcctRelnTMsg.dsAcctNum.getValue(), dsAcctRelnTMsg.relnDsAcctNum.getValue(), dsAcctRelnTMsg.dsAcctRelnTpCd.getValue(), dsAcctRelnTMsg.effFromDt.getValue(), dsAcctRelnTMsg.effThruDt.getValue());
                // Mod End 2017/11/16 QC#17322(Sol#174)

                if (result.isCodeNotFound()) {
                    continue;
                }

                if (!result.isCodeNormal()) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                    return false;
                }
                BigDecimal dsAcctRelnPk = (BigDecimal) result.getResultObject();

                DS_ACCT_RELNTMsg acctRelnTMsg = NMAL6700CommonLogic.findDsAcctRelnForUpdate(glblCmpyCd, dsAcctRelnPk);

                // START 2018/09/28 [QC#28165, MOD]
                if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(dsAcctRelnTMsg.dsAcctRelnTpCd.getValue())) {
                    if (ZYPConstant.FLG_ON_Y.equals(dsAcctRelnTMsg.dsAcctRelnBillToFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(dsAcctRelnTMsg.dsAcctRelnShipToFlg.getValue())){
                        //Noting
                    } else if (ZYPConstant.FLG_ON_Y.equals(dsAcctRelnTMsg.dsAcctRelnBillToFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(dsAcctRelnTMsg.dsAcctRelnShipToFlg.getValue())){
                        ZYPEZDItemValueSetter.setValue(acctRelnTMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_OFF_N);
                    } else if (ZYPConstant.FLG_OFF_N.equals(dsAcctRelnTMsg.dsAcctRelnBillToFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(dsAcctRelnTMsg.dsAcctRelnShipToFlg.getValue())){
                        ZYPEZDItemValueSetter.setValue(acctRelnTMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_OFF_N);
                    }
                    ZYPEZDItemValueSetter.setValue(acctRelnTMsg.dsAcctRelnRecipFlg, ZYPConstant.FLG_OFF_N);

                    S21FastTBLAccessor.update(acctRelnTMsg);
                    if (!RTNCD_NORMAL.equals(acctRelnTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                        return false;
                    }
                } else {
                    EZDTBLAccessor.logicalRemove(acctRelnTMsg);
                    if (!RTNCD_NORMAL.equals(acctRelnTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                        return false;
                    }
                }
                // END 2018/09/28 [QC#28165, MOD]
            }
            // 2017/10/3 QC#21198 Add End
        }

        return true;
    }

    private boolean updateContacts(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        int cntContacts = sharedMsg.D.getValidCount();
        for (int i = 0; i < cntContacts; i++) {
            NMAL6700_DSMsg dsMsg = sharedMsg.D.no(i);
            // 2018/08/30 S21_NA#27869 Add Start
            if (!ZYPCommonFunc.hasValue(dsMsg.dsCtacPsnRelnPk_D1)) {
                // Service Contact is not process target.
                continue;
            }
            // 2018/08/30 S21_NA#27869 Add End

            if (!dsMsg.dsPrimLocFlg_D1.getValue().equals(dsMsg.dsPrimLocFlg_DB.getValue())) { // 2017/08/08 S21_NA#8598 Add
                if (!updateDsCtacPsnReln(glblCmpyCd, bizMsg, sharedMsg, dsMsg)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean updateMarketing(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        if (!updateDsAccountForMarketingTab(bizMsg, sharedMsg, glblCmpyCd, bizMsg.dsAcctNum_H1.getValue())) {
            return false;
        }

        if (!updateCmpyForMarketingTab(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        int cnt = sharedMsg.E.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_ESMsg esMsg = sharedMsg.E.no(i);
            if (ZYPCommonFunc.hasValue(esMsg.dsAcctGrpAsgPk_E1)) {

                DS_ACCT_GRP_ASGTMsg dsAcctGrpAsgTMsg = NMAL6700CommonLogic.findDsAcctGrpAsgForUpdate(glblCmpyCd, esMsg.dsAcctGrpAsgPk_E1.getValue());
                if (dsAcctGrpAsgTMsg == null) {
                    String[] args = {"DS_ACCT_GRP_ASG" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(esMsg.ezUpTime_E1.getValue(), esMsg.ezUpTimeZone_E1.getValue(), dsAcctGrpAsgTMsg.ezUpTime.getValue(), dsAcctGrpAsgTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_ACCT_GRP_ASGTMsg beforeDsAcctGrpAsgTMsg = new DS_ACCT_GRP_ASGTMsg();
                DS_ACCT_GRP_ASGTMsg.copy(dsAcctGrpAsgTMsg, null, beforeDsAcctGrpAsgTMsg, null);
                setDsAcctGrpAsgData(dsAcctGrpAsgTMsg, bizMsg, esMsg, false);
                //Change check
                if (changeFieldsCheckForDsAcctGrpAsg(dsAcctGrpAsgTMsg, beforeDsAcctGrpAsgTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsAcctGrpAsgTMsg);
                    if (!RTNCD_NORMAL.equals(dsAcctGrpAsgTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_GRP_ASG" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }

            } else {
                DS_ACCT_GRP_ASGTMsg dsAcctGrpAsgTMsg = new DS_ACCT_GRP_ASGTMsg();
                setDsAcctGrpAsgData(dsAcctGrpAsgTMsg, bizMsg, esMsg, true);

                S21FastTBLAccessor.create(dsAcctGrpAsgTMsg);
                if (!RTNCD_NORMAL.equals(dsAcctGrpAsgTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_GRP_ASG" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }

        // Delete DS_ACCT_GRP_ASG
        if (!deleteDsAcctGrpAsgForMarketing(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }
        // Certification Required
        // Insert, Update SVC_ACCS_PMIT
        if (!insertAndUpdateSvcAccsPmitForMarketing(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }
        // Delete SVC_ACCS_PMIT
        if (!deleteSvcAccsPmitgForMarketing(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }

    private boolean updateTransactions(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        // Insert or Update DS_CUST_TRX_RULE
        int cnt = bizMsg.F.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_FCMsg fcMsg = bizMsg.F.no(i);
            if (ZYPCommonFunc.hasValue(fcMsg.dsCustTrxRulePk_F1)) {

                DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg = NMAL6700CommonLogic.findDsCustTrxRuleForUpdate(glblCmpyCd, fcMsg.dsCustTrxRulePk_F1.getValue());
                if (dsCustTrxRuleTMsg == null) {
                    String[] args = {"DS_CUST_TRX_RULE" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(fcMsg.ezUpTime_F1.getValue(), fcMsg.ezUpTimeZone_F1.getValue(), dsCustTrxRuleTMsg.ezUpTime.getValue(), dsCustTrxRuleTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_CUST_TRX_RULETMsg beforeDsCustTrxRuleTMsg = new DS_CUST_TRX_RULETMsg();
                DS_CUST_TRX_RULETMsg.copy(dsCustTrxRuleTMsg, null, beforeDsCustTrxRuleTMsg, null);
                setDsCustTrxRuleData(dsCustTrxRuleTMsg, bizMsg, fcMsg, false);
                //Change check
                if (changeFieldsCheckForDsCustTrxRule(dsCustTrxRuleTMsg, beforeDsCustTrxRuleTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsCustTrxRuleTMsg);
                    if (!RTNCD_NORMAL.equals(dsCustTrxRuleTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_TRX_RULE" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }

            } else {
                DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg = new DS_CUST_TRX_RULETMsg();
                setDsCustTrxRuleData(dsCustTrxRuleTMsg, bizMsg, fcMsg, true);

                S21FastTBLAccessor.create(dsCustTrxRuleTMsg);
                if (!RTNCD_NORMAL.equals(dsCustTrxRuleTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_TRX_RULE" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        // Delete DS_CUST_TRX_RULE
        cnt = sharedMsg.V.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_VSMsg vsMsg = sharedMsg.V.no(i);
            DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg = NMAL6700CommonLogic.findDsCustTrxRuleForUpdate(glblCmpyCd, vsMsg.dsCustTrxRulePk_V1.getValue());
            if (dsCustTrxRuleTMsg == null) {
                String[] args = {"DS_CUST_TRX_RULE" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(vsMsg.ezUpTime_V1.getValue(), vsMsg.ezUpTimeZone_V1.getValue(), dsCustTrxRuleTMsg.ezUpTime.getValue(), dsCustTrxRuleTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsCustTrxRuleTMsg);
            if (!RTNCD_NORMAL.equals(dsCustTrxRuleTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_TRX_RULE" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Insert or Update DS_CUST_SPCL_HDLG
        cnt = bizMsg.S.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_SCMsg scMsg = bizMsg.S.no(i);
            if (ZYPCommonFunc.hasValue(scMsg.dsCustSpclHdlgPk_S1)) {

                DS_CUST_SPCL_HDLGTMsg dsCustSpclHdlgTMsg = NMAL6700CommonLogic.findDsCustSpclHdlgForUpdate(glblCmpyCd, scMsg.dsCustSpclHdlgPk_S1.getValue());
                if (dsCustSpclHdlgTMsg == null) {
                    String[] args = {"DS_CUST_SPCL_HDLG" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(scMsg.ezUpTime_S1.getValue(), scMsg.ezUpTimeZone_S1.getValue(), dsCustSpclHdlgTMsg.ezUpTime.getValue(), dsCustSpclHdlgTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_CUST_SPCL_HDLGTMsg beforeDsCustSpclHdlgTMsg = new DS_CUST_SPCL_HDLGTMsg();
                DS_CUST_SPCL_HDLGTMsg.copy(dsCustSpclHdlgTMsg, null, beforeDsCustSpclHdlgTMsg, null);
                setDsCustSpclHdlgData(dsCustSpclHdlgTMsg, bizMsg, scMsg, false);
                //Change check
                if (changeFieldsCheckForDsCustSpclHdlg(dsCustSpclHdlgTMsg, beforeDsCustSpclHdlgTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsCustSpclHdlgTMsg);
                    if (!RTNCD_NORMAL.equals(dsCustSpclHdlgTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SPCL_HDLG" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }
            } else {
                DS_CUST_SPCL_HDLGTMsg dsCustSpclHdlgTMsg = new DS_CUST_SPCL_HDLGTMsg();
                setDsCustSpclHdlgData(dsCustSpclHdlgTMsg, bizMsg, scMsg, true);

                S21FastTBLAccessor.create(dsCustSpclHdlgTMsg);
                if (!RTNCD_NORMAL.equals(dsCustSpclHdlgTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SPCL_HDLG" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        // Delete DS_CUST_SPCL_HDLG
        cnt = sharedMsg.H.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_HSMsg hsMsg = sharedMsg.H.no(i);
            DS_CUST_SPCL_HDLGTMsg dsCustSpclHdlgTMsg = NMAL6700CommonLogic.findDsCustSpclHdlgForUpdate(glblCmpyCd, hsMsg.dsCustSpclHdlgPk_H1.getValue());
            if (dsCustSpclHdlgTMsg == null) {
                String[] args = {"DS_CUST_SPCL_HDLG" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(hsMsg.ezUpTime_H1.getValue(), hsMsg.ezUpTimeZone_H1.getValue(), dsCustSpclHdlgTMsg.ezUpTime.getValue(), dsCustSpclHdlgTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsCustSpclHdlgTMsg);
            if (!RTNCD_NORMAL.equals(dsCustSpclHdlgTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SPCL_HDLG" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        return true;

    }

    private boolean updateCollections(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!updateDsAcctCrPrfl(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }
        if (!updateArAmount(bizMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }

    private boolean updateInvBllg(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!createOrUpdateDsCustInvRule(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        if (!createOrUpdateDsAcctRefAttrb(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }
    private boolean updateBankAcct(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        // 2018/12/10 QC#29315 Del Start
//
//        EZDMsg.copy(bizMsg.W, null, sharedMsg.W, null);
//
//        // Delete DS_ACCT_CARR
//        int cnt = sharedMsg.U.getValidCount();
//        for (int i = 0; i < cnt; i++) {
//            NMAL6700_USMsg usMsg = sharedMsg.U.no(i);
//            DS_ACCT_CARRTMsg dsAcctCarrTMsg = NMAL6700CommonLogic.findDsAcctCarrForUpdate(glblCmpyCd, usMsg.dsAcctCarrPk_U1.getValue());
//            if (dsAcctCarrTMsg == null) {
//                String[] args = {"DS_ACCT_CARR" };
//                bizMsg.setMessageInfo(NMAM8111E, args);
//                return false;
//            }
//            if (!ZYPDateUtil.isSameTimeStamp(usMsg.ezUpTime_U1.getValue(), usMsg.ezUpTimeZone_U1.getValue(), dsAcctCarrTMsg.ezUpTime.getValue(), dsAcctCarrTMsg.ezUpTimeZone.getValue())) {
//                bizMsg.setMessageInfo(NZZM0003E);
//                return false;
//            }
//
//            EZDTBLAccessor.logicalRemove(dsAcctCarrTMsg);
//            if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_CARR" });
//                return false;
//            }
//            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//        }
//        // Insert or Update DS_ACCT_CARR
//        int cntDsAcctCarr = sharedMsg.W.getValidCount();
//        for (int i = 0; i < cntDsAcctCarr; i++) {
//            NMAL6700_WSMsg wsMsg = sharedMsg.W.no(i);
//            if (ZYPCommonFunc.hasValue(wsMsg.dsAcctCarrPk_W1)) {
//
//                DS_ACCT_CARRTMsg dsAcctCarrTMsg = NMAL6700CommonLogic.findDsAcctCarrForUpdate(glblCmpyCd, wsMsg.dsAcctCarrPk_W1.getValue());
//                if (dsAcctCarrTMsg == null) {
//                    String[] args = {"DS_ACCT_CARR" };
//                    bizMsg.setMessageInfo(NMAM8111E, args);
//                    return false;
//                }
//                if (!ZYPDateUtil.isSameTimeStamp(wsMsg.ezUpTime_W1.getValue(), wsMsg.ezUpTimeZone_W1.getValue(), dsAcctCarrTMsg.ezUpTime.getValue(), dsAcctCarrTMsg.ezUpTimeZone.getValue())) {
//                    bizMsg.setMessageInfo(NZZM0003E);
//                    return false;
//                }
//                DS_ACCT_CARRTMsg beforeDsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
//                DS_ACCT_CARRTMsg.copy(dsAcctCarrTMsg, null, beforeDsAcctCarrTMsg, null);
//                setDsAcctCarrData(dsAcctCarrTMsg, bizMsg, wsMsg, false);
//                //Change check
//                if (changeFieldsCheckForDsAcctCarr(dsAcctCarrTMsg, beforeDsAcctCarrTMsg)) {
//                    //Change has been made.
//                    S21FastTBLAccessor.update(dsAcctCarrTMsg);
//                    if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
//                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_CARR" });
//                        return false;
//                    }
//                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//                }
//
//            } else {
//                DS_ACCT_CARRTMsg dsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
//                setDsAcctCarrData(dsAcctCarrTMsg, bizMsg, wsMsg, true);
//
//                S21FastTBLAccessor.create(dsAcctCarrTMsg);
//                if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_CARR" });
//                    return false;
//                }
//                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//            }
//        }
//
        // 2018/12/10 QC#29315 Del End

        // Update DS_BANK_ACCT
        // START 2020/12/14 J.Evangelista [QC#57937,MOD]
        // 2018/12/10 QC#29315 Mod Start
//        cnt = bizMsg.I.getValidCount();
//        int cnt = bizMsg.I.getValidCount();
        // 2018/12/10 QC#29315 Mod End
        int cnt = sharedMsg.I.getValidCount();
        // END   2020/12/14 J.Evangelista [QC#57937,MOD]
        for (int i = 0; i < cnt; i++) {
            // START 2020/12/14 J.Evangelista [QC#57937,MOD]
//            NMAL6700_ICMsg isMsg = bizMsg.I.no(i);
            NMAL6700_ISMsg isMsg = sharedMsg.I.no(i);
            // END   2020/12/14 J.Evangelista [QC#57937,MOD]
            if (ZYPCommonFunc.hasValue(isMsg.dsBankAcctPk_I1)) {

                DS_BANK_ACCTTMsg dsBankAcctTMsg = NMAL6700CommonLogic.findDsBankAcctForUpdate(glblCmpyCd, isMsg.dsBankAcctPk_I1.getValue());
                if (dsBankAcctTMsg == null) {
                    String[] args = {"DS_BANK_ACCT" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(isMsg.ezUpTime_I1.getValue(), isMsg.ezUpTimeZone_I1.getValue(), dsBankAcctTMsg.ezUpTime.getValue(), dsBankAcctTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_BANK_ACCTTMsg beforeDsBankAcctTMsg = new DS_BANK_ACCTTMsg();
                DS_BANK_ACCTTMsg.copy(dsBankAcctTMsg, null, beforeDsBankAcctTMsg, null);
                ZYPEZDItemValueSetter.setValue(dsBankAcctTMsg.effThruDt, isMsg.effThruDt_I1.getValue());
                if (changeFieldsCheckForDsBankAcct(dsBankAcctTMsg, beforeDsBankAcctTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsBankAcctTMsg);
                    if (!RTNCD_NORMAL.equals(dsBankAcctTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_BANK_ACCT" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                }

            }
        }
        return true;
    }

    private boolean updateInstructions(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        // Delete DS_CUST_SPCL_MSG
        int delCnt = sharedMsg.X.getValidCount();
        for (int i = 0; i < delCnt; i++) {
            NMAL6700_XSMsg xsMsg = sharedMsg.X.no(i);
            if (ZYPCommonFunc.hasValue(xsMsg.dsCustSpclMsgPk_X1)) {
                DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg = new DS_CUST_SPCL_MSGTMsg();
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsCustSpclMsgPk, xsMsg.dsCustSpclMsgPk_X1);
                dsCustSpclMsgTMsg = (DS_CUST_SPCL_MSGTMsg) EZDTBLAccessor.findByKeyForUpdate(dsCustSpclMsgTMsg);
                if (dsCustSpclMsgTMsg == null) {
                    String[] args = {"DS_CUST_SPCL_MSG" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }

                if (!ZYPDateUtil.isSameTimeStamp(xsMsg.ezUpTime_X1.getValue(), xsMsg.ezUpTimeZone_X1.getValue(), dsCustSpclMsgTMsg.ezUpTime.getValue(), dsCustSpclMsgTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                EZDTBLAccessor.logicalRemove(dsCustSpclMsgTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCustSpclMsgTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0208E, new String[] {"DS_CUST_SPCL_MSG" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        // Insert or Update DS_CUST_SPCL_MSG
        int cnt = sharedMsg.J.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_JSMsg jsMsg = sharedMsg.J.no(i);
            if (ZYPCommonFunc.hasValue(jsMsg.dsCustSpclMsgPk_J1)) {

                DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg = NMAL6700CommonLogic.findDsCustSpclMsgForUpdate(glblCmpyCd, jsMsg.dsCustSpclMsgPk_J1.getValue(), true); // QC#18961
                if (dsCustSpclMsgTMsg == null) {
                    String[] args = {"DS_CUST_SPCL_MSG" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(jsMsg.ezUpTime_J1.getValue(), jsMsg.ezUpTimeZone_J1.getValue(), dsCustSpclMsgTMsg.ezUpTime.getValue(), dsCustSpclMsgTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_CUST_SPCL_MSGTMsg beforeDsCustSpclMsgTMsg = new DS_CUST_SPCL_MSGTMsg();
                DS_CUST_SPCL_MSGTMsg.copy(dsCustSpclMsgTMsg, null, beforeDsCustSpclMsgTMsg, null);
                setDsCustSpclMsgData(dsCustSpclMsgTMsg, bizMsg, jsMsg, false);
                //Change check
                if (changeFieldsCheckForDsCustSpclMsg(dsCustSpclMsgTMsg, beforeDsCustSpclMsgTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsCustSpclMsgTMsg);
                    if (!RTNCD_NORMAL.equals(dsCustSpclMsgTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SPCL_MSG" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                }
            } else {
                DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg = new DS_CUST_SPCL_MSGTMsg();
                setDsCustSpclMsgData(dsCustSpclMsgTMsg, bizMsg, jsMsg, true);

                S21FastTBLAccessor.create(dsCustSpclMsgTMsg);
                if (!RTNCD_NORMAL.equals(dsCustSpclMsgTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SPCL_MSG" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        return true;
    }

    /**
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     * @param glblCmpyCd String
     */
    private void updateAcctInfo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H3.getValue())) {

            if ((bizMsg.dsAcctTpCd_H3.getValue().equals(bizMsg.dsAcctTpCd_HD.getValue()))) {
                SELL_TO_CUSTTMsg updateSellToCustTMsg = NMAL6700CommonLogic.findSellToCustForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
                if (updateSellToCustTMsg == null) {
                    String[] args = {"SELL_TO_CUST" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), updateSellToCustTMsg.ezUpTime.getValue(), updateSellToCustTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }
                // Acct --> Pros
                if (DS_ACCT_TP.CUSTOMER.equals(updateSellToCustTMsg.dsAcctTpCd.getValue())
                        && DS_ACCT_TP.PROSPECT.equals(bizMsg.dsAcctTpCd_H3.getValue())) {
                    String[] args = {"before 'CUSTOMER'", "PROSPECT" };
                    bizMsg.setMessageInfo(NMAM0076E, args);
                    return;

                }
                SELL_TO_CUSTTMsg beforeUpdateSellToCustTMsg = new SELL_TO_CUSTTMsg();
                SELL_TO_CUSTTMsg.copy(updateSellToCustTMsg, null, beforeUpdateSellToCustTMsg, null);
                if (!setSellToCustData(updateSellToCustTMsg, bizMsg, false)) {
                    return;
                }
                //Change check
                if (changeFieldsCheckForCust(updateSellToCustTMsg, beforeUpdateSellToCustTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(updateSellToCustTMsg);
                    if (!RTNCD_NORMAL.equals(updateSellToCustTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"SELL_TO_CUST" });
                        return;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                } else {
                    return;
                }
                updateSellToCustRelatedInfo(bizMsg, sharedMsg, glblCmpyCd);
                // START 2024/03/11 S.Ikariya [QC#63499, ADD]
                if (!cascadeToBillToCust(bizMsg, sharedMsg, beforeUpdateSellToCustTMsg)) {
                    return;
                }
                // END 2024/03/11 S.Ikariya [QC#63499, ADD]
            } else {
                boolean exstLocWithoutLocNum = false;
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", glblCmpyCd);
                queryParam.put("dsAcctNum", bizMsg.dsAcctNum_H1.getValue());
                S21SsmEZDResult res = NMAL6700Query.getInstance().getLocationWithoutLocNum(queryParam);
                if (res.isCodeNormal()) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) res.getResultObject();
                    if (list != null && list.size() > 0) {
                        exstLocWithoutLocNum = true;
                    }
                }
                if (exstLocWithoutLocNum) {
                    DS_ACCT_PROSTMsg updateDsAcctProsTMsg = NMAL6700CommonLogic.findDsAcctProsForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
                    if (updateDsAcctProsTMsg == null) {
                        String[] args = {"DS_ACCT_PROS" };
                        bizMsg.setMessageInfo(NMAM8111E, args);
                        return;
                    }
                    if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), updateDsAcctProsTMsg.ezUpTime.getValue(), updateDsAcctProsTMsg.ezUpTimeZone.getValue())) {
                        bizMsg.setMessageInfo(NZZM0003E);
                        return;
                    }

                    setDsAcctProsData(updateDsAcctProsTMsg, bizMsg, false);
                    updateDsAcctProsTMsg.dsAcctTpCd.setValue(DS_ACCT_TP.PROSPECT);
                    updateDsAcctProsTMsg.prosToCustChngStsCd.setValue(PROS_TO_CUST_CHNG_STS_PENDING);

                    S21FastTBLAccessor.update(updateDsAcctProsTMsg);
                    if (!RTNCD_NORMAL.equals(updateDsAcctProsTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_PROS_PROS" });
                        return;
                    }

                    updateDsAcctProsRelatedInfo(bizMsg, sharedMsg, glblCmpyCd);

                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                    bizMsg.setMessageInfo(NMAM8648I);

                } else {

                    // Delete DS_ACCT_PROS
                    DS_ACCT_PROSTMsg deleteDsAcctProsTMsg = NMAL6700CommonLogic.findDsAcctProsForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
                    if (deleteDsAcctProsTMsg == null) {
                        String[] args = {"DS_ACCT_PROS" };
                        bizMsg.setMessageInfo(NMAM8111E, args);
                        return;
                    }
                    EZDTMsg[] tmsgList = new EZDTMsg[] {deleteDsAcctProsTMsg };
                    int delCnt = S21FastTBLAccessor.removeLogical(tmsgList);
                    if (delCnt != 1) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_PROS" });
                        return;
                    }
                    SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
                    EZDMsg.copy(deleteDsAcctProsTMsg, null, sellToCustTMsg, null);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustPk, deleteDsAcctProsTMsg.dsAcctProsPk);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustCd, deleteDsAcctProsTMsg.dsAcctNum);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellHldFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.canRcvInvAtSetCmptFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.prinCustPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRIN_CUST_SQ));
                    ZYPEZDItemValueSetter.setValue(bizMsg.prinCustPk_H2, sellToCustTMsg.prinCustPk);
                    if (!setSellToCustData(sellToCustTMsg, bizMsg, false)) {
                        return;
                    }

                    S21FastTBLAccessor.create(sellToCustTMsg);
                    if (!RTNCD_NORMAL.equals(sellToCustTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0176E, new String[] {"SELL_TO_CUST" });
                        return;
                    }
    
                    // create primary customer
                    PRIN_CUSTTMsg prinCustTMsg = createNewPrinCustTMsg(bizMsg, glblCmpyCd);
                    S21FastTBLAccessor.create(prinCustTMsg);
                    if (!RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0176E, new String[] {"PRIN_CUST" });
                        return;
                    }

                    // delete prospect location
                    PROS_PTY_LOC_WRKTMsg deleteProsPtyLocWrkTmsg = new PROS_PTY_LOC_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(deleteProsPtyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(deleteProsPtyLocWrkTmsg.ptyLocPk, deleteDsAcctProsTMsg.ptyLocPk.getValue());
                    deleteProsPtyLocWrkTmsg = (PROS_PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(deleteProsPtyLocWrkTmsg);
                    if (deleteProsPtyLocWrkTmsg == null || !RTNCD_NORMAL.equals(deleteProsPtyLocWrkTmsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM8111E, new String[] {"PROS_PTY_LOC_WRK" });
                        return;
                    }
                    tmsgList = new EZDTMsg[] {deleteProsPtyLocWrkTmsg };
                    delCnt = S21FastTBLAccessor.removeLogical(tmsgList);
                    if (delCnt != 1) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"PROS_PTY_LOC_WRK" });
                        return;
                    }

                    // create customer location
                    PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
                    EZDMsg.copy(deleteProsPtyLocWrkTmsg, null, ptyLocWrkTMsg, null);
                    S21FastTBLAccessor.create(ptyLocWrkTMsg);
                    if (!RTNCD_NORMAL.equals(ptyLocWrkTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0176E, new String[] {"PTY_LOC_WRK" });
                        return;
                    }

                    updateSellToCustRelatedInfo(bizMsg, sharedMsg, glblCmpyCd);
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustPk_H1, sellToCustTMsg.sellToCustPk);
                }
            }
        } else {

            DS_ACCT_PROSTMsg updateDsAcctProsTMsg = NMAL6700CommonLogic.findDsAcctProsForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
            if (updateDsAcctProsTMsg == null) {
                String[] args = {"DS_ACCT_PROS" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return;
            }
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_H1.getValue(), bizMsg.ezUpTimeZone_H1.getValue(), updateDsAcctProsTMsg.ezUpTime.getValue(), updateDsAcctProsTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return;
            }

            DS_ACCT_PROSTMsg beforeUpdateDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            DS_ACCT_PROSTMsg.copy(updateDsAcctProsTMsg, null, beforeUpdateDsAcctProsTMsg, null);
            if (!setDsAcctProsData(updateDsAcctProsTMsg, bizMsg, false)) {
                return;
            }
            //Change check
            if (changeFieldsCheckForPros(updateDsAcctProsTMsg, beforeUpdateDsAcctProsTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateDsAcctProsTMsg);
                if (!RTNCD_NORMAL.equals(updateDsAcctProsTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_PROS_CUST" });
                    return;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            } else {
                return;
            }

            updateDsAcctProsRelatedInfo(bizMsg, sharedMsg, glblCmpyCd);
        }
    }

    private boolean updateSellToCustRelatedInfo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {

        // Update PTY_LOC_WRK
        if (!updatePtyLocWrk(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update CMPY method
        if (!updateCmpy(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update BILL_TO_CUST
        if (!updateBillToCust(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update PRIN_CUST
        if (!updatePrinCust(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update ACCT_LOC
        if (!updateAcctLoc(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update BILL_TO_CUST
        if (!updateBillToCustFromDsBillToCust(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update SHIP_TO_CUST
        if (!updateShipToCust(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
                && ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue())) {
            // Insert DS_CUST_SPCL_MSG
            if (!insertDsCustSpclMsg(bizMsg, glblCmpyCd)) {
                return false;
            }
        }

        return true;
    }

    private boolean updateDsAcctProsRelatedInfo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {

        // Update PROS_PTY_LOC_WRK
        if (!updateProsPtyLocWrk(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        // Update CMPY method
        if (!updateCmpy(bizMsg, sharedMsg, glblCmpyCd)) {
            return false;
        }

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
                && ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue())) {
            // Insert DS_CUST_SPCL_MSG
            if (!insertDsCustSpclMsg(bizMsg, glblCmpyCd)) {
                return false;
            }
        }

        return true;
    }

    private boolean updatePtyLocWrk(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (bizMsg.A.getValidCount() == 0) {
            if (!(ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
                    && !ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue()))) {
                return true;
            }
        }
        // Update PTY_LOC_WRK
        S21SsmEZDResult resLocation = NMAL6700Query.getInstance().getLocation(bizMsg.dsAcctNum_H1.getValue());
        if (!resLocation.isCodeNormal() && bizMsg.A.getValidCount() > 0) {
            String[] args = {"ACCT_LOC, PTY_LOC_WRK" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        List<Map<String, Object>> resultLocationList = (List<Map<String, Object>>) resLocation.getResultObject();
        for (int i = 0; i < resultLocationList.size(); i++) {
            Map<String, Object> resultLocation = resultLocationList.get(i);
            BigDecimal ptyLocPk = (BigDecimal) resultLocation.get("PTY_LOC_PK");
            PTY_LOC_WRKTMsg updatePtyLocWrkTMsg = NMAL6700CommonLogic.findPtyLocWrkForUpdate(glblCmpyCd, ptyLocPk);

            PTY_LOC_WRKTMsg beforeUpdatePtyLocWrkTMsg = new PTY_LOC_WRKTMsg();
            PTY_LOC_WRKTMsg.copy(updatePtyLocWrkTMsg, null, beforeUpdatePtyLocWrkTMsg, null);
            if (!setPtyLocWrkData(updatePtyLocWrkTMsg, bizMsg, sharedMsg)) {
                return false;
            }
            //Change check
            if (changeFieldsCheckForPtyLocWrk(updatePtyLocWrkTMsg, beforeUpdatePtyLocWrkTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updatePtyLocWrkTMsg);
                if (!RTNCD_NORMAL.equals(updatePtyLocWrkTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"PTY_LOC_WRK" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        return true;
    }

    private boolean updateProsPtyLocWrk(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (bizMsg.A.getValidCount() == 0) {
            if (!(ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
                    && !ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue()))) {
                return true;
            }
        }
        // Update PROS_PTY_LOC_WRK
        S21SsmEZDResult resProsLoc = NMAL6700Query.getInstance().getProspectLocation(bizMsg.dsAcctNum_H1.getValue());
        if (!resProsLoc.isCodeNormal() && bizMsg.A.getValidCount() > 0) {
            String[] args = {"PROS_PTY_LOC_WRK" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        List<Map<String, Object>> resultProsLocList = (List<Map<String, Object>>) resProsLoc.getResultObject();
        for (int i = 0; i < resultProsLocList.size(); i++) {
            Map<String, Object> resultProsLoc = resultProsLocList.get(i);
            BigDecimal ptyLocPk = (BigDecimal) resultProsLoc.get("PTY_LOC_PK");
            PROS_PTY_LOC_WRKTMsg updateProsPtyLocWrkTMsg = NMAL6700CommonLogic.findProsPtyLocWrkForUpdate(glblCmpyCd, ptyLocPk);

            PROS_PTY_LOC_WRKTMsg beforeUpdateProsPtyLocWrkTMsg = new PROS_PTY_LOC_WRKTMsg();
            PROS_PTY_LOC_WRKTMsg.copy(updateProsPtyLocWrkTMsg, null, beforeUpdateProsPtyLocWrkTMsg, null);
            if (!setProsPtyLocWrkData(updateProsPtyLocWrkTMsg, bizMsg, sharedMsg)) {
                return false;
            }
            //Change check
            if (changeFieldsCheckForProsPtyLocWrk(updateProsPtyLocWrkTMsg, beforeUpdateProsPtyLocWrkTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateProsPtyLocWrkTMsg);
                if (!RTNCD_NORMAL.equals(updateProsPtyLocWrkTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"PROS_PTY_LOC_WRK" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        return true;
    }

    private boolean updateCmpy(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        // Update CMPY
        CMPYTMsg updateCmpyTMsg = NMAL6700CommonLogic.findCmpyForUpdate(glblCmpyCd, bizMsg.cmpyPk_H1.getValue());
        if (updateCmpyTMsg == null) {
            String[] args = {"CMPY" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        CMPYTMsg beforeUpdateCmpyTMsg = new CMPYTMsg();
        CMPYTMsg.copy(updateCmpyTMsg, null, beforeUpdateCmpyTMsg, null);
        if (!setCmpyData(updateCmpyTMsg, bizMsg)) {
            return false;
        }
        //Change check
        if (changeFieldsCheckForCmpy(updateCmpyTMsg, beforeUpdateCmpyTMsg)) {
            //Change has been made.
            S21FastTBLAccessor.update(updateCmpyTMsg);
            if (!RTNCD_NORMAL.equals(updateCmpyTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"CMPY" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

        }
        return true;
    }

    private boolean updateBillToCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (bizMsg.A.getValidCount() == 0) {
            return true;
        }
        S21SsmEZDResult resBillToCust = NMAL6700Query.getInstance().getBillToCust(bizMsg.dsAcctNum_H1.getValue());
        if (!resBillToCust.isCodeNormal()) {
            //Not error
            return true;
        }
        List<Map<String, Object>> resultBillToCustList = (List<Map<String, Object>>) resBillToCust.getResultObject();
        if (resultBillToCustList.size() == 0) {
            return false;
        }
        for (int i = 0; i < resultBillToCustList.size(); i++) {
            Map<String, Object> resultBillToCust = resultBillToCustList.get(i);
            BigDecimal billToCustPk = (BigDecimal) resultBillToCust.get("BILL_TO_CUST_PK");
            BILL_TO_CUSTTMsg updateBillToCustTMsg = NMAL6700CommonLogic.findBillToCustForUpdate(glblCmpyCd, billToCustPk);
            if (updateBillToCustTMsg == null) {
                String[] args = {"BILL_TO_CUST" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }

            BILL_TO_CUSTTMsg beforeUpdateBillToCustTMsg = new BILL_TO_CUSTTMsg();
            BILL_TO_CUSTTMsg.copy(updateBillToCustTMsg, null, beforeUpdateBillToCustTMsg, null);
            if (!setBillToCustData(updateBillToCustTMsg, bizMsg)) {
                return false;
            }
            //Change check
            if (changeFieldsCheckForBill(updateBillToCustTMsg, beforeUpdateBillToCustTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateBillToCustTMsg);
                if (!RTNCD_NORMAL.equals(updateBillToCustTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            } else {
                return true;
            }
        }
        return true;
    }

    private boolean updatePrinCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (bizMsg.A.getValidCount() == 0) {
            return true;
        }
        // Update PRIN_CUST Method
        PRIN_CUSTTMsg updatePrinCustForRgtnStsCdTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, bizMsg.prinCustPk_H2.getValue());
        if (updatePrinCustForRgtnStsCdTMsg == null) {
            BigDecimal prinCustPk = this.getPrinCustPkAftDelPrimLocFlg(bizMsg);
            if (prinCustPk != null) {
                updatePrinCustForRgtnStsCdTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, prinCustPk);
                if (updatePrinCustForRgtnStsCdTMsg == null) {
                    return true;
                }
            }
        }
        PRIN_CUSTTMsg beforeUpdatePrinCustForRgtnStsCdTMsg = new PRIN_CUSTTMsg();
        PRIN_CUSTTMsg.copy(updatePrinCustForRgtnStsCdTMsg, null, beforeUpdatePrinCustForRgtnStsCdTMsg, null);
        if (!setPrinCustForRgtnStsCdData(bizMsg, updatePrinCustForRgtnStsCdTMsg)) {
            return false;
        }
        //Change check
        if (changeFieldsCheckForPrinCustRgtnStsCd(updatePrinCustForRgtnStsCdTMsg, beforeUpdatePrinCustForRgtnStsCdTMsg)) {
            //Change has been made.
            S21FastTBLAccessor.update(updatePrinCustForRgtnStsCdTMsg);
            if (!RTNCD_NORMAL.equals(updatePrinCustForRgtnStsCdTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"PRIN_CUST" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

        }
        return true;
    }

    private boolean updateAcctLoc(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        // Update ACCT_LOC
        S21SsmEZDResult resAcctLoc = NMAL6700Query.getInstance().getAcctLoc(bizMsg.dsAcctNum_H1.getValue());
        if (!resAcctLoc.isCodeNormal()) {
            // Not error
            return true;
        }

        List<Map<String, Object>> resultAcctLocList = (List<Map<String, Object>>) resAcctLoc.getResultObject();
        String effThruDt = MAX_EFF_THRU_DT;
        for (int i = 0; i < resultAcctLocList.size(); i++) {
            Map<String, Object> resultAcctLoc = resultAcctLocList.get(i);

            if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue())) {

                if (resultAcctLoc.get("EFF_THRU_DT") != null) {
                    effThruDt = resultAcctLoc.get("EFF_THRU_DT").toString();
                } else {
                    effThruDt = MAX_EFF_THRU_DT;
                }
                if (!ZYPCommonFunc.hasValue(sharedMsg.effThruDt_H1) || effThruDt.compareTo(sharedMsg.effThruDt_H1.getValue()) != 0) {
                    continue;
                }

                S21SsmEZDResult resCntAcctLoc = NMAL6700Query.getInstance().getCountActiveAcctLoc((String) resultAcctLoc.get("LOC_NUM"));
                if (resCntAcctLoc.isCodeNormal()) {
                    int cntAcctLoc = (Integer) resCntAcctLoc.getResultObject();
                    if (cntAcctLoc > 0) {
                        bizMsg.setMessageInfo(NMAM8658I);
                        continue;
                    }
                }
            }

            BigDecimal acctLocPk = (BigDecimal) resultAcctLoc.get("ACCT_LOC_PK");
            ACCT_LOCTMsg updateAcctLocTMsg = NMAL6700CommonLogic.findAcctLocForUpdate(glblCmpyCd, acctLocPk);
            if (updateAcctLocTMsg == null) {
                String[] args = {"ACCT_LOC" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }

            ACCT_LOCTMsg beforeUpdateAcctLocTMsg = new ACCT_LOCTMsg();
            ACCT_LOCTMsg.copy(updateAcctLocTMsg, null, beforeUpdateAcctLocTMsg, null);
            if (!setAcctLocData(updateAcctLocTMsg, bizMsg, sharedMsg)) {
                return false;
            }
            //Change check
            if (changeFieldsCheckForAcctLoc(updateAcctLocTMsg, beforeUpdateAcctLocTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateAcctLocTMsg);
                if (!RTNCD_NORMAL.equals(updateAcctLocTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"ACCT_LOC" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                // Change the Account Location associated with other Account to Inactive
                S21SsmEZDResult resOtherAcctLoc = NMAL6700Query.getInstance().getOtherAcctLoc(updateAcctLocTMsg.locNum.getValue());
                if (resAcctLoc.isCodeNormal()) {
                    List<Map<String, Object>> resultOtherAcctLocList = (List<Map<String, Object>>) resOtherAcctLoc.getResultObject();
                    for (int j = 0; j < resultOtherAcctLocList.size(); j++) {
                        Map<String, Object> resultOtherAcctLoc = resultOtherAcctLocList.get(j);
                        BigDecimal otherAcctLocPk = (BigDecimal) resultOtherAcctLoc.get("ACCT_LOC_PK");
                        ACCT_LOCTMsg updateOtherAcctLocTMsg = NMAL6700CommonLogic.findAcctLocForUpdate(glblCmpyCd, otherAcctLocPk);
                        if (updateOtherAcctLocTMsg == null) {
                            String[] args = {"ACCT_LOC" };
                            bizMsg.setMessageInfo(NMAM8111E, args);
                            return false;
                        }
                        String slsDt = ZYPDateUtil.getSalesDate();
                        ZYPEZDItemValueSetter.setValue(updateOtherAcctLocTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
                        ZYPEZDItemValueSetter.setValue(updateOtherAcctLocTMsg.effFromDt, ZYPDateUtil.addDays(slsDt, -1));
                        ZYPEZDItemValueSetter.setValue(updateOtherAcctLocTMsg.effThruDt, ZYPDateUtil.addDays(slsDt, -1));
                        S21FastTBLAccessor.update(updateOtherAcctLocTMsg);
                        if (!RTNCD_NORMAL.equals(updateOtherAcctLocTMsg.getReturnCode())) {
                            bizMsg.setMessageInfo(NMAM0177E, new String[] {"ACCT_LOC" });
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean updateBillToCustFromDsBillToCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        // Update BILL_TO_CUST
        S21SsmEZDResult resBillToCust = NMAL6700Query.getInstance().getBillToCustNotLookRgtnStsCd(bizMsg.dsAcctNum_H1.getValue());
        if (!resBillToCust.isCodeNormal()) {
            // Not error
            return true;
        }
        List<Map<String, Object>> resultBillToCustList = (List<Map<String, Object>>) resBillToCust.getResultObject();
        for (int i = 0; i < resultBillToCustList.size(); i++) {
            Map<String, Object> resultBillToCust = resultBillToCustList.get(i);
            BigDecimal billToCustPk = (BigDecimal) resultBillToCust.get("BILL_TO_CUST_PK");
            BILL_TO_CUSTTMsg updateBillToCustTMsg = NMAL6700CommonLogic.findBillToCustForUpdate(glblCmpyCd, billToCustPk);
            if (updateBillToCustTMsg == null) {
                String[] args = {"BILL_TO_CUST" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            BILL_TO_CUSTTMsg beforeUpdateBillToCustTMsg = new BILL_TO_CUSTTMsg();
            BILL_TO_CUSTTMsg.copy(updateBillToCustTMsg, null, beforeUpdateBillToCustTMsg, null);
            if (!setBillToCustNotLookRgtnStsCdData(updateBillToCustTMsg, bizMsg, sharedMsg)) {
                return false;
            }

            // 2023/11/06 QC#61924 Add Start
            if (!beforeUpdateBillToCustTMsg.deacNewTrxFlg.equals(bizMsg.xxChkBox_H3.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
                    ZYPEZDItemValueSetter.setValue(updateBillToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(updateBillToCustTMsg.deacNewTrxDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                } else {
                    ZYPEZDItemValueSetter.setValue(updateBillToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                    updateBillToCustTMsg.deacNewTrxDt.clear();
                }
            }
            // 2023/11/06 QC#61924 Add End

            //Change check
            if (changeFieldsCheckForBillNotLookRgtnStsCd(updateBillToCustTMsg, beforeUpdateBillToCustTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateBillToCustTMsg);
                if (!RTNCD_NORMAL.equals(updateBillToCustTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        return true;
    }

    private boolean updateShipToCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        // Update SHIP_TO_CUST
        S21SsmEZDResult resShipToCust = NMAL6700Query.getInstance().getShipToCust(bizMsg.dsAcctNum_H1.getValue());
        if (!resShipToCust.isCodeNormal()) {
            // Not error
            return true;
        }
        List<Map<String, Object>> resultShipToCustList = (List<Map<String, Object>>) resShipToCust.getResultObject();
        for (int i = 0; i < resultShipToCustList.size(); i++) {
            Map<String, Object> resultShipToCust = resultShipToCustList.get(i);
            BigDecimal shipToCustPk = (BigDecimal) resultShipToCust.get("SHIP_TO_CUST_PK");
            SHIP_TO_CUSTTMsg updateShipToCustTMsg = NMAL6700CommonLogic.findShipToCustForUpdate(glblCmpyCd, shipToCustPk);
            if (updateShipToCustTMsg == null) {
                String[] args = {"SHIP_TO_CUST" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            SHIP_TO_CUSTTMsg beforeUpdateShipToCustTMsg = new SHIP_TO_CUSTTMsg();
            SHIP_TO_CUSTTMsg.copy(updateShipToCustTMsg, null, beforeUpdateShipToCustTMsg, null);

            if (!setShipToCustData(updateShipToCustTMsg, bizMsg, sharedMsg)) {
                return false;
            }

            // 2023/11/06 QC#61924 Add Start
            if (!beforeUpdateShipToCustTMsg.deacNewTrxFlg.equals(bizMsg.xxChkBox_H3.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue()) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
                    ZYPEZDItemValueSetter.setValue(updateShipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(updateShipToCustTMsg.deacNewTrxDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                } else {
                    ZYPEZDItemValueSetter.setValue(updateShipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                    updateShipToCustTMsg.deacNewTrxDt.clear();
                }
            }
            // 2023/11/06 QC#61924 Add End

            //Change check
            if (changeFieldsCheckForShip(updateShipToCustTMsg, beforeUpdateShipToCustTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateShipToCustTMsg);
                if (!RTNCD_NORMAL.equals(updateShipToCustTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"SHIP_TO_CUST" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        return true;
    }

    private boolean insertDsCustSpclMsg(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        // Insert DS_CUST_SPCL_MSG
        DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg = new DS_CUST_SPCL_MSGTMsg();

        if (!setDsCustSpclMsgData(dsCustSpclMsgTMsg, bizMsg)) {
            return false;
        }

        S21FastTBLAccessor.create(dsCustSpclMsgTMsg);
        if (!RTNCD_NORMAL.equals(dsCustSpclMsgTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_CUST_SPCL_MSG" });
            return false;
        }
        bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        return true;
    }

    private Map<String, Object> getPrimaryInfo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        String locNum = null;
        for (int i = 0; i < sharedMsg.A.getValidCount(); i++) {
            NMAL6700_ASMsg smsg = (NMAL6700_ASMsg) sharedMsg.A.get(i);
            if (ZYPCommonFunc.hasValue(smsg.xxChkBox_AP)) {
                locNum = smsg.locNum_A1.getValue();
                break;
            }
        }
        if (!ZYPCommonFunc.hasValue(locNum)) {
            return null;
        }

        S21SsmEZDResult res = NMAL6700Query.getInstance().getPrimaryLocation(locNum);
        if (!res.isCodeNormal()) {
            String[] args = {"PTY_LOC_WRK, BILL_TO_CUST" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return null;
        }
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) res.getResultObject();
        if (resultList.size() != 1) {
            String[] args = {"PTY_LOC_WRK, BILL_TO_CUST" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return null;
        }
        return resultList.get(0);
    }

    private boolean updatePrinCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd, Map<String, Object> primaryInfo) {
        // Update PRIN_CUST
        PRIN_CUSTTMsg updatePrinCustTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, bizMsg.prinCustPk_H2.getValue());
        if (updatePrinCustTMsg == null) {
            BigDecimal prinCustPk = this.getPrinCustPkAftDelPrimLocFlg(bizMsg);
            if (prinCustPk != null) {
                updatePrinCustTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, prinCustPk);
                if (updatePrinCustTMsg == null) {
                    String[] args = {"PRIN_CUST" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
            }
        }

        PRIN_CUSTTMsg beforeUpdatePrinCustTMsg = new PRIN_CUSTTMsg();
        PRIN_CUSTTMsg.copy(updatePrinCustTMsg, null, beforeUpdatePrinCustTMsg, null);
        if (!setPrinCustData(bizMsg, updatePrinCustTMsg, primaryInfo)) {
            return false;
        }
        //Change check
        if (changeFieldsCheckForPrinCust(updatePrinCustTMsg, beforeUpdatePrinCustTMsg)) {
            //Change has been made.
            S21FastTBLAccessor.update(updatePrinCustTMsg);
            if (!RTNCD_NORMAL.equals(updatePrinCustTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"PRIN_CUST" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

        }
        return true;
    }

//    private boolean updateDsAcctProsFromPrinCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd, Map<String, Object> primaryInfo) {
//        // Update DS_ACCT_PROS
//        DS_ACCT_PROSTMsg updateDsAcctProsTMsg = NMAL6700CommonLogic.findDsAcctProsForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
//        if (updateDsAcctProsTMsg == null) {
//            String[] args = {"DS_ACCT_PROS" };
//            bizMsg.setMessageInfo(NMAM8111E, args);
//            return false;
//        }
//        DS_ACCT_PROSTMsg beforeUpdateDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
//        DS_ACCT_PROSTMsg.copy(updateDsAcctProsTMsg, null, beforeUpdateDsAcctProsTMsg, null);
//        if (!setDsAcctProsData(updateDsAcctProsTMsg, primaryInfo)) {
//            return false;
//        }
//        //Change check
//        if (changeFieldsCheckForProsFromPrinCust(updateDsAcctProsTMsg, beforeUpdateDsAcctProsTMsg)) {
//            //Change has been made.
//            S21FastTBLAccessor.update(updateDsAcctProsTMsg);
//            if (!RTNCD_NORMAL.equals(updateDsAcctProsTMsg.getReturnCode())) {
//                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_PROS" });
//                return false;
//            }
//            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//
//        }
//        return true;
//    }

    private boolean updateSellToCustFromPrinCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd, Map<String, Object> primaryInfo) {
        // Update SELL_TO_CUST
        SELL_TO_CUSTTMsg updateSellToCustTMsg = NMAL6700CommonLogic.findSellToCustForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
        if (updateSellToCustTMsg == null) {
            String[] args = {"SELL_TO_CUST" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        SELL_TO_CUSTTMsg beforeUpdateSellToCustTMsg = new SELL_TO_CUSTTMsg();
        SELL_TO_CUSTTMsg.copy(updateSellToCustTMsg, null, beforeUpdateSellToCustTMsg, null);
        if (!setSellToCustData(updateSellToCustTMsg, primaryInfo)) {
            return false;
        }
        //Change check
        if (changeFieldsCheckForCustFromPrinCust(updateSellToCustTMsg, beforeUpdateSellToCustTMsg)) {
            //Change has been made.
            S21FastTBLAccessor.update(updateSellToCustTMsg);
            if (!RTNCD_NORMAL.equals(updateSellToCustTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"SELL_TO_CUST" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

        }
        return true;
    }

    private void createAcctInfo(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H3.getValue())) {
            SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();

            if (!setSellToCustData(sellToCustTMsg, bizMsg, true)) {
                return;
            }

            S21FastTBLAccessor.create(sellToCustTMsg);
            if (!RTNCD_NORMAL.equals(sellToCustTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_ACCT_CUST" });
                return;
            }

            if (!createSellToCustRelatedInfo(bizMsg, glblCmpyCd, sellToCustTMsg.sellToCustCd.getValue())) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustPk_H1, sellToCustTMsg.sellToCustPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H1, sellToCustTMsg.sellToCustCd);
        } else {
            DS_ACCT_PROSTMsg dsAcctProsTMsg = new DS_ACCT_PROSTMsg();

            if (!setDsAcctProsData(dsAcctProsTMsg, bizMsg, true)) {
                return;
            }

            S21FastTBLAccessor.create(dsAcctProsTMsg);
            if (!RTNCD_NORMAL.equals(dsAcctProsTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_ACCT_PROS" });
                return;
            }

            if (!createDsAcctProsRelatedInfo(bizMsg, glblCmpyCd, dsAcctProsTMsg.dsAcctNum.getValue())) {
                return;
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustPk_H1, dsAcctProsTMsg.dsAcctProsPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H1, dsAcctProsTMsg.dsAcctNum);

        }
        bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

    }

    private boolean createSellToCustRelatedInfo(NMAL6700CMsg bizMsg, String glblCmpyCd, String dsAcctNum) {

        PTY_LOC_WRKTMsg ptyLocWrkTMsg = createNewPtyLocWrkTMsg(bizMsg, glblCmpyCd);
        S21FastTBLAccessor.create(ptyLocWrkTMsg);
        if (!RTNCD_NORMAL.equals(ptyLocWrkTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {" PTY_LOC_WRK" });
            return false;
        }

        CMPYTMsg cmpyTMsg = createNewCmpyTMsg(bizMsg, glblCmpyCd);
        S21FastTBLAccessor.create(cmpyTMsg);
        if (!RTNCD_NORMAL.equals(cmpyTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {" CMPY" });
            return false;
        }

        PRIN_CUSTTMsg prinCustTMsg = createNewPrinCustTMsg(bizMsg, glblCmpyCd);
        S21FastTBLAccessor.create(prinCustTMsg);
        if (!RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {"PRIN_CUST" });
            return false;
        }

        return true;
    }

    private boolean createDsAcctProsRelatedInfo(NMAL6700CMsg bizMsg, String glblCmpyCd, String dsAcctNum) {

        PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTMsg = createNewProsPtyLocWrkTMsg(bizMsg, glblCmpyCd);
        S21FastTBLAccessor.create(prosPtyLocWrkTMsg);
        if (!RTNCD_NORMAL.equals(prosPtyLocWrkTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {" PTY_LOC_WRK" });
            return false;
        }

        CMPYTMsg cmpyTMsg = createNewCmpyTMsg(bizMsg, glblCmpyCd);
        S21FastTBLAccessor.create(cmpyTMsg);
        if (!RTNCD_NORMAL.equals(cmpyTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {" CMPY" });
            return false;
        }

        return true;
    }

    private DS_ACCT_CR_PRFLTMsg createNewDsAcctCrPrflTMsg(NMAL6700CMsg bizMsg, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTMsg = new DS_ACCT_CR_PRFLTMsg();

        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ccyCd, bizMsg.ccyCd_U3);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crLimitAmt, bizMsg.crLimitAmt_U1);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crRiskClsCd, bizMsg.crRiskClsCd_U3);
        // START 2018/01/25 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.contrCrRiskClsCd, bizMsg.contrCrRiskClsCd_U3);
        // END   2018/01/25 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.invDtChkReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crRvwDtChkReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.psnGtdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.inProcAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.arBalAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crLimitChngDt, slsDt);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.sesnCrLimitAmt, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crChkReqTpCd, bizMsg.crChkReqTpCd_U3);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ovdWsFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ovdPrtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.srCrCardFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.custCrRtgCd, bizMsg.custCrRtgCd_U3);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.ovrdPmtTermFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ovrdPmtTermFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ovrdPmtTermFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd_U3);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.cashOrCcReqFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.cashOrCcReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.cashOrCcReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.custHardHldFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.custHardHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.custHardHldFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2018/04/16 E.Kameishi [QC#21037, ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.autoCashHrchCd, bizMsg.autoCashHrchCd_U3);
        // END 2018/04/16 E.Kameishi [QC#21037, ADD]

        return dsAcctCrPrflTMsg;
    }

    private PTY_LOC_WRKTMsg createNewPtyLocWrkTMsg(NMAL6700CMsg bizMsg, String glblCmpyCd) {

        PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ptyLocPk, bizMsg.ptyLocPk_H1);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.cmpyPk, bizMsg.cmpyPk_H1);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dbaNm, bizMsg.dbaNm_H1);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.apvlStsCd, APVL_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.locNm, getLocNm(bizMsg));
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/07/04 [QC#25339, ADD]
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsAcctNmChngTpCd, DUNS_CHNG_TP.UPDATED);
        // END   2018/07/04 [QC#25339, ADD]

        return ptyLocWrkTMsg;
    }

    private PROS_PTY_LOC_WRKTMsg createNewProsPtyLocWrkTMsg(NMAL6700CMsg bizMsg, String glblCmpyCd) {

        PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTMsg = new PROS_PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.ptyLocPk, bizMsg.ptyLocPk_H1);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.cmpyPk, bizMsg.cmpyPk_H1);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.dbaNm, bizMsg.dbaNm_H1);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.apvlStsCd, APVL_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.locNm, getLocNm(bizMsg));
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/07/04 [QC#25339, ADD]
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(prosPtyLocWrkTMsg.dunsAcctNmChngTpCd, DUNS_CHNG_TP.UPDATED);
        // END   2018/07/04 [QC#25339, ADD]

        return prosPtyLocWrkTMsg;
    }

    private CMPYTMsg createNewCmpyTMsg(NMAL6700CMsg bizMsg, String glblCmpyCd) {

        CMPYTMsg cmpyTMsg = new CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(cmpyTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmpyTMsg.cmpyPk, bizMsg.cmpyPk_H1);
        ZYPEZDItemValueSetter.setValue(cmpyTMsg.dbaNm, bizMsg.dbaNm_H1);
        ZYPEZDItemValueSetter.setValue(cmpyTMsg.cmpyNm, S21StringUtil.subStringByLength(bizMsg.dsAcctNm_H1.getValue(), 0, 60));

        return cmpyTMsg;
    }

    private PRIN_CUSTTMsg createNewPrinCustTMsg(NMAL6700CMsg bizMsg, String glblCmpyCd) {

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        PRIN_CUSTTMsg prinCustTMsg = new PRIN_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustPk,  bizMsg.prinCustPk_H2);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dbaNm, bizMsg.dbaNm_H1);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.taxOrgClsCd, TAX_ORG_CLS.OTHERS);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.ptyLocPk, bizMsg.ptyLocPk_H1);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.locRoleTpCd, LOC_ROLE_TP.CUSTOMER_PRINCIPAL);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.effFromDt, slsDt);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.cmpyPk, bizMsg.cmpyPk_H1);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dsAcctItrlFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(prinCustTMsg.bizRelnTpCd, BIZ_RELN_TP.INTERNCUST);
        } else {
            ZYPEZDItemValueSetter.setValue(prinCustTMsg.bizRelnTpCd, BIZ_RELN_TP.EXTERNCUST);
        }
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.coaAfflCd, bizMsg.coaAfflCd_H1);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustTpCd, PRIN_CUST_TP.OTHER);

        ZYPEZDItemValueSetter.setValue(bizMsg.prinCustPk_H2, prinCustTMsg.prinCustPk);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.locNm, getLocNm(bizMsg));

        ZYPEZDItemValueSetter.setValue(prinCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);

        return prinCustTMsg;
    }

    private boolean setSellToCustData(SELL_TO_CUSTTMsg sellToCustTMsg, NMAL6700CMsg bizMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SELL_TO_CUST_SQ));
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustCd, ZYPExtnNumbering.getUniqueID(glblCmpyCd, "SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.prinCustPk,  ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRIN_CUST_SQ));
            ZYPEZDItemValueSetter.setValue(bizMsg.prinCustPk_H2, sellToCustTMsg.prinCustPk);
            BigDecimal cmpyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CMPY_SQ);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.cmpyPk, cmpyPk);
            bizMsg.cmpyPk_H1.setValue(cmpyPk);

            BigDecimal ptyLocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PTY_LOC_WRK_SQ);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ptyLocPk, ptyLocPk);
            bizMsg.ptyLocPk_H1.setValue(ptyLocPk);

            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
            // START 2018/01/25 [QC#22095, ADD]
            //ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
            // END   2018/01/25 [QC#22095, ADD]
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dunFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.locRoleTpCd, LOC_ROLE_TP.SELL_TO);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.locGrpCd, LOC_GRP.CUSTOMER);
            String salesDate = ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode(), BUSINESS_ID);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.effFromDt, salesDate);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.prinCustTpCd, PRIN_CUST_TP.OTHER);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.usrDlrTpCd, USR_DLR_TP.USER);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.canRcvInvAtSetCmptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
            // 2023/11/06 QC#61924 Add Start
            if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
            } else {
                if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxDt, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
                }
            }
            // 2023/11/06 QC#61924 Add End
        }

        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);
        // 2018/08/17 QC#27786 Add Start
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.locNm, getLocNm(bizMsg));
        // 2018/08/17 QC#27786 Add End
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctTpCd, bizMsg.dsAcctTpCd_H3);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctItrlFlg, bizMsg.dsAcctItrlFlg_H3);
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dsAcctItrlFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.bizRelnTpCd, BIZ_RELN_TP.INTERNCUST);
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.bizRelnTpCd, BIZ_RELN_TP.EXTERNCUST);
        }
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctInacRsnCd, bizMsg.dsAcctInacRsnCd_H3);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctClsCd, bizMsg.dsAcctClsCd_H3);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.coaAfflCd, bizMsg.coaAfflCd_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.coaChCd, bizMsg.coaChCd_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctDlrCd, bizMsg.dsAcctDlrCd_H3);

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(sellToCustTMsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(sellToCustTMsg.effFromDt, sellToCustTMsg.effThruDt);
            } else if (!ZYPCommonFunc.hasValue(sellToCustTMsg.rgtnStsCd)) {
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(sellToCustTMsg.rgtnStsCd.getValue())) {
                sellToCustTMsg.effThruDt.clear();
            }
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
        }

        // 2023/11/06 QC#61924 Add Start
        if (hasDeactivateAccountAndLocation(sellToCustTMsg, bizMsg, glblCmpyCd)) {
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        // 2023/11/06 QC#61924 Add End

        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctLegalNm, bizMsg.dsAcctLegalNm_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dbaNm, bizMsg.dbaNm_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctDunsNm, bizMsg.dsAcctDunsNm_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctAltNm, bizMsg.dsAcctAltNm_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsXtrnlRefTxt, bizMsg.dsXtrnlRefTxt_H1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsDataSrcTxt, bizMsg.dsDataSrcTxt_H1);

        //Transaction Tab
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.coaCcCd, bizMsg.coaCcCd_F1);

        //Collections
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.arStmtFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.arStmtFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2018/01/25 [QC#22095, ADD]
        //if (ZYPConstant.FLG_ON_Y.equals(bizMsg.sendCrBalStmtFlg_U1.getValue())) {
        //    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_ON_Y);
        //} else {
        //    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
        //}
        // END   2018/01/25 [QC#22095, ADD]
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.arStmtIssDay, NMAL6700CommonLogic.getArStmtIssDay(getGlobalCompanyCode(), bizMsg.arStmtIssCycleCd_U3.getValue()));
        // START 2018/01/16 [QC#21734, MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunFlg_U1.getValue())) {
        //     ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dunFlg, ZYPConstant.FLG_ON_Y);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dunFlg, ZYPConstant.FLG_OFF_N);
        // }
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dunFlg, ZYPConstant.FLG_ON_Y);
        // END   2018/01/16 [QC#21734, MOD]
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.cltCustTpCd, bizMsg.cltCustTpCd_U1);
        NMAL6700CommonLogic.getCltPtfoNm(bizMsg);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.cltPtfoPk, bizMsg.cltPtfoPk_U1);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsCltAcctStsCd, bizMsg.dsCltAcctStsCd_U3);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.lateFeeFlg_U1.getValue())) {
            sellToCustTMsg.lateFeeFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            sellToCustTMsg.lateFeeFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.lateFeeAmt_U1)) {
            sellToCustTMsg.lateFeeAmt.setValue(bizMsg.lateFeeAmt_U1.getValue());
        } else {
            sellToCustTMsg.lateFeeAmt.setValue(BigDecimal.ZERO);
        }
        // Del Start 2018/07/30 QC#27222
//        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsCustTaxCd, bizMsg.dsCustTaxCd_U1);
//        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsCustTaxCalcCd, bizMsg.dsCustTaxCalcCd_U3);
        // Del End 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsTaxPrntTpCd, bizMsg.dsTaxPrntTpCd_U3);
        // Del Start 2018/07/30 QC#27222
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dsTaxExemFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsExemExprDt, bizMsg.dsExemExprDt_U1);
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsTaxGrpExemCd, bizMsg.dsTaxGrpExemCd_U3);

        // START 2018/01/16 [QC#23882, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.sendCrBalStmtFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        // END   2018/01/16 [QC#23882, ADD]

        // Invoice/Billing
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.defBaseTpCd, bizMsg.defBaseTpCd_V3);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.defBaseCycleCd, bizMsg.defBaseCycleCd_V3);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.defUsgTpCd, bizMsg.defUsgTpCd_V3);
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.defUsgCycleCd, bizMsg.defUsgCycleCd_V3);
        // START 2022/03/22 [QC#59683, ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.dsInvTgtrTpCd_V1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTgtrTpCd_V1, DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY);
        }
        ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsInvTgtrTpCd, bizMsg.dsInvTgtrTpCd_V1);
        // END   2022/03/22 [QC#59683, ADD]
        // START 2022/03/22 [QC#59683, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dsBillTgtrFlg_V1.getValue())) {
        DS_INV_TGTR_TPTMsg dsInvTgtrTp = getDsInvTgtrTp(bizMsg.dsInvTgtrTpCd_V1.getValue());
        if (dsInvTgtrTp != null) {
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsBillTgtrFlg, ZYPConstant.FLG_ON_Y);
            String invSeptBaseUsgFlg = dsInvTgtrTp.invSeptBaseUsgFlg.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(invSeptBaseUsgFlg)) {
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsBillTgtrFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2022/03/22 [QC#59683, MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    private boolean setPrinCustForRgtnStsCdData(NMAL6700CMsg bizMsg, PRIN_CUSTTMsg tmsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tmsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(tmsg.effFromDt, tmsg.effThruDt);

            } else if (!ZYPCommonFunc.hasValue(tmsg.rgtnStsCd)) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(tmsg.rgtnStsCd.getValue())) {
                tmsg.effThruDt.clear();
            }
            ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        }
        return true;
    }

    private boolean setPrinCustData(NMAL6700CMsg bizMsg, PRIN_CUSTTMsg updatePrinCustTMsg, Map<String, Object> primaryInfo) {
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.firstLineAddr, (String) primaryInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.scdLineAddr, (String) primaryInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.thirdLineAddr, (String) primaryInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.frthLineAddr, (String) primaryInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.ctyAddr, (String) primaryInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.cntyPk, (BigDecimal) primaryInfo.get("CNTY_PK"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.provNm, (String) primaryInfo.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.stCd, (String) primaryInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.postCd, (String) primaryInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.ctryCd, (String) primaryInfo.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.locNum, (String) primaryInfo.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.locNm, (String) primaryInfo.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.dunsNum, (String) primaryInfo.get("DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.rgtnStsCd, (String) primaryInfo.get("RGTN_STS_CD"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.ptyLocPk, (BigDecimal) primaryInfo.get("PTY_LOC_PK"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.effFromDt, (String) primaryInfo.get("EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.effThruDt, (String) primaryInfo.get("EFF_THRU_DT"));
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dsAcctItrlFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.bizRelnTpCd, BIZ_RELN_TP.INTERNCUST);
        } else {
            ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.bizRelnTpCd, BIZ_RELN_TP.EXTERNCUST);
        }
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.geoCd, (String) primaryInfo.get("GEO_CD"));
        ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.telNum, (String) primaryInfo.get("TEL_NUM"));
        return true;
    }

    private boolean setSellToCustData(SELL_TO_CUSTTMsg tmsg, Map<String, Object> primaryInfo) {
        ZYPEZDItemValueSetter.setValue(tmsg.firstLineAddr, (String) primaryInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tmsg.scdLineAddr, (String) primaryInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tmsg.thirdLineAddr, (String) primaryInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tmsg.frthLineAddr, (String) primaryInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(tmsg.ctyAddr, (String) primaryInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(tmsg.cntyPk, (BigDecimal) primaryInfo.get("CNTY_PK"));
        ZYPEZDItemValueSetter.setValue(tmsg.provNm, (String) primaryInfo.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(tmsg.stCd, (String) primaryInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(tmsg.postCd, (String) primaryInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(tmsg.ctryCd, (String) primaryInfo.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(tmsg.locNum, (String) primaryInfo.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(tmsg.locNm, (String) primaryInfo.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(tmsg.ptyLocPk, (BigDecimal) primaryInfo.get("PTY_LOC_PK"));
        ZYPEZDItemValueSetter.setValue(tmsg.telNum, (String) primaryInfo.get("TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(tmsg.geoCd, (String) primaryInfo.get("GEO_CD"));
        return true;
    }

//    private boolean setDsAcctProsData(DS_ACCT_PROSTMsg tmsg, Map<String, Object> primaryInfo) {
//        ZYPEZDItemValueSetter.setValue(tmsg.firstLineAddr, (String) primaryInfo.get("FIRST_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(tmsg.scdLineAddr, (String) primaryInfo.get("SCD_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(tmsg.thirdLineAddr, (String) primaryInfo.get("THIRD_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(tmsg.frthLineAddr, (String) primaryInfo.get("FRTH_LINE_ADDR"));
//        ZYPEZDItemValueSetter.setValue(tmsg.ctyAddr, (String) primaryInfo.get("CTY_ADDR"));
//        ZYPEZDItemValueSetter.setValue(tmsg.cntyPk, (BigDecimal) primaryInfo.get("CNTY_PK"));
//        ZYPEZDItemValueSetter.setValue(tmsg.provNm, (String) primaryInfo.get("PROV_NM"));
//        ZYPEZDItemValueSetter.setValue(tmsg.stCd, (String) primaryInfo.get("ST_CD"));
//        ZYPEZDItemValueSetter.setValue(tmsg.postCd, (String) primaryInfo.get("POST_CD"));
//        ZYPEZDItemValueSetter.setValue(tmsg.ctryCd, (String) primaryInfo.get("CTRY_CD"));
//        ZYPEZDItemValueSetter.setValue(tmsg.locNum, (String) primaryInfo.get("LOC_NUM"));
//        ZYPEZDItemValueSetter.setValue(tmsg.locNm, (String) primaryInfo.get("LOC_NM"));
//        ZYPEZDItemValueSetter.setValue(tmsg.ptyLocPk, (BigDecimal) primaryInfo.get("PTY_LOC_PK"));
//        ZYPEZDItemValueSetter.setValue(tmsg.telNum, (String) primaryInfo.get("TEL_NUM"));
//        ZYPEZDItemValueSetter.setValue(tmsg.geoCd, (String) primaryInfo.get("GEO_CD"));
//        return true;
//    }

    private boolean setPtyLocWrkData(PTY_LOC_WRKTMsg tmsg, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tmsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(tmsg.effFromDt, tmsg.effThruDt);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(tmsg.rgtnStsCd.getValue()) && tmsg.effThruDt.getValue().equals(sharedMsg.effThruDt_H1.getValue())) {
                tmsg.effThruDt.clear();
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        return true;
    }

    private boolean setProsPtyLocWrkData(PROS_PTY_LOC_WRKTMsg tmsg, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tmsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(tmsg.effFromDt, tmsg.effThruDt);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(tmsg.rgtnStsCd.getValue()) && tmsg.effThruDt.getValue().equals(sharedMsg.effThruDt_H1.getValue())) {
                tmsg.effThruDt.clear();
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        return true;
    }

    private boolean setCmpyData(CMPYTMsg tmsg, NMAL6700CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(tmsg.dbaNm, bizMsg.dbaNm_H1.getValue());
        String dsAcctNm = bizMsg.dsAcctNm_H1.getValue();
        if (dsAcctNm != null && dsAcctNm.length() > COL_LOC_NM_MAX_DIGIT_LENGTH) {
            ZYPEZDItemValueSetter.setValue(tmsg.cmpyNm, dsAcctNm.substring(0, COL_LOC_NM_MAX_DIGIT_LENGTH));
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.cmpyNm, dsAcctNm);
        }
        return true;
    }

    private boolean setBillToCustData(BILL_TO_CUSTTMsg tmsg, NMAL6700CMsg bizMsg) {
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dsAcctItrlFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(tmsg.bizRelnTpCd, BIZ_RELN_TP.INTERNCUST);
        } else {
            ZYPEZDItemValueSetter.setValue(tmsg.bizRelnTpCd, BIZ_RELN_TP.EXTERNCUST);
        }
        return true;
    }

    private boolean setAcctLocData(ACCT_LOCTMsg tmsg, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tmsg.rgtnStsCd.getValue())
                    || RGTN_STS.PENDING_COMPLETION.equals(tmsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(tmsg.effFromDt, tmsg.effThruDt);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(tmsg.rgtnStsCd.getValue()) && tmsg.effThruDt.getValue().equals(sharedMsg.effThruDt_H1.getValue())) {
                tmsg.effThruDt.clear();
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        return true;
    }

    private boolean setBillToCustNotLookRgtnStsCdData(BILL_TO_CUSTTMsg tmsg, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tmsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(tmsg.effFromDt, tmsg.effThruDt);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(tmsg.rgtnStsCd.getValue()) && tmsg.effThruDt.getValue().equals(sharedMsg.effThruDt_H1.getValue())) {
                tmsg.effThruDt.clear();
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        return true;

    }

    private boolean setShipToCustData(SHIP_TO_CUSTTMsg tmsg, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tmsg.rgtnStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.TERMINATED);
                setEffectiveDateForTermination(tmsg.effFromDt, tmsg.effThruDt);
            }
        } else {
            if (RGTN_STS.TERMINATED.equals(tmsg.rgtnStsCd.getValue()) && tmsg.effThruDt.getValue().equals(sharedMsg.effThruDt_H1.getValue())) {
                tmsg.effThruDt.clear();
                ZYPEZDItemValueSetter.setValue(tmsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        return true;
    }

    private boolean setDsCustSpclMsgData(DS_CUST_SPCL_MSGTMsg tmsg, NMAL6700CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tmsg.dsCustSpclMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_SPCL_MSG_SQ));
        ZYPEZDItemValueSetter.setValue(tmsg.dsAcctNum, bizMsg.dsAcctNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.lineBizTpCd, LINE_BIZ_TP.ALL);
        ZYPEZDItemValueSetter.setValue(tmsg.dsBizAreaCd, DS_BIZ_AREA.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(tmsg.dsCustMsgTpCd, DS_CUST_MSG_TP.NOTE);
        String dsCustMsgTxt = null;
        for (int i = 0; i < bizMsg.dsAcctInacRsnCd_H1.length(); i++) {
            String dsAcctInacRsnCd = ((EZDCStringItem) bizMsg.dsAcctInacRsnCd_H1.get(i)).getValue();
            if (ZYPCommonFunc.hasValue(bizMsg.dsAcctInacRsnCd_H3) && bizMsg.dsAcctInacRsnCd_H3.getValue().equals(dsAcctInacRsnCd)) {
                dsCustMsgTxt = ((EZDCStringItem) bizMsg.dsAcctInacRsnNm_H2.get(i)).getValue();
                ZYPEZDItemValueSetter.setValue(tmsg.dsCustMsgTxt, dsCustMsgTxt);
            }
        }
        ZYPEZDItemValueSetter.setValue(tmsg.dsPrintOnInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tmsg.custEffLvlCd, CUST_EFF_LVL.ACCOUNT_ONLY);
        ZYPEZDItemValueSetter.setValue(tmsg.effFromDt, ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode(), BUSINESS_ID));
        return true;
    }

    private boolean setDsAcctProsData(DS_ACCT_PROSTMsg dsAcctProsTMsg, NMAL6700CMsg bizMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctProsPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SELL_TO_CUST_SQ));
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctNum, ZYPExtnNumbering.getUniqueID(glblCmpyCd, "SELL_TO_CUST_CD"));

            BigDecimal cmpyPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CMPY_SQ);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.cmpyPk, cmpyPk);
            bizMsg.cmpyPk_H1.setValue(cmpyPk);

            BigDecimal ptyLocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PTY_LOC_WRK_SQ);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.ptyLocPk, ptyLocPk);
            bizMsg.ptyLocPk_H1.setValue(ptyLocPk);

            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
            // START 2018/01/25 [QC#22095, ADD]
            //ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
            // END   2018/01/25 [QC#22095, ADD]
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dunFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.locRoleTpCd, LOC_ROLE_TP.SELL_TO);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.locGrpCd, LOC_GRP.CUSTOMER);
            String salesDate = ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode(), BUSINESS_ID);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.effFromDt, salesDate);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.prinCustTpCd, PRIN_CUST_TP.OTHER);
        }

        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctTpCd, bizMsg.dsAcctTpCd_H3);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctItrlFlg, bizMsg.dsAcctItrlFlg_H3);
        if (ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.dsAcctItrlFlg_H3.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizRelnTpCd, BIZ_RELN_TP.INTERNCUST);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizRelnTpCd, BIZ_RELN_TP.EXTERNCUST);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctInacRsnCd, bizMsg.dsAcctInacRsnCd_H3);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctClsCd, bizMsg.dsAcctClsCd_H3);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.coaAfflCd, bizMsg.coaAfflCd_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.coaChCd, bizMsg.coaChCd_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctDlrCd, bizMsg.dsAcctDlrCd_H3);

        if (ZYPConstant.FLG_ON_Y.equals(dsAcctProsTMsg.dsAcctActvCustFlg.getValue())
                && !ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
            setEffectiveDateForTermination(dsAcctProsTMsg.effFromDt, dsAcctProsTMsg.effThruDt);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.effThruDt, "");
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctLegalNm, bizMsg.dsAcctLegalNm_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dbaNm, bizMsg.dbaNm_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctDunsNm, bizMsg.dsAcctDunsNm_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctAltNm, bizMsg.dsAcctAltNm_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsXtrnlRefTxt, bizMsg.dsXtrnlRefTxt_H1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsDataSrcTxt, bizMsg.dsDataSrcTxt_H1);

        //Transaction Tab
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.coaCcCd, bizMsg.coaCcCd_F1);

        //Collections
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.arStmtFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.arStmtFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.arStmtIssDay, NMAL6700CommonLogic.getArStmtIssDay(getGlobalCompanyCode(), bizMsg.arStmtIssCycleCd_U3.getValue()));
        // START 2018/01/16 [QC#21734, MOD]
        // if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunFlg_U1.getValue())) {
        //     ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dunFlg, ZYPConstant.FLG_ON_Y);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dunFlg, ZYPConstant.FLG_OFF_N);
        // }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dunFlg, ZYPConstant.FLG_ON_Y);
        // END   2018/01/16 [QC#21734, MOD]
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.cltCustTpCd, bizMsg.cltCustTpCd_U1);
        NMAL6700CommonLogic.getCltPtfoNm(bizMsg);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.cltPtfoPk, bizMsg.cltPtfoPk_U1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsCltAcctStsCd, bizMsg.dsCltAcctStsCd_U3);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.lateFeeFlg_U1.getValue())) {
            dsAcctProsTMsg.lateFeeFlg.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            dsAcctProsTMsg.lateFeeFlg.setValue(ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.lateFeeAmt_U1)) {
            dsAcctProsTMsg.lateFeeAmt.setValue(bizMsg.lateFeeAmt_U1.getValue());
        } else {
            dsAcctProsTMsg.lateFeeAmt.setValue(BigDecimal.ZERO);
        }
        // Del Start 2018/07/30 QC#27222
//        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsCustTaxCd, bizMsg.dsCustTaxCd_U1);
//        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsCustTaxCalcCd, bizMsg.dsCustTaxCalcCd_U3);
        // Del End 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsTaxPrntTpCd, bizMsg.dsTaxPrntTpCd_U3);
        // Del Start 2018/07/30 QC#27222
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dsTaxExemFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsTaxExemFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsExemExprDt, bizMsg.dsExemExprDt_U1);
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/07/30 QC#27222
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsTaxGrpExemCd, bizMsg.dsTaxGrpExemCd_U3);

        // START 2018/01/16 [QC#23882, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.sendCrBalStmtFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
        }
        // END   2018/01/16 [QC#23882, ADD]

        // Invoice/Billing
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.defBaseTpCd, bizMsg.defBaseTpCd_V3);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.defBaseCycleCd, bizMsg.defBaseCycleCd_V3);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.defUsgTpCd, bizMsg.defUsgTpCd_V3);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.defUsgCycleCd, bizMsg.defUsgCycleCd_V3);
        // START 2022/03/22 [QC#59683, ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.dsInvTgtrTpCd_V1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsInvTgtrTpCd_V1, DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsInvTgtrTpCd, bizMsg.dsInvTgtrTpCd_V1);
        // END   2022/03/22 [QC#59683, ADD]
        // START 2022/03/22 [QC#59683, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dsBillTgtrFlg_V1.getValue())) {
        DS_INV_TGTR_TPTMsg dsInvTgtrTp = getDsInvTgtrTp(bizMsg.dsInvTgtrTpCd_V1.getValue());
        if (dsInvTgtrTp != null) {
//            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsBillTgtrFlg, ZYPConstant.FLG_ON_Y);
            String invSeptBaseUsgFlg = dsInvTgtrTp.invSeptBaseUsgFlg.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(invSeptBaseUsgFlg)) {
                ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsBillTgtrFlg, ZYPConstant.FLG_ON_Y);
            }
            // END   2022/03/22 [QC#59683, MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    private boolean setDsAcctProsMarketingData(DS_ACCT_PROSTMsg dsAcctProsTMsg, NMAL6700CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctDunsNum, bizMsg.dsAcctDunsNum_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsUltDunsNum, bizMsg.dsUltDunsNum_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsLocEmpNum, bizMsg.dsLocEmpNum_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsLocRevAmt, bizMsg.dsLocRevAmt_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsCustSicCd, bizMsg.dsCustSicCd_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsLastUpdDunsDt, bizMsg.dsLastUpdDunsDt_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsCustSicDescTxt, bizMsg.dsCustSicDescTxt_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.dsAcctUrl,  bizMsg.dsAcctUrl_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizHrsSunFromTm, bizMsg.bizHrsSunFromTm_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizHrsSunToTm, bizMsg.bizHrsSunToTm_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizHrsMonFriFromTm, bizMsg.bizHrsMonFriFromTm_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizHrsMonFriToTm, bizMsg.bizHrsMonFriToTm_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizHrsSatFromTm, bizMsg.bizHrsSatFromTm_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.bizHrsSatToTm, bizMsg.bizHrsSatToTm_M1);
        return true;
    }

    private boolean setDsAcctCrPrflData(DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTMsg, NMAL6700CMsg bizMsg, DS_ACCT_CR_PRFLTMsg bfDsAcctCrPrflTMsg) {

        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ccyCd, bizMsg.ccyCd_U3);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.custCrRtgCd, bizMsg.custCrRtgCd_U3);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crLimitAmt, bizMsg.crLimitAmt_U1);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crChkReqTpCd, bizMsg.crChkReqTpCd_U3);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crRiskClsCd, bizMsg.crRiskClsCd_U3);
        // START 2018/01/25 [QC#22095,ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.contrCrRiskClsCd, bizMsg.contrCrRiskClsCd_U3);
        // END   2018/01/25 [QC#22095,ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.pmtTermCashDiscCd, bizMsg.pmtTermCashDiscCd_U3);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.ovrdPmtTermFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ovrdPmtTermFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.ovrdPmtTermFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.cashOrCcReqFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.cashOrCcReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.cashOrCcReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.custHardHldFlg_U1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.custHardHldFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.custHardHldFlg, ZYPConstant.FLG_OFF_N);
        }
        // START 2017/09/07 J.Kim [QC#20495,ADD]
        if (!NMAL6700CommonLogic.equalBigDecimal(dsAcctCrPrflTMsg.crLimitAmt, bfDsAcctCrPrflTMsg.crLimitAmt)) {
            String slsDt = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crLimitChngDt, slsDt);
            BigDecimal addAmt = NMAL6700CommonLogic.add(dsAcctCrPrflTMsg.inProcAmt.getValue(), dsAcctCrPrflTMsg.arBalAmt.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.crBalAmt, NMAL6700CommonLogic.subtract(bizMsg.crLimitAmt_U1.getValue(), addAmt));
        }
        // END 2017/09/07 J.Kim [QC#20495,ADD]
        // START 2018/04/16 E.Kameishi [QC#21037, ADD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflTMsg.autoCashHrchCd, bizMsg.autoCashHrchCd_U3);
        // END 2018/04/16 E.Kameishi [QC#21037, ADD]
        return true;
    }

    private void setDsAcctGrpAsgData(DS_ACCT_GRP_ASGTMsg dsAcctRelnTMsg, NMAL6700CMsg bizMsg, NMAL6700_ESMsg esMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctGrpAsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_GRP_ASG_SQ));
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctGrpCd, esMsg.dsAcctGrpCd_E3);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.effFromDt, esMsg.effFromDt_E1);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.effThruDt, esMsg.effThruDt_E1);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsBizAreaCd, esMsg.dsBizAreaCd_E3);
    }

    private void setSvcAccsPmit(SVC_ACCS_PMITTMsg svcAccsPmitTMsg, NMAL6700CMsg bizMsg, NMAL6700_NCMsg ncMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcAccsPmitPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_ACCS_PMIT_SQ));
            ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcPmitLvlTpCd, SVC_PMIT_LVL_TP.PARTY);
            ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcPmitLvlValTxt, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcAccsPmitNum, ncMsg.svcAccsPmitNum_N1);
        ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.effFromDt, ncMsg.effFromDt_N1);
        ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.effToDt, ncMsg.effToDt_N1);

    }

    private void setDsCtacPsnRelnData(DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTMsg, NMAL6700_DSMsg dsMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(dsMsg.dsPrimLocFlg_D1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTMsg.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    private void setDsCustTrxRuleData(DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg, NMAL6700CMsg bizMsg, NMAL6700_FCMsg fcMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsCustTrxRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_TRX_RULE_SQ));
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsTrxRuleTpCd, fcMsg.dsTrxRuleTpCd_F3);
        if (ZYPConstant.FLG_ON_Y.equals(fcMsg.dsPoReqFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsPoReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsPoReqFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2022/11/25 QC#60398 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(fcMsg.hardCopyReqFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.hardCopyReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.hardCopyReqFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2022/11/25 QC#60398 Add End
        ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsBlktPoNum, fcMsg.dsBlktPoNum_F1);
        ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsPoExprDt, fcMsg.dsPoExprDt_F1);
        ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsDefBillToCd, fcMsg.dsDefBillToCd_F1);
        ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsDefShipToCd, fcMsg.dsDefShipToCd_F1);
        ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.custEffLvlCd, fcMsg.custEffLvlCd_F3);
        if (ZYPConstant.FLG_ON_Y.equals(fcMsg.dsCrCardReqFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsCrCardReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsCrCardReqFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(fcMsg.dsOvngtAllwFlg_F1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsOvngtAllwFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCustTrxRuleTMsg.dsOvngtAllwFlg, ZYPConstant.FLG_OFF_N);
        }
    }

    private void setDsCustSpclHdlgData(DS_CUST_SPCL_HDLGTMsg dsCustSpclHdlgTMsg, NMAL6700CMsg bizMsg, NMAL6700_SCMsg scMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.dsCustSpclHdlgPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CUST_SPCL_HDLG_SQ"));
            ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }
        ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.dsSpclHdlgTpCd, scMsg.dsSpclHdlgTpCd_S3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.dsSpclHdlgTpValCd, scMsg.dsSpclHdlgTpValCd_S3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.custEffLvlCd, scMsg.custEffLvlCd_S3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.effFromDt, scMsg.effFromDt_S1);
        ZYPEZDItemValueSetter.setValue(dsCustSpclHdlgTMsg.effThruDt, scMsg.effThruDt_S1);
    }
    // 2018/12/10 QC#29315 Mod Start
//    private void setDsAcctCarrData(DS_ACCT_CARRTMsg dsAcctCarrTMsg, NMAL6700CMsg bizMsg, NMAL6700_WSMsg wsMsg, boolean isCreate) {
//        String glblCmpyCd = getGlobalCompanyCode();
//
//        if (isCreate) {
//            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctCarrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BANK_ACCT_SQ));
//            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
//        }
//
//        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.vndCd, wsMsg.vndCd_W3);
//        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsCarrAcctNum, wsMsg.dsCarrAcctNum_W1);
//        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effFromDt, wsMsg.effFromDt_W1);
//        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effThruDt, wsMsg.effThruDt_W1);
//        if (ZYPConstant.FLG_ON_Y.equals(wsMsg.xxChkBox_WD.getValue())) {
//            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.defAcctCarrFlg, ZYPConstant.FLG_ON_Y);
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.defAcctCarrFlg, ZYPConstant.FLG_OFF_N);
//        }
//    }
    private void setDsAcctCarrData(DS_ACCT_CARRTMsg dsAcctCarrTMsg, NMAL6700CMsg bizMsg, NMAL6700_MSMsg msMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctCarrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BANK_ACCT_SQ));
            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.vndCd, msMsg.vndCd_M3);
        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsCarrAcctNum, msMsg.dsCarrAcctNum_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effFromDt, msMsg.effFromDt_M1);
        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effThruDt, msMsg.effThruDt_M1);
        // 2018/12/10 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.lineBizTpCd, msMsg.lineBizTpCd_M3);
        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsBizAreaCd, msMsg.dsBizAreaCd_M3);
        ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.custEffLvlCd, msMsg.custEffLvlCd_M3);
        // 2018/12/10 QC#29315 Add End
        if (ZYPConstant.FLG_ON_Y.equals(msMsg.xxChkBox_MD.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.defAcctCarrFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.defAcctCarrFlg, ZYPConstant.FLG_OFF_N);
        }
    }
    // 2018/12/10 QC#29315 Mod End

    private void setDsCustSpclMsgData(DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg, NMAL6700CMsg bizMsg, NMAL6700_JSMsg jsMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsCustSpclMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_SPCL_MSG_SQ));
            ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.lineBizTpCd, jsMsg.lineBizTpCd_J3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsBizAreaCd, jsMsg.dsBizAreaCd_J3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsCustMsgTpCd, jsMsg.dsCustMsgTpCd_J3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsCustMsgTxt, cutToMaxByte(jsMsg.dsCustMsgTxt_J1.getValue(), MAX_SPCL_MSG_LEN));
        if (ZYPConstant.FLG_ON_Y.equals(jsMsg.dsPrintOnInvFlg_J1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsPrintOnInvFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.dsPrintOnInvFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.custEffLvlCd, jsMsg.custEffLvlCd_J3);
        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.effFromDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(dsCustSpclMsgTMsg.effThruDt, jsMsg.effThruDt_J1);
    }

    private void setDsAcctRefAttrbData(DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg, NMAL6700CMsg bizMsg, NMAL6700_KCMsg kcMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.dsAcctRefAttrbPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_REF_ATTRB_SQ));
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbNm, kcMsg.bllgAttrbNm_K1);
        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbValTxt, kcMsg.bllgAttrbValTxt_K1);
        if (ZYPConstant.FLG_ON_Y.equals(kcMsg.bllgAttrbEnblFlg_K1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbEnblFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbEnblFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(kcMsg.bllgAttrbReqFlg_K1.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbReqFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.bllgAttrbReqFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.custEffLvlCd, kcMsg.custEffLvlCd_K3);
        ZYPEZDItemValueSetter.setValue(dsAcctRefAttrbTMsg.dsAcctRefAttrbNum, kcMsg.dsAcctRefAttrbNum_K1);
    }

    private void setDsCustInvRuleData(DS_CUST_INV_RULETMsg dsCustInvRuleTMsg, NMAL6700CMsg bizMsg, NMAL6700_GCMsg gcMsg, boolean isCreate) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.dsCustInvRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_INV_RULE_SQ));
            ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
        }

        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.custEffLvlCd, gcMsg.custEffLvlCd_G3);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.custInvSrcCd, gcMsg.custInvSrcCd_G3);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.custBllgTpCd, gcMsg.custBllgTpCd_G3);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.custConslTermCd, gcMsg.custConslTermCd_G3);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.custBllgVcleCd, gcMsg.custBllgVcleCd_G3);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.custEmlMsgTxt, gcMsg.custEmlMsgTxt_G1);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.defInvGrpCd, gcMsg.defInvGrpCd_G3);
        ZYPEZDItemValueSetter.setValue(dsCustInvRuleTMsg.invGrpNum, gcMsg.invGrpNum_G1);

    }

    private void setDsAcctRelnData(DS_ACCT_RELNTMsg dsAcctRelnTMsg, NMAL6700CMsg bizMsg, NMAL6700_CSMsg csMsg, boolean isCreate, boolean isReciprocal) {
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_RELN_SQ));
            if (isReciprocal) {
                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctNum, csMsg.dsAcctNum_C1);
                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.relnDsAcctNum, bizMsg.dsAcctNum_H1);
            } else {
                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.relnDsAcctNum, csMsg.dsAcctNum_C1);
            }
        }

        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnTpCd, csMsg.dsAcctRelnTpCd_C3);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.effFromDt, csMsg.effFromDt_C1);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.effThruDt, csMsg.effThruDt_C1);
        if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CB.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CS.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnRecipFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnRecipFlg, ZYPConstant.FLG_OFF_N);
        }

        String effThruDt = "";
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (ZYPCommonFunc.hasValue(csMsg.effThruDt_C1)) {
            effThruDt = csMsg.effThruDt_C1.getValue();
        } else {
            effThruDt = MAX_EFF_THRU_DT;
        }

        if (ZYPDateUtil.compare(slsDt, csMsg.effFromDt_C1.getValue()) >= 0
            && ZYPDateUtil.compare(slsDt,  effThruDt) <= 0) {

            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

        } else {
            slsDt = ZYPDateUtil.addDays(slsDt, -1);
            if (ZYPDateUtil.compare(effThruDt, slsDt) == 0) {
                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.rgtnStsCd, RGTN_STS.TERMINATED);

            } else {
                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
            }
        }

    }

    private void setDsAcctRelnData(DS_ACCT_RELNTMsg dsAcctRelnTMsg, NMAL6700CMsg bizMsg, NMAL6700_CSMsg csMsg, boolean isCreate) {
        setDsAcctRelnData(dsAcctRelnTMsg, bizMsg, csMsg, isCreate, false);
    }

    private boolean createOrUpdateRecipAcctReln(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700_CSMsg csMsg, DS_ACCT_RELNTMsg dsAcctRelnTMsg, DS_ACCT_RELNTMsg beforeDsAcctRelnTMsg) {
        String dsAcctNum = bizMsg.dsAcctNum_H1.getValue();
        // START 2017/08/09 J.Kim [QC#20184,DEL]
        // if (prevEffFromDt != null) {
        // END 2017/08/09 J.Kim [QC#20184,DEL]

        // START 2018/09/28 [QC#28165, MOD]
        //String effThruDt = null;
        //if (ZYPCommonFunc.hasValue(csMsg.effThruDt_C1)) {
        //    effThruDt = csMsg.effThruDt_C1.getValue();
        //}

        String effFromDt = null;
        String effThruDt = null;

        //S21SsmEZDResult res  = null;
        if (beforeDsAcctRelnTMsg != null) {
            //Update
            effFromDt = beforeDsAcctRelnTMsg.effFromDt.getValue();

            if (ZYPCommonFunc.hasValue(beforeDsAcctRelnTMsg.effThruDt)) {
                effThruDt =beforeDsAcctRelnTMsg.effThruDt.getValue();
            }
        } else {
            //Insert
            effFromDt = csMsg.effFromDt_C1.getValue();
            effThruDt = csMsg.effThruDt_C1.getValue();
        }

        S21SsmEZDResult res = null;

        if (effFromDt != null){
            res = NMAL6700Query.getInstance().getRepDsAcctReln(glblCmpyCd, csMsg.dsAcctNum_C1.getValue(), dsAcctNum, csMsg.dsAcctRelnTpCd_C3.getValue(), effFromDt, effThruDt);
        }
        
        //S21SsmEZDResult res = NMAL6700Query.getInstance().getRepDsAcctReln(glblCmpyCd, csMsg.dsAcctNum_C1.getValue(), dsAcctNum, csMsg.dsAcctRelnTpCd_C3.getValue());

        if ((res != null) && (res.isCodeNormal())) {
            List list = (List) res.getResultObject();

            Map map = null;
            Boolean isContinue = false;

            for (Object obj : list){
                map = (Map)obj;
                String flg = (String)map.get("DS_ACCT_RELN_RECIP_FLG");

                if (ZYPConstant.FLG_ON_Y.equals(flg)) { 
                    String fromDt = (String)map.get("EFF_FROM_DT");
                    String thruDt = (String)map.get("EFF_THRU_DT");

                    if (effFromDt.equals(fromDt)){
                        if (ZYPCommonFunc.hasValue(effThruDt)){
                            if (effThruDt.equals(thruDt)){
                                isContinue = true;
                                break;
                            }
                        } else if (!ZYPCommonFunc.hasValue(thruDt)) {
                            isContinue = true;
                            break;
                        }
                    }
                }
            }

            if (!isContinue){
                csMsg.xxChkBox_CR.setErrorInfo(1, NMAM8689E);
                csMsg.effFromDt_C1.setErrorInfo(1, NMAM8689E);
                csMsg.effThruDt_C1.setErrorInfo(1, NMAM8689E);
                return false;
            }
            // END 2018/09/28 [QC#28165, MOD]

            DS_ACCT_RELNTMsg dsAcctRelnTMsg2 = NMAL6700CommonLogic.findDsAcctRelnForUpdate(glblCmpyCd, (BigDecimal) map.get("DS_ACCT_RELN_PK"));

            // START 2017/08/09 J.Kim [QC#20184,MOD]
            // ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.dsAcctRelnBillToFlg,dsAcctRelnTMsg.dsAcctRelnBillToFlg);
            // ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.dsAcctRelnShipToFlg,dsAcctRelnTMsg.dsAcctRelnShipToFlg);
            // ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.dsAcctRelnRecipFlg,dsAcctRelnTMsg.dsAcctRelnRecipFlg);
            // ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.effFromDt,dsAcctRelnTMsg.effFromDt);
            // ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.effThruDt,dsAcctRelnTMsg.effThruDt);
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.dsAcctRelnBillToFlg, setDsAcctFlg(csMsg.xxChkBox_CB));
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.dsAcctRelnShipToFlg, setDsAcctFlg(csMsg.xxChkBox_CS));
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.dsAcctRelnRecipFlg, setDsAcctFlg(csMsg.xxChkBox_CR));
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.effFromDt, csMsg.effFromDt_C1);
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.effThruDt, csMsg.effThruDt_C1);
            // END 2017/08/09 J.Kim [QC#20184,MOD]

            // START 2018/09/12 [QC#28165, ADD]
            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
            if (ZYPCommonFunc.hasValue(csMsg.effThruDt_C1)) {
                effThruDt = csMsg.effThruDt_C1.getValue();
            } else {
                effThruDt = MAX_EFF_THRU_DT;
            }

            if (ZYPDateUtil.compare(slsDt, csMsg.effFromDt_C1.getValue()) >= 0 && ZYPDateUtil.compare(slsDt, effThruDt) <= 0) {

                ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

            } else {
                slsDt = ZYPDateUtil.addDays(slsDt, -1);
                if (ZYPDateUtil.compare(effThruDt, slsDt) == 0) {
                    ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.rgtnStsCd, RGTN_STS.TERMINATED);

                } else {
                    ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg2.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                }
            }
            // END 2018/09/12 [QC#28165, ADD]

            EZDTBLAccessor.update(dsAcctRelnTMsg2);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcctRelnTMsg2.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_RELN" });
                return false;
            }

        } else if ((res == null) || (res.isCodeNotFound())) {
            // START 2017/08/09 J.Kim [QC#20184,ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CR.getValue())) {
                // START 2018/09/12 [QC#28165, ADD]
                if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                    return createReverseRelnShip(glblCmpyCd, bizMsg, csMsg);
                }
                // END 2018/09/12 [QC#28165, ADD]
            }
            // END 2017/08/09 J.Kim [QC#20184,ADD]
            DS_ACCT_RELNTMsg recipDsAcctRelnTMsg = new DS_ACCT_RELNTMsg();
            EZDMsg.copy(dsAcctRelnTMsg, null, recipDsAcctRelnTMsg, null);
            ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.dsAcctRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_RELN_SQ));
            ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.dsAcctNum, csMsg.dsAcctNum_C1.getValue());
            ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.relnDsAcctNum, dsAcctNum);

            // START 2018/09/12 [QC#28165, ADD]
            if (DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(csMsg.dsAcctRelnTpCd_C3.getValue())) {
                // Bill -> Ship
                if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CB.getValue())) {
                    ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_ON_Y);
                }

                // Ship -> Bill
                if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CS.getValue())) {
                    ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_ON_Y);
                }
            }
            // END 2018/09/12 [QC#28165, ADD]

            S21FastTBLAccessor.create(recipDsAcctRelnTMsg);
            if (!RTNCD_NORMAL.equals(recipDsAcctRelnTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_ACCT_RELN" });
                return false;
            }
        }
        // START 2017/08/09 J.Kim [QC#20184,DEL]
        //} else {
        //    DS_ACCT_RELNTMsg recipDsAcctRelnTMsg = new DS_ACCT_RELNTMsg();
        //    EZDMsg.copy(dsAcctRelnTMsg, null, recipDsAcctRelnTMsg, null);
        //    ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.dsAcctRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ACCT_RELN_SQ));
        //    ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.dsAcctNum, csMsg.dsAcctNum_C1.getValue());
        //    ZYPEZDItemValueSetter.setValue(recipDsAcctRelnTMsg.relnDsAcctNum, dsAcctNum);
        //
        //    S21FastTBLAccessor.create(recipDsAcctRelnTMsg);
        //    if (!RTNCD_NORMAL.equals(recipDsAcctRelnTMsg.getReturnCode())) {
        //        bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_ACCT_RELN" });
        //        return false;
        //    }
        //}
        // END 2017/08/09 J.Kim [QC#20184,DEL]
        return true;
    }

    // START 2018/09/12 [QC#28165, ADD]
    private boolean createReverseRelnShip(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700_CSMsg csMsg){
        String effThruDt = null;
        if (ZYPCommonFunc.hasValue(csMsg.effThruDt_C1)) {
            effThruDt = csMsg.effThruDt_C1.getValue();
        }

        S21SsmEZDResult result = NMAL6700Query.getInstance().getRepDsAcctReln(glblCmpyCd, csMsg.dsAcctNum_C1.getValue(), bizMsg.dsAcctNum_H1.getValue(), csMsg.dsAcctRelnTpCd_C3.getValue(), csMsg.effFromDt_C1.getValue(), effThruDt);

        if (!result.isCodeNotFound()) {
            return true;
        }

        DS_ACCT_RELNTMsg dsAcctRelnTMsg = new DS_ACCT_RELNTMsg();
        setDsAcctRelnData(dsAcctRelnTMsg, bizMsg, csMsg, true, true);

        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnRecipFlg, ZYPConstant.FLG_OFF_N);

        // Bill -> Ship
        if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CB.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnShipToFlg, ZYPConstant.FLG_ON_Y);
        }

        // Ship -> Bill
        if (ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_CS.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsAcctRelnTMsg.dsAcctRelnBillToFlg, ZYPConstant.FLG_ON_Y);
        }

        S21FastTBLAccessor.create(dsAcctRelnTMsg);
        if (!RTNCD_NORMAL.equals(dsAcctRelnTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_ACCT_RELN" });
            return false;
        }

        return true;
    }
    // END 2018/09/12 [QC#28165, ADD]

    private boolean updateDsCtacPsnReln(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, NMAL6700_DSMsg dsMsg) {
        DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTMsg = NMAL6700CommonLogic.findDsCtacPsnRelnForUpdate(glblCmpyCd, dsMsg.dsCtacPsnRelnPk_D1.getValue());
        if (dsCtacPsnRelnTMsg == null) {
            String[] args = {"DS_CTAC_PSN_RELN" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(dsMsg.ezUpTime_DR.getValue(), dsMsg.ezUpTimeZone_DR.getValue(), dsCtacPsnRelnTMsg.ezUpTime.getValue(), dsCtacPsnRelnTMsg.ezUpTimeZone.getValue())) {
            bizMsg.setMessageInfo(NZZM0003E);
            return false;
        }

        DS_CTAC_PSN_RELNTMsg beforeDsCtacPsnRelnTMsg = new DS_CTAC_PSN_RELNTMsg();
        DS_CTAC_PSN_RELNTMsg.copy(dsCtacPsnRelnTMsg, null, beforeDsCtacPsnRelnTMsg, null);
        setDsCtacPsnRelnData(dsCtacPsnRelnTMsg, dsMsg);
        //Change check
        if (changeFieldsCheckForDsCtacPsnReln(dsCtacPsnRelnTMsg, beforeDsCtacPsnRelnTMsg)) {
            //Change has been made.
            S21FastTBLAccessor.update(dsCtacPsnRelnTMsg);
            if (!RTNCD_NORMAL.equals(dsCtacPsnRelnTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CTAC_PSN_RELN" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

        }
        return true;
    }

    private boolean updateDsAccountForMarketingTab(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd, String dsAcctNum) {
        SELL_TO_CUSTTMsg updateSellToCustForRgtnStsCdTMsg = NMAL6700CommonLogic.findSellToCustForUpdate(glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());

        if (updateSellToCustForRgtnStsCdTMsg == null) {
            DS_ACCT_PROSTMsg exDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            exDsAcctProsTMsg.setSQLID("001");
            exDsAcctProsTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            exDsAcctProsTMsg.setConditionValue("dsAcctNum01", bizMsg.dsAcctNum_H1.getValue());
            DS_ACCT_PROSTMsgArray resultArrayPros = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(exDsAcctProsTMsg);
            if (resultArrayPros == null || resultArrayPros.length() == 0) {
                String[] args = {"DS_ACCT_PROS" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }

            exDsAcctProsTMsg = (DS_ACCT_PROSTMsg) resultArrayPros.get(0);

            DS_ACCT_PROSTMsg updateDsAcctProsTMsg = NMAL6700CommonLogic.findDsAcctProsForUpdate(glblCmpyCd, exDsAcctProsTMsg.dsAcctProsPk.getValue());
            if (updateDsAcctProsTMsg == null) {
                String[] args = {"DS_ACCT_PROS" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }

            DS_ACCT_PROSTMsg beforeUpdateDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            DS_ACCT_PROSTMsg.copy(updateDsAcctProsTMsg, null, beforeUpdateDsAcctProsTMsg, null);
            if (!setDsAcctProsMarketingData(updateDsAcctProsTMsg, bizMsg)) {
                return false;
            }
            //Change check
            if (changeFieldsCheckForProsFromMarketing(updateDsAcctProsTMsg, beforeUpdateDsAcctProsTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateDsAcctProsTMsg);

                if (!RTNCD_NORMAL.equals(updateDsAcctProsTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_PROS" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        } else {
            // Update SELL_TO_CUST
            SELL_TO_CUSTTMsg beforeUpdateSellToCustForRgtnStsCdTMsg = new SELL_TO_CUSTTMsg();
            SELL_TO_CUSTTMsg.copy(updateSellToCustForRgtnStsCdTMsg, null, beforeUpdateSellToCustForRgtnStsCdTMsg, null);
            if (!setSellToCustDataForMarketingTab(bizMsg, updateSellToCustForRgtnStsCdTMsg)) {
                return false;
            }
            //Change check
            if (changeFieldsCheckForSellFromMarketing(updateSellToCustForRgtnStsCdTMsg, beforeUpdateSellToCustForRgtnStsCdTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateSellToCustForRgtnStsCdTMsg);
                if (!RTNCD_NORMAL.equals(updateSellToCustForRgtnStsCdTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"SELL_TO_CUST" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        return true;
    }

    private boolean setSellToCustDataForMarketingTab(NMAL6700CMsg bizMsg, SELL_TO_CUSTTMsg tmsg) {
        ZYPEZDItemValueSetter.setValue(tmsg.dsAcctDunsNum, bizMsg.dsAcctDunsNum_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsUltDunsNum, bizMsg.dsUltDunsNum_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsLocEmpNum, bizMsg.dsLocEmpNum_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsLocRevAmt, bizMsg.dsLocRevAmt_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsCustSicCd, bizMsg.dsCustSicCd_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsLastUpdDunsDt, bizMsg.dsLastUpdDunsDt_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsAcctUrl,  bizMsg.dsAcctUrl_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsCustSicCd, bizMsg.dsCustSicCd_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dsCustSicDescTxt, bizMsg.dsCustSicDescTxt_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.bizHrsSunFromTm, bizMsg.bizHrsSunFromTm_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.bizHrsSunToTm, bizMsg.bizHrsSunToTm_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.bizHrsMonFriFromTm, bizMsg.bizHrsMonFriFromTm_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.bizHrsMonFriToTm, bizMsg.bizHrsMonFriToTm_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.bizHrsSatFromTm, bizMsg.bizHrsSatFromTm_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.bizHrsSatToTm, bizMsg.bizHrsSatToTm_M1);
        ZYPEZDItemValueSetter.setValue(tmsg.dunsNum, bizMsg.dsAcctDunsNum_M1);
        return true;
    }

    private boolean updateCmpyForMarketingTab(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        // Update CMPY
        CMPYTMsg updateCmpyTMsg = NMAL6700CommonLogic.findCmpyForUpdate(glblCmpyCd, bizMsg.cmpyPk_H1.getValue());
        if (updateCmpyTMsg == null) {
            String[] args = {"CMPY" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        CMPYTMsg beforeUpdateCmpyTMsg = new CMPYTMsg();
        CMPYTMsg.copy(updateCmpyTMsg, null, beforeUpdateCmpyTMsg, null);
        if (!setCmpyDataForMarketingTab(updateCmpyTMsg, bizMsg)) {
            return false;
        }
        //Change check
        if (changeFieldsCheckForCmpyFromMarketing(updateCmpyTMsg, beforeUpdateCmpyTMsg)) {
            //Change has been made.
            S21FastTBLAccessor.update(updateCmpyTMsg);
            if (!RTNCD_NORMAL.equals(updateCmpyTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"CMPY" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

        }
        return true;
    }

    private boolean setCmpyDataForMarketingTab(CMPYTMsg tmsg, NMAL6700CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(tmsg.dunsNum, bizMsg.dsAcctDunsNum_M1);
        return true;
    }

    private boolean deleteDsAcctGrpAsgForMarketing(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {

        int cnt = sharedMsg.Q.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_QSMsg qsMsg = sharedMsg.Q.no(i);
            DS_ACCT_GRP_ASGTMsg tmsg = NMAL6700CommonLogic.findDsAcctGrpAsgForUpdate(glblCmpyCd, qsMsg.dsAcctGrpAsgPk_Q1.getValue());
            if (tmsg == null) {
                String[] args = {"DS_ACCT_GRP_ASG" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(qsMsg.ezUpTime_Q1.getValue(), qsMsg.ezUpTimeZone_Q1.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(tmsg);
            if (!RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_GRP_ASG" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        return true;
    }

    private boolean insertAndUpdateSvcAccsPmitForMarketing(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        for (int i = 0; i < bizMsg.N.getValidCount(); i++) {
            NMAL6700_NCMsg ncMsg = bizMsg.N.no(i);
            if (ZYPCommonFunc.hasValue(ncMsg.svcAccsPmitPk_N1)) {

                SVC_ACCS_PMITTMsg svcAccsPmitTMsg = NMAL6700CommonLogic.findSvcAccsPmitForUpdate(glblCmpyCd, ncMsg.svcAccsPmitPk_N1.getValue());
                if (svcAccsPmitTMsg == null) {
                    String[] args = {"SVC_ACCS_PMIT" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(ncMsg.ezUpTime_N1.getValue(), ncMsg.ezUpTimeZone_N1.getValue(), svcAccsPmitTMsg.ezUpTime.getValue(), svcAccsPmitTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }

                SVC_ACCS_PMITTMsg beforeSvcAccsPmitTMsg = new SVC_ACCS_PMITTMsg();
                SVC_ACCS_PMITTMsg.copy(svcAccsPmitTMsg, null, beforeSvcAccsPmitTMsg, null);
                setSvcAccsPmit(svcAccsPmitTMsg, bizMsg, ncMsg, false);
                //Change check
                if (changeFieldsCheckForSvcAccsPmit(svcAccsPmitTMsg, beforeSvcAccsPmitTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(svcAccsPmitTMsg);
                    if (!RTNCD_NORMAL.equals(svcAccsPmitTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"SVC_ACCS_PMIT" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }
            } else {
                SVC_ACCS_PMITTMsg svcAccsPmitTMsg = new SVC_ACCS_PMITTMsg();
                setSvcAccsPmit(svcAccsPmitTMsg, bizMsg, ncMsg, true);

                S21FastTBLAccessor.create(svcAccsPmitTMsg);
                if (!RTNCD_NORMAL.equals(svcAccsPmitTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"SVC_ACCS_PMIT" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            }
        }
        return true;
    }

    private boolean deleteSvcAccsPmitgForMarketing(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {

        int cnt = sharedMsg.R.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_RSMsg rsMsg = sharedMsg.R.no(i);
            SVC_ACCS_PMITTMsg tmsg = NMAL6700CommonLogic.findSvcAccsPmitForUpdate(glblCmpyCd, rsMsg.svcAccsPmitPk_R1.getValue());
            if (tmsg == null) {
                String[] args = {"SVC_ACCS_PMIT" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(rsMsg.ezUpTime_R1.getValue(), rsMsg.ezUpTimeZone_R1.getValue(), tmsg.ezUpTime.getValue(), tmsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(tmsg);
            if (!RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"SVC_ACCS_PMIT" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        return true;
    }

    /**
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean createOrUpdateDsAcctRefAttrb(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {

        // Delete DS_ACCT_REF_ATTRB
        int cnt = sharedMsg.T.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_TSMsg tsMsg = sharedMsg.T.no(i);
            DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg = NMAL6700CommonLogic.findDsAcctRefAttrbForUpdate(glblCmpyCd, tsMsg.dsAcctRefAttrbPk_T1.getValue());
            if (dsAcctRefAttrbTMsg == null) {
                String[] args = {"DS_ACCT_REF_ATTRB" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(tsMsg.ezUpTime_T1.getValue(), tsMsg.ezUpTimeZone_T1.getValue(), dsAcctRefAttrbTMsg.ezUpTime.getValue(), dsAcctRefAttrbTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsAcctRefAttrbTMsg);
            if (!RTNCD_NORMAL.equals(dsAcctRefAttrbTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_REF_ATTRB" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        cnt = bizMsg.K.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_KCMsg kcMsg = bizMsg.K.no(i);
            if (ZYPCommonFunc.hasValue(kcMsg.dsAcctRefAttrbPk_K1)) {

                DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg = NMAL6700CommonLogic.findDsAcctRefAttrbForUpdate(glblCmpyCd, kcMsg.dsAcctRefAttrbPk_K1.getValue());
                if (dsAcctRefAttrbTMsg == null) {
                    String[] args = {"DS_ACCT_REF_ATTRB" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(kcMsg.ezUpTime_K1.getValue(), kcMsg.ezUpTimeZone_K1.getValue(), dsAcctRefAttrbTMsg.ezUpTime.getValue(), dsAcctRefAttrbTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_ACCT_REF_ATTRBTMsg beforeDsAcctRefAttrbTMsg = new DS_ACCT_REF_ATTRBTMsg();
                DS_ACCT_REF_ATTRBTMsg.copy(dsAcctRefAttrbTMsg, null, beforeDsAcctRefAttrbTMsg, null);
                setDsAcctRefAttrbData(dsAcctRefAttrbTMsg, bizMsg, kcMsg, false);
                //Change check
                if (changeFieldsCheckForDsAcctRefAttrb(dsAcctRefAttrbTMsg, beforeDsAcctRefAttrbTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsAcctRefAttrbTMsg);
                    if (!RTNCD_NORMAL.equals(dsAcctRefAttrbTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_REF_ATTRB" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }

            } else {
                DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg = new DS_ACCT_REF_ATTRBTMsg();
                setDsAcctRefAttrbData(dsAcctRefAttrbTMsg, bizMsg, kcMsg, true);

                S21FastTBLAccessor.create(dsAcctRefAttrbTMsg);
                if (!RTNCD_NORMAL.equals(dsAcctRefAttrbTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_REF_ATTRB" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }

        return true;
    }

    private boolean createOrUpdateDsCustInvRule(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {

        // Delete DS_CUST_INV_RULE
        int cnt = sharedMsg.Y.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_YSMsg ysMsg = sharedMsg.Y.no(i);
            DS_CUST_INV_RULETMsg dsCustInvRuleTMsg = NMAL6700CommonLogic.findDsCustInvRuleForUpdate(glblCmpyCd, ysMsg.dsCustInvRulePk_Y1.getValue());
            if (dsCustInvRuleTMsg == null) {
                String[] args = {"DS_CUST_INV_RULE" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            if (!ZYPDateUtil.isSameTimeStamp(ysMsg.ezUpTime_Y1.getValue(), ysMsg.ezUpTimeZone_Y1.getValue(), dsCustInvRuleTMsg.ezUpTime.getValue(), dsCustInvRuleTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(dsCustInvRuleTMsg);
            if (!RTNCD_NORMAL.equals(dsCustInvRuleTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_INV_RULE" });
                return false;
            }
            bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        cnt = bizMsg.G.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_GCMsg gcMsg = bizMsg.G.no(i);
            if (ZYPCommonFunc.hasValue(gcMsg.dsCustInvRulePk_G1)) {

                DS_CUST_INV_RULETMsg dsCustInvRuleTMsg = NMAL6700CommonLogic.findDsCustInvRuleForUpdate(glblCmpyCd, gcMsg.dsCustInvRulePk_G1.getValue());
                if (dsCustInvRuleTMsg == null) {
                    String[] args = {"DS_CUST_INV_RULE" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(gcMsg.ezUpTime_G1.getValue(), gcMsg.ezUpTimeZone_G1.getValue(), dsCustInvRuleTMsg.ezUpTime.getValue(), dsCustInvRuleTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                DS_CUST_INV_RULETMsg beforeDsCustInvRuleTMsg = new DS_CUST_INV_RULETMsg();
                DS_CUST_INV_RULETMsg.copy(dsCustInvRuleTMsg, null, beforeDsCustInvRuleTMsg, null);
                setDsCustInvRuleData(dsCustInvRuleTMsg, bizMsg, gcMsg, false);
                //Change check
                if (changeFieldsCheckForDsCustInvRule(dsCustInvRuleTMsg, beforeDsCustInvRuleTMsg)) {
                    //Change has been made.
                    S21FastTBLAccessor.update(dsCustInvRuleTMsg);
                    if (!RTNCD_NORMAL.equals(dsCustInvRuleTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_INV_RULE" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                }

                if (!updateInternalEmailReview(bizMsg, dsCustInvRuleTMsg.dsCustInvRulePk.getValue(), bizMsg.G.no(i).xxGenlFldAreaTxt_G1.getValue())) {
                    return true;
                }
                if (!updateExternalEmailContact(bizMsg, dsCustInvRuleTMsg.dsCustInvRulePk.getValue(), bizMsg.G.no(i).xxGenlFldAreaTxt_G2.getValue())) {
                    return true;
                }

            } else {
                DS_CUST_INV_RULETMsg dsCustInvRuleTMsg = new DS_CUST_INV_RULETMsg();
                setDsCustInvRuleData(dsCustInvRuleTMsg, bizMsg, gcMsg, true);

                S21FastTBLAccessor.create(dsCustInvRuleTMsg);
                if (!RTNCD_NORMAL.equals(dsCustInvRuleTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_INV_RULE" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

                if (!insertInternalEmailReview(bizMsg, dsCustInvRuleTMsg.dsCustInvRulePk.getValue(), bizMsg.G.no(i).xxGenlFldAreaTxt_G1.getValue())) {
                    return true;
                }
                if (!insertExternalEmailContact(bizMsg, dsCustInvRuleTMsg.dsCustInvRulePk.getValue(), bizMsg.G.no(i).xxGenlFldAreaTxt_G2.getValue())) {
                    return true;
                }

            }
        }
        return true;
    }

    private boolean updateInternalEmailReview(NMAL6700CMsg cMsg, BigDecimal dsCustInvRulePk, String psnCdList) {
        List<String> listExstPsnCd = new ArrayList<String>();
        List<BigDecimal> listExstRcpntPk = new ArrayList<BigDecimal>();
        S21SsmEZDResult result = NMAL6700Query.getInstance().getInvoiceSourceListInternalReview(dsCustInvRulePk);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
            for (Map<String, Object> map : listMap) {
                listExstPsnCd.add((String) map.get("PSN_CD"));
                listExstRcpntPk.add((BigDecimal) map.get("DS_CUST_INV_RULE_RCPNT_PK"));
            }
        }

        String[] psnCdArray = NMAL6700CommonLogic.splitByComma(psnCdList);
        List<String> listNewPsnCd = Arrays.asList(psnCdArray);

        for (int i = 0; i < listExstPsnCd.size(); i++) {
            String exstPsnCd = listExstPsnCd.get(i);
            BigDecimal dsCustInvRuleRcpntPk = listExstRcpntPk.get(i);
            if (!listNewPsnCd.contains(exstPsnCd)) {
                if (!removeInternalEmailReview(cMsg, dsCustInvRuleRcpntPk)) {
                    return false;
                }
                cMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }

        for (String newPsnCd : listNewPsnCd) {
            if (!listExstPsnCd.contains(newPsnCd)) {
                if (!insertInternalEmailReview(cMsg, dsCustInvRulePk, newPsnCd)) {
                    return false;
                }
                cMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        return true;
    }

    private boolean insertInternalEmailReview(NMAL6700CMsg cMsg, BigDecimal dsCustInvRulePk, String psnCdList) {
        String[] psnCdArray = NMAL6700CommonLogic.splitByComma(psnCdList);
        for (String psnCd : psnCdArray) {
            DS_CUST_INV_RULE_RCPNTTMsg tMsg = new DS_CUST_INV_RULE_RCPNTTMsg();
            tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
            tMsg.dsCustInvRuleRcpntPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_INV_RULE_RCPNT_SQ));
            tMsg.dsCustInvRulePk.setValue(dsCustInvRulePk);
            tMsg.invRuleRcpntTpCd.setValue(INV_RULE_RCPNT_TP.INTERNAL);
            tMsg.psnCd.setValue(psnCd);
            S21FastTBLAccessor.insert(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {"DS_CUST_INV_RULE_RCPNT" });
                return false;
            }
        }
        return true;
    }

    private boolean removeInternalEmailReview(NMAL6700CMsg cMsg, BigDecimal dsCustInvRuleRcpntPk) {
        DS_CUST_INV_RULE_RCPNTTMsg tMsg = new DS_CUST_INV_RULE_RCPNTTMsg();
        tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tMsg.dsCustInvRuleRcpntPk.setValue(dsCustInvRuleRcpntPk);
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0208E, new String[] {"DS_CUST_INV_RULE_RCPNT" });
            return false;
        }
        return true;
    }

    private boolean updateExternalEmailContact(NMAL6700CMsg cMsg, BigDecimal dsCustInvRulePk, String ctacPsnPkList) {
        List<String> listExstCtacPsnPk = new ArrayList<String>();
        List<BigDecimal> listExstRcpntPk = new ArrayList<BigDecimal>();
        S21SsmEZDResult result = NMAL6700Query.getInstance().getInvoiceSourceListExternalContact(dsCustInvRulePk);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> listMap = (List<Map<String, Object>>) result.getResultObject();
            for (Map<String, Object> map : listMap) {
                listExstCtacPsnPk.add(((BigDecimal) map.get("CTAC_PSN_PK")).toPlainString());
                listExstRcpntPk.add((BigDecimal) map.get("DS_CUST_INV_RULE_RCPNT_PK"));
            }
        }

        String[] ctacPsnPkArray = NMAL6700CommonLogic.splitByComma(ctacPsnPkList);
        List<String> listNewCtacPsnPk = Arrays.asList(ctacPsnPkArray);

        for (int i = 0; i < listExstCtacPsnPk.size(); i++) {
            String exstCtacPsnPk = listExstCtacPsnPk.get(i);
            BigDecimal dsCustInvRuleRcpntPk = listExstRcpntPk.get(i);
            if (!listNewCtacPsnPk.contains(exstCtacPsnPk)) {
                if (!removeExternalEmailContact(cMsg, dsCustInvRuleRcpntPk)) {
                    return false;
                }
                cMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }

        for (String newCtacPsnPk : listNewCtacPsnPk) {
            if (!listExstCtacPsnPk.contains(newCtacPsnPk)) {
                if (!insertExternalEmailContact(cMsg, dsCustInvRulePk, newCtacPsnPk)) {
                    return false;
                }
                cMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        return true;
    }

    private boolean insertExternalEmailContact(NMAL6700CMsg cMsg, BigDecimal dsCustInvRulePk, String ctacPsnPkList) {
        String[] ctacPsnPkCdArray = NMAL6700CommonLogic.splitByComma(ctacPsnPkList);
        for (String ctacPsnPk : ctacPsnPkCdArray) {
            DS_CUST_INV_RULE_RCPNTTMsg tMsg = new DS_CUST_INV_RULE_RCPNTTMsg();
            tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
            tMsg.dsCustInvRuleRcpntPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CUST_INV_RULE_RCPNT_SQ));
            tMsg.dsCustInvRulePk.setValue(dsCustInvRulePk);
            tMsg.invRuleRcpntTpCd.setValue(INV_RULE_RCPNT_TP.EXTERNAL);
            tMsg.ctacPsnPk.setValue(new BigDecimal(ctacPsnPk));
            S21FastTBLAccessor.insert(tMsg);
            if (!RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {"DS_CUST_INV_RULE_RCPNT" });
                return false;
            }
        }
        return true;
    }

    private boolean removeExternalEmailContact(NMAL6700CMsg cMsg, BigDecimal dsCustInvRuleRcpntPk) {
        return removeInternalEmailReview(cMsg, dsCustInvRuleRcpntPk);
    }

    private boolean isExistGroupCode(String dsAcctGrpCd) {
        DS_ACCT_GRPTMsg tmsg = new DS_ACCT_GRPTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tmsg.dsAcctGrpCd, dsAcctGrpCd);
        tmsg = (DS_ACCT_GRPTMsg) S21FastTBLAccessor.findByKey(tmsg);

        if (tmsg == null) {
            return false;
        }

        return true;
    }

    private String getEffThruDt(String effThruDt) {
        String strEffThruDt = "";
        if (ZYPCommonFunc.hasValue(effThruDt)) {
            strEffThruDt = effThruDt;
        } else {
            strEffThruDt = MAX_EFF_THRU_DT;
        }
        return strEffThruDt;
    }

    private boolean inputCheckForInvBllg(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        boolean rtnFlg = true;
        int cnt = bizMsg.G.getValidCount();
        Map<String, NMAL6700_GCMsg> map = new HashMap<String, NMAL6700_GCMsg>();

        // Check Base Adv/Arrears and Base Cycle
        if (ZYPCommonFunc.hasValue(bizMsg.defBaseCycleCd_V3)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.defBaseTpCd_V3)) {
                String[] args = {"Base Cycle", "Base Adv/Arrears" };
                bizMsg.defBaseTpCd_V3.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defBaseTpCd_V3)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.defBaseCycleCd_V3)) {
                String[] args = {"Base Adv/Arrears", "Base Cycle" };
                bizMsg.defBaseCycleCd_V3.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }
        // Check Usage Adv/Arrears and Usage Cycle
        if (ZYPCommonFunc.hasValue(bizMsg.defUsgCycleCd_V3)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.defUsgTpCd_V3)) {
                String[] args = {"Usage Cycle", "Usage Adv/Arrears" };
                bizMsg.defUsgTpCd_V3.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.defUsgTpCd_V3)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.defUsgCycleCd_V3)) {
                String[] args = {"Usage Adv/Arrears", "Usage Cycle" };
                bizMsg.defUsgCycleCd_V3.setErrorInfo(1, NMAM8178E, args);
                rtnFlg = false;
            }
        }

        // Check Detail
        for (int i = 0; i < cnt; i++) {
            NMAL6700_GCMsg gcMsg = bizMsg.G.no(i);

            // Check duplicate for Invoice Source
            String custInvSrcCd = gcMsg.custInvSrcCd_G3.getValue();
            if (map.containsKey(custInvSrcCd)) {
                String[] args = {"Invoice Source" };
                gcMsg.custInvSrcCd_G3.setErrorInfo(1, NMAM0072E, args);
                rtnFlg = false;
            } else {
                map.put(custInvSrcCd, gcMsg);
            }

            String custBllgVcleCd = gcMsg.custBllgVcleCd_G3.getValue();
            if (CUST_BLLG_VCLE.EMAIL_ONLY.equals(custBllgVcleCd) || CUST_BLLG_VCLE.PRINT_AND_EMAIL.equals(custBllgVcleCd)) {
                if (!ZYPCommonFunc.hasValue(gcMsg.xxGenlFldAreaTxt_G2)) {
                    String[] args = {"Bill Vehicle", "EMAIL", "External Email Contact" };
                    gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM8177E, args);
                    rtnFlg = false;
                }
            }

            if (ZYPCommonFunc.hasValue(gcMsg.defInvGrpCd_G3)) {
                if (!ZYPCommonFunc.hasValue(gcMsg.invGrpNum_G1)) {
                    String[] args = {"Default Grouping", "Invoice Group#" };
                    gcMsg.invGrpNum_G1.setErrorInfo(1, NMAM8178E, args);
                    rtnFlg = false;
                }

                if (!CUST_BLLG_TP.CONSOLIDATED.equals(gcMsg.custBllgTpCd_G3.getValue())) {
                    String[] args = {ZYPCodeDataUtil.getName(CUST_BLLG_TP.class, glblCmpyCd, CUST_BLLG_TP.CONSOLIDATED), "Default Grouping" };
                    gcMsg.custBllgTpCd_G3.setErrorInfo(1, NMAM8179E, args);
                    rtnFlg = false;

                }
            }

            // Check Consolidated Term, Default Grouping, Invoice Group#
            if (CUST_BLLG_TP.CONSOLIDATED.equals(gcMsg.custBllgTpCd_G3.getValue())) {
                if (!ZYPCommonFunc.hasValue(gcMsg.custConslTermCd_G3)) {
                    String[] args = {"Bill Type", ZYPCodeDataUtil.getName(CUST_BLLG_TP.class, glblCmpyCd, CUST_BLLG_TP.CONSOLIDATED), "Consolidated Term" };
                    gcMsg.custConslTermCd_G3.setErrorInfo(1, NMAM8180E, args);
                    rtnFlg = false;
                }
                if (!ZYPCommonFunc.hasValue(gcMsg.invGrpNum_G1)) {
                    String[] args = {"Bill Type", ZYPCodeDataUtil.getName(CUST_BLLG_TP.class, glblCmpyCd, CUST_BLLG_TP.CONSOLIDATED), "Invoice Group#" };
                    gcMsg.invGrpNum_G1.setErrorInfo(1, NMAM8180E, args);
                    rtnFlg = false;
                }
            }

            // If Bill Type is 'Daily', the Consolidated Term, Default Grouping, Invoice Group# can not be input.
            if (CUST_BLLG_TP.DAILY.equals(gcMsg.custBllgTpCd_G3.getValue())) {
                if (ZYPCommonFunc.hasValue(gcMsg.custConslTermCd_G3)) {
                    gcMsg.custConslTermCd_G3.setErrorInfo(1, NMAM8409E, new String[] {"Bill Type 'Daily'", "Consolidated Term" });
                    rtnFlg = false;
                }
                if (ZYPCommonFunc.hasValue(gcMsg.defInvGrpCd_G3)) {
                    gcMsg.defInvGrpCd_G3.setErrorInfo(1, NMAM8409E, new String[] {"Bill Type 'Daily'", "Default Grouping" });
                    rtnFlg = false;
                }
                if (ZYPCommonFunc.hasValue(gcMsg.invGrpNum_G1)) {
                    gcMsg.invGrpNum_G1.setErrorInfo(1, NMAM8409E, new String[] {"Bill Type 'Daily'", "Invoice Group#" });
                    rtnFlg = false;
                }
            }

            // Check Bill Type and Bill Vehicle combination
            if (ZYPCommonFunc.hasValue(gcMsg.custBllgTpCd_G3) && ZYPCommonFunc.hasValue(gcMsg.custBllgVcleCd_G3)) {
                CUST_BLLG_VCLETMsg tMsg = new CUST_BLLG_VCLETMsg();
                tMsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                tMsg.custBllgVcleCd.setValue(gcMsg.custBllgVcleCd_G3.getValue());
                tMsg = (CUST_BLLG_VCLETMsg) EZDTBLAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    if (CUST_BLLG_TP.CONSOLIDATED.equals(gcMsg.custBllgTpCd_G3.getValue())) {
                        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.conslBllgAvalFlg.getValue())) {
                            gcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM0306E, new String[] {"Bill Type", "Bill Vehicle" });
                            rtnFlg = false;
                        }
                    } else if (CUST_BLLG_TP.DAILY.equals(gcMsg.custBllgTpCd_G3.getValue())) {
                        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.dlyBllgAvalFlg.getValue())) {
                            gcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM0306E, new String[] {"Bill Type", "Bill Vehicle" });
                            rtnFlg = false;
                        }
                    }
                }
            }

            // QC#23604 add Start
            // Check Bill Type and Default Invoice Group relation
            if (!CUST_INV_SRC.SERVICE_CONTRACT.equals(custInvSrcCd)) {
                // START 2018/06/25 U.Kim [QC#26703,MOD]
                // if (DEF_INV_GRP.IB_CONTROL_FIELDS.equals(gcMsg.defInvGrpCd_G3.getValue())) {
                //     String[] args = {"Invoce Grouping 'IB CONTROL FIELDS'", "Invoice Source", "not SERVICE CONTRACT" };
                //     gcMsg.defInvGrpCd_G3.setErrorInfo(1, NMAM8682E, args);
                //     rtnFlg = false;
                // }
                if (DEF_INV_GRP.IB_CONTROL_FIELDS1.equals(gcMsg.defInvGrpCd_G3.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS2.equals(gcMsg.defInvGrpCd_G3.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS3.equals(gcMsg.defInvGrpCd_G3.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS4.equals(gcMsg.defInvGrpCd_G3.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS5.equals(gcMsg.defInvGrpCd_G3.getValue())
                        || DEF_INV_GRP.IB_CONTROL_FIELDS6.equals(gcMsg.defInvGrpCd_G3.getValue())) {
                    String[] args = {"Invoce Grouping 'IB CONTROL FIELDS'", "Invoice Source", "not SERVICE CONTRACT" };
                    gcMsg.defInvGrpCd_G3.setErrorInfo(1, NMAM8682E, args);
                    rtnFlg = false;
                }
                // END 2018/06/25 U.Kim [QC#26703,MOD]
            }
            // QC#23604 add End

            // Check same value 
            if (ZYPCommonFunc.hasValue(gcMsg.invGrpNum_G1)) {
                for (int j = 0; j < cnt; j++) {
                    NMAL6700_GCMsg chkGcMsg = bizMsg.G.no(j);
                    if (gcMsg.invGrpNum_G1.getValue().equals(chkGcMsg.invGrpNum_G1.getValue())) {
                        if (!gcMsg.custBllgTpCd_G3.getValue().equals(chkGcMsg.custBllgTpCd_G3.getValue())) {
                            String[] args = {"Bill Type" };
                            gcMsg.custBllgTpCd_G3.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.custBllgTpCd_G3.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                        if (!gcMsg.custConslTermCd_G3.getValue().equals(chkGcMsg.custConslTermCd_G3.getValue())) {
                            String[] args = {"Consolidated Term" };
                            gcMsg.custConslTermCd_G3.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.custConslTermCd_G3.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                        if (!gcMsg.custBllgVcleCd_G3.getValue().equals(chkGcMsg.custBllgVcleCd_G3.getValue())) {
                            String[] args = {"Bill Vehicle" };
                            gcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                        if (!gcMsg.xxGenlFldAreaTxt_G1.getValue().equals(chkGcMsg.xxGenlFldAreaTxt_G1.getValue())) {
                            String[] args = {"Internal Email Review" };
                            gcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.xxGenlFldAreaTxt_G1.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                        if (!gcMsg.xxGenlFldAreaTxt_G2.getValue().equals(chkGcMsg.xxGenlFldAreaTxt_G2.getValue())) {
                            String[] args = {"External Email Contact" };
                            gcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.xxGenlFldAreaTxt_G2.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }

                        if (!gcMsg.custEmlMsgTxt_G1.getValue().equals(chkGcMsg.custEmlMsgTxt_G1.getValue())) {
                            String[] args = {"Custom Email Subject" };
                            gcMsg.custEmlMsgTxt_G1.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.custEmlMsgTxt_G1.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                        if (!gcMsg.defInvGrpCd_G3.getValue().equals(chkGcMsg.defInvGrpCd_G3.getValue())) {
                            String[] args = {"Default Grouping" };
                            gcMsg.defInvGrpCd_G3.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.defInvGrpCd_G3.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                        if (!gcMsg.custEffLvlCd_G3.getValue().equals(chkGcMsg.custEffLvlCd_G3.getValue())) {
                            String[] args = {"Effective Level" };
                            gcMsg.custEffLvlCd_G3.setErrorInfo(1, NMAM0293E, args);
                            chkGcMsg.custEffLvlCd_G3.setErrorInfo(1, NMAM0293E, args);
                            rtnFlg = false;
                        }
                    }
                }
            }
            
            // START 2024/03/12 T.Nagae [QC#63552,ADD]
            if (CUST_INV_SRC.RNW_UPLFT.equals(custInvSrcCd)) {
                List<String> rnwUplftTarget = null;
                String rnwUplftTargetVal = ZYPCodeDataUtil.getVarCharConstValue(NMAL6700Constant.NMAL6700_RNW_UPLFT_TARGET, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(rnwUplftTargetVal)) {
                    rnwUplftTarget = Arrays.asList(rnwUplftTargetVal.split(","));
                }
                if (!rnwUplftTarget.contains(gcMsg.custBllgVcleCd_G3.getValue())) {
                    String custBllgVcleNm = ZYPCodeDataUtil.getName(CUST_BLLG_VCLE.class, glblCmpyCd, gcMsg.custBllgVcleCd_G3.getValue());
                    gcMsg.custBllgVcleCd_G3.setErrorInfo(1, NMAM0076E, new String[] {"Renewal/Upliftment", custBllgVcleNm });
                }
            }
            // END 2024/03/12 T.Nagae [QC#63552,ADD]
        }
        return rtnFlg;
    }

    private boolean updateDsAcctCrPrfl(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        DS_ACCT_CR_PRFLTMsg updateDsAcctCrPrflTMsg = NMAL6700CommonLogic.findDsAcctCrPrflForUpdate(glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        if (updateDsAcctCrPrflTMsg == null) {
            DS_ACCT_CR_PRFLTMsg dsAcctCrPrflTMsg = createNewDsAcctCrPrflTMsg(bizMsg, glblCmpyCd);

            S21FastTBLAccessor.create(dsAcctCrPrflTMsg);
            if (!RTNCD_NORMAL.equals(dsAcctCrPrflTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0176E, new String[] {"DS_ACCT_CR_PRFL" });
                return false;
            }

        } else {
            if (!ZYPDateUtil.isSameTimeStamp(bizMsg.ezUpTime_U1.getValue(), bizMsg.ezUpTimeZone_U1.getValue(), updateDsAcctCrPrflTMsg.ezUpTime.getValue(), updateDsAcctCrPrflTMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            DS_ACCT_CR_PRFLTMsg beforeUpdateDsAcctCrPrflTMsg = new DS_ACCT_CR_PRFLTMsg();
            DS_ACCT_CR_PRFLTMsg.copy(updateDsAcctCrPrflTMsg, null, beforeUpdateDsAcctCrPrflTMsg, null);
            if (!setDsAcctCrPrflData(updateDsAcctCrPrflTMsg, bizMsg, beforeUpdateDsAcctCrPrflTMsg)) {
                return false;
            }
            // START 2024/03/11 S.Ikariya [QC#63499, ADD]
            if (!cascadeToCustCrPrfl(bizMsg, sharedMsg, beforeUpdateDsAcctCrPrflTMsg, glblCmpyCd)) {
                return false;
            }
            // END 2024/03/11 S.Ikariya [QC#63499, ADD]
            //Change check
            if (changeFieldsCheckForDsAcctCrPrfl(updateDsAcctCrPrflTMsg, beforeUpdateDsAcctCrPrflTMsg)) {
                //Change has been made.
                S21FastTBLAccessor.update(updateDsAcctCrPrflTMsg);
                if (!RTNCD_NORMAL.equals(updateDsAcctCrPrflTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_CR_PRFL" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);

            } else {
                return true;
            }
        }
        return true;
    }

    // START 2024/03/11 S.Ikariya [QC#63499, ADD]
    private boolean cascadeToBillToCust(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, SELL_TO_CUSTTMsg beforeUpdateSellToCustTMsg) {

        S21SsmEZDResult resBillToCust = NMAL6700Query.getInstance().getBillToCust(cMsg.dsAcctNum_H1.getValue());
        if (!resBillToCust.isCodeNormal()) {
            return true;
        }
        Boolean updateTMsgFlg = false;
        List<Map<String, Object>> resultBillToCustList = (List<Map<String, Object>>) resBillToCust.getResultObject();
        for (int i = 0; i < resultBillToCustList.size(); i++) {
            Map<String, Object> resultBillToCust = resultBillToCustList.get(i);
            BigDecimal billToCustPk = (BigDecimal) resultBillToCust.get("BILL_TO_CUST_PK");

            BILL_TO_CUSTTMsg updateBillToCustTMsg = NMAL6700CommonLogic.findBillToCustForUpdate(this.getGlobalCompanyCode(), billToCustPk);
            if (isNotEquals(cMsg.arStmtFlg_U1.getValue(), beforeUpdateSellToCustTMsg.arStmtFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.arStmtFlg_U1.getValue())) {
                    updateBillToCustTMsg.arStmtFlg.setValue(cMsg.arStmtFlg_U1.getValue());
                } else {
                    updateBillToCustTMsg.arStmtFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.sendCrBalStmtFlg_U1.getValue(), beforeUpdateSellToCustTMsg.sendCrBalStmtFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.sendCrBalStmtFlg_U1.getValue())) {
                    updateBillToCustTMsg.sendCrBalStmtFlg.setValue(cMsg.sendCrBalStmtFlg_U1.getValue());
                } else {
                    updateBillToCustTMsg.sendCrBalStmtFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.arStmtIssCycleCd_U3.getValue(), beforeUpdateSellToCustTMsg.arStmtIssDay.getValue())) {
                updateBillToCustTMsg.arStmtIssDay.setValue(cMsg.arStmtIssCycleCd_U3.getValue());
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.cltCustTpCd_U1.getValue(), beforeUpdateSellToCustTMsg.cltCustTpCd.getValue())) {
                updateBillToCustTMsg.cltCustTpCd.setValue(cMsg.cltCustTpCd_U1.getValue());
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.lateFeeAmt_U1.getValue(), beforeUpdateSellToCustTMsg.lateFeeAmt.getValue())) {
                if (cMsg.lateFeeAmt_U1.getValue() != null){
                    updateBillToCustTMsg.lateFeeAmt.setValue(cMsg.lateFeeAmt_U1.getValue());
                    updateTMsgFlg = true;
                }else{
                    updateBillToCustTMsg.lateFeeAmt.setValue(BigDecimal.ZERO);
                    updateTMsgFlg = true;
                }
            }
            if (isNotEquals(cMsg.dsTaxExemFlg_U1.getValue(), beforeUpdateSellToCustTMsg.dsTaxExemFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsTaxExemFlg_U1.getValue())) {
                    updateBillToCustTMsg.dsTaxExemFlg.setValue(cMsg.dsTaxExemFlg_U1.getValue());
                } else {
                    updateBillToCustTMsg.dsTaxExemFlg.setValue(ZYPConstant.FLG_OFF_N);
                }
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.cltPtfoPk_U1.getValue(), beforeUpdateSellToCustTMsg.cltPtfoPk.getValue())) {
                updateBillToCustTMsg.cltPtfoPk.setValue(cMsg.cltPtfoPk_U1.getValue());
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.dsTaxGrpExemCd_U3.getValue(), beforeUpdateSellToCustTMsg.dsTaxGrpExemCd.getValue())) {
                updateBillToCustTMsg.dsTaxGrpExemCd.setValue(cMsg.dsTaxGrpExemCd_U3.getValue());
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.dsTaxPrntTpCd_U3.getValue(), beforeUpdateSellToCustTMsg.dsTaxPrntTpCd.getValue())) {
                updateBillToCustTMsg.dsTaxPrntTpCd.setValue(cMsg.dsTaxPrntTpCd_U3.getValue());
                updateTMsgFlg = true;
            }
            if (isNotEquals(cMsg.dsExemExprDt_U1.getValue(), beforeUpdateSellToCustTMsg.dsExemExprDt.getValue())) {
                updateBillToCustTMsg.dsExemExprDt.setValue(cMsg.dsExemExprDt_U1.getValue());
                updateTMsgFlg = true;
            }
            if (updateTMsgFlg) {
                S21FastTBLAccessor.update(updateBillToCustTMsg);
                if (!RTNCD_NORMAL.equals(updateBillToCustTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                    return false;
                }
            }
        }
        return updateTMsgFlg;
    }

    // END 2024/03/11 S.Ikariya [QC#63499, ADD]
    // START 2024/03/11 S.Ikariya [QC#63499, ADD]
    private boolean cascadeToCustCrPrfl(NMAL6700CMsg cMsg, NMAL6700SMsg sMsg, DS_ACCT_CR_PRFLTMsg beforeUpdateDsAcctCrPrflTMsg, String glblCmpyCd) {

        S21SsmEZDResult resBillToCust = NMAL6700Query.getInstance().getBillToCust(cMsg.dsAcctNum_H1.getValue());
        if (!resBillToCust.isCodeNormal()) {
            return false;
        }
        boolean updateFlg = false;
        if (isNotEquals(cMsg.autoCashHrchCd_U3.getValue(), beforeUpdateDsAcctCrPrflTMsg.autoCashHrchCd.getValue())) {
            updateFlg = true;
        }
        if (updateFlg) {
            List<Map<String, Object>> resultBillToCustList = (List<Map<String, Object>>) resBillToCust.getResultObject();
            for (int i = 0; i < resultBillToCustList.size(); i++) {
                CUST_CR_PRFLTMsg custCrPrfl = new CUST_CR_PRFLTMsg();
                Map<String, Object> resultBillToCust = resultBillToCustList.get(i);
                BigDecimal billToCustPk = (BigDecimal) resultBillToCust.get("BILL_TO_CUST_PK");
                ZYPEZDItemValueSetter.setValue(custCrPrfl.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(custCrPrfl.billToCustPk, billToCustPk);
                CUST_CR_PRFLTMsg updateCustCrPrfl = (CUST_CR_PRFLTMsg) EZDTBLAccessor.findByKey(custCrPrfl);
                if (updateCustCrPrfl != null) {
                    updateCustCrPrfl.autoCashHrchCd.setValue(cMsg.autoCashHrchCd_U3.getValue());
                    S21FastTBLAccessor.update(updateCustCrPrfl);
                    if (!RTNCD_NORMAL.equals(updateCustCrPrfl.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM0177E, new String[] {"CUST_CR_PRFL" });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // END 2024/03/11 S.Ikariya [QC#63499, ADD]
    private boolean updateArAmount(NMAL6700CMsg cMsg, String glblCmpyCd) {

        if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(cMsg.rgtnStsCd_H1.getValue())
                || RGTN_STS.TERMINATED.equals(cMsg.rgtnStsCd_H1.getValue())) {

            if (ZYPCommonFunc.hasValue(cMsg.crLimitAmt_U1)
                    && cMsg.crLimitAmt_U1.getValue().compareTo(BigDecimal.ZERO) > 0) {
                String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

                if (!NMAL6700CommonLogic.callCreditProfileUpdateApi(cMsg, slsDt, glblCmpyCd)) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean masterCheckForInvBllg(NMAL6700CMsg cMsg, String glblCmpyCd) {
        boolean rtnFlg = true;
        int cnt = cMsg.G.getValidCount();

        for (int i = 0; i < cnt; i++) {
            NMAL6700_GCMsg gcMsg = cMsg.G.no(i);
            if (!NMAL6700CommonLogic.chkContact(cMsg, gcMsg, glblCmpyCd)) {
                rtnFlg = false;
            }
            if (!NMAL6700CommonLogic.chkBillVehicle(cMsg, gcMsg, glblCmpyCd)) {
                rtnFlg = false;
            }
            if (!NMAL6700CommonLogic.chkResource(gcMsg, glblCmpyCd)) {
                rtnFlg = false;
            }

        }

        return rtnFlg;
    }

    /**
     * 
     * @param cMsg NMAL6700CMsg
     * @return NMAL6700CMsg
     */
    private String getLocNm(NMAL6700CMsg cMsg) {
        return S21StringUtil.subStringByLength(cMsg.dsAcctNm_H1.getValue(), 0, COL_LOC_NM_MAX_DIGIT_LENGTH);
    }

    /**
     * 
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     * @param glblCmpyCd String
     */
    private void updateAcctNmForLocInfo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (!bizMsg.dsAcctNm_H1.getValue().equals(bizMsg.dsAcctNm_BK.getValue())) {

            if (DS_ACCT_TP.CUSTOMER.equals(bizMsg.dsAcctTpCd_H3.getValue())) {
                // Update Location Number
                // PTY_LOC_WRK
                if (!updateAcctNmForPtyLocWrk(bizMsg, glblCmpyCd)) {
                    return;
                }
                // SHIP_TO_CUST
                if (!updateAcctNmForShipToCust(bizMsg, glblCmpyCd)) {
                    return;
                }
                // BILL_TO_CUST
                if (!updateAcctNmForBillToCust(bizMsg, glblCmpyCd)) {
                    return;
                }
                // ALT_PAYER
                if (!updateAcctNmForAltPayer(bizMsg, glblCmpyCd)) {
                    return;
                }
                // PRIN_CUST
                if (!updateAcctNmForPrinCust(bizMsg, glblCmpyCd)) {
                    return;
                }
            } else {
                // Update Location Number
                // PTY_LOC_WRK
                if (!updateAcctNmForProsPtyLocWrk(bizMsg, glblCmpyCd)) {
                    return;
                }
            }
        }
    }

    /**
     * updateAcctNmForProsPtyLocWrk
     * @param glblCmpyCd String
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean updateAcctNmForProsPtyLocWrk(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult result = NMAL6700Query.getInstance().getProsPtyLocWrkList(bizMsg.dsAcctNum_H1.getValue(), glblCmpyCd);
        List<PROS_PTY_LOC_WRKTMsg> resultProsPtyLocWrkList = new ArrayList<PROS_PTY_LOC_WRKTMsg>();

        if (!result.isCodeNormal()) {
            // Before Create DS_ACCT_LOC
            PROS_PTY_LOC_WRKTMsg prmTMsg = new PROS_PTY_LOC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prmTMsg.ptyLocPk, bizMsg.ptyLocPk_H1.getValue());
            prmTMsg = (PROS_PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
            if (prmTMsg == null) {
                // Not error
                return true;
            } else {
                resultProsPtyLocWrkList.add(prmTMsg);

            }
        } else {
            resultProsPtyLocWrkList = (List) result.getResultObject();
        }

        for (int i = 0; i < resultProsPtyLocWrkList.size(); i++) {
            PROS_PTY_LOC_WRKTMsg tMsg = resultProsPtyLocWrkList.get(i);
            // Update PROS_PTY_LOC_WRK
            PROS_PTY_LOC_WRKTMsg updateProsPtyLocWrkTmsg = NMAL6700CommonLogic.findProsPtyLocWrkForUpdate(glblCmpyCd, tMsg.ptyLocPk.getValue());
            if (updateProsPtyLocWrkTmsg == null) {
                String[] args = {"PROS_PTY_LOC_WRK" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updateProsPtyLocWrkTmsg.locNm, getLocNm(bizMsg));
            ZYPEZDItemValueSetter.setValue(updateProsPtyLocWrkTmsg.dsAcctNm, bizMsg.dsAcctNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(updateProsPtyLocWrkTmsg.dunsAcctNmChngTpCd, DUNS_CHNG_TP.UPDATED);
            S21FastTBLAccessor.update(updateProsPtyLocWrkTmsg);
            if (!RTNCD_NORMAL.equals(updateProsPtyLocWrkTmsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"PROS_PTY_LOC_WRK" });
                return false;
            }
        }
        return true;
    }

    /**
     * updateAcctNmForPtyLocWrk
     * @param glblCmpyCd String
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean updateAcctNmForPtyLocWrk(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult result = NMAL6700Query.getInstance().getPtyLocWrkList(bizMsg.dsAcctNum_H1.getValue(), glblCmpyCd);
        List<PTY_LOC_WRKTMsg> resultPtyLocWrkList = new ArrayList<PTY_LOC_WRKTMsg>();

        if (!result.isCodeNormal()) {
            // Before Create DS_ACCT_LOC
            PTY_LOC_WRKTMsg prmTMsg = new PTY_LOC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prmTMsg.ptyLocPk, bizMsg.ptyLocPk_H1.getValue());
            prmTMsg = (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
            if (prmTMsg == null) {
                // Not error
                return true;
            } else {
                resultPtyLocWrkList.add(prmTMsg);

            }
        } else {
            resultPtyLocWrkList = (List) result.getResultObject();
        }

        for (int i = 0; i < resultPtyLocWrkList.size(); i++) {
            PTY_LOC_WRKTMsg tMsg = resultPtyLocWrkList.get(i);
            // Update PTY_LOC_WRK
            PTY_LOC_WRKTMsg updatePtyLocWrkTmsg = NMAL6700CommonLogic.findPtyLocWrkForUpdate(glblCmpyCd, tMsg.ptyLocPk.getValue());
            if (updatePtyLocWrkTmsg == null) {
                String[] args = {"PTY_LOC_WRK" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updatePtyLocWrkTmsg.locNm, getLocNm(bizMsg));
            ZYPEZDItemValueSetter.setValue(updatePtyLocWrkTmsg.dsAcctNm, bizMsg.dsAcctNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(updatePtyLocWrkTmsg.dunsAcctNmChngTpCd, DUNS_CHNG_TP.UPDATED);
            S21FastTBLAccessor.update(updatePtyLocWrkTmsg);
            if (!RTNCD_NORMAL.equals(updatePtyLocWrkTmsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"PTY_LOC_WRK" });
                return false;
            }
        }
        return true;
    }

    /**
     * updateAcctNmForShipToCust
     * @param glblCmpyCd String
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean updateAcctNmForShipToCust(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult resShipToCust = NMAL6700Query.getInstance().getShipToCust(bizMsg.dsAcctNum_H1.getValue());
        if (!resShipToCust.isCodeNormal()) {
            // Not error
            return true;
        }

        List<Map<String, Object>> resultShipToCustList = (List<Map<String, Object>>) resShipToCust.getResultObject();
        for (int i = 0; i < resultShipToCustList.size(); i++) {
            Map<String, Object> resultShipToCust = resultShipToCustList.get(i);
            BigDecimal shipToCustPk = (BigDecimal) resultShipToCust.get("SHIP_TO_CUST_PK");
            // Update SHIP_TO_CUST
            SHIP_TO_CUSTTMsg updateShipToCustTMsg = NMAL6700CommonLogic.findShipToCustForUpdate(glblCmpyCd, shipToCustPk);
            if (updateShipToCustTMsg == null) {
                String[] args = {"SHIP_TO_CUST" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updateShipToCustTMsg.locNm, getLocNm(bizMsg));
            ZYPEZDItemValueSetter.setValue(updateShipToCustTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);
            S21FastTBLAccessor.update(updateShipToCustTMsg);
            if (!RTNCD_NORMAL.equals(updateShipToCustTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"SHIP_TO_CUST" });
                return false;
            }
        }
        return true;
    }

    /**
     * updateAcctNmForBillToCust
     * @param glblCmpyCd String
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean updateAcctNmForBillToCust(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult resBillToCust = NMAL6700Query.getInstance().getBillToCustNotLookRgtnStsCd(bizMsg.dsAcctNum_H1.getValue());
        if (!resBillToCust.isCodeNormal()) {
            // Not error
            return true;
        }
        List<Map<String, Object>> resultBillToCustList = (List<Map<String, Object>>) resBillToCust.getResultObject();
        for (int i = 0; i < resultBillToCustList.size(); i++) {
            Map<String, Object> resultBillToCust = resultBillToCustList.get(i);
            BigDecimal billToCustPk = (BigDecimal) resultBillToCust.get("BILL_TO_CUST_PK");
            // Update BILL_TO_CUST
            BILL_TO_CUSTTMsg updateBillToCustTMsg = NMAL6700CommonLogic.findBillToCustForUpdate(glblCmpyCd, billToCustPk);
            if (updateBillToCustTMsg == null) {
                String[] args = {"BILL_TO_CUST" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updateBillToCustTMsg.locNm, getLocNm(bizMsg));
            ZYPEZDItemValueSetter.setValue(updateBillToCustTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);
            S21FastTBLAccessor.update(updateBillToCustTMsg);
            if (!RTNCD_NORMAL.equals(updateBillToCustTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                return false;
            }

            // Update INV_RCPNT
            INV_RCPNTTMsg updateInvRcpntTMsg = NMAL6700CommonLogic.findInvRcpntForUpdate(glblCmpyCd, billToCustPk);
            if (updateInvRcpntTMsg == null) {
                String[] args = {"INV_RCPNTTMsg" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updateInvRcpntTMsg.locNm, getLocNm(bizMsg));
            S21FastTBLAccessor.update(updateInvRcpntTMsg);
            if (!RTNCD_NORMAL.equals(updateInvRcpntTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"INV_RCPNTTMsg" });
                return false;
            }
        }
        return true;
    }

    /**
     * updateAcctNmForAltPayerList
     * @param glblCmpyCd String
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean updateAcctNmForAltPayer(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        // Update BILL_TO_CUST
        S21SsmEZDResult result = NMAL6700Query.getInstance().getAltPayerList(bizMsg.dsAcctNum_H1.getValue(), glblCmpyCd);
        if (!result.isCodeNormal()) {
            // Not error
            return true;
        }

        List<ALT_PAYERTMsg> resultAltPayerList = (List<ALT_PAYERTMsg>) result.getResultObject();
        for (int i = 0; i < resultAltPayerList.size(); i++) {
            ALT_PAYERTMsg tMsg = resultAltPayerList.get(i);
            ALT_PAYERTMsg updateAltPayerTMsg = new ALT_PAYERTMsg();
            ZYPEZDItemValueSetter.setValue(updateAltPayerTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(updateAltPayerTMsg.sellToCustCd, bizMsg.dsAcctNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(updateAltPayerTMsg.billToCustCd, tMsg.billToCustCd);
            updateAltPayerTMsg = (ALT_PAYERTMsg) S21FastTBLAccessor.findByKey(updateAltPayerTMsg);

            if (updateAltPayerTMsg == null) {
                String[] args = {"ALT_PAYER" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(updateAltPayerTMsg.locNm, getLocNm(bizMsg));
            S21FastTBLAccessor.update(updateAltPayerTMsg);
            if (!RTNCD_NORMAL.equals(updateAltPayerTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"ALT_PAYER" });
                return false;
            }
        }
        return true;
    }

    /**
     * updateAcctNmForPrinCust
     * @param glblCmpyCd String
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean updateAcctNmForPrinCust(NMAL6700CMsg bizMsg, String glblCmpyCd) {
        S21SsmEZDResult result = NMAL6700Query.getInstance().getPrinCust(glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        List<Map<String, Object>> resultPrinCustList = new ArrayList<Map<String, Object>>();
        if (!result.isCodeNormal()) {
            // 2018/08/17 QC#27786 Add Start
            if(ZYPCommonFunc.hasValue(bizMsg.ptyLocPk_H1)){
                result = NMAL6700Query.getInstance().getPrinCustBefCreateAcctLoc(glblCmpyCd, bizMsg.ptyLocPk_H1.getValue());
            }
            // 2018/08/17 QC#27786 Add End
            if (!result.isCodeNormal()) {
                result = NMAL6700Query.getInstance().getPrinCustAftDelPrimLocFlg(glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
                if (!result.isCodeNormal()) {
                    // Not error
                    return true;
                }
            }
        }
        resultPrinCustList = (List<Map<String, Object>>) result.getResultObject();
        for (int i = 0; i < resultPrinCustList.size(); i++) {
            Map<String, Object> resultBillToCustFromDsBillToCust = resultPrinCustList.get(i);
            BigDecimal prinCustPk = (BigDecimal) resultBillToCustFromDsBillToCust.get("PRIN_CUST_PK");
            // Update PRIN_CUST
            PRIN_CUSTTMsg updatePrinCustTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, prinCustPk);
            if (updatePrinCustTMsg == null) {
                String[] args = {"PRIN_CUST" };
                bizMsg.setMessageInfo(NMAM8111E, args);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.locNm, getLocNm(bizMsg));

            ZYPEZDItemValueSetter.setValue(updatePrinCustTMsg.dsAcctNm, bizMsg.dsAcctNm_H1);

            S21FastTBLAccessor.update(updatePrinCustTMsg);
            if (!RTNCD_NORMAL.equals(updatePrinCustTMsg.getReturnCode())) {
                bizMsg.setMessageInfo(NMAM0177E, new String[] {"PRIN_CUST" });
                return false;
            }
        }
        return true;
    }

    /**
     * coaCmbnCheck 
     * @param bizMsg NMAL6700CMsg
     * @return boolean
     */
    private boolean coaCmbnCheck(NMAL6700CMsg bizMsg) {
        // GL Code Combination Check API
        NFZC102001 nfzc102001 = new NFZC102001();
        NFZC102001PMsg paramMsg = new NFZC102001PMsg();

        paramMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        paramMsg.coaAfflCd.setValue(bizMsg.coaAfflCd_H1.getValue());
        paramMsg.coaChCd.setValue(bizMsg.coaChCd_H1.getValue());
        // QC#19433 Start
        ZYPEZDItemValueSetter.setValue(paramMsg.resrcObjNm, "NMAL6700001");
        // QC#19433 End


        nfzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

        List msgIdList = S21ApiUtil.getXxMsgIdList(paramMsg);

        if (msgIdList != null && msgIdList.size() > 0) {
            for (int i = 0; i < msgIdList.size(); i++) {
                bizMsg.setMessageInfo((String) msgIdList.get(0));
                return false;
            }
        }
        return true;
    }

    /**
     * openTrnCheck 
     * 
     * @param bizMsg NMAL6700CMsg
     * @param sharedMsg NMAL6700SMsg
     * @return boolean
     */
    private boolean openTrnCheck(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        if (!ZYPConstant.CHKBOX_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())
             && ZYPConstant.CHKBOX_ON_Y.equals(sharedMsg.xxChkBox_H1.getValue())) {
              String glblCmpyCd = getGlobalCompanyCode();
              String acctNum = bizMsg.dsAcctNum_H1.getValue();

            // 2019/12/13 QC#54882 Mod Start
//              S21SsmEZDResult result = NMAL6700Query.getInstance().countCpoOpenOrder(acctNum, glblCmpyCd);
//              BigDecimal count = BigDecimal.ZERO;
//              if (!result.isCodeNotFound()) {
//                  count = (BigDecimal) result.getResultObject();
//              }
//
//              if (BigDecimal.ONE.compareTo(count) == 0) {
//                  return false;
//              }
//
//              result = null;
//              result = NMAL6700Query.getInstance().countOpenReturnOrder(acctNum, glblCmpyCd);
//              count = BigDecimal.ZERO;
//              if (!result.isCodeNotFound()) {
//                  count = (BigDecimal) result.getResultObject();
//              }
//
//              if (BigDecimal.ONE.compareTo(count) == 0) {
//                  return false;
//              }
//
//              result = null;
//              result = NMAL6700Query.getInstance().countOpenMachineMasterCheck(acctNum, glblCmpyCd);
//              count = BigDecimal.ZERO;
//              if (!result.isCodeNotFound()) {
//                  count = (BigDecimal) result.getResultObject();
//              }
//
//              if (BigDecimal.ONE.compareTo(count) == 0) {
//                  return false;
//              }
//
//              result = null;
//              result = NMAL6700Query.getInstance().countContractDsContrNum(acctNum, glblCmpyCd);
//              count = BigDecimal.ZERO;
//              if (!result.isCodeNotFound()) {
//                  count = (BigDecimal) result.getResultObject();
//              }
//
//              if (BigDecimal.ONE.compareTo(count) == 0) {
//                  return false;
//              }
//
//              result = null;
//              result = NMAL6700Query.getInstance().countServiceCall(acctNum, glblCmpyCd);
//              count = BigDecimal.ZERO;
//              if (!result.isCodeNotFound()) {
//                  count = (BigDecimal) result.getResultObject();
//              }
//
//              if (BigDecimal.ONE.compareTo(count) == 0) {
//                  return false;
//              }
            S21SsmEZDResult result = NMAL6700Query.getInstance().countOpenTransaction(acctNum, glblCmpyCd);
            BigDecimal count = BigDecimal.ZERO;
            if (!result.isCodeNotFound()) {
                count = (BigDecimal) result.getResultObject();
            }
            if (BigDecimal.ONE.compareTo(count) == 0) {
                return false;
            }
            // 2019/12/13 QC#54882 Mod End

        }
        return true;
    }

    private boolean changeFieldsCheckForCust(SELL_TO_CUSTTMsg updateSellToCustTMsg, SELL_TO_CUSTTMsg beforeUpdateSellToCustTMsg) {

    if (isNotEquals(updateSellToCustTMsg.dsAcctNm.getValue(), beforeUpdateSellToCustTMsg.dsAcctNm.getValue())
        || isNotEquals(updateSellToCustTMsg.rgtnStsCd.getValue(), beforeUpdateSellToCustTMsg.rgtnStsCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctInacRsnCd.getValue(), beforeUpdateSellToCustTMsg.dsAcctInacRsnCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctTpCd.getValue(), beforeUpdateSellToCustTMsg.dsAcctTpCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctItrlFlg.getValue(), beforeUpdateSellToCustTMsg.dsAcctItrlFlg.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctClsCd.getValue(), beforeUpdateSellToCustTMsg.dsAcctClsCd.getValue())
                || isNotEquals(updateSellToCustTMsg.coaChCd.getValue(), beforeUpdateSellToCustTMsg.coaChCd.getValue())
                || isNotEquals(updateSellToCustTMsg.coaAfflCd.getValue(), beforeUpdateSellToCustTMsg.coaAfflCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctDlrCd.getValue(), beforeUpdateSellToCustTMsg.dsAcctDlrCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctLegalNm.getValue(), beforeUpdateSellToCustTMsg.dsAcctLegalNm.getValue())
                || isNotEquals(updateSellToCustTMsg.dbaNm.getValue(), beforeUpdateSellToCustTMsg.dbaNm.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctDunsNm.getValue(), beforeUpdateSellToCustTMsg.dsAcctDunsNm.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctAltNm.getValue(), beforeUpdateSellToCustTMsg.dsAcctAltNm.getValue())
                || isNotEquals(updateSellToCustTMsg.dsXtrnlRefTxt.getValue(), beforeUpdateSellToCustTMsg.dsXtrnlRefTxt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsDataSrcTxt.getValue(), beforeUpdateSellToCustTMsg.dsDataSrcTxt.getValue())
                || isNotEquals(updateSellToCustTMsg.effThruDt.getValue(), beforeUpdateSellToCustTMsg.effThruDt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctActvCustFlg.getValue(), beforeUpdateSellToCustTMsg.dsAcctActvCustFlg.getValue())
                // 2023/11/14 QC#61924 Add Start
                || isNotEquals(updateSellToCustTMsg.deacNewTrxFlg.getValue(), beforeUpdateSellToCustTMsg.deacNewTrxFlg.getValue())
                // 2023/11/14 QC#61924 Add End
                // Transaction Tab
                || isNotEquals(updateSellToCustTMsg.coaCcCd.getValue(), beforeUpdateSellToCustTMsg.coaCcCd.getValue())
                // Collections Tab
                || isNotEquals(updateSellToCustTMsg.arStmtFlg.getValue(), beforeUpdateSellToCustTMsg.arStmtFlg.getValue())
                // START 2018/04/23 [QC#23882, ADD]
                || isNotEquals(updateSellToCustTMsg.sendCrBalStmtFlg.getValue(), beforeUpdateSellToCustTMsg.sendCrBalStmtFlg.getValue())
                // END   2018/04/23 [QC#23882, ADD]
                || isNotEquals(updateSellToCustTMsg.arStmtIssDay.getValue(), beforeUpdateSellToCustTMsg.arStmtIssDay.getValue())
                || isNotEquals(updateSellToCustTMsg.dunFlg.getValue(), beforeUpdateSellToCustTMsg.dunFlg.getValue())
                || isNotEquals(updateSellToCustTMsg.cltCustTpCd.getValue(), beforeUpdateSellToCustTMsg.cltCustTpCd.getValue())
                || isNotEquals(updateSellToCustTMsg.cltPtfoPk.getValue(), beforeUpdateSellToCustTMsg.cltPtfoPk.getValue())
                || isNotEquals(updateSellToCustTMsg.dsCltAcctStsCd.getValue(), beforeUpdateSellToCustTMsg.dsCltAcctStsCd.getValue())
                || isNotEquals(updateSellToCustTMsg.lateFeeFlg.getValue(), beforeUpdateSellToCustTMsg.lateFeeFlg.getValue())
                || isNotEquals(updateSellToCustTMsg.lateFeeAmt.getValue(), beforeUpdateSellToCustTMsg.lateFeeAmt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsCustTaxCd.getValue(), beforeUpdateSellToCustTMsg.dsCustTaxCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsCustTaxCalcCd.getValue(), beforeUpdateSellToCustTMsg.dsCustTaxCalcCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsTaxPrntTpCd.getValue(), beforeUpdateSellToCustTMsg.dsTaxPrntTpCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsTaxExemFlg.getValue(), beforeUpdateSellToCustTMsg.dsTaxExemFlg.getValue())
                || isNotEquals(updateSellToCustTMsg.dsExemExprDt.getValue(), beforeUpdateSellToCustTMsg.dsExemExprDt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsTaxGrpExemCd.getValue(), beforeUpdateSellToCustTMsg.dsTaxGrpExemCd.getValue())
                //Invoice/Billing
                || isNotEquals(updateSellToCustTMsg.defBaseTpCd.getValue(), beforeUpdateSellToCustTMsg.defBaseTpCd.getValue())
                || isNotEquals(updateSellToCustTMsg.defBaseCycleCd.getValue(), beforeUpdateSellToCustTMsg.defBaseCycleCd.getValue())
                || isNotEquals(updateSellToCustTMsg.defUsgTpCd.getValue(), beforeUpdateSellToCustTMsg.defUsgTpCd.getValue())
                || isNotEquals(updateSellToCustTMsg.defUsgCycleCd.getValue(), beforeUpdateSellToCustTMsg.defUsgCycleCd.getValue())
                // START 2022/03/22 [QC#59683, ADD]
                || isNotEquals(updateSellToCustTMsg.dsInvTgtrTpCd.getValue(), beforeUpdateSellToCustTMsg.dsInvTgtrTpCd.getValue())
                // END   2022/03/22 [QC#59683, ADD]
                || isNotEquals(updateSellToCustTMsg.dsBillTgtrFlg.getValue(), beforeUpdateSellToCustTMsg.dsBillTgtrFlg.getValue())) {
            return true;
        }
        return false;

    }
    private boolean changeFieldsCheckForPros(DS_ACCT_PROSTMsg updateDsAcctProsTMsg, DS_ACCT_PROSTMsg beforeUpdateDsAcctProsTMsg) {

        if (isNotEquals(updateDsAcctProsTMsg.dsAcctNm.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctNm.getValue())
            || isNotEquals(updateDsAcctProsTMsg.rgtnStsCd.getValue(), beforeUpdateDsAcctProsTMsg.rgtnStsCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctInacRsnCd.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctInacRsnCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctTpCd.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctTpCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctItrlFlg.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctItrlFlg.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctClsCd.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctClsCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.coaChCd.getValue(), beforeUpdateDsAcctProsTMsg.coaChCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.coaAfflCd.getValue(), beforeUpdateDsAcctProsTMsg.coaAfflCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctDlrCd.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctDlrCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctLegalNm.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctLegalNm.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dbaNm.getValue(), beforeUpdateDsAcctProsTMsg.dbaNm.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctDunsNm.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctDunsNm.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctAltNm.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctAltNm.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsXtrnlRefTxt.getValue(), beforeUpdateDsAcctProsTMsg.dsXtrnlRefTxt.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsDataSrcTxt.getValue(), beforeUpdateDsAcctProsTMsg.dsDataSrcTxt.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.effThruDt.getValue(), beforeUpdateDsAcctProsTMsg.effThruDt.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsAcctActvCustFlg.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctActvCustFlg.getValue())
                    // Transaction Tab
                    || isNotEquals(updateDsAcctProsTMsg.coaCcCd.getValue(), updateDsAcctProsTMsg.effThruDt.getValue())
                    // Collections Tab
                    || isNotEquals(updateDsAcctProsTMsg.arStmtFlg.getValue(), beforeUpdateDsAcctProsTMsg.arStmtFlg.getValue())
                    // START 2018/01/25 [QC#23882, ADD]
                    || isNotEquals(updateDsAcctProsTMsg.sendCrBalStmtFlg.getValue(), beforeUpdateDsAcctProsTMsg.sendCrBalStmtFlg.getValue())                
                    // END   2018/01/25 [QC#23882, ADD]
                    || isNotEquals(updateDsAcctProsTMsg.arStmtIssDay.getValue(), beforeUpdateDsAcctProsTMsg.arStmtIssDay.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dunFlg.getValue(), beforeUpdateDsAcctProsTMsg.dunFlg.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.cltCustTpCd.getValue(), beforeUpdateDsAcctProsTMsg.cltCustTpCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.cltPtfoPk.getValue(), beforeUpdateDsAcctProsTMsg.cltPtfoPk.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsCltAcctStsCd.getValue(), beforeUpdateDsAcctProsTMsg.dsCltAcctStsCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.lateFeeFlg.getValue(), beforeUpdateDsAcctProsTMsg.lateFeeFlg.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.lateFeeAmt.getValue(), beforeUpdateDsAcctProsTMsg.lateFeeAmt.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsCustTaxCd.getValue(), beforeUpdateDsAcctProsTMsg.dsCustTaxCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsCustTaxCalcCd.getValue(), beforeUpdateDsAcctProsTMsg.dsCustTaxCalcCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsTaxPrntTpCd.getValue(), beforeUpdateDsAcctProsTMsg.dsTaxPrntTpCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsTaxExemFlg.getValue(), beforeUpdateDsAcctProsTMsg.dsTaxExemFlg.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsExemExprDt.getValue(), beforeUpdateDsAcctProsTMsg.dsExemExprDt.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.dsTaxGrpExemCd.getValue(), beforeUpdateDsAcctProsTMsg.dsTaxGrpExemCd.getValue())
                    //Invoice/Billing
                    || isNotEquals(updateDsAcctProsTMsg.defBaseTpCd.getValue(), beforeUpdateDsAcctProsTMsg.defBaseTpCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.defBaseCycleCd.getValue(), beforeUpdateDsAcctProsTMsg.defBaseCycleCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.defUsgTpCd.getValue(), beforeUpdateDsAcctProsTMsg.defUsgTpCd.getValue())
                    || isNotEquals(updateDsAcctProsTMsg.defUsgCycleCd.getValue(), beforeUpdateDsAcctProsTMsg.defUsgCycleCd.getValue())
                    // START 2022/03/22 [QC#59683, ADD]
                    || isNotEquals(updateDsAcctProsTMsg.dsInvTgtrTpCd.getValue(), beforeUpdateDsAcctProsTMsg.dsInvTgtrTpCd.getValue())
                    // END   2022/03/22 [QC#59683, ADD]
                    || isNotEquals(updateDsAcctProsTMsg.dsBillTgtrFlg.getValue(), beforeUpdateDsAcctProsTMsg.dsBillTgtrFlg.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForPtyLocWrk(PTY_LOC_WRKTMsg updatePtyLocWrkTMsg, PTY_LOC_WRKTMsg beforeUpdatePtyLocWrkTMsg) {

        if (isNotEquals(updatePtyLocWrkTMsg.rgtnStsCd.getValue(), beforeUpdatePtyLocWrkTMsg.rgtnStsCd.getValue())
            || isNotEquals(updatePtyLocWrkTMsg.effThruDt.getValue(), beforeUpdatePtyLocWrkTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForProsPtyLocWrk(PROS_PTY_LOC_WRKTMsg updateProsPtyLocWrkTMsg, PROS_PTY_LOC_WRKTMsg beforeUpdateProsPtyLocWrkTMsg) {

        if (isNotEquals(updateProsPtyLocWrkTMsg.rgtnStsCd.getValue(), beforeUpdateProsPtyLocWrkTMsg.rgtnStsCd.getValue())
            || isNotEquals(updateProsPtyLocWrkTMsg.effThruDt.getValue(), beforeUpdateProsPtyLocWrkTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForCmpy(CMPYTMsg updateCmpyTMsg, CMPYTMsg beforeUpdateCmpyTMsg) {

        if (isNotEquals(updateCmpyTMsg.dbaNm.getValue(), beforeUpdateCmpyTMsg.dbaNm.getValue())
            || isNotEquals(updateCmpyTMsg.cmpyNm.getValue(), beforeUpdateCmpyTMsg.cmpyNm.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForBill(BILL_TO_CUSTTMsg updateBillToCustTMsg, BILL_TO_CUSTTMsg beforeUpdateBillToCustTMsg) {

        if (isNotEquals(updateBillToCustTMsg.bizRelnTpCd.getValue(), beforeUpdateBillToCustTMsg.bizRelnTpCd.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForPrinCustRgtnStsCd(PRIN_CUSTTMsg updatePrinCustForRgtnStsCdTMsg, PRIN_CUSTTMsg beforeUpdatePrinCustForRgtnStsCdTMsg) {

        if (isNotEquals(updatePrinCustForRgtnStsCdTMsg.rgtnStsCd.getValue(), beforeUpdatePrinCustForRgtnStsCdTMsg.rgtnStsCd.getValue())
            || isNotEquals(updatePrinCustForRgtnStsCdTMsg.effThruDt.getValue(), beforeUpdatePrinCustForRgtnStsCdTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForAcctLoc(ACCT_LOCTMsg updateAcctLocTMsg, ACCT_LOCTMsg beforeUpdateAcctLocTMsg) {

        if (isNotEquals(updateAcctLocTMsg.rgtnStsCd.getValue(), beforeUpdateAcctLocTMsg.rgtnStsCd.getValue())
            || isNotEquals(updateAcctLocTMsg.effThruDt.getValue(), beforeUpdateAcctLocTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForBillNotLookRgtnStsCd(BILL_TO_CUSTTMsg updateBillToCustTMsg, BILL_TO_CUSTTMsg beforeUpdateBillToCustTMsg) {

        if (isNotEquals(updateBillToCustTMsg.rgtnStsCd.getValue(), beforeUpdateBillToCustTMsg.rgtnStsCd.getValue())
            // 2023/11/06 QC#61924 Add Start
            || isNotEquals(updateBillToCustTMsg.deacNewTrxFlg.getValue(), beforeUpdateBillToCustTMsg.deacNewTrxFlg.getValue())
            // 2023/11/06 QC#61924 Add End
            || isNotEquals(updateBillToCustTMsg.effThruDt.getValue(), beforeUpdateBillToCustTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForShip(SHIP_TO_CUSTTMsg updateShipToCustTMsg, SHIP_TO_CUSTTMsg beforeUpdateShipToCustTMsg) {

        if (isNotEquals(updateShipToCustTMsg.rgtnStsCd.getValue(), beforeUpdateShipToCustTMsg.rgtnStsCd.getValue())
            // 2023/11/06 QC#61924 Add Start
            || isNotEquals(updateShipToCustTMsg.deacNewTrxFlg.getValue(), updateShipToCustTMsg.deacNewTrxFlg.getValue())
            // 2023/11/06 QC#61924 Add End
            || isNotEquals(updateShipToCustTMsg.effThruDt.getValue(), beforeUpdateShipToCustTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForPrinCust(PRIN_CUSTTMsg updatePrinCustTmsg, PRIN_CUSTTMsg beforeUpdatePrinCustTmsg) {

        if (isNotEquals(updatePrinCustTmsg.firstLineAddr.getValue(), beforeUpdatePrinCustTmsg.firstLineAddr.getValue())
            || isNotEquals(updatePrinCustTmsg.scdLineAddr.getValue(), beforeUpdatePrinCustTmsg.scdLineAddr.getValue())
            || isNotEquals(updatePrinCustTmsg.thirdLineAddr.getValue(), beforeUpdatePrinCustTmsg.thirdLineAddr.getValue())
            || isNotEquals(updatePrinCustTmsg.frthLineAddr.getValue(), beforeUpdatePrinCustTmsg.frthLineAddr.getValue())
            || isNotEquals(updatePrinCustTmsg.ctyAddr.getValue(), beforeUpdatePrinCustTmsg.ctyAddr.getValue())
            || isNotEquals(updatePrinCustTmsg.cntyPk.getValue(), beforeUpdatePrinCustTmsg.cntyPk.getValue())
            || isNotEquals(updatePrinCustTmsg.provNm.getValue(), beforeUpdatePrinCustTmsg.provNm.getValue())
            || isNotEquals(updatePrinCustTmsg.stCd.getValue(), beforeUpdatePrinCustTmsg.stCd.getValue())
            || isNotEquals(updatePrinCustTmsg.postCd.getValue(), beforeUpdatePrinCustTmsg.postCd.getValue())
            || isNotEquals(updatePrinCustTmsg.ctryCd.getValue(), beforeUpdatePrinCustTmsg.ctryCd.getValue())
            || isNotEquals(updatePrinCustTmsg.locNum.getValue(), beforeUpdatePrinCustTmsg.locNum.getValue())
            || isNotEquals(updatePrinCustTmsg.locNm.getValue(), beforeUpdatePrinCustTmsg.locNm.getValue())
            || isNotEquals(updatePrinCustTmsg.dunsNum.getValue(), beforeUpdatePrinCustTmsg.dunsNum.getValue())
            || isNotEquals(updatePrinCustTmsg.rgtnStsCd.getValue(), beforeUpdatePrinCustTmsg.rgtnStsCd.getValue())
            || isNotEquals(updatePrinCustTmsg.ptyLocPk.getValue(), beforeUpdatePrinCustTmsg.ptyLocPk.getValue())
            || isNotEquals(updatePrinCustTmsg.effFromDt.getValue(), beforeUpdatePrinCustTmsg.effFromDt.getValue())
            || isNotEquals(updatePrinCustTmsg.effThruDt.getValue(), beforeUpdatePrinCustTmsg.effThruDt.getValue())
            || isNotEquals(updatePrinCustTmsg.bizRelnTpCd.getValue(), beforeUpdatePrinCustTmsg.bizRelnTpCd.getValue())
            || isNotEquals(updatePrinCustTmsg.geoCd.getValue(), beforeUpdatePrinCustTmsg.geoCd.getValue())
            || isNotEquals(updatePrinCustTmsg.telNum.getValue(), beforeUpdatePrinCustTmsg.telNum.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForCustFromPrinCust(SELL_TO_CUSTTMsg updateDsAcctCustTMsg, SELL_TO_CUSTTMsg beforeUpdateDsAcctCustTMsg) {

        if (isNotEquals(updateDsAcctCustTMsg.firstLineAddr.getValue(), beforeUpdateDsAcctCustTMsg.firstLineAddr.getValue())
            || isNotEquals(updateDsAcctCustTMsg.scdLineAddr.getValue(), beforeUpdateDsAcctCustTMsg.scdLineAddr.getValue())
            || isNotEquals(updateDsAcctCustTMsg.thirdLineAddr.getValue(), beforeUpdateDsAcctCustTMsg.thirdLineAddr.getValue())
            || isNotEquals(updateDsAcctCustTMsg.frthLineAddr.getValue(), beforeUpdateDsAcctCustTMsg.frthLineAddr.getValue())
            || isNotEquals(updateDsAcctCustTMsg.ctyAddr.getValue(), beforeUpdateDsAcctCustTMsg.ctyAddr.getValue())
            || isNotEquals(updateDsAcctCustTMsg.cntyPk.getValue(), beforeUpdateDsAcctCustTMsg.cntyPk.getValue())
            || isNotEquals(updateDsAcctCustTMsg.provNm.getValue(), beforeUpdateDsAcctCustTMsg.provNm.getValue())
            || isNotEquals(updateDsAcctCustTMsg.stCd.getValue(), beforeUpdateDsAcctCustTMsg.stCd.getValue())
            || isNotEquals(updateDsAcctCustTMsg.postCd.getValue(), beforeUpdateDsAcctCustTMsg.postCd.getValue())
            || isNotEquals(updateDsAcctCustTMsg.ctryCd.getValue(), beforeUpdateDsAcctCustTMsg.ctryCd.getValue())
            || isNotEquals(updateDsAcctCustTMsg.locNum.getValue(), beforeUpdateDsAcctCustTMsg.locNum.getValue())
            || isNotEquals(updateDsAcctCustTMsg.locNm.getValue(), beforeUpdateDsAcctCustTMsg.locNm.getValue())
            || isNotEquals(updateDsAcctCustTMsg.ptyLocPk.getValue(), beforeUpdateDsAcctCustTMsg.ptyLocPk.getValue())
            || isNotEquals(updateDsAcctCustTMsg.geoCd.getValue(), beforeUpdateDsAcctCustTMsg.geoCd.getValue())
            || isNotEquals(updateDsAcctCustTMsg.telNum.getValue(), beforeUpdateDsAcctCustTMsg.telNum.getValue())) {
            return true;
        }
        return false;

    }

//    private boolean changeFieldsCheckForProsFromPrinCust(DS_ACCT_PROSTMsg updateDsAcctProsTMsg, DS_ACCT_PROSTMsg beforeUpdateDsAcctProsTMsg) {
//
//        if (isNotEquals(updateDsAcctProsTMsg.firstLineAddr.getValue(), beforeUpdateDsAcctProsTMsg.firstLineAddr.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.scdLineAddr.getValue(), beforeUpdateDsAcctProsTMsg.scdLineAddr.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.thirdLineAddr.getValue(), beforeUpdateDsAcctProsTMsg.thirdLineAddr.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.frthLineAddr.getValue(), beforeUpdateDsAcctProsTMsg.frthLineAddr.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.ctyAddr.getValue(), beforeUpdateDsAcctProsTMsg.ctyAddr.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.cntyPk.getValue(), beforeUpdateDsAcctProsTMsg.cntyPk.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.provNm.getValue(), beforeUpdateDsAcctProsTMsg.provNm.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.stCd.getValue(), beforeUpdateDsAcctProsTMsg.stCd.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.postCd.getValue(), beforeUpdateDsAcctProsTMsg.postCd.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.ctryCd.getValue(), beforeUpdateDsAcctProsTMsg.ctryCd.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.locNum.getValue(), beforeUpdateDsAcctProsTMsg.locNum.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.locNm.getValue(), beforeUpdateDsAcctProsTMsg.locNm.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.ptyLocPk.getValue(), beforeUpdateDsAcctProsTMsg.ptyLocPk.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.geoCd.getValue(), beforeUpdateDsAcctProsTMsg.geoCd.getValue())
//            || isNotEquals(updateDsAcctProsTMsg.telNum.getValue(), beforeUpdateDsAcctProsTMsg.telNum.getValue())) {
//            return true;
//        }
//        return false;
//
//    }

    private boolean changeFieldsCheckForAcctReln(DS_ACCT_RELNTMsg dsAcctRelnTMsg, DS_ACCT_RELNTMsg beforeDsAcctRelnTMsg) {

        if (isNotEquals(dsAcctRelnTMsg.dsAcctRelnTpCd.getValue(), beforeDsAcctRelnTMsg.dsAcctRelnTpCd.getValue())
            || isNotEquals(dsAcctRelnTMsg.effFromDt.getValue(), beforeDsAcctRelnTMsg.effFromDt.getValue())
            || isNotEquals(dsAcctRelnTMsg.effThruDt.getValue(), beforeDsAcctRelnTMsg.effThruDt.getValue())
            || isNotEquals(dsAcctRelnTMsg.dsAcctRelnBillToFlg.getValue(), beforeDsAcctRelnTMsg.dsAcctRelnBillToFlg.getValue())
            || isNotEquals(dsAcctRelnTMsg.dsAcctRelnShipToFlg.getValue(), beforeDsAcctRelnTMsg.dsAcctRelnShipToFlg.getValue())
            || isNotEquals(dsAcctRelnTMsg.dsAcctRelnRecipFlg.getValue(), beforeDsAcctRelnTMsg.dsAcctRelnRecipFlg.getValue())
            || isNotEquals(dsAcctRelnTMsg.rgtnStsCd.getValue(), beforeDsAcctRelnTMsg.rgtnStsCd.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForDsCtacPsnReln(DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTMsg, DS_CTAC_PSN_RELNTMsg beforeDsCtacPsnRelnTMsg) {

        if (isNotEquals(dsCtacPsnRelnTMsg.dsPrimLocFlg.getValue(), beforeDsCtacPsnRelnTMsg.dsPrimLocFlg.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForProsFromMarketing(DS_ACCT_PROSTMsg updateDsAcctProsTMsg, DS_ACCT_PROSTMsg beforeUpdateDsAcctProsTMsg) {

        if (isNotEquals(updateDsAcctProsTMsg.dsAcctDunsNum.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctDunsNum.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsUltDunsNum.getValue(), beforeUpdateDsAcctProsTMsg.dsUltDunsNum.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsLocEmpNum.getValue(), beforeUpdateDsAcctProsTMsg.dsLocEmpNum.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsLocRevAmt.getValue(), beforeUpdateDsAcctProsTMsg.dsLocRevAmt.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsCustSicCd.getValue(), beforeUpdateDsAcctProsTMsg.dsCustSicCd.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsLastUpdDunsDt.getValue(), beforeUpdateDsAcctProsTMsg.dsLastUpdDunsDt.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsCustSicDescTxt.getValue(), beforeUpdateDsAcctProsTMsg.dsCustSicDescTxt.getValue())
            || isNotEquals(updateDsAcctProsTMsg.dsAcctUrl.getValue(), beforeUpdateDsAcctProsTMsg.dsAcctUrl.getValue())
            || isNotEquals(updateDsAcctProsTMsg.bizHrsSunFromTm.getValue(), beforeUpdateDsAcctProsTMsg.bizHrsSunFromTm.getValue())
            || isNotEquals(updateDsAcctProsTMsg.bizHrsSunToTm.getValue(), beforeUpdateDsAcctProsTMsg.bizHrsSunToTm.getValue())
            || isNotEquals(updateDsAcctProsTMsg.bizHrsMonFriFromTm.getValue(), beforeUpdateDsAcctProsTMsg.bizHrsMonFriFromTm.getValue())
            || isNotEquals(updateDsAcctProsTMsg.bizHrsMonFriToTm.getValue(), beforeUpdateDsAcctProsTMsg.bizHrsMonFriToTm.getValue())
            || isNotEquals(updateDsAcctProsTMsg.bizHrsSatFromTm.getValue(), beforeUpdateDsAcctProsTMsg.bizHrsSatFromTm.getValue())
            || isNotEquals(updateDsAcctProsTMsg.bizHrsSatToTm.getValue(), beforeUpdateDsAcctProsTMsg.bizHrsSatToTm.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForSellFromMarketing(SELL_TO_CUSTTMsg updateSellToCustTMsg, SELL_TO_CUSTTMsg beforeUpdateSellToCustTMsg) {

        if (isNotEquals(updateSellToCustTMsg.dsAcctDunsNum.getValue(), beforeUpdateSellToCustTMsg.dsAcctDunsNum.getValue())
                || isNotEquals(updateSellToCustTMsg.dsUltDunsNum.getValue(), beforeUpdateSellToCustTMsg.dsUltDunsNum.getValue())
                || isNotEquals(updateSellToCustTMsg.dsLocEmpNum.getValue(), beforeUpdateSellToCustTMsg.dsLocEmpNum.getValue())
                || isNotEquals(updateSellToCustTMsg.dsLocRevAmt.getValue(), beforeUpdateSellToCustTMsg.dsLocRevAmt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsCustSicCd.getValue(), beforeUpdateSellToCustTMsg.dsCustSicCd.getValue())
                || isNotEquals(updateSellToCustTMsg.dsLastUpdDunsDt.getValue(), beforeUpdateSellToCustTMsg.dsLastUpdDunsDt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsCustSicDescTxt.getValue(), beforeUpdateSellToCustTMsg.dsCustSicDescTxt.getValue())
                || isNotEquals(updateSellToCustTMsg.dsAcctUrl.getValue(), beforeUpdateSellToCustTMsg.dsAcctUrl.getValue())
                || isNotEquals(updateSellToCustTMsg.bizHrsSunFromTm.getValue(), beforeUpdateSellToCustTMsg.bizHrsSunFromTm.getValue())
                || isNotEquals(updateSellToCustTMsg.bizHrsSunToTm.getValue(), beforeUpdateSellToCustTMsg.bizHrsSunToTm.getValue())
                || isNotEquals(updateSellToCustTMsg.bizHrsMonFriFromTm.getValue(), beforeUpdateSellToCustTMsg.bizHrsMonFriFromTm.getValue())
                || isNotEquals(updateSellToCustTMsg.bizHrsMonFriToTm.getValue(), beforeUpdateSellToCustTMsg.bizHrsMonFriToTm.getValue())
                || isNotEquals(updateSellToCustTMsg.bizHrsSatFromTm.getValue(), beforeUpdateSellToCustTMsg.bizHrsSatFromTm.getValue())
                || isNotEquals(updateSellToCustTMsg.bizHrsSatToTm.getValue(), beforeUpdateSellToCustTMsg.bizHrsSatToTm.getValue())
                || isNotEquals(updateSellToCustTMsg.dunsNum.getValue(), beforeUpdateSellToCustTMsg.dunsNum.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForCmpyFromMarketing(CMPYTMsg updateCmpyTMsg, CMPYTMsg beforeUpdateCmpyTMsg) {

        if (isNotEquals(updateCmpyTMsg.dunsNum.getValue(), beforeUpdateCmpyTMsg.dunsNum.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForDsAcctGrpAsg(DS_ACCT_GRP_ASGTMsg dsAcctGrpAsgTMsg, DS_ACCT_GRP_ASGTMsg beforeDsAcctGrpAsgTMsg) {

        if (isNotEquals(dsAcctGrpAsgTMsg.dsAcctGrpCd.getValue(), beforeDsAcctGrpAsgTMsg.dsAcctGrpCd.getValue())
            || isNotEquals(dsAcctGrpAsgTMsg.effFromDt.getValue(), beforeDsAcctGrpAsgTMsg.effFromDt.getValue())
            || isNotEquals(dsAcctGrpAsgTMsg.effThruDt.getValue(), beforeDsAcctGrpAsgTMsg.effThruDt.getValue())
            || isNotEquals(dsAcctGrpAsgTMsg.dsBizAreaCd.getValue(), beforeDsAcctGrpAsgTMsg.dsBizAreaCd.getValue())
            ) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForSvcAccsPmit(SVC_ACCS_PMITTMsg svcAccsPmitTMsg, SVC_ACCS_PMITTMsg beforeSvcAccsPmitTMsg) {

        if (isNotEquals(svcAccsPmitTMsg.svcAccsPmitNum.getValue(), beforeSvcAccsPmitTMsg.svcAccsPmitNum.getValue())
            || isNotEquals(svcAccsPmitTMsg.effFromDt.getValue(), beforeSvcAccsPmitTMsg.effFromDt.getValue())
            || isNotEquals(svcAccsPmitTMsg.effToDt.getValue(), beforeSvcAccsPmitTMsg.effToDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForDsCustTrxRule(DS_CUST_TRX_RULETMsg dsCustTrxRuleTMsg, DS_CUST_TRX_RULETMsg beforeDsCustTrxRuleTMsg) {

        if (isNotEquals(dsCustTrxRuleTMsg.dsTrxRuleTpCd.getValue(), beforeDsCustTrxRuleTMsg.dsTrxRuleTpCd.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.dsPoReqFlg.getValue(), beforeDsCustTrxRuleTMsg.dsPoReqFlg.getValue())
            // 2022/11/25 QC#60398 Add Start
            || isNotEquals(dsCustTrxRuleTMsg.hardCopyReqFlg.getValue(), beforeDsCustTrxRuleTMsg.hardCopyReqFlg.getValue())
            // 2022/11/25 QC#60398 Add End
            || isNotEquals(dsCustTrxRuleTMsg.dsBlktPoNum.getValue(), beforeDsCustTrxRuleTMsg.dsBlktPoNum.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.dsPoExprDt.getValue(), beforeDsCustTrxRuleTMsg.dsPoExprDt.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.dsDefBillToCd.getValue(), beforeDsCustTrxRuleTMsg.dsDefBillToCd.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.dsDefShipToCd.getValue(), beforeDsCustTrxRuleTMsg.dsDefShipToCd.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.custEffLvlCd.getValue(), beforeDsCustTrxRuleTMsg.custEffLvlCd.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.dsCrCardReqFlg.getValue(), beforeDsCustTrxRuleTMsg.dsCrCardReqFlg.getValue())
            || isNotEquals(dsCustTrxRuleTMsg.dsOvngtAllwFlg.getValue(), beforeDsCustTrxRuleTMsg.dsOvngtAllwFlg.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForDsCustSpclHdlg(DS_CUST_SPCL_HDLGTMsg dsCustSpclHdlgTMsg, DS_CUST_SPCL_HDLGTMsg beforeDsCustSpclHdlgTMsg) {

        if (isNotEquals(dsCustSpclHdlgTMsg.dsSpclHdlgTpCd.getValue(), beforeDsCustSpclHdlgTMsg.dsSpclHdlgTpCd.getValue())
            || isNotEquals(dsCustSpclHdlgTMsg.dsSpclHdlgTpValCd.getValue(), beforeDsCustSpclHdlgTMsg.dsSpclHdlgTpValCd.getValue())
            || isNotEquals(dsCustSpclHdlgTMsg.custEffLvlCd.getValue(), beforeDsCustSpclHdlgTMsg.custEffLvlCd.getValue())
            || isNotEquals(dsCustSpclHdlgTMsg.effFromDt.getValue(), beforeDsCustSpclHdlgTMsg.effFromDt.getValue())
            || isNotEquals(dsCustSpclHdlgTMsg.effThruDt.getValue(), beforeDsCustSpclHdlgTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForDsAcctCrPrfl(DS_ACCT_CR_PRFLTMsg updateDsAcctCrPrflTMsg, DS_ACCT_CR_PRFLTMsg beforeUpdateDsAcctCrPrflTMsg) {

        if (isNotEquals(updateDsAcctCrPrflTMsg.ccyCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.ccyCd.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.custCrRtgCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.custCrRtgCd.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.crLimitAmt.getValue(), beforeUpdateDsAcctCrPrflTMsg.crLimitAmt.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.crChkReqTpCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.crChkReqTpCd.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.crRiskClsCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.crRiskClsCd.getValue())
            // START 2018/01/25 [QC#22095,ADD]
            || isNotEquals(updateDsAcctCrPrflTMsg.contrCrRiskClsCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.contrCrRiskClsCd.getValue())
            // END   2018/01/25 [QC#22095,ADD]
            || isNotEquals(updateDsAcctCrPrflTMsg.pmtTermCashDiscCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.pmtTermCashDiscCd.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.ovrdPmtTermFlg.getValue(), beforeUpdateDsAcctCrPrflTMsg.ovrdPmtTermFlg.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.cashOrCcReqFlg.getValue(), beforeUpdateDsAcctCrPrflTMsg.cashOrCcReqFlg.getValue())
            || isNotEquals(updateDsAcctCrPrflTMsg.custHardHldFlg.getValue(), beforeUpdateDsAcctCrPrflTMsg.custHardHldFlg.getValue())
            // START 2018/04/16 E.Kameishi [QC#21037, ADD]
            || isNotEquals(updateDsAcctCrPrflTMsg.autoCashHrchCd.getValue(), beforeUpdateDsAcctCrPrflTMsg.autoCashHrchCd.getValue())
            // END 2018/04/16 E.Kameishi [QC#21037, ADD]
            ) {
            return true;
        }
        return false;

    }

    private boolean changeFieldsCheckForDsCustInvRule(DS_CUST_INV_RULETMsg dsCustInvRuleTMsg, DS_CUST_INV_RULETMsg beforeDsCustInvRuleTMsg) {

        if (isNotEquals(dsCustInvRuleTMsg.custEffLvlCd.getValue(), beforeDsCustInvRuleTMsg.custEffLvlCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.custInvSrcCd.getValue(), beforeDsCustInvRuleTMsg.custInvSrcCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.custBllgTpCd.getValue(), beforeDsCustInvRuleTMsg.custBllgTpCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.custConslTermCd.getValue(), beforeDsCustInvRuleTMsg.custConslTermCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.custBllgVcleCd.getValue(), beforeDsCustInvRuleTMsg.custBllgVcleCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.itrlRvwPsnCd.getValue(), beforeDsCustInvRuleTMsg.itrlRvwPsnCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.ctacPsnPk.getValue(), beforeDsCustInvRuleTMsg.ctacPsnPk.getValue())
            || isNotEquals(dsCustInvRuleTMsg.custEmlMsgTxt.getValue(), beforeDsCustInvRuleTMsg.custEmlMsgTxt.getValue())
            || isNotEquals(dsCustInvRuleTMsg.defInvGrpCd.getValue(), beforeDsCustInvRuleTMsg.defInvGrpCd.getValue())
            || isNotEquals(dsCustInvRuleTMsg.invGrpNum.getValue(), beforeDsCustInvRuleTMsg.invGrpNum.getValue())) {
            return true;
        }
        return false;
    }

    private boolean changeFieldsCheckForDsAcctRefAttrb(DS_ACCT_REF_ATTRBTMsg dsAcctRefAttrbTMsg, DS_ACCT_REF_ATTRBTMsg beforeDsAcctRefAttrbTMsg) {

        if (isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbNm.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbNm.getValue())
            || isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbValTxt.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbValTxt.getValue())
            || isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbEnblFlg.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbEnblFlg.getValue())
            || isNotEquals(dsAcctRefAttrbTMsg.bllgAttrbReqFlg.getValue(), beforeDsAcctRefAttrbTMsg.bllgAttrbReqFlg.getValue())
            || isNotEquals(dsAcctRefAttrbTMsg.custEffLvlCd.getValue(), beforeDsAcctRefAttrbTMsg.custEffLvlCd.getValue())
            || isNotEquals(dsAcctRefAttrbTMsg.dsAcctRefAttrbNum.getValue(), beforeDsAcctRefAttrbTMsg.dsAcctRefAttrbNum.getValue())) {
            return true;
        }
        return false;
    }

    private boolean changeFieldsCheckForDsAcctCarr(DS_ACCT_CARRTMsg dsAcctCarrTMsg, DS_ACCT_CARRTMsg beforeDsAcctCarrTMsg) {

        if (isNotEquals(dsAcctCarrTMsg.vndCd.getValue(), beforeDsAcctCarrTMsg.vndCd.getValue())
            // 2018/12/13 QC#29315 Add Start
            || isNotEquals(dsAcctCarrTMsg.lineBizTpCd.getValue(), beforeDsAcctCarrTMsg.lineBizTpCd.getValue())
            || isNotEquals(dsAcctCarrTMsg.dsBizAreaCd.getValue(), beforeDsAcctCarrTMsg.dsBizAreaCd.getValue())
            || isNotEquals(dsAcctCarrTMsg.custEffLvlCd.getValue(), beforeDsAcctCarrTMsg.custEffLvlCd.getValue())
            || isNotEquals(dsAcctCarrTMsg.dsAcctNum.getValue(), beforeDsAcctCarrTMsg.dsAcctNum.getValue())
            || isNotEquals(dsAcctCarrTMsg.vndCd.getValue(), beforeDsAcctCarrTMsg.vndCd.getValue())
            || isNotEquals(dsAcctCarrTMsg.locNum.getValue(), beforeDsAcctCarrTMsg.locNum.getValue())
            // 2018/12/13 QC#29315 Add End
            || isNotEquals(dsAcctCarrTMsg.dsCarrAcctNum.getValue(), beforeDsAcctCarrTMsg.dsCarrAcctNum.getValue())
            || isNotEquals(dsAcctCarrTMsg.effFromDt.getValue(), beforeDsAcctCarrTMsg.effFromDt.getValue())
            || isNotEquals(dsAcctCarrTMsg.effThruDt.getValue(), beforeDsAcctCarrTMsg.effThruDt.getValue())
            || isNotEquals(dsAcctCarrTMsg.defAcctCarrFlg.getValue(), beforeDsAcctCarrTMsg.defAcctCarrFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean changeFieldsCheckForDsBankAcct(DS_BANK_ACCTTMsg dsBankAcctTMsg, DS_BANK_ACCTTMsg beforeDsBankAcctTMsg) {

        if (isNotEquals(dsBankAcctTMsg.effThruDt.getValue(), beforeDsBankAcctTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;
    }

    private boolean changeFieldsCheckForDsCustSpclMsg(DS_CUST_SPCL_MSGTMsg dsCustSpclMsgTMsg, DS_CUST_SPCL_MSGTMsg beforeDsCustSpclMsgTMsg) {

        if (isNotEquals(dsCustSpclMsgTMsg.lineBizTpCd.getValue(), beforeDsCustSpclMsgTMsg.lineBizTpCd.getValue())
            || isNotEquals(dsCustSpclMsgTMsg.dsBizAreaCd.getValue(), beforeDsCustSpclMsgTMsg.dsBizAreaCd.getValue())
            || isNotEquals(dsCustSpclMsgTMsg.dsCustMsgTpCd.getValue(), beforeDsCustSpclMsgTMsg.dsCustMsgTpCd.getValue())
            || isNotEquals(dsCustSpclMsgTMsg.dsCustMsgTxt.getValue(), beforeDsCustSpclMsgTMsg.dsCustMsgTxt.getValue())
            || isNotEquals(dsCustSpclMsgTMsg.dsPrintOnInvFlg.getValue(), beforeDsCustSpclMsgTMsg.dsPrintOnInvFlg.getValue())
            || isNotEquals(dsCustSpclMsgTMsg.custEffLvlCd.getValue(), beforeDsCustSpclMsgTMsg.custEffLvlCd.getValue())
            || isNotEquals(dsCustSpclMsgTMsg.effThruDt.getValue(), beforeDsCustSpclMsgTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isNotEquals(String orig, String backUp) {
        // QC#18961 for difference of line separator.
        if (!nvl(orig.replaceAll("\r", "")).equals(nvl(backUp.replaceAll("\r", "")))) {
            return true;
        }
        return false;
    }

    private boolean isNotEquals(BigDecimal orig, BigDecimal backUp) {
        if (orig == null) {
            if (backUp == null) {
                return false;
            } else {
                return true;
            }
        } else {
            if (backUp == null) {
                return true;
            }
        }
        if (orig.compareTo(backUp) != 0) {
            return true;
        }
        return false;
    }

    private String nvl(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }

    private BigDecimal getPrinCustPkAftDelPrimLocFlg(NMAL6700CMsg bizMsg) {
        S21SsmEZDResult result = NMAL6700Query.getInstance().getPrinCustAftDelPrimLocFlg(this.getGlobalCompanyCode(), bizMsg.dsAcctNum_H1.getValue());
        if (result.isCodeNormal()) {
        List<Map<String, Object>> resultPrinCustList = (List<Map<String, Object>>) result.getResultObject();
            if (resultPrinCustList.size() > 0) {
                Map<String, Object> map = resultPrinCustList.get(0);
                BigDecimal prinCustPk = (BigDecimal) map.get("PRIN_CUST_PK");
                return prinCustPk;
            }
        }
        return null;
    }

    private boolean clearPrimLocPrinCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        PRIN_CUSTTMsg updatePrinCustTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, bizMsg.prinCustPk_H2.getValue());
        if (updatePrinCustTMsg == null) {
            return true;
        }
        updatePrinCustTMsg.firstLineAddr.clear();
        updatePrinCustTMsg.scdLineAddr.clear();
        updatePrinCustTMsg.thirdLineAddr.clear();
        updatePrinCustTMsg.frthLineAddr.clear();
        updatePrinCustTMsg.ctyAddr.clear();
        updatePrinCustTMsg.cntyPk.clear();
        updatePrinCustTMsg.provNm.clear();
        updatePrinCustTMsg.stCd.clear();
        updatePrinCustTMsg.postCd.clear();
        updatePrinCustTMsg.ctryCd.clear();
        updatePrinCustTMsg.locNum.clear();
        updatePrinCustTMsg.ptyLocPk.clear();
        S21FastTBLAccessor.update(updatePrinCustTMsg);
        if (!RTNCD_NORMAL.equals(updatePrinCustTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {"PRIN_CUST" });
            return false;
        }
        bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        return true;
    }

//    private boolean clearPrimLocDsAdcctPros(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
//        DS_ACCT_PROSTMsg updateDsAcctProsTMsg = NMAL6700CommonLogic.findDsAcctProsForUpdate(glblCmpyCd, bizMsg.sellToCustPk_H1.getValue());
//        if (updateDsAcctProsTMsg == null) {
//            String[] args = {"DS_ACCT_PROS" };
//            bizMsg.setMessageInfo(NMAM8111E, args);
//            return false;
//        }
//        updateDsAcctProsTMsg.firstLineAddr.clear();
//        updateDsAcctProsTMsg.scdLineAddr.clear();
//        updateDsAcctProsTMsg.thirdLineAddr.clear();
//        updateDsAcctProsTMsg.frthLineAddr.clear();
//        updateDsAcctProsTMsg.ctyAddr.clear();
//        updateDsAcctProsTMsg.cntyPk.clear();
//        updateDsAcctProsTMsg.provNm.clear();
//        updateDsAcctProsTMsg.stCd.clear();
//        updateDsAcctProsTMsg.postCd.clear();
//        updateDsAcctProsTMsg.ctryCd.clear();
//        updateDsAcctProsTMsg.locNum.clear();
//        updateDsAcctProsTMsg.ptyLocPk.clear();
//        S21FastTBLAccessor.update(updateDsAcctProsTMsg);
//        if (!RTNCD_NORMAL.equals(updateDsAcctProsTMsg.getReturnCode())) {
//            bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_PROS" });
//            return false;
//        }
//        bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//        return true;
//    }

    private boolean clearPrimLocSellToCust(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        SELL_TO_CUSTTMsg updateSellToCustTMsg = NMAL6700CommonLogic.findSellToCustForUpdate(glblCmpyCd, bizMsg.dsAcctNum_H1.getValue());
        if (updateSellToCustTMsg == null) {
            String[] args = {"SELL_TO_CUST" };
            bizMsg.setMessageInfo(NMAM8111E, args);
            return false;
        }
        updateSellToCustTMsg.firstLineAddr.clear();
        updateSellToCustTMsg.scdLineAddr.clear();
        updateSellToCustTMsg.thirdLineAddr.clear();
        updateSellToCustTMsg.frthLineAddr.clear();
        updateSellToCustTMsg.ctyAddr.clear();
        updateSellToCustTMsg.cntyPk.clear();
        updateSellToCustTMsg.provNm.clear();
        updateSellToCustTMsg.stCd.clear();
        updateSellToCustTMsg.postCd.clear();
        updateSellToCustTMsg.ctryCd.clear();
        updateSellToCustTMsg.locNum.clear();
        updateSellToCustTMsg.ptyLocPk.clear();
        updateSellToCustTMsg.addlLocNm.clear();
        updateSellToCustTMsg.prinCustPk.clear();
        S21FastTBLAccessor.update(updateSellToCustTMsg);
        if (!RTNCD_NORMAL.equals(updateSellToCustTMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {"SELL_TO_CUST" });
            return false;
        }
        bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
        return true;
    }

    private String cutToMaxByte(String value, int maxByteLen) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return value;
        }
        if (EZDStringUtil.getByteLength(value) <= maxByteLen) {
            return value;
        }
        for (int i = 0; i < value.length(); i++) {
            String substr = value.substring(0, value.length() - i - 1);
            if (EZDStringUtil.getByteLength(substr) <= maxByteLen) {
                return substr;
            }
        }
        return value;
    }

    private void setEffectiveDateForTermination(EZDTDateItem itemEffFromDt, EZDTDateItem itemEffThruDt) {
        String slsDt = ZYPDateUtil.getSalesDate();
        if (itemEffFromDt.getValue().compareTo(slsDt) < 0) {
            ZYPEZDItemValueSetter.setValue(itemEffThruDt, ZYPDateUtil.addDays(slsDt, -1));
        } else {
            ZYPEZDItemValueSetter.setValue(itemEffFromDt, ZYPDateUtil.addDays(slsDt, -1));
            ZYPEZDItemValueSetter.setValue(itemEffThruDt, ZYPDateUtil.addDays(slsDt, -1));
        }
    }

    private void updateCoAForLoc(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg, String glblCmpyCd) {
        if (DS_ACCT_TP.PROSPECT.equals(bizMsg.dsAcctTpCd_H3.getValue())) {
            return;
        }

        if (!bizMsg.coaAfflCd_H1.getValue().equals(sharedMsg.coaAfflCd_H1.getValue()) || !bizMsg.coaChCd_H1.getValue().equals(sharedMsg.coaChCd_H1.getValue())) {
            if (!updateCoAForBillTo(bizMsg, sharedMsg)) {
                return;
            }
            // 2019/04/05 QC#31030 Del Start
            //if (!updateCoAForShipTo(bizMsg, sharedMsg)) {
            //    return;
            //}
            // 2019/04/05 QC#31030 Del End
        }
    }

    private boolean updateCoAForBillTo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        S21SsmEZDResult resBillToCust = NMAL6700Query.getInstance().getBillToCustNotLookRgtnStsCd(bizMsg.dsAcctNum_H1.getValue());
        if (!resBillToCust.isCodeNormal()) {
            return true;
        }
        List<Map<String, Object>> resultBillToCustList = (List<Map<String, Object>>) resBillToCust.getResultObject();
        for (int i = 0; i < resultBillToCustList.size(); i++) {
            Map<String, Object> resultBillToCust = resultBillToCustList.get(i);
            BigDecimal billToCustPk = (BigDecimal) resultBillToCust.get("BILL_TO_CUST_PK");

            BILL_TO_CUSTTMsg updateBillToCustTMsg = NMAL6700CommonLogic.findBillToCustForUpdate(this.getGlobalCompanyCode(), billToCustPk);
            if (updateBillToCustTMsg != null) {
                boolean isNeedUpd = false;
                if (!bizMsg.coaAfflCd_H1.getValue().equals(sharedMsg.coaAfflCd_H1.getValue())) {
                    updateBillToCustTMsg.coaAfflCd.setValue(bizMsg.coaAfflCd_H1.getValue());
                    isNeedUpd = true;
                }
                if (!bizMsg.coaChCd_H1.getValue().equals(sharedMsg.coaChCd_H1.getValue())) {
                    // START 2024/03/11 S.Ikariya [QC#63499, DEL]
                    // if(sharedMsg.coaChCd_H1.getValue().equals(updateBillToCustTMsg.coaChCd.getValue())) {
                    // END 2024/03/11 S.Ikariya [QC#63499, DEL]
                        updateBillToCustTMsg.coaChCd.setValue(bizMsg.coaChCd_H1.getValue());
                        isNeedUpd = true;
                    // START 2024/03/11 S.Ikariya [QC#63499, DEL]
                    // }
                    // END 2024/03/11 S.Ikariya [QC#63499, DEL]
                }
                if (isNeedUpd) {
                    // update BILL_TO_CUST
                    S21FastTBLAccessor.update(updateBillToCustTMsg);
                    if (!RTNCD_NORMAL.equals(updateBillToCustTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean updateCoAForShipTo(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        S21SsmEZDResult resShipToCust = NMAL6700Query.getInstance().getShipToCust(bizMsg.dsAcctNum_H1.getValue());
        if (!resShipToCust.isCodeNormal()) {
            return true;
        }
        List<Map<String, Object>> resultShipToCustList = (List<Map<String, Object>>) resShipToCust.getResultObject();
        for (int i = 0; i < resultShipToCustList.size(); i++) {
            Map<String, Object> resultShipToCust = resultShipToCustList.get(i);
            BigDecimal shipToCustPk = (BigDecimal) resultShipToCust.get("SHIP_TO_CUST_PK");

            SHIP_TO_CUSTTMsg updateShipToCustTMsg = NMAL6700CommonLogic.findShipToCustForUpdate(this.getGlobalCompanyCode(), shipToCustPk);
            if (updateShipToCustTMsg != null) {
                boolean isNeedUpd = false;
                if (!bizMsg.coaAfflCd_H1.getValue().equals(sharedMsg.coaAfflCd_H1.getValue())) {
                    updateShipToCustTMsg.coaAfflCd.setValue(bizMsg.coaAfflCd_H1.getValue());
                    isNeedUpd = true;
                }
                if (!bizMsg.coaChCd_H1.getValue().equals(sharedMsg.coaChCd_H1.getValue())) {
                    if (sharedMsg.coaChCd_H1.getValue().equals(updateShipToCustTMsg.coaChCd.getValue())) {
                        updateShipToCustTMsg.coaChCd.setValue(bizMsg.coaChCd_H1.getValue());
                        isNeedUpd = true;
                    }
                }
                if (isNeedUpd) {
                    S21FastTBLAccessor.update(updateShipToCustTMsg);
                    if (!RTNCD_NORMAL.equals(updateShipToCustTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"SHIP_TO_CUST" });
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // START 2017/08/09 J.Kim [QC#20184,ADD]
    private String setDsAcctFlg(EZDSStringItem flg) {

        if (!ZYPCommonFunc.hasValue(flg)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return flg.getValue();
    }
    // END 2017/08/09 J.Kim [QC#20184,ADD]

    // 2018/02/14 QC#20297(Sol#379) add start
    private boolean inputCheckForShipping(NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {
        boolean rtnFlg = true;
        int cnt = sharedMsg.M.getValidCount();
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        // 2018/12/10 QC#29315 Add Start
        List<ArrayList> carrCombList = new ArrayList<ArrayList>();
        // 2018/12/10 QC#29315 Add End
        for (int i = 0; i < cnt; i++) {
            NMAL6700_MSMsg msMsg = sharedMsg.M.no(i);
            // 2018/12/10 QC#29315 Mod Start
//            // QC#18961
//            if (ZYPCommonFunc.hasValue(msMsg.dsCustShpgDefPk_M1)) {
//                DS_CUST_SHPG_DEFTMsg beforedsCustShpgDefTMsg // no lock
//                = NMAL6700CommonLogic.findDsCustShpgDefForUpdate(getGlobalCompanyCode(), msMsg.dsCustShpgDefPk_M1.getValue(), false);
//                if (beforedsCustShpgDefTMsg != null) {
//                    DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
//                    setDsCustShpgDefData(dsCustShpgDefTMsg, bizMsg, msMsg, false);
//                    //Change check
//                    if (!changeFieldsCheckForDsCustShpgDef(dsCustShpgDefTMsg, beforedsCustShpgDefTMsg)) {
//                        continue; // no change.
//                    }
//                }
//            }
            if (!ZYPCommonFunc.hasValue(msMsg.xxChkBox_MD)) {
                ZYPEZDItemValueSetter.setValue(msMsg.xxChkBox_MD, ZYPConstant.FLG_OFF_N);
            }
            // 2023/01/13 QC#60860 Add Start
            sharedMsg = NMAL6700CommonLogic.vndCdHasValue(getGlobalCompanyCode(), sharedMsg);
            // 2023/01/13 QC#60860 Add End
            // 2019/01/19 QC#29940 Add Start
            boolean effThruDtChkFlg = true;
            // 2019/01/19 QC#29940 Add End
            if (ZYPCommonFunc.hasValue(msMsg.dsCustShpgDefPk_M1) && ZYPCommonFunc.hasValue(msMsg.dsAcctCarrPk_M1)) {
                DS_CUST_SHPG_DEFTMsg beforedsCustShpgDefTMsg // no lock
                = NMAL6700CommonLogic.findDsCustShpgDefForUpdate(getGlobalCompanyCode(), msMsg.dsCustShpgDefPk_M1.getValue(), false);
                DS_ACCT_CARRTMsg beforeDsAcctCarrTMsg // no lock
                = NMAL6700CommonLogic.findDsAcctCarrForUpdate(getGlobalCompanyCode(), msMsg.dsAcctCarrPk_M1.getValue(), false);

                if (beforeDsAcctCarrTMsg != null) {
                    DS_ACCT_CARRTMsg dsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
                    setDsAcctCarrData(dsAcctCarrTMsg, bizMsg, msMsg, false);
                    // Change check
                    if (!changeFieldsCheckForDsAcctCarr(dsAcctCarrTMsg, beforeDsAcctCarrTMsg)) {
                        continue; // no change.
                    }
                }
                if (beforedsCustShpgDefTMsg != null) {
                    DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
                    setDsCustShpgDefData(dsCustShpgDefTMsg, bizMsg, msMsg, msMsg.dsAcctCarrPk_M1.getValue(), false);
                    // Change check
                    if (!changeFieldsCheckForDsCustShpgDef(dsCustShpgDefTMsg, beforedsCustShpgDefTMsg)) {
                        continue; // no change.
                    }
                }
                // 2019/01/19 QC#29940 Add Start
                // If don't change effThruDt, pass NMAM8200E check.
                String effThruDt = msMsg.effThruDt_M1.getValue();
                if (!ZYPCommonFunc.hasValue(effThruDt) && !ZYPCommonFunc.hasValue(beforedsCustShpgDefTMsg.effThruDt.getValue())) {
                    effThruDtChkFlg = false; // no change effThruDt(Null pattern).
                } else if (ZYPCommonFunc.hasValue(effThruDt) && effThruDt.equals(beforedsCustShpgDefTMsg.effThruDt.getValue())) {
                    effThruDtChkFlg = false; // no change effThruDt(Not null pattern).
                }
                // 2019/01/19 QC#29940 Add End
            }
            // 2018/12/10 QC#29315 Mod End

            String effThruDt = msMsg.effThruDt_M1.getValue();
            // 2019/01/19 QC#29940 Mod Start
            //if (ZYPCommonFunc.hasValue(salesDate) && ZYPCommonFunc.hasValue(effThruDt) && salesDate.compareTo(effThruDt) > 0) {
            if (effThruDtChkFlg && ZYPCommonFunc.hasValue(salesDate) && ZYPCommonFunc.hasValue(effThruDt) && salesDate.compareTo(effThruDt) > 0) {
                // 2019/01/19 QC#29940 Mod End
                String[] args = {"End Date" };
                msMsg.effThruDt_M1.setErrorInfo(1, NMAM8200E, args);
                rtnFlg = false;
            }

            // Duplicate
            if (ZYPCommonFunc.hasValue(msMsg.effThruDt_M1) && ZYPDateUtil.compare(salesDate, msMsg.effThruDt_M1.getValue()) >= 0) {
                continue;
            }

            for (int j = 0; j < cnt; j ++) {
                if (i == j) {
                    continue;
                }
                NMAL6700_MSMsg msMsg2 = sharedMsg.M.no(j);
                // 2018/12/13 QC#29315 Del Start
//                if (ZYPCommonFunc.hasValue(msMsg2.effThruDt_M1) && ZYPDateUtil.compare(salesDate, msMsg2.effThruDt_M1.getValue()) >= 0) {
//                    continue;
//                }
                // 2018/12/13 QC#29315 Del End
                // 2018/12/10 QC#29315 Mod Start
//                if (!isNotEquals(msMsg.lineBizTpCd_M3.getValue(), msMsg2.lineBizTpCd_M3.getValue()) //
//                        && !isNotEquals(msMsg.dsBizAreaCd_M3.getValue(), msMsg2.dsBizAreaCd_M3.getValue())) {
//                    String[] args = {"Line Of Business and Business Area" };
//                    msMsg.effThruDt_M1.setErrorInfo(1, NMAM0072E, args);
//                    rtnFlg = false;
//                }
                // check effective date duplication
                if (!isNotEquals(msMsg.lineBizTpCd_M3.getValue(), msMsg2.lineBizTpCd_M3.getValue()) //
                        && !isNotEquals(msMsg.dsBizAreaCd_M3.getValue(), msMsg2.dsBizAreaCd_M3.getValue()) //
                        && !isNotEquals(msMsg.vndCd_M3.getValue(), msMsg2.vndCd_M3.getValue())) {

                    if (ZYPDateUtil.compare(msMsg.effFromDt_M1.getValue(), msMsg2.effFromDt_M1.getValue()) >= 0 && ZYPDateUtil.compare(msMsg.effFromDt_M1.getValue(), msMsg2.effThruDt_M1.getValue()) <= 0) {
                        String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                        msMsg.effFromDt_M1.setErrorInfo(1, NMAM8178E, args);
                        msMsg.effThruDt_M1.setErrorInfo(1, NMAM8178E, args);
                        rtnFlg = false;
                    } else if (!(ZYPCommonFunc.hasValue(msMsg2.effThruDt_M1))) {
                        if (ZYPDateUtil.compare(msMsg.effFromDt_M1.getValue(), msMsg2.effFromDt_M1.getValue()) >= 0) {
                            String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                            msMsg.effFromDt_M1.setErrorInfo(1, NMAM8178E, args);
                            msMsg.effThruDt_M1.setErrorInfo(1, NMAM8178E, args);
                            rtnFlg = false;
                        }
                    }
                    if (ZYPDateUtil.compare(msMsg.effThruDt_M1.getValue(), msMsg2.effFromDt_M1.getValue()) >= 0 && ZYPDateUtil.compare(msMsg.effThruDt_M1.getValue(), msMsg2.effThruDt_M1.getValue()) <= 0) {
                        String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                        msMsg.effFromDt_M1.setErrorInfo(1, NMAM8178E, args);
                        msMsg.effThruDt_M1.setErrorInfo(1, NMAM8178E, args);
                        rtnFlg = false;
                    } else if (!(ZYPCommonFunc.hasValue(msMsg.effThruDt_M1))) {
                        if (ZYPDateUtil.compare(msMsg.effFromDt_M1.getValue(), msMsg2.effFromDt_M1.getValue()) <= 0) {
                            String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                            msMsg.effFromDt_M1.setErrorInfo(1, NMAM8178E, args);
                            msMsg.effThruDt_M1.setErrorInfo(1, NMAM8178E, args);
                            rtnFlg = false;
                        }
                    }
                }
                // 2018/12/10 QC#29315 Mod End
            }
            // 2018/12/10 QC#29315 Add Start
            // update carrCombList for check
            setCarrCombList(msMsg, carrCombList, i);
            // 2018/12/10 QC#29315 Add End
        }
        // 2018/12/10 QC#29315 Add Start
        // errcheck for defflg
        int errRowNum = 0;
        for (List<String> defFlgList : carrCombList) {
            if ("0".equals(defFlgList.get(2))) {
                for (int k = 3; k < defFlgList.size(); k++) {
                    errRowNum = Integer.parseInt(defFlgList.get(k));
                    sharedMsg.M.no(errRowNum).xxChkBox_MD.setErrorInfo(1, NMAM8372E);
                }
                rtnFlg = false;
                break;
            }
            if (Integer.parseInt(defFlgList.get(2)) > 1) {
                for (int k = 3; k < defFlgList.size(); k++) {
                    errRowNum = Integer.parseInt(defFlgList.get(k));
                    String[] args = {"Only one defalut carrier can be selected at the same combination of LOB and Business Area" };
                    sharedMsg.M.no(errRowNum).xxChkBox_MD.setErrorInfo(1, NMAM0177E, args);
                }
                rtnFlg = false;
                break;
            }
        }
        // 2018/12/10 QC#29315 Add End
        return rtnFlg;
    }
    // 2018/12/10 QC#29315 Mod Start
//    private void setDsCustShpgDefData(DS_CUST_SHPG_DEFTMsg dsCustSpclMsgTMsg, NMAL6700CMsg bizMsg, NMAL6700_MSMsg msMsg, boolean isCreate) {
    private void setDsCustShpgDefData(DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg, NMAL6700CMsg bizMsg, NMAL6700_MSMsg msMsg, BigDecimal dsAcctCarrPk, boolean isCreate) {
    // 2018/12/10 QC#29315 Mod End
        String glblCmpyCd = getGlobalCompanyCode();

        if (isCreate) {
            ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.dsCustShpgDefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_SHPG_DEF_SQ));
            ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.dsAcctNum, bizMsg.dsAcctNum_H1);
            // 2018/12/10 QC#29315 Add Start
            ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.dsAcctCarrPk, dsAcctCarrPk);
            // 2018/12/10 QC#29315 Add End
        }

        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.lineBizTpCd, msMsg.lineBizTpCd_M3);
        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.dsBizAreaCd, msMsg.dsBizAreaCd_M3);
        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.frtCondCd, msMsg.frtCondCd_M3);
        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.shpgSvcLvlCd, msMsg.shpgSvcLvlCd_M3);
        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.custEffLvlCd, msMsg.custEffLvlCd_M3);
        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.effFromDt, msMsg.effFromDt_M1);
        ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.effThruDt, msMsg.effThruDt_M1);
        // 2018/12/10 QC#29315 Add Start
        if (ZYPConstant.FLG_ON_Y.equals(msMsg.xxChkBox_MD.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.defFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.defFlg, ZYPConstant.FLG_OFF_N);
        }
        // 2018/12/10 QC#29315 Add End
    }

    private boolean changeFieldsCheckForDsCustShpgDef(DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg, DS_CUST_SHPG_DEFTMsg beforeDsCustShpgDefTMsg) {

        if (isNotEquals(dsCustShpgDefTMsg.lineBizTpCd.getValue(), beforeDsCustShpgDefTMsg.lineBizTpCd.getValue())
            || isNotEquals(dsCustShpgDefTMsg.dsBizAreaCd.getValue(), beforeDsCustShpgDefTMsg.dsBizAreaCd.getValue())
            || isNotEquals(dsCustShpgDefTMsg.frtCondCd.getValue(), beforeDsCustShpgDefTMsg.frtCondCd.getValue())
            || isNotEquals(dsCustShpgDefTMsg.shpgSvcLvlCd.getValue(), beforeDsCustShpgDefTMsg.shpgSvcLvlCd.getValue())
            || isNotEquals(dsCustShpgDefTMsg.custEffLvlCd.getValue(), beforeDsCustShpgDefTMsg.custEffLvlCd.getValue())
            // 2018/12/13 QC#29315 Add Start
            || isNotEquals(dsCustShpgDefTMsg.defFlg.getValue(), beforeDsCustShpgDefTMsg.defFlg.getValue())
            || isNotEquals(dsCustShpgDefTMsg.dsAcctNum.getValue(), beforeDsCustShpgDefTMsg.dsAcctNum.getValue())
            || isNotEquals(dsCustShpgDefTMsg.locNum.getValue(), beforeDsCustShpgDefTMsg.locNum.getValue())
            || isNotEquals(dsCustShpgDefTMsg.effFromDt.getValue(), beforeDsCustShpgDefTMsg.effFromDt.getValue())
            // 2018/12/13 QC#29315 Add End
            || isNotEquals(dsCustShpgDefTMsg.effThruDt.getValue(), beforeDsCustShpgDefTMsg.effThruDt.getValue())) {
            return true;
        }
        return false;
    }

    private boolean updateShipping(String glblCmpyCd, NMAL6700CMsg bizMsg, NMAL6700SMsg sharedMsg) {

        // Delete DS_CUST_SHPG_DEF
        int delCnt = sharedMsg.Z.getValidCount();
        for (int i = 0; i < delCnt; i++) {
            NMAL6700_ZSMsg zsMsg = sharedMsg.Z.no(i);
            // 2018/12/10 QC#29315 Mod Start
//            if (ZYPCommonFunc.hasValue(zsMsg.dsCustShpgDefPk_Z1)) {
//                DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
//                ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.dsCustShpgDefPk, zsMsg.dsCustShpgDefPk_Z1);
            if (ZYPCommonFunc.hasValue(zsMsg.dsAcctCarrPk_Z1)) {
                DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
                DS_ACCT_CARRTMsg dsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
                ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCustShpgDefTMsg.dsCustShpgDefPk, zsMsg.dsCustShpgDefPk_Z1);
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctCarrPk, zsMsg.dsAcctCarrPk_Z1);
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.glblCmpyCd, glblCmpyCd);
                // 2018/12/10 QC#29315 Mod End

                dsCustShpgDefTMsg = (DS_CUST_SHPG_DEFTMsg) EZDTBLAccessor.findByKeyForUpdate(dsCustShpgDefTMsg);
                if (dsCustShpgDefTMsg == null) {
                    String[] args = {"DS_CUST_SHPG_DEF" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }

                // 2018/12/10 QC#29315 Add Start
                dsAcctCarrTMsg = (DS_ACCT_CARRTMsg) EZDTBLAccessor.findByKeyForUpdate(dsAcctCarrTMsg);
                if (dsAcctCarrTMsg == null) {
                    String[] args = {"DS_ACCT_CARR" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                // 2018/12/10 QC#29315 Add End
                // 2018/12/10 QC#29315 Mod Start
//                if (!ZYPDateUtil.isSameTimeStamp(zsMsg.ezUpTime_Z2.getValue(), zsMsg.ezUpTimeZone_Z2.getValue(), dsCustShpgDefTMsg.ezUpTime.getValue(), dsCustShpgDefTMsg.ezUpTimeZone.getValue())) {
                if (!ZYPDateUtil.isSameTimeStamp(zsMsg.ezUpTime_ZS.getValue(), zsMsg.ezUpTimeZone_ZS.getValue(), dsCustShpgDefTMsg.ezUpTime.getValue(), dsCustShpgDefTMsg.ezUpTimeZone.getValue())
                        || !ZYPDateUtil.isSameTimeStamp(zsMsg.ezUpTime_ZC.getValue(), zsMsg.ezUpTimeZone_ZC.getValue(), dsAcctCarrTMsg.ezUpTime.getValue(), dsAcctCarrTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                // 2018/12/10 QC#29315 Mod End

                // 2018/12/10 QC#29315 Mod Start
//                EZDTBLAccessor.logicalRemove(dsCustShpgDefTMsg);
//                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCustShpgDefTMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo(NMAM0208E, new String[] {"DS_CUST_SPCL_MSG" });
//                    return false;
//                }
                EZDTBLAccessor.logicalRemove(dsAcctCarrTMsg);
                EZDTBLAccessor.logicalRemove(dsCustShpgDefTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCustShpgDefTMsg.getReturnCode()) || !EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0208E, new String[] {"DS_CUST_SPCL_MSG" });
                    return false;
                }
                // 2018/12/10 QC#29315 Mod End
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }
        // Insert or Update DS_CUST_SHPG_DEF
        int cnt = sharedMsg.M.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NMAL6700_MSMsg msMsg = sharedMsg.M.no(i);
            if (ZYPCommonFunc.hasValue(msMsg.dsCustShpgDefPk_M1)) {
                // 2018/12/10 QC#29315 Add Start
                DS_ACCT_CARRTMsg dsAcctCarrTMsg = NMAL6700CommonLogic.findDsAcctCarrForUpdate(glblCmpyCd, msMsg.dsAcctCarrPk_M1.getValue(), true);
                if (dsAcctCarrTMsg == null) {
                    String[] args = {"DS_ACCT_CARRTMsg" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(msMsg.ezUpTime_MC.getValue(), msMsg.ezUpTimeZone_MC.getValue(), dsAcctCarrTMsg.ezUpTime.getValue(), dsAcctCarrTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }
                // 2018/12/10 QC#29315 Add End

                DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = NMAL6700CommonLogic.findDsCustShpgDefForUpdate(glblCmpyCd, msMsg.dsCustShpgDefPk_M1.getValue(), true);
                if (dsCustShpgDefTMsg == null) {
                    String[] args = {"DS_CUST_SHPG_DEFTMsg" };
                    bizMsg.setMessageInfo(NMAM8111E, args);
                    return false;
                }
                if (!ZYPDateUtil.isSameTimeStamp(msMsg.ezUpTime_MS.getValue(), msMsg.ezUpTimeZone_MS.getValue(), dsCustShpgDefTMsg.ezUpTime.getValue(), dsCustShpgDefTMsg.ezUpTimeZone.getValue())) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return false;
                }


                // 2018/12/10 QC#29315 Mod Start
//                DS_CUST_SHPG_DEFTMsg beforeDsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
//                DS_CUST_SHPG_DEFTMsg.copy(dsCustShpgDefTMsg, null, beforeDsCustShpgDefTMsg, null);
//                setDsCustShpgDefData(dsCustShpgDefTMsg, bizMsg, msMsg, false);
//                // Change check
//                if (changeFieldsCheckForDsCustShpgDef(dsCustShpgDefTMsg, beforeDsCustShpgDefTMsg)) {
//                    // Change has been made.
//                    S21FastTBLAccessor.update(dsCustShpgDefTMsg);
//                    if (!RTNCD_NORMAL.equals(dsCustShpgDefTMsg.getReturnCode())) {
//                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SHPG_DEFTMsg" });
//                        return false;
//                    }
//                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//                }
//            } else {
//                DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
//                setDsCustShpgDefData(dsCustShpgDefTMsg, bizMsg, msMsg, true);
//
//                S21FastTBLAccessor.create(dsCustShpgDefTMsg);
//                if (!RTNCD_NORMAL.equals(dsCustShpgDefTMsg.getReturnCode())) {
//                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SHPG_DEFTMsg" });
//                    return false;
//                }
//                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
//            }
                DS_ACCT_CARRTMsg beforeDsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
                DS_CUST_SHPG_DEFTMsg beforeDsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
                DS_ACCT_CARRTMsg.copy(dsAcctCarrTMsg, null, beforeDsAcctCarrTMsg, null);
                DS_CUST_SHPG_DEFTMsg.copy(dsCustShpgDefTMsg, null, beforeDsCustShpgDefTMsg, null);
                setDsAcctCarrData(dsAcctCarrTMsg, bizMsg, msMsg, false);
                setDsCustShpgDefData(dsCustShpgDefTMsg, bizMsg, msMsg,msMsg.dsAcctCarrPk_M1.getValue(), false);
                // Change check
                if (changeFieldsCheckForDsCustShpgDef(dsCustShpgDefTMsg, beforeDsCustShpgDefTMsg) || changeFieldsCheckForDsAcctCarr(dsAcctCarrTMsg, beforeDsAcctCarrTMsg)) {
                    // Change has been made.
                    S21FastTBLAccessor.update(dsCustShpgDefTMsg);
                    S21FastTBLAccessor.update(dsAcctCarrTMsg);
                    if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_CARRTMsg" });
                        return false;
                    }
                    if (!RTNCD_NORMAL.equals(dsCustShpgDefTMsg.getReturnCode())) {
                        bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SHPG_DEFTMsg" });
                        return false;
                    }
                    bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
                }
            } else {
                DS_CUST_SHPG_DEFTMsg dsCustShpgDefTMsg = new DS_CUST_SHPG_DEFTMsg();
                DS_ACCT_CARRTMsg dsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
                setDsAcctCarrData(dsAcctCarrTMsg, bizMsg, msMsg, true);
                setDsCustShpgDefData(dsCustShpgDefTMsg, bizMsg, msMsg, dsAcctCarrTMsg.dsAcctCarrPk.getValue(), true);

                S21FastTBLAccessor.create(dsAcctCarrTMsg);
                if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_CARRTMsg" });
                    return false;
                }
                S21FastTBLAccessor.create(dsCustShpgDefTMsg);
                if (!RTNCD_NORMAL.equals(dsCustShpgDefTMsg.getReturnCode())) {
                    bizMsg.setMessageInfo(NMAM0177E, new String[] {"DS_CUST_SHPG_DEFTMsg" });
                    return false;
                }
                bizMsg.xxExistCmntFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
            // 2018/12/10 QC#29315 Mod End
        }
        return true;
    }
    // 2018/02/14 QC#20297(Sol#379) add end
    // 2018/12/10 QC#29315 Add Start
    private List<ArrayList> setCarrCombList(NMAL6700_MSMsg msMsg, List<ArrayList> carrCombList, int rowNum) {
        int defFlgCnt;
        int matchCnt = 0;
        List<String> defFlagList = new ArrayList<String>();
        for (int i = 0; i < carrCombList.size(); i++) {
            defFlagList = (List<String>) carrCombList.get(i);
            if (!isNotEquals(defFlagList.get(0), msMsg.lineBizTpCd_M3.getValue()) //
                    && !isNotEquals(defFlagList.get(1), msMsg.dsBizAreaCd_M3.getValue())) {
                matchCnt++;
                defFlagList.add(String.valueOf(rowNum));
                if (ZYPCommonFunc.hasValue(msMsg.xxChkBox_MD) && ZYPConstant.FLG_ON_Y.equals(msMsg.xxChkBox_MD.getValue())) {
                    defFlgCnt = Integer.parseInt(defFlagList.get(2));
                    defFlgCnt++;
                    defFlagList.set(2, String.valueOf(defFlgCnt));
                    return carrCombList;
                }
            }
        }
        // add new combination list
        if (matchCnt > 0) {
            return carrCombList;
        } else {
            List<String> defFlagNewList = new ArrayList<String>();
            defFlagNewList.clear();
            defFlagNewList.add(0, msMsg.lineBizTpCd_M3.getValue());
            defFlagNewList.add(1, msMsg.dsBizAreaCd_M3.getValue());
            if (ZYPCommonFunc.hasValue(msMsg.xxChkBox_MD) && ZYPConstant.FLG_ON_Y.equals(msMsg.xxChkBox_MD.getValue())) {
                defFlagNewList.add(2, "1");
            } else {
                defFlagNewList.add(2, "0");
            }
            defFlagNewList.add(3, String.valueOf(rowNum));
            carrCombList.add((ArrayList) defFlagNewList);
        }
        return carrCombList;
    }
    // 2018/12/10 QC#29315 Add End

    // START 2022/03/22 [QC#59683, ADD]
    private DS_INV_TGTR_TPTMsg getDsInvTgtrTp(String dsInvTgtrTPCd) {
        DS_INV_TGTR_TPTMsg tMsg = new DS_INV_TGTR_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.dsInvTgtrTpCd, dsInvTgtrTPCd);
        tMsg = (DS_INV_TGTR_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
        return tMsg;
    }
    // END   2022/03/22 [QC#59683, ADD]

    private void clearExistsPrimLocPrinCust(String glblCmpyCd, String locNum, BigDecimal prinCustPk) {
        S21SsmEZDResult ssmResult = NMAL6700Query.getInstance().getExistPrinCust(glblCmpyCd, locNum, prinCustPk);
        if (ssmResult.isCodeNotFound()) {
            return;
        }

        List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (Map<String, Object> map : listMap) {
            PRIN_CUSTTMsg updatePrinCustTMsg = NMAL6700CommonLogic.findPrinCustForUpdate(glblCmpyCd, (BigDecimal) map.get("PRIN_CUST_PK"));
            
            updatePrinCustTMsg.firstLineAddr.clear();
            updatePrinCustTMsg.scdLineAddr.clear();
            updatePrinCustTMsg.thirdLineAddr.clear();
            updatePrinCustTMsg.frthLineAddr.clear();
            updatePrinCustTMsg.ctyAddr.clear();
            updatePrinCustTMsg.cntyPk.clear();
            updatePrinCustTMsg.provNm.clear();
            updatePrinCustTMsg.stCd.clear();
            updatePrinCustTMsg.postCd.clear();
            updatePrinCustTMsg.ctryCd.clear();
            updatePrinCustTMsg.locNum.clear();
            updatePrinCustTMsg.ptyLocPk.clear();
            S21FastTBLAccessor.update(updatePrinCustTMsg);
        }
    }

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateAccountAndLocation
     * @param sellToCustTMsg SELL_TO_CUSTTMsg
     * @param bizMsg NMAL6700CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean hasDeactivateAccountAndLocation(SELL_TO_CUSTTMsg sellToCustTMsg, NMAL6700CMsg bizMsg, String glblCmpyCd) {

        SELL_TO_CUSTTMsg originSellToCustTMsg = new SELL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(originSellToCustTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(originSellToCustTMsg.sellToCustPk, bizMsg.sellToCustPk_H1.getValue());
        originSellToCustTMsg = (SELL_TO_CUSTTMsg) S21FastTBLAccessor.findByKey(originSellToCustTMsg);

        if (originSellToCustTMsg == null) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H1.getValue())) {

            if (!originSellToCustTMsg.deacNewTrxFlg.getValue().equals(bizMsg.xxChkBox_H3.getValue())) {

                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H3.getValue())) {
                    String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxDt, slsDt);
                } else {
                    ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                    sellToCustTMsg.deacNewTrxDt.clear();
                }
                return true;
            }
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(originSellToCustTMsg.deacNewTrxFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                sellToCustTMsg.deacNewTrxDt.clear();
                return true;
            }
        }
        return false;
    }
    // 2023/11/06 QC#61924 Add End
}
