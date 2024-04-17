/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300;

import static business.blap.NSAL0300.constant.NSAL0300Constant.ASTERISK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ASTERISK_4;
import static business.blap.NSAL0300.constant.NSAL0300Constant.BIZ_ID;
import static business.blap.NSAL0300.constant.NSAL0300Constant.CONFIG_NUMBER_CHECK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DATE_FORMAT_DATE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEC_000;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEC_999;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_BASE_BLLG_TMG_TP_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_DS_CONTR_DTL_TP_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_MTR_BLLG_TMG_TP_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DIV_STR;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_CONTAINS;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_EQUAL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_FROM_TO;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_GREATER_THAN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_LESS_THAN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_NOT_EQUAL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_CONFIG_NUMBER;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_ITEM_CODE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_LINE_STATUS;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_LOCATION;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_MODEL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_SEQUENCE_NUMBER;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_SERIAL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM_START_DATE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_ARROW;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_MACHINE_ALL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_MACHINE_GREEN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_MACHINE_RED;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_ARROW;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_MACHINE_ALL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_MACHINE_GREEN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_MACHINE_RED;
import static business.blap.NSAL0300.constant.NSAL0300Constant.INPUT_IS_INVALID;
import static business.blap.NSAL0300.constant.NSAL0300Constant.INPUT_IS_VALID;
import static business.blap.NSAL0300.constant.NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LAST_DAY_OF_A_MONTH;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LOB_OR_BR;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MACHINE_DETAILS;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAP_KEY_DS_CONTR_DTL_PK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAP_KEY_MDSE_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAP_KEY_SER_NUM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NAZM0081E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0034E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0045E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0072E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0081E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0128E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0368E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0478E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0609E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0765E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0766E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0767E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0768E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSZM0684E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NZZM0001W;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PERCENT;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PERIOD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.RANGE_CHECK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.REP_NAME;
import static business.blap.NSAL0300.constant.NSAL0300Constant.SIMPLE_SUBSTANCE_CHECK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.SLASH;
import static business.blap.NSAL0300.constant.NSAL0300Constant.TXT_HYPHEN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ZZM9000E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ZZM9010E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0300.common.NSAL0300CommonLogic;
import business.blap.NSAL0300.common.NSAL0300DisplayControllBean;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.file.NSAL0300F00FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/11/13   Hitachi         T.Kanasaka      Update          QC666
 * 2015/11/24   Hitachi         T.Kanasaka      Update          QC564
 * 2015/11/27   Hitachi         A.Kohinata      Update          QC1200
 * 2015/11/30   Hitachi         A.Kohinata      Update          QC842
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC1271
 * 2015/12/01   Hitachi         A.Kohinata      Update          QC1295
 * 2015/12/02   Hitachi         T.Kanasaka      Update          QC1374
 * 2015/12/07   Hitachi         A.Kohinata      Update          QC1471,QC1215
 * 2015/12/08   Hitachi         A.Kohinata      Update          QC1645
 * 2015/12/09   Hitachi         T.Kanasaka      Update          QC814,QC815
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC1757
 * 2015/12/16   Hitachi         T.Kanasaka      Update          QC2065
 * 2015/12/23   Hitachi         K.Yamada        Update          CSA QC#1824
 * 2016/01/07   Hitachi         T.Tomita        Update          CSA QC#1029
 * 2016/01/12   Hitachi         T.Tomita        Update          CSA QC#2686
 * 2016/01/21   Hitachi         T.Tomita        Update          CSA QC#2182
 * 2016/02/09   Hitachi         T.Kanasaka      Update          QC3273
 * 2016/02/09   Hitachi         T.Aoyagi        Update          QC4081
 * 2016/02/10   Hitachi         T.Kanasaka      Update          QC3278
 * 2016/02/10   Hitachi         T.Tomita        Update          QC3982
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC2681
 * 2016/02/16   Hitachi         T.Aoyagi        Update          QC2947
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3700
 * 2016/02/23   Hitachi         T.Kanasaka      Update          QC3885
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/02/24   Hitachi         A.Kohinata      Update          QC3697
 * 2016/02/26   Hitachi         T.Kanasaka      Update          QC4092
 * 2016/02/29   Hitachi         T.Kanasaka      Update          QC2185
 * 2016/03/02   Hitachi         T.Tomita        Update          QC#3048
 * 2016/03/04   Hitachi         T.Tomita        Update          QC#3048
 * 2016/03/15   Hitachi         T.Tomita        Update          QC#4962
 * 2016/03/18   Hitachi         T.Tomita        Update          QC#3278
 * 2016/03/18   Hitachi         T.Tomita        Update          QC#4085
 * 2016/04/07   Hitachi         M.Gotou         Update          QC#5312,5113
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886, QC#4668
 * 2016/05/09   Hitachi         T.Kanasaka      Update          QC#6798
 * 2016/05/17   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/05/27   Hitachi         Y.Takeno        Update          QC#447
 * 2016/06/21   Hitachi         M.Gotou         Update          QC#6923
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/19   Hitachi         A.Kohinata      Update          QC#11526
 * 2016/07/21   Hitachi         A.Kohinata      Update          QC#11696
 * 2016/07/21   Hitachi         A.Kohinata      Update          QC#11720
 * 2016/08/08   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/06   Hitachi         T.Kanasaka      Update          QC#14216
 * 2016/09/06   Hitachi         T.Kanasaka      Update          QC#14217
 * 2016/09/06   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/27   Hitachi         T.Tomita        Update          QC#11522
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/21   Hitachi         K.Kishimoto     Update          QC#15146
 * 2016/11/15   Hitachi         T.Kanasaka      Update          QC#15526
 * 2016/11/22   Hitachi         A.Kohinata      Update          QC#16114
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#16600
 * 2016/12/27   Hitachi         Y.Takeno        Update          QC#16782
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 * 2017/01/27   Hitachi         Y.Takeno        Update          QC#17278
 * 2017/02/02   Hitachi         N.Arai          Update          QC#17197
 * 2017/06/22   Hitachi         Y.Osawa         Update          QC#17496
 * 2017/07/26   Hitachi         M.Kidokoro      Update          QC#18122
 * 2017/07/27   Hitachi         K.Kim           Update          QC#16889
 * 2017/07/31   Hitachi         M.Kidokoro      Update          QC#20116
 * 2017/08/03   Hitachi         K.Kim           Update          QC#17496
 * 2017/08/24   Hitachi         K.Kojima        Update          QC#20743
 * 2017/08/29   Hitachi         K.Kojima        Update          QC#20057
 * 2017/12/21   Hitachi         T.Tomita        Update          QC#18779
 * 2018/01/10   Hitachi         T.Tomita        Update          QC#18552
 * 2018/02/19   Hitachi         M.Kidokoro      Update          QC#23629
 * 2018/05/15   Hitachi         K.Kitachi       Update          QC#24265
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/11   Hitachi         K.Kojima        Update          QC#24243
 * 2018/07/23   Hitachi         K.Kim           Update          QC#26831
 * 2018/07/30   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2018/08/03   Hitachi         K.Kitachi       Update          QC#18070
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 * 2018/08/30   Hitachi         A.Kohinata      Update          QC#27961
 * 2018/10/15   Hitachi         K.Kitachi       Update          QC#28713
 * 2018/11/06   Hitachi         S.Kitamura      Update          QC#28868
 * 2018/11/07   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/02/15   Hitachi         T.Tomita        Update          QC#30295
 * 2019/05/13   Hitachi         K.Fujimoto      Update          QC#31137/50058
 * 2019/11/27   Hitachi         E.Kameishi      Update          QC#54594
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/10/03   Hitachi         N.Takatsu       Update          QC#60186
 * 2022/10/05   Hitachi         N.Takatsu       Update          QC#60072
 * 2022/10/07   Hitachi         N.Takatsu       Update          QC#60071
 * 2023/01/31   Hitachi         R.Takau         Update          QC#55645
 * 2024/02/26   Hitachi         K.Ogasawara     Update          QC#63550
 * 2024/03/22   Hitachi         Y.Tamai         Update          QC#63463
 *  *</pre>
 */
public class NSAL0300BL02 extends S21BusinessHandler {

    @Override
    protected boolean checkInput(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        NSAL0300CMsg cMsg = (NSAL0300CMsg) ezdCMsg;
        NSAL0300SMsg sMsg = (NSAL0300SMsg) ezdSMsg;
        // START 2017/08/29 K.Kojima [QC#20057,ADD]
        cMsg.xxScrItem500Txt.clear();
        // END 2017/08/29 K.Kojima [QC#20057,ADD]
        NSAL0300CommonLogic.copyTableA(cMsg, sMsg, cMsg.xxPageShowFromNum_A.getValueInt() - 1);
        // add start 2016/03/18 CSA Defect#4085
        String screenAplId = cMsg.getScreenAplID();
        if ("NSAL0300Scrn00_ChangeBasePrcUpRatio".equals(screenAplId)) {
            return checkInput_NSAL0300Scrn00_ChangeBasePrcUpRatio(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_ChangeMtrPrcUpRatio".equals(screenAplId)) {
            return checkInput_NSAL0300Scrn00_ChangeMtrPrcUpRatio(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_ChangeUplftBasePrcUpRatio".equals(screenAplId)) {
            return checkInput_NSAL0300Scrn00_ChangeUplftBasePrcUpRatio(cMsg, sMsg);
        } else if ("NSAL0300Scrn00_ChangeUplftMtrPrcUpRatio".equals(screenAplId)) {
            return checkInput_NSAL0300Scrn00_ChangeUplftMtrPrcUpRatio(cMsg, sMsg);
        // START 2016/09/27 T.Tomita [QC#11522, ADD]
        } else if ("NSAL0300Scrn00_OpenWin_BillingCounters".equals(screenAplId)) {
            return checkInput_NSAL0300Scrn00_OpenWin_BillingCounters(cMsg, sMsg);
        // END 2016/09/27 T.Tomita [QC#11522, ADD]
        }
        // add end 2016/03/18 CSA Defect#4085
        return INPUT_IS_VALID;
    }

    // add start 2016/03/18 CSA Defect#4085
    private boolean checkInput_NSAL0300Scrn00_ChangeBasePrcUpRatio(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return chkAutoRnw(cMsg, sMsg);
    }

    private boolean checkInput_NSAL0300Scrn00_ChangeMtrPrcUpRatio(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return chkAutoRnw(cMsg, sMsg);
    }

    private boolean checkInput_NSAL0300Scrn00_ChangeUplftBasePrcUpRatio(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return chkContrUplft(cMsg, sMsg);
    }

    private boolean checkInput_NSAL0300Scrn00_ChangeUplftMtrPrcUpRatio(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return chkContrUplft(cMsg, sMsg);
    }

    // START 2016/09/27 T.Tomita [QC#11522, ADD]
    private boolean checkInput_NSAL0300Scrn00_OpenWin_BillingCounters(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return chkExstUsgChrg(cMsg, sMsg);
    }
    // END 2016/09/27 T.Tomita [QC#11522, ADD]

    private boolean chkAutoRnw(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        boolean valid = INPUT_IS_VALID;
        String glblCmpyCd = getGlobalCompanyCode();
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
        return valid;
    }

    private boolean chkContrUplft(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        boolean valid = INPUT_IS_VALID;
        String glblCmpyCd = getGlobalCompanyCode();
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
        return valid;
    }
    // add end 2016/03/18 CSA Defect#4085

    // START 2016/09/27 T.Tomita [QC#11522, ADD]
    private boolean chkExstUsgChrg(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
            return INPUT_IS_VALID;
        }
        String glblCmpyCd = getGlobalCompanyCode();
        DS_CONTR_DTL_TPTMsg tMsg;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            tMsg = new DS_CONTR_DTL_TPTMsg();
            setValue(tMsg.glblCmpyCd, glblCmpyCd);
            setValue(tMsg.dsContrDtlTpCd, sMsg.A.no(i).dsContrDtlTpCd_A.getValue());
            tMsg = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(tMsg);
            if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.mainUnitLineFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(tMsg.usgChrgFlg.getValue())) {
                return INPUT_IS_VALID;
            }
        }
        cMsg.setMessageInfo(NSAM0609E);
        return INPUT_IS_INVALID;
    }
    // END 2016/09/27 T.Tomita [QC#11522, ADD]

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        // START 2016/02/15 T.Aoyagi [QC2681, ADD]
        NSAL0300CMsg cMsg = (NSAL0300CMsg) ezdCMsg;
        NSAL0300SMsg sMsg = (NSAL0300SMsg) ezdSMsg;
//        // Common Column Order Text
//        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        // END 2016/02/15 T.Aoyagi [QC2681, ADD]
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            // START 2016/02/15 T.Aoyagi [QC2681, DEL]
//            NSAL0300CMsg cMsg = (NSAL0300CMsg) ezdCMsg;
//            NSAL0300SMsg sMsg = (NSAL0300SMsg) ezdSMsg;
            // END 2016/02/15 T.Aoyagi [QC2681, DEL]
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0300_INIT".equals(screenAplId)) {
                doProcess_NSAL0300_INIT(cMsg, sMsg);
//                ZYPGUITableColumn.getColData(cMsg, sMsg);
                // START 2016/02/15 T.Aoyagi [QC2681, ADD]
