/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6720;

import static business.blap.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.blap.NMAL6720.constant.NMAL6720Constant.ERR_MSG_RELATED_TECH_WH;
import static business.blap.NMAL6720.constant.NMAL6720Constant.FUNC_ID_LOCATION_GENERAL_UPDATE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.IN_ACTIVE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MAX_DT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MAX_LEN_DPL_RSN_TXT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_ACCT_LOC;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_ALT_PAYER;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_BILL_TO_CUST;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_CUSTOMER;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_ACCT_CARR;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_ACCT_PROS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_CTAC_PSN_RELN;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_CUST_NON_PRF_TECH;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_CUST_SHPG_DEF;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_CUST_SPCL_MSG;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_CUST_TRX_RULE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_DS_XREF_ACCT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_INV_RCPNT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_LOC_USG;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_PRIN_CUST;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_PROSPECT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_PROS_PTY_LOC_WRK;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_PTY_LOC_WRK;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_RTL_WH;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_SELL_TO_CUST;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_SHIP_TO_CUST;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_START_DT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_SVC_ACCS_PMIT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_SVC_ACCS_PMIT_NUM;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_TECH_MSTR;
import static business.blap.NMAL6720.constant.NMAL6720Constant.MSG_WH;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NAMM0016E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM0176E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM0177E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM0303E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8052E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8111E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8113E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8115E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8178E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8200E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8261W;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8343E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8360W;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8370E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8372E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8389E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8492W;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8631E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8632E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8642E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8649I;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8650E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8651E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8652E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8663E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8666E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8693E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8694E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NWAM0984E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NZZM0000E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NZZM0001W;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NZZM0003E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NZZM0011E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8711E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8121E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.NMAM8725E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING;
import static business.blap.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_EDIT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_ACCOUNT;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_CLASSIFICATIONS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_CONTACTS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_MSG_NOTE;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_SHIPPING;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_SVC_ATTRB;
import static business.blap.NMAL6720.constant.NMAL6720Constant.TAB_TRANSACTIONS;
import static business.blap.NMAL6720.constant.NMAL6720Constant.VAR_CHAR_CONST_NM_ACCT_INAC_RSN_CD;
import static business.blap.NMAL6720.constant.NMAL6720Constant.VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD;
import static business.blap.NMAL6720.constant.NMAL6720Constant.ZZIM0094E;
import static business.blap.NMAL6720.constant.NMAL6720Constant.ZZZM9025E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDStringUtil;
import parts.common.EZDTDateItem;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6720.common.NMAL6720CommonLogic;
import business.blap.NMAL6720.constant.NMAL6720Constant;
import business.db.ACCT_LOCTMsg;
import business.db.ACCT_LOCTMsgArray;
import business.db.ALT_PAYERTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CTRYTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.DS_ACCT_CARRTMsg;
import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_PROSTMsgArray;
import business.db.DS_CTAC_PSN_RELNTMsg;
import business.db.DS_CUST_NON_PRF_TECHTMsg;
import business.db.DS_CUST_SHPG_DEFTMsg;
import business.db.DS_CUST_SPCL_MSGTMsg;
import business.db.DS_CUST_TRX_RULETMsg;
import business.db.DS_XREF_ACCTTMsg;
import business.db.INV_RCPNTTMsg;
import business.db.LOC_USGTMsg;
import business.db.LOC_USGTMsgArray;
import business.db.PRIN_CUSTTMsg;
import business.db.PROS_PTY_LOC_WRKTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTL_WHTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.SVC_ACCS_PMITTMsg;
import business.db.SVC_ACCS_PMIT_VALTMsg;
import business.db.SVC_ACCS_PMIT_VALTMsgArray;
import business.db.TECH_MSTRTMsg;
import business.db.WHTMsg;
import business.db.WHTMsgArray;
import business.parts.NMXC107001PMsg;
import business.parts.NMZC003001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;

import com.canon.cusa.s21.api.NMX.NMXC107001.NMXC107001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_EFF_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DPL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_CHNG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TMPL;
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
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.usa.s21.integration.api.properties.PropertyException;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.CallPartyScreeningServiceFaultDataExp;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.CallPartyScreeningServiceFaultResourceExp;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.CallPartyScreeningServiceFaultUserExp;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Administration;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Country;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.OutputFormat;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Parameter;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Parameters;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Party;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Request;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ServiceRequest;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ShipFromCountry;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.ShipToCountry;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Transaction;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.TransactionLine;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service.Svc;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.DeniedParty;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Error;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Response;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.response.Service;
import com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.wrapper.S21ChileKewillPartyScreeningServiceProxy;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/01/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/24   SRAA            Y.Chen          Update          QC#3580
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#2756
 * 2016/02/26   Fujitsu         C.Tanaka        Update          QC#4349
 * 2016/02/29   Fujitsu         C.Tanaka        Update          QC#2824
 * 2016/04/14   SRAA            Y.Chen          Update          QC#5749
 * 2016/04/14   SRAA            Y.Chen          Update          QC#6183
 * 2016/04/26   SRAA            Y.Chen          Update          QC#6186
 * 2016/04/27   SRAA            Y.Chen          Update          QC#6918, QC#7375
 * 2016/04/28   SRAA            Y.Chen          Update          QC#2674
 * 2016/05/18   Fujitsu         N.Sugiura       Update          Unit Test#193
 * 2016/05/25   SRAA            Y.Chen          Update          QC#9018
 * 2016/06/02   Fujitsu         S.Ohki          Update          QC#9382
 * 2016/06/21   Fujitsu         N.Sugiura       Update          QC#10493
 * 2016/07/05   Fujitsu         C.Tanaka        Update          QC#10677
 * 2016/07/06   SRAA            Y.Chen          Update          QC#10658
 * 2016/07/19   Hitachi         Y.Tsuchimoto    Update          QC#10745
 * 2016/07/19   Fujitsu         R.Nakamura      Update          QC#11729
 * 2016/08/17   SRAA            Y.Chen          Update          QC#11569
 * 2016/09/02   Fujitsu         N.Sugiura       Update          QC#13486
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2016/09/22   SRAA            Y.Chen          Update          QC#12060
 * 2016/09/30   Fujitsu         C.Yokoi         Update          QC#13877, QC#14720
 * 2016/10/13   Fujitsu         C.Yokoi         Update          QC#4096
 * 2016/10/20   Fujitsu         C.Yokoi         Update          QC#12060, QC#14720
 * 2016/12/16   Fujitsu         N.Aoyama        Update          QC#16659
 * 2016/12/27   Fujitsu         C.Yokoi         Update          QC#14924
 * 2017/02/20   Fujitsu         T.Aoi           Update          QC#16846
 * 2017/06/21   Fujitsu         N.Sugiura       Update          QC#19424
 * 2017/06/29   Hitachi         J.Kim           Update          QC#17670
 * 2017/08/10   Fujitsu         N.Sugiura       Update          QC#8598
 * 2017/11/27   Fujitsu         A.Kosai         Update          QC#20828
 * 2017/12/19   Fujitsu         A.Kosai         Update          QC#20828-1
 * 2018/02/19   Fujitsu         M.Ohno          Update          QC#20297(Sol#379)
 * 2018/03/26   Fujitsu         K.Ishizuka      Update          QC#23935
 * 2018/04/17   Fujitsu         M.Ohno          Update          QC#24635
 * 2018/04/19   Fujitsu         H.Ikeda         Update          QC#23882
 * 2018/05/16   Fujitsu         Hd.Sugawara     Update          QC#26041
 * 2018/08/30   Fujitsu         W.Honda         Update          QC#27869
 * 2018/10/09   Fujitsu         Hd.Sugawara     Update          QC#27598
 * 2018/10/26   Fujitsu         S.Kosaka        Update          QC#28841
 * 2018/11/13   Fujitsu         M.Ohno          Update          QC#27954
 * 2018/12/13   Fujitsu         M.Ishii         Update          QC#29315
 * 2019/01/19   Fujitsu         S.Kosaka        Update          QC#29940
 * 2019/02/19   Fujitsu         R.Nakamura      Update          QC#30293
 * 2019/04/05   Fujitsu         T.Noguchi       Update          QC#31030
 * 2019/05/08   Fujitsu         T.Noguchi       Update          QC#50101
 * 2019/05/23   Fujitsu         Hd.Sugawara     Update          QC#50101
 * 2019/08/07   Fujitsu         Hd.Sugawara     Update          QC#52385
 * 2019/09/27   Fujitsu         S.Kosaka        Update          QC#53591
 * 2019/12/13   Fujitsu         M.Nakamura      Update          QC#54882
 * 2020/03/24   Fujitsu         M.Ohno          Update          QC#55673
 * 2020/07/22   CITS            K.Ogino         Update          QC#57316
 * 2021/05/20   CITS            M.Furugoori     Update          QC#58743
 * 2022/01/12   CITS            R.Azucena       Update          QC#59596
 * 2023/01/13   Hitachi         H.Watanabe      Update          QC#60860
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 *</pre>
 */
public class NMAL6720BL06 extends S21BusinessHandler {

    /** isActive */
    boolean isActive = false;

    /** isInactive */
    boolean isInactive = false;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;
        NMAL6720SMsg sharedMsg = (NMAL6720SMsg) sMsg;
        super.preDoProcess(bizMsg, sharedMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6720Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL6720Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, sharedMsg);
            } else if ("NMAL6720Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private boolean checkInputTabMandatory(NMAL6720CMsg bizMsg) {

        boolean errorFlg = true;

        // TAB_ACCOUNT
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NMAL6720_ACMsg acMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(acMsg.effFromDt_A1)) {
                acMsg.effFromDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Start Date" });
                errorFlg = false;
            }
            if (!ZYPCommonFunc.hasValue(acMsg.dsAcctNum_A1)) {
                acMsg.dsAcctNum_A1.setErrorInfo(1, ZZZM9025E, new String[] {"Related Acct#" });
                errorFlg = false;
            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_ACCOUNT);
            return errorFlg;
        }

        // TAB_CLASSIFICATIONS
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NMAL6720_BCMsg bcMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bcMsg.dsXrefAcctTpCd_P1)) {
                bcMsg.dsXrefAcctTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Cross Reference Type" });
                errorFlg = false;
            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_CLASSIFICATIONS);
            return errorFlg;
        }

        // TAB_CONTACTS
        // Nothing

        // TAB_TRANSACTIONS
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            NMAL6720_DCMsg dcMsg = bizMsg.D.no(i);
            if (!ZYPCommonFunc.hasValue(dcMsg.dsTrxRuleTpCd_P1)) {
                dcMsg.dsTrxRuleTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Transaction Type" });
                errorFlg = false;
            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_TRANSACTIONS);
            return errorFlg;
        }

        // TAB_SVC_ATTRB
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            NMAL6720_FCMsg fcMsg = bizMsg.F.no(i);
            if (!ZYPCommonFunc.hasValue(fcMsg.svcAccsPmitNum_F1)) {
                fcMsg.svcAccsPmitNum_F1.setErrorInfo(1, "ZZM9000E", new String[] {"No" });
                errorFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(fcMsg.effFromDt_F1)) {
                fcMsg.effFromDt_F1.setErrorInfo(1, "ZZM9000E", new String[] {"Start Date" });
                errorFlg = false;
            }
        }
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            NMAL6720_GCMsg gcMsg = bizMsg.G.no(i);
            if (!ZYPCommonFunc.hasValue(gcMsg.nonPrfTechCd_G1)) {
                gcMsg.nonPrfTechCd_G1.setErrorInfo(1, "ZZM9000E", new String[] {"Employee ID" });
                errorFlg = false;
            }
        }
        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_SVC_ATTRB);
            return errorFlg;
        }

        if (!errorFlg) {
            bizMsg.xxDplyTab.setValue(TAB_MSG_NOTE);
            return errorFlg;
        }
        return errorFlg;
    }

    private boolean checkMsgNoteTabMandatory(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.E, sMsg.E, cMsg.xxPageShowFromNum_E);

        boolean errorFlg = true;
        NMAL6720_ESMsg eSMsg;
        int errIdx = 0;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            eSMsg = sMsg.E.no(i);
            if (!ZYPCommonFunc.hasValue(eSMsg.lineBizTpCd_P2)) {
                eSMsg.lineBizTpCd_P2.setErrorInfo(1, ZZZM9025E, new String[] {"Line Of Business" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
            if (!ZYPCommonFunc.hasValue(eSMsg.dsBizAreaCd_P1)) {
                eSMsg.dsBizAreaCd_P1.setErrorInfo(1, ZZZM9025E, new String[] {"Business Area" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
            if (!ZYPCommonFunc.hasValue(eSMsg.dsCustMsgTpCd_P1)) {
                eSMsg.dsCustMsgTpCd_P1.setErrorInfo(1, ZZZM9025E, new String[] {"Type" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
            if (!ZYPCommonFunc.hasValue(eSMsg.dsCustMsgTxt_E1)) {
                eSMsg.dsCustMsgTxt_E1.setErrorInfo(1, ZZZM9025E, new String[] {"Message Body" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
        }

        if (!errorFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_MSG_NOTE);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, errIdx);
        }

        return errorFlg;
    }

    private boolean saveHeaderInfo(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        BigDecimal shipToCustPk = cMsg.shipToCustPk_SH.getValue();

        // Header
        saveLocInfo(cMsg, sMsg);

        saveBillToCustByAccountTab(cMsg, sMsg);

        if ("E".equals(cMsg.getMessageKind())) {
            return false;
        }

        saveShipToCustByAccountTab(cMsg, sMsg);

        // 2020/03/02 QC#55673 Add Start
        saveRtlWh(cMsg, sMsg);
        // 2020/03/02 QC#55673 Add End

        saveInvRcpntInfo(cMsg);
        saveAltPayer(cMsg);

        savePrinCustByAccountTab(cMsg, sMsg, cMsg.dsAcctTpCd_H1.getValue());

        if (ZYPCommonFunc.hasValue(shipToCustPk)) {
            if (isUpdateShipTo(cMsg.effFromDt_SH.getValue(), cMsg.effThruDt_SH.getValue()) && isAddressChanged(cMsg, sMsg)) {
                saveWareHouseAddress(cMsg, sMsg);
            }
        }

        if ("E".equals(cMsg.getMessageKind())) {
            return false;
        }
        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue())) {
            int saveAcct = saveDsAcctCust(cMsg, sMsg);
            if (saveAcct == 0) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_CUSTOMER });
                return false;
            } else if (saveAcct == -1) {
                return false;
            }
        } else {
            int savePros = saveDsAcctPros(cMsg, sMsg);
            if (savePros == 0) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PROSPECT });
                return false;
            } else if (savePros == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAddressLength(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (!ZYPConstant.FLG_ON_1.equals(cMsg.xxPgFlg_L1.getValue())) {
            boolean isWarning = false;
            if (cMsg.firstLineAddr_H1.getValue().length() > NMAL6720Constant.MAX_LOC_ADDR_LEN && !cMsg.firstLineAddr_H1.getValue().equals(sMsg.firstLineAddr_H1.getValue())) {
                cMsg.firstLineAddr_H1.setErrorInfo(2, NMAM8492W);
                isWarning = true;
            }
            if (cMsg.scdLineAddr_H1.getValue().length() > NMAL6720Constant.MAX_LOC_ADDR_LEN && !cMsg.scdLineAddr_H1.getValue().equals(sMsg.scdLineAddr_H1.getValue())) {
                cMsg.scdLineAddr_H1.setErrorInfo(2, NMAM8492W);
                isWarning = true;
            }
            if (cMsg.thirdLineAddr_H1.getValue().length() > NMAL6720Constant.MAX_LOC_ADDR_LEN && !cMsg.thirdLineAddr_H1.getValue().equals(sMsg.thirdLineAddr_H1.getValue())) {
                cMsg.thirdLineAddr_H1.setErrorInfo(2, NMAM8492W);
                isWarning = true;
            }
            if (cMsg.frthLineAddr_H1.getValue().length() > NMAL6720Constant.MAX_LOC_ADDR_LEN && !cMsg.frthLineAddr_H1.getValue().equals(sMsg.frthLineAddr_H1.getValue())) {
                cMsg.frthLineAddr_H1.setErrorInfo(2, NMAM8492W);
                isWarning = true;
            }
            if (isWarning) {
                cMsg.setMessageInfo(NMAM8492W);
                cMsg.xxPgFlg_L1.setValue(ZYPConstant.FLG_ON_1);
                return false;
            }
        } else {
            cMsg.xxPgFlg_L1.setValue(ZYPConstant.FLG_OFF_0);
        }
        return true;
    }

    private boolean checkAddress(NMAL6720CMsg cMsg) {
        boolean result = true;

        NMZC003001PMsg param = new NMZC003001PMsg();
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

                boolean hasItemError = false;
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_01.getValue())) {
                    cMsg.firstLineAddr_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_02.getValue())) {
                    cMsg.scdLineAddr_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_03.getValue())) {
                    cMsg.ctyAddr_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_04.getValue())) {
                    cMsg.stCd_P1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_05.getValue())) {
                    cMsg.postCd_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_06.getValue())) {
                    cMsg.ctryCd_P1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(param.xxVldStsCd_07.getValue())) {
                    cMsg.cntyNm_H1.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (!hasItemError) {
                    cMsg.setMessageInfo(msgId, msgPrms);
                }

                return false;
            }
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_01.getValue())) {
            cMsg.firstLineAddr_H1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_01.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_H1, param.firstLineAddr);
            cMsg.firstLineAddr_H1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_02.getValue())) {
            cMsg.scdLineAddr_H1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_H1, param.scdLineAddr);
            cMsg.scdLineAddr_H1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_03.getValue())) {
            cMsg.ctyAddr_H1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_03.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_H1, param.ctyAddr);
            cMsg.ctyAddr_H1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_04.getValue())) {
            cMsg.stCd_P1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_04.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.stCd_P1, param.stCd);
            cMsg.stCd_P1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_05.getValue())) {
            cMsg.postCd_H1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.postCd_H1, param.postCd);
            cMsg.postCd_H1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_06.getValue())) {
            cMsg.ctryCd_P1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_06.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_P1, param.ctryCd);
            cMsg.ctryCd_P1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_07.getValue())) {
            cMsg.cntyNm_H1.setErrorInfo(1, NMAM8666E);
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_H1, param.cntyNm);
            cMsg.cntyNm_H1.setErrorInfo(2, NMAM8360W);
            cMsg.setMessageInfo(NMAM8360W);
            result = false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_H1, param.cntyPk);

        return result;
    }

    private boolean checkReactivate(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        boolean result = true;
        String slsDt = ZYPDateUtil.getSalesDate();

        String billToFlg = cMsg.dsAcctRelnBillToFlg_BI.getValue();
        String shipToFlg = cMsg.dsAcctRelnShipToFlg_SH.getValue();
        String effThruDtB = MAX_DT;
        String effThruDtS = MAX_DT;

        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
            effThruDtB = cMsg.effThruDt_BI.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
            effThruDtS = cMsg.effThruDt_SH.getValue();
        }

        if (RGTN_STS.TERMINATED.equals(sMsg.rgtnStsCd_H1.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
            if (ZYPConstant.FLG_ON_Y.equals(billToFlg) && ZYPConstant.FLG_ON_Y.equals(shipToFlg)) {
                if (ZYPDateUtil.compare(slsDt, effThruDtB) >= 0 && ZYPDateUtil.compare(slsDt, effThruDtS) >= 0) {
                    cMsg.effThruDt_BI.setErrorInfo(1, NMAM8370E);
                    cMsg.effThruDt_SH.setErrorInfo(1, NMAM8370E);
                    result = false;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(billToFlg)) {
                if (ZYPDateUtil.compare(slsDt, effThruDtB) == 1) {
                    cMsg.effThruDt_BI.setErrorInfo(1, NMAM8370E);
                    result = false;
                }
            } else if (ZYPConstant.FLG_ON_Y.equals(shipToFlg)) {
                if (ZYPDateUtil.compare(slsDt, effThruDtS) == 1) {
                    cMsg.effThruDt_SH.setErrorInfo(1, NMAM8370E);
                    result = false;
                }
            }
        }

        if (ZYPConstant.FLG_ON_Y.equals(shipToFlg) && ZYPCommonFunc.hasValue(cMsg.billToCustCd_SH)) {
            final NMZC610001 custInfoGetApi = new NMZC610001();

            // Create Api Parameter
            NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.billToCustCd, cMsg.billToCustCd_SH);
            ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I2, cMsg.dsAcctNum_H1);

            // Call Api
            custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(custInfoGetApiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(custInfoGetApiPMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    cMsg.setMessageInfo(msgId, msgPrms);
                    result = false;
                    break;
                }
            } else {
                NMZC610001_EligibleCheckListPMsg checkPMsg = custInfoGetApiPMsg.EligibleCheckList.no(0);
                if (!ZYPConstant.FLG_ON_Y.equals(checkPMsg.dsAcctRelnBillToFlg_B.getValue())) {
                    String[] params = {"Account#", "Related Bill To code", "Related Bill To code" };
                    cMsg.dsAcctRelnShipToFlg_SH.setErrorInfo(1, NMAM8389E, params);
                    result = false;
                }
            }
        }
        return result;
    }

    // Mod Start 2018/05/16 QC#26041
    //private boolean checkBillToShipToDate(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
    private boolean checkBillToShipToDate(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, List<String> functionIds) {
        // Mod End 2018/05/16 QC#26041

        if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_H1.getValue())) {
            if (!NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(cMsg.prosToCustChngStsCd_H1.getValue())) {
                return true;
            }
            // Add Start 2018/05/16 QC#26041
            if (!functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE)) {
                return true;
            }
            // Add End 2018/05/16 QC#26041
        }

        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue())) {
            if (!NMAL6720CommonLogic.isAcctActive(cMsg.dsAcctNum_H1.getValue(), getGlobalCompanyCode())) {
                return true;
            }
        }

        boolean result = true;
        String slsDt = ZYPDateUtil.getSalesDate();

        String billToFlg = cMsg.dsAcctRelnBillToFlg_BI.getValue();
        String shipToFlg = cMsg.dsAcctRelnShipToFlg_SH.getValue();
        String bakBillToFlg = sMsg.dsAcctRelnBillToFlg_BI.getValue();
        String bakShipToFlg = sMsg.dsAcctRelnShipToFlg_SH.getValue();
        String bakEffThruDtB = MAX_DT;
        String bakEffThruDtS = MAX_DT;
        String effFromDtB = null;
        String effFromDtS = null;
        String effThruDtB = MAX_DT;
        String effThruDtS = MAX_DT;

        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
            effThruDtB = cMsg.effThruDt_BI.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
            effThruDtS = cMsg.effThruDt_SH.getValue();
        }
        if (ZYPCommonFunc.hasValue(sMsg.effThruDt_BI)) {
            bakEffThruDtB = sMsg.effThruDt_BI.getValue();
        }
        if (ZYPCommonFunc.hasValue(sMsg.effThruDt_SH)) {
            bakEffThruDtS = sMsg.effThruDt_SH.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
            effFromDtB = cMsg.effFromDt_BI.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt_SH)) {
            effFromDtS = cMsg.effFromDt_SH.getValue();
        }

        // ship to cust
        if (ZYPConstant.CHKBOX_ON_Y.equals(bakShipToFlg) && !bakShipToFlg.equals(shipToFlg)) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(cMsg.rgtnStsCd_SH.getValue())) {
                if (ZYPDateUtil.compare(slsDt, effThruDtS) <= 0 && ZYPDateUtil.compare(bakEffThruDtS, effThruDtS) == 0) {
                    cMsg.effThruDt_SH.setErrorInfo(1, NMAM8642E);
                    result = false;
                }
            }
        } else if (ZYPConstant.CHKBOX_ON_Y.equals(shipToFlg) && !shipToFlg.equals(bakShipToFlg)) {
            if (ZYPCommonFunc.hasValue(sMsg.effFromDt_SH) && !RGTN_STS.READY_FOR_ORDER_TAKING.equals(cMsg.rgtnStsCd_SH.getValue())) {
                if (ZYPDateUtil.compare(slsDt, effThruDtS) > 0 && ZYPDateUtil.compare(bakEffThruDtS, effThruDtS) == 0) {
                    cMsg.effThruDt_SH.setErrorInfo(1, NMAM8651E);
                    result = false;
                }
            }
        }
        if (ZYPCommonFunc.hasValue(effFromDtS)) {
            if (ZYPDateUtil.compare(slsDt, effFromDtS) >= 0 && ZYPDateUtil.compare(slsDt, effThruDtS) <= 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctRelnShipToFlg_SH, ZYPConstant.CHKBOX_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctRelnShipToFlg_SH, "");
            }
        }

        // bill to cust
        if (ZYPConstant.CHKBOX_ON_Y.equals(bakBillToFlg) && !bakBillToFlg.equals(billToFlg)) {
            if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(cMsg.rgtnStsCd_BI.getValue())) {
                if (ZYPDateUtil.compare(slsDt, effThruDtB) <= 0 && ZYPDateUtil.compare(bakEffThruDtB, effThruDtB) == 0) {
                    cMsg.effThruDt_BI.setErrorInfo(1, NMAM8642E);
                    result = false;
                }
            }
        } else if (ZYPConstant.CHKBOX_ON_Y.equals(billToFlg) && !billToFlg.equals(bakBillToFlg)) {
            if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(cMsg.rgtnStsCd_BI.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.effFromDt_BI) && ZYPDateUtil.compare(slsDt, effThruDtB) > 0 && ZYPDateUtil.compare(bakEffThruDtB, effThruDtB) == 0) {
                    cMsg.effThruDt_BI.setErrorInfo(1, NMAM8651E);
                    result = false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(effFromDtB)) {
            if (ZYPDateUtil.compare(slsDt, effFromDtB) >= 0 && ZYPDateUtil.compare(slsDt, effThruDtB) <= 0) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctRelnBillToFlg_BI, ZYPConstant.CHKBOX_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctRelnBillToFlg_BI, "");
            }
        }

        if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {
            if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue()) || NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(cMsg.prosToCustChngStsCd_H1.getValue())) {
                if (ZYPDateUtil.compare(slsDt, effThruDtS) > 0 && ZYPDateUtil.compare(slsDt, effThruDtB) > 0) {
                    cMsg.effThruDt_SH.setErrorInfo(1, NMAM8652E);
                    cMsg.effThruDt_BI.setErrorInfo(1, NMAM8652E);
                } else {
                    cMsg.dsAcctRelnShipToFlg_SH.setErrorInfo(1, NZZM0011E);
                    cMsg.dsAcctRelnBillToFlg_BI.setErrorInfo(1, NZZM0011E);
                }
                // 2017/12/19 S21_NA#20828-1 Add Start
                result = false;
                // 2017/12/19 S21_NA#20828-1 Add End
            }
        }

        return result;
    }

    private boolean checkAcct(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        S21SsmEZDResult ssmResult;

        // 2017/11/27 QC#20828 Del Start
        //if (NMAL6720CommonLogic.checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {
        // 2017/11/27 QC#20828 Del End
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                // 2017/11/27 QC#20828 Mod Start
                //ssmResult = NMAL6720CommonLogic.getAcct(cMsg.A.no(i).dsAcctNum_A1.getValue(), getGlobalCompanyCode(), cMsg.dsAcctTpCd_H1.getValue());
                ssmResult = NMAL6720CommonLogic.getAcct(cMsg.A.no(i).dsAcctNum_A1.getValue(), getGlobalCompanyCode(), cMsg.A.no(i).dsAcctTpCd_A1.getValue());
                // 2017/11/27 QC#20828 Mod End
                if (ssmResult.isCodeNotFound()) {

                    cMsg.A.no(i).dsAcctNum_A1.setErrorInfo(1, NZZM0000E);

                    return false;
                }
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                // 2017/11/27 QC#20828 Mod Start
                //ssmResult = NMAL6720CommonLogic.getAcct(sMsg.A.no(i).dsAcctNum_A1.getValue(), getGlobalCompanyCode(), cMsg.dsAcctTpCd_H1.getValue());
                ssmResult = NMAL6720CommonLogic.getAcct(sMsg.A.no(i).dsAcctNum_A1.getValue(), getGlobalCompanyCode(), sMsg.A.no(i).dsAcctTpCd_A1.getValue());
                // 2017/11/27 QC#20828 Mod End
                if (ssmResult.isCodeNotFound()) {
                    cMsg.setMessageInfo(NZZM0000E);
                    return false;
                }
            }
        // 2017/11/27 QC#20828 Del Start
