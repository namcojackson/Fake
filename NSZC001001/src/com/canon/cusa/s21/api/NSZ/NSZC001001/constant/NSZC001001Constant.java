/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC001001.constant;

import java.math.BigDecimal;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;

/**
 * <pre>
 * Machine Master Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/01/2015   Hitachi         T.Tsuchida      Update          NA#CSA
 * 2015/12/09   Hitachi         T.Tomita        Update          CSA QC#951
 * 2016/07/11   Hitachi         T.Tomita        Update          CSA QC#446
 * 2016/08/03   Hitachi         T.Tomita        Update          CSA QC#12230
 * 2016/12/14   Fujitsu         T.Yoshida       Update          CSA QC#16416
 * 2017/09/14   Fujitsu         S.Fujita        Update          QC#18534
 * 2017/10/16   Hitachi         M.Kidokoro      Update          QC#20246
 * 2017/11/20   Hitachi         K.Ochiai        Update          QC#21698
 * 2018/06/19   Hitachi         T.Tomita        Update          QC#26618
 * 2018/08/09   CITS            T.Wada          Update          QC#27595
 * 2019/11/14   Hitachi         K.Kitachi       Update          QC#54327
 * 2022/07/13   Hitachi         N.Takatsu       Update          QC#60023
 * 2022/07/27   Hitachi         N.Takatsu       Update          QC#60023
 * 2024/04/04   CITS            T.Aizawa        Update          QC#54186
 * </pre>
 */
public final class NSZC001001Constant {

    /**
     * Input parameter "Global Company Code" is a mandatory field.
     */
    public static final String NSZM0001E = "NSZM0001E";

    /**
     * Input parameter "Sales Date" is a mandatory field.
     */
    public static final String NSZM0002E = "NSZM0002E";

    /**
     * Input parameter "Process Mode" is a mandatory field.
     */
    public static final String NSZM0003E = "NSZM0003E";

    /**
     * The input is not a defined "Process Mode".
     */
    public static final String NSZM0004E = "NSZM0004E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Service configuration master is not found.
//     */
//    public static final String NSZM0005E = "NSZM0005E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Service machine master is not found.
     */
    public static final String NSZM0006E = "NSZM0006E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * The serial number is already registered.
//     */
//    public static final String NSZM0007E = "NSZM0007E";
//
//    /**
//     * The machine control number is already registered.
//     */
//    public static final String NSZM0008E = "NSZM0008E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Event pattern cannot be identified.
     */
    public static final String NSZM0009E = "NSZM0009E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Service Configuration Master ID" is a mandatory field.
//     */
//    public static final String NSZM0010E = "NSZM0010E";
//
//    /**
//     * Input parameter "Service Machine Master ID" is a mandatory field.
//     */
//    public static final String NSZM0011E = "NSZM0011E";
//
//    /**
//     * Input parameter "Serial Number" is a mandatory field.
//     */
//    public static final String NSZM0012E = "NSZM0012E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Merchandise Code" is a mandatory field.
     */
    public static final String NSZM0013E = "NSZM0013E";

    /**
     * Input parameter "Service Machine Master Status Code" is a mandatory field.
     */
    public static final String NSZM0014E = "NSZM0014E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Ship To Code" is a mandatory field.
//     */
//    public static final String NSZM0017E = "NSZM0017E";
//
//    /**
//     * Input parameter "Install Location Override Flag" is a mandatory
//     * field.
//     */
//    public static final String NSZM0018E = "NSZM0018E";
//
//    /**
//     * Input parameter "Location Name" is a mandatory field.
//     */
//    public static final String NSZM0019E = "NSZM0019E";
//
//    /**
//     * Input parameter "First Line Address" is a mandatory field.
//     */
//    public static final String NSZM0020E = "NSZM0020E";
//
//    /**
//     * Input parameter "City Address" is a mandatory field.
//     */
//    public static final String NSZM0021E = "NSZM0021E";
//
//    /**
//     * Input parameter "State Code" is a mandatory field.
//     */
//    public static final String NSZM0022E = "NSZM0022E";
//
//    /**
//     * Input parameter "Postal Code" is a mandatory field.
//     */
//    public static final String NSZM0023E = "NSZM0023E";
//
//    /**
//     * Input parameter "Country Code" is a mandatory field.
//     */
//    public static final String NSZM0024E = "NSZM0024E";
//
//    /**
//     * Input parameter "Shipped From WH Code" is a mandatory field.
//     */
//    public static final String NSZM0026E = "NSZM0026E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Shipping Order Number" is a mandatory field.
     */
    public static final String NSZM0027E = "NSZM0027E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Shipped Date" is a mandatory field.
//     */
//    public static final String NSZM0028E = "NSZM0028E";
//
//    /**
//     * Input parameter "Installed Date" is a mandatory field.
//     */
//    public static final String NSZM0029E = "NSZM0029E";
//
//    /**
//     * Input parameter "Removed Date" is a mandatory field.
//     */
//    public static final String NSZM0030E = "NSZM0030E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Service Machine Type" is a mandatory field.
     */
    public static final String NSZM0031E = "NSZM0031E";

