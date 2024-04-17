/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC001001;

import static com.canon.cusa.s21.api.NSZ.NSZC001001.constant.NSZC001001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CPOTMsg;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_MDLTMsg;
import business.db.MDSETMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MDL_NMTMsg;
import business.db.MTR_LBTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsgArray;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MACH_MSTR_STSTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.db.SVC_NON_PRF_TECHTMsg;
import business.db.SVC_NON_PRF_TECHTMsgArray;
import business.db.SVC_PHYS_MTRTMsg;
import business.db.SVC_PHYS_MTRTMsgArray;
import business.db.SVC_PRVD_PTYTMsg;
import business.db.SWHTMsg;
import business.db.SVC_WTY_TPTMsg;
import business.db.TRTY_RULETMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NSZC001001_xxCmptMachListPMsg;
import business.parts.NSZC001001_xxNonPrfTechListPMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCd;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCdBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACTL_CNT_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADD_CHRG_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_SVC_PLN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRVD_PTY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_LOGIC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_COPY_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Machine Master Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/24/2013   SRA             N.Otsuji        Create
 * 10/16/2013   SRA             N.Otsuji        Update          QC#2713
 * 10/01/2015   Hitachi         T.Tsuchida      Update          NA#CSA
 * 11/05/2015   Hitachi         T.Tomita        Update          QC#430
 * 11/06/2015   Hitachi         T.Tomita        Update          QC#468, QC#474
 * 11/09/2015   Hitachi         T.Tomita        Update          QC#500
 * 11/10/2015   Hitachi         T.Tomita        Update          QC#527
 * 11/12/2015   Hitachi         T.Tomita        Update          QC#526
 * 11/16/2015   Hitachi         T.Tomita        Update          QC#647
 * 11/25/2015   Hitachi         T.Tomita        Update          QC#970
 * 2015/12/07   Hitachi         K.Yamada        Update          CSA QC#1548
 * 2015/12/09   Hitachi         T.Tomita        Update          CSA QC#951, QC#953
 * 2015/12/10   Hitachi         T.Tomita        Update          CSA QC#1795
 * 2015/12/11   Hitachi         T.Tomita        Update          CSA QC#1799, 1877, 1878
 * 2015/12/11   Hitachi         T.Tomita        Update          CSA QC#1800
 * 2016/01/04   Hitachi         T.Tomita        Update          CSA QC#2113, 2170
 * 2016/01/05   Hitachi         T.Tomita        Update          CSA QC#2169
 * 2016/02/09   Hitachi         T.Tomita        Update          CSA QC#1801
 * 2016/04/13   Hitachi         M.Gotou         Update          CSA QC#4903
 * 2016/04/19   Hitachi         T.Iwamoto       Update          CSA QC#7004
 * 2016/04/26   Hitachi         T.Tomita        Update          CSA QC#4890
 * 2016/05/10   Hitachi         T.Tomita        Update          CSA QC#6142
 * 2016/05/13   Hitachi         T.Tomita        Update          CSA QC#4578
 * 2016/05/17   Hitachi         T.Tomita        Update          CSA QC#7076, 7759
 * 2016/06/01   Hitachi         T.Tomita        Update          CSA QC#9317
 * 2016/06/02   Hitachi         T.Tomita        Update          CSA QC#6142
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          CSA QC#9591
 * 2016/06/28   Hitachi         T.Tomita        Update          CSA QC#10830
 * 2016/06/29   Hitachi         T.Tomita        Update          CSA QC#11058
 * 2016/06/30   Hitachi         T.Tomita        Update          CSA QC#10462
 * 2016/07/04   Hitachi         T.Tomita        Update          CSA QC#10474
 * 2016/07/06   Hitachi         T.Tomita        Update          CSA QC#11470
 * 2016/07/11   Hitachi         T.Tomita        Update          CSA QC#446
 * 2016/07/25   Hitachi         A.Kohinata      Update          CSA QC#6951
 * 2016/07/29   Hitachi         T.Tomita        Update          CSA QC#12003, 12510, 12642
 * 2016/08/03   Hitachi         T.Tomita        Update          QC#12230
 * 2016/08/24   Hitachi         T.Tomita        Update          QC#13532
 * 2016/08/29   Hitachi         T.Tomita        Update          QC#10568
 * 2016/09/05   Hitachi         T.Tomita        Update          QC#12471
 * 2016/09/07   Hitachi         T.Tomita        Update          QC#10568
 * 2016/09/08   Hitachi         T.Tomita        Update          QC#13902
 * 2016/09/13   Hitachi         T.Tomita        Update          QC#14517
 * 2016/09/23   Hitachi         K.Yamada        Update          QC#13686
 * 2016/09/28   Hitachi         K.Yamada        Update          CSA QC#14674
 * 2016/10/12   Hitachi         T.Tomita        Update          CSA QC#13981
 * 2016/10/13   Hitachi         T.Tomita        Update          CSA QC#14734
 * 2016/10/13   Hitachi         T.Tomita        Update          CSA QC#14868
 * 2016/10/19   Hitachi         T.Tomita        Update          CSA QC#14868
 * 2016/11/24   Hitachi         N.Arai          Update          CSA QC#15829
 * 2016/12/14   Fujitsu         T.Yoshida       Update          CSA QC#16416
 * 2016/12/16   Hitachi         Y.Takeno        Update          CSA QC#16217
 * 2016/12/22   Hitachi         K.Kojima        Update          QC#16600
 * 2017/01/05   Hitachi         K.Kitachi       Update          QC#16848
 * 2017/01/11   Hitachi         T.Mizuki        Update          QC#16850
 * 2017/01/11   Hitachi         N.Arai          Update          QC#15829-2
 * 2017/01/12   Hitachi         K.Kitachi       Update          QC#16962
 * 2017/02/14   Hitachi         K.Kitachi       Update          QC#17309
 * 2017/03/22   Hitachi         K.Kitachi       Update          QC#15679
 * 2017/05/15   Hitachi         K.Kitachi       Update          QC#18343
 * 2017/06/05   Hitachi         Y.Osawa         Update          QC#18652
 * 2017/07/07   Hitachi         K.Kojima        Update          QC#19660
 * 2017/08/28   Hitachi         K.Kitachi       Update          QC#18476
 * 2017/09/04   Hitachi         T.Kanasaka      Update          QC#15134
 * 2017/09/14   Fujitsu         S.Fujita        Update          QC#18534
 * 2017/10/03   CITS            M.Naito         Update          QC#21292
 * 2017/10/10   Hitachi         T.Tomita        Update          QC#21292
 * 2017/10/16   Hitachi         M.Kidokoro      Update          QC#20246
 * 2017/10/17   Hitachi         T.Tomita        Update          QC#21798
 * 2017/10/23   Hitachi         K.Kitachi       Update          QC#21796
 * 2017/10/24   Hitachi         T.Tomita        Update          QC#21550
 * 2017/11/20   Hitachi         K.Ochiai        Update          QC#21698
 * 2017/11/20   Hitachi         K.Ochiai        Update          QC#21700
 * 2018/01/09   Hitachi         M.Kidokoro      Update          QC#20635
 * 2018/01/26   Hitachi         U.Kim           Update          QC#23310
 * 2018/02/07   Hitachi         M.Kidokoro      Update          QC#23190
 * 2018/02/14   Hitachi         M.Kidokoro      Update          QC#24143
 * 2018/02/27   CITS            M.Naito         Update          QC#24047
 * 2018/02/27   Hitachi         T.Tomita        Update          QC#24362
 * 2018/03/02   Hitachi         T.Tomita        Update          QC#23425
 * 2018/03/14   Hitachi         T.Tomita        Update          QC#23427
 * 2018/04/04   Hitachi         U.Kim           Update          QC#23559(Sol358)
 * 2018/05/08   Hitachi         K.Kitachi       Update          QC#23604
 * 2018/05/21   Hitachi         T.Tomita        Update          QC#23433
 * 2018/06/04   Hitachi         T.Tomita        Update          QC#26412
 * 2018/06/07   Hitachi         T.Tomita        Update          QC#26482
 * 2018/06/13   Hitachi         T.Tomita        Update          QC#23428
 * 2018/06/19   Hitachi         T.Tomita        Update          QC#26618
 * 2018/07/02   Hitachi         K.Kitachi       Update          QC#27023
 * 2018/07/11   Hitachi         T.Tomita        Update          QC#19958
 * 2018/08/05   Hitachi         T.Tomita        Update          QC#27144
 * 2018/08/09   CITS            T.Wada          Update          QC#27595
 * 2018/08/22   Hitachi         T.Tomita        Update          QC#18190
 * 2018/08/23   Hitachi         T.Tomita        Update          QC#27126
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/12/17   Hitachi         T.Tomita        Update          QC#28383
 * 2019/01/11   Hitachi         K.Morita        Update          QC#26928
 * 2019/03/18   Hitachi         K.Fujimoto      Update          QC#30793
 * 2019/05/07   Hitachi         K.Kim           Update          QC#50119
 * 2019/05/20   Hitachi         K.Kim           Update          QC#50119
 * 2019/07/10   Hitachi         K.Kitachi       Update          QC#51273
 * 2019/11/06   Hitachi         A.Kohinata      Update          QC#54362
 * 2019/11/14   Hitachi         K.Kitachi       Update          QC#53575,54327
 * 2020/03/03   CITS            T.Wada          Update          QC#55913
 * 2020/03/30   Hitachi         K.Kitachi       Update          QC#56206
 * 2020/09/10   CITS            T.Wada          Update          QC#56909
 * 2020/11/19   CITS            R.Shimamoto     Update          QC#57983
 * 2021/01/21   CITS            T.Wada          Update          QC#59486
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/07/13   Hitachi         N.Takatsu       Update          QC#60023
 * 2023/04/26   CITS            L.Borrega       Update          QC#61388
 * 2023/07/31   Hitachi         S.Moriaia       Update          QC#61632
 * 2023/08/23   CITS            R.Jin           Update          QC#61808
 * 2023/10/02   Hitachi         T.Kuroda        Update          QC#61265
 * 2023/10/06   Hitachi         K.Watanabe      Update          QC#54186
 * 2023/12/19   Hitachi         T.Fukuta        Update          CSA-QC#61841
 * 2024/02/14   CITS            R.Tamura        Update          CSA-QC#63393
 * 2024/04/04   CITS            T.Aizawa        Update          QC#54186
 * </pre>
 */
public class NSZC001001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatType = null;

    /** SSM Batch Client */
    private final S21SsmBatchClient ssmBatClnt;

    /** Login User Id */
    private String loginUsrId = null;

    /** Model Id */
    private BigDecimal mdlId = null;

    // add start 2016/07/11 CSA Defect#446
    /** Pre-update SVC_MACH_MSTR */
    private SVC_MACH_MSTRTMsg preSvcMachMstrTMsg = null;

    /** DS_CONTR_CTRL_STS_CD List */
    private List<String> dsContrCtrlStsCdList = null;

    // START 2017/09/14 S.Fujita [QC#18534,MOD]
//    /** SVC_PGM_ST_WTY_NEW */
//    private String SVC_PGM_ST_WTY_NEW = null;
//
//    /** SVC_PGM_ST_WTY_USED */
//    private String SVC_PGM_ST_WTY_USED = null;

    /** SVC_PGM_ST_WTY */
    private String SVC_PGM_ST_WTY = null;
    // END 2017/09/14 S.Fujita [QC#18534,MOD]

    // add end 2016/07/11 CSA Defect#446

    // START 2017/03/22 K.Kitachi [QC#15679, ADD]
    /** Dummy Serial Flag */
    private String dummySerFlg = ZYPConstant.FLG_OFF_N;
    // END 2017/03/22 K.Kitachi [QC#15679, ADD]

    /**
     * Constructor
     */
    public NSZC001001() {
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * Machine Master Update API
     * 
     * <pre>
     * NSZC001001 api = new NSZC001001();
     * List<{@link NSZC001001PMsg}> pMsgList = new ArrayList<{@link NSZC001001PMsg}>();
     * for (SomeDataLikeSMsg data : dataList) {
     *     pMsgList.add(buildPMsg(data));
     * }
     * api.execute(pMsgList, onBatTp);
     * </pre>
     * 
     * is equivalent to
     * 
     * <pre>
     * NSZC001001 api = new NSZC001001();
     * for (SomeDataLikeSMsg data : dataList) {
     *     {@link NSZC001001PMsg} pMsg = buildPMsg(data);
     *     api.execute(pMsgList, onBatTp);
     * }
     * </pre>
     * @see
     * {@link NSZC001001#execute(NSZC001001PMsg, com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)}
     * @param pMsgList List of {@link NSZC001001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(final List<NSZC001001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NSZC001001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * Machine Master Update API
     * @param pMsg {@link NSZC001001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(final NSZC001001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        init(pMsg, onBatTp);
        execute();
        msgMap.flush();
    }

    private void execute() {
        // mod start 2016/07/11 CSA Defect#446
        validatePMsg();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();
        ProcessMode event = findEvent(pMsg);
        if (event == null) {
            if (!S21ApiUtil.isXxMsgId(pMsg)) {
                msgMap.addXxMsgId(NSZM0009E);
            }
            return;
        }

        if (hasValue(pMsg.svcMachMstrPk)) {
            this.preSvcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        }

        // Service Machine Master
        mainProc(event);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // QC#60023 Add start
        territoryRuleAdd(pMsg);
        // QC#60023 Add end
        
        // START 2017/02/14 K.Kitachi [QC#17309, MOD]
        // START 2018/08/23 K.Kitachi [QC#27773, MOD]
//        if (!pMsg.manCratFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.manCratFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(pMsg.wtyAutoCratFlg.getValue())) {
        // END 2018/08/23 K.Kitachi [QC#27773, MOD]
            // Warranty Contract
            warrantyContrProc(event);
        }
        // END 2017/02/14 K.Kitachi [QC#17309, MOD]
        // mod end 2016/07/11 CSA Defect#446
    }

    private void init(NSZC001001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        onBatType = onBatTp;

        S21UserProfileServiceFactory profileService = S21UserProfileServiceFactory.getInstance();

        if (profileService != null) {

            S21UserProfileService profile = profileService.getService();

            if (profile != null) {

                S21UserInfo userInfo = profile.getContextUserInfo();

                if (userInfo != null) {

                    loginUsrId = userInfo.getUserId();
                }
            }
        }

        this.dsContrCtrlStsCdList = new ArrayList<String>();
        this.dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.TERMINATED);
        this.dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.EXPIRED);
        this.dsContrCtrlStsCdList.add(DS_CONTR_CTRL_STS.CANCELLED);
    }

    // add start 2016/07/11 CSA Defect#446
    private void mainProc(ProcessMode event) {

        switch (event) {
            case INSERT_WAREHOUSE:
                exeInsWH();
                break;
            case INSERT_MACHINE_IN_FIELD:
                exeInsMIF();
                break;
            case UPDATE_WAREHOUSE:
                exeUpdWH();
                break;
            case UPDATE_MACHINE_IN_FIELD:
                exeUpdMIF();
                break;
            case UPDATE_TERMINATION:
                exeUpdTrmn();
                break;
            case SHIPMENT:
                exeShip();
                break;
            case INSTALLATION:
                exeIstl();
                break;
            case RMA:
                exeRMA();
                break;
            case RMA_CANCEL:
                exeRMACanc();
                break;
            case RWS:
                exeRWS();
                break;
            case DISPOSAL:
                exeDispose();
                break;
            case TRANSFER_OUT:
                exeTrnsfOut();
                break;
            case TRANSFER_IN:
                exeTrnsfIn();
                break;
            case SERVICE_UPDATE:
                exeSvcUpd();
                break;
            case CONVERSION_ORDER:
                exeConvOrd();
                break;
            case UPDATE_ATTRIBUTE:
                exeUpdAttr();
                break;
            case UPDATE_LOCATION:
                exeUpdLoc();
                break;
            case ALLOCATION_ON:
                exeAllocOn();
                break;
            case ALLOCATION_OFF:
                exeAllocOff();
                break;
            case SERVICE_EXCHANGE:
                exeSvcExch();
                break;
            case UPDATE_INVENTORY:
                exeUpdInvty();
                break;
            case ITEM_CHANGE:
                exeItemChng();
                break;
            // START 2019/11/14 K.Kitachi [QC#54327, ADD]
            case REMOVE_CONFIG:
                exeRmvConfig();
                break;
            // END 2019/11/14 K.Kitachi [QC#54327, ADD]
            default:
                msgMap.addXxMsgId(NSZM0009E);
                break;
        }
    }
    // add end 2016/07/11 CSA Defect#446

    private void mandatoryCheck(EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(messageId, null);
        }
    }

    private void vldMandatoryCheckForInsWH() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.mdseCd, NSZM0013E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.autoCratFlg, NSZM0189E);
        mandatoryCheck(pMsg.effFromDt, NSZM0696E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
        mandatoryCheck(pMsg.svcMachQty, NSZM0695E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForInsMIF() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.mdseCd, NSZM0013E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.autoCratFlg, NSZM0189E);
        mandatoryCheck(pMsg.effFromDt, NSZM0696E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
// del start 2016/12/16 CSA Defect#16217
//      mandatoryCheck(pMsg.svcMachTrxTpCd, NSZM0688E);
// del end   2016/12/16 CSA Defect#16217
        mandatoryCheck(pMsg.svcMachQty, NSZM0695E);
        mandatoryCheck(pMsg.ownrAcctNum, NSZM0689E);
        // del start 2016/05/13 CSA Defect#4578
//        mandatoryCheck(pMsg.ownrLocNum, NSZM0690E);
        // del end 2016/05/13 CSA Defect#4578
        mandatoryCheck(pMsg.billToAcctNum, NSZM0691E);
        mandatoryCheck(pMsg.billToLocNum, NSZM0692E);
        mandatoryCheck(pMsg.curLocAcctNum, NSZM0693E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForUpdWH() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.mdseCd, NSZM0013E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.effFromDt, NSZM0696E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
        mandatoryCheck(pMsg.svcMachQty, NSZM0695E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForUpdMIF() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.mdseCd, NSZM0013E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.effFromDt, NSZM0696E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
// del start 2016/12/16 CSA Defect#16217
//      mandatoryCheck(pMsg.svcMachTrxTpCd, NSZM0688E);
// del end   2016/12/16 CSA Defect#16217
        mandatoryCheck(pMsg.svcMachQty, NSZM0695E);
        mandatoryCheck(pMsg.ownrAcctNum, NSZM0689E);
        // del start 2016/05/13 CSA Defect#4578
//      mandatoryCheck(pMsg.ownrLocNum, NSZM0690E);
        // del end 2016/05/13 CSA Defect#4578
        mandatoryCheck(pMsg.billToAcctNum, NSZM0691E);
        mandatoryCheck(pMsg.billToLocNum, NSZM0692E);
        mandatoryCheck(pMsg.curLocAcctNum, NSZM0693E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForUpdTrmn() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.effThruDt, NSZM0697E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
        mandatoryCheck(pMsg.svcMachQty, NSZM0695E);
    }

    private void vldMandatoryCheckForShip() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachTpCd, NSZM0031E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.cpoOrdNum, NSZM0402E);
        mandatoryCheck(pMsg.cpoDtlLineNum, NSZM0403E);
        mandatoryCheck(pMsg.cpoDtlLineSubNum, NSZM0404E);
//      mandatoryCheck(pMsg.shpgPlnNum, NSZM0009E); // QC#16416 Del
        mandatoryCheck(pMsg.shpgPlnNum, NSZM1095E); // QC#16416 Add
        // del start 2016/07/29 CSA Defect#12003
        // soNum and soSlpNum doesn't not exists in Intangible Inventory
//        mandatoryCheck(pMsg.soNum, NSZM0027E);
//        mandatoryCheck(pMsg.soSlpNum, NSZM0685E);
        // del end 2016/07/29 CSA Defect#12003
        mandatoryCheck(pMsg.dsOrdTpCd, NSZM0371E);
        // START 2015/12/11 T.Tomita [QC#1878, DEL]
        // mandatoryCheck(pMsg.dsOrdRsnCd, NSZM0372E);
        // END 2015/12/11 T.Tomita [QC#1878, DEL]
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
// del start 2016/12/16 CSA Defect#16217
//      mandatoryCheck(pMsg.svcMachTrxTpCd, NSZM0688E);
// del end 2016/12/16 CSA Defect#16217
        mandatoryCheck(pMsg.ownrAcctNum, NSZM0689E);
        // del start 2016/05/13 CSA Defect#4578
//      mandatoryCheck(pMsg.ownrLocNum, NSZM0690E);
        // del end 2016/05/13 CSA Defect#4578
        mandatoryCheck(pMsg.billToAcctNum, NSZM0691E);
        mandatoryCheck(pMsg.billToLocNum, NSZM0692E);
        mandatoryCheck(pMsg.curLocAcctNum, NSZM0693E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForIstl() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
    }

    private void vldMandatoryCheckForRMA() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
    }

    private void vldMandatoryCheckForRMACanc() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
    }

    private void vldMandatoryCheckForRWS() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
    }

    private void vldMandatoryCheckForDispose() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
    }

    private void vldMandatoryCheckForTrnsfOut() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForTrnsfIn() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.mdseCd, NSZM0013E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForSvcUpd() {

    }

    private void vldMandatoryCheckForConvOrd() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.cpoOrdNum, NSZM0402E);
        mandatoryCheck(pMsg.cpoDtlLineNum, NSZM0403E);
        mandatoryCheck(pMsg.cpoDtlLineSubNum, NSZM0404E);
        mandatoryCheck(pMsg.dsOrdTpCd, NSZM0371E);
        // START 2015/12/11 T.Tomita [QC#1878, DEL]
        // mandatoryCheck(pMsg.dsOrdRsnCd, NSZM0372E);
        // END 2015/12/11 T.Tomita [QC#1878, DEL]
        mandatoryCheck(pMsg.ownrAcctNum, NSZM0689E);
        // del start 2016/05/13 CSA Defect#4578
//      mandatoryCheck(pMsg.ownrLocNum, NSZM0690E);
        // del end 2016/05/13 CSA Defect#4578
    }

    private void vldMandatoryCheckForUpdAttr() {

    }

    private void vldMandatoryCheckForUpdLoc() {

    }

    private void vldMandatoryCheckForAllocOn() {

    }

    private void vldMandatoryCheckForAllocOff() {

    }

    private void vldMandatoryCheckForSvcExch() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.svcMachTpCd, NSZM0031E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.cpoOrdNum, NSZM0402E);
        mandatoryCheck(pMsg.cpoDtlLineNum, NSZM0403E);
        mandatoryCheck(pMsg.cpoDtlLineSubNum, NSZM0404E);
//      mandatoryCheck(pMsg.shpgPlnNum, NSZM0009E); // QC#16416 Del
        mandatoryCheck(pMsg.shpgPlnNum, NSZM1095E); // QC#16416 Add
        // START 2024/02/14 R.Tamura [CSA-QC#63393,DEL]
        // mandatoryCheck(pMsg.soNum, NSZM0027E);
        // mandatoryCheck(pMsg.soSlpNum, NSZM0685E);
        // END 2024/02/14 R.Tamura [CSA-QC#63393,DEL]
        mandatoryCheck(pMsg.dsOrdTpCd, NSZM0371E);
        // START 2015/12/11 T.Tomita [QC#1878, DEL]
        // mandatoryCheck(pMsg.dsOrdRsnCd, NSZM0372E);
        // END 2015/12/11 T.Tomita [QC#1878, DEL]
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
// del start 2016/12/16 CSA Defect#16217
//      mandatoryCheck(pMsg.svcMachTrxTpCd, NSZM0688E);
// del end 2016/12/16 CSA Defect#16217
        mandatoryCheck(pMsg.ownrAcctNum, NSZM0689E);
        // del start 2016/05/13 CSA Defect#4578