//                xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
                // END 2016/02/15 T.Aoyagi [QC2681, ADD]
            } else if ("NSAL0300_NMAL6000".equals(screenAplId)) {
                doProcess_NSAL0300_NMAL6000(cMsg, sMsg);
            } else if ("NSAL0300_NMAL6050".equals(screenAplId)) {
                doProcess_NSAL0300_NMAL6050(cMsg, sMsg);
            } else if ("NSAL0300_NMAL6760".equals(screenAplId)) {
                doProcess_NSAL0300_NMAL6760(cMsg, sMsg);
            } else if ("NSAL0300_NMAL6770".equals(screenAplId)) {
                doProcess_NSAL0300_NMAL6770(cMsg, sMsg);
            } else if ("NSAL0300_NMAL6780".equals(screenAplId)) {
                doProcess_NSAL0300_NMAL6780(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0110".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0110(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0310".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0310(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0320".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0320(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0330".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0330(cMsg, sMsg);
            // Add Start 2018/01/18 QC#18552
            } else if ("NSAL0300_NSAL0350".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0350(cMsg, sMsg);
            // Add End 2018/01/18 QC#18552
            } else if ("NSAL0300_NSAL0360".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0360(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0380".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0380(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0390".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0390(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0400".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0400(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0410".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0410(cMsg, sMsg);
            // START 2017/01/24 N.Arai [QC#17228, MOD]
            // START 2016/01/21 T.Tomita [QC#2182, DEL]
            } else if ("NSAL0300_NSAL0420".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0420(cMsg, sMsg);
            // END 2016/01/21 T.Tomita [QC#2182, DEL]
            // END 2017/01/24 N.Arai [QC#17228, MOD]
            } else if ("NSAL0300_NSAL0440".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0440(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0450".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0450(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0460".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0460(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0470".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0470(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0500".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0500(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0550".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0550(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0560".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0560(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0570".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0570(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0580".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0580(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0590".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0590(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0600".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0600(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0610".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0610(cMsg, sMsg);
            // START 2016/01/12 T.Tomita [QC2686, MOD]
//            } else if ("NSAL0300_NSAL0660".equals(screenAplId)) {
//                doProcess_NSAL0300_NSAL0660(cMsg, sMsg);
            } else if ("NSAL0300_NSBL0160".equals(screenAplId)) {
                doProcess_NSAL0300_NSBL0160(cMsg, sMsg);
            // END 2016/01/12 T.Tomita [QC2686, MOD]
            } else if ("NSAL0300_NSAL0690".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0690(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0710".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0710(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0720".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0720(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0730".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0730(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0740".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0740(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0750".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0750(cMsg, sMsg);
            } else if ("NSAL0300_NSAL0760".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL0760(cMsg, sMsg);
            // START 2016/02/24 [QC3697, ADD]
            } else if ("NSAL0300_NSAL1260".equals(screenAplId)) {
                doProcess_NSAL0300_NSAL1260(cMsg, sMsg);
            // END 2016/02/24 [QC3697, ADD]
            } else if ("NSAL0300_NWAL1130".equals(screenAplId)) {
                doProcess_NSAL0300_NWAL1130(cMsg, sMsg);
            } else if ("NSAL0300_NWAL2010".equals(screenAplId)) {
                doProcess_NSAL0300_NWAL2010(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Add_Detail".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Add_Detail(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Add_Price".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Add_Price(cMsg, sMsg);
            // START 2016/02/10 T.Kanasaka [QC3278, ADD]
            } else if ("NSAL0300Scrn00_ChangeDetailEffectiveFromDateA".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDetailEffectiveFromDateA(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeDetailEffectiveFromDateB".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDetailEffectiveFromDateB(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeDetailEffectiveThruDateA".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDetailEffectiveThruDateA(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeDetailEffectiveThruDateB".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDetailEffectiveThruDateB(cMsg, sMsg);
            // END 2016/02/10 T.Kanasaka [QC3278, ADD]
            // START 2016/02/10 T.Tomita [QC3982, ADD]
            } else if ("NSAL0300Scrn00_ChangeDsAcctNum".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDsAcctNum(cMsg, sMsg);
            // END 2016/02/10 T.Tomita [QC3982, ADD]
            // START 2016/02/16 T.Aoyagi [QC2947, ADD]
            } else if ("NSAL0300Scrn00_ChangeBillToLoc".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBillToLoc(cMsg, sMsg);
            // END 2016/02/16 T.Aoyagi [QC2947, ADD]
            // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            } else if ("NSAL0300Scrn00_OpenWin_SpecialInstruction".equals(screenAplId)) {
            } else if ("NSAL0300Scrn00_OpenWin_SpecialInstruction_Base".equals(screenAplId)) {
            } else if ("NSAL0300Scrn00_OpenWin_SpecialInstruction_Meter".equals(screenAplId)) {
            // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            } else if ("NSAL0300Scrn00_ChangeDurationDate".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDurationDate(cMsg, sMsg);
            // add start 2016/07/01 CSA Defect#11261
            } else if ("NSAL0300Scrn00_ChangeServiceProgram".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeServiceProgram(cMsg, sMsg);
            // add end 2016/07/01 CSA Defect#11261
            } else if ("NSAL0300Scrn00_ChangeDurationPeriod".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDurationPeriod(cMsg, sMsg);
            // START 2016/02/09 T.Kanasaka [QC3273, ADD]
            } else if ("NSAL0300Scrn00_ChangeFreeCopyCnt".equals(screenAplId)) {
            // START 2018/02/19 M.Kidokoro [QC#23629, DEL]
//                doProcess_NSAL0300Scrn00_ChangeFreeCopyCnt(cMsg, sMsg);
            // END 2018/02/19 M.Kidokoro [QC#23629, DEL]
            // END 2016/02/09 T.Kanasaka [QC3273, ADD]
            // START 2016/02/26 T.Kanasaka [QC4092, ADD]
            } else if ("NSAL0300Scrn00_ChangeSvcContrBrCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeSvcContrBrCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeContrAdminPsnCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeContrAdminPsnCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeTocCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeTocCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangePmtTermCashDiscCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangePmtTermCashDiscCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeLeaseCmpyCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeLeaseCmpyCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeSerNum".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeSerNum(cMsg, sMsg);
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            } else if ("NSAL0300Scrn00_ChangeShipToCustCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeShipToCustCd(cMsg, sMsg);
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            } else if ("NSAL0300Scrn00_ChangeBaseBillToCustCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBaseBillToCustCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeBllgMtrBillToCustCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBllgMtrBillToCustCd(cMsg, sMsg);
            // END 2016/02/26 T.Kanasaka [QC4092, ADD]
            // START 2016/05/09 T.Kanasaka [QC#6798, ADD]
            } else if ("NSAL0300Scrn00_ChangeContrAutoRnwTpCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeContrAutoRnwTpCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeContrUplftTpCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeContrUplftTpCd(cMsg, sMsg);
            // END 2016/05/09 T.Kanasaka [QC#6798, ADD]
            // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
            } else if ("NSAL0300Scrn00_ChangeBaseBllgCycleCdA".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBaseBllgCycleCdA(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeBaseBllgCycleCdB".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBaseBllgCycleCdB(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeBllgMtrBllgCycleCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBllgMtrBllgCycleCd(cMsg, sMsg);
            // END 2016/05/17 T.Kanasaka [QC#2184, ADD]
            // START 2017/01/27 [QC#17278, ADD]
            } else if ("NSAL0300Scrn00_ChangeReportGrp".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeReportGrp(cMsg, sMsg);
            // END   2017/01/27 [QC#17278, ADD]
            } else if ("NSAL0300Scrn00_CMN_Clear".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_Reset".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_Return".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Return(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_Save".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Save(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_Submit".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Delete_Detail".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Delete_Detail(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Delete_Price".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Delete_Price(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Download".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Download(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenForUpdate".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenForUpdate(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_DisplayAccessory".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_DisplayAccessory(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Add_Detail".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Add_Detail(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_AdditionalCharge".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_AdditionalCharge(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_BillingCounters".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_BillingCounters(cMsg, sMsg);
            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            } else if ("NSAL0300Scrn00_OpenWin_ShipTo".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_ShipTo(cMsg, sMsg);
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
            } else if ("NSAL0300Scrn00_OpenWin_BillTo_Base".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_BillTo_Base(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_BillTo_Meter".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_BillTo_Meter(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_BillTo_Usage".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_BillTo_Usage(cMsg, sMsg);
            // START 2016/03/02 T.Tomita [QC#3048, DEL]
//            } else if ("NSAL0300Scrn00_OpenWin_BillToContact".equals(screenAplId)) {
//                doProcess_NSAL0300Scrn00_OpenWin_BillToContact(cMsg, sMsg);
            // END 2016/03/02 T.Tomita [QC#3048, DEL]
            // START 2016/03/04 T.Tomita [QC#3048, DEL]
//            } else if ("NSAL0300Scrn00_OpenWin_Contact_Base".equals(screenAplId)) {
//                doProcess_NSAL0300Scrn00_OpenWin_Contact_Base(cMsg, sMsg);
//            } else if ("NSAL0300Scrn00_OpenWin_Contact_Meter".equals(screenAplId)) {
//                doProcess_NSAL0300Scrn00_OpenWin_Contact_Meter(cMsg, sMsg);
            // END 2016/03/04 T.Tomita [QC#3048, DEL]
            } else if ("NSAL0300Scrn00_OpenWin_ContractNum".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_ContractNum(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_CreditCardPo".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_CreditCardPo(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_LeaseCompany".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_LeaseCompany(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_PaymentTerm".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_PaymentTerm(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Schedule_Base".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Schedule_Base(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Schedule_Usage".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Schedule_Usage(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_SellTo".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_SellTo(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Serial".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Serial(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_UpliftDetail".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_UpliftDetail(cMsg, sMsg);
            // Add Start 2018/01/10 QC#18552
            } else if ("NSAL0300Scrn00_OpenWin_Escalation".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Escalation(cMsg, sMsg);
            // Add End 2018/01/10 QC#18552
            } else if ("NSAL0300Scrn00_OpenWin_Terminate".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Terminate(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Renew".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Renew(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_StatusChange".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_StatusChange(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_CreditCard".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_CreditCard(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_SubContract".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_SubContract(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_PricingEffectivity_Base".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_PricingEffectivity_Base(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_PricingEffectivity_Meter".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_PricingEffectivity_Meter(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_AddNotes".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_AddNotes(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_Terms".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_Terms(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_OpenWin_CompleteContract".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_OpenWin_CompleteContract(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Go".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Go(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_PageJump".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_PageJump(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_PageNext".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_PagePrev".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_Branch".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_Branch(cMsg, sMsg);
            // START 2016/01/21 T.Tomita [QC#2182, ADD]
            } else if ("NSAL0300Scrn00_Search_Rep".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_Rep(cMsg, sMsg);
            // END 2016/01/21 T.Tomita [QC#2182, ADD]
            } else if ("NSAL0300Scrn00_Search_BillTo".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_BillTo(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_BillTo_Base".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_BillTo_Base(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_BillTo_Usage".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_BillTo_Usage(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_BillTo_Meter".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_BillTo_Meter(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_LeaseCompany".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_LeaseCompany(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_Customer".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_Customer(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Search_Serial".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Search_Serial(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_Upload".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Upload(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_TBLColumnSort".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_ColSave".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_ColSave(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_CMN_ColClear".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_CMN_ColClear(cMsg, sMsg);
            // add start 2016/03/18 CSA Defect#4085
            } else if ("NSAL0300Scrn00_ChangeBasePrcUpRatio".equals(screenAplId)) {
                return;
            } else if ("NSAL0300Scrn00_ChangeMtrPrcUpRatio".equals(screenAplId)) {
                return;
            } else if ("NSAL0300Scrn00_ChangeUplftBasePrcUpRatio".equals(screenAplId)) {
                return;
            } else if ("NSAL0300Scrn00_ChangeUplftMtrPrcUpRatio".equals(screenAplId)) {
                return;
            // add end 2016/03/18 CSA Defect#4085
            } else if ("NSAL0300Scrn00_DownloadMachine".equals(screenAplId)) {
             // START 2017/02/02 N.Arai [QC#17197, MOD]
                doProcess_NSAL0300Scrn00_DownloadMachine(cMsg, sMsg);
             // END 2017/02/02 N.Arai [QC#17197, MOD]
            } else if ("NSAL0300Scrn00_Filter".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Filter(cMsg, sMsg);
                return;
            // Add Start 2017/12/21 QC#18779
            } else if ("NSAL0300Scrn00_ChangeBaseBllgTmgCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBaseBllgTmgCd(cMsg, sMsg);
            // Add End 2017/12/21 QC#18779
            // START 2022/03/22 [QC#59683, ADD]
            } else if ("NSAL0300Scrn00_ChangeDsContrCatgCd".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeDsContrCatgCd(cMsg, sMsg);
            } else if ("NSAL0300Scrn00_ChangeBillTogether".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ChangeBillTogether(cMsg, sMsg);
            // END   2022/03/22 [QC#59683, ADD]
            // START 2022/10/05 N.Takatsu[QC#60072, ADD]
            } else if ("NSAL0300Scrn00_ExpandMachineAll".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_ExpandMachineAll(cMsg, sMsg);
            // END 2022/10/05 N.Takatsu[QC#60072, ADD]
            // START 2023/01/31 R.Takau [QC#55645,ADD]
            }else if ("NSAL0300_NFCL3170".equals(screenAplId)) {
                doProcess_NSAL0300_NFCL3170(cMsg, sMsg);
            // END 2023/01/31 R.Takau [QC#55645,ADD]
            // START 2024/03/22 Y.Tamai [QC#63463,ADD]
            } else if ("NSAL0300Scrn00_Revert".equals(screenAplId)) {
                doProcess_NSAL0300Scrn00_Revert(cMsg, sMsg);
            // END 2024/03/22 Y.Tamai [QC#63463,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            // START 2016/02/15 T.Aoyagi [QC2681, ADD]
            // Set Common Column Order Text of CMsg
//            ZYPEZDItemValueSetter.setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);
            // END 2016/02/15 T.Aoyagi [QC2681, ADD]
            // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            NSAL0300CommonLogic.setSpclInstnActivateFlg(cMsg);
            // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NSAL0300_INIT(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // add start 2018/08/30 QC#27961
        NSAL0300CommonLogic.resetSpclInstnInfo(cMsg);
        // add end 2018/08/30 QC#27961
        NSAL0300CommonLogic.resetParameter(cMsg, sMsg);
        if (ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            // START 2018/11/06 S.Kitamura [QC#28868,ADD]
            outputInitLog(cMsg);
            // END 2018/11/06 S.Kitamura [QC#28868,ADD]
            // START 2016/09/06 T.Kanasaka [QC#14216, MOD]
            search(cMsg, sMsg, new NSAL0300DisplayControllBean());
            NSAL0300CommonLogic.initDisplay(cMsg);
            // END 2016/09/06 T.Kanasaka [QC#14216, MOD]
        }
        // START 2016/02/18 T.Aoyagi [QC3700, ADD]
//        NSAL0300CommonLogic.setDefaultSummaryDetailMode(cMsg);
        // END 2016/02/18 T.Aoyagi [QC3700, ADD]
    }

    private void doProcess_NSAL0300_NMAL6000(NSAL0300CMsg msg, NSAL0300SMsg msg2) {
    }

    private void doProcess_NSAL0300_NMAL6050(NSAL0300CMsg msg, NSAL0300SMsg msg2) {
    }

    private void doProcess_NSAL0300_NSAL0110(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // START 2016/09/06 T.Kanasaka [QC#14216, MOD]
        if (ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            // add start 2018/08/30 QC#27961
            NSAL0300CommonLogic.resetSpclInstnInfo(cMsg);
            // add end 2018/08/30 QC#27961
            search(cMsg, sMsg, new NSAL0300DisplayControllBean());
            NSAL0300CommonLogic.initDisplay(cMsg);
        }
        // END 2016/09/06 T.Kanasaka [QC#14216, MOD]
    }

    private void doProcess_NSAL0300_NSAL0310(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int newIdxA = 0;
        if (cMsg.A.getValidCount() != 0) {
            newIdxA = NSAL0300CommonLogic.convertPageIndexToRowIndexA(cMsg, sMsg, cMsg.A.getValidCount() - 1);
            newIdxA++;
        }

        // mod start 2016/03/15 CSA Defect#4962
        String dsContrDtlTpCd;
        for (int p = 0; p < cMsg.P.getValidCount(); p++) {
            dsContrDtlTpCd = DEF_DS_CONTR_DTL_TP_CD;
            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.P.no(p).bllgFlg_P.getValue())) {
                dsContrDtlTpCd = DS_CONTR_DTL_TP.BASE_ONLY;
            }
            // Warning Flag
            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
            // START 2016/02/24 [QC3697, MOD]
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), dsContrDtlTpCd, cMsg.P.no(p).svcMachMstrPk_P.getValue(), cMsg.P.no(p).contrEffFromDt_P.getValue(), cMsg.P.no(p).contrEffThruDt_P
//                    .getValue(), cMsg.baseBllgCycleCd.getValue(), DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, null, null);
            NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), dsContrDtlTpCd, cMsg.P.no(p).svcMachMstrPk_P.getValue(), cMsg.P.no(p).contrEffFromDt_P.getValue(), cMsg.P.no(p).contrEffThruDt_P
                  .getValue(), cMsg.baseBllgCycleCd.getValue(), DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, null, null, cMsg.P.no(p).prntDsContrDtlPk_P.getValue(), cMsg.P.no(p).dsContrDtlPk_P1.getValue());
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
            // END 2016/02/24 [QC3697, MOD]
        }
        // mod end 2016/03/15 CSA Defect#4962

        if (NSAL0300CommonLogic.hasErrorOrWarn(cMsg)) {
            return;
        }

        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, newIdxA);
    }

    private void doProcess_NSAL0300_NSAL0320(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0330(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    // Add Start 2018/01/18 QC#18552
    private void doProcess_NSAL0300_NSAL0350(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }
    // Add End 2018/01/18 QC#18552

    private void doProcess_NSAL0300_NSAL0360(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0380(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0390(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0410(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NWAL1130(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // Mod Start 2018/08/20 QC#26946
        if ("OpenWin_Serial".equals(cMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.serNum)) {
                searchSerial(cMsg, sMsg);
            }
        } else if ("OpenWin_ServiceProgram".equals(cMsg.xxScrEventNm.getValue())) {
            String glblCmpyCd = getGlobalCompanyCode();
            NSAL0300CommonLogic.checkSvcPgmMdse(cMsg, glblCmpyCd);
        }
        // Mod End 2018/08/20 QC#26946
    }

    private void doProcess_NSAL0300_NMAL6760(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // START 2016/01/07 T.Tomita [QC#1029, ADD]
        // START 2017/06/22 [QC#17496, MOD]
        // if ("OpenWin_Customer".equals(cMsg.xxScrEventNm.getValue())) {
        if ("OpenWin_Customer".equals(cMsg.xxScrEventNm.getValue()) || "OpenWin_BillToLoc".equals(cMsg.xxScrEventNm.getValue())) {
        // END   2017/06/22 [QC#17496, MOD]
        // END 2016/01/07 T.Tomita [QC#1029, ADD]
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum)) {
                NSAL0300Query query = NSAL0300Query.getInstance();
                SELL_TO_CUSTTMsg sellToCustTMsg = query.getSellToCust(getGlobalCompanyCode(), cMsg.dsAcctNum.getValue());
                if (sellToCustTMsg != null) {
                    if (ZYPCommonFunc.hasValue(sellToCustTMsg.defBaseCycleCd)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.baseBllgCycleCd, sellToCustTMsg.defBaseCycleCd);
                    }
                    if (ZYPCommonFunc.hasValue(sellToCustTMsg.defUsgCycleCd)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgCycleCd, sellToCustTMsg.defUsgCycleCd);
                    }
                    // START 2016/02/10 T.Tomita [QC3982, MOD]
                    if (!ZYPCommonFunc.hasValue(cMsg.dsContrCatgCd) || !DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg_UT, sellToCustTMsg.dsBillTgtrFlg.getValue());
                        // START 2022/03/22 [QC#59683, ADD]
                        ZYPEZDItemValueSetter.setValue(cMsg.dsInvTgtrTpCd, sellToCustTMsg.dsInvTgtrTpCd.getValue());
                        // END   2022/03/22 [QC#59683, ADD]
                    }
                    // END 2016/02/10 T.Tomita [QC3982, MOD]
                    if (ZYPCommonFunc.hasValue(sellToCustTMsg.defBaseTpCd)) {
                        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgTmgCd_B, sellToCustTMsg.defBaseTpCd);
                        }
                    // START 2016/12/27 [QC#16782, ADD]
                    } else {
                        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgTmgCd_B, DEF_BASE_BLLG_TMG_TP_CD);
                        }
                    }
                    // END   2016/12/27 [QC#16782, ADD]
                    if (ZYPCommonFunc.hasValue(sellToCustTMsg.defUsgTpCd)) {
                        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrBllgTmgCd_B, sellToCustTMsg.defUsgTpCd);
                        }
                    // START 2016/12/27 [QC#16782, ADD]
                    } else {
                        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrBllgTmgCd_B, DEF_MTR_BLLG_TMG_TP_CD);
                        }
                    }
                    // END   2016/12/27 [QC#16782, ADD]
                }
            }
        // START 2016/01/07 T.Tomita [QC#1029, ADD]
        // START 2017/06/22 [QC#17496, DEL]
        // } else if ("OpenWin_BillToLoc".equals(cMsg.xxScrEventNm.getValue())) {
        // END  2017/06/22 [QC#17496, DEL]
            if (ZYPCommonFunc.hasValue((cMsg.altPayerCustCd))) {
                doProcess_NSAL0300Scrn00_Search_BillTo(cMsg, sMsg);
            }
        // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
        } else if ("OpenWin_ShipTo".equals(cMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
                int idx = cMsg.xxRowNum.getValueInt();
                if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).shipToCustCd_B))) {
                    searchShipTo(cMsg, sMsg);
                }
            }
        // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
        } else if ("OpenWin_BillTo_Base".equals(cMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
                int idx = cMsg.xxRowNum.getValueInt();
                if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).baseBillToCustCd_B))) {
                    doProcess_NSAL0300Scrn00_Search_BillTo_Base(cMsg, sMsg);
                }
            }
        } else if ("OpenWin_BillTo_Meter".equals(cMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
                int idx = cMsg.xxRowNum.getValueInt();
                if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).bllgMtrBillToCustCd_B))) {
                    doProcess_NSAL0300Scrn00_Search_BillTo_Meter(cMsg, sMsg);
                }
            }
        } else if ("OpenWin_LeaseCompany".equals(cMsg.xxScrEventNm.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
                doProcess_NSAL0300Scrn00_Search_LeaseCompany(cMsg, sMsg);
            }
        }
        // END 2016/01/07 T.Tomita [QC#1029, ADD]
    }

    private void doProcess_NSAL0300_NMAL6770(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300_NMAL6780(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300_NSAL0400(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    // START 2016/01/21 T.Tomita [QC#2182, DEL]
//    private void doProcess_NSAL0300_NSAL0420(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        NSAL0300CommonLogic.searchBranch(getGlobalCompanyCode(), cMsg, sMsg);
//    }
    // END 2016/01/21 T.Tomita [QC#2182, DEL]

    private void doProcess_NSAL0300_NSAL0440(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0450(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0460(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0470(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0500(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0550(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0560(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0570(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0580(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0590(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300_NSAL0600(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0610(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    // START 2016/01/12 T.Tomita [QC#2686, MOD]
//    private void doProcess_NSAL0300_NSAL0660(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        search(cMsg, sMsg);
//    }
    private void doProcess_NSAL0300_NSBL0160(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }
    // END 2016/01/12 T.Tomita [QC#2686, MOD]

    private void doProcess_NSAL0300_NSAL0690(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0710(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0720(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0730(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0740(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0750(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300_NSAL0760(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    // START 2016/02/24 [QC3697, ADD]
    private void doProcess_NSAL0300_NSAL1260(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int newIdxA = 0;
        if (cMsg.A.getValidCount() != 0) {
            newIdxA = NSAL0300CommonLogic.convertPageIndexToRowIndexA(cMsg, sMsg, cMsg.A.getValidCount() - 1);
            newIdxA++;
        }

        String baseBllgCycleCd = null;
        for (int q = 0; q < cMsg.Q.getValidCount(); q++) {
            if (DS_CONTR_CATG.REGULAR.equals(cMsg.dsContrCatgCd.getValue())) {
                baseBllgCycleCd = cMsg.Q.no(q).bllgCycleCd_Q.getValue();
            } else {
                baseBllgCycleCd = cMsg.baseBllgCycleCd.getValue();
            }

            // Warning Flag
            ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
            // START 2022/01/21 R.Cabral [QC#59502, MOD]
//            NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), DEF_DS_CONTR_DTL_TP_CD, cMsg.Q.no(q).svcMachMstrPk_Q.getValue(), cMsg.Q.no(q).contrEffFromDt_Q.getValue(), cMsg.Q.no(q).contrEffThruDt_Q
//                    .getValue(), baseBllgCycleCd, DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, cMsg.Q.no(q).basePrcDealAmt_Q.getValue(), cMsg.Q.no(q).mtrReadMethCd_Q.getValue());
            NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), DEF_DS_CONTR_DTL_TP_CD, cMsg.Q.no(q).svcMachMstrPk_Q.getValue(), cMsg.Q.no(q).contrEffFromDt_Q.getValue(), cMsg.Q.no(q).contrEffThruDt_Q
                    .getValue(), baseBllgCycleCd, DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, cMsg.Q.no(q).basePrcDealAmt_Q.getValue(), cMsg.Q.no(q).mtrReadMethCd_Q.getValue(), null, null);
            // END   2022/01/21 R.Cabral [QC#59502, MOD]
        }

        if (NSAL0300CommonLogic.hasErrorOrWarn(cMsg)) {
            return;
        }

        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, newIdxA);
    }
    // END 2016/02/24 [QC3697, ADD]

    private void doProcess_NSAL0300_NWAL2010(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.dsCrCardPk)) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            DS_CR_CARDTMsg getDsCrCardTMsg = query.getDsCrCard(getGlobalCompanyCode(), cMsg.dsCrCardPk.getValue());
            if (getDsCrCardTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.crCardCustRefNum, getDsCrCardTMsg.crCardCustRefNum);
                // START 2019/11/27 E.Kameishi [QC#54594, ADD]
                if (hasValue(getDsCrCardTMsg.crCardLastDigitNum)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem10Txt, ASTERISK_4.concat(getDsCrCardTMsg.crCardLastDigitNum.getValue()));
                }
                // END 2019/11/27 E.Kameishi [QC#54594, ADD]
                String crCardExprYrMth = getDsCrCardTMsg.crCardExprYrMth.getValue();
                ZYPEZDItemValueSetter.setValue(cMsg.xxMthDt_CC, crCardExprYrMth.substring(4, 6));
                ZYPEZDItemValueSetter.setValue(cMsg.xxYrDt_CC, crCardExprYrMth.substring(0, 4));
            }
        }
    }

    public static boolean machExst(NSAL0300_ASMsgArray aSMsgArray, BigDecimal svcMachMstrPk) {
        for (int s = 0; s < aSMsgArray.getValidCount(); s++) {
            BigDecimal sPk = aSMsgArray.no(s).svcMachMstrPk_A.getValue();
            if (ZYPCommonFunc.hasValue(sPk) && svcMachMstrPk.compareTo(sPk) == 0) {
                return true;
            }
        }
        return false;
    }

    private void doProcess_NSAL0300Scrn00_Add_Detail(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        // END 2016/12/19 K.Kojima [QC#16600,ADD]
        doProcess_NSAL0300Scrn00_ChangeSerNum(cMsg, sMsg);
        if (NSAL0300CommonLogic.hasError(cMsg)) {
            return;
        } else if (ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk_M.no(1))) {
            // START 2016/12/19 K.Kojima [QC#16600,MOD]
            // cMsg.serNum.setErrorInfo(1, NSAM0035E, new String[] {"Serial#" });
            // cMsg.setMessageInfo(NSAM0035E, new String[] {"Serial#" });
            ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
            // END 2016/12/19 K.Kojima [QC#16600,MOD]
            return;
        }

        int newIdxA = 0;
        if (cMsg.A.getValidCount() != 0) {
            newIdxA = NSAL0300CommonLogic.convertPageIndexToRowIndexA(cMsg, sMsg, cMsg.A.getValidCount() - 1);
            newIdxA++;
        }

//        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk.getValue();
        BigDecimal svcMachMstrPk = cMsg.svcMachMstrPk_M.no(0).getValue();
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk, svcMachMstrPk);

        // START 2016/05/27 [QC#447, ADD]
        if (!checkEOL(cMsg)) {
            return;
        }
        // END   2016/05/27 [QC#447, ADD]

        // START 2016/02/24 [QC3697, MOD]
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), DEF_DS_CONTR_DTL_TP_CD, cMsg.svcMachMstrPk.getValue(), cMsg.contrVrsnEffFromDt.getValue(), cMsg.contrVrsnEffThruDt.getValue(), cMsg.baseBllgCycleCd
//                .getValue(), DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, null, null);
        NSAL0300CommonLogic.addMachine(cMsg, sMsg.A, cMsg.B, cMsg.dsContrCatgCd.getValue(), DEF_DS_CONTR_DTL_TP_CD, cMsg.svcMachMstrPk.getValue(), cMsg.contrVrsnEffFromDt.getValue(), cMsg.contrVrsnEffThruDt.getValue(), cMsg.baseBllgCycleCd
                .getValue(), DEF_BASE_BLLG_TMG_TP_CD, cMsg.mtrBllgCycleCd.getValue(), DEF_MTR_BLLG_TMG_TP_CD, null, null, null, null);
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
        // END 2016/02/24 [QC3697, MOD]

        if (NSAL0300CommonLogic.hasErrorOrWarn(cMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, newIdxA);
        }

        cMsg.serNum.clear();
        cMsg.svcMachMstrPk.clear();
        cMsg.svcMachMstrPk_M.clear();
        cMsg.mdseCd_M.clear();
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_AdditionalCharge(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_Add_Price(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int addBtnIdx = cMsg.xxRowNum_BA.getValueInt();
        BigDecimal selectDsContrBllgMtrPk = cMsg.B.no(addBtnIdx).dsContrBllgMtrPk_B.getValue();
        String selectBllgMtrLbCd = cMsg.B.no(addBtnIdx).bllgMtrLbCd_B.getValue();
        int selAddIdx = 0;

        if (ZYPCommonFunc.hasValue(selectDsContrBllgMtrPk)) {
            if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                BigDecimal preDsContrBllgMtrPk = INVLD_DS_CONTR_BLLG_MTR_PK;
                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                    BigDecimal dsContrBllgMtrPk = cMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
                    String bllgMtrLbCd = cMsg.B.no(i).bllgMtrLbCd_B.getValue();
                    if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && preDsContrBllgMtrPk.compareTo(dsContrBllgMtrPk) != 0 && selectBllgMtrLbCd.equals(bllgMtrLbCd)) {
                        int newVldCnt = cMsg.B.getValidCount() + 1;
                        cMsg.B.setValidCount(newVldCnt);
                        int addIdx = i;
                        for (; addIdx < cMsg.B.getValidCount(); addIdx++) {
                            BigDecimal cmpDsContrBllgMtrPk = cMsg.B.no(addIdx).dsContrBllgMtrPk_B.getValue();
                            if (!ZYPCommonFunc.hasValue(cmpDsContrBllgMtrPk) || dsContrBllgMtrPk.compareTo(cmpDsContrBllgMtrPk) != 0) {
                                break;
                            }
                        }

                        if (selectDsContrBllgMtrPk.compareTo(dsContrBllgMtrPk) == 0) {
                            selAddIdx = addIdx;
                        }

                        if (addIdx > 0) {
                            for (int j = newVldCnt - 1; j > addIdx; j--) {
                                EZDMsg.copy(cMsg.B.no(j - 1), null, cMsg.B.no(j), null);
                            }
                            EZDMsg.copy(cMsg.B.no(addIdx - 1), null, cMsg.B.no(addIdx), null);
                            cMsg.B.no(addIdx).contrXsCopyPk_B.clear();
                            cMsg.B.no(addIdx).xsMtrAmtRate_B.clear();
                            cMsg.B.no(addIdx).xsMtrCopyQty_B.clear();
                        }

                        preDsContrBllgMtrPk = dsContrBllgMtrPk;
                    }
                }
                // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
                // NSAL0300CommonLogic.setCopyQtyForAggr(cMsg);
                NSAL0300CommonLogic.setCopyQtyForAggr(cMsg, getGlobalCompanyCode());
                // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.xxRowNum_BA, BigDecimal.valueOf(selAddIdx));
            } else {
                int newVldCnt = cMsg.B.getValidCount() + 1;
                cMsg.B.setValidCount(newVldCnt);
                int addIdx = addBtnIdx;
                for (; addIdx < cMsg.B.getValidCount(); addIdx++) {
                    BigDecimal cmpDsContrBllgMtrPk = cMsg.B.no(addIdx).dsContrBllgMtrPk_B.getValue();
                    if (!ZYPCommonFunc.hasValue(cmpDsContrBllgMtrPk) || selectDsContrBllgMtrPk.compareTo(cmpDsContrBllgMtrPk) != 0) {
                        break;
                    }
                }
                if (addIdx > addBtnIdx) {
                    for (int i = newVldCnt - 1; i > addIdx; i--) {
                        EZDMsg.copy(cMsg.B.no(i - 1), null, cMsg.B.no(i), null);
                    }
                    EZDMsg.copy(cMsg.B.no(addBtnIdx), null, cMsg.B.no(addIdx), null);
                    cMsg.B.no(addIdx).contrXsCopyPk_B.clear();
                    cMsg.B.no(addIdx).xsMtrCopyQty_B.clear();
                    cMsg.B.no(addIdx).xsMtrAmtRate_B.clear();
                }
                ZYPEZDItemValueSetter.setValue(cMsg.xxRowNum_BA, BigDecimal.valueOf(addIdx));
            }
        }
    }

    // START 2016/02/10 T.Kanasaka [QC3278, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeDetailEffectiveFromDateA(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_A.getValueInt();
        String fromDt = cMsg.A.no(idx).contrEffFromDt_A.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(idx).dsContrDtlPk_A.getValue();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, fromDt);
                break;
            }
        }
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffFromDt_B, fromDt);
                break;
            }
        }

        String dsContrStsCd = cMsg.dsContrStsCd.getValue();
        // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd)) {
        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd) && !DS_CONTR_STS.ENTERED.equals(dsContrStsCd)) {
        // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
            return;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.contrVrsnEffFromDt) || (ZYPCommonFunc.hasValue(fromDt) && fromDt.compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0)) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt, fromDt);
            doProcess_NSAL0300Scrn00_ChangeDurationDate(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeDetailEffectiveFromDateB(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_B.getValueInt();
        String fromDt = cMsg.B.no(idx).contrEffFromDt_B.getValue();
        BigDecimal dsContrDtlPk = cMsg.B.no(idx).dsContrDtlPk_B.getValue();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, fromDt);
                break;
            }
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrEffFromDt_A, fromDt);
                break;
            }
        }

        // START 2018/08/03 K.Kitachi [QC#18070, ADD]
        String dsContrDtlTpCd = cMsg.B.no(idx).dsContrDtlTpCd_B.getValue();
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (ZYPDateUtil.compare(sMsg.A.no(i).contrEffFromDt_A.getValue(), fromDt) < 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, fromDt);
                }
            }
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPDateUtil.compare(cMsg.A.no(i).contrEffFromDt_A.getValue(), fromDt) < 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrEffFromDt_A, fromDt);
                }
            }
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (ZYPDateUtil.compare(cMsg.B.no(i).contrEffFromDt_B.getValue(), fromDt) < 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffFromDt_B, fromDt);
                }
            }
        }
        // END 2018/08/03 K.Kitachi [QC#18070, ADD]

        String dsContrStsCd = cMsg.dsContrStsCd.getValue();
        // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd)) {
        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd) && !DS_CONTR_STS.ENTERED.equals(dsContrStsCd)) {
        // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
            return;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.contrVrsnEffFromDt) || (ZYPCommonFunc.hasValue(fromDt) && fromDt.compareTo(cMsg.contrVrsnEffFromDt.getValue()) < 0)) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt, fromDt);
            doProcess_NSAL0300Scrn00_ChangeDurationDate(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeDetailEffectiveThruDateA(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_A.getValueInt();
        String thruDt = cMsg.A.no(idx).contrEffThruDt_A.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(idx).dsContrDtlPk_A.getValue();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, thruDt);
                break;
            }
        }
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffThruDt_B, thruDt);
                break;
            }
        }

        String dsContrStsCd = cMsg.dsContrStsCd.getValue();
        // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd)) {
        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd) && !DS_CONTR_STS.ENTERED.equals(dsContrStsCd)) {
        // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
            return;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.contrVrsnEffThruDt) || (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0)) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt, thruDt);
            doProcess_NSAL0300Scrn00_ChangeDurationDate(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeDetailEffectiveThruDateB(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_B.getValueInt();
        String thruDt = cMsg.B.no(idx).contrEffThruDt_B.getValue();
        BigDecimal dsContrDtlPk = cMsg.B.no(idx).dsContrDtlPk_B.getValue();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, thruDt);
                break;
            }
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrEffThruDt_A, thruDt);
                break;
            }
        }

        // START 2018/08/03 K.Kitachi [QC#18070, ADD]
        String dsContrDtlTpCd = cMsg.B.no(idx).dsContrDtlTpCd_B.getValue();
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (ZYPDateUtil.compare(sMsg.A.no(i).contrEffThruDt_A.getValue(), thruDt) > 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, thruDt);
                }
            }
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPDateUtil.compare(cMsg.A.no(i).contrEffThruDt_A.getValue(), thruDt) > 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrEffThruDt_A, thruDt);
                }
            }
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (ZYPDateUtil.compare(cMsg.B.no(i).contrEffThruDt_B.getValue(), thruDt) > 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffThruDt_B, thruDt);
                }
            }
        }
        // END 2018/08/03 K.Kitachi [QC#18070, ADD]

        String dsContrStsCd = cMsg.dsContrStsCd.getValue();
        // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd)) {
        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd) && !DS_CONTR_STS.ENTERED.equals(dsContrStsCd)) {
        // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
            return;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.contrVrsnEffThruDt) || (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(cMsg.contrVrsnEffThruDt.getValue()) > 0)) {
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt, thruDt);
            doProcess_NSAL0300Scrn00_ChangeDurationDate(cMsg, sMsg);
        }
    }
    // END 2016/02/10 T.Kanasaka [QC3278, ADD]

    // START 2016/02/10 T.Tomita [QC3982, ADD]
    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    // START 2016/04/26 T.Tomita [QC#3886, MOD]
    private void doProcess_NSAL0300Scrn00_ChangeDsAcctNum(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.dsAcctNum)) {
            // START  2017/06/22 [QC#17496, ADD]
            if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm)) {
                cMsg.dsAcctNm.clear();
            }
            // END    2017/06/22 [QC#17496, ADD]
            return;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findSellToCust(getGlobalCompanyCode(), cMsg.dsAcctNum.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.dsAcctNum.setErrorInfo(1, NSAM0072E, new String[] {"Customer" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            // START  2017/08/03 K.Kim [QC#17496, ADD]
            cMsg.altPayerCustCd.clear();
            cMsg.billToLocNm.clear();
            cMsg.pmtTermCashDiscCd.clear();
            cMsg.pmtTermCashDiscDescTxt.clear();
            // END  2017/08/03 K.Kim [QC#17496, ADD]

            // START  2017/06/22 [QC#17496, ADD]
            NSAL0300CommonLogic.setDefSellToCust(cMsg, rsltMap);
            if (!ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
                NSAL0300CommonLogic.setDefBillToCust(getGlobalCompanyCode(), cMsg);
            }
            // END    2017/06/22 [QC#17496, ADD]

// START 2017/06/22 [QC#17496, DEL]
//
//            String dsAcctNm = (String) rsltMap.get("DS_ACCT_NM");
//            setValue(cMsg.dsAcctNm, dsAcctNm);
//
//            String defBaseCycleCd = (String) rsltMap.get("DEF_BASE_CYCLE_CD");
//            if (ZYPCommonFunc.hasValue(defBaseCycleCd)) {
//                setValue(cMsg.baseBllgCycleCd, defBaseCycleCd);
//            }
//
//            String defUsgCycleCd = (String) rsltMap.get("DEF_USG_CYCLE_CD");
//            if (ZYPCommonFunc.hasValue(defUsgCycleCd)) {
//                setValue(cMsg.mtrBllgCycleCd, defUsgCycleCd);
//            }
//
//            String dsBillTgtrFlg = (String) rsltMap.get("DS_BILL_TGTR_FLG");
//            if (!ZYPCommonFunc.hasValue(cMsg.dsContrCatgCd) || !DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
//                setValue(cMsg.xxSelFlg_UT, dsBillTgtrFlg);
//            }
//
//            String defBaseTpCd = (String) rsltMap.get("DEF_BASE_TP_CD");
//            if (ZYPCommonFunc.hasValue(defBaseTpCd)) {
//                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                    setValue(cMsg.B.no(i).baseBllgTmgCd_B, defBaseTpCd);
//                }
//            // START 2016/12/27 [QC#16782, ADD]
//            } else {
//                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                    setValue(cMsg.B.no(i).baseBllgTmgCd_B, DEF_BASE_BLLG_TMG_TP_CD);
//                }
//            }
//            // END   2016/12/27 [QC#16782, ADD]
//
//            String defUsgTpCd = (String) rsltMap.get("DEF_USG_TP_CD");
//            if (ZYPCommonFunc.hasValue(defUsgTpCd)) {
//                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                    setValue(cMsg.B.no(i).mtrBllgTmgCd_B, defUsgTpCd);
//                }
//            // START 2016/12/27 [QC#16782, ADD]
//            } else {
//                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                    setValue(cMsg.B.no(i).mtrBllgTmgCd_B, DEF_MTR_BLLG_TMG_TP_CD);
//                }
//            }
//            // END   2016/12/27 [QC#16782, ADD]
// END   2017/06/22 [QC#17496, DEL]

            // START 2016/09/06 T.Kanasaka [QC#14217, MOD]
//            if (ZYPCommonFunc.hasValue(cMsg.svcLineBizCd) && ZYPCommonFunc.hasValue(cMsg.svcContrBrCd)) {
            if (ZYPCommonFunc.hasValue(cMsg.svcLineBizCd) && ZYPCommonFunc.hasValue(cMsg.svcContrBrCd) && !ZYPCommonFunc.hasValue(cMsg.xxPsnNm)) {
            // END 2016/09/06 T.Kanasaka [QC#14217, MOD]
                NSAL0300CommonLogic.searchDefaultRep(getGlobalCompanyCode(), cMsg, sMsg);
            }
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
        // START 2017/08/03 K.Kim [QC#17496, ADD]
        NSAL0300CommonLogic.setDefaultPmtTerm(getGlobalCompanyCode(), cMsg);
        // END 2017/08/03 K.Kim [QC#17496, ADD]
    }
    // END 2016/04/26 T.Tomita [QC#3886, MOD]
    // END 2016/02/26 T.Kanasaka [QC4092, MOD]
    // END 2016/02/10 T.Tomita [QC3982, ADD]

    // START 2016/02/16 T.Aoyagi [QC2947, ADD]
    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    // START 2016/04/26 T.Tomita [QC#3886, MOD]
    private void doProcess_NSAL0300Scrn00_ChangeBillToLoc(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        NSAL0300CommonLogic.setDefaultPmtTerm(getGlobalCompanyCode(), cMsg);
//        doProcess_NSAL0300Scrn00_Search_BillTo(cMsg, sMsg);
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
            // START 2017/06/22 [QC#17496, ADD]
            if (ZYPCommonFunc.hasValue(cMsg.billToLocNm)) {
                cMsg.billToLocNm.clear();
            }
            // END   2017/06/22 [QC#17496, ADD]
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findBillToCust(glblCmpyCd, cMsg.altPayerCustCd.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.altPayerCustCd.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            String locNm = (String) rsltMap.get("LOC_NM");
            setValue(cMsg.billToLocNm, locNm);
            // START 2017/06/22 [QC#17496, ADD]
            rslt = NSAL0300Query.getInstance().getSellToCustFromBillToCustCd(glblCmpyCd, cMsg.altPayerCustCd.getValue());
            if (rslt != null && rslt.getQueryResultCount() == 1) {
                rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);
                NSAL0300CommonLogic.setDefSellToCust(cMsg, rsltMap);

                if (ZYPCommonFunc.hasValue(cMsg.svcLineBizCd) && ZYPCommonFunc.hasValue(cMsg.svcContrBrCd) && !ZYPCommonFunc.hasValue(cMsg.xxPsnNm)) {
                    NSAL0300CommonLogic.searchDefaultRep(getGlobalCompanyCode(), cMsg, sMsg);
                }
            }
            // END   2017/06/22 [QC#17496, ADD]
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
        NSAL0300CommonLogic.setDefaultPmtTerm(glblCmpyCd, cMsg);
    }
    // END 2016/04/26 T.Tomita [QC#3886, MOD]
    // END 2016/02/26 T.Kanasaka [QC4092, MOD]
    // END 2016/02/16 T.Aoyagi [QC2947, ADD]

    private void doProcess_NSAL0300Scrn00_ChangeDurationDate(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // add start 2015/12/23 CSA Defect#1824
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(cMsg.contrVrsnEffFromDt.getValue());
        param.setContrEffThruDt(cMsg.contrVrsnEffThruDt.getValue());

        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcDuration();
        ZYPEZDItemValueSetter.setValue(cMsg.contrDurnAot, param.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(cMsg.contrDurnAot_BK, param.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleUomCd, param.getCycleUomCd());
        // add end 2015/12/23 CSA Defect#1824
        // add start 2016/03/18 CSA Defect#3278
        copyDtlEffDate(cMsg, sMsg);
        // add end 2016/03/18 CSA Defect#3278
    }

    // add start 2016/07/01 CSA Defect#11261
    private void doProcess_NSAL0300Scrn00_ChangeServiceProgram(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        // Mod Start 2018/01/12 QC#18552
        String svcPgmMdseCd = null;
        String mdseDescShortTxt = null;
        int selectIdx = -1;

        if (ZYPCommonFunc.hasValue(cMsg.xxCellIdx)) {
            selectIdx = cMsg.xxCellIdx.getValueInt();
        }

        if (selectIdx > -1) {
            svcPgmMdseCd = cMsg.B.no(selectIdx).svcPgmMdseCd_B.getValue();
        } else {
            mdseDescShortTxt = cMsg.mdseDescShortTxt_SP.getValue();
        }
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().getPgmMdseCdPullDownList(getGlobalCompanyCode(), svcPgmMdseCd, mdseDescShortTxt);

        if (rslt.isCodeNotFound()) {
            if (selectIdx > -1) {
                cMsg.B.no(selectIdx).svcPgmMdseCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Service Program" });
                cMsg.B.no(selectIdx).mdseDescShortTxt_B.clear();
            } else {
                cMsg.mdseDescShortTxt_SP.setErrorInfo(1, NSAM0072E, new String[] {"Service Program" });
            }
            return;
        }
        // Mod End 2018/01/12 QC#18552

        List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();

        if (rsltList.size() > 1) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
            return;
        }
        svcPgmMdseCd = (String) rsltList.get(0).get("MDSE_CD");
        mdseDescShortTxt = (String) rsltList.get(0).get("MDSE_DESC_SHORT_TXT");

        if (selectIdx > -1) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(selectIdx).svcPgmMdseCd_B, svcPgmMdseCd);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(selectIdx).mdseDescShortTxt_B, mdseDescShortTxt);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.svcPgmMdseCd, svcPgmMdseCd);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_SP, mdseDescShortTxt);
        }
        // Add Start 2018/08/20 QC#26946
        NSAL0300CommonLogic.checkSvcPgmMdse(cMsg, getGlobalCompanyCode());
        // Add End 2018/08/20 QC#26946
    }
    // add end 2016/07/01 CSA Defect#11261

    // add start 2016/03/18 CSA Defect#3278
    private void copyDtlEffDate(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String dsContrStsCd = cMsg.dsContrStsCd.getValue();
        // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd)) {
        if (!DS_CONTR_STS.DRAFT.equals(dsContrStsCd) && !DS_CONTR_STS.ENTERED.equals(dsContrStsCd)) {
        // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
            return;
        }

        String fromDt = cMsg.contrVrsnEffFromDt.getValue();
        String thruDt = cMsg.contrVrsnEffThruDt.getValue();

        if (sMsg.A.getValidCount() == 0) {
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrEffFromDt_A, fromDt);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).contrEffThruDt_A, thruDt);
            }
        } else {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, fromDt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, thruDt);
            }
            doProcess_NSAL0300Scrn00_PageJump(cMsg, sMsg);
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffFromDt_B, fromDt);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffThruDt_B, thruDt);
            cMsg.B.no(i).basePrcTermDealAmtRate_B.clear();
        }
    }
    // add end 2016/03/18 CSA Defect#3278

    private void doProcess_NSAL0300Scrn00_ChangeDurationPeriod(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // add start 2015/12/23 CSA Defect#1824
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(cMsg.contrVrsnEffFromDt.getValue());
        param.setContrDurnNum(cMsg.contrDurnAot.getValue());
        param.setCycleUomCd(cMsg.bllgCycleUomCd.getValue());

        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcEndDt();
        ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt, param.getContrEffThruDt());
        // add end 2015/12/23 CSA Defect#1824

        // START 2017/08/24 K.Kojima [QC#20743,ADD]
        copyDtlEffDate(cMsg, sMsg);
        // END 2017/08/24 K.Kojima [QC#20743,ADD]
    }

    // START 2016/02/09 T.Kanasaka [QC3273, ADD]
    // START 2018/02/19 M.Kidokoro [QC#23629, DEL]