    /**
     * Failed to create a Service Machine Master record.
     */
    public static final String NSZM0034E = "NSZM0034E";

    /**
     * Failed to create a Service Configuration Master record.
     */
    public static final String NSZM0035E = "NSZM0035E";

    /**
     * Failed to update a Service Machine Master record.
     */
    public static final String NSZM0036E = "NSZM0036E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Main machine is already registered in the configuration.
//     */
//    public static final String NSZM0037E = "NSZM0037E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Failed to update a Service Configuration Master record.
     */
    public static final String NSZM0038E = "NSZM0038E";

    // START 2019/11/14 K.Kitachi [QC#54327, ADD]
    /**
     * Input parameter "Service Machine Master PK" is a mandatory field.
     */
    public static final String NSZM0074E = "NSZM0074E";
    // END 2019/11/14 K.Kitachi [QC#54327, ADD]

    /**
     * The entered 'Model Name' does not exist.
     */
    public static final String NSZM0090E = "NSZM0090E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "User Dealer Type" is a mandatory field.
//     */
//    public static final String NSZM0188E = "NSZM0188E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Auto Create Flag" is a mandatory field.
     */
    public static final String NSZM0189E = "NSZM0189E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Installation Status Update Complete Flag" is a mandatory field.
//     */
//    public static final String NSZM0332E = "NSZM0332E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Direct Sales Order Type Code" is a mandatory field.
     */
    public static final String NSZM0371E = "NSZM0371E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Direct Sales Order Reason Code" is a mandatory field.
//     */
//    public static final String NSZM0372E = "NSZM0372E";
//
//    /**
//     * Service Machine Type can be changed from Main Machine to Accessory only if there is no related accessories.
//     */
//    public static final String NSZM0373E = "NSZM0373E";
//
//    /**
//     * SVC_CONFIG_MSTR_PK has not been registered.
//     */
//    public static final String NSZM0374E = "NSZM0374E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /** 
     * Input parameter "CPO Order Number" is a mandatory field.
     */
    public static final String NSZM0402E = "NSZM0402E";

    /** 
     * Input parameter "CPO Detail Line Number" is a mandatory field. 
     */
    public static final String NSZM0403E = "NSZM0403E";

    /**
     * Input parameter "CPO Detail Line Sub Number" is a mandatory field.
     */
    public static final String NSZM0404E = "NSZM0404E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Customer Issue PO Number" is a mandatory field.
//     */
//    public static final String NSZM0405E = "NSZM0405E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /** 
     * It cannot be processed because Conversion Order is In-Process.
     */
    public static final String NSZM0406E = "NSZM0406E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * The process cannot be executed because the Machine Status is not "W4I".
//     */
//    public static final String NSZM0408E = "NSZM0408E";
//
//    /**
//     * The process cannot be executed because the Machine Status is not "INSTL".
//     */
//    public static final String NSZM0409E = "NSZM0409E";
//
//    /**
//     * The process cannot be executed because the Machine Status is not "W4R".
//     */
//    public static final String NSZM0410E = "NSZM0410E";
//
//    /**
//     * The process cannot be executed because the Machine Status is not "RMV".
//     */
//    public static final String NSZM0411E = "NSZM0411E";
//
//    /**
//     * The process cannot be executed because the Machine Status is not "W4I" or "INSTL".
//     */
//    public static final String NSZM0412E = "NSZM0412E";
//
//    /**
//     * The process cannot be executed because the Machine Status is not "W4R" or "INSTL".
//     */
//    public static final String NSZM0413E = "NSZM0413E";
//
//    /**
//     * Config # is not assigned yet.Please wait to ship the Main Unit or change Machine Type.
//     */
//    public static final String NSZM0416E = "NSZM0416E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * The entered 'Merchandise Code' does not exist.
     */
    public static final String NSZM0583E = "NSZM0583E";

    /**
     * Input parameter "Shipping Order Slip Number" is a mandatory field.
     */
    public static final String NSZM0685E = "NSZM0685E";

    /**
     * Input parameter "Service Machine Usage Status Code" is a mandatory field.
     */
    public static final String NSZM0686E = "NSZM0686E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Parent Serial Number" is a mandatory field.
//     */
//    public static final String NSZM0687E = "NSZM0687E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Service Machine Transaction Type Code" is a mandatory field.
     */
    public static final String NSZM0688E = "NSZM0688E";

