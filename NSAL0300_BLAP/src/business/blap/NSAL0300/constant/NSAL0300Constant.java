/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0300.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/10/16   Hitachi         T.Kanasaka      Update          N/A
 * 2015/12/01   Hitachi         T.Kanasaka      Update          QC1271
 * 2015/12/09   Hitachi         T.Kanasaka      Update          QC815
 * 2016/01/15   Hitachi         T.Tomita        Update          QC#3016
 * 2016/01/18   Hitachi         T.Tomita        Update          QC#2948
 * 2016/01/21   Hitachi         T.Tomita        Update          QC#2182
 * 2016/02/09   Hitachi         T.Kanasaka      Update          QC3273
 * 2016/02/12   Hitachi         T.Kanasaka      Update          QC3889
 * 2016/03/03   Hitachi         K.Kasai         Update          QC3018
 * 2016/04/07   Hitachi         M.Gotou         Update          QC#5312,5113
 * 2016/04/26   Hitachi         T.Tomita        Update          QC#3886
 * 2016/05/16   Hitachi         T.Kanasaka      Update          QC#2184
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#7637
 * 2016/05/27   Hitachi         Y.Takeno        Update          QC#447
 * 2016/05/30   Hitachi         K.Kasai         Update          QC#447
 * 2016/07/11   Hitachi         T.Kanasaka      Update          QC#11528
 * 2016/08/02   Hitachi         K.Kishimoto     Update          QC#7402
 * 2016/08/09   Hitachi         T.Kanasaka      Update          QC#4806
 * 2016/09/06   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/27   Hitachi         T.Tomita        Update          QC#11522
 * 2016/11/15   Hitachi         T.Kanasaka      Update          QC#15526
 * 2016/11/22   Hitachi         A.Kohinata      Update          QC#16114
 * 2016/12/26   Hitachi         Y.Takeno        Update          QC#16782
 * 2017/01/12   Hitachi         T.Mizuki        Update          QC#16792
 * 2017/02/07   Hitachi         Y.Takeno        Update          QC#17275
 * 2017/02/10   Hitachi         Y.Takeno        Update          QC#17494
 * 2017/02/21   Hitachi         K.Kishimoto     Update          QC#17646
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/09/01   Hitachi         U.Kim           Update          QC#20856
 * 2017/09/19   Fujitsu         S.Fujita        Update          QC#18534
 * 2017/10/05   Hitachi         K.Kojima        Update          QC#20523
 * 2018/01/12   Hitachi         T.Tomita        Update          QC#18552
 * 2018/01/30   CITS            M.Naito         Update          QC#21349
 * 2018/02/19   Hitachi         K.Kojima        Update          QC#24105
 * 2018/05/14   Hitachi         K.Kitachi       Update          QC#23541
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/07/02   Hitachi         K.Kitachi       Update          QC#26765
 * 2018/07/30   Hitachi         K.Kim           Update          QC#14307(Sol#020)
 * 2018/08/20   Hitachi         T.Tomita        Update          QC#26946
 * 2018/11/07   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/01/09   Hitachi         K.Kitachi       Update          QC#26928
 * 2019/01/17   CITS            M.Naito         Update          QC#29083
 * 2019/02/13   Hitachi         K.Kitachi       Update          QC#30318
 * 2019/11/27   Hitachi         E.Kameishi      Update          QC#54594
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/10/03   Hitachi         N.Takatsu       Update          QC#60186
 * 2022/10/07   Hitachi         N.Takatsu       Update          QC#60071
 * 2024/03/22   Hitachi         Y.Tamai         Update          QC#63463
 *</pre>
 */
public class NSAL0300Constant {

    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0300";

    /**
     * The contract cannot be found. Please confirm the contract No.
     */
    public static final String NAZM0081E = "NAZM0081E";

    /** Failed to update "@". */
    public static final String NSAM0031E = "NSAM0031E";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** Failed to delete "@". */
    public static final String NSAM0033E = "NSAM0033E";

    /** Please select item(s). */
    public static final String NSAM0034E = "NSAM0034E";

    /** [@] is duplicated. */
    public static final String NSAM0035E = "NSAM0035E";

    /** NSAM0045E : @ does not exist. */
    public static final String NSAM0045E = "NSAM0045E";

    /** If the "@" field is entered, please enter the "@" field as well. */
    public static final String NSAM0066E = "NSAM0066E";

    /** The entered '@' does not exist. */
    public static final String NSAM0072E = "NSAM0072E";

    /** Serial # cannot be uniquely specified. */
    public static final String NSAM0128E = "NSAM0128E";

    /**
     * End Date should be >= Start Date of the contract.
     */
    public static final String NSAM0327E = "NSAM0327E";

    /**
     * Base or Overage is required to create a contract.
     */
    public static final String NSAM0328E = "NSAM0328E";

    /**
     * Renewal method is a required field for selected renewal type.
     */
    public static final String NSAM0329E = "NSAM0329E";

    /**
     * #Days before renewal is a required field for selected renewal
     * type.
     */
    public static final String NSAM0330E = "NSAM0330E";

    /**
     * Markup percent for base is required field to create this
     * contract.
     */
    public static final String NSAM0331E = "NSAM0331E";

