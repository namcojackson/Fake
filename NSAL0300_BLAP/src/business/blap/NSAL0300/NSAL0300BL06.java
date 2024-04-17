/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300;

import static business.blap.NSAL0300.constant.NSAL0300Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0300.common.NSAL0300CommonLogic;
import business.blap.NSAL0300.common.NSAL0300DiffValueSetter;
import business.blap.NSAL0300.common.NSAL0300DisplayControllBean;
import business.blap.NSAL0300.constant.NSAL0300Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.CONTR_ADDL_CHRG_BAKTMsg;
import business.db.CONTR_BAKTMsg;
import business.db.CONTR_BAK_SMRYTMsg;
import business.db.CONTR_BANK_ACCT_RELN_BAKTMsg;
import business.db.CONTR_BLLG_MTR_BAKTMsg;
import business.db.CONTR_BLLG_SCHD_BAKTMsg;
import business.db.CONTR_BLLG_SCHD_MTR_BAKTMsg;
import business.db.CONTR_BLLG_SCHD_SMRY_BAKTMsg;
import business.db.CONTR_BR_ALLOC_BAKTMsg;
import business.db.CONTR_CR_CARD_PO_BAKTMsg;
import business.db.CONTR_DTL_BAKTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_BAKTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_PRC_ALLOC_BAKTMsg;
import business.db.CONTR_PRC_EFF_BAKTMsg;
import business.db.CONTR_PRC_EFF_MTR_BAKTMsg;
import business.db.CONTR_RNW_ESCL_BAKTMsg;
import business.db.CONTR_SEG_ALLOC_BAKTMsg;
import business.db.CONTR_SKIP_RECOV_BAKTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.CONTR_XS_COPY_BAKTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ACTTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_BANK_ACCT_RELNTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SKIP_RECOVTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTR_MTRTMsg;
import business.db.MTR_LBTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SUB_CONTR_BAKTMsg;
import business.db.SUB_CONTR_MTR_BAKTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsg;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsg;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsg;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.db.SVC_TERM_COND_ATTRBTMsgArray;
import business.db.SVC_TERM_COND_BAKTMsg;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.common.NSX.NSXC001001.MtrWinInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMtrWinFromThruDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetUplftFromDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.SlaInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.DefSvcTermCondInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SvcTermCond;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_MERGE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/11/11   Hitachi         T.Kanasaka      Update          QC570
 * 2015/11/13   Hitachi         T.Kanasaka      Update          QC663,QC666
 * 2015/11/16   Hitachi         T.Kanasaka      Update          QC729
 * 2015/11/17   Hitachi         T.Kanasaka      Update          QC563,QC756
 * 2015/11/19   Hitachi         T.Kanasaka      Update          QC876,QC889
 * 2015/11/24   Hitachi         T.Kanasaka      Update          QC564
 * 2015/11/26   Hitachi         A.Kohinata      Update          QC1156
 * 2015/11/26   Hitachi         A.Kohinata      Update          QC1159
 * 2015/11/27   Hitachi         A.Kohinata      Update          QC1238
 * 2015/11/30   Hitachi         A.Kohinata      Update          QC842
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC1271
 * 2015/12/02   Hitachi         T.Kanasaka      Update          QC1308
 * 2015/12/07   Hitachi         A.Kohinata      Update          QC1215
 * 2015/12/09   Hitachi         T.Kanasaka      Update          QC814,QC815
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC1907
 * 2015/12/14   Hitachi         T.Kanasaka      Update          QC1161,QC1950,QC1719
 * 2015/12/16   Hitachi         T.Kanasaka      Update          QC814,QC2065
 * 2015/12/25   Hitachi         T.Tomita        Update          QC2133
 * 2016/01/18   Hitachi         T.Tomita        Update          QC2948
 * 2016/01/18   Hitachi         T.Tomita        Update          QC1088
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/01/21   Hitachi         T.Iwamoto       Update          QC#3533
 * 2016/01/28   Hitachi         T.Kanasaka      Update          QC3842
 * 2016/01/29   Hitachi         T.Iwamoto       Update          QC3807
 * 2016/02/09   Hitachi         T.Aoyagi        Update          QC4081
 * 2016/02/10   Hitachi         T.Aoyagi        Update          QC2956
 * 2016/02/12   Hitachi         T.Kanasaka      Update          QC3889
 * 2016/02/16   Hitachi         T.Kanasaka      Update          QC3889,QC2579
 * 2016/02/18   Hitachi         T.Tomita        Update          QC3188
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/02/24   Hitachi         A.Kohinata      Update          QC3697
 * 2016/03/03   Hitachi         K.Kasai         Update          QC3018
 * 2016/03/08   Hitachi         T.Kanasaka      Update          QC2208
 * 2016/04/06   Hitachi         M.Gotou         Update          QC4921
 * 2016/04/08   Hitachi         M.Gotou         Update          QC#5312,5313
 * 2016/04/19   Hitachi         T.Kanasaka      Update          QC3889
 * 2016/05/17   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/05/20   Hitachi         T.Kanasaka      Update          QC#5942
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#7637
 * 2016/05/30   Hitachi         K.Kasai         Update          QC#447
 * 2016/06/14   Hitachi         M.Gotou         Update          QC#9901
 * 2016/06/20   Hitachi         T.Kanasaka      Update          QC#9019
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/11   Hitachi         T.Kanasaka      Update          QC#9019,QC#11528
 * 2016/07/21   Hitachi         A.Kohinata      Update          QC#11720
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/08/08   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/06   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/23   Hitachi         K.Yamada        Update          QC#13686
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/31   Hitachi         T.Kanasaka      Update          QC#15136
 * 2016/11/10   Hitachi         K.Kishimoto     Update          QC#15888
 * 2017/01/12   Hitachi         T.Mizuki        Update          QC#16792
 * 2017/02/07   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/14   Hitachi         Y.Takeno        Update          QC#17275-1
 * 2017/02/21   Hitachi         K.Kishimoto     Update          QC#17646
 * 2017/03/08   Hitachi         K.Ochiai        Update          QC#17453
 * 2017/03/17   Hitachi         K.Kitachi       Update          QC#17948
 * 2017/04/26   Hitachi         Y.Takeno        Update          RS#7224
 * 2017/06/14   Hitachi         K.Kitachi       Update          QC#19142
 * 2017/06/20   Hitachi         N.Arai          Update          QC#19056
 * 2017/06/19   Hitachi         A.Kohinata      Update          QC#19036
 * 2017/07/25   Hitachi         K.Yamada        Update          QC#20131
 * 2017/07/27   Hitachi         K.Kim           Update          QC#16889
 * 2017/07/31   Hitachi         M.Kidokoro      Update          QC#20116
 * 2017/08/10   Hitachi         K.Kojima        Update          QC#20527
 * 2017/09/19   Hitachi         K.Kitachi       Update          QC#21149
 * 2017/09/19   Fujitsu         S.Fujita        Update          QC#18534
 * 2017/09/20   Hitachi         K.Kim           Update          QC#21211
 * 2017/09/26   Hitachi         K.Kojima        Update          QC#21221
 * 2017/10/05   Hitachi         K.Kojima        Update          QC#20523
 * 2018/01/10   Hitachi         T.Tomita        Update          QC#18552
 * 2018/01/16   Hitachi         T.Tomita        Update          QC#23542
 * 2018/01/30   CITS            M.Naito         Update          QC#21349
 * 2018/03/12   Hitachi         T.Tomita        Update          QC#23629
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#24804
 * 2018/04/03   Hitachi         K.Kojima        Update          QC#21056
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/05/15   Hitachi         K.Kitachi       Update          QC#24265
 * 2018/06/03   Hitachi         T.Tomita        Update          QC#26379
 * 2018/06/04   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/06/05   Hitachi         K.Kim           Update          QC#24851
 * 2018/06/07   Hitachi         K.Kim           Update          QC#24857
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/02   Hitachi         K.Kitachi       Update          QC#26765
 * 2018/07/17   Hitachi         K.Kishimoto     Update          QC#25959
 * 2018/08/16   Hitachi         K.Kojima        Update          QC#23067
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 * 2018/08/29   CITS            M.Naito         Update          QC#27102
 * 2018/10/24   Hitachi         K.Kitachi       Update          QC#28889
 * 2018/11/07   Hitachi         K.Fujimoto      Update          QC#28627
 * 2018/12/13   Hitachi         K.Kim           Update          QC#29079
 * 2018/12/27   Hitachi         K.Kitachi       Update          QC#29762
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/01/17   CITS            M.Naito         Update          QC#29083
 * 2019/01/21   CITS            T.Wada          Update          QC#29371
 * 2019/01/28   Hitachi         T.Tomita        Update          QC#29083
 * 2019/02/13   Hitachi         K.Kitachi       Update          QC#30318
 * 2019/02/14   Hitachi         T.Tomita        Update          QC#30295
 * 2019/05/13   Hitachi         K.Kitachi       Update          QC#50211
 * 2019/05/13   Hitachi         K.Fujimoto      Update          QC#31137/50058
 * 2021/01/21   CITS            R.Cabral        Update          QC#59502
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2023/02/09   Hitachi         R.Takau         Update          QC#55645
 * 2023/12/07   Hitachi         Y.Ogura         Update          QC#62173
 * 2024/03/22   Hitachi         Y.Tamai         Update          QC#63463
 *</pre>
 */
public class NSAL0300BL06 extends S21BusinessHandler {

    /** Errored DsContrDtlPk List */
    private List<BigDecimal> errDsContrDtlPkList = null;

    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0300CMsg cMsg = (NSAL0300CMsg) ezdCMsg;
        NSAL0300SMsg sMsg = (NSAL0300SMsg) ezdSMsg;
        // START 2017/06/20 N.Arai [QC#19056, MOD]
        NSAL0300CommonLogic.copyTableA(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - 1);
        // END 2017/06/20 N.Arai [QC#19056, MOD]
        String screenAplId = cMsg.getScreenAplID();
        boolean result = false;
        this.errDsContrDtlPkList = new ArrayList<BigDecimal>();

        if ("NSAL0300Scrn00_CMN_Save".equals(screenAplId)) {
            result = checkInput_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_CMN_Submit".equals(screenAplId)) {
            result = checkInput_NSAL0300Scrn00_CMN_Submit(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenForUpdate".equals(screenAplId)) {
            // mod Start 2024/03/22 QC#63463
            //result = checkInput_NSAL0300Scrn00_OpenForUpdate(cMsg, sMsg);
            result = checkInput_NSAL0300Scrn00_OpenForUpdateAndRevert(cMsg, sMsg);
            // mod End 2024/03/22 QC#63463
        } else if ("NSAL0300Scrn00_OpenWin_AdditionalCharge".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_BillingCounters".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_CreditCardPo".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_Schedule_Base".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_Schedule_Usage".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_UpliftDetail".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_Terminate".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_Renew".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_StatusChange".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_CreditCard".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_SubContract".equals(screenAplId)) {
            result = checkInput_NSAL0300Scrn00_OpenWin_SubContract(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_PricingEffectivity_Base".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_PricingEffectivity_Meter".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_AddNotes".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_Terms".equals(screenAplId)) {
            result = checkInput_NSAL0300Scrn00_OpenWin_Terms(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_OpenWin_CompleteContract".equals(screenAplId)) {
            result = checkInput_NSAL0300Scrn00_OpenWin_CompleteContract(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_Go".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        // add start 2016/09/06 CSA Defect#11243
        } else if ("NSAL0300_NWAL2010".equals(screenAplId)) {
            result = checkInput_NSAL0300_NWAL2010(cMsg, sMsg);
        // add end 2016/09/06 CSA Defect#11243
//        } else if ("NSAL0300Scrn00_CMN_ColClear".equals(screenAplId)) {
//            ZYPGUITableColumn.clearColData(cMsg, sMsg);
//            return INPUT_IS_VALID;
//        } else if ("NSAL0300Scrn00_CMN_ColSave".equals(screenAplId)) {
//            ZYPGUITableColumn.setColData(cMsg, sMsg);
//            return INPUT_IS_VALID;
        // Add Start 2018/01/10 QC#18552
        } else if ("NSAL0300Scrn00_OpenWin_Escalation".equals(screenAplId)) {
            result = checkInputForOtherWin(cMsg, sMsg);
        // Add End 2018/01/10 QC#18552
        //Add Start 2024/03/22 QC#63463
        } else if ("NSAL0300Scrn00_Revert".equals(screenAplId)) {
            result = checkInput_NSAL0300Scrn00_OpenForUpdateAndRevert(cMsg, sMsg);
        //Add End 2024/03/22 QC#63463
        } else {
            throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
        }

        if (!result) {
            paginateByError(cMsg, sMsg);
            this.errDsContrDtlPkList = null;
        }

        return result;
    }

    private void addErroredDsContrDtlPk(BigDecimal dsContrDtlPk) {
        if (this.errDsContrDtlPkList != null && !this.errDsContrDtlPkList.contains(dsContrDtlPk)) {
            errDsContrDtlPkList.add(dsContrDtlPk);
        }
    }

    private void paginateByError(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (this.errDsContrDtlPkList == null) {
            return;
        }

        BigDecimal dsContrDtlPk = null;
        boolean filterLineErrFlg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
                if (this.errDsContrDtlPkList.contains(dsContrDtlPk)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_OFF_N);
                    filterLineErrFlg = true;
                }
            }
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
            if (this.errDsContrDtlPkList.contains(dsContrDtlPk)) {
                // if error exists in current page filterd line, paginate.
                if (filterLineErrFlg) {
                    NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, i);
                }
                return;
            }
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            // paginate error page
            if (this.errDsContrDtlPkList.contains(dsContrDtlPk)) {
                NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, i);
                return;
            }
        }
    }

    private boolean checkInput_NSAL0300Scrn00_CMN_Save(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        boolean valid = INPUT_IS_VALID;

        if (DISPLAY_MODE_FREEZE.equals(cMsg.xxModeCd_FU.getValue())) {
            return valid;
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
        String dsAcctNum = cMsg.dsAcctNum.getValue();

        // Auto set Branch, Toc
        NSAL0300CommonLogic.searchBranch(glblCmpyCd, cMsg, sMsg);
        // START 2016/01/21 T.Tomita [QC#2182, ADD]
        NSAL0300CommonLogic.searchRep(glblCmpyCd, cMsg, sMsg);
        // END 2016/01/21 T.Tomita [QC#2182, ADD]
        // START 2016/02/24 T.Kanasaka [QC4079, ADD]
        NSAL0300CommonLogic.searchSalesRep(glblCmpyCd, cMsg);
        // START 2018/06/18 K.Kim [QC#25195, ADD]
        NSAL0300CommonLogic.searchReportGrp(glblCmpyCd, cMsg);
        // END 2018/06/18 K.Kim [QC#25195, ADD]

        if (NSAL0300CommonLogic.hasError(cMsg)) {
            valid = INPUT_IS_INVALID;
        }
        // END 2016/02/24 T.Kanasaka [QC4079, ADD]

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk) && ZYPCommonFunc.hasValue(cMsg.dsContrNum)) {
            cMsg.dsContrNum.setErrorInfo(1, NSAM0384E);
            valid = INPUT_IS_INVALID;
        }

        if (ZYPCommonFunc.hasValue(cMsg.svcContrBrCd)) {
            SVC_CONTR_BRTMsg tMsg = query.getSvcContrBr(glblCmpyCd, cMsg.svcContrBrCd.getValue());
            if (tMsg == null) {
                cMsg.svcContrBrCd.setErrorInfo(1, NSAM0045E, new String[] {"Branch" });
                valid = INPUT_IS_INVALID;
            }
        }

        // START 2016/02/24 T.Kanasaka [QC4079, DEL]
//        if (ZYPCommonFunc.hasValue(cMsg.tocCd)) {
//            TOCTMsg tMsg = query.getToc(glblCmpyCd, cMsg.tocCd.getValue());
//            if (tMsg == null) {
//                cMsg.tocCd.setErrorInfo(1, NSAM0045E, new String[] {"Rep" });
//                valid = INPUT_IS_INVALID;
//            }
//        }
        // END 2016/02/24 T.Kanasaka [QC4079, DEL]

        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            SELL_TO_CUSTTMsg tMsg = query.getSellToCust(glblCmpyCd, dsAcctNum);
            if (tMsg == null) {
                cMsg.dsAcctNum.setErrorInfo(1, NSAM0045E, new String[] {"Customer" });
                valid = INPUT_IS_INVALID;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
            BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.altPayerCustCd.getValue());
            if (tMsg == null) {
                cMsg.altPayerCustCd.setErrorInfo(1, NSAM0045E, new String[] {"Bill to" });
                valid = INPUT_IS_INVALID;
            }
        }

        // START 2018/07/02 K.Kitachi [QC#26765, ADD]
        if (!NSAL0300CommonLogic.checkCfsRelation(glblCmpyCd, cMsg)) {
            valid = INPUT_IS_INVALID;
        }
        // END 2018/07/02 K.Kitachi [QC#26765, ADD]

        if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
            BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.leaseCmpyCd.getValue());
            if (tMsg == null) {
                cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0045E, new String[] {"Lease Company" });
                valid = INPUT_IS_INVALID;
            // START 2018/07/02 K.Kitachi [QC#26765, ADD]
            } else {
                if (!NSAL0300CommonLogic.checkLeaseCompany(glblCmpyCd, cMsg)) {
                    valid = INPUT_IS_INVALID;
                }
            // END 2018/07/02 K.Kitachi [QC#26765, ADD]
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                cMsg.baseChrgToLeaseCmpyFlg.setErrorInfo(1, NSAM0328E);
                cMsg.usgChrgToLeaseCmpyFlg.setErrorInfo(1, NSAM0328E);
                valid = INPUT_IS_INVALID;
            }
        }

        // START 2017/08/10 K.Kojima [QC#20527,MOD]
        // if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd) && !DS_CONTR_EDI.CFS.equals(cMsg.dsContrEdiCd.getValue())) {
        //     cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0189E, new String[] {"Lease", "Edi = CFS" });
        //     valid = INPUT_IS_INVALID;
        // }
        // END 2017/08/10 K.Kojima [QC#20527,MOD]

        // START 2015/12/25 T.Tomita [QC#2133, ADD]
        // Payment Term Code Master Check
        if (ZYPCommonFunc.hasValue(cMsg.pmtTermCashDiscCd)) {
            PMT_TERM_CASH_DISCTMsg tMsg = query.getPmtTermCashDisc(glblCmpyCd, cMsg.pmtTermCashDiscCd.getValue());
            if (tMsg == null) {
                cMsg.pmtTermCashDiscCd.setErrorInfo(1, NSAM0045E, new String[] {"Payment Term" });
                valid = INPUT_IS_INVALID;
            // START 2016/02/09 T.Aoyagi [QC4081, MOD]
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscDescTxt, tMsg.pmtTermCashDiscDescTxt);
                // START 2017/03/08 K.Ochiai [QC#17453, ADD]
                if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(cMsg.pmtTermCashDiscCd.getValue())) {
                    if (!ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
                        cMsg.crCardCustRefNum.setErrorInfo(1, NSAM0189E, new String[] {"Payment Term:CC", "Credit Card Cust Ref Num" });
                        valid = INPUT_IS_INVALID;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
                        cMsg.pmtTermCashDiscCd.setErrorInfo(1, NSAM0189E, new String[] {"Credit Card Cust Ref Num", "Payment Term:CC" });
                        valid = INPUT_IS_INVALID;
                    }
                }
                // END 2017/03/08 K.Ochiai [QC#17453, ADD]
            }
            // END 2016/02/09 T.Aoyagi [QC4081, MOD]
        }
        // END 2015/12/25 T.Tomita [QC#2133, ADD]

        // add start 2016/07/01 CSA Defect#11261
        if (ZYPCommonFunc.hasValue(cMsg.mdseDescShortTxt_SP)) {
            S21SsmEZDResult svcPgmMdseInfo = query.getSvcPgmMdseInfo(glblCmpyCd, cMsg.svcPgmMdseCd.getValue());
            List<Map<String, String>> svcPgmMdseList = (List<Map<String, String>>) svcPgmMdseInfo.getResultObject();

            if (svcPgmMdseInfo.isCodeNotFound()) {
                cMsg.mdseDescShortTxt_SP.setErrorInfo(1, NSAM0072E, new String[] {"Service Program" });
                valid = INPUT_IS_INVALID;
            } else if (svcPgmMdseList.size() > 1) {
                cMsg.mdseDescShortTxt_SP.setErrorInfo(1, NSAM0418E, new String[] {"Service Program", "Service Program in detail" });
                valid = INPUT_IS_INVALID;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.svcPgmMdseCd, svcPgmMdseList.get(0).get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_SP, svcPgmMdseList.get(0).get("MDSE_DESC_SHORT_TXT"));
            }
            // Mod Start 2018/08/20 QC#26946
//            // START 2017/09/19 S.Fujita [QC#18534,ADD]
//            if (!NSXC001001ContrValidation.checkCsaWarranty(glblCmpyCd, cMsg.dsContrCatgCd.getValue(), cMsg.svcPgmMdseCd.getValue())) {
//                cMsg.mdseDescShortTxt_SP.setErrorInfo(1, NSAM0698E);
//                valid = INPUT_IS_INVALID;
//            }
//            // END 2017/09/19 S.Fujita [QC#18534,ADD]
            if (!NSAL0300CommonLogic.checkSvcPgmTp(glblCmpyCd, cMsg.dsContrCatgCd, cMsg.svcPgmMdseCd, cMsg.mdseDescShortTxt_SP)) {
                valid = INPUT_IS_INVALID;
            }
            // Mod End 2018/08/20 QC#26946
        }
        // add end 2016/07/01 CSA Defect#11261

        if (ZYPCommonFunc.hasValue(cMsg.contrAutoRnwTpCd) && !CONTR_AUTO_RNW_TP.DO_NOT_RENEW.equals(cMsg.contrAutoRnwTpCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd)) {
                cMsg.rnwPrcMethCd.setErrorInfo(1, NSAM0329E);
                valid = INPUT_IS_INVALID;
            }
        }

//        if (ZYPCommonFunc.hasValue(cMsg.contrUplftTpCd)) {
//            CONTR_UPLFT_TPTMsg tMsg = (CONTR_UPLFT_TPTMsg) ZYPCodeDataUtil.findByCode("CONTR_UPLFT_TP", glblCmpyCd, cMsg.contrUplftTpCd.getValue());
//            if (tMsg != null) {
//                if (ZYPConstant.FLG_ON_Y.equals(tMsg.uplftBaseFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(tMsg.uplftUsgFlg.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(cMsg.uplftPrcMethCd)) {
//                        cMsg.uplftPrcMethCd.setErrorInfo(1, NSAM0333E);
//                        valid = INPUT_IS_INVALID;
//                    }
//                }
//                if (ZYPConstant.FLG_ON_Y.equals(tMsg.uplftBaseFlg.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(cMsg.uplftBasePrcUpRatio)) {
//                        cMsg.uplftBasePrcUpRatio.setErrorInfo(1, NSAM0331E);
//                        valid = INPUT_IS_INVALID;
//                    }
//                }
//                if (ZYPConstant.FLG_ON_Y.equals(tMsg.uplftUsgFlg.getValue())) {
//                    if (!ZYPCommonFunc.hasValue(cMsg.uplftMtrPrcUpRatio)) {
//                        cMsg.uplftMtrPrcUpRatio.setErrorInfo(1, NSAM0332E);
//                        valid = INPUT_IS_INVALID;
//                    }
//                }
//            }
//        }

        if (ZYPCommonFunc.hasValue(cMsg.contrVrsnEffFromDt) && ZYPCommonFunc.hasValue(cMsg.contrVrsnEffThruDt)) {
            if (ZYPDateUtil.compare(cMsg.contrVrsnEffFromDt.getValue(), cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                cMsg.contrVrsnEffThruDt.setErrorInfo(1, NSAM0327E);
                valid = INPUT_IS_INVALID;
            }
        }

        if (!NSXC001001ContrValidation.checkAggAllocation(cMsg.dsContrCatgCd.getValue(), cMsg.prcAllocByMachQtyFlg.getValue())) {
            cMsg.prcAllocByMachQtyFlg.setErrorInfo(1, NSZM0666E);
            valid = INPUT_IS_INVALID;
        }

        if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
            if (!NSXC001001ContrValidation.checkAcctBillEligible(glblCmpyCd, slsDt, dsAcctNum, cMsg.altPayerCustCd.getValue(), ONBATCH_TYPE.ONLINE)) {
                cMsg.altPayerCustCd.setErrorInfo(1, NSZM0698E, new String[] {"Customer#", "Bill To Location" });
                valid = INPUT_IS_INVALID;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
            if (!NSXC001001ContrValidation.checkAcctBillEligible(glblCmpyCd, slsDt, dsAcctNum, cMsg.leaseCmpyCd.getValue(), ONBATCH_TYPE.ONLINE)) {
                cMsg.leaseCmpyCd.setErrorInfo(1, NSZM0698E, new String[] {"Customer#", "Lease" });
                valid = INPUT_IS_INVALID;
            }
        }

        // START 2016/02/12 T.Kanasaka [QC3889, MOD]
//        if (ZYPCommonFunc.hasValue(cMsg.xxMthDt_CC) && ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC) && !NSXC001001ContrValidation.checkCrCardPo(cMsg.custPoNum.getValue(), cMsg.xxYrDt_CC.getValue() + cMsg.xxYrDt_CC.getValue())) {
//            cMsg.custPoNum.setErrorInfo(1, NSAM0081E, new String[] {"PO Number", "CC Expire Date" });
//            cMsg.xxMthDt_CC.setErrorInfo(1, NSAM0081E, new String[] {"PO Number", "CC Expire Date" });
//            cMsg.xxYrDt_CC.setErrorInfo(1, NSAM0081E, new String[] {"PO Number", "CC Expire Date" });
//            valid = INPUT_IS_INVALID;
//        }
        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        if (!NSXC001001ContrValidation.checkPoExprDt(cMsg.custPoNum.getValue(), cMsg.poDt.getValue())) {
//            cMsg.custPoNum.setErrorInfo(1, NSAM0066E, new String[] {"PO Number", "PO Expire Date" });
//            cMsg.poDt.setErrorInfo(1, NSAM0066E, new String[] {"PO Number", "PO Expire Date" });
//            valid = INPUT_IS_INVALID;
//        }
        if (!NSXC001001ContrValidation.checkPoExprDt(cMsg.custPoNum.getValue(), cMsg.poFromDt.getValue(), cMsg.poDt.getValue())) {
            cMsg.custPoNum.setErrorInfo(1, NSAM0066E, new String[] {"PO Number", "PO Date" });
            cMsg.poFromDt.setErrorInfo(1, NSAM0066E, new String[] {"PO Number", "PO Date" });
            cMsg.poDt.setErrorInfo(1, NSAM0066E, new String[] {"PO Number", "PO Date" });
            valid = INPUT_IS_INVALID;
        }
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
        // END 2016/02/12 T.Kanasaka [QC3889, MOD]

        // START 2019/01/09 K.Kitachi [QC#26928, ADD]
        if (!NSXC001001ContrValidation.checkConsistentPoDt(cMsg.poFromDt.getValue(), cMsg.poDt.getValue())) {
            cMsg.poFromDt.setErrorInfo(1, NSAM0743E, new String[] {"Thru Date", "From Date" });
            cMsg.poDt.setErrorInfo(1, NSAM0743E, new String[] {"Thru Date", "From Date" });
            valid = INPUT_IS_INVALID;
        }

        if (!NSXC001001ContrValidation.checkDuplicatePoDt(glblCmpyCd, cMsg.dsContrCrCardPoPk_PO.getValue(), cMsg.dsContrPk.getValue(), null, null, MACH_LVL_NUM_1, cMsg.custPoNum.getValue(), cMsg.poFromDt.getValue(), cMsg.poDt.getValue())) {
            cMsg.poFromDt.setErrorInfo(1, NSAM0035E, new String[] {"PO Date" });
            cMsg.poDt.setErrorInfo(1, NSAM0035E, new String[] {"PO Date" });
            valid = INPUT_IS_INVALID;
        }
        // END 2019/01/09 K.Kitachi [QC#26928, ADD]

        if (!NSXC001001ContrValidation.checkBillToAndLease(cMsg.altPayerCustCd.getValue(), cMsg.leaseCmpyCd.getValue())) {
            cMsg.altPayerCustCd.setErrorInfo(1, NSZM0668E);
            cMsg.leaseCmpyCd.setErrorInfo(1, NSZM0668E);
            valid = INPUT_IS_INVALID;
        }

        if (!NSXC001001ContrValidation.checkLeaseChrg(cMsg.leaseCmpyCd.getValue(), cMsg.baseChrgToLeaseCmpyFlg.getValue(), cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
            cMsg.baseChrgToLeaseCmpyFlg.setErrorInfo(1, NSZM0665E);
            cMsg.usgChrgToLeaseCmpyFlg.setErrorInfo(1, NSZM0665E);
            valid = INPUT_IS_INVALID;
        }

        // START 2018/01/30 M.Naito [QC#21349, DEL]
//        if (!NSXC001001ContrValidation.checkInvSepTp(cMsg.dsContrCatgCd.getValue(), NSAL0300CommonLogic.switchFlg(cMsg.xxSelFlg_UT.getValue()))) {
//            cMsg.xxSelFlg_UT.setErrorInfo(1, NSZM0667E);
//            valid = INPUT_IS_INVALID;
//        }
        // END 2018/01/30 M.Naito [QC#21349, DEL]

        if (!NSXC001001ContrValidation.checkWarranty(cMsg.dsContrCatgCd.getValue(), cMsg.leaseCmpyCd.getValue())) {
            cMsg.leaseCmpyCd.setErrorInfo(1, NSZM0669E);
            valid = INPUT_IS_INVALID;
        }

        // START 2016/02/12 T.Kanasaka [QC3889, ADD]
        // START 2016/04/119 T.Kanasaka [QC3889, MOD]
//        if (!ZYPCommonFunc.hasValue(cMsg.custPoNum)) {
        if (!DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue()) && !ZYPCommonFunc.hasValue(cMsg.custPoNum)) {
        // END 2016/04/119 T.Kanasaka [QC3889, MOD]
            if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
                boolean poReq = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, cMsg.altPayerCustCd.getValue(), ONBATCH_TYPE.ONLINE);
                if (poReq) {
                    cMsg.custPoNum.setErrorInfo(1, NSAM0066E, new String[] {"PO required Bill To Location", "PO Number" });
                    valid = INPUT_IS_INVALID;
                }
            }

            // START 2017/09/20 K.Kim [QC#21211, DEL]
//            if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
//                boolean poReq = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, cMsg.leaseCmpyCd.getValue(), ONBATCH_TYPE.ONLINE);
//                if (poReq) {
//                    cMsg.custPoNum.setErrorInfo(1, NSAM0066E, new String[] {"PO required Lease", "PO Number" });
//                    valid = INPUT_IS_INVALID;
//                }
//            }
            // END 2017/09/20 K.Kim [QC#21211, DEL]
        }
        // END 2016/02/12 T.Kanasaka [QC3889, ADD]

        // del start 2016/09/23 CSA Defect#13686
        //if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd) && !ZYPCommonFunc.hasValue(cMsg.xxPsnNm_CP)) {
        //    cMsg.xxPsnNm_CP.setErrorInfo(1, NSAM0189E, new String[] {"Customer#", "Bill To Contact" });
        //    valid = INPUT_IS_INVALID;
        //}
        // del end 2016/09/23 CSA Defect#13686

        // Auto set and check Contact Person
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        if (!NSAL0300CommonLogic.checkAndSetCtacPsnPk(glblCmpyCd, cMsg.ctacPsnPk_CP, cMsg.xxPsnNm_CP)) {
//            cMsg.xxPsnNm_CP.setErrorInfo(1, NSAM0040E, new String[] {"Bill To Contact" });
//            valid = INPUT_IS_INVALID;
//        }
        if (!NSAL0300CommonLogic.checkAndSetCtacPsnPk(cMsg, glblCmpyCd, slsDt, cMsg.altPayerCustCd.getValue(), cMsg.ctacPsnFirstNm_CP, cMsg.ctacPsnLastNm_CP, cMsg.ctacPsnPk_CP)) {
            valid = INPUT_IS_INVALID;
        }
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]

        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_CP)) {
            if (!NSAL0300CommonLogic.checkCtacPsnReln(glblCmpyCd, cMsg.ctacPsnPk_CP.getValue(), cMsg.altPayerCustCd.getValue())) {
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                cMsg.xxPsnNm_CP.setErrorInfo(1, NSAM0138E, new String[] {"Bill To Contact", "Bill To Location" });
                cMsg.ctacPsnFirstNm_CP.setErrorInfo(1, NSAM0138E, new String[] {"Bill To Contact", "Bill To Location" });
                cMsg.ctacPsnLastNm_CP.setErrorInfo(1, NSAM0138E, new String[] {"Bill To Contact", "Bill To Location" });
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                valid = INPUT_IS_INVALID;
            }
        }

        // add start 2018/11/07 K.Fujimoto QC#28627
        //Parent Contract Check.
        if (ZYPCommonFunc.hasValue(cMsg.contrLinkNum) && !cMsg.dsContrNum.getValue().equals(cMsg.contrLinkNum.getValue())){
        	DS_CONTRTMsg parentDsContrTMsg = query.getDsContr(glblCmpyCd, cMsg.contrLinkNum.getValue());
            if(parentDsContrTMsg == null){
                cMsg.contrLinkNum.setErrorInfo(1, NSZM1355E, new String[] {cMsg.contrLinkNum.getValue()});
                valid = INPUT_IS_INVALID;
            } else {
                String dsContrStsCd = parentDsContrTMsg.dsContrStsCd.getValue();
                String parentDsContrNum = parentDsContrTMsg.dsContrNum.getValue();
                String parentContrLinkNum = parentDsContrTMsg.contrLinkNum.getValue();
                String parentAltPayerCustCd = parentDsContrTMsg.altPayerCustCd.getValue();

                if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_STS.TERMINATED.equals(dsContrStsCd)) {
                    cMsg.contrLinkNum.setErrorInfo(1, NSZM1356E, new String[] {parentDsContrNum});
                    valid = INPUT_IS_INVALID;
                } else if (ZYPCommonFunc.hasValue(parentContrLinkNum) && !parentDsContrNum.equals(parentContrLinkNum)) {
                    cMsg.contrLinkNum.setErrorInfo(1, NSZM1357E);
                    valid = INPUT_IS_INVALID;
                } else if (!parentAltPayerCustCd.equals(cMsg.altPayerCustCd.getValue())) {
                    cMsg.contrLinkNum.setErrorInfo(1, NSZM1358E, new String[] {cMsg.dsContrNum.getValue(),parentDsContrNum});
                    valid = INPUT_IS_INVALID;
                }
            }
        }
        // START 2018/12/13 [QC#29079, MOD]
        //Child Contract Check.
        // if(ZYPCommonFunc.hasValue(cMsg.dsContrNum.getValue())){
        if(ZYPCommonFunc.hasValue(cMsg.dsContrPk) && ZYPCommonFunc.hasValue(cMsg.dsContrNum.getValue())){
        // END 2018/12/13 [QC#29079, MOD]
            DS_CONTRTMsg curretDsContrTMsg = query.getDsContr(glblCmpyCd, cMsg.dsContrNum.getValue());
            String currentDsContrNum = curretDsContrTMsg.dsContrNum.getValue();
            String currentContrLinkNum = curretDsContrTMsg.contrLinkNum.getValue();
            
            if (curretDsContrTMsg != null && !cMsg.contrLinkNum.getValue().equals(currentContrLinkNum) && currentDsContrNum.equals(currentContrLinkNum)){
               BigDecimal count = query.countChildDsContr(glblCmpyCd, cMsg.dsContrNum.getValue(),curretDsContrTMsg.contrLinkNum.getValue());
                if (count.compareTo(BigDecimal.ZERO) > 0) {
                    cMsg.contrLinkNum.setErrorInfo(1, NSZM1359E);
                    return false;
                }
                return true;
            }
            
        }
        // add End  2018/11/07 K.Fujimoto QC#28627
        
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrStsCd)) {
            return valid;
        }

        // add start 2016/09/06 CSA Defect#11243
        // Check Credit Card#
        if (ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
            DS_CR_CARDTMsg tMsg = query.getDsCrCard(glblCmpyCd, cMsg.crCardCustRefNum.getValue(), cMsg.sellToCustCd.getValue());
            if (tMsg == null) {
                cMsg.crCardCustRefNum.setErrorInfo(1, NSAM0045E, new String[] {"Entered Reference#" });
                valid = INPUT_IS_INVALID;
            } else {
                String crCardExprYrMth = tMsg.crCardExprYrMth.getValue();
                setValue(cMsg.xxMthDt_CC, crCardExprYrMth.substring(4, 6));
                setValue(cMsg.xxYrDt_CC, crCardExprYrMth.substring(0, 4));
                if (!ZYPConstant.FLG_ON_Y.equals(tMsg.crCardValidFlg.getValue())) {
                    cMsg.crCardCustRefNum.setErrorInfo(1, NSAM0040E, new String[] {"Entered Reference#" });
                    valid = INPUT_IS_INVALID;
                }
            }
        }
        // add end 2016/09/06 CSA Defect#11243

        // START 2016/01/29 T.Iwamoto [QC#3807, DEL]
