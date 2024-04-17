/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001.constant;

/**
 * <pre>
 * Service Charge Information
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/16/2013   Fujitsu         S.Nakai         Create          N/A
 * 2016/10/31   Hitachi         T.Kanasaka      Update          QC#15136
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 *</pre>
 */
public interface NSXC002001Constant {

    /** any type */
    String ANY_TP = "*";
    /** svc task chrg tp cd */
    String SVC_TASK_CHRG_TP_CD = "SVC_TASK_CHRG_TP_CD";
    /** svc chrg tp cd */
    String SVC_CHRG_TP_CD = "SVC_CHRG_TP_CD";
    /** svc chrg tp cd */
    String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    // START 2016/10/31 T.Kanasaka [QC#15136, ADD]
    /** Date Format: Default Effective Through Date */
    public static final String DEF_EFF_THRU_DT = "29991231";
    // END 2016/10/31 T.Kanasaka [QC#15136, ADD]

    // START 2018/09/10 K.Kitachi [QC#26260, ADD]
    /** Var Char Const : NSAB0560 Default Financial Branch */
    public static final String NSAB0560_DEFAULT_FIN_BR = "NSAB0560_DEFAULT_FIN_BR";
    // END 2018/09/10 K.Kitachi [QC#26260, ADD]
}