    /**
     * Markup percent for overage is required field to create this
     * contract.
     */
    public static final String NSAM0332E = "NSAM0332E";

    /**
     * Uplift method is a required field for selected Upliftment type.
     */
    public static final String NSAM0333E = "NSAM0333E";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Date verification error occurred in [@] field (mm/dd/yyyy). */
    public static final String ZZM9010E = "ZZM9010E";

    // START 2022/10/07 N.Takatsu[QC#60071, ADD]
    /** This Filter Condition is only available for Sequence Number. */
    public static final String NSAM0765E = "NSAM0765E";

    /** If you want to filter by sequence number, please enter in the format of 1 or 1.1 or 1-3 */
    public static final String NSAM0766E = "NSAM0766E";

    /** If you want to filter by Config number, please enter in  a number */
    public static final String NSAM0767E = "NSAM0767E";
    
    /** If you want to filter by sequence number, please enter in the format of 1 or 1.1 */
    public static final String NSAM0768E = "NSAM0768E";
    // START 2022/10/07 N.Takatsu[QC#60071, ADD]

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** "@" is invalid. */
    public static final String NSAM0040E = "NSAM0040E";

    /**
     * There is no Machine that makes a connection to Accessory. If
     * you want to make a connection to any Machine, please check the
     * appropriate Machine. If you don't need to make a connection,
     * please again press Add button.
     */
    public static final String NSAM0367W = "NSAM0367W";

    /**
     * If you selected @, it is required to enter @.
     */
    public static final String NSAM0081E = "NSAM0081E";

    /**
     * Relation between "@" and "@" is not correct.
     */
    public static final String NSAM0138E = "NSAM0138E";

    /**
     * If @ is the input, must input @.
     */
    public static final String NSAM0189E = "NSAM0189E";

    /**
     * If you are not Contract author, you cannot edit.
     */
    public static final String NSAM0383E = "NSAM0383E";

    /**
     * In case of new registration, you cannot input Contract No.
     */
    public static final String NSAM0384E = "NSAM0384E";

    /**
     * No details exist.
     */
    public static final String NSAM0385E = "NSAM0385E";

    /**
     * The number of Tier is required to be identical.
     */
    public static final String NSAM0386E = "NSAM0386E";

    /**
     * The selected record has already been invoiced and it cannot be
     * changed.
     */
    public static final String NSAM0387E = "NSAM0387E";

    /**
     * In case of changing to QA Hold, please register Notes in advance. If OK, please click the "Open For Update" button.
     */
    public static final String NSAM0391W = "NSAM0391W";

    /**
     * If you specify @ as base billing cycle, The closing day must be
     * set the Last day.
     */
    public static final String NSZM0657E = "NSZM0657E";

    /**
     * If you select the Base & Usage, please set the same closing day
     * and Base.
     */
    public static final String NSZM0658E = "NSZM0658E";

    /**
     * If you select the Base & Usage, please set the same billing day
     * and Base.
     */
    public static final String NSZM0659E = "NSZM0659E";

    /**
     * The contract detail period can not exceed the period of
     * contract header.
     */
    public static final String NSZM0660E = "NSZM0660E";

    /**
     * The contract detail period can not exceed the period of machine
     * contract.
     */
    public static final String NSZM0661E = "NSZM0661E";

    /**
     * Since the period is overlapped other contract, it can not be
     * registered.
     */
    public static final String NSZM0662E = "NSZM0662E";

    // START 2016/01/18 T.Tomita [QC#2948, DEL]
//    /**
//     * If the contract is lease, usage bill to customer must be the
//     * same account.
//     */
//    public static final String NSZM0664E = "NSZM0664E";
    // END 2016/01/18 T.Tomita [QC#2948, DEL]

    /**
     * Please specify at least one of the Base or Overage the case of
     * the Lease contract.
     */
    public static final String NSZM0665E = "NSZM0665E";

    /**
     * Allocate Across All Machines can be selected only if the
     * contract type is Aggregate.
     */
    public static final String NSZM0666E = "NSZM0666E";

    // START 2018/01/30 M.Naito [QC#21349, DEL]
//    /**
//     * If the Contract Type is Aggregate, You must select Bill Base &
//     * Usage Together.
//     */
//    public static final String NSZM0667E = "NSZM0667E";
    // END 2018/01/30 M.Naito [QC#21349, DEL]

    /**
     * You must set either the Bill To Location or Lease Company.
     */
    public static final String NSZM0668E = "NSZM0668E";

    /**
     * In Warranty, Lease Company can not be set.
     */
    public static final String NSZM0669E = "NSZM0669E";

    /**
     * You can set only one of the following items:Minimum Volume,
     * Minimum Amount, Free Copies, Rollover%.
     */
    public static final String NSZM0681E = "NSZM0681E";

    /**
     * Allowance must be set 0 when Minimum volume is set.
     */
    public static final String NSZM0682E = "NSZM0682E";

    // START 2016/05/23 M.Gotou [QC#7637, DEL]
//    /**
//     * Only tier1 rate can be set when Minimum amount is set.
//     */
//    public static final String NSZM0683E = "NSZM0683E";
    // END 2016/05/23 M.Gotou [QC#7637, DEL]

    /**
     * [@] cannot be set if you don't select [@].
     */
    public static final String NSZM0684E = "NSZM0684E";

