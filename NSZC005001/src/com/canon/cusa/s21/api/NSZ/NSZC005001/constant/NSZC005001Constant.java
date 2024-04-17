/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC005001.constant;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;

/**
 * <pre>
 * Service Dispatch Completion API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/06/04   SRA             E.Inada         Create          N/A
 * 10/21/2015   Hitachi         A.Kohinata      Update          NA#Renewal
 * 02/22/2016   Hitachi         A.Iwamoto       Update          QC#2749
 * 03/03/2016   Hitachi         A.Iwamoto       Update          QC#3390
 * 08/26/2016   Hitachi         K.Kasai         Update          QC#13661
 * 12/16/2016   Hitachi         K.Ochiai        Update          QC#15735
 * 12/19/2016   Hitachi         K.Kishimoto     Update          QC#16512
 * 01/26/2017   Hitachi         A.Kohinata      Update          CSA QC#17114
 * 01/30/2017   Hitachi         Y.Takeno        Update          CSA QC#17291
 * 09/01/2017   Hitachi         T.Tomita        Update          CSA QC#20672
 * 12/04/2017   Hitachi         U.Kim           Update          CSA QC#19907
 * 01/09/2018   Hitachi         U.Kim           Update          CSA QC#19702
 * 06/29/2018   Hitachi         A.Kohinata      Update          CSA QC#26795
 * 2018/12/26   Hitachi         K.Kitachi       Update          CSA QC#29723
 * 01/10/2019   Hitachi         K.Fujimoto      Update          CSA QC#26861
 * 2019/02/13   Hitachi         K.Fujimoto      Update          CSA QC#30310
 * 2019/07/30   Hitachi         K.Fujimoto      Update          CSA QC#52220
 * 2019/08/30   Hitachi         K.Fujimoto      Update          CSA QC#52128
 * 2019/11/18   Hitachi         K.Fujimoto      Update          CSA QC#54391
 * 2020/03/03   Hitachi         A.Kohinata      Update          CSA QC#56123
 * 2022/02/10   Hitachi         D.Yoshida       Update          CSA QC#57338
 *</pre>
 */
public interface NSZC005001Constant {

    /** Format Time stamp **/
    String FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** Format Date Time */
    String FORMAT_DT_TM = "yyyyMMddHHmmss";

    /** Date Time length */
    int LEN_DT_TM = 14;

    /** Date length */
    int LEN_DT = 8;

    /** Time length */
    int LEN_TM = 6;

    // Add Start 2019/02/13 K.Fujimoto QC#30310
    /** Service Comment Text Start Index */
    int STR_IDX_SVC_CMNT_TXT = 0;

    /** Service Comment Text length */
    int LEN_SVC_CMNT_TXT = 4000;
    // End Start 2019/02/13 K.Fujimoto QC#30310

    /** FSR Visit Number */
    String FSR_VISIT_NUM_01 = "01";

    /** 60 * 1000ms */
    double RSP_TM_VAL = 60000;

    /** Max RSP_TM */
    int MAX_RSP_TM = 99999;

    /** IWR_ST_CDS : 100 */
    String IWR_STS_CD_100 = "100";

    /** IWR_STS_CD : 300 */
    String IWR_STS_CD_300 = "300";

    /** SVC_DISPT_PND_CHRG_TP : 1 */
    String SVC_DISPT_PND_CHRG_TP_1 = "1";

    /** SVC_DISPT_PND_CHRG_TP : 2 */
    String SVC_DISPT_PND_CHRG_TP_2 = "2";

    /** SVC_DISPT_PND_CHRG_TP : 3 */
    String SVC_DISPT_PND_CHRG_TP_3 = "3";

    /** SVC_DISPT_PND_CHRG_TP : 4 */
    String SVC_DISPT_PND_CHRG_TP_4 = "4";

    /** SVC_DISPT_PND_CHRG_TP : 3 */
    String RTRN_INVTY_LOC_CD_SFX = "R";

    /** Service Task Status List : Not Completed */
    String[] SVC_TASK_STS_LIST_NOT_COMPLETE = new String[] {SVC_TASK_STS.CLOSED, SVC_TASK_STS.CANCELLED, SVC_TASK_STS.COMPLETED };

