/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.DEF_SLS_CR_PCT_WRITER;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0073E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1205E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.PCT_100;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parts.common.EZDMsg;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDSQLAccessor;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CAL_RELNTMsg;
import business.db.CONFIG_TPTMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CPO_DTL_RECTMsg;
import business.db.CPO_RECTMsg;
import business.db.CPO_RTRN_CALC_BASE_RECTMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsgArray;
import business.db.CPO_VTMsg;
import business.db.CPO_VTMsgArray;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_CONFIG_RECTMsg;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_DELY_INFOTMsgArray;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.DS_CPO_RTRN_DTL_RECTMsg;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_CPO_SLS_CRTMsgArray;
import business.db.DS_CPO_SLS_CR_RECTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.DS_SITE_SRVYTMsg;
import business.db.DS_SITE_SRVYTMsgArray;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASETMsgArray;
import business.db.ORD_PRC_CALC_BASE_RECTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.STTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC611001PMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NWZC002001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC153001PMsg;
import business.parts.NWZC153001_ordRtrnDtlListPMsg;
import business.parts.NWZC153002PMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC260001.constant.NMZC260001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC002001.NWZC002001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouLocalCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.SLS_CR_PCT_MD;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cpoConf.NWZC150001CpoConfMain;
import com.canon.cusa.s21.api.NWZ.NWZC153001.NWZC153001;
import com.canon.cusa.s21.api.NWZ.NWZC153001.constant.NWZC153001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Export;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_SUB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_COND_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLog;
import com.canon.cusa.s21.framework.log.S21BusinessProcessLogMsg;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/25   Fujitsu         T.Ogura         Create          N/A
 * 2017/05/24   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7262(2)
 * 2017/05/25   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#8389
 * 2017/05/30   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#7262-3
 * 2017/07/05   Fujitsu         A.Kosai         Update          S21_NA#19266
 * 2017/09/13   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154),S21_NA#16346(Sol#373)
 * 2017/10/24   Fujitsu         A.Kosai         Update          S21_NA#21705
 * 2017/10/24   Fujitsu         A.Kosai         Update          S21_NA#21707
 * 2018/03/14   Fujitsu         K.Ishizuka      Update          S21_NA#23674
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/25   Fujitsu         T.Aoi           Update          S21_NA#20154
 * 2018/07/03   Fujitsu         M.Yamada        Update          QC#26927
 * 2018/07/30   Fujitsu         M.Ohno          Update          S21_NA#26413
 * 2018/08/02   Fujitsu         R.Nakamura      Update          S21_NA#26338
 * 2018/08/20   Fujitsu         M.Yamada        Update          S21_NA#22127
 * 2018/08/22   Fujitsu         Y.Kanefusa      Update          S21_NA#26326
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 * 2018/09/05   Fujitsu         S.Takami        Update          S21_NA#26755
 * 2018/09/18   Fujitsu         Mz.Takahashi    Update          QC#28244
 * 2019/02/05   Fujitsu         K.Ishizuka      Update          S21_NA#30149
 * 2019/05/13   Fujitsu         Y.Kanefusa      Update          S21_NA#50126
 * 2019/05/15   Fujitsu         Y.Kanefusa      Update          S21_NA#50130
 * 2019/05/30   Fujitsu         R.Nakamura      Update          S21_NA#50405
 * 2019/06/05   Fujitsu         Y.Kanefusa      Update          S21_NA#50710
 * 2019/06/10   Fujitsu         Y.Kanefusa      Update          S21_NA#50747
 * 2019/09/24   Fujitsu         C.Hara          Update          S21_NA#53592
 * 2019/10/15   Fujitsu         C.Hara          Update          S21_NA#53592-1
 * 2019/10/30   Fujitsu         K.Kato          Update          S21_NA#53556
 * 2020/01/24   Fujitsu         Y.Kanefusa      Update          QC#54725
 * </pre>
 */

public class NWZC150001ForOrderCopy extends S21ApiCommonBase {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatchType = null;

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** SSM Client */
    private final S21SsmBatchClient ssmClient;

    /** Sub System ID : NWZ */
    private static final String SUB_SYS_ID_NWZ = "NWZ";

    /** Document Type : OM */
    private static final String DOC_TP_OM = "OM";

    /** Document Type : RT */
    private static final String DOC_TP_RT = "RT";

    /** Event ID for Business Process Log : Order Create */
    private static final String ORD_CREATE = "Order Create";

    /** Event ID for Business Process Log : Order Save */
    private static final String ORD_SAVE = "Order Save";

    /** Event ID for Business Process Log : Order Create(Sales Credit)" */
    private static final String ORD_SLS_CR_CREATE = "Order Create(Sales Credit)";

    /** Event ID for Shell Create */
//    private static final String ORD_SHELL_CREATE = "Shell Create";

