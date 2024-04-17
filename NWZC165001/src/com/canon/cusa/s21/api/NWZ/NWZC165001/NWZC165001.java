package com.canon.cusa.s21.api.NWZ.NWZC165001;

import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.APPROVE;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.CANCEL;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.DOC_TP_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_CREDIT_REVIEW;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_LEASE_BUYOUT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_PRFT_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_SPLY_ABUSE_CTAC_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_SPLY_ABUSE_PEND_ORD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_SPLY_ABUSE_SPLY_ENFORCE;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_SPLY_ABUSE_YEILD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_VALID_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_CREDIT_REVIEW;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_LEASE_BUYOUT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_PRFT_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_SPLY_ABUSE_CTAC_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_SPLY_ABUSE_PEND_ORD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_SPLY_ABUSE_SPLY_ENFORCE;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_SPLY_ABUSE_YEILD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_VALID_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZC1650_DTL_NOTE_TXT_TMPL;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZC1650_APVL_DTL_NOTE_TXT_TMPL;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM0073E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM0074E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM0075E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM0253E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM0817E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1154E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1780E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1781E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1929E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1951E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1982E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1983E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1984E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1985E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1986E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1987E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1988E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM1989E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.PROC_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.REJECT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.ROWNUM;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.SUB_SYS_ID;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.VAR_CHAR_CONST_NWZC1650_CLT_DTL_NOTE_TXT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.VAR_CHAR_CONST_NWZC1650_APVL_CLT_DTL_NOTE_TXT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_CREDIT_REVIEW;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_LEASE_BUYOUT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_PRFT_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_SPLY_ABUSE_CTAC_STS;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_SPLY_ABUSE_PEND_ORD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_SPLY_ABUSE_SPLY_ENFORCE;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_SPLY_ABUSE_YEILD;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_VALID_WF;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WF_PRFT_NO_PROC;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.FIXED_REJECT_COMMENT;
import static com.canon.cusa.s21.api.NWZ.NWZC177001.constant.NWZC177001Constant.NWZC1770_WF_REJ_CMNT;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.NWZM2049E;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.WFID_MAN_PRC_APVL;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_APVL_MAN_PRC_APVL;
import static com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant.EVENTID_REJ_MAN_PRC_APVL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.BIZ_PROC_LOGTMsg;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_RTRN_PRC_CALC_BASETMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsgArray;
import business.db.CPO_VTMsg;
import business.db.CPO_VTMsgArray;
import business.db.CUST_CR_PRFLTMsg;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.HLD_RSNTMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.MSG_TXT_DTLTMsgArray;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASETMsgArray;
import business.db.SHPG_PLNTMsg;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150001_rtnPriceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC164001PMsg;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC164001.NWZC164001;
import com.canon.cusa.s21.api.NWZ.NWZC164001.NWZC164001TokenObject;
import com.canon.cusa.s21.api.NWZ.NWZC165001.constant.NWZC165001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC177001.NWZC177001;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfAuthException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfTokenObj;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NWZC1650 Order Header Workflow Rejection And Approval API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/24   SRAA            K.Aratani       Create          QC#4665
 * 2016/03/16   SRAA            K.Aratani       Create          QC#5591
 * 2016/07/25   Fujitsu         T.Ishii         Update          QC#11179
 * 2016/07/25   Fujitsu         T.Ishii         Update          QC#4324
 * 2016/08/04   Fujitsu         S.Yamamoto      Update          S21_NA#13013
 * 2016/08/16   SRAA            K.Aratani       Update          S21_NA#12904
 * 2016/09/07   Hitachi         T.Mizuki        Update          QC#12459
 * 2016/10/18   Fujitsu         T.Yoshida       Update          QC#14974
 * 2016/10/20   Fujitsu         H.Nagashima     Update          QC#12459
 * 2017/01/04   Fujitsu         T.Yoshida       Update          S21_NA#14512
 * 2017/09/26   Fujitsu         S.Takami        Update          S21_NA#21124
 * 2017/10/16   Fujitsu         A.Kosai         Update          S21_NA#21511
 * 2018/01/11   Fujitsu         N.Sugiura       Update          S21_NA#22372
 * 2018/07/25   Fujitsu         Y.Matsui        Update          S21_NA#27014 Add implementation of S21NwfApproveEvent
 * 2018/07/27   Fujitsu         Y.Matsui        Update          S21_NA#26876 Add implementation of S21NwfRejectEvent
 * 2018/09/20   Fujitsu         S.Kosaka        Update          QC#9700
 * 2019/01/18   Fujitsu         S.Takami        Update          S21_NA#28773
 * 2019/01/30   Fujitsu         S.Takami        Update          S21_NA#28799
 * 2019/09/13   Fujitsu         S.Kosaka        Update          QC#52503
 * 2019/10/04   Fujitsu         K.Kato          Update          QC#51372
 * 2022/11/14   Hitachi         S.Fujita        Update          QC#60406
 * 2023/02/28   Hitachi         S.Fujita        Update          QC#61185
 * 2023/04/25   Hitachi         A.Kohinata      Update          QC#61337
 * 2023/05/12   Hitachi         S.Fujita        Update          QC#61396
 *</pre>
 */
public class NWZC165001 extends S21ApiCommonBase implements S21NwfProcessEndEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>, S21NwfApproveEvent<S21NwfContext, S21NwfToken> {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21Logger */
    private static final S21Logger logger = S21LoggerFactory.getLogger(NWZC165001.class);

    /**
     * Constructor
     */
    public NWZC165001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {

        if (APPROVE.equals(name)) {

            approve(context, token);
        } else if (REJECT.equals(name)) {

            reject(context, token);
        } else if (CANCEL.equals(name)) {

            // do nothing
            // QC#5591
        } else if (S21NwfConst.COND_END_NO_USER.equals(name)) {

            // The credit authorizer is not found. Please set up it at
            // workflow.
            // S21_NA#11179 modify start
            NWZC164001TokenObject tokenObj = (NWZC164001TokenObject) token.getTokenObj();
            final String processName = tokenObj.getParentProcess().getProcessName();
            if (!WFID_PRFT_WF.equals(processName) || !WF_PRFT_NO_PROC.equals(tokenObj.getCondStr9())) {

                //QC#12904
                //Profitability
                if (WFID_PRFT_WF.equals(processName)) {
                    //Profitability approver is not found. Please set up it at workflow.
                    //@ approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1982E, null);
                //Credit
                } else if (WFID_CREDIT_REVIEW.equals(processName)) {
                    //The credit authorizer is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1929E, null);
                } else if (WFID_VALID_WF.equals(processName)) {
                    //Validation WorkFlow approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1989E, null);
                } else if (WFID_SPLY_ABUSE_CTAC_STS.equals(processName)) {
                    //Supply Abuse (Contact Status) approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1984E, null);
                } else if (WFID_SPLY_ABUSE_PEND_ORD.equals(processName)) {
                    //Supply Abuse (Pending Order) approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1985E, null);
                } else if (WFID_SPLY_ABUSE_SPLY_ENFORCE.equals(processName)) {
                    //Supply Abuse (Supply Enforcement) approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1986E, null);
                } else if (WFID_SPLY_ABUSE_YEILD.equals(processName)) {
                    //Supply Abuse (Yield) approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1987E, null);
                } else if (WFID_LEASE_BUYOUT.equals(processName)) {
                    //Lease Buyout approver is not found. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1988E, null);
                } else {
                    //No user to approve. Please set up it at workflow.
                    throw new S21NwfBizException(NWZM1983E, null);
                }
            } else {
                if (WFID_PRFT_WF.equals(processName) && WF_PRFT_NO_PROC.equals(tokenObj.getCondStr9())) {

                    // release
                    // approve(context, token); // 2016/10/18 S21_NA#14974 Del
                }
            }
            // S21_NA#11179 modify end
        } else {

            // Undefined value is set in Process mode.
            throw new S21NwfBizException(NWZM1154E, null);
        }
    }

    // 2018/07/25 S21_NA#27014 Add Start
    @Override
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {
        // Get TokenObject of WorkFlow
        S21NwfTokenObj tokenObj = (S21NwfTokenObj) token.getTokenObj();

        final String processName = tokenObj.getParentProcess().getProcessName();
        final NWZC165001CpoBean cpoBean = new NWZC165001CpoBean();
        setCpoBean(cpoBean, tokenObj, context);

        if (WFID_CREDIT_REVIEW.equals(processName)) {
            // hard hold flag = Y -> error.
            if (isCreditHardHoldCustomer(cpoBean)) {
                throw new S21NwfBizException(NWZM1951E, null);
            }
        }
    }
    // 2018/07/25 S21_NA#27014 Add End

    // 2018/07/27 S21_NA#26876 Add Start
    @Override
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {
        reject(context, token);
    }
    // 2018/07/27 S21_NA#26876 Add End

    /**
     * approve
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException S21NwfBizException
     */
    public void approve(S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        // Log
        logger.debug("$$$$$$  Approve API Start $$$$$$");

        // Get TokenObject of WorkFlow
        S21NwfTokenObj tokenObj = (S21NwfTokenObj) token.getTokenObj();

        final String processName = tokenObj.getParentProcess().getProcessName();
        final NWZC165001CpoBean cpoBean = new NWZC165001CpoBean();
        setCpoBean(cpoBean, tokenObj, context);

        // check Mandatory of Input
        if (!checkInputForApprove(processName, cpoBean)) {
            throw new S21NwfBizException(NWZM1780E, null);
        }

        // S21_NA#4324 add start
        // 2018/07/25 S21_NA#27014 Del Start -- Move to S21NwfApproveEvent#approve()
//        if (WFID_CREDIT_REVIEW.equals(processName)) {
//
//            // hard hold flag = Y -> error.
//            if (isCreditHardHoldCustomer(cpoBean)) {
//
//                throw new S21NwfBizException(NWZM1951E, null);
//            }
//        }
        // 2018/07/25 S21_NA#27014 Del End
        // S21_NA#4324 add end

        // get HOLD
        List<Map<Object, Object>> hldList = getHld(processName, cpoBean);
        if (hldList.isEmpty()) {
            throw new S21NwfBizException(NWZM0253E, null);
        }

        String errMsg = null;
        List<NWZC033001PMsg> params = new ArrayList<NWZC033001PMsg>(); // S21_NA#14512 Add

        for (Map<Object, Object> hldMap : hldList) {
            setHldToCpo(hldMap, cpoBean);

            if (HLD_LVL.CPO_LEVEL.equals(cpoBean.getHldLvlCd_H()) || HLD_LVL.CPO_DETAIL_LEVEL.equals(cpoBean.getHldLvlCd_H()) || HLD_LVL.SHIPPING_PLAN_LEVEL.equals(cpoBean.getHldLvlCd_H())) {
                lockHoldRelatedAccessTable(cpoBean);
            }

            // S21_NA#14512 Mod Start
//            errMsg = callHldRlsApi(cpoBean);
//            if (ZYPCommonFunc.hasValue(errMsg)) {
//                throw new S21NwfBizException(errMsg, null);
//            }
            addHldRlsApiParam(cpoBean, params);
            // S21_NA#14512 Mod End
        }

        // S21_NA#14512 Add Start
        if (params.size() > 0) {
            // call HOLD Release API
            errMsg = callHldRlsApi(params);
            if (ZYPCommonFunc.hasValue(errMsg)) {
                throw new S21NwfBizException(errMsg, null);
            }
        }
        // S21_NA#14512 Add End

        // insert Business Process Log
        // String wDocId = getWDocIdForAprv(cpoBean, processName);
        String wDocId = getWDocIdForAprv(processName);
        insertBizProcLog(cpoBean, wDocId);

        // call Order Header Workflow Control API
        errMsg = callOrdHdrWfContrlApi(cpoBean);
        if (ZYPCommonFunc.hasValue(errMsg)) {
            throw new S21NwfBizException(errMsg, null);
        }

        // del start 2023/02/28 QC#61185
        // add start 2022/11/14 QC#60406
        //if (WFID_CREDIT_REVIEW.equals(processName)) {
        //    setCollectionText(context, cpoBean, NWZC165001Constant.APPROVE, getDocId(tokenObj));
        //}
        // add end 2022/11/14 QC#60406
        // del end 2023/02/28 QC#61185
        // Log
        logger.debug("$$$$$$  Approve API End $$$$$$");
    }
    