//        }
        // 2017/11/27 QC#20828 Del End
        return true;
    }

    private boolean checkAcctRelnDate(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        // 2017/11/27 QC#20828 Del Start
//        if (!NMAL6720CommonLogic.checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {
//            return true;
//        }
        // 2017/11/27 QC#20828 Del End

        NMAL6720CommonLogic.copyAcctListInput(cMsg, sMsg);

        // 2017/11/27 QC#20828 Add Start
        if (!NMAL6720CommonLogic.checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {
            return true;
        }
        // 2017/11/27 QC#20828 Add End

        boolean errFlg = true;
        boolean hasSalesStartDate = false;
        // START 2022/01/12 R.Azucena[QC#59596, ADD]
        boolean hasActiveOrFutureAcct = false;
        // END 2022/01/12 R.Azucena[QC#59596, ADD]
        String salesDt = ZYPDateUtil.getSalesDate();
        int cnt = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2022/01/12 R.Azucena[QC#59596, ADD]
            if (isInPeriod(salesDt, sMsg.A.no(i).effFromDt_A1.getValue(), sMsg.A.no(i).effThruDt_A1.getValue())
                    || (salesDt.compareTo(sMsg.A.no(i).effFromDt_A1.getValue()) <= 0)) {
                hasActiveOrFutureAcct = true;
            }
            // END 2022/01/12 R.Azucena[QC#59596, ADD]

            String fromDt1 = sMsg.A.no(i).effFromDt_A1.getValue();
            String thruDt1 = sMsg.A.no(i).effThruDt_A1.getValue();
            if (fromDt1.isEmpty()) {
                fromDt1 = MAX_DT;
            }
            if (thruDt1.isEmpty()) {
                thruDt1 = MAX_DT;
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_AB.getValue()) && fromDt1.compareTo(sMsg.A.no(i).effFromDt_AB.getValue()) != 0 && fromDt1.compareTo(ZYPDateUtil.getSalesDate()) == 0) {

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_AI.getValue())) {
                    cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM8663E, new String[] {MSG_START_DT });
                } else {
                    cMsg.A.no(cnt).effFromDt_A1.setErrorInfo(1, NMAM8663E, new String[] {MSG_START_DT });
                }
                return false;
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_AB) && (sMsg.A.no(i).effFromDt_AB.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0)) {
                // 2019/09/27 QC#53591 Mod Start
//                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_AB) || sMsg.A.no(i).effThruDt_AB.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0) {
//                    hasSalesStartDate = true;
//                }
                hasSalesStartDate = true;
                // 2019/09/27 QC#53591 Mod End
            }

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).rgtnStsCd_A1) && !RGTN_STS.PENDING_COMPLETION.equals(sMsg.A.no(i).rgtnStsCd_A1.getValue())) {
                if (0 > ZYPDateUtil.compare(salesDt, fromDt1)) {
                    cMsg.setMessageInfo(NMAM0303E);
                }
            }

            // Date Check
            if (ZYPDateUtil.compare(fromDt1, thruDt1) > 0) {
                cMsg.setMessageInfo(NMAM8115E);
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ACCOUNT);
                errFlg = false;
            }

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                String fromDt2 = sMsg.A.no(j).effFromDt_A1.getValue();
                String thruDt2 = sMsg.A.no(j).effThruDt_A1.getValue();
                if (fromDt2.isEmpty()) {
                    fromDt2 = MAX_DT;
                }
                if (thruDt2.isEmpty()) {
                    thruDt2 = MAX_DT;
                }
                if (i != j && 0 <= ZYPDateUtil.compare(fromDt1, fromDt2) && 0 >= ZYPDateUtil.compare(fromDt1, thruDt2)) {
                    cMsg.setMessageInfo(NMAM8113E);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ACCOUNT);
                    errFlg = false;
                } else if (i != j && 0 <= ZYPDateUtil.compare(thruDt1, fromDt2) && 0 >= ZYPDateUtil.compare(thruDt1, thruDt2)) {
                    cMsg.setMessageInfo(NMAM8113E);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ACCOUNT);
                    errFlg = false;
                }

                if (hasSalesStartDate) {
                    if (fromDt2.compareTo(ZYPDateUtil.getSalesDate()) == 0) {
                        cMsg.A.no(j).effFromDt_A1.setErrorInfo(1, NMAM8663E, new String[] {MSG_START_DT });
                        errFlg = false;
                    }
                }
            }

            if (!IN_ACTIVE.equals(sMsg.A.no(i).dsAcctStsNm_A1.getValue())) {
                cnt++;
            }
        }

        // START 2022/01/12 R.Azucena[QC#59596, ADD]
        if ((ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue()) && !ZYPCommonFunc.hasValue(cMsg.billToCustPk_BI)) ||
                (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH.getValue()) && !ZYPCommonFunc.hasValue(cMsg.shipToCustPk_SH))) {
            if (!hasActiveOrFutureAcct) {
                errFlg = false;
                cMsg.setMessageInfo(NMAM8711E, new String[] {salesDt});
            }
        }
        // END 2022/01/12 R.Azucena[QC#59596, ADD]

        return errFlg;
    }

    private void doProcess_NMAL6720Scrn00_CMN_Submit(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        // Add Start 2018/05/16 QC#26041
        S21UserProfileService userProfileService = getUserProfileService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        // Add End 2018/05/16 QC#26041

        if (!checkInputTabMandatory(cMsg)) {
            return;
        }

        if (!checkMsgNoteTabMandatory(cMsg, sMsg)) {
            return;
        }

        // 2018/02/19 QC#20297(Sol#379) add start
        // 2018/11/13 QC#27954 Del Start
//        if (!checkMsgNoteTabBizArea(cMsg, sMsg)) {
//            return;
//        }
        // 2018/11/13 QC#27954 Del End

        if (!checkShippingTabMandatory(cMsg, sMsg)) {
            return;
        }
        // 2018/12/13 QC#29315 Mod Start
//        if (!checkShippingTabDuplicate(cMsg, sMsg)) {
        if (!checkShippingTab(cMsg, sMsg)) {
        // 2018/12/13 QC#29315 Mod End
            return;
        }
        // 2018/02/19 QC#20297(Sol#379) add end

        if (!checkPrimary(cMsg, sMsg)) {
            return;
        }

        if (!checkContactPrimary(cMsg, sMsg)) {
            return;
        }

        // Mod Start 2018/05/16 QC#26041
        //if (!checkBillToShipToDate(cMsg, sMsg)) {
        if (!checkBillToShipToDate(cMsg, sMsg, functionIds)) {
            // Mod End 2018/05/16 QC#26041
            return;
        }

        if (!checkReactivate(cMsg, sMsg)) {
            return;
        }

        if (!checkAcctRelnDate(cMsg, sMsg)) {
            return;
        }

        if (!checkAcct(cMsg, sMsg)) {
            return;
        }

        if (!checkAddressLength(cMsg, sMsg)) {
            return;
        }

        if (!checkAddress(cMsg)) {
            return;
        }

        if (!checkInputChange(cMsg, sMsg)) {
            return;
        }

        // Add Start 2018/10/09 QC#27598
        if (!openTrnCheck(cMsg, sMsg)) {
            return;
        }
        // Add End 2018/10/09 QC#27598

        if (!saveHeaderInfo(cMsg, sMsg)) {
            return;
        }

        // START 2017/06/29 J.Kim [QC#17670,ADD]
        // Del Start 2018/10/09 QC#27598
        //if (!openTrnCheck(cMsg, sMsg)) {
        //    return;
        //}
        // Del End 2018/10/09 QC#27598
        // END 2017/06/29 J.Kim [QC#17670,ADD]

        // Account Tab
        saveAcctLoc(cMsg, sMsg, cMsg.dsAcctTpCd_H1.getValue());

        // Classifications Tab
        saveDsXrefAcct(cMsg, sMsg);

        // Contacts Tab
        saveDsCtacPsnReln(cMsg, sMsg);

        // Transactions Tab
        saveDsCustTrxRule(cMsg, sMsg);

        // Service Attrb Tab
        saveDsCustNonPrfTech(cMsg, sMsg);
        saveSvcAccsPmit(cMsg, sMsg);

        // Instructions Tab
        saveDsCustSpcl(cMsg, sMsg);

        // Shipping Tab
        // 2018/12/13 QC#29315 Mod Start
//        saveDsCustShpg(cMsg, sMsg);
        saveDsCustShpgAndDsAcctCarr(cMsg, sMsg);
        // 2018/12/13 QC#29315 Mod End

        // Mod Start 2018/05/16 QC#26041
        //switchProspectToCustomer(cMsg, sMsg);
        switchProspectToCustomer(cMsg, sMsg, functionIds);
        // Mod End 2018/05/16 QC#26041
    }

    private boolean checkPrimary(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        // 2019/05/08 QC#50101 Add Start
        NMAL6720CommonLogic.setOffPrinCustFlg(cMsg, glblCmpyCd);
        // 2019/05/08 QC#50101 Add End

        // Location Primary
        if (!ZYPConstant.FLG_ON_1.equals(cMsg.xxPgFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.prinCustFlg_PR.getValue())) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCmpyCd);
            queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
            queryParam.put("locNum", cMsg.locNum_H1.getValue());

            S21SsmEZDResult ssmResult = getQuery().getPrimaryLoc(queryParam);
            if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() > 0) {
                cMsg.xxPgFlg.setValue(ZYPConstant.FLG_ON_1);
                cMsg.prinCustFlg_PR.setErrorInfo(2, NMAM8261W);
                cMsg.setMessageInfo(NMAM8261W);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.reqTechCd_SA)) {
            if (!isExistTech(cMsg.reqTechCd_SA.getValue())) {
                cMsg.reqTechCd_SA.setErrorInfo(1, NMAM8111E, new String[] {MSG_TECH_MSTR });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SVC_ATTRB);
                return false;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.prfTechCd_SA)) {
            if (!isExistTech(cMsg.prfTechCd_SA.getValue())) {
                cMsg.prfTechCd_SA.setErrorInfo(1, NMAM8111E, new String[] {MSG_TECH_MSTR });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SVC_ATTRB);
                return false;
            }
        }

        return true;
    }

    private boolean checkContactPrimary(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.C, sMsg.C, cMsg.xxPageShowFromNum_C);

        // 2017/08/10 S21_NA#8598 Mod Start
//        boolean errFlg = true;
//        int errIdx = 0;
//        int primaryCount = 0;
//        int cnt = sMsg.C.getValidCount();
//        NMAL6720_CSMsg cSMsg1;
//        NMAL6720_CSMsg cSMsg2;
//        for (int i = 0; i < cnt; i++) {
//            if (ZYPConstant.FLG_ON_Y.equals(sMsg.C.no(i).dsPrimLocFlg_C1.getValue())) {
//                primaryCount++;
//            }
//        }
//
//        if (1 < primaryCount) {
//            for (int i = 0; i < cnt; i++) {
//                cSMsg1 = sMsg.C.no(i);
//                for (int j = 0; j < cnt; j++) {
//                    cSMsg2 = sMsg.C.no(j);
//                    if (i != j && ZYPConstant.CHKBOX_ON_Y.equals(cSMsg1.dsPrimLocFlg_C1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(cSMsg2.dsPrimLocFlg_C1.getValue())) {
//                        String fromDt1 = cSMsg1.effFromDt_C1.getValue();
//                        String thruDt1 = cSMsg1.effThruDt_C1.getValue();
//                        String fromDt2 = cSMsg2.effFromDt_C1.getValue();
//                        String thruDt2 = cSMsg2.effThruDt_C1.getValue();
//
//                        if (fromDt1.isEmpty()) {
//                            fromDt1 = MAX_DT;
//                        }
//                        if (thruDt1.isEmpty()) {
//                            thruDt1 = MAX_DT;
//                        }
//                        if (fromDt2.isEmpty()) {
//                            fromDt2 = MAX_DT;
//                        }
//                        if (thruDt2.isEmpty()) {
//                            thruDt2 = MAX_DT;
//                        }
//                        if (i != j && 0 <= ZYPDateUtil.compare(fromDt1, fromDt2) && 0 >= ZYPDateUtil.compare(fromDt1, thruDt2)) {
//                            cSMsg1.dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
//                            cSMsg2.dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
//                            errFlg = false;
//                            if (errIdx == 0) {
//                                errIdx = i;
//                            }
//                        } else if (i != j && 0 <= ZYPDateUtil.compare(thruDt1, fromDt2) && 0 >= ZYPDateUtil.compare(thruDt1, thruDt2)) {
//                            cSMsg1.dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
//                            cSMsg2.dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
//                            errFlg = false;
//                            if (errIdx == 0) {
//                                errIdx = i;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        boolean normalEndFlg = true;
        int errIdx = 0;
        int cnt = sMsg.C.getValidCount();

        // Duplicate Check
        List<BigDecimal> dsCtacPsnRelnPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cnt; i++) {
            if (!sMsg.C.no(i).dsPrimLocFlg_C1.getValue().equals(sMsg.C.no(i).dsPrimLocFlg_CB.getValue())) {
                dsCtacPsnRelnPkList.add(sMsg.C.no(i).dsCtacPsnRelnPk_C1.getValue());
                if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.C.no(i).dsPrimLocFlg_C1.getValue())) {
                    String chkEffFromDt = sMsg.C.no(i).effFromDt_C1.getValue();
                    String chkEffThruDt = "";
                    if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1)) {
                        chkEffThruDt = sMsg.C.no(i).effThruDt_C1.getValue();
                    } else {
                        chkEffThruDt = MAX_DT;
                    }

                    for (int j = 0; j < cnt; j++) {
                        if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.C.no(j).dsPrimLocFlg_C1.getValue())
                                && sMsg.C.no(i).ctacPsnPk_C1.getValue().compareTo(sMsg.C.no(j).ctacPsnPk_C1.getValue()) != 0
                                // 2017/08/04 S21_NA#8598 Add
                                && sMsg.C.no(i).ctacTpCd_C1.getValue().equals(sMsg.C.no(j).ctacTpCd_C1.getValue())) {
                            String effFromDt = sMsg.C.no(j).effFromDt_C1.getValue();
                            String effThruDt = "";
                            if (ZYPCommonFunc.hasValue(sMsg.C.no(j).effThruDt_C1)) {
                                effThruDt = sMsg.C.no(j).effThruDt_C1.getValue();
                            } else {
                                effThruDt = MAX_DT;
                            }
                            if (ZYPDateUtil.compare(effFromDt, chkEffFromDt) <= 0
                                    && ZYPDateUtil.compare(effThruDt, chkEffFromDt) >= 0) {
                                normalEndFlg = false;
                                if (errIdx == 0) {
                                  errIdx = i;
                                }
                                sMsg.C.no(i).dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
                            }
                            if (ZYPDateUtil.compare(effFromDt, chkEffThruDt) <= 0
                                    && ZYPDateUtil.compare(effThruDt, chkEffThruDt) >= 0) {
                                normalEndFlg = false;
                                if (errIdx == 0) {
                                    errIdx = i;
                                }
                                sMsg.C.no(j).dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
                            }
                        }
                    }
                }
            }
        }
        // Duplicate Check for DB.
        if (normalEndFlg) {
            for (int i = 0; i < cnt; i++) {
                NMAL6720_CSMsg csMsg = sMsg.C.no(i);
                if (!csMsg.dsPrimLocFlg_C1.getValue().equals(csMsg.dsPrimLocFlg_CB.getValue())) {
                    if (ZYPConstant.CHKBOX_ON_Y.equals(csMsg.dsPrimLocFlg_C1.getValue())) {
                        String chkEffFromDt = sMsg.C.no(i).effFromDt_C1.getValue();
                        String chkEffThruDt = "";
                        if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1)) {
                            chkEffThruDt = sMsg.C.no(i).effThruDt_C1.getValue();
                        } else {
                            chkEffThruDt = MAX_DT;
                        }
                        S21SsmEZDResult ssmResult = NMAL6720Query.getInstance().getPrimaryContactForDuplicateCheck(cMsg.locNum_H1.getValue(), csMsg.ctacPsnPk_C1.getValue()
                                , csMsg.ctacTpCd_C1.getValue(), getGlobalCompanyCode(), chkEffFromDt, chkEffThruDt);
                        if (!ssmResult.isCodeNormal()) {
                            continue;
                        }
                        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                        for (Map<String, Object> resultMap : resultList) {
                            if (!dsCtacPsnRelnPkList.contains(resultMap.get("DS_CTAC_PSN_RELN_PK"))) {
                                normalEndFlg = false;
                                if (errIdx == 0) {
                                    errIdx = i;
                                }
                                csMsg.dsPrimLocFlg_C1.setErrorInfo(1, NMAM8343E);
                            }
                        }
                        
                    }
                }
            }
        }
        // 2017/08/10 S21_NA#8598 Mod End
        if (!normalEndFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_CONTACTS);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_CONTACTS, errIdx);
        }

        return normalEndFlg;
    }

    private void savePrinCustByAccountTab(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, String dsAcctTpCd) {

        if (!NMAL6720CommonLogic.checkCustomerAccount(dsAcctTpCd)) {
            return;
        }

        PRIN_CUSTTMsg prinCustTMsg = new PRIN_CUSTTMsg();

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.prinCustFlg_PR.getValue())) {
            prinCustTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

            if (ZYPCommonFunc.hasValue(cMsg.prinCustPk_PR)) {
                prinCustTMsg.prinCustPk.setValue(cMsg.prinCustPk_PR.getValue());
            } else {
                S21SsmEZDResult ssmResult = getQuery().getPrinCust(queryParam);
                if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() == 1) {
                    Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
                    ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustPk, (BigDecimal) result.get("PRIN_CUST_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsg.prinCustPk_PR, (BigDecimal) result.get("PRIN_CUST_PK"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_PR, (String) result.get("EZUPTIME"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_PR, (String) result.get("EZUPTIMEZONE"));
                } else {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PRIN_CUST });
                    return;
                }
            }
        } else {

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.prinCustFlg_PR.getValue())) {
                if (!clearPrimLocPrinCust(cMsg, sMsg, this.getGlobalCompanyCode())) {
                    return;
                }
            }
            return;
        }

        prinCustTMsg = (PRIN_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prinCustTMsg);
        if (prinCustTMsg == null || !RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PRIN_CUST });
            return;
        }

        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_PR.getValue(), cMsg.ezUpTimeZone_PR.getValue(), prinCustTMsg.ezUpTime.getValue(), prinCustTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(prinCustTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.stCd, cMsg.stCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.postCd, cMsg.postCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.provNm, cMsg.provNm_H1.getValue());

        ZYPEZDItemValueSetter.setValue(prinCustTMsg.locNum, cMsg.locNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.telNum, cMsg.telNum_H1.getValue());

        ZYPEZDItemValueSetter.setValue(prinCustTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.rgtnStsCd, cMsg.rgtnStsCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.geoCd, cMsg.geoCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.effFromDt, cMsg.effFromDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.effThruDt, cMsg.effThruDt_H1.getValue());

        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.geoCd, cMsg.geoCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.glnNum, cMsg.glnNum_H1.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        }

        S21FastTBLAccessor.update(prinCustTMsg);
        if (!RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_PRIN_CUST });
            return;
        }

    }

    private void saveBillToCustByAccountTab(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(billToCustTMsg.glblCmpyCd, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.billToCustPk_BI)) {

            ZYPEZDItemValueSetter.setValue(billToCustTMsg.billToCustPk, cMsg.billToCustPk_BI.getValue());

            billToCustTMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(billToCustTMsg);
            if (billToCustTMsg == null || !RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_BILL_TO_CUST });
                return;
            }

            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_BI.getValue(), cMsg.ezUpTimeZone_BI.getValue(), billToCustTMsg.ezUpTime.getValue(), billToCustTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(billToCustTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.stCd, cMsg.stCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.postCd, cMsg.postCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.provNm, cMsg.provNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.telNum, cMsg.telNum_H1.getValue());

            String fromDate = cMsg.effFromDt_BI.getValue();
            String thruDate = MAX_DT;

            if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.effFromDt, fromDate);
            }
            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                thruDate = cMsg.effThruDt_BI.getValue();
            }

            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(sMsg.rgtnStsCd_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.rgtnStsCd, RGTN_STS.TERMINATED);

                    setEffectiveDateForTermination(billToCustTMsg.effFromDt, billToCustTMsg.effThruDt);
                } else {
                    if (MAX_DT.equals(thruDate)) {
                        thruDate = null;
                    }
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.effThruDt, thruDate);
                }
                // 2023/11/06 QC#61924 Add Start
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                billToCustTMsg.deacNewTrxDt.clear();
                // 2023/11/06 QC#61924 Add End
            } else {

                if (!NMAL6720CommonLogic.setRgtnCdBillTo(cMsg, billToCustTMsg.rgtnStsCd)) {
                    return;
                }

                if (MAX_DT.equals(thruDate)) {
                    thruDate = null;
                }
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.effThruDt, thruDate);
            }

            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.geoCd, cMsg.geoCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.glnNum, cMsg.glnNum_H1.getValue());

            if (isAccountChanged(cMsg)) {
                SELL_TO_CUSTTMsg acctTMsg = this.getAccountCoAInfo(cMsg);
                if (acctTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaChCd, acctTMsg.coaChCd);
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAfflCd, acctTMsg.coaAfflCd);
                }
            }

            ZYPEZDItemValueSetter.setValue(billToCustTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.primUsgFlg_BI.getValue())) {
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.primUsgFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.primUsgFlg, ZYPConstant.FLG_OFF_N);
            }

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
            }

            // 2023/11/06 QC#61924 Add Start
            if (!hasDeactivateForBillingLocationUpdate(billToCustTMsg, cMsg, sMsg, glblCmpyCd)) {
                cMsg.xxChkBox_H1.setErrorInfo(1, NMAM8725E);
                cMsg.setMessageInfo(NMAM8725E);
                return;
            }
            // 2023/11/06 QC#61924 Add End

            S21FastTBLAccessor.update(billToCustTMsg);
            if (!RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {"BILL_TO_CUST" });
                return;
            }

        } else {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {

                String defRemId = getDefaultRemId(cMsg.stCd_P1.getValue());
                if (!ZYPCommonFunc.hasValue(defRemId)) {
                    cMsg.setMessageInfo(NMAL6720Constant.NMAM8490E);
                    return;
                }

                BigDecimal billToCustPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.BILL_TO_CUST_SQ);
                ZYPEZDItemValueSetter.setValue(sMsg.billToCustPk_BI, billToCustPk);
                ZYPEZDItemValueSetter.setValue(cMsg.billToCustPk_BI, billToCustPk);

                ZYPEZDItemValueSetter.setValue(billToCustTMsg.billToCustPk, billToCustPk);

                String billToCustCd = ZYPExtnNumbering.getUniqueID(glblCmpyCd, "BILL_TO_CUST_CD");
                ZYPEZDItemValueSetter.setValue(sMsg.billToCustCd_BI, billToCustCd);
                ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd_BI, billToCustCd);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.billToCustCd, billToCustCd);

                ZYPEZDItemValueSetter.setValue(billToCustTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.stCd, cMsg.stCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.postCd, cMsg.postCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.provNm, cMsg.provNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.locNm, getLocNm(cMsg));
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.telNum, cMsg.telNum_H1.getValue());
                // QC#6918
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.remId, defRemId);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.cmpyPk, cMsg.cmpyPk.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.locGrpCd, LOC_GRP.CUSTOMER);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.bizRelnTpCd, cMsg.bizRelnTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.prinCustPk, cMsg.prinCustPk.getValue());

                ZYPEZDItemValueSetter.setValue(billToCustTMsg.flPlnCmpyFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.openRmaFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.arStmtFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.asnReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.printSccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.printUccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.printPltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.mixPltUccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.nonAsnUccLbFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.mixPltAllwFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.rcvAckReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                // 2023/11/06 QC#61924 Add Start
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                if (!hasDeactivateForBillingLocationUpdate(billToCustTMsg, cMsg, sMsg, glblCmpyCd)) {
                    cMsg.xxChkBox_H1.setErrorInfo(1, NMAM8725E);
                    cMsg.setMessageInfo(NMAM8725E);
                    return;
                }

                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                    billToCustTMsg.deacNewTrxDt.clear();
                }
                // 2023/11/06 QC#61924 Add End

                String fromDate = cMsg.effFromDt_BI.getValue();
                String thruDate = MAX_DT;

                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                    thruDate = cMsg.effThruDt_BI.getValue();
                }
                if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.effFromDt, fromDate);
                }

                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.effThruDt, thruDate);
                }

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                    if (!NMAL6720CommonLogic.setRgtnCdBillTo(cMsg, billToCustTMsg.rgtnStsCd)) {
                        return;
                    }
                }

                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.geoCd, cMsg.geoCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.glnNum, cMsg.glnNum_H1.getValue());

                NMAL6720CommonLogic.setCoaDefInfo(glblCmpyCd, cMsg, billToCustTMsg); // 2018/03/26 S21_NA#23935 Add
                
                if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue())) {
                    SELL_TO_CUSTTMsg acctTMsg = this.getAccountCoAInfo(cMsg);
                    // START 2022/01/12 R.Azucena[QC#59596, ADD]
                    if (acctTMsg == null) {
                        acctTMsg = getFirstAccountCoAInfo(cMsg);
                    }
                    // END 2022/01/12 R.Azucena[QC#59596, ADD]

                    if (acctTMsg != null) {
                        // 2018/03/26 S21_NA#23935 Add Start
                        billToCustTMsg.coaChCd.clear();
                        billToCustTMsg.coaAfflCd.clear();
                        // 2018/03/26 S21_NA#23935 Add End
                        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaChCd, acctTMsg.coaChCd);
                        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAfflCd, acctTMsg.coaAfflCd);
                    }
                } else {

                    DS_ACCT_PROSTMsg acctTMsg = this.getProspectCoAInfo(cMsg);
                    if (acctTMsg != null) {
                        // 2018/03/26 S21_NA#23935 Add Start
                        billToCustTMsg.coaChCd.clear();
                        billToCustTMsg.coaAfflCd.clear();
                        // 2018/03/26 S21_NA#23935 Add End
                        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaChCd, acctTMsg.coaChCd);
                        ZYPEZDItemValueSetter.setValue(billToCustTMsg.coaAfflCd, acctTMsg.coaAfflCd);
                    }
                }

                ZYPEZDItemValueSetter.setValue(billToCustTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1.getValue());
                // 2017/11/27 QC#20828 Mod Start
//                ZYPEZDItemValueSetter.setValue(billToCustTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
                if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.sellToCustCd, NMAL6720CommonLogic.getMergeToAcctCd(cMsg));
                } else {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
                }
                // 2017/11/27 QC#20828 Mod End
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.lateFeeFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dunFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsBillTgtrFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsAcctNm, cMsg.dsAcctNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.primUsgFlg_BI.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.primUsgFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.primUsgFlg, ZYPConstant.FLG_OFF_N);
                }

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(billToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                }

                // Mod Start 2019/02/19 QC#30293
//                // START 2018/01/16 [QC#23882, ADD]
//                ZYPEZDItemValueSetter.setValue(billToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
//                // END   2018/01/16 [QC#23882, ADD]
                NMAL6720CommonLogic.setTemplateForFinancials(glblCmpyCd, billToCustTMsg);
                // Mod End 2019/02/19 QC#30293
                
                S21FastTBLAccessor.insert(billToCustTMsg);
                if (!RTNCD_NORMAL.equals(billToCustTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_BILL_TO_CUST });
                    return;
                }
            }
        }
        updateRelnBillToCustPrimUsgFlg(cMsg, glblCmpyCd);
    }

    private void updateRelnBillToCustPrimUsgFlg(NMAL6720CMsg cMsg, String glblCmpyCd) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.primUsgFlg_BI.getValue())) {

            Map<String, Object> queryParam1 = new HashMap<String, Object>();
            queryParam1.put("glblCmpyCd", glblCmpyCd);
            queryParam1.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
            queryParam1.put("billToCustCd", cMsg.billToCustCd_BI.getValue());

            S21SsmEZDResult ssmResult = getQuery().getPrimaryBillTo(queryParam1);
            if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() > 0) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> result : resultList) {
                    BigDecimal billToCustPk = (BigDecimal) result.get("BILL_TO_CUST_PK");
                    BILL_TO_CUSTTMsg updateTMsg = new BILL_TO_CUSTTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.billToCustPk, billToCustPk);
                    updateTMsg = (BILL_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (!RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_BILL_TO_CUST });
                        return;
                    }

                    ZYPEZDItemValueSetter.setValue(updateTMsg.primUsgFlg, ZYPConstant.FLG_OFF_N);

                    S21FastTBLAccessor.update(updateTMsg);
                    if (!RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_BILL_TO_CUST });
                        return;
                    }
                }
            }
        }
    }

    private void saveShipToCustByAccountTab(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();
        String salesDate = ZYPDateUtil.getSalesDate();
        String fromDate = cMsg.effFromDt_SH.getValue();
        String thruDate = MAX_DT;
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
            thruDate = cMsg.effThruDt_SH.getValue();
        }

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.glblCmpyCd, glblCmpyCd);

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustPk_SH)) {
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.shipToCustPk, cMsg.shipToCustPk_SH.getValue());

            shipToCustTMsg = (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(shipToCustTMsg);
            if (shipToCustTMsg == null || !RTNCD_NORMAL.equals(shipToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_SHIP_TO_CUST });
                return;
            }

            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_SH.getValue(), cMsg.ezUpTimeZone_SH.getValue(), shipToCustTMsg.ezUpTime.getValue(), shipToCustTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NZZM0003E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.stCd, cMsg.stCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.postCd, cMsg.postCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.provNm, cMsg.provNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.telNum, cMsg.telNum_H1.getValue());

            String effThruDtShipTo = "";
            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
                effThruDtShipTo = cMsg.effThruDt_SH.getValue();
            } else {
                effThruDtShipTo = MAX_DT;
            }
            if (ZYPDateUtil.compare(salesDate, effThruDtShipTo) <= 0) {
                if (!cMsg.dplStsCd_P1.getValue().equals(sMsg.dplStsCd_P1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplStsCd, cMsg.dplStsCd_P1.getValue());
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplModDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplModByLoginUsrId, userId);
                }

            } else if (!cMsg.effThruDt_SH.getValue().equals(sMsg.effThruDt_SH.getValue())) {
                shipToCustTMsg.dplStsCd.clear();
                shipToCustTMsg.dplRsnTxt.clear();
                shipToCustTMsg.dplRspDtTmTs.clear();
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplModDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplModByLoginUsrId, getContextUserInfo().getUserId());
            }

            if (ZYPCommonFunc.hasValue(cMsg.effFromDt_SH)) {
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.effFromDt, fromDate);
            }

            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(sMsg.rgtnStsCd_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
                    setEffectiveDateForTermination(shipToCustTMsg.effFromDt, shipToCustTMsg.effThruDt);
                } else {
                    if (MAX_DT.equals(thruDate)) {
                        thruDate = null;
                    }
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.effThruDt, thruDate);
                }
                // 2023/11/06 QC#61924 Add Start
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                shipToCustTMsg.deacNewTrxDt.clear();
                // 2023/11/06 QC#61924 Add End
            } else {

                if (!NMAL6720CommonLogic.setRgtnCdShipTo(cMsg, shipToCustTMsg.rgtnStsCd)) {
                    return;
                }

                if (MAX_DT.equals(thruDate)) {
                    thruDate = null;
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.effThruDt, thruDate);

            }

            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.geoCd, cMsg.geoCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.glnNum, cMsg.glnNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.relnBillToCustCd, cMsg.billToCustCd_SH.getValue());

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.primUsgFlg_SH.getValue())) {
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.primUsgFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.primUsgFlg, ZYPConstant.FLG_OFF_N);
            }

            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1.getValue());
            if (isAccountChanged(cMsg)) {
                SELL_TO_CUSTTMsg acctTMsg = this.getAccountCoAInfo(cMsg);
                // 2018/04/16 S21_NA#24635 add start
                DEF_DPLY_COA_INFOTMsg defTMsg = getShipToDefCoa(cMsg);
                // 2018/04/16 S21_NA#24635 add end
                if (acctTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaChCd, acctTMsg.coaChCd);
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAfflCd, acctTMsg.coaAfflCd);
                    // 2018/04/16 S21_NA#24635 add start
                    if (defTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaCmpyCd, defTMsg.coaCmpyCd);
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaBrCd, defTMsg.coaBrCd);
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaCcCd, defTMsg.coaCcCd);
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAcctCd, defTMsg.coaAcctCd);
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaProdCd, defTMsg.coaProdCd);
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaProjCd, defTMsg.coaProjCd);
                        ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaExtnCd, defTMsg.coaExtnCd);
                    }
                    // 2018/04/16 S21_NA#24635 add end
                }
            }

            // 2020/03/02 QC#55673 Add Start
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.addlLocNm, cMsg.addlLocNm.getValue());
            // 2020/03/02 QC#55673 Add End

            // 2023/11/06 QC#61924 Add Start
            if (!hasDeactivateForShippingLocationUpdate(shipToCustTMsg, cMsg, sMsg, glblCmpyCd)) {
                cMsg.xxChkBox_H1.setErrorInfo(1, NMAM8725E);
                cMsg.setMessageInfo(NMAM8725E);
                return;
            }
            // 2023/11/06 QC#61924 Add End

            S21FastTBLAccessor.update(shipToCustTMsg);
            if (!RTNCD_NORMAL.equals(shipToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SHIP_TO_CUST });
                return;
            }

        } else {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH.getValue())) {

                BigDecimal shipToCustPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SHIP_TO_CUST_SQ);
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCustPk_SH, shipToCustPk);
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCustPk_SH, shipToCustPk);

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.shipToCustPk, shipToCustPk);

                String shipToCustCd = ZYPExtnNumbering.getUniqueID(glblCmpyCd, "SHIP_TO_CUST_CD");
                sMsg.shipToCustCd_SH.setValue(shipToCustCd);
                cMsg.shipToCustCd_SH.setValue(shipToCustCd);

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.shipToCustCd, shipToCustCd);

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.stCd, cMsg.stCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.postCd, cMsg.postCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.provNm, cMsg.provNm_H1.getValue());

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.locNm, getLocNm(cMsg));
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.telNum, cMsg.telNum_H1.getValue());

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.cmpyPk, cMsg.cmpyPk.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.prinCustPk, cMsg.prinCustPk.getValue());
                // 2017/11/27 QC#20828 Mod Start
