/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC010001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCommonFunc;
import parts.common.EZDDebugOutput;
import parts.common.EZDMessageEnv;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_MTR_READ_TPTMsg;
import business.db.DS_MTR_READ_TP_GRPTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_READ_SRC_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_PHYS_MTRTMsg;
import business.db.SVC_PHYS_MTRTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC010001_APMsg;
import business.parts.NSZC035001PMsg;
import business.parts.NSZC047022PMsg;
import business.parts.NSZC054001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC035001.NSZC035001;
import com.canon.cusa.s21.api.NSZ.NSZC035001.constant.NSZC035001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC054001.NSZC054001;
import com.canon.cusa.s21.common.NSX.NSXC001001.MtrWinInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMtrWinFromThruDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001MtrCntTwoPntEst;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.common.NSX.NSXC003001.SvcPhysMtrReadInfoBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CNTR_RESET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ROSS_BAT_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ROSS_SEND_TRGT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Meter Update API
 * 
 * Date         Company         Name            Create/Update   Defect No               GLBL CMPY CD  Description
 * ----------------------------------------------------------------------------------------------------------------------------         
 * 07/10/2013   WDS Team        K.ARATANI       Create          N/A                      ADB           New
 * 07/17/2013   WDS Team        K.ARATANI       Create          RQ#715                   ADB           DS_MDL_MTR_PK(Add Column in SVC_PHYS_MTR_READ)
 * 10/13/2015   Hitachi         K.Kishimoto     Update                                   ADB           coping with S21 CSA
 * 01/11/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#2913
 * 01/16/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#3757
 * 02/05/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#4151
 * 03/09/2016   Hitachi         T.Tomita        Update                                   ADB           QC#5199
 * 03/14/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#5427
 * 03/17/2016   Hitachi         M.Gotou         Update                                   ADB           QC#4403
 * 03/23/2016   Hitachi         T.Tomita        Update                                   ADB           QC#4430
 * 04/27/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#7583
 * 06/06/2016   Hitachi         Y.Tsuchimoto    Update                                   ADB           QC#9332
 * 07/08/2016   Hitachi         T.Kanasaka      Update                                   ADB           QC#10951
 * 07/26/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#5701
 * 09/14/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#14400
 * 10/03/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#12274
 * 10/03/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#14854
 * 11/04/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#15778
 * 11/25/2016   Hitachi         K.Ochiai        Update                                   ADB           QC#16170
 * 12/19/2016   Hitachi         K.Kishimoto     Update                                   ADB           QC#16512
 * 01/17/2017   Hitachi         K.Kitachi       Update                                   ADB           QC#16512
 * 2017/02/01   Hitachi         K.Kojima        Update                                   ADB           QC#15640
 * 2017/03/13   Hitachi         K.Kitachi       Update                                   ADB           QC#15163
 * 2017/03/27   Hitachi         T.Aoyagi        Update                                   ADB           QC#18094
 * 2017/03/29   Hitachi         T.Aoyagi        Update                                   ADB           QC#18091
 * 2017/05/31   Hitachi         A.Kohinata      Update                                   ADB           QC#18570
 * 2017/06/06   Hitachi         A.Kohinata      Update                                   ADB           QC#18617
 * 2017/08/08   Hitachi         A.Kohinata      Update                                   ADB           QC#18799
 * 2017/08/24   Hitachi         K.Kitachi       Update                                   ADB           QC#20746
 * 2017/08/28   Hitachi         T.Tomita        Update                                   ADB           QC#20537
 * 2017/09/01   Hitachi         K.Kasai         Update                                   ADB           QC#15134
 * 2017/09/15   Hitachi         K.Kojima        Update                                   ADB           QC#21125
 * 2017/09/19   Hitachi         T.Tomita        Update                                   ADB           QC#21185, 21189
 * 2017/09/19   Hitachi         K.Kojima        Update                                   ADB           QC#20869
 * 2017/09/22   Hitachi         K.Kasai         Update                                   ADB           QC#21330
 * 2017/09/27   Hitachi         K.Kasai         Update                                   ADB           QC#21443
 * 2017/09/28   Hitachi         K.Kasai         Update                                   ADB           QC#21482
 * 2017/10/11   Hitachi         A.Kohinata      Update                                   ADB           QC#18636
 * 2018/04/12   Hitachi         K.Kojima        Update                                   ADB           QC#23601
 * 2018/05/08   Hitachi         K.Kojima        Update                                   ADB           QC#24817
 * 2018/05/25   Hitachi         K.Kim           Update                                   ADB           QC#15410(Sol#246)
 * 2018/06/05   Hitachi         K.Kitachi       Update                                   ADB           QC#26048
 * 2018/06/04   Hitachi         A.Kohinata      Update                                   ADB           QC#26052
 * 2018/06/20   Hitachi         A.Kohinata      Update                                   ADB           QC#26700
 * 2018/06/26   Hitachi         K.Kishimoto     Update                                   ADB           QC#26192
 * 2018/06/29   Hitachi         A.Kohinata      Update                                   ADB           QC#26795
 * 2018/07/03   Hitachi         K.Kim           Update                                   ADB           QC#26726
 * 2018/07/12   Hitachi         K.Kitachi       Update                                   ADB           QC#27022
 * 2018/07/23   Hitachi         K.Kishimoto     Update                                   ADB           QC#23863
 * 2018/07/26   Hitachi         K.Kishimoto     Update                                   ADB           QC#27384
 * 2018/10/19   Hitachi         K.Kitachi       Update                                   ADB           QC#28733
 * 2019/05/27   Hitachi         A.Kohinata      Update                                   ADB           QC#50518
 * 2019/09/05   Hitachi         K.Kitachi       Update                                   ADB           QC#53247
 * 2019/09/11   Hitachi         K.Kitachi       Update                                   ADB           QC#53159
 * 2019/09/17   Hitachi         A.Kohinata      Update                                   ADB           QC#53261
 * 2019/09/18   Hitachi         K.Kitachi       Update                                   ADB           QC#53573
 * 2019/09/19   Hitachi         K.Kitachi       Update                                   ADB           QC#53159
 * 2019/11/14   Hitachi         A.Kohinata      Update                                   ADB           QC#54401
 * 2019/11/22   Hitachi         A.Kohinata      Update                                   ADB           QC#54799
 * 2019/11/27   Hitachi         K.Kitachi       Update                                   ADB           QC#54840
 * 2020/01/24   Hitachi         K.Kitachi       Update                                   ADB           QC#55495
 * 2020/03/03   Hitachi         A.Kohinata      Update                                   ADB           QC#56123
 * 2020/06/01   CITS            T.Sakurai       Update                                   ADB           QC#56941
 * 2020/07/03   CITS            T.Wada          Update                                   ADB           QC#57260
 * 2022/03/24   CITS            R.Jabal         Update                                   ADB           QC#59779
 * 2022/06/22   CITS            E.Sanchez       Update                                   ADB           QC#59804
 * 2022/08/12   Hitachi         N.Takatsu       Update                                   ADB           QC#60186
 * 2023/02/03   CITS            E.Sanchez       Update                                   ADB           QC#61138
 * 2023/06/08   CITS            T.Aizawa        Update                                   ADB           QC#60752
 * 2024/02/15   Hitachi         K.Watanabe      Update                                   ADB           QC#63529
 *</pre>
 */
public class NSZC010001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private final S21SsmBatchClient ssmClient;

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** DATE format for methods of this class */
    private static final String DATE_FORMAT = S21CalendarUtilConstants.TYPE_YYYYMMDD;

    // DEL-S 10/13/2015 K.Kishimoto
    // /** Meter Count Check Case */
    // private static final String[] mtrCountCheckArray =
    // {DS_MTR_READ_TP.PERIODIC_METER_READING,
    // DS_MTR_READ_TP.FINAL_METER_READING };
    //
    // /** Invoiced Flag Check Case */
    // private static final String[] mtrInvoicedCheckArray =
    // {DS_MTR_READ_TP.PERIODIC_METER_READING };
    // DEL-E 10/13/2015 K.Kishimoto

    /** Return Status Code */
    private static final String RETURN_CODE_NORMAL = "0";

    private static final String RETURN_CODE_ERROR = "9";

    /** MessageID */
    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    private static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Meter Reading Source Type Code" is a mandatory
     * field.
     */
    private static final String NSZM0355E = "NSZM0355E";

    /**
     * Input parameter "DS Meter Reading Type Code" is a mandatory
     * field.
     */
    private static final String NSZM0356E = "NSZM0356E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    private static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    private static final String NSZM0074E = "NSZM0074E";

    /**
     * Input parameter "Meter Information" is a mandatory field.
     */
    private static final String NSZM0357E = "NSZM0357E";

    /**
     * Input parameter "Physical Meter Number" or "Physical Meter ID"
     * is a mandatory field.
     */
    private static final String NSZM0358E = "NSZM0358E";

    /**
     * Input parameter "DS Meter Reading Type Code" does not found in
     * the DS_MTR_READ_TP.
     */
    private static final String NSZM0359E = "NSZM0359E";

    /**
     * Input parameter "Meter Reading Source Type Code" does not found
     * in the MTR_READ_SRC_TP.
     */
    private static final String NSZM0360E = "NSZM0360E";

    /**
     * Service machine master is not found.
     */
    private static final String NSZM0006E = "NSZM0006E";

    /**
     * This Meter is already entry as Invoiced.
     */
    private static final String NSZM0364E = "NSZM0364E";

    /**
     * This Meter is not exists in the DS_MDL_MTR.
     */
    private static final String NSZM0365E = "NSZM0365E";

    /**
     * Input parameter "Test Count" is negative amount.
     */
    private static final String NSZM0366E = "NSZM0366E";

    /**
     * Failed to register SVC_PHYS_MTR_READ.
     */
    private static final String NSZM0367E = "NSZM0367E";

    /**
     * Failed to update SVC_PHYS_MTR_READ.
     */
    private static final String NSZM0368E = "NSZM0368E";

    /**
     * Input parameter "Service Physical Meter Reading PK" does not
     * found in the SVC_PHYS_MTR_READ.
     */
    private static final String NSZM0369E = "NSZM0369E";

    /**
     * Reading Date cannot be greater than the today's date
     */
    private static final String NSZM0540E = "NSZM0540E";

    /**
     * Current meter reading can not be lower than the previous read.
     * Please select the meter reading type as 'Adjustment' to enter
     * lower reads.
     */
    private static final String NSZM0541E = "NSZM0541E";

    //START 2017/09/01 K.Kasai [QC#15134,ADD]
    /**
     * The entered meter is over the max meter digit.
     */
    private static final String NSZM1295E = "NSZM1295E";

    /**
     * This meter is not set the limit digit number.
     */
    private static final String NSZM1296E = "NSZM1296E";

    /**
     * Meter Type combination is incorrect. Please select same one or Actual and Exchange in Meter Type.
     */
    private static final String NSZM1297E = "NSZM1297E";
    //END 2017/09/01 K.Kasai [QC#15134,ADD]

    // ADD-S 10/13/2015 K.Kishimoto
    /**
     * Input Parameter "DS contract Detail PK" is mandatory field if
     * Start Meter Input.
     */
    private static final String NSZM0714E = "NSZM0714E";

    /**
     * Input Parameter "Contract Effective From Date" is mandatory
     * field if Start Meter Input.
     */
    private static final String NSZM0715E = "NSZM0715E";

    /**
     * Input parameter "DS Meter Reading Type Group Code was not found
     * in the DS_MTR_READ_TP_GRP.
     */
    private static final String NSZM0746E = "NSZM0746E";

    /**
     * The target data does not exist in Contract.
     */
    private static final String NSZM0617E = "NSZM0617E";

    /**
     * There is a discrepancy between the meter labels in parameter
     * and the machine physical meters.
     */
    private static final String NSZM0747E = "NSZM0747E";

    // Mod Start 2017/09/19 QC#21189
    /**
     * The current meter reading should be within +/- 75% of the
     * average volume for this meter.
     */
//    public static final String NSZM0542E = "NSZM0542E";
    public static final String NSZM1300W = "NSZM1300W";
    // Mod End 2017/09/19 QC#21189

    /**
     * The current meter reading should be Model Order Average Daily
     * Copy
     */
    public static final String NSZM0748W = "NSZM0748W";

    /**
     * Input Parameter "Meter Label Code" or
     * "Service Physical Meter Primary Key" is a mandatory field.
     */
    public static final String NSZM0745E = "NSZM0745E";

    /**
     * The Start Meter Reading Count Does not Exist.
     */
    public static final String NSZM0749E = "NSZM0749E";

    /**
     * Machine is missing start reads. Please enter a read with in set
     * days window.
     */
    public static final String NSZM0750E = "NSZM0750E";

    /**
     * Current Total meter reading is Over The Model Total
     */
    public static final String NSZM0751E = "NSZM0751E";

    // Add Start 2016/10/03 <QC#12274>
    /**
     * In-Reading against this service task has not been registered.
     */
    public static final String NSZM1065E = "NSZM1065E";


    /**
     * Out-Reading can not be lower than In-Reading.
     */
    public static final String NSZM1066E = "NSZM1066E";
    // Add End 2016/10/03 <QC#12274>

    // START 2018/05/08 K.Kojima [QC#24817,ADD]
    /**
     * Calculation result is negative. Please re-enter.
     */
    public static final String NSZM1333E = "NSZM1333E";
    // END 2018/05/08 K.Kojima [QC#24817,ADD]

    // START 2018/06/05 K.Kitachi [QC#26048, ADD]
    /**
     * The reads will be registered as meter rollover because of lower reads than previous one.
     */
    private static final String NSZM1335W = "NSZM1335W";
    // END 2018/06/05 K.Kitachi [QC#26048, ADD]

    // START 2019/09/18 K.Kitachi [QC#53573, ADD]
    /**
     * Allocated total meter reading will over the model max copies.
     */
    private static final String NSZM1366E = "NSZM1366E";
    // END 2019/09/18 K.Kitachi [QC#53573, ADD]

    // add start 2020/03/03 QC#56123
    /**
     * Current meter reading can not be higher than the next actual
     * read. Please enter lower reads than the next actual read.
     */
    public static final String NSZM1312E = "NSZM1312E";

    /**
     * Please entry meter read lower than the previous read if the
     * meter reading type is selected rollover.
     */
    public static final String NSZM1370E = "NSZM1370E";

    /**
     * The enteed reads are lower than the previous any read. If OK,
     * please submit again.
     */
    public static final String NSZM1371W = "NSZM1371W";

    /**
     * The entered reads are higher than the next any read. If OK,
     * please submit again.
     */
    public static final String NSZM1372W = "NSZM1372W";

    /**
     * Current Total meter reading is Over The Model Total. If OK,
     * please submit again.
     */
    public static final String NSZM1373W = "NSZM1373W";
    // add end 2020/03/03 QC#56123

    // START 2017/02/01 K.Kojima [QC#15640,ADD]
    /** Check Message ID List */
    // START 2017/09/15 K.Kojima [QC#21125,MOD]
    // private static final String[] CHECK_MSGID_LIST = new String[] {"NSZM0926E", "NSZM1077E" };
    private static final String[] CHECK_MSGID_LIST = new String[] {"NSZM0926E", "NSZM1077E", "NSZM1299E" };
    // END 2017/09/15 K.Kojima [QC#21125,MOD]

    // END 2017/02/01 K.Kojima [QC#15640,ADD]

    // START 2018/05/08 K.Kojima [QC#24817,DEL]
    // /**
    // * Total Meter
    // */
    // private static final String TOT_MTR = "TOT_MTR";
    // 
    // /**
    // * Black & White Meter
    // */
    // private static final String BW_MTR = "BW_MTR";
    // 
    // /**
    // * Color Meter
    // */
    // private static final String CLR_MTR = "CLR_MTR";
    // END 2018/05/08 K.Kojima [QC#24817,DEL]

    // ADD-E 10/13/2015 K.Kishimoto

    // START 2019/09/11 K.Kitachi [QC#53159, ADD]
    /**
     * Constant Key : NSZC0010_EXCL_CONTR_DTL_STS
     */
    private static final String CONST_KEY_EXCL_CONTR_DTL_STS = "NSZC0010_EXCL_CONTR_DTL_STS";

    /**
     * String : ","
     */
    private static final String STR_COMMA = ",";
    // END 2019/09/11 K.Kitachi [QC#53159, ADD]

    // Add Start 10/03/2016 <QC#12274>
    // START 2017/02/01 K.Kojima [QC#15640,MOD]
    // private static ONBATCH_TYPE onBatchType;
    private ONBATCH_TYPE onBatchType;
    // END 2017/02/01 K.Kojima [QC#15640,MOD]
    // Add End 10/03/2016 <QC#12274>

    //QC#57260 Add Start
    private ArrayList<BigDecimal> svcPhysMtrPkList = new ArrayList<BigDecimal>();
    private ArrayList<String> mdlMtrLbCdList = new ArrayList<String>();
    //QC#57260 Add End

    public NSZC010001() {
        super();
        this.ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute This can be called method from external class.
     * @param NSZC010001PMsg parameter
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(NSZC010001PMsg param, final ONBATCH_TYPE onBatchType) {

        this.msgMap = new S21ApiMessageMap(param);
        // Add Start 10/03/2016 <QC#12274>
        this.onBatchType = onBatchType;
        // Add End 10/03/2016 <QC#12274>
        doProcess();
        msgMap.flush();

    }

    /**
     * execute This can be called method from external class.
     * @param List<NSZC010001PMsg> list
     * @param ONBATCH_TYPE onBatchType
     */
    public void execute(List<NSZC010001PMsg> list, final ONBATCH_TYPE onBatchType) {

        for (NSZC010001PMsg param : list) {
            execute(param, onBatchType);
        }

    }

    /**
     * doProcess This is Main process Method.
     */
    @SuppressWarnings("unchecked")
    private void doProcess() {
        // #######################################
        // 0. Set initial value to parameter.
        // #######################################
        setInitReturnParam();

        // #######################################
        // 1-1. Mandatory Check
        // #######################################
        if (!checkMandatory()) {
            return;
        }

        NSZC010001PMsg param = (NSZC010001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        List<DS_CONTR_DTLTMsg> dsContrDtlTmsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        DS_CONTR_DTLTMsg dsContrDtlTmsg = null;
        Map<BigDecimal, AddlLineInfoBean> addlLineInfoMap = null;
        BigDecimal dsContrDtlPk = null;
        BigDecimal svcMachMstrPk = param.svcMachMstrPk.getValue();
        BigDecimal svcPhysMtrPk = null;
        String adjMtrFlg = null;
        boolean isBllgMtrCntFlg = false;
        String mtrReadDt = null;
        String entryDsMtrReadTpGrpCd = null;
        String prevDsMtrReadTpCdForCmbnChk = null;
        if (hasValue(param.A.no(0).mtrReadDt)) {
            mtrReadDt = param.A.no(0).mtrReadDt.getValue();
        } else {
            mtrReadDt = param.slsDt.getValue();
        }
        //QC#57260 Add Start
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = (NSZC010001_APMsg) param.A.no(i);
            if (hasValue(line.svcPhysMtrPk)) {
                svcPhysMtrPkList.add(line.svcPhysMtrPk.getValue());
            }
            if (hasValue(line.mdlMtrLbCd)) {
                mdlMtrLbCdList.add(line.mdlMtrLbCd.getValue());
            }
        }
        //QC#57260 Add End

        // #######################################
        // 1-2. Header Level Check
        // #######################################
        // #######################################
        // 1.2.1 Meter Reading Type Check
        // #######################################
        // get Meter Reading Type Info in Header(Default Value)
        DS_MTR_READ_TPTMsg mtrReadTpTmsg = getMtrReadTp(param.glblCmpyCd.getValue(), param.dsMtrReadTpCd.getValue());
        // END 2017/09/01 K.Kasai [QC#15134,MOD]
        if (mtrReadTpTmsg == null) {
            // "DS Meter Reading Type Code" does not found in the DS_MTR_READ_TP.
            setHeaderErrMsg(param, NSZM0359E);
            return;
        }
        // get Adjust Meter flag in Header(Default Value)
        adjMtrFlg = mtrReadTpTmsg.adjMtrReadTpFlg.getValue();
        // Check Meter Reading Type(Line level)
        // #######################################
        // 1-2.2 DS Meter Reading Type Group Check
        // #######################################
        DS_MTR_READ_TP_GRPTMsg dsMtrReadTpGrpTmsg = getDsMtrReadTpGrp(param);
        if (dsMtrReadTpGrpTmsg == null) {
            // "DS Meter Reading Type Group Code" does not found in the DS_MTR_READ_TP_GRP.
            setHeaderErrMsg(param, NSZM0746E);
            return;
        }
        entryDsMtrReadTpGrpCd = param.dsMtrReadTpGrpCd.getValue();
        if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue())) {
            isBllgMtrCntFlg = true;
        }
        if (!hasValue(param.dsContrDtlPk) && isBllgMtrCntFlg) {
            // START 2019/09/11 K.Kitachi [QC#53159, MOD]
//            dsContrDtlTmsgList = NSXC001001GetContr.getContrDtlByMachMstrPkList(glblCmpyCd, svcMachMstrPk, mtrReadDt);
            dsContrDtlTmsgList = getDsContrDtlTMsgList(glblCmpyCd, svcMachMstrPk, mtrReadDt);
            // END 2019/09/11 K.Kitachi [QC#53159, MOD]
            if (dsContrDtlTmsgList == null || dsContrDtlTmsgList.size() == 0) {
                setHeaderErrMsg(param, NSZM0617E);
                return;
            }
            for (int i = 0; i < dsContrDtlTmsgList.size(); i++) {
                dsContrDtlPkList.add(dsContrDtlTmsgList.get(i).dsContrDtlPk.getValue());
            }
        } else if (hasValue(param.dsContrDtlPk)) {
            dsContrDtlPkList.add(param.dsContrDtlPk.getValue());
            dsContrDtlTmsg = getDsContrDtlByPk(glblCmpyCd, param.dsContrDtlPk.getValue());
            if (dsContrDtlTmsg == null) {
                setHeaderErrMsg(param, NSZM0617E);
                return;
            }
            dsContrDtlTmsgList.add(dsContrDtlTmsg);
        }
        // #######################################
        // 1-2.3 Meter Reading Source Check
        // #######################################
        MTR_READ_SRC_TPTMsg mtrReadSrcTpTmsg = getMtrReadSrcTp(param);
        if (mtrReadSrcTpTmsg == null) {
            // "Meter Reading Source Type Code" does not found in the MTR_READ_SRC_TP.
            setHeaderErrMsg(param, NSZM0360E);
            return;
        }
        // #######################################
        // 1-2.4 Machine Master Check
        // #######################################
        SVC_MACH_MSTRTMsg machMstrTmsg = getSvcMachMstr(param);
        if (machMstrTmsg == null) {
            // Service machine master is not found.
            setHeaderErrMsg(param, NSZM0006E);
            return;
        }
        // #######################################
        // 2. Physical Meter Derive
        // #######################################
        // #######################################
        // 2.1 SvcPhysMtrPK derive
        // #######################################
        SVC_PHYS_MTRTMsgArray svcPhysMtrTmsgArray = getSvcPhysMtrTmsgArray(param);
        Map<String, BigDecimal> mtrLbMtrPkMap = new HashMap<String, BigDecimal>();
        Map<BigDecimal, String> mtrPkMtrLbMap = new HashMap<BigDecimal, String>();
        for (int i = 0; i < svcPhysMtrTmsgArray.getValidCount(); i++) {
            mtrLbMtrPkMap.put(svcPhysMtrTmsgArray.no(i).mdlMtrLbCd.getValue(), svcPhysMtrTmsgArray.no(i).svcPhysMtrPk.getValue());
            mtrPkMtrLbMap.put(svcPhysMtrTmsgArray.no(i).svcPhysMtrPk.getValue(), svcPhysMtrTmsgArray.no(i).mdlMtrLbCd.getValue());
        }
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = (NSZC010001_APMsg) param.A.no(i);
            if (!hasValue(line.svcPhysMtrPk)) {
                setValue(line.svcPhysMtrPk, mtrLbMtrPkMap.get(line.mdlMtrLbCd.getValue()));
            } else if (!hasValue(line.mdlMtrLbCd)) {
                setValue(line.mdlMtrLbCd, mtrPkMtrLbMap.get(line.svcPhysMtrPk.getValue()));
            }
        }
        svcPhysMtrPk = param.A.no(0).svcPhysMtrPk.getValue();
        // #######################################
        // 2.2 Set Meter Formula
        // #######################################
        if (!setMtrFmla(param, glblCmpyCd, svcMachMstrPk)) {
            return;
        }

        // #######################################
        // 2.3 Set Non Meter Formula
        // #######################################
        setNonMtrFmla(param, svcPhysMtrTmsgArray);
        // #######################################
        // 2.4. parameter has all Physical meter read PK?
        // #######################################
        if (param.A.getValidCount() < svcPhysMtrTmsgArray.getValidCount()) {
            setHeaderErrMsg(param, NSZM0747E);
            return;
        }
        Map<BigDecimal, String> prmPhysPkMap = new HashMap<BigDecimal, String>();
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = (NSZC010001_APMsg) param.A.no(i);
            prmPhysPkMap.put(line.svcPhysMtrPk.getValue(), line.mdlMtrLbCd.getValue());
        }
        for (int i = 0; i < svcPhysMtrTmsgArray.getValidCount(); i++) {
            if (!prmPhysPkMap.containsKey(svcPhysMtrTmsgArray.no(i).svcPhysMtrPk.getValue())) {
                setHeaderErrMsg(param, NSZM0747E);
                return;
            }
        }
        // #######################################
        // 3. Start Meter Entry Check
        // #######################################
        if (FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
            BigDecimal startMtrSvcPhysMtrReadGrpSq = NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, param.dsContrDtlPk.getValue(), svcPhysMtrPk);
            // START 2022/06/22 E.Sanchez [QC#59804, ADD]
            dsContrDtlPk = param.dsContrDtlPk.getValue();
            // END 2022/06/22 E.Sanchez [QC#59804, ADD]
            if (NSXC003001SvcPhysMtrRead.hasErrMachineMissingStartRead(glblCmpyCd, dsContrDtlPk, mtrReadDt)) {
                setHeaderErrMsg(param, NSZM0750E);
                return;
            }
            if (hasValue(startMtrSvcPhysMtrReadGrpSq)) {
                clearStartMtr(param, startMtrSvcPhysMtrReadGrpSq);
                startMtrSvcPhysMtrReadGrpSq = null;
            }
        }
        // #######################################
        // 4. Get Additional Line info
        // #######################################
        if (isBllgMtrCntFlg && !FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
            // START 2019/09/19 K.Kitachi [QC#53159, ADD]
            if (!isRegistStartRead(glblCmpyCd, dsContrDtlTmsgList)) {
                setHeaderErrMsg(param, NSZM0749E);
                return;
            }
            // END 2019/09/19 K.Kitachi [QC#53159, ADD]
            addlLineInfoMap = getAddlLineInfoMap(param, glblCmpyCd, dsContrDtlTmsgList, mtrReadDt);
        }
        if (hasMessage()) {
            return;
        }
        // #######################################
        // 5. current Total meter reading should be Over Model Total
        // #######################################
        // START 2019/09/05 K.Kitachi [QC#53247, DEL]
