/**
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB022001;

import static com.canon.cusa.s21.batch.NSA.NSAB022001.constant.NSAB022001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_AUTO_RNW_TPTMsg;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_DTL_STS_VTMsg;
import business.db.DS_CONTR_DTL_STS_VTMsgArray;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_INTFCTMsg;
import business.db.DS_CONTR_INTFCTMsgArray;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_CONTR_STS_VTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.DS_SUB_CONTR_INTFCTMsg;
import business.db.DS_SUB_CONTR_INTFCTMsgArray;
import business.db.DS_SUB_CONTR_MTRTMsg;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.PRC_ALLOC_INTFCTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsgArray;
import business.db.SVC_MACH_EXCH_TRKTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.db.SVC_TERM_COND_ATTRBTMsg;
import business.db.SVC_TERM_COND_ATTRBTMsgArray;
import business.db.TECH_MSTRTMsg;
import business.db.TECH_MSTRTMsgArray;
import business.db.VAR_CHAR_CONSTTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.WAIT_ISTL_CONTRTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC102001_xxMsgIdListPMsg;
import business.parts.NSXC001001PMsg;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC010001_xxMsgIdListPMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxContrDtlListPMsgArray;
import business.parts.NSZC046001_xxContrPhysBllgMtrRelnListPMsg;
import business.parts.NSZC046001_xxContrPhysBllgMtrRelnListPMsgArray;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsgArray;
import business.parts.NSZC046001_xxDsContrAddlChrgListPMsg;
import business.parts.NSZC046001_xxDsContrAddlChrgListPMsgArray;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsgArray;
import business.parts.NSZC046001_xxDsContrCrCardPoListPMsg;
import business.parts.NSZC046001_xxDsContrCrCardPoListPMsgArray;
import business.parts.NSZC046001_xxDsContrRnwEsclListPMsg;
import business.parts.NSZC046001_xxDsContrRnwEsclListPMsgArray;
import business.parts.NSZC046001_xxMsgIdListPMsg;
import business.parts.NSZC046001_xxSvcTermCondListPMsg;
import business.parts.NSZC046001_xxSvcTermCondListPMsgArray;
import business.parts.NSZC052001PMsg;
import business.parts.NSZC052001_xxSubContrListPMsg;
import business.parts.NSZC057001PMsg;
import business.parts.NWZC194001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC052001.NSZC052001;
import com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001;
import com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NWZ.NWZC194001.NWZC194001;
import com.canon.cusa.s21.api.NWZ.NWZC194001.constant.NWZC194001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetUplftFromDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsActlCntIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsAddlChrgIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsContrIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsXsCopyIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationPrcAllocIntfc;
import com.canon.cusa.s21.common.NSX.NSXC001001.SlaInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.ValidationReturnBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC002001.DefSvcTermCondInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcTermCond;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcCreditAmtForTerminateBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcTermAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcCreditAmtForTerminate;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcTermAmt;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdForOutListInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.NSXC004001GetDefCoaTrxCd;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADD_CHRG_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CHRG_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_ACT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FSR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_ALLOC_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.START_READ_VLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_FROM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 * Create Contract From Interface Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/03/15   Hitachi         Y.Tsuchimoto    Update          QC#867,QC#868,QC#871,QC#3810,QC#869,QC#870
 * 2016/03/17   Hitachi         Y.Tsuchimoto    Update          QC#4027
 * 2016/03/17   Hitachi         Y.Tsuchimoto    Update          QC#4126
 * 2016/03/18   Hitachi         Y.Tsuchimoto    Update          QC#5556(mod only NSAB022001.xml)
 * 2016/03/22   Hitachi         T.Iwamoto       Update          QC#5543
 * 2016/03/23   Hitachi         Y.Tsuchimoto    Update          QC#5826
 * 2016/03/28   Hitachi         Y.Tsuchimoto    Update          QC#5662, QC#5822, QC#5516(mod only NSAB022001.xml)
 * 2016/03/30   Hitachi         Y.Tsuchimoto    Update          QC#6284
 * 2016/03/31   Hitachi         Y.Tsuchimoto    Update          QC#6339
 * 2016/04/04   Hitachi         Y.Tsuchimoto    Update          QC#6487
 * 2016/04/08   Hitachi         Y.Tsuchimoto    Update          QC#6764
 * 2016/04/25   Hitachi         T.Iwamoto       Update          QC#3771
 * 2016/05/24   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/24   Hitachi         Y.Tsuchimoto    Update          QC#447
 * 2016/05/27   Hitachi         T.Kanasaka      Update          QC#8335
 * 2016/06/03   Hitachi         T.Iwamoto       Update          QC#4061
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 2016/06/07   Hitachi         T.Iwamoto       Update          QC#4061
 * 2016/06/22   Hitachi         Y.Tsuchimoto    Update          QC#10586
 * 2016/06/24   Hitachi         Y.Tsuchimoto    Update          QC#10691
 * 2016/06/29   Hitachi         Y.Tsuchimoto    Update          QC#10692
 * 2016/06/30   Hitachi         T.Iwamoto       Update          QC#10661
 * 2016/07/11   Hitachi         Y.Takeno        Update          QC#11451
 * 2016/07/25   Hitachi         T.Iwamoto       Update          QC#11463
 * 2016/08/01   Hitachi         T.Iwamoto       Update          QC#12625
 * 2016/08/03   Hitachi         T.Tomita        Update          QC#12230
 * 2016/08/23   Hitachi         T.Tomita        Update          QC#13704
 * 2016/08/24   Fujitsu         S.Nakai         Update          QC#13748
 * 2016/08/25   Hitachi         T.Tomita        Update          QC#12136
 * 2016/09/21   Hitachi         K.Yamada        Update          QC#13686
 * 2016/09/27   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/12   Hitachi         T.Tomita        Update          QC#15111
 * 2016/10/17   Hitachi         T.Tomita        Update          QC#14868
 * 2016/10/26   Hitachi         A.Kohinata      Update          QC#13299
 * 2016/10/27   Hitachi         A.Kohinata      Update          QC#14338
 * 2016/10/31   Hitachi         T.Iwamoto       Update          QC#15697
 * 2016/11/01   Hitachi         T.Kanasaka      Update          QC#15136
 * 2016/11/09   Hitachi         T.Tomita        Update          QC#15453
 * 2016/12/01   Hitachi         A.Kohinata      Update          QC#16205
 * 2016/12/13   Hitachi         T.Tomita        Update          QC#16571
 * 2016/12/19   Hitachi         T.Tomita        Update          QC#16097
 * 2016/12/21   Hitachi         T.Kanasaka      Update          QC#16650
 * 2016/12/21   Hitachi         T.Kanasaka      Update          QC#16641
 * 2016/12/27   Hitachi         T.Tomita        Update          QC#16636
 * 2017/02/10   Hitachi         T.Kanasaka      Update          QC#16650
 * 2017/02/16   Hitachi         T.Kanasaka      Update          QC#16650-3
 * 2017/03/22   Hitachi         T.Aoyagi        Update          QC#18086
 * 2017/04/21   Hitachi         K.Kitachi       Update          QC#18405
 * 2017/05/16   Hitachi         K.Kitachi       Update          QC#18071
 * 2017/07/19   Hitachi         Y.Takeno        Update          QC#19986
 * 2017/07/14   Hitachi         K.Kasai         Update          QC#18780
 * 2017/07/31   Hitachi         M.Kidokoro      Update          QC#20116
 * 2017/08/17   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/09/08   Hitachi         T.Tomita        Update          QC#20986
 * 2017/09/19   Hitachi         A.Kohinata      Update          QC#19775
 * 2017/10/10   Hitachi         A.Kohinata      Update          QC#21617
 * 2017/11/17   Hitachi         M.Kidokoro      Update          QC#22606
 * 2018/03/15   Hitachi         T.Tomita        Update          QC#23427
 * 2018/05/10   Hitachi         T.Tomita        Update          QC#25438
 * 2018/05/15   Hitachi         T.Tomita        Update          QC#25438
 * 2018/05/24   Hitachi         T.Tomita        Update          QC#26196
 * 2018/05/28   Hitachi         T.Tomita        Update          QC#26380, 26382
 * 2018/05/29   Hitachi         T.Tomita        Update          QC#26379
 * 2018/06/01   Hitachi         T.Tomita        Update          QC#26379
 * 2018/06/11   Hitachi         T.Tomita        Update          QC#26441
 * 2018/06/11   CITS            M.Naito         Update          QC#22971
 * 2018/06/13   Hitachi         T.Tomita        Update          QC#26628
 * 2018/06/18   Hitachi         T.Tomita        Update          QC#25872
 * 2018/07/19   Hitachi         T.Tomita        Update          QC#26379
 * 2018/08/30   Hitachi         T.Wada          Update          QC#27102
 * 2018/08/30   Hitachi         T.Tomita        Update          QC#27231
 * 2018/09/25   Hitachi         T.Tomita        Update          QC#28360
 * 2018/12/12   Hitachi         K.Kim           Update          QC#29079
 * 2019/01/15   Hitachi         S.Kitamura      Update          QC#26928
 * 2019/01/17   Fujitsu         R.Nakamura      Update          QC#29776
 * 2019/01/18   Hitachi         T.Tomita        Update          QC#29083
 * 2019/04/15   Hitachi         K.Fujimoto      Update          QC#31137/50058
 * 2019/07/30   Hitachi         S.Kitamura      Update          QC#52193
 * 2019/12/17   Hitachi         K.Kishimoto     Update          QC#54974
 * 2022/03/22   Hitachi         K.Kishimoto     Update          QC#59683
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2022/07/14   CITS            R.Jin           Update          QC#60276
 * 2023/02/16   Hitachi         K.Watanabe      Update          QC#60913
 * 2023/02/13   CITS            R.Jin           Update          QC#61172
 * 2023/04/13   Hitachi         T.Nagae         Update          QC#60913
 * 2023/09/04   CITS            T.Aizawa        Update          QC#60739
 * 2024/02/15   CITS            R.Tamura        Update          CSA-QC#63393
 *</pre>
 */
public class NSAB022001 extends S21BatchMain {
    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String salesDate;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    // Add Start 2018/06/11 QC#26441
    private Map<BigDecimal, BigDecimal> noExchAccDtlPkMap;
    // Add End 2018/06/11 QC#26441

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Sales Date
        // Mod Start 2018/06/12 QC#26441
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PRG_ID);
        // Mod End 2018/06/12 QC#26441
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            // START 2016/06/29 [QC#10692, MOD]
            this.termCd = TERM_CD.WARNING_END;
            // END   2016/06/29 [QC#10692, MOD]
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main method
     * @param args args
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NSAB022001().executeBatch(NSAB022001.class.getSimpleName());
    }

    private void doProcess() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            List<Map<String, Object>> targetList = getDsContrIntfcList();
            List<DS_CONTR_INTFCTMsg> targetTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();

            for (int i = 0; i < targetList.size(); i++) {
                Map<String, Object> targetInfo = targetList.get(i);
                DS_CONTR_INTFCTMsg tmsg = new DS_CONTR_INTFCTMsg();
                setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(tmsg.dsContrSrcRefNum, (String) targetInfo.get("DS_CONTR_SRC_REF_NUM"));
                setValue(tmsg.contrIntfcSrcTpCd, (String) targetInfo.get("CONTR_INTFC_SRC_TP"));
                setValue(tmsg.dsContrIntfcBatNum, (String) targetInfo.get("DS_CONTR_INTFC_BAT_NUM"));
                setValue(tmsg.dsContrIntfcActCd, (String) targetInfo.get("DS_CONTR_INTFC_ACT_CD"));

                targetTMsgList.add(tmsg);
            }

            // 2016/01/22 QC#754 Y.Tsuchimoto Add Start
            for (int i = 0; i < targetTMsgList.size(); i++) {
                DS_CONTR_INTFCTMsg tmsg = targetTMsgList.get(i);
                // set default rule
                List<DS_CONTR_INTFCTMsg> dsDsContrIntfcTMsgListForDefaultRule = getDsContrIntfcForDefaultRule(tmsg);
                // mod start 2017/08/17 QC#18799
                //Map<String, Object> defRule = getDsContrIntfcDefRule(tmsg.contrIntfcSrcTpCd.getValue());
                Map<String, Object> defRule = getDsContrIntfcDefRule(dsDsContrIntfcTMsgListForDefaultRule);
                // mod end 2017/08/17 QC#18799
                for (int j = 0; j < dsDsContrIntfcTMsgListForDefaultRule.size(); j++) {
                    DS_CONTR_INTFCTMsg setTarget = dsDsContrIntfcTMsgListForDefaultRule.get(j);

                    setValue(setTarget.glblCmpyCd, setTarget.glblCmpyCd);
                    setValue(setTarget.dsContrIntfcPk, setTarget.dsContrIntfcPk);
                    setTarget = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(setTarget);
                    setDefaultRule(defRule, setTarget);
                    // Update DS_CONTR_INTFC(default rule)
                    EZDTBLAccessor.update(setTarget);
                }
                List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = getDsAddlChrgIntfc(tmsg);
                for (int k = 0; k < dsAddlChrgIntfcTMsgList.size(); k++) {
                    DS_ADDL_CHRG_INTFCTMsg targetAdditional = dsAddlChrgIntfcTMsgList.get(k);
                    DS_ADDL_CHRG_INTFCTMsg updateTmsg = new DS_ADDL_CHRG_INTFCTMsg();
                    setValue(updateTmsg.glblCmpyCd, this.glblCmpyCd);
                    setValue(updateTmsg.dsAddlChrgIntfcPk, targetAdditional.dsAddlChrgIntfcPk);
                    updateTmsg = (DS_ADDL_CHRG_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updateTmsg);
                    if (updateTmsg != null) {
                        // Update DS_ADDL_CHRG_INTFC
                        // 1.2.8.Billing Thru Defaults
                        if (!ZYPCommonFunc.hasValue(updateTmsg.effThruDt)) {
                            String billingThruDefaults = getBillingThruDefaults(defRule);
                            if (hasValue(billingThruDefaults)) {
                                setValue(updateTmsg.effThruDt, billingThruDefaults);
                            }
                        }
                        // 1.2.9.Effectivity Defaults
                        // 1.2.9.1.
                        if (ZYPCommonFunc.hasValue(updateTmsg.effFromDt)) {
                            String startMonth = getFirstMonth(updateTmsg.effFromDt.getValue(), defRule);
                            if (hasValue(startMonth)) {
                                setValue(updateTmsg.effFromDt, startMonth);
                            } else {
                                setValue(updateTmsg.effFromDt, updateTmsg.effFromDt);
                            }
                        }
                        // 1.2.9.2.
                        if (ZYPCommonFunc.hasValue(updateTmsg.effThruDt)) {
                            String endMonth = getEndMonth(updateTmsg.effThruDt.getValue(), defRule);
                            if (hasValue(endMonth)) {
                                setValue(updateTmsg.effThruDt, endMonth);
                            } else {
                                setValue(updateTmsg.effThruDt, updateTmsg.effThruDt);
                            }
                        }

                        // 1.2.9.3., 1.2.9.4.
                        if (!ZYPCommonFunc.hasValue(updateTmsg.effFromDt) && !ZYPCommonFunc.hasValue(updateTmsg.effThruDt)) {
                            String dsContrDtlFromDt = null;
                            String dsContrDtlThruDt = null;
                            setContrFromDtAndContrThruDt(tmsg, defRule, dsContrDtlFromDt, dsContrDtlThruDt);
                            setValue(updateTmsg.effFromDt, dsContrDtlFromDt);
                            setValue(updateTmsg.effThruDt, dsContrDtlThruDt);
                        }

                        EZDTBLAccessor.update(updateTmsg);
                    }
                }
            }
            // 2016/01/22 QC#754 Y.Tsuchimoto Add End

            // Del Start 2019/01/17 QC#29776
//            NSXC001001PMsg prmPmsg =  new NSXC001001PMsg();
//            ValidationReturnBean validBean =  new ValidationReturnBean();
            // Del End 2019/01/17 QC#29776
            NSZC046001PMsg nszc046001PMsg = new NSZC046001PMsg();
            List<Map<String, Object>> oldMachInfoList = new ArrayList<Map<String, Object>>();
            // Add Start 2018/05/15 QC#25438
            boolean addContrExchgFlg;
            boolean trmdExchgFlg;
            // Add End 2018/05/15 QC#25438
            for (int i = 0; i < targetTMsgList.size(); i++) {
                DS_CONTR_INTFCTMsg tmsg = targetTMsgList.get(i);
                // Add Start 2018/06/11 QC#26441
                this.noExchAccDtlPkMap = new HashMap<BigDecimal, BigDecimal>();
                // Add End 2018/06/11 QC#26441
                // Mod Start 2018/05/15 QC#25438
                addContrExchgFlg = false;
                trmdExchgFlg = false;
                if (isSvcExchngByTrmd(tmsg)) {
                    trmdExchgFlg = true;
                    if (isSkipTrmdProcForSvcExchng(tmsg)) {
                        continue;
                    }
                }
                if (isSvcExchngByAddContr(tmsg)) {
                    addContrExchgFlg = true;
                    if (isSkipAddContrProcForSvcExchng(tmsg)) {
                        continue;
                    }
                }
                // Mod End 2018/05/15 QC#25438

                // Mod Start 2019/01/17 QC#29776
//                prmPmsg = new NSXC001001PMsg();
                NSXC001001PMsg actlCntPrmPmsg =  new NSXC001001PMsg();
                nszc046001PMsg = new NSZC046001PMsg();
                boolean validationErrorFlag = false;
                // 1.3.1.Validation Actual Counter Interface Table
                List<DS_ACTL_CNT_INTFCTMsg> dsActlCntIntfcTMsgList = getDsActlCntIntfc(tmsg);
//                NSXC001001ValidationDsActlCntIntfc.validationDsActlCntIntfc(prmPmsg, dsActlCntIntfcTMsgList);
                NSXC001001ValidationDsActlCntIntfc.validationDsActlCntIntfc(actlCntPrmPmsg, dsActlCntIntfcTMsgList);
//                if (prmPmsg.xxMsgIdList.getValidCount() > 0) {
                if (actlCntPrmPmsg.xxMsgIdList.getValidCount() > 0) {
                    // validation error
//                    NSXC001001ValidationDsActlCntIntfc.updateValidationResult(prmPmsg, dsActlCntIntfcTMsgList);
                    validationErrorFlag = true;
                }
                // Mod End 2019/01/17 QC#29776

                 // 1.3.2.Validation Excess Copy Interface Table
                List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList = getDsXsCopyIntfc(tmsg);
                // Mod Start 2019/01/17 QC#29776
//                validBean = NSXC001001ValidationDsXsCopyIntfc.validationDsXsCopyIntfc(dsXsCopyIntfcTMsgList);
                ValidationReturnBean xsCopyValidBean = NSXC001001ValidationDsXsCopyIntfc.validationDsXsCopyIntfc(dsXsCopyIntfcTMsgList);
//                if (validBean.xxMsgIdList.getValidCount() > 0) {
                if (xsCopyValidBean.xxMsgIdList.getValidCount() > 0) {
                    // validation error
//                    NSXC001001ValidationDsXsCopyIntfc.updateValidationResult(validBean, dsXsCopyIntfcTMsgList);
                    validationErrorFlag = true;
                }
                // Mod End 2019/01/17 QC#29776

                // 1.3.3.Validation Additional Charge Interface Table
                List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = getDsAddlChrgIntfc(tmsg);
                // Mod Start 2019/01/17 QC#29776
//                validBean = NSXC001001ValidationDsAddlChrgIntfc.validationDsAddlChrgIntfc(dsAddlChrgIntfcTMsgList);
                ValidationReturnBean addlChrgValidBean = NSXC001001ValidationDsAddlChrgIntfc.validationDsAddlChrgIntfc(dsAddlChrgIntfcTMsgList);
//                if (validBean.xxMsgIdList.getValidCount() > 0) {
                if (addlChrgValidBean.xxMsgIdList.getValidCount() > 0) {
                    // validation error
//                    NSXC001001ValidationDsAddlChrgIntfc.updateValidationResult(validBean, dsAddlChrgIntfcTMsgList);
                    validationErrorFlag = true;
                }
                // Mod End 2019/01/17 QC#29776

                // 1.3.4.Validation Revenue Distribution Interface Table
                List<PRC_ALLOC_INTFCTMsg> prcAllocIntfcTMsgList = getPrcAllocIntfc(tmsg);
                // Mod Start 2019/01/17 QC#29776
//                NSXC001001ValidationPrcAllocIntfc.validationPrcAllocIntfc(prcAllocIntfcTMsgList);
                ValidationReturnBean prcAllocValidBean = NSXC001001ValidationPrcAllocIntfc.validationPrcAllocIntfc(prcAllocIntfcTMsgList);
//                if (prmPmsg.xxMsgIdList.getValidCount() > 0) {
                if (prcAllocValidBean.xxMsgIdList.getValidCount() > 0) {
                    // validation error
//                    NSXC001001ValidationPrcAllocIntfc.updateValidationResult(validBean, prcAllocIntfcTMsgList);
                    validationErrorFlag = true;
                }
                // Mod End 2019/01/17 QC#29776

                // 1.3.5.Validation DS Contract Interface Table
                // Mod Start 2019/01/17 QC#29776
                NSXC001001PMsg contrPrmPmsg =  new NSXC001001PMsg();
                List<DS_CONTR_INTFCTMsg> dsDsContrIntfcTMsgList = getDsContrIntfc(tmsg);
//                NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(prmPmsg, dsDsContrIntfcTMsgList);
                NSXC001001ValidationDsContrIntfc.validationDsContrIntfc(contrPrmPmsg, dsDsContrIntfcTMsgList);
//                if (prmPmsg.xxMsgIdList.getValidCount() > 0) {
                if (contrPrmPmsg.xxMsgIdList.getValidCount() > 0) {
                    // validation error
//                    NSXC001001ValidationDsContrIntfc.updateValidationResult(prmPmsg, dsDsContrIntfcTMsgList);
                    validationErrorFlag = true;
                }
                // Mod End 2019/01/17 QC#29776

                if (validationErrorFlag) {
                    // Add Start 2018/09/25 QC#28360
                    rollback();
                    // Add End 2018/09/25 QC#28360

                    // Add Start 2019/01/17 QC#29776
                    if (actlCntPrmPmsg.xxMsgIdList.getValidCount() > 0) {
                        NSXC001001ValidationDsActlCntIntfc.updateValidationResult(actlCntPrmPmsg, dsActlCntIntfcTMsgList);
                    }
                    if (xsCopyValidBean.xxMsgIdList.getValidCount() > 0) {
                        NSXC001001ValidationDsXsCopyIntfc.updateValidationResult(xsCopyValidBean, dsXsCopyIntfcTMsgList);
                    }
                    if (addlChrgValidBean.xxMsgIdList.getValidCount() > 0) {
                        updateValidationResult(addlChrgValidBean, dsAddlChrgIntfcTMsgList);
                    }
                    if (prcAllocValidBean.xxMsgIdList.getValidCount() > 0) {
                        NSXC001001ValidationPrcAllocIntfc.updateValidationResult(prcAllocValidBean, prcAllocIntfcTMsgList);
                    }
                    if (contrPrmPmsg.xxMsgIdList.getValidCount() > 0) {
                        updateValidationResult(contrPrmPmsg, dsDsContrIntfcTMsgList);
                    }
                    // Add End 2019/01/17 QC#29776

                    // Update DS_CONTR_INTFC
                    updateDsContrIntfcToError(tmsg);
                    errorCount = errorCount + 1;
                    commit();
                    continue;
                }

                // 2.1.Parameter set for DS Contract Header
                Map<String, Object> dsContrHeader = getDsContrHeader(tmsg);

                // add start 2016/12/01 QC#16205
                if (overContrThruDtOfContr(tmsg, dsContrHeader)) {
                    setDsContrParamForUpdate(nszc046001PMsg, tmsg, dsContrHeader);
                    NSZC046001 apiNSZC046001 = new NSZC046001();
                    apiNSZC046001.execute(nszc046001PMsg, ONBATCH_TYPE.BATCH);
                    if (isErrorNSZC046001API(nszc046001PMsg, tmsg)) {
                        updateDsContrIntfcToError(tmsg);
                        errorCount = errorCount + 1;
                        continue;
                    }
                    nszc046001PMsg = new NSZC046001PMsg();
                }
                // add end 2016/12/01 QC#16205
                setDsContrHeaderParam(nszc046001PMsg, tmsg, dsContrHeader);

                // 2.2.Parameter set for DS Contract Detail
                List<Map<String, Object>> dsContrDetail = getDsContrDetail(tmsg);
                // START 2016/05/20 [QC#4061, MOD]
                if (dsContrDetail.size() > 0) {
                    setValue(tmsg.manContrOvrdFlg, (String) dsContrDetail.get(0).get("MAN_CONTR_OVRD_FLG"));
                    setValue(nszc046001PMsg.manContrOvrdFlg, tmsg.manContrOvrdFlg);
                }
                // END   2016/05/20 [QC#4061, MOD]
                String dsContrDtlTpCd = getDsContrDtlTpCd(tmsg);
                setDsContrDetailParam(nszc046001PMsg, dsContrDetail, dsContrDtlTpCd);

                // 2.3.Parameter set for Contract Renewal Escalation
                List<Map<String, Object>> dsContrRenewalEscalation = getDsContrRenewalEscalation(tmsg);
                setDsContrRenewalEscalation(nszc046001PMsg, tmsg, dsContrRenewalEscalation);

                // 2.5.Parameter set for Contract Credit Card PO
                List<Map<String, Object>> contractCreditCardPo = getContractCreditCardPo(tmsg);
                // START 2017/02/10 T.Kanasaka [QC#16650, MOD]
//                setContractCreditCardPo(nszc046001PMsg, tmsg, contractCreditCardPo);
                Map<String, Object> headerCreditCardPo = setContractCreditCardPo(nszc046001PMsg, tmsg, contractCreditCardPo);
                // END 2017/02/10 T.Kanasaka [QC#16650, MOD]

                // 2.6.Contract Additional Charge
                // START 2016/05/24 [QC#4061, MOD]
                if (!isManContrOvrd(tmsg)) {
                    // Line 1
                    List<Map<String, Object>> contractAdditionalChargeTopList = getContrAdditionalCharge(tmsg);
                    // Line 2-Last
                    List<Map<String, Object>> contractAdditionalChargeList = getContrAdditionalChargeList(tmsg);
                    setContrAdditionalChargeList(nszc046001PMsg, tmsg, contractAdditionalChargeTopList, contractAdditionalChargeList);
                }
                // END   2016/05/24 [QC#4061, MOD]

                // 2016/03/15 QC#3810 Y.Tsuchimoto Mod Start
                // START 2016/05/24 [QC#4061, MOD]
                if (!isManContrOvrd(tmsg)) {
                    // 2.8.Parameter set for Contract Billing Meter(with 2.7.Check Meter Machine Flag)
                    List<Map<String, Object>> contrBillingMeter = getContrBillingMeter(tmsg);
                    setContrBillingMeterParameter(nszc046001PMsg, tmsg, contrBillingMeter);
                }
                // END   2016/05/24 [QC#4061, MOD]

                // START 2016/05/24 [QC#4061, MOD]
                if (!isManContrOvrd(tmsg)) {
                    // 2.9.Parameter set for Contract Physical Billing(with 2.7.Check Meter Machine Flag)
                    // Meter Relation
                    List<Map<String, Object>> contrPhysicalBillingMeterRelation = getContrPhysicalBillingMeterRelation(tmsg);
                    setContrPhysicalBillingMeterRelationParameter(nszc046001PMsg, tmsg, contrPhysicalBillingMeterRelation);
                }
                // END   2016/05/24 [QC#4061, MOD]

                // START 2016/05/24 [QC#4061, MOD]
                if (!isManContrOvrd(tmsg)) {
                    // 2.10.Parameter set for Contract Excess Copy(with 2.7.Check Meter Machine Flag)
                    List<Map<String, Object>> contractExcessCopyTopList = getContractExcessCopy(tmsg);
                    List<Map<String, Object>> contractExcessCopyList = getContractExcessCopyList(tmsg);
                    setContractExcessCopyParameter(nszc046001PMsg, tmsg, contractExcessCopyTopList, contractExcessCopyList);
                }
                // END   2016/05/24 [QC#4061, MOD]
                // 2016/03/15 QC#3810 Y.Tsuchimoto Mod End

                // START 2016/05/24 [QC#4061, MOD]
                // mod start 2016/10/17 CSA Defect#14868
                // 2.11.Contract Price Allocation(9seg)
                if (oldMachInfoList.size() > 0) {
                    setParameterForContractPriceAllocationForOldMach(nszc046001PMsg, oldMachInfoList);
                } else if (!isManContrOvrd(tmsg)) {
                    setParameterForContractPriceAllocation(nszc046001PMsg, tmsg);
                }
                // mod start 2016/10/17 CSA Defect#14868
                // END   2016/05/24 [QC#4061, MOD]

                // 2.12.Parameter set for Term & Condition
                // mod start 2016/10/17 CSA Defect#14868
                if (oldMachInfoList.size() > 0) {
                    List<SVC_TERM_CONDTMsg> svcTermCondList;
                    for (Map<String, Object> oldMachInfo : oldMachInfoList) {
                        svcTermCondList = getTermConditionForOldMach(oldMachInfo);
                        setParameterForTermConditionForOldMach(nszc046001PMsg, svcTermCondList, oldMachInfo);
                    }
                } else {
                    List<Map<String, Object>> termConditionList = getTermCondition(tmsg);
                    setParameterForTermCondition(nszc046001PMsg, termConditionList, tmsg);
                }
                // mod end 2016/10/17 CSA Defect#14868
                // Mod Start 2018/05/15 QC#25438
                if (addContrExchgFlg) {
                    // Mod Start 2018/07/19 QC#26379
                    if (!setNSZC046001PMsgForSvcExchng(nszc046001PMsg, tmsg)) {
                        errorCount = errorCount + 1;
                        continue;
                    }
                    // Mod End 2018/07/19 QC#26379
                }
                // Mod End 2018/05/15 QC#25438
                // Add Start 2018/05/21 QC#23433
                if (trmdExchgFlg) {
                    // Mod Start 2018/05/24 QC#26196
                    editModeByTerminated(nszc046001PMsg, tmsg);
                    // Mod End 2018/05/24 QC#26196
                // START 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
                } else {
                    if (DS_CONTR_INTFC_ACT.TERMINATE.equals(tmsg.dsContrIntfcActCd.getValue())) {
                        editModeByTerminatedOnReturn(nszc046001PMsg, tmsg);
                    }
                }
                // END 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
                // Add End 2018/05/21 QC#23433

                // 2.13.Call Service Contract Data Update API
                NSZC046001 apiNSZC046001 = new NSZC046001();
                apiNSZC046001.execute(nszc046001PMsg, ONBATCH_TYPE.BATCH);
                if (isErrorNSZC046001API(nszc046001PMsg, tmsg)) {
                    updateDsContrIntfcToError(tmsg);
                    errorCount = errorCount + 1;
                    continue;
                } else {
                    normalCount = normalCount + 1;
                    // update status(to complete)
                    updateDsContrIntfcToComplete(tmsg);
                }

                // Add Start 2018/05/18 QC#23433
                if (addContrExchgFlg) {
                    // Mod Start 2018/05/24 QC#26196
                    updateContrForExch(nszc046001PMsg, tmsg);
                    // Mod End 2018/05/24 QC#26196
                }
                // Add End 2018/05/18 QC#23433
                // Add Start 2018/05/28 QC#26382
                if (trmdExchgFlg) {
                    trmdContrForExch(nszc046001PMsg, tmsg);
                }
                // Add End 2018/05/28 QC#26382
                // add start 2016/10/17 CSA Defect#14868
                // Create Sub Contract
                createSubContr(oldMachInfoList);
                // add end 2016/10/17 CSA Defect#14868

                // START 2017/02/10 T.Kanasaka [QC#16650, ADD]
                createContractCreditCardPoForFleetAggregate(nszc046001PMsg.dsContrPk.getValue(), headerCreditCardPo, dsContrHeader);
                // END 2017/02/10 T.Kanasaka [QC#16650, ADD]

                // START 2016/03/17 Y.Tsuchimoto [QC#4126 MOD]
                // 4.Create Physical Meter Read
                // mod start 2017/09/19 QC#19775
                // Mod Start 2018/05/17 QC#25438
                if (!DS_CONTR_INTFC_ACT.DELETE.equals(tmsg.dsContrIntfcActCd.getValue()) && !DS_CONTR_INTFC_ACT.TERMINATE.equals(tmsg.dsContrIntfcActCd.getValue())) {
                    Map<String, Object> defRuleMap = getDsContrIntfcDefRuleForMtrRead(tmsg);
                    if (defRuleMap != null && START_READ_VLD_TP.INSERT_READING.equals((String) defRuleMap.get("START_READ_VLD_TP_CD"))) {
                    // mod end 2017/09/19 QC#19775
                        // mod start 2017/08/17 QC#18799
                        String istlCallMtrReadUseFlg = (String) defRuleMap.get("ISTL_CALL_MTR_READ_USE_FLG");
                        List<Map<String, Object>> dsContrListForNSZC0100List = new ArrayList<Map<String, Object>>();
                        if (ZYPConstant.FLG_ON_Y.equals(istlCallMtrReadUseFlg)) {
                            dsContrListForNSZC0100List = getDsContrListForNSZC0100IstlCall(tmsg);
                        }
                        if (dsContrListForNSZC0100List.isEmpty()) {
                            dsContrListForNSZC0100List = getDsContrListForNSZC0100(tmsg);
                        }
                        // mod end 2017/08/17 QC#18799
    
                        NSZC010001 apiNSZC010001 = new NSZC010001();
                        List<NSZC010001PMsg> nszc010001PMsgList = new ArrayList<NSZC010001PMsg>();
                        setParameterForNSZC010001(nszc010001PMsgList, dsContrListForNSZC0100List);
                        for (int j = 0; j < nszc010001PMsgList.size(); j++) {
                            NSZC010001PMsg nszc010001PMsg = (NSZC010001PMsg) nszc010001PMsgList.get(j);
                            apiNSZC010001.execute(nszc010001PMsg, ONBATCH_TYPE.BATCH);
                            if (S21ApiUtil.getXxMsgIdList(nszc010001PMsg).size() > 0) {
                                // update DS_CONTR_INTFC
                                updateDsContrIntfc(nszc010001PMsg, tmsg, true);
                            }
                        }
                    }
                }
                // Mod End 2018/05/17 QC#25438
                // END   2016/03/17 Y.Tsuchimoto [QC#4126 MOD]

                // START 2016/11/01 T.Kanasaka [QC#15136, MOD]
                // START 2016/05/25 [QC#447, ADD]
                // 4.3.Create Term&Condition for SLA
                if (DS_CONTR_INTFC_ACT.CREATE.equals(tmsg.dsContrIntfcActCd.getValue()) || DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
                    // Default Term&Condition
                    BigDecimal dsContrPk = nszc046001PMsg.dsContrPk.getValue();

                    // Contract Level
                    if (!NSXC002001SvcTermCond.isExistTermCondForContrLvl(this.glblCmpyCd, dsContrPk)) {
                        List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanList = NSXC002001SvcTermCond.getTermCondInfoForContrLvl(this.glblCmpyCd, this.salesDate, nszc046001PMsg.svcPgmMdseCd.getValue());
                        for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanList) {
                            NSZC046001_xxSvcTermCondListPMsg tAndCMsg = new NSZC046001_xxSvcTermCondListPMsg();
                            ZYPEZDItemValueSetter.setValue(tAndCMsg.svcTermCondAttrbPk, defSvcTermCondInfoBean.getSvcTermCondAttrbPk());
                            ZYPEZDItemValueSetter.setValue(tAndCMsg.svcTermAttrbMapValCd, defSvcTermCondInfoBean.getSvcTermAttrbMapValCd());
                            createTermsAndConditions(nszc046001PMsg, tAndCMsg, null);
                        }
                    }

                    Map<String, List<DefSvcTermCondInfoBean>> defSvcTermCondMap = new HashMap<String, List<DefSvcTermCondInfoBean>>();
                    for (int j = 0; j < nszc046001PMsg.xxContrDtlList.getValidCount(); j++) {
                        NSZC046001_xxContrDtlListPMsg xxContrDtlListForSla = (NSZC046001_xxContrDtlListPMsg) nszc046001PMsg.xxContrDtlList.get(j);
                        if(DS_CONTR_DTL_TP.FLEET.equals(xxContrDtlListForSla.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(xxContrDtlListForSla.dsContrDtlTpCd.getValue())){
                            continue;
                        }

                        // Machine Level
                        BigDecimal dsContrDtlPk = xxContrDtlListForSla.dsContrDtlPk.getValue();
                        if (!NSXC002001SvcTermCond.isExistTermCondForMachLvl(this.glblCmpyCd, dsContrPk, dsContrDtlPk)) {
                            String svcPgmMdseCd = null;

                            if (DS_CONTR_CATG.FLEET.equals(nszc046001PMsg.dsContrCatgCd.getValue())) {
                                svcPgmMdseCd = getSvcPgmMdseCdForFleet(nszc046001PMsg);
                            } else {
                                svcPgmMdseCd = xxContrDtlListForSla.svcPgmMdseCd.getValue();
                            }

                            if (ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                                List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanList = null;
                                if (defSvcTermCondMap.containsKey(svcPgmMdseCd)) {
                                    defSvcTermCondInfoBeanList = defSvcTermCondMap.get(svcPgmMdseCd);
                                } else {
                                    defSvcTermCondInfoBeanList = NSXC002001SvcTermCond.getTermCondInfoForMachLvl(this.glblCmpyCd, this.salesDate, svcPgmMdseCd);
                                    defSvcTermCondMap.put(svcPgmMdseCd, defSvcTermCondInfoBeanList);
                                }

                                for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanList) {
                                    NSZC046001_xxSvcTermCondListPMsg tAndCMsg = new NSZC046001_xxSvcTermCondListPMsg();
                                    ZYPEZDItemValueSetter.setValue(tAndCMsg.svcTermCondAttrbPk, defSvcTermCondInfoBean.getSvcTermCondAttrbPk());
                                    ZYPEZDItemValueSetter.setValue(tAndCMsg.svcTermAttrbMapValCd, defSvcTermCondInfoBean.getSvcTermAttrbMapValCd());
                                    createTermsAndConditions(nszc046001PMsg, tAndCMsg, dsContrDtlPk);
                                }
                            }
                        }

                        // SLA Overwrite
                        SlaInfoBean slaBean = new SlaInfoBean();
                        setSlaInfoBean(slaBean, tmsg, xxContrDtlListForSla.dsContrDtlPk.getValue());
                        if (NSXC001001GetRspTime.getSLA(slaBean)) {
                            String slaMinutes = slaBean.getTermCondOptValTxt();
                            if (!hasValue(slaMinutes)) {
                                continue;
                            }
                            for (int k = 0; k < SLA_TERM_COND_VARCHAR_CONST_KEY.length; k++) {
                                String varCharConstKey = SLA_TERM_COND_VARCHAR_CONST_KEY[k];
                                NSZC046001_xxSvcTermCondListPMsg tAndCMsg = new NSZC046001_xxSvcTermCondListPMsg();
                                setTAndCMsg(tAndCMsg, varCharConstKey, slaMinutes);
                                if (hasValue(tAndCMsg.svcTermCondAttrbPk)) {
                                    createTermsAndConditionsForSla(nszc046001PMsg, tAndCMsg, xxContrDtlListForSla.dsContrDtlPk.getValue());
                                }
                            }
                        }
                    }
                }
                // END 2016/05/25 [QC#447, ADD]
                // END 2016/11/01 T.Kanasaka [QC#15136, MOD]

                // START 2016/03/17 Y.Tsuchimoto [QC#867 ADD]
                // 5.QA Validation
                NSZC057001 apiNSZC057001 = new NSZC057001();
                NSZC057001PMsg nszc057001PMsg = new NSZC057001PMsg();
                setParameterForNSZC057001(tmsg, nszc046001PMsg, nszc057001PMsg);
                // START 2017/11/17 M.Kidokoro [QC#22606, MOD]
//                if (nszc057001PMsg != null) {
                if ((!DS_CONTR_INTFC_ACT.TERMINATE.equals(tmsg.dsContrIntfcActCd.getValue()) && !DS_CONTR_INTFC_ACT.DELETE.equals(tmsg.dsContrIntfcActCd.getValue())) && nszc057001PMsg != null) {
                // END 2017/11/17 M.Kidokoro [QC#22606, MOD]
                    apiNSZC057001.execute(nszc057001PMsg, ONBATCH_TYPE.BATCH);
                }
                // END   2016/03/17 Y.Tsuchimoto [QC#867 ADD]
                //START 2017/07/18 K.Kasai [QC#18780,ADD]
                // Mod Start 2017/09/08 QC#20986
                String vldRslt = NSZC057001Constant.SUCCESS;
                if (hasValue(nszc057001PMsg.dsContrVldRsltMsgTxt)) {
                    vldRslt = nszc057001PMsg.dsContrVldRsltMsgTxt.getValue();
                }
                // Mod End 2017/09/08 QC#20986
                //END 2017/07/18 K.Kasai [QC#18780,ADD]

                // Mod Start 2018/05/15 QC#25438
                if (addContrExchgFlg) {
                    continue;
                } else if (trmdExchgFlg) {
                    int errCnt = countErrDsContrVld(nszc046001PMsg.dsContrPk.getValue());
                    vldRslt = NSZC057001Constant.SUCCESS;
                    if (errCnt > 0) {
                        vldRslt = NSZC057001Constant.ERROR;
                    }
                    // START 2024/02/15 R.Tamura [CSA-QC#63393,MOD]
                    if (i + 1 < targetTMsgList.size()) {
                        DS_CONTR_INTFCTMsg nextTmsg = targetTMsgList.get(i + 1);
                        if (tmsg.dsContrSrcRefNum.getValue().equals(nextTmsg.dsContrSrcRefNum.getValue()) && tmsg.contrIntfcSrcTpCd.getValue().equals(nextTmsg.contrIntfcSrcTpCd.getValue())
                                && tmsg.dsContrIntfcActCd.getValue().equals(nextTmsg.dsContrIntfcActCd.getValue()) && isSvcExchngByTrmd(nextTmsg)) {
                            continue;
                        }
                    }
                    // END 2024/02/15 R.Tamura [CSA-QC#63393,MOD]
                }
                // Mod End 2018/05/15 QC#25438

                // add start 2016/06/06 CSA Defect#1523, 4624
                callContrTrkAPI(nszc046001PMsg.dsContrPk.getValue());
                // add end 2016/06/06 CSA Defect#1523, 4624

                // 6.13.Contract Status Update
                //START 2017/07/18 K.Kasai [QC#18780,MOD]
//                updateContractStatus(tmsg, oldMachInfoList);
                updateContractStatus(tmsg, oldMachInfoList, vldRslt);
                //END 2017/07/18 K.Kasai [QC#18780,MOD]

                // add start 2016/06/06 CSA Defect#1523, 4624
                callContrTrkAPI(nszc046001PMsg.dsContrPk.getValue());
                // add end 2016/06/06 CSA Defect#1523, 4624

                // add start 2017/10/10 QC#21617
                createWaitIstlContr(nszc046001PMsg);
                // add end 2017/10/10 QC#21617

                // START 2022/06/01 [QC#59973, ADD]
                // Schdule Agreement Update for Terminate
                if ((DS_CONTR_INTFC_ACT.TERMINATE.equals(tmsg.dsContrIntfcActCd.getValue()) && !trmdExchgFlg)) {
                    for (int j = 0; j < nszc046001PMsg.xxContrDtlList.getValidCount(); j++) {
                        NSZC046001_xxContrDtlListPMsg xxContrDtlListForSla = (NSZC046001_xxContrDtlListPMsg) nszc046001PMsg.xxContrDtlList.get(j);
                        // START 2022/07/14 R.Jin [QC#60276, ADD]
                        String dsContrTpCd = getContractDtlTpCd(xxContrDtlListForSla.dsContrDtlPk.getValue());
                        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrTpCd)) {
                            continue;
                        }
                        // END 2022/07/14 R.Jin [QC#60276, ADD]
                        callSchdAgmtAdjContrApi(NWZC194001Constant.MODE_TERMINATE, glblCmpyCd, xxContrDtlListForSla.svcMachMstrPk.getValue(), xxContrDtlListForSla.dsContrPk.getValue());
                    }
                }
                // END 2022/06/01 [QC#59973, ADD]

                commit();
            }

            // 8.Sub Contract INSERT
            // 8.1.get DS_SUB_CONTR_INTFC
            List<DS_SUB_CONTR_INTFCTMsg> dsSubContrList = getDsSubContrIntfcList();
            for (int i = 0; i < dsSubContrList.size(); i++) {
                DS_SUB_CONTR_INTFCTMsg dsSubContr = (DS_SUB_CONTR_INTFCTMsg) dsSubContrList.get(i);
                DS_CONTR_INTFCTMsgArray dsContrIntfcList = new DS_CONTR_INTFCTMsgArray();
                // 8.2.get DS_CONTR_INTFC
                if (DS_CONTR_INTFC_ACT.CREATE.equals(dsSubContr.dsContrIntfcActCd.getValue()) && !hasValue(dsSubContr.dsContrNum)) {
                    dsContrIntfcList = getDsContrForDsSubContract(dsSubContr.contrIntfcSrcTpCd.getValue(), dsSubContr.dsContrSrcRefNum.getValue());
                    if (dsContrIntfcList.getValidCount() > 0) {
                        DS_CONTR_INTFCTMsg tmsg = (DS_CONTR_INTFCTMsg) dsContrIntfcList.get(0);
                        if (tmsg != null) {
                            setValue(dsSubContr.dsContrNum, tmsg.dsContrNum);
                        }
                    }
                }
                // 8.3.Validation
                // Validation Check(mandatory)
                StringBuffer errorMsgTxt = new StringBuffer();

                if (!hasValue(dsSubContr.contrIntfcSrcTpCd)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_CONTR_INTFC_SRC_TP_CD }));
                }
                if (!hasValue(dsSubContr.dsContrSrcRefNum)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_DS_CONTR_SRC_REF_NUM }));
                }
                if (!hasValue(dsSubContr.dsContrIntfcActCd)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_DS_CONTR_INTFC_ACT_CD }));
                }
                if (!hasValue(dsSubContr.serNum)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_SER_NUM }));
                }
                if (!hasValue(dsSubContr.dsContrNum)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_DS_CONTR_NUM }));
                }
                if (!hasValue(dsSubContr.vndCd)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_VND_CD }));
                }
                if (!hasValue(dsSubContr.techCd)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_TECH_CD }));
                }
                if (!hasValue(dsSubContr.effFromDt)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_EFF_FROM_DT }));
                }
                if (!hasValue(dsSubContr.effThruDt)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NMAM0836E, new String[] {PARAM_EFF_THRU_DT }));
                }

                // START 2016/06/22 [QC#10586, MOD]
                // Validation Check(exists)
                if (hasValue(dsSubContr.dsContrNum) && !isExistDsContr(dsSubContr.dsContrNum.getValue())) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NFCM0721E, new String[] {PARAM_DS_CONTR_NUM, PARAM_DS_CONTR_NUM2 }));
                }
                if (hasValue(dsSubContr.serNum) && !isExistSvcMachMstr(dsSubContr.serNum.getValue())) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NFCM0721E, new String[] {PARAM_SER_NUM, PARAM_SER_NUM2 }));
                }
                if (hasValue(dsSubContr.vndCd) && !isExistVnd(dsSubContr.vndCd.getValue())) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NFCM0721E, new String[] {PARAM_VND_CD, PARAM_VND_CD2 }));
                }
                if (hasValue(dsSubContr.techCd) && !isExistTechMstr(dsSubContr.techCd.getValue())) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NFCM0721E, new String[] {PARAM_TECH_CD, PARAM_TECH_CD2 }));
                }
                // END   2016/06/22 [QC#10586, MOD]
                // Validation Check(integrity)
                if (DS_CONTR_INTFC_ACT.DELETE.equals(dsSubContr.dsContrIntfcActCd.getValue())) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NSAM0389E));
                }
                if (!isIntegrityEffectiveDate(dsSubContr)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NSBM0024E));
                }

                // START 2016/06/22 [QC#10586, MOD]
                // Validation Check(header)
                if (!isCheckDsContrIntfcForDsContrIntfcBatNum(dsContrIntfcList)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NSAM0390E, new String[] {PARAM_DS_CONTR_INTFC_BAT_NUM, PARAM_DS_CONTR_SRC_REF_NUM }));
                }
                if (!isCheckDsContrIntfcForDsContrNum(dsContrIntfcList)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NSAM0390E, new String[] {PARAM_DS_CONTR_NUM, PARAM_DS_CONTR_SRC_REF_NUM }));
                }
                if (!isCheckDsContrIntfcForDsContrIntfcActCd(dsContrIntfcList)) {
                    errorMsgTxt.append(S21MessageFunc.clspGetMessage(NSAM0390E, new String[] {PARAM_DS_CONTR_INTFC_ACT_CD, PARAM_DS_CONTR_SRC_REF_NUM }));
                }
                // END   2016/06/22 [QC#10586, MOD]
                if (errorMsgTxt != null && errorMsgTxt.length() > 0) {
                    // Update
                    setValue(dsSubContr.subContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                    setValue(dsSubContr.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                    if (errorMsgTxt.length() > MAX_LENGTH_SVC_CMN_TXT) {
                        setValue(dsSubContr.svcCmntTxt, errorMsgTxt.substring(0, MAX_LENGTH_SVC_CMN_TXT));
                    } else {
                        setValue(dsSubContr.svcCmntTxt, errorMsgTxt.toString());
                    }
                    EZDTBLAccessor.update(dsSubContr);
                    continue;
                }

                // START 2016/06/22 [QC#10586, MOD]
                // 8.4. Set parameter for NSZC0520:Sub Contract Update API
                List<Map<String, Object>> existDsContrListForNSZC0520List = null;
                if (!DS_CONTR_INTFC_ACT.CREATE.equals(dsSubContr.dsContrIntfcActCd.getValue())) {
                    existDsContrListForNSZC0520List = getDsContrListForNSZC0520(dsSubContr);

                    // 8.5. Call NSZC0520:Sub Contract Update API
                    NSZC052001PMsg nszc052001PMsg = new NSZC052001PMsg();
                    setParameterForNSZC0520(nszc052001PMsg, dsSubContr, existDsContrListForNSZC0520List);
                    NSZC052001 apiNSZC052001 = new NSZC052001();
                    apiNSZC052001.execute(nszc052001PMsg, ONBATCH_TYPE.BATCH);
                    if (S21ApiUtil.getXxMsgIdList(nszc052001PMsg).size() > 0) {
                        List<String> messageList = S21ApiUtil.getXxMsgIdList(nszc052001PMsg);
                        for (int j = 0; j < messageList.size(); j++) {
                            String messageId = messageList.get(j);
                            errorMsgTxt.append(S21MessageFunc.clspGetMessage(messageId));
                        }
                        setValue(dsSubContr.subContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                        if (errorMsgTxt.length() > MAX_LENGTH_SVC_CMN_TXT) {
                            setValue(dsSubContr.svcCmntTxt, errorMsgTxt.substring(0, MAX_LENGTH_SVC_CMN_TXT));
                        } else {
                            setValue(dsSubContr.svcCmntTxt, errorMsgTxt.toString());
                        }
                        EZDTBLAccessor.update(dsSubContr);
                        // 8.6.SVC_MEMO Insert
                        insertSvcMemo(nszc046001PMsg, nszc052001PMsg, dsSubContr);
                        // 8.7.Sub Contract Update
                        updateDsSubContrIntfc(nszc052001PMsg, true);
                        continue;
                    }

                    // 8.6.SVC_MEMO Insert
                    insertSvcMemo(nszc046001PMsg, nszc052001PMsg, dsSubContr);
                    // 8.7.Ds Sub Contract Status Update
                    updateDsSubContrIntfc(nszc052001PMsg, false);
                }
                // END   2016/06/22 [QC#10586, MOD]
            }
            commit();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void updateDsContrIntfcToComplete(DS_CONTR_INTFCTMsg tmsg) {
        List<Map<String, Object>> updateList = getDsContrIntfcListForUpdateComplete(tmsg);
        for (int i = 0; i < updateList.size(); i++) {
            Map<String, Object> update = updateList.get(i);
            DS_CONTR_INTFCTMsg target = new DS_CONTR_INTFCTMsg();

            setValue(target.glblCmpyCd, (String) update.get("GLBL_CMPY_CD"));
            setValue(target.dsContrIntfcPk, (BigDecimal) update.get("DS_CONTR_INTFC_PK"));
            target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(target);
            if (target != null) {
                // Update DS_CONTR_PROC_STS_CD
                setValue(target.dsContrProcStsCd, DS_CONTR_PROC_STS.COMPLEATED);
                EZDTBLAccessor.update(target);
            }
        }
    }

    private List<Map<String, Object>> getDsContrIntfcList() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcList", param);
    }

    private List<DS_ACTL_CNT_INTFCTMsg> getDsActlCntIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<DS_ACTL_CNT_INTFCTMsg>) ssmBatchClient.queryObjectList("getActlCntIntfc", param);
    }

    private List<DS_XS_COPY_INTFCTMsg> getDsXsCopyIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<DS_XS_COPY_INTFCTMsg>) ssmBatchClient.queryObjectList("getDsXsCopyIntfc", param);
    }

    private List<DS_ADDL_CHRG_INTFCTMsg> getDsAddlChrgIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<DS_ADDL_CHRG_INTFCTMsg>) ssmBatchClient.queryObjectList("getDsAddlChrgIntfc", param);
    }

    private List<PRC_ALLOC_INTFCTMsg> getPrcAllocIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<PRC_ALLOC_INTFCTMsg>) ssmBatchClient.queryObjectList("getPrcAllocIntfc", param);
    }

    private List<DS_CONTR_INTFCTMsg> getDsContrIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<DS_CONTR_INTFCTMsg>) ssmBatchClient.queryObjectList("getDsContrIntfc", param);
    }

    // 2016/01/22 QC#754 Y.Tsuchimoto Add Start
    private List<DS_CONTR_INTFCTMsg> getDsContrIntfcForDefaultRule(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", tmsg.glblCmpyCd.getValue());
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrIntfcActCd", tmsg.dsContrIntfcActCd.getValue());

        return (List<DS_CONTR_INTFCTMsg>) ssmBatchClient.queryObjectList("getDsContrIntfcForDefaultRule", param);
    }

    // mod start 2017/08/17 QC#18799
    //private Map<String, Object> getDsContrIntfcDefRule(String contrIntfcSrcTpCd) {
    private Map<String, Object> getDsContrIntfcDefRule(List<DS_CONTR_INTFCTMsg> tMsgList) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("contrIntfcSrcTpCd", contrIntfcSrcTpCd);
        if (tMsgList == null || tMsgList.size() == 0) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("contrIntfcSrcTpCd", tMsgList.get(0).contrIntfcSrcTpCd.getValue());
        param.put("dsContrClsCd", tMsgList.get(0).dsContrClsCd.getValue());
        param.put("svcLineBizCd", tMsgList.get(0).svcLineBizCd.getValue());
        param.put("salesDate", this.salesDate);

        return (Map<String, Object>) ssmBatchClient.queryObject("getDsContrIntfcDefRule", param);
    }
    // mod end 2017/08/17 QC#18799
    // 2016/01/22 QC#754 Y.Tsuchimoto Add End

    private Map<String, Object> getDsContrHeader(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrHeader", param);
        if (rs.size() > 0) {
            return rs.get(0);
        }
        return null;
    }

    private List<Map<String, Object>> getDsContrDetail(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("base", CONTR_INTFC_DTL_TP.BASE);
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        // add start 2016/10/20 CSA Defect#14868
        param.put("canc", DS_CONTR_DTL_STS.CANCELLED);
        param.put("trmd", DS_CONTR_DTL_STS.TERMINATED);
        param.put("expd", DS_CONTR_DTL_STS.EXPIRED);
        // add end 2016/10/20 CSA Defect#14868
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrDetail", param);
    }

    private List<Map<String, Object>> getDsContrRenewalEscalation(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("lvl1", "1");
        param.put("lvl3", "3");
        // add start 2017/08/17 QC#18799
        param.put("salesDate", this.salesDate);
        // add end 2017/08/17 QC#18799
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrRenewalEscalation", param);
    }

    private List<Map<String, Object>> getContractCreditCardPo(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        // START 2016/12/21 T.Kanasaka [QC#16650, DEL]
//        param.put("creditCard", DS_PMT_METH.CREDIT_CARD);
        // END 2016/12/21 T.Kanasaka [QC#16650, DEL]
        // START 2017/02/10 T.Kanasaka [QC#16650, ADD]
        param.put("base", CONTR_INTFC_DTL_TP.BASE);
        // END 2017/02/10 T.Kanasaka [QC#16650, ADD]

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractCreditCardPo", param);
    }

    private void setDsContrHeaderParam(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> dsContrHeader) {
        if (nszc046001PMsg != null && tmsg != null && dsContrHeader != null) {
            setValue(nszc046001PMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(nszc046001PMsg.xxModeCd, getXxModeCdForLevel1(tmsg.dsContrIntfcActCd.getValue()));
            setValue(nszc046001PMsg.slsDt, this.salesDate);
            setValue(nszc046001PMsg.dsContrSrcTpCd, tmsg.contrIntfcSrcTpCd.getValue());
            setValue(nszc046001PMsg.dsContrSrcTpCd_HD, tmsg.contrIntfcSrcTpCd.getValue());
            setValue(nszc046001PMsg.psnCd, getPsnCd((String) dsContrHeader.get("EZUPUSERID")));
            setValue(nszc046001PMsg.dsContrPk, (BigDecimal) dsContrHeader.get("DS_CONTR_PK"));
            setValue(nszc046001PMsg.dsContrNum, (String) dsContrHeader.get("DS_CONTR_NUM"));
            setValue(nszc046001PMsg.dsContrSqNum, "00");
            // mod start 2016/08/03 CSA Defect#12230
            String dsContrCatgCd = (String) dsContrHeader.get("DS_CONTR_CATG_CD");
            if (hasValue(dsContrCatgCd) && DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
                setValue(nszc046001PMsg.dsContrTpCd, DS_CONTR_TP.WARRANTY);
            } else {
                setValue(nszc046001PMsg.dsContrTpCd, DS_CONTR_TP.SERVICE);
            }
            setValue(nszc046001PMsg.dsContrCatgCd, dsContrCatgCd);
            // mod end 2016/08/03 CSA Defect#12230
            setValue(nszc046001PMsg.dsContrStsCd, (String) dsContrHeader.get("DS_CONTR_STS_CD"));
            // START 2016/11/09 T.Tomita [QC#15453, MOD]
//            setValue(nszc046001PMsg.contrVrsnEffFromDt, (String) dsContrHeader.get("CONTR_FROM_DT"));
//            setValue(nszc046001PMsg.contrVrsnEffThruDt, (String) dsContrHeader.get("CONTR_THRU_DT"));
            setValue(nszc046001PMsg.contrVrsnEffFromDt, getMinContrFromDt(tmsg));
            setValue(nszc046001PMsg.contrVrsnEffThruDt, getMaxContrThruDt(tmsg));
            // END 2016/11/09 T.Tomita [QC#15453, MOD]
            setValue(nszc046001PMsg.billToCustCd, (String) dsContrHeader.get("BILL_TO_CUST_CD"));

            setValue(nszc046001PMsg.custPoNum, (String) dsContrHeader.get("CUST_PO_NUM"));
            setValue(nszc046001PMsg.poDt, (String) dsContrHeader.get("PO_DT"));
            setValue(nszc046001PMsg.pmtTermCashDiscCd, (String) dsContrHeader.get("PMT_TERM_CASH_DISC_CD"));
            setValue(nszc046001PMsg.svcContrRefCmntTxt, tmsg.dsContrSrcRefNum.getValue());
            // START 2016/12/21 T.Kanasaka [QC#16641, DEL]
//            setValue(nszc046001PMsg.contrAutoRnwTpCd, (String) dsContrHeader.get("CONTR_AUTO_RNW_TP_CD"));
//            setValue(nszc046001PMsg.rnwPrcMethCd, (String) dsContrHeader.get("RNW_PRC_METH_CD"));
//            String contrIntfcSrcTpCd = tmsg.contrIntfcSrcTpCd.getValue();
//            if (CONTR_INTFC_DTL_TP.BASE.equals(contrIntfcSrcTpCd)) {
//                setValue(nszc046001PMsg.basePrcUpRatio, (BigDecimal) dsContrHeader.get("RNW_PRC_UP_RATIO"));
//            }
//            if (CONTR_INTFC_DTL_TP.USAGE.equals(contrIntfcSrcTpCd)) {
//                setValue(nszc046001PMsg.mtrPrcUpRatio, (BigDecimal) dsContrHeader.get("RNW_PRC_UP_RATIO"));
//            }
            // END 2016/12/21 T.Kanasaka [QC#16641, DEL]
            setValue(nszc046001PMsg.bllgApvlReqFlg, ZYPConstant.FLG_OFF_N);
            setValue(nszc046001PMsg.ccyCd, (String) dsContrHeader.get("CCY_CD"));
            setValue(nszc046001PMsg.preInvReqFlg, ZYPConstant.FLG_OFF_N);
            setValue(nszc046001PMsg.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
            // Start 2022/03/22 [QC#59683, Add]
            setValue(nszc046001PMsg.dsInvTgtrTpCd, (String) dsContrHeader.get("DS_INV_TGTR_TP_CD"));
            // End   2022/03/22 [QC#59683, Add]
            setValue(nszc046001PMsg.invSeptBaseUsgFlg, (String) dsContrHeader.get("INV_SEPT_BASE_USG_FLG"));
            setValue(nszc046001PMsg.dsContrCratDt, getYyyyMmDd((String) dsContrHeader.get("EZINTIME")));
            setValue(nszc046001PMsg.dsContrCratPsnCd, getPsnCd((String) dsContrHeader.get("EZINUSERID")));
            setValue(nszc046001PMsg.dsContrLastUpdDt, getYyyyMmDd((String) dsContrHeader.get("EZUPTIME")));
            setValue(nszc046001PMsg.dsContrLastUpdPsnCd, getPsnCd((String) dsContrHeader.get("EZUPUSERID")));
            setValue(nszc046001PMsg.leaseCmpyCd, (String) dsContrHeader.get("LEASE_CMPY_CD"));
            // START 2016/12/21 T.Kanasaka [QC#16641, DEL]
//            setValue(nszc046001PMsg.contrUplftTpCd, (String) dsContrHeader.get("CONTR_UPLFT_TP_CD"));
//            setValue(nszc046001PMsg.uplftPrcMethCd, (String) dsContrHeader.get("UPLFT_PRC_METH_CD"));
//            if (CONTR_INTFC_DTL_TP.BASE.equals(contrIntfcSrcTpCd)) {
//                setValue(nszc046001PMsg.uplftBasePrcRatio, (BigDecimal) dsContrHeader.get("UPLFT_PRC_UP_RATIO"));
//            }
//            if (CONTR_INTFC_DTL_TP.USAGE.equals(contrIntfcSrcTpCd)) {
//                setValue(nszc046001PMsg.uplftMtrPrcRatio, (BigDecimal) dsContrHeader.get("UPLFT_PRC_UP_RATIO"));
//            }
            // END 2016/12/21 T.Kanasaka [QC#16641, DEL]
            setValue(nszc046001PMsg.dsAcctNum, (String) dsContrHeader.get("DS_ACCT_NUM"));
            // add start 2016/06/30 CSA Defect#10661
            setValue(nszc046001PMsg.contrAdminPsnCd_HD, (String) dsContrHeader.get("CONTR_ADMIN_PSN_CD"));
            // add end 2016/06/30 CSA Defect#10661
            setValue(nszc046001PMsg.tocCd, (String) dsContrHeader.get("TOC_CD"));
            setValue(nszc046001PMsg.baseChrgToLeaseCmpyFlg, (String) dsContrHeader.get("BASE_CHRG_TO_LEASE_CMPY_FLG"));
            setValue(nszc046001PMsg.usgChrgToLeaseCmpyFlg, (String) dsContrHeader.get("USG_CHRG_TO_LEASE_CMPY_FLG"));
            setValue(nszc046001PMsg.mtrEstTpCd, (String) dsContrHeader.get("MTR_EST_TP_CD"));
            setValue(nszc046001PMsg.prcAllocByMachQtyFlg, (String) dsContrHeader.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
            setValue(nszc046001PMsg.svcContrBrCd, (String) dsContrHeader.get("SVC_CONTR_BR_CD"));
            setValue(nszc046001PMsg.dsContrClsCd, (String) dsContrHeader.get("DS_CONTR_CLS_CD"));
            BigDecimal ctacPsnPk = (BigDecimal) dsContrHeader.get("CTAC_PSN_PK");
            if (ctacPsnPk != null) {
                setValue(nszc046001PMsg.ctacPsnPk, ctacPsnPk);
            } else {
                setValue(nszc046001PMsg.ctacPsnPk, getDefaultContactPersonPk((String) dsContrHeader.get("BILL_TO_CUST_CD")));
            }
            setValue(nszc046001PMsg.svcLineBizCd, (String) dsContrHeader.get("SVC_LINE_BIZ_CD"));
            setValue(nszc046001PMsg.dsContrSrcTpCd, tmsg.contrIntfcSrcTpCd.getValue());
            setValue(nszc046001PMsg.dsContrEdiCd, (String) dsContrHeader.get("DS_CONTR_EDI_CD"));
            setValue(nszc046001PMsg.qltyAsrnHldFlg, (String) dsContrHeader.get("QLTY_ASRN_HLD_FLG"));
            setValue(nszc046001PMsg.mtrHldFlg, (String) dsContrHeader.get("MTR_HLD_FLG"));
            setValue(nszc046001PMsg.contrHldFlg, (String) dsContrHeader.get("CONTR_HLD_FLG"));
            setValue(nszc046001PMsg.bllgHldFlg, (String) dsContrHeader.get("BLLG_HLD_FLG"));
            setValue(nszc046001PMsg.svcPgmMdseCd, (String) dsContrHeader.get("SVC_PGM_MDSE_CD"));
            setValue(nszc046001PMsg.contrAdminPsnCd_HD, (String) dsContrHeader.get("CONTR_ADMIN_PSN_CD"));
            // add start 2016/10/26 QC#13299
            setValue(nszc046001PMsg.baseBllgCycleCd, (String) dsContrHeader.get("BASE_BLLG_CYCLE_CD"));
            // add end 2016/10/26 QC#13299
        }
    }

    private void setDsContrDetailParam(NSZC046001PMsg nszc046001PMsg, List<Map<String, Object>> dsContrDetail, String dsContrDtlTpCd) {
        if (nszc046001PMsg != null && dsContrDetail != null) {

            int i = 0;
            for (; i < dsContrDetail.size(); i++) {
                Map<String, Object> targetDetail = dsContrDetail.get(i);
                setValue(nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlPk, (BigDecimal) targetDetail.get("DS_CONTR_DTL_PK"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).dsContrPk, (BigDecimal) targetDetail.get("DS_CONTR_PK"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlTpCd, getDsContrDtlTpCd(targetDetail, dsContrDtlTpCd));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlStsCd, (String) targetDetail.get("DS_CONTR_DTL_STS_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).cpoOrdNum, (String) targetDetail.get("CPO_ORD_NUM"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).cpoDtlLineNum, (String) targetDetail.get("CPO_DTL_LINE_NUM"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).cpoDtlLineSubNum, (String) targetDetail.get("CPO_DTL_LINE_SUB_NUM"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).dsOrdTpCd, (String) targetDetail.get("DS_ORD_TP_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).dsOrdRsnCd, (String) targetDetail.get("DS_ORD_RSN_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).svcConfigMstrPk, (BigDecimal) targetDetail.get("SVC_CONFIG_MSTR_PK"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).svcMachMstrPk, (BigDecimal) targetDetail.get("SVC_MACH_MSTR_PK"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).contrCloDay, (String) targetDetail.get("CONTR_CLO_DAY"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).baseBllgCycleCd, (String) targetDetail.get("BASE_BLLG_CYCLE_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).baseBllgTmgCd, (String) targetDetail.get("BASE_BLLG_TMG_TP_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).contrBllgDay, (String) targetDetail.get("CONTR_BLLG_DAY"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).mtrBllgCycleCd, (String) targetDetail.get("MTR_BLLG_CYCLE_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).mtrBllgTmgCd, (String) targetDetail.get("MTR_BLLG_TMG_CD"));
                if ((((BigDecimal) targetDetail.get("BASE_PRC_DEAL_AMT")) != null)) {
                    setValue(nszc046001PMsg.xxContrDtlList.no(i).basePrcDealAmt, (BigDecimal) targetDetail.get("BASE_PRC_DEAL_AMT"));
                } else if (!ZYPConstant.FLG_ON_Y.equals(targetDetail.get("MAN_CONTR_OVRD_FLG"))) {
                    setValue(nszc046001PMsg.xxContrDtlList.no(i).basePrcDealAmt, new BigDecimal("0"));
                }
                setValue(nszc046001PMsg.xxContrDtlList.no(i).basePrcFuncAmt, (BigDecimal) targetDetail.get("BASE_PRC_FUNC_AMT"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).contrEffFromDt, (String) targetDetail.get("CONTR_FROM_DT"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).contrEffThruDt, (String) targetDetail.get("CONTR_THRU_DT"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).contrCloDt, (String) targetDetail.get("CONTR_CLO_DT"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).baseBillToCustCd, (String) targetDetail.get("BASE_BILL_TO_CUST_CD"));
                // del start 2016/10/26 QC#13299
                //setValue(nszc046001PMsg.xxContrDtlList.no(i).usgBillToCustCd, (String) targetDetail.get("USG_BILL_TO_CUST_CD"));
                // del end 2016/10/26 QC#13299
                setValue(nszc046001PMsg.xxContrDtlList.no(i).mtrCloDay, (String) targetDetail.get("CONTR_CLO_DAY"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).mtrBllgDay, (String) targetDetail.get("CONTR_BLLG_DAY"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).mtrReadMethCd, (String) targetDetail.get("MTR_READ_METH_CD"));
                String billToCustCd = (String) targetDetail.get("BASE_BILL_TO_CUST_CD");
                if (!hasValue(billToCustCd)) {
                    billToCustCd = (String) targetDetail.get("USG_BILL_TO_CUST_CD");
                }
                BigDecimal ctacPsnPk = (BigDecimal) targetDetail.get("CTAC_PSN_PK");
                if (ctacPsnPk != null) {
                    setValue(nszc046001PMsg.xxContrDtlList.no(i).ctacPsnPk, ctacPsnPk);
                } else {
                    setValue(nszc046001PMsg.xxContrDtlList.no(i).ctacPsnPk, getDefaultContactPersonPk(billToCustCd));
                }
                setValue(nszc046001PMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate, (BigDecimal) targetDetail.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).qltyAsrnHldFlg, (String) targetDetail.get("QLTY_ASRN_HLD_FLG"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).mtrHldFlg, (String) targetDetail.get("MTR_HLD_FLG"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).contrHldFlg, (String) targetDetail.get("CONTR_HLD_FLG"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).bllgHldFlg, (String) targetDetail.get("BLLG_HLD_FLG"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).svcPgmMdseCd, (String) targetDetail.get("SVC_PGM_MDSE_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).prntSvcMachMstrPk, (BigDecimal) targetDetail.get("PRNT_SVC_MACH_MSTR_PK"));
                // START 2016/04/04 [QC#6487, ADD]
                setValue(nszc046001PMsg.xxContrDtlList.no(i).prntDsContrDtlPk, (BigDecimal) targetDetail.get("PRNT_DS_CONTR_DTL_PK"));
                // END   2016/04/04 [QC#6487, ADD]
                setValue(nszc046001PMsg.xxContrDtlList.no(i).invUpToDt, (String) targetDetail.get("BLLG_THRU_DT"));
                // START 2016/03/31 [QC#6339, ADD]
                setValue(nszc046001PMsg.xxContrDtlList.no(i).supprCrFlg, getDefaultFlgN((String) targetDetail.get("SUPPR_CR_FLG")));
                // END   2016/03/31 [QC#6339, ADD]
                // add start 2016/12/01 QC#16205
                if (DS_CONTR_CATG.WARRANTY.equals((String) targetDetail.get("DS_CONTR_CATG_CD")) && DS_CONTR_DTL_TP.ACCESSORIES.equals(nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlTpCd.getValue())) {
                    if (!overContrThruDtOfMach(targetDetail)) {
                        nszc046001PMsg.xxContrDtlList.no(i).prntSvcMachMstrPk.clear();
                        nszc046001PMsg.xxContrDtlList.no(i).prntDsContrDtlPk.clear();
                    }
                }
                // add end 2016/12/01 QC#16205

                // add start 2018/08/30 QC#27102
                String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(this.glblCmpyCd
                        , nszc046001PMsg.xxContrDtlList.no(i).contrEffFromDt.getValue()
                        , (String) targetDetail.get("CONTR_INTFC_SRC_TP_CD")
                        , (String) targetDetail.get("DS_CONTR_CLS_CD") 
                        ,(String) targetDetail.get("SVC_LINE_BIZ_CD"));
                setValue(nszc046001PMsg.xxContrDtlList.no(i).uplftFromDt, uplftFromDt);
                // add end 2018/08/30   QC#27102
            }
            nszc046001PMsg.xxContrDtlList.setValidCount(i);
            // START 2022/07/14 R.Jin [QC#60276, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(nszc046001PMsg.dsContrCatgCd.getValue()) && DS_CONTR_INTFC_ACT_CD_TERMINATE.equals(nszc046001PMsg.xxModeCd.getValue())) {
                List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
                String contrCloDt = "";
                for (int j = 0; j < nszc046001PMsg.xxContrDtlList.getValidCount(); j++) {
                    dsContrDtlPkList.add(nszc046001PMsg.xxContrDtlList.no(j).dsContrDtlPk.getValue());
                    if (hasValue(nszc046001PMsg.xxContrDtlList.no(j).contrCloDt.getValue()) && nszc046001PMsg.xxContrDtlList.no(j).contrCloDt.getValue().compareTo(contrCloDt) > 0) {
                        contrCloDt = nszc046001PMsg.xxContrDtlList.no(j).contrCloDt.getValue();
                    }
                }
                BigDecimal cloDtNoSetDtlCnt = getAggAndContrCloDtDtlCnt(nszc046001PMsg.dsContrPk.getValue(), dsContrDtlPkList);
                if (BigDecimal.ZERO.compareTo(cloDtNoSetDtlCnt) == 0) {
                    DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
                    inMsg.setSQLID("006");
                    inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    inMsg.setConditionValue("dsContrPk01", nszc046001PMsg.dsContrPk.getValue());
                    inMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
                    DS_CONTR_DTLTMsgArray outTmsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
                    if (outTmsgArray.length() > 0) {
                        for (int k = 0; k < outTmsgArray.length(); k++) {
                            DS_CONTR_DTLTMsg tmsg = outTmsgArray.no(k);
                            setValue(nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlPk, tmsg.dsContrDtlPk.getValue());
                            setValue(nszc046001PMsg.xxContrDtlList.no(i).contrCloDt, getMaxCloDT(contrCloDt, nszc046001PMsg.dsContrPk.getValue()));
                            setValue(nszc046001PMsg.xxContrDtlList.no(i).supprCrFlg, ZYPConstant.FLG_OFF_N);
                            i++;
                        }
                    }
                }
            }
             // END 2022/07/14 R.Jin [QC#60276, ADD]
            nszc046001PMsg.xxContrDtlList.setValidCount(i);
        }
    }

    private String getDsContrDtlTpCd(Map<String, Object> targetDetail, String dsContrDtlTpCd) {
        if (targetDetail == null) {
            return null;
        }
        BigDecimal svcMachMstrPk = (BigDecimal) targetDetail.get("SVC_MACH_MSTR_PK");
        if (svcMachMstrPk == null) {
            if (DS_CONTR_CATG.FLEET.equals((String) targetDetail.get("DS_CONTR_CATG_CD"))) {
                return DS_CONTR_DTL_TP.FLEET;
            } else if (DS_CONTR_CATG.AGGREGATE.equals((String) targetDetail.get("DS_CONTR_CATG_CD"))) {
                return DS_CONTR_DTL_TP.AGGREGATE;
            }
        } else {
            SVC_MACH_MSTRTMsg machTmsg = getSvcMachMstr(svcMachMstrPk);
            if (machTmsg == null) {
                return null;
            }
            if (SVC_MACH_TP.ACCESSORY.equals(machTmsg.svcMachTpCd.getValue())) {
                return DS_CONTR_DTL_TP.ACCESSORIES;
            } else if (SVC_MACH_TP.MACHINE.equals(machTmsg.svcMachTpCd.getValue())) {
                // add start 2016/08/03 CSA Defect#12230
                String dsContrCatgCd = (String) targetDetail.get("DS_CONTR_CATG_CD");
                if (hasValue(dsContrCatgCd) && DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
                    return DS_CONTR_DTL_TP.BASE_ONLY;
                }
                // add end 2016/08/03 CSA Defect#12230
                if (ZYPConstant.FLG_ON_Y.equals((String) targetDetail.get("MAN_CONTR_OVRD_FLG")) && ZYPConstant.FLG_ON_Y.equals((String) targetDetail.get("MTR_REQ_MDL_FLG"))) {
                    return DS_CONTR_DTL_TP.BASE_AND_USAGE;
                }
                return dsContrDtlTpCd;
            }
        }
        return null;
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcMachMstrPk) {
        if (svcMachMstrPk == null) {
            return null;
        }
        SVC_MACH_MSTRTMsg machTmsg = new SVC_MACH_MSTRTMsg();
        setValue(machTmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(machTmsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(machTmsg);
    }

    private String getDsContrDtlTpCd(DS_CONTR_INTFCTMsg tmsg) {
        // START 2016/05/20 [QC#4061, MOD]
        if (ZYPConstant.FLG_ON_Y.equals(tmsg.manContrOvrdFlg.getValue())) {
            return DS_CONTR_DTL_TP.BASE_ONLY;
        } else {
            List<Map<String, Object>> list = getDsContrIntfcBaseAndUsage(tmsg);
            if (list != null && list.size() == 2) {
                return DS_CONTR_DTL_TP.BASE_AND_USAGE;
            } else if (list != null && list.size() == 1) {
                Map<String, Object> map = (Map<String, Object>) list.get(0);
                if (map != null && CONTR_INTFC_DTL_TP.BASE.equals((String) map.get("CONTR_INTFC_DTL_TP_CD"))) {
                    return DS_CONTR_DTL_TP.BASE_ONLY;
                } else if (map != null && CONTR_INTFC_DTL_TP.USAGE.equals((String) map.get("CONTR_INTFC_DTL_TP_CD"))) {
                    return DS_CONTR_DTL_TP.USAGE_ONLY;
                }
            }
        }
        // END   2016/05/20 [QC#4061, MOD]
        return null;
    }

    private List<Map<String, Object>> getDsContrIntfcBaseAndUsage(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcBaseAndUsage", param);
    }

    // START 2016/03/17 Y.Tsuchimoto [QC#4027 MOD]
    private void setDsContrRenewalEscalation(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> dsContrRenewalEscalation) {
        if (nszc046001PMsg == null || tmsg == null || dsContrRenewalEscalation == null || dsContrRenewalEscalation.size() == 0) {
            return;
        }
        // START 2016/04/04 [QC#6487, ADD]
        // mod start 2016/08/03 CSA Defect#12230
        String dsContrCatgCd = (String) dsContrRenewalEscalation.get(0).get("DS_CONTR_CATG_CD");
        // START 2017/05/16 K.Kitachi [QC#18071, MOD]
        if ((DS_CONTR_INTFC_ACT.UPDATE.equals(tmsg.dsContrIntfcActCd.getValue()) && !hasValue((BigDecimal) dsContrRenewalEscalation.get(0).get("DS_CONTR_RNW_ESCL_PK")))
                || (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue()))
                || (hasValue(dsContrCatgCd) && DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd))) {
        // END 2017/05/16 K.Kitachi [QC#18071, MOD]
            return;
        }
        // mod end 2016/08/03 CSA Defect#12230
        // END   2016/04/04 [QC#6487, ADD]
        setDsContrRenewalEscalationParameter(nszc046001PMsg, tmsg, dsContrRenewalEscalation.get(0), DS_CONTR_MACH_LVL_NUM_LEVEL1, true);

//        // Level1 check
//        boolean level1Flg = true;
//        boolean dsContrRnwEsclExistFlg = false;
//        Map<String, String> setKeyMap1 = new HashMap<String, String>();
//        for (int i = 0; i < dsContrRenewalEscalation.size(); i++) {
//            Map<String, Object> target = dsContrRenewalEscalation.get(i);
//            String key = getKeyForRenewalEscalationLevel1(target);
//            String value = getValueForRenewalEscalationLevel1(target);
//            if (i == 0) {
//                setKeyMap1.put(key, value);
//            }
//            if (i > 0 && !setKeyMap1.containsKey(key)) {
//                // no Level1
//                level1Flg = false;
//                break;
//            }
//            if (i > 0 && !setKeyMap1.containsValue(value)) {
//                // no Level1
//                level1Flg = false;
//                break;
//            }
//            if ((BigDecimal) target.get("DS_CONTR_RNW_ESCL_PK") != null) {
//                dsContrRnwEsclExistFlg = true;
//                break;
//            }
//        }
//
//        if (level1Flg) {
//            setDsContrRenewalEscalationParameter(nszc046001PMsg, tmsg, dsContrRenewalEscalation.get(0), DS_CONTR_MACH_LVL_NUM_LEVEL1, true);
//            return;
//        }
//
//        // Level2,3 check
//        boolean level2Flg = true;
//        boolean level3Flg = false;
//        for (int i = 0; i < dsContrRenewalEscalation.size(); i++) {
//            Map<String, Object> target = dsContrRenewalEscalation.get(i);
//            String key = getKeyForRenewalEscalationLevel3(target);
//            String value = getValueForRenewalEscalationLevel3(target);
//            String afterKey = null;
//            String afterValue = null;
//            if (i != dsContrRenewalEscalation.size() - 1) {
//                afterKey = getKeyForRenewalEscalationLevel3(dsContrRenewalEscalation.get(i + 1));
//                afterValue = getValueForRenewalEscalationLevel3(dsContrRenewalEscalation.get(i + 1));
//            }
//            if ((i == dsContrRenewalEscalation.size() - 1) || (hasValue(key) && !key.equals(afterKey))) {
//                if (level2Flg) {
//                    setDsContrRenewalEscalationParameter(nszc046001PMsg, tmsg, dsContrRenewalEscalation.get(i), DS_CONTR_MACH_LVL_NUM_LEVEL2, true);
//                } else if (level3Flg) {
//                    setDsContrRenewalEscalationParameter(nszc046001PMsg, tmsg, dsContrRenewalEscalation.get(i), DS_CONTR_MACH_LVL_NUM_LEVEL3, true);
//                }
//
//                // Flag clear
//                level2Flg = true;
//                level3Flg = false;
//            } else {
//                if (i != 0) {
//                    if (hasValue(value) && !value.equals(afterValue)) {
//                        level2Flg = false;
//                        level3Flg = true;
//                    }
//                }
//            }
//        }
    }

//    private String getKeyForRenewalEscalationLevel1(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("DS_CONTR_SRC_REF_NUM")).append(DELIMITA);
//        key.append((String) target.get("CONTR_INTFC_SRC_TP_CD"));
//        return key.toString();
//    }
//
//    private String getKeyForRenewalEscalationLevel3(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("DS_CONTR_SRC_REF_NUM")).append(DELIMITA);
//        key.append((String) target.get("CONTR_INTFC_SRC_TP_CD")).append(DELIMITA);
//        BigDecimal svcMachMstrPk = (BigDecimal) target.get("SVC_MACH_MSTR_PK");
//        if (svcMachMstrPk != null) {
//            key.append(svcMachMstrPk.toString());
//        }
//        return key.toString();
//    }
//
//    private String getValueForRenewalEscalationLevel1(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("CONTR_AUTO_RNW_TP_CD")).append(DELIMITA);
//        key.append((String) target.get("RNW_PRC_METH")).append(DELIMITA);
//        BigDecimal befEndRnwDaysAot = (BigDecimal) target.get("BEF_END_RNW_DAYS_AOT");
//        if (befEndRnwDaysAot != null) {
//            key.append(befEndRnwDaysAot.toString()).append(DELIMITA);
//        } else {
//            key.append((String) null).append(DELIMITA);
//        }
//        key.append((String) target.get("CONTR_UPLFT_TP_CD")).append(DELIMITA);
//        key.append((String) target.get("UPLFT_PRC_METH"));
//        return key.toString();
//    }
//
//    private String getValueForRenewalEscalationLevel3(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("CONTR_AUTO_RNW_TP_CD")).append(DELIMITA);
//        key.append((String) target.get("RNW_PRC_METH")).append(DELIMITA);
//        BigDecimal befEndRnwDaysAot = (BigDecimal) target.get("BEF_END_RNW_DAYS_AOT");
//        if (befEndRnwDaysAot != null) {
//            key.append(befEndRnwDaysAot.toString()).append(DELIMITA);
//        } else {
//            key.append((String) null).append(DELIMITA);
//        }
//        key.append((String) target.get("CONTR_UPLFT_TP_CD")).append(DELIMITA);
//        key.append((String) target.get("UPLFT_PRC_METH")).append(DELIMITA);
//        BigDecimal rnwPrcUpRatio = (BigDecimal) target.get("RNW_PRC_UP_RATIO");
//        if (rnwPrcUpRatio != null) {
//            key.append(rnwPrcUpRatio.toString());
//        } else {
//            key.append((String) null);
//        }
//
//        return key.toString();
//    }
//    // END   2016/03/17 Y.Tsuchimoto [QC#4027 MOD]

    private void setDsContrRenewalEscalationParameter(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> dsContrRenewalEscalation, String dsContrMachLvlNum, boolean baseFlg) {
        if (nszc046001PMsg != null && dsContrRenewalEscalation != null && ZYPCommonFunc.hasValue(dsContrMachLvlNum)) {
            int idx = nszc046001PMsg.xxDsContrRnwEsclList.getValidCount();
            boolean isRnwBasFlg = isRnwBase((String) dsContrRenewalEscalation.get("CONTR_AUTO_RNW_TP_CD"));
            boolean isRnwUsageFlg = isRnwUsage((String) dsContrRenewalEscalation.get("CONTR_AUTO_RNW_TP_CD"));
            boolean isUplftBaseFlg = isUplftBase((String) dsContrRenewalEscalation.get("CONTR_UPLFT_TP_CD"));
            boolean isUplftUsageFlg = isUplftUsage((String) dsContrRenewalEscalation.get("CONTR_UPLFT_TP_CD"));

            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).dsContrRnwEsclPk, (BigDecimal) dsContrRenewalEscalation.get("DS_CONTR_RNW_ESCL_PK"));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).dsContrPk, (BigDecimal) dsContrRenewalEscalation.get("DS_CONTR_PK"));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).dsContrDtlPk, (BigDecimal) dsContrRenewalEscalation.get("DS_CONTR_DTL_PK"));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).dsContrMachLvlNum, dsContrMachLvlNum);
            String dsContrBaseUsgNm = null;
            String dsContrDtlTpCd = (String) dsContrRenewalEscalation.get("DS_CONTR_DTL_TP_CD");
            if (hasValue(dsContrMachLvlNum) && DS_CONTR_MACH_LVL_NUM_LEVEL3.equals(dsContrMachLvlNum) && hasValue(dsContrDtlTpCd) && DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd)) {
                dsContrBaseUsgNm = DS_CONTR_BASE_USG_NM_BASE;
            } else if (hasValue(dsContrMachLvlNum) && DS_CONTR_MACH_LVL_NUM_LEVEL3.equals(dsContrMachLvlNum) && hasValue(dsContrDtlTpCd) && DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd)) {
                dsContrBaseUsgNm = DS_CONTR_BASE_USG_NM_OVERAGE;
            }
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).dsContrBaseUsgNm, dsContrBaseUsgNm);
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).contrAutoRnwTpCd, (String) dsContrRenewalEscalation.get("CONTR_AUTO_RNW_TP_CD"));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).rnwPrcMethCd, (String) dsContrRenewalEscalation.get("RNW_PRC_METH_CD"));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).befEndRnwDaysAot, (BigDecimal) dsContrRenewalEscalation.get("BEF_END_RNW_DAYS_AOT"));
            if (isRnwBasFlg) {
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).basePrcUpRatio, (BigDecimal) dsContrRenewalEscalation.get("RNW_PRC_UP_RATIO"));
            }
            if (isRnwUsageFlg) {
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).mtrPrcUpRatio, (BigDecimal) dsContrRenewalEscalation.get("RNW_PRC_UP_RATIO"));
            }
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).contrUplftTpCd, (String) dsContrRenewalEscalation.get("CONTR_UPLFT_TP_CD"));
            setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).uplftPrcMethCd, (String) dsContrRenewalEscalation.get("UPLFT_PRC_METH_CD"));
            if (isUplftBaseFlg) {
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).uplftBasePrcUpRatio, (BigDecimal) dsContrRenewalEscalation.get("UPLFT_PRC_UP_RATIO"));
            }
            if (isUplftUsageFlg) {
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).uplftMtrPrcUpRatio, (BigDecimal) dsContrRenewalEscalation.get("UPLFT_PRC_UP_RATIO"));
            }

            // START 2018/06/11 M.Naito [QC#22971, ADD]
            if (DS_CONTR_INTFC_ACT.CREATE.equals(tmsg.dsContrIntfcActCd.getValue())) {
                setUplftFlg(nszc046001PMsg.xxDsContrRnwEsclList.no(idx), (String) dsContrRenewalEscalation.get("CONTR_FROM_DT"), (String) dsContrRenewalEscalation.get("CONTR_THRU_DT"));
            } else {
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).firstYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("FIRST_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).scdYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("SCD_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).thirdYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("THIRD_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).frthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("FRTH_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).fifthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("FIFTH_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).sixthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("SIXTH_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).svnthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("SVNTH_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).eighthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("EIGHTH_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).ninthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("NINTH_YR_CONTR_UPLFT_FLG")));
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).tenthYrContrUplftFlg, getDefaultFlgN((String) dsContrRenewalEscalation.get("TENTH_YR_CONTR_UPLFT_FLG")));
            }
            // END 2018/06/11 M.Naito [QC#22971, ADD]

            // mod start 2017/08/17 QC#18799
            //setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).uplftBefEndRnwDaysAot, (BigDecimal) dsContrRenewalEscalation.get("BEF_END_RNW_DAYS_AOT"));
            String contrUplftTpCd = (String) dsContrRenewalEscalation.get("CONTR_UPLFT_TP_CD");
            if (ZYPCommonFunc.hasValue(contrUplftTpCd) && !CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(contrUplftTpCd)) {
                BigDecimal befEndUplftDaysAot = (BigDecimal) dsContrRenewalEscalation.get("BEF_END_UPLFT_DAYS_AOT");
                if (!ZYPCommonFunc.hasValue(befEndUplftDaysAot)) {
                    befEndUplftDaysAot = BigDecimal.ZERO;
                }
                setValue(nszc046001PMsg.xxDsContrRnwEsclList.no(idx).uplftBefEndRnwDaysAot, befEndUplftDaysAot);
            }
            // mod end 2017/08/17 QC#18799
            nszc046001PMsg.xxDsContrRnwEsclList.setValidCount(idx + 1);
        }
    }

    private boolean isRnwBase(String contrAutoRnwTpCd) {
        CONTR_AUTO_RNW_TPTMsg tmsg = getRnwTp(contrAutoRnwTpCd);
        if (tmsg != null) {
            return ZYPConstant.FLG_ON_Y.equals(tmsg.rnwBaseFlg.getValue());
        }
        return false;
    }

    private boolean isRnwUsage(String contrAutoRnwTpCd) {
        CONTR_AUTO_RNW_TPTMsg tmsg = getRnwTp(contrAutoRnwTpCd);
        if (tmsg != null) {
            return ZYPConstant.FLG_ON_Y.equals(tmsg.rnwUsgFlg.getValue());
        }
        return false;
    }

    private CONTR_UPLFT_TPTMsg getUplftTp(String contrUplftTpCd) {
        // START 2016/12/13 T.Tomita [QC#16571, ADD]
        if (!hasValue(contrUplftTpCd)) {
            return null;
        }
        // END 2016/12/13 T.Tomita [QC#16571, ADD]
        CONTR_UPLFT_TPTMsg uplftTp = new CONTR_UPLFT_TPTMsg();
        setValue(uplftTp.glblCmpyCd, this.glblCmpyCd);
        setValue(uplftTp.contrUplftTpCd, contrUplftTpCd);

        return (CONTR_UPLFT_TPTMsg) S21CodeTableAccessor.findByKey(uplftTp);
    }

    private boolean isUplftBase(String contrUplftTpCd) {
        CONTR_UPLFT_TPTMsg tmsg = getUplftTp(contrUplftTpCd);
        if (tmsg != null) {
            return ZYPConstant.FLG_ON_Y.equals(tmsg.uplftBaseFlg.getValue());
        }
        return false;
    }

    private boolean isUplftUsage(String contrUplftTpCd) {
        CONTR_UPLFT_TPTMsg tmsg = getUplftTp(contrUplftTpCd);
        if (tmsg != null) {
            return ZYPConstant.FLG_ON_Y.equals(tmsg.uplftUsgFlg.getValue());
        }
        return false;
    }

    private CONTR_AUTO_RNW_TPTMsg getRnwTp(String contrAutoRnwTpCd) {
        // START 2017/04/21 K.Kitachi [QC#18405, ADD]
        if (!hasValue(contrAutoRnwTpCd)) {
            return null;
        }
        // END 2017/04/21 K.Kitachi [QC#18405, ADD]
        CONTR_AUTO_RNW_TPTMsg rnwTp = new CONTR_AUTO_RNW_TPTMsg();
        setValue(rnwTp.glblCmpyCd, this.glblCmpyCd);
        setValue(rnwTp.contrAutoRnwTpCd, contrAutoRnwTpCd);

        return (CONTR_AUTO_RNW_TPTMsg) S21CodeTableAccessor.findByKey(rnwTp);
    }

//    private String getKeyForCreditCardPoLevel1(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("DS_CONTR_SRC_REF_NUM")).append(DELIMITA);
//        key.append((String) target.get("CONTR_INTFC_SRC_TP_CD"));
//        return key.toString();
//    }
//
//    private String getKeyForCreditCardPoLevel3(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("DS_CONTR_SRC_REF_NUM")).append(DELIMITA);
//        key.append((String) target.get("CONTR_INTFC_SRC_TP_CD")).append(DELIMITA);
//        BigDecimal svcMachMstrPk = (BigDecimal) target.get("SVC_MACH_MSTR_PK");
//        if (svcMachMstrPk != null) {
//            key.append(svcMachMstrPk.toString());
//        }
//        return key.toString();
//    }
//
//    private String getValueForCreditCardPoLevel1and3(Map<String, Object> target) {
//        if (target == null) {
//            return null;
//        }
//        StringBuffer key = new StringBuffer();
//        key.append((String) target.get("CR_CARD_CUST_REF_NUM")).append(DELIMITA);
//        key.append((String) target.get("CUST_PO_NUM")).append(DELIMITA);
//        key.append((String) target.get("PO_DT"));
//        return key.toString();
//    }

    // START 2017/02/10 T.Kanasaka [QC#16650, MOD]
    // START 2016/03/17 Y.Tsuchimoto [QC#4027 MOD]
    private Map<String, Object> setContractCreditCardPo(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> contractCreditCardPo) {
        if (nszc046001PMsg == null || tmsg == null || contractCreditCardPo == null || contractCreditCardPo.size() == 0) {
            return null;
        }

        // mod start 2016/08/03 CSA Defect#12230
        String dsContrCatgCd = (String) contractCreditCardPo.get(0).get("DS_CONTR_CATG_CD");
        if (hasValue(dsContrCatgCd) && DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            return null;
        }
        // mod end 2016/08/03 CSA Defect#12230

        // Level-1
        Map<String, Object> headerCreditCardPo = contractCreditCardPo.get(0);
        int idx = nszc046001PMsg.xxDsContrCrCardPoList.getValidCount();
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).xxModeCd, getXxModeCdForLevel1(tmsg.dsContrIntfcActCd.getValue()));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrCrCardPoPk, (BigDecimal) headerCreditCardPo.get("DS_CONTR_CR_CARD_PO_PK"));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrPk, (BigDecimal) headerCreditCardPo.get("DS_CONTR_PK"));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).crCardCustRefNum, (String) headerCreditCardPo.get("CR_CARD_CUST_REF_NUM"));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).crCardExprYrMth, (String) headerCreditCardPo.get("CR_CARD_EXPR_YR_MTH"));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).custPoNum, (String) headerCreditCardPo.get("CUST_PO_NUM"));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).poDt, (String) headerCreditCardPo.get("PO_DT"));
        setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).leaseCmpyFlg, (String) headerCreditCardPo.get("LEASE_CMPY_FLG"));
        nszc046001PMsg.xxDsContrCrCardPoList.setValidCount(idx + 1);

        // Level-2(Machine)
        for (int i = 0; i < contractCreditCardPo.size(); i++) {
            Map<String, Object> target = contractCreditCardPo.get(i);
            idx = nszc046001PMsg.xxDsContrCrCardPoList.getValidCount();
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrCrCardPoPk, (BigDecimal) target.get("DS_CONTR_CR_CARD_PO_PK"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrPk, (BigDecimal) target.get("DS_CONTR_PK"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrDtlPk, (BigDecimal) target.get("DS_CONTR_DTL_PK"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).crCardCustRefNum, (String) target.get("CR_CARD_CUST_REF_NUM"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).crCardExprYrMth, (String) target.get("CR_CARD_EXPR_YR_MTH"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).custPoNum, (String) target.get("CUST_PO_NUM"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).poDt, (String) target.get("PO_DT"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).leaseCmpyFlg, (String) target.get("LEASE_CMPY_FLG"));
            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).svcMachMstrPk, (BigDecimal) target.get("SVC_MACH_MSTR_PK"));
            nszc046001PMsg.xxDsContrCrCardPoList.setValidCount(idx + 1);
        }

        return headerCreditCardPo;

//        // Level1 check
//        boolean level1Flg = true;
//        Map<String, String> setKeyMap1 = new HashMap<String, String>();
//        for (int i = 0; i < contractCreditCardPo.size(); i++) {
//            Map<String, Object> target = contractCreditCardPo.get(i);
//            String key = getKeyForCreditCardPoLevel1(target);
//            String value = getValueForCreditCardPoLevel1and3(target);
//            if (i == 0) {
//                setKeyMap1.put(key, value);
//            }
//            if (i > 0 && !setKeyMap1.containsKey(key)) {
//                // no Level1
//                level1Flg = false;
//                break;
//            }
//            if (i > 0 && !setKeyMap1.containsValue(value)) {
//                // no Level1
//                level1Flg = false;
//                break;
//            }
//        }
//        if (level1Flg) {
//            Map<String, Object> setTarget = contractCreditCardPo.get(0);
//            setContractCreditCardPoParameter(nszc046001PMsg, tmsg, setTarget, DS_CONTR_MACH_LVL_NUM_LEVEL1);
//            return;
//        }
//
//        // Level2,3 check
//        boolean level2Flg = true;
//        boolean level3Flg = false;
//        for (int i = 0; i < contractCreditCardPo.size(); i++) {
//            Map<String, Object> target = contractCreditCardPo.get(i);
//            String key = getKeyForCreditCardPoLevel3(target);
//            String value = getValueForCreditCardPoLevel1and3(target);
//            String afterKey = null;
//            String afterValue = null;
//            if (i != contractCreditCardPo.size() - 1) {
//                afterKey = getKeyForCreditCardPoLevel3(contractCreditCardPo.get(i + 1));
//                afterValue = getValueForCreditCardPoLevel1and3(contractCreditCardPo.get(i + 1));
//            }
//
//            if ((i == contractCreditCardPo.size() - 1) || (hasValue(key) && !key.equals(afterKey))) {
//                if (level2Flg) {
//                    setContractCreditCardPoParameter(nszc046001PMsg, tmsg, target, DS_CONTR_MACH_LVL_NUM_LEVEL2);
//                } else if (level3Flg) {
//                    setContractCreditCardPoParameter(nszc046001PMsg, tmsg, target, DS_CONTR_MACH_LVL_NUM_LEVEL3);
//                }
//
//                // Flag clear
//                level2Flg = true;
//                level3Flg = false;
//            } else {
//                if (i != 0) {
//                    if (hasValue(value) && !value.equals(afterValue)) {
//                        level2Flg = false;
//                        level3Flg = true;
//                    }
//                }
//            }
//        }
    }
    // END   2016/03/17 Y.Tsuchimoto [QC#4027 MOD]
    // END 2017/02/10 T.Kanasaka [QC#16650, MOD]

    // START 2016/12/21 T.Kanasaka [QC#16650, DEL]
//    private void setContractCreditCardPoParameter(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> contractCreditCardPo, String dsContrMachLvlNum) {
//        if (nszc046001PMsg != null && contractCreditCardPo != null && tmsg != null && ZYPCommonFunc.hasValue(dsContrMachLvlNum)) {
//            int idx = nszc046001PMsg.xxDsContrCrCardPoList.getValidCount();
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrCrCardPoPk, (BigDecimal) contractCreditCardPo.get("DS_CONTR_CR_CARD_PO_PK"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrPk, (BigDecimal) contractCreditCardPo.get("DS_CONTR_PK"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrDtlPk, (BigDecimal) contractCreditCardPo.get("DS_CONTR_DTL_PK"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrBllgMtrPk, (BigDecimal) contractCreditCardPo.get("DS_CONTR_BLLG_MTR_PK"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).crCardCustRefNum, (String) contractCreditCardPo.get("CR_CARD_CUST_REF_NUM"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).crCardExprYrMth, (String) contractCreditCardPo.get("CR_CARD_EXPR_YR_MTH"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).custPoNum, (String) contractCreditCardPo.get("CUST_PO_NUM"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).poDt, (String) contractCreditCardPo.get("PO_DT"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).leaseCmpyFlg, (String) contractCreditCardPo.get("LEASE_CMPY_FLG"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).dsContrMachLvlNum, dsContrMachLvlNum);
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).svcMachMstrPk, (BigDecimal) contractCreditCardPo.get("SVC_MACH_MSTR_PK"));
//            setValue(nszc046001PMsg.xxDsContrCrCardPoList.no(idx).bllgMtrLbCd, (String) contractCreditCardPo.get("BLLG_MTR_LB_CD"));
//            nszc046001PMsg.xxDsContrCrCardPoList.setValidCount(idx + 1);
//        }
//    }
    // END 2016/12/21 T.Kanasaka [QC#16650, DEL]

    // START 2017/02/10 T.Kanasaka [QC#16650, ADD]
    private void createContractCreditCardPoForFleetAggregate(BigDecimal dsContrPk, Map<String, Object> headerCreditCardPo, Map<String, Object> dsContrHeader) {
        String dsContrCatgCd = (String) dsContrHeader.get("DS_CONTR_CATG_CD");
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            return;
        }

        // START 2017/02/16 T.Kanasaka [QC#16650-3, ADD]
        if (headerCreditCardPo == null) {
            return;
        }
        // END 2017/02/16 T.Kanasaka [QC#16650-3, ADD]

        Map<String, Object> fleetAggregateLineForPoCard = getContrFleetAggregateLineForPoCard(dsContrPk);
        if (fleetAggregateLineForPoCard == null) {
            return;
        }

        String leaseCmpyFlg = (String) headerCreditCardPo.get("LEASE_CMPY_FLG");
        if (!hasValue(leaseCmpyFlg)) {
            leaseCmpyFlg = ZYPConstant.FLG_OFF_N;
        }

        DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
        BigDecimal dsContrCrCardPoPk = (BigDecimal) dsContrHeader.get("DS_CONTR_CR_CARD_PO_PK");
        if (dsContrCrCardPoPk == null) {
            dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, (BigDecimal) fleetAggregateLineForPoCard.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, (String) headerCreditCardPo.get("CR_CARD_CUST_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCardExprYrMth, (String) headerCreditCardPo.get("CR_CARD_EXPR_YR_MTH"));
            ZYPEZDItemValueSetter.setValue(tMsg.custPoNum, (String) headerCreditCardPo.get("CUST_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.poDt, (String) headerCreditCardPo.get("PO_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyFlg, leaseCmpyFlg);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrMachLvlNum, "2");
            EZDTBLAccessor.insert(tMsg);
        } else {
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
            tMsg = (DS_CONTR_CR_CARD_POTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, (String) headerCreditCardPo.get("CR_CARD_CUST_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.crCardExprYrMth, (String) headerCreditCardPo.get("CR_CARD_EXPR_YR_MTH"));
                ZYPEZDItemValueSetter.setValue(tMsg.custPoNum, (String) headerCreditCardPo.get("CUST_PO_NUM"));
                ZYPEZDItemValueSetter.setValue(tMsg.poDt, (String) headerCreditCardPo.get("PO_DT"));
                ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyFlg, leaseCmpyFlg);
                EZDTBLAccessor.update(tMsg);
            }
        }
    }

    private Map<String, Object> getContrFleetAggregateLineForPoCard(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpCd_Fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpCd_Aggregate", DS_CONTR_DTL_TP.AGGREGATE);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getContrFleetAggregateLineForPoCard", param);
    }
    // END 2017/02/10 T.Kanasaka [QC#16650, ADD]

    private List<Map<String, Object>> getContrAdditionalCharge(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("contra", CHRG_LVL_TP.CONTRACT);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrAdditionalCharge", param);
    }

    private List<Map<String, Object>> getContrAdditionalChargeList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("contra", CHRG_LVL_TP.CONTRACT);
        param.put("contrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.BASE);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrAdditionalChargeList", param);
    }

    private void setContrAdditionalChargeList(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> contractAdditionalChargeTopList, List<Map<String, Object>> contrAdditionalChargeList) {

        if (nszc046001PMsg == null || tmsg == null || (contractAdditionalChargeTopList == null && contrAdditionalChargeList == null)) {
            return;
        }
        // Flag default set
        for (int i = 0; i < contractAdditionalChargeTopList.size(); i++) {
            Map<String, Object> target = contractAdditionalChargeTopList.get(i);
            String printDtlFlg = (String) target.get("PRINT_DTL_FLG");
            ((Map<String, Object>) contractAdditionalChargeTopList.get(i)).put("PRINT_DTL_FLG", getDefaultFlgY(printDtlFlg));
            String addlChrgInvdFlg = (String) target.get("ADDL_CHRG_INVD_FLG");
            ((Map<String, Object>) contractAdditionalChargeTopList.get(i)).put("ADDL_CHRG_INVD_FLG", getDefaultFlgN(addlChrgInvdFlg));
        }
        for (int i = 0; i < contrAdditionalChargeList.size(); i++) {
            Map<String, Object> target = contrAdditionalChargeList.get(i);
            String addlChrgInvdFlg = (String) target.get("ADDL_CHRG_INVD_FLG");
            ((Map<String, Object>) contrAdditionalChargeList.get(i)).put("ADDL_CHRG_INVD_FLG", getDefaultFlgN(addlChrgInvdFlg));
        }

        List<Map<String, Object>> parameterList = new ArrayList<Map<String, Object>>();
        if (contractAdditionalChargeTopList.size() > 0 && contrAdditionalChargeList.size() == 0) {
            int i = 0;
            parameterList = new ArrayList<Map<String, Object>>(contractAdditionalChargeTopList.size());
            for (; i < contractAdditionalChargeTopList.size(); i++) {
                Map<String, Object> contractExcessCopyTop = contractAdditionalChargeTopList.get(i);
                parameterList.add(contractExcessCopyTop);
            }
        } else if (contractAdditionalChargeTopList.size() > 0 && contrAdditionalChargeList.size() > 0) {
            parameterList = new ArrayList<Map<String, Object>>(contrAdditionalChargeList.size() + 1);
            Map<String, Object> contractExcessCopyTop = contractAdditionalChargeTopList.get(0);
            parameterList.add(contractExcessCopyTop);
            int i = 0;
            for (; i < contrAdditionalChargeList.size(); i++) {
                Map<String, Object> contractExcessCopy = contrAdditionalChargeList.get(i);
                parameterList.add(contractExcessCopy);
            }
        } else if (contractAdditionalChargeTopList.size() == 0 && contrAdditionalChargeList.size() > 0) {
            parameterList = new ArrayList<Map<String, Object>>(contrAdditionalChargeList.size());
            int i = 0;
            for (; i < contrAdditionalChargeList.size(); i++) {
                Map<String, Object> contractExcessCopy = contrAdditionalChargeList.get(i);
                parameterList.add(contractExcessCopy);
            }
        }

        int i = 0;
        for (; i < parameterList.size(); i++) {
            Map<String, Object> parameter = parameterList.get(i);
            setContrAdditionalChargeListParameter(nszc046001PMsg, tmsg, parameter, i);
        }
        nszc046001PMsg.xxDsContrAddlChrgList.setValidCount(i);
    }

    private void setContrAdditionalChargeListParameter(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> contrAdditionalCharge, int idx) {
        if (nszc046001PMsg != null && contrAdditionalCharge != null && tmsg != null) {
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).dsContrAddlChrgPk, (BigDecimal) contrAdditionalCharge.get("DS_CONTR_ADDL_CHRG_PK"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).dsContrPk, (BigDecimal) contrAdditionalCharge.get("DS_CONTR_PK"));
            if (CHRG_LVL_TP.CONTRACT.equals((String) contrAdditionalCharge.get("CHRG_LVL_TP_CD"))) {
                setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).dsContrDtlPk, (BigDecimal) contrAdditionalCharge.get("DS_CONTR_DTL_PK"));
            }
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).addlChrgTpCd, (String) contrAdditionalCharge.get("ADDL_CHRG_TP_CD"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).svcBillByTpCd, (String) contrAdditionalCharge.get("CHRG_LVL_TP_CD"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).addlChrgInvTpCd, (String) contrAdditionalCharge.get("ADDL_CHRG_INV_TP_CD"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).ccyCd, (String) contrAdditionalCharge.get("CCY_CD"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).addlChrgFlatDealPrcAmt, (BigDecimal) contrAdditionalCharge.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).addlChrgFlatFuncPrcAmt, (BigDecimal) contrAdditionalCharge.get("ADDL_CHRG_FLAT_FUNC_PRC_AMT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).addlChrgAplcPct, (BigDecimal) contrAdditionalCharge.get("ADDL_CHRG_APLC_PCT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).printDtlFlg, (String) contrAdditionalCharge.get("PRINT_DTL_FLG"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).bllgCycleCd, (String) contrAdditionalCharge.get("BLLG_CYCLE_CD"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).effFromDt, (String) contrAdditionalCharge.get("EFF_FROM_DT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).effThruDt, (String) contrAdditionalCharge.get("EFF_THRU_DT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).trmnDt, (String) contrAdditionalCharge.get("TRMN_DT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).addlChrgInvdFlg, (String) contrAdditionalCharge.get("ADDL_CHRG_INVD_FLG"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).invUpToDt, (String) contrAdditionalCharge.get("INV_UP_TO_DT"));
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(idx).svcMachMstrPk, (BigDecimal) contrAdditionalCharge.get("SVC_MACH_MSTR_PK"));
        }
    }

    private List<Map<String, Object>> getContrBillingMeter(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        // del start 2016/10/27 QC#14338
        // START 2016/04/08 [QC#6764, ADD]
        //param.put("spclFltMdseCd", ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd));
        // END   2016/04/08 [QC#6764, ADD]
        // del end 2016/10/27 QC#14338

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrBillingMeter", param);
    }

    private void setContrBillingMeterParameter(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> contrBillingMeterList) {
        if (nszc046001PMsg != null && contrBillingMeterList != null && tmsg != null) {
            int i = 0;
            for (; i < contrBillingMeterList.size(); i++) {
                Map<String, Object> contrBillingMeter = contrBillingMeterList.get(i);
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).dsContrBllgMtrPk, (BigDecimal) contrBillingMeter.get("DS_CONTR_BLLG_MTR_PK"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).dsContrDtlPk, (BigDecimal) contrBillingMeter.get("DS_CONTR_DTL_PK"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).dsContrBllgMtrId, (String) contrBillingMeter.get("DS_CONTR_BLLG_MTR_ID"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).mlyCopyInclPrcQty, (BigDecimal) contrBillingMeter.get("MLY_COPY_INCL_PRC_QTY"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).bllgMtrLbCd, (String) contrBillingMeter.get("BLLG_MTR_LB_CD"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).bllgMtrBillToCustCd, (String) contrBillingMeter.get("BILL_TO_CUST_CD"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).bllgMtrBllgCycleCd, (String) contrBillingMeter.get("BLLG_CYCLE_CD"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).dsContrBllgMtrStsCd, (String) contrBillingMeter.get("DS_CONTR_BLLG_MTR_STS_CD"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).xsChrgTpCd, (String) contrBillingMeter.get("XS_CHRG_TP_CD"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).bllgRollOverRatio, (BigDecimal) contrBillingMeter.get("BLLG_ROLL_OVER_RATIO"));
                BigDecimal ctacPsnPk = (BigDecimal) contrBillingMeter.get("CTAC_PSN_PK");
                if (ctacPsnPk != null) {
                    setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).ctacPsnPk, ctacPsnPk);
                } else {
                    setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).ctacPsnPk, getDefaultContactPersonPk((String) contrBillingMeter.get("BILL_TO_CUST_CD")));
                }
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).rollOverCnt, (BigDecimal) contrBillingMeter.get("ROLL_OVER_CNT"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).intgMdseCd, (String) contrBillingMeter.get("INTG_MDSE_CD"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).qltyAsrnHldFlg, (String) contrBillingMeter.get("QLTY_ASRN_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).mtrHldFlg, (String) contrBillingMeter.get("MTR_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).contrHldFlg, (String) contrBillingMeter.get("CONTR_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).bllgHldFlg, (String) contrBillingMeter.get("BLLG_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrBllgMtrList.no(i).svcMachMstrPk, (BigDecimal) contrBillingMeter.get("SVC_MACH_MSTR_PK"));
            }
            nszc046001PMsg.xxDsContrBllgMtrList.setValidCount(i);
        }
    }

    private void setContrPhysicalBillingMeterRelationParameter(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> contrPhysicalBillingMeterRelationList) {
        if (nszc046001PMsg != null && contrPhysicalBillingMeterRelationList != null && tmsg != null) {
            int i = 0;
            for (; i < contrPhysicalBillingMeterRelationList.size(); i++) {
                Map<String, Object> contrPhysicalBillingMeterRelation = contrPhysicalBillingMeterRelationList.get(i);
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).dsContrBllgMtrPk, (BigDecimal) contrPhysicalBillingMeterRelation.get("DS_CONTR_BLLG_MTR_PK"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).contrPhysBllgMtrRelnPk, (BigDecimal) contrPhysicalBillingMeterRelation.get("CONTR_PHYS_BLLG_MTR_RELN_PK"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).dsContrDtlPk, (BigDecimal) contrPhysicalBillingMeterRelation.get("DS_CONTR_DTL_PK"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).machMstrPk, (BigDecimal) contrPhysicalBillingMeterRelation.get("SVC_MACH_MSTR_PK"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).contrMtrMultRate, (BigDecimal) contrPhysicalBillingMeterRelation.get("CONTR_MTR_MULT_RATE"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).bllgMtrLbCd, (String) contrPhysicalBillingMeterRelation.get("BLLG_MTR_LB_CD"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).dsContrBllgMtrPk, (BigDecimal) contrPhysicalBillingMeterRelation.get("DS_CONTR_BLLG_MTR_PK"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).svcPhysMtrPk, (BigDecimal) contrPhysicalBillingMeterRelation.get("SVC_PHYS_MTR_PK"));
                setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).bllblFlg, (String) contrPhysicalBillingMeterRelation.get("BLLBL_FLG"));
                //setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).bllblFlg, "N");
                // del start 2016/08/25 CSA Defect#12136
                 setValue(nszc046001PMsg.xxContrPhysBllgMtrRelnList.no(i).bllgMtrLvlNum, (String) contrPhysicalBillingMeterRelation.get("BLLG_MTR_LVL_NUM"));
                // del end 2016/08/25 CSA Defect#12136
            }
            nszc046001PMsg.xxContrPhysBllgMtrRelnList.setValidCount(i);
        }
    }

    private List<Map<String, Object>> getContractExcessCopy(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        // del start 2016/10/27 QC#14338
        // START 2016/04/08 [QC#6764, ADD]
        //param.put("spclFltMdseCd", ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd));
        // END   2016/04/08 [QC#6764, ADD]
        // del end 2016/10/27 QC#14338

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractExcessCopy", param);
    }

    private List<Map<String, Object>> getContractExcessCopyList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        // del start 2016/10/27 QC#14338
        // START 2016/04/08 [QC#6764, ADD]
        //param.put("spclFltMdseCd", ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd));
        // END   2016/04/08 [QC#6764, ADD]
        // del end 2016/10/27 QC#14338

        // START 2016/04/04 [QC#6487, MOD]
        if (DS_CONTR_INTFC_ACT.UPDATE.equals(tmsg.dsContrIntfcActCd.getValue())) {
            return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractExcessCopyListForUpdate", param);
        } else if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
            return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractExcessCopyListForAddToContract", param);
        } else {
            return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractExcessCopyList", param);
        }
        // END   2016/04/04 [QC#6487, MOD]
    }

    private void setContractExcessCopyParameter(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> contractExcessCopyTopList, List<Map<String, Object>> contractExcessCopyList) {
        if (nszc046001PMsg != null && contractExcessCopyTopList != null && contractExcessCopyList != null && tmsg != null) {
            List<Map<String, Object>> parameterList = new ArrayList<Map<String, Object>>();

            if (contractExcessCopyTopList.size() > 0 && contractExcessCopyList.size() == 0) {
                int i = 0;
                parameterList = new ArrayList<Map<String, Object>>(contractExcessCopyTopList.size());
                for (; i < contractExcessCopyTopList.size(); i++) {
                    Map<String, Object> contractExcessCopyTop = contractExcessCopyTopList.get(i);
                    parameterList.add(contractExcessCopyTop);
                }
            } else if (contractExcessCopyTopList.size() > 0 && contractExcessCopyList.size() > 0) {
                // START 2017/03/22 [QC#18086, MOD]
//                parameterList = new ArrayList<Map<String, Object>>(contractExcessCopyList.size() + 1);
//                Map<String, Object> contractExcessCopyTop = contractExcessCopyTopList.get(0);
//                topSvcMachMstrPk = (BigDecimal) contractExcessCopyTop.get("SVC_MACH_MSTR_PK");
//                topBllgMtrLbCd = (String) contractExcessCopyTop.get("BLLG_MTR_LB_CD");
//                parameterList.add(contractExcessCopyTop);
//                cnt = cnt + 1;
                parameterList = new ArrayList<Map<String, Object>>(contractExcessCopyTopList.size() + contractExcessCopyList.size());
                for (int i = 0; i < contractExcessCopyTopList.size(); i++) {
                    parameterList.add(contractExcessCopyTopList.get(i));
                }
                // END 2017/03/22 [QC#18086, MOD]
                int i = 0;
                for (; i < contractExcessCopyList.size(); i++) {
                    Map<String, Object> contractExcessCopy = contractExcessCopyList.get(i);
                    parameterList.add(contractExcessCopy);
                }
            } else if (contractExcessCopyTopList.size() == 0 && contractExcessCopyList.size() > 0) {
                parameterList = new ArrayList<Map<String, Object>>(contractExcessCopyList.size());
                int i = 0;
                for (; i < contractExcessCopyList.size(); i++) {
                    Map<String, Object> contractExcessCopy = contractExcessCopyList.get(i);
                    parameterList.add(contractExcessCopy);
                }
            }
            int i = 0;
            Map<String, BigDecimal> minQty = new HashMap<String, BigDecimal>();
            Map<String, BigDecimal> minQtyArg = new HashMap<String, BigDecimal>();
            BigDecimal nowMinQty = null;
            for (; i < parameterList.size(); i++) {
                Map<String, Object> parameter = parameterList.get(i);
                // START 2016/04/04 [QC#6487, MOD]
                //setValue(nszc046001PMsg.xxContrXsCopyList.no(i).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
                if (DS_CONTR_INTFC_ACT.UPDATE.equals(tmsg.dsContrIntfcActCd.getValue())) {
                    if (hasValue((BigDecimal) parameter.get("CONTR_XS_COPY_PK"))) {
                        setValue(nszc046001PMsg.xxContrXsCopyList.no(i).xxModeCd, CRUD_MODE_DELETE);
                    } else {
                        setValue(nszc046001PMsg.xxContrXsCopyList.no(i).xxModeCd, CRUD_MODE_CREATE);
                    }
                } else {
                    setValue(nszc046001PMsg.xxContrXsCopyList.no(i).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
                }
                // END   2016/04/04 [QC#6487, MOD]
                setValue(nszc046001PMsg.xxContrXsCopyList.no(i).contrXsCopyPk, (BigDecimal) parameter.get("CONTR_XS_COPY_PK"));
                setValue(nszc046001PMsg.xxContrXsCopyList.no(i).dsContrBllgMtrPk, (BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"));
                setValue(nszc046001PMsg.xxContrXsCopyList.no(i).xsMtrCopyQty, (BigDecimal) parameter.get("XS_MTR_COPY_QTY"));
                setValue(nszc046001PMsg.xxContrXsCopyList.no(i).xsMtrAmtRate, (BigDecimal) parameter.get("XS_MTR_AMT_RATE"));
                setValue(nszc046001PMsg.xxContrXsCopyList.no(i).svcMachMstrPk, (BigDecimal) parameter.get("SVC_MACH_MSTR_PK"));
                setValue(nszc046001PMsg.xxContrXsCopyList.no(i).bllgMtrLbCd, (String) parameter.get("BLLG_MTR_LB_CD"));
                if (!hasValue((BigDecimal) parameter.get("CONTR_XS_COPY_PK"))) {
                    // START 2017/03/22 [QC#18086, MOD]
//                    if (!minQty.containsKey((BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"))) {
//                        minQty.put((BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"), (BigDecimal) parameter.get("XS_MTR_COPY_QTY"));
//                        minQtyArg.put((BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"), new BigDecimal(i));
//                    }
//                    nowMinQty = minQty.get((BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"));
//                    if (nowMinQty.compareTo((BigDecimal) parameter.get("XS_MTR_COPY_QTY")) > 0) {
//                        minQty.put((BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"), (BigDecimal) parameter.get("XS_MTR_COPY_QTY"));
//                        minQtyArg.put((BigDecimal) parameter.get("DS_CONTR_BLLG_MTR_PK"), new BigDecimal(i));
//                    }
                    String mapKey = getMapKey(nszc046001PMsg.xxContrXsCopyList.no(i));
                    if (!minQty.containsKey(mapKey)) {
                        minQty.put(mapKey, (BigDecimal) parameter.get("XS_MTR_COPY_QTY"));
                        minQtyArg.put(mapKey, new BigDecimal(i));
                    }
                    nowMinQty = minQty.get(mapKey);
                    if (nowMinQty.compareTo((BigDecimal) parameter.get("XS_MTR_COPY_QTY")) > 0) {
                        minQty.put(mapKey, (BigDecimal) parameter.get("XS_MTR_COPY_QTY"));
                        minQtyArg.put(mapKey, new BigDecimal(i));
                    }
                    // END 2017/03/22 [QC#18086, MOD]
                }
            }
            nszc046001PMsg.xxContrXsCopyList.setValidCount(i);
            BigDecimal xsMtrFirstFlgArg = null;
            for (int j = 0; j < nszc046001PMsg.xxContrXsCopyList.getValidCount(); j++) {
                if (!hasValue(nszc046001PMsg.xxContrXsCopyList.no(j).contrXsCopyPk)) {
                    // START 2017/03/22 [QC#18086, MOD]
//                    xsMtrFirstFlgArg = minQtyArg.get(nszc046001PMsg.xxContrXsCopyList.no(j).dsContrBllgMtrPk.getValue());
                    String mapKey = getMapKey(nszc046001PMsg.xxContrXsCopyList.no(j));
                    xsMtrFirstFlgArg = minQtyArg.get(mapKey);
                    // END 2017/03/22 [QC#18086, MOD]
                    if (xsMtrFirstFlgArg.compareTo(new BigDecimal(j)) == 0) {
                        setValue(nszc046001PMsg.xxContrXsCopyList.no(j).xsMtrFirstFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        setValue(nszc046001PMsg.xxContrXsCopyList.no(j).xsMtrFirstFlg, ZYPConstant.FLG_OFF_N);
                    }
                }
            }
        }
    }

    // START 2017/03/22 [QC#18086, ADD]
    private String getMapKey(NSZC046001_xxContrXsCopyListPMsg xsCopyPMsg) {
        String strMachPk = "";
        String strMtrLbCd = "";
        BigDecimal svcMachMstrPk = xsCopyPMsg.svcMachMstrPk.getValue();
        String bllgMtrLbCd = xsCopyPMsg.bllgMtrLbCd.getValue();

        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            strMachPk = svcMachMstrPk.toString();
        }
        if (ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            strMtrLbCd = bllgMtrLbCd;
        }
        return strMachPk + "&" + strMtrLbCd;
    }
    // END 2017/03/22 [QC#18086, ADD]

    private String getXxModeCdForLevel1(String dsContrIntfcActCd) {
        if (DS_CONTR_INTFC_ACT.CREATE.equals(dsContrIntfcActCd)) {
            return DS_CONTR_INTFC_ACT_CD_CREATE;
        } else if (DS_CONTR_INTFC_ACT.UPDATE.equals(dsContrIntfcActCd)) {
            return DS_CONTR_INTFC_ACT_CD_UPDATE;
        } else if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(dsContrIntfcActCd)) {
            return DS_CONTR_INTFC_ACT_CD_ADD_TO_CONTR;
        } else if (DS_CONTR_INTFC_ACT.DELETE.equals(dsContrIntfcActCd)) {
            return DS_CONTR_INTFC_ACT_CD_DELETE;
        } else if (DS_CONTR_INTFC_ACT.TERMINATE.equals(dsContrIntfcActCd)) {
            return DS_CONTR_INTFC_ACT_CD_TERMINATE;
        }
        return null;
    }

    private String getXxModeCdForLevel2(String dsContrIntfcActCd) {
        if (DS_CONTR_INTFC_ACT.CREATE.equals(dsContrIntfcActCd)) {
            return CRUD_MODE_CREATE;
        } else if (DS_CONTR_INTFC_ACT.UPDATE.equals(dsContrIntfcActCd)) {
            return CRUD_MODE_UPDATE;
        } else if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(dsContrIntfcActCd)) {
            return CRUD_MODE_UPDATE;
        } else if (DS_CONTR_INTFC_ACT.DELETE.equals(dsContrIntfcActCd)) {
            return CRUD_MODE_DELETE;
        } else if (DS_CONTR_INTFC_ACT.TERMINATE.equals(dsContrIntfcActCd)) {
            return CRUD_MODE_DELETE;
        }
        return null;
    }

    private List<Map<String, Object>> getContrPhysicalBillingMeterRelation(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        // del start 2016/10/27 QC#14338
        // START 2016/04/08 [QC#6764, ADD]
        //param.put("spclFltMdseCd", ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, this.glblCmpyCd));
        // END   2016/04/08 [QC#6764, ADD]
        // del end 2016/10/27 QC#14338

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrPhysicalBillingMeterRelation", param);
    }

    private boolean isErrorNSZC046001API(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg) {
        // 2.14.Update Contract
        List<Map<String, Object>> dsContrIntfcListDsContrNum = getDsContrIntfcStatusClearList(tmsg);
        for (int i = 0; i < dsContrIntfcListDsContrNum.size(); i++) {
            Map<String, Object> dsContrIntfc = dsContrIntfcListDsContrNum.get(i);
            DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
            setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
            setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfc.get("DS_CONTR_INTFC_PK"));
            DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
            if (target != null) {
                // Update
                setValue(target.dsContrNum, nszc046001PMsg.dsContrNum.getValue());
                setValue(target.dsContrIntfcDt, this.salesDate);
                // START 2016/03/30 [QC#6284, ADD]
                setValue(tmsg.dsContrNum, nszc046001PMsg.dsContrNum.getValue());
                setValue(tmsg.dsContrIntfcDt, this.salesDate);
                // END   2016/03/30 [QC#6284, ADD]
                EZDTBLAccessor.update(target);
            }
        }

        // Status Clear
        // 3.1.DS_CONTR_INTFC Status Clear
        List<Map<String, Object>> dsContrIntfcList = getDsContrIntfcStatusClearList(tmsg);
        for (int i = 0; i < dsContrIntfcList.size(); i++) {
            Map<String, Object> dsContrIntfc = dsContrIntfcList.get(i);
            DS_CONTR_INTFCTMsg dsContrIntfcTmsg = new DS_CONTR_INTFCTMsg();
            setValue(dsContrIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(dsContrIntfcTmsg.dsContrIntfcPk, (BigDecimal) dsContrIntfc.get("DS_CONTR_INTFC_PK"));
            DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrIntfcTmsg);
            if (target != null) {
                // Update
                setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
                setValue(target.intfcErrMsgTxt, (String) null);
                EZDTBLAccessor.update(target);
            }
        }

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.2.DS_ACTL_CNT_INTFC Status Clear
            List<Map<String, Object>> dsActlCntIntfcList = getDsActlCntIntfcStatusClearList(tmsg);
            for (int i = 0; i < dsActlCntIntfcList.size(); i++) {
                Map<String, Object> dsActlCntIntfc = dsActlCntIntfcList.get(i);
                DS_ACTL_CNT_INTFCTMsg dsActlCntIntfcTmsg = new DS_ACTL_CNT_INTFCTMsg();
                setValue(dsActlCntIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(dsActlCntIntfcTmsg.dsActlCntIntfcPk, (BigDecimal) dsActlCntIntfc.get("DS_ACTL_CNT_INTFC_PK"));
                DS_ACTL_CNT_INTFCTMsg target = (DS_ACTL_CNT_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsActlCntIntfcTmsg);
                if (target != null) {
                    // Update
                    setValue(target.actlCntIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
                    setValue(target.intfcErrMsgTxt, (String) null);
                    EZDTBLAccessor.update(target);
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.3.DS_XS_COPY_INTFC Status Clear
            List<Map<String, Object>> contrXsCopyIntfcList = getContrXsCopyIntfcStatusClearList(tmsg);
            for (int i = 0; i < contrXsCopyIntfcList.size(); i++) {
                Map<String, Object> contrXsCopyIntfc = contrXsCopyIntfcList.get(i);
                DS_XS_COPY_INTFCTMsg dsXsCopyIntfcTmsg = new DS_XS_COPY_INTFCTMsg();
                setValue(dsXsCopyIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(dsXsCopyIntfcTmsg.dsXsCopyIntfcPk, (BigDecimal) contrXsCopyIntfc.get("DS_XS_COPY_INTFC_PK"));
                DS_XS_COPY_INTFCTMsg target = (DS_XS_COPY_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsXsCopyIntfcTmsg);
                if (target != null) {
                    // Update
                    setValue(target.xsCopyIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
                    setValue(target.intfcErrMsgTxt, (String) null);
                    EZDTBLAccessor.update(target);
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.4.DS_ADDL_CHRG_INTFC Status Clear
            List<Map<String, Object>> dsContrAddlChrgIntfcList = getDsContrAddlChrgIntfcStatusClearList(tmsg);
            for (int i = 0; i < dsContrAddlChrgIntfcList.size(); i++) {
                Map<String, Object> dsContrAddlChrgIntfc = dsContrAddlChrgIntfcList.get(i);
                DS_ADDL_CHRG_INTFCTMsg dsContrAddlChrgIntfcTmsg = new DS_ADDL_CHRG_INTFCTMsg();
                setValue(dsContrAddlChrgIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(dsContrAddlChrgIntfcTmsg.dsAddlChrgIntfcPk, (BigDecimal) dsContrAddlChrgIntfc.get("DS_ADDL_CHRG_INTFC_PK"));
                DS_ADDL_CHRG_INTFCTMsg target = (DS_ADDL_CHRG_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrAddlChrgIntfcTmsg);
                if (target != null) {
                    // Update
                    setValue(target.addChrgIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
                    setValue(target.intfcErrMsgTxt, (String) null);
                    EZDTBLAccessor.update(target);
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // START 2016/05/24 [QC#4061, MOD]
        //if (!isManContrOvrd(tmsg)) {
        //    // 3.5.PRC_ALLOC_INTFC Status Clear
        //    List<Map<String, Object>> prcAllocIntfcList = getPrcAllocIntfcStatusClearList(tmsg);
        //    for (int i = 0; i < prcAllocIntfcList.size(); i++) {
        //        Map<String, Object> prcAllocIntfc = prcAllocIntfcList.get(i);
        //        PRC_ALLOC_INTFCTMsg prcAllocIntfcTmsg = new PRC_ALLOC_INTFCTMsg();
        //        setValue(prcAllocIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
        //        setValue(prcAllocIntfcTmsg.prcAllocIntfcPk, (BigDecimal) prcAllocIntfc.get("PRC_ALLOC_INTFC_PK"));
        //        PRC_ALLOC_INTFCTMsg target = (PRC_ALLOC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcAllocIntfcTmsg);
        //        if (target != null) {
        //            // Update
        //            setValue(target.allocIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
        //            setValue(target.intfcErrMsgTxt, (String) null);
        //            EZDTBLAccessor.update(target);
        //        }
        //    }
        //}
        // END   2016/05/24 [QC#4061, MOD]

        boolean errorFlg = false;

//        // 3.6.PRC_ALLOC_INTFC set Error
//        NSZC046001_xxDsContrBrAllocListPMsg xxDsContrBrAllocList = (NSZC046001_xxDsContrBrAllocListPMsg) nszc046001PMsg.xx.get(0);
//        if (xxDsContrBrAllocList != null && ZYPCommonFunc.hasValue(xxDsContrBrAllocList.xxMsgId)) {
//            List<Map<String, Object>> prcAllocIntfcErrorList = getPrcAllocIntfcStatusErrorList(nszc046001PMsg, tmsg);
//            for (int i = 0; i < prcAllocIntfcErrorList.size(); i++) {
//                Map<String, Object> dsContrIntfc = prcAllocIntfcErrorList.get(i);
//                PRC_ALLOC_INTFCTMsg dsContrInfc = new PRC_ALLOC_INTFCTMsg();
//                setValue(dsContrInfc.prcAllocIntfcPk, (BigDecimal) dsContrIntfc.get("PRC_ALLOC_INTFC_PK"));
//                PRC_ALLOC_INTFCTMsgArray targetList = (PRC_ALLOC_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdateNoWait(dsContrInfc);
//                if (targetList.getValidCount() > 0) {
//                    PRC_ALLOC_INTFCTMsg target = (PRC_ALLOC_INTFCTMsg) targetList.get(0);
//                    // Update
//                    setValue(target.allocIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
//                    setValue(target.intfcErrMsgTxt, xxDsContrBrAllocList.xxDsMultMsgDplyTxt.getValue());
//                    EZDTBLAccessor.update(target);
//                }
//            }
//            errorFlg = true;
//        }

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.7.DS_CONTR_ADDL_CHRG set Error
            NSZC046001_xxDsContrAddlChrgListPMsgArray xxDsContrAddlChrgList = (NSZC046001_xxDsContrAddlChrgListPMsgArray) nszc046001PMsg.xxDsContrAddlChrgList;
            for (int i = 0; i < xxDsContrAddlChrgList.getValidCount(); i++) {
                NSZC046001_xxDsContrAddlChrgListPMsg xxDsContrAddlChrg = (NSZC046001_xxDsContrAddlChrgListPMsg) xxDsContrAddlChrgList.get(i);
                if (hasValue(xxDsContrAddlChrg.xxMsgId)) {
                    List<Map<String, Object>> dsContrIntfcErrorList = getDsContrIntfcForDsContrAddlChrgStatusErrorList(tmsg, xxDsContrAddlChrg);
                    // START 2016/06/24 [QC#10691, ADD]
                    if (dsContrIntfcErrorList.size() == 0) {
                        dsContrIntfcErrorList = getDsContrIntfcForDsContrStatusErrorList(nszc046001PMsg, tmsg);
                    }
                    // END   2016/06/24 [QC#10691, ADD]
                    for (int j = 0; j < dsContrIntfcErrorList.size(); j++) {
                        Map<String, Object> dsContrIntfcError = dsContrIntfcErrorList.get(j);
                        DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                        setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                        setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfcError.get("DS_CONTR_INTFC_PK"));
                        DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                        if (target != null) {
                            // Update
                            setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                            setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxDsContrAddlChrg.xxMsgId.getValue(), xxDsContrAddlChrg.xxDsMultMsgDplyTxt.getValue()));
                            EZDTBLAccessor.update(target);
                        }
                    }
                    // START 2016/03/22 [QC#5543,QC# ADD]
                    List<Map<String, Object>> dsAddlChrgIntfcErrorList = getDsAddlChrgStatusErrorList(tmsg, xxDsContrAddlChrg);
                    // START 2016/06/24 [QC#10691, ADD]
                    if (dsAddlChrgIntfcErrorList.size() == 0) {
                        dsAddlChrgIntfcErrorList = getDsAddlChrgStatusErrorListForHeader(tmsg, xxDsContrAddlChrg);
                    }
                    // END   2016/06/24 [QC#10691, ADD]
                    for (int j = 0; j < dsAddlChrgIntfcErrorList.size(); j++) {
                        Map<String, Object> dsAddlChrgIntfcError = dsAddlChrgIntfcErrorList.get(j);
                        DS_ADDL_CHRG_INTFCTMsg dsAddlChrg = new DS_ADDL_CHRG_INTFCTMsg();
                        setValue(dsAddlChrg.glblCmpyCd, this.glblCmpyCd);
                        setValue(dsAddlChrg.dsAddlChrgIntfcPk, (BigDecimal) dsAddlChrgIntfcError.get("DS_ADDL_CHRG_INTFC_PK"));
                        DS_ADDL_CHRG_INTFCTMsg target = (DS_ADDL_CHRG_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsAddlChrg);
                        if (target != null) {
                            // Update
                            setValue(target.addChrgIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                            setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxDsContrAddlChrg.xxMsgId.getValue(), xxDsContrAddlChrg.xxDsMultMsgDplyTxt.getValue()));
                            EZDTBLAccessor.update(target);
                        }
                    }
                    // END 2016/03/22 [QC#5543,QC# ADD]
                    errorFlg = true;
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // 3.8.DS_CONTR_CR_CARD_PO set Error
        NSZC046001_xxDsContrCrCardPoListPMsgArray xxDsContrCrCardPoList = (NSZC046001_xxDsContrCrCardPoListPMsgArray) nszc046001PMsg.xxDsContrCrCardPoList;
        for (int i = 0; i < xxDsContrCrCardPoList.getValidCount(); i++) {
            NSZC046001_xxDsContrCrCardPoListPMsg xxDsContrCrCardPo = (NSZC046001_xxDsContrCrCardPoListPMsg) xxDsContrCrCardPoList.get(i);
            if (hasValue(xxDsContrCrCardPo.xxMsgId)) {
                List<Map<String, Object>> dsContrCrCardPoErrorList = getDsContrIntfcForDsContrCrCardPoStatusErrorList(tmsg, xxDsContrCrCardPo);
                // START 2016/06/24 [QC#10691, ADD]
                if (dsContrCrCardPoErrorList.size() == 0) {
                    dsContrCrCardPoErrorList = getDsContrIntfcForDsContrStatusErrorList(nszc046001PMsg, tmsg);
                }
                // END   2016/06/24 [QC#10691, ADD]
                for (int j = 0; j < dsContrCrCardPoErrorList.size(); j++) {
                    Map<String, Object> dsContrCrCardPoError = dsContrCrCardPoErrorList.get(j);
                    DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                    setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                    setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrCrCardPoError.get("DS_CONTR_INTFC_PK"));
                    DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                    if (target != null) {
                        // Update
                        setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                        setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxDsContrCrCardPo.xxMsgId.getValue(), xxDsContrCrCardPo.xxDsMultMsgDplyTxt.getValue()));
                        EZDTBLAccessor.update(target);
                    }
                }
                errorFlg = true;
            }
        }

        // 3.9.DS_CONTR_RNW_ESCL set Error
        NSZC046001_xxDsContrRnwEsclListPMsgArray xxDsContrRnwEsclList = (NSZC046001_xxDsContrRnwEsclListPMsgArray) nszc046001PMsg.xxDsContrRnwEsclList;
        for (int i = 0; i < xxDsContrRnwEsclList.getValidCount(); i++) {
            NSZC046001_xxDsContrRnwEsclListPMsg xxDsContrRnwEscl = (NSZC046001_xxDsContrRnwEsclListPMsg) xxDsContrRnwEsclList.get(i);
            if (hasValue(xxDsContrRnwEscl.xxMsgId)) {
                List<Map<String, Object>> dsContrRnwEsclStatusErrorList = getDsContrIntfcForDsContrRnwEsclStatusErrorList(tmsg, xxDsContrRnwEscl);
                // START 2016/06/24 [QC#10691, ADD]
                if (dsContrRnwEsclStatusErrorList.size() == 0) {
                    dsContrRnwEsclStatusErrorList = getDsContrIntfcForDsContrStatusErrorList(nszc046001PMsg, tmsg);
                }
                // END   2016/06/24 [QC#10691, ADD]
                for (int j = 0; j < dsContrRnwEsclStatusErrorList.size(); j++) {
                    Map<String, Object> dsContrRnwEsclStatusError = dsContrRnwEsclStatusErrorList.get(j);
                    DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                    setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                    setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrRnwEsclStatusError.get("DS_CONTR_INTFC_PK"));
                    DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                    if (target != null) {
                        // Update
                        setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                        setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxDsContrRnwEscl.xxMsgId.getValue(), xxDsContrRnwEscl.xxDsMultMsgDplyTxt.getValue()));
                        EZDTBLAccessor.update(target);
                    }
                }
                errorFlg = true;
            }
        }

        // 3.10.SVC_TERM_COND set Error
        NSZC046001_xxSvcTermCondListPMsgArray xxSvcTermCondList = (NSZC046001_xxSvcTermCondListPMsgArray) nszc046001PMsg.xxSvcTermCondList;
        for (int i = 0; i < xxSvcTermCondList.getValidCount(); i++) {
            NSZC046001_xxSvcTermCondListPMsg xxSvcTermCond = (NSZC046001_xxSvcTermCondListPMsg) xxSvcTermCondList.get(i);
            if (hasValue(xxSvcTermCond.xxMsgId)) {
                List<Map<String, Object>> dsContrIntfcForSvcTermCondStatusErrorList = getDsContrIntfcForSvcTermCondStatusErrorList(tmsg, xxSvcTermCond);
                for (int j = 0; j < dsContrIntfcForSvcTermCondStatusErrorList.size(); j++) {
                    Map<String, Object> dsContrIntfcForSvcTermCondStatusError = dsContrIntfcForSvcTermCondStatusErrorList.get(j);
                    DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                    setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                    setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfcForSvcTermCondStatusError.get("DS_CONTR_INTFC_PK"));
                    DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                    if (target != null) {
                        // Update
                        setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                        setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxSvcTermCond.xxMsgId.getValue(), xxSvcTermCond.xxDsMultMsgDplyTxt.getValue()));
                        EZDTBLAccessor.update(target);
                    }
                }
                errorFlg = true;
            }
        }

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.11.CONTR_XS_COPY set Error
            NSZC046001_xxContrXsCopyListPMsgArray xxContrXsCopyList = (NSZC046001_xxContrXsCopyListPMsgArray) nszc046001PMsg.xxContrXsCopyList;
            for (int i = 0; i < xxContrXsCopyList.getValidCount(); i++) {
                NSZC046001_xxContrXsCopyListPMsg xxContrXsCopy = (NSZC046001_xxContrXsCopyListPMsg) xxContrXsCopyList.get(i);
                if (hasValue(xxContrXsCopy.xxMsgId)) {
                    List<Map<String, Object>> dsContrIntfcForContrXsCopyErrorList = getDsContrIntfcForContrXsCopyStatusErrorList(tmsg, xxContrXsCopy);
                    for (int j = 0; j < dsContrIntfcForContrXsCopyErrorList.size(); j++) {
                        Map<String, Object> dsContrIntfcForContrXsCopyError = dsContrIntfcForContrXsCopyErrorList.get(j);
                        DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                        setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                        setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfcForContrXsCopyError.get("DS_CONTR_INTFC_PK"));
                        DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                        if (target != null) {
                            // Update
                            setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                            setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxContrXsCopy.xxMsgId.getValue(), xxContrXsCopy.xxDsMultMsgDplyTxt.getValue()));
                            EZDTBLAccessor.update(target);
                        }
                    }

                    List<Map<String, Object>> contrXsCopyIntfcErrorList = getContrXsCopyIntfcStatusErrorList(tmsg, xxContrXsCopy);
                    for (int j = 0; j < contrXsCopyIntfcErrorList.size(); j++) {
                        Map<String, Object> contrXsCopyIntfcError = contrXsCopyIntfcErrorList.get(j);
                        DS_XS_COPY_INTFCTMsg dsXsCopyInfc = new DS_XS_COPY_INTFCTMsg();
                        setValue(dsXsCopyInfc.glblCmpyCd, this.glblCmpyCd);
                        setValue(dsXsCopyInfc.dsXsCopyIntfcPk, (BigDecimal) contrXsCopyIntfcError.get("DS_XS_COPY_INTFC_PK"));
                        DS_XS_COPY_INTFCTMsg target = (DS_XS_COPY_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsXsCopyInfc);
                        if (target != null) {
                            // Update
                            setValue(target.xsCopyIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                            setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxContrXsCopy.xxMsgId.getValue(), xxContrXsCopy.xxDsMultMsgDplyTxt.getValue()));
                            EZDTBLAccessor.update(target);
                        }
                    }
                    errorFlg = true;
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.12.CONTR_PHYS_BLLG_MTR_RELN set Error
            NSZC046001_xxContrPhysBllgMtrRelnListPMsgArray xxContrPhysBllgMtrRelnList = (NSZC046001_xxContrPhysBllgMtrRelnListPMsgArray) nszc046001PMsg.xxContrPhysBllgMtrRelnList;
            for (int i = 0; i < xxContrPhysBllgMtrRelnList.getValidCount(); i++) {
                NSZC046001_xxContrPhysBllgMtrRelnListPMsg xxContrPhysBllgMtrReln = (NSZC046001_xxContrPhysBllgMtrRelnListPMsg) xxContrPhysBllgMtrRelnList.get(i);
                if (hasValue(xxContrPhysBllgMtrReln.xxMsgId)) {
                    List<Map<String, Object>> dsActlCntIntfcErrorList = getDsActlCntIntfcStatusErrorList(tmsg, xxContrPhysBllgMtrReln);
                    for (int j = 0; j < dsActlCntIntfcErrorList.size(); j++) {
                        Map<String, Object> dsActlCntIntfcError = dsActlCntIntfcErrorList.get(j);
                        DS_ACTL_CNT_INTFCTMsg dsActlCntIntfcTmsg = new DS_ACTL_CNT_INTFCTMsg();
                        setValue(dsActlCntIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
                        setValue(dsActlCntIntfcTmsg.dsActlCntIntfcPk, (BigDecimal) dsActlCntIntfcError.get("DS_ACTL_CNT_INTFC_PK"));
                        DS_ACTL_CNT_INTFCTMsg target = (DS_ACTL_CNT_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsActlCntIntfcTmsg);
                        if (target != null) {
                            // Update
                            setValue(target.actlCntIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                            setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxContrPhysBllgMtrReln.xxMsgId.getValue(), xxContrPhysBllgMtrReln.xxDsMultMsgDplyTxt.getValue()));
                            EZDTBLAccessor.update(target);
                        }
                    }
                    errorFlg = true;
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // START 2016/05/24 [QC#4061, MOD]
        if (!isManContrOvrd(tmsg)) {
            // 3.13.DS_CONTR_BLLG_MTR set Error
            NSZC046001_xxDsContrBllgMtrListPMsgArray xxDsContrBllgMtrList = (NSZC046001_xxDsContrBllgMtrListPMsgArray) nszc046001PMsg.xxDsContrBllgMtrList;
            for (int i = 0; i < xxDsContrBllgMtrList.getValidCount(); i++) {
                NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr = (NSZC046001_xxDsContrBllgMtrListPMsg) xxDsContrBllgMtrList.get(i);
                if (hasValue(xxDsContrBllgMtr.xxMsgId)) {
                    List<Map<String, Object>> dsContrIntfcErrorList = getDsContrIntfcForDsContrBllgMtrStatusErrorList(tmsg, xxDsContrBllgMtr);
                    for (int j = 0; j < dsContrIntfcErrorList.size(); j++) {
                        Map<String, Object> dsContrIntfcError = dsContrIntfcErrorList.get(j);
                        DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                        setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                        setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfcError.get("DS_CONTR_INTFC_PK"));
                        DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                        if (target != null) {
                            // Update
                            setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                            setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxDsContrBllgMtr.xxMsgId.getValue(), xxDsContrBllgMtr.xxDsMultMsgDplyTxt.getValue()));
                            EZDTBLAccessor.update(target);
                        }
                    }
                    errorFlg = true;
                }
            }
        }
        // END   2016/05/24 [QC#4061, MOD]

        // 3.14.Contract Detail set Error
        NSZC046001_xxContrDtlListPMsgArray xxContrDtlList = (NSZC046001_xxContrDtlListPMsgArray) nszc046001PMsg.xxContrDtlList;
        for (int i = 0; i < xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg xxContrDtl = (NSZC046001_xxContrDtlListPMsg) xxContrDtlList.get(i);
            if (hasValue(xxContrDtl.xxMsgId_DT)) {
                List<Map<String, Object>> dsContrIntfcForDsContrDtlErrorList = getDsContrIntfcForDsContrDtlStatusErrorList(tmsg, xxContrDtl);
                for (int j = 0; j < dsContrIntfcForDsContrDtlErrorList.size(); j++) {
                    Map<String, Object> dsContrIntfcForDsContrDtlError = dsContrIntfcForDsContrDtlErrorList.get(j);
                    DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                    setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                    setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfcForDsContrDtlError.get("DS_CONTR_INTFC_PK"));
                    DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                    if (target != null) {
                        // Update
                        setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                        setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(xxContrDtl.xxMsgId_DT.getValue(), xxContrDtl.xxDsMultMsgDplyTxt_DT.getValue()));
                        EZDTBLAccessor.update(target);
                    }
                }
                errorFlg = true;
            }
        }

        // 3.15.Contract Header set Error(xxMsgIdList)
        if (S21ApiUtil.isXxMsgId(nszc046001PMsg)) {
            NSZC046001_xxMsgIdListPMsg xxMsgIdList = (NSZC046001_xxMsgIdListPMsg) nszc046001PMsg.xxMsgIdList.get(0);
            List<Map<String, Object>> dsContrIntfcForDsContrList = getDsContrIntfcForDsContrStatusErrorList(nszc046001PMsg, tmsg);
            for (int i = 0; i < dsContrIntfcForDsContrList.size(); i++) {
                Map<String, Object> dsContrIntfc = dsContrIntfcForDsContrList.get(i);
                DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfc.get("DS_CONTR_INTFC_PK"));
                DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                if (target != null) {
                    // Update
                    setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                    setValue(target.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(xxMsgIdList.xxMsgId.getValue(), new String[] {xxMsgIdList.xxMsgPrmTxt_0.getValue(), xxMsgIdList.xxMsgPrmTxt_1.getValue() }));
                    EZDTBLAccessor.update(target);
                }
            }
            errorFlg = true;
        }

        // 3.15.Contract Header set Error(xxMsgId_HD)
        if (hasValue(nszc046001PMsg.xxMsgId_HD)) {
            List<Map<String, Object>> dsContrIntfcForDsContrList = getDsContrIntfcForDsContrStatusErrorList(nszc046001PMsg, tmsg);
            for (int i = 0; i < dsContrIntfcForDsContrList.size(); i++) {
                Map<String, Object> dsContrIntfc = dsContrIntfcForDsContrList.get(i);
                DS_CONTR_INTFCTMsg dsContrInfc = new DS_CONTR_INTFCTMsg();
                setValue(dsContrInfc.glblCmpyCd, this.glblCmpyCd);
                setValue(dsContrInfc.dsContrIntfcPk, (BigDecimal) dsContrIntfc.get("DS_CONTR_INTFC_PK"));
                DS_CONTR_INTFCTMsg target = (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsContrInfc);
                if (target != null) {
                    // Update
                    setValue(target.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                    setValue(target.intfcErrMsgTxt, getIntfcErrMsgTxt(nszc046001PMsg.xxMsgId_HD.getValue(), nszc046001PMsg.xxDsMultMsgDplyTxt_HD.getValue()));
                    EZDTBLAccessor.update(target);
                }
            }
            errorFlg = true;
        }

        return errorFlg;
    }

    private List<Map<String, Object>> getDsContrIntfcStatusClearList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcStatusClearList", param);
    }

    private List<Map<String, Object>> getDsActlCntIntfcStatusClearList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsActlCntIntfcStatusClearList", param);
    }

    private List<Map<String, Object>> getContrXsCopyIntfcStatusClearList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrXsCopyIntfcStatusClearList", param);
    }

    private List<Map<String, Object>> getDsContrAddlChrgIntfcStatusClearList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrAddlChrgIntfcStatusClearList", param);
    }

    private List<Map<String, Object>> getPrcAllocIntfcStatusClearList(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrcAllocIntfcStatusClearList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForDsContrAddlChrgStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxDsContrAddlChrgListPMsg xxDsContrAddlChrg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        if (!CHRG_LVL_TP.CONTRACT.equals(tmsg.chrgLvlTpCd.getValue())) {
            param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
            if (xxDsContrAddlChrg.svcMachMstrPk.getValue() == null) {
                setValue(xxDsContrAddlChrg.svcMachMstrPk, BigDecimal.ZERO);
            }
            param.put("svcMachMasterPk", xxDsContrAddlChrg.svcMachMstrPk.getValue());
        }
        param.put("chagLvlTpCd", tmsg.chrgLvlTpCd.getValue());
        param.put("addlChrgFlatDealPrcAmt", xxDsContrAddlChrg.addlChrgFlatDealPrcAmt.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForDsContrCrCardPoStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxDsContrCrCardPoListPMsg xxDsContrCrCardPo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxDsContrCrCardPo.svcMachMstrPk.getValue() == null) {
            setValue(xxDsContrCrCardPo.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxDsContrCrCardPo.svcMachMstrPk.getValue());
        param.put("bllgMtrLbCdFlg", ZYPConstant.FLG_ON_Y);
        if (!hasValue(xxDsContrCrCardPo.bllgMtrLbCd)) {
            setValue(xxDsContrCrCardPo.bllgMtrLbCd, "0");
        }
        param.put("bllgMtrLbCd", xxDsContrCrCardPo.bllgMtrLbCd.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForDsContrRnwEsclStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxDsContrRnwEsclListPMsg xxDsContrRnwEscl) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxDsContrRnwEscl.svcMachMstrPk.getValue() == null) {
            setValue(xxDsContrRnwEscl.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxDsContrRnwEscl.svcMachMstrPk.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForSvcTermCondStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxSvcTermCondListPMsg xxSvcTermCond) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxSvcTermCond.svcMachMstrPk.getValue() == null) {
            setValue(xxSvcTermCond.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxSvcTermCond.svcMachMstrPk.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForContrXsCopyStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxContrXsCopyListPMsg xxContrXsCopy) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxContrXsCopy.svcMachMstrPk.getValue() == null) {
            setValue(xxContrXsCopy.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxContrXsCopy.svcMachMstrPk.getValue());
        if (!hasValue(xxContrXsCopy.bllgMtrLbCd)) {
            setValue(xxContrXsCopy.bllgMtrLbCd, "0");
        }
        param.put("bllgMtrLbCd", xxContrXsCopy.bllgMtrLbCd.getValue());
        param.put("xsMtrCopyQty", xxContrXsCopy.xsMtrCopyQty.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonBllgMtrStatusErrorList", param);
    }

    // 2016/03/22 [QC#5543,QC# ADD]
    private List<Map<String, Object>> getDsAddlChrgStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxDsContrAddlChrgListPMsg xxDsContrAddlChrg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxDsContrAddlChrg.svcMachMstrPk.getValue() == null) {
            setValue(xxDsContrAddlChrg.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxDsContrAddlChrg.svcMachMstrPk.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsAddlChrgStatusErrorList", param);
    }

    // START 2016/06/24 [QC#10691, ADD]
    private List<Map<String, Object>> getDsAddlChrgStatusErrorListForHeader(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxDsContrAddlChrgListPMsg xxDsContrAddlChrg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsAddlChrgStatusErrorList", param);
    }
    // END   2016/06/24 [QC#10691, ADD]

    private List<Map<String, Object>> getContrXsCopyIntfcStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxContrXsCopyListPMsg xxContrXsCopy) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxContrXsCopy.svcMachMstrPk.getValue() == null) {
            setValue(xxContrXsCopy.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxContrXsCopy.svcMachMstrPk.getValue());
        param.put("bllgMtrLbCd", xxContrXsCopy.bllgMtrLbCd.getValue());
        param.put("xsMtrCopyQty", xxContrXsCopy.xsMtrCopyQty.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrXsCopyIntfcStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsActlCntIntfcStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxContrPhysBllgMtrRelnListPMsg xxContrPhysBllgMtrReln) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxContrPhysBllgMtrReln.machMstrPk.getValue() == null) {
            setValue(xxContrPhysBllgMtrReln.machMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxContrPhysBllgMtrReln.machMstrPk.getValue());
        param.put("bllgMtrLbCdFlg", ZYPConstant.FLG_ON_Y);
        param.put("bllgMtrLbCd", xxContrPhysBllgMtrReln.bllgMtrLbCd.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsActlCntIntfcStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForDsContrBllgMtrStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxDsContrBllgMtrListPMsg xxDsContrBllgMtr) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxDsContrBllgMtr.svcMachMstrPk.getValue() == null) {
            setValue(xxDsContrBllgMtr.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxDsContrBllgMtr.svcMachMstrPk.getValue());
        param.put("bllgMtrLbCd", xxDsContrBllgMtr.bllgMtrLbCd.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonBllgMtrStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForDsContrDtlStatusErrorList(DS_CONTR_INTFCTMsg tmsg, NSZC046001_xxContrDtlListPMsg xxContrDtl) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        param.put("svcMachMasterFlg", ZYPConstant.FLG_ON_Y);
        if (xxContrDtl.svcMachMstrPk.getValue() == null) {
            setValue(xxContrDtl.svcMachMstrPk, BigDecimal.ZERO);
        }
        param.put("svcMachMasterPk", xxContrDtl.svcMachMstrPk.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForCommonStatusErrorList", param);
    }

    private List<Map<String, Object>> getDsContrIntfcForDsContrStatusErrorList(NSZC046001PMsg pMsg, DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcForDsContrStatusErrorList", param);
    }

    private DS_CONTR_INTFCTMsg getDsContrIntfcQaTarget(BigDecimal pk) {
        DS_CONTR_INTFCTMsg param = new DS_CONTR_INTFCTMsg();
        setValue(param.glblCmpyCd, this.glblCmpyCd);
        setValue(param.dsContrIntfcPk, pk);

        return (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(param);
    }

    // START 2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
    private List<Map<String, Object>> getDsContrIntfcQaDsContrIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcQaDsContrIntfc", param);
    }
    // END   2016/03/24 Y.Tsuchimoto [QC#5662 ADD]

    private List<Map<String, Object>> getDsContrIntfcQaDsActlCntIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcQaDsActlCntIntfc", param);
    }

    private List<Map<String, Object>> getDsContrIntfcQaDsXsCoppIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcQaDsXsCoppIntfc", param);
    }

    private List<Map<String, Object>> getDsContrIntfcQaDsAddlChrgIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcQaDsAddlChrgIntfc", param);
    }

    private List<Map<String, Object>> getDsContrIntfcQaPrcAllocIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcQaPrcAllocIntfc", param);
    }

    // mod start 2016/10/19 CSA Defect#14868
    //START 2017/07/18 K.Kasai [QC#18780,MOD]
//    private void updateContractStatus(DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> oldMachInfoList) {
    private void updateContractStatus(DS_CONTR_INTFCTMsg tmsg, List<Map<String, Object>> oldMachInfoList, String vldRslt) {
    //END 2017/07/18 K.Kasai [QC#18780,MOD]
        if (oldMachInfoList.size() > 0) {
            return;
        }

        boolean stsUpdateFlg = isUpdateContractStatus(tmsg);
        // Update DS_CONTR
        // START 2016/03/30 [QC#6284, MOD]
        DS_CONTRTMsgArray dsContrList = getDsContrForContractStatus(tmsg.dsContrNum.getValue());
        for (int i = 0; i < dsContrList.getValidCount(); i++) {
            DS_CONTRTMsg target = (DS_CONTRTMsg) dsContrList.get(i);
            // Update
            //START 2017/07/14 K.Kasai [QC#18780,MOD]
//            if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
//                setValue(target.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//            }
//            if (stsUpdateFlg && !isManContrOvrd(tmsg) && DS_CONTR_STS.DRAFT.equals(target.dsContrStsCd.getValue())) {
//                // START 2017/07/19 [QC#19986, MOD]
//                // setValue(target.dsContrStsCd, DS_CONTR_STS.APPROVED);
//                if (ZYPDateUtil.compare(target.contrVrsnEffFromDt.getValue(), this.salesDate) <= 0) {
//                    setValue(target.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
//                } else {
//                    setValue(target.dsContrStsCd, DS_CONTR_STS.APPROVED);
//            setValue(target.dsContrStsCd, DS_CONTR_STS.APPROVED);
//          }
//                // END   2017/07/19 [QC#19986, MOD]
            if (stsUpdateFlg && !isManContrOvrd(tmsg)) {
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                if (DS_CONTR_STS.DRAFT.equals(target.dsContrStsCd.getValue())
                if ((DS_CONTR_STS.DRAFT.equals(target.dsContrStsCd.getValue())
                        || DS_CONTR_STS.ENTERED.equals(target.dsContrStsCd.getValue()))
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                        && NSZC057001Constant.SUCCESS.equals(vldRslt)) {
                    if (ZYPDateUtil.compare(target.contrVrsnEffFromDt.getValue(), this.salesDate) <= 0) {
                        setValue(target.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
                    } else {
                        setValue(target.dsContrStsCd, DS_CONTR_STS.APPROVED);
                    }
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                } else if (!DS_CONTR_STS.DRAFT.equals(target.dsContrStsCd.getValue())
                } else if (!(DS_CONTR_STS.DRAFT.equals(target.dsContrStsCd.getValue())
                        || DS_CONTR_STS.ENTERED.equals(target.dsContrStsCd.getValue()))
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                        && !NSZC057001Constant.SUCCESS.equals(vldRslt)
                        && ZYPConstant.FLG_OFF_N.equals(target.qltyAsrnHldFlg.getValue())) {
                    setValue(target.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                }
                } else if (DS_CONTR_STS.DRAFT.equals(target.dsContrStsCd.getValue())
                        && !NSZC057001Constant.SUCCESS.equals(vldRslt)) {
                    setValue(target.dsContrStsCd, DS_CONTR_STS.ENTERED);
                }
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                EZDTBLAccessor.update(target);
            } else {
                continue;
            }
            //END 2017/07/14 K.Kasai [QC#18780,MOD]

            // Update DS_CONTR_DTL
            DS_CONTR_DTLTMsgArray dsContrDtlList = getDsContrDtlForContractStatus(target.dsContrPk.getValue());
            for (int j = 0; j < dsContrDtlList.getValidCount(); j++) {
                DS_CONTR_DTLTMsg targetDtl = (DS_CONTR_DTLTMsg) dsContrDtlList.get(j);
                //START 2017/07/14 K.Kasai [QC#18780,ADD]
                String dsContrDtlStsCd = targetDtl.dsContrDtlStsCd.getValue();
                String qltyAsrnHldFlg = targetDtl.qltyAsrnHldFlg.getValue();
                //END 2017/07/14 K.Kasai [QC#18780,ADD]
                // Update
                //START 2017/07/14 K.Kasai [QC#18780,MOD]
//                if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
//                    setValue(targetDtl.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                }
//                // START 2016/06/03 [QC#4061, MOD]
//                if (stsUpdateFlg && !isManContrOvrd(tmsg) && DS_CONTR_DTL_STS.SAVED.equals(targetDtl.dsContrDtlStsCd.getValue())) {
//                    // START 2017/07/19 [QC#19986, MOD]
//                    // setValue(targetDtl.dsContrDtlStsCd, DS_CONTR_DTL_STS.SIGNED);
//                    if (ZYPDateUtil.compare(targetDtl.contrEffFromDt.getValue(), this.salesDate) <= 0) {
//                     setValue(targetDtl.dsContrDtlStsCd, DS_CONTR_DTL_STS.ACTIVE);
//                  } else {
//                      setValue(targetDtl.dsContrDtlStsCd, DS_CONTR_DTL_STS.SIGNED);
//                  }
//                  // END   2017/07/19 [QC#19986, MOD]
//                }
//                // END 2016/06/03 [QC#4061, MOD]
//                EZDTBLAccessor.update(targetDtl);
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                if (DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)
                if ((DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)
                        || DS_CONTR_DTL_STS.SUBMITED.equals(dsContrDtlStsCd))
                // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
                        && NSZC057001Constant.SUCCESS.equals(vldRslt)) {
                    if (ZYPDateUtil.compare(targetDtl.contrEffFromDt.getValue(), this.salesDate) <= 0) {
                        dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
                    } else {
                        dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
                    }
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                } else if (!DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)
                } else if ((!DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)
                    && !DS_CONTR_DTL_STS.SUBMITED.equals(dsContrDtlStsCd))
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                    && !NSZC057001Constant.SUCCESS.equals(vldRslt)
                    && ZYPConstant.FLG_OFF_N.equals(qltyAsrnHldFlg)) {
                    qltyAsrnHldFlg = ZYPConstant.FLG_ON_Y;
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                }
                } else if (DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)
                    && !NSZC057001Constant.SUCCESS.equals(vldRslt)) {
                    dsContrDtlStsCd = DS_CONTR_DTL_STS.SUBMITED;
                }
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                setValue(targetDtl.dsContrDtlStsCd, dsContrDtlStsCd);
                setValue(targetDtl.qltyAsrnHldFlg, qltyAsrnHldFlg);
                EZDTBLAccessor.update(targetDtl);
                //END 2017/07/14 K.Kasai [QC#18780,MOD]

                // Update DS_CONTR_BLLG_MTR
                DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrList = getDsContrBllgMtrForContractStatus(targetDtl.dsContrDtlPk.getValue());
                for (int k = 0; k < dsContrBllgMtrList.getValidCount(); k++) {
                    DS_CONTR_BLLG_MTRTMsg targetBllgMtr = (DS_CONTR_BLLG_MTRTMsg) dsContrBllgMtrList.get(k);
                    // Update
                    //START 2017/07/14 K.Kasai [QC#18780,MOD]
//                    if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
//                        setValue(targetBllgMtr.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                    }
//                    if (stsUpdateFlg && DS_CONTR_DTL_STS.SAVED.equals(targetBllgMtr.dsContrBllgMtrStsCd.getValue())) {
//                        // START 2017/07/19 [QC#19986, MOD]
//                        // setValue(targetBllgMtr.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SIGNED);
//                        if (ZYPDateUtil.compare(targetDtl.contrEffFromDt.getValue(), this.salesDate) <= 0) {
//                            setValue(targetBllgMtr.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.ACTIVE);
//                        } else {
//                            setValue(targetBllgMtr.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SIGNED);
//                        }
//                        // END   2017/07/19 [QC#19986, MOD]
//                    }
                    setValue(targetBllgMtr.dsContrBllgMtrStsCd, dsContrDtlStsCd);
                    setValue(targetBllgMtr.qltyAsrnHldFlg, qltyAsrnHldFlg);
                    //END 2017/07/14 K.Kasai [QC#18780,MOD]
                    EZDTBLAccessor.update(targetBllgMtr);
                }

                // Update DS_CONTR_PRC_EFF
                DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffList = getDsContrPrcEffForContractStatus(targetDtl.dsContrDtlPk.getValue());
                for (int l = 0; l < dsContrPrcEffList.getValidCount(); l++) {
                    DS_CONTR_PRC_EFFTMsg targetPrcEff = (DS_CONTR_PRC_EFFTMsg) dsContrPrcEffList.get(l);
                    // Update
                    //START 2017/07/14 K.Kasai [QC#18780,MOD]
//                    if (DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
//                        setValue(targetPrcEff.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
//                    }
//                    if (stsUpdateFlg && DS_CONTR_DTL_STS.SAVED.equals(targetPrcEff.dsContrPrcEffStsCd.getValue())) {
//                        // START 2017/07/19 [QC#19986, MOD]
//                        // setValue(targetPrcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
//                        if (ZYPDateUtil.compare(targetDtl.contrEffFromDt.getValue(), this.salesDate) <= 0) {
//                            setValue(targetPrcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
//                        } else {
//                            setValue(targetPrcEff.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
//                        }
//                        // END   2017/07/19 [QC#19986, MOD]
//                    }
                    setValue(targetPrcEff.dsContrPrcEffStsCd, dsContrDtlStsCd);
                    setValue(targetPrcEff.qltyAsrnHldFlg, qltyAsrnHldFlg);
                    //END 2017/07/14 K.Kasai [QC#18780,MOD]
                    EZDTBLAccessor.update(targetPrcEff);
                }
            }
            // END 2016/03/30 [QC#6284, MOD]
        }
    }
    // mod end 2016/10/19 CSA Defect#14868

    private boolean isUpdateContractStatus(DS_CONTR_INTFCTMsg tmsg) {
        if (getDsContrIntfcForContractStatus(tmsg).getValidCount() > 0) {
            return false;
        }
        //START 2017/07/12 K.Kasai [QC#18780,DEL]
//        // START 2016/07/11 [QC#11451, ADD]
//        int cntVldError = countupContrVldRsltWrk(tmsg);
//        if (cntVldError > 0) {
//            return false;
//        }
//        // END   2016/07/11 [QC#11451, ADD]
        //END 2017/07/12 K.Kasai [QC#18780,DEL]
        return true;
    }

    // START 2016/07/11 [QC#11451, ADD]
    private Integer countupContrVldRsltWrk(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrVldStsCd", DS_CONTR_VLD_STS.ERROR);
        return (Integer) this.ssmBatchClient.queryObject("countupContrVldRsltWrk", param);
    }
    // END   2016/07/11 [QC#11451, ADD]

    private DS_CONTR_INTFCTMsgArray getDsContrIntfcForContractStatus(DS_CONTR_INTFCTMsg tmsg) {
        DS_CONTR_INTFCTMsg param = new DS_CONTR_INTFCTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrSrcRefNum01", tmsg.dsContrSrcRefNum.getValue());
        param.setConditionValue("contrIntfcSrcTpCd01", tmsg.contrIntfcSrcTpCd.getValue());
        param.setConditionValue("dsContrIntfcBatNum01", tmsg.dsContrIntfcBatNum.getValue());
        param.setConditionValue("dsContrIntfcStsCd01", DS_CONTR_INTFC_STS.SUCCESS);

        return (DS_CONTR_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }

    private DS_CONTRTMsgArray getDsContrForContractStatus(String dsContrNum) {
        DS_CONTRTMsg param = new DS_CONTRTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrNum01", dsContrNum);
        param.setConditionValue("dsContrSqNum01", "00");

        return (DS_CONTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtlForContractStatus(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
        // Mod Start 2017/09/08 QC#20986
        param.setSQLID("204");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("dsContrDtlStsCd01", DS_CONTR_DTL_STS.ORDER);
        // Mod End 2017/09/08 QC#20986
        return (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrForContractStatus(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg param = new DS_CONTR_BLLG_MTRTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);

        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }

    // START 2016/03/30 [QC#6284, MOD]
    private DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffForContractStatus(BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_EFFTMsg param = new DS_CONTR_PRC_EFFTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);

        return (DS_CONTR_PRC_EFFTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }
    // END   2016/03/30 [QC#6284, MOD]

    private DS_CONTR_INTFCTMsgArray getDsContrForDsSubContract(String contrIntfcSrcTpCd, String dsContrSrcRefNum) {
        DS_CONTR_INTFCTMsg param = new DS_CONTR_INTFCTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("contrIntfcSrcTpCd01", contrIntfcSrcTpCd);
        param.setConditionValue("dsContrSrcRefNum01", dsContrSrcRefNum);

        return (DS_CONTR_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);

    }

    private boolean isExistDsContr(String dsContrNum) {

        DS_CONTRTMsgArray list = getDsContrForContractStatus(dsContrNum);
        if (list.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    // START 2016/06/22 [QC#10586, MOD]
    private boolean isExistSvcMachMstr(String serNum) {
        SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("serNum01", serNum);

        SVC_MACH_MSTRTMsgArray list = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private boolean isExistVnd(String vndCd) {
        VNDTMsg param = new VNDTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("vndCd01", vndCd);

        VNDTMsgArray list = (VNDTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return true;
        }
        return false;
    }
    // END   2016/06/22 [QC#10586, MOD]

    private boolean isExistTechMstr(String techTocCd) {
        TECH_MSTRTMsg param = new TECH_MSTRTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("techTocCd01", techTocCd);

        TECH_MSTRTMsgArray list = (TECH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    // START 2016/06/22 [QC#10586, MOD]
    private boolean isIntegrityEffectiveDate(DS_SUB_CONTR_INTFCTMsg tmsg) {
        if (tmsg != null) {
            if (hasValue(tmsg.effFromDt) && tmsg.effFromDt.getValue().compareTo(tmsg.effThruDt.getValue()) <= 0) {
                return true;
            }
        }
        return false;
    }
    // END   2016/06/22 [QC#10586, MOD]

    private boolean isCheckDsContrIntfcForDsContrIntfcBatNum(DS_CONTR_INTFCTMsgArray dsContrIntfcList) {
        return isCheckDsContrIntfc(dsContrIntfcList, MODE_DS_CONTR_INTFC_BAT_NUM);
    }

    private boolean isCheckDsContrIntfcForDsContrNum(DS_CONTR_INTFCTMsgArray dsContrIntfcList) {
        return isCheckDsContrIntfc(dsContrIntfcList, MODE_DS_CONTR_NUM);
    }

    private boolean isCheckDsContrIntfcForDsContrIntfcActCd(DS_CONTR_INTFCTMsgArray dsContrIntfcList) {
        return isCheckDsContrIntfc(dsContrIntfcList, MODE_DS_CONTR_INTFC_ACT_CD);
    }

    private boolean isCheckDsContrIntfc(DS_CONTR_INTFCTMsgArray dsContrIntfcList, String mode) {
        String beforeContrIntfcSrcTpCd = null;
        String beforeDsContrSrcRefNum = null;
        String beforeTarget = null;
        String contrIntfcSrcTpCd = null;
        String dsContrSrcRefNum = null;
        String target = null;
        for (int i = 0; i < dsContrIntfcList.getValidCount(); i++) {
            DS_CONTR_INTFCTMsg dsContrIntfc = (DS_CONTR_INTFCTMsg) dsContrIntfcList.get(i);
            contrIntfcSrcTpCd = dsContrIntfc.contrIntfcSrcTpCd.getValue();
            dsContrSrcRefNum = dsContrIntfc.dsContrSrcRefNum.getValue();
            if (MODE_DS_CONTR_INTFC_BAT_NUM.equals(mode)) {
                target = dsContrIntfc.dsContrIntfcBatNum.getValue();
            } else if (MODE_DS_CONTR_NUM.equals(mode)) {
                target = dsContrIntfc.dsContrNum.getValue();
            } else if (MODE_DS_CONTR_INTFC_ACT_CD.equals(mode)) {
                target = dsContrIntfc.dsContrIntfcActCd.getValue();
            }
            if (i != 0) {
                DS_CONTR_INTFCTMsg beforeDsContrIntfc = (DS_CONTR_INTFCTMsg) dsContrIntfcList.get(i - 1);
                beforeContrIntfcSrcTpCd = beforeDsContrIntfc.contrIntfcSrcTpCd.getValue();
                beforeDsContrSrcRefNum = beforeDsContrIntfc.dsContrSrcRefNum.getValue();
                if (MODE_DS_CONTR_INTFC_BAT_NUM.equals(mode)) {
                    beforeTarget = beforeDsContrIntfc.dsContrIntfcBatNum.getValue();
                } else if (MODE_DS_CONTR_NUM.equals(mode)) {
                    beforeTarget = beforeDsContrIntfc.dsContrNum.getValue();
                } else if (MODE_DS_CONTR_INTFC_ACT_CD.equals(mode)) {
                    beforeTarget = beforeDsContrIntfc.dsContrIntfcActCd.getValue();
                }

            }
            if (i != 0) {
                if (contrIntfcSrcTpCd.equals(beforeContrIntfcSrcTpCd) && dsContrSrcRefNum.equals(beforeDsContrSrcRefNum)) {
                    if (hasValue(target) && !target.equals(beforeTarget)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private List<Map<String, Object>> getDsContrListForNSZC0520(DS_SUB_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrNum", tmsg.dsContrNum.getValue());
        param.put("serNum", tmsg.serNum.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrListForNSZC0520", param);
    }

    private List<Map<String, Object>> getDsContrListForNSZC0100(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("maxDate", MAX_DATE);
        param.put("format", FORMAT);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrListForNSZC0100", param);
    }

    private List<Map<String, Object>> getTermCondition(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        String[] svcTermCondAttrb = new String[TERM_COND_CA_MAX_LENGTH];
        svcTermCondAttrb[ARGS_TERM_COND_CAP_BW_ORIG_ATTRB_NM] = getSvcTermCondAttrb(TERM_COND_CAP_BW_ORIG_ATTRB_NM);
        svcTermCondAttrb[ARGS_TERM_COND_CAP_CL_ORIG_ATTRB_NM] = getSvcTermCondAttrb(TERM_COND_CAP_CL_ORIG_ATTRB_NM);
        svcTermCondAttrb[ARGS_TERM_COND_CAP_TO_ORIG_ATTRB_NM] = getSvcTermCondAttrb(TERM_COND_CAP_TO_ORIG_ATTRB_NM);
        svcTermCondAttrb[ARGS_TERM_COND_CAP_BW_RUN_ATTRB_NM] = getSvcTermCondAttrb(TERM_COND_CAP_BW_RUN_ATTRB_NM);
        svcTermCondAttrb[ARGS_TERM_COND_CAP_CLR_RUN_ATTRB_NM] = getSvcTermCondAttrb(TERM_COND_CAP_CLR_RUN_ATTRB_NM);
        svcTermCondAttrb[ARGS_TERM_COND_CAP_TOT_RUN_ATTRB_NM] = getSvcTermCondAttrb(TERM_COND_CAP_TOT_RUN_ATTRB_NM);
        svcTermCondAttrb[ARGS_TERM_COND_PRC_SVC_PLN_TP_CD] = getSvcTermCondAttrb(TERM_COND_PRC_SVC_PLN_TP_CD);

        param.put("svcTermCondAttrb", svcTermCondAttrb);
        param.put("slsDt", this.salesDate);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getTermCondition", param);
    }

    private String getSvcTermCondAttrb(String varCharConstNm) {
        VAR_CHAR_CONSTTMsg tMsg = new VAR_CHAR_CONSTTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.varCharConstNm.setValue(varCharConstNm);
        VAR_CHAR_CONSTTMsg bwOrigTmsg = (VAR_CHAR_CONSTTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (bwOrigTmsg != null) {
            return bwOrigTmsg.varCharConstNm.getValue();
        }
        return null;
    }

    private void setParameterForTermCondition(NSZC046001PMsg nszc046001PMsg, List<Map<String, Object>> termConditionList, DS_CONTR_INTFCTMsg tmsg) {
        if (nszc046001PMsg != null && termConditionList != null && tmsg != null) {
            int i = 0;
            for (; i < termConditionList.size(); i++) {
                Map<String, Object> contrBillingMeter = termConditionList.get(i);
                setValue(nszc046001PMsg.xxSvcTermCondList.no(i).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
                setValue(nszc046001PMsg.xxSvcTermCondList.no(i).svcTermCondPk, (BigDecimal) contrBillingMeter.get("SVC_TERM_COND_PK"));
                setValue(nszc046001PMsg.xxSvcTermCondList.no(i).dsContrPk, (BigDecimal) contrBillingMeter.get("DS_CONTR_PK"));
                setValue(nszc046001PMsg.xxSvcTermCondList.no(i).dsContrDtlPk, (BigDecimal) contrBillingMeter.get("DS_CONTR_DTL_PK"));
                setValue(nszc046001PMsg.xxSvcTermCondList.no(i).svcTermCondAttrbPk, (BigDecimal) contrBillingMeter.get("SVC_TERM_COND_ATTRB_PK"));
                String svcTermCondAttrbNm = (String) contrBillingMeter.get("SVC_TERM_COND_ATTRB_NM");
                if (isBigDecimalSvcTermAttrbMapValCdColumn(svcTermCondAttrbNm)) {
                    BigDecimal svcTermAttrbMapValCd = (BigDecimal) contrBillingMeter.get(getSvcTermAttrbMapValCdColumn(svcTermCondAttrbNm));
                    if (svcTermAttrbMapValCd != null) {
                        setValue(nszc046001PMsg.xxSvcTermCondList.no(i).svcTermAttrbMapValCd, svcTermAttrbMapValCd.toString());
                    } else {
                        setValue(nszc046001PMsg.xxSvcTermCondList.no(i).svcTermAttrbMapValCd, (String) null);
                    }
                } else {
                    setValue(nszc046001PMsg.xxSvcTermCondList.no(i).svcTermAttrbMapValCd, (String) contrBillingMeter.get(getSvcTermAttrbMapValCdColumn(svcTermCondAttrbNm)));
                }
                setValue(nszc046001PMsg.xxSvcTermCondList.no(i).svcMachMstrPk, (BigDecimal) contrBillingMeter.get("SVC_MACH_MSTR_PK"));
            }
            nszc046001PMsg.xxSvcTermCondList.setValidCount(i);
        }
    }

    private String getSvcTermAttrbMapValCdColumn(String svcTermCondAttrbNm) {
        if (svcTermCondAttrbNm == null) {
            return null;
        }

        if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_CAP_BW_ORIG_ATTRB_NM))) {
            return COL_CAP_BW_ORIG_QTY;
        } else if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_CAP_CL_ORIG_ATTRB_NM))) {
            return COL_CAP_CLR_ORIG_QTY;
        } else if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_CAP_TO_ORIG_ATTRB_NM))) {
            return COL_CAP_TOT_ORIG_QTY;
        } else if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_CAP_BW_RUN_ATTRB_NM))) {
            return COL_CAP_BW_RUN_QTY;
        } else if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_CAP_CLR_RUN_ATTRB_NM))) {
            return COL_CAP_CLR_RUN_QTY;
        } else if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_CAP_TOT_RUN_ATTRB_NM))) {
            return COL_CAP_TOT_RUN_QTY;
        } else if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_PRC_SVC_PLN_TP_CD))) {
            return COL_PRC_SVC_PLN_TP_CD;
        }
        return null;
    }

    private boolean isBigDecimalSvcTermAttrbMapValCdColumn(String svcTermCondAttrbNm) {
        if (svcTermCondAttrbNm == null) {
            return true;
        }

        if (svcTermCondAttrbNm.equals(getSvcTermCondAttrb(TERM_COND_PRC_SVC_PLN_TP_CD))) {
            return false;
        }
        return true;
    }

    private List<Map<String, Object>> getContractPriceAllocationHeader(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("contract", PRC_ALLOC_INTFC_TP.CONTRACT);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractPriceAllocationHeader", param);
    }

    private List<Map<String, Object>> getContractPriceAllocationMachine(DS_CONTR_INTFCTMsg tmsg) {
        return getContractPriceAllocationDetailMachine(tmsg, PRC_ALLOC_INTFC_TP.MACHINE);
    }

    private List<Map<String, Object>> getContractPriceAllocationDetailMachine(DS_CONTR_INTFCTMsg tmsg, String prcAllocIntfcTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("prcAllocIntfcTpCd", prcAllocIntfcTpCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractPriceAllocationDetailMachine", param);
    }

    private List<Map<String, Object>> getContractPriceAllocationBase(DS_CONTR_INTFCTMsg tmsg) {
        return getContractPriceAllocationDetailBaseAndUsage(tmsg, PRC_ALLOC_INTFC_TP.BASE);
    }

    private List<Map<String, Object>> getContractPriceAllocationUsage(DS_CONTR_INTFCTMsg tmsg) {
        return getContractPriceAllocationDetailBaseAndUsage(tmsg, PRC_ALLOC_INTFC_TP.USAGE);
    }

    private List<Map<String, Object>> getContractPriceAllocationDetailBaseAndUsage(DS_CONTR_INTFCTMsg tmsg, String prcAllocIntfcTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("prcAllocIntfcTpCd", prcAllocIntfcTpCd);
        param.put("bc", SVC_INV_CHRG_TP.BASE_CHARGE);
        param.put("mc", SVC_INV_CHRG_TP.METER_CHARGE);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContractPriceAllocationDetailBaseAndUsage", param);
    }

    private void setParameterForContractPriceAllocation(NSZC046001PMsg pmsg, DS_CONTR_INTFCTMsg tmsg) {

        // get target data
        List<List<Map<String, Object>>> contractPriceAllocationList = new ArrayList<List<Map<String, Object>>>();
        contractPriceAllocationList.add(getContractPriceAllocationHeader(tmsg));
        contractPriceAllocationList.add(getContractPriceAllocationMachine(tmsg));
        contractPriceAllocationList.add(getContractPriceAllocationBase(tmsg));
        contractPriceAllocationList.add(getContractPriceAllocationUsage(tmsg));
        int setParameterCnt = 0;
        for (int h = 0; h < contractPriceAllocationList.size(); h++) {
            // set parameter for NSXC0040
            List<Map<String, Object>> contractPriceAllocationTargetList = contractPriceAllocationList.get(h);
            GetDefCoaTrxCdInfoBean[] beanList = new GetDefCoaTrxCdInfoBean[contractPriceAllocationTargetList.size()];
            setParameterForNSXC004001(beanList, contractPriceAllocationTargetList);
            // Call NSXC0040
            callNSXC0040(beanList);
            // check combination
            if (checkNineSegmentCombination(beanList)) {
                // set parameter for NSZC0460
                for (int i = 0; i < contractPriceAllocationTargetList.size(); i++) {
                    Map<String, Object> contractPriceAllocationTarget = contractPriceAllocationTargetList.get(i);
                    GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
                    setParameterForNSXC004001(bean, contractPriceAllocationTarget);
                    // call NSXC0040
                    bean = NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean);
                    GetDefCoaTrxCdForOutListInfoBean outBean = bean.getOutLisstInfoBean().get(0);
                    // 2-11-4.set parameter for DS_CONTR_BR_ALLOC
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).dsContrBrAllocPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_BR_ALLOC_PK"));
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).dsContrPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_PK"));
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).dsContrDtlPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_DTL_PK"));
                    // START 2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
                    String svcInvChrgTpCd = (String) contractPriceAllocationTarget.get("PRC_ALLOC_INTFC_TP_CD");
                    if (PRC_ALLOC_INTFC_TP.BASE.equals(svcInvChrgTpCd)) {
                        setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).svcInvChrgTpCd, SVC_INV_CHRG_TP.BASE_CHARGE);
                    } else if (PRC_ALLOC_INTFC_TP.USAGE.equals(svcInvChrgTpCd)) {
                        setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).svcInvChrgTpCd, SVC_INV_CHRG_TP.METER_CHARGE);
                    } else {
                        setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).svcInvChrgTpCd, (String) null);
                    }
                    // END   2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).coaBrCd, outBean.getCoaBrCd());
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).prcAllocRate, outBean.getPrcAllocPct());
                    setValue(pmsg.xxDsContrBrAllocList.no(setParameterCnt).svcMachMstrPk, (BigDecimal) contractPriceAllocationTarget.get("SVC_MACH_MSTR_PK"));

                    // 2-11-5.set parameter for DS_CONTR_SEG_ALLOC
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).xxModeCd, getXxModeCdForLevel2(tmsg.dsContrIntfcActCd.getValue()));
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).dsContrSegAllocPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_SEG_ALLOC_PK"));
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).dsContrPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_PK"));
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).dsContrDtlPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_DTL_PK"));
                    // START 2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
                    if (PRC_ALLOC_INTFC_TP.BASE.equals(svcInvChrgTpCd)) {
                        setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).svcInvChrgTpCd, SVC_INV_CHRG_TP.BASE_CHARGE);
                    } else if (PRC_ALLOC_INTFC_TP.USAGE.equals(svcInvChrgTpCd)) {
                        setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).svcInvChrgTpCd, SVC_INV_CHRG_TP.METER_CHARGE);
                    } else {
                        setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).svcInvChrgTpCd, (String) null);
                    }
                    // END   2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaCmpyCd, outBean.getCoaCmpyCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaAfflCd, outBean.getCoaAfflCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaBrCd, outBean.getCoaBrCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaChCd, outBean.getCoaChCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaCcCd, outBean.getCoaCcCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaAcctCd, outBean.getCoaAcctCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaProdCd, outBean.getCoaProdCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaProjCd, outBean.getCoaProjCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).coaExtnCd, outBean.getCoaExtnCd());
                    setValue(pmsg.xxDsContrSegAllocList.no(setParameterCnt).svcMachMstrPk, (BigDecimal) contractPriceAllocationTarget.get("SVC_MACH_MSTR_PK"));

                    setParameterCnt++;
                }
            // START 2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
            } else {
                // START 2016/05/24 [QC#4061, MOD]
                if (!isManContrOvrd(tmsg)) {
                    for (int i = 0; i < contractPriceAllocationTargetList.size(); i++) {
                        // 2.15. get Insert DS_CONTR_PRC_ALLOC
                        Map<String, Object> contractPriceAllocationTarget = contractPriceAllocationTargetList.get(i);
                        insertDsContrPrcAlloc(beanList, contractPriceAllocationTarget, tmsg);
                    }
                }
                // END   2016/05/24 [QC#4061, MOD]
            }
            // END   2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
        }
    }

    // START 2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]
    private void insertDsContrPrcAlloc(GetDefCoaTrxCdInfoBean[] beanList, Map<String, Object> contractPriceAllocationTarget, DS_CONTR_INTFCTMsg tmsg) {
        if (beanList == null || beanList.length == 0) {
            return;
        }
        for (int i = 0; i < beanList.length; i++) {
            GetDefCoaTrxCdInfoBean bean = beanList[i];
            bean = NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean);
            GetDefCoaTrxCdForOutListInfoBean outBean = bean.getOutLisstInfoBean().get(0);

            DS_CONTR_PRC_ALLOCTMsg tMsg = new DS_CONTR_PRC_ALLOCTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            BigDecimal dsContrPrcAllocPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_ALLOC_SQ);
            setValue(tMsg.dsContrPrcAllocPk, dsContrPrcAllocPk);
            setValue(tMsg.dsContrPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_PK"));
            setValue(tMsg.dsContrDtlPk, (BigDecimal) contractPriceAllocationTarget.get("DS_CONTR_DTL_PK"));
            String svcInvChrgTpCd = (String) contractPriceAllocationTarget.get("PRC_ALLOC_INTFC_TP_CD");
            if (PRC_ALLOC_INTFC_TP.BASE.equals(svcInvChrgTpCd)) {
                setValue(tMsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.BASE_CHARGE);
            } else if (PRC_ALLOC_INTFC_TP.USAGE.equals(svcInvChrgTpCd)) {
                setValue(tMsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.METER_CHARGE);
            } else {
                setValue(tMsg.svcInvChrgTpCd, (String) null);
            }
            setValue(tMsg.coaCmpyCd, outBean.getCoaCmpyCd());
            setValue(tMsg.coaAfflCd, outBean.getCoaAfflCd());
            setValue(tMsg.coaBrCd, outBean.getCoaBrCd());
            setValue(tMsg.coaChCd, outBean.getCoaChCd());
            setValue(tMsg.coaCcCd, outBean.getCoaCcCd());
            setValue(tMsg.coaAcctCd, outBean.getCoaAcctCd());
            setValue(tMsg.coaProdCd, outBean.getCoaProdCd());
            setValue(tMsg.coaProjCd, outBean.getCoaProjCd());
            setValue(tMsg.coaExtnCd, outBean.getCoaExtnCd());
            setValue(tMsg.prcAllocRate, outBean.getPrcAllocPct());
            EZDTBLAccessor.insert(tMsg);

            // START 2016/05/24 [QC#4061, MOD]
            if (!isManContrOvrd(tmsg)) {
                // 3.5.PRC_ALLOC_INTFC Status Clear
                List<Map<String, Object>> prcAllocIntfcList = getPrcAllocIntfcStatusClearList(tmsg);
                for (int j = 0; j < prcAllocIntfcList.size(); j++) {
                    Map<String, Object> prcAllocIntfc = prcAllocIntfcList.get(j);
                    PRC_ALLOC_INTFCTMsg prcAllocIntfcTmsg = new PRC_ALLOC_INTFCTMsg();
                    setValue(prcAllocIntfcTmsg.glblCmpyCd, this.glblCmpyCd);
                    setValue(prcAllocIntfcTmsg.prcAllocIntfcPk, (BigDecimal) prcAllocIntfc.get("PRC_ALLOC_INTFC_PK"));
                    PRC_ALLOC_INTFCTMsg target = (PRC_ALLOC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcAllocIntfcTmsg);
                    if (target != null) {
                        // Update
                        setValue(target.allocIntfcStsCd, DS_CONTR_INTFC_STS.SUCCESS);
                        setValue(target.intfcErrMsgTxt, (String) null);
                        EZDTBLAccessor.update(target);
                    }
                }

                // 2.16. check 9Segment Combination
                NFZC102001PMsg pMsg = callNFZC1020(outBean);
                // 2.17.PRC_ALLOC_INTFC set Error
                errorSetForPrcAllocIntfcSetError(pMsg, tmsg, contractPriceAllocationTarget);
            }
            // END   2016/05/24 [QC#4061, MOD]
        }
    }

    private NFZC102001PMsg callNFZC1020(GetDefCoaTrxCdForOutListInfoBean outBean) {
        NFZC102001PMsg pMsg = new NFZC102001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.coaCmpyCd, outBean.getCoaCmpyCd());
        setValue(pMsg.coaAfflCd, outBean.getCoaAfflCd());
        setValue(pMsg.coaBrCd, outBean.getCoaBrCd());
        setValue(pMsg.coaChCd, outBean.getCoaChCd());
        setValue(pMsg.coaCcCd, outBean.getCoaCcCd());
        setValue(pMsg.coaAcctCd, outBean.getCoaAcctCd());
        setValue(pMsg.coaProdCd, outBean.getCoaProdCd());
        setValue(pMsg.coaProjCd, outBean.getCoaProjCd());
        setValue(pMsg.coaExtnCd, outBean.getCoaExtnCd());
        setValue(pMsg.resrcObjNm, RESRC_OBJ_NM);
        NFZC102001 api = new NFZC102001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        return pMsg;
    }

    private void errorSetForPrcAllocIntfcSetError(NFZC102001PMsg pMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> contractPriceAllocationTarget) {
        // 2.17.PRC_ALLOC_INTFC set Error
        if (pMsg.xxMsgIdList != null && pMsg.xxMsgIdList.getValidCount() > 0) {
            NFZC102001_xxMsgIdListPMsg xxMsg = (NFZC102001_xxMsgIdListPMsg) pMsg.xxMsgIdList.get(0);
            List<Map<String, Object>> prcAllocIntfcErrorList = getPrcAllocIntfcStatusErrorList(pMsg, tmsg, contractPriceAllocationTarget);
            for (int i = 0; i < prcAllocIntfcErrorList.size(); i++) {
                Map<String, Object> dsContrIntfc = prcAllocIntfcErrorList.get(i);
                PRC_ALLOC_INTFCTMsg prcAllocIntfc = new PRC_ALLOC_INTFCTMsg();
                // START 2016/06/29 [QC#10692,MOD]
                setValue(prcAllocIntfc.glblCmpyCd, this.glblCmpyCd);
                setValue(prcAllocIntfc.prcAllocIntfcPk, (BigDecimal) dsContrIntfc.get("PRC_ALLOC_INTFC_PK"));
                PRC_ALLOC_INTFCTMsg target = (PRC_ALLOC_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prcAllocIntfc);
                if (target != null) {
                    // Update
                    setValue(target.allocIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                    setValue(target.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(xxMsg.xxMsgId.getValue(), new String[] {xxMsg.xxMsgPrmTxt_0.getValue() }));
                    EZDTBLAccessor.update(target);
                }
                // END   2016/06/29 [QC#10692,MOD]
            }
        }
    }

    private List<Map<String, Object>> getPrcAllocIntfcStatusErrorList(NFZC102001PMsg pMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> contractPriceAllocationTarget) {
        if (pMsg == null || tmsg == null || contractPriceAllocationTarget == null) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        String prcAllocIntfcTpCd = (String) contractPriceAllocationTarget.get("PRC_ALLOC_INTFC_TP_CD");
        if (!PRC_ALLOC_INTFC_TP.CONTRACT.equals(prcAllocIntfcTpCd)) {
            param.put("svcMachMasterPk", (BigDecimal) contractPriceAllocationTarget.get("SVC_MACH_MSTR_PK"));
        }
        // START 2016/06/29 [QC#10692,ADD]
        param.put("error", DS_CONTR_INTFC_STS.ERROR);
        // END   2016/06/29 [QC#10692,ADD]
        param.put("prcAllocIntfcTpCd", prcAllocIntfcTpCd);
        param.put("tocCd", (String) contractPriceAllocationTarget.get("TOC_CD"));
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrcAllocIntfcStatusErrorList", param);
    }
    // END   2016/03/15 Y.Tsuchimoto [QC#869,QC#870 MOD]

    private void setParameterForNSXC004001(GetDefCoaTrxCdInfoBean[] beanList, List<Map<String, Object>> targetList) {
        if (beanList == null || targetList == null) {
            return;
        }

        for (int i = 0; i < targetList.size(); i++) {
            Map<String, Object> target = targetList.get(i);
            GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
            setParameterForNSXC004001(bean, target);

            beanList[i] = bean;
        }
    }

    private void setParameterForNSXC004001(GetDefCoaTrxCdInfoBean bean, Map<String, Object> target) {
        bean.setGlblCmpyCd(this.glblCmpyCd);
        bean.setMdseCd((String) target.get("MDSE_CD"));
        bean.setDsAcctNum((String) target.get("DS_ACCT_NUM"));
        if (!PRC_ALLOC_INTFC_TP.USAGE.equals((String) target.get("PRC_ALLOC_INTFC_TP_CD"))) {
            bean.setBaseChrgFlg(ZYPConstant.FLG_ON_Y);
        } else {
            bean.setBaseChrgFlg(ZYPConstant.FLG_OFF_N);
        }
        if (!PRC_ALLOC_INTFC_TP.BASE.equals((String) target.get("PRC_ALLOC_INTFC_TP_CD"))) {
            bean.setUsgChrgFlg(ZYPConstant.FLG_ON_Y);
        } else {
            bean.setUsgChrgFlg(ZYPConstant.FLG_OFF_N);
        }
        bean.setAddlChrgFlg(ZYPConstant.FLG_OFF_N);
        // 2016/03/15 QC#871 Y.Tsuchimoto Mod Start
        bean.setTocCd((String) target.get("TOC_CD"));
        //Mod Start 05/27/2016 <QC#8335>
//      if (PRC_ALLOC_INTFC_TP.USAGE.equals((String) target.get("PRC_ALLOC_INTFC_TP_CD"))) {
//          bean.setIntgMdseCd((String) target.get("INTG_MDSE_CD"));
//      }
//      if (PRC_ALLOC_INTFC_TP.BASE.equals((String) target.get("PRC_ALLOC_INTFC_TP_CD"))) {
//          bean.setSvcPgmMdseCd((String) target.get("SVC_PGM_MDSE_CD"));
//      }
        bean.setSvcPgmMdseCd((String) target.get("SVC_PGM_MDSE_CD"));
        bean.setSvcMachMstrPk((BigDecimal) target.get("SVC_MACH_MSTR_PK"));
        if (DS_CONTR_CATG.FLEET.equals((String) target.get("DS_CONTR_CATG_CD")) && !ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
            bean.setFleetLineFlg(ZYPConstant.FLG_ON_Y);
        } else {
            bean.setFleetLineFlg(ZYPConstant.FLG_OFF_N);
        }
        bean.setSvcLineBizCd((String) target.get("SVC_LINE_BIZ_CD"));
        // START 2016/09/27 T.Kanasaka [QC#9905, ADD]
        bean.setDsContrPk((BigDecimal) target.get("DS_CONTR_PK"));
        bean.setDsContrDtlPk((BigDecimal) target.get("DS_CONTR_DTL_PK"));
        // END 2016/09/27 T.Kanasaka [QC#9905, ADD]
        bean.setXxModeCd(NSXC004001GetDefCoaTrxCd.XX_MODE_02);
        //Mod End 05/27/2016 <QC#8335>
        // 2016/03/15 QC#871 Y.Tsuchimoto Mod End
    }

    private void callNSXC0040(GetDefCoaTrxCdInfoBean[] beanList) {
        for (int i = 0; i < beanList.length; i++) {
            beanList[i] = NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(beanList[i]);
        }
    }

    private boolean checkNineSegmentCombination(GetDefCoaTrxCdInfoBean[] beanList) {
        List<String> branchList = new ArrayList<String>();
        List<String> eightSegList = new ArrayList<String>();
        for (int i = 0; i < beanList.length; i++) {
            GetDefCoaTrxCdInfoBean bean = beanList[i];
            // get branch(no duplicate)
            if (bean != null && !branchList.contains(bean.getOutLisstInfoBean().get(0).getCoaBrCd())) {
                branchList.add(bean.getOutLisstInfoBean().get(0).getCoaBrCd());
            }
            // get 8seg(no duplicate)
            if (bean != null && !eightSegList.contains(get8SegmentCd(bean.getOutLisstInfoBean().get(0)))) {
                eightSegList.add(get8SegmentCd(bean.getOutLisstInfoBean().get(0)));
            }
        }

        // check combination
        for (int i = 0; i < branchList.size(); i++) {
            String branch = branchList.get(i);
            for (int j = 0; j < eightSegList.size(); j++) {
                String eightSeg = eightSegList.get(j);
                for (int k = 0; k < beanList.length; k++) {
                    GetDefCoaTrxCdForOutListInfoBean outBean = beanList[k].getOutLisstInfoBean().get(0);
                    if (!(hasValue(branch) && branch.equals(outBean.getCoaBrCd()) && (eightSeg != null && eightSeg.equals(get8SegmentCd(outBean))))) {
                        return false;
                    }
                }
            }
        }

        // check 100%(Branch, 8seg)
        BigDecimal sumPrcAllocPct = new BigDecimal(0);
        for (int i = 0; i < beanList.length; i++) {
            GetDefCoaTrxCdForOutListInfoBean outBean = beanList[i].getOutLisstInfoBean().get(0);
            if (outBean.getPrcAllocPct() != null) {
                sumPrcAllocPct = sumPrcAllocPct.add(outBean.getPrcAllocPct());
            }
        }
        if (sumPrcAllocPct.compareTo(new BigDecimal(MAX_PERCENT)) != 0) {
            return false;
        }

        // check ratio
        // get Branch sum
        Map<String, BigDecimal> branchMap = new HashMap<String, BigDecimal>();
        for (int i = 0; i < branchList.size(); i++) {
            BigDecimal sumRatioPerBranch = new BigDecimal(0);
            String branchCd = branchList.get(i);
            for (int j = 0; j < beanList.length; j++) {

                GetDefCoaTrxCdForOutListInfoBean outBean = beanList[j].getOutLisstInfoBean().get(0);
                if (hasValue(branchCd) && branchCd.equals(outBean.getCoaBrCd())) {
                    sumRatioPerBranch = sumRatioPerBranch.add(outBean.getPrcAllocPct());
                }
            }
            branchMap.put(branchCd, sumRatioPerBranch);
        }

        // get 8seg sum
        Map<String, BigDecimal> eightMap = new HashMap<String, BigDecimal>();
        for (int i = 0; i < eightSegList.size(); i++) {
            BigDecimal sumRatioPerEightSeg = new BigDecimal(0);
            String eightSeg = eightSegList.get(i);
            for (int j = 0; j < beanList.length; j++) {
                GetDefCoaTrxCdForOutListInfoBean outBean = beanList[j].getOutLisstInfoBean().get(0);
                if (hasValue(eightSeg) && eightSeg.equals(get8SegmentCd(outBean))) {
                    sumRatioPerEightSeg = sumRatioPerEightSeg.add(outBean.getPrcAllocPct());
                }
            }
            eightMap.put(eightSeg, sumRatioPerEightSeg);
        }

        for (String branchCd : branchMap.keySet()) {
            BigDecimal branchRatio = branchMap.get(branchCd);
            for (String eightSeg : eightMap.keySet()) {
                BigDecimal eightSegRatio = eightMap.get(eightSeg);
                BigDecimal checkRatio = branchRatio.add(eightSegRatio.divide(new BigDecimal(MAX_PERCENT)));
                for (int i = 0; i < beanList.length; i++) {
                    GetDefCoaTrxCdForOutListInfoBean outBean = beanList[i].getOutLisstInfoBean().get(0);
                    if ((hasValue(branchCd) && branchCd.equals(outBean.getCoaBrCd())) && hasValue(eightSeg) && eightSeg.equals(get8SegmentCd(outBean))) {
                        if (checkRatio.compareTo(outBean.getPrcAllocPct()) != 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private String get8SegmentCd(GetDefCoaTrxCdForOutListInfoBean outBean) {
        if (outBean == null) {
            return null;
        }
        StringBuffer rtn8Segment = new StringBuffer();
        String delimiter = getNineSegmentDelimiterChar();
        rtn8Segment.append(outBean.getCoaCmpyCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaExtnCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaCcCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaAcctCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaProjCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaProdCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaAfflCd()).append(delimiter);
        rtn8Segment.append(outBean.getCoaChCd()).append(delimiter);
        return rtn8Segment.toString();
    }

    private String getNineSegmentDelimiterChar() {
        VAR_CHAR_CONSTTMsg tMsg = new VAR_CHAR_CONSTTMsg();
        tMsg.glblCmpyCd.setValue(this.glblCmpyCd);
        tMsg.varCharConstNm.setValue(VARCHAR_NINE_SEGMENT_DELIMITER_CHAR);
        VAR_CHAR_CONSTTMsg bwOrigTmsg = (VAR_CHAR_CONSTTMsg) S21CodeTableAccessor.findByKey(tMsg);
        if (bwOrigTmsg != null) {
            return bwOrigTmsg.varCharConstNm.getValue();
        }
        return null;
    }

    private void setParameterForNSZC010001(List<NSZC010001PMsg> pmsgList, List<Map<String, Object>> dsContrListForNSZC0100List) {

        int headerCnt = 0;
        int detailCnt = 0;
        NSZC010001PMsg pmsg = null;
        for (int i = 0; i < dsContrListForNSZC0100List.size(); i++) {
            BigDecimal beforeSvcMachMstrPk = null;
            BigDecimal afterSvcMachMstrPk = null;
            BigDecimal svcMachMstrPk = null;
            if (i < dsContrListForNSZC0100List.size() - 1) {
                Map<String, Object> afterDsContrListForNSZC0100 = dsContrListForNSZC0100List.get(i + 1);
                afterSvcMachMstrPk = (BigDecimal) afterDsContrListForNSZC0100.get("SVC_MACH_MSTR_PK");
            }

            Map<String, Object> dsContrListForNSZC0100 = dsContrListForNSZC0100List.get(i);
            svcMachMstrPk = (BigDecimal) dsContrListForNSZC0100.get("SVC_MACH_MSTR_PK");
            // mod start 2016/08/23 CSA Defect#13704
            if (dsContrListForNSZC0100List.size() > 1 && i == dsContrListForNSZC0100List.size() - 1) {
                Map<String, Object> beforeContrListForNSZC0100 = dsContrListForNSZC0100List.get(i - 1);
                beforeSvcMachMstrPk = (BigDecimal) beforeContrListForNSZC0100.get("SVC_MACH_MSTR_PK");
                if (svcMachMstrPk.compareTo(beforeSvcMachMstrPk) != 0) {
                    headerCnt++;
                    detailCnt = 0;
                }
            }
            // mod end 2016/08/23 CSA Defect#13704
            // START 2016/03/17 Y.Tsuchimoto [QC#4123,QC#4126 MOD]
            // Header
            if (detailCnt == 0) {
                pmsg = new NSZC010001PMsg();
                setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
                setValue(pmsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
                setValue(pmsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                setValue(pmsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
                setValue(pmsg.slsDt, this.salesDate);
                setValue(pmsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
                setValue(pmsg.svcMachMstrPk, (BigDecimal) dsContrListForNSZC0100.get("SVC_MACH_MSTR_PK"));
                setValue(pmsg.dsContrDtlPk, (BigDecimal) dsContrListForNSZC0100.get("DS_CONTR_DTL_PK"));
                setValue(pmsg.xxStartReadFlg, ZYPConstant.FLG_ON_Y);
                setValue(pmsg.contrEffFromDt, (String) dsContrListForNSZC0100.get("CONTR_EFF_FROM_DT"));

            }
            // Detail
            // mod start 2017/08/17 QC#18799
            //setValue(pmsg.A.no(detailCnt).mtrReadDt, (String) dsContrListForNSZC0100.get("MINUS_ONE_DAY"));
            setValue(pmsg.A.no(detailCnt).mtrReadDt, (String) dsContrListForNSZC0100.get("MTR_READ_DT"));
            // mod end 2017/08/17 QC#18799
            setValue(pmsg.A.no(detailCnt).readMtrCnt, (BigDecimal) dsContrListForNSZC0100.get("START_MTR_CNT"));
            setValue(pmsg.A.no(detailCnt).svcPhysMtrPk, (BigDecimal) dsContrListForNSZC0100.get("SVC_PHYS_MTR_PK"));
            setValue(pmsg.A.no(detailCnt).vldMtrFlg, getDefaultFlgY((String) dsContrListForNSZC0100.get("VLD_MTR_FLG")));
            pmsg.A.setValidCount(detailCnt + 1);
            detailCnt++;
            if ((i == dsContrListForNSZC0100List.size() - 1) || (svcMachMstrPk.compareTo(afterSvcMachMstrPk) != 0)) {
                headerCnt++;
                detailCnt = 0;
                pmsgList.add(pmsg);
            }
            // END   2016/03/17 Y.Tsuchimoto [QC#4123,QC#4126 MOD]
        }
    }

    // START 2016/06/22 [QC#10586, MOD]
    private void setParameterForNSZC0520(NSZC052001PMsg pmsg, DS_SUB_CONTR_INTFCTMsg dsSubContr, List<Map<String, Object>> existDsContrListForNSZC0520List) {
        if (pmsg == null) {
            return;
        }
        setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pmsg.slsDt, this.salesDate);
        setValue(pmsg.serNum, dsSubContr.serNum.getValue());
        setValue(pmsg.dsContrNum, dsSubContr.dsContrNum.getValue());
        setValue(pmsg.xxSubContrList.no(0).vndCd, dsSubContr.vndCd.getValue());
        setValue(pmsg.xxSubContrList.no(0).techCd, dsSubContr.techCd.getValue());
        setValue(pmsg.xxSubContrList.no(0).effFromDt, dsSubContr.effFromDt.getValue());
        setValue(pmsg.xxSubContrList.no(0).effThruDt, dsSubContr.effThruDt.getValue());
        setValue(pmsg.xxSubContrList.no(0).contrTrmnFlg, dsSubContr.contrTrmnFlg.getValue());
        setValue(pmsg.xxSubContrList.no(0).basePrcDealAmt, dsSubContr.basePrcDealAmt.getValue());
        setValue(pmsg.xxSubContrList.no(0).adminPrcDealAmt, dsSubContr.adminPrcDealAmt.getValue());
        setValue(pmsg.xxSubContrList.no(0).prepdMaintFlg, dsSubContr.prepdMaintFlg.getValue());
        setValue(pmsg.xxSubContrList.no(0).bwMtrAlwncCnt, dsSubContr.bwMtrAlwncCnt.getValue());
        setValue(pmsg.xxSubContrList.no(0).colorMtrAlwncCnt, dsSubContr.colorMtrAlwncCnt.getValue());
        setValue(pmsg.xxSubContrList.no(0).bwMtrPrcAmtRate, dsSubContr.bwMtrPrcAmtRate.getValue());
        setValue(pmsg.xxSubContrList.no(0).colorMtrPrcAmtRate, dsSubContr.colorMtrPrcAmtRate.getValue());
        setValue(pmsg.xxSubContrList.no(0).splyInclFlg, dsSubContr.splyInclFlg.getValue());
        setValue(pmsg.xxSubContrList.no(0).bllgCycleCd, dsSubContr.bllgCycleCd.getValue());
        setValue(pmsg.xxSubContrList.no(0).dlrFleetFlg, dsSubContr.dlrFleetFlg.getValue());
        setValue(pmsg.xxSubContrList.no(0).dlrFleetNum, dsSubContr.dlrFleetNum.getValue());
        pmsg.xxSubContrList.setValidCount(1);

        int subContrListCnt = pmsg.xxSubContrList.getValidCount();
        for (int i = 0; i < existDsContrListForNSZC0520List.size(); i++) {
            Map<String, Object> existDsContrListForNSZC0520 = existDsContrListForNSZC0520List.get(i);
            if (!isDuplicationDsSubContr(dsSubContr, existDsContrListForNSZC0520)) {
                setValue(pmsg.xxSubContrList.no(subContrListCnt).vndCd, (String) existDsContrListForNSZC0520.get(COL_VND_CD));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).techCd, (String) existDsContrListForNSZC0520.get(COL_TECH_CD));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).effFromDt, (String) existDsContrListForNSZC0520.get(COL_EFF_FROM_DT));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).effThruDt, (String) existDsContrListForNSZC0520.get(COL_EFF_THRU_DT));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).contrTrmnFlg, (String) existDsContrListForNSZC0520.get(COL_CONTR_TRMN_FLG));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).basePrcDealAmt, (BigDecimal) existDsContrListForNSZC0520.get(COL_BASE_PRC_DEAL_AMT));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).adminPrcDealAmt, (BigDecimal) existDsContrListForNSZC0520.get(COL_ADMIN_PRC_DEAL_AMT));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).prepdMaintFlg, (String) existDsContrListForNSZC0520.get(COL_PREPD_MAINT_FLG));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).bwMtrAlwncCnt, (BigDecimal) existDsContrListForNSZC0520.get(COL_BW_MTR_ALWNC_CNT));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).colorMtrAlwncCnt, (BigDecimal) existDsContrListForNSZC0520.get(COL_COLOR_MTR_ALWNC_CNT));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).bwMtrPrcAmtRate, (BigDecimal) existDsContrListForNSZC0520.get(COL_BW_MTR_PRC_AMT_RATE));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).colorMtrPrcAmtRate, (BigDecimal) existDsContrListForNSZC0520.get(COL_COLOR_MTR_PRC_AMT_RATE));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).splyInclFlg, (String) existDsContrListForNSZC0520.get(COL_SPLY_INCL_FLG));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).bllgCycleCd, (String) existDsContrListForNSZC0520.get(COL_BLLG_CYCLE_CD));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).dlrFleetFlg, (String) existDsContrListForNSZC0520.get(COL_DLR_FLEET_FLG));
                setValue(pmsg.xxSubContrList.no(subContrListCnt).dlrFleetNum, (String) existDsContrListForNSZC0520.get(COL_DLR_FLEET_NUM));
                subContrListCnt++;
            }
        }
        pmsg.xxSubContrList.setValidCount(subContrListCnt);
    }
    // END   2016/06/22 [QC#10586, MOD]

    private boolean isDuplicationDsSubContr(DS_SUB_CONTR_INTFCTMsg dsSubContr, Map<String, Object> existDsContrListForNSZC0520) {
        if (dsSubContr == null || existDsContrListForNSZC0520 == null) {
            return false;
        }
        String effFromDt = dsSubContr.effFromDt.getValue();
        String effThruDt = dsSubContr.effThruDt.getValue();

        String existEffFromDt = (String) existDsContrListForNSZC0520.get("EFF_FROM_DT");
        String existEffThruDt = (String) existDsContrListForNSZC0520.get("EFF_THRU_DT");
        if (hasValue(effFromDt) && effFromDt.compareTo(existEffFromDt) >= 0 && hasValue(effFromDt) && effFromDt.compareTo(existEffThruDt) <= 0) {
            return true;
        }
        if (hasValue(effThruDt) && effThruDt.compareTo(existEffFromDt) >= 0 && hasValue(effThruDt) && effThruDt.compareTo(existEffThruDt) <= 0) {
            return true;
        }
        return false;
    }

    private void insertSvcMemo(NSZC046001PMsg nszc046001PMsg, NSZC052001PMsg nszc052001PMsg, DS_SUB_CONTR_INTFCTMsg dsSubContr) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        for (int i = 0; i < nszc052001PMsg.xxSubContrList.getValidCount(); i++) {
            NSZC052001_xxSubContrListPMsg xxSubContr = (NSZC052001_xxSubContrListPMsg) nszc052001PMsg.xxSubContrList.get(i);
            BigDecimal newSvcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);
            setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tmsg.svcMemoPk, newSvcMemoPk);
            setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.ENTRY);
            setValue(tmsg.svcCmntTxt, dsSubContr.svcCmntTxt.getValue());
            setValue(tmsg.dsContrDtlPk, ((NSZC046001_xxContrDtlListPMsg) nszc046001PMsg.xxContrDtlList.get(0)).dsContrDtlPk.getValue());
            setValue(tmsg.lastUpdUsrId, getUserProfileService().getContextUserInfo().getUserId());
            setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
            setValue(tmsg.dsSubContrPk, xxSubContr.dsSubContrPk.getValue());
            EZDTBLAccessor.insert(tmsg);
        }
    }

    private void updateDsContrIntfc(NSZC010001PMsg nszc010001PMsg, DS_CONTR_INTFCTMsg tmsg, boolean isErrorFlag) {
            // UPDATE
        if (isErrorFlag) {
            setValue(tmsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
            // setValue(tmsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
            NSZC010001_xxMsgIdListPMsg xxMsg = (NSZC010001_xxMsgIdListPMsg) nszc010001PMsg.xxMsgIdList.get(0);
            setValue(tmsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(xxMsg.xxMsgId.getValue(), new String[] {xxMsg.xxMsgPrmTxt_0.getValue(), xxMsg.xxMsgPrmTxt_1.getValue(), xxMsg.xxMsgPrmTxt_2.getValue(), xxMsg.xxMsgPrmTxt_3.getValue(),
                    xxMsg.xxMsgPrmTxt_4.getValue() }));
        } else {
            setValue(tmsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
            setValue(tmsg.dsContrProcStsCd, DS_CONTR_PROC_STS.COMPLEATED);
            EZDTBLAccessor.update(tmsg);
        }
    }

    private void updateDsSubContrIntfc(NSZC052001PMsg nszc052001PMsg, boolean isErrorFlag) {

        DS_SUB_CONTR_INTFCTMsgArray list = getDsSubContrIntfcForStatusUpdate(nszc052001PMsg.dsContrNum.getValue(), nszc052001PMsg.serNum.getValue());
        for (int i = 0; i < list.getValidCount(); i++) {
            DS_SUB_CONTR_INTFCTMsg tmsg = (DS_SUB_CONTR_INTFCTMsg) list.get(i);
            // UPDATE
            if (isErrorFlag) {
                setValue(tmsg.subContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                setValue(tmsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
            } else {
                setValue(tmsg.subContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
                setValue(tmsg.dsContrProcStsCd, DS_CONTR_PROC_STS.COMPLEATED);
            }
            EZDTBLAccessor.update(tmsg);
        }
    }

    private DS_SUB_CONTR_INTFCTMsgArray getDsSubContrIntfcForStatusUpdate(String dsContrNum, String serNum) {
        DS_SUB_CONTR_INTFCTMsg param = new DS_SUB_CONTR_INTFCTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrNum01", dsContrNum);
        param.setConditionValue("serNum01", serNum);

        return (DS_SUB_CONTR_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
    }

    private String getYyyyMmDd(String target) {
        if (target != null) {
            if (target.length() > FORMAT.length()) {
                return target.substring(0, FORMAT.length());
            } else {
                return target;
            }
        } else {
            return null;
        }
    }

    private String getPsnCd(String target) {
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

    private List<Map<String, Object>> getDsContrIntfcListForUpdateComplete(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrIntfcListForUpdateComplete", param);
    }

    private BigDecimal getDefaultContactPersonPk(String billToCustCd) {
        if (!hasValue(billToCustCd)) {
            return null;
        }
        // mod start 2016/10/12 CSA Defect#15111
        return null;
//        NWXC021001PMsg pMsg = new NWXC021001PMsg();
//        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(pMsg.billToCustCd, billToCustCd);
//        setValue(pMsg.shipToCustCd, "dummy");
//
//        // Get Default Contact Person
//        NWXC021001 api = new NWXC021001();
//        api.execute(pMsg, ONBATCH_TYPE.BATCH);
//
//        // mod start 2016/09/21 CSA Defect#13686
//        BigDecimal ctacPsnPk = null;
//        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
//            if (CTAC_TP.BILL_TO_CONTACT.equals(pMsg.A.no(i).ctacTpCd.getValue())) {
//                ctacPsnPk = pMsg.A.no(i).ctacPsnPk.getValue();
//                break;
//            }
//        }
//        return ctacPsnPk;
//        // mod end 2016/09/21 CSA Defect#13686
        // mod end 2016/10/12 CSA Defect#15111
    }

    private List<DS_SUB_CONTR_INTFCTMsg> getDsSubContrIntfcList() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);

        return (List<DS_SUB_CONTR_INTFCTMsg>) ssmBatchClient.queryObjectList("getDsSubContrIntfcList", param);
    }
    // START 2016/03/23 Y.Tsuchimoto [QC#5826 ADD]
    private boolean isSetDefaultRuleForMtrReadMethCd(DS_CONTR_INTFCTMsg target) {
        if (CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd.getValue())) {
            return false;
        }
        return isExistsUsageLine(target);
    }

    private boolean isExistsUsageLine(DS_CONTR_INTFCTMsg target) {
        List<Map<String, Object>> list = getUsageListForDsContrIntfc(target);
        if (list != null && list.size() > 0) {
            return true;
        }

        return false;
    }

    private List<Map<String, Object>> getUsageListForDsContrIntfc(DS_CONTR_INTFCTMsg target) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", target.glblCmpyCd.getValue());
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", target.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", target.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", target.dsContrIntfcBatNum.getValue());
        param.put("dsContrIntfcPk", target.dsContrIntfcPk.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUsageListForDsContrIntfc", param);
    }
    // END   2016/03/23 Y.Tsuchimoto [QC#5826 ADD]

    // 2016/01/22 QC#754 Y.Tsuchimoto Add Start
    private void setDefaultRule(Map<String, Object> defRule, DS_CONTR_INTFCTMsg target) {
        if (defRule == null || target == null) {
            return;
        }
        // 1.2.1.2.2.General Defaults
        if (isSetDefaultRuleForMtrReadMethCd(target) && !ZYPCommonFunc.hasValue(target.mtrReadMethCd)) {
            setValue(target.mtrReadMethCd, (String) defRule.get("MTR_READ_METH_CD"));
        }
        // 1.2.1.2.3.General Defaults
        if (!ZYPCommonFunc.hasValue(target.dsContrClsCd)) {
            setValue(target.dsContrClsCd, (String) defRule.get("DS_CONTR_CLS_CD"));
        }
        // 1.2.1.2.4.General Defaults
        if (!ZYPCommonFunc.hasValue(target.printDtlFlg)) {
            setValue(target.printDtlFlg, (String) defRule.get("PRINT_DTL_FLG"));
        }
        // del start 2016/12/27 CSA Defect#16636
//        // 1.2.3.Renewal Defaults
//        if (!ZYPCommonFunc.hasValue(target.contrAutoRnwTpCd)) {
//            if (ZYPCommonFunc.hasValue((String) defRule.get("CONTR_AUTO_RNW_TP_CD"))) {
//                setValue(target.contrAutoRnwTpCd, (String) defRule.get("CONTR_AUTO_RNW_TP_CD"));
//            } else {
//                setValue(target.contrAutoRnwTpCd, CONTR_AUTO_RNW_TP.DO_NOT_RENEW);
//            }
//        }
//        if (!ZYPCommonFunc.hasValue(target.rnwPrcMethCd)) {
//            setValue(target.rnwPrcMethCd, (String) defRule.get("RNW_PRC_METH_CD"));
//        }
//        if (!ZYPCommonFunc.hasValue(target.befEndRnwDaysAot)) {
//            setValue(target.befEndRnwDaysAot, (BigDecimal) defRule.get("BEF_END_RNW_DAYS_AOT"));
//        }
//        if (!ZYPCommonFunc.hasValue(target.rnwPrcUpRatio)) {
//            if (CONTR_INTFC_DTL_TP.BASE.equals(target.contrIntfcDtlTpCd.getValue())) {
//                setValue(target.rnwPrcUpRatio, (BigDecimal) defRule.get("BASE_PRC_UP_RATIO"));
//            } else if (CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd.getValue())) {
//                setValue(target.rnwPrcUpRatio, (BigDecimal) defRule.get("MTR_PRC_UP_RATIO"));
//            }
//        }
        // del end 2016/12/27 CSA Defect#16636
        // del start 2017/09/19 QC#19775
        // 1.2.4.Overage Defaults
//        if (START_READ_VLD_TP.INSERT_READING.equals((String) defRule.get("START_READ_VLD_TP_CD")) && ((BigDecimal) defRule.get("MTR_READ_WIN_DAYS_AOT")) != null) {
//            if (hasValue(target.dsContrIntfcPk)) {
//                BigDecimal startMtrCnt = getStartMtrCnt(target.dsContrIntfcPk.getValue());
//                setValue(target.startMtrCnt, startMtrCnt);
//            }
//        }
        // del end 2017/09/19 QC#19775
        // 1.2.6.Entitlement Default
        if (!ZYPCommonFunc.hasValue(target.mtrEstTpCd)) {
            setValue(target.mtrEstTpCd, (String) defRule.get("MTR_EST_TP_CD"));
        }
        if (!ZYPCommonFunc.hasValue(target.bllgCycleCd)) {
            if (CONTR_INTFC_DTL_TP.BASE.equals(target.contrIntfcDtlTpCd.getValue())) {
                setValue(target.bllgCycleCd, (String) defRule.get("BASE_BLLG_CYCLE_CD"));
            } else if (CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd.getValue())) {
                setValue(target.bllgCycleCd, (String) defRule.get("MTR_BLLG_CYCLE_CD"));
            }
        }
        // Start 2022/03/22 [QC#59683, Add]
        if (!ZYPCommonFunc.hasValue(target.dsInvTgtrTpCd)) {
            setValue(target.dsInvTgtrTpCd, (String) defRule.get("DS_INV_TGTR_TP_CD"));
        }
        // End   2022/03/22 [QC#59683, Add]
        if (!ZYPCommonFunc.hasValue(target.invSeptBaseUsgFlg)) {
            setValue(target.invSeptBaseUsgFlg, (String) defRule.get("INV_SEPT_BASE_USG_FLG"));
        }
        if (!ZYPCommonFunc.hasValue(target.prcAllocByMachQtyFlg)) {
            setValue(target.prcAllocByMachQtyFlg, (String) defRule.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
        }
        // del start 2016/12/27 CSA Defect#16636
//        // 1.2.7.Upliftment Defaults
//        if (!ZYPCommonFunc.hasValue(target.contrUplftTpCd)) {
//            setValue(target.contrUplftTpCd, (String) defRule.get("CONTR_UPLFT_TP_CD"));
//        }
//        if (!ZYPCommonFunc.hasValue(target.uplftPrcMethCd)) {
//            setValue(target.uplftPrcMethCd, (String) defRule.get("UPLFT_PRC_METH_CD"));
//        }
//        if (!ZYPCommonFunc.hasValue(target.uplftPrcUpRatio)) {
//            if (CONTR_INTFC_DTL_TP.BASE.equals(target.contrIntfcDtlTpCd.getValue())) {
//                setValue(target.uplftPrcUpRatio, (BigDecimal) defRule.get("UPLFT_BASE_PRC_UP_RATIO"));
//            } else if (CONTR_INTFC_DTL_TP.USAGE.equals(target.contrIntfcDtlTpCd.getValue())) {
//                setValue(target.uplftPrcUpRatio, (BigDecimal) defRule.get("UPLFT_MTR_PRC_UP_RATIO"));
//            }
//        }
        // del end 2016/12/27 CSA Defect#16636
        // 1.2.8.Billing Thru Defaults
        if (!ZYPCommonFunc.hasValue(target.bllgThruDt)) {
            String billingThruDefaults = getBillingThruDefaults(defRule);
            if (hasValue(billingThruDefaults)) {
                setValue(target.bllgThruDt, billingThruDefaults);
            }
        }
        // mod 2016/04/25 Start QC#3771
        if (ZYPCommonFunc.hasValue(target.bllgThruDt) && ZYPCommonFunc.hasValue(target.contrThruDt)) {
            if (target.bllgThruDt.getValue().equals(target.contrThruDt.getValue())) {
                String endMonth = getEndMonth(target.bllgThruDt.getValue(), defRule);
                if (hasValue(endMonth)) {
                    setValue(target.bllgThruDt, endMonth);
                } else {
                    setValue(target.bllgThruDt, target.bllgThruDt);
                }
            }
        }
        // mod 2016/04/25 End QC#3771
        // 1.2.9.Effectivity Defaults
        // 1.2.9.1.
        if (ZYPCommonFunc.hasValue(target.contrFromDt)) {
            String startMonth = getFirstMonth(target.contrFromDt.getValue(), defRule);
            if (hasValue(startMonth)) {
                setValue(target.contrFromDt, startMonth);
            } else {
                setValue(target.contrFromDt, target.contrFromDt);
            }
        }
        // 1.2.9.2.
        if (ZYPCommonFunc.hasValue(target.contrThruDt)) {
            String endMonth = getEndMonth(target.contrThruDt.getValue(), defRule);
            if (hasValue(endMonth)) {
                setValue(target.contrThruDt, endMonth);
            } else {
                setValue(target.contrThruDt, target.contrThruDt);
            }
        }

        // 1.2.9.3., 1.2.9.4.
        if (!ZYPCommonFunc.hasValue(target.contrFromDt) && !ZYPCommonFunc.hasValue(target.contrThruDt)) {
            String dsContrDtlFromDt = null;
            String dsContrDtlThruDt = null;
            setContrFromDtAndContrThruDt(target, defRule, dsContrDtlFromDt, dsContrDtlThruDt);
            setValue(target.contrFromDt, dsContrDtlFromDt);
            setValue(target.contrThruDt, dsContrDtlThruDt);
        }
    }

    private String getBillingThruDefaults(Map<String, Object> defRule) {
        // 1.2.8.Billing Thru Defaults
        // add start 2017/08/17 QC#18799
        if (defRule == null) {
            return null;
        }
        // add end 2017/08/17 QC#18799
        if (ZYPCommonFunc.hasValue((String) defRule.get("BLLG_THRU_DT"))) {
            return (String) defRule.get("BLLG_THRU_DT");
        } else {
            if (BLLG_THRU_TP.SYSDATE.equals((String) defRule.get("BLLG_THRU_TP_CD"))) {
                return ZYPDateUtil.getSalesDate(glblCmpyCd);
            }
        }
        return null;
    }

    private String getFirstMonth(String contrFromDt, Map<String, Object> defRule) {
        // 1.2.9.1.
        // add start 2017/08/17 QC#18799
        if (defRule == null) {
            return null;
        }
        // add end 2017/08/17 QC#18799
        if (STUB_PREP_FROM_TP.EXTEND_START_MONTH.equals((String) defRule.get("STUB_PREP_FROM_TP_CD"))) {
            if (ZYPCommonFunc.hasValue(contrFromDt)) {
                return getFirstMonth(contrFromDt);
            }
        }
        return null;
    }

    private String getEndMonth(String contrThruDt, Map<String, Object> defRule) {
        // 1.2.9.2.
        // add start 2017/08/17 QC#18799
        if (defRule == null) {
            return null;
        }
        // add end 2017/08/17 QC#18799
        if (STUB_PREP_THRU_TP.EXTEND_END_MONTH.equals((String) defRule.get("STUB_PREP_THRU_TP_CD"))) {
            if (ZYPCommonFunc.hasValue(contrThruDt)) {
                return getEndMonth(contrThruDt);
            }
        }
        return null;
    }

    private void setContrFromDtAndContrThruDt(DS_CONTR_INTFCTMsg target, Map<String, Object> defRule, String contrFromDt, String contrThruDt) {
        // add start 2017/08/17 QC#18799
        if (defRule == null) {
            return;
        }
        // add end 2017/08/17 QC#18799
        if (hasValue(target.dsContrIntfcPk)) {
            Map<String, String> defMap = getSameDsContrDtlList(target.dsContrIntfcPk.getValue());
            // 1.2.9.3.
            if (ZYPConstant.FLG_ON_Y.equals((String) defRule.get("AUTO_EFF_FLEET_FLG")) && !ZYPCommonFunc.hasValue(target.contrFromDt) && !ZYPCommonFunc.hasValue(target.contrThruDt)) {
                if (DS_CONTR_CATG.FLEET.equals(target.dsContrCatgCd.getValue()) && DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(target.dsContrIntfcActCd.getValue())) {
                    contrFromDt = (String) defMap.get("CONTR_EFF_FROM_DT");
                    contrThruDt = (String) defMap.get("CONTR_EFF_THRU_DT");
                }
            }
            // 1.2.9.4.
            if (ZYPConstant.FLG_ON_Y.equals((String) defRule.get("AUTO_EFF_AGGR_FLG")) && !ZYPCommonFunc.hasValue(target.contrFromDt) && !ZYPCommonFunc.hasValue(target.contrThruDt)) {
                if (DS_CONTR_CATG.AGGREGATE.equals(target.dsContrCatgCd.getValue()) && DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(target.dsContrIntfcActCd.getValue())) {
                    contrFromDt = (String) defMap.get("CONTR_EFF_FROM_DT");
                    contrThruDt = (String) defMap.get("CONTR_EFF_THRU_DT");
                }
            }
        }
    }

    // del start 2017/09/19 QC#19775
//    private BigDecimal getStartMtrCnt(BigDecimal dsContrIntfcPk) {
//        Map<String, Object> map = getStartMtrCntData(dsContrIntfcPk);
//        if (map == null) {
//            return null;
//        }
//        return (BigDecimal) map.get("START_MTR_CNT");
//    }
//
//    private Map<String, Object> getStartMtrCntData(BigDecimal dsContrIntfcPk) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("dsContrIntfcPk", dsContrIntfcPk);
//
//        return (Map<String, Object>) this.ssmBatchClient.queryObject("getStartMtrCntData", param);
//    }
    // del end 2017/09/19 QC#19775

    private String getFirstMonth(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        Calendar firstCal = Calendar.getInstance();
        firstCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

        int firstDay = firstCal.getActualMinimum(Calendar.DATE);
        firstCal.set(Calendar.DATE, firstDay);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(firstCal.getTimeInMillis());
    }

    private String getEndMonth(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

        int endDay = endCal.getActualMaximum(Calendar.DATE);
        endCal.set(Calendar.DATE, endDay);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(endCal.getTimeInMillis());
    }

    private Map<String, String> getSameDsContrDtlList(BigDecimal dsContrIntfcPk) {

        List<Map<String, Object>> list = getSameDsContrDtlListData(dsContrIntfcPk);
        Map<String, String> rtnMap = new HashMap<String, String>();
        boolean setContrFromDtFlag = true;
        boolean setContrThruDtFlag = true;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> targetMap = list.get(i);
            String contrFromDt = (String) rtnMap.get("CONTR_EFF_FROM_DT");
            String contrThruDt = (String) rtnMap.get("CONTR_EFF_THRU_DT");
            String getContrFromDt = (String) targetMap.get("CONTR_EFF_FROM_DT");
            String getContrThruDt = (String) targetMap.get("CONTR_EFF_THRU_DT");
            if (setContrFromDtFlag) {
                if (!ZYPCommonFunc.hasValue(contrFromDt)) {
                    rtnMap.put("CONTR_EFF_FROM_DT", getContrFromDt);
                } else {
                    if (!contrFromDt.equals(getContrFromDt)) {
                        setContrFromDtFlag = false;
                    }
                }
            }
            if (setContrThruDtFlag) {
                if (!ZYPCommonFunc.hasValue(contrThruDt)) {
                    rtnMap.put("CONTR_EFF_THRU_DT", getContrThruDt);
                } else {
                    if (!contrThruDt.equals(getContrThruDt)) {
                        setContrThruDtFlag = false;
                    }
                }
            }
        }
        if (!setContrFromDtFlag) {
            rtnMap.put("CONTR_EFF_FROM_DT", null);
        }
        if (!setContrThruDtFlag) {
            rtnMap.put("CONTR_EFF_THRU_DT", null);
        }
        return rtnMap;
    }

    private List<Map<String, Object>> getSameDsContrDtlListData(BigDecimal dsContrIntfcPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcPk", dsContrIntfcPk);
        param.put("active", DS_CONTR_DTL_STS.ACTIVE);

        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSameDsContrDtlList", param);
    }
    // 2016/01/22 QC#754 Y.Tsuchimoto Add End

    // 2016/03/15 QC#867 Y.Tsuchimoto Add Start
    private void setParameterForNSZC057001(DS_CONTR_INTFCTMsg tmsg, NSZC046001PMsg nszc046001PMsg, NSZC057001PMsg nszc057001PMsg) {
        if (tmsg == null || nszc046001PMsg == null || nszc057001PMsg == null) {
            return;
        }
        setValue(nszc057001PMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(nszc057001PMsg.slsDt, this.salesDate);
        String dsContrNum = tmsg.dsContrNum.getValue();
        if (!hasValue(dsContrNum)) {
            dsContrNum = nszc046001PMsg.dsContrNum.getValue();
            if (!hasValue(dsContrNum)) {
                return;
            }
        }
        setValue(nszc057001PMsg.dsContrNum, dsContrNum);
    }
    // 2016/03/15 QC#867 Y.Tsuchimoto Add End

    private String getDefaultFlgN(String flg) {
        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    private String getDefaultFlgY(String flg) {
        if (ZYPConstant.FLG_OFF_N.equals(flg)) {
            return ZYPConstant.FLG_OFF_N;
        } else {
            return ZYPConstant.FLG_ON_Y;
        }
    }

    // START 2016/03/24 Y.Tsuchimoto [QC#5662 ADD]
    private void updateDsContrIntfcToError(DS_CONTR_INTFCTMsg tmsg) {
        // DS_CONTR_INTFC
        List<Map<String, Object>> dsContrIntfcQaDsContrIntfcList = getDsContrIntfcQaDsContrIntfc(tmsg);
        for (int j = 0; j < dsContrIntfcQaDsContrIntfcList.size(); j++) {
            Map<String, Object> targetIntfc = dsContrIntfcQaDsContrIntfcList.get(j);
            DS_CONTR_INTFCTMsg target = getDsContrIntfcQaTarget((BigDecimal) targetIntfc.get(COL_DS_CONTR_INTFC_PK));
            if (!DS_CONTR_PROC_STS.ERROR.equals(target.dsContrProcStsCd.getValue())) {
                // Update
                setValue(target.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                EZDTBLAccessor.update(target);
            }
        }
        // DS_ACTL_CNT_INTFC
        List<Map<String, Object>> dsContrIntfcQaDsActlCntIntfcList = getDsContrIntfcQaDsActlCntIntfc(tmsg);
        for (int j = 0; j < dsContrIntfcQaDsActlCntIntfcList.size(); j++) {
            Map<String, Object> targetIntfc = dsContrIntfcQaDsActlCntIntfcList.get(j);
            DS_CONTR_INTFCTMsg target = getDsContrIntfcQaTarget((BigDecimal) targetIntfc.get(COL_DS_CONTR_INTFC_PK));
            if (!DS_CONTR_PROC_STS.ERROR.equals(target.dsContrProcStsCd.getValue())) {
                // Update
                setValue(target.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                EZDTBLAccessor.update(target);
            }
        }
        // DS_XS_COPY_INTFC
        List<Map<String, Object>> dsContrIntfcQaDsXsCoppIntfcList = getDsContrIntfcQaDsXsCoppIntfc(tmsg);
        for (int j = 0; j < dsContrIntfcQaDsXsCoppIntfcList.size(); j++) {
            Map<String, Object> targetIntfc = dsContrIntfcQaDsXsCoppIntfcList.get(j);
            DS_CONTR_INTFCTMsg target = getDsContrIntfcQaTarget((BigDecimal) targetIntfc.get(COL_DS_CONTR_INTFC_PK));
            if (!DS_CONTR_PROC_STS.ERROR.equals(target.dsContrProcStsCd.getValue())) {
                // Update
                setValue(target.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                EZDTBLAccessor.update(target);
            }
        }
        // DS_ADDL_CHRG_INTFC
        List<Map<String, Object>> dsContrIntfcQaDsAddlChrgIntfcList = getDsContrIntfcQaDsAddlChrgIntfc(tmsg);
        for (int j = 0; j < dsContrIntfcQaDsAddlChrgIntfcList.size(); j++) {
            Map<String, Object> targetIntfc = dsContrIntfcQaDsAddlChrgIntfcList.get(j);
            DS_CONTR_INTFCTMsg target = getDsContrIntfcQaTarget((BigDecimal) targetIntfc.get(COL_DS_CONTR_INTFC_PK));
            if (!DS_CONTR_PROC_STS.ERROR.equals(target.dsContrProcStsCd.getValue())) {
                // Update
                setValue(target.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                EZDTBLAccessor.update(target);
            }
        }
        // PRC_ALLOC_INTFC
        List<Map<String, Object>> dsContrIntfcQaPrcAllocIntfcList = getDsContrIntfcQaPrcAllocIntfc(tmsg);
        for (int j = 0; j < dsContrIntfcQaPrcAllocIntfcList.size(); j++) {
            Map<String, Object> targetIntfc = dsContrIntfcQaPrcAllocIntfcList.get(j);
            DS_CONTR_INTFCTMsg target = getDsContrIntfcQaTarget((BigDecimal) targetIntfc.get(COL_DS_CONTR_INTFC_PK));
            if (!DS_CONTR_PROC_STS.ERROR.equals(target.dsContrProcStsCd.getValue())) {
                // Update
                setValue(target.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                EZDTBLAccessor.update(target);
            }
        }
    }

    private String getIntfcErrMsgTxt(String id, String message) {
        if (!hasValue(id) && !hasValue(message)) {
            return null;
        }
        if (!hasValue(id) && hasValue(message)) {
            return message;
        }
        if (hasValue(id) && !hasValue(message)) {
            return S21MessageFunc.clspGetMessage(id);
        }
        return id + " " + message;
    }
    // END   2016/03/24 Y.Tsuchimoto [QC#5662 ADD]

    // START 2016/05/24 [QC#4061, MOD]
    private boolean isManContrOvrd(DS_CONTR_INTFCTMsg tmsg) {
        if (ZYPConstant.FLG_ON_Y.equals(tmsg.manContrOvrdFlg.getValue())) {
            return true;
        }
        return false;
    }
    // END   2016/05/24 [QC#4061, MOD]

    // START   2016/05/24 [QC#447, ADD]
    private void setSlaInfoBean(SlaInfoBean slaBean, DS_CONTR_INTFCTMsg tmsg, BigDecimal dsContrDtlPk) {
        if (slaBean == null || tmsg == null) {
            return;
        }
        slaBean.setGlblCmpyCd(tmsg.glblCmpyCd.getValue());
        slaBean.setDsContrDtlPk(dsContrDtlPk);
        if (this.salesDate.compareTo(tmsg.contrFromDt.getValue()) >= 0) {
            slaBean.setSlaDt(this.salesDate);
        } else {
            slaBean.setSlaDt(tmsg.contrFromDt.getValue());
        }
    }

    private void setTAndCMsg(NSZC046001_xxSvcTermCondListPMsg tAndCMsg, String varCharConstKey, String slaMinutes) {
        String svcTermCondAttrbPkKey = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, this.glblCmpyCd);
        BigDecimal svcTermCondAttrbPk = getSvcTermCondAttrbPk(svcTermCondAttrbPkKey);
        if (svcTermCondAttrbPk != null) {
            setValue(tAndCMsg.svcTermCondAttrbPk, svcTermCondAttrbPk);
            if (Arrays.asList(SLA_TERM_COND_VARCHAR_CONST_KEY_SET_VALUE).contains(varCharConstKey)) {
                setValue(tAndCMsg.svcTermAttrbMapValCd, slaMinutes);
            }
        }
    }

    private BigDecimal getSvcTermCondAttrbPk(String svcTermCondAttrbPkKey) {
        if (!hasValue(svcTermCondAttrbPkKey)) {
            return null;
        }
        SVC_TERM_COND_ATTRBTMsg param = new SVC_TERM_COND_ATTRBTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("svcTermCondAttrbNm01", svcTermCondAttrbPkKey);
        SVC_TERM_COND_ATTRBTMsgArray list = (SVC_TERM_COND_ATTRBTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return ((SVC_TERM_COND_ATTRBTMsg) list.get(0)).svcTermCondAttrbPk.getValue();
        }
        return null;
    }

    // START 2016/11/01 T.Kanasaka [QC#15136, MOD]
    private void createTermsAndConditions(NSZC046001PMsg param, NSZC046001_xxSvcTermCondListPMsg tAndCMsg, BigDecimal dsContrDtlPk) {

        BigDecimal tAndCPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ);

        SVC_TERM_CONDTMsg inParam = new SVC_TERM_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermCondPk, tAndCPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrPk, param.dsContrPk);
        ZYPEZDItemValueSetter.setValue(inParam.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermCondAttrbPk, tAndCMsg.svcTermCondAttrbPk);
        ZYPEZDItemValueSetter.setValue(inParam.svcTermAttrbMapValCd, tAndCMsg.svcTermAttrbMapValCd);

        S21ApiTBLAccessor.insert(inParam);
    }

    private void createTermsAndConditionsForSla(NSZC046001PMsg param, NSZC046001_xxSvcTermCondListPMsg tAndCMsg, BigDecimal dsContrDtlPk) {
        SVC_TERM_CONDTMsg inParam = new SVC_TERM_CONDTMsg();
        inParam.setSQLID("001");
        inParam.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inParam.setConditionValue("dsContrPk01", param.dsContrPk.getValue());
        inParam.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inParam.setConditionValue("svcTermCondAttrbPk01", tAndCMsg.svcTermCondAttrbPk.getValue());

        SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inParam);
        if (svcTermCondTMsgArray != null && svcTermCondTMsgArray.getValidCount() > 0) {
            inParam = svcTermCondTMsgArray.no(0);
            ZYPEZDItemValueSetter.setValue(inParam.svcTermAttrbMapValCd, tAndCMsg.svcTermAttrbMapValCd);
            S21ApiTBLAccessor.update(inParam);
        } else {
            createTermsAndConditions(param, tAndCMsg, dsContrDtlPk);
        }
    }
    // END 2016/11/01 T.Kanasaka [QC#15136, MOD]
    // END   2016/05/24 [QC#447, ADD]

    // add start 2016/06/06 CSA Defect#1523, 4624
    private void callContrTrkAPI(BigDecimal dsContrPk) {
        String userId = this.getClass().getSimpleName();
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code, dsContrPk, userId, this.salesDate, null, null, ONBATCH_TYPE.BATCH)) {
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
            tMsg = (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                String msgTxt = S21MessageFunc.clspGetMessage(NSXC001001ContractTracking.ERR_MSG_ID);
                S21InfoLogOutput.println(ZYPCommonFunc.concatString(msgTxt, " :Contract#: ", tMsg.dsContrNum.getValue()));
            }
        }
    }
    // add end 2016/06/06 CSA Defect#1523, 4624

    // add start 2016/10/17 CSA Defect#14868
    private List<Map<String, Object>> getOldMachInfoForIntfc(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("contrIntfcSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("contrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.BASE);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOldMachInfoForIntfc", param);
    }

    // Del Start 2018/06/01 QC#26379
//    private void terminateAccForExchange(Map<String, Object> oldMachInfo) {
//        BigDecimal dsContrIntfcPk = (BigDecimal) oldMachInfo.get("DS_CONTR_INTFC_PK");
//        BigDecimal dsContrDtlPk = (BigDecimal) oldMachInfo.get("OLD_DS_CONTR_DTL_PK");
//        DS_CONTR_DTLTMsg dtlTMsg = getDsContrDtl(dsContrDtlPk);
//        DS_CONTRTMsg hdrTMsg = getDsContr(dtlTMsg.dsContrPk.getValue());
//        DS_CONTR_INTFCTMsg intfcTMsg = getDsContrIntfc(dsContrIntfcPk);
//        String cloDt = ZYPDateUtil.addDays(intfcTMsg.contrFromDt.getValue(), -1);
//
//        NSZC046001PMsg nszc046001PMsg = new NSZC046001PMsg();
//        EZDMsg.copy(hdrTMsg, null, nszc046001PMsg, null);
//        EZDMsg.copy(dtlTMsg, null, nszc046001PMsg.xxContrDtlList.no(0), null);
//        nszc046001PMsg.xxContrDtlList.setValidCount(1);
//
//        setValue(nszc046001PMsg.slsDt, this.salesDate);
//        setValue(nszc046001PMsg.xxModeCd, DS_CONTR_INTFC_ACT_CD_TERMINATE);
//        setValue(nszc046001PMsg.xxContrDtlList.no(0).contrCloDt, cloDt);
//
//        // Call Service Contract Data Update API
//        NSZC046001 apiNSZC046001 = new NSZC046001();
//        apiNSZC046001.execute(nszc046001PMsg, ONBATCH_TYPE.BATCH);
//    }
    // Del End 2018/06/01 QC#26379

    private DS_CONTR_INTFCTMsg getDsContrIntfc(BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg tMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private DS_CONTRTMsg getDsContr(BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtl(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tMsg);
    }

    private List<SVC_TERM_CONDTMsg> getTermConditionForOldMach(Map<String, Object> oldMachInfo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", (BigDecimal) oldMachInfo.get("OLD_DS_CONTR_DTL_PK"));
        param.put("slsDt", this.salesDate);
        return (List<SVC_TERM_CONDTMsg>) this.ssmBatchClient.queryObjectList("getTermConditionForOldMach", param);
    }

    private void setParameterForTermConditionForOldMach(NSZC046001PMsg nszc046001PMsg, List<SVC_TERM_CONDTMsg> svcTermCondList, Map<String, Object> oldMachInfo) {
        if (nszc046001PMsg == null) {
            return;
        }

        String dsContrIntfcActCd = (String) oldMachInfo.get("DS_CONTR_INTFC_ACT_CD");
        BigDecimal svcMachMstrPk = (BigDecimal) oldMachInfo.get("SVC_MACH_MSTR_PK");
        int termCnt = nszc046001PMsg.xxSvcTermCondList.getValidCount();
        for (SVC_TERM_CONDTMsg svcTermCond : svcTermCondList) {
            setValue(nszc046001PMsg.xxSvcTermCondList.no(termCnt).xxModeCd, getXxModeCdForLevel2(dsContrIntfcActCd));
            setValue(nszc046001PMsg.xxSvcTermCondList.no(termCnt).svcTermCondPk, svcTermCond.svcTermCondPk);
            setValue(nszc046001PMsg.xxSvcTermCondList.no(termCnt).dsContrPk, svcTermCond.dsContrPk);
            setValue(nszc046001PMsg.xxSvcTermCondList.no(termCnt).svcTermCondAttrbPk, svcTermCond.svcTermCondAttrbPk);
            setValue(nszc046001PMsg.xxSvcTermCondList.no(termCnt).svcTermAttrbMapValCd, svcTermCond.svcTermAttrbMapValCd);
            setValue(nszc046001PMsg.xxSvcTermCondList.no(termCnt).svcMachMstrPk, svcMachMstrPk);
            termCnt++;
        }
        nszc046001PMsg.xxSvcTermCondList.setValidCount(termCnt);
    }

    private void setParameterForContractPriceAllocationForOldMach(NSZC046001PMsg pmsg, List<Map<String, Object>> oldMachInfoList) {
        BigDecimal oldDsContrDtlPk;
        String dsContrIntfcActCd;
        BigDecimal svcMachMstrPk;
        for (Map<String, Object> oldMachInfo : oldMachInfoList) {
            oldDsContrDtlPk = (BigDecimal) oldMachInfo.get("OLD_DS_CONTR_DTL_PK");
            dsContrIntfcActCd = (String) oldMachInfo.get("DS_CONTR_INTFC_ACT_CD");
            svcMachMstrPk = (BigDecimal) oldMachInfo.get("SVC_MACH_MSTR_PK");
            // Branch Allocation
            DS_CONTR_BR_ALLOCTMsgArray brAllocList = getDsContrBrAlloc(this.glblCmpyCd, oldDsContrDtlPk);
            int brCnt = pmsg.xxDsContrBrAllocList.getValidCount();
            for (int i = 0; i < brAllocList.getValidCount(); i++) {
                setValue(pmsg.xxDsContrBrAllocList.no(brCnt).xxModeCd, getXxModeCdForLevel2(dsContrIntfcActCd));
                setValue(pmsg.xxDsContrBrAllocList.no(brCnt).dsContrPk, brAllocList.no(i).dsContrPk);
                setValue(pmsg.xxDsContrBrAllocList.no(brCnt).svcInvChrgTpCd, brAllocList.no(i).svcInvChrgTpCd);
                setValue(pmsg.xxDsContrBrAllocList.no(brCnt).coaBrCd, brAllocList.no(i).coaBrCd);
                setValue(pmsg.xxDsContrBrAllocList.no(brCnt).prcAllocRate, brAllocList.no(i).prcAllocRate);
                setValue(pmsg.xxDsContrBrAllocList.no(brCnt).svcMachMstrPk, svcMachMstrPk);
                brCnt++;
            }
            pmsg.xxDsContrBrAllocList.setValidCount(brCnt);

            // Segment Allocation
            DS_CONTR_PRC_ALLOCTMsgArray prcAllocList = getDsContrPrcAlloc(this.glblCmpyCd, oldDsContrDtlPk);
            int segCnt = pmsg.xxDsContrSegAllocList.getValidCount();
            for (int j = 0; j < prcAllocList.getValidCount(); j++) {
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).xxModeCd, getXxModeCdForLevel2(dsContrIntfcActCd));
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).dsContrPk, prcAllocList.no(j).dsContrPk);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).svcInvChrgTpCd, prcAllocList.no(j).svcInvChrgTpCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaCmpyCd, prcAllocList.no(j).coaCmpyCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaAfflCd, prcAllocList.no(j).coaAfflCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaBrCd, prcAllocList.no(j).coaBrCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaChCd, prcAllocList.no(j).coaChCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaCcCd, prcAllocList.no(j).coaCcCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaAcctCd, prcAllocList.no(j).coaAcctCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaProdCd, prcAllocList.no(j).coaProdCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaProjCd, prcAllocList.no(j).coaProjCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).coaExtnCd, prcAllocList.no(j).coaExtnCd);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).prcAllocRate, prcAllocList.no(j).prcAllocRate);
                setValue(pmsg.xxDsContrSegAllocList.no(segCnt).svcMachMstrPk, svcMachMstrPk);
                segCnt++;
            }
            pmsg.xxDsContrSegAllocList.setValidCount(segCnt);
        }
    }

    private DS_CONTR_BR_ALLOCTMsgArray getDsContrBrAlloc(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BR_ALLOCTMsg inMsg = new DS_CONTR_BR_ALLOCTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private DS_CONTR_PRC_ALLOCTMsgArray getDsContrPrcAlloc(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_ALLOCTMsg inMsg = new DS_CONTR_PRC_ALLOCTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_PRC_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private void createSubContr(List<Map<String, Object>> oldMachInfoList) {
        BigDecimal oldDsContrDtlPk;
        String serNum;
        String dsContrNum;
        for (Map<String, Object> oldMachInfo : oldMachInfoList) {
            if (!validCreateSubContr(oldMachInfo)) {
                continue;
            }
            oldDsContrDtlPk = (BigDecimal) oldMachInfo.get("OLD_DS_CONTR_DTL_PK");
            serNum = (String) oldMachInfo.get("SER_NUM");
            dsContrNum = (String) oldMachInfo.get("DS_CONTR_NUM");
            DS_SUB_CONTRTMsgArray dsSubContrList = getDsSubContr(this.glblCmpyCd, oldDsContrDtlPk);
            if (dsSubContrList.getValidCount() == 0) {
                continue;
            }

            NSZC052001PMsg pmsg = new NSZC052001PMsg();
            setValue(pmsg.glblCmpyCd, this.glblCmpyCd);
            setValue(pmsg.slsDt, this.salesDate);
            setValue(pmsg.serNum, serNum);
            setValue(pmsg.dsContrNum, dsContrNum);

            DS_SUB_CONTRTMsg dsSubContr;
            int i = 0;
            for (; i < dsSubContrList.getValidCount(); i++) {
                dsSubContr = dsSubContrList.no(i);
                setValue(pmsg.xxSubContrList.no(i).vndCd, dsSubContr.vndCd);
                setValue(pmsg.xxSubContrList.no(i).techCd, dsSubContr.techTocCd);
                setValue(pmsg.xxSubContrList.no(i).effFromDt, dsSubContr.effFromDt);
                setValue(pmsg.xxSubContrList.no(i).effThruDt, dsSubContr.effThruDt);
                setValue(pmsg.xxSubContrList.no(i).contrTrmnFlg, dsSubContr.contrTrmnFlg);
                setValue(pmsg.xxSubContrList.no(i).basePrcDealAmt, dsSubContr.basePrcDealAmt);
                setValue(pmsg.xxSubContrList.no(i).adminPrcDealAmt, dsSubContr.adminPrcDealAmt);
                setValue(pmsg.xxSubContrList.no(i).prepdMaintFlg, dsSubContr.prepdMaintFlg);
                setValue(pmsg.xxSubContrList.no(i).bwMtrAlwncCnt, dsSubContr.bwMtrAlwncCnt);
                setValue(pmsg.xxSubContrList.no(i).colorMtrAlwncCnt, dsSubContr.colorMtrAlwncCnt);
                setValue(pmsg.xxSubContrList.no(i).bwMtrPrcAmtRate, dsSubContr.bwMtrPrcAmtRate);
                setValue(pmsg.xxSubContrList.no(i).colorMtrPrcAmtRate, dsSubContr.colorMtrPrcAmtRate);
                setValue(pmsg.xxSubContrList.no(i).splyInclFlg, dsSubContr.splyInclFlg);
                setValue(pmsg.xxSubContrList.no(i).bllgCycleCd, dsSubContr.bllgCycleCd);
                setValue(pmsg.xxSubContrList.no(i).dlrFleetFlg, dsSubContr.dlrFleetFlg);
                setValue(pmsg.xxSubContrList.no(i).dlrFleetNum, dsSubContr.dlrFleetNum);
            }
            pmsg.xxSubContrList.setValidCount(i);

            NSZC052001 api = new NSZC052001();
            api.execute(pmsg, ONBATCH_TYPE.BATCH);
        }
    }

    private boolean validCreateSubContr(Map<String, Object> oldMachInfo) {
        if (!DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals((String) oldMachInfo.get("DS_CONTR_INTFC_ACT_CD"))) {
            return false;
        }

        if (!hasValue((BigDecimal) oldMachInfo.get("OLD_DS_CONTR_DTL_PK"))) {
            return false;
        }

        BigDecimal dsContrDtlPk = getDsContrDtlPk(this.glblCmpyCd, (BigDecimal) oldMachInfo.get("DS_CONTR_INTFC_PK"));
        if (!hasValue(dsContrDtlPk)) {
            return false;
        }
        return true;
    }

    private BigDecimal getDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrIntfcPk", dsContrIntfcPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("getDsContrDtlPk", param);
    }

    private DS_SUB_CONTRTMsgArray getDsSubContr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_SUB_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // add end 2016/10/17 CSA Defect#14868

    private String getSvcPgmMdseCdForFleet(NSZC046001PMsg nszc046001PMsg) {
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg xxContrDtlList = (NSZC046001_xxContrDtlListPMsg) nszc046001PMsg.xxContrDtlList.get(i);
            if (DS_CONTR_DTL_TP.FLEET.equals(xxContrDtlList.dsContrDtlTpCd.getValue())) {
                return xxContrDtlList.svcPgmMdseCd.getValue();
            }
        }

        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlForFleet(nszc046001PMsg.dsContrPk.getValue());
        if (dsContrDtlTMsg != null) {
            return dsContrDtlTMsg.svcPgmMdseCd.getValue();
        }

        return null;
    }

    private DS_CONTR_DTLTMsg getDsContrDtlForFleet(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
        param.setSQLID("006");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);

        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (dsContrDtlTMsgArray != null && dsContrDtlTMsgArray.getValidCount() > 0) {
            return dsContrDtlTMsgArray.no(0);
        }
        return null;
    }

    // START 2016/11/09 T.Tomita [QC#15453, ADD]
    private String getMinContrFromDt(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        return (String) this.ssmBatchClient.queryObject("getMinContrFromDt", param);
    }

    private String getMaxContrThruDt(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        return (String) this.ssmBatchClient.queryObject("getMaxContrThruDt", param);
    }
    // END 2016/11/09 T.Tomita [QC#15453, ADD]

    // add start 2016/12/01 QC#16205
    private boolean overContrThruDtOfContr(DS_CONTR_INTFCTMsg tmsg, Map<String, Object> dsContrHeader) {
        if (!CONTR_INTFC_SRC_TP.IB_UPDATE.equals(tmsg.contrIntfcSrcTpCd.getValue())) {
            return false;
        }
        if (!DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(tmsg.dsContrIntfcActCd.getValue())) {
            return false;
        }
        String contrThruDtByContr = (String) dsContrHeader.get("CONTR_VRSN_EFF_THRU_DT");
        String contrThruDtByIntfc = getMaxContrThruDt(tmsg);
        if (hasValue(contrThruDtByContr) && hasValue(contrThruDtByIntfc) && ZYPDateUtil.compare(contrThruDtByContr, contrThruDtByIntfc) < 0) {
            return true;
        }
        return false;
    }

    private void setDsContrParamForUpdate(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg tmsg, Map<String, Object> dsContrHeader) {
        setDsContrHeaderParam(nszc046001PMsg, tmsg, dsContrHeader);
        setValue(nszc046001PMsg.xxModeCd, DS_CONTR_INTFC_ACT_CD_UPDATE);
        setValue(nszc046001PMsg.contrVrsnEffFromDt, (String) dsContrHeader.get("CONTR_VRSN_EFF_FROM_DT"));
        setValue(nszc046001PMsg.contrVrsnEffThruDt, getMaxContrThruDt(tmsg));

        DS_CONTR_DTLTMsgArray dsContrDtlList = getDsContrDtlByDsContrPk((BigDecimal) dsContrHeader.get("DS_CONTR_PK"));
        for (int i = 0; i < dsContrDtlList.getValidCount(); i++) {
            EZDMsg.copy(dsContrDtlList.no(i), null, nszc046001PMsg.xxContrDtlList.no(i), null);
        }
        nszc046001PMsg.xxContrDtlList.setValidCount(dsContrDtlList.getValidCount());
    }

    private DS_CONTR_DTLTMsgArray getDsContrDtlByDsContrPk(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);

        return (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(param);
    }

    private boolean overContrThruDtOfMach(Map<String, Object> targetDetail) {
        String accContrThruDt = (String) targetDetail.get("CONTR_THRU_DT");
        String machContrThruDt = getMachContrThruDtByIntfc(targetDetail);
        if (!hasValue(machContrThruDt) && hasValue((BigDecimal) targetDetail.get("DS_CONTR_PK"))) {
            machContrThruDt = getMachContrThruDtByContr(targetDetail);
        }
        if (hasValue(accContrThruDt) && hasValue(machContrThruDt) && ZYPDateUtil.compare(accContrThruDt, machContrThruDt) <= 0) {
            return true;
        }
        return false;
    }

    private String getMachContrThruDtByIntfc(Map<String, Object> targetDetail) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", (BigDecimal) targetDetail.get("SVC_MACH_MSTR_PK"));
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("dsContrSrcRefNum", (String) targetDetail.get("DS_CONTR_SRC_REF_NUM"));
        param.put("dsContrSrcTpCd", (String) targetDetail.get("CONTR_INTFC_SRC_TP_CD"));
        param.put("dsContrIntfcBatNum", (String) targetDetail.get("DS_CONTR_INTFC_BAT_NUM"));
        return (String) this.ssmBatchClient.queryObject("getMachContrThruDtByIntfc", param);
    }

    private String getMachContrThruDtByContr(Map<String, Object> targetDetail) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", (BigDecimal) targetDetail.get("SVC_MACH_MSTR_PK"));
        param.put("dsContrPk", (BigDecimal) targetDetail.get("DS_CONTR_PK"));
        return (String) this.ssmBatchClient.queryObject("getMachContrThruDtByContr", param);
    }
    // add end 2016/12/01 QC#16205

    // add start 2017/08/17 QC#18799
    // mod start 2017/09/19 QC#19775
    private Map<String, Object> getDsContrIntfcDefRuleForMtrRead(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("salesDate", this.salesDate);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getDsContrIntfcDefRuleForMtrRead", param);
    }
    // mod end 2017/09/19 QC#19775

    private List<Map<String, Object>> getDsContrListForNSZC0100IstlCall(DS_CONTR_INTFCTMsg tmsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", tmsg.dsContrSrcRefNum.getValue());
        param.put("dsContrSrcTpCd", tmsg.contrIntfcSrcTpCd.getValue());
        param.put("usage", CONTR_INTFC_DTL_TP.USAGE);
        param.put("dsContrIntfcBatNum", tmsg.dsContrIntfcBatNum.getValue());
        param.put("maxDate", MAX_DATE);
        param.put("format", FORMAT);
        param.put("fsrTpCd", FSR_TP.INSTALL_CALL);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrListForNSZC0100IstlCall", param);
    }
    // add end 2017/08/17 QC#18799

    // add start 2017/10/10 QC#21617
    private void createWaitIstlContr(NSZC046001PMsg nszc046001PMsg) {
        if (!DS_CONTR_CATG.WARRANTY.equals(nszc046001PMsg.dsContrCatgCd.getValue())) {
            return;
        }

        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            NSZC046001_xxContrDtlListPMsg xxContrDtl = (NSZC046001_xxContrDtlListPMsg) nszc046001PMsg.xxContrDtlList.get(i);
            BigDecimal dsContrDtlPk = xxContrDtl.dsContrDtlPk.getValue();
            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(dsContrDtlPk);
            if (dsContrDtlTMsg == null) {
                continue;
            }
            if (DS_CONTR_DTL_STS.ACTIVE.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue()) || DS_CONTR_DTL_STS.SIGNED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                continue;
            }
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(dsContrDtlTMsg.svcMachMstrPk.getValue());
            if (dsContrDtlTMsg == null) {
                continue;
            }
            if (!SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(svcMachMstrTMsg.svcMachMstrStsCd.getValue())) {
                continue;
            }

            WAIT_ISTL_CONTRTMsg waitIstlContrTMsg = getWaitIstlContr(dsContrDtlPk);
            if (waitIstlContrTMsg != null) {
                continue;
            }
            insertWaitIstlContr(dsContrDtlPk);
        }
    }

    private WAIT_ISTL_CONTRTMsg getWaitIstlContr(BigDecimal dsContrDtlPk) {
        WAIT_ISTL_CONTRTMsg inMsg = new WAIT_ISTL_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (WAIT_ISTL_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private void insertWaitIstlContr(BigDecimal dsContrDtlPk) {
        WAIT_ISTL_CONTRTMsg inMsg = new WAIT_ISTL_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(inMsg.fsrNum, DEF_FSR_NUM);
        EZDTBLAccessor.create(inMsg);
    }
    // add end 2017/10/10 QC#21617
    // Mod Start 2018/05/15 QC#25438
    private boolean isSkipTrmdProcForSvcExchng(DS_CONTR_INTFCTMsg paramTMsg) {
        // Exists Add Contract Interface Data
        List<BigDecimal> addContrIntfcPkList = getAddContrIntfcPkForSvcEchng(paramTMsg.dsContrIntfcBatNum.getValue(), paramTMsg.dsContrSrcRefNum.getValue(), paramTMsg.contrIntfcSrcTpCd.getValue());
        if (addContrIntfcPkList.size() == 0) {
            // Skip Terminate Process
            return true;
        }

        // Check Status by Add Contract Interface
        DS_CONTR_INTFCTMsg addContrTMsg;
        for (BigDecimal dsContrIntfcPk : addContrIntfcPkList) {
            addContrTMsg = getDsContrIntfc(dsContrIntfcPk);
            if (!DS_CONTR_INTFC_STS.SUCCESS.equals(addContrTMsg.dsContrIntfcStsCd.getValue())) {
                // Skip Terminate Process
                return true;
            }
        }
        return false;
    }

    private boolean isSvcExchngByTrmd(DS_CONTR_INTFCTMsg paramTMsg) {
        // Check Terminate Action
        if (!DS_CONTR_INTFC_ACT.TERMINATE.equals(paramTMsg.dsContrIntfcActCd.getValue())) {
            return false;
        }

        // Check Service Exchange Order
        String cpoOrdNum = getTermCpoOrdNumForSvcEchng(paramTMsg.dsContrSrcRefNum.getValue(), paramTMsg.contrIntfcSrcTpCd.getValue(), paramTMsg.dsContrIntfcBatNum.getValue());
        if (!hasValue(cpoOrdNum)) {
            return false;
        }
        return true;
    }

    private String getTermCpoOrdNumForSvcEchng(String dsContrSrcRefNum, String contrIntfcSrcTpCd, String dsContrIntfcBatNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", dsContrSrcRefNum);
        param.put("contrIntfcSrcTpCd", contrIntfcSrcTpCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcBatNum);
        param.put("ordCatgCtxTpCd", "SERVICE_EXCHANGE");
        // START 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
        param.put("rtrnLineStsCd", RTRN_LINE_STS.CLOSED);
        // END 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
        return (String) this.ssmBatchClient.queryObject("getTermCpoOrdNumForSvcEchng", param);
    }

    private List<BigDecimal> getAddContrIntfcPkForSvcEchng(String dsContrIntrfcBatNum, String dsContrSrcRefNum, String contrIntfcSrcTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntrfcBatNum", dsContrIntrfcBatNum);
        param.put("dsContrSrcRefNum", dsContrSrcRefNum);
        param.put("contrIntfcSrcTpCd", contrIntfcSrcTpCd);
        param.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getAddContrIntfcPkForSvcEchng", param);
    }

    private boolean isSkipAddContrProcForSvcExchng(DS_CONTR_INTFCTMsg paramTMsg) {
        // Exists Terminate Interface Data
        List<BigDecimal> trmdIntfcPkList = getTrmdIntfcPkForSvcEchng(paramTMsg.dsContrIntfcBatNum.getValue(), paramTMsg.dsContrSrcRefNum.getValue(), paramTMsg.contrIntfcSrcTpCd.getValue());
        if (trmdIntfcPkList.size() == 0) {
            // Skip Add to Contract Process
            return true;
        }

        // Update DS_CONTR_INTFC
        List<BigDecimal> addContrIntfcPkList = getAddContrIntfcPk(paramTMsg);
        DS_CONTR_INTFCTMsg inTMsg;
        for (BigDecimal addContrIntfcPk : addContrIntfcPkList) {
            inTMsg = getDsContrIntfc(addContrIntfcPk);
            updateTermAmt(inTMsg, trmdIntfcPkList);
        }

        return false;
    }

    private boolean isSvcExchngByAddContr(DS_CONTR_INTFCTMsg paramTMsg) {
        // Check Add to Contract Action
        if (!DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT.equals(paramTMsg.dsContrIntfcActCd.getValue())) {
            return false;
        }

        // Check Service Exchange Order
        String cpoOrdNum = getAddContrCpoOrdNumForSvcEchng(paramTMsg.dsContrSrcRefNum.getValue(), paramTMsg.contrIntfcSrcTpCd.getValue(), paramTMsg.dsContrIntfcBatNum.getValue());
        if (!hasValue(cpoOrdNum)) {
            return false;
        }
        return true;
    }

    private void updateTermAmt(DS_CONTR_INTFCTMsg inTMsg, List<BigDecimal> trmdIntfcPkList) {
        // Check Base Only
        if (!CONTR_INTFC_DTL_TP.BASE.equals(inTMsg.contrIntfcDtlTpCd.getValue())) {
            return;
        }

        DS_CONTR_INTFCTMsg trmdIntfcTMsg = getTrmdIntfcTMsg(trmdIntfcPkList, inTMsg.oldSvcMachMstrPk.getValue());
        if (trmdIntfcTMsg == null) {
            return;
        }

        // Original Contract
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(inTMsg.oldDsContrDtlPk.getValue());
        DS_CONTRTMsg dsContrTMsg = getDsContr(dsContrDtlTMsg.dsContrPk.getValue());

        // Mod Start 2018/05/21 QC#23433
        if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.WARRANTY.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return;
        }

        if (ZYPDateUtil.compare(trmdIntfcTMsg.contrFromDt.getValue(), trmdIntfcTMsg.contrCloDt.getValue()) > 0) {
            return;
        }
        // Mod End 2018/05/21 QC#23433

        // Calculate TermAmt for Exchange.
        // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
        List<Map<String, Object>> schdList = getSchdList(dsContrDtlTMsg.dsContrDtlPk.getValue(), trmdIntfcTMsg.contrCloDt.getValue());
        BigDecimal trmdTermAmt = BigDecimal.ZERO;
        String trmdStubFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
        for (int i = 0; i < schdList.size(); i++) {
            Map<String, Object> schd = schdList.get(i);
            trmdTermAmt = trmdTermAmt.add((BigDecimal) schd.get("BASE_ACTL_PRC_DEAL_AMT"));
            String bllgSchdThruDt = (String) schd.get("BLLG_SCHD_THRU_DT");
            if (trmdStubFromDt.compareTo(bllgSchdThruDt) < 0) {
                trmdStubFromDt = ZYPDateUtil.addDays(bllgSchdThruDt, 1);
            }
        }
        // Add End   2019/04/15 K.Fujimoto QC#31137/50058
        // Mod Start 2019/04/15 K.Fujimoto QC#31137/50058
        // BigDecimal trmdTermAmt = getTrmdTermAmt(trmdIntfcTMsg, dsContrTMsg.ccyCd.getValue());
        Map<String, Object> lastSchd = getLastSchd(dsContrDtlTMsg.dsContrDtlPk.getValue(), trmdIntfcTMsg.contrCloDt.getValue());
        if (lastSchd != null) {
            BigDecimal trmdStubTermAmt;
            if (((String) lastSchd.get("INV_FLG")).equals(ZYPConstant.FLG_ON_Y)) {
                trmdStubTermAmt = calcCreditAmtForTerminate(trmdIntfcTMsg.contrCloDt.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), (BigDecimal) lastSchd.get("BASE_ACTL_PRC_DEAL_AMT"));
            } else {
                trmdStubTermAmt = getTrmdTermAmt(trmdIntfcTMsg, dsContrTMsg.ccyCd.getValue(), trmdStubFromDt, (BigDecimal) lastSchd.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
            trmdTermAmt = trmdTermAmt.add(trmdStubTermAmt);
        }
        // Mod End 2019/04/15 K.Fujimoto QC#31137/50058
        // Mod Start 2019/07/30 S.Kitamura QC#52193
        // BigDecimal calcAddContrTermAmt = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue().subtract(trmdTermAmt);
        BigDecimal calcAddContrTermAmt = BigDecimal.ZERO;
        if (hasValue(dsContrDtlTMsg.basePrcTermDealAmtRate)) {
            calcAddContrTermAmt = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue().subtract(trmdTermAmt);
        }
        // Mod End 2019/07/30 S.Kitamura QC#52193
        if (BigDecimal.ZERO.compareTo(calcAddContrTermAmt) > 0) {
            return;
        }

        // Update DS_CONTR_INTFC
        ZYPEZDItemValueSetter.setValue(inTMsg.basePrcTermDealAmtRate, calcAddContrTermAmt);
        S21ApiTBLAccessor.update(inTMsg);
    }

    private String getAddContrCpoOrdNumForSvcEchng(String dsContrSrcRefNum, String contrIntfcSrcTpCd, String dsContrIntfcBatNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrSrcRefNum", dsContrSrcRefNum);
        param.put("contrIntfcSrcTpCd", contrIntfcSrcTpCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcBatNum);
        param.put("ordCatgCtxTpCd", "SERVICE_EXCHANGE");
        return (String) this.ssmBatchClient.queryObject("getAddContrCpoOrdNumForSvcEchng", param);
    }

    private List<BigDecimal> getTrmdIntfcPkForSvcEchng(String dsContrIntrfcBatNum, String dsContrSrcRefNum, String contrIntfcSrcTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntrfcBatNum", dsContrIntrfcBatNum);
        param.put("dsContrSrcRefNum", dsContrSrcRefNum);
        param.put("contrIntfcSrcTpCd", contrIntfcSrcTpCd);
        param.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getTrmdIntfcPkForSvcEchng", param);
    }

    private DS_CONTR_INTFCTMsg getTrmdIntfcTMsg(List<BigDecimal> trmdIntfcPkList, BigDecimal oldSvcMachMstrPk) {
        BigDecimal termIntfcPk = getTrmdIntfcPk(trmdIntfcPkList, oldSvcMachMstrPk);
        if (!hasValue(termIntfcPk)) {
            return null;
        }
        return getDsContrIntfc(termIntfcPk);
    }

    private List<BigDecimal> getAddContrIntfcPk(DS_CONTR_INTFCTMsg paramTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", paramTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", paramTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", paramTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", paramTMsg.dsContrIntfcActCd.getValue());
        param.put("reprocess", DS_CONTR_PROC_STS.REPROCESS);
        param.put("inProcess", DS_CONTR_PROC_STS.IN_PROCESS);
        param.put("base", CONTR_INTFC_DTL_TP.BASE);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getAddContrIntfcPk", param);
    }
    
    private BigDecimal getTrmdIntfcPk(List<BigDecimal> intfcPkList, BigDecimal oldSvcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("intfcPkList", intfcPkList);
        param.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        param.put("dsContrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.BASE);
        param.put("oldSvcMachMstrPk", oldSvcMachMstrPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("getTrmdIntfcPk", param);
    }

    // Mod Start 2019/04/15 K.Fujimoto QC#31137/50058
    private BigDecimal getTrmdTermAmt(DS_CONTR_INTFCTMsg trmdIntfcTMsg, String ccyCd, String stubFromDt, BigDecimal lastSchdDealAmt) {
    //    private BigDecimal getTrmdTermAmt(DS_CONTR_INTFCTMsg trmdIntfcTMsg, String ccyCd) {
        if (BigDecimal.ZERO.compareTo(lastSchdDealAmt) >= 0) {
            return BigDecimal.ZERO;
        }
        CalcTermAmtBean inBean = new CalcTermAmtBean();
        inBean.setGlblCmpyCd(trmdIntfcTMsg.glblCmpyCd.getValue());
        // inBean.setContrPrcEffFromDt(trmdIntfcTMsg.contrFromDt.getValue());
        inBean.setContrPrcEffFromDt(stubFromDt);
        inBean.setContrPrcEffThruDt(trmdIntfcTMsg.contrCloDt.getValue());
        inBean.setBllgCycleCd(trmdIntfcTMsg.bllgCycleCd.getValue());
        inBean.setContrCloDay(trmdIntfcTMsg.contrCloDay.getValue());
        // inBean.setBasePrcDealAmt(trmdIntfcTMsg.basePrcDealAmt.getValue());
        inBean.setBasePrcDealAmt(lastSchdDealAmt);
        // Mod END 2019/04/15 K.Fujimoto QC#31137/50058
        inBean.setCcyCd(ccyCd);
        return NSXC003001CalcTermAmt.calcTermAmt(inBean);
    }

    // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
    private BigDecimal calcCreditAmtForTerminate(String cloDt, BigDecimal dsContrDtlPk,  BigDecimal lastSchdDealAmt) {
        CalcCreditAmtForTerminateBean inBean = new CalcCreditAmtForTerminateBean();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        inBean.setDsContrDtlPk(dsContrDtlPk);
        inBean.setTrmnDt(cloDt);
        // START 2019/12/17 [QC#54974, MOD]
        NSXC003001CalcCreditAmtForTerminate.calcCreditAmtForTerminate1Period(inBean);
        // END   2019/12/17 [QC#54974, MOD]
        BigDecimal creditAmt = inBean.getCreditAmt();
        
        return lastSchdDealAmt.subtract(inBean.getCreditAmt());
    }
    // Add End 2019/04/15 K.Fujimoto QC#31137/50058

    // Mod Start 2018/07/19 QC#26379
    private boolean setNSZC046001PMsgForSvcExchng(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        // Terminate No Exchange Accessory
        trmdNoExchAcc(nszc046001PMsg, dsContrIntfcTMsg);
        if (DS_CONTR_INTFC_STS.ERROR.equals(dsContrIntfcTMsg.dsContrIntfcStsCd.getValue())) {
            updateIntfcToTrmdAcc(dsContrIntfcTMsg);
            return false;
        }

        // Add Start 2018/06/18 QC#25872
        chngMtrGrpMdl(nszc046001PMsg, dsContrIntfcTMsg);
        // Add End 2018/06/18 QC#25872

        if (DS_CONTR_CATG.FLEET.equals(nszc046001PMsg.dsContrCatgCd.getValue())) {
            // xxContrXsCopyList
            if (nszc046001PMsg.xxContrXsCopyList.getValidCount() > 0) {
                ZYPTableUtil.clear(nszc046001PMsg.xxContrXsCopyList);
            }
        } else if (DS_CONTR_CATG.AGGREGATE.equals(nszc046001PMsg.dsContrCatgCd.getValue())) {
            DS_CONTR_DTLTMsg aggLine = getAggLineDsContrDtl(nszc046001PMsg.dsContrPk.getValue());
            if (aggLine == null) {
                return true;
            }
            DS_CONTR_DTL_TPTMsg dsContrDtlTp;
            for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
                dsContrDtlTp = getDsContrDtlTp(nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlTpCd.getValue());
                if (dsContrDtlTp == null) {
                    continue;
                }
                if (ZYPConstant.FLG_ON_Y.equals(dsContrDtlTp.mainUnitLineFlg.getValue())) {
                    setValue(nszc046001PMsg.xxContrDtlList.no(i).prntDsContrDtlPk, aggLine.dsContrDtlPk);
                }
            }
            EZDMsg.copy(aggLine, null, nszc046001PMsg.xxContrDtlList.no(nszc046001PMsg.xxContrDtlList.getValidCount()), null);
            nszc046001PMsg.xxContrDtlList.setValidCount(nszc046001PMsg.xxContrDtlList.getValidCount() + 1);
        }

        // add start 2018/08/30 QC#27102
        NSZC046001_xxContrDtlListPMsg dtlPMsg;
        BigDecimal oldDsContrDtlPk;
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {

            dtlPMsg = nszc046001PMsg.xxContrDtlList.no(i);
            oldDsContrDtlPk = getOldDsContrDtlPk(dtlPMsg, dsContrIntfcTMsg);
            if (!hasValue(oldDsContrDtlPk)) {
                if (!hasValue(dtlPMsg.svcMachMstrPk) || !this.noExchAccDtlPkMap.containsKey(dtlPMsg.svcMachMstrPk.getValue())) {
                    continue;
                }
                oldDsContrDtlPk = this.noExchAccDtlPkMap.get(dtlPMsg.svcMachMstrPk.getValue());
            }

            if (hasValue(oldDsContrDtlPk)) {
                // get DS_CONTR_DTL
                DS_CONTR_DTLTMsg dsContrDtltmsg = getDsContrDtl(oldDsContrDtlPk);
                if (hasValue(dsContrDtltmsg.uplftFromDt) ) {
                    setValue(dtlPMsg.uplftFromDt, dsContrDtltmsg.uplftFromDt.getValue());
                }
                // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
                BigDecimal prcEffCnt = getPrcEffCntForExch(oldDsContrDtlPk, dtlPMsg.contrEffFromDt.getValue());
                if (hasValue(prcEffCnt) && prcEffCnt.compareTo(BigDecimal.ONE) > 0) {
                    setValue(dtlPMsg.oldDsContrDtlPk, oldDsContrDtlPk);
                }
                // Add End   2019/04/15 K.Fujimoto QC#31137/50058
            }
        }
        // add end 2018/08/30 QC#27102

        return true;
    }
    // Mod End 2018/07/19 QC#26379

    private DS_CONTR_DTLTMsg getAggLineDsContrDtl(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
        param.setSQLID("006");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.AGGREGATE);
        DS_CONTR_DTLTMsgArray rsltArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        }
        return rsltArray.no(0);
    }

    // Add Start 2018/05/29 QC#26379
    private DS_CONTR_DTLTMsg getFltLineDsContrDtl(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg param = new DS_CONTR_DTLTMsg();
        param.setSQLID("006");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrPk01", dsContrPk);
        param.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);
        DS_CONTR_DTLTMsgArray rsltArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (rsltArray == null || rsltArray.getValidCount() == 0) {
            return null;
        }
        return rsltArray.no(0);
    }
    // Add End 2018/05/29 QC#26379

    private DS_CONTR_DTL_TPTMsg getDsContrDtlTp(String dsContrDtlTpCd) {
        DS_CONTR_DTL_TPTMsg inMsg = new DS_CONTR_DTL_TPTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlTpCd, dsContrDtlTpCd);
        return (DS_CONTR_DTL_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private int countErrDsContrVld(BigDecimal dsContrPk) {
        DS_CONTR_VLD_RSLT_WRKTMsg inMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);
        inMsg.setConditionValue("dsContrVldStsCd01", DS_CONTR_VLD_STS.ERROR);
        DS_CONTR_VLD_RSLT_WRKTMsgArray rsltArray = (DS_CONTR_VLD_RSLT_WRKTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        return rsltArray.getValidCount();
    }
    // Mod End 2018/05/15 QC#25438
    // Add Start 2018/05/18 QC#23433
    // Mod Start 2018/05/24 QC#26196
    private void updateContrForExch(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        // Update Contract Header
        updateContrHdrForExch(nszc046001PMsg, dsContrIntfcTMsg);

        // Update Contract Detail
        NSZC046001_xxContrDtlListPMsg dtlPMsg;
        Map<String, String> billWithEquipFlgMap;
        // Add Start 2018/06/11 QC#26441
        BigDecimal oldDsContrDtlPk;
        // Add End 2018/06/11 QC#26441
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            dtlPMsg = nszc046001PMsg.xxContrDtlList.no(i);
            // Add Start 2018/06/18 QC#25872
            if (isMainMachine(dtlPMsg) && chngMtrGrp(dtlPMsg.svcMachMstrPk.getValue(), dsContrIntfcTMsg)) {
                continue;
            }
            // Add End 2018/06/18 QC#25872

            billWithEquipFlgMap = getBillWithEquipFlg(dtlPMsg, dsContrIntfcTMsg);
            // Mod Start 2018/05/28 QC#26380
            // Update Bill with Equipment
            if (billWithEquipFlgMap != null && ZYPConstant.FLG_ON_Y.equals(billWithEquipFlgMap.get("BILL_WITH_EQUIP_FLG"))) {
                // Update DS_CONTR_DTL
                if (!updateBillWithEquipByDtl(dtlPMsg, billWithEquipFlgMap)) {
                    continue;
                }

                // Update DS_CONTR_BLLG_SCHD
                updateBillWithEquipBySchd(dtlPMsg);
            }

            oldDsContrDtlPk = getOldDsContrDtlPk(dtlPMsg, dsContrIntfcTMsg);
            if (!hasValue(oldDsContrDtlPk)) {
                if (!hasValue(dtlPMsg.svcMachMstrPk) || !this.noExchAccDtlPkMap.containsKey(dtlPMsg.svcMachMstrPk.getValue())) {
                    continue;
                }
                oldDsContrDtlPk = this.noExchAccDtlPkMap.get(dtlPMsg.svcMachMstrPk.getValue());
            }

            // START 2019/12/17 [QC#54974, ADD]
            // Copy Contract Detail Attribete
            copyContrDtlAttrb(dtlPMsg, oldDsContrDtlPk);
            // END   2019/12/17 [QC#54974, ADD]
            // Create Distribution
            createDistribution(dtlPMsg, oldDsContrDtlPk);

            // Create Term&Condition
            createTermCond(dtlPMsg, oldDsContrDtlPk);

            // Create Renewal & Uplift
            createRnwEscl(dtlPMsg, oldDsContrDtlPk);

            // Create Sub Contract
            createSubContr(dtlPMsg, oldDsContrDtlPk);

            // Add Start 2018/08/29 QC#27231
            // Create Service Machine Exchange Tracking
            createSvcMachExchTrk(dtlPMsg, oldDsContrDtlPk);
            // Add End 2018/08/29 QC#27231
            // START 2019/01/15 S.Kitamura[QC#26928, ADD]
            createDsContrCrCardPo(dtlPMsg, oldDsContrDtlPk);
            // END 2019/01/15 S.Kitamura[QC#26928, ADD]
            // Add Start 2019/01/18 QC#29083
            // Update Billing Meter
            updateDsContrBllgMtr(dtlPMsg, oldDsContrDtlPk);
            // Add End 2019/01/18 QC#29083
            // START 2022/06/01 [QC#59973, ADD]
            callSchdAgmtAdjContrApi(NWZC194001Constant.MODE_SERVICE_EXCHANGE, glblCmpyCd, dtlPMsg.svcMachMstrPk.getValue(), dtlPMsg.dsContrPk.getValue());
            // END 2022/06/01 [QC#59973, ADD]
            // START 2023/02/16 K.Watanabe [QC#60913, ADD]
            SVC_CONFIG_MSTR_DTLTMsg newConfigDtl = getSvcConfigMstrDtlForMachPk(dtlPMsg.svcMachMstrPk.getValue());
            List<Map<String, Object>> rsltList = getSvcPkgMap(glblCmpyCd, newConfigDtl.mdlId.getValue(), ZYPDateUtil.getSalesDate(glblCmpyCd));
            DS_CONTR_DTLTMsg oldDsContrDtlTMsg = getDsContrDtl(oldDsContrDtlPk);
            BigDecimal oldPrcMtrPkgPk = oldDsContrDtlTMsg.prcMtrPkgPk.getValue();
            boolean isOldDsContrDtlPkContain = false;
            for (Map<String, Object> dsContrDtlTMsg : rsltList) {
                // START 2023/04/13 T.Nagae [QC#60913, MOD]
                if (dsContrDtlTMsg.get("PRC_MTR_PKG_PK") != null && ((BigDecimal) dsContrDtlTMsg.get("PRC_MTR_PKG_PK")).equals(oldPrcMtrPkgPk)) {
//                if (dsContrDtlTMsg.get("PRC_MTR_PKG_PK") != null && oldPrcMtrPkgPk.equals((BigDecimal) dsContrDtlTMsg.get("PRC_MTR_PKG_PK"))) {
                // END   2023/04/13 T.Nagae [QC#60913, MOD]
                    isOldDsContrDtlPkContain = true;
                    break;
                }
            }
            if (isOldDsContrDtlPkContain) {
                DS_CONTR_DTLTMsg newContrDtlTMsg = getDsContrDtlForUpd(dtlPMsg.dsContrDtlPk.getValue());
                setValue(newContrDtlTMsg.prcMtrPkgPk, oldPrcMtrPkgPk);
                EZDTBLAccessor.update(newContrDtlTMsg);
            }
            // END 2023/02/16 K.Watanabe [QC#60913, ADD]
        }
    }

    private void updateContrHdrForExch(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        if (!hasValue(nszc046001PMsg.dsContrPk)) {
            return;
        }

        DS_CONTRTMsg dsContrTMsg = getDsContr(nszc046001PMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            return;
        }

        // Update DS_CONTR
        setValue(dsContrTMsg.svcContrRefCmntTxt, editSvcContrRefCmntTxt(dsContrTMsg, dsContrIntfcTMsg));
        EZDTBLAccessor.update(dsContrTMsg);
    }

    private String editSvcContrRefCmntTxt(DS_CONTRTMsg dsContrTMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        String cpoOrdNum = getCpoOrdNumForExch(dsContrIntfcTMsg);
        if (!hasValue(cpoOrdNum)) {
            return dsContrTMsg.svcContrRefCmntTxt.getValue();
        }

        if (!hasValue(dsContrTMsg.svcContrRefCmntTxt.getValue())) {
            return cpoOrdNum;
        }

        if (dsContrTMsg.svcContrRefCmntTxt.getValue().indexOf(cpoOrdNum) >= 0) {
            // exists CPO_ORD_NUM
            return dsContrTMsg.svcContrRefCmntTxt.getValue();
        }

        StringBuilder txt = new StringBuilder();
        txt.append(dsContrTMsg.svcContrRefCmntTxt.getValue());
        txt.append(" | ");
        txt.append(cpoOrdNum);
        String rtnTxt = txt.toString();

        if (rtnTxt.length() > dsContrTMsg.getAttr("svcContrRefCmntTxt").getDigit()) {
            rtnTxt = rtnTxt.substring(0, dsContrTMsg.getAttr("svcContrRefCmntTxt").getDigit());
        }
        return rtnTxt;
    }

    private String getCpoOrdNumForExch(DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        return (String) this.ssmBatchClient.queryObject("getCpoOrdNumForExch", param);
    }
    // Mod End 2018/05/24 QC#26196

    private Map<String, String> getBillWithEquipFlg(NSZC046001_xxContrDtlListPMsg dtlPMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        if (!hasValue(dtlPMsg.svcMachMstrPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        param.put("svcMachMstrPk", dtlPMsg.svcMachMstrPk.getValue());
        return (Map<String, String>) this.ssmBatchClient.queryObject("getBillWithEquipFlg", param);
    }

    private boolean updateBillWithEquipByDtl(NSZC046001_xxContrDtlListPMsg dtlPMsg, Map<String, String> billWithEquipFlgMap) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(dtlPMsg.dsContrDtlPk.getValue());
        if (dsContrDtlTMsg == null) {
            return false;
        }

        // Update
        setValue(dsContrDtlTMsg.baseInvUpToDt, dsContrDtlTMsg.contrEffThruDt);
        setValue(dsContrDtlTMsg.billWithEquipFlg, billWithEquipFlgMap.get("BILL_WITH_EQUIP_FLG"));
        setValue(dsContrDtlTMsg.billWithEquipInvdFlg, billWithEquipFlgMap.get("BILL_WITH_EQUIP_INVD_FLG"));
        EZDTBLAccessor.update(dsContrDtlTMsg);
        return true;
    }

    private void updateBillWithEquipBySchd(NSZC046001_xxContrDtlListPMsg dtlPMsg) {
        DS_CONTR_BLLG_SCHDTMsgArray schdArray = getBillWithEquipBaseSchd(dtlPMsg);
        for (int i = 0; i < schdArray.getValidCount(); i++) {
            // Update
            setValue(schdArray.no(i).invFlg, ZYPConstant.FLG_ON_Y);
            setValue(schdArray.no(i).dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
            EZDTBLAccessor.update(schdArray.no(i));
        }
    }

    private DS_CONTR_BLLG_SCHDTMsgArray getBillWithEquipBaseSchd(NSZC046001_xxContrDtlListPMsg dtlPMsg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("010");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dtlPMsg.dsContrDtlPk.getValue());
        inMsg.setConditionValue("baseChrgFlg01", ZYPConstant.FLG_ON_Y);
        inMsg.setConditionValue("invFlg01", ZYPConstant.FLG_OFF_N);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);

        return (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    // Mod Start 2018/05/24 QC#26196
    private void editModeByTerminated(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        boolean delModeFlg = false;
        NSZC046001_xxContrDtlListPMsg dtlPMsg;
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            dtlPMsg = nszc046001PMsg.xxContrDtlList.no(i);
            if (hasValue(dtlPMsg.dsContrDtlPk) && !isTerminate(dtlPMsg.dsContrDtlPk.getValue())) {
                delModeFlg = true;
            }

            // Contract Close Date
            setValue(dtlPMsg.contrCloDt, editContrCloDtByTrmd(dtlPMsg, dsContrIntfcTMsg));
        }

        if (delModeFlg) {
            // Change Delete Mode
            setValue(nszc046001PMsg.xxModeCd, DS_CONTR_INTFC_ACT_CD_DELETE);
        }
    }

    private String editContrCloDtByTrmd(NSZC046001_xxContrDtlListPMsg dtlPMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        if (!hasValue(dtlPMsg.svcMachMstrPk)) {
            return dtlPMsg.contrCloDt.getValue();
        }

        String addContrFromDt = getAddContrFromDt(dsContrIntfcTMsg, dtlPMsg.svcMachMstrPk.getValue());
        if (!hasValue(addContrFromDt)) {
            return dtlPMsg.contrCloDt.getValue();
        }

        // START 2023/09/04 t.aizawa [QC#60739, ADD]
        String istlDt = getAddContrIstlDt(dsContrIntfcTMsg, dtlPMsg.svcMachMstrPk.getValue());
        // START 2023/09/04 t.aizawa [QC#60739, ADD]

        if (hasValue(dtlPMsg.contrCloDt) && ZYPDateUtil.compare(dtlPMsg.contrCloDt.getValue(), addContrFromDt) < 0) {
            // START 2023/09/04 t.aizawa [QC#60739, ADD]
            if (!hasValue(istlDt) || ZYPDateUtil.compare(dtlPMsg.contrCloDt.getValue(), istlDt) < 0) {
                // END   2023/09/04 t.aizawa [QC#60739, ADD]
                return dtlPMsg.contrCloDt.getValue();
                // START 2023/09/04 t.aizawa [QC#60739, ADD]
            }

            return ZYPDateUtil.addDays(istlDt, -1);
            // END   2023/09/04 t.aizawa [QC#60739, ADD]
        }

        // START 2023/09/04 t.aizawa [QC#60739, ADD]
        if (!hasValue(istlDt) || ZYPDateUtil.compare(addContrFromDt, istlDt) < 0) {
            // END   2023/09/04 t.aizawa [QC#60739, ADD]
            return ZYPDateUtil.addDays(addContrFromDt, -1);
            // START 2023/09/04 t.aizawa [QC#60739, ADD]
        }

        return ZYPDateUtil.addDays(istlDt, -1);
        // END   2023/09/04 t.aizawa [QC#60739, ADD]
    }

    private String getAddContrFromDt(DS_CONTR_INTFCTMsg dsContrIntfcTMsg, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("trmdActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        param.put("addContrActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
        return (String) this.ssmBatchClient.queryObject("getAddContrFromDt", param);
    }
    // Mod End 2018/05/24 QC#26196

    private boolean isTerminate(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTL_STS_VTMsg inMsg = new DS_CONTR_DTL_STS_VTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("ettlAvalFlg01", ZYPConstant.FLG_ON_Y);
        DS_CONTR_DTL_STS_VTMsgArray rsltArray = (DS_CONTR_DTL_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (rsltArray.getValidCount() == 0) {
            return false;
        }

        if (DS_CONTR_CTRL_STS.ENTERED.equals(rsltArray.no(0).dsContrCtrlStsCd.getValue())) {
            return false;
        }
        return true;
    }
    // Add End 2018/05/18 QC#23433
    // Add Start 2018/05/28 QC#26380
    // Mod Start 2018/06/11 QC#26441
    private void createDistribution(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        int i = 0;
        // Copy DS_CONTR_BR_ALLOC
        DS_CONTR_BR_ALLOCTMsgArray dsContrBrAllocTMsgArray = getBrAllocForUpd(oldDsContrDtlPk);
        DS_CONTR_BR_ALLOCTMsg dsContrBrAllcTMsg;
        for (i = 0; i < dsContrBrAllocTMsgArray.getValidCount(); i++) {
            dsContrBrAllcTMsg = new DS_CONTR_BR_ALLOCTMsg();
            EZDMsg.copy(dsContrBrAllocTMsgArray.no(i), null, dsContrBrAllcTMsg, null);
            setValue(dsContrBrAllcTMsg.dsContrBrAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BR_ALLOC_SQ));
            setValue(dsContrBrAllcTMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk);
            EZDTBLAccessor.create(dsContrBrAllcTMsg);
        }

        // Copy DS_CONTR_SEG_ALLOC
        DS_CONTR_SEG_ALLOCTMsgArray dsContrSegAllocTMsgArray = getSegAllocForUpd(oldDsContrDtlPk);
        DS_CONTR_SEG_ALLOCTMsg dsContrSegAllcTMsg;
        for (i = 0; i < dsContrSegAllocTMsgArray.getValidCount(); i++) {
            dsContrSegAllcTMsg = new DS_CONTR_SEG_ALLOCTMsg();
            EZDMsg.copy(dsContrSegAllocTMsgArray.no(i), null, dsContrSegAllcTMsg, null);
            setValue(dsContrSegAllcTMsg.dsContrSegAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_SEG_ALLOC_SQ));
            setValue(dsContrSegAllcTMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk);
            EZDTBLAccessor.create(dsContrSegAllcTMsg);
        }

        // Copy DS_CONTR_PRC_ALLOC
        DS_CONTR_PRC_ALLOCTMsgArray dsContrPrcAllocTMsgArray = getPrcAllocForUpd(oldDsContrDtlPk);
        DS_CONTR_PRC_ALLOCTMsg dsContrPrcAllocTMsg;
        for (i = 0; i < dsContrPrcAllocTMsgArray.getValidCount(); i++) {
            dsContrPrcAllocTMsg = new DS_CONTR_PRC_ALLOCTMsg();
            EZDMsg.copy(dsContrPrcAllocTMsgArray.no(i), null, dsContrPrcAllocTMsg, null);
            setValue(dsContrPrcAllocTMsg.dsContrPrcAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_ALLOC_SQ));
            setValue(dsContrPrcAllocTMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk);
            EZDTBLAccessor.create(dsContrPrcAllocTMsg);
        }
    }
    // Mod End 2018/06/10 QC#26441

    private BigDecimal getOldDsContrDtlPk(NSZC046001_xxContrDtlListPMsg dtlPMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        if (!hasValue(dtlPMsg.svcMachMstrPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        param.put("svcMachMstrPk", dtlPMsg.svcMachMstrPk.getValue());
        return (BigDecimal) this.ssmBatchClient.queryObject("getOldDsContrDtlPk", param);
    }

    private DS_CONTR_BR_ALLOCTMsgArray getBrAllocForUpd(BigDecimal dsContrDtlPk) {
        DS_CONTR_BR_ALLOCTMsg inMsg = new DS_CONTR_BR_ALLOCTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
    }

    private DS_CONTR_SEG_ALLOCTMsgArray getSegAllocForUpd(BigDecimal dsContrDtlPk) {
        DS_CONTR_SEG_ALLOCTMsg inMsg = new DS_CONTR_SEG_ALLOCTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
    }

    private DS_CONTR_PRC_ALLOCTMsgArray getPrcAllocForUpd(BigDecimal dsContrDtlPk) {
        DS_CONTR_PRC_ALLOCTMsg inMsg = new DS_CONTR_PRC_ALLOCTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_PRC_ALLOCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
    }
    // Add End 2018/05/28 QC#26380
    // Add Start 2018/05/28 QC#26382
    private void trmdContrForExch(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        BigDecimal dsContrDtlPk;
        String contrCloDt;
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            dsContrDtlPk = nszc046001PMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue();
            contrCloDt = nszc046001PMsg.xxContrDtlList.no(i).contrCloDt.getValue();
            if (!hasValue(dsContrDtlPk) || !hasValue(contrCloDt)) {
                continue;
            }

            // Terminate Additional Charge
            trmdAddlChrg(dsContrDtlPk, contrCloDt);
        }
    }

    private void trmdAddlChrg(BigDecimal dsContrDtlPk, String contrCloDt) {
        DS_CONTR_ADDL_CHRGTMsgArray trmdAddlChrgArray = getAddlChrgForUpd(dsContrDtlPk);
        for (int i = 0; i < trmdAddlChrgArray.getValidCount(); i++) {
            if (hasValue(trmdAddlChrgArray.no(i).trmnDt) && ZYPDateUtil.compare(trmdAddlChrgArray.no(i).trmnDt.getValue(), contrCloDt) < 0) {
                continue;
            }
            // Set Terminate Date
            setValue(trmdAddlChrgArray.no(i).trmnDt, contrCloDt);
            EZDTBLAccessor.update(trmdAddlChrgArray.no(i));
        }
    }

    // Add Start 2018/06/01 QC#26379
    private DS_CONTR_ADDL_CHRGTMsgArray getAddlChrg(BigDecimal dsContrDtlPk) {
        DS_CONTR_ADDL_CHRGTMsg inMsg = new DS_CONTR_ADDL_CHRGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_ADDL_CHRGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // Add End 2018/06/01 QC#26379

    private DS_CONTR_ADDL_CHRGTMsgArray getAddlChrgForUpd(BigDecimal dsContrDtlPk) {
        DS_CONTR_ADDL_CHRGTMsg inMsg = new DS_CONTR_ADDL_CHRGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_ADDL_CHRGTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
    }
    // Add End 2018/05/28 QC#26382
    // Add Start 2018/05/29 QC#26379
    private boolean isMainMachine(NSZC046001_xxContrDtlListPMsg dtlPMsg) {
        BigDecimal svcMachMstrPk = dtlPMsg.svcMachMstrPk.getValue();
        if (!hasValue(svcMachMstrPk)) {
            return false;
        }

        SVC_MACH_MSTRTMsg machTmsg = getSvcMachMstr(svcMachMstrPk);
        if (machTmsg == null) {
            return false;
        }

        if (!SVC_MACH_TP.MACHINE.equals(machTmsg.svcMachTpCd.getValue())) {
            return false;
        }
        return true;
    }

    private void updatePrntDtlPk(NSZC046001PMsg nszc046001PMsg, NSZC046001_xxContrDtlListPMsg dtlPMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        if (!isMainMachine(dtlPMsg)) {
            return;
        }

        DS_CONTR_DTLTMsg machContrDtlTMsg = getDsContrDtl(dtlPMsg.dsContrDtlPk.getValue());
        if (machContrDtlTMsg == null) {
            return;
        }

        if (DS_CONTR_CATG.FLEET.equals(nszc046001PMsg.dsContrCatgCd.getValue())) {
            // Update Main Machine PRNT_DS_CONTR_DTL_PK (Fleet)
            DS_CONTR_DTLTMsg fltContrDtlTMsg = getFltLineDsContrDtl(dtlPMsg.dsContrPk.getValue());
            if (fltContrDtlTMsg == null) {
                return;
            }
            setValue(machContrDtlTMsg.prntDsContrDtlPk, fltContrDtlTMsg.dsContrDtlPk);
            EZDTBLAccessor.update(machContrDtlTMsg);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(nszc046001PMsg.dsContrCatgCd.getValue())) {
            // Update Main Machine PRNT_DS_CONTR_DTL_PK (Aggregate)
            updateDsContrBllgSchdForExch(machContrDtlTMsg);
        }

        // Update Accessory PRNT_DS_CONTR_DTL_PK
        List<BigDecimal> accDsContrDtlPkList = getDsContrDtlPkForAccNoExch(dtlPMsg, dsContrIntfcTMsg);
        DS_CONTR_DTLTMsg accContrDtlTMsg;
        for (int i = 0; i < accDsContrDtlPkList.size(); i++) {
            accContrDtlTMsg = getDsContrDtl(accDsContrDtlPkList.get(i));
            if (accContrDtlTMsg == null) {
                continue;
            }

            setValue(accContrDtlTMsg.prntDsContrDtlPk, dtlPMsg.dsContrDtlPk);
            EZDTBLAccessor.update(accContrDtlTMsg);

            // Update Accessory DS_CONTR_BLLG_SCHD_SMRY
            updateDsContrBllgSchdSmryForExch(accContrDtlTMsg);
            // Update Accessory DS_CONTR_BLLG_SCHD
            updateDsContrBllgSchdForExch(accContrDtlTMsg);
        }
    }

    private void updateDsContrBllgSchdSmryForExch(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        List<BigDecimal> smryPkList = getDsContrBllgSchdSmryPkForAccNoExch(dsContrDtlTMsg.dsContrDtlPk.getValue());
        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg;
        for (BigDecimal smryPk : smryPkList) {
            inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.dsContrBllgSchdSmryPk, smryPk);
            inMsg = (DS_CONTR_BLLG_SCHD_SMRYTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);
            if (inMsg == null) {
                continue;
            }

            setValue(inMsg.prntDsContrDtlPk, dsContrDtlTMsg.prntDsContrDtlPk);
            EZDTBLAccessor.update(inMsg);
        }
    }

    private void updateDsContrBllgSchdForExch(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        DS_CONTR_BLLG_SCHDTMsg targetBllgSchdTMsg;
        DS_CONTR_BLLG_SCHDTMsg prntBllgSchdTMsg;

        // Base Charge
        DS_CONTR_BLLG_SCHDTMsgArray dsContrBllgSchdTMsgArray = getBllgSchdForUpdate(dsContrDtlTMsg.dsContrDtlPk.getValue(), ZYPConstant.FLG_ON_Y);
        for (int i = 0; i < dsContrBllgSchdTMsgArray.getValidCount(); i++) {
            targetBllgSchdTMsg = dsContrBllgSchdTMsgArray.no(i);
            prntBllgSchdTMsg = getPrntBllgSchd(dsContrDtlTMsg.prntDsContrDtlPk.getValue(), targetBllgSchdTMsg.bllgSchdFromDt.getValue(), targetBllgSchdTMsg.bllgSchdThruDt.getValue(), ZYPConstant.FLG_ON_Y);
            if (prntBllgSchdTMsg == null) {
                continue;
            }

            // Update DS_CONTR_BLLG_SCHD
            setValue(targetBllgSchdTMsg.prntDsContrBllgSchdPk, prntBllgSchdTMsg.dsContrBllgSchdPk);
            setValue(targetBllgSchdTMsg.prntDsContrDtlPk, prntBllgSchdTMsg.dsContrDtlPk);
            EZDTBLAccessor.update(targetBllgSchdTMsg);
        }

        // Usage Charge
        dsContrBllgSchdTMsgArray = getBllgSchdForUpdate(dsContrDtlTMsg.dsContrDtlPk.getValue(), ZYPConstant.FLG_OFF_N);
        for (int i = 0; i < dsContrBllgSchdTMsgArray.getValidCount(); i++) {
            targetBllgSchdTMsg = dsContrBllgSchdTMsgArray.no(i);
            prntBllgSchdTMsg = getPrntBllgSchd(dsContrDtlTMsg.prntDsContrDtlPk.getValue(), targetBllgSchdTMsg.bllgSchdFromDt.getValue(), targetBllgSchdTMsg.bllgSchdThruDt.getValue(), ZYPConstant.FLG_OFF_N);
            if (prntBllgSchdTMsg == null) {
                continue;
            }

            // Update DS_CONTR_BLLG_SCHD
            setValue(targetBllgSchdTMsg.prntDsContrBllgSchdPk, prntBllgSchdTMsg.dsContrBllgSchdPk);
            setValue(targetBllgSchdTMsg.prntDsContrDtlPk, prntBllgSchdTMsg.dsContrDtlPk);
            EZDTBLAccessor.update(targetBllgSchdTMsg);
        }
    }

    private List<BigDecimal> getDsContrDtlPkForAccNoExch(NSZC046001_xxContrDtlListPMsg dtlPMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", dtlPMsg.svcMachMstrPk.getValue());
        param.put("mach", SVC_MACH_TP.MACHINE);
        param.put("acc", SVC_MACH_TP.ACCESSORY);
        param.put("dsContrPk", dtlPMsg.dsContrPk.getValue());
        param.put("dsContrDtlPk", dtlPMsg.dsContrDtlPk.getValue());
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPkForAccNoExch", param);
    }

    private List<BigDecimal> getDsContrBllgSchdSmryPkForAccNoExch(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("baseChrgFlg", ZYPConstant.FLG_ON_Y);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrBllgSchdSmryPkForAccNoExch", param);
    }

    private DS_CONTR_BLLG_SCHDTMsgArray getBllgSchdForUpdate(BigDecimal dsContrDtlPk, String baseChrgFlg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("010");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("baseChrgFlg01", baseChrgFlg);
        inMsg.setConditionValue("invFlg01", ZYPConstant.FLG_OFF_N);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);

        return (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);
    }

    private DS_CONTR_BLLG_SCHDTMsg getPrntBllgSchd(BigDecimal dsContrDtlPk, String bllgSchdFromDt, String bllgSchdThruDt, String baseChrgFlg) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("012");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("baseChrgFlg01", baseChrgFlg);
        inMsg.setConditionValue("invFlg01", ZYPConstant.FLG_OFF_N);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        inMsg.setConditionValue("bllgSchdFromDt01", bllgSchdFromDt);
        inMsg.setConditionValue("bllgSchdThruDt01", bllgSchdThruDt);
        DS_CONTR_BLLG_SCHDTMsgArray rsltArray = (DS_CONTR_BLLG_SCHDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (rsltArray.getValidCount() == 0) {
            return null;
        }
        return rsltArray.no(0);
    }
    // Add End 2018/05/29 QC#26379
    // Add Start 2018/06/01 QC#26379
    private void trmdNoExchAcc(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        Map<String, Object> machMap = getConfigPkForMachIntfc(dsContrIntfcTMsg);
        if (machMap == null) {
            return;
        }
        BigDecimal svcConfigMstrPk = (BigDecimal) machMap.get("SVC_CONFIG_MSTR_PK");
        BigDecimal svcMachMstrPk = (BigDecimal) machMap.get("SVC_MACH_MSTR_PK");

        String fromDt = nszc046001PMsg.xxContrDtlList.no(0).contrEffFromDt.getValue();
        String cloDt = ZYPDateUtil.addDays(fromDt, -1);
        // START 2023/09/04 t.aizawa [QC#60739, ADD]
        String contrCloDt = getTrmdIntfcContrCloDt(dsContrIntfcTMsg, svcMachMstrPk);
        if (hasValue(contrCloDt) && (ZYPDateUtil.compare(contrCloDt, cloDt) < 0)) {
            cloDt = contrCloDt;
        }
        String istlDt = (String) machMap.get("ISTL_DT");
        if (hasValue(istlDt) && (ZYPDateUtil.compare(istlDt, cloDt) <= 0)) {
            cloDt = ZYPDateUtil.addDays(istlDt, -1);
        }
        // END   2023/09/04 t.aizawa [QC#60739, ADD]
        List<Map<String, Object>> accPkMapList = getNoExchAccPkMapList(dsContrIntfcTMsg, nszc046001PMsg.dsContrPk.getValue(), svcConfigMstrPk);
        BigDecimal dsContrDtlPk;
        DS_CONTRTMsg hdrTMsg = getDsContr(nszc046001PMsg.dsContrPk.getValue());
        DS_CONTR_DTLTMsg dtlTMsg;
        // Mod Start 2018/07/19 QC#26379
        NSZC046001PMsg accNszc046001PMsg;
        for (Map<String, Object> accPkMap : accPkMapList) {
            dsContrDtlPk = (BigDecimal) accPkMap.get("DS_CONTR_DTL_PK");
            dtlTMsg = getDsContrDtl(dsContrDtlPk);
            // Add Start 2018/06/11 QC#26441
            this.noExchAccDtlPkMap.put(dtlTMsg.svcMachMstrPk.getValue(), dtlTMsg.dsContrDtlPk.getValue());
            // Add End 2018/06/11 QC#26441
            // Set Add to Contract TMsg
            setAddContrTMsg(nszc046001PMsg, hdrTMsg, dtlTMsg, fromDt, cloDt, svcMachMstrPk);
            // Terminate
            accNszc046001PMsg = terminateAccForExchange(hdrTMsg, dtlTMsg, cloDt);
            if (isErrTrmdNoExchAcc(accNszc046001PMsg, dsContrIntfcTMsg)) {
                return;
            }
        }
        // Mod End 2018/07/19 QC#26379
    }

    private void setAddContrTMsg(NSZC046001PMsg nszc046001PMsg, DS_CONTRTMsg hdrTMsg, DS_CONTR_DTLTMsg dtlTMsg, String fromDt, String cloDt, BigDecimal machPk) {
        // Contract Detail
        int dtlCnt = nszc046001PMsg.xxContrDtlList.getValidCount();
        EZDMsg.copy(dtlTMsg, null, nszc046001PMsg.xxContrDtlList.no(dtlCnt), null);
        setValue(nszc046001PMsg.xxContrDtlList.no(dtlCnt).contrEffFromDt, fromDt);
        setValue(nszc046001PMsg.xxContrDtlList.no(dtlCnt).basePrcTermDealAmtRate, calcBaseTotAmt(hdrTMsg, dtlTMsg, fromDt, cloDt));
        setValue(nszc046001PMsg.xxContrDtlList.no(dtlCnt).prntSvcMachMstrPk, machPk);
        nszc046001PMsg.xxContrDtlList.no(dtlCnt).dsContrDtlStsCd.clear();
        nszc046001PMsg.xxContrDtlList.no(dtlCnt).prntDsContrDtlPk.clear();
        nszc046001PMsg.xxContrDtlList.setValidCount(dtlCnt + 1);

        // Additional Charge
        DS_CONTR_ADDL_CHRGTMsgArray addlTMsgArray = getAddlChrg(dtlTMsg.dsContrDtlPk.getValue());
        DS_CONTR_ADDL_CHRGTMsg addlTMsg;
        int addlCnt;
        for (int i = 0; i < addlTMsgArray.getValidCount(); i++) {
            addlTMsg = addlTMsgArray.no(i);
            addlCnt = nszc046001PMsg.xxDsContrAddlChrgList.getValidCount();
            EZDMsg.copy(addlTMsg, null, nszc046001PMsg.xxDsContrAddlChrgList.no(addlCnt), null);
            setValue(nszc046001PMsg.xxDsContrAddlChrgList.no(addlCnt).effFromDt, fromDt);
            nszc046001PMsg.xxDsContrAddlChrgList.setValidCount(addlCnt + 1);
        }
        // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
        BigDecimal oldDsContrDtlPk = dtlTMsg.dsContrDtlPk.getValue();
        List<Map<String, Object>> prcEffList = getPrcEffForNoExchAcc(oldDsContrDtlPk, fromDt);
        if (prcEffList != null && prcEffList.size() > 1) {
            setValue(nszc046001PMsg.xxContrDtlList.no(dtlCnt).oldDsContrDtlPk, oldDsContrDtlPk);
            int prcEffCnt = nszc046001PMsg.xxDsContrPrcEffList.getValidCount();
            int sqNum = 1;
            for (Map<String, Object> prcEff : prcEffList) {
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).dsContrPrcEffSqNum, new BigDecimal(sqNum));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).dsContrDtlPk, oldDsContrDtlPk);
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).dsContrPrcEffStsCd, (String) prcEff.get("DS_CONTR_PRC_EFF_STS_CD"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).contrPrcEffFromDt , (String) prcEff.get("CONTR_PRC_EFF_FROM_DT"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).contrPrcEffThruDt , (String) prcEff.get("CONTR_PRC_EFF_THRU_DT"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).bllgCycleCd , (String) prcEff.get("BLLG_CYCLE_CD"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).basePrcDealAmt, (BigDecimal) prcEff.get("BASE_PRC_DEAL_AMT"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).basePrcTermDealAmtRate , (BigDecimal) prcEff.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).qltyAsrnHldFlg , (String) prcEff.get("QLTY_ASRN_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).qltyAsrnHldPendApvlFlg , (String) prcEff.get("QLTY_ASRN_HLD_PEND_APVL_FLG"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).mtrHldFlg , (String) prcEff.get("MTR_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).contrHldFlg , (String) prcEff.get("CONTR_HLD_FLG"));
                setValue(nszc046001PMsg.xxDsContrPrcEffList.no(prcEffCnt).bllgHldFlg , (String) prcEff.get("BLLG_HLD_FLG"));
                prcEffCnt++;
                sqNum++;
            }
            nszc046001PMsg.xxDsContrPrcEffList.setValidCount(prcEffCnt);
        }
        // Add End   2019/04/15 K.Fujimoto QC#31137/50058
    }

    // Mod Start 2018/07/19 QC#26379
    private NSZC046001PMsg terminateAccForExchange(DS_CONTRTMsg hdrTMsg, DS_CONTR_DTLTMsg dtlTMsg, String cloDt) {

        NSZC046001PMsg nszc046001PMsg = new NSZC046001PMsg();
        EZDMsg.copy(hdrTMsg, null, nszc046001PMsg, null);
        EZDMsg.copy(dtlTMsg, null, nszc046001PMsg.xxContrDtlList.no(0), null);
        nszc046001PMsg.xxContrDtlList.setValidCount(1);

        setValue(nszc046001PMsg.slsDt, this.salesDate);
        setValue(nszc046001PMsg.xxModeCd, getTrmdDelMode(DS_CONTR_INTFC_ACT_CD_TERMINATE, hdrTMsg));
        setValue(nszc046001PMsg.psnCd, getPsnCd(hdrTMsg.ezUpUserID.getValue()));
        setValue(nszc046001PMsg.xxContrDtlList.no(0).contrCloDt, cloDt);

        // Call Service Contract Data Update API
        NSZC046001 apiNSZC046001 = new NSZC046001();
        apiNSZC046001.execute(nszc046001PMsg, ONBATCH_TYPE.BATCH);
        return nszc046001PMsg;
    }
    // Mod End 2018/07/19 QC#26379

    private Map<String, Object> getConfigPkForMachIntfc(DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        param.put("mach", SVC_MACH_TP.MACHINE);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getConfigPkForMachIntfc", param);
    }

    private List<Map<String, Object>> getNoExchAccPkMapList(DS_CONTR_INTFCTMsg dsContrIntfcTMsg, BigDecimal dsContrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("dsContrIntfcActCd", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        param.put("dsContrPk", dsContrPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getNoExchAccPkMapList", param);
    }

    private BigDecimal calcBaseTotAmt(DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg, String fromDt, String cloDt) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.WARRANTY.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return null;
        }

        if (ZYPDateUtil.compare(dsContrDtlTMsg.contrEffFromDt.getValue(), cloDt) > 0) {
            return null;
        }

        // Calculate TermAmt for Accessory of Exchanged Machine.
        // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
        List<Map<String, Object>> schdList = getSchdList(dsContrDtlTMsg.dsContrDtlPk.getValue(), cloDt);
        BigDecimal trmdTermAmt = BigDecimal.ZERO;
        String trmdStubFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
        for (int i = 0; i < schdList.size(); i++) {
            Map<String, Object> schd = schdList.get(i);
            trmdTermAmt = trmdTermAmt.add((BigDecimal) schd.get("BASE_ACTL_PRC_DEAL_AMT"));
            String bllgSchdThruDt = (String) schd.get("BLLG_SCHD_THRU_DT");
            if (trmdStubFromDt.compareTo(bllgSchdThruDt) < 0) {
                trmdStubFromDt = ZYPDateUtil.addDays(bllgSchdThruDt, 1);
            }
        }
        // Add End   2019/04/15 K.Fujimoto QC#31137/50058
        // Mod Start 2019/04/15 K.Fujimoto QC#31137/50058
        // BigDecimal trmdTermAmt = getTrmdTermAmt(dsContrDtlTMsg, cloDt, dsContrTMsg.ccyCd.getValue());

        Map<String, Object> lastSchd = getLastSchd(dsContrDtlTMsg.dsContrDtlPk.getValue(), cloDt);
        if (lastSchd != null) {
            BigDecimal trmdStubTermAmt;
            if (((String) lastSchd.get("INV_FLG")).equals(ZYPConstant.FLG_ON_Y)) {
                trmdStubTermAmt = calcCreditAmtForTerminate(cloDt, dsContrDtlTMsg.dsContrDtlPk.getValue(), (BigDecimal) lastSchd.get("BASE_ACTL_PRC_DEAL_AMT"));
            } else {
                trmdStubTermAmt = getTrmdTermAmt(dsContrDtlTMsg, cloDt, dsContrTMsg.ccyCd.getValue(), trmdStubFromDt, (BigDecimal) lastSchd.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
            trmdTermAmt = trmdTermAmt.add(trmdStubTermAmt);
        }
        // Mod End 2019/04/15 K.Fujimoto QC#31137/50058

        BigDecimal calcAddContrTermAmt = dsContrDtlTMsg.basePrcTermDealAmtRate.getValue().subtract(trmdTermAmt);
        if (BigDecimal.ZERO.compareTo(calcAddContrTermAmt) > 0) {
            return null;
        }

        return calcAddContrTermAmt;
    }

    // Mod Start 2019/04/15 K.Fujimoto QC#31137/50058
    // private BigDecimal getTrmdTermAmt(DS_CONTR_DTLTMsg dsContrDtlTMsg, String cloDt, String ccyCd) {
    private BigDecimal getTrmdTermAmt(DS_CONTR_DTLTMsg dsContrDtlTMsg, String cloDt, String ccyCd, String fromDt, BigDecimal stubDealAmt) {
        // Mod End 2019/04/15 K.Fujimoto QC#31137/50058
        if (BigDecimal.ZERO.compareTo(stubDealAmt) >= 0) {
            return BigDecimal.ZERO;
        }
        CalcTermAmtBean inBean = new CalcTermAmtBean();
        inBean.setGlblCmpyCd(this.glblCmpyCd);
        // Mod Start 2019/04/15 K.Fujimoto QC#31137/50058
        // inBean.setContrPrcEffFromDt(dsContrDtlTMsg.contrEffFromDt.getValue());
        inBean.setContrPrcEffFromDt(fromDt);
        // Mod End 2019/04/15 K.Fujimoto QC#31137/50058
        inBean.setContrPrcEffThruDt(cloDt);
        inBean.setBllgCycleCd(dsContrDtlTMsg.baseBllgCycleCd.getValue());
        inBean.setContrCloDay(dsContrDtlTMsg.contrCloDay.getValue());
        // inBean.setBasePrcDealAmt(dsContrDtlTMsg.basePrcDealAmt.getValue());
        inBean.setBasePrcDealAmt(stubDealAmt);
        inBean.setCcyCd(ccyCd);

        return NSXC003001CalcTermAmt.calcTermAmt(inBean);
    }
    // Add End 2018/06/01 QC#26379
    // Add Start 2018/06/11 QC#26441
    private void createTermCond(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = getSvcTermCond(this.glblCmpyCd, oldDsContrDtlPk);
        SVC_TERM_CONDTMsg inTMsg;
        for (int i = 0; i < svcTermCondTMsgArray.getValidCount(); i++) {
            inTMsg = new SVC_TERM_CONDTMsg();
            EZDMsg.copy(svcTermCondTMsgArray.no(i), null, inTMsg, null);
            setValue(inTMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
            setValue(inTMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk);
            EZDTBLAccessor.create(inTMsg);
        }
    }

    private SVC_TERM_CONDTMsgArray getSvcTermCond(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // Add End 2018/06/11 QC#26441

    // START 2018/06/11 M.Naito [QC#22971, ADD]
    private void setUplftFlg(NSZC046001_xxDsContrRnwEsclListPMsg rnwEsclPMsg, String contrFromDt, String contrThruDt) {
        // set default
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);

        BigDecimal defContrUplftTermAot = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(defContrUplftTermAot)) {
            defContrUplftTermAot = DEF_CONTR_UPLFT_TERM_AOT;
        }
        int termAot = defContrUplftTermAot.intValue();
        int durCnt = calcDuration(rnwEsclPMsg, contrFromDt, contrThruDt);
        if (termAot > durCnt) {
            termAot = durCnt;
        }
        if (termAot > 10) {
            termAot = 10;
        }
        switch (termAot) {
            case 10:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 9:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 8:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 7:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 6:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 5:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 4:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.frthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 3:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 2:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.scdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            case 1:
                ZYPEZDItemValueSetter.setValue(rnwEsclPMsg.firstYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
            default:
        }
    }

    private int calcDuration(NSZC046001_xxDsContrRnwEsclListPMsg rnwEsclPMsg, String contrFromDt, String contrThruDt) {

        if (!ZYPCommonFunc.hasValue(contrFromDt) || !ZYPCommonFunc.hasValue(contrThruDt)) {
            return 0;
        }

        int count = 0;
        Calendar clndrFromDt = Calendar.getInstance();
        Calendar clndrThruDt = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            clndrFromDt.setTime(sdf.parse(contrFromDt));
            clndrThruDt.setTime(sdf.parse(contrThruDt));
        } catch (ParseException e) {
            return 0;
        }
        clndrFromDt.add(Calendar.MONTH, 1);

        while (clndrFromDt.compareTo(clndrThruDt) <= 0) {
            clndrFromDt.add(Calendar.MONTH, 1);
            count++;
        }

        int durCnt = count / 12;
        if (count % 12 == 0) {
            durCnt--;
        }
        return durCnt;
    }
    // END 2018/06/11 M.Naito [QC#22971, ADD]
    // Add Start 2018/06/13 QC#26628
    private void createRnwEscl(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        DS_CONTR_DTLTMsg oldContrDtlTMsg = getDsContrDtl(oldDsContrDtlPk);
        DS_CONTR_DTLTMsg newContrDtlTMsg = getDsContrDtlForUpd(dtlPMsg.dsContrDtlPk.getValue());
        if (oldContrDtlTMsg == null || newContrDtlTMsg == null) {
            return;
        }

        // Update Contract Detail
        setValue(newContrDtlTMsg.rnwEffFromDt, oldContrDtlTMsg.rnwEffFromDt);
        setValue(newContrDtlTMsg.contrRnwTotCnt, oldContrDtlTMsg.contrRnwTotCnt);
        setValue(newContrDtlTMsg.uplftEffFromDt, oldContrDtlTMsg.uplftEffFromDt);
        EZDTBLAccessor.update(newContrDtlTMsg);

        DS_CONTR_RNW_ESCLTMsgArray rnwEsclArray = getRnwEsclTMsgArray(oldDsContrDtlPk);
        DS_CONTR_RNW_ESCLTMsg inTMsg;
        for (int i = 0; i < rnwEsclArray.getValidCount(); i++) {
            inTMsg = new DS_CONTR_RNW_ESCLTMsg();
            EZDMsg.copy(rnwEsclArray.no(i), null, inTMsg, null);
            setValue(inTMsg.dsContrRnwEsclPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ));
            setValue(inTMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk);
            EZDTBLAccessor.create(inTMsg);
        }
    }

    private DS_CONTR_DTLTMsg getDsContrDtlForUpd(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
    }

    private DS_CONTR_RNW_ESCLTMsgArray getRnwEsclTMsgArray(BigDecimal dsContrDtlPk) {
        DS_CONTR_RNW_ESCLTMsg inMsg = new DS_CONTR_RNW_ESCLTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_RNW_ESCLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private void createSubContr(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        DS_SUB_CONTRTMsgArray subContrArray = getSubContrArray(oldDsContrDtlPk);
        DS_SUB_CONTRTMsg inTMsg;
        for (int i = 0; i < subContrArray.getValidCount(); i++) {
            inTMsg = new DS_SUB_CONTRTMsg();
            EZDMsg.copy(subContrArray.no(i), null, inTMsg, null);
            setValue(inTMsg.dsSubContrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_SQ));
            setValue(inTMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk);
            setValue(inTMsg.effFromDt, dtlPMsg.contrEffFromDt);
            EZDTBLAccessor.create(inTMsg);

            // Terminate
            setValue(subContrArray.no(i).contrTrmnFlg, ZYPConstant.FLG_ON_Y);
            EZDTBLAccessor.update(subContrArray.no(i));

            // START 2018/12/12 [QC#29079, ADD]
            List<Map<String, Object>> subContrMtrList = getOldSubContrMtrList(subContrArray.no(i).dsSubContrPk.getValue(), dtlPMsg.dsContrDtlPk.getValue());
            DS_SUB_CONTR_MTRTMsg mtrTMsg;
            for (int j = 0; j < subContrMtrList.size(); j++) {
                Map<String, Object> targetSubContrMtr = subContrMtrList.get(j);
                mtrTMsg = new DS_SUB_CONTR_MTRTMsg();
                setValue(mtrTMsg.glblCmpyCd, this.glblCmpyCd);
                setValue(mtrTMsg.dsSubContrMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_SUB_CONTR_MTR_SQ));
                setValue(mtrTMsg.dsSubContrPk, inTMsg.dsSubContrPk);
                setValue(mtrTMsg.bllgMtrLbCd, (String) targetSubContrMtr.get("BLLG_MTR_LB_CD"));
                setValue(mtrTMsg.mtrAlwncCnt, (BigDecimal) targetSubContrMtr.get("MTR_ALWNC_CNT"));
                setValue(mtrTMsg.prcMtrRate, (BigDecimal) targetSubContrMtr.get("PRC_MTR_RATE"));
                EZDTBLAccessor.create(mtrTMsg);
            }
            // END 2018/12/12 [QC#29079, ADD]
        }
    }

    private DS_SUB_CONTRTMsgArray getSubContrArray(BigDecimal dsContrDtlPk) {
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("contrTrmnFlg01", ZYPConstant.FLG_OFF_N);
        return (DS_SUB_CONTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // Add End 2018/06/13 QC#26628
    // START 2018/12/12 [QC#29079, ADD]
    private List<Map<String, Object>> getOldSubContrMtrList(BigDecimal dsSubContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsSubContrPk", dsSubContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.CANCELLED);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOldSubContrMtrList", param);
    }
    // END 2018/12/12 [QC#29079, ADD]
    // Add Start 2018/06/18 QC#25872
    private void chngMtrGrpMdl(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        NSZC046001_xxContrDtlListPMsg dtlPMsg;
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            dtlPMsg = nszc046001PMsg.xxContrDtlList.no(i);
            if (!isMainMachine(dtlPMsg)) {
                continue;
            }

            if (!chngMtrGrp(dtlPMsg.svcMachMstrPk.getValue(), dsContrIntfcTMsg)) {
                continue;
            }

            ZYPTableUtil.clear(nszc046001PMsg.xxDsContrBllgMtrList);
            ZYPTableUtil.clear(nszc046001PMsg.xxContrPhysBllgMtrRelnList);
            ZYPTableUtil.clear(nszc046001PMsg.xxContrXsCopyList);
            ZYPTableUtil.clear(nszc046001PMsg.xxDsContrAddlChrgList);
            ZYPTableUtil.clear(nszc046001PMsg.xxDsContrRnwEsclList);
            ZYPTableUtil.clear(nszc046001PMsg.xxDsContrBrAllocList);
            ZYPTableUtil.clear(nszc046001PMsg.xxDsContrSegAllocList);
        }
    }

    private boolean chngMtrGrp(BigDecimal newSvcMachMstrPk, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        SVC_CONFIG_MSTR_DTLTMsg newConfigDtl = getSvcConfigMstrDtlForMachPk(newSvcMachMstrPk);
        if (newConfigDtl == null || !hasValue(newConfigDtl.mdlId)) {
            return false;
        }

        BigDecimal oldSvcMachMstrPk = getOldMachMstrPk(newSvcMachMstrPk, dsContrIntfcTMsg);
        SVC_CONFIG_MSTR_DTLTMsg olsConfigDtl = getSvcConfigMstrDtl(newConfigDtl.svcConfigMstrPk.getValue(), oldSvcMachMstrPk);
        if (olsConfigDtl == null || !hasValue(olsConfigDtl.mdlId)) {
            return false;
        }

        if (!isChangeMtrGrp(olsConfigDtl.mdlId.getValue(), newConfigDtl.mdlId.getValue())) {
            return false;
        }
        return true;
    }

    private SVC_CONFIG_MSTR_DTLTMsg getSvcConfigMstrDtlForMachPk(BigDecimal svcMachMstrPk) {
        if (!hasValue(svcMachMstrPk)) {
            return null;
        }

        SVC_CONFIG_MSTR_DTLTMsg inMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_CONFIG_MSTR_DTLTMsgArray reslutList = (SVC_CONFIG_MSTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (reslutList.getValidCount() == 0) {
            return null;
        }
        return reslutList.no(0);
    }

    private SVC_CONFIG_MSTR_DTLTMsg getSvcConfigMstrDtl(BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg inMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_CONFIG_MSTR_DTLTMsgArray reslutList = (SVC_CONFIG_MSTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (reslutList.getValidCount() == 0) {
            return null;
        }
        return reslutList.no(0);
    }

    private BigDecimal getOldMachMstrPk(BigDecimal svcMachMstrPk, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", CONTR_INTFC_SRC_TP.IB_UPDATE);
        param.put("dsContrIntfcActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
        List<BigDecimal> resultList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getOldMachMstrPk", param);
        if (resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    private boolean isChangeMtrGrp(BigDecimal oldMdlId, BigDecimal newMdlId) {
        DS_MDLTMsg oldMdlTMsg = getDsMdl(oldMdlId);
        DS_MDLTMsg newMdlTMsg = getDsMdl(newMdlId);
        if (oldMdlTMsg == null || newMdlTMsg == null) {
            return false;
        }

        BigDecimal oldMtrGrpPk = oldMdlTMsg.mtrGrpPk.getValue();
        if (!hasValue(oldMtrGrpPk)) {
            oldMtrGrpPk = BigDecimal.ZERO;
        }
        BigDecimal newMtrGrpPk = newMdlTMsg.mtrGrpPk.getValue();
        if (!hasValue(newMtrGrpPk)) {
            newMtrGrpPk = BigDecimal.ZERO;
        }

        if (oldMtrGrpPk.compareTo(newMtrGrpPk) == 0) {
            return false;
        }
        return true;
    }

    private DS_MDLTMsg getDsMdl(BigDecimal mdlId) {
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.mdlId, mdlId);
        return (DS_MDLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // Add End 2018/06/18 QC#25872
    // Add Start 2018/07/19 QC#26379
    private String getTrmdDelMode(String xxModeCd, DS_CONTRTMsg dsContrTMsg) {
        if (DS_CONTR_INTFC_ACT_CD_TERMINATE.equals(xxModeCd)) {
            DS_CONTR_STS_VTMsg stsTMsg = getDsContrStsVTMsg(dsContrTMsg.dsContrPk.getValue());
            if (stsTMsg == null) {
                return xxModeCd;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(stsTMsg.contrTrmnAvalFlg.getValue())) {
                return DS_CONTR_INTFC_ACT_CD_DELETE;
            }
        }
        return xxModeCd;
    }

    private DS_CONTR_STS_VTMsg getDsContrStsVTMsg(BigDecimal dsContrPk) {
        DS_CONTR_STS_VTMsg inMsg = new DS_CONTR_STS_VTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrPk01", dsContrPk);
        DS_CONTR_STS_VTMsgArray rsltList = (DS_CONTR_STS_VTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (rsltList.getValidCount() == 0) {
            return null;
        }
        return rsltList.no(0);
    }

    private boolean isErrTrmdNoExchAcc(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        if (hasValue(nszc046001PMsg.xxMsgId_HD)) {
            setValue(dsContrIntfcTMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
            setValue(dsContrIntfcTMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
            setValue(dsContrIntfcTMsg.intfcErrMsgTxt, nszc046001PMsg.xxDsMultMsgDplyTxt_HD);
            return true;
        }

        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            if (hasValue(nszc046001PMsg.xxContrDtlList.no(i).xxMsgId_DT)) {
                setValue(dsContrIntfcTMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.ERROR);
                setValue(dsContrIntfcTMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                setValue(dsContrIntfcTMsg.intfcErrMsgTxt, nszc046001PMsg.xxContrDtlList.no(i).xxDsMultMsgDplyTxt_DT);
                return true;
            }
        }
        return false;
    }

    private void updateIntfcToTrmdAcc(DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        DS_CONTR_INTFCTMsg inMsg = new DS_CONTR_INTFCTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrIntfcBatNum01", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        inMsg.setConditionValue("dsContrSrcRefNum01", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        inMsg.setConditionValue("contrIntfcSrcTpCd01", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        inMsg.setConditionValue("dsContrIntfcActCd01", dsContrIntfcTMsg.dsContrIntfcActCd.getValue());
        DS_CONTR_INTFCTMsgArray tergetList = (DS_CONTR_INTFCTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);

        for (int i = 0; i < tergetList.getValidCount(); i++) {
            setValue(tergetList.no(i).dsContrProcStsCd, dsContrIntfcTMsg.dsContrProcStsCd);
            setValue(tergetList.no(i).dsContrIntfcStsCd, dsContrIntfcTMsg.dsContrIntfcStsCd);
            setValue(tergetList.no(i).intfcErrMsgTxt, dsContrIntfcTMsg.intfcErrMsgTxt);
            EZDTBLAccessor.update(tergetList.no(i));
        }
    }
    // Add End 2018/07/19 QC#26379
    // Add Start 2018/08/29 QC#27231
    private void createSvcMachExchTrk(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        if (!hasValue(oldDsContrDtlPk)) {
            return;
        }

        DS_CONTR_DTLTMsg oldDsContrTMsg = getDsContrDtl(oldDsContrDtlPk);
        if (oldDsContrTMsg == null) {
            return;
        }

        SVC_MACH_EXCH_TRKTMsg inMsg = new SVC_MACH_EXCH_TRKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMachExchTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_MACH_EXCH_TRK_SQ"));
        setValue(inMsg.origSvcMachMstrPk, oldDsContrTMsg.svcMachMstrPk);
        setValue(inMsg.exchSvcMachMstrPk, dtlPMsg.svcMachMstrPk);
        setValue(inMsg.origDsContrDtlPk, oldDsContrTMsg.dsContrDtlPk);
        setValue(inMsg.exchDsContrDtlPk, dtlPMsg.dsContrDtlPk);
        setValue(inMsg.svcExchDt, dtlPMsg.contrEffFromDt);
        EZDTBLAccessor.create(inMsg);
    }
    // Add End 2018/08/29 QC#27231

    // Add Start 2019/01/18 QC#29776
    /**
     * <pre>
     * updateValidationResult
     * </pre>
     * @param resultPMsg ValidationReturnBean
     * @param rstMsgList List<DS_ADDL_CHRG_INTFCTMsg>
     */
    public static void updateValidationResult(ValidationReturnBean resultPMsg, List<DS_ADDL_CHRG_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        if (resultPMsg == null) {
            resultPMsg = new ValidationReturnBean();
        }
        resultPMsg.xxMsgIdList.setValidCount(resultPMsg.xxMsgIdList.length());
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_ADDL_CHRG_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.dsAddlChrgIntfcPk)) {
                // for update
                DS_ADDL_CHRG_INTFCTMsg tMsg = getDsAddlChrgIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsAddlChrgIntfcPk.getValue());
                if (tMsg == null) {
                    setErrorMessage(resultPMsg, ZZZM9004E, errCnt);
                    return;
                }
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                    setValue(tMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.addChrgIntfcStsCd, ADD_CHRG_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(resultPMsg, NSXM0097E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0097E);

                    return;
                }
            }
        }
    }

    private static DS_ADDL_CHRG_INTFCTMsg getDsAddlChrgIntfc(String glblCmpyCd, BigDecimal dsAddlChrgIntfcPk) {
        DS_ADDL_CHRG_INTFCTMsg prmTMsg = new DS_ADDL_CHRG_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsAddlChrgIntfcPk, dsAddlChrgIntfcPk);
        DS_ADDL_CHRG_INTFCTMsg prmTMsgtst = (DS_ADDL_CHRG_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
        return (DS_ADDL_CHRG_INTFCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(prmTMsg);
    }

    /**
     * <pre>
     * updateValidationResult
     * </pre>
     * @param prmPmsg NSXC001001PMsg
     * @param rstMsgList List<DS_CONTR_INTFCTMsg>
     */
    public static void updateValidationResult(NSXC001001PMsg prmPmsg, List<DS_CONTR_INTFCTMsg> rstMsgList) {
        int errCnt = 0;
        for (int i = 0; i < rstMsgList.size(); i++) {
            DS_CONTR_INTFCTMsg rstMsg = rstMsgList.get(i);
            if (hasValue(rstMsg.dsContrIntfcPk)) {
                // for update
                DS_CONTR_INTFCTMsg tMsg = getContrIntfc(rstMsg.glblCmpyCd.getValue(), rstMsg.dsContrIntfcPk.getValue());
                if (tMsg == null) {
                    setErrorMessage(prmPmsg, ZZZM9004E, errCnt);
                    return;
                }
                if (hasValue(rstMsg.intfcErrMsgTxt)) {
                    setValue(tMsg.intfcErrMsgTxt, rstMsg.intfcErrMsgTxt);
                    setValue(tMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.ERROR);
                } else {
                    setValue(tMsg.intfcErrMsgTxt, (String) null);
                    setValue(tMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
                }

                EZDTBLAccessor.update(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    setErrorMessage(prmPmsg, NSXM0074E, errCnt);
                    setErrorStatusAndMessage(tMsg, NSXM0074E);

                    return;
                }
            }
        }
    }

    private static DS_CONTR_INTFCTMsg getContrIntfc(String glblCmpyCd, BigDecimal dsContrIntfcPk) {
        DS_CONTR_INTFCTMsg prmTMsg = new DS_CONTR_INTFCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsContrIntfcPk, dsContrIntfcPk);
        return (DS_CONTR_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static void setErrorMessage(ValidationReturnBean prmPmsg, String errorMsgId, int errCnt) {
        if (prmPmsg == null || errorMsgId == null) {
            return;
        }
        if (errCnt >= prmPmsg.xxMsgIdList.length()) {
            return;
        }
        setValue(prmPmsg.xxMsgIdList.no(errCnt).xxMsgId, errorMsgId);
    }

    private static void setErrorStatusAndMessage(DS_ADDL_CHRG_INTFCTMsg tMsg, String errorMsgId) {
        if (tMsg == null || errorMsgId == null) {
            return;
        }
        setValue(tMsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
    }

    private static void setErrorMessage(NSXC001001PMsg prmPmsg, String errorMsgId, int errCnt) {
        if (prmPmsg == null || errorMsgId == null) {
            return;
        }
        if (errCnt >= prmPmsg.xxMsgIdList.length()) {
            return;
        }
        setValue(prmPmsg.xxMsgIdList.no(errCnt).xxMsgId, errorMsgId);
    }

    private static void setErrorStatusAndMessage(DS_CONTR_INTFCTMsg tMsg, String errorMsgId) {
        if (tMsg == null || errorMsgId == null) {
            return;
        }
        if (!hasValue(tMsg.intfcErrMsgTxt)) {
            setValue(tMsg.intfcErrMsgTxt, S21MessageFunc.clspGetMessage(errorMsgId));
        }
    }
    // Add End 2019/01/18 QC#29776
    // START 2019/01/15 S.Kitamura[QC#26928, ADD]
    private void createDsContrCrCardPo(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        if (!hasValue(oldDsContrDtlPk)) {
            return;
        }

        DS_CONTR_DTLTMsg oldDsContrTMsg = getDsContrDtl(oldDsContrDtlPk);
        if (oldDsContrTMsg == null) {
            return;
        }
        createPoForExch(dtlPMsg, oldDsContrTMsg.dsContrPk.getValue(), oldDsContrDtlPk);
        createCrCardForExch(dtlPMsg, oldDsContrTMsg.dsContrPk.getValue(), oldDsContrDtlPk);
    }

    private void createPoForExch(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrPk, BigDecimal oldDsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("oldDsContrPk", oldDsContrPk);
        param.put("oldDsContrDtlPk", oldDsContrDtlPk);
        param.put("newMachineFromDt", dtlPMsg.contrEffFromDt.getValue());
        param.put("newDsContrDtlPk", dtlPMsg.dsContrDtlPk.getValue());
        List<Map<String, Object>> poList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPoListForExch", param);
        for (int i = 0; i < poList.size(); i++) {
            Map<String, Object> po = poList.get(i);
            DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
            BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, (BigDecimal)  po.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, (BigDecimal) po.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.custPoNum, (String) po.get("CUST_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.poDt, (String) po.get("PO_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyFlg, (String) po.get("LEASE_CMPY_FLG"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrMachLvlNum, (String) po.get("DS_CONTR_MACH_LVL_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.custIssPoDt, (String) po.get("CUST_ISS_PO_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.poFromDt, (String) po.get("PO_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCardFlg, "N");
            EZDTBLAccessor.insert(tMsg);
        }
    }
    private void createCrCardForExch(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrPk, BigDecimal oldDsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("oldDsContrPk", oldDsContrPk);
        param.put("oldDsContrDtlPk", oldDsContrDtlPk);
        param.put("newDsContrDtlPk", dtlPMsg.dsContrDtlPk.getValue());
        List<Map<String, Object>> crCardList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCrCardListForExch", param);
        for (int i = 0; i < crCardList.size(); i++) {
            Map<String, Object> crCard = crCardList.get(i);
            DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
            BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, (BigDecimal)  crCard.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dtlPMsg.dsContrDtlPk.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, (BigDecimal) crCard.get("DS_CONTR_BLLG_MTR_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, (String) crCard.get("CR_CARD_CUST_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCardExprYrMth, (String) crCard.get("CR_CARD_EXPR_YR_MTH"));
            ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyFlg, (String) crCard.get("LEASE_CMPY_FLG"));
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrMachLvlNum, (String) crCard.get("DS_CONTR_MACH_LVL_NUM"));
            ZYPEZDItemValueSetter.setValue(tMsg.crCardFlg, "Y");
            EZDTBLAccessor.insert(tMsg);
        }
    }
    // END 2019/01/15 S.Kitamura[QC#26928, ADD]

    // Add Start 2019/01/18 QC#29083
    private void updateDsContrBllgMtr(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        if (!hasValue(oldDsContrDtlPk)) {
            return;
        }

        DS_CONTR_BLLG_MTRTMsgArray oldMtrTMsgArray = getDsContrBllgMtrByDtlPk(oldDsContrDtlPk);
        DS_CONTR_BLLG_MTRTMsg oldDsContrBllgMtrTMsg;
        DS_CONTR_BLLG_MTRTMsg newDsContrBllgMtrTMsg;
        String startDt;
        for (int i = 0; i < oldMtrTMsgArray.getValidCount(); i++) {
            oldDsContrBllgMtrTMsg = oldMtrTMsgArray.no(i);
            // START 2023/02/16 K.Watanabe [QC#60913, MOD]
            // if (existsCumCopy(oldDsContrBllgMtrTMsg)) {
            if (oldDsContrBllgMtrTMsg == null) {
                continue;
            }
            // END 2023/02/16 K.Watanabe [QC#60913, MOD]
            newDsContrBllgMtrTMsg = getDsContrBllgMtrByBllgMtr(dtlPMsg.dsContrDtlPk.getValue(), oldDsContrBllgMtrTMsg.bllgMtrLbCd.getValue());
            if (newDsContrBllgMtrTMsg == null) {
                continue;
            }

            // START 2023/02/16 K.Watanabe [QC#60913, MOD]
            // Update Cumulative Copy
            // setValue(newDsContrBllgMtrTMsg.cumCopyCnt, oldDsContrBllgMtrTMsg.cumCopyCnt);
            // setValue(newDsContrBllgMtrTMsg.cumCopyFreqMthAot, oldDsContrBllgMtrTMsg.cumCopyFreqMthAot);
            // startDt = oldDsContrBllgMtrTMsg.cumCopyStartDt.getValue();
            // if (ZYPDateUtil.compare(startDt, dtlPMsg.contrEffFromDt.getValue()) < 0) {
            //     startDt = dtlPMsg.contrEffFromDt.getValue();
            // }
            // setValue(newDsContrBllgMtrTMsg.cumCopyStartDt, startDt);
            // setValue(newDsContrBllgMtrTMsg.cumCopyEndDt, oldDsContrBllgMtrTMsg.cumCopyEndDt);
            // EZDTBLAccessor.update(newDsContrBllgMtrTMsg);
            if (existsCumCopy(oldDsContrBllgMtrTMsg)) {
                // Update Cumulative Copy
                setValue(newDsContrBllgMtrTMsg.cumCopyCnt, oldDsContrBllgMtrTMsg.cumCopyCnt);
                setValue(newDsContrBllgMtrTMsg.cumCopyFreqMthAot, oldDsContrBllgMtrTMsg.cumCopyFreqMthAot);
                startDt = oldDsContrBllgMtrTMsg.cumCopyStartDt.getValue();
                if (ZYPDateUtil.compare(startDt, dtlPMsg.contrEffFromDt.getValue()) < 0) {
                    startDt = dtlPMsg.contrEffFromDt.getValue();
                }
                setValue(newDsContrBllgMtrTMsg.cumCopyStartDt, startDt);
                setValue(newDsContrBllgMtrTMsg.cumCopyEndDt, oldDsContrBllgMtrTMsg.cumCopyEndDt);
                EZDTBLAccessor.update(newDsContrBllgMtrTMsg);
            }
            // END 2023/02/16 K.Watanabe [QC#60913, MOD]

            // START 2023/02/16 K.Watanabe [QC#60913, ADD]
            if (hasValue(oldDsContrBllgMtrTMsg.bllgFreeCopyCnt)) {
                setValue(newDsContrBllgMtrTMsg.bllgFreeCopyCnt, oldDsContrBllgMtrTMsg.bllgFreeCopyCnt);
            }

            if (hasValue(oldDsContrBllgMtrTMsg.bllgMinCnt)) {
                setValue(newDsContrBllgMtrTMsg.bllgMinCnt, oldDsContrBllgMtrTMsg.bllgMinCnt);
            }

            if (hasValue(oldDsContrBllgMtrTMsg.bllgMinAmtRate)) {
                setValue(newDsContrBllgMtrTMsg.bllgMinAmtRate, oldDsContrBllgMtrTMsg.bllgMinAmtRate);
            }

            if (hasValue(oldDsContrBllgMtrTMsg.rollOverCnt)) {
                setValue(newDsContrBllgMtrTMsg.rollOverCnt, oldDsContrBllgMtrTMsg.rollOverCnt);
            }

            if (hasValue(oldDsContrBllgMtrTMsg.bllgFreeCopyCnt) || hasValue(oldDsContrBllgMtrTMsg.bllgMinCnt) || hasValue(oldDsContrBllgMtrTMsg.bllgMinAmtRate) || hasValue(oldDsContrBllgMtrTMsg.rollOverCnt)) {
                EZDTBLAccessor.update(newDsContrBllgMtrTMsg);
            }
            // END 2023/02/16 K.Watanabe [QC#60913, ADD]
        }
    }

    private boolean existsCumCopy(DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg) {
        if (dsContrBllgMtrTMsg == null) {
            return false;
        }
        if (!hasValue(dsContrBllgMtrTMsg.cumCopyCnt)) {
            return false;
        }
        return true;
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrByDtlPk(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg param = new DS_CONTR_BLLG_MTRTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);

        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(param);
    }
    
    private DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtrByBllgMtr(BigDecimal dsContrDtlPk, String bllgMtrLbCd) {
        DS_CONTR_BLLG_MTRTMsg param = new DS_CONTR_BLLG_MTRTMsg();
        param.setSQLID("002");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        param.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);

        DS_CONTR_BLLG_MTRTMsgArray resultArray = (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (resultArray.getValidCount() == 0) {
            return null;
        }

        return resultArray.no(0);
    }
    // Add End 2019/01/18 QC#29083
    // Add Start 2019/04/15 K.Fujimoto QC#31137/50058
    private List<Map<String, Object>> getSchdList(BigDecimal dsContrDtlPk, String cloDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("cloDt", cloDt);
        param.put("regular", DS_BLLG_SCHD_TP.REGULAR);
        param.put("rebill", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSchdList", param);
    }

    private Map<String, Object> getLastSchd(BigDecimal dsContrDtlPk, String cloDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("cloDt", cloDt);
        param.put("regular", DS_BLLG_SCHD_TP.REGULAR);
        param.put("rebill", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLastSchd", param);
        if (resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    private BigDecimal getPrcEffCntForExch(BigDecimal dsContrDtlPk, String exchDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("exchDt", exchDt);
        param.put("canc", DS_CONTR_DTL_STS.CANCELLED);
        return (BigDecimal) this.ssmBatchClient.queryObject("getPrcEffCntForExch", param);
    }

    private List<Map<String, Object>> getPrcEffForNoExchAcc(BigDecimal dsContrDtlPk, String exchDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("exchDt", exchDt);
        param.put("canc", DS_CONTR_DTL_STS.CANCELLED);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPrcEffForNoExchAcc", param);
    }
    // Add End   2019/04/15 K.Fujimoto QC#31137/50058

    // START 2019/12/17 [QC#54974, ADD]
    // Copy Contract Detail Attribete
    private boolean copyContrDtlAttrb(NSZC046001_xxContrDtlListPMsg dtlPMsg, BigDecimal oldDsContrDtlPk) {
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtl(dtlPMsg.dsContrDtlPk.getValue());
        if (dsContrDtlTMsg == null) {
            return false;
        }

        DS_CONTR_DTLTMsg OldDsContrDtlTMsg = getDsContrDtl(oldDsContrDtlPk);
        if (OldDsContrDtlTMsg == null) {
            return false;
        }

        // Update
        setValue(dsContrDtlTMsg.svcInvMergeTpCd, OldDsContrDtlTMsg.svcInvMergeTpCd);
        EZDTBLAccessor.update(dsContrDtlTMsg);
        return true;
    }
    // END   2019/12/17 [QC#54974, ADD]

    // START 2022/06/01 [QC#59973, ADD]
    private boolean callSchdAgmtAdjContrApi(String xxMode, String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal dsContrPk) {
        // Create ApiMsg
        NWZC194001PMsg apiPMsg = new NWZC194001PMsg();
        setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiPMsg.xxModeCd, xxMode);
        setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        // Call Api
        new NWZC194001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        // Check returns
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            return false;
        }
        return true;
    }
    // END 2022/06/01 [QC#59973, ADD]
    // START 2022/07/14 R.Jin [QC#60276, ADD]
    private BigDecimal getAggAndContrCloDtDtlCnt(BigDecimal dsContrPk, List<BigDecimal> dsContrDtlPkList) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPkList", dsContrDtlPkList);
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("cancelled", DS_CONTR_CTRL_STS.CANCELLED);
        param.put("terminated", DS_CONTR_CTRL_STS.TERMINATED);
        param.put("expired", DS_CONTR_CTRL_STS.EXPIRED);
        // START 2023/02/13 R.Jin [QC#61172, ADD]
        param.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        // END 2023/02/13 R.Jin [QC#61172, ADD]
        return (BigDecimal) this.ssmBatchClient.queryObject("getAGGAndContrCloDtNullDtlCnt", param);
    }
    private String getMaxCloDT(String cloDt, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        String maxCloDt = (String) this.ssmBatchClient.queryObject("getMaxCloDt", param);
        if (maxCloDt != null && maxCloDt.compareTo(cloDt) > 0) {
            return maxCloDt;
        }
        return cloDt;
    }
    private String getContractDtlTpCd(BigDecimal dsContractDtlPk) {
        DS_CONTR_DTLTMsg inTmsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(inTmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.dsContrDtlPk, dsContractDtlPk);
        DS_CONTR_DTLTMsg outTmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKeyForUpdate(inTmsg);
        if (outTmsg != null) {
            return outTmsg.dsContrDtlTpCd.getValue();
        }
        return null;
    }
    // END 2022/07/14 R.Jin [QC#60276, ADD]

    // START 2023/02/16 K.Watanabe [QC#60913, ADD]
    /**
     * getSvcPkgMap
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @param slsDt String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcPkgMap(String glblCmpyCd, BigDecimal mdlId, String slsDt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("mdlId", mdlId);
        ssmPrm.put("endDt", END_DT);
        ssmPrm.put("slsDt", slsDt);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcPkgMap", ssmPrm);
    }
    // END 2023/02/16 K.Watanabe [QC#60913, ADD]

    // START 2023/09/04 t.aizawa [QC#60739, ADD]
    /**
     * Get Installation Date for Machine or Accessory to add to Contract.
     * @param dsContrIntfcTMsg DS_CONTR_INTFCTMsg
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    private String getAddContrIstlDt(DS_CONTR_INTFCTMsg dsContrIntfcTMsg, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("trmdActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        param.put("addContrActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
        return (String) this.ssmBatchClient.queryObject("getAddContrIstlDt", param);
    }

    /**
     * Get Contract Closed Date from Terminate Contract Interface.
     * @param dsContrIntfcTMsg DS_CONTR_INTFCTMsg
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    private String getTrmdIntfcContrCloDt(DS_CONTR_INTFCTMsg dsContrIntfcTMsg, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrIntfcBatNum", dsContrIntfcTMsg.dsContrIntfcBatNum.getValue());
        param.put("dsContrSrcRefNum", dsContrIntfcTMsg.dsContrSrcRefNum.getValue());
        param.put("contrIntfcSrcTpCd", dsContrIntfcTMsg.contrIntfcSrcTpCd.getValue());
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("addContrActCd", DS_CONTR_INTFC_ACT.ADD_TO_CONTRACT);
        param.put("trmdActCd", DS_CONTR_INTFC_ACT.TERMINATE);
        return (String) this.ssmBatchClient.queryObject("getTrmdIntfcContrCloDt", param);
    }
    // END   2023/09/04 t.aizawa [QC#60739, ADD]

    // START 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
    private void editModeByTerminatedOnReturn(NSZC046001PMsg nszc046001PMsg, DS_CONTR_INTFCTMsg dsContrIntfcTMsg) {
        boolean delModeFlg = false;
        NSZC046001_xxContrDtlListPMsg dtlPMsg;
        for (int i = 0; i < nszc046001PMsg.xxContrDtlList.getValidCount(); i++) {
            dtlPMsg = nszc046001PMsg.xxContrDtlList.no(i);
            if (hasValue(dtlPMsg.dsContrDtlPk) && !isTerminate(dtlPMsg.dsContrDtlPk.getValue())) {
                delModeFlg = true;
            }

            if (delModeFlg) {
                // Change Delete Mode
                setValue(nszc046001PMsg.xxModeCd, DS_CONTR_INTFC_ACT_CD_DELETE);
            }
        }
    }
    // END 2024/02/15 R.Tamura [CSA-QC#63393,ADD]

}