//                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
                if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.sellToCustCd, NMAL6720CommonLogic.getMergeToAcctCd(cMsg));
                } else {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
                }
                // 2017/11/27 QC#20828 Mod End

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.locRoleTpCd, LOC_ROLE_TP.SHIP_TO);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.locGrpCd, LOC_GRP.CUSTOMER);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.cnsgnFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.oemFlg, ZYPConstant.FLG_OFF_N);

                PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
                PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTMsg = new PROS_PTY_LOC_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());

                if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue())) {
                    ptyLocWrkTMsg = (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKey(ptyLocWrkTMsg);
                    if (ptyLocWrkTMsg == null) {
                        cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PTY_LOC_WRK });
                        return;
                    }
                } else {
                    EZDMsg.copy(ptyLocWrkTMsg, null, prosPtyLocWrkTMsg, null);
                    prosPtyLocWrkTMsg = (PROS_PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKey(prosPtyLocWrkTMsg);
                    if (prosPtyLocWrkTMsg == null) {
                        cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PROS_PTY_LOC_WRK });
                        return;
                    }
                    EZDMsg.copy(prosPtyLocWrkTMsg, null, ptyLocWrkTMsg, null);
                }

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplStsCd, ptyLocWrkTMsg.dplStsCd);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplRsnTxt, ptyLocWrkTMsg.dplRsnTxt);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplRspDtTmTs, ptyLocWrkTMsg.dplRspDtTmTs);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.embgoFlg, ptyLocWrkTMsg.embgoFlg);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplModDtTmTs, ptyLocWrkTMsg.dplModDtTmTs);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dplModByLoginUsrId, ptyLocWrkTMsg.dplModByLoginUsrId);

                if (ZYPCommonFunc.hasValue(cMsg.effFromDt_SH)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.effFromDt, fromDate);
                }

                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.effThruDt, thruDate);
                }

                if (ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                    if (!NMAL6720CommonLogic.setRgtnCdShipTo(cMsg, shipToCustTMsg.rgtnStsCd)) {
                        return;
                    }
                }
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.geoCd, cMsg.geoCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.glnNum, cMsg.glnNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.relnBillToCustCd, cMsg.billToCustCd_SH.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1.getValue());

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsTaxExemFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
                }

                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.primUsgFlg_SH.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.primUsgFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.primUsgFlg, ZYPConstant.FLG_OFF_N);
                }

                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsAcctNm, cMsg.dsAcctNm_H1.getValue());
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());

                // 2020/03/02 QC#55673 Add Start
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.addlLocNm, cMsg.addlLocNm.getValue());
                // 2020/03/02 QC#55673 Add End

                // 2019/04/05 QC#31030 Del Start
                //if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue())) {
                //  SELL_TO_CUSTTMsg acctTMsg = this.getAccountCoAInfo(cMsg);
                // 2019/04/05 QC#31030 Del End
                    // 2018/04/16 S21_NA#24635 add start
                    DEF_DPLY_COA_INFOTMsg defTMsg = getShipToDefCoa(cMsg);
                    // 2018/04/16 S21_NA#24635 add end
                // 2019/04/05 QC#31030 Del Start
                //  if (acctTMsg != null) {
                //      ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaChCd, acctTMsg.coaChCd);
                //      ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAfflCd, acctTMsg.coaAfflCd);
                // 2019/04/05 QC#31030 Del End
                        // 2018/04/16 S21_NA#24635 add start
                        if (defTMsg != null) {
                            // 2019/04/05 QC#31030 Add Start
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaChCd, defTMsg.coaChCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAfflCd, defTMsg.coaAfflCd);
                            // 2019/04/05 QC#31030 Add End
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaCmpyCd, defTMsg.coaCmpyCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaBrCd, defTMsg.coaBrCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaCcCd, defTMsg.coaCcCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAcctCd, defTMsg.coaAcctCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaProdCd, defTMsg.coaProdCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaProjCd, defTMsg.coaProjCd);
                            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaExtnCd, defTMsg.coaExtnCd);
                        }
                        // 2018/04/16 S21_NA#24635 add end
                // 2019/04/05 QC#31030 Del Start
                //    }
                //} else {
                //
                //  DS_ACCT_PROSTMsg acctTMsg = this.getProspectCoAInfo(cMsg);
                //  // 2018/04/16 S21_NA#24635 add start
                //  DEF_DPLY_COA_INFOTMsg defTMsg = getShipToDefCoa(cMsg);
                //  // 2018/04/16 S21_NA#24635 add end
                //  if (acctTMsg != null) {
                //      ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaChCd, acctTMsg.coaChCd);
                //      ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAfflCd, acctTMsg.coaAfflCd);
                //      // 2018/04/16 S21_NA#24635 add start
                //      if (defTMsg != null) {
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaCmpyCd, defTMsg.coaCmpyCd);
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaBrCd, defTMsg.coaBrCd);
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaCcCd, defTMsg.coaCcCd);
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaAcctCd, defTMsg.coaAcctCd);
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaProdCd, defTMsg.coaProdCd);
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaProjCd, defTMsg.coaProjCd);
                //          ZYPEZDItemValueSetter.setValue(shipToCustTMsg.coaExtnCd, defTMsg.coaExtnCd);
                //      }
                //      // 2018/04/16 S21_NA#24635 add end
                //  }
                //}
                // 2019/04/05 QC#31030 Del End

                // 2023/11/06 QC#61924 Add Start
                ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                if (!hasDeactivateForShippingLocationUpdate(shipToCustTMsg, cMsg, sMsg, glblCmpyCd)) {
                    cMsg.xxChkBox_H1.setErrorInfo(1, NMAM8725E);
                    cMsg.setMessageInfo(NMAM8725E);
                    return;
                }

                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
                    shipToCustTMsg.deacNewTrxDt.clear();
                }
                // 2023/11/06 QC#61924 Add End

                S21FastTBLAccessor.insert(shipToCustTMsg);
                if (!RTNCD_NORMAL.equals(shipToCustTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_SHIP_TO_CUST });
                    return;
                }
            }
        }

        updateRelnShipToCustPrimUsgFlg(cMsg, glblCmpyCd);
    }

    private void updateRelnShipToCustPrimUsgFlg(NMAL6720CMsg cMsg, String glblCmpyCd) {
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH.getValue()) && ZYPConstant.FLG_ON_Y.equals(cMsg.primUsgFlg_SH.getValue())) {

            Map<String, Object> queryParam1 = new HashMap<String, Object>();
            queryParam1.put("glblCmpyCd", glblCmpyCd);
            queryParam1.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
            queryParam1.put("shipToCustCd", cMsg.shipToCustCd_SH.getValue());

            S21SsmEZDResult ssmResult = getQuery().getPrimaryShipTo(queryParam1);
            if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() > 0) {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> result : resultList) {
                    BigDecimal shipToCustPk = (BigDecimal) result.get("SHIP_TO_CUST_PK");
                    SHIP_TO_CUSTTMsg updateTMsg = new SHIP_TO_CUSTTMsg();
                    ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(updateTMsg.shipToCustPk, shipToCustPk);
                    updateTMsg = (SHIP_TO_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(updateTMsg);
                    if (!RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_SHIP_TO_CUST });
                        return;
                    }

                    ZYPEZDItemValueSetter.setValue(updateTMsg.primUsgFlg, ZYPConstant.FLG_OFF_N);

                    S21FastTBLAccessor.update(updateTMsg);
                    if (!RTNCD_NORMAL.equals(updateTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SHIP_TO_CUST });
                        return;
                    }
                }
            }
        }
    }

    private void insertLocUsg(NMAL6720CMsg cMsg, String locRoleTp) {

        // 2016/12/16 QC#16659 ADD START
        LOC_USGTMsg tMsg = new LOC_USGTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        tMsg.setConditionValue("locGrpCd01", LOC_GRP.CUSTOMER);
        tMsg.setConditionValue("locRoleTpCd01", locRoleTp);
        tMsg.setConditionValue("ptyLocPk01", cMsg.ptyLocPk.getValue());

        LOC_USGTMsgArray resMsg = (LOC_USGTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (resMsg.getValidCount() != 0) {
            return;
        }
        // 2016/12/16 QC#16659 ADD E N D

        LOC_USGTMsg locUsgTMsg = new LOC_USGTMsg();
        ZYPEZDItemValueSetter.setValue(locUsgTMsg.glblCmpyCd, getGlobalCompanyCode());
        BigDecimal locUsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.LOC_USG_SQ);

        ZYPEZDItemValueSetter.setValue(locUsgTMsg.locUsgPk, locUsgPk);
        ZYPEZDItemValueSetter.setValue(locUsgTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
        ZYPEZDItemValueSetter.setValue(locUsgTMsg.locGrpCd, LOC_GRP.CUSTOMER);

        ZYPEZDItemValueSetter.setValue(locUsgTMsg.locRoleTpCd, locRoleTp);

        S21FastTBLAccessor.insert(locUsgTMsg);
        if (!RTNCD_NORMAL.equals(locUsgTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_LOC_USG });
            return;
        }
    }

    private boolean isAddressChanged(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (!isEquals(cMsg.ctryCd_P1.getValue(), sMsg.ctryCd_P1.getValue()) || !isEquals(cMsg.firstLineAddr_H1.getValue(), sMsg.firstLineAddr_H1.getValue()) || !isEquals(cMsg.scdLineAddr_H1.getValue(), sMsg.scdLineAddr_H1.getValue())
                || !isEquals(cMsg.thirdLineAddr_H1.getValue(), sMsg.thirdLineAddr_H1.getValue()) || !isEquals(cMsg.frthLineAddr_H1.getValue(), sMsg.frthLineAddr_H1.getValue())
                || !isEquals(cMsg.ctyAddr_H1.getValue(), sMsg.ctyAddr_H1.getValue()) || !isEquals(cMsg.stCd_P1.getValue(), sMsg.stCd_P1.getValue()) || !isEquals(cMsg.postCd_H1.getValue(), sMsg.postCd_H1.getValue())
                || !isEquals(cMsg.cntyNm_H1.getValue(), sMsg.cntyNm_H1.getValue()) || !isEquals(cMsg.provNm_H1.getValue(), sMsg.provNm_H1.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isUpdateShipTo(String effFromDt, String effThruDt) {
        if (!ZYPCommonFunc.hasValue(effFromDt)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(effThruDt)) {
            effThruDt = MAX_DT;
        }
        String salesDt = ZYPDateUtil.getSalesDate();

        if (ZYPDateUtil.compare(salesDt, effFromDt) >= 0 && ZYPDateUtil.compare(salesDt, effThruDt) <= 0) {
            return true;
        }
        return false;
    }

    private boolean saveWareHouseAddress(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("locNum", cMsg.locNum_H1.getValue());
        queryParam.put("locRoleTp_WareHouse", LOC_ROLE_TP.CANON_WAREHOUSE);
        queryParam.put("locRoleTp_Technician", LOC_ROLE_TP.TECHNICIAN);

        S21SsmEZDResult ssmResult = getQuery().getShipToCustCd(queryParam);
        if (!ssmResult.isCodeNormal()) {
            return true;
        }

        List<Map<String, Object>> shipToCustList = (List<Map<String, Object>>) ssmResult.getResultObject();
        Map<String, Object> shipToCustMap = shipToCustList.get(0);

        // 2018/10/26 QC#28841 Add Start
        List<SHIP_TO_CUSTTMsg> updShipToCustList = new ArrayList<SHIP_TO_CUSTTMsg>();
        List<PTY_LOC_WRKTMsg> updPtyLocWrkList = new ArrayList<PTY_LOC_WRKTMsg>();
        String shipToCustCd = null;
        BigDecimal ptyLocPk = null;
        String effFfomDt = null;
        String effThruDt = null;
        String constValEndHazmat = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_END_TECH_HAZMAT_CD, getGlobalCompanyCode());
        String shipToLocNum = (String) shipToCustMap.get("LOC_NUM_PL");
        String salesDate = ZYPDateUtil.getSalesDate();
        if (ZYPCommonFunc.hasValue(shipToLocNum) && shipToLocNum.contains(constValEndHazmat)) {
            // Check exist Warehouse
            boolean noActiveFlg = true;
            String whCheckCd = shipToLocNum.substring(0, shipToLocNum.indexOf(constValEndHazmat));
            RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
            rtlWhTMsg.setSQLID("002");
            rtlWhTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            rtlWhTMsg.setConditionValue("rtlWhCd01", whCheckCd);

            RTL_WHTMsgArray rtlWhTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(rtlWhTMsg);

            if (rtlWhTMsgArray.length() == 0) {
                cMsg.setMessageInfo(NMAM8693E, new String[] {ERR_MSG_RELATED_TECH_WH });
                return false;
            }

            for (int i = 0; i < rtlWhTMsgArray.getValidCount(); i++) {
                RTL_WHTMsg updRtlWhTMsg = rtlWhTMsgArray.no(i);
                effFfomDt = updRtlWhTMsg.effFromDt.getValue();
                effThruDt = updRtlWhTMsg.effThruDt.getValue();
                if (!ZYPCommonFunc.hasValue(effThruDt)) {
                    effThruDt = MAX_DT;
                }
                if (ZYPDateUtil.compare(salesDate, effFfomDt) >= 0 && ZYPDateUtil.compare(effThruDt, salesDate) >= 0) {
                    noActiveFlg = false;
                }
            }
            if (noActiveFlg) {
                cMsg.setMessageInfo(NMAM8694E, new String[] {ERR_MSG_RELATED_TECH_WH });
                return false;
            }

            shipToCustCd = (String) shipToCustMap.get("SHIP_TO_CUST_CD");
            ptyLocPk = (BigDecimal) shipToCustMap.get("PTY_LOC_PK");
            if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                updShipToCustList = saveShipToCustByWareHouse(cMsg, updShipToCustList, shipToCustCd, shipToCustMap);
            }

            if (ZYPCommonFunc.hasValue(ptyLocPk)) {
                updPtyLocWrkList = savePtyLocWrkByWareHouse(cMsg, updPtyLocWrkList, ptyLocPk, shipToCustMap);
            }
        } else {
            // 2018/10/26 QC#28841 Add End

            // get Warehouse
            Map<String, Object> searchWareHouseParam = new HashMap<String, Object>();
            searchWareHouseParam.put("glblCmpyCd", getGlobalCompanyCode());
            searchWareHouseParam.put("shipToCustCd", (String) shipToCustMap.get("SHIP_TO_CUST_CD"));

            ssmResult = getQuery().getWareHouse(searchWareHouseParam);
            if (!ssmResult.isCodeNormal()) {
                cMsg.setMessageInfo(NMAM8650E, new String[] {ERR_MSG_RELATED_TECH_WH });
                return false;
            }

            List<Map<String, Object>> wareHouseList = (List<Map<String, Object>>) ssmResult.getResultObject();

            // update
            List<WHTMsg> updWhList = new ArrayList<WHTMsg>();
            // 2018/10/26 QC#28841 Mod Start
//            List<SHIP_TO_CUSTTMsg> updShipToCustList = new ArrayList<SHIP_TO_CUSTTMsg>();
//            List<PTY_LOC_WRKTMsg> updPtyLocWrkList = new ArrayList<PTY_LOC_WRKTMsg>();
            // 2018/10/26 QC#28841 Mod End
            List<String> shipToCustCdList = new ArrayList<String>();
            List<BigDecimal> ptyLocPkList = new ArrayList<BigDecimal>();
            // 2018/10/26 QC#28841 Mod Start
//            String shipToCustCd = null;
//            BigDecimal ptyLocPk = null;
            // 2018/10/26 QC#28841 Mod End
            for (Map<String, Object> wareHouseMap : wareHouseList) {
                shipToCustCd = (String) wareHouseMap.get("SHIP_TO_CUST_CD");
                ptyLocPk = (BigDecimal) wareHouseMap.get("PTY_LOC_PK");

                if ("WH_CD".equals((String) wareHouseMap.get("WH"))) {
                    if (!saveRetailWarehouseForShipTo(cMsg, wareHouseMap, shipToCustMap)) {
                        return false;
                    }
                    if (!saveRetailWarehouseForReturnTo(cMsg, wareHouseMap, shipToCustMap)) {
                        return false;
                    }
                }

                updWhList = saveWarehouse(cMsg, updWhList, wareHouseMap, shipToCustMap);

                if (ZYPCommonFunc.hasValue(shipToCustCd)) {
                    if (!isUpdated(shipToCustCd, shipToCustCdList)) {
                        updShipToCustList = saveShipToCustByWareHouse(cMsg, updShipToCustList, shipToCustCd, shipToCustMap);
                        shipToCustCdList.add(shipToCustCd);
                    }
                }

                if (ZYPCommonFunc.hasValue(ptyLocPk)) {
                    if (!isUpdated(ptyLocPk, ptyLocPkList)) {
                        updPtyLocWrkList = savePtyLocWrkByWareHouse(cMsg, updPtyLocWrkList, ptyLocPk, shipToCustMap);
                        ptyLocPkList.add(ptyLocPk);
                    }
                }
            }

            // update WH
            if (!updWhList.isEmpty()) {
                int updCnt = S21FastTBLAccessor.update(updWhList.toArray(new WHTMsg[0]));
                if (updCnt != updWhList.size()) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_WH });
                    return false;
                }
            }
        }

        // update SHIP_TO_CUST
        if (!updShipToCustList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updShipToCustList.toArray(new SHIP_TO_CUSTTMsg[0]));
            if (updCnt != updShipToCustList.size()) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SHIP_TO_CUST });
                return false;
            }
        }

        // update PTY_LOC_WRK
        if (!updPtyLocWrkList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updPtyLocWrkList.toArray(new PTY_LOC_WRKTMsg[0]));
            if (updCnt != updPtyLocWrkList.size()) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_PTY_LOC_WRK });
                return false;
            }
        }

        return true;
    }

    private boolean isUpdated(String updColumn, List<String> updColumnList) {
        for (String cmprUpdColumn : updColumnList) {
            if (cmprUpdColumn.equals(updColumn)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUpdated(BigDecimal updColumn, List<BigDecimal> updColumnList) {
        for (BigDecimal cmprUpdColumn : updColumnList) {
            if (cmprUpdColumn.equals(updColumn)) {
                return true;
            }
        }
        return false;
    }

    private boolean saveRetailWarehouseForShipTo(NMAL6720CMsg cMsg, Map<String, Object> wareHouseMap, Map<String, Object> shipToCustMap) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        rtlWhTMsg.setSQLID("003");
        rtlWhTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        rtlWhTMsg.setConditionValue("shipToCustCd01", wareHouseMap.get("RTL_WH_CD"));

        RTL_WHTMsgArray rtlWhTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(rtlWhTMsg);
        if (rtlWhTMsgArray.length() == 0) {
            return true;
        }

        List<RTL_WHTMsg> updRtlWhList = new ArrayList<RTL_WHTMsg>();
        for (int i = 0; i < rtlWhTMsgArray.getValidCount(); i++) {
            RTL_WHTMsg updRtlWhTMsg = rtlWhTMsgArray.no(i);

            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.firstLineAddr, (String) shipToCustMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.scdLineAddr, (String) shipToCustMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.thirdLineAddr, (String) shipToCustMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.frthLineAddr, (String) shipToCustMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.ctyAddr, (String) shipToCustMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.cntyPk, (BigDecimal) shipToCustMap.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.provNm, (String) shipToCustMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.stCd, (String) shipToCustMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.postCd, (String) shipToCustMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.ctryCd, (String) shipToCustMap.get("CTRY_CD"));
            // 2020/03/02 QC#55673 Add Start
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.addlLocNm, (String) shipToCustMap.get("ADDL_LOC_NM"));
            // 2020/03/02 QC#55673 Add End

            updRtlWhList.add(updRtlWhTMsg);
        }

        // update RTL_WH
        if (!updRtlWhList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updRtlWhList.toArray(new RTL_WHTMsg[0]));
            if (updCnt != updRtlWhList.size()) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_RTL_WH });
                return false;
            }
        }
        return true;
    }

    private boolean saveRetailWarehouseForReturnTo(NMAL6720CMsg cMsg, Map<String, Object> wareHouseMap, Map<String, Object> shipToCustMap) {
        RTL_WHTMsg rtlWhTMsg = new RTL_WHTMsg();
        rtlWhTMsg.setSQLID("004");
        rtlWhTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        rtlWhTMsg.setConditionValue("rtrnToCustCd01", wareHouseMap.get("RTL_WH_CD"));

        RTL_WHTMsgArray rtlWhTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByCondition(rtlWhTMsg);
        if (rtlWhTMsgArray.length() == 0) {
            return true;
        }

        List<RTL_WHTMsg> updRtlWhList = new ArrayList<RTL_WHTMsg>();
        for (int i = 0; i < rtlWhTMsgArray.getValidCount(); i++) {
            RTL_WHTMsg updRtlWhTMsg = rtlWhTMsgArray.no(i);

            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToFirstLineAddr, (String) shipToCustMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToScdLineAddr, (String) shipToCustMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToThirdLineAddr, (String) shipToCustMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToFrthLineAddr, (String) shipToCustMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToCtyAddr, (String) shipToCustMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToCntyPk, (BigDecimal) shipToCustMap.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToProvNm, (String) shipToCustMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToStCd, (String) shipToCustMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToPostCd, (String) shipToCustMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToCtryCd, (String) shipToCustMap.get("CTRY_CD"));
            // 2020/03/02 QC#55673 Add Start
            ZYPEZDItemValueSetter.setValue(updRtlWhTMsg.rtrnToAddlLocNm, (String) shipToCustMap.get("ADDL_LOC_NM"));
            // 2020/03/02 QC#55673 Add End

            updRtlWhList.add(updRtlWhTMsg);
        }

        // update RTL_WH
        if (!updRtlWhList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updRtlWhList.toArray(new RTL_WHTMsg[0]));
            if (updCnt != updRtlWhList.size()) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_RTL_WH });
                return false;
            }
        }
        return true;
    }

    private List<WHTMsg> saveWarehouse(NMAL6720CMsg cMsg, List<WHTMsg> updWhList, Map<String, Object> wareHouseMap, Map<String, Object> shipToCustMap) {
        WHTMsg whTMsg = new WHTMsg();
        whTMsg.setSQLID("007");
        whTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        whTMsg.setConditionValue("whCd01", wareHouseMap.get("RTL_WH_CD"));

        WHTMsgArray whTMsgArray = (WHTMsgArray) EZDTBLAccessor.findByCondition(whTMsg);
        if (whTMsgArray.length() == 0) {
            return updWhList;
        }

        for (int i = 0; i < whTMsgArray.getValidCount(); i++) {
            WHTMsg updWhTMsg = whTMsgArray.no(i);

            ZYPEZDItemValueSetter.setValue(updWhTMsg.firstLineAddr, (String) shipToCustMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.scdLineAddr, (String) shipToCustMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.thirdLineAddr, (String) shipToCustMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.frthLineAddr, (String) shipToCustMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.ctyAddr, (String) shipToCustMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.cntyPk, (BigDecimal) shipToCustMap.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.provNm, (String) shipToCustMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.stCd, (String) shipToCustMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.postCd, (String) shipToCustMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(updWhTMsg.ctryCd, (String) shipToCustMap.get("CTRY_CD"));

            updWhList.add(updWhTMsg);
        }

        return updWhList;
    }

    private List<SHIP_TO_CUSTTMsg> saveShipToCustByWareHouse(NMAL6720CMsg cMsg, List<SHIP_TO_CUSTTMsg> updShipToCustList, String shipToCustCd, Map<String, Object> shipToCustMap) {
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);

        SHIP_TO_CUSTTMsgArray updShipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
        if (updShipToCustTMsgArray.length() == 0) {
            return updShipToCustList;
        }

        for (int i = 0; i < updShipToCustTMsgArray.getValidCount(); i++) {
            SHIP_TO_CUSTTMsg updShipToCustTMsg = updShipToCustTMsgArray.no(i);
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.firstLineAddr, (String) shipToCustMap.get("FIRST_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.scdLineAddr, (String) shipToCustMap.get("SCD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.thirdLineAddr, (String) shipToCustMap.get("THIRD_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.frthLineAddr, (String) shipToCustMap.get("FRTH_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.ctyAddr, (String) shipToCustMap.get("CTY_ADDR"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.cntyPk, (BigDecimal) shipToCustMap.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.provNm, (String) shipToCustMap.get("PROV_NM"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.stCd, (String) shipToCustMap.get("ST_CD"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.postCd, (String) shipToCustMap.get("POST_CD"));
            ZYPEZDItemValueSetter.setValue(updShipToCustTMsg.ctryCd, (String) shipToCustMap.get("CTRY_CD"));

            updShipToCustList.add(updShipToCustTMsg);
        }

        return updShipToCustList;
    }

    private List<PTY_LOC_WRKTMsg> savePtyLocWrkByWareHouse(NMAL6720CMsg cMsg, List<PTY_LOC_WRKTMsg> updPtyLocWrkList, BigDecimal ptyLocWrkPk, Map<String, Object> shipToCustMap) {
        PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ptyLocPk, ptyLocWrkPk);

        PTY_LOC_WRKTMsg updPtyLocWrkTMsg = (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKey(ptyLocWrkTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updPtyLocWrkTMsg.getReturnCode())) {
            return updPtyLocWrkList;
        }

        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.firstLineAddr, (String) shipToCustMap.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.scdLineAddr, (String) shipToCustMap.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.thirdLineAddr, (String) shipToCustMap.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.frthLineAddr, (String) shipToCustMap.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.ctyAddr, (String) shipToCustMap.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.cntyPk, (BigDecimal) shipToCustMap.get("CNTY_PK"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.provNm, (String) shipToCustMap.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.stCd, (String) shipToCustMap.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.postCd, (String) shipToCustMap.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(updPtyLocWrkTMsg.ctryCd, (String) shipToCustMap.get("CTRY_CD"));

        updPtyLocWrkList.add(updPtyLocWrkTMsg);

        return updPtyLocWrkList;
    }

    private int saveDsAcctCust(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.prinCustFlg_PR.getValue())) {
            SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
            sellToCustTMsg.setSQLID("001");
            sellToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            sellToCustTMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H1.getValue());

            SELL_TO_CUSTTMsgArray sellToCustTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(sellToCustTMsg);
            if (sellToCustTMsgArray.length() == 0) {
                return 0;
            }
            sellToCustTMsg = sellToCustTMsgArray.no(0);

            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.stCd, cMsg.stCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.postCd, cMsg.postCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.provNm, cMsg.provNm_H1.getValue());

            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.locNum, cMsg.locNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.locNm, getLocNm(cMsg));
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.telNum, cMsg.telNum_H1.getValue());

            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.geoCd, cMsg.geoCd_H1.getValue());

            S21FastTBLAccessor.update(sellToCustTMsg);
            if (!RTNCD_NORMAL.equals(sellToCustTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SELL_TO_CUST });
                return -1;
            }
        } else {

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.prinCustFlg_PR.getValue())) {
                return clearPrimLocDsAdcctCust(cMsg, sMsg, this.getGlobalCompanyCode());
            }
        }
        return 1;
    }

    private int saveDsAcctPros(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.prinCustFlg_PR.getValue()) || !PROS_TO_CUST_CHNG_STS_PENDING.equals(cMsg.prosToCustChngStsCd_H1.getValue())) {
            DS_ACCT_PROSTMsg dsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            dsAcctProsTMsg.setSQLID("001");
            dsAcctProsTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            dsAcctProsTMsg.setConditionValue("dsAcctNum01", cMsg.dsAcctNum_H1.getValue());

            DS_ACCT_PROSTMsgArray dsAcctProsTMsgArray = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(dsAcctProsTMsg);
            if (dsAcctProsTMsgArray.length() == 0) {
                return 0;
            }
            dsAcctProsTMsg = dsAcctProsTMsgArray.no(0);

            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.stCd, cMsg.stCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.postCd, cMsg.postCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.provNm, cMsg.provNm_H1.getValue());

            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.locNum, cMsg.locNum_H1.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.locNm, getLocNm(cMsg));
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.telNum, cMsg.telNum_H1.getValue());

            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
            ZYPEZDItemValueSetter.setValue(dsAcctProsTMsg.geoCd, cMsg.geoCd_H1.getValue());

            S21FastTBLAccessor.update(dsAcctProsTMsg);
            if (!RTNCD_NORMAL.equals(dsAcctProsTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_ACCT_PROS });
                return -1;
            }
        }

        return 1;
    }

    private void saveLocInfo(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (SCRN_EVENT_EDIT.equals(cMsg.xxScrEventNm.getValue())) {
            updatePtyLocWrk(cMsg, sMsg);

        } else {

            String glblCompyCd = getGlobalCompanyCode();

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", glblCompyCd);
            queryParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());
            queryParam.put("dsAcctTpCd", cMsg.dsAcctTpCd_H1.getValue());

            S21SsmEZDResult ssmResult = getQuery().getFirstLocInfo(queryParam);
            if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() == 1) {
                Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
                BigDecimal ptyLocPk = (BigDecimal) result.get("PTY_LOC_PK");
                ZYPEZDItemValueSetter.setValue(cMsg.ptyLocPk, ptyLocPk);
                ZYPEZDItemValueSetter.setValue(sMsg.ptyLocPk, ptyLocPk);
                String locNum = ZYPExtnNumbering.getUniqueID(glblCompyCd, "LOC_NUM");
                ZYPEZDItemValueSetter.setValue(cMsg.locNum_H1, locNum);
                ZYPEZDItemValueSetter.setValue(sMsg.locNum_H1, locNum);

                updatePtyLocWrk(cMsg, sMsg);

            } else {
                insertPtyLocWrk(cMsg, sMsg);

            }
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {
            insertLocUsg(cMsg, LOC_ROLE_TP.BILL_TO);
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH.getValue())) {
            insertLocUsg(cMsg, LOC_ROLE_TP.SHIP_TO);
        }
    }

    private void updatePtyLocWrk(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
        PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTMsg = new PROS_PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());

        if (NMAL6720CommonLogic.checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {

            ptyLocWrkTMsg = (PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ptyLocWrkTMsg);
            if (ptyLocWrkTMsg == null) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PTY_LOC_WRK });
                return;
            }
        } else {
            EZDMsg.copy(ptyLocWrkTMsg, null, prosPtyLocWrkTMsg, null);

            prosPtyLocWrkTMsg = (PROS_PTY_LOC_WRKTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prosPtyLocWrkTMsg);
            if (prosPtyLocWrkTMsg == null) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PROS_PTY_LOC_WRK });
                return;
            }
            EZDMsg.copy(prosPtyLocWrkTMsg, null, ptyLocWrkTMsg, null);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ezUpTime, prosPtyLocWrkTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ezUpTimeZone, prosPtyLocWrkTMsg.ezUpTimeZone);

        }

        if (SCRN_EVENT_EDIT.equals(cMsg.xxScrEventNm.getValue()) && !ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), ptyLocWrkTMsg.ezUpTime.getValue(), ptyLocWrkTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.stCd, cMsg.stCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.postCd, cMsg.postCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.provNm, cMsg.provNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.telNum, cMsg.telNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.locNum, cMsg.locNum_H1.getValue());

        String salesDate = ZYPDateUtil.getSalesDate();

        String rgtnStsCd = ptyLocWrkTMsg.rgtnStsCd.getValue();
        if (!RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd) && ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
            String effThruDt = null;
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.effThruDt, effThruDt);
            isActive = true;
        } else if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(rgtnStsCd) && !ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
            setEffectiveDateForTermination(ptyLocWrkTMsg.effFromDt, ptyLocWrkTMsg.effThruDt);
            isInactive = true;
        } else {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
            }
        }
        setDplInfo(ptyLocWrkTMsg, cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(ptyLocWrkTMsg.effFromDt)) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.effFromDt, salesDate);
        }

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.geoCd, cMsg.geoCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glnNum, cMsg.glnNum_H1.getValue());

        ZYPEZDItemValueSetter.setValue(cMsg.rgtnStsCd_H1, ptyLocWrkTMsg.rgtnStsCd.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_H1, ptyLocWrkTMsg.effThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_H1, ptyLocWrkTMsg.effFromDt.getValue());

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsUltDunsNum, cMsg.dsUltDunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.hqDunsNum, cMsg.hqDunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsPrntDunsNum, cMsg.dsPrntDunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLocEmpNum, cMsg.dsLocEmpNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLocRevAmt, cMsg.dsLocRevAmt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsCustSicDescTxt, cMsg.dsCustSicDescTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsCustSicCd, cMsg.dsCustSicCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLastUpdDunsDt, cMsg.dsLastUpdDunsDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsTradeStyleNm, cMsg.dunsTradeStyleNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsActvCd, cMsg.dunsActvCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsLineAddr, cMsg.dunsLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsBizNm, cMsg.dunsBizNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLastRcvDunsTxt, cMsg.dsLastRcvDunsTxt_H1.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        }

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.reqTechCd, cMsg.reqTechCd_SA.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.prfTechCd, cMsg.prfTechCd_SA.getValue());

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());

        if (!isEquals(cMsg.ctryCd_P1.getValue(), sMsg.ctryCd_P1.getValue()) || !isEquals(cMsg.firstLineAddr_H1.getValue(), sMsg.firstLineAddr_H1.getValue()) || !isEquals(cMsg.scdLineAddr_H1.getValue(), sMsg.scdLineAddr_H1.getValue())
                || !isEquals(cMsg.thirdLineAddr_H1.getValue(), sMsg.thirdLineAddr_H1.getValue()) || !isEquals(cMsg.frthLineAddr_H1.getValue(), sMsg.frthLineAddr_H1.getValue())
                || !isEquals(cMsg.ctyAddr_H1.getValue(), sMsg.ctyAddr_H1.getValue()) || !isEquals(cMsg.stCd_P1.getValue(), sMsg.stCd_P1.getValue()) || !isEquals(cMsg.postCd_H1.getValue(), sMsg.postCd_H1.getValue())
                || !isEquals(cMsg.cntyNm_H1.getValue(), sMsg.cntyNm_H1.getValue())) {

            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsAddrChngTpCd, DUNS_CHNG_TP.UPDATED);
        }
        if (!isEquals(cMsg.telNum_H1.getValue(), sMsg.telNum_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsTelChngTpCd, DUNS_CHNG_TP.UPDATED);

        }

        // Add Start 2019/05/23 QC#50101
        String activeRelatedAcctNum = NMAL6720CommonLogic.getActiveRelatedAcctNum(cMsg, salesDate);

        if (ZYPCommonFunc.hasValue(activeRelatedAcctNum) && !cMsg.dsAcctNum_H1.getValue().equals(activeRelatedAcctNum)) {
            S21SsmEZDResult ssmResult = NMAL6720Query.getInstance().getAcctCmpyPkByAcctNum(getGlobalCompanyCode(), activeRelatedAcctNum);

            if (ssmResult.isCodeNormal()) {
                BigDecimal newCmpyPk = (BigDecimal) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.cmpyPk, newCmpyPk);
            }
        }
        // Add End 2019/05/23 QC#50101

        // 2020/03/02 QC#55673 Add Start
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.addlLocNm, cMsg.addlLocNm.getValue());
        // 2020/03/02 QC#55673 Add End

        if (NMAL6720CommonLogic.checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {
            S21FastTBLAccessor.update(ptyLocWrkTMsg);
            if (!RTNCD_NORMAL.equals(ptyLocWrkTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_PTY_LOC_WRK });
                return;
            }
        } else {
            EZDMsg.copy(ptyLocWrkTMsg, null, prosPtyLocWrkTMsg, null);
            S21FastTBLAccessor.update(prosPtyLocWrkTMsg);
            if (!RTNCD_NORMAL.equals(prosPtyLocWrkTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_PROS_PTY_LOC_WRK });
                return;
            }
        }

    }

    private void insertPtyLocWrk(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        String locNum = ZYPExtnNumbering.getUniqueID(glblCmpyCd, "LOC_NUM");
        ZYPEZDItemValueSetter.setValue(cMsg.locNum_H1, locNum);
        ZYPEZDItemValueSetter.setValue(sMsg.locNum_H1, locNum);
        ZYPEZDItemValueSetter.setValue(cMsg.locNum_P1, locNum);

        BigDecimal ptyLocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PTY_LOC_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(cMsg.ptyLocPk, ptyLocPk);
        ZYPEZDItemValueSetter.setValue(sMsg.ptyLocPk, ptyLocPk);

        PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.locNum, cMsg.locNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.locNm, getLocNm(cMsg));

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.stCd, cMsg.stCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.postCd, cMsg.postCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.provNm, cMsg.provNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.telNum, cMsg.telNum_H1.getValue());

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.effFromDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.apvlStsCd, APVL_STS.APPROVED);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.cmpyPk, cMsg.cmpyPk.getValue());

        setDplInfo(ptyLocWrkTMsg, cMsg, sMsg);

        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.embgoFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        } else {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
        }

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsNum, cMsg.dunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.geoCd, cMsg.geoCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.glnNum, cMsg.glnNum_H1.getValue());

        ZYPEZDItemValueSetter.setValue(cMsg.rgtnStsCd_H1, ptyLocWrkTMsg.rgtnStsCd.getValue());

        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsAcctNm, cMsg.dsAcctNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLocNm, cMsg.dsLocNm_H1.getValue());

        // Classification Tab
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsUltDunsNum, cMsg.dsUltDunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.hqDunsNum, cMsg.hqDunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsPrntDunsNum, cMsg.dsPrntDunsNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLocEmpNum, cMsg.dsLocEmpNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLocRevAmt, cMsg.dsLocRevAmt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsCustSicDescTxt, cMsg.dsCustSicDescTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsCustSicCd, cMsg.dsCustSicCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLastUpdDunsDt, cMsg.dsLastUpdDunsDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsTradeStyleNm, cMsg.dunsTradeStyleNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsActvCd, cMsg.dunsActvCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsLineAddr, cMsg.dunsLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dunsBizNm, cMsg.dunsBizNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsLastRcvDunsTxt, cMsg.dsLastRcvDunsTxt_H1.getValue());

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        }

        // Service Attrb Tab
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.reqTechCd, cMsg.reqTechCd_SA.getValue());
        ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.prfTechCd, cMsg.prfTechCd_SA.getValue());

        if (NMAL6720CommonLogic.checkCustomerAccount(cMsg.dsAcctTpCd_H1.getValue())) {
            S21FastTBLAccessor.insert(ptyLocWrkTMsg);
            if (!RTNCD_NORMAL.equals(ptyLocWrkTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_PTY_LOC_WRK });
                return;
            }
        } else {
            PROS_PTY_LOC_WRKTMsg prosPtyLocWrkTMsg = new PROS_PTY_LOC_WRKTMsg();
            EZDMsg.copy(ptyLocWrkTMsg, null, prosPtyLocWrkTMsg, null);
            S21FastTBLAccessor.insert(prosPtyLocWrkTMsg);
            if (!RTNCD_NORMAL.equals(prosPtyLocWrkTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_PROS_PTY_LOC_WRK });
                return;
            }
        }

    }

    private void saveInvRcpntInfo(NMAL6720CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.billToCustCd_BI) && !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        INV_RCPNTTMsg invRcpntTMsg = new INV_RCPNTTMsg();
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.custPk, cMsg.billToCustPk_BI.getValue());

        invRcpntTMsg = (INV_RCPNTTMsg) S21FastTBLAccessor.findByKey(invRcpntTMsg);
        if (invRcpntTMsg != null) {

            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.custCd, cMsg.billToCustCd_BI.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.stCd, cMsg.stCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.postCd, cMsg.postCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.provNm, cMsg.provNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.telNum, cMsg.telNum_H1.getValue());

            if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
                ZYPEZDItemValueSetter.setValue(invRcpntTMsg.effFromDt, cMsg.effFromDt_BI.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                ZYPEZDItemValueSetter.setValue(invRcpntTMsg.effThruDt, cMsg.effThruDt_BI.getValue());
            }

            S21FastTBLAccessor.update(invRcpntTMsg);
            if (!RTNCD_NORMAL.equals(invRcpntTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_INV_RCPNT });
                return;
            }
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {
                insertInvRcpnt(cMsg);
            }
        }
    }

    private void insertInvRcpnt(NMAL6720CMsg cMsg) {

        INV_RCPNTTMsg invRcpntTMsg = new INV_RCPNTTMsg();
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.glblCmpyCd, getGlobalCompanyCode());

        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.custPk, cMsg.billToCustPk_BI.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.custCd, cMsg.billToCustCd_BI.getValue());

        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.stCd, cMsg.stCd_P1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.postCd, cMsg.postCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.provNm, cMsg.provNm_H1.getValue());

        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.locNm, getLocNm(cMsg));
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.telNum, cMsg.telNum_H1.getValue());

        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.locGrpCd, LOC_GRP.CUSTOMER);

        if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.effFromDt, cMsg.effFromDt_BI.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
            ZYPEZDItemValueSetter.setValue(invRcpntTMsg.effThruDt, cMsg.effThruDt_BI.getValue());
        }

        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.printDunsOnInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.invTmplCd, INV_TMPL.REGULAR);
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.cmpyPk, cMsg.cmpyPk.getValue());
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.altInvRcpntFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.locNum, cMsg.locNum_H1.getValue());

        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.invRcpntCustCd, cMsg.billToCustCd_BI.getValue()); // Add QC#19424
        ZYPEZDItemValueSetter.setValue(invRcpntTMsg.invRcpntDefFlg, ZYPConstant.FLG_ON_Y);

        S21FastTBLAccessor.insert(invRcpntTMsg);
        if (!RTNCD_NORMAL.equals(invRcpntTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_INV_RCPNT });
            return;
        }
    }

    private void saveAltPayer(NMAL6720CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.billToCustCd_BI) && !ZYPConstant.CHKBOX_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        ALT_PAYERTMsg altPayerTMsg = new ALT_PAYERTMsg();
        ZYPEZDItemValueSetter.setValue(altPayerTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(altPayerTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(altPayerTMsg.billToCustCd, cMsg.billToCustCd_BI.getValue());

        altPayerTMsg = (ALT_PAYERTMsg) S21FastTBLAccessor.findByKey(altPayerTMsg);

        if (altPayerTMsg != null) {

            ZYPEZDItemValueSetter.setValue(altPayerTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.stCd, cMsg.stCd_P1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.postCd, cMsg.postCd_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
            ZYPEZDItemValueSetter.setValue(altPayerTMsg.provNm, cMsg.provNm_H1.getValue());

            ZYPEZDItemValueSetter.setValue(altPayerTMsg.telNum, cMsg.telNum_H1.getValue());

            if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.effFromDt, cMsg.effFromDt_BI.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.effThruDt, cMsg.effThruDt_BI.getValue());
            }

            S21FastTBLAccessor.update(altPayerTMsg);
            if (!RTNCD_NORMAL.equals(altPayerTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_ALT_PAYER });
                return;
            }
        } else {

            if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnBillToFlg_BI.getValue())) {
                altPayerTMsg = new ALT_PAYERTMsg();

                ZYPEZDItemValueSetter.setValue(altPayerTMsg.glblCmpyCd, glblCmpyCd);
                // 2017/11/27 QC#20828 Mod Start
//                ZYPEZDItemValueSetter.setValue(altPayerTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
                if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                    ZYPEZDItemValueSetter.setValue(altPayerTMsg.sellToCustCd, NMAL6720CommonLogic.getMergeToAcctCd(cMsg));
                } else {
                    ZYPEZDItemValueSetter.setValue(altPayerTMsg.sellToCustCd, cMsg.dsAcctNum_H1.getValue());
                }
                // 2017/11/27 QC#20828 Mod End
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.billToCustCd, cMsg.billToCustCd_BI.getValue());

                ZYPEZDItemValueSetter.setValue(altPayerTMsg.firstLineAddr, cMsg.firstLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.scdLineAddr, cMsg.scdLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.frthLineAddr, cMsg.frthLineAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.ctryCd, cMsg.ctryCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.ctyAddr, cMsg.ctyAddr_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.stCd, cMsg.stCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.postCd, cMsg.postCd_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.cntyPk, cMsg.cntyPk_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.provNm, cMsg.provNm_H1.getValue());

                ZYPEZDItemValueSetter.setValue(altPayerTMsg.locNm, getLocNm(cMsg));
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.telNum, cMsg.telNum_H1.getValue());

                ZYPEZDItemValueSetter.setValue(altPayerTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.flPlnCmpyFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.ptyLocPk, cMsg.ptyLocPk.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.locRoleTpCd, LOC_ROLE_TP.BILL_TO);
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.locGrpCd, LOC_GRP.CUSTOMER);

                if (ZYPCommonFunc.hasValue(cMsg.effFromDt_BI)) {
                    ZYPEZDItemValueSetter.setValue(altPayerTMsg.effFromDt, cMsg.effFromDt_BI.getValue());
                }
                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                    ZYPEZDItemValueSetter.setValue(altPayerTMsg.effThruDt, cMsg.effThruDt_BI.getValue());
                }

                ZYPEZDItemValueSetter.setValue(altPayerTMsg.cmpyPk, cMsg.cmpyPk.getValue());
                ZYPEZDItemValueSetter.setValue(altPayerTMsg.prinCustPk, cMsg.prinCustPk.getValue());

                S21FastTBLAccessor.insert(altPayerTMsg);
                if (!RTNCD_NORMAL.equals(altPayerTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_ALT_PAYER });
                    return;
                }
            }
        }
    }

    private void saveAcctLoc(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, String dsAcctTpCd) {

        if (!NMAL6720CommonLogic.checkCustomerAccount(dsAcctTpCd)) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        // changed cMsg to sMsg
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ACCT_LOCTMsg acctLocTMsg = new ACCT_LOCTMsg();
            ZYPEZDItemValueSetter.setValue(acctLocTMsg.glblCmpyCd, glblCmpyCd);
            String salesDate = ZYPDateUtil.getSalesDate();
            String fromDate = sMsg.A.no(i).effFromDt_A1.getValue();
            String thruDate = MAX_DT;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effThruDt_A1)) {
                thruDate = sMsg.A.no(i).effThruDt_A1.getValue();
            }

            // 2017/11/27 QC#20828 Mod Start
            if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg) &&
                    !NMAL6720CommonLogic.checkCustomerAccount(sMsg.A.no(i).dsAcctTpCd_A1.getValue())) {
                continue;
            }
            // 2017/11/27 QC#20828 Mod End

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).acctLocPk_A1)) {
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.acctLocPk, sMsg.A.no(i).acctLocPk_A1.getValue());

                acctLocTMsg = (ACCT_LOCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(acctLocTMsg);
                if (acctLocTMsg == null || !RTNCD_NORMAL.equals(acctLocTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_ACCT_LOC });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A1.getValue(), sMsg.A.no(i).ezUpTimeZone_A1.getValue(), acctLocTMsg.ezUpTime.getValue(), acctLocTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                    ZYPEZDItemValueSetter.setValue(acctLocTMsg.effFromDt, fromDate);
                }

                if (!(RGTN_STS.TERMINATED.equals(acctLocTMsg.rgtnStsCd.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue()))) {

                    if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
                        ZYPEZDItemValueSetter.setValue(acctLocTMsg.rgtnStsCd, RGTN_STS.TERMINATED);

                        setEffectiveDateForTermination(acctLocTMsg.effFromDt, acctLocTMsg.effThruDt);
                    } else {

                        if (RGTN_STS.READY_FOR_ORDER_TAKING.equals(acctLocTMsg.rgtnStsCd.getValue())) {

                            // Mod Start 2019/05/23 QC#50101
                            //if (0 <= ZYPDateUtil.compare(salesDate, thruDate)) {
                            if (0 < ZYPDateUtil.compare(salesDate, thruDate)) {
                                // Mod End 2019/05/23 QC#50101

                                ZYPEZDItemValueSetter.setValue(acctLocTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
                            }
                            // Mod Start 2019/05/23 QC#50101
                        //} else if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 > ZYPDateUtil.compare(salesDate, thruDate)) {
                        } else if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 >= ZYPDateUtil.compare(salesDate, thruDate)) {
                            // Mod End 2019/05/23 QC#50101
                            ZYPEZDItemValueSetter.setValue(acctLocTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                            // Add Start 2019/05/23 QC#50101
                        } else if (0 < ZYPDateUtil.compare(salesDate, thruDate)) {
                            ZYPEZDItemValueSetter.setValue(acctLocTMsg.rgtnStsCd, RGTN_STS.TERMINATED);
                            // Add End 2019/05/23 QC#50101
                        }

                        if (MAX_DT.equals(thruDate)) {
                            thruDate = null;
                        }
                        ZYPEZDItemValueSetter.setValue(acctLocTMsg.effThruDt, thruDate);
                    }
                }

                ZYPEZDItemValueSetter.setValue(acctLocTMsg.dsAcctNum, sMsg.A.no(i).dsAcctNum_A1);
                S21FastTBLAccessor.update(acctLocTMsg);
                if (!RTNCD_NORMAL.equals(acctLocTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_ACCT_LOC });
                    return;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.acctLocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ACCT_LOC_SQ));
                // 2017/11/27 QC#20828 Mod Start