    /**
     * setCpoBean
     * @param msgMap NWZC183001PMsg
     * @throws S21NwfBizException
     */
    private void setCpoBean(NWZC165001CpoBean cpoBean, S21NwfTokenObj tokenObj, S21NwfContext context) {
        NWZC164001TokenObject tokenBizObj = (NWZC164001TokenObject) tokenObj;
        cpoBean.setCpoOrdNum(tokenBizObj.getCpoOrdNum());
        cpoBean.setHldRsnCd(tokenBizObj.getHldRsnCd());
        cpoBean.setCpoDtlLineNum(tokenBizObj.getCpoDtlLineNum());
        cpoBean.setCpoDtlLineSubNum(tokenBizObj.getCpoDtlLineSubNum());
        cpoBean.setAprvCmnt(tokenObj.getComment());
        cpoBean.setSlsDt(ZYPDateUtil.getSalesDate());
        cpoBean.setGlblCmpyCd(tokenObj.getGlblCmpyCd());
        cpoBean.setUserId(context.getUserID());
    }

    /**
     * checkInputForApprove
     * @param processName String
     * @param cpoBean NWZC165001CpoBean
     * @return boolean
     */
    private boolean checkInputForApprove(String processName, NWZC165001CpoBean cpoBean) {
        // Global Company Code
        if (!ZYPCommonFunc.hasValue(cpoBean.getGlblCmpyCd())) {
            return false;
        }

        // CPO Order Number
        if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum())) {
            return false;
        }

        if (WFID_CREDIT_REVIEW.equals(processName)) {
            return true;
        }

        // Hold Reason Code
        if (!ZYPCommonFunc.hasValue(cpoBean.getHldRsnCd())) {
            return false;
        }

        if (WFID_LEASE_BUYOUT.equals(processName)) {
            // CPO Detail Line Number
            if (!ZYPCommonFunc.hasValue(cpoBean.getCpoDtlLineNum())) {
                return false;
            }
            // CPO Detail Line Sub Number
            if (!ZYPCommonFunc.hasValue(cpoBean.getCpoDtlLineSubNum())) {
                return false;
            }
        }
        return true;
    }

    /**
     * getHld
     * @param processName String
     * @param cpoBean NWZC165001CpoBean
     * @return List<Map<Object, Object>>
     */
    @SuppressWarnings("unchecked")
    public List<Map<Object, Object>> getHld(String processName, NWZC165001CpoBean cpoBean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        if (WFID_CREDIT_REVIEW.equals(processName)) {
            ssmParam.put("hldTpCd", HLD_TP.CREDIT_HOLD);
        } else {
            ssmParam.put("hldRsnCd", cpoBean.getHldRsnCd());
        }

        if (WFID_LEASE_BUYOUT.equals(processName)) {
            ssmParam.put("cpoDtlLineNum", cpoBean.getCpoDtlLineNum());
            ssmParam.put("cpoDtlLineSubNum", cpoBean.getCpoDtlLineSubNum());
        }

        // S21_NA#13013 Mod & Add
//        return (List<Map<Object, Object>>) ssmClient.queryObjectList("getHld", ssmParam);
        List<Map<Object, Object>> releaseHoldList = (List<Map<Object, Object>>) ssmClient.queryObjectList("getHld", ssmParam);

        if (WFID_LEASE_BUYOUT.equals(processName)) {

            ssmParam.put("Cancelled", ORD_LINE_STS.CANCELLED);
            int leftHold = (Integer) ssmClient.queryObject("hasLeftBuyoutApvlHold", ssmParam);
            if (leftHold < 1) {
                ssmParam.put("hldRsnCd", HLD_RSN.BUYOUT_BILLING);
                List<Map<Object, Object>> holdInfoList = (List<Map<Object, Object>>) ssmClient.queryObjectList("getBuyoutBillingHold", ssmParam);
                for (Map<Object, Object> holdInfo : holdInfoList) {
                    releaseHoldList.add(holdInfo);
                }
            }
        }
        return releaseHoldList;
    }

    /**
     * lockHoldRelatedAccessTable
     * @param cpoBean NWZC165001CpoBean
     * @throws S21NwfBizException S21NwfBizException
     */
    public void lockHoldRelatedAccessTable(NWZC165001CpoBean cpoBean) throws S21NwfBizException {
        // CPO, CPO Detail and Shipping Plan Hold Level
        CPOTMsg cpoTMsg = (CPOTMsg) getCpoTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum_H());
        if (cpoTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoTMsg.getReturnCode())) {
            throw new S21NwfBizException(NWZM0073E, null);
        }

        if (HLD_LVL.CPO_LEVEL.equals(cpoBean.getHldLvlCd_H())) {
            return;
        }

        // CPO Detail and Shipping Plan hold Level
        CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) getCpoDtlTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum(), cpoBean.getCpoDtlLineNum_H(), cpoBean.getCpoDtlLineSubNum_H());
        if (cpoDtlTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(cpoDtlTMsg.getReturnCode())) {
            throw new S21NwfBizException(NWZM0074E, null);
        }

        if (HLD_LVL.CPO_DETAIL_LEVEL.equals(cpoBean.getHldLvlCd_H())) {
            return;
        }

        // Shipping Plan hold Level
        SHPG_PLNTMsg shpgPlnTMsg = (SHPG_PLNTMsg) getShpgPlnTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getShpgPlnNum_H());
        if (shpgPlnTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(shpgPlnTMsg.getReturnCode())) {
            throw new S21NwfBizException(NWZM0075E, null);
        }
    }

    /**
     * getHldDtl
     * @param hldMap Map<Object, Object>
     * @param cpoBean NWZC165001CpoBean
     */
    public void setHldToCpo(Map<Object, Object> hldMap, NWZC165001CpoBean cpoBean) {
        cpoBean.setHldPk((BigDecimal) hldMap.get("HLD_PK"));
        cpoBean.setCpoOrdNum_H((String) hldMap.get("CPO_ORD_NUM"));
        cpoBean.setCpoDtlLineNum_H((String) hldMap.get("CPO_DTL_LINE_NUM"));
        cpoBean.setCpoDtlLineSubNum_H((String) hldMap.get("CPO_DTL_LINE_SUB_NUM"));
        cpoBean.setShpgPlnNum_H((String) hldMap.get("SHPG_PLN_NUM"));
        cpoBean.setHldRsnNm_H((String) hldMap.get("HLD_RSN_NM"));
        cpoBean.setHldLvlCd_H((String) hldMap.get("HLD_LVL_CD"));
    }

    /**
     * call HOLD Release API(NWZC0330)
     * @param cpoBean NWZC165001CpoBean
     * @return String
     */
    public String callHldRlsApi(NWZC165001CpoBean cpoBean) {
        NWZC033001PMsg hldRlsPMsg = new NWZC033001PMsg();

        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.hldPk, cpoBean.getHldPk());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.cpoOrdNum, cpoBean.getCpoOrdNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.cpoDtlLineNum, cpoBean.getCpoDtlLineNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.cpoDtlLineSubNum, cpoBean.getCpoDtlLineSubNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.shpgPlnNum, cpoBean.getShpgPlnNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.slsDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.hldRelRsnCd, HLD_REL_RSN.AUTO);
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.relMemoTxt, cpoBean.getAprvCmnt());

        NWZC033001 hldRlsApi = new NWZC033001();
        hldRlsApi.execute(hldRlsPMsg, ONBATCH_TYPE.ONLINE);

        if (hldRlsPMsg.xxMsgIdList.length() > 0) {
            return hldRlsPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
        }
        return null;
    }

    // S21_NA#14512 Add Start
    /**
     * call Hold Release API(NWZC0330)
     * @param params List<NWZC033001PMsg>
     * @return Error Message
     */
    public String callHldRlsApi(List<NWZC033001PMsg> params) {

        NWZC033001 hldRlsApi = new NWZC033001();
        hldRlsApi.execute(params, ONBATCH_TYPE.ONLINE);

        for (NWZC033001PMsg hldRlsPMsg : params) {
            if (hldRlsPMsg.xxMsgIdList.length() > 0) {
                return hldRlsPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            }
        }

        return null;
    }

    /**
     * Add Hold Release API Parameter
     * @param cpoBean NWZC165001CpoBean
     * @param params List<NWZC033001PMsg>
     */
    public void addHldRlsApiParam(NWZC165001CpoBean cpoBean, List<NWZC033001PMsg> params) {

        NWZC033001PMsg hldRlsPMsg = new NWZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.hldPk, cpoBean.getHldPk());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.cpoOrdNum, cpoBean.getCpoOrdNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.cpoDtlLineNum, cpoBean.getCpoDtlLineNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.cpoDtlLineSubNum, cpoBean.getCpoDtlLineSubNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.shpgPlnNum, cpoBean.getShpgPlnNum_H());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.slsDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.hldRelRsnCd, HLD_REL_RSN.AUTO);
        ZYPEZDItemValueSetter.setValue(hldRlsPMsg.relMemoTxt, cpoBean.getAprvCmnt());
        params.add(hldRlsPMsg);
    }
    // S21_NA#14512 Add End

    /**
     * getWDocIdForAprv
     * @param processName String
     * @return String
     */
    private String getWDocIdForAprv(String processName) {
        // private String getWDocIdForAprv(NWZC165001CpoBean cpoBean,
        // String processName) {
        // StringBuilder wDocId = new StringBuilder();
        //
        // wDocId.append(WF_APPROVE).append("(").append(processName);
        //
        // if (ZYPCommonFunc.hasValue(cpoBean.getHldRsnNm_H())) {
        // wDocId.append("-").append(cpoBean.getHldRsnNm_H()).append(")");
        // } else {
        // wDocId.append(")");
        // }
        //
        // return wDocId.toString();

        String wDocId = "";
        if (WFID_VALID_WF.equals(processName)) {
            wDocId = EVENTID_APVL_VALID_WF;
        } else if (WFID_PRFT_WF.equals(processName)) {
            wDocId = EVENTID_APVL_PRFT_WF;
        } else if (WFID_SPLY_ABUSE_YEILD.equals(processName)) {
            wDocId = EVENTID_APVL_SPLY_ABUSE_YEILD;
        } else if (WFID_SPLY_ABUSE_CTAC_STS.equals(processName)) {
            wDocId = EVENTID_APVL_SPLY_ABUSE_CTAC_STS;
        } else if (WFID_SPLY_ABUSE_PEND_ORD.equals(processName)) {
            wDocId = EVENTID_APVL_SPLY_ABUSE_PEND_ORD;
        } else if (WFID_SPLY_ABUSE_SPLY_ENFORCE.equals(processName)) {
            wDocId = EVENTID_APVL_SPLY_ABUSE_SPLY_ENFORCE;
        } else if (WFID_CREDIT_REVIEW.equals(processName)) {
            wDocId = EVENTID_APVL_CREDIT_REVIEW;
        } else if (WFID_LEASE_BUYOUT.equals(processName)) {
            wDocId = EVENTID_APVL_LEASE_BUYOUT;
        // add start 2023/04/25 QC#61337
        } else if  (WFID_MAN_PRC_APVL.equals(processName)) {
            wDocId = EVENTID_APVL_MAN_PRC_APVL;
        // add end 2023/04/25 QC#61337
        }
        return wDocId;
    }

    /**
     * call Order Header Workflow Control API(NWZC1640)
     * @param cpoBean NWZC165001CpoBean
     * @return String
     */
    public String callOrdHdrWfContrlApi(NWZC165001CpoBean cpoBean) {
        NWZC164001PMsg ordHdrWfControlPMsg = new NWZC164001PMsg();

        ZYPEZDItemValueSetter.setValue(ordHdrWfControlPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(ordHdrWfControlPMsg.cpoOrdNum, cpoBean.getCpoOrdNum_H());
        ZYPEZDItemValueSetter.setValue(ordHdrWfControlPMsg.slsDt, cpoBean.getSlsDt());

        NWZC164001 ordHdrWfControlApi = new NWZC164001();
        ordHdrWfControlApi.execute(ordHdrWfControlPMsg, ONBATCH_TYPE.ONLINE);

        if (ordHdrWfControlPMsg.xxMsgIdList.length() > 0) {
            return ordHdrWfControlPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
        }
        return null;
    }

    /**
     * getCpoTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return EZDTMsg
     */
    public EZDTMsg getCpoTMsgForUpdate(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        cpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

        return (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(cpoTMsg);
    }

    /**
     * getCpoDtlTMsgForUpdate
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param ordLineNum String
     * @param ordLineSubNum String
     * @return EZDTMsg
     */
    public EZDTMsg getCpoDtlTMsgForUpdate(String glblCmpyCd, String cpoOrdNum, String ordLineNum, String ordLineSubNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(ordLineNum) || !ZYPCommonFunc.hasValue(ordLineSubNum)) {
            return null;
        }

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
        cpoDtlTMsg.cpoOrdNum.setValue(cpoOrdNum);
        cpoDtlTMsg.cpoDtlLineNum.setValue(ordLineNum);
        cpoDtlTMsg.cpoDtlLineSubNum.setValue(ordLineSubNum);

        return (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(cpoDtlTMsg);
    }

    /**
     * getShpgPlnTMsgForUpdate
     * @param glblCmpyCd String
     * @param shpgPlnNum String
     * @return EZDTMsg
     */
    public EZDTMsg getShpgPlnTMsgForUpdate(String glblCmpyCd, String shpgPlnNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(shpgPlnNum)) {
            return null;
        }

        SHPG_PLNTMsg shpgPlnTMsg = new SHPG_PLNTMsg();
        shpgPlnTMsg.glblCmpyCd.setValue(glblCmpyCd);
        shpgPlnTMsg.shpgPlnNum.setValue(shpgPlnNum);

        return (SHPG_PLNTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(shpgPlnTMsg);
    }

    /**
     * reject
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfBizException S21NwfBizException
     */
    public void reject(S21NwfContext context, S21NwfToken token) throws S21NwfBizException {
        // Log
        logger.debug("$$$$$$  Reject API Start $$$$$$");

        // Get TokenObject of WorkFlow
        S21NwfTokenObj tokenObj = (S21NwfTokenObj) token.getTokenObj();

        // set CPO Data
        final String processName = tokenObj.getParentProcess().getProcessName();
        final NWZC165001CpoBean cpoBean = new NWZC165001CpoBean();
        setCpoBean(cpoBean, tokenObj, context);

        // check Input Parameter
        if (!checkInputForReject(processName, cpoBean)) {
            throw new S21NwfBizException(NWZM1780E, null);
        }

        // check Credit WorkFlow
        // 2019/01/18 S21_NA#28773 Add Start
        boolean isRebookRejectOrder = false;
        // 2019/01/18 S21_NA#28773 Add End
        if (WFID_CREDIT_REVIEW.equals(processName)) {
            boolean isCrWorkFlow = chkCrWorkFlow(cpoBean);

            // 2019/01/18 S21_NA#28773 Add Start
            isRebookRejectOrder = isRejectOrder(tokenObj);
            // 2019/01/18 S21_NA#28773 Add End

            if (isCrWorkFlow && !isRebookRejectOrder) { // 2019/01/18 S21_NA#28773 Add !isRebookRejectOrder
                // check Order Cancel is Allowance
                if (!chkAllowOrderCancel(cpoBean)) {
                    throw new S21NwfBizException(NWZM0817E, null);
                }
                // cancel Order
                callDsCpoUpdateApi(cpoBean);
            }
        } else {
            // 2019/09/13 QC#52503 Add Start
            if (WFID_VALID_WF.equals(processName)
                    || WFID_PRFT_WF.equals(processName)
                    || WFID_SPLY_ABUSE_YEILD.equals(processName)
                    || WFID_SPLY_ABUSE_CTAC_STS.equals(processName)
                    || WFID_SPLY_ABUSE_PEND_ORD.equals(processName)
                    || WFID_SPLY_ABUSE_SPLY_ENFORCE.equals(processName)
                    // mod start 2023/04/25 QC#61337
                    //|| WFID_LEASE_BUYOUT.equals(processName)) {
                    || WFID_LEASE_BUYOUT.equals(processName)
                    || WFID_MAN_PRC_APVL.equals(processName)) {
                    // mod end 2023/04/25 QC#61337
                isRebookRejectOrder = isRejectOrder(tokenObj);
            }
            // 2019/09/13 QC#52503 Add End
            String hldRsnNm = getHldRsnNm(cpoBean);
            cpoBean.setHldRsnNm_H(hldRsnNm);
        }

        // update Workflow Reject Flag
        if (!isRebookRejectOrder // 2019/01/18 S21_NA#28773 Add !isRebookRejectOrder
                && !updateWfRejectFlg(cpoBean)) {
            throw new S21NwfBizException(NWZM1781E, null);
        }

        // insert Business Process Log
        String wDocId = getWDocIdForReject(processName);
        // String wDocId = getWDocIdForReject(cpoBean, processName);
        insertBizProcLog(cpoBean, wDocId);

        NWZC188001PMsg pMsg = new NWZC188001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoBean.getCpoOrdNum());

        NWZC188001 ordStsUpdtApi = new NWZC188001();
        ordStsUpdtApi.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            throw new S21NwfBizException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), null);
        }

        // 2019/01/30 S21_NA#28799 Add Start
        if (WFID_CREDIT_REVIEW.equals(processName)) {
            // mod start 2022/11/14 QC#60406  
            //setCollectionText(cpoBean);
            setCollectionText(context, cpoBean, NWZC165001Constant.REJECT, getDocId(tokenObj));
            // mod end 2022/11/14 QC#60406
        }
        // 2019/01/30 S21_NA#28799 Add End

        // add start 2023/04/25 QC#61337
        // Reject Profitability WF -> Cancel Manual Price WF. Reject Manual Price -> Cancel Profitability WF.
        boolean cancMod = S21StringUtil.isEquals("CANCEL", ((NWZC164001TokenObject) tokenObj).getCondStr9());
        if (!cancMod && (WFID_PRFT_WF.equals(processName) || WFID_MAN_PRC_APVL.equals(processName))) {
            String trgtProcName = "";
            if (WFID_PRFT_WF.equals(processName)) {
                trgtProcName = WFID_MAN_PRC_APVL;
            } else {
                trgtProcName = WFID_PRFT_WF;
            }
            try {
                List<S21NwfProcess> processList = context.getProcessFindByGroup(cpoBean.getCpoOrdNum());
                for (S21NwfProcess curProcess : processList) {
                    if (S21StringUtil.isEquals(trgtProcName, curProcess.getProcessName())) {
                        // Cancel Target Process
                        if (curProcess.isActive()) {
                            S21NwfToken cancToken = curProcess.getToken();
                            NWZC164001TokenObject cancTokenObj = (NWZC164001TokenObject) cancToken.getTokenObj();
                            cancTokenObj.setCondStr9(trgtProcName);
                            new NWZC177001().cancel(trgtProcName, context, cancToken);
                        }
                    }
                }
            } catch(S21NwfSystemException ex) {
                logger.debug("$$$$$$  Reject API (with S21NwfSystemException) End $$$$$$");
                throw new S21NwfBizException(NWZM2049E, null);
            }
        }
        // add end 2023/04/25 QC#61337

        // Log
        logger.debug("$$$$$$  Reject API End $$$$$$");
    }

    /**
     * checkInputForReject
     * @param processName String
     * @param cpoBean NWZC165001CpoBean
     * @return boolean
     */
    private boolean checkInputForReject(String processName, NWZC165001CpoBean cpoBean) {
        // CPO Order Number
        if (!ZYPCommonFunc.hasValue(cpoBean.getCpoOrdNum())) {
            return false;
        }

        if (WFID_CREDIT_REVIEW.equals(processName)) {
            return true;
        }

        // Hold Reason Code
        if (!ZYPCommonFunc.hasValue(cpoBean.getHldRsnCd())) {
            return false;
        }
        return true;
    }

    /**
     * chkCrWorkFlow
     * @param cpoBean NWZC165001CpoBean
     * @return boolean
     */
    private boolean chkCrWorkFlow(NWZC165001CpoBean cpoBean) {
        // Auto Cancel Order Flag
        cpoBean.setAutoCancOrdFlg(getAutoCancOrdFlg(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum()));

        if (ZYPConstant.FLG_ON_Y.equals(cpoBean.getAutoCancOrdFlg())) {
            return true;
        }
        return false;
    }

    /**
     * chkAllowOrderCancel
     * @param cpoBean NWZC165001CpoBean
     * @return boolean
     */
    private boolean chkAllowOrderCancel(NWZC165001CpoBean cpoBean) {
        // check if has SHPG_PLN
        int cntShpgPln = hasShpgPln(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
        // mod start 2016/09/07 CSA QC#12459
        if (cntShpgPln >= 1) {
        // mod end 2016/09/07 CSA QC#12459
            return false;
        }

        // check if has DS_CPO_RTRN_DTL
        int cntDsCpoRtrnDtl = hasDsCpoRtrnDtl(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
        // mod start 2016/09/07 CSA QC#12459
        if (cntDsCpoRtrnDtl >= 1) {
        // mod end 2016/09/07 CSA QC#12459
            return false;
        }

        return true;
    }

    /**
     * getWDocIdForReject
     * @param cpoBean NWZC165001CpoBean
     * @param processName String
     * @return boolean
     */
    private String getWDocIdForReject(String processName) {
        // private String getWDocIdForReject(NWZC165001CpoBean
        // cpoBean, String processName) {
        // StringBuilder wDocId = new StringBuilder();
        //
        // wDocId.append(WF_REJECT).append("(").append(processName);
        //
        // if (WFID_CREDIT_REVIEW.equals(processName)) {
        // wDocId.append(")");
        // } else {
        // // Hold Reason Name
        // String hldRsnNm = getHldRsnNm(cpoBean);
        // wDocId.append("-").append(hldRsnNm).append(")");
        // }
        //
        // return wDocId.toString();

        String wDocId = "";
        if (WFID_VALID_WF.equals(processName)) {
            wDocId = EVENTID_REJ_VALID_WF;
        } else if (WFID_PRFT_WF.equals(processName)) {
            wDocId = EVENTID_REJ_PRFT_WF;
        } else if (WFID_SPLY_ABUSE_YEILD.equals(processName)) {
            wDocId = EVENTID_REJ_SPLY_ABUSE_YEILD;
        } else if (WFID_SPLY_ABUSE_CTAC_STS.equals(processName)) {
            wDocId = EVENTID_REJ_SPLY_ABUSE_CTAC_STS;
        } else if (WFID_SPLY_ABUSE_PEND_ORD.equals(processName)) {
            wDocId = EVENTID_REJ_SPLY_ABUSE_PEND_ORD;
        } else if (WFID_SPLY_ABUSE_SPLY_ENFORCE.equals(processName)) {
            wDocId = EVENTID_REJ_SPLY_ABUSE_SPLY_ENFORCE;
        } else if (WFID_CREDIT_REVIEW.equals(processName)) {
            wDocId = EVENTID_REJ_CREDIT_REVIEW;
        } else if (WFID_LEASE_BUYOUT.equals(processName)) {
            wDocId = EVENTID_REJ_LEASE_BUYOUT;
        // add start 2023/04/25 QC#61337
        } else if  (WFID_MAN_PRC_APVL.equals(processName)) {
            wDocId = EVENTID_REJ_MAN_PRC_APVL;
        // add end 2023/04/25 QC#61337
        }
        return wDocId;
    }

    /**
     * insertBizProcLog
     * @param cpoBean NWZC165001CpoBean
     * @param wDocId String
     * @return boolean
     */
    private void insertBizProcLog(NWZC165001CpoBean cpoBean, String wDocId) {
        S21BusinessProcessLogMsg bizProcLog = new S21BusinessProcessLogMsg();

        bizProcLog.setSubSysId(SUB_SYS_ID);
        bizProcLog.setProcId(PROC_ID);
        bizProcLog.setDocTpCd(DOC_TP_CD);
        bizProcLog.setPrntDocId(cpoBean.getCpoOrdNum());

        int lengthEventId = new BIZ_PROC_LOGTMsg().getAttr("eventId").getDigit();
        if (wDocId.length() > lengthEventId) {
            bizProcLog.setEventId(wDocId.substring(0, lengthEventId));
        } else {
            bizProcLog.setEventId(wDocId);
        }

        S21BusinessProcessLog.print(bizProcLog);
    }

    /**
     * updateWfRejectFlg
     * @param cpoBean NWZC165001CpoBean
     * @return boolean
     */
    private boolean updateWfRejectFlg(NWZC165001CpoBean cpoBean) {
       CPOTMsg dsCpoTMsg = (CPOTMsg) getDsCpoTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());

        if (dsCpoTMsg == null) {
            return false;
        }

        dsCpoTMsg.wfRejFlg.setValue(ZYPConstant.FLG_ON_Y);

        S21FastTBLAccessor.update(dsCpoTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsCpoTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * getAutoCancOrdFlg
     * @param glblCmpyCd String
     * @param ordNum String
     * @return String
     */
    public String getAutoCancOrdFlg(String glblCmpyCd, String ordNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", ordNum);

        return (String) ssmClient.queryObject("getAutoCancOrdFlg", ssmParam);
    }

    /**
     * hasShpgPln
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Integer
     */
    public Integer hasShpgPln(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("saved", SHPG_STS.SAVED);
        ssmParam.put("valid", SHPG_STS.VALIDATED);
        ssmParam.put("cancel", SHPG_STS.CANCELLED);
        ssmParam.put("poCancel", SHPG_STS.P_OR_O_CANCELLED);
        ssmParam.put("rowNum", ROWNUM);

        return (Integer) ssmClient.queryObject("hasShpgPln", ssmParam);
    }

    /**
     * hasDsCpoRtrnDtl
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return Integer
     */
    public Integer hasDsCpoRtrnDtl(String glblCmpyCd, String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("saved", RTRN_LINE_STS.ENTERED);
        ssmParam.put("valid", RTRN_LINE_STS.BOOKED);
        ssmParam.put("cancel", RTRN_LINE_STS.CANCELLED);
        ssmParam.put("rowNum", ROWNUM);

        return (Integer) ssmClient.queryObject("hasDsCpoRtrnDtl", ssmParam);
    }

    /**
     * getHldRsnNm
     * @param cpoBean NWZC165001CpoBean
     * @return String
     */
    public String getHldRsnNm(NWZC165001CpoBean cpoBean) {
        HLD_RSNTMsg hldRsnRslt = (HLD_RSNTMsg) getHldRsnTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getHldRsnCd());

        if (hldRsnRslt == null) {
            return null;
        }
        return hldRsnRslt.hldRsnDescTxt.getValue();
    }

    /**
     * getHldRsnNm
     * @param glblCmpyCd String
     * @param cd String
     * @return EZDTMsg
     */
    public EZDTMsg getHldRsnTMsg(String glblCmpyCd, String cd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cd)) {
            return null;
        }

        final HLD_RSNTMsg tMsg = new HLD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hldRsnCd, cd);

        return findByKeyWithCache(tMsg);
    }

    /**
     * getDsCpoTMsgForUpdate
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return EZDTMsg
     */
    public EZDTMsg getDsCpoTMsgForUpdate(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPOTMsg dsCpoTMsg = new CPOTMsg();
        dsCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        dsCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

        return (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsCpoTMsg);
    }

    /**
     * getCpoTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return EZDTMsg
     */
    public EZDTMsg getCpoTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPOTMsg cpoTMsg = new CPOTMsg();
        cpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        cpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

        return (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);
    }

    /**
     * getDsCpoTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return EZDTMsg
     */
    public EZDTMsg getDsCpoTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPOTMsg dsCpoTMsg = new CPOTMsg();
        dsCpoTMsg.glblCmpyCd.setValue(glblCmpyCd);
        dsCpoTMsg.cpoOrdNum.setValue(cpoOrdNum);

        return (CPOTMsg) S21FastTBLAccessor.findByKey(dsCpoTMsg);
    }

    /**
     * getDsCpoTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return String
     */
    public String getInvTxtCmnt(String glblCmpyCd, String cpoOrdNum) {

        MSG_TXT_DTLTMsgArray msgTxtDtlArry = getMsgTxtDtlTMsg(glblCmpyCd, cpoOrdNum);
        if (msgTxtDtlArry == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msgTxtDtlArry.length(); i++) {
            sb.append(msgTxtDtlArry.no(i).msgTxtInfoTxt.getValue());
        }

        return sb.toString();
    }

    /**
     * getDsCpoTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return MSG_TXT_DTLTMsgArray
     */
    public MSG_TXT_DTLTMsgArray getMsgTxtDtlTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        MSG_TXT_DTLTMsg msgTxtDtlTMsg = new MSG_TXT_DTLTMsg();
        msgTxtDtlTMsg.setSQLID("008");
        msgTxtDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        msgTxtDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        msgTxtDtlTMsg.setConditionValue("txtTpCd01", TXT_TP.INVOICE_COMMENT);

        return (MSG_TXT_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(msgTxtDtlTMsg);
    }

    /**
     * getDsCpoConfigTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return DS_CPO_CONFIGTMsgArray
     */
    public DS_CPO_CONFIGTMsgArray getDsCpoConfigTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        DS_CPO_CONFIGTMsg dsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
        dsCpoConfigTMsg.setSQLID("001");
        dsCpoConfigTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCpoConfigTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (DS_CPO_CONFIGTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoConfigTMsg);
    }

    /**
     * getCpoDtlTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return DS_CPO_CONFIGTMsgArray
     */
    public CPO_DTLTMsgArray getCpoDtlTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
        cpoDtlTMsg.setSQLID("001");
        cpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(cpoDtlTMsg);
    }

    /**
     * getDsCpoDtlTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return CPO_CONFIGTMsgArray
     */
    public CPO_DTLTMsgArray getDsCpoDtlTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
        dsCpoDtlTMsg.setSQLID("501");
        dsCpoDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCpoDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoDtlTMsg);
    }

    /**
     * getDsCpoRtrnDtlTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return DS_CPO_CONFIGTMsgArray
     */
    public DS_CPO_RTRN_DTLTMsgArray getDsCpoRtrnDtlTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        dsCpoRtrnDtlTMsg.setSQLID("001");
        dsCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (DS_CPO_RTRN_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(dsCpoRtrnDtlTMsg);
    }

    /**
     * getOrdPrcCalcBaseTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return DS_CPO_CONFIGTMsgArray
     */
    public ORD_PRC_CALC_BASETMsgArray getOrdPrcCalcBaseTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
        ordPrcCalcBaseTMsg.setSQLID("002");
        ordPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        ordPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (ORD_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(ordPrcCalcBaseTMsg);
    }

    /**
     * getRtrnPrcCalcBaseTMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return CPO_RTRN_PRC_CALC_BASETMsgArray
     */
    public CPO_RTRN_PRC_CALC_BASETMsgArray getRtrnPrcCalcBaseTMsg(String glblCmpyCd, String cpoOrdNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }

        CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();
        rtrnPrcCalcBaseTMsg.setSQLID("201");
        rtrnPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        rtrnPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (CPO_RTRN_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(rtrnPrcCalcBaseTMsg);
    }

    /**
     * findByKeyWithCache
     * @param reqTMsg EZDTMsg
     * @return EZDTMsg
     */
    private static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {
        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }

    /**
     * callDsCpoUpdateApi
     * @param cpoBean NWZC165001CpoBean
     * @throws S21NwfBizException S21NwfBizException
     */
    public void callDsCpoUpdateApi(NWZC165001CpoBean cpoBean) throws S21NwfBizException {
        NWZC150001PMsg cpoUpdApiMsg = new NWZC150001PMsg();
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList02 = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        cpoUpdApiMsg = setDsCpoUpdateApiPMsg(cpoBean, cpoUpdApiMsg);

        new NWZC150001().execute(cpoUpdApiMsg, cpoUpdApiOutMsgList02, cpoUpdApiOutMsgList03, ONBATCH_TYPE.ONLINE);

        String xxMsgID = chkCpoUpdateApiErrMsg(cpoUpdApiMsg, cpoUpdApiOutMsgList02);
        if (ZYPCommonFunc.hasValue(xxMsgID)) {
             throw new S21NwfBizException(xxMsgID, null);
        }
    }

     /**
     * chkCpoUpdateApiErrMsg
     * @param NWZC165001CpoBean NWZC165001CpoBean
     * @param cpoUpdApiMsg NWZC150001PMsg
     * @param cpoUpdApiOutMsgList List<NWZC150002PMsg>
     * @return String
     */
     private String chkCpoUpdateApiErrMsg(NWZC150001PMsg cpoUpdApiMsg, List<NWZC150002PMsg> cpoUpdApiOutMsgList) {

         for (int i = 0; i < cpoUpdApiMsg.xxMsgIdList.getValidCount(); i++) {

             final String xxMsgId = cpoUpdApiMsg.xxMsgIdList.no(i).xxMsgId.getValue();

             if (xxMsgId.endsWith("E")) {
                 return xxMsgId;
             }
         }

         for (NWZC150002PMsg pMsg : cpoUpdApiOutMsgList) {
             for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {

                 final String xxMsgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();

                 if (xxMsgId.endsWith("E")) {
                     return xxMsgId;
                 }
             }
         }
         return null;
     }

    /**
     * setDsCpoUpdateApiPMsg
     * @param cpoBean NWZC165001CpoBean
     * @param cpoUpdApiReqPMsg NWZC150001PMsg
     * @return NWZC150001PMsg
     * @throws S21NwfBizException S21NwfBizException
     */
    public NWZC150001PMsg setDsCpoUpdateApiPMsg(NWZC165001CpoBean cpoBean, NWZC150001PMsg cpoUpdApiReqPMsg) throws S21NwfBizException {
        final String glblCmpyCd = cpoBean.getGlblCmpyCd();
        final String cpoOrdNum = cpoBean.getCpoOrdNum();

        // ----------------------------------------------------------------------------------
        // set Header
        CPOTMsg cpoTMsg = (CPOTMsg) getCpoTMsg(glblCmpyCd, cpoOrdNum);
        if (cpoTMsg == null) {
            throw new S21NwfBizException("", null);
        }

        CPOTMsg dsCpoTMsg = (CPOTMsg) getDsCpoTMsg(glblCmpyCd, cpoOrdNum);
        if (dsCpoTMsg == null) {
            throw new S21NwfBizException("", null);
        }

        setCpoUpdApiReqPMsg(cpoUpdApiReqPMsg, cpoBean, cpoTMsg, dsCpoTMsg);

        // ----------------------------------------------------------------------------------
        // set CPO Config
        DS_CPO_CONFIGTMsgArray dsCpoConfigTMsgArry = getDsCpoConfigTMsg(glblCmpyCd, cpoOrdNum);
        if (dsCpoConfigTMsgArry.length() == 0) {
            throw new S21NwfBizException("", null);
        }

        for (int i = 0; i < dsCpoConfigTMsgArry.getValidCount(); i++) {
            DS_CPO_CONFIGTMsg configMsg = (DS_CPO_CONFIGTMsg) dsCpoConfigTMsgArry.get(i);
            setConfigUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, configMsg);
        }

        // ----------------------------------------------------------------------------------
        // set CPO Detail and CPO Return Detail
        CPO_DTLTMsgArray cpoDtlTMsgArry = getCpoDtlTMsg(glblCmpyCd, cpoOrdNum);
        CPO_DTLTMsgArray dsCpoDtlTMsgArry = getDsCpoDtlTMsg(glblCmpyCd, cpoOrdNum);
        DS_CPO_RTRN_DTLTMsgArray dsCpoRtrnDtlTMsgArry = getDsCpoRtrnDtlTMsg(glblCmpyCd, cpoOrdNum);

        if (cpoDtlTMsgArry.length() == 0 && dsCpoDtlTMsgArry.length() == 0 && dsCpoRtrnDtlTMsgArry.length() == 0) {
            throw new S21NwfBizException("", null);
        }

        for (int i = 0; i < cpoDtlTMsgArry.getValidCount(); i++) {
            CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) cpoDtlTMsgArry.no(i);

            for (int idx = 0; idx < dsCpoDtlTMsgArry.getValidCount(); idx++) {
                if (cpoDtlTMsg.cpoDtlLineNum.getValue().equals(dsCpoDtlTMsgArry.no(idx).cpoDtlLineNum.getValue()) && cpoDtlTMsg.cpoDtlLineSubNum.getValue().equals(dsCpoDtlTMsgArry.no(idx).cpoDtlLineSubNum.getValue())) {

                    CPO_DTLTMsg dsCpoDtlTMsg = (CPO_DTLTMsg) dsCpoDtlTMsgArry.get(i);
                    setCpoUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, cpoDtlTMsg, dsCpoDtlTMsg);
                    break;
                }
            }
        }

        for (int i = 0; i < dsCpoRtrnDtlTMsgArry.getValidCount(); i++) {
            final DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = dsCpoRtrnDtlTMsgArry.no(i);

            setCpoUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, dsCpoRtrnDtlTMsg);
        }

        // ----------------------------------------------------------------------------------
        // set Order Price Calc Base and Return Price Calc Base
        ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseTMsgArry = getOrdPrcCalcBaseTMsg(glblCmpyCd, cpoOrdNum);
        CPO_RTRN_PRC_CALC_BASETMsgArray rtrnPrcCalcBaseTMsgArry = getRtrnPrcCalcBaseTMsg(glblCmpyCd, cpoOrdNum);

        if (ordPrcCalcBaseTMsgArry.length() == 0 && rtrnPrcCalcBaseTMsgArry.length() == 0) {
            throw new S21NwfBizException("", null);
        }

        for (int i = 0; i < ordPrcCalcBaseTMsgArry.getValidCount(); i++) {
            final ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg = ordPrcCalcBaseTMsgArry.no(i);
            setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, ordPrcCalcBaseTMsg);
        }

        for (int i = 0; i < rtrnPrcCalcBaseTMsgArry.getValidCount(); i++) {
            final CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg = rtrnPrcCalcBaseTMsgArry.no(i);
            setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, rtrnPrcCalcBaseTMsg);
        }

        return cpoUpdApiReqPMsg;
    }

    private void setCpoUpdApiReqPMsg(NWZC150001PMsg pMsg, NWZC165001CpoBean cpoBean, CPOTMsg cpoTMsg, CPOTMsg dsCpoTMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC150001Constant.MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cpoTMsg.cpoOrdTs.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.usrId, cpoBean.getUserId());
        ZYPEZDItemValueSetter.setValue(pMsg.bizAppId, NWZC165001Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, dsCpoTMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, dsCpoTMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, dsCpoTMsg.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, cpoTMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoDt, cpoTMsg.custIssPoDt);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, cpoTMsg.sysSrcCd);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoSrcTpCd, cpoTMsg.cpoSrcTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordFuflLvlCd, cpoTMsg.ordFuflLvlCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, dsCpoTMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, cpoTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, dsCpoTMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(pMsg.sellToCustCd, cpoTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.soldToCustLocCd, dsCpoTMsg.soldToCustLocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.adminPsnCd, cpoTMsg.adminPsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addRddDt, cpoTMsg.addRddDt);
        ZYPEZDItemValueSetter.setValue(pMsg.addRsdDt, cpoTMsg.addRsdDt);
        ZYPEZDItemValueSetter.setValue(pMsg.addShpgSvcLvlCd, cpoTMsg.addShpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addDropShipFlg, cpoTMsg.addDropShipFlg);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, cpoTMsg.addShipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToLocNm, cpoTMsg.addShipToLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToAddlLocNm, cpoTMsg.addShipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFirstLineAddr, cpoTMsg.addShipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToScdLineAddr, cpoTMsg.addShipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToThirdLineAddr, cpoTMsg.addShipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToFrthLineAddr, cpoTMsg.addShipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtyAddr, cpoTMsg.addShipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToStCd, cpoTMsg.addShipToStCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToProvNm, cpoTMsg.addShipToProvNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToPostCd, cpoTMsg.addShipToPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCtryCd, cpoTMsg.addShipToCtryCd);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCntyNm, cpoTMsg.addShipToCntyNm);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo01RefCmntTxt, cpoTMsg.addShipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipTo02RefCmntTxt, cpoTMsg.addShipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.addPmtTermCashDiscCd, cpoTMsg.addPmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, dsCpoTMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSgnDt, dsCpoTMsg.ordSgnDt);
        ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, dsCpoTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, dsCpoTMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, dsCpoTMsg.prcCalcDt);
        ZYPEZDItemValueSetter.setValue(pMsg.negoDealAmt, dsCpoTMsg.negoDealAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.prcCatgCd, dsCpoTMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.flPrcListCd, dsCpoTMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, dsCpoTMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, dsCpoTMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.aquNum, dsCpoTMsg.aquNum);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcImptDt, dsCpoTMsg.ordSrcImptDt);
        ZYPEZDItemValueSetter.setValue(pMsg.ordSrcRefNum, dsCpoTMsg.ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.loanPerDaysAot, dsCpoTMsg.loanPerDaysAot);
        ZYPEZDItemValueSetter.setValue(pMsg.leaseCmpyPoNum, dsCpoTMsg.leaseCmpyPoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.leasePrchOptCd, dsCpoTMsg.leasePrchOptCd);
        ZYPEZDItemValueSetter.setValue(pMsg.leaseTermCd, dsCpoTMsg.leaseTermCd);
        ZYPEZDItemValueSetter.setValue(pMsg.leasePmtFreqCd, dsCpoTMsg.leasePmtFreqCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordLogTpCd, dsCpoTMsg.ordLogTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, dsCpoTMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, dsCpoTMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, dsCpoTMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(pMsg.spclHdlgTpCd, dsCpoTMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtChkNum, dsCpoTMsg.prePmtChkNum);
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtAmt, dsCpoTMsg.prePmtAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.prePmtTpCd, dsCpoTMsg.prePmtTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.crRebilRsnCatgCd, dsCpoTMsg.crRebilRsnCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsCrCardPk, dsCpoTMsg.dsCrCardPk);
        ZYPEZDItemValueSetter.setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.orgRqstDelyDt, dsCpoTMsg.orgRqstDelyDt);
        ZYPEZDItemValueSetter.setValue(pMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.invCmntTxt, getInvTxtCmnt(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum_H()));
    }

    private void setConfigUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, DS_CPO_CONFIGTMsg configTMsg) {

        final int dtlPMsgCount = pMsg.cpoConfig.getValidCount();
        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(dtlPMsgCount);

        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_MODIFY);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsCpoConfigPk, configTMsg.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, configTMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, configTMsg.configCatgCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, configTMsg.configTpCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.svcConfigMstrPk, configTMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlId, configTMsg.mdlId);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, configTMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, configTMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, configTMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, configTMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, cpoConfigPMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, configTMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, configTMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, configTMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, configTMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, configTMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, configTMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, configTMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, configTMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, configTMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, configTMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, configTMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, configTMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, configTMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, configTMsg.shipToCtryCd);

        pMsg.cpoConfig.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, CPO_DTLTMsg cpoDtlTMsg, CPO_DTLTMsg dsCpoDtlTMsg) {

        final int dtlPMsgCount = pMsg.A.getValidCount();
        final NWZC150001_APMsg dtlPMsg = pMsg.A.no(dtlPMsgCount);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_CANCEL);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, cpoDtlTMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, cpoDtlTMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, cpoDtlTMsg.mdseCd);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.dropShipFlg_A1, cpoDtlTMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, cpoDtlTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToLocNm_A1, cpoDtlTMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToAddlLocNm_A1, cpoDtlTMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstLineAddr_A1, cpoDtlTMsg.shipToFirstLineAddr);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdLineAddr_A1, cpoDtlTMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToThirdLineAddr_A1, cpoDtlTMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFrthLineAddr_A1, cpoDtlTMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtyAddr_A1, cpoDtlTMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToStCd_A1, cpoDtlTMsg.shipToStCd);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToProvNm_A1, cpoDtlTMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToPostCd_A1, cpoDtlTMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCtryCd_A1, cpoDtlTMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCntyNm_A1, cpoDtlTMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, cpoDtlTMsg.shipToFirstRefCmntTxt);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToScdRefCmntTxt_A1, cpoDtlTMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, cpoDtlTMsg.ordQty);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, cpoDtlTMsg.ordCustUomQty);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, cpoDtlTMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, cpoDtlTMsg.entDealNetUnitPrcAmt);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_A1, cpoDtlTMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rddDt_A1, cpoDtlTMsg.rddDt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rsdDt_A1, cpoDtlTMsg.rsdDt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.shipCpltCd_A1, cpoDtlTMsg.shipCpltCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.stkStsCd_A1, cpoDtlTMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, cpoDtlTMsg.slsRepOrSlsTeamTocCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custMdseCd_A1, cpoDtlTMsg.custMdseCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, cpoDtlTMsg.custUomCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, dsCpoDtlTMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, dsCpoDtlTMsg.unitNetWt);

        dtlPMsg.xxTotBaseAmt_A1.clear();
        dtlPMsg.xxTotDiscAmt_A1.clear();
        dtlPMsg.xxTotSpclPrcAmt_A1.clear();
        dtlPMsg.xxTotNetDiscAmt_A1.clear();
        dtlPMsg.xxTotNetPrcAmt_A1.clear();
        dtlPMsg.xxTotFrtAmt_A1.clear();
        dtlPMsg.xxTotSpclChrgAmt_A1.clear();
        dtlPMsg.xxTotTaxAmt_A1.clear();
        dtlPMsg.xxSlsAmt_A1.clear();
        dtlPMsg.xxTotAmt_A1.clear();

        ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, dsCpoDtlTMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrNum_A1, dsCpoDtlTMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsContrSqNum_A1, dsCpoDtlTMsg.dsContrSqNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsCpoConfigPk_A1, dsCpoDtlTMsg.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, dsCpoDtlTMsg.dsOrdLineCatgCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, dsCpoDtlTMsg.ordLineSrcCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, dsCpoDtlTMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_A1, dsCpoDtlTMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.serNum_A1, dsCpoDtlTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, dsCpoDtlTMsg.flPrcListCd);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, dsCpoDtlTMsg.dealPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, dsCpoDtlTMsg.funcPrcListPrcAmt);

        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineNum_A1, dsCpoDtlTMsg.refCpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.refCpoDtlLineSubNum_A1, dsCpoDtlTMsg.refCpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dplyLineRefNum_A1, dsCpoDtlTMsg.dplyLineRefNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.crRebilCd_A1, dsCpoDtlTMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, dsCpoDtlTMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyTpCd_A1, dsCpoDtlTMsg.splyTpCd);
        // 2019/10/04 QC#51372 Mod Start
        //ZYPEZDItemValueSetter.setValue(dtlPMsg.splyNm_A1, dsCpoDtlTMsg.splyNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prntVndNm_A1, dsCpoDtlTMsg.prntVndNm);
        // 2019/10/04 QC#51372 Mod End
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyFirstAddr_A1, dsCpoDtlTMsg.splyFirstAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyCtyAddr_A1, dsCpoDtlTMsg.splyCtyAddr);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyStCd_A1, dsCpoDtlTMsg.splyStCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.splyPostCd_A1, dsCpoDtlTMsg.splyPostCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpContrNum_A1, dsCpoDtlTMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dlrRefNum_A1, dsCpoDtlTMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.csmpPrcListCd_A1, dsCpoDtlTMsg.csmpPrcListCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.rntlTrmnDt_A1, dsCpoDtlTMsg.rntlTrmnDt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.bllgAttrbCustAcctCd_A1, dsCpoDtlTMsg.bllgAttrbCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbNm_A1, dsCpoDtlTMsg.firstBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.firstBllgAttrbValTxt_A1, dsCpoDtlTMsg.firstBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbNm_A1, dsCpoDtlTMsg.scdBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.scdBllgAttrbValTxt_A1, dsCpoDtlTMsg.scdBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbNm_A1, dsCpoDtlTMsg.thirdBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.thirdBllgAttrbValTxt_A1, dsCpoDtlTMsg.thirdBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbNm_A1, dsCpoDtlTMsg.frthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.frthBllgAttrbValTxt_A1, dsCpoDtlTMsg.frthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbNm_A1, dsCpoDtlTMsg.fifthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.fifthBllgAttrbValTxt_A1, dsCpoDtlTMsg.fifthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbNm_A1, dsCpoDtlTMsg.sixthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sixthBllgAttrbValTxt_A1, dsCpoDtlTMsg.sixthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.sbstMdseCd_A1, dsCpoDtlTMsg.sbstMdseCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, dsCpoDtlTMsg.ordSrcRefLineNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineSubNum_A1, dsCpoDtlTMsg.ordSrcRefLineSubNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, dsCpoDtlTMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, dsCpoDtlTMsg.supdLockFlg);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcListEquipConfigNum_A1, dsCpoDtlTMsg.prcListEquipConfigNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, cpoDtlTMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.loanBalQty_A1, dsCpoDtlTMsg.loanBalQty);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcRevTrnsfAmt_A1, dsCpoDtlTMsg.funcSvcRevTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcRevTrnsfAmt_A1, dsCpoDtlTMsg.dealSvcRevTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcSvcCostTrnsfAmt_A1, dsCpoDtlTMsg.funcSvcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealSvcCostTrnsfAmt_A1, dsCpoDtlTMsg.dealSvcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManFlAdjAmt_A1, dsCpoDtlTMsg.funcManFlAdjAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManFlAdjAmt_A1, dsCpoDtlTMsg.dealManFlAdjAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcManRepRevAdjAmt_A1, dsCpoDtlTMsg.funcManRepRevAdjAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.dealManRepRevAdjAmt_A1, dsCpoDtlTMsg.dealManRepRevAdjAmt);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.ordUpldRefNum_A1, dsCpoDtlTMsg.ordUpldRefNum);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, dsCpoDtlTMsg.baseCmptFlg);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.svcConfigMstrPk_A1, dsCpoDtlTMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(dtlPMsg.funcUnitFlPrcAmt_A1, dsCpoDtlTMsg.funcUnitFlPrcAmt); // 2018/01/11 S21_NA#22372 Add

        pMsg.A.setValidCount(dtlPMsgCount + 1);
    }

    private void setCpoUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {

        final int dtlPMsgCount = pMsg.rtnDtl.getValidCount();
        final NWZC150001_rtnDtlPMsg rtrnDtlPMsg = pMsg.rtnDtl.no(dtlPMsgCount);

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.xxRqstTpCd_B1, NWZC150001Constant.RQST_TP_RTRN_DTL_CANCEL);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.cpoDtlLineNum_B1, dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.cpoDtlLineSubNum_B1, dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dsCpoConfigPk_B1, dsCpoRtrnDtlTMsg.dsCpoConfigPk);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dsOrdPosnNum_B1, dsCpoRtrnDtlTMsg.dsOrdPosnNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dsOrdLineCatgCd_B1, dsCpoRtrnDtlTMsg.dsOrdLineCatgCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ordLineSrcCd_B1, dsCpoRtrnDtlTMsg.ordLineSrcCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.mdseCd_B1, dsCpoRtrnDtlTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.origMdseCd_B1, dsCpoRtrnDtlTMsg.origMdseCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.custMdseCd_B1, dsCpoRtrnDtlTMsg.custMdseCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.baseCmptFlg_B1, dsCpoRtrnDtlTMsg.baseCmptFlg);

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dropShipFlg_B1, dsCpoRtrnDtlTMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToCustCd_B1, dsCpoRtrnDtlTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToLocNm_B1, dsCpoRtrnDtlTMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToAddlLocNm_B1, dsCpoRtrnDtlTMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToFirstLineAddr_B1, dsCpoRtrnDtlTMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToScdLineAddr_B1, dsCpoRtrnDtlTMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToThirdLineAddr_B1, dsCpoRtrnDtlTMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToFrthLineAddr_B1, dsCpoRtrnDtlTMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToFirstRefCmntTxt_B1, dsCpoRtrnDtlTMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToScdRefCmntTxt_B1, dsCpoRtrnDtlTMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToCtyAddr_B1, dsCpoRtrnDtlTMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToStCd_B1, dsCpoRtrnDtlTMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToProvNm_B1, dsCpoRtrnDtlTMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToCntyNm_B1, dsCpoRtrnDtlTMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToPostCd_B1, dsCpoRtrnDtlTMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.shipToCtryCd_B1, dsCpoRtrnDtlTMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ordQty_B1, dsCpoRtrnDtlTMsg.ordQty);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ordCustUomQty_B1, dsCpoRtrnDtlTMsg.ordCustUomQty);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.invtyLocCd_B1, dsCpoRtrnDtlTMsg.invtyLocCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.rtlWhCd_B1, dsCpoRtrnDtlTMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.rtlSwhCd_B1, dsCpoRtrnDtlTMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.stkStsCd_B1, dsCpoRtrnDtlTMsg.stkStsCd);

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.prcBaseDt_B1, dsCpoRtrnDtlTMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.prcCatgCd_B1, dsCpoRtrnDtlTMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.flPrcListCd_B1, dsCpoRtrnDtlTMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.entDealNetUnitPrcAmt_B1, dsCpoRtrnDtlTMsg.entDealNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dealPrcListPrcAmt_B1, dsCpoRtrnDtlTMsg.dealPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.funcPrcListPrcAmt_B1, dsCpoRtrnDtlTMsg.funcPrcListPrcAmt);

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ccyCd_B1, dsCpoRtrnDtlTMsg.ccyCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.slsRepOrSlsTeamTocCd_B1, dsCpoRtrnDtlTMsg.slsRepOrSlsTeamTocCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.serNum_B1, dsCpoRtrnDtlTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.rqstPickUpDt_B1, dsCpoRtrnDtlTMsg.rqstPickUpDt);

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.cpoDtlCancFlg_B1, dsCpoRtrnDtlTMsg.cpoDtlCancFlg);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.pmtTermCd_B1, dsCpoRtrnDtlTMsg.pmtTermCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.crRebilCd_B1, dsCpoRtrnDtlTMsg.crRebilCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.machConfigNum_B1, dsCpoRtrnDtlTMsg.machConfigNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.svcConfigMstrPk_B1, dsCpoRtrnDtlTMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dsContrNum_B1, dsCpoRtrnDtlTMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dsContrSqNum_B1, dsCpoRtrnDtlTMsg.dsContrSqNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.svcMachMstrPk_B1, dsCpoRtrnDtlTMsg.svcMachMstrPk);

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.refCpoDtlLineNum_B1, dsCpoRtrnDtlTMsg.refCpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.refCpoDtlLineSubNum_B1, dsCpoRtrnDtlTMsg.refCpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dplyLineRefNum_B1, dsCpoRtrnDtlTMsg.dplyLineRefNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.csmpContrNum_B1, dsCpoRtrnDtlTMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dlrRefNum_B1, dsCpoRtrnDtlTMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.csmpPrcListCd_B1, dsCpoRtrnDtlTMsg.csmpPrcListCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.hddRmvCd_B1, dsCpoRtrnDtlTMsg.hddRmvCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.rtrnRsnCd_B1, dsCpoRtrnDtlTMsg.rtrnRsnCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.taxFlg_B1, dsCpoRtrnDtlTMsg.taxFlg);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.lineNetWt_B1, dsCpoRtrnDtlTMsg.unitNetWt);

        rtrnDtlPMsg.basePrcAmt_B1.clear();
        rtrnDtlPMsg.discPrcAmt_B1.clear();
        rtrnDtlPMsg.xxTotSpclPrcAmt_B1.clear();
        rtrnDtlPMsg.xxTotNetDiscAmt_B1.clear();
        rtrnDtlPMsg.netAmt_B1.clear();
        rtrnDtlPMsg.totFrtAmt_B1.clear();
        rtrnDtlPMsg.xxTotSpclChrgAmt_B1.clear();
        rtrnDtlPMsg.xxTotTaxAmt_B1.clear();
        rtrnDtlPMsg.netAmt_B2.clear();
        rtrnDtlPMsg.rtrnTotAmt_B1.clear();

        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.bllgAttrbCustAcctCd_B1, dsCpoRtrnDtlTMsg.bllgAttrbCustAcctCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.firstBllgAttrbNm_B1, dsCpoRtrnDtlTMsg.firstBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.firstBllgAttrbValTxt_B1, dsCpoRtrnDtlTMsg.firstBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.scdBllgAttrbNm_B1, dsCpoRtrnDtlTMsg.scdBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.scdBllgAttrbValTxt_B1, dsCpoRtrnDtlTMsg.scdBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.thirdBllgAttrbNm_B1, dsCpoRtrnDtlTMsg.thirdBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.thirdBllgAttrbValTxt_B1, dsCpoRtrnDtlTMsg.thirdBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.frthBllgAttrbNm_B1, dsCpoRtrnDtlTMsg.frthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.frthBllgAttrbValTxt_B1, dsCpoRtrnDtlTMsg.frthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.fifthBllgAttrbNm_B1, dsCpoRtrnDtlTMsg.fifthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.fifthBllgAttrbValTxt_B1, dsCpoRtrnDtlTMsg.fifthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.sixthBllgAttrbNm_B1, dsCpoRtrnDtlTMsg.sixthBllgAttrbNm);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.sixthBllgAttrbValTxt_B1, dsCpoRtrnDtlTMsg.sixthBllgAttrbValTxt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ordSrcRefLineNum_B1, dsCpoRtrnDtlTMsg.ordSrcRefLineNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ordSrcRefLineSubNum_B1, dsCpoRtrnDtlTMsg.ordSrcRefLineSubNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.funcSvcRevTrnsfAmt_B1, dsCpoRtrnDtlTMsg.funcSvcRevTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dealSvcRevTrnsfAmt_B1, dsCpoRtrnDtlTMsg.dealSvcRevTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.funcSvcCostTrnsfAmt_B1, dsCpoRtrnDtlTMsg.funcSvcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dealSvcCostTrnsfAmt_B1, dsCpoRtrnDtlTMsg.dealSvcCostTrnsfAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.funcManFlAdjAmt_B1, dsCpoRtrnDtlTMsg.funcManFlAdjAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dealManFlAdjAmt_B1, dsCpoRtrnDtlTMsg.dealManFlAdjAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.funcManRepRevAdjAmt_B1, dsCpoRtrnDtlTMsg.funcManRepRevAdjAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.dealManRepRevAdjAmt_B1, dsCpoRtrnDtlTMsg.dealManRepRevAdjAmt);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.ordUpldRefNum_B1, dsCpoRtrnDtlTMsg.ordUpldRefNum);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.thirdPtyDspTpCd_B1, dsCpoRtrnDtlTMsg.thirdPtyDspTpCd);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.rtrnQty_B1, dsCpoRtrnDtlTMsg.rtrnQty);
        ZYPEZDItemValueSetter.setValue(rtrnDtlPMsg.funcUnitFlPrcAmt_B1, dsCpoRtrnDtlTMsg.funcUnitFlPrcAmt); // 2018/01/11 S21_NA#22372 Add

        // 2017/10/16 S21_NA#21511 Mod Start
        //pMsg.A.setValidCount(dtlPMsgCount + 1);
        pMsg.rtnDtl.setValidCount(dtlPMsgCount + 1);
        // 2017/10/16 S21_NA#21511 Mod End
    }

    private void setCpoUpdApiReqPriceCalcBase(NWZC150001PMsg cpoUpdApiReqPMsg, ORD_PRC_CALC_BASETMsg ordPrcCalcBaseTMsg) {
        int priceListPMsgCount = cpoUpdApiReqPMsg.priceList.getValidCount();
        NWZC150001_priceListPMsg priceListPMsg = cpoUpdApiReqPMsg.priceList.no(priceListPMsgCount);

        // QC#9700  2018/09/20 Upd Start
        EZDMsg.copy(ordPrcCalcBaseTMsg, null, priceListPMsg, null);
        ZYPEZDItemValueSetter.setValue(priceListPMsg.ordPrcCalcBasePk, ordPrcCalcBaseTMsg.ordPrcCalcBasePk);
        ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, ordPrcCalcBaseTMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, ordPrcCalcBaseTMsg.cpoDtlLineSubNum);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondTpCd, ordPrcCalcBaseTMsg.prcCondTpCd);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcDtlGrpCd, ordPrcCalcBaseTMsg.prcDtlGrpCd);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcJrnlGrpCd, ordPrcCalcBaseTMsg.prcJrnlGrpCd);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManEntryFlg, ordPrcCalcBaseTMsg.prcCondManEntryFlg);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManAddFlg, ordPrcCalcBaseTMsg.prcCondManAddFlg);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondManDelFlg, ordPrcCalcBaseTMsg.prcCondManDelFlg);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.pkgUomCd, ordPrcCalcBaseTMsg.pkgUomCd);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCondUnitCd, ordPrcCalcBaseTMsg.prcCondUnitCd);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.prcCalcMethCd, ordPrcCalcBaseTMsg.prcCalcMethCd);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcAmtRate, ordPrcCalcBaseTMsg.autoPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.maxPrcAmtRate, ordPrcCalcBaseTMsg.maxPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.minPrcAmtRate, ordPrcCalcBaseTMsg.minPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.manPrcAmtRate, ordPrcCalcBaseTMsg.manPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.calcPrcAmtRate, ordPrcCalcBaseTMsg.calcPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.unitPrcAmt, ordPrcCalcBaseTMsg.unitPrcAmt);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.dsMdsePrcPk, ordPrcCalcBaseTMsg.dsMdsePrcPk);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.specCondPrcPk, ordPrcCalcBaseTMsg.specCondPrcPk);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.frtPerWtPk, ordPrcCalcBaseTMsg.frtPerWtPk);