    // QC#50130 2019/05/15 Add Start
    private static final Map<String, String> PRC_DTL_GRP_COND_TP_RMA_MAP = new LinkedHashMap<String, String>();
    // QC#50130 2019/05/15 Add End
    /**
     * Constructor for NWZC150001ForOrderCopy
     */
    public NWZC150001ForOrderCopy() {
        super();
        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * DS CPO Update API.(Order Copy)
     * @param inPMsg        NWZC150001PMsg
     * @param resPMsg2List  List<NWZC150002PMsg>
     * @param resPMsg3List  List<NWZC150003PMsg>
     * @param prmOnBatchType ONBATCH_TYPE
     * @param localCache Local cache
     * </pre>
     */
    public void execute(final NWZC150001PMsg inPMsg //
            , final List<NWZC150002PMsg> resPMsg2List //
            , final List<NWZC150003PMsg> resPMsg3List //
            , final ONBATCH_TYPE prmOnBatchType
            // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
            , NWZC150001CpouLocalCache localCache) {
            // 2017/05/11 S21_NA#Review structure Lv.2 Add End

        NWZC150001PMsg pMsg = new NWZC150001PMsg();

        try {
            EZDMsg.copy(inPMsg, null, pMsg, null);

            this.msgIdMgr = new S21ApiMessageIdMgr();
            this.onBatchType = prmOnBatchType;

            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            doProcess(pMsg, resPMsg2List, resPMsg3List);
            doProcess(pMsg, resPMsg2List, resPMsg3List, localCache);
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End

        } finally {
            inPMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
            EZDMsg.copy(pMsg, null, inPMsg, null);
            super.updateMessage(inPMsg, this.msgIdMgr);
        }
    }

    private void doProcess(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> resPMsg2List //
            , List<NWZC150003PMsg> resPMsg3List
            // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
            , NWZC150001CpouLocalCache localCache) {
            // 2017/05/11 S21_NA#Review structure Lv.2 Add End

        if (chkParam(pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_A, ZYPMaxTenDigitsNumbering.getUniqueID("CPO_ORD_NUM"));

        String userId = pMsg.usrId.getValue();
        String timeStamp = EZDDBCICarrier.getStartDateTime();

        if (registCopyCpo(pMsg, userId, timeStamp)) {
            return;
        }

        // Copy Only Header
        if (NWZC150001Constant.MODE_COPY_HEADER.equals(pMsg.xxModeCd.getValue())) {

            // DS_CPO_SLS_CR
            if (registCopyDsCpoSlsCr(pMsg, null, userId, timeStamp, false)) {
                return;
            }

            // DS_CPO_DELY_INFO
            if (registCopyDsCpoDelyInfo(pMsg, null, false)) {
                return;
            }

            // DS_CPO_ISTL_INFO
            if (registCopyDsCpoIstlInfo(pMsg, null, false)) {
                return;
            }

            // DS_SITE_SRVY
            if (registCopyDsSiteSrvy(pMsg, null, false)) {
                return;
            }

            // DS_CPO_CTAC_PSN
            if (registCopyDsCpoCtacPsnForHdr(pMsg)) {
                return;
            }

            return;
        // 2017/09/1 S21_NA#18859 Add Start
        } else if (NWZC150001Constant.MODE_COPY_ALL_RETURN.equals(pMsg.xxModeCd.getValue())) {

            // DS_CPO_CONFIG
            Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap = new HashMap<BigDecimal, DS_CPO_CONFIGTMsg>();
            if (registCopyDsCpoRMAConfig(pMsg)) {
                return;
            }

            createDsCpoConfigMap(pMsg, dsCpoConfigMap);

            // DS_CPO_RTRN_DTL
            if (callCpoReturnUpdApi(pMsg, dsCpoConfigMap)) {
                return;
            }

            // DS_CPO_SLS_CR
            if (registCopyDsCpoSlsCrForAllReturn(pMsg, dsCpoConfigMap, userId, timeStamp)) { // QC#22127
                return;
            }

            // DS_SITE_SRVY (No REC)
            if (registCopyDsSiteSrvy(pMsg, dsCpoConfigMap, true)) {
                return;
            }

            // DS_CPO_CTAC_PSN (No REC)
            if (registCopyDsCpoCtacPsn(pMsg, dsCpoConfigMap)) {
                return;
            }
            return;
            // 2017/09/13 S21_NA#18859 Add Start
        }

        // DS_CPO_CONFIG
        Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap = new HashMap<BigDecimal, DS_CPO_CONFIGTMsg>();
        if (registCopyDsCpoConfig(pMsg, userId, timeStamp, dsCpoConfigMap)) {
            return;
        }

        // CPO_DTL
        CPOTMsg copyCpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        copyCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(copyCpoTMsg);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        if (registCopyCpoDtl(pMsg, copyCpoTMsg, dsCpoConfigMap, userId, timeStamp)) {
        if (registCopyCpoDtl(pMsg, copyCpoTMsg, dsCpoConfigMap, userId, timeStamp, localCache)) {
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
            return;
        }

        // ORD_PRC_CALC_BASE
        if (registCopyOrdPrcCalcBase(pMsg, userId, timeStamp)) {
            return;
        }

        // DS_CPO_RTRN_DTL
        List<DS_CPO_RTRN_DTLTMsg> copyDsCpoRtrnDtlTMsgList = new ArrayList<DS_CPO_RTRN_DTLTMsg>(0);
        if (registCopyDsCpoRtrnDtl(pMsg, dsCpoConfigMap, userId, timeStamp, copyDsCpoRtrnDtlTMsgList)) {
            return;
        }

        // CPO_RTRN_PRC_CALC_BASE
        if (registCopyCpoRtrnPrcCalcBase(pMsg, copyDsCpoRtrnDtlTMsgList, userId, timeStamp)) {
            return;
        }

        // DS_CPO_SLS_CR
        if (registCopyDsCpoSlsCr(pMsg, dsCpoConfigMap, userId, timeStamp, true)) {
            return;
        }

        // DS_CPO_DELY_INFO (No REC)
        if (registCopyDsCpoDelyInfo(pMsg, dsCpoConfigMap, true)) {
            return;
        }

        // DS_CPO_ISTL_INFO (No REC)
        if (registCopyDsCpoIstlInfo(pMsg, dsCpoConfigMap, true)) {
            return;
        }

        // DS_SITE_SRVY (No REC)
        if (registCopyDsSiteSrvy(pMsg, dsCpoConfigMap, true)) {
            return;
        }

        // DS_CPO_CTAC_PSN (No REC)
        if (registCopyDsCpoCtacPsn(pMsg, dsCpoConfigMap)) {
            return;
        }

        // CPO_SVC Shell
        if (registCopyShellDataCpoSvc(pMsg, userId, timeStamp)) {
            return;
        }
    }

    private boolean chkParam(NWZC150001PMsg pMsg) {

        boolean errFlg = false;
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setMsgId(pMsg, NWZM0011E);
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            setMsgId(pMsg, NWZM1205E);
            errFlg = true;
        }
        return errFlg;
    }

    private boolean registCopyCpo(NWZC150001PMsg pMsg, String userId, String timeStamp) {

        CPOTMsg origCpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(origCpoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(origCpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);

        CPOTMsg copyCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(origCpoTMsg);
        if (copyCpoTMsg == null) {
            setMsgId(pMsg, NWZM0073E);
            return true;
        }
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoOrdNum, pMsg.cpoOrdNum_A.getValue());
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoOrdTs, pMsg.slsDt.getValue());
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordHdrStsCd, ORD_HDR_STS.SAVED);
        // 2017/05/30 S21_NA#Review structure Lv.2 RS#7262-3 Start
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordHdrDplyStsCd, ORD_HDR_DPLY_STS.ENTERED);
        // 2017/05/30 S21_NA#Review structure Lv.2 RS#7262-3 End
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.adminPsnCd, userId);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.addRddDt, pMsg.slsDt);
        copyCpoTMsg.addRsdDt.clear();
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoCancFlg, ZYPConstant.FLG_OFF_N);
        copyCpoTMsg.cpoCancDt.clear();
        copyCpoTMsg.origOrdNum.clear();
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.submtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.openFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoSrcTpCd, CPO_SRC_TP.COPY);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.sysSrcCd, SYS_SRC.S21_ORDER);

        if (ZYPCommonFunc.hasValue(copyCpoTMsg.orgRqstDelyDt)) {
            ZYPEZDItemValueSetter.setValue(copyCpoTMsg.orgRqstDelyDt, pMsg.slsDt);
        }
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.prcBaseDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.prcCalcDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordSrcImptDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordSrcRefNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.wfRejFlg, ZYPConstant.FLG_OFF_N);
        copyCpoTMsg.ordBookTs.clear();
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ordBookFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.cpoUpdVrsnNum, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.dsCpoPrcInd, ZYPConstant.FLG_OFF_0);
        // 2017/10/24 S21_NA#21705 Mod Start
        //copyCpoTMsg.prePmtChkNum.clear();
        //copyCpoTMsg.prePmtAmt.clear();
        //copyCpoTMsg.prePmtTpCd.clear();
        if (!NWZC150001Constant.MODE_COPY_ALL_RETURN.equals(pMsg.xxModeCd.getValue())) {
            copyCpoTMsg.prePmtChkNum.clear();
            copyCpoTMsg.prePmtAmt.clear();
            copyCpoTMsg.prePmtTpCd.clear();
        }
        // 2017/10/24 S21_NA#21705 Mod End
        copyCpoTMsg.dsCpoCratTs.setValue(timeStamp);
        copyCpoTMsg.dsCpoCratUsrId.setValue(userId);
        copyCpoTMsg.dsCpoUpdTs.setValue(timeStamp);
        copyCpoTMsg.dsCpoUpdUsrId.setValue(userId);
        copyCpoTMsg.ordBookReqUsrId.clear();
        copyCpoTMsg.ordBookReqTs.clear();
        copyCpoTMsg.ordSrcImptTs.clear();
        copyCpoTMsg.dsImptOrdPk.clear();

        // Add Start 2017/09/13 S21_NA#18859
        if (NWZC150001Constant.MODE_COPY_ALL_RETURN.equals(pMsg.xxModeCd.getValue())) {
//            setDefaultFromOrdTp(copyCpoTMsg, pMsg, msgIdMgr); // QC#26927
            // 2017/10/24 S21_NA#21705 Del Start
            //setDefaultPmtTerm(copyCpoTMsg, pMsg, msgIdMgr);
            // 2017/10/24 S21_NA#21705 Del End
//            setDefaultCarrSvcLvl(copyCpoTMsg, pMsg, msgIdMgr); // QC#26927
            ZYPEZDItemValueSetter.setValue(copyCpoTMsg.origOrdNum, pMsg.cpoOrdNum); // Add 2017/09/22 S21_NA#18859
            // QC#22127
            // Del Start 2019/05/30 QC#50405
//            if (deriveDefaultSlsRepForCopyAllReturn(pMsg) && ZYPCommonFunc.hasValue(pMsg.slsRepCd)) {
//                ZYPEZDItemValueSetter.setValue(copyCpoTMsg.slsRepTocCd, pMsg.slsRepCd);
//            }
            // Del End 2019/05/30 QC#50405
        }
        // Add Start 2019/05/30 QC#50405
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.slsRepTocCd, pMsg.slsRepCd);
        // Add End 2019/05/30 QC#50405
        // Add End 2017/09/13 S21_NA#18859

        S21FastTBLAccessor.insert(copyCpoTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(copyCpoTMsg.getReturnCode())) {
            throw new S21AbendException("CPO : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
        }

        // Biz Log
        insertProcLog(copyCpoTMsg);

        // Rec
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ezUpTime, timeStamp);
        ZYPEZDItemValueSetter.setValue(copyCpoTMsg.ezUpUserID, userId);

        BigDecimal bizProcLogLogPk = getBizProcLogPk(copyCpoTMsg);
        if (bizProcLogLogPk != null) {
            CPO_RECTMsg cpoRecMsg = new CPO_RECTMsg();
            EZDMsg.copy(copyCpoTMsg, null, cpoRecMsg, null);
            cpoRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
            S21FastTBLAccessor.insert(cpoRecMsg);
        }
        return false;
    }

    private void insertProcLog(CPOTMsg cpoTMsg) {
        insertBizProcLog(cpoTMsg.cpoOrdNum.getValue(), ORD_CREATE, null, DOC_TP_OM);
    }

    private void insertBizProcLog(String cpoOrdNum, String eventId, String docId, String docTpCd) {
        S21BusinessProcessLogMsg bizProcLog = new S21BusinessProcessLogMsg();

        bizProcLog.setSubSysId(SUB_SYS_ID_NWZ);
        bizProcLog.setProcId(DOC_TP_OM);
        bizProcLog.setDocTpCd(docTpCd);
        bizProcLog.setPrntDocId(cpoOrdNum);
        bizProcLog.setEventId(eventId);
        if (ZYPCommonFunc.hasValue(docId)) {
            bizProcLog.setDocId(docId);
        }

        S21BusinessProcessLog.print(bizProcLog);
    }

    private BigDecimal getBizProcLogPk(CPOTMsg cpoTMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
        queryParam.put("procId", DOC_TP_OM);
        queryParam.put("eventId", ORD_CREATE + "%");
        queryParam.put("docTpCd", DOC_TP_OM);
        queryParam.remove("docId");
        queryParam.put("cpoOrdNum", cpoTMsg.cpoOrdNum.getValue());
        queryParam.put("userId", cpoTMsg.ezUpUserID.getValue());
        queryParam.put("ezUptime", cpoTMsg.ezUpTime.getValue());

        return getMaxBizProcPk(queryParam);
    }

    private boolean registCopyDsCpoSlsCr(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp, boolean isNeedConfigCopy) {

        DS_CPO_SLS_CRTMsg origDSCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();

        // For Header DS_CPO_SLS_CR
        origDSCpoSlsCrTMsg.setSQLID("001");
        origDSCpoSlsCrTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDSCpoSlsCrTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_CPO_SLS_CRTMsgArray copyDsCpoSlsCrTMsgArray = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(origDSCpoSlsCrTMsg);
        List<DS_CPO_SLS_CRTMsg> copyDsCpoSlsCrTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>(0);
        for (int i = 0; i < copyDsCpoSlsCrTMsgArray.getValidCount(); i++) {
            DS_CPO_SLS_CRTMsg copyDsCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
            EZDMsg.copy(copyDsCpoSlsCrTMsgArray.no(i), null, copyDsCpoSlsCrTMsg, null);

            copyDsCpoSlsCrTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoSlsCrTMsg.dsCpoSlsCrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));

            copyDsCpoSlsCrTMsgList.add(copyDsCpoSlsCrTMsg);
        }

        // For DS_CPO_CONFIG
        if (isNeedConfigCopy) {
            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
                origDSCpoSlsCrTMsg.setSQLID("002");
                origDSCpoSlsCrTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                origDSCpoSlsCrTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
                DS_CPO_SLS_CRTMsgArray copyDsCpoSlsCrTMsgConfigArray = (DS_CPO_SLS_CRTMsgArray) EZDTBLAccessor.findByCondition(origDSCpoSlsCrTMsg);

                for (int i = 0; i < copyDsCpoSlsCrTMsgConfigArray.getValidCount(); i++) {
                    DS_CPO_SLS_CRTMsg copyDsCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
                    EZDMsg.copy(copyDsCpoSlsCrTMsgConfigArray.no(i), null, copyDsCpoSlsCrTMsg, null);

                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoSlsCrTMsg.dsCpoConfigPk.getValue());
                    if (copyDsCpoConfigTMsg == null) {
                        continue;
                    }
                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();

                    copyDsCpoSlsCrTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
                    copyDsCpoSlsCrTMsg.dsCpoSlsCrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));
                    copyDsCpoSlsCrTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);

                    copyDsCpoSlsCrTMsgList.add(copyDsCpoSlsCrTMsg);
                }
            }
        }

        if (!copyDsCpoSlsCrTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoSlsCrTMsgList.toArray(new DS_CPO_SLS_CRTMsg[0]));
            if (rsltCnt != copyDsCpoSlsCrTMsgList.size()) {
                throw new S21AbendException("DS_CPO_SLS_CR : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }

        registerBizProcLocWithDsCpoSlsCrRec(userId, timeStamp, copyDsCpoSlsCrTMsgList);

        return false;
    }


    private boolean registCopyDsCpoSlsCrForAllReturn(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp) {
        if (pMsg.cpoSlsCr.getValidCount() == 0) {
            return (registCopyDsCpoSlsCr(pMsg, dsCpoConfigMap, userId, timeStamp, true));
        }

        List<DS_CPO_SLS_CRTMsg> copyDsCpoSlsCrTMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>(0);

        for (int i = 0; i < pMsg.cpoSlsCr.getValidCount(); i++) {
            NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(i);
            DS_CPO_SLS_CRTMsg copyDsCpoSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
            EZDMsg.copy(cpoSlsCrPMsg, null, copyDsCpoSlsCrTMsg, null);
            copyDsCpoSlsCrTMsg.slsRepTocCd.setValue(cpoSlsCrPMsg.slsRepCd.getValue());

            copyDsCpoSlsCrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            copyDsCpoSlsCrTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoSlsCrTMsg.dsCpoSlsCrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));

            copyDsCpoSlsCrTMsgList.add(copyDsCpoSlsCrTMsg);

            // For DS_CPO_CONFIG
            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
                // 2018/09/18 QC#28244 Add Start
                DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(origdDsCpoConfigPk);
                if (copyDsCpoConfigTMsg == null) {
                    continue;
                }
                // 2018/09/18 QC#28244 Add End

                DS_CPO_CONFIGTMsg configTMsg = new DS_CPO_CONFIGTMsg();
                ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(configTMsg.dsCpoConfigPk, origdDsCpoConfigPk);
                configTMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKey(configTMsg);
                if (configTMsg == null) {
                    continue;
                }
                DS_CPO_SLS_CRTMsg copyConfigSlsCrTMsg = new DS_CPO_SLS_CRTMsg();
                EZDMsg.copy(copyDsCpoSlsCrTMsg, null, copyConfigSlsCrTMsg, null);
                copyConfigSlsCrTMsg.dsCpoSlsCrPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_SLS_CR_SQ));

                // 2018/09/18 QC#28244 Mod Start
                //ZYPEZDItemValueSetter.setValue(copyConfigSlsCrTMsg.dsCpoConfigPk, origdDsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(copyConfigSlsCrTMsg.dsCpoConfigPk, copyDsCpoConfigTMsg.dsCpoConfigPk);
                // 2018/09/18 QC#28244 Mod End

                ZYPEZDItemValueSetter.setValue(copyConfigSlsCrTMsg.dsOrdPosnNum, configTMsg.dsOrdPosnNum);

                copyDsCpoSlsCrTMsgList.add(copyConfigSlsCrTMsg);
            }
        }

        if (!copyDsCpoSlsCrTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoSlsCrTMsgList.toArray(new DS_CPO_SLS_CRTMsg[copyDsCpoSlsCrTMsgList.size()]));
            if (rsltCnt != copyDsCpoSlsCrTMsgList.size()) {
                throw new S21AbendException("DS_CPO_SLS_CR : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }
        registerBizProcLocWithDsCpoSlsCrRec(userId, timeStamp, copyDsCpoSlsCrTMsgList);
        return false;
    }

    private void registerBizProcLocWithDsCpoSlsCrRec(String userId, String timeStamp, List<DS_CPO_SLS_CRTMsg> copyDsCpoSlsCrTMsgList) {
        // Biz Proc Log
        Map<BigDecimal, String> dsCpoConfigPkMap = new HashMap<BigDecimal, String>(0);
        for (DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg : copyDsCpoSlsCrTMsgList) {
            insertProcLog(dsCpoSlsCrTMsg, dsCpoConfigPkMap);

            // Rec
            ZYPEZDItemValueSetter.setValue(dsCpoSlsCrTMsg.ezUpTime, timeStamp);
            ZYPEZDItemValueSetter.setValue(dsCpoSlsCrTMsg.ezUpUserID, userId);

            BigDecimal bizProcLogLogPk = getBizProcLogPk(dsCpoSlsCrTMsg, dsCpoConfigPkMap);
            if (bizProcLogLogPk != null) {
                DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecMsg = new DS_CPO_SLS_CR_RECTMsg();
                EZDMsg.copy(dsCpoSlsCrTMsg, null, dsCpoSlsCrRecMsg, null);
                dsCpoSlsCrRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
                S21FastTBLAccessor.insert(dsCpoSlsCrRecMsg);
            }
        }
    }

    private void insertProcLog(DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg, Map<BigDecimal, String> dsCpoConfigPkMap) {

        String cpoOrdNum = dsCpoSlsCrTMsg.cpoOrdNum.getValue();
        String docId = null;
        String docTp = DOC_TP_OM;

        DS_CPO_CONFIGTMsg configMsg = null;
        if (ZYPCommonFunc.hasValue(dsCpoSlsCrTMsg.dsCpoConfigPk)) {
            configMsg = new DS_CPO_CONFIGTMsg();
            ZYPEZDItemValueSetter.setValue(configMsg.glblCmpyCd, dsCpoSlsCrTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(configMsg.dsCpoConfigPk, dsCpoSlsCrTMsg.dsCpoConfigPk);
            configMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKey(configMsg);
        }
        if (configMsg != null) {
            docId = configMsg.dsOrdPosnNum.getValue();
            if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
                docTp = DOC_TP_RT;
            }
            if (!dsCpoConfigPkMap.containsKey(configMsg.dsCpoConfigPk.getValue())) {
                dsCpoConfigPkMap.put(configMsg.dsCpoConfigPk.getValue(), docTp);
            }
        }
        insertBizProcLog(cpoOrdNum, ORD_SLS_CR_CREATE, docId, docTp);
    }

    private BigDecimal getBizProcLogPk(DS_CPO_SLS_CRTMsg dsCpoSlsCrTMsg, Map<BigDecimal, String> dsCpoConfigPkMap) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
        queryParam.put("procId", DOC_TP_OM);

        queryParam.put("eventId", ORD_SLS_CR_CREATE + "%");

        String docTpCd = DOC_TP_OM;
        if (ZYPCommonFunc.hasValue(dsCpoSlsCrTMsg.dsCpoConfigPk.getValue())) {
            docTpCd = dsCpoConfigPkMap.get(dsCpoSlsCrTMsg.dsCpoConfigPk.getValue());
            if (docTpCd == null) {
                docTpCd = DOC_TP_OM;
            }
        }
        queryParam.put("docTpCd", docTpCd);

        String docId = null;
        if (ZYPCommonFunc.hasValue(dsCpoSlsCrTMsg.dsOrdPosnNum)) {
            docId = dsCpoSlsCrTMsg.dsOrdPosnNum.getValue();
        }
        queryParam.put("docId", docId);
        queryParam.put("cpoOrdNum", dsCpoSlsCrTMsg.cpoOrdNum.getValue());
        queryParam.put("userId", dsCpoSlsCrTMsg.ezUpUserID.getValue());
        queryParam.put("ezUptime", dsCpoSlsCrTMsg.ezUpTime.getValue());

        return getMaxBizProcPk(queryParam);
    }

    private boolean registCopyDsCpoDelyInfo(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, boolean isNeedConfigCopy) {

        DS_CPO_DELY_INFOTMsg origDsCpoDelyInfoTMsg = new DS_CPO_DELY_INFOTMsg();

        // For Header DS_CPO_DELY_INFO
        origDsCpoDelyInfoTMsg.setSQLID("001");
        origDsCpoDelyInfoTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsCpoDelyInfoTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_CPO_DELY_INFOTMsgArray copyDsCpoDelyInfoTMsgArray = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoDelyInfoTMsg);
        List<DS_CPO_DELY_INFOTMsg> copyDsCpoDelyInfoTMsgList = new ArrayList<DS_CPO_DELY_INFOTMsg>(0);
        for (int i = 0; i < copyDsCpoDelyInfoTMsgArray.getValidCount(); i++) {
            DS_CPO_DELY_INFOTMsg copyDsCpoDelyInfoTMsg = new DS_CPO_DELY_INFOTMsg();
            EZDMsg.copy(copyDsCpoDelyInfoTMsgArray.no(i), null, copyDsCpoDelyInfoTMsg, null);

            copyDsCpoDelyInfoTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoDelyInfoTMsg.dsCpoDelyInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(copyDsCpoDelyInfoTMsg.rddDt, pMsg.slsDt);

            copyDsCpoDelyInfoTMsgList.add(copyDsCpoDelyInfoTMsg);
        }

        // For DS_CPO_CONFIG
        if (isNeedConfigCopy) {
            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
                origDsCpoDelyInfoTMsg.setSQLID("002");
                origDsCpoDelyInfoTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                origDsCpoDelyInfoTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
                DS_CPO_DELY_INFOTMsgArray copyDsCpoDelyInfoTMsgConfigArray = (DS_CPO_DELY_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoDelyInfoTMsg);

                for (int i = 0; i < copyDsCpoDelyInfoTMsgConfigArray.getValidCount(); i++) {
                    DS_CPO_DELY_INFOTMsg copyDsCpoDelyInfoTMsg = new DS_CPO_DELY_INFOTMsg();
                    EZDMsg.copy(copyDsCpoDelyInfoTMsgConfigArray.no(i), null, copyDsCpoDelyInfoTMsg, null);

                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoDelyInfoTMsg.dsCpoConfigPk.getValue());
                    if (copyDsCpoConfigTMsg == null) {
                        continue;
                    }
                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();

                    copyDsCpoDelyInfoTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
                    copyDsCpoDelyInfoTMsg.dsCpoDelyInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_DELY_INFO_SQ));
                    copyDsCpoDelyInfoTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
                    ZYPEZDItemValueSetter.setValue(copyDsCpoDelyInfoTMsg.rddDt, pMsg.slsDt);

                    // 2020/01/24 QC#54725 Mod Start
                    //copyDsCpoDelyInfoTMsgList.add(copyDsCpoDelyInfoTMsg);
                    EZDTBLAccessor.insert(copyDsCpoDelyInfoTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(copyDsCpoDelyInfoTMsg.getReturnCode())) {
                        throw new S21AbendException("DS_CPO_DELY_INFO : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
                    }
                    if (!new SchedulingCommentsClobAccessor(copyDsCpoDelyInfoTMsg).updateSql(new SchedulingCommentsClobAccessor(copyDsCpoDelyInfoTMsgConfigArray.no(i)).getSql())) {
                        throw new S21AbendException("DS_CPO_DELY_INFO : Insert (CROB) Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
                    }
                    // 2020/01/24 QC#54725 Mod End
                }
            }
        }

        // 2020/01/24 QC#54725 Del Start
        //if (!copyDsCpoDelyInfoTMsgList.isEmpty()) {
        //    int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoDelyInfoTMsgList.toArray(new DS_CPO_DELY_INFOTMsg[0]));
        //    if (rsltCnt != copyDsCpoDelyInfoTMsgList.size()) {
        //        throw new S21AbendException("DS_CPO_DELY_INFO : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
        //    }
        //}
        // 2020/01/24 QC#54725 Del End

        return false;
    }

    private boolean registCopyDsCpoIstlInfo(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, boolean isNeedConfigCopy) {

        DS_CPO_ISTL_INFOTMsg origDsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();

        // For Header DS_CPO_ISTL_INFO
        origDsCpoIstlInfoTMsg.setSQLID("001");
        origDsCpoIstlInfoTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsCpoIstlInfoTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_CPO_ISTL_INFOTMsgArray copyDsCpoIstlInfoTMsgArray = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoIstlInfoTMsg);
        List<DS_CPO_ISTL_INFOTMsg> copyDsCpoIstlInfoTMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>(0);
        for (int i = 0; i < copyDsCpoIstlInfoTMsgArray.getValidCount(); i++) {
            DS_CPO_ISTL_INFOTMsg copyDsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();
            EZDMsg.copy(copyDsCpoIstlInfoTMsgArray.no(i), null, copyDsCpoIstlInfoTMsg, null);

            copyDsCpoIstlInfoTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoIstlInfoTMsg.dsCpoIstlInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ));
            ZYPEZDItemValueSetter.setValue(copyDsCpoIstlInfoTMsg.rqstIstlDt, pMsg.slsDt);

            copyDsCpoIstlInfoTMsgList.add(copyDsCpoIstlInfoTMsg);
        }

        // For DS_CPO_CONFIG
        if (isNeedConfigCopy) {
            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
                origDsCpoIstlInfoTMsg.setSQLID("002");
                origDsCpoIstlInfoTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                origDsCpoIstlInfoTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
                DS_CPO_ISTL_INFOTMsgArray copyDsCpoIstlInfoTMsgConfigArray = (DS_CPO_ISTL_INFOTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoIstlInfoTMsg);

                for (int i = 0; i < copyDsCpoIstlInfoTMsgConfigArray.getValidCount(); i++) {
                    DS_CPO_ISTL_INFOTMsg copyDsCpoIstlInfoTMsg = new DS_CPO_ISTL_INFOTMsg();
                    EZDMsg.copy(copyDsCpoIstlInfoTMsgConfigArray.no(i), null, copyDsCpoIstlInfoTMsg, null);

                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoIstlInfoTMsg.dsCpoConfigPk.getValue());
                    if (copyDsCpoConfigTMsg == null) {
                        continue;
                    }
                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();

                    copyDsCpoIstlInfoTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
                    copyDsCpoIstlInfoTMsg.dsCpoIstlInfoPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_ISTL_INFO_SQ));
                    copyDsCpoIstlInfoTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
                    ZYPEZDItemValueSetter.setValue(copyDsCpoIstlInfoTMsg.rqstIstlDt, pMsg.slsDt);

                    copyDsCpoIstlInfoTMsgList.add(copyDsCpoIstlInfoTMsg);
                }
            }
        }

        if (!copyDsCpoIstlInfoTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoIstlInfoTMsgList.toArray(new DS_CPO_ISTL_INFOTMsg[0]));
            if (rsltCnt != copyDsCpoIstlInfoTMsgList.size()) {
                throw new S21AbendException("DS_CPO_ISTL_INFO : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }

        return false;
    }

    private boolean registCopyDsSiteSrvy(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, boolean isNeedConfigCopy) {

        DS_SITE_SRVYTMsg origDsSiteSrvyTMsg = new DS_SITE_SRVYTMsg();

        // For Header DS_SITE_SRVY
        origDsSiteSrvyTMsg.setSQLID("005");
        origDsSiteSrvyTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsSiteSrvyTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_SITE_SRVYTMsgArray copyDsSiteSrvyTMsgArray = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(origDsSiteSrvyTMsg);
        List<DS_SITE_SRVYTMsg> copyDsSiteSrvyTMsgList = new ArrayList<DS_SITE_SRVYTMsg>(0);
        for (int i = 0; i < copyDsSiteSrvyTMsgArray.getValidCount(); i++) {
            DS_SITE_SRVYTMsg copyDsSiteSrvyTMsg = new DS_SITE_SRVYTMsg();
            EZDMsg.copy(copyDsSiteSrvyTMsgArray.no(i), null, copyDsSiteSrvyTMsg, null);

            copyDsSiteSrvyTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsSiteSrvyTMsg.dsSiteSrvyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));

            copyDsSiteSrvyTMsgList.add(copyDsSiteSrvyTMsg);
        }

        // For DS_CPO_CONFIG
        if (isNeedConfigCopy) {
            for (BigDecimal origdDsCpoConfigPk : dsCpoConfigMap.keySet()) {
                origDsSiteSrvyTMsg.setSQLID("006");
                origDsSiteSrvyTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                origDsSiteSrvyTMsg.setConditionValue("dsCpoConfigPk01", origdDsCpoConfigPk);
                DS_SITE_SRVYTMsgArray copyDsSiteSrvyTMsgConfigArray = (DS_SITE_SRVYTMsgArray) EZDTBLAccessor.findByCondition(origDsSiteSrvyTMsg);

                for (int i = 0; i < copyDsSiteSrvyTMsgConfigArray.getValidCount(); i++) {
                    DS_SITE_SRVYTMsg copyDsSiteSrvyTMsg = new DS_SITE_SRVYTMsg();
                    EZDMsg.copy(copyDsSiteSrvyTMsgConfigArray.no(i), null, copyDsSiteSrvyTMsg, null);

                    DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsSiteSrvyTMsg.dsCpoConfigPk.getValue());
                    if (copyDsCpoConfigTMsg == null) {
                        continue;
                    }
                    BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();

                    copyDsSiteSrvyTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
                    copyDsSiteSrvyTMsg.dsSiteSrvyPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SITE_SRVY_SQ));
                    copyDsSiteSrvyTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);

                    copyDsSiteSrvyTMsgList.add(copyDsSiteSrvyTMsg);
                }
            }
        }

        if (!copyDsSiteSrvyTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsSiteSrvyTMsgList.toArray(new DS_SITE_SRVYTMsg[0]));
            if (rsltCnt != copyDsSiteSrvyTMsgList.size()) {
                throw new S21AbendException("DS_SITE_SRVY : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }

        return false;
    }

    private boolean registCopyDsCpoCtacPsnForHdr(NWZC150001PMsg pMsg) {

        DS_CPO_CTAC_PSNTMsg origDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();

        origDsCpoCtacPsnTMsg.setSQLID("009");
        origDsCpoCtacPsnTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsCpoCtacPsnTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        DS_CPO_CTAC_PSNTMsgArray copyDsCpoCtacPsnTMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoCtacPsnTMsg);

        List<DS_CPO_CTAC_PSNTMsg> copyDsCpoCtacPsnTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>(0);

        for (int i = 0; i < copyDsCpoCtacPsnTMsgArray.getValidCount(); i++) {
            DS_CPO_CTAC_PSNTMsg copyDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();
            EZDMsg.copy(copyDsCpoCtacPsnTMsgArray.no(i), null, copyDsCpoCtacPsnTMsg, null);

            copyDsCpoCtacPsnTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoCtacPsnTMsg.dsCpoCtacPsnPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
            copyDsCpoCtacPsnTMsgList.add(copyDsCpoCtacPsnTMsg);
        }

        if (!copyDsCpoCtacPsnTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoCtacPsnTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
            if (rsltCnt != copyDsCpoCtacPsnTMsgList.size()) {
                throw new S21AbendException("DS_CPO_CTAC_PSN : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }

        return false;
    }

    private boolean registCopyDsCpoConfig(NWZC150001PMsg pMsg, String userId, String timeStamp, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {

        DS_CPO_CONFIGTMsg origDsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
        origDsCpoConfigTMsg.setSQLID("001");
        origDsCpoConfigTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsCpoConfigTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        boolean isRetailEquipmentOrd = false;
        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd) //
                || !ZYPCommonFunc.hasValue(pMsg.dsOrdTpCd)) {
            CPOTMsg origDsCpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(origDsCpoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(origDsCpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);

            origDsCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(origDsCpoTMsg);
            if (origDsCpoTMsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, origDsCpoTMsg.dsOrdCatgCd);
                ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, origDsCpoTMsg.dsOrdTpCd);
            }
        }
        isRetailEquipmentOrd = isExistOrdCatg(pMsg);

        DS_CPO_CONFIGTMsgArray copyDsCpoConfigTMsgArray = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoConfigTMsg);
        List<DS_CPO_CONFIGTMsg> copyDsCpoConfigTMsgList = new ArrayList<DS_CPO_CONFIGTMsg>(0);
        for (int i = 0; i < copyDsCpoConfigTMsgArray.getValidCount(); i++) {
            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
            EZDMsg.copy(copyDsCpoConfigTMsgArray.no(i), null, copyDsCpoConfigTMsg, null);

            copyDsCpoConfigTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            BigDecimal dsCpoConfigPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CONFIG_SQ);
            copyDsCpoConfigTMsg.dsCpoConfigPk.setValue(dsCpoConfigPk);
            BigDecimal svcConfigMstrPk = null;
            String configCatgCd = copyDsCpoConfigTMsg.configCatgCd.getValue();
            boolean newSvcConfigMstrFlg = false;
            if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
                CONFIG_TPTMsg configTpTMsg = new CONFIG_TPTMsg();
                configTpTMsg.glblCmpyCd.setValue(copyDsCpoConfigTMsg.glblCmpyCd.getValue());
                configTpTMsg.configTpCd.setValue(copyDsCpoConfigTMsg.configTpCd.getValue());
                configTpTMsg = (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(configTpTMsg);
                if (configTpTMsg != null) {
                    newSvcConfigMstrFlg = S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, configTpTMsg.configPkAsgFlg.getValue());
                    if (!isRetailEquipmentOrd) {
                        newSvcConfigMstrFlg = false;
                    }
                } else {
                    newSvcConfigMstrFlg = true;
                }
            } else if (CONFIG_CATG.INBOUND.equals(configCatgCd)) {
                if (NWXC150001DsCheck.matchConfigTp(pMsg.glblCmpyCd.getValue(), copyDsCpoConfigTMsg.configTpCd.getValue(), CONFIG_CATG.INBOUND, true, false, false)) {
                    newSvcConfigMstrFlg = false;
                    copyDsCpoConfigTMsg.svcConfigMstrPk.clear();
                }
            } else {
                continue;
            }
            if (newSvcConfigMstrFlg) {
                // 2018/03/01 S21_NA#24117 Mod Start
                if (ZYPCommonFunc.hasValue(copyDsCpoConfigTMsgArray.no(i).svcConfigMstrPk)) {
                    svcConfigMstrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ);
                    copyDsCpoConfigTMsg.svcConfigMstrPk.setValue(svcConfigMstrPk);
                }
                // 2018/03/01 S21_NA#24117 Mod Start
            }
            dsCpoConfigMap.put(copyDsCpoConfigTMsgArray.no(i).dsCpoConfigPk.getValue(), copyDsCpoConfigTMsg);
            copyDsCpoConfigTMsgList.add(copyDsCpoConfigTMsg);
        }
        if (!copyDsCpoConfigTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoConfigTMsgList.toArray(new DS_CPO_CONFIGTMsg[0]));
            if (rsltCnt != copyDsCpoConfigTMsgList.size()) {
                throw new S21AbendException("DS_CPO_CONFIG : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
            // Biz Log
            for (DS_CPO_CONFIGTMsg dsCpoConfigTMsg : copyDsCpoConfigTMsgList) {
                insertProcLog(dsCpoConfigTMsg);

                // Rec
                ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.ezUpTime, timeStamp);
                ZYPEZDItemValueSetter.setValue(dsCpoConfigTMsg.ezUpUserID, userId);

                BigDecimal bizProcLogLogPk = getBizProcLogPk(dsCpoConfigTMsg);
                if (bizProcLogLogPk != null) {
                    DS_CPO_CONFIG_RECTMsg dsCpoConfigRecMsg = new DS_CPO_CONFIG_RECTMsg();
                    EZDMsg.copy(dsCpoConfigTMsg, null, dsCpoConfigRecMsg, null);
                    dsCpoConfigRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
                    S21FastTBLAccessor.insert(dsCpoConfigRecMsg);
                }
            }
        }
        return false;
    }

    /**
     * check Exist Order Category
     * @param pMsg NWZC150001PMsg
     * @return true: exist
     */
    private boolean isExistOrdCatg(NWZC150001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd)) {
            return false;
        }

        // 2018/05/20 S21_NA#25604 Del Start
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.RETAIL_EQUIPMENT_ORDERS);
//        params.put("dsOrdCatgCd", pMsg.dsOrdCatgCd.getValue());
//        params.put("dsOrdTpCd", pMsg.dsOrdTpCd.getValue());
//        params.put("dsOrdRsnCd", pMsg.dsOrdRsnCd.getValue());
//
//        return 0 < (Integer) this.ssmClient.queryObject("isExistOrdCatg", params);
        // 2018/05/20 S21_NA#25604 Del End
        // 2018/05/20 S21_NA#25604 Add Start
        return NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), //
                pMsg.dsOrdCatgCd.getValue(), //
                pMsg.dsOrdTpCd.getValue(), //
                pMsg.dsOrdRsnCd.getValue());
        // 2018/05/20 S21_NA#25604 Add End
    }

    private void insertProcLog(DS_CPO_CONFIGTMsg dsCpoConfigTMsg) {

        String cpoOrdNum = dsCpoConfigTMsg.cpoOrdNum.getValue();
        String docId = dsCpoConfigTMsg.dsOrdPosnNum.getValue();
        String docTp = DOC_TP_OM;
        if (CONFIG_CATG.INBOUND.equals(dsCpoConfigTMsg.configCatgCd.getValue())) {
            docTp = DOC_TP_RT;
        }
        insertBizProcLog(cpoOrdNum, ORD_CREATE, docId, docTp);
    }

    private BigDecimal getBizProcLogPk(DS_CPO_CONFIGTMsg dsCpoConfigTMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
        queryParam.put("procId", DOC_TP_OM);
        queryParam.put("eventId", ORD_CREATE + "%");

        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, dsCpoConfigTMsg.configCatgCd.getValue())) {
            queryParam.put("docTpCd", DOC_TP_OM);
        } else {
            queryParam.put("docTpCd", DOC_TP_RT);
        }

        String docId = dsCpoConfigTMsg.dsOrdPosnNum.getValue();
        queryParam.put("docId", docId);
        queryParam.put("cpoOrdNum", dsCpoConfigTMsg.cpoOrdNum.getValue());
        queryParam.put("userId", dsCpoConfigTMsg.ezUpUserID.getValue());
        queryParam.put("ezUptime", dsCpoConfigTMsg.ezUpTime.getValue());

        return getMaxBizProcPk(queryParam);
    }

    private boolean registCopyCpoDtl(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp, NWZC150001CpouLocalCache localCache) {

        CPO_DTLTMsg origCpoDtlTMsg = new CPO_DTLTMsg();
        origCpoDtlTMsg.setSQLID("008");
        origCpoDtlTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origCpoDtlTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        CPO_DTLTMsgArray copyCpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(origCpoDtlTMsg);
        List<CPO_DTLTMsg> copyCpoDtlTMsgList = new ArrayList<CPO_DTLTMsg>(0);

        Map<String, CPO_DTLTMsg> setCacheMap = new HashMap<String, CPO_DTLTMsg>();
        for (int i = 0; i < copyCpoDtlTMsgArray.getValidCount(); i++) {
            CPO_DTLTMsg copyCpoDtlTMsg = new CPO_DTLTMsg();
            EZDMsg.copy(copyCpoDtlTMsgArray.no(i), null, copyCpoDtlTMsg, null);
            BigDecimal cancQty = copyCpoDtlTMsg.cancQty.getValue();
            BigDecimal ordQty = copyCpoDtlTMsg.ordQty.getValue();
            if ((cancQty != null && BigDecimal.ZERO.compareTo(cancQty) < 0) //
                    && (ordQty != null && BigDecimal.ZERO.compareTo(ordQty) == 0)) {
                copyCpoDtlTMsg.ordQty.setValue(cancQty);
                // 2018/09/05 S21_NA#26755 Add Start
                int ordCustUomQty = getOrdCustUomQty(pMsg.glblCmpyCd.getValue(), copyCpoDtlTMsg.mdseCd.getValue(), copyCpoDtlTMsg.custUomCd.getValue(), cancQty.intValue());
                copyCpoDtlTMsg.ordCustUomQty.setValue(ordCustUomQty);
                // 2018/09/05 S21_NA#26755 Add End
            }
            copyCpoDtlTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyCpoDtlTMsg.ordLineStsCd.setValue(ORD_LINE_STS.SAVED);
            copyCpoDtlTMsg.ordLineDplyStsCd.setValue(ORD_LINE_DPLY_STS.ENTERED);
            copyCpoDtlTMsg.shipQty.setValue(BigDecimal.ZERO);
            copyCpoDtlTMsg.cancQty.setValue(BigDecimal.ZERO);
            copyCpoDtlTMsg.origCpoOrdNum.clear();
            copyCpoDtlTMsg.origInvNum.clear();
            copyCpoDtlTMsg.cpoDtlCancFlg.setValue(ZYPConstant.FLG_OFF_N);
            copyCpoDtlTMsg.cpoDtlCancDt.clear();
            copyCpoDtlTMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
            copyCpoDtlTMsg.submtFlg.setValue(ZYPConstant.FLG_OFF_N);
            copyCpoDtlTMsg.openFlg.setValue(ZYPConstant.FLG_ON_Y);

            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyCpoDtlTMsg.dsCpoConfigPk.getValue());
            if (copyDsCpoConfigTMsg == null) {
                continue;
            }
            BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
            copyCpoDtlTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyCpoDtlTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
            copyCpoDtlTMsg.loanBalQty.setValue(BigDecimal.ZERO);
            copyCpoDtlTMsg.origCpoDtlLineNum.clear();
            copyCpoDtlTMsg.origCpoDtlLineSubNum.clear();
            ZYPEZDItemValueSetter.setValue(copyCpoDtlTMsg.svcConfigMstrPk, copyDsCpoConfigTMsg.svcConfigMstrPk);
            copyCpoDtlTMsg.dsCpoDtlCratTs.setValue(timeStamp);
            copyCpoDtlTMsg.dsCpoDtlCratUsrId.setValue(userId);
            copyCpoDtlTMsg.dsCpoDtlUpdTs.setValue(timeStamp);
            copyCpoDtlTMsg.dsCpoDtlUpdUsrId.setValue(userId);
            // 2017/07/05 S21_NA#19266 Add Start
            copyCpoDtlTMsg.cpoOrdTs.clear();
            ZYPEZDItemValueSetter.setValue(copyCpoDtlTMsg.prcBaseDt, pMsg.slsDt);
            // 2017/07/05 S21_NA#19266 Add End

            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            setRddRsdExpdDt(pMsg, cpoTMsg, copyCpoDtlTMsg, setCacheMap);
            setRddRsdExpdDt(pMsg, cpoTMsg, copyCpoDtlTMsg, setCacheMap, localCache);
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End

            // Add Start 2019/05/30 QC#50405
            copyCpoDtlTMsg.slsRepOrSlsTeamTocCd.setValue(pMsg.slsRepCd.getValue());
            // Add End 2019/05/30 QC#50405
            ZYPEZDItemValueSetter.setValue(copyCpoDtlTMsg.cpoSrcTpCd, CPO_SRC_TP.COPY); //ADD 2019/06/05 QC#50710

            copyCpoDtlTMsgList.add(copyCpoDtlTMsg);
        }

        for (String cpoDtlLineNum : setCacheMap.keySet()) {
            CPO_DTLTMsg childCpoDtlTMsg = setCacheMap.get(cpoDtlLineNum);
            for (int i = 0; i < copyCpoDtlTMsgList.size(); i++) {
                CPO_DTLTMsg setCpoDtlTMsg = copyCpoDtlTMsgList.get(i);
                if ("000".equals(setCpoDtlTMsg.cpoDtlLineSubNum.getValue())
                        && S21StringUtil.isEquals(cpoDtlLineNum, setCpoDtlTMsg.cpoDtlLineNum.getValue())) {
                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rddDt, childCpoDtlTMsg.rddDt);
                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rsdDt, childCpoDtlTMsg.rsdDt);
                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.expdShipDt, childCpoDtlTMsg.expdShipDt);
                }
            }
        }

        if (!copyCpoDtlTMsgList.isEmpty()) {
            // 2018/09/05 S21_NA#26755 Add Start
            // resetBaseCmptFlg(pMsg, copyCpoDtlTMsgList); // 2019/02/05 S21_NA#30149 Mod
            resetBaseCmptFlg(pMsg, copyCpoDtlTMsgList, dsCpoConfigMap);
            // 2018/09/05 S21_NA#26755 Add End
            int rsltCnt = S21FastTBLAccessor.insert(copyCpoDtlTMsgList.toArray(new CPO_DTLTMsg[0]));
            if (rsltCnt != copyCpoDtlTMsgList.size()) {
                throw new S21AbendException("CPO_DTL : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }

        for (String cpoDtlLineNum : setCacheMap.keySet()) {
            CPO_DTLTMsg childCpoDtlTMsg = setCacheMap.get(cpoDtlLineNum);

            for (int i = 0; i < copyCpoDtlTMsgList.size(); i++) {
                CPO_DTLTMsg setCpoDtlTMsg = copyCpoDtlTMsgList.get(i);
                if ("000".equals(setCpoDtlTMsg.cpoDtlLineSubNum.getValue())
                        && S21StringUtil.isEquals(cpoDtlLineNum, setCpoDtlTMsg.cpoDtlLineNum.getValue())) {
                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rddDt, childCpoDtlTMsg.rddDt);
                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.rsdDt, childCpoDtlTMsg.rsdDt);
                   ZYPEZDItemValueSetter.setValue(setCpoDtlTMsg.expdShipDt, childCpoDtlTMsg.expdShipDt);
                }
            }
        }

        // Biz Log
        for (CPO_DTLTMsg cpoDtlTMsg : copyCpoDtlTMsgList) {
            insertProcLog(cpoDtlTMsg);

            // Rec
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpTime, timeStamp);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpUserID, userId);

            BigDecimal bizProcLogLogPk = getBizProcLogPk(cpoDtlTMsg);
            if (bizProcLogLogPk != null) {
                CPO_DTL_RECTMsg cpoDtlRecMsg = new CPO_DTL_RECTMsg();
                EZDMsg.copy(cpoDtlTMsg, null, cpoDtlRecMsg, null);
                cpoDtlRecMsg.bizProcLogPk.setValue(bizProcLogLogPk);
                S21FastTBLAccessor.insert(cpoDtlRecMsg);
            }
        }

        return false;
    }

    private void setRddRsdExpdDt(NWZC150001PMsg pMsg, CPOTMsg cpoTMsg, CPO_DTLTMsg cpoDtlTMsg, Map<String, CPO_DTLTMsg> setCacheMap, NWZC150001CpouLocalCache localCache) {

        if (ZYPCommonFunc.hasValue(cpoDtlTMsg.setMdseCd)) {
            CPO_DTLTMsg setCpoDtlTMsg = setCacheMap.get(cpoDtlTMsg.cpoDtlLineNum.getValue());
            if (setCpoDtlTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rsdDt, setCpoDtlTMsg.rsdDt);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.expdShipDt, setCpoDtlTMsg.expdShipDt);
                ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rddDt, setCpoDtlTMsg.rddDt);
                return;
            }
        }
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        String rdd = slsDt;
        String rsd = "";
        String esd = "";

        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        slsDt = adjustBizDay(glblCmpyCd, cpoDtlTMsg.invtyLocCd.getValue(), slsDt);
        slsDt = adjustBizDay(glblCmpyCd, cpoDtlTMsg.invtyLocCd.getValue(), slsDt, localCache);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        boolean callCalcLtAPI = false;
        if (!"000".equals(cpoDtlTMsg.cpoDtlLineSubNum.getValue())) {
            callCalcLtAPI = true;
        }
        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, cpoDtlTMsg.mdseCd.getValue());
        if (mdseTMsg == null) {
            callCalcLtAPI = false;
        } else if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, mdseTMsg.invtyCtrlFlg.getValue())) {
            callCalcLtAPI = false;
        }
        if (!callCalcLtAPI) {
            cpoDtlTMsg.rddDt.clear();
            cpoDtlTMsg.rsdDt.clear();
            cpoDtlTMsg.expdShipDt.clear();
            return;
        }
        if (callCalcLtAPI) {
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            if (isExportForCtry(glblCmpyCd, cpoDtlTMsg.shipToCtryCd.getValue())) {
            if (isExportForCtry(glblCmpyCd, cpoDtlTMsg.shipToCtryCd.getValue(), localCache)) {
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
                // [SHPG_SVC_LVL]
                SHPG_SVC_LVLTMsg shpgSvcLvlTMsg = new SHPG_SVC_LVLTMsg();
                ZYPEZDItemValueSetter.setValue(shpgSvcLvlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(shpgSvcLvlTMsg.shpgSvcLvlCd, cpoDtlTMsg.shpgSvcLvlCd.getValue());
                shpgSvcLvlTMsg = (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(shpgSvcLvlTMsg);

                // needs shipToStCd? (0:Ground Standard
                // Delivery, 1:Pick Up)
                boolean needsShipToStCd = false;
                if (shpgSvcLvlTMsg != null) {
                    needsShipToStCd = asList(SHPG_SVC_TP.GROUND_STANDARD_DELIVERY, SHPG_SVC_TP.PICK_UP).contains(shpgSvcLvlTMsg.shpgSvcTpCd.getValue());
                }

                if (needsShipToStCd) {

                    // State
                    final String shipToStCd = cpoDtlTMsg.shipToStCd.getValue();
                    if (!ZYPCommonFunc.hasValue(shipToStCd)) {
                        callCalcLtAPI = false;
                    } else if (!existsShipToSt(glblCmpyCd, shipToStCd)) {
                        callCalcLtAPI = false;
                    }

                    if (!callCalcLtAPI) {
                        // RSD
                        if (!ZYPCommonFunc.hasValue(rsd)) {
                            rsd = slsDt;
                        }
                        esd = rsd;
                    }
                }
            }
        }

        // --------------------------------------------------
        // [NWZC002001] : Lead Time Calculation API
        // --------------------------------------------------
        boolean hasError = false;

        if (callCalcLtAPI) {

            final NWZC002001PMsg apiPMsg = new NWZC002001PMsg();
            ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.mdseCd, mdseTMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.ordQty, cpoDtlTMsg.ordQty);
            ZYPEZDItemValueSetter.setValue(apiPMsg.shpgSvcLvlCd, cpoDtlTMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.frtChrgToCd, cpoDtlTMsg.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.frtChrgMethCd, cpoDtlTMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.xxRddDt, rdd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.invtyLocCd, cpoDtlTMsg.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToPostCd, cpoDtlTMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustCd, cpoDtlTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.shipToStCd, cpoDtlTMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.sellToCustCd, cpoTMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.uomCd, cpoDtlTMsg.custUomCd);
            ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, slsDt);

            new NWZC002001().execute(apiPMsg, this.onBatchType);

            // has Error.
            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                hasError = true;
            } else {
                // ESD = RDD - a longest transportation lead
                // time within the SSL
                esd = apiPMsg.xxPsdDt.getValue();
                // RSD = RDD - a longest transportation lead
                // time within the SSL -
                // mercahndise.allocation lead time
                rsd = apiPMsg.xxPsdDt.getValue();
                int daysPriAllocNum = nullToZero(mdseTMsg.daysPriAllocNum.getValue()).intValue();
                if (daysPriAllocNum > 0) {
                    rsd = ZYPDateUtil.addDays(esd, -1 * daysPriAllocNum);
                    // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//                    rsd = adjustBizDay(glblCmpyCd, cpoDtlTMsg.invtyLocCd.getValue(), rsd);
                    rsd = adjustBizDay(glblCmpyCd, cpoDtlTMsg.invtyLocCd.getValue(), rsd, localCache);
                    // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
                }
            }
        }

        // set RSD/ESD/RDD
        if (!hasError) {

            // RSD/ESD/RDD
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rsdDt, rsd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.expdShipDt, esd);
            ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.rddDt, rdd);

            // Component
            if (ZYPCommonFunc.hasValue(cpoDtlTMsg.setMdseCd)) {
                final String key = cpoDtlTMsg.cpoDtlLineNum.getValue();
                if (!setCacheMap.containsKey(key)) {
                    setCacheMap.put(key, cpoDtlTMsg);
                }
            }
        }
    }

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            MDSETMsg queryMdseTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

            mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
            if (mdseTMsg == null) {

                ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
                ordTakeMdseMsg.setSQLID("002");
                ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

                ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
                if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                    mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
                }
            }
        }
        return mdseTMsg;
    }

    private String adjustBizDay(String glblCmpyCd, String invtyLocCd, String targetDate, NWZC150001CpouLocalCache localCache) {

        // Illegal date.
        if (!ZYPCommonFunc.hasValue(targetDate)) {
            return targetDate;
        }

        // WAREHOUSE_CALENDAR
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        CAL_RELNTMsg calReln = getCalRelnTMsg(glblCmpyCd, CAL_SUB_TP.WAREHOUSE_CALENDAR, invtyLocCd);
        CAL_RELNTMsg calReln = getCalRelnTMsg(glblCmpyCd, CAL_SUB_TP.WAREHOUSE_CALENDAR, invtyLocCd, localCache);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End

        if (calReln == null) {
            return targetDate;
        }

        final String calTpCd = calReln.calTpCd.getValue();

        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        if (isBizDay(glblCmpyCd, calTpCd, targetDate)) {
        if (isBizDay(glblCmpyCd, calTpCd, targetDate, localCache)) {
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
            return targetDate;
        } else {
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//            return getBizDay(glblCmpyCd, calTpCd, targetDate);
            return getBizDay(glblCmpyCd, calTpCd, targetDate, localCache);
            // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
        }
    }

    private CAL_RELNTMsg getCalRelnTMsg(String glblCmpyCd, String calSubTpCd, String calMultCd, NWZC150001CpouLocalCache localCache) {

        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        final CAL_RELNTMsg calRelnTMsg = new CAL_RELNTMsg();
//        ZYPEZDItemValueSetter.setValue(calRelnTMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(calRelnTMsg.calSubTpCd, calSubTpCd);
//        ZYPEZDItemValueSetter.setValue(calRelnTMsg.calMultCd, calMultCd);
//
//        return (CAL_RELNTMsg) S21CacheTBLAccessor.findByKey(calRelnTMsg);
        return localCache.bizDayCache.getTMsgByKey(glblCmpyCd, calSubTpCd, calMultCd);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
    }

    private boolean isBizDay(String glblCmpyCd, String calTpCd, String targetDate, NWZC150001CpouLocalCache localCache) {

        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        boolean isBizDay = false;
//        try {
//            isBizDay = ZYPDateUtil.isBusinessDayEx(glblCmpyCd, calTpCd, targetDate);
//        } catch (S21AbendException e) {
//            isBizDay = false;
//        }
//
//        return isBizDay;
        return localCache.bizDayCache.isBizDay(glblCmpyCd, calTpCd, targetDate);
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
    }

    private String getBizDay(String glblCmpyCd, String calTpCd, String targetDate, NWZC150001CpouLocalCache localCache) {

        // 2017/05/11 S21_NA#Review structure Lv.2 Add Start
//        String bizDay;
//        try {
//            bizDay = ZYPDateUtil.addBusinessDayEx(glblCmpyCd, calTpCd, targetDate, -1);
//        } catch (S21AbendException e) {
//            bizDay = targetDate;
//        }
//
//        return bizDay;
        return localCache.bizDayCache.getBizDay(glblCmpyCd, calTpCd, targetDate);
        // 2017/05/11 S21_NA#Review structure Lv.2 Add End
    }

    private boolean isExportForCtry(String glblCmpyCd, String shipToCtryCd, NWZC150001CpouLocalCache localCache) {

        // 2017/05/11 S21_NA#Review structure Lv.2 Mod Start
//        return NWXC001001Export.isExportForCtry(glblCmpyCd, shipToCtryCd);
        final StringBuilder cacheKeySb = new StringBuilder();
        cacheKeySb.append(glblCmpyCd).append(",");
        cacheKeySb.append(shipToCtryCd);

        final String cacheKey = cacheKeySb.toString();

        Boolean checkRes = localCache.exportForCtryCheckResultCache.get(cacheKey);
        if (checkRes == null) {
            checkRes = NWXC001001Export.isExportForCtry(glblCmpyCd, shipToCtryCd);
            localCache.exportForCtryCheckResultCache.put(cacheKey, checkRes);
        }
        return checkRes;
        // 2017/05/11 S21_NA#Review structure Lv.2 Mod End
    }

    private boolean existsShipToSt(String glblCmpyCd, String shipToStCd) {

        if (!ZYPCommonFunc.hasValue(shipToStCd)) {
            return true;
        }

        final STTMsg sTTMsg = new STTMsg();
        sTTMsg.glblCmpyCd.setValue(glblCmpyCd);
        sTTMsg.stCd.setValue(shipToStCd);

        return S21CacheTBLAccessor.findByKey(sTTMsg) != null;
    }

    private BigDecimal nullToZero(BigDecimal decimal) {
        if (ZYPCommonFunc.hasValue(decimal)) {
            return decimal;
        }
        return BigDecimal.ZERO;
    }

    private void insertProcLog(CPO_DTLTMsg cpoDtlTMsg) {

        String cpoOrdNum = cpoDtlTMsg.cpoOrdNum.getValue();
        String docId = cpoDtlTMsg.cpoDtlLineNum.getValue() + "." + cpoDtlTMsg.cpoDtlLineSubNum.getValue();
        insertBizProcLog(cpoOrdNum, ORD_CREATE, docId, DOC_TP_OM);
    }

    private BigDecimal getBizProcLogPk(CPO_DTLTMsg cpoDtlTMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
        queryParam.put("procId", DOC_TP_OM);
        queryParam.put("eventId", ORD_CREATE + "%");
        queryParam.put("docTpCd", DOC_TP_OM);

        String docId = cpoDtlTMsg.cpoDtlLineNum.getValue() + "." + cpoDtlTMsg.cpoDtlLineSubNum.getValue();
        queryParam.put("docId", docId);
        queryParam.put("cpoOrdNum", cpoDtlTMsg.cpoOrdNum.getValue());
        queryParam.put("userId", cpoDtlTMsg.ezUpUserID.getValue());
        queryParam.put("ezUptime", cpoDtlTMsg.ezUpTime.getValue());

        return getMaxBizProcPk(queryParam);
    }

    private boolean registCopyOrdPrcCalcBase(NWZC150001PMsg pMsg, String userId, String timeStamp) {

        ORD_PRC_CALC_BASETMsg origOrdPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();

        // For Header DS_CPO_SLS_CR
        // 2019/10/30 S21_QC#53556 Mod Start
        //origOrdPrcCalcBaseTMsg.setSQLID("002");
        origOrdPrcCalcBaseTMsg.setSQLID("004");
        // 2019/10/30 S21_QC#53556 Mod End
        origOrdPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origOrdPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        ORD_PRC_CALC_BASETMsgArray origOrdPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(origOrdPrcCalcBaseTMsg);
        List<ORD_PRC_CALC_BASETMsg> copyOrdPrcCalcBaseTMsgList = new ArrayList<ORD_PRC_CALC_BASETMsg>(0);
        for (int i = 0; i < origOrdPrcCalcBaseTMsgArray.getValidCount(); i++) {
            // QC#9700  2018/09/03 Add Start
            if(!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, origOrdPrcCalcBaseTMsgArray.no(i).prcRuleApplyFlg.getValue())){
                continue;
            }
            // QC#9700  2018/09/03 Add End
            ORD_PRC_CALC_BASETMsg copyOrdPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
            EZDMsg.copy(origOrdPrcCalcBaseTMsgArray.no(i), null, copyOrdPrcCalcBaseTMsg, null);

            copyOrdPrcCalcBaseTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyOrdPrcCalcBaseTMsg.ordPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRC_CALC_BASE_SQ));

            copyOrdPrcCalcBaseTMsgList.add(copyOrdPrcCalcBaseTMsg);
        }

        if (!copyOrdPrcCalcBaseTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyOrdPrcCalcBaseTMsgList.toArray(new ORD_PRC_CALC_BASETMsg[0]));
            if (rsltCnt != copyOrdPrcCalcBaseTMsgList.size()) {
                throw new S21AbendException("ORD_PRC_CALC_BASE : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }

            // REC
            Map<String, BigDecimal> recKeyMap = new HashMap<String, BigDecimal>(0);
            for (ORD_PRC_CALC_BASETMsg copyOrdPrcCalcBaseTMsg : copyOrdPrcCalcBaseTMsgList) {
                String hashKey = copyOrdPrcCalcBaseTMsg.cpoOrdNum.getValue() + ".";
                hashKey = hashKey + copyOrdPrcCalcBaseTMsg.cpoDtlLineNum.getValue() + ".";
                hashKey = hashKey + copyOrdPrcCalcBaseTMsg.cpoDtlLineSubNum.getValue();

                BigDecimal bizProcLogPk = recKeyMap.get(hashKey);
                if (bizProcLogPk == null) {
                    CPO_DTLTMsg cpoDtlTMsg = new CPO_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoOrdNum, copyOrdPrcCalcBaseTMsg.cpoOrdNum);
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineNum, copyOrdPrcCalcBaseTMsg.cpoDtlLineNum);
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.cpoDtlLineSubNum, copyOrdPrcCalcBaseTMsg.cpoDtlLineSubNum);
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpUserID, userId);
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.ezUpTime, timeStamp);

                    bizProcLogPk = getBizProcLogPk(cpoDtlTMsg);
                    if (bizProcLogPk == null) {
                        continue;
                    }
                    recKeyMap.put(hashKey, bizProcLogPk);
                }

                ORD_PRC_CALC_BASE_RECTMsg copyOrdPrcCalcBaseRecTMsg = new ORD_PRC_CALC_BASE_RECTMsg();
                EZDMsg.copy(copyOrdPrcCalcBaseTMsg, null, copyOrdPrcCalcBaseRecTMsg, null);
                copyOrdPrcCalcBaseRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
                S21FastTBLAccessor.insert(copyOrdPrcCalcBaseRecTMsg);
            }
        }
        return false;
    }

    private boolean registCopyDsCpoRtrnDtl(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap, String userId, String timeStamp, List<DS_CPO_RTRN_DTLTMsg> copyDsCpoRtrnDtlTMsgList) {

        DS_CPO_RTRN_DTLTMsg origDSCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
        origDSCpoRtrnDtlTMsg.setSQLID("001");
        origDSCpoRtrnDtlTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDSCpoRtrnDtlTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_CPO_RTRN_DTLTMsgArray copyDsCpoRtrnDtlTMsgArray = (DS_CPO_RTRN_DTLTMsgArray) EZDTBLAccessor.findByCondition(origDSCpoRtrnDtlTMsg);
        for (int i = 0; i < copyDsCpoRtrnDtlTMsgArray.getValidCount(); i++) {
            DS_CPO_RTRN_DTLTMsg copyDsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
            EZDMsg.copy(copyDsCpoRtrnDtlTMsgArray.no(i), null, copyDsCpoRtrnDtlTMsg, null);

            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoRtrnDtlTMsg.dsCpoConfigPk.getValue());
            if (copyDsCpoConfigTMsg == null) {
                continue;
            }
            BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();
            BigDecimal cancQty = copyDsCpoRtrnDtlTMsg.cancQty.getValue();
            BigDecimal ordQty = copyDsCpoRtrnDtlTMsg.ordQty.getValue();
            if ((cancQty != null && BigDecimal.ZERO.compareTo(cancQty) > 0) //
                    && (ordQty != null && BigDecimal.ZERO.compareTo(ordQty) == 0)) {
                copyDsCpoRtrnDtlTMsg.ordQty.setValue(cancQty);
                // 2018/09/05 S21_NA#26755 Add Start
                int ordCustUomQty = getOrdCustUomQty(pMsg.glblCmpyCd.getValue(), copyDsCpoRtrnDtlTMsg.mdseCd.getValue(), copyDsCpoRtrnDtlTMsg.custUomCd.getValue(), cancQty.intValue());
                copyDsCpoRtrnDtlTMsg.ordCustUomQty.setValue(ordCustUomQty);
                // 2018/09/05 S21_NA#26755 Add End
            }
            copyDsCpoRtrnDtlTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoRtrnDtlTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
            copyDsCpoRtrnDtlTMsg.rtrnLineStsCd.setValue(RTRN_LINE_STS.ENTERED);
            // 2017/05/24 S21_NA#Review structure Lv.2 RS#7262-2 Add Start
            copyDsCpoRtrnDtlTMsg.rtrnLineDplyStsCd.setValue(RTRN_LINE_DPLY_STS.ENTERED);
            // 2017/05/24 S21_NA#Review structure Lv.2 RS#7262-2 Add End
            copyDsCpoRtrnDtlTMsg.rtrnQty.setValue(BigDecimal.ZERO);
            copyDsCpoRtrnDtlTMsg.cancQty.setValue(BigDecimal.ZERO);
            copyDsCpoRtrnDtlTMsg.origCpoOrdNum.clear();
            copyDsCpoRtrnDtlTMsg.origCpoDtlLineNum.clear();
            copyDsCpoRtrnDtlTMsg.origCpoDtlLineSubNum.clear();
            copyDsCpoRtrnDtlTMsg.origInvNum.clear();
            copyDsCpoRtrnDtlTMsg.invNum.clear(); // 2018/03/14 S21_NA#23674 Add
            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.svcConfigMstrPk, copyDsCpoConfigTMsg.svcConfigMstrPk);
            copyDsCpoRtrnDtlTMsg.cpoOrdTs.clear();
            copyDsCpoRtrnDtlTMsg.cpoOrdSubmtTs.clear();
            copyDsCpoRtrnDtlTMsg.submtFlg.setValue(ZYPConstant.FLG_OFF_N);
            copyDsCpoRtrnDtlTMsg.openFlg.setValue(ZYPConstant.FLG_ON_Y);
            copyDsCpoRtrnDtlTMsg.cpoDtlCancFlg.setValue(ZYPConstant.FLG_OFF_N);
            copyDsCpoRtrnDtlTMsg.cpoDtlCancDt.clear();
            copyDsCpoRtrnDtlTMsg.cpoDtlHldFlg.setValue(ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.rqstPickUpDt, pMsg.slsDt);
            // 2017/07/05 S21_NA#19266 Add Start
            // Mod Start 2018/08/02 QC#26338