    /**
     * The combination of [@] and [@] is incorrect.
     */
    public static final String NSZM0698E = "NSZM0698E";

    // START 2016/01/21 T.Tomita [QC#2182, ADD]
    /**
     * There are multiple @. Please enter @.
     */
    public static final String NSAM0418E = "NSAM0418E";

    /**
     * Relationships are incorrect among LOB, Branch and Rep. If OK, please click the "Save" button.
     */
    public static final String NSAM0419W = "NSAM0419W";
    // END 2016/01/21 T.Tomita [QC#2182, ADD]

    // add start 2016/03/03 CSA Defect#3018
    /**
     * Cannot select more than "@" lines.
     */
    public static final String NSAM0141E = "NSAM0141E";
    // add end 2016/03/03 CSA Defect#3018

    // START 2016/05/23 M.Gotou [QC#7637, ADD]
    /**
     * Only tier 1 rate can be set when @ is set.
     */
    public static final String NSAM0477E = "NSAM0477E";
    // END 2016/05/23 M.Gotou [QC#7637, ADD]

    // START 2016/05/27 [QC#447, ADD]
    /**
     * The EOL has passed. Can not add the Serial# {@}.
     */
    public static final String NSAM0478E = "NSAM0478E";
    // END 2016/05/27 [QC#447, ADD]

    // Add Start 08/02/2016 <QC#7402>
    /**
     * This contract has already billed $[@]. Please enter billed amount or more.
     */
    public static final String NSAM0600E = "NSAM0600E";
    // Add End   08/02/2016 <QC#7402>

    // START 2016/09/27 T.Tomita [QC#11522, ADD]
    /**
     * Can't transition screen because usage charge doesn't exists.
     */
    public static final String NSAM0609E = "NSAM0609E";
    // END 2016/09/27 T.Tomita [QC#11522, ADD]

    // Add Start 2016/11/10 <QC#15888>
    /**
     * You can not enter [@] earlier than [@].
     */
    public static final String NSAM0346E = "NSAM0346E";
    // Add End   2016/11/10 <QC#15888>

    // add start 2016/11/22 CSA Defect#16114
    /**
     * The selected data cannot be deleted since the price effectivity
     * is already invoiced.
     */
    public static final String NSAM0368E = "NSAM0368E";
    // add end 2016/11/22 CSA Defect#16114
    // START 2017/02/21 K.Kishimoto [QC#17646, ADD]
    /**
     * allowance must be greater than above
     */
    public static final String NSAM0623E = "NSAM0623E";
    // END   2017/02/21 K.Kishimoto [QC#17646, ADD]
    
    // START 2017/09/01 U.Kim [QC#20856, ADD]
    /**
     * There is no Machine that makes a connection to Accessory. If
     * you want to make a connection to any Machine, please check the
     * appropriate Machine. 
     */
    public static final String NSAM0697E = "NSAM0697E";
    // END 2017/09/01 U.Kim [QC#20856, ADD]

    // Add Start 2018/01/12 QC#18552
    /**
     * This value of Invoice Date can't select if you select the Bill All Base & Usage Together.
     */
    public static final String NSAM0711E = "NSAM0711E";
    // Add End 2018/01/12 QC#18552

    // START 2018/05/14 K.Kitachi [QC#23541, ADD]
    /**
     * Allowance must be set 0 when Minimum amount is set.
     */
    public static final String NSAM0720E = "NSAM0720E";

    /**
     * Allowance must be not set 0 when Rollover% is set.
     */
    public static final String NSAM0721E = "NSAM0721E";
    // END 2018/05/14 K.Kitachi [QC#23541, ADD]

    // Add Start 2018/08/20 QC#26946
    /** This Service Program cannot be specified for Non Fleet and Fleet, Aggregate Contract. */
    public static final String NSAM0734E = "NSAM0734E";

    /** This Service Program cannot be specified for Warranty Contract. */
    public static final String NSAM0735E = "NSAM0735E";
    // Add End 2018/08/20 QC#26946

    // START 2018/07/02 K.Kitachi [QC#26765, ADD]
    /**
     * You select CFS in EDI, leasing company input is mandatory.
     */
    public static final String NSAM0727E = "NSAM0727E";

    /**
     * You select CFS in EDI, but leasing company you entered is not CFS.
     */
    public static final String NSAM0728E = "NSAM0728E";

    /**
     * The company you entered is not a leasing company.
     */
    public static final String NSAM0729E = "NSAM0729E";
    // END 2018/07/02 K.Kitachi [QC#26765, ADD]

    // START 2019/01/09 K.Kitachi [QC#26928, ADD]
    /**
     * [@] must be greater than or equal to [@].
     */
    public static final String NSAM0743E = "NSAM0743E";
    // END 2019/01/09 K.Kitachi [QC#26928, ADD]

    // START 2019/01/17 M.Naito [QC#29083,ADD];
    // START 2019/02/13 K.Kitachi [QC#30318, DEL]
//    /**
//     * '@ is not in contract detail effective period.
//     */
//    public static final String NSAM0157E = "NSAM0157E";
    // END 2019/02/13 K.Kitachi [QC#30318, DEL]
    // END 2019/01/17 M.Naito [QC#29083,ADD];

