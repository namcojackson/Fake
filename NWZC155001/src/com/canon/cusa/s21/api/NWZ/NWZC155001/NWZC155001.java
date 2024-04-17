package com.canon.cusa.s21.api.NWZ.NWZC155001;

import static com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant.PROCESS_MODE_TRANSACTION;
import static com.canon.cusa.s21.api.NWZ.NWZC155001.constant.NWZC155001Constant.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_DI_CHK_RSLT_DTLTMsg;
import business.db.DS_CPO_DI_CHK_RSLT_HDRTMsg;
import business.db.DS_CPO_DI_CHK_RSLT_HDRTMsgArray;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsgArray;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASETMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.TOCTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC610001_TransactionRuleListPMsg;
import business.parts.NWZC033001PMsg;
import business.parts.NWZC044001PMsg;
import business.parts.NWZC155001PMsg;
import business.parts.NWZC156001PMsg;
import business.parts.NWZC156001_svcConfigRefPMsg;
import business.parts.NWZC156002PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC181001PMsg;
import business.parts.NWZC181002PMsgArray;
import business.parts.NWZC188001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.api.NWZ.NWZC044001.NWZC044001;
import com.canon.cusa.s21.api.NWZ.NWZC156001.NWZC156001;
import com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC181001.NWZC181001;
import com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC188001.NWZC188001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DI_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SPCL_HDLG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_PRFT_TRX_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRC_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 * 2015/12/23   Fujitsu         S.Takami        Update          S21_NA#2391
 * 2016/01/15   Fujitsu         T.ishii         Update          S21_NA#3184
 * 2016/02/19   Fujitsu         S.Ohki          Update          S21_NA#1686
 * 2016/03/01   SRAA            K.Aratani       Update          S21_NA#4692
 * 2016/03/02   Fujitsu         S.Ohki          Update          S21_NA#4593
 * 2016/03/02   Fujitsu         S.Ohki          Update          S21_NA#5000
 * 2016/03/30   SRAA            K.Aratani       Update          S21_NA#6274
 * 2016/05/05   SRAA            K.Aratani       Update          S21_NA#7943,7944
 * 2016/05/05   SRAA            K.Aratani       Update          S21_NA#8400, 4692-Reopen
 * 2016/05/23   SRAA            K.Aratani       Update          S21_NA#8838
 * 2016/05/25   SRAA            E.Inada         Update          S21_NA#8549
 * 2016/06/08   SRAA            E.Inada         Update          S21_NA#4657
 * 2016/06/29   SRAA            E.Inada         Update          S21_NA#10734
 * 2016/07/11   Fujitsu         S.Iidaka        Update          S21_NA#11552
 * 2016/07/14   Fujitsu         K.Sato          Update          S21_NA#8389
 * 2016/07/19   Fujitsu         K.Sato          Update          S21_NA#7123/10441
 * 2016/07/20   Fujitsu         K.Sato          Update          S21_NA#8611
 * 2016/07/21   Fujitsu         K.Sato          Update          S21_NA#5383
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/31   SRAA            K.Aratani       Update          S21_NA#13364
 * 2016/09/01   Fujitsu         Y.Taoka         Update          S21_NA#7942
 * 2016/09/02   Fujitsu         T.Murai         Update          S21_NA#13813
 * 2016/09/07   Hitachi         T.Mizuki        Update          S21_NA#11774
 * 2016/09/30   Fujitsu         N.Aoyama        Update          S21_NA#14779
 * 2016/10/05   Fujitsu         M.Yamada        Update          S21_NA#14940
 * 2016/10/19   SRAA            K.Aratani       Update          S21_NA#15439
 * 2016/10/20   Fujitsu         W.Honda         Update          S21_NA#15193
 * 2016/10/21   SRAA            K.Aratani       Update          S21_NA#15509
 * 2016/11/01   Fujitsu         S.Takami        Update          S21_NA#15665
 * 2016/11/01   Fujitsu         N.Sugiura       Update          S21_NA#15682
 * 2016/11/01   Fujitsu         S.Takami        Update          S21_NA#15665-2
 * 2016/11/05   Fujitsu         Y.Kanefusa      Update          S21_NA#15652
 * 2016/11/15   Fujitsu         Y.Kanefusa      Update          S21_NA#15960
 * 2016/11/16   Fujitsu         M.Yamada        Update          S21_NA#15652
 * 2016/12/13   Fujitsu         M.Ohno          Update          S21_NA#16479
 * 2017/01/18   Fujitsu         S.Takami        Update          S21_NA#17138
 * 2017/02/06   Fujitsu         T.Yoshida       Update          S21_NA#15510
 * 2017/02/28   Fujitsu         N.Aoyama        Update          S21_NA#16575
 * 2017/03/15   Fujitsu         S.Ohki          Update          S21_NA#18017
 * 2017/05/11   Fujitsu         S.Ohki          Update          RS#5899,5900,5902,5903,5904,5905,5906,5907,7259,8357
 * 2017/06/07   Fujitsu         S.Ohki          Update          S21_NA#18843
 * 2017/06/08   Fujitsu         R.Nakamura      Update          S21_NA#18936
 * 2017/06/09   Fujitsu         R.Nakamura      Update          S21_NA#18549
 * 2017/07/11   Fujitsu         A.Kosai         Update          S21_NA#19218
 * 2017/07/13   Fujitsu         S.Takami        Update          S21_NA#19888
 * 2017/07/18   Fujitsu         S.Takami        Update          S21_NA#19983
 * 2017/07/19   Fujitsu         Y.kanefusa      Update          S21_NA#19984
 * 2017/07/21   Fujitsu         W.Honda         Update          S21_NA#20039
 * 2017/07/25   Fujitsu         A.Kosai         Update          S21_NA#19920
 * 2017/08/03   Fujitsu         S.Ohki          Update          S21_NA#20374
 * 2017/08/10   Fujitsu         S.Ohki          Update          S21_NA#19970
 * 2017/08/21   Fujitsu         S.Ohki          Update          S21_NA#20374
 * 2017/08/15   Hitachi         T.Kanasaka      Update          S21_NA#18193
 * 2017/08/22   Fujitsu         S.Ohki          Update          S21_NA#20022
 * 2017/09/13   Fujitsu         S.Ohki          Update          S21_NA#21057
 * 2017/09/15   Fujitsu         S.Ohki          Update          S21_NA#21040
 * 2017/09/16   Fujitsu         S.Ohki          Update          S21_NA#21064
 * 2017/10/11   Fujitsu         S.Ohki          Update          S21_NA#21469
 * 2017/10/24   Fujitsu         S.Takami        Update          S21_NA#22035
 * 2017/10/26   Fujitsu         A.Kosai         Update          S21_NA#21100
 * 2017/10/26   Fujitsu         S.Takami        Update          S21_NA#21820
 * 2017/10/27   Fujitsu         S.Ohki          Update          S21_NA#22092
 * 2017/11/24   Fujitsu         K.Ishizuka      Update          S21_NA#21594
 * 2017/12/04   Fujitsu         M.Yamada        Update          S21_NA#21471
 * 2017/12/06   Fujitsu         K.Ishizuka      Update          S21_NA#22664
 * 2017/12/08   Fujitsu         M.Yamada        Update          S21_NA#22874
 * 2017/12/15   Fujitsu         K.Ishizuka      Update          S21_NA#19804(Sol#349)
 * 2017/12/20   Fujitsu         M.Yamada        Update          S21_NA#22874
 * 2018/01/26   Fujitsu         T.Aoi           Update          S21_NA#23320
 * 2018/01/26   Fujitsu         M.Ohno          Update          S21_NA#24117
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          S21_NA#22967
 * 2018/03/05   Fujitsu         Y.Kanefusa      Update          S21_NA#24559
 * 2018/03/15   Fujitsu         K.Ishizuka      Update          S21_NA#24253
 * 2018/04/05   Fujitsu         R.Nakamura      Update          S21_NA#25085
 * 2018/04/06   Fujitsu         R.Nakamura      Update          S21_NA#24949
 * 2018/05/13   Fujitsu         K.Ishizuka      Update          S21_NA#22482
 * 2018/05/17   Fujitsu         K.Ishizuka      Update          S21_NA#21744
 * 2018/07/31   Fujitsu         M.Yamada        Update          S21_NA#26410
 * 2018/08/17   Fujitsu         K.Ishizuka      Update          S21_NA#26271
 * 2018/09/27   Fujitsu         Mz.Takahashi    Update          S21_NA#28481
 * 2018/09/27   Fujitsu         Y.Kanefusa      Update          S21_NA#28370
 * 2018/10/05   Fujitsu         T.Noguchi       Update          S21_NA#28596
 * 2018/10/12   Fujitsu         M.Ohno          Update          S21_NA#28370
 * 2018/12/13   Fujitsu         M.Yamada        Update          QC#29248
 * 2019/02/20   Fujitsu         W.Honda         Update          S21_NA#30347
 * 2019/03/06   Fujitsu         Y.Kanefusa      Update          S21_NA#30573
 * 2019/04/03   Fujitsu         K.Ishizuka      Update          S21_NA#31019
 * 2019/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#30819
 * 2019/04/10   Fujitsu         Y.Kanefusa      Update          S21_NA#31128
 * 2019/04/17   Fujitsu         K.Kato          Update          S21_NA#31181
 * 2019/04/24   Fujitsu         Hd.Sugawara     Update          S21_NA#31125,QC#31126
 * 2019/05/07   Fujitsu         K.Kato          Update          S21_NA#50174
 * 2019/07/10   Fujitsu         Hd.Sugawara     Update          S21_NA#51286
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 * 2019/12/25   Fujitsu         K.Kato          Update          QC#54229
 * 2020/01/27   Fujitsu         C.Hara          Update          QC#55492
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2021/03/31   CITS            A.Marte         Update          QC#58292
 * 2021/06/01   CITS            K.Ogino         Update          QC#58846, QC#57285
 * 2022/09/20   Hitachi         N.Takatsu       Update          QC#54883
 * 2022/10/07   CITS            F.Fadriquela    Update          QC#60623
 * 2022/10/18   CITS            F.Fadriquela    Update          QC#60623
 * 2022/10/24   Hitachi         T.Doi           Update          QC#54883
 * 2022/11/25   Hitachi         H.Watanabe      Update          QC#60398
 * 2022/12/22   Hitachi         H.Watanabe      Update          QC#60398
 * 2023/01/05   CITS            R.Azucena       Update          QC#60974
 * 2023/01/30   CITS            R.Azucena       Update          QC#61094
 * 2023/10/06   Hitachi         K.Watanabe      Update          QC#54186
 * 2024/03/01   CITS            T.Aizawa        Update          QC#54186
 * 2024/03/12   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NWZC155001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient;

    /** Cache DB Access */
    private static NWZC155001_CacheFindByKey cacheDBAccess = NWZC155001_CacheFindByKey.getInstance();

    /**
     * constructor
     */
    public NWZC155001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param pMsg NWZC155001PMsg
     * @param onBatch ONBATCH_TYPE
     */
    public void execute(final NWZC155001PMsg pMsg, final ONBATCH_TYPE onBatch) {
        this.msgMap = new S21ApiMessageMap(pMsg);
        this.onBatchType = onBatch;

        doProcess(pMsg);

        msgMap.flush();
    }

    /**
     * execute
     * @param params List<NWZC155001PMsg>
     * @param onBatch ONBATCH_TYPE
     */
    public void execute(final List<NWZC155001PMsg> params, final ONBATCH_TYPE onBatch) {
        for (NWZC155001PMsg param : params) {
            execute(param, onBatch);
        }
    }

    /**
     * doProcess
     * @param param NWZC155001PMsg
     */
    private void doProcess(NWZC155001PMsg pMsg) {
        // =================================================================
        // 0. pre-processing
        // =================================================================
        // 0.1 Check Parameter
        if (!checkInputParam(pMsg)) {
            return;
        }

        // 0.2 Set Data
        final NWZC155001CpoBean cpoBean = new NWZC155001CpoBean(pMsg);
        setData(cpoBean);

        if (isError(pMsg)) {
            return;
        }

        // 0.3 Get Check Target DI Code List
        // START 2024/03/12 M.Kuroi [QC#63638, MOD]
        // boolean hasTargetDiChkCd = getTargetDiChkCd(cpoBean);
        boolean hasTargetDiChkCd = getTargetDiChkCd(cpoBean, pMsg);
        // END 2024/03/12 M.Kuroi [QC#63638, MOD]

        // 0.4 Set version Number Of this DI Check
        setVrsnNum(cpoBean);

        // =================================================================
        // 1. execute DI Check
        // =================================================================

        // doProcess_Stub(cpoBean);

        // 1.1 Do process of DI Check
        if (hasTargetDiChkCd) {
            final String chkMode = getChkMode(cpoBean);
            doProcess_DiCheck(cpoBean, chkMode, pMsg);
        }

        // =================================================================
        // 2. Register DI Check Result
        // =================================================================
        // 2.0 pre-processing
        boolean isDiChkSuccess = true;
        // 2016/03/02 S21_NA#5000 Mod Start
        // if (ZYPConstant.FLG_ON_Y.equals(cpoBean.getDiErrorFlg())) {
        if (isDiCheckError(cpoBean)) {
            // 2016/03/02 S21_NA#5000 Mod End
            isDiChkSuccess = false;
        }

        // 2.1 register DS_CPO_DI_CHK_RSLT_HDR
        registDsCpoDiChkRsltHdr(cpoBean, isDiChkSuccess);

        if (isError(pMsg)) {
            return;
        }

        // 2.2 register DS_CPO_DI_CHK_RSLT_DTL
        // if (!isDiChkSuccess) { // 2016/03/02 S21_NA#5000 Del
        registDsCpoDiChkRsltDtl(cpoBean);

        if (isError(pMsg)) {
            return;
        }
        // } // 2016/03/02 S21_NA#5000 Del

        // 2.3 register HLD
        if (isDiChkSuccess) {
            releaseHold(cpoBean); // release
        } else {
            registHold(cpoBean); // register
        }

        if (isError(pMsg)) {
            return;
        }

        // 2.4 Update status of Last DI Check Result
        updStsOfLstDiChk(cpoBean);

        if (isError(pMsg)) {
            return;
        }

        // 2.5 return DI Check Hold Flag
        if (isDiChkSuccess) {
            ZYPEZDItemValueSetter.setValue(pMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.diChkHldFlg, ZYPConstant.FLG_ON_Y);
        }

        callOrderStatusUpdateApi(cpoBean);
    }

    /**
     * check Input Parameter
     * @param param NWZC155001PMsg
     * @return boolean
     */
    private boolean checkInputParam(NWZC155001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            msgMap.addXxMsgId(NWZM1205E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NWZM1309E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.diJobId)) {
            msgMap.addXxMsgId(NWZM1579E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.diChkSubmtPsnCd)) {
            msgMap.addXxMsgId(NWZM0336E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.ordDt)) {
            msgMap.addXxMsgId(NWZM0394E);
        }
        return !isError(pMsg);
    }

    /**
     * Data setting
     * @param cpoBean NWZC155001CpoBean
     */
    void setData(NWZC155001CpoBean cpoBean) {
        // set CPO to NWZC155001CpoBean

        // 2016/09/30 UPD START QC#14779
        // DS_CPO_VTMsg dsCpoVTMsg = (DS_CPO_VTMsg)
        // getDsCpoVTMsg(cpoBean.getGlblCmpyCd(),
        // cpoBean.getCpoOrdNum());
        // if (dsCpoVTMsg == null) {
        // msgMap.addXxMsgId(NWZM1207E);
        // return;
        // }
        // setCpoBean(cpoBean, dsCpoVTMsg);

        Map<String, String> key = new HashMap<String, String>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        key.put("credit", CR_REBIL.CREDIT); // QC#15960 2016/11/15 Add
        Map<String, Object> rsDsCpo = (Map<String, Object>) ssmClient.queryObject("getDsCpoVData", key);

        if (rsDsCpo == null) {
            msgMap.addXxMsgId(NWZM1207E);
            return;
        }

        cpoBean.setDsOrdTpCd((String) rsDsCpo.get("DS_ORD_TP_CD"));
        cpoBean.setCpoOrdTs((String) rsDsCpo.get("CPO_ORD_TS"));
        cpoBean.setDsOrdCatgCd((String) rsDsCpo.get("DS_ORD_CATG_CD"));
        cpoBean.setOrdHdrSts((String) rsDsCpo.get("ORD_HDR_STS_CD"));
        cpoBean.setCpoUpdVrsnNum((BigDecimal) rsDsCpo.get("CPO_UPD_VRSN_NUM"));
        cpoBean.setDiErrorFlg(ZYPConstant.FLG_OFF_N);
        cpoBean.setShipToCustAcctCd((String) rsDsCpo.get("SHIP_TO_CUST_ACCT_CD"));
        cpoBean.setLeaseCmpyPoNum((String) rsDsCpo.get("LEASE_CMPY_PO_NUM"));
        cpoBean.setSellToCustCd((String) rsDsCpo.get("SELL_TO_CUST_CD"));
        cpoBean.setBillToCustCd((String) rsDsCpo.get("BILL_TO_CUST_CD")); // 2016/03/08
        // S21_NA#5000
        // Add
        cpoBean.setBillToCustAcctCd((String) rsDsCpo.get("BILL_TO_CUST_ACCT_CD")); // QC#21471
        cpoBean.setLineBizTpCd((String) rsDsCpo.get("LINE_BIZ_TP_CD"));
        // 2016/02/19 S21_NA#1686 Add Start
        cpoBean.setPrePmtAmt((BigDecimal) rsDsCpo.get("PRE_PMT_AMT"));
        cpoBean.setOrdTotDealNetAmt((BigDecimal) rsDsCpo.get("ORD_TOT_DEAL_NET_AMT"));
        // 2016/02/19 S21_NA#1686 Add End
        cpoBean.setPrcContrNum((String) rsDsCpo.get("PRC_CONTR_NUM"));
        // QC#10441 Add Start
        cpoBean.setCsmpContrNum((String) rsDsCpo.get("CSMP_CONTR_NUM"));
        cpoBean.setDlrRefNum((String) rsDsCpo.get("DLR_REF_NUM"));
        // QC#10441 Add End
        cpoBean.setDclnSvcCd((String) rsDsCpo.get("DCLN_SVC_CD")); // S21_NA#8388
        // ADD
        // 2016/09/30 UPD E N D QC#14779
        cpoBean.setSlsRepTocCd((String) rsDsCpo.get("SLS_REP_TOC_CD")); // S21_NA#19804
        // ADD
        cpoBean.setShipToCustCd((String) rsDsCpo.get("ADD_SHIP_TO_CUST_CD")); // S21_NA#19804
        // ADD

        // 2019/05/07 QC#50174 Add Start
        cpoBean.setOrdBookFlg((String) rsDsCpo.get("ORD_BOOK_FLG")); // 2019/05/07
        // QC#50174
        // Add
        // Start
        // 2019/05/07 QC#50174 Add End

        // 2020/04/29 QC#56638 Add Start
        cpoBean.setSoldToCustLocCd((String) rsDsCpo.get("SOLD_TO_CUST_LOC_CD"));
        // 2020/04/29 QC#56638 Add End

        // set Line to NWZC155001CpoDtlBean
        List<NWZC155001CpoDtlBean> cpoDtlBeanList = new ArrayList<NWZC155001CpoDtlBean>();

        // set DS_CPO_DTLV Data
        // 2016/09/30 UPD START QC#14779
        // DS_CPO_DTL_VTMsgArray dsCpoDtlVTMsgArry =
        // (DS_CPO_DTL_VTMsgArray)
        // getDsCpoDtlVTMsgArray(cpoBean.getGlblCmpyCd(),
        // cpoBean.getCpoOrdNum());
        // for (int idx = 0; idx < dsCpoDtlVTMsgArry.getValidCount();
        // idx++) {
        // DS_CPO_DTL_VTMsg dsCpoDtlVTMsg = dsCpoDtlVTMsgArry.no(idx);
        // NWZC155001CpoDtlBean cpoDtlBean =
        // setOutboundCpoDtlBean(cpoBean, dsCpoDtlVTMsg);
        // cpoDtlBeanList.add(cpoDtlBean);
        // }
        List<Map<String, Object>> rsDsCpoDtl = (List<Map<String, Object>>) ssmClient.queryObjectList("getDsCpoDtlVData", key);

        for (Map<String, Object> rsDtl : rsDsCpoDtl) {
            String ordLineStsCd = (String) rsDtl.get("ORD_LINE_STS_CD");
            if (ORD_LINE_STS.CANCELLED.equals(ordLineStsCd) //
                    || ORD_LINE_STS.CLOSED.equals(ordLineStsCd)) {
                continue;
            }

            NWZC155001CpoDtlBean cpoDtlBean = new NWZC155001CpoDtlBean();

            cpoDtlBean.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
            cpoDtlBean.setCpoOrdNum(cpoBean.getCpoOrdNum());
            cpoDtlBean.setDsCpoConfigPk((BigDecimal) rsDtl.get("DS_CPO_CONFIG_PK"));
            cpoDtlBean.setDsCpoLineNum(rsDtl.get("DS_CPO_LINE_NUM").toString());
            if (ZYPCommonFunc.hasValue((BigDecimal) rsDtl.get("DS_CPO_LINE_SUB_NUM"))) {
                cpoDtlBean.setDsCpoLineSubNum(rsDtl.get("DS_CPO_LINE_SUB_NUM").toString());
            }
            cpoDtlBean.setCpoDtlLineNum((String) rsDtl.get("CPO_DTL_LINE_NUM"));
            cpoDtlBean.setCpoDtlLineSubNum((String) rsDtl.get("CPO_DTL_LINE_SUB_NUM"));
            cpoDtlBean.setDsOrdPosnNum((String) rsDtl.get("DS_ORD_POSN_NUM"));
            cpoDtlBean.setConfigCatgCd(CONFIG_CATG.OUTBOUND);
            cpoDtlBean.setOrdQty((BigDecimal) rsDtl.get("ORD_QTY"));
            cpoDtlBean.setCpoDtlFuncNetAmt((BigDecimal) rsDtl.get("CPO_DTL_FUNC_NET_AMT"));
            cpoDtlBean.setPrcCatgCd((String) rsDtl.get("PRC_CATG_CD"));
            cpoDtlBean.setOpenFlg((String) rsDtl.get("OPEN_FLG"));
            cpoDtlBean.setMdseCd((String) rsDtl.get("MDSE_CD"));
            cpoDtlBean.setCcyCd((String) rsDtl.get("CCY_CD"));
            cpoDtlBean.setPrcListEquipConfigNum((BigDecimal) rsDtl.get("PRC_LIST_EQUIP_CONFIG_NUM"));
            cpoDtlBean.setDsOrdLineCatgCd((String) rsDtl.get("DS_ORD_LINE_CATG_CD"));
            cpoDtlBean.setInvQty((BigDecimal) rsDtl.get("INV_QTY"));
            cpoDtlBean.setCtyAddr_SH((String) rsDtl.get("SHIP_TO_CTY_ADDR"));
            cpoDtlBean.setStCd_SH((String) rsDtl.get("SHIP_TO_ST_CD"));
            cpoDtlBean.setCtryCd_SH((String) rsDtl.get("SHIP_TO_CTRY_CD"));
            cpoDtlBean.setUomCd((String) rsDtl.get("CUST_UOM_CD"));
            cpoDtlBean.setUomQty((BigDecimal) rsDtl.get("ORD_CUST_UOM_QTY"));
            cpoDtlBean.setFuncNetAmt((BigDecimal) rsDtl.get("ENT_CPO_DTL_FUNC_NET_AMT"));
            cpoDtlBean.setFirstBllgAttrbNm((String) rsDtl.get("FIRST_BLLG_ATTRB_NM"));
            cpoDtlBean.setScdBllgAttrbNm((String) rsDtl.get("SCD_BLLG_ATTRB_NM"));
            cpoDtlBean.setThirdBllgAttrbNm((String) rsDtl.get("THIRD_BLLG_ATTRB_NM"));
            cpoDtlBean.setFrthBllgAttrbNm((String) rsDtl.get("FRTH_BLLG_ATTRB_NM"));
            cpoDtlBean.setFifthBllgAttrbNm((String) rsDtl.get("FIFTH_BLLG_ATTRB_NM"));
            cpoDtlBean.setFirstBllgAttrbValTxt((String) rsDtl.get("FIRST_BLLG_ATTRB_VAL_TXT"));
            cpoDtlBean.setScdBllgAttrbValTxt((String) rsDtl.get("SCD_BLLG_ATTRB_VAL_TXT"));
            cpoDtlBean.setThirdBllgAttrbValTxt((String) rsDtl.get("THIRD_BLLG_ATTRB_VAL_TXT"));
            cpoDtlBean.setFrthBllgAttrbValTxt((String) rsDtl.get("FRTH_BLLG_ATTRB_VAL_TXT"));
            cpoDtlBean.setFifthBllgAttrbValTxt((String) rsDtl.get("FIFTH_BLLG_ATTRB_VAL_TXT"));
            cpoDtlBean.setFlPrcListCd((String) rsDtl.get("FL_PRC_LIST_CD"));
            cpoDtlBean.setEntFuncNetUnitPrcAmt((BigDecimal) rsDtl.get("ENT_FUNC_NET_UNIT_PRC_AMT"));
            cpoDtlBean.setEntDealNetUnitPrcAmt((BigDecimal) rsDtl.get("ENT_DEAL_NET_UNIT_PRC_AMT"));
            cpoDtlBean.setFuncPrcListPrcAmt((BigDecimal) rsDtl.get("FUNC_PRC_LIST_PRC_AMT"));
            cpoDtlBean.setCrRebilCd((String) rsDtl.get("CR_REBIL_CD"));

            // 2017/10/24 S21_NA#22035 Add Start
            cpoDtlBean.setRtlWhCd((String) rsDtl.get("RTL_WH_CD"));
            cpoDtlBean.setRtlSWhCd((String) rsDtl.get("RTL_SWH_CD"));
            // 2017/10/24 S21_NA#22035 Add End
            cpoDtlBean.setOrdLineStsCd(ordLineStsCd);
            cpoDtlBean.setSlsRepTocCd((String) rsDtl.get("SLS_REP_OR_SLS_TEAM_TOC_CD")); // S21_NA#19804
            // ADD
            cpoDtlBean.setSvcConfigMstrPk((BigDecimal) rsDtl.get("SVC_CONFIG_MSTR_PK")); // 2018/03/15
            // S21_NA#24253
            // Add
            cpoDtlBeanList.add(cpoDtlBean);
        }
        // 2016/09/30 UPD E N D QC#14779

        // set DS_CPO_RTRN_DTL_VTMsgArray Data
        // 2016/09/30 UPD START QC#14779
        // DS_CPO_RTRN_DTL_VTMsgArray dsCpoRtrnDtlVTMsgArry =
        // (DS_CPO_RTRN_DTL_VTMsgArray)
        // getDsCpoRtrnDtlVTMsgArray(cpoBean.getGlblCmpyCd(),
        // cpoBean.getCpoOrdNum());
        // for (int idx = 0; idx <
        // dsCpoRtrnDtlVTMsgArry.getValidCount(); idx++) {
        // DS_CPO_RTRN_DTL_VTMsg dsCpoRtrnDtlVTMsg =
        // dsCpoRtrnDtlVTMsgArry.no(idx);
        // NWZC155001CpoDtlBean cpoDtlBean =
        // setInboundCpoDtlBean(cpoBean, dsCpoRtrnDtlVTMsg);
        // cpoDtlBeanList.add(cpoDtlBean);
        // }
        List<Map<String, Object>> rsDsCpoRtrn = (List<Map<String, Object>>) ssmClient.queryObjectList("getDsCpoRtrnDtlVData", key);

        for (Map<String, Object> rsRt : rsDsCpoRtrn) {
            String rtrnLineStsCd = (String) rsRt.get("RTRN_LINE_STS_CD");
            if (RTRN_LINE_STS.CANCELLED.equals(rtrnLineStsCd) //
                    || RTRN_LINE_STS.CLOSED.equals(rtrnLineStsCd)) {
                continue;
            }

            NWZC155001CpoDtlBean cpoDtlBean = new NWZC155001CpoDtlBean();

            cpoDtlBean.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
            cpoDtlBean.setCpoOrdNum(cpoBean.getCpoOrdNum());
            cpoDtlBean.setDsCpoConfigPk((BigDecimal) rsRt.get("DS_CPO_CONFIG_PK"));
            // QC#7943
            // cpoDtlBean.setDsCpoLineNum(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineNum.getValue());
            String strDsCpoLineNum = "";
            if (ZYPCommonFunc.hasValue((BigDecimal) rsRt.get("DS_CPO_LINE_NUM"))) {
                strDsCpoLineNum = rsRt.get("DS_CPO_LINE_NUM").toString();
            }
            cpoDtlBean.setDsCpoLineNum(strDsCpoLineNum);
            // if
            // (ZYPCommonFunc.hasValue(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineSubNum))
            // {
            // cpoDtlBean.setDsCpoLineSubNum(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineSubNum.getValue());
            if (ZYPCommonFunc.hasValue((BigDecimal) rsRt.get("DS_CPO_LINE_SUB_NUM"))) {
                cpoDtlBean.setDsCpoLineSubNum(rsRt.get("DS_CPO_LINE_SUB_NUM").toString());
            }
            cpoDtlBean.setCpoDtlLineNum((String) rsRt.get("DS_CPO_RTRN_LINE_NUM"));
            cpoDtlBean.setCpoDtlLineSubNum((String) rsRt.get("DS_CPO_RTRN_LINE_SUB_NUM"));
            cpoDtlBean.setDsOrdPosnNum((String) rsRt.get("DS_ORD_POSN_NUM"));
            cpoDtlBean.setConfigCatgCd(CONFIG_CATG.INBOUND);
            cpoDtlBean.setOrdQty((BigDecimal) rsRt.get("ORD_QTY"));
            cpoDtlBean.setCpoDtlFuncNetAmt((BigDecimal) rsRt.get("CPO_DTL_FUNC_NET_AMT"));
            cpoDtlBean.setHddRmvCd((String) rsRt.get("HDD_RMV_CD"));
            cpoDtlBean.setSvcMachMstrPk((BigDecimal) rsRt.get("SVC_MACH_MSTR_PK"));
            cpoDtlBean.setSvcConfigMstrPk((BigDecimal) rsRt.get("SVC_CONFIG_MSTR_PK"));
            cpoDtlBean.setSerNum((String) rsRt.get("SER_NUM"));
            cpoDtlBean.setPrcCatgCd((String) rsRt.get("PRC_CATG_CD"));
            cpoDtlBean.setMdseCd((String) rsRt.get("MDSE_CD"));
            cpoDtlBean.setCcyCd((String) rsRt.get("CCY_CD"));
            cpoDtlBean.setDsOrdLineCatgCd((String) rsRt.get("DS_ORD_LINE_CATG_CD"));
            cpoDtlBean.setCtyAddr_SH((String) rsRt.get("SHIP_TO_CTY_ADDR"));
            cpoDtlBean.setStCd_SH((String) rsRt.get("SHIP_TO_ST_CD"));
            cpoDtlBean.setCtryCd_SH((String) rsRt.get("SHIP_TO_CTRY_CD"));
            cpoDtlBean.setUomCd((String) rsRt.get("CUST_UOM_CD"));
            cpoDtlBean.setFuncNetAmt((BigDecimal) rsRt.get("ENT_CPO_DTL_FUNC_NET_AMT"));
            // 2017/09/16 S21_NA#21064 Add Start
            cpoDtlBean.setRtrnLineStsCd(rtrnLineStsCd);
            // 2017/09/16 S21_NA#21064 Add End
            cpoDtlBean.setSlsRepTocCd((String) rsRt.get("SLS_REP_OR_SLS_TEAM_TOC_CD")); // S21_NA#19804
            // ADD
            cpoDtlBeanList.add(cpoDtlBean);
        }
        // 2016/09/30 UPD E N D QC#14779

        cpoBean.setCpoDtlBeanList(cpoDtlBeanList);

        if (cpoBean.getCpoDtlBeanList().size() == 0) {
            return;
        }

        // set Config to NWZC155001CpoDtlBean
        DS_CPO_CONFIGTMsgArray dsCpoConfigTMsgArry = (DS_CPO_CONFIGTMsgArray) getDsCpoConfigTMsg(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
        for (int idx = 0; idx < dsCpoConfigTMsgArry.getValidCount(); idx++) {
            DS_CPO_CONFIGTMsg dsCpoConfigTMsg = dsCpoConfigTMsgArry.no(idx);
            for (int cpoDtlidx = 0; cpoDtlidx < cpoBean.getCpoDtlBeanList().size(); cpoDtlidx++) {
                NWZC155001CpoDtlBean cpoDtlBean = cpoBean.getCpoDtlBeanList().get(cpoDtlidx);
                if (dsCpoConfigTMsg.dsCpoConfigPk.getValue().equals(cpoDtlBean.getDsCpoConfigPk())) {
                    setDsCpoConfigToCpoDtlBean(cpoDtlBean, dsCpoConfigTMsg);
                }
            }
        }
    }

    // /**
    // * set CpoBean
    // * @param cpoBean NWZC155001CpoBean
    // * @param dsCpoVTMsg DS_CPO_DTDS_CPO_VTMsgL_VTMsg
    // */
    // private void setCpoBean(NWZC155001CpoBean cpoBean, CPO_VTMsg
    // dsCpoVTMsg) {
    // cpoBean.setDsOrdTpCd(dsCpoVTMsg.dsOrdTpCd.getValue());
    // cpoBean.setCpoOrdTs(dsCpoVTMsg.cpoOrdTs.getValue());
    // cpoBean.setDsOrdCatgCd(dsCpoVTMsg.dsOrdCatgCd.getValue());
    // cpoBean.setOrdHdrSts(dsCpoVTMsg.ordHdrStsCd.getValue());
    // cpoBean.setCpoUpdVrsnNum(dsCpoVTMsg.cpoUpdVrsnNum.getValue());
    // cpoBean.setDiErrorFlg(ZYPConstant.FLG_OFF_N);
    // cpoBean.setShipToCustAcctCd(dsCpoVTMsg.shipToCustAcctCd.getValue());
    // cpoBean.setLeaseCmpyPoNum(dsCpoVTMsg.leaseCmpyPoNum.getValue());
    // cpoBean.setSellToCustCd(dsCpoVTMsg.sellToCustCd.getValue());
    // cpoBean.setBillToCustCd(dsCpoVTMsg.billToCustCd.getValue()); //
    // 2016/03/08 S21_NA#5000 Add
    // cpoBean.setLineBizTpCd(dsCpoVTMsg.lineBizTpCd.getValue());
    // // 2016/02/19 S21_NA#1686 Add Start
    // cpoBean.setPrePmtAmt(dsCpoVTMsg.prePmtAmt.getValue());
    // cpoBean.setOrdTotDealNetAmt(dsCpoVTMsg.ordTotDealNetAmt.getValue());
    // // 2016/02/19 S21_NA#1686 Add End
    // cpoBean.setPrcContrNum(dsCpoVTMsg.prcContrNum.getValue());
    // // QC#10441 Add Start
    // cpoBean.setCsmpContrNum(dsCpoVTMsg.csmpContrNum.getValue());
    // cpoBean.setDlrRefNum(dsCpoVTMsg.dlrRefNum.getValue());
    // // QC#10441 Add End
    // cpoBean.setDclnSvcCd(dsCpoVTMsg.dclnSvcCd.getValue()); //
    // S21_NA#8388 ADD
    //
    // }

    // 2016/09/30 DEL START QC#14779
    // /**
    // * set CpoDtlBean of Outbound(Sales)
    // * @param cpoBean NWZC155001CpoBean
    // * @param dsCpoDtlVTMsg DS_CPO_DTL_VTMsg
    // */
    // private NWZC155001CpoDtlBean
    // setOutboundCpoDtlBean(NWZC155001CpoBean cpoBean,
    // DS_CPO_DTL_VTMsg dsCpoDtlVTMsg) {
    // NWZC155001CpoDtlBean cpoDtlBean = new NWZC155001CpoDtlBean();
    //
    // cpoDtlBean.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
    // cpoDtlBean.setCpoOrdNum(cpoBean.getCpoOrdNum());
    // cpoDtlBean.setDsCpoConfigPk(dsCpoDtlVTMsg.dsCpoConfigPk.getValue());
    // cpoDtlBean.setDsCpoLineNum(dsCpoDtlVTMsg.dsCpoLineNum.getValue().toString());
    // if (ZYPCommonFunc.hasValue(dsCpoDtlVTMsg.dsCpoLineSubNum)) {
    // cpoDtlBean.setDsCpoLineSubNum(dsCpoDtlVTMsg.dsCpoLineSubNum.getValue().toString());
    // }
    // cpoDtlBean.setCpoDtlLineNum(dsCpoDtlVTMsg.cpoDtlLineNum.getValue());
    // cpoDtlBean.setCpoDtlLineSubNum(dsCpoDtlVTMsg.cpoDtlLineSubNum.getValue());
    // cpoDtlBean.setDsOrdPosnNum(dsCpoDtlVTMsg.dsOrdPosnNum.getValue());
    // cpoDtlBean.setConfigCatgCd(CONFIG_CATG.OUTBOUND);
    // cpoDtlBean.setOrdQty(dsCpoDtlVTMsg.ordQty.getValue());
    // cpoDtlBean.setCpoDtlFuncNetAmt(dsCpoDtlVTMsg.cpoDtlFuncNetAmt.getValue());
    // cpoDtlBean.setPrcCatgCd(dsCpoDtlVTMsg.prcCatgCd.getValue());
    // cpoDtlBean.setOpenFlg(dsCpoDtlVTMsg.openFlg.getValue());
    // cpoDtlBean.setMdseCd(dsCpoDtlVTMsg.mdseCd.getValue());
    // cpoDtlBean.setCcyCd(dsCpoDtlVTMsg.ccyCd.getValue());
    // cpoDtlBean.setPrcListEquipConfigNum(dsCpoDtlVTMsg.prcListEquipConfigNum.getValue());
    // cpoDtlBean.setDsOrdLineCatgCd(dsCpoDtlVTMsg.dsOrdLineCatgCd.getValue());
    // cpoDtlBean.setInvQty(dsCpoDtlVTMsg.invQty.getValue());
    // cpoDtlBean.setCtyAddr_SH(dsCpoDtlVTMsg.shipToCtyAddr.getValue());
    // cpoDtlBean.setStCd_SH(dsCpoDtlVTMsg.shipToStCd.getValue());
    // cpoDtlBean.setCtryCd_SH(dsCpoDtlVTMsg.shipToCtryCd.getValue());
    // cpoDtlBean.setUomCd(dsCpoDtlVTMsg.custUomCd.getValue());
    // cpoDtlBean.setUomQty(dsCpoDtlVTMsg.ordCustUomQty.getValue());
    // cpoDtlBean.setFuncNetAmt(dsCpoDtlVTMsg.entCpoDtlFuncNetAmt.getValue());
    // cpoDtlBean.setFirstBllgAttrbNm(dsCpoDtlVTMsg.firstBllgAttrbNm.getValue());
    // cpoDtlBean.setScdBllgAttrbNm(dsCpoDtlVTMsg.scdBllgAttrbNm.getValue());
    // cpoDtlBean.setThirdBllgAttrbNm(dsCpoDtlVTMsg.thirdBllgAttrbNm.getValue());
    // cpoDtlBean.setFrthBllgAttrbNm(dsCpoDtlVTMsg.frthBllgAttrbNm.getValue());
    // cpoDtlBean.setFifthBllgAttrbNm(dsCpoDtlVTMsg.fifthBllgAttrbNm.getValue());
    // cpoDtlBean.setFirstBllgAttrbValTxt(dsCpoDtlVTMsg.firstBllgAttrbValTxt.getValue());
    // cpoDtlBean.setScdBllgAttrbValTxt(dsCpoDtlVTMsg.scdBllgAttrbValTxt.getValue());
    // cpoDtlBean.setThirdBllgAttrbValTxt(dsCpoDtlVTMsg.thirdBllgAttrbValTxt.getValue());
    // cpoDtlBean.setFrthBllgAttrbValTxt(dsCpoDtlVTMsg.frthBllgAttrbValTxt.getValue());
    // cpoDtlBean.setFifthBllgAttrbValTxt(dsCpoDtlVTMsg.fifthBllgAttrbValTxt.getValue());
    // cpoDtlBean.setFlPrcListCd(dsCpoDtlVTMsg.flPrcListCd.getValue());
    // cpoDtlBean.setEntFuncNetUnitPrcAmt(dsCpoDtlVTMsg.entFuncNetUnitPrcAmt.getValue());
    // cpoDtlBean.setEntDealNetUnitPrcAmt(dsCpoDtlVTMsg.entDealNetUnitPrcAmt.getValue());
    // cpoDtlBean.setFuncPrcListPrcAmt(dsCpoDtlVTMsg.funcPrcListPrcAmt.getValue());
    // cpoDtlBean.setCrRebilCd(dsCpoDtlVTMsg.crRebilCd.getValue());
    //
    // return cpoDtlBean;
    // }
    // 2016/09/30 DEL E N D QC#14779

    // 2016/09/30 DEL START QC#14779
    // /**
    // * set CpoDtlBean of Inbound(Return)
    // * @param cpoBean NWZC155001CpoBean
    // * @param dsCpoRtrnDtlVTMsg DS_CPO_RTRN_DTL_VTMsg
    // */
    // private NWZC155001CpoDtlBean
    // setInboundCpoDtlBean(NWZC155001CpoBean cpoBean,
    // DS_CPO_RTRN_DTL_VTMsg dsCpoRtrnDtlVTMsg) {
    // NWZC155001CpoDtlBean cpoDtlBean = new NWZC155001CpoDtlBean();
    //
    // cpoDtlBean.setGlblCmpyCd(cpoBean.getGlblCmpyCd());
    // cpoDtlBean.setCpoOrdNum(cpoBean.getCpoOrdNum());
    // cpoDtlBean.setDsCpoConfigPk(dsCpoRtrnDtlVTMsg.dsCpoConfigPk.getValue());
    // //QC#7943
    // //cpoDtlBean.setDsCpoLineNum(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineNum.getValue());
    // String strDsCpoLineNum = "";
    // if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlVTMsg.dsCpoLineNum)) {
    // strDsCpoLineNum =
    // dsCpoRtrnDtlVTMsg.dsCpoLineNum.getValue().toString();
    // }
    // cpoDtlBean.setDsCpoLineNum(strDsCpoLineNum);
    // //if
    // (ZYPCommonFunc.hasValue(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineSubNum))
    // {
    // //
    // cpoDtlBean.setDsCpoLineSubNum(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineSubNum.getValue());
    // if (ZYPCommonFunc.hasValue(dsCpoRtrnDtlVTMsg.dsCpoLineSubNum))
    // {
    // cpoDtlBean.setDsCpoLineSubNum(dsCpoRtrnDtlVTMsg.dsCpoLineSubNum.getValue().toString());
    // }
    // cpoDtlBean.setCpoDtlLineNum(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineNum.getValue());
    // cpoDtlBean.setCpoDtlLineSubNum(dsCpoRtrnDtlVTMsg.dsCpoRtrnLineSubNum.getValue());
    // cpoDtlBean.setDsOrdPosnNum(dsCpoRtrnDtlVTMsg.dsOrdPosnNum.getValue());
    // cpoDtlBean.setConfigCatgCd(CONFIG_CATG.INBOUND);
    // cpoDtlBean.setOrdQty(dsCpoRtrnDtlVTMsg.ordQty.getValue());
    // cpoDtlBean.setCpoDtlFuncNetAmt(dsCpoRtrnDtlVTMsg.cpoDtlFuncNetAmt.getValue());
    // cpoDtlBean.setHddRmvCd(dsCpoRtrnDtlVTMsg.hddRmvCd.getValue());
    // cpoDtlBean.setSvcMachMstrPk(dsCpoRtrnDtlVTMsg.svcMachMstrPk.getValue());
    // cpoDtlBean.setSvcConfigMstrPk(dsCpoRtrnDtlVTMsg.svcConfigMstrPk.getValue());
    // cpoDtlBean.setSerNum(dsCpoRtrnDtlVTMsg.serNum.getValue());
    // cpoDtlBean.setPrcCatgCd(dsCpoRtrnDtlVTMsg.prcCatgCd.getValue());
    // cpoDtlBean.setMdseCd(dsCpoRtrnDtlVTMsg.mdseCd.getValue());
    // cpoDtlBean.setCcyCd(dsCpoRtrnDtlVTMsg.ccyCd.getValue());
    // cpoDtlBean.setDsOrdLineCatgCd(dsCpoRtrnDtlVTMsg.dsOrdLineCatgCd.getValue());
    // cpoDtlBean.setCtyAddr_SH(dsCpoRtrnDtlVTMsg.shipToCtyAddr.getValue());
    // cpoDtlBean.setStCd_SH(dsCpoRtrnDtlVTMsg.shipToStCd.getValue());
    // cpoDtlBean.setCtryCd_SH(dsCpoRtrnDtlVTMsg.shipToCtryCd.getValue());
    // cpoDtlBean.setUomCd(dsCpoRtrnDtlVTMsg.custUomCd.getValue());
    // cpoDtlBean.setFuncNetAmt(dsCpoRtrnDtlVTMsg.entCpoDtlFuncNetAmt.getValue());
    //
    // return cpoDtlBean;
    // }
    // 2016/09/30 DEL E N D QC#14779

    /**
     * set DS_CPO_CONFIG to cpoDtlBean
     * @param cpoBean NWZC155001CpoBean
     * @param dsCpoVTMsg DS_CPO_DTDS_CPO_VTMsgL_VTMsg
     */
    private void setDsCpoConfigToCpoDtlBean(NWZC155001CpoDtlBean cpoDtlBean, DS_CPO_CONFIGTMsg dsCpoConfigTMsg) {
        cpoDtlBean.setBillToCustCd(dsCpoConfigTMsg.billToCustLocCd.getValue());
        cpoDtlBean.setDsAcctNum_BL(dsCpoConfigTMsg.billToCustAcctCd.getValue());
        cpoDtlBean.setShipToCustCd(dsCpoConfigTMsg.shipToCustLocCd.getValue());
        cpoDtlBean.setDsAcctNum_SH(dsCpoConfigTMsg.shipToCustAcctCd.getValue());
        cpoDtlBean.setMdlId(dsCpoConfigTMsg.mdlId.getValue());
        cpoDtlBean.setMdlNm(dsCpoConfigTMsg.mdlDescTxt.getValue());
        cpoDtlBean.setConfigTpCd(dsCpoConfigTMsg.configTpCd.getValue()); // 2018/03/15
        // S21_NA#24253
        // Add
    }

    /**
     * get Target DI Check Code List.
     * @param param NWZC155001PMsg
     */
    // START 2024/03/12 M.Kuroi [QC#63638, MOD]
    // private boolean getTargetDiChkCd(NWZC155001CpoBean cpoBean) {
    private boolean getTargetDiChkCd(NWZC155001CpoBean cpoBean, NWZC155001PMsg pMsg) {
        // END 2024/03/12 M.Kuroi [QC#63638, MOD]

        // get Target DI Check Code
        // START 2024/03/12 M.Kuroi [QC#63638, MOD]
        // List<NWZC155001DiChkBean> diChckList =
        // getDiChckCd(cpoBean);
        List<NWZC155001DiChkBean> diChckList = null;
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.contrExecFlg.getValue())) {
            diChckList = getDiChckCdWithContrFlg(cpoBean);
        } else {
            diChckList = getDiChckCd(cpoBean);
        }
        // END 2024/03/12 M.Kuroi [QC#63638, MOD]

        // if (diChckList == null) {
        if (diChckList.size() == 0) {
            return false;
        }

        // set Target DI Check Code
        cpoBean.setDiChkBeanList(diChckList);
        return true;
    }

    /**
     * set VersionNumber
     * @param cpoBean NWZC155001CpoBean
     */
    private void setVrsnNum(NWZC155001CpoBean cpoBean) {
        BigDecimal lastVrsnNum = getLastVrsnNum(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
        BigDecimal vrsnNum = add(lastVrsnNum, BigDecimal.ONE);
        cpoBean.setDiChkVrsnNum(vrsnNum);
    }

    /**
     * add value of BigDecimal
     * @param bc1 BigDecimal
     * @param bc2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal add(BigDecimal bc1, BigDecimal bc2) {
        if (!ZYPCommonFunc.hasValue(bc1)) {
            bc1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(bc2)) {
            bc2 = BigDecimal.ZERO;
        }
        return bc1.add(bc2);
    }

    /**
     * get DI Check Mode
     * @param cpoBean NWZC155001CpoBean
     */
    private String getChkMode(NWZC155001CpoBean cpoBean) {
        boolean hasOutbound = false;
        boolean hasInbound = false;

        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
            // QC#22874
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()) //
                    || RTRN_LINE_STS.CLOSED.equals(cpoDtlBean.getRtrnLineStsCd())) {
                continue;
            }
            if (CONFIG_CATG.OUTBOUND.equals(cpoDtlBean.getConfigCatgCd())) {
                hasOutbound = true;
            }
            if (CONFIG_CATG.INBOUND.equals(cpoDtlBean.getConfigCatgCd())) {
                hasInbound = true;
            }
            if (hasOutbound && hasInbound) {
                break;
            }
        }

        if (hasOutbound) {
            if (hasInbound) {
                return CHK_MODE_LINE_RMA;
            } else {
                return CHK_MODE_LINE;
            }
        }
        if (hasInbound) {
            return CHK_MODE_RMA;
        }
        return CHK_MODE_HEADER;
    }

    /**
     * registDsCpoDiChkRsltHdr
     * @param cpoBean NWZC155001CpoBean
     * @param isDiChkSuccess boolean
     */
    private void registDsCpoDiChkRsltHdr(NWZC155001CpoBean cpoBean, boolean isDiChkSuccess) {

        // START 2022/10/07 F.Fadriquela [QC#60623, ADD]
        BigDecimal lastVrsnNum = getLastVrsnNum(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());
        if (ZYPCommonFunc.hasValue(lastVrsnNum) && lastVrsnNum.compareTo(cpoBean.getDiChkVrsnNum()) == 0) {
            msgMap.addXxMsgId(NWAM0242E);
            return;
        }
        // END 2022/10/07 F.Fadriquela [QC#60623, ADD]

        DS_CPO_DI_CHK_RSLT_HDRTMsg insDiChkRsltHdrTMsg = new DS_CPO_DI_CHK_RSLT_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        cpoBean.setDsCpoDiChkRsltHdrPk(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DI_CHK_RSLT_HDR_SQ));
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.dsCpoDiChkRsltHdrPk, cpoBean.getDsCpoDiChkRsltHdrPk());
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkVrsnNum, cpoBean.getDiChkVrsnNum());
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkExecDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkSubmtPsnCd, cpoBean.getDiChkSubmtPsnCd());
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diJobId, cpoBean.getDiJobId());
        ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.cpoUpdVrsnNum, cpoBean.getCpoUpdVrsnNum());

        if (isDiChkSuccess) {
            ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkStsCd, DI_CHK_STS.ACCEPTED);
            ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkHdrAcptDt, cpoBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkHdrAcptPsnCd, cpoBean.getDiChkSubmtPsnCd());

        } else {
            ZYPEZDItemValueSetter.setValue(insDiChkRsltHdrTMsg.diChkStsCd, DI_CHK_STS.IN_PROCESS);
        }

        S21ApiTBLAccessor.insert(insDiChkRsltHdrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insDiChkRsltHdrTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1580E);
        }
    }

    /**
     * registDsCpoDiChkRsltDtl
     * @param cpoBean NWZC155001CpoBean
     */
    private void registDsCpoDiChkRsltDtl(NWZC155001CpoBean cpoBean) {
        for (NWZC155001DiResultBean diRsltBean : cpoBean.getDiResultList()) {
            DS_CPO_DI_CHK_RSLT_DTLTMsg insDiChkRsltDtlTMsg = new DS_CPO_DI_CHK_RSLT_DTLTMsg();

            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.dsCpoDiChkRsltDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DI_CHK_RSLT_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.dsCpoDiChkRsltHdrPk, cpoBean.getDsCpoDiChkRsltHdrPk());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diChkCd, diRsltBean.getDiChckCd());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diChkLvlCd, diRsltBean.getDiChkLvlCd());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diChkDtlStsCd, diRsltBean.getDiChkDtlStsCd());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diChkErrTxt, diRsltBean.getDiChkErrTxt());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diErrCpoConfigPk, diRsltBean.getCpoConfigPk());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diErrConfigCatgCd, diRsltBean.getConfigCatgCd());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diErrOrdPosnNum, diRsltBean.getDsOrdPosnNum());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diErrCpoLineNum, diRsltBean.getCpoLineNum());
            ZYPEZDItemValueSetter.setValue(insDiChkRsltDtlTMsg.diErrCpoLineSubNum, diRsltBean.getCpoLineSubNum());

            cacheDBAccess.insertWithCache(insDiChkRsltDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insDiChkRsltDtlTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NWZM1582E);
                return;
            }
        }
    }

    /**
     * releaseHold
     * @param cpoBean NWZC155001CpoBean
     */
    private void releaseHold(NWZC155001CpoBean cpoBean) {
        final String glblCmpyCd = cpoBean.getGlblCmpyCd();
        final String cpoOrdNum = cpoBean.getCpoOrdNum();

        // -------------------------------------------------
        // update Hold Flag of DS_CPO
        CPOTMsg updDsCpoTMsg = (CPOTMsg) getCpoTMsgForUpdate(glblCmpyCd, cpoOrdNum);
        if (updDsCpoTMsg == null) {
            msgMap.addXxMsgId(NWZM1208E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(updDsCpoTMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
        S21ApiTBLAccessor.update(updDsCpoTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsCpoTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1208E);
            return;
        }

        // -------------------------------------------------
        // release Hold from HLD
        HLDTMsg updHldTMsg = (HLDTMsg) getHldTMsgForUpdate(glblCmpyCd, cpoOrdNum);
        if (updHldTMsg == null) {
            return;
        }

        // 2017/10/26 S21_NA#21820 Add Start
        boolean isBooked = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, updDsCpoTMsg.ordBookFlg.getValue());

        if (isBooked) {
            NWZC033001PMsg hldReleaseApiPMsg = new NWZC033001PMsg();
            ZYPEZDItemValueSetter.setValue(hldReleaseApiPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(hldReleaseApiPMsg.cpoOrdNum, cpoBean.getCpoOrdNum());
            ZYPEZDItemValueSetter.setValue(hldReleaseApiPMsg.hldRelRsnCd, HLD_RSN.DI_CHECK_HOLD);
            ZYPEZDItemValueSetter.setValue(hldReleaseApiPMsg.slsDt, cpoBean.getSlsDt());
            ZYPEZDItemValueSetter.setValue(hldReleaseApiPMsg.hldPk, updHldTMsg.hldPk);

            new NWZC033001().execute(hldReleaseApiPMsg, onBatchType);

            if (S21ApiUtil.isXxMsgId(hldReleaseApiPMsg)) {

                List<String> errList = S21ApiUtil.getXxMsgIdList(hldReleaseApiPMsg);

                if (!errList.isEmpty()) {
                    for (String xxMsgId : errList) {
                        if (xxMsgId.endsWith("E")) {
                            msgMap.addXxMsgId(xxMsgId);
                            return;
                        }
                    }
                }
            }
            return;
        }
        // 2017/10/26 S21_NA#21820 Add End
        ZYPEZDItemValueSetter.setValue(updHldTMsg.relPsnCd, cpoBean.getDiChkSubmtPsnCd());
        ZYPEZDItemValueSetter.setValue(updHldTMsg.relFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(updHldTMsg.relDt, cpoBean.getSlsDt());

        // 2016/11/01 S21_NA#15682 Mod Start
        S21ApiTBLAccessor.update(updHldTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updHldTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM0080E);
        }
        // 2016/11/01 S21_NA#15682 Mod End
    }

    /**
     * registHld
     * @param cpoBean NWZC155001CpoBean
     */
    private void registHold(NWZC155001CpoBean cpoBean) {

        // update Hold Flag of DS_CPO
        final String glblCmpyCd = cpoBean.getGlblCmpyCd();
        final String cpoOrdNum = cpoBean.getCpoOrdNum();
        CPOTMsg updDsCpoTMsg = (CPOTMsg) getCpoTMsgForUpdate(glblCmpyCd, cpoOrdNum);
        if (updDsCpoTMsg == null) {
            msgMap.addXxMsgId(NWZM1208E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(updDsCpoTMsg.diChkHldFlg, ZYPConstant.FLG_ON_Y);
        S21ApiTBLAccessor.update(updDsCpoTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDsCpoTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1208E);
            return;
        }

        // If the status is "SAVE", it is not performed HOLD
        final CPOTMsg tMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cpoOrdNum);

        CPOTMsg resMsg = (CPOTMsg) findByKeyWithCache(tMsg);

        if (ORD_HDR_STS.SAVED.equals(resMsg.ordHdrStsCd.getValue())) {
            return;
        }

        EZDTMsgArray hldTMsgAry = (HLDTMsgArray) getHldTMsg(glblCmpyCd, cpoOrdNum);

        // register Hold
        if (hldTMsgAry.getValidCount() < 1) {
            callNWZC044001AddHoldInfoAPI(cpoBean);
        }
    }

    /**
     * call NWZC044001AddHlodInfoAPI
     * @param cpoBean NWZC155001CpoBean
     * @param linePMsgList List<NWZC155002PMsg>
     */
    private void callNWZC044001AddHoldInfoAPI(NWZC155001CpoBean cpoBean) {
        NWZC044001PMsg addHoldInfoPMsg = new NWZC044001PMsg();

        ZYPEZDItemValueSetter.setValue(addHoldInfoPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(addHoldInfoPMsg.slsDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(addHoldInfoPMsg.hldRsnCd, HLD_RSN.DI_CHECK_HOLD);
        ZYPEZDItemValueSetter.setValue(addHoldInfoPMsg.cpoOrdNum, cpoBean.getCpoOrdNum());

        NWZC044001 addHoldInfoAPI = new NWZC044001();
        addHoldInfoAPI.execute(addHoldInfoPMsg, onBatchType);

        if (addHoldInfoPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int j = 0; j < addHoldInfoPMsg.xxMsgIdList.getValidCount(); j++) {
                String xxMsgId = addHoldInfoPMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                msgMap.addXxMsgId(xxMsgId);
            }
        }
    }

    /**
     * Update status of last DI Check result
     * @param cpoBean NWZC155001CpoBean
     */
    private void updStsOfLstDiChk(NWZC155001CpoBean cpoBean) {
        if (cpoBean.getDiChkVrsnNum().compareTo(BigDecimal.ONE) == 0) {
            return;
        }

        // get latest process of DI Check
        BigDecimal lstVrsnNum = subtract(cpoBean.getDiChkVrsnNum(), BigDecimal.ONE);
        DS_CPO_DI_CHK_RSLT_HDRTMsg updDiChkRsltHdrTMsg = (DS_CPO_DI_CHK_RSLT_HDRTMsg) getDsCpoDiChkRsltHdrTMsgForUpdate(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum(), lstVrsnNum);

        if (updDiChkRsltHdrTMsg == null) {
            msgMap.addXxMsgId(NWZM1581E);
            return;
        }

        // set DI Check Status
        ZYPEZDItemValueSetter.setValue(updDiChkRsltHdrTMsg.diChkStsCd, DI_CHK_STS.OVERRIDDEN);

        // update
        S21ApiTBLAccessor.update(updDiChkRsltHdrTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updDiChkRsltHdrTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NWZM1581E);
        }
    }

    /**
     * subtract value of BigDecimal
     * @param bc1 BigDecimal
     * @param bc2 BigDecimal
     * @return BigDecimal
     */
    private static BigDecimal subtract(BigDecimal bc1, BigDecimal bc2) {
        if (!ZYPCommonFunc.hasValue(bc1)) {
            bc1 = BigDecimal.ZERO;
        }
        if (!ZYPCommonFunc.hasValue(bc2)) {
            bc2 = BigDecimal.ZERO;
        }
        return bc1.subtract(bc2);
    }

    /**
     * getDiChckCd
     * @param cpoBean NWZC155001CpoBean
     */
    private List<NWZC155001DiChkBean> getDiChckCd(NWZC155001CpoBean cpoBean) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        // ssmParam.put("cpoOrdTs", cpoBean.getCpoOrdTs());
        ssmParam.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());
        // 2018/08/17 S21_NA#26271 Mod Start
        ssmParam.put("dsOrdTpCd", cpoBean.getDsOrdTpCd());
        ssmParam.put("slsDt", cpoBean.getSlsDt());
        // 2018/08/17 S21_NA#26271 Mod End
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);

        // 2022/09/20 QC#54883 Add Start N.Takatsu
        String ordBookFlg = cpoBean.getOrdBookFlg();
        boolean isBooked = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, ordBookFlg);
        if (isBooked == false) {
            ordBookFlg = ZYPConstant.FLG_OFF_N;
        }
        ssmParam.put("ordBookFlg", ordBookFlg);
        // 2022/09/20 QC#54883 Add End N.Takatsu
        List<NWZC155001DiChkBean> diChkList = new ArrayList<NWZC155001DiChkBean>();
        List<Map> mapList = (List<Map>) ssmClient.queryObjectList("getDiChkCd", ssmParam);

        NWZC155001DiChkBean bean;
        for (Map map : mapList) {
            bean = new NWZC155001DiChkBean();
            bean.setDiChckCd(map.get("DI_CHK_CD").toString());
            bean.setDiChkLvlCd(map.get("DI_CHK_LVL_CD").toString());
            bean.setDiChkRsltTpCd(map.get("DI_CHK_RSLT_TP_CD").toString());
            bean.setDiMndChkPrflCd((String) map.get("DI_MND_CHK_PRFL_CD")); // QC#4657

            diChkList.add(bean);
        }
        return diChkList;
    }

    /**
     * getLastVrsnNum
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private BigDecimal getLastVrsnNum(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);

        return (BigDecimal) ssmClient.queryObject("getLastVrsnNum", ssmParam);
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
     * getCpoTMsgForUpdate
     * @param glblCmpyCd String
     * @param cpoNum String
     * @return EZDTMsg
     */
    private static EZDTMsg getCpoTMsgForUpdate(String glblCmpyCd, String ordNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(ordNum)) {
            return null;
        }

        CPOTMsg updTMsgKey = new CPOTMsg();

        ZYPEZDItemValueSetter.setValue(updTMsgKey.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(updTMsgKey.cpoOrdNum, ordNum);

        CPOTMsg updTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(updTMsgKey);
        if (updTMsg == null || !S21FastTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
            return null;
        }
        return updTMsg;
    }

    // 2016/11/01 S21_NA#15682 Mod Start
    /**
     * getHldTMsgForUpdate
     * @param glblCmpyCd String
     * @param cpoNum String
     * @return EZDTMsg
     */
    private static EZDTMsg getHldTMsgForUpdate(String glblCmpyCd, String ordNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(ordNum)) {
            return null;
        }

        final HLDTMsg tMsg = new HLDTMsg();
        tMsg.setSQLID("016");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("hldRsnCd01", HLD_RSN.DI_CHECK_HOLD);
        tMsg.setConditionValue("cpoOrdNum01", ordNum);
        tMsg.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);

        HLDTMsgArray updTMsg = (HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
        if (updTMsg.getValidCount() != 1) {
            return null;
        }
        return updTMsg.no(0);
    }

    // 2016/11/01 S21_NA#15682 Mod End

    /**
     * getHldTMsg
     * @param glblCmpyCd String
     * @param ordNum String
     * @return EZDTMsgArray
     */
    private static EZDTMsgArray getHldTMsg(String glblCmpyCd, String ordNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(ordNum)) {
            return null;
        }

        final HLDTMsg tMsg = new HLDTMsg();
        tMsg.setSQLID("016");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("hldRsnCd01", HLD_RSN.DI_CHECK_HOLD);
        tMsg.setConditionValue("cpoOrdNum01", ordNum);
        tMsg.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);

        return S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * getHldTMsgForUpdate
     * @param glblCmpyCd String
     * @param cpoNum String
     * @param lstVrsnNum BigDecimal
     * @return EZDTMsg
     */
    private static EZDTMsg getDsCpoDiChkRsltHdrTMsgForUpdate(String glblCmpyCd, String ordNum, BigDecimal lstVrsnNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(ordNum)) {
            return null;
        }

        DS_CPO_DI_CHK_RSLT_HDRTMsg updTMsgKey = new DS_CPO_DI_CHK_RSLT_HDRTMsg();
        updTMsgKey.setSQLID("001");
        updTMsgKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
        updTMsgKey.setConditionValue("cpoOrdNum01", ordNum);
        updTMsgKey.setConditionValue("diChkVrsnNum01", lstVrsnNum);

        DS_CPO_DI_CHK_RSLT_HDRTMsgArray updTMsg = (DS_CPO_DI_CHK_RSLT_HDRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(updTMsgKey);
        if (updTMsg.getValidCount() != 1) {
            return null;
        }
        return updTMsg.no(0);
    }

    // 2016/09/30 DEL START QC#14779
    // /**
    // * getDsCpoVTMsg
    // * @param glblCmpyCd String
    // * @param ordNum String
    // * @return EZDTMsg
    // */
    // private static EZDTMsg getDsCpoVTMsg(String glblCmpyCd, String
    // ordNum) {
    // if (!ZYPCommonFunc.hasValue(glblCmpyCd) ||
    // !ZYPCommonFunc.hasValue(ordNum)) {
    // return null;
    // }
    //
    // final DS_CPO_VTMsg tMsg = new DS_CPO_VTMsg();
    // tMsg.setSQLID("001");
    // tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    // tMsg.setConditionValue("cpoOrdNum01", ordNum);
    // DS_CPO_VTMsgArray tMsgResult = (DS_CPO_VTMsgArray)
    // EZDTBLAccessor.findByCondition(tMsg);
    //
    // if (tMsgResult.length() == 0) {
    // return null;
    // }
    // return tMsgResult.no(0);
    // }
    // 2016/09/30 DEL END QC#14779

    /**
     * getDsCpoConfigTMsg
     * @param glblCmpyCd String
     * @param ordNum String
     * @return EZDTMsg
     */
    private static DS_CPO_CONFIGTMsgArray getDsCpoConfigTMsg(String glblCmpyCd, String ordNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(ordNum)) {
            return null;
        }

        final DS_CPO_CONFIGTMsg tMsg = new DS_CPO_CONFIGTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cpoOrdNum01", ordNum);
        DS_CPO_CONFIGTMsgArray tMsgResult = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        return tMsgResult;
    }

    // 2016/09/30 DEL START QC#14779
    // /**
    // * getDsCpoDtlVTMsgArray
    // * @param glblCmpyCd String
    // * @param cpoOrdNum String
    // * @return CPO_DTLTMsgArray
    // */
    // private DS_CPO_DTL_VTMsgArray getDsCpoDtlVTMsgArray(String
    // glblCmpyCd, String cpoOrdNum) {
    // FindCondition fc = new FindCondition("001");
    // fc.addCondition("glblCmpyCd01", glblCmpyCd);
    // fc.addCondition("cpoOrdNum01", cpoOrdNum);
    //
    // return (DS_CPO_DTL_VTMsgArray)
    // localCache.dsCpoDtlVCache.getTMsgArray(fc);
    // }
    // 2016/09/30 DEL E N D QC#14779

    /**
     * getCpoDtl
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return CPO_DTLTMsgArray
     */
    private EZDTMsg getCpoDtl(String glblCmpyCd, String ordNum, String lineNum, String lineSubNum) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(ordNum) || !ZYPCommonFunc.hasValue(lineNum) || !ZYPCommonFunc.hasValue(lineSubNum)) {
            return null;
        }

        final CPO_DTLTMsg tMsg = new CPO_DTLTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.cpoOrdNum.setValue(ordNum);
        tMsg.cpoDtlLineNum.setValue(lineNum);
        tMsg.cpoDtlLineSubNum.setValue(lineSubNum);

        return findByKeyWithCache(tMsg);
    }

    // 2016/09/30 DEL START QC#14779
    // /**
    // * getDsCpoRtrnDtlVTMsgArray
    // * @param glblCmpyCd String
    // * @param cpoOrdNum String
    // * @return CPO_DTLTMsgArray
    // */
    // private DS_CPO_RTRN_DTL_VTMsgArray
    // getDsCpoRtrnDtlVTMsgArray(String glblCmpyCd, String cpoOrdNum)
    // {
    // FindCondition fc = new FindCondition("001");
    // fc.addCondition("glblCmpyCd01", glblCmpyCd);
    // fc.addCondition("cpoOrdNum01", cpoOrdNum);
    //
    // return (DS_CPO_RTRN_DTL_VTMsgArray)
    // localCache.dsCpoRtrnDtlVCache.getTMsgArray(fc);
    // }
    // 2016/09/30 DEL E N D QC#14779

    /**
     * isError
     * @param pMsg NWZC155001PMsg
     * @return boolean
     */
    private boolean isError(NWZC155001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Local data cache class. used by master data, transaction data.
     */
    private static final class LocalCache {
        /** DS_CPO_DTL_V Cache */
        private final DsCpoDtlVCache dsCpoDtlVCache = new DsCpoDtlVCache();

        /** DS_CPO_RTRN_DTL_V Cache */
        private final DsCpoRtrnDtlVCache dsCpoRtrnDtlVCache = new DsCpoRtrnDtlVCache();
    }

    // =========================================================================================
    // DI CHECKING PROCESS below here
    // =========================================================================================

    /**
     * doProcess_DiCheck
     * @param cpoBean NWZC155001CpoBean
     * @param checkMode String
     */
    private void doProcess_DiCheck(NWZC155001CpoBean cpoBean, String chkMode, NWZC155001PMsg pMsg) {

        for (NWZC155001DiChkBean diChkBean : cpoBean.getDiChkBeanList()) {

            if (isError(pMsg)) {
                return;
            }

            // All Mode (Mode: 01 Thru 04)
            if (commonDiChkForAllMode(cpoBean, diChkBean)) {
                continue;
            }

            // Line and RMA Mode (Mode:01)
            if (CHK_MODE_LINE_RMA.equals(chkMode)) {
                if (commonDiChkForLineMode(cpoBean, diChkBean, pMsg)) {
                    continue;
                }
                commonDiChkForRmaMode(cpoBean, diChkBean, pMsg);
                continue;
            }

            // Line Mode (Mode:02)
            if (CHK_MODE_LINE.equals(chkMode)) {
                commonDiChkForLineMode(cpoBean, diChkBean, pMsg);

                // START 2023/01/30 R.Azucena [QC#61094, ADD]
                if (E099_1330.equals(diChkBean.getDiChckCd())) {
                    checkLoanForLoanOrders(cpoBean, diChkBean);
                }
                // END 2023/01/30 R.Azucena [QC#61094, ADD]
                continue;
            }

            // RMA Mode (Mode:03)
            if (CHK_MODE_RMA.equals(chkMode)) {
                commonDiChkForRmaMode(cpoBean, diChkBean, pMsg);
            }
        }

        List<Map> acceptList = getAcceptRslt(cpoBean);

        if (acceptList != null) {
            for (int i = 0; i < cpoBean.getDiResultList().size(); i++) {
                NWZC155001DiResultBean diResBean = cpoBean.getDiResultList().get(i);
                for (Map<String, Object> acceptMap : acceptList) {
                    if (S21StringUtil.isEquals(diResBean.getDiChckCd(), (String) acceptMap.get("DI_CHK_CD")) //
                            && S21StringUtil.isEquals(diResBean.getDiChkLvlCd(), (String) acceptMap.get("DI_CHK_LVL_CD")) //
                            && S21StringUtil.isEquals(diResBean.getDsOrdPosnNum(), (String) acceptMap.get("DI_ERR_ORD_POSN_NUM")) //
                            && S21StringUtil.isEquals(diResBean.getCpoLineNum(), (String) acceptMap.get("DI_ERR_CPO_LINE_NUM")) //
                            && S21StringUtil.isEquals(diResBean.getCpoLineSubNum(), (String) acceptMap.get("DI_ERR_CPO_LINE_SUB_NUM"))) {

                        diResBean.setDiChkDtlStsCd(DI_CHK_DTL_STS.ACCEPTED);
                        break;
                    }
                }
            }
        }
    }

    /**
     * getAcceptRslt
     * @param cpoBean NWZC155001CpoBean
     * @param checkMode String
     */
    private List<Map> getAcceptRslt(NWZC155001CpoBean cpoBean) {

        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String ordNum = cpoBean.getCpoOrdNum();

        String maxVesnNum = getMaxVrsnNum(glblCmpyCd, ordNum);

        if (!ZYPCommonFunc.hasValue(maxVesnNum)) {
            return null;
        }

        Map vrsnPrevMap = getVrsnNumPresencePrev(glblCmpyCd, ordNum, maxVesnNum);

        if (vrsnPrevMap == null) {
            return null;
        }

        return getAcceptRsltForPrev(glblCmpyCd, ordNum, maxVesnNum);

    }

    /**
     * getMaxVrsnNum
     * @param glblCmpyCd String
     * @param ordNum String
     * @return String
     */
    private String getMaxVrsnNum(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);

        return (String) ssmClient.queryObject("getMaxVrsnNum", ssmParam);
    }

    /**
     * getVerNumPresencePrev
     * @param glblCmpyCd String
     * @param ordNum String
     * @param verNum String
     * @return Map
     */
    private Map getVrsnNumPresencePrev(String glblCmpyCd, String ordNum, String verNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("vrsnNum", verNum);

        return (Map) ssmClient.queryObject("getVrsnNumPresencePrev", ssmParam);
    }

    /**
     * getAcceptRsltForPrev
     * @param glblCmpyCd String
     * @param ordNum String
     * @param verNum String
     * @return List<Map>
     */
    private List<Map> getAcceptRsltForPrev(String glblCmpyCd, String ordNum, String verNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("vrsnNum", verNum);
        ssmParam.put("diChkDtlStsCd", DI_CHK_STS.ACCEPTED);

        return (List<Map>) ssmClient.queryObjectList("getAcceptRsltForPrev", ssmParam);
    }

    /**
     * commonDiChkForAllMode
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    private boolean commonDiChkForAllMode(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        if (E099_1000.equals(diChkBean.getDiChckCd())) {
            checkCustomerEquipmentPoRequired(cpoBean, diChkBean);
            return true;
        }
        if (E099_1005.equals(diChkBean.getDiChckCd())) {
            checkCustomerMaintainPoRequired(cpoBean, diChkBean);
            return true;
        }
        if (E099_1010A.equals(diChkBean.getDiChckCd())) {
            checkLeaseEqupimentPoRequired(cpoBean, diChkBean);
            return true;
        }
        if (E099_1010B.equals(diChkBean.getDiChckCd())) {
            checkLeasePoFormat(cpoBean, diChkBean);
            return true;
        }
        if (E099_1015.equals(diChkBean.getDiChckCd())) {
            checkDuplicatePo(cpoBean, diChkBean);
            return true;
        }
        if (E099_1035A.equals(diChkBean.getDiChckCd())) {
            checkTaxExemExpire(cpoBean, diChkBean);
            return true;
        }
        if (E099_1035B.equals(diChkBean.getDiChckCd())) {
            checkTaxExemExpire(cpoBean, diChkBean);
            return true;
        }
        if (E099_1040.equals(diChkBean.getDiChckCd())) {
            checkDiffSubTotalAndTotalMsrp(cpoBean, diChkBean);
            return true;
        }
        if (E099_1055.equals(diChkBean.getDiChckCd())) {
            checkAssociationAndComplianceCodeValid(cpoBean, diChkBean);
            return true;
        }

        // QC#4657 Add
        if (E099_1075.equals(diChkBean.getDiChckCd())) {
            checkHeaderFieldRequire(cpoBean, diChkBean);
            return true;
        }

        // 2016/02/19 S21_NA#1686 Add Start
        if (E099_IT03.equals(diChkBean.getDiChckCd())) {
            checkPrepaymentAmt(cpoBean, diChkBean);
            return true;
        }
        // 2016/02/19 S21_NA#1686 Add End

        // mod start 2016/09/07 CSA S21_NA#11774
        if (E099_1200.equals(diChkBean.getDiChckCd())) {
            checkServiceShellValid(cpoBean, diChkBean);
            return true;
        }
        // mod end 2016/09/07 CSA S21_NA#11774

        // QC#24245 2018/06/13 Add Start
        if (E099_1200A.equals(diChkBean.getDiChckCd())) {
            checkServiceShellValidByConfig(cpoBean, diChkBean);
            return true;
        }
        // QC#24245 2018/06/13 Add End

        // 2017/12/15 S21_NA#19804 ADD START
        if (E099_1260B.equals(diChkBean.getDiChckCd())) {
            checkDefaultSalesRep(cpoBean, diChkBean);
            return true;
        }

        if (E099_1260A.equals(diChkBean.getDiChckCd())) {
            checkDefaultSalesRep(cpoBean, diChkBean);
            return true;
        }
        // 2017/12/15 S21_NA#19804 ADD END

        // Add Start 2019/04/24 QC#31125,QC#31126
        if (E099_1300A.equals(diChkBean.getDiChckCd())) {
            checkSalesRepActive(cpoBean, diChkBean);
            return true;
        }

        if (E099_1300B.equals(diChkBean.getDiChckCd())) {
            checkSalesRepActive(cpoBean, diChkBean);
            return true;
        }
        // Add End 2019/04/24 QC#31125,QC#31126

        return false;
    }

    /**
     * commonDiChkForLineMode
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    private boolean commonDiChkForLineMode(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001PMsg pMsg) {

        if (E099_1020A.equals(diChkBean.getDiChckCd())) {
            checkCusomertControlField(cpoBean, diChkBean);
            return true;
        }
        if (E099_1020B.equals(diChkBean.getDiChckCd())) {
            checkCusomertControlField(cpoBean, diChkBean);
            return true;
        }
        if (E099_1025.equals(diChkBean.getDiChckCd())) {
            checkServiceShellDetailRequire(cpoBean, diChkBean);
            return true;
        }
        // 2017/08/04 S21_NA#20374 Add Start
        if (E099_1025A.equals(diChkBean.getDiChckCd())) {
            checkServiceShellCustmerRelation(cpoBean, diChkBean);
            return true;
        }
        // 2017/08/04 S21_NA#20374 Add End
        if (E099_1030.equals(diChkBean.getDiChckCd())) {
            checkRentalShellDetailRequire(cpoBean, diChkBean);
            return true;
        }
        if (E099_1045.equals(diChkBean.getDiChckCd())) {
            checkSowRequire(cpoBean, diChkBean);
            return true;
        }
        if (E099_1050.equals(diChkBean.getDiChckCd())) {
            checkSiteSrvyRequire(cpoBean, diChkBean);
            return true;
        }
        if (E099_1065A.equals(diChkBean.getDiChckCd())) {
            checkCtacValidationForHeaderLevel(cpoBean, diChkBean);
            return true;
        }
        if (E099_1065B.equals(diChkBean.getDiChckCd())) {
            checkCtacValidationForLineLevel(cpoBean, diChkBean);
            return true;
        }

        // QC#5383 Add Start
        if (E099_1065C.equals(diChkBean.getDiChckCd())) {
            checkCtacValidation(cpoBean, diChkBean, CONFIG_CATG.OUTBOUND);
            return true;
        }
        // QC#5383 Add End

        if (E099_1070.equals(diChkBean.getDiChckCd())) {
            // 2017/01/18 S21_NA#17138 Mod Start
            // checkProServicePrice(cpoBean, diChkBean, pMsg);
            // return true;
            boolean isProfitUsgReason = isProfitUsgReason(cpoBean.getGlblCmpyCd(), cpoBean.getDsOrdTpCd());
            if (isProfitUsgReason) {
                checkProServicePrice(cpoBean, diChkBean, pMsg);
                return true;
            }
            // 2017/01/18 S21_NA#17138 Mod Start
        }

        // QC#4657
        if (E099_1080.equals(diChkBean.getDiChckCd())) {
            checkLineFieldRequire(cpoBean, diChkBean);
            return true;
        }

        // 2017/10/26 S21_NA#21100 Add Start
        if (E099_1081.equals(diChkBean.getDiChckCd())) {
            checkLineIstlDivRequire(cpoBean, diChkBean);
            return true;
        }
        // 2017/10/26 S21_NA#21100 Add End

        // QC#8611 Add Start
        if (E099_1100.equals(diChkBean.getDiChckCd())) {
            checkSiteSrvyRequireWarning(cpoBean, diChkBean);
            return true;
        }
        // QC#8611 Add End

        // QC#26410
        if (E099_1110.equals(diChkBean.getDiChckCd())) {
            checkServiceAllocationType(cpoBean, diChkBean);
            return true;
        }

        if (E099_IT01.equals(diChkBean.getDiChckCd())) {
            checkDiffModel(cpoBean, diChkBean);
            return true;
        }
        if (E099_IT02.equals(diChkBean.getDiChckCd())) {
            checkDealerAuthValid(cpoBean, diChkBean);
            return true;
        }

        // S21_NA#7942 ADD START
        if (E099_1210.equals(diChkBean.getDiChckCd())) {
            checkMinMaxPriceRange(cpoBean, diChkBean);
            return true;
        }
        // QC#7942 ADD End

        // Add Start S21_NA#15510
        if (E099_1220.equals(diChkBean.getDiChckCd())) {
            checkEmsdSalesRep(cpoBean, diChkBean);
            return true;
        }
        // Add End S21_NA#15510

        // Add Start S21_NA#20022
        if (E099_1230.equals(diChkBean.getDiChckCd())) {
            checkAutoAddSplyItem(cpoBean, diChkBean);
            return true;
        }
        // Add End S21_NA#20022

        // Add Start S21_NA#21064
        if (E099_1240.equals(diChkBean.getDiChckCd())) {
            checkSerNumRequire(cpoBean, diChkBean);
            return true;
        }
        // Add End S21_NA#21064

        // QC#21471 LEASE CFS ACCOUNT CHECK
        if (E099_1250.equals(diChkBean.getDiChckCd())) {
            checkLeaseCfsAcct(cpoBean, diChkBean);
            return true;
        }

        // 2018/03/01 S21_NA#24117 add start
        if (E099_1270.equals(diChkBean.getDiChckCd())) {
            checkMandatoryMdlId(cpoBean, diChkBean);
            return true;
        }
        // 2018/03/01 S21_NA#24117 add End

        // 2018/03/15 S21_NA#24253 Add Start
        if (E099_1280.equals(diChkBean.getDiChckCd())) {
            checkConfigShiptoLocation(cpoBean, diChkBean);
            return true;
        }
        // 2018/03/15 S21_NA#24253 Add End

        // 2019/04/04 S21_NA#30819 Add Start
        if (E099_1290.equals(diChkBean.getDiChckCd())) {
            checkCustIstl(cpoBean, diChkBean);
            return true;
        }
        // 2019/04/04 S21_NA#30819 Add End

        // 2019/11/22 QC#54213 Add Start
        if (E099_1310.equals(diChkBean.getDiChckCd())) {
            checkServiceShellDetailNoRequire(cpoBean, diChkBean);
            return true;
        }
        // 2019/11/22 QC#54213 Add End

        // 2022/11/25 QC#60398 Add Start
        if (E099_1320.equals(diChkBean.getDiChckCd())) {
            checkHardCopyRequired(cpoBean, diChkBean);
            return true;
        }
        // 2022/11/25 QC#60398 Add End
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (E099_1340.equals(diChkBean.getDiChckCd())) {
            checkToBeInstalledByOrServiceProvidedBy(cpoBean, diChkBean);
            return true;
        }
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        return false;
    }

    // QC#26410
    private void checkServiceAllocationType(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();

        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd())) {
                continue;
            }

            if (!NWXC150001DsCheck.isAvalableItemForOrderLine(cpoBean.getGlblCmpyCd(), cpoDtlBean.getMdseCd(), cpoDtlBean.getCrRebilCd())) {
                // set Error
                setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkBean.getDiChckCd() //
                        , E099_1110_ERROR_MSG.replace("@", cpoDtlBean.getMdseCd()) //
                        , cpoDtlBean.getDsOrdPosnNum() //
                        , cpoDtlBean.getDsCpoLineNum() //
                        , cpoDtlBean.getDsCpoLineSubNum() //
                        , cpoDtlBean.getConfigCatgCd());
                // return; // 2018/10/05 S21_NA#28596 Del
            }
        }
    }

    // QC#21471
    private void checkLeaseCfsAcct(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        if (!NWXC150001DsCheck.isCfsBillToAcct(cpoBean.getGlblCmpyCd(), cpoBean.getBillToCustAcctCd(), cpoBean.getOrdDt())) {
            // set Error
            setErrToDiRsltBean(//
                    cpoBean //
                    , null //
                    , diChkBean.getDiChckCd() //
                    , diChkBean.getDiChkLvlCd() //
                    , diChkBean.getDiChkRsltTpCd() //
                    , E099_1250_ERROR_MSG);
            return;
        }

    }

    /**
     * commonDiChkForRmaMode
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param pMsg NWZC155001PMsg
     */
    private void commonDiChkForRmaMode(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001PMsg pMsg) {
        if (E099_1085.equals(diChkBean.getDiChckCd())) {
            checkDiffShipToLoc(cpoBean, diChkBean);
            return;
        }
        if (E099_1090.equals(diChkBean.getDiChckCd())) {
            checkDuplicateRmaChk(cpoBean, diChkBean);
            return;
        }
        if (E099_1060.equals(diChkBean.getDiChckCd())) {
            checkHddValidation(cpoBean, diChkBean, pMsg);
        }

        // QC#22664 Add Start
        if (E099_1065D.equals(diChkBean.getDiChckCd())) {
            checkCtacValidation(cpoBean, diChkBean, CONFIG_CATG.INBOUND);
        }
        // QC#22664 Add End
    }

    /**
     * checkCustomerEquipmentPoRequired
     * 
     * <pre>
     * DI Check Code: E099-1000
     * Customer Equipment PO Required
     * Set error if required customer Equipment PO is not input
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkCustomerEquipmentPoRequired(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String ordNum = cpoBean.getCpoOrdNum();

        // S21_NA#11552 START
        if (isAllLineCredit(glblCmpyCd, ordNum)) {
            return;
        }
        // S21_NA#11552 END

        // 2017/07/18 S21_NA#19983 Add Start
        boolean isLeaseOrder = isLeaseOrder(glblCmpyCd, ordNum);
        // 2017/07/18 S21_NA#19983 Add Start

        // get Customer Issue PO Number By Location Code
        String custIssPoNum = null;
        // 2017/07/18 S21_NA#19983 Mod Start
        // Map resMap = getPoNumByLocCd(glblCmpyCd, ordNum);
        Map resMap = null;
        if (isLeaseOrder) {
            resMap = getPoNumByLocCdFromSoldTo(glblCmpyCd, ordNum);
        } else {
            resMap = getPoNumByLocCd(glblCmpyCd, ordNum);
        }
        // 2017/07/18 S21_NA#19983 Mod Start

        if (resMap != null && resMap.size() > 0) {
            custIssPoNum = (String) resMap.get("CUST_ISS_PO_NUM");
            if (ZYPCommonFunc.hasValue(custIssPoNum)) {
                cpoBean.setCustIssPoNum(custIssPoNum);
                return;
            } else {
                // set Error
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Customer Equipment PO on the order# ").append(ordNum);
                msgParam.append(" cannot be NULL on the order header");
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                return;
            }
        }

        // get Customer Issue PO Number By Account Code
        // 2017/07/18 S21_NA#19983 Mod Start
        // resMap = getPoNumByAcctCd(glblCmpyCd, ordNum);
        if (isLeaseOrder) {
            resMap = getPoNumBySoldToAcctCd(glblCmpyCd, ordNum);
        } else {
            resMap = getPoNumByAcctCd(glblCmpyCd, ordNum);
        }
        // 2017/07/18 S21_NA#19983 Mod End

        if (resMap != null && resMap.size() > 0) {
            custIssPoNum = (String) resMap.get("CUST_ISS_PO_NUM");
            if (ZYPCommonFunc.hasValue(custIssPoNum)) {
                cpoBean.setCustIssPoNum(custIssPoNum);
                return;
            } else {
                // set Error
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Customer Equipment PO on the order# ").append(ordNum);
                msgParam.append(" cannot be NULL on the order header");
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                return;
            }
        }
    }

    // S21_NA#11552 START
    @SuppressWarnings("unchecked")
    private boolean isAllLineCredit(String glblCmpyCd, String cpoOrdNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        ssmParam.put("rtrnLineStsCancel", RTRN_LINE_STS.CANCELLED);

        List<Map<String, String>> getDsCpoDtlDsRtrnDtlList = ssmClient.queryObjectList("getDsCpoDtlDsRtrnDtlList", ssmParam);

        if (getDsCpoDtlDsRtrnDtlList == null || getDsCpoDtlDsRtrnDtlList.size() == 0) {
            return false;
        }

        boolean allLineCreditFlg = true;
        for (Map<String, String> dsCpoDtlDsRtrnDtl : getDsCpoDtlDsRtrnDtlList) {
            if (!CR_REBIL.CREDIT.equals(dsCpoDtlDsRtrnDtl.get("CR_REBIL_CD"))) {
                allLineCreditFlg = false;
                break;
            }
        }

        if (allLineCreditFlg) {
            return true;
        }

        return false;

    }

    // S21_NA#11552 END

    // S21_NA#21594 START
    @SuppressWarnings("unchecked")
    private boolean isAllLineRebill(String glblCmpyCd, String cpoOrdNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("ordLineStsCancel", ORD_LINE_STS.CANCELLED);
        ssmParam.put("rtrnLineStsCancel", RTRN_LINE_STS.CANCELLED);

        List<Map<String, String>> getDsCpoDtlDsRtrnDtlList = ssmClient.queryObjectList("getDsCpoDtlDsRtrnDtlList", ssmParam);

        if (getDsCpoDtlDsRtrnDtlList == null || getDsCpoDtlDsRtrnDtlList.size() == 0) {
            return false;
        }

        boolean allLineCreditFlg = true;
        for (Map<String, String> dsCpoDtlDsRtrnDtl : getDsCpoDtlDsRtrnDtlList) {
            if (!CR_REBIL.REBILL.equals(dsCpoDtlDsRtrnDtl.get("CR_REBIL_CD"))) {
                allLineCreditFlg = false;
                break;
            }
        }

        if (allLineCreditFlg) {
            return true;
        }

        return false;

    }

    // S21_NA#21594 END

    /**
     * getPoNumByLocCd
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private Map getPoNumByLocCd(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (Map) ssmClient.queryObject("getPoNumByLocCd", ssmParam);
    }

    /**
     * getPoNumByAcctCd
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private Map getPoNumByAcctCd(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (Map) ssmClient.queryObject("getPoNumByAcctCd", ssmParam);
    }

    /**
     * checkCustomerMaintainPoRequired
     * 
     * <pre>
     * DI Check Code: E099-1005
     * Customer Maintenance PO Required
     * Set error if required customer Maintenance PO is not input
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkCustomerMaintainPoRequired(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String ordNum = cpoBean.getCpoOrdNum();

        // S21_NA#11552 START
        if (isAllLineCredit(glblCmpyCd, ordNum)) {
            return;
        }
        // S21_NA#11552 END

        // S21_NA#8388 START
        if (isDeclineSvc(cpoBean)) {
            return;
        }
        // S21_NA#8388 END

        // get Maintenance PO Num By Location
        List<Map<String, Object>> maintPoMapByLoc = getMaintPoNumByLocCd(glblCmpyCd, ordNum);

        // Mod Start 2016/12/21 M.Ohno S21_NA#16652
        for (Map<String, Object> mapLoc : maintPoMapByLoc) {
            if (ZYPCommonFunc.hasValue((String) mapLoc.get("CUST_PO_NUM"))) {
                continue;
            }

            // S21_NA#8388 START
            if (isDeclineSvcCofig(glblCmpyCd, ordNum, (String) mapLoc.get("CPO_DTL_LINE_NUM"))) {
                continue;
            }
            // S21_NA#8388 END

            String lineNum = mapLoc.get("DS_CPO_LINE_NUM").toString();
            String lineSubNum = null;
            if (ZYPCommonFunc.hasValue((BigDecimal) mapLoc.get("DS_CPO_LINE_SUB_NUM"))) {
                lineSubNum = mapLoc.get("DS_CPO_LINE_SUB_NUM").toString();
            }

            // set Error
            if (!ZYPCommonFunc.hasValue((String) mapLoc.get("CUST_PO_NUM"))) {
                for (int i = 0; i < cpoBean.getCpoDtlBeanList().size(); i++) {

                    if (cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineNum().equals(lineNum) //
                            && (!ZYPCommonFunc.hasValue(cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum()) //
                            || (ZYPCommonFunc.hasValue(cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum()) //
                            && cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum().equals(lineSubNum)))) {

                        String svcLineNum = mapLoc.get("SHELL_LINE_NUM").toString();
                        String msgParam = getErrMsgForChkCustMaintPoReq(svcLineNum);

                        // 2017/08/10 S21_NA#19970 Add Start
                        // setErrToDiRsltBean(cpoBean,
                        // cpoBean.getCpoDtlBeanList().get(i),
                        // diChkBean.getDiChckCd(),
                        // diChkBean.getDiChkLvlCd(),
                        // diChkBean.getDiChkRsltTpCd(), msgParam);
                        BigDecimal dsCpoConfigPk = (BigDecimal) mapLoc.get("DS_CPO_CONFIG_PK");
                        String confCatgCd = mapLoc.get("CONFIG_CATG_CD").toString();
                        String dsOrdPosnNum = mapLoc.get("DS_ORD_POSN_NUM").toString();

                        setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, dsCpoConfigPk, confCatgCd, dsOrdPosnNum);
                        // 2017/08/10 S21_NA#19970 Add End

                        break;
                    }
                }
            }
        }

        if (maintPoMapByLoc.size() <= 0) {
            // get Maintenance PO Num By Account Code
            List<HashMap<String, Object>> maintPoMapByAcct = getMaintPoNumByAcctCd(glblCmpyCd, ordNum);

            // check Po Num By Account Code
            for (Map<String, Object> mapAcct : maintPoMapByAcct) {
                if (ZYPCommonFunc.hasValue((String) mapAcct.get("CUST_PO_NUM"))) {
                    continue;
                }

                // S21_NA#8388 START
                if (isDeclineSvcCofig(glblCmpyCd, ordNum, (String) mapAcct.get("CPO_DTL_LINE_NUM"))) {
                    continue;
                }
                // S21_NA#8388 END

                String lineNum = mapAcct.get("DS_CPO_LINE_NUM").toString();
                String lineSubNum = null;
                if (ZYPCommonFunc.hasValue((BigDecimal) mapAcct.get("DS_CPO_LINE_SUB_NUM"))) {
                    lineSubNum = mapAcct.get("DS_CPO_LINE_SUB_NUM").toString();
                }

                // set Error
                if (!ZYPCommonFunc.hasValue((String) mapAcct.get("CUST_PO_NUM"))) {
                    for (int i = 0; i < cpoBean.getCpoDtlBeanList().size(); i++) {

                        if (cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineNum().equals(lineNum) //
                                && (!ZYPCommonFunc.hasValue(cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum()) //
                                || (ZYPCommonFunc.hasValue(cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum()) //
                                && cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum().equals(lineSubNum)))) {

                            String svcLineNum = mapAcct.get("SHELL_LINE_NUM").toString();
                            String msgParam = getErrMsgForChkCustMaintPoReq(svcLineNum);

                            // 2017/08/10 S21_NA#19970 Add Start
                            // setErrToDiRsltBean(cpoBean,
                            // cpoBean.getCpoDtlBeanList().get(i),
                            // diChkBean.getDiChckCd(),
                            // diChkBean.getDiChkLvlCd(),
                            // diChkBean.getDiChkRsltTpCd(),
                            // msgParam);
                            BigDecimal dsCpoConfigPk = (BigDecimal) mapAcct.get("DS_CPO_CONFIG_PK");
                            String confCatgCd = mapAcct.get("CONFIG_CATG_CD").toString();
                            String dsOrdPosnNum = mapAcct.get("DS_ORD_POSN_NUM").toString();

                            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, dsCpoConfigPk, confCatgCd, dsOrdPosnNum);
                            // 2017/08/10 S21_NA#19970 Add End

                            break;
                        }
                    }
                }
            }
        }

        // check Po Num By Location
        // for (Map<String, Object> mapLoc : maintPoMapByLoc) {
        // if (ZYPCommonFunc.hasValue((String)
        // mapLoc.get("CUST_ISS_PO_NUM"))) {
        // continue;
        // }
        //
        // //S21_NA#8388 START
        // if (isDeclineSvcCofig(glblCmpyCd, ordNum, (String)
        // mapLoc.get("CPO_DTL_LINE_NUM"))) {
        // continue;
        // }
        // //S21_NA#8388 END
        //
        // String lineNumByLoc =
        // mapLoc.get("DS_CPO_LINE_NUM").toString();
        // String subLineNumByLoc = null;
        // if (ZYPCommonFunc.hasValue((BigDecimal)
        // mapLoc.get("DS_CPO_LINE_SUB_NUM"))) {
        // subLineNumByLoc =
        // mapLoc.get("DS_CPO_LINE_SUB_NUM").toString();
        // }
        //
        // // check Po Num By Account Code
        // for (Map<String, Object> mapAcct : maintPoMapByAcct) {
        //
        // String lineNumByAcct =
        // mapAcct.get("DS_CPO_LINE_NUM").toString();
        // String subLineNumByAcct = null;
        // if (ZYPCommonFunc.hasValue((BigDecimal)
        // mapAcct.get("DS_CPO_LINE_SUB_NUM"))) {
        // subLineNumByAcct =
        // mapAcct.get("DS_CPO_LINE_SUB_NUM").toString();
        // }
        //
        // if (!lineNumByLoc.equals(lineNumByAcct) &&
        // !subLineNumByLoc.equals(subLineNumByAcct)) {
        // continue;
        // }
        //
        // // set Error
        // if (!ZYPCommonFunc.hasValue((String)
        // mapAcct.get("CUST_ISS_PO_NUM"))) {
        // for (int i = 0; i < cpoBean.getCpoDtlBeanList().size();
        // i++) {
        // if
        // (lineNumByLoc.equals(cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineNum())
        // &&
        // subLineNumByLoc.equals(cpoBean.getCpoDtlBeanList().get(i).getDsCpoLineSubNum()))
        // {
        // String msgParam = getErrMsgForChkCustMaintPoReq((String)
        // mapLoc.get("CUST_ISS_PO_NUM"));
        // setErrToDiRsltBean(cpoBean,
        // cpoBean.getCpoDtlBeanList().get(i),
        // diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(),
        // diChkBean.getDiChkRsltTpCd(), msgParam);
        // break;
        // }
        // }
        // break;
        // }
        // }
        // }
        // Mod End 2016/12/21 M.Ohno S21_NA#16652
    }

    /**
     * getErrMsgForChkCustMaintPoReq
     * @param lineNum String
     * @return String
     */
    private String getErrMsgForChkCustMaintPoReq(String lineNum) {
        StringBuilder msgParam = new StringBuilder();

        // START 2022/10/24 T.Doi [QC#54883, MOD]
        // msgParam.append("Customer Maintenance PO on the service shell page (");
        msgParam.append("Customer Maintenance PO on the service shell# (");
        // END 2022/10/24 T.Doi [QC#54883, MOD]
        msgParam.append(lineNum);
        msgParam.append(") cannot be NULL");

        return msgParam.toString();
    }

    /**
     * getMaintPoNumByLocCd
     * @param glblCmpyCd String
     * @param ordNum String
     * @return List<Map>
     */
    private List<Map<String, Object>> getMaintPoNumByLocCd(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("contractCd", DS_TRX_RULE_TP.CONTRACT);
        // QC#19984 2017/07/19 Add Start
        ssmParam.put("warranty", DS_CONTR_CATG.WARRANTY);
        // QC#19984 2017/07/19 Add E n d
        ssmParam.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getMaintPoNumByLocCd", ssmParam);
    }

    /**
     * getPoNumByAcctCd
     * @param glblCmpyCd String
     * @param ordNum String
     * @return List<Map>
     */
    private List<HashMap<String, Object>> getMaintPoNumByAcctCd(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("contractCd", DS_TRX_RULE_TP.CONTRACT);
        // QC#19984 2017/07/19 Add Start
        ssmParam.put("warranty", DS_CONTR_CATG.WARRANTY);
        // QC#19984 2017/07/19 Add E n d
        ssmParam.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        return (List<HashMap<String, Object>>) ssmClient.queryObjectList("getMaintPoNumByAcctCd", ssmParam);
    }

    /**
     * checkLeaseEqupimentPoRequired
     * 
     * <pre>
     * DI Check Code: E099-1010A
     * Lease Equipment PO Required
     * Set Error if required Lease Equipment PO is not input
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkLeaseEqupimentPoRequired(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String ordNum = cpoBean.getCpoOrdNum();
        String leaseCmpyPoNum = null;

        // S21_NA#11552 START
        if (isAllLineCredit(glblCmpyCd, ordNum)) {
            return;
        }
        // S21_NA#11552 END

        // get Lease Company PO By Location Code
        Map resMap = getLeasePoNumByLocCd(glblCmpyCd, ordNum);

        if (resMap != null && resMap.size() > 0) {
            leaseCmpyPoNum = (String) resMap.get("LEASE_CMPY_PO_NUM");
            if (ZYPCommonFunc.hasValue(leaseCmpyPoNum)) {
                cpoBean.setLeaseCmpyPoNum(leaseCmpyPoNum);
                return;
            } else {
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Lease Equipment PO on the order#  ").append(ordNum);
                msgParam.append(" cannot be NULL on the order header as it is billed to CFS");
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                return;
            }
        }

        // get Lease Company PO By Account Code
        resMap = getLeasePoNumByAcctCd(glblCmpyCd, ordNum);

        if (resMap != null && resMap.size() > 0) {
            leaseCmpyPoNum = (String) resMap.get("LEASE_CMPY_PO_NUM");
            if (ZYPCommonFunc.hasValue(leaseCmpyPoNum)) {
                cpoBean.setLeaseCmpyPoNum(leaseCmpyPoNum);
                return;
            } else {
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Lease Equipment PO on the order#  ").append(ordNum);
                msgParam.append(" cannot be NULL on the order header as it is billed to CFS");
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                return;
            }
        }
    }

    /**
     * getLeasePoNumByLocCd
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private Map getLeasePoNumByLocCd(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (Map) ssmClient.queryObject("getLeasePoNumByLocCd", ssmParam);
    }

    /**
     * getLeasePoNumByAcctCd
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private Map getLeasePoNumByAcctCd(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (Map) ssmClient.queryObject("getLeasePoNumByAcctCd", ssmParam);
    }

    /**
     * checkLeasePoFormat
     * 
     * <pre>
     * DI Check Code: E099-1010B
     * Lease Equipment PO Format
     * set error if format of Lease Equipment PO is not 9999/9999
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkLeasePoFormat(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String ordNum = cpoBean.getCpoOrdNum();
        String leaseCmpyPoNum = null;

        // S21_NA#11552 START
        if (isAllLineCredit(glblCmpyCd, ordNum)) {
            return;
        }
        // S21_NA#11552 END

        // get Lease Equipment PO by Location Code
        Map resMapByLocCd = getLeasePoNumByLocCd(glblCmpyCd, ordNum);

        if (resMapByLocCd != null && resMapByLocCd.size() > 0) {
            leaseCmpyPoNum = (String) resMapByLocCd.get("LEASE_CMPY_PO_NUM");

            // if has Lease Company PO, check format
            if (ZYPCommonFunc.hasValue(leaseCmpyPoNum)) {
                cpoBean.setLeaseCmpyPoNum(leaseCmpyPoNum);

                // set error if Lease Company PO unmatches the Format
                if (!leaseCmpyPoNum.matches(LEASE_PO_FORMAT)) {
                    String msgParam = "Lease Equipment PO should be in the format 'Application Number / PO Number'";
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                    return;
                }
            }
        }

        // get Lease Equipment PO by Account Code
        Map resMapByAcct = getLeasePoNumByAcctCd(glblCmpyCd, ordNum);

        if (resMapByAcct != null && resMapByAcct.size() > 0) {
            leaseCmpyPoNum = (String) resMapByAcct.get("LEASE_CMPY_PO_NUM");

            // if has Lease Company PO, check format
            if (ZYPCommonFunc.hasValue(leaseCmpyPoNum)) {
                cpoBean.setLeaseCmpyPoNum(leaseCmpyPoNum);

                // set error if Lease Company PO unmatches the Format
                if (!leaseCmpyPoNum.matches(LEASE_PO_FORMAT)) {
                    String msgParam = "Lease Equipment PO should be in the format 'Application Number / PO Number'";
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                    return;
                }
            }
        }
    }

    /**
     * checkDuplicatePo
     * 
     * <pre>
     * DI Check Code: E099-1015
     * Duplicate PO Entered
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkDuplicatePo(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        // S21_NA#11552 START
        if (isAllLineCredit(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum())) {
            return;
        }
        // S21_NA#11552 END

        // S21_NA#21594 START
        if (isAllLineRebill(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum())) {
            return;
        }
        // S21_NA#21594 START

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("ordNum", cpoBean.getCpoOrdNum());
        ssmParam.put("ordHdrStsCd", ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordLineStsCancelled", ORD_LINE_STS.CANCELLED);

        Map<String, Object> duplicatePoMap = null;
        // check duplicated customer PO Number
        duplicatePoMap = (Map<String, Object>) ssmClient.queryObject("getDuplicateOrd", ssmParam);
        if (duplicatePoMap != null && !duplicatePoMap.isEmpty()) {
            String msgParam = getDuplicatePoErrMsg(duplicatePoMap);
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            return;
        }

        // S21_NA#8388 MOD START
        if (isDeclineSvc(cpoBean)) {
            return;
        }
        // S21_NA#8388 MOD END

        // S21_NA#8388 MOD START
        // check duplicated maintenance PO Number
        List<Map<String, Object>> duplicatePoMapList //
        = (List<Map<String, Object>>) ssmClient.queryObjectList("getDuplicateMaintPo", ssmParam);
        if (duplicatePoMapList != null) {
            for (Map<String, Object> map : duplicatePoMapList) {
                if (isDeclineSvcCofig(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum(), (String) map.get("CPO_DTL_LINE_NUM"))) {
                    continue;
                }
                if (map != null && !map.isEmpty()) {
                    String msgParam = getDuplicatePoErrMsg(map);
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                    return; // QC#28370 2018/09/28 Add
                }
            }
        }
        // S21_NA#8388 MOD END

        // check duplicated Lease company PO Number
        duplicatePoMap = (Map<String, Object>) ssmClient.queryObject("getDuplicateLeasePo", ssmParam);
        if (duplicatePoMap != null && !duplicatePoMap.isEmpty()) {
            String msgParam = getDuplicatePoErrMsg(duplicatePoMap);
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
        }
    }

    /**
     * getDuplicatePoErrMsg
     * @param duplicatePoMap Map<String, Object>
     * @return String
     */
    public String getDuplicatePoErrMsg(Map<String, Object> duplicatePoMap) {
        String ordNum = (String) duplicatePoMap.get("CPO_ORD_NUM");
        String poNum = (String) duplicatePoMap.get("PO_NUM");
        String billToCust = (String) duplicatePoMap.get("BILL_TO_CUST_CD");

        StringBuilder msgParam = new StringBuilder();
        msgParam.append("The combination of '").append(poNum);
        msgParam.append("' and '").append(billToCust);
        msgParam.append("' is referenced by another order ").append(ordNum);

        return msgParam.toString();
    }

    /**
     * checkCusomertControlField
     * 
     * <pre>
     * DI Check Code: E099-1020
     * A Mandatory Customer Control Fields
     * B Optional Customer Control Fields
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkCusomertControlField(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("ordNum", cpoBean.getCpoOrdNum());
        ssmParam.put("coaMdseTpCd", COA_PROJ.MACHINE);

        List<Map> custCtrlFieldMap = (List<Map>) ssmClient.queryObjectList("getCustCtrlField", ssmParam);

        for (int mapIdx = 0; mapIdx < custCtrlFieldMap.size(); mapIdx++) {
            Map map = custCtrlFieldMap.get(mapIdx);

            String lineNum = map.get("DS_CPO_LINE_NUM").toString();
            String lineSubNum = null;
            if (ZYPCommonFunc.hasValue((BigDecimal) map.get("DS_CPO_LINE_SUB_NUM"))) {
                lineSubNum = map.get("DS_CPO_LINE_SUB_NUM").toString();
            }
            String shipToLocCd = (String) map.get("SHIP_TO_CUST_CD");
            String attrbNm = (String) map.get("BLLG_ATTRB_NM");
            String attrbReqFlg = (String) map.get("BLLG_ATTRB_REQ_FLG");

            // If Attribute is required, check Warning. Or else, check
            // Error.
            if (ZYPConstant.FLG_ON_Y.equals(attrbReqFlg)) {
                if (E099_1020B.equals(diChkBean.getDiChckCd())) {
                    continue;
                }
            } else {
                if (E099_1020A.equals(diChkBean.getDiChckCd())) {
                    continue;
                }
            }

            for (int dtlIdx = 0; dtlIdx < cpoBean.getCpoDtlBeanList().size(); dtlIdx++) {
                NWZC155001CpoDtlBean cpoDtlBean = cpoBean.getCpoDtlBeanList().get(dtlIdx);
                if (!CONFIG_CATG.OUTBOUND.equals(cpoDtlBean.getConfigCatgCd())) {
                    continue;
                }

                if (cpoDtlBean.getDsCpoLineNum().equals(lineNum)
                        && (!ZYPCommonFunc.hasValue(cpoDtlBean.getDsCpoLineSubNum()) || (ZYPCommonFunc.hasValue(cpoDtlBean.getDsCpoLineSubNum()) && cpoDtlBean.getDsCpoLineSubNum().equals(lineSubNum)))) {
                    String msgParam = null;

                    // First Billing Attribute
                    if (ZYPCommonFunc.hasValue(cpoDtlBean.getFirstBllgAttrbNm())) {
                        if (cpoDtlBean.getFirstBllgAttrbNm().equals(attrbNm) && !ZYPCommonFunc.hasValue(cpoDtlBean.getFirstBllgAttrbValTxt())) {
                            msgParam = getCustCtrlFieldErrMsg(attrbReqFlg, attrbNm, lineNum, shipToLocCd);
                            setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                            break;
                        }
                    }

                    // Second Billing Attribute
                    if (ZYPCommonFunc.hasValue(cpoDtlBean.getScdBllgAttrbNm())) {
                        if (cpoDtlBean.getScdBllgAttrbNm().equals(attrbNm) && !ZYPCommonFunc.hasValue(cpoDtlBean.getScdBllgAttrbValTxt())) {
                            msgParam = getCustCtrlFieldErrMsg(attrbReqFlg, attrbNm, lineNum, shipToLocCd);
                            setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                            break;
                        }
                    }

                    // Third Billing Attribute
                    if (ZYPCommonFunc.hasValue(cpoDtlBean.getThirdBllgAttrbNm())) {
                        if (cpoDtlBean.getThirdBllgAttrbNm().equals(attrbNm) && !ZYPCommonFunc.hasValue(cpoDtlBean.getThirdBllgAttrbValTxt())) {
                            msgParam = getCustCtrlFieldErrMsg(attrbReqFlg, attrbNm, lineNum, shipToLocCd);
                            setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                            break;
                        }
                    }

                    // Fourth Billing Attribute
                    if (ZYPCommonFunc.hasValue(cpoDtlBean.getFrthBllgAttrbNm())) {
                        if (cpoDtlBean.getFrthBllgAttrbNm().equals(attrbNm) && !ZYPCommonFunc.hasValue(cpoDtlBean.getFrthBllgAttrbValTxt())) {
                            msgParam = getCustCtrlFieldErrMsg(attrbReqFlg, attrbNm, lineNum, shipToLocCd);
                            setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                            break;
                        }
                    }

                    // Fifth Billing Attribute
                    if (ZYPCommonFunc.hasValue(cpoDtlBean.getFifthBllgAttrbNm())) {
                        if (cpoDtlBean.getFifthBllgAttrbNm().equals(attrbNm) && !ZYPCommonFunc.hasValue(cpoDtlBean.getFifthBllgAttrbValTxt())) {
                            msgParam = getCustCtrlFieldErrMsg(attrbReqFlg, attrbNm, lineNum, shipToLocCd);
                            setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * getCustCtrlFieldErrMsg
     * @param attrbReqFlg Boolean
     * @param attrbNm String
     * @param lineNum String
     * @param shipToLocCd String
     * @return String
     */
    public String getCustCtrlFieldErrMsg(String attrbReqFlg, String attrbNm, String lineNum, String shipToLocCd) {
        StringBuilder msgParam = new StringBuilder();

        msgParam.append("The customer control field ('").append(attrbNm);
        if (ZYPConstant.FLG_ON_Y.equals(attrbReqFlg)) {
            msgParam.append("') cannot be NULL on the line# ").append(lineNum);
        } else {
            msgParam.append("') is NULL on the line# ").append(lineNum);
        }
        msgParam.append("for the ship to location# ").append(shipToLocCd);

        return msgParam.toString();
    }

    /**
     * checkServiceShellDetailRequire
     * 
     * <pre>
     * DI Check Code: E099-1025
     * Service Shell Details Required
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkServiceShellDetailRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        // S21_NA#8388 START
        if (isDeclineSvc(cpoBean)) {
            return;
        }
        // S21_NA#8388 END

        // get order line which requires to input service shell
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        // QC#7123 Add Start
        key.put("cpoDtlLineSubNumSet", CPO_DTL_LINE_SUB_NUM_SET);
        // QC#7123 Add End
        // QC#16603 2016/12/14 Mod Start
        // key.put("mdseTpCtxTpCd", E099_1025_MERCH_TYPE);
        // QC#20039 2017/07/21 Del Start
        // key.put("mdseTpCtxTpCd", CPO_SVC_CONFIG_ITEMS);
        // QC#20039 2017/07/21 Del End
        // QC#16603 2016/12/14 Mod End
        key.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SERVICE_SHELL_REQUIRED_ORDERS);
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
        key.put("rtlWhCd", RTL_WH_BILL_ONLY); // 2019/11/22 QC#54213
        // Add

        List<Map<String, Object>> lineReqShellMapList = (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdLineRequireShell", key);

        // return when it has no result
        // S21_NA#19920 2017/07/25 Mod Start
        // if (lineReqShellMapList == null ||
        // lineReqShellMapList.isEmpty()) {
        if (lineReqShellMapList == null) {
            // S21_NA#19920 2017/07/25 Mod End
            return;
        }

        ArrayList<String> errFieldList = new ArrayList<String>();
        for (int mapIdx = 0; mapIdx < lineReqShellMapList.size(); mapIdx++) {

            // S21_NA#8388 START
            if (isDeclineSvc((String) lineReqShellMapList.get(mapIdx).get("DCLN_SVC_CD"))) {
                continue;
            }
            // S21_NA#8388 END

            errFieldList.clear();

            // check exists of required service shell line
            if (!checkExistRequiredShellLine(cpoBean, lineReqShellMapList.get(mapIdx), errFieldList, diChkBean)) {
                continue;
            }

            // check input of required shell field item
            // checkInputRequiredShellField(cpoBean,
            // lineReqShellMapList.get(mapIdx), errFieldList,
            // diChkBean); // QC#16603 2016/12/14 Del
        }

        // QC#16603 2016/12/14 Add Start
        key.put("dsContrCratTpCd", DS_CONTR_CRAT_TP.SHELL); // QC#30573
        // 2019/03/06
        List<Map<String, Object>> lineReqShellFieldMapList //
        = (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdLineRequireFieldShell", key);
        // 2018/05/13 S21_NA#22482 Add Start
        Map<BigDecimal, Boolean> dsContrPkMap = new HashMap<BigDecimal, Boolean>();
        Map<String, BigDecimal> seqCheckMap = new HashMap<String, BigDecimal>();
        String continuityErrorTxt = "There is an error in the continuity of the term.";
        // 2018/05/13 S21_NA#22482 Add End
        for (int mapIdx = 0; mapIdx < lineReqShellFieldMapList.size(); mapIdx++) {

            // 2018/05/13 S21_NA#22482 Add Start
            BigDecimal fromPerMthNum = (BigDecimal) lineReqShellFieldMapList.get(mapIdx).get("FROM_PER_MTH_NUM");
            BigDecimal toPerMthNum = (BigDecimal) lineReqShellFieldMapList.get(mapIdx).get("TO_PER_MTH_NUM");
            String shellLineNum = "SHELL#" + ((BigDecimal) lineReqShellFieldMapList.get(mapIdx).get("SHELL_LINE_NUM")).toString();
            String dsOrdPosnNum = (String) lineReqShellFieldMapList.get(mapIdx).get("DS_ORD_POSN_NUM");
            BigDecimal dsContrDtlPk = (BigDecimal) lineReqShellFieldMapList.get(mapIdx).get("DS_CONTR_DTL_PK");
            // 2018/05/13 S21_NA#22482 Add End

            errFieldList.clear();
            if (isDeclineSvc((String) lineReqShellFieldMapList.get(mapIdx).get("DCLN_SVC_CD"))) {
                continue;
            }

            // 2018/05/16 S21_NA#21744 Add Start
            if (!ZYPCommonFunc.hasValue(dsOrdPosnNum) && ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                continue;
            }
            // 2018/05/16 S21_NA#21744 Add End

            // 2018/05/13 S21_NA#22482 Mod Start
            // checkInputRequiredShellField(cpoBean,
            // lineReqShellFieldMapList.get(mapIdx), errFieldList,
            // diChkBean);// QC#16603
            checkInputRequiredShellField(cpoBean, lineReqShellFieldMapList.get(mapIdx), errFieldList, diChkBean, dsContrPkMap);
            // 2018/05/13 S21_NA#22482 Mod End

            // 2018/05/13 S21_NA#22482 Add Start
            if (ZYPCommonFunc.hasValue(dsOrdPosnNum) && ZYPCommonFunc.hasValue(fromPerMthNum) && ZYPCommonFunc.hasValue(toPerMthNum)) {
                if (!seqCheckMap.containsKey(dsOrdPosnNum)) {
                    if (BigDecimal.ONE.compareTo(fromPerMthNum) != 0) {
                        setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), //
                                diChkBean.getDiChkRsltTpCd(), continuityErrorTxt + shellLineNum);
                    } else {
                        seqCheckMap.put(dsOrdPosnNum, toPerMthNum.add(BigDecimal.ONE));
                    }
                } else {
                    if (seqCheckMap.get(dsOrdPosnNum).compareTo(fromPerMthNum) != 0) {
                        setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), //
                                diChkBean.getDiChkRsltTpCd(), continuityErrorTxt + shellLineNum);
                    } else {
                        seqCheckMap.put(dsOrdPosnNum, toPerMthNum.add(BigDecimal.ONE));
                    }
                }
            }

            if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                String missingErrTxt = "Missing Config." + shellLineNum;
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), //
                        diChkBean.getDiChkRsltTpCd(), missingErrTxt);
            }
            // 2018/05/13 S21_NA#22482 Add End
        }
        // QC#16603 2016/12/14 Add End
        // QC#24245 2018/06/13 Add Start
        key.put("configTp", CONFIG_TP.ADD_TO_CONFIG);
        key.put("addContrFlg", ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> lineReqAddAccShellMapList //
        = (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdLineForAddAccShell", key);
        for (Map<String, Object> lineReqAddAccShellMap : lineReqAddAccShellMapList) {
            if (isDeclineSvc((String) lineReqAddAccShellMap.get("DCLN_SVC_CD"))) {
                continue;
            }
            if (!CONFIG_CATG.OUTBOUND.equals((String) lineReqAddAccShellMap.get("CONFIG_CATG_CD"))) {
                continue;
            }
            // check exists of required service shell line
            BigDecimal dsCpoConfigPk = (BigDecimal) lineReqAddAccShellMap.get("DS_CPO_CONFIG_PK");
            String posnNum = (String) lineReqAddAccShellMap.get("DS_ORD_POSN_NUM");
            String catgCd = (String) lineReqAddAccShellMap.get("CONFIG_CATG_CD");
            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), MSG_ADD_ACCESSORY_SHELL_REQUEST, dsCpoConfigPk, catgCd, posnNum);
        }
        // QC#24245 2018/06/13 Add End
    }

    // 2019/11/22 QC#54213 Add Start
    /**
     * checkServiceShellDetailNoRequire
     * 
     * <pre>
     * DI Check Code: E099-1310
     * Service Shell Details No Required
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkServiceShellDetailNoRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        // get order line which requires to input service shell
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("cpoDtlLineSubNumSet", CPO_DTL_LINE_SUB_NUM_SET);
        key.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SERVICE_SHELL_REQUIRED_ORDERS);
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED });
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED });
        key.put("rtlWhCd", RTL_WH_BILL_ONLY);

        List<Map<String, Object>> lineReqShellMapList = (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdLineNoRequireShell", key);

        // return when it has no result
        if (lineReqShellMapList == null) {
            return;
        }

        ArrayList<String> errFieldList = new ArrayList<String>();
        for (int mapIdx = 0; mapIdx < lineReqShellMapList.size(); mapIdx++) {
            errFieldList.clear();
            // check exists of required service shell line
            if (!checkExistRequiredShellLine(cpoBean, lineReqShellMapList.get(mapIdx), errFieldList, diChkBean)) {
                continue;
            }
        }
    }

    // 2019/11/22 QC#54213 Add End

    /**
     * checkRentalShellDetailRequire
     * 
     * <pre>
     * DI Check Code: E099-1030
     * Rental Shell Details Required
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkRentalShellDetailRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        // S21_NA#8388 START
        if (isDeclineSvc(cpoBean)) {
            return;
        }
        // S21_NA#8388 END

        // get order line which requires to input rental shell
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        // QC#7123 Add Start
        key.put("cpoDtlLineSubNumSet", CPO_DTL_LINE_SUB_NUM_SET);
        // QC#7123 Add End
        // QC#16603 2016/12/14 Mod Start
        // key.put("mdseTpCtxTpCd", E099_1030_MERCH_TYPE);
        key.put("mdseTpCtxTpCdAddlBase", CPO_SVC_ADDL_BASE_ITEMS);
        key.put("mdseTpCtxTpCdConfig", CPO_SVC_CONFIG_ITEMS);
        // QC#16603 2016/12/14 Mod End
        key.put("ordCatgCtxTp", RENTAL_SHELL_REQUIRED);
        // ADD START 2016/09/02 S21_NA#13813
        key.put("RentalCatgCd", SVC_PRC_CATG.RENTAL_EQUIPMENT_BASE_CHARGE);
        // Get List< Map<SVC_CONFIG_MSTR_PK, CPO_SVC_DTL_PK >>
        // Add Start 2017/06/12 QC#18549
        key.put("svcPrcCatgCd", SVC_PRC_CATG.MAIN_UNIT_BASE_CHARGE);
        // Add End 2017/06/12 QC#18549
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        List<Map<String, BigDecimal>> configSvcDtlMapList = (List<Map<String, BigDecimal>>) ssmClient.queryObjectList("getRentalShellDtlFormOrdConf", key);

        // return when it has no result
        if (configSvcDtlMapList == null || configSvcDtlMapList.isEmpty()) {
            return;
        }

        // Map <key:Service Detail, Value:Config PK>
        Map<String, List<String>> dtlConfigMap = new HashMap<String, List<String>>();
        for (Map<String, BigDecimal> configSvcDtl : configSvcDtlMapList) {
            BigDecimal cntDtlPk = configSvcDtl.get("DS_CONTR_DTL_PK");
            BigDecimal configMstrPk = configSvcDtl.get("SVC_CONFIG_MSTR_PK");

            if (!ZYPCommonFunc.hasValue(cntDtlPk)) {
                break;
            }
            String cntDtlPkStr = cntDtlPk.toPlainString();
            if (dtlConfigMap.containsKey(cntDtlPkStr)) {
                List<String> confMstrpkList = dtlConfigMap.get(cntDtlPkStr);
                confMstrpkList.add(configMstrPk.toPlainString());
            } else {
                List<String> confMstrpkList = new ArrayList<String>();
                confMstrpkList.add(configMstrPk.toPlainString());
                dtlConfigMap.put(cntDtlPkStr, confMstrpkList);
            }
        }
        Set<String> keySet = dtlConfigMap.keySet();
        for (String MapKey : keySet) {
            List<String> configPkList = dtlConfigMap.get(MapKey);

            key.put("cntDtlPk", MapKey);
            key.put("configMstrPkList", configPkList);

            // ADD END 2016/09/02 S21_NA#13813

            List<Map> lineReqShellMapList = (List<Map>) ssmClient.queryObjectList("getOrdLineRequireForRentalShell", key);

            // return when it has no result
            if (lineReqShellMapList == null || lineReqShellMapList.isEmpty()) {
                // return;
                // Mod Start 2017/06/09 QC#18549
                // break;
                continue;
                // Mod End 2017/06/09 QC#18549
            }

            ArrayList<String> errFieldList = new ArrayList<String>();
            for (int mapIdx = 0; mapIdx < lineReqShellMapList.size(); mapIdx++) {

                // S21_NA#8388 START
                if (isDeclineSvc((String) lineReqShellMapList.get(mapIdx).get("DCLN_SVC_CD"))) {
                    continue;
                }
                // S21_NA#8388 END

                errFieldList.clear();

                // check exists of required rental shell line
                if (!checkExistRequiredShellLine(cpoBean, lineReqShellMapList.get(mapIdx), errFieldList, diChkBean)) {
                    continue;
                }

                // check input of required shell field item
                // 2018/05/13 S21_NA#22482 Mod Start
                // checkInputRequiredShellField(cpoBean,
                // lineReqShellMapList.get(mapIdx), errFieldList,
                // diChkBean);
                checkInputRequiredShellField(cpoBean, lineReqShellMapList.get(mapIdx), errFieldList, diChkBean, null);
                // 2018/05/13 S21_NA#22482 Mod End
            }

        } // ADD 2016/09/02 S21_NA#13813
        // QC#24245 2018/06/13 Add Start
        key.put("configTp", CONFIG_TP.ADD_TO_CONFIG);
        key.put("addContrFlg", ZYPConstant.FLG_ON_Y);
        List<Map<String, Object>> lineReqAddAccShellMapList = (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdLineForAddAccShell", key);
        for (Map<String, Object> lineReqAddAccShellMap : lineReqAddAccShellMapList) {
            if (isDeclineSvc((String) lineReqAddAccShellMap.get("DCLN_SVC_CD"))) {
                continue;
            }
            if (!CONFIG_CATG.OUTBOUND.equals((String) lineReqAddAccShellMap.get("CONFIG_CATG_CD"))) {
                continue;
            }
            // check exists of required service shell line
            BigDecimal dsCpoConfigPk = (BigDecimal) lineReqAddAccShellMap.get("DS_CPO_CONFIG_PK");
            String posnNum = (String) lineReqAddAccShellMap.get("DS_ORD_POSN_NUM");
            String catgCd = (String) lineReqAddAccShellMap.get("CONFIG_CATG_CD");
            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), MSG_ADD_ACCESSORY_SHELL_REQUEST, dsCpoConfigPk, catgCd, posnNum);
        }
        // QC#24245 2018/06/13 Add End

        // QC#14940
        for (String MapKey : keySet) {
            key.put("cntDtlPk", MapKey);

            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmClient.queryObjectList("getRentalShellCheckForIbControl", key);
            if (rsltList == null || rsltList.isEmpty()) {
                continue;
            }

            for (Map<String, Object> rslt : rsltList) {
                NWZC155001CpoDtlBean cpoDtlBean = new NWZC155001CpoDtlBean();
                cpoDtlBean.setDsCpoConfigPk((BigDecimal) rslt.get("DS_CPO_CONFIG_PK"));
                cpoDtlBean.setConfigCatgCd(CONFIG_CATG.OUTBOUND);
                cpoDtlBean.setDsOrdPosnNum((String) rslt.get("DS_ORD_POSN_NUM"));
                cpoDtlBean.setCpoOrdNum((String) rslt.get("CPO_ORD_NUM"));
                BigDecimal dsCpoLineNum = (BigDecimal) rslt.get("DS_CPO_LINE_NUM");
                if (ZYPCommonFunc.hasValue(dsCpoLineNum)) {
                    cpoDtlBean.setDsCpoLineNum(dsCpoLineNum.toString());
                }
                BigDecimal dsCpoLineSubNum = (BigDecimal) rslt.get("DS_CPO_LINE_SUB_NUM");
                if (ZYPCommonFunc.hasValue(dsCpoLineSubNum)) {
                    cpoDtlBean.setDsCpoLineSubNum(dsCpoLineSubNum.toString());
                }

                setErrToDiRsltBean(//
                        cpoBean //
                        , cpoDtlBean //
                        , diChkBean.getDiChckCd() //
                        , diChkBean.getDiChkLvlCd() //
                        , diChkBean.getDiChkRsltTpCd() //
                        , RENTAL_SHELL_IB_CONTROL_MSGPARAM);
            }
        }
    }

    /**
     * checkExistRequiredShellLine
     * @param cpoBean NWZC155001CpoBean
     * @param shellRequirdDtlMap Map
     * @param errFieldList ArrayList<String>
     * @param diChkBean NWZC155001DiChkBean
     * @return boolean
     */
    public boolean checkExistRequiredShellLine(NWZC155001CpoBean cpoBean, Map lineRequiresShellMap, ArrayList<String> errFieldList, NWZC155001DiChkBean diChkBean) {

        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
            String dsOrdPosnNum_1 = cpoDtlBean.getDsOrdPosnNum() == null ? "" : cpoDtlBean.getDsOrdPosnNum();
            String dsCpoLineNum_1 = cpoDtlBean.getDsCpoLineNum() == null ? "" : cpoDtlBean.getDsCpoLineNum();
            String dsCpoLineSubNum_1 = cpoDtlBean.getDsCpoLineSubNum() == null ? "" : cpoDtlBean.getDsCpoLineSubNum();
            String dsOrdPosnNum_2 = lineRequiresShellMap.get("DS_ORD_POSN_NUM") == null ? "" : lineRequiresShellMap.get("DS_ORD_POSN_NUM").toString();
            String dsCpoLineNum_2 = lineRequiresShellMap.get("DS_CPO_LINE_NUM") == null ? "" : lineRequiresShellMap.get("DS_CPO_LINE_NUM").toString();
            String dsCpoLineSubNum_2 = lineRequiresShellMap.get("DS_CPO_LINE_SUB_NUM") == null ? "" : lineRequiresShellMap.get("DS_CPO_LINE_SUB_NUM").toString();

            // 03/01/2016 QC4692 S --
            if (dsCpoLineNum_1.equals(dsCpoLineNum_2) && dsCpoLineSubNum_1.equals(dsCpoLineSubNum_2) && dsOrdPosnNum_1.equals(dsOrdPosnNum_2)
            // if (cpoDtlBean.getDsCpoLineNum().equals((String)
                    // lineRequiresShellMap.get("CPO_DTL_LINE_NUM").toString())
                    // &&
                    // cpoDtlBean.getDsCpoLineSubNum().equals((String)
                    // lineRequiresShellMap.get("CPO_DTL_LINE_SUB_NUM"))
                    && cpoDtlBean.getConfigCatgCd().equals(CONFIG_CATG.OUTBOUND)) {
                // 03/01/2016 QC4692 E --

                // set error
                // 03/01/2016 QC4692 S --
                // setErrToDiRsltBean(cpoBean, null,
                // diChkBean.getDiChckCd(),
                // diChkBean.getDiChkLvlCd(),
                // diChkBean.getDiChkRsltTpCd(),
                // getShellDtlReqErrMsg(errFieldList,
                // diChkBean.getDiChckCd(), null));
                setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), getShellDtlReqErrMsg(errFieldList, diChkBean.getDiChckCd(), null));
                // 03/01/2016 QC4692 E --
                break;
            }
        }
        return false;
    }

    /**
     * checkInputRequiredShellField
     * @param cpoBean NWZC155001CpoBean
     * @param shellRequirdDtlMap Map
     * @param errFieldList ArrayList<String>
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkInputRequiredShellField(NWZC155001CpoBean cpoBean, Map lineRequiresShellMap, ArrayList<String> errFieldList, NWZC155001DiChkBean diChkBean, Map<BigDecimal, Boolean> dsContrPkMap) {

        // set to error list if required shell field is not input
        // 2018/05/13 S21_NA#22482 Add Condition
        if (!(E099_1025.equals(diChkBean.getDiChckCd()) && ZYPConstant.FLG_ON_Y.equals((String) lineRequiresShellMap.get("MAN_CONTR_OVRD_FLG")))) {
            // Shell item
            if (!ZYPCommonFunc.hasValue((String) lineRequiresShellMap.get("SVC_PGM_MDSE_CD"))) {
                errFieldList.add("'Shell Item' ");
            }
            // Contract Type
            if (!ZYPCommonFunc.hasValue((String) lineRequiresShellMap.get("PRC_SVC_CONTR_TP_CD"))) {
                errFieldList.add("'Contract Type' ");
            }
            // Plan Type
            if (!ZYPCommonFunc.hasValue((String) lineRequiresShellMap.get("PRC_SVC_PLN_TP_CD"))) {
                errFieldList.add("'Plan Type' ");
            }
            // Contract Indicator
            if (!ZYPCommonFunc.hasValue((String) lineRequiresShellMap.get("DS_CONTR_CATG_CD"))) {
                errFieldList.add("'Contract Indicator' ");
            }
            // QC#7944
            // Base Bill Cycle
            // if (!ZYPCommonFunc.hasValue((String)
            // lineRequiresShellMap.get("PRC_SVC_PLN_TP_CD"))) {
            if (!ZYPCommonFunc.hasValue((String) lineRequiresShellMap.get("BASE_BLLG_CYCLE_CD"))) {
                errFieldList.add("'Base Bill Cycle' ");
            }
        }
        // QC#7944
        // Usage Bill Cycle
        // if (!ZYPCommonFunc.hasValue((String)
        // lineRequiresShellMap.get("DS_CONTR_CATG_CD"))) {
        // QC#8400
        // if (!ZYPCommonFunc.hasValue((String)
        // lineRequiresShellMap.get("USG_BLLG_CYCLE_CD"))) {
        // errFieldList.add("'Usage Bill Cycle' ");
        // }

        // 2018/05/13 S21_NA#22482 Add Start
        if (E099_1025.equals(diChkBean.getDiChckCd()) && ZYPConstant.FLG_ON_Y.equals((String) lineRequiresShellMap.get("MAN_CONTR_OVRD_FLG"))) {
            BigDecimal dsContrPk = (BigDecimal) lineRequiresShellMap.get("DS_CONTR_PK");
            BigDecimal fromPerMthNum = (BigDecimal) lineRequiresShellMap.get("FROM_PER_MTH_NUM");
            BigDecimal toPerMthNum = (BigDecimal) lineRequiresShellMap.get("TO_PER_MTH_NUM");
            String dsAcctNum = (String) lineRequiresShellMap.get("DS_ACCT_NUM");
            String altPayerCustCd = (String) lineRequiresShellMap.get("ALT_PAYER_CUST_CD");
            String dsContrCatgCd = (String) lineRequiresShellMap.get("DS_CONTR_CATG_CD");
            BigDecimal baseBllgCycleMthAot = (BigDecimal) lineRequiresShellMap.get("BLLG_CYCLE_MTH_AOT");
            BigDecimal usageBllgCycleMthAot = (BigDecimal) lineRequiresShellMap.get("USG_BLLG_CYCLE_MTH_AOT");
            String shellLineNum = "SHELL#" + ((BigDecimal) lineRequiresShellMap.get("SHELL_LINE_NUM")).toString();
            String mtrReadMethCd = (String) lineRequiresShellMap.get("MTR_READ_METH_CD");
            // QC#57285 Mod start
            // String custPoNum = (String)
            // lineRequiresShellMap.get("CUST_PO_NUM");
            // String poDt = (String)
            // lineRequiresShellMap.get("PO_DT");
            // String custIssPoDt = (String)
            // lineRequiresShellMap.get("CUST_ISS_PO_DT");
            // QC#57285 Mod end

            if (!dsContrPkMap.containsKey(dsContrPk)) {
                List<String> errorTxtList = new ArrayList<String>();
                // Term From
                if (!ZYPCommonFunc.hasValue(fromPerMthNum)) {
                    errFieldList.add("Term From");
                }
                // Term To
                if (!ZYPCommonFunc.hasValue(toPerMthNum)) {
                    errFieldList.add("Term To");
                }
                // Customer Code
                if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
                    errFieldList.add("Customer Code");
                } else {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                    params.put("dsAcctNum", dsAcctNum);
                    params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                    Integer cnt = (Integer) ssmClient.queryObject("getDsAcctCustCount", params);
                    if (cnt == 0) {
                        errorTxtList.add("The entered Customer Code '" + dsAcctNum + "' does not exist in Master.");
                    }
                }
                // Bill TOo Code
                if (!ZYPCommonFunc.hasValue(altPayerCustCd)) {
                    errFieldList.add("Bill To Code");
                } else {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                    params.put("billToCustCd", altPayerCustCd);
                    params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
                    Integer cnt = (Integer) ssmClient.queryObject("getBillToCustCount", params);
                    if (cnt == 0) {
                        errorTxtList.add("The entered Bill To Code '" + altPayerCustCd + "' does not exist in Master.");
                    }
                }
                // Contract Indicator
                if (!ZYPCommonFunc.hasValue(dsContrCatgCd)) {
                    errFieldList.add("Contract Indicator");
                }

                // Sell/Bill Relation Check
                if (ZYPCommonFunc.hasValue(dsAcctNum) && ZYPCommonFunc.hasValue(altPayerCustCd)) {
                    if (!NSXC001001ContrValidation.checkAcctBillEligible(cpoBean.getGlblCmpyCd(), cpoBean.getSlsDt(), dsAcctNum, altPayerCustCd, onBatchType)) {
                        errorTxtList.add("The combination of '" + dsAcctNum + "' and '" + altPayerCustCd + "' is incorrect.");
                    }
                    if (callCustInfoGetApiForTransactionMode(cpoBean.getGlblCmpyCd(), dsAcctNum, altPayerCustCd)) {
                        dsContrPkMap.put(dsContrPk, true);
                    }
                }

                // Term From/To Compare Check
                if (ZYPCommonFunc.hasValue(fromPerMthNum) && ZYPCommonFunc.hasValue(toPerMthNum)) {
                    BigDecimal calcTermMthNum = calcTermMthNum(fromPerMthNum, toPerMthNum);
                    if (BigDecimal.ZERO.compareTo(calcTermMthNum) == 0) {
                        errorTxtList.add("The chronological sequence between the dates in the Term From/To field is wrong.");
                    } else {
                        // Term Divide Check
                        if (checkBillCycle(baseBllgCycleMthAot, calcTermMthNum)) {
                            errorTxtList.add("Relation between Term Month and Base Bill Cycle is not correct.");
                        }
                        if (checkBillCycle(usageBllgCycleMthAot, calcTermMthNum)) {
                            errorTxtList.add("Relation between Term Month and Usage Bill Cycle is not correct.");
                        }
                    }
                }
                if (!dsContrPkMap.containsKey(dsContrPk)) {
                    dsContrPkMap.put(dsContrPk, false);
                }

                if (!errorTxtList.isEmpty()) {
                    for (String errorTxt : errorTxtList) {
                        setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), //
                                diChkBean.getDiChkRsltTpCd(), errorTxt + shellLineNum);
                    }
                }
            }

            // PO Info Required Check
            if (dsContrPkMap.containsKey(dsContrPk) && dsContrPkMap.get(dsContrPk)) {
                // QC#57285 Add
                Map<String, Object> key = new HashMap<String, Object>();
                key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                key.put("ordNum", cpoBean.getCpoOrdNum());
                key.put("cpoDtlLineSubNumSet", CPO_DTL_LINE_SUB_NUM_SET);
                key.put("ordCatgCtxTp", ORD_CATG_CTX_TP.SERVICE_SHELL_REQUIRED_ORDERS);
                key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
                key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
                key.put("rtlWhCd", RTL_WH_BILL_ONLY);
                key.put("dsContrCratTpCd", DS_CONTR_CRAT_TP.SHELL);
                List<Map<String, Object>> lineReqShellFieldMapList = (List<Map<String, Object>>) ssmClient.queryObjectList("getDsContrCrCrdPo", key);

                for (int mapIdx = 0; mapIdx < lineReqShellFieldMapList.size(); mapIdx++) {

                    String dsOrdPosnNum = (String) lineReqShellFieldMapList.get(mapIdx).get("DS_ORD_POSN_NUM");
                    BigDecimal dsContrDtlPk = (BigDecimal) lineReqShellFieldMapList.get(mapIdx).get("DS_CONTR_DTL_PK");

                    if (isDeclineSvc((String) lineReqShellFieldMapList.get(mapIdx).get("DCLN_SVC_CD"))) {
                        continue;
                    }

                    // 2018/05/16 S21_NA#21744 Add Start
                    if (!ZYPCommonFunc.hasValue(dsOrdPosnNum) && ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                        continue;
                    }

                    String custPoNum = (String) lineReqShellFieldMapList.get(mapIdx).get("CUST_PO_NUM");
                    String poDt = (String) lineReqShellFieldMapList.get(mapIdx).get("PO_DT");
                    String custIssPoDt = (String) lineReqShellFieldMapList.get(mapIdx).get("CUST_ISS_PO_DT");
                    String contrMachLvl = (String) lineReqShellFieldMapList.get(mapIdx).get("DS_CONTR_MACH_LVL_NUM");

                    // Customer PO Number
                    if (!ZYPCommonFunc.hasValue(custPoNum)) {
                        errFieldList.add("Customer PO Number");
                    }
                    // PO Expr Date
                    if (!ZYPCommonFunc.hasValue(poDt)) {
                        errFieldList.add("PO Expr Date");
                    }
                    // PO Date. Mod QC#57285 CUST_ISS_PO_DT is not set
                    // for the BASE Line, so exclude the check.
                    if (!ZYPCommonFunc.hasValue(custIssPoDt) && !"3".equals(contrMachLvl)) {
                        errFieldList.add("PO Date");
                    }
                }
                // QC#57285 End
            }
        }
        // 2018/05/13 S21_NA#22482 Add End

        // if error list is empty, do nothing
        if (errFieldList.isEmpty()) {
            return;
        }

        boolean setError = false; // 2018/05/14 S21_NA#22482 Add
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {

            // 03/01/2016 QC4692 S --
            String dsCpoLineNum_1 = cpoDtlBean.getDsCpoLineNum() == null ? "" : cpoDtlBean.getDsCpoLineNum();
            String dsCpoLineSubNum_1 = cpoDtlBean.getDsCpoLineSubNum() == null ? "" : cpoDtlBean.getDsCpoLineSubNum();
            String dsCpoLineNum_2 = lineRequiresShellMap.get("DS_CPO_LINE_NUM") == null ? "" : lineRequiresShellMap.get("DS_CPO_LINE_NUM").toString();
            String dsCpoLineSubNum_2 = lineRequiresShellMap.get("DS_CPO_LINE_SUB_NUM") == null ? "" : lineRequiresShellMap.get("DS_CPO_LINE_SUB_NUM").toString();
            // if (cpoDtlBean.getDsCpoLineNum().equals((String)
            // lineRequiresShellMap.get("CPO_DTL_LINE_NUM").toString())
            // && cpoDtlBean.getDsCpoLineSubNum().equals((String)
            // lineRequiresShellMap.get("CPO_DTL_LINE_SUB_NUM"))
            if (dsCpoLineNum_1.equals(dsCpoLineNum_2) //
                    && dsCpoLineSubNum_1.equals(dsCpoLineSubNum_2) //
                    && cpoDtlBean.getConfigCatgCd().equals(CONFIG_CATG.OUTBOUND)) {
                // 03/01/2016 QC4692 E --

                // QC#8400, QC#4692-Reopen
                String cpoSvcLineNum = "";
                BigDecimal iCpoSvcLineNum = (BigDecimal) lineRequiresShellMap.get("SHELL_LINE_NUM");
                if (iCpoSvcLineNum != null) {
                    cpoSvcLineNum = iCpoSvcLineNum.toString();
                }

                // set Error
                // 03/01/2016 QC4692 S --
                // setErrToDiRsltBean(cpoBean, null,
                // diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(),
                // diChkBean.getDiChkRsltTpCd(),
                // getShellDtlReqErrMsg(errFieldList,
                // diChkBean.getDiChckCd(), (String)
                // lineRequiresShellMap
                // .get("CPO_SVC_LINE_NUM")));
                // setErrToDiRsltBean(cpoBean, cpoDtlBean,
                // diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(),
                // diChkBean.getDiChkRsltTpCd(),
                // getShellDtlReqErrMsg(errFieldList,
                // diChkBean.getDiChckCd(), (String)
                // lineRequiresShellMap
                // .get("CPO_SVC_LINE_NUM")));
                // 03/01/2016 QC4692 E --
                // QC#8400, QC#4692-Reopen
                setError = true; // 2018/05/14 S21_NA#22482 Add
                setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), getShellDtlReqErrMsg(errFieldList, diChkBean.getDiChckCd(), cpoSvcLineNum));
                break;
            }
        }
        // 2018/05/14 S21_NA#22482 Add Start
        if (!setError) {
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), //
                    getShellDtlReqErrMsg(errFieldList, diChkBean.getDiChckCd(), ((BigDecimal) lineRequiresShellMap.get("SHELL_LINE_NUM")).toString()));
        }
        // 2018/05/14 S21_NA#22482 Add End
    }

    /**
     * Term From/To Compare Check
     * @param fromPerMthNum
     * @param toPerMthNum
     * @return calcTermMthNum
     */
    private BigDecimal calcTermMthNum(BigDecimal fromPerMthNum, BigDecimal toPerMthNum) {
        if (fromPerMthNum.compareTo(toPerMthNum) > 0) {
            return BigDecimal.ZERO;
        }
        return toPerMthNum.subtract(fromPerMthNum).add(BigDecimal.ONE);
    }

    /**
     * Term Divide Check
     * @param bllgCycleMthAot
     * @param calcTermMthNum
     * @return
     */
    private boolean checkBillCycle(BigDecimal bllgCycleMthAot, BigDecimal calcTermMthNum) {
        if (!ZYPCommonFunc.hasValue(bllgCycleMthAot)) {
            return false;
        }
        BigDecimal rmin = calcTermMthNum.remainder(bllgCycleMthAot);
        if (BigDecimal.ZERO.compareTo(rmin) == 0) {
            return false;
        }
        return true;
    }

    /**
     * PO Require Check
     * @param glblCmpyCd
     * @param dsAcctNum
     * @param billToCode
     * @return
     */
    private boolean callCustInfoGetApiForTransactionMode(String glblCmpyCd, String dsAcctNum, String billToCode) {
        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(pMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.CONTRACT);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, billToCode);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, dsAcctNum);

        new NMZC610001().execute(pMsg, onBatchType);
        if (!S21ApiUtil.isXxMsgId(pMsg)) {
            String dsPoReqFlg = ZYPConstant.FLG_OFF_N;
            for (int ixP = 0; ixP < pMsg.TransactionRuleList.getValidCount(); ixP++) {
                NMZC610001_TransactionRuleListPMsg trPMsg = pMsg.TransactionRuleList.no(ixP);
                dsPoReqFlg = trPMsg.dsPoReqFlg.getValue();
                break;
            }
            if (ZYPConstant.FLG_ON_Y.equals(dsPoReqFlg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getShellDtlReqErrMsg
     * @param fieldNmList ArrayList<String>
     * @param checkMd String
     * @param shellLineNum String
     * @return String
     */
    public String getShellDtlReqErrMsg(ArrayList<String> errFieldList, String checkMd, String shellLineNum) {
        StringBuilder msgParam = new StringBuilder();

        if (errFieldList != null && errFieldList.size() > 0) {
            for (String field : errFieldList) {
                msgParam.append("'").append(field).append("' ,");
            }
            msgParam.setLength(msgParam.length() - 1); // 2018/05/14
            // S21_NA#22482
            // Add

            msgParam.append("cannot be NULL on the ");
            if (E099_1025.equals(checkMd)) {
                msgParam.append("service shell line");
            } else {
                msgParam.append("rental shell line");
            }
            if (ZYPCommonFunc.hasValue(shellLineNum)) {
                msgParam.append("# ").append(shellLineNum);
            }
        } else {
            if (E099_1025.equals(checkMd)) {
                msgParam.append("The service shell does not exist.");
                // 2019/11/22 QC#54213 Add Start
            } else if (E099_1310.equals(checkMd)) {
                msgParam.append("The service shell is not required.");
                // 2019/11/22 QC#54213 Add End
            } else {
                msgParam.append("The rental shell does not exist.");
            }
        }
        return msgParam.toString();
    }

    /**
     * checkTaxExemExpire
     * 
     * <pre>
     * DI Check Code: E099-1035
     * A Tax Exempt Number about to expire
     * B Tax Exempt Number Expired
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkTaxExemExpire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        // get tax exemption expire date by Bill to customer
        Map<String, Object> taxExemMap = getTaxExemByLocNum(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());

        // Check tax exemption expire date
        if (taxExemMap != null && !taxExemMap.isEmpty()) {
            String taxExemFlg = (String) taxExemMap.get("DS_TAX_EXEM_FLG");
            if (!ZYPConstant.FLG_OFF_N.equals(taxExemFlg)) {
                checkExemExprDt(cpoBean, diChkBean, taxExemMap, cpoBean.getBillToCustCd(), null);
            }
        }

        // get tax exemption expire date by DS Account number
        taxExemMap = getTaxExemByAcctNum(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());

        // Check tax exemption expire date
        if (taxExemMap != null && !taxExemMap.isEmpty()) {
            String taxExemFlg = (String) taxExemMap.get("DS_TAX_EXEM_FLG");
            if (!ZYPConstant.FLG_OFF_N.equals(taxExemFlg)) {
                checkExemExprDt(cpoBean, diChkBean, taxExemMap, null, cpoBean.getSellToCustCd());
            }
        }
    }

    /**
     * getTaxExemByLocNum
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private Map<String, Object> getTaxExemByLocNum(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);

        return (Map<String, Object>) ssmClient.queryObject("getTaxExemByLocNum", ssmParam);
    }

    /**
     * getTaxExemByAcctNum
     * @param glblCmpyCd String
     * @param ordNum String
     */
    private Map<String, Object> getTaxExemByAcctNum(String glblCmpyCd, String ordNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);

        return (Map<String, Object>) ssmClient.queryObject("getTaxExemByAcctNum", ssmParam);
    }

    /**
     * checkExemExprDt
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param taxExemMap Map
     * @param billToCustCd String
     * @param dsAcctCd String
     * @return boolean
     */
    private void checkExemExprDt(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, Map taxExemMap, String billToCustCd, String dsAcctCd) {

        SimpleDateFormat changeDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String exemExprDtSt = (String) taxExemMap.get("DS_EXEM_EXPR_DT");

        if (!ZYPCommonFunc.hasValue(exemExprDtSt)) {
            return;
        }

        String slsDtSt = cpoBean.getSlsDt();
        Date exemExprDt = null;
        Date slsDt = null;
        try {
            exemExprDt = changeDateFormat.parse(ZYPDateUtil.convertFormat(exemExprDtSt, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.SEPARATOR_SLASH));
            slsDt = changeDateFormat.parse(ZYPDateUtil.convertFormat(slsDtSt, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.SEPARATOR_SLASH));
        } catch (java.text.ParseException e) {
            msgMap.addXxMsgId(NWZM1753E);
            return;
        }

        if (E099_1035A.equals(diChkBean.getDiChckCd())) {
            BigDecimal diffNumOfDays = BigDecimal.valueOf(ZYPDateUtil.getDiffDays(exemExprDtSt, slsDtSt));
            BigDecimal taxExemDays = ZYPCodeDataUtil.getNumConstValue("TAX_EXEM_DAYS", cpoBean.getGlblCmpyCd());

            if (slsDt.compareTo(exemExprDt) > 0) {
                return;
            }

            if (taxExemDays.compareTo(diffNumOfDays) > 0) {
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Tax Exempt Number is about to expire in ").append(diffNumOfDays).append("days");
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
            }
            return;
        }

        if (E099_1035B.equals(diChkBean.getDiChckCd())) {
            if (slsDt.compareTo(exemExprDt) > 0) {
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Tax Exempt Number for the '");
                if (ZYPCommonFunc.hasValue(billToCustCd)) {
                    msgParam.append(billToCustCd).append("' location has expired");
                } else {
                    msgParam.append(dsAcctCd).append("' account has expired");
                }
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
            }
        }
    }

    /**
     * checkDiffSubTotalAndTotalMsrp
     * 
     * <pre>
     * DI Check Code: E099-1040
     * Sub Total on the order is more than the total MSRP
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkDiffSubTotalAndTotalMsrp(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        List<String> unusedList = new ArrayList<String>();
        BigDecimal sumMsrpAmt = getTotalMsrpAmt(cpoBean, unusedList);
        BigDecimal subTotalAmt = getSubTotalAmt(cpoBean, unusedList);

        if (ZYPCommonFunc.hasValue(sumMsrpAmt) || ZYPCommonFunc.hasValue(subTotalAmt)) {
            if (subTotalAmt.compareTo(sumMsrpAmt) > 0) {
                String msgParam = "Sub Total on the order is greater the total MSRP";
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            }
        }
    }

    /**
     * getTotalMsrpAmt
     * @param cpoBean NWZC155001CpoBean
     * @return BigDecimal
     */
    private BigDecimal getTotalMsrpAmt(NWZC155001CpoBean cpoBean, List<String> unusedList) {
        List<Map<String, Object>> msrpAmtList = getMsrpAmt(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum(), cpoBean.getSlsDt());

        BigDecimal totalMsrpAmt = BigDecimal.ZERO;
        for (Map<String, Object> msrpAmtMap : msrpAmtList) {
            BigDecimal ordQty = (BigDecimal) msrpAmtMap.get("ORD_QTY");
            BigDecimal msrpAmt = (BigDecimal) msrpAmtMap.get("PRC_LIST_EQUIP_PRC_AMT");

            if (msrpAmt == null || msrpAmt.compareTo(BigDecimal.ZERO) == 0) { // 2017/07/13
                // S21_NA#19888
                // Add
                // Condition
                // msrpAmt
                // ==
                // null
                // QC#31128 2019/04/10 Mod Start
                // unusedList.add((String)
                // msrpAmtMap.get("PRC_QLFY_VAL_TXT"));
                unusedList.add((String) msrpAmtMap.get("MDSE_CD"));
                // QC#31128 2019/04/10 Mod End
                continue;
            }

            totalMsrpAmt = add(totalMsrpAmt, ordQty.multiply(msrpAmt));
        }
        return totalMsrpAmt;
    }

    /**
     * getSubTotalAmt
     * @param cpoBean NWZC155001CpoBean
     * @return BigDecimal
     */
    private BigDecimal getSubTotalAmt(NWZC155001CpoBean cpoBean, List<String> unusedList) {
        BigDecimal subTotalAmt = BigDecimal.ZERO;
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {

            if (unusedList.contains(cpoDtlBean.getMdseCd())) {
                continue;
            } else if (cpoDtlBean.getMdseCd().length() > 8 && unusedList.contains(cpoDtlBean.getMdseCd().substring(0, 8))) {
                continue;
            }

            subTotalAmt = add(subTotalAmt, cpoDtlBean.getCpoDtlFuncNetAmt());
        }
        return subTotalAmt;
    }

    /**
     * getMsrpAmt
     * @param glblCmpyCd String
     * @param ordNum String
     * @param slsDt String
     */
    private List<Map<String, Object>> getMsrpAmt(String glblCmpyCd, String ordNum, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("prcListTpCd", PRC_LIST_TP.MSRP);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("prcQlfyTpCd", PRC_QLFY_TP.ITEM_CODE);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getMsrpAmt", ssmParam);
    }

    /**
     * checkSowRequire
     * 
     * <pre>
     * DI Check Code: E099-1045
     * SOW Required
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkSowRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        List<Map> sowReqLineMap = (List<Map>) getSowReqLine(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());

        if (sowReqLineMap == null || sowReqLineMap.isEmpty()) {
            return;
        }

        for (int mapIdx = 0; mapIdx < sowReqLineMap.size(); mapIdx++) {
            Map map = sowReqLineMap.get(mapIdx);

            String posnNum = map.get("DS_ORD_POSN_NUM").toString();
            String lineNum = map.get("DS_CPO_LINE_NUM").toString();
            String lineSubNum = null;
            if (ZYPCommonFunc.hasValue((BigDecimal) map.get("DS_CPO_LINE_SUB_NUM"))) {
                lineSubNum = map.get("DS_CPO_LINE_SUB_NUM").toString();
            }

            StringBuilder msgParam = new StringBuilder();
            msgParam.append("SOW Required for the ").append((String) map.get("MDSE_NM"));

            setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkBean.getDiChckCd(), msgParam.toString(), posnNum, lineNum, lineSubNum, CONFIG_CATG.OUTBOUND);
        }
    }

    /**
     * getSowReqLine
     * @param glblCmpyCd String
     * @param ordNum String
     * @return List<Map>
     */
    private List<Map> getSowReqLine(String glblCmpyCd, String ordNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordNum", ordNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);

        return (List<Map>) ssmClient.queryObjectList("getSowReqLine", ssmParam);
    }

    /**
     * checkSiteSrvyRequire
     * 
     * <pre>
     * DI Check Code: E099-1050
     * Site Survey Required
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkSiteSrvyRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2017/10/11 S21_NA#21469 Add Start
        key.put("flgN", ZYPConstant.FLG_OFF_N);
        // 2017/10/11 S21_NA#21469 Add End
        // QC#8611 Add Start
        key.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        key.put("diChckCd", diChkBean.getDiChckCd());
        // QC#8611 Add End
        key.put("rtrnLineStsList", new String[] {RTRN_LINE_STS.CANCELLED, RTRN_LINE_STS.CLOSED });

        // get order Line contains Site Survey Required item
        List<Map<String, Object>> siteSrvReqLineMap = (List<Map<String, Object>>) ssmClient.queryObjectList("getSiteSrvyReqConfig", key);

        // if has no result, do nothing
        if (siteSrvReqLineMap == null || siteSrvReqLineMap.isEmpty()) {
            return;
        }

        for (int mapIdx = 0; mapIdx < siteSrvReqLineMap.size(); mapIdx++) {
            Map<String, Object> map = siteSrvReqLineMap.get(mapIdx);

            BigDecimal configPk = (BigDecimal) map.get("DS_CPO_CONFIG_PK");
            String catgCd = (String) map.get("CONFIG_CATG_CD");
            String posnNum = (String) map.get("DS_ORD_POSN_NUM");
            String mdlNm = (String) map.get("MDL_DESC_TXT");

            StringBuilder msgParam = new StringBuilder();
            msgParam.append("Site Survey Required for the config model# '").append(mdlNm).append("'");

            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString(), configPk, catgCd, posnNum);
        }
    }

    // QC#8611 Add Start
    /**
     * checkSiteSrvyRequireWarning
     * 
     * <pre>
     * DI Check Code: E099-1100
     * Site Survey Required
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkSiteSrvyRequireWarning(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("flgY", ZYPConstant.FLG_ON_Y);
        // 2017/10/11 S21_NA#21469 Add Start
        key.put("flgN", ZYPConstant.FLG_OFF_N);
        // 2017/10/11 S21_NA#21469 Add End
        key.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        key.put("diChckCd", diChkBean.getDiChckCd());
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED });

        // get order Line contains Site Survey Required item
        List<Map<String, Object>> siteSrvReqLineMap = (List<Map<String, Object>>) ssmClient.queryObjectList("getSiteSrvyReqConfig", key);

        // if has no result, do nothing
        if (siteSrvReqLineMap == null || siteSrvReqLineMap.isEmpty()) {
            return;
        }

        for (int mapIdx = 0; mapIdx < siteSrvReqLineMap.size(); mapIdx++) {
            Map<String, Object> map = siteSrvReqLineMap.get(mapIdx);

            BigDecimal configPk = (BigDecimal) map.get("DS_CPO_CONFIG_PK");
            String catgCd = (String) map.get("CONFIG_CATG_CD");
            String posnNum = (String) map.get("DS_ORD_POSN_NUM");
            String mdlNm = (String) map.get("MDL_DESC_TXT");

            StringBuilder msgParam = new StringBuilder();
            msgParam.append("Site Survey Required for the config model# '").append(mdlNm).append("'");

            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString(), configPk, catgCd, posnNum);
        }
    }

    // QC#8611 Add End

    /**
     * check Association Program And Compliance Code Validation
     * 
     * <pre>
     * DI Check Code: E099-1055
     * Association & Compliance Code Validation
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkAssociationAndComplianceCodeValid(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        // QC#10734 Delete
        // String glblCmpyCd = cpoBean.getGlblCmpyCd();
        // String ordNum = cpoBean.getCpoOrdNum();
        //
        // boolean isDiError = false;
        //
        // // Error if the Association program is set but NOT contains
        // rebate item
        // isDiError =
        // isOrdAssociationNotContainRebateItem(glblCmpyCd, ordNum);
        // if (isDiError) {
        // String msgParam = "Missing required Rebate Item";
        // setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(),
        // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
        // msgParam);
        // return;
        // }
        //
        // // Error if the Association program is NOT set but contains
        // rebate item
        // isDiError = isOrdNotAssocContainRebateItem(glblCmpyCd,
        // ordNum);
        // if (isDiError) {
        // String msgParam =
        // "Incorrect or Missing, Compliance Code/Association Value";
        // // set error
        // setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(),
        // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
        // msgParam);
        // return;
        // }
        //
        // // ** TBD
        // // Error if order contains CSMP and Price contract,
        // // but Association Program is not set or not contains
        // rebate item
        // Map<String, Object> notAssocAndRebateOrdMap = (Map<String,
        // Object>) getNotAssocNotRebateOrd(glblCmpyCd, ordNum,
        // cpoBean.getSlsDt());
        //
        // if (notAssocAndRebateOrdMap != null &&
        // !notAssocAndRebateOrdMap.isEmpty()) {
        // String msgParam =
        // getErrMsgForAssocAndComplianceCodeValid(notAssocAndRebateOrdMap);
        // setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(),
        // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
        // msgParam);
        // return;
        // }

        // QC#10441 Add Start
        if (!ZYPCommonFunc.hasValue(cpoBean.getCsmpContrNum()) //
                && !ZYPCommonFunc.hasValue(cpoBean.getDlrRefNum()) //
                && !ZYPCommonFunc.hasValue(cpoBean.getPrcContrNum())) {
            return;
        }
        // QC#10441 Add End

        // 2016/11/01 S21_NA#15665 Add Start
        boolean hasCsmpNum = ZYPCommonFunc.hasValue(cpoBean.getCsmpContrNum());
        boolean hasRefNum = ZYPCommonFunc.hasValue(cpoBean.getDlrRefNum());
        boolean hasPrcContrNum = ZYPCommonFunc.hasValue(cpoBean.getPrcContrNum());

        CPOTMsg dsCpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(dsCpoTMsg.cpoOrdNum, cpoBean.getCpoOrdNum());

        dsCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(dsCpoTMsg);
        if (dsCpoTMsg == null) {
            msgMap.addXxMsgId(NWZM1208E);
            return;
        }

        PRC_CATGTMsg dsPrcCatgTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(dsPrcCatgTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(dsPrcCatgTMsg.prcCatgCd, dsCpoTMsg.prcCatgCd);
        dsPrcCatgTMsg = (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(dsPrcCatgTMsg);

        boolean hasPrcListPrcContrNum = true;
        if (dsPrcCatgTMsg == null || !ZYPCommonFunc.hasValue(dsPrcCatgTMsg.prcContrNum)) {
            hasPrcListPrcContrNum = false;
        }
        // S21_NA#15665
        // No error case:
        // 1. CSMP# or CSA# was set.
        // 2. Price Contract was not set
        // 3. Price Contract# of (Sell) Price List was not set
        if ((hasCsmpNum || hasRefNum) //
                && !hasPrcContrNum //
                && !hasPrcListPrcContrNum) {
            return;
        }
        // 2016/11/01 S21_NA#15665 Add End

        // // QC#10734 Add Start
        // final String MSG_NO_REBATE =
        // "Missing required Rebate Item %s";
        // // QC#10441 Mod Start
        // // final String MSG_NO_ASSOCIATION =
        // "Incorect, Inactive or Missing, Compliance Code/Association Value";
        // final String MSG_NO_ASSOCIATION =
        // "Incorrect, Inactive or Missing, Compliance Code/Association Value";
        // // QC#10441 Mod End
        // final String MSG_NO_ASS_AND_REB =
        // "Customer CSMP# %s eligible for both Rebate Item %s and Association Value : %s missing. ";

        // 2016/11/01 S21_NA#15665-2 Add Start
        if ((hasCsmpNum || hasRefNum) //
                && !hasPrcContrNum //
                && hasPrcListPrcContrNum) {

            List<String> rebateItemList = getRebateItemForPrcContr(cpoBean, dsPrcCatgTMsg.prcContrNum.getValue());

            if (rebateItemList == null || rebateItemList.isEmpty()) {
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), MSG_NO_ASSOCIATION);
            } else {
                for (String mdseCd : rebateItemList) {
                    String msg = String.format(MSG_NO_ASS_AND_REB, cpoBean.getCsmpContrNum(), mdseCd, dsPrcCatgTMsg.prcContrNum.getValue());
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msg);
                }
            }
            return;
        }
        // 2016/11/01 S21_NA#15665-2 Add End

        // Get Price Contract (Association Program)
        Map<String, Object> prcContrMap = getPriceContract(cpoBean);

        // If no price contract
        if (prcContrMap == null || prcContrMap.size() == 0) {
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), MSG_NO_ASSOCIATION);
            return;
        }

        String csmpNum = (String) prcContrMap.get("CSMP_NUM");
        String prcContrNum = (String) prcContrMap.get("PRC_CONTR_NUM");
        BigDecimal prcContrPk = (BigDecimal) prcContrMap.get("PRC_CONTR_PK");

        // If no price contract
        if (prcContrPk == null) {
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), MSG_NO_ASSOCIATION);
            return;
        }

        // Get Rebate Item that is not used.
        List<String> rebateItemList = getRebateItemList(cpoBean, prcContrPk);

        // If order doesn't have price contract, or different from
        // master setting.
        if (!ZYPCommonFunc.hasValue(cpoBean.getPrcContrNum()) || !cpoBean.getPrcContrNum().equals(prcContrNum)) {
            // If order has the correct rebate items, or unnecessary.
            if (rebateItemList == null || rebateItemList.size() == 0) {
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), MSG_NO_ASSOCIATION);
                return;
            } else {
                // If order doesn't have correct rebate items.
                for (String mdseCd : rebateItemList) {
                    String msg = String.format(MSG_NO_ASS_AND_REB, csmpNum, mdseCd, prcContrNum);
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msg);
                }
                return;
            }
        } else {
            // Order has correct price contract, but no rebate item.
            for (String mdseCd : rebateItemList) {
                String msg = String.format(MSG_NO_REBATE, mdseCd);
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msg);
            }
            return;
        }
        // QC#10734 End
    }

    // /**
    // * isOrdAssociationNotContainRebateItem
    // * @param glblCmpyCd String
    // * @param ordNum String
    // * @return boolean
    // */
    // public boolean isOrdAssociationNotContainRebateItem(String
    // glblCmpyCd, String ordNum) {
    // Map<String, Object> ssmParam = new HashMap<String, Object>();
    // ssmParam.put("glblCmpyCd", glblCmpyCd);
    // ssmParam.put("ordNum", ordNum);
    // ssmParam.put("buyoutRebate", MDSE_CTX_BUYOUTS_REBATES);
    // ssmParam.put("rowNum", ROW_NUM);
    //
    // return (Integer)
    // ssmClient.queryObject("isOrdAssocAndNotContainRebateItem",
    // ssmParam) > 0;
    // }

    // /**
    // * isOrdNotAssociationContainsRebateItem
    // * @param glblCmpyCd String
    // * @param ordNum String
    // * @return boolean
    // */
    // public boolean isOrdNotAssocContainRebateItem(String
    // glblCmpyCd, String ordNum) {
    // Map<String, Object> ssmParam = new HashMap<String, Object>();
    // ssmParam.put("glblCmpyCd", glblCmpyCd);
    // ssmParam.put("ordNum", ordNum);
    // ssmParam.put("buyoutRebate", MDSE_CTX_BUYOUTS_REBATES);
    // ssmParam.put("rowNum", ROW_NUM);
    //
    // return (Integer)
    // ssmClient.queryObject("isOrdNotAssocContainRebateItem",
    // ssmParam) > 0;
    // }

    // /**
    // * getNotAssocNotRebateOrd
    // * @param glblCmpyCd String
    // * @param ordNum String
    // * @return Map<String, Object>
    // */
    // public Map<String, Object> getNotAssocNotRebateOrd(String
    // glblCmpyCd, String ordNum, String slsDt) {
    // Map<String, Object> ssmParam = new HashMap<String, Object>();
    // ssmParam.put("glblCmpyCd", glblCmpyCd);
    // ssmParam.put("ordNum", ordNum);
    // ssmParam.put("slsDt", slsDt);
    // ssmParam.put("buyoutRebate", MDSE_CTX_BUYOUTS_REBATES);
    //
    // return (Map<String, Object>)
    // ssmClient.queryObject("getNotAssocNotRebateOrd", ssmParam);
    // }

    /**
     * getPriceContract
     * @param cpoBean
     * @return Map<String, Object>
     */
    public Map<String, Object> getPriceContract(NWZC155001CpoBean cpoBean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("slsDt", cpoBean.getSlsDt());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        ssmParam.put("sellToCustCd", cpoBean.getSellToCustCd());

        return (Map<String, Object>) ssmClient.queryObject("getPriceContract", ssmParam);
    }

    /**
     * getRebateItemList
     * @param cpoBean
     * @param prcContrPk
     * @return List<String>
     */
    public List<String> getRebateItemList(NWZC155001CpoBean cpoBean, BigDecimal prcContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("slsDt", cpoBean.getSlsDt());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        ssmParam.put("sellToCustCd", cpoBean.getSellToCustCd());
        ssmParam.put("prcContrPk", prcContrPk);
        ssmParam.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());
        ssmParam.put("dsOrdTpCd", cpoBean.getDsOrdTpCd());
        ssmParam.put("ordCatgCtxTpEquip", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        ssmParam.put("ordCatgCtxTpSupply", ORD_CATG_CTX_TP.SUPPLIES_ORDER_VALUE_SET);

        return (List<String>) ssmClient.queryObjectList("getRebateItemList", ssmParam);
    }

    // 2016/11/01 S21_NA#15665-2 Add Start
    private List<String> getRebateItemForPrcContr(NWZC155001CpoBean cpoBean, String prcContrNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("prcContrNum", prcContrNum);
        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsDt", cpoBean.getSlsDt());

        return (List<String>) ssmClient.queryObjectList("getRebateItemForPrcContr", ssmParam);
    }

    // 2016/11/01 S21_NA#15665-2 Add End

    /**
     * getErrMsgForAssocAndComplianceCodeValid
     * @param notAssocAndRebateOrdMap Map<String, Object>
     * @return String
     */
    public String getErrMsgForAssocAndComplianceCodeValid(Map<String, Object> notAssocAndRebateOrdMap) {
        StringBuilder msgParam = new StringBuilder();

        msgParam.append("Customer CSMP#").append((String) notAssocAndRebateOrdMap.get("CSMP_CONTR_NUM"));
        msgParam.append(" eligible for both Rebate Item#").append((String) notAssocAndRebateOrdMap.get("MDSE_CD"));
        msgParam.append(" and Association Value : ").append((String) notAssocAndRebateOrdMap.get("PRC_CONTR_NUM"));
        msgParam.append(" missing. ");

        return msgParam.toString();
    }

    /**
     * checkHddValidation
     * 
     * <pre>
     * DI Check Code: E099-1060
     * Hard Drive (RMA) Validation
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param pMsg NWZC155001PMsg
     */
    public void checkHddValidation(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001PMsg pMsg) {
        if (chkHddRmvReqInSvcMachMstr(cpoBean, diChkBean)) {
            return;
        }

        chkHddRmvReqInCustMstr(cpoBean, diChkBean, pMsg);
    }

    /**
     * chkReqHddRmvInCustMstr
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @return boolean
     */
    public boolean chkHddRmvReqInSvcMachMstr(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("rmvCd", HDD_REMOVE);
        key.put("eraseCd", HDD_ERASE);
        key.put("flgY", ZYPConstant.FLG_ON_Y); // 2015/12/23
        // S21_NA#2391 Add
        key.put("rtrnLineStsList", new String[] {RTRN_LINE_STS.CANCELLED, RTRN_LINE_STS.CLOSED }); // QC#22874

        // QC#8389 Mod Start
        // Map<String, Object> hddRealeaseMap = (Map<String, Object>)
        // ssmClient.queryObject("getHddReleaseLine", key);
        List<Map<String, Object>> hddRealeaseMapList //
        = (List<Map<String, Object>>) ssmClient.queryObjectList("getHddReleaseLine", key);

        String hddReleaseTp = null;
        boolean isDiError = false;
        // if (hddRealeaseMap != null && !hddRealeaseMap.isEmpty()) {
        if (hddRealeaseMapList != null && !hddRealeaseMapList.isEmpty()) {
            // for (int mapIdx = 0; mapIdx < hddRealeaseMap.size();
            // mapIdx++) {
            for (Map<String, Object> hddRealeaseMap : hddRealeaseMapList) {
                // String posnNum = (String)
                // hddRealeaseMap.get("POSN_NUM");
                // String lineNum = (String)
                // hddRealeaseMap.get("DS_CPO_RTRN_LINE_NUM");
                // String lineSubNum = (String)
                // hddRealeaseMap.get("DS_CPO_RTRN_LINE_SUB_NUM");
                String posnNum = "";
                String lineNum = "";
                String lineSubNum = "";
                if (hddRealeaseMap.get("DS_ORD_POSN_NUM") != null) {
                    posnNum = (String) hddRealeaseMap.get("DS_ORD_POSN_NUM");
                }
                if (hddRealeaseMap.get("DS_CPO_LINE_NUM") != null) {
                    lineNum = hddRealeaseMap.get("DS_CPO_LINE_NUM").toString();
                }
                if (hddRealeaseMap.get("DS_CPO_LINE_SUB_NUM") != null) {
                    lineSubNum = hddRealeaseMap.get("DS_CPO_LINE_SUB_NUM").toString();
                }
                hddReleaseTp = (String) hddRealeaseMap.get("HDD_RELEASE_TP");

                if (HDD_REMOVE.equals(hddReleaseTp)) {
                    setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkBean.getDiChckCd(), HDD_RMV_MSGPARAM, posnNum, lineNum, lineSubNum, CONFIG_CATG.INBOUND);
                    isDiError = true;
                } else {
                    setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkBean.getDiChckCd(), HDD_ERASE_MSGPARAM, posnNum, lineNum, lineSubNum, CONFIG_CATG.INBOUND);
                    isDiError = true;
                }
            }
        }
        return isDiError;
        // QC#8389 Mod End
    }

    /**
     * chkReqHddRmvInCustMstr
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param pMsg NWZC155001PMsg
     */
    public void chkHddRmvReqInCustMstr(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001PMsg pMsg) {
        // check Hdd Remove Required in Customer Master
        boolean isHddRmvReq = false;
        NMZC610001PMsg defaultSearchPMsgForHddRmv = setDefaultSearchPMsg(cpoBean, HDD_REMOVE);
        new NMZC610001().execute(defaultSearchPMsgForHddRmv, onBatchType);

        if (ZYPCommonFunc.hasValue(defaultSearchPMsgForHddRmv.xxMsgIdList.no(0).xxMsgId)) {
            msgMap.addXxMsgId(defaultSearchPMsgForHddRmv.xxMsgIdList.no(0).xxMsgId.getValue());
            return;
        }
        if (ZYPCommonFunc.hasValue(defaultSearchPMsgForHddRmv.dsAcctNum_O)) {
            isHddRmvReq = true;
        }

        // check Hdd Erase Required in Customer Master
        boolean isHddEraseReq = false;
        if (!isHddRmvReq) {
            NMZC610001PMsg defaultSearchPMsgForHddErase = setDefaultSearchPMsg(cpoBean, HDD_ERASE);
            new NMZC610001().execute(defaultSearchPMsgForHddErase, onBatchType);

            if (ZYPCommonFunc.hasValue(defaultSearchPMsgForHddErase.xxMsgIdList.no(0).xxMsgId)) {
                msgMap.addXxMsgId(defaultSearchPMsgForHddErase.xxMsgIdList.no(0).xxMsgId.getValue());
                return;
            }
            if (ZYPCommonFunc.hasValue(defaultSearchPMsgForHddErase.dsAcctNum_O)) {
                isHddEraseReq = true;
            }
        }

        // check Hdd Erase Required But not set in CpoDtl
        if (isHddEraseReq || isHddRmvReq) {
            chkHddRmvInCpoDtl(cpoBean, diChkBean, isHddRmvReq, isHddEraseReq);
        }
    }

    /**
     * chkReqHddRmvInCustMstr
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param isHddRmvReq boolean
     * @param isHddEraseReq boolean
     */
    public void chkHddRmvInCpoDtl(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, boolean isHddRmvReq, boolean isHddEraseReq) {
        for (int idx = 0; idx < cpoBean.getCpoDtlBeanList().size(); idx++) {
            NWZC155001CpoDtlBean cpoDtlBean = cpoBean.getCpoDtlBeanList().get(idx);
            // QC#22874
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()) //
                    || RTRN_LINE_STS.CLOSED.equals(cpoDtlBean.getRtrnLineStsCd())) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(cpoDtlBean.getSvcMachMstrPk())) {
                if (isHddRmvReq && !HDD_REMOVE.equals(cpoDtlBean.getHddRmvCd())) {
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), HDD_RMV_MSGPARAM);
                    continue;
                }
                if (isHddEraseReq && !HDD_ERASE.equals(cpoDtlBean.getHddRmvCd())) {
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), HDD_ERASE_MSGPARAM);
                }
            }
        }
    }

    /**
     * setDefaultSearchPMsg
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param hddReleaseTpCd String
     * @return NMZC610001PMsg
     */
    public NMZC610001PMsg setDefaultSearchPMsg(NWZC155001CpoBean cpoBean, String hddReleaseTpCd) {
        NMZC610001PMsg defaultSearchPMsg = new NMZC610001PMsg();
        defaultSearchPMsg.glblCmpyCd.setValue(cpoBean.getGlblCmpyCd());
        defaultSearchPMsg.xxModeCd.setValue(NMZC610001Constant.PROCESS_MODE_SPECIAL_HANDLING);
        if (HDD_REMOVE.equals(hddReleaseTpCd)) {
            defaultSearchPMsg.dsSpclHdlgTpCd.setValue(DS_SPCL_HDLG_TP.HARD_DRIVE_REMOVAL);
        } else {
            defaultSearchPMsg.dsSpclHdlgTpCd.setValue(DS_SPCL_HDLG_TP.HARD_DRIVE_ERASE);
        }
        defaultSearchPMsg.dsAcctNum_I1.setValue(cpoBean.getShipToCustAcctCd());
        defaultSearchPMsg.slsDt.setValue(cpoBean.getSlsDt());

        return defaultSearchPMsg;
    }

    // QC#5383 Add Start
    /**
     * checkCtacValidation
     * 
     * <pre>
     * DI Check Code: E099-1065C
     * Contacts Validation
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param configCatg String
     */
    // public void checkCtacValidation(NWZC155001CpoBean cpoBean,
    // NWZC155001DiChkBean diChkBean) { // S21_NA#22664 MOD 2017/12/06
    public void checkCtacValidation(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, String configCatg) {
        // get required role
        List<Map<String, Object>> ordTpCtacRelnList = getOrdTpCtacReln(cpoBean);
        if (ordTpCtacRelnList.isEmpty()) {
            return;
        }

        // 2019/12/25 QC#54229 Mod Start
        // List<Map<String, Object>> ctacValidationConfigList =
        // getCtacValidationConfig(cpoBean);
        List<Map<String, Object>> ctacValidationConfigList = getCtacValidationConfig(cpoBean, configCatg);
        // 2019/12/25 QC#54229 Mod End
        for (Map<String, Object> ordTpCtacRelnMap : ordTpCtacRelnList) {
            String ctacTpCd = (String) ordTpCtacRelnMap.get("CTAC_TP_CD");
            String ctacTpDescTxt = (String) ordTpCtacRelnMap.get("CTAC_TP_DESC_TXT");
            // get required input Field
            Map<String, Object> reqFieldForCtacPsnMap //
            = (Map<String, Object>) ssmClient.queryObject("getRequiredContactField", setCtacPsnReqFlgKey(cpoBean, ctacTpCd, diChkBean.getDiChckCd(), null));
            if (reqFieldForCtacPsnMap == null || reqFieldForCtacPsnMap.isEmpty()) {
                continue;
            }
            for (Map<String, Object> ctacValidationConfigMap : ctacValidationConfigList) {
                String catgCd = (String) ctacValidationConfigMap.get("CONFIG_CATG_CD");
                // if (CONFIG_CATG.OUTBOUND.equals(catgCd)) { //
                // S21_NA#22664 MOD 2017/12/06
                if (!configCatg.equals(catgCd)) {
                    continue;
                }
                // 2017/10/11 S21_NA#21469 Add Start
                String configTpCd = (String) ctacValidationConfigMap.get("CONFIG_TP_CD");
                if (CONFIG_TP.TO_SALES_CONVERSION.equals(configTpCd) || CONFIG_TP.ADD_TO_CONFIG.equals(configTpCd)) {
                    continue;
                }
                // Add Start 2018/04/06 QC#14949
                // START 2023/01/05 R.Azucena [QC#60974 DEL]
                // if (null == ctacValidationConfigMap.get("MDL_ID")
                // || (null != ctacValidationConfigMap.get("MDL_ID")
                // &&
                // SVC_MDL_TP.SOFTWARE.equals(ctacValidationConfigMap.get("SVC_MDL_TP_CD"))))
                // {
                // continue;
                // }
                // END 2023/01/05 R.Azucena [QC#60974 DEL]
                // Add End 2018/04/06 QC#14949
                // 2017/10/11 S21_NA#21469 Add End
                BigDecimal dsCpoConfigPk = (BigDecimal) ctacValidationConfigMap.get("DS_CPO_CONFIG_PK");
                String posnNum = (String) ctacValidationConfigMap.get("DS_ORD_POSN_NUM");
                // get contact person detail configuration Level
                List<Map<String, Object>> ctacPsnDtlList //
                = (List<Map<String, Object>>) ssmClient.queryObjectList("getCtacPsnDtlOfConfigLvl", setCtacPsnReqFlgKey(cpoBean, ctacTpCd, diChkBean.getDiChckCd(), dsCpoConfigPk));
                if (ctacPsnDtlList == null || ctacPsnDtlList.isEmpty()) {
                    // 2017/07/11 S21_NA#19218 Mod Start
                    // // get contact person header Level
                    // Map<String, Object> ctacPsnDtlMap =
                    // (Map<String, Object>)
                    // ssmClient.queryObject("getCtacPsnDtlHdrLvl",
                    // setCtacPsnReqFlgKey(cpoBean, ctacTpCd, null,
                    // null));
                    // if (ctacPsnDtlMap == null ||
                    // ctacPsnDtlMap.isEmpty()) {
                    // // set Error if contact person header Level is
                    // not exist.
                    // String msgParam = MSGPARAM_CTAC_PSN_VALID_CHECK
                    // + ctacTpDescTxt;
                    // setErrToDiRsltBeanConfigLvl(cpoBean,
                    // diChkBean.getDiChckCd(),
                    // diChkBean.getDiChkLvlCd(),
                    // diChkBean.getDiChkRsltTpCd(), msgParam,
                    // dsCpoConfigPk, catgCd, posnNum);
                    // } else {
                    // // check input for required field for Contact
                    // Person header Level
                    // ArrayList<String> errFieldList =
                    // getErrorFieldList(reqFieldForCtacPsnMap,
                    // ctacPsnDtlMap);
                    // if (!errFieldList.isEmpty()) {
                    // // set error if required field is not entered
                    // header Level
                    // String msgParam =
                    // getCtacPsnReqErrMsg(errFieldList,
                    // ctacTpDescTxt);
                    // setErrToDiRsltBeanConfigLvl(cpoBean,
                    // diChkBean.getDiChckCd(),
                    // diChkBean.getDiChkLvlCd(),
                    // diChkBean.getDiChkRsltTpCd(), msgParam,
                    // dsCpoConfigPk, catgCd, posnNum);
                    // }
                    // }
                    String msgParam = MSGPARAM_CTAC_PSN_VALID_CHECK + ctacTpDescTxt;
                    setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, dsCpoConfigPk, catgCd, posnNum);
                    // 2017/07/11 S21_NA#19218 Mod End
                } else {
                    for (Map<String, Object> ctacPsnDtlMap : ctacPsnDtlList) {
                        // check input for required field for Contact
                        // Person configuration Level
                        ArrayList<String> errFieldList = getErrorFieldList(reqFieldForCtacPsnMap, ctacPsnDtlMap);
                        if (!errFieldList.isEmpty()) {
                            // set error if required field is not
                            // entered configuration Level
                            String msgParam = getCtacPsnReqErrMsg(errFieldList, ctacTpDescTxt);
                            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, dsCpoConfigPk, catgCd, posnNum);
                        }
                    }
                }
            }
        }
    }

    // QC#5383 Add End

    /**
     * checkCtacValidationForHeaderLevel
     * 
     * <pre>
     * DI Check Code: E099-1065A
     * Contacts Validation of Header Level
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkCtacValidationForHeaderLevel(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        // ### eManage Contact Person Check ###
        // check requirement of EManage Contact Person
        if (isShellRequireEmanageCtacPsn(cpoBean)) {

            checkEmanageContactPersonRequire(cpoBean, diChkBean);

        }

        // ### Equipment and Service Role Contact Person Check ###
        // QC#15439-1
        // checkEquipSvcRoleCtacReq(cpoBean, diChkBean);
    }

    /**
     * checkCtacValidationForLineLevel
     * 
     * <pre>
     * DI Check Code: E099-1065B
     * Contacts Validation of Line Level
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkCtacValidationForLineLevel(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        // ### Subscription Support Contact Person Check ###
        // get Order Lines contains subscription support items
        List<Map<String, Object>> lineContainSbscrptSprtItemList = getOrdLineContainSbscrptSprtItem(cpoBean);
        if (lineContainSbscrptSprtItemList.isEmpty()) {
            return;
        }

        String ctacTpSubScrpServ = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_SUBSCRIPTION_SUPPORT, cpoBean.getGlblCmpyCd()); // 2016/03/02
        // S21_NA#4593
        // Add

        // get required input Field
        Map<String, Object> reqFieldForCtacPsnMap //
        = (Map<String, Object>) ssmClient.queryObject("getRequiredContactField", setCtacPsnReqFlgKey(cpoBean, ctacTpSubScrpServ, diChkBean.getDiChckCd(), null)); // 2016/03/02
        // S21_NA#4593
        // Mod
        // BigDecimal dsCpoConfigPk = new BigDecimal(0);

        // 2018/10/17 QC#28370 Add Start
        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        // 2018/10/17 QC#28370 Add End
        // check line contains subscription support item
        for (Map<String, Object> lineContainSbscrptItemMap : lineContainSbscrptSprtItemList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) lineContainSbscrptItemMap.get("DS_CPO_CONFIG_PK");
            String configCatgCd = (String) lineContainSbscrptItemMap.get("CONFIG_CATG_CD");
            String dsOrdPosnNum = (String) lineContainSbscrptItemMap.get("DS_ORD_POSN_NUM");

            // 2018/10/17 QC#28370 Add Start
            if (pkList.contains(dsCpoConfigPk)) {
                continue;
            }
            // 2018/10/17 QC#28370 Add End
            // get Subscription Service Contact person
            List<Map<String, Object>> ctacPsnDtlList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCtacPsnDtlOfConfigLvl", setCtacPsnReqFlgKey(cpoBean, ctacTpSubScrpServ, diChkBean.getDiChckCd(), dsCpoConfigPk)); // 2016/03/02
            // S21_NA#4593
            // Mod

            // set Error if Subscription Service contact person is not
            // set
            if (ctacPsnDtlList == null || ctacPsnDtlList.isEmpty()) {
                String msgParam = MSGPARAM_CTAC_PSN_VALID_CHECK + E099_1065_MSG_SUBSCRIPT_SERVICE;
                // QC#13364
                // setErrorToDiRsltByDefineConfig(cpoBean, diChkBean,
                // diChkBean.getDiChckCd(), msgParam, dsCpoConfigPk);
                setErrToDiRsltBeanConfigLvl(//
                        cpoBean //
                        , diChkBean.getDiChckCd() //
                        , diChkBean.getDiChkLvlCd() //
                        , diChkBean.getDiChkRsltTpCd() //
                        , msgParam, dsCpoConfigPk, configCatgCd, dsOrdPosnNum);
                // 2018/10/17 QC#28370 Add Start
                pkList.add(dsCpoConfigPk);
                // 2018/10/17 QC#28370 Add End
                continue;
                // return;
            }

            if (reqFieldForCtacPsnMap == null || reqFieldForCtacPsnMap.isEmpty()) {
                continue;
            }

            for (Map<String, Object> ctacPsnDtlMap : ctacPsnDtlList) {
                // check input for required field for Contact Person
                ArrayList<String> errFieldList = getErrorFieldList(reqFieldForCtacPsnMap, ctacPsnDtlMap);
                if (!errFieldList.isEmpty()) {
                    // set error if required field is not entered
                    String msgParam = getCtacPsnReqErrMsg(errFieldList, E099_1065_MSG_SUBSCRIPT_SERVICE);
                    // QC#13364
                    // setErrorToDiRsltByDefineConfig(cpoBean,
                    // diChkBean, diChkBean.getDiChckCd(), msgParam,
                    // dsCpoConfigPk);
                    setErrToDiRsltBeanConfigLvl(//
                            cpoBean //
                            , diChkBean.getDiChckCd() //
                            , diChkBean.getDiChkLvlCd() //
                            , diChkBean.getDiChkRsltTpCd() //
                            , msgParam, dsCpoConfigPk, configCatgCd, dsOrdPosnNum);
                    // break;
                }
            }
        }
    }

    /**
     * check attribute required for Emanage Contact person is entered
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkEmanageContactPersonRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        String ctacTpEmanage = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EMANAGE, cpoBean.getGlblCmpyCd());

        // get Emanage contact person Detail
        Map<String, Object> ctacPsnDtlMap //
        = (Map<String, Object>) ssmClient.queryObject("getCtacPsnDtl", setCtacPsnReqFlgKey(cpoBean, ctacTpEmanage, diChkBean.getDiChckCd(), null));

        // set error if Emanage Contact Person is not set to Order
        // QC#15439
        // if (ctacPsnDtlMap.isEmpty()) {
        if (ctacPsnDtlMap == null || ctacPsnDtlMap.isEmpty()) {
            String msgParam = MSGPARAM_CTAC_PSN_VALID_CHECK + E099_1065_MSG_EMANAGE;
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            return;
        }

        // get required field
        Map<String, Object> requiredFieldMap //
        = (Map<String, Object>) ssmClient.queryObject("getRequiredContactField", setCtacPsnReqFlgKey(cpoBean, ctacTpEmanage, diChkBean.getDiChckCd(), null));

        if (requiredFieldMap != null && !requiredFieldMap.isEmpty()) {

            // set error if required field is not input
            ArrayList<String> errMsgAttbList = getErrorFieldList(requiredFieldMap, ctacPsnDtlMap);
            if (!errMsgAttbList.isEmpty()) {
                String msgParam = getCtacPsnReqErrMsg(errMsgAttbList, E099_1065_MSG_EMANAGE);
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            }
        }
    }

    // /**
    // * check attribute required for Equipment service role contact
    // person is entered
    // * @param cpoBean NWZC155001CpoBean
    // * @param diChkBean NWZC155001DiChkBean
    // */
    // public void checkEquipSvcRoleCtacReq(NWZC155001CpoBean cpoBean,
    // NWZC155001DiChkBean diChkBean) {
    // // get Equip Service Role Contact person
    // List<Map> equipSrvcLineMap = (List<Map>)
    // getEquipSrvcConfig(cpoBean);
    //
    // if (equipSrvcLineMap == null || equipSrvcLineMap.isEmpty()) {
    // return;
    // }
    //
    // // set error if Equip Service Role Contact person is NOT set
    // if (ZYPCommonFunc.hasValue((String)
    // equipSrvcLineMap.get(0).get("DC_ORD_NUM")) &&
    // !ZYPCommonFunc.hasValue((String)
    // equipSrvcLineMap.get(0).get("CP_ORD_NUM"))) {
    //
    // // set Error
    // String msgParam = MSGPARAM_CTAC_PSN_VALID_CHECK +
    // E099_1065_MSG_EQUIPMENT_ORDER;
    // setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(),
    // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
    // msgParam);
    // return;
    // }
    //
    // if (ZYPCommonFunc.hasValue((String)
    // equipSrvcLineMap.get(0).get("CP_ORD_NUM"))) {
    //
    // String ctacTpEquipServ =
    // ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EQUIP_SERVICE,
    // cpoBean.getGlblCmpyCd());
    //
    // // get required field for Equip Service Role contact person
    // List<Map> ctacReqFlgMap = (List<Map>)
    // ssmClient.queryObjectList("getRequiredContactField",
    // setCtacPsnReqFlgKey(cpoBean, ctacTpEquipServ,
    // diChkBean.getDiChckCd(), null));
    //
    // if (ctacReqFlgMap == null || ctacReqFlgMap.isEmpty()) {
    // return;
    // }
    //
    // // get not input required field
    // ArrayList<String> errMsgAttbList =
    // getErrorFieldList(ctacReqFlgMap.get(0),
    // equipSrvcLineMap.get(0));
    //
    // // set error if required field is not input
    // if (!errMsgAttbList.isEmpty()) {
    // // QC#15439
    // // String msgParam = getCtacPsnReqErrMsg(errMsgAttbList,
    // ctacTpEquipServ);
    // String msgParam = getCtacPsnReqErrMsg(errMsgAttbList,
    // E099_1065_MSG_EQUIPMENT_ORDER);
    // setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(),
    // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
    // msgParam);
    // }
    // }
    // }

    /**
     * isShellRequireEmanageCtacPsn
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @return boolean
     */
    public boolean isShellRequireEmanageCtacPsn(NWZC155001CpoBean cpoBean) {
        Map<String, Object> eManageKey = new HashMap<String, Object>();
        eManageKey.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        eManageKey.put("ordNum", cpoBean.getCpoOrdNum());
        // QC#15439
        // eManageKey.put("ctacPsnTp",
        // ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EMANAGE,
        // cpoBean.getGlblCmpyCd()));
        eManageKey.put("mtrReadMethCd", MTR_READ_METH.EMANAGE); // eManage
        eManageKey.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
        eManageKey.put("rowNum", ROW_NUM);

        int cntEmanageOrd = (Integer) ssmClient.queryObject("isShellRequireEmanageCtacPsn", eManageKey);
        if (cntEmanageOrd > 0) {
            return true;
        }
        return false;
    }

    /**
     * getOrdLineContainSbscrptSprtItem
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @return S21SsmEZDResult
     */
    public List<Map<String, Object>> getOrdLineContainSbscrptSprtItem(NWZC155001CpoBean cpoBean) {
        Map<String, Object> sbscrptSprtKey = new HashMap<String, Object>();
        sbscrptSprtKey.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        sbscrptSprtKey.put("ordNum", cpoBean.getCpoOrdNum());
        sbscrptSprtKey.put("mdseTpCtxTp", MDSE_CTX_SUBSCRIPT_SPRT);
        sbscrptSprtKey.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getLineContainSbscSprtItem", sbscrptSprtKey);
    }

    /**
     * getEquipSrvcConfig
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @return S21SsmEZDResult
     */
    public List<Map<String, Object>> getEquipSrvcConfig(NWZC155001CpoBean cpoBean) {
        Map<String, Object> equipSrvcKey = new HashMap<String, Object>();
        equipSrvcKey.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        equipSrvcKey.put("ordNum", cpoBean.getCpoOrdNum());
        equipSrvcKey.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);
        equipSrvcKey.put("ctacTpCd", ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_EQUIP_SERVICE, cpoBean.getGlblCmpyCd()));

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getEquipSrvcConfig", equipSrvcKey);
    }

    /**
     * setCtacPsnReqFlgKey
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param ctacPsnTpCd String
     * @param dichkCd String
     * @param cpoConfigPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> setCtacPsnReqFlgKey(NWZC155001CpoBean cpoBean, String ctacPsnTpCd, String dichkCd, BigDecimal cpoConfigPk) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("ctacTpCd", ctacPsnTpCd);
        key.put("ordDt", cpoBean.getOrdDt());
        key.put("diChkCd", dichkCd);
        // if (ZYPCommonFunc.hasValue(cpoConfigPk)) {
        key.put("cpoConfigPk", cpoConfigPk);
        // }
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        return key;
    }

    /**
     * getErrorFieldList
     * 
     * <pre>
     * @param ctacReqFlgMap Map<String, Object>
     * @param ctacPsnDtlMap List<Map>
     * @return ArrayList<String>
     */
    public ArrayList<String> getErrorFieldList(Map<String, Object> ctacReqFlgMap, Map<String, Object> ctacPsnDtlMap) {
        ArrayList<String> errFieldList = new ArrayList<String>();

        // First Name
        if (ZYPConstant.FLG_ON_Y.equals((String) ctacReqFlgMap.get("FIRST_NM_REQ_FLG")) //
                && !ZYPCommonFunc.hasValue((String) ctacPsnDtlMap.get("CTAC_PSN_FIRST_NM"))) {
            errFieldList.add("First Name");
        }

        // Last Name
        if (ZYPConstant.FLG_ON_Y.equals((String) ctacReqFlgMap.get("LAST_NM_REQ_FLG")) //
                && !ZYPCommonFunc.hasValue((String) ctacPsnDtlMap.get("CTAC_PSN_LAST_NM"))) {
            errFieldList.add("Last Name");
        }

        // Email Address
        if (ZYPConstant.FLG_ON_Y.equals((String) ctacReqFlgMap.get("EML_ADDR_REQ_FLG")) //
                && !ZYPCommonFunc.hasValue((String) ctacPsnDtlMap.get("CTAC_PSN_EML_ADDR"))) {
            errFieldList.add("Email Address");
        }

        // Telephone Number
        if (ZYPConstant.FLG_ON_Y.equals((String) ctacReqFlgMap.get("TEL_NUM_REQ_FLG")) //
                && !ZYPCommonFunc.hasValue((String) ctacPsnDtlMap.get("CTAC_PSN_TEL_NUM"))) {
            errFieldList.add("Tel Number");
        }

        // FAX Number
        if (ZYPConstant.FLG_ON_Y.equals((String) ctacReqFlgMap.get("FAX_NUM_REQ_FLG")) //
                && !ZYPCommonFunc.hasValue((String) ctacPsnDtlMap.get("CTAC_PSN_FAX_NUM"))) {
            errFieldList.add("Fax Number");
        }

        // Extension Number
        if (ZYPConstant.FLG_ON_Y.equals((String) ctacReqFlgMap.get("EXTN_NUM_REQ_FLG")) //
                && !ZYPCommonFunc.hasValue((String) ctacPsnDtlMap.get("CTAC_PSN_EXTN_NUM"))) {
            errFieldList.add("Extension Number");
        }

        return errFieldList;
    }

    /**
     * getCtacPsnReqErrMsg
     * 
     * <pre>
     * @param errCtacPsnFieldList ArrayList<String>
     * @param ctacPsnTp String
     * @return String
     */
    public String getCtacPsnReqErrMsg(ArrayList<String> errCtacPsnFieldList, String ctacPsnTp) {
        StringBuilder msgParam = new StringBuilder();
        msgParam.append("Contact details ");

        int i = 0;
        for (int idx = 0; idx < errCtacPsnFieldList.size(); idx++) {
            i++;
            msgParam.append(errCtacPsnFieldList.get(idx));
            if (i != errCtacPsnFieldList.size()) {
                msgParam.append(", ");
            }
        }

        msgParam.append(" have to be entered for ").append(ctacPsnTp);

        return msgParam.toString();
    }

    /**
     * checkProServicePrice
     * 
     * <pre>
     * DI Check Code: E099-1070
     * Professional Services Price Exception
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param pMsg NWZC155001PMsg
     */
    public void checkProServicePrice(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001PMsg pMsg) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("mdseTpCtxTp", PROF_SERVICES);

        // get parameter for Pricing API
        List<Map> proServiceLineMap = (List<Map>) ssmClient.queryObjectList("getProServiceLine", key);
        if (proServiceLineMap == null || proServiceLineMap.isEmpty()) {
            return;
        }

        // // get Floor Price of Professional Service Price
        // NWZC157001PMsg prcApiPMsg = getFloorPrc(cpoBean,
        // proServiceLineMap, pMsg);
        //
        // if (prcApiPMsg.xxMsgIdList.getValidCount() > 0) {
        // for (int i = 0; i < prcApiPMsg.xxMsgIdList.getValidCount();
        // i++) {
        // pMsg.xxMsgIdList.no(pMsg.xxMsgIdList.getValidCount()).xxMsgId.setValue(prcApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
        // pMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount()
        // + 1);
        // }
        // return;
        // }

        // get Order Total GP Percent
        BigDecimal ordTotalCpPct = getOrdTotalGpPercent(cpoBean, pMsg);
        // S21_NA#8549 Add
        if (ordTotalCpPct == null) {
            return;
        }

        // set Error if Floor Price is over Function net amount of
        // order line
        // for (int idx = 0; idx <
        // prcApiPMsg.NWZC157002PMsg.getValidCount(); idx++) {
        // NWZC157004PMsg prcApiLineResult =
        // prcApiPMsg.NWZC157004PMsg.no(idx);
        // for (NWZC155001CpoDtlBean cpoDtlBean :
        // cpoBean.getCpoDtlBeanList()) {
        //
        // if (S21StringUtil.isEquals(cpoDtlBean.getConfigCatgCd(),
        // prcApiLineResult.configCatgCd.getValue()) &&
        // S21StringUtil.isEquals(deleteZero(cpoDtlBean.getCpoDtlLineNum()),
        // deleteZero(prcApiLineResult.trxLineNum.getValue()))
        // &&
        // S21StringUtil.isEquals(deleteZero(cpoDtlBean.getCpoDtlLineSubNum()),
        // deleteZero(prcApiLineResult.trxLineSubNum.getValue()))) {
        // chkProfSvcPrcException(cpoBean, diChkBean, cpoDtlBean,
        // prcApiLineResult, ordTotalCpPct);
        // }
        // }
        // }

        // get min(FIRST_BIZ_CTX_ATTRB_NUM)
        BigDecimal minFirstBizCtxAttrbNum = getMinFirstBizCtxAttrbNum(proServiceLineMap);

        // error check
        for (int mapIdx = 0; mapIdx < proServiceLineMap.size(); mapIdx++) {
            Map proSvcLineMap = proServiceLineMap.get(mapIdx);

            String lineNum = proSvcLineMap.get("DS_CPO_LINE_NUM").toString();
            String lineSubNum = null;
            if (ZYPCommonFunc.hasValue((BigDecimal) proSvcLineMap.get("DS_CPO_LINE_SUB_NUM"))) {
                lineSubNum = proSvcLineMap.get("DS_CPO_LINE_SUB_NUM").toString();
            }
            // 2017/09/15 S21_NA#21040 Add Start
            String posnNum = proSvcLineMap.get("DS_ORD_POSN_NUM").toString();
            // 2017/09/15 S21_NA#21040 Add End

            NWZC155001CpoDtlBean cpoDtlBean = getProfSvcCpoDtl(cpoBean, lineNum, lineSubNum, posnNum, CONFIG_CATG.OUTBOUND); // 2017/09/15
            // S21_NA#21040
            // Mod

            if (cpoDtlBean == null) {
                continue;
            }

            cpoDtlBean.setFirstBizCtxAttrbNum(minFirstBizCtxAttrbNum);
            chkProfSvcPrcException(cpoBean, diChkBean, cpoDtlBean, ordTotalCpPct);
        }
    }

    private BigDecimal getMinFirstBizCtxAttrbNum(List<Map> proServiceLineMap) {
        BigDecimal minFirstBizCtxAttrbNum = null;
        for (int mapIdx = 0; mapIdx < proServiceLineMap.size(); mapIdx++) {
            Map proSvcLineMap = proServiceLineMap.get(mapIdx);
            BigDecimal attrbNum = (BigDecimal) proSvcLineMap.get("FIRST_BIZ_CTX_ATTRB_NUM");
            if (minFirstBizCtxAttrbNum == null || minFirstBizCtxAttrbNum.compareTo(attrbNum) > 0) {
                minFirstBizCtxAttrbNum = attrbNum;
            }
        }
        if (minFirstBizCtxAttrbNum == null) {
            minFirstBizCtxAttrbNum = BigDecimal.ZERO;
        }
        return minFirstBizCtxAttrbNum;
    }

    /**
     * getOrdTotalGpPercent
     * @param cpoBean NWZC155001CpoBean
     * @param pMsg NWZC155001PMsg
     * @return BigDecimal
     */
    public BigDecimal getOrdTotalGpPercent(NWZC155001CpoBean cpoBean, NWZC155001PMsg pMsg) {
        // S21_NA#8549 Modify
        // Map<String, Object> key = new HashMap<String, Object>();
        // key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        // key.put("ordNum", cpoBean.getCpoOrdNum());
        //
        // return (BigDecimal)
        // ssmClient.queryObject("getOrdTotalGpPercent", key);

        NWZC156001PMsg prftApiPMsg = getProfitabilityCalculateApiPMsg(cpoBean);
        List<NWZC156002PMsg> prftApiPMsg2List = new ArrayList<NWZC156002PMsg>();

        if (prftApiPMsg == null) {
            msgMap.addXxMsgId(NWZM1942E);
            return null;
        }

        new NWZC156001().execute(prftApiPMsg, prftApiPMsg2List, onBatchType);

        if (prftApiPMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < prftApiPMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(prftApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return null;
        }

        if (prftApiPMsg.grsPrftPct.getValue() == null) {
            msgMap.addXxMsgId(NWZM1942E);
            return null;
        }

        return prftApiPMsg.grsPrftPct.getValue();
    }

    private NWZC156001PMsg getProfitabilityCalculateApiPMsg(NWZC155001CpoBean cpoBean) {

        NWZC156001PMsg pMsg = new NWZC156001PMsg();

        List<Map<String, Object>> resultMapList = getPrftCalcInfoForOrder(cpoBean);

        if (resultMapList == null || resultMapList.isEmpty()) {
            return null;
        }
        if (resultMapList.size() > pMsg.svcConfigRef.length()) {
            return null;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(pMsg.trxHdrNum, cpoBean.getCpoOrdNum());
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC156001Constant.MODE_ONLINE);

        int i = 0;
        Map<String, Object> resultMap0 = resultMapList.get(i);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, (String) resultMap0.get("DS_ORD_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, (String) resultMap0.get("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, (String) resultMap0.get("DS_ORD_RSN_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcBaseDt, (String) resultMap0.get("PRC_BASE_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcCalcDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.funcNegoDealAmt, (BigDecimal) resultMap0.get("NEGO_DEAL_AMT"));
        ZYPEZDItemValueSetter.setValue(pMsg.csmpContrNum, (String) resultMap0.get("CSMP_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dlrRefNum, (String) resultMap0.get("DLR_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, (String) resultMap0.get("SELL_TO_CUST_CD"));

        for (Map<String, Object> resultMap : resultMapList) {
            NWZC156001_svcConfigRefPMsg scrPMsg = pMsg.svcConfigRef.no(i++);
            ZYPEZDItemValueSetter.setValue(scrPMsg.ordPrftTrxCatgCd, (String) resultMap.get("CONFIG_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxLineNum, (String) resultMap.get("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxLineSubNum, (String) resultMap.get("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsOrdPosnNum, (String) resultMap.get("DS_ORD_POSN_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsCpoLineNum, (BigDecimal) resultMap.get("DS_CPO_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsCpoLineSubNum, (BigDecimal) resultMap.get("DS_CPO_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.mdseCd, (String) resultMap.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.rtlWhCd, (String) resultMap.get("RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.rtlSwhCd, (String) resultMap.get("RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.dsOrdLineCatgCd, (String) resultMap.get("DS_ORD_LINE_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.ordQty, (BigDecimal) resultMap.get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.custUomCd, (String) resultMap.get("CUST_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxRefLineNum, (String) resultMap.get("REF_CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.trxRefLineSubNum, (String) resultMap.get("REF_CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.flPrcListCd, (String) resultMap.get("FL_PRC_LIST_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.csmpPrcListCd, (String) resultMap.get("CSMP_PRC_LIST_CD")); // 2016/03/10
            // S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.prcCatgCd, (String) resultMap.get("PRC_CATG_CD")); // QC#7707
            // 2016/10/20 S21_NA#15193 Mod Start
            // ZYPEZDItemValueSetter.setValue(scrPMsg.coaMdseTpNm,
            // (String) resultMap.get("COA_MDSE_TP_NM")); // QC#7707
            ZYPEZDItemValueSetter.setValue(scrPMsg.coaProjNm, (String) resultMap.get("COA_MDSE_TP_NM")); // QC#7707
            // 2016/10/20 S21_NA#15193 Mod End
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcManFlAdjAmt, (BigDecimal) resultMap.get("FUNC_MAN_FL_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitListPrcAmt, (BigDecimal) resultMap.get("FUNC_PRC_LIST_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcNetUnitPrcAmt, (BigDecimal) resultMap.get("FUNC_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcNetSellPrcAmt, (BigDecimal) resultMap.get("FUNC_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcSvcCostTrnsfAmt, (BigDecimal) resultMap.get("FUNC_SVC_COST_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcManRepRevAdjAmt, (BigDecimal) resultMap.get("FUNC_MAN_REP_REV_ADJ_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.funcSvcRevTrnsfAmt, (BigDecimal) resultMap.get("FUNC_SVC_REV_TRNSF_AMT"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.cpoDtlFuncSlsAmt, (BigDecimal) resultMap.get("CPO_DTL_FUNC_SLS_AMT")); // 2016/03/10
            // S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.ordCustUomQty, (BigDecimal) resultMap.get("ORD_CUST_UOM_QTY")); // 2016/03/10
            // S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.csmpContrNum, (String) resultMap.get("DTL_CSMP_CONTR_NUM")); // 2016/03/10
            // S21_NA#2939
            ZYPEZDItemValueSetter.setValue(scrPMsg.dlrRefNum, (String) resultMap.get("DTL_DLR_REF_NUM")); // 2016/03/10
            // S21_NA#2939

            ZYPEZDItemValueSetter.setValue(scrPMsg.ordLineStsCd, (String) resultMap.get("LINE_STS_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.billToCustCd, (String) resultMap.get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(scrPMsg.shipToCustCd, (String) resultMap.get("SHIP_TO_CUST_CD"));

            ZYPEZDItemValueSetter.setValue(scrPMsg.funcUnitStdCostAmt, (BigDecimal) resultMap.get("THIS_MTH_TOT_STD_COST_AMT"));
        }
        pMsg.svcConfigRef.setValidCount(i);

        return pMsg;
    }

    private List<Map<String, Object>> getPrftCalcInfoForOrder(NWZC155001CpoBean cpoBean) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        params.put("trxHdrNum", cpoBean.getCpoOrdNum());
        params.put("outBound", CONFIG_CATG.OUTBOUND);
        params.put("inBound", CONFIG_CATG.INBOUND);
        params.put("ordPrftTrxCatgOutBound", ORD_PRFT_TRX_CATG.OUTBOUND);
        params.put("ordPrftTrxCatgInBound", ORD_PRFT_TRX_CATG.INBOUND);
        params.put("slsDt", cpoBean.getSlsDt());

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmClient.queryObjectList("getPrftCalcInfoForOrder", params);
        return list;
    }

    /**
     * chkProfSvcPrcException
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param cpoDtlBean NWZC155001CpoDtlBean
     * @param ordTotalCpPct BigDecimal
     */
    public void chkProfSvcPrcException(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001CpoDtlBean cpoDtlBean, BigDecimal ordTotalCpPct) {
        // S21_NA#8549 modify
        // if
        // (prcApiLineResult.xxTotBaseAmt.getValue().compareTo(cpoDtlBean.getCpoDtlFuncNetAmt())
        // <= 0) {
        // return;
        // }
        //
        // StringBuilder msgParam = new StringBuilder();
        // msgParam.append("The unit selling price <> unit list price for the item#:");
        // msgParam.append(cpoDtlBean.getMdseCd()).append(" , line#:");
        // msgParam.append(deleteZero(cpoDtlBean.getDsOrdPosnNum())).append(".").append(deleteZero(cpoDtlBean.getCpoDtlLineNum()));
        //
        // setErrToDiRsltBean(cpoBean, cpoDtlBean,
        // diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(),
        // diChkBean.getDiChkRsltTpCd(), msgParam.toString());

        // Sell Price List != Floor Price List
        if (!cpoDtlBean.getPrcCatgCd().equals(cpoDtlBean.getFlPrcListCd())) {
            return;
        }

        // NetUnitPrc < FloorUnitPrc(SellUnitPrc)
        if (cpoDtlBean.getEntFuncNetUnitPrcAmt().compareTo(cpoDtlBean.getFuncPrcListPrcAmt()) < 0) {
            setErrForProfSvcPrc(cpoBean, diChkBean, cpoDtlBean);
            return;
        }

        // min(FIRST_BIZ_CTX_ATTRB_NUM) > GP%
        if (cpoDtlBean.getFirstBizCtxAttrbNum().compareTo(ordTotalCpPct) > 0) {
            // NetUnitPrc > FloorUnitPrc(SellUnitPrc)
            if (cpoDtlBean.getEntFuncNetUnitPrcAmt().compareTo(cpoDtlBean.getFuncPrcListPrcAmt()) > 0) {
                setErrForProfSvcPrc(cpoBean, diChkBean, cpoDtlBean);
                return;
            }
        }
    }

    private void setErrForProfSvcPrc(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001CpoDtlBean cpoDtlBean) {
        StringBuilder msgParam = new StringBuilder();
        msgParam.append("The unit selling price <> unit list price for the item#:");
        msgParam.append(cpoDtlBean.getMdseCd()).append(" , line#:");
        msgParam.append(deleteZero(cpoDtlBean.getDsOrdPosnNum())).append(".").append(deleteZero(cpoDtlBean.getCpoDtlLineNum()));

        setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
    }

    /**
     * getProfSvcCpoDtl
     * @param glblCmpyCd String
     * @param ordNum String
     * @param slsDt String
     */
    private NWZC155001CpoDtlBean getProfSvcCpoDtl(NWZC155001CpoBean cpoBean, String lineNum, String lineSubNum, String posnNum, String configCatg) { // 2017/09/15
        // S21_NA#21040
        // Mod
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
            // 2017/09/15 S21_NA#21040 Mod Start
            // if
            // (S21StringUtil.isEquals(cpoDtlBean.getDsCpoLineNum(),
            // lineNum) &&
            // S21StringUtil.isEquals(cpoDtlBean.getDsCpoLineSubNum(),
            // lineSubNum) &&
            // S21StringUtil.isEquals(cpoDtlBean.getConfigCatgCd(),
            // configCatg)) {
            if (S21StringUtil.isEquals(cpoDtlBean.getDsCpoLineNum(), lineNum) && S21StringUtil.isEquals(cpoDtlBean.getDsCpoLineSubNum(), lineSubNum) && S21StringUtil.isEquals(cpoDtlBean.getDsOrdPosnNum(), posnNum)
                    && S21StringUtil.isEquals(cpoDtlBean.getConfigCatgCd(), configCatg)) {
                // 2017/09/15 S21_NA#21040 Mod End
                return cpoDtlBean;
            }
        }
        return null;
    }

    /**
     * setHdrParam
     * @param cpoBean NWZC155001CpoBean
     * @param prcApiPMsg NWZC157001PMsg
     */
    private static void setHdrParam(NWZC155001CpoBean cpoBean, NWZC157001PMsg prcApiPMsg) {
        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        prcApiPMsg.xxModeCd.setValue(NWZC157001.GET_BASE_PRICE);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, cpoBean.getSlsDt());
        prcApiPMsg.prcCtxTpCd.setValue(PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, cpoBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, cpoBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, cpoBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, cpoBean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_OFF_N);
    }

    /**
     * setHdrParam
     * @param cpoBean NWZC155001CpoBean
     * @param cpoDtlBean NWZC155001CpoDtlBean
     * @param prcApiPMsg NWZC157001PMsg
     * @param idx int
     */
    private void setOutboundLineParam(NWZC155001CpoBean cpoBean, NWZC155001CpoDtlBean cpoDtlBean, NWZC157001PMsg prcApiPMsg) {
        NWZC157002PMsg lineParam = prcApiPMsg.NWZC157002PMsg.no(prcApiPMsg.NWZC157002PMsg.getValidCount());
        ZYPEZDItemValueSetter.setValue(lineParam.trxLineNum, cpoDtlBean.getCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(lineParam.trxLineSubNum, cpoDtlBean.getCpoDtlLineSubNum());
        ZYPEZDItemValueSetter.setValue(lineParam.configCatgCd, cpoDtlBean.getConfigCatgCd());
        ZYPEZDItemValueSetter.setValue(lineParam.billToCustCd, cpoDtlBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(lineParam.shipToCustCd, cpoDtlBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(lineParam.dsAcctNum_SH, cpoDtlBean.getDsAcctNum_SH());
        ZYPEZDItemValueSetter.setValue(lineParam.dsAcctNum_BL, cpoDtlBean.getDsAcctNum_BL());
        ZYPEZDItemValueSetter.setValue(lineParam.prcCatgCd, cpoDtlBean.getPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(lineParam.csmpNum, cpoDtlBean.getCsmpNum());
        ZYPEZDItemValueSetter.setValue(lineParam.dlrRefNum, cpoDtlBean.getDlrRefNum());
        ZYPEZDItemValueSetter.setValue(lineParam.prcContrNum, cpoDtlBean.getPrcContrNum());
        ZYPEZDItemValueSetter.setValue(lineParam.coaBrCd, cpoDtlBean.getCoaBrCd());
        ZYPEZDItemValueSetter.setValue(lineParam.ccyCd, cpoDtlBean.getCcyCd());
        ZYPEZDItemValueSetter.setValue(lineParam.prcListEquipConfigNum, cpoDtlBean.getPrcListEquipConfigNum());
        ZYPEZDItemValueSetter.setValue(lineParam.dsOrdLineCatgCd, cpoDtlBean.getDsOrdLineCatgCd());
        ZYPEZDItemValueSetter.setValue(lineParam.ordQty, cpoDtlBean.getOrdQty());
        ZYPEZDItemValueSetter.setValue(lineParam.invQty, cpoDtlBean.getInvQty());
        ZYPEZDItemValueSetter.setValue(lineParam.mdlId, cpoDtlBean.getMdlId());
        ZYPEZDItemValueSetter.setValue(lineParam.ctyAddr_SH, cpoDtlBean.getCtyAddr_SH());
        ZYPEZDItemValueSetter.setValue(lineParam.stCd_SH, cpoDtlBean.getStCd_SH());
        ZYPEZDItemValueSetter.setValue(lineParam.ctryCd_SH, cpoDtlBean.getCtryCd_SH());
        ZYPEZDItemValueSetter.setValue(lineParam.mdseCd, cpoDtlBean.getMdseCd());
        ZYPEZDItemValueSetter.setValue(lineParam.pkgUomCd, cpoDtlBean.getUomCd());
        ZYPEZDItemValueSetter.setValue(lineParam.ordCustUomQty, cpoDtlBean.getUomQty());
        if (ZYPConstant.FLG_ON_Y.equals(cpoDtlBean.getOpenFlg())) {
            ZYPEZDItemValueSetter.setValue(lineParam.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        } else {
            ZYPEZDItemValueSetter.setValue(lineParam.xxPrcCloFlg, ZYPConstant.FLG_ON_Y);
        }

        CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) getCpoDtl(cpoDtlBean.getGlblCmpyCd(), cpoDtlBean.getCpoOrdNum(), cpoDtlBean.getDsCpoLineNum(), cpoDtlBean.getDsCpoLineSubNum());
        if (cpoDtlTMsg != null) {
            ZYPEZDItemValueSetter.setValue(lineParam.coaBrCd, cpoDtlTMsg.coaBrCd.getValue());
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(prcApiPMsg.NWZC157002PMsg.getValidCount() + 1);
    }

    /**
     * checkDiffShipToLoc
     * 
     * <pre>
     * DI Check Code: E099-1085
     * Different Customer Ship To Location on RMA
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkDiffShipToLoc(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("svcMachMstrStsList", new String[] {SVC_MACH_MSTR_STS.INSTALLED, SVC_MACH_MSTR_STS.DEALER_SERVICE }); // 2019/04/03
        // S21_NA#31019
        // Add

        // QC#8389 Mod Start
        List<Map<String, Object>> diffShipToLineMapList //
        = (List<Map<String, Object>>) ssmClient.queryObjectList("getDiffShipToCustLine", key);
        // Map<String, Object> diffShipToLineMap = (Map<String,
        // Object>) ssmClient.queryObject("getDiffShipToCustLine",
        // key);

        if (diffShipToLineMapList == null || diffShipToLineMapList.isEmpty()) {
            return;
        }
        // if (diffShipToLineMap == null ||
        // diffShipToLineMap.isEmpty()) {
        // return;
        // }
        // QC#8389 Mod End
        // 2018/10/17 QC#28370 Add Start
        List<BigDecimal> keyList = new ArrayList<BigDecimal>();
        // 2018/10/17 QC#28370 Add End

        for (Map<String, Object> diffShipToLineMap : diffShipToLineMapList) {
            // 2018/10/17 QC#28370 Add Start
            BigDecimal configPk = (BigDecimal) diffShipToLineMap.get("DS_CPO_CONFIG_PK");
            if (keyList.contains(configPk)) {
                continue;
            }
            // 2018/10/17 QC#28370 Add End
            // for (int mapIdx = 0; mapIdx < diffShipToLineMap.size();
            // mapIdx++) {
            String posnNum = (String) diffShipToLineMap.get("DS_ORD_POSN_NUM");
            // QC#8389 Mod Start
            String lineNum = "";
            String lineSubNum = "";
            if (diffShipToLineMap.get("DS_CPO_LINE_NUM") != null) {
                lineNum = diffShipToLineMap.get("DS_CPO_LINE_NUM").toString();
            }
            if (diffShipToLineMap.get("DS_CPO_LINE_SUB_NUM") != null) {
                lineSubNum = diffShipToLineMap.get("DS_CPO_LINE_SUB_NUM").toString();
            }
            // String lineNum = (String)
            // diffShipToLineMap.get("DS_CPO_RTRN_LINE_NUM");
            // String lineSubNum = (String)
            // diffShipToLineMap.get("DS_CPO_RTRN_LINE_SUB_NUM");
            // QC#8389 Mod End
            String rtrnShipTo = (String) diffShipToLineMap.get("RTRN_SHIP_TO_CUST_CD");
            String smmShipTo = (String) diffShipToLineMap.get("SMM_SHIP_TO_CUST_CD");

            String msgParam = getMsgParamForDiffShipToLoc(lineNum, lineSubNum, rtrnShipTo, smmShipTo, cpoBean.getCpoOrdNum());
            // 2018/10/17 QC#28370 Mod Start
            // setErrorToDiRsltByDefineLine(cpoBean, diChkBean,
            // diChkBean.getDiChckCd(), msgParam, posnNum, lineNum,
            // lineSubNum, CONFIG_CATG.INBOUND);
            setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, configPk, CONFIG_CATG.INBOUND, posnNum);
            keyList.add(configPk);
            // 2018/10/17 QC#28370 Mod End
        }
    }

    /**
     * getMsgParamForDiffShipToLoc
     * @param lineNum String
     * @param lineSubNum String
     * @param rtrnShipTo String
     * @param smmShipTo String
     * @param ordNum String
     * @return String
     */
    public String getMsgParamForDiffShipToLoc(String lineNum, String lineSubNum, String rtrnShipTo, String smmShipTo, String ordNum) {
        StringBuilder msgParam = new StringBuilder();
        // QC#8389 Mod Start
        msgParam.append("Ship to Location# ").append(rtrnShipTo);
        msgParam.append(" on the RMA#");
        // msgParam.append(" on the RMA# ").append(lineNum);
        // if (ZYPCommonFunc.hasValue(lineSubNum)) {
        // msgParam.append(".").append(lineSubNum);
        // }
        msgParam.append(" is different from ").append(smmShipTo);
        msgParam.append("  of the orignal order# ");
        // msgParam.append("  of the order# ").append(ordNum);
        // QC#8389 Mod End

        return msgParam.toString();
    }

    /**
     * checkDuplicateRmaChk
     * 
     * <pre>
     * DI Check Code: E099-1090
     * Duplicate RMA Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkDuplicateRmaChk(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        for (int idx = 0; idx < cpoBean.getCpoDtlBeanList().size(); idx++) {

            NWZC155001CpoDtlBean cpoDtlBean = cpoBean.getCpoDtlBeanList().get(idx);
            // if
            // (RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()))
            // {
            // continue;
            // }
            // 2015/12/23 S21_NA#2391 Mod Start
            // List<Map<String, Object>> duplicateRmaLineList =
            // getDuplicateRmaLine(cpoDtlBean);
            List<Map<String, Object>> duplicateRmaLineList = null;
            // 2017/02/28 QC#16575 UPD START
            // if (ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum()) ||
            // ZYPCommonFunc.hasValue(cpoDtlBean.getSvcConfigMstrPk()))
            // {
            if ((ZYPCommonFunc.hasValue(cpoDtlBean.getSerNum()) && ZYPCommonFunc.hasValue(cpoDtlBean.getMdseCd())) || ZYPCommonFunc.hasValue(cpoDtlBean.getSvcConfigMstrPk())) {
                // 2017/02/28 QC#16575 UPD E N D
                duplicateRmaLineList = getDuplicateRmaLine(cpoDtlBean);
            }
            // 2015/12/23 S21_NA#2391 Mod End

            if (duplicateRmaLineList == null || duplicateRmaLineList.size() == 0) {
                continue;
            }

            for (int mapIdx = 0; mapIdx < duplicateRmaLineList.size(); mapIdx++) {

                Map map = duplicateRmaLineList.get(mapIdx);
                String serNum = (String) map.get("SER_NUM");
                // QC#7943
                // String configMstrPk = (String)
                // map.get("SVC_CONFIG_MSTR_PK");
                BigDecimal configMstrPk = (BigDecimal) map.get("SVC_CONFIG_MSTR_PK");
                String ordNum = (String) map.get("CPO_ORD_NUM");

                boolean isSameSerNum = false;
                boolean isSameConfigMstrPk = false;
                if (ZYPCommonFunc.hasValue(serNum)) {
                    if (serNum.equals(cpoDtlBean.getSerNum())) {
                        isSameSerNum = true;
                    }
                }
                String strConfigMstrPk = "";
                if (ZYPCommonFunc.hasValue(configMstrPk)) {
                    // QC#7943
                    // if
                    // (configMstrPk.equals(cpoDtlBean.getSvcConfigMstrPk()))
                    // {
                    if (configMstrPk.compareTo(cpoDtlBean.getSvcConfigMstrPk()) == 0) {
                        isSameConfigMstrPk = true;
                    }
                    strConfigMstrPk = configMstrPk.toString();
                }

                // QC#7943
                // String msgParam =
                // getMsgParamForDuplicateRmaChk(serNum, configMstrPk,
                // isSameSerNum, isSameConfigMstrPk, ordNum);
                String msgParam = getMsgParamForDuplicateRmaChk(serNum, strConfigMstrPk, isSameSerNum, isSameConfigMstrPk, ordNum);
                setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            }
        }
    }

    /**
     * getDuplicateRmaLine
     * @param cpoDtlBean NWZC155001CpoDtlBean
     * @return Map<String, Object>
     */
    public List<Map<String, Object>> getDuplicateRmaLine(NWZC155001CpoDtlBean cpoDtlBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoDtlBean.getGlblCmpyCd());
        key.put("ordNum", cpoDtlBean.getCpoOrdNum());
        key.put("configMstrPk", cpoDtlBean.getSvcConfigMstrPk());
        key.put("serNum", cpoDtlBean.getSerNum());
        // 2017/02/28 QC#16575 ADD START
        key.put("mdseCd", cpoDtlBean.getMdseCd());
        // 2017/02/28 QC#16575 ADD E N D
        key.put("svcMachUsgStsCd", SVC_MACH_USG_STS.RETURNED);
        key.put("flgY", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getDupicateRmaLine", key);
    }

    /**
     * getMsgParamForDiffShipToLoc
     * @param serNum String
     * @param configMstrPk String
     * @param isSameSerNum boolean
     * @param isSameConfigMstrPk boolean
     * @param ordNum String
     * @return String
     */
    public String getMsgParamForDuplicateRmaChk(String serNum, String configMstrPk, boolean isSameSerNum, boolean isSameConfigMstrPk, String ordNum) {

        StringBuilder msgParam = new StringBuilder();
        if (isSameSerNum) {
            msgParam.append(serNum);
            if (isSameConfigMstrPk) {
                msgParam.append(" and ");
            }
        }
        if (isSameConfigMstrPk) {
            msgParam.append(configMstrPk);
        }
        msgParam.append(" exists on the order# ").append(ordNum);

        return msgParam.toString();
    }

    /**
     * checkDiffModel
     * 
     * <pre>
     * DI Check Code: E099-IT01
     * Check if there is the Model ID on Shell
     * that is not same as Model ID on Configuration
     * 
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkDiffModel(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        // 2019/05/07 QC#50174 Add Start
        if (isBooked(cpoBean)) {

            return;
        }
        // 2019/05/07 QC#50174 Add End

        // S21_NA#8388 START
        if (isDeclineSvc(cpoBean)) {
            return;
        }
        // S21_NA#8388 END

        // QC#8838
        // Map<String, Object> diffModelLineMap = (Map<String,
        // Object>) ssmClient.queryObject("getDiffModelLine", key);
        List<Map<String, Object>> diffModelLineList = (List<Map<String, Object>>) ssmClient.queryObjectList("getDiffModelLine", key);

        // if (diffModelLineMap == null || diffModelLineMap.isEmpty())
        // {
        if (diffModelLineList == null || diffModelLineList.size() == 0) {
            return;
        }

        // for (int mapIdx = 0; mapIdx < diffModelLineMap.size();
        // mapIdx++) {
        for (int mapIdx = 0; mapIdx < diffModelLineList.size(); mapIdx++) {
            Map<String, Object> diffModelLineMap = (Map<String, Object>) diffModelLineList.get(mapIdx);
            if (diffModelLineMap == null || diffModelLineMap.isEmpty()) {
                continue;
            }

            // S21_NA#8388 START
            if (isDeclineSvc((String) diffModelLineMap.get("DCLN_SVC_CD"))) {
                continue;
            }
            // S21_NA#8388 END

            String posnNum = diffModelLineMap.get("DS_ORD_POSN_NUM").toString();
            BigDecimal lineNum = (BigDecimal) diffModelLineMap.get("DS_CPO_LINE_NUM");
            BigDecimal lineSubNum = (BigDecimal) diffModelLineMap.get("DS_CPO_LINE_SUB_NUM");

            // 2018/10/17 QC#28370 Mod Start
            StringBuilder msgParam = new StringBuilder();
            msgParam.append("Config Model <> Shell Model for Shell #").append((BigDecimal) diffModelLineMap.get("SHELL_LINE_NUM"));
            // String msgParam = "Config Model <> Shell Model";

            setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkBean.getDiChckCd(), msgParam.toString(), posnNum, lineNum, lineSubNum, CONFIG_CATG.OUTBOUND);
            // 2018/10/17 QC#28370 Mod End
        }
    }

    /**
     * checkDealerAuthValid
     * 
     * <pre>
     * DI Check Code: E099-IT02
     * Authorized Service Dealer Check
     * 
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkDealerAuthValid(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        final String glblCmpyCd = cpoBean.getGlblCmpyCd();
        final String dsOrdCatgCd = cpoBean.getDsOrdCatgCd();
        final String dsOrdTpCd = cpoBean.getDsOrdTpCd();

        // if the Context Type of Order Type is Dealer, it will be
        // target of this check
        boolean isDealerCtxtTpOrd = checkisOrdDealerCtxTp(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, SERVICING_DEALER_ORDERS);
        if (!isDealerCtxtTpOrd) {
            if (checkisOrdDealerCtxTp(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, DEALER_ORDER)) {
                isDealerCtxtTpOrd = true;
            } else {
                return;
            }
        }

        // get check target cpo detail data
        List<Map> parentMdseLineMapList = getAuthAndConfigInfo(cpoBean);

        // check cpo detail data
        NWZC155001CpoDtlBean cpoDtlBean = null;
        for (Map map : parentMdseLineMapList) {

            // Error if Configuration Flag is Y, but Sales Auth or
            // Service Auth is NOT required
            // QC#6274
            String configFlg = map.get("CONFIG_FLG") == null ? "N" : (String) map.get("CONFIG_FLG");
            if (ZYPConstant.FLG_OFF_N.equals(configFlg)) {
                continue;
            }
            // QC#6274
            String slsAuthFlg = map.get("SLS_AUTH_FLG") == null ? "N" : (String) map.get("SLS_AUTH_FLG");
            String svcAuthFlg = map.get("SVC_AUTH_FLG") == null ? "N" : (String) map.get("SVC_AUTH_FLG");
            if (!ZYPConstant.FLG_ON_Y.equals(slsAuthFlg) || !ZYPConstant.FLG_ON_Y.equals(svcAuthFlg)) {

                // QC#6274
                String posnNum = map.get("DS_ORD_POSN_NUM") == null ? "" : (String) map.get("DS_ORD_POSN_NUM");
                String lineNum = map.get("DS_CPO_LINE_NUM") == null ? "" : map.get("DS_CPO_LINE_NUM").toString();
                String subLineNum = null;
                if (ZYPCommonFunc.hasValue((BigDecimal) map.get("DS_CPO_LINE_SUB_NUM"))) {
                    subLineNum = map.get("DS_CPO_LINE_SUB_NUM").toString();
                }

                for (int idx = 0; idx < cpoBean.getCpoDtlBeanList().size(); idx++) {
                    cpoDtlBean = cpoBean.getCpoDtlBeanList().get(idx);
                    // QC#22874
                    if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                            || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd()) //
                            || RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()) //
                            || RTRN_LINE_STS.CLOSED.equals(cpoDtlBean.getRtrnLineStsCd())) {
                        continue;
                    }
                    // QC#6274
                    if (!posnNum.equals(cpoDtlBean.getDsOrdPosnNum()) //
                            || !lineNum.equals(cpoDtlBean.getDsCpoLineNum()) //
                            || (ZYPCommonFunc.hasValue(subLineNum) && !subLineNum.equals(cpoDtlBean.getDsCpoLineSubNum()))) {
                        continue;
                    }

                    // QC#6274
                    String mktMdlCd = map.get("M_MKT_MDL_CD") == null ? "" : (String) map.get("M_MKT_MDL_CD");
                    String msgParam = getMsgParamForDlrAuthValidChk(cpoBean.getSellToCustCd(), mktMdlCd);
                    // set Error
                    setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                    break;
                }
            }
        }
    }

    // 2016/02/19 S21_NA#1686 Add Start
    /**
     * checkPrepaymentAmt
     * 
     * <pre>
     * DI Check Code: E099-IT03
     * Check that the Prepayment Amount is not a large amount of money than the Order Total Amount
     * 
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkPrepaymentAmt(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        if (ZYPCommonFunc.hasValue(cpoBean.getPrePmtAmt()) && ZYPCommonFunc.hasValue(cpoBean.getOrdTotDealNetAmt())) {

            if (cpoBean.getPrePmtAmt().compareTo(cpoBean.getOrdTotDealNetAmt()) > 0) {
                String msgParam = "Order Total Aomunt is more than Prepayment Amount.";
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            }
        }
    }

    // 2016/02/19 S21_NA#1686 Add End

    // S21_NA#7942 ADD START
    /**
     * checkMinMaxPriceRange
     * 
     * <pre>
     * DI Check Code: E099-1210
     * Min Max Price Range Check
     * 
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkMinMaxPriceRange(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        // Get Price Calc Base
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(cpoBean, prcApiPMsg);
        for (int idx = 0; idx < cpoBean.getCpoDtlBeanList().size(); idx++) {
            NWZC155001CpoDtlBean cpoDtlBean = cpoBean.getCpoDtlBeanList().get(idx);
            // QC#22874
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()) //
                    || RTRN_LINE_STS.CLOSED.equals(cpoDtlBean.getRtrnLineStsCd())) {
                continue;
            }
            if (!CR_REBIL.CREDIT.equals(cpoDtlBean.getCrRebilCd()) && CONFIG_CATG.OUTBOUND.equals(cpoDtlBean.getConfigCatgCd())) {
                setOutboundLineParam(cpoBean, cpoDtlBean, prcApiPMsg);
            }
        }
        if (prcApiPMsg.NWZC157002PMsg.getValidCount() == 0) {
            return;
        }

        // Call Pricing API
        NWZC157001 pricingApi = new NWZC157001();
        pricingApi.execute(prcApiPMsg, this.onBatchType);
        ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseList = getOrdPrcCalcBaseManualPrice(cpoBean.getGlblCmpyCd(), cpoBean.getCpoOrdNum());

        // Get Price Calc Base
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {

            NWZC157002PMsg linePrc = prcApiPMsg.NWZC157002PMsg.no(i);
            NWZC155001CpoDtlBean cpoDtlBean //
            = getTargetCpoDtlBean(cpoBean, linePrc.trxLineNum.getValue(), linePrc.trxLineSubNum.getValue(), linePrc.configCatgCd.getValue());
            BigDecimal unitSellPrice = cpoDtlBean.getEntDealNetUnitPrcAmt();
            BigDecimal minPrice = null;
            BigDecimal maxPrice = null;
            for (int j = 0; j < linePrc.NWZC157003PMsg.getValidCount(); j++) {

                NWZC157003PMsg prcBase = linePrc.NWZC157003PMsg.no(j);
                if (PRC_DTL_GRP.BASE_PRICE.equals(prcBase.prcDtlGrpCd.getValue())) {
                    minPrice = prcBase.minPrcAmtRate.getValue();
                    maxPrice = prcBase.maxPrcAmtRate.getValue();
                }
            }
            if (!hasManualPrice(ordPrcCalcBaseList, cpoDtlBean)) {
                // Skip check Price
                continue;
            }
            // Check Price Ranges
            boolean chkRst = isRangePriceAmt(unitSellPrice, minPrice, maxPrice);
            if (!chkRst) {
                // set Error
                String msgParam = getMsgParamForCheckMinMaxPriceRange(unitSellPrice, minPrice, maxPrice);
                setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
            }
        }
    }

    // S21_NA#7942 ADD END

    /**
     * isSvcDealerOrd
     * @param glblCmpyCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpCd String
     * @param dealerCtxTpCd String
     * @return boolean
     */
    private boolean checkisOrdDealerCtxTp(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dealerCtxTpCd) {
        ORD_CATG_BIZ_CTXTMsg ordCatgBizCtxTMsg = new ORD_CATG_BIZ_CTXTMsg();

        ordCatgBizCtxTMsg.setSQLID("001");
        ordCatgBizCtxTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        ordCatgBizCtxTMsg.setConditionValue("ordCatgCtxTpCd01", dealerCtxTpCd);

        ORD_CATG_BIZ_CTXTMsgArray ordCatgBizCtxArry = (ORD_CATG_BIZ_CTXTMsgArray) S21ApiTBLAccessor.findByCondition(ordCatgBizCtxTMsg);

        for (int i = 0; i < ordCatgBizCtxArry.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(dsOrdCatgCd) && dsOrdCatgCd.equals(ordCatgBizCtxArry.no(i).dsOrdCatgCd.getValue())) {
                if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
                    if (dsOrdTpCd.equals(ordCatgBizCtxArry.no(i).dsOrdTpCd.getValue())) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * getAuthAndConfigInfo
     * @param cpoBean NWZC155001CpoBean
     * @return List<Map>
     */
    private List<Map> getAuthAndConfigInfo(NWZC155001CpoBean cpoBean) {
        Map<String, Object> key = new HashMap<String, Object>();

        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("flgY", ZYPConstant.FLG_ON_Y);
        key.put("flgN", ZYPConstant.FLG_OFF_N);
        key.put("dsAcctNum", cpoBean.getSellToCustCd());
        key.put("slsDt", cpoBean.getSlsDt());

        return (List<Map>) ssmClient.queryObjectList("getAuthAndConfigInfo", key);
    }

    /**
     * getMsgParamForDlrAuthValidChk
     * @param sellToCust String
     * @param mktMdlCd String
     * @return String
     */
    public String getMsgParamForDlrAuthValidChk(String sellToCust, String mktMdlCd) {

        StringBuilder msgParam = new StringBuilder();
        msgParam.append("This Customer Account ('").append(sellToCust);
        msgParam.append("') and Marketing Model ('").append(mktMdlCd);
        msgParam.append("') is not authorized as service dealer in Dealer Placing Order.");

        return msgParam.toString();
    }

    /**
     * setErrToDiRsltBean
     * @param cpoBean NWZC155001CpoBean
     * @param cpoDtlBean NWZC155001CpoDtlBean
     * @param diChkCd String
     * @param diChkLvlCd String
     * @param diChkRsltTpCd String
     * @param diChkErrTxt String
     */
    protected void setErrToDiRsltBean(NWZC155001CpoBean cpoBean, NWZC155001CpoDtlBean cpoDtlBean, String diChkCd, String diChkLvlCd, String diChkRsltTpCd, String diChkErrTxt) {
        List<NWZC155001DiResultBean> diRsltBeanList = cpoBean.getDiResultList();

        NWZC155001DiResultBean diRsltBean = new NWZC155001DiResultBean();
        diRsltBean.setDiChkCd(diChkCd);
        diRsltBean.setDiChkLvlCd(diChkLvlCd);
        diRsltBean.setDiChkDtlStsCd(diChkRsltTpCd);
        diRsltBean.setDiChkErrTxt(diChkErrTxt);

        if (cpoDtlBean != null) {
            diRsltBean.setCpoConfigPk(cpoDtlBean.getDsCpoConfigPk());
            diRsltBean.setConfigCatgCd(cpoDtlBean.getConfigCatgCd());
            diRsltBean.setDsOrdPosnNum(cpoDtlBean.getDsOrdPosnNum());
            diRsltBean.setCpoLineNum(cpoDtlBean.getDsCpoLineNum());
            diRsltBean.setCpoLineSubNum(cpoDtlBean.getDsCpoLineSubNum());
        }
        diRsltBeanList.add(diRsltBean);
        cpoBean.setDiResultList(diRsltBeanList);
        // cpoBean.setDiErrorFlg(ZYPConstant.FLG_ON_Y);v// 2016/03/02
        // S21_NA#5000 Del
    }

    // /**
    // * setAcceptToDiRsltBean
    // * @param cpoBean NWZC155001CpoBean
    // * @param acceptMap HashMap
    // */
    // // private void setAcceptToDiRsltBean(NWZC155001CpoBean
    // cpoBean,
    // // Map<String, String> acceptMap) {
    // private void setAcceptToDiRsltBean(NWZC155001CpoBean cpoBean,
    // Map<String, Object> acceptMap) { // S21_NA#3184
    // List<NWZC155001DiResultBean> diRsltBeanList =
    // cpoBean.getDiResultList();
    //
    // NWZC155001DiResultBean diRsltBean = new
    // NWZC155001DiResultBean();
    // diRsltBean.setDiChkCd((String) acceptMap.get("DI_CHK_CD"));
    // diRsltBean.setDiChkLvlCd((String)
    // acceptMap.get("DI_CHK_LVL_CD"));
    // diRsltBean.setDiChkDtlStsCd((String)
    // acceptMap.get("DI_CHK_DTL_STS_CD"));
    // diRsltBean.setDiChkErrTxt((String)
    // acceptMap.get("DI_CHK_ERR_TXT"));
    // diRsltBean.setConfigCatgCd((String)
    // acceptMap.get("DI_ERR_CONFIG_CATG_CD"));
    // diRsltBean.setDsOrdPosnNum((String)
    // acceptMap.get("DI_ERR_ORD_POSN_NUM"));
    // diRsltBean.setCpoLineNum((String)
    // acceptMap.get("DI_ERR_CPO_LINE_NUM"));
    // diRsltBean.setCpoLineSubNum((String)
    // acceptMap.get("DI_ERR_CPO_LINE_SUB_NUM"));
    // diRsltBean.setCpoConfigPk((BigDecimal)
    // acceptMap.get("DI_ERR_CPO_CONFIG_PK"));
    //
    // diRsltBeanList.add(diRsltBean);
    // cpoBean.setDiResultList(diRsltBeanList);
    // }

    /**
     * setErrorToDiRsltByDefineLine
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param diChkCd String
     * @param msgParam String
     * @param lineNum BigDecimal
     * @param lineSubNum BigDecimal
     * @param configCatg String
     */
    private void setErrorToDiRsltByDefineLine(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, String diChkCd, String msgParam, String posnNum, BigDecimal lineNum, BigDecimal lineSubNum, String configCatg) {
        if (ZYPCommonFunc.hasValue(lineSubNum)) {
            setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkCd, msgParam, posnNum, lineNum.toString(), lineSubNum.toString(), configCatg);
        } else {
            setErrorToDiRsltByDefineLine(cpoBean, diChkBean, diChkCd, msgParam, posnNum, lineNum.toString(), null, configCatg);
        }
    }

    /**
     * setErrorToDiRsltByDefineLine
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     * @param diChkCd String
     * @param msgParam String
     * @param lineNum String
     * @param lineSubNum String
     * @param configCatg String
     */
    private void setErrorToDiRsltByDefineLine(//
            NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, String diChkCd, String msgParam, String posnNum, String lineNum, String lineSubNum, String configCatg) {

        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {

            if (S21StringUtil.isEquals(cpoDtlBean.getDsOrdPosnNum(), posnNum) //
                    && S21StringUtil.isEquals(cpoDtlBean.getDsCpoLineNum(), lineNum) //
                    && S21StringUtil.isEquals(cpoDtlBean.getConfigCatgCd(), configCatg)) {

                if (ZYPCommonFunc.hasValue(cpoDtlBean.getDsCpoLineSubNum()) //
                        && !S21StringUtil.isEquals(cpoDtlBean.getDsCpoLineSubNum(), lineSubNum)) {
                    continue;
                }

                setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkCd, diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                break;
            }
        }
    }

    // QC#13364
    // /**
    // * setErrorToDiRsltByDefineConfig
    // * @param glblCmpyCd String
    // * @param configPk BigDecimal
    // */
    // private void setErrorToDiRsltByDefineConfig(NWZC155001CpoBean
    // cpoBean, NWZC155001DiChkBean diChkBean, String diChkCd, String
    // msgParam, BigDecimal configPk) {
    // if (!ZYPCommonFunc.hasValue(configPk)) {
    // return;
    // }
    //
    // for (NWZC155001CpoDtlBean cpoDtlBean :
    // cpoBean.getCpoDtlBeanList()) {
    // if (cpoDtlBean.getDsCpoConfigPk().compareTo(configPk) == 0) {
    // setErrToDiRsltBeanConfigLvl(cpoBean, diChkCd,
    // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
    // msgParam, configPk, cpoDtlBean.getConfigCatgCd(),
    // cpoDtlBean.getDsOrdPosnNum());
    // }
    // }
    // }

    /**
     * addDtlErrorListForConfigLvl
     * @param cpoBean NWZC155001CpoBean
     * @param diChkCd String
     * @param diChkLvlCd String
     * @param diChkRsltTpCd String
     * @param diChkErrTxt String
     * @param configPk BigDecimal
     * @param catgCd String
     * @param posnNum String
     */
    public void setErrToDiRsltBeanConfigLvl(NWZC155001CpoBean cpoBean, String diChkCd, String diChkLvlCd, String diChkRsltTpCd, String diChkErrTxt, BigDecimal configPk, String catgCd, String posnNum) {
        List<NWZC155001DiResultBean> diRsltBeanList = cpoBean.getDiResultList();
        NWZC155001DiResultBean diRsltBean = new NWZC155001DiResultBean();

        diRsltBean.setDiChkCd(diChkCd);
        diRsltBean.setDiChkLvlCd(diChkLvlCd);
        diRsltBean.setDiChkDtlStsCd(diChkRsltTpCd);
        diRsltBean.setDiChkErrTxt(diChkErrTxt);
        diRsltBean.setCpoConfigPk(configPk);
        diRsltBean.setConfigCatgCd(catgCd);
        diRsltBean.setDsOrdPosnNum(posnNum);

        diRsltBeanList.add(diRsltBean);
        cpoBean.setDiResultList(diRsltBeanList);
        // cpoBean.setDiErrorFlg(ZYPConstant.FLG_ON_Y); // 2016/03/02
        // S21_NA#5000 Del
    }

    /**
     * deleteZero
     * @param stNum String
     * @return String
     */
    private String deleteZero(String stNum) {
        if (ZYPCommonFunc.hasValue(stNum)) {
            return stNum.replaceFirst("^0+", "");
        } else {
            return "";
        }
    }

    // 2016/03/02 S21_NA#5000 Add Start
    /**
     * isErrorDiCheck
     * @param cpoBean NWZC155001CpoBean
     * @return boolean
     */
    private boolean isDiCheckError(NWZC155001CpoBean cpoBean) {
        List<NWZC155001DiResultBean> diRsltBeanList = cpoBean.getDiResultList();

        for (NWZC155001DiResultBean diRsltBean : diRsltBeanList) {
            if (DI_CHK_DTL_STS.ERROR.equals(diRsltBean.getDiChkDtlStsCd()) || DI_CHK_DTL_STS.WARNING.equals(diRsltBean.getDiChkDtlStsCd())) {
                return true;
            }
        }
        return false;
    }

    // 2016/03/02 S21_NA#5000 Add End

    // QC#4657 Add start
    private void checkHeaderFieldRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        doProcessMandatoryCheck(cpoBean, diChkBean);
    }

    private void checkLineFieldRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        doProcessMandatoryCheck(cpoBean, diChkBean);
    }

    private void doProcessMandatoryCheck(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        new NWZC155001MandatoryCheck().doProcessCheck(cpoBean, diChkBean, msgMap);
    }

    // QC#4657 end

    // QC#5383 Add Start
    /**
     * getOrdTpCtacReln
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @return S21SsmEZDResult
     */
    public List<Map<String, Object>> getOrdTpCtacReln(NWZC155001CpoBean cpoBean) {
        Map<String, Object> ordTpCtacRelnKey = new HashMap<String, Object>();
        ordTpCtacRelnKey.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ordTpCtacRelnKey.put("ordNum", cpoBean.getCpoOrdNum());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdTpCtacReln", ordTpCtacRelnKey);
    }

    /**
     * getCtacValidationConfig
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @return S21SsmEZDResult
     */
    // 2019/12/25 QC#54229 Mod Start
    // public List<Map<String, Object>>
    // getCtacValidationConfig(NWZC155001CpoBean cpoBean) {
    public List<Map<String, Object>> getCtacValidationConfig(NWZC155001CpoBean cpoBean, String configCatg) {
        // 2019/12/25 QC#54229 Mod End
        Map<String, Object> ctacValidationConfigKey = new HashMap<String, Object>();
        ctacValidationConfigKey.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ctacValidationConfigKey.put("ordNum", cpoBean.getCpoOrdNum());
        ctacValidationConfigKey.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
        // START 2023/01/05 R.Azucena [QC#60974 ADD]
        ctacValidationConfigKey.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2023/01/05 R.Azucena [QC#60974 ADD]
        // 2019/12/25 QC#54229 Add Start
        if (CONFIG_CATG.INBOUND.equals(configCatg)) {
            ctacValidationConfigKey.put("rmaFlg", ZYPConstant.FLG_ON_Y); // QC#22874
        }
        // 2019/12/25 QC#54229 Add End

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getCtacValidationConfig", ctacValidationConfigKey);
    }

    // QC#5383 Add End

    // S21_NA#8388 START
    /**
     * isDeclineSvc
     * 
     * <pre>
     * @param dclnSvcCd String
     * @return boolean
     */
    public boolean isDeclineSvc(String dclnSvcCd) {

        if (ZYPConstant.FLG_ON_1.equals(dclnSvcCd)) {
            return true;
        }
        return false;
    }

    /**
     * isDeclineSvc
     * 
     * <pre>
     * @param glblCmpyCd String
     * @param ordNum String
     * @return boolean
     */
    private boolean isDeclineSvc(NWZC155001CpoBean cpoBean) {

        if (isDeclineSvc(cpoBean.getDclnSvcCd())) {
            return true;
        }
        return false;
    }

    /**
     * isDeclineSvcCofig
     * 
     * <pre>
     * @param glblCmpyCd String
     * @param ordNum String
     * @param ordLineNum String
     * @return boolean
     */
    private boolean isDeclineSvcCofig(String glblCmpyCd, String ordNum, String ordLineNum) {

        Map<String, Object> getDclnSvcKey = new HashMap<String, Object>();
        getDclnSvcKey.put("glblCmpyCd", glblCmpyCd);
        getDclnSvcKey.put("cpoOrdNum", ordNum);
        getDclnSvcKey.put("cpoDtlLineNum", ordLineNum);
        getDclnSvcKey.put("configCatgCdOut", CONFIG_CATG.OUTBOUND);

        String dclnSvcCd = (String) ssmClient.queryObject("getDclnSvcCdInConfig", getDclnSvcKey);

        if (dclnSvcCd != null && isDeclineSvc(dclnSvcCd)) {
            return true;
        }
        return false;
    }

    // S21_NA#8388 END

    // S21_NA#7942 ADD START
    /**
     * isRangePriceAmt
     * 
     * <pre>
     * @param basePrice BigDecimal
     * @param discount BigDecimal
     * @param minPrice BigDecimal
     * @param maxPrice BigDecimal
     * @return boolean
     */
    private boolean isRangePriceAmt(BigDecimal unitSellPrice, BigDecimal minPrice, BigDecimal maxPrice) {

        if (!ZYPCommonFunc.hasValue(unitSellPrice)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(minPrice)) {
            if (unitSellPrice.compareTo(minPrice) < 0) {
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(maxPrice)) {
            if (unitSellPrice.compareTo(maxPrice) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * getTargetCpoDtlBean
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param lineNum String
     * @param lineSubNum String
     * @param configCatgCd String
     * @return NWZC155001CpoDtlBean
     */
    private NWZC155001CpoDtlBean getTargetCpoDtlBean(//
            NWZC155001CpoBean cpoBean, String lineNum, String lineSubNum, String configCatgCd) {

        for (int idx = 0; idx < cpoBean.getCpoDtlBeanList().size(); idx++) {
            NWZC155001CpoDtlBean cpoDtlBean = cpoBean.getCpoDtlBeanList().get(idx);
            if (cpoDtlBean.getCpoDtlLineNum().equals(lineNum) //
                    && cpoDtlBean.getCpoDtlLineSubNum().equals(lineSubNum) //
                    && cpoDtlBean.getConfigCatgCd().equals(configCatgCd)) {
                return cpoDtlBean;
            }
        }
        return null;
    }

    /**
     * getMsgParamForCheckMinMaxPriceRange
     * 
     * <pre>
     * @param basePrice BigDecimal
     * @param discount BigDecimal
     * @param minPrice BigDecimal
     * @param maxPrice BigDecimal
     * @return String
     */
    private String getMsgParamForCheckMinMaxPriceRange(BigDecimal unitSellPrice, BigDecimal minPrice, BigDecimal maxPrice) {

        // Unit Net Sell Price $$ is out of range of $$(min) and
        // $$(max)
        StringBuilder msgParam = new StringBuilder();
        msgParam.append("Unit Net Sell Price $");
        msgParam.append(unitSellPrice);
        msgParam.append(" is out of range of $");
        if (ZYPCommonFunc.hasValue(minPrice)) {
            msgParam.append(minPrice);
        } else {
            msgParam.append("0");
        }
        msgParam.append(" and $");
        if (ZYPCommonFunc.hasValue(maxPrice)) {
            msgParam.append(maxPrice);
        } else {
            msgParam.append("9,999,999,999,999.99");
        }
        msgParam.append(".");
        return msgParam.toString();
    }

    /**
     * getOrdPrcCalcBaseManualPrice
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @return ORD_PRC_CALC_BASETMsgArray
     */
    private ORD_PRC_CALC_BASETMsgArray getOrdPrcCalcBaseManualPrice(String glblCmpyCd, String cpoOrdNum) {

        ORD_PRC_CALC_BASETMsg tMsg = new ORD_PRC_CALC_BASETMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        tMsg.setConditionValue("prcCondTpCd01", PRC_COND_TP.MANUAL_PRICE);

        return (ORD_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    /**
     * hasManualPrice
     * @param ordPrcCalcBaseList ORD_PRC_CALC_BASETMsgArray
     * @param cpoDtlBean NWZC155001CpoDtlBean
     * @return boolean
     */
    private boolean hasManualPrice(ORD_PRC_CALC_BASETMsgArray ordPrcCalcBaseList, NWZC155001CpoDtlBean cpoDtlBean) {

        if (ordPrcCalcBaseList == null || ordPrcCalcBaseList.getValidCount() == 0) {
            return false;
        }

        for (int i = 0; i < ordPrcCalcBaseList.getValidCount(); i++) {
            ORD_PRC_CALC_BASETMsg ordPrcCalcBase = ordPrcCalcBaseList.no(i);
            if (ordPrcCalcBase.cpoDtlLineNum.getValue().equals(cpoDtlBean.getCpoDtlLineNum()) //
                    && ordPrcCalcBase.cpoDtlLineSubNum.getValue().equals(cpoDtlBean.getCpoDtlLineSubNum())) {
                return true;
            }
        }
        return false;
    }

    // S21_NA#7942 ADD END

    // mod start 2016/09/07 CSA QC#11774
    /**
     * Service Shell Validation Check
     * 
     * <pre>
     * DI Check Code: E099-1200
     * Service Shell Validation Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkServiceShellValid(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        // select Check Inf
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        key.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874
        // 2019/05/07 QC#50174 Add Start
        key.put("ordBookFlg", cpoBean.getOrdBookFlg());
        // 2019/05/07 QC#50174 Add End
        List<Map<String, Object>> rsLst //
        = (List<Map<String, Object>>) ssmClient.queryObjectList("getSvcShllInf", key);

        // 2018/10/12 QC#28370 Add Start
        List<String> keyList = new ArrayList<String>();
        // 2018/10/12 QC#28370 Add End
        for (Map<String, Object> rs : rsLst) {
            // 2018/10/12 QC#28370 Add Start

            // START 2021/03/31 A.Marte [QC#58292, ADD]
            // Check if there is Model ID on shell
            if (!ZYPCommonFunc.hasValue((BigDecimal) rs.get("MDL_ID"))) {
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Model ID missing for Shell #").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Model ").append((String) rs.get("T_MDL_NM"));
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                continue;
            }
            // END 2021/03/31 A.Marte [QC#58292, ADD]

            StringBuilder keyString = new StringBuilder();
            keyString.append((BigDecimal) rs.get("SHELL_LINE_NUM")).append(",").append(rs.get("MDL_ID"));
            if (keyList.contains(keyString.toString())) {
                continue;
            }
            // 2018/10/12 QC#28370 Add End

            // 2020/01/27 QC#55492 Del Start
            // QC#15652 2016/11/05 Add Start
            // if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y,
            // (String) rs.get("ADD_ASRY_FLG"))) {
            // continue;
            // }
            // QC#15652 2016/11/05 Add End
            // 2020/01/27 QC#55492 Del End

            // QC#16479 2016/12/13 Add Start
            if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, (String) rs.get("MAN_CONTR_OVRD_FLG"))) {
                continue;
            }
            // QC#16479 2016/12/13 Add Start

            // QC#22092 2017/10/27 Add Start
            if (S21StringUtil.isEquals(SVC_MACH_MSTR_STS.INSTALLED, (String) rs.get("SVC_MACH_MSTR_STS_CD")) || S21StringUtil.isEquals(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION, (String) rs.get("SVC_MACH_MSTR_STS_CD"))
                    || S21StringUtil.isEquals(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL, (String) rs.get("SVC_MACH_MSTR_STS_CD"))) {
                continue;
            }
            // QC#22092 2017/10/27 Add End

            // QC#29248
            boolean hasBillableCounter //
            = NWXC150001DsCheck.hasBillableCounter(cpoBean.getGlblCmpyCd(), (BigDecimal) rs.get("MDL_ID"), (BigDecimal) rs.get("PRC_MTR_PKG_PK"), cpoBean.getSlsDt());
            if (hasBillableCounter && !ZYPCommonFunc.hasValue((String) rs.get("MTR_READ_METH_CD"))) {
                if (ZYPCommonFunc.hasValue((String) rs.get("MTR_BLLG_CYCLE_CD"))) {
                    StringBuilder msgParamUsg = new StringBuilder();
                    msgParamUsg.append("Meter Read Method is mandatory #").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Model ").append((String) rs.get("T_MDL_NM"));
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParamUsg.toString());
                    keyList.add(keyString.toString());
                }
            }

            // not Fleet
            if (!DS_CONTR_CATG.FLEET.equals((String) rs.get("DS_CONTR_CATG_CD"))) {
                Map<String, Object> prm = new HashMap<String, Object>();
                prm.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                prm.put("cpoOrdNum", cpoBean.getCpoOrdNum());
                prm.put("dsContrDtlPk", rs.get("DS_CONTR_DTL_PK"));
                prm.put("dsContrPk", rs.get("DS_CONTR_PK"));
                prm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
                prm.put("mdlId", rs.get("MDL_ID")); // QC#18843
                // 2017/06/07

                if ((Integer) ssmClient.queryObject("cntCpoSvcPrc", prm) < 1) {
                    StringBuilder msgParam = new StringBuilder();
                    msgParam.append("Service Pricing missing for Shell #").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Model ").append((String) rs.get("T_MDL_NM"));
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                    // 2018/10/12 QC#28370 Add Start
                    keyList.add(keyString.toString());
                    // 2018/10/12 QC#28370 Add End
                }

                // Usage Billing is not null
                // QC#18017 2017/03/15 Add Start
                // if (ZYPCommonFunc.hasValue((String)
                // rs.get("USG_BLLG_CYCLE_CD"))) {
                String mtrReqMdlFlg = (String) ssmClient.queryObject("getMtrReqMdlFlg", prm);
                if (hasBillableCounter // QC#29248
                        && ZYPCommonFunc.hasValue((String) rs.get("MTR_BLLG_CYCLE_CD")) && ZYPConstant.FLG_ON_Y.equals(mtrReqMdlFlg)) {
                    // QC#18017 2017/03/15 Add End
                    Map<String, Object> prmUsg = new HashMap<String, Object>();
                    prmUsg.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                    // prmUsg.put("dsContrDtlPk",
                    // rs.get("DS_CONTR_DTL_PK"));
                    prmUsg.put("dsContrPk", rs.get("DS_CONTR_PK"));
                    prmUsg.put("mdlId", rs.get("MDL_ID"));
                    // Del Start 2017/08/15 QC#18193
                    // // Add Start 2017/06/08 QC#18936
                    // if (DS_CONTR_CATG.AGGREGATE.equals((String)
                    // rs.get("DS_CONTR_CATG_CD"))) {
                    // prmUsg.put("aggrFlg", ZYPConstant.FLG_ON_Y);
                    // }
                    // // Add End 2017/06/08 QC#18936
                    // Del End 2017/08/15 QC#18193
                    if ((Integer) ssmClient.queryObject("cntCpoSvcUsgPrc", prmUsg) < 1) {
                        // QC#58846
                        if (ZYPCommonFunc.hasValue((BigDecimal) rs.get("CNTR_MDL_ID")) && ZYPConstant.FLG_ON_Y.equals(cpoBean.getOrdBookFlg()) && !rs.get("MDL_ID").equals(rs.get("CNTR_MDL_ID"))) {
                            continue;
                        }
                        StringBuilder msgParamUsg = new StringBuilder();
                        msgParamUsg.append("Service Usage Pricing missing for Shell #").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Model ").append((String) rs.get("T_MDL_NM"));
                        setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParamUsg.toString());
                        // 2018/10/12 QC#28370 Add Start
                        keyList.add(keyString.toString());
                        // 2018/10/12 QC#28370 Add End
                    }
                }
                // Fleet
            } else if (DS_CONTR_CATG.FLEET.equals((String) rs.get("DS_CONTR_CATG_CD"))) {
                Map<String, Object> prm = new HashMap<String, Object>();
                prm.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                prm.put("cpoOrdNum", cpoBean.getCpoOrdNum());
                prm.put("dsContrPk", rs.get("DS_CONTR_PK"));
                // prm.put("dsContrDtlPk", rs.get("DS_CONTR_DTL_PK"));
                prm.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
                prm.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
                prm.put("mdlId", rs.get("MDL_ID")); // QC#18843
                // 2017/06/07

                if ((Integer) ssmClient.queryObject("cntFleetCpoSvcPrc", prm) < 1) {
                    StringBuilder msgParamFlt = new StringBuilder();
                    msgParamFlt.append("Service Pricing missing for Shell #").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Fleet");
                    setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParamFlt.toString());
                    // 2018/10/12 QC#28370 Add Start
                    keyList.add(keyString.toString());
                    // 2018/10/12 QC#28370 Add End
                }

                // Usage Billing is not null
                // QC#18017 2017/03/15 Add Start
                // if (ZYPCommonFunc.hasValue((String)
                // rs.get("USG_BLLG_CYCLE_CD"))) {
                String mtrReqMdlFlg = (String) ssmClient.queryObject("getMtrReqMdlFlg", prm);
                if (hasBillableCounter // QC#29248
                        && ZYPCommonFunc.hasValue((String) rs.get("MTR_BLLG_CYCLE_CD")) && ZYPConstant.FLG_ON_Y.equals(mtrReqMdlFlg)) {
                    // QC#18017 2017/03/15 Add End
                    Map<String, Object> prmFltUsg = new HashMap<String, Object>();
                    prmFltUsg.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
                    prmFltUsg.put("cpoOrdNum", cpoBean.getCpoOrdNum());
                    // QC#15509
                    // prmFltUsg.put("dsContrDtlPk", (BigDecimal)
                    // rs.get("DS_CONTR_DTL_PK"));
                    prmFltUsg.put("dsContrPk", (BigDecimal) rs.get("DS_CONTR_PK"));
                    // Mod Start 2017/08/15 QC#18193
                    // prmFltUsg.put("dsContrCatgCd",
                    // DS_CONTR_CATG.FLEET);
                    // if ((Integer)
                    // ssmClient.queryObject("cntFltCpoSvcUsgPrc",
                    // prmFltUsg) >= 1) {
                    if ((Integer) ssmClient.queryObject("cntFltCpoSvcUsgPrc", prmFltUsg) < 1) {
                        // Mod End 2017/08/15 QC#18193
                        StringBuilder msgParamUsg = new StringBuilder();
                        msgParamUsg.append("Service Usage Pricing missing for Shell #").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Model ").append((String) rs.get("T_MDL_NM"));
                        setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParamUsg.toString());
                        // 2018/10/12 QC#28370 Add Start
                        keyList.add(keyString.toString());
                        // 2018/10/12 QC#28370 Add End
                    }
                }
            }
        }
    }

    // QC#29248
    private boolean hasBillableCounter(String glblCmpyCd, BigDecimal prcMtrPkgPk, String slsDt) {
        if (!ZYPCommonFunc.hasValue(prcMtrPkgPk)) {
            return false;
        }
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("prcMtrPkgPk", prcMtrPkgPk);
        prm.put("slsDt", slsDt);
        return ((Integer) ssmClient.queryObject("cntBillableCounter", prm) > 0);
    }

    // // QC#29248
    // private boolean isMeteredModel(Map<String, Object> rs) {
    // if (ZYPConstant.FLG_ON_Y.equals((String)
    // rs.get("ADD_ASRY_FLG"))) {
    // return false;
    // }
    // return ZYPCommonFunc.hasValue((BigDecimal)
    // rs.get("MTR_GRP_PK"));
    // }

    // mod end 2016/09/07 CSA QC#11774
    // QC#24245 2018/06/13 Add Start
    public void checkServiceShellValidByConfig(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        key.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED });
        List<Map<String, Object>> rsLst = (List<Map<String, Object>>) ssmClient.queryObjectList("getSvcShllInfobyConfig", key);
        for (Map<String, Object> rs : rsLst) {
            StringBuilder msgParam = new StringBuilder();
            msgParam.append("There is a discrepancy between Shell Config and Order Config, Shell#").append(String.valueOf((BigDecimal) rs.get("SHELL_LINE_NUM"))).append(" - Model ").append((String) rs.get("T_MDL_NM"));
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
        }

    }

    // QC#24245 2018/06/13 Add End

    // 2017/01/18 S21_NA#17138 Add Start
    private boolean isProfitUsgReason(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TP_PROC_DFNTMsg dfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dfnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dfnTMsg);
        if (dfnTMsg == null) {
            return false;
        }
        String prftApvlNodeUsgFlg = dfnTMsg.prftApvlNodeUsgFlg.getValue();
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, prftApvlNodeUsgFlg)) {
            return true;
        } else {
            return false;
        }
    }

    // 2017/01/18 S21_NA#17138 Add End

    // Add Start S21_NA#15510
    /**
     * Check EMSD Sales Rep
     * 
     * <pre>
     * DI Check Code: E099-1220
     * Check EMSD Sales Rep of Config Level
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkEmsdSalesRep(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        if (!isEmsdOrder(cpoBean)) {
            return;
        }

        // get Non EMSD Sales Rep for Config
        List<Map<String, Object>> nonEmsdSalesRepList = getNonEmsdSalesRepList(cpoBean);

        for (Map nonEmsdSalesRepMap : nonEmsdSalesRepList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) nonEmsdSalesRepMap.get("DS_CPO_CONFIG_PK");
            String dsOrdPosnNum = (String) nonEmsdSalesRepMap.get("DS_ORD_POSN_NUM");

            setErrToDiRsltBeanConfigLvl(//
                    cpoBean //
                    , diChkBean.getDiChckCd() //
                    , diChkBean.getDiChkLvlCd() //
                    , diChkBean.getDiChkRsltTpCd() //
                    , EMSD_SLS_REP_MSGPARAM, dsCpoConfigPk, CONFIG_CATG.OUTBOUND, dsOrdPosnNum);
            continue;
        }
    }

    /**
     * Check EMSD Order
     * @param cpoBean NWZC155001CpoBean
     * @return true : EMSD Order
     */
    private boolean isEmsdOrder(NWZC155001CpoBean cpoBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("ordNum", cpoBean.getCpoOrdNum());
        ssmParam.put("ordCatgCtxTp", ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET);

        if ((Integer) ssmClient.queryObject("cntEmsdOrder", ssmParam) > 0) {
            return true;
        }

        return false;
    }

    /**
     * Get Non EMSD Sales Rep List
     * @param cpoBean NWZC155001CpoBean
     * @return Non EMSD Sales Rep List
     */
    private List<Map<String, Object>> getNonEmsdSalesRepList(NWZC155001CpoBean cpoBean) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("ordNum", cpoBean.getCpoOrdNum());
        ssmParam.put("outBound", CONFIG_CATG.OUTBOUND);
        ssmParam.put("emsd", LINE_BIZ_ROLE_TP.EMSD);
        ssmParam.put("ordLineStsList", new String[] {ORD_LINE_STS.CANCELLED, ORD_LINE_STS.CLOSED }); // QC#22874

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getNonEmsdSalesRepList", ssmParam);
    }

    // Add End S21_NA#15510

    /**
     * Call Order Display Status Update API
     * @param cpoBean NWZC155001CpoBean
     */
    private void callOrderStatusUpdateApi(NWZC155001CpoBean cpoBean) {

        NWZC188001PMsg pMsg = new NWZC188001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, cpoBean.getCpoOrdNum());

        NWZC188001 odrStsUpdApi = new NWZC188001();

        odrStsUpdApi.execute(pMsg, this.onBatchType);

        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
    }

    // 2017/07/18 S21_NA#19983 Add Start
    private boolean isLeaseOrder(String glblCmpyCd, String cpoOrdNum) {

        CPOTMsg cpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, cpoOrdNum);

        cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(cpoTMsg);
        if (cpoTMsg == null) {
            return false;
        }

        String dsOrdCatgCd = cpoTMsg.dsOrdCatgCd.getValue();
        String dsOrdTpCd = cpoTMsg.dsOrdTpCd.getValue();
        String dsOrdRsnCd = cpoTMsg.dsOrdRsnCd.getValue();

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LEASE_ORDER_VALUE_SET);
        if (ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            ssmParam.put("dsOrdCatgCd", dsOrdCatgCd);
        }
        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        }
        if (ZYPCommonFunc.hasValue(dsOrdRsnCd)) {
            ssmParam.put("dsOrdRsnCd", dsOrdRsnCd);
        }

        Integer rsltCnt = (Integer) ssmClient.queryObject("isExistOrdCatg", ssmParam);

        if (rsltCnt == null) {
            return false;
        }
        int rslt = Integer.valueOf(rsltCnt);
        if (rslt == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * getPoNumByLocCdFromSoldTo
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     */
    private Map getPoNumByLocCdFromSoldTo(String glblCmpyCd, String cpoOrdNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (Map) ssmClient.queryObject("getPoNumByLocCdFromSoldTo", ssmParam);
    }

    /**
     * getPoNumBySoldToAcctCd
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     */
    private Map getPoNumBySoldToAcctCd(String glblCmpyCd, String cpoOrdNum) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoOrdNum", cpoOrdNum);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (Map) ssmClient.queryObject("getPoNumBySoldToAcctCd", ssmParam);
    }

    // 2017/07/18 S21_NA#19983 Add Start

    // 2017/08/03 S21_NA#20374 Add Start
    /**
     * checkServiceShellCustmerRelation
     * 
     * <pre>
     * DI Check Code: E099-1025A
     * Service Shell Customer and Order Customer Relation Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkServiceShellCustmerRelation(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        List<Map<String, Object>> resList = getOrdCustAndShellCust(cpoBean);
        String glblCmpyCd = cpoBean.getGlblCmpyCd();

        for (Map resMap : resList) {

            String billToCustCd = (String) resMap.get("ALT_PAYER_CUST_CD");
            String soldToCustCd = (String) resMap.get("SELL_TO_CUST_CD");
            String shipToCustAcctCd = (String) resMap.get("SHIP_TO_CUST_ACCT_CD");
            // Add Start 2018/02/26 QC#22967
            String soldToCustLocCd = (String) resMap.get("SOLD_TO_CUST_LOC_CD");
            // Add End 2018/02/26 QC#22967

            // Mod Start 2019/04/17 QC#31181
            // Mod Start 2018/02/26 QC#22967
            boolean checkErrFlg = checkBillShipSoldRelation(glblCmpyCd, billToCustCd, soldToCustCd, shipToCustAcctCd);
            // boolean checkErrFlg =
            // checkBillShipSoldRelation(glblCmpyCd, billToCustCd,
            // soldToCustCd, shipToCustAcctCd, soldToCustLocCd);
            // Mod End 2018/02/26 QC#22967
            // Mod End 2019/04/17 QC#31181

            if (checkErrFlg) {
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("Relation between Order Customer and Service Customer are not correct. Shell#:").append(resMap.get("SHELL_LINE_NUM")).append(" Line#:").append(resMap.get("DS_ORD_POSN_NUM")).append(".").append(
                        resMap.get("DS_CPO_LINE_NUM"));

                setErrToDiRsltBean(cpoBean, getErrDtlBean(cpoBean, resMap), diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
            }
        }
    }

    /**
     * getOrdCustAndShellCust
     * @param cpoBean NWZC155001CpoBean
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getOrdCustAndShellCust(NWZC155001CpoBean cpoBean) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());

        return (List<Map<String, Object>>) ssmClient.queryObjectList("getOrdCustAndShellCust", ssmParam);
    }

    // Mod Start 2019/04/17 QC#31181
    // Mod Start 2018/02/26 QC#22967
    /**
     * checkBillShipSoldRelation
     * @param cpoBean NWZC155001CpoBean
     * @return boolean if error then return true.
     */
    private boolean checkBillShipSoldRelation(String glblCmpyCd, String billToCustCd, String soldToCustCd, String shipToCustAcctCd) {
        // /**
        // * checkBillShipSoldRelation
        // * @param glblCmpyCd String
        // * @param billToCustCd String
        // * @param soldToCustCd String
        // * @param shipToCustAcctCd String
        // * @param soldToCustLocCd String
        // * @return boolean
        // */
        // private boolean checkBillShipSoldRelation(String
        // glblCmpyCd, String billToCustCd, String soldToCustCd,
        // String shipToCustAcctCd, String soldToCustLocCd) {
        // // Mod End 2018/02/26 QC#22967
        // Mod End 2019/04/17 QC#31181

        // Del Start 2019/04/17 QC#31181
        // // Add Start 2018/02/26 QC#22967
        // boolean result = false;
        // // Check Sold To(Customer Code) <- Ship To(Account Number)
        // // relation.
        // result = callCustInfoGetApiForCheckRelation(glblCmpyCd,
        // soldToCustLocCd, shipToCustAcctCd);

        // if (result) {
        // return result;
        // }

        // // Check Bill To(Customer Code) <- Sold To(Account Number)
        // // relation.
        // result = callCustInfoGetApiForCheckRelation(glblCmpyCd,
        // billToCustCd, soldToCustCd);

        // return result;
        // // Add End 2018/02/26 QC#22967
        // Del End 2019/04/17 QC#31181

        // Add Start 2019/04/17 QC#31181
        // Del Start 2018/02/26 QC#22967
        NMZC610001PMsg custInfoGetApiMsg = callCustInfoGetApi(glblCmpyCd, billToCustCd, soldToCustCd, shipToCustAcctCd);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {

            for (int ix = 0; ix < custInfoGetApiMsg.xxMsgIdList.getValidCount(); ix++) {

                EZDDebugOutput.println(1, S21MessageFunc.clspGetMessage(custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgId.getValue(), new String[] {custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_0.getValue(),
                        custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue(), custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue() }), NWZC155001.class);
            }
            return true;
        }
        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);

            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {

                return true;
            }
        }
        return false;
        // Del End 2018/02/26 QC#22967
        // Add End 2019/04/17 QC#31181
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param pMsg NWZC185001PMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param errMsgId String
     * @return boolean
     */
    private boolean callCustInfoGetApiForCheckRelation(String glblCmpyCd, String billToCustCd, String acctNum) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                glblCmpyCd, billToCustCd, acctNum, this.onBatchType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            return true;
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                return true;
            }
        }

        return false;
    }

    // Add End 2018/02/26 QC#22967

    // Add Start 2019/04/19 QC#31181
    // Del Start 2018/02/26 QC#22967
    /**
     * callCustInfoGetApi
     * @param cpoBean NWZC155001CpoBean
     * @return NMZC610001PMsg.
     */
    private NMZC610001PMsg callCustInfoGetApi(String glblCmpyCd, String billToCustCd, String soldToCustCd, String shipToCustAcctCd) {
        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();

        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);

        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, soldToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I2, shipToCustAcctCd);

        NMZC610001 nmzc610001 = new NMZC610001();

        nmzc610001.execute(custInfoGetApiMsg, this.onBatchType);

        return custInfoGetApiMsg;
    }

    // Del End 2018/02/26 QC#22967
    // Add End 2019/04/19 QC#31181

    /**
     * getErrDtlBean
     * @param cpoBean NWZC155001CpoBean
     * @param shellMap Map
     * @return NWZC155001CpoDtlBean
     */
    public NWZC155001CpoDtlBean getErrDtlBean(NWZC155001CpoBean cpoBean, Map shellMap) {

        for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
            String dsOrdPosnNum_1 = cpoDtlBean.getDsOrdPosnNum() == null ? "" : cpoDtlBean.getDsOrdPosnNum();
            String dsCpoLineNum_1 = cpoDtlBean.getDsCpoLineNum() == null ? "" : cpoDtlBean.getDsCpoLineNum();
            String dsCpoLineSubNum_1 = cpoDtlBean.getDsCpoLineSubNum() == null ? "" : cpoDtlBean.getDsCpoLineSubNum();
            String dsOrdPosnNum_2 = shellMap.get("DS_ORD_POSN_NUM") == null ? "" : shellMap.get("DS_ORD_POSN_NUM").toString();
            String dsCpoLineNum_2 = shellMap.get("DS_CPO_LINE_NUM") == null ? "" : shellMap.get("DS_CPO_LINE_NUM").toString();
            String dsCpoLineSubNum_2 = shellMap.get("DS_CPO_LINE_SUB_NUM") == null ? "" : shellMap.get("DS_CPO_LINE_SUB_NUM").toString();

            if (dsCpoLineNum_1.equals(dsCpoLineNum_2) && dsCpoLineSubNum_1.equals(dsCpoLineSubNum_2) && dsOrdPosnNum_1.equals(dsOrdPosnNum_2)) {
                return cpoDtlBean;
            }
        }
        return null;
    }

    // 2017/08/03 S21_NA#20374 Add End

    // 2017/08/22 S21_NA#20022 Add Start
    /**
     * checkAutoAddSplyItem
     * 
     * <pre>
     * DI Check Code: E099-1230
     * Service Shell Customer and Order Customer Relation Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkAutoAddSplyItem(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        final DS_CPO_CONFIGTMsg tMsg = new DS_CPO_CONFIGTMsg();
        // 2017/09/13 S21_NA#21057 Mod Start
        // tMsg.setSQLID("001");
        tMsg.setSQLID("005");
        // 2017/09/13 S21_NA#21057 Mod End
        tMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        tMsg.setConditionValue("cpoOrdNum01", cpoBean.getCpoOrdNum());
        // 2017/09/13 S21_NA#21057 Add End
        tMsg.setConditionValue("configCatgCd01", CONFIG_CATG.OUTBOUND);
        // 2017/09/13 S21_NA#21057 Add End
        DS_CPO_CONFIGTMsgArray tMsgResult = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        for (int i = 0; i < tMsgResult.length(); i++) {

            DS_CPO_CONFIGTMsg confTMsg = tMsgResult.no(i);

            // 2017/10/11 S21_NA#21469 Add Start
            String configTpCd = (String) confTMsg.configTpCd.getValue();
            if (CONFIG_TP.TO_SALES_CONVERSION.equals(configTpCd) || CONFIG_TP.ADD_TO_CONFIG.equals(configTpCd)) {
                continue;
            }
            // 2017/10/11 S21_NA#21469 Add End

            NWZC181001PMsg autoAddSplyPMsg = callAutoAddSplyApi(cpoBean, confTMsg);

            if (autoAddSplyPMsg != null && autoAddSplyPMsg.NWZC181002PMsg != null) {
                List<String> errMsgList = compareAutoAddSply(cpoBean, autoAddSplyPMsg.NWZC181002PMsg, confTMsg.dsOrdPosnNum.getValue());

                for (String errMsg : errMsgList) {
                    setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), errMsg, confTMsg.dsCpoConfigPk.getValue(), confTMsg.configCatgCd.getValue(), confTMsg.dsOrdPosnNum
                            .getValue());
                }
            }
        }
    }

    /**
     * Call NWZC1810 Auto Add Supply API
     * @param cpoBean NWZC155001CpoBean
     * @param confTMsg DS_CPO_CONFIGTMsg
     * @return NWZC181001PMsg
     */
    public NWZC181001PMsg callAutoAddSplyApi(NWZC155001CpoBean cpoBean, DS_CPO_CONFIGTMsg confTMsg) {

        NWZC181001PMsg pMsg = new NWZC181001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, cpoBean.getSlsDt());
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NWZC181001Constant.PROC_MD_ODR_ENT);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, cpoBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, cpoBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(pMsg.mdlId, confTMsg.mdlId.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.dsCpoConfigPk, confTMsg.dsCpoConfigPk.getValue());

        // call NWZC181001 Auto Add Supply API
        new NWZC181001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            EZDDebugOutput.println(1, S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue()), NWZC155001.class);
            return null;
        }

        return pMsg;
    }

    /**
     * compareAutoAddSply
     * @param cpoBean NWZC155001CpoBean
     * @param splyItemList NWZC181002PMsgArray
     * @param dsOrdPosnNum String
     * @return boolean
     */
    public List<String> compareAutoAddSply(NWZC155001CpoBean cpoBean, NWZC181002PMsgArray splyItemList, String dsOrdPosnNum) {

        List<String> errMsgList = new ArrayList();
        StringBuilder errMsg = null;
        boolean compareFlg;

        for (int i = 0; i < splyItemList.getValidCount(); i++) {
            String autoAddSplyMdseCd = splyItemList.no(i).mdseCd.getValue();
            BigDecimal autoAddSplyQty = splyItemList.no(i).ordQty.getValue();
            String autoAddSplyLineCatgCd = splyItemList.no(i).dsOrdLineCatgCd.getValue();

            if (autoAddSplyMdseCd.length() > 8) {
                autoAddSplyMdseCd = autoAddSplyMdseCd.substring(0, 8);
            }

            compareFlg = false;
            for (NWZC155001CpoDtlBean dtlBean : cpoBean.getCpoDtlBeanList()) {
                // QC#22874
                if (ORD_LINE_STS.CANCELLED.equals(dtlBean.getOrdLineStsCd()) //
                        || ORD_LINE_STS.CLOSED.equals(dtlBean.getOrdLineStsCd()) //
                        || RTRN_LINE_STS.CANCELLED.equals(dtlBean.getRtrnLineStsCd()) //
                        || RTRN_LINE_STS.CLOSED.equals(dtlBean.getRtrnLineStsCd())) {
                    continue;
                }

                if (!dsOrdPosnNum.equals(dtlBean.getDsOrdPosnNum())) {
                    continue;
                }

                String dtlMdseCd = dtlBean.getMdseCd();
                // Compare of MDSE Code, Line Category
                if (dtlMdseCd.startsWith(autoAddSplyMdseCd) && dtlBean.getDsOrdLineCatgCd().equals(autoAddSplyLineCatgCd)) {
                    compareFlg = true;
                    break;
                }
            }

            if (!compareFlg) {
                errMsg = new StringBuilder();
                errMsg.append("Supply Line [item = ");
                errMsg.append(autoAddSplyMdseCd);
                errMsg.append(", Line Category = ");
                if (DS_ORD_LINE_CATG.INITIAL_SUPPLIES.equals(autoAddSplyLineCatgCd)) {
                    errMsg.append("INITIAL SUPPLIES");
                } else {
                    errMsg.append("CONTRACT SUPPLY");
                }
                errMsg.append("] is not added.");
                errMsgList.add(errMsg.toString());
            }
        }

        return errMsgList;
    }

    // 2017/08/22 S21_NA#20022 Add End

    // 2017/09/16 S21_NA#21064 Add Start
    /**
     * checkSerNumRequire
     * 
     * <pre>
     * DI Check Code: E099-1240
     * Serial Number Require Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkSerNumRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        boolean isEquipmentOrd = isExistOrdCatg(cpoBean);

        for (NWZC155001CpoDtlBean dtlBean : cpoBean.getCpoDtlBeanList()) {

            if (CONFIG_CATG.INBOUND.equals(dtlBean.getConfigCatgCd()) && !RTRN_LINE_STS.CANCELLED.equals(dtlBean.getRtrnLineStsCd()) && !RTRN_LINE_STS.CLOSED.equals(dtlBean.getRtrnLineStsCd())) {

                if (isEquipmentOrd && ZYPCommonFunc.hasValue(dtlBean.getMdseCd())) {
                    boolean isNeedSerialNum = isNeedSerialNum(dtlBean.getGlblCmpyCd(), dtlBean.getMdseCd());
                    if (isNeedSerialNum && !ZYPCommonFunc.hasValue(dtlBean.getSerNum())) {
                        StringBuilder msgParam = new StringBuilder();
                        msgParam.append("Serial number is required. [Item = ");
                        msgParam.append(dtlBean.getMdseCd());
                        msgParam.append("]");
                        setErrToDiRsltBean(cpoBean, dtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                    }
                }
            }
        }
    }

    /**
     * check Exist Order Category
     * @param cpoBean NWZC155001CpoBean
     * @return true: exist
     */
    public boolean isExistOrdCatg(NWZC155001CpoBean cpoBean) {

        if (!ZYPCommonFunc.hasValue(cpoBean.getDsOrdCatgCd())) {
            return false;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
        params.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());

        Integer resultCount = (Integer) ssmClient.queryObject("isExistOrdCatg", params);

        return 0 < resultCount;
    }

    /**
     * <pre>
     * check serial number mandatory or not
     * @param String glblCmpyCd
     * @param String mdseCd
     * @return true: Serial Take, false: no need serial number
     * </pre>
     */
    public static boolean isNeedSerialNum(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);

        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);

        if (null != mdseTMsg && ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            return true;
        } else {
            return false;
        }
    }

    // 2017/09/16 S21_NA#21064 Add End

    // 2017/10/26 S21_NA#21100 Add Start
    private void checkLineIstlDivRequire(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();

        BigDecimal dsCpoConfigPk = BigDecimal.ZERO;
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {
            // QC#22874
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || RTRN_LINE_STS.CANCELLED.equals(cpoDtlBean.getRtrnLineStsCd()) //
                    || RTRN_LINE_STS.CLOSED.equals(cpoDtlBean.getRtrnLineStsCd())) {
                continue;
            }

            if (cpoDtlBean.getDsCpoConfigPk().compareTo(dsCpoConfigPk) == 0) {
                continue;
            }

            dsCpoConfigPk = cpoDtlBean.getDsCpoConfigPk();

            final DS_CPO_ISTL_INFOTMsg tMsg = new DS_CPO_ISTL_INFOTMsg();
            tMsg.setSQLID("002");
            tMsg.setConditionValue("glblCmpyCd01", cpoDtlBean.getGlblCmpyCd());
            tMsg.setConditionValue("dsCpoConfigPk01", dsCpoConfigPk);

            DS_CPO_ISTL_INFOTMsgArray istlInfoAry = (DS_CPO_ISTL_INFOTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);

            for (int idx = 0; idx < istlInfoAry.length(); idx++) {

                DS_CPO_ISTL_INFOTMsg istlInfo = istlInfoAry.no(idx);

                if (!ZYPCommonFunc.hasValue(istlInfo.istlDivCd)) {

                    if (ZYPCommonFunc.hasValue(istlInfo.svcIstlRuleNum) && !S21StringUtil.isEquals(istlInfo.svcIstlRuleNum.getValue(), SVC_ISTL_RULE_NUM_NO_INSTALL)) {

                        StringBuilder msgParam = new StringBuilder(ISTL_DIV_REQUIRED_MSGPARAM);
                        setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                    }
                }
            }
        }
    }

    // 2017/10/26 S21_NA#21100 Add End

    // 2017/12/15 S21_NA#19804(Sol#349) ADD START
    /**
     * checkDefaultSalesRep
     * 
     * <pre>
     * DI Check Code: E099-1260
     * Sales Rep Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    private void checkDefaultSalesRep(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, cpoBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, cpoBean.getDsOrdTpCd());
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, cpoBean.getSoldToCustLocCd());
        if (!callDefSlsRepApi(cpoBean, nMZC260001PMsg)) {
            return;
        }

        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray TMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (TMsgArray != null && TMsgArray.length() > 0) {
            for (int i = 0; i < TMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = TMsgArray.no(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }
        if (E099_1260B.equals(diChkBean.getDiChckCd())) {
            // QC#24559 2018/03/05 Add Start
            // compareDefSlsRep(nMZC260001PMsg, targetWriterList,
            // cpoBean.getSlsRepTocCd(), cpoBean, diChkBean, null);
            List<String> missingTocList = compareDefSlsRep(nMZC260001PMsg, targetWriterList, cpoBean.getSlsRepTocCd(), cpoBean, diChkBean, null);
            if (missingTocList.size() > 0) {
                String errMsg = createErrMsg(missingTocList, cpoBean.getGlblCmpyCd());
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), errMsg);
            }
            // QC#24559 2018/03/05 Add End
        }
        if (E099_1260A.equals(diChkBean.getDiChckCd())) {
            List<String> posnNumList = new ArrayList<String>();
            for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
                if (!posnNumList.contains(cpoDtlBean.getConfigCatgCd() + cpoDtlBean.getDsOrdPosnNum())) {
                    // QC#24559 2018/03/05 Add Start
                    // compareDefSlsRep(nMZC260001PMsg,
                    // targetWriterList, cpoDtlBean.getSlsRepTocCd(),
                    // cpoBean, diChkBean, cpoDtlBean);
                    List<String> missingTocList = compareDefSlsRep(nMZC260001PMsg, targetWriterList, cpoDtlBean.getSlsRepTocCd(), cpoBean, diChkBean, cpoDtlBean);
                    if (missingTocList.size() > 0) {
                        String errMsg = createErrMsg(missingTocList, cpoBean.getGlblCmpyCd());
                        setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), errMsg, cpoDtlBean.getDsCpoConfigPk(), cpoDtlBean.getConfigCatgCd(), cpoDtlBean
                                .getDsOrdPosnNum());
                    }
                    // QC#24559 2018/03/05 Add End
                    posnNumList.add(cpoDtlBean.getConfigCatgCd() + cpoDtlBean.getDsOrdPosnNum());
                }
            }
        }
    }

    private List<String> compareDefSlsRep(NMZC260001PMsg nMZC260001PMsg, List<String> targetWriterList, String slsRepTocCd,//
            NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean, NWZC155001CpoDtlBean cpoDtlBean) {

        // 2018/01/26 S21_NA#23320 Mod Start
        List<String> missingTocList = new ArrayList<String>();
        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
            if (!targetWriterList.contains(defSlsRepPMsg.lineBizRoleTpCd.getValue())//
                    || !ZYPCommonFunc.hasValue(defSlsRepPMsg.tocCd)) {
                continue;
            }

            // if(slsRepTocCd.equals(defSlsRepPMsg.tocCd.getValue())){
            // return;
            // }

            DS_ORD_TP_PROC_DFNTMsg dfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(dfnTMsg.glblCmpyCd, cpoBean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(dfnTMsg.dsOrdTpCd, cpoBean.getDsOrdTpCd());

            dfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(dfnTMsg);

            if (dfnTMsg != null) {
                if (ZYPCommonFunc.hasValue(dfnTMsg.trtyGrpTpTxt.getValue())) {

                    String[] trtyGrpTpCd = dfnTMsg.trtyGrpTpTxt.getValue().split(",");

                    // Mod Start 2018/04/05 QC#25085
                    // if
                    // (Arrays.asList(trtyGrpTpCd).contains(defSlsRepPMsg.trtyGrpTpCd.getValue())//
                    // &&
                    // slsRepTocCd.equals(defSlsRepPMsg.tocCd.getValue()))
                    // {
                    // return new ArrayList<String>();
                    // }
                    if (!Arrays.asList(trtyGrpTpCd).contains(defSlsRepPMsg.trtyGrpTpCd.getValue())) {
                        continue;
                    } else if (slsRepTocCd.equals(defSlsRepPMsg.tocCd.getValue())) {
                        return new ArrayList<String>();
                    }
                    // Mod End 2018/04/05 QC#25085
                } else if (ZYPCommonFunc.hasValue(dfnTMsg.lineBizTpCd.getValue())) {

                    if (!dfnTMsg.lineBizTpCd.getValue().equals(defSlsRepPMsg.lineBizTpCd.getValue())) {
                        continue;
                    } else {
                        if (slsRepTocCd.equals(defSlsRepPMsg.tocCd.getValue())) {
                            return new ArrayList<String>();
                        }
                    }
                }
            }
            missingTocList.add(defSlsRepPMsg.tocCd.getValue());
        }
        // 2018/01/26 S21_NA#23320 Mod End

        // QC#24559 2018/03/05 Add Start
        // if (missingTocList.size() > 0) {
        // return true;
        // String errMsg =
        // createErrMsg(missingTocList,cpoBean.getGlblCmpyCd());
        // setErrToDiRsltBean(cpoBean, cpoDtlBean,
        // diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(),
        // diChkBean.getDiChkRsltTpCd(), errMsg);
        // }
        return missingTocList;
        // QC#24559 2018/03/05 Add End
    }

    private static String createErrMsg(List<String> missingTocList, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        if (missingTocList.size() == 1) {
            sb.append("Missing Sales Credit for Rep ");
        } else {
            sb.append("Missing Sales Credit for one of Reps ");
        }
        int i = 0;
        for (String tocCd : missingTocList) {
            String tocNm = getTocNm(glblCmpyCd, tocCd);
            if (i != 0 && tocNm != null) {
                sb.append(", ");
            }
            sb.append(tocNm);
            i++;
        }
        return sb.toString();
    }

    private static String getTocNm(String glblCmpyCd, String tocCd) {
        TOCTMsg tocTmsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tocTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tocTmsg.tocCd, tocCd);
        TOCTMsg tocOutTmsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tocTmsg);

        if (tocOutTmsg != null) {
            return "\"" + tocOutTmsg.tocNm.getValue() + "\"";
        }
        return null;
    }

    /**
     * Call NMZC2600 Default Sales Rep API
     * @param bizMsg NWAL2200CMsg
     * @param pMsg NMZC260001PMsg
     * @return has API error : false
     */
    private static boolean callDefSlsRepApi(NWZC155001CpoBean cpoBean, NMZC260001PMsg pMsg) {

        // call NMZC2600 Dafault Sales Rep API
        new NMZC260001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    return false;
                }
            }
        }
        return true;
    }

    // 2017/12/15 S21_NA#19804(Sol#349) ADD END

    // 2018/03/01 S21_NA#24117 add start
    private void checkMandatoryMdlId(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();
        List<NWZC155001CpoDtlBean> cpoDtlBeanListConfigLvl = cpoBean.getCpoDtlBeanList();

        BigDecimal dsCpoConfigPk = BigDecimal.ZERO;
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {
            if (ORD_LINE_STS.CANCELLED.equals(cpoDtlBean.getOrdLineStsCd()) //
                    || ORD_LINE_STS.CLOSED.equals(cpoDtlBean.getOrdLineStsCd())) {
                continue;
            }

            if (cpoDtlBean.getDsCpoConfigPk().compareTo(dsCpoConfigPk) == 0) {
                continue;
            }

            // 2018/09/27 S21_NA#28481 add start
            if (CONFIG_CATG.INBOUND.equals(cpoDtlBean.getConfigCatgCd())) {
                continue;
            }
            // 2018/09/27 S21_NA#28481 add end

            dsCpoConfigPk = cpoDtlBean.getDsCpoConfigPk();

            if (ZYPCommonFunc.hasValue(cpoDtlBean.getMdlId())) {
                continue;
            }

            for (NWZC155001CpoDtlBean cpoDtlBeanConfigLvl : cpoDtlBeanList) {
                if (cpoDtlBean.getDsCpoConfigPk().compareTo(cpoDtlBeanConfigLvl.getDsCpoConfigPk()) != 0) {
                    continue;
                }

                ALL_MDSE_VTMsg tmsg = new ALL_MDSE_VTMsg();
                tmsg.setSQLID("003");
                tmsg.setConditionValue("glblCmpyCd01", cpoBean.getGlblCmpyCd());
                tmsg.setConditionValue("mdseCd01", cpoDtlBeanConfigLvl.getMdseCd());

                ALL_MDSE_VTMsgArray tmsgArray = (ALL_MDSE_VTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
                if (tmsgArray.length() > 0) {
                    // 2019/02/20 S21_NA#30347 mod start
                    // String mdseItemTpCd =
                    // tmsgArray.no(0).mdseItemTpCd.getValue();

                    // if (MDSE_ITEM_TP.MACHINE.equals(mdseItemTpCd)
                    // ||
                    // MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)//
                    // || MDSE_ITEM_TP.SOFTWARE.equals(mdseItemTpCd)
                    // ||
                    // MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(mdseItemTpCd))
                    // {
                    String instlBaseCtrlFlg = tmsgArray.no(0).instlBaseCtrlFlg.getValue();

                    if (ZYPConstant.FLG_ON_Y.equals(instlBaseCtrlFlg)) {
                        // 2019/02/20 S21_NA#30347 mod end

                        String msgParam = "Model is not found.";
                        setErrToDiRsltBean(cpoBean, cpoDtlBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam);
                        break;
                    }
                }
            }
        }
    }

    // 2018/03/01 S21_NA#24117 add end

    // 2018/03/15 S21_NA#24253 Add Start
    private void checkConfigShiptoLocation(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();

        BigDecimal dsCpoConfigPk = BigDecimal.ZERO;
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {

            if (cpoDtlBean.getDsCpoConfigPk().compareTo(dsCpoConfigPk) == 0) {
                continue;
            }

            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, cpoDtlBean.getConfigCatgCd())) {
                continue;
            }

            dsCpoConfigPk = cpoDtlBean.getDsCpoConfigPk();

            if (!isAllDetailRebillConfig(cpoBean, dsCpoConfigPk) && //
                    NWXC150001DsCheck.checkConfigShiptoLocation(//
                            cpoBean.getGlblCmpyCd() //
                            , cpoDtlBean.getConfigTpCd() //
                            , cpoDtlBean.getSvcConfigMstrPk() //
                            , cpoDtlBean.getShipToCustCd())) {
                String msgParam = "Ship to location and Installed location are inconsistent.";
                setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, cpoDtlBean.getDsCpoConfigPk(), cpoDtlBean.getConfigCatgCd(), cpoDtlBean.getDsOrdPosnNum());
                break;
            }
        }
    }

    public static boolean isAllDetailRebillConfig(NWZC155001CpoBean cpoBean, BigDecimal dsCpoConfigPk) {
        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {

            boolean isRebillDtl = S21StringUtil.isEquals(CR_REBIL.REBILL, cpoDtlBean.getCrRebilCd());
            if (dsCpoConfigPk.compareTo(cpoDtlBean.getDsCpoConfigPk()) == 0 && !isRebillDtl) {
                return false;
            }
        }
        return true;
    }

    // 2018/03/15 S21_NA#24253 Add End

    // 2019/04/04 S21_NA#30819 Add Start
    /**
     * check difference between Customer Installable Flag and Service
     * Customer Installable Flag
     */
    private void checkCustIstl(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        List<NWZC155001CpoDtlBean> cpoDtlBeanList = cpoBean.getCpoDtlBeanList();
        BigDecimal dsCpoConfigPk = BigDecimal.ZERO;
        Map<BigDecimal, Boolean> custIstlChkMap = new HashMap<BigDecimal, Boolean>();
        setCustIstlInfo(custIstlChkMap, cpoBean);
        for (NWZC155001CpoDtlBean cpoDtlBean : cpoDtlBeanList) {

            if (cpoDtlBean.getDsCpoConfigPk().compareTo(dsCpoConfigPk) == 0) {
                continue;
            }
            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, cpoDtlBean.getConfigCatgCd())) {
                continue;
            }
            dsCpoConfigPk = cpoDtlBean.getDsCpoConfigPk();
            Boolean isDifferentVal = false;
            if (custIstlChkMap.containsKey(dsCpoConfigPk)) {
                isDifferentVal = custIstlChkMap.get(dsCpoConfigPk);
            }
            if (isDifferentVal) {
                String msgParam = "Seleceted install type for Model " + cpoDtlBean.getMdlNm() + " has different from customer installable setting on Model Master.";
                setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam, cpoDtlBean.getDsCpoConfigPk(), cpoDtlBean.getConfigCatgCd(), cpoDtlBean.getDsOrdPosnNum());
            }
        }
    }

    /**
     * set Customer Installable Flag Information
     */
    public void setCustIstlInfo(Map<BigDecimal, Boolean> custIstlChkMap, NWZC155001CpoBean cpoBean) {

        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        ssmParam.put("configCatgOut", CONFIG_CATG.OUTBOUND);

        List<Map> resMapList = (List<Map>) ssmClient.queryObjectList("getCustIstlInfo", ssmParam);

        for (Map<String, Object> rsDtl : resMapList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) rsDtl.get("DS_CPO_CONFIG_PK");
            String custIstlflg = (String) rsDtl.get("CUST_ISTL_FLG");
            String svcCustIstlflg = (String) rsDtl.get("SVC_CUST_ISTL_FLG");
            if (!ZYPCommonFunc.hasValue(custIstlflg) || !ZYPCommonFunc.hasValue(svcCustIstlflg) || custIstlChkMap.containsKey(dsCpoConfigPk)) {
                continue;
            }
            if (!custIstlflg.equals(svcCustIstlflg)) {
                custIstlChkMap.put(dsCpoConfigPk, true);
            } else {
                custIstlChkMap.put(dsCpoConfigPk, false);
            }
        }
    }

    // 2019/04/04 S21_NA#30819 Add End

    // Add Start 2019/04/24 QC#31125,QC#31126
    /**
     * checkSalesRepActive
     * 
     * <pre>
     * DI Check Code: E099-1260
     * Sales Rep Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    private void checkSalesRepActive(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("cpoOrdNum", cpoBean.getCpoOrdNum());
        // Mod Start 2019/07/10 QC#51286
        // ssmParam.put("slsDt", cpoBean.getSlsDt());
        ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        // Mod End 2019/07/10 QC#51286
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("slsCrQuotFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("rgtnStsCd", RGTN_STS.TERMINATED);

        List<Map> resMapList = (List<Map>) ssmClient.queryObjectList("getActiveSlsRepInfo", ssmParam);

        if (resMapList == null) {
            return;
        }

        for (Map<String, Object> rsDtl : resMapList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) rsDtl.get("DS_CPO_CONFIG_PK");
            String actvTocCd = (String) rsDtl.get("ACTV_TOC_CD");
            String psnNum = (String) rsDtl.get("PSN_NUM");

            if (E099_1300A.equals(diChkBean.getDiChckCd())) {
                // Header Level Check
                if (dsCpoConfigPk != null) {
                    // Line Config or RMA
                    continue;
                }
                if (actvTocCd != null) {
                    // This TOC_CD is active.
                    continue;
                }

                String errMsg = createSalesRepNonActiveErrMsg(psnNum);
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), errMsg);
            }

            if (E099_1300B.equals(diChkBean.getDiChckCd())) {
                // Line Config/ RMA Level Check
                if (dsCpoConfigPk == null) {
                    // Header
                    continue;
                }
                if (actvTocCd != null) {
                    // This TOC_CD is active.
                    continue;
                }

                String errMsg = createSalesRepNonActiveErrMsg(psnNum);

                for (NWZC155001CpoDtlBean cpoDtlBean : cpoBean.getCpoDtlBeanList()) {
                    if (dsCpoConfigPk.compareTo(cpoDtlBean.getDsCpoConfigPk()) == 0) {
                        setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), //
                                errMsg, cpoDtlBean.getDsCpoConfigPk(), cpoDtlBean.getConfigCatgCd(), cpoDtlBean.getDsOrdPosnNum());
                        break;
                    }
                }
            }
        }
    }

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    /**
     * checkToBeInstalledByOrServiceProvidedBy
     * 
     * <pre>
     * DI Check Code: E099_1340
     * To Be Installed By or Service Provided By Check
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    private void checkToBeInstalledByOrServiceProvidedBy(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {

        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        key.put("configCatgCd", CONFIG_CATG.OUTBOUND);
        // START 2024/03/01 t.aizawa [QC#61771, ADD]
        key.put("dsCondConstGrpId", NWZC1550_LINE_CATG);
        // END 2024/03/01 t.aizawa [QC#61771, ADD]
        List<Map<String, Object>> cpoConfigList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCpoConfigList", key);

        if (cpoConfigList == null) {
            return;
        }

        key.put("accessory", MDSE_ITEM_TP.ACCESSORY);
        key.put("software", MDSE_ITEM_TP.SOFTWARE);
        for (Map<String, Object> cpoConfigMap : cpoConfigList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) cpoConfigMap.get("DS_CPO_CONFIG_PK");
            key.put("dsCpoConfigPk", dsCpoConfigPk);
            Map<String, Object> dsCpoIstlInfo = (Map<String, Object>) ssmClient.queryObject("getDsCpoIstlInfo", key);
            if (dsCpoIstlInfo == null) {
                String dsOrdPosnNum = (String) cpoConfigMap.get("DS_ORD_POSN_NUM");
                String confCatgCd = cpoConfigMap.get("CONFIG_CATG_CD").toString();
                setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), E099_1340_MSG, dsCpoConfigPk, confCatgCd, dsOrdPosnNum);
                continue;
            }
            if (dsCpoIstlInfo.get("ISTL_BY_SVC_PRVD_PTY_CD") == null || dsCpoIstlInfo.get("SVC_BY_SVC_PRVD_PTY_CD") == null) {
                BigDecimal cpoConfigSvcPrvdPtyCdCnt = (BigDecimal) ssmClient.queryObject("getCpoConfigSvcPrvdPtyCdCnt", key);
                if (BigDecimal.ZERO.compareTo(cpoConfigSvcPrvdPtyCdCnt) >= 0) {
                    String dsOrdPosnNum = (String) cpoConfigMap.get("DS_ORD_POSN_NUM");
                    String confCatgCd = cpoConfigMap.get("CONFIG_CATG_CD").toString();
                    setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), E099_1340_MSG, dsCpoConfigPk, confCatgCd, dsOrdPosnNum);
                }
            }
        }
    }

    // END 2023/10/06 K.Watanabe [QC#54186, ADD]

    /**
     * createSalesRepNonActiveErrMsg
     * @param psnNum String
     * @return String
     */
    private static String createSalesRepNonActiveErrMsg(String psnNum) {

        StringBuilder sb = new StringBuilder();

        sb.append("The entered Sales Rep Number '");
        sb.append(psnNum);
        sb.append("' does not exist in Master or Inactive item.");

        return sb.toString();
    }

    // Add End 2019/04/24 QC#31125,QC#31126

    // 2019/05/07 QC#50174 Add Start
    /**
     * isBooked
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @return boolean
     */
    private boolean isBooked(NWZC155001CpoBean cpoBean) {

        if (isBooked(cpoBean.getOrdBookFlg())) {
            return true;
        }
        return false;
    }

    /**
     * isBooked
     * 
     * <pre>
     * @param ordBookFlg String
     * @return boolean
     */
    public boolean isBooked(String ordBookFlg) {

        if (ZYPConstant.FLG_ON_Y.equals(ordBookFlg)) {
            return true;
        }
        return false;
    }

    // 2019/05/07 QC#50174 Add End

    // 2022/11/25 QC#60398 Add Start
    /**
     * checkHardCopyRequired
     * 
     * <pre>
     * DI Check Code: E099-1320
     * Customer Equipment Hard Copy Required
     * Set Warning This customer required Hard Copy of PO. Please confirm if you have done it.
     * 
     * <pre>
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkHardCopyRequired(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        String glblCmpyCd = cpoBean.getGlblCmpyCd();
        String ordNum = cpoBean.getCpoOrdNum();

        if (isAllLineCredit(glblCmpyCd, ordNum)) {
            return;
        }
        // 2022/12/22 QC#60398 Mod Start
        // NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        // apiMsg.glblCmpyCd.setValue(glblCmpyCd);
        // apiMsg.xxModeCd.setValue(PROCESS_MODE_TRANSACTION);
        // apiMsg.dsTrxRuleTpCd.setValue(DS_TRX_RULE_TP.CONTRACT);
        // apiMsg.billToCustCd.setValue(cpoBean.getBillToCustCd());
        // apiMsg.dsAcctNum_I1.setValue(cpoBean.getSellToCustCd());
        // apiMsg.slsDt.setValue(cpoBean.getSlsDt());
        //
        // NMZC610001 api = new NMZC610001();
        // api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        // if (S21ApiUtil.isXxMsgId(apiMsg)) {
        // return;
        // }
        //
        // if
        // (ZYPConstant.FLG_ON_Y.equals(apiMsg.TransactionRuleList.no(0).hardCopyReqFlg.getValue()))
        // {
        // // set Warning
        // StringBuilder msgParam = new StringBuilder();
        // msgParam.append("This customer required Hard Copy of PO. Please confirm if you have done it.");
        // setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(),
        // diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(),
        // msgParam.toString());
        // return;
        // }
        String hardCopyReqFlg = ZYPConstant.FLG_OFF_N;
        List<String> trxRuleTpList = getTrxRuleTp(glblCmpyCd);
        for (String trxRuleTp : trxRuleTpList) {
            NMZC610001PMsg apiMsg = new NMZC610001PMsg();
            apiMsg.glblCmpyCd.setValue(glblCmpyCd);
            apiMsg.xxModeCd.setValue(PROCESS_MODE_TRANSACTION);
            apiMsg.dsTrxRuleTpCd.setValue(trxRuleTp);
            apiMsg.billToCustCd.setValue(cpoBean.getBillToCustCd());
            apiMsg.slsDt.setValue(cpoBean.getSlsDt());

            NMZC610001 api = new NMZC610001();
            api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
            if (S21ApiUtil.isXxMsgId(apiMsg)) {
                return;
            }
            if (ZYPConstant.FLG_ON_Y.equals(apiMsg.TransactionRuleList.no(0).hardCopyReqFlg.getValue())) {
                hardCopyReqFlg = ZYPConstant.FLG_ON_Y;
                break;
            }
        }
        if (ZYPConstant.FLG_ON_Y.equals(hardCopyReqFlg)) {
            // set Warning
            StringBuilder msgParam = new StringBuilder();
            msgParam.append("This customer required Hard Copy of PO. Please confirm if you have done it.");
            setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
            return;
        }
        if (isLeaseOrder(glblCmpyCd, ordNum)) {
            hardCopyReqFlg = ZYPConstant.FLG_OFF_N;
            for (String trxRuleTp : trxRuleTpList) {
                NMZC610001PMsg apiMsg = new NMZC610001PMsg();
                apiMsg.glblCmpyCd.setValue(glblCmpyCd);
                apiMsg.xxModeCd.setValue(PROCESS_MODE_TRANSACTION);
                apiMsg.dsTrxRuleTpCd.setValue(trxRuleTp);
                apiMsg.billToCustCd.setValue(cpoBean.getSoldToCustLocCd());
                apiMsg.slsDt.setValue(cpoBean.getSlsDt());

                NMZC610001 api = new NMZC610001();
                api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
                if (S21ApiUtil.isXxMsgId(apiMsg)) {
                    return;
                }
                if (ZYPConstant.FLG_ON_Y.equals(apiMsg.TransactionRuleList.no(0).hardCopyReqFlg.getValue())) {
                    hardCopyReqFlg = ZYPConstant.FLG_ON_Y;
                    break;
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(hardCopyReqFlg)) {
                // set Warning
                StringBuilder msgParam = new StringBuilder();
                msgParam.append("This customer required Hard Copy of PO. Please confirm if you have done it.");
                setErrToDiRsltBean(cpoBean, null, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), msgParam.toString());
                return;
            }
        }
        // 2022/12/22 QC#60398 Mod End
    }

    // 2022/11/25 QC#60398 Add End
    // 2022/12/22 QC#60398 Add Start
    private List<String> getTrxRuleTp(String glblCmpyCd) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("equipOrd", ORD_CATG_CTX_TP.EQUIPMENT_ORDER_VALUE_SET);

        return (List<String>) ssmClient.queryObjectList("getTrxRuleTp", ssmParam);
    }

    // 2022/12/22 QC#60398 Add End

    // START 2023/01/30 R.Azucena [QC#61094, ADD]
    /**
     * checkLoanForLoanOrders
     * @param cpoBean NWZC155001CpoBean
     * @param diChkBean NWZC155001DiChkBean
     */
    public void checkLoanForLoanOrders(NWZC155001CpoBean cpoBean, NWZC155001DiChkBean diChkBean) {
        Map<String, Object> key = new HashMap<String, Object>();
        key.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        key.put("ordNum", cpoBean.getCpoOrdNum());
        List<Map<String, Object>> cpoConfigList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCpoConfigList", key);

        if (cpoConfigList == null) {
            return;
        }

        key.put("ordLineCatgCd", DS_ORD_LINE_CATG.LOAN_ORDERS);
        key.put("ordLineSts", ORD_LINE_STS.CANCELLED);

        for (Map<String, Object> cpoConfigMap : cpoConfigList) {
            BigDecimal dsCpoConfigPk = (BigDecimal) cpoConfigMap.get("DS_CPO_CONFIG_PK");
            String dsOrdPosnNum = (String) cpoConfigMap.get("DS_ORD_POSN_NUM");
            key.put("dsCpoConfigPk", dsCpoConfigPk);
            key.put("dsOrdPosnNum", dsOrdPosnNum);
            BigDecimal loanOrdersCnt = (BigDecimal) ssmClient.queryObject("getLoanOrdersCnt", key);

            if (BigDecimal.ZERO.equals(loanOrdersCnt)) {
                String confCatgCd = cpoConfigMap.get("CONFIG_CATG_CD").toString();
                setErrToDiRsltBeanConfigLvl(cpoBean, diChkBean.getDiChckCd(), diChkBean.getDiChkLvlCd(), diChkBean.getDiChkRsltTpCd(), E099_1330_MSG, dsCpoConfigPk, confCatgCd, dsOrdPosnNum);
            }
        }
    }

    // END 2023/01/30 R.Azucena [QC#61094, ADD]

    // START 2024/03/12 M.Kuroi [QC#63638, ADD]
    /**
     * getDiChckCdWithContrFlg
     * @param cpoBean NWZC155001CpoBean
     */
    private List<NWZC155001DiChkBean> getDiChckCdWithContrFlg(NWZC155001CpoBean cpoBean) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cpoBean.getGlblCmpyCd());
        ssmParam.put("dsOrdCatgCd", cpoBean.getDsOrdCatgCd());
        ssmParam.put("dsOrdTpCd", cpoBean.getDsOrdTpCd());
        ssmParam.put("slsDt", cpoBean.getSlsDt());
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);

        String ordBookFlg = cpoBean.getOrdBookFlg();
        boolean isBooked = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, ordBookFlg);
        if (isBooked == false) {
            ordBookFlg = ZYPConstant.FLG_OFF_N;
        }
        ssmParam.put("ordBookFlg", ordBookFlg);
        List<NWZC155001DiChkBean> diChkList = new ArrayList<NWZC155001DiChkBean>();
        List<Map> mapList = (List<Map>) ssmClient.queryObjectList("getDiChckCdWithContrFlg", ssmParam);

        NWZC155001DiChkBean bean;
        for (Map map : mapList) {
            bean = new NWZC155001DiChkBean();
            bean.setDiChckCd(map.get("DI_CHK_CD").toString());
            bean.setDiChkLvlCd(map.get("DI_CHK_LVL_CD").toString());
            bean.setDiChkRsltTpCd(map.get("DI_CHK_RSLT_TP_CD").toString());
            bean.setDiMndChkPrflCd((String) map.get("DI_MND_CHK_PRFL_CD"));

            diChkList.add(bean);
        }
        return diChkList;
    }
    // END 2024/03/12 M.Kuroi [QC#63638, ADD]
}