//    private void doProcess_NSAL0300Scrn00_ChangeFreeCopyCnt(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        int bIdx = cMsg.xxRowNum_B.getValueInt();
//        if (ZYPCommonFunc.hasValue((cMsg.B.no(bIdx).bllgFreeCopyCnt_B))) {
//            ZYPEZDItemValueSetter.setValue(cMsg.B.no(bIdx).bllgRollOverRatio_B, ROLL_OVER_MAX);
//        } else {
//            cMsg.B.no(bIdx).bllgRollOverRatio_B.clear();
//        }
//    }
    // END 2018/02/19 M.Kidokoro [QC#23629, DEL]
    // END 2016/02/09 T.Kanasaka [QC3273, ADD]

    // START 2016/02/26 T.Kanasaka [QC4092, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeSvcContrBrCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // mod start 2016/04/07 CSA Defect#5312,5313
        //doProcess_NSAL0300Scrn00_Search_Branch(cMsg, sMsg);
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        String st = cMsg.xxDplyByCdNmCnctTxt.getValue();
        if (!ZYPCommonFunc.hasValue((cMsg.xxDplyByCdNmCnctTxt))) {
            cMsg.svcLineBizCd.clear();
            cMsg.svcContrBrCd.clear();
            cMsg.svcContrBrDescTxt.clear();
            return;
        }
        String[] st2 = st.split(DIV_STR, 2);
        String[] para = new String[st2.length];
        for (int i = 0; i < st2.length; i++) {
            para[i] = st2[i];
        }
        String lob = para[0].trim();
        String branchName = PERCENT;
        if (st2.length > 1) {
            para[1] = para[1].trim();
            branchName = para[1] + branchName;
        }
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findBranchNameInfo(getGlobalCompanyCode(), lob, branchName);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
            if (rslt.getQueryResultCount() > 1) {
                setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
            } else {
                Map<String, String> rsltMap = rsltList.get(0);
                lob = lob.concat(DIV_STR);
                lob = lob.concat((String) rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
                setValue(cMsg.xxDplyByCdNmCnctTxt, lob);
                setValue(cMsg.svcContrBrCd, rsltMap.get("SVC_CONTR_BR_CD"));
                setValue(cMsg.svcContrBrDescTxt, rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
                setValue(cMsg.svcLineBizCd, rsltMap.get("SVC_LINE_BIZ_CD"));
            }
        } else {
            cMsg.xxDplyByCdNmCnctTxt.setErrorInfo(1, NSAM0045E, new String[] {LOB_OR_BR });
        }
        // mod end 2016/04/07 CSA Defect#5312,5313
    }

    private void doProcess_NSAL0300Scrn00_ChangeContrAdminPsnCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // mod start 2016/04/07 CSA Defect#5312,5313