//        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
//        if (ZYPCommonFunc.hasValue(cMsg.dsContrPk) && DS_CONTR_STS.DRAFT.equals(cMsg.dsContrStsCd.getValue()) && !userId.equals(cMsg.dsContrCratPsnCd.getValue())) {
//            cMsg.setMessageInfo(NSAM0383E);
//            valid = INPUT_IS_INVALID;
//        }
        // END 2016/01/29 T.Iwamoto [QC#3807, DEL]

        if (ZYPCommonFunc.hasValue(cMsg.contrAutoRnwTpCd) && !NSXC001001ContrValidation.checkAutoRnwMeth(glblCmpyCd, cMsg.contrAutoRnwTpCd.getValue(), cMsg.rnwPrcMethCd.getValue())) {
            cMsg.rnwPrcMethCd.setErrorInfo(1, NSAM0081E, new String[] {"Renwewal Type", "Renwewal Method" });
            valid = INPUT_IS_INVALID;
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd)) {
                if (!NSXC001001ContrValidation.checkBasePrcUpRatioMandatory(glblCmpyCd, cMsg.contrAutoRnwTpCd.getValue(), cMsg.rnwPrcMethCd.getValue(), cMsg.basePrcUpRatio.getValue())) {
                    cMsg.basePrcUpRatio.setErrorInfo(1, NSAM0081E, new String[] {ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT), "Renwewal Base Price Up Ratio" });
                    valid = INPUT_IS_INVALID;
                }

                if (!NSXC001001ContrValidation.checkBasePrcUpRatioNotAcceptable(glblCmpyCd, cMsg.contrAutoRnwTpCd.getValue(), cMsg.rnwPrcMethCd.getValue(), cMsg.basePrcUpRatio.getValue())) {
                    cMsg.basePrcUpRatio.setErrorInfo(1, NSZM0684E, new String[] {"Renwewal Base Price Up Ratio", ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT)});
                    valid = INPUT_IS_INVALID;
                }

                if (!NSXC001001ContrValidation.checkUsgPrcUpRatioMandatory(glblCmpyCd, cMsg.contrAutoRnwTpCd.getValue(), cMsg.rnwPrcMethCd.getValue(), cMsg.mtrPrcUpRatio.getValue())) {
                    cMsg.mtrPrcUpRatio.setErrorInfo(1, NSAM0081E, new String[] {ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT), "Renwewal Meter Price Up Ratio" });
                    valid = INPUT_IS_INVALID;
                }

                if (!NSXC001001ContrValidation.checkUsgPrcUpRatioNotAcceptable(glblCmpyCd, cMsg.contrAutoRnwTpCd.getValue(), cMsg.rnwPrcMethCd.getValue(), cMsg.mtrPrcUpRatio.getValue())) {
                    cMsg.mtrPrcUpRatio.setErrorInfo(1, NSZM0684E, new String[] {"Renwewal Meter Price Up Ratio", ZYPCodeDataUtil.getName(RNW_PRC_METH.class, glblCmpyCd, RNW_PRC_METH.MARKUP_PERCENT)});
                    valid = INPUT_IS_INVALID;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.contrUplftTpCd) && !CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(cMsg.contrUplftTpCd.getValue()) && !ZYPCommonFunc.hasValue(cMsg.uplftPrcMethCd)) {
            cMsg.uplftPrcMethCd.setErrorInfo(1, NSAM0081E, new String[] {"Upliftment Type", "Upliftment Method" });
            valid = INPUT_IS_INVALID;
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.uplftPrcMethCd)) {
                if (!NSXC001001ContrValidation.checkBaseUplftRatioMandatory(glblCmpyCd, cMsg.contrUplftTpCd.getValue(), cMsg.uplftPrcMethCd.getValue(), cMsg.uplftBasePrcUpRatio.getValue())) {
                    cMsg.uplftBasePrcUpRatio.setErrorInfo(1, NSAM0081E, new String[] {ZYPCodeDataUtil.getName(UPLFT_PRC_METH.class, glblCmpyCd, UPLFT_PRC_METH.MARKUP_PERCENT), "Uplift Base Price Up Ratio" });
                    valid = INPUT_IS_INVALID;
                }

                if (!NSXC001001ContrValidation.checkBaseUplftRatioNotAcceptable(glblCmpyCd, cMsg.contrUplftTpCd.getValue(), cMsg.uplftPrcMethCd.getValue(), cMsg.uplftBasePrcUpRatio.getValue())) {
                    cMsg.uplftBasePrcUpRatio.setErrorInfo(1, NSZM0684E, new String[] {"Uplift Base Price Up Ratio", ZYPCodeDataUtil.getName(UPLFT_PRC_METH.class, glblCmpyCd, UPLFT_PRC_METH.MARKUP_PERCENT) });
                    valid = INPUT_IS_INVALID;
                }

                if (!NSXC001001ContrValidation.checkUsgUplftRatioMandatory(glblCmpyCd, cMsg.contrUplftTpCd.getValue(), cMsg.uplftPrcMethCd.getValue(), cMsg.uplftMtrPrcUpRatio.getValue())) {
                    cMsg.uplftMtrPrcUpRatio.setErrorInfo(1, NSAM0081E, new String[] {ZYPCodeDataUtil.getName(UPLFT_PRC_METH.class, glblCmpyCd, UPLFT_PRC_METH.MARKUP_PERCENT), "Uplift Meter Price Up Ratio" });
                    valid = INPUT_IS_INVALID;
                }

                if (!NSXC001001ContrValidation.checkUsgUplftRatioNotAcceptable(glblCmpyCd, cMsg.contrUplftTpCd.getValue(), cMsg.uplftPrcMethCd.getValue(), cMsg.uplftMtrPrcUpRatio.getValue())) {
                    cMsg.uplftMtrPrcUpRatio.setErrorInfo(1, NSZM0684E, new String[] {"Uplift Meter Price Up Ratio", ZYPCodeDataUtil.getName(UPLFT_PRC_METH.class, glblCmpyCd, UPLFT_PRC_METH.MARKUP_PERCENT) });
                    valid = INPUT_IS_INVALID;
                }
            }
        }

//        if (cMsg.A.getValidCount() == 0) {
//            cMsg.setMessageInfo(NSAM0385E);
//            valid = INPUT_IS_INVALID;
//        }

        // START 2016/02/10 T.Kanasaka [QC30558, ADD]
        NSAL0300CommonLogic.setItemForSave(cMsg);
        // END 2016/02/10 T.Kanasaka [QC30558, ADD]

        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            // Auto set for Aggregate
            // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
            //NSAL0300CommonLogic.setAggregateItem(cMsg);
            NSAL0300CommonLogic.setAggregateItem(cMsg, glblCmpyCd);
            // END  2019/05/13 K.Fujimoto [31137/50058, MOD]
        }

        BigDecimal preDsContrBllgMtrPk_B = new BigDecimal(-1);
        // START 2017/07/27 [QC#16889, MOD]
        // String preSerNum = INVLD_SER_NUM;
        // String preMdseCd = INVLD_MDSE_CD;
        BigDecimal preDsContrDtlPk = BigDecimal.ONE.negate();
        // END 2017/07/27 [QC#16889, MOD]
        Map<String, Integer> tierCntMap = new HashMap<String, Integer>();
        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            tierCntMap = getTierCntMap(cMsg.B);
        }

        // START 2016/02/16 T.Kanasaka [QC2579, ADD]
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NSAL0300_ASMsg asMsg = sMsg.A.no(i);

                if (ZYPDateUtil.compare(asMsg.contrEffFromDt_A.getValue(), asMsg.contrEffThruDt_A.getValue()) > 0) {
                    NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                    if (findACMsg != null) {
                        findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0327E);
                    }
                    valid = INPUT_IS_INVALID;
                }

                // Add Start 2016/11/10 <QC#15888>
                BigDecimal fleetDsContrDtlPk = null;
                String invoicedFromDt = null;
                String invoicedThruDt = null;
                if (cMsg.B.getValidCount() > 0 && DS_CONTR_DTL_TP.FLEET.equals(cMsg.B.no(0).dsContrDtlTpCd_B.getValue())) {
                    fleetDsContrDtlPk =cMsg.B.no(0).dsContrDtlPk_B.getValue();
                }
                S21SsmEZDResult rslt = query.getInvoicedContrInfo(glblCmpyCd, fleetDsContrDtlPk);
                if (rslt != null && rslt.isCodeNormal()) {
                    List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
                    if (rslt.getQueryResultCount() > 0) {
                        Map<String, String> rsltMap = rsltList.get(0);
                        invoicedFromDt = rsltMap.get("INVOICED_FROM_DT");
                        invoicedThruDt = rsltMap.get("INVOICED_THRU_DT");
                    }
                }
                BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk_A.getValue();
                DS_CONTR_DTLTMsg curDtlTMsg = NSAL0300Query.getInstance().getDsContrDtlForRead(glblCmpyCd, dsContrDtlPk);
                if (ZYPCommonFunc.hasValue(invoicedFromDt)) {
                    String fromDt = asMsg.contrEffFromDt_A.getValue();
                    String thruDt = asMsg.contrEffThruDt_A.getValue();
                    if (curDtlTMsg == null) {
                        if (ZYPCommonFunc.hasValue(fromDt) && fromDt.compareTo(invoicedThruDt) <= 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSAM0346E, new String [] {"Start Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt)});
                            }
                            valid = INPUT_IS_INVALID;
                        }
                        // START 2017/03/17 K.Kitachi [QC#17948, MOD]
                        if (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(invoicedThruDt) < 0) {
                        // END 2017/03/17 K.Kitachi [QC#17948, MOD]
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0346E, new String [] {"End Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt)});
                            }
                            valid = INPUT_IS_INVALID;
                        }
                    } else {
                        if (ZYPCommonFunc.hasValue(fromDt) && fromDt.compareTo(invoicedFromDt) >= 0 && fromDt.compareTo(invoicedThruDt) <= 0) {
                            if (fromDt.compareTo(curDtlTMsg.contrEffFromDt.getValue()) != 0) {
                                NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                                if (findACMsg != null) {
                                    findACMsg.contrEffFromDt_A.setErrorInfo(1, NSAM0387E);
                                }
                                valid = INPUT_IS_INVALID;
                            }
                        }
                        // START 2017/03/17 K.Kitachi [QC#17948, MOD]
                        if (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(invoicedThruDt) < 0) {
                        // END 2017/03/17 K.Kitachi [QC#17948, MOD]
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0346E, new String [] {"End Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt)});
                            }
                            valid = INPUT_IS_INVALID;
                        }
                    }
                }
                // Add End   2016/11/10 <QC#15888>
                if (DS_CONTR_DTL_TP.ACCESSORIES.equals(sMsg.A.no(i).dsContrDtlTpCd_A.getValue())) {
                    // Accessory
                    NSAL0300_ASMsg parentASMsg = NSAL0300CommonLogic.getParentASMsg(sMsg.A, asMsg.prntDsContrDtlPk_A.getValue());
                    if (parentASMsg != null) {
                        if (ZYPCommonFunc.hasValue(parentASMsg.contrEffFromDt_A) && asMsg.contrEffFromDt_A.getValue().compareTo(parentASMsg.contrEffFromDt_A.getValue()) < 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0661E);
                            }
                            valid = INPUT_IS_INVALID;
                        } else if (ZYPCommonFunc.hasValue(asMsg.contrEffThruDt_A) && asMsg.contrEffThruDt_A.getValue().compareTo(parentASMsg.contrEffThruDt_A.getValue()) > 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0661E);
                            }
                            valid = INPUT_IS_INVALID;
                        }
                    }
                } else {
                    // Machine
                    if (ZYPCommonFunc.hasValue(asMsg.contrEffFromDt_A) && asMsg.contrEffFromDt_A.getValue().compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0) {
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                        if (findACMsg != null) {
                            findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0660E);
                            valid = INPUT_IS_INVALID;
                        }
                    }

                    if (ZYPCommonFunc.hasValue(asMsg.contrEffThruDt_A) && asMsg.contrEffThruDt_A.getValue().compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                        if (findACMsg != null) {
                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0660E);
                            valid = INPUT_IS_INVALID;
                        }
                    }
                    // START 2018/06/07 K.Kim [QC#24857, ADD]
                    if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(sMsg.A.no(i).dsContrDtlTpCd_A.getValue())) {
                        BigDecimal countBllgMtr = query.countBllgMtr(glblCmpyCd, dsContrDtlPk);
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                        if (findACMsg != null && !ZYPCommonFunc.hasValue(findACMsg.mtrReadMethCd_A) && countBllgMtr.compareTo(BigDecimal.ZERO) != 0) {
                            findACMsg.mtrReadMethCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Meter Method"});
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        }
                    }
                    // END 2018/06/07 K.Kim [QC#24857, ADD]
                }

                // START 2018/06/04 K.Kim [QC#15410(Sol#246),DEL]
//                if (ZYPCommonFunc.hasValue(asMsg.svcMachMstrPk_A) && ZYPCommonFunc.hasValue(asMsg.dsContrDtlPk_A)) {
//                    // Mod Start 2018/06/03 QC#26379
//                    String thruDt = asMsg.contrEffThruDt_A.getValue();
//                    if (ZYPCommonFunc.hasValue(asMsg.contrCloDt_A)) {
//                        thruDt = asMsg.contrCloDt_A.getValue();
//                    }
//                    if (!NSXC001001ContrValidation.checkDuplicateContr(glblCmpyCd, cMsg.dsContrCatgCd.getValue(), asMsg.svcMachMstrPk_A.getValue(), asMsg.dsContrDtlPk_A.getValue(), asMsg.contrEffFromDt_A.getValue(), thruDt)) {
//                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
//                        if (findACMsg != null) {
//                            findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0662E);
//                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0662E);
//                        }
//                        valid = INPUT_IS_INVALID;
//                    }
//                    // Mod End 2018/06/03 QC#26379
//                }
                // END 2018/06/04 K.Kim [QC#15410(Sol#246),DEL]
            }
        }
        // END 2016/02/16 T.Kanasaka [QC2579, ADD]
        // Add Start 2019/02/14 QC#30295
        BigDecimal nextDsContrBllgMtrPk_B = null;
        Map<String, BigDecimal> aggLineMtrCtacPsnPkMap = new HashMap<String, BigDecimal>();
        List<BigDecimal> errDsContrBllgMtrPkList = new ArrayList<BigDecimal>();
        // Add End 2019/02/14 QC#30295
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            String serNum = bCMsg.serNum_B.getValue();
            String mdseCd = bCMsg.mdseCd_B.getValue();
            String dsContrDtlTpCd = bCMsg.dsContrDtlTpCd_B.getValue();
            BigDecimal dsContrDtlPk = bCMsg.dsContrDtlPk_B.getValue();

            // START 2018/10/24 K.Kitachi [QC#28889, ADD]
            if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && !NSAL0300CommonLogic.checkAndSetCtacPsnPk(cMsg, glblCmpyCd, slsDt, bCMsg.baseBillToCustCd_B.getValue(), bCMsg.ctacPsnFirstNm_BB, bCMsg.ctacPsnLastNm_BB, bCMsg.ctacPsnPk_BB)) {
                addErroredDsContrDtlPk(dsContrDtlPk);
                valid = INPUT_IS_INVALID;
            }
            // END 2018/10/24 K.Kitachi [QC#28889, ADD]

            boolean aggMachFlg = false;
            if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                aggMachFlg = true;
            }

            if (!aggMachFlg) {
                // Auto set and check Contact Person
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && !NSAL0300CommonLogic.checkAndSetCtacPsnPk(glblCmpyCd, bCMsg.ctacPsnPk_BB, bCMsg.xxPsnNm_BB)) {
//                    bCMsg.xxPsnNm_BB.setErrorInfo(1, NSAM0040E, new String[] {"Contact" });
//                    addErroredDsContrDtlPk(dsContrDtlPk);
//                    valid = INPUT_IS_INVALID;
//                }
                // START 2018/10/24 K.Kitachi [QC#28889, DEL]
//                if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && !NSAL0300CommonLogic.checkAndSetCtacPsnPk(cMsg, glblCmpyCd, slsDt, bCMsg.baseBillToCustCd_B.getValue(), bCMsg.ctacPsnFirstNm_BB, bCMsg.ctacPsnLastNm_BB, bCMsg.ctacPsnPk_BB)) {
//                    addErroredDsContrDtlPk(dsContrDtlPk);
//                    valid = INPUT_IS_INVALID;
//                }
                // END 2018/10/24 K.Kitachi [QC#28889, DEL]
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]

                if (ZYPCommonFunc.hasValue(bCMsg.dsContrBllgMtrPk_B)) {
                    if (!DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd) && !ZYPCommonFunc.hasValue(bCMsg.xsMtrCopyQty_B)) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, ZZM9000E, new String[] {"Allowance" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    if (!(DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) && !ZYPCommonFunc.hasValue(bCMsg.xsMtrAmtRate_B)) {
                        bCMsg.xsMtrAmtRate_B.setErrorInfo(1, ZZM9000E, new String[] {"Price" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                }

                // START 2017/07/27 [QC#16889, MOD]
                // if (!NSAL0300CommonLogic.isEqualMach(preSerNum, preMdseCd, serNum, mdseCd)) {
                if (!NSAL0300CommonLogic.isEqualNum(preDsContrDtlPk, dsContrDtlPk)) {
                // END 2017/07/27 [QC#16889, MOD]
                    if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B)) {
                        if (ZYPDateUtil.compare(bCMsg.contrEffFromDt_B.getValue(), bCMsg.contrEffThruDt_B.getValue()) > 0) {
                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSAM0327E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;

                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0327E);
                            }
                        }
                    }

                    // mod start 2016/09/23 CSA Defect#13686
                    //if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.baseBillToCustCd_B) && !ZYPCommonFunc.hasValue(bCMsg.xxPsnNm_BB)) {
                    //    bCMsg.xxPsnNm_BB.setErrorInfo(1, NSAM0189E, new String[] {"Bill To", "Contact" });
                    //    addErroredDsContrDtlPk(dsContrDtlPk);
                    //    valid = INPUT_IS_INVALID;
                    //} else 
                    if (!NSAL0300CommonLogic.checkCtacPsnReln(glblCmpyCd, bCMsg.ctacPsnPk_BB.getValue(), bCMsg.baseBillToCustCd_B.getValue())) {
                        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                        bCMsg.xxPsnNm_BB.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
                        bCMsg.ctacPsnFirstNm_BB.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
                        bCMsg.ctacPsnLastNm_BB.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
                        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // mod end 2016/09/23 CSA Defect#13686

//                    if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && !NSXC001001ContrValidation.checkBllgCycle(glblCmpyCd, bCMsg.baseBllgCycleCd_B.getValue(), bCMsg.contrCloDay_B.getValue())) {
//                        String cycleNm = ZYPCodeDataUtil.getName(BLLG_CYCLE.class, glblCmpyCd, bCMsg.baseBllgCycleCd_B.getValue());
//                        bCMsg.contrCloDay_B.setErrorInfo(1, NSZM0657E, new String[] {cycleNm });
//                        valid = INPUT_IS_INVALID;
//                    }

//                    if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !NSXC001001ContrValidation.checkBllgCycle(glblCmpyCd, bCMsg.bllgMtrBllgCycleCd_B.getValue(), bCMsg.mtrCloDay_B.getValue())) {
//                        String cycleNm = ZYPCodeDataUtil.getName(BLLG_CYCLE.class, glblCmpyCd, bCMsg.bllgMtrBllgCycleCd_B.getValue());
//                        bCMsg.mtrCloDay_B.setErrorInfo(1, NSZM0657E, new String[] {cycleNm });
//                        valid = INPUT_IS_INVALID;
//                    }

                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue()) && !NSXC001001ContrValidation.checkBaseAndUsgCloDay(glblCmpyCd, bCMsg.dsContrDtlTpCd_B.getValue(), bCMsg.contrCloDay_B.getValue(), bCMsg.mtrCloDay_B.getValue())) {
                        // START 2016/05/17 T.Kanasaka [QC#2184, MOD]
                        bCMsg.baseDplyPerEndDay_B.setErrorInfo(1, NSZM0658E);
                        bCMsg.mtrDplyPerEndDay_B.setErrorInfo(1, NSZM0658E);
                        // END 2016/05/17 T.Kanasaka [QC#2184, MOD]
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue()) && !NSXC001001ContrValidation.checkBaseAndUsgBllgDay(glblCmpyCd, bCMsg.dsContrDtlTpCd_B.getValue(), bCMsg.contrBllgDay_B.getValue(), bCMsg.mtrBllgDay_B.getValue())) {
                        bCMsg.contrBllgDay_B.setErrorInfo(1, NSZM0659E);
                        bCMsg.mtrBllgDay_B.setErrorInfo(1, NSZM0659E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    // START 2018/06/18 K.Kim [QC#25195, MOD]
                    // add start 2016/07/01 CSA Defect#11261
                    // if (ZYPCommonFunc.hasValue(bCMsg.mdseDescShortTxt_B)) {
                    if (ZYPCommonFunc.hasValue(bCMsg.svcPgmMdseCd_B)) {
                        S21SsmEZDResult svcPgmMdseInfo = query.getSvcPgmMdseInfo(glblCmpyCd, bCMsg.svcPgmMdseCd_B.getValue());
                        List<Map<String, String>> svcPgmMdseList = (List<Map<String, String>>) svcPgmMdseInfo.getResultObject();

                        if (svcPgmMdseInfo.isCodeNotFound()) {
                            // bCMsg.mdseDescShortTxt_B.setErrorInfo(1, NSAM0072E, new String[] {"Service Program" });
                            bCMsg.svcPgmMdseCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Service Program" });
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        } else if (svcPgmMdseList.size() > 1) {
                            // bCMsg.mdseDescShortTxt_B.setErrorInfo(1, NSAM0418E, new String[] {"Service Program", "Service Program in detail" });
                            bCMsg.svcPgmMdseCd_B.setErrorInfo(1, NSAM0418E, new String[] {"Service Program", "Service Program in detail" });
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        } else {
                            ZYPEZDItemValueSetter.setValue(bCMsg.svcPgmMdseCd_B, svcPgmMdseList.get(0).get("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(bCMsg.mdseDescShortTxt_B, svcPgmMdseList.get(0).get("MDSE_DESC_SHORT_TXT"));
                        }
                        // Mod Start 2018/08/20 QC#26946
//                        // START 2017/09/19 S.Fujita [QC#18534,ADD]
//                        if (!NSXC001001ContrValidation.checkCsaWarranty(glblCmpyCd, cMsg.dsContrCatgCd.getValue(), bCMsg.svcPgmMdseCd_B.getValue())) {
//                            // bCMsg.mdseDescShortTxt_B.setErrorInfo(1, NSAM0698E);
//                            bCMsg.svcPgmMdseCd_B.setErrorInfo(1, NSAM0698E);
//                            valid = INPUT_IS_INVALID;
//                        }
//                        // END 2017/09/19 S.Fujita [QC#18534,ADD]
                        if (!NSAL0300CommonLogic.checkSvcPgmTp(glblCmpyCd, cMsg.dsContrCatgCd, bCMsg.svcPgmMdseCd_B, bCMsg.svcPgmMdseCd_B)) {
                            valid = INPUT_IS_INVALID;
                        }
                        // Mod End 2018/08/20 QC#26946
                    }
                    // add end 2016/07/01 CSA Defect#11261
                    // END 2018/06/18 K.Kim [QC#25195, MOD]

                    if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                        S21SsmEZDResult rslt = query.getInvoicedContrInfo(glblCmpyCd, dsContrDtlPk);
                        if (rslt != null && rslt.isCodeNormal()) {
                            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
                            if (rslt.getQueryResultCount() > 0) {
                                Map<String, String> rsltMap = rsltList.get(0);
                                String contrCloDay = rsltMap.get("CONTR_CLO_DAY");
                                String mtrCloDay = rsltMap.get("MTR_CLO_DAY");
                                String contrEffFromDt = rsltMap.get("CONTR_EFF_FROM_DT");
                                // Add Start 2018/01/17 QC#18552
                                String baseBllgTmgCd = rsltMap.get("BASE_BLLG_TMG_CD");
                                if (ZYPCommonFunc.hasValue(bCMsg.baseBllgTmgCd_B) && !bCMsg.baseBllgTmgCd_B.getValue().equals(baseBllgTmgCd)) {
                                    bCMsg.baseBllgTmgCd_B.setErrorInfo(1, NSAM0387E);
                                    addErroredDsContrDtlPk(dsContrDtlPk);
                                    valid = INPUT_IS_INVALID;
                                }
                                // Add End 2018/01/17 QC#18552
                                // START 2016/02/18 T.Tomita [QC#3188, MOD]
                                // START 2016/05/17 T.Kanasaka [QC#2184, MOD]
                                if (ZYPCommonFunc.hasValue(bCMsg.contrCloDay_B) && !bCMsg.contrCloDay_B.getValue().equals(contrCloDay)) {
                                    bCMsg.baseDplyPerEndDay_B.setErrorInfo(1, NSAM0387E);
                                    addErroredDsContrDtlPk(dsContrDtlPk);
                                    valid = INPUT_IS_INVALID;
                                }
                                if (ZYPCommonFunc.hasValue(bCMsg.mtrCloDay_B) && !bCMsg.mtrCloDay_B.getValue().equals(mtrCloDay)) {
                                    bCMsg.mtrDplyPerEndDay_B.setErrorInfo(1, NSAM0387E);
                                    addErroredDsContrDtlPk(dsContrDtlPk);
                                    valid = INPUT_IS_INVALID;
                                }
                                // END 2016/05/17 T.Kanasaka [QC#2184, MOD]
                                // END 2016/02/18 T.Tomita [QC#3188, MOD]
                                if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && ZYPDateUtil.compare(bCMsg.contrEffFromDt_B.getValue(), contrEffFromDt) != 0) {
                                    bCMsg.contrEffFromDt_B.setErrorInfo(1, NSAM0387E);
                                    // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                    NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                    if (findACMsg != null) {
                                        findACMsg.contrEffFromDt_A.setErrorInfo(1, NSAM0387E);
                                    }
                                    // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                    addErroredDsContrDtlPk(dsContrDtlPk);
                                    valid = INPUT_IS_INVALID;
                                }
                                // Add Start 08/02/2016 <QC#7402>
                                BigDecimal invoicedBaseTermAmtRate = query.getInvoicedBaseTermAmtRate(glblCmpyCd, dsContrDtlPk);
                                if (ZYPCommonFunc.hasValue(bCMsg.basePrcTermDealAmtRate_B) && BigDecimal.ZERO.compareTo(invoicedBaseTermAmtRate) < 0) {
                                    if (invoicedBaseTermAmtRate.compareTo(bCMsg.basePrcTermDealAmtRate_B.getValue()) > 0) {
                                        bCMsg.basePrcTermDealAmtRate_B.setErrorInfo(1, NSAM0600E, new String[] {invoicedBaseTermAmtRate.toString()});
                                        addErroredDsContrDtlPk(dsContrDtlPk);
                                        valid = INPUT_IS_INVALID;
                                    }
                                }
                                //getInvoicedBaseTermAmtRate
                                // Add End   08/02/2016 <QC#7402>
                                // Add Start 2016/11/10 <QC#15888>
                                String invoicedThruDt = rsltMap.get("INVOICED_THRU_DT");
                                if (ZYPCommonFunc.hasValue(invoicedThruDt)) {
                                    String thruDt = bCMsg.contrEffThruDt_B.getValue();
                                    // START 2017/03/17 K.Kitachi [QC#17948, MOD]
                                    if (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(invoicedThruDt) < 0) {
                                    // END 2017/03/17 K.Kitachi [QC#17948, MOD]
                                        bCMsg.contrEffThruDt_B.setErrorInfo(1, NSAM0346E, new String[] {"End Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt) });
                                        // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                        if (findACMsg != null) {
                                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0346E, new String[] {"End Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt) });
                                        }
                                        // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                        addErroredDsContrDtlPk(dsContrDtlPk);
                                        valid = INPUT_IS_INVALID;
                                    }
                                }
                                // Add End   2016/11/10 <QC#15888>
                            }
                        }
                    }

                    // START 2018/06/04 K.Kim [QC#15410(Sol#246),DEL]
//                    if (ZYPCommonFunc.hasValue(bCMsg.svcMachMstrPk_B) && ZYPCommonFunc.hasValue(dsContrDtlPk)) {
//                        // Mod Start 2018/06/03 QC#26379
//                        String thruDt = bCMsg.contrEffThruDt_B.getValue();
//                        if (ZYPCommonFunc.hasValue(bCMsg.contrCloDt_B)) {
//                            thruDt = bCMsg.contrCloDt_B.getValue();
//                        }
//                        if (!NSXC001001ContrValidation.checkDuplicateContr(glblCmpyCd, cMsg.dsContrCatgCd.getValue(), bCMsg.svcMachMstrPk_B.getValue(), dsContrDtlPk, bCMsg.contrEffFromDt_B.getValue(), thruDt)) {
//                            bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0662E);
//                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0662E);
//                            // START 2018/03/15 K.Kojima [QC#24804,ADD]
//                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
//                            if (findACMsg != null) {
//                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0662E);
//                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0662E);
//                            }
//                            // END 2018/03/15 K.Kojima [QC#24804,ADD]
//                            addErroredDsContrDtlPk(dsContrDtlPk);
//                            valid = INPUT_IS_INVALID;
//                        }
//                        // Mod End 2018/06/03 QC#26379
//                    }
                    // END 2018/06/04 K.Kim [QC#15410(Sol#246),DEL]

                    // START 2016/01/18 T.Tomita [QC#2948, DEL]
//                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue()) && !NSXC001001ContrValidation.checkLeaseBillTo(cMsg.leaseCmpyCd.getValue(), bCMsg.baseBillToCustCd_B.getValue())) {
//                        bCMsg.baseBillToCustCd_B.setErrorInfo(1, NSZM0664E);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // END 2016/01/18 T.Tomita [QC#2948, DEL]

                    if (ZYPCommonFunc.hasValue(bCMsg.baseBillToCustCd_B)) {
                        if (!NSXC001001ContrValidation.checkAcctBillEligible(glblCmpyCd, slsDt, dsAcctNum, bCMsg.baseBillToCustCd_B.getValue(), ONBATCH_TYPE.ONLINE)) {
                            bCMsg.baseBillToCustCd_B.setErrorInfo(1, NSZM0698E, new String[] {"Customer#", "Bill To" });
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        }

                        // START 2016/02/16 T.Kanasaka [QC3889, ADD]
                        // START 2016/04/119 T.Kanasaka [QC3889, MOD]
//                        if (!ZYPCommonFunc.hasValue(cMsg.custPoNum)) {
                        if (!DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue()) && !ZYPCommonFunc.hasValue(cMsg.custPoNum)) {
                            // START 2017/09/20 K.Kim [QC#21211, ADD]
                            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                            // END 2017/09/20 K.Kim [QC#21211, ADD]
                        // END 2016/04/119 T.Kanasaka [QC3889, MOD]
                                boolean poReq = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, bCMsg.baseBillToCustCd_B.getValue(), ONBATCH_TYPE.ONLINE);
                                if (poReq) {
                                    cMsg.custPoNum.setErrorInfo(1, NSAM0066E, new String[] {"PO required Bill To Location", "PO Number" });
                                    valid = INPUT_IS_INVALID;
                                }
                            }
                        }
                        // END 2016/02/16 T.Kanasaka [QC3889, ADD]
                    }

                    if (ZYPConstant.FLG_ON_Y.equals(bCMsg.xxDplyCtrlFlg_B0.getValue())) {
                        // Accessory
                        NSAL0300_BCMsg parentBCMsg = NSAL0300CommonLogic.getParentBCMsg(cMsg.B, bCMsg.prntDsContrDtlPk_B.getValue());
                        if (parentBCMsg != null) {
                            if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && bCMsg.contrEffFromDt_B.getValue().compareTo(parentBCMsg.contrEffFromDt_B.getValue()) < 0) {
                                bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0661E);
                                // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                if (findACMsg != null) {
                                    findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0661E);
                                }
                                // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                addErroredDsContrDtlPk(dsContrDtlPk);
                                valid = INPUT_IS_INVALID;
                            } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B) && bCMsg.contrEffThruDt_B.getValue().compareTo(parentBCMsg.contrEffThruDt_B.getValue()) > 0) {
                                bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0661E);
                                // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                if (findACMsg != null) {
                                    findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0661E);
                                }
                                // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                addErroredDsContrDtlPk(dsContrDtlPk);
                                valid = INPUT_IS_INVALID;
                            }
                        }
                    } else {
                        // Machine
                        if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && bCMsg.contrEffFromDt_B.getValue().compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0660E);
                            }
                            bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0660E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B) && bCMsg.contrEffThruDt_B.getValue().compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0660E);
                            }
                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0660E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        }
                    }

                    // START 2017/07/27 [QC#16889, MOD]
                    // preSerNum = serNum;
                    // preMdseCd = mdseCd;
                    preDsContrDtlPk = dsContrDtlPk;
                    // END 2017/07/27 [QC#16889, MOD]
                }

                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.dsContrBllgMtrPk_B) && preDsContrBllgMtrPk_B.compareTo(bCMsg.dsContrBllgMtrPk_B.getValue()) != 0) {
                    // Auto set and check Contact Person
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    if (!NSAL0300CommonLogic.checkAndSetCtacPsnPk(glblCmpyCd, bCMsg.ctacPsnPk_BM, bCMsg.xxPsnNm_BM)) {
//                        bCMsg.xxPsnNm_BM.setErrorInfo(1, NSAM0040E, new String[] {"Contact" });
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // Del Start 2019/02/19 QC#30295
//                    if (!NSAL0300CommonLogic.checkAndSetCtacPsnPk(cMsg, glblCmpyCd, slsDt, bCMsg.bllgMtrBillToCustCd_B.getValue(), bCMsg.ctacPsnFirstNm_BM, bCMsg.ctacPsnLastNm_BM, bCMsg.ctacPsnPk_BM)) {
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // Del End 2019/02/19 QC#30295

                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]

                    if (!NSXC001001ContrValidation.checkBllgMtrParam(bCMsg.bllgMinCnt_B.getValue(), bCMsg.bllgMinAmtRate_B.getValue(), bCMsg.bllgRollOverRatio_B.getValue(), bCMsg.bllgFreeCopyCnt_B.getValue())) {
                        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//                        bCMsg.bllgMinCnt_B.setErrorInfo(1, NSZM0681E);
//                        bCMsg.bllgMinAmtRate_B.setErrorInfo(1, NSZM0681E);
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgMinCnt_B.setErrorInfo(1, NSZM0681E);
                                    cMsg.B.no(j).bllgMinAmtRate_B.setErrorInfo(1, NSZM0681E);
                                }
                            }
                        } else {
                            bCMsg.bllgMinCnt_B.setErrorInfo(1, NSZM0681E);
                            bCMsg.bllgMinAmtRate_B.setErrorInfo(1, NSZM0681E);
                        }
                        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
                        bCMsg.bllgRollOverRatio_B.setErrorInfo(1, NSZM0681E);
                        bCMsg.bllgFreeCopyCnt_B.setErrorInfo(1, NSZM0681E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    int tierCnt = getTierCnt(cMsg.B, bCMsg.dsContrBllgMtrPk_B.getValue());

                    // START 2016/05/23 M.Gotou [QC#7637, MOD]
                    if (!NSXC001001ContrValidation.checkXsMinVol(bCMsg.bllgMinCnt_B.getValue(), tierCnt)) {
                        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Vol" });
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgMinCnt_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Vol" });
                                }
                            }
                        } else {
                            bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Vol" });
                        }
                        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (!NSXC001001ContrValidation.checkXsMinVol(bCMsg.bllgMinCnt_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSZM0682E);
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgMinCnt_B.setErrorInfo(1, NSZM0682E);
                                }
                            }
                        } else {
                            bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSZM0682E);
                        }
                        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (!NSXC001001ContrValidation.checkXsMinAmt(bCMsg.bllgMinAmtRate_B.getValue(), tierCnt)) {
                        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Amt" });
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgMinAmtRate_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Amt" });
                                }
                            }
                        } else {
                            bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Amt" });
                        }
                        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
                    if (!NSXC001001ContrValidation.checkXsMinAmt(bCMsg.bllgMinAmtRate_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgMinAmtRate_B.setErrorInfo(1, NSAM0720E);
                                }
                            }
                        } else {
                            bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0720E);
                        }
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (!NSXC001001ContrValidation.checkRollOver(bCMsg.bllgRollOverRatio_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgRollOverRatio_B.setErrorInfo(1, NSAM0721E);
                                }
                            }
                        } else {
                            bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0721E);
                        }
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

                    // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                    if (!ZYPCommonFunc.hasValue(bCMsg.bllgFreeCopyCnt_B) && !NSXC001001ContrValidation.checkRollOver(bCMsg.bllgRollOverRatio_B.getValue(), tierCnt)) {
