/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300.common;

import static business.blap.NSAL0300.constant.NSAL0300Constant.BIZ_ID;
import static business.blap.NSAL0300.constant.NSAL0300Constant.COMMA;
import static business.blap.NSAL0300.constant.NSAL0300Constant.CUM_COPY_FREQ_MTH;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DATE_FORMAT_FULL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_BASE_BLLG_TMG_TP_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_BLLG_TMG_TP_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_CONTR_UPLFT_TERM_AOT;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DEF_MTR_BLLG_TMG_TP_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DETAIL_MODE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DISPLAY_MODE_FREEZE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DISPLAY_MODE_UPDATE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.DUMMY_EML_ADDR;
import static business.blap.NSAL0300.constant.NSAL0300Constant.END_OF_CURRENT_MONTH;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_CONDITION_EQUAL;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FILTER_ITEM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FMT_MTR_MULT_RATE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.FUNC_CATG_ID;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_ARROW;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_MACHINE_GREEN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_CLOSE_MACHINE_RED;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_ARROW;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_MACHINE_GREEN;
import static business.blap.NSAL0300.constant.NSAL0300Constant.IMG_OPEN_MACHINE_RED;
import static business.blap.NSAL0300.constant.NSAL0300Constant.INIT_DAY;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LAST_DAY;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LAST_DAY_OF_A_MONTH;
import static business.blap.NSAL0300.constant.NSAL0300Constant.LIMIT_DOWNLOAD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAP_KEY_DS_CONTR_DTL_PK;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAP_KEY_MDSE_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAP_KEY_SER_NUM;
import static business.blap.NSAL0300.constant.NSAL0300Constant.MAX_LENGTH_COUNTER_NAME;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0031E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0033E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0045E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0072E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0189E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0418E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0697E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0698E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0727E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0728E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0729E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0734E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0735E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NSZM0665E;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT;
import static business.blap.NSAL0300.constant.NSAL0300Constant.NZZM0001W;
import static business.blap.NSAL0300.constant.NSAL0300Constant.PERIOD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.SUMMARY_MODE;
import static business.blap.NSAL0300.constant.NSAL0300Constant.WTY_CONTR_CATG_CD;
import static business.blap.NSAL0300.constant.NSAL0300Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.blap.NSAL0300.NSAL0300Query;
import business.blap.NSAL0300.NSAL0300SMsg;
import business.blap.NSAL0300.NSAL0300_ACMsg;
import business.blap.NSAL0300.NSAL0300_ACMsgArray;
import business.blap.NSAL0300.NSAL0300_ASMsg;
import business.blap.NSAL0300.NSAL0300_ASMsgArray;
import business.blap.NSAL0300.NSAL0300_BCMsg;
import business.blap.NSAL0300.NSAL0300_BCMsgArray;
import business.blap.NSAL0300.NSAL0300_ZCMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_AUTO_RNW_TPTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_UPLFT_TPTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.CR_CARD_TRXTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.db.DS_CONTR_STS_VTMsg;
import business.db.DS_INV_TGTR_TPTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.DS_SUB_CONTR_MTRTMsg;
import business.db.DS_SUB_CONTR_MTRTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.file.NSAL0300F01FMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047010PMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC047021PMsg;
import business.parts.NSZC077001PMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001DefaultSvcPgmGetter;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_BLLG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INV_TGTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RNW_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_MERGE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PGM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.UPLFT_PRC_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/11/09   Hitachi         T.Kanasaka      Update          QC493,QC497
 * 2015/11/13   Hitachi         T.Kanasaka      Update          QC663
 * 2015/11/19   Hitachi         T.Kanasaka      Update          QC876,QC889
 * 2015/11/24   Hitachi         T.Kanasaka      Update          QC564,QC1078
 * 2015/11/30   Hitachi         A.Kohinata      Update          QC1165,QC1197
 * 2015/12/01   Hitachi         A.Kohinata      Update          QC1295
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC1313
 * 2015/12/02   Hitachi         A.Kohinata      Update          QC910
 * 2015/12/03   Hitachi         T.Kanasaka      Update          QC1454
 * 2015/12/07   Hitachi         A.Kohinata      Update          QC1215
 * 2015/12/08   Hitachi         T.Kanasaka      Update          QC1227
 * 2015/12/09   Hitachi         T.Kanasaka      Update          QC814,QC815,QC1472,QC896
 * 2015/12/11   Hitachi         T.Kanasaka      Update          QC1757,QC1907
 * 2015/12/14   Hitachi         T.Kanasaka      Update          QC1889
 * 2015/12/16   Hitachi         T.Kanasaka      Update          QC2065
 * 2016/01/15   Hitachi         T.Tomita        Update          QC#3016
 * 2016/01/18   Hitachi         T.Tomita        Update          QC#2948
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/02/08   Hitachi         T.Tomita        Update          QC#3721
 * 2016/02/10   Hitachi         T.Kanasaka      Update          QC3058
 * 2016/02/16   Hitachi         T.Kanasaka      Update          QC2579
 * 2016/02/16   Hitachi         T.Aoyagi        Update          QC2947
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC3023
 * 2016/02/18   Hitachi         T.Aoyagi        Update          QC3700
 * 2016/02/24   Hitachi         T.Kanasaka      Update          QC4079
 * 2016/02/24   Hitachi         A.Kohinata      Update          QC3697
 * 2016/03/08   Hitachi         T.Kanasaka      Update          QC2208
 * 2016/03/25   Hitachi         T.Kanasaka      Update          QC#6040
 * 2016/04/08   Hitachi         M.Gotou         Update          QC#5312,5313
 * 2016/04/14   Hitachi         T.Kanasaka      Update          QC#3985
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#4668
 * 2016/05/09   Hitachi         T.Kanasaka      Update          QC#6798
 * 2016/05/16   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/06/20   Hitachi         T.Kanasaka      Update          QC#9019
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/05   Hitachi         T.Tomita        Update          QC#10594
 * 2016/07/06   Hitachi         T.Kanasaka      Update          QC#9019
 * 2016/07/07   Hitachi         K.Kishimoto     Update          QC#11466
 * 2016/07/11   Hitachi         T.Kanasaka      Update          QC#9019
 * 2016/08/02   Hitachi         A.Kohinata      Update          QC#4433
 * 2016/08/08   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/06   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/08   Hitachi         A.Kohinata      Update          QC#12429
 * 2016/09/23   Hitachi         K.Yamada        Update          QC#13686
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/21   Hitachi         K.Kishimoto     Update          QC#15146
 * 2016/11/15   Hitachi         T.Kanasaka      Update          QC#15526
 * 2016/11/16   Hitachi         T.Kanasaka      Update          QC#15942
 * 2017/01/04   Hitachi         T.Mizuki        Update          QC#16399
 * 2017/02/02   Hitachi         N.Arai          Update          QC#17197
 * 2017/02/06   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/10   Hitachi         Y.Takeno        Update          QC#17494
 * 2017/02/14   Hitachi         Y.Takeno        Update          QC#17275-1
 * 2017/02/21   Hitachi         K.Kishimoto     Update          QC#17646
 * 2017/02/28   Hitachi         K.Kishimoto     Update          QC#17809
 * 2017/06/12   Hitachi         N.Arai          Update          QC#18567
 * 2017/06/22   Hitachi         Y.Osawa         Update          QC#17496
 * 2017/06/27   Hitachi         T.Tomita        Update          QC#19562
 * 2017/07/20   Hitachi         M.Kidokoro      Update          QC#4468
 * 2017/07/27   Hitachi         K.Kim           Update          QC#16889
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/09/01   Hitachi         U.Kim           Update          QC#20856
 * 2017/09/11   Hitachi         K.Kojima        Update          QC#18435
 * 2017/09/13   Hitachi         K.Yamada        Update          QC#21062
 * 2017/09/13   Hitachi         K.Kim           Update          QC#19938
 * 2017/09/21   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/09/26   Hitachi         K.Kojima        Update          QC#21221
 * 2017/10/05   Hitachi         K.Kojima        Update          QC#20523
 * 2017/10/10   Hitachi         K.Yamada        Update          QC#21222
 * 2017/10/18   Hitachi         K.Kitachi       Update          QC#21222
 * 2017/11/10   Hitachi         K.Kojima        Update          QC#22435
 * 2017/12/21   Hitachi         T.Tomita        Update          QC#18779
 * 2018/01/15   Hitachi         T.Tomita        Update          QC#18552
 * 2018/04/03   Hitachi         K.Kojima        Update          QC#21056
 * 2018/04/23   Hitachi         K.Kojima        Update          QC#25595
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/06/14   Hitachi         K.Kojima        Update          QC#26615
 * 2018/06/18   Hitachi         K.Kim           Update          QC#25195
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/02   Hitachi         K.Kitachi       Update          QC#26765
 * 2018/07/18   Hitachi         K.Kishimoto     Update          QC#26929
 * 2018/07/18   Hitachi         K.Kishimoto     Update          QC#26859
 * 2018/07/30   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 * 2018/08/21   Hitachi         K.Kishimoto     Update          QC#22821
 * 2018/08/30   Hitachi         A.Kohinata      Update          QC#27961
 * 2018/09/20   Hitachi         K.Kitachi       Update          QC#28328
 * 2018/09/27   Hitachi         K.Kim           Update          QC#27777
 * 2018/10/24   Hitachi         K.Kitachi       Update          QC#28889
 * 2018/12/13   Hitachi         K.Kim           Update          QC#29079
 * 2019/01/17   Hitachi         K.Kim           Update          QC#29782
 * 2019/01/17   CITS            M.Naito         Update          QC#29083
 * 2019/01/21   CITS            T.Wada          Update          QC#29371
 * 2019/01/28   Hitachi         T.Tomita        Update          QC#29083
 * 2019/05/13   Hitachi         K.Fujimoto      Update          QC#31137/50058
 * 2019/11/01   Hitachi         K.Kim           Update          QC#53317
 * 2020/06/25   CITS            T.Wada          Update          QC#55590
 * 2021/10/01   CITS            T.Wada          Update          QC#59240
 * 2022/01/21   CITS            R.Cabral        Update          QC#59502
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2023/08/18   CITS            T.Kojima        Update          QC#60846
 * 2022/10/03   Hitachi         N.Takatsu       Update          QC#60186
 *</pre>
 */
public class NSAL0300CommonLogic {

    /**
     * Reset parameter
     * @param cMsg NSAL0150CMsg
     * @param sMsg NSAL0150SMsg
     */
    public static void resetParameter(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
        String dsContrNum = cMsg.dsContrNum.getValue();
        String dsContrSqNum = cMsg.dsContrSqNum.getValue();

        // add start 2018/08/30 QC#27961
        List<NSAL0300_ZCMsg> zCMsgList = new ArrayList<NSAL0300_ZCMsg>();
        for (int i = 0; i < cMsg.Z.getValidCount(); i++) {
            NSAL0300_ZCMsg zCMsg = new NSAL0300_ZCMsg();
            ZYPEZDItemValueSetter.setValue(zCMsg.billToCustCd_Z, cMsg.Z.no(i).billToCustCd_Z);
            ZYPEZDItemValueSetter.setValue(zCMsg.xxDplyCtrlFlg_Z, cMsg.Z.no(i).xxDplyCtrlFlg_Z);
            zCMsgList.add(zCMsg);
        }
        // add end 2018/08/30 QC#27961

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);

        // add start 2018/08/30 QC#27961
        for (int i = 0; i < zCMsgList.size(); i++) {
            EZDMsg.copy(zCMsgList.get(i), null, cMsg.Z.get(i), null);
        }
        cMsg.Z.setValidCount(zCMsgList.size());
        // add end 2018/08/30 QC#27961

        // START 2016/02/18 T.Aoyagi [QC3700, DEL]
//        ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_SD, DETAIL_MODE);
        // END 2016/02/18 T.Aoyagi [QC3700, DEL]
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, dsContrNum);
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrSqNum, dsContrSqNum);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.X);

        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CATG.class, cMsg.dsContrCatgCd_C, cMsg.dsContrCatgDescTxt_C);
//        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, cMsg.bllgTmgTpCd_T, cMsg.bllgTmgTpDescTxt_T);
        ZYPCodeDataUtil.createPulldownList(MTR_EST_TP.class, cMsg.mtrEstTpCd_E, cMsg.mtrEstTpDescTxt_E);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.baseBllgCycleCd_CB, cMsg.bllgCycleDescTxt_CB);
        deletePulldownList(cMsg.baseBllgCycleCd_CB, cMsg.bllgCycleDescTxt_CB, BLLG_CYCLE.DAILY);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.mtrBllgCycleCd_CU, cMsg.bllgCycleDescTxt_CU);
        deletePulldownList(cMsg.mtrBllgCycleCd_CU, cMsg.bllgCycleDescTxt_CU, BLLG_CYCLE.DAILY);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_BLLG_STS.class, cMsg.dsContrBllgStsCd_S, cMsg.dsContrBllgStsDescTxt_S);
        ZYPCodeDataUtil.createPulldownList(CONTR_AUTO_RNW_TP.class, cMsg.contrAutoRnwTpCd_R, cMsg.contrAutoRnwTpDescTxt_R);
        ZYPCodeDataUtil.createPulldownList(RNW_PRC_METH.class, cMsg.rnwPrcMethCd_M, cMsg.rnwPrcMethDescTxt_M);
        ZYPCodeDataUtil.createPulldownList(CONTR_UPLFT_TP.class, cMsg.contrUplftTpCd_U, cMsg.contrUplftTpDescTxt_U);
        ZYPCodeDataUtil.createPulldownList(UPLFT_PRC_METH.class, cMsg.uplftPrcMethCd_M, cMsg.uplftPrcMethDescTxt_M);

        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CLS.class, cMsg.dsContrClsCd_C, cMsg.dsContrClsDescTxt_C);
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_EDI.class, cMsg.dsContrEdiCd_E, cMsg.dsContrEdiDescTxt_E);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE_UOM.class, cMsg.bllgCycleUomCd_CB, cMsg.bllgCycleUomDescTxt_CB);
        // del start 2016/08/02 CSA Defect#4433
        //ZYPCodeDataUtil.createPulldownList(DS_CONTR_ACT.class, cMsg.dsContrActCd_C, cMsg.dsContrActDescTxt_C);
        // del end 2016/08/02 CSA Defect#4433
        // del start 2016/07/01 CSA Defect#11261
//        createPgmMdseCdPullDownList(cMsg.svcPgmMdseCd_C, cMsg.mdseNm_C);
        // del end 2016/07/01 CSA Defect#11261
        ZYPEZDItemValueSetter.setValue(cMsg.bllgTmgTpCd, DEF_BLLG_TMG_TP_CD);
        ZYPEZDItemValueSetter.setValue(cMsg.baseBllgCycleCd, BLLG_CYCLE.MONTHLY);
        // START 2016/04/14 T.Kanasaka [QC#3985, DEL]
//        ZYPEZDItemValueSetter.setValue(cMsg.mtrBllgCycleCd, BLLG_CYCLE.MONTHLY);
        // END 2016/04/14 T.Kanasaka [QC#3985, DEL]

        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        NSAL0300Query query = NSAL0300Query.getInstance();
        GLBL_CMPYTMsg glblCmpyTMsg = query.getGlblCmpy(glblCmpyCd);
        if (glblCmpyTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.ccyCd, glblCmpyTMsg.stdCcyCd);
        }
        // START 2016/01/15 T.Tomita [QC#3016, ADD]
        // Get Default Warranty DS_CONTR_CLS_CD
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd_WT,ZYPCodeDataUtil.getVarCharConstValue(WTY_CONTR_CATG_CD, glblCmpyCd));
        // END 2016/01/15 T.Tomita [QC#3016, ADD]

        // add start 2016/08/02 CSA Defect#4433
        createDsContrActPulldownList(cMsg, glblCmpyCd);
        // add end 2016/08/02 CSA Defect#4433

        ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_EC, IMG_OPEN_ARROW);

        createFilterPullDownList(cMsg);
        // START 2016/11/15 T.Kanasaka [QC#15526, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_2V, FILTER_CONDITION_EQUAL);
        // END 2016/11/15 T.Kanasaka [QC#15526, ADD]
        // START 2022/03/22 [QC#59683, ADD]
        ZYPCodeDataUtil.createPulldownList(DS_INV_TGTR_TP.class, cMsg.dsInvTgtrTpCd_E, cMsg.dsInvTgtrTpDescTxt_E);
        setValue(cMsg.dsInvTgtrTpCd, DS_INV_TGTR_TP.BILL_ALL_BASE_AND_USAGE_TOGETHER);
        String invSeptBaseUsgFlg = NSAL0300CommonLogic.getInvSeptBaseUsgFlg(glblCmpyCd, cMsg.dsInvTgtrTpCd.getValue());
        setValue(cMsg.xxSelFlg_UT, NSAL0300CommonLogic.switchFlg(invSeptBaseUsgFlg));
        // END   2022/03/22 [QC#59683, ADD]
    	// START 2022/10/03 N.Takatsu[QC#60186, ADD]
        setValue(cMsg.mtrEstTpCd, END_OF_CURRENT_MONTH);
        // END 2022/10/03 N.Takatsu[QC#60186, ADD]
    }

    // add start 2016/08/02 CSA Defect#4433
    private static void createDsContrActPulldownList(NSAL0300CMsg cMsg, String glblCmpyCd) {
        // START 2017/07/20 M.Kidokoro [QC#4468, DEL]
//        DS_CONTR_ACTTMsg tMsg = new DS_CONTR_ACTTMsg();
//        tMsg.setSQLID("001");
//        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        DS_CONTR_ACTTMsgArray tMsgArray = (DS_CONTR_ACTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
//
//        int index = 0;
//        for (int i = 0; i < tMsgArray.length(); i++) {
//            if (hasFuncId(tMsgArray.no(i).bizAppId.getValue())) {
//                cMsg.dsContrActCd_C.no(index).setValue(tMsgArray.no(i).dsContrActCd.getValue());
//                cMsg.dsContrActDescTxt_C.no(index).setValue(tMsgArray.no(i).dsContrActDescTxt.getValue());
//                index++;
//            }
//        }
        // END 2017/07/20 M.Kidokoro [QC#4468, DEL]
        // START 2017/07/20 M.Kidokoro [QC#4468, ADD]
        NSAL0300Query query = NSAL0300Query.getInstance();
        S21SsmEZDResult rslt = query.getDsContrActCd(glblCmpyCd);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            int index = 0;
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, String> valueMap = rsltList.get(i);
                if (hasFuncId(valueMap.get("BIZ_APP_ID"))) {
                    cMsg.dsContrActCd_C.no(index).setValue(valueMap.get("DS_CONTR_ACT_CD"));
                    cMsg.dsContrActDescTxt_C.no(index).setValue(valueMap.get("DS_CONTR_ACT_DESC_TXT"));
                    index++;
                }
            }
        }
        // END 2017/07/20 M.Kidokoro [QC#4468, ADD]
    }

    private static boolean hasFuncId(String bizAppId) {
        List<String> functionList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(bizAppId);
        if (functionList != null && !functionList.isEmpty()) {
            return true;
        }
        return false;
    }
    // add end 2016/08/02 CSA Defect#4433

    private static void createContrCloDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        createDayPullDownList(valueItemArray, dispItemArray);
    }

    // Mod Start 2017/12/20 QC#18779
    /**
     * createContrBllgDayPullDownList
     * @param glblCmpyCd String
     * @param bllgTmgTpCd String
     * @param baseChrgFlg String
     * @param usgChrgFlg String
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     */
    public static void createContrBllgDayPullDownList(String glblCmpyCd, String bllgTmgTpCd, String baseChrgFlg, String usgChrgFlg, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
//        createDayPullDownList(valueItemArray, dispItemArray);
        if (!hasValue(bllgTmgTpCd) && ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
            bllgTmgTpCd = BLLG_TMG_TP.ADVANCE;
        }

        if (!hasValue(bllgTmgTpCd) && ZYPConstant.FLG_ON_Y.equals(usgChrgFlg)) {
            bllgTmgTpCd = BLLG_TMG_TP.ARREARS;
        }

        if (!hasValue(bllgTmgTpCd)) {
            return;
        }

        valueItemArray.clear();
        dispItemArray.clear();
        List<Map<String, Object>> dayAotList = NSAL0300Query.getInstance().getBllgDayTp(glblCmpyCd, baseChrgFlg, usgChrgFlg, bllgTmgTpCd);
        int i = 0;
        for (Map<String, Object> dayAot : dayAotList) {
            ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), (String) dayAot.get("BLLG_DAYS_CD"));
            ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), (String) dayAot.get("BLLG_DAYS_NM"));
            i++;
        }
    }
    // Mod End 2017/12/20 QC#18779

    private static void createDayPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        valueItemArray.clear();
        dispItemArray.clear();
        for (int i = 0; i < 28; i++) {
            ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), i + "");
            ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), i + "");
        }
        ZYPEZDItemValueSetter.setValue(valueItemArray.no(28), LAST_DAY_OF_A_MONTH);
        ZYPEZDItemValueSetter.setValue(dispItemArray.no(28), LAST_DAY);
    }

    // del start 2016/07/01 CSA Defect#11261
//    private static void createPgmMdseCdPullDownList(EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
//        valueItemArray.clear();
//        dispItemArray.clear();
//
//        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
//        NSAL0300Query query = NSAL0300Query.getInstance();
//        S21SsmEZDResult rslt = query.getPgmMdseCdPullDownList(glblCmpyCd);
//        if (rslt != null) {
//            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
//            int rsltCnt = rslt.getQueryResultCount();
//            for (int i = 0; i < rsltCnt; i++) {
//                Map<String, String> valueMap = rsltList.get(i);
//                ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), (String) valueMap.get("MDSE_CD"));
//                ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), (String) valueMap.get("MDSE_NM"));
//            }
//        }
//
//    }
    // del end 2016/07/01 CSA Defect#11261

    // START 2019/01/17 M.Naito [QC#29083,ADD]
    private static void createCumCopyFreqMthAotPullDownList(String glblCmpyCd, EZDCBigDecimalItemArray valueItemArray, EZDCBigDecimalItemArray dispItemArray) {
        String cnstCumCopyFreqMth = ZYPCodeDataUtil.getVarCharConstValue(CUM_COPY_FREQ_MTH, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cnstCumCopyFreqMth)) {
            String[] cumCopyFreqMthArray = cnstCumCopyFreqMth.split(","); 
            int i = 0; 
            for (String cumCopyFreqMth : cumCopyFreqMthArray) {
                BigDecimal cumCopyFreqMthAot = new BigDecimal(cumCopyFreqMth);
                ZYPEZDItemValueSetter.setValue(valueItemArray.no(i), cumCopyFreqMthAot);
                ZYPEZDItemValueSetter.setValue(dispItemArray.no(i), cumCopyFreqMthAot);
                i++;
            }
        }
    }
    // END 2019/01/17 M.Naito [QC#29083,ADD]

    /**
     * Check if NSAL0150CMsg has error
     * @param cMsg NSAL0150CMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean hasError(NSAL0300CMsg cMsg) {
        if ("E".equals(cMsg.getMessageKind())) {
            return true;
        }
        try {
            Field field = NSAL0300CMsg.class.getSuperclass().getDeclaredField("errorHash");
            field.setAccessible(true);
            Map<String, EZDMessageInfo> errorHash = (Map<String, EZDMessageInfo>) field.get(cMsg);
            // Add Start 07/07/2016 <QC#11466>
            if (errorHash == null) {
                return false;
            }
            // Add End   07/07/2016 <QC#11466>
            return !errorHash.isEmpty();
        } catch (Exception e) {
            throw new S21AbendException(e);
        }
    }

    /**
     * Check if NSAL0150CMsg has error or warning
     * @param cMsg NSAL0150CMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean hasErrorOrWarn(NSAL0300CMsg cMsg) {
        boolean result = hasError(cMsg);
        if (!result && "W".equals(cMsg.getMessageKind())) {
            result = true;
        }
        return result;
    }

    /**
     * Get Index of sMsg.A from need to count
     * @param sMsg NSAL0100SMsg
     * @param needCnt int
     */
    public static int convertIndexFromNeedCount(NSAL0300SMsg sMsg, int needCnt) {
        int cnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                cnt++;
            }
            if (cnt == needCnt) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Convert row index to first row index in page
     * @param rowsPerPage int
     * @param rowIndex int
     * @return int
     */
    private static int convertRowIndexToFirstRowIndexInPage(NSAL0300SMsg sMsg, int rowsPerPage, int rowIndex) {
        int filteredCnt = 0;
        for (int i = rowIndex; i >= 0; i--) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                filteredCnt++;
            }
        }
        int needCnt = ((rowIndex - filteredCnt) / rowsPerPage) * rowsPerPage + 1;
        return convertIndexFromNeedCount(sMsg, needCnt);
    }


    public static int convertPageIndexToRowIndex(NSAL0300SMsg sMsg, int rowsPerPage, int page, int pageIndex) {
        if (page <= 0) {
            return 0;
        }

        int needCnt = ((page - 1) * rowsPerPage) + pageIndex + 1;
        return convertIndexFromNeedCount(sMsg, needCnt);
    }

    /**
     * convertPageIndexToRowIndex
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     * @param cIndex cMsg target Index
     */
    public static int convertPageIndexToRowIndexA(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, int cIndex) {
        BigDecimal dsContrDtlPk = cMsg.A.no(cIndex).dsContrDtlPk_A.getValue();
        for (int i = cIndex; i < sMsg.A.getValidCount(); i++) {
            if (isEqualNum(dsContrDtlPk, sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                return i;
            }
        }
        return convertPageIndexToRowIndex(sMsg, cMsg.A.length(), cMsg.xxPageShowCurNum_A.getValueInt(), cIndex);
    }

    public static int convertPageNumToFirstRowIndex(NSAL0300SMsg sMsg, int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }

        int needCnt = rowsPerPage * (page - 1) + 1;
        return convertIndexFromNeedCount(sMsg, needCnt);
    }

    /**
     * Copy table
     * @param cMsg
     * @param sMsg
     * @param startIndex
     */
    public static void copyTableA(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, int startIndex) {
        copy(cMsg.A, sMsg.A, startIndex);
    }

    /**
     * Copy
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param startIndex int
     */
    private static void copy(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, int startIndex) {
        for (int i = 0; i < cMsgArray.getValidCount(); i++) {
            for (int j = startIndex; j < sMsgArray.getValidCount(); j++) {
                if (isEqualNum(((NSAL0300_ACMsg) cMsgArray.get(i)).dsContrDtlPk_A.getValue(), ((NSAL0300_ASMsg) sMsgArray.get(j)).dsContrDtlPk_A.getValue())) {
                    EZDMsg.copy(cMsgArray.get(i), null, sMsgArray.get(j), null);
                    break;
                }
            }
        }
    }

    public static void delete(EZDMsgArray msgArray, int delIdx) {
        delete(msgArray, delIdx, 1);
    }

    public static void delete(EZDMsgArray msgArray, int fromIdx, int delCnt) {
        int curCnt = msgArray.getValidCount();
        for (int i = fromIdx; i < curCnt; i++) {
            EZDMsg.copy(msgArray.get(i + delCnt), null, msgArray.get(i), null);
        }
        for (int i = 0; i < delCnt; i++) {
            int clearIdx = curCnt - delCnt + i;
            if (clearIdx <= msgArray.length() - 1) {
                msgArray.get(clearIdx).clear();
            }
        }
        msgArray.setValidCount(curCnt - delCnt);
    }

    /**
     * Paginate
     * @param cMsg NSAL0100CMsg
     * @param sMsg NSAL0100SMsg
     * @param startIndex int
     */
    private static void paginateTableA(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, int startIndex) {
        paginate(cMsg.A, sMsg.A, startIndex);
        setTableAPaginationParameters(cMsg, sMsg, startIndex);
    }

    /**
     * Paginate
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param startIndex int
     */
    private static void paginate(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, int startIndex) {
        int num = 0;
        ZYPTableUtil.clear(cMsgArray);
        for (int i = startIndex; i < sMsgArray.getValidCount(); i++) {
            if (num >= cMsgArray.length()) {
                break;
            }

            if (!ZYPConstant.FLG_ON_Y.equals(((NSAL0300_ASMsg) sMsgArray.get(i)).xxDplyCtrlFlg_A.getValue())) {
                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(num), null);
                num++;
            }
        }
        cMsgArray.setValidCount(num);
    }

    /**
     * Paginate to item
     * @param cMsg NSAL0100CMsg
     * @param sMsg NSAL0100SMsg
     * @param itemIndex int
     */
    public static void paginateTableAToItem(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, int itemIndex) {

        int firstRowIndexInPage = convertRowIndexToFirstRowIndexInPage(sMsg, cMsg.A.length(), itemIndex);

        paginateTableA(cMsg, sMsg, firstRowIndexInPage);

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setTableAPulldownList(cMsg.A.no(i));
        }
    }

    private static void setTableAPulldownList(NSAL0300_ACMsg msg) {
        ZYPCodeDataUtil.createPulldownList(DS_CONTR_DTL_TP.class, msg.dsContrDtlTpCd_AT, msg.dsContrDtlTpDescTxt_AT);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, msg.baseBllgCycleCd_AB, msg.bllgCycleDescTxt_AB);
        deletePulldownList(msg.baseBllgCycleCd_AB, msg.bllgCycleDescTxt_AB, BLLG_CYCLE.DAILY);
        ZYPCodeDataUtil.createPulldownList(MTR_READ_METH.class, msg.mtrReadMethCd_AM, msg.mtrReadMethDescTxt_AM);
    }

    // Mod Start 2017/12/21 QC#18779
    public static void setTableBPulldownList(String glblCmpyCd, NSAL0300_BCMsg msg) {
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, msg.baseBllgCycleCd_BB, msg.bllgCycleDescTxt_BB);
        deletePulldownList(msg.baseBllgCycleCd_BB, msg.bllgCycleDescTxt_BB, BLLG_CYCLE.DAILY);
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, msg.bllgMtrBllgCycleCd_BU, msg.bllgCycleDescTxt_BU);
        deletePulldownList(msg.bllgMtrBllgCycleCd_BU, msg.bllgCycleDescTxt_BU, BLLG_CYCLE.DAILY);
        ZYPCodeDataUtil.createPulldownList(XS_CHRG_TP.class, msg.xsChrgTpCd_BU, msg.xsChrgTpDescTxt_BU);
        NSAL0300CommonLogic.createContrCloDayPullDownList(msg.contrCloDay_BC, msg.xxEdtDescTxt_BC);
        NSAL0300CommonLogic.createContrBllgDayPullDownList(glblCmpyCd, msg.baseBllgTmgCd_B.getValue(), ZYPConstant.FLG_ON_Y, ZYPConstant.FLG_OFF_N, msg.contrBllgDay_BB, msg.xxEdtDescTxt_BB);
        ZYPCodeDataUtil.createPulldownList(BLLG_TMG_TP.class, msg.bllgTmgTpCd_BT, msg.bllgTmgTpDescTxt_BT);
        NSAL0300CommonLogic.createContrCloDayPullDownList(msg.mtrCloDay_MC, msg.xxEdtDescTxt_MC);
        NSAL0300CommonLogic.createContrBllgDayPullDownList(glblCmpyCd, msg.mtrBllgTmgCd_B.getValue(), ZYPConstant.FLG_OFF_N, ZYPConstant.FLG_ON_Y, msg.mtrBllgDay_MB, msg.xxEdtDescTxt_MB);
        // Start 2019/01/21 T.Wada [QC#29371] 
        ZYPCodeDataUtil.createPulldownList(SVC_INV_MERGE_TP.class, msg.svcInvMergeTpCd_BT, msg.svcInvMergeTpDescTxt_BT);
        // End 2019/01/21 T.Wada [QC#29371] 
        // del start 2016/07/01 CSA Defect#11261