    /** Service Task Status List : Open */
    String[] SVC_TASK_STS_LIST_OPEN = new String[] {SVC_TASK_STS.CLOSED, SVC_TASK_STS.CANCELLED };

    /** SVC_MEMO_TRX_NM FSR_VISIT_NUM */
    String SVC_MEMO_TRX_NM_FSR_VISIT_NUM = "FSR_VISIT_NUM";

    /** Const Key DISPT_MTR_READ_SRC_TP */
    String CONST_KEY_DISPT_MTR_READ_SRC_TP = "DISPT_MTR_READ_SRC_TP";

    /** Const Key SVC_DISPT_CHK_IWR_STS_100_FLG */
    String CONST_KEY_SVC_DISPT_CHK_IWR_STS_100_FLG = "SVC_DISPT_CHK_IWR_STS_100_FLG";

    /** Const Key SVC_DISPT_CHK_IWR_STS_300_FLG */
    String CONST_KEY_SVC_DISPT_CHK_IWR_STS_300_FLG = "SVC_DISPT_CHK_IWR_STS_300_FLG";

    /** Const Key IWR_ROSS_COMM_ERR_DAYS */
    String CONST_KEY_IWR_ROSS_COMM_ERR_DAYS = "IWR_ROSS_COMM_ERR_DAYS";

    /** Const Key IWR_ROSS_COMM_FOLL_UP_DAYS */
    String CONST_KEY_IWR_ROSS_COMM_FOLL_UP_DAYS = "IWR_ROSS_COMM_FOLL_UP_DAYS";

    /** Const Key PARTS_USG_NGTV_QTY_MSG */
    String CONST_KEY_PARTS_USG_NGTV_QTY_MSG = "PARTS_USG_NGTV_QTY_MSG";

    /** Const Key NO_LBOR_CHRG_MSG */
    String CONST_KEY_NO_LBOR_CHRG_MSG = "NO_LBOR_CHRG_MSG";

    /** Const Key NO_TRVL_CHRG_MSG */
    String CONST_KEY_NO_TRVL_CHRG_MSG = "NO_TRVL_CHRG_MSG";

    /** Const Key NO_USG_CHRG_MSG */
    String CONST_KEY_NO_USG_CHRG_MSG = "NO_USG_CHRG_MSG";

    /** Const Key IWR_COMM_ERR_MSG */
    String CONST_KEY_IWR_COMM_ERR_MSG = "IWR_COMM_ERR_MSG";

    // Add End   12/19/2016 <QC#16512>
    /** Const Key INVLD_MTR_ENTRY_ERR_MSG */
    String CONST_KEY_INVLD_MTR_ENTRY_ERR_MSG = "INVLD_MTR_ENTRY_ERR_MSG";
    // Add End   12/19/2016 <QC#16512>

    /** Const Key PARTS_USG_NGTV_QTY_MSG */
    String CONST_KEY_INSTALL_CHECK_LIST_MSG = "INSTALL_CHECK_LIST_MSG";

    // add start 2017/01/26 CSA Defect#17114
    /** Const Key TECH_PI_MSG */
    String CONST_KEY_TECH_PI_MSG = "TECH_PI_MSG";
    // add end 2017/01/26 CSA Defect#17114

    // Add Start 2019/01/10 K.Fujimoto QC#26861
    /** Const Key MOD_CLO_SVC_TASK_MSG */
    String CONST_KEY_MOD_CLO_SVC_TASK_MSG = "MOD_CLO_SVC_TASK_MSG";
    // Add End   2019/01/10 K.Fujimoto QC#26861

    /** Const Key INTG_PROD_CATG_CD_DRUM */
    String CONST_KEY_INTG_PROD_CATG_CD_DRUM = "INTG_PROD_CATG_CD_DRUM";

    /** Const Key SVC_DISPT_PND_CHRG_TP */
    String CONST_KEY_SVC_DISPT_PND_CHRG_TP = "SVC_DISPT_PND_CHRG_TP";