//                ZYPEZDItemValueSetter.setValue(acctLocTMsg.dsAcctNum, sMsg.A.no(i).dsAcctNum_A1.getValue());
                if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                    ZYPEZDItemValueSetter.setValue(acctLocTMsg.dsAcctNum, NMAL6720CommonLogic.getMergeToAcctCd(cMsg));
                } else {
                    ZYPEZDItemValueSetter.setValue(acctLocTMsg.dsAcctNum, sMsg.A.no(i).dsAcctNum_A1.getValue());
                }
                // 2017/11/27 QC#20828 Mod End
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.locNum, sMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.ptyLocPk, sMsg.ptyLocPk.getValue());
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.effFromDt, sMsg.A.no(i).effFromDt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(acctLocTMsg.effThruDt, sMsg.A.no(i).effThruDt_A1.getValue());

                // Mod Start 2019/05/23 QC#50101
                //if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 > ZYPDateUtil.compare(salesDate, thruDate)) {
                if (0 <= ZYPDateUtil.compare(salesDate, fromDate) && 0 >= ZYPDateUtil.compare(salesDate, thruDate)) {
                    // Mod End 2019/05/23 QC#50101
                    ZYPEZDItemValueSetter.setValue(acctLocTMsg.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                } else {
                    ZYPEZDItemValueSetter.setValue(acctLocTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                }

                S21FastTBLAccessor.insert(acctLocTMsg);
                if (!RTNCD_NORMAL.equals(acctLocTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_ACCT_LOC });
                    return;
                }
            }
        }

        int len = sMsg.T.getValidCount();
        if (len > 0) {
            ACCT_LOCTMsg[] delArr = new ACCT_LOCTMsg[len];
            int cnt = 0;

            for (int i = 0; i < len; i++) {
                ACCT_LOCTMsg delTMsg = new ACCT_LOCTMsg();
                ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(delTMsg.acctLocPk, sMsg.T.no(i).acctLocPk_T1);

                delTMsg = (ACCT_LOCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg);

                if (delTMsg == null || !RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_ACCT_LOC });
                    return;
                }
                delArr[cnt] = delTMsg;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(delArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_ACCT_LOC });
                return;
            }
        }
    }

    private void saveDsXrefAcct(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        // Duplicate check
        if (isDuplicatedRefAndType(cMsg)) {
            return;
        }
        if (isDuplicatedRefAndTypeAtOtherLocation(cMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.geoCd_H1) && !cMsg.geoCd_H1.getValue().equals(sMsg.geoCd_H1.getValue())) {
            if (!checkExistsGeoCodeByVertex(cMsg)) {
                return;
            }
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            DS_XREF_ACCTTMsg dsXrefAcctTMsg = new DS_XREF_ACCTTMsg();
            ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsXrefAcctPk_B1)) {
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctPk, cMsg.B.no(i).dsXrefAcctPk_B1.getValue());

                dsXrefAcctTMsg = (DS_XREF_ACCTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsXrefAcctTMsg);

                if (dsXrefAcctTMsg == null || !RTNCD_NORMAL.equals(dsXrefAcctTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_XREF_ACCT });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cMsg.B.no(i).ezUpTime_B1.getValue(), cMsg.B.no(i).ezUpTimeZone_B1.getValue(), dsXrefAcctTMsg.ezUpTime.getValue(), dsXrefAcctTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctCd, cMsg.B.no(i).dsXrefAcctCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctNm, cMsg.B.no(i).dsXrefAcctNm_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctTpCd, cMsg.B.no(i).dsXrefAcctTpCd_P1.getValue());

                S21FastTBLAccessor.update(dsXrefAcctTMsg);
                if (!RTNCD_NORMAL.equals(dsXrefAcctTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_XREF_ACCT });
                    return;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_XREF_ACCT_SQ));

                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctTpCd, cMsg.B.no(i).dsXrefAcctTpCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctCd, cMsg.B.no(i).dsXrefAcctCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctNm, cMsg.B.no(i).dsXrefAcctNm_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.locNum, cMsg.locNum_H1.getValue());

                S21FastTBLAccessor.insert(dsXrefAcctTMsg);
                if (!RTNCD_NORMAL.equals(dsXrefAcctTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_XREF_ACCT });
                    return;
                }
            }
        }

        int len = sMsg.X.getValidCount();
        if (len > 0) {
            DS_XREF_ACCTTMsg[] dsXrefAcctTMsgArr = new DS_XREF_ACCTTMsg[len];
            int cnt = 0;

            for (int i = 0; i < sMsg.X.getValidCount(); i++) {
                DS_XREF_ACCTTMsg dsXrefAcctTMsg = new DS_XREF_ACCTTMsg();
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsXrefAcctTMsg.dsXrefAcctPk, sMsg.X.no(i).dsXrefAcctPk_X1.getValue());

                dsXrefAcctTMsg = (DS_XREF_ACCTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsXrefAcctTMsg);

                if (dsXrefAcctTMsg == null || !RTNCD_NORMAL.equals(dsXrefAcctTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_XREF_ACCT });
                    return;
                }
                dsXrefAcctTMsgArr[cnt] = dsXrefAcctTMsg;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(dsXrefAcctTMsgArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_XREF_ACCT });
                return;
            }
        }
    }

    private boolean isDuplicatedRefAndType(NMAL6720CMsg cMsg) {
        if (cMsg == null) {
            return true;
        }

        Set<String> dupliRefAndType = new HashSet<String>();
        Set<String> dupliCheckRefAndType = new HashSet<String>();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String currentRefAndType = cMsg.B.no(i).dsXrefAcctTpCd_P1.getValue() + ',' + cMsg.B.no(i).dsXrefAcctCd_B1.getValue();
            if (dupliCheckRefAndType.contains(currentRefAndType)) {
                dupliRefAndType.add(currentRefAndType);
            } else {
                dupliCheckRefAndType.add(currentRefAndType);
            }
        }
        boolean dupliFlg = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String currentRefAndType = cMsg.B.no(i).dsXrefAcctTpCd_P1.getValue() + ',' + cMsg.B.no(i).dsXrefAcctCd_B1.getValue();
            if (dupliRefAndType.contains(currentRefAndType)) {
                cMsg.B.no(i).dsXrefAcctTpCd_P1.setErrorInfo(1, NMAM8631E);
                cMsg.B.no(i).dsXrefAcctCd_B1.setErrorInfo(1, NMAM8631E);
                dupliFlg = true;
            }
        }
        return dupliFlg;
    }

    /**
     * checkExistsGeoCodeByVertex
     * @param cMsg NMAL6720CMsg
     * @return boolean
     */
    public static boolean checkExistsGeoCodeByVertex(NMAL6720CMsg cMsg) {

        NMXC107001PMsg pMsg = new NMXC107001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMXC107001.CHECK_EXISTS_GEO_CODE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.geoCd, cMsg.geoCd_H1);

        // Get Geo Code
        NMXC107001 api = new NMXC107001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            String msgId = pMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            cMsg.setMessageInfo(msgId);
            return false;
        }

        return true;

    }

    private boolean isDuplicatedRefAndTypeAtOtherLocation(NMAL6720CMsg cMsg) {
        if (cMsg == null) {
            return true;
        }

        boolean dupliFlg = false;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        List<Map<String, Object>> list = null;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
            queryParam.put("acctTpCd", cMsg.B.no(i).dsXrefAcctTpCd_P1.getValue());
            queryParam.put("acctCd", cMsg.B.no(i).dsXrefAcctCd_B1.getValue());
            queryParam.put("locNum", cMsg.locNum_H1.getValue());
            queryParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
            S21SsmEZDResult res = NMAL6720Query.getInstance().getDuplicateRefAndType(queryParam);
            if (res.isCodeNormal()) {
                list = (List<Map<String, Object>>) res.getResultObject();
                if (list != null && list.size() > 0) {
                    cMsg.B.no(i).dsXrefAcctTpCd_P1.setErrorInfo(1, NMAM8632E);
                    cMsg.B.no(i).dsXrefAcctCd_B1.setErrorInfo(1, NMAM8632E);
                    dupliFlg = true;
                }
            }
        }
        return dupliFlg;
    }

    private void saveDsCtacPsnReln(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        NMAL6720_CSMsg cSMsg;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            cSMsg = sMsg.C.no(i);
            if (!cSMsg.dsPrimLocFlg_C1.getValue().equals(cSMsg.dsPrimLocFlg_CB.getValue())) { // 2017/08/10 S21_NA#8598 Add
                // 2018/08/30 S21_NA#27869 Add Start
                if (!ZYPCommonFunc.hasValue(cSMsg.dsCtacPsnRelnPk_C1)) {
                    // Service Contact is not process target.
                    continue;
                }
                // 2018/08/30 S21_NA#27869 Add End
                DS_CTAC_PSN_RELNTMsg dsCtacPsnReln = new DS_CTAC_PSN_RELNTMsg();
                dsCtacPsnReln.glblCmpyCd.setValue(glblCmpyCd);
                dsCtacPsnReln.dsCtacPsnRelnPk.setValue(cSMsg.dsCtacPsnRelnPk_C1.getValue());

                dsCtacPsnReln = (DS_CTAC_PSN_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCtacPsnReln);
                if (dsCtacPsnReln == null) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CTAC_PSN_RELN });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cSMsg.ezUpTime_C1.getValue(), cSMsg.ezUpTimeZone_C1.getValue(), dsCtacPsnReln.ezUpTime.getValue(), dsCtacPsnReln.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                if (ZYPConstant.CHKBOX_ON_Y.equals(cSMsg.dsPrimLocFlg_C1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
                }

                S21FastTBLAccessor.update(dsCtacPsnReln);
                if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CTAC_PSN_RELN });
                    return;
                }
            }
        }
    }

    private void saveDsCustTrxRule(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        // Get Related Account for Related BillTo/ShipTo
        // Mod Start 2019/08/07 QC#52385
//        final NMZC610001 custInfoGetApi = new NMZC610001();
//
//        // Create Api Parameter
//        NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_RELATED_BILL_SHIP);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxChildRelnFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, cMsg.dsAcctNum_H1.getValue());
//
//        // Call Api
//        custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
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
        List<Map<String, Object>> relCustInfoDefBillShip = //
            NMAL6720CommonLogic.getRelatedCustomerInfoForDefaultBillShip(cMsg, glblCmpyCd, cMsg.dsAcctNum_H1.getValue());
        // Mod End 2019/08/07 QC#52385

        for (int i = 0; i < cMsg.D.getValidCount(); i++) {

            // Default Bill To Check
            if (ZYPCommonFunc.hasValue(cMsg.D.no(i).dsDefBillToCd_D1)) {
                BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
                billToCustTMsg.setSQLID("003");
                billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                billToCustTMsg.setConditionValue("billToCustCd01", cMsg.D.no(i).dsDefBillToCd_D1.getValue());

                BILL_TO_CUSTTMsgArray billTmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustTMsg);
                if (billTmsgArray.length() == 0) {
                    cMsg.D.no(i).dsDefBillToCd_D1.setErrorInfo(1, NMAM8111E, new String[] {MSG_BILL_TO_CUST });
                    return;
                }
            }

            // Default Ship To Check
            if (ZYPCommonFunc.hasValue(cMsg.D.no(i).dsDefShipToCd_D1)) {
                SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
                shipToCustTMsg.setSQLID("004");
                shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                shipToCustTMsg.setConditionValue("shipToCustCd01", cMsg.D.no(i).dsDefShipToCd_D1.getValue());

                SHIP_TO_CUSTTMsgArray shipTmsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
                if (shipTmsgArray.length() == 0) {
                    cMsg.D.no(i).dsDefShipToCd_D1.setErrorInfo(1, NMAM8111E, new String[] {MSG_SHIP_TO_CUST });
                    return;
                }
            }

            // Check Related Bill To/Ship To
            if (ZYPCommonFunc.hasValue(cMsg.D.no(i).dsDefBillToCd_D1)) {
                boolean chkDsDefBillToFlg = false;
                // Mod Start 2019/08/07 QC#52385
                //for (int j = 0; j < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); j++) {
                //    NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(j);
                //    if (inPmsg.billToCustCd.getValue().equals(cMsg.D.no(i).dsDefBillToCd_D1.getValue())) {
                for (int j = 0; j < relCustInfoDefBillShip.size(); j++) {
                    Map<String, Object> result = relCustInfoDefBillShip.get(j);
                    String billToCustCd = (String) result.get("R_BILL_TO_CUST_CD");
                    if (cMsg.D.no(i).dsDefBillToCd_D1.getValue().equals(billToCustCd)) {
                        // Mod End 2019/08/07 QC#52385
                        chkDsDefBillToFlg = true;
                    }
                }
                if (!chkDsDefBillToFlg) {
                    cMsg.D.no(i).dsDefBillToCd_D1.setErrorInfo(1, NMAM8111E, new String[] {"BILL_TO_CUST" });
                    return;
                }
            }
            if (ZYPCommonFunc.hasValue(cMsg.D.no(i).dsDefShipToCd_D1)) {
                boolean chkDsDefShipToFlg = false;
                // Mod Start 2019/08/07 QC#52385
                //for (int j = 0; j < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); j++) {
                //    NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(j);
                //    if (inPmsg.shipToCustCd.getValue().equals(cMsg.D.no(i).dsDefShipToCd_D1.getValue())) {
                for (int j = 0; j < relCustInfoDefBillShip.size(); j++) {
                    Map<String, Object> result = relCustInfoDefBillShip.get(j);
                    String shipToCustCd = (String) result.get("R_SHIP_TO_CUST_CD");
                    if (cMsg.D.no(i).dsDefShipToCd_D1.getValue().equals(shipToCustCd)) {
                        // Mod End 2019/08/07 QC#52385
                        chkDsDefShipToFlg = true;
                    }
                }
                if (!chkDsDefShipToFlg) {
                    cMsg.D.no(i).dsDefShipToCd_D1.setErrorInfo(1, NMAM8111E, new String[] {"SHIP_TO_CUST" });
                    return;
                }
            }

            // Save Transaction
            DS_CUST_TRX_RULETMsg dsCustTrxRule = new DS_CUST_TRX_RULETMsg();
            ZYPEZDItemValueSetter.setValue(dsCustTrxRule.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.D.no(i).dsCustTrxRulePk_D1)) {
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCustTrxRulePk, cMsg.D.no(i).dsCustTrxRulePk_D1.getValue());
                dsCustTrxRule = (DS_CUST_TRX_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCustTrxRule);
                if (dsCustTrxRule == null) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_TRX_RULE });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cMsg.D.no(i).ezUpTime_D1.getValue(), cMsg.D.no(i).ezUpTimeZone_D1.getValue(), dsCustTrxRule.ezUpTime.getValue(), dsCustTrxRule.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsTrxRuleTpCd, cMsg.D.no(i).dsTrxRuleTpCd_P1.getValue());
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).dsPoReqFlg_D1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsPoReqFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsPoReqFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsBlktPoNum, cMsg.D.no(i).dsBlktPoNum_D1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsPoExprDt, cMsg.D.no(i).dsPoExprDt_D1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsDefBillToCd, cMsg.D.no(i).dsDefBillToCd_D1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsDefShipToCd, cMsg.D.no(i).dsDefShipToCd_D1.getValue());
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).dsCrCardReqFlg_D1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCrCardReqFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCrCardReqFlg, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).dsOvngtAllwFlg_D1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsOvngtAllwFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsOvngtAllwFlg, ZYPConstant.FLG_OFF_N);
                }

                S21FastTBLAccessor.update(dsCustTrxRule);
                if (!RTNCD_NORMAL.equals(dsCustTrxRule.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_TRX_RULE });
                    return;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCustTrxRulePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_TRX_RULE_SQ));
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsTrxRuleTpCd, cMsg.D.no(i).dsTrxRuleTpCd_P1.getValue());
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).dsPoReqFlg_D1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsPoReqFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsPoReqFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsBlktPoNum, cMsg.D.no(i).dsBlktPoNum_D1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsPoExprDt, cMsg.D.no(i).dsPoExprDt_D1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsDefBillToCd, cMsg.D.no(i).dsDefBillToCd_D1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsDefShipToCd, cMsg.D.no(i).dsDefShipToCd_D1.getValue());
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).dsCrCardReqFlg_D1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCrCardReqFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCrCardReqFlg, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).dsOvngtAllwFlg_D1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsOvngtAllwFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsOvngtAllwFlg, ZYPConstant.FLG_OFF_N);
                }

                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.custEffLvlCd, CUST_EFF_LVL.LOCATION_ONLY);

                S21FastTBLAccessor.insert(dsCustTrxRule);
                if (!RTNCD_NORMAL.equals(dsCustTrxRule.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_CUST_TRX_RULE });
                    return;
                }
            }
        }

        int len = sMsg.Y.getValidCount();
        if (len > 0) {
            DS_CUST_TRX_RULETMsg[] dsCustTrxRuleArr = new DS_CUST_TRX_RULETMsg[len];
            int cnt = 0;

            for (int i = 0; i < sMsg.Y.getValidCount(); i++) {
                DS_CUST_TRX_RULETMsg dsCustTrxRule = new DS_CUST_TRX_RULETMsg();
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCustTrxRule.dsCustTrxRulePk, sMsg.Y.no(i).dsCustTrxRulePk_Y1.getValue());

                dsCustTrxRule = (DS_CUST_TRX_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCustTrxRule);

                if (dsCustTrxRule == null || !RTNCD_NORMAL.equals(dsCustTrxRule.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_XREF_ACCT });
                    return;
                }
                dsCustTrxRuleArr[cnt] = dsCustTrxRule;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(dsCustTrxRuleArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_XREF_ACCT });
                return;
            }
        }
    }

    private void saveDsCustSpcl(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        NMAL6720_ESMsg eSMsg;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            eSMsg = sMsg.E.no(i);
            DS_CUST_SPCL_MSGTMsg dsCustSpclTMsg = new DS_CUST_SPCL_MSGTMsg();
            ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(eSMsg.dsCustSpclMsgPk_E1)) {
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsCustSpclMsgPk, eSMsg.dsCustSpclMsgPk_E1.getValue());

                dsCustSpclTMsg = (DS_CUST_SPCL_MSGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCustSpclTMsg);
                if (dsCustSpclTMsg == null || !RTNCD_NORMAL.equals(dsCustSpclTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_SPCL_MSG });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(eSMsg.ezUpTime_E1.getValue(), eSMsg.ezUpTimeZone_E1.getValue(), dsCustSpclTMsg.ezUpTime.getValue(), dsCustSpclTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.lineBizTpCd, eSMsg.lineBizTpCd_P2.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsBizAreaCd, eSMsg.dsBizAreaCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsCustMsgTpCd, eSMsg.dsCustMsgTpCd_P1.getValue());

                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsCustMsgTxt, cutToMaxByte(eSMsg.dsCustMsgTxt_E1.getValue(), NMAL6720Constant.MAX_SPCL_MSG_LEN));
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.effThruDt, eSMsg.effThruDt_E1.getValue());

                if (ZYPConstant.CHKBOX_ON_Y.equals(eSMsg.dsPrintOnInvFlg_E1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsPrintOnInvFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsPrintOnInvFlg, ZYPConstant.FLG_OFF_N);
                }

                S21FastTBLAccessor.update(dsCustSpclTMsg);
                if (!RTNCD_NORMAL.equals(dsCustSpclTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_SPCL_MSG });
                    return;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsCustSpclMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_SPCL_MSG_SQ));

                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.lineBizTpCd, eSMsg.lineBizTpCd_P2.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsBizAreaCd, eSMsg.dsBizAreaCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsCustMsgTpCd, eSMsg.dsCustMsgTpCd_P1.getValue());

                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsCustMsgTxt, cutToMaxByte(eSMsg.dsCustMsgTxt_E1.getValue(), NMAL6720Constant.MAX_SPCL_MSG_LEN));
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.effFromDt, ZYPDateUtil.getSalesDate());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.effThruDt, eSMsg.effThruDt_E1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.custEffLvlCd, CUST_EFF_LVL.LOCATION_ONLY);

                if (ZYPConstant.FLG_ON_Y.equals(eSMsg.dsPrintOnInvFlg_E1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsPrintOnInvFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCustSpclTMsg.dsPrintOnInvFlg, ZYPConstant.FLG_OFF_N);
                }

                S21FastTBLAccessor.insert(dsCustSpclTMsg);
                if (!RTNCD_NORMAL.equals(dsCustSpclTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_CUST_SPCL_MSG });
                    return;
                }
            }
        }

        int len = sMsg.W.getValidCount();
        if (len > 0) {
            DS_CUST_SPCL_MSGTMsg[] delArr = new DS_CUST_SPCL_MSGTMsg[len];
            int cnt = 0;

            for (int i = 0; i < len; i++) {
                DS_CUST_SPCL_MSGTMsg delTMsg = new DS_CUST_SPCL_MSGTMsg();
                ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(delTMsg.dsCustSpclMsgPk, sMsg.W.no(i).dsCustSpclMsgPk_W1);

                delTMsg = (DS_CUST_SPCL_MSGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg);

                if (delTMsg == null || !RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_SPCL_MSG });
                    return;
                }
                delArr[cnt] = delTMsg;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(delArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_SPCL_MSG });
                return;
            }
        }
    }

    private void saveDsCustNonPrfTech(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        for (int i = 0; i < cMsg.G.getValidCount(); i++) {

            if (!isExistTech(cMsg.G.no(i).nonPrfTechCd_G1.getValue())) {
                cMsg.G.no(i).nonPrfTechCd_G1.setErrorInfo(1, NMAM8111E, new String[] {MSG_TECH_MSTR });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SVC_ATTRB);
                return;
            }

            DS_CUST_NON_PRF_TECHTMsg dsCustNonPrfTech = new DS_CUST_NON_PRF_TECHTMsg();
            ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.G.no(i).dsCustNonPrfTechPk_G1)) {
                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.dsCustNonPrfTechPk, cMsg.G.no(i).dsCustNonPrfTechPk_G1.getValue());
                dsCustNonPrfTech = (DS_CUST_NON_PRF_TECHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCustNonPrfTech);
                if (dsCustNonPrfTech == null) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_NON_PRF_TECH });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cMsg.G.no(i).ezUpTime_G1.getValue(), cMsg.G.no(i).ezUpTimeZone_G1.getValue(), dsCustNonPrfTech.ezUpTime.getValue(), dsCustNonPrfTech.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.effThruDt, cMsg.G.no(i).effThruDt_G1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.nonPrfTechCd, cMsg.G.no(i).nonPrfTechCd_G1.getValue());

                S21FastTBLAccessor.update(dsCustNonPrfTech);
                if (!RTNCD_NORMAL.equals(dsCustNonPrfTech.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_NON_PRF_TECH });
                    return;
                }
            } else {

                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.dsCustNonPrfTechPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_NON_PRF_TECH_SQ));
                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.effThruDt, cMsg.G.no(i).effThruDt_G1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustNonPrfTech.nonPrfTechCd, cMsg.G.no(i).nonPrfTechCd_G1.getValue());
                S21FastTBLAccessor.insert(dsCustNonPrfTech);
                if (!RTNCD_NORMAL.equals(dsCustNonPrfTech.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_CUST_NON_PRF_TECH });
                    return;
                }

            }
        }

        int len = sMsg.V.getValidCount();
        if (len > 0) {
            DS_CUST_NON_PRF_TECHTMsg[] delArr = new DS_CUST_NON_PRF_TECHTMsg[len];
            int cnt = 0;

            for (int i = 0; i < len; i++) {
                DS_CUST_NON_PRF_TECHTMsg delTMsg = new DS_CUST_NON_PRF_TECHTMsg();
                ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(delTMsg.dsCustNonPrfTechPk, sMsg.V.no(i).dsCustNonPrfTechPk_V1);

                delTMsg = (DS_CUST_NON_PRF_TECHTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg);

                if (delTMsg == null || !RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_NON_PRF_TECH });
                    return;
                }
                delArr[cnt] = delTMsg;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(delArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_NON_PRF_TECH });
                return;
            }
        }
    }

    private void saveSvcAccsPmit(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        for (int i = 0; i < cMsg.F.getValidCount(); i++) {

            SVC_ACCS_PMIT_VALTMsg svcAccsPmitValTMsg = new SVC_ACCS_PMIT_VALTMsg();
            svcAccsPmitValTMsg.setSQLID("001");
            svcAccsPmitValTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            svcAccsPmitValTMsg.setConditionValue("svcAccsPmitNum01", cMsg.F.no(i).svcAccsPmitNum_F1.getValue());

            SVC_ACCS_PMIT_VALTMsgArray svcAccsPmitValTMsgArray = (SVC_ACCS_PMIT_VALTMsgArray) EZDTBLAccessor.findByCondition(svcAccsPmitValTMsg);
            if (svcAccsPmitValTMsgArray.length() == 0) {
                cMsg.F.no(i).svcAccsPmitNum_F1.setErrorInfo(1, NMAM8111E, new String[] {MSG_SVC_ACCS_PMIT_NUM });
                ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SVC_ATTRB);
                return;
            }

            SVC_ACCS_PMITTMsg svcAccsPmitTMsg = new SVC_ACCS_PMITTMsg();
            ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.F.no(i).svcAccsPmitPk_F1)) {
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcAccsPmitPk, cMsg.F.no(i).svcAccsPmitPk_F1.getValue());
                svcAccsPmitTMsg = (SVC_ACCS_PMITTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(svcAccsPmitTMsg);
                if (svcAccsPmitTMsg == null) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_SVC_ACCS_PMIT });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(cMsg.F.no(i).ezUpTime_F1.getValue(), cMsg.F.no(i).ezUpTimeZone_F1.getValue(), svcAccsPmitTMsg.ezUpTime.getValue(), svcAccsPmitTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcAccsPmitNum, cMsg.F.no(i).svcAccsPmitNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.effFromDt, cMsg.F.no(i).effFromDt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.effToDt, cMsg.F.no(i).effToDt_F1.getValue());

                S21FastTBLAccessor.update(svcAccsPmitTMsg);
                if (!RTNCD_NORMAL.equals(svcAccsPmitTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SVC_ACCS_PMIT });
                    return;
                }
            } else {

                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcAccsPmitPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_ACCS_PMIT_SQ));
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcAccsPmitNum, cMsg.F.no(i).svcAccsPmitNum_F1.getValue());
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.effFromDt, cMsg.F.no(i).effFromDt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.effToDt, cMsg.F.no(i).effToDt_F1.getValue());
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcPmitLvlTpCd, SVC_PMIT_LVL_TP.SITE);
                ZYPEZDItemValueSetter.setValue(svcAccsPmitTMsg.svcPmitLvlValTxt, cMsg.locNum_H1.getValue());
                S21FastTBLAccessor.insert(svcAccsPmitTMsg);
                if (!RTNCD_NORMAL.equals(svcAccsPmitTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_SVC_ACCS_PMIT });
                    return;
                }
            }
        }

        int len = sMsg.U.getValidCount();
        if (len > 0) {
            SVC_ACCS_PMITTMsg[] delArr = new SVC_ACCS_PMITTMsg[len];
            int cnt = 0;

            for (int i = 0; i < len; i++) {
                SVC_ACCS_PMITTMsg delTMsg = new SVC_ACCS_PMITTMsg();
                ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(delTMsg.svcAccsPmitPk, sMsg.U.no(i).svcAccsPmitPk_U1);

                delTMsg = (SVC_ACCS_PMITTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg);

                if (delTMsg == null || !RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_SVC_ACCS_PMIT });
                    return;
                }
                delArr[cnt] = delTMsg;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(delArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SVC_ACCS_PMIT });
                return;
            }
        }
    }

    private boolean isExistTech(String techCd) {

        TECH_MSTRTMsg techMstrTMsg = new TECH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(techMstrTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(techMstrTMsg.techTocCd, techCd);
        techMstrTMsg = (TECH_MSTRTMsg) S21FastTBLAccessor.findByKey(techMstrTMsg);
        if (techMstrTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * getDefaultRemId
     * @param stCd String
     * @return String
     */
    private String getDefaultRemId(String stCd) {
        String remId = "";

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("stCd", stCd);

        S21SsmEZDResult ssmResult = getQuery().getDefaultRemId(queryParam);
        if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() == 1) {
            Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
            remId = (String) result.get("REM_ID");
        } else {
            // QC#6918
            ssmResult = getQuery().getDefaultRemIdNoState(queryParam);
            if (ssmResult.isCodeNormal() && ssmResult.getQueryResultCount() == 1) {
                Map<String, Object> result = (Map<String, Object>) ssmResult.getResultObject();
                remId = (String) result.get("REM_ID");
            }
        }

        return remId;
    }

    /**
     * @param cMsg NMAL6720CMsg
     * @return NMAL6720CMsg
     */
    private static String getLocNm(NMAL6720CMsg cMsg) {
        return S21StringUtil.subStringByLength(cMsg.dsAcctNm_H1.getValue(), 0, 60);
    }

    /**
     * Get Instance
     * @return
     */
    private static NMAL6720Query getQuery() {
        return NMAL6720Query.getInstance();
    }

    private boolean checkInputChange(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (!isInputChanged(cMsg, sMsg)) {
            cMsg.setMessageInfo("NMAM8333I");
            return false;
        }
        return true;
    }

    private boolean isInputChanged(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (isInputChanged_Header(cMsg, sMsg)) {
            return true;
        }
        if (isInputChanged_RowUpdate(cMsg, sMsg)) {
            return true;
        }
        if (isInputChanged_RowAdd(cMsg, sMsg)) {
            return true;
        }
        if (isInputChanged_RowDelete(cMsg, sMsg)) {
            return true;
        }
        return false;
    }

    private boolean isInputChanged_Header(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (!isEquals(cMsg.ctryCd_P1.getValue(), sMsg.ctryCd_P1.getValue()) || !isEquals(cMsg.ctryCd_P1.getValue(), sMsg.ctryCd_P1.getValue()) || !isEquals(cMsg.firstLineAddr_H1.getValue(), sMsg.firstLineAddr_H1.getValue())
                || !isEquals(cMsg.scdLineAddr_H1.getValue(), sMsg.scdLineAddr_H1.getValue()) || !isEquals(cMsg.thirdLineAddr_H1.getValue(), sMsg.thirdLineAddr_H1.getValue())
                || !isEquals(cMsg.frthLineAddr_H1.getValue(), sMsg.frthLineAddr_H1.getValue()) || !isEquals(cMsg.ctyAddr_H1.getValue(), sMsg.ctyAddr_H1.getValue()) || !isEquals(cMsg.stCd_P1.getValue(), sMsg.stCd_P1.getValue())
                || !isEquals(cMsg.postCd_H1.getValue(), sMsg.postCd_H1.getValue()) || !isEquals(cMsg.cntyNm_H1.getValue(), sMsg.cntyNm_H1.getValue()) || !isEquals(cMsg.provNm_H1.getValue(), sMsg.provNm_H1.getValue())
                || !isEquals(cMsg.dsAcctRelnBillToFlg_BI.getValue(), sMsg.dsAcctRelnBillToFlg_BI.getValue(), ZYPConstant.FLG_OFF_N) || !isEquals(cMsg.effFromDt_BI.getValue(), sMsg.effFromDt_BI.getValue())
                || !isEquals(cMsg.effThruDt_BI.getValue(), sMsg.effThruDt_BI.getValue()) || !isEquals(cMsg.billToCustCd_BI.getValue(), sMsg.billToCustCd_BI.getValue())
                || !isEquals(cMsg.primUsgFlg_BI.getValue(), sMsg.primUsgFlg_BI.getValue(), ZYPConstant.FLG_OFF_N) || !isEquals(cMsg.dsAcctRelnShipToFlg_SH.getValue(), sMsg.dsAcctRelnShipToFlg_SH.getValue(), ZYPConstant.FLG_OFF_N)
                || !isEquals(cMsg.effFromDt_SH.getValue(), sMsg.effFromDt_SH.getValue()) || !isEquals(cMsg.effThruDt_SH.getValue(), sMsg.effThruDt_SH.getValue())
                || !isEquals(cMsg.shipToCustCd_SH.getValue(), sMsg.shipToCustCd_SH.getValue()) || !isEquals(cMsg.billToCustCd_SH.getValue(), sMsg.billToCustCd_SH.getValue())
                || !isEquals(cMsg.primUsgFlg_SH.getValue(), sMsg.primUsgFlg_SH.getValue(), ZYPConstant.FLG_OFF_N) || !isEquals(cMsg.prinCustFlg_PR.getValue(), sMsg.prinCustFlg_PR.getValue(), ZYPConstant.FLG_OFF_N)
                || !isEquals(cMsg.dsLocNm_H1.getValue(), sMsg.dsLocNm_H1.getValue()) || !isEquals(cMsg.telNum_H1.getValue(), sMsg.telNum_H1.getValue())
                || !isEquals(cMsg.actvFlg_H1.getValue(), sMsg.actvFlg_H1.getValue(), ZYPConstant.FLG_OFF_N) || !isEquals(cMsg.lineBizTpCd_P1.getValue(), sMsg.lineBizTpCd_P1.getValue())
                || !isEquals(cMsg.dplStsCd_P1.getValue(), sMsg.dplStsCd_P1.getValue()) || !isEquals(cMsg.embgoFlg_H1.getValue(), sMsg.embgoFlg_H1.getValue(), ZYPConstant.FLG_OFF_N)
                || !isEquals(cMsg.dplModDtTmTs_H1.getValue(), sMsg.dplModDtTmTs_H1.getValue()) || !isEquals(cMsg.dplRsnTxt_H1.getValue(), sMsg.dplRsnTxt_H1.getValue()) || !isEquals(cMsg.glnNum_H1.getValue(), sMsg.glnNum_H1.getValue())
                || !isEquals(cMsg.dunsNum_H1.getValue(), sMsg.dunsNum_H1.getValue()) || !isEquals(cMsg.dsUltDunsNum_H1.getValue(), sMsg.dsUltDunsNum_H1.getValue()) || !isEquals(cMsg.hqDunsNum_H1.getValue(), sMsg.hqDunsNum_H1.getValue())
                || !isEquals(cMsg.dsPrntDunsNum_H1.getValue(), sMsg.dsPrntDunsNum_H1.getValue()) || !isEquals(cMsg.dsLocEmpNum_H1.getValue(), sMsg.dsLocEmpNum_H1.getValue())
                || !isEquals(cMsg.dsLocRevAmt_H1.getValue(), sMsg.dsLocRevAmt_H1.getValue()) || !isEquals(cMsg.dsCustSicCd_H1.getValue(), sMsg.dsCustSicCd_H1.getValue())
                || !isEquals(cMsg.dsCustSicDescTxt_H1.getValue(), sMsg.dsCustSicDescTxt_H1.getValue()) || !isEquals(cMsg.dsInsdCtyLimitFlg_H1.getValue(), sMsg.dsInsdCtyLimitFlg_H1.getValue(), ZYPConstant.FLG_OFF_N)
                || !isEquals(cMsg.geoCd_H1.getValue(), sMsg.geoCd_H1.getValue()) || !isEquals(cMsg.prfTechCd_SA.getValue(), sMsg.prfTechCd_SA.getValue()) || !isEquals(cMsg.reqTechCd_SA.getValue(), sMsg.reqTechCd_SA.getValue())
                // QC#5749
                || !isEquals(cMsg.dunsTradeStyleNm_H1.getValue(), sMsg.dunsTradeStyleNm_H1.getValue()) || !isEquals(cMsg.dunsActvCd_H1.getValue(), sMsg.dunsActvCd_H1.getValue())
                || !isEquals(cMsg.dunsLineAddr_H1.getValue(), sMsg.dunsLineAddr_H1.getValue()) || !isEquals(cMsg.dunsBizNm_H1.getValue(), sMsg.dunsBizNm_H1.getValue())
                // 2023/11/06 QC#61924 Add Start
                || !isEquals(cMsg.xxChkBox_H1.getValue(), sMsg.xxChkBox_H1.getValue())
                // 2023/11/06 QC#61924 Add End
                // 2020/03/02 QC#55673 Add Start
                || !isEquals(cMsg.addlLocNm.getValue(), sMsg.addlLocNm.getValue())) {
                // 2020/03/02 QC#55673 Add End
            return true;
        }
        return false;
    }

    private boolean isInputChanged_RowUpdate(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        // QC#10677 MOD C.Tanaka 2016/7/5 Start change cMsg to sMsg
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!isEquals(sMsg.A.no(i).dsAcctNum_A1.getValue(), sMsg.A.no(i).dsAcctNum_AB.getValue()) || !isEquals(sMsg.A.no(i).effFromDt_A1.getValue(), sMsg.A.no(i).effFromDt_AB.getValue())
                    || !isEquals(sMsg.A.no(i).effThruDt_A1.getValue(), sMsg.A.no(i).effThruDt_AB.getValue())) {
                return true;
            }
            // QC#10677 MOD C.Tanaka 2016/7/5 End
        }
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (!isEquals(cMsg.B.no(i).dsXrefAcctTpCd_P1.getValue(), sMsg.B.no(i).dsXrefAcctTpCd_P1.getValue()) || !isEquals(cMsg.B.no(i).dsXrefAcctNm_B1.getValue(), sMsg.B.no(i).dsXrefAcctNm_B1.getValue())
                    || !isEquals(cMsg.B.no(i).dsXrefAcctCd_B1.getValue(), sMsg.B.no(i).dsXrefAcctCd_B1.getValue())) {
                return true;
            }
        }
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!isEquals(sMsg.C.no(i).ctacPsnNum_C1.getValue(), sMsg.R.no(i).ctacPsnNum_C1.getValue()) || !isEquals(sMsg.C.no(i).dsPrimLocFlg_C1.getValue(), sMsg.R.no(i).dsPrimLocFlg_C1.getValue(), ZYPConstant.FLG_OFF_N)) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (!isEquals(cMsg.D.no(i).dsTrxRuleTpCd_P1.getValue(), sMsg.D.no(i).dsTrxRuleTpCd_P1.getValue()) || !isEquals(cMsg.D.no(i).dsPoReqFlg_D1.getValue(), sMsg.D.no(i).dsPoReqFlg_D1.getValue(), ZYPConstant.FLG_OFF_N)
                    || !isEquals(cMsg.D.no(i).dsBlktPoNum_D1.getValue(), sMsg.D.no(i).dsBlktPoNum_D1.getValue()) || !isEquals(cMsg.D.no(i).dsPoExprDt_D1.getValue(), sMsg.D.no(i).dsPoExprDt_D1.getValue())
                    || !isEquals(cMsg.D.no(i).dsDefBillToCd_D1.getValue(), sMsg.D.no(i).dsDefBillToCd_D1.getValue()) || !isEquals(cMsg.D.no(i).dsDefShipToCd_D1.getValue(), sMsg.D.no(i).dsDefShipToCd_D1.getValue())
                    || !isEquals(cMsg.D.no(i).dsCrCardReqFlg_D1.getValue(), sMsg.D.no(i).dsCrCardReqFlg_D1.getValue(), ZYPConstant.FLG_OFF_N)
                    || !isEquals(cMsg.D.no(i).dsOvngtAllwFlg_D1.getValue(), sMsg.D.no(i).dsOvngtAllwFlg_D1.getValue(), ZYPConstant.FLG_OFF_N)) {
                return true;
            }
        }
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            if (!isEquals(sMsg.E.no(i).lineBizTpCd_P2.getValue(), sMsg.S.no(i).lineBizTpCd_P2.getValue()) || !isEquals(sMsg.E.no(i).dsBizAreaCd_P1.getValue(), sMsg.S.no(i).dsBizAreaCd_P1.getValue())
                    || !isEquals(sMsg.E.no(i).dsCustMsgTpCd_P1.getValue(), sMsg.S.no(i).dsCustMsgTpCd_P1.getValue()) || !isEquals(sMsg.E.no(i).dsCustMsgTxt_E1.getValue(), sMsg.S.no(i).dsCustMsgTxt_E1.getValue())
                    || !isEquals(sMsg.E.no(i).dsPrintOnInvFlg_E1.getValue(), sMsg.S.no(i).dsPrintOnInvFlg_E1.getValue(), ZYPConstant.FLG_OFF_N) || !isEquals(sMsg.E.no(i).effThruDt_E1.getValue(), sMsg.S.no(i).effThruDt_E1.getValue())) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            if (!isEquals(cMsg.F.no(i).svcAccsPmitNum_F1.getValue(), sMsg.F.no(i).svcAccsPmitNum_F1.getValue()) || !isEquals(cMsg.F.no(i).effFromDt_F1.getValue(), sMsg.F.no(i).effFromDt_F1.getValue())
                    || !isEquals(cMsg.F.no(i).effToDt_F1.getValue(), sMsg.F.no(i).effToDt_F1.getValue())) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.G.getValidCount(); i++) {
            if (!isEquals(cMsg.G.no(i).nonPrfTechCd_G1.getValue(), sMsg.G.no(i).nonPrfTechCd_G1.getValue()) || !isEquals(cMsg.G.no(i).effThruDt_G1.getValue(), sMsg.G.no(i).effThruDt_G1.getValue())) {
                return true;
            }
        }
        // 2018/02/19 QC#20297(Sol#379) add start
        for (int i = 0; i < sMsg.I.getValidCount(); i++) {
            // 2018/12/20 S21_NA#29315 Mod Start
            if (!isEquals(sMsg.I.no(i).lineBizTpCd_P3.getValue(), sMsg.K.no(i).lineBizTpCd_P3.getValue()) || !isEquals(sMsg.I.no(i).dsBizAreaCd_P2.getValue(), sMsg.K.no(i).dsBizAreaCd_P2.getValue()) //
                    || !isEquals(sMsg.I.no(i).frtCondCd_P1.getValue(), sMsg.K.no(i).frtCondCd_P1.getValue())  || !isEquals(sMsg.I.no(i).shpgSvcLvlCd_P1.getValue(), sMsg.K.no(i).shpgSvcLvlCd_P1.getValue()) //
                    || !isEquals(sMsg.I.no(i).effThruDt_I1.getValue(), sMsg.K.no(i).effThruDt_I1.getValue()) || !isEquals(sMsg.I.no(i).xxChkBox_ID.getValue(), sMsg.K.no(i).xxChkBox_ID.getValue()) //
                    || !isEquals(sMsg.I.no(i).vndCd_I3.getValue(), sMsg.K.no(i).vndCd_I3.getValue()) || !isEquals(sMsg.I.no(i).dsCarrAcctNum_I1.getValue(), sMsg.K.no(i).dsCarrAcctNum_I1.getValue()) //
                    || !isEquals(sMsg.I.no(i).effFromDt_I1.getValue(), sMsg.K.no(i).effFromDt_I1.getValue())) {
            // 2018/12/20 S21_NA#29315 Mod End
                return true;
            }
        }
        // 2018/02/19 QC#20297(Sol#379) add end
        return false;
    }

    private boolean isInputChanged_RowAdd(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (DS_ACCT_TP.CUSTOMER.equals(cMsg.dsAcctTpCd_H1.getValue())) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).acctLocPk_A1)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).dsXrefAcctPk_B1)) {
                return true;
            }
        }
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).dsCtacPsnRelnPk_C1)) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.D.no(i).dsCustTrxRulePk_D1)) {
                return true;
            }
        }
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.E.no(i).dsCustSpclMsgPk_E1)) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.F.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.F.no(i).svcAccsPmitPk_F1)) {
                return true;
            }
        }
        for (int i = 0; i < cMsg.G.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.G.no(i).dsCustNonPrfTechPk_G1)) {
                return true;
            }
        }
        // 2018/02/19 QC#20297(Sol#379) add start
        for (int i = 0; i < cMsg.I.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.I.no(i).dsCustShpgDefPk_I1)) {
                return true;
            }
        }
        // 2018/02/19 QC#20297(Sol#379) add end
        return false;
    }

    private boolean isInputChanged_RowDelete(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (sMsg.T.getValidCount() > 0) {
            return true;
        }
        if (sMsg.X.getValidCount() > 0) {
            return true;
        }
        if (sMsg.Y.getValidCount() > 0) {
            return true;
        }
        if (sMsg.W.getValidCount() > 0) {
            return true;
        }
        if (sMsg.U.getValidCount() > 0) {
            return true;
        }
        if (sMsg.V.getValidCount() > 0) {
            return true;
        }
        // 2018/02/19 QC#20297(Sol#379) add start
        if (sMsg.J.getValidCount() > 0) {
            return true;
        }
        // 2018/02/19 QC#20297(Sol#379) add end
        return false;
    }

    /**
     * Compare value for checkbox item
     * @param newValue Value from screen, when checkbox is selected
     * value will be "Y", otherwise value will be blank.
     * @param oldValue Value from DB, always "Y"/"N", will never be
     * blank.
     * @param newDefaultValue Blank checkbox's default value "N".
     * @return
     */
    private boolean isEquals(String newValue, String oldValue, String newDefaultValue) {
        if (isEquals(newValue, oldValue)) {
            return true;
        }
        if (!ZYPCommonFunc.hasValue(newValue)) {
            if (isEquals(oldValue, newDefaultValue)) {
                return true;
            }
        }
        return false;
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

    private boolean isEquals(BigDecimal newValue, BigDecimal oldValue) {
        if (oldValue == null & newValue == null) {
            return true;
        }
        if (oldValue == null || newValue == null) {
            return false;
        }
        if (oldValue.compareTo(newValue) == 0) {
            return true;
        }
        return false;
    }

    private boolean clearPrimLocPrinCust(NMAL6720CMsg cMsg, NMAL6720SMsg sharedMsg, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(cMsg.prinCustPk_PR)) {
            return true;
        }
        PRIN_CUSTTMsg prinCustTMsg = new PRIN_CUSTTMsg();
        prinCustTMsg.glblCmpyCd.setValue(glblCmpyCd);
        prinCustTMsg.prinCustPk.setValue(cMsg.prinCustPk_PR.getValue());
        prinCustTMsg = (PRIN_CUSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prinCustTMsg);
        if (prinCustTMsg == null || !RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PRIN_CUST });
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_PR.getValue(), cMsg.ezUpTimeZone_PR.getValue(), prinCustTMsg.ezUpTime.getValue(), prinCustTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        prinCustTMsg.firstLineAddr.clear();
        prinCustTMsg.scdLineAddr.clear();
        prinCustTMsg.thirdLineAddr.clear();
        prinCustTMsg.frthLineAddr.clear();
        prinCustTMsg.ctyAddr.clear();
        prinCustTMsg.cntyPk.clear();
        prinCustTMsg.provNm.clear();
        prinCustTMsg.stCd.clear();
        prinCustTMsg.postCd.clear();
        prinCustTMsg.ctryCd.clear();
        prinCustTMsg.locNum.clear();
        prinCustTMsg.ptyLocPk.clear();
        S21FastTBLAccessor.update(prinCustTMsg);
        if (!RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_PRIN_CUST });
            return false;
        }
        return true;
    }

    private int clearPrimLocDsAdcctCust(NMAL6720CMsg cMsg, NMAL6720SMsg sharedMsg, String glblCmpyCd) {
        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("001");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        sellToCustTMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H1.getValue());
        SELL_TO_CUSTTMsgArray sellToCustTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(sellToCustTMsg);
        if (sellToCustTMsgArray.length() == 0) {
            return 0;
        }
        sellToCustTMsg = sellToCustTMsgArray.no(0);
        sellToCustTMsg.firstLineAddr.clear();
        sellToCustTMsg.scdLineAddr.clear();
        sellToCustTMsg.thirdLineAddr.clear();
        sellToCustTMsg.frthLineAddr.clear();
        sellToCustTMsg.ctyAddr.clear();
        sellToCustTMsg.cntyPk.clear();
        sellToCustTMsg.provNm.clear();
        sellToCustTMsg.stCd.clear();
        sellToCustTMsg.postCd.clear();
        sellToCustTMsg.ctryCd.clear();
        sellToCustTMsg.locNum.clear();
        sellToCustTMsg.ptyLocPk.clear();
        S21FastTBLAccessor.update(sellToCustTMsg);
        if (!RTNCD_NORMAL.equals(sellToCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_SELL_TO_CUST });
            return -1;
        }
        return 1;
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

    /**
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     * @param profile S21UserProfileService
     * @param res
     * @return true: There is not any denied party found. false: If
     * error message is set, API errors are found. If error message is
     * not set, DPL parties are found.
     */
    public static boolean isDplCheckOk_BasicInfo(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, S21UserProfileService profile) {

        if (!ZYPConstant.FLG_ON_Y.equals(ZYPCodeDataUtil.getVarCharConstValue("ECS_AVAL_FLG", profile.getGlobalCompanyCode()))) {
            setDPLReslutEcsNotAvailable_RqstList(cMsg, profile);
            return true;
        }

        String saleDate = ZYPDateUtil.getSalesDate();
        // ISO 8601 Date Format: YYYY-MM-DD
        String saleDateISO8601 = saleDate.substring(0, 4) + "-" + saleDate.substring(4, 6) + "-" + saleDate.substring(6, 8);

        Request req = new Request();

        // ---------------------------------------
        // Header
        // ---------------------------------------
        req.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_TYPE", profile.getGlobalCompanyCode()));
        req.setVersion(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_VERSION", profile.getGlobalCompanyCode()));
        req.setDateFormat(ZYPCodeDataUtil.getVarCharConstValue("ECS_REC_DTFORMAT", profile.getGlobalCompanyCode()));
        req.setDeploymentMode(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_DEPLOYMODE", profile.getGlobalCompanyCode()));
        req.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_REQ_HANDLER", profile.getGlobalCompanyCode()));

        // ---------------------------------------
        // Administration
        // ---------------------------------------
        Administration admin = new Administration();
        admin.setSubscriberID(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_ID", profile.getGlobalCompanyCode()));
        admin.setSubscriberPassword(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_PASS", profile.getGlobalCompanyCode()));
        admin.setRequestDate(saleDateISO8601);
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_TYPE", profile.getGlobalCompanyCode()));
        admin.setOutputFormat(outputFormat);
        admin.setIncludeRequest(ZYPCodeDataUtil.getVarCharConstValue("ECS_ADMIN_INCREQ", profile.getGlobalCompanyCode()));

        req.setAdministration(admin);

        // ---------------------------------------
        // Service
        // ---------------------------------------
        com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service service = new com.canon.usa.s21.integration.service.s21chile.kewill.partyscreening.request.Service();

        // Service: ECE
        service.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_ECE", profile.getGlobalCompanyCode()));
        service.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVHDLR_ECE", profile.getGlobalCompanyCode()));

        // Service: DeniedParty
        Svc serviceNest0 = new Svc();
        serviceNest0.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_DPL", profile.getGlobalCompanyCode()));
        serviceNest0.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVHDLR_DPL", profile.getGlobalCompanyCode()));

        Parameters parametersForDnp = new Parameters();
        Parameter paraForDnp = new Parameter();
        paraForDnp = new Parameter();
        paraForDnp.setName(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMNAME", profile.getGlobalCompanyCode()));
        paraForDnp.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMVAL", profile.getGlobalCompanyCode()));
        parametersForDnp.getParameter().add(paraForDnp);
        serviceNest0.setParameters(parametersForDnp);

        service.getService().add(serviceNest0);

        // Service: Embergo
        Svc serviceNest1 = new Svc();
        serviceNest1.setType(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_EMBGO", profile.getGlobalCompanyCode()));
        serviceNest1.setHandler(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVHDLR_EMBGO", profile.getGlobalCompanyCode()));

        Parameters parametersForEbg = new Parameters();
        Parameter paraForEbg = new Parameter();
        paraForEbg = new Parameter();
        paraForEbg.setName(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMNAME", profile.getGlobalCompanyCode()));
        paraForEbg.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_PARMVAL", profile.getGlobalCompanyCode()));
        parametersForEbg.getParameter().add(paraForEbg);
        serviceNest1.setParameters(parametersForEbg);

        service.getService().add(serviceNest1);
        // req.setService(service);
        req.getService().add(service);

        // ---------------------------------------
        // Transaction
        // ---------------------------------------
        ServiceRequest sr = new ServiceRequest();

        Transaction t = new Transaction();
        // t = new Transaction();
        t.setUserDefined(cMsg.cmpyPk.getValue().toString());
        t.setTransactionDate(saleDateISO8601);

        ShipFromCountry sfc = new ShipFromCountry();
        sfc.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CTRY", profile.getGlobalCompanyCode()));
        sfc.setScheme(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_SCHM", profile.getGlobalCompanyCode()));
        sfc.setDomain(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_DOMAIN", profile.getGlobalCompanyCode()));
        sfc.setCode(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CD", profile.getGlobalCompanyCode()));
        t.setShipFromCountry(sfc);

        ShipToCountry stc = new ShipToCountry();
        stc.setValue(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CTRY", profile.getGlobalCompanyCode()));
        stc.setScheme(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_SCHM", profile.getGlobalCompanyCode()));
        stc.setDomain(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_DOMAIN", profile.getGlobalCompanyCode()));
        stc.setCode(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_CD", profile.getGlobalCompanyCode()));
        t.setShipToCountry(stc);

        // ---------------------------------------
        // Transaction Line
        // ---------------------------------------
        List<TransactionLine> listTl = new ArrayList<TransactionLine>();

        TransactionLine tl = new TransactionLine();
        tl.setUserDefined(cMsg.ptyLocPk.getValue().toString());

        Party party = new Party();

        party.setUserDefined(cMsg.ptyLocPk.getValue().toString());

        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H1)) {
            party.setName(getLocNm(cMsg));
        }

        String tmpAddress = "";
        if (ZYPCommonFunc.hasValue(cMsg.firstLineAddr_H1)) {
            tmpAddress += cMsg.firstLineAddr_H1.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.scdLineAddr_H1)) {
            if (ZYPCommonFunc.hasValue(tmpAddress)) {
                tmpAddress += " ";
            }
            tmpAddress += cMsg.scdLineAddr_H1.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.thirdLineAddr_H1)) {
            if (ZYPCommonFunc.hasValue(tmpAddress)) {
                tmpAddress += " ";
            }
            tmpAddress += cMsg.thirdLineAddr_H1.getValue();
        }
        if (ZYPCommonFunc.hasValue(cMsg.frthLineAddr_H1)) {
            if (ZYPCommonFunc.hasValue(tmpAddress)) {
                tmpAddress += " ";
            }
            tmpAddress += cMsg.frthLineAddr_H1.getValue();
        }
        party.setAddress(tmpAddress);

        if (ZYPCommonFunc.hasValue(cMsg.ctyAddr_H1)) {
            party.setCity(cMsg.ctyAddr_H1.getValue());
        }
        boolean flgProv = false;
        if (ZYPCommonFunc.hasValue(cMsg.stCd_P1)) {
            STTMsg tmsgST = NMAL6720CommonLogic.findByKeySt(profile.getGlobalCompanyCode(), cMsg.stCd_P1.getValue());
            if (null != tmsgST) {
                if (ZYPCommonFunc.hasValue(tmsgST.stNm)) {
                    party.setSubDivision(tmsgST.stNm.getValue());
                } else {
                    flgProv = true;
                }
            } else {
                flgProv = true;
            }
        } else {
            flgProv = true;
        }
        if (flgProv) {
            if (ZYPCommonFunc.hasValue(cMsg.provNm_H1)) {
                party.setSubDivision(cMsg.provNm_H1.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.postCd_H1)) {
            party.setPostalCode(cMsg.postCd_H1.getValue());
        }

        Country country = new Country();
        country.setScheme(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_SCHM", profile.getGlobalCompanyCode()));
        country.setDomain(ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SHIP_FR_DOMAIN", profile.getGlobalCompanyCode()));

        if (ZYPCommonFunc.hasValue(cMsg.ctryCd_P1)) {
            country.setCode(cMsg.ctryCd_P1.getValue());
            country.setValue(getCtryNmByCtryCd(profile.getGlobalCompanyCode(), cMsg.ctryCd_P1.getValue()));
        }

        party.setCountry(country);
        tl.getParty().add(party);
        listTl.add(tl);

        t.getTransactionLine().addAll(listTl);
        sr.getTransaction().add(t);
        req.setServiceRequest(sr);

        try {
            S21ChileKewillPartyScreeningServiceProxy proxyStub = new S21ChileKewillPartyScreeningServiceProxy();
            // Call party screening service
            Response res = proxyStub.newRequest(req);

            // Check respone
            if (checkDPLCheckResult_RequestList(cMsg, sMsg, profile, res) == false) {
                return false;
            }
        } catch (RemoteException e) {
            setEcsApiExceptionError_RqstList(cMsg, e);
            return false;
        } catch (CallPartyScreeningServiceFaultResourceExp e) {
            setEcsApiExceptionError_RqstList(cMsg, e);
            return false;
        } catch (CallPartyScreeningServiceFaultDataExp e) {
            setEcsApiExceptionError_RqstList(cMsg, e);
            return false;
        } catch (CallPartyScreeningServiceFaultUserExp e) {
            setEcsApiExceptionError_RqstList(cMsg, e);
            return false;
        } catch (IOException e) {
            setEcsApiExceptionError_RqstList(cMsg, e);
            return false;
        } catch (PropertyException e) {
            setEcsApiExceptionError_RqstList(cMsg, e);
            return false;
        } catch (Throwable e) {
            cMsg.setMessageInfo(NMAM8052E);
            S21InfoLogOutput.println("[Ecs Err]:" + e.toString());
            return false;
        }

        return true;
    }

    private static boolean checkDPLCheckResult_RequestList(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, S21UserProfileService profile, Response res) {

        // Check API error
        boolean isApiError = isEcsApiError_RqstList(res);
        if (isApiError == true) {
            cMsg.setMessageInfo(NMAM8052E);
            return false;
        }

        // Check screening results
        List<Service> services = res.getServiceResponse().getService();
        boolean hasDeniedParty = false;

        String embgoFlg = ZYPConstant.FLG_OFF_N;
        String dplStsCd = DPL_STS.PASS;
        String dplRsnTxt = null;
        String dplRspDtTmTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");

        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);

            // Find service respone of the current location
            if (cMsg.ptyLocPk.getValue().compareTo(new BigDecimal(service.getUserDefined())) != 0) {
                continue;
            }

            // Check embargo
            if (ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_EMBGO", profile.getGlobalCompanyCode()).equals(service.getType())) {
                if (ZYPConstant.FLG_ON_Y.equals(service.getEmbargo())) {
                    embgoFlg = ZYPConstant.FLG_ON_Y;

                }
            }

            // Check deny party
            if (ZYPCodeDataUtil.getVarCharConstValue("ECS_SERV_SERVTP_DPL", profile.getGlobalCompanyCode()).equals(service.getType())) {
                List<DeniedParty> deniedParty = service.getDeniedParty();
                if (deniedParty != null) {
                    if (deniedParty.size() > 0 && ZYPCommonFunc.hasValue(deniedParty.get(0).getWords())) {
                        dplStsCd = DPL_STS.FAIL;
                        dplRsnTxt = deniedParty.get(0).getWords();
                        hasDeniedParty = true;
                    }
                }
                String redFlagWords = service.getRedFlagWords();
                if (false == hasDeniedParty && redFlagWords != null && redFlagWords.trim().length() > 0) {
                    dplStsCd = DPL_STS.FAIL;
                    if (redFlagWords.length() > MAX_LEN_DPL_RSN_TXT) {
                        redFlagWords = redFlagWords.substring(0, MAX_LEN_DPL_RSN_TXT);
                    }
                    dplRsnTxt = redFlagWords;

                }
            }
        }

        // Check dplRsnTxt's length
        if (dplRsnTxt != null) {
            if (dplRsnTxt.length() > MAX_LEN_DPL_RSN_TXT) {
                dplRsnTxt = dplRsnTxt.substring(0, MAX_LEN_DPL_RSN_TXT);
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.embgoFlg_H1, embgoFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.dplStsCd_P1, dplStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.dplRsnTxt_H1, dplRsnTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.dplRspDtTmTs_H1, dplRspDtTmTs);

        return true;
    }

    private static boolean isEcsApiError_RqstList(Response res) {
        if (res == null) {
            // EZDDebugOutput.println(1,
            // "[Ecs Err]: Response is not available.",
            // NMAL6720BL06.class);
            S21InfoLogOutput.println("[Ecs Err]: Response is not available.");
            return true;
        }

        if (res.getError() != null) {
            debugOutpuEcsApiError_RqstList(res.getError());
            return true;
        }

        if (res.getServiceResponse() == null) {
            // EZDDebugOutput.println(1,
            // "[Ecs Err]: serviceResponse is not available.",
            // NMAL6720BL06.class);
            S21InfoLogOutput.println("[Ecs Err]: serviceResponse is not available.");
            return true;
        }

        List<Service> services = res.getServiceResponse().getService();

        if (services == null) {
            // EZDDebugOutput.println(1,
            // "[Ecs Err]: services is not available.",
            // NMAL6720BL06.class);
            S21InfoLogOutput.println("[Ecs Err]: services is not available.");
            return true;
        }

        // Check API error
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i) != null) {
                List<Error> err = services.get(i).getError();
                if (err != null && err.size() != 0) {
                    for (int c = 0; c < err.size(); c++) {
                        debugOutpuEcsApiError_RqstList(err.get(c));
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private static void setDPLReslutEcsNotAvailable_RqstList(NMAL6720CMsg cMsg, S21UserProfileService profile) {

        String embgoFlg = ZYPConstant.FLG_OFF_N;
        // "The connection to the Kewill System was not available.";
        String dplStsCd = ZYPCodeDataUtil.getVarCharConstValue("DPL_STS_CD_INIT_VAL", profile.getGlobalCompanyCode());
        String dplRsnTxt = "";
        if (DPL_STS.FAIL.equals(dplStsCd)) {
            dplRsnTxt = "The connection to the Kewill System was not available.";
        }
        String dplRspDtTmTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");

        ZYPEZDItemValueSetter.setValue(cMsg.embgoFlg_H1, embgoFlg);
        ZYPEZDItemValueSetter.setValue(cMsg.dplStsCd_P1, dplStsCd);
        ZYPEZDItemValueSetter.setValue(cMsg.dplRsnTxt_H1, dplRsnTxt);
        ZYPEZDItemValueSetter.setValue(cMsg.dplRspDtTmTs_H1, dplRspDtTmTs);
    }

    private static String getCtryNmByCtryCd(String glblCmpyCd, String ctryCd) {
        CTRYTMsg tMsg = new CTRYTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.ctryCd.setValue(ctryCd);
        // tMsg = (CTRYTMsg)EZDTBLAccessor.findByKey(tMsg);
        tMsg = (CTRYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ctryNm.getValue();
        }
        return "";
    }

    private static void debugOutpuEcsApiError_RqstList(Error err) {
        // EZDDebugOutput.println(1, "[Ecs Err]:" + err.getSource(),
        // NMAL6720BL06.class);
        // EZDDebugOutput.println(1, "[Ecs Err]:" + err.getType(),
        // NMAL6720BL06.class);
        // EZDDebugOutput.println(1, "[Ecs Err]:" + err.getCode(),
        // NMAL6720BL06.class);
        // EZDDebugOutput.println(1, "[Ecs Err]:" + err.getMessage(),
        // NMAL6720BL06.class);
        S21InfoLogOutput.println("[Ecs Err]:" + err.getSource());
        S21InfoLogOutput.println("[Ecs Err]:" + err.getType());
        S21InfoLogOutput.println("[Ecs Err]:" + err.getCode());
        S21InfoLogOutput.println("[Ecs Err]:" + err.getMessage());
    }

    private static void setEcsApiExceptionError_RqstList(NMAL6720CMsg cMsg, Exception exception) {
        cMsg.setMessageInfo(NMAM8052E);
        // EZDDebugOutput.println(1, "[Ecs Err]: " +
        // exception.toString(), NMAL6720CMsg.class);
        S21InfoLogOutput.println("[Ecs Err]:" + exception.toString());
    }

    // 2016/05/18 Unit Test#193 Add End

    // QC#11569
    private void setEffectiveDateForTermination(EZDTDateItem itemEffFromDt, EZDTDateItem itemEffThruDt) {
        String slsDt = ZYPDateUtil.getSalesDate();
        if (itemEffFromDt.getValue().compareTo(slsDt) < 0) {
            ZYPEZDItemValueSetter.setValue(itemEffThruDt, ZYPDateUtil.addDays(slsDt, -1));
        } else {
            ZYPEZDItemValueSetter.setValue(itemEffFromDt, ZYPDateUtil.addDays(slsDt, -1));
            ZYPEZDItemValueSetter.setValue(itemEffThruDt, ZYPDateUtil.addDays(slsDt, -1));
        }
    }

    // 2016/09/02 QC#13486 Add Start
    private void setDplInfo(PTY_LOC_WRKTMsg ptyLocWrkTMsg, NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.dsAcctRelnShipToFlg_SH.getValue()) && (!ZYPCommonFunc.hasValue(cMsg.rgtnStsCd_SH) || RGTN_STS.TERMINATED.equals((sMsg.rgtnStsCd_SH.getValue())))) {

            boolean isDplCheckOk = isDplCheckOk_BasicInfo(cMsg, sMsg, getUserProfileService());
            // Check DPL screening API error
            if (!isDplCheckOk) {
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplStsCd, cMsg.dplStsCd_P1);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplRsnTxt, cMsg.dplRsnTxt_H1);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplRspDtTmTs, cMsg.dplRspDtTmTs_H1);
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.embgoFlg_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                }
            }
        } else {
            String effThruDtShipTo = "";
            String salesDate = ZYPDateUtil.getSalesDate();
            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
                effThruDtShipTo = cMsg.effThruDt_SH.getValue();
            } else {
                effThruDtShipTo = MAX_DT;
            }
            if (ZYPDateUtil.compare(salesDate, effThruDtShipTo) <= 0) {
                if (!cMsg.dplStsCd_P1.getValue().equals(sMsg.dplStsCd_P1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplModDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
                    ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplModByLoginUsrId, getContextUserInfo().getUserId());
                    if (DPL_STS.PASS.equals(sMsg.dplStsCd_P1.getValue())) {
                        ptyLocWrkTMsg.dplRsnTxt.clear();
                    }
                }
            } else if (!cMsg.effThruDt_SH.getValue().equals(sMsg.effThruDt_SH.getValue())) {
                ptyLocWrkTMsg.dplStsCd.clear();
                ptyLocWrkTMsg.dplRsnTxt.clear();
                ptyLocWrkTMsg.dplRspDtTmTs.clear();
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplModDtTmTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMdd"));
                ZYPEZDItemValueSetter.setValue(ptyLocWrkTMsg.dplModByLoginUsrId, getContextUserInfo().getUserId());
            }
        }
    }

    private SELL_TO_CUSTTMsg getAccountCoAInfo(NMAL6720CMsg cMsg) {
        String dsAcctNum = null;
        String slsDt = ZYPDateUtil.getSalesDate();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NMAL6720_ACMsg acMsg = cMsg.A.no(i);
            if (isInPeriod(slsDt, acMsg.effFromDt_A1.getValue(), acMsg.effThruDt_A1.getValue())) {
                dsAcctNum = acMsg.dsAcctNum_A1.getValue();
                break;
            }
        }
        // if (!ZYPCommonFunc.hasValue(dsAcctNum)) { // 2018/03/26 S21_NA#23935 Mod
        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
            tMsg.setConditionValue("sellToCustCd01", dsAcctNum);
            SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            if (tMsgArray.length() > 0) {
                return tMsgArray.no(0);
            }
        }
        return null;
    }

    // START 2022/01/12 R.Azucena[QC#59596, ADD]
    private SELL_TO_CUSTTMsg getFirstAccountCoAInfo(NMAL6720CMsg cMsg) {
        String dsAcctNum = null;

        if (cMsg.A.getValidCount() > 0) {
            // Get first account
            dsAcctNum = cMsg.A.no(0).dsAcctNum_A1.getValue();
        }

        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
            tMsg.setConditionValue("sellToCustCd01", dsAcctNum);
            SELL_TO_CUSTTMsgArray tMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            if (tMsgArray.length() > 0) {
                return tMsgArray.no(0);
            }
        }

        return null;
    }
    // END 2022/01/12 R.Azucena[QC#59596, ADD]

    // QC#12060
    private DS_ACCT_PROSTMsg getProspectCoAInfo(NMAL6720CMsg cMsg) {
        String dsAcctNum = null;
        String slsDt = ZYPDateUtil.getSalesDate();

        if (dsAcctNum == null) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NMAL6720_ACMsg acMsg = cMsg.A.no(i);
                if (isInPeriod(slsDt, acMsg.effFromDt_A1.getValue(), acMsg.effThruDt_A1.getValue())) {
                    dsAcctNum = acMsg.dsAcctNum_A1.getValue();
                    break;
                }
            }
        }
        if (dsAcctNum != null) {
            DS_ACCT_PROSTMsg tMsg = new DS_ACCT_PROSTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
            tMsg.setConditionValue("dsAcctNum01", dsAcctNum);
            DS_ACCT_PROSTMsgArray tMsgArray = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            if (tMsgArray.length() > 0) {
                return tMsgArray.no(0);
            }
        }
        return null;
    }

    private boolean isInPeriod(String value, String from, String to) {
        if (value.compareTo(from) >= 0) {
            if (!ZYPCommonFunc.hasValue(to)) {
                return true;
            } else if (value.compareTo(to) <= 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isAccountChanged(NMAL6720CMsg cMsg) {
        String newDsAcctNum = null;
        String oldDsAcctNum = null;
        String slsDt = ZYPDateUtil.getSalesDate();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NMAL6720_ACMsg acMsg = cMsg.A.no(i);
            if (isInPeriod(slsDt, acMsg.effFromDt_A1.getValue(), acMsg.effThruDt_A1.getValue())) {
                newDsAcctNum = acMsg.dsAcctNum_A1.getValue();
                break;
            }
        }
        if (newDsAcctNum != null) {
            ACCT_LOCTMsg tMsg = new ACCT_LOCTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
            tMsg.setConditionValue("locNum01", cMsg.locNum_H1.getValue());
            ACCT_LOCTMsgArray tMsgArray = (ACCT_LOCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
            for (int i = 0; i < tMsgArray.length(); i++) {
                tMsg = tMsgArray.no(i);
                if (isInPeriod(slsDt, tMsg.effFromDt.getValue(), tMsg.effThruDt.getValue())) {
                    oldDsAcctNum = tMsg.dsAcctNum.getValue();
                    break;
                }
            }
        }
        if (newDsAcctNum != null && oldDsAcctNum != null) {
            if (!newDsAcctNum.equals(oldDsAcctNum)) {
                return true;
            }
        }
        return false;
    }

    // Mod Start 2018/05/16 QC#26041
    //private void switchProspectToCustomer(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
    private void switchProspectToCustomer(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, List<String> functionIds) {
        // Mod End 2018/05/16 QC#26041
        String glblCmpyCd = this.getGlobalCompanyCode();
        boolean isNeedToSwitch = false;

        if (DS_ACCT_TP.PROSPECT.equals(cMsg.dsAcctTpCd_H1.getValue())) {
            if (NMAL6720Constant.PROS_TO_CUST_CHNG_STS_PENDING.equals(cMsg.prosToCustChngStsCd_H1.getValue())) {
                // Add Start 2018/05/16 QC#26041
                if (functionIds.contains(FUNC_ID_LOCATION_GENERAL_UPDATE) || //
                        NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                    // Add End 2018/05/16 QC#26041

                isNeedToSwitch = true;

                // Add Start 2018/05/16 QC#26041
                }
                // Add End 2018/05/16 QC#26041
            }
        }
        if (isNeedToSwitch) {
            // delete prospect
            DS_ACCT_PROSTMsg tMsg = new DS_ACCT_PROSTMsg();
            tMsg.setSQLID("001");
            tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            tMsg.setConditionValue("dsAcctNum01", cMsg.dsAcctNum_H1.getValue());
            DS_ACCT_PROSTMsgArray tMsgArray = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByConditionForUpdate(tMsg);
            if (tMsgArray.length() == 0) {
                String[] args = {"DS_ACCT_PROS" };
                cMsg.setMessageInfo(NMAM8111E, args);
                return;
            }
            DS_ACCT_PROSTMsg deleteDsAcctProsTMsg = tMsgArray.no(0);
            // 2017/11/27 QC#20828 Mod Start
//            EZDTMsg[] tmsgList = new EZDTMsg[] {deleteDsAcctProsTMsg };
//            int delCnt = S21FastTBLAccessor.removeLogical(tmsgList);
//            if (delCnt != 1) {
//                cMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_PROS" });
//                return;
//            }
            if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                ZYPEZDItemValueSetter.setValue(deleteDsAcctProsTMsg.dsAcctActvCustFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(deleteDsAcctProsTMsg.rgtnStsCd, RGTN_STS.TERMINATED);

                String acctInacRsnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_ACCT_INAC_RSN_CD, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(acctInacRsnCd)) {
                    ZYPEZDItemValueSetter.setValue(deleteDsAcctProsTMsg.dsAcctInacRsnCd, acctInacRsnCd);
                }
            }

            EZDTMsg[] tmsgList = new EZDTMsg[] {deleteDsAcctProsTMsg };
            int delCnt = 0;

            if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                delCnt = S21FastTBLAccessor.update(tmsgList);

            } else {
                delCnt = S21FastTBLAccessor.removeLogical(tmsgList);
            }

            if (delCnt != 1) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {"DS_ACCT_PROS" });
                return;
            }
            // 2017/11/27 QC#20828 Mod End

            // create customer
            SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
            // 2017/11/27 QC#20828 Mod Start
//            EZDMsg.copy(deleteDsAcctProsTMsg, null, sellToCustTMsg, null);
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustPk, deleteDsAcctProsTMsg.dsAcctProsPk);
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustCd, deleteDsAcctProsTMsg.dsAcctNum);
//            sellToCustTMsg.prosToCustChngStsCd.clear();
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctTpCd, DS_ACCT_TP.CUSTOMER);
//
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.rgtnStsCd, cMsg.rgtnStsCd_H1);
//            sellToCustTMsg.prosToCustChngStsCd.clear();
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.usrDlrTpCd, USR_DLR_TP.USER);
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.canRcvInvAtSetCmptFlg, ZYPConstant.FLG_OFF_N);
//
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellHldFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.prinCustPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRIN_CUST_SQ));
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustCd, deleteDsAcctProsTMsg.dsAcctNum.getValue());
//
//            ZYPEZDItemValueSetter.setValue(sellToCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
//
//            S21FastTBLAccessor.create(sellToCustTMsg);
//            if (!RTNCD_NORMAL.equals(sellToCustTMsg.getReturnCode())) {
//                cMsg.setMessageInfo(NMAM0176E, new String[] {"SELL_TO_CUST" });
//                return;
//            }
            if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                sellToCustTMsg.setSQLID("001");
                sellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                sellToCustTMsg.setConditionValue("sellToCustCd01", NMAL6720CommonLogic.getMergeToAcctCd(cMsg));

                SELL_TO_CUSTTMsgArray sellToCustTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(sellToCustTMsg);
                if (sellToCustTMsgArray.length() == 0) {
                    return;
                }
                sellToCustTMsg = sellToCustTMsgArray.no(0);

            } else {
                EZDMsg.copy(deleteDsAcctProsTMsg, null, sellToCustTMsg, null);
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustPk, deleteDsAcctProsTMsg.dsAcctProsPk);
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustCd, deleteDsAcctProsTMsg.dsAcctNum);
                sellToCustTMsg.prosToCustChngStsCd.clear();
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.dsAcctTpCd, DS_ACCT_TP.CUSTOMER);

                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.rgtnStsCd, cMsg.rgtnStsCd_H1);
                sellToCustTMsg.prosToCustChngStsCd.clear();
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.usrDlrTpCd, USR_DLR_TP.USER);
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.canRcvInvAtSetCmptFlg, ZYPConstant.FLG_OFF_N);

                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.prinCustPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PRIN_CUST_SQ));
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sellToCustCd, deleteDsAcctProsTMsg.dsAcctNum.getValue());

                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);

                // START 2018/01/16 [QC#23882, ADD]
                ZYPEZDItemValueSetter.setValue(sellToCustTMsg.sendCrBalStmtFlg, ZYPConstant.FLG_OFF_N);
                // END   2018/01/16 [QC#23882, ADD]

                S21FastTBLAccessor.create(sellToCustTMsg);
                if (!RTNCD_NORMAL.equals(sellToCustTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {"SELL_TO_CUST" });
                    return;
                }
            }
            // 2017/11/27 QC#20828 Mod End

            // delete prospect location
            PROS_PTY_LOC_WRKTMsg deleteProsPtyLocWrkTmsg = new PROS_PTY_LOC_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(deleteProsPtyLocWrkTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(deleteProsPtyLocWrkTmsg.ptyLocPk, deleteDsAcctProsTMsg.ptyLocPk.getValue());
            deleteProsPtyLocWrkTmsg = (PROS_PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(deleteProsPtyLocWrkTmsg);
            if (deleteProsPtyLocWrkTmsg == null || !RTNCD_NORMAL.equals(deleteProsPtyLocWrkTmsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_PROS_PTY_LOC_WRK });
                return;
            }
            tmsgList = new EZDTMsg[] {deleteProsPtyLocWrkTmsg };
            delCnt = S21FastTBLAccessor.removeLogical(tmsgList);
            if (delCnt != 1) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_PROS_PTY_LOC_WRK });
                return;
            }

            // create customer location
            PTY_LOC_WRKTMsg ptyLocWrkTMsg = new PTY_LOC_WRKTMsg();
            EZDMsg.copy(deleteProsPtyLocWrkTmsg, null, ptyLocWrkTMsg, null);
            S21FastTBLAccessor.create(ptyLocWrkTMsg);
            if (!RTNCD_NORMAL.equals(ptyLocWrkTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_PTY_LOC_WRK });
                return;
            }
            // create account location
            saveAcctLoc(cMsg, sMsg, sellToCustTMsg.dsAcctTpCd.getValue());

            // create primary custmer
            // 2017/11/27 QC#20828 Mod Start