//            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.prcBaseDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.prcBaseDt, pMsg.prcCalcDt);
            // Mod End 2018/08/02 QC#26338
            // 2017/07/05 S21_NA#19266 Add End

            // Add Start 2019/05/30 QC#50405
            copyDsCpoRtrnDtlTMsg.slsRepOrSlsTeamTocCd.setValue(pMsg.slsRepCd.getValue());
            // Add End 2019/05/30 QC#50405
            ZYPEZDItemValueSetter.setValue(copyDsCpoRtrnDtlTMsg.cpoSrcTpCd, CPO_SRC_TP.COPY); //ADD 2019/06/05 QC#50710

            copyDsCpoRtrnDtlTMsgList.add(copyDsCpoRtrnDtlTMsg);

        }
        if (!copyDsCpoRtrnDtlTMsgList.isEmpty()) {
            // 2018/09/05 S21_NA#26755 Add Start
            resetBaseCmptFlg4Return(pMsg, copyDsCpoRtrnDtlTMsgList);
            // 2018/09/05 S21_NA#26755 Add Start
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoRtrnDtlTMsgList.toArray(new DS_CPO_RTRN_DTLTMsg[0]));
            if (rsltCnt != copyDsCpoRtrnDtlTMsgList.size()) {
                throw new S21AbendException("DS_CPO_RTRN_DTL : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
            for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : copyDsCpoRtrnDtlTMsgList) {
                // Biz Log
                insertProcLog(dsCpoRtrnDtlTMsg);

                // Rec
                dsCpoRtrnDtlTMsg.ezUpUserID.setValue(userId);
                dsCpoRtrnDtlTMsg.ezUpTime.setValue(timeStamp);
                BigDecimal bizProcLogPk = getBizProcLogPk(dsCpoRtrnDtlTMsg);
                if (bizProcLogPk != null) {
                    DS_CPO_RTRN_DTL_RECTMsg dsCpoRtrnDtlRecTMsg = new DS_CPO_RTRN_DTL_RECTMsg();
                    EZDMsg.copy(dsCpoRtrnDtlTMsg, null, dsCpoRtrnDtlRecTMsg, null);
                    dsCpoRtrnDtlRecTMsg.bizProcLogPk.setValue(bizProcLogPk);

                    S21FastTBLAccessor.insert(dsCpoRtrnDtlRecTMsg);
                }
            }
        }

        return false;
    }

    private void insertProcLog(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {

        boolean isPureRtrn = isPureReturn(dsCpoRtrnDtlTMsg);

        String cpoOrdNum = dsCpoRtrnDtlTMsg.cpoOrdNum.getValue();
        String docId = dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue() + "." + dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();
        String eventId = ORD_SAVE;

        if (isPureRtrn) {
            eventId = ORD_CREATE;
        }
        insertBizProcLog(cpoOrdNum, eventId, docId, DOC_TP_RT);
    }

    private boolean isPureReturn(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {

        boolean isPureRtrn = true;
        CPO_DTLTMsg origCpoDtlTMsg = new CPO_DTLTMsg();
        origCpoDtlTMsg.setSQLID("008");
        origCpoDtlTMsg.setConditionValue("glblCmpyCd01", dsCpoRtrnDtlTMsg.glblCmpyCd.getValue());
        origCpoDtlTMsg.setConditionValue("cpoOrdNum01", dsCpoRtrnDtlTMsg.cpoOrdNum.getValue());

        CPO_DTLTMsgArray copyCpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(origCpoDtlTMsg);

        if (copyCpoDtlTMsgArray.getValidCount() > 0) {
            isPureRtrn = false;
        }
        return isPureRtrn;
    }

    private BigDecimal getBizProcLogPk(DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
        queryParam.put("procId", DOC_TP_OM);

        boolean isPureRtrn = isPureReturn(dsCpoRtrnDtlTMsg);
        String eventId = ORD_SAVE;

        if (isPureRtrn) {
            eventId = ORD_CREATE;
        }

        queryParam.put("eventId", eventId + "%");
        queryParam.put("docTpCd", DOC_TP_RT);

        String docId = dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue() + "." + dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue();
        queryParam.put("docId", docId);
        queryParam.put("cpoOrdNum", dsCpoRtrnDtlTMsg.cpoOrdNum.getValue());
        queryParam.put("userId", dsCpoRtrnDtlTMsg.ezUpUserID.getValue());
        queryParam.put("ezUptime", dsCpoRtrnDtlTMsg.ezUpTime.getValue());

        return getMaxBizProcPk(queryParam);
    }

    private boolean registCopyCpoRtrnPrcCalcBase(NWZC150001PMsg pMsg,  List<DS_CPO_RTRN_DTLTMsg> copyDsCpoRtrnDtlTMsgList, String userId, String timeStamp) {

        List<CPO_RTRN_PRC_CALC_BASETMsg> copyCpoRtrnPrcCalcBaseTMsgList = new ArrayList<CPO_RTRN_PRC_CALC_BASETMsg>(0);

        for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : copyDsCpoRtrnDtlTMsgList) {
            CPO_RTRN_PRC_CALC_BASETMsg origOrdPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();

            origOrdPrcCalcBaseTMsg.setSQLID("001");
            origOrdPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            origOrdPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
            origOrdPrcCalcBaseTMsg.setConditionValue("dsCpoRtrnLineNum01", dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue());
            origOrdPrcCalcBaseTMsg.setConditionValue("dsCpoRtrnLineSubNum01", dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue());

            CPO_RTRN_PRC_CALC_BASETMsgArray origOrdPrcCalcBaseTMsgArray = (CPO_RTRN_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(origOrdPrcCalcBaseTMsg);
            for (int i = 0; i < origOrdPrcCalcBaseTMsgArray.getValidCount(); i++) {
                // QC#9700  2018/09/03 Add Start
                if(!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, origOrdPrcCalcBaseTMsgArray.no(i).prcRuleApplyFlg.getValue())){
                    continue;
                }
                // QC#9700  2018/09/03 Add End
                CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseTMsg = new CPO_RTRN_PRC_CALC_BASETMsg();
                EZDMsg.copy(origOrdPrcCalcBaseTMsgArray.no(i), null, cpoRtrnPrcCalcBaseTMsg, null);

                cpoRtrnPrcCalcBaseTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
                cpoRtrnPrcCalcBaseTMsg.cpoRtrnPrcCalcBasePk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_RTRN_PRC_CALC_BASE_SQ));

                copyCpoRtrnPrcCalcBaseTMsgList.add(cpoRtrnPrcCalcBaseTMsg);
            }

        }
        if (!copyCpoRtrnPrcCalcBaseTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyCpoRtrnPrcCalcBaseTMsgList.toArray(new CPO_RTRN_PRC_CALC_BASETMsg[0]));
            if (rsltCnt != copyCpoRtrnPrcCalcBaseTMsgList.size()) {
                throw new S21AbendException("CPO_RTRN_PRC_CALC_BASE : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }
        // REC
        Map<String, BigDecimal> recKeyMap = new HashMap<String, BigDecimal>(0);
        for (CPO_RTRN_PRC_CALC_BASETMsg copyCpoRtrnPrcCalcBaseTMsg  : copyCpoRtrnPrcCalcBaseTMsgList) {
            String hashKey = copyCpoRtrnPrcCalcBaseTMsg.cpoOrdNum.getValue() + ".";
            hashKey = hashKey + copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineNum.getValue() + ".";
            hashKey = hashKey + copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineSubNum.getValue();

            BigDecimal bizProcLogPk = recKeyMap.get(hashKey);
            if (bizProcLogPk == null) {
                DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg = new DS_CPO_RTRN_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.cpoOrdNum, copyCpoRtrnPrcCalcBaseTMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum, copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineNum);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum, copyCpoRtrnPrcCalcBaseTMsg.dsCpoRtrnLineSubNum);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.ezUpUserID, userId);
                ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.ezUpTime, timeStamp);

                bizProcLogPk = getBizProcLogPk(dsCpoRtrnDtlTMsg);
                if (bizProcLogPk == null) {
                    continue;
                }
                recKeyMap.put(hashKey, bizProcLogPk);
            }

            CPO_RTRN_CALC_BASE_RECTMsg copyCpoRtrnPrcCalcBaseRecTMsg = new CPO_RTRN_CALC_BASE_RECTMsg();
            EZDMsg.copy(copyCpoRtrnPrcCalcBaseTMsg, null, copyCpoRtrnPrcCalcBaseRecTMsg, null);
            copyCpoRtrnPrcCalcBaseRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
            S21FastTBLAccessor.insert(copyCpoRtrnPrcCalcBaseRecTMsg);
        }
        return false;
    }

    private boolean registCopyDsCpoCtacPsn(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {

        DS_CPO_CTAC_PSNTMsg origDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();

        origDsCpoCtacPsnTMsg.setSQLID("005");
        origDsCpoCtacPsnTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsCpoCtacPsnTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_CPO_CTAC_PSNTMsgArray copyDsCpoCtacPsnTMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoCtacPsnTMsg);
        List<DS_CPO_CTAC_PSNTMsg> copyDsCpoCtacPsnTMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>(0);
        for (int i = 0; i < copyDsCpoCtacPsnTMsgArray.getValidCount(); i++) {
            DS_CPO_CTAC_PSNTMsg copyDsCpoCtacPsnTMsg = new DS_CPO_CTAC_PSNTMsg();
            EZDMsg.copy(copyDsCpoCtacPsnTMsgArray.no(i), null, copyDsCpoCtacPsnTMsg, null);

            copyDsCpoCtacPsnTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
            copyDsCpoCtacPsnTMsg.dsCpoCtacPsnPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CPO_CTAC_PSN_SQ));
            if (ZYPCommonFunc.hasValue(copyDsCpoCtacPsnTMsg.dsCpoConfigPk)) {
                DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyDsCpoCtacPsnTMsg.dsCpoConfigPk.getValue());
                if (copyDsCpoConfigTMsg == null) {
                    continue;
                }
                BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();

                copyDsCpoCtacPsnTMsg.dsCpoConfigPk.setValue(newDsCpoConfigPk);
            }

            copyDsCpoCtacPsnTMsgList.add(copyDsCpoCtacPsnTMsg);
        }

        if (!copyDsCpoCtacPsnTMsgList.isEmpty()) {
            int rsltCnt = S21FastTBLAccessor.insert(copyDsCpoCtacPsnTMsgList.toArray(new DS_CPO_CTAC_PSNTMsg[0]));
            if (rsltCnt != copyDsCpoCtacPsnTMsgList.size()) {
                throw new S21AbendException("DS_CPO_CTAC_PSN : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
            }
        }
        return false;
    }

    private boolean registCopyShellDataCpoSvc(NWZC150001PMsg pMsg, String userId, String timeStamp) {

        // 2017/05/25 S21_NA#Review structure Lv.2 RS#8389 Add Start
        NSZC115001PMsg shellApiPMsg = new NSZC115001PMsg();
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        shellApiPMsg.xxProcMd.setValue(NSZC115001Constant.PROC_MODE_COPY);
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.refCpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.svcConfigRefList.no(0).cpoOrdNum, pMsg.cpoOrdNum_A);
        shellApiPMsg.svcConfigRefList.setValidCount(1);

        new NSZC115001().execute(shellApiPMsg, onBatchType);
        // 2017/05/25 S21_NA#Review structure Lv.2 RS#8389 Add End

        // 2017/05/11 S21_NA#Review structure Lv.2 RS#8389 Del Start