    /** Add Detai Default Value of Date */
    public static final String INIT_DAY = "0";

    public static final String LAST_DAY_OF_A_MONTH = "99";

    // START 2022/10/03 N.Takatsu[QC#60186, ADD]
    public static final String END_OF_CURRENT_MONTH = "1";
    // END 2022/10/03 N.Takatsu[QC#60186, ADD]

    public static final String LAST_DAY = "Last Day";

    public static final String RADIO_DAY_OTHER = "1";

    public static final String RADIO_LAST_DAY_OF_A_MONTH = "2";

    public static final String DEF_DS_CONTR_SQ_NUM = "00";

    public static final String DEF_DS_CONTR_CATG_CD = DS_CONTR_CATG.REGULAR;

    public static final String DEF_DS_CONTR_TP_CD = DS_CONTR_TP.SERVICE;

    public static final String DEF_DS_CONTR_DTL_TP_CD = DS_CONTR_DTL_TP.BASE_AND_USAGE;

    public static final String DEF_BLLG_TMG_TP_CD = BLLG_TMG_TP.ARREARS;

    // START 2016/12/26 [QC#16782, MOD]
    // public static final String DEF_BASE_BLLG_TMG_TP_CD = BLLG_TMG_TP.ARREARS;
    public static final String DEF_BASE_BLLG_TMG_TP_CD = BLLG_TMG_TP.ADVANCE;
    // END   2016/12/26 [QC#16782, MOD]

    public static final String DEF_MTR_BLLG_TMG_TP_CD = BLLG_TMG_TP.ARREARS;

    public static final BigDecimal INVLD_DS_CONTR_BLLG_MTR_PK = BigDecimal.ONE.negate();

    public static final String INVLD_SER_NUM = "XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4";

    public static final String INVLD_MDSE_CD = "XXXXXXXXX1XXXXXXXXX2";

    public static boolean INPUT_IS_VALID = true;

    public static boolean INPUT_IS_INVALID = false;

    /** Numbering UniqueID DS_CONTR_NUM */
    public static final String DS_CONTR_NUM = "DS_CONTR_NUM";

    /** Display Mode : Update */
    public static final String DISPLAY_MODE_UPDATE = "1";

    /** Display Mode : Freeze */
    public static final String DISPLAY_MODE_FREEZE = "0";

    /** NumConst : DEF_CONTR_UPLFT_TERM_AOT */
    public static final String NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT = "DEF_CONTR_UPLFT_TERM_AOT";

    /** NumConst Default Value: DEF_CONTR_UPLFT_TERM_AOT */
    public static final BigDecimal DEF_CONTR_UPLFT_TERM_AOT = new BigDecimal(5);

    /** NumConst : QA_HOLD_ENTRY_TM_AOT */
    public static final String NUM_CONST_QA_HOLD_ENTRY_TM_AOT = "QA_HOLD_ENTRY_TM_AOT";

    /** NumConst Default Value: QA_HOLD_ENTRY_TM_AOT */
    public static final BigDecimal DEF_QA_HOLD_ENTRY_TM_AOT = new BigDecimal(30);

    // add start 2016/03/03 CSA Defect#3018
    /** NumConst: NSAL0300_TERMS_LMT_CNT */
    public static final String NSAL0300_TERMS_LMT_CNT = "NSAL0300_TERMS_LMT_CNT";

    /** NumConst Default Value: NSAL0300_TERMS_LMT_CNT */
    public static final BigDecimal DEF_NSAL0300_TERMS_LMT_CNT = new BigDecimal(50);
    // add end 2016/03/03 CSA Defect#3018

    /** Date Format: yyyyMMddHHmmssSSS */
    public static final String DATE_FORMAT_FULL = "yyyyMMddHHmmssSSS";

    /** Date Format: yyyyMMdd */
    public static final String DATE_FORMAT_DATE = "yyyyMMdd";

    /** Summary/Detail Mode : Summary */
    public static final String SUMMARY_MODE = "0";

    /** Summary/Detail Mode : Detail */
    public static final String DETAIL_MODE = "1";

    /** Date Format: Default Effective Through Date */
    public static final String DEF_EFF_THRU_DT = "29991231";

    /** Map Key: SER_NUM */
    public static final String MAP_KEY_SER_NUM = "MAP_KEY_SER_NUM";

    /** Map Key: MDSE_CD */
    public static final String MAP_KEY_MDSE_CD = "MAP_KEY_MDSE_CD";

    /** Map Key: DS_CONTR_DTL_PK */
    public static final String MAP_KEY_DS_CONTR_DTL_PK = "MAP_KEY_DS_CONTR_DTL_PK";

    // START 2016/01/15 T.Tomita [QC#3016, ADD]
    /** VarCharConst : WTY_CONTR_CATG_CD */
    public static final String WTY_CONTR_CATG_CD = "WTY_CONTR_CATG_CD";
    // END 2016/01/15 T.Tomita [QC#3016, ADD]

    // START 2016/05/16 T.Kanasaka [QC#2184, ADD]
    // del start 2017/08/08 QC#18799
//    /** VarCharConst : DEF_CONTR_PER_END_DAY */
//    public static final String DEF_CONTR_PER_END_DAY = "DEF_CONTR_PER_END_DAY";
//
//    /** VarCharConst : DEF_CONTR_INV_DAY */
//    public static final String DEF_CONTR_INV_DAY = "DEF_CONTR_INV_DAY";
    // del end 2017/08/08 QC#18799
    // END 2016/05/16 T.Kanasaka [QC#2184, ADD]