    /**
     * Input parameter "Owner Account Number" is a mandatory field.
     */
    public static final String NSZM0689E = "NSZM0689E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Input parameter "Owner Location Number" is a mandatory field.
//     */
//    public static final String NSZM0690E = "NSZM0690E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Input parameter "Billing To Account Number" is a mandatory field.
     */
    public static final String NSZM0691E = "NSZM0691E";

    /**
     * Input parameter "Billing To Location Number" is a mandatory field.
     */
    public static final String NSZM0692E = "NSZM0692E";

    /**
     * Input parameter "Current Location Account Number" is a mandatory field.
     */
    public static final String NSZM0693E = "NSZM0693E";

    /**
     * Input parameter "Current Location Number" is a mandatory field.
     */
    public static final String NSZM0694E = "NSZM0694E";

    /**
     * Input parameter "Service Machine Quantity" is a mandatory field.
     */
    public static final String NSZM0695E = "NSZM0695E";

    /**
     * Input parameter "Effective From Date" is a mandatory field.
     */
    public static final String NSZM0696E = "NSZM0696E";

    /**
     * Input parameter "Effective Through Date" is a mandatory field.
     */
    public static final String NSZM0697E = "NSZM0697E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Failed to create a Service Configuration Master Detail record.
//     */
//    public static final String NSZM0716E = "NSZM0716E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Failed to update a Service Configuration Master Detail record.
     */
    public static final String NSZM0717E = "NSZM0717E";

    /**
     * Failed to create a Service Machine Master Track record.
     */
    public static final String NSZM0718E = "NSZM0718E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Failed to update a Service Machine Master Track record.
//     */
//    public static final String NSZM0719E = "NSZM0719E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Failed to create a Service Physical Meter record.
     */
    public static final String NSZM0720E = "NSZM0720E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Failed to update a Service Physical Meter record.
//     */
//    public static final String NSZM0721E = "NSZM0721E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Failed to create a Service Non Preferred Technician record.
     */
    public static final String NSZM0722E = "NSZM0722E";

    /**
     * Failed to update a Service Non Preferred Technician record.
     */
    public static final String NSZM0723E = "NSZM0723E";

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Service Configuration Master Detail is not found.
//     */
//    public static final String NSZM0724E = "NSZM0724E";
//
//    /**
//     * Service Machine Master Track is not found.
//     */
//    public static final String NSZM0725E = "NSZM0725E";
//
//    /**
//     * Service Physical Meter is not found.
//     */
//    public static final String NSZM0726E = "NSZM0726E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Service Non Preferred Technician is not found.
     */
    public static final String NSZM0727E = "NSZM0727E";

    // START 2015/12/09 T.Tomita [CSA QC#951, ADD]
    /**
     * Input parameter "Merchandise Code" is not Install base trackable.
     */
    public static final String NSZM0804E = "NSZM0804E";
    // END 2015/12/09 T.Tomita [CSA QC#951, ADD]

    // START 2017/10/16 M.Kidokoro [QC#20246, ADD]
    /**
     * Failed to update a Service Machine Contact Person record.
     */
    public static final String NSZM1309E = "NSZM1309E";
    // END 2017/10/16 M.Kidokoro [QC#20246, ADD]

    // START 2019/11/14 K.Kitachi [QC#54327, ADD]
    /**
     * You cannot remove configuration because the location of IB is not at warehouse.
     */
    public static final String NSZM1368E = "NSZM1368E";
    // END 2019/11/14 K.Kitachi [QC#54327, ADD]

    /**
     * Service Machine Sequence Number starts with
     */
    public static final BigDecimal INIT_SVC_MACH_SQ_NUM = BigDecimal.ONE;

    /**
     * Service Machine Quantity starts with
     */
    public static final BigDecimal INIT_SVC_MACH_QTY = BigDecimal.ONE;

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Insert
//     */
//    public static final String INSERT = "INSERT";
//
//    /**
//     * Delete
//     */
//    public static final String DELETE = "DELETE";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    /**
     * Percent
     */
    public static final String PERCENT = "%";

    /**
     * Divide Dummy Serial Number
     */
    public static final String DIV_DUMMY_SER_NUM = "-";

    // add start 2016/07/11 CSA Defect#446
    /**
     * Create Warranty Contract Mode
     */
    public static final int CRAT_WTY = 1;

    /**
     * Terminate Warranty Contract Mode
     */
    public static final int TERM_WTY = 2;

    /**
     * PSN_CD LENGTH
     */
    public static final int PSN_CD_LENGTH = 8;