//      mandatoryCheck(pMsg.ownrLocNum, NSZM0690E);
        // del end 2016/05/13 CSA Defect#4578
        mandatoryCheck(pMsg.billToAcctNum, NSZM0691E);
        mandatoryCheck(pMsg.billToLocNum, NSZM0692E);
        mandatoryCheck(pMsg.curLocAcctNum, NSZM0693E);
        mandatoryCheck(pMsg.curLocNum, NSZM0694E);
    }

    private void vldMandatoryCheckForUpdInvty() {

    }

    private void vldMandatoryCheckForItemChng() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        mandatoryCheck(pMsg.mdseCd, NSZM0013E);
        mandatoryCheck(pMsg.svcMachMstrStsCd, NSZM0014E);
        mandatoryCheck(pMsg.svcMachUsgStsCd, NSZM0686E);
    }

    // START 2019/11/14 K.Kitachi [QC#54327, ADD]
    private void vldMandatoryCheckForRmvConfig() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            mandatoryCheck(pMsg.xxCmptMachList.no(i).svcMachMstrPk, NSZM0074E);
            mandatoryCheck(pMsg.xxCmptMachList.no(i).effThruDt, NSZM0697E);
        }
    }
    // END 2019/11/14 K.Kitachi [QC#54327, ADD]

    private void exeInsWH() {

        vldMandatoryCheckForInsWH();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        setDummySerNum(pMsg);

        createSvcMachMstrByInsWH(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        // Del Start 2018/07/11 QC#19958
        // updateSvcConfigMstrDtl(pMsg);
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // Del end 2018/07/11 QC#19958
        // add end 2016/04/13 CSA Defect#4903

        if (pMsg.xxCmptMachList.getValidCount() == 0) {
            return;
        }

        // START 2016/02/09 T.Tomita [QC#1801, ADD]
        if (hasValue(pMsg.shipDt)) {
            return;
        }
        // END 2016/02/09 T.Tomita [QC#1801, ADD]

        createAndUpdateSvcConfigMstr(pMsg, pMsg.svcConfigMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Add Start 2018/07/11 QC#19958
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add End 2018/07/11 QC#19958
        // START 2015/12/11 T.Tomita [QC#1799, MOD]
        // START 2015/11/09 T.Tomita [QC#500, MOD]
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            BigDecimal newMdlId = getMdlId();
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // START 2018/02/07 M.Kidokoro [QC#23190,MOD]
//            createAndUpdateSvcPhsMtr(pMsg, newMdlId);
            SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, pMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            SVC_MACH_MSTRTMsg updTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
            createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
            // END 2018/02/07 M.Kidokoro [QC#23190,MOD]
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // END 2015/11/09 T.Tomita [QC#500, MOD]
        // END 2015/12/11 T.Tomita [QC#1799, MOD]
    }

    private void exeInsMIF() {

        vldMandatoryCheckForInsMIF();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        setDummySerNum(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // START 2015/11/06 T.Tomita [QC#468, DEL]
//        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
//        if (exstTMsg == null) {
//            msgMap.addXxMsgId(NSZM0006E);
//            return;
//        }
        // END 2015/11/06 T.Tomita [QC#468, DEL]

        createSvcMachMstrByInsMIF(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // add start 2016/04/13 CSA Defect#4903
        // Del Start 2018/07/11 QC#19958
        // updateSvcConfigMstrDtl(pMsg);
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // Del End 2018/07/11 QC#19958
        // add end 2016/04/13 CSA Defect#4903

        if (pMsg.xxCmptMachList.getValidCount() == 0) {
            return;
        }

        createAndUpdateSvcConfigMstr(pMsg, pMsg.svcConfigMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Add Start 2018/07/11 QC#19958
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add End 2018/07/11 QC#19958
        // START 2015/12/11 T.Tomita [QC#1799, MOD]
        // START 2015/11/09 T.Tomita [QC#500, MOD]
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            BigDecimal newMdlId = getMdlId();
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // START 2018/02/07 M.Kidokoro [QC#23190,MOD]
//            createAndUpdateSvcPhsMtr(pMsg, newMdlId);
            SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, pMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            SVC_MACH_MSTRTMsg updTMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
            createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
            // END 2018/02/07 M.Kidokoro [QC#23190,MOD]
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // END 2015/11/09 T.Tomita [QC#500, MOD]
        // END 2015/12/11 T.Tomita [QC#1799, MOD]
    }

    private void exeUpdWH() {

        vldMandatoryCheckForUpdWH();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByUpdWH(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        // Del Start 2018/07/11 QC#19958
        // updateSvcConfigMstrDtl(pMsg);
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // Del End 2018/07/11 QC#19958
        // add end 2016/04/13 CSA Defect#4903

        // mod start 2016/08/24 CSA Defect#13532
        if (pMsg.xxCmptMachList.getValidCount() > 0) {
            createAndUpdateSvcConfigMstr(pMsg, pMsg.svcConfigMstrPk.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // START 2015/12/11 T.Tomita [QC#1799, MOD]
            // START 2015/11/09 T.Tomita [QC#500, MOD]
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                BigDecimal newMdlId = getMdlId();
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
            // END 2015/11/09 T.Tomita [QC#500, MOD]
            // END 2015/12/11 T.Tomita [QC#1799, MOD]
        }
        // mod end 2016/08/24 CSA Defect#13532

        // Add Start 2018/07/11 QC#19958
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add End 2018/07/11 QC#19958
        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeUpdMIF() {

        vldMandatoryCheckForUpdMIF();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByUpdMIF(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        
        // START 2023/08/23 R.Jin [QC#61808, ADD]
        updateSvcMachMstrForAccsoryPrnSerNum(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // START 2023/08/23 R.Jin [QC#61808, ADD]
        
        // add start 2016/04/13 CSA Defect#4903
        // Del Start 2018/07/11 QC#19958
        // updateSvcConfigMstrDtl(pMsg);
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // Del End 2018/07/11 QC#19958
        // add end 2016/04/13 CSA Defect#4903
        // mod start 2016/08/24 CSA Defect#13532
        if (pMsg.xxCmptMachList.getValidCount() > 0) {
            createAndUpdateSvcConfigMstr(pMsg, pMsg.svcConfigMstrPk.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // START 2015/12/11 T.Tomita [QC#1799, MOD]
            // START 2015/11/09 T.Tomita [QC#500, MOD]
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                BigDecimal newMdlId = getMdlId();
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
            // END 2015/11/09 T.Tomita [QC#500, MOD]
            // END 2015/12/11 T.Tomita [QC#1799, MOD]
        }
        // mod end 2016/08/24 CSA Defect#13532

        // Add Start 2018/07/11 QC#19958
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add End 2018/07/11 QC#19958
        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeUpdTrmn() {

        vldMandatoryCheckForUpdTrmn();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByUpdTrmn(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (hasValue(pMsg.svcConfigMstrPk)) {
            BigDecimal svcConfigMstrPk = findSvcConfigMstrForUpdTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.svcConfigMstrPk.getValue());
            updateSvcMachMstrForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            updateSvcConfigMstrDtlForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // START 2018/02/27 M.Naito [QC#24047, MOD]
//            if (svcConfigMstrPk == null) {
            if (BigDecimal.ZERO.equals(svcConfigMstrPk)) {
            // END 2018/02/27 M.Naito [QC#24047, MOD]
                removeSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }
    }

    private void exeShip() {

        vldMandatoryCheckForShip();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByShip(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        // Del Start 2018/07/11 QC#19958
        // updateSvcConfigMstrDtl(pMsg);
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // Del End 2018/07/11 QC#19958
        // add end 2016/04/13 CSA Defect#4903

        // START 2015/12/10 T.Tomita [QC#1795, MOD]
        createAndUpdateSvcConfigMstr(pMsg, pMsg.svcConfigMstrPk.getValue());
        // END 2015/12/10 T.Tomita [QC#1795, MOD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Add Start 2018/07/11 QC#19958
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add End 2018/07/11 QC#19958
        // START 2015/12/11 T.Tomita [QC#1799, MOD]
        // START 2015/11/09 T.Tomita [QC#500, MOD]
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            BigDecimal newMdlId = getMdlId();
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // END 2015/11/09 T.Tomita [QC#500, MOD]
        // END 2015/12/11 T.Tomita [QC#1799, MOD]

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeIstl() {

        vldMandatoryCheckForIstl();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        // add start 2016/04/19 CSA Defect#7004
        SVC_MACH_MSTRTMsgArray tMsgArray = findSvcMachMstrBySvcConfigMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            // START 2017/01/11 N.Arai [QC#15829-2, MOD]
            // SVC_MACH_MSTRTMsg exstTMsg = tMsgArray.no(i);
            // SVC_MACH_MSTRTMsg updTMsg = tMsgArray.no(i);
            SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), (BigDecimal) tMsgArray.no(i).svcMachMstrPk.getValue());
            if (exstTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
            // START 2017/08/28 K.Kitachi [QC#18476, ADD]
            if (!exstTMsg.svcMachMstrStsCd.getValue().equals(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION)) {
                continue;
            }
            // END 2017/08/28 K.Kitachi [QC#18476, ADD]
            SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), (BigDecimal) tMsgArray.no(i).svcMachMstrPk.getValue());
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
            // END 2017/01/11 N.Arai [QC#15829-2, MOD]
            updateSvcMachMstrByIstl(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // START 2018/07/02 K.Kitachi [QC#27023, ADD]
            updateSvcConfigMstrDtlByIstl(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // END 2018/07/02 K.Kitachi [QC#27023, ADD]

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // add end 2016/04/19 CSA Defect#7004
        // mod start 2016/10/13 CSA Defect#14898
        List<Map<String, Object>> svcExchngOrdList = findSvcExchngOrd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        // mod end 2016/10/13 CSA Defect#14898
        // add start 2016/04/19 CSA Defect#7004
        if (svcExchngOrdList.size() == 0) {
            return;
        }
        // add end 2016/04/19 CSA Defect#7004

        BigDecimal dsContrIntfcBatSq = getMaxPlusOneSequence(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue());
        String newDsContrIntfcBatNum = getDsContrIntfcBatNum(dsContrIntfcBatSq, pMsg.slsDt.getValue());

        BigDecimal termDsContrIntfcBatSq = dsContrIntfcBatSq.add(BigDecimal.ONE);
        String termNewDsContrIntfcBatNum = getDsContrIntfcBatNum(termDsContrIntfcBatSq, pMsg.slsDt.getValue());
        SVC_MACH_MSTRTMsg origMainMachTMsg = null;
        // Add Start 2018/03/14 QC#23427
        List<SVC_MACH_MSTRTMsg> origAccMachTMsgList = new ArrayList<SVC_MACH_MSTRTMsg>();
        // Add End 2018/03/14 QC#23427
        List<SVC_MACH_MSTRTMsg> exchMachList = new ArrayList<SVC_MACH_MSTRTMsg>();
        String cloDt = ZYPDateUtil.addDays(pMsg.istlDt.getValue(), -1);

        BigDecimal svcMachMstrPk = null;
        // add start 2016/10/13 CSA Defect#14898
        BigDecimal svcConfigMstrPk = BigDecimal.ZERO;
        // add end 2016/10/13 CSA Defect#14898
        for (int i = 0; i < svcExchngOrdList.size(); i++) {
            svcMachMstrPk = (BigDecimal) svcExchngOrdList.get(i).get("SVC_MACH_MSTR_PK");
            // add start 2016/10/13 CSA Defect#14898
            svcConfigMstrPk = (BigDecimal) svcExchngOrdList.get(i).get("SVC_CONFIG_MSTR_PK");
            // add end 2016/10/13 CSA Defect#14898
            SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), svcMachMstrPk);
            if (exstTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), svcMachMstrPk);
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            // Mod Start 2018/03/14 QC#23427
            if (SVC_MACH_TP.MACHINE.equals((String) svcExchngOrdList.get(i).get("SVC_MACH_TP_CD"))) {
                origMainMachTMsg = updTMsg;
            } else {
                origAccMachTMsgList.add(updTMsg);
            }
            // Mod End 2018/03/14 QC#23427
            // mod start 2016/10/13 CSA Defect#14898
//            SVC_MACH_MSTRTMsgArray termSvcMachList = findSvcMachMstrBySvcConfigMstrPk(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
//            for (int j = 0; j < termSvcMachList.getValidCount(); j++) {
//                createContrIntfcForTrmn(pMsg, termSvcMachList.no(j), termDsContrIntfcBatSq, termNewDsContrIntfcBatNum, cloDt);
//                if (msgMap.getMsgMgr().isXxMsgId()) {
//                    return;
//                }
//            }
            createContrIntfcForTrmn(pMsg, updTMsg, termDsContrIntfcBatSq, termNewDsContrIntfcBatNum, cloDt);
            // mod end 2016/10/13 CSA Defect#14898
            // add end 2016/09/26 CSA Defect#14674

            // QC#56909 Add Start
            BigDecimal exstSvcConfigMstrPk = null;
            if (ZYPCommonFunc.hasValue(exstTMsg.svcConfigMstrPk)) {
                exstSvcConfigMstrPk = exstTMsg.svcConfigMstrPk.getValue();
            }
            if (ZYPCommonFunc.hasValue(exstSvcConfigMstrPk) && ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                if (!svcConfigMstrPk.equals(exstSvcConfigMstrPk)) {
                    rmvSvcConfigMstrDtlByIstlForSource(pMsg.glblCmpyCd.getValue(), svcMachMstrPk, svcConfigMstrPk, pMsg.slsDt.getValue());
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        return;
                    }
                    continue;
                }
            }
            // QC#56909 Add End

            updateSvcMachMstrByIstlForSource(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // add start 2016/10/13 CSA Defect#14898
            updateSvcConfigMstrDtlByIstlForSource(pMsg.glblCmpyCd.getValue(), svcMachMstrPk, pMsg.slsDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // add end 2016/10/13 CSA Defect#14898
        }

        // mod start 2016/10/13 CSA Defect#14898
        SVC_CONFIG_MSTR_DTLTMsgArray svcConfigMstrDtlTMsgArray = findSvcConfigMstrDtlForRgtnConfig(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
        for (int i = 0; i < svcConfigMstrDtlTMsgArray.getValidCount(); i++) {
            SVC_CONFIG_MSTR_DTLTMsg tMsg = svcConfigMstrDtlTMsgArray.no(i);
            SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue());
            if (exstTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrPk.getValue());
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            updateSvcMachMstrByIstlForDestination(pMsg, updTMsg, svcConfigMstrPk);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            exchMachList.add(updTMsg);

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        SVC_CONFIG_MSTRTMsg exstSvcConfigMstr = findSvcConfigMstrForUpdate(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
        BigDecimal exstMdlId = null;
        if (exstSvcConfigMstr != null) {
            exstMdlId = exstSvcConfigMstr.mdlId.getValue();
        }

        // START 2017/10/23 K.Kitachi [QC#21796, MOD]
        List<BigDecimal> svcMachMstrPkList = getSvcMachMstrPkBySvcConfigMstrPk(pMsg.glblCmpyCd.getValue(), exstSvcConfigMstr.svcConfigMstrPk.getValue());
        SVC_MACH_MSTRTMsg mainSvcMach = null;
        for (int i = 0; i < svcMachMstrPkList.size(); i++) {
            SVC_MACH_MSTRTMsg updateTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), svcMachMstrPkList.get(i));
            if (updateTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            // START 2016/11/24 N.Arai [QC#15829, MOD]
            SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), svcMachMstrPkList.get(i));
            if (exstTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }
            // END 2016/11/24 N.Arai [QC#15829, MOD]

            if (hasValue(updateTMsg.svcMachTpCd) && SVC_MACH_TP.MACHINE.equals(updateTMsg.svcMachTpCd.getValue())) {
                mainSvcMach = updateTMsg;
            }

            if (mainSvcMach == null) {
                continue;
            }

            if (!SVC_MACH_TP.MACHINE.equals(updateTMsg.svcMachTpCd.getValue())) {
                setValue(updateTMsg.svcConfigMstrPk, mainSvcMach.svcConfigMstrPk);
                setValue(updateTMsg.svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                BigDecimal svcMachSqNum = mainSvcMach.svcMachSqNum.getValue().add(BigDecimal.valueOf(i));
                setValue(updateTMsg.svcMachSqNum, svcMachSqNum);
            }

            setValue(updateTMsg.prntSerNum, mainSvcMach.serNum);
            // START 2018/05/08 K.Kitachi [QC#23604, ADD]
            if (origMainMachTMsg != null) {
                setValue(updateTMsg.ctrlFldTxt_01, origMainMachTMsg.ctrlFldTxt_01);
                setValue(updateTMsg.ctrlFldTxt_02, origMainMachTMsg.ctrlFldTxt_02);
                setValue(updateTMsg.ctrlFldTxt_03, origMainMachTMsg.ctrlFldTxt_03);
                setValue(updateTMsg.ctrlFldTxt_04, origMainMachTMsg.ctrlFldTxt_04);
                setValue(updateTMsg.ctrlFldTxt_05, origMainMachTMsg.ctrlFldTxt_05);
                setValue(updateTMsg.ctrlFldTxt_06, origMainMachTMsg.ctrlFldTxt_06);
            } else {
                setValue(updateTMsg.ctrlFldTxt_01, mainSvcMach.ctrlFldTxt_01);
                setValue(updateTMsg.ctrlFldTxt_02, mainSvcMach.ctrlFldTxt_02);
                setValue(updateTMsg.ctrlFldTxt_03, mainSvcMach.ctrlFldTxt_03);
                setValue(updateTMsg.ctrlFldTxt_04, mainSvcMach.ctrlFldTxt_04);
                setValue(updateTMsg.ctrlFldTxt_05, mainSvcMach.ctrlFldTxt_05);
                setValue(updateTMsg.ctrlFldTxt_06, mainSvcMach.ctrlFldTxt_06);
            }
            // END 2018/05/08 K.Kitachi [QC#23604, ADD]
            EZDTBLAccessor.update(updateTMsg);
            String rtrnCd = updateTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM0036E);
            }

            // START 2016/11/24 N.Arai [QC#15829, MOD]
            createSvcMachMstrTrk(pMsg, exstTMsg, updateTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // END 2016/11/24 N.Arai [QC#15829, MOD]

            SVC_CONFIG_MSTR_DTLTMsg inMsg = new SVC_CONFIG_MSTR_DTLTMsg();
            inMsg.setSQLID("006");
            inMsg.setConditionValue("glblCmpyCd01", updateTMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("svcConfigMstrPk01", updateTMsg.svcConfigMstrPk.getValue());
            inMsg.setConditionValue("svcMachMstrPk01", updateTMsg.svcMachMstrPk.getValue());
            SVC_CONFIG_MSTR_DTLTMsgArray dtlTMsgArray = (SVC_CONFIG_MSTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (dtlTMsgArray.getValidCount() > 0) {
                setValue(dtlTMsgArray.no(0).prntSvcMachMstrPk, mainSvcMach.svcMachMstrPk);
                setValue(dtlTMsgArray.no(0).mdlId, exstMdlId);
                if (!hasValue(dtlTMsgArray.no(0).istlDt)) {
                    setValue(dtlTMsgArray.no(0).istlDt, pMsg.slsDt);
                }
                setValue(dtlTMsgArray.no(0).rgtnConfigDt, pMsg.slsDt);
                EZDTBLAccessor.update(dtlTMsgArray.no(0));
                rtrnCd = dtlTMsgArray.no(0).getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                    msgMap.addXxMsgId(NSZM0717E);
                    return;
                }
            }
        }
        // Mod Start 2018/06/04 QC#26412
//        if (exstSvcConfigMstr != null && mainSvcMach != null) {
        if (exstSvcConfigMstr != null) {
            SVC_MACH_MSTRTMsgArray machList = findSvcMachMstrBySvcConfigMstrPk(exstSvcConfigMstr.glblCmpyCd.getValue(), exstSvcConfigMstr.svcConfigMstrPk.getValue());

            // Update SVC_CONFIG_MSTR
            if (mainSvcMach != null) {
                // Set new Main Machine
                setValue(exstSvcConfigMstr.svcMachMstrPk, mainSvcMach.svcMachMstrPk);
            }

            BigDecimal newMdlId = callSvcMdlApiByConfigPk(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), machList);
            boolean chngMdlFlg = false;
            if (hasValue(newMdlId) && !newMdlId.equals(exstSvcConfigMstr.mdlId.getValue())) {
                // Set new Model
                setValue(exstSvcConfigMstr.mdlId, newMdlId);
                exstMdlId = newMdlId;
                chngMdlFlg = true;
            }
            EZDTBLAccessor.update(exstSvcConfigMstr);

            // Update SVC_CONFIG_MSTR_DTL
            if (chngMdlFlg) {
                SVC_MACH_MSTRTMsg machTMsg;
                for (int i = 0; i < machList.getValidCount(); i++) {
                    machTMsg = machList.no(i);

                    List<Map<String, Object>> configDtlPkMapList = findSvcConfigMstrDtlForUpd(machTMsg.glblCmpyCd.getValue(), machTMsg.svcMachMstrPk.getValue(), machTMsg.svcConfigMstrPk.getValue());
                    if (configDtlPkMapList.size() == 0) {
                        continue;
                    }
                    BigDecimal svcConfigMstrDtlPk = (BigDecimal) configDtlPkMapList.get(0).get("SVC_CONFIG_MSTR_DTL_PK");
                    SVC_CONFIG_MSTR_DTLTMsg prmTMsg = new SVC_CONFIG_MSTR_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
                    ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, machTMsg.glblCmpyCd.getValue());
                    prmTMsg = (SVC_CONFIG_MSTR_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
                    setValue(prmTMsg.mdlId, newMdlId);
                    EZDTBLAccessor.update(prmTMsg);
                }
            }
        }
        // Mod End 2018/06/04 QC#26412
        // END 2017/10/23 K.Kitachi [QC#21796, MOD]

        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            createAndUpdateSvcPhsMtr(pMsg, exstMdlId);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // mod end 2016/10/13 CSA Defect#14898

        // add start 2016/09/27 CSA Defect#14674
        List<BigDecimal> procSvcMachMstrPkList = new ArrayList<BigDecimal>();
        SVC_MACH_MSTRTMsg origAccMachTMsg = null;
        for (SVC_MACH_MSTRTMsg exchMachMstr : exchMachList) {
            // Mod Start 2018/03/14 QC#23427
//            SVC_MACH_MSTRTMsgArray addContrSvcMachList = findSvcMachMstrBySvcConfigMstrPk(pMsg.glblCmpyCd.getValue(), exchMachMstr.svcConfigMstrPk.getValue());
//            for (int j = 0; j < addContrSvcMachList.getValidCount(); j++) {
//                SVC_MACH_MSTRTMsg addContrSvcMach = addContrSvcMachList.no(j);
//                if (SVC_MACH_TP.MACHINE.equals(addContrSvcMach.svcMachTpCd.getValue())) {
//                    createContrIntfcForAddToContr(pMsg, addContrSvcMach, dsContrIntfcBatSq, newDsContrIntfcBatNum, origMainMachTMsg, null, pMsg.istlDt.getValue());
//                } else {
//                    
//                    createContrIntfcForAddToContr(pMsg, addContrSvcMach, dsContrIntfcBatSq, newDsContrIntfcBatNum, addContrSvcMach, null, pMsg.istlDt.getValue());
//                }
//                if (msgMap.getMsgMgr().isXxMsgId()) {
//                    return;
//                }
//            }
            if (SVC_MACH_TP.MACHINE.equals(exchMachMstr.svcMachTpCd.getValue())) {
                createContrIntfcForAddToContr(pMsg, exchMachMstr, dsContrIntfcBatSq, newDsContrIntfcBatNum, origMainMachTMsg, null, pMsg.istlDt.getValue());
                procSvcMachMstrPkList.add(origMainMachTMsg.svcMachMstrPk.getValue());
            } else {
                origAccMachTMsg = getOrigAccMachTMsg(exchMachMstr, origAccMachTMsgList, procSvcMachMstrPkList);
                if (origAccMachTMsg != null) {
                    // Exchange Contract
                    createContrIntfcForAddToContr(pMsg, exchMachMstr, dsContrIntfcBatSq, newDsContrIntfcBatNum, origAccMachTMsg, null, pMsg.istlDt.getValue());
                    procSvcMachMstrPkList.add(origAccMachTMsg.svcMachMstrPk.getValue());
                }
            }
            // Mod End 2018/03/14 QC#23427
        }
        // add end 2016/09/27 CSA Defect#14674
    }

    private void exeRMA() {

        vldMandatoryCheckForRMA();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByRMA(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeRMACanc() {

        vldMandatoryCheckForRMACanc();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByRMACanc(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeRWS() {

        vldMandatoryCheckForRWS();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        // Mod Start 2018/12/17 QC#28383
        String cloDt;
        if (SVC_MACH_MSTR_STS.TERMINATED.equals(pMsg.svcMachMstrStsCd.getValue())) {
            updateSvcMachMstrByUpdTrmn(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            cloDt = updTMsg.effThruDt.getValue();

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            if (!trmnSvcConfigMstrDtl(pMsg)) {
                return;
            }
        } else {
            updateSvcMachMstrByRWS(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            cloDt = updTMsg.svcMachRmvDt.getValue();
            // add start 2016/04/13 CSA Defect#4903
            updateSvcConfigMstrDtl(pMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // add end 2016/04/13 CSA Defect#4903

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // Mod End 2018/12/16 QC#28383

        // add start 2016/09/26 CSA Defect#14674
        // Mod Start 2018/08/05 QC#27144
//        BigDecimal dsContrIntfcBatSq = getMaxPlusOneSequence(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue());
//        String newDsContrIntfcBatNum = getDsContrIntfcBatNum(dsContrIntfcBatSq, pMsg.slsDt.getValue());
        BigDecimal dsContrIntfcBatSq;
        String newDsContrIntfcBatNum;
        Map<String, Object> intfcMap = getExistsTrmdIntfc(updTMsg);
        if (intfcMap == null || intfcMap.isEmpty()) {
            dsContrIntfcBatSq = getMaxPlusOneSequence(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue());
            newDsContrIntfcBatNum = getDsContrIntfcBatNum(dsContrIntfcBatSq, pMsg.slsDt.getValue());
        } else {
            dsContrIntfcBatSq = (BigDecimal) intfcMap.get("DS_CONTR_INTFC_BAT_SQ");
            newDsContrIntfcBatNum = (String) intfcMap.get("DS_CONTR_INTFC_BAT_NUM");
        }
        // Mod End 2018/08/05 QC#27144
        // Mod Start 2018/12/17 QC#28383
        // createContrIntfcForTrmn(pMsg, updTMsg, dsContrIntfcBatSq, newDsContrIntfcBatNum, updTMsg.svcMachRmvDt.getValue());
        createContrIntfcForTrmn(pMsg, updTMsg, dsContrIntfcBatSq, newDsContrIntfcBatNum, cloDt);
        // Mod End 2018/12/17 QC#28383
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/09/26 CSA Defect#14674

        // QC#59486 Add Start
        boolean isServiceExchange = false;
        if (ZYPCommonFunc.hasValue(pMsg.rmaNum))  {
            isServiceExchange = isServiceExchange(pMsg.glblCmpyCd.getValue(), pMsg.rmaNum.getValue());
        }
        // QC#59486 Add End
        // QC#59486 Mod Start
        if (!isServiceExchange) {
            // START 2020/03/30 K.Kitachi [QC#56206, ADD]
            if (SVC_MACH_TP.MACHINE.equals(exstTMsg.svcMachTpCd.getValue())) {
                SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
                inMsg.setSQLID("003");
                inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                inMsg.setConditionValue("svcConfigMstrPk01", exstTMsg.svcConfigMstrPk.getValue());
                inMsg.setConditionValue("svcMachMstrPk01", exstTMsg.svcMachMstrPk.getValue());
                SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
                for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {
                    createContrIntfcForTrmn(pMsg, svcMachMstrTMsgArray.no(i), dsContrIntfcBatSq, newDsContrIntfcBatNum, cloDt);
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        return;
                    }
                }
            }
            // END 2020/03/30 K.Kitachi [QC#56206, ADD]
        }
        // QC#59486 Mod End

        // START 2017/10/16 M.Kidokoro [QC#20246, ADD]
        removeSvcMachCtacPsn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // END 2017/10/16 M.Kidokoro [QC#20246, ADD]
    }

    private void exeDispose() {

        vldMandatoryCheckForDispose();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByDispose(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (hasValue(pMsg.svcConfigMstrPk)) {
            BigDecimal svcConfigMstrPk = findSvcConfigMstrForUpdTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.svcConfigMstrPk.getValue());
            updateSvcMachMstrForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            updateSvcConfigMstrDtlForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            if (svcConfigMstrPk == null) {
                removeSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }
    }

    private void exeTrnsfOut() {

        vldMandatoryCheckForTrnsfOut();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByTrnsfOut(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        // Del Start 2018/07/11 QC#19958
        // updateSvcConfigMstrDtl(pMsg);
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // Del End 2018/07/11 QC#19958
        // add end 2016/04/13 CSA Defect#4903

        // add start 2016/07/29 CSA Defect#12642
        if (hasValue(pMsg.svcConfigMstrPk) && pMsg.xxCmptMachList.getValidCount() > 0) {
            createAndUpdateSvcConfigMstr(pMsg, pMsg.svcConfigMstrPk.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                BigDecimal newMdlId = getMdlId();
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }
        // add end 2016/07/29 CSA Defect#12642

        // Add Start 2018/07/11 QC#19958
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Add End 2018/07/11 QC#19958
        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeTrnsfIn() {

        vldMandatoryCheckForTrnsfIn();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByTrnsfIn(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeSvcUpd() {

        vldMandatoryCheckForSvcUpd();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrBySvcUpd(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeConvOrd() {

        vldMandatoryCheckForConvOrd();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByConvOrd(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeUpdAttr() {

        vldMandatoryCheckForUpdAttr();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (updTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        updateSvcMachMstrByUpdAttr(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        for (int i = 0; i < pMsg.xxNonPrfTechList.getValidCount(); i++) {
            if (!hasValue(pMsg.xxNonPrfTechList.no(i).dsCustNonPrfTechPk)) {
                SVC_NON_PRF_TECHTMsgArray svcNonPrfTechTMsgArray = findSvcNonPrfTechByTechCd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.xxNonPrfTechList.no(i).nonPrfTechCd.getValue());
                if (svcNonPrfTechTMsgArray.getValidCount() > 0) {
                    msgMap.addXxMsgId(NSZM0727E);
                    return;
                }
                createSvcNonPrfTech(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.xxNonPrfTechList.no(i));
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxNonPrfTechList.no(i).delFlg.getValue())) {
                    removeSvcNonPrfTech(pMsg.glblCmpyCd.getValue(), pMsg.xxNonPrfTechList.no(i).dsCustNonPrfTechPk.getValue());
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        return;
                    }
                    // START 2015/11/16 T.Tomita [QC#647, ADD]
                    continue;
                    // END 2015/11/16 T.Tomita [QC#647, ADD]
                }

                SVC_NON_PRF_TECHTMsg svcNonPrfTechTMsg = findSvcNonPrfTechForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.xxNonPrfTechList.no(i).dsCustNonPrfTechPk.getValue());
                if (svcNonPrfTechTMsg == null) {
                    msgMap.addXxMsgId(NSZM0727E);
                    return;
                }
                // START 2015/11/16 T.Tomita [QC#647, MOD]
                updateSvcNonPrfTech(pMsg.xxNonPrfTechList.no(i), svcNonPrfTechTMsg);
                // END 2015/11/16 T.Tomita [QC#647, MOD]
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }
    }

    private void exeUpdLoc() {

        vldMandatoryCheckForUpdLoc();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        updateSvcMachMstrByUpdLoc(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeAllocOn() {

        vldMandatoryCheckForAllocOn();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        updateSvcMachMstrByAllocOn(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeAllocOff() {

        vldMandatoryCheckForAllocOff();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        updateSvcMachMstrByAllocOff(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeSvcExch() {

        vldMandatoryCheckForSvcExch();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        updateSvcMachMstrBySvcExch(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // Mod Start 2018/03/02 QC#23425
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            BigDecimal newMdlId = callGetSvcMdlApiForSvcExch();
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            createAndUpdateSvcPhsMtr(pMsg, updTMsg, newMdlId);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // Mod End 2018/03/02 QC#23425

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        createSvcConfigMstrDtlForSvcExch(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeUpdInvty() {

        vldMandatoryCheckForUpdInvty();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (exstTMsg == null) {
            msgMap.addXxMsgId(NSZM0006E);
            return;
        }

        SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        updateSvcMachMstrByUpdInvty(pMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add start 2016/04/13 CSA Defect#4903
        updateSvcConfigMstrDtl(pMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // add end 2016/04/13 CSA Defect#4903

        createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void exeItemChng() {

        vldMandatoryCheckForItemChng();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        MDSETMsg mdseTMsg = findMdse(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
        if (mdseTMsg == null) {
            msgMap.addXxMsgId(NSZM0583E);
            return;
        }

        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            createSvcMachMstrByItemChng(pMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // add start 2016/04/13 CSA Defect#4903
            updateSvcConfigMstrDtl(pMsg);
            return;
            // add end 2016/04/13 CSA Defect#4903
        }

        // del start 2016/06/28 CSA Defect#10830
//        if (SVC_MACH_MSTR_STS.TERMINATED.equals(pMsg.svcMachMstrStsCd.getValue())) {
        // del end 2016/06/28 CSA Defect#10830
        BigDecimal svcMachMstrPk = findSvcMachMstrForCheckExstWH(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (hasValue(svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (exstTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            updateSvcMachMstrByItemChng(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // add start 2016/04/13 CSA Defect#4903
            updateSvcConfigMstrDtl(pMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // add end 2016/04/13 CSA Defect#4903

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        } else {
            svcMachMstrPk = findSvcMachMstrForItemChngForCheckDummySerNum(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue());
            if (svcMachMstrPk == null) {
                createSvcMachMstrByItemChng(pMsg);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            } else {
                setDummySerNum(pMsg);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                createSvcMachMstrByItemChng(pMsg);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
            // add start 2016/04/13 CSA Defect#4903
            updateSvcConfigMstrDtl(pMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // add end 2016/04/13 CSA Defect#4903
        }
        // del start 2016/06/28 CSA Defect#10830
//        }
        // del end 2016/06/28 CSA Defect#10830
    }

    // START 2019/11/14 K.Kitachi [QC#54327, ADD]
    private void exeRmvConfig() {

        vldMandatoryCheckForRmvConfig();
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            SVC_MACH_MSTRTMsg exstTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue());
            if (exstTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            if (!SVC_MACH_MSTR_STS.CREATED.equals(exstTMsg.svcMachMstrStsCd.getValue()) && !SVC_MACH_MSTR_STS.REMOVED.equals(exstTMsg.svcMachMstrStsCd.getValue())) {
                msgMap.addXxMsgId(NSZM1368E);
                return;
            }

            SVC_MACH_MSTRTMsg updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue());
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
                return;
            }

            updateSvcMachMstrByRmvConfig(pMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            updateSvcConfigMstrDtlForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue(), pMsg.xxCmptMachList.no(i).effThruDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            createSvcMachMstrTrk(pMsg, exstTMsg, updTMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
    }
    // END 2019/11/14 K.Kitachi [QC#54327, ADD]

    private void updateSvcMachMstrByUpdWH(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        // START 2015/11/09 T.Tomita [QC#500, DEL]
//        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_SQ));
        // END 2015/11/09 T.Tomita [QC#500, DEL]
        if (hasValue(pMsg.serNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.serNum, pMsg.serNum);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        BigDecimal nextSqNum = null;
        String svcMachTpCd = getSvcMachTpCd(pMsg);
        if (hasValue(svcMachTpCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, svcMachTpCd);
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            } else {
                // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), exstTMsg.svcMachMstrPk.getValue());
                // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, nextSqNum);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
            }
        }
        // mod start 2015/12/07 CSA Defect#1548
        if (hasValue(pMsg.custMachCtrlNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        }
        // mod end 2015/12/07 CSA Defect#1548
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        // START 2017/03/22 K.Kitachi [QC#15679, MOD]
        if (hasValue(pMsg.oldSerNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.oldSerNum, pMsg.oldSerNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.dummySerFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2017/03/22 K.Kitachi [QC#15679, MOD]
        // mod start 2017/01/11 CSA QC#16850
        if (hasValue(pMsg.iwrCondCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        }
        // mod end 2017/01/11 CSA QC#16850
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.istlDt, pMsg.istlDt);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.effFromDt, pMsg.effFromDt);
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.effThruDt, pMsg.effThruDt);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.prntSerNum, pMsg.prntSerNum);
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        // START 2015/11/12 T.Tomita [QC#526, ADD]
        exstTMsg.ownrAcctNum.clear();
        exstTMsg.ownrLocNum.clear();
        exstTMsg.billToAcctNum.clear();
        exstTMsg.billToLocNum.clear();
        // END 2015/11/12 T.Tomita [QC#526, ADD]
        exstTMsg.curLocAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachQty, pMsg.svcMachQty);
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // add start 2016/06/01 CSA Defect#9317
        exstTMsg.indBillToLocNum.clear();
        // add end 2016/06/01 CSA Defect#9317
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2018/01/26 U.Kim [QC#23310, DEL]
        // ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2018/01/26 U.Kim [QC#23310, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByUpdMIF(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        if (hasValue(pMsg.serNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.serNum, pMsg.serNum);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        BigDecimal nextSqNum = null;
        String svcMachTpCd = getSvcMachTpCd(pMsg);
        // START 2015/11/05 T.Tomita [QC#430, MOD]
        if (hasValue(pMsg.svcConfigMstrPk) && hasValue(svcMachTpCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, svcMachTpCd);
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            } else {
                // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), exstTMsg.svcMachMstrPk.getValue());
                // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, nextSqNum);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
            }
        }
        // END 2015/11/05 T.Tomita [QC#430, MOD]
        // mod start 2015/12/07 CSA Defect#1548
        if (hasValue(pMsg.custMachCtrlNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        }
        // mod end 2015/12/07 CSA Defect#1548
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        // mod start 2017/01/11 CSA QC#16850
        if (hasValue(pMsg.locNm)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.locNm, pMsg.locNm);
        }
        if (hasValue(pMsg.addlLocNm)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.addlLocNm, pMsg.addlLocNm);
        }
        if (hasValue(pMsg.firstLineAddr)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.firstLineAddr, pMsg.firstLineAddr);
        }
        if (hasValue(pMsg.scdLineAddr)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.scdLineAddr, pMsg.scdLineAddr);
        }
        if (hasValue(pMsg.thirdLineAddr)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.thirdLineAddr, pMsg.thirdLineAddr);
        }
        if (hasValue(pMsg.frthLineAddr)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.frthLineAddr, pMsg.frthLineAddr);
        }
        if (hasValue(pMsg.ctyAddr)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.ctyAddr, pMsg.ctyAddr);
        }
        if (hasValue(pMsg.stCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stCd, pMsg.stCd);
        }
        if (hasValue(pMsg.provNm)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.provNm, pMsg.provNm);
        }
        if (hasValue(pMsg.cntyNm)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.cntyNm, pMsg.cntyNm);
        }
        if (hasValue(pMsg.postCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.postCd, pMsg.postCd);
        }
        if (hasValue(pMsg.ctryCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.ctryCd, pMsg.ctryCd);
        }
        if (hasValue(pMsg.svcMachFlNm)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachFlNm, pMsg.svcMachFlNm);
        }
        // mod end 2017/01/11 CSA QC#16850
        // START 2017/03/22 K.Kitachi [QC#15679, MOD]
        if (hasValue(pMsg.oldSerNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.oldSerNum, pMsg.oldSerNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.dummySerFlg, ZYPConstant.FLG_OFF_N);
        }
        // END 2017/03/22 K.Kitachi [QC#15679, MOD]
        // mod start 2017/01/11 CSA QC#16850
        if (hasValue(pMsg.iwrCondCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        }
        // mod end 2017/01/11 CSA QC#16850
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.istlDt, pMsg.istlDt);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.effFromDt, pMsg.effFromDt);
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.effThruDt, pMsg.effThruDt);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.prntSerNum, pMsg.prntSerNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrAcctNum, pMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrLocNum, pMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachQty, pMsg.svcMachQty);
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indBillToLocNum, getIndBillToLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue()));
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
        //// START 2017/07/07 K.Kojima [QC#19660,ADD]
        //if (hasValue(pMsg.svcByLineBizTpCd)) {
        //    ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, pMsg.svcByLineBizTpCd);
        //}
        //// END 2017/07/07 K.Kojima [QC#19660,ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByUpdTrmn(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        exstTMsg.svcConfigMstrPk.clear();
        exstTMsg.svcMachSqNum.clear();
        exstTMsg.svcMachTpCd.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.effThruDt, pMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachQty, pMsg.svcMachQty);
        // START 2023/10/02 T.Kuroda [QC#61265, DEL]
        //if (hasValue(pMsg.svcMachMstrLocStsCd)) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        //}
        // END   2023/10/02 T.Kuroda [QC#61265, DEL]
        // Add Start 2017/10/17 QC#21798
        if (hasValue(pMsg.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        }
        if (hasValue(pMsg.cpoDtlLineNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineNum, pMsg.cpoDtlLineNum);
        }
        if (hasValue(pMsg.cpoDtlLineSubNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineSubNum, pMsg.cpoDtlLineSubNum);
        }
        if (hasValue(pMsg.shpgPlnNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.shpgPlnNum, pMsg.shpgPlnNum);
        }
        if (hasValue(pMsg.soNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.soNum, pMsg.soNum);
        }
        if (hasValue(pMsg.soSlpNum)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.soSlpNum, pMsg.soSlpNum);
        }
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // Add End 2017/10/17 QC#21798
        // START 2023/04/26 L.Borrega [QC#61388, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        // END 2023/04/26 L.Borrega [QC#61388, ADD]
        // START 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxTrmnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.ownrAcctNum, pMsg.ownrAcctNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.billToAcctNum, pMsg.billToAcctNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.billToLocNum, pMsg.billToLocNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.curLocAcctNum, pMsg.curLocAcctNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
            ZYPEZDItemValueSetter.setValue(exstTMsg.indBillToLocNum, getIndBillToLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue()));
            ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        }
        // END 2023/12/19 T.Fukuta [CSA-QC#61841,ADD]
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByShip(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.serNum, pMsg.serNum);
        BigDecimal nextSqNum = null;
        // START 2015/11/05 T.Tomita [QC#430, MOD]
        if (hasValue(pMsg.svcConfigMstrPk) && hasValue(pMsg.svcMachTpCd)) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            } else {
                // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), exstTMsg.svcMachMstrPk.getValue());
                // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, nextSqNum);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
            }
            // START 2015/12/11 T.Tomita [QC#1799, ADD]
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
            // END 2015/12/11 T.Tomita [QC#1799, ADD]
        }
        // END 2015/11/05 T.Tomita [QC#430, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, pMsg.svcMachTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipFromWhCd, pMsg.shipFromWhCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipDt, pMsg.shipDt);
        // add start 2016/06/02 CSA Defect#6142
        exstTMsg.svcMachRmvDt.clear();
        // add end 2016/06/02 CSA Defect#6142
        ZYPEZDItemValueSetter.setValue(exstTMsg.locNm, pMsg.locNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.addlLocNm, pMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.thirdLineAddr, pMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.frthLineAddr, pMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.provNm, pMsg.provNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cntyNm, pMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.postCd, pMsg.postCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctryCd, pMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachFlNm, pMsg.svcMachFlNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineNum, pMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineSubNum, pMsg.cpoDtlLineSubNum);
        // mod start 2016/05/17 CSA Defect#7076
        exstTMsg.rmaNum.clear();
        exstTMsg.rmaLineNum.clear();
        exstTMsg.rmaLineSubNum.clear();
        exstTMsg.rmaLineSubTrxNum.clear();
        // mod end 2016/05/17 CSA Defect#7076
        ZYPEZDItemValueSetter.setValue(exstTMsg.shpgPlnNum, pMsg.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.soNum, pMsg.soNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.soSlpNum, pMsg.soSlpNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsOrdRsnCd, pMsg.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.prntSerNum, pMsg.prntSerNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrAcctNum, pMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrLocNum, pMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_01, pMsg.ctrlFldTxt_01);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_02, pMsg.ctrlFldTxt_02);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_03, pMsg.ctrlFldTxt_03);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_04, pMsg.ctrlFldTxt_04);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_05, pMsg.ctrlFldTxt_05);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_06, pMsg.ctrlFldTxt_06);
        ZYPEZDItemValueSetter.setValue(exstTMsg.prcContrNum, pMsg.prcContrNum);
        if (hasValue(pMsg.corpAdvtgStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.corpAdvtgStsCd, pMsg.corpAdvtgStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsPoExprDt, pMsg.dsPoExprDt);
        if (hasValue(pMsg.hardDriveRmvInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveRmvInclFlg, pMsg.hardDriveRmvInclFlg);
        }
        if (hasValue(pMsg.hardDriveEraseInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveEraseInclFlg, pMsg.hardDriveEraseInclFlg);
        }
        if (hasValue(pMsg.leaseRtrnFeeInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.leaseRtrnFeeInclFlg, pMsg.leaseRtrnFeeInclFlg);
        }
        if (hasValue(pMsg.dsKeyAcctFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.dsKeyAcctFlg, pMsg.dsKeyAcctFlg);
        }
        if (hasValue(pMsg.svcNtwkConnStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcNtwkConnStsCd, pMsg.svcNtwkConnStsCd);
        }
        // mod start 2016/06/30 CSA Defect#10462
        ZYPEZDItemValueSetter.setValue(exstTMsg.sldByLineBizTpCd, getSldByLineBizTpCd(pMsg));
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, getSvcByLineBizTpCd(pMsg));
        exstTMsg = setSvcPrvdPtyCd(exstTMsg, pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        // mod end 2016/06/30 CSA Defect#10462
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcLicCnt, pMsg.svcLicCnt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        // mod start 2016/06/30 CSA Defect#18652
//        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
//            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
//        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        // mod end   2016/06/30 CSA Defect#18652

        // START 2016/06/10 [QC#9591, MOD]
        String iwrEnblFlg = getIwrEnblFlg(pMsg);
        if (ZYPConstant.FLG_ON_Y.equals(iwrEnblFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, IWR_COND.REQUESTED);
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrRgtnStsCd, IWR_RGTN_STS.AWAITING_REGISTRATION);
        } else {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, IWR_COND.DISABLED);
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrRgtnStsCd, IWR_RGTN_STS.N_OR_A);
        }
        // END   2016/06/10 [QC#9591, MOD]

        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSunFromTm, pMsg.bizHrsSunFromTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSunToTm, pMsg.bizHrsSunToTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsMonFriFromTm, pMsg.bizHrsMonFriFromTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsMonFriToTm, pMsg.bizHrsMonFriToTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSatFromTm, pMsg.bizHrsSatFromTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSatToTm, pMsg.bizHrsSatToTm);
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indBillToLocNum, getIndBillToLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue()));
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568

        // START 2018/08/09 T.Wada   [QC#27595, ADD]
        if (ZYPCommonFunc.hasValue(exstTMsg.svcByLineBizTpCd) && LINE_BIZ_TP.ESS.equals(exstTMsg.svcByLineBizTpCd.getValue())) {
            String dummyTech = ZYPCodeDataUtil.getVarCharConstValue(CONST_ESS_DUMMY_TECH, pMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(dummyTech)) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.reqTechCd, dummyTech);
            }
        }
        // END   2018/08/09 T.Wada   [QC#27595, ADD]

        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    private SVC_MACH_MSTRTMsg setSvcPrvdPtyCd(SVC_MACH_MSTRTMsg exstTMsg, String svcMachMstrStsCd) {
        CPO_DTLTMsg cpoDtlTMsg = findCpoDtl(exstTMsg.glblCmpyCd.getValue(), exstTMsg.cpoOrdNum.getValue(), exstTMsg.cpoDtlLineNum.getValue(), exstTMsg.cpoDtlLineSubNum.getValue());
        Map<String, Object> dsCpoIstlInfo = findDsCpoIstlInfo(cpoDtlTMsg.glblCmpyCd.getValue(), cpoDtlTMsg.dsCpoConfigPk.getValue());
        if (dsCpoIstlInfo != null) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, (String) dsCpoIstlInfo.get("ISTL_BY_SVC_PRVD_PTY_CD"));
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, (String) dsCpoIstlInfo.get("SVC_BY_SVC_PRVD_PTY_CD"));
        }
        if (exstTMsg.istlBySvcPrvdPtyCd.getValue().isEmpty() || exstTMsg.svcBySvcPrvdPtyCd.getValue().isEmpty()) {
            Map<String, Object> svcLineBizCdMap = getSvcLineBizCdforDICheck(cpoDtlTMsg.glblCmpyCd.getValue(), cpoDtlTMsg.dsCpoConfigPk.getValue(), cpoDtlTMsg.mdseCd.getValue());
            // START 2024/04/04 t.aizawa [QC#54186, ADD]
            if (svcLineBizCdMap != null) {
            // END   2024/04/04 t.aizawa [QC#54186, ADD]
                String mdseItemTpCd = (String) svcLineBizCdMap.get("MDSE_ITEM_TP_CD");
                if (MDSE_ITEM_TP.ACCESSORY.equals(mdseItemTpCd)) {
                    BigDecimal svcConfigMstrPk = (BigDecimal) svcLineBizCdMap.get("SVC_CONFIG_MSTR_PK");
                    SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = findSvcConfigMstr(cpoDtlTMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(svcConfigMstrTMsg.glblCmpyCd.getValue(), svcConfigMstrTMsg.svcMachMstrPk.getValue());
                    if (exstTMsg.istlBySvcPrvdPtyCd.getValue().isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, svcMachMstrTMsg.istlBySvcPrvdPtyCd);
                    }
                    if (exstTMsg.svcBySvcPrvdPtyCd.getValue().isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, svcMachMstrTMsg.svcBySvcPrvdPtyCd);
                    }
                } else if (MDSE_ITEM_TP.SOFTWARE.equals(mdseItemTpCd)) {
                    String svcPrvdPty = getSvcPrvdPty((String) svcLineBizCdMap.get("SVC_LINE_BIZ_CD"));
                    if (svcPrvdPty != null && exstTMsg.istlBySvcPrvdPtyCd.getValue().isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, svcPrvdPty);
                    }
                    if (svcPrvdPty != null && exstTMsg.svcBySvcPrvdPtyCd.getValue().isEmpty()) {
                        ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, svcPrvdPty);
                    }
                }
            // START 2024/04/04 t.aizawa [QC#54186, ADD]
            }

            String svcLineBizCd = getSvcLineBizCdByMdseCd(cpoDtlTMsg.glblCmpyCd.getValue(), cpoDtlTMsg.mdseCd.getValue());
            String svcPrvdPtyCd = getSvcPrvdPty(svcLineBizCd);
            if (exstTMsg.istlBySvcPrvdPtyCd.getValue().isEmpty()) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, svcPrvdPtyCd);
            }
            if (exstTMsg.svcBySvcPrvdPtyCd.getValue().isEmpty()) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, svcPrvdPtyCd);
            }
            // END   2024/04/04 t.aizawa [QC#54186, ADD]
        }
        SVC_PRVD_PTYTMsg svcPrvdPtyTMsg = new SVC_PRVD_PTYTMsg();
        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)) {
            svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(exstTMsg.glblCmpyCd.getValue(), exstTMsg.istlBySvcPrvdPtyCd.getValue());
        } else {
            svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(exstTMsg.glblCmpyCd.getValue(), exstTMsg.svcBySvcPrvdPtyCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, svcPrvdPtyTMsg.lineBizTpCd);
        return exstTMsg;
    }
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]

    // add 2016/04/19 CSA Defect#7004
    private void updateSvcMachMstrByIstl(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.istlDt, pMsg.istlDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        String dummyTech = ZYPCodeDataUtil.getVarCharConstValue(CONST_ESS_DUMMY_TECH, pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(dummyTech)) {
            if (ZYPCommonFunc.hasValue(exstTMsg.svcByLineBizTpCd) && LINE_BIZ_TP.ESS.equals(exstTMsg.svcByLineBizTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.reqTechCd, dummyTech);
            } else if (ZYPCommonFunc.hasValue(exstTMsg.reqTechCd) && dummyTech.equals(exstTMsg.reqTechCd.getValue())) {
                exstTMsg.reqTechCd.clear();
            }
        }
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]

        // START 2016/06/10 [QC#9591, MOD]
        if (hasValue(pMsg.iwrCondCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        }
        // END   2016/06/10 [QC#9591, MOD]

        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByIstlForSource(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        exstTMsg.svcConfigMstrPk.clear();
        exstTMsg.svcMachTpCd.clear();
        exstTMsg.svcMachSqNum.clear();
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    // mod start 2016/10/13 CSA Defect#14898
    private void updateSvcMachMstrByIstlForDestination(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg, BigDecimal svcConfigMstrPk) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.istlDt, pMsg.istlDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        String dummyTech = ZYPCodeDataUtil.getVarCharConstValue(CONST_ESS_DUMMY_TECH, pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(dummyTech)) {
            if (ZYPCommonFunc.hasValue(exstTMsg.svcByLineBizTpCd) && LINE_BIZ_TP.ESS.equals(exstTMsg.svcByLineBizTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.reqTechCd, dummyTech);
            } else if (ZYPCommonFunc.hasValue(exstTMsg.reqTechCd) && dummyTech.equals(exstTMsg.reqTechCd.getValue())) {
                exstTMsg.reqTechCd.clear();
            }
        }
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (hasValue(pMsg.iwrCondCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        }
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }
    // mod end 2016/10/13 CSA Defect#14898

    private void updateSvcMachMstrByRMA(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rtrnToWhCd, pMsg.rtrnToWhCd);
        // mod start 2016/05/17 CSA Defect#7076
//        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineNum, pMsg.cpoDtlLineNum);
//        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineSubNum, pMsg.cpoDtlLineSubNum);

        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaNum, pMsg.rmaNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaLineNum, pMsg.rmaLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaLineSubNum, pMsg.rmaLineSubNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaLineSubTrxNum, pMsg.rmaLineSubTrxNum);

        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(exstTMsg.trxHdrNum, pMsg.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.trxLineNum, pMsg.trxLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.trxLineSubNum, pMsg.trxLineSubNum);
        // mod end 2016/05/17 CSA Defect#7076
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        if (hasValue(pMsg.hardDriveRmvInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveRmvInclFlg, pMsg.hardDriveRmvInclFlg);
        }
        if (hasValue(pMsg.hardDriveEraseInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveEraseInclFlg, pMsg.hardDriveEraseInclFlg);
        }
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByRMACanc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        exstTMsg.rtrnToWhCd.clear();
        // mod start 2016/05/17 CSA Defect#7076
//        exstTMsg.cpoOrdNum.clear();
//        exstTMsg.cpoDtlLineNum.clear();
//        exstTMsg.cpoDtlLineSubNum.clear();

        exstTMsg.rmaNum.clear();
        exstTMsg.rmaLineNum.clear();
        exstTMsg.rmaLineSubNum.clear();
        exstTMsg.rmaLineSubTrxNum.clear();

        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);

        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        // mod end 2016/05/17 CSA Defect#7076
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByRWS(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        exstTMsg.shipFromWhCd.clear();
        exstTMsg.shipDt.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachRmvDt, pMsg.svcMachRmvDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rtrnToWhCd, pMsg.rtrnToWhCd);
        exstTMsg.locNm.clear();
        exstTMsg.addlLocNm.clear();
        exstTMsg.firstLineAddr.clear();
        exstTMsg.scdLineAddr.clear();
        exstTMsg.thirdLineAddr.clear();
        exstTMsg.frthLineAddr.clear();
        exstTMsg.ctyAddr.clear();
        exstTMsg.stCd.clear();
        exstTMsg.provNm.clear();
        exstTMsg.cntyNm.clear();
        exstTMsg.postCd.clear();
        exstTMsg.ctryCd.clear();
        exstTMsg.svcMachFlNm.clear();
        exstTMsg.prfTechCd.clear();
        // mod start 2016/05/17 CSA Defect#7759
//        exstTMsg.cpoOrdNum.clear();
//        exstTMsg.cpoDtlLineNum.clear();
//        exstTMsg.cpoDtlLineSubNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaNum, pMsg.rmaNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaLineNum, pMsg.rmaLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaLineSubNum, pMsg.rmaLineSubNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.rmaLineSubTrxNum, pMsg.rmaLineSubTrxNum);
        // mod end 2016/05/17 CSA Defect#7759
        exstTMsg.custIssPoNum.clear();
        // del start 2016/05/17 CSA Defect#7759
//        exstTMsg.shpgPlnNum.clear();
//        exstTMsg.soNum.clear();
//        exstTMsg.soSlpNum.clear();
        // del end 2016/05/17 CSA Defect#7759
        exstTMsg.dsOrdTpCd.clear();
        exstTMsg.dsOrdRsnCd.clear();
        // START 2016/06/10 [QC#9591, DEL]
        //exstTMsg.iwrCondCd.clear();
        // END   2016/06/10 [QC#9591, DEL]
        // START 2018/02/14 M.Kidokoro [QC#24143,DEL]
//        exstTMsg.mtrGrpPk.clear();
//        exstTMsg.mtrGrpNm.clear();
//        exstTMsg.mtrGrpDescTxt.clear();
        // END 2018/02/14 M.Kidokoro [QC#24143,DEL]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        exstTMsg.prntSerNum.clear();
        exstTMsg.svcMachTrxTpCd.clear();
        exstTMsg.ownrAcctNum.clear();
        exstTMsg.ownrLocNum.clear();
        exstTMsg.billToAcctNum.clear();
        exstTMsg.billToLocNum.clear();
        exstTMsg.curLocAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        exstTMsg.ctrlFldTxt_01.clear();
        exstTMsg.ctrlFldTxt_02.clear();
        exstTMsg.ctrlFldTxt_03.clear();
        exstTMsg.ctrlFldTxt_04.clear();
        exstTMsg.ctrlFldTxt_05.clear();
        exstTMsg.ctrlFldTxt_06.clear();
        exstTMsg.prcContrNum.clear();
        exstTMsg.corpAdvtgStsCd.clear();
        exstTMsg.dsPoExprDt.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(exstTMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_OFF_N);
//        exstTMsg.fldSvcBrCd.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsKeyAcctFlg, ZYPConstant.FLG_OFF_N);
        exstTMsg.svcNtwkConnStsCd.clear();
        // del start 2019/11/06 QC#54362
        //exstTMsg.sldByLineBizTpCd.clear();
        // del end 2019/11/06 QC#54362
//        exstTMsg.svcByLineBizTpCd.clear();
        exstTMsg.svcLicCnt.clear();
        exstTMsg.reqTechCd.clear();
//        exstTMsg.finBrCd.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // add start 2016/05/13 CSA Defect#4578
        exstTMsg.indBillToLocNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2016/06/10 [QC#9591, MOD]
        if (IWR_RGTN_STS.REGISTERED.equals(exstTMsg.iwrRgtnStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrRgtnStsCd, IWR_RGTN_STS.AWAITING_DEREGISTRATION);
        }
        // END   2016/06/10 [QC#9591, MOD]
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2018/01/26 U.Kim [QC#23310, DEL]
        // ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2018/01/26 U.Kim [QC#23310, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByDispose(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        exstTMsg.svcConfigMstrPk.clear();
        exstTMsg.svcMachSqNum.clear();
        exstTMsg.svcMachTpCd.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.effThruDt, pMsg.effThruDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        // START 2023/10/02 T.Kuroda [QC#61265, DEL]
        //if (hasValue(pMsg.svcMachMstrLocStsCd)) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        //}
        // END   2023/10/02 T.Kuroda [QC#61265, DEL]
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByTrnsfOut(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        // add start 2016/07/29 CSA Defect#12642
        BigDecimal nextSqNum = null;
        if (hasValue(pMsg.svcConfigMstrPk) && hasValue(pMsg.svcMachTpCd)) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            } else {
                // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), exstTMsg.svcMachMstrPk.getValue());
                // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, nextSqNum);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
            }
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, pMsg.svcMachTpCd);
        }
        // add end 2016/07/29 CSA Defect#12642

        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipFromWhCd, pMsg.shipFromWhCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipDt, pMsg.shipDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }

        // add start 2016/07/29 CSA Defect#12510
        exstTMsg.ownrAcctNum.clear();
        exstTMsg.ownrLocNum.clear();
        exstTMsg.billToAcctNum.clear();
        exstTMsg.billToLocNum.clear();
        exstTMsg.curLocAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        exstTMsg.indBillToLocNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/07/29 CSA Defect#12510
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByTrnsfIn(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        exstTMsg.curLocAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrBySvcUpd(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.drumExchDt, pMsg.drumExchDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.lastSvcCallDt, pMsg.lastSvcCallDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.totSvcVisitCnt, pMsg.totSvcVisitCnt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.lastTechVisitDt, pMsg.lastTechVisitDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.lastPrvntMaintDt, pMsg.lastPrvntMaintDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.lastSvcCallVisitDt, pMsg.lastSvcCallVisitDt);
        // add start 2016/04/19 CSA Defect#7004
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // add end 2016/04/19 CSA Defect#7004

        // START 2016/06/10 [QC#9591, MOD]
        if (hasValue(pMsg.iwrCondCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        }
        // END   2016/06/10 [QC#9591, MOD]

        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByConvOrd(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.locNm, pMsg.locNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.addlLocNm, pMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.thirdLineAddr, pMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.frthLineAddr, pMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.provNm, pMsg.provNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cntyNm, pMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.postCd, pMsg.postCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctryCd, pMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachFlNm, pMsg.svcMachFlNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineNum, pMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineSubNum, pMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.custIssPoNum, pMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsOrdRsnCd, pMsg.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.convInprocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrAcctNum, pMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrLocNum, pMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indBillToLocNum, getIndBillToLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue()));
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568
        // Add Start 2017/10/24 QC#21550
        if (hasValue(pMsg.svcMachTrxTpCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        }
        // Add End 2017/10/24 QC#21550
        // START 2019/03/18 K.Fujimoto [CSA QC#30793, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        // END   2019/03/18 K.Fujimoto [CSA QC#30793, ADD]
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByUpdAttr(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.prfTechCd, pMsg.prfTechCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.custIssPoNum, pMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_01, pMsg.ctrlFldTxt_01);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_02, pMsg.ctrlFldTxt_02);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_03, pMsg.ctrlFldTxt_03);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_04, pMsg.ctrlFldTxt_04);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_05, pMsg.ctrlFldTxt_05);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_06, pMsg.ctrlFldTxt_06);
        ZYPEZDItemValueSetter.setValue(exstTMsg.prcContrNum, pMsg.prcContrNum);
        if (hasValue(pMsg.corpAdvtgStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.corpAdvtgStsCd, pMsg.corpAdvtgStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsPoExprDt, pMsg.dsPoExprDt);
        if (hasValue(pMsg.hardDriveRmvInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveRmvInclFlg, pMsg.hardDriveRmvInclFlg);
        }
        if (hasValue(pMsg.hardDriveEraseInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveEraseInclFlg, pMsg.hardDriveEraseInclFlg);
        }
        if (hasValue(pMsg.leaseRtrnFeeInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.leaseRtrnFeeInclFlg, pMsg.leaseRtrnFeeInclFlg);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, pMsg.fldSvcBrCd);
        if (hasValue(pMsg.dsKeyAcctFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.dsKeyAcctFlg, pMsg.dsKeyAcctFlg);
        }
        if (hasValue(pMsg.svcNtwkConnStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcNtwkConnStsCd, pMsg.svcNtwkConnStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.sldByLineBizTpCd, pMsg.sldByLineBizTpCd);
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
        //// START 2015/11/16 T.Tomita [QC#647, ADD]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, pMsg.svcByLineBizTpCd);
        //// END 2015/11/16 T.Tomita [QC#647, ADD]
        exstTMsg = setSvcByLineBizTpCd(exstTMsg, pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcLicCnt, pMsg.svcLicCnt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.reqTechCd, pMsg.reqTechCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, pMsg.finBrCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // mod start 2017/01/11 CSA QC#16850
        if (hasValue(pMsg.iwrCondCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        }
        // mod end 2017/01/11 CSA QC#16850
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSunFromTm, pMsg.bizHrsSunFromTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSunToTm, pMsg.bizHrsSunToTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsMonFriFromTm, pMsg.bizHrsMonFriFromTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsMonFriToTm, pMsg.bizHrsMonFriToTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSatFromTm, pMsg.bizHrsSatFromTm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.bizHrsSatToTm, pMsg.bizHrsSatToTm);
        if (hasValue(pMsg.enetPlotFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.enetPlotFlg, pMsg.enetPlotFlg);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.enetCmntTxt_01, pMsg.enetCmntTxt_01);
        ZYPEZDItemValueSetter.setValue(exstTMsg.enetCmntTxt_02, pMsg.enetCmntTxt_02);
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByUpdLoc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.locNm, pMsg.locNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.addlLocNm, pMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.thirdLineAddr, pMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.frthLineAddr, pMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.provNm, pMsg.provNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cntyNm, pMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.postCd, pMsg.postCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctryCd, pMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachFlNm, pMsg.svcMachFlNm);
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByAllocOn(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(exstTMsg.trxHdrNum, pMsg.trxHdrNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.trxLineNum, pMsg.trxLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.trxLineSubNum, pMsg.trxLineSubNum);
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByAllocOff(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrBySvcExch(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        // mod start 2016/09/08 CSA Defect#13902
//        ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
//        BigDecimal nextSqNum = null;
//        // START 2015/11/05 T.Tomita [QC#430, MOD]
//        if (hasValue(pMsg.svcConfigMstrPk) && hasValue(pMsg.svcMachTpCd)) {
//            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
//                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
//            } else {
//                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
//                ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, nextSqNum);
//                ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
//            }
//            // START 2015/12/11 T.Tomita [QC#1799, ADD]
//            ZYPEZDItemValueSetter.setValue(exstTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
//            // END 2015/12/11 T.Tomita [QC#1799, ADD]
//        }
//        // END 2015/11/05 T.Tomita [QC#430, MOD]
//      ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, pMsg.svcMachTpCd);
        exstTMsg.svcConfigMstrPk.clear();
        // Mod Start 2018/03/02 QC#23425
//        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, BigDecimal.ONE);
//        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
//        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, pMsg.svcMachTpCd);
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
        } else {
            BigDecimal nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), exstTMsg.svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, nextSqNum);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
        }
        // Mod End 2018/03/02 QC#23425
        // mod end 2016/09/08 CSA Defect#13902
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipFromWhCd, pMsg.shipFromWhCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipDt, pMsg.shipDt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.locNm, pMsg.locNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.addlLocNm, pMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.thirdLineAddr, pMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.frthLineAddr, pMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(exstTMsg.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.provNm, pMsg.provNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cntyNm, pMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.postCd, pMsg.postCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctryCd, pMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachFlNm, pMsg.svcMachFlNm);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineNum, pMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.cpoDtlLineSubNum, pMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.custIssPoNum, pMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.shpgPlnNum, pMsg.shpgPlnNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.soNum, pMsg.soNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.soSlpNum, pMsg.soSlpNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsOrdRsnCd, pMsg.dsOrdRsnCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.prntSerNum, pMsg.prntSerNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrAcctNum, pMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ownrLocNum, pMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_01, pMsg.ctrlFldTxt_01);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_02, pMsg.ctrlFldTxt_02);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_03, pMsg.ctrlFldTxt_03);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_04, pMsg.ctrlFldTxt_04);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_05, pMsg.ctrlFldTxt_05);
        ZYPEZDItemValueSetter.setValue(exstTMsg.ctrlFldTxt_06, pMsg.ctrlFldTxt_06);
        ZYPEZDItemValueSetter.setValue(exstTMsg.prcContrNum, pMsg.prcContrNum);
        if (hasValue(pMsg.corpAdvtgStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.corpAdvtgStsCd, pMsg.corpAdvtgStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.dsPoExprDt, pMsg.dsPoExprDt);
        if (hasValue(pMsg.hardDriveRmvInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveRmvInclFlg, pMsg.hardDriveRmvInclFlg);
        }
        if (hasValue(pMsg.hardDriveEraseInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.hardDriveEraseInclFlg, pMsg.hardDriveEraseInclFlg);
        }
        if (hasValue(pMsg.leaseRtrnFeeInclFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.leaseRtrnFeeInclFlg, pMsg.leaseRtrnFeeInclFlg);
        }
        if (hasValue(pMsg.dsKeyAcctFlg)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.dsKeyAcctFlg, pMsg.dsKeyAcctFlg);
        }
        if (hasValue(pMsg.svcNtwkConnStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcNtwkConnStsCd, pMsg.svcNtwkConnStsCd);
        }
        // START 2019/05/20 [QC#50119, MOD]
        // ZYPEZDItemValueSetter.setValue(exstTMsg.sldByLineBizTpCd, pMsg.sldByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.sldByLineBizTpCd, getSldByLineBizTpCd(pMsg));
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, getSvcByLineBizTpCd(pMsg));
        exstTMsg = setSvcPrvdPtyCd(exstTMsg, pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        // END 2019/05/20 [QC#50119, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcLicCnt, pMsg.svcLicCnt);
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        exstTMsg.trxHdrNum.clear();
        exstTMsg.trxLineNum.clear();
        exstTMsg.trxLineSubNum.clear();
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.iwrCondCd, pMsg.iwrCondCd);
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indBillToLocNum, getIndBillToLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue()));
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568

        // START 2018/08/09 T.Wada   [QC#27595, ADD]
        if (ZYPCommonFunc.hasValue(exstTMsg.svcByLineBizTpCd) && LINE_BIZ_TP.ESS.equals(exstTMsg.svcByLineBizTpCd.getValue())) {
            String dummyTech = ZYPCodeDataUtil.getVarCharConstValue(CONST_ESS_DUMMY_TECH, pMsg.glblCmpyCd.getValue());
            if (ZYPCommonFunc.hasValue(dummyTech)) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.reqTechCd, dummyTech);
            }
        }
        // END   2018/08/09 T.Wada   [QC#27595, ADD]

        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByUpdInvty(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        exstTMsg.curLocAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2018/01/26 U.Kim [QC#23310, DEL]
        // ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2018/01/26 U.Kim [QC#23310, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    private void updateSvcMachMstrByItemChng(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.mdseCd, pMsg.mdseCd);
        // del start 2016/06/28 CSA Defect#10830
//        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
//        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
        // del end 2016/06/28 CSA Defect#10830
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        exstTMsg.iwrCondCd.clear();
        // START 2016/06/29 T.Tomita [QC#11058, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.effFromDt, pMsg.effFromDt);
        exstTMsg.effThruDt.clear();
        // END 2016/06/29 T.Tomita [QC#11058, ADD]
        if (hasValue(pMsg.stkStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.stkStsCd, pMsg.stkStsCd);
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        if (hasValue(pMsg.svcMachMstrLocStsCd)) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        }
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(exstTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (pMsg.istlBySvcPrvdPtyCd != null && !pMsg.istlBySvcPrvdPtyCd.getValue().isEmpty()) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, pMsg.istlBySvcPrvdPtyCd);
        } else {
            String svcPrvdPty = getSvcPrvdPty(findLineBizReln(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue()));
            if (svcPrvdPty != null) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, svcPrvdPty);
            }
        }
        if (pMsg.svcBySvcPrvdPtyCd != null && !pMsg.svcBySvcPrvdPtyCd.getValue().isEmpty()) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, pMsg.svcBySvcPrvdPtyCd);
        } else {
            String svcPrvdPty = getSvcPrvdPty(findLineBizReln(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue()));
            if (svcPrvdPty != null) {
                ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, svcPrvdPty);
            }
        }
        SVC_PRVD_PTYTMsg svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(exstTMsg.glblCmpyCd.getValue(), exstTMsg.svcBySvcPrvdPtyCd.getValue());
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, svcPrvdPtyTMsg.lineBizTpCd);
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/09/07 CSA Defect#10568
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue());
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, exstTMsg.svcByLineBizTpCd.getValue(), exstTMsg.sldByLineBizTpCd.getValue(), exstTMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        ZYPEZDItemValueSetter.setValue(exstTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        // START 2020/11/19 R.Shimamoto [QC#57983, DEL]
        //ZYPEZDItemValueSetter.setValue(exstTMsg.finBrCd, brCdBean.getFinBrCd());
        // END 2020/11/19 R.Shimamoto [QC#57983, DEL]
        // add end 2016/09/07 CSA Defect#10568
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

    // START 2019/11/14 K.Kitachi [QC#54327, ADD]
    private void updateSvcMachMstrByRmvConfig(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg) {
        exstTMsg.svcConfigMstrPk.clear();
        exstTMsg.svcMachTpCd.clear();
        exstTMsg.svcMachSqNum.clear();
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }
    // END 2019/11/14 K.Kitachi [QC#54327, ADD]

    // START 2015/11/09 T.Tomita [QC#474, ADD]
    private void updateSvcMachMstrForConfgPk(NSZC001001PMsg pMsg) {
        SVC_MACH_MSTRTMsg tMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachTpCd, pMsg.svcMachTpCd);
        // START 2015/12/11 T.Tomita [QC#1799, MOD]
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
        } else {
            // START 2017/01/12 K.Kitachi [QC#16962, MOD]
            BigDecimal nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), tMsg.svcMachMstrPk.getValue());
            // END 2017/01/12 K.Kitachi [QC#16962, MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachSqNum, nextSqNum);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, nextSqNum);
        }
        // END 2015/12/11 T.Tomita [QC#1799, MOD]
        // Add Start 2017/10/10 QC#21292
        ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, getPrntSerNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue()));
        // Add End 2017/10/10 QC#21292
        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0036E);
            return;
        }

        // mod start 2016/07/06 CSA Defect#11470
        BigDecimal svcMachSqNum;
        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            if (!hasValue(pMsg.xxCmptMachList.no(i).svcMachMstrPk) || pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue().compareTo(pMsg.svcMachMstrPk.getValue()) == 0) {
                continue;
            }
            tMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachTpCd, pMsg.xxCmptMachList.no(i).svcMachTpCd);
            // START 2017/01/12 K.Kitachi [QC#16962, MOD]
            svcMachSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), tMsg.svcMachMstrPk.getValue());
            // END 2017/01/12 K.Kitachi [QC#16962, MOD]
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachSqNum, svcMachSqNum);
            // Add Start 2017/10/10 QC#21292
            ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, getPrntSerNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue()));
            // Add End 2017/10/10 QC#21292

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0036E);
                return;
            }
        }
        // mod end 2016/07/06 CSA Defect#11470
    }
    // END 2015/11/09 T.Tomita [QC#474, ADD]

    private void updateSvcMachMstrForCompMachList(NSZC001001PMsg pMsg, BigDecimal cmpSvcMachMstrPk) {
        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            // START 2015/11/09 T.Tomita [QC#474, MOD]
            updateSvcMachMstrForCompMach(pMsg, pMsg.xxCmptMachList.no(i), cmpSvcMachMstrPk);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // END 2015/11/09 T.Tomita [QC#474, MOD]
        }
    }

    // START 2015/11/09 T.Tomita [QC#474, ADD]
    private void updateSvcMachMstrForCompMach(NSZC001001PMsg pMsg, NSZC001001_xxCmptMachListPMsg xxCmptMach, BigDecimal cmpSvcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), xxCmptMach.svcMachMstrPk.getValue());
        if (tMsg != null) {
            // START 2015/12/09 T.Tomita [QC#953, MOD]
            if (!hasValue(xxCmptMach.effThruDt)) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk.getValue());
                if (cmpSvcMachMstrPk.compareTo(tMsg.svcMachMstrPk.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(tMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
                    ZYPEZDItemValueSetter.setValue(tMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.svcMachTpCd, SVC_MACH_TP.ACCESSORY);
                    // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                    BigDecimal nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), tMsg.svcMachMstrPk.getValue());
                    // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                    ZYPEZDItemValueSetter.setValue(tMsg.svcMachSqNum, nextSqNum);
                }
                // Add Start 2017/10/10 QC#21292
                ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, getPrntSerNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue()));
                // Add End 2017/10/10 QC#21292
            } else {
                tMsg.svcConfigMstrPk.clear();
                tMsg.svcMachTpCd.clear();
                tMsg.svcMachSqNum.clear();
            }
            // END 2015/12/09 T.Tomita [QC#953, MOD]
            EZDTBLAccessor.update(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0036E);
            }
        }
    }

    private void updateSvcMachMstrForRemove(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = findSvcMachMstrForUpdate(glblCmpyCd, svcMachMstrPk);
        if (tMsg != null) {
            tMsg.svcConfigMstrPk.clear();
            tMsg.svcMachTpCd.clear();
            // START 2015/12/09 T.Tomita [QC#953, ADD]
            tMsg.svcMachSqNum.clear();
            // END 2015/12/09 T.Tomita [QC#953, ADD]
            // Add Start 2017/10/10 QC#21292
            tMsg.prntSerNum.clear();
            // Add End 2017/10/10 QC#21292
            EZDTBLAccessor.update(tMsg);
            String rtrnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM0036E);
            }
        }
    }
    // END 2015/11/09 T.Tomita [QC#474, ADD]

    private void updateSvcMachMstrForTrmn(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = findSvcMachMstrForUpdate(glblCmpyCd, svcMachMstrPk);
        if (tMsg != null) {
            tMsg.svcConfigMstrPk.clear();
            EZDTBLAccessor.update(tMsg);
            String rtrnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM0036E);
            }
        }
    }

    private void updateSvcMachMstrForSvcPhsMtr(SVC_MACH_MSTRTMsg updTMsg, List<Map<String, Object>> dsMdlMtrList) {
        ZYPEZDItemValueSetter.setValue(updTMsg.mtrGrpPk, (BigDecimal) dsMdlMtrList.get(0).get("MTR_GRP_PK"));
        ZYPEZDItemValueSetter.setValue(updTMsg.mtrGrpNm, (String) dsMdlMtrList.get(0).get("MTR_GRP_NM"));
        ZYPEZDItemValueSetter.setValue(updTMsg.mtrGrpDescTxt, (String) dsMdlMtrList.get(0).get("MTR_GRP_DESC_TXT"));
        // START 2015/11/09 T.Tomita [QC#474, ADD]
        S21FastTBLAccessor.update(updTMsg);
        // END 2015/11/09 T.Tomita [QC#474, ADD]
        String rtnCd = updTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }

//    private void updateSvcMachMstrForSvcPhsMtr(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg updTMsg, BigDecimal mdlId) {
//        // START 2015/12/11 T.Tomita [QC#1877, ADD]
//        if (!isMtrReqMdl(pMsg.glblCmpyCd.getValue(), mdlId)) {
//            return;
//        }
//        // END 2015/12/11 T.Tomita [QC#1877, ADD]
//        // mod start 2016/09/09 CSA Defect#13902
//        List<Map<String, Object>> dsMdlMtrList = findDsModelMeter(pMsg.glblCmpyCd.getValue(), mdlId);
//        // mod end 2016/09/09 CSA Defect#13902
//        if (dsMdlMtrList.size() == 0) {
//            msgMap.addXxMsgId(NSZM0036E);
//            return;
//        }
//
//        updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
//        if (updTMsg != null) {
//            updateSvcMachMstrForSvcPhsMtr(updTMsg, dsMdlMtrList);
//            if (msgMap.getMsgMgr().isXxMsgId()) {
//                return;
//            }
//        }
//    }

    private void updateSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal mdlId) {
        SVC_CONFIG_MSTRTMsg tMsg = findSvcConfigMstrForUpdate(glblCmpyCd, svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, mdlId);
        EZDTBLAccessor.update(tMsg);
        String rtrnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
            msgMap.addXxMsgId(NSZM0038E);
        }
    }

    // add start 2016/04/13 CSA Defect#4903
    private void updateSvcConfigMstrDtl(NSZC001001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcConfigMstrPk = pMsg.svcConfigMstrPk.getValue();
        if (svcConfigMstrPk == null) {
            return;
        }

        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            BigDecimal svcMachMstrPk = pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue();

            SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg = findSvcMachMstr(glblCmpyCd, svcMachMstrPk);
            if (exstSvcMachMstrTMsg != null) {

                List<Map<String, Object>> svcConfigMstrDtlList = findSvcConfigMstrDtlForUpd(glblCmpyCd, svcMachMstrPk, svcConfigMstrPk);
                if (svcConfigMstrDtlList == null || svcConfigMstrDtlList.isEmpty()) {
                    return;
                }
                // START 2016/12/22 K.Kojima [QC#16600,MOD]
                // BigDecimal svcMachMstrDtlPk = (BigDecimal) svcConfigMstrDtlList.get(0).get("SVC_CONFIG_MSTR_DTL_PK");
                BigDecimal svcConfigMstrDtlPk = (BigDecimal) svcConfigMstrDtlList.get(0).get("SVC_CONFIG_MSTR_DTL_PK");
                // END 2016/12/22 K.Kojima [QC#16600,MOD]

                BigDecimal prntSvcMachMstrPk = svcMachMstrPk;
                if (SVC_MACH_TP.ACCESSORY.equals(exstSvcMachMstrTMsg.svcMachTpCd.getValue())) {
                    // START 2016/12/22 K.Kojima [QC#16600,MOD]
                    // SVC_MACH_MSTRTMsg prntMachTMsg = findSvcMachMstrToSerNum(exstSvcMachMstrTMsg.glblCmpyCd.getValue(), exstSvcMachMstrTMsg.prntSerNum.getValue());
                    prntSvcMachMstrPk = getPrntSvcMachMstrPk(glblCmpyCd, svcConfigMstrDtlPk);
                    // END 2016/12/22 K.Kojima [QC#16600,MOD]
                    // START 2016/12/22 K.Kojima [QC#16600,DEL]
                    // if (prntMachTMsg != null) {
                    //     prntSvcMachMstrPk = prntMachTMsg.svcMachMstrPk.getValue();
                    // }
                    // END 2016/12/22 K.Kojima [QC#16600,DEL]
                }

                SVC_CONFIG_MSTR_DTLTMsg prmTMsg = new SVC_CONFIG_MSTR_DTLTMsg();
                // START 2016/12/22 K.Kojima [QC#16600,MOD]
                // ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrDtlPk, svcMachMstrDtlPk);
                ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
                // END 2016/12/22 K.Kojima [QC#16600,MOD]
                ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
                SVC_CONFIG_MSTR_DTLTMsg tMsg = (SVC_CONFIG_MSTR_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);

                ZYPEZDItemValueSetter.setValue(tMsg.serNum, exstSvcMachMstrTMsg.serNum);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, exstSvcMachMstrTMsg.mdseCd);
                ZYPEZDItemValueSetter.setValue(tMsg.prntSvcMachMstrPk, prntSvcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(tMsg.mdlId, (BigDecimal) svcConfigMstrDtlList.get(0).get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(tMsg.istlDt, exstSvcMachMstrTMsg.istlDt);
                ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNum, exstSvcMachMstrTMsg.ownrAcctNum);
                ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNum, exstSvcMachMstrTMsg.billToAcctNum);
                ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, exstSvcMachMstrTMsg.billToLocNum);
                ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, exstSvcMachMstrTMsg.curLocAcctNum);
                ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, exstSvcMachMstrTMsg.curLocNum);
                ZYPEZDItemValueSetter.setValue(tMsg.istlLocOvrdFlg, exstSvcMachMstrTMsg.istlLocOvrdFlg);
                ZYPEZDItemValueSetter.setValue(tMsg.locNm, exstSvcMachMstrTMsg.locNm);
                ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, exstSvcMachMstrTMsg.addlLocNm);
                ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, exstSvcMachMstrTMsg.firstLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, exstSvcMachMstrTMsg.scdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, exstSvcMachMstrTMsg.thirdLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, exstSvcMachMstrTMsg.frthLineAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, exstSvcMachMstrTMsg.ctyAddr);
                ZYPEZDItemValueSetter.setValue(tMsg.stCd, exstSvcMachMstrTMsg.stCd);
                ZYPEZDItemValueSetter.setValue(tMsg.postCd, exstSvcMachMstrTMsg.postCd);
                EZDTBLAccessor.update(tMsg);
                String rtrnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                    msgMap.addXxMsgId(NSZM0717E);
                }
            }
        }
    }
    // add end 2016/04/13 CSA Defect#4903

    private void updateSvcConfigMstrDtlForRmv(String glblCmpyCd, BigDecimal svcConfigMstrPk, String slsDt) {
        SVC_CONFIG_MSTR_DTLTMsgArray exstConfigDtlTMsgArray = findSvcConfigMstrDtlForRmv(glblCmpyCd, svcConfigMstrPk);
        for (int i = 0; i < exstConfigDtlTMsgArray.getValidCount(); i++) {
            SVC_CONFIG_MSTR_DTLTMsg tMsg = (SVC_CONFIG_MSTR_DTLTMsg) exstConfigDtlTMsgArray.get(i);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, slsDt);
            EZDTBLAccessor.update(tMsg);
            String rtrnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM0717E);
            }
        }
    }

    private void updateSvcConfigMstrDtlForTrmn(String glblCmpyCd, BigDecimal svcMachMstrPk, String slsDt) {
        SVC_CONFIG_MSTR_DTLTMsgArray exstConfigDtlTMsgArray = findSvcConfigMstrDtlBySvcMachMstrPk(glblCmpyCd, svcMachMstrPk);
        for (int i = 0; i < exstConfigDtlTMsgArray.getValidCount(); i++) {
            SVC_CONFIG_MSTR_DTLTMsg tMsg = (SVC_CONFIG_MSTR_DTLTMsg) exstConfigDtlTMsgArray.get(i);
            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, slsDt);
                EZDTBLAccessor.update(tMsg);
                String rtrnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                    msgMap.addXxMsgId(NSZM0717E);
                }
            }
        }
    }