//        List<CPO_SVCTMsg> origCpoSvcTMsgList = getCpoSvcInfoByCpoOrdNum(pMsg.glblCmpyCd.getValue(), pMsg.cpoOrdNum.getValue());
//        if (origCpoSvcTMsgList == null || origCpoSvcTMsgList.isEmpty()) {
//            return false;
//        }
//
//        List<CPO_SVCTMsg> copyCpoSvcTMsgList = new ArrayList<CPO_SVCTMsg>(0);
//        Map<BigDecimal, BigDecimal> cpoSvcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        for (CPO_SVCTMsg origCpoSvcTMsg : origCpoSvcTMsgList) {
//            CPO_SVCTMsg copyCpoSvcTMsg = new CPO_SVCTMsg();
//            EZDMsg.copy(origCpoSvcTMsg, null, copyCpoSvcTMsg, null);
//            copyCpoSvcTMsg.refCpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
//            BigDecimal newCpoSvcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_SQ);
//            cpoSvcPkMap.put(copyCpoSvcTMsg.cpoSvcPk.getValue(), newCpoSvcPk);
//            copyCpoSvcTMsg.cpoSvcPk.setValue(newCpoSvcPk);
//
//            copyCpoSvcTMsgList.add(copyCpoSvcTMsg);
//        }
//        // CPO_SVC_DTL
//        Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_DTLTMsg> copyCpoSvcDtlTMsgList = getCopyCpoSvcDtl(pMsg, cpoSvcPkMap, cpoSvcDtlPkMap);
//        if (copyCpoSvcDtlTMsgList == null || (copyCpoSvcDtlTMsgList != null && copyCpoSvcDtlTMsgList.isEmpty())) {
//            return false;
//        }
//
//        // If header without detail, erase such header from the list
//        for (int i = copyCpoSvcTMsgList.size() - 1; i >= 0; i--) {
//            if (isNoDetail(copyCpoSvcTMsgList.get(i), copyCpoSvcDtlTMsgList)) {
//                BigDecimal origCpoSvcPk = getKeyFromCpoSvcPkMap(cpoSvcPkMap, copyCpoSvcTMsgList.get(i).cpoSvcPk.getValue());
//                if (origCpoSvcPk != null) {
//                    cpoSvcPkMap.remove(origCpoSvcPk);
//                }
//                copyCpoSvcTMsgList.remove(i);
//            }
//        }
//        if (copyCpoSvcTMsgList.isEmpty()) {
//            return false;
//        }
//
//        // CPO_SVC_PRC
//        Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_PRCTMsg> copyCpoSvcPrcTMsgList = getCopyCpoSvcPrcList(pMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap);
//
//        // CPO_SVC_CONFIG_REF
//        Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_CONFIG_REFTMsg> copyCpoSvcConfigRefTMsgList = getCopyCpoSvcConfigRefList(pMsg, cpoSvcPkMap, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcConfigRefPkMap);
//
//        // replace cpoSvcConfigRefPk
//        for (CPO_SVC_PRCTMsg copyCpoSvcPrcTMsg : copyCpoSvcPrcTMsgList) {
//            if (ZYPCommonFunc.hasValue(copyCpoSvcPrcTMsg.cpoSvcConfigRefPk)) {
//                BigDecimal newCpoSvcConfigRefPk = cpoSvcConfigRefPkMap.get(copyCpoSvcPrcTMsg.cpoSvcConfigRefPk.getValue());
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcPrcTMsg.cpoSvcConfigRefPk, newCpoSvcConfigRefPk);
//            }
//        }
//        
//        // If detail without config_ref, erase such detail from the list
//        for (int i = copyCpoSvcDtlTMsgList.size() - 1; i >= 0; i--) {
//            if (isNoConfigRef(copyCpoSvcDtlTMsgList.get(i), copyCpoSvcConfigRefTMsgList)) {
//                BigDecimal origCpoSvcDtlPk = getKeyFromCpoSvcDtlPkMap(cpoSvcDtlPkMap, copyCpoSvcDtlTMsgList.get(i).cpoSvcDtlPk.getValue());
//                if (origCpoSvcDtlPk != null) {
//                    cpoSvcDtlPkMap.remove(origCpoSvcDtlPk);
//                }
//                copyCpoSvcDtlTMsgList.remove(i);
//            }
//        }
//        if (copyCpoSvcDtlTMsgList.isEmpty()) {
//            return false;
//        }
//
//        // Insert CPO_SVC_DTL
//        if (!copyCpoSvcDtlTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcDtlTMsgList.toArray(new CPO_SVC_DTLTMsg[0]));
//            if (rsltCnt != copyCpoSvcDtlTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_DTL : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//            // Biz Proc Log
//            for (CPO_SVC_DTLTMsg cpoSvcDtlTMsg : copyCpoSvcDtlTMsgList) {
//                insertProcLog(pMsg.cpoOrdNum_A.getValue(), cpoSvcDtlTMsg);
//
//                // Rec
//                cpoSvcDtlTMsg.ezUpUserID.setValue(userId);
//                cpoSvcDtlTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(pMsg.cpoOrdNum_A.getValue(), cpoSvcDtlTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_DTL_RECTMsg cpoSvcDtlRecTMsg = new CPO_SVC_DTL_RECTMsg();
//                    EZDMsg.copy(cpoSvcDtlTMsg, null, cpoSvcDtlRecTMsg, null);
//                    cpoSvcDtlRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcDtlRecTMsg);
//                }
//            }
//        }
//
//        // Insert CPO_SVC_PRC
//        if (!copyCpoSvcPrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcPrcTMsgList.toArray(new CPO_SVC_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcPrcTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_PRC : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//            // Biz Proc Log
//            for (CPO_SVC_PRCTMsg cpoSvcPrcTMsg : copyCpoSvcPrcTMsgList) {
//                insertProcLog(pMsg.cpoOrdNum_A.getValue(), cpoSvcPrcTMsg);
//
//                // Rec
//                cpoSvcPrcTMsg.ezUpUserID.setValue(userId);
//                cpoSvcPrcTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(pMsg.cpoOrdNum_A.getValue(), cpoSvcPrcTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_PRC_RECTMsg cpoSvcPrcRecTMsg = new CPO_SVC_PRC_RECTMsg();
//                    EZDMsg.copy(cpoSvcPrcTMsg, null, cpoSvcPrcRecTMsg, null);
//                    cpoSvcPrcRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcPrcRecTMsg);
//                }
//            }
//        }
//
//        // Insert CPO_SVC_CONFIG_REF
//        if (!copyCpoSvcConfigRefTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcConfigRefTMsgList.toArray(new CPO_SVC_CONFIG_REFTMsg[0]));
//            if (rsltCnt != copyCpoSvcConfigRefTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_CONFIG_REF : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//            // Biz Proc Log
//            for (CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg : copyCpoSvcConfigRefTMsgList) {
//                insertProcLog(cpoSvcConfigRefTMsg);
//
//                // Rec
//                cpoSvcConfigRefTMsg.ezUpUserID.setValue(userId);
//                cpoSvcConfigRefTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(cpoSvcConfigRefTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_CONFIG_REF_RECTMsg cpoSvcConfigRefRecTMsg = new CPO_SVC_CONFIG_REF_RECTMsg();
//                    EZDMsg.copy(cpoSvcConfigRefTMsg, null, cpoSvcConfigRefRecTMsg, null);
//                    cpoSvcConfigRefRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcConfigRefRecTMsg);
//                }
//            }
//        }
//
//        // CPO_SVC_USG_PRC
//        List<CPO_SVC_USG_PRCTMsg> copyCpoSvcUsgPrcTMsgList = getCopyCpoSvcUsgPrcList(pMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap);
//        if (!copyCpoSvcUsgPrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcUsgPrcTMsgList.toArray(new CPO_SVC_USG_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcUsgPrcTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_USG_PRC : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//        }
//
//        // CPO_SVC_ADDL_BASE_PRC
//        Map<BigDecimal, BigDecimal> cpoSvcAddlBasePrcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_ADDL_BASE_PRCTMsg> copyCpoSvcAddlBasePrcTMsgList = getCopyCpoSvcAddlBasePrcList(pMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcAddlBasePrcPkMap);
//        if (!copyCpoSvcAddlBasePrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcAddlBasePrcTMsgList.toArray(new CPO_SVC_ADDL_BASE_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcAddlBasePrcTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_ADDL_BASE_PRC : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//            // Biz Proc Log
//            for (CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg : copyCpoSvcAddlBasePrcTMsgList) {
//                insertProcLog(cpoSvcAddlBasePrcTMsg);
//
//                // Rec
//                cpoSvcAddlBasePrcTMsg.ezUpUserID.setValue(userId);
//                cpoSvcAddlBasePrcTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(cpoSvcAddlBasePrcTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_ADDL_BASE_RECTMsg cpoSvcAddlBasePrcRecTMsg = new CPO_SVC_ADDL_BASE_RECTMsg();
//                    EZDMsg.copy(cpoSvcAddlBasePrcTMsg, null, cpoSvcAddlBasePrcRecTMsg, null);
//                    cpoSvcAddlBasePrcRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcAddlBasePrcRecTMsg);
//                }
//            }
//        }
//
//        // CPO_SVC_ADDL_CHRG_PRC
//        Map<BigDecimal, BigDecimal> cpoSvcAddlChrgPrcPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<CPO_SVC_ADDL_CHRG_PRCTMsg> copyCpoSvcAddlChrgPrcTMsgList = getCopyCpoSvcAddlChrgPrcList(pMsg, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcAddlChrgPrcPkMap);
//        if (!copyCpoSvcAddlChrgPrcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcAddlChrgPrcTMsgList.toArray(new CPO_SVC_ADDL_CHRG_PRCTMsg[0]));
//            if (rsltCnt != copyCpoSvcAddlChrgPrcTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_ADDL_CHRG_PRC : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//            // Biz Proc Log
//            for (CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg : copyCpoSvcAddlChrgPrcTMsgList) {
//                insertProcLog(cpoSvcAddlChrgPrcTMsg);
//
//                // Rec
//                cpoSvcAddlChrgPrcTMsg.ezUpUserID.setValue(userId);
//                cpoSvcAddlChrgPrcTMsg.ezUpTime.setValue(timeStamp);
//                BigDecimal bizProcLogPk = getBizProcLogPk(cpoSvcAddlChrgPrcTMsg);
//                if (bizProcLogPk != null) {
//                    CPO_SVC_ADDL_CHRG_RECTMsg cpoSvcAddlChrgPrcRecTMsg = new CPO_SVC_ADDL_CHRG_RECTMsg();
//                    EZDMsg.copy(cpoSvcAddlChrgPrcTMsg, null, cpoSvcAddlChrgPrcRecTMsg, null);
//                    cpoSvcAddlChrgPrcRecTMsg.bizProcLogPk.setValue(bizProcLogPk);
//                    S21FastTBLAccessor.insert(cpoSvcAddlChrgPrcRecTMsg);
//                }
//            }
//        }
//
//        // CPO_SVC_PRC_TAX_DTL
//        List<CPO_SVC_PRC_TAX_DTLTMsg> copyCpoSvcPrcTaxDtlTMsgList = getCopyCpoSvcPrcTaxDtlList(pMsg, cpoSvcPkMap, cpoSvcDtlPkMap, cpoSvcPrcPkMap, cpoSvcConfigRefPkMap, cpoSvcAddlBasePrcPkMap, cpoSvcAddlChrgPrcPkMap);
//        if (!copyCpoSvcPrcTaxDtlTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcPrcTaxDtlTMsgList.toArray(new CPO_SVC_PRC_TAX_DTLTMsg[0]));
//            if (rsltCnt != copyCpoSvcPrcTaxDtlTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC_PRC_TAX_DTL : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//        }
//
//        // SCHD_CRAT_TMPL
//        Map<BigDecimal, BigDecimal> schdCratTmplPkMap = new HashMap<BigDecimal, BigDecimal>(0);
//        List<SCHD_CRAT_TMPLTMsg> copySchdCratTmplTMsgList = getCopySchdCratTmplList(pMsg, cpoSvcDtlPkMap, schdCratTmplPkMap);
//        if (!copySchdCratTmplTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copySchdCratTmplTMsgList.toArray(new SCHD_CRAT_TMPLTMsg[0]));
//            if (rsltCnt != copySchdCratTmplTMsgList.size()) {
//                throw new S21AbendException("SCHD_CRAT_TMPL : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//        }
//
//        // SCHD_CRAT_TMPL_LINE
//        List<SCHD_CRAT_TMPL_LINETMsg> copySchdCratTmplLineTMsgList = getCopySchdCratTmplLineList(pMsg, schdCratTmplPkMap, cpoSvcConfigRefPkMap);
//        if (!copySchdCratTmplLineTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copySchdCratTmplLineTMsgList.toArray(new SCHD_CRAT_TMPL_LINETMsg[0]));
//            if (rsltCnt != copySchdCratTmplLineTMsgList.size()) {
//                throw new S21AbendException("SCHD_CRAT_TMPL_LINE : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//        }
//
//        // Insert Header
//        if (!copyCpoSvcTMsgList.isEmpty()) {
//            int rsltCnt = S21FastTBLAccessor.insertDirect(copyCpoSvcTMsgList.toArray(new CPO_SVCTMsg[0]));
//            if (rsltCnt != copyCpoSvcTMsgList.size()) {
//                throw new S21AbendException("CPO_SVC : Insert Error. CPO_ORD_NUM=[" + pMsg.cpoOrdNum.getValue() + "]");
//            }
//        }
        // 2017/05/11 S21_NA#Review structure Lv.2 RS#8389 Del End
        return false;
    }

    // 2017/05/11 S21_NA#Review structure Lv.2 RS#8389 Del Start