    // START 2016/02/09 T.Kanasaka [QC3273, MOD]
    /** Rollover100% */
    public static final BigDecimal ROLL_OVER_MAX = new BigDecimal(100);
    // END 2016/02/09 T.Kanasaka [QC3273, MOD]

    // add start 2016/04/07 CSA Defect#5312,5313
    /** Divide String  */
    public static final String DIV_STR = "-";

    /** LOB_OR_BR */
    public static final String LOB_OR_BR = "LOB or Branch";

    /** Rep Name */
    public static final String REP_NAME = "Branch Rep";

    // add end 2016/04/07 CSA Defect#5312,5313

    // START 2016/04/26 T.Tomita [QC#3886, ADD]
    /** Percent : % */
    public static final String PERCENT = "%";
    // END 2016/04/26 T.Tomita [QC#3886, ADD]

    /** Period : . */
    public static final String PERIOD = ".";

    /** Comma : % */
    public static final String COMMA = ",";

    /** Asterisk : * */
    public static final String ASTERISK = "*";

    /** Question : ? */
    public static final String QUESTION = "?";

    /** Underscore : _ */
    public static final String UNDERSCORE = "_";

    // START 2022/10/07 N.Takatsu[QC#60071, ADD]
    /** Period : . */
    public static final String TXT_HYPHEN = "-";

    /** Period : . */
    public static final String DEC_000 = "000";
    
    /** Period : . */
    public static final String DEC_999 = "999";

    /** Period : . */
    public static final String CONFIG_NUMBER_CHECK = "^[0-9]*";

    /** Period : . */
    public static final String SIMPLE_SUBSTANCE_CHECK = "^[[0-9]|.]*";

    /** Period : . */
    public static final String RANGE_CHECK = "^[[[0-9]|-]|.]*";
    // END 2022/10/07 N.Takatsu[QC#60071, ADD]
    
    /** Slash : / */
    public static final String SLASH = "/";

    // add start 2016/05/30 CSA Defect#447
    /** Constant name */
    public static final String TERM_COND_RSP_TM_MEAS_PER = "TERM_COND_RSP_TM_MEAS_PER";

    /** Constant name */
    public static final String TERM_COND_RSP_TM_RMD_1 = "TERM_COND_RSP_TM_RMD_1";

    /** Constant name */
    public static final String TERM_COND_RSP_TM_RMD_2 = "TERM_COND_RSP_TM_RMD_2";

    /** Constant name */
    public static final String TERM_COND_MAX_CMBN_SLA_RMD = "TERM_COND_MAX_CMBN_SLA_RMD";

    /** Constant name */
    public static final String TERM_COND_RSP_TM_COMIT = "TERM_COND_RSP_TM_COMIT";
    // add end 2016/05/30 CSA Defect#447

    /** Expand/Cllapse Icon Close */
    public static final String IMG_CLOSE_ARROW = "./img/biz/rightarrow2.png";

    /** Expand/Cllapse Icon Open */
    public static final String IMG_OPEN_ARROW = "./img/biz/downarrow2.png";

    /** Expand(Add)/Cllapse(Del) Icon Close Red */
    public static final String IMG_CLOSE_MACHINE_RED = "./img/biz/add-red.png";

    /** Expand(Add)/Cllapse(Del) Icon Close Green */
    public static final String IMG_CLOSE_MACHINE_GREEN = "./img/biz/add.png";

    /** Expand(Add)/Cllapse(Del) Icon Open Red */
    public static final String IMG_OPEN_MACHINE_RED = "./img/biz/delete.png";

    /** Expand(Add)/Cllapse(Del) Icon Open Green */
    public static final String IMG_OPEN_MACHINE_GREEN = "./img/biz/delete-green.png";

    /** Machine List Icon Close */
    public static final String IMG_CLOSE_MACHINE_ALL = "./img/wfcomp/S21WfPlus.gif";

    /** Machine List Icon Open */
    public static final String IMG_OPEN_MACHINE_ALL = "./img/wfcomp/S21WfMinus.gif";

    /** Filter Item: Serial# */
    public static final String FILTER_ITEM_SERIAL = "01";

    /** Filter Item: Model */
    public static final String FILTER_ITEM_MODEL = "02";

    /** Filter Item: Location */
    public static final String FILTER_ITEM_LOCATION = "03";

    /** Filter Item: Start Date */
    public static final String FILTER_ITEM_START_DATE = "04";

    // START 2022/10/07 N.Takatsu[QC#60071, ADD]
    /** Filter Item: Config # */
    public static final String FILTER_ITEM_CONFIG_NUMBER = "05";

    /** Filter Item: Line Status */
    public static final String FILTER_ITEM_LINE_STATUS = "06";

    /** Filter Item: Item Code */
    public static final String FILTER_ITEM_ITEM_CODE = "07";

    /** Filter Item: Sequence # */
    public static final String FILTER_ITEM_SEQUENCE_NUMBER = "08";
    // END 2022/10/07 N.Takatsu[QC#60071, ADD]