//        createPgmMdseCdPullDownList(msg.svcPgmMdseCd_BB, msg.mdseNm_BB);
        // del end 2016/07/01 CSA Defect#11261
        // START 2019/01/17 M.Naito [QC#29083,ADD]
        NSAL0300CommonLogic.createCumCopyFreqMthAotPullDownList(glblCmpyCd, msg.cumCopyFreqMthAot_BU, msg.cumCopyFreqMthAot_BM);
        // END 2019/01/17 M.Naito [QC#29083,ADD]
    }
    // Mod End 2017/12/21 QC#18779

    /**
     * Set pagination parameters
     * @param cMsg NSAL0100CMsg
     * @param sMsg NSAL0100SMsg
     * @param startIndex int
     */
    private static void setTableAPaginationParameters(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, int startIndex) {
        int cnt = 0;
        for (int i = 0; i <= startIndex; i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                cnt++;
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.valueOf(cnt));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(cnt + cMsg.A.getValidCount() - 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(getEnabledASMsgCount(sMsg)));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_A, BigDecimal.valueOf(cnt).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_A, BigDecimal.valueOf(getEnabledASMsgCount(sMsg)).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    private static int getEnabledASMsgCount(NSAL0300SMsg sMsg) {
        int enabledASMsgCount = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                enabledASMsgCount++;
            }
        }
        return enabledASMsgCount;
    }

    public static boolean isEqualNum(BigDecimal a, BigDecimal b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b != null && a.compareTo(b) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEqualStr(String a, String b) {
        if (!ZYPCommonFunc.hasValue(a) && !ZYPCommonFunc.hasValue(b)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(a) && ZYPCommonFunc.hasValue(b) && ZYPCommonFunc.trimTail(a).equals(ZYPCommonFunc.trimTail(b))) {
            return true;
        }
        return false;
    }

// START 2017/07/27 [QC#16889, DEL]
//    public static boolean isEqualMach(String serNum1, String mdseCd1, String serNum2, String mdseCd2) {
//        if (isEqualStr(serNum1, serNum2) && isEqualStr(mdseCd1, mdseCd2)) {
//            return true;
//        }
//        return false;
//    }
//
//    public static void deleteTableBbySerNum(NSAL0300CMsg cMsg, String serNum, String mdseCd) {
//
//        final int INIT_IDX = -1;
//
//        int delFromIdx = INIT_IDX;
//        int newFromIdx = INIT_IDX;
//
//        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//            String cmpSerNum = cMsg.B.no(i).serNum_B.getValue();
//            String cmpMdseCd = cMsg.B.no(i).mdseCd_B.getValue();
//            if (isEqualMach(serNum, mdseCd, cmpSerNum, cmpMdseCd)) {
//                if (delFromIdx == INIT_IDX) {
//                    delFromIdx = i;
//                }
//            } else {
//                if (delFromIdx != INIT_IDX) {
//                    newFromIdx = i;
//                    break;
//                }
//            }
//        }
//
//        if (newFromIdx == INIT_IDX) {
//            newFromIdx = cMsg.B.getValidCount();
//        }
//
//        int delCnt = newFromIdx - delFromIdx;
//
//        if (delFromIdx >= 0) {
//            delete(cMsg.B, delFromIdx, delCnt);
//        }
//    }
//
//    /**
//     * deleteTableAbySerNum
//     * @param sMsg NSAL0100SMsg
//     * @param serNum SER_NUM
//     * @param mdseCd MDSE_CD
//     */
//    public static void deleteTableAbySerNum(NSAL0300SMsg sMsg, String serNum, String mdseCd) {
//
//        final int INIT_IDX = -1;
//
//        int delFromIdx = INIT_IDX;
//        int newFromIdx = INIT_IDX;
//
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//            String cmpSerNum = sMsg.A.no(i).serNum_A.getValue();
//            String cmpMdseCd = sMsg.A.no(i).mdseCd_A.getValue();
//            if (isEqualMach(serNum, mdseCd, cmpSerNum, cmpMdseCd)) {
//                if (delFromIdx == INIT_IDX) {
//                    delFromIdx = i;
//                }
//            } else {
//                if (delFromIdx != INIT_IDX) {
//                    newFromIdx = i;
//                    break;
//                }
//            }
//        }
//
//        if (newFromIdx == INIT_IDX) {
//            newFromIdx = sMsg.A.getValidCount();
//        }
//
//        int delCnt = newFromIdx - delFromIdx;
//
//        if (delFromIdx >= 0) {
//            delete(sMsg.A, delFromIdx, delCnt);
//        }
//    }
// END 2017/07/27 [QC#16889, DEL]

    // START 2017/07/27 [QC#16889, ADD]
    public static void deleteTableAbyDsContrDtlPk(NSAL0300SMsg sMsg, BigDecimal dsContrDtlPk) {
        final int INIT_IDX = -1;

        int delFromIdx = INIT_IDX;
        int newFromIdx = INIT_IDX;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            BigDecimal cmpDsContrDtlPk = sMsg.A.no(i).dsContrDtlPk_A.getValue();
            if (isEqualNum(dsContrDtlPk, cmpDsContrDtlPk)) {
                if (delFromIdx == INIT_IDX) {
                    delFromIdx = i;
                }
            } else {
                if (delFromIdx != INIT_IDX) {
                    newFromIdx = i;
                    break;
                }
            }
        }

        if (newFromIdx == INIT_IDX) {
            newFromIdx = sMsg.A.getValidCount();
        }

        int delCnt = newFromIdx - delFromIdx;

        if (delFromIdx >= 0) {
            delete(sMsg.A, delFromIdx, delCnt);
        }
    }

    public static void deleteTableBbyDsContrDtlPk(NSAL0300CMsg cMsg, BigDecimal dsContrDtlPk) {
        final int INIT_IDX = -1;

        int delFromIdx = INIT_IDX;
        int newFromIdx = INIT_IDX;

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            BigDecimal cmpDsContrDtlPk = cMsg.B.no(i).dsContrDtlPk_B.getValue();
            if (isEqualNum(dsContrDtlPk, cmpDsContrDtlPk)) {
                if (delFromIdx == INIT_IDX) {
                    delFromIdx = i;
                }
            } else {
                if (delFromIdx != INIT_IDX) {
                    newFromIdx = i;
                    break;
                }
            }
        }

        if (newFromIdx == INIT_IDX) {
            newFromIdx = cMsg.B.getValidCount();
        }

        int delCnt = newFromIdx - delFromIdx;

        if (delFromIdx >= 0) {
            delete(cMsg.B, delFromIdx, delCnt);
        }
    }
    // END 2017/07/27 [QC#16889, ADD]

    // START 2017/07/27 [QC#16889, MOD]
    // public static int getExcessCopyBracketCount(NSAL0300CMsg cMsg, String serNum, String mdseCd, BigDecimal dsContrBllgMtrPk) {
    public static int getExcessCopyBracketCount(NSAL0300CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    // END 2017/07/27 [QC#16889, MOD]
        int cnt = 0;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            // START 2017/07/27 [QC#16889, MOD]
            // String cmpSerNum = cMsg.B.no(i).serNum_B.getValue();
            // String cmpMdseCd = cMsg.B.no(i).mdseCd_B.getValue();
            BigDecimal cmpDsContrDtlPk = cMsg.B.no(i).dsContrDtlPk_B.getValue();
            // END 2017/07/27 [QC#16889, MOD]
            BigDecimal cmpDsContrBllgMtrPk = cMsg.B.no(i).dsContrBllgMtrPk_B.getValue();
            // START 2017/07/27 [QC#16889, MOD]
            // if (isEqualMach(serNum, mdseCd, cmpSerNum, cmpMdseCd) && isEqualNum(dsContrBllgMtrPk, cmpDsContrBllgMtrPk)) {
            if (isEqualNum(dsContrDtlPk, cmpDsContrDtlPk) && isEqualNum(dsContrBllgMtrPk, cmpDsContrBllgMtrPk)) {
            // END 2017/07/27 [QC#16889, MOD]
                cnt++;
            }
        }
        return cnt;
    }

    public static int getIndexOfSameMtr(NSAL0300CMsg cMsg, BigDecimal dsContrBllgMtrPk, int rowNum) {
        int i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            if (dsContrBllgMtrPk.compareTo(cMsg.B.no(i).dsContrBllgMtrPk_B.getValue()) == 0) {
                break;
            }
        }
        return (rowNum - i);
    }

    /**
     * setCopyQtyForAggr
     * @param cMsg NSAL0300CMsg
     */
    // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
    // public static void setCopyQtyForAggr(NSAL0300CMsg cMsg) {
    public static void setCopyQtyForAggr(NSAL0300CMsg cMsg, String glblCmpyCd) {
    // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
        String preBllgMtrLbCd = null;
        int seqNo = 0;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                if (!ZYPCommonFunc.hasValue(preBllgMtrLbCd)) {
                    preBllgMtrLbCd = cMsg.B.no(i).bllgMtrLbCd_B.getValue();
                }

                if (preBllgMtrLbCd.equals(cMsg.B.no(i).bllgMtrLbCd_B.getValue())) {
                    seqNo++;
                    //ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsMtrCopyQty_B, BigDecimal.valueOf(seqNo));
                } else {
                    seqNo = 1;
                    //ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsMtrCopyQty_B, BigDecimal.valueOf(seqNo));
                    preBllgMtrLbCd = cMsg.B.no(i).bllgMtrLbCd_B.getValue();
                }
                // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
                // BigDecimal aggAllowance = sumAllowance(cMsg.B, preBllgMtrLbCd, seqNo);
                BigDecimal aggAllowance = sumAllowance(cMsg.B, preBllgMtrLbCd, seqNo, glblCmpyCd);
                // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsMtrCopyQty_B, aggAllowance);
            }
        }
    }
    // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    // private static BigDecimal sumAllowance(NSAL0300_BCMsgArray bCMsgArray, String bllgMtrLbCd, int seqNo) {
    private static BigDecimal sumAllowance(NSAL0300_BCMsgArray bCMsgArray, String bllgMtrLbCd, int seqNo, String glblCmpyCd) {
    // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
        BigDecimal retVal = BigDecimal.ZERO;
        if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return retVal;
        }
        // START 2019/05/13 K.Fujimoto [31137/50058, ADD]
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
        // END   2019/05/13 K.Fujimoto [31137/50058, ADD]
        int machSeq = 0;
        BigDecimal preMachPk = BigDecimal.valueOf(-1);
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).dsContrDtlTpCd_B) && !DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsgArray.no(i).dsContrDtlTpCd_B.getValue()) && bllgMtrLbCd.equals(bCMsgArray.no(i).bllgMtrLbCd_B.getValue())) {
                if (preMachPk.compareTo(bCMsgArray.no(i).svcMachMstrPk_B.getValue()) != 0) {
                    preMachPk = bCMsgArray.no(i).svcMachMstrPk_B.getValue();
                    machSeq = 1;
                } else {
                    machSeq++;
                }
                if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).xsMtrCopyQty_B)) {
                    if (seqNo == machSeq) {
                        // START 2019/05/13 K.Fujimoto [31137/50058, ADD]
                        if(!hasValue(bCMsgArray.no(i).contrCloDt_B) || bCMsgArray.no(i).contrCloDt_B.getValue().compareTo(slsDt) > 0) {
                        // END   2019/05/13 K.Fujimoto [31137/50058, ADD]
                            retVal = retVal.add(bCMsgArray.no(i).xsMtrCopyQty_B.getValue());
                        }
                    }
                }
            }
        }
        return retVal;
    }
    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]

    public static String flg(EZDCStringItem flg) {
        if (ZYPCommonFunc.hasValue(flg)) {
            return flg.getValue();
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    public static String switchFlg(String flg) {
        if (ZYPCommonFunc.hasValue(flg)) {
            if (ZYPConstant.FLG_ON_Y.equals(flg)) {
                return ZYPConstant.FLG_OFF_N;
            }
        }
        return ZYPConstant.FLG_ON_Y;
    }

    public static String switchFlg(EZDCStringItem flg) {
        if (ZYPCommonFunc.hasValue(flg)) {
            if (ZYPConstant.FLG_ON_Y.equals(flg.getValue())) {
                return ZYPConstant.FLG_OFF_N;
            }
        }
        return ZYPConstant.FLG_ON_Y;
    }

    public static String formatAddress(String firstLineAddr, String secondLineAddr, String thirdLineAddr, String fourthLineAddr, String ctyAddr, String stCd, String postCd) {
        StringBuilder buf = new StringBuilder();
        if (ZYPCommonFunc.hasValue(firstLineAddr)) {
            buf.append(firstLineAddr);
        }
        if (ZYPCommonFunc.hasValue(secondLineAddr)) {
            buf.append(" ");
            buf.append(secondLineAddr);
        }
        if (ZYPCommonFunc.hasValue(thirdLineAddr)) {
            buf.append(" ");
            buf.append(thirdLineAddr);
        }
        if (ZYPCommonFunc.hasValue(fourthLineAddr)) {
            buf.append(" ");
            buf.append(fourthLineAddr);
        }
        if (ZYPCommonFunc.hasValue(ctyAddr)) {
            buf.append(", ");
            buf.append(ctyAddr);
        }
        if (ZYPCommonFunc.hasValue(stCd)) {
            buf.append(", ");
            buf.append(stCd);
        }
        if (ZYPCommonFunc.hasValue(postCd)) {
            buf.append(" ");
            buf.append(postCd);
        }
        return buf.toString();
    }

    /**
     * addMachine
     * @param cMsg NSAL0300CMsg
     * @param aSMsgArray NSAL0300_ASMsgArray
     * @param bCMsgArray NSAL0300_BCMsgArray
     * @param dsContrCatgCd String
     * @param dsContrDtlTpCd String
     * @param svcMachMstrPk BigDecimal
     * @param contrEffFromDt String
     * @param contrEffThruDt String
     * @param baseBllgCycleCd String
     * @param baseBllgTmgTpCd String
     * @param mtrBllgCycleCd String
     * @param mtrBllgTmgTpCd String
     * @param basePrcDealAmt BigDecimal
     * @param mtrReadMethCd String
     * @param prntDsContrDtlPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     */
    @SuppressWarnings("unchecked")
    // String mtrReadMethCd, BigDecimal prntDsContrDtlPk, BigDecimal dsContrDtlPk
    // START 2016/02/24 [QC3697, MOD]
    // START 2022/01/21 R.Cabral [QC#59502, MOD]
//    public static void addMachine(NSAL0300CMsg cMsg, NSAL0300_ASMsgArray aSMsgArray, NSAL0300_BCMsgArray bCMsgArray, String dsContrCatgCd, String dsContrDtlTpCd, BigDecimal svcMachMstrPk, String contrEffFromDt, String contrEffThruDt,
//            String baseBllgCycleCd, String baseBllgTmgTpCd, String mtrBllgCycleCd, String mtrBllgTmgTpCd, BigDecimal basePrcDealAmt, String mtrReadMethCd) {
    public static void addMachine(NSAL0300CMsg cMsg, NSAL0300_ASMsgArray aSMsgArray, NSAL0300_BCMsgArray bCMsgArray, String dsContrCatgCd, String dsContrDtlTpCd, BigDecimal svcMachMstrPk, String contrEffFromDt, String contrEffThruDt,
            String baseBllgCycleCd, String baseBllgTmgTpCd, String mtrBllgCycleCd, String mtrBllgTmgTpCd, BigDecimal basePrcDealAmt, String mtrReadMethCd, BigDecimal prntDsContrDtlPk, BigDecimal dsContrDtlPk) {
    // END   2022/01/21 R.Cabral [QC#59502, MOD]]
    // END 2016/02/24 [QC3697, MOD]

        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        NSAL0300Query query = NSAL0300Query.getInstance();
        // START 2022/01/21 R.Cabral [QC#59502, MOD]
//        BigDecimal prntDsContrDtlPk = null;
//        BigDecimal dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_DTL_SQ");
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            dsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CONTR_DTL_SQ");
        }
        // END   2022/01/21 R.Cabral [QC#59502, MOD]
        int addAIdx = aSMsgArray.getValidCount();
        // START 2017/06/27 [QC#19562, ADD]
        String svcMachTpCd = null;
        // END   2017/06/27 [QC#19562, ADD]
        // START 2018/08/22 [QC#22821, ADD]
        String svcPgmMdseCd = cMsg.svcPgmMdseCd.getValue();
        String mdseDescShortTxt = cMsg.mdseDescShortTxt_SP.getValue();
        // END   2018/08/22 [QC#22821, ADD]
        Map<String, Object> rsltMap = null;
        if (ZYPCommonFunc.hasValue(svcMachMstrPk)) {
        // START 2017/07/18 [QC#26859, DEL]
//            if (NSAL0300BL02.machExst(aSMsgArray, svcMachMstrPk)) {
//                return;
//            }
        // END   2017/07/18 [QC#26859, DEL]
            S21SsmEZDResult rslt = query.getDsContrDtlTmpl1(glblCmpyCd, svcMachMstrPk);
            if (rslt != null && rslt.isCodeNormal()) {
                rsltMap = (Map<String, Object>) rslt.getResultObject();

                // START 2017/06/27 [QC#19562, MOD]
                svcMachTpCd = (String) rsltMap.get("SVC_MACH_TP_CD");
                // END   2017/06/27 [QC#19562, MOD]
                if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
                    dsContrDtlTpCd = DS_CONTR_DTL_TP.ACCESSORIES;
                    // START 2019/01/17 [QC#29782, DEL]
//                    // Start   2018/08/22 [QC#22821, ADD]
//                    if (!DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue())) {
//                        svcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, glblCmpyCd, cMsg.svcLineBizCd.getValue(), (String) rsltMap.get("MDSE_CD"), ZYPConstant.FLG_OFF_N);
//                        mdseDescShortTxt = "";
//                            
//                        S21SsmEZDResult accRslt = NSAL0300Query.getInstance().getPgmMdseCdPullDownList(glblCmpyCd, svcPgmMdseCd, "");
//                        if (!accRslt.isCodeNotFound()) {
//                                List<Map<String, String>> rsltList = (List<Map<String, String>>) accRslt.getResultObject();
//                                mdseDescShortTxt = (String) rsltList.get(0).get("MDSE_DESC_SHORT_TXT");
//                        }
//                    }
                    // END   2018/08/22 [QC#22821, ADD]
                    // END 2019/01/17 [QC#29782, DEL]

                    BigDecimal svcConfigMstrPk = (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK");
                    // START 2018/09/20 K.Kitachi [QC#28328, MOD]
//                    for (int i = 0; i < aSMsgArray.getValidCount(); i++) {
//                        if (svcConfigMstrPk.compareTo(aSMsgArray.no(i).svcConfigMstrPk_A.getValue()) == 0) {
//                            prntDsContrDtlPk = aSMsgArray.no(i).dsContrDtlPk_A.getValue();
//                            addAIdx = i + 1;
//                            break;
//                        }
//                    }
                    if (hasValue(svcConfigMstrPk)) {
                        List<String> mainUnitDtlTpList = query.getMainUnitDtlTpList(glblCmpyCd);
                        for (int i = 0; i < aSMsgArray.getValidCount(); i++) {
                            if (isEqualNum(svcConfigMstrPk, aSMsgArray.no(i).svcConfigMstrPk_A.getValue()) && mainUnitDtlTpList.contains(aSMsgArray.no(i).dsContrDtlTpCd_A.getValue())) {
                                // START 2022/01/21 R.Cabral [QC#59502, MOD]
//                                prntDsContrDtlPk = aSMsgArray.no(i).dsContrDtlPk_A.getValue();
//                                addAIdx = i + 1;
//                                break;
                                if (prntDsContrDtlPk == null) {
                                    prntDsContrDtlPk = aSMsgArray.no(i).dsContrDtlPk_A.getValue();
                                    addAIdx = i + 1;
                                    break;
                                } else if (prntDsContrDtlPk.compareTo(aSMsgArray.no(i).dsContrDtlPk_A.getValue()) == 0) {
                                    addAIdx = i + 1;
                                    break;
                                }
                                // END   2022/01/21 R.Cabral [QC#59502, MOD]
                            }
                        }
                    }
                    // END 2018/09/20 K.Kitachi [QC#28328, MOD]

                    if (prntDsContrDtlPk == null) {
                        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(aSMsgArray, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
                        if (selectedRows.size() == 1) {
                            prntDsContrDtlPk = aSMsgArray.no(selectedRows.get(0)).dsContrDtlPk_A.getValue();
                            addAIdx = selectedRows.get(0) + 1;
                        } else if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxRsltFlg.getValue())) {
                            //START 2017/09/01 U.Kim [QC#20856, MOD]
                            //cMsg.serNum.setErrorInfo(2, NSAM0367W);
                            //cMsg.setMessageInfo(NSAM0367W);
                            cMsg.serNum.setErrorInfo(1, NSAM0697E);
                            cMsg.setMessageInfo(NSAM0697E);
                            // Warning Flag
                            //ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_ON_Y);
                            //END 2017/09/01 U.Kim [QC#20856, MOD]
                            return;
                        } else {
                            prntDsContrDtlPk = getDsContrDtlPkForNoParent(bCMsgArray);
                        }
                    }
                    // START 2019/01/17 [QC#29782, ADD]
                    if (!DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue())) {
                        String prntSvcPgmMdseCd = NSAL0300Query.getInstance().getPrntSvcPgmMdseCd(glblCmpyCd, prntDsContrDtlPk);
                        svcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, glblCmpyCd, cMsg.svcLineBizCd.getValue(), (String) rsltMap.get("MDSE_CD"), ZYPConstant.FLG_OFF_N, prntSvcPgmMdseCd);
                        mdseDescShortTxt = "";

                        S21SsmEZDResult accRslt = NSAL0300Query.getInstance().getPgmMdseCdPullDownList(glblCmpyCd, svcPgmMdseCd, "");
                        if (!accRslt.isCodeNotFound()) {
                                List<Map<String, String>> rsltList = (List<Map<String, String>>) accRslt.getResultObject();
                                mdseDescShortTxt = (String) rsltList.get(0).get("MDSE_DESC_SHORT_TXT");
                        }
                    }
                    // END 2019/01/17 [QC#29782, ADD]
                } else {
                    if (!DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                        prntDsContrDtlPk = getDsContrDtlPkForNoParent(bCMsgArray);
                    }

                    if (ZYPConstant.FLG_OFF_N.equals((String) rsltMap.get("MTR_REQ_MDL_FLG")) || DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
                        dsContrDtlTpCd = DS_CONTR_DTL_TP.BASE_ONLY;
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.xxRsltFlg, ZYPConstant.FLG_OFF_N);

        String addr = null;

        if (!DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            int aCnt = aSMsgArray.getValidCount() + 1;
            aSMsgArray.setValidCount(aCnt);
            for (int i = aCnt - 1; i > addAIdx; i--) {
                EZDMsg.copy(aSMsgArray.no(i - 1), null, aSMsgArray.no(i), null);
            }
            aSMsgArray.no(addAIdx).clear();

            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).dsContrDtlPk_A, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).prntDsContrDtlPk_A, prntDsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).dsContrDtlTpCd_A, dsContrDtlTpCd);
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).contrEffFromDt_A, contrEffFromDt);
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).contrEffThruDt_A, contrEffThruDt);
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).contrRnwTotCnt_A, BigDecimal.ZERO);
            // START 2018/08/22 [QC#22821, MOD]
            // Add Start 2018/01/15 QC#18552
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).svcPgmMdseCd_A, svcPgmMdseCd);
            ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).mdseDescShortTxt_AS, mdseDescShortTxt);
            // Add End 2018/01/15 QC#18552
            // END   2018/08/22 [QC#22821, MOD]

            if (rsltMap != null) {
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).serNum_A, (String) rsltMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).mdseCd_A, (String) rsltMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).mdlNm_A, (String) rsltMap.get("MDL_NM"));

                String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
                String secondLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
                String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
                String fourthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
                String ctyAddr = (String) rsltMap.get("CTY_ADDR");
                String stCd = (String) rsltMap.get("ST_CD");
                String postCd = (String) rsltMap.get("POST_CD");
                addr = formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).billToCustLocAddr_A, addr);
            }

            // Mod Start 10/21/2016 <QC#15146>
            if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).baseBllgCycleCd_A, baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).xxFilePathTxt_A, IMG_CLOSE_MACHINE_GREEN);
            } else if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).baseBllgCycleCd_A, baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).xxFilePathTxt_A, IMG_OPEN_MACHINE_RED);
            }
            // Mod End   10/21/2016 <QC#15146>

            // START 2016/02/24 [QC3697, ADD]
            if (ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).basePrcDealAmt_A, basePrcDealAmt);
                }
            }
            if (ZYPCommonFunc.hasValue(mtrReadMethCd)) {
                if (!DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {
                    ZYPEZDItemValueSetter.setValue(aSMsgArray.no(addAIdx).mtrReadMethCd_A, mtrReadMethCd);
                }
            }
            // END 2016/02/24 [QC3697, ADD]
        }

        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) || (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)
                || DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd)) {

            int bCnt = bCMsgArray.getValidCount() + 1;
            int bIdx = bCMsgArray.getValidCount();

            bCMsgArray.setValidCount(bCnt);
            bCMsgArray.no(bIdx).clear();

            // START 2016/01/18 T.Tomita [QC#2948, MOD]
            String defBaseBillToCustCd = null;
            String defUsageBillToCustCd = null;
            if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
                if (!FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue()) && !FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                    cMsg.baseChrgToLeaseCmpyFlg.setErrorInfo(1, NSZM0665E);
                    cMsg.usgChrgToLeaseCmpyFlg.setErrorInfo(1, NSZM0665E);
                    bCMsgArray.setValidCount(bCnt-1);
                    return;
                }

                if (FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue())) {
                    defBaseBillToCustCd = cMsg.leaseCmpyCd.getValue();
                } else {
                    defBaseBillToCustCd = cMsg.altPayerCustCd.getValue();
                }

                if (FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                    defUsageBillToCustCd = cMsg.leaseCmpyCd.getValue();
                } else {
                    defUsageBillToCustCd = cMsg.altPayerCustCd.getValue();
                }
            } else if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
                defBaseBillToCustCd = cMsg.altPayerCustCd.getValue();
                defUsageBillToCustCd = cMsg.altPayerCustCd.getValue();
            }

            String defBaseBillToLocNm = null;
            String defBaseBillToAddr = null;
            String defUsageBillToLocNm = null;
            String defUsageBillToAddr = null;
            BILL_TO_CUSTTMsg billToCustTMsg;
            if (ZYPCommonFunc.hasValue(defBaseBillToCustCd)) {
                billToCustTMsg = query.getBillToCust(glblCmpyCd, defBaseBillToCustCd);
                if (billToCustTMsg != null) {
                    defBaseBillToLocNm = billToCustTMsg.locNm.getValue();
                    defBaseBillToAddr = formatAddress(billToCustTMsg.firstLineAddr.getValue(), billToCustTMsg.scdLineAddr.getValue(), billToCustTMsg.thirdLineAddr.getValue(), billToCustTMsg.frthLineAddr.getValue(), billToCustTMsg.ctyAddr.getValue(), billToCustTMsg.stCd.getValue(), billToCustTMsg.postCd.getValue());
                }
            }

            if (ZYPCommonFunc.hasValue(defUsageBillToCustCd)) {
                billToCustTMsg = query.getBillToCust(glblCmpyCd, defUsageBillToCustCd);
                if (billToCustTMsg != null) {
                    defUsageBillToLocNm = billToCustTMsg.locNm.getValue();
                    defUsageBillToAddr = formatAddress(billToCustTMsg.firstLineAddr.getValue(), billToCustTMsg.scdLineAddr.getValue(), billToCustTMsg.thirdLineAddr.getValue(), billToCustTMsg.frthLineAddr.getValue(), billToCustTMsg.ctyAddr.getValue(), billToCustTMsg.stCd.getValue(), billToCustTMsg.postCd.getValue());
                }
            }
            // END 2016/01/18 T.Tomita [QC#2948, MOD]

            SELL_TO_CUSTTMsgArray sellToCustTMsgArray = query.getSellToCustList(glblCmpyCd, cMsg.dsAcctNum.getValue());
            if (sellToCustTMsgArray != null && sellToCustTMsgArray.getValidCount() == 1) {
                if (ZYPCommonFunc.hasValue(sellToCustTMsgArray.no(0).defBaseTpCd)) {
                    baseBllgTmgTpCd = sellToCustTMsgArray.no(0).defBaseTpCd.getValue();
                }
                if (ZYPCommonFunc.hasValue(sellToCustTMsgArray.no(0).defUsgTpCd)) {
                    mtrBllgTmgTpCd = sellToCustTMsgArray.no(0).defUsgTpCd.getValue();
                }
            }

            // START 2017/06/27 [QC#19562, ADD]
            if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd) && hasValue(prntDsContrDtlPk)) {
                int mainMachIdx = getMainMachLineIndex(bCMsgArray, prntDsContrDtlPk);
                for (int cpIdx = bCMsgArray.getValidCount() - 1; cpIdx > mainMachIdx; cpIdx--) {
                    EZDMsg.copy(bCMsgArray.no(cpIdx - 1), null, bCMsgArray.no(cpIdx), null);
                }
                bIdx = mainMachIdx + 1;
                bCMsgArray.no(bIdx).clear();
            }
            // END   2017/06/27 [QC#19562, ADD]

            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).dsContrDtlPk_B, dsContrDtlPk);
            if (ZYPCommonFunc.hasValue(prntDsContrDtlPk)) {
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).prntDsContrDtlPk_B, prntDsContrDtlPk);
            }

            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).dsContrDtlTpCd_B, dsContrDtlTpCd);
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).contrEffFromDt_B, contrEffFromDt);
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).contrEffThruDt_B, contrEffThruDt);

            // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) && ZYPCommonFunc.hasValue(defBaseBillToCustCd)) {
                String defShipToCustCd = getDefShipToCustCd(glblCmpyCd, defBaseBillToCustCd, cMsg);
                if (ZYPCommonFunc.hasValue(defShipToCustCd)) {
                    SHIP_TO_CUSTTMsg shipToCustTMsg = query.getShipToCust(glblCmpyCd, defShipToCustCd);
                    if (shipToCustTMsg != null) {
                        ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).shipToCustCd_B, defShipToCustCd);
                        ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).shipToLocNm_B, shipToCustTMsg.locNm);
                    }
                }
            }
            // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

            if (rsltMap != null) {
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).svcConfigMstrPk_B, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).svcMachMstrPk_B, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).serNum_B, (String) rsltMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mdseCd_B, (String) rsltMap.get("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mdlNm_B, (String) rsltMap.get("MDL_NM"));
                // Add Start 2018/01/14 QC#18552
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mdseDescShortTxt_BI, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).shipToCustCd_B, (String) rsltMap.get("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).shipToLocNm_B, (String) rsltMap.get("LOC_NM"));
                String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
                String secondLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
                String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
                String fourthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
                String ctyAddr = (String) rsltMap.get("CTY_ADDR");
                String stCd = (String) rsltMap.get("ST_CD");
                String postCd = (String) rsltMap.get("POST_CD");
                addr = formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).shipToCustLocAddr_B, (String) addr);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).svcBrCd_B, (String) rsltMap.get("FLD_SVC_BR_CD"));
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).svcBrCdDescTxt_B, (String) rsltMap.get("SVC_BR_CD_DESC_TXT"));
                // Add End 2018/01/14 QC#18552
            }

            // mod start 2017/08/08 QC#18799
            // START 2016/05/16 T.Kanasaka [QC#2184, ADD]