//    private void updateSvcConfigMstrDtlForRgtnConfig(String glblCmpyCd, BigDecimal svcConfigMstrPk, String slsDt) {
//        SVC_CONFIG_MSTR_DTLTMsgArray exstConfigDtlTMsgArray = findSvcConfigMstrDtlForRgtnConfig(glblCmpyCd, svcConfigMstrPk);
//        for (int i = 0; i < exstConfigDtlTMsgArray.getValidCount(); i++) {
//            SVC_CONFIG_MSTR_DTLTMsg tMsg = (SVC_CONFIG_MSTR_DTLTMsg) exstConfigDtlTMsgArray.get(i);
//            if (tMsg != null) {
//                ZYPEZDItemValueSetter.setValue(tMsg.rgtnConfigDt, slsDt);
//                EZDTBLAccessor.update(tMsg);
//                String rtrnCd = tMsg.getReturnCode();
//                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
//                    msgMap.addXxMsgId(NSZM0717E);
//                }
//            }
//        }
//    }

    // add start 2016/10/18 CSA Defect#14868
    private void updateSvcConfigMstrDtlByIstlForSource(String glblCmpyCd, BigDecimal svcMachMstrPk, String slsDt) {
        SVC_CONFIG_MSTR_DTLTMsgArray svcConfigMstrDtlList = findSvcConfigMstrDtlForMach(glblCmpyCd, svcMachMstrPk);
        SVC_CONFIG_MSTR_DTLTMsg tMsg;
        for (int i = 0; i < svcConfigMstrDtlList.getValidCount(); i++) {
            tMsg = svcConfigMstrDtlList.no(i);
            // Mod Start 2018/03/14 QC#23427
//            tMsg.svcConfigMstrPk.clear();
            if (hasValue(tMsg.svcMachRmvDt)) {
                continue;
            }
            // Mod End 2018/03/14 QC#23427
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, slsDt);
            EZDTBLAccessor.update(tMsg);
            String rtrnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM0717E);
            }
        }
    }
    // add end 2016/10/18 CSA Defect#14868

    // START 2015/11/16 T.Tomita [QC#647, MOD]
    private void updateSvcNonPrfTech(NSZC001001_xxNonPrfTechListPMsg linePMsg, SVC_NON_PRF_TECHTMsg exstTMsg) {
        ZYPEZDItemValueSetter.setValue(exstTMsg.nonPrfTechCd, linePMsg.nonPrfTechCd);
        ZYPEZDItemValueSetter.setValue(exstTMsg.effThruDt, linePMsg.effThruDt);
        S21FastTBLAccessor.update(exstTMsg);
        String rtnCd = exstTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0036E);
        }
    }
    // END 2015/11/16 T.Tomita [QC#647, MOD]

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    private SVC_MACH_MSTRTMsg setSvcByLineBizTpCd(SVC_MACH_MSTRTMsg exstTMsg, String istlBySvcPrvdPtyCd, String svcBySvcPrvdPtyCd, String svcMachMstrStsCd) {
        if (istlBySvcPrvdPtyCd != null && !istlBySvcPrvdPtyCd.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.istlBySvcPrvdPtyCd, istlBySvcPrvdPtyCd);
        }
        if (svcBySvcPrvdPtyCd != null && !svcBySvcPrvdPtyCd.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(exstTMsg.svcBySvcPrvdPtyCd, svcBySvcPrvdPtyCd);
        }
        SVC_PRVD_PTYTMsg svcPrvdPtyTMsg;
        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)) {
            svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(exstTMsg.glblCmpyCd.getValue(), exstTMsg.istlBySvcPrvdPtyCd.getValue());
        } else {
            svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(exstTMsg.glblCmpyCd.getValue(), exstTMsg.svcBySvcPrvdPtyCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(exstTMsg.svcByLineBizTpCd, svcPrvdPtyTMsg.lineBizTpCd);
        return exstTMsg;
    }
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]

    private void createSvcMachMstrByInsWH(NSZC001001PMsg pMsg) {
        // START 2015/12/09 T.Tomita [CSA QC#951, ADD]
        // IB trackable check
        if (!isInstlBaseCtrlFlg(pMsg)) {
            msgMap.addXxMsgId(NSZM0804E);
            return;
        }
        // END 2015/12/09 T.Tomita [CSA QC#951, ADD]
        SVC_MACH_MSTRTMsg insTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.serNum, pMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        BigDecimal nextSqNum = null;
        // START 2015/11/05 T.Tomita [QC#430, MOD]
        if (hasValue(pMsg.svcConfigMstrPk) && hasValue(pMsg.svcMachTpCd)) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(insTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            } else {
                // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), null);
                // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                ZYPEZDItemValueSetter.setValue(insTMsg.svcMachSqNum, nextSqNum);
            }
        }
        // END 2015/11/05 T.Tomita [QC#430, MOD]
        // mod start 2015/12/07 CSA Defect#1548
        if (hasValue(pMsg.custMachCtrlNum)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.custMachCtrlNum, pMsg.serNum);
        }
        // mod end 2015/12/07 CSA Defect#1548
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachTpCd, pMsg.svcMachTpCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlLocOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.autoCratFlg, pMsg.autoCratFlg);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlStsUpdCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.convInprocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.iwrCondCd, pMsg.iwrCondCd);
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.istlDt, pMsg.istlDt);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, pMsg.effFromDt);
        // START 2016/10/13 T.Tomita [QC#14734, ADD]
        if (SVC_MACH_MSTR_STS.TERMINATED.equals(pMsg.svcMachMstrStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(insTMsg.effThruDt, pMsg.effThruDt);
        }
        // END 2016/10/13 T.Tomita [QC#14734, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.prntSerNum, pMsg.prntSerNum);
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        insTMsg.curLocAcctNum.clear();
        ZYPEZDItemValueSetter.setValue(insTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachQty, pMsg.svcMachQty);
        ZYPEZDItemValueSetter.setValue(insTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.leaseRtrnFeeInclFlg,  ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsKeyAcctFlg,  ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.shrDlrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.enetPlotFlg, ZYPConstant.FLG_OFF_N);
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(insTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        insTMsg = setInsSvcByLineBizTpCd(insTMsg, pMsg.mdseCd.getValue(), pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]

        // add start 2016/08/29 CSA Defect#10568
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg);
        ZYPEZDItemValueSetter.setValue(insTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        ZYPEZDItemValueSetter.setValue(insTMsg.finBrCd, brCdBean.getFinBrCd());
        // START 2023/10/06 K.Watanabe [QC#54186, DEL]
        //ZYPEZDItemValueSetter.setValue(insTMsg.svcByLineBizTpCd, brCdBean.getSvcLineBizCd());
        // END   2023/10/06 K.Watanabe [QC#54186, DEL]
        // add end 2016/08/29 CSA Defect#10568
        // START 2017/03/22 K.Kitachi [QC#15679, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.dummySerFlg, this.dummySerFlg);
        // END 2017/03/22 K.Kitachi [QC#15679, ADD]
        // START 2023/07/31 S.Moriai [QC#61632, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.swLicId, pMsg.swLicId);
        // END 2023/07/31 S.Moriai [QC#61632, ADD]
        S21FastTBLAccessor.create(insTMsg);
        String rtnCd = insTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0034E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, insTMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, insTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, insTMsg.svcMachSqNum);
    }

    private void createSvcMachMstrByInsMIF(NSZC001001PMsg pMsg) {
        // START 2015/12/09 T.Tomita [CSA QC#951, ADD]
        // IB trackable check
        if (!isInstlBaseCtrlFlg(pMsg)) {
            msgMap.addXxMsgId(NSZM0804E);
            return;
        }
        // END 2015/12/09 T.Tomita [CSA QC#951, ADD]
        SVC_MACH_MSTRTMsg insTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.serNum, pMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        BigDecimal nextSqNum = null;
        // START 2015/11/05 T.Tomita [QC#430, MOD]
        if (hasValue(pMsg.svcConfigMstrPk) && hasValue(pMsg.svcMachTpCd)) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(insTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
            } else {
                // START 2017/01/12 K.Kitachi [QC#16962, MOD]
                nextSqNum = findNextSvcMachSqNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), null);
                // END 2017/01/12 K.Kitachi [QC#16962, MOD]
                ZYPEZDItemValueSetter.setValue(insTMsg.svcMachSqNum, nextSqNum);
            }
        }
        // END 2015/11/05 T.Tomita [QC#430, MOD]
        // mod start 2015/12/07 CSA Defect#1548
        if (hasValue(pMsg.custMachCtrlNum)) {
            ZYPEZDItemValueSetter.setValue(insTMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        } else {
            ZYPEZDItemValueSetter.setValue(insTMsg.custMachCtrlNum, pMsg.serNum);
        }
        // mod end 2015/12/07 CSA Defect#1548
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachTpCd, pMsg.svcMachTpCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrStsCd, pMsg.svcMachMstrStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlLocOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.locNm, pMsg.locNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.addlLocNm, pMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(insTMsg.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(insTMsg.thirdLineAddr, pMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(insTMsg.frthLineAddr, pMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(insTMsg.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.provNm, pMsg.provNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.cntyNm, pMsg.cntyNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.postCd, pMsg.postCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.ctryCd, pMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachFlNm, pMsg.svcMachFlNm);
        ZYPEZDItemValueSetter.setValue(insTMsg.autoCratFlg, pMsg.autoCratFlg);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlStsUpdCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.convInprocFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.iwrCondCd, pMsg.iwrCondCd);
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.istlDt, pMsg.istlDt);
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, pMsg.effFromDt);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachUsgStsCd, pMsg.svcMachUsgStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.prntSerNum, pMsg.prntSerNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachTrxTpCd, pMsg.svcMachTrxTpCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.ownrAcctNum, pMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.ownrLocNum, pMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachQty, pMsg.svcMachQty);
        ZYPEZDItemValueSetter.setValue(insTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.leaseRtrnFeeInclFlg,  ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsKeyAcctFlg,  ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.shrDlrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.enetPlotFlg, ZYPConstant.FLG_OFF_N);
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(insTMsg.indBillToLocNum, getIndBillToLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue()));
        ZYPEZDItemValueSetter.setValue(insTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        insTMsg = setInsSvcByLineBizTpCd(insTMsg, pMsg.mdseCd.getValue(), pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/07/25 CSA Defect#6951
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg);
        ZYPEZDItemValueSetter.setValue(insTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        ZYPEZDItemValueSetter.setValue(insTMsg.finBrCd, brCdBean.getFinBrCd());
        // add end 2016/07/25 CSA Defect#6951
        // START 2023/10/06 K.Watanabe [QC#54186, DEL]
        //// mod start 2016/09/07 CSA Defect#10568
        //if (hasValue(pMsg.svcByLineBizTpCd)) {
        //    // START 2016/09/05 T.Tomita [QC#12471, ADD]
        //    ZYPEZDItemValueSetter.setValue(insTMsg.svcByLineBizTpCd, pMsg.svcByLineBizTpCd);
        //    // END 2016/09/05 T.Tomita [QC#12471, ADD]
        //} else {
        //    ZYPEZDItemValueSetter.setValue(insTMsg.svcByLineBizTpCd, brCdBean.getSvcLineBizCd());
        //}
        //// mod end 2016/09/07 CSA Defect#10568
        // END 2023/10/06 K.Watanabe [QC#54186, DEL]
        // START 2017/03/22 K.Kitachi [QC#15679, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.dummySerFlg, this.dummySerFlg);
        // END 2017/03/22 K.Kitachi [QC#15679, ADD]
        // START 2019/05/07 [QC#50119, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.sldByLineBizTpCd, pMsg.sldByLineBizTpCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlDt, pMsg.istlDt);
        // END 2019/05/07 [QC#50119, ADD]
        // START 2023/07/31 S.Moriai [QC#61632, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.swLicId, pMsg.swLicId);
        // END 2023/07/31 S.Moriai [QC#61632, ADD]

        S21FastTBLAccessor.create(insTMsg);
        String rtnCd = insTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0034E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, insTMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, insTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, insTMsg.svcMachSqNum);
    }

    private void createSvcMachMstrByItemChng(NSZC001001PMsg pMsg) {
        // START 2015/12/09 T.Tomita [CSA QC#951, ADD]
        // IB trackable check
        if (!isInstlBaseCtrlFlg(pMsg)) {
            msgMap.addXxMsgId(NSZM0804E);
            return;
        }
        // END 2015/12/09 T.Tomita [CSA QC#951, ADD]

        SVC_MACH_MSTRTMsg insTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.serNum, pMsg.serNum);
        ZYPEZDItemValueSetter.setValue(insTMsg.mdseCd, pMsg.mdseCd);
        // del start 2016/06/28 CSA Defect#10830
//        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachSqNum, INIT_SVC_MACH_SQ_NUM);
//        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachTpCd, SVC_MACH_TP.MACHINE);
        // del end 2016/06/28 CSA Defect#10830
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlLocOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.autoCratFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.istlStsUpdCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.convInprocFlg, ZYPConstant.FLG_OFF_N);
        insTMsg.iwrCondCd.clear();
        // START 2016/06/29 T.Tomita [QC#11058, MOD]
//        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(insTMsg.effFromDt, pMsg.effFromDt);
        insTMsg.effThruDt.clear();
        // END 2016/06/29 T.Tomita [QC#11058, MOD]
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_INVENTORY);
        ZYPEZDItemValueSetter.setValue(insTMsg.stkStsCd, pMsg.stkStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.curLocNum, pMsg.curLocNum);
        // START 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.shipToCustCd, pMsg.curLocNum);
        // END 2016/01/05 T.Tomita [CSA QC#2169, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachQty, INIT_SVC_MACH_QTY);
        ZYPEZDItemValueSetter.setValue(insTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.leaseRtrnFeeInclFlg,  ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.dsKeyAcctFlg,  ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMaintAvalFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrLocStsCd, pMsg.svcMachMstrLocStsCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.shrDlrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insTMsg.enetPlotFlg, ZYPConstant.FLG_OFF_N);
        // add start 2016/05/13 CSA Defect#4578
        ZYPEZDItemValueSetter.setValue(insTMsg.indCurLocNum, getIndCurLocNum(pMsg.glblCmpyCd.getValue(), pMsg.curLocNum.getValue()));
        // add end 2016/05/13 CSA Defect#4578
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        insTMsg = setInsSvcByLineBizTpCd(insTMsg, pMsg.mdseCd.getValue(), pMsg.istlBySvcPrvdPtyCd.getValue(), pMsg.svcBySvcPrvdPtyCd.getValue(), pMsg.svcMachMstrStsCd.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, ADD]
        // add start 2016/08/29 CSA Defect#10568
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg);
        ZYPEZDItemValueSetter.setValue(insTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        ZYPEZDItemValueSetter.setValue(insTMsg.finBrCd, brCdBean.getFinBrCd());
        // START 2023/10/06 K.Watanabe [QC#54186, DEL]
        //ZYPEZDItemValueSetter.setValue(insTMsg.svcByLineBizTpCd, brCdBean.getSvcLineBizCd());
        // END 2023/10/06 K.Watanabe [QC#54186, DEL]
        // add end 2016/08/29 CSA Defect#10568
        // START 2017/03/22 K.Kitachi [QC#15679, ADD]
        ZYPEZDItemValueSetter.setValue(insTMsg.dummySerFlg, this.dummySerFlg);
        // END 2017/03/22 K.Kitachi [QC#15679, ADD]
        S21FastTBLAccessor.create(insTMsg);
        String rtnCd = insTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0034E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, insTMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, insTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachSqNum, insTMsg.svcMachSqNum);
    }

    private void createSvcConfigMstr(NSZC001001PMsg pMsg, BigDecimal svcMachMstrPk, BigDecimal newMdlId) {
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        if (hasValue(pMsg.svcConfigMstrPk)) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_SQ));
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, tMsg.svcConfigMstrPk);
        }
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        tMsg.svcConfigTpCd.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, newMdlId);
        tMsg.svcSlnSq.clear();
        tMsg.svcSlnNm.clear();
        tMsg.svcSlnCratPsnCd.clear();
        tMsg.svcSlnCratPsnCd.clear();
        tMsg.svcSlnCratDt.clear();
        tMsg.svcSlnUpdPsnCd.clear();
        tMsg.svcSlnUpdDt.clear();
        S21FastTBLAccessor.create(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0035E);
        }
    }

//    private void createSvcConfigMstrDtlForIstl(NSZC001001PMsg pMsg, SVC_CONFIG_MSTR_DTLTMsg exstTMsg, BigDecimal newMdlId) {
//        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_DTL_SQ));
//        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, exstTMsg.svcConfigMstrPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, exstTMsg.svcMachMstrPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.serNum, exstTMsg.serNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, exstTMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.prntSvcMachMstrPk, pMsg.prntSvcMachMstrPk);
//        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, newMdlId);
//        ZYPEZDItemValueSetter.setValue(tMsg.istlDt, exstTMsg.istlDt);
//        tMsg.svcMachRmvDt.clear();
//        ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNum, exstTMsg.ownrAcctNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.ownrLocNum, exstTMsg.ownrLocNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNum, exstTMsg.billToAcctNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, exstTMsg.billToLocNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, exstTMsg.curLocAcctNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, exstTMsg.curLocNum);
//        ZYPEZDItemValueSetter.setValue(tMsg.istlLocOvrdFlg, exstTMsg.istlLocOvrdFlg);
//        ZYPEZDItemValueSetter.setValue(tMsg.locNm, exstTMsg.locNm);
//        ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, exstTMsg.addlLocNm);
//        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, exstTMsg.firstLineAddr);
//        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, exstTMsg.scdLineAddr);
//        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, exstTMsg.thirdLineAddr);
//        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, exstTMsg.frthLineAddr);
//        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, exstTMsg.ctyAddr);
//        ZYPEZDItemValueSetter.setValue(tMsg.stCd, exstTMsg.stCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.postCd, exstTMsg.postCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.rgtnConfigDt, pMsg.slsDt);
//        S21FastTBLAccessor.create(tMsg);
//        String rtnCd = tMsg.getReturnCode();
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//            msgMap.addXxMsgId(NSZM0718E);
//        }
//    }

    private void createSvcConfigMstrDtlForSvcExch(NSZC001001PMsg pMsg) {
        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, pMsg.serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, pMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prntSvcMachMstrPk, pMsg.prntSvcMachMstrPk);
        tMsg.mdlId.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.istlDt, pMsg.istlDt);
        tMsg.svcMachRmvDt.clear();
        ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNum, pMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ownrLocNum, pMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNum, pMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, pMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, pMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, pMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.istlLocOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.locNm, pMsg.locNm);
        ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, pMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, pMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, pMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, pMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, pMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, pMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, pMsg.stCd);
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, pMsg.postCd);
        tMsg.rgtnConfigDt.clear();
        S21FastTBLAccessor.create(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0718E);
        }
    }

    // START 2015/11/09 T.Tomita [QC#474, ADD]
    // mod start 2016/07/04 CSA Defect#10474
    private void createSvcConfigMstrDtl(NSZC001001PMsg pMsg, BigDecimal newMdlId, String slsDt) {
        if (!hasValue(pMsg.svcMachMstrPk) || !hasValue(pMsg.svcConfigMstrPk)) {
            return;
        }

        SVC_MACH_MSTRTMsg machTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (machTMsg == null) {
            return;
        }

        SVC_CONFIG_MSTRTMsg configTMsg = findSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
        if (configTMsg == null) {
            return;
        }
        BigDecimal prntSvcMachMstrPk = configTMsg.svcMachMstrPk.getValue();

        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, machTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, machTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prntSvcMachMstrPk, prntSvcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, newMdlId);
        ZYPEZDItemValueSetter.setValue(tMsg.istlDt, machTMsg.istlDt);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, machTMsg.svcMachRmvDt);
        ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNum, machTMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ownrLocNum, machTMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNum, machTMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, machTMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, machTMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, machTMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.istlLocOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.locNm, machTMsg.locNm);
        ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, machTMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, machTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, machTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, machTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, machTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, machTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, machTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, machTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnConfigDt, slsDt);
        S21FastTBLAccessor.create(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0718E);
            return;
        }
    }
    // mod end 2016/07/04 CSA Defect#10474
    // END 2015/11/09 T.Tomita [QC#474, ADD]

    // START 2015/11/10 T.Tomita [QC#527, MOD]
    private void createSvcConfigMstrDtl(NSZC001001PMsg pMsg, NSZC001001_xxCmptMachListPMsg xxCmptMach, BigDecimal newMdlId, String slsDt) {
        if (!hasValue(xxCmptMach.svcMachMstrPk) || xxCmptMach.svcMachMstrPk.getValue().compareTo(pMsg.svcMachMstrPk.getValue()) == 0) {
            return;
        }

        SVC_MACH_MSTRTMsg machTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), xxCmptMach.svcMachMstrPk.getValue());
        if (machTMsg == null) {
            return;
        }

        BigDecimal prntSvcMachMstrPk = xxCmptMach.svcMachMstrPk.getValue();
        if (SVC_MACH_TP.ACCESSORY.equals(machTMsg.svcMachTpCd.getValue())) {
            // mod start 2016/07/01 CSA Defect#10474
//            SVC_MACH_MSTRTMsg prntMachTMsg = findSvcMachMstrToSerNum(machTMsg.glblCmpyCd.getValue(), machTMsg.prntSerNum.getValue());
//            if (prntMachTMsg != null) {
//                prntSvcMachMstrPk = prntMachTMsg.svcMachMstrPk.getValue();
//            }
            SVC_CONFIG_MSTRTMsg configTMsg = findSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
            if (configTMsg == null) {
                return;
            }
            prntSvcMachMstrPk = configTMsg.svcMachMstrPk.getValue();
            // mod end 2016/07/01 CSA Defect#10474
        }

        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CONFIG_MSTR_DTL_SQ));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, xxCmptMach.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, machTMsg.serNum);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, machTMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prntSvcMachMstrPk, prntSvcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, newMdlId);
        ZYPEZDItemValueSetter.setValue(tMsg.istlDt, machTMsg.istlDt);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, machTMsg.svcMachRmvDt);
        ZYPEZDItemValueSetter.setValue(tMsg.ownrAcctNum, machTMsg.ownrAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.ownrLocNum, machTMsg.ownrLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToAcctNum, machTMsg.billToAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.billToLocNum, machTMsg.billToLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, machTMsg.curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, machTMsg.curLocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.istlLocOvrdFlg, machTMsg.istlLocOvrdFlg);
        ZYPEZDItemValueSetter.setValue(tMsg.locNm, machTMsg.locNm);
        ZYPEZDItemValueSetter.setValue(tMsg.addlLocNm, machTMsg.addlLocNm);
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, machTMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, machTMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, machTMsg.thirdLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, machTMsg.frthLineAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, machTMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, machTMsg.stCd);
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, machTMsg.postCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnConfigDt, slsDt);
        S21FastTBLAccessor.create(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0718E);
            return;
        }
    }
    // END 2015/11/10 T.Tomita [QC#527, MOD]

    // START 2019/11/14 K.Kitachi [QC#54327, MOD]
//    private void createSvcMachMstrTrk(NSZC001001PMsg pMsg, String updFldTxt, String oldVal, String newVal) {
    private void createSvcMachMstrTrk(NSZC001001PMsg pMsg, BigDecimal svcMachMstrPk, String updFldTxt, String oldVal, String newVal) {
    // END 2019/11/14 K.Kitachi [QC#54327, MOD]
        SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal svcMachMstrTrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrTrkPk, svcMachMstrTrkPk);
        // START 2019/11/14 K.Kitachi [QC#54327, MOD]
//        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        // END 2019/11/14 K.Kitachi [QC#54327, MOD]
        ZYPEZDItemValueSetter.setValue(tMsg.trxRgtnDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(tMsg.updFldTxt, updFldTxt);
        ZYPEZDItemValueSetter.setValue(tMsg.oldValTxt, oldVal);
        ZYPEZDItemValueSetter.setValue(tMsg.newValTxt, newVal);
        ZYPEZDItemValueSetter.setValue(tMsg.updUsrId, loginUsrId);
        ZYPEZDItemValueSetter.setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
        S21FastTBLAccessor.create(tMsg);
        String rtnCd = tMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0720E);
        }
    }

    // START 2019/11/14 K.Kitachi [QC#54327, MOD]