    /** Filter Condition: = */
    public static final String FILTER_CONDITION_EQUAL = "01";

    /** Filter Condition: > */
    public static final String FILTER_CONDITION_GREATER_THAN = "02";

    // START 2016/11/15 T.Kanasaka [QC#15526, MOD]
    /** Filter Condition: Contains */
    public static final String FILTER_CONDITION_CONTAINS = "03";

    /** Filter Condition: < */
    public static final String FILTER_CONDITION_LESS_THAN = "04";

    /** Filter Condition: <> */
    public static final String FILTER_CONDITION_NOT_EQUAL = "05";
    
    // START 2022/10/07 N.Takatsu[QC#60071, MOD]
    /** Filter Condition: From-To (only Seq.#) */
    public static final String FILTER_CONDITION_FROM_TO = "06";

    /** Filter Item */
//    public static final String[][] FILTER_ITEM = {{FILTER_ITEM_SERIAL, "Serial#" }, {FILTER_ITEM_MODEL, "Model" }, {FILTER_ITEM_LOCATION, "Location" }, {FILTER_ITEM_START_DATE, "Start Date" } };
    public static final String[][] FILTER_ITEM = {{FILTER_ITEM_SERIAL, "Serial#" }, {FILTER_ITEM_MODEL, "Model" }, {FILTER_ITEM_LOCATION, "Location" }, {FILTER_ITEM_START_DATE, "Start Date" }, {FILTER_ITEM_CONFIG_NUMBER, "Config Number" }, {FILTER_ITEM_LINE_STATUS, "Line Status" }, {FILTER_ITEM_ITEM_CODE, "Item Code" }, {FILTER_ITEM_SEQUENCE_NUMBER, "Sequence Number" } };

    /** Filter Condition */
//    public static final String[][] FILTER_CONDITION = {{FILTER_CONDITION_EQUAL, "=" }, {FILTER_CONDITION_GREATER_THAN, ">" }, {FILTER_CONDITION_CONTAINS, "Contains" }, {FILTER_CONDITION_LESS_THAN, "<" }, {FILTER_CONDITION_NOT_EQUAL, "<>" } };
    public static final String[][] FILTER_CONDITION = {{FILTER_CONDITION_EQUAL, "=" }, {FILTER_CONDITION_GREATER_THAN, ">" }, {FILTER_CONDITION_CONTAINS, "Contains" }, {FILTER_CONDITION_LESS_THAN, "<" }, {FILTER_CONDITION_NOT_EQUAL, "<>" }, {FILTER_CONDITION_FROM_TO, "From-To (only Seq.#)" } };
    // END 2022/10/07 N.Takatsu[QC#60071, MOD]
    // END 2016/11/15 T.Kanasaka [QC#15526, MOD]

    // add start 2016/09/06 CSA Defect#11243
    /** Machine Level Number (1) */
    public static final String MACH_LVL_NUM_1 = "1";
    // add end 2016/09/06 CSA Defect#11243

    // mod start 2017/01/12 CSA QC#16792
    /**
     * Function Id: Inquiry
     */
    public static final String FUNC_ID_INQ = "NSAL0300T010";

    /**
     * Function Id: Update
     */
    public static final String FUNC_ID_UPD = "NSAL0300T020";

    /**
     * Function Id: ShipTo
     */
    public static final String FUNC_ID_SHIP_TO = "NSAL0300T030";
    // mod end 2017/01/12 CSA QC#16792

 // START 2017/02/02 N.Arai [QC#17197, MOD]
    /** Fetch size for Download */
    public static final int FETCH_SIZE_MAX = 1000;

    /** LIMIT_DOWNLOAD */
    public static final int LIMIT_DOWNLOAD = 2000;

    /** MachineDetails */
    public static final String MACHINE_DETAILS = "MachineDetails";

    /** No search results found. */
    public static final String ZZZM9001E = "ZZZM9001E";
 // END 2017/02/02 N.Arai [QC#17197, MOD]

    // START 2017/02/07 [QC#17494, ADD]
    /**
     * FMT_MTR_MULT_RATE
     */
    public static final String FMT_MTR_MULT_RATE = "(%.2f)";

    /**
     * MAX_LENGTH_COUNTER_NAME
     */
    public static final int MAX_LENGTH_COUNTER_NAME = 4000;
    // END    2017/02/07 [QC#17494, ADD]

    // START 2017/09/19 S.Fujita [QC#18534,ADD]
    /** "CSA Warranty" cannot be specified as Service Program for Fleet and Aggregate Contract. */
    public static final String NSAM0698E = "NSAM0698E";
    // END 2017/09/19 S.Fujita [QC#18534,ADD]

    // START 2017/10/05 K.Kojima [QC#20523,ADD]
    /** Level Number : 1 */
    public static final String LVL_NUM_1 = "1";

    /** Level Number : 1 */
    public static final String LVL_NUM_2 = "2";

    /** Level Number : 1 */
    public static final String LVL_NUM_3 = "3";
    // END 2017/10/05 K.Kojima [QC#20523,ADD]

    // START 2018/02/19 K.Kojima [QC#24105,ADD]
    /** VAR_CHAR_CONST: PND_ISTL_CONTR_STS_NM */
    public static final String PND_ISTL_CONTR_STS_NM = "PND_ISTL_CONTR_STS_NM";