//                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Rollover%" });
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // END 2018/05/14 K.Kitachi [QC#23541, DEL]

                    if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && !checkTierCnt(tierCntMap, bCMsg.bllgMtrLbCd_B.getValue(), tierCnt)) {
                        cMsg.setMessageInfo(NSAM0386E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // Del Start 2018/03/12 QC#23629
//                    if (ZYPCommonFunc.hasValue(bCMsg.bllgFreeCopyCnt_B) && !ZYPCommonFunc.hasValue(bCMsg.bllgRollOverRatio_B)) {
//                        bCMsg.bllgRollOverRatio_B.setErrorInfo(1, NSAM0189E, new String[] {"Free Copies", "Rollover% = 100" });
//                        valid = INPUT_IS_INVALID;
//                    }
                    // Del End 2018/03/12 QC#23629
                    if (!NSXC001001ContrValidation.checkFreeCopy(bCMsg.bllgFreeCopyCnt_B.getValue(), tierCnt)) {
                        // START 2018/05/14 K.Kitachi [QC#23541, MOD]
//                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Free copies" });
                        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) && bCMsg.bllgMtrLbCd_B.getValue().equals(cMsg.B.no(j).bllgMtrLbCd_B.getValue())) {
                                    cMsg.B.no(j).bllgFreeCopyCnt_B.setErrorInfo(1, NSAM0477E, new String[] {"Free copies" });
                                }
                            }
                        } else {
                            bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Free copies" });
                        }
                        // END 2018/05/14 K.Kitachi [QC#23541, MOD]
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END 2016/05/23 M.Gotou [QC#7637, MOD]

                    // mod start 2016/09/23 CSA Defect#13686
                    //if (ZYPCommonFunc.hasValue(bCMsg.bllgMtrBillToCustCd_B) && !ZYPCommonFunc.hasValue(bCMsg.xxPsnNm_BM)) {
                    //    bCMsg.xxPsnNm_BM.setErrorInfo(1, NSAM0189E, new String[] {"Bill To", "Contact" });
                    //    addErroredDsContrDtlPk(dsContrDtlPk);
                    //    valid = INPUT_IS_INVALID;
                    //} else
                    // Del Start 2019/02/19 QC#30295
//                    if (!NSAL0300CommonLogic.checkCtacPsnReln(glblCmpyCd, bCMsg.ctacPsnPk_BM.getValue(), bCMsg.bllgMtrBillToCustCd_B.getValue())) {
//                        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
////                        bCMsg.xxPsnNm_BM.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
//                        bCMsg.ctacPsnFirstNm_BM.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
//                        bCMsg.ctacPsnLastNm_BM.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
//                        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // Del End 2019/02/19 QC#30295
                    // mod end 2016/09/23 CSA Defect#13686

                    // START 2016/01/18 T.Tomita [QC#2948, DEL]
//                    if (ZYPConstant.FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue()) && !NSXC001001ContrValidation.checkLeaseBillTo(cMsg.leaseCmpyCd.getValue(), bCMsg.bllgMtrBillToCustCd_B.getValue())) {
//                        bCMsg.bllgMtrBillToCustCd_B.setErrorInfo(1, NSZM0664E);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // END 2016/01/18 T.Tomita [QC#2948, DEL]
                    // Del Start 2019/02/19 QC#30295
//                    if (!NSXC001001ContrValidation.checkAcctBillEligible(glblCmpyCd, slsDt, dsAcctNum, bCMsg.bllgMtrBillToCustCd_B.getValue(), ONBATCH_TYPE.ONLINE)) {
//                        bCMsg.bllgMtrBillToCustCd_B.setErrorInfo(1, NSZM0698E, new String[] {"Customer#", "Bill To" });
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // Del End 2019/02/19 QC#30295
                    // START 2016/02/16 T.Kanasaka [QC3889, ADD]
                    if (!ZYPCommonFunc.hasValue(cMsg.custPoNum)) {
                        // START 2017/09/20 K.Kim [QC#21211, ADD]
                        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                        // END 2017/09/20 K.Kim [QC#21211, ADD]
                            if (ZYPCommonFunc.hasValue(bCMsg.bllgMtrBillToCustCd_B)) {
                                boolean poReq = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, bCMsg.bllgMtrBillToCustCd_B.getValue(), ONBATCH_TYPE.ONLINE);
                                if (poReq) {
                                    cMsg.custPoNum.setErrorInfo(1, NSAM0066E, new String[] {"PO required Bill To Location", "PO Number" });
                                    addErroredDsContrDtlPk(dsContrDtlPk);
                                    valid = INPUT_IS_INVALID;
                                }
                            }
                        }
                    }
                    // END 2016/02/16 T.Kanasaka [QC3889, ADD]

                    // START 2018/06/07 K.Kim [QC#24857, ADD]
                    NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                    if (findACMsg != null && !ZYPCommonFunc.hasValue(findACMsg.mtrReadMethCd_A)) {
                        findACMsg.mtrReadMethCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Meter Method"});
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END 2018/06/07 K.Kim [QC#24857, ADD]

                    preDsContrBllgMtrPk_B = bCMsg.dsContrBllgMtrPk_B.getValue();

                    // Del Start 2019/02/19 QC#30295
//                    // START 2019/01/17 M.Naito [QC#29083,ADD]
//                    boolean cumCopyInputErrFlg = true;
//                    if (!hasValue(bCMsg.cumCopyCnt_B) && !hasValue(bCMsg.cumCopyFreqMthAot_B) && !hasValue(bCMsg.cumCopyStartDt_B) && !hasValue(bCMsg.cumCopyEndDt_B)) {
//                        cumCopyInputErrFlg = false;
//                    }
//                    if (hasValue(bCMsg.cumCopyCnt_B) && hasValue(bCMsg.cumCopyFreqMthAot_B) && hasValue(bCMsg.cumCopyStartDt_B) && hasValue(bCMsg.cumCopyEndDt_B)) {
//                        cumCopyInputErrFlg = false;
//                    }
//                    if (cumCopyInputErrFlg) {
//                        if (!hasValue(bCMsg.cumCopyCnt_B)) {
//                            bCMsg.cumCopyCnt_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy Allowance" });
//                        }
//                        if (!hasValue(bCMsg.cumCopyFreqMthAot_B)) {
//                            bCMsg.cumCopyFreqMthAot_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy Frequency" });
//                        }
//                        if (!hasValue(bCMsg.cumCopyStartDt_B)) {
//                            bCMsg.cumCopyStartDt_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy Start Date" });
//                        }
//                        if (!hasValue(bCMsg.cumCopyEndDt_B)) {
//                            bCMsg.cumCopyEndDt_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy End Date" });
//                        }
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
//                    if (ZYPCommonFunc.hasValue(bCMsg.cumCopyStartDt_B) && ZYPCommonFunc.hasValue(bCMsg.cumCopyEndDt_B)) {
//                        if (ZYPDateUtil.compare(bCMsg.cumCopyStartDt_B.getValue(), bCMsg.cumCopyEndDt_B.getValue()) > 0) {
//                            bCMsg.cumCopyStartDt_B.setErrorInfo(1, NSAM0327E);
//                            addErroredDsContrDtlPk(dsContrDtlPk);
//                            valid = INPUT_IS_INVALID;
//                        // START 2019/02/13 K.Kitachi [QC#30318, DEL]
////                        } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B)) {
////                            if (bCMsg.cumCopyStartDt_B.getValue().compareTo(bCMsg.contrEffFromDt_B.getValue()) < 0 || bCMsg.cumCopyStartDt_B.getValue().compareTo(bCMsg.contrEffThruDt_B.getValue()) > 0) {
////                                bCMsg.cumCopyStartDt_B.setErrorInfo(1, NSAM0157E, new String[] {"Cumulative Copy Start Date" });
////                                addErroredDsContrDtlPk(dsContrDtlPk);
////                                valid = INPUT_IS_INVALID;
////                            }
////                            if (bCMsg.cumCopyEndDt_B.getValue().compareTo(bCMsg.contrEffFromDt_B.getValue()) < 0 || bCMsg.cumCopyEndDt_B.getValue().compareTo(bCMsg.contrEffThruDt_B.getValue()) > 0) {
////                                bCMsg.cumCopyEndDt_B.setErrorInfo(1, NSAM0157E, new String[] {"Cumulative Copy End Date" });
////                                addErroredDsContrDtlPk(dsContrDtlPk);
////                                valid = INPUT_IS_INVALID;
////                            }
//                        // END 2019/02/13 K.Kitachi [QC#30318, DEL]
//                        }
//                    }
//                    // END 2019/01/17 M.Naito [QC#29083,ADD]
                    // Del End 2019/02/19 QC#30295
                }

                // Add Start 2019/02/19 QC#30295
                int nextIdx = i + 1;
                if (nextIdx < cMsg.B.getValidCount()) {
                    nextDsContrBllgMtrPk_B = cMsg.B.no(nextIdx).dsContrBllgMtrPk_B.getValue();
                } else {
                    nextDsContrBllgMtrPk_B = null;
                }
                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.dsContrBllgMtrPk_B) && !NSAL0300CommonLogic.isEqualNum(bCMsg.dsContrBllgMtrPk_B.getValue(), nextDsContrBllgMtrPk_B)) {
                    if (!NSAL0300CommonLogic.checkAndSetCtacPsnPk(cMsg, glblCmpyCd, slsDt, bCMsg.bllgMtrBillToCustCd_B.getValue(), bCMsg.ctacPsnFirstNm_BM, bCMsg.ctacPsnLastNm_BM, bCMsg.ctacPsnPk_BM)) {
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                        errDsContrBllgMtrPkList.add(bCMsg.dsContrBllgMtrPk_B.getValue());
                    }
                    if (valid && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                        aggLineMtrCtacPsnPkMap.put(bCMsg.bllgMtrLbCd_B.getValue(), bCMsg.ctacPsnPk_BM.getValue());
                    }

                    if (!NSAL0300CommonLogic.checkCtacPsnReln(glblCmpyCd, bCMsg.ctacPsnPk_BM.getValue(), bCMsg.bllgMtrBillToCustCd_B.getValue())) {
                        bCMsg.ctacPsnFirstNm_BM.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
                        bCMsg.ctacPsnLastNm_BM.setErrorInfo(1, NSAM0138E, new String[] {"Contact", "Bill To" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                        errDsContrBllgMtrPkList.add(bCMsg.dsContrBllgMtrPk_B.getValue());
                    }

                    if (!NSXC001001ContrValidation.checkAcctBillEligible(glblCmpyCd, slsDt, dsAcctNum, bCMsg.bllgMtrBillToCustCd_B.getValue(), ONBATCH_TYPE.ONLINE)) {
                        bCMsg.bllgMtrBillToCustCd_B.setErrorInfo(1, NSZM0698E, new String[] {"Customer#", "Bill To" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                        errDsContrBllgMtrPkList.add(bCMsg.dsContrBllgMtrPk_B.getValue());
                    }

                    boolean cumCopyInputErrFlg = true;
                    if (!hasValue(bCMsg.cumCopyCnt_B) && !hasValue(bCMsg.cumCopyFreqMthAot_B) && !hasValue(bCMsg.cumCopyStartDt_B) && !hasValue(bCMsg.cumCopyEndDt_B)) {
                        cumCopyInputErrFlg = false;
                    }
                    if (hasValue(bCMsg.cumCopyCnt_B) && hasValue(bCMsg.cumCopyFreqMthAot_B) && hasValue(bCMsg.cumCopyStartDt_B) && hasValue(bCMsg.cumCopyEndDt_B)) {
                        cumCopyInputErrFlg = false;
                    }
                    if (cumCopyInputErrFlg) {
                        if (!hasValue(bCMsg.cumCopyCnt_B)) {
                            bCMsg.cumCopyCnt_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy Allowance" });
                        }
                        if (!hasValue(bCMsg.cumCopyFreqMthAot_B)) {
                            bCMsg.cumCopyFreqMthAot_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy Frequency" });
                        }
                        if (!hasValue(bCMsg.cumCopyStartDt_B)) {
                            bCMsg.cumCopyStartDt_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy Start Date" });
                        }
                        if (!hasValue(bCMsg.cumCopyEndDt_B)) {
                            bCMsg.cumCopyEndDt_B.setErrorInfo(1, ZZM9000E, new String[] {"Cumulative Copy End Date" });
                        }
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                        errDsContrBllgMtrPkList.add(bCMsg.dsContrBllgMtrPk_B.getValue());
                    }
                    if (ZYPCommonFunc.hasValue(bCMsg.cumCopyStartDt_B) && ZYPCommonFunc.hasValue(bCMsg.cumCopyEndDt_B)) {
                        if (ZYPDateUtil.compare(bCMsg.cumCopyStartDt_B.getValue(), bCMsg.cumCopyEndDt_B.getValue()) > 0) {
                            bCMsg.cumCopyStartDt_B.setErrorInfo(1, NSAM0327E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                            errDsContrBllgMtrPkList.add(bCMsg.dsContrBllgMtrPk_B.getValue());
                        }
                    }
                }
                // Add End 2019/02/19 QC#30295

                // START 2016/07/11 T.Kanasaka [QC#11528, ADD]
                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                    if (!checkSameAllowance(cMsg.B, bCMsg.dsContrBllgMtrPk_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0035E, new String[] {"Allowance" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                        // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
                    } else if (!checkAllowanceSeq(cMsg.B, bCMsg.dsContrBllgMtrPk_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]
                }
                // END 2016/07/11 T.Kanasaka [QC#11528, ADD]
            } else {
                // Aggregate Machine & Accessory Check
                if (ZYPCommonFunc.hasValue(bCMsg.dsContrBllgMtrPk_B)) {
                    if (!DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd) && !ZYPCommonFunc.hasValue(bCMsg.xsMtrCopyQty_B)) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, ZZM9000E, new String[] {"Allowance" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                }

                // START 2017/07/27 [QC#16889, MOD]
                // if (!NSAL0300CommonLogic.isEqualMach(preSerNum, preMdseCd, serNum, mdseCd)) {
                if (!NSAL0300CommonLogic.isEqualNum(preDsContrDtlPk, dsContrDtlPk)) {
                // END 2017/07/27 [QC#16889, MOD]
                    if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B)) {
                        if (ZYPDateUtil.compare(bCMsg.contrEffFromDt_B.getValue(), bCMsg.contrEffThruDt_B.getValue()) > 0) {
                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSAM0327E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;

                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0327E);
                            }
                        }
                    }

                    if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                        S21SsmEZDResult rslt = query.getInvoicedContrInfo(glblCmpyCd, dsContrDtlPk);
                        if (rslt != null && rslt.isCodeNormal()) {
                            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
                            if (rslt.getQueryResultCount() > 0) {
                                Map<String, String> rsltMap = rsltList.get(0);
                                String contrEffFromDt = rsltMap.get("CONTR_EFF_FROM_DT");
                                if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && ZYPDateUtil.compare(bCMsg.contrEffFromDt_B.getValue(), contrEffFromDt) != 0) {
                                    bCMsg.contrEffFromDt_B.setErrorInfo(1, NSAM0387E);
                                    // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                    NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                    if (findACMsg != null) {
                                        findACMsg.contrEffFromDt_A.setErrorInfo(1, NSAM0387E);
                                    }
                                    // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                    addErroredDsContrDtlPk(dsContrDtlPk);
                                    valid = INPUT_IS_INVALID;
                                }
                                // Add Start 2016/11/10 <QC#15888>
                                String invoicedThruDt = rsltMap.get("INVOICED_THRU_DT");
                                if (ZYPCommonFunc.hasValue(invoicedThruDt)) {
                                    String thruDt = bCMsg.contrEffThruDt_B.getValue();
                                    // START 2017/03/17 K.Kitachi [QC#17948, MOD]
                                    if (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(invoicedThruDt) < 0) {
                                    // END 2017/03/17 K.Kitachi [QC#17948, MOD]
                                        bCMsg.contrEffThruDt_B.setErrorInfo(1, NSAM0346E, new String[] {"End Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt) });
                                        // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                        if (findACMsg != null) {
                                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0346E, new String[] {"End Date", ZYPDateUtil.formatEzd8ToDisp(invoicedThruDt) });
                                        }
                                        // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                        addErroredDsContrDtlPk(dsContrDtlPk);
                                        valid = INPUT_IS_INVALID;
                                    }
                                }
                                // Add End   2016/11/10 <QC#15888>
                            }
                        }
                    }

                    // START 2018/06/04 K.Kim [QC#15410(Sol#246),DEL]
//                    if (ZYPCommonFunc.hasValue(bCMsg.svcMachMstrPk_B) && ZYPCommonFunc.hasValue(dsContrDtlPk)) {
//                        // Mod Start 2018/06/03 QC#26379
//                        String thruDt = bCMsg.contrEffThruDt_B.getValue();
//                        if (ZYPCommonFunc.hasValue(bCMsg.contrCloDt_B)) {
//                            thruDt = bCMsg.contrCloDt_B.getValue();
//                        }
//                        if (!NSXC001001ContrValidation.checkDuplicateContr(glblCmpyCd, cMsg.dsContrCatgCd.getValue(), bCMsg.svcMachMstrPk_B.getValue(), dsContrDtlPk, bCMsg.contrEffFromDt_B.getValue(), thruDt)) {
//                            bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0662E);
//                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0662E);
//                            // START 2018/03/15 K.Kojima [QC#24804,ADD]
//                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
//                            if (findACMsg != null) {
//                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0662E);
//                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0662E);
//                            }
//                            // END 2018/03/15 K.Kojima [QC#24804,ADD]
//                            addErroredDsContrDtlPk(dsContrDtlPk);
//                            valid = INPUT_IS_INVALID;
//                        }
//                        // Mod End 2018/06/03 QC#26379
//                    }
                    // END 2018/06/04 K.Kim [QC#15410(Sol#246),DEL]

                    if (ZYPConstant.FLG_ON_Y.equals(bCMsg.xxDplyCtrlFlg_B0.getValue())) {
                        // Accessory
                        NSAL0300_BCMsg parentBCMsg = NSAL0300CommonLogic.getParentBCMsg(cMsg.B, bCMsg.prntDsContrDtlPk_B.getValue());
                        if (parentBCMsg != null) {
                            if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && bCMsg.contrEffFromDt_B.getValue().compareTo(parentBCMsg.contrEffFromDt_B.getValue()) < 0) {
                                bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0661E);
                                // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                if (findACMsg != null) {
                                    findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0661E);
                                }
                                // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                addErroredDsContrDtlPk(dsContrDtlPk);
                                valid = INPUT_IS_INVALID;
                            } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B) && bCMsg.contrEffThruDt_B.getValue().compareTo(parentBCMsg.contrEffThruDt_B.getValue()) > 0) {
                                bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0661E);
                                // START 2018/03/15 K.Kojima [QC#24804,ADD]
                                NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                                if (findACMsg != null) {
                                    findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0661E);
                                }
                                // END 2018/03/15 K.Kojima [QC#24804,ADD]
                                addErroredDsContrDtlPk(dsContrDtlPk);
                                valid = INPUT_IS_INVALID;
                            }
                        }
                    } else {
                        // Machine
                        if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && bCMsg.contrEffFromDt_B.getValue().compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0660E);
                            }
                            bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0660E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B) && bCMsg.contrEffThruDt_B.getValue().compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0660E);
                            }
                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0660E);
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        }
                    }

                    // START 2017/07/27 [QC#16889, MOD]
                    // preSerNum = serNum;
                    // preMdseCd = mdseCd;
                    preDsContrDtlPk = dsContrDtlPk;
                    // END 2017/07/27 [QC#16889, MOD]
                }

                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.dsContrBllgMtrPk_B) && preDsContrBllgMtrPk_B.compareTo(bCMsg.dsContrBllgMtrPk_B.getValue()) != 0) {

                    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
                    if (!NSXC001001ContrValidation.checkBllgMtrParam(bCMsg.bllgMinCnt_B.getValue(), bCMsg.bllgMinAmtRate_B.getValue(), bCMsg.bllgRollOverRatio_B.getValue(), bCMsg.bllgFreeCopyCnt_B.getValue())) {
                        bCMsg.bllgMinCnt_B.setErrorInfo(1, NSZM0681E);
                        bCMsg.bllgMinAmtRate_B.setErrorInfo(1, NSZM0681E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

                    int tierCnt = getTierCnt(cMsg.B, bCMsg.dsContrBllgMtrPk_B.getValue());

                    // START 2016/05/23 M.Gotou [QC#7637, MOD]
                    if (!NSXC001001ContrValidation.checkXsMinVol(bCMsg.bllgMinCnt_B.getValue(), tierCnt)) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Vol" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (!NSXC001001ContrValidation.checkXsMinVol(bCMsg.bllgMinCnt_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSZM0682E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (!NSXC001001ContrValidation.checkXsMinAmt(bCMsg.bllgMinAmtRate_B.getValue(), tierCnt)) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Min. Amt" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
                    if (!NSXC001001ContrValidation.checkXsMinAmt(bCMsg.bllgMinAmtRate_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0720E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }

                    if (!NSXC001001ContrValidation.checkRollOver(bCMsg.bllgRollOverRatio_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0721E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

                    // START 2018/05/14 K.Kitachi [QC#23541, DEL]
//                    if (!ZYPCommonFunc.hasValue(bCMsg.bllgFreeCopyCnt_B) && !NSXC001001ContrValidation.checkRollOver(bCMsg.bllgRollOverRatio_B.getValue(), tierCnt)) {
//                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0477E, new String[] {"Rollover%" });
//                        addErroredDsContrDtlPk(dsContrDtlPk);
//                        valid = INPUT_IS_INVALID;
//                    }
                    // END 2018/05/14 K.Kitachi [QC#23541, DEL]
                    // END 2016/05/23 M.Gotou [QC#7637, MOD]

                    if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && !checkTierCnt(tierCntMap, bCMsg.bllgMtrLbCd_B.getValue(), tierCnt)) {
                        cMsg.setMessageInfo(NSAM0386E);
                        valid = INPUT_IS_INVALID;
                    }

                    // START 2018/06/07 K.Kim [QC#24857, ADD]
                    NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                    if (findACMsg != null && !ZYPCommonFunc.hasValue(findACMsg.mtrReadMethCd_A)) {
                        findACMsg.mtrReadMethCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Meter Method"});
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END 2018/06/07 K.Kim [QC#24857, ADD]

                    preDsContrBllgMtrPk_B = bCMsg.dsContrBllgMtrPk_B.getValue();
                }

                // START 2016/07/11 T.Kanasaka [QC#11528, ADD]
                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                    if (!checkSameAllowance(cMsg.B, bCMsg.dsContrBllgMtrPk_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        bCMsg.xsMtrCopyQty_B.setErrorInfo(1, NSAM0035E, new String[] {"Allowance" });
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
                    } else if (!checkAllowanceSeq(cMsg.B, bCMsg.dsContrBllgMtrPk_B.getValue(), bCMsg.xsMtrCopyQty_B.getValue())) {
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]
                    // Add Start 2019/02/14 QC#30295
                    if (valid && aggLineMtrCtacPsnPkMap.containsKey(bCMsg.bllgMtrLbCd_B.getValue())) {
                        setValue(bCMsg.ctacPsnPk_BM, aggLineMtrCtacPsnPkMap.get(bCMsg.bllgMtrLbCd_B.getValue()));
                    }
                    // Add End 2019/02/14 QC#30295
                }
                // END 2016/07/11 T.Kanasaka [QC#11528, ADD]
            }
            // Add Start 2018/01/12 QC#18552
            if (ZYPCommonFunc.hasValue(bCMsg.contrBllgDay_B)) {
                BigDecimal bllgDayAot = new BigDecimal(bCMsg.contrBllgDay_B.getValue());
                if (BigDecimal.ZERO.compareTo(bllgDayAot) > 0 && ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue())) {
                    bCMsg.contrBllgDay_B.setErrorInfo(1, NSAM0711E);
                    addErroredDsContrDtlPk(dsContrDtlPk);
                    valid = INPUT_IS_INVALID;
                }
            }
            // Add End 2018/01/12 QC#18552
        }

        // Add Start 2019/02/19 QC#30295
        if (errDsContrBllgMtrPkList.size() > 0) {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (!hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                    continue;
                }
                if (!errDsContrBllgMtrPkList.contains(cMsg.B.no(i).dsContrBllgMtrPk_B.getValue())) {
                    continue;
                }
                setValue(cMsg.B.no(i).xxFilePathTxt_BM, IMG_OPEN_ARROW);
                setValue(cMsg.B.no(i).xxFilePathTxt_BC, IMG_OPEN_ARROW);
            }
        }
        // Add End 2019/02/19 QC#30295

        // START 2023/02/09 R.Takau [QC#55645,ADD]
        if (PMT_TERM_CASH_DISC.CHECK_BY_PHONE.equals(cMsg.pmtTermCashDiscCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.bankRteNum) || !ZYPCommonFunc.hasValue(cMsg.dsBankAcctNum)) {
                cMsg.bankRteNum.setErrorInfo(1, NSAM0189E, new String[]{"Payment Term:CP","Bank Routing# and Bank Account#"});
                cMsg.dsBankAcctNum.setErrorInfo(1, NSAM0189E, new String[]{"Payment Term:CP","Bank Routing# and Bank Account#"});
                valid = INPUT_IS_INVALID;
                return valid;
            }

            Map<String, Object> resultMap = NSAL0300Query.getInstance().getRelation(glblCmpyCd,cMsg.dsCustBankAcctRelnPk.getValue().toString(),cMsg.sellToCustCd.getValue().toString());
            if (resultMap == null) {
                // START 2023/12/7 Y.Ogura [QC#62173, MOD]
//                cMsg.setMessageInfo(NSAM0138E,new String[]{"Bank Routing#","Bank Account#"});
                cMsg.setMessageInfo(NSAM0138E,new String[]{"Bank Account","Bill To Account"});
                // END 2023/12/7 Y.Ogura [QC#62173, MOD]
                valid = INPUT_IS_INVALID;
            } else {
                String effFromDate = (String) resultMap.get("EFF_FROM_DT");
                String effThruDt = (String) resultMap.get("EFF_THRU_DT");

                if(hasValue(effThruDt)) {
                    if(slsDt.compareTo(effFromDate) < 0 && slsDt.compareTo(effThruDt) > 0){
                        cMsg.setMessageInfo(NSAM0040E, new String[]{"Bank Routing# and Bank Account#"});
                        valid = INPUT_IS_INVALID;
                    }
                } else {
                    if(slsDt.compareTo(effFromDate) < 0){
                        cMsg.setMessageInfo(NSAM0040E, new String[]{"Bank Routing# and Bank Account#"});
                        valid = INPUT_IS_INVALID;
                    }
                }
            }
        }
        //END 2023/02/15 R.Takau [QC#55645,ADD]
        return valid;
    }

    private Map<String, Integer> getTierCntMap(NSAL0300_BCMsgArray bCMsgArray) {
        Map<String, Integer> tierCntMap = new HashMap<String, Integer>();

        int cnt = 0;
        BigDecimal dsContrBllgMtrPk = null;
        BigDecimal preDsContrBllgMtrPk = null;
        String bllgMtrLbCd = null;
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            dsContrBllgMtrPk = bCMsgArray.no(i).dsContrBllgMtrPk_B.getValue();
            if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(preDsContrBllgMtrPk) && preDsContrBllgMtrPk.compareTo(dsContrBllgMtrPk) != 0) {
                if (ZYPCommonFunc.hasValue(bllgMtrLbCd) && !tierCntMap.containsKey(bllgMtrLbCd)) {
                    tierCntMap.put(bllgMtrLbCd, cnt);
                }
                cnt = 0;
            }
            cnt++;
            bllgMtrLbCd = bCMsgArray.no(i).bllgMtrLbCd_B.getValue();
            preDsContrBllgMtrPk = dsContrBllgMtrPk;
        }
        if (ZYPCommonFunc.hasValue(preDsContrBllgMtrPk)) {
            if (ZYPCommonFunc.hasValue(bllgMtrLbCd) && !tierCntMap.containsKey(bllgMtrLbCd)) {
                tierCntMap.put(bllgMtrLbCd, cnt);
            }
        }
        return tierCntMap;
    }

    private int getTierCnt(NSAL0300_BCMsgArray bCMsgArray, BigDecimal dsContrBllgMtrPk) {
        int cnt = 0;
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return cnt;
        }
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).dsContrBllgMtrPk_B) && dsContrBllgMtrPk.compareTo(bCMsgArray.no(i).dsContrBllgMtrPk_B.getValue()) == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean checkTierCnt(Map<String, Integer> tierCntMap, String bllgMtrLbCd, int tierCnt) {
        if (tierCntMap.containsKey(bllgMtrLbCd)) {
            Integer mapTierCnt = tierCntMap.get(bllgMtrLbCd);
           if (mapTierCnt.compareTo(Integer.valueOf(tierCnt)) == 0) {
                return true;
            }
        }
        return false;
    }

    // START 2016/07/11 T.Kanasaka [QC#11528, ADD]
    private boolean checkSameAllowance(NSAL0300_BCMsgArray bCMsgArray, BigDecimal dsContrBllgMtrPk, BigDecimal xsMtrCopyQty_B) {
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) || !ZYPCommonFunc.hasValue(xsMtrCopyQty_B)) {
            return true;
        }

        int cnt = 0;
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).dsContrBllgMtrPk_B) && dsContrBllgMtrPk.compareTo(bCMsgArray.no(i).dsContrBllgMtrPk_B.getValue()) == 0) {
                if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).xsMtrCopyQty_B) && xsMtrCopyQty_B.compareTo(bCMsgArray.no(i).xsMtrCopyQty_B.getValue()) == 0) {
                    cnt++;
                }
            }
        }

        if (cnt > 1) {
            return false;
        }

        return true;
    }
    // END 2016/07/11 T.Kanasaka [QC#11528, ADD]

    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    private boolean checkAllowanceSeq(NSAL0300_BCMsgArray bCMsgArray, BigDecimal dsContrBllgMtrPk, BigDecimal xsMtrCopyQty_B) {
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) || !ZYPCommonFunc.hasValue(xsMtrCopyQty_B)) {
            return true;
        }

        boolean ret = true;
        BigDecimal curAllowace = BigDecimal.valueOf(-1);
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).dsContrBllgMtrPk_B) && dsContrBllgMtrPk.compareTo(bCMsgArray.no(i).dsContrBllgMtrPk_B.getValue()) == 0) {
                if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).xsMtrCopyQty_B)) {
                    if (curAllowace.compareTo(bCMsgArray.no(i).xsMtrCopyQty_B.getValue()) >= 0) {
                        bCMsgArray.no(i).xsMtrCopyQty_B.setErrorInfo(1, NSAM0623E);
                        ret = false;
                    }
                    curAllowace = bCMsgArray.no(i).xsMtrCopyQty_B.getValue();
                }
            }
        }

        return ret;
    }
    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]

    private NSAL0300_ACMsg findACMsg(NSAL0300_ACMsgArray aCMsgArray, BigDecimal dsContrDtlPk) {
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return null;
        }
        for (int i = 0; i < aCMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(aCMsgArray.no(i).dsContrDtlPk_A) && dsContrDtlPk.compareTo(aCMsgArray.no(i).dsContrDtlPk_A.getValue()) == 0) {
                return aCMsgArray.no(i);
            }
        }
        return null;
    }

    private boolean checkInput_NSAL0300Scrn00_CMN_Submit(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        boolean valid = INPUT_IS_VALID;

        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0385E);
            valid = INPUT_IS_INVALID;
        }

        return valid;
    }
    //mod start 2024/03/22 CSA Defect#63463
    //private boolean checkInput_NSAL0300Scrn00_OpenForUpdate(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    private boolean checkInput_NSAL0300Scrn00_OpenForUpdateAndRevert(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    //mod End 2024/03/22 CSA Defect#63463
        return INPUT_IS_VALID;
    }

    private boolean checkInput_NSAL0300Scrn00_OpenWin_SubContract(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0034E);
            return INPUT_IS_INVALID;
        }

        return checkInputForOtherWin(cMsg, sMsg);
    }

    private boolean checkInput_NSAL0300Scrn00_OpenWin_Terms(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0034E);
            return INPUT_IS_INVALID;
        }
        // add start 2016/03/03 CSA Defect#3018
        BigDecimal termsLmtCnt = ZYPCodeDataUtil.getNumConstValue(NSAL0300_TERMS_LMT_CNT, getGlobalCompanyCode());
        if (termsLmtCnt == null) {
            termsLmtCnt = DEF_NSAL0300_TERMS_LMT_CNT;
        }
        if (selectedRows.size() > termsLmtCnt.intValue()) {
            cMsg.setMessageInfo(NSAM0141E, new String[]{String.valueOf(termsLmtCnt.intValue() + 1)});
            return INPUT_IS_INVALID;
        }
        // add end 2016/03/03 CSA Defect#3018

        return checkInputForOtherWin(cMsg, sMsg);
    }

    private boolean checkInput_NSAL0300Scrn00_OpenWin_CompleteContract(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0385E);
            return INPUT_IS_INVALID;
        }

        return checkInputForOtherWin(cMsg, sMsg);
    }

    private boolean checkInputForOtherWin(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        boolean valid = INPUT_IS_VALID;

        // add start 2016/05/30 CSA Defect#447
        // get SVC_MACH_MSTR_PK from DS_CONTR(exclude Fleet Machine)
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().getTermCondUpdTrgt(getGlobalCompanyCode(), cMsg.dsContrPk.getValue());
        List<Map<String, Object>> dsContrDtlList = (List<Map<String, Object>>) rslt.getResultObject();
        String slsDt = ZYPDateUtil.getSalesDate();

        for (Map<String, Object> dsContrDtlMap : dsContrDtlList) {
            // get SLA Time
            BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtlMap.get("DS_CONTR_DTL_PK");
            String contrEffFromDt = (String) dsContrDtlMap.get("CONTR_EFF_FROM_DT");
            SlaInfoBean infoBean = new SlaInfoBean();
            infoBean.setGlblCmpyCd(getGlobalCompanyCode());
            infoBean.setDsContrDtlPk(dsContrDtlPk);
            if (ZYPDateUtil.compare(contrEffFromDt, slsDt) > 0) {
                infoBean.setSlaDt(contrEffFromDt);
            } else {
                infoBean.setSlaDt(slsDt);
            }

            if (!NSXC001001GetRspTime.getSLA(infoBean)) {
                cMsg.setMessageInfo(infoBean.getXxMsgId());
                return INPUT_IS_INVALID;
            } else {
                if (ZYPCommonFunc.hasValue(infoBean.getTermCondOptValTxt())) {
                    //get SLA time from VAR_CHAR_CONST
                    if (!insertSvcTermCond(cMsg, dsContrDtlPk, infoBean, NSAL0300Constant.TERM_COND_RSP_TM_MEAS_PER)) {
                        return INPUT_IS_INVALID;
                    }
                    if (!insertSvcTermCond(cMsg, dsContrDtlPk, infoBean, NSAL0300Constant.TERM_COND_RSP_TM_RMD_1)) {
                        return INPUT_IS_INVALID;
                    }
                    if (!insertSvcTermCond(cMsg, dsContrDtlPk, infoBean, NSAL0300Constant.TERM_COND_RSP_TM_RMD_2)) {
                        return INPUT_IS_INVALID;
                    }
                    if (!insertSvcTermCond(cMsg, dsContrDtlPk, infoBean, NSAL0300Constant.TERM_COND_MAX_CMBN_SLA_RMD)) {
                        return INPUT_IS_INVALID;
                    }
                    if (!insertSvcTermCond(cMsg, dsContrDtlPk, infoBean, NSAL0300Constant.TERM_COND_RSP_TM_COMIT)) {
                        return INPUT_IS_INVALID;
                    }
                }
            }
        }
        // add end 2016/05/30 CSA Defect#447
        if (DISPLAY_MODE_FREEZE.equals(cMsg.xxModeCd_FU.getValue())) {
            return valid;
        }

        if (ZYPCommonFunc.hasValue(cMsg.dsContrStsCd) && !DS_CONTR_STS.DRAFT.equals(cMsg.dsContrStsCd.getValue())
                // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
                && !DS_CONTR_STS.ENTERED.equals(cMsg.dsContrStsCd.getValue())
                // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
                // add start 2016/06/15 CSA Defect#9901
                || "NSAL0300Scrn00_OpenWin_CompleteContract".equals(cMsg.getScreenAplID())) {
                // add end 2016/06/15 CSA Defect#9901
            return checkInput_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
        }

        if (ZYPCommonFunc.hasValue(cMsg.contrVrsnEffFromDt) && ZYPCommonFunc.hasValue(cMsg.contrVrsnEffThruDt)) {
            if (ZYPDateUtil.compare(cMsg.contrVrsnEffFromDt.getValue(), cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                cMsg.contrVrsnEffThruDt.setErrorInfo(1, NSAM0327E);
                valid = INPUT_IS_INVALID;
            }
        }

        // add start 2016/09/06 CSA Defect#11243
        // Check Credit Card#
        if (ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            String glblCmpyCd = getGlobalCompanyCode();
            DS_CR_CARDTMsg tMsg = query.getDsCrCard(glblCmpyCd, cMsg.crCardCustRefNum.getValue(), cMsg.sellToCustCd.getValue());
            if (tMsg == null) {
                cMsg.crCardCustRefNum.setErrorInfo(1, NSAM0045E, new String[] {"Entered Reference#" });
                valid = INPUT_IS_INVALID;
            } else {
                String crCardExprYrMth = tMsg.crCardExprYrMth.getValue();
                setValue(cMsg.xxMthDt_CC, crCardExprYrMth.substring(4, 6));
                setValue(cMsg.xxYrDt_CC, crCardExprYrMth.substring(0, 4));
                if (!ZYPConstant.FLG_ON_Y.equals(tMsg.crCardValidFlg.getValue())) {
                    cMsg.crCardCustRefNum.setErrorInfo(1, NSAM0040E, new String[] {"Entered Reference#" });
                    valid = INPUT_IS_INVALID;
                }
            }
        }
        // add end 2016/09/06 CSA Defect#11243

        // START 2016/01/29 T.Iwamoto [QC#3807, DEL]
//        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
//        if (ZYPCommonFunc.hasValue(cMsg.dsContrPk) && DS_CONTR_STS.DRAFT.equals(cMsg.dsContrStsCd.getValue()) && !userId.equals(cMsg.dsContrCratPsnCd.getValue())) {
//            cMsg.setMessageInfo(NSAM0383E);
//            valid = INPUT_IS_INVALID;
//        }
        // END 2016/01/29 T.Iwamoto [QC#3807, DEL]

        // START 2016/02/10 T.Kanasaka [QC30558, ADD]
        NSAL0300CommonLogic.setItemForSave(cMsg);
        // END 2016/02/10 T.Kanasaka [QC30558, ADD]

        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            // Auto set for Aggregate
            // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
            //NSAL0300CommonLogic.setAggregateItem(cMsg);
            NSAL0300CommonLogic.setAggregateItem(cMsg, getGlobalCompanyCode());
            // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
        }

        // START 2017/07/27 [QC#16889, MOD]
        // String preSerNum = INVLD_SER_NUM;
        // String preMdseCd = INVLD_MDSE_CD;
        BigDecimal preDsContrDtlPk = BigDecimal.ONE.negate();
        // END 2017/07/27 [QC#16889, MOD]

        // START 2016/02/16 T.Kanasaka [QC2579, ADD]
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NSAL0300_ASMsg asMsg = sMsg.A.no(i);

                if (ZYPDateUtil.compare(asMsg.contrEffFromDt_A.getValue(), asMsg.contrEffThruDt_A.getValue()) > 0) {
                    NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                    if (findACMsg != null) {
                        findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0327E);
                    }
                    valid = INPUT_IS_INVALID;
                }

                if (DS_CONTR_DTL_TP.ACCESSORIES.equals(sMsg.A.no(i).dsContrDtlTpCd_A.getValue())) {
                    // Accessory
                    NSAL0300_ASMsg parentASMsg = NSAL0300CommonLogic.getParentASMsg(sMsg.A, asMsg.prntDsContrDtlPk_A.getValue());
                    if (parentASMsg != null) {
                        if (ZYPCommonFunc.hasValue(parentASMsg.contrEffFromDt_A) && asMsg.contrEffFromDt_A.getValue().compareTo(parentASMsg.contrEffFromDt_A.getValue()) < 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0661E);
                            }
                            valid = INPUT_IS_INVALID;
                        } else if (ZYPCommonFunc.hasValue(asMsg.contrEffThruDt_A) && asMsg.contrEffThruDt_A.getValue().compareTo(parentASMsg.contrEffThruDt_A.getValue()) > 0) {
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0661E);
                            }
                            valid = INPUT_IS_INVALID;
                        }
                    }
                } else {
                    // Machine
                    if (ZYPCommonFunc.hasValue(asMsg.contrEffFromDt_A) && asMsg.contrEffFromDt_A.getValue().compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0) {
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                        if (findACMsg != null) {
                            findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0660E);
                            valid = INPUT_IS_INVALID;
                        }
                    }

                    if (ZYPCommonFunc.hasValue(asMsg.contrEffThruDt_A) && asMsg.contrEffThruDt_A.getValue().compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                        if (findACMsg != null) {
                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0660E);
                            valid = INPUT_IS_INVALID;
                        }
                    }
                    // START 2018/06/07 K.Kim [QC#24857, ADD]
                    if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(sMsg.A.no(i).dsContrDtlTpCd_A.getValue())) {
                        BigDecimal countBllgMtr = NSAL0300Query.getInstance().countBllgMtr(getGlobalCompanyCode(), asMsg.dsContrDtlPk_A.getValue());
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, asMsg.dsContrDtlPk_A.getValue());
                        if (findACMsg != null && !ZYPCommonFunc.hasValue(findACMsg.mtrReadMethCd_A) && countBllgMtr.compareTo(BigDecimal.ZERO) != 0) {
                            findACMsg.mtrReadMethCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Meter Method"});
                            addErroredDsContrDtlPk(asMsg.dsContrDtlPk_A.getValue());
                            valid = INPUT_IS_INVALID;
                        }
                    }
                    // END 2018/06/07 K.Kim [QC#24857, ADD]
                }
            }
        }
        // END 2016/02/16 T.Kanasaka [QC2579, ADD]

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            String serNum = bCMsg.serNum_B.getValue();
            String mdseCd = bCMsg.mdseCd_B.getValue();
            String dsContrDtlTpCd = bCMsg.dsContrDtlTpCd_B.getValue();
            BigDecimal dsContrDtlPk = bCMsg.dsContrDtlPk_B.getValue();

            // START 2017/07/27 [QC#16889, MOD]
            // if (!NSAL0300CommonLogic.isEqualMach(preSerNum, preMdseCd, serNum, mdseCd)) {
            if (!NSAL0300CommonLogic.isEqualNum(preDsContrDtlPk, dsContrDtlPk)) {
            // END 2017/07/27 [QC#16889, MOD]
                if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B)) {
                    if (ZYPDateUtil.compare(bCMsg.contrEffFromDt_B.getValue(), bCMsg.contrEffThruDt_B.getValue()) > 0) {
                        bCMsg.contrEffThruDt_B.setErrorInfo(1, NSAM0327E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;

                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                        if (findACMsg != null) {
                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSAM0327E);
                        }
                    }
                }

                if (ZYPConstant.FLG_ON_Y.equals(bCMsg.xxDplyCtrlFlg_B0.getValue())) {
                    // Accessory
                    NSAL0300_BCMsg parentBCMsg = NSAL0300CommonLogic.getParentBCMsg(cMsg.B, bCMsg.prntDsContrDtlPk_B.getValue());
                    if (parentBCMsg != null) {
                        if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && bCMsg.contrEffFromDt_B.getValue().compareTo(parentBCMsg.contrEffFromDt_B.getValue()) < 0) {
                            bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0661E);
                            // START 2018/03/15 K.Kojima [QC#24804,ADD]
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0661E);
                            }
                            // END 2018/03/15 K.Kojima [QC#24804,ADD]
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B) && bCMsg.contrEffThruDt_B.getValue().compareTo(parentBCMsg.contrEffThruDt_B.getValue()) > 0) {
                            bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0661E);
                            // START 2018/03/15 K.Kojima [QC#24804,ADD]
                            NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                            if (findACMsg != null) {
                                findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0661E);
                            }
                            // END 2018/03/15 K.Kojima [QC#24804,ADD]
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        }
                    }
                } else {
                    // Machine
                    if (ZYPCommonFunc.hasValue(bCMsg.contrEffFromDt_B) && bCMsg.contrEffFromDt_B.getValue().compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0) {
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                        if (findACMsg != null) {
                            findACMsg.contrEffFromDt_A.setErrorInfo(1, NSZM0660E);
                        }
                        bCMsg.contrEffFromDt_B.setErrorInfo(1, NSZM0660E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    } else if (ZYPCommonFunc.hasValue(bCMsg.contrEffThruDt_B) && bCMsg.contrEffThruDt_B.getValue().compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0) {
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                        if (findACMsg != null) {
                            findACMsg.contrEffThruDt_A.setErrorInfo(1, NSZM0660E);
                        }
                        bCMsg.contrEffThruDt_B.setErrorInfo(1, NSZM0660E);
                        addErroredDsContrDtlPk(dsContrDtlPk);
                        valid = INPUT_IS_INVALID;
                    }
                    // START 2018/06/07 K.Kim [QC#24857, ADD]
                    if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(bCMsg.dsContrDtlTpCd_B.getValue())) {
                        BigDecimal countBllgMtr = NSAL0300Query.getInstance().countBllgMtr(getGlobalCompanyCode(), dsContrDtlPk);
                        NSAL0300_ACMsg findACMsg = findACMsg(cMsg.A, dsContrDtlPk);
                        if (findACMsg != null && !ZYPCommonFunc.hasValue(findACMsg.mtrReadMethCd_A) && countBllgMtr.compareTo(BigDecimal.ZERO) != 0) {
                            findACMsg.mtrReadMethCd_A.setErrorInfo(1, ZZM9000E, new String[] {"Meter Method"});
                            addErroredDsContrDtlPk(dsContrDtlPk);
                            valid = INPUT_IS_INVALID;
                        }
                    }
                    // END 2018/06/07 K.Kim [QC#24857, ADD]
                }

                // START 2017/07/27 [QC#16889, MOD]
                // preSerNum = serNum;
                // preMdseCd = mdseCd;
                preDsContrDtlPk = dsContrDtlPk;
                // END 2017/07/27 [QC#16889, MOD]
            }
        }

        return valid;
    }

    // add start 2016/05/30 CSA Defect#447
    private boolean insertSvcTermCond(NSAL0300CMsg cMsg, BigDecimal dsContrDtlPk, SlaInfoBean infoBean, String svcTermAttrbNm) {
        String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(svcTermAttrbNm, getGlobalCompanyCode());
        if (svcTermCondAttrbNm != null) {
            //check SVC_TERM_COND is registered
            SVC_TERM_COND_ATTRBTMsgArray getSvcTermCondAttrb = NSAL0300Query.getInstance().getSvcTermCondAttrb(getGlobalCompanyCode(), svcTermCondAttrbNm);
            if (getSvcTermCondAttrb.getValidCount() > 0) {
                SVC_TERM_CONDTMsgArray getSvcTermCond = NSAL0300Query.getInstance().getSvcTermCond(getGlobalCompanyCode(), cMsg.dsContrPk.getValue(), dsContrDtlPk, getSvcTermCondAttrb.no(0).svcTermCondAttrbPk.getValue());
                if (getSvcTermCond.getValidCount() == 0) {
                    //insert SVC_TERM_COND
                    SVC_TERM_CONDTMsg inTMsg = new SVC_TERM_CONDTMsg();
                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(inTMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsContrPk, cMsg.dsContrPk);
                    ZYPEZDItemValueSetter.setValue(inTMsg.dsContrDtlPk, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(inTMsg.svcTermCondAttrbPk, getSvcTermCondAttrb.no(0).svcTermCondAttrbPk);
                    ZYPEZDItemValueSetter.setValue(inTMsg.svcTermAttrbMapValCd, infoBean.getTermCondOptValTxt());
                    S21FastTBLAccessor.insert(inTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Service Term Condition" });
                        return INPUT_IS_INVALID;
                    }
                }
            }
        }
        return INPUT_IS_VALID;
    }
    // add end 2016/05/30 CSA Defect#447

    // add start 2016/09/06 CSA Defect#11243
    private boolean checkInput_NSAL0300_NWAL2010(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return INPUT_IS_VALID;
    }
    // add end 2016/09/06 CSA Defect#11243

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0300CMsg cMsg = (NSAL0300CMsg) ezdCMsg;
            NSAL0300SMsg sMsg = (NSAL0300SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0300Scrn00_CMN_Save".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_Submit".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenForUpdate".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenForUpdate(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_AdditionalCharge".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_BillingCounters".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_CreditCardPo".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Schedule_Base".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Schedule_Usage".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_UpliftDetail".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Terminate".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Renew".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_StatusChange".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_CreditCard".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_SubContract".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_PricingEffectivity_Base".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_PricingEffectivity_Meter".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_AddNotes".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Terms".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_CompleteContract".equals(screenAplId)) {
                // START 2017/08/25 M.Kidokoro [QC#20527,MOD]
                // doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
                doProcess_NSAL0300Scrn00_OpenWin_CompleteContract(cMsg, sMsg);
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
            } else if ("NSAL0300Scrn00_Go".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
                doProcess_NSAL0300Scrn00_Go(cMsg, sMsg);
            // add start 2016/09/06 CSA Defect#11243
            } else if ("NSAL0300_NWAL2010".equals(screenAplId)) {
                doProcess_NSAL0300_NWAL2010(cMsg, sMsg);
            // add end 2016/09/06 CSA Defect#11243
//            } else if ("NSAL0300Scrn00_CMN_ColSave".equals(screenAplId)) {
//                ZYPGUITableColumn.setColData(cMsg, sMsg);
//            } else if ("NSAL0300Scrn00_CMN_ColClear".equals(screenAplId)) {
//                ZYPGUITableColumn.clearColData(cMsg, sMsg);
            // Add Start 2018/01/10 QC#18552
            } else if ("NSAL0300Scrn00_OpenWin_Escalation".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            // Add End 2018/01/10 QC#18552
            // Add Start 2024/03/22 QC#63463
            } else if ("NSAL0300Scrn00_Revert".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Revert(cMsg, sMsg);
            // Add End 2024/03/22 QC#63463
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSAL0300Scrn00_CMN_Save(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        if (DISPLAY_MODE_FREEZE.equals(cMsg.xxModeCd_FU.getValue())) {
            return;
        }
        // mod start 2017/01/12 CSA QC#16792
        List<String> functionList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_ID);
        if (!functionList.contains(FUNC_ID_UPD) && !functionList.contains(FUNC_ID_SHIP_TO) && functionList.contains(FUNC_ID_INQ)) {
            return;
        }
        // mod end 2017/01/12 CSA QC#16792
        prepareInputParams(cMsg, sMsg);
        if (NSAL0300CommonLogic.hasError(cMsg)) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        // START 2016/01/21 T.Tomita [QC#2182, ADD]
        if (!NSAL0300CommonLogic.checkSvcBrResrcReln(glblCmpyCd, cMsg, sMsg)) {
            if (!ZYPCommonFunc.hasValue(cMsg.xxRsltFlg_RP) || !ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg_RP.getValue())) {
                // mod start 2016/04/08 CSA Defect#5312,5313
                //cMsg.svcContrBrCd.setErrorInfo(2, NSAM0419W);
                //cMsg.contrAdminPsnCd.setErrorInfo(2, NSAM0419W);
                cMsg.xxDplyByCdNmCnctTxt.setErrorInfo(2, NSAM0419W);
                cMsg.xxPsnNm.setErrorInfo(2, NSAM0419W);
                // mod start 2016/04/08 CSA Defect#5312,5313
                cMsg.setMessageInfo(NSAM0419W);
                ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg_RP, ZYPConstant.FLG_ON_Y);
                return;
            }
        }
        cMsg.xxRsltFlg_RP.clear();
        // END 2016/01/21 T.Tomita [QC#2182, ADD]

        String dsContrCatgCd = cMsg.dsContrCatgCd.getValue();
        // START 2018/07/17 [QC#25959, ADD]
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            String minFromDt = "99991231";
            String maxThruDt = "";
            for (int aIdx = 0 ; aIdx < sMsg.A.getValidCount(); aIdx++) {
                if (minFromDt.compareTo(sMsg.A.no(aIdx).contrEffFromDt_A.getValue()) > 0) {
                    minFromDt = sMsg.A.no(aIdx).contrEffFromDt_A.getValue();
                }
                if (maxThruDt.compareTo(sMsg.A.no(aIdx).contrEffThruDt_A.getValue()) < 0) {
                    maxThruDt = sMsg.A.no(aIdx).contrEffThruDt_A.getValue();
                }
            }
            if (ZYPCommonFunc.hasValue(maxThruDt)) {
                for (int bIdx = 0 ; bIdx < cMsg.B.getValidCount(); bIdx++) {
                    if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())
                            ||    DS_CONTR_DTL_TP.FLEET.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())    ) {
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(bIdx).contrEffFromDt_B, minFromDt);
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(bIdx).contrEffThruDt_B, maxThruDt);
                    }
                }
                ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt, minFromDt);
                ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt, maxThruDt);
            }
        }
        // END   2018/07/17 [QC#25959, ADD]
        NSAL0300ContractCreator creator;
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            creator = new NSAL0300RegularContractCreator(cMsg, sMsg);
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            creator = new NSAL0300FleetContractCreator(cMsg, sMsg);
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            creator = new NSAL0300AggregateContractCreator(cMsg, sMsg);
        } else if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            creator = new NSAL0300WarrantyContractCreator(cMsg, sMsg);
        } else {
            return;
        }
        creator.create();
        if (creator.hasError()) {
            return;
        }

        // START 2016/07/11 T.Kanasaka [QC#9019, MOD]
        NSAL0300CommonLogic.deleteDsContrDtl(glblCmpyCd, cMsg, sMsg);
        if (NSAL0300CommonLogic.hasError(cMsg)) {
            return;
        }

        // START 2016/05/20 T.Kanasaka [QC#5942, ADD]
        if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) && cMsg.A.getValidCount() > 0 && creator.getUsgSchdItemUpdMapDtl().isEmpty()) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            S21SsmEZDResult rslt = query.getFleetMachineNoSchedule(glblCmpyCd, cMsg.dsContrPk.getValue());
            List<BigDecimal> rsltList = (List<BigDecimal>) rslt.getResultObject();
            if (rslt.getQueryResultCount() > 0) {
                creator.getUsgSchdItemUpdMapDtl().put(cMsg.B.no(0).dsContrDtlPk_B.getValue(), true);
            }
        }
        // END 2016/05/20 T.Kanasaka [QC#5942, ADD]

        // START 2017/09/19 K.Kitachi [QC#21149, ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && cMsg.A.getValidCount() > 0 && creator.getUsgSchdItemUpdMapDtl().isEmpty()) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            S21SsmEZDResult rslt = query.getAggregateMachineNoSchedule(glblCmpyCd, cMsg.dsContrPk.getValue());
            List<BigDecimal> rsltList = (List<BigDecimal>) rslt.getResultObject();
            if (rslt.getQueryResultCount() > 0) {
                creator.getUsgSchdItemUpdMapDtl().put(cMsg.B.no(0).dsContrDtlPk_B.getValue(), true);
            }
        }
        // END 2017/09/19 K.Kitachi [QC#21149, ADD]

        // START 2016/06/20 T.Kanasaka [QC#9019, ADD]