//        if (!FLG_ON_Y.equals(param.xxStartReadFlg.getValue()) && isBllgMtrCntFlg && !FLG_ON_Y.equals(adjMtrFlg)) {
//            BigDecimal ttlMtrCnt = getTtlMtrCnt(param);
//            if (hasMessage()) {
//                return;
//            }
//            if (!FLG_ON_Y.equals(param.xxStartReadFlg.getValue()) && isBllgMtrCntFlg && !FLG_ON_Y.equals(adjMtrFlg) && hasValue(ttlMtrCnt)) {
//                if (hasErrMdlTtlOver(param, ttlMtrCnt, addlLineInfoMap)) {
//                    return;
//                }
//            }
//        }
        // END 2019/09/05 K.Kitachi [QC#53247, DEL]
        // #######################################
        // 6. Line Level Check
        // #######################################
        // Add Start 10/03/2016 <QC#12274>
        boolean isTestCopyInput = false;
        // Add End 10/03/2016 <QC#12274>
        // Mod Start 01/17/2017 <QC#16512>
        boolean isAdjSvcFrceRead = false;
        // START 2017/09/01 K.Kasai [QC#15134,DEL]
        // START 2017/03/27 [QC#18094, ADD]
        boolean isValidChecked = false;
        // END 2017/03/27 [QC#18094, ADD]
        // START 2017/09/01 K.Kasai [QC#15134,ADD]
        int cntrDigitNum = 0;
        // START 2018/07/26 [QC#27384 MOD]
        ArrayList<Boolean> isExchMtr = new ArrayList<Boolean>();
        ArrayList<Boolean> isRollOverMtr = new ArrayList<Boolean>();
        // END  /07/26 [QC#27384 MOD]
        String dsMtrReadTpCd = param.dsMtrReadTpCd.getValue();
        // END 2017/09/01 K.Kasai [QC#15134,ADD]
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = param.A.no(i);
            // START 2018/07/26 [QC#27384 MOD]
            isExchMtr.add(false);
            isRollOverMtr.add(false);
            // END   2018/07/26 [QC#27384 MOD]
            if (!hasValue(line.dsMtrReadTpCd)) {
                setValue(line.dsMtrReadTpCd, dsMtrReadTpCd);
            }
            // #######################################
            // 6.1 Set Meter Reading Date
            // #######################################
            if (!hasValue(line.mtrReadDt)) {
                line.mtrReadDt.setValue(param.slsDt.getValue());
            } else if (!hasValue(line.svcPhysMtrReadPk)) {
                // EZDCommonFunc#cmpDate
                // * @param ls1 [I] Date 1(YYYYMMDD)
                // * @param ls2 [I] Date 2(YYYYMMDD)
                // * @return 0:Date 1=Date 2/ -1:Date 1<Date 2/
                // -2:
                // Error
                int ret = EZDCommonFunc.cmpDate(param.slsDt.getValue(), line.mtrReadDt.getValue());
                if (ret == -1) {
                    // QC#57260 Mod Start
                    if (isVldCounter(line)) {
                        // "Meter Reading Date" is future date.
                        setDetailErrMsg(param, line, NSZM0540E);
                        return;
                    }
                    // QC#57260 Mod End
                }
            }
            // #######################################
            // 6.2. svcPhysMtrReadPk check
            // #######################################
            SVC_PHYS_MTR_READTMsg svcPhysMtrRead = null;
            if (hasValue(line.svcPhysMtrReadPk)) {
                // START 2017/09/01 K.Kasai [QC#15134,MOD]
                // svcPhysMtrRead = getSvcPhysMtrRead(param, line);
                svcPhysMtrRead = getSvcPhysMtrRead(param.glblCmpyCd.getValue(), line.svcPhysMtrReadPk.getValue());
                // END 2017/09/01 K.Kasai [QC#15134,MOD]
                if (svcPhysMtrRead == null) {
                    // QC#57260 Mod Start
                    if (isVldCounter(line)) {
                        // Input parameter "Service Physical Meter Reading PK" does not found in the SVC_PHYS_MTR_READ.
                        setDetailErrMsg(param, line, NSZM0369E);
                        return;
                    }
                    // QC#57260 Mod End
                }
            }
            // #######################################
            // 6.3. Meter Exists Check
            // #######################################
            if (!checkMeterExists(param, line)) {
                // QC#57260 Mod Start
                if (isVldCounter(line)) {
                    // This Meter is not exists in the DS_MDL_MTR.
                    setDetailErrMsg(param, line, NSZM0365E);
                    return;
                }
             // QC#57260 Mod End
            }
            // START 2018/07/26 [QC#27384 ADD]
            boolean isExchMtrCurr = false;
            boolean isRollOverMtrCurr = false;
            // END   2018/07/26 [QC#27384 ADD]
            // END 2017/09/22 K.Kasai [QC#21330,ADD]
            // START 2017/09/01 K.Kasai [QC#15134,ADD]
            // get meter limit digit num
            cntrDigitNum = getCntrDigitNum(svcPhysMtrTmsgArray, line.svcPhysMtrPk.getValue(), line.mtrLbCd.getValue());
            // get dsMtrReadTpCd(set line value(default:header))
            dsMtrReadTpCd = hasValue(line.dsMtrReadTpCd) ? line.dsMtrReadTpCd.getValue() : param.dsMtrReadTpCd.getValue();
            // check whether this meter read is rollover/exchange
            // target or not.
            // START 2018/07/26 [QC#27384 MOD]
            isExchMtrCurr = isMtrExch(dsMtrReadTpCd);
            isExchMtr.set(i, isExchMtrCurr);
            if (!isExchMtrCurr && cntrDigitNum > 0) {
                // mod start 2020/03/03 QC#56123
                //isRollOverMtrCurr =  isMtrRO(param, line, dsMtrReadTpCd);
                isRollOverMtrCurr =  isMtrRO(param, line, dsMtrReadTpCd, false);
                // mod end 2020/03/03 QC#56123
                isRollOverMtr.set(i, isRollOverMtrCurr);
            }
            // END   2018/07/26 [QC#27384 MOD]
            if (DS_MTR_READ_TP.ADJUSTMENT.equals(dsMtrReadTpCd) && DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(param.dsMtrReadTpGrpCd.getValue())) {
                isAdjSvcFrceRead = true;
            }
            // END 2017/09/01 K.Kasai [QC#15134,ADD]
            // ADD-S 10/13/2015 K.Kishimoto
            AddlLineInfoBean addlLine = null;
            if (addlLineInfoMap != null) {
                addlLine = addlLineInfoMap.get(param.A.no(i).svcPhysMtrPk.getValue());
            }
            // ADD-E 10/13/2015 K.Kishimoto

            // START 2017/09/27 K.Kasai [QC#21482,ADD]
            if (DS_MTR_READ_TP.ROLLOVER.equals(line.dsMtrReadTpCd.getValue())) {
                setValue(line.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
            }
            // END 2017/09/27 K.Kasai [QC#21482,ADD]
            // check existence of DS_MTR_READ_TP
            mtrReadTpTmsg = getMtrReadTp(param.glblCmpyCd.getValue(), line.dsMtrReadTpCd.getValue());
            if (mtrReadTpTmsg == null) {
                // "DS Meter Reading Type Code" does not found in the  DS_MTR_READ_TP.
                setHeaderErrMsg(param, NSZM0359E);
                return;
            }
            if (FLG_OFF_N.equals(adjMtrFlg)) {
                adjMtrFlg = mtrReadTpTmsg.adjMtrReadTpFlg.getValue();
            }
            // #######################################
            // 6.4 Meter Count Check
            // #######################################
            // Mod Start 01/17/2017 <QC#16512>
            if (isAdjSvcFrceRead == false) {
                // START 2017/09/01 K.Kasai [QC#15134,MOD]
                // mod start 2020/03/03 QC#56123
                //checkMeterCount(param, line, cntrDigitNum);
                checkMeterCount(param, line, cntrDigitNum, isRollOverMtrCurr);
                // mod end 2020/03/03 QC#56123
                // END 2017/09/01 K.Kasai [QC#15134,MOD]
            }

            // #######################################
            // 6.5 check DS_MTR_READ_TP_CD combination check(Actual and Exchange combination and all same is OK.)
            // #######################################
            // skip if a case of 1st loop
            if (prevDsMtrReadTpCdForCmbnChk == null) {
                // do nothing.
                // in the case of prev:Actual, curr:Exchange or Actual
                // is OK, Other is NG.
            } else if (DS_MTR_READ_TP.ACTUAL.equals(prevDsMtrReadTpCdForCmbnChk)) {
                if (DS_MTR_READ_TP.EXCHANGE_METER_TO.equals(line.dsMtrReadTpCd.getValue()) || DS_MTR_READ_TP.ACTUAL.equals(line.dsMtrReadTpCd.getValue())) {
                    // do nothing.
                } else {
                    setHeaderErrMsg(param, NSZM1297E);
                    return;
                }
                // in the case of prev:Exchange, curr:Exchange or
                // Actual is OK, Other is NG.
            } else if (DS_MTR_READ_TP.EXCHANGE_METER_TO.equals(prevDsMtrReadTpCdForCmbnChk)) {
                if (DS_MTR_READ_TP.EXCHANGE_METER_TO.equals(line.dsMtrReadTpCd.getValue()) || DS_MTR_READ_TP.ACTUAL.equals(line.dsMtrReadTpCd.getValue())) {
                    // do nothing.
                } else {
                    setHeaderErrMsg(param, NSZM1297E);
                    return;
                }
                // in the case of prev and curr is same, the
                // combination is OK. Other is NG.
            } else if (!prevDsMtrReadTpCdForCmbnChk.equals(line.dsMtrReadTpCd.getValue())) {
                setHeaderErrMsg(param, NSZM1297E);
                return;
            }
            prevDsMtrReadTpCdForCmbnChk = line.dsMtrReadTpCd.getValue();
            // #######################################
            // 1-11. Test Count Check
            // #######################################
            // Mod Start 01/17/2017 <QC#16512>
            // mod start 2017/10/11 CSA Defect#18636
            if (!MTR_READ_SRC_TP.CUSTOMER_CARE.equals(param.mtrReadSrcTpCd.getValue())) {
                if (!hasValue(line.svcPhysMtrReadPk) && !isAdjSvcFrceRead) {
                    setValue(line.testMtrCnt, BigDecimal.ZERO);
                }
                if (!hasValue(line.svcPhysMtrReadPk) && DS_MTR_READ_TP_GRP.SERVICE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && !isAdjSvcFrceRead) {
                    if (DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(param.dsTestCopyClsCd.getValue())) {
                        Map<String, Object> testCopyInInfo = getTestCopyInInfo(glblCmpyCd, param, line);
                        if (testCopyInInfo == null || DS_TEST_COPY_CLS.TEST_COPY_OUT.equals((String) testCopyInInfo.get("DS_TEST_COPY_CLS_CD"))) {
                            // QC#57260 Mod Start
                            if (isVldCounter(line)) {
                                setDetailErrMsg(param, line, NSZM1065E);
                                return;
                            }
                            // QC#57260 Mod End
                        } else {
                            BigDecimal inReadCnt = (BigDecimal) testCopyInInfo.get("READ_MTR_CNT");
                            BigDecimal outReadCnt = line.readMtrCnt.getValue();
                            BigDecimal testCopyCnt = BigDecimal.ZERO;
                            // in the case of Meter Exchange
                            // START 2018/07/26 [QC#27384 MOD]
                            if (isExchMtrCurr) {
                                // START 2020/03/03 [QC#56123, DEL]
//                                testCopyCnt = line.readMtrCnt.getValue();
                                // END   2020/03/03 [QC#56123, DEL]
                                // in the case of Meter Rollover
                            // mod start 2018/06/29 QC#26795
                            } else if (inReadCnt.compareTo(outReadCnt) > 0 || isRollOverMtrCurr) {
                            // mod end 2018/06/29 QC#26795
                                outReadCnt = calcMtrCntWithRO(outReadCnt.intValue(), cntrDigitNum);
                                testCopyCnt = outReadCnt.subtract(inReadCnt);
                            } else {
                                testCopyCnt = outReadCnt.subtract(inReadCnt);
                            }
                            setValue(line.testMtrCnt, testCopyCnt);
                            if (BigDecimal.ZERO.compareTo(testCopyCnt) < 0) {
                                isTestCopyInput = true;
                            }
                            // END   2018/07/26 [QC#27384 MOD]
                            // END 2017/09/01 K.Kasai [QC#15134,MOD]
                        }
                    }
                }
            }
            // add start 2020/03/03 QC#56123
            BigDecimal readMtrCnt = line.readMtrCnt.getValue();
            if (isRollOverMtrCurr) {
                readMtrCnt = calcMtrCntWithRO(line.readMtrCnt.getValueInt(), cntrDigitNum);
            }
            // add end 2020/03/03 QC#56123
            // mod start 2020/03/03 QC#56123
            //if (addlLine != null && DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && !hasValue(line.svcPhysMtrReadPk) && !FLG_ON_Y.equals(adjMtrFlg)) {
            if (addlLine != null && DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && !hasValue(line.svcPhysMtrReadPk) && !FLG_ON_Y.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue())) {
            // mod end 2020/03/03 QC#56123
                // Mod End 04/27/2016 <QC#7583>
                // #######################################
                // 1-x. current meter reading should be within xx%
                // #######################################
                // START 2017/09/01 K.Kasai [QC#15134,MOD]
                // in the case of meter exchange, skip this check
                // process.
                // START 2018/07/26 [QC#27384 MOD]
                if (!isExchMtrCurr) {
                // END   2018/07/26 [QC#27384 MOD]
                    // Mod Start 2017/09/19 QC#21189
                    if (!FLG_ON_Y.equals(param.xxWrnSkipFlg.getValue())) {
                        BigDecimal dtlPk = addlLine.getDsContrDtlPk();
                        BigDecimal startMtrSvcPhysMtrReadGrpSq = addlLine.getStartMeterSvcPhysMtrTMsg().svcPhysMtrReadGrpSq.getValue();
                        // START 2018/10/19 K.Kitachi [QC#28733, MOD]
//                        if (NSXC003001SvcPhysMtrRead.hasErrAveVolForMtr(glblCmpyCd, dtlPk, addlLine.getDsContrBllgMtrPk(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), line.readMtrCnt.getValue())) {
//                            if (hasValue(startMtrSvcPhysMtrReadGrpSq) && hasValue(addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq()) && startMtrSvcPhysMtrReadGrpSq.compareTo(addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq()) != 0) {
//                                // Mod Start 2017/09/19 QC#21189
//                                // setDetailErrMsg(param, line, NSZM0542E);
//                                setDetailErrMsg(param, line, NSZM1300W);
//                                // Mod End 2017/09/19 QC#21189
//                            }
//                        }
//                        // #######################################
//                        // 1-x. current meter reading should be Model Order Average Daily Copy
//                        // #######################################
//                        if (NSXC003001SvcPhysMtrRead.isOverAdcv(glblCmpyCd, dtlPk, svcMachMstrPk, line.mdlMtrLbCd.getValue(), line.svcPhysMtrPk.getValue(), addlLine.getLastStagingMeterSvcPhysMtrReadGrpSq(), line.mtrReadDt
//                                .getValue(),
//                                line.readMtrCnt.getValue())) {
//                            setDetailErrMsg(param, line, NSZM0748W);
//                        }
                        if (!NSXC003001SvcPhysMtrRead.hasErrAveVolForMtrPreChk(glblCmpyCd, dtlPk, addlLine.getDsContrBllgMtrPk(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue())) {
                            // mod start 2020/03/03 QC#56123
                            //if (NSXC003001SvcPhysMtrRead.hasErrAveVolForMtr(glblCmpyCd, dtlPk, addlLine.getDsContrBllgMtrPk(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), line.readMtrCnt.getValue())) {
                            if (NSXC003001SvcPhysMtrRead.hasErrAveVolForMtr(glblCmpyCd, dtlPk, addlLine.getDsContrBllgMtrPk(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), readMtrCnt)) {
                            // mod end 2020/03/03 QC#56123
                                if (hasValue(startMtrSvcPhysMtrReadGrpSq) && hasValue(addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq()) && startMtrSvcPhysMtrReadGrpSq.compareTo(addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq()) != 0) {
                                    // QC#57260 Mod Start
                                    if (isVldCounter(line)) {
                                        setDetailErrMsg(param, line, NSZM1300W);
                                    }
                                    // QC#57260 Mod End
                                }
                            }
                            // mod end 2020/03/03 QC#56123
                        } else {
                            // #######################################
                            // 1-x. current meter reading should be Model Order Average Daily Copy
                            // #######################################
                            // mod start 2020/03/03 QC#56123
                            //if (NSXC003001SvcPhysMtrRead.isOverAdcv(glblCmpyCd, dtlPk, svcMachMstrPk, line.mdlMtrLbCd.getValue(), line.svcPhysMtrPk.getValue(), addlLine.getLastStagingMeterSvcPhysMtrReadGrpSq(), line.mtrReadDt.getValue(), line.readMtrCnt.getValue())) {
                            if (NSXC003001SvcPhysMtrRead.isOverAdcv(glblCmpyCd, dtlPk, svcMachMstrPk, line.mdlMtrLbCd.getValue(), line.svcPhysMtrPk.getValue(), addlLine.getLastStagingMeterSvcPhysMtrReadGrpSq(), line.mtrReadDt.getValue(), readMtrCnt)) {
                                // QC#57260 Mod Start
                                if (isVldCounter(line)) {
                                    // mod end 2020/03/03 QC#56123
                                    setDetailErrMsg(param, line, NSZM0748W);
                                }
                                // QC#57260 Mod End
                            }
                        }
                        // END 2018/10/19 K.Kitachi [QC#28733, MOD]
                    }
                    // Mod End 2017/09/19 QC#21189
                }
            }
            // add start 2020/03/03 QC#56123
            if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && !hasValue(line.svcPhysMtrReadPk) && !FLG_ON_Y.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue())) {
                if (!isExchMtrCurr && !FLG_ON_Y.equals(param.xxWrnSkipFlg.getValue())) {
                    // #######################################
                    // 1-x. current meter reading should be within xx% of ADCV
                    // #######################################
                    List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
                    dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
                    dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
                    SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoExclSvcTaskNum(param.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList, param.svcTaskNum.getValue());
                    if (bean != null) {
                        SVC_PHYS_MTR_READTMsg tMsg = getSvcPhysMtrReadByKey(param.glblCmpyCd.getValue(), bean.getSvcPhysMtrReadPk());
                        if (NSXC003001SvcPhysMtrRead.isOverAdcvSvcRead(glblCmpyCd, svcMachMstrPk, line.mdlMtrLbCd.getValue(), line.svcPhysMtrPk.getValue(), tMsg.svcPhysMtrReadGrpSq.getValue(), line.mtrReadDt.getValue(), readMtrCnt)) {
                            // QC#57260 Mod Start
                            if (isVldCounter(line)) {
                                setDetailErrMsg(param, line, NSZM1300W);
                            }
                        }
                    }
                }
            }
            // add end 2020/03/03 QC#56123
        }
        if (hasMessage()) {
            return;
        }

        // #######################################
        // 7. Meter Registry
        // #######################################
        BigDecimal svcPhysMtrReadGrpSq = null;
        // #######################################
        // 7.1 Get New svcPhysMtrReadGrpSq
        // #######################################
        if (!hasValue(param.A.no(0).svcPhysMtrReadPk)) {
            // START 2017/03/13 K.Kitachi [QC#15163, MOD]
            // svcPhysMtrReadGrpSq =
            // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PHYS_MTR_READ_GRP_SQ);
            svcPhysMtrReadGrpSq = getSvcPhysMtrReadGrpSq(glblCmpyCd, svcMachMstrPk);
            // END 2017/03/13 K.Kitachi [QC#15163, MOD]
        }
        for (int i = 0; i < param.A.getValidCount(); i++) {
            // START 2018/07/26 [QC#27384 ADD]
            boolean isExchMtrCurr = isExchMtr.get(i);
            boolean isRollOverMtrCurr = isRollOverMtr.get(i);
            // END   2018/07/26 [QC#27384 ADD]
            NSZC010001_APMsg line = param.A.no(i);
            // #######################################
            // 7.2 Set Meter Date
            // #######################################
            if (!hasValue(line.rgtnMtrDt)) {
                line.rgtnMtrDt.setValue(getMeterDate(param, line));
            }
            SVC_PHYS_MTR_READTMsg svcPhysMtrRead = null;
            if (hasValue(line.svcPhysMtrReadPk)) {
                svcPhysMtrRead = getSvcPhysMtrRead(glblCmpyCd, line.svcPhysMtrReadPk.getValue());
                // update
                setValue(svcPhysMtrRead.rgtnMtrDt, line.rgtnMtrDt);
                setValue(svcPhysMtrRead.readMtrCnt, line.readMtrCnt);
                setValue(svcPhysMtrRead.testMtrCnt, line.testMtrCnt);
                setValue(svcPhysMtrRead.mtrReadDt, line.mtrReadDt);
                if (!hasValue(line.invProcFlg)) {
                    line.invProcFlg.setValue("N");
                }
                setValue(svcPhysMtrRead.invProcFlg, line.invProcFlg);
                setValue(svcPhysMtrRead.rgtnUsrId, line.rgtnUsrId);
                if (!hasValue(line.estFlg)) {
                    line.estFlg.setValue("N");
                }
                setValue(svcPhysMtrRead.estFlg, line.estFlg);
                setValue(svcPhysMtrRead.mtrLbCd, line.mtrLbCd);
                setValue(svcPhysMtrRead.mtrClsTpCd, line.mtrClsTpCd);

                // START 2017/09/01 K.Kasai [QC#15134,MOD]
                // setValue(svcPhysMtrRead.dsMtrReadTpCd,
                // param.dsMtrReadTpCd);
                // mod start 2020/03/03 QC#56123
                //if (DS_MTR_READ_TP.ROLLOVER.equals(dsMtrReadTpCd)) {
                if (isRollOverMtrCurr || isExchMtrCurr) {
                // mod end 2020/03/03 QC#56123
                    setValue(svcPhysMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                } else {
                    // START 2020/06/01 T.Sakurai [QC#56941,MOD]
                    setValue(svcPhysMtrRead.dsMtrReadTpCd, line.dsMtrReadTpCd);
                    // END 2020/06/01 T.Sakurai [QC#56941,MOD]
                }
                // END 2017/09/01 K.Kasai [QC#15134,MOD]
                setValue(svcPhysMtrRead.mtrReadSrcTpCd, param.mtrReadSrcTpCd);
                setValue(svcPhysMtrRead.dsMdlMtrPk, line.dsMdlMtrPk); // RQ#715

                setValue(svcPhysMtrRead.mtrEntryCmntTxt, line.mtrEntryCmntTxt);
                // START 2017/03/27 [QC#18094, ADD]
                if (svcPhysMtrRead.vldMtrFlg.getValue().equals(ZYPConstant.FLG_OFF_N) && line.vldMtrFlg.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                    isValidChecked = true;
                }
                // END 2017/03/27 [QC#18094, ADD]
                setValue(svcPhysMtrRead.vldMtrFlg, line.vldMtrFlg);

                // START 2016/06/06 [QC#9332, ADD]
                if (!hasValue(svcPhysMtrRead.rossBatProcStsCd)) {
                    setValue(svcPhysMtrRead.rossBatProcStsCd, ROSS_BAT_PROC_STS.NOT_PROCESSED);
                }
                if (!hasValue(svcPhysMtrRead.rossSendTrgtCd)) {
                    setValue(svcPhysMtrRead.rossSendTrgtCd, ROSS_SEND_TRGT.NOT_APPLICABLE_BY_TRANSACTION_STATUS);
                }
                // END 2016/06/06 [QC#9332, ADD]

                if (hasValue(svcPhysMtrRead.svcTaskNum)) {
                    isTestCopyInput = true;
                }

                EZDTBLAccessor.update(svcPhysMtrRead);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcPhysMtrRead.getReturnCode())) {
                    // Failed to update SVC_PHYS_MTR_READ.
                    setDetailErrMsg(param, line, NSZM0368E);
                    // START 2017/09/01 K.Kasai [QC#15134,ADD]
                    return;
                } else {
                    // set the insert data's svcPhysMtrReadPk
                    setValue(line.svcPhysMtrReadPk_O, svcPhysMtrRead.svcPhysMtrReadPk);
                    // END 2017/09/01 K.Kasai [QC#15134,ADD]
                }

            } else {
                // insert
                svcPhysMtrRead = new SVC_PHYS_MTR_READTMsg();
                setValue(svcPhysMtrRead.glblCmpyCd, param.glblCmpyCd);
                setValue(svcPhysMtrRead.svcPhysMtrReadPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_PHYS_MTR_READ_SQ));
                setValue(svcPhysMtrRead.svcMachMstrPk, param.svcMachMstrPk);
                setValue(svcPhysMtrRead.physMtrId, line.physMtrId);
                setValue(svcPhysMtrRead.mtrLbCd, line.mtrLbCd);
                setValue(svcPhysMtrRead.mtrClsTpCd, line.mtrClsTpCd);
                setValue(svcPhysMtrRead.rgtnMtrDt, line.rgtnMtrDt);
                setValue(svcPhysMtrRead.readMtrCnt, line.readMtrCnt);
                setValue(svcPhysMtrRead.testMtrCnt, line.testMtrCnt);
                setValue(svcPhysMtrRead.mtrReadDt, line.mtrReadDt);
                if (!hasValue(line.invProcFlg)) {
                    line.invProcFlg.setValue("N");
                }
                setValue(svcPhysMtrRead.invProcFlg, line.invProcFlg);
                // START 2017/09/01 K.Kasai [QC#15134,MOD]
                // setValue(svcPhysMtrRead.dsMtrReadTpCd,
                // param.dsMtrReadTpCd);
                // mod start 2020/03/03 QC#56123
                //if (DS_MTR_READ_TP.ROLLOVER.equals(dsMtrReadTpCd)) {
                if (isRollOverMtrCurr || isExchMtrCurr) {
                // mod end 2020/03/03 QC#56123
                    setValue(svcPhysMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                } else {
                    // START 2020/06/01 T.Sakurai [QC#56941,MOD]
                    setValue(svcPhysMtrRead.dsMtrReadTpCd, line.dsMtrReadTpCd);
                    // END 2020/06/01 T.Sakurai [QC#56941,MOD]
                }
                // END 2017/09/01 K.Kasai [QC#15134,MOD]
                setValue(svcPhysMtrRead.mtrReadSrcTpCd, param.mtrReadSrcTpCd);
                // Add Start 10/03/2016 <QC#12274>
                setValue(svcPhysMtrRead.svcTaskNum, param.svcTaskNum);
                setValue(svcPhysMtrRead.dsTestCopyClsCd, param.dsTestCopyClsCd);
                // Add Start 10/03/2016 <QC#12274>
                setValue(svcPhysMtrRead.fsrNum, param.fsrNum);
                setValue(svcPhysMtrRead.fsrVisitNum, param.fsrVisitNum);
                setValue(svcPhysMtrRead.rgtnUsrId, line.rgtnUsrId);
                if (!hasValue(line.estFlg)) {
                    line.estFlg.setValue("N");
                }
                setValue(svcPhysMtrRead.estFlg, line.estFlg);
                setValue(svcPhysMtrRead.dsMdlMtrPk, line.dsMdlMtrPk); // RQ#715
                // MOD-S 10/13/2015 K.Kishimoto
                // setValue(svcPhysMtrRead.svcPhysMtrReadGrpSq,
                // line.svcPhysMtrReadGrpSq);
                setValue(svcPhysMtrRead.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
                // MOD-E 10/13/2015 K.Kishimoto
                setValue(svcPhysMtrRead.svcPhysMtrPk, line.svcPhysMtrPk);
                setValue(svcPhysMtrRead.mtrEntryCmntTxt, line.mtrEntryCmntTxt);
                setValue(svcPhysMtrRead.vldMtrFlg, line.vldMtrFlg);
                // ADD-S 10/13/2015 K.Kishimoto
                setValue(svcPhysMtrRead.dsMtrReadTpGrpCd, entryDsMtrReadTpGrpCd);
                if (FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
                    setValue(svcPhysMtrRead.dsContrDtlPk, param.dsContrDtlPk);
                }
                // ADD-E 10/13/2015 K.Kishimoto

                // START 2016/06/06 [QC#9332, ADD]
                setValue(svcPhysMtrRead.rossBatProcStsCd, ROSS_BAT_PROC_STS.NOT_PROCESSED);
                setValue(svcPhysMtrRead.rossSendTrgtCd, ROSS_SEND_TRGT.NOT_APPLICABLE_BY_TRANSACTION_STATUS);
                // END 2016/06/06 [QC#9332, ADD]
                // START 2017/09/01 K.Kasai [QC#15134,ADD]
                // START 2018/07/26 [QC#27384 MOD]
                if (isRollOverMtrCurr) {
                    setValue(svcPhysMtrRead.cntrResetTpCd, CNTR_RESET_TP.METER_ROLLOVER);
                } else if (isExchMtrCurr) {
                    setValue(svcPhysMtrRead.cntrResetTpCd, CNTR_RESET_TP.METER_EXCHANGE);
                }
                // END  2018/07/26 [QC#27384 MOD]
                // END 2017/09/01 K.Kasai [QC#15134,ADD]

                // START 2020/01/24 K.Kitachi [QC#55495, ADD]
                setValue(svcPhysMtrRead.carryOverTpCd, ZYPConstant.FLG_OFF_0);
                // START 2022/06/22 E.Sanchez [QC#59804, MOD]
                // boolean isCarryOver = isCarryOver(svcPhysMtrRead.glblCmpyCd.getValue(), svcPhysMtrRead.svcPhysMtrPk.getValue(),svcPhysMtrRead.mtrReadDt.getValue(), svcPhysMtrRead.dsMtrReadTpGrpCd.getValue());
                boolean isCarryOver = isCarryOver(svcPhysMtrRead.glblCmpyCd.getValue(), svcPhysMtrRead.svcPhysMtrPk.getValue(),svcPhysMtrRead.mtrReadDt.getValue(),
                                                  svcPhysMtrRead.dsMtrReadTpGrpCd.getValue(), param.xxStartReadFlg.getValue());
                // END 2022/06/22 E.Sanchez [QC#59804, MOD]
                if (isCarryOver) {
                    setValue(svcPhysMtrRead.carryOverTpCd, ZYPConstant.FLG_ON_1);
                }
                // END 2020/01/24 K.Kitachi [QC#55495, ADD]

                EZDTBLAccessor.insert(svcPhysMtrRead);
                // MOD-S 03/09/2016 T.Tomita
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcPhysMtrRead.getReturnCode())) {
                    // Failed to register SVC_PHYS_MTR_READ.
                    setDetailErrMsg(param, line, NSZM0367E);
                    return;
                } else {
                    setValue(line.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
                    // START 2017/09/01 K.Kasai [QC#15134,ADD]
                    // set the insert data's svcPhysMtrReadPk
                    setValue(line.svcPhysMtrReadPk_O, svcPhysMtrRead.svcPhysMtrReadPk);
                    // END 2017/09/01 K.Kasai [QC#15134,ADD]
                    // add start 2019/05/27 QC#50518
                    setValue(line.cntrResetTpCd, svcPhysMtrRead.cntrResetTpCd);
                    // add end 2019/05/27 QC#50518
                }
                // MOD-E 03/09/2016 T.Tomita
            }
            // Update DS_CONTR_BLLG_SCHD
            AddlLineInfoBean addlLine = null;
            if (addlLineInfoMap != null) {
                addlLine = addlLineInfoMap.get(param.A.no(i).svcPhysMtrPk.getValue());
            }
            DS_CONTR_BLLG_SCHDTMsg schdTmsg = null;
            if (addlLine != null) {
                schdTmsg = addlLine.getDsContrBllgSchdTMsg();
            }
            // Mod Start 10/03/2016 <QC#14854>
            // Mod Start 01/26/2016 <QC#3757>
            if (schdTmsg != null && !FLG_ON_Y.equals(schdTmsg.invFlg.getValue())) {
                // START 2023/06/08 t.aizawa [QC#60752,DEL]
                // START 2018/07/23 [QC#23863, MOD]
                // SVC_PHYS_MTR_READTMsg bllgMtrRead = NSXC003001SvcPhysMtrRead.getBillingMeterSvcPhysMtrRead(glblCmpyCd, addlLine.getDsContrDtlPk(), svcMachMstrPk, svcPhysMtrPk, addlLine.getMtrWinFromDt(), addlLine.getMtrWinThruDt());
                // END   2018/07/23 [QC#23863, MOD]
                // END   2023/06/08 t.aizawa [QC#60752,DEL]
                // START 2023/06/08 t.aizawa [QC#60752,ADD]
                String mtrWinFromDt = addlLine.getMtrWinFromDt();
                String bllgSchdFromDt = schdTmsg.bllgSchdFromDt.getValue();
                if (ZYPDateUtil.compare(mtrWinFromDt, bllgSchdFromDt) < 0) {
                    mtrWinFromDt = bllgSchdFromDt;
                }
                SVC_PHYS_MTR_READTMsg bllgMtrRead = NSXC003001SvcPhysMtrRead.getBillingMeterSvcPhysMtrRead(glblCmpyCd, addlLine.getDsContrDtlPk(), svcMachMstrPk, svcPhysMtrPk, mtrWinFromDt, addlLine.getMtrWinThruDt());
                // END   2023/06/08 t.aizawa [QC#60752,ADD]
                if (bllgMtrRead == null) {
                    UpdateDsContrBllgSchd(param, schdTmsg, null, FLG_OFF_N);
                } else {
                    BigDecimal bllgMtrReagGrpSq = bllgMtrRead.svcPhysMtrReadGrpSq.getValue();
                    BigDecimal schdMtrReagGrpSq = schdTmsg.svcPhysMtrReadGrpSq.getValue();
                    if (!hasValue(schdMtrReagGrpSq) || bllgMtrReagGrpSq.compareTo(schdMtrReagGrpSq) != 0) {
                        UpdateDsContrBllgSchd(param, schdTmsg, bllgMtrReagGrpSq, FLG_ON_Y);
                        // START 2017/08/24 K.Kitachi [QC#20746, DEL]
                        // isExecPreview = true;
                        // END 2017/08/24 K.Kitachi [QC#20746, DEL]
                    }
                }
            }
        }
        if (hasMessage()) {
            return;
        }
        // START 2019/09/05 K.Kitachi [QC#53247, ADD]
        // #######################################
        // 5. current Total meter reading should be Over Model Total
        // #######################################
        if (!FLG_ON_Y.equals(param.xxStartReadFlg.getValue()) && isBllgMtrCntFlg && !FLG_ON_Y.equals(adjMtrFlg)) {
            // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//            BigDecimal ttlMtrCnt = getTtlMtrCnt(param);
            BigDecimal ttlMtrCnt = getTtlMtrCnt(param, false);
            // END 2019/09/18 K.Kitachi [QC#53573, MOD]
            if (hasMessage()) {
                return;
            }
            if (!FLG_ON_Y.equals(param.xxStartReadFlg.getValue()) && isBllgMtrCntFlg && !FLG_ON_Y.equals(adjMtrFlg) && hasValue(ttlMtrCnt)) {
                // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//                if (hasErrMdlTtlOver(param, ttlMtrCnt, addlLineInfoMap)) {
                if (hasErrMdlTtlOver(param, ttlMtrCnt, addlLineInfoMap, false)) {
                // END 2019/09/18 K.Kitachi [QC#53573, MOD]
                    return;
                }
            }
        }
        // END 2019/09/05 K.Kitachi [QC#53247, ADD]
        // add start 2020/03/03 QC#56123
        if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && !FLG_ON_Y.equals(adjMtrFlg) && !FLG_ON_Y.equals(param.xxWrnSkipFlg.getValue())) {
            if (hasErrMdlTtlOverSvcRead(param)) {
                return;
            }
        }
        // add end 2020/03/03 QC#56123

        if (FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
            return;
        }
        // #######################################
        // 3. Back Allocate
        // #######################################
        for (DS_CONTR_DTLTMsg dtlTMsg : dsContrDtlTmsgList) {
            if (!hasValue(param.A.no(0).svcPhysMtrReadPk) && DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && FLG_OFF_N.equals(adjMtrFlg)) {
                // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//                execBackAlloc(param, addlLineInfoMap, dtlTMsg, svcPhysMtrTmsgArray);
                execBackAlloc(param, addlLineInfoMap, dtlTMsg, svcPhysMtrTmsgArray, dsContrDtlTmsgList);
                // END 2019/09/18 K.Kitachi [QC#53573, MOD]
            } else if (isValidChecked && DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && FLG_OFF_N.equals(adjMtrFlg)) {
                // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//                execBackAlloc(param, addlLineInfoMap, dtlTMsg, svcPhysMtrTmsgArray);
                execBackAlloc(param, addlLineInfoMap, dtlTMsg, svcPhysMtrTmsgArray, dsContrDtlTmsgList);
                // END 2019/09/18 K.Kitachi [QC#53573, MOD]
            }
        }
        // START 2017/08/24 K.Kitachi [QC#20746, ADD]
        if (!execResetProcess(param, dsContrDtlTmsgList)) {
            return;
        }
        // END 2017/08/24 K.Kitachi [QC#20746, ADD]

        // #######################################
        // 4. Preview Billing
        // #######################################
        // Preivew Billing API
        previewBilling(param, dsContrDtlTmsgList);

    }

    // ADD-S 10/13/2015 K.Kishimoto

    // START 2017/09/01 K.Kasai [QC#15134,MOD]
    // private void execBackAlloc(NSZC010001PMsg param,
    // Map<BigDecimal, AddlLineInfoBean> addlLineInfoMap,
    // DS_CONTR_DTLTMsg dsContrDtlTmsg, BigDecimal
    // startMtrSvcPhysMtrReadGrpSq) {
    // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//    private void execBackAlloc(NSZC010001PMsg param, Map<BigDecimal, AddlLineInfoBean> addlLineInfoMap, DS_CONTR_DTLTMsg dsContrDtlTmsg, SVC_PHYS_MTRTMsgArray svcPhysMtrTmsgArray) {
    private void execBackAlloc(NSZC010001PMsg param, Map<BigDecimal, AddlLineInfoBean> addlLineInfoMap, DS_CONTR_DTLTMsg dsContrDtlTmsg, SVC_PHYS_MTRTMsgArray svcPhysMtrTmsgArray, List<DS_CONTR_DTLTMsg> dsContrDtlTmsgList) {
    // END 2019/09/18 K.Kitachi [QC#53573, MOD]
        // END 2017/09/01 K.Kasai [QC#15134,MOD]
        String glblCmpyCd = param.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = dsContrDtlTmsg.dsContrDtlPk.getValue();
        BigDecimal startGrpSq    = NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, param.A.no(0).svcPhysMtrPk.getValue());
        String fromDt = dsContrDtlTmsg.contrEffFromDt.getValue();
        String thruDt = param.A.no(0).mtrReadDt.getValue();
        List<String> backAllocTargetDateList = getBackAllocTargetDate(glblCmpyCd, dsContrDtlPk, fromDt, thruDt);
        if (backAllocTargetDateList == null || backAllocTargetDateList.isEmpty()) {
            return;
        }
        for (String backAllocTargetDate : backAllocTargetDateList) {
            BigDecimal backAllocTargetGrpSq = getGrpSqByReadDt(glblCmpyCd, param.A.no(0).svcPhysMtrPk.getValue(), backAllocTargetDate);
            BigDecimal estCntSvcPhysMtrReadGrpSq = null;
            if (backAllocTargetGrpSq == null) {
                NSZC010001PMsg copyParam = new NSZC010001PMsg();
                EZDMsg.copy(param, null, copyParam, null);
                // START 2017/03/29 [QC#18091, ADD]
                // END 2017/03/29 [QC#18091, ADD]
                // START 2017/03/13 K.Kitachi [QC#15163, MOD]
                // BigDecimal estCntSvcPhysMtrReadGrpSq =
                // ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PHYS_MTR_READ_GRP_SQ);
                estCntSvcPhysMtrReadGrpSq = getSvcPhysMtrReadGrpSq(glblCmpyCd, param.svcMachMstrPk.getValue());
                // END 2017/03/13 K.Kitachi [QC#15163, MOD]
                for (int i = 0; i < copyParam.A.getValidCount(); i++) {
                    NSZC010001_APMsg line = copyParam.A.no(i);
                    BigDecimal physMtrPk = line.svcPhysMtrPk.getValue();
                    
                    SVC_PHYS_MTR_READTMsg startMtrReadTmsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, physMtrPk, startGrpSq);
                    if (startMtrReadTmsg == null) {
                        continue;
                    }
                    BigDecimal startMtrSvcPhysMtrReadGrpSq = startMtrReadTmsg.svcPhysMtrReadGrpSq.getValue();
                    BigDecimal lastPhysMtrReadGrpSq = getlastBllgMtrGrpSqByBllgMtrPk(glblCmpyCd, dsContrDtlPk, null, fromDt, backAllocTargetDate);
                    if (lastPhysMtrReadGrpSq == null) {
                        lastPhysMtrReadGrpSq = startMtrSvcPhysMtrReadGrpSq;
                    }
                    SvcPhysMtrReadInfoBean inpMtrReadInfo = new SvcPhysMtrReadInfoBean();
                    SvcPhysMtrReadInfoBean lastMtrReadInfo = new SvcPhysMtrReadInfoBean();
                    // START 2017/09/01 K.Kasai [QC#15134,ADD]
                    inpMtrReadInfo.setSvcPhysMtrReadPk(copyParam.A.no(i).svcPhysMtrReadPk_O.getValue());
                    inpMtrReadInfo.setSvcPhysMtrPk(copyParam.A.no(i).svcPhysMtrPk.getValue());
                    // END 2017/09/01 K.Kasai [QC#15134,ADD]
                    inpMtrReadInfo.setMtrReadDt(copyParam.A.no(i).mtrReadDt.getValue());
                    inpMtrReadInfo.setReadMtrCnt(copyParam.A.no(i).readMtrCnt.getValue());
                    // Start 01/11/2016 K.Kishimoto [QC#2913]
                    // START 2017/03/29 [QC#18091, DEL]
                    // BigDecimal lastPhysMtrReadGrpSq;
                    // if (hasValue(bllgMtrPk)) {
                    // lastPhysMtrReadGrpSq =
                    // getlastBllgMtrGrpSqByBllgMtrPk(glblCmpyCd,
                    // dsContrDtlPk, bllgMtrPk, fromDt,
                    // backAllocTargetDate);
                    // if (lastPhysMtrReadGrpSq == null) {
                    // lastPhysMtrReadGrpSq = startMtrSvcPhysMtrReadGrpSq;
                    // }
                    // } else {
                    // lastPhysMtrReadGrpSq = startMtrSvcPhysMtrReadGrpSq;
                    // }
                    // END 2017/03/29 [QC#18091, DEL]
                    // End 01/11/2016 K.Kishimoto [QC#2913]
                    SVC_PHYS_MTR_READTMsg fromMtr = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, copyParam.A.no(i).svcPhysMtrPk.getValue(), lastPhysMtrReadGrpSq);
                    // START 2017/09/01 K.Kasai [QC#15134,ADD]
                    lastMtrReadInfo.setSvcPhysMtrReadPk(fromMtr.svcPhysMtrReadPk.getValue());
                    lastMtrReadInfo.setSvcPhysMtrPk(fromMtr.svcPhysMtrPk.getValue());
                    // END 2017/09/01 K.Kasai [QC#15134,ADD]
                    lastMtrReadInfo.setMtrReadDt(fromMtr.mtrReadDt.getValue());
                    lastMtrReadInfo.setReadMtrCnt(fromMtr.readMtrCnt.getValue());
                    // START 2017/09/01 K.Kasai [QC#15134,MOD]
                    // BigDecimal estMtrCnt =
                    // NSXC003001SvcPhysMtrRead.estMtrCnt(backAllocTargetDate,
                    // lastMtrReadInfo, inpMtrReadInfo);
                    lastMtrReadInfo.setMtrReadDt(fromMtr.mtrReadDt.getValue());
                    lastMtrReadInfo.setReadMtrCnt(fromMtr.readMtrCnt.getValue());
                    List<SvcPhysMtrReadInfoBean> mtrReadList = new ArrayList<SvcPhysMtrReadInfoBean>();
                    mtrReadList.add(lastMtrReadInfo);
                    mtrReadList.add(inpMtrReadInfo);
                    BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(glblCmpyCd, backAllocTargetDate, mtrReadList);
                    // END 2017/09/01 K.Kasai [QC#15134,ADD]
                    setValue(copyParam.A.no(i).mtrReadDt, backAllocTargetDate);

                    // START 2022/03/24 R.Jabal [QC#59779, ADD]
                    BigDecimal lastReadMtrCnt = getPreviousMeterCount(glblCmpyCd, param.svcMachMstrPk.getValue(), lastMtrReadInfo.getSvcPhysMtrPk(), backAllocTargetDate, FLG_ON_Y);
                    if (ZYPCommonFunc.hasValue(lastReadMtrCnt) && ZYPCommonFunc.hasValue(estMtrCnt) && lastReadMtrCnt.compareTo(estMtrCnt) > 0) {
                        estMtrCnt = lastReadMtrCnt;
                    }
                    // END 2022/03/24 R.Jabal [QC#59779, ADD]
                    setValue(copyParam.A.no(i).readMtrCnt, estMtrCnt);
                    // add start 2019/09/17 QC#53261
                    if (!CNTR_RESET_TP.METER_ROLLOVER.equals(copyParam.A.no(i).cntrResetTpCd.getValue()) && hasValue(inpMtrReadInfo.getReadMtrCnt()) && hasValue(estMtrCnt) && inpMtrReadInfo.getReadMtrCnt().compareTo(estMtrCnt) < 0) {
                        setValue(copyParam.A.no(i).readMtrCnt, inpMtrReadInfo.getReadMtrCnt());
                    }
                    // add end 2019/09/17 QC#53261
                    setValue(copyParam.A.no(i).estFlg, FLG_ON_Y);
                    setValue(copyParam.A.no(i).svcPhysMtrReadGrpSq, estCntSvcPhysMtrReadGrpSq);
                }
                insertEstMtrRead(copyParam, svcPhysMtrTmsgArray);
                // START 2019/09/18 K.Kitachi [QC#53573, ADD]
                BigDecimal ttlMtrCnt = getTtlMtrCnt(copyParam, true);
                if (hasMessage()) {
                    return;
                }
                if (hasValue(ttlMtrCnt)) {
                    Map<BigDecimal, AddlLineInfoBean> addlBackAllocLineInfoMap = getAddlLineInfoMap(param, glblCmpyCd, dsContrDtlTmsgList, backAllocTargetDate);
                    if (hasErrMdlTtlOver(copyParam, ttlMtrCnt, addlBackAllocLineInfoMap, true)) {
                        return;
                    }
                }
                // END 2019/09/18 K.Kitachi [QC#53573, ADD]
            } else {
                estCntSvcPhysMtrReadGrpSq = backAllocTargetGrpSq;
            }
            // END 2017/09/01 K.Kasai [QC#15134,MOD]
            DS_CONTR_BLLG_SCHDTMsgArray estSchd = getEstTargetSchd(glblCmpyCd, dsContrDtlPk, backAllocTargetDate);
            // Mod Start 09/14/2016 <QC#14400>
            for (int i = 0; i < estSchd.getValidCount(); i++) {
                if (FLG_OFF_N.equals(estSchd.no(i).mtrEntryCpltFlg.getValue())) {
                    UpdateDsContrBllgSchd(param, estSchd.no(i), estCntSvcPhysMtrReadGrpSq, FLG_ON_Y);
                } else {
                    String orgReadDt = getMtrReadDtBySvcPhysMtrReadGrpSq(glblCmpyCd, estSchd.no(i).svcPhysMtrReadGrpSq.getValue());
                    if (orgReadDt != null && backAllocTargetDate.compareTo(orgReadDt) >= 0) {
                        UpdateDsContrBllgSchd(param, estSchd.no(i), estCntSvcPhysMtrReadGrpSq, FLG_ON_Y);
                    }
                }
            }
            // Mod End 09/141/2016 <QC#14400>
            // START 2018/07/12 K.Kitachi [QC#27022, ADD]
            updateFmlaMtrReadCntByGrpSq(param, glblCmpyCd, param.svcMachMstrPk.getValue(), estCntSvcPhysMtrReadGrpSq);
            // END 2018/07/12 K.Kitachi [QC#27022, ADD]
        }
    }

    // START 2017/09/01 K.Kasai [QC#15134,MOD]
    // private void insertEstMtrRead(NSZC010001PMsg param) {
    private void insertEstMtrRead(NSZC010001PMsg param, SVC_PHYS_MTRTMsgArray svcPhysMtrTmsgArray) {
        // END 2017/09/01 K.Kasai [QC#15134,MOD]
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = (NSZC010001_APMsg) param.A.no(i);

            SVC_PHYS_MTR_READTMsg svcPhysMtrRead = new SVC_PHYS_MTR_READTMsg();
            // START 2017/09/01 K.Kasai [QC#15134,ADD]
            // check Rollover Meter or not
            boolean isRollOverMtr = false;
            int cntrDigitNum = 0;
            // mod start 2018/06/20 QC#26700
            SVC_PHYS_MTR_READTMsg tMsg = getSvcPhysMtrReadByKey(param.glblCmpyCd.getValue(), line.svcPhysMtrReadPk_O.getValue());
            if (CNTR_RESET_TP.METER_ROLLOVER.equals(tMsg.cntrResetTpCd.getValue())) {
                // get meter limit digit num
                cntrDigitNum = getCntrDigitNum(svcPhysMtrTmsgArray, line.svcPhysMtrPk.getValue(), line.mtrLbCd.getValue());
                if (cntrDigitNum > 0) {
                    // get dsMtrReadTpCd(set line value(default:header))
                    String dsMtrReadTpCd = hasValue(line.dsMtrReadTpCd) ? line.dsMtrReadTpCd.getValue() : param.dsMtrReadTpCd.getValue();
                    // mod start 2020/03/03 QC#56123
                    //isRollOverMtr = isMtrRO(param, line, dsMtrReadTpCd);
                    isRollOverMtr = isMtrRO(param, line, dsMtrReadTpCd, true);
                    // mod end 2020/03/03 QC#56123
                }
            }
            //END 2017/09/01 K.Kasai [QC#15134,ADD]
            // mod end 2018/06/20 QC#26700
            setValue(svcPhysMtrRead.glblCmpyCd, param.glblCmpyCd);
            setValue(svcPhysMtrRead.svcPhysMtrReadPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_PHYS_MTR_READ_SQ));
            setValue(svcPhysMtrRead.svcMachMstrPk, param.svcMachMstrPk);
            setValue(svcPhysMtrRead.physMtrId, line.physMtrId);
            setValue(svcPhysMtrRead.mtrLbCd, line.mtrLbCd);
            setValue(svcPhysMtrRead.mtrClsTpCd, line.mtrClsTpCd);
            setValue(svcPhysMtrRead.mtrReadSrcTpCd, param.mtrReadSrcTpCd);
            setValue(svcPhysMtrRead.rgtnMtrDt, line.rgtnMtrDt);
            setValue(svcPhysMtrRead.readMtrCnt, line.readMtrCnt);
            setValue(svcPhysMtrRead.testMtrCnt, line.testMtrCnt);
            setValue(svcPhysMtrRead.mtrReadDt, line.mtrReadDt);
            if (!hasValue(line.invProcFlg)) {
                line.invProcFlg.setValue("N");
            }
            setValue(svcPhysMtrRead.invProcFlg, line.invProcFlg);
            // START 2017/09/01 K.Kasai [QC#15134,MOD]
            // setValue(svcPhysMtrRead.dsMtrReadTpCd,
            // param.dsMtrReadTpCd);
            // START 2018/04/12 K.Kojima [QC#23601,MOD]
            // if (hasValue(line.dsMtrReadTpCd)) {
            // setValue(svcPhysMtrRead.dsMtrReadTpCd,
            // line.dsMtrReadTpCd);
            // } else {
            // setValue(svcPhysMtrRead.dsMtrReadTpCd,
            // param.dsMtrReadTpCd);
            // }
            // START 2022/08/12 N.Takatsu [QC#60186, MOD]
            // setValue(svcPhysMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ALLOCATED);
            String dsMtrReadTpCd = param.dsMtrReadTpCd.getValue();
            if (hasValue(line.dsMtrReadTpCd)) {
                dsMtrReadTpCd = line.dsMtrReadTpCd.getValue();
            }
            if (DS_MTR_READ_TP.ESTIMATED.equals(dsMtrReadTpCd)) {
                setValue(svcPhysMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ESTIMATEDA);
            } else {
                setValue(svcPhysMtrRead.dsMtrReadTpCd, DS_MTR_READ_TP.ALLOCATED);
            }

            // END 2022/08/12 N.Takatsui [QC#60186, MOD]
            // END 2018/04/12 K.Kojima [QC#23601,MOD]
            // END 2017/09/01 K.Kasai [QC#15134,MOD]
            setValue(svcPhysMtrRead.fsrNum, param.fsrNum);
            setValue(svcPhysMtrRead.fsrVisitNum, param.fsrVisitNum);
            setValue(svcPhysMtrRead.rgtnUsrId, line.rgtnUsrId);
            if (!hasValue(line.estFlg)) {
                line.estFlg.setValue("N");
            }
            setValue(svcPhysMtrRead.estFlg, line.estFlg);
            setValue(svcPhysMtrRead.dsMdlMtrPk, line.dsMdlMtrPk);
            setValue(svcPhysMtrRead.svcPhysMtrReadGrpSq, line.svcPhysMtrReadGrpSq);
            setValue(svcPhysMtrRead.svcPhysMtrPk, line.svcPhysMtrPk);
            setValue(svcPhysMtrRead.mtrEntryCmntTxt, line.mtrEntryCmntTxt);
            setValue(svcPhysMtrRead.vldMtrFlg, line.vldMtrFlg);
            setValue(svcPhysMtrRead.dsMtrReadTpGrpCd, param.dsMtrReadTpGrpCd);
            if (FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
                setValue(svcPhysMtrRead.dsContrDtlPk, param.dsContrDtlPk);
            }

            // START 2016/06/06 [QC#9332, ADD]
            setValue(svcPhysMtrRead.rossBatProcStsCd, ROSS_BAT_PROC_STS.NOT_PROCESSED);
            setValue(svcPhysMtrRead.rossSendTrgtCd, ROSS_SEND_TRGT.NOT_APPLICABLE_BY_TRANSACTION_STATUS);
            // END 2016/06/06 [QC#9332, ADD]
            // START 2017/09/01 K.Kasai [QC#15134,ADD]
            if (isRollOverMtr) {
                setValue(svcPhysMtrRead.cntrResetTpCd, CNTR_RESET_TP.METER_ROLLOVER);
                // START 2017/09/27 K.Kasai [QC#21433,ADD]
                // clear if latest svcPhysMtrRead is not rollover.
                SVC_PHYS_MTR_READTMsg inpTMsg = getSvcPhysMtrRead(param.glblCmpyCd.getValue(), line.svcPhysMtrReadPk_O.getValue());
                inpTMsg.cntrResetTpCd.clear();
                S21ApiTBLAccessor.update(inpTMsg);
                // END 2017/09/27 K.Kasai [QC#21433,ADD]
            }
            // END 2017/09/01 K.Kasai [QC#15134,ADD]

            // START 2020/01/24 K.Kitachi [QC#55495, ADD]
            setValue(svcPhysMtrRead.carryOverTpCd, ZYPConstant.FLG_OFF_0);
            // START 2022/06/22 E.Sanchez [QC#59804, MOD]
            // boolean isCarryOver = isCarryOver(svcPhysMtrRead.glblCmpyCd.getValue(), svcPhysMtrRead.svcPhysMtrPk.getValue(),svcPhysMtrRead.mtrReadDt.getValue(), svcPhysMtrRead.dsMtrReadTpGrpCd.getValue());
            boolean isCarryOver = isCarryOver(svcPhysMtrRead.glblCmpyCd.getValue(), svcPhysMtrRead.svcPhysMtrPk.getValue(),svcPhysMtrRead.mtrReadDt.getValue(),
                                              svcPhysMtrRead.dsMtrReadTpGrpCd.getValue(), param.xxStartReadFlg.getValue());
            // END 2022/06/22 E.Sanchez [QC#59804, MOD]
            if (isCarryOver) {
                setValue(svcPhysMtrRead.carryOverTpCd, ZYPConstant.FLG_ON_1);
            }
            // END 2020/01/24 K.Kitachi [QC#55495, ADD]

            EZDTBLAccessor.insert(svcPhysMtrRead);
        }
    }

    // ADD-E 10/13/2015 K.Kishimoto

    /**
     * setInitReturnParam set initial parameter
     */
    private void setInitReturnParam() {

        NSZC010001PMsg param = (NSZC010001PMsg) msgMap.getPmsg();

        // Add Start 2017/09/19 QC#21185
        if (!hasValue(param.xxRqstFlg_WR)) {
            setValue(param.xxRqstFlg_WR, ZYPConstant.FLG_OFF_N);
        }
        // Add End 2017/09/19 QC#21185

        param.xxRsltStsCd.setValue(RETURN_CODE_NORMAL);
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = (NSZC010001_APMsg) param.A.no(i);
            if (line != null) {
                line.xxRsltStsCd.setValue(RETURN_CODE_NORMAL);
                if (!hasValue(line.readMtrCnt)) {
                    line.readMtrCnt.setValue(BigDecimal.ZERO);
                }
                if (!hasValue(line.testMtrCnt)) {
                    line.testMtrCnt.setValue(BigDecimal.ZERO);
                }
                if (!hasValue(line.estFlg)) {
                    line.estFlg.setValue("N");
                }
                if (!hasValue(line.invProcFlg)) {
                    line.invProcFlg.setValue("N");
                }
            }
        }
    }

    /**
     * isEmpty
     * @param List<Map<String, Object>> col
     * @return boolean not have list:true, has list:false
     */
    private static boolean isEmpty(List<Map<String, Object>> col) {
        return col == null || col.isEmpty();
    }

    /**
     * hasMessage
     * @return boolean Normal:false, Error:true
     */
    private boolean hasMessage() {
        // Add Start 2017/09/19 QC#21185
        NSZC010001PMsg param = (NSZC010001PMsg) msgMap.getPmsg();
        if (ZYPConstant.FLG_ON_Y.equals(param.xxRqstFlg_WR.getValue())) {
            this.msgMap.flush();
            String msgId = null;
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                msgId = param.xxMsgIdList.no(i).xxMsgId.getValue();
                if (hasValue(msgId) && msgId.substring(msgId.length() - 1).equals("E")) {
                    return true;
                }
            }
            return false;
        }
        // Add End 2017/09/19 QC#
        if (!this.msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    /**
     * setHeaderErrMsg
     * @param NSZC010001PMsg pMsg
     * @param String msgId
     */
    private void setHeaderErrMsg(NSZC010001PMsg pMsg, String msgId) {

        msgMap.addXxMsgId(msgId);
        // START 2019/09/05 K.Kitachi [QC#53247, ADD]
        if (hasValue(pMsg.xxMsgId) && hasValue(msgId)) {
            boolean befMsgWarn = false;
            if (pMsg.xxMsgId.getValue().substring(pMsg.xxMsgId.getValue().length() - 1).equals("W")) {
                befMsgWarn = true;
            }
            boolean aftMsgErr = false;
            if (msgId.substring(msgId.length() - 1).equals("E")) {
                aftMsgErr = true;
            }
            if (befMsgWarn && aftMsgErr) {
                ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
                ZYPEZDItemValueSetter.setValue(pMsg.xxDsMsgEntryTxt, EZDMessageEnv.getMessage(msgId));
            }
        }
        // END 2019/09/05 K.Kitachi [QC#53247, ADD]
        if (!hasValue(pMsg.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgId, msgId);
            ZYPEZDItemValueSetter.setValue(pMsg.xxDsMsgEntryTxt, EZDMessageEnv.getMessage(msgId));
        }
        ZYPEZDItemValueSetter.setValue(pMsg.xxRsltStsCd, RETURN_CODE_ERROR);
        msgMap.flush();
    }

    /**
     * setDetailErrMsg
     * @param NSZC010001PMsg pMsg
     * @param String msgId
     * @param NSZC010001_APMsg line
     */
    private void setDetailErrMsg(NSZC010001PMsg pMsg, NSZC010001_APMsg line, String msgId) {
        if (!hasValue(line.xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(line.xxMsgId, msgId);
            ZYPEZDItemValueSetter.setValue(line.xxDsMsgEntryTxt, EZDMessageEnv.getMessage(msgId));
        }
        ZYPEZDItemValueSetter.setValue(line.xxRsltStsCd, RETURN_CODE_ERROR);
        setHeaderErrMsg(pMsg, msgId);
    }

    /**
     * checkMandatory
     * @return boolean Normal:true, Error:false
     */
    private boolean checkMandatory() {

        NSZC010001PMsg param = (NSZC010001PMsg) msgMap.getPmsg();

        // #######################################
        // 5-1-1
        // #######################################
        if (!hasValue(param.glblCmpyCd)) {
            // Input parameter "Global Company Code" is a mandatory field.
            setHeaderErrMsg(param, NSZM0001E);
            return false;
        }

        if (!hasValue(param.mtrReadSrcTpCd)) {
            // Input parameter "Meter Reading Source Type Code" is a mandatory field.
            setHeaderErrMsg(param, NSZM0355E);
            return false;
        }

        if (!hasValue(param.dsMtrReadTpCd)) {
            // Input parameter "DS Meter Reading Type Code" is a mandatory field.
            setHeaderErrMsg(param, NSZM0356E);
            return false;
        }

        if (!hasValue(param.slsDt)) {
            // Input parameter "Sales Date" is a mandatory field.
            setHeaderErrMsg(param, NSZM0002E);
            return false;
        }

        if (!hasValue(param.svcMachMstrPk)) {
            // Input parameter "Service Machine Master PK" is a mandatory field.
            setHeaderErrMsg(param, NSZM0074E);
            return false;
        }

        // ADD-S 10/13/2015 K.Kishimoto
        if (!hasValue(param.dsMtrReadTpGrpCd)) {
            setHeaderErrMsg(param, NSZM0746E);
            return false;
        }

        if (hasValue(param.xxStartReadFlg) && FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
            if (!hasValue(param.dsContrDtlPk)) {
                // Input Parameter "DS contract Detail PK" is mandatory field if Start Meter Input.
                setHeaderErrMsg(param, NSZM0714E);
                return false;
            }
            if (!hasValue(param.contrEffFromDt)) {
                // Input Parameter "Contract Effective From Date" is mandatory field if Start Meter Input.
                setHeaderErrMsg(param, NSZM0715E);
                return false;
            }
        }
        // ADD-E 10/13/2015 K.Kishimoto

        if (param.A.getValidCount() == 0) {
            // Input parameter "Meter Information" is a mandatory field.
            setHeaderErrMsg(param, NSZM0357E);
            return false;
        }

        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = (NSZC010001_APMsg) param.A.no(i);
            if (line == null) {
                // Input parameter "Meter Information" is a mandatory field.
                setHeaderErrMsg(param, NSZM0357E);
                return false;
            }
            // MOD-S 10/13/2015 K.Kishimoto
            if (!hasValue(line.svcPhysMtrPk) && !hasValue(line.mdlMtrLbCd)) {
                setDetailErrMsg(param, line, NSZM0745E);
                return false;
            }
            // MOD-E 10/13/2015 K.Kishimoto
        }

        return true;
    }

    // START 2017/09/01 K.Kasai [QC#15134,MOD]
    /**
     * getMtrReadTp
     * @param glblCmpyCd String
     * @param dsMtrReadTpCd String
     * @return DS_MTR_READ_TPTMsg
     */
    private DS_MTR_READ_TPTMsg getMtrReadTp(String glblCmpyCd, String dsMtrReadTpCd) {

        DS_MTR_READ_TPTMsg mtrReadTpTmsg = new DS_MTR_READ_TPTMsg();
        setValue(mtrReadTpTmsg.glblCmpyCd, glblCmpyCd);
        setValue(mtrReadTpTmsg.dsMtrReadTpCd, dsMtrReadTpCd);
        mtrReadTpTmsg = (DS_MTR_READ_TPTMsg) S21CacheTBLAccessor.findByKey(mtrReadTpTmsg);
        return mtrReadTpTmsg;
    }

    // END 2017/09/01 K.Kasai [QC#15134,MOD]

    /**
     * getMtrReadSrcTp
     * @param param NSZC010001PMsg
     * @return MTR_READ_SRC_TPTMsg
     */
    private MTR_READ_SRC_TPTMsg getMtrReadSrcTp(NSZC010001PMsg param) {
        MTR_READ_SRC_TPTMsg mtrReadSrcTpTmsg = new MTR_READ_SRC_TPTMsg();
        setValue(mtrReadSrcTpTmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(mtrReadSrcTpTmsg.mtrReadSrcTpCd, param.mtrReadSrcTpCd.getValue());
        mtrReadSrcTpTmsg = (MTR_READ_SRC_TPTMsg) S21CacheTBLAccessor.findByKey(mtrReadSrcTpTmsg);
        return mtrReadSrcTpTmsg;
    }

    /**
     * getSvcMachMstr
     * @param param NSZC010001PMsg
     * @return SVC_MACH_MSTRTMsg
     */
    private SVC_MACH_MSTRTMsg getSvcMachMstr(NSZC010001PMsg param) {
        SVC_MACH_MSTRTMsg svcMachMstrTmsg = new SVC_MACH_MSTRTMsg();
        setValue(svcMachMstrTmsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(svcMachMstrTmsg.svcMachMstrPk, param.svcMachMstrPk.getValue());
        svcMachMstrTmsg = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(svcMachMstrTmsg);
        return svcMachMstrTmsg;
    }

    // START 2017/09/01 K.Kasai [QC#15134,MOD]
    /**
     * getSvcPhysMtrRead
     * @param NSZC010001PMsg parameter
     * @param NSZC010001_APMsg line
     * @return SVC_PHYS_MTR_READTMsg
     */
    private SVC_PHYS_MTR_READTMsg getSvcPhysMtrRead(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        SVC_PHYS_MTR_READTMsg svcPhysMtrRead = new SVC_PHYS_MTR_READTMsg();
        setValue(svcPhysMtrRead.glblCmpyCd, glblCmpyCd);
        setValue(svcPhysMtrRead.svcPhysMtrReadPk, svcPhysMtrReadPk);
        svcPhysMtrRead = (SVC_PHYS_MTR_READTMsg) S21FastTBLAccessor.findByKeyForUpdate(svcPhysMtrRead);
        return svcPhysMtrRead;
    }
    // END 2017/09/01 K.Kasai [QC#15134,MOD]

    // add start 2018/06/20 QC#26700
    /**
     * getSvcPhysMtrReadByKey
     * @param NSZC010001PMsg parameter
     * @param NSZC010001_APMsg line
     * @return SVC_PHYS_MTR_READTMsg
     */
    private SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadByKey(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        SVC_PHYS_MTR_READTMsg svcPhysMtrRead = new SVC_PHYS_MTR_READTMsg();
        setValue(svcPhysMtrRead.glblCmpyCd, glblCmpyCd);
        setValue(svcPhysMtrRead.svcPhysMtrReadPk, svcPhysMtrReadPk);
        svcPhysMtrRead = (SVC_PHYS_MTR_READTMsg) S21FastTBLAccessor.findByKey(svcPhysMtrRead);
        return svcPhysMtrRead;
    }
    // add end 2018/06/20 QC#26700

    // /**
    // * checkMeterDate
    // *
    // * Next Month Check
    // * YYYYMM of rgtnMtrDt > YYYYMM of slsDt is false
    // *
    // * @param String slsDt
    // * @param String rgtnMtrDt
    // * @return boolean
    // */
    // private boolean checkMeterDate(String slsDt, String rgtnMtrDt)
    // {
    //        
    // int slsDtMm = 0;
    // try {
    // slsDtMm = Integer.parseInt(slsDt.substring(0, 6));
    // } catch (NumberFormatException e) {}
    //        
    // int rgtnMtrDtPlusOneMm = 0;
    // try {
    // rgtnMtrDtPlusOneMm = Integer.parseInt(rgtnMtrDt.substring(0,
    // 6));
    // } catch (NumberFormatException e) {}
    //        
    // if (rgtnMtrDtPlusOneMm > slsDtMm) {
    // return false;
    // }
    // return true;
    // }

    /**
     * getMeterDate
     * @param param NSZC010001PMsg
     * @param NSZC010001_APMsg line
     * @return String
     */
    private String getMeterDate(NSZC010001PMsg param, NSZC010001_APMsg line) {

        String mtrDt = "";
        int slsDtMm = 0;
        try {
            slsDtMm = Integer.parseInt(param.slsDt.getValue().substring(0, 6));
        } catch (NumberFormatException e) {
        }

        String slsDtPlusOne = addMonths(param.slsDt.getValue(), 1);
        int slsDtPlusOneMm = 0;
        try {
            slsDtPlusOneMm = Integer.parseInt(slsDtPlusOne.substring(0, 6));
        } catch (NumberFormatException e) {
        }

        List<Map<String, Object>> dsContrList = getDsContrDtl(param, line);
        if (!isEmpty(dsContrList)) {
            for (int j = 0; j < dsContrList.size(); j++) {
                Map<String, Object> map = (Map<String, Object>) dsContrList.get(j);
                String dt = "";
                if (map != null) {
                    dt = map.get("MTR_BLLG_NEXT_BLLG_DT") == null ? "" : (String) map.get("MTR_BLLG_NEXT_BLLG_DT");
                }
                int dtMm = 0;
                if (dt.length() >= 6) {
                    try {
                        dtMm = Integer.parseInt(dt.substring(0, 6));
                    } catch (NumberFormatException e) {
                    }

                }
                if (dtMm != 0) {
                    // If Parameter Sales Data is same month, Previous
                    // End of date of Meter Date.
                    if (slsDtMm == dtMm) {
                        mtrDt = getLastDateOfMonth(addMonths(param.slsDt.getValue(), -1));
                        // If Parameter Sales Data is next month, End
                        // of date of Meter Date.
                    } else if (slsDtPlusOneMm >= dtMm) {
                        mtrDt = getLastDateOfMonth(param.slsDt.getValue());
                    }
                }
            }
        }
        if ("".equals(mtrDt)) {
            mtrDt = getLastDateOfMonth(param.slsDt.getValue());
        }
        return mtrDt;
    }

    /**
     * getDsContrDtl
     * @param NSZC010001PMsg pMsg
     * @param NSZC010001_APMsg line
     * @return List<Map<String, Object>>
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsContrDtl(final NSZC010001PMsg pMsg, final NSZC010001_APMsg line) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("machMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("mtrReadDt", line.mtrReadDt.getValue());

        Set<String> dsContrDtlStsCd = new LinkedHashSet<String>();
        dsContrDtlStsCd.add(DS_CONTR_DTL_STS.ACTIVE);
        dsContrDtlStsCd.add(DS_CONTR_DTL_STS.WAITING_FOR_FINAL_BILL);
        param.put("dsContrDtlStsCd", dsContrDtlStsCd);

        return ssmClient.queryObjectList("getDsContrDtl", param);
    }

    /**
     * checkMeterCount
     * @param NSZC010001PMsg pMsg
     * @param NSZC010001_APMsg line
     * @param cntrDigitNum int
     * @param isRollOverMtrCurr boolean
     * @return boolean
     */
    // START 2017/09/01 K.Kasai [QC#15134,MOD]
    // mod start 2020/03/03 QC#56123
    //private void checkMeterCount(final NSZC010001PMsg pMsg, final NSZC010001_APMsg line, int cntrDigitNum) {
    private void checkMeterCount(final NSZC010001PMsg pMsg, final NSZC010001_APMsg line, int cntrDigitNum, boolean isRollOverMtrCurr) {
    // mod end 2020/03/03 QC#56123
        // END 2017/09/01 K.Kasai [QC#15134,MOD]

        if (hasValue(line.svcPhysMtrReadPk)) {
            return;
        }
        // mod start 2016/03/17 CSA Defect#4403
        // START 2017/09/01 K.Kasai [QC#15134,MOD]
        // DS_MTR_READ_TPTMsg mtrReadTpTmsg = getMtrReadTp(pMsg);
        String dsMtrReadTpCd = hasValue(line.dsMtrReadTpCd) ? line.dsMtrReadTpCd.getValue() : pMsg.dsMtrReadTpCd.getValue();
        DS_MTR_READ_TPTMsg mtrReadTpTmsg = null;
        mtrReadTpTmsg = getMtrReadTp(pMsg.glblCmpyCd.getValue(), dsMtrReadTpCd);
        BigDecimal inpReadMtrCnt = line.readMtrCnt.getValue() == null ? BigDecimal.ZERO : line.readMtrCnt.getValue();
        // check meter count whether over the max digit num or not.
        if (cntrDigitNum > 0) {
            if (inpReadMtrCnt.compareTo(getMaxMtrCnt(cntrDigitNum)) >= 0) {
                // QC#57260 Mod Start
                if (isVldCounter(line)) {
                    // create new message
                    setDetailErrMsg(pMsg, line, NSZM1295E);
                    return;
                }
                // QC#57260 Mod End
            }
        }
        // END 2017/09/01 K.Kasai [QC#15134,MOD]
        // del start 2020/03/03 QC#56123
//        if (mtrReadTpTmsg.adjMtrReadTpFlg.getValue().equals(ZYPConstant.FLG_OFF_N)) {
            // mod end 2016/03/17 CSA Defect#4403

            // START 2017/09/01 K.Kasai [QC#15134,DEL]
            // BigDecimal inpReadMtrCnt = line.readMtrCnt.getValue()
            // == null ? BigDecimal.ZERO : line.readMtrCnt.getValue();
            // END 2017/09/01 K.Kasai [QC#15134,DEL]

            // START 2017/09/01 K.Kasai [QC#15134,MOD]
            // Map<String, Object> rsltMap = getLastMtrRead(pMsg, line);
            // mod start 2018/06/04 QC#26052
            //Map<String, Object> rsltMap = getLastMtrRead(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), null);
//            Map<String, Object> rsltMap = getLastMtrRead(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), null, pMsg.dsMtrReadTpGrpCd.getValue());
            // mod end 2018/06/04 QC#26052
            // END 2017/09/01 K.Kasai [QC#15134,MOD]
            // START 2018/06/05 K.Kitachi [QC#26048, MOD]
//            //START 2017/09/01 K.Kasai [QC#15134,ADD]
//            if (cntrDigitNum == 0) {
//                if (rsltMap != null && !rsltMap.isEmpty()) {
//                    BigDecimal lastReadMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
//                    if (inpReadMtrCnt.compareTo(lastReadMtrCnt) < 0) {
//                        setDetailErrMsg(pMsg, line, NSZM0541E);
//                        return;
//                    }
//                }
//            }
//            //END 2017/09/01 K.Kasai [QC#15134,ADD]
//            if (rsltMap != null && !rsltMap.isEmpty()) {
//                BigDecimal lastReadMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
//                if (inpReadMtrCnt.compareTo(lastReadMtrCnt) < 0) {
//                    if (cntrDigitNum == 0) {
//                        setDetailErrMsg(pMsg, line, NSZM0541E);
//                        return;
//                    }
//                    if (cntrDigitNum > 0) {
//                        if (!FLG_ON_Y.equals(pMsg.xxWrnSkipFlg.getValue())) {
//                            setDetailErrMsg(pMsg, line, NSZM1335W);
//                            return;
//                        }
//                    }
//                }
//            }
            // END 2018/06/05 K.Kitachi [QC#26048, MOD]
//        }
        // del end 2020/03/03 QC#56123
        // START 2017/09/01 K.Kasai [QC#15134,ADD]
        // mod start 2020/03/03 QC#56123
        //if (DS_MTR_READ_TP.ROLLOVER.equals(dsMtrReadTpCd)) {
        if (isRollOverMtrCurr) {
        // mod end 2020/03/03 QC#56123
            // check rollover target meter is enable to be rollover.
            if (cntrDigitNum == 0) {
                // QC#57260 Mod Start
                if (isVldCounter(line)) {
                    setDetailErrMsg(pMsg, line, NSZM1296E);
                    return;
                }
             // QC#57260 Mod End
            }
        }
        // END 2017/09/01 K.Kasai [QC#15134,ADD]

        // add start 2020/03/03 QC#56123
        // Higher/Lower Read check
        // START 2024/02/15 K.Watanabe [QC#63529,MOD]
        // if (FLG_ON_Y.equals(pMsg.xxStartReadFlg.getValue()) || ((DS_MTR_READ_TP_GRP.SERVICE_READS.equals(pMsg.dsMtrReadTpGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue())))) {
        if ((FLG_ON_Y.equals(pMsg.xxStartReadFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue()))
                || ((DS_MTR_READ_TP_GRP.SERVICE_READS.equals(pMsg.dsMtrReadTpGrpCd.getValue()) && ZYPConstant.FLG_OFF_N.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue())))) {
        // END   2024/02/15 K.Watanabe [QC#63529,MOD]

            List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
            if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(pMsg.dsMtrReadTpGrpCd.getValue())) {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
            } else {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
            }

            SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoByMtrReadTpGrp(pMsg.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList);
            if (bean != null) {
                // not rollover: prev > current -> error
                if (!isRollOverMtrCurr && bean.getReadMtrCnt().compareTo(inpReadMtrCnt) > 0) {
                    // QC#57260 Mod Start
                    if (isVldCounter(line)) {
                        setDetailErrMsg(pMsg, line, NSZM0541E);
                        return;
                    }
                    // QC#57260 Mod End
                }
                // rollover: prev < current -> error
                if (isRollOverMtrCurr && bean.getReadMtrCnt().compareTo(inpReadMtrCnt) < 0) {
                    // QC#57260 Mod Start
                    if (isVldCounter(line)) {
                        setDetailErrMsg(pMsg, line, NSZM1370E);
                        return;
                    }
                    // QC#57260 Mod End
                }
            }
            // next < current -> error
            bean = NSXC003001SvcPhysMtrRead.getNextMtrReadInfoByMtrReadTpGrp(pMsg.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList);
            if (bean != null) {
                if (bean.getReadMtrCnt().compareTo(inpReadMtrCnt) < 0) {
                    // QC#57260 Mod Start
                    if (isVldCounter(line)) {
                        setDetailErrMsg(pMsg, line, NSZM1312E);
                        return;
                    }
                    // QC#57260 Mod End
                }
            }
        }

        // Higher/Lower Read for Cross check
        if (!DS_MTR_READ_TP.CUSTOMER_CARE.equals(dsMtrReadTpCd) && !FLG_ON_Y.equals(pMsg.xxWrnSkipFlg.getValue())) {
            if (ZYPConstant.FLG_OFF_N.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue()) && !isRollOverMtrCurr) {
                // prev > current -> warning
                SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoByMtrReadTpGrp(pMsg.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), null);
                if (bean != null) {
                    if (bean.getReadMtrCnt().compareTo(inpReadMtrCnt) > 0) {
                        // QC#57260 Mod Start
                        if (isVldCounter(line)) {
                            setDetailErrMsg(pMsg, line, NSZM1371W);
                            return;
                        }
                        // QC#57260 Mod End
                    }
                }
            }
            // next < current -> warning
            SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getNextMtrReadInfoByMtrReadTpGrp(pMsg.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), null);
            if (bean != null) {
                if (bean.getReadMtrCnt().compareTo(inpReadMtrCnt) < 0) {
                    // QC#57260 Mod Start
                    if (isVldCounter(line)) {
                        setDetailErrMsg(pMsg, line, NSZM1372W);
                        return;
                    }
                    // QC#57260 Mod End
                }
            }
        }
       // add end 2020/03/03 QC#56123
    }

    /**
     * Get last meter reading information
     * @param NSZC010001PMsg pMsg
     * @param NSZC010001_APMsg line
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    // START 2017/09/01 K.Kasai [QC#15134,MOD]
    // private Map<String, Object> getLastMtrRead(final NSZC010001PMsg pMsg, final NSZC010001_APMsg line) {
    // mod start 2018/06/04 QC#26052
    //private Map<String, Object> getLastMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal svcPhysMtrReadPk) {
    private Map<String, Object> getLastMtrRead(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal svcPhysMtrReadPk, String dsMtrReadTpGrpCd) {
    // mod end 2018/06/04 QC#26052
        // END 2017/09/01 K.Kasai [QC#15134,MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        // START 2017/09/01 K.Kasai [QC#15134,MOD]
        // param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        // param.put("svcPhysMtrPk", line.svcPhysMtrPk.getValue());
        // param.put("mtrReadDt", line.mtrReadDt.getValue());
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("svcPhysMtrPk", svcPhysMtrPk);
        param.put("mtrReadDt", mtrReadDt);
        param.put("svcPhysMtrReadPk", svcPhysMtrReadPk);
        // add start 2018/06/04 QC#26052
        // mod start 2019/11/22 QC#54799
        //param.put("dsMtrReadTpGrpCd", dsMtrReadTpGrpCd);
        //param.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        if (!DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd) && !DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(dsMtrReadTpGrpCd)) {
            param.put("dsMtrReadTpGrpCd", dsMtrReadTpGrpCd);
            param.put("exchange", CNTR_RESET_TP.METER_EXCHANGE);
        }
        // mod end 2019/11/22 QC#54799
        // add end 2018/06/04 QC#26052
        // add start 2018/06/29 QC#26795
        // del start 2019/11/22 QC#54799
        //if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(dsMtrReadTpGrpCd)) {
        //    param.put("addDsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
        //} else if (DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(dsMtrReadTpGrpCd)) {
        //    param.put("addDsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.SERVICE_READS);
        //}
        // del end 2019/11/22 QC#54799
        // add end 2018/06/29 QC#26795
        // END 2017/09/01 K.Kasai [QC#15134,MOD]
        // MOD-S 10/13/2015 K.Kishimoto
        // del start 2016/03/17 CSA Defect#4403
        // param.put("dsMtrReadGrpCd",
        // pMsg.dsMtrReadTpGrpCd.getValue());
        // del end 2016/03/17 CSA Defect#4403
        // Set<String> dsMtrReadTpSet1 = new LinkedHashSet<String>();
        // dsMtrReadTpSet1.add(DS_MTR_READ_TP.INITIAL_METER_READING);
        // dsMtrReadTpSet1.add(DS_MTR_READ_TP.PERIODIC_METER_READING);
        // dsMtrReadTpSet1.add(DS_MTR_READ_TP.FINAL_METER_READING);
        // dsMtrReadTpSet1.add(DS_MTR_READ_TP.CORRECTION);
        // dsMtrReadTpSet1.add(DS_MTR_READ_TP.EXCHANGE_METER_TO);
        // param.put("dsMtrReadTpSet1", dsMtrReadTpSet1);
        // MOD-S 10/13/2015 K.Kishimoto
        return (Map<String, Object>) ssmClient.queryObject("getLastMtrRead", param);
    }

    /**
     * checkInvoicedMeter
     * @param NSZC010001PMsg pMsg
     * @param NSZC010001_APMsg line
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean checkInvoicedMeter(NSZC010001PMsg pMsg, NSZC010001_APMsg line) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("physMtrId", line.physMtrId.getValue());
        param.put("invProcFlg", FLG_ON_Y);
        param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("dsMtrReadTpCd", pMsg.dsMtrReadTpCd.getValue());
        param.put("mtrReadDt", line.mtrReadDt.getValue());

        Map<String, Object> m1 = (Map<String, Object>) ssmClient.queryObject("checkInvoicedMeter", param);
        if (m1 != null && m1.get("SVC_PHYS_MTR_READ_PK") != null) {
            return false;
        }
        return true;
    }

    /**
     * checkMeterExists
     * @param NSZC010001PMsg pMsg
     * @param NSZC010001_APMsg line
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean checkMeterExists(NSZC010001PMsg pMsg, NSZC010001_APMsg line) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("svcPhysMtrPk", line.svcPhysMtrPk.getValue());

        Map<String, Object> m1 = (Map<String, Object>) ssmClient.queryObject("checkMeterExists", param);
        if (m1 == null || m1.get("SVC_MACH_MSTR_PK") == null) {
            return false;
        } else {
            // MOD-S 10/13/2015 K.Kishimoto
            // String mtrLbCd = m1.get("MDL_MTR_LB_CD") == null ? "" :
            // (String) m1.get("MDL_MTR_LB_CD");
            // String mtrClsTpCd = m1.get("MTR_CLS_TP_CD") == null ?
            // "" : (String) m1.get("MTR_CLS_TP_CD");
            // line.mtrLbCd.setValue(mtrLbCd);
            // line.mtrClsTpCd.setValue(mtrClsTpCd);
            String mtrLbCd = m1.get("MDL_MTR_LB_CD") == null ? "" : (String) m1.get("MDL_MTR_LB_CD");
            line.mtrLbCd.setValue(mtrLbCd);
            // MOD-E 10/13/2015 K.Kishimoto
            if (m1.get("DS_MDL_MTR_PK") != null) {
                line.dsMdlMtrPk.setValue((BigDecimal) m1.get("DS_MDL_MTR_PK"));
            }
        }
        return true;
    }

    /**
     * addMonths
     * @param String date
     * @param int numberOfMonths
     * @return String
     */
    public static String addMonths(String date, int numberOfMonths) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, numberOfMonths);
        return format.format(cal.getTime());
    }

    /**
     * get the last date(YYYYMMDD) of the month of the specified date.
     * @param date YYYMMDD
     * @return the last date(YYYYMMDD) of the month
     */
    public static String getLastDateOfMonth(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return format.format(cal.getTime());
    }

    // ADD-S 10/13/2015 K.Kishimoto
    private SVC_PHYS_MTRTMsgArray getSvcPhysMtrTmsgArray(final NSZC010001PMsg pMsg) {
        SVC_PHYS_MTRTMsg inMsg = new SVC_PHYS_MTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", pMsg.svcMachMstrPk.getValue());
        SVC_PHYS_MTRTMsgArray outArray = (SVC_PHYS_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return outArray;
    }

    private Map<BigDecimal, AddlLineInfoBean> getAddlLineInfoMap(NSZC010001PMsg param, String glblCmpyCd, List<DS_CONTR_DTLTMsg> dsContrDtlTmsgList, String mtrReadDt) {
        Map<BigDecimal, AddlLineInfoBean> outMap = new HashMap<BigDecimal, AddlLineInfoBean>();
        Map<BigDecimal, CONTR_PHYS_BLLG_MTR_RELNTMsg> relnMap = new HashMap<BigDecimal, CONTR_PHYS_BLLG_MTR_RELNTMsg>();
        for (DS_CONTR_DTLTMsg dsContrDtlTmsg : dsContrDtlTmsgList) {
            CONTR_PHYS_BLLG_MTR_RELNTMsg relnMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            relnMsg.setSQLID("001");
            relnMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            relnMsg.setConditionValue("dsContrDtlPk01", dsContrDtlTmsg.dsContrDtlPk.getValue());
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray outArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(relnMsg);
            for (int i = 0; i < outArray.getValidCount(); i++) {
                if (FLG_ON_Y.equals(outArray.no(i).bllblFlg.getValue())) {
                    relnMap.put(outArray.no(i).svcPhysMtrPk.getValue(), outArray.no(i));
                }
            }
        }
        Map<BigDecimal, BigDecimal> startMtrGrpSqMap = new HashMap<BigDecimal, BigDecimal>();
        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = param.A.no(i);
            BigDecimal svcPhysMtrPk = line.svcPhysMtrPk.getValue();
            BigDecimal dsContrBllgMtrPk = null;
            CONTR_PHYS_BLLG_MTR_RELNTMsg contrPhysBllgMtrRelnTMsg = relnMap.get(svcPhysMtrPk);
            if (contrPhysBllgMtrRelnTMsg == null) {
                continue;
            }
            dsContrBllgMtrPk = contrPhysBllgMtrRelnTMsg.dsContrBllgMtrPk.getValue();
            AddlLineInfoBean addlLineInfo = new AddlLineInfoBean();
            BigDecimal dsContrDtlPk = contrPhysBllgMtrRelnTMsg.dsContrDtlPk.getValue();
            addlLineInfo.setDsContrBllgMtrPk(contrPhysBllgMtrRelnTMsg.dsContrBllgMtrPk.getValue());
            addlLineInfo.setDsContrDtlPk(dsContrDtlPk);
            BigDecimal startMtrGrpSq = null;
            if (startMtrGrpSqMap.containsKey(dsContrDtlPk)) {
                startMtrGrpSq = startMtrGrpSqMap.get(dsContrDtlPk);
            } else {
                startMtrGrpSq =NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk);
                startMtrGrpSqMap.put(dsContrDtlPk, startMtrGrpSq);
            }
            if (!FLG_ON_Y.equals(param.xxStartReadFlg.getValue()) && startMtrGrpSq == null) {
                setHeaderErrMsg(param, NSZM0749E);
                return null;
            }
            SVC_PHYS_MTR_READTMsg startPhysMtrReadTMsg = NSXC003001SvcPhysMtrRead.getSvcPhysMtrReadBysvcPhysMtrReadGrpSq(glblCmpyCd, svcPhysMtrPk, startMtrGrpSq);
            addlLineInfo.setStartMeterSvcPhysMtrTMsg(startPhysMtrReadTMsg);
            addlLineInfo.setIsBllgRead(true);
            addlLineInfo.setMtrWinFromDt(null);
            addlLineInfo.setMtrWinThruDt(null);
            addlLineInfo.setLastBillingMeterSvcPhysMtrReadGrpSq(null);
            // START 2017/09/19 K.Kojima [QC#20869,ADD]
            addlLineInfo.setLastStagingMeterSvcPhysMtrReadGrpSq(null);
            // END 2017/09/19 K.Kojima [QC#20869,ADD]
            outMap.put(svcPhysMtrPk, addlLineInfo);
            if (!DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) || hasValue(line.svcPhysMtrReadPk)) {
                addlLineInfo.setIsBllgRead(false);
            }
            // Add Start 09/14/2016 <QC#14400>
            if (DS_MTR_READ_TP_GRP.BILLABLE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) && dsContrBllgMtrPk != null && !hasValue(line.svcPhysMtrReadPk) && !FLG_ON_Y.equals(param.xxStartReadFlg.getValue())) {
                if (startPhysMtrReadTMsg != null) {
                    addlLineInfo.setLastBillingMeterSvcPhysMtrReadGrpSq(NSXC003001SvcPhysMtrRead.getLastBillingMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, startPhysMtrReadTMsg.mtrReadDt.getValue(), mtrReadDt));
                    // START 2017/09/19 K.Kojima [QC#20869,ADD]
                    addlLineInfo.setLastStagingMeterSvcPhysMtrReadGrpSq(NSXC003001SvcPhysMtrRead.getLastStagingMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, startPhysMtrReadTMsg.mtrReadDt.getValue(), mtrReadDt));
                    // END 2017/09/19 K.Kojima [QC#20869,ADD]
                }
            }
            // Add End 09/14/2016 <QC#14400>
            DS_CONTR_BLLG_SCHDTMsgArray schdArray = getDsContrBllgSchdTmsgArray(glblCmpyCd, dsContrDtlPk, addlLineInfo.getDsContrBllgMtrPk(), mtrReadDt);
            DS_CONTR_BLLG_SCHDTMsg schdTmsg = null;
            if (schdArray != null && schdArray.getValidCount() != 0) {
                schdTmsg = schdArray.no(0);
                addlLineInfo.setDsContrBllgSchdTMsg(schdTmsg);
            } else {
                addlLineInfo.setDsContrBllgSchdTMsg(null);
            }
            if (schdTmsg != null) {
                MtrWinInfo mtrWinInfo = new MtrWinInfo();
                mtrWinInfo.setGlblCmpyCd(glblCmpyCd);
                mtrWinInfo.setBaseDt(mtrReadDt);
                mtrWinInfo.setBllgFromDt(schdTmsg.bllgSchdFromDt.getValue());
                mtrWinInfo.setBllgThruDt(schdTmsg.bllgSchdThruDt.getValue());
                // add start 2017/08/08 QC#18799
                mtrWinInfo.setBaseDt(param.slsDt.getValue());
                mtrWinInfo.setDsContrBllgSchdPk(schdTmsg.dsContrBllgSchdPk.getValue());
                // add end 2017/08/08 QC#18799
                NSXC001001GetMtrWinFromThruDt.getMtrWinByDate(mtrWinInfo);
                String fromDt = mtrWinInfo.getMtrWinFromDt();
                String thruDt = mtrWinInfo.getMtrWinThruDt();
                addlLineInfo.setMtrWinFromDt(fromDt);
                addlLineInfo.setMtrWinThruDt(thruDt);
                if (hasValue(mtrWinInfo.getMtrWinFromDt()) && hasValue(mtrWinInfo.getMtrWinThruDt())) {
                    if (mtrReadDt.compareTo(mtrWinInfo.getMtrWinFromDt()) < 0) {
                        addlLineInfo.setIsBllgRead(false);
                    } else if (mtrReadDt.compareTo(mtrWinInfo.getMtrWinThruDt()) > 0) {
                        addlLineInfo.setIsBllgRead(false);
                    }
                }
            } else {
                addlLineInfo.setIsBllgRead(false);
                addlLineInfo.setMtrWinFromDt(null);
                addlLineInfo.setMtrWinThruDt(null);
            }
        }
        return outMap;
    }

    private DS_CONTR_BLLG_SCHDTMsgArray getDsContrBllgSchdTmsgArray(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String dt) {
        if (!hasValue(dsContrBllgMtrPk)) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("008");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setConditionValue("bllgSchdFromDt01", dt);
        inMsg.setConditionValue("bllgSchdThruDt01", dt);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return outArray;
    }

    private DS_CONTR_BLLG_SCHDTMsgArray getBackAllocTargetSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String fromDt, String thruDt) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setConditionValue("bllgSchdFromDt01", fromDt);
        inMsg.setConditionValue("bllgSchdFromDt02", thruDt);
        inMsg.setConditionValue("mtrEntryCpltFlg01", FLG_OFF_N);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return outArray;
    }

    private DS_CONTR_BLLG_SCHDTMsgArray getEstTargetSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgSchdThruDt01) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("009");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("bllgSchdThruDt01", bllgSchdThruDt01);
        inMsg.setConditionValue("usgChrgFlg01", FLG_ON_Y);
        inMsg.setConditionValue("invFlg01", FLG_OFF_N);
        inMsg.setConditionValue("invTpCd01", INV_TP.INVOICE);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return outArray;
    }

    private DS_CONTR_BLLG_SCHDTMsgArray getLastMtrEntrySchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String fromDt, String thruDt) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setConditionValue("bllgSchdFromDt01", fromDt);
        inMsg.setConditionValue("bllgSchdFromDt02", thruDt);
        inMsg.setConditionValue("mtrEntryCpltFlg01", FLG_ON_Y);
        DS_CONTR_BLLG_SCHDTMsgArray outArray = (DS_CONTR_BLLG_SCHDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return outArray;
    }

    private DS_MTR_READ_TP_GRPTMsg getDsMtrReadTpGrp(NSZC010001PMsg param) {
        DS_MTR_READ_TP_GRPTMsg inMsg = new DS_MTR_READ_TP_GRPTMsg();
        setValue(inMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(inMsg.dsMtrReadTpGrpCd, param.dsMtrReadTpGrpCd.getValue());
        inMsg = (DS_MTR_READ_TP_GRPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    private MTR_LBTMsg getMtrLbTmsg(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mtrLbCd, mtrLbCd);
        inMsg = (MTR_LBTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    // START 2018/05/08 K.Kojima [QC#24817,DEL]
    // private MTR_GRPTMsg getMtrGrp(NSZC010001PMsg param, BigDecimal
    // mtrGrpPk) {
    // MTR_GRPTMsg inMsg = new MTR_GRPTMsg();
    // setValue(inMsg.glblCmpyCd, param.glblCmpyCd.getValue());
    // setValue(inMsg.mtrGrpPk, mtrGrpPk);
    // inMsg = (MTR_GRPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    // return inMsg;
    // }
    // END 2018/05/08 K.Kojima [QC#24817,DEL]

    private void clearStartMtr(NSZC010001PMsg param, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        // START 2022/06/22 E.Sanchez [QC#59804, ADD]
        boolean isMeterUsedForBilling = false;
        List<BigDecimal> rslt = getBllgMtrSchdBySvcPhysMrReadGrpSq(param.glblCmpyCd.getValue(), svcPhysMtrReadGrpSq);
        if (rslt != null && !rslt.isEmpty()) {
            isMeterUsedForBilling = true;
        }
        // END 2022/06/22 E.Sanchez [QC#59804, ADD]
        for (int i = 0; i < outArray.getValidCount(); i++) {
            setValue(outArray.no(i).dsContrDtlPk, (BigDecimal) null);
            // START 2022/06/22 E.Sanchez [QC#59804, MOD]
            // Add Start 2017/08/28 QC#20537
            if (!isMeterUsedForBilling) {
                setValue(outArray.no(i).vldMtrFlg, ZYPConstant.FLG_OFF_N);                
            }
            // Add End 2017/08/28 QC#20537
            // END 2022/06/22 E.Sanchez [QC#59804, MOD]
            S21ApiTBLAccessor.update(outArray.no(i));
        }
    }

    private DS_CONTR_DTLTMsg getDsContrDtlByPk(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        inMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    private List<String> getBackAllocTargetDate(String glblCmpyCd, BigDecimal dsContrDtlPk, String fromDt, String thruDt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("dsContrDtlPk", dsContrDtlPk);
        prm.put("fromDt", fromDt);
        prm.put("thruDt", thruDt);
        // START 2023/02/03 E.Sanchez [QC#61138, ADD]
        prm.put("skipRecovTpCd", SKIP_RECOV_TP.SKIP);
        // END 2023/02/03 E.Sanchez [QC#61138, ADD]
        @SuppressWarnings("unchecked")
        List<String> m1 = (List<String>) ssmClient.queryObjectList("getBackAllocTargetDate", prm);
        return m1;
    }

    private BigDecimal getlastBllgMtrGrpSqByBllgMtrPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String fromDt, String thruDt) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("dsContrDtlPk", dsContrDtlPk);
        prm.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        prm.put("fromDt", fromDt);
        prm.put("thruDt", thruDt);
        // START 2022/03/24 R.Jabal [QC#59779, ADD]
        prm.put("vldMtrFlg", FLG_ON_Y);
        // END 2022/03/24 R.Jabal [QC#59779, ADD]
        @SuppressWarnings("unchecked")
        BigDecimal m1 = (BigDecimal) ssmClient.queryObject("getlastBllgMtrGrpSqByBllgMtrPk", prm);
        return m1;
    }

    private String getMtrReadDtBySvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        @SuppressWarnings("unchecked")
        String m1 = (String) ssmClient.queryObject("getMtrReadDtBySvcPhysMtrReadGrpSq", prm);
        return m1;
    }

    // Mod Start 10/03/2016 <QC#15854>
    private void UpdateDsContrBllgSchd(NSZC010001PMsg param, DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTmsg, BigDecimal svcPhysMtrReadGrpSq, String mtrEntryCpltFlg) {
        setValue(dsContrBllgSchdTmsg.svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
        setValue(dsContrBllgSchdTmsg.mtrEntryCpltFlg, mtrEntryCpltFlg);
        String glblCmpyCd = dsContrBllgSchdTmsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = dsContrBllgSchdTmsg.dsContrDtlPk.getValue();
        Map<String, Object> contrInfo = getContrInfoFromDtl(glblCmpyCd, dsContrDtlPk);
        if (contrInfo == null) {
            return;
        }
        String dsContrCatgCd = (String) contrInfo.get("DS_CONTR_CATG_CD");
        BigDecimal dsContrBllgSchdPk = dsContrBllgSchdTmsg.dsContrBllgSchdPk.getValue();
        BigDecimal prntDsContrBllgSchdPk = dsContrBllgSchdTmsg.prntDsContrBllgSchdPk.getValue();
        S21ApiTBLAccessor.update(dsContrBllgSchdTmsg);
        // START 2017/08/24 K.Kitachi [QC#20746, DEL]
        // NSZC047001 api = new NSZC047001();
        // NSZC047022PMsg pMsg = new NSZC047022PMsg();
        // setValue(pMsg.glblCmpyCd, dsContrBllgSchdTmsg.glblCmpyCd);
        // setValue(pMsg.slsDt, param.slsDt);
        // setValue(pMsg.dsContrCatgCd, dsContrCatgCd);
        // setValue(pMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        // setValue(pMsg.prntDsContrBllgSchdPk,
        // prntDsContrBllgSchdPk);
        // api.execute(pMsg, this.onBatchType);
        // END 2017/08/24 K.Kitachi [QC#20746, DEL]
    }

    // ADD-E 10/13/2015 K.Kishimoto
    private void updatePrntDsContrBllgSchd(NSZC010001PMsg param, BigDecimal prntDsContrBllgSchdPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, prntDsContrBllgSchdPk);
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return;
        }
        setValue(inMsg.bllgCalcFlg, FLG_OFF_N);
        setValue(inMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
        inMsg.readMtrCnt.clear();
        inMsg.bllgMtrCnt.clear();
        inMsg.mtrChrgDealAmt.clear();
        inMsg.mtrChrgFuncAmt.clear();
        inMsg.slsTaxRate.clear();
        inMsg.dealTaxAmt.clear();
        inMsg.funcTaxAmt.clear();

        S21ApiTBLAccessor.update(inMsg);
    }

    // Mod End 10/03/2016 <QC#14854>
    // Add Start 02/05/2016 <QC#4151>
    // Start 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
    private void previewBilling(NSZC010001PMsg param, List<DS_CONTR_DTLTMsg> dsContrDtlTmsgList) {
        String glblCmpyCd = param.glblCmpyCd.getValue();

        for (DS_CONTR_DTLTMsg dtlTMsg : dsContrDtlTmsgList) {
            Map<String, Object> contrInfo = getContrInfoFromDtl(glblCmpyCd, dtlTMsg.dsContrDtlPk.getValue());
            if (contrInfo == null) {
                return;
            }
            String dsContrNum = (String) contrInfo.get("DS_CONTR_NUM");
            String dsContrCatgCd = (String) contrInfo.get("DS_CONTR_CATG_CD");
            Map<String, Object> ssmParam = null;
            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
                // Start 2018/06/26 [QC#26196,MOD]
                ssmParam = getSsmParamUsageReg(glblCmpyCd, dsContrNum, dtlTMsg.dsContrDtlPk.getValue());
                // End   2018/06/26 [QC#26196,MOD]
                usageBllgCalcReg(param, glblCmpyCd, ssmParam);
            } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                ssmParam = getSsmParamUsageAgg(glblCmpyCd, dsContrNum);
                usageBllgCalcFltAgg(param, glblCmpyCd, ssmParam, dsContrCatgCd);
            } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                ssmParam = getSsmParamUsageFlt(glblCmpyCd, dsContrNum);
                usageBllgCalcFltAgg(param, glblCmpyCd, ssmParam, dsContrCatgCd);
            }
        }
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
    }
    // END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]

    // Start 2018/06/26 [QC#26196,MOD]
    private Map<String, Object> getSsmParamUsageReg(String glblCmpyCd, String dsContrNum, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrNum", dsContrNum);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.REGULAR);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return ssmParam;
    }
    // End   2018/06/26 [QC#26196,MOD]

    private Map<String, Object> getSsmParamUsageAgg(String glblCmpyCd, String dsContrNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrNum", dsContrNum);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return ssmParam;
    }

    private Map<String, Object> getSsmParamUsageFlt(String glblCmpyCd, String dsContrNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrNum", dsContrNum);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        return ssmParam;
    }

    private void usageBllgCalcReg(NSZC010001PMsg param, String glblCmpyCd, Map<String, Object> ssmParam) {

        List<Map<String, Object>> biilingTargeList = getBillingTarget("getUsageChrgReg", ssmParam);
        for (Map<String, Object> biilingTarget : biilingTargeList) {
            if (getNotEntryCntReg(glblCmpyCd, (BigDecimal) biilingTarget.get("DS_CONTR_DTL_PK"), (String) biilingTarget.get("NEXT_BLLG_DT")) == 0) {
                callPreviewBillingAPI(param, biilingTarget);
            }
        }
    }

    // START 2016/07/08 T.Kanasaka [QC#10951, MOD]
    private void usageBllgCalcFltAgg(NSZC010001PMsg param, String glblCmpyCd, Map<String, Object> ssmParam, String dsContrCatgCd) {
        // END 2016/07/08 T.Kanasaka [QC#10951, MOD]

        // Mod Start 11/04/2016 <QC#15778>
        // mod start 2019/11/14 QC#54401
        List<Map<String, Object>> biilingTargeList = getBillingTarget("getUsageChrgFltAgg", ssmParam);
        String preDsContrNum = null;
        String preNextBillDt = null;
        String preBllgSchdThruDt = null;
        for (Map<String, Object> biilingTarget : biilingTargeList) {
            String curDsContrNum = (String) biilingTarget.get("DS_CONTR_NUM");
            String curNextBillDt = (String) biilingTarget.get("NEXT_BLLG_DT");
            String curBllgSchdThruDt = (String) biilingTarget.get("BLLG_SCHD_THRU_DT");
            // mod start 2017/06/05 CSA Defect#18617
            if (getNotEntryCntFltAgg(glblCmpyCd, (BigDecimal) biilingTarget.get("DS_CONTR_PK"), curNextBillDt) == 0) {
                if (!isSamePeriod(preDsContrNum, preNextBillDt, preBllgSchdThruDt, curDsContrNum, curNextBillDt, curBllgSchdThruDt)) {
                    callPreviewBillingAPIFltAgg(param, biilingTarget);
                }
                // START 2016/07/08 T.Kanasaka [QC#10951, ADD]
            } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                callFleetCalculationAPI(param, biilingTarget);
                // END 2016/07/08 T.Kanasaka [QC#10951, ADD]
            }
            preDsContrNum = curDsContrNum;
            preNextBillDt = curNextBillDt;
            // mod end 2017/06/05 CSA Defect#18617
            preBllgSchdThruDt = curBllgSchdThruDt;
        }
        // mod end 2019/11/14 QC#54401
        // Mod End 11/04/2016 <QC#15778>
    }

    // mod start 2019/11/14 QC#54401
    private boolean isSamePeriod(String preDsContrNum, String preNextBillDt, String preBllgSchdThruDt, String curDsContrNum, String curNextBillDt, String curBllgSchdThruDt) {
        if (!hasValue(preDsContrNum) || !hasValue(preNextBillDt) || !hasValue(preBllgSchdThruDt)) {
            return false;
        }
        if (!preDsContrNum.equals(curDsContrNum)) {
            return false;
        }
        if (!preNextBillDt.equals(curNextBillDt)) {
            return false;
        }
        if (!preBllgSchdThruDt.equals(curBllgSchdThruDt)) {
            return false;
        }
        return true;
    }
    // mod end 2019/11/14 QC#54401

    private void callPreviewBillingAPI(NSZC010001PMsg param, Map<String, Object> biilingTarget) {

        NSZC054001 api = new NSZC054001();
        NSZC054001PMsg pMsg = new NSZC054001PMsg();
        setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(pMsg.slsDt, param.slsDt.getValue());
        setValue(pMsg.mtrReadDt, getMtrReadDtByGrpSq(param.glblCmpyCd.getValue(), (BigDecimal) biilingTarget.get("SVC_PHYS_MTR_READ_GRP_SQ")));
        setValue(pMsg.dsContrNum, (String) biilingTarget.get("DS_CONTR_NUM"));
        setValue(pMsg.dsContrPk, (BigDecimal) biilingTarget.get("DS_CONTR_PK"));
        setValue(pMsg.svcMachMstrPk, (BigDecimal) biilingTarget.get("SVC_MACH_MSTR_PK"));
        // START 2017/02/01 K.Kojima [QC#15640,MOD]
        // api.execute(pMsg, ONBATCH_TYPE.BATCH);
        api.execute(pMsg, this.onBatchType);
        // END 2017/02/01 K.Kojima [QC#15640,MOD]
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // START 2017/02/01 K.Kojima [QC#15640,MOD]
            // S21ApiMessage msg =
            // S21ApiUtil.getXxMsgList(pMsg).get(0);
            // String msgId = msg.getXxMsgid();
            // String[] msgPrms = msg.getXxMsgPrmArray();
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (existsCheckMsgIdList(msgId)) {
                    setHeaderErrMsg(param, msgId);
                }
            }
            // END 2017/02/01 K.Kojima [QC#15640,MOD]
        }
    }

    private void callPreviewBillingAPIFltAgg(NSZC010001PMsg param, Map<String, Object> biilingTarget) {

        NSZC054001 api = new NSZC054001();
        NSZC054001PMsg pMsg = new NSZC054001PMsg();
        setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(pMsg.slsDt, param.slsDt.getValue());
        setValue(pMsg.mtrReadDt, (String) biilingTarget.get("BLLG_SCHD_THRU_DT"));
        setValue(pMsg.dsContrNum, (String) biilingTarget.get("DS_CONTR_NUM"));
        setValue(pMsg.dsContrPk, (BigDecimal) biilingTarget.get("DS_CONTR_PK"));
        // START 2017/02/01 K.Kojima [QC#15640,MOD]
        // api.execute(pMsg, ONBATCH_TYPE.BATCH);
        api.execute(pMsg, this.onBatchType);
        // END 2017/02/01 K.Kojima [QC#15640,MOD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // START 2017/02/01 K.Kojima [QC#15640,MOD]
            // S21ApiMessage msg =
            // S21ApiUtil.getXxMsgList(pMsg).get(0);
            // String msgId = msg.getXxMsgid();
            // String[] msgPrms = msg.getXxMsgPrmArray();
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (existsCheckMsgIdList(msgId)) {
                    setHeaderErrMsg(param, msgId);
                }
            }
            // END 2017/02/01 K.Kojima [QC#15640,MOD]
        }
    }

    // START 2016/07/08 T.Kanasaka [QC#10951, ADD]
    private void callFleetCalculationAPI(NSZC010001PMsg param, Map<String, Object> biilingTarget) {

        NSZC035001 api = new NSZC035001();
        NSZC035001PMsg pMsg = new NSZC035001PMsg();
        setValue(pMsg.glblCmpyCd, param.glblCmpyCd.getValue());
        setValue(pMsg.xxModeCd, NSZC035001Constant.MODE_NORMAL);
        setValue(pMsg.dsContrNum, (String) biilingTarget.get("DS_CONTR_NUM"));
        setValue(pMsg.bllgDt, (String) biilingTarget.get("BLLG_SCHD_THRU_DT"));
        setValue(pMsg.prntDsContrBllgSchdPk, (BigDecimal) biilingTarget.get("DS_CONTR_BLLG_SCHD_PK"));
        // START 2017/02/01 K.Kojima [QC#15640,MOD]
        // api.execute(pMsg, ONBATCH_TYPE.BATCH);
        api.execute(pMsg, this.onBatchType);
        // END 2017/02/01 K.Kojima [QC#15640,MOD]

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
        }
    }

    // END 2016/07/08 T.Kanasaka [QC#10951, ADD]

    private String getMtrReadDtByGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).mtrReadDt.getValue();
    }

    private int getNotEntryCntReg(String glblCmpyCd, BigDecimal dsContrDtlPk, String nextBllgDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("nextBllgDt", nextBllgDt);
        BigDecimal notEntryCnt = (BigDecimal) ssmClient.queryObject("getNotEntryCntReg", ssmParam);
        return notEntryCnt.intValue();
    }

    // Mod Start 11/04/2016 <QC#15778>
    private int getNotEntryCntFltAgg(String glblCmpyCd, BigDecimal dsContrPk, String nextBllgDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("agg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("fleet", DS_CONTR_DTL_TP.FLEET);
        ssmParam.put("nextBllgDt", nextBllgDt);
        BigDecimal notEntryCnt = (BigDecimal) ssmClient.queryObject("getNotEntryCntFltAgg", ssmParam);
        return notEntryCnt.intValue();
    }

    // Mod End 11/04/2016 <QC#15778>

    private List<Map<String, Object>> getBillingTarget(String sqlId, Map<String, Object> ssmParam) {
        List<Map<String, Object>> billingTarget = (List<Map<String, Object>>) ssmClient.queryObjectList(sqlId, ssmParam);
        return billingTarget;
    }

    private Map<String, Object> getContrInfoFromDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        Map<String, Object> contrInfo = (Map<String, Object>) ssmClient.queryObject("getContrInfoFromDtl", ssmParam);
        return contrInfo;
    }

    // Add End 02/05/2016 <QC#4151>
    // Add Start 10/03/2016 <QC#12274>
    private Map<String, Object> getTestCopyInInfo(String glblCmpyCd, NSZC010001PMsg param, NSZC010001_APMsg line) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcTaskNum", param.svcTaskNum);
        ssmParam.put("svcPhysMtrPk", line.svcPhysMtrPk);
        ssmParam.put("mtrReadDt", line.mtrReadDt);
        Map<String, Object> testCopyInInfo = (Map<String, Object>) ssmClient.queryObject("getTestCopyInInfo", ssmParam);
        return testCopyInInfo;
    }

    // START 2018/05/25 K.Kim [QC#15410(Sol#246),DEL]
    // // Add Start 10/03/2016 <QC#12274>
    // private boolean updateTestCopyRecalc(NSZC010001PMsg param) {
    // boolean isRecalc = false;
    // String glblCmpyCd = param.glblCmpyCd.getValue();
    // String testReadDt = param.A.no(0).mtrReadDt.getValue();
    // DS_CONTR_DTLTMsg dsContrDtlTmsg =
    // NSXC001001GetContr.getContrDtlByMachMstrPk(glblCmpyCd,
    // param.svcMachMstrPk.getValue(), testReadDt);
    // if (dsContrDtlTmsg == null) {
    // return isRecalc;
    // }
    // BigDecimal dsContrDtlPk =
    // dsContrDtlTmsg.dsContrDtlPk.getValue();
    // Map<String, Object> contrInfo = getContrInfoFromDtl(glblCmpyCd,
    // dsContrDtlPk);
    // if (contrInfo == null) {
    // return isRecalc;
    // }
    // String dsContrCatgCd = (String)
    // contrInfo.get("DS_CONTR_CATG_CD");
    // HashMap<BigDecimal, BigDecimal> resetMap = new
    // HashMap<BigDecimal, BigDecimal>();
    // BigDecimal starGrpSq =
    // NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd,
    // dsContrDtlPk, param.A.no(0).svcPhysMtrPk.getValue());
    // if (starGrpSq == null) {
    // return isRecalc;
    // }
    // String startReadDt = getMtrReadDtByGrpSq(glblCmpyCd,
    // starGrpSq);
    // for (int i = 0; i < param.A.getValidCount(); i++) {
    // BigDecimal svcPhysMtrPk =
    // param.A.no(i).svcPhysMtrPk.getValue();
    // DS_CONTR_BLLG_SCHDTMsg curSchd =
    // getCurSchdForTestCopy(glblCmpyCd, dsContrDtlPk, svcPhysMtrPk,
    // testReadDt);
    // if (curSchd == null) {
    // continue;
    // }
    // BigDecimal curGrpSq = curSchd.svcPhysMtrReadGrpSq.getValue();
    // String curReadDt = getMtrReadDtByGrpSq(glblCmpyCd, curGrpSq);
    // if (curReadDt.compareTo(testReadDt) < 0) {
    // String searchDt =
    // ZYPDateUtil.addDays(curSchd.bllgSchdThruDt.getValue(), 1);
    // curSchd = getCurSchdForTestCopy(glblCmpyCd, dsContrDtlPk,
    // svcPhysMtrPk, searchDt);
    // if (curSchd == null) {
    // continue;
    // }
    // curGrpSq = curSchd.svcPhysMtrReadGrpSq.getValue();
    // curReadDt = getMtrReadDtByGrpSq(glblCmpyCd, curGrpSq);
    // }
    // BigDecimal dsContrBllgSchdPk =
    // curSchd.dsContrBllgSchdPk.getValue();
    // BigDecimal prntDsContrBllgSchdPk =
    // curSchd.prntDsContrBllgSchdPk.getValue();
    // if (resetMap.containsKey(dsContrBllgSchdPk)) {
    // continue;
    // }
    // resetMap.put(dsContrBllgSchdPk, prntDsContrBllgSchdPk);
    // String curSchdFromDt = curSchd.bllgSchdFromDt.getValue();
    // String preReadDt = getPreReadDt(glblCmpyCd, dsContrDtlPk,
    // svcPhysMtrPk, curSchdFromDt);
    //
    // boolean isReset = false;
    // if (preReadDt == null) {
    // if (startReadDt.compareTo(testReadDt) <= 0 &&
    // testReadDt.compareTo(curReadDt) <= 0) {
    // isReset = true;
    // }
    // } else {
    // if (preReadDt.compareTo(testReadDt) < 0 &&
    // testReadDt.compareTo(curReadDt) <= 0) {
    // isReset = true;
    // }
    // }
    // if (isReset == false) {
    // continue;
    // }
    //
    // NSZC047001 api = new NSZC047001();
    // NSZC047022PMsg pMsg = new NSZC047022PMsg();
    // setValue(pMsg.glblCmpyCd, glblCmpyCd);
    // setValue(pMsg.slsDt, param.slsDt);
    // setValue(pMsg.dsContrCatgCd, dsContrCatgCd);
    // setValue(pMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
    // setValue(pMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
    // api.execute(pMsg, this.onBatchType);
    // isRecalc = true;
    // }
    // return isRecalc;
    // }
    // END 2018/05/25 K.Kim [QC#15410(Sol#246),DEL]

    private DS_CONTR_BLLG_SCHDTMsg getCurSchdForTestCopy(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk, String mtrReadDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("svcPhysMtrPk", svcPhysMtrPk);
        ssmParam.put("mtrReadDt", mtrReadDt);
        ssmParam.put("stsBilled", DS_BLLG_SCHD_STS.BILLED);
        Map<String, Object> curSchdInfo = (Map<String, Object>) ssmClient.queryObject("getCurSchdForTestCopy", ssmParam);
        if (curSchdInfo == null) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, (BigDecimal) curSchdInfo.get("DS_CONTR_BLLG_SCHD_PK"));
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return inMsg;
    }

    private String getPreReadDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcPhysMtrPk, String curSchdFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("svcPhysMtrPk", svcPhysMtrPk);
        ssmParam.put("curSchdFromDt", curSchdFromDt);
        ssmParam.put("stsBilled", DS_BLLG_SCHD_STS.BILLED);
        Map<String, Object> preSchdInfo = (Map<String, Object>) ssmClient.queryObject("getPreSchdForTestCopy", ssmParam);
        if (preSchdInfo == null) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, (BigDecimal) preSchdInfo.get("DS_CONTR_BLLG_SCHD_PK"));
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        if (!hasValue(inMsg.svcPhysMtrReadGrpSq)) {
            return null;
        }
        BigDecimal preGrpSq = inMsg.svcPhysMtrReadGrpSq.getValue();
        String preReadDt = getMtrReadDtByGrpSq(glblCmpyCd, preGrpSq);
        return preReadDt;
    }

    // START 2017/02/01 K.Kojima [QC#15640,ADD]
    /**
     * existsCheckMsgIdList
     * @param msgId String
     * @return boolean
     */
    private boolean existsCheckMsgIdList(String msgId) {
        if (msgId == null) {
            return false;
        }
        for (int i = 0; i < CHECK_MSGID_LIST.length; i++) {
            if (msgId.equals(CHECK_MSGID_LIST[i])) {
                return true;
            }
        }
        return false;
    }

    // END 2017/02/01 K.Kojima [QC#15640,ADD]

    // START 2017/03/13 K.Kitachi [QC#15163, ADD]
    private BigDecimal getSvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        BigDecimal newSvcPhysMtrReadGrpSq = null;
        BigDecimal count = BigDecimal.ONE;
        while (count.compareTo(BigDecimal.ZERO) > 0) {
            newSvcPhysMtrReadGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PHYS_MTR_READ_GRP_SQ);
            count = countSvcPhysMtrReadGrpSq(glblCmpyCd, svcMachMstrPk, newSvcPhysMtrReadGrpSq);
        }
        return newSvcPhysMtrReadGrpSq;
    }

    private BigDecimal countSvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        return (BigDecimal) this.ssmClient.queryObject("countSvcPhysMtrReadGrpSq", ssmParam);
    }

    // END 2017/03/13 K.Kitachi [QC#15163, ADD]

    // START 2017/08/24 K.Kitachi [QC#20746, ADD]
    private boolean execResetProcess(NSZC010001PMsg param,  List<DS_CONTR_DTLTMsg> dsContrDtlTmsgList) {
        boolean isExecReset = false;
        String glblCmpyCd = param.glblCmpyCd.getValue();
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
        // BigDecimal dsContrDtlPk = param.dsContrDtlPk.getValue();
        // if (!hasValue(dsContrDtlPk)) {
        // String mtrReadDt = param.A.no(0).mtrReadDt.getValue();
        // DS_CONTR_DTLTMsg dsContrDtlTmsg =
        // NSXC001001GetContr.getContrDtlByMachMstrPk(glblCmpyCd,
        // param.svcMachMstrPk.getValue(), mtrReadDt);
        // if (dsContrDtlTmsg == null) {
        // return isExecReset;
        // }
        // dsContrDtlPk = dsContrDtlTmsg.dsContrDtlPk.getValue();
        // }
        if (dsContrDtlTmsgList.size() == 0) {
            return isExecReset;
        }
        for (DS_CONTR_DTLTMsg dsContrDtlTmsg : dsContrDtlTmsgList) {
            BigDecimal dsContrDtlPk = dsContrDtlTmsg.dsContrDtlPk.getValue();
            DS_CONTR_BLLG_MTRTMsgArray outMsgArray = getDsContrBllgMtr(glblCmpyCd, dsContrDtlPk);

            for (int i = 0; i < outMsgArray.getValidCount(); i++) {
                List<Map<String, Object>> schdInfoList = getResetSchdInfo(glblCmpyCd, dsContrDtlPk, outMsgArray.no(i).dsContrBllgMtrPk.getValue());
                if (schdInfoList == null || schdInfoList.size() == 0) {
                    continue;
                }

                for (Map<String, Object> schdInfo : schdInfoList) {
                    callBillingScheduleAPI(param, schdInfo);
                    isExecReset = true;
                }
            }
        }
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]

        return isExecReset;
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private List<Map<String, Object>> getResetSchdInfo(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getResetSchdInfo", ssmParam);
    }

    // START 2018/07/03 K.Kim [QC#26726, MOD]
    // private void callBillingScheduleAPI(NSZC010001PMsg param, Map<String, Object> schdInfo) {
    private boolean callBillingScheduleAPI(NSZC010001PMsg param, Map<String, Object> schdInfo) {
    // END 2018/07/03 K.Kim [QC#26726, MOD]
        NSZC047001 api = new NSZC047001();
        NSZC047022PMsg pMsg = new NSZC047022PMsg();
        setValue(pMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(pMsg.slsDt, param.slsDt);
        setValue(pMsg.dsContrCatgCd, (String) schdInfo.get("DS_CONTR_CATG_CD"));
        setValue(pMsg.dsContrBllgSchdPk, (BigDecimal) schdInfo.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(pMsg.prntDsContrBllgSchdPk, (BigDecimal) schdInfo.get("PRNT_DS_CONTR_BLLG_SCHD_PK"));
        api.execute(pMsg, this.onBatchType);
        // START 2018/07/03 K.Kim [QC#26726, ADD]
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
            return false;
        }
        return true;
        // END 2018/07/03 K.Kim [QC#26726, ADD]
    }

    // END 2017/08/24 K.Kitachi [QC#20746, ADD]

    // START 2017/09/01 K.Kasai [QC#15134,ADD]
    private int getCntrDigitNum(SVC_PHYS_MTRTMsgArray svcPhysMtrTmsgArray, BigDecimal svcPhysMtrPk, String mtrLbCd) {
        int cntrDigitNum = 0;
        for (int i = 0; i < svcPhysMtrTmsgArray.getValidCount(); i++) {
            if (svcPhysMtrTmsgArray.no(i).svcPhysMtrPk.getValue().equals(svcPhysMtrPk) || svcPhysMtrTmsgArray.no(i).mdlMtrLbCd.getValue().equals(mtrLbCd)) {
                if (hasValue(svcPhysMtrTmsgArray.no(i).cntrDigitNum)) {
                    cntrDigitNum = svcPhysMtrTmsgArray.no(i).cntrDigitNum.getValueInt();
                    return cntrDigitNum;
                }
            }
        }
        return cntrDigitNum;
    }

    private BigDecimal getMaxMtrCnt(int cntrDigitNum) {
        double maxMtrCnt = 0;
        if (cntrDigitNum > 0) {
            maxMtrCnt = Math.pow(10, cntrDigitNum);
        }
        return BigDecimal.valueOf(maxMtrCnt);
    }

    private BigDecimal calcMtrCntWithRO(int mtrReadCnt, int cntrDigitNum) {
        BigDecimal mtrReadCntBd = BigDecimal.valueOf(mtrReadCnt);
        if (cntrDigitNum > 0) {
            mtrReadCntBd = getMaxMtrCnt(cntrDigitNum).add(BigDecimal.valueOf(mtrReadCnt)).setScale(0);
        }
        return mtrReadCntBd;
    }

    private boolean isMtrExch(String dsMtrReadTpCd) {
        if (DS_MTR_READ_TP.EXCHANGE_METER_TO.equals(dsMtrReadTpCd)) {
            return true;
        }
        return false;
    }

    // mod start 2020/03/03 QC#56123
    //private boolean isMtrRO(NSZC010001PMsg param, NSZC010001_APMsg line, String dsMtrReadTpCd) {
    private boolean isMtrRO(NSZC010001PMsg param, NSZC010001_APMsg line, String dsMtrReadTpCd, boolean isBackAlloc) {
        if (DS_MTR_READ_TP.ROLLOVER.equals(dsMtrReadTpCd)) {
            return true;
        //} else {
        } else if (isBackAlloc) {
            DS_MTR_READ_TPTMsg mtrReadTpTmsg = getMtrReadTp(param.glblCmpyCd.getValue(), dsMtrReadTpCd);
            if (FLG_OFF_N.equals(mtrReadTpTmsg.adjMtrReadTpFlg.getValue())) {
                // mod start 2018/06/04 QC#26052
                //Map<String, Object> rsltMap = getLastMtrRead(param.glblCmpyCd.getValue(),param.svcMachMstrPk.getValue(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), null);
//                Map<String, Object> rsltMap = getLastMtrRead(param.glblCmpyCd.getValue(), param.svcMachMstrPk.getValue(), line.svcPhysMtrPk.getValue(), line.mtrReadDt.getValue(), null, param.dsMtrReadTpGrpCd.getValue());
                // mod end 2018/06/04 QC#26052
//                if (rsltMap != null && !rsltMap.isEmpty()) {
//                    BigDecimal lastReadMtrCnt = (BigDecimal) rsltMap.get("READ_MTR_CNT");
//                    if (line.readMtrCnt.getValue().compareTo(lastReadMtrCnt) < 0) {
//                        return true;
//                    }
//                }
                List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
                SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoByMtrReadTpGrp(param.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList);
                if (bean != null) {
                    if (bean.getReadMtrCnt().compareTo(line.readMtrCnt.getValue()) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // mod end 2020/03/03 QC#56123

    // END 2017/09/01 K.Kasai [QC#15134,ADD]

    // START 2018/05/08 K.Kojima [QC#24817,ADD]
    private boolean setMtrFmla(NSZC010001PMsg param, String glblCmpyCd, BigDecimal svcMachMstrPk) {
        List<Map<String, Object>> mtrFmlaInfoList = getMtrFmlaInfo(glblCmpyCd, svcMachMstrPk);
        for (Map<String, Object> mtrFmlaInfo : mtrFmlaInfoList) {
            String mtrLbCd = (String) mtrFmlaInfo.get("MTR_LB_CD");
            boolean existsMtrLbCdFlg = false;
            for (int i = 0; i < param.A.getValidCount(); i++) {
                if (mtrLbCd.equals(param.A.no(i).mdlMtrLbCd.getValue())) {
                    existsMtrLbCdFlg = true;
                    break;
                }
            }
            if (existsMtrLbCdFlg) {
                continue;
            }
            BigDecimal readMtrCnt = BigDecimal.ZERO;
            for (int i = 1; i <= 10; i++) {
                String number = String.valueOf(i);
                if (i < 10) {
                    number = "0" + number;
                }
                String mtrFmlaTpCd = (String) mtrFmlaInfo.get("MTR_FMLA_TP_CD_" + number);
                String fmlaMtrLbCd = (String) mtrFmlaInfo.get("FMLA_MTR_LB_CD_" + number);
                if (!hasValue(mtrFmlaTpCd) || !hasValue(fmlaMtrLbCd)) {
                    break;
                }
                if (i != 1 && MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                    break;
                }
                boolean existsFmlaMtrLbFlg = false;
                for (int paramCount = 0; paramCount < param.A.getValidCount(); paramCount++) {
                    if (fmlaMtrLbCd.equals(param.A.no(paramCount).mdlMtrLbCd.getValue())) {
                        if (MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                            readMtrCnt = param.A.no(paramCount).readMtrCnt.getValue();
                            existsFmlaMtrLbFlg = true;
                        } else if (MTR_FMLA_TP.ADD.equals(mtrFmlaTpCd)) {
                            readMtrCnt = readMtrCnt.add(param.A.no(paramCount).readMtrCnt.getValue());
                            existsFmlaMtrLbFlg = true;
                        } else if (MTR_FMLA_TP.SUBTRACT.equals(mtrFmlaTpCd)) {
                            readMtrCnt = readMtrCnt.subtract(param.A.no(paramCount).readMtrCnt.getValue());
                            existsFmlaMtrLbFlg = true;
                        }
                        break;
                    }
                }
                if (!existsFmlaMtrLbFlg) {
                    setHeaderErrMsg(param, NSZM0747E);
                    return false;
                }
                if (readMtrCnt.compareTo(BigDecimal.ZERO) < 0) {
                    setHeaderErrMsg(param, NSZM1333E);
                    return false;
                }
            }
            EZDMsg.copy(param.A.no(param.A.getValidCount() - 1), null, param.A.no(param.A.getValidCount()), null);
            NSZC010001_APMsg clrLine = param.A.no(param.A.getValidCount());
            clrLine.svcPhysMtrReadPk.clear();
            clrLine.testMtrCnt.clear();
            clrLine.mtrEntryCmntTxt.clear();
            setValue(clrLine.readMtrCnt, readMtrCnt);
            setValue(clrLine.estFlg, ZYPConstant.FLG_OFF_N);
            setValue(clrLine.invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(clrLine.mtrLbCd, mtrLbCd);
            setValue(clrLine.mdlMtrLbCd, mtrLbCd);
            setValue(clrLine.dsMdlMtrPk, (BigDecimal) mtrFmlaInfo.get("DS_MDL_MTR_PK"));
            setValue(clrLine.svcPhysMtrPk, (BigDecimal) mtrFmlaInfo.get("SVC_PHYS_MTR_PK"));
            // START 2019/11/27 K.Kitachi [QC#54840, ADD]
            if (isRollover(clrLine) || isExchange(clrLine)) {
                setValue(clrLine.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                clrLine.cntrResetTpCd.clear();
            }
            // END 2019/11/27 K.Kitachi [QC#54840, ADD]
            param.A.setValidCount(param.A.getValidCount() + 1);
        }
        return true;
    }

    private void setNonMtrFmla(NSZC010001PMsg param, SVC_PHYS_MTRTMsgArray svcPhysMtrTmsgArray) {
        for (int physMtrCount = 0; physMtrCount < svcPhysMtrTmsgArray.getValidCount(); physMtrCount++) {
            SVC_PHYS_MTRTMsg tMsg = svcPhysMtrTmsgArray.no(physMtrCount);
            boolean existsMtrLbCdFlg = false;
            for (int paramCount = 0; paramCount < param.A.getValidCount(); paramCount++) {
                if (tMsg.mdlMtrLbCd.getValue().equals(param.A.no(paramCount).mdlMtrLbCd.getValue())) {
                    existsMtrLbCdFlg = true;
                    break;
                }
            }
            if (existsMtrLbCdFlg) {
                continue;
            }
            // mod start 2020/03/03 QC#56123
            //BigDecimal readMtrCnt = getBeforeMtrCnt(tMsg.glblCmpyCd.getValue(), tMsg.svcPhysMtrPk.getValue(), param.A.no(0).mtrReadDt.getValue());
            //if (readMtrCnt == null) {
            //    readMtrCnt = BigDecimal.ZERO;
            //}
            BigDecimal readMtrCnt = BigDecimal.ZERO;
            List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
            if (DS_MTR_READ_TP_GRP.SERVICE_READS.equals(param.dsMtrReadTpGrpCd.getValue()) || DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ.equals(param.dsMtrReadTpGrpCd.getValue())) {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
            } else {
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.BILLABLE_READS);
                dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SUPPLY_READS);
            }
            SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoByMtrReadTpGrp(param.glblCmpyCd.getValue(), param.A.no(0).mtrReadDt.getValue(), tMsg.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList);
            if (bean != null) {
                readMtrCnt = bean.getReadMtrCnt();
            }
            // mod end 2020/03/03 QC#56123

            EZDMsg.copy(param.A.no(param.A.getValidCount() - 1), null, param.A.no(param.A.getValidCount()), null);
            NSZC010001_APMsg clrLine = param.A.no(param.A.getValidCount());
            clrLine.svcPhysMtrReadPk.clear();
            clrLine.testMtrCnt.clear();
            clrLine.mtrEntryCmntTxt.clear();
            setValue(clrLine.readMtrCnt, readMtrCnt);
            setValue(clrLine.estFlg, ZYPConstant.FLG_OFF_N);
            setValue(clrLine.invProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(clrLine.mtrLbCd, tMsg.mdlMtrLbCd);
            setValue(clrLine.mdlMtrLbCd, tMsg.mdlMtrLbCd);
            setValue(clrLine.dsMdlMtrPk, tMsg.dsMdlMtrPk);
            setValue(clrLine.svcPhysMtrPk, tMsg.svcPhysMtrPk);
            // START 2019/11/27 K.Kitachi [QC#54840, ADD]
            if (isRollover(clrLine) || isExchange(clrLine)) {
                setValue(clrLine.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
                clrLine.cntrResetTpCd.clear();
            }
            // END 2019/11/27 K.Kitachi [QC#54840, ADD]
            param.A.setValidCount(param.A.getValidCount() + 1);
        }
    }

    private List<Map<String, Object>> getMtrFmlaInfo(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getMtrFmlaInfo", ssmParam);
    }

    // del start 2020/03/03 QC#56123
//    private BigDecimal getBeforeMtrCnt(String glblCmpyCd, BigDecimal svcPhysMtrPk, String mtrReadDt) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("svcPhysMtrPk", svcPhysMtrPk);
//        ssmParam.put("mtrReadDt", mtrReadDt);
//        return (BigDecimal) this.ssmClient.queryObject("getBeforeMtrCnt", ssmParam);
//    }
    // del end 2020/03/03 QC#56123
    // END 2018/05/08 K.Kojima [QC#24817,ADD]

    // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//    private BigDecimal getTtlMtrCnt(NSZC010001PMsg param) {
    private BigDecimal getTtlMtrCnt(NSZC010001PMsg param, boolean isBackAlloc) {
    // END 2019/09/18 K.Kitachi [QC#53573, MOD]
        // Mod End 04/27/2016 <QC#7583>
        BigDecimal currTotCnt = BigDecimal.ZERO;
        String glblCmpyCd = param.glblCmpyCd.getValue();
        BigDecimal currTotCntTotMtr = BigDecimal.ZERO;
        boolean existTotMtr = false;
        for (int i = 0; i < param.A.getValidCount(); i++) {
            MTR_LBTMsg mtrLbTmsg = getMtrLbTmsg(glblCmpyCd, param.A.no(i).mdlMtrLbCd.getValue());
            if (mtrLbTmsg == null) {
                // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//                setHeaderErrMsg(param, NSZM0751E);
                if (isBackAlloc) {
                    setHeaderErrMsg(param, NSZM1366E);
                } else {
                    setHeaderErrMsg(param, NSZM0751E);
                }
                // END 2019/09/18 K.Kitachi [QC#53573, MOD]
                return null;
            }
            if (!FLG_ON_Y.equals(mtrLbTmsg.totMtrFlg.getValue())) {
                currTotCnt = currTotCnt.add(param.A.no(i).readMtrCnt.getValue());
            } else {
                currTotCntTotMtr = currTotCntTotMtr.add(param.A.no(i).readMtrCnt.getValue());
                existTotMtr = true;
            }
        }
        // START 2019/09/05 K.Kitachi [QC#53247, MOD]
//        if (existTotMtr) {
//            currTotCnt = currTotCntTotMtr;
//        } else {
//            return null;
//        }
//        return currTotCnt;
        boolean isUsedTotMtr = NSXC003001SvcPhysMtrRead.isUsedTotMtr(glblCmpyCd, param.svcMachMstrPk.getValue());
        if (!isUsedTotMtr || !existTotMtr) {
            return currTotCnt;
        }
        return currTotCntTotMtr;
        // END 2019/09/05 K.Kitachi [QC#53247, MOD]
    }

    // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//    private boolean hasErrMdlTtlOver(NSZC010001PMsg param, BigDecimal currTotCnt, Map<BigDecimal, AddlLineInfoBean> addlLineInfoMap) {
    private boolean hasErrMdlTtlOver(NSZC010001PMsg param, BigDecimal currTotCnt, Map<BigDecimal, AddlLineInfoBean> addlLineInfoMap, boolean isBackAlloc) {
    // END 2019/09/18 K.Kitachi [QC#53573, MOD]
        BigDecimal lastBillingMeterSvcPhysMtrReadGrpSq = null;
        String glblCmpyCd = param.glblCmpyCd.getValue();
        BigDecimal svcMachMstrPk = param.svcMachMstrPk.getValue();
        String mtrReadDt = param.A.no(0).mtrReadDt.getValue();
        // START 2019/09/05 K.Kitachi [QC#53247, ADD]
        BigDecimal dsContrDtlPk = null;
        SvcPhysMtrReadInfoBean startRead = new SvcPhysMtrReadInfoBean();
        SvcPhysMtrReadInfoBean endRead = new SvcPhysMtrReadInfoBean();
        NSXC003001MtrCntTwoPntEst mtrCntTwoPntEst = new NSXC003001MtrCntTwoPntEst();
        BigDecimal currTotRollOverExchCnt = BigDecimal.ZERO;
        BigDecimal currTotRollOverExchCntTotMtr = BigDecimal.ZERO;
        boolean existTotMtr = false;
        boolean isErrorCheck = false;
        // END 2019/09/05 K.Kitachi [QC#53247, ADD]
        for (int i = 0; i < param.A.getValidCount(); i++) {
            // START 2019/09/05 K.Kitachi [QC#53247, ADD]
            MTR_LBTMsg mtrLbTmsg = getMtrLbTmsg(glblCmpyCd, param.A.no(i).mdlMtrLbCd.getValue());
            if (mtrLbTmsg == null) {
                // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//                setHeaderErrMsg(param, NSZM0751E);
                if (isBackAlloc) {
                    setHeaderErrMsg(param, NSZM1366E);
                } else {
                    setHeaderErrMsg(param, NSZM0751E);
                }
                // END 2019/09/18 K.Kitachi [QC#53573, MOD]
                return true;
            }
            if (FLG_ON_Y.equals(mtrLbTmsg.totMtrFlg.getValue())) {
                existTotMtr = true;
            }
            // END 2019/09/05 K.Kitachi [QC#53247, ADD]
            AddlLineInfoBean addlLine = addlLineInfoMap.get(param.A.no(i).svcPhysMtrPk.getValue());
            if (addlLine == null) {
                continue;
            }
            if (!addlLine.getIsBllgRead()) {
                continue;
            }
            if (addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq() == null) {
                continue;
            }
            // START 2019/09/05 K.Kitachi [QC#53247, MOD]
//            BigDecimal dsContrDtlPk = addlLine.getDsContrDtlPk();
//            if (!hasValue(dsContrDtlPk)) {
//                continue;
//            }
            if (!hasValue(addlLine.getDsContrDtlPk())) {
                continue;
            }
            dsContrDtlPk = addlLine.getDsContrDtlPk();
            // END 2019/09/05 K.Kitachi [QC#53247, MOD]
            if (addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq() != null) {
                lastBillingMeterSvcPhysMtrReadGrpSq = addlLine.getLastBillingMeterSvcPhysMtrReadGrpSq();
            }
            // START 2019/09/05 K.Kitachi [QC#53247, ADD]
            BigDecimal startSvcPhysMtrReadPk = getSvcPhysMtrReadPk(glblCmpyCd, param.A.no(i).svcPhysMtrPk.getValue(), lastBillingMeterSvcPhysMtrReadGrpSq);
            if (!hasValue(startSvcPhysMtrReadPk)) {
                continue;
            }
            BigDecimal endSvcPhysMtrReadPk = param.A.no(i).svcPhysMtrReadPk_O.getValue();
            if (!hasValue(endSvcPhysMtrReadPk)) {
                continue;
            }
            startRead.setSvcPhysMtrReadPk(startSvcPhysMtrReadPk);
            endRead.setSvcPhysMtrReadPk(endSvcPhysMtrReadPk);
            BigDecimal rollOverExchCnt = mtrCntTwoPntEst.getRollOverExchCnt(glblCmpyCd, startRead, endRead);
            if (!FLG_ON_Y.equals(mtrLbTmsg.totMtrFlg.getValue())) {
                currTotRollOverExchCnt = currTotRollOverExchCnt.add(rollOverExchCnt);
            } else {
                currTotRollOverExchCntTotMtr = currTotRollOverExchCntTotMtr.add(rollOverExchCnt);
            }
            isErrorCheck = true;
            // END 2019/09/05 K.Kitachi [QC#53247, ADD]
            // START 2019/09/05 K.Kitachi [QC#53247, DEL]
//            if (NSXC003001SvcPhysMtrRead.isOverTotal(glblCmpyCd, dsContrDtlPk, svcMachMstrPk, param.A.no(0).svcPhysMtrPk.getValue(), lastBillingMeterSvcPhysMtrReadGrpSq, mtrReadDt, currTotCnt)) {
//                setHeaderErrMsg(param, NSZM0751E);
//                return true;
//            }
            // END 2019/09/05 K.Kitachi [QC#53247, DEL]
        }
        // START 2019/09/05 K.Kitachi [QC#53247, ADD]
        if (!isErrorCheck) {
            return false;
        }
        BigDecimal calcTotMtrCnt = currTotCnt;
        boolean isUsedTotMtr = NSXC003001SvcPhysMtrRead.isUsedTotMtr(glblCmpyCd, param.svcMachMstrPk.getValue());
        if (!isUsedTotMtr || !existTotMtr) {
            calcTotMtrCnt = calcTotMtrCnt.add(currTotRollOverExchCnt);
        } else {
            calcTotMtrCnt = calcTotMtrCnt.add(currTotRollOverExchCntTotMtr);
        }
        if (NSXC003001SvcPhysMtrRead.isOverTotal(glblCmpyCd, dsContrDtlPk, svcMachMstrPk, param.A.no(0).svcPhysMtrPk.getValue(), lastBillingMeterSvcPhysMtrReadGrpSq, mtrReadDt, calcTotMtrCnt)) {
            // START 2019/09/18 K.Kitachi [QC#53573, MOD]
//            setHeaderErrMsg(param, NSZM0751E);
            if (isBackAlloc) {
                setHeaderErrMsg(param, NSZM1366E);
            } else {
                setHeaderErrMsg(param, NSZM0751E);
            }
            // END 2019/09/18 K.Kitachi [QC#53573, MOD]
            return true;
        }
        // END 2019/09/05 K.Kitachi [QC#53247, ADD]
        return false;
    }
    // Start 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
    private BigDecimal getGrpSqByReadDt(String glblCmpyCd, BigDecimal svcPhysMtrPk, String mtrReadDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcPhysMtrPk", svcPhysMtrPk);
        ssmParam.put("mtrReadDt", mtrReadDt);
        ssmParam.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        return (BigDecimal) this.ssmClient.queryObject("getGrpSqByReadDt", ssmParam);
    }
    // End   2018/05/25 K.Kim [QC#15410(Sol#246),ADD]

    // START 2018/07/12 K.Kitachi [QC#27022, ADD]
    private boolean updateFmlaMtrReadCntByGrpSq(NSZC010001PMsg param, String glblCmpyCd, BigDecimal svcMachMstrPk, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsgArray svcPhysMtrReadList = getSvcPhysMtrReadByGrpSqForUpdate(glblCmpyCd, svcPhysMtrReadGrpSq);
        List<Map<String, Object>> mtrFmlaInfoList = getMtrFmlaInfo(glblCmpyCd, svcMachMstrPk);
        for (Map<String, Object> mtrFmlaInfo : mtrFmlaInfoList) {
            String mtrLbCd = (String) mtrFmlaInfo.get("MTR_LB_CD");
            SVC_PHYS_MTR_READTMsg updateSvcPhysMtrRead = null;
            for (int i = 0; i < svcPhysMtrReadList.getValidCount(); i++) {
                if (mtrLbCd.equals(svcPhysMtrReadList.no(i).mtrLbCd.getValue())) {
                    updateSvcPhysMtrRead = svcPhysMtrReadList.no(i);
                    break;
                }
            }
            if (updateSvcPhysMtrRead == null) {
                continue;
            }
            BigDecimal readMtrCnt = BigDecimal.ZERO;
            for (int i = 1; i <= 10; i++) {
                String number = String.valueOf(i);
                if (i < 10) {
                    number = "0" + number;
                }
                String mtrFmlaTpCd = (String) mtrFmlaInfo.get("MTR_FMLA_TP_CD_" + number);
                String fmlaMtrLbCd = (String) mtrFmlaInfo.get("FMLA_MTR_LB_CD_" + number);
                if (!hasValue(mtrFmlaTpCd) || !hasValue(fmlaMtrLbCd)) {
                    break;
                }
                if (i != 1 && MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                    break;
                }
                boolean existsFmlaMtrLbFlg = false;
                for (int paramCount = 0; paramCount < svcPhysMtrReadList.getValidCount(); paramCount++) {
                    if (fmlaMtrLbCd.equals(svcPhysMtrReadList.no(paramCount).mtrLbCd.getValue())) {
                        if (MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                            readMtrCnt = svcPhysMtrReadList.no(paramCount).readMtrCnt.getValue();
                            existsFmlaMtrLbFlg = true;
                        } else if (MTR_FMLA_TP.ADD.equals(mtrFmlaTpCd)) {
                            readMtrCnt = readMtrCnt.add(svcPhysMtrReadList.no(paramCount).readMtrCnt.getValue());
                            existsFmlaMtrLbFlg = true;
                        } else if (MTR_FMLA_TP.SUBTRACT.equals(mtrFmlaTpCd)) {
                            readMtrCnt = readMtrCnt.subtract(svcPhysMtrReadList.no(paramCount).readMtrCnt.getValue());
                            existsFmlaMtrLbFlg = true;
                        }
                        break;
                    }
                }
                if (!existsFmlaMtrLbFlg) {
                    setHeaderErrMsg(param, NSZM0747E);
                    return false;
                }
                if (readMtrCnt.compareTo(BigDecimal.ZERO) < 0) {
                    setHeaderErrMsg(param, NSZM1333E);
                    return false;
                }
            }
            setValue(updateSvcPhysMtrRead.readMtrCnt, readMtrCnt);
            S21ApiTBLAccessor.update(updateSvcPhysMtrRead);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateSvcPhysMtrRead.getReturnCode())) {
                setHeaderErrMsg(param, NSZM0368E);
                return false;
            }
        }
        return true;
    }

    private SVC_PHYS_MTR_READTMsgArray getSvcPhysMtrReadByGrpSqForUpdate(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        return (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
    }
    // END 2018/07/12 K.Kitachi [QC#27022, ADD]

    // START 2019/09/05 K.Kitachi [QC#53247, ADD]
    private BigDecimal getSvcPhysMtrReadPk(String glblCmpyCd, BigDecimal svcPhysMtrPk, BigDecimal svcPhysMtrReadGrpSq) {
        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        inMsg.setSQLID("006");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcPhysMtrPk01", svcPhysMtrPk);
        inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
        SVC_PHYS_MTR_READTMsgArray outArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return null;
        }
        return outArray.no(0).svcPhysMtrReadPk.getValue();
    }
    // END 2019/09/05 K.Kitachi [QC#53247, ADD]

    // START 2019/09/11 K.Kitachi [QC#53159, ADD]
    private List<DS_CONTR_DTLTMsg> getDsContrDtlTMsgList(String glblCmpyCd, BigDecimal svcMachMstrPk, String mtrReadDt) {
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkList(glblCmpyCd, svcMachMstrPk, mtrReadDt);
        if (dsContrDtlTMsgList == null || dsContrDtlTMsgList.size() == 0) {
            return null;
        }
        List<String> exclContrDtlStsList = getExclContrDtlStsList(glblCmpyCd);
        if (exclContrDtlStsList == null || exclContrDtlStsList.size() == 0) {
            return dsContrDtlTMsgList;
        }
        for (int i = dsContrDtlTMsgList.size() - 1; i >= 0; i--) {
            if (exclContrDtlStsList.contains(dsContrDtlTMsgList.get(i).dsContrDtlStsCd.getValue())) {
                dsContrDtlTMsgList.remove(i);
            }
        }
        return dsContrDtlTMsgList;
    }

    private List<String> getExclContrDtlStsList(String glblCmpyCd) {
        String exclContrDtlSts = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_EXCL_CONTR_DTL_STS, glblCmpyCd);
        if (!hasValue(exclContrDtlSts)) {
            return null;
        }
        String[] list = exclContrDtlSts.split(STR_COMMA);
        return Arrays.asList(list);
    }
    // END 2019/09/11 K.Kitachi [QC#53159, ADD]

    // START 2019/09/19 K.Kitachi [QC#53159, ADD]
    private boolean isRegistStartRead(String glblCmpyCd, List<DS_CONTR_DTLTMsg> dsContrDtlTmsgList) {
        for (DS_CONTR_DTLTMsg dsContrDtlTmsg : dsContrDtlTmsgList) {
            BigDecimal dsContrDtlPk = dsContrDtlTmsg.dsContrDtlPk.getValue();
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray contrPhysBllgMtrRelnTMsgArray = getContrPhysBllgMtrReln(glblCmpyCd, dsContrDtlPk);
            for (int i = 0; i < contrPhysBllgMtrRelnTMsgArray.getValidCount(); i++) {
                if (!FLG_ON_Y.equals(contrPhysBllgMtrRelnTMsgArray.no(i).bllblFlg.getValue())) {
                    continue;
                }
                BigDecimal startMtrGrpSq = NSXC003001SvcPhysMtrRead.getStartMeterSvcPhysMtrReadGrpSq(glblCmpyCd, dsContrDtlPk, contrPhysBllgMtrRelnTMsgArray.no(i).svcPhysMtrPk.getValue());
                if (!hasValue(startMtrGrpSq)) {
                    return false;
                }
            }
        }
        return true;
    }

    private CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrReln(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2019/09/19 K.Kitachi [QC#53159, ADD]

    // START 2019/11/27 K.Kitachi [QC#54840, ADD]
    private boolean isRollover(NSZC010001_APMsg aPMsg) {
        if (DS_MTR_READ_TP.ROLLOVER.equals(aPMsg.dsMtrReadTpCd.getValue())) {
            return true;
        }
        if (CNTR_RESET_TP.METER_ROLLOVER.equals(aPMsg.cntrResetTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isExchange(NSZC010001_APMsg aPMsg) {
        if (DS_MTR_READ_TP.EXCHANGE_METER_TO.equals(aPMsg.dsMtrReadTpCd.getValue())) {
            return true;
        }
        if (CNTR_RESET_TP.METER_EXCHANGE.equals(aPMsg.cntrResetTpCd.getValue())) {
            return true;
        }
        return false;
    }
    // END 2019/11/27 K.Kitachi [QC#54840, ADD]

    // START 2020/01/24 K.Kitachi [QC#55495, ADD]
    // START 2022/06/22 E.Sanchez [QC#59804, MOD]
    // private boolean isCarryOver(String glblCmpyCd, BigDecimal svcPhysMtrPk,String mtrReadDt, String dsMtrReadTpGrpCd) {
    private boolean isCarryOver(String glblCmpyCd, BigDecimal svcPhysMtrPk,String mtrReadDt, String dsMtrReadTpGrpCd, String xxStartReadFlg ) {
    // END 2022/06/22 E.Sanchez [QC#59804, MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcPhysMtrPk", svcPhysMtrPk);
        ssmParam.put("mtrReadDt", mtrReadDt);
        // START 2022/06/22 E.Sanchez [QC#59804, ADD]
        if (FLG_ON_Y.equals(xxStartReadFlg)) {
            int befDays = ZYPCodeDataUtil.getNumConstValue(
                    "START_MTR_READ_WINDOW_BEF_DAYS", glblCmpyCd).intValue();
            String newMtrDt = ZYPDateUtil.addDays(mtrReadDt, befDays);
            ssmParam.put("mtrReadDt", newMtrDt);
        }
        // END 2022/06/22 E.Sanchez [QC#59804, ADD]
        ssmParam.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.CANCELLED);
        ssmParam.put("dsMtrReadTpGrpCd", dsMtrReadTpGrpCd);
        BigDecimal count = (BigDecimal) this.ssmClient.queryObject("countTechMndBllblCntr", ssmParam);
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return true;
        }
        return false;
    }
    // END 2020/01/24 K.Kitachi [QC#55495, ADD]

    // add start 2020/03/03 QC#56123
    private boolean hasErrMdlTtlOverSvcRead(NSZC010001PMsg param) {
        SvcPhysMtrReadInfoBean startRead = new SvcPhysMtrReadInfoBean();
        SvcPhysMtrReadInfoBean endRead = new SvcPhysMtrReadInfoBean();
        NSXC003001MtrCntTwoPntEst mtrCntTwoPntEst = new NSXC003001MtrCntTwoPntEst();
        boolean existTotMtr = false;
        boolean isErrorCheck = false;
        SVC_PHYS_MTR_READTMsg lastSvcPhysMtrRead = null;
        BigDecimal currTotCnt = BigDecimal.ZERO;
        BigDecimal currTotCntTotMtr = BigDecimal.ZERO;

        for (int i = 0; i < param.A.getValidCount(); i++) {
            NSZC010001_APMsg line = param.A.no(i);
            if (hasValue(line.svcPhysMtrReadPk) && FLG_OFF_N.equals(line.vldMtrFlg.getValue())) {
                return false;
            }
            MTR_LBTMsg mtrLbTmsg = getMtrLbTmsg(param.glblCmpyCd.getValue(), line.mdlMtrLbCd.getValue());
            if (mtrLbTmsg == null) {
                continue;
            }

            if (FLG_ON_Y.equals(mtrLbTmsg.totMtrFlg.getValue())) {
                currTotCntTotMtr = currTotCntTotMtr.add(line.readMtrCnt.getValue());
                existTotMtr = true;
            } else {
                currTotCnt = currTotCnt.add(line.readMtrCnt.getValue());
            }

            List<String> dsMtrReadTpGrpCdList = new ArrayList<String>();
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_READS);
            dsMtrReadTpGrpCdList.add(DS_MTR_READ_TP_GRP.SERVICE_FORCED_READ);
            SVC_PHYS_MTR_READTMsg tMsg = getSvcPhysMtrReadByKey(param.glblCmpyCd.getValue(), line.svcPhysMtrReadPk_O.getValue());
            SvcPhysMtrReadInfoBean bean = NSXC003001SvcPhysMtrRead.getPrevMtrReadInfoExclGrpSq(param.glblCmpyCd.getValue(), line.mtrReadDt.getValue(), line.svcPhysMtrPk.getValue(), dsMtrReadTpGrpCdList, tMsg.svcPhysMtrReadGrpSq.getValue());
            if (bean == null) {
                continue;
            }
            BigDecimal startSvcPhysMtrReadPk = bean.getSvcPhysMtrReadPk();
            BigDecimal endSvcPhysMtrReadPk = line.svcPhysMtrReadPk_O.getValue();
            if (!hasValue(startSvcPhysMtrReadPk) || !hasValue(endSvcPhysMtrReadPk)) {
                continue;
            }
            startRead.setSvcPhysMtrReadPk(startSvcPhysMtrReadPk);
            endRead.setSvcPhysMtrReadPk(endSvcPhysMtrReadPk);
            BigDecimal rollOverExchCnt = mtrCntTwoPntEst.getRollOverExchCnt(param.glblCmpyCd.getValue(), startRead, endRead);
            if (FLG_ON_Y.equals(mtrLbTmsg.totMtrFlg.getValue())) {
                currTotCntTotMtr = currTotCntTotMtr.add(rollOverExchCnt);
            } else {
                currTotCnt = currTotCnt.add(rollOverExchCnt);
            }
            if (lastSvcPhysMtrRead == null) {
                lastSvcPhysMtrRead = getSvcPhysMtrRead(param.glblCmpyCd.getValue(), startSvcPhysMtrReadPk);
            }
            isErrorCheck = true;
        }

        if (!isErrorCheck) {
            return false;
        }

        BigDecimal calcTotMtrCnt = null;
        if (existTotMtr) {
            calcTotMtrCnt = currTotCntTotMtr;
        } else {
            calcTotMtrCnt = currTotCnt;
        }
        if (NSXC003001SvcPhysMtrRead.isOverTotal(param.glblCmpyCd.getValue(), null, param.svcMachMstrPk.getValue(), param.A.no(0).svcPhysMtrPk.getValue(), lastSvcPhysMtrRead.svcPhysMtrReadGrpSq.getValue(), param.A.no(0).mtrReadDt
                .getValue(), calcTotMtrCnt)) {
            setHeaderErrMsg(param, NSZM1373W);
            return true;
        }
        return false;
    }
    // add end 2020/03/03 QC#56123
    // QC#57260 Add Start
    private boolean isVldCounter (NSZC010001_APMsg line) {
        if (ZYPCommonFunc.hasValue(line.svcPhysMtrPk) && svcPhysMtrPkList.contains(line.svcPhysMtrPk.getValue())) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(line.mdlMtrLbCd) && mdlMtrLbCdList.contains(line.mdlMtrLbCd.getValue())) {
            return true;
        }
        return false;
    }
    // QC#57260 Add End

    // START 2022/03/24 R.Jabal [QC#59779, ADD]
    /**
     * Get previous Meter Count
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param svcPhysMtrPk BigDecimal
     * @param mtrReadDt String
     * @param vldMtrFlg String
     * @return BigDecimal
     */
    private BigDecimal getPreviousMeterCount(final String glblCmpyCd, final BigDecimal svcMachMstrPk, final BigDecimal svcPhysMtrPk, final String mtrReadDt, final String vldMtrFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcPhysMtrPk", svcPhysMtrPk);
        ssmParam.put("mtrReadDt", mtrReadDt);
        ssmParam.put("vldMtrFlg", vldMtrFlg);
        ssmParam.put("dsMtrReadTpGrpCd", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        BigDecimal count = (BigDecimal) this.ssmClient.queryObject("getPreviousMeterCount", ssmParam);
        return count;
    }
    // END 2022/03/24 R.Jabal [QC#59779, ADD]

    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
    @SuppressWarnings("unchecked")
    private List<BigDecimal> getBllgMtrSchdBySvcPhysMrReadGrpSq(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcPhysMtrReadGrpSq", svcPhysMtrReadGrpSq);
        return this.ssmClient.queryObjectList("getBllgMtrSchdBySvcPhysMrReadGrpSq", ssmPrm);
    }
    // END 2022/06/22 E.Sanchez [QC#59804, ADD]
}