    // Add Start 2019/02/13 K.Fujimoto QC#30310
    /** Const Key NSZC0050_PARTS_ADD_MSG_TMPL */
    String CONST_KEY_NSZC0050_PARTS_ADD_MSG_TMPL = "NSZC0050_PARTS_ADD_MSG_TMPL";

    /** Const Key NSZC0050_PARTS_MOD_MSG_TMPL */
    String CONST_KEY_NSZC0050_PARTS_MOD_MSG_TMPL = "NSZC0050_PARTS_MOD_MSG_TMPL";

    /** Const Key NSZC0050_PARTS_DEL_MSG_TMPL */
    String CONST_KEY_NSZC0050_PARTS_DEL_MSG_TMPL = "NSZC0050_PARTS_DEL_MSG_TMPL";
    // End Start 2019/02/13 K.Fujimoto QC#30310
    
    // Add Start 2019/07/30 K.Fujimoto QC#52220
    /** Const Key NSZC0050_PARTS_DEL_MSG_TMPL */
    String CONST_KEY_NSZC0050_PARTS_USED_MSG_TMPL = "NSZC0050_PARTS_USED_MSG_TMPL";
    // End Start 2019/07/30 K.Fujimoto QC#52220
    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    /** Const Key CONST_KEY_L2_FOLL_UP_ERR_MSG */
    String CONST_KEY_L2_FOLL_UP_ERR_MSG = "L2_FOLL_UP_ERR_MSG";
    /** Const Key CONST_KEY_L2_FOLL_UP_MEMO */
    String CONST_KEY_L2_FOLL_UP_MEMO = "L2_FOLL_UP_MEMO";
    /** Const Key NSZC0050_PARTS_DEL_MSG_TMPL */
    String CONST_KEY_L2_FOLL_UP_CREATE_MEMO = "L2_FOLL_UP_CREATE_MEMO";
    /** Const Key SVC_ISTL_NOT_EXIST_EML_ADDR */
    String CONST_KEY_SVC_ISTL_NOT_EXIST_EML_ADDR = "SVC_ISTL_NOT_EXIST_EML_ADDR";
    /** Not exist */
    String NOT_EXIST = "Not exist";
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]

    // ------------ Error Message ------------
    /** Input parameter "Global Company Code" is a mandatory field. */
    String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    String NSZM0002E = "NSZM0002E";

    /** Input parameter "Merchandise Code" is a mandatory field. */
    String NSZM0013E = "NSZM0013E";

    /**
     * Input parameter "DS Service Call Type Code" is a mandatory
     * field.
     */
    String NSZM0049E = "NSZM0049E";
    /** Input parameter "Service Bill Type Code" is a mandatory field. */
    String NSZM0050E = "NSZM0050E";
    /** Input parameter "Technician Code" is a mandatory field. */
    String NSZM0052E = "NSZM0052E";
    /** Input parameter "Invoice Currency Code" is a mandatory field. */
    String NSZM0070E = "NSZM0070E";
    /** Input parameter "Currency Exchange Rate" is a mandatory field. */
    String NSZM0071E = "NSZM0071E";
    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    String NSZM0074E = "NSZM0074E";
    /** Failed to search a Service Task record. */
    String NSZM0079E = "NSZM0079E";
    /** The corresponding data does not exist in "CCY". */
    String NSZM0099E = "NSZM0099E";
    /** Input parameter "Service Task Number" is a mandatory field. */
    String NSZM0082E = "NSZM0082E";
    /** The corresponding data does not exist in "MDSE". */
    String NSZM0105E = "NSZM0105E";
    /**
     * Input parameter "Payment Term Cash Discount Code" is a
     * mandatory field.
     */
    String NSZM0126E = "NSZM0126E";
    /** Input parameter "Execute User Id" is a mandatory field. */
    String NSZM0163E = "NSZM0163E";
    /** It failed to update the SVC_TASK. */
    String NSZM0167E = "NSZM0167E";
    /** It failed to update the FSR. */
    String NSZM0168E = "NSZM0168E";
    /** It failed to update the FSR_VISIT. */
    String NSZM0169E = "NSZM0169E";
    /** It failed to insert the FSR_EVEINT. */
    String NSZM0173E = "NSZM0173E";
    /** Input parameter "FSR#" is a mandatory field. */
    String NSZM0180E = "NSZM0180E";
    /** Failed to search a FSR record. */
    String NSZM0181E = "NSZM0181E";
    /** Failed to search a FSR Visit record. */
    String NSZM0182E = "NSZM0182E";
    /** Input parameter "FSR Visit Number" is a mandatory field. */
    String NSZM0213E = "NSZM0213E";
    /** Input parameter "FSR Visit Arrive Date" is a mandatory field. */
    String NSZM0214E = "NSZM0214E";
    /** Input parameter "FSR Visit Arrive Time" is a mandatory field. */
    String NSZM0215E = "NSZM0215E";
    /**
     * Input parameter "FSR Visit Complete Date" is a mandatory field.
     */
    String NSZM0216E = "NSZM0216E";
    /**
     * Input parameter "FSR Visit Complete Time" is a mandatory field.
     */
    String NSZM0217E = "NSZM0217E";
    /**
     * Input parameter "Service Travel Time Number" is a mandatory
     * field.
     */
    String NSZM0218E = "NSZM0218E";
    /**
     * Input parameter "Service Charge Continuation Flag" is a
     * mandatory field.
     */
    String NSZM0241E = "NSZM0241E";
    /**
     * Input parameter "Service Problem Type Code" is a mandatory
     * field.
     */
    String NSZM0243E = "NSZM0243E";
    /**
     * Input parameter "Service Problem Location Type Code" is a
     * mandatory field.
     */
    String NSZM0244E = "NSZM0244E";
    /**
     * Input parameter "Service Problem Reason Type Code" is a
     * mandatory field.
     */
    String NSZM0245E = "NSZM0245E";
    /**
     * Input parameter "Service Problem Correct Type Code" is a
     * mandatory field.
     */
    String NSZM0246E = "NSZM0246E";
    /**
     * Input parameter "Service Labor Time Number" is a mandatory
     * field.
     */
    String NSZM0247E = "NSZM0247E";
    /**
     * Input parameter "Parts Used By Technician Code" is a mandatory
     * field.
     */
    String NSZM0248E = "NSZM0248E";
    /** Input parameter "Merchandise Name" is a mandatory field. */
    String NSZM0249E = "NSZM0249E";
    /** Input parameter "Service Parts Quantity" is a mandatory field. */
    String NSZM0250E = "NSZM0250E";
    /**
     * Input parameter "FSR Usage Processed Flag" is a mandatory
     * field.
     */
    String NSZM0260E = "NSZM0260E";
    /**
     * Input parameter "Service Inventory Exception Flag" is a
     * mandatory field.
     */
    String NSZM0261E = "NSZM0261E";

    /** Input parameter "Resist Meter Date" is a mandatory field. */
    String NSZM0265E = "NSZM0265E";

    /** Input parameter "Reading Meter Count" is a mandatory field. */
    String NSZM0266E = "NSZM0266E";

    /** Input parameter "Meter Read Date" is a mandatory field. */
    String NSZM0268E = "NSZM0268E";
    /**
     * Input parameter "Reading Meter Type Code" is a mandatory field.
     */
    String NSZM0269E = "NSZM0269E";
    /**
     * Input parameter "Meter Reading Source Code" is a mandatory
     * field.
     */
    String NSZM0270E = "NSZM0270E";
    /** Input parameter "DS Contract Number" is a mandatory field. */
    String NSZM0271E = "NSZM0271E";
    /**
     * Input parameter "Service Time Event Code" is a mandatory field.
     */
    String NSZM0272E = "NSZM0272E";
    /**
     * Input parameter "Service Time Event From Date" is a mandatory
     * field.
     */
    String NSZM0273E = "NSZM0273E";
    /**
     * Input parameter "Service Time Event From Time" is a mandatory
     * field.
     */
    String NSZM0274E = "NSZM0274E";
    /**
     * Input parameter "Service Time Event To Date" is a mandatory
     * field.
     */
    String NSZM0275E = "NSZM0275E";
    /**
     * Input parameter "Service Time Event To Time" is a mandatory
     * field.
     */
    String NSZM0276E = "NSZM0276E";
    /** Input parameter "Machine Down Flag" is a mandatory field. */
    String NSZM0278E = "NSZM0278E";
    /** Input parameter "Completion Flag" is a mandatory field. */
    String NSZM0280E = "NSZM0280E";
    /** Could not get "Technician Inventory TOC Code". */
    String NSZM0344E = "NSZM0344E";
    /** Failed to search a FSR_USG record. */
    String NSZM0345E = "NSZM0345E";
    /** It failed to insert the FSR_VISIT_TASK. */
    String NSZM0346E = "NSZM0346E";
    /** It failed to insert the FSR_USG. */
    String NSZM0347E = "NSZM0347E";
    /** It failed to update the FSR_VISIT_TASK. */
    String NSZM0348E = "NSZM0348E";
    /** It failed to update the FSR_USG. */
    String NSZM0349E = "NSZM0349E";
    /** It failed to update the FSR_VISIT_TM_EVENT. */
    String NSZM0350E = "NSZM0350E";
    /** It failed to insert the FSR_VISIT_TM_EVENT. */
    String NSZM0351E = "NSZM0351E";
    /** Failed to update SVC_PHYS_MTR_READ. */
    String NSZM0368E = "NSZM0368E";
    /** Latest machine down flag cannot be obtained. */
    String NSZM0400E = "NSZM0400E";
    /** Failed to insert the SVC_MEMO. */
    String NSZM0475E = "NSZM0475E";
    /** Input parameter "Parts Used By Technician Location Code" is a mandatory field. */
    String NSZM0514E = "NSZM0514E";
    /** Service Physical Meter is not found. */
    String NSZM0726E = "NSZM0726E";
    /** Input parameter "Unit of Measure Code" is a mandatory field. */
    String NSZM0738E = "NSZM0738E";
    /** Input parameter "Service Execute Date" is a mandatory field. */
    String NSZM0739E = "NSZM0739E";
    /**
     * Input parameter "DS Meter Reading Type Group Code" is a
     * mandatory field.
     */
    String NSZM0741E = "NSZM0741E";
    /**
     * Input parameter "Service Expense Quantity" is a mandatory
     * field.
     */
    String NSZM0744E = "NSZM0744E";
    /**
     * Input Parameter "Meter Label Code" or
     * "Service Physical Meter Primary Key" is a mandatory field.
     */
    String NSZM0745E = "NSZM0745E";
    /** Failed to search a DS Service Call Type record. */
    String NSZM0752E = "NSZM0752E";
    /** Failed to search a Inventory record. */
    String NSZM0753E = "NSZM0753E";
    /** Failed to search a Merchandise Storage Package record. */
    String NSZM0754E = "NSZM0754E";
    /** Failed to search a Service Bill Type record. */
    String NSZM0755E = "NSZM0755E";
    /** Failed to search a Service Machine Master record. */
    String NSZM0756E = "NSZM0756E";
    /** Failed to search a Service Time Event record. */
    String NSZM0757E = "NSZM0757E";
    /** Failed to search a DS Service Call Type record. */
    String NSZM0847E = "NSZM0847E";
    /** Failed to insert the FSR_EXP. */
    String NSZM0758E = "NSZM0758E";
    /** Failed to update the FSR_EXP. */
    String NSZM0759E = "NSZM0759E";
    /** Meter Count is not consistent. */
    String NSZM0760E = "NSZM0760E";
    /** Input parameter "Service Time Event Duration" is a mandatory field. */
    String NSZM0761E = "NSZM0761E";
    /** Failed to search a FSR_EXP record. */
    String NSZM0762E = "NSZM0762E";
    // add start 2015/12/16 CSA Defect#978
    /** Process Mode is not valid. */
    String NSZM0039E = "NSZM0039E";
    /** The process cannot be executed because the "FSR Status" is incorrect. */
    String NSZM0154E = "NSZM0154E";
    /** Failed to update the FSR_CHRG. */
    String NSZM0805E = "NSZM0805E";
    /** Failed to update the FSR_CHRG_DTL. */
    String NSZM0806E = "NSZM0806E";
    /** Failed to update the FSR_ISTL_CHK_LIST. */
    String NSZM0849E = "NSZM0849E";
    /** Input parameter "UOM Code" is a mandatory field. */
    String NSZM0644E = "NSZM0644E";
    /** Input parameter "Service Invoice Charge Type Code" is a mandatory field. */
    String NSZM0127E = "NSZM0127E";
    /** Input parameter "Service Charge Transaction Type Code" is a mandatory field. */
    String NSZM0807E = "NSZM0807E";
    /** Input parameter "Service Charge Quantity" is a mandatory field. */
    String NSZM0808E = "NSZM0808E";
    /** Input parameter "Service Charge Unit Amount" is a mandatory field. */
    String NSZM0809E = "NSZM0809E";
    /** Input parameter "Service Charge Deal Amount" is a mandatory field. */
    String NSZM0810E = "NSZM0810E";
    /** Input parameter "Service Charge Flag" is a mandatory field. */
    String NSZM0811E = "NSZM0811E";
    /** Input parameter "Price Category Code" is a mandatory field. */
    String NSZM0812E = "NSZM0812E";
    /** Input parameter "Service Charge Unit Amount Of Time" is a mandatory field. */
    String NSZM0813E = "NSZM0813E";
    /** Target Mdse data does not exist .TABLE ID [MDSE] */
    String NSZM0861E = "NSZM0861E";
    /** Target Debrief Data does not exist .TABLE ID [FSR_VISIT_TASK] */
    String NSZM0862E = "NSZM0862E";

    // Add End   12/19/2016 <QC#16512>
    /**
     * Current meter reading can not be lower than the previous read.
     * Please select the meter reading type as 'Adjustment' to enter
     * lower reads.
     */
    String NSZM0541E = "NSZM0541E";
    // Add End   12/19/2016 <QC#16512>
    // Add Start 2019/08/30 K.Fujimoto QC#52128
    /** The entered meter is over the max meter digit.*/
    String NSZM1295E = "NSZM1295E";
    // End Start 2019/08/30 K.Fujimoto QC#52128

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
    // add end 2020/03/03 QC#56123

    /** Debrief Mode */
    String MODE_DEBRIEF = "01";

    /** Chargable Mode */
    String MODE_CHARGABLE = "02";

    /**Install Check Mode */
    String MODE_INSTALL_CHECK = "03";

    /** exchange method */
    String MULTIPLICATION = "*";

    /** exchange method */
    String DIVISION = "/";

    /** varchar const name */
    String SVC_LBOR_PRC_CATG_NM = "SVC_LBOR_PRC_CATG_NM";

    /** varchar const name */
    String SVC_TRVL_PRC_CATG_NM = "SVC_TRVL_PRC_CATG_NM";

    /** varchar const name */
    String SVC_INV_ZERO_LINE_CRAT_FLG = "SVC_INV_ZERO_LINE_CRAT_FLG";

    /** varchar const name */
    String SVC_LBOR_TRVL_UOM_CD = "SVC_LBOR_TRVL_UOM_CD";

 // add start 2016/03/03 CSA Defect#3390
    /** varchar const name */
    String SVC_BIZ_FROM_HOUR = "SVC_BIZ_FROM_HOUR";

    /** varchar const name */
    String SVC_BIZ_THRU_HOUR = "SVC_BIZ_THRU_HOUR";
 // add end 2016/03/03 CSA Defect#3390

    /** Sub warehouse:Good */
    String PART_USG_INVTY_LOC_SFX = "G";
    // add end 2015/12/16 CSA Defect#978

    /** Input parameter "FSR Number" is a mandatory field. */
    String NSZM0291E = "NSZM0291E";
    /** Input parameter "Service Config Master Pk" is a mandatory field. */
    String NSZM0570E = "NSZM0570E";
    /** Input parameter "Mdse Code" is a mandatory field. */
    String NSZM0842E = "NSZM0842E";
    // Del Start 2017/09/01 QC#20672