//        NSAL0300CommonLogic.deleteDsContrDtl(glblCmpyCd, cMsg, sMsg);
//        if (NSAL0300CommonLogic.hasError(cMsg)) {
//            return;
//        }
        // END 2016/07/11 T.Kanasaka [QC#9019, MOD]

        if (!NSAL0300CommonLogic.callContractBillingScheduleAPIforDelete(glblCmpyCd, cMsg, sMsg)) {
            return;
        }
        // END 2016/06/20 T.Kanasaka [QC#9019, ADD]

        // START 2017/10/05 K.Kojima [QC#20523,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.poDt)) {
            NSAL0300CommonLogic.releaseRenewalHoldForPO(glblCmpyCd, cMsg.dsContrPk.getValue(), ZYPDateUtil.getSalesDate(glblCmpyCd));
        }
        // END 2017/10/05 K.Kojima [QC#20523,ADD]

        // Save display controll
        NSAL0300DisplayControllBean displayBean = NSAL0300CommonLogic.saveDisplay(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.B);
        // START 2017/09/26 K.Kojima [QC#21221,MOD]
        // NSAL0300CommonLogic.searchDetailForB(glblCmpyCd, cMsg);
        NSAL0300CommonLogic.searchDetailForB(glblCmpyCd, cMsg, ZYPConstant.FLG_ON_Y);
        // END 2017/09/26 K.Kojima [QC#21221,MOD]

        // Restore display controll
        NSAL0300CommonLogic.restoreDisplayForB(cMsg, displayBean);

        if (!NSAL0300CommonLogic.callContractBillingScheduleAPI(glblCmpyCd, cMsg, creator.getBaseSchdItemUpdFlgContr(), creator.getBaseSchdItemUpdMapDtl(), creator.getUsgSchdItemUpdFlgContr(), creator.getUsgSchdItemUpdMapDtl(), creator.getUsgSchdItemUpdMapMtr())) {
            return;
        }
        NSAL0300CommonLogic.setDisplayMode(glblCmpyCd, cMsg, sMsg);
        cMsg.setMessageInfo(ZZM8100I);
    }

    // START 2024/03/22 Y.Tamai[QC#63463,ADD]
    private void doProcess_NSAL0300Scrn00_Revert(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        boolean notRevertFlg = false;

        // Validation check
        if (checkNotExistsActiveContract(cMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y)) {
            notRevertFlg = true;
        }
        if (notRevertFlg == false && checkExistsContrBillingHold(cMsg, getGlobalCompanyCode())) {
            notRevertFlg = true;
        }
        if (notRevertFlg) {
            cMsg.setMessageInfo(NSAM0794E);
            return;
        }

        // Revert backup source
        if (!revertToBackupSource(cMsg, getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y)) {
            return;
        }
        // Insert into DS_CONTR_TRK
        if (!insertIntoTrackingTable(cMsg, getGlobalCompanyCode())) {
            return;
        }
        cMsg.setMessageInfo(ZZM8100I);
    }
    // END 2024/03/22 Y.Tamai[QC#63463,ADD]
    private void doProcess_NSAL0300Scrn00_CMN_Submit(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);

        // Update DS Contract
        NSAL0300Query query = NSAL0300Query.getInstance();
        DS_CONTRTMsg dsContrTMsg = query.getDsContr(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract" });
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_C.getValue(), cMsg.ezUpTimeZone_C.getValue(), dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        String nextStatus = null;
        if (slsDt.compareTo(dsContrTMsg.contrVrsnEffFromDt.getValue()) >= 0) {
            nextStatus = DS_CONTR_STS.EFFECTIVE;
        } else {
            nextStatus = DS_CONTR_STS.APPROVED;
        }
        ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrStsCd, nextStatus);
        S21FastTBLAccessor.update(dsContrTMsg);
        String rtnCd = dsContrTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract" });
            return;
        }
        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
            return;
        }

        // Update DS Contract Detail
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = query.getDsContrDtlList(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrDtlTMsgArray == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Detail" });
            return;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (slsDt.compareTo(dsContrDtlTMsg.contrEffFromDt.getValue()) >= 0) {
                nextStatus = DS_CONTR_DTL_STS.ACTIVE;
            } else {
                nextStatus = DS_CONTR_DTL_STS.SIGNED;
            }
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, nextStatus);
            S21FastTBLAccessor.update(dsContrDtlTMsg);
            rtnCd = dsContrDtlTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Detail" });
                return;
            }
            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                return;
            }

            // Update DS Contract Billing Meter
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, nextStatus);
                    S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
                    rtnCd = dsContrBllgMtrTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Billing Meter" });
                        return;
                    }
                    if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                        return;
                    }
                }
            }

            // Update DS Contract Price Effective
            DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffTMsgArray = query.getDsContrPrcEffList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrPrcEffTMsgArray != null) {
                for (int j = 0; j < dsContrPrcEffTMsgArray.getValidCount(); j++) {
                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = dsContrPrcEffTMsgArray.no(j);

                    // START 2016/01/28 T.Kanasaka [QC#3533, UPD]
                    if (dsContrPrcEffTMsg.contrPrcEffThruDt.getValue().compareTo(slsDt) >= 0 && slsDt.compareTo(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue()) >= 0) {
                    // END 2016/01/28 T.Kanasaka [QC#3533, UPD]
                        nextStatus = DS_CONTR_DTL_STS.ACTIVE;
                    } else {
                        nextStatus = DS_CONTR_DTL_STS.SIGNED;
                    }
                    ZYPEZDItemValueSetter.setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, nextStatus);
                    S21FastTBLAccessor.update(dsContrPrcEffTMsg);
                    rtnCd = dsContrPrcEffTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective" });
                        return;
                    }
                    if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffPk
                            .getValue(), dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), dsContrPrcEffTMsg.contrPrcEffThruDt.getValue(), dsContrPrcEffTMsg.baseChrgFlg.getValue())) {
                        return;
                    }
                }
            }
        }
        cMsg.setMessageInfo(ZZM8100I);
    }

    private void doProcess_NSAL0300Scrn00_OpenForUpdate(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        // START 2016/02/10 T.Aoyagi [QC2956, DEL]
//        BigDecimal qaHoldEntryTmAot = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_QA_HOLD_ENTRY_TM_AOT, glblCmpyCd);
//        if (!ZYPCommonFunc.hasValue(qaHoldEntryTmAot)) {
//            qaHoldEntryTmAot = DEF_QA_HOLD_ENTRY_TM_AOT;
//        }
//        int tmAot = qaHoldEntryTmAot.intValue();
//        String lastUpdTs = NSAL0300CommonLogic.addMinute(ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_FULL), (-1) * tmAot);
        // END 2016/02/10 T.Aoyagi [QC2956, DEL]

        NSAL0300Query query = NSAL0300Query.getInstance();

        // START 2016/02/10 T.Aoyagi [QC2956, DEL]
//        SVC_MEMOTMsg svcMemoTMsg = query.getSvcMemoForQAHld(glblCmpyCd, cMsg.dsContrPk.getValue(), lastUpdTs);
//        if (svcMemoTMsg == null && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
//            cMsg.setMessageInfo(NSAM0391W);
//            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
//            return;
//        }
        // END 2016/02/10 T.Aoyagi [QC2956, DEL]
        // START 2024/03/22 Y.Tamai [QC63463, ADD]
        BigDecimal contrBakSmrySq = ZYPOracleSeqAccessor.getNumberBigDecimal("CONTR_BAK_SMRY_SQ");
        BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
        Map<String, Object> resultMap = NSAL0300Query.getInstance().getActiveContrBakSmryPk(glblCmpyCd, cMsg.dsContrPk.getValue(), ZYPConstant.FLG_ON_Y);
        List<BigDecimal> dsContrDtlPkList = NSAL0300Query.getInstance().getDsContrDtlPkList(dsContrPk, glblCmpyCd);
        List<BigDecimal> dsContrBllgMtrPkList = NSAL0300Query.getInstance().getDsContrBllgMtrPkList(dsContrPk, glblCmpyCd);
        List<BigDecimal> dsSubContrPkList = NSAL0300Query.getInstance().getDsSubContrPkList(dsContrPk, glblCmpyCd);

        if (!executeBackupForDsContrPk(glblCmpyCd, cMsg, contrBakSmrySq, resultMap)) {
            return;
        }
        if (!executeBackupForDsContrDtlPk(glblCmpyCd, cMsg, contrBakSmrySq, resultMap, dsContrPk)) {
            return;
        }
        if (!executeBackupForDsContrMtrPk(glblCmpyCd, cMsg, contrBakSmrySq, resultMap, dsContrPk, dsContrBllgMtrPkList)) {
            return;
        }
        if (!executeBackupForDsContrSubPk(glblCmpyCd, cMsg, contrBakSmrySq, resultMap, dsContrPk, dsSubContrPkList)) {
            return;
        }
        // END 2024/03/22 Y.Tamai [QC63463, ADD]

        // Update DS Contract
        DS_CONTRTMsg dsContrTMsg = query.getDsContr(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrTMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract" });
            return;
        }
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_C.getValue(), cMsg.ezUpTimeZone_C.getValue(), dsContrTMsg.ezUpTime.getValue(), dsContrTMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        ZYPEZDItemValueSetter.setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(dsContrTMsg);
        String rtnCd = dsContrTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract" });
            return;
        }
        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
            return;
        }

        // Update DS Contract Detail
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = query.getDsContrDtlList(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrDtlTMsgArray == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Detail" });
            return;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            // START 2017/06/14 K.Kitachi [QC#19142, ADD]
            if (DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END 2017/06/14 K.Kitachi [QC#19142, ADD]
            // START 2017/07/25 K.Yamada [QC#20131, ADD]
            if (DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                continue;
            }
            // END 2017/07/25 K.Yamada [QC#20131, ADD]
            ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(dsContrDtlTMsg);
            rtnCd = dsContrDtlTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Detail" });
                return;
            }
            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                return;
            }

            // Update DS Contract Billing Meter
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
                    S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
                    rtnCd = dsContrBllgMtrTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Billing Meter" });
                        return;
                    }
                    if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                        return;
                    }
                }
            }

            // Update DS Contract Price Effective
            DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffTMsgArray = query.getDsContrPrcEffList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrPrcEffTMsgArray != null) {
                for (int j = 0; j < dsContrPrcEffTMsgArray.getValidCount(); j++) {
                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = dsContrPrcEffTMsgArray.no(j);
                    ZYPEZDItemValueSetter.setValue(dsContrPrcEffTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
                    S21FastTBLAccessor.update(dsContrPrcEffTMsg);
                    rtnCd = dsContrPrcEffTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective" });
                        return;
                    }
                    if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffPk
                            .getValue(), dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), dsContrPrcEffTMsg.contrPrcEffThruDt.getValue(), dsContrPrcEffTMsg.baseChrgFlg.getValue())) {
                        return;
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_OFF_N);
        cMsg.setMessageInfo(ZZM8100I);
    }

    private void doProcess_NSAL0300Scrn00_Go(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        NSAL0300Query query = NSAL0300Query.getInstance();
        DS_CONTR_ACTTMsg dsContrActTMsg = query.getDsContrAct(glblCmpyCd, cMsg.dsContrActCd.getValue());
        if (dsContrActTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.bizAppId, dsContrActTMsg.bizAppId);
        }
    }

    // add start 2016/09/06 CSA Defect#11243
    private void doProcess_NSAL0300_NWAL2010(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            return;
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        Map<String, Object> crCardDiffData = query.getCrCardDiffData(getGlobalCompanyCode(), cMsg.dsContrPk.getValue());
        if (crCardDiffData == null || crCardDiffData.isEmpty()) {
            return;
        }

        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        if (!ZYPCommonFunc.hasValue(cMsg.dsContrCrCardPoPk)) {
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrCrCardPoPk_CC)) {
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
            insertDsContrCrCardPo(cMsg, crCardDiffData);
        } else {
            updateDsContrCrCardPo(cMsg, crCardDiffData);
        }
        // START 2017/03/08 K.Ochiai [QC#17453, ADD]
        if (ZYPCommonFunc.hasValue((String) crCardDiffData.get("CR_CARD_CUST_REF_NUM"))) {
            setValue(cMsg.pmtTermCashDiscCd, PMT_TERM_CASH_DISC.CREDIT_CARD);
            PMT_TERM_CASH_DISCTMsg cctMsg = query.getPmtTermCashDisc(getGlobalCompanyCode(), cMsg.pmtTermCashDiscCd.getValue());
            setValue(cMsg.pmtTermCashDiscDescTxt, cctMsg.pmtTermCashDiscDescTxt);
        }
        // END 2017/03/08 K.Ochiai [QC#17453, ADD]
    }

    private void insertDsContrCrCardPo(NSAL0300CMsg cMsg, Map<String, Object> crCardDiffData) {

        DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
        setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(tMsg.dsContrCrCardPoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ));
        setValue(tMsg.dsContrPk, cMsg.dsContrPk);
        // START 2019/01/09 K.Kitachi [QC#26928, DEL]
//        setValue(tMsg.custPoNum, cMsg.custPoNum);
//        setValue(tMsg.poDt, cMsg.poDt);
        // END 2019/01/09 K.Kitachi [QC#26928, DEL]
        setValue(tMsg.crCardCustRefNum, (String) crCardDiffData.get("CR_CARD_CUST_REF_NUM"));
        setValue(tMsg.crCardExprYrMth, (String) crCardDiffData.get("CR_CARD_EXPR_YR_MTH"));
        setValue(tMsg.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        setValue(tMsg.dsContrMachLvlNum, MACH_LVL_NUM_1);
        // START 2019/01/09 K.Kitachi [QC#26928, ADD]
        setValue(tMsg.crCardFlg, ZYPConstant.FLG_ON_Y);
        // END 2019/01/09 K.Kitachi [QC#26928, ADD]

        S21FastTBLAccessor.insert(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Credit Card PO" });
            return;
        }
        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        setValue(cMsg.ezUpTime_P, tMsg.ezUpTime);
//        setValue(cMsg.ezUpTimeZone_P, tMsg.ezUpTimeZone);
//        setValue(cMsg.dsContrCrCardPoPk, tMsg.dsContrCrCardPoPk);
        setValue(cMsg.ezUpTime_CC, tMsg.ezUpTime);
        setValue(cMsg.ezUpTimeZone_CC, tMsg.ezUpTimeZone);
        setValue(cMsg.dsContrCrCardPoPk_CC, tMsg.dsContrCrCardPoPk);
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
        setValue(cMsg.crCardCustRefNum, tMsg.crCardCustRefNum);
        if (ZYPCommonFunc.hasValue(tMsg.crCardExprYrMth) && tMsg.crCardExprYrMth.getValue().length() >= 6) {
            setValue(cMsg.xxMthDt_CC, tMsg.crCardExprYrMth.getValue().substring(4, 6));
            setValue(cMsg.xxYrDt_CC, tMsg.crCardExprYrMth.getValue().substring(0, 4));
        }
    }

    private void updateDsContrCrCardPo(NSAL0300CMsg cMsg, Map<String, Object> crCardDiffData) {

        NSAL0300Query query = NSAL0300Query.getInstance();
        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        DS_CONTR_CR_CARD_POTMsg tMsg = query.getDsContrCrCardPo(getGlobalCompanyCode(), cMsg.dsContrCrCardPoPk.getValue());
        DS_CONTR_CR_CARD_POTMsg tMsg = query.getDsContrCrCardPo(getGlobalCompanyCode(), cMsg.dsContrCrCardPoPk_CC.getValue());
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
        if (tMsg == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Credit Card PO" });
            return;
        }
        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_P.getValue(), cMsg.ezUpTimeZone_P.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
        if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_CC.getValue(), cMsg.ezUpTimeZone_CC.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
            cMsg.setMessageInfo(ZZZM9004E);
            return;
        }

        setValue(tMsg.crCardCustRefNum, (String) crCardDiffData.get("CR_CARD_CUST_REF_NUM"));
        setValue(tMsg.crCardExprYrMth, (String) crCardDiffData.get("CR_CARD_EXPR_YR_MTH"));

        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Credit Card PO" });
            return;
        }
        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        setValue(cMsg.ezUpTime_P, tMsg.ezUpTime);
//        setValue(cMsg.ezUpTimeZone_P, tMsg.ezUpTimeZone);
        setValue(cMsg.ezUpTime_CC, tMsg.ezUpTime);
        setValue(cMsg.ezUpTimeZone_CC, tMsg.ezUpTimeZone);
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]
        setValue(cMsg.crCardCustRefNum, tMsg.crCardCustRefNum);
        if (ZYPCommonFunc.hasValue(tMsg.crCardExprYrMth) && tMsg.crCardExprYrMth.getValue().length() >= 6) {
            setValue(cMsg.xxMthDt_CC, tMsg.crCardExprYrMth.getValue().substring(4, 6));
            setValue(cMsg.xxYrDt_CC, tMsg.crCardExprYrMth.getValue().substring(0, 4));
        }
    }
    // add end 2016/09/06 CSA Defect#11243

    private void prepareInputParams(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        for (int a = 0; a < cMsg.A.getValidCount(); a++) {
            // START 2017/07/27 [QC#16889, MOD]
            // String serNum = cMsg.A.no(a).serNum_A.getValue();
            // String mdseCd = cMsg.A.no(a).mdseCd_A.getValue();
            BigDecimal dsContrDtlPk = cMsg.A.no(a).dsContrDtlPk_A.getValue();
            // END 2017/07/27 [QC#16889, MOD]
            for (int b = 0; b < cMsg.B.getValidCount(); b++) {
                // START 2017/07/27 [QC#16889, MOD]
                // String cmpSerNum = cMsg.B.no(b).serNum_B.getValue();
                // String cmpMdseCd = cMsg.B.no(b).mdseCd_B.getValue();
                BigDecimal cmpDsContrDtlPk = cMsg.B.no(b).dsContrDtlPk_B.getValue();
                // if (NSAL0300CommonLogic.isEqualMach(serNum, mdseCd, cmpSerNum, cmpMdseCd)) {
                if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cmpDsContrDtlPk)) {
                // END 2017/07/27 [QC#16889, MOD]
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(b).mtrReadMethCd_B, cMsg.A.no(a).mtrReadMethCd_A);
                    // Add Start 2018/01/15 QC#18552
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(b).basePrcDealAmt_B, cMsg.A.no(a).basePrcDealAmt_A);
                    // Add End 2018/01/15 QC#18552
                    break;
                }
            }
        }
    }

    private static class NSAL0300ContractCreator {

        protected NSAL0300CMsg cMsg;

        protected NSAL0300SMsg sMsg;

        protected String glblCmpyCd;

        protected String slsDt;

        protected String userId;

        // START 2016/03/08 T.Kanasaka [QC2208, ADD]
        protected boolean baseSchdItemUpdFlgContr = false;

        protected Map<BigDecimal, Boolean> baseSchdItemUpdMapDtl = new HashMap<BigDecimal, Boolean>();

        protected boolean usgSchdItemUpdFlgContr = false;

        protected Map<BigDecimal, Boolean> usgSchdItemUpdMapDtl = new HashMap<BigDecimal, Boolean>();

        protected Map<BigDecimal, Boolean> usgSchdItemUpdMapMtr = new HashMap<BigDecimal, Boolean>();
        // END 2016/03/08 T.Kanasaka [QC2208, ADD]

        private BigDecimal dsContrPk;

        private Map<String, BigDecimal> dsContrDtlPkMap;

        public NSAL0300ContractCreator(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
            this.cMsg = cMsg;
            this.sMsg = sMsg;
            this.glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BIZ_ID);
            this.userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
            this.dsContrDtlPkMap = new HashMap<String, BigDecimal>();
        }

        // START 2016/03/08 T.Kanasaka [QC2208, ADD]
        public boolean getBaseSchdItemUpdFlgContr() {
            return baseSchdItemUpdFlgContr;
        }

        public Map<BigDecimal, Boolean> getBaseSchdItemUpdMapDtl() {
            return baseSchdItemUpdMapDtl;
        }

        public boolean getUsgSchdItemUpdFlgContr() {
            return usgSchdItemUpdFlgContr;
        }

        public Map<BigDecimal, Boolean> getUsgSchdItemUpdMapDtl() {
            return usgSchdItemUpdMapDtl;
        }

        public Map<BigDecimal, Boolean> getUsgSchdItemUpdMapMtr() {
            return usgSchdItemUpdMapMtr;
        }
        // END 2016/03/08 T.Kanasaka [QC2208, ADD]

        public void create() {
            setupDummyMach();
            if (hasError()) {
                return;
            }
            mergeDsContr();
            if (hasError()) {
                return;
            }
            // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//            mergeDsContrCrCardPo();
//            if (hasError()) {
//                return;
//            }
            mergeDsContrCrCardPoForPo();
            if (hasError()) {
                return;
            }
            mergeDsContrCrCardPoForCrCard();
            if (hasError()) {
                return;
            }
            // END 2019/01/09 K.Kitachi [QC#26928, MOD]
            mergeDsContrRnwEscl();
            if (hasError()) {
                return;
            }
            mergeDsContrDtl();
            if (hasError()) {
                return;
            }
//            mergeContrCovSvc();
//            if (hasError()) {
//                return;
//            }
            mergeDsContrBllgMtr();
            if (hasError()) {
                return;
            }
            mergeContrXsCopy();
            if (hasError()) {
                return;
            }

            // START 2017/02/07 [QC#17275, ADD]
            mergeContrPhysBllgReln();
            if (hasError()) {
                return;
            }
            // END   2017/02/07 [QC#17275, ADD]

            // START 2016/10/31 T.Kanasaka [QC#15136, ADD]
            mergeSvcTermCond();
            if (hasError()) {
                return;
            }
            // END 2016/10/31 T.Kanasaka [QC#15136, ADD]
            
            // START 2023/02/09 R.Takau [QC#55645, ADD]
            mergeDsContrBankAcctRelen();
            if (hasError()) {
                return;
            }
            // END 2023/02/09 R.Takau [QC#55645, ADD]
        }

        protected static void setupDummyMach(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, String glblCmpyCd, String dsContrDtlTpCd) {

            if (cMsg.B.getValidCount() == 0) {
                // START 2016/02/24 [QC3697, MOD]
                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), dsContrDtlTpCd, null, cMsg.contrVrsnEffFromDt.getValue(), cMsg.contrVrsnEffThruDt.getValue(),
//                        cMsg.baseBllgCycleCd.getValue(), DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, null, null);
                NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), dsContrDtlTpCd, null, cMsg.contrVrsnEffFromDt.getValue(), cMsg.contrVrsnEffThruDt.getValue(),
                        cMsg.baseBllgCycleCd.getValue(), DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, null, null, null, null);
                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                // END 2016/02/24 [QC3697, MOD]
            }
        }

        // START 2016/03/08 T.Kanasaka [QC2208, ADD]
        protected void checkSchdItemUpd(boolean isChanged, boolean baseFlg, boolean usgFlg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
            if (!isChanged) {
                return;
            }

            if (baseFlg) {
                if (dsContrDtlPk == null) {
                    baseSchdItemUpdFlgContr = true;
                } else {
                    baseSchdItemUpdMapDtl.put(dsContrDtlPk, true);
                }
            }

            if (usgFlg) {
                if (dsContrDtlPk == null && dsContrBllgMtrPk == null) {
                    usgSchdItemUpdFlgContr = true;
                } else if (dsContrDtlPk != null && dsContrBllgMtrPk == null) {
                    usgSchdItemUpdMapDtl.put(dsContrDtlPk, true);
                } else {
                    usgSchdItemUpdMapMtr.put(dsContrBllgMtrPk, true);
                }
            }
        }
        // END 2016/03/08 T.Kanasaka [QC2208, ADD]

        protected BigDecimal getDsContrPk() {
            if (ZYPCommonFunc.hasValue(dsContrPk)) {
                return dsContrPk;
            } else {
                if (ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
                    dsContrPk = cMsg.dsContrPk.getValue();
                } else {
                    dsContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_SQ);
                }
                return dsContrPk;
            }
        }

        protected void setupDummyMach() {
        }

        protected String getDsContrStsCd(NSAL0300CMsg cMsg) {
            return DS_CONTR_STS.DRAFT;
        }

        // START 2016/03/08 T.Kanasaka [QC2208, MOD]
        protected void mergeDsContr() {
            if (ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
                NSAL0300Query query = NSAL0300Query.getInstance();
                DS_CONTRTMsg tMsg = query.getDsContr(glblCmpyCd, cMsg.dsContrPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract" });
                    return;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_C.getValue(), cMsg.ezUpTimeZone_C.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }

                    NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
                    diffSetter.setValue(tMsg.dsContrNm, cMsg.dsContrNm);
                    diffSetter.setValue(tMsg.svcContrBrCd, cMsg.svcContrBrCd);
                    diffSetter.setValue(tMsg.tocCd, cMsg.tocCd);
                    diffSetter.setValue(tMsg.contrVrsnEffFromDt, cMsg.contrVrsnEffFromDt);
                    // TODO Max Eff Thru Dt
                    diffSetter.setValue(tMsg.contrVrsnEffThruDt, cMsg.contrVrsnEffThruDt);
                    diffSetter.setValue(tMsg.ccyCd, cMsg.ccyCd);
                    diffSetter.setValue(tMsg.dsContrLastUpdDt, slsDt);
                    diffSetter.setValue(tMsg.dsContrLastUpdPsnCd, userId);
                    checkSchdItemUpd(diffSetter.setValue(tMsg.dsAcctNum, cMsg.dsAcctNum), true, true, null, null);
                    diffSetter.setValue(tMsg.leaseCmpyCd, cMsg.leaseCmpyCd);
                    diffSetter.setValue(tMsg.baseChrgToLeaseCmpyFlg, NSAL0300CommonLogic.flg(cMsg.baseChrgToLeaseCmpyFlg));
                    diffSetter.setValue(tMsg.usgChrgToLeaseCmpyFlg, NSAL0300CommonLogic.flg(cMsg.usgChrgToLeaseCmpyFlg));
                    // START 2018/05/15 K.Kitachi [QC#24265, ADD]
                    diffSetter.setValue(tMsg.cfsLeaseNumCmntTxt, cMsg.cfsLeaseNumCmntTxt);
                    // END 2018/05/15 K.Kitachi [QC#24265, ADD]
                    // add start 2018/11/07 K.Fujimoto QC#28627
                    diffSetter.setValue(tMsg.contrLinkNum, cMsg.contrLinkNum);
                    // add end   2018/11/07 K.Fujimoto QC#28627
                    diffSetter.setValue(tMsg.dsContrBllgStsCd, cMsg.dsContrBllgStsCd);
                    diffSetter.setValue(tMsg.pmtTermCashDiscCd, cMsg.pmtTermCashDiscCd);
                    diffSetter.setValue(tMsg.mtrEstTpCd, cMsg.mtrEstTpCd);
                    // START 2022/03/22 [QC#59683, MOD]
//                    diffSetter.setValue(tMsg.invSeptBaseUsgFlg, NSAL0300CommonLogic.switchFlg(cMsg.xxSelFlg_UT));
                    diffSetter.setValue(tMsg.invSeptBaseUsgFlg, NSAL0300CommonLogic.getInvSeptBaseUsgFlg(glblCmpyCd, cMsg.dsInvTgtrTpCd.getValue()));
                    // END   2022/03/22 [QC#59683, MOD]
                    // START 2022/03/22 [QC#59683, ADD]
                    diffSetter.setValue(tMsg.dsInvTgtrTpCd, cMsg.dsInvTgtrTpCd);
                    // END   2022/03/22 [QC#59683, ADD]
                    diffSetter.setValue(tMsg.dsContrCatgCd, cMsg.dsContrCatgCd);
                    diffSetter.setValue(tMsg.dsContrClsCd, cMsg.dsContrClsCd);
                    diffSetter.setValue(tMsg.dsContrStsCd, cMsg.dsContrStsCd);
                    diffSetter.setValue(tMsg.svcContrRefCmntTxt, cMsg.svcContrRefCmntTxt);
                    diffSetter.setValue(tMsg.contrAdminPsnCd, cMsg.contrAdminPsnCd);
                    diffSetter.setValue(tMsg.ctacPsnPk, cMsg.ctacPsnPk_CP);
                    diffSetter.setValue(tMsg.contrInvCmntTxt, cMsg.contrInvCmntTxt);
                    if (!ZYPCommonFunc.hasValue(tMsg.cratDraftPsnCd)) {
                        diffSetter.setValue(tMsg.cratDraftPsnCd, userId);
                    }
                    diffSetter.setValue(tMsg.svcLineBizCd, cMsg.svcLineBizCd);
                    diffSetter.setValue(tMsg.dsContrEdiCd, cMsg.dsContrEdiCd);
                    diffSetter.setValue(tMsg.svcPgmMdseCd, cMsg.svcPgmMdseCd);
                    diffSetter.setValue(tMsg.dsContrRptGrpNum, cMsg.dsContrRptGrpNum);
                    diffSetter.setValue(tMsg.altPayerCustCd, cMsg.altPayerCustCd);
                    diffSetter.setValue(tMsg.baseBllgCycleCd, cMsg.baseBllgCycleCd);
                    diffSetter.setValue(tMsg.mtrBllgCycleCd, cMsg.mtrBllgCycleCd);
                    // add start 2016/07/21 CSA Defect#11720
                    diffSetter.setValue(tMsg.contrDurnAot, cMsg.contrDurnAot);
                    diffSetter.setValue(tMsg.bllgCycleUomCd, cMsg.bllgCycleUomCd);
                    // add end 2016/07/21 CSA Defect#11720

                    // TODO
                    checkSchdItemUpd(diffSetter.setValue(tMsg.prcAllocByMachQtyFlg, NSAL0300CommonLogic.flg(cMsg.prcAllocByMachQtyFlg)), false, true, null, null);
                    if (diffSetter.set()) {
                        S21FastTBLAccessor.update(tMsg);
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract" });
                            return;
                        }
                        // add start 2018/11/07 K.Fujimoto QC#28627
                        if(ZYPCommonFunc.hasValue(cMsg.contrLinkNum) && !cMsg.contrLinkNum.getValue().equals(cMsg.dsContrNum.getValue())){
                            if(!updateParentDsContract()){
                            	return;
                            };
                        }
                        // add end   2018/11/07 K.Fujimoto QC#28627
                        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
                            return;
                        }
                    }
                }
            } else {
                DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, getDsContrPk());
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrNum, ZYPExtnNumbering.getUniqueID(glblCmpyCd, DS_CONTR_NUM));
                // add start 2018/11/07 K.Fujimoto QC#28627
                ZYPEZDItemValueSetter.setValue(tMsg.contrLinkNum, cMsg.contrLinkNum);
                // add end   2018/11/07 K.Fujimoto QC#28627
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrSqNum, DEF_DS_CONTR_SQ_NUM);
                ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, cMsg.svcContrBrCd);
                ZYPEZDItemValueSetter.setValue(tMsg.tocCd, cMsg.tocCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrNm, cMsg.dsContrNm);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrStsCd, getDsContrStsCd(cMsg));
                ZYPEZDItemValueSetter.setValue(tMsg.contrVrsnEffFromDt, cMsg.contrVrsnEffFromDt);
                ZYPEZDItemValueSetter.setValue(tMsg.contrVrsnEffThruDt, cMsg.contrVrsnEffThruDt);
                ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, cMsg.ccyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrCratDt, slsDt);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrCratPsnCd, userId);
                ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum, cMsg.dsAcctNum);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrTpCd, DEF_DS_CONTR_TP_CD);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrCatgCd, cMsg.dsContrCatgCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrLastUpdDt, slsDt);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrLastUpdPsnCd, userId);
                ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyCd, cMsg.leaseCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.baseChrgToLeaseCmpyFlg, NSAL0300CommonLogic.flg(cMsg.baseChrgToLeaseCmpyFlg));
                ZYPEZDItemValueSetter.setValue(tMsg.usgChrgToLeaseCmpyFlg, NSAL0300CommonLogic.flg(cMsg.usgChrgToLeaseCmpyFlg));
                // START 2018/05/15 K.Kitachi [QC#24265, ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.cfsLeaseNumCmntTxt, cMsg.cfsLeaseNumCmntTxt);
                // END 2018/05/15 K.Kitachi [QC#24265, ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgStsCd, cMsg.dsContrBllgStsCd);
                ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, cMsg.pmtTermCashDiscCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrEstTpCd, cMsg.mtrEstTpCd);
                // START 2022/03/22 [QC#59683, MOD]