//            PRIN_CUSTTMsg prinCustTMsg = new PRIN_CUSTTMsg();
//            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustPk, sellToCustTMsg.prinCustPk);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.dbaNm, sellToCustTMsg.dbaNm);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.rgtnStsCd, sellToCustTMsg.rgtnStsCd);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.taxOrgClsCd, TAX_ORG_CLS.OTHERS);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.ptyLocPk, cMsg.ptyLocPk);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.locRoleTpCd, LOC_ROLE_TP.CUSTOMER_PRINCIPAL);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.locGrpCd, LOC_GRP.CUSTOMER);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.effFromDt, slsDt);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.cmpyPk, sellToCustTMsg.cmpyPk);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.bizRelnTpCd, sellToCustTMsg.bizRelnTpCd);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.coaAfflCd, sellToCustTMsg.coaAfflCd);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustTpCd, PRIN_CUST_TP.OTHER);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.locNm, getLocNm(cMsg));
//
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
//            ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsAcctNm, cMsg.dsAcctNm_H1);
//            S21FastTBLAccessor.create(prinCustTMsg);
//            if (!RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
//                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_PRIN_CUST });
//                return;
//            }
            if (!NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                boolean res = createPrinCust(cMsg, sellToCustTMsg);
                if (!res) {
                    return;
                }
            }
            // 2017/11/27 QC#20828 Mod End
            savePrinCustByAccountTab(cMsg, sMsg, sellToCustTMsg.dsAcctTpCd.getValue());
            clearPrimLocDsAdcctCust(cMsg, sMsg, this.getGlobalCompanyCode());

            cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            // 2017/11/27 QC#20828 Add Start
            if (NMAL6720CommonLogic.isMergeProsLocToCust(cMsg)) {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_H1, NMAL6720CommonLogic.getMergeToAcctCd(cMsg));
            }
            // 2017/11/27 QC#20828 Add End

            cMsg.setMessageInfo(NMAM8649I);
        }
    }

    // START 2017/06/29 J.Kim [QC#17670,ADD]
    /**
     * openTrnCheck
     * @param bizMsg NMAL6720CMsg
     * @param sharedMsg NMAL6720SMsg
     * @return boolean
     */
    private boolean openTrnCheck(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        boolean rtnFlg = true;

        // Add Start 2018/10/09 QC#27598
        boolean billToChkFlg = false;
        boolean shipToChkFlg = false;

        boolean actvFlgWrnFlg = false;
        boolean billToStartWrnFlg = false;
        boolean billToEndWrnFlg = false;
        boolean shipToStartWrnFlg = false;
        boolean shipToEndWrnFlg = false;
        boolean acctWrnFlg = false;

        if (!ZYPConstant.FLG_ON_1.equals(cMsg.xxPgFlg_L2.getValue())) {
            String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());

            // Check Active Flag
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue()) && //
                    ZYPConstant.FLG_ON_Y.equals(sMsg.actvFlg_H1.getValue())) {
                // Change Active Flag.
                if (ZYPCommonFunc.hasValue(cMsg.billToCustCd_BI)) {
                    billToChkFlg = true;
                }
                if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_SH)) {
                    shipToChkFlg = true;
                }
                actvFlgWrnFlg = true;
            }

            // Check Bill To
            if (ZYPCommonFunc.hasValue(cMsg.billToCustCd_BI)) {
                if (ZYPDateUtil.compare(salesDate, cMsg.effFromDt_BI.getValue()) < 0 && //
                        ZYPDateUtil.compare(salesDate, sMsg.effFromDt_BI.getValue()) >= 0) {
                    // Change start date to future from current or
                    // past.
                    billToChkFlg = true;
                    billToStartWrnFlg = true;
                }

                String oldEndDate = null;
                if (ZYPCommonFunc.hasValue(sMsg.effThruDt_BI)) {
                    oldEndDate = sMsg.effThruDt_BI.getValue();
                } else {
                    oldEndDate = MAX_DT;
                }

                String newEndDate = null;
                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
                    newEndDate = cMsg.effThruDt_BI.getValue();
                } else {
                    newEndDate = MAX_DT;
                }

                if (ZYPDateUtil.compare(salesDate, newEndDate) > 0 && //
                        ZYPDateUtil.compare(salesDate, oldEndDate) <= 0) {
                    // Change end date to past from current or
                    // future.
                    billToChkFlg = true;
                    billToEndWrnFlg = true;
                }
            }

            // Check Ship To
            if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_SH)) {
                if (ZYPDateUtil.compare(salesDate, cMsg.effFromDt_SH.getValue()) < 0 && //
                        ZYPDateUtil.compare(salesDate, sMsg.effFromDt_SH.getValue()) >= 0) {
                    // Change start date to future from current or
                    // past.
                    shipToChkFlg = true;
                    shipToStartWrnFlg = true;
                }

                String oldEndDate = null;
                if (ZYPCommonFunc.hasValue(sMsg.effThruDt_SH)) {
                    oldEndDate = sMsg.effThruDt_SH.getValue();
                } else {
                    oldEndDate = MAX_DT;
                }

                String newEndDate = null;
                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
                    newEndDate = cMsg.effThruDt_SH.getValue();
                } else {
                    newEndDate = MAX_DT;
                }

                if (ZYPDateUtil.compare(salesDate, newEndDate) > 0 && //
                        ZYPDateUtil.compare(salesDate, oldEndDate) <= 0) {
                    // Change end date to past from current or
                    // future.
                    shipToChkFlg = true;
                    shipToEndWrnFlg = true;
                }
            }

            String oldRelatedAcctNum = getCurrentAccount(cMsg);
            String newRelatedAcctNum = null;

            // Get NEW current Account Number.
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                NMAL6720_ACMsg acMsg = cMsg.A.no(i);
                if (ZYPDateUtil.compare(salesDate, acMsg.effFromDt_A1.getValue()) >= 0) {
                    if (ZYPCommonFunc.hasValue(acMsg.effThruDt_A1)) {
                        if (ZYPDateUtil.compare(salesDate, acMsg.effThruDt_A1.getValue()) <= 0) {
                            newRelatedAcctNum = acMsg.dsAcctNum_A1.getValue();
                            break;
                        }
                    } else {
                        newRelatedAcctNum = acMsg.dsAcctNum_A1.getValue();
                        break;
                    }
                }
            }

            if (oldRelatedAcctNum != null && !isEquals(newRelatedAcctNum, oldRelatedAcctNum)) {
                if (ZYPCommonFunc.hasValue(cMsg.billToCustCd_BI)) {
                    billToChkFlg = true;
                }
                if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_SH)) {
                    shipToChkFlg = true;
                }
                acctWrnFlg = true;
            }

            if (!actvFlgWrnFlg && !billToStartWrnFlg && !billToEndWrnFlg && !shipToStartWrnFlg && !shipToEndWrnFlg && !acctWrnFlg) {
                // No need to check.
                return rtnFlg;
            }

            // QC#57316 Mod Start
            if (!openTransactionCheck(cMsg, billToChkFlg, shipToChkFlg)) {
                if (actvFlgWrnFlg) {
                    cMsg.actvFlg_H1.setErrorInfo(1, NWAM0984E);
                }
                if (billToStartWrnFlg) {
                    cMsg.effFromDt_BI.setErrorInfo(1, NWAM0984E);
                }
                if (billToEndWrnFlg) {
                    cMsg.effThruDt_BI.setErrorInfo(1, NWAM0984E);
                }
                if (shipToStartWrnFlg) {
                    cMsg.effFromDt_SH.setErrorInfo(1, NWAM0984E);
                }
                if (shipToEndWrnFlg) {
                    cMsg.effThruDt_SH.setErrorInfo(1, NWAM0984E);
                }

                if (acctWrnFlg) {
                    for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                        NMAL6720_ACMsg acMsg = cMsg.A.no(i);

                        if (!isEquals(oldRelatedAcctNum, acMsg.dsAcctNum_A1.getValue())) {
                            continue;
                        }

                        if (ZYPDateUtil.compare(salesDate, acMsg.effFromDt_A1.getValue()) < 0) {
                            acMsg.effFromDt_A1.setErrorInfo(1, NWAM0984E);
                        }

                        if (ZYPCommonFunc.hasValue(acMsg.effThruDt_A1) && //
                                ZYPDateUtil.compare(salesDate, acMsg.effThruDt_A1.getValue()) > 0) {
                            acMsg.effThruDt_A1.setErrorInfo(1, NWAM0984E);
                        }
                    }
                }

                cMsg.setMessageInfo(NWAM0984E);
                rtnFlg = false;