//    private List<CPO_SVC_DTLTMsg> getCopyCpoSvcDtl(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcPkMap, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap) {
//
//        List<CPO_SVC_DTLTMsg> copyCpoSvcDtlTMsgList = new ArrayList<CPO_SVC_DTLTMsg>(0);
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//
//            List<Map<String, Object>> rsltObjList = getCpoSvcDtlForShell(pMsg.glblCmpyCd.getValue(), origCpoSvcPk);
//            if (rsltObjList == null || rsltObjList.isEmpty()) {
//                return copyCpoSvcDtlTMsgList;
//            }
//
//            for (Map<String, Object> rsltObj : rsltObjList) {
//                CPO_SVC_DTLTMsg copyCpoSvcDtlTMsg = new CPO_SVC_DTLTMsg();
//
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.glblCmpyCd,            pMsg.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcDtlPk,           (BigDecimal) rsltObj.get("CPO_SVC_DTL_PK"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcPk,              cpoSvcPkMap.get(origCpoSvcPk));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcLineNum,         (BigDecimal) rsltObj.get("CPO_SVC_LINE_NUM"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcMdseCd,          (String) rsltObj.get("CPO_SVC_MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.prcSvcContrTpCd,       (String) rsltObj.get("PRC_SVC_CONTR_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.prcSvcPlnTpCd,         (String) rsltObj.get("PRC_SVC_PLN_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.dsContrCatgCd,         (String) rsltObj.get("DS_CONTR_CATG_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.baseBllgCycleCd,       (String) rsltObj.get("BASE_BLLG_CYCLE_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.usgBllgCycleCd,        (String) rsltObj.get("USG_BLLG_CYCLE_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.fromPerMthNum,         (BigDecimal) rsltObj.get("FROM_PER_MTH_NUM"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.toPerMthNum,           (BigDecimal) rsltObj.get("TO_PER_MTH_NUM"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.termMthNum,            (BigDecimal) rsltObj.get("TERM_MTH_NUM"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.billWithEquipFlg,      (String) rsltObj.get("BILL_WITH_EQUIP_FLG"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.billByTpCd,            (String) rsltObj.get("BILL_BY_TP_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.soldToCustLocCd,       (String) rsltObj.get("SOLD_TO_CUST_LOC_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.sellToCustCd,          (String) rsltObj.get("SELL_TO_CUST_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcAgmtVerNum,      (String) rsltObj.get("CPO_SVC_AGMT_VER_NUM"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.manContrOvrdFlg,       (String) rsltObj.get("MAN_CONTR_OVRD_FLG"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.manContrOvrdRsnCd,     (String) rsltObj.get("MAN_CONTR_OVRD_RSN_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.manContrOvrdCmntTxt,   (String) rsltObj.get("MAN_CONTR_OVRD_CMNT_TXT"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.dsContrPk,             (BigDecimal) rsltObj.get("DS_CONTR_PK"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.svcPrcCatgCd,          (String) rsltObj.get("SVC_PRC_CATG_CD"));
//                copyCpoSvcDtlTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.crRebilCd,             (String) rsltObj.get("CR_REBIL_CD"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.contrCratFlg,          ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.applyEquipBillToFlg,   (String) rsltObj.get("APPLY_EQUIP_BILL_TO_FLG"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.totBasePrcDealAmt,     (BigDecimal) rsltObj.get("TOT_BASE_PRC_DEAL_AMT"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.totBasePrcFuncAmt,     (BigDecimal) rsltObj.get("TOT_BASE_PRC_FUNC_AMT"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.addAsryFlg,            (String) rsltObj.get("ADD_ASRY_FLG"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.fixTermInMthAot,       (BigDecimal) rsltObj.get("FIX_TERM_IN_MTH_AOT"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.maxUplftPct,           (BigDecimal) rsltObj.get("MAX_UPLFT_PCT"));
//                ZYPEZDItemValueSetter.setValue(copyCpoSvcDtlTMsg.cpoSvcLineActCd,       (String) rsltObj.get("CPO_SVC_LINE_ACT_CD"));
//
//                BigDecimal origCpoSvcDtlPk = copyCpoSvcDtlTMsg.cpoSvcDtlPk.getValue();
//                BigDecimal newCpoSvcDltPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_DTL_SQ);
//                cpoSvcDtlPkMap.put(origCpoSvcDtlPk, newCpoSvcDltPk);
//                copyCpoSvcDtlTMsg.cpoSvcDtlPk.setValue(newCpoSvcDltPk);
//
//                copyCpoSvcDtlTMsgList.add(copyCpoSvcDtlTMsg);
//            }
//        }
//        return copyCpoSvcDtlTMsgList;
//    }

//    private boolean isNoDetail(CPO_SVCTMsg copyCpoSvcTMsg, List<CPO_SVC_DTLTMsg> copyCpoSvcDtlTMsgList) {
//
//        boolean isNoDetail = true;
//        BigDecimal newCpoSvcPk = copyCpoSvcTMsg.cpoSvcPk.getValue();
//        for (CPO_SVC_DTLTMsg copyCpoSvcDtlTMsg : copyCpoSvcDtlTMsgList) {
//            if (newCpoSvcPk.compareTo(copyCpoSvcDtlTMsg.cpoSvcPk.getValue()) == 0) {
//                isNoDetail = false;
//                break;
//            }
//        }
//        return isNoDetail;
//    }

//    private BigDecimal getKeyFromCpoSvcPkMap(Map<BigDecimal, BigDecimal> cpoSvcPkMap, BigDecimal newCpoSvcPk) {
//
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//            BigDecimal valCpoSvcPk = cpoSvcPkMap.get(origCpoSvcPk);
//            if (valCpoSvcPk == null) {
//                continue;
//            }
//            if (valCpoSvcPk.compareTo(newCpoSvcPk) == 0) {
//                return origCpoSvcPk;
//            }
//        }
//        return null;
//    }

//    private List<CPO_SVC_PRCTMsg> getCopyCpoSvcPrcList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap) {
//
//        List<CPO_SVC_PRCTMsg> copyCpoSvcPrcTMsgList = new ArrayList<CPO_SVC_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_PRCTMsg origCpoSvcPrcTMsg = new CPO_SVC_PRCTMsg();
//            origCpoSvcPrcTMsg.setSQLID("001");
//            origCpoSvcPrcTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            origCpoSvcPrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_PRCTMsgArray copyCpoSvcPrcTMsgArray = (CPO_SVC_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcPrcTMsg);
//            for (int i = 0; i < copyCpoSvcPrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_PRCTMsg copyCpoSvcPrcTMsg = new CPO_SVC_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcPrcTMsgArray.no(i), null, copyCpoSvcPrcTMsg, null);
//
//                BigDecimal newCpoSvcPrcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_PRC_SQ);
//                BigDecimal origCpoSvcPrcPk = copyCpoSvcPrcTMsg.cpoSvcPrcPk.getValue();
//
//                cpoSvcPrcPkMap.put(origCpoSvcPrcPk, newCpoSvcPrcPk);
//
//                copyCpoSvcPrcTMsg.cpoSvcPrcPk.setValue(newCpoSvcPrcPk);
//                copyCpoSvcPrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copyCpoSvcPrcTMsgList.add(copyCpoSvcPrcTMsg);
//            }
//        }
//        return copyCpoSvcPrcTMsgList;
//    }

//    private List<CPO_SVC_CONFIG_REFTMsg> getCopyCpoSvcConfigRefList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcPkMap, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMap) {
//
//        List<CPO_SVC_CONFIG_REFTMsg> copyCpoSvcConfigRefTMsgList = new ArrayList<CPO_SVC_CONFIG_REFTMsg>(0);
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//            List<Map<String, Object>> rsltMapList = getCpoSvcConfigRefForShell(pMsg.glblCmpyCd.getValue(), origCpoSvcPk);
//            if (rsltMapList == null || rsltMapList.isEmpty()) {
//                continue;
//            }
//            for (Map<String, Object> rsltMap : rsltMapList) {
//                BigDecimal origCpoSvcDtlPk = (BigDecimal) rsltMap.get("CPO_SVC_DTL_PK");
//                String cpoOrdNum = (String) rsltMap.get("CPO_ORD_NUM");
//                String cpoDtlLineNum = (String) rsltMap.get("CPO_DTL_LINE_NUM");
//                String cpoDtlLineSubNum = (String) rsltMap.get("CPO_DTL_LINE_SUB_NUM");
//
//                CPO_SVC_CONFIG_REFTMsg origCpoConfigRefTMsg = new CPO_SVC_CONFIG_REFTMsg();
//                origCpoConfigRefTMsg.setSQLID("001");
//                origCpoConfigRefTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//                origCpoConfigRefTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
//                origCpoConfigRefTMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
//                origCpoConfigRefTMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
//                origCpoConfigRefTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//                CPO_SVC_CONFIG_REFTMsgArray copyCpoConfigRefTMsgArray = (CPO_SVC_CONFIG_REFTMsgArray) EZDTBLAccessor.findByCondition(origCpoConfigRefTMsg);
//                for (int i = 0; i < copyCpoConfigRefTMsgArray.getValidCount(); i++) {
//                    CPO_SVC_CONFIG_REFTMsg copyCpoSvcConfigRefTMsg = new CPO_SVC_CONFIG_REFTMsg();
//                    EZDMsg.copy(copyCpoConfigRefTMsgArray.no(i), null, copyCpoSvcConfigRefTMsg, null);
//
//                    copyCpoSvcConfigRefTMsg.cpoSvcConfigRefPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_CONFIG_REF_SQ));
//                    copyCpoSvcConfigRefTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                    if (ZYPCommonFunc.hasValue(copyCpoSvcConfigRefTMsg.cpoSvcPrcPk)) {
//                        BigDecimal newCpoSvcPrcPk = cpoSvcPrcPkMap.get(copyCpoSvcConfigRefTMsg.cpoSvcPrcPk.getValue());
//                        ZYPEZDItemValueSetter.setValue(copyCpoSvcConfigRefTMsg.cpoSvcPrcPk, newCpoSvcPrcPk);
//                    }
//                    copyCpoSvcConfigRefTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
//                    copyCpoSvcConfigRefTMsg.contrCratFlg.setValue(ZYPConstant.FLG_OFF_N);
//                    copyCpoSvcConfigRefTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N);
//
//                    // SVC_CONFIG_MSTR_PK
//                    CPO_DTLTMsg dsCpoDtlTMsg = new CPO_DTLTMsg();
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.glblCmpyCd, pMsg.glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoOrdNum, copyCpoSvcConfigRefTMsg.cpoOrdNum);
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineNum, copyCpoSvcConfigRefTMsg.cpoDtlLineNum);
//                    ZYPEZDItemValueSetter.setValue(dsCpoDtlTMsg.cpoDtlLineSubNum, copyCpoSvcConfigRefTMsg.cpoDtlLineSubNum);
//                    dsCpoDtlTMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlTMsg);
//                    if (dsCpoDtlTMsg != null) {
//                        ZYPEZDItemValueSetter.setValue(copyCpoSvcConfigRefTMsg.svcConfigMstrPk, dsCpoDtlTMsg.svcConfigMstrPk);
//                    }
//
//                    copyCpoSvcConfigRefTMsgList.add(copyCpoSvcConfigRefTMsg);
//                    cpoSvcConfigRefPkMap.put(copyCpoConfigRefTMsgArray.no(i).cpoSvcConfigRefPk.getValue(), copyCpoSvcConfigRefTMsg.cpoSvcConfigRefPk.getValue());
//                }
//            }
//        }
//        return copyCpoSvcConfigRefTMsgList;
//    }

//    private boolean isNoConfigRef(CPO_SVC_DTLTMsg copyCpoSvcDtlTMsg, List<CPO_SVC_CONFIG_REFTMsg> copyCpoSvcConfigRefTMsgList) {
//
//        boolean isNoConfigRef = true;
//        BigDecimal newCpoSvcDtlPk = copyCpoSvcDtlTMsg.cpoSvcDtlPk.getValue();
//        for (CPO_SVC_CONFIG_REFTMsg copyCpoSvcConfigRefTMsg : copyCpoSvcConfigRefTMsgList) {
//            if (newCpoSvcDtlPk.compareTo(copyCpoSvcConfigRefTMsg.cpoSvcDtlPk.getValue()) == 0) {
//                isNoConfigRef = false;
//                break;
//            }
//        }
//        return isNoConfigRef;
//    }

//    private BigDecimal getKeyFromCpoSvcDtlPkMap(Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, BigDecimal newCpoSvcDtlPk) {
//
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            BigDecimal valCpoSvcDtlPk = cpoSvcDtlPkMap.get(origCpoSvcDtlPk);
//            if (valCpoSvcDtlPk == null) {
//                continue;
//            }
//            if (valCpoSvcDtlPk.compareTo(newCpoSvcDtlPk) == 0) {
//                return origCpoSvcDtlPk;
//            }
//        }
//        return null;
//    }

//    private void insertProcLog(String cpoOrdNum, CPO_SVC_DTLTMsg cpoSvcDtlTMsg) {
//
//        String docId = String.valueOf(cpoSvcDtlTMsg.cpoSvcLineNum.getValue());
//        insertBizProcLog(cpoOrdNum, ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }

//    private BigDecimal getBizProcLogPk(String cpoOrdNum, CPO_SVC_DTLTMsg cpoSvcDtlTMsg) {
//
//        String docId = String.valueOf(cpoSvcDtlTMsg.cpoSvcLineNum.getValue());
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        queryParam.put("userId", cpoSvcDtlTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcDtlTMsg.ezUpTime.getValue());
//
//        return getMaxBizProcPk(queryParam);
//    }

//    private void insertProcLog(String cpoOrdNum, CPO_SVC_PRCTMsg cpoSvcPrcTMsg) {
//
//        String docId = getCpoSvcPrcDocId(cpoSvcPrcTMsg);
//        insertBizProcLog(cpoOrdNum, ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }

//    private String getCpoSvcPrcDocId(CPO_SVC_PRCTMsg cpoSvcPrcTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcPrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcPrcTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            if (ZYPCommonFunc.hasValue(cpoSvcPrcTMsg.mdlId)) {
//                docId = docId + String.valueOf(cpoSvcPrcTMsg.mdlId.getValue());
//            } else {
//                docId = docId + DS_CONTR_CATG.FLEET;
//            }
//        }
//        return docId;
//    }

//    private BigDecimal getBizProcLogPk(String cpoOrdNum, CPO_SVC_PRCTMsg cpoSvcPrcTMsg) {
//
//        String docId = getCpoSvcPrcDocId(cpoSvcPrcTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        queryParam.put("userId", cpoSvcPrcTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcPrcTMsg.ezUpTime.getValue());
//
//        return getMaxBizProcPk(queryParam);
//    }

//    private void insertProcLog(CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg) {
//
//        String docId = getCpoSvcConfigRefDocId(cpoSvcConfigRefTMsg);
//        insertBizProcLog(cpoSvcConfigRefTMsg.cpoOrdNum.getValue(), ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }

//    private String getCpoSvcConfigRefDocId(CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcConfigRefTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcConfigRefTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        CPO_DTLTMsg dsCpoDtlMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.glblCmpyCd, cpoSvcConfigRefTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoOrdNum, cpoSvcConfigRefTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineNum, cpoSvcConfigRefTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineSubNum, cpoSvcConfigRefTMsg.cpoDtlLineSubNum);
//
//        dsCpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null && dsCpoDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            docId = docId + dsCpoDtlMsg.dsOrdPosnNum.getValue();
//        }
//        return docId;
//    }

//    private BigDecimal getBizProcLogPk(CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefTMsg) {
//
//        String docId = getCpoSvcConfigRefDocId(cpoSvcConfigRefTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoSvcConfigRefTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoSvcConfigRefTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcConfigRefTMsg.ezUpTime.getValue());
//
//        return getMaxBizProcPk(queryParam);
//    }

//    private List<CPO_SVC_USG_PRCTMsg> getCopyCpoSvcUsgPrcList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap,  Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap) {
//
//        List<CPO_SVC_USG_PRCTMsg> copyCpoSvcUsgPrcTMsgList = new ArrayList<CPO_SVC_USG_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_USG_PRCTMsg origCpoSvcUsgPrcTMsg = new CPO_SVC_USG_PRCTMsg();
//            origCpoSvcUsgPrcTMsg.setSQLID("001");
//            origCpoSvcUsgPrcTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            origCpoSvcUsgPrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_USG_PRCTMsgArray copyCpoSvcUsgPrcTMsgArray = (CPO_SVC_USG_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcUsgPrcTMsg);
//            for (int i = 0; i < copyCpoSvcUsgPrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_USG_PRCTMsg copyCpoSvcUsgPrcTMsg = new CPO_SVC_USG_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcUsgPrcTMsgArray.no(i), null, copyCpoSvcUsgPrcTMsg, null);
//
//                copyCpoSvcUsgPrcTMsg.cpoSvcUsgPrcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_USG_PRC_SQ));
//                copyCpoSvcUsgPrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                if (ZYPCommonFunc.hasValue(copyCpoSvcUsgPrcTMsg.cpoSvcPrcPk)) {
//                    ZYPEZDItemValueSetter.setValue(copyCpoSvcUsgPrcTMsg.cpoSvcPrcPk, cpoSvcPrcPkMap.get(copyCpoSvcUsgPrcTMsg.cpoSvcPrcPk.getValue()));
//                }
//                copyCpoSvcUsgPrcTMsgList.add(copyCpoSvcUsgPrcTMsg);
//            }
//        }
//        return copyCpoSvcUsgPrcTMsgList;
//    }

//    private List<CPO_SVC_ADDL_BASE_PRCTMsg> getCopyCpoSvcAddlBasePrcList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap,  Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlBasePrcPkMap) {
//
//        List<CPO_SVC_ADDL_BASE_PRCTMsg> copyCpoSvcAddlBasePrcTMsgList = new ArrayList<CPO_SVC_ADDL_BASE_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_ADDL_BASE_PRCTMsg origCpoSvcAddlBasePrcTMsg = new CPO_SVC_ADDL_BASE_PRCTMsg();
//            origCpoSvcAddlBasePrcTMsg.setSQLID("001");
//            origCpoSvcAddlBasePrcTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            origCpoSvcAddlBasePrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_ADDL_BASE_PRCTMsgArray copyCpoSvcAddlBasePrcTMsgArray = (CPO_SVC_ADDL_BASE_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcAddlBasePrcTMsg);
//            for (int i = 0; i < copyCpoSvcAddlBasePrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_ADDL_BASE_PRCTMsg copyCpoSvcAddlBasePrcTMsg = new CPO_SVC_ADDL_BASE_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcAddlBasePrcTMsgArray.no(i), null, copyCpoSvcAddlBasePrcTMsg, null);
//
//                copyCpoSvcAddlBasePrcTMsg.cpoSvcAddlBasePrcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_ADDL_BASE_PRC_SQ));
//                copyCpoSvcAddlBasePrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copyCpoSvcAddlBasePrcTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
//                copyCpoSvcAddlBasePrcTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N);
//
//                copyCpoSvcAddlBasePrcTMsgList.add(copyCpoSvcAddlBasePrcTMsg);
//                cpoSvcAddlBasePrcPkMap.put(copyCpoSvcAddlBasePrcTMsgArray.no(i).cpoSvcAddlBasePrcPk.getValue(), copyCpoSvcAddlBasePrcTMsg.cpoSvcAddlBasePrcPk.getValue());
//            }
//        }
//        return copyCpoSvcAddlBasePrcTMsgList;
//    }

//    private void insertProcLog(CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg) {
//
//        String docId = getCpoSvcAddlBasePrcDocId(cpoSvcAddlBasePrcTMsg);
//        insertBizProcLog(cpoSvcAddlBasePrcTMsg.cpoOrdNum.getValue(), ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }

//    private String getCpoSvcAddlBasePrcDocId(CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcAddlBasePrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcAddlBasePrcTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        CPO_DTLTMsg dsCpoDtlMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.glblCmpyCd, cpoSvcAddlBasePrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoOrdNum, cpoSvcAddlBasePrcTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineNum, cpoSvcAddlBasePrcTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineSubNum, cpoSvcAddlBasePrcTMsg.cpoDtlLineSubNum);
//
//        dsCpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null && dsCpoDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            docId = docId + dsCpoDtlMsg.dsOrdPosnNum.getValue() + ".";
//            docId = docId + cpoSvcAddlBasePrcTMsg.cpoDtlLineNum.getValue() + "." + cpoSvcAddlBasePrcTMsg.cpoDtlLineSubNum.getValue();
//        }
//        return docId;
//    }

//    private BigDecimal getBizProcLogPk(CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcTMsg) {
//
//        String docId = getCpoSvcAddlBasePrcDocId(cpoSvcAddlBasePrcTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoSvcAddlBasePrcTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoSvcAddlBasePrcTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcAddlBasePrcTMsg.ezUpTime.getValue());
//
//        return getMaxBizProcPk(queryParam);
//    }

//    private List<CPO_SVC_ADDL_CHRG_PRCTMsg> getCopyCpoSvcAddlChrgPrcList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap,  Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlChrgPrcPkMap) {
//
//        List<CPO_SVC_ADDL_CHRG_PRCTMsg> copyCpoSvcAddlChrgPrcTMsgList = new ArrayList<CPO_SVC_ADDL_CHRG_PRCTMsg>(0);
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            CPO_SVC_ADDL_CHRG_PRCTMsg origCpoSvcAddlChrgPrcTMsg = new CPO_SVC_ADDL_CHRG_PRCTMsg();
//            origCpoSvcAddlChrgPrcTMsg.setSQLID("001");
//            origCpoSvcAddlChrgPrcTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            origCpoSvcAddlChrgPrcTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            CPO_SVC_ADDL_CHRG_PRCTMsgArray copyCpoSvcAddlChrgPrcTMsgArray = (CPO_SVC_ADDL_CHRG_PRCTMsgArray) EZDTBLAccessor.findByCondition(origCpoSvcAddlChrgPrcTMsg);
//            for (int i = 0; i < copyCpoSvcAddlChrgPrcTMsgArray.getValidCount(); i++) {
//                CPO_SVC_ADDL_CHRG_PRCTMsg copyCpoSvcAddlChrgPrcTMsg = new CPO_SVC_ADDL_CHRG_PRCTMsg();
//                EZDMsg.copy(copyCpoSvcAddlChrgPrcTMsgArray.no(i), null, copyCpoSvcAddlChrgPrcTMsg, null);
//
//                copyCpoSvcAddlChrgPrcTMsg.cpoSvcAddlChrgPrcPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_ADDL_CHRG_PRC_SQ));
//                copyCpoSvcAddlChrgPrcTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copyCpoSvcAddlChrgPrcTMsg.cpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
//                copyCpoSvcAddlChrgPrcTMsg.billWithEquipInvdFlg.setValue(ZYPConstant.FLG_OFF_N);
//
//                copyCpoSvcAddlChrgPrcTMsgList.add(copyCpoSvcAddlChrgPrcTMsg);
//                cpoSvcAddlChrgPrcPkMap.put(copyCpoSvcAddlChrgPrcTMsgArray.no(i).cpoSvcAddlChrgPrcPk.getValue(), copyCpoSvcAddlChrgPrcTMsg.cpoSvcAddlChrgPrcPk.getValue());
//            }
//        }
//        return copyCpoSvcAddlChrgPrcTMsgList;
//    }

//    private void insertProcLog(CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg) {
//
//        String docId = getCpoSvcAddlChrgPrcDocId(cpoSvcAddlChrgPrcTMsg);
//        insertBizProcLog(cpoSvcAddlChrgPrcTMsg.cpoOrdNum.getValue(), ORD_SHELL_CREATE, docId, DOC_TP_OM);
//    }

//    private String getCpoSvcAddlChrgPrcDocId(CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg) {
//
//        CPO_SVC_DTLTMsg cpoSvcDtlMsg = new CPO_SVC_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.glblCmpyCd, cpoSvcAddlChrgPrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcDtlMsg.cpoSvcDtlPk, cpoSvcAddlChrgPrcTMsg.cpoSvcDtlPk);
//        cpoSvcDtlMsg = (CPO_SVC_DTLTMsg) S21FastTBLAccessor.findByKey(cpoSvcDtlMsg);
//
//        CPO_DTLTMsg dsCpoDtlMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.glblCmpyCd, cpoSvcAddlChrgPrcTMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoOrdNum, cpoSvcAddlChrgPrcTMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineNum, cpoSvcAddlChrgPrcTMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(dsCpoDtlMsg.cpoDtlLineSubNum, cpoSvcAddlChrgPrcTMsg.cpoDtlLineSubNum);
//
//        dsCpoDtlMsg = (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(dsCpoDtlMsg);
//
//        String docId = "";
//        if (cpoSvcDtlMsg != null && dsCpoDtlMsg != null) {
//            docId = String.valueOf(cpoSvcDtlMsg.cpoSvcLineNum.getValue()) + ".";
//            docId = docId + dsCpoDtlMsg.dsOrdPosnNum.getValue() + ".";
//            docId = docId + cpoSvcAddlChrgPrcTMsg.cpoDtlLineNum.getValue() + "." + cpoSvcAddlChrgPrcTMsg.cpoDtlLineSubNum.getValue();
//        }
//        return docId;
//    }

//    private BigDecimal getBizProcLogPk(CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcTMsg) {
//
//        String docId = getCpoSvcAddlChrgPrcDocId(cpoSvcAddlChrgPrcTMsg);
//
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("subSysId", SUB_SYS_ID_NWZ);
//        queryParam.put("procId", DOC_TP_OM);
//        queryParam.put("eventId", ORD_SHELL_CREATE + "%");
//        queryParam.put("docTpCd", DOC_TP_OM);
//
//        queryParam.put("docId", docId);
//        queryParam.put("cpoOrdNum", cpoSvcAddlChrgPrcTMsg.cpoOrdNum.getValue());
//        queryParam.put("userId", cpoSvcAddlChrgPrcTMsg.ezUpUserID.getValue());
//        queryParam.put("ezUptime", cpoSvcAddlChrgPrcTMsg.ezUpTime.getValue());
//
//        return getMaxBizProcPk(queryParam);
//    }

//    private List<CPO_SVC_PRC_TAX_DTLTMsg> getCopyCpoSvcPrcTaxDtlList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcPkMap, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> cpoSvcPrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlBasePrcPkMap, Map<BigDecimal, BigDecimal> cpoSvcAddlChrgPrcPkMap) {
//
//        List<CPO_SVC_PRC_TAX_DTLTMsg> copyCpoSvcPrcTaxDtlTMsgList = new ArrayList<CPO_SVC_PRC_TAX_DTLTMsg>(0);
//
//        for (BigDecimal origCpoSvcPk : cpoSvcPkMap.keySet()) {
//
//            CPO_SVC_PRC_TAX_DTLTMsg keyCpoSvcPrcTaxDtlTMsg = new CPO_SVC_PRC_TAX_DTLTMsg();
//            keyCpoSvcPrcTaxDtlTMsg.setSQLID("003");
//            keyCpoSvcPrcTaxDtlTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            keyCpoSvcPrcTaxDtlTMsg.setConditionValue("cpoSvcPk01", origCpoSvcPk);
//
//            CPO_SVC_PRC_TAX_DTLTMsgArray copyCpoSvcPrcTaxDtlTMsgArray = (CPO_SVC_PRC_TAX_DTLTMsgArray) EZDTBLAccessor.findByCondition(keyCpoSvcPrcTaxDtlTMsg);
//            for (int i = 0; i < copyCpoSvcPrcTaxDtlTMsgArray.getValidCount(); i++) {
//                CPO_SVC_PRC_TAX_DTLTMsg copyCpoSvcPrcTaxDtlTMsg = new CPO_SVC_PRC_TAX_DTLTMsg();
//                EZDMsg.copy(copyCpoSvcPrcTaxDtlTMsgArray.no(i), null, copyCpoSvcPrcTaxDtlTMsg, null);
//                copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcTaxDtlPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CPO_SVC_PRC_TAX_DTL_SQ));
//
//                BigDecimal cpoSvcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcPk.getValue();
//                BigDecimal cpoSvcDtlPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcDtlPk.getValue();
//                BigDecimal cpoSvcPrcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcPk.getValue();
//                BigDecimal cpoSvcConfigRefPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcConfigRefPk.getValue();
//                BigDecimal cpoSvcAddlBasePrcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlBasePrcPk.getValue();
//                BigDecimal cpoSvcAddlChrgPrcPk = copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlChrgPrcPk.getValue();
//
//                copyCpoSvcPrcTaxDtlTMsg.cpoSvcPk.setValue(cpoSvcPkMap.get(cpoSvcPk));
//                if (ZYPCommonFunc.hasValue(cpoSvcDtlPk) && ZYPCommonFunc.hasValue(cpoSvcDtlPkMap.get(cpoSvcDtlPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(cpoSvcDtlPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcDtlPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcPrcPk) && ZYPCommonFunc.hasValue(cpoSvcPrcPkMap.get(cpoSvcPrcPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcPk.setValue(cpoSvcPrcPkMap.get(cpoSvcPrcPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcPrcPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) && ZYPCommonFunc.hasValue(cpoSvcConfigRefPkMap.get(cpoSvcConfigRefPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcConfigRefPk.setValue(cpoSvcConfigRefPkMap.get(cpoSvcConfigRefPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcConfigRefPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcAddlBasePrcPk) && ZYPCommonFunc.hasValue(cpoSvcAddlBasePrcPkMap.get(cpoSvcAddlBasePrcPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlBasePrcPk.setValue(cpoSvcAddlBasePrcPkMap.get(cpoSvcAddlBasePrcPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlBasePrcPk.clear();
//                }
//                if (ZYPCommonFunc.hasValue(cpoSvcAddlChrgPrcPk) && ZYPCommonFunc.hasValue(cpoSvcAddlChrgPrcPkMap.get(cpoSvcAddlChrgPrcPk))) {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlChrgPrcPk.setValue(cpoSvcAddlChrgPrcPkMap.get(cpoSvcAddlChrgPrcPk));
//                } else {
//                    copyCpoSvcPrcTaxDtlTMsg.cpoSvcAddlChrgPrcPk.clear();
//                }
//
//                copyCpoSvcPrcTaxDtlTMsgList.add(copyCpoSvcPrcTaxDtlTMsg);
//            }
//        }
//        return copyCpoSvcPrcTaxDtlTMsgList;
//    }

//    private List<SCHD_CRAT_TMPLTMsg> getCopySchdCratTmplList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> cpoSvcDtlPkMap, Map<BigDecimal, BigDecimal> schdCratTmplPkMap) {
//        List<SCHD_CRAT_TMPLTMsg> copySchdCratTmplTMsgList = new ArrayList<SCHD_CRAT_TMPLTMsg>(0);
//
//        for (BigDecimal origCpoSvcDtlPk : cpoSvcDtlPkMap.keySet()) {
//            SCHD_CRAT_TMPLTMsg keySchdCratTmplTMsg = new SCHD_CRAT_TMPLTMsg();
//            keySchdCratTmplTMsg.setSQLID("001");
//            keySchdCratTmplTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            keySchdCratTmplTMsg.setConditionValue("cpoSvcDtlPk01", origCpoSvcDtlPk);
//
//            SCHD_CRAT_TMPLTMsgArray copySchdCratTmplTMsgArray = (SCHD_CRAT_TMPLTMsgArray) EZDTBLAccessor.findByCondition(keySchdCratTmplTMsg);
//            for (int i = 0; i < copySchdCratTmplTMsgArray.getValidCount(); i++) {
//                SCHD_CRAT_TMPLTMsg copySchdCratTmplTMsg = new SCHD_CRAT_TMPLTMsg();
//                EZDMsg.copy(copySchdCratTmplTMsgArray.no(i), null, copySchdCratTmplTMsg, null);
//
//                copySchdCratTmplTMsg.schdCratTmplPk.setValue(ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SCHD_CRAT_TMPL_SQ));
//                copySchdCratTmplTMsg.cpoSvcDtlPk.setValue(cpoSvcDtlPkMap.get(origCpoSvcDtlPk));
//                copySchdCratTmplTMsg.refCpoOrdNum.setValue(pMsg.cpoOrdNum_A.getValue());
//
//                copySchdCratTmplTMsgList.add(copySchdCratTmplTMsg);
//                schdCratTmplPkMap.put(copySchdCratTmplTMsgArray.no(i).schdCratTmplPk.getValue(), copySchdCratTmplTMsg.schdCratTmplPk.getValue());
//            }
//        }
//        return copySchdCratTmplTMsgList;
//    }

//    private List<SCHD_CRAT_TMPL_LINETMsg> getCopySchdCratTmplLineList(NWZC150001PMsg pMsg, Map<BigDecimal, BigDecimal> schdCratTmplPkMap, Map<BigDecimal, BigDecimal> cpoSvcConfigRefPkMa) {
//        List<SCHD_CRAT_TMPL_LINETMsg> copySchdCratTmplLineTMsgList = new ArrayList<SCHD_CRAT_TMPL_LINETMsg>(0);
//
//        for (BigDecimal origSchdCratTmplPk : schdCratTmplPkMap.keySet()) {
//            SCHD_CRAT_TMPL_LINETMsg keySchdCratTmplLineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
//            keySchdCratTmplLineTMsg.setSQLID("003");
//            keySchdCratTmplLineTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
//            keySchdCratTmplLineTMsg.setConditionValue("schdCratTmplPk01", origSchdCratTmplPk);
//
//            SCHD_CRAT_TMPL_LINETMsgArray copySchdCratTmplLineTMsgArray = (SCHD_CRAT_TMPL_LINETMsgArray) EZDTBLAccessor.findByCondition(keySchdCratTmplLineTMsg);
//            for (int i = 0; i < copySchdCratTmplLineTMsgArray.getValidCount(); i++) {
//                SCHD_CRAT_TMPL_LINETMsg copySchdCratTmplLineTMsg = new SCHD_CRAT_TMPL_LINETMsg();
//                EZDMsg.copy(copySchdCratTmplLineTMsgArray.no(i), null, copySchdCratTmplLineTMsg, null);
//
//                copySchdCratTmplLineTMsg.schdCratTmplPk.setValue(schdCratTmplPkMap.get(origSchdCratTmplPk));
//                copySchdCratTmplLineTMsg.cpoSvcConfigRefPk.setValue(cpoSvcConfigRefPkMa.get(copySchdCratTmplLineTMsgArray.no(i).cpoSvcConfigRefPk.getValue()));
//
//                copySchdCratTmplLineTMsgList.add(copySchdCratTmplLineTMsg);
//            }
//        }
//        return copySchdCratTmplLineTMsgList;
//    }
    // 2017/05/11 S21_NA#Review structure Lv.2 RS#8389 Del End

    /**
     * <pre>
     * get max biz process log primary key
     * @param queryParam
     *  Key: subSysId
     *       procId
     *       eventId (available like search)
     *       docTpCd
     *       docId
     *       cpoOrdNum
     *       userId
     *       ezUptime
     * @return BigDecimal
     */
    private BigDecimal getMaxBizProcPk(Map<String, String> queryParam) {

        return (BigDecimal) this.ssmClient.queryObject("getMaxBizProcPk", queryParam);
    }

    // 2017/05/11 S21_NA#Review structure Lv.2 RS#8389 Del Start
//    /**
//     * <pre>
//     * Get CPO_SVC Record
//     * @param glblCmpyCd Global Company Code (Search Key)
//     * @param cpoOrdNum CPO Order Number (SearchKey)
//     * @return List<CPO_SVCTMsg>
//     * </pre>
//     */
//    private List<CPO_SVCTMsg> getCpoSvcInfoByCpoOrdNum(String glblCmpyCd, String cpoOrdNum) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoOrdNum", cpoOrdNum);
//        return (List<CPO_SVCTMsg>)this.ssmClient.queryObjectList("getCpoSvcInfoByCpoOrdNum", queryParam);
//    }

//    private List<Map<String, Object>> getCpoSvcDtlForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return (List<Map<String, Object>>)this.ssmClient.queryObjectList("getCpoSvcDtlForShell", queryParam);
//    }

//    private List<Map<String, Object>> getCpoSvcConfigRefForShell(String glblCmpyCd, BigDecimal cpoSvcPk) {
//        Map<String, String> queryParam = new HashMap<String, String>();
//        queryParam.put("glblCmpyCd", glblCmpyCd);
//        queryParam.put("cpoSvcPk", cpoSvcPk.toString());
//        return (List<Map<String, Object>>)this.ssmClient.queryObjectList("getCpoSvcConfigRefForShell", queryParam);
//    }
    // 2017/05/11 S21_NA#Review structure Lv.2 RS#8389 Del End

    // Add Start 2017/09/13 S21_NA$18859
//    private void setDefaultFromOrdTp(CPOTMsg cpoTmsg, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
//
//        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cpoTmsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, cpoTmsg.dsOrdTpCd);
//
//        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
//
//        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.actvFlg.getValue())) {
//            return;
//        }
//        if ((ZYPDateUtil.compare(tMsg.effFromDt.getValue(), pMsg.slsDt.getValue()) > 0)) {
//            return;
//        }
//        if (ZYPCommonFunc.hasValue(tMsg.effThruDt) && //
//                (ZYPDateUtil.compare(tMsg.effThruDt.getValue(), pMsg.slsDt.getValue()) < 0)) {
//            return;
//        }
//
//        ZYPEZDItemValueSetter.setValue(cpoTmsg.frtCondCd, tMsg.frtCondCd);
//        ZYPEZDItemValueSetter.setValue(cpoTmsg.flPrcListCd, tMsg.defPrcCatgCd);
//        ZYPEZDItemValueSetter.setValue(cpoTmsg.carrSvcLvlCd, tMsg.defCarrSvcLvlCd);
//        ZYPEZDItemValueSetter.setValue(cpoTmsg.addShpgSvcLvlCd, tMsg.defShpgSvcLvlCd);
//    }

//    private void setDefaultPmtTerm(CPOTMsg cpoTmsg, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {
//
//        String defaultPmtTerm = NWXC150001DsCheck.getDefaultLocPmtTerm(pMsg.glblCmpyCd.getValue(), cpoTmsg.billToCustCd.getValue());
//        if (ZYPCommonFunc.hasValue(defaultPmtTerm)) {
//            ZYPEZDItemValueSetter.setValue(cpoTmsg.addPmtTermCashDiscCd, defaultPmtTerm);
//            return;
//        }
//        defaultPmtTerm = NWXC150001DsCheck.getDefaultAcctPmtTerm(pMsg.glblCmpyCd.getValue(), cpoTmsg.billToCustAcctCd.getValue());
//        if (ZYPCommonFunc.hasValue(defaultPmtTerm)) {
//            ZYPEZDItemValueSetter.setValue(cpoTmsg.addPmtTermCashDiscCd, defaultPmtTerm);
//            return;
//        }
//        msgIdMgr.addXxMsgId(NWZM0925E, pMsg);
//    }
    /**
     * Derive Default Carrier Service Level
     * @param pMsg NWZC150001PMsg
     * @return No API Error : true
     */
    public void setDefaultCarrSvcLvl(CPOTMsg cpoTmsg, NWZC150001PMsg pMsg, S21ApiMessageIdMgr msgIdMgr) {

        if (!ZYPCommonFunc.hasValue(cpoTmsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(cpoTmsg.addShpgSvcLvlCd)) {
            return;
        }
        if (!FRT_COND.COLLECT.equals(cpoTmsg.frtCondCd.getValue())) {
            return;
        }

        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(cpoTmsg, pMsg);

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                msgIdMgr.addXxMsgId(msgId, pMsg);

                if (msgId.endsWith("E")) {
                    return;
                }
            }
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            ZYPEZDItemValueSetter.setValue(cpoTmsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum.getValue());

            String carrSvcLvlCd = NWZC150001Query.getInstance().getCarrSvcLvlCd(pMsg, vndCd);
            ZYPEZDItemValueSetter.setValue(cpoTmsg.carrSvcLvlCd, carrSvcLvlCd);
        }
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param cpoTmsg CPOTMsg
     * @param nwzc1500pMsg NWZC150001PMsg
     * @return NMZC611001PMsg
     */
    public NMZC611001PMsg callDefaultCarrierApi(CPOTMsg cpoTmsg, NWZC150001PMsg nwzc1500pMsg) {

        NMZC611001PMsg pMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, nwzc1500pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, nwzc1500pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum, cpoTmsg.shipToCustAcctCd);
        new NMZC611001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }

    private boolean registCopyDsCpoRMAConfig(NWZC150001PMsg pMsg) {

        NWZC150001PMsg newPMsg = new NWZC150001PMsg();

        CPOTMsg origDsCpoTMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(origDsCpoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(origDsCpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);

        origDsCpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(origDsCpoTMsg);
        if (origDsCpoTMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, origDsCpoTMsg.dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, origDsCpoTMsg.dsOrdTpCd);

            EZDMsg.copy(origDsCpoTMsg, null, newPMsg, null);
            ZYPEZDItemValueSetter.setValue(newPMsg.cpoOrdNum, pMsg.cpoOrdNum_A);
        }

        DS_CPO_CONFIGTMsg origDsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();
        origDsCpoConfigTMsg.setSQLID("001");
        origDsCpoConfigTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origDsCpoConfigTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        DS_CPO_CONFIGTMsgArray copyDsCpoConfigTMsgArray = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(origDsCpoConfigTMsg);
        for (int i = 0; i < copyDsCpoConfigTMsgArray.getValidCount(); i++) {

            if (!CONFIG_CATG.OUTBOUND.equals(copyDsCpoConfigTMsgArray.no(i).configCatgCd.getValue())) {
                continue;
            }

            NWZC150001_cpoConfigPMsg copyDsCpoConfigPMsg = newPMsg.cpoConfig.no(newPMsg.cpoConfig.getValidCount());
            EZDMsg.copy(copyDsCpoConfigTMsgArray.no(i), null, copyDsCpoConfigPMsg, null);

            ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
            ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.shipToCustCd, copyDsCpoConfigTMsgArray.no(i).shipToCustLocCd);
            ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.billToCustCd, copyDsCpoConfigTMsgArray.no(i).billToCustLocCd);

            copyDsCpoConfigPMsg.configCatgCd.setValue(CONFIG_CATG.INBOUND);

            if (!ZYPCommonFunc.hasValue(copyDsCpoConfigPMsg.svcConfigMstrPk)) {
                // Not exists IB in the config.
                ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.configTpCd, CONFIG_TP.RETURN_NEW);
            } else {
                // Inbound Close
                ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.configTpCd, CONFIG_TP.RETURN_EXISTING_IB);
                checkDiffShipToLoc(pMsg, copyDsCpoConfigPMsg); // 2019/09/24 QC#53592 Add
            }

            newPMsg.cpoConfig.setValidCount(newPMsg.cpoConfig.getValidCount() + 1);
        }

        // 2018/06/25 QC#20154 Mod Start
        //new NWZC150001CpoConfMain().execute(newPMsg, this.onBatchType);
        new NWZC150001CpoConfMain().execute(newPMsg, this.onBatchType, null);
        // 2018/06/25 QC#20154 Mod End

        for (int i = 0; i < newPMsg.xxMsgIdList.getValidCount(); i++) {

            final String xxMsgId = newPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                setMsgId(pMsg, xxMsgId);
                return true;
            }
        }
        return false;
    }

    // 2019/09/24 QC#53592 Add Start
    private static void checkDiffShipToLoc(NWZC150001PMsg pMsg,NWZC150001_cpoConfigPMsg copyDsCpoConfigPMsg) {
        String smmShipToCust = "";
        SVC_MACH_MSTRTMsg smmTMsg = new SVC_MACH_MSTRTMsg();
        smmTMsg.setSQLID("005");
        smmTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        smmTMsg.setConditionValue("svcConfigMstrPk01", copyDsCpoConfigPMsg.svcConfigMstrPk.getValue());
        smmTMsg.setConditionValue("svcMachTpCd01", SVC_MACH_TP.MACHINE);
        SVC_MACH_MSTRTMsgArray svcMachMstr = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(smmTMsg);
        if (svcMachMstr == null || svcMachMstr.getValidCount() == 0) {
            return;
        }
        smmTMsg = (SVC_MACH_MSTRTMsg) svcMachMstr.get(0);
        smmShipToCust = smmTMsg.curLocNum.getValue();
        if(!ZYPCommonFunc.hasValue(smmShipToCust)){
            return;
        }
        SHIP_TO_CUSTTMsg stcTMsg = new SHIP_TO_CUSTTMsg();
        stcTMsg.setSQLID("004");
        stcTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        stcTMsg.setConditionValue("shipToCustCd01",smmShipToCust);
        SHIP_TO_CUSTTMsgArray shipToCust = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(stcTMsg);
        if (shipToCust == null || shipToCust.getValidCount() == 0) {
            return;
        }
        stcTMsg = (SHIP_TO_CUSTTMsg) shipToCust.get(0);
        if(LOC_GRP.CUSTOMER.equals(stcTMsg.locGrpCd.getValue())){
            if(!copyDsCpoConfigPMsg.shipToCustCd.equals(smmShipToCust)){
                ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.shipToCustCd, smmShipToCust);
                ZYPEZDItemValueSetter.setValue(copyDsCpoConfigPMsg.shipToCustAcctCd, stcTMsg.sellToCustCd); // 2019/10/15 QC#53592-1 Add
            }
        }
    }
    // 2019/09/24 QC#53592 Add End

    /**
     * Create Map<CopyFromConfigPk, CopyToConfigTmsg>
     * @param pMsg
     * @param dsCpoConfigMap
     */
    private void createDsCpoConfigMap(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {

        DS_CPO_CONFIGTMsg tMsg = new DS_CPO_CONFIGTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        DS_CPO_CONFIGTMsgArray copyFromTMsgArray = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        tMsg = new DS_CPO_CONFIGTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum_A.getValue());
        DS_CPO_CONFIGTMsgArray copyToTMsgArray = (DS_CPO_CONFIGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        for (int i = 0; i < copyFromTMsgArray.getValidCount(); i++) {
            DS_CPO_CONFIGTMsg copyFromTMsg = copyFromTMsgArray.no(i);

            for (int j = 0; j < copyToTMsgArray.getValidCount(); j++) {
                DS_CPO_CONFIGTMsg copyToTMsg = copyToTMsgArray.no(j);

                if (S21StringUtil.isEquals(copyFromTMsg.dsOrdPosnNum.getValue(), copyToTMsg.dsOrdPosnNum.getValue()) //
                        && S21StringUtil.isEquals(copyFromTMsg.configCatgCd.getValue(), CONFIG_CATG.OUTBOUND)) {

                    dsCpoConfigMap.put(copyFromTMsg.dsCpoConfigPk.getValue(), copyToTMsg);
                    break;
                }
            }
        }
    }

    private boolean callCpoReturnUpdApi(NWZC150001PMsg pMsg, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {

        NWZC153001PMsg rtrnPMsg = new NWZC153001PMsg();

        CPOTMsg origCpoMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(origCpoMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(origCpoMsg.cpoOrdNum, pMsg.cpoOrdNum);
        origCpoMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(origCpoMsg);

        EZDMsg.copy(origCpoMsg, null, rtrnPMsg, null);

        ZYPEZDItemValueSetter.setValue(rtrnPMsg.glblCmpyCd, origCpoMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.xxRqstTpCd, NWZC153001Constant.RQST_HDR_SAVE);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.cpoOrdNum, pMsg.cpoOrdNum_A);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcBaseDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.xxValUpdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordSrcRefNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.origCpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.xxScrEdtTpCd, NWZC153001Constant.SCRN_EDT_TP_PURE);
        ZYPEZDItemValueSetter.setValue(rtrnPMsg.diChkHldFlg, ZYPConstant.FLG_OFF_N);

        CPO_DTLTMsg origCpoDtlTMsg = new CPO_DTLTMsg();
        origCpoDtlTMsg.setSQLID("001");
        origCpoDtlTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origCpoDtlTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        CPO_DTLTMsgArray copyCpoDtlTMsgArray = (CPO_DTLTMsgArray) EZDTBLAccessor.findByCondition(origCpoDtlTMsg);

        int idx = 0;
        // Copy Return Detail
        for (; idx < copyCpoDtlTMsgArray.getValidCount(); idx++) {
            EZDMsg.copy(copyCpoDtlTMsgArray.no(idx), null, rtrnPMsg.ordRtrnDtlList.no(idx), null);

            DS_CPO_CONFIGTMsg copyDsCpoConfigTMsg = new DS_CPO_CONFIGTMsg();

            copyDsCpoConfigTMsg = dsCpoConfigMap.get(copyCpoDtlTMsgArray.no(idx).dsCpoConfigPk.getValue());

            if (copyDsCpoConfigTMsg == null) {
                continue;
            }

            BigDecimal newDsCpoConfigPk = copyDsCpoConfigTMsg.dsCpoConfigPk.getValue();

            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).dsCpoConfigPk, newDsCpoConfigPk);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).xxRqstTpCd, NWZC153001Constant.RQST_DTL_SAVE);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineNum, copyCpoDtlTMsgArray.no(idx).cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).dsCpoRtrnLineSubNum, copyCpoDtlTMsgArray.no(idx).cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).ordSrcRefLineNum, copyCpoDtlTMsgArray.no(idx).cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).ordSrcRefLineSubNum, copyCpoDtlTMsgArray.no(idx).cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).origCpoOrdNum, pMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).origCpoDtlLineNum, copyCpoDtlTMsgArray.no(idx).cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).origCpoDtlLineSubNum, copyCpoDtlTMsgArray.no(idx).cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).pmtTermCd, origCpoMsg.addPmtTermCd);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).pmtTermCashDiscCd, rtrnPMsg.addPmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).cashDiscTermCd, rtrnPMsg.addPmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).cpoOrdTpCd, rtrnPMsg.cpoOrdTpCd);
            // 2018/07/30 S21_NA#26413 del start