//    /** Input parameter "Install Check Verify Flag" is a mandatory field. */
//    String NSZM0843E = "NSZM0843E";
    // Del End 2017/09/01 QC#20672
    //check target List
    /** IWR */
    String DEBRIEF_CHK_IWR = "1";
    /** Inventory */
    String DEBRIEF_CHK_INVTY = "2";
    /** Usage Charge */
    String DEBRIEF_CHK_USG_CHRG = "3";
    /** Labor Charge */
    String DEBRIEF_CHK_LBOR_CHRG = "4";
    /** Travel Charge */
    String DEBRIEF_CHK_TRVL_CHRG = "5";
    /** Install Check */
    String DEBRIEF_CHK_ISTALL = "6";
    // add start 2017/01/26 CSA Defect#17114
    /** Tech PI */
    String DEBRIEF_CHK_TECH_PI = "7";
    // add end 2017/01/26 CSA Defect#17114
    /** ALL */
    String DEBRIEF_CHK_ALL = "*";
    // START 2019/11/18 K.Fujimoto [QC#54391, ADD]
    /** Onsite Supprot(L2) */
    String DEBRIEF_CHK_ONS_PROC = "8";
    // END   2019/11/18 K.Fujimoto [QC#54391, ADD]

    //column name
    /** SVC_MOD_PLN_ID */
    String SVC_MOD_PLN_ID = "SVC_MOD_PLN_ID";
    /** SVC_TASK_NUM */
    String SVC_TASK_NUM = "SVC_TASK_NUM";

    // add start 2016/02/22 CSA Defect#2749
    /** 100 */
    BigDecimal BIGDECIMAL_100 = BigDecimal.valueOf(100);
    // add end 2016/02/22 CSA Defect#2749

    // START 2016/08/26 [QC#13661, ADD]
    /** Default hhmmss */
    String DEF_HHMMSS = "000000";
    // END 2016/08/26 [QC#13661, ADD]

    // START 2015/12/14 K.Ochiai [QC#15735, ADD]
    /** Completion notification Mail Template Code */
    public static final String CMPL_MAIL_TMPL_ID = "NSZC0050M001";

    /** From Mail Address Group */
    public static final String FROM_ADDR_GRP_CD = "FROM0003";

    /** From Mail Address Group */
    public static final String SVC_DATA_MGT_DEPT = "SVC_DATA_MGT_DEPT";

    /** Could not get FROM mail-address. */
    public static final String NSZM0184E = "NSZM0184E";

    /** Could not get TO mail-address. */
    public static final String NSZM0436E = "NSZM0436E";

    /** Could not get Mailtemplate. */
    public static final String NSZM0185E = "NSZM0185E";
    // END 2015/12/14 K.Ochiai [QC#15735, ADD]

    // START 2017/01/30 [QC#17291, ADD]
    /** max value of SVC_LBOR_TM_NUM */
    public static final BigDecimal MAX_VALUE_SVC_LBOR_TM_NUM = BigDecimal.valueOf(9999);
    // END   2017/01/30 [QC#17291, ADD]

    // START 2017/12/04 U.Kim [QC#19907, ADD]

    /** comma */
    public static final String COMMA = ",";

    /** Location Update Request Mail Template Code */
    public static final String LOC_UPDT_MAIL_TMPL_ID = "NSZC0050M002";
    // END 2017/12/04 U.Kim [QC#19907, ADD]

    // START 2018/01/09 U.Kim [QC#19702, ADD]
    /** Waiting second for find by key*/
    String WAIT_SEC_UPD_TASK_COMPLETION = "WAIT_SEC_UPD_TASK_COMPLETION";
    // END 2018/01/09 U.Kim [QC#19702, ADD]

    // add start 2018/06/29 QC#26795
    /** The reads will be registered as meter rollover because of lower reads than previous one. */
    public static final String NSZM1335W = "NSZM1335W";
    // add end 2018/06/29 QC#26795

    // START 2018/12/26 K.Kitachi [QC#29723, ADD]
    /** Failed to get Service Problem Correct Type Code. */
    public static final String NSZM1361E = "NSZM1361E";
    // END 2018/12/26 K.Kitachi [QC#29723, ADD]

    // START 2022/02/10 [QC#57338, ADD]
    /** Response Time Target DS_SVC_CALL_TP : DS_COND_CONST */
    public static final String RSP_TM_TGT_CALL_TP = "RSP_TM_TGT_CALL_TP";
    // END   2022/02/10 [QC#57338, ADD]

}