//        if (ZYPCommonFunc.hasValue((cMsg.contrAdminPsnCd))) {
//            NSAL0300Query query = NSAL0300Query.getInstance();
//            S21_PSNTMsg tMsg = query.getS21Psn(getGlobalCompanyCode(), cMsg.contrAdminPsnCd.getValue());
//            if (tMsg == null) {
//                cMsg.contrAdminPsnCd.setErrorInfo(1, NSAM0072E, new String[] {"Rep" });
//            } else {
//                ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm, tMsg.psnFirstNm.getValue() + " " + tMsg.psnLastNm.getValue());
//            }
//        } else {
//            cMsg.xxPsnNm.clear();
//        }
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        String st = cMsg.xxPsnNm.getValue();
        if (!ZYPCommonFunc.hasValue(cMsg.xxPsnNm)) {
            cMsg.contrAdminPsnCd.clear();
            return;
        }

        String[] st2 = st.split(" ");
        String[] para = new String[st2.length];
        for (int i = 0; i < st2.length; i++) {
            para[i] = st2[i];
        }
        String firstNm = para[0];
        String lastNm = "";
        if (st2.length > 1) {
            lastNm = para[1] + PERCENT;
        } else {
            firstNm = firstNm + PERCENT;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findRepNameInfo(getGlobalCompanyCode(), firstNm, lastNm);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
            if (rslt.getQueryResultCount() > 1) {
                setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
            } else {
                Map<String, String> rsltMap = rsltList.get(0);
                String repName = "";
                repName = repName.concat((String) rsltMap.get("FIRST_NM"));
                repName = repName.concat(" ");
                repName = repName.concat((String) rsltMap.get("LAST_NM"));
                setValue(cMsg.xxPsnNm, repName);
                setValue(cMsg.contrAdminPsnCd, rsltMap.get("PSN_CD"));
            }
        } else {
            cMsg.xxPsnNm.setErrorInfo(1, NSAM0045E, new String[] {REP_NAME });
        }
        // mod end 2016/04/07 CSA Defect#5312,5313
    }

    // START 2016/04/26 T.Tomita [QC#4668, MOD]
    private void doProcess_NSAL0300Scrn00_ChangeTocCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        cMsg.tocNm.clear();