//            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).rtrnRsnCd, RTRN_RSN.REGULAR_RETURN);
            // 2018/07/30 S21_NA#26413 del start
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).mdseCd, getRtrnMdseCd(copyCpoDtlTMsgArray.no(idx)));
            // 2018/09/05 S21_NA#26755 Del Start
//            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).ordQty, copyCpoDtlTMsgArray.no(idx).ordQty.getValue().negate());
            // 2018/09/05 S21_NA#26755 Del End
            // 2018/09/05 S21_NA#26755 Add Start
            BigDecimal ordQty = copyCpoDtlTMsgArray.no(idx).ordQty.getValue();
            BigDecimal cancQty = copyCpoDtlTMsgArray.no(idx).cancQty.getValue();
            if (cancQty != null && BigDecimal.ZERO.compareTo(cancQty) < 0 //
                    && ordQty != null && BigDecimal.ZERO.compareTo(ordQty) == 0) {
                ordQty = cancQty.negate();
                int ordCustUomQty = getOrdCustUomQty(pMsg.glblCmpyCd.getValue(), copyCpoDtlTMsgArray.no(idx).mdseCd.getValue(), copyCpoDtlTMsgArray.no(idx).custUomCd.getValue(), ordQty.intValue());
                rtrnPMsg.ordRtrnDtlList.no(idx).ordQty.setValue(ordQty);
                rtrnPMsg.ordRtrnDtlList.no(idx).ordCustUomQty.setValue(ordCustUomQty);
            } else {
                ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).ordQty, copyCpoDtlTMsgArray.no(idx).ordQty.getValue().negate());
                ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).ordCustUomQty, copyCpoDtlTMsgArray.no(idx).ordCustUomQty.getValue().negate());
            }
            rtrnPMsg.ordRtrnDtlList.no(idx).baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
            rtrnPMsg.ordRtrnDtlList.no(idx).dplyLineRefNum.clear();
            // 2018/09/05 S21_NA#26755 Add End
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).rtrnQty, BigDecimal.ZERO);
            // 2018/09/05 S21_NA#26755 Del Start