//    private void createSvcMachMstrTrk(NSZC001001PMsg pMsg, String updFldTxt, BigDecimal oldVal, BigDecimal newVal) {
    private void createSvcMachMstrTrk(NSZC001001PMsg pMsg, BigDecimal svcMachMstrPk, String updFldTxt, BigDecimal oldVal, BigDecimal newVal) {
    // END 2019/11/14 K.Kitachi [QC#54327, MOD]
        String strOldVal = "";
        String strNewVal = "";
        if (hasValue(oldVal)) {
            strOldVal = oldVal.toString();
        }
        if (hasValue(newVal)) {
            strNewVal = newVal.toString();
        }
        // START 2019/11/14 K.Kitachi [QC#54327, MOD]
//        createSvcMachMstrTrk(pMsg, updFldTxt, strOldVal, strNewVal);
        createSvcMachMstrTrk(pMsg, svcMachMstrPk, updFldTxt, strOldVal, strNewVal);
        // END 2019/11/14 K.Kitachi [QC#54327, MOD]
    }

    private void createSvcMachMstrTrk(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg exstTMsg, SVC_MACH_MSTRTMsg updTMsg) {

        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();

        @SuppressWarnings("unchecked")
        ArrayList[] columnList = tMsg.getSelectColumnList();

        String dbNm;
        String rsNm;
        for (int idx = 12; idx < columnList[0].size(); idx++) {
            dbNm = columnList[0].get(idx).toString();
            rsNm = columnList[1].get(idx).toString();
            if (tMsg.getAttr(dbNm).getType() == EZDItemAttrDefines.TYPE_SEISU_SYOSU) {
               if (checkUpdateSvcMachMstrTrk(exstTMsg.getValueBigDecimal(dbNm, 0), updTMsg.getValueBigDecimal(dbNm, 0))) {
                    // START 2019/11/14 K.Kitachi [QC#54327, MOD]
//                    createSvcMachMstrTrk(pMsg, rsNm, exstTMsg.getValueBigDecimal(dbNm, 0), updTMsg.getValueBigDecimal(dbNm, 0));
                    createSvcMachMstrTrk(pMsg, exstTMsg.svcMachMstrPk.getValue(), rsNm, exstTMsg.getValueBigDecimal(dbNm, 0), updTMsg.getValueBigDecimal(dbNm, 0));
                    // END 2019/11/14 K.Kitachi [QC#54327, MOD]
                }
            } else {
                if (checkUpdateSvcMachMstrTrk(exstTMsg.getValueString(dbNm, 0), updTMsg.getValueString(dbNm, 0))) {
                    // START 2019/11/14 K.Kitachi [QC#54327, MOD]
//                    createSvcMachMstrTrk(pMsg, rsNm, exstTMsg.getValueString(dbNm, 0), updTMsg.getValueString(dbNm, 0));
                    createSvcMachMstrTrk(pMsg, exstTMsg.svcMachMstrPk.getValue(), rsNm, exstTMsg.getValueString(dbNm, 0), updTMsg.getValueString(dbNm, 0));
                    // END 2019/11/14 K.Kitachi [QC#54327, MOD]
                }
            }
        }
    }

    // START 2015/11/09 T.Tomita [QC#474, MOD]
    private void createAndUpdateSvcConfigMstr(NSZC001001PMsg pMsg, BigDecimal svcConfigMstrPk) {
        String mdseCd = pMsg.mdseCd.getValue();
        // START 2015/12/11 T.Tomita [QC#1799, MOD]
        BigDecimal cmpSvcMachMstrPk = null;
        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.xxCmptMachList.no(i).svcMachTpCd.getValue())
                    && mdseCd.equals(pMsg.xxCmptMachList.no(i).mdseCd.getValue())) {
                cmpSvcMachMstrPk = pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue();
            }
        }

        // mod start 2016/07/01 CSA Defect#10474
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        if (!hasValue(cmpSvcMachMstrPk) && hasValue(pMsg.svcMachTpCd)) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
                cmpSvcMachMstrPk = pMsg.svcMachMstrPk.getValue();
            } else if (SVC_MACH_TP.ACCESSORY.equals(pMsg.svcMachTpCd.getValue()) && hasValue(svcConfigMstrPk)) {
                SVC_CONFIG_MSTRTMsg confgTMsg = findSvcConfigMstr(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
                if (confgTMsg != null) {
                    cmpSvcMachMstrPk = confgTMsg.svcMachMstrPk.getValue();
                }
            }
        }
        // END 2015/11/16 T.Tomita [QC#647, ADD]
        // mod start 2016/07/01 CSA Defect#10474

        if (!hasValue(cmpSvcMachMstrPk)) {
            return;
        }
        // END 2015/12/11 T.Tomita [QC#1799, MOD]
        BigDecimal exstMdlId = BigDecimal.ZERO;
        BigDecimal newMdlId = null;
        if (pMsg.svcMachMstrStsCd.getValue().equals(SVC_MACH_MSTR_STS.DEALER_SERVICE)) {
            removeSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
            updateSvcConfigMstrDtlForRmv(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), pMsg.slsDt.getValue());
            return;
        }

        // Delete Pick Config Master
        if (hasValue(pMsg.pickSvcConfigMstrPk)) {
            removeSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.pickSvcConfigMstrPk.getValue());
            updateSvcConfigMstrDtlForRmv(pMsg.glblCmpyCd.getValue(), pMsg.pickSvcConfigMstrPk.getValue(), pMsg.slsDt.getValue());
        }

        SVC_CONFIG_MSTRTMsg exstSvcConfigMstr = findSvcConfigMstrForUpdate(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
        if (exstSvcConfigMstr == null) {
            // Create
            newMdlId = callGetSvcMdlApi();
            setMdlId(newMdlId);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            // SVC_CONFIG_MSTR
            createSvcConfigMstr(pMsg, cmpSvcMachMstrPk, newMdlId);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // Update SVC_MACH_MSTR SVC_CONFIG_MSTR_PK
            updateSvcMachMstrForConfgPk(pMsg);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // SVC_CONFIG_MSTR_DTL(Main Machine)
            createSvcConfigMstrDtl(pMsg, newMdlId, pMsg.slsDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }

            // add start 2016/07/01 CSA Defect#10474
            // SVC_CONFIG_MSTR_DTL(Accessory)
            for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
                // SVC_CONFIG_MSTR_DTL(Accessory)
                createSvcConfigMstrDtl(pMsg, pMsg.xxCmptMachList.no(i), newMdlId, pMsg.slsDt.getValue());
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
            // add end 2016/07/01 CSA Defect#10474
            return;
        }

        // START 2015/11/10 T.Tomita [QC#527, MOD]
        // mod start 2016/07/01 CSA Defect#10474
        boolean configMdlUpdateFlg = false;
        exstMdlId = exstSvcConfigMstr.mdlId.getValue();
        newMdlId = callGetSvcMdlApi();

        if (hasValue(newMdlId) && !exstMdlId.equals(newMdlId)) {
            setMdlId(newMdlId);
            configMdlUpdateFlg = true;
        } else {
            setMdlId(exstMdlId);
        }

        if (!existsConfigMstrDtl(pMsg)) {
            // Create Service Configuration Master Detail
            createSvcConfigMstrDtl(pMsg, newMdlId, pMsg.slsDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        List<Map<String, Object>> svcMachMstrByShipList = findSvcMachMstrByShip(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk);
        // mod end 2016/07/01 CSA Defect#10474

        boolean updateFlg;
        // mod start 2016/04/26 CSA Defect#4890
        String mainMachRmvDt;
        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            updateFlg = false;
            BigDecimal newSvcMachMstrPk = pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue();
            String newSvcMachRmvDt = pMsg.xxCmptMachList.no(i).effThruDt.getValue();

            // START 2015/12/11 T.Tomita [QC#1799, ADD]
            if (!hasValue(newSvcMachMstrPk)) {
                continue;
            }
            // END 2015/12/11 T.Tomita [QC#1799, ADD]
            mainMachRmvDt = null;
            for (int j = 0; j < svcMachMstrByShipList.size(); j++) {
                BigDecimal exstSvcMachMstrPk = (BigDecimal) svcMachMstrByShipList.get(j).get("SVC_MACH_MSTR_PK");
                String svcMachTpCd = (String) svcMachMstrByShipList.get(j).get("SVC_MACH_TP_CD");
                if (exstSvcMachMstrPk.compareTo(newSvcMachMstrPk) == 0) {
                    updateFlg = true;
                    if (hasValue(newSvcMachRmvDt) && ZYPDateUtil.compare(newSvcMachRmvDt, pMsg.slsDt.getValue()) <= 0) {
                        updateSvcConfigMstrDtlForTrmn(pMsg.glblCmpyCd.getValue(), newSvcMachMstrPk, newSvcMachRmvDt);
                        if (msgMap.getMsgMgr().isXxMsgId()) {
                            return;
                        }

                        updateSvcMachMstrForRemove(pMsg.glblCmpyCd.getValue(), newSvcMachMstrPk);
                        if (msgMap.getMsgMgr().isXxMsgId()) {
                            return;
                        }

                        if (hasValue(svcMachTpCd) && SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                            mainMachRmvDt = newSvcMachRmvDt;
                        }
                    }
                } else if (hasValue(mainMachRmvDt)) {
                    // Delete Accessory Configration of Main Machine Delete
                    updateSvcConfigMstrDtlForTrmn(pMsg.glblCmpyCd.getValue(), exstSvcMachMstrPk, mainMachRmvDt);
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        return;
                    }

                    updateSvcMachMstrForRemove(pMsg.glblCmpyCd.getValue(), exstSvcMachMstrPk);
                    if (msgMap.getMsgMgr().isXxMsgId()) {
                        return;
                    }
                }
            }

            if (!updateFlg) {
                createSvcConfigMstrDtl(pMsg, pMsg.xxCmptMachList.no(i), newMdlId, pMsg.slsDt.getValue());
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                updateSvcMachMstrForCompMach(pMsg, pMsg.xxCmptMachList.no(i), cmpSvcMachMstrPk);
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
            }
        }
        // mod end 2016/04/26 CSA Defect#4890

        if (configMdlUpdateFlg) {
            updateSvcConfigMstr(pMsg.glblCmpyCd.getValue(), svcConfigMstrPk, newMdlId);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
            updateSvcMachMstrForCompMachList(pMsg, cmpSvcMachMstrPk);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
        // END 2015/11/10 T.Tomita [QC#527, MOD]
    }
    // END 2015/11/09 T.Tomita [QC#474, MOD]

    private void createAndUpdateSvcPhsMtr(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg updTMsg, BigDecimal newMdlId) {
        // START 2015/12/11 T.Tomita [QC#1877, ADD]
        if (!isMtrReqMdl(pMsg.glblCmpyCd.getValue(), newMdlId)) {
            return;
        }
        // END 2015/12/11 T.Tomita [QC#1877, ADD]
        // mod start 2016/09/09 CSA Defect#13902
        List<Map<String, Object>> dsMdlMtrList = findDsModelMeter(pMsg.glblCmpyCd.getValue(), newMdlId);
        // mod end 2016/09/09 CSA Defect#13902
        if (dsMdlMtrList.size() == 0) {
            msgMap.addXxMsgId(NSZM0036E);
            return;
        }
        // Mod Start 2018/08/22 QC#18190
//        SVC_PHYS_MTRTMsg svcPhsMtrTMsg = findSvcPhysMtr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), (BigDecimal) dsMdlMtrList.get(0).get("DS_MDL_MTR_PK"));
        SVC_PHYS_MTRTMsg svcPhsMtrTMsg = findSvcPhysMtr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        // Mod End 2018/08/22 QC#18190
        if (svcPhsMtrTMsg == null) {
            createSvcPhsMtr(pMsg, dsMdlMtrList);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }

        updTMsg = findSvcMachMstrForUpdate(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        updateSvcMachMstrForSvcPhsMtr(updTMsg, dsMdlMtrList);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void createAndUpdateSvcPhsMtr(NSZC001001PMsg pMsg, BigDecimal newMdlId) {
        // START 2015/12/11 T.Tomita [QC#1877, ADD]
        if (!isMtrReqMdl(pMsg.glblCmpyCd.getValue(), newMdlId)) {
            return;
        }
        // END 2015/12/11 T.Tomita [QC#1877, ADD]
        // mod start 2016/09/09 CSA Defect#13902
        List<Map<String, Object>> dsMdlMtrList = findDsModelMeter(pMsg.glblCmpyCd.getValue(), newMdlId);
        // mod end 2016/09/09 CSA Defect#13902
        if (dsMdlMtrList.size() == 0) {
            msgMap.addXxMsgId(NSZM0036E);
            return;
        }
        // Mod Start 2018/08/22 QC#18190
//        SVC_PHYS_MTRTMsg svcPhsMtrTMsg = findSvcPhysMtr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), (BigDecimal) dsMdlMtrList.get(0).get("DS_MDL_MTR_PK"));
        SVC_PHYS_MTRTMsg svcPhsMtrTMsg = findSvcPhysMtr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        // Mod End 2018/08/22 QC#18190
        if (svcPhsMtrTMsg == null) {
            createSvcPhsMtr(pMsg, dsMdlMtrList);
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return;
            }
        }
    }

    private void createSvcPhsMtr(NSZC001001PMsg pMsg,  List<Map<String, Object>> dsMdlMtrList) {
        String rtnCd = "";
        for (int i = 0; i < dsMdlMtrList.size(); i++) {
            SVC_PHYS_MTRTMsg svcPhysMtrTMsg = new SVC_PHYS_MTRTMsg();
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            BigDecimal svcPhysMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PHYS_MTR_SQ);
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.svcPhysMtrPk, svcPhysMtrPk);
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mtrGrpPk, (BigDecimal) dsMdlMtrList.get(i).get("MTR_GRP_PK"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.dsMdlMtrPk, (BigDecimal) dsMdlMtrList.get(i).get("DS_MDL_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mdlMtrId, (String) dsMdlMtrList.get(i).get("MDL_MTR_ID"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mtrClsTpCd, (String) dsMdlMtrList.get(i).get("MTR_CLS_TP_CD"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.svcMtrAvalFlg, (String) dsMdlMtrList.get(i).get("SVC_MTR_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.bllgMtrAvalFlg, (String) dsMdlMtrList.get(i).get("BLLG_MTR_AVAL_FLG"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.maxMtrCnt, (BigDecimal) dsMdlMtrList.get(i).get("MAX_MTR_CNT"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.avgMlyCopyVolCnt, (BigDecimal) dsMdlMtrList.get(i).get("AVG_MLY_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.maxMlyCopyVolCnt, (BigDecimal) dsMdlMtrList.get(i).get("MAX_MLY_COPY_VOL_CNT"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mdlMtrLbCd, (String) dsMdlMtrList.get(i).get("MDL_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mdlMtrLbNoteTxt, (String) dsMdlMtrList.get(i).get("MDL_MTR_LB_NOTE_TXT"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.bllgMtrId, (String) dsMdlMtrList.get(i).get("BLLG_MTR_ID"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.bllgMtrLbCd, (String) dsMdlMtrList.get(i).get("BLLG_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mtrEntryMndFlg, (String) dsMdlMtrList.get(i).get("MTR_ENTRY_MND_FLG"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.mtrMultRate, (BigDecimal) dsMdlMtrList.get(i).get("MTR_MULT_RATE"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.fleetMdlMtrLbCd, (String) dsMdlMtrList.get(i).get("FLEET_MDL_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.aggrMdlMtrLbCd, (String) dsMdlMtrList.get(i).get("AGGR_MDL_MTR_LB_CD"));
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.actvFlg, (String) dsMdlMtrList.get(i).get("ACTV_FLG"));
            // START 2017/09/04 T.Kanasaka [QC#15134,ADD]
            ZYPEZDItemValueSetter.setValue(svcPhysMtrTMsg.cntrDigitNum, (BigDecimal) dsMdlMtrList.get(i).get("CNTR_DIGIT_NUM"));
            // END 2017/09/04 T.Kanasaka [QC#15134,ADD]
            svcPhysMtrTMsg.effFromDt.clear();
            svcPhysMtrTMsg.effThruDt.clear();
            S21FastTBLAccessor.create(svcPhysMtrTMsg);
            rtnCd = svcPhysMtrTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0720E);
            }
        }
    }

    private void createSvcNonPrfTech(String glblCmpyCd, BigDecimal svcMachMstrPk, NSZC001001_xxNonPrfTechListPMsg pMsg) {
        SVC_NON_PRF_TECHTMsg insTMsg = new SVC_NON_PRF_TECHTMsg();
        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.svcNonPrfTechPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_NON_PRF_TECH_SQ));
        ZYPEZDItemValueSetter.setValue(insTMsg.svcMachMstrPk, svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.nonPrfTechCd, pMsg.nonPrfTechCd);
        ZYPEZDItemValueSetter.setValue(insTMsg.effThruDt, pMsg.effThruDt);
        S21FastTBLAccessor.create(insTMsg);
        String rtnCd = insTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            msgMap.addXxMsgId(NSZM0722E);
            return;
        }
    }

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    private SVC_MACH_MSTRTMsg setInsSvcByLineBizTpCd(SVC_MACH_MSTRTMsg insTMsg, String mdseCd, String istlBySvcPrvdPtyCd, String svcBySvcPrvdPtyCd, String svcMachMstrStsCd) {
        if (istlBySvcPrvdPtyCd != null && !istlBySvcPrvdPtyCd.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(insTMsg.istlBySvcPrvdPtyCd, istlBySvcPrvdPtyCd);
        } else {
            String svcPrvdPty = getSvcPrvdPty(findLineBizReln(insTMsg.glblCmpyCd.getValue(), mdseCd));
            if (svcPrvdPty != null) {
                ZYPEZDItemValueSetter.setValue(insTMsg.istlBySvcPrvdPtyCd, svcPrvdPty);
            }
        }
        if (svcBySvcPrvdPtyCd != null && !svcBySvcPrvdPtyCd.isEmpty()) {
            ZYPEZDItemValueSetter.setValue(insTMsg.svcBySvcPrvdPtyCd,svcBySvcPrvdPtyCd);
        } else {
            String svcPrvdPty = getSvcPrvdPty(findLineBizReln(insTMsg.glblCmpyCd.getValue(), mdseCd));
            if (svcPrvdPty != null) {
                ZYPEZDItemValueSetter.setValue(insTMsg.svcBySvcPrvdPtyCd, svcPrvdPty);
            }
        }
        SVC_PRVD_PTYTMsg svcPrvdPtyTMsg = new SVC_PRVD_PTYTMsg();
        if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrStsCd)) {
            svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(insTMsg.glblCmpyCd.getValue(), insTMsg.istlBySvcPrvdPtyCd.getValue());
        } else {
            svcPrvdPtyTMsg = findSvcPrvdPtyTMsg(insTMsg.glblCmpyCd.getValue(), insTMsg.svcBySvcPrvdPtyCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(insTMsg.svcByLineBizTpCd, svcPrvdPtyTMsg.lineBizTpCd);
        return insTMsg;
    }

    private String getSvcPrvdPty (String svcLineBizCd) {
        if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
            return SVC_PRVD_PTY.ESS_DIRECT;
        } else if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
            return SVC_PRVD_PTY.LFS_DIRECT;
        } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
            return SVC_PRVD_PTY.PPS_DIRECT;
        }
        return null;
    }
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]

    private boolean checkUpdateSvcMachMstrTrk(String exstVal, String updVal) {
        boolean rtnVal = false;
        if (hasValue(exstVal)) {
            if (hasValue(updVal)) {
                if (!exstVal.equals(updVal)) {
                    rtnVal = true;
                }
            } else {
                rtnVal = true;
            }
        } else {
            if (hasValue(updVal)) {
                rtnVal = true;
            }
        }
        return rtnVal;
    }

    private boolean checkUpdateSvcMachMstrTrk(BigDecimal exstVal, BigDecimal updVal) {
        boolean rtnVal = false;
        if (hasValue(exstVal)) {
            if (hasValue(updVal)) {
                if (exstVal.compareTo(updVal) != 0) {
                    rtnVal = true;
                }
            } else {
                rtnVal = true;
            }
        } else {
            if (hasValue(updVal)) {
                rtnVal = true;
            }
        }
        return rtnVal;
    }

    private void removeSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg exstConfigTMsg = findSvcConfigMstrForUpdate(glblCmpyCd, svcConfigMstrPk);
        if (exstConfigTMsg != null) {
            EZDTBLAccessor.logicalRemove(exstConfigTMsg);
            String rtrnCd = exstConfigTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM0038E);
            }
        }
    }

    private void removeSvcNonPrfTech(String glblCmpyCd, BigDecimal svcNonPrfTechPk) {
        SVC_NON_PRF_TECHTMsg tMsg = findSvcNonPrfTechForUpdate(glblCmpyCd, svcNonPrfTechPk);
        if (tMsg != null) {
            EZDTBLAccessor.logicalRemove(tMsg);
            String rtnCd = tMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0723E);
            }
        }
    }

    // START 2017/10/16 M.Kidokoro [QC#20246, ADD]
    private void removeSvcMachCtacPsn(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        if (!hasValue(svcMachMstrPk)) {
            return;
        }
        SVC_MACH_CTAC_PSNTMsgArray tMsgArray = findSvcMachCtacPsnForUpdate(glblCmpyCd, svcMachMstrPk);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            EZDTBLAccessor.logicalRemove(tMsgArray.no(i));
            String rtrnCd = tMsgArray.no(i).getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgId(NSZM1309E);
                return;
            }
        }
    }
    // END 2017/10/16 M.Kidokoro [QC#20246, ADD]

    private void setDummySerNum(NSZC001001PMsg pMsg) {
        // START 2017/03/22 K.Kitachi [QC#15679, ADD]
        this.dummySerFlg = ZYPConstant.FLG_OFF_N;
        // END 2017/03/22 K.Kitachi [QC#15679, ADD]

        // START 2015/11/25 T.Tomita [QC#970, MOD]
        if (ProcessMode.ITEM_CHANGE.code.equals(pMsg.xxModeCd.getValue())) {
            BigDecimal svcMachMstrPk = findSvcMachMstrForItemChngForCheckDummySerNum(pMsg.glblCmpyCd.getValue(), pMsg.serNum.getValue());
            if (svcMachMstrPk == null) {
                return;
            }
        }

        MDSETMsg mdseTMsg = findMdse(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
        if (mdseTMsg == null) {
            msgMap.addXxMsgId(NSZM0583E);
            return;
        }

        // Del Start 2018/02/27 QC#24362
//        // INVTY_CTRL_FLG Check
//        if (!ZYPConstant.FLG_ON_Y.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
//            // Non Serialize Item
//            pMsg.serNum.clear();
//            return;
//        }
        // Del End 2018/02/27 QC#24362

        // START 2016/01/04 T.Tomita [QC#2170, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(mdseTMsg.rcvSerTakeFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            // Non Serialize Item
            pMsg.serNum.clear();
            return;
        }
        // END 2016/01/04 T.Tomita [QC#2170, ADD]

        String dummySerNum = "";
        if (hasValue(pMsg.serNum)) {
            dummySerNum = pMsg.serNum.getValue();
        } else {
            if (pMsg.xxCmptMachList.getValidCount() > 0) {
                BigDecimal newMdlId = callGetSvcMdlApi();
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return;
                }
                MDL_NMTMsg mdlNmTMsg = findMdlNm(pMsg.glblCmpyCd.getValue(), newMdlId);
                if (mdlNmTMsg == null) {
                    msgMap.addXxMsgId(NSZM0090E);
                    return;
                }
                dummySerNum = mdlNmTMsg.t_MdlNm.getValue();
            } else {
                // START 2015/12/14 T.Tomita [QC#1800, MOD]
                dummySerNum = mdseTMsg.mdseCd.getValue();
                // END 2015/12/14 T.Tomita [QC#1800, MOD]
            }
            // START 2017/03/22 K.Kitachi [QC#15679, ADD]
            this.dummySerFlg = ZYPConstant.FLG_ON_Y;
            // END 2017/03/22 K.Kitachi [QC#15679, ADD]
        }

        // Serial Number Exists Check
        // START 2016/12/22 K.Kojima [QC#16600,MOD]
        // SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstrToSerNum(pMsg.glblCmpyCd.getValue(), dummySerNum);
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstrToSerNum(pMsg.glblCmpyCd.getValue(), dummySerNum, pMsg.mdseCd.getValue());
        // END 2016/12/22 K.Kojima [QC#16600,MOD]
        if (svcMachMstrTMsg == null) {
            ZYPEZDItemValueSetter.setValue(pMsg.serNum, dummySerNum);
            return;
        }

        int dummySqNum = 0;
        // START 2016/12/22 K.Kojima [QC#16600,MOD]
        // SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = findSvcMachMstrForDummySerNum(pMsg.glblCmpyCd.getValue(), dummySerNum + DIV_DUMMY_SER_NUM);
        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = findSvcMachMstrForDummySerNum(pMsg.glblCmpyCd.getValue(), dummySerNum + DIV_DUMMY_SER_NUM, pMsg.mdseCd.getValue());
        // END 2016/12/22 K.Kojima [QC#16600,MOD]

        if (svcMachMstrTMsgArray.getValidCount() > 0) {
            String checkSerNum = "";
            int checkDummySqNum = 0;
            for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {
                svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) svcMachMstrTMsgArray.get(i);
                checkSerNum = svcMachMstrTMsg.serNum.getValue().substring(dummySerNum.length() + 1);
                if (isNumberCheck(checkSerNum)) {
                    if (checkDummySqNum < Integer.parseInt(checkSerNum)) {
                        checkDummySqNum = Integer.parseInt(checkSerNum);
                    }
                }
            }
            dummySqNum = checkDummySqNum + 1;
        }
        dummySerNum = dummySerNum.concat(DIV_DUMMY_SER_NUM).concat(Integer.toString(dummySqNum));
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, dummySerNum);
        // END 2015/11/25 T.Tomita [QC#970, MOD]
    }

    // mod start 2016/09/09 CSA Defect#13902
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> findDsModelMeter(String glblCmpyCd, BigDecimal newMdlId) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
//        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("mdlId", newMdlId);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("findDsModelMeter", ssmPrm);
    }
    // mod end 2016/09/09 CSA Defect#13902

    private MDL_NMTMsg findMdlNm(String glblCmpyCd, BigDecimal newMdlId) {
        MDL_NMTMsg prmTMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.t_GlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.t_MdlId, newMdlId);
        return (MDL_NMTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private MDSETMsg findMdse(String glblCmpyCd, String mdseCd) {
        if (!hasValue(mdseCd)) {
            return null;
        }
        MDSETMsg prmTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private SVC_CONFIG_MSTRTMsg findSvcConfigMstrForUpdate(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        if (!hasValue(svcConfigMstrPk)) {
            return null;
        }
        SVC_CONFIG_MSTRTMsg prmTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    // add start 2016/07/01 CSA Defect#10474
    private SVC_CONFIG_MSTRTMsg findSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg prmTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // add end 2016/07/01 CSA Defect#10474

    private SVC_CONFIG_MSTR_DTLTMsgArray findSvcConfigMstrDtlForRmv(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_CONFIG_MSTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private SVC_CONFIG_MSTR_DTLTMsgArray findSvcConfigMstrDtlBySvcMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_CONFIG_MSTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    private SVC_CONFIG_MSTR_DTLTMsgArray findSvcConfigMstrDtlForRgtnConfig(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_CONFIG_MSTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    // add start 2016/10/13 CSA Defect#14898
    private SVC_CONFIG_MSTR_DTLTMsgArray findSvcConfigMstrDtlForMach(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg tMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        // START 2017/10/23 K.Kitachi [QC#21796, MOD]
//        tMsg.setSQLID("005");
        tMsg.setSQLID("002");
        // END 2017/10/23 K.Kitachi [QC#21796, MOD]
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_CONFIG_MSTR_DTLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }
    // add end 2016/10/13 CSA Defect#14898

    // START 2015/11/09 T.Tomita [QC#474, ADD]
    // START 2016/12/22 K.Kojima [QC#16600,MOD]
    // private SVC_MACH_MSTRTMsg findSvcMachMstrToSerNum(String glblCmpyCd, String serNum) {
    private SVC_MACH_MSTRTMsg findSvcMachMstrToSerNum(String glblCmpyCd, String serNum, String mdseCd) {
    // END 2016/12/22 K.Kojima [QC#16600,MOD]
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        // START 2016/12/22 K.Kojima [QC#16600,MOD]
        // prmTMsg.setSQLID("002");
        prmTMsg.setSQLID("001");
        // END 2016/12/22 K.Kojima [QC#16600,MOD]
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("serNum01", serNum);
        // START 2016/12/22 K.Kojima [QC#16600,ADD]
        prmTMsg.setConditionValue("mdseCd01", mdseCd);
        // END 2016/12/22 K.Kojima [QC#16600,ADD]
        SVC_MACH_MSTRTMsgArray resultList = (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
        SVC_MACH_MSTRTMsg result = null;
        if (resultList.length() > 0) {
            result = resultList.no(0);
        }
        return result;
    }
    // START 2015/11/09 T.Tomita [QC#474, ADD]

    private SVC_MACH_MSTRTMsg findSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private SVC_MACH_MSTRTMsg findSvcMachMstrForUpdate(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(prmTMsg);
    }

    private SVC_NON_PRF_TECHTMsg findSvcNonPrfTechForUpdate(String glblCmpyCd, BigDecimal svcNonPrfTechPk) {
        SVC_NON_PRF_TECHTMsg prmTMsg = new SVC_NON_PRF_TECHTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcNonPrfTechPk, svcNonPrfTechPk);
        return (SVC_NON_PRF_TECHTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    private CPO_DTLTMsg findCpoDtl(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        CPO_DTLTMsg prmTMsg = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cpoOrdNum, cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cpoDtlLineNum, cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(prmTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        return (CPO_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private SVC_PRVD_PTYTMsg findSvcPrvdPtyTMsg(String glblCmpyCd, String svcPrvdPtyCd) {
        SVC_PRVD_PTYTMsg prmTMsg = new SVC_PRVD_PTYTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcPrvdPtyCd, svcPrvdPtyCd);
        return (SVC_PRVD_PTYTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]

    // mod start 2016/10/13 CSA Defect#14868
    private List<Map<String, Object>> findSvcExchngOrd(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        prmMap.put("orgCatgCtxTpCd", "SERVICE_EXCHANGE");
        // START 2017/10/23 K.Kitachi [QC#21796, ADD]
        prmMap.put("svcMachTpCdIsMach", SVC_MACH_TP.MACHINE);
        prmMap.put("svcMachTpCdIsAcc", SVC_MACH_TP.ACCESSORY);
        // END 2017/10/23 K.Kitachi [QC#21796, ADD]
        // Add Start 2018/06/07 QC#26482
        prmMap.put("ordHdrStsCd", ORD_HDR_STS.VALIDATED);
        // Add End 2018/06/07 QC#26482
        // Add Start 2018/06/13 QC#23428
        prmMap.put("canceled", DS_CONTR_DTL_STS.CANCELLED);
        prmMap.put("expired", DS_CONTR_DTL_STS.EXPIRED);
        prmMap.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.IB_UPDATE);
        prmMap.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
        prmMap.put("success", DS_CONTR_INTFC_STS.SUCCESS);
        // Add End 2018/06/13 QC#23428
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("findSvcExchngOrd", prmMap);
    }
    // mod end 2016/10/13 CSA Defect#14868

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> findSvcMachMstrByShip(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcConfigMstrPk", svcConfigMstrPk);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("findSvcMachMstrByShip", prmMap);
    }

    // add start 2016/04/13 CSA Defect#4903
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> findSvcConfigMstrDtlForUpd(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        prmMap.put("svcConfigMstrPk", svcConfigMstrPk);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("findSvcConfigMstrDtlForUpd", prmMap);
    }
    // add end 2016/04/13 CSA Defect#4903

    private BigDecimal findSvcConfigMstrForUpdTrmn(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        prmMap.put("svcConfigMstrPk", svcConfigMstrPk);
        return (BigDecimal) ssmBatClnt.queryObject("findSvcConfigMstrForUpdTrmn", prmMap);
    }

    // START 2017/01/12 K.Kitachi [QC#16962, MOD]
    private BigDecimal findNextSvcMachSqNum(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        if (hasValue(svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(glblCmpyCd, svcMachMstrPk);
            if (svcMachMstrTMsg != null && hasValue(svcMachMstrTMsg.svcMachSqNum) && hasValue(svcMachMstrTMsg.svcConfigMstrPk) && svcConfigMstrPk.compareTo(svcMachMstrTMsg.svcConfigMstrPk.getValue()) == 0) {
                return svcMachMstrTMsg.svcMachSqNum.getValue();
            }
        }
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcConfigMstrPk", svcConfigMstrPk);
        return (BigDecimal) ssmBatClnt.queryObject("findNextCmptSqNum", prmMap);
    }
    // END 2017/01/12 K.Kitachi [QC#16962, MOD]

    private BigDecimal findConversionInProcess(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcMachMstrStsCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcMachMstrPk", svcMachMstrPk);
        prmMap.put("svcMachMstrStsCd", svcMachMstrStsCd);
        return (BigDecimal) ssmBatClnt.queryObject("findConversionInProcess", prmMap);
    }

    private SVC_MACH_MSTRTMsgArray findSvcMachMstrBySvcConfigMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("016");
        tMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    // START 2016/12/22 K.Kojima [QC#16600,MOD]
    // private SVC_MACH_MSTRTMsgArray findSvcMachMstrForDummySerNum(String glblCmpyCd, String serNum) {
    private SVC_MACH_MSTRTMsgArray findSvcMachMstrForDummySerNum(String glblCmpyCd, String serNum, String mdseCd) {
    // END 2016/12/22 K.Kojima [QC#16600,MOD]
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        // START 2016/12/22 K.Kojima [QC#16600,MOD]
        // tMsg.setSQLID("007");
        tMsg.setSQLID("018");
        // END 2016/12/22 K.Kojima [QC#16600,MOD]
        tMsg.setConditionValue("serNum01", serNum.concat(PERCENT));
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // START 2016/12/22 K.Kojima [QC#16600,ADD]
        tMsg.setConditionValue("mdseCd01", mdseCd);
        // END 2016/12/22 K.Kojima [QC#16600,ADD]
        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private SVC_NON_PRF_TECHTMsgArray findSvcNonPrfTechByTechCd(String glblCmpyCd, BigDecimal svcMachMstrPk, String nonPrfTechCd) {
        SVC_NON_PRF_TECHTMsg tMsg = new SVC_NON_PRF_TECHTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        tMsg.setConditionValue("nonPrfTechCd01", nonPrfTechCd);
        return (SVC_NON_PRF_TECHTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private BigDecimal findSvcMachMstrForCheckExstWH(String glblCmpyCd, String serNum) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("serNum", serNum);
        prmMap.put("svcMachMstrStsList", getSvcMachStsCd());
        return (BigDecimal) ssmBatClnt.queryObject("findSvcMachMstrForCheckExstWH", prmMap);
    }

    private BigDecimal findSvcMachMstrForItemChngForCheckDummySerNum(String glblCmpyCd, String serNum) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("serNum", serNum);
        prmMap.put("svcMachMstrStsList", getSvcMachStsCd());
        return (BigDecimal) ssmBatClnt.queryObject("findSvcMachMstrForItemChngForCheckDummySerNum", prmMap);
    }

    // START 2023/10/06 K.Watanabe [QC#54186, ADD]
    @SuppressWarnings("unchecked")
    private Map<String, Object> findDsCpoIstlInfo(String glblCmpyCd, BigDecimal dsCpoConfigPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("dsCpoConfigPk", dsCpoConfigPk);
        return (Map<String, Object>) ssmBatClnt.queryObject("findDsCpoIstlInfo", prmMap);
    }

    private String findLineBizReln(String glblCmpyCd, String mdseCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("mdseCd", mdseCd);
        return (String) ssmBatClnt.queryObject("findLineBizReln", prmMap);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getSvcLineBizCdforDICheck(String glblCmpyCd, BigDecimal dsCpoConfigPk, String mdseCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("dsCpoConfigPk", dsCpoConfigPk);
        prmMap.put("mdseCd", mdseCd);
        // START 2024/04/04 t.aizawa [QC#54186, MOD]
        // List<String> configTpCdList = new ArrayList<String>();
        // configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        // configTpCdList.add(CONFIG_TP.SERVICE_EXCHANGE);
        List<String> configTpCdList = getVarCharConstValueToList(glblCmpyCd, NSZC0010_INCL_CONFIG_TP_CD);
        if (configTpCdList == null || configTpCdList.isEmpty()) {
            configTpCdList = new ArrayList<String>();
            configTpCdList.add(CONFIG_TP.NEW);
            configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
            configTpCdList.add(CONFIG_TP.EXISTING);
            configTpCdList.add(CONFIG_TP.TO_SALES_CONVERSION);
            configTpCdList.add(CONFIG_TP.CREDIT_REBILL_CONFIG);
            configTpCdList.add(CONFIG_TP.SERVICE_EXCHANGE);
        }
        // END   2024/04/04 t.aizawa [QC#54186, MOD]
        prmMap.put("configTpCdList", configTpCdList);
        prmMap.put("mdseItemTpCdAccessory",MDSE_ITEM_TP.ACCESSORY);
        prmMap.put("mdseItemTpCdSoftware",MDSE_ITEM_TP.SOFTWARE);
        return (Map<String, Object>) ssmBatClnt.queryObject("getSvcLineBizCdforDICheck", prmMap);
    }
    // END 2023/10/06 K.Watanabe [QC#54186, ADD]

    // START 2024/04/04 t.aizawa [QC#54186, ADD]
    /**
     * Get Variable Character Constant Converted to List
     * @param glblCmpyCd String
     * @param constKey String
     * @return List<String>
     */
    private List<String> getVarCharConstValueToList(String glblCmpyCd, String constKey) {
        String constVal = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);
        if (!hasValue(constVal)) {
            return new ArrayList<String>();
        }
        String[] constValArray = constVal.split(DELIMITER_COMMA);
        return Arrays.asList(constValArray);
    }

    /**
     * Get Service Line of Business Code by Merchandise Code
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return String
     */
    private String getSvcLineBizCdByMdseCd(String glblCmpyCd, String mdseCd) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("mdseCd", mdseCd);
        return (String) ssmBatClnt.queryObject("getSvcLineBizCdByMdseCd", prmMap);
    }
    // END   2024/04/04 t.aizawa [QC#54186, ADD]

    // Mod Start 2018/08/22 QC#18190
//    private SVC_PHYS_MTRTMsg findSvcPhysMtr(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsMdlMtrPk) {
    private SVC_PHYS_MTRTMsg findSvcPhysMtr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_PHYS_MTRTMsg tMsg = new SVC_PHYS_MTRTMsg();
//        tMsg.setSQLID("001");
//        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
//        tMsg.setConditionValue("dsMdlMtrPk01", dsMdlMtrPk);
//        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setSQLID("002");
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        SVC_PHYS_MTRTMsgArray tMsgArray = (SVC_PHYS_MTRTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }
    // Mod End 2018/08/22 QC#18190

    // START 2017/10/16 M.Kidokoro [QC#20246, ADD]
    private SVC_MACH_CTAC_PSNTMsgArray findSvcMachCtacPsnForUpdate(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        return (SVC_MACH_CTAC_PSNTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }
    // END 2017/10/16 M.Kidokoro [QC#20246, ADD]

    private BigDecimal callGetSvcMdlApi() {
        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        int cntAccessory = 0;
        NSZC048001 svcMdlApi = new NSZC048001();
        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, pMsg.slsDt);
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.xxCmptMachList.no(i).svcMachTpCd.getValue()) && !hasValue(pMsg.xxCmptMachList.no(i).effThruDt)) {
                ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, pMsg.xxCmptMachList.no(i).mdseCd);
            } else if (SVC_MACH_TP.ACCESSORY.equals(pMsg.xxCmptMachList.no(i).svcMachTpCd.getValue()) && !hasValue(pMsg.xxCmptMachList.no(i).effThruDt)) {
                ZYPEZDItemValueSetter.setValue(svcMdlPMsg.xxChildMdseCdList.no(cntAccessory).childMdseCd, pMsg.xxCmptMachList.no(i).mdseCd);
                cntAccessory++;
            }
        }
        // START 2016/01/04 T.Tomita [QC#2113, ADD]
        svcMdlPMsg.xxChildMdseCdList.setValidCount(cntAccessory);
        // START 2016/01/04 T.Tomita [QC#2113, ADD]

        if (!hasValue(svcMdlPMsg.prntMdseCd)) {
            return null;
        }
        // END 2015/11/16 T.Tomita [QC#647, MOD]

        svcMdlApi.execute(svcMdlPMsg, onBatType);
        if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
            }
        }

        return svcMdlPMsg.mdlId.getValue();
    }

//    private BigDecimal callGetSvcMdlApi(SVC_MACH_MSTRTMsgArray tMsgArray) {
//        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();
//
//        int cntAccessory = 0;
//        NSZC048001 svcMdlApi = new NSZC048001();
//        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
//        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, pMsg.slsDt);
//        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
//            SVC_MACH_MSTRTMsg tMsg = tMsgArray.no(i);
//            if (SVC_MACH_TP.MACHINE.equals(tMsg.svcMachTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, tMsg.mdseCd);
//            } else if (SVC_MACH_TP.ACCESSORY.equals(tMsg.svcMachTpCd.getValue())) {
//                ZYPEZDItemValueSetter.setValue(svcMdlPMsg.xxChildMdseCdList.no(cntAccessory).childMdseCd, tMsg.mdseCd);
//                cntAccessory++;
//            }
//        }
//        // START 2016/01/04 T.Tomita [QC#2113, ADD]
//        svcMdlPMsg.xxChildMdseCdList.setValidCount(cntAccessory);
//        // START 2016/01/04 T.Tomita [QC#2113, ADD]
//
//        svcMdlApi.execute(svcMdlPMsg, onBatType);
//        if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlPMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
//            }
//        }
//        return svcMdlPMsg.mdlId.getValue();
//    }

    // Mod Start 2018/03/02 QC#23425
    private BigDecimal callGetSvcMdlApiForSvcExch() {
        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();
        NSZC048001 svcMdlApi = new NSZC048001();
        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcMdlPMsg.slsDt, pMsg.slsDt);

        int childIdx = 0;
        for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
            if (SVC_MACH_TP.MACHINE.equals(pMsg.xxCmptMachList.no(i).svcMachTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(svcMdlPMsg.prntMdseCd, pMsg.xxCmptMachList.no(i).mdseCd);
            } else {
                ZYPEZDItemValueSetter.setValue(svcMdlPMsg.xxChildMdseCdList.no(childIdx).childMdseCd, pMsg.xxCmptMachList.no(i).mdseCd);
                childIdx++;
            }
        }
        svcMdlPMsg.xxChildMdseCdList.setValidCount(childIdx);

        svcMdlApi.execute(svcMdlPMsg, onBatType);
        if (S21ApiUtil.isXxMsgId(svcMdlPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
            }
        }

        return svcMdlPMsg.mdlId.getValue();
    }
    // Mod End 2018/03/02 QC#23425

    private String getSvcMachTpCd(NSZC001001PMsg pMsg) {
        String rtnVal = "";
        String mdseCd = "";
        String cmpMdseCd = "";
        if (hasValue(pMsg.mdseCd)) {
            mdseCd = pMsg.mdseCd.getValue();

            // START 2017/10/03 M.Naito [QC#21292, MOD]
            if (hasValue(pMsg.svcMachTpCd)) {
                rtnVal = pMsg.svcMachTpCd.getValue();
            } else {
                for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
                    if (hasValue(pMsg.xxCmptMachList.no(i).mdseCd)) {
                        cmpMdseCd = pMsg.xxCmptMachList.no(i).mdseCd.getValue();
                    }
                    if (mdseCd.equals(cmpMdseCd)) {
                        rtnVal = pMsg.xxCmptMachList.no(i).svcMachTpCd.getValue();
                        break;
                    }
                }
            }
            // END 2017/10/03 M.Naito [QC#21292, MOD]
        }
        return rtnVal;
    }

    private BigDecimal getMdlId() {
        if (mdlId == null) {
            msgMap.addXxMsgId(NSZM0090E);
        }
        return this.mdlId;
    }

    private void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    // START 2015/12/09 T.Tomita [CSA QC#951, ADD]
    private boolean isInstlBaseCtrlFlg(NSZC001001PMsg pMsg) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, pMsg.mdseCd);
        MDSETMsg rtnMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg != null && ZYPConstant.FLG_ON_Y.equals(rtnMsg.instlBaseCtrlFlg.getValue())) {
            return true;
        }
        return false;
    }
    // END 2015/12/09 T.Tomita [CSA QC#951, ADD]

    // START 2015/12/11 T.Tomita [QC#1877, ADD]
    private boolean isMtrReqMdl(String glblCmpyCd, BigDecimal mdlId) {
        if (!hasValue(mdlId)) {
            return false;
        }
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdlId, mdlId);
        DS_MDLTMsg rtnMsg = (DS_MDLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg == null || ZYPConstant.FLG_OFF_N.equals(rtnMsg.mtrReqMdlFlg.getValue())) {
            return false;
        }
        return true;
    }
    // END 2015/12/11 T.Tomita [QC#1877, ADD]

    // add start 2016/05/13 CSA Defect#4578
    private String getIndBillToLocNum(String glblCmpyCd, String billToLocNum) {
        if (!ZYPCommonFunc.hasValue(billToLocNum)) {
            return null;
        }

        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("billToCustCd01", billToLocNum);

        BILL_TO_CUSTTMsgArray resultArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultArray.getValidCount() == 0) {
            return null;
        }

        return resultArray.no(0).locNum.getValue();
    }

    private String getIndCurLocNum(String glblCmpyCd, String curLocNum) {
        if (!ZYPCommonFunc.hasValue(curLocNum)) {
            return null;
        }

        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", curLocNum);

        SHIP_TO_CUSTTMsgArray resultArray = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (resultArray.getValidCount() == 0) {
            return null;
        }

        return resultArray.no(0).locNum.getValue();
    }
    // add end 2016/05/13 CSA Defect#4578

    protected void validatePMsg() {

        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }

        if (!hasValue(pMsg.slsDt)) {
            msgMap.addXxMsgId(NSZM0002E);
        }

        if (!hasValue(pMsg.xxModeCd)) {
            msgMap.addXxMsgId(NSZM0003E);
        }

        ProcessMode procMode = ProcessMode.codeOf(pMsg.xxModeCd.getValue());
        if (procMode == null) {
            msgMap.addXxMsgId(NSZM0004E);
        }

        if (hasValue(pMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (exstSvcMachMstrTMsg == null) {
                msgMap.addXxMsgId(NSZM0006E);
            }
        }

        if (hasValue(pMsg.svcMachMstrPk) && hasValue(pMsg.svcMachMstrStsCd)) {
            BigDecimal svcMachMstrPk = findConversionInProcess(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.svcMachMstrStsCd.getValue());
            if (svcMachMstrPk != null) {
                msgMap.addXxMsgId(NSZM0406E);
                return;
            }
        }
    }

    protected ProcessMode findEvent(NSZC001001PMsg pMsg) {

        if (!hasValue(pMsg.glblCmpyCd)) {
            return null;
        }

        String exstSvcMachMstrStsCd = null;
        String exstSvcMachMstrTp = null;

        if (hasValue(pMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg exstSvcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (exstSvcMachMstrTMsg == null) {
                return null;
            } else {
                exstSvcMachMstrStsCd = exstSvcMachMstrTMsg.svcMachMstrStsCd.getValue();
                exstSvcMachMstrTp = exstSvcMachMstrTMsg.svcMachTpCd.getValue();
            }
        }

        ProcessMode procMode = ProcessMode.codeOf(pMsg.xxModeCd.getValue());
        if (procMode == null) {
            return null;
        }
        String prmSvcMachMstrStsCd = pMsg.svcMachMstrStsCd.getValue();
        ProcessMode event = null;
        // START 2015/11/06 T.Tomita [QC#474, MOD]
        // START 2016/01/05 T.Tomita [QC#2169, MOD]
        switch (procMode) {
            case INSERT_WAREHOUSE:
                // START 2016/10/13 T.Tomita [QC#14734, MOD]
                if (SVC_MACH_MSTR_STS.CREATED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.TERMINATED.equals(prmSvcMachMstrStsCd)
                        || SVC_MACH_MSTR_STS.DUPLICATE.equals(prmSvcMachMstrStsCd)) {
                // END 2016/10/13 T.Tomita [QC#14734, MOD]
                    event = procMode;
                }
                break;
            case INSERT_MACHINE_IN_FIELD:
                if (!hasValue(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(prmSvcMachMstrStsCd)
                            || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case UPDATE_WAREHOUSE:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.CREATED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.DUPLICATE.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case UPDATE_MACHINE_IN_FIELD:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(prmSvcMachMstrStsCd)
                            || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case UPDATE_TERMINATION:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.TERMINATED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case SHIPMENT:
                if (SVC_MACH_MSTR_STS.CREATED.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case INSTALLATION:
                // START 2017/08/28 K.Kitachi [QC#18476, MOD]
                if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(exstSvcMachMstrStsCd)) {
                // END 2017/08/28 K.Kitachi [QC#18476, MOD]
                    if (SVC_MACH_MSTR_STS.INSTALLED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case RMA:
                if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case RMA_CANCEL:
                if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.INSTALLED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case RWS:
                if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.REMOVED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                // Add Start 2018/12/17 QC#28383
                if (hasValue(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.TERMINATED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                // Add End 2018/12/17 QC#28383
                break;
            case DISPOSAL:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.TERMINATED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case TRANSFER_OUT:
                if (SVC_MACH_MSTR_STS.CREATED.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.CREATED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case TRANSFER_IN:
                if (SVC_MACH_MSTR_STS.CREATED.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.CREATED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case SERVICE_UPDATE:
                // START 2019/07/10 K.Kitachi [QC#51273, MOD]
//                if (SVC_MACH_MSTR_STS.INSTALLED.equals(exstSvcMachMstrStsCd)) {
                // QC#55913 Add Mod 
                //if (hasValue(exstSvcMachMstrTp) && SVC_MACH_TP.MACHINE.equals(exstSvcMachMstrTp)) {
                if (hasValue(exstSvcMachMstrStsCd)) {
                    event = procMode;
                }
//                }
                // END 2019/07/10 K.Kitachi [QC#51273, MOD]
                break;
            case CONVERSION_ORDER:
                if (SVC_MACH_MSTR_STS.INSTALLED.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.INSTALLED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case UPDATE_ATTRIBUTE:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    event = procMode;
                }
                break;
            case UPDATE_LOCATION:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    event = procMode;
                }
                break;
            case ALLOCATION_ON:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    event = procMode;
                }
                break;
            case ALLOCATION_OFF:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    event = procMode;
                }
                break;
            case SERVICE_EXCHANGE:
                if (SVC_MACH_MSTR_STS.CREATED.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case UPDATE_INVENTORY:
                if (SVC_MACH_MSTR_STS.CREATED.equals(exstSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(exstSvcMachMstrStsCd)) {
                    if (SVC_MACH_MSTR_STS.CREATED.equals(prmSvcMachMstrStsCd) || SVC_MACH_MSTR_STS.REMOVED.equals(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            case ITEM_CHANGE:
                if (hasValue(exstSvcMachMstrStsCd)) {
                    if (hasValue(prmSvcMachMstrStsCd)) {
                        event = procMode;
                    }
                }
                break;
            // START 2019/11/14 K.Kitachi [QC#54327, ADD]
            case REMOVE_CONFIG:
                event = procMode;
                break;
            // END 2019/11/14 K.Kitachi [QC#54327, ADD]
            default:
                break;
        }
        // END 2016/01/05 T.Tomita [QC#2169, MOD]
        // END 2015/11/06 T.Tomita [QC#474, MOD]
        return event;
    }

    // START 2016/06/10 [QC#9591, ADD]
    private String getIwrEnblFlg(NSZC001001PMsg pMsg) {
        MDSETMsg prmTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.mdseCd, pMsg.mdseCd);

        MDSETMsg tMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
        if (tMsg != null) {
            return tMsg.iwrEnblFlg.getValue();
        }
        return null;
    }
    // END   2016/06/10 [QC#9591, ADD]
    // add start 2016/06/30 CSA Defect#10462
    private String getSldByLineBizTpCd(NSZC001001PMsg pMsg) {
        if (hasValue(pMsg.sldByLineBizTpCd)) {
            return pMsg.sldByLineBizTpCd.getValue();
        }

        if (!hasValue(pMsg.dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg inMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        DS_ORD_TP_PROC_DFNTMsg rtnMsg = (DS_ORD_TP_PROC_DFNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg == null) {
            return null;
        }
        return rtnMsg.lineBizTpCd.getValue();
    }

    private String getSvcByLineBizTpCd(NSZC001001PMsg pMsg) {
        if (hasValue(pMsg.svcByLineBizTpCd)) {
            return pMsg.svcByLineBizTpCd.getValue();
        }

        String rtnLineBizTpCd = null;
        List<Map<String, Object>> dsCpoIstlInfoList = findSvcByLineBizTpCd(pMsg);
        for (Map<String, Object> dsCpoIstlInfo : dsCpoIstlInfoList) {
            rtnLineBizTpCd = (String) dsCpoIstlInfo.get("ISTL_DIV_CD");
            if (hasValue(rtnLineBizTpCd)) {
                break;
            }
        }

        // add start 2016/10/12 CSA Defect#13981
        if (!hasValue(rtnLineBizTpCd)) {
            MDSETMsg mdseTMsg = findMdse(pMsg.glblCmpyCd.getValue(), pMsg.mdseCd.getValue());
            if (mdseTMsg != null) {
                rtnLineBizTpCd = mdseTMsg.lineBizTpCd.getValue();
            }
        }
        // add end 2016/10/12 CSA Defect#13981

        // START 2017/01/05 K.Kitachi [QC#16848, MOD]
//        return rtnLineBizTpCd;
        return convLineBizTpToSvcLineBiz(pMsg.glblCmpyCd.getValue(), rtnLineBizTpCd);
        // END 2017/01/05 K.Kitachi [QC#16848, MOD]
    }

    private List<Map<String, Object>> findSvcByLineBizTpCd(NSZC001001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
        param.put("cpoDtlLineNum", pMsg.cpoDtlLineNum.getValue());
        param.put("cpoDtlLineSubNum", pMsg.cpoDtlLineSubNum.getValue());
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("findSvcByLineBizTpCd", param);
    }
    // add end 2016/06/30 CSA Defect#10462
    // add start 2016/07/04 CSA Defect#10474
    private boolean existsConfigMstrDtl(NSZC001001PMsg pMsg) {
        if (!hasValue(pMsg.svcConfigMstrPk) || !hasValue(pMsg.svcMachMstrPk)) {
            return false;
        }
        SVC_CONFIG_MSTR_DTLTMsgArray list = findSvcConfigMstrDtlList(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue(), pMsg.svcMachMstrPk.getValue());
        if (list.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private SVC_CONFIG_MSTR_DTLTMsgArray findSvcConfigMstrDtlList(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg inMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        return (SVC_CONFIG_MSTR_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // add end 2016/07/04 CSA Defect#10474

    // add start 2016/07/11 CSA Defect#446
    private void warrantyContrProc(ProcessMode event) {
        switch (procMode(event)) {
            case CRAT_WTY:
                createWarranty();
                break;
            case TERM_WTY:
                terminateWarranty();
                break;
            default:
                break;
        }
    }

    private int procMode(ProcessMode event) {
        if (event == ProcessMode.INSERT_MACHINE_IN_FIELD || event == ProcessMode.UPDATE_MACHINE_IN_FIELD || event == ProcessMode.SHIPMENT || event == ProcessMode.SERVICE_EXCHANGE) {
            return CRAT_WTY;
        } else if (event == ProcessMode.UPDATE_WAREHOUSE || event == ProcessMode.UPDATE_TERMINATION || event == ProcessMode.RWS || event == ProcessMode.DISPOSAL) {
            return TERM_WTY;
        }
        return 0;
    }

    private void createWarranty() {
        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();
        // check Create Warranty Contract
        if (!isCreateWarranty(pMsg)) {
            return;
        }

        // START 2016/08/03 T.Tomita [QC#12230, ADD]
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // START 2017/09/14 S.Fujita [QC#18534,DEL]
//        // VAR_CHAR_CONST
//        this.SVC_PGM_ST_WTY_NEW = ZYPCodeDataUtil.getVarCharConstValue(SVC_PGM_ST_WTY_NEW_KEY, glblCmpyCd);
//        if (!hasValue(this.SVC_PGM_ST_WTY_NEW)) {
//            this.msgMap.addXxMsgId(NSZM1027E);
//            return;
//        }
//        this.SVC_PGM_ST_WTY_USED = ZYPCodeDataUtil.getVarCharConstValue(SVC_PGM_ST_WTY_USED_KEY, glblCmpyCd);
//        if (!hasValue(this.SVC_PGM_ST_WTY_USED)) {
//            this.msgMap.addXxMsgId(NSZM1028E);
//            return;
//        }
        // END 2017/09/14 S.Fujita [QC#18534,DEL]

        BigDecimal svcMachMstrPk = pMsg.svcMachMstrPk.getValue();
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(glblCmpyCd, svcMachMstrPk);
        if (svcMachMstrTMsg == null) {
            return;
        }
        BigDecimal countMdse = countMdse(glblCmpyCd, svcMachMstrTMsg.mdseCd.getValue());
        if (countMdse.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        // START 2017/09/14 S.Fujita [QC#18534,ADD]
        this.SVC_PGM_ST_WTY = getSvcPgmMdse(svcMachMstrTMsg);
        if (!hasValue(this.SVC_PGM_ST_WTY)) {
            this.msgMap.addXxMsgId(NSAM0702E);
            return;
        }
        // END 2017/09/14 S.Fujita [QC#18534,ADD]

        String svcMachTpCd = svcMachMstrTMsg.svcMachTpCd.getValue();
        if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
            // machine warranty create
            insertWarranty(pMsg, svcMachMstrTMsg);
            for (int i = 0; i < pMsg.xxCmptMachList.getValidCount(); i++) {
                BigDecimal accSvcMachMstrPk = pMsg.xxCmptMachList.no(i).svcMachMstrPk.getValue();
                if (hasValue(accSvcMachMstrPk) && !svcMachMstrPk.equals(accSvcMachMstrPk)) {
                    SVC_MACH_MSTRTMsg accSvcMachMstrTMsg = findSvcMachMstr(glblCmpyCd, accSvcMachMstrPk);
                    if (accSvcMachMstrTMsg == null) {
                        continue;
                    }
                    BigDecimal countAccMdse = countMdse(glblCmpyCd, accSvcMachMstrTMsg.mdseCd.getValue());
                    if (countAccMdse.compareTo(BigDecimal.ZERO) == 0) {
                        continue;
                    }
                    // accessory warranty create
                    insertWarranty(pMsg, accSvcMachMstrTMsg);
                }
            }
        }
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
            // accessory warranty create
            insertWarranty(pMsg, svcMachMstrTMsg);
        }
        // END 2016/08/03 T.Tomita [QC#12230, ADD]
    }

    private NSZC046001PMsg executeContrDataUpdApi(NSZC046001PMsg param) {
        NSZC046001 api = new NSZC046001();
        api.execute(param, this.onBatType);

        if (hasValue(param.xxMsgId_HD)) {
            EZDDebugOutput.println(0, param.xxMsgId_HD.getValue() + ":" + param.xxDsMultMsgDplyTxt_HD.getValue(), this);
            return null;
        }

        for (int i = 0; i < param.xxContrDtlList.getValidCount(); i++) {
            if (hasValue(param.xxContrDtlList.no(i).xxMsgId_DT)) {
                EZDDebugOutput.println(0, param.xxContrDtlList.no(i).xxMsgId_DT.getValue() + ":" + param.xxContrDtlList.no(i).xxDsMultMsgDplyTxt_DT.getValue(), this);
                return null;
            }
        }
        return param;
    }

    private boolean callContrTrkAPI(String glblCmpyCd, String slsDt, BigDecimal dsContrPk, String userId) {
        String stsMemoRsnCd = DS_CONTR_TRK_RSN.CONTRACT_MODE_CHANGE;
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, dsContrPk, userId, slsDt, stsMemoRsnCd, null, ONBATCH_TYPE.ONLINE)) {
            msgMap.addXxMsgId(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }

    private void terminateWarranty() {
        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();
        if (!isTerminateWarranty()) {
            return;
        }

        // call Contract Data Update API
        NSZC046001PMsg contrUpdApiParam = callContrDataUpdApiForTrmn(pMsg);
        if (contrUpdApiParam == null) {
            return;
        }

        // call contract tracking api
        callContrTrkAPI(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue(), contrUpdApiParam.dsContrPk.getValue(), contrUpdApiParam.psnCd.getValue());
    }

    private NSZC046001PMsg callContrDataUpdApiForTrmn(NSZC001001PMsg pMsg) {
        NSZC046001PMsg param = setTrmnContrDataUpdApiParam(pMsg);
        if (param == null) {
            return null;
        }

        param = executeContrDataUpdApi(param);
        if (param == null || msgMap.getMsgMgr().isXxMsgId()) {
            return null;
        }
        return param;
    }

    private NSZC046001PMsg setTrmnContrDataUpdApiParam(NSZC001001PMsg pMsg) {
        NSZC046001PMsg param = new NSZC046001PMsg();
        List<Map<String, Object>> tergetList = findTerminateWtyContr(pMsg);
        if (tergetList.size() == 0) {
            return null;
        }

        setTrmnContrDataUpdApiParamHdr(param, tergetList.get(0));
        setTrmnContrDataUpdApiParamDtl(param, tergetList);
        return param;
    }

    private void setTrmnContrDataUpdApiParamHdr(NSZC046001PMsg param, Map<String, Object> terget) {
        setValue(param.glblCmpyCd, (String) terget.get("GLBL_CMPY_CD"));
        setValue(param.xxModeCd, NSZC046001Constant.MODE_TERMINATE);
        setValue(param.slsDt, (String) terget.get("SLS_DT"));
        setValue(param.dsContrSrcTpCd, DS_CONTR_SRC_TP.IB_UPDATE_API);
        setValue(param.psnCd, getPsnCd((String) terget.get("EZUPUSERID")));
        setValue(param.dsContrPk, (BigDecimal) terget.get("DS_CONTR_PK"));
        setValue(param.dsContrNum, (String) terget.get("DS_CONTR_NUM"));
    }

    private void setTrmnContrDataUpdApiParamDtl(NSZC046001PMsg param, List<Map<String, Object>> tergetList) {
        int i = 0;
        for (Map<String, Object> terget : tergetList) {
            setValue(param.xxContrDtlList.no(i).dsContrDtlPk, (BigDecimal) terget.get("DS_CONTR_DTL_PK"));
            setValue(param.xxContrDtlList.no(i).contrCloDt, (String) terget.get("SLS_DT"));
            setValue(param.xxContrDtlList.no(i).trmnTotAmt, BigDecimal.ZERO);
            setValue(param.xxContrDtlList.no(i).trmnOvrdTotAmt, BigDecimal.ZERO);
            setValue(param.xxContrDtlList.no(i).supprCrFlg, ZYPConstant.FLG_OFF_N);
            i++;
        }
        param.xxContrDtlList.setValidCount(i);
    }

    private SVC_MACH_MSTR_STSTMsg findSvcMachMstrSts(String glblCmpyCd, String svcMachMstrStsCd) {
        SVC_MACH_MSTR_STSTMsg inMsg = new SVC_MACH_MSTR_STSTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMachMstrStsCd, svcMachMstrStsCd);
        return (SVC_MACH_MSTR_STSTMsg) S21CodeTableAccessor.findByKey(inMsg);
    }

    private List<Map<String, Object>> findTerminateWtyContr(NSZC001001PMsg pMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
        param.put("svcMachMstrPk", svcMachMstrTMsg.svcMachMstrPk.getValue());
        param.put("svcMachTpCd", svcMachMstrTMsg.svcMachTpCd.getValue());
        param.put("slsDt", pMsg.slsDt.getValue());
        param.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        param.put("dsContrCtrlStsCdList", this.dsContrCtrlStsCdList);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("findTerminateWtyContr", param);
    }

    private String getContrVrsnEffThruDt(String glblCmpyCd, String slsDt, String mdseCd) {
        MDSETMsg mdseTMsg = findMdse(glblCmpyCd, mdseCd);
        int wtyDaysAot = mdseTMsg.wtyDaysAot.getValueInt();
        if (wtyDaysAot > 0) {
            wtyDaysAot = wtyDaysAot -1;
        }
        return ZYPDateUtil.addDays(slsDt, wtyDaysAot);
    }

    // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//    private Map<String, Object> getContractBranch(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
    private Map<String, Object> getContractBranch(SVC_MACH_MSTRTMsg svcMachMstrTMsg, String svcLineBizCd) {
    // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        Map<String, Object> rtnMap = null;
        Map<String, Object> param = new HashMap<String, Object>();
        // START 2017/11/20 K.Ochiai [QC#21700, MOD]
        if (hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            rtnMap = getEmsdContractBranch(svcMachMstrTMsg);
            if (rtnMap == null) {
                param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
                param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
                // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
                param.put("svcLineBizCd", svcLineBizCd);
                // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
                rtnMap = (Map<String, Object>) ssmBatClnt.queryObject("getContractBranchForCpo", param);
            }
        }
        // END 2017/11/20 K.Ochiai [QC#21700, MOD]
        if (rtnMap == null && hasValue(svcMachMstrTMsg.svcMachTrxTpCd)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("dsOrdCatgCd", svcMachMstrTMsg.svcMachTrxTpCd.getValue());
            // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
            param.put("svcLineBizCd", svcLineBizCd);
            // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
            rtnMap = (Map<String, Object>) ssmBatClnt.queryObject("getContractBranch", param);
        }

        // Add Start 2018/06/19 QC#26618
        if (rtnMap != null) {
            return rtnMap;
        }

        String svcBrCd = getSvcBrCdByMachMstr(svcMachMstrTMsg);
        if (!hasValue(svcBrCd)) {
            return rtnMap;
        }
        rtnMap = getContrCoaBrMap(svcMachMstrTMsg.glblCmpyCd.getValue(), svcBrCd, svcLineBizCd);
        // Add End 2018/06/19 QC#26618
        return rtnMap;
    }

    // START 2017/11/20 K.Ochiai [QC#21700, ADD]
    private Map<String, Object> getEmsdContractBranch(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
        param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
        param.put("emsdContrBrRep", ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP);

        return (Map<String, Object>) ssmBatClnt.queryObject("getEmsdContractBranch", param);
    }
    // END 2017/11/20 K.Ochiai [QC#21700, ADD]

    // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//    private String getContractSalesRep(SVC_MACH_MSTRTMsg svcMachMstrTMsg, String svcContrBrCd) {
    private String getContractSalesRep(SVC_MACH_MSTRTMsg svcMachMstrTMsg, String svcContrBrCd, String svcLineBizCd) {
    // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        String slsRep = null;
        Map<String, Object> param = new HashMap<String, Object>();
        // START 2017/11/20 K.Ochiai [QC#21698, MOD]
        if (hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            slsRep = getEmsdslsRep(svcMachMstrTMsg);
            if (!hasValue(slsRep)) {
                param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
                param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
                // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
                param.put("svcLineBizCd", svcLineBizCd);
                // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
                slsRep = (String) ssmBatClnt.queryObject("getContractSalesRepForCpo", param);
            }
        }
        // END 2017/11/20 K.Ochiai [QC#21698, MOD]

        // Mod Start 2018/06/19 QC#26618
        if (!hasValue(slsRep) && hasValue(svcContrBrCd) && hasValue(svcLineBizCd)) {
        // Mod End 2018/06/19 QC#26618
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("svcContrBrCd", svcContrBrCd);
            // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
            param.put("svcLineBizCd", svcLineBizCd);
            // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
            slsRep = (String) ssmBatClnt.queryObject("getContractSalesRep", param);
        }
        return slsRep;
    }

    // START 2017/11/20 K.Ochiai [QC#21698, ADD]
    private String getEmsdslsRep(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
        param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
        param.put("emsdContrBrRep", ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP);
        return (String) ssmBatClnt.queryObject("getEmsdslsRep", param);
    }
    // END 2017/11/20 K.Ochiai [QC#21698, ADD]

    private String getDsContrCls(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        String dsContrCls = null;
        Map<String, Object> param = new HashMap<String, Object>();
        if (hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
            param.put("svcContrCatg", ORD_CATG_CTX_TP_SVC_CONTR_CATG);
            dsContrCls = (String) ssmBatClnt.queryObject("getFirstBizCtxNmForCpo", param);
        }

        if (!hasValue(dsContrCls) && hasValue(svcMachMstrTMsg.svcMachTrxTpCd)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("dsOrdCatgCd", svcMachMstrTMsg.svcMachTrxTpCd.getValue());
            param.put("svcContrCatg", ORD_CATG_CTX_TP_SVC_CONTR_CATG);
            dsContrCls = (String) ssmBatClnt.queryObject("getFirstBizCtxNm", param);
        }
        // Add Start 2018/06/19 QC#26618
        if (!hasValue(dsContrCls)) {
            dsContrCls = DS_CONTR_CLS.RETAIL;
        }
        // Add End 2018/06/19 QC#26618
        return dsContrCls;
    }

    private String getLineBizTpCd(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        String lineBizTp = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        if (hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
            lineBizTp = (String) ssmBatClnt.queryObject("getLineBizTpCdForCpo", param);
        }

        if (!hasValue(lineBizTp) && hasValue(svcMachMstrTMsg.svcMachTrxTpCd)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("dsOrdCatgCd", svcMachMstrTMsg.svcMachTrxTpCd.getValue());
            lineBizTp = (String) ssmBatClnt.queryObject("getLineBizTpCd", param);
        }

        // Add Start 2018/06/19 QC#26618
        if (!hasValue(lineBizTp)) {
            lineBizTp = svcMachMstrTMsg.sldByLineBizTpCd.getValue();
        }

        if (!hasValue(lineBizTp)) {
            lineBizTp = svcMachMstrTMsg.svcByLineBizTpCd.getValue();
        }
        // Add End 2018/06/19 QC#26618
        return lineBizTp;
    }

    private BigDecimal getCtacPsnPk(SVC_MACH_MSTRTMsg svcMachMstrTMsg, String slsDt) {
        BigDecimal ctacPsnPk = null;
        Map<String, Object> param = new HashMap<String, Object>();
        // START 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        String [] ctacTpList = {CTAC_TP.BILL_TO_CONTACT, CTAC_TP.BILL_TO_CONTACT_CONTRACTS};
        // END 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        if (hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("cpoOrdNum", svcMachMstrTMsg.cpoOrdNum.getValue());
            param.put("billToContact", CTAC_TP.BILL_TO_CONTACT);
            // START 2018/04/04 U.Kim [QC#23559, ADD]
            param.put("billToCustCd", svcMachMstrTMsg.billToLocNum.getValue());
            param.put("billToContactContr", CTAC_TP.BILL_TO_CONTACT_CONTRACTS);
            param.put("ctacTpList", ctacTpList);
            // END 2018/04/04 U.Kim [QC#23559, ADD]
            ctacPsnPk = (BigDecimal) ssmBatClnt.queryObject("getCtacPsnPkForCpo", param);
        }

        if (!hasValue(ctacPsnPk)) {
            param.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
            param.put("svcMachMstrPk", svcMachMstrTMsg.svcMachMstrPk.getValue());
            param.put("slsDt", slsDt);
            param.put("maxDt", MAX_DT);
            // add start 2016/09/23 CSA Defect#13686
            param.put("billToContact", CTAC_TP.BILL_TO_CONTACT);
            // START 2018/04/04 U.Kim [QC#23559, ADD]
            param.put("billToContactContr", CTAC_TP.BILL_TO_CONTACT_CONTRACTS);
            param.put("ctacTpList", ctacTpList);
            // END 2018/04/04 U.Kim [QC#23559, ADD]
            // add end 2016/09/23 CSA Defect#13686
            ctacPsnPk = (BigDecimal) ssmBatClnt.queryObject("getCtacPsnPk", param);
        }
        return ctacPsnPk;
    }

    // START 2017/09/14 S.Fujita [QC#18534,DEL]
//    private String getSvcPgmMdse(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
//        String svcPgmMdse = this.SVC_PGM_ST_WTY_NEW;
//        if (hasValue(svcMachMstrTMsg.cpoOrdNum) && hasValue(svcMachMstrTMsg.cpoDtlLineNum) && hasValue(svcMachMstrTMsg.cpoDtlLineSubNum)) {
//            CPO_DTLTMsg inCpoDtlMsg = new CPO_DTLTMsg();
//            setValue(inCpoDtlMsg.glblCmpyCd, svcMachMstrTMsg.glblCmpyCd);
//            setValue(inCpoDtlMsg.cpoOrdNum, svcMachMstrTMsg.cpoOrdNum);
//            setValue(inCpoDtlMsg.cpoDtlLineNum, svcMachMstrTMsg.cpoDtlLineNum);
//            setValue(inCpoDtlMsg.cpoDtlLineSubNum, svcMachMstrTMsg.cpoDtlLineSubNum);
//            CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(inCpoDtlMsg);
//            if (cpoDtlTMsg == null) {
//                return svcPgmMdse;
//            }
//
//            SWHTMsg inSwhMsg = new SWHTMsg();
//            setValue(inSwhMsg.glblCmpyCd, cpoDtlTMsg.glblCmpyCd);
//            setValue(inSwhMsg.rtlSwhCd, cpoDtlTMsg.rtlSwhCd);
//            SWHTMsg swhTMsg = (SWHTMsg) S21ApiTBLAccessor.findByKey(inSwhMsg);
//            if (swhTMsg == null) {
//                return svcPgmMdse;
//            }
//
//            if (!hasValue(swhTMsg.mdseInvtyCostPct)) {
//                return svcPgmMdse;
//            }
//
//            BigDecimal mdseInvtyCostPct = swhTMsg.mdseInvtyCostPct.getValue();
//            if (mdseInvtyCostPct.compareTo(MAX_PCT) != 0) {
//                svcPgmMdse = this.SVC_PGM_ST_WTY_USED;
//            }
//        }
//        return svcPgmMdse;
//    }
    // END 2017/09/14 S.Fujita [QC#18534,DEL]

    private String getSvcCallAvalFlg(SVC_MACH_MSTRTMsg tMsg) {
        if (tMsg == null) {
            return ZYPConstant.FLG_OFF_N;
        }

        SVC_MACH_MSTR_STSTMsg stsTMsg = findSvcMachMstrSts(tMsg.glblCmpyCd.getValue(), tMsg.svcMachMstrStsCd.getValue());
        if (stsTMsg == null) {
            return ZYPConstant.FLG_OFF_N;
        }
        return stsTMsg.svcCallAvalFlg.getValue();
    }

    // mod start 2016/09/07 CSA Defect#10568
    // add start 2016/07/25 CSA Defect#6951
    private NSXC002001GetBrCdBean getBrCd(NSZC001001PMsg pMsg) {
        String lineBizTpCd = pMsg.svcByLineBizTpCd.getValue();
        if (!hasValue(lineBizTpCd)) {
            MDSETMsg mdseTMsg = new MDSETMsg();
            setValue(mdseTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(mdseTMsg.mdseCd, pMsg.mdseCd);
            mdseTMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(mdseTMsg);
            if (mdseTMsg != null) {
                lineBizTpCd = mdseTMsg.lineBizTpCd.getValue();
            }
        }

        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////        return getBrCd(pMsg, lineBizTpCd);
//        return getBrCd(pMsg, lineBizTpCd, pMsg.sldByLineBizTpCd.getValue());
//        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        return getBrCd(pMsg, lineBizTpCd, pMsg.sldByLineBizTpCd.getValue(), pMsg.svcMachMstrPk.getValue());
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
    }
    // add end 2016/07/25 CSA Defect#6951
    // mod end 2016/09/07 CSA Defect#10568

    // add start 2016/09/07 CSA Defect#10568
    // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////    private NSXC002001GetBrCdBean getBrCd(NSZC001001PMsg pMsg, String lineBizTpCd) {
//    private NSXC002001GetBrCdBean getBrCd(NSZC001001PMsg pMsg, String lineBizTpCd, String sldByLineBizTpCd) {
//    // END 2018/09/10 K.Kitachi [QC#26260, MOD]
    private NSXC002001GetBrCdBean getBrCd(NSZC001001PMsg pMsg, String lineBizTpCd, String sldByLineBizTpCd, BigDecimal svcMachMstrPk) {
    // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        String postCd = null;
        SHIP_TO_CUSTTMsg prmTMsg = new SHIP_TO_CUSTTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        // START 2023/10/06 K.Watanabe [QC#54186, MOD]
//        prmTMsg.setConditionValue("shipToCustCd01", pMsg.curLocNum.getValue());
        String curLocNum = pMsg.curLocNum.getValue();
        if (curLocNum == null || curLocNum.isEmpty()) {
            SVC_MACH_MSTRTMsg selectTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), svcMachMstrPk);
            curLocNum = selectTMsg.curLocNum.getValue();
        }
        prmTMsg.setConditionValue("shipToCustCd01", curLocNum);
        // END 2023/10/06 K.Watanabe [QC#54186, MOD]
        SHIP_TO_CUSTTMsgArray resultList = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
        if (resultList.length() > 0) {
            postCd = resultList.no(0).postCd.getValue();
        }

        NSXC002001GetBrCdBean brCdBean = new NSXC002001GetBrCdBean();
        brCdBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        brCdBean.setPostCd(postCd);
        brCdBean.setSvcLineBizCd(lineBizTpCd);
        brCdBean.setSalesDate(pMsg.slsDt.getValue());
        // START 2018/09/10 K.Kitachi [QC#26260, ADD]
        brCdBean.setSldByLineBizTpCd(sldByLineBizTpCd);
        // END 2018/09/10 K.Kitachi [QC#26260, ADD]
        NSXC002001GetBrCd.getBrCd(brCdBean);
        return brCdBean;
    }
    // add end 2016/09/07 CSA Defect#10568

    private boolean isCreateWarranty(NSZC001001PMsg pMsg) {
        // Service Machine Master Status Validation
        // START 2018/08/23 K.Kitachi [QC#27773, MOD]
//        if (!isCallAval(pMsg)) {
        if (!isCallAval(pMsg) && !ZYPConstant.FLG_ON_Y.equals(pMsg.wtyAutoCratFlg.getValue())) {
        // END 2018/08/23 K.Kitachi [QC#27773, MOD]
            return false;
        }

        // START 2017/09/14 S.Fujita [QC#18534,ADD]
        // check New Service Machine Master
        if (!isNewSvcMachMstr(pMsg)) {
        	return false;
        }
        // END 2017/09/14 S.Fujita [QC#18534,ADD]

        return true;
    }

    private boolean isCallAval(NSZC001001PMsg pMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        String preSvcCallAvalFlg = getSvcCallAvalFlg(this.preSvcMachMstrTMsg);
        String svcCallAvalFlg = getSvcCallAvalFlg(svcMachMstrTMsg);
        if (ZYPConstant.FLG_OFF_N.equals(preSvcCallAvalFlg) && ZYPConstant.FLG_ON_Y.equals(svcCallAvalFlg)) {
            return true;
        }
        return false;
    }

    private boolean isTerminateWarranty() {
        NSZC001001PMsg pMsg = (NSZC001001PMsg) msgMap.getPmsg();
        // Service Machine Master Status Validation
        if (!isNotCallAval(pMsg)) {
            return false;
        }
        return true;
    }

    private boolean isNotCallAval(NSZC001001PMsg pMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        String preSvcCallAvalFlg = getSvcCallAvalFlg(this.preSvcMachMstrTMsg);
        String svcCallAvalFlg = getSvcCallAvalFlg(svcMachMstrTMsg);
        if (ZYPConstant.FLG_ON_Y.equals(preSvcCallAvalFlg) && ZYPConstant.FLG_OFF_N.equals(svcCallAvalFlg)) {
            return true;
        }
        return false;
    }

    private static String getPsnCd(String target) {
        if (target != null) {
            if (target.length() > PSN_CD_LENGTH) {
                return target.substring(0, PSN_CD_LENGTH);
            } else {
                return target;
            }
        } else {
            return null;
        }
    }
    // add end 2016/07/11 CSA Defect#446

    // START 2016/08/03 T.Tomita [QC#12230, ADD]
    private BigDecimal countMdse(String glblCmpyCd, String mdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("mdseCd", mdseCd);
        return (BigDecimal) this.ssmBatClnt.queryObject("countMdse", param);
    }

    // mod start 2016/09/13 CSA Defect#14517
    private DS_CONTR_INTFCTMsg createDsContrIntfcTMsg(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String machDsContrNum, String machDsContrIntfcBatNum, String dsContrSrcRefNum, BigDecimal svcConfigMstrPk) {
        DS_CONTR_INTFCTMsg dsContrIntfcTMsg = new DS_CONTR_INTFCTMsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal dsContrIntfcBatSq = getMaxPlusOneSequence(glblCmpyCd, slsDt);
        String newDsContrIntfcBatNum = getDsContrIntfcBatNum(dsContrIntfcBatSq, slsDt);
        if (SVC_MACH_TP.MACHINE.equals(svcMachMstrTMsg.svcMachTpCd.getValue())) {
            // machine warranty create
            setValue(dsContrIntfcTMsg.dsContrIntfcBatNum, newDsContrIntfcBatNum);
            setValue(dsContrIntfcTMsg.dsContrIntfcActCd, DS_CONTR_INTFC_ACT.CREATE);
        } else if (hasValue(machDsContrNum)) {
            // accessory warranty add to contract
            setValue(dsContrIntfcTMsg.dsContrIntfcBatNum, newDsContrIntfcBatNum);
            setValue(dsContrIntfcTMsg.dsContrIntfcActCd, DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
            setValue(dsContrIntfcTMsg.dsContrNum, machDsContrNum);
        } else if (hasValue(machDsContrIntfcBatNum)) {
            // accessory warranty create
            setValue(dsContrIntfcTMsg.dsContrIntfcBatNum, machDsContrIntfcBatNum);
            setValue(dsContrIntfcTMsg.dsContrIntfcActCd, DS_CONTR_INTFC_ACT.CREATE);
        }
        setValue(dsContrIntfcTMsg.glblCmpyCd, glblCmpyCd);
        BigDecimal dsContrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ);
        setValue(dsContrIntfcTMsg.dsContrIntfcPk, dsContrIntfcPk);
        setValue(dsContrIntfcTMsg.dsContrSrcRefNum, dsContrSrcRefNum);
        setValue(dsContrIntfcTMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.IB_UPDATE);
        setValue(dsContrIntfcTMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
        setValue(dsContrIntfcTMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
        setValue(dsContrIntfcTMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(dsContrIntfcTMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(dsContrIntfcTMsg.contrIntfcDtlTpCd, CONTR_INTFC_DTL_TP.BASE);
        setValue(dsContrIntfcTMsg.dsAcctNum, svcMachMstrTMsg.billToAcctNum);
        setValue(dsContrIntfcTMsg.billToCustCd, svcMachMstrTMsg.billToLocNum);
        // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//        Map<String, Object> contrBrMap = getContractBranch(svcMachMstrTMsg);
        Map<String, Object> contrBrMap = getContractBranch(svcMachMstrTMsg, getLineBizTpCd(svcMachMstrTMsg));
        // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        if (contrBrMap != null) {
            String svcContrBrCd = (String) contrBrMap.get("SVC_CONTR_BR_CD");
            setValue(dsContrIntfcTMsg.svcContrBrCd, svcContrBrCd);
            // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//            setValue(dsContrIntfcTMsg.tocCd, getContractSalesRep(svcMachMstrTMsg, svcContrBrCd));
            setValue(dsContrIntfcTMsg.tocCd, getContractSalesRep(svcMachMstrTMsg, svcContrBrCd, getLineBizTpCd(svcMachMstrTMsg)));
            // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
            setValue(dsContrIntfcTMsg.contrAdminPsnCd, (String) contrBrMap.get("CONTR_ADMIN_PSN_CD"));
        }
        setValue(dsContrIntfcTMsg.custPoNum, svcMachMstrTMsg.custIssPoNum);
        setValue(dsContrIntfcTMsg.poDt, svcMachMstrTMsg.dsPoExprDt);
        // START 2017/09/14 S.Fujita [QC#18534,MOD]
//        setValue(dsContrIntfcTMsg.svcPgmMdseCd, getSvcPgmMdse(svcMachMstrTMsg));
        setValue(dsContrIntfcTMsg.svcPgmMdseCd, this.SVC_PGM_ST_WTY);
        // END 2017/09/14 S.Fujita [QC#18534,MOD]
        String mdseCd = svcMachMstrTMsg.mdseCd.getValue();
        setValue(dsContrIntfcTMsg.mdseCd, mdseCd);
        Map<String, Object> mdlInfoMap = getMdlInfoMap(glblCmpyCd, svcConfigMstrPk);
        if (mdlInfoMap != null) {
            setValue(dsContrIntfcTMsg.mdlId, (BigDecimal) mdlInfoMap.get("MDL_ID"));
            setValue(dsContrIntfcTMsg.mdlNm, (String) mdlInfoMap.get("T_MDL_NM"));
        }
        setValue(dsContrIntfcTMsg.contrFromDt, slsDt);
        setValue(dsContrIntfcTMsg.contrThruDt, getContrVrsnEffThruDt(glblCmpyCd, slsDt, mdseCd));
        setValue(dsContrIntfcTMsg.prcAllocByMachQtyFlg, ZYPConstant.FLG_OFF_N);
        setValue(dsContrIntfcTMsg.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
        setValue(dsContrIntfcTMsg.contrUplftTpCd, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        setValue(dsContrIntfcTMsg.dsContrCatgCd, DS_CONTR_CATG.WARRANTY);
        setValue(dsContrIntfcTMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
        setValue(dsContrIntfcTMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        setValue(dsContrIntfcTMsg.dsContrClsCd, getDsContrCls(svcMachMstrTMsg));
        setValue(dsContrIntfcTMsg.ctacPsnPk, getCtacPsnPk(svcMachMstrTMsg, slsDt));
        setValue(dsContrIntfcTMsg.invSeptBaseUsgFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsContrIntfcTMsg.svcLineBizCd, getLineBizTpCd(svcMachMstrTMsg));
        setValue(dsContrIntfcTMsg.dsContrIntfcDt, slsDt);
        setValue(dsContrIntfcTMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(dsContrIntfcTMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(dsContrIntfcTMsg.dsContrIntfcBatSq, dsContrIntfcBatSq);
        setValue(dsContrIntfcTMsg.prcSvcPlnTpCd, PRC_SVC_PLN_TP.STANDARD);
        setValue(dsContrIntfcTMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        // START 2022/03/22 [QC#59683, ADD]
        setValue(dsContrIntfcTMsg.dsInvTgtrTpCd, DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY);
        // END   2022/03/22 [QC#59683, ADD]
        return dsContrIntfcTMsg;
    }
    // mod end 2016/09/13 CSA Defect#14517

    private Map<String, Object> getDsContrWty(String glblCmpyCd, BigDecimal svcConfigMstrPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("slsDt", slsDt);
        param.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        param.put("dsContrCtrlStsCdList", this.dsContrCtrlStsCdList);
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getDsContrWty", param);
    }

    private Map<String, Object> getDsContrIntfc(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        param.put("dsContrProcStsCd", DS_CONTR_PROC_STS.COMPLEATED);
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getDsContrIntfc", param);
    }

    private BigDecimal getMaxPlusOneSequence(String glblCmpyCd, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("slsDt", slsDt);

        int maxDsContrIntfcSqNum = (Integer) this.ssmBatClnt.queryObject("getMaxPlusOneSequence", param);
        BigDecimal rtnSqNum = null;
        if (maxDsContrIntfcSqNum > 0) {
            rtnSqNum = new BigDecimal(maxDsContrIntfcSqNum);
            rtnSqNum = rtnSqNum.add(BigDecimal.ONE);
        } else {
            rtnSqNum = BigDecimal.ONE;
        }
        return rtnSqNum;
    }

    private String getDsContrIntfcBatNum(BigDecimal dsContrIntfcSqNum, String slsDt) {
        return slsDt + ZYPCommonFunc.leftPad(String.valueOf(dsContrIntfcSqNum), LPAD_LEN, PAD_STR);
    }

    private Map<String, Object> getMdlInfoMap(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        // START 2017/05/15 K.Kitachi [QC#18343, ADD]
        if (!hasValue(svcConfigMstrPk)) {
            return null;
        }
        // END 2017/05/15 K.Kitachi [QC#18343, ADD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getMdlInfo", param);
    }

    private void insertWarranty(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        String glblCmpyCd = svcMachMstrTMsg.glblCmpyCd.getValue();
        BigDecimal svcConfigMstrPk = svcMachMstrTMsg.svcConfigMstrPk.getValue();
        // add start 2016/09/13 CSA Defect#14517
        if (!hasValue(svcConfigMstrPk) && !hasValue(pMsg.svcConfigMstrPk)) {
            return;
        }

        if (!hasValue(svcConfigMstrPk)) {
            svcConfigMstrPk = pMsg.svcConfigMstrPk.getValue();
        }
        // add end 2016/09/13 CSA Defect#14517
        String dsContrNum = null;
        String dsContrIntfcBatNum = null;
        String dsContrSrcRefNum = null; 
        String svcMachTpCd = svcMachMstrTMsg.svcMachTpCd.getValue();
        if (hasValue(svcMachMstrTMsg.cpoOrdNum)) {
            dsContrSrcRefNum = svcMachMstrTMsg.cpoOrdNum.getValue();
        } else {
            dsContrSrcRefNum = svcMachMstrTMsg.serNum.getValue();
        }

        Map<String, Object> dsContrWty = getDsContrWty(glblCmpyCd, svcConfigMstrPk, pMsg.slsDt.getValue());
        Map<String, Object> dsContrIntfc = getDsContrIntfc(glblCmpyCd, svcConfigMstrPk);
        if (dsContrWty != null) {
            dsContrNum = (String) dsContrWty.get("DS_CONTR_NUM");
            dsContrSrcRefNum = (String) dsContrWty.get("SVC_CONTR_REF_CMNT_TXT");
        }
        if (dsContrIntfc != null) {
            dsContrIntfcBatNum = (String) dsContrIntfc.get("DS_CONTR_INTFC_BAT_NUM");
            dsContrSrcRefNum = (String) dsContrIntfc.get("DS_CONTR_SRC_REF_NUM");
        }

        if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
            if (hasValue(dsContrNum) || hasValue(dsContrIntfcBatNum)) {
                return;
            }
        }
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
            if (!hasValue(dsContrNum) && !hasValue(dsContrIntfcBatNum)) {
                return;
            }
        }
        // mod start 2016/09/13 CSA Defect#14517
        DS_CONTR_INTFCTMsg dsContrIntfcTMsg = createDsContrIntfcTMsg(pMsg, svcMachMstrTMsg, dsContrNum, dsContrIntfcBatNum, dsContrSrcRefNum, svcConfigMstrPk);
        // mod end 2016/09/13 CSA Defect#14517
        S21ApiTBLAccessor.insert(dsContrIntfcTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrIntfcTMsg.getReturnCode())) {
            this.msgMap.addXxMsgId(NSZM0972E);
        }
    }
    // END 2016/08/03 T.Tomita [QC#12230, ADD]
    // Del Start 2018/03/02 QC#23425
//    private SVC_MACH_MSTRTMsgArray getAccSvcMachMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
//        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
//        inMsg.setSQLID("005");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
//        inMsg.setConditionValue("svcMachTpCd01", SVC_MACH_TP.ACCESSORY);
//        return (SVC_MACH_MSTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
//    }
    // Del End 2018/03/02 QC#23425

    // add start 2016/09/26 CSA Defect#14674
    private void createContrIntfcForTrmn(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg
            , BigDecimal dsContrIntfcBatSq, String newDsContrIntfcBatNum, String cloDt) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        List<BigDecimal> contrInfoList = getContrInfo(glblCmpyCd
                , svcMachMstrTMsg.svcMachMstrPk.getValue());
        if (contrInfoList == null || contrInfoList.isEmpty()) {
            return;
        }

        List<DS_CONTR_INTFCTMsg> dsContrIntfcList = new ArrayList<DS_CONTR_INTFCTMsg>();

        for (BigDecimal dsContrDtlPk : contrInfoList) {
            DS_CONTR_DTLTMsg dsContrDtl = getDsContrDtl(glblCmpyCd, dsContrDtlPk);
            DS_CONTRTMsg dsContr = getDsContr(glblCmpyCd, dsContrDtl.dsContrPk.getValue());
            // Add Start 2018/03/14 QC#23427
            if (dsContr == null || existsTrmdIntfcData(svcMachMstrTMsg, dsContr)) {
                continue;
            }
            // Add End 2018/03/14 QC#23427
            DS_CONTR_INTFCTMsg baseIntfcMsg = createContrIntfcForBase(pMsg, svcMachMstrTMsg, dsContrDtl, dsContr
                    , DS_CONTR_INTFC_ACT.TERMINATE, dsContrIntfcBatSq, newDsContrIntfcBatNum, cloDt, null);
            dsContrIntfcList.add(baseIntfcMsg);

            DS_CONTR_BLLG_MTRTMsgArray bllgMtrList = getDsContrBllgMtrList(glblCmpyCd, dsContrDtl.dsContrDtlPk.getValue());
            for (int i = 0; i < bllgMtrList.getValidCount(); i++) {
                DS_CONTR_BLLG_MTRTMsg bllgMtr = bllgMtrList.no(i);
                DS_CONTR_INTFCTMsg usgIntfcMsg = createContrIntfcForUsg(pMsg, svcMachMstrTMsg, dsContrDtl, dsContr, bllgMtr
                        , baseIntfcMsg, DS_CONTR_INTFC_ACT.TERMINATE);
                dsContrIntfcList.add(usgIntfcMsg);
            }
        }

        for (DS_CONTR_INTFCTMsg dsContrIntfcTMsg : dsContrIntfcList) {
            S21ApiTBLAccessor.insert(dsContrIntfcTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrIntfcTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NSZM0972E);
            }
        }
    }

    private void createContrIntfcForAddToContr(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, BigDecimal dsContrIntfcBatSq, String newDsContrIntfcBatNum, SVC_MACH_MSTRTMsg origSvcMachMstrTMsg, String cloDt, String istlDt) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        List<BigDecimal> contrInfoList = getContrInfo(glblCmpyCd, origSvcMachMstrTMsg.svcMachMstrPk.getValue());
        if (contrInfoList == null || contrInfoList.isEmpty()) {
            return;
        }

        List<DS_CONTR_INTFCTMsg> dsContrIntfcList = new ArrayList<DS_CONTR_INTFCTMsg>();
        List<DS_XS_COPY_INTFCTMsg> xsCopyIntfcList = new ArrayList<DS_XS_COPY_INTFCTMsg>();
        List<DS_ACTL_CNT_INTFCTMsg> actlCntIntfcList = new ArrayList<DS_ACTL_CNT_INTFCTMsg>();
        List<DS_ADDL_CHRG_INTFCTMsg> addlChrgIntfcList = new ArrayList<DS_ADDL_CHRG_INTFCTMsg>();

        for (BigDecimal dsContrDtlPk : contrInfoList) {
            DS_CONTR_DTLTMsg dsContrDtl = getDsContrDtl(glblCmpyCd, dsContrDtlPk);
            DS_CONTRTMsg dsContr = getDsContr(glblCmpyCd, dsContrDtl.dsContrPk.getValue());

            DS_CONTR_INTFCTMsg baseIntfcMsg = createContrIntfcForBase(pMsg, svcMachMstrTMsg, dsContrDtl, dsContr, DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT, dsContrIntfcBatSq, newDsContrIntfcBatNum, cloDt, istlDt);
            dsContrIntfcList.add(baseIntfcMsg);

            // Add Start 2018/05/22 QC#23433
            if (hasValue(dsContr.dsContrCatgCd) && DS_CONTR_CATG.WARRANTY.equals(dsContr.dsContrCatgCd.getValue())) {
                continue;
            }
            // Add End 2018/05/22 QC#23433

            DS_CONTR_BLLG_MTRTMsgArray bllgMtrList = getDsContrBllgMtrList(glblCmpyCd, dsContrDtl.dsContrDtlPk.getValue());
            for (int i = 0; i < bllgMtrList.getValidCount(); i++) {
                DS_CONTR_BLLG_MTRTMsg bllgMtr = bllgMtrList.no(i);
                DS_CONTR_INTFCTMsg usgIntfcMsg = createContrIntfcForUsg(pMsg, svcMachMstrTMsg, dsContrDtl, dsContr, bllgMtr, baseIntfcMsg, DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
                dsContrIntfcList.add(usgIntfcMsg);

                List<Map<String, Object>> xsCopyInfoList = getXsCopyInfo(glblCmpyCd, bllgMtr.dsContrBllgMtrPk.getValue(), ZYPConstant.FLG_OFF_N);
                for (Map<String, Object> xsCopyInfo : xsCopyInfoList) {
                    DS_XS_COPY_INTFCTMsg xsCopy = createXsCopyIntfc(pMsg, svcMachMstrTMsg, dsContr, bllgMtr, baseIntfcMsg, xsCopyInfo);
                    xsCopyIntfcList.add(xsCopy);
                }

            }

            // Mod Start 2018/08/23 QC#27126
//            List<Map<String, Object>> physMtrInfoList = getPhysMtrInfo(glblCmpyCd, origSvcMachMstrTMsg.svcMachMstrPk.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue());
            List<Map<String, Object>> physMtrInfoList = getPhysMtrInfo(glblCmpyCd, dsContrDtlPk, origSvcMachMstrTMsg.svcMachMstrPk.getValue(), svcMachMstrTMsg.svcMachMstrPk.getValue());
            // Mod End 2018/08/23 QC#27126
            for (Map<String, Object> physMtrInfo : physMtrInfoList) {
                DS_ACTL_CNT_INTFCTMsg actlCnt = createActlCntIntfc(pMsg, svcMachMstrTMsg, dsContr, baseIntfcMsg, physMtrInfo);
                actlCntIntfcList.add(actlCnt);
            }

            List<Map<String, Object>> addlChrgInfoList = getAddlChrgInfo(glblCmpyCd, dsContr.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), pMsg.slsDt.getValue());
            for (Map<String, Object> addlChrgInfo : addlChrgInfoList) {
                DS_ADDL_CHRG_INTFCTMsg addlChrg = createAddlChrgIntfc(pMsg, svcMachMstrTMsg, dsContr, baseIntfcMsg, addlChrgInfo);
                addlChrgIntfcList.add(addlChrg);
            }
        }

        for (DS_CONTR_INTFCTMsg dsContrIntfcTMsg : dsContrIntfcList) {
            S21ApiTBLAccessor.insert(dsContrIntfcTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrIntfcTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NSZM0972E);
            }
        }
        for (DS_XS_COPY_INTFCTMsg xsCopyIntfcTMsg : xsCopyIntfcList) {
            S21ApiTBLAccessor.insert(xsCopyIntfcTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xsCopyIntfcTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NSZM0972E);
            }
        }
        for (DS_ACTL_CNT_INTFCTMsg actlCntIntfcTMsg : actlCntIntfcList) {
            S21ApiTBLAccessor.insert(actlCntIntfcTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(actlCntIntfcTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NSZM0972E);
            }
        }
        for (DS_ADDL_CHRG_INTFCTMsg addlChrgIntfcTMsg : addlChrgIntfcList) {
            S21ApiTBLAccessor.insert(addlChrgIntfcTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(addlChrgIntfcTMsg.getReturnCode())) {
                this.msgMap.addXxMsgId(NSZM0972E);
            }
        }
    }

    private List<BigDecimal> getContrInfo(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("canceled", DS_CONTR_CTRL_STS.CANCELLED);
        param.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        param.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
        return (List<BigDecimal>) this.ssmBatClnt.queryObjectList("getContrInfo", param);
    }

    private DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);

        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);

        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(inMsg);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setSQLID("001");

        return (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    
    // Mod Start 2019/01/11 QC#26928
//    private Map<String, Object> getCrCardPoInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("dsContrPk", dsContrPk);
//        param.put("dsContrDtlPk", dsContrDtlPk);
//        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
//        param.put("baseUsgLv", "3");
//        return (Map<String, Object>) this.ssmBatClnt.queryObject("getCrCardPoInfo", param);
//    }
    // Mod End 2019/01/11 QC#26928
    
    private Map<String, Object> getContrRnwEsclInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String dsContrBaseUsgNm) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("baseUsgLv", "3");
        param.put("dsContrBaseUsgNm", dsContrBaseUsgNm);
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getContrRnwEsclInfo", param);
    }

    private List<Map<String, Object>> getAddlChrgInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("slsDt", slsDt);
        return (List<Map<String, Object>>) this.ssmBatClnt.queryObjectList("getAddlChrgInfo", param);
    }

    private List<Map<String, Object>> getXsCopyInfo(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String xsMtrFirstFlg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        param.put("xsMtrFirstFlg", xsMtrFirstFlg);
        return (List<Map<String, Object>>) this.ssmBatClnt.queryObjectList("getXsCopyInfo", param);
    }

    // Mod Start 2018/08/23 QC#27126
//    private List<Map<String, Object>> getPhysMtrInfo(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal newSvcMachMstrPk) {
    private List<Map<String, Object>> getPhysMtrInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, BigDecimal newSvcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("newSvcMachMstrPk", newSvcMachMstrPk);
        return (List<Map<String, Object>>) this.ssmBatClnt.queryObjectList("getPhysMtrInfo", param);
    }
    // Mod End 2018/08/23 QC#27126

    private DS_CONTR_INTFCTMsg createContrIntfcForBase(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg
            , DS_CONTR_DTLTMsg dsContrDtl, DS_CONTRTMsg dsContr, String dsContrInfcActCd, BigDecimal dsContrIntfcBatSq, String newDsContrIntfcBatNum, String cloDt, String istlDt) {
        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal dsContrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ);
        setValue(inMsg.dsContrIntfcPk, dsContrIntfcPk);
        setValue(inMsg.dsContrIntfcBatNum, newDsContrIntfcBatNum);
        setValue(inMsg.dsContrSrcRefNum, dsContr.dsContrNum.getValue());
        setValue(inMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.IB_UPDATE);
        setValue(inMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
        setValue(inMsg.dsContrIntfcActCd, dsContrInfcActCd);
        setValue(inMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
        setValue(inMsg.dsContrNum, dsContr.dsContrNum);
        setValue(inMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(inMsg.contrIntfcDtlTpCd, CONTR_INTFC_DTL_TP.BASE);
        setValue(inMsg.dsAcctNum, dsContr.dsAcctNum);
        setValue(inMsg.billToCustCd, dsContrDtl.baseBillToCustCd);
        setValue(inMsg.leaseCmpyCd, dsContr.leaseCmpyCd);
        setValue(inMsg.svcContrBrCd, dsContr.svcContrBrCd);
        setValue(inMsg.tocCd, dsContr.tocCd);
     // Mod Start 2019/01/11 QC#26928
//        Map<String, Object> crCardPoMap = getCrCardPoInfo(pMsg.glblCmpyCd.getValue(), dsContr.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), null);
//        if (crCardPoMap != null) {
//            setValue(inMsg.crCardCustRefNum, (String) crCardPoMap.get("CR_CARD_CUST_REF_NUM"));
//            setValue(inMsg.crCardExprYrMth, (String) crCardPoMap.get("CR_CARD_EXPR_YR_MTH"));
//            setValue(inMsg.custPoNum, (String) crCardPoMap.get("CUST_PO_NUM"));
//            setValue(inMsg.poDt, (String) crCardPoMap.get("PO_DT"));
//        }
     // Mod End 2019/01/11 QC#26928
        setValue(inMsg.mtrEstTpCd, dsContr.mtrEstTpCd);
        setValue(inMsg.svcPgmMdseCd, dsContrDtl.svcPgmMdseCd);
        setValue(inMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        Map<String, Object> mdlInfoMap = getMdlInfoMap(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.svcConfigMstrPk.getValue());
        if (mdlInfoMap != null) {
            setValue(inMsg.mdlId, (BigDecimal) mdlInfoMap.get("MDL_ID"));
            setValue(inMsg.mdlNm, (String) mdlInfoMap.get("T_MDL_NM"));
        }
        // Mod Start 2018/05/21 QC#23433
        if (hasValue(istlDt) && ZYPDateUtil.compare(dsContrDtl.contrEffFromDt.getValue(), istlDt) <= 0) {
            setValue(inMsg.contrFromDt, istlDt);
        } else {
            setValue(inMsg.contrFromDt, dsContrDtl.contrEffFromDt);
        }
        // Mod End 2018/05/21 QC#23433
        setValue(inMsg.contrThruDt, dsContrDtl.contrEffThruDt);
        setValue(inMsg.bllgCycleCd, dsContrDtl.baseBllgCycleCd);
        setValue(inMsg.prcAllocByMachQtyFlg, dsContr.prcAllocByMachQtyFlg);
        Map<String, Object> rnwEsclInfo = getContrRnwEsclInfo(pMsg.glblCmpyCd.getValue()
                , dsContr.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), "BASE");
        if (rnwEsclInfo != null) {
            setValue(inMsg.contrAutoRnwTpCd, (String) rnwEsclInfo.get("CONTR_AUTO_RNW_TP_CD"));
            setValue(inMsg.rnwPrcMethCd, (String) rnwEsclInfo.get("RNW_PRC_METH_CD"));
            setValue(inMsg.befEndRnwDaysAot, (BigDecimal) rnwEsclInfo.get("BEF_END_RNW_DAYS_AOT"));
            setValue(inMsg.rnwPrcUpRatio, (BigDecimal) rnwEsclInfo.get("BASE_PRC_UP_RATIO"));
            setValue(inMsg.contrUplftTpCd, (String) rnwEsclInfo.get("CONTR_UPLFT_TP_CD"));
            setValue(inMsg.uplftPrcMethCd, (String) rnwEsclInfo.get("UPLFT_PRC_METH_CD"));
            setValue(inMsg.uplftPrcUpRatio, (BigDecimal) rnwEsclInfo.get("UPLFT_BASE_PRC_UP_RATIO"));
        } else {
            setValue(inMsg.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
            setValue(inMsg.contrUplftTpCd, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        }
        setValue(inMsg.basePrcDealAmt, dsContrDtl.basePrcDealAmt);
        setValue(inMsg.contrCloDay, dsContrDtl.contrCloDay);
        setValue(inMsg.contrBllgDay, dsContrDtl.contrBllgDay);
        setValue(inMsg.dsContrCatgCd, dsContr.dsContrCatgCd);
        setValue(inMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
        setValue(inMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.dsContrClsCd, dsContr.dsContrClsCd);
        setValue(inMsg.ctacPsnPk, dsContrDtl.ctacPsnPk);
        setValue(inMsg.invSeptBaseUsgFlg, dsContr.invSeptBaseUsgFlg);
        setValue(inMsg.contrCloDt, cloDt);
        setValue(inMsg.contrDurnAot, dsContr.contrDurnAot);
        setValue(inMsg.pmtTermCashDiscCd, dsContr.pmtTermCashDiscCd);
        setValue(inMsg.svcLineBizCd, dsContr.svcLineBizCd);
        setValue(inMsg.bllgTmgTpCd, dsContrDtl.baseBllgTmgCd);
        setValue(inMsg.dsContrEdiCd, dsContr.dsContrEdiCd);
        setValue(inMsg.dsContrIntfcDt, pMsg.slsDt.getValue());
        setValue(inMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.dsContrIntfcBatSq, dsContrIntfcBatSq);
        setValue(inMsg.prcSvcPlnTpCd, PRC_SVC_PLN_TP.STANDARD);
        setValue(inMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.contrAdminPsnCd, dsContr.contrAdminPsnCd);
        // Mod Start 2018/03/16 QC#23427
//        // add start 2016/10/17 CSA Defect#14868
//        if (ProcessMode.INSTALLATION.code.equals(pMsg.xxModeCd.getValue())) {
//            setValue(inMsg.oldDsContrDtlPk, dsContrDtl.dsContrDtlPk);
//            setValue(inMsg.oldSvcMachMstrPk, dsContrDtl.svcMachMstrPk);
//        }
//        // add end 2016/10/17 CSA Defect#14868
        setValue(inMsg.oldDsContrDtlPk, dsContrDtl.dsContrDtlPk);
        setValue(inMsg.oldSvcMachMstrPk, dsContrDtl.svcMachMstrPk);
        // Mod End 2018/03/16 QC#23427
        // START 2022/03/22 [QC#59683, ADD]
        setValue(inMsg.dsInvTgtrTpCd, dsContr.dsInvTgtrTpCd);
        // END   2022/03/22 [QC#59683, ADD]
        return inMsg;
    }

    private DS_CONTR_INTFCTMsg createContrIntfcForUsg(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg, DS_CONTR_DTLTMsg dsContrDtl
            , DS_CONTRTMsg dsContr, DS_CONTR_BLLG_MTRTMsg bllgMtr, DS_CONTR_INTFCTMsg baseIntfc, String dsContrInfcActCd) {
        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal dsContrIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ);
        setValue(inMsg.dsContrIntfcPk, dsContrIntfcPk);
        setValue(inMsg.dsContrIntfcBatNum, baseIntfc.dsContrIntfcBatNum);
        setValue(inMsg.dsContrSrcRefNum, dsContr.dsContrNum.getValue());
        setValue(inMsg.contrIntfcSrcTpCd, baseIntfc.contrIntfcSrcTpCd);
        setValue(inMsg.dsContrProcStsCd, baseIntfc.dsContrProcStsCd);
        setValue(inMsg.dsContrIntfcActCd, dsContrInfcActCd);
        setValue(inMsg.dsContrIntfcStsCd, baseIntfc.dsContrIntfcStsCd);
        setValue(inMsg.dsContrNum, dsContr.dsContrNum);
        setValue(inMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(inMsg.contrIntfcDtlTpCd, CONTR_INTFC_DTL_TP.USAGE);
        setValue(inMsg.dsAcctNum, baseIntfc.dsAcctNum);
        setValue(inMsg.billToCustCd, bllgMtr.bllgMtrBillToCustCd);
        setValue(inMsg.leaseCmpyCd, dsContr.leaseCmpyCd);
        setValue(inMsg.svcContrBrCd, dsContr.svcContrBrCd);
        setValue(inMsg.tocCd, dsContr.tocCd);
        // Mod Start 2019/01/11 QC#26928
//        Map<String, Object> crCardPoMap = getCrCardPoInfo(pMsg.glblCmpyCd.getValue(), dsContr.dsContrPk.getValue()
//                , dsContrDtl.dsContrDtlPk.getValue(), bllgMtr.dsContrBllgMtrPk.getValue());
//        if (crCardPoMap != null) {
//            setValue(inMsg.crCardCustRefNum, (String) crCardPoMap.get("CR_CARD_CUST_REF_NUM"));
//            setValue(inMsg.crCardExprYrMth, (String) crCardPoMap.get("CR_CARD_EXPR_YR_MTH"));
//            setValue(inMsg.custPoNum, (String) crCardPoMap.get("CUST_PO_NUM"));
//            setValue(inMsg.poDt, (String) crCardPoMap.get("PO_DT"));
//        }
        // Mod End 2019/01/11 QC#26928
        setValue(inMsg.mtrEstTpCd, baseIntfc.mtrEstTpCd);
        setValue(inMsg.svcPgmMdseCd, baseIntfc.svcPgmMdseCd);
        setValue(inMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        setValue(inMsg.mdlId, baseIntfc.mdlId);
        setValue(inMsg.mdlNm, baseIntfc.mdlNm);
        setValue(inMsg.contrFromDt, baseIntfc.contrFromDt);
        setValue(inMsg.contrThruDt, baseIntfc.contrThruDt);
        setValue(inMsg.bllgCycleCd, bllgMtr.bllgMtrBllgCycleCd);
        setValue(inMsg.prcAllocByMachQtyFlg, baseIntfc.prcAllocByMachQtyFlg);
        Map<String, Object> rnwEsclInfo = getContrRnwEsclInfo(pMsg.glblCmpyCd.getValue()
                , dsContr.dsContrPk.getValue(), dsContrDtl.dsContrDtlPk.getValue(), "USAGE");
        if (rnwEsclInfo != null) {
            setValue(inMsg.contrAutoRnwTpCd, (String) rnwEsclInfo.get("CONTR_AUTO_RNW_TP_CD"));
            setValue(inMsg.rnwPrcMethCd, (String) rnwEsclInfo.get("RNW_PRC_METH_CD"));
            setValue(inMsg.befEndRnwDaysAot, (BigDecimal) rnwEsclInfo.get("BEF_END_RNW_DAYS_AOT"));
            setValue(inMsg.rnwPrcUpRatio, (BigDecimal) rnwEsclInfo.get("BASE_PRC_UP_RATIO"));
            setValue(inMsg.contrUplftTpCd, (String) rnwEsclInfo.get("CONTR_UPLFT_TP_CD"));
            setValue(inMsg.uplftPrcMethCd, (String) rnwEsclInfo.get("UPLFT_PRC_METH_CD"));
            setValue(inMsg.uplftPrcUpRatio, (BigDecimal) rnwEsclInfo.get("UPLFT_BASE_PRC_UP_RATIO"));
        } else {
            setValue(inMsg.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
            setValue(inMsg.contrUplftTpCd, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        }
        setValue(inMsg.mtrReadMethCd, dsContrDtl.mtrReadMethCd);
        setValue(inMsg.contrCloDay, dsContrDtl.mtrCloDay);
        setValue(inMsg.contrBllgDay, dsContrDtl.mtrBllgDay);
        setValue(inMsg.bllgMtrLbCd, bllgMtr.bllgMtrLbCd);
        // Mod Start 2018/03/14 QC#23427
//        MTR_LBTMsg mtrLb = (MTR_LBTMsg) ZYPCodeDataUtil.findByCode(MTR_LB.class, pMsg.glblCmpyCd.getValue(), bllgMtr.bllgMtrLbCd.getValue());
        MTR_LBTMsg mtrLb = getMtrLb(pMsg.glblCmpyCd.getValue(), bllgMtr.bllgMtrLbCd.getValue());
        // Mod End 2018/03/14 QC#23427
        setValue(inMsg.bllgMtrLbNm, mtrLb.mtrLbNm);
        setValue(inMsg.bllgRollOverRatio, bllgMtr.bllgRollOverRatio);
        setValue(inMsg.dsContrCatgCd, baseIntfc.dsContrCatgCd);
        setValue(inMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);

        setValue(inMsg.xsChrgTpCd, bllgMtr.xsChrgTpCd);
        List<Map<String, Object>> xsCopyInfoList = getXsCopyInfo(pMsg.glblCmpyCd.getValue(), bllgMtr.dsContrBllgMtrPk.getValue(), ZYPConstant.FLG_ON_Y);
        if (xsCopyInfoList != null && !xsCopyInfoList.isEmpty()) {
            Map<String, Object> xsCopyInfo = xsCopyInfoList.get(0);
            setValue(inMsg.xsMtrCopyQty, (BigDecimal) xsCopyInfo.get("XS_MTR_COPY_QTY"));
            setValue(inMsg.xsMtrAmtRate, (BigDecimal) xsCopyInfo.get("XS_MTR_AMT_RATE"));
        }
        setValue(inMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.dsContrClsCd, baseIntfc.dsContrClsCd);
        setValue(inMsg.ctacPsnPk, baseIntfc.ctacPsnPk);
        setValue(inMsg.invSeptBaseUsgFlg, baseIntfc.invSeptBaseUsgFlg);
        setValue(inMsg.contrCloDt, baseIntfc.contrCloDt);
        setValue(inMsg.contrDurnAot, baseIntfc.contrDurnAot);
        setValue(inMsg.pmtTermCashDiscCd, baseIntfc.pmtTermCashDiscCd);
        setValue(inMsg.svcLineBizCd, baseIntfc.svcLineBizCd);
        setValue(inMsg.bllgTmgTpCd, dsContrDtl.mtrBllgTmgCd);
        setValue(inMsg.dsContrEdiCd, baseIntfc.dsContrEdiCd);
        setValue(inMsg.dsContrIntfcDt, pMsg.slsDt.getValue());
        setValue(inMsg.baseChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.usgChrgToLeaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.intgMdseCd, bllgMtr.intgMdseCd);
        setValue(inMsg.dsContrIntfcBatSq, baseIntfc.dsContrIntfcBatSq);
        setValue(inMsg.prcSvcPlnTpCd, baseIntfc.prcSvcPlnTpCd);
        setValue(inMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        setValue(inMsg.contrAdminPsnCd, baseIntfc.contrAdminPsnCd);
        // Mod Start 2018/03/16 QC#23427
//        // add start 2016/10/17 CSA Defect#14868
//        if (ProcessMode.INSTALLATION.code.equals(pMsg.xxModeCd.getValue())) {
//            setValue(inMsg.oldDsContrDtlPk, dsContrDtl.dsContrDtlPk);
//            setValue(inMsg.oldSvcMachMstrPk, dsContrDtl.svcMachMstrPk);
//        }
//        // add end 2016/10/17 CSA Defect#14868
        setValue(inMsg.oldDsContrDtlPk, dsContrDtl.dsContrDtlPk);
        setValue(inMsg.oldSvcMachMstrPk, dsContrDtl.svcMachMstrPk);
        // Mod End 2018/03/16 QC#23427
        // START 2022/03/22 [QC#59683, ADD]
        setValue(inMsg.dsInvTgtrTpCd, baseIntfc.dsInvTgtrTpCd);
        // END   2022/03/22 [QC#59683, ADD]
        return inMsg;
    }

    private DS_XS_COPY_INTFCTMsg createXsCopyIntfc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg
            ,DS_CONTRTMsg dsContr, DS_CONTR_BLLG_MTRTMsg bllgMtr, DS_CONTR_INTFCTMsg baseIntfc, Map<String, Object> xsCopyInfo) {
        DS_XS_COPY_INTFCTMsg inMsg = new DS_XS_COPY_INTFCTMsg();

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal dsXsCopyIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_XS_COPY_INTFC_SQ);
        setValue(inMsg.dsXsCopyIntfcPk, dsXsCopyIntfcPk);
        setValue(inMsg.dsContrIntfcBatNum, baseIntfc.dsContrIntfcBatNum);
        setValue(inMsg.dsContrSrcRefNum, dsContr.dsContrNum.getValue());
        setValue(inMsg.contrIntfcSrcTpCd, baseIntfc.contrIntfcSrcTpCd);
        setValue(inMsg.xsCopyIntfcStsCd, XS_COPY_INTFC_STS.NORMAL);
        setValue(inMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(inMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        setValue(inMsg.bllgMtrLbCd, bllgMtr.bllgMtrLbCd);
        // Mod Start 2018/03/14 QC#23427
//        MTR_LBTMsg mtrLb = (MTR_LBTMsg) ZYPCodeDataUtil.findByCode(MTR_LB.class, pMsg.glblCmpyCd.getValue(), bllgMtr.bllgMtrLbCd.getValue());
        MTR_LBTMsg mtrLb = getMtrLb(pMsg.glblCmpyCd.getValue(), bllgMtr.bllgMtrLbCd.getValue());
        // Mod End 2018/03/14 QC#23427
        setValue(inMsg.bllgMtrLbNm, mtrLb.mtrLbNm);
        setValue(inMsg.xsMtrCopyQty, (BigDecimal) xsCopyInfo.get("XS_MTR_COPY_QTY"));
        setValue(inMsg.xsMtrAmtRate, (BigDecimal) xsCopyInfo.get("XS_MTR_AMT_RATE"));
        setValue(inMsg.xsMtrLvlNum, "1");

        return inMsg;
    }

    private DS_ACTL_CNT_INTFCTMsg createActlCntIntfc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg
            ,DS_CONTRTMsg dsContr, DS_CONTR_INTFCTMsg baseIntfc, Map<String, Object> physMtrInfo) {
        DS_ACTL_CNT_INTFCTMsg inMsg = new DS_ACTL_CNT_INTFCTMsg();

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal actlCntIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ACTL_CNT_INTFC_SQ);
        setValue(inMsg.dsActlCntIntfcPk, actlCntIntfcPk);
        setValue(inMsg.dsContrIntfcBatNum, baseIntfc.dsContrIntfcBatNum);
        setValue(inMsg.dsContrSrcRefNum, dsContr.dsContrNum.getValue());
        setValue(inMsg.contrIntfcSrcTpCd, baseIntfc.contrIntfcSrcTpCd);
        setValue(inMsg.actlCntIntfcStsCd, ACTL_CNT_INTFC_STS.NORMAL);
        setValue(inMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(inMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        setValue(inMsg.physMtrLbCd, (String) physMtrInfo.get("MDL_MTR_LB_CD"));
        // Mod Start 2018/03/14 QC#23427
//        MTR_LBTMsg mtrLb = (MTR_LBTMsg) ZYPCodeDataUtil.findByCode(MTR_LB.class, pMsg.glblCmpyCd.getValue(), inMsg.physMtrLbCd.getValue());
        MTR_LBTMsg mtrLb = getMtrLb(pMsg.glblCmpyCd.getValue(), inMsg.physMtrLbCd.getValue());
        // Mod End 2018/03/14 QC#23427
        setValue(inMsg.physMtrLbNm, mtrLb.mtrLbNm);
        setValue(inMsg.bllblFlg, (String) physMtrInfo.get("BLLBL_FLG"));
        setValue(inMsg.contrMtrMultRate, (BigDecimal) physMtrInfo.get("CONTR_MTR_MULT_RATE"));
        setValue(inMsg.bllgMtrLbCd, (String) physMtrInfo.get("BLLG_MTR_LB_CD"));
        // START 2016/10/19 T.Tomita [QC#14868, MOD]
        // Mod Start 2018/03/14 QC#23427
//        MTR_LBTMsg bllgMtrLb = (MTR_LBTMsg) ZYPCodeDataUtil.findByCode(MTR_LB.class, pMsg.glblCmpyCd.getValue(), inMsg.bllgMtrLbCd.getValue());
        MTR_LBTMsg bllgMtrLb = getMtrLb(pMsg.glblCmpyCd.getValue(), inMsg.bllgMtrLbCd.getValue());
        // Mod End 2018/03/14 QC#23427
        setValue(inMsg.bllgMtrLbNm, bllgMtrLb.mtrLbNm);
        // END 2016/10/19 T.Tomita [QC#14868, MOD]
        setValue(inMsg.intgMdseCd, (String) physMtrInfo.get("INTG_MDSE_CD"));

        return inMsg;
    }

    private DS_ADDL_CHRG_INTFCTMsg createAddlChrgIntfc(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg
            ,DS_CONTRTMsg dsContr, DS_CONTR_INTFCTMsg baseIntfc, Map<String, Object> addlChrgInfo) {
        DS_ADDL_CHRG_INTFCTMsg inMsg = new DS_ADDL_CHRG_INTFCTMsg();

        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        BigDecimal addlChrgIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ADDL_CHRG_INTFC_SQ);
        setValue(inMsg.dsAddlChrgIntfcPk, addlChrgIntfcPk);
        setValue(inMsg.dsContrIntfcBatNum, baseIntfc.dsContrIntfcBatNum);
        setValue(inMsg.dsContrSrcRefNum, dsContr.dsContrNum.getValue());
        setValue(inMsg.contrIntfcSrcTpCd, baseIntfc.contrIntfcSrcTpCd);
        setValue(inMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.NORMAL);
        setValue(inMsg.serNum, svcMachMstrTMsg.serNum);
        setValue(inMsg.svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
        setValue(inMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        setValue(inMsg.chrgLvlTpCd, (String) addlChrgInfo.get("SVC_BILL_BY_TP_CD"));
        setValue(inMsg.addlChrgTpCd, (String) addlChrgInfo.get("ADDL_CHRG_TP_CD"));
        setValue(inMsg.effFromDt, (String) addlChrgInfo.get("EFF_FROM_DT"));
        setValue(inMsg.effThruDt, (String) addlChrgInfo.get("EFF_THRU_DT"));
        setValue(inMsg.bllgCycleCd, (String) addlChrgInfo.get("BLLG_CYCLE_CD"));
        setValue(inMsg.addlChrgFlatDealPrcAmt, (BigDecimal) addlChrgInfo.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
        setValue(inMsg.addlChrgAplcPct, (BigDecimal) addlChrgInfo.get("ADDL_CHRG_APLC_PCT"));
        setValue(inMsg.addlChrgInvTpCd, (String) addlChrgInfo.get("ADDL_CHRG_INV_TP_CD"));
        setValue(inMsg.printDtlFlg, (String) addlChrgInfo.get("PRINT_DTL_FLG"));

        return inMsg;
    }
    // add end 2016/09/26 CSA Defect#14674

    // START 2016/12/22 K.Kojima [QC#16600,ADD]
    private BigDecimal getPrntSvcMachMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrDtlPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcConfigMstrDtlPk", svcConfigMstrDtlPk);
        return (BigDecimal) ssmBatClnt.queryObject("getPrntSvcMachMstrPk", prmMap);
    }
    // END 2016/12/22 K.Kojima [QC#16600,ADD]

    // START 2017/01/05 K.Kitachi [QC#16848, ADD]
    private String convLineBizTpToSvcLineBiz(String glblCmpyCd, String lineBizTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("lineBizTpCd", lineBizTpCd);
        param.put("nullString", "NULL");
        return (String) this.ssmBatClnt.queryObject("convLineBizTpToSvcLineBiz", param);
    }
    // END 2017/01/05 K.Kitachi [QC#16848, ADD]

    // START 2017/09/14 S.Fujita [QC#18534,ADD]
    private boolean isNewSvcMachMstr(NSZC001001PMsg pMsg) {
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (hasValue(svcMachMstrTMsg.cpoOrdNum) && hasValue(svcMachMstrTMsg.cpoDtlLineNum) && hasValue(svcMachMstrTMsg.cpoDtlLineSubNum)) {
            CPO_DTLTMsg inCpoDtlMsg = new CPO_DTLTMsg();
            setValue(inCpoDtlMsg.glblCmpyCd, svcMachMstrTMsg.glblCmpyCd);
            setValue(inCpoDtlMsg.cpoOrdNum, svcMachMstrTMsg.cpoOrdNum);
            setValue(inCpoDtlMsg.cpoDtlLineNum, svcMachMstrTMsg.cpoDtlLineNum);
            setValue(inCpoDtlMsg.cpoDtlLineSubNum, svcMachMstrTMsg.cpoDtlLineSubNum);
            CPO_DTLTMsg cpoDtlTMsg = (CPO_DTLTMsg) S21ApiTBLAccessor.findByKey(inCpoDtlMsg);
            if (cpoDtlTMsg == null) {
                return true;
            }

            SWHTMsg inSwhMsg = new SWHTMsg();
            setValue(inSwhMsg.glblCmpyCd, cpoDtlTMsg.glblCmpyCd);
            setValue(inSwhMsg.rtlSwhCd, cpoDtlTMsg.rtlSwhCd);
            SWHTMsg swhTMsg = (SWHTMsg) S21ApiTBLAccessor.findByKey(inSwhMsg);
            if (swhTMsg == null) {
                return true;
            }

            if (!hasValue(swhTMsg.mdseInvtyCostPct)) {
                return true;
            }

            BigDecimal mdseInvtyCostPct = swhTMsg.mdseInvtyCostPct.getValue();
            if (mdseInvtyCostPct.compareTo(MAX_PCT) != 0) {
                return false;
            }
        }
        return true;
    }

    private String getSvcPgmMdse(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        String svcPgmMdse = BLANK;
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, svcMachMstrTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, svcMachMstrTMsg.mdseCd);
        MDSETMsg rtnMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg != null && hasValue(rtnMsg.svcWtyTpCd.getValue())) {
            svcPgmMdse = getMdseCd(rtnMsg.glblCmpyCd.getValue(), rtnMsg.svcWtyTpCd.getValue());
        }
        return svcPgmMdse;
    }

    private String getMdseCd(String glblCmpyCd, String svcWtyTpCd) {
        String mdseCd = BLANK;
        SVC_WTY_TPTMsg inMsg = new SVC_WTY_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcWtyTpCd, svcWtyTpCd);
        SVC_WTY_TPTMsg rtnMsg = (SVC_WTY_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (rtnMsg != null && hasValue(rtnMsg.svcPgmMdseCd.getValue())) {
            mdseCd = rtnMsg.svcPgmMdseCd.getValue();
        }
        return mdseCd;
    }
    // END 2017/09/14 S.Fujita [QC#18534,ADD]
    // Add Start 2017/10/10 QC#21292
    private String getPrntSerNum(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        if (!hasValue(glblCmpyCd) || !hasValue(svcConfigMstrPk)) {
            return null;
        }
        SVC_CONFIG_MSTRTMsg configTMsg = findSvcConfigMstr(glblCmpyCd, svcConfigMstrPk);
        if (configTMsg == null || !hasValue(configTMsg.svcMachMstrPk)) {
            return null;
        }
        SVC_MACH_MSTRTMsg tmsg = findSvcMachMstr(glblCmpyCd, configTMsg.svcMachMstrPk.getValue());
        if (tmsg == null) {
            return null;
        }
        return tmsg.serNum.getValue();
    }
    // Add End 2017/10/10 QC#21292

    // START 2017/10/23 K.Kitachi [QC#21796, ADD]
    private List<BigDecimal> getSvcMachMstrPkBySvcConfigMstrPk(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("svcConfigMstrPk", svcConfigMstrPk);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.INSTALLED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL);
        prmMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        prmMap.put("svcMachUsgStsCd", SVC_MACH_USG_STS.AT_CUSTOMER);
        return (List<BigDecimal>) this.ssmBatClnt.queryObjectList("getSvcMachMstrPkBySvcConfigMstrPk", prmMap);
    }
    // END 2017/10/23 K.Kitachi [QC#21796, ADD]
    // Add Start 2018/03/14 QC#23427
    private MTR_LBTMsg getMtrLb(String glblCmpyCd, String mtrLbCd) {
        if (!hasValue(mtrLbCd)) {
            return null;
        }
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mtrLbCd, mtrLbCd);
        return (MTR_LBTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private SVC_MACH_MSTRTMsg getOrigAccMachTMsg(SVC_MACH_MSTRTMsg newMachMstr, List<SVC_MACH_MSTRTMsg> origAccMachTMsgList, List<BigDecimal> procSvcMachMstrPkList) {
        String mdseCd = newMachMstr.mdseCd.getValue();
        for (SVC_MACH_MSTRTMsg origAccMachTMsg : origAccMachTMsgList) {
            if (!mdseCd.equals(origAccMachTMsg.mdseCd.getValue())) {
                continue;
            }
            if (procSvcMachMstrPkList.contains(origAccMachTMsg.svcMachMstrPk.getValue())) {
                continue;
            }
            return origAccMachTMsg;
        }
        return null;
    }

    private boolean existsTrmdIntfcData(SVC_MACH_MSTRTMsg svcMachMstrTMsg, DS_CONTRTMsg dsContrTMsg) {
        if (countTrmdIntfc(svcMachMstrTMsg, dsContrTMsg).compareTo(BigDecimal.ZERO) > 0) {
            // Exists
            return true;
        }
        // Not exists
        return false;
    }

    private BigDecimal countTrmdIntfc(SVC_MACH_MSTRTMsg svcMachMstrTMsg, DS_CONTRTMsg dsContrTMsg) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", dsContrTMsg.glblCmpyCd.getValue());
        prmMap.put("dsContrNum", dsContrTMsg.dsContrNum.getValue());
        prmMap.put("svcMachMstrPk", svcMachMstrTMsg.svcMachMstrPk.getValue());
        prmMap.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        prmMap.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.IB_UPDATE);
        return (BigDecimal) this.ssmBatClnt.queryObject("countTrmdIntfc", prmMap);
    }
    // Add End 2018/03/14 QC#23427
    // Add Start 2018/06/04 QC#26412
    private BigDecimal callSvcMdlApiByConfigPk(String glblCmpyCd, String slsDt, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray) {
        if (svcMachMstrTMsgArray.getValidCount() == 0) {
            return null;
        }

        NSZC048001 svcMdlApi = new NSZC048001();
        NSZC048001PMsg svcMdlPMsg = new NSZC048001PMsg();
        setValue(svcMdlPMsg.glblCmpyCd, glblCmpyCd);
        setValue(svcMdlPMsg.slsDt, slsDt);

        int childCnt = 0;
        for (int i = 0; i < svcMachMstrTMsgArray.getValidCount(); i++) {
            if (SVC_MACH_TP.MACHINE.equals(svcMachMstrTMsgArray.no(i).svcMachTpCd.getValue())) {
                setValue(svcMdlPMsg.prntMdseCd, svcMachMstrTMsgArray.no(i).mdseCd);
            } else if (SVC_MACH_TP.ACCESSORY.equals(svcMachMstrTMsgArray.no(i).svcMachTpCd.getValue())) {
                setValue(svcMdlPMsg.xxChildMdseCdList.no(childCnt).childMdseCd, svcMachMstrTMsgArray.no(i).mdseCd);
                childCnt++;
            }
        }
        svcMdlPMsg.xxChildMdseCdList.setValidCount(childCnt);

        if (!hasValue(svcMdlPMsg.prntMdseCd)) {
            return null;
        }

        svcMdlApi.execute(svcMdlPMsg, onBatType);
        return svcMdlPMsg.mdlId.getValue();
    }
    // Add End 2018/06/04 QC#26412
    // Add Start 2018/06/19 QC#26618
    private String getSvcBrCdByMachMstr(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        if (hasValue(svcMachMstrTMsg.finBrCd)) {
            return svcMachMstrTMsg.finBrCd.getValue();
        } else if (hasValue(svcMachMstrTMsg.fldSvcBrCd)) {
            return svcMachMstrTMsg.fldSvcBrCd.getValue();
        }
        return ZYPCodeDataUtil.getVarCharConstValue(DEFAULT_FIN_BR, svcMachMstrTMsg.glblCmpyCd.getValue());
    }

    private Map<String, Object> getContrCoaBrMap(String glblCmpyCd, String coaBrCd, String svclineBizCd) {
        if (!hasValue(coaBrCd) || !hasValue(svclineBizCd)) {
            return null;
        }
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", glblCmpyCd);
        prmMap.put("coaBrCd", coaBrCd);
        prmMap.put("svcLineBizCd", svclineBizCd);
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getContrCoaBrMap", prmMap);
    }
    // Add End 2018/06/19 QC#26618

    // START 2018/07/02 K.Kitachi [QC#27023, ADD]
    private void updateSvcConfigMstrDtlByIstl(NSZC001001PMsg pMsg, SVC_MACH_MSTRTMsg machTMsg) {
        SVC_CONFIG_MSTR_DTLTMsgArray updTMsgList = findSvcConfigMstrDtlList(pMsg.glblCmpyCd.getValue(), machTMsg.svcConfigMstrPk.getValue(), machTMsg.svcMachMstrPk.getValue());
        for (int i = 0; i < updTMsgList.getValidCount(); i++) {
            SVC_CONFIG_MSTR_DTLTMsg updTMsg = findSvcConfigMstrDtlForUpdate(pMsg.glblCmpyCd.getValue(), updTMsgList.no(i).svcConfigMstrDtlPk.getValue());
            setValue(updTMsg.istlDt, machTMsg.istlDt);
            S21FastTBLAccessor.update(updTMsg);
            String rtnCd = updTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                msgMap.addXxMsgId(NSZM0717E);
            }
        }
    }

    private SVC_CONFIG_MSTR_DTLTMsg findSvcConfigMstrDtlForUpdate(String glblCmpyCd, BigDecimal svcConfigMstrDtlPk) {
        SVC_CONFIG_MSTR_DTLTMsg prmTMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
        return (SVC_CONFIG_MSTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }
    // END 2018/07/02 K.Kitachi [QC#27023, ADD]
    // Add Start 2018/08/05 QC#27144
    private Map<String, Object> getExistsTrmdIntfc(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", svcMachMstrTMsg.glblCmpyCd.getValue());
        prmMap.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.IB_UPDATE);
        prmMap.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        prmMap.put("dsContrIntfcStsCd", DS_CONTR_INTFC_STS.SUCCESS);
        prmMap.put("cpoOrdNum", svcMachMstrTMsg.rmaNum.getValue());
        prmMap.put("dsCpoRtrnLineNum", svcMachMstrTMsg.rmaLineNum.getValue());
        prmMap.put("dsCpoRtrnLineSubNum", svcMachMstrTMsg.rmaLineSubNum.getValue());

        return (Map<String, Object>) this.ssmBatClnt.queryObject("getExistsTrmdIntfc", prmMap);
    }
    // Add End 2018/08/05 QC#27144
    // Add Start 2018/12/17 QC#28383
    private boolean trmnSvcConfigMstrDtl(NSZC001001PMsg pMsg) {
        if (hasValue(pMsg.svcConfigMstrPk)) {
            BigDecimal svcConfigMstrPk = findSvcConfigMstrForUpdTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.svcConfigMstrPk.getValue());
            updateSvcMachMstrForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return false;
            }

            updateSvcConfigMstrDtlForTrmn(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue());
            if (msgMap.getMsgMgr().isXxMsgId()) {
                return false;
            }

            if (BigDecimal.ZERO.equals(svcConfigMstrPk)) {
                removeSvcConfigMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
                if (msgMap.getMsgMgr().isXxMsgId()) {
                    return false;
                }
            }
        }
        return true;
    }
    // Add End 2018/12/17 QC#28383
    // QC#56909 Add Start
    private void rmvSvcConfigMstrDtlByIstlForSource(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcConfigMstrPk, String slsDt) {
        SVC_CONFIG_MSTR_DTLTMsgArray svcConfigMstrDtlList = findSvcConfigMstrDtlForMach(glblCmpyCd, svcMachMstrPk);
        SVC_CONFIG_MSTR_DTLTMsg tMsg;
        for (int i = 0; i < svcConfigMstrDtlList.getValidCount(); i++) {
            tMsg = svcConfigMstrDtlList.no(i);
            if (hasValue(tMsg.svcMachRmvDt)) {
                continue;
            }
            if (hasValue(tMsg.svcConfigMstrPk) && svcConfigMstrPk.equals(tMsg.svcConfigMstrPk.getValue())) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcMachRmvDt, slsDt);
                EZDTBLAccessor.update(tMsg);
                String rtrnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                    msgMap.addXxMsgId(NSZM0717E);
                }
            }
        }
    }
    // QC#56909 Add End

    // QC#59486 Add start
    private boolean isServiceExchange(String glblCmpyCd, String cpoOrdNum) {
        CPOTMsg inMsg = new CPOTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.cpoOrdNum, cpoOrdNum);
        CPOTMsg outMsg = (CPOTMsg) S21FastTBLAccessor.findByKey(inMsg);

        if (outMsg != null && ZYPCommonFunc.hasValue(outMsg.dsOrdCatgCd)) {
        	String dsOrdCatgCd = outMsg.dsOrdCatgCd.getValue();
            if (DS_ORD_CATG.SERVICE_EXCHANGE_CSA.equals(dsOrdCatgCd)) {
                return true;
            }
        }

        return false;
    }
    // QC#59486 Add end

    // QC#60023 Add start
    @SuppressWarnings("unchecked")
    private void territoryRuleAdd(NSZC001001PMsg pMsg) {
        if (!hasValue(pMsg.svcMachMstrPk)) {
            return;
        }
        String startMachMstr = "";
        if (this.preSvcMachMstrTMsg != null && pMsg.svcMachMstrPk.getValue().equals(this.preSvcMachMstrTMsg.svcMachMstrPk.getValue())) {
            startMachMstr = this.preSvcMachMstrTMsg.svcMachMstrStsCd.getValue();
        }
        SVC_MACH_MSTRTMsg endSvcMachMstrTMsg = findSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (endSvcMachMstrTMsg == null) {
            return;
        }
        String endMachMstr = endSvcMachMstrTMsg.svcMachMstrStsCd.getValue();
        if (!SVC_MACH_MSTR_STS.INSTALLED.equals(startMachMstr) && SVC_MACH_MSTR_STS.INSTALLED.equals(endMachMstr)) {
            if (!hasValue(endSvcMachMstrTMsg.sldByLineBizTpCd.getValue())) {
                return;
            }

            Map<String, Object> ssmTrtyRuleRegistrationDecision = new HashMap<String, Object>();
            ssmTrtyRuleRegistrationDecision.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmTrtyRuleRegistrationDecision.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
            ssmTrtyRuleRegistrationDecision.put("trtyRuleTpCd", TRTY_RULE_TP.LOCATION_NUMBER);
            ssmTrtyRuleRegistrationDecision.put("trtyRuleFromValTxt", endSvcMachMstrTMsg.indCurLocNum.getValue());
            ssmTrtyRuleRegistrationDecision.put("trtyRuleOprdTpCd", TRTY_RULE_OPRD_TP.EQUAL);
            ssmTrtyRuleRegistrationDecision.put("trtyRuleLogicTpCd", TRTY_RULE_LOGIC_TP.OR);
            ssmTrtyRuleRegistrationDecision.put("sldByLineBizTcCd", endSvcMachMstrTMsg.sldByLineBizTpCd.getValue());
            int ssmTrtyRuleCnt = (Integer) ssmBatClnt.queryObject("getTrtyRuleCount", ssmTrtyRuleRegistrationDecision);

            if (ssmTrtyRuleCnt != 0) {
                return;
            }

            if (!hasValue(endSvcMachMstrTMsg.cpoOrdNum)) {
                return;
            }

            String psnCd = null;
            Map<String, Object> ssmFindPsnCd = new HashMap<String, Object>();
            ssmFindPsnCd.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmFindPsnCd.put("cpoOrdNum", endSvcMachMstrTMsg.cpoOrdNum.getValue());
            psnCd = (String) ssmBatClnt.queryObject("findPsnCd", ssmFindPsnCd);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("psnCd", psnCd);
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("maxEffDt", MAX_EFF_THRU_DT);
            ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
            ssmParam.put("slsDt", pMsg.slsDt.getValue());
            List<Map<String, Object>> ssmParamUniqueList = (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getTargetOrg", ssmParam);

            if (ssmParamUniqueList.size() != 1) {
                return;
            }

            TRTY_RULETMsg inTMsg = new TRTY_RULETMsg();

            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TRTY_RULE_SQ);
            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRulePk, trtyRulePk);
            ZYPEZDItemValueSetter.setValue(inTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(inTMsg.orgCd, ssmParamUniqueList.get(0).get("ORG_CD").toString());
            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleTpCd, TRTY_RULE_TP.LOCATION_NUMBER);
            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleFromValTxt, endSvcMachMstrTMsg.indCurLocNum.getValue());
            inTMsg.trtyRuleThruValTxt.clear();
            ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, pMsg.slsDt.getValue());
            inTMsg.effThruDt.clear();
            ZYPEZDItemValueSetter.setValue(inTMsg.orgTierCd, ssmParamUniqueList.get(0).get("ORG_TIER_CD").toString());
            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleOprdTpCd, TRTY_RULE_OPRD_TP.EQUAL);
            ZYPEZDItemValueSetter.setValue(inTMsg.trtyRuleLogicTpCd, TRTY_RULE_LOGIC_TP.OR);

            EZDTBLAccessor.insert(inTMsg);

            String rtrnCd = inTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                msgMap.addXxMsgIdWithPrm(NSZM0626E, new String[] {"TRTY_RULE" });
            }
        }
    }
    // QC#60023 Add end
    // START 2023/08/23 R.Jin [QC#61808, ADD]
    private void updateSvcMachMstrForAccsoryPrnSerNum(NSZC001001PMsg pMsg) {
        if(!pMsg.svcMachTpCd.getValue().equals(SVC_MACH_TP.MACHINE) ) {
            return;
        }
        if(!hasValue(pMsg.oldSerNum) ||  pMsg.serNum.getValue().equals(pMsg.oldSerNum.getValue())) {
            return;
        }
        List<BigDecimal> svcMachMstrPkList = getSvcMachMstrPkListForPrntNum(pMsg.glblCmpyCd.getValue(), pMsg.svcConfigMstrPk.getValue());
        for(BigDecimal  svcMachMstrPk :  svcMachMstrPkList) {
            SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
            setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
            SVC_MACH_MSTRTMsg tMsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(inMsg);
            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.prntSerNum, pMsg.serNum);
                EZDTBLAccessor.update(tMsg);
                String rtrnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtrnCd)) {
                    msgMap.addXxMsgId(NSZM0717E);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<BigDecimal> getSvcMachMstrPkListForPrntNum(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMachineTpAccsory", SVC_MACH_TP.ACCESSORY);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        return (List<BigDecimal>) this.ssmBatClnt.queryObjectList("svcMachMstrPkListForPrntNum", param);
    }
    // END 2023/08/23 R.Jin [QC#61808, ADD]
}