    // START 2017/09/14 S.Fujita [QC#18534,DEL]
//    /**
//     * VAR_CAHR_CONST Key : SVC_PGM_ST_WTY_NEW
//     */
//    public static final String SVC_PGM_ST_WTY_NEW_KEY = "SVC_PGM_ST_WTY_NEW";
//
//    /**
//     * VAR_CAHR_CONST Key : SVC_PGM_ST_WTY_USED
//     */
//    public static final String SVC_PGM_ST_WTY_USED_KEY = "SVC_PGM_ST_WTY_USED";
    // END 2017/09/14 S.Fujita [QC#18534,DEL]

    /**
     * ORD_CATG_CTX_TP : SVC_CONTR_CATG
     */
    public static final String ORD_CATG_CTX_TP_SVC_CONTR_CATG = "SVC_CONTR_CATG";

    /**
     * Max Date
     */
    public static final String MAX_DT = "29991231";

    /**
     * Max Percent
     */
    public static final BigDecimal MAX_PCT = new BigDecimal(100);

    // START 2016/08/03 T.Tomita [QC#12230, DEL]
//    /**
//     * Contract does not exist.
//     */
//    public static final String NSZM0434E = "NSZM0434E";
//
//    /**
//     * Contract Detail does not exist.
//     */
//    public static final String NSZM1026E = "NSZM1026E";
//
//    /**
//     * Failed to update DS_CONTR.
//     */
//    public static final String NSZM0873E = "NSZM0873E";
//
//    /**
//     * Failed to update DS_CONTR_DTL.
//     */
//    public static final String NSZM0875E = "NSZM0875E";
    // END 2016/08/03 T.Tomita [QC#12230, DEL]

    // START 2017/09/14 S.Fujita [QC#18534,DEL]
//    /**
//     * "Service Program for Standard Warranty New" doesn't exist in the VAR_CHAR_CONST.
//     */
//    public static final String NSZM1027E = "NSZM1027E";
//
//    /**
//     * "Service Program for Standard Warranty Used" doesn't exist in the VAR_CHAR_CONST.
//     */
//    public static final String NSZM1028E = "NSZM1028E";
    // END 2017/09/14 S.Fujita [QC#18534,DEL]
    // add end 2016/07/11 CSA Defect#446

    /**
     * Service Machine Status Code Is Item Change
     * @return String[]
     */
    public static String[] getSvcMachStsCd() {
        return new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED, SVC_MACH_MSTR_STS.TERMINATED, SVC_MACH_MSTR_STS.DUPLICATE};
    }

    // START 2016/08/03 T.Tomita [QC#12230, ADD]
    /** left padding length 6 */
    public static final int LPAD_LEN = 6;

    /** left padding by 0 */
    public static final String PAD_STR = "0";

    /** Failed to insert the DS_CONTR_INTFC. */
    public static final String NSZM0972E = "NSZM0972E";
    // END 2016/08/03 T.Tomita [QC#12230, ADD]

    // QC#16416 Add Start
    /** Input parameter "Shipping Plan Number" is a mandatory field. */
    public static final String NSZM1095E = "NSZM1095E";
    // QC#16416 Add End

    // START 2017/09/14 S.Fujita [QC#18534,ADD]
    /** BLANK */
    public static final String BLANK = "";

    /**
     * "Service Program for Standard Warranty" doesn't exist in the SVC_WTY_TP.
     */
    public static final String NSAM0702E = "NSAM0702E";
    // END 2017/09/14 S.Fujita [QC#18534,ADD]

    // START 2017/11/20 K.Ochiai [QC#21698, ADD]
    public static final String ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP = "EMSD_CONTR_BR_REP";
    // END 2017/11/20 K.Ochiai [QC#21698, ADD]
    // Add Start 2018/06/18 QC#26618
    public static final String DEFAULT_FIN_BR = "NSAB0560_DEFAULT_FIN_BR";
    // Add End 2018/06/18 QC#26618

    // Add Start 2018/08/09 QC#27595
    public static final String CONST_ESS_DUMMY_TECH = "ESS_DUMMY_TECH";
    // Add End 2018/08/09 QC#27595

    // QC#60023 2022/07/13 Add start
    /** Failed to insert the @. */
    public static final String NSZM0626E = "NSZM0626E";

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    // QC#60023 2022/07/13 Add end

    // START 2024/04/04 t.aizawa [QC#54186, ADD]
    /** Variable Character Constant Key "NSZC0010_INCL_CONFIG_TP_CD" */
    public static final String NSZC0010_INCL_CONFIG_TP_CD = "NSZC0010_INCL_CONFIG_TP_CD";

    /** Delimiter "," */
    public static final String DELIMITER_COMMA = ",";
    // END   2024/04/04 t.aizawa [QC#54186, ADD]

}