//            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).ordCustUomQty, copyCpoDtlTMsgArray.no(idx).ordCustUomQty.getValue().negate());
            // 2018/09/05 S21_NA#26755 Del End
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).dsOrdLineCatgCd, NWZC150001Query.getInstance().getInBoundLineCatg( //
                    pMsg, rtrnPMsg.dsOrdCatgCd.getValue(), rtrnPMsg.ordRtrnDtlList.no(idx).dsOrdLineCatgCd.getValue(), rtrnPMsg.dsOrdTpCd.getValue()));

            SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
            tMsg.setSQLID("004");
            tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            tMsg.setConditionValue("svcConfigMstrPk01", rtrnPMsg.ordRtrnDtlList.no(idx).svcConfigMstrPk.getValue());
            tMsg.setConditionValue("mdseCd01", rtrnPMsg.ordRtrnDtlList.no(idx).mdseCd.getValue());
            SVC_MACH_MSTRTMsgArray smmTMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

            for (int i = 0; i < smmTMsgArray.getValidCount(); i++) {
                boolean dupSerFlg = false;
                SVC_MACH_MSTRTMsg resultTMsg = smmTMsgArray.no(i);
                for (int x = 0 ; x < rtrnPMsg.ordRtrnDtlList.getValidCount(); x++) {
                    if ( S21StringUtil.isEquals(resultTMsg.serNum.getValue(), rtrnPMsg.ordRtrnDtlList.no(x).serNum.getValue())) {
                        dupSerFlg = true;
                         break;
                    }
                }
                if (!dupSerFlg) {
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).serNum, resultTMsg.serNum);
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).svcMachMstrPk, resultTMsg.svcMachMstrPk);
                    break;
                }
            }
            // 2017/10/24 S21_NA#21707 Add Start
            if (needsWh(rtrnPMsg.glblCmpyCd.getValue(), rtrnPMsg.ordRtrnDtlList.no(idx).mdseCd.getValue())) {

                NWZC180001PMsg defaultWHPMsg = callDefaultWHApiForRtrn(rtrnPMsg, rtrnPMsg.ordRtrnDtlList.no(idx));

                if (!S21ApiUtil.isXxMsgId(defaultWHPMsg)) {
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).rtlWhCd, defaultWHPMsg.rtlWhCd);
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).rtlSwhCd, defaultWHPMsg.rtlSwhCd);

                } else {
                    rtrnPMsg.ordRtrnDtlList.no(idx).rtlWhCd.clear();
                    rtrnPMsg.ordRtrnDtlList.no(idx).rtlSwhCd.clear();
                }
            }
            // 2017/10/24 S21_NA#21707 Add End

            // Add Start 2019/05/30 QC#50405
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).slsRepOrSlsTeamTocCd, pMsg.slsRepCd);
            // Add End 2019/05/30 QC#50405
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.ordRtrnDtlList.no(idx).cpoSrcTpCd, CPO_SRC_TP.COPY); //ADD 2019/06/05 QC#50710

        }
        rtrnPMsg.ordRtrnDtlList.setValidCount(idx);

        ORD_PRC_CALC_BASETMsg origPrcCalcBaseTMsg = new ORD_PRC_CALC_BASETMsg();
        // 2019/10/30 S21_QC#53556 Mod Start
        //origPrcCalcBaseTMsg.setSQLID("002");
        origPrcCalcBaseTMsg.setSQLID("004");
        // 2019/10/30 S21_QC#53556 Mod End
        origPrcCalcBaseTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        origPrcCalcBaseTMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());
        ORD_PRC_CALC_BASETMsgArray origPrcCalcBaseTMsgArray = (ORD_PRC_CALC_BASETMsgArray) EZDTBLAccessor.findByCondition(origPrcCalcBaseTMsg);

        // QC#50130 2019/05/15 Add Start
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.FREIGHT,        PRC_COND_TP.RMA_FREIGHT);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.HANDLING_FEE,   PRC_COND_TP.RMA_HANDLING_FEE);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.FUEL_SURCHARGE, PRC_COND_TP.RMA_FUEL_SURCHARGE);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.SHIPPING_FEE,   PRC_COND_TP.RMA_SHIPPING_FEE);
        PRC_DTL_GRP_COND_TP_RMA_MAP.put(PRC_DTL_GRP.RESTOCKING_FEE, PRC_COND_TP.RMA_RESTOCK_FEE);
        Map<BigDecimal, BigDecimal> specCondPrcMap = new HashMap<BigDecimal, BigDecimal>();
        BigDecimal specCondPrcPk = null;
        int idx2 = 0;
        // QC#50130 2019/05/15 Add End
        // Copy Return Price Calc List
        for (idx = 0; idx < origPrcCalcBaseTMsgArray.getValidCount(); idx++) {
            // QC#9700  2018/09/03 Add Start
            if(!S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, origPrcCalcBaseTMsgArray.no(idx).prcRuleApplyFlg.getValue())){
                continue;
            }
            // QC#9700  2018/09/03 Add End
            EZDMsg.copy(origPrcCalcBaseTMsgArray.no(idx), null, rtrnPMsg.prcCalcList.no(idx2), null);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).dsCpoRtrnLineNum, origPrcCalcBaseTMsgArray.no(idx).cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).dsCpoRtrnLineSubNum, origPrcCalcBaseTMsgArray.no(idx).cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).calcPrcAmtRate, origPrcCalcBaseTMsgArray.no(idx).calcPrcAmtRate.getValue().negate());
            // QC#26326 2018/08/22 Add Start
            if (!PRC_COND_TP.BASE_PRICE.equals(origPrcCalcBaseTMsgArray.no(idx).prcCondTpCd.getValue()) && !PRC_COND_TP.MANUAL_PRICE.equals(origPrcCalcBaseTMsgArray.no(idx).prcCondTpCd.getValue())) {
                if (!PRC_DTL_GRP.TAX.equals(origPrcCalcBaseTMsgArray.no(idx).prcDtlGrpCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(origPrcCalcBaseTMsgArray.no(idx).manPrcAmtRate)) {
                        ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).manPrcAmtRate, origPrcCalcBaseTMsgArray.no(idx).autoPrcAmtRate.getValue());
                        rtrnPMsg.prcCalcList.no(idx2).autoPrcAmtRate.clear(); // QC#50747 2019/06/10 Add
                    }
                    // QC#50130 2019/05/15 Add Start
                    specCondPrcPk = null;
                    String prcCondTpCd = PRC_DTL_GRP_COND_TP_RMA_MAP.get(origPrcCalcBaseTMsgArray.no(idx).prcDtlGrpCd.getValue());
                    if (ZYPCommonFunc.hasValue(prcCondTpCd)) {
                        specCondPrcPk = specCondPrcMap.get(origPrcCalcBaseTMsgArray.no(idx).specCondPrcPk.getValue());
                        if (ZYPCommonFunc.hasValue(specCondPrcPk)) {
                            ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).prcCondTpCd, prcCondTpCd);
                            ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).specCondPrcPk, specCondPrcPk);
                        } else {
                            specCondPrcPk = getSpecCondPrcPk(pMsg.glblCmpyCd.getValue(), prcCondTpCd);
                            if (ZYPCommonFunc.hasValue(specCondPrcPk)) {
                                ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).prcCondTpCd, prcCondTpCd);
                                ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(idx2).specCondPrcPk, specCondPrcPk);
                                specCondPrcMap.put(origPrcCalcBaseTMsgArray.no(idx).specCondPrcPk.getValue(), specCondPrcPk);
                            }
                        }
                    }
                    // QC#50130 2019/05/15 Add End
                }
            }
            // QC#26326 2018/08/22 Add End
            rtrnPMsg.prcCalcList.no(idx2).cpoRtrnPrcCalcBasePk.clear();
            idx2++;
        }
        // QC#50130 2019/05/15 Add End
        rtrnPMsg.prcCalcList.setValidCount(idx2);

        // QC#50130 2019/05/15 Add Start
        // replace specCondPk for Roundfactor
        for (Entry<BigDecimal, BigDecimal> entry : specCondPrcMap.entrySet()) {
            BigDecimal key = entry.getKey();
            BigDecimal value = entry.getValue();
            for (int i = 0; i < rtrnPMsg.prcCalcList.getValidCount(); i++) {
                if (rtrnPMsg.prcCalcList.no(i).specCondPrcPk.getValue().compareTo(key) == 0) {
                    ZYPEZDItemValueSetter.setValue(rtrnPMsg.prcCalcList.no(i).specCondPrcPk, value);
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(rtrnPMsg.invRcpntCustCd, rtrnPMsg.billToCustCd);

        final List<NWZC153002PMsg> cpoRtnUpdApiOutMsgList = new ArrayList<NWZC153002PMsg>();
        new NWZC153001().execute(rtrnPMsg, cpoRtnUpdApiOutMsgList, this.onBatchType);

        if (S21ApiUtil.isXxMsgId(rtrnPMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(rtrnPMsg);
            for (String msgId : ml) {
                setMsgId(pMsg, msgId);
            }
        }

        boolean rtrnDtlErrFlg = false;
        for (int i = 0; i < cpoRtnUpdApiOutMsgList.size(); i++) {
            NWZC150003PMsg resPMsg = new NWZC150003PMsg();
            EZDMsg.copy(cpoRtnUpdApiOutMsgList.get(i), null, resPMsg, null);
            ZYPEZDItemValueSetter.setValue(resPMsg.cpoDtlLineNum, cpoRtnUpdApiOutMsgList.get(i).dsCpoRtrnLineNum);
            ZYPEZDItemValueSetter.setValue(resPMsg.cpoDtlLineSubNum, cpoRtnUpdApiOutMsgList.get(i).dsCpoRtrnLineSubNum);

            NWZC153002PMsg cpoUpdApiOutMsg = cpoRtnUpdApiOutMsgList.get(i);
            for (int j = 0; j < cpoUpdApiOutMsg.xxMsgIdList.getValidCount(); j++) {
                final String xxMsgId = cpoUpdApiOutMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                if (xxMsgId.endsWith("E")) {
                    setMsgId(pMsg, xxMsgId);
                    rtrnDtlErrFlg = true;
                }
            }
        }

        for (int i = 0; i < rtrnPMsg.xxMsgIdList.getValidCount(); i++) {
            final String xxMsgId = rtrnPMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            if (xxMsgId.endsWith("E")) {
                setMsgId(pMsg, xxMsgId);
                return true;
            }
        }
        return rtrnDtlErrFlg;
    }

    private String getRtrnMdseCd(CPO_DTLTMsg cpoDtlTmsg) {

        String rtrnMdseCd = null;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cpoDtlTmsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", cpoDtlTmsg.cpoOrdNum.getValue());
        params.put("cpoDtlLineNum", cpoDtlTmsg.cpoDtlLineNum.getValue());
        params.put("cpoDtlLineSubNum", cpoDtlTmsg.cpoDtlLineSubNum.getValue());
        // QC#50126 2019/05/13 Add Start
        params.put("invLineCatgCd", INV_LINE_CATG.ITEM);
        // QC#50126 2019/05/13 Add End
        

        rtrnMdseCd = (String) this.ssmClient.queryObject("getRtrnMdseCd", params);

        return rtrnMdseCd;
    }
    // Add End 2017/09/13 S21_NA#18859
    // 2017/10/24 S21_NA#21707 Add Start
    private boolean needsWh(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = getMdse(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return false;
        }

        if (MDSE_TP.SET.equals(mdseTMsg.mdseTpCd.getValue())) {
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
            return false;
        }

        return true;
    }

    private NWZC180001PMsg callDefaultWHApiForRtrn(NWZC153001PMsg rtrnPMsg, NWZC153001_ordRtrnDtlListPMsg rtrnDtlPMsg) {

        NWZC180001PMsg pMsg = new NWZC180001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, rtrnPMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, rtrnPMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, rtrnPMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, rtrnPMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, rtrnDtlPMsg.rtrnRsnCd);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rtrnDtlPMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, rtrnDtlPMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, rtrnDtlPMsg.ordQty);
        if (ZYPCommonFunc.hasValue(rtrnDtlPMsg.svcMachMstrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, rtrnDtlPMsg.svcMachMstrPk);
        }

        new NWZC180001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        return pMsg;
    }
    // 2017/10/24 S21_NA#21707 Add End

    /**
     * Derive Default Sales Rep Data
     * @param bizMsg Business Message
     * @param pMsg pMsg of NWZC150001PMsg
     * @return Succeed derive : true
     */
    private boolean deriveDefaultSlsRepForCopyAllReturn(NWZC150001PMsg pMsg) {

        pMsg.slsRepCd.clear();

        CPO_VTMsg tMsg = new CPO_VTMsg();
        tMsg.setSQLID("501");
        tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("cpoOrdNum01", pMsg.cpoOrdNum.getValue());

        CPO_VTMsgArray tMsgAry = (CPO_VTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return false;
        }
        tMsg = tMsgAry.no(0);
        ZYPEZDItemValueSetter.setValue(pMsg.addShipToCustCd, tMsg.addShipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, tMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, tMsg.dsOrdTpCd);

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, tMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, tMsg.addShipToCustCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, tMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, tMsg.dsOrdTpCd);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", tMsg.glblCmpyCd);
        params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET);
        params.put("dsOrdCatgCd", tMsg.dsOrdCatgCd);
        params.put("dsOrdTpCd", tMsg.dsOrdTpCd);
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        String resultFlg = (String) ssmClient.queryObject("getOrdCatgBizCtx", params);

        if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.xxModeCd, NMZC260001Constant.XX_MODE_CD_EMSD);
            ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustAcctCd, tMsg.shipToCustAcctCd);
        }

        if (!callDefSlsRepApi(pMsg, nMZC260001PMsg, tMsg.addShipToCustCd.getValue())) {
            return false;
        }

        String curLineBizTpCd = tMsg.lineBizTpCd.getValue();
        String trtyGrpTpTxt = getTrtyGrpTpTxtFromDsOrdTpCd(pMsg);
        List<String> trtyGrpTpCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)) {
            trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
        }
        List<String> targetWriterList = getTargetWriterList(pMsg.glblCmpyCd.getValue());
        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();
        List<NMZC260001_defSlsRepListPMsg> matchLobRoleSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>();

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        List<String> psnCdList = new ArrayList<String>(0);
        List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            if(ZYPCommonFunc.hasValue(defSlsRepPMsg.xxRsltFlg) && ZYPConstant.FLG_ON_Y.equals(defSlsRepPMsg.xxRsltFlg.getValue())//
                    && !matchLobRoleSlsRepPMsgList.isEmpty()){
                continue;
            }

            if (ZYPCommonFunc.hasValue(defSlsRepPMsg.psnCd)) {
                if (psnCdList.contains(defSlsRepPMsg.psnCd.getValue())) {
                    continue;
                } else {
                    psnCdList.add(defSlsRepPMsg.psnCd.getValue());
                }
            }

            if ((trtyGrpTpCdList.isEmpty() && defSlsRepPMsg.lineBizTpCd.getValue().equals(curLineBizTpCd))
                    || (trtyGrpTpCdList.size() > 0 && trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
                matchLobSlsRepPMsgList.add(defSlsRepPMsg);

                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (targetWriterList.contains(lineBizRoleTpCd)) {
                    matchLobRoleSlsRepPMsgList.add(defSlsRepPMsg);
                }
            }

            params.clear();
            params.put("glblCmpyCd", tMsg.glblCmpyCd);
            params.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP);
            params.put("dsOrdCatgCd", tMsg.dsOrdCatgCd);
            params.put("dsOrdTpCd", tMsg.dsOrdTpCd);
            params.put("flgY", ZYPConstant.FLG_ON_Y);
            String fstBizCtxAttbTxt = (String) ssmClient.queryObject("getOrdCatgBizCtxFstAttbTxt", params);
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                if (fstBizCtxAttbTxt.equals(defSlsRepPMsg.lineBizRoleTpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(defSlsRepPMsg.slsCrQuotFlg.getValue())) {

                    List<Map<String, Object>> slsRepList = getSalesRepList(tMsg.glblCmpyCd.getValue(), defSlsRepPMsg.tocCd.getValue());
                    if (slsRepList != null && slsRepList.size() != 0) {
                        for (Map<String, Object> slsRepMap : slsRepList) {
                            dummyRepList.add(slsRepMap);
                        }
                    }
                }
            }
        }

        if (defSlsRepMsgArray.getValidCount() > 0) {
            if (matchLobRoleSlsRepPMsgList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(pMsg.slsRepCd, matchLobRoleSlsRepPMsgList.get(0).tocCd);
            }
        }

        if (matchLobSlsRepPMsgList.size() > 0) {
            setDefaultSlsRep(pMsg, matchLobSlsRepPMsgList, targetWriterList);
        }

        return true;
    }

    private void setDefaultSlsRep(NWZC150001PMsg pMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList, List<String> targetWriterList) {
        SLS_CR_PCT_MD mode = getSalesCreditPrecentMode(slsRepPMsgList);
        BigDecimal writerPct = BigDecimal.ZERO;
        BigDecimal installerPct = BigDecimal.ZERO;
        if (SLS_CR_PCT_MD.WRITER_AND_INSTALLER == mode) {
            BigDecimal pct = ZYPCodeDataUtil.getNumConstValue(DEF_SLS_CR_PCT_WRITER, pMsg.glblCmpyCd.getValue());
            if (pct != null) {
                writerPct = pct;
                installerPct = PCT_100.subtract(writerPct);
            }
        }

        int validCnt = 0;
        for (int i = 0; i < slsRepPMsgList.size() && i < 10; i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(validCnt);

            NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = pMsg.cpoSlsCr.no(i);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCd, defSlsRepPMsg.tocCd);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepRoleTpCd, defSlsRepPMsg.lineBizRoleTpCd);
            if (SLS_CR_PCT_MD.WRITER_ONLY == mode) {
                ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCrPct, PCT_100);
            } else if (SLS_CR_PCT_MD.WRITER_AND_INSTALLER == mode) {
                if (targetWriterList.contains(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCrPct, writerPct);
                } else {
                    ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCrPct, installerPct);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCrPct, BigDecimal.ZERO);
            }
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsCrQuotFlg, defSlsRepPMsg.slsCrQuotFlg);
            validCnt++;

        }
        pMsg.cpoSlsCr.setValidCount(validCnt);

    }

    private SLS_CR_PCT_MD getSalesCreditPrecentMode(List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList){

        boolean hasWriter = false;
        boolean hasInstaller = false;
        if (slsRepPMsgList.size() == 1) {
            return SLS_CR_PCT_MD.WRITER_ONLY;
        } else if (slsRepPMsgList.size() == 2) {
            for (NMZC260001_defSlsRepListPMsg data : slsRepPMsgList) {
                if (isWriter(data.lineBizRoleTpCd.getValue())) {
                    hasWriter = true;
                } else if (isInstaller(data.lineBizRoleTpCd.getValue())) {
                    hasInstaller = true;
                }
            }
            if (hasWriter && hasInstaller) {
                return SLS_CR_PCT_MD.WRITER_AND_INSTALLER;
            }
        }
        return SLS_CR_PCT_MD.NONE;
    }

    private static boolean isWriter(String slsRepRoleTpCd){
        // 2017/11/02 S21_NA#20146 Mod Start
        //List<String> writerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER);
        List<String> writerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER);
        // 2017/11/02 S21_NA#20146 Mod End
        return writerList.contains(slsRepRoleTpCd);
    }

    /**
     * isInstaller
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isInstaller(String slsRepRoleTpCd){
        List<String> installerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_INSTALLER, LINE_BIZ_ROLE_TP.LFS_INSTALLER, LINE_BIZ_ROLE_TP.PPS_INSTALLER);
        return installerList.contains(slsRepRoleTpCd);
    }

    private List<Map<String, Object>> getSalesRepList(String glblCmpyCd, String slsRepTocCd) {
        if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsRepTocCd", slsRepTocCd);

        return NWZC150001Common.autoCast(ssmClient.queryObjectList("getSalesRepList", ssmParam));
    }

    private List<String> getTargetWriterList(String glblCmpyCd) {
        LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
        lineBizRoleTpTMsg.setSQLID("001");
        lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
        LINE_BIZ_ROLE_TPTMsgArray tMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
        List<String> targetWriterList = new ArrayList<String>();
        if (tMsgArray != null && tMsgArray.length() > 0) {
            for (int i = 0; i < tMsgArray.length(); i++) {
                LINE_BIZ_ROLE_TPTMsg tMsg = (LINE_BIZ_ROLE_TPTMsg) tMsgArray.get(i);
                targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
            }
        }

        return targetWriterList;
    }

    private String getTrtyGrpTpTxtFromDsOrdTpCd(NWZC150001PMsg pMsg) {

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            return dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();
        }

        return null;
    }

        /**
     * Call NMZC2600 Default Sales Rep API
     * @param pMsg NWZC150001PMsg
     * @param mzPMsg NMZC260001PMsg
     * @param shipToCustCd Ship To Customer Code
     * @return has API error : false
     */
    private boolean callDefSlsRepApi(NWZC150001PMsg pMsg, NMZC260001PMsg mzPMsg, String shipToCustCd) {

        // call NMZC2600 Default Sales Rep API
        new NMZC260001().execute(mzPMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(mzPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(mzPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    setMsgId(pMsg, msgId);
                    return false;
                }
            }
        }

        return true;
    }

        /**
     * <pre>
     * Set the message id.
     * @param pMsg Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    private void setMsgId(NWZC150001PMsg pMsg, String val) {

        this.msgIdMgr.addXxMsgId(val, pMsg);
    }

    // 2018/09/05 S21_NA#26755 Add Start  
    // private void resetBaseCmptFlg(NWZC150001PMsg pMsg, List<CPO_DTLTMsg> cpoDtlTMsgList) { // 2019/02/05 S21_NA#30149 Mod
    private void resetBaseCmptFlg(NWZC150001PMsg pMsg, List<CPO_DTLTMsg> cpoDtlTMsgList, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {

        if (!NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue())) {
            return;
        }

        Map<String, List<CPO_DTLTMsg>> posnLineMap = new LinkedHashMap<String, List<CPO_DTLTMsg>>(0);
        for (CPO_DTLTMsg cpoDtlTMsg : cpoDtlTMsgList) {
            String dsOrdPosnNum = cpoDtlTMsg.dsOrdPosnNum.getValue();
            List<CPO_DTLTMsg> posnDtlList = posnLineMap.get(dsOrdPosnNum);
            if (posnDtlList == null) {
                posnDtlList = new ArrayList<CPO_DTLTMsg>(0);
            }
            posnDtlList.add(cpoDtlTMsg);
            posnLineMap.put(dsOrdPosnNum, posnDtlList);
        }

        for (String dsOrdPosnNum : posnLineMap.keySet()) {
            List<CPO_DTLTMsg> posnDtlList = posnLineMap.get(dsOrdPosnNum);
            // setBaseCmptFlg(pMsg, posnDtlList); // 2019/02/05 S21_NA#30149 Mod
            setBaseCmptFlg(pMsg, posnDtlList, dsCpoConfigMap);
        }
    }

    private void resetBaseCmptFlg4Return(NWZC150001PMsg pMsg, List<DS_CPO_RTRN_DTLTMsg> dsCpoRtnDtlTMsgList) {

        if (!NWXC150001DsCheck.isRetailEquipOrder(pMsg.glblCmpyCd.getValue(), pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue())) {
            return;
        }

        Map<String, List<DS_CPO_RTRN_DTLTMsg>> posnLineMap = new LinkedHashMap<String, List<DS_CPO_RTRN_DTLTMsg>>(0);
        for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : dsCpoRtnDtlTMsgList) {
            String dsOrdPosnNum = dsCpoRtrnDtlTMsg.dsOrdPosnNum.getValue();
            List<DS_CPO_RTRN_DTLTMsg> posnDtlList = posnLineMap.get(dsOrdPosnNum);
            if (posnDtlList == null) {
                posnDtlList = new ArrayList<DS_CPO_RTRN_DTLTMsg>(0);
            }
            posnDtlList.add(dsCpoRtrnDtlTMsg);
            posnLineMap.put(dsOrdPosnNum, posnDtlList);
        }

        for (String dsOrdPosnNum : posnLineMap.keySet()) {
            List<DS_CPO_RTRN_DTLTMsg> posnDtlList = posnLineMap.get(dsOrdPosnNum);
            setBaseCmptFlg4Return(pMsg, posnDtlList);
        }
    }

    // private void setBaseCmptFlg(NWZC150001PMsg pMsg, List<CPO_DTLTMsg> cpoDtlTMsgList) { // 2019/02/05 S21_NA#30149 Mod
    private void setBaseCmptFlg(NWZC150001PMsg pMsg, List<CPO_DTLTMsg> cpoDtlTMsgList, Map<BigDecimal, DS_CPO_CONFIGTMsg> dsCpoConfigMap) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String mainMachCoaMdseTpCd = NWXC150001DsCheck.getCoaMdseTpCd(glblCmpyCd);

        CPO_DTLTMsg baseCmptCpoDtlTMsg = null;
        CPO_DTLTMsg machCpoDtlTMsg = null;
        CPO_DTLTMsg instCpoDtlTMsg = null;
        
        for (CPO_DTLTMsg cpoDtlTMsg : cpoDtlTMsgList) {
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, cpoDtlTMsg.mdseCd.getValue());
            if (S21StringUtil.isEquals(mainMachCoaMdseTpCd, mdseTMsg.coaMdseTpCd.getValue())) {
                machCpoDtlTMsg = cpoDtlTMsg;
                break;
            }
            if (instCpoDtlTMsg == null //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                instCpoDtlTMsg = cpoDtlTMsg;
            }
        }

        if (machCpoDtlTMsg != null) {
            baseCmptCpoDtlTMsg = machCpoDtlTMsg;
        } else if (instCpoDtlTMsg != null) {
            baseCmptCpoDtlTMsg = instCpoDtlTMsg;
        }

        String baseRefNum = "";
        boolean isReqBaseCmptFlg = true; // 2019/02/05 S21_NA#30149 Add
        if (baseCmptCpoDtlTMsg != null) {
            baseRefNum = baseCmptCpoDtlTMsg.dsOrdPosnNum.getValue() + "." + String.valueOf(baseCmptCpoDtlTMsg.dsCpoLineNum.getValueInt());
            if (ZYPCommonFunc.hasValue(baseCmptCpoDtlTMsg.dsCpoLineSubNum)) {
                baseRefNum = baseRefNum + "." + String.valueOf(baseCmptCpoDtlTMsg.dsCpoLineSubNum.getValueInt());
            }
            // 2019/02/05 S21_NA#30149 Add Start
            for (DS_CPO_CONFIGTMsg tmsg: dsCpoConfigMap.values()) {
                if (S21StringUtil.isEquals(baseCmptCpoDtlTMsg.dsOrdPosnNum.getValue(), tmsg.dsOrdPosnNum.getValue())) {
                    isReqBaseCmptFlg = NWXC150001DsCheck.isReqBaseCmptFlgAtConfigTp(glblCmpyCd, tmsg.configTpCd.getValue());
                    break;
                }
            }
            // 2019/02/05 S21_NA#30149 Add End
        }

        for (CPO_DTLTMsg cpoDtlTMsg : cpoDtlTMsgList) {
            if (baseCmptCpoDtlTMsg == null) {
                cpoDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
                cpoDtlTMsg.dplyLineRefNum.clear();
            } else {
                if (S21StringUtil.isEquals(baseCmptCpoDtlTMsg.cpoDtlLineNum.getValue(), cpoDtlTMsg.cpoDtlLineNum.getValue()) //
                        && S21StringUtil.isEquals(baseCmptCpoDtlTMsg.cpoDtlLineSubNum.getValue(), cpoDtlTMsg.cpoDtlLineSubNum.getValue())) {
                    cpoDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_ON_Y);
                    cpoDtlTMsg.dplyLineRefNum.clear();
                    // 2019/02/05 S21_NA#30149 Add Start
                    if (!isReqBaseCmptFlg) {
                        cpoDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
                    }
                    // 2019/02/05 S21_NA#30149 Add End
                } else {
                    cpoDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(cpoDtlTMsg.dplyLineRefNum, baseRefNum);
                }
            }
        }
    }

    private void setBaseCmptFlg4Return(NWZC150001PMsg pMsg, List<DS_CPO_RTRN_DTLTMsg> dsCpoRtnDtlTMsgList) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String mainMachCoaMdseTpCd = NWXC150001DsCheck.getCoaMdseTpCd(glblCmpyCd);

        DS_CPO_RTRN_DTLTMsg baseCmptDsCpoRtrnDtlTMsg = null;
        DS_CPO_RTRN_DTLTMsg machDsCpoRtrnDtlTMsg = null;
        DS_CPO_RTRN_DTLTMsg instDsCpoRtrnDtlTMsg = null;

        for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : dsCpoRtnDtlTMsgList) {
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, dsCpoRtrnDtlTMsg.mdseCd.getValue());
            if (S21StringUtil.isEquals(mainMachCoaMdseTpCd, mdseTMsg.coaMdseTpCd.getValue())) {
                machDsCpoRtrnDtlTMsg = dsCpoRtrnDtlTMsg;
                break;
            }
            if (instDsCpoRtrnDtlTMsg == null //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                instDsCpoRtrnDtlTMsg = dsCpoRtrnDtlTMsg;
            }
        }

        if (machDsCpoRtrnDtlTMsg != null) {
            baseCmptDsCpoRtrnDtlTMsg = machDsCpoRtrnDtlTMsg;
        } else if (instDsCpoRtrnDtlTMsg != null) {
            baseCmptDsCpoRtrnDtlTMsg = instDsCpoRtrnDtlTMsg;
        }

        String baseRefNum = "";
        if (baseCmptDsCpoRtrnDtlTMsg != null) {
            baseRefNum = baseCmptDsCpoRtrnDtlTMsg.dsOrdPosnNum.getValue() + "." + String.valueOf(baseCmptDsCpoRtrnDtlTMsg.dsCpoLineNum.getValueInt());
            if (ZYPCommonFunc.hasValue(baseCmptDsCpoRtrnDtlTMsg.dsCpoLineSubNum)) {
                baseRefNum = baseRefNum + "." + String.valueOf(baseCmptDsCpoRtrnDtlTMsg.dsCpoLineSubNum.getValueInt());
            }
        }

        for (DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlTMsg : dsCpoRtnDtlTMsgList) {
            if (baseCmptDsCpoRtrnDtlTMsg == null) {
                dsCpoRtrnDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
                dsCpoRtrnDtlTMsg.dplyLineRefNum.clear();
            } else {
                if (S21StringUtil.isEquals(baseCmptDsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue(), dsCpoRtrnDtlTMsg.dsCpoRtrnLineNum.getValue()) //
                        && S21StringUtil.isEquals(baseCmptDsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue(), dsCpoRtrnDtlTMsg.dsCpoRtrnLineSubNum.getValue())) {
                    dsCpoRtrnDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_ON_Y);
                    dsCpoRtrnDtlTMsg.dplyLineRefNum.clear();
                } else {
                    dsCpoRtrnDtlTMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlTMsg.dplyLineRefNum, baseRefNum);
                }
            }
        }
    }

    private int getOrdCustUomQty(String glblCmpyCd, String mdseCd, String custUomCd, int ordQty) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return 0;
        }
        MDSE_STORE_PKGTMsg mdseStorePkgQueryKey = new MDSE_STORE_PKGTMsg();
        mdseStorePkgQueryKey.glblCmpyCd.setValue(glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseStorePkgQueryKey.pkgUomCd, custUomCd);
        mdseStorePkgQueryKey.mdseCd.setValue(mdseTMsg.mdseCd.getValue());

        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(mdseStorePkgQueryKey);

        if (mdseStorePkgTMsg != null) {
            return ordQty / mdseStorePkgTMsg.inEachQty.getValueInt();
        }

        ORD_TAKE_MDSETMsg ordTakeMdseQueryKey = new ORD_TAKE_MDSETMsg();
        ordTakeMdseQueryKey.glblCmpyCd.setValue(glblCmpyCd);
        ordTakeMdseQueryKey.mdseCd.setValue(mdseCd);

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseQueryKey);
        if (ordTakeMdseTMsg == null) {
            return -1;
        }
        mdseStorePkgQueryKey.mdseCd.setValue(ordTakeMdseTMsg.mdseCd.getValue());

        mdseStorePkgTMsg = (MDSE_STORE_PKGTMsg) S21CacheTBLAccessor.findByKey(mdseStorePkgQueryKey);

        if (mdseStorePkgTMsg != null) {
            return ordQty / mdseStorePkgTMsg.inEachQty.getValueInt();
        }
        return 0;
    }
    // 2018/09/05 S21_NA#26755 Add End
    // QC#50130 2019/05/15 Add Start
    private BigDecimal getSpecCondPrcPk(String glblCmpyCd, String prcCondTpCd) {
        if(!ZYPCommonFunc.hasValue(prcCondTpCd)){
            return null;
        }

        BigDecimal rtrnVal = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prcCondTpCd", prcCondTpCd);
        params.put("invLineCatgCd", INV_LINE_CATG.ITEM);
        return (BigDecimal) this.ssmClient.queryObject("getSpecCondPrcPk", params);
    }
    // QC#50130 2019/05/15 Add End
    // 2020/01/24 QC#54725 Mod Start
    public class SchedulingCommentsClobAccessor extends EZDSQLAccessor {
        /** CLOB_COL_NM */
        private static final String CLOB_COL_NM = "DELY_SCHD_CMNT_CLOD";

        /** AND */
        private static final String AND = "AND";

        /** dsCpoDelyInfoTMsg */
        final DS_CPO_DELY_INFOTMsg dsCpoDelyInfoTMsg;

        /** keyColumnList */
        final List<String>[] keyColumnList;

        /**
         * Constractor.
         * @param dsCpoDelyInfoTMsg
         */
        SchedulingCommentsClobAccessor(DS_CPO_DELY_INFOTMsg dsCpoDelyInfoTMsg) {
            this.dsCpoDelyInfoTMsg = dsCpoDelyInfoTMsg;
            this.keyColumnList = (List<String>[]) dsCpoDelyInfoTMsg.getKeyColumnList();
        }

        /**
         * get 'DELY_SCHD_CMNT_CLOD'.
         * @return DELY_SCHD_CMNT_CLOD
         */
        String getSql() {

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {

                con = getConnection();

                // --------------------------------------------------
                // SELECT
                // --------------------------------------------------
                final String selectSql = createClobSelectSQL();
                ps = con.prepareStatement(selectSql);
                for (int i = 0; i < keyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, dsCpoDelyInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
                }

                rs = ps.executeQuery();

                String sqlTxt = "";
                if (rs.next()) {
                    final Clob clob = rs.getClob(CLOB_COL_NM);
                    if (clob != null) {
                        int length = (int) clob.length();
                        sqlTxt = clob.getSubString(1, length);
                    }
                }

                return sqlTxt;

            } catch (SQLException e) {
                throw new S21AbendException(e);
            } finally {
                super.closeResource(con, ps, rs);
            }
        }

        /**
         * update 'DELY_SCHD_CMNT_CLOD'.
         * @param cmmtTxt DELY_SCHD_CMNT_CLOD
         * @return true/success, false/failure
         */
        boolean updateSql(String cmmtTxt) {

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {

                con = getConnection();

                // --------------------------------------------------
                // UPDATE (EMPTY_CLOB)
                // --------------------------------------------------
                final String updateSql = createClobEmptyUpdateSQL();
                ps = con.prepareStatement(updateSql);
                for (int i = 0; i < keyColumnList[1].size(); i++) {
                    getJDBCUtil().setParam(ps, i + 1, dsCpoDelyInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
                }

                final int resCnt = ps.executeUpdate();

                if (resCnt == 1) {

                    // --------------------------------------------------
                    // SELECT
                    // --------------------------------------------------
                    final String selectSql = createClobSelectSQL();
                    ps = con.prepareStatement(selectSql);
                    for (int i = 0; i < keyColumnList[1].size(); i++) {
                        getJDBCUtil().setParam(ps, i + 1, dsCpoDelyInfoTMsg.getValueData(keyColumnList[0].get(i), -1));
                    }
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        // write CLOB.
                        final Clob clob = rs.getClob(CLOB_COL_NM);
                        if (clob != null) {
                            clob.setString(1, cmmtTxt);
                        }
                    }
                }

                return true;

            } catch (SQLException e) {
                throw new S21AbendException(e);
            } finally {
                super.closeResource(con, ps, rs);
            }
        }

        private String createClobEmptyUpdateSQL() {

            final StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(dsCpoDelyInfoTMsg.getTableName().toUpperCase());
            sb.append(" SET ").append(CLOB_COL_NM).append(" = EMPTY_CLOB()");
            sb.append(" WHERE ");
            for (int i = 0; i < keyColumnList[1].size(); i++) {
                sb.append(" ").append(keyColumnList[1].get(i)).append(" = ? ").append(AND);
            }

            String sql = sb.toString();
            if (sql.endsWith(AND)) {
                sql = sql.substring(0, sql.length() - AND.length());
            }

            return sql;
        }

        private String createClobSelectSQL() {

            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(CLOB_COL_NM);
            sb.append(" FROM ").append(dsCpoDelyInfoTMsg.getTableName().toUpperCase());
            sb.append(" WHERE ");
            for (int i = 0; i < keyColumnList[1].size(); i++) {
                sb.append(" ").append(keyColumnList[1].get(i)).append(" = ? ").append(AND);
            }

            // SQL
            String sql = sb.toString();
            if (sql.endsWith(AND)) {
                sql = sql.substring(0, sql.length() - AND.length());
            }

            return sql;
        }
    }
    // 2020/01/24 QC#54725 Mod End
}