//                ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgFlg, NSAL0300CommonLogic.switchFlg(cMsg.xxSelFlg_UT));
                ZYPEZDItemValueSetter.setValue(tMsg.invSeptBaseUsgFlg, NSAL0300CommonLogic.getInvSeptBaseUsgFlg(glblCmpyCd, cMsg.dsInvTgtrTpCd.getValue()));
                // END   2022/03/22 [QC#59683, MOD]
                // START 2022/03/22 [QC#59683, ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.dsInvTgtrTpCd, cMsg.dsInvTgtrTpCd);
                // END   2022/03/22 [QC#59683, ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.prcAllocByMachQtyFlg, NSAL0300CommonLogic.flg(cMsg.prcAllocByMachQtyFlg));
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrClsCd, cMsg.dsContrClsCd);
                ZYPEZDItemValueSetter.setValue(tMsg.svcContrRefCmntTxt, cMsg.svcContrRefCmntTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.contrAdminPsnCd, cMsg.contrAdminPsnCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, cMsg.ctacPsnPk_CP);
                ZYPEZDItemValueSetter.setValue(tMsg.contrInvCmntTxt, cMsg.contrInvCmntTxt);
                ZYPEZDItemValueSetter.setValue(tMsg.cratDraftPsnCd, userId);
                ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, cMsg.svcLineBizCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrSrcTpCd, DS_CONTR_SRC_TP.CONTRACT_MAINTENACE);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrEdiCd, cMsg.dsContrEdiCd);
                ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.svcPgmMdseCd, cMsg.svcPgmMdseCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsContrRptGrpNum, cMsg.dsContrRptGrpNum);
                ZYPEZDItemValueSetter.setValue(tMsg.altPayerCustCd, cMsg.altPayerCustCd);
                ZYPEZDItemValueSetter.setValue(tMsg.baseBllgCycleCd, cMsg.baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mtrBllgCycleCd, cMsg.mtrBllgCycleCd);
                // add start 2016/07/21 CSA Defect#11720
                ZYPEZDItemValueSetter.setValue(tMsg.contrDurnAot, cMsg.contrDurnAot);
                ZYPEZDItemValueSetter.setValue(tMsg.bllgCycleUomCd, cMsg.bllgCycleUomCd);
                // add end 2016/07/21 CSA Defect#11720

                // TODO
                ZYPEZDItemValueSetter.setValue(tMsg.bllgApvlReqFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.preInvReqFlg, ZYPConstant.FLG_OFF_N);

                // START 2016/01/18 [QC#1088, ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                // END 2016/01/18 [QC#1088, ADD]
                // START 2017/04/26 [RS#7224, ADD]
                ZYPEZDItemValueSetter.setValue(tMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(tMsg.applyEquipBillToFlg, ZYPConstant.FLG_OFF_N);
                // END   2017/04/26 [RS#7224, ADD]
                // add start 2017/06/19 CSA Defect#19036
                ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
                // add end 2017/06/19 CSA Defect#19036
                S21FastTBLAccessor.insert(tMsg);
                String rtnCd = tMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract" });
                    return;
                }
                // add start 2018/11/07 K.Fujimoto QC#28627
                if(ZYPCommonFunc.hasValue(cMsg.contrLinkNum) && !cMsg.contrLinkNum.getValue().equals(tMsg.dsContrNum.getValue())){
                    if(!updateParentDsContract()){
                    	return;
                    };
                }
                // add end   2018/11/07 K.Fujimoto QC#28627
                if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, tMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
                    return;
                }

                ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, tMsg.dsContrPk);
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, tMsg.dsContrNum);
            }
        }
        // END 2016/03/08 T.Kanasaka [QC2208, MOD]

        // add start 2018/11/07 K.Fujimoto QC#28627
        private boolean updateParentDsContract(){
            NSAL0300Query query = NSAL0300Query.getInstance();
            DS_CONTRTMsg parentDsContrTMsg = query.getDsContr(glblCmpyCd, cMsg.contrLinkNum.getValue());
            if(parentDsContrTMsg != null && !ZYPCommonFunc.hasValue(parentDsContrTMsg.contrLinkNum)){
                ZYPEZDItemValueSetter.setValue(parentDsContrTMsg.contrLinkNum,parentDsContrTMsg.dsContrNum);
                S21FastTBLAccessor.update(parentDsContrTMsg);
                String rtnCd = parentDsContrTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract" });
                    return false;
                }
            }
            return true;
        }
        //add end   2018/11/07 K.Fujimoto QC#28627

        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        protected void mergeDsContrCrCardPo() {
//            NSAL0300Query query = NSAL0300Query.getInstance();
//            // add start 2016/09/06 CSA Defect#11243
//            if (!ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
//                cMsg.xxMthDt_CC.clear();
//                cMsg.xxYrDt_CC.clear();
//            }
//            // add end 2016/09/06 CSA Defect#11243
//            boolean hasValue = (ZYPCommonFunc.hasValue(cMsg.custPoNum) || ZYPCommonFunc.hasValue(cMsg.poDt) || ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum) || ZYPCommonFunc.hasValue(cMsg.xxMthDt_CC) || ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC));
//            if (ZYPCommonFunc.hasValue(cMsg.dsContrCrCardPoPk)) {
//                DS_CONTR_CR_CARD_POTMsg tMsg = query.getDsContrCrCardPo(glblCmpyCd, cMsg.dsContrCrCardPoPk.getValue());
//                if (tMsg == null) {
//                    cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Credit Card PO" });
//                    return;
//                } else {
//                    if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_P.getValue(), cMsg.ezUpTimeZone_P.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
//                        cMsg.setMessageInfo(ZZZM9004E);
//                        return;
//                    }
//                    if (hasValue) {
//                        NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
//                        diffSetter.setValue(tMsg.custPoNum, cMsg.custPoNum);
//                        diffSetter.setValue(tMsg.poDt, cMsg.poDt);
//                        diffSetter.setValue(tMsg.crCardCustRefNum, cMsg.crCardCustRefNum);
//                        if (ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC)) {
//                            diffSetter.setValue(tMsg.crCardExprYrMth, cMsg.xxYrDt_CC.getValue() + cMsg.xxMthDt_CC.getValue());
//                        } else {
//                            tMsg.crCardExprYrMth.clear();
//                        }
//                        if (diffSetter.set()) {
//                            S21FastTBLAccessor.update(tMsg);
//                            String rtnCd = tMsg.getReturnCode();
//                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Credit Card PO" });
//                                return;
//                            }
//                        }
//                    } else {
//                        S21FastTBLAccessor.removeLogical(new DS_CONTR_CR_CARD_POTMsg[] {tMsg });
//                        String rtnCd = tMsg.getReturnCode();
//                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                            cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Credit Card PO" });
//                            return;
//                        }
//                    }
//                }
//            } else {
//                if (hasValue) {
//                    DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
//                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_CR_CARD_PO_SQ");
//                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
//                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, getDsContrPk());
//                    tMsg.dsContrDtlPk.clear();
//                    tMsg.dsContrBllgMtrPk.clear();
//                    ZYPEZDItemValueSetter.setValue(tMsg.custPoNum, cMsg.custPoNum);
//                    ZYPEZDItemValueSetter.setValue(tMsg.poDt, cMsg.poDt);
//                    ZYPEZDItemValueSetter.setValue(tMsg.crCardCustRefNum, cMsg.crCardCustRefNum);
//                    if (ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC)) {
//                        ZYPEZDItemValueSetter.setValue(tMsg.crCardExprYrMth, cMsg.xxYrDt_CC.getValue() + cMsg.xxMthDt_CC.getValue());
//                    }
//                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrMachLvlNum, "1");
//                    // TODO
//                    ZYPEZDItemValueSetter.setValue(tMsg.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
//
//                    S21FastTBLAccessor.insert(tMsg);
//                    String rtnCd = tMsg.getReturnCode();
//                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                        cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Credit Card PO" });
//                        return;
//                    }
//                }
//            }
//            // add start 2016/09/06 CSA Defect#11243
//            if (ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
//                NSAL0300CommonLogic.callCreditCardValidationApiForSave(glblCmpyCd, cMsg);
//            } else {
//                NSAL0300CommonLogic.callCreditCardValidationApiForVoid(glblCmpyCd, cMsg);
//            }
//            // add end 2016/09/06 CSA Defect#11243
//        }

        protected void mergeDsContrCrCardPoForPo() {
            NSAL0300Query query = NSAL0300Query.getInstance();
            boolean hasValue = (ZYPCommonFunc.hasValue(cMsg.custPoNum) || ZYPCommonFunc.hasValue(cMsg.poDt) || ZYPCommonFunc.hasValue(cMsg.poFromDt));
            boolean isUpdate = false;
            DS_CONTR_CR_CARD_POTMsg tMsg = null;
            if (ZYPCommonFunc.hasValue(cMsg.dsContrCrCardPoPk_PO)) {
                tMsg = query.getDsContrCrCardPo(glblCmpyCd, cMsg.dsContrCrCardPoPk_PO.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Credit Card PO" });
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_PO.getValue(), cMsg.ezUpTimeZone_PO.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (!hasValue) {
                    S21FastTBLAccessor.removeLogical(new DS_CONTR_CR_CARD_POTMsg[] {tMsg });
                    String rtnCd = tMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Credit Card PO" });
                        return;
                    }
                }
                if (NSAL0300CommonLogic.isEqualStr(cMsg.custPoNum.getValue(), tMsg.custPoNum.getValue())) {
                    isUpdate = true;
                }
                if (NSAL0300CommonLogic.isEqualStr(cMsg.poFromDt.getValue(), tMsg.poFromDt.getValue()) && NSAL0300CommonLogic.isEqualStr(cMsg.poDt.getValue(), tMsg.poDt.getValue())) {
                    isUpdate = true;
                }
            }
            if (hasValue) {
                if (isUpdate) {
                    NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
                    diffSetter.setValue(tMsg.custPoNum, cMsg.custPoNum);
                    diffSetter.setValue(tMsg.poFromDt, cMsg.poFromDt);
                    diffSetter.setValue(tMsg.poDt, cMsg.poDt);
                    if (diffSetter.set()) {
                        S21FastTBLAccessor.update(tMsg);
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Credit Card PO" });
                            return;
                        }
                    }
                } else {
                    DS_CONTR_CR_CARD_POTMsg insTMsg = new DS_CONTR_CR_CARD_POTMsg();
                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsContrPk, getDsContrPk());
                    ZYPEZDItemValueSetter.setValue(insTMsg.custPoNum, cMsg.custPoNum);
                    ZYPEZDItemValueSetter.setValue(insTMsg.poDt, cMsg.poDt);
                    ZYPEZDItemValueSetter.setValue(insTMsg.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsContrMachLvlNum, MACH_LVL_NUM_1);
                    ZYPEZDItemValueSetter.setValue(insTMsg.poFromDt, cMsg.poFromDt);
                    ZYPEZDItemValueSetter.setValue(insTMsg.crCardFlg, ZYPConstant.FLG_OFF_N);

                    S21FastTBLAccessor.insert(insTMsg);
                    String rtnCd = insTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Credit Card PO" });
                        return;
                    }
                }
            }
        }

        protected void mergeDsContrCrCardPoForCrCard() {
            NSAL0300Query query = NSAL0300Query.getInstance();
            if (!ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
                cMsg.xxMthDt_CC.clear();
                cMsg.xxYrDt_CC.clear();
            }
            boolean hasValue = (ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum) || ZYPCommonFunc.hasValue(cMsg.xxMthDt_CC) || ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC));
            if (ZYPCommonFunc.hasValue(cMsg.dsContrCrCardPoPk_CC)) {
                DS_CONTR_CR_CARD_POTMsg tMsg = query.getDsContrCrCardPo(glblCmpyCd, cMsg.dsContrCrCardPoPk_CC.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Credit Card PO" });
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_CC.getValue(), cMsg.ezUpTimeZone_CC.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(ZZZM9004E);
                    return;
                }
                if (hasValue) {
                    NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
                    diffSetter.setValue(tMsg.crCardCustRefNum, cMsg.crCardCustRefNum);
                    if (ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC)) {
                        diffSetter.setValue(tMsg.crCardExprYrMth, cMsg.xxYrDt_CC.getValue() + cMsg.xxMthDt_CC.getValue());
                    } else {
                        tMsg.crCardExprYrMth.clear();
                    }
                    if (diffSetter.set()) {
                        S21FastTBLAccessor.update(tMsg);
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Credit Card PO" });
                            return;
                        }
                    }
                } else {
                    S21FastTBLAccessor.removeLogical(new DS_CONTR_CR_CARD_POTMsg[] {tMsg });
                    String rtnCd = tMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Credit Card PO" });
                        return;
                    }
                }
            } else {
                if (hasValue) {
                    DS_CONTR_CR_CARD_POTMsg insTMsg = new DS_CONTR_CR_CARD_POTMsg();
                    BigDecimal dsContrCrCardPoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ);
                    ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsContrCrCardPoPk, dsContrCrCardPoPk);
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsContrPk, getDsContrPk());
                    ZYPEZDItemValueSetter.setValue(insTMsg.crCardCustRefNum, cMsg.crCardCustRefNum);
                    if (ZYPCommonFunc.hasValue(cMsg.xxYrDt_CC)) {
                        ZYPEZDItemValueSetter.setValue(insTMsg.crCardExprYrMth, cMsg.xxYrDt_CC.getValue() + cMsg.xxMthDt_CC.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(insTMsg.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(insTMsg.dsContrMachLvlNum, MACH_LVL_NUM_1);
                    ZYPEZDItemValueSetter.setValue(insTMsg.crCardFlg, ZYPConstant.FLG_ON_Y);

                    S21FastTBLAccessor.insert(insTMsg);
                    String rtnCd = insTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Credit Card PO" });
                        return;
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(cMsg.crCardCustRefNum)) {
                NSAL0300CommonLogic.callCreditCardValidationApiForSave(glblCmpyCd, cMsg);
            } else {
                NSAL0300CommonLogic.callCreditCardValidationApiForVoid(glblCmpyCd, cMsg);
            }
        }
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]

        protected void mergeDsContrRnwEscl() {
            NSAL0300Query query = NSAL0300Query.getInstance();
            boolean hasValue = ZYPCommonFunc.hasValue(cMsg.contrAutoRnwTpCd) || ZYPCommonFunc.hasValue(cMsg.befEndRnwDaysAot) || ZYPCommonFunc.hasValue(cMsg.rnwPrcMethCd) || ZYPCommonFunc.hasValue(cMsg.basePrcUpRatio)
                    || ZYPCommonFunc.hasValue(cMsg.mtrPrcUpRatio) || ZYPCommonFunc.hasValue(cMsg.contrUplftTpCd) || ZYPCommonFunc.hasValue(cMsg.uplftPrcMethCd) || ZYPCommonFunc.hasValue(cMsg.uplftBasePrcUpRatio)
                    || ZYPCommonFunc.hasValue(cMsg.uplftMtrPrcUpRatio);
            if (ZYPCommonFunc.hasValue(cMsg.dsContrRnwEsclPk)) {
                DS_CONTR_RNW_ESCLTMsg tMsg = query.getDsContrRnwEscl(glblCmpyCd, cMsg.dsContrRnwEsclPk.getValue());
                if (tMsg == null) {
                    cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Renewal Escalation" });
                    return;
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_S.getValue(), cMsg.ezUpTimeZone_S.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    }
                    if (hasValue) {
                        NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
                        diffSetter.setValue(tMsg.contrAutoRnwTpCd, cMsg.contrAutoRnwTpCd);
                        diffSetter.setValue(tMsg.befEndRnwDaysAot, cMsg.befEndRnwDaysAot);
                        diffSetter.setValue(tMsg.rnwPrcMethCd, cMsg.rnwPrcMethCd);
                        diffSetter.setValue(tMsg.basePrcUpRatio, cMsg.basePrcUpRatio);
                        diffSetter.setValue(tMsg.mtrPrcUpRatio, cMsg.mtrPrcUpRatio);
                        diffSetter.setValue(tMsg.contrUplftTpCd, cMsg.contrUplftTpCd);
                        diffSetter.setValue(tMsg.uplftPrcMethCd, cMsg.uplftPrcMethCd);
                        diffSetter.setValue(tMsg.uplftBasePrcUpRatio, cMsg.uplftBasePrcUpRatio);
                        diffSetter.setValue(tMsg.uplftMtrPrcUpRatio, cMsg.uplftMtrPrcUpRatio);
                        NSAL0300CommonLogic.setUplftFlg(glblCmpyCd, tMsg);

                        if (diffSetter.set()) {
                            // START 2018/08/16 K.Kojima [QC#23067,ADD]
                            if (CONTR_UPLFT_TP.DO_NOT_UPLIFT.equals(tMsg.contrUplftTpCd.getValue())) {
                                tMsg.uplftBefEndRnwDaysAot.clear();
                            }
                            // END 2018/08/16 K.Kojima [QC#23067,ADD]
                            S21FastTBLAccessor.update(tMsg);
                            String rtnCd = tMsg.getReturnCode();
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Renewal Escalation" });
                                return;
                            }
                        }
                    } else {
                        S21FastTBLAccessor.removeLogical(new DS_CONTR_RNW_ESCLTMsg[] {tMsg });
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Renewal Escalation" });
                            return;
                        }
                    }
                }
            } else {
                if (hasValue) {
                    DS_CONTR_RNW_ESCLTMsg tMsg = new DS_CONTR_RNW_ESCLTMsg();
                    BigDecimal dsContrRnwEsclPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_RNW_ESCL_SQ");
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, getDsContrPk());
                    tMsg.dsContrDtlPk.clear();
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrMachLvlNum, "1");

                    ZYPEZDItemValueSetter.setValue(tMsg.contrAutoRnwTpCd, cMsg.contrAutoRnwTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.befEndRnwDaysAot, cMsg.befEndRnwDaysAot);
                    ZYPEZDItemValueSetter.setValue(tMsg.rnwPrcMethCd, cMsg.rnwPrcMethCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.basePrcUpRatio, cMsg.basePrcUpRatio);
                    ZYPEZDItemValueSetter.setValue(tMsg.mtrPrcUpRatio, cMsg.mtrPrcUpRatio);
                    ZYPEZDItemValueSetter.setValue(tMsg.contrUplftTpCd, cMsg.contrUplftTpCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.uplftPrcMethCd, cMsg.uplftPrcMethCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.uplftBasePrcUpRatio, cMsg.uplftBasePrcUpRatio);
                    ZYPEZDItemValueSetter.setValue(tMsg.uplftMtrPrcUpRatio, cMsg.uplftMtrPrcUpRatio);
                    NSAL0300CommonLogic.setUplftFlg(glblCmpyCd, tMsg);

                    S21FastTBLAccessor.insert(tMsg);
                    String rtnCd = tMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Renewal Escalation" });
                        return;
                    }
                }
            }
        }

        protected String getDsContrDtlStsCd(NSAL0300CMsg cMsg) {
            return DS_CONTR_DTL_STS.SAVED;
        }

        protected BigDecimal getDsContrDtlPk(String dsContrDtlTpCd, String serNum, String mdseCd) {
            StringBuilder buf = new StringBuilder();
            buf.append(dsContrDtlTpCd);
            if (ZYPCommonFunc.hasValue(serNum)) {
                buf.append("\t");
                buf.append(serNum);
            }
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                buf.append("\t");
                buf.append(mdseCd);
            }
            String key = buf.toString();
            BigDecimal dsContrDtlPk = null;
            if (dsContrDtlPkMap.containsKey(key)) {
                dsContrDtlPk = dsContrDtlPkMap.get(key);
            } else {
                dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_DTL_SQ");
                dsContrDtlPkMap.put(key, dsContrDtlPk);
            }
            return dsContrDtlPk;
        }

        protected void mapDsContrDtlPk(String dsContrDtlTpCd, String serNum, String mdseCd, BigDecimal dsContrDtlPk) {
            StringBuilder buf = new StringBuilder();
            buf.append(dsContrDtlTpCd);
            if (ZYPCommonFunc.hasValue(serNum)) {
                buf.append("\t");
                buf.append(serNum);
            }
            if (ZYPCommonFunc.hasValue(mdseCd)) {
                buf.append("\t");
                buf.append(mdseCd);
            }
            String key = buf.toString();
            dsContrDtlPkMap.put(key, dsContrDtlPk);
        }

        protected BigDecimal getDsContrDtlPk(String dsContrDtlTpCd) {
            for (Map.Entry<String, BigDecimal> dsContrDtlPkEntry : dsContrDtlPkMap.entrySet()) {
                String dsContrDtlPkKey = dsContrDtlPkEntry.getKey();
                if (dsContrDtlPkKey.startsWith(dsContrDtlTpCd)) {
                    return dsContrDtlPkEntry.getValue();
                }
            }
            return null;
        }

        protected BigDecimal getPrntDsContrDtlPk(int bIdx) {
            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())) {
                return cMsg.B.no(bIdx).prntDsContrDtlPk_B.getValue();
            } else {
                return null;
            }
        }

        // START 2016/03/08 T.Kanasaka [QC2208, MOD]
        protected void mergeDsContrDtl() {

            NSAL0300Query query = NSAL0300Query.getInstance();
            NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
            // START 2017/07/27 [QC#16889, MOD]
            // String preSerNum = INVLD_SER_NUM;
            // String preMdseCd = INVLD_MDSE_CD;
            BigDecimal preDsContrDtlPk = BigDecimal.ONE.negate();
            // END   2017/07/27 [QC#16889, MOD]
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                String serNum = cMsg.B.no(i).serNum_B.getValue();
                String mdseCd = cMsg.B.no(i).mdseCd_B.getValue();
                // START 2017/07/27 [QC#16889, MOD]
                BigDecimal dsContrDtlPk = cMsg.B.no(i).dsContrDtlPk_B.getValue();
                // if (!NSAL0300CommonLogic.isEqualMach(preSerNum, preMdseCd, serNum, mdseCd)) {
                if (!NSAL0300CommonLogic.isEqualNum(preDsContrDtlPk, dsContrDtlPk)) {
                // END 2017/07/27 [QC#16889, MOD]
                    boolean existsDsContrDtl = false;
                    DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
                    if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrDtlPk_B)) {
                        tMsg = query.getDsContrDtl(glblCmpyCd, cMsg.B.no(i).dsContrDtlPk_B.getValue());
                        if (tMsg != null) {
                            existsDsContrDtl = true;
                        }
                    }

                    if (existsDsContrDtl) {
                        mapDsContrDtlPk(cMsg.B.no(i).dsContrDtlTpCd_B.getValue(), serNum, mdseCd, cMsg.B.no(i).dsContrDtlPk_B.getValue());

                        // update
                        if (!ZYPDateUtil.isSameTimeStamp(cMsg.B.no(i).ezUpTime_BD.getValue(), cMsg.B.no(i).ezUpTimeZone_BD.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            cMsg.setMessageInfo(ZZZM9004E);
                            return;
                        }

                        diffSetter.clear();

                        // START 2018/08/29 M.Naito [QC#27102, ADD]
                        if (!tMsg.contrEffFromDt.getValue().equals(cMsg.B.no(i).contrEffFromDt_B.getValue())) {
                            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, cMsg.B.no(i).contrEffFromDt_B.getValue(), CONTR_INTFC_SRC_TP.ORDER, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
                            if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                                diffSetter.setValue(tMsg.uplftFromDt, uplftFromDt);
                            }
                        }
                        // END 2018/08/29 M.Naito [QC#27102, ADD]
                        checkSchdItemUpd(diffSetter.setValue(tMsg.contrEffFromDt, cMsg.B.no(i).contrEffFromDt_B), true, true, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        checkSchdItemUpd(diffSetter.setValue(tMsg.contrEffThruDt, cMsg.B.no(i).contrEffThruDt_B), true, true, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);

                        // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
                        diffSetter.setValue(tMsg.shipToCustCd, cMsg.B.no(i).shipToCustCd_B);
                        // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

                        // mod start 2016/09/23 CSA Defect#13686
                        //if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ctacPsnPk_BB)) {
                        diffSetter.setValue(tMsg.ctacPsnPk, cMsg.B.no(i).ctacPsnPk_BB);
//                        } else if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ctacPsnPk_BU)) {
//                            diffSetter.setValue(tMsg.ctacPsnPk, cMsg.B.no(i).ctacPsnPk_BU);
                        //}

                        // base
                        checkSchdItemUpd(diffSetter.setValue(tMsg.baseBillToCustCd, cMsg.B.no(i).baseBillToCustCd_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        diffSetter.setValue(tMsg.baseDplyPerEndDay, cMsg.B.no(i).baseDplyPerEndDay_B);
                        // END 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        checkSchdItemUpd(diffSetter.setValue(tMsg.contrCloDay, cMsg.B.no(i).contrCloDay_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        checkSchdItemUpd(diffSetter.setValue(tMsg.contrBllgDay, cMsg.B.no(i).contrBllgDay_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        // START 2019/05/13 K.Kitachi [QC#50211, DEL]
//                        checkSchdItemUpd(diffSetter.setValue(tMsg.baseBllgCycleCd, cMsg.B.no(i).baseBllgCycleCd_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        // END 2019/05/13 K.Kitachi [QC#50211, DEL]
                        // START 2018/06/05 K.Kim [QC#24851, ADD]
                        BigDecimal countDsContrPrcEff = query.countDsContrPrcEff(glblCmpyCd, dsContrDtlPk);
                        if (countDsContrPrcEff.compareTo(BigDecimal.ONE) != 1){
                            // START 2019/05/13 K.Kitachi [QC#50211, ADD]
                            checkSchdItemUpd(diffSetter.setValue(tMsg.baseBllgCycleCd, cMsg.B.no(i).baseBllgCycleCd_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                            // END 2019/05/13 K.Kitachi [QC#50211, ADD]
                            checkSchdItemUpd(diffSetter.setValue(tMsg.basePrcDealAmt, cMsg.B.no(i).basePrcDealAmt_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.basePrcTermDealAmtRate, cMsg.B.no(i).basePrcTermDealAmtRate_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        }
                        // END 2018/06/05 K.Kim [QC#24851, ADD]
                        // Mod Start 2018/01/16 QC#23542
//                        diffSetter.setValue(tMsg.baseBllgTmgCd, cMsg.B.no(i).baseBllgTmgCd_B);
                        checkSchdItemUpd(diffSetter.setValue(tMsg.baseBllgTmgCd, cMsg.B.no(i).baseBllgTmgCd_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        // Mod End 2018/01/16 QC#23542
                        // START 2018/06/05 K.Kim [QC#24851, DEL]
                        // checkSchdItemUpd(diffSetter.setValue(tMsg.basePrcTermDealAmtRate, cMsg.B.no(i).basePrcTermDealAmtRate_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        // END 2018/06/05 K.Kim [QC#24851, DEL]
                        checkSchdItemUpd(diffSetter.setValue(tMsg.svcPgmMdseCd, cMsg.B.no(i).svcPgmMdseCd_B), true, false, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);

                        // usage
//                        diffSetter.setValue(tMsg.usgBillToCustCd, cMsg.B.no(i).usgBillToCustCd_B);
                        // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        diffSetter.setValue(tMsg.mtrDplyPerEndDay, cMsg.B.no(i).mtrDplyPerEndDay_B);
                        // END 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        checkSchdItemUpd(diffSetter.setValue(tMsg.mtrCloDay, cMsg.B.no(i).mtrCloDay_B), false, true, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        checkSchdItemUpd(diffSetter.setValue(tMsg.mtrBllgDay, cMsg.B.no(i).mtrBllgDay_B), false, true, cMsg.B.no(i).dsContrDtlPk_B.getValue(), null);
                        diffSetter.setValue(tMsg.mtrBllgTmgCd, cMsg.B.no(i).mtrBllgTmgCd_B);
                        diffSetter.setValue(tMsg.mtrReadMethCd, cMsg.B.no(i).mtrReadMethCd_B);

                        // Start 2019/01/21 T.Wada [QC#29371] 
                        String svcInvMergeTpCd =  cMsg.B.no(i).xxTpCd_B.getValue();
                        if (ZYPCommonFunc.hasValue(svcInvMergeTpCd)) {
                            diffSetter.setValue(tMsg.svcInvMergeTpCd, svcInvMergeTpCd);
                        } else {
                            diffSetter.setValue(tMsg.svcInvMergeTpCd, SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE);
                        }
                        // End 2019/01/21 T.Wada [QC#29371] 

                        if (diffSetter.set()) {
                            S21FastTBLAccessor.update(tMsg);
                            String rtnCd = tMsg.getReturnCode();
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Detail" });
                                return;
                            }
                            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), tMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                                return;
                            }
                        }
                    } else {
                        // new
                        tMsg = new DS_CONTR_DTLTMsg();

                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrDtlPk_B)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B);
                        } else {
                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, getDsContrDtlPk(cMsg.B.no(i).dsContrDtlTpCd_B.getValue(), serNum, mdseCd));

                        }
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, getDsContrPk());
                        ZYPEZDItemValueSetter.setValue(tMsg.contrEffFromDt, cMsg.B.no(i).contrEffFromDt_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.contrEffThruDt, cMsg.B.no(i).contrEffThruDt_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.prntDsContrDtlPk, getPrntDsContrDtlPk(i));

                        // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.shipToCustCd, cMsg.B.no(i).shipToCustCd_B);
                        // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

                        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ctacPsnPk_BB)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, cMsg.B.no(i).ctacPsnPk_BB);
//                        } else if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ctacPsnPk_BU)) {
//                            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, cMsg.B.no(i).ctacPsnPk_BU);
                        }

                        // base
                        ZYPEZDItemValueSetter.setValue(tMsg.baseBillToCustCd, cMsg.B.no(i).baseBillToCustCd_B);
                        // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.baseDplyPerEndDay, cMsg.B.no(i).baseDplyPerEndDay_B);
                        // END 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.contrCloDay, cMsg.B.no(i).contrCloDay_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.contrBllgDay, cMsg.B.no(i).contrBllgDay_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.baseBllgCycleCd, cMsg.B.no(i).baseBllgCycleCd_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.basePrcDealAmt, cMsg.B.no(i).basePrcDealAmt_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.baseBllgTmgCd, cMsg.B.no(i).baseBllgTmgCd_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.basePrcTermDealAmtRate, cMsg.B.no(i).basePrcTermDealAmtRate_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.svcPgmMdseCd, cMsg.B.no(i).svcPgmMdseCd_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.baseBllgCycleCd, cMsg.B.no(i).baseBllgCycleCd_B);

                        // usage
//                        ZYPEZDItemValueSetter.setValue(tMsg.usgBillToCustCd, cMsg.B.no(i).usgBillToCustCd_B);
                        // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrDplyPerEndDay, cMsg.B.no(i).mtrDplyPerEndDay_B);
                        // END 2016/05/17 T.Kanasaka [QC#2184, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrCloDay, cMsg.B.no(i).mtrCloDay_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrBllgDay, cMsg.B.no(i).mtrBllgDay_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrBllgCycleCd, cMsg.B.no(i).mtrBllgCycleCd_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrBllgTmgCd, cMsg.B.no(i).mtrBllgTmgCd_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadMethCd, cMsg.B.no(i).mtrReadMethCd_B);

                        // TODO
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpCd, cMsg.B.no(i).dsContrDtlTpCd_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlStsCd, getDsContrDtlStsCd(cMsg));

                        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, cMsg.B.no(i).svcConfigMstrPk_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, cMsg.B.no(i).svcMachMstrPk_B);
                        ZYPEZDItemValueSetter.setValue(tMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);

                        // START 2016/01/25 T.Iwamoto [QC#3533, DEL]
//                        ZYPEZDItemValueSetter.setValue(tMsg.maxContrRnwCnt, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(tMsg.maxPrcUpRatio, BigDecimal.ZERO);
                        // END   2016/01/25 T.Iwamoto [QC#3533, DEL]
                        ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                        // START 2016/01/18 [QC#1088, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                        // END 2016/01/18 [QC#1088, ADD]
                        // START 2016/04/06 [QC#4921, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.contrRnwTotCnt, BigDecimal.ZERO);
                        // END 2016/04/06 [QC#4921, ADD]
                        // START 2017/04/26 [RS#7224, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.addContrFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
                        // END   2017/04/26 [RS#7224, ADD]
                        // START 2018/08/29 M.Naito [QC#27102, ADD]
                        String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, tMsg.contrEffFromDt.getValue(), CONTR_INTFC_SRC_TP.ORDER, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
                        if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.uplftFromDt, uplftFromDt);
                        }
                        // END 2018/08/29 M.Naito [QC#27102, ADD]

                        // Start 2019/01/21 T.Wada [QC#29371] 
                        String svcInvMergeTpCd =  cMsg.B.no(i).xxTpCd_B.getValue();
                        if (ZYPCommonFunc.hasValue(svcInvMergeTpCd)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.svcInvMergeTpCd, svcInvMergeTpCd);
                        } else {
                            ZYPEZDItemValueSetter.setValue(tMsg.svcInvMergeTpCd, SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE);
                        }
                        // End 2019/01/21 T.Wada [QC#29371] 

                        S21FastTBLAccessor.insert(tMsg);
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Detail" });
                            return;
                        }
                        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), tMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                            return;
                        }
                        baseSchdItemUpdMapDtl.put(cMsg.B.no(i).dsContrDtlPk_B.getValue(), true);
                        usgSchdItemUpdMapDtl.put(cMsg.B.no(i).dsContrDtlPk_B.getValue(), true);
                    }
                }
                // START 2017/07/27 [QC#16889, MOD]
                // preSerNum = serNum;
                // preMdseCd = mdseCd;
                preDsContrDtlPk = dsContrDtlPk;
                // END 2017/07/27 [QC#16889, MOD]
            }

            // START 2016/06/20 T.Kanasaka [QC#9019, DEL]