//                if (actvFlgWrnFlg) {
//                    cMsg.actvFlg_H1.setErrorInfo(2, NMAM8692W);
//                }
//                if (billToStartWrnFlg) {
//                    cMsg.effFromDt_BI.setErrorInfo(2, NMAM8692W);
//                }
//                if (billToEndWrnFlg) {
//                    cMsg.effThruDt_BI.setErrorInfo(2, NMAM8692W);
//                }
//                if (shipToStartWrnFlg) {
//                    cMsg.effFromDt_SH.setErrorInfo(2, NMAM8692W);
//                }
//                if (shipToEndWrnFlg) {
//                    cMsg.effThruDt_SH.setErrorInfo(2, NMAM8692W);
//                }
//
//                if (acctWrnFlg) {
//                    for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//                        NMAL6720_ACMsg acMsg = cMsg.A.no(i);
//
//                        if (!isEquals(oldRelatedAcctNum, acMsg.dsAcctNum_A1.getValue())) {
//                            continue;
//                        }
//
//                        if (ZYPDateUtil.compare(salesDate, acMsg.effFromDt_A1.getValue()) < 0) {
//                            acMsg.effFromDt_A1.setErrorInfo(2, NMAM8692W);
//                        }
//
//                        if (ZYPCommonFunc.hasValue(acMsg.effThruDt_A1) && //
//                                ZYPDateUtil.compare(salesDate, acMsg.effThruDt_A1.getValue()) > 0) {
//                            acMsg.effThruDt_A1.setErrorInfo(2, NMAM8692W);
//                        }
//                    }
//                }
//
//                cMsg.setMessageInfo(NMAM8692W);
//                cMsg.xxPgFlg_L2.setValue(ZYPConstant.FLG_ON_1);
//                rtnFlg = false;
            }
            // QC#57316 End
        } else {
            cMsg.xxPgFlg_L2.setValue(ZYPConstant.FLG_OFF_0);
        }
        // Add End 2018/10/09 QC#27598

        // Del Start 2018/10/09 QC#27598