//            String defContrPerEndDay = ZYPCodeDataUtil.getVarCharConstValue(DEF_CONTR_PER_END_DAY, glblCmpyCd);
//            if (!ZYPCommonFunc.hasValue(defContrPerEndDay)) {
//                defContrPerEndDay = LAST_DAY_OF_A_MONTH;
//            }
//            String defContrInvDay = ZYPCodeDataUtil.getVarCharConstValue(DEF_CONTR_INV_DAY, glblCmpyCd);
//            if (!ZYPCommonFunc.hasValue(defContrInvDay)) {
//                defContrInvDay = LAST_DAY_OF_A_MONTH;
//            }
            String defContrPerEndDay = null;
            String defContrInvDay = null;
            String bllgMtrBllgCycleCd = mtrBllgCycleCd;
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = query.getDsContrIntfcDefRule(glblCmpyCd, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
            if (dsContrIntfcDefRuleTMsg != null) {
                defContrPerEndDay = dsContrIntfcDefRuleTMsg.contrCloDay.getValue();
                defContrInvDay = dsContrIntfcDefRuleTMsg.contrBllgDay.getValue();
                if (!hasValue(bllgMtrBllgCycleCd)) {
                    bllgMtrBllgCycleCd = dsContrIntfcDefRuleTMsg.mtrBllgCycleCd.getValue();
                }
            }
            if (!ZYPCommonFunc.hasValue(defContrPerEndDay)) {
                defContrPerEndDay = LAST_DAY_OF_A_MONTH;
            }
            if (!ZYPCommonFunc.hasValue(defContrInvDay)) {
                defContrInvDay = LAST_DAY_OF_A_MONTH;
            }
            // END 2016/05/16 T.Kanasaka [QC#2184, ADD]
            // mod end 2017/08/08 QC#18799

            // mod start 2017/08/08 QC#18799
            // START 2017/06/27 [QC#19562, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
                    if (DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsgArray.no(i).dsContrDtlTpCd_B.getValue())) {
                        defContrPerEndDay = bCMsgArray.no(i).contrCloDay_B.getValue();
                        defContrInvDay = bCMsgArray.no(i).contrBllgDay_B.getValue();
                        bllgMtrBllgCycleCd = bCMsgArray.no(i).bllgMtrBllgCycleCd_B.getValue();
                        break;
                    }
                }
            }
            // END   2017/06/27 [QC#19562, ADD]
            // mod end 2017/08/08 QC#18799

            if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd)) {
                // base
                // START 2016/01/18 T.Tomita [QC#2948, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).baseBillToCustCd_B, defBaseBillToCustCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).billToLocNm_BB, defBaseBillToLocNm);
                // END 2016/01/18 T.Tomita [QC#2948, MOD]
                // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).baseDplyPerEndDay_B, defContrPerEndDay);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).contrCloDay_B, defContrPerEndDay);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).contrBllgDay_B, defContrInvDay);
                // END 2016/05/16 T.Kanasaka [QC#2184, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).baseBllgCycleCd_B, baseBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).baseBllgTmgCd_B, baseBllgTmgTpCd);
                // START 2018/08/22 [QC#22821, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).svcPgmMdseCd_B, svcPgmMdseCd);
                // add start 2016/07/01 CSA Defect#11261
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mdseDescShortTxt_B, mdseDescShortTxt);
                // add end 2016/07/01 CSA Defect#11261
                // END   2018/08/22 [QC#22821, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).ctacPsnPk_BB, cMsg.ctacPsnPk_CP);
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxPsnNm_BB, cMsg.xxPsnNm_CP);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).ctacPsnFirstNm_BB, cMsg.ctacPsnFirstNm_CP);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).ctacPsnLastNm_BB, cMsg.ctacPsnLastNm_CP);
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]

                if (defBaseBillToAddr != null) {
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).billToCustLocAddr_BB, defBaseBillToAddr);
                }
                // Mod Start 2018/01/14 QC#18552
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxFilePathTxt_BB, IMG_CLOSE_ARROW);
                // Mod End 2018/01/14 QC#18552
            }

            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B0, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B0, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);

            if (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                // usage
                // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mtrDplyPerEndDay_B, defContrPerEndDay);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mtrCloDay_B, defContrPerEndDay);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mtrBllgDay_B, defContrInvDay);
                // END 2016/05/16 T.Kanasaka [QC#2184, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mtrBllgCycleCd_B, mtrBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).mtrBllgTmgCd_B, mtrBllgTmgTpCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B2, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B3, ZYPConstant.FLG_OFF_N);

                // meter
                // START 2016/01/18 T.Tomita [QC#2948, MOD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).bllgMtrBillToCustCd_B, defUsageBillToCustCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).billToLocNm_BM, defUsageBillToLocNm);
                // END 2016/01/18 T.Tomita [QC#2948, MOD]
                // mod start 2017/08/08 QC#18799
//                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).bllgMtrBllgCycleCd_B, mtrBllgCycleCd);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).bllgMtrBllgCycleCd_B, bllgMtrBllgCycleCd);
                // mod end 2017/08/08 QC#18799
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xsChrgTpCd_B, XS_CHRG_TP.POINT);
                if (defUsageBillToAddr != null) {
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).billToCustLocAddr_BM, defUsageBillToAddr);
                }
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).ctacPsnPk_BM, cMsg.ctacPsnPk_CP);
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxPsnNm_BM, cMsg.xxPsnNm_CP);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).ctacPsnFirstNm_BM, cMsg.ctacPsnFirstNm_CP);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).ctacPsnLastNm_BM, cMsg.ctacPsnLastNm_CP);
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                // Mod Start 2018/01/14 QC#18552
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxFilePathTxt_BM, IMG_OPEN_ARROW);
                // Mod End 2018/01/14 QC#18552
            }

            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B4, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).xxDplyCtrlFlg_B5, ZYPConstant.FLG_OFF_N);

            // Mod Start 2017/12/21 QC#18779
            setTableBPulldownList(glblCmpyCd, bCMsgArray.no(bIdx));
            // Mod End 2017/12/21 QC#18779

            // START 2016/02/24 [QC3697, ADD]
            if (ZYPCommonFunc.hasValue(basePrcDealAmt)) {
                if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) || (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd))) {
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bIdx).basePrcDealAmt_B, basePrcDealAmt);
                }
            }
            // END 2016/02/24 [QC3697, ADD]
        }

        // START 2016/02/24 [QC3697, ADD]
        if (ZYPCommonFunc.hasValue(basePrcDealAmt)) {
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
                if (bCMsgArray.getValidCount() > 0) {
                    BigDecimal totalBasePrcDealAmt;
                    if (ZYPCommonFunc.hasValue(bCMsgArray.no(0).basePrcDealAmt_B)) {
                        totalBasePrcDealAmt = bCMsgArray.no(0).basePrcDealAmt_B.getValue().add(basePrcDealAmt);
                    } else {
                        totalBasePrcDealAmt = basePrcDealAmt;
                    }
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(0).basePrcDealAmt_B, totalBasePrcDealAmt);
                }
            }
        }
        // END 2016/02/24 [QC3697, ADD]

        // START 2017/02/06 [QC#17275, ADD]
        setDefaultBllgMtr(cMsg, aSMsgArray, bCMsgArray, glblCmpyCd, dsContrCatgCd, dsContrDtlTpCd, svcMachMstrPk);
        // END   2017/02/06 [QC#17275, ADD]
    }

    // START 2017/02/06 [QC#17275, ADD]
    private static void setDefaultBllgMtr(NSAL0300CMsg cMsg, NSAL0300_ASMsgArray aSMsgArray, NSAL0300_BCMsgArray bCMsgArray, String glblCmpyCd, String dsContrCatgCd, String dsContrDtlTpCd, BigDecimal svcMachMstrPk) {
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) && (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd))) {

            NSAL0300Query query = NSAL0300Query.getInstance();
            S21SsmEZDResult rslt = query.getDsContrBllgMtrTmpl(glblCmpyCd, svcMachMstrPk);
            if (rslt != null && rslt.isCodeNormal()) {
                int bSrcIdx = bCMsgArray.getValidCount() - 1;
                int bDestIdx = bSrcIdx;
                BigDecimal dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);

                List<Map<String, Object>> rsltRelnList = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
                int rsltCnt = rslt.getQueryResultCount();
                for (int i = 0; i < rsltCnt; i++) {
                    if (i != 0) {
                        bDestIdx++;
                        bCMsgArray.setValidCount(bDestIdx + 1);
                        EZDMsg.copy(bCMsgArray.no(bSrcIdx), null, bCMsgArray.no(bDestIdx), null);
                        dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
                    }

                    Map<String, Object> rsltMap = rsltList.get(i);
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).dsContrBllgMtrPk_B, dsContrBllgMtrPk);
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).bllgMtrLbCd_B, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).mtrLbDescTxt_B, (String) rsltMap.get("MTR_LB_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).mtrLbDescTxt_BX, (String) rsltMap.get("MTR_LB_DESC_TXT"));
                    // START 2017/02/10 [QC#17494, DEL]
                    // ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).contrMtrMultRate_B, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
                    // ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxComnScrColValTxt_B, (String) rsltMap.get("MDL_MTR_LB_DESC_TXT"));
                    // END   2017/02/10 [QC#17494, DEL]
                    bCMsgArray.no(bDestIdx).xsMtrCopyQty_B.clear();
                    bCMsgArray.no(bDestIdx).xsMtrAmtRate_B.clear();
                    bCMsgArray.no(bDestIdx).bllgFreeCopyCnt_B.clear();
                    bCMsgArray.no(bDestIdx).bllgMinCnt_B.clear();
                    bCMsgArray.no(bDestIdx).bllgMinAmtRate_B.clear();
                    bCMsgArray.no(bDestIdx).bllgRollOverRatio_B.clear();
                    // START 2019/01/17 M.Naito [QC#29083,ADD]
                    bCMsgArray.no(bDestIdx).cumCopyCnt_B.clear();
                    bCMsgArray.no(bDestIdx).cumCopyFreqMthAot_B.clear();
                    bCMsgArray.no(bDestIdx).cumCopyStartDt_B.clear();
                    bCMsgArray.no(bDestIdx).cumCopyEndDt_B.clear();
                    // END 2019/01/17 M.Naito [QC#29083,ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxFilePathTxt_BD, IMG_OPEN_MACHINE_RED);
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxFilePathTxt_BA, IMG_CLOSE_MACHINE_GREEN);
                    ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxFilePathTxt_BC, IMG_CLOSE_ARROW);

                    // START 2017/02/10 [QC#17494, ADD]
                    String bllgMtrLbCd = (String) rsltMap.get("BLLG_MTR_LB_CD");
                    S21SsmEZDResult rsltReln = query.getContrPhysBllgMtrRelnTmpl(glblCmpyCd, svcMachMstrPk, bllgMtrLbCd);
                    if (rsltReln != null && rsltReln.isCodeNormal()) {
                        List<Map<String, Object>> rsltRelnSubList = (List<Map<String, Object>>) rsltReln.getResultObject();
                        for (Map<String, Object> rsltRelnMap : rsltRelnSubList) {
                            rsltRelnMap.put("DS_CONTR_BLLG_MTR_PK", dsContrBllgMtrPk);
                        }
                        rsltRelnList.addAll(rsltRelnSubList);
                    }
                    // END   2017/02/10 [QC#17494, ADD]
                }
                // START 2017/02/10 [QC#17494, ADD]
                setContrPhysBllgMtrReln(bCMsgArray, rsltRelnList);
                // END   2017/02/10 [QC#17494, ADD]
            }
        }
        // START 2018/04/03 K.Kojima [QC#21056,ADD]
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd) && (!DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd))) {
            if (bCMsgArray.getValidCount() == 0) {
                return;
            }

            NSAL0300Query query = NSAL0300Query.getInstance();
            BigDecimal dsContrPk = cMsg.dsContrPk.getValue();
            List<Map<String, Object>> rsltList = query.getInsertBllgMtrInfo(glblCmpyCd, dsContrPk, DS_CONTR_DTL_TP.AGGREGATE, svcMachMstrPk);
            if (rsltList == null || rsltList.size() == 0) {
                return;
            }
            List<String> bllgMtrLbCdList = new ArrayList<String>();
            for (Map<String, Object> rsltMap : rsltList) {
                bllgMtrLbCdList.add((String) rsltMap.get("BLLG_MTR_LB_CD"));
            }

            BigDecimal aggLineDsContrDtlPk = bCMsgArray.no(0).dsContrDtlPk_B.getValue();
            int insCount = 0;
            int bDestIdx = bCMsgArray.getValidCount() - 1;
            Map<String, BigDecimal> dsContrBllgMtrMap = new HashMap<String, BigDecimal>();
            for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
                BigDecimal dsContrDtlPk = bCMsgArray.no(i).dsContrDtlPk_B.getValue();
                if (!isEqualNum(dsContrDtlPk, aggLineDsContrDtlPk)) {
                    continue;
                }
                String aggLineBllgMtrLbCd = bCMsgArray.no(i).bllgMtrLbCd_B.getValue();
                if (!ZYPCommonFunc.hasValue(aggLineBllgMtrLbCd)) {
                    continue;
                }
                if (!bllgMtrLbCdList.contains(aggLineBllgMtrLbCd)) {
                    continue;
                }
                if (insCount != 0) {
                    bDestIdx++;
                    bCMsgArray.setValidCount(bDestIdx + 1);
                    EZDMsg.copy(bCMsgArray.no(bDestIdx - 1), null, bCMsgArray.no(bDestIdx), null);
                }
                BigDecimal dsContrBllgMtrPk = dsContrBllgMtrMap.get(aggLineBllgMtrLbCd);
                if (dsContrBllgMtrPk == null) {
                    dsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_BLLG_MTR_SQ);
                    dsContrBllgMtrMap.put(aggLineBllgMtrLbCd, dsContrBllgMtrPk);
                }

                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).dsContrBllgMtrPk_B, dsContrBllgMtrPk);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).bllgMtrLbCd_B, bCMsgArray.no(i).bllgMtrLbCd_B);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).mtrLbDescTxt_B, bCMsgArray.no(i).mtrLbDescTxt_B);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).mtrLbDescTxt_BX, bCMsgArray.no(i).mtrLbDescTxt_BX);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).bllgMtrBllgCycleCd_B, bCMsgArray.no(i).bllgMtrBllgCycleCd_B);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xsChrgTpCd_B, bCMsgArray.no(i).xsChrgTpCd_B);
                bCMsgArray.no(bDestIdx).xsMtrCopyQty_B.clear();
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xsMtrAmtRate_B, bCMsgArray.no(i).xsMtrAmtRate_B);
                bCMsgArray.no(bDestIdx).bllgFreeCopyCnt_B.clear();
                bCMsgArray.no(bDestIdx).bllgMinCnt_B.clear();
                bCMsgArray.no(bDestIdx).bllgMinAmtRate_B.clear();
                bCMsgArray.no(bDestIdx).bllgRollOverRatio_B.clear();
                // START 2019/01/17 M.Naito [QC#29083,ADD]
                bCMsgArray.no(bDestIdx).cumCopyCnt_B.clear();
                bCMsgArray.no(bDestIdx).cumCopyFreqMthAot_B.clear();
                bCMsgArray.no(bDestIdx).cumCopyStartDt_B.clear();
                bCMsgArray.no(bDestIdx).cumCopyEndDt_B.clear();
                // END 2019/01/17 M.Naito [QC#29083,ADD]
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxFilePathTxt_BD, IMG_OPEN_MACHINE_RED);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxFilePathTxt_BA, IMG_CLOSE_MACHINE_GREEN);
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(bDestIdx).xxFilePathTxt_BC, IMG_CLOSE_ARROW);

                insCount++;
            }
        }
        // END 2018/04/03 K.Kojima [QC#21056,ADD]
    }
    // END   2017/02/06 [QC#17275, ADD]

    /**
     * checkAndSetCtacPsnPk
     * @param cMsg NSAL0300CMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param billToCustCd String
     * @param ctacPsnFirstNmItem EZDCStringItem
     * @param ctacPsnLastNmItem EZDCStringItem
     * @param ctacPsnPkItem EZDCBigDecimalItem
     * @return true:OK, false:NG
     */
    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//    public static boolean checkAndSetCtacPsnPk(String glblCmpyCd, EZDCBigDecimalItem ctacPsnPkItem, EZDCStringItem psnNmItem) {
    public static boolean checkAndSetCtacPsnPk(NSAL0300CMsg cMsg, String glblCmpyCd, String slsDt, String billToCustCd, EZDCStringItem ctacPsnFirstNmItem, EZDCStringItem ctacPsnLastNmItem, EZDCBigDecimalItem ctacPsnPkItem) {
    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        // mod start 2016/09/23 CSA Defect#13686
        // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//        if (!ZYPCommonFunc.hasValue(psnNmItem) && !ZYPCommonFunc.hasValue(ctacPsnPkItem)) {
//            return true;
//        }
//        if (!ZYPCommonFunc.hasValue(psnNmItem) && ZYPCommonFunc.hasValue(ctacPsnPkItem)) {
//            ctacPsnPkItem.clear();
//            return true;
//        }
        if (ZYPCommonFunc.hasValue(ctacPsnFirstNmItem) && !ZYPCommonFunc.hasValue(ctacPsnLastNmItem)) {
            ctacPsnLastNmItem.setErrorInfo(1, NSAM0189E, new String[] {"Contact First Name", "Contact Last Name" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(ctacPsnFirstNmItem) && ZYPCommonFunc.hasValue(ctacPsnLastNmItem)) {
            ctacPsnFirstNmItem.setErrorInfo(1, NSAM0189E, new String[] {"Contact Last Name", "Contact First Name" });
            return false;
        }
        if (!ZYPCommonFunc.hasValue(ctacPsnFirstNmItem) && !ZYPCommonFunc.hasValue(ctacPsnLastNmItem)) {
            if (ZYPCommonFunc.hasValue(ctacPsnPkItem)) {
                ctacPsnPkItem.clear();
            }
            return true;
        }
        // END 2018/06/18 K.Kitachi [QC#18591, MOD]
        // mod end 2016/09/23 CSA Defect#13686

        // START 2018/06/18 K.Kitachi [QC#18591, DEL]
//        NSAL0300Query query = NSAL0300Query.getInstance();
//        S21SsmEZDResult rslt = query.getCtacPsnPk(glblCmpyCd, psnNmItem.getValue());
//        if (rslt == null || rslt.getQueryResultCount() == 0) {
//            return false;
//        } else if (rslt.getQueryResultCount() == 1) {
//            ZYPEZDItemValueSetter.setValue(ctacPsnPkItem, ((List<BigDecimal>) rslt.getResultObject()).get(0));
//            return true;
//        } else if (!ZYPCommonFunc.hasValue(ctacPsnPkItem)) {
//            return false;
//        } else {
//            List<BigDecimal> rsltList = (List<BigDecimal>) rslt.getResultObject();
//            boolean existDb = false;
//            for (BigDecimal dbCtacPsnPk : rsltList) {
//                if (dbCtacPsnPk.compareTo(ctacPsnPkItem.getValue()) == 0) {
//                    existDb = true;
//                    break;
//                }
//            }
//            if (existDb) {
//                return true;
//            } else {
//                return false;
//            }
//        }
        // END 2018/06/18 K.Kitachi [QC#18591, DEL]

        // START 2018/06/18 K.Kitachi [QC#18591, ADD]
        String locNum = getLocNum(glblCmpyCd, billToCustCd);
        NMZC002001PMsg ctacUpdApiReqPMsg = createCtacUpdApiReqPMsg(cMsg, glblCmpyCd, slsDt, locNum, ctacPsnFirstNmItem.getValue(), ctacPsnLastNmItem.getValue(), NMZC002001Constant.PROCESS_MODE_GET_CTAC_PSN_PK);
        if (!callCtacUpdApi(ctacUpdApiReqPMsg, ctacPsnFirstNmItem, ctacPsnLastNmItem, ctacPsnPkItem)) {
            return false;
        }
        if (hasValue(ctacUpdApiReqPMsg.ctacPsnPk)) {
            return true;
        }

        ctacUpdApiReqPMsg = createCtacUpdApiReqPMsg(cMsg, glblCmpyCd, slsDt, locNum, ctacPsnFirstNmItem.getValue(), ctacPsnLastNmItem.getValue(), NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        if (!callCtacUpdApi(ctacUpdApiReqPMsg, ctacPsnFirstNmItem, ctacPsnLastNmItem, ctacPsnPkItem)) {
            return false;
        }
        return true;
        // END 2018/06/18 K.Kitachi [QC#18591, ADD]
    }

    /**
     * setDisplayMode
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     */
    public static void setDisplayMode(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (NSAL0300CommonLogic.hasError(cMsg)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            DS_CONTR_STS_VTMsg dsContrStsVTMsg = query.getDsContrStsV(glblCmpyCd, cMsg.dsContrPk.getValue());
            if (dsContrStsVTMsg != null) {
                String dsContrCtrlStsCd = dsContrStsVTMsg.dsContrCtrlStsCd.getValue();
                ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd, dsContrCtrlStsCd);
                // START 2016/02/17 T.Kanasaka [QC3023, MOD]
//                if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.CANCELLED.equals(dsContrCtrlStsCd)) {
                if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd)) {
                // END 2016/02/17 T.Kanasaka [QC3023, MOD]
                    ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_FU, DISPLAY_MODE_UPDATE);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_FU, DISPLAY_MODE_FREEZE);
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_FU, DISPLAY_MODE_UPDATE);
        }
    }

    /**
     * checkPtyLocPk
     * @param glblCmpyCd glblCmpyCd
     * @param ctacPsnPk ctacPsnPk
     * @param billToCustCd billToCustCd
     * @return true:OK, false:NG
     */
    public static boolean checkCtacPsnReln(String glblCmpyCd, BigDecimal ctacPsnPk, String billToCustCd) {
        if (!ZYPCommonFunc.hasValue(ctacPsnPk) || !ZYPCommonFunc.hasValue(billToCustCd)) {
            return true;
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        S21SsmEZDResult rslt = query.getCtacPsnReln(glblCmpyCd, ctacPsnPk, billToCustCd);
        if (rslt != null && rslt.isCodeNormal() && rslt.getQueryResultCount() != 0) {
            return true;
        }
        return false;
    }

    /**
     * setUplftFlg
     * @param glblCmpyCd glblCmpyCd
     * @param tMsg DS_CONTR_RNW_ESCLTMsg
     */
    public static void setUplftFlg(String glblCmpyCd, DS_CONTR_RNW_ESCLTMsg tMsg) {
        String contrUplftTpCd = tMsg.contrUplftTpCd.getValue();

        // set default
        ZYPEZDItemValueSetter.setValue(tMsg.firstYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.scdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.frthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(tMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_OFF_N);

        NSAL0300Query query = NSAL0300Query.getInstance();
        CONTR_UPLFT_TPTMsg contrUplftTpTMsg = query.getUplftTp(glblCmpyCd, contrUplftTpCd);
        if (contrUplftTpTMsg != null) {
            if (ZYPConstant.FLG_ON_Y.equals(contrUplftTpTMsg.uplftBaseFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(contrUplftTpTMsg.uplftUsgFlg.getValue())) {
                BigDecimal defContrUplftTermAot = ZYPCodeDataUtil.getNumConstValue(NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT, glblCmpyCd);
                if (!ZYPCommonFunc.hasValue(defContrUplftTermAot)) {
                    defContrUplftTermAot = DEF_CONTR_UPLFT_TERM_AOT;
                }
                int termAot = defContrUplftTermAot.intValue();
                if (termAot > 10) {
                    termAot = 10;
                }
                switch (termAot) {
                    case 10:
                        ZYPEZDItemValueSetter.setValue(tMsg.tenthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 9:
                        ZYPEZDItemValueSetter.setValue(tMsg.ninthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 8:
                        ZYPEZDItemValueSetter.setValue(tMsg.eighthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 7:
                        ZYPEZDItemValueSetter.setValue(tMsg.svnthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 6:
                        ZYPEZDItemValueSetter.setValue(tMsg.sixthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 5:
                        ZYPEZDItemValueSetter.setValue(tMsg.fifthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 4:
                        ZYPEZDItemValueSetter.setValue(tMsg.frthYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 3:
                        ZYPEZDItemValueSetter.setValue(tMsg.thirdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 2:
                        ZYPEZDItemValueSetter.setValue(tMsg.scdYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    case 1:
                        ZYPEZDItemValueSetter.setValue(tMsg.firstYrContrUplftFlg, ZYPConstant.FLG_ON_Y);
                    default:
                }
            }
        }
    }

    // START 2016/01/21 T.Tomita [QC#2182, ADD]
    /**
     * searchBranch
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     */
    public static void searchBranch(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300Query query = NSAL0300Query.getInstance();
        S21SsmEZDResult rslt = query.getBranch(glblCmpyCd, cMsg.svcContrBrCd.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
            if (rslt.getQueryResultCount() > 0) {
                Map<String, String> rsltMap = rsltList.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.svcContrBrDescTxt, (String) rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
            }
        } else {
            // mod start 2016/04/08 CSA Defect#5312,5313
            //cMsg.svcContrBrCd.setErrorInfo(1, NSAM0072E, new String[] {"Branch" });
            cMsg.xxDplyByCdNmCnctTxt.setErrorInfo(1, NSAM0072E, new String[] {"Branch" });
            // mod end 2016/04/08 CSA Defect#5312,5313
        }
    }

    /**
     * searchRep
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     */
    public static void searchRep(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300Query query;
        S21SsmEZDResult rslt;
        Map<String, String> rsltMap;
        // set PSN_CD
        if (!ZYPCommonFunc.hasValue(cMsg.contrAdminPsnCd) && ZYPCommonFunc.hasValue(cMsg.xxPsnNm)) {
            query = NSAL0300Query.getInstance();
            rslt = query.getContrAdminPsnCd(glblCmpyCd, cMsg.xxPsnNm.getValue());
            if (rslt != null && rslt.isCodeNormal() && rslt.getQueryResultCount() == 1) {
                List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
                rsltMap = rsltList.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.contrAdminPsnCd, (String) rsltMap.get("PSN_CD"));
            } else {
                if (rslt != null && rslt.getQueryResultCount() > 1) {
                    // mod start 2016/04/08 CSA Defect#5312,5313
                    //cMsg.contrAdminPsnCd.setErrorInfo(1, NSAM0418E, new String[] {"Rep Name", "Rep Code" });
                    cMsg.xxPsnNm.setErrorInfo(1, NSAM0418E, new String[] {"Branch Rep", "Branch Rep" });
                    // mod end 2016/04/08 CSA Defect#5312,5313
                } else {
                    cMsg.xxPsnNm.setErrorInfo(1, NSAM0045E, new String[] {"Branch Rep" });
                }
                return;
            }
        }
    }

    /**
     * checkSvcBrResrcReln
     * @param glblCmpyCd
     * @param cMsg
     * @param sMsg
     * @return boolean (true:check OK, false:check NG)
     */
    public static boolean checkSvcBrResrcReln(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.svcContrBrCd) || !ZYPCommonFunc.hasValue(cMsg.contrAdminPsnCd)) {
            return true;
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        S21SsmEZDResult rslt = query.countSvcBrResrcReln(glblCmpyCd, cMsg.svcContrBrCd.getValue(), cMsg.contrAdminPsnCd.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            BigDecimal count = (BigDecimal) rslt.getResultObject();
            if (count == null || BigDecimal.ZERO.compareTo(count) == 0) {
                return false;
            }
        }
        return true;
    }
    // END 2016/01/21 T.Tomita [QC#2182, ADD]

    // START 2016/01/21 T.Tomita [QC#2182, MOD]
    /**
     * searchDefaultRep
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     */
    public static void searchDefaultRep(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300Query query = NSAL0300Query.getInstance();
        S21SsmEZDResult rslt = query.getRepInfo(glblCmpyCd, cMsg.dsAcctNum.getValue(), cMsg.svcLineBizCd.getValue(), cMsg.svcContrBrCd.getValue());
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, String>> rsltList = (List<Map<String, String>>) rslt.getResultObject();
            if (rslt.getQueryResultCount() > 0) {
                Map<String, String> rsltMap = rsltList.get(0);
//                ZYPEZDItemValueSetter.setValue(cMsg.svcContrBrDescTxt, (String) rsltMap.get("SVC_CONTR_BR_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.contrAdminPsnCd, (String) rsltMap.get("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm, (String) rsltMap.get("PSN_NM"));
            }
        } else {
//            cMsg.svcContrBrDescTxt.clear();
            cMsg.contrAdminPsnCd.clear();
            cMsg.xxPsnNm.clear();
//            cMsg.svcLineBizCd.setErrorInfo(1, NSAM0040E, new String[] {"Branch" });
//            cMsg.svcContrBrCd.setErrorInfo(1, NSAM0040E, new String[] {"Branch" });
        }
        if (!ZYPCommonFunc.hasValue(cMsg.contrAdminPsnCd)) {
//            cMsg.svcLineBizCd.setErrorInfo(1, NSAM0040E, new String[] {"Branch" });
//            cMsg.svcContrBrCd.setErrorInfo(1, NSAM0040E, new String[] {"Branch" });
            cMsg.contrAdminPsnCd.setErrorInfo(1, NSAM0045E, new String[] {"Branch Rep" });
        }
    }
    // END 2016/01/21 T.Tomita [QC#2182, MOD]

    // START 2016/02/24 T.Kanasaka [QC4079, ADD]
    /**
     * searchSalesRep
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     */
    public static void searchSalesRep(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.tocCd) && !ZYPCommonFunc.hasValue(cMsg.tocNm)) {
            return;
        }

        // START 2016/04/26 T.Tomita [QC#4668, MOD]
        NSAL0300Query query = NSAL0300Query.getInstance();
//        S21SsmEZDResult rslt = query.getSalesRepInfo(glblCmpyCd, cMsg.tocCd.getValue(), cMsg.tocNm.getValue());
        // START 2018/06/18 K.Kim [QC#25195, MOD]
        // S21SsmEZDResult rslt = query.getTocInfo(glblCmpyCd, cMsg.tocCd.getValue());
        S21SsmEZDResult rslt = query.getTocInfo(glblCmpyCd, cMsg.tocCd.getValue(), cMsg.tocNm.getValue());
        // END 2018/06/18 K.Kim [QC#25195, MOD]
        if (rslt == null || rslt.getQueryResultCount() == 0) {
//            if (ZYPCommonFunc.hasValue(cMsg.tocCd)) {
//                cMsg.tocCd.setErrorInfo(1, NSAM0045E, new String[] {"Sales Rep" });
//            }
            if (ZYPCommonFunc.hasValue(cMsg.tocNm)) {
                cMsg.tocNm.setErrorInfo(1, NSAM0045E, new String[] {"Sales Rep Name" });
            }
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> resultMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.tocCd, (resultMap.get("TOC_CD")));
            ZYPEZDItemValueSetter.setValue(cMsg.tocNm, (resultMap.get("TOC_NM")));
        } else {
            // START 2018/06/18 K.Kim [QC#25195, MOD]
            // cMsg.contrAdminPsnCd.setErrorInfo(1, NSAM0418E, new String[] {"Sales Rep Name", "Sales Rep" });
            cMsg.tocNm.setErrorInfo(1, NSAM0418E, new String[] {"Sales Rep Name", "Sales Rep" });
            // END 2018/06/18 K.Kim [QC#25195, MOD]
        }
        // END 2016/04/26 T.Tomita [QC#4668, MOD]
    }
    // END 2016/02/24 T.Kanasaka [QC4079, ADD]

    // START 2018/06/18 K.Kim [QC#25195, ADD]
    /**
     * searchReportGrp
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     */
    public static void searchReportGrp(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrRptGrpDescTxt)) {
            cMsg.dsContrRptGrpNum.clear();
            return;
        }

        S21SsmEZDResult rslt = NSAL0300Query.getInstance().findDsContrRptGrp(glblCmpyCd, cMsg.dsContrRptGrpDescTxt.getValue(), cMsg.dsContrRptGrpNum.getValue());
        if (rslt == null || rslt.getQueryResultCount() == 0) {
            cMsg.dsContrRptGrpDescTxt.setErrorInfo(1, NSAM0072E, new String[] {"Report Group" });
        } else if (rslt.getQueryResultCount() == 1) {
            Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);
            setValue(cMsg.dsContrRptGrpNum, rsltMap.get("DS_CONTR_RPT_GRP_NUM"));
            setValue(cMsg.dsContrRptGrpDescTxt, rsltMap.get("DS_CONTR_RPT_GRP_DESC_TXT"));
        } else {
            cMsg.dsContrRptGrpDescTxt.setErrorInfo(1, NSAM0418E, new String[] {"Report Group", "Report Group" });
        }
    }
    // END 2018/06/18 K.Kim [QC#25195, ADD]

    /**
     * addMinute
     * @param date yyyyMMddHHmmssSSS
     * @param minute Additional minute
     * @return result date
     */
    public static String addMinute(String date, int minute) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_FULL);
        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            throw new S21AbendException(e);
        }
        calendar.add(Calendar.MINUTE, minute);
        date = format.format(calendar.getTime());
        return date;
    }

    /**
     * getChildAccessoryBCMsgList
     * @param bCMsgArray NSAL0300_BCMsgArray
     * @param dsContrDtlPk Parent DS_CONTR_DTL_PK
     * @return List<NSAL0300_BCMsg>
     */
    public static List<NSAL0300_BCMsg> getChildAccessoryBCMsgList(NSAL0300_BCMsgArray bCMsgArray, BigDecimal dsContrDtlPk) {
        List<NSAL0300_BCMsg> resultList = new ArrayList<NSAL0300_BCMsg>();
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return resultList;
        }
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(bCMsgArray.no(i).xxDplyCtrlFlg_B0.getValue()) && dsContrDtlPk.compareTo(bCMsgArray.no(i).prntDsContrDtlPk_B.getValue()) == 0) {
                resultList.add(bCMsgArray.no(i));
            }
        }
        return resultList;
    }

    /**
     * getChildAccessoryACMsgList
     * @param aCMsgArray NSAL0300_ACMsgArray
     * @param dsContrDtlPk Parent DS_CONTR_DTL_PK
     * @return List<NSAL0300_ACMsg>
     */
    public static List<NSAL0300_ACMsg> getChildAccessoryACMsgList(NSAL0300_ACMsgArray aCMsgArray, BigDecimal dsContrDtlPk) {
        List<NSAL0300_ACMsg> resultList = new ArrayList<NSAL0300_ACMsg>();
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return resultList;
        }
        for (int i = 0; i < aCMsgArray.getValidCount(); i++) {
            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(aCMsgArray.no(i).dsContrDtlTpCd_A.getValue()) && dsContrDtlPk.compareTo(aCMsgArray.no(i).prntDsContrDtlPk_A.getValue()) == 0) {
                resultList.add(aCMsgArray.no(i));
            }
        }
        return resultList;
    }

    /**
     * getChildAccessoryInfoList
     * @param aSMsgArray NSAL0300_ASMsgArray
     * @param dsContrDtlPk Parent DS_CONTR_DTL_PK
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getChildAccessoryInfoList(NSAL0300_ASMsgArray aSMsgArray, BigDecimal dsContrDtlPk) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            return resultList;
        }
        for (int i = 0; i < aSMsgArray.getValidCount(); i++) {
            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(aSMsgArray.no(i).dsContrDtlTpCd_A.getValue()) && dsContrDtlPk.compareTo(aSMsgArray.no(i).prntDsContrDtlPk_A.getValue()) == 0) {
                Map<String, Object> infoMap = new HashMap<String, Object>();
                infoMap.put(MAP_KEY_SER_NUM, aSMsgArray.no(i).serNum_A.getValue());
                infoMap.put(MAP_KEY_MDSE_CD, aSMsgArray.no(i).mdseCd_A.getValue());
                infoMap.put(MAP_KEY_DS_CONTR_DTL_PK, aSMsgArray.no(i).dsContrDtlPk_A.getValue());
                resultList.add(infoMap);
            }
        }
        return resultList;
    }

    /**
     * getParentBCMsg
     * @param bCMsgArray NSAL0300_BCMsgArray
     * @param prntDsContrDtlPk Parent DS_CONTR_DTL_PK
     * @return List<NSAL0300_BCMsg>
     */
    public static NSAL0300_BCMsg getParentBCMsg(NSAL0300_BCMsgArray bCMsgArray, BigDecimal prntDsContrDtlPk) {
        if (!ZYPCommonFunc.hasValue(prntDsContrDtlPk)) {
            return null;
        }
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (prntDsContrDtlPk.compareTo(bCMsgArray.no(i).dsContrDtlPk_B.getValue()) == 0) {
                return bCMsgArray.no(i);
            }
        }
        return null;
    }

    /**
     * callContractTrackingAPI
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param dsContrPk DS_CONTR_PK
     * @param dsContrDtlPk DS_CONTR_DTL_PK
     * @param dsContrBllgMtrPk DS_CONTR_BLLG_MTR_PK
     * @param dsContrPrcEffPk DS_CONTR_PRC_EFF_PK
     * @param contrPrcEffFromDt CONTR_PRC_EFF_FROM_DT
     * @param contrPrcEffThruDt CONTR_PRC_EFF_THRU_DT
     * @param baseChrgFlg BASE_CHAR_FLG
     * @return result (true:OK, false:NG)
     */
    public static boolean callContractTrackingAPI(String glblCmpyCd, NSAL0300CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String baseChrgFlg) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.USER_OPERATION.code);

        if (ZYPCommonFunc.hasValue(dsContrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.CONTRACT_HEADER);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        }

        if (ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
            if (ZYPConstant.FLG_ON_Y.equals(baseChrgFlg)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
            ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        }

        S21UserProfileService s21UserProfileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo userInfo = s21UserProfileService.getContextUserInfo();
        String usrId = userInfo.getUserId();
        ZYPEZDItemValueSetter.setValue(pMsg.stsMemoUpdPsnCd, usrId);

        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            cMsg.setMessageInfo(msgId, msgPrms);
            return false;
        }

        return true;
    }

    // START 2016/03/08 T.Kanasaka [QC2208, MOD]
    /**
     * callContractBillingScheduleAPI
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param baseSchdItemUpdFlgContr boolean
     * @param baseSchdItemUpdMapDtl Map<BigDecimal, Boolean>
     * @param usgSchdItemUpdFlgContr boolean
     * @param usgSchdItemUpdMapDtl Map<BigDecimal, Boolean>
     * @param usgSchdItemUpdMapMtr Map<BigDecimal, Boolean>
     * @return result (true:OK, false:NG)
     */
    public static boolean callContractBillingScheduleAPI(String glblCmpyCd, NSAL0300CMsg cMsg, boolean baseSchdItemUpdFlgContr, Map<BigDecimal, Boolean> baseSchdItemUpdMapDtl, boolean usgSchdItemUpdFlgContr, Map<BigDecimal, Boolean> usgSchdItemUpdMapDtl, Map<BigDecimal, Boolean> usgSchdItemUpdMapMtr) {

        if (DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue())) {
            return true;
        }

        if (cMsg.A.getValidCount() == 0) {
            // START 2018/06/14 K.Kojima [QC#26615,MOD]
            // return true;
            if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                if (cMsg.B.getValidCount() == 0 || !hasValue(cMsg.B.no(0).basePrcDealAmt_B)) {
                    return true;
                }
            } else {
                return true;
            }
            // END 2018/06/14 K.Kojima [QC#26615,MOD]
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            if (!baseSchdItemUpdMapDtl.isEmpty()) {
                baseSchdItemUpdFlgContr = true;
            }

            if (!usgSchdItemUpdMapDtl.isEmpty() || !usgSchdItemUpdMapMtr.isEmpty()) {
                usgSchdItemUpdFlgContr = true;
            }
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        BigDecimal preDsContrDtlPk = BigDecimal.ONE.negate();
        BigDecimal preDsContrBllgMtrPk = BigDecimal.ONE.negate();

        // Add Start 02/28/2017 <QC#17809>
        BigDecimal aggLinePk = null;
        // Add End   02/28/2017 <QC#17809>
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            BigDecimal dsContrDtlPk = bCMsg.dsContrDtlPk_B.getValue();
            if (ZYPCommonFunc.hasValue(dsContrDtlPk) && !NSAL0300CommonLogic.isEqualNum(preDsContrDtlPk, dsContrDtlPk)) {
                String dsContrDtlTpCd = bCMsg.dsContrDtlTpCd_B.getValue();

                // Base
                if (DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                    //Add Start 09/08/2016 <QC#12429>
                    NSZC047021PMsg m21PMsg = chkPerSeqNum(glblCmpyCd, cMsg, bCMsg, bCMsg.contrCloDay_B.getValue(), bCMsg.baseBllgTmgCd_B.getValue(), bCMsg.baseBllgCycleCd_B.getValue());
                    if (m21PMsg.xxMsgIdList.getValidCount() > 0) {
                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(m21PMsg).get(0);
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        cMsg.setMessageInfo(msgId, msgPrms);
                        return false;
                    }
                    //Add End   09/08/2016 <QC#12429>
                    NSZC047001PMsg pMsg = new NSZC047001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "01");
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrCloDay, bCMsg.contrCloDay_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.baseBllgTmgCd, bCMsg.baseBllgTmgCd_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrBllgDay, bCMsg.contrBllgDay_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, bCMsg.contrEffFromDt_B);
                    // START 2019/11/01 [QC#53317,MOD]
                    if (ZYPCommonFunc.hasValue(bCMsg.contrCloDt_B)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, bCMsg.contrCloDt_B);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, bCMsg.contrEffThruDt_B);
                    }
                    // END 2019/11/01 [QC#53317,MOD]

                    pMsg.xxBaseLineList.setValidCount(1);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, bCMsg.baseBllgCycleCd_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, bCMsg.basePrcDealAmt_B);
                    // Mod Start 10/06/2017 QC#21222
                    // Mod Start 09/13/2017 QC#21062
                    if (baseSchdItemUpdFlgContr || baseSchdItemUpdMapDtl.containsKey(dsContrDtlPk)) {
                        // START 2017/11/10 K.Kojima [QC#22435,ADD]
                        BigDecimal invoicedTermAmount = query.getInvoicedTermAmountForBase(glblCmpyCd, dsContrDtlPk);

                        // QC#59240 Add Start
                        BigDecimal totalAmtOfUninvBefFinIn = query.getTotalAmtOfUninvBefFinInv(glblCmpyCd, dsContrDtlPk);
                        if (!ZYPCommonFunc.hasValue(totalAmtOfUninvBefFinIn)) {
                            totalAmtOfUninvBefFinIn = BigDecimal.ZERO;
                        }
                        // QC#59240 Add End

                        boolean invoicedFlag = false;
                        if (ZYPCommonFunc.hasValue(invoicedTermAmount)) {
                            invoicedFlag = true;
                        }
                        // END 2017/11/10 K.Kojima [QC#22435,ADD]
                        // START 2017/11/10 K.Kojima [QC#22435,MOD]
                        // int bllgCycleCnt = calcBllgCycleCntFromDuration(bCMsg, glblCmpyCd);
                        int bllgCycleCnt = calcBllgCycleCntFromDuration(bCMsg, glblCmpyCd, invoicedFlag);
                        // END 2017/11/10 K.Kojima [QC#22435,MOD]
                        if (bllgCycleCnt > 0 && ZYPCommonFunc.hasValue(bCMsg.basePrcDealAmt_B)) {
                            BigDecimal calculatedTermAmt = bCMsg.basePrcDealAmt_B.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                            // START 2017/10/18 K.Kitachi [QC#21222, ADD]
                            // START 2017/11/10 K.Kojima [QC#22435,DEL]
                            // BigDecimal invoicedTermAmount = query.getInvoicedTermAmountForBase(glblCmpyCd, dsContrDtlPk);
                            // END 2017/11/10 K.Kojima [QC#22435,DEL]
                            if (ZYPCommonFunc.hasValue(invoicedTermAmount)) {
                                // QC#59240 Mod Start
                                //calculatedTermAmt = calculatedTermAmt.add(invoicedTermAmount);
                                calculatedTermAmt = calculatedTermAmt.add(invoicedTermAmount).add(totalAmtOfUninvBefFinIn);
                                // QC#59240 Mod End
                            }
                            // END 2017/10/18 K.Kitachi [QC#21222, ADD]
                            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, calculatedTermAmt);

                            // update term amount
                            updateTermAmtForDsContrDtl(cMsg, glblCmpyCd, dsContrDtlPk, calculatedTermAmt);
                        }
                    }
                    // Mod End 09/13/2017 QC#21062
                    // Mod End 10/06/2017 QC#21222
                    BigDecimal dsContrPrcEffPk = bCMsg.dsContrPrcEffPk_BB.getValue();
                    if (ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
                        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = query.getDsContrPrcEff(glblCmpyCd, dsContrPrcEffPk);
                        if (dsContrPrcEffTMsg != null) {
                            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffPk_BL, dsContrPrcEffPk);
                            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).dsContrPrcEffSqNum_BL, dsContrPrcEffTMsg.dsContrPrcEffSqNum);
                            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).effFromDt_BL, dsContrPrcEffTMsg.contrPrcEffFromDt);
                            ZYPEZDItemValueSetter.setValue(pMsg.xxBaseLineList.no(0).effThruDt_BL, dsContrPrcEffTMsg.contrPrcEffThruDt);
                        }
                    }

                    if (baseSchdItemUpdFlgContr || baseSchdItemUpdMapDtl.containsKey(dsContrDtlPk)) {
                        // Add Start 02/28/2017 <QC#17809>
                        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                            aggLinePk = dsContrDtlPk;
                        }
                        // Add End   02/28/2017 <QC#17809>
                        NSZC047001 api = new NSZC047001();
                        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                        if (pMsg.xxMsgIdList.getValidCount() > 0) {
                            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                            String msgId = msg.getXxMsgid();
                            String[] msgPrms = msg.getXxMsgPrmArray();
                            cMsg.setMessageInfo(msgId, msgPrms);
                            return false;
                        }
                        // START 2017/09/11 K.Kojima [QC#18435,ADD]
                        if (!callContractTrackingAPIForPE(glblCmpyCd, cMsg, dsContrDtlPk, null, ZYPConstant.FLG_ON_Y)){
                            return false;
                        }
                        // END 2017/09/11 K.Kojima [QC#18435,ADD]
                    }
                }
            }
            preDsContrDtlPk = dsContrDtlPk;
        }
        // Add Start 02/28/2017 <QC#17809>
        // START 2018/04/23 K.Kojima [QC#25595,DEL]
        // if (hasValue(aggLinePk)) {
        //     NSZC047011PMsg pMsg = new NSZC047011PMsg();
        //     ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        //     ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
        //     ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID));
        //     ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, aggLinePk);
        //     NSZC047001 api = new NSZC047001();
        //     api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        //     if (pMsg.xxMsgIdList.getValidCount() > 0) {
        //         S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
        //         String msgId = msg.getXxMsgid();
        //         String[] msgPrms = msg.getXxMsgPrmArray();
        //         cMsg.setMessageInfo(msgId, msgPrms);
        //         return false;
        //     }
        //     // START 2017/09/11 K.Kojima [QC#18435,ADD]
        //     if (!callContractTrackingAPIForPE(glblCmpyCd, cMsg, aggLinePk, null, ZYPConstant.FLG_ON_Y)){
        //         return false;
        //     }
        //     // END 2017/09/11 K.Kojima [QC#18435,ADD]
        // }
        // END 2018/04/23 K.Kojima [QC#25595,DEL]
        // Add End   02/28/2017 <QC#17809>

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            // START 2018/04/23 K.Kojima [QC#25595,ADD]
            BigDecimal dsContrDtlPk = bCMsg.dsContrDtlPk_B.getValue();
            // END 2018/04/23 K.Kojima [QC#25595,ADD]
            BigDecimal dsContrBllgMtrPk = bCMsg.dsContrBllgMtrPk_B.getValue();
            if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !NSAL0300CommonLogic.isEqualNum(preDsContrBllgMtrPk, dsContrBllgMtrPk)) {
                String dsContrDtlTpCd = bCMsg.dsContrDtlTpCd_B.getValue();

                // Usage
                if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                    //Add Start 09/08/2016 <QC#12429>
                    NSZC047021PMsg m21PMsg = chkPerSeqNum(glblCmpyCd, cMsg, bCMsg, bCMsg.mtrCloDay_B.getValue(), bCMsg.mtrBllgTmgCd_B.getValue(), bCMsg.bllgMtrBllgCycleCd_B.getValue());
                        if (m21PMsg.xxMsgIdList.getValidCount() > 0) {
                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(m21PMsg).get(0);
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        cMsg.setMessageInfo(msgId, msgPrms);
                        return false;
                    }
                    //Add End   09/08/2016 <QC#12429>
                    NSZC047001PMsg pMsg = new NSZC047001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "01");
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID));
                    ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, bCMsg.dsContrDtlPk_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
                    ZYPEZDItemValueSetter.setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(pMsg.mtrCloDay, bCMsg.mtrCloDay_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgTmgCd, bCMsg.mtrBllgTmgCd_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.mtrBllgDay, bCMsg.mtrBllgDay_B);
                    ZYPEZDItemValueSetter.setValue(pMsg.contrEffFromDt, bCMsg.contrEffFromDt_B);
                    // START 2019/11/01 [QC#53317,MOD]
                    if (ZYPCommonFunc.hasValue(bCMsg.contrCloDt_B)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, bCMsg.contrCloDt_B);
                    } else {
                        ZYPEZDItemValueSetter.setValue(pMsg.contrEffThruDt, bCMsg.contrEffThruDt_B);
                    }
                    // END 2019/11/01 [QC#53317,MOD]

                    DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = null;
                    BigDecimal dsContrPrcEffPk = bCMsg.dsContrPrcEffPk_BM.getValue();
                    if (ZYPCommonFunc.hasValue(dsContrPrcEffPk)) {
                        dsContrPrcEffTMsg = query.getDsContrPrcEff(glblCmpyCd, dsContrPrcEffPk);
                    }
                    setMtrLineParam(cMsg, i, dsContrBllgMtrPk, dsContrPrcEffTMsg, pMsg);
                    if (pMsg.xxMtrLineList.getValidCount() > 0 && (usgSchdItemUpdFlgContr || usgSchdItemUpdMapDtl.containsKey(bCMsg.dsContrDtlPk_B.getValue()) || usgSchdItemUpdMapMtr.containsKey(dsContrBllgMtrPk))) {
                        // START 2018/04/23 K.Kojima [QC#25595,ADD]
                        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                            aggLinePk = dsContrDtlPk;
                        }
                        // END 2018/04/23 K.Kojima [QC#25595,ADD]
                        NSZC047001 api = new NSZC047001();
                        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                        if (pMsg.xxMsgIdList.getValidCount() > 0) {
                            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                            String msgId = msg.getXxMsgid();
                            String[] msgPrms = msg.getXxMsgPrmArray();
                            cMsg.setMessageInfo(msgId, msgPrms);
                            return false;
                        }
                        // START 2017/09/11 K.Kojima [QC#18435,ADD]
                        if (!callContractTrackingAPIForPE(glblCmpyCd, cMsg, bCMsg.dsContrDtlPk_B.getValue(), dsContrBllgMtrPk, ZYPConstant.FLG_OFF_N)){
                            return false;
                        }
                        // END 2017/09/11 K.Kojima [QC#18435,ADD]
                    }
                }
            }
            preDsContrBllgMtrPk = dsContrBllgMtrPk;
        }

        // START 2018/04/23 K.Kojima [QC#25595,ADD]
        if (hasValue(aggLinePk)) {
            NSZC047011PMsg pMsg = new NSZC047011PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "11");
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID));
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, aggLinePk);
            NSZC047001 api = new NSZC047001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }
            if (!callContractTrackingAPIForPE(glblCmpyCd, cMsg, aggLinePk, null, ZYPConstant.FLG_ON_Y)){
                return false;
            }
        }
        // END 2018/04/23 K.Kojima [QC#25595,ADD]

        return true;
    }
    // END 2016/03/08 T.Kanasaka [QC2208, MOD]

    //Add Start 09/08/2016 <QC#12429>
    private static NSZC047021PMsg chkPerSeqNum(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300_BCMsg bCMsg, String cloDay, String bllgTmgCd, String bllgCycleCd) {
        NSZC047021PMsg m21PMsg = new NSZC047021PMsg();
        setValue(m21PMsg.glblCmpyCd, glblCmpyCd);
        setValue(m21PMsg.xxModeCd, "21");
        setValue(m21PMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        setValue(m21PMsg.dsContrDtlPk, bCMsg.dsContrDtlPk_B.getValue());
        setValue(m21PMsg.contrCloDay, cloDay);
        setValue(m21PMsg.baseBllgTmgCd, bllgTmgCd);
        setValue(m21PMsg.baseBllgCycleCd, bllgCycleCd);
        setValue(m21PMsg.ccyCd, cMsg.ccyCd);
        setValue(m21PMsg.effFromDt, bCMsg.contrEffFromDt_B);
        setValue(m21PMsg.effThruDt, bCMsg.contrEffThruDt_B);
        setValue(m21PMsg.basePrcDealAmt, BigDecimal.ZERO);

        NSZC047001 api = new NSZC047001();
        api.execute(m21PMsg, ONBATCH_TYPE.ONLINE);
        return m21PMsg;
    }
    //Add End   09/08/2016 <QC#12429>

    /**
     * setMtrLineParam
     * @param cMsg NSAL0300CMsg
     * @param startIndex index of start BCMsg
     * @param dsContrBllgMtrPk BigDecimal
     * @param dsContrPrcEffTMsg DS_CONTR_PRC_EFFTMsg
     * @param pMsg NSZC047001PMsg
     */
    public static void setMtrLineParam(NSAL0300CMsg cMsg, int startIndex, BigDecimal dsContrBllgMtrPk, DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg, NSZC047001PMsg pMsg) {
        int listIndex = 0;
        for (int i = startIndex; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            BigDecimal tmpDsContrBllgMtrPk = bCMsg.dsContrBllgMtrPk_B.getValue();
            if (!ZYPCommonFunc.hasValue(tmpDsContrBllgMtrPk) || !NSAL0300CommonLogic.isEqualNum(tmpDsContrBllgMtrPk, dsContrBllgMtrPk)) {
                break;
            }

            if (!ZYPCommonFunc.hasValue(bCMsg.xsMtrCopyQty_B) || !ZYPCommonFunc.hasValue(bCMsg.xsMtrAmtRate_B)) {
                continue;
            }

            if (dsContrPrcEffTMsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).dsContrPrcEffPk_ML, dsContrPrcEffTMsg.dsContrPrcEffPk);
//                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).dsContrPrcEffMtrPk_ML, );
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).dsContrPrcEffSqNum_ML, dsContrPrcEffTMsg.dsContrPrcEffSqNum);
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).effFromDt_ML, dsContrPrcEffTMsg.contrPrcEffFromDt);
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).effThruDt_ML, dsContrPrcEffTMsg.contrPrcEffThruDt);
            }
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).mtrBllgCycleCd_ML, bCMsg.bllgMtrBllgCycleCd_B);
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).dsContrBllgMtrPk_ML, bCMsg.dsContrBllgMtrPk_B);
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).contrXsCopyPk_ML, bCMsg.contrXsCopyPk_B);
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).xsMtrCopyQty_ML, bCMsg.xsMtrCopyQty_B);
            ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).xsMtrAmtRate_ML, bCMsg.xsMtrAmtRate_B);
            if (i == startIndex) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).xsMtrFirstFlg_ML, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.xxMtrLineList.no(listIndex).xsMtrFirstFlg_ML, ZYPConstant.FLG_OFF_N);
            }

            listIndex++;
        }
        pMsg.xxMtrLineList.setValidCount(listIndex);
    }

    // START 2016/05/16 T.Kanasaka [QC#2184, MOD]
    /**
     * setItemForSave
     * @param cMsg NSAL0300CMsg
     */
    public static void setItemForSave(NSAL0300CMsg cMsg) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            String dsContrDtlTpCd = bCMsg.dsContrDtlTpCd_B.getValue();
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxSelFlg_UT.getValue()) && !DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) && !DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                ZYPEZDItemValueSetter.setValue(bCMsg.mtrDplyPerEndDay_B, bCMsg.baseDplyPerEndDay_B);
                ZYPEZDItemValueSetter.setValue(bCMsg.mtrBllgDay_B, bCMsg.contrBllgDay_B);
            }

            setCloDay(bCMsg.baseDplyPerEndDay_B, bCMsg.contrCloDay_B, bCMsg.contrEffFromDt_B.getValue());
            setCloDay(bCMsg.mtrDplyPerEndDay_B, bCMsg.mtrCloDay_B, bCMsg.contrEffFromDt_B.getValue());
        }
    }

    private static void setCloDay(EZDCStringItem dplyItem, EZDCStringItem cloDayItem, String contrEffFromDt) {
        if (INIT_DAY.equals(dplyItem.getValue())) {
            int fromDt = 0;
            if (ZYPCommonFunc.hasValue(contrEffFromDt)) {
                fromDt = Integer.parseInt(contrEffFromDt.substring(6));
            }
            if (fromDt >= 2 && fromDt <= 28) {
                fromDt--;
                ZYPEZDItemValueSetter.setValue(cloDayItem, new Integer(fromDt).toString());
            } else {
                ZYPEZDItemValueSetter.setValue(cloDayItem, LAST_DAY_OF_A_MONTH);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(cloDayItem, dplyItem);
        }
    }
    // END 2016/05/16 T.Kanasaka [QC#2184, MOD]

    /**
     * setAggregateItem
     * @param cMsg NSAL0300CMsg
     */
     
    // START 2019/05/13 K.Fujimoto [31137/50058, MOD]
    // public static void setAggregateItem(NSAL0300CMsg cMsg) {
    public static void setAggregateItem(NSAL0300CMsg cMsg, String glblCmpyCd) {
        // setCopyQtyForAggr(cMsg);
        setCopyQtyForAggr(cMsg, glblCmpyCd);
    // END   2019/05/13 K.Fujimoto [31137/50058, MOD]
        setBasePrcDealAmtForAggr(cMsg);
        // START 2018/05/14 K.Kitachi [QC#23541, ADD]
        setMinVolMinAmtForAggr(cMsg);
        // END 2018/05/14 K.Kitachi [QC#23541, ADD]

        BigDecimal preDsContrBllgMtrPk = new BigDecimal(-1);
        int allowanceIndex = 0;

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            String dsContrDtlTpCd = bCMsg.dsContrDtlTpCd_B.getValue();
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
                // Base
                NSAL0300_BCMsg aggBCMsg = getAggLine(cMsg);
                if (aggBCMsg != null) {
                    // START 2018/10/24 K.Kitachi [QC#28889, DEL]
//                    ZYPEZDItemValueSetter.setValue(bCMsg.baseBillToCustCd_B, aggBCMsg.baseBillToCustCd_B);
//                    ZYPEZDItemValueSetter.setValue(bCMsg.ctacPsnPk_BB, aggBCMsg.ctacPsnPk_BB);
//                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
////                    ZYPEZDItemValueSetter.setValue(bCMsg.xxPsnNm_BB, aggBCMsg.xxPsnNm_BB);
//                    ZYPEZDItemValueSetter.setValue(bCMsg.ctacPsnFirstNm_BB, aggBCMsg.ctacPsnFirstNm_BB);
//                    ZYPEZDItemValueSetter.setValue(bCMsg.ctacPsnLastNm_BB, aggBCMsg.ctacPsnLastNm_BB);
//                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    // END 2018/10/24 K.Kitachi [QC#28889, DEL]
                    // START 2016/05/16 T.Kanasaka [QC#2184, ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.baseDplyPerEndDay_B, aggBCMsg.baseDplyPerEndDay_B);
                    // END 2016/05/16 T.Kanasaka [QC#2184, ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.contrCloDay_B, aggBCMsg.contrCloDay_B);
                    ZYPEZDItemValueSetter.setValue(bCMsg.contrBllgDay_B, aggBCMsg.contrBllgDay_B);
                    // START 2018/09/27 [QC#27777, DEL]
                    // ZYPEZDItemValueSetter.setValue(bCMsg.svcPgmMdseCd_B, aggBCMsg.svcPgmMdseCd_B);
                    // add start 2016/07/01 CSA Defect#11261
                    // ZYPEZDItemValueSetter.setValue(bCMsg.mdseDescShortTxt_B, aggBCMsg.mdseDescShortTxt_B);
                    // add end 2016/07/01 CSA Defect#11261
                    // END 2018/09/27 [QC#27777, DEL]
                    ZYPEZDItemValueSetter.setValue(bCMsg.baseBllgCycleCd_B, aggBCMsg.baseBllgCycleCd_B);
                    // START 2016/11/16 T.Kanasaka [QC#15942, ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.baseBllgTmgCd_B, aggBCMsg.baseBllgTmgCd_B);
                    // END 2016/11/16 T.Kanasaka [QC#15942, ADD]

                    // Usage
                    // START 2016/05/16 T.Kanasaka [QC#2184, ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.mtrDplyPerEndDay_B, aggBCMsg.mtrDplyPerEndDay_B);
                    // END 2016/05/16 T.Kanasaka [QC#2184, ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.mtrCloDay_B, aggBCMsg.mtrCloDay_B);
                    ZYPEZDItemValueSetter.setValue(bCMsg.mtrBllgDay_B, aggBCMsg.mtrBllgDay_B);
                }

                if (DS_CONTR_DTL_TP.BASE_ONLY.equals(dsContrDtlTpCd) || DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd)) {
                    continue;
                }

                // Meter
                aggBCMsg = getAggLineForMeter(cMsg, bCMsg.bllgMtrLbCd_B.getValue());
                if (aggBCMsg != null) {
                    ZYPEZDItemValueSetter.setValue(bCMsg.bllgMtrBillToCustCd_B, aggBCMsg.bllgMtrBillToCustCd_B);
                    ZYPEZDItemValueSetter.setValue(bCMsg.ctacPsnPk_BM, aggBCMsg.ctacPsnPk_BM);
                    // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                    ZYPEZDItemValueSetter.setValue(bCMsg.xxPsnNm_BM, aggBCMsg.xxPsnNm_BM);
                    ZYPEZDItemValueSetter.setValue(bCMsg.ctacPsnFirstNm_BM, aggBCMsg.ctacPsnFirstNm_BM);
                    ZYPEZDItemValueSetter.setValue(bCMsg.ctacPsnLastNm_BM, aggBCMsg.ctacPsnLastNm_BM);
                    // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.bllgMtrBllgCycleCd_B, aggBCMsg.bllgMtrBllgCycleCd_B);
                    ZYPEZDItemValueSetter.setValue(bCMsg.xsChrgTpCd_B, aggBCMsg.xsChrgTpCd_B);
                    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
                    ZYPEZDItemValueSetter.setValue(bCMsg.bllgRollOverRatio_B, aggBCMsg.bllgRollOverRatio_B);
                    // END 2018/05/14 K.Kitachi [QC#23541, ADD]
                }

                // Excess
                BigDecimal dsContrBllgMtrPk = bCMsg.dsContrBllgMtrPk_B.getValue();
                // START 2016/07/05 T.Tomita [QC#10594, MOD]
                if (isEqualNum(dsContrBllgMtrPk, preDsContrBllgMtrPk)) {
                    allowanceIndex++;
                } else {
                    allowanceIndex = 0;
                }
                // END 2016/07/05 T.Tomita [QC#10594, MOD]

                aggBCMsg = getAggLineForExcess(cMsg, bCMsg.bllgMtrLbCd_B.getValue(), allowanceIndex);
                if (aggBCMsg != null) {
                    ZYPEZDItemValueSetter.setValue(bCMsg.xsMtrAmtRate_B, aggBCMsg.xsMtrAmtRate_B);
                }
                preDsContrBllgMtrPk = bCMsg.dsContrBllgMtrPk_B.getValue();
            }
        }
    }

    /**
     * getAggLineForBase
     * @param cMsg NSAL0300CMsg
     * @param return aggregate NSAL0300_BCMsg
     */
    public static NSAL0300_BCMsg getAggLine(NSAL0300CMsg cMsg) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsg.dsContrDtlTpCd_B.getValue())) {
                return cMsg.B.no(i);
            }
        }
        return null;
    }

    /**
     * getAggLineForMeter
     * @param cMsg NSAL0300CMsg
     * @param bllgMtrLbCd BLLG_MTR_LB_CD_B
     * @param return aggregate NSAL0300_BCMsg
     */
    public static NSAL0300_BCMsg getAggLineForMeter(NSAL0300CMsg cMsg, String bllgMtrLbCd) {
        if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return null;
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsg.dsContrDtlTpCd_B.getValue()) && bllgMtrLbCd.equals(bCMsg.bllgMtrLbCd_B.getValue())) {
                return cMsg.B.no(i);
            }
        }
        return null;
    }

    /**
     * getAggLineForExcess
     * @param cMsg NSAL0300CMsg
     * @param bllgMtrLbCd BLLG_MTR_LB_CD_B
     * @param allowanceIndex allowance Index
     * @param return aggregate NSAL0300_BCMsg
     */
    public static NSAL0300_BCMsg getAggLineForExcess(NSAL0300CMsg cMsg, String bllgMtrLbCd, int allowanceIndex) {
        if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return null;
        }

        int aggAllowanceIndex = 0;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsg.dsContrDtlTpCd_B.getValue()) && bllgMtrLbCd.equals(bCMsg.bllgMtrLbCd_B.getValue())) {
                if (aggAllowanceIndex == allowanceIndex) {
                    return cMsg.B.no(i);
                }
                aggAllowanceIndex++;
            }
        }
        return null;
    }

    /**
     * setBasePrcDealAmtForAggr
     * @param cMsg NSAL0300CMsg
     */
    public static void setBasePrcDealAmtForAggr(NSAL0300CMsg cMsg) {
        BigDecimal aggBasePrcDealAmt = BigDecimal.ZERO;
        BigDecimal preDsContrDtlPk = BigDecimal.ONE.negate();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            BigDecimal dsContrDtlPk = bCMsg.dsContrDtlPk_B.getValue();
            if (ZYPCommonFunc.hasValue(dsContrDtlPk) && !NSAL0300CommonLogic.isEqualNum(preDsContrDtlPk, dsContrDtlPk)) {
                if (!DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                    if (ZYPCommonFunc.hasValue(bCMsg.basePrcDealAmt_B)) {
                        aggBasePrcDealAmt = aggBasePrcDealAmt.add(bCMsg.basePrcDealAmt_B.getValue());
                    }
                }
            }
            preDsContrDtlPk = dsContrDtlPk;
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0300_BCMsg bCMsg = cMsg.B.no(i);
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(bCMsg.basePrcDealAmt_B, aggBasePrcDealAmt);
            }
        }
    }

    /**
     * getDsContrDtlPkForNoParent
     * @param bCMsgArray NSAL0300_BCMsgArray
     * @return dsContrDtlPk
     */
    public static BigDecimal getDsContrDtlPkForNoParent(NSAL0300_BCMsgArray bCMsgArray) {
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (DS_CONTR_DTL_TP.FLEET.equals(bCMsgArray.no(i).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsgArray.no(i).dsContrDtlTpCd_B.getValue())) {
                return bCMsgArray.no(i).dsContrDtlPk_B.getValue();
            }
        }
        return null;
    }

    /**
     * saveDeleteDsContrDtlPk
     * @param sMsg NSAL0300SMsg
     * @param dsContrDtlPk DS_CONTR_DTL_PK
     */
    public static void saveDeleteDsContrDtlPk(NSAL0300SMsg sMsg, BigDecimal dsContrDtlPk) {
        if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
            boolean isExist = false;
            for (int j = 0; j < sMsg.X.getValidCount(); j++) {
                if (dsContrDtlPk.compareTo(sMsg.X.no(j).dsContrDtlPk_X.getValue()) == 0) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                int newVldCnt = sMsg.X.getValidCount() + 1;
                sMsg.X.setValidCount(newVldCnt);
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(newVldCnt - 1).dsContrDtlPk_X, dsContrDtlPk);
            }
        }
    }

    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }

    // START 2017/09/26 K.Kojima [QC#21221,ADD]
    /**
     * searchDetailForB
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     */
    public static void searchDetailForB(String glblCmpyCd, NSAL0300CMsg cMsg) {
        searchDetailForB(glblCmpyCd, cMsg, ZYPConstant.FLG_OFF_N);
    }
    // END 2017/09/26 K.Kojima [QC#21221,ADD]

    /**
     * searchDetailForB
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     */
    // START 2017/09/26 K.Kojima [QC#21221,MOD]
    // public static void searchDetailForB(String glblCmpyCd, NSAL0300CMsg cMsg) {
    public static void searchDetailForB(String glblCmpyCd, NSAL0300CMsg cMsg, String forScheduleFlag) {
    // END 2017/09/26 K.Kojima [QC#21221,MOD]
        NSAL0300Query query = NSAL0300Query.getInstance();
        // START 2016/02/17 T.Kanasaka [QC3023, MOD]
//        S21SsmEZDResult rslt = query.getDsContrDtlInfo2(glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrCatgCd.getValue(), cMsg.B.length() + 1);
        // START 2017/09/26 K.Kojima [QC#21221,MOD]
        // S21SsmEZDResult rslt = query.getDsContrDtlInfo2(glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrCatgCd.getValue(), cMsg.dsContrStsCd.getValue(), cMsg.B.length() + 1);
        S21SsmEZDResult rslt = query.getDsContrDtlInfo2(glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrCatgCd.getValue(), cMsg.dsContrStsCd.getValue(), cMsg.B.length() + 1, forScheduleFlag);
        // END 2017/09/26 K.Kojima [QC#21221,MOD]
        // END 2016/02/17 T.Kanasaka [QC3023, MOD]
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > cMsg.B.length()) {
                rsltCnt = cMsg.B.length();
                cMsg.setMessageInfo(NZZM0001W);
            }
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ezUpTime_BD, (String) rsltMap.get("EZUPTIME_BD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ezUpTimeZone_BD, (String) rsltMap.get("EZUPTIMEZONE_BD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsContrDtlPk_B, (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).prntDsContrDtlPk_B, (BigDecimal) rsltMap.get("PRNT_DS_CONTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcConfigMstrPk_B, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcMachMstrPk_B, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).serNum_B, (String) rsltMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdseCd_B, (String) rsltMap.get("MDSE_CD"));
                // Add Start 2018/01/10 QC#18552
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdseDescShortTxt_BI, (String) rsltMap.get("MDSE_DESC_SHORT_TXT_BI"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxListNum_B, (BigDecimal) rsltMap.get("UNIT_CNT"));
                // Add End 2018/01/10 QC#18552
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffFromDt_B, (String) rsltMap.get("CONTR_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrEffThruDt_B, (String) rsltMap.get("CONTR_EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsContrDtlTpCd_B, (String) rsltMap.get("DS_CONTR_DTL_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsContrDtlStsCd_B, (String) rsltMap.get("DS_CONTR_DTL_STS_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrReadMethCd_B, (String) rsltMap.get("MTR_READ_METH_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdlNm_B, (String) rsltMap.get("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).nextBllgDt_BB, (String) rsltMap.get("BASE_NEXT_BLLG_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).nextBllgDt_BU, (String) rsltMap.get("USG_NEXT_BLLG_DT"));
                // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).shipToCustCd_B, (String) rsltMap.get("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).shipToLocNm_B, (String) rsltMap.get("SHIP_TO_LOC_NM"));
                // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

                // base
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBillToCustCd_B, (String) rsltMap.get("BASE_BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToLocNm_BB, (String) rsltMap.get("BASE_BILL_TO_LOC_NM"));
                // START 2016/05/16 T.Kanasaka [QC#2184, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseDplyPerEndDay_B, (String) rsltMap.get("BASE_DPLY_PER_END_DAY"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgCycleStartMth_BB, (String) rsltMap.get("BASE_BLLG_CYCLE_START_MTH"));
                // END 2016/05/16 T.Kanasaka [QC#2184, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrCloDay_B, (String) rsltMap.get("BASE_CLO_DAY"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrBllgDay_B, (String) rsltMap.get("BASE_BLLG_DAY"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrCovSvcPk_B, (BigDecimal) rsltMap.get("CONTR_COV_SVC_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcPgmMdseCd_B, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
                // add start 2016/07/01 CSA Defect#11261
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mdseDescShortTxt_B, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                // add end 2016/07/01 CSA Defect#11261
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgCycleCd_B, (String) rsltMap.get("BASE_BLLG_CYCLE_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).basePrcDealAmt_B, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
                if (ZYPCommonFunc.hasValue((String) rsltMap.get("BASE_BLLG_TMG_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgTmgCd_B, (String) rsltMap.get("BASE_BLLG_TMG_CD"));
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgTmgCd_B, DEF_BASE_BLLG_TMG_TP_CD);
                }

                // Start 2019/01/21 T.Wada [QC#29371]
                if (ZYPCommonFunc.hasValue((String) rsltMap.get("SVC_INV_MERGE_TP_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxTpCd_B, (String) rsltMap.get("SVC_INV_MERGE_TP_CD"));
                } 
                else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxTpCd_B, SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE);
                }
                // End 2019/01/21 T.Wada [QC#29371]


                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnPk_BB, (BigDecimal) rsltMap.get("CTAC_PSN_PK"));
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxPsnNm_BB, (String) rsltMap.get("PSN_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnFirstNm_BB, (String) rsltMap.get("CTAC_PSN_FIRST_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnLastNm_BB, (String) rsltMap.get("CTAC_PSN_LAST_NM"));
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).basePrcTermDealAmtRate_B, (BigDecimal) rsltMap.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsContrPrcEffPk_BB, (BigDecimal) rsltMap.get("BASE_BLLG_DS_CONTR_PRC_EFF_PK"));
                // START 2017/09/13 K.Kim [QC#19938, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B6, (String) rsltMap.get("BASE_BILLED_FLG"));
                // END 2017/09/13 K.Kim [QC#19938, ADD]

                // usage
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrCloDay_B, (String) rsltMap.get("MTR_CLO_DAY"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrBllgDay_B, (String) rsltMap.get("MTR_BLLG_DAY"));
                // START 2016/05/16 T.Kanasaka [QC#2184, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrDplyPerEndDay_B, (String) rsltMap.get("MTR_DPLY_PER_END_DAY"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgCycleStartMth_BU, (String) rsltMap.get("MTR_BLLG_CYCLE_START_MTH"));
                // END 2016/05/16 T.Kanasaka [QC#2184, ADD]

                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ezUpTime_BM, (String) rsltMap.get("EZUPTIME_BM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ezUpTimeZone_BM, (String) rsltMap.get("EZUPTIMEZONE_BM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsContrBllgMtrPk_B, (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrLbCd_B, (String) rsltMap.get("BLLG_MTR_LB_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrBillToCustCd_B, (String) rsltMap.get("BLLG_MTR_BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbDescTxt_B, (String) rsltMap.get("MTR_LB_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrBllgCycleCd_B, (String) rsltMap.get("BLLG_MTR_BLLG_CYCLE_CD"));
                if (ZYPCommonFunc.hasValue((String) rsltMap.get("MTR_BLLG_TMG_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrBllgTmgCd_B, (String) rsltMap.get("MTR_BLLG_TMG_CD"));
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrBllgTmgCd_B, DEF_MTR_BLLG_TMG_TP_CD);
                }
                // START 2016/02/08 T.Tomita [QC#3721, MOD]
                if (!ZYPCommonFunc.hasValue((BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK")) && !ZYPCommonFunc.hasValue((String) rsltMap.get("XS_CHRG_TP_CD"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsChrgTpCd_B, XS_CHRG_TP.POINT);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsChrgTpCd_B, (String) rsltMap.get("XS_CHRG_TP_CD"));
                }
                // START 2016/02/08 T.Tomita [QC#3721, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToLocNm_BM, (String) rsltMap.get("BLLG_MTR_BILL_TO_LOC_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnPk_BM, (BigDecimal) rsltMap.get("BLLG_MTR_CTAC_PSN_PK"));
                // START 2018/06/18 K.Kitachi [QC#18591, MOD]
//                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxPsnNm_BM, (String) rsltMap.get("BLLG_MTR_PSN_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnFirstNm_BM, (String) rsltMap.get("BLLG_MTR_CTAC_PSN_FIRST_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnLastNm_BM, (String) rsltMap.get("BLLG_MTR_CTAC_PSN_LAST_NM"));
                // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMinCnt_B, (BigDecimal) rsltMap.get("BLLG_MIN_CNT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMinAmtRate_B, (BigDecimal) rsltMap.get("BLLG_MIN_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgRollOverRatio_B, (BigDecimal) rsltMap.get("BLLG_ROLL_OVER_RATIO"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgFreeCopyCnt_B, (BigDecimal) rsltMap.get("BLLG_FREE_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).dsContrPrcEffPk_BM, (BigDecimal) rsltMap.get("BLLG_MTR_DS_CONTR_PRC_EFF_PK"));

                String firstLineAddr = (String) rsltMap.get("FIRST_LINE_ADDR");
                String secondLineAddr = (String) rsltMap.get("SCD_LINE_ADDR");
                String thirdLineAddr = (String) rsltMap.get("THIRD_LINE_ADDR");
                String fourthLineAddr = (String) rsltMap.get("FRTH_LINE_ADDR");
                String ctyAddr = (String) rsltMap.get("CTY_ADDR");
                String stCd = (String) rsltMap.get("ST_CD");
                String postCd = (String) rsltMap.get("POST_CD");
                String addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToCustLocAddr_BM, addr);

                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ezUpTime_BX, (String) rsltMap.get("EZUPTIME_BX"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ezUpTimeZone_BX, (String) rsltMap.get("EZUPTIMEZONE_BX"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrXsCopyPk_B, (BigDecimal) rsltMap.get("CONTR_XS_COPY_PK"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsMtrCopyQty_B, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xsMtrAmtRate_B, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
                // START 2017/09/13 K.Kim [QC#19938, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B7, (String) rsltMap.get("USG_BILLED_FLG"));
                // END 2017/09/13 K.Kim [QC#19938, ADD]

                // Is Accessory
                if (DS_CONTR_DTL_TP.ACCESSORIES.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B0, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B0, ZYPConstant.FLG_OFF_N);
                }
                // Is Open Accessory
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B1, ZYPConstant.FLG_OFF_N);

                // Is Price Effectivity(Base) Exist
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B2, (String) rsltMap.get("BASE_PRC_FLG"));
                // START 2018/07/18 [QC#26929, ADD]
                if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B2, ZYPConstant.FLG_OFF_N);
                }
                // END   2018/07/18 [QC#26929, ADD]
                // Is Top Schedule(Base) Exist
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B3, (String) rsltMap.get("BASE_TOP_SCHEDULE_FLG"));

                // Is Price Effectivity(Meter) Exist
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B4, (String) rsltMap.get("USG_PRC_FLG"));
                // Is Top Schedule(Meter) Exist
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B5, (String) rsltMap.get("USG_TOP_SCHEDULE_FLG"));

                // START 2017/09/13 K.Kim [QC#19938, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B8, (String) rsltMap.get("BILL_WITH_EQUIP_FLG"));
                // END 2017/09/13 K.Kim [QC#19938, ADD]

                // START 2017/09/21 K.Kitachi [QC#21222, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_B9, (String) rsltMap.get("BASE_PRC_EFF_MULTI_FLG"));
                // END 2017/09/21 K.Kitachi [QC#21222, ADD]

                // START 2023/08/18 T.Kojima [QC#60846, ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDplyCtrlFlg_BC, (String) rsltMap.get("USG_PRC_EFF_MULTI_FLG"));
                // END 2023/08/18 T.Kojima [QC#60846, ADD]

                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).rnwEffFromDt_B, (String) rsltMap.get("RNW_EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrRnwTotCnt_B, (BigDecimal) rsltMap.get("CONTR_RNW_TOT_CNT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).contrCloDt_B, (String) rsltMap.get("CONTR_CLO_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BB, IMG_OPEN_ARROW);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BM, IMG_OPEN_ARROW);
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BC, IMG_CLOSE_ARROW);
                }
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).mtrLbDescTxt_BX, cMsg.B.no(i).mtrLbDescTxt_B);

                if (!DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                    if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsContrBllgMtrPk_B)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BD, IMG_OPEN_MACHINE_RED);
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BA, IMG_CLOSE_MACHINE_GREEN);
                    }
                }

                // START 2023/08/18 T.Kojima [QC#60846, ADD]
                if (ZYPConstant.FLG_ON_Y.equals((String) rsltMap.get("USG_PRC_EFF_MULTI_FLG"))) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BD, "");
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BA, "");
                }
                // END 2023/08/18 T.Kojima [QC#60846, ADD]

                firstLineAddr = (String) rsltMap.get("BASE_FIRST_LINE_ADDR");
                secondLineAddr = (String) rsltMap.get("BASE_SCD_LINE_ADDR");
                thirdLineAddr = (String) rsltMap.get("BASE_THIRD_LINE_ADDR");
                fourthLineAddr = (String) rsltMap.get("BASE_FRTH_LINE_ADDR");
                ctyAddr = (String) rsltMap.get("BASE_CTY_ADDR");
                stCd = (String) rsltMap.get("BASE_ST_CD");
                postCd = (String) rsltMap.get("BASE_POST_CD");
                addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToCustLocAddr_BB, addr);

                // Add Start 2018/01/10 QC#18552
                // Ship to Customer Address
                firstLineAddr = (String) rsltMap.get("SHIP_FIRST_LINE_ADDR");
                secondLineAddr = (String) rsltMap.get("SHIP_SCD_LINE_ADDR");
                thirdLineAddr = (String) rsltMap.get("SHIP_THIRD_LINE_ADDR");
                fourthLineAddr = (String) rsltMap.get("SHIP_FRTH_LINE_ADDR");
                ctyAddr = (String) rsltMap.get("SHIP_CTY_ADDR");
                stCd = (String) rsltMap.get("SHIP_ST_CD");
                postCd = (String) rsltMap.get("SHIP_POST_CD");
                addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).shipToCustLocAddr_B, addr);
                // Service Branch
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcBrCd_B, (String) rsltMap.get("FLD_SVC_BR_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcBrCdDescTxt_B, (String) rsltMap.get("SVC_BR_CD_DESC_TXT"));
                // Add End 2018/01/10 QC#18552

                // START 2019/01/17 M.Naito [QC#29083,ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).cumCopyCnt_B, (BigDecimal) rsltMap.get("CUM_COPY_CNT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).cumCopyFreqMthAot_B, (BigDecimal) rsltMap.get("CUM_COPY_FREQ_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).cumCopyStartDt_B, (String) rsltMap.get("CUM_COPY_START_DT"));
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).cumCopyEndDt_B, (String) rsltMap.get("CUM_COPY_END_DT"));
                // END 2019/01/17 M.Naito [QC#29083,ADD]

                // Set Default
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).baseBillToCustCd_B)) {
                    String defBillToCustCd = null;
                    // START 2017/06/12 N.Arai [QC#18567, MOD]
//                    if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
//                        defBillToCustCd = cMsg.leaseCmpyCd.getValue();
//                    } else if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
//                        defBillToCustCd = cMsg.altPayerCustCd.getValue();
//                    }
                    if (FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue())) {
                        defBillToCustCd = cMsg.leaseCmpyCd.getValue();
                    } else {
                        defBillToCustCd = cMsg.altPayerCustCd.getValue();
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBillToCustCd_B, defBillToCustCd);
                    // Add Start 2018/01/15 QC#18552
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToCustLocAddr_BB, getBillToCustAddr(glblCmpyCd, cMsg.B.no(i).baseBillToCustCd_B.getValue()));
                    // Add End 2018/01/15 QC#18552
                }
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).billToLocNm_BB)) {
                    String defBillToLocNm = null;
//                    if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
//                        defBillToLocNm = cMsg.dsAcctNm_L.getValue();
//                    } else if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
//                        defBillToLocNm = cMsg.billToLocNm.getValue();
//                    }
                    if (FLG_ON_Y.equals(cMsg.baseChrgToLeaseCmpyFlg.getValue())) {
                        defBillToLocNm = cMsg.dsAcctNm_L.getValue();
                    } else {
                        defBillToLocNm = cMsg.billToLocNm.getValue();
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToLocNm_BB, defBillToLocNm);
                }
                // del start 2016/09/23 CSA Defect#13686
                //if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).xxPsnNm_BB)) {
                //    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxPsnNm_BB, cMsg.xxPsnNm_CP);
                //    // START 2016/03/25 T.Kanasaka [QC#6040, ADD]
                //    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnPk_BB, cMsg.ctacPsnPk_CP);
                //    // END 2016/03/25 T.Kanasaka [QC#6040, ADD]
                //}
                // del end 2016/09/23 CSA Defect#13686
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).baseBllgCycleCd_B)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).baseBllgCycleCd_B, cMsg.baseBllgCycleCd);
                }
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).bllgMtrBillToCustCd_B)) {
                    String defBillToCustCd = null;
//                    if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
//                        defBillToCustCd = cMsg.leaseCmpyCd.getValue();
//                    } else if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
//                        defBillToCustCd = cMsg.altPayerCustCd.getValue();
//                    }
                    if (FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                        defBillToCustCd = cMsg.leaseCmpyCd.getValue();
                    } else {
                        defBillToCustCd = cMsg.altPayerCustCd.getValue();
                    }
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrBillToCustCd_B, defBillToCustCd);
                    // Add Start 2018/01/15 QC#18552
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToCustLocAddr_BM, getBillToCustAddr(glblCmpyCd, cMsg.B.no(i).bllgMtrBillToCustCd_B.getValue()));
                    // Add End 2018/01/15 QC#18552
                }
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).billToLocNm_BM)) {
                    String defBillToLocNm = null;
//                    if (ZYPCommonFunc.hasValue(cMsg.leaseCmpyCd)) {
//                        defBillToLocNm = cMsg.dsAcctNm_L.getValue();
//                    } else if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
//                        defBillToLocNm = cMsg.billToLocNm.getValue();
//                    }
                    if (FLG_ON_Y.equals(cMsg.usgChrgToLeaseCmpyFlg.getValue())) {
                        defBillToLocNm = cMsg.dsAcctNm_L.getValue();
                    } else {
                        defBillToLocNm = cMsg.billToLocNm.getValue();
                    }
                    // END 2017/06/12 N.Arai [QC#18567, MOD]
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).billToLocNm_BM, defBillToLocNm);
                }
                // del start 2016/09/23 CSA Defect#13686
                //if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).xxPsnNm_BM)) {
                //    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxPsnNm_BM, cMsg.xxPsnNm_CP);
                //    // START 2016/03/25 T.Kanasaka [QC#6040, ADD]
                //    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).ctacPsnPk_BM, cMsg.ctacPsnPk_CP);
                //    // END 2016/03/25 T.Kanasaka [QC#6040, ADD]
                //}
                // del end 2016/09/23 CSA Defect#13686
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).bllgMtrBllgCycleCd_B)) {
                    // mod start 2017/08/08 QC#18799
                    if (ZYPCommonFunc.hasValue(cMsg.mtrBllgCycleCd)) {
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrBllgCycleCd_B, cMsg.mtrBllgCycleCd);
                    } else {
                        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = query.getDsContrIntfcDefRule(glblCmpyCd, cMsg.dsContrClsCd.getValue(), cMsg.svcLineBizCd.getValue());
                        if (dsContrIntfcDefRuleTMsg != null) {
                            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bllgMtrBllgCycleCd_B, dsContrIntfcDefRuleTMsg.mtrBllgCycleCd);
                        }
                    }
                    // mod end 2017/08/08 QC#18799
                }
            }
            cMsg.B.setValidCount(rsltCnt);

            rslt = query.getContrPhysBllgMtrReln(glblCmpyCd, cMsg.dsContrPk.getValue());
            if (rslt != null && rslt.isCodeNormal()) {
                rsltList = (List<Map<String, Object>>) rslt.getResultObject();
// START 2017/02/10 [QC#17494, MOD]
                setContrPhysBllgMtrReln(cMsg.B, rsltList);
//                rsltCnt = rslt.getQueryResultCount();
//                for (int i = 0; i < rsltCnt; i++) {
//                    Map<String, Object> rsltMap = rsltList.get(i);
//                    for (int j = 0; j < cMsg.B.getValidCount(); j++) {
//                        if (!ZYPCommonFunc.hasValue(cMsg.B.no(j).dsContrBllgMtrPk_B) || !ZYPCommonFunc.hasValue(cMsg.B.no(j).bllgMtrLbCd_B)) {
//                            continue;
//                        }
//
//                        if (DS_CONTR_DTL_TP.FLEET.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(j).dsContrDtlTpCd_B.getValue())) {
//                            if (cMsg.B.no(j).bllgMtrLbCd_B.getValue().equals((String) rsltMap.get("BLLG_MTR_LB_CD"))) {
//                                if (ZYPCommonFunc.hasValue(cMsg.B.no(j).xxComnScrColValTxt_B)) {
//                                    if (cMsg.B.no(j).xxComnScrColValTxt_B.getValue().indexOf((String) rsltMap.get("MDL_MTR_LB_DESC_TXT")) == -1) {
//                                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxComnScrColValTxt_B, cMsg.B.no(j).xxComnScrColValTxt_B.getValue() + COMMA + (String) rsltMap.get("MDL_MTR_LB_DESC_TXT"));
//                                    }
//                                } else {
//                                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxComnScrColValTxt_B, (String) rsltMap.get("MDL_MTR_LB_DESC_TXT"));
//                                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).contrMtrMultRate_B, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
//                                }
//                            }
//                        } else {
//                            if (cMsg.B.no(j).dsContrBllgMtrPk_B.getValue().compareTo((BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK")) == 0 && cMsg.B.no(j).bllgMtrLbCd_B.getValue().equals((String) rsltMap.get("BLLG_MTR_LB_CD"))) {
//                                if (ZYPCommonFunc.hasValue(cMsg.B.no(j).xxComnScrColValTxt_B)) {
//                                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxComnScrColValTxt_B, cMsg.B.no(j).xxComnScrColValTxt_B.getValue() + COMMA + (String) rsltMap.get("MDL_MTR_LB_DESC_TXT"));
//                                } else {
//                                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).xxComnScrColValTxt_B, (String) rsltMap.get("MDL_MTR_LB_DESC_TXT"));
//                                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).contrMtrMultRate_B, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
//                                }
//                            }
//                        }
//                    }
//                }
// END 2017/02/10 [QC#17494, MOD]
            }

            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                NSAL0300CommonLogic.setTableBPulldownList(glblCmpyCd, cMsg.B.no(i));
            }
        }
    }

    // START 2017/02/10 [QC#17494, ADD]
    private static void setContrPhysBllgMtrReln(NSAL0300_BCMsgArray bCMsgArray, List<Map<String, Object>> rsltList) {
        for (int j = 0; j < bCMsgArray.getValidCount(); j++) {
            if (!ZYPCommonFunc.hasValue(bCMsgArray.no(j).dsContrBllgMtrPk_B) || !ZYPCommonFunc.hasValue(bCMsgArray.no(j).bllgMtrLbCd_B)) {
                continue;
            }

            BigDecimal mtrMultRate = null;
            // START 2017/02/14 [QC#17275-1, MOD]
            // BigDecimal prevMtrMultRate = null;
            // END   2017/02/14 [QC#17275-1, MOD]
            boolean multiMtrMultRate = false;
            List<String> mtrLbDescTxtList = new ArrayList<String>();
            List<BigDecimal> mtrMultRateList = new ArrayList<BigDecimal>();

            for (int i = 0; i < rsltList.size(); i++) {
                Map<String, Object> rsltMap = rsltList.get(i); 
                BigDecimal dsContrBllgMtrPk = (BigDecimal) rsltMap.get("DS_CONTR_BLLG_MTR_PK");
                String mdlMtrLbDescTxt = (String) rsltMap.get("MDL_MTR_LB_DESC_TXT");
                String bllgMtrLbTxt = (String) rsltMap.get("BLLG_MTR_LB_CD");
                // START 2017/02/14 [QC#17275-1, MOD]
                mtrMultRate = (BigDecimal)rsltMap.get("CONTR_MTR_MULT_RATE");
                // if (i == 0) {
                //     prevMtrMultRate = (BigDecimal)rsltMap.get("CONTR_MTR_MULT_RATE");
                //     mtrMultRate = prevMtrMultRate;
                // } else {
                //     prevMtrMultRate = mtrMultRate;
                //     mtrMultRate = (BigDecimal)rsltMap.get("CONTR_MTR_MULT_RATE");
                // }
                // END   2017/02/14 [QC#17275-1, MOD]

                if (DS_CONTR_DTL_TP.FLEET.equals(bCMsgArray.no(j).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsgArray.no(j).dsContrDtlTpCd_B.getValue())) {
                    if (!bCMsgArray.no(j).bllgMtrLbCd_B.getValue().equals(bllgMtrLbTxt)) {
                        continue;
                    }
                } else {
                    if (bCMsgArray.no(j).dsContrBllgMtrPk_B.getValue().compareTo(dsContrBllgMtrPk) != 0) {
                        continue;
                    }
                    if (!bCMsgArray.no(j).bllgMtrLbCd_B.getValue().equals(bllgMtrLbTxt)) {
                        continue;
                    }
                }

                if (DS_CONTR_DTL_TP.FLEET.equals(bCMsgArray.no(j).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsgArray.no(j).dsContrDtlTpCd_B.getValue())) {
                    if (mtrLbDescTxtList.contains(mdlMtrLbDescTxt)) {
                        continue;
                    }
                }

                // START 2017/02/14 [QC#17275-1, DEL]
                // if (!mtrMultRate.equals(prevMtrMultRate)) {
                //     multiMtrMultRate = true;
                // }
                // END   2017/02/14 [QC#17275-1, DEL]

                mtrLbDescTxtList.add(mdlMtrLbDescTxt);
                mtrMultRateList.add(mtrMultRate);
            }

            // START 2017/02/14 [QC#17275-1, ADD]
            if (mtrLbDescTxtList.isEmpty()) {
                continue;
            }

            BigDecimal prevMtrMultRate = null;
            mtrMultRate = null;
            for (BigDecimal rate : mtrMultRateList) {
                mtrMultRate = rate;
                if (prevMtrMultRate != null && rate.compareTo(prevMtrMultRate) != 0) {
                    multiMtrMultRate = true;
                    break;
                }
                prevMtrMultRate = rate;
            }
            // END   2017/02/14 [QC#17275-1, ADD]

            if (multiMtrMultRate) {
                bCMsgArray.no(j).contrMtrMultRate_B.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(bCMsgArray.no(j).contrMtrMultRate_B, mtrMultRate);
            }

            Iterator<String> itrMtrLbDescTxt = mtrLbDescTxtList.iterator();
            Iterator<BigDecimal> itrMtrMultRate = mtrMultRateList.iterator();
            StringBuilder mtrLbBuilder = new StringBuilder();
            while (itrMtrLbDescTxt.hasNext()) {
                if (mtrLbBuilder.length() > 0) {
                    mtrLbBuilder.append(COMMA);
                }
                mtrLbBuilder.append(itrMtrLbDescTxt.next());
                if (multiMtrMultRate) {
                    mtrLbBuilder.append(String.format(FMT_MTR_MULT_RATE, itrMtrMultRate.next()));
                }
            }
            if (mtrLbBuilder.length() > MAX_LENGTH_COUNTER_NAME) {
                mtrLbBuilder.delete(MAX_LENGTH_COUNTER_NAME, mtrLbBuilder.length());
            }
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(j).xxComnScrColValTxt_B, mtrLbBuilder.toString());
       }
    }
    // END   2017/02/10 [QC#17494, ADD]

    // START 2016/02/16 T.Kanasaka [QC2579, ADD]
    /**
     * getParentASMsg
     * @param aSMsgArray NSAL0300_ASMsgArray
     * @param prntDsContrDtlPk Parent DS_CONTR_DTL_PK
     * @return List<NSAL0300_ASMsg>
     */
    public static NSAL0300_ASMsg getParentASMsg(NSAL0300_ASMsgArray aSMsgArray, BigDecimal prntDsContrDtlPk) {
        if (!ZYPCommonFunc.hasValue(prntDsContrDtlPk)) {
            return null;
        }
        for (int i = 0; i < aSMsgArray.getValidCount(); i++) {
            if (prntDsContrDtlPk.compareTo(aSMsgArray.no(i).dsContrDtlPk_A.getValue()) == 0) {
                return aSMsgArray.no(i);
            }
        }
        return null;
    }
    // END 2016/02/16 T.Kanasaka [QC2579, ADD]

    // START 2016/02/16 T.Aoyagi [QC2947, ADD]
    /**
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static void setDefaultPmtTerm(String glblCmpyCd, NSAL0300CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd)) {
            NSAL0300Query query = NSAL0300Query.getInstance();
            S21SsmEZDResult rslt = query.getCustPmtTerm(glblCmpyCd, cMsg.altPayerCustCd.getValue());

            if (rslt != null && rslt.isCodeNormal()) {
                Map<String, String> rsltMap = (Map<String, String>) rslt.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscCd, rsltMap.get("PMT_TERM_CASH_DISC_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscDescTxt, rsltMap.get("PMT_TERM_CASH_DISC_DESC_TXT"));

            } else {
                rslt = query.getAcctPmtTerm(glblCmpyCd, cMsg.altPayerCustCd.getValue());
                if (rslt != null && rslt.isCodeNormal()) {
                    Map<String, String> rsltMap = (Map<String, String>) rslt.getResultObject();
                    ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscCd, rsltMap.get("PMT_TERM_CASH_DISC_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.pmtTermCashDiscDescTxt, rsltMap.get("PMT_TERM_CASH_DISC_DESC_TXT"));
                }
            }
        }
    }
    // END 2016/02/16 T.Aoyagi [QC2947, ADD]

    // START 2016/02/18 T.Aoyagi [QC3700, ADD]
    /**
     * @param cMsg NSAL0300CMsg
     */
    public static void setDefaultSummaryDetailMode(NSAL0300CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk) || DISPLAY_MODE_UPDATE.equals(cMsg.xxModeCd_FU.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_SD, DETAIL_MODE);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxModeCd_SD, SUMMARY_MODE);
        }
    }
    // END 2016/02/18 T.Aoyagi [QC3700, ADD]

    // START 2016/05/09 T.Kanasaka [QC#6798, ADD]
    /**
     * setRnwFlg
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static void setRnwFlg(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.contrAutoRnwTpCd)) {
            CONTR_AUTO_RNW_TPTMsg contrAutoRnwTpTMsg = (CONTR_AUTO_RNW_TPTMsg) ZYPCodeDataUtil.findByCode(CONTR_AUTO_RNW_TP.class, glblCmpyCd, cMsg.contrAutoRnwTpCd.getValue());
            if (contrAutoRnwTpTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.rnwBaseFlg, contrAutoRnwTpTMsg.rnwBaseFlg);
                ZYPEZDItemValueSetter.setValue(cMsg.rnwUsgFlg, contrAutoRnwTpTMsg.rnwUsgFlg);
            }
        }
    }

    /**
     * setUplftFlg
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static void setUplftFlg(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.contrUplftTpCd)) {
            CONTR_UPLFT_TPTMsg contrUplftTpTMsg = (CONTR_UPLFT_TPTMsg) ZYPCodeDataUtil.findByCode(CONTR_UPLFT_TP.class, glblCmpyCd, cMsg.contrUplftTpCd.getValue());
            if (contrUplftTpTMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.uplftBaseFlg, contrUplftTpTMsg.uplftBaseFlg);
                ZYPEZDItemValueSetter.setValue(cMsg.uplftUsgFlg, contrUplftTpTMsg.uplftUsgFlg);
            }
        }
    }
    // END 2016/05/09 T.Kanasaka [QC#6798, ADD]

    // START 2016/06/20 T.Kanasaka [QC#9019, ADD]
    /**
     * deleteDsContrDtl
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     */
    public static void deleteDsContrDtl(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300Query query = NSAL0300Query.getInstance();
        BigDecimal dsContrDtlPk = null;
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> contrXsCopyTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        List<DS_CONTR_ADDL_CHRGTMsg> dsContrAddlChrgTMsgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();
        List<DS_CONTR_BR_ALLOCTMsg> dsContrBrAllocTMsgList = new ArrayList<DS_CONTR_BR_ALLOCTMsg>();
        List<DS_CONTR_SEG_ALLOCTMsg> dsContrSegAllocTMsgList = new ArrayList<DS_CONTR_SEG_ALLOCTMsg>();
        List<DS_CONTR_PRC_ALLOCTMsg> dsContrPrcAllocTMsgList = new ArrayList<DS_CONTR_PRC_ALLOCTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        List<DS_CONTR_RNW_ESCLTMsg> dsContrRnwEsclTMsgList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();
        List<DS_SUB_CONTRTMsg> dsSubContrTMsgList = new ArrayList<DS_SUB_CONTRTMsg>();
        // START 2018/12/13 [QC#29079, ADD]
        List<DS_SUB_CONTR_MTRTMsg> dsSubContrMtrTMsgList = new ArrayList<DS_SUB_CONTR_MTRTMsg>();
        // END 2018/12/13 [QC#29079, ADD]
        List<SVC_TERM_CONDTMsg> svcTermCondTMsgList = new ArrayList<SVC_TERM_CONDTMsg>();

        for (int i = 0; i < sMsg.X.getValidCount(); i++) {
            dsContrDtlPk = sMsg.X.no(i).dsContrDtlPk_X.getValue();

            // DS_CONTR_DTL
            DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtl(glblCmpyCd, dsContrDtlPk);
            if (dsContrDtlTMsg != null) {
                dsContrDtlTMsgList.add(dsContrDtlTMsg);
            }

            // DS_CONTR_BLLG_MTR
            DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrList(glblCmpyCd, dsContrDtlPk);
            if (dsContrBllgMtrTMsgArray != null) {
                for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                    dsContrBllgMtrTMsgList.add(dsContrBllgMtrTMsgArray.no(j));

                    // CONTR_XS_COPY
                    CONTR_XS_COPYTMsgArray contrXsCopyTMsgArray = query.getContrXsCopy(glblCmpyCd, dsContrBllgMtrTMsgArray.no(j).dsContrBllgMtrPk.getValue());
                    if (contrXsCopyTMsgArray != null) {
                        for (int k = 0; k < contrXsCopyTMsgArray.getValidCount(); k++) {
                            contrXsCopyTMsgList.add(contrXsCopyTMsgArray.no(k));
                        }
                    }
                }
            }

            // CONTR_PHYS_BLLG_MTR_RELN
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnTMsgArray = query.getContrPhysBllgMtrRelnByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (contrPhysBllgMtrRelnTMsgArray != null) {
                for (int j = 0; j < contrPhysBllgMtrRelnTMsgArray.getValidCount(); j++) {
                    contrPhysBllgMtrRelnTMsgList.add(contrPhysBllgMtrRelnTMsgArray.no(j));
                }
            }

            // DS_CONTR_ADDL_CHRG
            DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgArray = query.getDsContrAddlChrgByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsContrAddlChrgTMsgArray != null) {
                for (int j = 0; j < dsContrAddlChrgTMsgArray.getValidCount(); j++) {
                    dsContrAddlChrgTMsgList.add(dsContrAddlChrgTMsgArray.no(j));
                }
            }

            // DS_CONTR_BR_ALLOC
            DS_CONTR_BR_ALLOCTMsgArray dsContrBrAllocTMsgArray = query.getDsContrBrAllocByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsContrBrAllocTMsgArray != null) {
                for (int j = 0; j < dsContrBrAllocTMsgArray.getValidCount(); j++) {
                    dsContrBrAllocTMsgList.add(dsContrBrAllocTMsgArray.no(j));
                }
            }

            // DS_CONTR_SEG_ALLOC
            DS_CONTR_SEG_ALLOCTMsgArray dsContrSegAllocTMsgArray = query.getDsContrSegAllocByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsContrSegAllocTMsgArray != null) {
                for (int j = 0; j < dsContrSegAllocTMsgArray.getValidCount(); j++) {
                    dsContrSegAllocTMsgList.add(dsContrSegAllocTMsgArray.no(j));
                }
            }

            // DS_CONTR_PRC_ALLOC
            DS_CONTR_PRC_ALLOCTMsgArray dsContrPrcAllocTMsgArray = query.getDsContrPrcAllocByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsContrPrcAllocTMsgArray != null) {
                for (int j = 0; j < dsContrPrcAllocTMsgArray.getValidCount(); j++) {
                    dsContrPrcAllocTMsgList.add(dsContrPrcAllocTMsgArray.no(j));
                }
            }

            // DS_CONTR_CR_CARD_PO
            DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = query.getDsContrCrCardPoByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsContrCrCardPoTMsgArray != null) {
                for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                    dsContrCrCardPoTMsgList.add(dsContrCrCardPoTMsgArray.no(j));
                }
            }

            // DS_CONTR_RNW_ESCL
            DS_CONTR_RNW_ESCLTMsgArray dsContrRnwEsclTMsgArray = query.getDsContrRnwEsclByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsContrRnwEsclTMsgArray != null) {
                for (int j = 0; j < dsContrRnwEsclTMsgArray.getValidCount(); j++) {
                    dsContrRnwEsclTMsgList.add(dsContrRnwEsclTMsgArray.no(j));
                }
            }

            // DS_SUB_CONTR
            DS_SUB_CONTRTMsgArray dsSubContrTMsgArray = query.getDsSubContrByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (dsSubContrTMsgArray != null) {
                for (int j = 0; j < dsSubContrTMsgArray.getValidCount(); j++) {
                    dsSubContrTMsgList.add(dsSubContrTMsgArray.no(j));

                    // START 2018/12/13 [QC#29079, ADD]
                    // DS_SUB_CONTR_MTR
                    DS_SUB_CONTR_MTRTMsgArray dsSubContrMtrTMsgArray = query.getDsSubContrMtrByDsSubContrPk(glblCmpyCd, dsSubContrTMsgArray.no(j).dsSubContrPk.getValue());
                    if (dsSubContrMtrTMsgArray != null) {
                        for (int k = 0; k < dsSubContrMtrTMsgArray.getValidCount(); k++) {
                            dsSubContrMtrTMsgList.add(dsSubContrMtrTMsgArray.no(k));
                        }
                    }
                    // END 2018/12/13 [QC#29079, ADD]
                }
            }

            // SVC_TERM_COND
            SVC_TERM_CONDTMsgArray svcTermCondTMsgArray = query.getSvcTermCondByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
            if (svcTermCondTMsgArray != null) {
                for (int j = 0; j < svcTermCondTMsgArray.getValidCount(); j++) {
                    svcTermCondTMsgList.add(svcTermCondTMsgArray.no(j));
                }
            }
        }

        // DS_CONTR_DTL
        if (dsContrDtlTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrDtlTMsgList.toArray(new DS_CONTR_DTLTMsg[dsContrDtlTMsgList.size()]));
            if (delCnt != dsContrDtlTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Detail" });
                return;
            }
        }

        // DS_CONTR_BLLG_MTR
        if (dsContrBllgMtrTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrBllgMtrTMsgList.toArray(new DS_CONTR_BLLG_MTRTMsg[dsContrBllgMtrTMsgList.size()]));
            if (delCnt != dsContrBllgMtrTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Billing Meter" });
                return;
            }
        }

        // CONTR_XS_COPY
        if (contrXsCopyTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(contrXsCopyTMsgList.toArray(new CONTR_XS_COPYTMsg[contrXsCopyTMsgList.size()]));
            if (delCnt != contrXsCopyTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Contract Excess Copy" });
                return;
            }
        }

        // CONTR_PHYS_BLLG_MTR_RELN
        if (contrPhysBllgMtrRelnTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(contrPhysBllgMtrRelnTMsgList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[contrPhysBllgMtrRelnTMsgList.size()]));
            if (delCnt != contrPhysBllgMtrRelnTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Contract Physical Billing Meter Relation" });
                return;
            }
        }

        // DS_CONTR_ADDL_CHRG
        if (dsContrAddlChrgTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrAddlChrgTMsgList.toArray(new DS_CONTR_ADDL_CHRGTMsg[dsContrAddlChrgTMsgList.size()]));
            if (delCnt != dsContrAddlChrgTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Additional Charge" });
                return;
            }
        }

        // DS_CONTR_BR_ALLOC
        if (dsContrBrAllocTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrBrAllocTMsgList.toArray(new DS_CONTR_BR_ALLOCTMsg[dsContrBrAllocTMsgList.size()]));
            if (delCnt != dsContrBrAllocTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Branch Allocation" });
                return;
            }
        }

        // DS_CONTR_SEG_ALLOC
        if (dsContrSegAllocTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrSegAllocTMsgList.toArray(new DS_CONTR_SEG_ALLOCTMsg[dsContrSegAllocTMsgList.size()]));
            if (delCnt != dsContrSegAllocTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Segment Allocation" });
                return;
            }
        }

        // DS_CONTR_PRC_ALLOC
        if (dsContrPrcAllocTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrPrcAllocTMsgList.toArray(new DS_CONTR_PRC_ALLOCTMsg[dsContrPrcAllocTMsgList.size()]));
            if (delCnt != dsContrPrcAllocTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Price Allocation" });
                return;
            }
        }

        // DS_CONTR_CR_CARD_PO
        if (dsContrCrCardPoTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrCrCardPoTMsgList.toArray(new DS_CONTR_CR_CARD_POTMsg[dsContrCrCardPoTMsgList.size()]));
            if (delCnt != dsContrCrCardPoTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Credit Card PO" });
                return;
            }
        }

        // DS_CONTR_RNW_ESCL
        if (dsContrRnwEsclTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrRnwEsclTMsgList.toArray(new DS_CONTR_RNW_ESCLTMsg[dsContrRnwEsclTMsgList.size()]));
            if (delCnt != dsContrRnwEsclTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Renewal Escalation" });
                return;
            }
        }

        // DS_SUB_CONTR
        if (dsSubContrTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsSubContrTMsgList.toArray(new DS_SUB_CONTRTMsg[dsSubContrTMsgList.size()]));
            if (delCnt != dsSubContrTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Subordinate Contract" });
                return;
            }
        }

        // START 2018/12/13 [QC#29079, ADD]
        if (dsSubContrMtrTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsSubContrMtrTMsgList.toArray(new DS_SUB_CONTR_MTRTMsg[dsSubContrMtrTMsgList.size()]));
            if (delCnt != dsSubContrMtrTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Sub Contract Meter" });
                return;
            }
        }
        // END 2018/12/13 [QC#29079, ADD]

        // SVC_TERM_COND
        if (svcTermCondTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(svcTermCondTMsgList.toArray(new SVC_TERM_CONDTMsg[svcTermCondTMsgList.size()]));
            if (delCnt != svcTermCondTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Service Term Condition" });
                return;
            }
        }

        // START 2016/07/06 T.Kanasaka [QC#9019, ADD]
        // No Fleet Machine
        // START 2016/07/11 T.Kanasaka [QC#9019, MOD]
        if ((DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) && sMsg.X.getValidCount() > 0 && cMsg.A.getValidCount() == 0) {
            deleteDsContrDtlForFleetAggregateLine(glblCmpyCd, cMsg);
        }
        // END 2016/07/11 T.Kanasaka [QC#9019, MOD]
        // END 2016/07/06 T.Kanasaka [QC#9019, ADD]
    }

    // START 2016/07/06 T.Kanasaka [QC#9019, ADD]
    private static void deleteDsContrDtlForFleetAggregateLine(String glblCmpyCd, NSAL0300CMsg cMsg) {
        NSAL0300Query query = NSAL0300Query.getInstance();
        BigDecimal dsContrDtlPk = cMsg.B.no(0).dsContrDtlPk_B.getValue();
        List<DS_CONTR_BLLG_MTRTMsg> dsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> contrXsCopyTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> contrPhysBllgMtrRelnTMsgList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();

        // DS_CONTR_BLLG_MTR
        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrList(glblCmpyCd, dsContrDtlPk);
        if (dsContrBllgMtrTMsgArray != null) {
            for (int j = 0; j < dsContrBllgMtrTMsgArray.getValidCount(); j++) {
                dsContrBllgMtrTMsgList.add(dsContrBllgMtrTMsgArray.no(j));

                // CONTR_XS_COPY
                CONTR_XS_COPYTMsgArray contrXsCopyTMsgArray = query.getContrXsCopy(glblCmpyCd, dsContrBllgMtrTMsgArray.no(j).dsContrBllgMtrPk.getValue());
                if (contrXsCopyTMsgArray != null) {
                    for (int k = 0; k < contrXsCopyTMsgArray.getValidCount(); k++) {
                        contrXsCopyTMsgList.add(contrXsCopyTMsgArray.no(k));
                    }
                }
            }
        }

        // CONTR_PHYS_BLLG_MTR_RELN
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnTMsgArray = query.getContrPhysBllgMtrRelnByDsContrDtlPk(glblCmpyCd, dsContrDtlPk);
        if (contrPhysBllgMtrRelnTMsgArray != null) {
            for (int j = 0; j < contrPhysBllgMtrRelnTMsgArray.getValidCount(); j++) {
                contrPhysBllgMtrRelnTMsgList.add(contrPhysBllgMtrRelnTMsgArray.no(j));
            }
        }

        // DS_CONTR_CR_CARD_PO
        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = query.getDsContrCrCardPoForMtr(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (dsContrCrCardPoTMsgArray != null) {
            for (int j = 0; j < dsContrCrCardPoTMsgArray.getValidCount(); j++) {
                dsContrCrCardPoTMsgList.add(dsContrCrCardPoTMsgArray.no(j));
            }
        }

        // DS_CONTR_BLLG_MTR
        if (dsContrBllgMtrTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrBllgMtrTMsgList.toArray(new DS_CONTR_BLLG_MTRTMsg[dsContrBllgMtrTMsgList.size()]));
            if (delCnt != dsContrBllgMtrTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Billing Meter" });
                return;
            }
        }

        // CONTR_XS_COPY
        if (contrXsCopyTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(contrXsCopyTMsgList.toArray(new CONTR_XS_COPYTMsg[contrXsCopyTMsgList.size()]));
            if (delCnt != contrXsCopyTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Contract Excess Copy" });
                return;
            }
        }

        // CONTR_PHYS_BLLG_MTR_RELN
        if (contrPhysBllgMtrRelnTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(contrPhysBllgMtrRelnTMsgList.toArray(new CONTR_PHYS_BLLG_MTR_RELNTMsg[contrPhysBllgMtrRelnTMsgList.size()]));
            if (delCnt != contrPhysBllgMtrRelnTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"Contract Physical Billing Meter Relation" });
                return;
            }
        }

        // DS_CONTR_CR_CARD_PO
        if (dsContrCrCardPoTMsgList.size() > 0) {
            int delCnt = S21FastTBLAccessor.removeLogical(dsContrCrCardPoTMsgList.toArray(new DS_CONTR_CR_CARD_POTMsg[dsContrCrCardPoTMsgList.size()]));
            if (delCnt != dsContrCrCardPoTMsgList.size()) {
                cMsg.setMessageInfo(NSAM0033E, new String[] {"DS Contract Credit Card PO" });
                return;
            }
        }
    }
    // END 2016/07/06 T.Kanasaka [QC#9019, ADD]

    /**
     * callContractBillingScheduleAPIforDelete
     * @param glblCmpyCd glblCmpyCd
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     * @return result (true:OK, false:NG)
     */
    public static boolean callContractBillingScheduleAPIforDelete(String glblCmpyCd, NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        if (DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue())) {
            return true;
        }

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);

        // START 2016/07/06 T.Kanasaka [QC#9019, ADD]
        // No Fleet Machine
        // START 2016/07/11 T.Kanasaka [QC#9019, MOD]
        if ((DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue()) || DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) && sMsg.X.getValidCount() > 0 && cMsg.A.getValidCount() == 0) {
            // Usage
            // mod start 2017/01/04 CSA QC#16399
            NSZC047010PMsg pMsg = new NSZC047010PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "10");
            // mod end 2017/01/04 CSA QC#16399
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, cMsg.B.no(0).dsContrDtlPk_B.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);

            NSZC047001 api = new NSZC047001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }

            if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd.getValue())) {
                return true;
            }
        }
        // END 2016/07/11 T.Kanasaka [QC#9019, MOD]
        // END 2016/07/06 T.Kanasaka [QC#9019, ADD]

        for (int i = 0; i < sMsg.X.getValidCount(); i++) {
            // Base
            // mod start 2017/01/04 CSA QC#16399
            NSZC047010PMsg pMsg = new NSZC047010PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "10");
            // mod end 2017/01/04 CSA QC#16399
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, sMsg.X.no(i).dsContrDtlPk_X.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_OFF_N);

            NSZC047001 api = new NSZC047001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }

            // Usage
            // mod start 2017/01/04 CSA QC#16399
            pMsg = new NSZC047010PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, "10");
            // mod start 2017/01/04 CSA QC#16399
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
            ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, sMsg.X.no(i).dsContrDtlPk_X.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.delFlg, ZYPConstant.FLG_ON_Y);

            api = new NSZC047001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }
        }

        return true;
    }
    // END 2016/06/20 T.Kanasaka [QC#9019, ADD]

    private static void createFilterPullDownList(NSAL0300CMsg cMsg) {
        for (int i = 0; i < FILTER_ITEM.length; i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_1C.no(i), FILTER_ITEM[i][0]);
            ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_1D.no(i), FILTER_ITEM[i][1]);
        }

        for (int i = 0; i < FILTER_CONDITION.length; i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_2C.no(i), FILTER_CONDITION[i][0]);
            ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_2D.no(i), FILTER_CONDITION[i][1]);
        }
    }

    /**
     * hasAccessory
     * @param cMsg NSAL0300CMsg
     * @param dsContrDtlPk BigDecimal
     * @return result
     */
    public static boolean hasAccessory(NSAL0300CMsg cMsg, BigDecimal dsContrDtlPk) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).prntDsContrDtlPk_B) && dsContrDtlPk.compareTo(cMsg.B.no(i).prntDsContrDtlPk_B.getValue()) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Save display controll
     * @param cMsg NSAL0150CMsg
     * @param sMsg NSAL0150SMsg
     * @return NSAL0300DisplayControllBean
     */
    public static NSAL0300DisplayControllBean saveDisplay(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg) {
        NSAL0300DisplayControllBean displayBean = new NSAL0300DisplayControllBean();

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            return displayBean;
        }

        displayBean.setPageFromNum(cMsg.xxPageShowFromNum_A.getValue());

        if (IMG_OPEN_ARROW.equals(cMsg.xxFilePathTxt_EC.getValue())) {
            displayBean.setEndCustomerOpenFlg(true);
        }

        if (IMG_OPEN_ARROW.equals(cMsg.xxFilePathTxt_FA.getValue())) {
            displayBean.setFleetAggregateOpenFlg(true);
        }

        if (IMG_OPEN_ARROW.equals(cMsg.xxFilePathTxt_M.getValue())) {
            displayBean.setMachineListOpenFlg(true);
        }

        displayBean.setFilterItem(cMsg.xxCondCd_1V.getValue());
        displayBean.setFilterCondition(cMsg.xxCondCd_2V.getValue());
        displayBean.setFilterValue(cMsg.condSqlTxt.getValue());

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxDplyCtrlFlg_A.getValue())) {
                displayBean.addFilteredDsContrDtlPkList(sMsg.A.no(i).dsContrDtlPk_A.getValue());
            }

            if (IMG_OPEN_MACHINE_RED.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue()) || IMG_OPEN_MACHINE_GREEN.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                displayBean.addOpenedDsContrDtlPkList(sMsg.A.no(i).dsContrDtlPk_A.getValue());
            }
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (IMG_OPEN_MACHINE_RED.equals(cMsg.A.no(i).xxFilePathTxt_A.getValue()) || IMG_OPEN_MACHINE_GREEN.equals(cMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                displayBean.addOpenedDsContrDtlPkList(cMsg.A.no(i).dsContrDtlPk_A.getValue());
            }
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (IMG_OPEN_ARROW.equals(cMsg.B.no(i).xxFilePathTxt_BB.getValue())) {
                displayBean.addOpenedBaseDsContrDtlPkList(cMsg.B.no(i).dsContrDtlPk_B.getValue());
            }

            if (IMG_OPEN_ARROW.equals(cMsg.B.no(i).xxFilePathTxt_BM.getValue())) {
                displayBean.addOpenedOverageDsContrDtlPkList(cMsg.B.no(i).dsContrDtlPk_B.getValue());
            }

            if (IMG_OPEN_ARROW.equals(cMsg.B.no(i).xxFilePathTxt_BC.getValue())) {
                displayBean.addOpenedDsContrBllgMtrPkList(cMsg.B.no(i).dsContrBllgMtrPk_B.getValue());
            }
        }

        return displayBean;
    }

    /**
     * Resotre display controll
     * @param cMsg NSAL0150CMsg
     * @param sMsg NSAL0150SMsg
     * @param displayBean NSAL0300DisplayControllBean
     */
    public static void restoreDisplay(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, NSAL0300DisplayControllBean displayBean) {

        if (displayBean.getEndCustomerOpenFlg()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_EC, IMG_OPEN_ARROW);
            // START 2016/11/15 T.Kanasaka [QC#15526, ADD]
            cMsg.dsAcctNm_EC.clear();
            cMsg.mdseDescShortTxt_EC.clear();
            // END 2016/11/15 T.Kanasaka [QC#15526, ADD]
        }

        if (displayBean.getFleetAggregateOpenFlg()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_FA, IMG_OPEN_ARROW);
        }

        if (displayBean.getMachineListOpenFlg()) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxFilePathTxt_M, IMG_OPEN_ARROW);
        }

        if (ZYPCommonFunc.hasValue(displayBean.getFilterItem())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_1V, displayBean.getFilterItem());
        }
        if (ZYPCommonFunc.hasValue(displayBean.getFilterCondition())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxCondCd_2V, displayBean.getFilterCondition());
        }
        if (ZYPCommonFunc.hasValue(displayBean.getFilterValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.condSqlTxt, displayBean.getFilterValue());
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (displayBean.getFilteredDsContrDtlPkList().contains(sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
            }

            if (displayBean.getOpenedDsContrDtlPkList().contains(sMsg.A.no(i).dsContrDtlPk_A.getValue())) {
                if (IMG_CLOSE_MACHINE_RED.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_RED);
                } else if (IMG_CLOSE_MACHINE_GREEN.equals(sMsg.A.no(i).xxFilePathTxt_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFilePathTxt_A, IMG_OPEN_MACHINE_GREEN);
                }
            }
        }

        restoreDisplayForB(cMsg, displayBean);
    }

    /**
     * Resotre display controll for B
     * @param cMsg NSAL0150CMsg
     * @param displayBean NSAL0300DisplayControllBean
     */
    public static void restoreDisplayForB(NSAL0300CMsg cMsg, NSAL0300DisplayControllBean displayBean) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (displayBean.getOpenedBaseDsContrDtlPkList().contains(cMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BB, IMG_OPEN_ARROW);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BB, IMG_CLOSE_ARROW);
            }

            if (displayBean.getOpenedOverageDsContrDtlPkList().contains(cMsg.B.no(i).dsContrDtlPk_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BM, IMG_OPEN_ARROW);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BM, IMG_CLOSE_ARROW);
            }

            if (displayBean.getOpenedDsContrBllgMtrPkList().contains(cMsg.B.no(i).dsContrBllgMtrPk_B.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BC, IMG_OPEN_ARROW);
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BC, IMG_CLOSE_ARROW);
            }
        }
    }

    /**
     * init display controll for B
     * @param cMsg NSAL0150CMsg
     */
    public static void initDisplay(NSAL0300CMsg cMsg) {
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BB, IMG_OPEN_ARROW);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BM, IMG_OPEN_ARROW);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxFilePathTxt_BC, IMG_CLOSE_ARROW);
        }
    }

    // add start 2016/09/06 CSA Defect#11243
    /**
     * callCreditCardValidationApiForSave
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static void callCreditCardValidationApiForSave(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            return;
        }

        // get CrCardTrx
        NSAL0300Query query = NSAL0300Query.getInstance();
        List<BigDecimal> crCardTrxPkList = query.getCrCardTrxPk(glblCmpyCd, cMsg.dsContrPk.getValue());

        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
        if (crCardTrxPkList == null || crCardTrxPkList.size() == 0) {
            // insert CrCardTrx
            NWZC203001PMsg pMsg = new NWZC203001PMsg();
            setValue(pMsg.glblCmpyCd, glblCmpyCd);
            setValue(pMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(pMsg.slsDt, slsDt);
            setValue(pMsg.crCardCustRefNum, cMsg.crCardCustRefNum);
            setValue(pMsg.sellToCustCd, cMsg.sellToCustCd);
            setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.CONTRACT_HEADER);
            setValue(pMsg.firstTrxInfoNum, cMsg.dsContrPk);
            setValue(pMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);

            NWZC203001 api = new NWZC203001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
            }
        } else {
            // update CrCardTrx
            for (BigDecimal crCardTrxPk : crCardTrxPkList) {
                CR_CARD_TRXTMsg crCardTrxTMsg = NSAL0300Query.getInstance().getCrCardTrx(glblCmpyCd, crCardTrxPk);
                if (crCardTrxTMsg == null) {
                    continue;
                }
                NWZC203001PMsg pMsg = new NWZC203001PMsg();
                EZDMsg.copy(crCardTrxTMsg, null, pMsg, null);
                setValue(pMsg.glblCmpyCd, glblCmpyCd);
                setValue(pMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
                setValue(pMsg.slsDt, slsDt);
                setValue(pMsg.crCardCustRefNum, cMsg.crCardCustRefNum);
                setValue(pMsg.sellToCustCd, cMsg.sellToCustCd);
                setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.CONTRACT_HEADER);
                setValue(pMsg.firstTrxInfoNum, cMsg.dsContrPk);
                setValue(pMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);
                setValue(pMsg.xxPmtcProcStsCd, crCardTrxTMsg.crCardTrxProcStsCd);
                setValue(pMsg.xxPmtcApvlStsNum, crCardTrxTMsg.crCardTrxApvlStsCd);
                setValue(pMsg.xxPmtcTrxRefIdxCd, crCardTrxTMsg.crCardRefIdxNum);

                NWZC203001 api = new NWZC203001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                if (pMsg.xxMsgIdList.getValidCount() > 0) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    cMsg.setMessageInfo(msgId, msgPrms);
                }
            }
        }
    }

    /**
     * callCreditCardValidationApiForVoid
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static void callCreditCardValidationApiForVoid(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk)) {
            return;
        }

        // get CrCardTrx
        NSAL0300Query query = NSAL0300Query.getInstance();
        List<BigDecimal> crCardTrxPkList = query.getCrCardTrxPk(glblCmpyCd, cMsg.dsContrPk.getValue());
        if (crCardTrxPkList == null || crCardTrxPkList.size() == 0) {
            return;
        }

        // cancel CrCardTrx
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);
        for (BigDecimal crCardTrxPk : crCardTrxPkList) {
            CR_CARD_TRXTMsg crCardTrxTMsg = query.getCrCardTrx(glblCmpyCd, crCardTrxPk);
            if (crCardTrxTMsg == null) {
                continue;
            }
            NWZC203001PMsg pMsg = new NWZC203001PMsg();
            EZDMsg.copy(crCardTrxTMsg, null, pMsg, null);
            setValue(pMsg.glblCmpyCd, glblCmpyCd);
            setValue(pMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            setValue(pMsg.slsDt, slsDt);
            setValue(pMsg.sellToCustCd, crCardTrxTMsg.billToCustAcctCd);
            setValue(pMsg.crCardTrxTpCd, CR_CARD_TRX_TP.CONTRACT_HEADER);
            setValue(pMsg.firstTrxInfoNum, cMsg.dsContrPk);
            setValue(pMsg.crCardCancDt, slsDt);
            setValue(pMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.VOID_COMPLETED);
            setValue(pMsg.xxPmtcProcStsCd, crCardTrxTMsg.crCardTrxProcStsCd);
            setValue(pMsg.xxPmtcApvlStsNum, crCardTrxTMsg.crCardTrxApvlStsCd);
            setValue(pMsg.xxPmtcTrxRefIdxCd, crCardTrxTMsg.crCardRefIdxNum);

            NWZC203001 api = new NWZC203001();
            api.execute(pMsg, ONBATCH_TYPE.ONLINE);
            if (pMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
            }
        }
    }
    // add end 2016/09/06 CSA Defect#11243

    // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
    private static String getDefShipToCustCd(String glblCmpyCd, String billToCustCd, NSAL0300CMsg cMsg) {
        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, cMsg.dsAcctNum);
        ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, billToCustCd);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    return null;
                }
            }
        }
        if (pMsg.DefaultBillShipList.getValidCount() > 0) {
            return pMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        }
        return null;
    }
    // END 2016/09/23 T.Kanasaka [QC#9905, ADD]

 // START 2017/02/02 N.Arai [QC#17197, MOD]
    /**
     * createMachineDetailsCsvFile
     * @param cMsg NSAL0300CMsg
     * @param sMsg NSAL0300SMsg
     * @param glblCmpyCd 
     */
    public static void createMachineDetailsCsvFile(NSAL0300CMsg cMsg, NSAL0300SMsg sMsg, String glblCmpyCd) {

        NSAL0300F01FMsg fMsg = new NSAL0300F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
        csvOutFile.writeHeader(new String[] {"Contract#", "Line#", "Machine Master", "Serial#", "Configuration#", "Status", "Install Location",
                "Service Program", "Start Date", "End Date", "Base Frequency", "Base Charge", "Read Method", "Renewal Date", "Times Renewed", "Date Terminated" });

        if (!hasValue(cMsg.dsContrPk) || !hasValue(cMsg.dsContrCatgCd)) {
            csvOutFile.close();
            cMsg.setMessageInfo(ZZZM9001E);
            return;
        }

        NSAL0300Query query = NSAL0300Query.getInstance();
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {

            rs = query.getDsContrDtlInfo4Download(stmtSelect ,glblCmpyCd, cMsg.dsContrPk.getValue(), cMsg.dsContrCatgCd.getValue(), LIMIT_DOWNLOAD + 1);

            int sqNum = 0;
            int sqSubNum = 0;
            BigDecimal dsContrDtlPk = null;

            int rsCnt = 0;
            while (rs.next()) {
                if (rsCnt >= LIMIT_DOWNLOAD) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                ZYPEZDItemValueSetter.setValue(fMsg.dsContrNum, cMsg.dsContrNum);
                ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsContrCtrlStsNm, rs.getString("DS_CONTR_CTRL_STS_NM"));

                String firstLineAddr = rs.getString("FIRST_LINE_ADDR");
                String secondLineAddr = rs.getString("SCD_LINE_ADDR");
                String thirdLineAddr = rs.getString("THIRD_LINE_ADDR");
                String fourthLineAddr = rs.getString("FRTH_LINE_ADDR");
                String ctyAddr = rs.getString("CTY_ADDR");
                String stCd = rs.getString("ST_CD");
                String postCd = rs.getString("POST_CD");
                String addr = NSAL0300CommonLogic.formatAddress(firstLineAddr, secondLineAddr, thirdLineAddr, fourthLineAddr, ctyAddr, stCd, postCd);

                ZYPEZDItemValueSetter.setValue(fMsg.billToCustLocAddr, addr);
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_SD, convertDateEzd8ToDisp(rs.getString("CONTR_EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_ED, convertDateEzd8ToDisp(rs.getString("CONTR_EFF_THRU_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.bllgCycleDescTxt, rs.getString("BLLG_CYCLE_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.basePrcDealAmt, rs.getBigDecimal("BASE_PRC_DEAL_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.mtrReadMethDescTxt, rs.getString("MTR_READ_METH_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_RD, convertDateEzd8ToDisp(rs.getString("RNW_EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.contrRnwTotCnt, rs.getBigDecimal("CONTR_RNW_TOT_CNT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CD, convertDateEzd8ToDisp(rs.getString("CONTR_CLO_DT")));

                String asryFlg = rs.getString("ASRY_FLG");
                if (ZYPConstant.FLG_ON_Y.equals(asryFlg)) {
                    sqNum++;
                    sqSubNum = 0;
                    dsContrDtlPk = rs.getBigDecimal("DS_CONTR_DTL_PK");
                } else if (DS_CONTR_DTL_TP.ACCESSORIES.equals(rs.getString("DS_CONTR_DTL_TP_CD")) && hasValue(dsContrDtlPk) && hasValue(rs.getBigDecimal("PRNT_DS_CONTR_DTL_PK")) && dsContrDtlPk.compareTo(rs.getBigDecimal("PRNT_DS_CONTR_DTL_PK")) == 0) {
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
                ZYPEZDItemValueSetter.setValue(fMsg.sqId, sqId);

                csvOutFile.write();
                rsCnt++;
            }
            if (rsCnt == 0) {
                cMsg.setMessageInfo(ZZZM9001E);
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            csvOutFile.close();
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }
// END 2017/02/02 N.Arai [QC#17197, MOD]

 // START 2017/02/02 N.Arai [QC#17197, MOD]
    /**
     * convertDateEzd8ToDisp
     * @param date String
     * @return String 
     */
    private static String convertDateEzd8ToDisp(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
 // END 2017/02/02 N.Arai [QC#17197, MOD]


    // START 2017/06/22 [QC#17496, ADD]
    /**
     * setDefBillToCust
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static void setDefBillToCust(String glblCmpyCd, NSAL0300CMsg cMsg) {
        NMZC610001PMsg pMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        ZYPEZDItemValueSetter.setValue(pMsg.dsAcctNum_I1, cMsg.dsAcctNum);
        new NMZC610001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    return;
                }
            }
        }

        if (pMsg.DefaultBillShipList.getValidCount() > 0) {
            setValue(cMsg.altPayerCustCd, pMsg.DefaultBillShipList.no(0).billToCustCd);
            S21SsmEZDResult rslt = NSAL0300Query.getInstance().findBillToCust(glblCmpyCd, cMsg.altPayerCustCd.getValue());
            if (rslt.getQueryResultCount() == 1) {
                Map<String, String> rsltMap = ((List<Map<String, String>>) rslt.getResultObject()).get(0);
                String locNm = (String) rsltMap.get("LOC_NM");
                setValue(cMsg.billToLocNm, locNm);
            }
        }
    }

    /**
     * setDefSellToCust
     * @param cMsg NSAL0300CMsg
     * @param rsltMap Map<String, String> rsltMap
     */
    public static void setDefSellToCust(NSAL0300CMsg cMsg, Map<String, String> rsltMap) {

        String dsAcctNum = (String) rsltMap.get("SELL_TO_CUST_CD");
        setValue(cMsg.dsAcctNum, dsAcctNum);

        String dsAcctNm = (String) rsltMap.get("DS_ACCT_NM");
        setValue(cMsg.dsAcctNm, dsAcctNm);

        String defBaseCycleCd = (String) rsltMap.get("DEF_BASE_CYCLE_CD");
        if (ZYPCommonFunc.hasValue(defBaseCycleCd)) {
            setValue(cMsg.baseBllgCycleCd, defBaseCycleCd);
        }

        String defUsgCycleCd = (String) rsltMap.get("DEF_USG_CYCLE_CD");
        if (ZYPCommonFunc.hasValue(defUsgCycleCd)) {
            setValue(cMsg.mtrBllgCycleCd, defUsgCycleCd);
        }

        String dsBillTgtrFlg = (String) rsltMap.get("DS_BILL_TGTR_FLG");
        // START 2022/03/22 [QC#59683, ADD]
        String dsInvTgtrTpCd = (String) rsltMap.get("DS_INV_TGTR_TP_CD");
        // END   2022/03/22 [QC#59683, ADD]
        if (!ZYPCommonFunc.hasValue(cMsg.dsContrCatgCd) || !DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
            setValue(cMsg.xxSelFlg_UT, dsBillTgtrFlg);
            // START 2022/03/22 [QC#59683, ADD]
            setValue(cMsg.dsInvTgtrTpCd, dsInvTgtrTpCd);
            // END   2022/03/22 [QC#59683, ADD]
        }

        String defBaseTpCd = (String) rsltMap.get("DEF_BASE_TP_CD");
        if (ZYPCommonFunc.hasValue(defBaseTpCd)) {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                setValue(cMsg.B.no(i).baseBllgTmgCd_B, defBaseTpCd);
            }
        } else {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                setValue(cMsg.B.no(i).baseBllgTmgCd_B, DEF_BASE_BLLG_TMG_TP_CD);
            }
        }

        String defUsgTpCd = (String) rsltMap.get("DEF_USG_TP_CD");
        if (ZYPCommonFunc.hasValue(defUsgTpCd)) {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                setValue(cMsg.B.no(i).mtrBllgTmgCd_B, defUsgTpCd);
            }
        } else {
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                setValue(cMsg.B.no(i).mtrBllgTmgCd_B, DEF_MTR_BLLG_TMG_TP_CD);
            }
        }
    }
    // END   2017/06/22 [QC#17496, ADD]

    // START 2017/06/27 [QC#19562, ADD]
    private static int getMainMachLineIndex(NSAL0300_BCMsgArray bCMsgArray, BigDecimal prntDsContrDtlPk) {
        int rtnIndex = bCMsgArray.getValidCount() - 1;
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (!hasValue(bCMsgArray.no(i).dsContrDtlPk_B)) {
                continue;
            }
            if (prntDsContrDtlPk.compareTo(bCMsgArray.no(i).dsContrDtlPk_B.getValue()) == 0) {
                rtnIndex = i;
            }
        }
        return rtnIndex;
    }
    // END   2017/06/27 [QC#19562, ADD]

    // START 2017/09/11 K.Kojima [QC#18435,ADD]
    private static boolean callContractTrackingAPIForPE(String glblCmpyCd, NSAL0300CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {
        List<Map<String, Object>> peList = NSAL0300Query.getInstance().getDsContrPrcEffForTracking(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
        for (int i = 0; i < peList.size(); i++) {
            BigDecimal dsContrPrcEffPk = (BigDecimal) peList.get(i).get("DS_CONTR_PRC_EFF_PK");
            String contrPrcEffFromDt = (String) peList.get(i).get("CONTR_PRC_EFF_FROM_DT");
            String contrPrcEffThruDt = (String) peList.get(i).get("CONTR_PRC_EFF_THRU_DT");
            if (!NSAL0300CommonLogic.callContractTrackingAPI(glblCmpyCd, cMsg, cMsg.dsContrPk.getValue(), dsContrDtlPk, dsContrBllgMtrPk, dsContrPrcEffPk, contrPrcEffFromDt, contrPrcEffThruDt, baseChrgFlg)) {
                return false;
            }
        }
        return true;
    }

    // END 2017/09/11 K.Kojima [QC#18435,ADD]

    // Add Start 09/13/2017 QC#21062
    // START 2017/11/10 K.Kojima [QC#22435,MOD]
    // private static int calcBllgCycleCntFromDuration(NSAL0300_BCMsg bCMsg, String glblCmpyCd) {
    private static int calcBllgCycleCntFromDuration(NSAL0300_BCMsg bCMsg, String glblCmpyCd, boolean invoicedFlag) {
    // END 2017/11/10 K.Kojima [QC#22435,MOD]
        // calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            // START 2017/09/21 K.Kitachi [QC#21222, MOD]
//            startDt = df.parse(bCMsg.contrEffFromDt_B.getValue());
            // START 2017/11/10 K.Kojima [QC#22435,MOD]
            // String calcFromDt = NSAL0300Query.getInstance().getMinUnbilledFromDt(glblCmpyCd, bCMsg.dsContrDtlPk_B.getValue());
            String calcFromDt = null;
            if (invoicedFlag) {
                calcFromDt = NSAL0300Query.getInstance().getMinUnbilledFromDt(glblCmpyCd, bCMsg.dsContrDtlPk_B.getValue());
            }
            // END 2017/11/10 K.Kojima [QC#22435,MOD]

            if (!hasValue(calcFromDt)) {
                calcFromDt = bCMsg.contrEffFromDt_B.getValue();
            } 
                // QC#59240 ModEnd
            startDt = df.parse(calcFromDt);
            // END 2017/09/21 K.Kitachi [QC#21222, MOD]
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = bCMsg.contrEffThruDt_B.getValue();
        // START 2019/11/02 [QC#53317,ADD]
        if (ZYPCommonFunc.hasValue(bCMsg.contrCloDt_B)) {
            paramEndDate = bCMsg.contrCloDt_B.getValue();
        }
        // END 2019/11/02 [QC#53317,ADD]
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return 0;
        }

        // get BLLG_CYCLE Info
        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, bCMsg.baseBllgCycleCd_B);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }

    private static void updateTermAmtForDsContrDtl(NSAL0300CMsg cMsg, String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal calculatedTermAmt) {

        DS_CONTR_DTLTMsg inTMsg = NSAL0300Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (inTMsg == null) {
            return;
        }
        setValue(inTMsg.basePrcTermDealAmtRate, calculatedTermAmt);

        EZDTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"Ds Contract Detail" });
        }
    }
    // Add End 09/13/2017 QC#21062

    // START 2017/10/05 K.Kojima [QC#20523,ADD]
    public static void releaseRenewalHoldForPO(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        if (!ZYPCommonFunc.hasValue(dsContrPk)) {
            return;
        }
        // QC#55590 Mod Start
        String dsPoReqFlg = (String) NSAL0300Query.getInstance().getDsPoReqFlg(glblCmpyCd, dsContrPk);
        List<BigDecimal> dsContrPrcEffPkList = new ArrayList<BigDecimal>();
        if (ZYPConstant.FLG_OFF_N.equals(dsPoReqFlg)) {
            dsContrPrcEffPkList = (List<BigDecimal>) NSAL0300Query.getInstance().getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo(glblCmpyCd, dsContrPk).getResultObject();
        } else {
            dsContrPrcEffPkList = (List<BigDecimal>) NSAL0300Query.getInstance().getRenewalHoldforPoReleaseDsContrPrcEffPk(glblCmpyCd, dsContrPk).getResultObject();
        }
        // QC#55590 Mod End
        if (dsContrPrcEffPkList == null || dsContrPrcEffPkList.size() == 0) {
            return;
        }
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < dsContrPrcEffPkList.size(); i++) {
            BigDecimal dsContrPrcEffPk = dsContrPrcEffPkList.get(i);
            DS_CONTR_PRC_EFFTMsg tMsg = NSAL0300Query.getInstance().getDsContrPrcEff(glblCmpyCd, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcEffStsCd, getNewStatus(slsDt, tMsg.contrPrcEffFromDt.getValue()));
            EZDTBLAccessor.update(tMsg);
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.baseChrgFlg.getValue())) {
                callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsg.dsContrDtlPk.getValue(), tMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffPk, tMsg.contrPrcEffFromDt.getValue(), tMsg.contrPrcEffThruDt.getValue(), DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsg.dsContrDtlPk.getValue(), tMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffPk, tMsg.contrPrcEffFromDt.getValue(), tMsg.contrPrcEffThruDt.getValue(), DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            if (!dsContrDtlPkList.contains(tMsg.dsContrDtlPk.getValue())) {
                dsContrDtlPkList.add(tMsg.dsContrDtlPk.getValue());
            }
        }
        if (dsContrDtlPkList.size() == 0) {
            return;
        }
        for (int i = 0; i < dsContrDtlPkList.size(); i++) {
            BigDecimal dsContrDtlPk = dsContrDtlPkList.get(i);
            DS_CONTR_DTLTMsg tMsg = NSAL0300Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlStsCd, getNewStatus(slsDt, tMsg.contrEffFromDt.getValue()));
            EZDTBLAccessor.update(tMsg);
            callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsg.dsContrDtlPk.getValue());
            if (!dsContrDtlPkList.contains(tMsg.dsContrDtlPk.getValue())) {
                dsContrDtlPkList.add(tMsg.dsContrDtlPk.getValue());
            }
            if (DS_CONTR_DTL_TP.FLEET.equals(tMsg.dsContrDtlTpCd.getValue())) {
                List<BigDecimal> dsContrDtlPkListForFleetMachine = (List<BigDecimal>) NSAL0300Query.getInstance().getRenewalHoldforPoReleaseDsContrDtlPk(glblCmpyCd, dsContrDtlPk).getResultObject();
                if (dsContrDtlPkListForFleetMachine == null || dsContrDtlPkListForFleetMachine.size() == 0) {
                    continue;
                }
                for (int m = 0; m < dsContrDtlPkListForFleetMachine.size(); m++) {
                    dsContrDtlPk = dsContrDtlPkListForFleetMachine.get(m);
                    DS_CONTR_DTLTMsg tMsgMachine = NSAL0300Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(tMsgMachine.dsContrDtlStsCd, getNewStatus(slsDt, tMsgMachine.contrEffFromDt.getValue()));
                    EZDTBLAccessor.update(tMsgMachine);
                    callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsgMachine.dsContrDtlPk.getValue());
                }
            }
        }
    }

    private static boolean callContractTrackingAPIforRenewalHold(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private static boolean callContractTrackingAPIforRenewalHold(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String dsContrTrkTpCd) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, dsContrTrkTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private static String getNewStatus(String slsDt, String effFromDt) {
        String dsContrDtlStsCd = null;
        if (slsDt.compareTo(effFromDt) >= 0) {
            dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
        } else {
            dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
        }
        return dsContrDtlStsCd;
    }
    // END 2017/10/05 K.Kojima [QC#20523,ADD]
    // Add Start 2018/01/15 QC#18552
    private static String getBillToCustAddr(String glblCmpyCd, String billToCustCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }
        BILL_TO_CUSTTMsg outMsg = NSAL0300Query.getInstance().getBillToCust(glblCmpyCd, billToCustCd);
        if (outMsg == null) {
            return null;
        }
        String firstLineAddr = outMsg.firstLineAddr.getValue();
        String scdLineAddr = outMsg.scdLineAddr.getValue();
        String thirdLineAddr = outMsg.thirdLineAddr.getValue();
        String frthLineAddr = outMsg.frthLineAddr.getValue();
        String ctyAddr = outMsg.ctyAddr.getValue();
        String stCd = outMsg.stCd.getValue();
        String postCd = outMsg.postCd.getValue();
        return NSAL0300CommonLogic.formatAddress(firstLineAddr, scdLineAddr, thirdLineAddr, frthLineAddr, ctyAddr, stCd, postCd);
    }
    // Add End 2018/01/15 QC#18552

    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
    /**
     * setMinVolMinAmtForAggr
     * @param cMsg NSAL0300CMsg
     */
    public static void setMinVolMinAmtForAggr(NSAL0300CMsg cMsg) {
        String preBllgMtrLbCd = null;
        int seqNo = 0;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (DS_CONTR_DTL_TP.AGGREGATE.equals(cMsg.B.no(i).dsContrDtlTpCd_B.getValue())) {
                if (!ZYPCommonFunc.hasValue(preBllgMtrLbCd)) {
                    preBllgMtrLbCd = cMsg.B.no(i).bllgMtrLbCd_B.getValue();
                }

                if (preBllgMtrLbCd.equals(cMsg.B.no(i).bllgMtrLbCd_B.getValue())) {
                    seqNo++;
                } else {
                    seqNo = 1;
                    preBllgMtrLbCd = cMsg.B.no(i).bllgMtrLbCd_B.getValue();
                }
                setMinVolMinAmtForAggr(cMsg.B, i, preBllgMtrLbCd, seqNo);
            }
        }
    }

    private static void setMinVolMinAmtForAggr(NSAL0300_BCMsgArray bCMsgArray, int idx, String bllgMtrLbCd, int seqNo) {
        if (!ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return;
        }

        BigDecimal sumMinVol = BigDecimal.ZERO;
        BigDecimal sumMinAmt = BigDecimal.ZERO;
        boolean isCalcMinVol = false;
        boolean isCalcMinAmt = false;
        int machSeq = 0;
        BigDecimal preMachPk = BigDecimal.valueOf(-1);
        for (int i = 0; i < bCMsgArray.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).dsContrDtlTpCd_B) && !DS_CONTR_DTL_TP.AGGREGATE.equals(bCMsgArray.no(i).dsContrDtlTpCd_B.getValue()) && bllgMtrLbCd.equals(bCMsgArray.no(i).bllgMtrLbCd_B.getValue())) {
                if (preMachPk.compareTo(bCMsgArray.no(i).svcMachMstrPk_B.getValue()) != 0) {
                    preMachPk = bCMsgArray.no(i).svcMachMstrPk_B.getValue();
                    machSeq = 1;
                } else {
                    machSeq++;
                }
                if (seqNo == machSeq) {
                    if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).bllgMinCnt_B)) {
                        sumMinVol = sumMinVol.add(bCMsgArray.no(i).bllgMinCnt_B.getValue());
                        isCalcMinVol = true;
                    }
                    if (ZYPCommonFunc.hasValue(bCMsgArray.no(i).bllgMinAmtRate_B)) {
                        sumMinAmt = sumMinAmt.add(bCMsgArray.no(i).bllgMinAmtRate_B.getValue());
                        isCalcMinAmt = true;
                    }
                }
            }
        }
        if (isCalcMinVol) {
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(idx).bllgMinCnt_B, sumMinVol);
        } else {
            bCMsgArray.no(idx).bllgMinCnt_B.clear();
        }
        if (isCalcMinAmt) {
            ZYPEZDItemValueSetter.setValue(bCMsgArray.no(idx).bllgMinAmtRate_B, sumMinAmt);
        } else {
            bCMsgArray.no(idx).bllgMinAmtRate_B.clear();
        }
    }
    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

    // START 2018/06/18 K.Kitachi [QC#18591, ADD]
    private static String getLocNum(String glblCmpyCd, String billToCustCd) {
        NSAL0300Query query = NSAL0300Query.getInstance();
        BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, billToCustCd);
        if (tMsg == null) {
            return null;
        }
        return tMsg.locNum.getValue();
    }

    private static NMZC002001PMsg createCtacUpdApiReqPMsg(NSAL0300CMsg cMsg, String glblCmpyCd, String slsDt, String locNum, String ctacPsnFirstNm, String ctacPsnLastNm, String procModeCd) {
        NMZC002001PMsg ctacUpdApiReqPMsg = new NMZC002001PMsg();
        setValue(ctacUpdApiReqPMsg.xxModeCd, procModeCd);
        setValue(ctacUpdApiReqPMsg.glblCmpyCd, glblCmpyCd);
        setValue(ctacUpdApiReqPMsg.slsDt, slsDt);
        setValue(ctacUpdApiReqPMsg.locNum, locNum);
        setValue(ctacUpdApiReqPMsg.ctacPsnFirstNm, ctacPsnFirstNm);
        setValue(ctacUpdApiReqPMsg.ctacPsnLastNm, ctacPsnLastNm);
        setValue(ctacUpdApiReqPMsg.ctacTpCd, CTAC_TP.BILL_TO_CONTACT_CONTRACTS);

        NMZC002001_ContactPointInfoListPMsg ctacPntInfo = ctacUpdApiReqPMsg.ContactPointInfoList.no(0);
        setValue(ctacPntInfo.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        setValue(ctacPntInfo.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
        String dummyEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(DUMMY_EML_ADDR, glblCmpyCd);
        setValue(ctacPntInfo.dsCtacPntValTxt, dummyEmlAddr);
        ctacUpdApiReqPMsg.ContactPointInfoList.setValidCount(1);

        return ctacUpdApiReqPMsg;
    }

    private static boolean callCtacUpdApi(NMZC002001PMsg ctacUpdApiReqPMsg, EZDCStringItem ctacPsnFirstNmItem, EZDCStringItem ctacPsnLastNmItem, EZDCBigDecimalItem ctacPsnPkItem) {
        NMZC002001 ctacUpdApi = new NMZC002001();
        ctacUpdApi.execute(ctacUpdApiReqPMsg, ONBATCH_TYPE.ONLINE);
        setCtacUpdateApiErrMsg(ctacUpdApiReqPMsg, ctacPsnFirstNmItem, ctacPsnLastNmItem);
        if (ctacPsnFirstNmItem.isError() || ctacPsnLastNmItem.isError()) {
            return false;
        }
        setValue(ctacPsnPkItem, ctacUpdApiReqPMsg.ctacPsnPk);
        return true;
    }

    private static void setCtacUpdateApiErrMsg(NMZC002001PMsg ctacUpdApiReqPMsg, EZDCStringItem ctacPsnFirstNmItem, EZDCStringItem ctacPsnLastNmItem) {
        if (S21ApiUtil.isXxMsgId(ctacUpdApiReqPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(ctacUpdApiReqPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                ctacPsnFirstNmItem.setErrorInfo(1, msgId, msgPrms);
                ctacPsnLastNmItem.setErrorInfo(1, msgId, msgPrms);
                return;
            }
        }
    }
    // END 2018/06/18 K.Kitachi [QC#18591, ADD]

    // START 2018/07/02 K.Kitachi [QC#26765, ADD]
    /**
     * checkCfsRelation
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static boolean checkCfsRelation(String glblCmpyCd, NSAL0300CMsg cMsg) {
        if (hasValue(cMsg.dsContrEdiCd) && DS_CONTR_EDI.CFS.equals(cMsg.dsContrEdiCd.getValue())) {
            if (!hasValue(cMsg.leaseCmpyCd)) {
                cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0727E);
                return false;
            }
            NSAL0300Query query = NSAL0300Query.getInstance();
            BigDecimal count = query.countLeaseDlrMap(glblCmpyCd, cMsg.leaseCmpyCd.getValue());
            if (count.compareTo(BigDecimal.ZERO) == 0) {
                cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0728E);
                return false;
            }
        }
        return true;
    }

    /**
     * checkLeaseCompany
     * @param glblCmpyCd String
     * @param cMsg NSAL0300CMsg
     */
    public static boolean checkLeaseCompany(String glblCmpyCd, NSAL0300CMsg cMsg) {
        NSAL0300Query query = NSAL0300Query.getInstance();
        BigDecimal count = query.countLeaseCmpy(glblCmpyCd, cMsg.leaseCmpyCd.getValue());
        if (count.compareTo(BigDecimal.ZERO) == 0) {
            cMsg.leaseCmpyCd.setErrorInfo(1, NSAM0729E);
            return false;
        }
        return true;
    }
    // END 2018/07/02 K.Kitachi [QC#26765, ADD]

    // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    // mod start 2018/08/30 QC#27961
    /**
     * Set Activate Flag For Special Instruction
     * @param cMsg NSAL0300CMsg
     */
    public static void setSpclInstnActivateFlg(NSAL0300CMsg cMsg) {
        //Map<String, String> spclInstnMap = new HashMap<String, String>();

        // Header
        if (ZYPCommonFunc.hasValue(cMsg.altPayerCustCd) && cMsg.altPayerCustCd.getErrorCode() == 0) {
            //setValue(cMsg.xxDplyCtrlFlg_SI, getSpclInstnActivateFlg(cMsg.altPayerCustCd.getValue(), cMsg.svcLineBizCd.getValue(), spclInstnMap));
            //spclInstnMap.put(cMsg.altPayerCustCd.getValue(), cMsg.xxDplyCtrlFlg_SI.getValue());
            setValue(cMsg.xxDplyCtrlFlg_SI, getSpclInstnActivateFlg(cMsg.altPayerCustCd.getValue(), cMsg));
        } else {
            setValue(cMsg.xxDplyCtrlFlg_SI, ZYPConstant.FLG_OFF_N);
        }

        // Detail
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            // Base
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).baseBillToCustCd_B) && cMsg.B.no(i).baseBillToCustCd_B.getErrorCode() == 0) {
                //setValue(cMsg.B.no(i).xxDplyCtrlFlg_BA, getSpclInstnActivateFlg(cMsg.B.no(i).baseBillToCustCd_B.getValue(), cMsg.svcLineBizCd.getValue(), spclInstnMap));
                //spclInstnMap.put(cMsg.B.no(i).baseBillToCustCd_B.getValue(), cMsg.B.no(i).xxDplyCtrlFlg_BA.getValue());
                setValue(cMsg.B.no(i).xxDplyCtrlFlg_BA, getSpclInstnActivateFlg(cMsg.B.no(i).baseBillToCustCd_B.getValue(), cMsg));
            } else {
                setValue(cMsg.B.no(i).xxDplyCtrlFlg_BA, ZYPConstant.FLG_OFF_N);
            }
            // Meter
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).bllgMtrBillToCustCd_B) && cMsg.B.no(i).bllgMtrBillToCustCd_B.getErrorCode() == 0) {
                //setValue(cMsg.B.no(i).xxDplyCtrlFlg_BB, getSpclInstnActivateFlg(cMsg.B.no(i).bllgMtrBillToCustCd_B.getValue(), cMsg.svcLineBizCd.getValue(), spclInstnMap));
                //spclInstnMap.put(cMsg.B.no(i).bllgMtrBillToCustCd_B.getValue(), cMsg.B.no(i).xxDplyCtrlFlg_BB.getValue());
                setValue(cMsg.B.no(i).xxDplyCtrlFlg_BB, getSpclInstnActivateFlg(cMsg.B.no(i).bllgMtrBillToCustCd_B.getValue(), cMsg));
            } else {
                setValue(cMsg.B.no(i).xxDplyCtrlFlg_BB, ZYPConstant.FLG_OFF_N);
            }
        }
    }