//            // delete
//            List<DS_CONTR_DTLTMsg> delTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
//            for (int i = 0; i < sMsg.X.getValidCount(); i++) {
//                DS_CONTR_DTLTMsg TMsg = query.getDsContrDtl(glblCmpyCd, sMsg.X.no(i).dsContrDtlPk_X.getValue());
//                if (TMsg != null) {
//                    delTMsgList.add(TMsg);
//                }
//            }
//            if (delTMsgList.size() > 0) {
//                int delCnt = S21FastTBLAccessor.removeLogical(delTMsgList.toArray(new DS_CONTR_DTLTMsg[delTMsgList.size()]));
//                if (delCnt != delTMsgList.size()) {
//                    cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Detail" });
//                    return;
//                }
//            }
            // END 2016/06/20 T.Kanasaka [QC#9019, DEL]
        }
        // END 2016/03/08 T.Kanasaka [QC2208, MOD]

//        protected void mergeContrCovSvc() {
//            NSAL0300Query query = NSAL0300Query.getInstance();
//            String preSerNum = INVLD_SER_NUM;
//            String preMdseCd = INVLD_MDSE_CD;
//            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                String serNum = cMsg.B.no(i).serNum_B.getValue();
//                String mdseCd = cMsg.B.no(i).mdseCd_B.getValue();
//                if (!NSAL0300CommonLogic.isEqualMach(preSerNum, preMdseCd, serNum, mdseCd)) {
//                    if (ZYPCommonFunc.hasValue(cMsg.B.no(i).contrCovSvcPk_B)) {
//                        // update
//                        CONTR_COV_SVCTMsg tMsg = query.getContrCovSvc(glblCmpyCd, cMsg.B.no(i).contrCovSvcPk_B.getValue());
//                        if (tMsg == null) {
//                            cMsg.setMessageInfo(NSAM0045E, new String[] {"Contract Covered Service" });
//                            return;
//                        }
//                        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsCovSvcTpCd_B)) {
//                            NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
//                            diffSetter.setValue(tMsg.dsCovSvcTpCd, cMsg.B.no(i).dsCovSvcTpCd_B);
//                            if (diffSetter.set()) {
//                                S21FastTBLAccessor.update(tMsg);
//                                String rtnCd = tMsg.getReturnCode();
//                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                                    cMsg.setMessageInfo(NSAM0031E, new String[] {"Contract Covered Service" });
//                                    return;
//                                }
//                            }
//                        } else {
//                            S21FastTBLAccessor.removeLogical(new CONTR_COV_SVCTMsg[] {tMsg });
//                            String rtnCd = tMsg.getReturnCode();
//                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                                cMsg.setMessageInfo(NSAM0033E, new String[] {"Contract Covered Service" });
//                                return;
//                            }
//                        }
//                    } else {
//                        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsCovSvcTpCd_B)) {
//                            // insert
//                            CONTR_COV_SVCTMsg tMsg = new CONTR_COV_SVCTMsg();
//                            BigDecimal contrCovSvcPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CONTR_COV_SVC_SQ");
//                            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//                            ZYPEZDItemValueSetter.setValue(tMsg.contrCovSvcPk, contrCovSvcPk);
//                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, getDsContrDtlPk(cMsg.B.no(i).dsContrDtlTpCd_B.getValue(), serNum, mdseCd));
//                            ZYPEZDItemValueSetter.setValue(tMsg.dsCovSvcTpCd, cMsg.B.no(i).dsCovSvcTpCd_B);
//                            // TODO
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovSunFlg, ZYPConstant.FLG_OFF_N);
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovMonFlg, ZYPConstant.FLG_OFF_N);
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovTueFlg, ZYPConstant.FLG_OFF_N);
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovWedFlg, ZYPConstant.FLG_OFF_N);
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovThuFlg, ZYPConstant.FLG_OFF_N);
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovFriFlg, ZYPConstant.FLG_OFF_N);
//                            ZYPEZDItemValueSetter.setValue(tMsg.svcCovSatFlg, ZYPConstant.FLG_OFF_N);
//
//                            S21FastTBLAccessor.insert(tMsg);
//                            String rtnCd = tMsg.getReturnCode();
//                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                                cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Covered Service" });
//                                return;
//                            }
//                        }
//                    }
//                }
//                preSerNum = serNum;
//                preMdseCd = mdseCd;
//            }
//        }

        // START 2016/03/08 T.Kanasaka [QC2208, MOD]
        protected void mergeDsContrBllgMtr() {
            NSAL0300Query query = NSAL0300Query.getInstance();
            NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
            BigDecimal preDsContrBllgMtrPk = INVLD_DS_CONTR_BLLG_MTR_PK;

            boolean updateExistFlg = false;
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                    updateExistFlg = true;
                    break;
                }
            }
            if (updateExistFlg) {
                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                    BigDecimal dsContrBllgMtrPk = cMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
                    if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                        if (!NSAL0300CommonLogic.isEqualNum(preDsContrBllgMtrPk, dsContrBllgMtrPk)) {
                            DS_CONTR_BLLG_MTRTMsg tMsg = query.getDsContrBllgMtr(glblCmpyCd, dsContrBllgMtrPk);
                            // START 2017/02/07 [QC#17275, MOD]
                            boolean updateFlg = true;
                            // END   2017/02/07 [QC#17275, MOD]
                            if (tMsg == null) {
                                // START 2017/02/07 [QC#17275, MOD]
                                // cMsg.setMessageInfo(NSAM0045E, new String[] {"Ds Contract Billing Meter" });
                                // return;
                                tMsg = new DS_CONTR_BLLG_MTRTMsg();
                                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
                                ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B);
                                ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrStsCd, getDsContrDtlStsCd(cMsg));
                                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, cMsg.B.no(i).bllgMtrLbCd_B);
                                ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                                ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                                ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                                ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                                ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).bllgMtrLbCd_B)) {
                                    MTR_LBTMsg mtrLbTMsg = query.getMtrLbTMsg(glblCmpyCd, cMsg.B.no(i).bllgMtrLbCd_B.getValue());
                                    if (mtrLbTMsg != null) {
                                        ZYPEZDItemValueSetter.setValue(tMsg.intgMdseCd, mtrLbTMsg.intgMdseCd);
                                    }
                                }
                                updateFlg = false;
                                // END   2017/02/07 [QC#17275, MOD]
                            }
                            // START 2017/02/07 [QC#17275, MOD]
                            // if (!ZYPDateUtil.isSameTimeStamp(cMsg.B.no(i).ezUpTime_BM.getValue(), cMsg.B.no(i).ezUpTimeZone_BM.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            if (updateFlg && !ZYPDateUtil.isSameTimeStamp(cMsg.B.no(i).ezUpTime_BM.getValue(), cMsg.B.no(i).ezUpTimeZone_BM.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            // END   2017/02/07 [QC#17275, MOD]
                                cMsg.setMessageInfo(ZZZM9004E);
                                return;
                            }

                            diffSetter.clear();
                            String dsContrDtlTpCd = cMsg.B.no(i).dsContrDtlTpCd_B.getValue();
                            checkSchdItemUpd(diffSetter.setValue(tMsg.bllgMtrBillToCustCd, cMsg.B.no(i).bllgMtrBillToCustCd_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.bllgMtrBllgCycleCd, cMsg.B.no(i).bllgMtrBllgCycleCd_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.xsChrgTpCd, cMsg.B.no(i).xsChrgTpCd_B), false, true, null, dsContrBllgMtrPk);
                            diffSetter.setValue(tMsg.ctacPsnPk, cMsg.B.no(i).ctacPsnPk_BM);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.bllgMinCnt, cMsg.B.no(i).bllgMinCnt_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.bllgMinAmtRate, cMsg.B.no(i).bllgMinAmtRate_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.bllgRollOverRatio, cMsg.B.no(i).bllgRollOverRatio_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.bllgFreeCopyCnt, cMsg.B.no(i).bllgFreeCopyCnt_B), false, true, null, dsContrBllgMtrPk);
                            // START 2019/01/17 M.Naito [QC#29083,ADD]
                            checkSchdItemUpd(diffSetter.setValue(tMsg.cumCopyCnt, cMsg.B.no(i).cumCopyCnt_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.cumCopyFreqMthAot, cMsg.B.no(i).cumCopyFreqMthAot_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.cumCopyStartDt, cMsg.B.no(i).cumCopyStartDt_B), false, true, null, dsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(tMsg.cumCopyEndDt, cMsg.B.no(i).cumCopyEndDt_B), false, true, null, dsContrBllgMtrPk);
                            // Mod Start 2019/01/28 QC#29083
//                            if (!NSAL0300CommonLogic.isExistInvoicedBllg(glblCmpyCd, dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(tMsg.cumCopyCnt)) {
//                                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).contrEffFromDt_B) && cMsg.B.no(i).contrEffFromDt_B.getValue().equals(tMsg.cumCopyStartDt.getValue())) {
//                                    ZYPEZDItemValueSetter.setValue(tMsg.bllgFreeCopyCnt, tMsg.cumCopyCnt);
//                                }
//                            }
                            if (NSAL0300CommonLogic.isUpdateFreeCopy(this.glblCmpyCd, dsContrBllgMtrPk, tMsg.cumCopyStartDt.getValue())) {
                                ZYPEZDItemValueSetter.setValue(tMsg.bllgFreeCopyCnt, tMsg.cumCopyCnt);
                            }
                            // Mod End 2019/01/28 QC#29083
                            // END 2019/01/17 M.Naito [QC#29083,ADD]

                            if (diffSetter.set()) {
                                // START 2017/02/07 [QC#17275, MOD]
                                // S21FastTBLAccessor.update(tMsg);
                                if (updateFlg) {
                                    S21FastTBLAccessor.update(tMsg);
                                } else {
                                    S21FastTBLAccessor.insert(tMsg);
                                }
                                // END   2017/02/07 [QC#17275, MOD]
                                String rtnCd = tMsg.getReturnCode();
                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                    // START 2017/02/07 [QC#17275, MOD]
                                    // cMsg.setMessageInfo(NSAM0031E, new String[] {"Ds Contract Billing Meter" });
                                    if (updateFlg) {
                                        cMsg.setMessageInfo(NSAM0031E, new String[] {"Ds Contract Billing Meter" });
                                    } else {
                                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Ds Contract Billing Meter" });
                                    }
                                    // END   2017/02/07 [QC#17275, MOD]
                                    return;
                                }
                                if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), tMsg.dsContrDtlPk.getValue(), tMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                                    return;
                                }
                            }
                        }
//                    } else if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
//                        String dsContrDtlTpCd = cMsg.B.no(i).dsContrDtlTpCd_B.getValue();
//                        if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
//                            DS_CONTR_BLLG_MTRTMsg tMsg = new DS_CONTR_BLLG_MTRTMsg();
//                            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_BLLG_MTR_SQ"));
//                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B);
//                            ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, cMsg.B.no(i).bllgMtrLbCd_B);
//                            ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrBillToCustCd, cMsg.B.no(i).bllgMtrBillToCustCd_B);
//                            ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrBllgCycleCd, cMsg.B.no(i).bllgMtrBllgCycleCd_B);
//                            ZYPEZDItemValueSetter.setValue(tMsg.xsChrgTpCd, cMsg.B.no(i).xsChrgTpCd_B);
//                            ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, cMsg.B.no(i).ctacPsnPk_BM);
//
//                            // from Aggregate Line
//                            NSAL0300_BCMsg parentBCMsg = NSAL0300CommonLogic.getParentBCMsg(cMsg.B, cMsg.B.no(i).prntDsContrDtlPk_B.getValue());
//                            if (parentBCMsg != null) {
//                                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrLbCd, parentBCMsg.bllgMtrLbCd_B);
//                                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrBillToCustCd, parentBCMsg.bllgMtrBillToCustCd_B);
//                                ZYPEZDItemValueSetter.setValue(tMsg.bllgMtrBllgCycleCd, parentBCMsg.bllgMtrBllgCycleCd_B);
//                                ZYPEZDItemValueSetter.setValue(tMsg.xsChrgTpCd, parentBCMsg.xsChrgTpCd_B);
//                                ZYPEZDItemValueSetter.setValue(tMsg.ctacPsnPk, parentBCMsg.ctacPsnPk_BM);
////                                ZYPEZDItemValueSetter.setValue(tMsg.intgMdseCd, parentBCMsg.);
//                            }
//
//                            // from Contract Detail
//                            DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlForRead(glblCmpyCd, cMsg.B.no(i).dsContrDtlPk_B.getValue());
//                            if (dsContrDtlTMsg != null) {
//                                ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrStsCd, dsContrDtlTMsg.dsContrDtlStsCd);
//                                ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, dsContrDtlTMsg.qltyAsrnHldFlg);
//                                ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, dsContrDtlTMsg.mtrHldFlg);
//                                ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, dsContrDtlTMsg.contrHldFlg);
//                                ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, dsContrDtlTMsg.bllgHldFlg);
//                            }
//
//                            S21FastTBLAccessor.insert(tMsg);
//                            String rtnCd = tMsg.getReturnCode();
//                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//                                cMsg.setMessageInfo(NSAM0032E, new String[] {"Ds Contract Billing Meter" });
//                                return;
//                            }
//                            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), tMsg.dsContrDtlPk.getValue(), tMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
//                                return;
//                            }
//                        }
                    }

                    preDsContrBllgMtrPk = dsContrBllgMtrPk;
                }
            }

            // START 2016/06/20 T.Kanasaka [QC#9019, DEL]
//            // delete
//            List<DS_CONTR_BLLG_MTRTMsg> delTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
//            for (int i = 0; i < sMsg.X.getValidCount(); i++) {
//                DS_CONTR_BLLG_MTRTMsgArray TMsgArray = query.getDsContrBllgMtrList(glblCmpyCd, sMsg.X.no(i).dsContrDtlPk_X.getValue());
//                if (TMsgArray != null) {
//                    for (int j = 0; j < TMsgArray.getValidCount(); j++) {
//                        delTMsgList.add(TMsgArray.no(j));
//                    }
//                }
//            }
//            if (delTMsgList.size() > 0) {
//                int delCnt = S21FastTBLAccessor.removeLogical(delTMsgList.toArray(new DS_CONTR_BLLG_MTRTMsg[delTMsgList.size()]));
//                if (delCnt != delTMsgList.size()) {
//                    cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Billing Meter" });
//                    return;
//                }
//            }
            // END 2016/06/20 T.Kanasaka [QC#9019, DEL]
        }
        // END 2016/03/08 T.Kanasaka [QC2208, MOD]

        protected void mergeContrXsCopy() {
            BigDecimal preDsContrBllgMtrPk = INVLD_DS_CONTR_BLLG_MTR_PK;
            List<CONTR_XS_COPYTMsg> tMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
            BigDecimal minQty = null;
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                BigDecimal dsContrBllgMtrPk = cMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
                if (!NSAL0300CommonLogic.isEqualNum(preDsContrBllgMtrPk, dsContrBllgMtrPk)) {
                    if (preDsContrBllgMtrPk != null && preDsContrBllgMtrPk.compareTo(INVLD_DS_CONTR_BLLG_MTR_PK) != 0) {
                        mergeContrXsCopy(cMsg, preDsContrBllgMtrPk, tMsgList, minQty);
                        if (NSAL0300CommonLogic.hasError(cMsg)) {
                            return;
                        }
                    }
                    tMsgList.clear();
                    minQty = null;
                }
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B) && ZYPCommonFunc.hasValue(cMsg.B.no(i).xsMtrCopyQty_B) && ZYPCommonFunc.hasValue(cMsg.B.no(i).xsMtrAmtRate_B)) {
                    CONTR_XS_COPYTMsg tMsg = new CONTR_XS_COPYTMsg();
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.contrXsCopyPk, cMsg.B.no(i).contrXsCopyPk_B);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrBllgMtrPk, cMsg.B.no(i).dsContrBllgMtrPk_B);
                    ZYPEZDItemValueSetter.setValue(tMsg.xsMtrCopyQty, cMsg.B.no(i).xsMtrCopyQty_B);
                    ZYPEZDItemValueSetter.setValue(tMsg.xsMtrAmtRate, cMsg.B.no(i).xsMtrAmtRate_B);
                    ZYPEZDItemValueSetter.setValue(tMsg.xsMtrFirstFlg, ZYPConstant.FLG_OFF_N);
                    if (minQty == null || (ZYPCommonFunc.hasValue(tMsg.xsMtrCopyQty) && tMsg.xsMtrCopyQty.getValue().compareTo(minQty) < 0)) {
                        minQty = cMsg.B.no(i).xsMtrCopyQty_B.getValue();
                    } 
                    tMsgList.add(tMsg);
                }
                preDsContrBllgMtrPk = dsContrBllgMtrPk;
            }
            if (tMsgList.size() > 0) {
                mergeContrXsCopy(cMsg, preDsContrBllgMtrPk, tMsgList, minQty);
            }
        }

        // START 2016/03/08 T.Kanasaka [QC2208, MOD]
        protected void mergeContrXsCopy(NSAL0300CMsg cMsg, BigDecimal preDsContrBllgMtrPk, List<CONTR_XS_COPYTMsg> tMsgList, BigDecimal minQty) {
            for (CONTR_XS_COPYTMsg tMsg : tMsgList) {
                if (ZYPCommonFunc.hasValue(tMsg.xsMtrCopyQty) && tMsg.xsMtrCopyQty.getValue().compareTo(minQty) == 0) {
                    ZYPEZDItemValueSetter.setValue(tMsg.xsMtrFirstFlg, ZYPConstant.FLG_ON_Y);
                }
            }

            NSAL0300Query query = NSAL0300Query.getInstance();
            NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
            List<CONTR_XS_COPYTMsg> insTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
            List<CONTR_XS_COPYTMsg> updTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
            List<CONTR_XS_COPYTMsg> delTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
            CONTR_XS_COPYTMsgArray tMsgArray = query.getContrXsCopy(glblCmpyCd, preDsContrBllgMtrPk);
            for (int i = 0; i < tMsgArray.length(); i++) {
                delTMsgList.add(tMsgArray.no(i));
            }
            for (CONTR_XS_COPYTMsg tMsg : tMsgList) {
                if (ZYPCommonFunc.hasValue(tMsg.contrXsCopyPk)) {
                    boolean found = false;
                    for (CONTR_XS_COPYTMsg delTMsg : delTMsgList) {
                        if (NSAL0300CommonLogic.isEqualNum(tMsg.contrXsCopyPk.getValue(), delTMsg.contrXsCopyPk.getValue())) {
                            diffSetter.clear();
                            checkSchdItemUpd(diffSetter.setValue(delTMsg.xsMtrCopyQty, tMsg.xsMtrCopyQty), false, true, null, preDsContrBllgMtrPk);
                            checkSchdItemUpd(diffSetter.setValue(delTMsg.xsMtrAmtRate, tMsg.xsMtrAmtRate), false, true, null, preDsContrBllgMtrPk);
                            diffSetter.setValue(delTMsg.xsMtrFirstFlg, tMsg.xsMtrFirstFlg.getValue());
                            if (diffSetter.set()) {
                                updTMsgList.add(delTMsg);
                            }
                            delTMsgList.remove(delTMsg);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        insTMsgList.add(tMsg);
                    }
                } else {
                    insTMsgList.add(tMsg);
                }
            }
            if (insTMsgList.size() > 0) {
                for (CONTR_XS_COPYTMsg tMsg : insTMsgList) {
                    BigDecimal contrXsCopyPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CONTR_XS_COPY_SQ");
                    ZYPEZDItemValueSetter.setValue(tMsg.contrXsCopyPk, contrXsCopyPk);
                }
                int insCnt = S21FastTBLAccessor.insert(insTMsgList.toArray(new CONTR_XS_COPYTMsg[insTMsgList.size()]));
                if (insCnt != insTMsgList.size()) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {"Excess Copy" });
                    return;
                }
                usgSchdItemUpdMapMtr.put(preDsContrBllgMtrPk, true);
            }
            if (updTMsgList.size() > 0) {
                int updCnt = S21FastTBLAccessor.update(updTMsgList.toArray(new CONTR_XS_COPYTMsg[updTMsgList.size()]));
                if (updCnt != updTMsgList.size()) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"Excess Copy" });
                    return;
                }
                usgSchdItemUpdMapMtr.put(preDsContrBllgMtrPk, true);
            }
            if (delTMsgList.size() > 0) {
                int delCnt = S21FastTBLAccessor.removeLogical(delTMsgList.toArray(new CONTR_XS_COPYTMsg[delTMsgList.size()]));
                if (delCnt != delTMsgList.size()) {
                    cMsg.setMessageInfo(NSAM0033E, new String[] {"Excess Copy" });
                    return;
                }
                usgSchdItemUpdMapMtr.put(preDsContrBllgMtrPk, true);
            }
        }
        // END 2016/03/08 T.Kanasaka [QC2208, MOD]

        // START 2016/10/31 T.Kanasaka [QC#15136, ADD]
        protected void mergeSvcTermCond() {
            BigDecimal dsContrPk = cMsg.dsContrPk.getValue();

            // Contract Level
            if (!NSXC002001SvcTermCond.isExistTermCondForContrLvl(this.glblCmpyCd, dsContrPk)) {
                List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanList = NSXC002001SvcTermCond.getTermCondInfoForContrLvl(this.glblCmpyCd, this.slsDt, cMsg.svcPgmMdseCd.getValue());
                for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanList) {
                    SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
                    BigDecimal svcTermCondPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ);
                    ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondPk, svcTermCondPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
                    ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondAttrbPk, defSvcTermCondInfoBean.getSvcTermCondAttrbPk());
                    ZYPEZDItemValueSetter.setValue(tMsg.svcTermAttrbMapValCd, defSvcTermCondInfoBean.getSvcTermAttrbMapValCd());

                    S21FastTBLAccessor.insert(tMsg);
                    String rtnCd = tMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Service Term Condition" });
                        return;
                    }
                }
            }

            // Machine Level
            Map<String, List<DefSvcTermCondInfoBean>> defSvcTermCondMap = new HashMap<String, List<DefSvcTermCondInfoBean>>();
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                BigDecimal dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
                if (!NSXC002001SvcTermCond.isExistTermCondForMachLvl(this.glblCmpyCd, dsContrPk, dsContrDtlPk)) {
                    String svcPgmMdseCd = getSvcPgmMdseCd(i);
                    if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
                        continue;
                    }

                    List<DefSvcTermCondInfoBean> defSvcTermCondInfoBeanList = null;
                    if (defSvcTermCondMap.containsKey(svcPgmMdseCd)) {
                        defSvcTermCondInfoBeanList = defSvcTermCondMap.get(svcPgmMdseCd);
                    } else {
                        defSvcTermCondInfoBeanList = NSXC002001SvcTermCond.getTermCondInfoForMachLvl(this.glblCmpyCd, this.slsDt, svcPgmMdseCd);
                        defSvcTermCondMap.put(svcPgmMdseCd, defSvcTermCondInfoBeanList);
                    }

                    for (DefSvcTermCondInfoBean defSvcTermCondInfoBean : defSvcTermCondInfoBeanList) {
                        SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
                        BigDecimal svcTermCondPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ);
                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondPk, svcTermCondPk);
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
                        ZYPEZDItemValueSetter.setValue(tMsg.svcTermCondAttrbPk, defSvcTermCondInfoBean.getSvcTermCondAttrbPk());
                        ZYPEZDItemValueSetter.setValue(tMsg.svcTermAttrbMapValCd, defSvcTermCondInfoBean.getSvcTermAttrbMapValCd());

                        S21FastTBLAccessor.insert(tMsg);
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0032E, new String[] {"Service Term Condition" });
                            return;
                        }
                    }
                }
            }
        }

        protected String getSvcPgmMdseCd(int sIndex) {
            String svcPgmMdseCd = null;
            BigDecimal dsContrDtlPk = sMsg.A.no(sIndex).dsContrDtlPk_A.getValue();
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (NSAL0300CommonLogic.isEqualNum(cMsg.B.no(i).dsContrDtlPk_B.getValue(), dsContrDtlPk)) {
                    svcPgmMdseCd = cMsg.B.no(i).svcPgmMdseCd_B.getValue();
                    break;
                }
            }
            return svcPgmMdseCd;
        }
        // END 2016/10/31 T.Kanasaka [QC#15136, ADD]

        // START 2017/02/07 [QC#17275, ADD]
        protected void mergeContrPhysBllgReln() {
            NSAL0300Query query = NSAL0300Query.getInstance();
            BigDecimal preDsContrBllgMtrPk = INVLD_DS_CONTR_BLLG_MTR_PK;
            BigDecimal preSvcMachMstrPk = INVLD_DS_CONTR_BLLG_MTR_PK;

            boolean insertFlg = false;
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                    insertFlg = true;
                    break;
                }
            }

            if (!insertFlg) {
                return;
            }

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                // START 2018/12/27 K.Kitachi [QC#29762, ADD]
                if (!cMsg.B.no(i).dsContrDtlTpCd_B.getValue().equals(DS_CONTR_DTL_TP.BASE_AND_USAGE) && !cMsg.B.no(i).dsContrDtlTpCd_B.getValue().equals(DS_CONTR_DTL_TP.USAGE_ONLY)) {
                    continue;
                }
                // END 2018/12/27 K.Kitachi [QC#29762, ADD]
                BigDecimal dsContrBllgMtrPk = cMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
                BigDecimal svcMachMstrPk = cMsg.B.no(i).svcMachMstrPk_B.getValue();
                BigDecimal dsContrDtlPk = cMsg.B.no(i).dsContrDtlPk_B.getValue();
                if (!NSAL0300CommonLogic.isEqualNum(preSvcMachMstrPk, svcMachMstrPk)) {
                    CONTR_PHYS_BLLG_MTR_RELNTMsgArray tMsgArray = query.getContrPhysBllgMtrRelnArray(glblCmpyCd, dsContrDtlPk);
                    if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                        // CONTR_PHYS_BLLG_MTR_RELN record exists
                        continue;
                    }

                    // insert CONTR_PHYS_BLLG_MTR_RELN (not associated DS_CONTR_BLLG_MTR)
                    // START 2017/02/14 [QC#17275-1, MOD]
                    // S21SsmEZDResult rslt = query.getSvcPhysMtrList(glblCmpyCd, svcMachMstrPk, null);
                    S21SsmEZDResult rslt = query.getSvcPhysMtrList(glblCmpyCd, svcMachMstrPk, dsContrDtlPk, null);
                    // END   2017/02/14 [QC#17275-1, MOD]
                    if (rslt != null && rslt.isCodeNormal()) {
                        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
                        for (Map<String, Object> rsltMap : rsltList) {
                            BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                            CONTR_PHYS_BLLG_MTR_RELNTMsg relnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                            ZYPEZDItemValueSetter.setValue(relnTMsg.glblCmpyCd, this.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(relnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                            ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrDtlPk, dsContrDtlPk);
                            ZYPEZDItemValueSetter.setValue(relnTMsg.machMstrPk, svcMachMstrPk);
                            ZYPEZDItemValueSetter.setValue(relnTMsg.contrMtrMultRate, (BigDecimal) rsltMap.get("MTR_MULT_RATE"));
                            ZYPEZDItemValueSetter.setValue(relnTMsg.svcPhysMtrPk, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                            ZYPEZDItemValueSetter.setValue(relnTMsg.bllblFlg, (String) rsltMap.get("MTR_ENTRY_MND_FLG"));
                            if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("MTR_ENTRY_MND_FLG"))) {
                                ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLvlNum, (String) rsltMap.get("BLLG_MTR_MAP_LVL_NUM"));
                            }
                            // START 2017/04/26 [RS#7224, ADD]
                            ZYPEZDItemValueSetter.setValue(relnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                            // END   2017/04/26 [RS#7224, ADD]

                            S21FastTBLAccessor.insert(relnTMsg);
                            String rtnCd = relnTMsg.getReturnCode();
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                                return;
                            }
                        }
                    }
                }

                // insert CONTR_PHYS_BLLG_MTR_RELN (associated DS_CONTR_BLLG_MTR)
                if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
                    if (!NSAL0300CommonLogic.isEqualNum(preDsContrBllgMtrPk, dsContrBllgMtrPk)) {
                        DS_CONTR_BLLG_MTRTMsg tMsg = query.getDsContrBllgMtr(glblCmpyCd, dsContrBllgMtrPk);
                        if (tMsg == null) {
                            cMsg.setMessageInfo(NSAM0045E, new String[] {"Ds Contract Billing Meter" });
                            return;
                        }

                        // START 2017/02/14 [QC#17275-1, MOD]
                        // S21SsmEZDResult rslt = query.getSvcPhysMtrList(glblCmpyCd, svcMachMstrPk, tMsg.bllgMtrLbCd.getValue());
                        S21SsmEZDResult rslt = query.getSvcPhysMtrList(glblCmpyCd, svcMachMstrPk, dsContrDtlPk, tMsg.bllgMtrLbCd.getValue());
                        // END   2017/02/14 [QC#17275-1, MOD]
                        if (rslt != null && rslt.isCodeNormal()) {
                            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
                            for (Map<String, Object> rsltMap : rsltList) {
                                BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                                CONTR_PHYS_BLLG_MTR_RELNTMsg relnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                                ZYPEZDItemValueSetter.setValue(relnTMsg.glblCmpyCd, this.glblCmpyCd);
                                ZYPEZDItemValueSetter.setValue(relnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                                ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrDtlPk, dsContrDtlPk);
                                ZYPEZDItemValueSetter.setValue(relnTMsg.machMstrPk, svcMachMstrPk);
                                ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLbCd, tMsg.bllgMtrLbCd);
                                ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrBllgMtrPk, tMsg.dsContrBllgMtrPk);
                                ZYPEZDItemValueSetter.setValue(relnTMsg.contrMtrMultRate, (BigDecimal) rsltMap.get("MTR_MULT_RATE"));
                                ZYPEZDItemValueSetter.setValue(relnTMsg.svcPhysMtrPk, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                                ZYPEZDItemValueSetter.setValue(relnTMsg.bllblFlg, (String) rsltMap.get("MTR_ENTRY_MND_FLG"));
                                ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLvlNum, (String) rsltMap.get("BLLG_MTR_MAP_LVL_NUM"));
                                // START 2017/04/26 [RS#7224, ADD]
                                ZYPEZDItemValueSetter.setValue(relnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                                // END   2017/04/26 [RS#7224, ADD]

                                S21FastTBLAccessor.insert(relnTMsg);
                                String rtnCd = relnTMsg.getReturnCode();
                                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                    cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                                    return;
                                }
                            }
                        }
                    }
                }
                preDsContrBllgMtrPk = dsContrBllgMtrPk;
                preSvcMachMstrPk = svcMachMstrPk;
            }
        }
        // END   2017/02/07 [QC#17275, ADD]

        public boolean hasError() {
            return NSAL0300CommonLogic.hasError(cMsg);
        }

        //START 2023/02/09/R.Takau [QC#55645,ADD]
        protected void mergeDsContrBankAcctRelen() {

            DS_CONTR_BANK_ACCT_RELNTMsg bankAcctRelnTMsg = new DS_CONTR_BANK_ACCT_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(bankAcctRelnTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bankAcctRelnTMsg.dsContrPk, cMsg.dsContrPk);
            if(PMT_TERM_CASH_DISC.CHECK_BY_PHONE.equals(cMsg.pmtTermCashDiscCd.getValue())){
                DS_CONTR_BANK_ACCT_RELNTMsg bankAcctRelnTMsgResult = (DS_CONTR_BANK_ACCT_RELNTMsg) EZDTBLAccessor.findByKey(bankAcctRelnTMsg);
                if (bankAcctRelnTMsgResult == null) {
                    ZYPEZDItemValueSetter.setValue(bankAcctRelnTMsg.dsCustBankAcctRelnPk, cMsg.dsCustBankAcctRelnPk);
                    EZDTBLAccessor.create(bankAcctRelnTMsg);
                } else {
                    if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_CP.getValue(), cMsg.ezUpTimeZone_CP.getValue(), bankAcctRelnTMsgResult.ezUpTime.getValue(), bankAcctRelnTMsgResult.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(ZZZM9004E);
                        return;
                    } else {
                        NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
                        diffSetter.setValue(bankAcctRelnTMsgResult.dsCustBankAcctRelnPk, cMsg.dsCustBankAcctRelnPk);
                        if (diffSetter.set()) {
                            S21FastTBLAccessor.update(bankAcctRelnTMsgResult);
                        }
                    }
                }
            } else{
                DS_CONTR_BANK_ACCT_RELNTMsg bankAcctRelnTMsgResult = (DS_CONTR_BANK_ACCT_RELNTMsg) EZDTBLAccessor.findByKey(bankAcctRelnTMsg);
                if (bankAcctRelnTMsgResult != null) {
                    EZDTBLAccessor.remove(bankAcctRelnTMsg);
                }
                cMsg.bankRteNum.clear();
                cMsg.dsBankAcctNum.clear();
                cMsg.dsCustBankAcctRelnPk.clear();
                cMsg.ezUpTime_CP.clear();
                cMsg.ezUpTimeZone_CP.clear();
            }
        }
        //END 2023/02/09/R.Takau [QC#55645,ADD]
    }

    private static class NSAL0300RegularContractCreator extends NSAL0300ContractCreator {

        public NSAL0300RegularContractCreator(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
            super(cMsg, sMsg);
        }

    }

    private static class NSAL0300FleetContractCreator extends NSAL0300ContractCreator {

        public NSAL0300FleetContractCreator(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
            super(cMsg, sMsg);
        }

        @Override
        protected void setupDummyMach() {
            setupDummyMach(cMsg, sMsg, glblCmpyCd, DS_CONTR_DTL_TP.FLEET);

            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            if (cMsg.B.getValidCount() > 0 && !ZYPCommonFunc.hasValue(cMsg.B.no(0).shipToCustCd_B)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_FA, IMG_OPEN_ARROW);
            }
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
        }

        @Override
        protected BigDecimal getPrntDsContrDtlPk(int bIdx) {
            if (DS_CONTR_DTL_TP.FLEET.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())) {
                return null;
            } else if (DS_CONTR_DTL_TP.ACCESSORIES.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())) {
                return cMsg.B.no(bIdx).prntDsContrDtlPk_B.getValue();
            } else {
                return getDsContrDtlPk(DS_CONTR_DTL_TP.FLEET);
            }
        }

        private BigDecimal getDsContrDtlPkForFleet() {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (DS_CONTR_DTL_TP.FLEET.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue()) && ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrDtlPk_B)) {
                    return cMsg.B.no(i).dsContrDtlPk_B.getValue();
                }
            }
            return null;
        }

        @Override
        public void mergeDsContrDtl() {
            // Fleet Line
            super.mergeDsContrDtl();

            // Fleet Detail
            NSAL0300Query query = NSAL0300Query.getInstance();
            NSAL0300DiffValueSetter diffSetter = new NSAL0300DiffValueSetter();
            if (cMsg.B.getValidCount() > 0) {
                for (int i = 0; i < sMsg.A.getValidCount(); i++) {

                    boolean existsDsContrDtl = false;
                    DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).dsContrDtlPk_A)) {
                        tMsg = query.getDsContrDtl(glblCmpyCd, sMsg.A.no(i).dsContrDtlPk_A.getValue());
                        if (tMsg != null) {
                            existsDsContrDtl = true;
                        }
                    }

                    if (existsDsContrDtl) {
                        // update
                        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(i).ezUpTime_A.getValue(), sMsg.A.no(i).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                            cMsg.setMessageInfo(ZZZM9004E);
                            return;
                        }
                        diffSetter.clear();
                        // START 2018/08/29 M.Naito [QC#27102, ADD]
                        if (!tMsg.contrEffFromDt.getValue().equals(sMsg.A.no(i).contrEffFromDt_A.getValue())) {
                            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, sMsg.A.no(i).contrEffFromDt_A.getValue(), CONTR_INTFC_SRC_TP.ORDER, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
                            if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                                diffSetter.setValue(tMsg.uplftFromDt, uplftFromDt);
                            }
                        }
                        // END 2018/08/29 M.Naito [QC#27102, ADD]
                        diffSetter.setValue(tMsg.contrEffFromDt, sMsg.A.no(i).contrEffFromDt_A);
                        diffSetter.setValue(tMsg.contrEffThruDt, sMsg.A.no(i).contrEffThruDt_A);
                        diffSetter.setValue(tMsg.mtrReadMethCd, sMsg.A.no(i).mtrReadMethCd_A);
                        if (diffSetter.set()) {
                            S21FastTBLAccessor.update(tMsg);
                            String rtnCd = tMsg.getReturnCode();
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Detail" });
                                return;
                            }
                            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), tMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                                return;
                            }
                        }
                    } else {
                        // insert
                        // new
                        tMsg = new DS_CONTR_DTLTMsg();

                        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).dsContrDtlPk_A)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A);
                        } else {
                            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, getDsContrDtlPk(sMsg.A.no(i).dsContrDtlTpCd_A.getValue(), sMsg.A.no(i).serNum_A.getValue(), sMsg.A.no(i).mdseCd_A.getValue()));
                        }
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, getDsContrPk());
                        ZYPEZDItemValueSetter.setValue(tMsg.contrEffFromDt, sMsg.A.no(i).contrEffFromDt_A);
                        ZYPEZDItemValueSetter.setValue(tMsg.contrEffThruDt, sMsg.A.no(i).contrEffThruDt_A);
                        ZYPEZDItemValueSetter.setValue(tMsg.prntDsContrDtlPk, sMsg.A.no(i).prntDsContrDtlPk_A);

                        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadMethCd, sMsg.A.no(i).mtrReadMethCd_A);

                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlTpCd, sMsg.A.no(i).dsContrDtlTpCd_A);
                        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlStsCd, getDsContrDtlStsCd(cMsg));

                        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, sMsg.A.no(i).svcConfigMstrPk_A);
                        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, sMsg.A.no(i).svcMachMstrPk_A);
                        ZYPEZDItemValueSetter.setValue(tMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);

                        // START 2016/01/25 T.Iwamoto [QC#3533, DEL]