//        boolean exeFlg = false;
//
//        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
//            exeFlg = true;
//        }
//
//        // Bill To
//        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
//            if (!isEquals(cMsg.effThruDt_BI.getValue(), sMsg.effThruDt_BI.getValue())) {
//                exeFlg = true;
//            }
//        }
//
//        // Ship To
//        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
//            if (!isEquals(cMsg.effThruDt_SH.getValue(), sMsg.effThruDt_SH.getValue())) {
//                exeFlg = true;
//            }
//        }
//
//        if (exeFlg) {
//            if (!openTransactionCheck(cMsg)) {
//                if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
//                    cMsg.actvFlg_H1.setErrorInfo(1, NMAM8374E);
//                    rtnFlg = false;
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_BI)) {
//                    cMsg.effThruDt_BI.setErrorInfo(1, NMAM8374E);
//                    rtnFlg = false;
//                }
//                if (ZYPCommonFunc.hasValue(cMsg.effThruDt_SH)) {
//                    cMsg.effThruDt_SH.setErrorInfo(1, NMAM8374E);
//                    rtnFlg = false;
//                }
//            }
//        }
//
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//            NMAL6720_ACMsg acMsg = cMsg.A.no(i);
//            NMAL6720_ASMsg asMsg = sMsg.A.no(i);
//            if (ZYPCommonFunc.hasValue(acMsg.effThruDt_A1)) {
//                if (ZYPCommonFunc.hasValue(asMsg.acctLocPk_A1) && isEquals(asMsg.effFromDt_A1.getValue(), asMsg.effFromDt_AB.getValue()) 
//                        && isEquals(asMsg.effThruDt_A1.getValue(), asMsg.effThruDt_AB.getValue())) {
//                    continue;
//                }
//                if (!openTransactionCheck(cMsg)) {
//                    acMsg.effThruDt_A1.setErrorInfo(1, NMAM8374E);
//                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_ACCOUNT);
//                    rtnFlg = false;
//                }
//            }
//        }
        // Del End 2018/10/09 QC#27598

        return rtnFlg;
    }

    // Add Start 2018/10/05 QC#28510
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
    // Add End 2018/10/05 QC#28510

    // Mod Start 2018/10/09 QC#27598
    // Mod End 2018/10/09 QC#27598
   ///**
   //  * openTransactionCheck
   //  * @param bizMsg NMAL6720CMsg
   //  * @param sharedMsg NMAL6720SMsg
   //  * @return boolean
   //  */
   // private boolean openTransactionCheck(NMAL6720CMsg cMsg) {
    /**
     * openTransactionCheck
     * @param cMsg NMAL6720CMsg
     * @param billToChkFlg boolean
     * @param shipToChkFlg boolean
     * @return boolean
     */
    private boolean openTransactionCheck(NMAL6720CMsg cMsg, boolean billToChkFlg, boolean shipToChkFlg) {
        // Mod End 2018/10/09 QC#27598

        String glblCmpyCd = getGlobalCompanyCode();
        String locNum = cMsg.locNum_H1.getValue();
        // Add Start 2018/10/09 QC#27598
        String dsAcctNum = cMsg.dsAcctNum_H1.getValue();

        String billToCd = null;
        String shipToCd = null;
        // 2021/05/20 QC#58743 Add Start
        String billToRgtnStsCd = null;
        String shipToRgtnStsCd = null;
        // 2021/05/20 QC#58743 Add Start

        if (billToChkFlg) {
            billToCd = cMsg.billToCustCd_BI.getValue();
            // 2021/05/20 QC#58743 Add Start
            billToRgtnStsCd = cMsg.rgtnStsCd_BI.getValue();
            // 2021/05/20 QC#58743 Add Start
        }

        if (shipToChkFlg) {
            shipToCd = cMsg.shipToCustCd_SH.getValue();
            // 2021/05/20 QC#58743 Add Start
            shipToRgtnStsCd = cMsg.rgtnStsCd_SH.getValue();
            // 2021/05/20 QC#58743 Add Start
        }
        // Add End 2018/10/09 QC#27598

        // 2019/12/13 QC#54882 Mod Start
//        // Mod Start 2018/10/09 QC#27598
//        //S21SsmEZDResult result = NMAL6720Query.getInstance().countCpoOpenOrder(locNum, glblCmpyCd);
//        S21SsmEZDResult result = NMAL6720Query.getInstance().countCpoOpenOrder(glblCmpyCd, dsAcctNum, locNum, billToCd, shipToCd);
//        // Mod End 2018/10/09 QC#27598
//        BigDecimal count = BigDecimal.ZERO;
//        if (!result.isCodeNotFound()) {
//            count = (BigDecimal) result.getResultObject();
//        }
//
//        if (BigDecimal.ONE.compareTo(count) == 0) {
//            return false;
//        }
//
//        result = null;
//        // Mod Start 2018/10/09 QC#27598
//        //result = NMAL6720Query.getInstance().countOpenReturnOrder(locNum, glblCmpyCd);
//        result = NMAL6720Query.getInstance().countOpenReturnOrder(glblCmpyCd, dsAcctNum, locNum, billToCd, shipToCd);
//        // Mod End 2018/10/09 QC#27598
//        count = BigDecimal.ZERO;
//        if (!result.isCodeNotFound()) {
//            count = (BigDecimal) result.getResultObject();
//        }
//
//        if (BigDecimal.ONE.compareTo(count) == 0) {
//            return false;
//        }
//
//        result = null;
//        // Mod Start 2018/10/09 QC#27598
//        //result = NMAL6720Query.getInstance().countOpenMachineMasterCheck(locNum, glblCmpyCd);
//        result = NMAL6720Query.getInstance().countOpenMachineMasterCheck(glblCmpyCd, dsAcctNum, locNum, billToCd, shipToCd);
//        // Mod End 2018/10/09 QC#27598
//        count = BigDecimal.ZERO;
//        if (!result.isCodeNotFound()) {
//            count = (BigDecimal) result.getResultObject();
//        }
//
//        if (BigDecimal.ONE.compareTo(count) == 0) {
//            return false;
//        }

        // 2021/05/20 QC#58743 Mod Start
//        S21SsmEZDResult result = NMAL6720Query.getInstance().countOpenTransaction(glblCmpyCd, dsAcctNum, locNum, billToCd, shipToCd);
        S21SsmEZDResult result = NMAL6720Query.getInstance().countOpenTransaction(glblCmpyCd, dsAcctNum, locNum, billToCd, shipToCd, billToRgtnStsCd, shipToRgtnStsCd);
        // 2021/05/20 QC#58743 Mod End
        BigDecimal count = BigDecimal.ZERO;
        if (!result.isCodeNotFound()) {
            count = (BigDecimal) result.getResultObject();
        }
        if (BigDecimal.ONE.compareTo(count) == 0) {
            return false;
        }
        // 2019/12/13 QC#54882 Mod End

        return true;
    }
    // END 2017/06/29 J.Kim [QC#17670,ADD]
    // 2017/11/27 QC#20828 Add Start
    private boolean createPrinCust(NMAL6720CMsg cMsg, SELL_TO_CUSTTMsg sellToCustTMsg) {

        String slsDt = ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode());

        PRIN_CUSTTMsg prinCustTMsg = new PRIN_CUSTTMsg();
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustPk, sellToCustTMsg.prinCustPk);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dbaNm, sellToCustTMsg.dbaNm);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.rgtnStsCd, sellToCustTMsg.rgtnStsCd);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.taxOrgClsCd, TAX_ORG_CLS.OTHERS);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.ptyLocPk, cMsg.ptyLocPk);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.locRoleTpCd, LOC_ROLE_TP.CUSTOMER_PRINCIPAL);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.locGrpCd, LOC_GRP.CUSTOMER);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.effFromDt, slsDt);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.cmpyPk, sellToCustTMsg.cmpyPk);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.bizRelnTpCd, sellToCustTMsg.bizRelnTpCd);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.coaAfflCd, sellToCustTMsg.coaAfflCd);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.prinCustTpCd, PRIN_CUST_TP.OTHER);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.locNm, getLocNm(cMsg));
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.embgoFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsInsdCtyLimitFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prinCustTMsg.dsAcctNm, cMsg.dsAcctNm_H1);

        S21FastTBLAccessor.create(prinCustTMsg);
        if (!RTNCD_NORMAL.equals(prinCustTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_PRIN_CUST });
            return false;
        }

        return true;
    }
    // 2017/11/27 QC#20828 Add End

    // 2018/02/19 QC#20297(Sol#379) add start
    private boolean checkShippingTabMandatory(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        NMAL6720CommonLogic.copyCurrentPageToSMsg(cMsg.I, sMsg.I, cMsg.xxPageShowFromNum_I);

        // 2023/01/13 QC#60860 Add Start
        sMsg = NMAL6720CommonLogic.vndCdHasValue(getGlobalCompanyCode(), sMsg);
        // 2023/01/13 QC#60860 Add End

        boolean errorFlg = true;
        NMAL6720_ISMsg iSMsg;
        int errIdx = 0;
        for (int i = 0; i < sMsg.I.getValidCount(); i++) {
            iSMsg = sMsg.I.no(i);
            if (!ZYPCommonFunc.hasValue(iSMsg.lineBizTpCd_P3)) {
                iSMsg.lineBizTpCd_P3.setErrorInfo(1, ZZZM9025E, new String[] {"Line Of Business" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
            if (!ZYPCommonFunc.hasValue(iSMsg.dsBizAreaCd_P2)) {
                iSMsg.dsBizAreaCd_P2.setErrorInfo(1, ZZZM9025E, new String[] {"Business Area" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
            // 2018/12/13 QC#29315 Add Start
            if (!ZYPCommonFunc.hasValue(iSMsg.effFromDt_I1)) {
                iSMsg.effFromDt_I1.setErrorInfo(1, ZZZM9025E, new String[] {"Start Date" });
                errorFlg = false;
                if (errIdx == 0) {
                    errIdx = i;
                }
            }
            if ((ZYPCommonFunc.hasValue(iSMsg.dsCarrAcctNum_I1) && !ZYPCommonFunc.hasValue(iSMsg.vndCd_I3))) {
            // 2023/01/13 QC#60860 Mod Start
//                iSMsg.vndCd_I3.setErrorInfo(1, ZZIM0094E, new String[] {"If Account Number is entered, Carrier" });
                iSMsg.carrNm_I3.setErrorInfo(1, ZZIM0094E, new String[] {"If Account Number is entered, Carrier" });
            // 2023/01/13 QC#60860 Mod Start
                errorFlg = false;
            }
            // 2018/12/13 QC#29315 Add End
            // 2023/01/13 QC#60860 Add Start
            if ((ZYPCommonFunc.hasValue(iSMsg.carrNm_I3) && !ZYPCommonFunc.hasValue(iSMsg.vndCd_I3))) {
                iSMsg.carrNm_I3.setErrorInfo(1, NMAM8121E, new String[] {"Carrier" });
                errorFlg = false;
            }
            // 2023/01/13 QC#60860 Add End
        }
        if (!errorFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SHIPPING);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, errIdx);
        }

        return errorFlg;
    }
    // 2018/12/13 QC#29315 Mod Start
//    private void saveDsCustShpg(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        private void saveDsCustShpgAndDsAcctCarr(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
    // 2018/12/13 QC#29315 Mod End
        String glblCmpyCd = getGlobalCompanyCode();
        NMAL6720_ISMsg iSMsg;
        for (int i = 0; i < sMsg.I.getValidCount(); i++) {
            iSMsg = sMsg.I.no(i);
            // 2018/12/13 QC#29315 Mod Start
//            DS_CUST_SHPG_DEFTMsg dsCustShpgTMsg = new DS_CUST_SHPG_DEFTMsg();
//            ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.glblCmpyCd, glblCmpyCd);
//
//            if (ZYPCommonFunc.hasValue(iSMsg.dsCustShpgDefPk_I1)) {
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsCustShpgDefPk, iSMsg.dsCustShpgDefPk_I1.getValue());
//
//                dsCustShpgTMsg = (DS_CUST_SHPG_DEFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCustShpgTMsg);
//                if (dsCustShpgTMsg == null || !RTNCD_NORMAL.equals(dsCustShpgTMsg.getReturnCode())) {
//                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_SHPG_DEF });
//                    return;
//                }
//
//                if (!ZYPDateUtil.isSameTimeStamp(iSMsg.ezUpTime_I1.getValue(), iSMsg.ezUpTimeZone_I1.getValue(), dsCustShpgTMsg.ezUpTime.getValue(), dsCustShpgTMsg.ezUpTimeZone.getValue())) {
//                    cMsg.setMessageInfo(NZZM0003E);
//                    return;
//                }
//
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.lineBizTpCd, iSMsg.lineBizTpCd_P3.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsBizAreaCd, iSMsg.dsBizAreaCd_P2.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.frtCondCd, iSMsg.frtCondCd_P1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.shpgSvcLvlCd, iSMsg.shpgSvcLvlCd_P1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effThruDt, iSMsg.effThruDt_I1.getValue());
//
//                S21FastTBLAccessor.update(dsCustShpgTMsg);
//                if (!RTNCD_NORMAL.equals(dsCustShpgTMsg.getReturnCode())) {
//                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_SHPG_DEF });
//                    return;
//                }
//            } else {
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsCustShpgDefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_SHPG_DEF_SQ));
//
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.locNum, cMsg.locNum_H1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.lineBizTpCd, iSMsg.lineBizTpCd_P3.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsBizAreaCd, iSMsg.dsBizAreaCd_P2.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.frtCondCd, iSMsg.frtCondCd_P1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.shpgSvcLvlCd, iSMsg.shpgSvcLvlCd_P1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effFromDt, ZYPDateUtil.getSalesDate());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effThruDt, iSMsg.effThruDt_I1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.custEffLvlCd, CUST_EFF_LVL.LOCATION_ONLY);
//                S21FastTBLAccessor.insert(dsCustShpgTMsg);
//                if (!RTNCD_NORMAL.equals(dsCustShpgTMsg.getReturnCode())) {
//                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_CUST_SHPG_DEF });
//                    return;
//                }
//            }
            if (!ZYPCommonFunc.hasValue(iSMsg.xxChkBox_ID)) {
                ZYPEZDItemValueSetter.setValue(iSMsg.xxChkBox_ID, ZYPConstant.FLG_OFF_N);
            }

            DS_CUST_SHPG_DEFTMsg dsCustShpgTMsg = new DS_CUST_SHPG_DEFTMsg();
            DS_ACCT_CARRTMsg dsAcctCarrTMsg = new DS_ACCT_CARRTMsg();
            ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(iSMsg.dsCustShpgDefPk_I1) && ZYPCommonFunc.hasValue(iSMsg.dsAcctCarrPk_I1)) {
                // update
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsCustShpgDefPk, iSMsg.dsCustShpgDefPk_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctCarrPk, iSMsg.dsAcctCarrPk_I1.getValue());

                dsCustShpgTMsg = (DS_CUST_SHPG_DEFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCustShpgTMsg);
                if (dsCustShpgTMsg == null || !RTNCD_NORMAL.equals(dsCustShpgTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_SHPG_DEF });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(iSMsg.ezUpTime_IS.getValue(), iSMsg.ezUpTimeZone_IS.getValue(), dsCustShpgTMsg.ezUpTime.getValue(), dsCustShpgTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                dsAcctCarrTMsg = (DS_ACCT_CARRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsAcctCarrTMsg);
                if (dsAcctCarrTMsg == null || !RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_ACCT_CARR });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(iSMsg.ezUpTime_IC.getValue(), iSMsg.ezUpTimeZone_IC.getValue(), dsAcctCarrTMsg.ezUpTime.getValue(), dsAcctCarrTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.lineBizTpCd, iSMsg.lineBizTpCd_P3.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsBizAreaCd, iSMsg.dsBizAreaCd_P2.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.frtCondCd, iSMsg.frtCondCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.shpgSvcLvlCd, iSMsg.shpgSvcLvlCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effFromDt, iSMsg.effFromDt_I1.getValue());;
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effThruDt, iSMsg.effThruDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.defFlg, iSMsg.xxChkBox_ID.getValue());
                
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.vndCd, iSMsg.vndCd_I3.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsCarrAcctNum, iSMsg.dsCarrAcctNum_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effFromDt, iSMsg.effFromDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effThruDt, iSMsg.effThruDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.defAcctCarrFlg, iSMsg.xxChkBox_ID.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.lineBizTpCd, iSMsg.lineBizTpCd_P3.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsBizAreaCd, iSMsg.dsBizAreaCd_P2.getValue());

                S21FastTBLAccessor.update(dsCustShpgTMsg);
                if (!RTNCD_NORMAL.equals(dsCustShpgTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_SHPG_DEF });
                    return;
                }
                S21FastTBLAccessor.update(dsAcctCarrTMsg);
                if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_ACCT_CARR });
                    return;
                }
            } else {
                // create
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsCustShpgDefPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CUST_SHPG_DEF_SQ));
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsAcctCarrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BANK_ACCT_SQ));

                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.lineBizTpCd, iSMsg.lineBizTpCd_P3.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsBizAreaCd, iSMsg.dsBizAreaCd_P2.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.frtCondCd, iSMsg.frtCondCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.shpgSvcLvlCd, iSMsg.shpgSvcLvlCd_P1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effFromDt, iSMsg.effFromDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.effThruDt, iSMsg.effThruDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.custEffLvlCd, CUST_EFF_LVL.LOCATION_ONLY);
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.defFlg, iSMsg.xxChkBox_ID.getValue());
                ZYPEZDItemValueSetter.setValue(dsCustShpgTMsg.dsAcctCarrPk, dsAcctCarrTMsg.dsAcctCarrPk.getValue());

                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.vndCd, iSMsg.vndCd_I3.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsCarrAcctNum, iSMsg.dsCarrAcctNum_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effFromDt, iSMsg.effFromDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.effThruDt, iSMsg.effThruDt_I1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.defAcctCarrFlg, iSMsg.xxChkBox_ID.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.locNum, cMsg.locNum_H1.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.lineBizTpCd, iSMsg.lineBizTpCd_P3.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.dsBizAreaCd, iSMsg.dsBizAreaCd_P2.getValue());
                ZYPEZDItemValueSetter.setValue(dsAcctCarrTMsg.custEffLvlCd, CUST_EFF_LVL.LOCATION_ONLY);

                S21FastTBLAccessor.insert(dsCustShpgTMsg);
                S21FastTBLAccessor.insert(dsAcctCarrTMsg);
                if (!RTNCD_NORMAL.equals(dsCustShpgTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_CUST_SHPG_DEF });
                    return;
                }
                if (!RTNCD_NORMAL.equals(dsAcctCarrTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_DS_ACCT_CARR });
                    return;
                }
            }
         // 2018/12/13 QC#29315 Mod End
        }
        // 2018/12/13 QC#29315 Mod Start