//        ZYPEZDItemValueSetter.setValue(priceListPMsg.autoPrcCcyCd, ordPrcCalcBaseTMsg.autoPrcCcyCd);
        // QC#9700  2018/09/20 Upd End

        cpoUpdApiReqPMsg.priceList.setValidCount(priceListPMsgCount + 1);
    }

    private void setCpoUpdApiReqPriceCalcBase(NWZC150001PMsg cpoUpdApiReqPMsg, CPO_RTRN_PRC_CALC_BASETMsg rtrnPrcCalcBaseTMsg) {
        int rtrnPrcListPMsgCount = cpoUpdApiReqPMsg.rtnPriceList.getValidCount();
        NWZC150001_rtnPriceListPMsg rtnPriceListPMsg = cpoUpdApiReqPMsg.rtnPriceList.no(rtrnPrcListPMsgCount);

        // QC#9700  2018/09/20 Upd Start
        EZDMsg.copy(rtrnPrcCalcBaseTMsg, null, rtnPriceListPMsg, null);
        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.ordPrcCalcBasePk, rtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk);
        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.cpoDtlLineNum, rtrnPrcCalcBaseTMsg.dsCpoRtrnLineNum);
        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.cpoDtlLineSubNum, rtrnPrcCalcBaseTMsg.dsCpoRtrnLineSubNum);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondTpCd, rtrnPrcCalcBaseTMsg.prcCondTpCd);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcDtlGrpCd, rtrnPrcCalcBaseTMsg.prcDtlGrpCd);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcJrnlGrpCd, rtrnPrcCalcBaseTMsg.prcJrnlGrpCd);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondManEntryFlg, rtrnPrcCalcBaseTMsg.prcCondManEntryFlg);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondManAddFlg, rtrnPrcCalcBaseTMsg.prcCondManAddFlg);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondManDelFlg, rtrnPrcCalcBaseTMsg.prcCondManDelFlg);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.pkgUomCd, rtrnPrcCalcBaseTMsg.pkgUomCd);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCondUnitCd, rtrnPrcCalcBaseTMsg.prcCondUnitCd);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.prcCalcMethCd, rtrnPrcCalcBaseTMsg.prcCalcMethCd);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.autoPrcAmtRate, rtrnPrcCalcBaseTMsg.autoPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.maxPrcAmtRate, rtrnPrcCalcBaseTMsg.maxPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.minPrcAmtRate, rtrnPrcCalcBaseTMsg.minPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.manPrcAmtRate, rtrnPrcCalcBaseTMsg.manPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.calcPrcAmtRate, rtrnPrcCalcBaseTMsg.calcPrcAmtRate);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.unitPrcAmt, rtrnPrcCalcBaseTMsg.unitPrcAmt);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.dsMdsePrcPk, rtrnPrcCalcBaseTMsg.dsMdsePrcPk);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.specCondPrcPk, rtrnPrcCalcBaseTMsg.specCondPrcPk);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.frtPerWtPk, rtrnPrcCalcBaseTMsg.frtPerWtPk);