//                        ZYPEZDItemValueSetter.setValue(tMsg.maxContrRnwCnt, BigDecimal.ZERO);
//                        ZYPEZDItemValueSetter.setValue(tMsg.maxPrcUpRatio, BigDecimal.ZERO);
                        // END   2016/01/25 T.Iwamoto [QC#3533, DEL]
                        ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
                        // START 2016/01/18 [QC#1088, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
                        // END 2016/01/18 [QC#1088, ADD]
                        // START 2016/04/06 [QC#4921, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.contrRnwTotCnt, BigDecimal.ZERO);
                        // END 2016/04/06 [QC#4921, ADD]

                        // START 2017/04/26 [RS#7224, ADD]
                        ZYPEZDItemValueSetter.setValue(tMsg.addContrFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
                        ZYPEZDItemValueSetter.setValue(tMsg.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
                        // END   2017/04/26 [RS#7224, ADD]

                        // START 2018/08/29 M.Naito [QC#27102, ADD]
                        String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, tMsg.contrEffFromDt.getValue(), CONTR_INTFC_SRC_TP.ORDER, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
                        if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                            ZYPEZDItemValueSetter.setValue(tMsg.uplftFromDt, uplftFromDt);
                        }
                        // END 2018/08/29 M.Naito [QC#27102, ADD]

                        S21FastTBLAccessor.insert(tMsg);
                        String rtnCd = tMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS Contract Detail" });
                            return;
                        }
                        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), tMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                            return;
                        }
                    }
                }
            }
        }

        // START 2018/04/03 K.Kojima [QC#21056,ADD]
        @Override
        protected void mergeDsContrBllgMtr() {
            super.mergeDsContrBllgMtr();
            NSAL0300Query query = NSAL0300Query.getInstance();
            BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
            if (cMsg.A.getValidCount() == 0) {
                return;
            }
            for (int machCnt = 0; machCnt < cMsg.A.getValidCount(); machCnt++) {
                BigDecimal dsContrDtlPk = cMsg.A.no(machCnt).dsContrDtlPk_A.getValue();
                DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlForRead(glblCmpyCd, dsContrDtlPk);
                if (dsContrDtlTMsg == null) {
                    continue;
                }
                if (!dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.BASE_AND_USAGE) && !dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.USAGE_ONLY)) {
                    continue;
                }
                BigDecimal countBllgMtr = query.countBllgMtr(glblCmpyCd, dsContrDtlPk);
                if (countBllgMtr != null && countBllgMtr.compareTo(BigDecimal.ZERO) != 0) {
                    continue;
                }
                List<Map<String, Object>> insBllgMtrList = query.getInsertBllgMtrInfo(glblCmpyCd, dsContrPk, DS_CONTR_DTL_TP.FLEET, dsContrDtlTMsg.svcMachMstrPk.getValue());
                if (insBllgMtrList == null || insBllgMtrList.size() == 0) {
                    continue;
                }
                String preBllgMtrLbCd = null;
                for (Map<String, Object> insBllgMtr : insBllgMtrList) {
                    String bllgMtrLbCd = (String) insBllgMtr.get("BLLG_MTR_LB_CD");
                    if (preBllgMtrLbCd != null && preBllgMtrLbCd.equals(bllgMtrLbCd)) {
                        continue;
                    }
                    preBllgMtrLbCd = bllgMtrLbCd;
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ));
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrDtlPk, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgMtrLbCd, bllgMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.xsChrgTpCd, XS_CHRG_TP.POINT);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SAVED);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.intgMdseCd, (String) insBllgMtr.get("INTG_MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, dsContrDtlTMsg.qltyAsrnHldFlg);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.mtrHldFlg, dsContrDtlTMsg.mtrHldFlg);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.contrHldFlg, dsContrDtlTMsg.contrHldFlg);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgHldFlg, dsContrDtlTMsg.bllgHldFlg);
                    ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.qltyAsrnHldPendApvlFlg, dsContrDtlTMsg.qltyAsrnHldPendApvlFlg);
                    S21FastTBLAccessor.insert(dsContrBllgMtrTMsg);
                    String rtnCd = dsContrBllgMtrTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Ds Contract Billing Meter" });
                        return;
                    }
                    if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrBllgMtrTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                        return;
                    }
                    for (Map<String, Object> insBllgMtrReln : insBllgMtrList) {
                        String bllgMtrLbCdReln = (String) insBllgMtrReln.get("BLLG_MTR_LB_CD");
                        String mdlMtrLbCdReln = (String) insBllgMtrReln.get("MDL_MTR_LB_CD");
                        if (!bllgMtrLbCdReln.equals(bllgMtrLbCd)) {
                            continue;
                        }
                        Map<String, Object> insReln = query.getInsertRelnInfo(glblCmpyCd, dsContrPk, bllgMtrLbCdReln, mdlMtrLbCdReln);
                        CONTR_PHYS_BLLG_MTR_RELNTMsg relnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                        ZYPEZDItemValueSetter.setValue(relnTMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(relnTMsg.contrPhysBllgMtrRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ));
                        ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrDtlPk, dsContrDtlPk);
                        ZYPEZDItemValueSetter.setValue(relnTMsg.machMstrPk, dsContrDtlTMsg.svcMachMstrPk);
                        ZYPEZDItemValueSetter.setValue(relnTMsg.contrMtrMultRate, (BigDecimal) insReln.get("CONTR_MTR_MULT_RATE"));
                        ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLbCd, bllgMtrLbCd);
                        ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrBllgMtrPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk);
                        ZYPEZDItemValueSetter.setValue(relnTMsg.svcPhysMtrPk, (BigDecimal) insBllgMtrReln.get("SVC_PHYS_MTR_PK"));
                        ZYPEZDItemValueSetter.setValue(relnTMsg.bllblFlg, (String) insReln.get("BLLBL_FLG"));
                        ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLvlNum, (String) insReln.get("BLLG_MTR_LVL_NUM"));
                        ZYPEZDItemValueSetter.setValue(relnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                        S21FastTBLAccessor.insert(relnTMsg);
                        rtnCd = dsContrBllgMtrTMsg.getReturnCode();
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                            cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                            return;
                        }
                    }
                }
                
            }
        }

        // END 2018/04/03 K.Kojima [QC#21056,ADD]

        // START 2016/10/31 T.Kanasaka [QC#15136, ADD]
        protected String getSvcPgmMdseCd(int sIndex) {
            String svcPgmMdseCd = null;
            if (cMsg.B.getValidCount() > 0) {
                svcPgmMdseCd = cMsg.B.no(0).svcPgmMdseCd_B.getValue();
            }
            return svcPgmMdseCd;
        }
        // END 2016/10/31 T.Kanasaka [QC#15136, ADD]

        // START 2017/02/09 [QC#17275, ADD]
        @Override
        protected void mergeContrPhysBllgReln() {
            // START 2018/04/04 K.Kojima [QC#21056,ADD]
            NSAL0300Query query = NSAL0300Query.getInstance();

            // insert CONTR_PHYS_BLLG_MTR_RELN (not associated DS_CONTR_BLLG_MTR)
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                BigDecimal dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).svcMachMstrPk_A)) {
                    continue;
                }
                DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlForRead(glblCmpyCd, dsContrDtlPk);
                if (dsContrDtlTMsg == null) {
                    continue;
                }
                if (!dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.BASE_AND_USAGE) && !dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.USAGE_ONLY)) {
                    continue;
                }
                BigDecimal countBllgMtr = query.countBllgMtr(glblCmpyCd, dsContrDtlPk);
                if (countBllgMtr == null || countBllgMtr.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                List<Map<String, Object>> rsltList = query.getPhysMtrNoReln(glblCmpyCd, dsContrDtlPk);
                if (rsltList == null || rsltList.size() == 0) {
                    continue;
                }
                for (Map<String, Object> rsltMap : rsltList) {
                    BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                    CONTR_PHYS_BLLG_MTR_RELNTMsg relnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(relnTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrDtlPk, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.machMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.svcPhysMtrPk, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.bllblFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);

                    S21FastTBLAccessor.insert(relnTMsg);
                    String rtnCd = relnTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                        return;
                    }
                }
            }
            // END 2018/04/04 K.Kojima [QC#21056,ADD]
        }
        // END   2017/02/09 [QC#17275, ADD]
    }

    private static class NSAL0300AggregateContractCreator extends NSAL0300ContractCreator {

        public NSAL0300AggregateContractCreator(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
            super(cMsg, sMsg);
        }

        @Override
        protected void setupDummyMach() {
            setupDummyMach(cMsg, sMsg, glblCmpyCd, DS_CONTR_DTL_TP.AGGREGATE);
        }

        @Override
        protected BigDecimal getPrntDsContrDtlPk(int bIdx) {
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())) {
                return null;
            } else if (DS_CONTR_DTL_TP.ACCESSORIES.equals(cMsg.B.no(bIdx).dsContrDtlTpCd_B.getValue())) {
                return cMsg.B.no(bIdx).prntDsContrDtlPk_B.getValue();
            } else {
                return getDsContrDtlPk(DS_CONTR_DTL_TP.AGGREGATE);
            }
        }

        // START 2017/02/09 [QC#17275, ADD]
        @Override
        protected void mergeContrPhysBllgReln() {
            // START 2018/04/04 K.Kojima [QC#21056,ADD]
            NSAL0300Query query = NSAL0300Query.getInstance();

            boolean insertFlg = false;
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                    insertFlg = true;
                    break;
                }
            }

            if (!insertFlg) {
                return;
            }

            // insert CONTR_PHYS_BLLG_MTR_RELN (associated DS_CONTR_BLLG_MTR)
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                BigDecimal dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
                BigDecimal svcMachMstrPk = cMsg.A.no(i).svcMachMstrPk_A.getValue();
                if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    continue;
                }
                DS_CONTR_BLLG_MTRTMsgArray bllgMtrArray = query.getDsContrBllgMtrListFindByCondition(glblCmpyCd, dsContrDtlPk);
                if (bllgMtrArray == null || bllgMtrArray.getValidCount() == 0) {
                    continue;
                }
                CONTR_PHYS_BLLG_MTR_RELNTMsgArray tMsgArray = query.getContrPhysBllgMtrRelnArray(glblCmpyCd, dsContrDtlPk);
                if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                    // CONTR_PHYS_BLLG_MTR_RELN record exists
                    continue;
                }
                List<Map<String, Object>> rsltList = query.getInsertBllgMtrInfo(glblCmpyCd, cMsg.dsContrPk.getValue(), DS_CONTR_DTL_TP.AGGREGATE, svcMachMstrPk);
                if (rsltList == null || rsltList.size() == 0) {
                    continue;
                }
                Map<String, BigDecimal> bllgMtrLbMap = new HashMap<String, BigDecimal>();
                for (int bllgMtrCount = 0; bllgMtrCount < bllgMtrArray.getValidCount(); bllgMtrCount++) {
                    bllgMtrLbMap.put(bllgMtrArray.no(bllgMtrCount).bllgMtrLbCd.getValue(), bllgMtrArray.no(bllgMtrCount).dsContrBllgMtrPk.getValue());
                }
                for (Map<String, Object> rsltMap : rsltList) {
                    String bllgMtrLbCd = (String) rsltMap.get("BLLG_MTR_LB_CD");
                    String mdlMtrLbCd = (String) rsltMap.get("MDL_MTR_LB_CD");
                    Map<String, Object> insReln = query.getInsertRelnInfo(glblCmpyCd, cMsg.dsContrPk.getValue(), bllgMtrLbCd, mdlMtrLbCd);
                    BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                    CONTR_PHYS_BLLG_MTR_RELNTMsg relnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(relnTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrDtlPk, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.machMstrPk, svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLbCd, bllgMtrLbCd);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrBllgMtrPk, bllgMtrLbMap.get(bllgMtrLbCd));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.contrMtrMultRate, (BigDecimal) insReln.get("CONTR_MTR_MULT_RATE"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.svcPhysMtrPk, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.bllblFlg, (String) insReln.get("BLLBL_FLG"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.bllgMtrLvlNum, (String) insReln.get("BLLG_MTR_LVL_NUM"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);

                    S21FastTBLAccessor.insert(relnTMsg);
                    String rtnCd = relnTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                        return;
                    }
                }
            }

            // insert CONTR_PHYS_BLLG_MTR_RELN (not associated DS_CONTR_BLLG_MTR)
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                BigDecimal dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).svcMachMstrPk_A)) {
                    continue;
                }
                DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlForRead(glblCmpyCd, dsContrDtlPk);
                if (dsContrDtlTMsg == null) {
                    continue;
                }
                if (!dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.BASE_AND_USAGE) && !dsContrDtlTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.USAGE_ONLY)) {
                    continue;
                }
                BigDecimal countBllgMtr = query.countBllgMtr(glblCmpyCd, dsContrDtlPk);
                if (countBllgMtr == null || countBllgMtr.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                List<Map<String, Object>> rsltList = query.getPhysMtrNoReln(glblCmpyCd, dsContrDtlPk);
                if (rsltList == null || rsltList.size() == 0) {
                    continue;
                }
                for (Map<String, Object> rsltMap : rsltList) {
                    BigDecimal contrPhysBllgMtrRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_PHYS_BLLG_MTR_RELN_SQ);
                    CONTR_PHYS_BLLG_MTR_RELNTMsg relnTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                    ZYPEZDItemValueSetter.setValue(relnTMsg.glblCmpyCd, this.glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.contrPhysBllgMtrRelnPk, contrPhysBllgMtrRelnPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.dsContrDtlPk, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.machMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.svcPhysMtrPk, (BigDecimal) rsltMap.get("SVC_PHYS_MTR_PK"));
                    ZYPEZDItemValueSetter.setValue(relnTMsg.bllblFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(relnTMsg.actvFlg, ZYPConstant.FLG_ON_Y);

                    S21FastTBLAccessor.insert(relnTMsg);
                    String rtnCd = relnTMsg.getReturnCode();
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"Contract Physical Billing Meter Relation" });
                        return;
                    }
                }
            }
            // END 2018/04/04 K.Kojima [QC#21056,ADD]
        }
        // END 2017/02/09 [QC#17275, ADD]
    }

    private static class NSAL0300WarrantyContractCreator extends NSAL0300ContractCreator {

        public NSAL0300WarrantyContractCreator(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
            super(cMsg, sMsg);
        }

        // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//        @Override
//        protected void mergeDsContrCrCardPo() {
//            // Do nothing
//        }

        @Override
        protected void mergeDsContrCrCardPoForPo() {
            // Do nothing
        }

        @Override
        protected void mergeDsContrCrCardPoForCrCard() {
            // Do nothing
        }
        // END 2019/01/09 K.Kitachi [QC#26928, MOD]

        @Override
        protected void mergeDsContrRnwEscl() {
            // Do nothing
        }

        @Override
        protected void mergeDsContrBllgMtr() {
            // Do nothing
        }

        @Override
        protected void mergeContrXsCopy() {
            // Do nothing
        }

        // START 2016/10/31 T.Kanasaka [QC#15136, ADD]
        @Override
        protected void mergeSvcTermCond() {
            // Do nothing
        }
        // END 2016/10/31 T.Kanasaka [QC#15136, ADD]

        // START 2017/02/09 [QC#17275, ADD]
        @Override
        protected void mergeContrPhysBllgReln() {
            // Do nothing
        }
        // END   2017/02/09 [QC#17275, ADD]
    }

    // START 2017/07/31 M.Kidokoro [QC#20116, ADD]
    private void doProcess_NSAL0300Scrn00_OpenWin_CompleteContract(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
        if (NSAL0300CommonLogic.hasError(cMsg)) {
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        // String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd,
        // BIZ_ID);
        NSAL0300Query query = NSAL0300Query.getInstance();

        // Update DS Contract
        if (DS_CONTR_STS.DRAFT.equals(cMsg.dsContrStsCd.getValue())) {
            DS_CONTRTMsg dsContrTMsg = query.getDsContr(glblCmpyCd, cMsg.dsContrPk.getValue());
            if (dsContrTMsg == null) {
                cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract" });
                return;
            }

            ZYPEZDItemValueSetter.setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.ENTERED);
            S21FastTBLAccessor.update(dsContrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract" });
                return;
            }
            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), null, null, null, null, null, null)) {
                return;
            }
        }

        // Update DS Contract Detail
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = query.getDsContrDtlList(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrDtlTMsgArray == null) {
            cMsg.setMessageInfo(NSAM0045E, new String[] {"DS Contract Detail" });
            return;
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
            if (DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SUBMITED);
                S21FastTBLAccessor.update(dsContrDtlTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Detail" });
                    return;
                }
                if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), null, null, null, null, null)) {
                    return;
                }
            }

            // Update DS Contract Billing Meter
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(j);
                    if (DS_CONTR_DTL_STS.SAVED.equals(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SUBMITED);
                        S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgMtrTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Billing Meter" });
                            return;
                        }
                        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), null, null, null, null)) {
                            return;
                        }
                    }
                }
            }

            // Update DS Contract Price Effective
            DS_CONTR_PRC_EFFTMsgArray dsContrPrcEffTMsgArray = query.getDsContrPrcEffList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (dsContrPrcEffTMsgArray != null) {
                for (int j = 0; j < dsContrPrcEffTMsgArray.getValidCount(); j++) {
                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = dsContrPrcEffTMsgArray.no(j);
                    if (DS_CONTR_DTL_STS.SAVED.equals(dsContrPrcEffTMsg.dsContrPrcEffStsCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SUBMITED);
                        S21FastTBLAccessor.update(dsContrPrcEffTMsg);
                        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrPrcEffTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NSAM0031E, new String[] {"DS Contract Price Effective" });
                            return;
                        }
                        if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffPk
                                .getValue(), dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), dsContrPrcEffTMsg.contrPrcEffThruDt.getValue(), dsContrPrcEffTMsg.baseChrgFlg.getValue())) {
                            return;
                        }
                    }
                }
            }
        }
    }
    // END 2017/07/31 M.Kidokoro [QC#20116, ADD]
    // START 2024/03/22 Y.Tamai[QC#63463,ADD]
    private boolean checkNotExistsActiveContract(NSAL0300CMsg cMsg, String glblCmpyCd, String actvFlg) {
        Map<String, Object> resultMap = NSAL0300Query.getInstance().getActiveContrBakSmryPk(glblCmpyCd, cMsg.dsContrPk.getValue(), actvFlg);
        if (resultMap == null) {
            return true;
        }
        return false;
    }

    private boolean checkExistsContrBillingHold(NSAL0300CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> resultMap = NSAL0300Query.getInstance().getActiveContrBakSmryPk(glblCmpyCd, cMsg.dsContrPk.getValue(), ZYPConstant.FLG_ON_Y);
        if (resultMap != null) {
            BigDecimal contrBakSmryPk = (BigDecimal) resultMap.get("CONTR_BAK_SMRY_PK");
            BigDecimal dsContrPk = (BigDecimal) resultMap.get("DS_CONTR_PK");
            String bakCratTs = (String) resultMap.get("BAK_CRAT_TS");
            BigDecimal bllgHldCnt = NSAL0300Query.getInstance().getExistBllgHldCnt(glblCmpyCd, dsContrPk, contrBakSmryPk, bakCratTs);
            if (BigDecimal.ZERO.compareTo(bllgHldCnt) < 0) {
                return true;
            }
        }

        DS_CONTRTMsg dsContrTmsg = NSAL0300Query.getInstance().getDsContr(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(dsContrTmsg.bllgHldFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(dsContrTmsg.qltyAsrnHldFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean revertToBackupSource(NSAL0300CMsg cMsg, String glblCmpyCd, String actvFlg) {
        Map<String, Object> resultMap = NSAL0300Query.getInstance().getActiveContrBakSmryPk(glblCmpyCd, cMsg.dsContrPk.getValue(), actvFlg);

        if (resultMap != null) {
            BigDecimal contrBakSmryPk = (BigDecimal) resultMap.get("CONTR_BAK_SMRY_PK");
            BigDecimal dsContrPk = (BigDecimal) resultMap.get("DS_CONTR_PK");
            String bakCratTs = (String) resultMap.get("BAK_CRAT_TS");

            // common logic
            List<BigDecimal> dsContrDtlPkList = NSAL0300Query.getInstance().getDsContrDtlPkList(dsContrPk, glblCmpyCd);
            List<BigDecimal> dsContrBllgMtrPkList = NSAL0300Query.getInstance().getDsContrBllgMtrPkList(dsContrPk, glblCmpyCd);
            List<BigDecimal> dsSubContrPkList = NSAL0300Query.getInstance().getDsSubContrPkList(dsContrPk, glblCmpyCd);

            // revert using DS_CONTR_BLLG_MTR_PK table
            if (!revertBackupSourceForMtrPk(dsContrBllgMtrPkList, glblCmpyCd, contrBakSmryPk, bakCratTs, dsContrPk)) {
                return false;
            }
            // revert using DS_SUB_CONTR_PK table
            if (!revertBackupSourceForDsSubPk(dsSubContrPkList, glblCmpyCd, contrBakSmryPk, bakCratTs, dsContrPk)) {
                return false;
            }
            // revert using DS_CONTR_DTL_PK table
            if (!revertBackupSourceForDsContrDtlPk(dsContrDtlPkList, glblCmpyCd, contrBakSmryPk, bakCratTs, dsContrPk)) {
                return false;
            }
            // Remove Staging Infomation
            if (removeStagingInfo(cMsg)) {
                return false;
            }
            // revert using DS_CONTR_PK table
            if (!revertBackupSourceForDsContrPk(dsContrPk, glblCmpyCd, contrBakSmryPk, bakCratTs)) {
                return false;
            }
        }
        return true;
    }

    private boolean revertBackupSourceForMtrPk(List<BigDecimal> dsContrBllgMtrPkList, String glblCmpyCd, BigDecimal contrBakSmryPk, String bakCratTs, BigDecimal dsContrPk) {
        List<BigDecimal> sinceActiveTblPkList = new ArrayList<BigDecimal>();
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        // CONTR_XS_COPY
        CONTR_XS_COPYTMsg contrXsCopyTmsg = new CONTR_XS_COPYTMsg();
        // delete records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForMtrTblPkList(contrXsCopyTmsg.getTableName(), CONTR_XS_COPY_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(contrXsCopyTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrXsCopyTmsg.contrXsCopyPk, sinActiveTblPk);
            EZDTBLAccessor.remove(contrXsCopyTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrXsCopyTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_BLLG_SCHD_MTR
        DS_CONTR_BLLG_SCHD_MTRTMsg dsContrBllgSchdMtrTmsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        // delete records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForMtrTblPkList(dsContrBllgSchdMtrTmsg.getTableName(), DS_CONTR_BLLG_SCHD_MTR_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrBllgSchdMtrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrBllgSchdMtrTmsg.dsContrBllgSchdMtrPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrBllgSchdMtrTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgSchdMtrTmsg.getReturnCode())) {
                return false;
            }
        }
        // DS_CONTR_PRC_EFF_MTR
        DS_CONTR_PRC_EFF_MTRTMsg dsContrPrcEffTmsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        // delete records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForMtrTblPkList(dsContrPrcEffTmsg.getTableName(), DS_CONTR_PRC_EFF_MTR_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.dsContrPrcEffMtrPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrPrcEffTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrPrcEffTmsg.getReturnCode())) {
                return false;
            }
        }
        try {
            // CONTR_XS_COPY
            rs = getBackupPkList(stmtSelect, CONTR_XS_COPY_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                CONTR_XS_COPYTMsg tmpContrXsCopyTmsg = new CONTR_XS_COPYTMsg();
                ZYPEZDItemValueSetter.setValue(tmpContrXsCopyTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpContrXsCopyTmsg.contrXsCopyPk, rs.getBigDecimal(CONTR_XS_COPY_PK));
                if (!(insertIntoBackupSourceTable(rs, new CONTR_XS_COPYTMsg(), glblCmpyCd, tmpContrXsCopyTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_SCHD_MTR
            rs = getBackupPkList(stmtSelect, CONTR_BLLG_SCHD_MTR_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_BLLG_SCHD_MTRTMsg tmpDsContrBllgSchdMtrTmsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgSchdMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgSchdMtrTmsg.dsContrBllgSchdMtrPk, rs.getBigDecimal(DS_CONTR_BLLG_SCHD_MTR_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_BLLG_SCHD_MTRTMsg(), glblCmpyCd, tmpDsContrBllgSchdMtrTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_PRC_EFF_MTR
            rs = getBackupPkList(stmtSelect, CONTR_PRC_EFF_MTR_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_PRC_EFF_MTRTMsg tmpDsContrPrcEffTmsg = new DS_CONTR_PRC_EFF_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrPrcEffTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrPrcEffTmsg.dsContrPrcEffMtrPk, rs.getBigDecimal(DS_CONTR_PRC_EFF_MTR_PK));
                if ((!insertIntoBackupSourceTable(rs, new DS_CONTR_PRC_EFF_MTRTMsg(), glblCmpyCd, tmpDsContrPrcEffTmsg))) {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        return true;
    }

    private boolean revertBackupSourceForDsSubPk(List<BigDecimal> dsSubContrPkList, String glblCmpyCd, BigDecimal contrBakSmryPk, String bakCratTs, BigDecimal dsContrPk) {
        List<BigDecimal> sinceActiveTblPkList = null;
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        // DS_SUB_CONTR_MTR
        DS_SUB_CONTR_MTRTMsg dsSubContrMtrTmsg = new DS_SUB_CONTR_MTRTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsSubTblPkList(dsSubContrMtrTmsg.getTableName(), DS_SUB_CONTR_MTR_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsSubContrMtrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsSubContrMtrTmsg.dsSubContrMtrPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsSubContrMtrTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsSubContrMtrTmsg.getReturnCode())) {
                return false;
            }
        }
        try {
            // DS_SUB_CONTR_MTR
            rs = getBackupPkList(stmtSelect, SUB_CONTR_MTR_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_SUB_CONTR_MTRTMsg tmpDsSubContrMtrTmsg = new DS_SUB_CONTR_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsSubContrMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsSubContrMtrTmsg.dsSubContrMtrPk, rs.getBigDecimal(DS_SUB_CONTR_MTR_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_SUB_CONTR_MTRTMsg(), glblCmpyCd, tmpDsSubContrMtrTmsg))) {
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        return true;
    }

    private boolean revertBackupSourceForDsContrDtlPk(List<BigDecimal> dsContrDtlPkList, String glblCmpyCd, BigDecimal contrBakSmryPk, String bakCratTs, BigDecimal dsContrPk) {
        List<BigDecimal> sinceActiveTblPkList = new ArrayList<BigDecimal>();
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        // CONTR_PHYS_BLLG_MTR_RELN
        CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTmsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(contrPhysBllgMtrRelnTmsg.getTableName(), CONTR_PHYS_BLLG_MTR_RELN_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrRelnTmsg.contrPhysBllgMtrRelnPk, sinActiveTblPk);
            EZDTBLAccessor.remove(contrPhysBllgMtrRelnTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(contrPhysBllgMtrRelnTmsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_CONTR_BLLG_MTR
        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg = new DS_CONTR_BLLG_MTRTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsContrBllgMtrTmsg.getTableName(), DS_CONTR_BLLG_MTR_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTmsg.dsContrBllgMtrPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrBllgMtrTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgMtrTmsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_CONTR_BLLG_SCHD
        DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg = new DS_CONTR_BLLG_SCHDTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsContrBllgSchdTmsg.getTableName(), DS_CONTR_BLLG_SCHD_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrBllgSchdTmsg.dsContrBllgSchdPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrBllgSchdTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgSchdTmsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_CONTR_BLLG_SCHD_SMRY
        DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTmsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsContrBllgSchdSmryTmsg.getTableName(), DS_CONTR_BLLG_SCHD_SMRY_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrBllgSchdSmryTmsg.dsContrBllgSchdSmryPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrBllgSchdSmryTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgSchdSmryTmsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_CONTR_PRC_ALLOC
        DS_CONTR_PRC_ALLOCTMsg dsContrPrcAllocTmsg = new DS_CONTR_PRC_ALLOCTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsContrPrcAllocTmsg.getTableName(), DS_CONTR_PRC_ALLOC_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrPrcAllocTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrPrcAllocTmsg.dsContrPrcAllocPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrPrcAllocTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrPrcAllocTmsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_CONTR_PRC_EFF
        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTmsg = new DS_CONTR_PRC_EFFTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsContrPrcEffTmsg.getTableName(), DS_CONTR_PRC_EFF_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrPrcEffTmsg.dsContrPrcEffPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrPrcEffTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrPrcEffTmsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_CONTR_SKIP_RECOV
        DS_CONTR_SKIP_RECOVTMsg dsContrSkipRecovTMsg = new DS_CONTR_SKIP_RECOVTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsContrSkipRecovTMsg.getTableName(), DS_CONTR_SKIP_RECOV_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrSkipRecovTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrSkipRecovTMsg.dsContrSkipRecovPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrSkipRecovTMsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrSkipRecovTMsg.getReturnCode()))) {
                return false;
            }
        }

        // DS_SUB_CONTR
        DS_SUB_CONTRTMsg dsSubContrTmsg = new DS_SUB_CONTRTMsg();
        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrDtlTblPkList(dsSubContrTmsg.getTableName(), DS_SUB_CONTR_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsSubContrTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsSubContrTmsg.dsSubContrPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsSubContrTmsg);
            if (!(EZDTBLAccessor.RTNCD_NORMAL.equals(dsSubContrTmsg.getReturnCode()))) {
                return false;
            }
        }
        try {
            // CONTR_PHYS_BLLG_MTR_RELN
            rs = getBackupPkList(stmtSelect, CONTR_PHYS_BLLG_MTR_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                CONTR_PHYS_BLLG_MTR_RELNTMsg tmpContrPhysBllgMtrRelnTmsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tmpContrPhysBllgMtrRelnTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpContrPhysBllgMtrRelnTmsg.contrPhysBllgMtrRelnPk, rs.getBigDecimal(CONTR_PHYS_BLLG_MTR_RELN_PK));
                if (!(insertIntoBackupSourceTable(rs, new CONTR_PHYS_BLLG_MTR_RELNTMsg(), glblCmpyCd, tmpContrPhysBllgMtrRelnTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_MTR
            rs = getBackupPkList(stmtSelect, CONTR_BLLG_MTR_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_BLLG_MTRTMsg tmpDsContrBllgMtrTmsg = new DS_CONTR_BLLG_MTRTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgMtrTmsg.dsContrBllgMtrPk, rs.getBigDecimal(DS_CONTR_BLLG_MTR_PK));
                if ((!insertIntoBackupSourceTable(rs, new DS_CONTR_BLLG_MTRTMsg(), glblCmpyCd, tmpDsContrBllgMtrTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_SCHD
            rs = getBackupPkList(stmtSelect, CONTR_BLLG_SCHD_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_BLLG_SCHDTMsg tmpDsContrBllgSchdTmsg = new DS_CONTR_BLLG_SCHDTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgSchdTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgSchdTmsg.dsContrBllgSchdPk, rs.getBigDecimal(DS_CONTR_BLLG_SCHD_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_BLLG_SCHDTMsg(), glblCmpyCd, tmpDsContrBllgSchdTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_SCHD_SMRY
            rs = getBackupPkList(stmtSelect, CONTR_BLLG_SCHD_SMRY_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_BLLG_SCHD_SMRYTMsg tmpDsContrBllgSchdSmryTmsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgSchdSmryTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrBllgSchdSmryTmsg.dsContrBllgSchdSmryPk, rs.getBigDecimal(DS_CONTR_BLLG_SCHD_SMRY_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_BLLG_SCHD_SMRYTMsg(), glblCmpyCd, tmpDsContrBllgSchdSmryTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_PRC_ALLOC
            rs = getBackupPkList(stmtSelect, CONTR_PRC_ALLOC_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_PRC_ALLOCTMsg tmpDsContrPrcAllocTmsg = new DS_CONTR_PRC_ALLOCTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrPrcAllocTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrPrcAllocTmsg.dsContrPrcAllocPk, rs.getBigDecimal(DS_CONTR_PRC_ALLOC_PK));
                if (!insertIntoBackupSourceTable(rs, new DS_CONTR_PRC_ALLOCTMsg(), glblCmpyCd, tmpDsContrPrcAllocTmsg)) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_PRC_EFF
            rs = getBackupPkList(stmtSelect, CONTR_PRC_EFF_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_PRC_EFFTMsg tmpDsContrPrcEffTmsg = new DS_CONTR_PRC_EFFTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrPrcEffTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrPrcEffTmsg.dsContrPrcEffPk, rs.getBigDecimal(DS_CONTR_PRC_EFF_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_PRC_EFFTMsg(), glblCmpyCd, tmpDsContrPrcEffTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_SKIP_RECOV
            rs = getBackupPkList(stmtSelect, CONTR_SKIP_RECOV_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_SKIP_RECOVTMsg tmpdsContrSkipRecovTMsg = new DS_CONTR_SKIP_RECOVTMsg();
                ZYPEZDItemValueSetter.setValue(tmpdsContrSkipRecovTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpdsContrSkipRecovTMsg.dsContrSkipRecovPk, rs.getBigDecimal(DS_CONTR_SKIP_RECOV_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_SKIP_RECOVTMsg(), glblCmpyCd, tmpdsContrSkipRecovTMsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_SUB_CONTR
            rs = getBackupPkList(stmtSelect, SUB_CONTR_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_SUB_CONTRTMsg tmpDsSubContrTmsg = new DS_SUB_CONTRTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsSubContrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsSubContrTmsg.dsSubContrPk, rs.getBigDecimal(DS_SUB_CONTR_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_SUB_CONTRTMsg(), glblCmpyCd, tmpDsSubContrTmsg))) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        return true;
    }

    private boolean revertBackupSourceForDsContrPk(BigDecimal dsContrPk, String glblCmpyCd, BigDecimal contrBakSmryPk, String bakCratTs) {
        List<BigDecimal> sinceActiveTblPkList = new ArrayList<BigDecimal>();
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        // DS_CONTR_ADDL_CHRG
        DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTmsg = new DS_CONTR_ADDL_CHRGTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrAddlChrgTmsg.getTableName(), DS_CONTR_ADDL_CHRG_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrAddlChrgTmsg.dsContrAddlChrgPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrAddlChrgTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrAddlChrgTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_BANK_ACCT_RELN
        DS_CONTR_BANK_ACCT_RELNTMsg dsContrBankAcctRelnTmsg = new DS_CONTR_BANK_ACCT_RELNTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrBankAcctRelnTmsg.getTableName(), DS_CONTR_BANK_ACCT_RELN_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrBankAcctRelnTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrBankAcctRelnTmsg.dsCustBankAcctRelnPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrBankAcctRelnTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBankAcctRelnTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_BR_ALLOC
        DS_CONTR_BR_ALLOCTMsg dsContrBrAllocTmsg = new DS_CONTR_BR_ALLOCTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrBrAllocTmsg.getTableName(), DS_CONTR_BR_ALLOC_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrBrAllocTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrBrAllocTmsg.dsContrBrAllocPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrBrAllocTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBrAllocTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_CR_CARD_PO
        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTmsg = new DS_CONTR_CR_CARD_POTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrCrCardPoTmsg.getTableName(), DS_CONTR_CR_CARD_PO_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrCrCardPoTmsg.dsContrCrCardPoPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrCrCardPoTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrCrCardPoTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_DTL
        DS_CONTR_DTLTMsg dsContrDtlTmsg = new DS_CONTR_DTLTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrDtlTmsg.getTableName(), DS_CONTR_DTL_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrDtlTmsg.dsContrDtlPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrDtlTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_RNW_ESCL
        DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTmsg = new DS_CONTR_RNW_ESCLTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrRnwEsclTmsg.getTableName(), DS_CONTR_RNW_ESCL_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrRnwEsclTmsg.dsContrRnwEsclPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrRnwEsclTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrRnwEsclTmsg.getReturnCode())) {
                return false;
            }
        }

        // DS_CONTR_SEG_ALLOC
        DS_CONTR_SEG_ALLOCTMsg dsContrSegAllocTmsg = new DS_CONTR_SEG_ALLOCTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(dsContrSegAllocTmsg.getTableName(), DS_CONTR_SEG_ALLOC_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(dsContrSegAllocTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsContrSegAllocTmsg.dsContrSegAllocPk, sinActiveTblPk);
            EZDTBLAccessor.remove(dsContrSegAllocTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrSegAllocTmsg.getReturnCode())) {
                return false;
            }
        }

        // SVC_TERM_COND
        SVC_TERM_CONDTMsg svcTermCondTmsg = new SVC_TERM_CONDTMsg();

        // deleting records created since active
        sinceActiveTblPkList = NSAL0300Query.getInstance().getSinceActiveForDsContrTblPkList(svcTermCondTmsg.getTableName(), SVC_TERM_COND_PK, dsContrPk, glblCmpyCd, bakCratTs);
        for (BigDecimal sinActiveTblPk : sinceActiveTblPkList) {
            ZYPEZDItemValueSetter.setValue(svcTermCondTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(svcTermCondTmsg.svcTermCondPk, sinActiveTblPk);
            EZDTBLAccessor.remove(svcTermCondTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTermCondTmsg.getReturnCode())) {
                return false;
            }
        }

        try {
            // DS_CONTR_DTL
            rs = getBackupPkList(stmtSelect, CONTR_DTL_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_DTLTMsg tmpDsContrDtlTmsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrDtlTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrDtlTmsg.dsContrDtlPk, rs.getBigDecimal(DS_CONTR_DTL_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_DTLTMsg(), glblCmpyCd, tmpDsContrDtlTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_ADDL_CHRG
            rs = getBackupPkList(stmtSelect, CONTR_ADDL_CHRG_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_ADDL_CHRGTMsg tmpDsContrAddlChrgTmsg = new DS_CONTR_ADDL_CHRGTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrAddlChrgTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrAddlChrgTmsg.dsContrAddlChrgPk, rs.getBigDecimal(DS_CONTR_ADDL_CHRG_PK));
                if ((!insertIntoBackupSourceTable(rs, new DS_CONTR_ADDL_CHRGTMsg(), glblCmpyCd, tmpDsContrAddlChrgTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BANK_ACCT_RELN
            rs = getBackupPkList(stmtSelect, CONTR_BANK_ACCT_RELN_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_BANK_ACCT_RELNTMsg tmpDsContrBankAcctRelnTmsg = new DS_CONTR_BANK_ACCT_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrBankAcctRelnTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrBankAcctRelnTmsg.dsContrPk, rs.getBigDecimal(DS_CONTR_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_BANK_ACCT_RELNTMsg(), glblCmpyCd, tmpDsContrBankAcctRelnTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BR_ALLOC
            rs = getBackupPkList(stmtSelect, CONTR_BR_ALLOC_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_BR_ALLOCTMsg tmpDsContrBrAllocTmsg = new DS_CONTR_BR_ALLOCTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrBrAllocTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrBrAllocTmsg.dsContrBrAllocPk, rs.getBigDecimal(DS_CONTR_BR_ALLOC_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_BR_ALLOCTMsg(), glblCmpyCd, tmpDsContrBrAllocTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_CR_CARD_PO
            rs = getBackupPkList(stmtSelect, CONTR_CR_CARD_PO_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_CR_CARD_POTMsg tmpDsContrCrCardPoTmsg = new DS_CONTR_CR_CARD_POTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrCrCardPoTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrCrCardPoTmsg.dsContrCrCardPoPk, rs.getBigDecimal(DS_CONTR_CR_CARD_PO_PK));
                if (!insertIntoBackupSourceTable(rs, new DS_CONTR_CR_CARD_POTMsg(), glblCmpyCd, tmpDsContrCrCardPoTmsg)) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_RNW_ESCL
            rs = getBackupPkList(stmtSelect, CONTR_RNW_ESCL_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_RNW_ESCLTMsg tmpDsContrRnwEsclTmsg = new DS_CONTR_RNW_ESCLTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrRnwEsclTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrRnwEsclTmsg.dsContrRnwEsclPk, rs.getBigDecimal(DS_CONTR_RNW_ESCL_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_RNW_ESCLTMsg(), glblCmpyCd, tmpDsContrRnwEsclTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_SEG_ALLOC
            rs = getBackupPkList(stmtSelect, CONTR_SEG_ALLOC_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                DS_CONTR_SEG_ALLOCTMsg tmpDsContrSegAllocTmsg = new DS_CONTR_SEG_ALLOCTMsg();
                ZYPEZDItemValueSetter.setValue(tmpDsContrSegAllocTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpDsContrSegAllocTmsg.dsContrSegAllocPk, rs.getBigDecimal(DS_CONTR_SEG_ALLOC_PK));
                if (!(insertIntoBackupSourceTable(rs, new DS_CONTR_SEG_ALLOCTMsg(), glblCmpyCd, tmpDsContrSegAllocTmsg))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // SVC_TERM_COND
            rs = getBackupPkList(stmtSelect, SVC_TERM_COND_BAK, contrBakSmryPk, glblCmpyCd);
            while (rs.next()) {
                SVC_TERM_CONDTMsg tmpSvcTermCondTmsg = new SVC_TERM_CONDTMsg();
                ZYPEZDItemValueSetter.setValue(tmpSvcTermCondTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tmpSvcTermCondTmsg.svcTermCondPk, rs.getBigDecimal(SVC_TERM_COND_PK));
                if (!(insertIntoBackupSourceTable(rs, new SVC_TERM_CONDTMsg(), glblCmpyCd, tmpSvcTermCondTmsg))) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        // DS_CONTR
        DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
        CONTR_BAKTMsg contrBakTmsg = new CONTR_BAKTMsg();
        // get backup source(for Ezin column)
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrPk, dsContrPk);
        dsContrTmsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTmsg);

        if (dsContrTmsg != null) {
            ZYPEZDItemValueSetter.setValue(contrBakTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrBakTmsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(contrBakTmsg.contrBakSmryPk, contrBakSmryPk);
            contrBakTmsg = (CONTR_BAKTMsg) EZDTBLAccessor.findByKey(contrBakTmsg);
            if (contrBakTmsg != null) {
                EZDMsg.copy(contrBakTmsg, null, dsContrTmsg, null);
                EZDTBLAccessor.update(dsContrTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTmsg.getReturnCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean executeBackupForDsContrPk(String glblCmpyCd, NSAL0300CMsg cMsg, BigDecimal contrBakSmrySq, Map<String, Object> resultMap) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        List<BigDecimal> removePkList = null;

        DS_CONTRTMsg dsContrTmsg = new DS_CONTRTMsg();
        CONTR_BAKTMsg contrBakTmsg = new CONTR_BAKTMsg();

        DS_CONTR_DTLTMsg dsContrDtlTmsg = new DS_CONTR_DTLTMsg();
        CONTR_DTL_BAKTMsg contrDtlBakTmsg = new CONTR_DTL_BAKTMsg();

        DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTmsg = new DS_CONTR_ADDL_CHRGTMsg();
        CONTR_ADDL_CHRG_BAKTMsg contrAddlChrgBakTmsg = new CONTR_ADDL_CHRG_BAKTMsg();

        DS_CONTR_BANK_ACCT_RELNTMsg dsContrBankAcctRelnTmsg = new DS_CONTR_BANK_ACCT_RELNTMsg();
        CONTR_BANK_ACCT_RELN_BAKTMsg contrBankAcctRelnBakTmsg = new CONTR_BANK_ACCT_RELN_BAKTMsg();

        DS_CONTR_BR_ALLOCTMsg dsContrBrAllocTmsg = new DS_CONTR_BR_ALLOCTMsg();
        CONTR_BR_ALLOC_BAKTMsg contrBrAllocBakTmsg = new CONTR_BR_ALLOC_BAKTMsg();

        DS_CONTR_CR_CARD_POTMsg dsContrCrCardPoTmsg = new DS_CONTR_CR_CARD_POTMsg();
        CONTR_CR_CARD_PO_BAKTMsg contrCrCardPoBakTmsg = new CONTR_CR_CARD_PO_BAKTMsg();

        DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTmsg = new DS_CONTR_RNW_ESCLTMsg();
        CONTR_RNW_ESCL_BAKTMsg contrRnwEsclBakTmsg = new CONTR_RNW_ESCL_BAKTMsg();

        DS_CONTR_SEG_ALLOCTMsg dsContrSegAllocTmsg = new DS_CONTR_SEG_ALLOCTMsg();
        CONTR_SEG_ALLOC_BAKTMsg contrSegAllocBakTmsg = new CONTR_SEG_ALLOC_BAKTMsg();

        SVC_TERM_CONDTMsg svcTermCondTmsg = new SVC_TERM_CONDTMsg();
        SVC_TERM_COND_BAKTMsg svcTermCondBakTmsg = new SVC_TERM_COND_BAKTMsg();

        CONTR_BAK_SMRYTMsg contrBakSmryTmsg = new CONTR_BAK_SMRYTMsg();
        // logical remove for backup record
        if (resultMap != null) {
            BigDecimal contrBakSmryPk = (BigDecimal) resultMap.get("CONTR_BAK_SMRY_PK");
            BigDecimal dsContrPk = (BigDecimal) resultMap.get("DS_CONTR_PK");

            // CONTR_BAK
            ZYPEZDItemValueSetter.setValue(contrBakTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrBakTmsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(contrBakTmsg.contrBakSmryPk, contrBakSmryPk);
            EZDTBLAccessor.logicalRemove(contrBakTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBakTmsg.getReturnCode())) {
                return false;
            }

            // CONTR_DTL_BAK
            removePkList = getRemovePkList(contrDtlBakTmsg.getTableName(), DS_CONTR_DTL_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrDtlBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrDtlBakTmsg.dsContrDtlPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrDtlBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrDtlBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrDtlBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_ADDL_CHRG
            removePkList = getRemovePkList(contrAddlChrgBakTmsg.getTableName(), DS_CONTR_ADDL_CHRG_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrAddlChrgBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrAddlChrgBakTmsg.dsContrAddlChrgPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrAddlChrgBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrAddlChrgBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrAddlChrgBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_BANK_ACCT_RELN
            removePkList = getRemovePkList(contrBankAcctRelnBakTmsg.getTableName(), DS_CONTR_BANK_ACCT_RELN_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrBankAcctRelnBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrBankAcctRelnBakTmsg.dsContrPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrBankAcctRelnBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrBankAcctRelnBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBankAcctRelnBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_BR_ALLOC
            removePkList = getRemovePkList(contrBrAllocBakTmsg.getTableName(), DS_CONTR_BR_ALLOC_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrBrAllocBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrBrAllocBakTmsg.dsContrBrAllocPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrBrAllocBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrBrAllocBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBrAllocBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_CR_CARD_PO
            removePkList = getRemovePkList(contrCrCardPoBakTmsg.getTableName(), DS_CONTR_CR_CARD_PO_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrCrCardPoBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrCrCardPoBakTmsg.dsContrCrCardPoPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrCrCardPoBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrCrCardPoBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrCrCardPoBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_RNW_ESCL
            removePkList = getRemovePkList(contrRnwEsclBakTmsg.getTableName(), DS_CONTR_RNW_ESCL_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrRnwEsclBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrRnwEsclBakTmsg.dsContrRnwEsclPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrRnwEsclBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrRnwEsclBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrRnwEsclBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_SEG_ALLOC
            removePkList = getRemovePkList(contrSegAllocBakTmsg.getTableName(), DS_CONTR_SEG_ALLOC_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrSegAllocBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrSegAllocBakTmsg.dsContrSegAllocPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrSegAllocBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrSegAllocBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrSegAllocBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // SVC_TERM_COND
            removePkList = getRemovePkList(svcTermCondBakTmsg.getTableName(), SVC_TERM_COND_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(svcTermCondBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcTermCondBakTmsg.svcTermCondPk, removePk);
                ZYPEZDItemValueSetter.setValue(svcTermCondBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(svcTermCondBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTermCondBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // CONTR_BAK_SMRY
            ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.contrBakSmryPk, contrBakSmryPk);
            EZDTBLAccessor.logicalRemove(contrBakSmryTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBakSmryTmsg.getReturnCode())) {
                return false;
            }
        }
        // create backup record
        // DS_CONTR
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsContrTmsg.dsContrPk, cMsg.dsContrPk.getValue());
        dsContrTmsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTmsg);

        EZDMsg.copy(dsContrTmsg, null, contrBakTmsg, null);
        ZYPEZDItemValueSetter.setValue(contrBakTmsg.contrBakSmryPk, contrBakSmrySq);
        EZDTBLAccessor.insert(contrBakTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBakTmsg.getReturnCode())) {
            return false;
        }

        try {
            // DS_CONTR_DTL
            rs = getBackupSourcePkList(stmtSelect, dsContrDtlTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_DTL_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_ADDL_CHRG
            rs = getBackupSourcePkList(stmtSelect, dsContrAddlChrgTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_ADDL_CHRG_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BANK_ACCT_RELN
            rs = getBackupSourcePkList(stmtSelect, dsContrBankAcctRelnTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_BANK_ACCT_RELN_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BR_ALLOC
            rs = getBackupSourcePkList(stmtSelect, dsContrBrAllocTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_BR_ALLOC_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_CR_CARD_PO
            rs = getBackupSourcePkList(stmtSelect, dsContrCrCardPoTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_CR_CARD_PO_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_RNW_ESCL
            rs = getBackupSourcePkList(stmtSelect, dsContrRnwEsclTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_RNW_ESCL_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_SEG_ALLOC
            rs = getBackupSourcePkList(stmtSelect, dsContrSegAllocTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_SEG_ALLOC_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // SVC_TERM_COND
            rs = getBackupSourcePkList(stmtSelect, svcTermCondTmsg.getTableName(), cMsg.dsContrPk.getValue(), glblCmpyCd);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new SVC_TERM_COND_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        // CONTR_BAK_SMRY
        ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.contrBakSmryPk, contrBakSmrySq);
        ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.dsContrPk, cMsg.dsContrPk.getValue());
        ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.bakCratTs, EZDDBCICarrier.getStartDateTime());
        ZYPEZDItemValueSetter.setValue(contrBakSmryTmsg.actvFlg, ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.insert(contrBakSmryTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBakSmryTmsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean executeBackupForDsContrDtlPk(String glblCmpyCd, NSAL0300CMsg cMsg, BigDecimal contrBakSmrySq, Map<String, Object> resultMap, BigDecimal dsContrPk) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        List<BigDecimal> removePkList = null;

        CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTmsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        CONTR_PHYS_BLLG_MTR_BAKTMsg contrPhysBllgMtrBakTmsg = new CONTR_PHYS_BLLG_MTR_BAKTMsg();

        DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTmsg = new DS_CONTR_BLLG_MTRTMsg();
        CONTR_BLLG_MTR_BAKTMsg contrBllgMtrTmsg = new CONTR_BLLG_MTR_BAKTMsg();

        CONTR_BLLG_SCHD_BAKTMsg contrBllgSchdBakTmsg = new CONTR_BLLG_SCHD_BAKTMsg();

        DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTmsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        CONTR_BLLG_SCHD_SMRY_BAKTMsg contrBllgSchdSmryBakMsg = new CONTR_BLLG_SCHD_SMRY_BAKTMsg();

        DS_CONTR_PRC_ALLOCTMsg dsContrPrcAllocTmsg = new DS_CONTR_PRC_ALLOCTMsg();
        CONTR_PRC_ALLOC_BAKTMsg contrPrcAllocBakTmsg = new CONTR_PRC_ALLOC_BAKTMsg();

        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTmsg = new DS_CONTR_PRC_EFFTMsg();
        CONTR_PRC_EFF_BAKTMsg contrPrcEffBakTmsg = new CONTR_PRC_EFF_BAKTMsg();

        DS_CONTR_SKIP_RECOVTMsg dsContrSkipRecovTmsg = new DS_CONTR_SKIP_RECOVTMsg();
        CONTR_SKIP_RECOV_BAKTMsg contrSkipRecovBakTmsg = new CONTR_SKIP_RECOV_BAKTMsg();

        DS_SUB_CONTRTMsg dsSubContrTmsg = new DS_SUB_CONTRTMsg();
        SUB_CONTR_BAKTMsg subContrBakTmsg = new SUB_CONTR_BAKTMsg();

        // logical remove for backup record
        if (resultMap != null) {
            BigDecimal contrBakSmryPk = (BigDecimal) resultMap.get("CONTR_BAK_SMRY_PK");

            // CONTR_PHYS_BLLG_MTR_RELN
            removePkList = getRemovePkList(contrPhysBllgMtrBakTmsg.getTableName(), CONTR_PHYS_BLLG_MTR_RELN_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrBakTmsg.contrPhysBllgMtrRelnPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrPhysBllgMtrBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrPhysBllgMtrBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrPhysBllgMtrBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_BLLG_MTR
            removePkList = getRemovePkList(contrBllgMtrTmsg.getTableName(), DS_CONTR_BLLG_MTR_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrBllgMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrBllgMtrTmsg.dsContrBllgMtrPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrBllgMtrTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrBllgMtrTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBllgMtrTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_BLLG_SCHD
            removePkList = getRemovePkList(contrBllgSchdBakTmsg.getTableName(), DS_CONTR_BLLG_SCHD_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrBllgSchdBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrBllgSchdBakTmsg.dsContrBllgSchdPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrBllgSchdBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrBllgSchdBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBllgSchdBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_BLLG_SCHD_SMRY
            removePkList = getRemovePkList(contrBllgSchdSmryBakMsg.getTableName(), DS_CONTR_BLLG_SCHD_SMRY_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrBllgSchdSmryBakMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrBllgSchdSmryBakMsg.dsContrBllgSchdSmryPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrBllgSchdSmryBakMsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrBllgSchdSmryBakMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBllgSchdSmryBakMsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_PRC_ALLOC
            removePkList = getRemovePkList(contrPrcAllocBakTmsg.getTableName(), DS_CONTR_PRC_ALLOC_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrPrcAllocBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrPrcAllocBakTmsg.dsContrPrcAllocPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrPrcAllocBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrPrcAllocBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrPrcAllocBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_PRC_EFF
            removePkList = getRemovePkList(contrPrcEffBakTmsg.getTableName(), DS_CONTR_PRC_EFF_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrPrcEffBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrPrcEffBakTmsg.dsContrPrcEffPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrPrcEffBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrPrcEffBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrPrcEffBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_SKIP_RECOV
            removePkList = getRemovePkList(contrSkipRecovBakTmsg.getTableName(), DS_CONTR_SKIP_RECOV_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrSkipRecovBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrSkipRecovBakTmsg.dsContrSkipRecovPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrSkipRecovBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrSkipRecovBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrSkipRecovBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_SUB_CONTR
            removePkList = getRemovePkList(subContrBakTmsg.getTableName(), DS_SUB_CONTR_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(subContrBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(subContrBakTmsg.dsSubContrPk, removePk);
                ZYPEZDItemValueSetter.setValue(subContrBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(subContrBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(subContrBakTmsg.getReturnCode())) {
                    return false;
                }
            }
        }
        // create backup record
        try {
            // CONTR_PHYS_BLLG_MTR_RELN
            rs = getBackupSourcePkList(stmtSelect, contrPhysBllgMtrRelnTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_PHYS_BLLG_MTR_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_MTR
            rs = getBackupSourcePkList(stmtSelect, dsContrBllgMtrTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_BLLG_MTR_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_SCHD
            NSAL0300Query.getInstance().backupDsCntrBllgSchd(glblCmpyCd, dsContrPk, contrBakSmrySq);
            // DS_CONTR_BLLG_SCHD_SMRY
            rs = getBackupSourcePkList(stmtSelect, dsContrBllgSchdSmryTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_BLLG_SCHD_SMRY_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_PRC_ALLOC
            rs = getBackupSourcePkList(stmtSelect, dsContrPrcAllocTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_PRC_ALLOC_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_PRC_EFF
            rs = getBackupSourcePkList(stmtSelect, dsContrPrcEffTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_PRC_EFF_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_SKIP_RECOV
            rs = getBackupSourcePkList(stmtSelect, dsContrSkipRecovTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_SKIP_RECOV_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_SUB_CONTR
            rs = getBackupSourcePkList(stmtSelect, dsSubContrTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_DTL_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new SUB_CONTR_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private boolean executeBackupForDsContrMtrPk(String glblCmpyCd, NSAL0300CMsg cMsg, BigDecimal contrBakSmrySq, Map<String, Object> resultMap, BigDecimal dsContrPk, List<BigDecimal> dsContrBllgMtrPkList) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        List<BigDecimal> removePkList = null;

        CONTR_XS_COPYTMsg contrXsCopyTmsg = new CONTR_XS_COPYTMsg();
        CONTR_XS_COPY_BAKTMsg contrXsCopyBakTmsg = new CONTR_XS_COPY_BAKTMsg();

        DS_CONTR_BLLG_SCHD_MTRTMsg dsContrBllgSchdMtrTmsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        CONTR_BLLG_SCHD_MTR_BAKTMsg contrBllgSchdMtrTmsg = new CONTR_BLLG_SCHD_MTR_BAKTMsg();

        DS_CONTR_PRC_EFF_MTRTMsg dsContrPrcEffMtrTmsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        CONTR_PRC_EFF_MTR_BAKTMsg contrPrcEffMtrTmsg = new CONTR_PRC_EFF_MTR_BAKTMsg();

        // logical remove for backup record
        if (resultMap != null) {
            BigDecimal contrBakSmryPk = (BigDecimal) resultMap.get("CONTR_BAK_SMRY_PK");

            // CONTR_XS_COPY_PK
            removePkList = getRemovePkList(contrXsCopyBakTmsg.getTableName(), CONTR_XS_COPY_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrXsCopyBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrXsCopyBakTmsg.contrXsCopyPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrXsCopyBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrXsCopyBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrXsCopyBakTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_BLLG_SCHD_MTR
            removePkList = getRemovePkList(contrBllgSchdMtrTmsg.getTableName(), DS_CONTR_BLLG_SCHD_MTR_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrBllgSchdMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrBllgSchdMtrTmsg.dsContrBllgSchdMtrPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrBllgSchdMtrTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrBllgSchdMtrTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrBllgSchdMtrTmsg.getReturnCode())) {
                    return false;
                }
            }

            // DS_CONTR_PRC_EFF_MTR
            removePkList = getRemovePkList(contrPrcEffMtrTmsg.getTableName(), DS_CONTR_PRC_EFF_MTR_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(contrPrcEffMtrTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(contrPrcEffMtrTmsg.dsContrPrcEffMtrPk, removePk);
                ZYPEZDItemValueSetter.setValue(contrPrcEffMtrTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(contrPrcEffMtrTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(contrPrcEffMtrTmsg.getReturnCode())) {
                    return false;
                }
            }
        }
        if (dsContrBllgMtrPkList == null || dsContrBllgMtrPkList.size() == 0) {
            return true;
        }
        // create backup record
        try {
            // CONTR_XS_COPY_PK
            rs = getBackupSourcePkList(stmtSelect, contrXsCopyTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_BLLG_MTR_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_XS_COPY_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_BLLG_SCHD_MTR
            rs = getBackupSourcePkList(stmtSelect, dsContrBllgSchdMtrTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_BLLG_MTR_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_BLLG_SCHD_MTR_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }

            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
            // DS_CONTR_PRC_EFF_MTR
            rs = getBackupSourcePkList(stmtSelect, dsContrPrcEffMtrTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_CONTR_BLLG_MTR_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new CONTR_PRC_EFF_MTR_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private boolean executeBackupForDsContrSubPk(String glblCmpyCd, NSAL0300CMsg cMsg, BigDecimal contrBakSmrySq, Map<String, Object> resultMap, BigDecimal dsContrPk, List<BigDecimal> dsSubContrPkList) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        List<BigDecimal> removePkList = null;

        DS_SUB_CONTR_MTRTMsg dsSubContrMtrTmsg = new DS_SUB_CONTR_MTRTMsg();
        SUB_CONTR_MTR_BAKTMsg subContrMtrBakTmsg = new SUB_CONTR_MTR_BAKTMsg();

        // logical remove for backup record
        if (resultMap != null) {
            BigDecimal contrBakSmryPk = (BigDecimal) resultMap.get("CONTR_BAK_SMRY_PK");

            // DS_SUB_CONTR_MTR
            removePkList = getRemovePkList(subContrMtrBakTmsg.getTableName(), DS_SUB_CONTR_MTR_PK, glblCmpyCd, contrBakSmryPk);
            for (BigDecimal removePk : removePkList) {
                ZYPEZDItemValueSetter.setValue(subContrMtrBakTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(subContrMtrBakTmsg.dsSubContrMtrPk, removePk);
                ZYPEZDItemValueSetter.setValue(subContrMtrBakTmsg.contrBakSmryPk, contrBakSmryPk);
                EZDTBLAccessor.logicalRemove(subContrMtrBakTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(subContrMtrBakTmsg.getReturnCode())) {
                    return false;
                }
            }

        }
        if (dsSubContrPkList == null || dsSubContrPkList.size() == 0) {
            return true;
        }
        // create backup record
        try {
            // CONTR_XS_COPY_PK
            rs = getBackupSourcePkList(stmtSelect, dsSubContrMtrTmsg.getTableName(), dsContrPk, glblCmpyCd, DS_SUB_CONTR_PK);
            while (rs.next()) {
                if (!(insertIntoBackupTable(rs, new SUB_CONTR_MTR_BAKTMsg(), glblCmpyCd, contrBakSmrySq))) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private List<BigDecimal> getRemovePkList(String tblName, String tblPk, String glblCmpyCd, BigDecimal contrBakSmryPk) {
        return NSAL0300Query.getInstance().getRemovePkList(tblName, tblPk, glblCmpyCd, contrBakSmryPk);
    }

    private ResultSet getBackupSourcePkList(PreparedStatement stmtSelect, String tblName, BigDecimal pkInfo, String glblCmpyCd) throws SQLException {
        return NSAL0300Query.getInstance().getBackupSourcePkList(stmtSelect, tblName, pkInfo, glblCmpyCd);
    }

    private ResultSet getBackupSourcePkList(PreparedStatement stmtSelect, String tblName, BigDecimal dsContrPk, String glblCmpyCd, String usingWhereColumn) throws SQLException {
        if (DS_CONTR_DTL_PK.equals(usingWhereColumn)) {
            return NSAL0300Query.getInstance().getBackupSourcePkListUsingDsContrDtl(stmtSelect, tblName, dsContrPk, glblCmpyCd);
        } else if (DS_CONTR_BLLG_MTR_PK.equals(usingWhereColumn)) {
            return NSAL0300Query.getInstance().getBackupSourcePkListUsingBllgMtr(stmtSelect, tblName, dsContrPk, glblCmpyCd);
        } else {
            return NSAL0300Query.getInstance().getBackupSourcePkListUsingDsSubContr(stmtSelect, tblName, dsContrPk, glblCmpyCd);
        }
    }

    private ResultSet getBackupPkList(PreparedStatement stmtSelect, String tblName, BigDecimal pkInfo, String glblCmpyCd) throws SQLException {
        return NSAL0300Query.getInstance().getBackupPkList(stmtSelect, tblName, pkInfo, glblCmpyCd);
    }

    private boolean insertIntoBackupTable(ResultSet rs, EZDTMsg bakTmsg, String glblCmpyCd, BigDecimal contrBakSmrySq) throws SQLException {
        ArrayList[] columnList = bakTmsg.getSelectColumnList();
        List<String> rsColNames = new ArrayList<String>();
        ResultSetMetaData rsm;
        String dbNm;
        String rsNm;

        rsm = rs.getMetaData();
        for (int i = 0; i < rsm.getColumnCount(); i++) {
            rsColNames.add(rsm.getColumnName(i + 1));
        }
        bakTmsg.setDBValue("glblCmpyCd", glblCmpyCd);
        bakTmsg.setDBValue("contrBakSmryPk", contrBakSmrySq);
        for (int idx = VAR_ITEM_START_POSN_FOR_BACKUP; idx < columnList[0].size(); idx++) {
            dbNm = columnList[0].get(idx).toString();
            rsNm = columnList[1].get(idx).toString();
            if (!rsColNames.contains(rsNm)) {
                continue;
            }
            if (bakTmsg.getAttr(dbNm).getType() == EZDItemAttrDefines.TYPE_SEISU_SYOSU) {
                bakTmsg.setDBValue(dbNm, rs.getBigDecimal(rsNm));
            } else {
                bakTmsg.setDBValue(dbNm, rs.getString(rsNm));
            }
        }
        EZDTBLAccessor.insert(bakTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(bakTmsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean insertIntoBackupSourceTable(ResultSet rs, EZDTMsg bakSocTmsg, String glblCmpyCd, EZDTMsg insTmsg) throws SQLException {
        ArrayList[] columnList = bakSocTmsg.getSelectColumnList();
        List<String> rsColNames = new ArrayList<String>();
        ResultSetMetaData rsm;
        String dbNm;
        String rsNm;

        insTmsg = EZDTBLAccessor.findByKey(insTmsg);
        rsm = rs.getMetaData();
        for (int i = 0; i < rsm.getColumnCount(); i++) {
            rsColNames.add(rsm.getColumnName(i + 1));
        }
        bakSocTmsg.setDBValue("glblCmpyCd", glblCmpyCd);
        for (int idx = VAR_ITEM_START_POSN_FOR_BACKUP_SOURCE; idx < columnList[0].size(); idx++) {
            dbNm = columnList[0].get(idx).toString();
            rsNm = columnList[1].get(idx).toString();
            if (!rsColNames.contains(rsNm)) {
                continue;
            }
            if (bakSocTmsg.getAttr(dbNm).getType() == EZDItemAttrDefines.TYPE_SEISU_SYOSU) {
                bakSocTmsg.setDBValue(dbNm, rs.getBigDecimal(rsNm));
            } else {
                bakSocTmsg.setDBValue(dbNm, rs.getString(rsNm));
            }
        }
        if (insTmsg == null) {
            EZDTBLAccessor.create(bakSocTmsg);
        } else {
            EZDTBLAccessor.update(bakSocTmsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(bakSocTmsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean insertIntoTrackingTable(NSAL0300CMsg cMsg, String glblCmpyCd) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        setValue(pMsg.glblCmpyCd, glblCmpyCd);
        setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);
        setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
        setValue(pMsg.dsContrPk, cMsg.dsContrPk.getValue());
        setValue(pMsg.stsMemoRsnCd, DS_CONTR_TRK_RSN.REVERT_CONTRACT);
        setValue(pMsg.stsMemoUpdPsnCd, cMsg.getUserID());

        new NSZC077001().execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
    private boolean removeStagingInfo(NSAL0300CMsg cMsg) {

        List<SVC_CONTR_BLLGTMsg> contrBllgList = new ArrayList<SVC_CONTR_BLLGTMsg>();
        List<SVC_CONTR_BASE_BLLGTMsg> contrBaseBllgList = new ArrayList<SVC_CONTR_BASE_BLLGTMsg>();
        List<SVC_CONTR_MTR_BLLGTMsg> contrMtrBllgList = new ArrayList<SVC_CONTR_MTR_BLLGTMsg>();
        List<SVC_CONTR_BLLG_ALLOCTMsg> contrBllgAllocList = new ArrayList<SVC_CONTR_BLLG_ALLOCTMsg>();
        List<SVC_CONTR_XS_MTR_BLLGTMsg> contrXsMtrBllgList = new ArrayList<SVC_CONTR_XS_MTR_BLLGTMsg>();
        List<SVC_CONTR_ADDL_CHRG_BLLGTMsg> contrAddlChrgBllgList = new ArrayList<SVC_CONTR_ADDL_CHRG_BLLGTMsg>();
        List<DS_CONTR_BLLG_SCHDTMsg> dsContrBllgSchdList = new ArrayList<DS_CONTR_BLLG_SCHDTMsg>();
        String glblCmpyCd = getGlobalCompanyCode();
        List<BigDecimal> dsContrDtlPkList = NSAL0300Query.getInstance().getDsContrDtlPkList(cMsg.dsContrPk.getValue(), glblCmpyCd);

        for (BigDecimal dsContrDtPkl : dsContrDtlPkList) {
            // get svcContrBllg
            S21SsmEZDResult svcContrBllgResult = NSAL0300Query.getInstance().getSvcContrBllg(dsContrDtPkl);
            if (svcContrBllgResult.isCodeNotFound()) {
                continue;
            }
            List<Map<String, Object>> stagingInfoList = (List<Map<String, Object>>) svcContrBllgResult.getResultObject();
            for (Map<String, Object> stagingInfo : stagingInfoList) {
                SVC_CONTR_BLLGTMsg svcContrBllgMsg = new SVC_CONTR_BLLGTMsg();
                setValue(svcContrBllgMsg.glblCmpyCd, getGlobalCompanyCode());
                setValue(svcContrBllgMsg.svcContrBllgPk, (BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                svcContrBllgMsg = (SVC_CONTR_BLLGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(svcContrBllgMsg);
                if (svcContrBllgMsg == null) {
                    continue;
                }
                contrBllgList.add(svcContrBllgMsg);

                // get svcContrBaseBllg
                SVC_CONTR_BASE_BLLGTMsgArray svcContrBaseBllgArray = NSAL0300Query.getInstance().getSvcContrBaseBllgForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrBaseBllgArray.length(); i++) {
                    contrBaseBllgList.add(svcContrBaseBllgArray.no(i));
                }

                // get svcContrMtrBllg
                SVC_CONTR_MTR_BLLGTMsgArray svcContrMtrBllgArray = NSAL0300Query.getInstance().getSvcContrMtrBllgForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrMtrBllgArray.length(); i++) {
                    contrMtrBllgList.add(svcContrMtrBllgArray.no(i));
                    // get svcContrXsMtrBllg
                    SVC_CONTR_XS_MTR_BLLGTMsgArray svcContrSxMtrBllgArray = NSAL0300Query.getInstance().getSvcContrXsMtrBllgForUpdateNoWait(svcContrMtrBllgArray.no(i).svcContrMtrBllgPk.getValue());
                    for (int j = 0; j < svcContrSxMtrBllgArray.length(); j++) {
                        contrXsMtrBllgList.add(svcContrSxMtrBllgArray.no(j));
                    }
                }
                // get svcContrBllgaAlloc
                SVC_CONTR_BLLG_ALLOCTMsgArray svcContrBllgAllocArray = NSAL0300Query.getInstance().getSvcContrBllgAllocForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrBllgAllocArray.length(); i++) {
                    contrBllgAllocList.add(svcContrBllgAllocArray.no(i));
                }

                // get svcContrAddlChrgBllg
                SVC_CONTR_ADDL_CHRG_BLLGTMsgArray svcContrAddlChrgBllgArray = NSAL0300Query.getInstance().getSvcContrAddlChrgBllgForUpdateNoWait((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"));
                for (int i = 0; i < svcContrAddlChrgBllgArray.length(); i++) {
                    contrAddlChrgBllgList.add(svcContrAddlChrgBllgArray.no(i));
                }
                // remove Other Record
                if (removeOtherRec((BigDecimal) stagingInfo.get("SVC_CONTR_BLLG_PK"))) {
                    return true;
                }
            }
        }
        List<BigDecimal> resestBllgSchdPkList = NSAL0300Query.getInstance().getResetBllgSchdPkList(glblCmpyCd, cMsg.dsContrPk.getValue());
        BigDecimal tmpDtlPk = null;
        BigDecimal tmpSvcMachPk = null;
        BigDecimal tmpPhysMtrPk = null;

        for (BigDecimal resestBllgSchdPk : resestBllgSchdPkList) {
            // return dsContrBllgSchd
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = NSAL0300Query.getInstance().getDsContrBllgSchdForUpdateNoWait(resestBllgSchdPk);
            if (dsContrBllgSchdTMsg != null && dsContrBllgSchdTMsg.invFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
                setValue(dsContrBllgSchdTMsg.bllgStageFlg, ZYPConstant.FLG_OFF_N);
                setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
                setValue(dsContrBllgSchdTMsg.bllgCalcFlg, ZYPConstant.FLG_OFF_N);
                if (ZYPConstant.FLG_ON_Y.equals(dsContrBllgSchdTMsg.usgChrgFlg.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrBllgSchdTMsg.dsContrDtlTpCd.getValue())) {
                    if (!hasValue(tmpDtlPk) || tmpDtlPk.compareTo(dsContrBllgSchdTMsg.dsContrDtlPk.getValue()) != 0) {
                        tmpDtlPk = dsContrBllgSchdTMsg.dsContrDtlPk.getValue();
                        Map<String, Object> resetMtrEntryINfo = NSAL0300Query.getInstance().getResetMtrEntryInfo(glblCmpyCd, tmpDtlPk);
                        if (resetMtrEntryINfo != null) {
                            tmpSvcMachPk = (BigDecimal) resetMtrEntryINfo.get("SVC_MACH_MSTR_PK");
                            tmpPhysMtrPk = (BigDecimal) resetMtrEntryINfo.get("SVC_PHYS_MTR_PK");
                        }
                    }
                    resetMtrEntry(glblCmpyCd, dsContrBllgSchdTMsg, tmpDtlPk, tmpSvcMachPk, tmpPhysMtrPk);
                }
                dsContrBllgSchdTMsg.readMtrCnt.clear();
                dsContrBllgSchdTMsg.bllgMtrCnt.clear();
                dsContrBllgSchdTMsg.mtrChrgDealAmt.clear();
                dsContrBllgSchdTMsg.mtrChrgFuncAmt.clear();
                dsContrBllgSchdTMsg.slsTaxRate.clear();
                dsContrBllgSchdTMsg.dealTaxAmt.clear();
                dsContrBllgSchdTMsg.funcTaxAmt.clear();

                dsContrBllgSchdList.add(dsContrBllgSchdTMsg);
            } else if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrBllgSchdTMsg.dsContrDtlTpCd.getValue())){
                setValue(dsContrBllgSchdTMsg.bllgStageFlg, ZYPConstant.FLG_ON_Y);
                setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
                setValue(dsContrBllgSchdTMsg.bllgCalcFlg, ZYPConstant.FLG_ON_Y);
                dsContrBllgSchdTMsg.readMtrCnt.clear();
                dsContrBllgSchdTMsg.bllgMtrCnt.clear();
                dsContrBllgSchdTMsg.mtrChrgDealAmt.clear();
                dsContrBllgSchdTMsg.mtrChrgFuncAmt.clear();
                dsContrBllgSchdTMsg.slsTaxRate.clear();
                dsContrBllgSchdTMsg.dealTaxAmt.clear();
                dsContrBllgSchdTMsg.funcTaxAmt.clear();

                dsContrBllgSchdList.add(dsContrBllgSchdTMsg);
            }
        }

        if (!contrBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrBllgList.toArray(new SVC_CONTR_BLLGTMsg[0]));
            if (updCnt != contrBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_BLLGTMsg().getTableName() });
                return true;
            }
        }
        if (!contrBaseBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrBaseBllgList.toArray(new SVC_CONTR_BASE_BLLGTMsg[0]));
            if (updCnt != contrBaseBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_BASE_BLLGTMsg().getTableName() });
                return true;
            }
        }
        if (!contrMtrBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrMtrBllgList.toArray(new SVC_CONTR_MTR_BLLGTMsg[0]));
            if (updCnt != contrMtrBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_MTR_BLLGTMsg().getTableName() });
                return true;
            }
        }
        if (!contrXsMtrBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrXsMtrBllgList.toArray(new SVC_CONTR_XS_MTR_BLLGTMsg[0]));
            if (updCnt != contrXsMtrBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_XS_MTR_BLLGTMsg().getTableName() });
                return true;
            }
        }
        if (!contrAddlChrgBllgList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.removeLogical(contrAddlChrgBllgList.toArray(new SVC_CONTR_ADDL_CHRG_BLLGTMsg[0]));
            if (updCnt != contrAddlChrgBllgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {new SVC_CONTR_ADDL_CHRG_BLLGTMsg().getTableName() });
                return true;
            }
        }
        if (!dsContrBllgSchdList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(dsContrBllgSchdList.toArray(new DS_CONTR_BLLG_SCHDTMsg[0]));
            if (updCnt != dsContrBllgSchdList.size()) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {new DS_CONTR_BLLG_SCHDTMsg().getTableName() });
                return true;
            }
        }
        return false;
    }

    private boolean resetMtrEntry(String glblCmpyCd, DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg, BigDecimal dsContrDtlPk, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk) {
        MtrWinInfo mtrWinInfo = new MtrWinInfo();
        mtrWinInfo.setGlblCmpyCd(glblCmpyCd);
        mtrWinInfo.setBllgFromDt(dsContrBllgSchdTMsg.bllgSchdFromDt.getValue());
        mtrWinInfo.setBllgThruDt(dsContrBllgSchdTMsg.bllgSchdThruDt.getValue());
        mtrWinInfo.setBaseDt(ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID));
        mtrWinInfo.setDsContrBllgSchdPk(dsContrBllgSchdTMsg.dsContrBllgSchdPk.getValue());
        NSXC001001GetMtrWinFromThruDt.getMtrWinByDate(mtrWinInfo);
        String fromDt = mtrWinInfo.getMtrWinFromDt();
        String thruDt = mtrWinInfo.getMtrWinThruDt();

        SVC_PHYS_MTR_READTMsg bllgMtrRead = NSXC003001SvcPhysMtrRead.getBillingMeterSvcPhysMtrRead(glblCmpyCd, dsContrDtlPk, svcMachMstrPk, svcPhysMtrPk, fromDt, thruDt);

        if (bllgMtrRead != null) {
            setValue(dsContrBllgSchdTMsg.mtrEntryCpltFlg, ZYPConstant.FLG_ON_Y);
            setValue(dsContrBllgSchdTMsg.svcPhysMtrReadGrpSq, bllgMtrRead.svcPhysMtrReadGrpSq);
        } else {
            setValue(dsContrBllgSchdTMsg.mtrEntryCpltFlg, ZYPConstant.FLG_OFF_N);
            dsContrBllgSchdTMsg.svcPhysMtrReadGrpSq.clear();
        }
        return false;
    }

    private boolean removeOtherRec(BigDecimal svcConrBllgPk) {
        String glblCmpyCd = getGlobalCompanyCode();
        //remove SVC_CONTR_BLLG_ALLOC
        List<BigDecimal> svcContrBllgAllocPkList = NSAL0300Query.getInstance().getSvcContrBllgAllocPk(glblCmpyCd, svcConrBllgPk);
        NSAL0300Query.getInstance().removeSvcContrBllgAlloc(glblCmpyCd, svcContrBllgAllocPkList);

        //remove AGGR_USG_RECAL
        List<BigDecimal> svcContrBllgGrpSqList = NSAL0300Query.getInstance().getSvcContrBllgGrpSq(glblCmpyCd, svcConrBllgPk);
        for (BigDecimal svcContrBllgGrpSq : svcContrBllgGrpSqList) {
            List<BigDecimal> aggrUsgRecalPkList = NSAL0300Query.getInstance().getAggrUsgRecalPk(glblCmpyCd, svcContrBllgGrpSq);

            for (BigDecimal aggrUsgRecalPk : aggrUsgRecalPkList) {
                List<BigDecimal> aggrUsgRecalDtlPkList = NSAL0300Query.getInstance().getAggrUsgRecalDtlPk(glblCmpyCd, aggrUsgRecalPk);

                for (BigDecimal aggUsgRecalDtlPk : aggrUsgRecalDtlPkList) {
                    List<BigDecimal> aggrUsgRecalXsMtrList = NSAL0300Query.getInstance().getAggrUsgRecalXsMtrPk(glblCmpyCd, aggUsgRecalDtlPk);
                    NSAL0300Query.getInstance().removeAggrUsgRecalXsMtr(glblCmpyCd, aggUsgRecalDtlPk, aggrUsgRecalXsMtrList);
                }
                NSAL0300Query.getInstance().removeAggrUsgRecalDtl(glblCmpyCd, aggrUsgRecalPk, aggrUsgRecalDtlPkList);
            }
            NSAL0300Query.getInstance().removeAggrUsgRecal(glblCmpyCd, aggrUsgRecalPkList);
        }

        return false;
    }
    // END 2024/03/22 Y.Tamai[QC#63463,ADD]
}