//        NSAL0300CommonLogic.searchSalesRep(getGlobalCompanyCode(), cMsg);
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.tocNm)) {
            cMsg.tocCd.clear();
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findTocInfo(glblCmpyCd, cMsg.tocNm.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.tocNm.setErrorInfo(1, NSAM0045E, new String[] {"Sales Rep" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> resultMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);
            setValue(cMsg.tocCd, (resultMap.get("TOC_CD")));
            setValue(cMsg.tocNm, (resultMap.get("TOC_NM")));
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // END 2016/04/26 T.Tomita [QC#4668, MOD]

    private void doProcess_NSAL0300Scrn00_ChangePmtTermCashDiscCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue((cMsg.pmtTermCashDiscCd))) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            PMT_TERM_CASH_DISCTMsg tMsg = query.getPmtTermCashDisc(getGlobalCompanyCode(), cMsg.pmtTermCashDiscCd.getValue());
            if (tMsg == null) {
                cMsg.pmtTermCashDiscCd.setErrorInfo(1, NSAM0072E, new String[] {"Payment Term" });
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscDescTxt, tMsg.pmtTermCashDiscDescTxt);
            }
            // START 2023/02/12 R.Takau [QC#55645,ADD]
            if (PMT_TERM_CASH_DISC.CHECK_BY_PHONE.equals(cMsg.pmtTermCashDiscCd.getValue())){
                cMsg.crCardCustRefNum.clear();
                cMsg.xxScrItem10Txt.clear();
                cMsg.xxMthDt_CC.clear();
                cMsg.xxYrDt_CC.clear();
            } else {
                cMsg.bankRteNum.clear();
                cMsg.dsBankAcctNum.clear();
                cMsg.dsCustBankAcctRelnPk.clear();
                cMsg.ezUpTime_CP.clear();
                cMsg.ezUpTimeZone_CP.clear();
            }
            // END 2023/02/12 R.Takau [QC#55645,ADD]
        } else {
            cMsg.pmtTermCashDiscDescTxt.clear();
        }
    }

    // START 2016/04/26 T.Tomita [QC#3886, MOD]
    private void doProcess_NSAL0300Scrn00_ChangeLeaseCmpyCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        doProcess_NSAL0300Scrn00_Search_LeaseCompany(cMsg, sMsg);
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
            return;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findBillToCust(getGlobalCompanyCode(), cMsg.leaseCmpyCd.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0072E, new String[] {"Lease Company" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            String locNm = (String) rsltMap.get("LOC_NM");
            setValue(cMsg.dsAcctNm_L, locNm);
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // END 2016/04/26 T.Tomita [QC#3886, MOD]

    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    private void doProcess_NSAL0300Scrn00_ChangeSerNum(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue((cMsg.serNum))) {
            searchSerial(cMsg, sMsg);
        } else {
            cMsg.svcMachMstrPk_M.clear();
            cMsg.mdseCd_M.clear();
        }
    }

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeShipToCustCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            return;
        }

        int idx = cMsg.xxRowNum.getValueInt();
        if (!ZYPCommonFunc.hasValue((cMsg.B.no(idx).shipToCustCd_B))) {
            return;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findShipToCust(getGlobalCompanyCode(), cMsg.B.no(idx).shipToCustCd_B.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.B.no(idx).shipToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Ship To Location" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            String locNm = (String) rsltMap.get("LOC_NM");
            setValue(cMsg.B.no(idx).shipToLocNm_B, locNm);
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    // START 2016/04/26 T.Tomita [QC#3886, MOD]
    private void doProcess_NSAL0300Scrn00_ChangeBaseBillToCustCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        doProcess_NSAL0300Scrn00_Search_BillTo_Base(cMsg, sMsg);
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            return;
        }

        int idx = cMsg.xxRowNum.getValueInt();
        if (!ZYPCommonFunc.hasValue((cMsg.B.no(idx).baseBillToCustCd_B))) {
            // START 2018/07/11 K.Kojima [QC#24243,ADD]
            cMsg.B.no(idx).billToLocNm_BB.clear();
            cMsg.B.no(idx).billToCustLocAddr_BB.clear();
            // END 2018/07/11 K.Kojima [QC#24243,ADD]
            return;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findBillToCust(getGlobalCompanyCode(), cMsg.B.no(idx).baseBillToCustCd_B.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.B.no(idx).baseBillToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            String locNm = (String) rsltMap.get("LOC_NM");
            setValue(cMsg.B.no(idx).billToLocNm_BB, locNm);
            // START 2018/07/11 K.Kojima [QC#24243,ADD]
            String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
            String scdLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
            String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
            String frthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
            String ctyAddr = (String) rsltMap.get("CTY_ADDR");
            String stCd = (String) rsltMap.get("ST_CD");
            String postCd = (String) rsltMap.get("POST_CD");
            String addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, scdLineAddr, thirdLineAddr, frthLineAddr, ctyAddr, stCd, postCd);
            setValue(cMsg.B.no(idx).billToCustLocAddr_BB, addr);
            // END 2018/07/11 K.Kojima [QC#24243,ADD]
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeBllgMtrBillToCustCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        doProcess_NSAL0300Scrn00_Search_BillTo_Meter(cMsg, sMsg);
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            return;
        }

        int idx = cMsg.xxRowNum.getValueInt();
        if (!ZYPCommonFunc.hasValue((cMsg.B.no(idx).bllgMtrBillToCustCd_B))) {
            // START 2018/07/11 K.Kojima [QC#24243,ADD]
            cMsg.B.no(idx).billToLocNm_BM.clear();
            cMsg.B.no(idx).billToCustLocAddr_BM.clear();
            // END 2018/07/11 K.Kojima [QC#24243,ADD]
            return;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findBillToCust(getGlobalCompanyCode(), cMsg.B.no(idx).bllgMtrBillToCustCd_B.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.B.no(idx).bllgMtrBillToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            String locNm = (String) rsltMap.get("LOC_NM");
            setValue(cMsg.B.no(idx).billToLocNm_BM, locNm);
            String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
            String scdLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
            String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
            String frthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
            String ctyAddr = (String) rsltMap.get("CTY_ADDR");
            String stCd = (String) rsltMap.get("ST_CD");
            String postCd = (String) rsltMap.get("POST_CD");
            String addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, scdLineAddr, thirdLineAddr, frthLineAddr, ctyAddr, stCd, postCd);
            setValue(cMsg.B.no(idx).billToCustLocAddr_BM, addr);
            // Add Start 2019/02/14 QC#30295
            copybllgMtrBillToCustCd(cMsg, idx);
            // Add End 2019/02/14 QC#30295
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // END 2016/04/26 T.Tomita [QC#3886, MOD]
    // END 2016/02/26 T.Kanasaka [QC4092, ADD]

    // START 2016/05/09 T.Kanasaka [QC#6798, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeContrAutoRnwTpCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300CommonLogic.setRnwFlg(getGlobalCompanyCode(), cMsg);
    }

    private void doProcess_NSAL0300Scrn00_ChangeContrUplftTpCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300CommonLogic.setUplftFlg(getGlobalCompanyCode(), cMsg);
    }
    // END 2016/05/09 T.Kanasaka [QC#6798, ADD]

    // START 2016/05/17 T.Kanasaka [QC#2184, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeBaseBllgCycleCdA(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_A.getValueInt();
        BigDecimal dsContrDtlPk = cMsg.A.no(idx).dsContrDtlPk_A.getValue();
        String bllgCycleCd = cMsg.A.no(idx).baseBllgCycleCd_A.getValue();
        if (!ZYPCommonFunc.hasValue(bllgCycleCd)) {
            return;
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        BLLG_CYCLETMsg bllgCycleTMsg = query.getBllgCycle(getGlobalCompanyCode(), bllgCycleCd);

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgCycleCd_B, bllgCycleCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgCycleStartMth_BB, bllgCycleTMsg.bllgCycleStartMth);

                if (ZYPCommonFunc.hasValue(bllgCycleTMsg.bllgCycleStartMth)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseDplyPerEndDay_B, LAST_DAY_OF_A_MONTH);
                }

                String dsContrDtlTpCd = cMsg.B.no(i).dsContrDtlTpCd_B.getValue();
                if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrDplyPerEndDay_B, LAST_DAY_OF_A_MONTH);
                }

                break;
            }
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeBaseBllgCycleCdB(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_B.getValueInt();
        String bllgCycleCd = cMsg.B.no(idx).baseBllgCycleCd_B.getValue();
        BigDecimal dsContrDtlPk = cMsg.B.no(idx).dsContrDtlPk_B.getValue();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).baseBllgCycleCd_A, bllgCycleCd);
                break;
            }
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).baseBllgCycleCd_A, bllgCycleCd);
                break;
            }
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        BLLG_CYCLETMsg bllgCycleTMsg = query.getBllgCycle(getGlobalCompanyCode(), bllgCycleCd);
        ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).bllgCycleStartMth_BB, bllgCycleTMsg.bllgCycleStartMth);

        if (ZYPCommonFunc.hasValue(bllgCycleTMsg.bllgCycleStartMth)) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).baseDplyPerEndDay_B, LAST_DAY_OF_A_MONTH);
        }

        String dsContrDtlTpCd = cMsg.B.no(idx).dsContrDtlTpCd_B.getValue();
        if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).mtrDplyPerEndDay_B, LAST_DAY_OF_A_MONTH);
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeBllgMtrBllgCycleCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int idx = cMsg.xxRowNum_B.getValueInt();
        String bllgCycleCd = cMsg.B.no(idx).bllgMtrBllgCycleCd_B.getValue();

        NSAL0300Query query = NSAL0300Query.getInstance();
        BLLG_CYCLETMsg bllgCycleTMsg = query.getBllgCycle(getGlobalCompanyCode(), bllgCycleCd);
        ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).bllgCycleStartMth_BU, bllgCycleTMsg.bllgCycleStartMth);

        if (ZYPCommonFunc.hasValue(bllgCycleTMsg.bllgCycleStartMth)) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).mtrDplyPerEndDay_B, LAST_DAY_OF_A_MONTH);
        }

        String dsContrDtlTpCd = cMsg.B.no(idx).dsContrDtlTpCd_B.getValue();
        if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue())) {
            BigDecimal dsContrDtlPk = cMsg.B.no(idx).dsContrDtlPk_B.getValue();
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                if (NSAL0300CommonLogic.isEqualNum(dsContrDtlPk, cMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).baseDplyPerEndDay_B, LAST_DAY_OF_A_MONTH);
                }

                break;
            }
        }
    }
    // END 2016/05/17 T.Kanasaka [QC#2184, ADD]

    // START 2017/01/27 [QC#17278, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeReportGrp(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        setValue(cMsg.xxSetFlg, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrRptGrpDescTxt)) {
            cMsg.dsContrRptGrpNum.clear();
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        // START 2018/06/18 K.Kim [QC#25195, MOD]
        // S21SsmEZDResult rslt = NSAL0300Query.getInstance().findDsContrRptGrp(glblCmpyCd, cMsg.dsContrRptGrpDescTxt.getValue());
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findDsContrRptGrp(glblCmpyCd, cMsg.dsContrRptGrpDescTxt.getValue(), cMsg.dsContrRptGrpNum.getValue());
        // END 2018/06/18 K.Kim [QC#25195, MOD]
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.dsContrRptGrpDescTxt.setErrorInfo(1, NSAM0072E, new String[] {"Report Group" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);

            String dsContrRptGrpNum = (String) rsltMap.get("DS_CONTR_RPT_GRP_NUM");
            String dsContrRptGrpDescTxt = (String) rsltMap.get("DS_CONTR_RPT_GRP_DESC_TXT");
            setValue(cMsg.dsContrRptGrpNum, dsContrRptGrpNum);
            setValue(cMsg.dsContrRptGrpDescTxt, dsContrRptGrpDescTxt);
        } else {
            setValue(cMsg.xxSetFlg, ZYPConstant.FLG_ON_Y);
        }
    }
    // END   2017/01/27 [QC#17278, ADD]

    private void doProcess_NSAL0300Scrn00_CMN_Clear(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // add start 2018/08/30 QC#27961
        NSAL0300CommonLogic.resetSpclInstnInfo(cMsg);
        // add end 2018/08/30 QC#27961
        cMsg.dsContrPk.clear();
        cMsg.dsContrNum.clear();
        cMsg.dsContrSqNum.clear();
        NSAL0300CommonLogic.resetParameter(cMsg, sMsg);
        // START 2016/02/18 T.Aoyagi [QC3700, ADD]
//        NSAL0300CommonLogic.setDefaultSummaryDetailMode(cMsg);
        // END 2016/02/18 T.Aoyagi [QC3700, ADD]
    }

    private void doProcess_NSAL0300Scrn00_CMN_Reset(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // add start 2018/08/30 QC#27961
        NSAL0300CommonLogic.resetSpclInstnInfo(cMsg);
        // add end 2018/08/30 QC#27961
        cMsg.dsContrNum.clear();
        cMsg.dsContrSqNum.clear();
        search(cMsg, sMsg, new NSAL0300DisplayControllBean());
        NSAL0300CommonLogic.initDisplay(cMsg);
    }

    private void doProcess_NSAL0300Scrn00_CMN_Return(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_CMN_Save(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_CMN_Submit(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_Delete_Detail(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0034E);
        } else {
            // add start 2016/11/22 CSA Defect#16114
            NSAL0300Query query = NSAL0300Query.getInstance();
            boolean hasError = false;
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A.getValue())) {
                    BigDecimal dsContrDtlPk = null;
                    if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                        dsContrDtlPk = cMsg.A.no(i).prntDsContrDtlPk_A.getValue();
                    } else {
                        dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
                    }
                    if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                        continue;
                    }
                    BigDecimal count = query.getBllgSchdCloseCount(getGlobalCompanyCode(), dsContrDtlPk);
                    if (count.compareTo(BigDecimal.ZERO) > 0) {
                        cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAM0368E);
                        hasError = true;
                    }
                }
            }
            if (hasError) {
                return;
            }
            // add end 2016/11/22 CSA Defect#16114

            List<Map<String, Object>> delAccInfoList = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A.getValue()) && NSAL0300CommonLogic.hasAccessory(cMsg, cMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                    delAccInfoList.addAll(NSAL0300CommonLogic.getChildAccessoryInfoList(sMsg.A, cMsg.A.no(i).dsContrDtlPk_A.getValue()));
                }
            }

            int idx = -1;
            // mod start 2016/07/21 CSA Defect#11696
            //for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            for (int i = cMsg.A.getValidCount() - 1; i >= 0; i--) {
            // mod end 2016/07/21 CSA Defect#11696
                if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(i).xxChkBox_A.getValue())) {
                    int cIdx = i;
                    int sIdx = NSAL0300CommonLogic.convertPageIndexToRowIndexA(cMsg, sMsg, cIdx);
                    // START 2017/07/27 [QC#16889, MOD]
                    // String serNum = sMsg.A.no(sIdx).serNum_A.getValue();
                    // String mdseCd = sMsg.A.no(sIdx).mdseCd_A.getValue();
                    BigDecimal dsContrDtlPk = sMsg.A.no(sIdx).dsContrDtlPk_A.getValue();
                    // END   2017/07/27 [QC#16889, MOD]
                    NSAL0300CommonLogic.delete(sMsg.A, sIdx);
                    if (!DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                        // START 2017/07/27 [QC#16889, MOD]
                        // NSAL0300CommonLogic.deleteTableBbySerNum(cMsg, serNum, mdseCd);
                        NSAL0300CommonLogic.deleteTableBbyDsContrDtlPk(cMsg, dsContrDtlPk);
                        // END   2017/07/27 [QC#16889, MOD]
                    }
                    // mod start 2016/07/21 CSA Defect#11696
                    //if (idx < 0) {
                    //    idx = sIdx;
                    //}
                    idx = sIdx;
                    // mod end 2016/07/21 CSA Defect#11696

                    // Save SMsg.X
                    // START 2017/07/27 [QC#16889, MOD]
                    // BigDecimal dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
                    dsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
                    // END   2017/07/27 [QC#16889, MOD]
                    NSAL0300CommonLogic.saveDeleteDsContrDtlPk(sMsg, dsContrDtlPk);
                }
            }

            for (Map<String, Object> delAccInfoMap : delAccInfoList) {
                String serNum = (String) delAccInfoMap.get(MAP_KEY_SER_NUM);
                String mdseCd = (String) delAccInfoMap.get(MAP_KEY_MDSE_CD);
                BigDecimal dsContrDtlPk = (BigDecimal) delAccInfoMap.get(MAP_KEY_DS_CONTR_DTL_PK);

                // START 2017/07/27 [QC#16889, MOD]
                // NSAL0300CommonLogic.deleteTableAbySerNum(sMsg, serNum, mdseCd);
                NSAL0300CommonLogic.deleteTableAbyDsContrDtlPk(sMsg, dsContrDtlPk);
                if (!DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                    // NSAL0300CommonLogic.deleteTableBbySerNum(cMsg, serNum, mdseCd);
                    NSAL0300CommonLogic.deleteTableBbyDsContrDtlPk(cMsg, dsContrDtlPk);
                }
                // END 2017/07/27 [QC#16889, MOD]
                // Save SMsg.X
                NSAL0300CommonLogic.saveDeleteDsContrDtlPk(sMsg, dsContrDtlPk);
            }

            int i = idx - 1;
            for (; i >= 0; i--) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                    break;
                }
            }
            if (i < 0) {
                i = NSAL0300CommonLogic.convertIndexFromNeedCount(sMsg, 1);
            }
            NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, i);
            cMsg.xxChkBox_M.clear();
        }
    }

    private void doProcess_NSAL0300Scrn00_Delete_Price(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum_BX)) {
            if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                int rowNum = cMsg.xxRowNum_BX.getValueInt();
                String selectBllgMtrLbCd = cMsg.B.no(rowNum).bllgMtrLbCd_B.getValue();
                BigDecimal preDsContrBllgMtrPk = INVLD_DS_CONTR_BLLG_MTR_PK;

                int indexOfSameMtr = NSAL0300CommonLogic.getIndexOfSameMtr(cMsg, cMsg.B.no(rowNum).dsContrBllgMtrPk_B.getValue(), rowNum);

                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                    BigDecimal dsContrBllgMtrPk = cMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
                    String bllgMtrLbCd = cMsg.B.no(i).bllgMtrLbCd_B.getValue();
                    // mod start 2016/07/19 CSA Defect#11526
                    if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && preDsContrBllgMtrPk.compareTo(dsContrBllgMtrPk) != 0 && selectBllgMtrLbCd.equals(bllgMtrLbCd)) {
                    // mod end 2016/07/19 CSA Defect#11526
                        int rowNumDel = i + indexOfSameMtr;
                        // START 2017/07/27 [QC#16889, MOD]
                        // String serNum = cMsg.B.no(rowNumDel).serNum_B.getValue();
                        // String mdseCd = cMsg.B.no(rowNumDel).mdseCd_B.getValue();
                        BigDecimal dsContrDtlPk = cMsg.B.no(rowNumDel).dsContrDtlPk_B.getValue();
                        // int xsBrktCnt = NSAL0300CommonLogic.getExcessCopyBracketCount(cMsg, serNum, mdseCd, dsContrBllgMtrPk);
                        int xsBrktCnt = NSAL0300CommonLogic.getExcessCopyBracketCount(cMsg, dsContrDtlPk, dsContrBllgMtrPk);
                        // END 2017/07/27 [QC#16889, MOD]
                        if (xsBrktCnt <= 1) {
                            cMsg.B.no(rowNumDel).contrXsCopyPk_B.clear();
                            cMsg.B.no(rowNumDel).xsMtrCopyQty_B.clear();
                            cMsg.B.no(rowNumDel).xsMtrAmtRate_B.clear();
                        } else {
                            NSAL0300CommonLogic.delete(cMsg.B, rowNumDel);
                            i--;
                        }
                        // add start 2016/07/19 CSA Defect#11526
                        preDsContrBllgMtrPk = dsContrBllgMtrPk;
                        // add end 2016/07/19 CSA Defect#11526
                    }
                    // del start 2016/07/19 CSA Defect#11526
                    //preDsContrBllgMtrPk = dsContrBllgMtrPk;
                    // del end 2016/07/19 CSA Defect#11526
                }
                // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
                // NSAL0300CommonLogic.setCopyQtyForAggr(cMsg);
                NSAL0300CommonLogic.setCopyQtyForAggr(cMsg, getGlobalCompanyCode());
                // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
            } else {
                int rowNum = cMsg.xxRowNum_BX.getValueInt();
                // START 2017/07/27 [QC#16889, MOD]
                // String serNum = cMsg.B.no(rowNum).serNum_B.getValue();
                // String mdseCd = cMsg.B.no(rowNum).mdseCd_B.getValue();
                BigDecimal dsContrDtlPk = cMsg.B.no(rowNum).dsContrDtlPk_B.getValue();
                BigDecimal dsContrBllgMtrPk = cMsg.B.no(rowNum).dsContrBllgMtrPk_B.getValue();
                // int xsBrktCnt = NSAL0300CommonLogic.getExcessCopyBracketCount(cMsg, serNum, mdseCd, dsContrBllgMtrPk);
                int xsBrktCnt = NSAL0300CommonLogic.getExcessCopyBracketCount(cMsg, dsContrDtlPk, dsContrBllgMtrPk);
                // END 2017/07/27 [QC#16889, MOD]
                if (xsBrktCnt <= 1) {
                    cMsg.B.no(rowNum).contrXsCopyPk_B.clear();
                    cMsg.B.no(rowNum).xsMtrCopyQty_B.clear();
                    cMsg.B.no(rowNum).xsMtrAmtRate_B.clear();
                } else {
                    NSAL0300CommonLogic.delete(cMsg.B, rowNum);
                }
            }
        } else {
            cMsg.setMessageInfo(NSAM0034E);
        }
    }

    private void doProcess_NSAL0300Scrn00_Download(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // START 2016/02/24 [QC3697, ADD]
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0300F00FMsg fMsg = new NSAL0300F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        String[] csvHeader = new String[] {"IB ID", "Serial#", "Machine Frequency", "Price", "Meter Read Method" };
        csvOutFile.writeHeader(csvHeader);
        csvOutFile.close();
        // END 2016/02/24 [QC3697, ADD]
    }

    private void doProcess_NSAL0300Scrn00_OpenForUpdate(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (!NSAL0300CommonLogic.hasErrorOrWarn(cMsg)) {
            search(cMsg, sMsg);
            // START 2016/02/18 T.Aoyagi [QC3700, ADD]
            NSAL0300CommonLogic.setDefaultSummaryDetailMode(cMsg);
            // END 2016/02/18 T.Aoyagi [QC3700, ADD]
        }
    }

    private void doProcess_NSAL0300Scrn00_DisplayAccessory(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        int cIdx = cMsg.xxRowNum_A.getValueInt();
//        int sIdx = NSAL0300CommonLogic.convertPageIndexToRowIndexA(cMsg, sMsg, cIdx);
//        List<NSAL0300_BCMsg> accessoryBCMsgList = NSAL0300CommonLogic.getChildAccessoryBCMsgList(cMsg.B, cMsg.A.no(cIdx).dsContrDtlPk_A.getValue());
//
//        String xxFilePathTxt = ASRY_CLOSE;
//        String xxDplyCtrlFlg = ZYPConstant.FLG_OFF_N;
//        if (ASRY_CLOSE.equals(cMsg.A.no(cIdx).xxFilePathTxt_A1.getValue())) {
//            xxFilePathTxt = ASRY_OPEN;
//            xxDplyCtrlFlg = ZYPConstant.FLG_ON_Y;
//        }
//
//        ZYPEZDItemValueSetter.setValue(cMsg.A.no(cIdx).xxFilePathTxt_A1, xxFilePathTxt);
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sIdx).xxFilePathTxt_A1, xxFilePathTxt);
//
//        for (NSAL0300_BCMsg bCMsg : accessoryBCMsgList) {
//            ZYPEZDItemValueSetter.setValue(bCMsg.xxDplyCtrlFlg_B1, xxDplyCtrlFlg);
//        }
//
//        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, cIdx);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Add_Detail(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_BillingCounters(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    private void doProcess_NSAL0300Scrn00_OpenWin_ShipTo(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

    private void doProcess_NSAL0300Scrn00_OpenWin_BillTo_Base(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_BillTo_Meter(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_BillTo_Usage(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    // START 2016/03/02 T.Tomita [QC#3048, MOD]
//    private void doProcess_NSAL0300Scrn00_OpenWin_BillToContact(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        NSAL0300Query query = NSAL0300Query.getInstance();
//        BILL_TO_CUSTTMsg billToCustTMsg = query.getBillToCust(getGlobalCompanyCode(), cMsg.altPayerCustCd.getValue());
//        if (billToCustTMsg != null) {
//            ZYPEZDItemValueSetter.setValue(cMsg.locNum_PP, billToCustTMsg.locNum);
//        }
//    }
    // END 2016/03/02 T.Tomita [QC#3048, MOD]

      // START 2016/03/04 T.Tomita [QC#3048, MOD]
//    private void doProcess_NSAL0300Scrn00_OpenWin_Contact_Base(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        int bIdx = cMsg.xxRowNum_B.getValueInt();
//        NSAL0300Query query = NSAL0300Query.getInstance();
//        BILL_TO_CUSTTMsg billToCustTMsg = query.getBillToCust(getGlobalCompanyCode(), cMsg.B.no(bIdx).baseBillToCustCd_B.getValue());
//        if (billToCustTMsg != null) {
//            ZYPEZDItemValueSetter.setValue(cMsg.locNum_PP, billToCustTMsg.locNum);
//        }
//    }

//    private void doProcess_NSAL0300Scrn00_OpenWin_Contact_Meter(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        int bIdx = cMsg.xxRowNum_B.getValueInt();
//        NSAL0300Query query = NSAL0300Query.getInstance();
//        BILL_TO_CUSTTMsg billToCustTMsg = query.getBillToCust(getGlobalCompanyCode(), cMsg.B.no(bIdx).bllgMtrBillToCustCd_B.getValue());
//        if (billToCustTMsg != null) {
//            ZYPEZDItemValueSetter.setValue(cMsg.locNum_PP, billToCustTMsg.locNum);
//        }
//    }
    // END 2016/03/04 T.Tomita [QC#3048, MOD]

    private void doProcess_NSAL0300Scrn00_OpenWin_ContractNum(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_CreditCardPo(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_LeaseCompany(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_PaymentTerm(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Schedule_Base(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Schedule_Usage(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_SellTo(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Serial(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // START 2018/10/15 K.Kitachi [QC#28713, ADD]
        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findSellToCust(getGlobalCompanyCode(), cMsg.dsAcctNum.getValue().concat(PERCENT));
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.dsAcctNum.setErrorInfo(1, NSAM0072E, new String[] {"Customer" });
        }
        // END 2018/10/15 K.Kitachi [QC#28713, ADD]
    }

    // Mod Start 2018/01/10 QC#18552
    private void doProcess_NSAL0300Scrn00_OpenWin_UpliftDetail(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
//        search(cMsg, sMsg);
        return;
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Escalation(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        return;
    }
    // Mod End 2018/01/10 QC#18552

    private void doProcess_NSAL0300Scrn00_OpenWin_Terminate(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Renew(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_StatusChange(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_CreditCard(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_SubContract(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_PricingEffectivity_Base(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_PricingEffectivity_Meter(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_AddNotes(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_Terms(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_OpenWin_CompleteContract(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_Go(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_PageJump(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int rowIndex = NSAL0300CommonLogic.convertPageNumToFirstRowIndex(sMsg, cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt());
        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL0300Scrn00_PageNext(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int rowIndex = NSAL0300CommonLogic.convertPageNumToFirstRowIndex(sMsg, cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt() + 1);
        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL0300Scrn00_PagePrev(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        int rowIndex = NSAL0300CommonLogic.convertPageNumToFirstRowIndex(sMsg, cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt() - 1);
        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSAL0300Scrn00_Search(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // START 2018/11/06 S.Kitamura [QC#28868,ADD]
        outputSearchLog(cMsg);
        // END 2018/11/06 S.Kitamura [QC#28868,ADD]        
        // add start 2018/08/30 QC#27961
        NSAL0300CommonLogic.resetSpclInstnInfo(cMsg);
        // add end 2018/08/30 QC#27961
        cMsg.dsContrPk.clear();
        search(cMsg, sMsg);
        // START 2016/02/18 T.Aoyagi [QC3700, ADD]
//        NSAL0300CommonLogic.setDefaultSummaryDetailMode(cMsg);
        // END 2016/02/18 T.Aoyagi [QC3700, ADD]
        NSAL0300CommonLogic.initDisplay(cMsg);
    }

    // START 2016/01/21 T.Tomita [QC#2182, ADD]
    private void doProcess_NSAL0300Scrn00_Search_Rep(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300CommonLogic.searchDefaultRep(getGlobalCompanyCode(), cMsg, sMsg);
    }
    // END  2016/01/21 T.Tomita [QC#2182, ADD]

    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    private void doProcess_NSAL0300Scrn00_Search_Branch(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue((cMsg.svcContrBrCd))) {
            NSAL0300CommonLogic.searchBranch(getGlobalCompanyCode(), cMsg, sMsg);
        } else {
            cMsg.svcContrBrDescTxt.clear();
        }
    }
    // END 2016/02/26 T.Kanasaka [QC4092, MOD]

    private void doProcess_NSAL0300Scrn00_Search_BillTo(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue((cMsg.altPayerCustCd))) {
            String glblCmpyCd = getGlobalCompanyCode();
            NSAL0300Query query = NSAL0300Query.getInstance();
            BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.altPayerCustCd.getValue());
            if (tMsg == null) {
                cMsg.altPayerCustCd.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.billToLocNm, tMsg.locNm);
            }
        // START 2016/02/26 T.Kanasaka [QC4092, ADD]
        } else {
            cMsg.billToLocNm.clear();
        // END 2016/02/26 T.Kanasaka [QC4092, ADD]
        }
        // START 2016/02/16 T.Aoyagi [QC2947, ADD]
        NSAL0300CommonLogic.setDefaultPmtTerm(getGlobalCompanyCode(), cMsg);
        // END 2016/02/16 T.Aoyagi [QC2947, ADD]
    }

    private void doProcess_NSAL0300Scrn00_Search_BillTo_Base(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            int idx = cMsg.xxRowNum.getValueInt();
            if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).baseBillToCustCd_B))) {
                String glblCmpyCd = getGlobalCompanyCode();
                NSAL0300Query query = NSAL0300Query.getInstance();
                BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.B.no(idx).baseBillToCustCd_B.getValue());
                if (tMsg == null) {
                    cMsg.B.no(idx).baseBillToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).billToLocNm_BB, tMsg.locNm);
                    // START 2018/07/11 K.Kojima [QC#24243,ADD]
                    String addr = NSAL0300CommonLogic.formatAddress(tMsg.firstLineAddr.getValue(), tMsg.scdLineAddr.getValue(), tMsg.thirdLineAddr.getValue(), tMsg.frthLineAddr.getValue(), tMsg.ctyAddr.getValue(), tMsg.stCd.getValue(), tMsg.postCd.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).billToCustLocAddr_BB, addr);
                    // END 2018/07/11 K.Kojima [QC#24243,ADD]
                }
            // START 2016/02/26 T.Kanasaka [QC4092, ADD]
            } else {
                cMsg.B.no(idx).billToLocNm_BB.clear();
            // END 2016/02/26 T.Kanasaka [QC4092, ADD]
            }
        }
    }

    private void doProcess_NSAL0300Scrn00_Search_BillTo_Usage(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            int idx = cMsg.xxRowNum.getValueInt();
            if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).usgBillToCustCd_B))) {
                String glblCmpyCd = getGlobalCompanyCode();
                NSAL0300Query query = NSAL0300Query.getInstance();
                BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.B.no(idx).usgBillToCustCd_B.getValue());
                if (tMsg == null) {
                    cMsg.B.no(idx).usgBillToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).billToLocNm_BU, tMsg.locNm);
                }
            }
        }
    }

    // START 2016/02/26 T.Kanasaka [QC4092, MOD]
    private void doProcess_NSAL0300Scrn00_Search_BillTo_Meter(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            int idx = cMsg.xxRowNum.getValueInt();
            if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).bllgMtrBillToCustCd_B))) {
                String glblCmpyCd = getGlobalCompanyCode();
                NSAL0300Query query = NSAL0300Query.getInstance();
                BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.B.no(idx).bllgMtrBillToCustCd_B.getValue());
                if (tMsg == null) {
                    cMsg.B.no(idx).bllgMtrBillToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Bill To" });
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).billToLocNm_BM, tMsg.locNm);
                    String addr = NSAL0300CommonLogic.formatAddress(tMsg.firstLineAddr.getValue(), tMsg.scdLineAddr.getValue(), tMsg.thirdLineAddr.getValue(), tMsg.frthLineAddr.getValue(), tMsg.ctyAddr.getValue(), tMsg.stCd.getValue(), tMsg.postCd.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).billToCustLocAddr_BM, addr);
                    // Add Start 2019/02/14 QC#30295
                    copybllgMtrBillToCustCd(cMsg, idx);
                    // Add End 2019/02/14 QC#30295
                }
            } else {
                cMsg.B.no(idx).billToLocNm_BM.clear();
                cMsg.B.no(idx).billToCustLocAddr_BM.clear();
            }
        }
    }
    // END 2016/02/26 T.Kanasaka [QC4092, MOD]
    // Add Start 2019/02/14 QC#30295
    private void copybllgMtrBillToCustCd(NSAL0300CMsg cMsg, int idx) {
        BigDecimal dsContrBllgMtrPk = cMsg.B.no(idx).dsContrBllgMtrPk_B.getValue();
        if (!hasValue(dsContrBllgMtrPk)) {
            return;
        }
        String billToCustCd = cMsg.B.no(idx).bllgMtrBillToCustCd_B.getValue();
        String billToLocNm = cMsg.B.no(idx).billToLocNm_BM.getValue();
        String addr = cMsg.B.no(idx).billToCustLocAddr_BM.getValue();
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (!hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                continue;
            }

            if (dsContrBllgMtrPk.compareTo(cMsg.B.no(i).dsContrBllgMtrPk_B.getValue()) != 0) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrBillToCustCd_B, billToCustCd);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToLocNm_BM, billToLocNm);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToCustLocAddr_BM, addr);
        }
    }
    // Add End 2019/02/14 QC#30295

    private void doProcess_NSAL0300Scrn00_Search_LeaseCompany(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
            String glblCmpyCd = getGlobalCompanyCode();
            NSAL0300Query query = NSAL0300Query.getInstance();
            // Lease Company is a Bill To Customer
            BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.leaseCmpyCd.getValue());
            if (tMsg == null) {
                cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0072E, new String[] {"Lease Company" });
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_L, tMsg.locNm);
            }
        // START 2016/02/26 T.Kanasaka [QC4092, ADD]
        } else {
            cMsg.dsAcctNm_L.clear();
        // END 2016/02/26 T.Kanasaka [QC4092, ADD]
        }
    }

    private void doProcess_NSAL0300Scrn00_Search_Customer(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum)) {
            String glblCmpyCd = getGlobalCompanyCode();
            NSAL0300Query query = NSAL0300Query.getInstance();
            SELL_TO_CUSTTMsg tMsg = query.getSellToCust(glblCmpyCd, cMsg.dsAcctNum.getValue());
            if (tMsg == null) {
                cMsg.dsAcctNum.setErrorInfo(1, NSAM0072E, new String[] {"Customer" });
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, tMsg.dsAcctNm);
            }
        // START 2016/02/26 T.Kanasaka [QC4092, ADD]
        } else {
            cMsg.dsAcctNm.clear();
        // END 2016/02/26 T.Kanasaka [QC4092, ADD]
        }
    }

    private void doProcess_NSAL0300Scrn00_Search_Serial(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        searchSerial(cMsg, sMsg);
    }

    private void doProcess_NSAL0300Scrn00_Upload(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // TODO Auto-generated method stub

    }

    private void doProcess_NSAL0300Scrn00_TBLColumnSort(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_A.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        }
    }

    private void doProcess_NSAL0300Scrn00_CMN_ColSave(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    private void doProcess_NSAL0300Scrn00_CMN_ColClear(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
    }

    // START 2016/11/15 T.Kanasaka [QC#15526, MOD]
    private void doProcess_NSAL0300Scrn00_Filter(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {

        String filterItem = cMsg.xxCondCd_1V.getValue();
        String filterCondition = cMsg.xxCondCd_2V.getValue();
        String filterValue = cMsg.condSqlTxt.getValue();
        String itemValue = null;
        boolean errFlg = false;
//        boolean wildCardFlg = false;

        // input check
        if (ZYPCommonFunc.hasValue(filterItem) || ZYPCommonFunc.hasValue(filterCondition) || ZYPCommonFunc.hasValue(filterValue)) {
            if (!ZYPCommonFunc.hasValue(filterItem)) {
                errFlg = true;
                cMsg.xxCondCd_1V.setErrorInfo(1, ZZM9000E, new String[] {"Filter By (Item)" });
            }
            if (!ZYPCommonFunc.hasValue(filterCondition)) {
                errFlg = true;
                cMsg.xxCondCd_2V.setErrorInfo(1, ZZM9000E, new String[] {"Filter By (Condition)" });
            }
            if (!ZYPCommonFunc.hasValue(filterValue)) {
                errFlg = true;
                cMsg.condSqlTxt.setErrorInfo(1, ZZM9000E, new String[] {"Filter By (Value)" });
            }
            // START 2022/10/07 N.Takatsu[QC#60071, ADD]
            if (!FILTER_ITEM_SEQUENCE_NUMBER.equals(filterItem) && FILTER_CONDITION_FROM_TO.equals(filterCondition)) {
                errFlg = true;
                cMsg.condSqlTxt.setErrorInfo(1, NSAM0765E, new String[] {"Filter By (Value)" });
            } 
            
            if (FILTER_ITEM_CONFIG_NUMBER.equals(filterItem) && !filterValue.matches(CONFIG_NUMBER_CHECK)) {
                errFlg = true;
                cMsg.condSqlTxt.setErrorInfo(1, NSAM0767E, new String[] {"Filter By (Value)" });
            } 
            if (FILTER_ITEM_SEQUENCE_NUMBER.equals(filterItem)) {
                if (FILTER_CONDITION_FROM_TO.equals(filterCondition)) {
                    if (rangeCheckFormat(filterItem, filterCondition, filterValue)) {
                        errFlg = true;
                        cMsg.condSqlTxt.setErrorInfo(1, NSAM0766E, new String[] {"Filter By (Value)" });
                    }
                } else {
                    if (simpleSubstanceCheckFormat(filterItem, filterValue)) {
                        errFlg = true;
                        cMsg.condSqlTxt.setErrorInfo(1, NSAM0768E, new String[] {"Filter By (Value)" });
                    }
                }
            } 

            if (filterSeqNumbercheck(filterValue)) {
                errFlg = true;
                cMsg.condSqlTxt.setErrorInfo(1, NSAM0766E, new String[] {"Filter By (Value)" });
            }
            // END 2022/10/07 N.Takatsu[QC#60071, ADD]

            if (errFlg) {
                return;
            }
        }

        // date format check
        if (ZYPCommonFunc.hasValue(filterValue) && FILTER_ITEM_START_DATE.equals(filterItem)) {
            String dateFormat = ZYPDateUtil.getJavaDateFormatString(true);
            dateFormat = dateFormat.replaceAll(SLASH, "");
            filterValue = filterValue.replaceAll(SLASH, "");
            if (!ZYPDateUtil.isValidDate(filterValue, dateFormat)) {
                errFlg = true;
                cMsg.condSqlTxt.setErrorInfo(1, ZZM9010E, new String[] {"Filter By (Value)" });
            } else {
                // change date format to yyyyMMdd
                filterValue = ZYPDateUtil.DateFormatter(filterValue, dateFormat, DATE_FORMAT_DATE);
            }
        }

//        if (ZYPCommonFunc.hasValue(filterValue) && (filterValue.indexOf(PERCENT) >= 0 || filterValue.indexOf(UNDERSCORE) >= 0)) {
//            if (FILTER_CONDITION_GREATER_THAN.equals(filterCondition) || FILTER_CONDITION_LESS_THAN.equals(filterCondition)) {
//                errFlg = true;
//                cMsg.condSqlTxt.setErrorInfo(1, NSAM0081E, new String[] {"'>' or '<'", "Exact Value" });
//            } else {
//                wildCardFlg = true;
//                filterValue = filterValue.replaceAll(PERCENT, PERIOD + ASTERISK);
//                filterValue = filterValue.replaceAll(UNDERSCORE, PERIOD);
//            }
//        }

        if (FILTER_CONDITION_CONTAINS.equals(filterCondition)) {
            filterValue = PERIOD + ASTERISK + filterValue + PERIOD + ASTERISK;
        }

        if (errFlg) {
            return;
        }

        // set filter flag -> xxDplyCtrlFlg_A (Y:not display)
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            itemValue = null;
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_OFF_N);

            if (!ZYPCommonFunc.hasValue(filterValue)) {
                continue;
            }

            if (FILTER_ITEM_SERIAL.equals(filterItem)) {
                itemValue = sMsg.A.no(i).serNum_A.getValue();
            } else if (FILTER_ITEM_MODEL.equals(filterItem)) {
                itemValue = sMsg.A.no(i).mdlNm_A.getValue();
            } else if (FILTER_ITEM_LOCATION.equals(filterItem)) {
                itemValue = sMsg.A.no(i).billToCustLocAddr_A.getValue();
            } else if (FILTER_ITEM_START_DATE.equals(filterItem)) {
                itemValue = sMsg.A.no(i).contrEffFromDt_A.getValue();
            // START 2022/10/07 N.Takatsu[QC#60071, ADD]
            } else if (FILTER_ITEM_CONFIG_NUMBER.equals(filterItem)) {
                itemValue = sMsg.A.no(i).svcConfigMstrPk_A.getValue().toString();
            } else if (FILTER_ITEM_LINE_STATUS.equals(filterItem)) {
                itemValue = sMsg.A.no(i).dsContrDtlStsDescTxt_A.getValue();
            } else if (FILTER_ITEM_ITEM_CODE.equals(filterItem)) {
                itemValue = sMsg.A.no(i).mdseCd_A.getValue();
            } else if (FILTER_ITEM_SEQUENCE_NUMBER.equals(filterItem)) {
                itemValue = sMsg.A.no(i).sqId_A.getValue();
            // END 2022/10/07 N.Takatsu[QC#60071, ADD]
            }

            if (FILTER_CONDITION_EQUAL.equals(filterCondition)) {
//                if (wildCardFlg) {
//                    if (!ZYPCommonFunc.hasValue(itemValue) || !itemValue.matches(filterValue)) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
//                    }
//                } else {
//                    if (!ZYPCommonFunc.hasValue(itemValue) || !itemValue.equals(filterValue)) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
//                    }
//                }
                if (!ZYPCommonFunc.hasValue(itemValue) || !itemValue.equals(filterValue)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                }
            } else if (FILTER_CONDITION_GREATER_THAN.equals(filterCondition)) {
                // START 2022/10/07 N.Takatsu[QC#60071, MOD]
                if (FILTER_ITEM_CONFIG_NUMBER.equals(filterItem) || FILTER_ITEM_SEQUENCE_NUMBER.equals(filterItem)) {
                    if (filterNum(sMsg, filterCondition, filterValue, itemValue)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                } else if (!ZYPCommonFunc.hasValue(itemValue) || !(itemValue.compareTo(filterValue) > 0)) {
                // if (!ZYPCommonFunc.hasValue(itemValue) || !(itemValue.compareTo(filterValue) > 0)) {
                // END 2022/10/07 N.Takatsu[QC#60071, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                }
            } else if (FILTER_CONDITION_CONTAINS.equals(filterCondition)) {
                if (!ZYPCommonFunc.hasValue(itemValue) || !itemValue.matches(filterValue)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                }
            } else if (FILTER_CONDITION_LESS_THAN.equals(filterCondition)) {
                // START 2022/10/07 N.Takatsu[QC#60071, MOD]
                if (FILTER_ITEM_CONFIG_NUMBER.equals(filterItem) || FILTER_ITEM_SEQUENCE_NUMBER.equals(filterItem)) {
                    if (filterNum(sMsg, filterCondition, filterValue, itemValue)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                } else if (ZYPCommonFunc.hasValue(itemValue) && !(itemValue.compareTo(filterValue) < 0)) {
                // if (ZYPCommonFunc.hasValue(itemValue) && !(itemValue.compareTo(filterValue) < 0)) {
                // END 2022/10/07 N.Takatsu[QC#60071, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                }
            } else if (FILTER_CONDITION_NOT_EQUAL.equals(filterCondition)) {
//                if (wildCardFlg) {
//                    if (ZYPCommonFunc.hasValue(itemValue) && itemValue.matches(filterValue)) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(itemValue) && itemValue.equals(filterValue)) {
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
//                    }
//                }
                if (ZYPCommonFunc.hasValue(itemValue) && itemValue.equals(filterValue)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                }
              // START 2022/10/07 N.Takatsu[QC#60071, ADD]
            } else if (FILTER_CONDITION_FROM_TO.equals(filterCondition) && FILTER_ITEM_SEQUENCE_NUMBER.equals(filterItem)) {
                if (filterFromTo(sMsg, filterValue, itemValue)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                }
              // END 2022/10/07 N.Takatsu[QC#60071, ADD]
            }
        }

        // pagenation
        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, NSAL0300CommonLogic.convertIndexFromNeedCount(sMsg, 1));
    }
    // END 2016/11/15 T.Kanasaka [QC#15526, MOD]

    private void search(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        search(cMsg, sMsg, null);
    }

    @SuppressWarnings("unchecked")
    private void search(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, NSAL0300DisplayControllBean displayBean) {

        if (NSAL0300CommonLogic.hasError(cMsg)) {
            return;
        }

        // START 2017/08/29 K.Kojima [QC#20057,ADD]
        HashMap<String, String> baseBllgCycleMap = new HashMap<String, String>(cMsg.A.getValidCount());
        HashMap<String, BigDecimal> basePrcDealAmtMap = new HashMap<String, BigDecimal>(cMsg.A.getValidCount());
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            String key = getMapKey(cMsg.A.no(i));
            if (key != null) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).baseBllgCycleCd_A)) {
                    baseBllgCycleMap.put(key, cMsg.A.no(i).baseBllgCycleCd_A.getValue());
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).basePrcDealAmt_A)) {
                    basePrcDealAmtMap.put(key, cMsg.A.no(i).basePrcDealAmt_A.getValue());
                }
            }
        }
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            String key = getMapKey(cMsg.B.no(i));
            if (key != null) {
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).baseBllgCycleCd_B)) {
                    baseBllgCycleMap.put(key, cMsg.B.no(i).baseBllgCycleCd_B.getValue());
                }
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).basePrcDealAmt_B)) {
                    basePrcDealAmtMap.put(key, cMsg.B.no(i).basePrcDealAmt_B.getValue());
                }
            }
        }
        // END 2017/08/29 K.Kojima [QC#20057,ADD]

        // Save display controll
        if (displayBean == null) {
            displayBean = NSAL0300CommonLogic.saveDisplay(cMsg, sMsg);
        }

        NSAL0300CommonLogic.resetParameter(cMsg, sMsg);

        String glblCmpyCd = getGlobalCompanyCode();
        String dsContrCatgCd = null;

        NSAL0300Query query = NSAL0300Query.getInstance();

        S21SsmEZDResult rslt = null;

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            rslt = query.getDsContrPk(glblCmpyCd, cMsg.dsContrNum.getValue(), cMsg.dsContrSqNum.getValue());
            if (rslt == null || !rslt.isCodeNormal()) {
                cMsg.setMessageInfo(NAZM0081E);
                EZDDebugOutput.println(3, String.format("dsContrNum=%s, dsContrSqNum=%s", cMsg.dsContrNum.getValue(), cMsg.dsContrSqNum.getValue()), NSAL0300BL02.class);
                return;
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, (BigDecimal) rslt.getResultObject());
            }
        }

        rslt = query.getDsContrInfo(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (rslt == null || !rslt.isCodeNormal()) {
            cMsg.setMessageInfo(NAZM0081E);
            EZDDebugOutput.println(3, String.format("dsContrPk=%s", cMsg.dsContrPk.getValue()), NSAL0300BL02.class);
            return;
        } else {
            Map<String, Object> rsltMap = (Map<String, Object>) rslt.getResultObject();
            // Header
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_C, (String) rsltMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_C, (String) rsltMap.get("EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, (BigDecimal) rsltMap.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgAbbrNm, (String) rsltMap.get("DS_CONTR_CATG_ABBR_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, (String) rsltMap.get("DS_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrSqNum, (String) rsltMap.get("DS_CONTR_SQ_NUM"));
            // add start 2018/11/07 K.Fujimoto QC#28627
            ZYPEZDItemValueSetter.setValue(cMsg.contrLinkNum, (String) rsltMap.get("CONTR_LINK_NUM"));
            // add end   2018/11/07 K.Fujimoto QC#28627
            ZYPEZDItemValueSetter.setValue(cMsg.svcLineBizCd, (String) rsltMap.get("SVC_LINE_BIZ_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcContrBrCd, (String) rsltMap.get("SVC_CONTR_BR_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcContrBrDescTxt, (String) rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
            // add start 2016/04/07 CSA Defect#5312,5313
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyByCdNmCnctTxt,
                    (String) rsltMap.get("SVC_LINE_BIZ_CD") + DIV_STR + (String) rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
            // add end 2016/04/07 CSA Defect#5312,5313
            ZYPEZDItemValueSetter.setValue(cMsg.contrAdminPsnCd, (String) rsltMap.get("CONTR_ADMIN_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm, (String) rsltMap.get("PSN_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrRptGrpNum, (String) rsltMap.get("DS_CONTR_RPT_GRP_NUM"));
            // START 2017/01/27 [QC#17278, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrRptGrpDescTxt, (String) rsltMap.get("DS_CONTR_RPT_GRP_DESC_TXT"));
            // END   2017/01/27 [QC#17278, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrNm, (String) rsltMap.get("DS_CONTR_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrStsCd, (String) rsltMap.get("DS_CONTR_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrStsDescTxt, (String) rsltMap.get("DS_CONTR_STS_DESC_TXT"));
            // START 2017/07/26 M.Kidokoro [QC#18122, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.contrTrmnAvalFlg, (String) rsltMap.get("CONTR_TRMN_AVAL_FLG"));
            // END 2017/07/26 M.Kidokoro [QC#18122, ADD]
            // START 2018/07/23 K.Kim [QC#26831, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.contrRnwAvalFlg, (String) rsltMap.get("CONTR_RNW_AVAL_FLG"));
            // END 2018/07/23 K.Kim [QC#26831, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrEdiCd, (String) rsltMap.get("DS_CONTR_EDI_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.contrInvCmntTxt, (String) rsltMap.get("CONTR_INV_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcContrRefCmntTxt, (String) rsltMap.get("SVC_CONTR_REF_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(cMsg.tocCd, (String) rsltMap.get("TOC_CD"));
            // START 2016/02/24 T.Kanasaka [QC4079, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.tocNm, (String) rsltMap.get("TOC_NM"));
            // END 2016/02/24 T.Kanasaka [QC4079, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd, (String) rsltMap.get("DS_CONTR_CLS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCratPsnCd, (String) rsltMap.get("CRAT_DRAFT_PSN_CD"));

            // End Customer
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, (String) rsltMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum, (String) rsltMap.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.altPayerCustCd, (String) rsltMap.get("ALT_PAYER_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.billToLocNm, (String) rsltMap.get("LOC_NM"));
            // add start 2016/09/06 CSA Defect#11243
            ZYPEZDItemValueSetter.setValue(cMsg.sellToCustCd, (String) rsltMap.get("SELL_TO_CUST_CD"));
            // add end 2016/09/06 CSA Defect#11243
            ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnPk_CP, (BigDecimal) rsltMap.get("CTAC_PSN_PK"));
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_CP, (String) rsltMap.get("PSN_NM_CP"));
            ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnFirstNm_CP, (String) rsltMap.get("CTAC_PSN_FIRST_NM_CP"));
            ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnLastNm_CP, (String) rsltMap.get("CTAC_PSN_LAST_NM_CP"));
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.leaseCmpyCd, (String) rsltMap.get("LEASE_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_L, (String) rsltMap.get("DS_ACCT_NM_L"));
            ZYPEZDItemValueSetter.setValue(cMsg.baseChrgToLeaseCmpyFlg, (String) rsltMap.get("BASE_CHRG_TO_LEASE_CMPY_FLG"));
            ZYPEZDItemValueSetter.setValue(cMsg.usgChrgToLeaseCmpyFlg, (String) rsltMap.get("USG_CHRG_TO_LEASE_CMPY_FLG"));
            // START 2018/05/15 K.Kitachi [QC#24265, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.cfsLeaseNumCmntTxt, (String) rsltMap.get("CFS_LEASE_NUM_CMNT_TXT"));
            // END 2018/05/15 K.Kitachi [QC#24265, ADD]
            // START 2019/01/09 K.Kitachi [QC#26928, MOD]
//            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_P, (String) rsltMap.get("EZUPTIME_P"));
//            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_P, (String) rsltMap.get("EZUPTIMEZONE_P"));
//            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCrCardPoPk, (BigDecimal) rsltMap.get("DS_CONTR_CR_CARD_PO_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_PO, (String) rsltMap.get("EZUPTIME_PO"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_PO, (String) rsltMap.get("EZUPTIMEZONE_PO"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCrCardPoPk_PO, (BigDecimal) rsltMap.get("DS_CONTR_CR_CARD_PO_PK_PO"));
            // END 2019/01/09 K.Kitachi [QC#26928, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.custPoNum, (String) rsltMap.get("CUST_PO_NUM"));
            // START 2019/01/09 K.Kitachi [QC#26928, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.poFromDt, (String) rsltMap.get("PO_FROM_DT"));
            // END 2019/01/09 K.Kitachi [QC#26928, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.poDt, (String) rsltMap.get("PO_DT"));
            // START 2019/01/09 K.Kitachi [QC#26928, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_CC, (String) rsltMap.get("EZUPTIME_CC"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_CC, (String) rsltMap.get("EZUPTIMEZONE_CC"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCrCardPoPk_CC, (BigDecimal) rsltMap.get("DS_CONTR_CR_CARD_PO_PK_CC"));
            // END 2019/01/09 K.Kitachi [QC#26928, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.crCardCustRefNum, (String) rsltMap.get("CR_CARD_CUST_REF_NUM"));
            // START 2019/11/27 E.Kameishi [QC#54594, ADD]
            if (rsltMap.get("CR_CARD_LAST_DIGIT_NUM") != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem10Txt, ASTERISK_4.concat((String) rsltMap.get("CR_CARD_LAST_DIGIT_NUM")));
            }
            // END 2019/11/27 E.Kameishi [QC#54594, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.xxMthDt_CC, (String) rsltMap.get("CR_CARD_EXPR_MTH"));
            ZYPEZDItemValueSetter.setValue(cMsg.xxYrDt_CC, (String) rsltMap.get("CR_CARD_EXPR_YR"));
            ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscCd, (String) rsltMap.get("PMT_TERM_CASH_DISC_CD"));
            // START 2016/02/09 T.Aoyagi [QC4081, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscDescTxt, (String) rsltMap.get("PMT_TERM_CASH_DISC_DESC_TXT"));
            // END 2016/02/09 T.Aoyagi [QC4081, ADD]
            // Renewal
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrRnwEsclPk, (BigDecimal) rsltMap.get("DS_CONTR_RNW_ESCL_PK"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_S, (String) rsltMap.get("EZUPTIME_S"));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_S, (String) rsltMap.get("EZUPTIMEZONE_S"));
            ZYPEZDItemValueSetter.setValue(cMsg.contrAutoRnwTpCd, (String) rsltMap.get("CONTR_AUTO_RNW_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.befEndRnwDaysAot, (BigDecimal) rsltMap.get("BEF_END_RNW_DAYS_AOT"));
            ZYPEZDItemValueSetter.setValue(cMsg.contrRnwTotCnt, (BigDecimal) rsltMap.get("CONTR_RNW_TOT_CNT"));
            ZYPEZDItemValueSetter.setValue(cMsg.rnwPrcMethCd, (String) rsltMap.get("RNW_PRC_METH_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.basePrcUpRatio, (BigDecimal) rsltMap.get("BASE_PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(cMsg.mtrPrcUpRatio, (BigDecimal) rsltMap.get("MTR_PRC_UP_RATIO"));
            // Entitlement Details
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt, (String) rsltMap.get("CONTR_VRSN_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffFromDt_BK, (String) rsltMap.get("CONTR_VRSN_EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt, (String) rsltMap.get("CONTR_VRSN_EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.contrVrsnEffThruDt_BK, (String) rsltMap.get("CONTR_VRSN_EFF_THRU_DT"));
            // START 2022/10/03 N.Takatsu[QC#60186, MOD]
            // ZYPEZDItemValueSetter.setValue(cMsg.mtrEstTpCd, (String) rsltMap.get("MTR_EST_TP_CD"));
            if (rsltMap.get("MTR_EST_TP_CD") != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.mtrEstTpCd, (String) rsltMap.get("MTR_EST_TP_CD"));
            }
            // END 2022/10/03 N.Takatsu[QC#60186, MOD]
            ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg_UT, NSAL0300CommonLogic.switchFlg((String) rsltMap.get("INV_SEPT_BASE_USG_FLG")));
            ZYPEZDItemValueSetter.setValue(cMsg.prcAllocByMachQtyFlg, (String) rsltMap.get("PRC_ALLOC_BY_MACH_QTY_FLG"));

            // mod start 2016/07/21 CSA Defect#11720
            // mod start 2015/12/23 CSA Defect#1824
            BigDecimal contrDurnAot = (BigDecimal) rsltMap.get("CONTR_DURN_AOT");
            String bllgCycleUomCd = (String) rsltMap.get("BLLG_CYCLE_UOM_CD");
            if (ZYPCommonFunc.hasValue(contrDurnAot) && ZYPCommonFunc.hasValue(bllgCycleUomCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.contrDurnAot, contrDurnAot);
                ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleUomCd, bllgCycleUomCd);
                copyDtlEffDate(cMsg, sMsg);
            } else {
                // Call duration calculator
                doProcess_NSAL0300Scrn00_ChangeDurationDate(cMsg, sMsg);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.contrDurnAot_BK, cMsg.contrDurnAot);
            ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleUomCd_BK, cMsg.bllgCycleUomCd);
            // mod end 2015/12/23 CSA Defect#1824
            // mod end 2016/07/21 CSA Defect#11720

            ZYPEZDItemValueSetter.setValue(cMsg.svcPgmMdseCd, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
            // add start 2016/07/01 CSA Defect#11261
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_SP, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
            // add end 2016/07/01 CSA Defect#11261
            ZYPEZDItemValueSetter.setValue(cMsg.baseBllgCycleCd, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgCycleCd, (String) rsltMap.get("MTR_BLLG_CYCLE_CD"));
            // Upliftment
            ZYPEZDItemValueSetter.setValue(cMsg.contrUplftTpCd, (String) rsltMap.get("CONTR_UPLFT_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.uplftPrcMethCd, (String) rsltMap.get("UPLFT_PRC_METH_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.uplftBasePrcUpRatio, (BigDecimal) rsltMap.get("UPLFT_BASE_PRC_UP_RATIO"));
            ZYPEZDItemValueSetter.setValue(cMsg.uplftMtrPrcUpRatio, (BigDecimal) rsltMap.get("UPLFT_MTR_PRC_UP_RATIO"));
            //
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCratDt, (String) rsltMap.get("DS_CONTR_CRAT_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCratPsnCd, (String) rsltMap.get("DS_CONTR_CRAT_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrLastUpdDt, (String) rsltMap.get("DS_CONTR_LAST_UPD_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrLastUpdPsnCd, (String) rsltMap.get("DS_CONTR_LAST_UPD_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrApvlDt, (String) rsltMap.get("DS_CONTR_APVL_DT"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrApvlPsnCd, (String) rsltMap.get("DS_CONTR_APVL_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrBllgStsCd, (String) rsltMap.get("DS_CONTR_BLLG_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.rnwPerMth, (String) rsltMap.get("RNW_PER_MTH"));

            // START 2016/05/09 T.Kanasaka [QC#6798, ADD]
            NSAL0300CommonLogic.setRnwFlg(glblCmpyCd, cMsg);
            NSAL0300CommonLogic.setUplftFlg(glblCmpyCd, cMsg);
            // END 2016/05/09 T.Kanasaka [QC#6798, ADD]

            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_EC, IMG_CLOSE_ARROW);
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_EC, (String) rsltMap.get("DS_ACCT_NM"));
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_EC, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));

            dsContrCatgCd = cMsg.dsContrCatgCd.getValue();
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_FA, IMG_CLOSE_ARROW);
            }

            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_M, IMG_CLOSE_MACHINE_ALL);
            }
            // START 2022/03/22 [QC#59683, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.dsInvTgtrTpCd, (String) rsltMap.get("DS_INV_TGTR_TP_CD"));
            NSAL0300CommonLogic.editDsInvTgtrTpPullDownList(glblCmpyCd, cMsg);
            // END   2022/03/22 [QC#59683, ADD]
            // START 2023/02/14 R.Takau [QC#55645,ADD]
            BigDecimal dsCustBankAcctRelnPk = (BigDecimal) rsltMap.get("DS_CUST_BANK_ACCT_RELN_PK");
            if(ZYPCommonFunc.hasValue(dsCustBankAcctRelnPk)){
                ZYPEZDItemValueSetter.setValue(cMsg.bankRteNum, (String) rsltMap.get("BANK_RTE_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsBankAcctNum, (String) rsltMap.get("MASK_BANK_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.dsCustBankAcctRelnPk, (BigDecimal) rsltMap.get("DS_CUST_BANK_ACCT_RELN_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_CP, (String) rsltMap.get("EZUPTIME_CP"));
                ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_CP, (String) rsltMap.get("EZUPTIMEZONE_CP"));
            }
            // END  2023/02/14 R.Takau [QC#55645,ADD]
        }

        rslt = query.getDsContrDtlInfo1(glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrCatgCd.getValue(), sMsg.A.length() + 1);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > sMsg.A.length()) {
                rsltCnt = sMsg.A.length();
                cMsg.setMessageInfo(NZZM0001W);
            }

            int sqNum = 0;
            int sqSubNum = 0;
            BigDecimal dsContrDtlPk = null;

            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_A, (String) rsltMap.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_A, (String) rsltMap.get("EZUPTIMEZONE"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrDtlPk_A, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntDsContrDtlPk_A, (BigDecimal) rsltMap.get("PRNT_DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNum_A, (String) rsltMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A, (String) rsltMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdlNm_A, (String) rsltMap.get("MDL_NM"));

                String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
                String secondLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
                String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
                String fourthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
                String ctyAddr = (String) rsltMap.get("CTY_ADDR");
                String stCd = (String) rsltMap.get("ST_CD");
                String postCd = (String) rsltMap.get("POST_CD");
                String addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).billToCustLocAddr_A, addr);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrDtlTpCd_A, (String) rsltMap.get("DS_CONTR_DTL_TP_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrDtlStsCd_A, (String) rsltMap.get("DS_CONTR_DTL_STS_CD"));
                // Mod Start 2018/01/18 QC#18552
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrDtlStsDescTxt_A, (String) rsltMap.get("DS_CONTR_CTRL_STS_NM"));
                // Mod End 2018/01/18 QC#18552
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffFromDt_A, (String) rsltMap.get("CONTR_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrEffThruDt_A, (String) rsltMap.get("CONTR_EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).baseBllgCycleCd_A, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).basePrcDealAmt_A, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mtrReadMethCd_A, (String) rsltMap.get("MTR_READ_METH_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rnwEffFromDt_A, (String) rsltMap.get("RNW_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrRnwTotCnt_A, (BigDecimal) rsltMap.get("CONTR_RNW_TOT_CNT"));
                // START 2016/02/23 T.Kanasaka [QC3885, ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).contrCloDt_A, (String) rsltMap.get("CONTR_CLO_DT"));
                // END 2016/02/23 T.Kanasaka [QC3885, ADD]
                // Add Start 2018/01/10 QC#18552
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcPgmMdseCd_A, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_AS, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prcMtrPkgPk_A, (BigDecimal) rsltMap.get("PRC_MTR_PKG_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prcMtrPkgNm_A, (String) rsltMap.get("PRC_MTR_PKG_NM"));
                // Add End 2018/01/10 QC#18552

                String asryFlg = (String) rsltMap.get("ASRY_FLG");
                if (ZYPConstant.FLG_ON_Y.equals(asryFlg)) {
                    sqNum++;
                    sqSubNum = 0;
                    dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
                } else if (DS_CONTR_DTL_TP.ACCESSORIES.equals(sMsg.A.no(i).dsContrDtlTpCd_A.getValue()) && ZYPCommonFunc.hasValue(dsContrDtlPk) && ZYPCommonFunc.hasValue(sMsg.A.no(i).prntDsContrDtlPk_A) && dsContrDtlPk.compareTo(sMsg.A.no(i).prntDsContrDtlPk_A.getValue()) == 0) {
                    sqSubNum++;
                } else {
                    sqNum++;
                    sqSubNum = 0;
                    dsContrDtlPk = null;
                }

                String sqId = String.valueOf(sqNum);
                if (sqSubNum != 0) {
                    sqId += PERIOD + String.valueOf(sqSubNum);
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).sqId_A, sqId);

                // Mod Start 10/21/2016 <QC#15146>
                if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_CLOSE_MACHINE_GREEN);
                } else if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                    String xxFilePathTxt = IMG_CLOSE_MACHINE_GREEN;
                    String baseChrgFlg = (String) rsltMap.get("BASE_CHRG_FLG");
                    String usgChrgFlg = (String) rsltMap.get("USG_CHRG_FLG");
                    String basePrcDtlFlg = (String) rsltMap.get("BASE_PRC_DTL_FLG");
                    String usgPrcDtlFlg = (String) rsltMap.get("USG_PRC_DTL_FLG");
                    if (DS_CONTR_DTL_STS.CANCELLED.equals(sMsg.A.no(i).dsContrDtlStsCd_A.getValue())) {
                        xxFilePathTxt = IMG_CLOSE_MACHINE_RED;
                    } else if (ZYPConstant.FLG_ON_Y.equals(baseChrgFlg) && !ZYPConstant.FLG_ON_Y.equals(basePrcDtlFlg)) {
                        xxFilePathTxt = IMG_CLOSE_MACHINE_RED;
                    } else if (ZYPConstant.FLG_ON_Y.equals(usgChrgFlg) && !ZYPConstant.FLG_ON_Y.equals(usgPrcDtlFlg)) {
                        xxFilePathTxt = IMG_CLOSE_MACHINE_RED;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, xxFilePathTxt);
                }
                // Mod End   10/21/2016 <QC#15146>
            }
            sMsg.A.setValidCount(rsltCnt);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_EC, IMG_OPEN_ARROW);
            cMsg.dsAcctNm_EC.clear();
            cMsg.mdseDescShortTxt_EC.clear();
        }

        NSAL0300CommonLogic.searchDetailForB(glblCmpyCd, cMsg);

        // add start 2016/06/21 CSA Defect#6923
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_RN, ZYPConstant.FLG_OFF_N);
        rslt = query.getContrAutoRnwTpCd(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            String renewTpCd = null;
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                renewTpCd = (String) rsltMap.get("CONTR_AUTO_RNW_TP_CD");
                //START 2024/02/26 K.Ogasawara [QC#63550,MOD]
//              if (CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(renewTpCd)
//                      || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_ONLY.equals(renewTpCd)
//                      || CONTR_AUTO_RNW_TP.MANUAL_RENEW_OVERAGE_ONLY.equals(renewTpCd)
                if (CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_OVERAGE.equals(renewTpCd)
                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_BASE_ONLY.equals(renewTpCd)
                        || CONTR_AUTO_RNW_TP.MANUAL_RENEW_OVERAGE_ONLY.equals(renewTpCd)
                        || CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_OVERAGE.equals(renewTpCd)
                        || CONTR_AUTO_RNW_TP.AUTO_RENEW_BASE_ONLY.equals(renewTpCd)
                        || CONTR_AUTO_RNW_TP.AUTO_RENEW_OVERAGE_ONLY.equals(renewTpCd)) {
                //END 2024/02/26 K.Ogasawara [QC#63550,MOD]
                    ZYPEZDItemValueSetter.setValue(cMsg.xxDplyCtrlFlg_RN, ZYPConstant.FLG_ON_Y);
                    break;
                }
            }
        }
        // add end 2016/06/21 CSA Defect#6923

        NSAL0300CommonLogic.setDisplayMode(getGlobalCompanyCode(), cMsg, sMsg);

        // Restore display controll
        NSAL0300CommonLogic.restoreDisplay(cMsg, sMsg, displayBean);

        NSAL0300CommonLogic.paginateTableAToItem(cMsg, sMsg, NSAL0300CommonLogic.convertIndexFromNeedCount(sMsg, displayBean.getPageFromNum().intValue()));

        // START 2017/08/29 K.Kojima [QC#20057,ADD]
        if ("NSAL0300Scrn00_CMN_Save".equals(cMsg.getScreenAplID())) {
            boolean msgFlag = false;
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                String key = getMapKey(cMsg.A.no(i));
                if (key == null) {
                    continue;
                }
                String baseBllgCycle = baseBllgCycleMap.get(key);
                BigDecimal basePrcDealAmt = basePrcDealAmtMap.get(key);
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).baseBllgCycleCd_A) && ZYPCommonFunc.hasValue(baseBllgCycle)) {
                    if (!cMsg.A.no(i).baseBllgCycleCd_A.getValue().equals(baseBllgCycle)) {
                        msgFlag = true;
                        break;
                    }
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).basePrcDealAmt_A) && ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                    if (cMsg.A.no(i).basePrcDealAmt_A.getValue().compareTo(basePrcDealAmt) != 0) {
                        msgFlag = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                String key = getMapKey(cMsg.B.no(i));
                if (key == null) {
                    continue;
                }
                String baseBllgCycle = baseBllgCycleMap.get(key);
                BigDecimal basePrcDealAmt = basePrcDealAmtMap.get(key);
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).baseBllgCycleCd_B) && ZYPCommonFunc.hasValue(baseBllgCycle)) {
                    if (!cMsg.B.no(i).baseBllgCycleCd_B.getValue().equals(baseBllgCycle)) {
                        msgFlag = true;
                        break;
                    }
                }
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).basePrcDealAmt_B) && ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                    if (cMsg.B.no(i).basePrcDealAmt_B.getValue().compareTo(basePrcDealAmt) != 0) {
                        msgFlag = true;
                        break;
                    }
                }
            }
            if (msgFlag) {
                StringBuffer msg = new StringBuffer();
                msg.append("Your change request has been applied to the future billing schedule.");
                msg.append("This screen shows infomation for the current Price Effectivitiy.");
                msg.append("Please click on the PE button to confirm all billing schedule.");
                ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem500Txt, msg.toString());
            }
        }
        // END 2017/08/29 K.Kojima [QC#20057,ADD]
    }

    // START 2017/08/29 K.Kojima [QC#20057,ADD]
    private String getMapKey(NSAL0300_ACMsg acMsg) {
        String key = null;
        if (ZYPCommonFunc.hasValue(acMsg.svcMachMstrPk_A)) {
            key = acMsg.svcMachMstrPk_A.getValue().toString();
        }
        return key;
    }

    private String getMapKey(NSAL0300_BCMsg bcMsg) {
        String key = null;
        if (DS_CONTR_DTL_TP.FLEET.equals(bcMsg.dsContrDtlTpCd_B.getValue())) {
            key = DS_CONTR_DTL_TP.FLEET;
        } else if (DS_CONTR_DTL_TP.AGGREGATE.equals(bcMsg.dsContrDtlTpCd_B.getValue())) {
            key = DS_CONTR_DTL_TP.AGGREGATE;
        }
        return key;
    }

    // END 2017/08/29 K.Kojima [QC#20057,ADD]

    @SuppressWarnings("unchecked")
    private void searchSerial(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        NSAL0300Query query = NSAL0300Query.getInstance();
        cMsg.svcMachMstrPk_M.clear();
        cMsg.mdseCd_M.clear();
        S21SsmEZDResult rslt = query.getMdseBySerNum(glblCmpyCd, cMsg.serNum.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrPk_M.no(i), (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_M.no(i), (String) rsltMap.get("MDSE_CD"));
            }
        } else {
            cMsg.serNum.setErrorInfo(1, NSAM0128E);
            cMsg.setMessageInfo(NSAM0128E);
        }
    }

    // START 2016/05/27 [QC#447, ADD]
    private boolean checkEOL(NSAL0300CMsg cMsg) {
        EndOfLifeBean infoBean = new EndOfLifeBean();
        infoBean.setGlblCmpyCd(getGlobalCompanyCode());
        infoBean.setSvcMachMstrPk(cMsg.svcMachMstrPk.getValue());
        infoBean.setEolDt(cMsg.contrVrsnEffFromDt.getValue());

        if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
            cMsg.serNum.setErrorInfo(1, infoBean.getXxMsgId());
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
            cMsg.serNum.setErrorInfo(1, NSAM0478E, new String[] {cMsg.serNum.getValue() });
            return false;
        }

        return true;
    }
    // END   2016/05/27 [QC#447, ADD]

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    private void searchShipTo(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
            int idx = cMsg.xxRowNum.getValueInt();
            if (ZYPCommonFunc.hasValue((cMsg.B.no(idx).shipToCustCd_B))) {
                String glblCmpyCd = getGlobalCompanyCode();
                NSAL0300Query query = NSAL0300Query.getInstance();
                SHIP_TO_CUSTTMsg tMsg = query.getShipToCust(glblCmpyCd, cMsg.B.no(idx).shipToCustCd_B.getValue());
                if (tMsg == null) {
                    cMsg.B.no(idx).shipToCustCd_B.setErrorInfo(1, NSAM0072E, new String[] {"Ship To Location" });
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(idx).shipToLocNm_B, tMsg.locNm);
                }
            } else {
                cMsg.B.no(idx).shipToLocNm_B.clear();
            }
        }
    }
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

 // START 2017/01/24 N.Arai [QC#17228, MOD]
    private void doProcess_NSAL0300_NSAL0420(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (ZYPCommonFunc.hasValue((cMsg.contrAdminPsnCd))) {

            String repName = NSAL0300Query.getInstance().findRepNameInfoFromPsnCd(getGlobalCompanyCode(), cMsg.contrAdminPsnCd.getValue());
            if (ZYPCommonFunc.hasValue(repName)) {
                setValue(cMsg.xxPsnNm, repName);
            } else {
                cMsg.contrAdminPsnCd.clear();
                cMsg.xxPsnNm.clear();
            }
        }
    }
 // END 2017/01/24 N.Arai [QC#17228, MOD]

 // START 2017/02/02 N.Arai [QC#17197, MOD]
    /**
     * do process (DownloadMachine)
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     */
    private void doProcess_NSAL0300Scrn00_DownloadMachine(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        // create csv file
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BIZ_ID + "_" + MACHINE_DETAILS + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0300CommonLogic.createMachineDetailsCsvFile(cMsg, sMsg, getGlobalCompanyCode());
    }