    /** VAR_CHAR_CONST: ACTV_PENDING_PO_CONTR_STS_NM */
    public static final String ACTV_PENDING_PO_CONTR_STS_NM = "ACTV_PENDING_PO_CONTR_STS_NM";

    /** VAR_CHAR_CONST: ACTV_RENEWAL_HOLD_CONTR_STS_NM */
    public static final String ACTV_RENEWAL_HOLD_CONTR_STS_NM = "ACTV_RENEWAL_HOLD_CONTR_STS_NM";
    // END 2018/02/19 K.Kojima [QC#24105,ADD]

    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /** VAR_CHAR_CONST: ALL_PER_TRMN_CONTR_STS_NM */
    public static final String ALL_PER_TRMN_CONTR_STS_NM = "ALL_PER_TRMN_CONTR_STS_NM";
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]

    // START 2018/06/18 K.Kitachi [QC#18591, ADD]
    /** VAR_CHAR_CONST: DUMMY_EML_ADDR */
    public static final String DUMMY_EML_ADDR = "DUMMY_EML_ADDR";
    // END 2018/06/18 K.Kitachi [QC#18591, ADD]

    // START 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]
    /** Function Category ID for Check Special Instruction */
    public static final String FUNC_CATG_ID = "CONTRACT";
    // END 2018/07/30 K.Kim [QC#14307(Sol#020), ADD]

    // START 2019/01/17 M.Naito [QC#29083,ADD];
    /** VAR_CHAR_CONST: CUM_COPY_FREQ_MTH */
    public static final String CUM_COPY_FREQ_MTH = "CUM_COPY_FREQ_MTH";
    // END 2019/01/17 M.Naito [QC#29083,ADD];

    // add start 2018/11/07 K.Fujimoto QC#28627
    /**
     * The parent contract # [@] does not exist.
     */
    public static final String NSZM1355E = "NSZM1355E";

    /**
     * The parent contract # [@] is not active.
     */
    public static final String NSZM1356E = "NSZM1356E";

    /**
     * Parent Contract's Link# must set its own Contract#.
     */
    public static final String NSZM1357E = "NSZM1357E";

    /**
     * Link contract # [@] Bill To Mis-Match with parent contract # [@] Bill To.
     */
    public static final String NSZM1358E = "NSZM1358E";

    /**
     * The link # cannot be changed since the contract has some child contracts.
     * If you want to change the link #, please clear a link # of each child contract in advance.
     */
    public static final String NSZM1359E = "NSZM1359E";
    // add end   2018/11/07 K.Fujimoto QC#28627
    // START 2019/11/27 E.Kameishi [QC#54594, ADD]
    /** Asterisk 4 digits : * */
    public static final String ASTERISK_4 = "****";
    // END 2019/11/27 E.Kameishi [QC#54594, ADD]

    // START 2024/03/22 Y.Tamai [QC#63463, ADD]    
    /** TBL_ALIAS */
    public static final String TBL_ALIAS = "A";

    /** DS_CONTR_PK */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** DS_CONTR_DTL_PK */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /** DS_CONTR_ADDL_CHRG_PK */
    public static final String DS_CONTR_ADDL_CHRG_PK = "DS_CONTR_ADDL_CHRG_PK";

    /** DS_CONTR_BANK_ACCT_RELN_PK */
    public static final String DS_CONTR_BANK_ACCT_RELN_PK = "DS_CONTR_PK";

    /** DS_CONTR_BR_ALLOC_PK */
    public static final String DS_CONTR_BR_ALLOC_PK = "DS_CONTR_BR_ALLOC_PK";

    /** DS_CONTR_CR_CARD_PO_PK */
    public static final String DS_CONTR_CR_CARD_PO_PK = "DS_CONTR_CR_CARD_PO_PK";

    /** DS_CONTR_RNW_ESCL_PK */
    public static final String DS_CONTR_RNW_ESCL_PK = "DS_CONTR_RNW_ESCL_PK";

    /** DS_CONTR_SEG_ALLOC_PK */
    public static final String DS_CONTR_SEG_ALLOC_PK = "DS_CONTR_SEG_ALLOC_PK";

    /** SVC_TERM_COND_PK */
    public static final String SVC_TERM_COND_PK = "SVC_TERM_COND_PK";

    /** CONTR_PHYS_BLLG_MTR_RELN_PK */
    public static final String CONTR_PHYS_BLLG_MTR_RELN_PK = "CONTR_PHYS_BLLG_MTR_RELN_PK";

    /** DS_CONTR_BLLG_MTR_PK */
    public static final String DS_CONTR_BLLG_MTR_PK = "DS_CONTR_BLLG_MTR_PK";

    /** DS_CONTR_BLLG_SCHD_PK */
    public static final String DS_CONTR_BLLG_SCHD_PK = "DS_CONTR_BLLG_SCHD_PK";

    /** DS_CONTR_BLLG_SCHD_SMRY_PK */
    public static final String DS_CONTR_BLLG_SCHD_SMRY_PK = "DS_CONTR_BLLG_SCHD_SMRY_PK";

    /** DS_CONTR_PRC_ALLOC_PK */
    public static final String DS_CONTR_PRC_ALLOC_PK = "DS_CONTR_PRC_ALLOC_PK";

    /** DS_CONTR_PRC_EFF_PK */
    public static final String DS_CONTR_PRC_EFF_PK = "DS_CONTR_PRC_EFF_PK";

    /** DS_CONTR_SKIP_RECOV_PK */
    public static final String DS_CONTR_SKIP_RECOV_PK = "DS_CONTR_SKIP_RECOV_PK";

    /** DS_SUB_CONTR_PK */
    public static final String DS_SUB_CONTR_PK = "DS_SUB_CONTR_PK";

    /** CONTR_XS_COPY_PK */
    public static final String CONTR_XS_COPY_PK = "CONTR_XS_COPY_PK";

    /** DS_CONTR_BLLG_SCHD_MTR_PK */
    public static final String DS_CONTR_BLLG_SCHD_MTR_PK = "DS_CONTR_BLLG_SCHD_MTR_PK";

    /** DS_CONTR_PRC_EFF_MTR_PK */
    public static final String DS_CONTR_PRC_EFF_MTR_PK = "DS_CONTR_PRC_EFF_MTR_PK";

    /** DS_SUB_CONTR_MTR_PK */
    public static final String DS_SUB_CONTR_MTR_PK = "DS_SUB_CONTR_MTR_PK";

    /** SVC_TERM_COND_BAK */
    public static final String SVC_TERM_COND_BAK = "SVC_TERM_COND_BAK";

    /** CONTR_ADDL_CHRG_BAK */
    public static final String CONTR_ADDL_CHRG_BAK = "CONTR_ADDL_CHRG_BAK";

    /** CONTR_BANK_ACCT_RELN_BAK */
    public static final String CONTR_BANK_ACCT_RELN_BAK = "CONTR_BANK_ACCT_RELN_BAK";

    /** CONTR_BR_ALLOC_BAK */
    public static final String CONTR_BR_ALLOC_BAK = "CONTR_BR_ALLOC_BAK";

    /** CONTR_CR_CARD_PO_BAK */
    public static final String CONTR_CR_CARD_PO_BAK = "CONTR_CR_CARD_PO_BAK";

    /** CONTR_DTL_BAK */
    public static final String CONTR_DTL_BAK = "CONTR_DTL_BAK";

    /** CONTR_RNW_ESCL_BAK */
    public static final String CONTR_RNW_ESCL_BAK = "CONTR_RNW_ESCL_BAK";

    /** CONTR_SEG_ALLOC_BAK */
    public static final String CONTR_SEG_ALLOC_BAK = "CONTR_SEG_ALLOC_BAK";

    /** CONTR_PHYS_BLLG_MTR_BAK */
    public static final String CONTR_PHYS_BLLG_MTR_BAK = "CONTR_PHYS_BLLG_MTR_BAK";

    /** CONTR_BLLG_MTR_BAK */
    public static final String CONTR_BLLG_MTR_BAK = "CONTR_BLLG_MTR_BAK";

    /** CONTR_BLLG_SCHD_BAK */
    public static final String CONTR_BLLG_SCHD_BAK = "CONTR_BLLG_SCHD_BAK";

    /** CONTR_BLLG_SCHD_SMRY_BAK */
    public static final String CONTR_BLLG_SCHD_SMRY_BAK = "CONTR_BLLG_SCHD_SMRY_BAK";

    /** CONTR_PRC_ALLOC_BAK */
    public static final String CONTR_PRC_ALLOC_BAK = "CONTR_PRC_ALLOC_BAK";

    /** CONTR_PRC_EFF_BAK */
    public static final String CONTR_PRC_EFF_BAK = "CONTR_PRC_EFF_BAK";

    /** CONTR_SKIP_RECOV_BAK */
    public static final String CONTR_SKIP_RECOV_BAK = "CONTR_SKIP_RECOV_BAK";

    /** SUB_CONTR_BAK */
    public static final String SUB_CONTR_BAK = "SUB_CONTR_BAK";

    /** CONTR_XS_COPY_BAK */
    public static final String CONTR_XS_COPY_BAK = "CONTR_XS_COPY_BAK";

    /** CONTR_BLLG_SCHD_MTR_BAK */
    public static final String CONTR_BLLG_SCHD_MTR_BAK = "CONTR_BLLG_SCHD_MTR_BAK";

    /** CONTR_PRC_EFF_MTR_BAK */
    public static final String CONTR_PRC_EFF_MTR_BAK = "CONTR_PRC_EFF_MTR_BAK";

    /** SUB_CONTR_MTR_BAK */
    public static final String SUB_CONTR_MTR_BAK = "SUB_CONTR_MTR_BAK";

    /** VAR_ITEM_START_POSN_FOR_BACKUP */
    public static final int VAR_ITEM_START_POSN_FOR_BACKUP = 14;

    /** VAR_ITEM_START_POSN_FOR_BACKUP_SOURCE */
    public static final int VAR_ITEM_START_POSN_FOR_BACKUP_SOURCE = 13;

    /** 
     * Cannot revert the contract. Please activate it manually if need.
     */
    public static final String NSAM0794E = "NSAM0794E";
    // END 2024/03/22 Y.Tamai [QC#63463, ADD]
}