//        int len = sMsg.J.getValidCount();
//        if (len > 0) {
//            DS_CUST_SHPG_DEFTMsg[] delArr = new DS_CUST_SHPG_DEFTMsg[len];
//            int cnt = 0;
//
//            for (int i = 0; i < len; i++) {
//                DS_CUST_SHPG_DEFTMsg delTMsg = new DS_CUST_SHPG_DEFTMsg();
//                ZYPEZDItemValueSetter.setValue(delTMsg.glblCmpyCd, glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(delTMsg.dsCustShpgDefPk, sMsg.J.no(i).dsCustShpgDefPk_J1);
//
//                delTMsg = (DS_CUST_SHPG_DEFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg);
//
//                if (delTMsg == null || !RTNCD_NORMAL.equals(delTMsg.getReturnCode())) {
//                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_SHPG_DEF });
//                    return;
//                }
//                delArr[cnt] = delTMsg;
//                cnt++;
//            }
//
//            int result = S21FastTBLAccessor.removeLogical(delArr);
//            if (result != len) {
//                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_SHPG_DEF });
//                return;
//            }
//        }
        int len = sMsg.J.getValidCount();
        if (len > 0) {
            DS_CUST_SHPG_DEFTMsg[] delArr1 = new DS_CUST_SHPG_DEFTMsg[len];
            DS_ACCT_CARRTMsg[] delArr2 = new DS_ACCT_CARRTMsg[len];

            int cnt = 0;

            for (int i = 0; i < len; i++) {
                DS_CUST_SHPG_DEFTMsg delTMsg1 = new DS_CUST_SHPG_DEFTMsg();
                DS_ACCT_CARRTMsg delTMsg2 = new DS_ACCT_CARRTMsg();
                ZYPEZDItemValueSetter.setValue(delTMsg1.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(delTMsg1.dsCustShpgDefPk, sMsg.J.no(i).dsCustShpgDefPk_J1);
                ZYPEZDItemValueSetter.setValue(delTMsg2.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(delTMsg2.dsAcctCarrPk, sMsg.J.no(i).dsAcctCarrPk_J1);

                delTMsg1 = (DS_CUST_SHPG_DEFTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg1);
                delTMsg2 = (DS_ACCT_CARRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(delTMsg2);

                if (delTMsg1 == null || !RTNCD_NORMAL.equals(delTMsg1.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_CUST_SHPG_DEF });
                    return;
                }

                if (delTMsg2 == null || !RTNCD_NORMAL.equals(delTMsg2.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {MSG_DS_ACCT_CARR });
                    return;
                }
                delArr1[cnt] = delTMsg1;
                delArr2[cnt] = delTMsg2;
                cnt++;
            }

            int result1 = S21FastTBLAccessor.removeLogical(delArr1);
            int result2 = S21FastTBLAccessor.removeLogical(delArr2);
            if (result1 != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_CUST_SHPG_DEF });
                return;
            } else if (result2 != len) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_DS_ACCT_CARR });
                return;
            }
        }
        // 2018/12/13 QC#29315 Mod End
    }
    // 2018/12/13 QC#29315 Mod Start
//    private boolean checkShippingTabDuplicate(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
    private boolean checkShippingTab(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
    // 2018/12/13 QC#29315 Mod End

        boolean errorFlg = true;
        int errIdx = 0;
        String salesDate = ZYPDateUtil.getSalesDate(getGlobalCompanyCode());
        // 2018/12/10 QC#29315 Add Start
        List<ArrayList> carrCombList = new ArrayList<ArrayList>();
        // 2018/12/10 QC#29315 Add End
        // 2019/01/19 QC#29940 Add Start
        Map<BigDecimal, String> beforeEffThruDtMap = new TreeMap<BigDecimal, String>();
        for (int i = 0; i < sMsg.K.getValidCount(); i++) {
            beforeEffThruDtMap.put(sMsg.K.no(i).dsCustShpgDefPk_I1.getValue(), sMsg.K.no(i).effThruDt_I1.getValue());
        }
        // 2019/01/19 QC#29940 Add End
        for (int i = 0; i < sMsg.I.getValidCount(); i++) {
            NMAL6720_ISMsg iSMsg = sMsg.I.no(i);
            // 2018/12/13 QC#29315 Mod Start
//            if (ZYPCommonFunc.hasValue(iSMsg.effThruDt_I1) && ZYPDateUtil.compare(salesDate, iSMsg.effThruDt_I1.getValue()) >= 0) {
//                continue;
//            }
            String effThruDt = iSMsg.effThruDt_I1.getValue();
            String effFromDt = iSMsg.effFromDt_I1.getValue();
            // 2019/01/19 QC#29940 Mod Start
            String beforeEffThruDt = null;
            if (ZYPCommonFunc.hasValue(iSMsg.dsCustShpgDefPk_I1.getValue())) {
                beforeEffThruDt = beforeEffThruDtMap.get(iSMsg.dsCustShpgDefPk_I1.getValue());
            }
            if (ZYPCommonFunc.hasValue(salesDate) && ZYPCommonFunc.hasValue(effThruDt) && !ZYPCommonFunc.hasValue(beforeEffThruDt) && salesDate.compareTo(effThruDt) > 0 
                    || ZYPCommonFunc.hasValue(salesDate) && ZYPCommonFunc.hasValue(effThruDt) && !effThruDt.equals(beforeEffThruDt) && salesDate.compareTo(effThruDt) > 0) {
            //if (ZYPCommonFunc.hasValue(salesDate) && ZYPCommonFunc.hasValue(effThruDt) && salesDate.compareTo(effThruDt) > 0) {
                // 2019/01/19 QC#29940 Mod End
                String[] args = {"End Date" };
                iSMsg.effThruDt_I1.setErrorInfo(1, NMAM8200E, args);
                errorFlg = false;
            }
            if (ZYPCommonFunc.hasValue(effThruDt) && ZYPCommonFunc.hasValue(effFromDt)) {
                if (effFromDt.compareTo(effThruDt) > 0) {
                    String[] args = {"End Date" };
                    iSMsg.effThruDt_I1.setErrorInfo(1, NMAM8200E, args);
                    errorFlg = false;
                }
            }
            // 2018/12/13 QC#29315 Mod End
            for (int j = 0; j < sMsg.I.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }
                NMAL6720_ISMsg iSMsg2 = sMsg.I.no(j);

                // 2018/12/13 QC#29315 Del Start
//                if (ZYPCommonFunc.hasValue(iSMsg2.effThruDt_I1) && ZYPDateUtil.compare(salesDate, iSMsg2.effThruDt_I1.getValue()) >= 0) {
//                    continue;
//                }
                // 2018/12/13 QC#29315 Del End
                // 2018/12/13 QC#29315 Mod Start
//                if (isEquals(iSMsg.lineBizTpCd_P3.getValue(), iSMsg2.lineBizTpCd_P3.getValue()) //
//                        && isEquals(iSMsg.dsBizAreaCd_P2.getValue(), iSMsg2.dsBizAreaCd_P2.getValue())) {
//                    iSMsg.effThruDt_I1.setErrorInfo(1, NMAM0072E, new String[] {"Line Of Business and Business Area" });
//                    errorFlg = false;
//                    if (errIdx == 0) {
//                        errIdx = i;
//                    }
                    // check effective date duplication
                if (!isNotEquals(iSMsg.lineBizTpCd_P3.getValue(), iSMsg2.lineBizTpCd_P3.getValue()) //
                        && !isNotEquals(iSMsg.dsBizAreaCd_P2.getValue(), iSMsg2.dsBizAreaCd_P2.getValue()) //
                        && !isNotEquals(iSMsg.vndCd_I3.getValue(), iSMsg2.vndCd_I3.getValue())) {

                    if (ZYPDateUtil.compare(iSMsg.effFromDt_I1.getValue(), iSMsg2.effFromDt_I1.getValue()) >= 0 && ZYPDateUtil.compare(iSMsg.effFromDt_I1.getValue(), iSMsg2.effThruDt_I1.getValue()) <= 0) {
                        String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                        iSMsg.effFromDt_I1.setErrorInfo(1, NMAM8178E, args);
                        iSMsg.effThruDt_I1.setErrorInfo(1, NMAM8178E, args);
                        errorFlg = false;
                    } else if (!(ZYPCommonFunc.hasValue(iSMsg2.effThruDt_I1))) {
                        if (ZYPDateUtil.compare(iSMsg.effFromDt_I1.getValue(), iSMsg2.effFromDt_I1.getValue()) >= 0) {
                            String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                            iSMsg.effFromDt_I1.setErrorInfo(1, NMAM8178E, args);
                            iSMsg.effThruDt_I1.setErrorInfo(1, NMAM8178E, args);
                            errorFlg = false;
                        }
                    }
                    if (ZYPDateUtil.compare(iSMsg.effThruDt_I1.getValue(), iSMsg2.effFromDt_I1.getValue()) >= 0 && ZYPDateUtil.compare(iSMsg.effThruDt_I1.getValue(), iSMsg2.effThruDt_I1.getValue()) <= 0) {
                        String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                        iSMsg.effFromDt_I1.setErrorInfo(1, NMAM8178E, args);
                        iSMsg.effThruDt_I1.setErrorInfo(1, NMAM8178E, args);
                        errorFlg = false;
                    } else if (!(ZYPCommonFunc.hasValue(iSMsg.effThruDt_I1))) {
                        if (ZYPDateUtil.compare(iSMsg.effFromDt_I1.getValue(), iSMsg2.effFromDt_I1.getValue()) <= 0) {
                            String[] args = {"the same combination of Line of Business, Business Area and Carrier", "different effective date" };
                            iSMsg.effFromDt_I1.setErrorInfo(1, NMAM8178E, args);
                            iSMsg.effThruDt_I1.setErrorInfo(1, NMAM8178E, args);
                            errorFlg = false;
                        }
                    }
                    // 2018/12/13 QC#29315 Mod End
                }
            }
            // 2018/12/13 QC#29315 Add Start
            // update carrCombList for check
            setCarrCombList(iSMsg, carrCombList, i);
            // 2018/12/13 QC#29315 Add End
        }
        // 2018/12/13 QC#29315 Add Start
        // errcheck for defflg
        int errRowNum = 0;
        for (List<String> defFlgList : carrCombList) {
            if ("0".equals(defFlgList.get(2))) {
                for (int k = 3; k < defFlgList.size(); k++) {
                    errRowNum = Integer.parseInt(defFlgList.get(k));
                    sMsg.I.no(errRowNum).xxChkBox_ID.setErrorInfo(1, NMAM8372E);
                }
                errorFlg = false;
                break;
            }
            if (Integer.parseInt(defFlgList.get(2)) > 1) {
                for (int k = 3; k < defFlgList.size(); k++) {
                    errRowNum = Integer.parseInt(defFlgList.get(k));
                    String[] args = {"Only one defalut carrier can be selected at the same combination of LOB and Business Area" };
                    sMsg.I.no(errRowNum).xxChkBox_ID.setErrorInfo(1, NMAM0177E, args);
                }
                errorFlg = false;
                break;
            }
        }
        // 2018/12/13 QC#29315 Add End

        if (!errorFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_SHIPPING);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_SHIPPING, errIdx);
        }

        return errorFlg;
    }
    private boolean checkMsgNoteTabBizArea(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {

        boolean errorFlg = true;
        int errIdx = 0;
        for (int i = 0; i < sMsg.E.getValidCount(); i++) {
            NMAL6720_ESMsg eSMsg = sMsg.E.no(i);
            if (DS_BIZ_AREA.PARTS.equals(eSMsg.dsBizAreaCd_P1.getValue())) {
                if (!DS_CUST_MSG_TP.SHIP.equals(eSMsg.dsCustMsgTpCd_P1.getValue())) {
                    eSMsg.dsCustMsgTpCd_P1.setErrorInfo(1, NAMM0016E, new String[] {"PARTS", "SHIP" });
                    errorFlg = false;
                    if (errIdx == 0) {
                        errIdx = i;
                    }
                }
            }
        }

        if (!errorFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyTab, TAB_MSG_NOTE);
            NMAL6720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg, TAB_MSG_NOTE, errIdx);
        }

        return errorFlg;
    }
    // 2018/02/19 QC#20297(Sol#379) add start
    
    // 2018/04/16 QC#24635 add start
    private DEF_DPLY_COA_INFOTMsg getShipToDefCoa(NMAL6720CMsg msg) {
        DEF_DPLY_COA_INFOTMsg defTMsg = new DEF_DPLY_COA_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(defTMsg.appFuncId, "NMAL6740001");
        ZYPEZDItemValueSetter.setValue(defTMsg.glblCmpyCd, this.getGlobalCompanyCode());

        defTMsg = (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(defTMsg);
        return defTMsg;
    }
    // 2018/04/16 QC#24635 add end
    // 2018/12/13 QC#29315 Add Start
    private boolean isNotEquals(String orig, String backUp) {
        if (!nvl(orig.replaceAll("\r", "")).equals(nvl(backUp.replaceAll("\r", "")))) {
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

    private List<ArrayList> setCarrCombList(NMAL6720_ISMsg iSMsg, List<ArrayList> carrCombList, int rowNum) {
        int defFlgCnt;
        int matchCnt = 0;
        List<String> defFlagList = new ArrayList<String>();
        for (int i = 0; i < carrCombList.size(); i++) {
            defFlagList = (List<String>) carrCombList.get(i);
            if (!isNotEquals(defFlagList.get(0), iSMsg.lineBizTpCd_P3.getValue()) //
                    && !isNotEquals(defFlagList.get(1), iSMsg.dsBizAreaCd_P2.getValue())) {
                matchCnt++;
                defFlagList.add(String.valueOf(rowNum));
                if (ZYPCommonFunc.hasValue(iSMsg.xxChkBox_ID) && ZYPConstant.FLG_ON_Y.equals(iSMsg.xxChkBox_ID.getValue())) {
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
            defFlagNewList.add(0, iSMsg.lineBizTpCd_P3.getValue());
            defFlagNewList.add(1, iSMsg.dsBizAreaCd_P2.getValue());
            if (ZYPCommonFunc.hasValue(iSMsg.xxChkBox_ID) && ZYPConstant.FLG_ON_Y.equals(iSMsg.xxChkBox_ID.getValue())) {
                defFlagNewList.add(2, "1");
            } else {
                defFlagNewList.add(2, "0");
            }
            defFlagNewList.add(3, String.valueOf(rowNum));
            carrCombList.add((ArrayList) defFlagNewList);
        }
        return carrCombList;
    }
    // 2018/12/13 QC#29315 Add End

    // 2020/03/24 QC#55673 Add Start
    private void saveRtlWh(NMAL6720CMsg cMsg, NMAL6720SMsg sMsg) {
        if (S21StringUtil.isEquals(cMsg.addlLocNm.getValue(), sMsg.addlLocNm.getValue())) {
            return;
        }

        RTL_WHTMsg inTMsg = new RTL_WHTMsg();
        inTMsg.setSQLID("003");
        inTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inTMsg.setConditionValue("shipToCustCd01", cMsg.shipToCustCd_SH.getValue());
        RTL_WHTMsgArray tMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inTMsg);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            RTL_WHTMsg updtTMsg = (RTL_WHTMsg) tMsgArray.get(i);

            ZYPEZDItemValueSetter.setValue(updtTMsg.addlLocNm, cMsg.addlLocNm);
 
            S21FastTBLAccessor.update(updtTMsg);
            if (!RTNCD_NORMAL.equals(updtTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_RTL_WH });
                return;
            }
        }

        RTL_WHTMsg rtrnInTMsg =  new RTL_WHTMsg();
        rtrnInTMsg.setSQLID("004");
        rtrnInTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        rtrnInTMsg.setConditionValue("rtrnToCustCd01", cMsg.shipToCustCd_SH.getValue());
        RTL_WHTMsgArray rtrnTMsgArray = (RTL_WHTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(rtrnInTMsg);

        for (int i = 0; i < rtrnTMsgArray.getValidCount(); i++) {
            RTL_WHTMsg rtrnUpdtTMsg = (RTL_WHTMsg) rtrnTMsgArray.get(i);

            ZYPEZDItemValueSetter.setValue(rtrnUpdtTMsg.rtrnToAddlLocNm, cMsg.addlLocNm);

            S21FastTBLAccessor.update(rtrnUpdtTMsg);
            if (!RTNCD_NORMAL.equals(rtrnUpdtTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_RTL_WH });
                return;
            }
        }
    }
    // 2020/03/24 QC#55673 Add End

    // 2023/11/06 QC#61924 Add Start
    /**
     * hasDeactivateForShippingLocationUpdate
     * @param shipToCustTMsg SHIP_TO_CUSTTMsg
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean hasDeactivateForShippingLocationUpdate(SHIP_TO_CUSTTMsg shipToCustTMsg, NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, String glblCmpyCd) {
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
            shipToCustTMsg.deacNewTrxDt.clear();
            return true;
        }

        if (shipToCustTMsg.deacNewTrxFlg.getValue().equals(cMsg.xxChkBox_H1.getValue())) {
            return true;
        }

        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("001");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        sellToCustTMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H1.getValue());

        SELL_TO_CUSTTMsgArray resMsg = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(sellToCustTMsg);
        if (resMsg.getValidCount() == 0) {
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(resMsg.no(0).deacNewTrxFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxDt, slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(shipToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
            shipToCustTMsg.deacNewTrxDt.clear();
        }
        return true;
    }

    /**
     * hasDeactivateForBillingLocationUpdate
     * @param billToCustTMsg BILL_TO_CUSTTMsg
     * @param cMsg NMAL6720CMsg
     * @param sMsg NMAL6720SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean hasDeactivateForBillingLocationUpdate(BILL_TO_CUSTTMsg billToCustTMsg, NMAL6720CMsg cMsg, NMAL6720SMsg sMsg, String glblCmpyCd) {
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.actvFlg_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
            billToCustTMsg.deacNewTrxDt.clear();
            return true;
        }

        if (!ZYPCommonFunc.hasValue(billToCustTMsg.deacNewTrxFlg)) {
            return false;
        }

        if (billToCustTMsg.deacNewTrxFlg.getValue().equals(cMsg.xxChkBox_H1.getValue())) {
            return true;
        }

        SELL_TO_CUSTTMsg sellToCustTMsg = new SELL_TO_CUSTTMsg();
        sellToCustTMsg.setSQLID("001");
        sellToCustTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        sellToCustTMsg.setConditionValue("sellToCustCd01", cMsg.dsAcctNum_H1.getValue());

        SELL_TO_CUSTTMsgArray resMsg = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(sellToCustTMsg);
        if (resMsg.getValidCount() == 0) {
            return true;
        }

        if (ZYPConstant.FLG_ON_Y.equals(resMsg.no(0).deacNewTrxFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxDt, slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(billToCustTMsg.deacNewTrxFlg, ZYPConstant.FLG_OFF_N);
            billToCustTMsg.deacNewTrxDt.clear();
        }
        return true;
    }
    // 2023/11/06 QC#61924 Add End
}