// END 2017/02/02 N.Arai [QC#17197, MOD]
    // Add Start 2017/12/21 QC#18779
    private void doProcess_NSAL0300Scrn00_ChangeBaseBllgTmgCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();

        // Select Line
        int selectRowNum = cMsg.xxRowNum_B.getValueInt();
        NSAL0300_BCMsg targetBCMsg = cMsg.B.no(selectRowNum);

        String bllgTmgTpCd = targetBCMsg.baseBllgTmgCd_B.getValue();
        String contrBllgDay = targetBCMsg.contrBllgDay_B.getValue();
        String defContrInvDay = "0";
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = NSAL0300Query.getInstance().getDsContrIntfcDefRule(glblCmpyCd, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
        if (dsContrIntfcDefRuleTMsg != null) {
            defContrInvDay = dsContrIntfcDefRuleTMsg.contrBllgDay.getValue();
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                targetBCMsg = cMsg.B.no(i);
                setValue(targetBCMsg.baseBllgTmgCd_B, bllgTmgTpCd);
                setValue(targetBCMsg.contrBllgDay_B, contrBllgDay);
                changeContrBllgDayPullDown(glblCmpyCd, targetBCMsg, defContrInvDay);
            }
        } else {
            changeContrBllgDayPullDown(glblCmpyCd, targetBCMsg, defContrInvDay);
        }
    }

    private void changeContrBllgDayPullDown(String glblCmpyCd, NSAL0300_BCMsg targetBCMsg, String defContrInvDay) {
        // Pulldown Set
        NSAL0300CommonLogic.createContrBllgDayPullDownList(glblCmpyCd, targetBCMsg.baseBllgTmgCd_B.getValue(), ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N, targetBCMsg.contrBllgDay_BB, targetBCMsg.xxEdtDescTxt_BB);
        if (!hasValue(targetBCMsg.contrBllgDay_B)) {
            // default set
            setValue(targetBCMsg.contrBllgDay_B, defContrInvDay);
            return;
        }

        boolean existsBllgDay = false;
        for (int i = 0; i < targetBCMsg.contrBllgDay_BB.length(); i++) {
            if (!hasValue(targetBCMsg.contrBllgDay_BB.no(i))) {
                continue;
            }
            if (targetBCMsg.contrBllgDay_B.getValue().equals(targetBCMsg.contrBllgDay_BB.no(i).getValue())) {
                existsBllgDay = true;
                break;
            }
        }

        if (!existsBllgDay) {
            // default set
            setValue(targetBCMsg.contrBllgDay_B, defContrInvDay);
        }
    }
    // Add End 2017/12/21 QC#18779

    // START 2018/11/06 S.Kitamura [QC#28868,ADD]
    private void outputInitLog(NSAL0300CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0300 Init Condition : ");
        sb.append(" Contract#[").append(cMsg.dsContrPk.getValue()).append("]");
        S21InfoLogOutput.println(sb.toString());
    }
    private void outputSearchLog(NSAL0300CMsg cMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0300 Search Condition : ");
        if (hasValue(cMsg.dsContrPk)) {
            sb.append(" Contract#[").append(cMsg.dsContrPk.getValue()).append("]");
        } else if (hasValue(cMsg.dsContrNum)) {
            sb.append(" Contract#[").append(cMsg.dsContrNum.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/11/06 S.Kitamura [QC#28868,ADD]

    // START 2022/03/22 [QC#59683, ADD]
    private void doProcess_NSAL0300Scrn00_ChangeDsContrCatgCd(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String globalCompanyCode = getGlobalCompanyCode();
        NSAL0300CommonLogic.editDsInvTgtrTpPullDownList(globalCompanyCode, cMsg);
        String dsContrCatgCd = cMsg.dsContrCatgCd.getValue();
        if (hasValue(dsContrCatgCd) && DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
            setValue(cMsg.dsInvTgtrTpCd, DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY);
            String invSeptBaseUsgFlg = NSAL0300CommonLogic.getInvSeptBaseUsgFlg(globalCompanyCode, cMsg.dsInvTgtrTpCd.getValue());
            setValue(cMsg.xxSelFlg_UT, NSAL0300CommonLogic.switchFlg(invSeptBaseUsgFlg));
        }
    }

    private void doProcess_NSAL0300Scrn00_ChangeBillTogether(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        String globalCompanyCode = getGlobalCompanyCode();
        String invSeptBaseUsgFlg = NSAL0300CommonLogic.getInvSeptBaseUsgFlg(globalCompanyCode, cMsg.dsInvTgtrTpCd.getValue());
        setValue(cMsg.xxSelFlg_UT, NSAL0300CommonLogic.switchFlg(invSeptBaseUsgFlg));
    }
    // END   2022/03/22 [QC#59683, ADD]

    // START 2022/10/05 N.Takatsu[QC#60072, ADD]
    private void doProcess_NSAL0300Scrn00_ExpandMachineAll(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (IMG_CLOSE_MACHINE_ALL.equals(cMsg.xxFilePathTxt_M.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_M, IMG_OPEN_MACHINE_ALL);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (IMG_CLOSE_MACHINE_RED.equals(cMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_RED);
                } else if (IMG_CLOSE_MACHINE_GREEN.equals(cMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_GREEN);
                }
            }
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (IMG_CLOSE_MACHINE_RED.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_RED);
                } else if (IMG_CLOSE_MACHINE_GREEN.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_GREEN);
                }
                BigDecimal dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
                for (int bIdx = 0; bIdx < cMsg.B.getValidCount(); bIdx++) {
                    if (cMsg.B.no(bIdx).dsContrDtlPk_B.getValue().compareTo(dsContrDtlPk) == 0) {
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(bIdx).xxFilePathTxt_BB, IMG_CLOSE_ARROW);
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(bIdx).xxFilePathTxt_BM, IMG_OPEN_ARROW);
                    }
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_M, IMG_CLOSE_MACHINE_ALL);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (IMG_OPEN_MACHINE_RED.equals(cMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxFilePathTxt_A, IMG_CLOSE_MACHINE_RED);
                } else if (IMG_OPEN_MACHINE_GREEN.equals(cMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxFilePathTxt_A, IMG_CLOSE_MACHINE_GREEN);
                }
            }
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (IMG_OPEN_MACHINE_RED.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_CLOSE_MACHINE_RED);
                } else if (IMG_OPEN_MACHINE_GREEN.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_CLOSE_MACHINE_GREEN);
                }
            }
        }
    }
    // END 2022/10/05 N.Takatsu[QC#60072, ADD]
    
    // START 2022/10/07 N.Takatsu[QC#60071, ADD]
    private boolean filterNum(NSAL0300SMsg sMsg, String filterCondition, String filterValue, String itemValue) {
        BigDecimal itemValueBigDecimal = filterSeqNumberMin(itemValue);
        BigDecimal filterValueBigDecimal = null;
        if (FILTER_CONDITION_GREATER_THAN.equals(filterCondition)) {
            filterValueBigDecimal = filterSeqNumberMin(filterValue);
            if (!(itemValueBigDecimal.compareTo(filterValueBigDecimal) > 0)) {
                return true;
            }
        } else if (FILTER_CONDITION_LESS_THAN.equals(filterCondition)) {
            filterValueBigDecimal = filterSeqNumberMin(filterValue);
            if (!(itemValueBigDecimal.compareTo(filterValueBigDecimal) < 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean filterFromTo(NSAL0300SMsg sMsg, String filterValue, String itemValue) {
        BigDecimal itemValueBigDecimal = null;
        BigDecimal filterValueBigDecimalFrom = null;
        BigDecimal filterValueBigDecimalTo = null;
        String[] itemFilterValue = filterValue.split((TXT_HYPHEN), -1);

        if (itemFilterValue.length == 1) {
            if (itemFilterValue[0].contains(PERIOD)) {
                if (!itemValue.equals(filterValue)) {
                    return true;
                }
            } else {
                itemValueBigDecimal = filterSeqNumberMin(itemValue);
                filterValueBigDecimalFrom = new BigDecimal(itemFilterValue[0]);
                if (!itemValueBigDecimal.setScale(0, BigDecimal.ROUND_DOWN).equals(filterValueBigDecimalFrom)) {
                    return true;
                }
            }
        } else if (itemFilterValue.length == 2 && "".equals(itemFilterValue[1])) {
            itemValueBigDecimal = filterSeqNumberMin(itemValue);
            filterValueBigDecimalFrom = filterSeqNumberMin(itemFilterValue[0]);
            if (!(itemValueBigDecimal.compareTo(filterValueBigDecimalFrom) >= 0)) {
                return true;
            }
        } else if (itemFilterValue.length == 2) {
            itemValueBigDecimal = filterSeqNumberMin(itemValue);
            filterValueBigDecimalFrom = filterSeqNumberMin(itemFilterValue[0]);
            filterValueBigDecimalTo = filterSeqNumberMax(itemFilterValue[1]);
            if (!(itemValueBigDecimal.compareTo(filterValueBigDecimalFrom) >= 0) || !(itemValueBigDecimal.compareTo(filterValueBigDecimalTo) <= 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean filterSeqNumbercheck(String itemFilterValue) {
        String[] itemFilterValueList = itemFilterValue.split(Pattern.quote(PERIOD), -1);
        if (itemFilterValueList.length == 2 && itemFilterValueList[1].length() > 3) {
            return true;
        }
        return false;
    }

    private BigDecimal filterSeqNumberMin(String itemFilterValue) {
        String[] itemFilterValueList = itemFilterValue.split(Pattern.quote(PERIOD), -1);
        String intStr = itemFilterValueList[0];
        String decStr = DEC_000;
        String rsltStr = null;
        if (itemFilterValueList.length == 2) {
            String wkStr = DEC_000 + itemFilterValueList[1];
            decStr = wkStr.substring(wkStr.length() - 3);
        }
        rsltStr = intStr + PERIOD + decStr;
        BigDecimal filterValueBigDecimal = new BigDecimal(rsltStr);
        return filterValueBigDecimal;
    }

    private BigDecimal filterSeqNumberMax(String itemFilterValue) {
        String[] itemFilterValueList = itemFilterValue.split(Pattern.quote(PERIOD), -1);
        String intStr = itemFilterValueList[0];
        String decStr = DEC_999;
        String rsltStr = null;
        if (itemFilterValueList.length == 2) {
            String wkStr = DEC_000 + itemFilterValueList[1];
            decStr = wkStr.substring(wkStr.length() - 3);
        }
        rsltStr = intStr + PERIOD + decStr;
        BigDecimal filterValueBigDecimal = new BigDecimal(rsltStr);
        return filterValueBigDecimal;
    }

    private boolean rangeCheckFormat(String filterItem, String filterCondition, String filterValue) {
        if (filterValue.matches(RANGE_CHECK)) {
            String[] hyphenCheck = filterValue.split(TXT_HYPHEN, -1);
            if (hyphenCheck.length == 2) {
                String[] periodCheck0 = hyphenCheck[0].split(Pattern.quote(PERIOD), -1);
                String[] periodCheck1 = hyphenCheck[1].split(Pattern.quote(PERIOD), -1);
                if (periodCheck0.length > 2 || periodCheck1.length > 2) {
                    return true;
                } else if (periodCheck0.length >= 1) {
                    return false;
                }
            } else if (hyphenCheck.length == 1) {
                String[] periodCheck = hyphenCheck[0].split(Pattern.quote(PERIOD), -1);
                if (periodCheck.length > 2) {
                    return true;
                } else if (periodCheck.length >= 1) {
                    return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    private boolean simpleSubstanceCheckFormat(String filterItem, String filterValue) {
        if (filterValue.matches(SIMPLE_SUBSTANCE_CHECK)) {
            String[] periodCheck = filterValue.split(Pattern.quote(PERIOD), -1);
            if (periodCheck.length > 2) {
                return true;
            } else if (periodCheck.length >= 1) {
                return false;
            }
        }
        return true;
    }
    // END 2022/10/07 N.Takatsu[QC#60071, ADD]
    
    //START 2023/02/03 R.Takau [QC#55645,ADD]
    private void doProcess_NSAL0300_NFCL3170(NSAL0300CMsg cMsg, EZDSMsg sMsg) {
        String glblCmpyCd = getGlobalCompanyCode();
        if(ZYPCommonFunc.hasValue(cMsg.dsCustBankAcctRelnPk)){
            Map<String, Object> resultMap = NSAL0300Query.getInstance().getBankInfo(glblCmpyCd,cMsg.dsCustBankAcctRelnPk.getValue().toString());
            if(resultMap != null){
                ZYPEZDItemValueSetter.setValue(cMsg.bankRteNum, resultMap.get("BANK_RTE_NUM").toString());
                ZYPEZDItemValueSetter.setValue(cMsg.dsBankAcctNum, resultMap.get("MASK_BANK_ACCT_NUM").toString());
            }
        }
    }
    //END 2023/02/03 R.Takau [QC#55645,ADD]
    //START 2024/03/22 Y.Tamai [QC#63463,ADD]
    private void doProcess_NSAL0300Scrn00_Revert(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (!NSAL0300CommonLogic.hasErrorOrWarn(cMsg)) {
            search(cMsg, sMsg);
            NSAL0300CommonLogic.setDefaultSummaryDetailMode(cMsg);
        }
    }
    // END 2024/03/22 Y.Tamai [QC#63463,ADD]
}