//    /**
//     * get Activate Flag For Special Instruction
//     * @param billToCustCd String
//     * @param svcLineBizCd String
//     * @param spclInstnMap Map<String, String>
//     * @return String FLG_ON_Y, FLG_OFF_N
//     */
//    public static String getSpclInstnActivateFlg(String billToCustCd, String svcLineBizCd, Map<String, String> spclInstnMap) {
//
//        if (ZYPCommonFunc.hasValue(spclInstnMap.get(billToCustCd))) {
//            return spclInstnMap.get(billToCustCd);
//        }
//
//        if (isExistSpclInstruction(billToCustCd, svcLineBizCd)) {
//            return ZYPConstant.FLG_ON_Y;
//        } else {
//            return ZYPConstant.FLG_OFF_N;
//        }
//    }

    private static String getSpclInstnActivateFlg(String billToCustCd, NSAL0300CMsg cMsg) {
        for (int i = 0; i < cMsg.Z.getValidCount(); i++) {
            if (billToCustCd.equals(cMsg.Z.no(i).billToCustCd_Z.getValue())) {
                return cMsg.Z.no(i).xxDplyCtrlFlg_Z.getValue();
            }
        }

        String existsSpclInstn = ZYPConstant.FLG_OFF_N;
        if (isExistSpclInstruction(billToCustCd, cMsg.svcLineBizCd.getValue())) {
            existsSpclInstn = ZYPConstant.FLG_ON_Y;
        }

        if (cMsg.Z.getValidCount() < cMsg.Z.length()) {
            int cnt = cMsg.Z.getValidCount();
            setValue(cMsg.Z.no(cnt).billToCustCd_Z, billToCustCd);
            setValue(cMsg.Z.no(cnt).xxDplyCtrlFlg_Z, existsSpclInstn);
            cMsg.Z.setValidCount(cnt + 1);
        }
        return existsSpclInstn;
    }
    // mod end 2018/08/30 QC#27961

    /**
     * Exists Special Instruction
     * @param billToCustCd String
     * @param svcLineBizCd String
     * @return boolean
     */
    public static boolean isExistSpclInstruction(String billToCustCd, String svcLineBizCd) {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();

        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            if (NWXC150001DsCheck.spclInstnDisplayDetermination(
                    glblCmpyCd, DS_TRX_RULE_TP.CONTRACT, null, null, billToCustCd, null, BIZ_ID, FUNC_CATG_ID, svcLineBizCd)){
                return true;
            }
        }
        return false;
    }
    // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    // Add Start 2018/08/20 QC#26949
    /**
     * checkSvcPgmMdse
     * @param cMsg NSAL0300CMsg
     * @param glblCmpyCd String
     */
    public static void checkSvcPgmMdse(NSAL0300CMsg cMsg, String glblCmpyCd) {
        int xxCellIdx = cMsg.xxCellIdx.getValueInt();
        EZDCStringItem svcPgmMdseCd;
        EZDCStringItem errItem;
        if (xxCellIdx > -1) {
            svcPgmMdseCd = cMsg.B.no(xxCellIdx).svcPgmMdseCd_B;
            errItem = cMsg.B.no(xxCellIdx).svcPgmMdseCd_B;
        } else {
            svcPgmMdseCd = cMsg.svcPgmMdseCd;
            errItem = cMsg.mdseDescShortTxt_SP;
        }
        if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
            return;
        }

        checkSvcPgmTp(glblCmpyCd, cMsg.dsContrCatgCd, svcPgmMdseCd, errItem);
    }

    public static boolean checkSvcPgmTp(String glblCmpyCd, EZDCStringItem dsContrCatgCd, EZDCStringItem svcPgmMdseCd, EZDCStringItem errItem) {
        if (!ZYPCommonFunc.hasValue(dsContrCatgCd)) {
            // No Check
            return true;
        }

        MDSETMsg mdseTMsg = NSAL0300Query.getInstance().getMdse(glblCmpyCd, svcPgmMdseCd.getValue());
        if (mdseTMsg == null) {
            errItem.setErrorInfo(1, NSAM0045E, new String[]{"Service Program" });
            return false;
        }

        if (SVC_PGM_TP.WARRANTY.equals(mdseTMsg.svcPgmTpCd.getValue())) {
            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd.getValue())
                    || DS_CONTR_CATG.FLEET.equals(dsContrCatgCd.getValue())
                    || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd.getValue())) {
                errItem.setErrorInfo(1, NSAM0734E);
                return false;
            }
        }

        if (DS_CONTR_CATG.WARRANTY.equals(dsContrCatgCd.getValue())) {
            if (!SVC_PGM_TP.WARRANTY.equals(mdseTMsg.svcPgmTpCd.getValue())) {
                errItem.setErrorInfo(1, NSAM0735E);
                return false;
            }
        }

        if (!NSXC001001ContrValidation.checkCsaWarranty(glblCmpyCd, dsContrCatgCd.getValue(), svcPgmMdseCd.getValue())) {
            errItem.setErrorInfo(1, NSAM0698E);
            return false;
        }
        return true;
    }
    // Add End 2018/08/20 QC#26949
    // START 2019/01/17 [QC#29782, MOD]
    // START 2018/08/22 [QC#22821, ADD]
    // public static String getAccSvcPgmMdseCd(String defSvcPgmMdseCd, String glblCmpyCd, String svcLineBizCd, String mdseCd, String rntlFlg) {
    public static String getAccSvcPgmMdseCd(String defSvcPgmMdseCd, String glblCmpyCd, String svcLineBizCd, String mdseCd, String rntlFlg, String prntSvcPgmMdseCd) {
        // NSXC001001GetAccSvcPgmMdse nsxc001001GetAccSvcPgmMdse = new NSXC001001GetAccSvcPgmMdse();
        // String svcPgmMdseCd = nsxc001001GetAccSvcPgmMdse.getAccSvcPgmMdse(glblCmpyCd, svcLineBizCd, mdseCd, rntlFlg);
        NSXC001001DefaultSvcPgmGetter nsxc001001DefaultSvcPgmGetter = new NSXC001001DefaultSvcPgmGetter();
        String svcPgmMdseCd = nsxc001001DefaultSvcPgmGetter.getAccSvcPgmMdse(glblCmpyCd, svcLineBizCd, mdseCd, rntlFlg, prntSvcPgmMdseCd);
        if (!hasValue(svcPgmMdseCd)) {
            svcPgmMdseCd = defSvcPgmMdseCd;
        }
        return svcPgmMdseCd;
    }
    // END   2018/08/22 [QC#22821, ADD]
    // END 2019/01/17 [QC#29782, MOD]

    // add start 2018/08/30 QC#27961
    public static void resetSpclInstnInfo(NSAL0300CMsg cMsg) {
        ZYPTableUtil.clear(cMsg.Z);
    }
    // add end 2018/08/30 QC#27961

    // START 2019/01/17 M.Naito [QC#29083,ADD]
    // Mod Start 2019/01/28 QC#29083