//        ZYPEZDItemValueSetter.setValue(rtnPriceListPMsg.autoPrcCcyCd, rtrnPrcCalcBaseTMsg.autoPrcCcyCd);
        // QC#9700  2018/09/20 Upd End

        cpoUpdApiReqPMsg.rtnPriceList.setValidCount(rtrnPrcListPMsgCount + 1);
    }

    private boolean isCreditHardHoldCustomer(NWZC165001CpoBean cpoBean) {

        CPOTMsg cpo = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpo.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(cpo.cpoOrdNum, cpoBean.getCpoOrdNum());
        cpo = (CPOTMsg) S21FastTBLAccessor.findByKey(cpo);
        if (cpo == null) {

            return false;
        }

        // location level
        BILL_TO_CUSTTMsg location = getLocationInfo(cpo.glblCmpyCd.getValue(), cpo.payerCustCd.getValue());
        if (location != null) {

            CUST_CR_PRFLTMsg dsCustCrPrfl = new CUST_CR_PRFLTMsg();
            ZYPEZDItemValueSetter.setValue(dsCustCrPrfl.glblCmpyCd, location.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCustCrPrfl.billToCustPk, location.billToCustPk);
            dsCustCrPrfl = (CUST_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(dsCustCrPrfl);
            if (dsCustCrPrfl != null) {

                if (S21StringUtil.isEquals(dsCustCrPrfl.custHardHldFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                    return true;
                }
            }
        }

        // account level
        DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = new DS_ACCT_CR_PRFLTMsg();
        // 2017/09/26 S21_NA#21124 Mod Start
//        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, location.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.glblCmpyCd, cpo.glblCmpyCd);
        // 2017/09/26 S21_NA#21124 Mod End
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrfl.dsAcctNum, cpo.billToCustAcctCd);
        dsAcctCrPrfl = (DS_ACCT_CR_PRFLTMsg) S21FastTBLAccessor.findByKey(dsAcctCrPrfl);
        if (dsAcctCrPrfl != null) {

            if (S21StringUtil.isEquals(dsAcctCrPrfl.custHardHldFlg.getValue(), ZYPConstant.FLG_ON_Y)) {

                return true;
            }
        }
        return false;
    }

    private BILL_TO_CUSTTMsg getLocationInfo(String glblCmpyCd, String locCd) {

        final BILL_TO_CUSTTMsg condition = new BILL_TO_CUSTTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("billToCustCd01", locCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        BILL_TO_CUSTTMsgArray tmsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);

        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0);
        }

        return null;
    }

    // 2019/01/18 S21_NA#28773 Add Start
    private boolean isRejectOrder(S21NwfTokenObj tokenObj) {

        String tokenRejectComment = tokenObj.getComment();
        String varcharRejectComment = getRejectComment(tokenObj.getGlblCmpyCd());
        if (ZYPCommonFunc.hasValue(tokenRejectComment) //
                && ZYPCommonFunc.hasValue(varcharRejectComment) //
                && S21StringUtil.isEquals(tokenRejectComment, varcharRejectComment)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Get Default Reject Comment.
     * @param glblCmpyCd Global Company Code
     * @return Default Reject Comment.
     * </pre>
     */
    private String getRejectComment(String glblCmpyCd) {

        String constRejectComment = null;
        if (ZYPCommonFunc.hasValue(glblCmpyCd)) {
            constRejectComment = ZYPCodeDataUtil.getVarCharConstValue(NWZC1770_WF_REJ_CMNT, glblCmpyCd);
        }
        if (ZYPCommonFunc.hasValue(constRejectComment)) {
            constRejectComment = FIXED_REJECT_COMMENT;
        }
        return constRejectComment;
    }
    // 2019/01/18 S21_NA#28773 Add End

    // 2019/01/30 S21_NA#28799 Add Start
    // mod start 2022/11/14 QC#60406
    //private void setCollectionText(NWZC165001CpoBean cpoBean) {
    private void setCollectionText(S21NwfContext context, NWZC165001CpoBean cpoBean, String name, String docId) {
    // mod end 2022/11/14 QC#60406
        CPO_VTMsg cpoVTMsg = getCpoVTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
        if (cpoVTMsg == null) {
            return;
        }

        String cratUserName = getCratUserName(cpoVTMsg);
        String rejectedUserName = getRejectUserName(cpoBean.getGlblCmpyCd(), cpoBean.getUserId());
        // del start 2022/11/14 QC#60406
        //String cltNoteTpCd = CLT_NOTE_TP.REJECTED_CREDIT_REVIEW;
        // del end 2022/11/14 QC#60406
        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        // del start 2022/11/14 QC#60406
        //ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, cpoBean.getGlblCmpyCd());
        //ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, cltNoteTpCd);
        //cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);
        // del end 2022/11/14 QC#60406
        String colNoteSubjTxt = "";
        // add start 2022/11/14 QC#60406
        String cltNoteTpCd = null;
        String dtlNoteTxtTmpl = null;
        String dtlNoteTxt = null;
        String wfComment = null;
        wfComment = getWfComment(context, docId);

        if (NWZC165001Constant.APPROVE.equals(name)) {
            cltNoteTpCd = CLT_NOTE_TP.APPROVED_CREDIT_REVIEW;
            ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, cltNoteTpCd);
            cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);
            dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NWZC1650_APVL_CLT_DTL_NOTE_TXT, cpoBean.getGlblCmpyCd());
            if (cltNoteTp != null && ZYPCommonFunc.hasValue(cltNoteTp.cltNoteTpDescTxt)) {
                colNoteSubjTxt = cltNoteTp.cltNoteTpDescTxt.getValue();
            }
            if (dtlNoteTxtTmpl == null) {
                dtlNoteTxtTmpl = NWZC1650_APVL_DTL_NOTE_TXT_TMPL;
            }
            dtlNoteTxt = String.format(dtlNoteTxtTmpl, wfComment, cpoBean.getCpoOrdNum(), cpoVTMsg.ordTotDealNetAmt.getValue(), cratUserName, rejectedUserName);
        }else if (NWZC165001Constant.REJECT.equals(name)){
            cltNoteTpCd = CLT_NOTE_TP.REJECTED_CREDIT_REVIEW;
            ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, cltNoteTpCd);
            cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);
            dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NWZC1650_CLT_DTL_NOTE_TXT, cpoBean.getGlblCmpyCd());
        // add end 2022/11/14 QC#60406
            if (cltNoteTp != null && ZYPCommonFunc.hasValue(cltNoteTp.cltNoteTpDescTxt)) {
                colNoteSubjTxt = cltNoteTp.cltNoteTpDescTxt.getValue();
            }
            // del start 2022/11/14 QC#60406
            //String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NWZC1650_CLT_DTL_NOTE_TXT, cpoBean.getGlblCmpyCd());
            // del end 2022/11/14 QC#60406
            if (dtlNoteTxtTmpl == null) {
                dtlNoteTxtTmpl = NWZC1650_DTL_NOTE_TXT_TMPL;
            }
            // mod start 2022/11/14 QC#60406
            //String dtlNoteTxt = String.format(dtlNoteTxtTmpl, cpoBean.getAprvCmnt(), cpoBean.getCpoOrdNum(), cpoVTMsg.ordTotDealNetAmt.getValue(), cratUserName, rejectedUserName);
            if(hasValue(wfComment)){
                String separator = System.getProperty("line.separator");
                StringBuilder sb = new StringBuilder();
                sb.append(wfComment);
                sb.append(separator);
                // add start 2023/05/12 QC#61396
                sb.append("Comment:");
                // add end 2023/05/12 QC#61396
                sb.append(cpoBean.getAprvCmnt());
                // add start 2023/05/12 QC#61396
                sb.append(separator);
                sb.append("By ");
                sb.append(cpoBean.getUserId());
                sb.append(" ");
                sb.append(rejectedUserName);
                // add end 2023/05/12 QC#61396
                wfComment = sb.toString();
            } else{
                // mod Start 2023/05/12 QC#61396
                String separator = System.getProperty("line.separator");
                StringBuilder sb = new StringBuilder();
                //wfComment = cpoBean.getAprvCmnt();
                sb.append(cpoBean.getAprvCmnt());
                sb.append(separator);
                sb.append("By ");
                sb.append(cpoBean.getUserId());
                sb.append(" ");
                sb.append(rejectedUserName);
                wfComment = sb.toString();
                // mod Start 2023/05/12 QC#61396
            }
            dtlNoteTxt = String.format(dtlNoteTxtTmpl, wfComment, cpoBean.getCpoOrdNum(), cpoVTMsg.ordTotDealNetAmt.getValue(), cratUserName, rejectedUserName);
            // mod end 2022/11/14 QC#60406
        }
        CLT_NOTE_DTLTMsg cltNoteDtlMsg = new CLT_NOTE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctCd, cpoVTMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctTpCd, CLT_ACCT_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratUsrId, cpoBean.getUserId());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteSubjTxt, colNoteSubjTxt);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteTpCd, cltNoteTpCd);

        int dtlNoteMaxLength = cltNoteDtlMsg.getAttr("dtlNoteTxt").getDigit();
        if (dtlNoteTxt.length() > dtlNoteMaxLength) {
            dtlNoteTxt = dtlNoteTxt.substring(0, dtlNoteMaxLength);
        }
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.dtlNoteTxt, dtlNoteTxt);

        EZDTBLAccessor.create(cltNoteDtlMsg);

    }     
    // add start 2022/11/14 QC#60406
    private String getWfComment(S21NwfContext context, String docId){
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String separator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        try {
            String wfId = ZYPCodeDataUtil.getVarCharConstValue("CR_APVL_WF_ID", glblCmpyCd);
            List<S21NwfProcess> procs = context.getProcessForBiz(wfId, docId);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizHistWorkItem> history = p.getHistory();
                for (S21NwfUtilBizHistWorkItem wi : history) {
                    if (wi.isApprovable()) {
                        if (hasValue(sb.toString())) {
                            sb.append(separator);
                        }
                        // add start 2023/05/12 QC#61396
                        if (wi.getActName().equals(APPROVE)) {
                        // add end 2023/05/12 QC#61396
                        sb.append(wi.getWorkItemName());
                        sb.append(":");
                        // add start 2023/05/12 QC#61396
                        }
                        // add end 2023/05/12 QC#61396
                        String wfComment = wi.getComment();
                        if (hasValue(wfComment)) {
                            sb.append(wfComment);
                            // add start 2023/05/12 QC#61396
                            sb.append(separator);
                            // add end 2023/05/12 QC#61396
                        }
                        String userNm = getRejectUserName(glblCmpyCd, wi.getActOpUser());
                        if (hasValue(userNm)) {
                            // mod Start 2023/05/12 QC#61396
                            // sb.append(" By ");
                            sb.append("By ");
                            // mod End 2023/05/12 QC#61396
                            sb.append(wi.getActOpUser());
                            sb.append(" ");
                            sb.append(userNm);
                        }
                    }
                }
            }
            return sb.toString();

        } catch (S21NwfSystemException e) {
            return "";
        }
    }

    private String getDocId(S21NwfTokenObj tokenObj) {
        NWZC164001TokenObject tokenBizObj = (NWZC164001TokenObject) tokenObj;
        return tokenBizObj.getDocId();
    }
    // add end 2022/11/14 QC#60406

    private CPO_VTMsg getCpoVTMsg(String glblCmpyCd, String cpoOrdNum) {

        CPO_VTMsg cpoVTMsg = new CPO_VTMsg();
        cpoVTMsg.setSQLID("501");
        cpoVTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        cpoVTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        CPO_VTMsgArray rsltArray = (CPO_VTMsgArray) EZDTBLAccessor.findByCondition(cpoVTMsg);

        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        } else {
            return rsltArray.no(0);
        }
    }

    private String getCratUserName(CPO_VTMsg cpoVTMsg) {

        String cratUserNm = cpoVTMsg.cratUsrNm.getValue();
        if (!ZYPCommonFunc.hasValue(cratUserNm)) {
            return "";
        }
        int beginIndex = cratUserNm.indexOf("(");
        if (beginIndex < 0) {
            beginIndex = 0;
        } else {
            beginIndex++;
        }
        int endIndex = cratUserNm.indexOf(")");
        if (endIndex < 0) {
            endIndex = cratUserNm.length();
        }
        return cratUserNm.substring(beginIndex, endIndex);
    }

    private String getRejectUserName(String glblCmpyCd, String userId) {

        String rslt = null;
        AUTH_PSNTMsg authPsnCondTMsg = new AUTH_PSNTMsg();
        authPsnCondTMsg.setSQLID("053");
        authPsnCondTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        authPsnCondTMsg.setConditionValue("usrNm01", userId);

        AUTH_PSNTMsgArray authPsnTMsgArray = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(authPsnCondTMsg);
        if (authPsnTMsgArray != null && authPsnTMsgArray.getValidCount() > 0) {
            AUTH_PSNTMsg authPsnTMsg = authPsnTMsgArray.no(0);
            StringBuilder userFullName = new StringBuilder("");
            if (ZYPCommonFunc.hasValue(authPsnTMsg.firstNm)) {
                userFullName.append(authPsnTMsg.firstNm.getValue() + " ");
            }
            // No need middle name -->
//            if (ZYPCommonFunc.hasValue(authPsnTMsg.midNm)) {
//                userFullName.append(authPsnTMsg.midNm.getValue() + " ");
//            }
            // No need middle name <--
            if (ZYPCommonFunc.hasValue(authPsnTMsg.lastNm)) {
                userFullName.append(authPsnTMsg.lastNm.getValue());
            }
            rslt = userFullName.toString().trim();
        }
        if (rslt == null) {
            rslt = "";
        }
        return rslt;
    }
    // 2019/01/30 S21_NA#28799 Add End
}