//    public static boolean isExistInvoicedBllg(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
//        NSAL0300Query query = NSAL0300Query.getInstance();
//        BigDecimal count = query.isExistInvoicedBllg(glblCmpyCd, dsContrBllgMtrPk);
//        if (count.compareTo(BigDecimal.ZERO) == 0) {
//            return false;
//        }
//        return true;
//    }
    public static boolean isUpdateFreeCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String cumCopyStartDt) {
        if (!hasValue(dsContrBllgMtrPk) || !hasValue(dsContrBllgMtrPk)) {
            return false;
        }

        Map<String, Object> schdMap = NSAL0300Query.getInstance().getUninvicedSchedule(glblCmpyCd, dsContrBllgMtrPk);
        if (schdMap == null) {
            return false;
        }

        String fromDt = (String) schdMap.get("BLLG_SCHD_FROM_DT");
        String thruDt = (String) schdMap.get("BLLG_SCHD_THRU_DT");
        if ((ZYPDateUtil.compare(cumCopyStartDt, fromDt) < 0) || (ZYPDateUtil.compare(cumCopyStartDt, thruDt) > 0)) {
            return false;
        }
        return true;
    }
    // Mod End 2019/01/28 QC#29083
    // END 2019/01/17 M.Naito [QC#29083,ADD]

    // START 2022/03/22 [QC#59683, ADD]
    public static String getInvSeptBaseUsgFlg(String glblCmpyCd, String dsInvTgtrTpCd) {
        DS_INV_TGTR_TPTMsg tMsg = NSAL0300Query.getInstance().getDsInvTgtrTp(glblCmpyCd, dsInvTgtrTpCd);
        if (tMsg != null) {
            return tMsg.invSeptBaseUsgFlg.getValue();
        }
        return ZYPConstant.FLG_OFF_N;
    }

    public static void editDsInvTgtrTpPullDownList(String glblCmpyCd, NSAL0300CMsg cMsg) {
        String dsContrCatgCd = cMsg.dsContrCatgCd.getValue();
        if (hasValue(dsContrCatgCd) && DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            deletePulldownList(cMsg.dsInvTgtrTpCd_E, cMsg.dsInvTgtrTpDescTxt_E, DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY);
            if (hasValue(cMsg.dsInvTgtrTpCd) && DS_INV_TGTR_TP.INVOICE_INDIVIDUALLY.equals(cMsg.dsInvTgtrTpCd.getValue())) {
                setValue(cMsg.dsInvTgtrTpCd, DS_INV_TGTR_TP.BILL_ALL_BASE_TOGETHER_AND_ALL_USAGE_TOGETHER);
            }
        } else {
            ZYPCodeDataUtil.createPulldownList(DS_INV_TGTR_TP.class, cMsg.dsInvTgtrTpCd_E, cMsg.dsInvTgtrTpDescTxt_E);
        }
        String invSeptBaseUsgFlg = getInvSeptBaseUsgFlg(glblCmpyCd, cMsg.dsInvTgtrTpCd.getValue());
        setValue(cMsg.xxSelFlg_UT, switchFlg(invSeptBaseUsgFlg));
    }
    // END   2022/03/22 [QC#59683, ADD]
}
