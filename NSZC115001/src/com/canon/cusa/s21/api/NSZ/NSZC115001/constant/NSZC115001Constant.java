/**
 *  <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC115001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * NSZC1150 : Contract Import API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/11   Hitachi         T.Kanasaka      Create          N/A
 * 2017/05/15   Hitachi         T.Tomita        Update          RS#8377
 * 2017/05/24   Hitachi         T.Tomita        Update          RS#8396
 * 2017/05/24   Hitachi         A.Kohinata      Update          RS#8379
 * 2017/05/25   Hitachi         T.Tomita        Update          RS#8398
 * 2017/06/19   Hitachi         K.Kitachi       Update          QC#18855
 * 2017/06/21   Hitachi         K.Kitachi       Update          QC#18068
 * 2017/06/23   Hitachi         K.Kitachi       Update          QC#18068
 * 2017/07/28   Hitachi         Y.Takeno        Update          QC#20088
 * 2017/08/02   Hitachi         Y.Takeno        Update          QC#19792
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/08/09   Hitachi         K.Kim           Update          QC#20228
 * 2017/08/25   Hitachi         U.Kim           Update          QC#18345
 * 2017/10/06   Fujitsu         K.Ishizuka      Update          QC#21393
 * 2017/11/20   Hitachi         K.Ochiai        Update          QC#21698
 * 2018/05/24   Hitachi         K.Kishimoto     Update          QC#26062
 * 2018/08/22   Hitachi         A.Kohinata      Update          QC#23920
 * 2019/01/18   Hitachi         T.Kanasaka      Update          QC#29081
 * 2023/07/05   CITS            T.Kojima        Update          QC#61472
 * 2023/11/08   CITS            R.Jin           Update          QC#62108
 *</pre>
 */
public class NSZC115001Constant {

    // -- Process Mode ---------------------
    /** Process Mode : New */
    public static final String PROC_MODE_NEW = "1";
    /** Process Mode : Modify */
    public static final String PROC_MODE_MOD = "2";
    /** Process Mode : Copy */
    public static final String PROC_MODE_COPY = "3";
    /** Process Mode : Physical Delete */
    public static final String PROC_MODE_PHYS_DEL = "4";
    /** Process Mode : B/w Equipment */
    public static final String PROC_MODE_BW_EQUIP = "5";

    // -- Request Type ---------------------
    /** Request Type : New */
    public static final String RQST_TP_NEW = "1";
    /** Request Type : Modify */
    public static final String RQST_TP_MOD = "2";
    /** Request Type : Delete */
    public static final String RQST_TP_DEL = "3";

    // -- Message Code --------------------
    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";
    /** "Request Type" is required. */
    public static final String NSZM1118E = "NSZM1118E";
    /** "Request Type Code" has an invalid value. */
    public static final String NSZM1119E = "NSZM1119E";
    /** Input parameter "Process Mode" is a mandatory field. */
    public static final String NSZM0003E = "NSZM0003E";
    /** The input is not a defined "Process Mode". */
    public static final String NSZM0004E = "NSZM0004E";
    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";
    /** Input parameter "CPO Order Number" is a mandatory field. */
    public static final String NSZM0402E = "NSZM0402E";
    /** Reference CPO Order Number of the parameter is not set. */
    public static final String NSZM1120E = "NSZM1120E";
    /** Input parameter "DS Contract Pk" is a mandatory field. */
    public static final String NSZM0856E = "NSZM0856E";
    /** Shell Line Number of the parameter is not set. */
    public static final String NSZM1121E = "NSZM1121E";
    /** Service Program Merchandise Code of the parameter is not set. */
    public static final String NSZM1122E = "NSZM1122E";
    /** Price Service Contract Type Code of the parameter is not set. */
    public static final String NSZM1123E = "NSZM1123E";
    /** Price Service Plan Type Code of the parameter is not set. */
    public static final String NSZM1124E = "NSZM1124E";
    /** DS Contract Category Code of the parameter is not set. */
    public static final String NSZM1125E = "NSZM1125E";
    /** Base Billing Cycle Code of the parameter is not set. */
    public static final String NSZM1126E = "NSZM1126E";
    /** From Period Month Number of the parameter is not set. */
    public static final String NSZM1127E = "NSZM1127E";
    /** To Period Month Number of the parameter is not set. */
    public static final String NSZM1128E = "NSZM1128E";
    /** Bill with Equipment Flag of the parameter is not set. */
    public static final String NSZM1129E = "NSZM1129E";
    /** Bill by Type Code of the parameter is not set. */
    public static final String NSZM1130E = "NSZM1130E";
    /** Sold to Customer Location Code of the parameter is not set. */
    public static final String NSZM1131E = "NSZM1131E";
    /** Input parameter "Sell To Code" is a mandatory field. */
    public static final String NSZM0016E = "NSZM0016E";
    /** Input parameter "DS Contract Detail Pk" is a mandatory field. */
    public static final String NSZM0857E = "NSZM0857E";
    /** Position Number of the parameter is not set. */
    public static final String NSZM1132E = "NSZM1132E";
    /** Input parameter "CPO Detail Line Number" is a mandatory field. */
    public static final String NSZM0403E = "NSZM0403E";
    /** Input parameter "CPO Detail Line Sub Number" is a mandatory field. */
    public static final String NSZM0404E = "NSZM0404E";
//    /** Meter Read Method Code of the parameter is not set. */
//    public static final String NSZM1133E = "NSZM1133E";
    /** Model Identifier of the parameter is not set. */
    public static final String NSZM1134E = "NSZM1134E";
    /** Maintenance Price Category Code of the parameter is not set. */
    public static final String NSZM1135E = "NSZM1135E";
    /** Price Meter Package PK of the parameter is not set. */
    public static final String NSZM1136E = "NSZM1136E";
    /** Base Price Deal Amount of the parameter is not set. */
    public static final String NSZM1137E = "NSZM1137E";
    /** Deal Price List Price Amount of the parameter is not set. */
    public static final String NSZM1138E = "NSZM1138E";
    /** Contract Physical Billing Meter Relation PK of the parameter is not set. */
    public static final String NSZM1139E = "NSZM1139E";
    /** Input parameter "Billing Meter Label Code" is a mandatory field. */
    public static final String NSZM0899E = "NSZM0899E";
    /** DS Contract Additional Charge PK of the parameter is not set. */
    public static final String NSZM1140E = "NSZM1140E";
    /** Additional Base Price Category Code of the parameter is not set. */
    public static final String NSZM1141E = "NSZM1141E";
    /** Additional Base Price Deal Amount of the parameter is not set. */
    public static final String NSZM1142E = "NSZM1142E";
    /** Additional Charge Price Category Code of the parameter is not set. */
    public static final String NSZM1143E = "NSZM1143E";
    /** Additional Charge Merchandise Code of the parameter is not set. */
    public static final String NSZM1144E = "NSZM1144E";
    /** Additional Charge Price Deal Amount of the parameter is not set. */
    public static final String NSZM1145E = "NSZM1145E";
    /** Parameter "Service Program Merchandise Code" does not exists in the Master. */
    public static final String NSZM1146E = "NSZM1146E";
    /** Parameter "Price Service Contract Type Code" does not exists in the Master. */
    public static final String NSZM1147E = "NSZM1147E";
    /** arameter "Price Service Plan Type Code" does not exists in the Master. */
    public static final String NSZM1148E = "NSZM1148E";
    /** Parameter "DS Contract Category Code" does not exists in the Master. */
    public static final String NSZM1149E = "NSZM1149E";
    /** Parameter "Base Billing Cycle Code" does not exists in the Master. */
    public static final String NSZM1150E = "NSZM1150E";
    /** Parameter "Usage Billing Cycle Code" does not exists in the Master. */
    public static final String NSZM1151E = "NSZM1151E";
    /** Parameter "Bill by Type Code" does not exists in the Master. */
    public static final String NSZM1152E = "NSZM1152E";
    /** Parameter "Sold to customer location Code" does not exists in the Master. */
    public static final String NSZM1153E = "NSZM1153E";
    /** Parameter "Sell To Customer Code" does not exists in the Master. */
    public static final String NSZM1154E = "NSZM1154E";
    /** Parameter "Manual Contract Override Reason Code" does not exists in the Master. */
    public static final String NSZM1155E = "NSZM1155E";
    /** Parameter "Credit And Rebill Code" does not exists in the Master. */
    public static final String NSZM1156E = "NSZM1156E";
    /** Parameter "Meter Read Method Code" does not exists in the Master. */
    public static final String NSZM1157E = "NSZM1157E";
    /** Parameter "Model Identifier" does not exists in the Master. */
    public static final String NSZM1158E = "NSZM1158E";
    /** Parameter "Price Meter Package PK" does not exists in the Master. */
    public static final String NSZM1159E = "NSZM1159E";
    /** Parameter "Price Tiered Service Offering Code" does not exists in the Master. */
    public static final String NSZM1160E = "NSZM1160E";
    /** Parameter "Price List Band Code" does not exists in the Master. */
    public static final String NSZM1161E = "NSZM1161E";
    /** Parameter "Billing Meter Label Code" does not exists in the Master. */
    public static final String NSZM1162E = "NSZM1162E";
    /** Parameter "Usage Merchandise Code" does not exists in the Master. */
    public static final String NSZM1163E = "NSZM1163E";
    /** Parameter "Regular Meter Label Code" does not exists in the Master. */
    public static final String NSZM1164E = "NSZM1164E";
    /** Parameter "Price Service Tier Type Code" does not exists in the Master. */
    public static final String NSZM1165E = "NSZM1165E";
    /** Parameter "CPO Order Number" does not exists in the CPO_DTL. */
    public static final String NSZM1166E = "NSZM1166E";
    /** Parameter "Additional Charge Merchandise Code" does not exists in the Master. */
    public static final String NSZM1167E = "NSZM1167E";
    /** 'Manual Contract Override Reason Code' and 'Manual Contract Override Comment' is mandatory if 'Manual Contract Override Flag' is 'Y' */
    public static final String NSZM1168E = "NSZM1168E";
    /** The combination of parameters "Service Detail List" and "Service Config Reference List" is incorrect. */
    public static final String NSZM1169E = "NSZM1169E";
    /** The combination of parameters "CPO Service Detail List" and "CPO Service Price List" is incorrect. */
    public static final String NSZM1170E = "NSZM1170E";
    /** The combination of parameters "CPO Service Detail List" and "CPO Service Usage Price List" is incorrect. */
    public static final String NSZM1171E = "NSZM1171E";
    /** The combination of parameters "CPO Service Price List" and "CPO Service Config Reference List" is incorrect. */
    public static final String NSZM1172E = "NSZM1172E";
    /** The 'Excess Meter Amount Rate' is not match with the same 'Billing Meter Label Code'. */
    public static final String NSZM1173E = "NSZM1173E";
    /** Count of Tier does not match. */
    public static final String NSZM1174E = "NSZM1174E";
    /** The combination of parameters "Price Meter Package PK" and "Model Identifier" is incorrect. */
    public static final String NSZM1175E = "NSZM1175E";
    /** Parameter "Contract PK" does not exists in the DS_CONTR. */
    public static final String NSZM1176E = "NSZM1176E";
    /** Contract of the state is incorrect. */
    public static final String NSZM1177E = "NSZM1177E";
    /** Period Month Number for Base Billing Cycle is incorrect. */
    public static final String NSZM1178E = "NSZM1178E";
    /** Period Month Number for Usage Billing Cycle is incorrect. */
    public static final String NSZM1179E = "NSZM1179E";
    /** Period Month Number is incorrect. */
    public static final String NSZM1180E = "NSZM1180E";
    /** Parameter "Model Identifier" is incorrect. */
    public static final String NSZM1181E = "NSZM1181E";
    /** Count of "Service Usage Price List" is incorrect. */
    public static final String NSZM1182E = "NSZM1182E";
    /** Count of "Tier" is incorrect. */
    public static final String NSZM1183E = "NSZM1183E";
    /** The value for "From Period Month Number" must be bigger than 0. */
    public static final String NSZM1184E = "NSZM1184E";
    /** The value for "To Period Month Number" must be over "From Period Month Number". */
    public static final String NSZM1185E = "NSZM1185E";
    /** Parameters for the Contract Meter Multiple Rate is not correct. */
    public static final String NSZM1186E = "NSZM1186E";
    /** Parameters for the Model ID can not set for fleet. */
    public static final String NSZM1187E = "NSZM1187E";
    /** The model identifier does not exist in Master  or "Inactive" item. */
    public static final String NSZM1188E = "NSZM1188E";
    /** This service meter package is not valid with the service price list. */
    public static final String NSZM1189E = "NSZM1189E";
    /** Total price amount has exceeded 10,000,000. */
    public static final String NSZM1190E = "NSZM1190E";
    /** Per Unit Periodic Covered Images is mandatory. */
    public static final String NSZM1191E = "NSZM1191E";
    /** Excess Per Image Charge is mandatory. */
    public static final String NSZM1192E = "NSZM1192E";
    /** Multiplier is mandatory. */
    public static final String NSZM1193E = "NSZM1193E";
    /** If the Contract Indicator is Aggregate, please set the same Service Package to all Model. */
    public static final String NSZM1194E = "NSZM1194E";
    /** Excess thing Charge Rate is the same for each Billing Counter in all Model under. */
    public static final String NSZM1195E = "NSZM1195E";
    /** Tier and Non-Tier are mixed. */
    public static final String NSZM1196E = "NSZM1196E";
    /** Range from is mandatory. */
    public static final String NSZM1197E = "NSZM1197E";
    /** Range to is mandatory. */
    public static final String NSZM1198E = "NSZM1198E";
    /** Excess Per Image Charge is mandatory. */
    public static final String NSZM1199E = "NSZM1199E";
    /** Number of Tier must be greater than 1 per billing counter. */
    public static final String NSZM1200E = "NSZM1200E";
    /** Price Book Item is invalid. */
    public static final String NSZM1201E = "NSZM1201E";
    /** Model service price list is invalid. */
    public static final String NSZM1202E = "NSZM1202E";
    /** Accessory Charge service price list is invalid. */
    public static final String NSZM1203E = "NSZM1203E";
    /** Rental Eq Price List is invalid. */
    public static final String NSZM1204E = "NSZM1204E";
    /** Additional Service Charge price list is invalid. */
    public static final String NSZM1205E = "NSZM1205E";
    /** Range from has exceeded the maximum value (999,999,999). */
    public static final String NSZM1206E = "NSZM1206E";
    /** Range to has exceeded the maximum value (999,999,999). */
    public static final String NSZM1207E = "NSZM1207E";
    /** Excess Per Image Charge has exceeded the maximum value (9.99999). */
    public static final String NSZM1208E = "NSZM1208E";
    /** Range to must be greater than Range from. */
    public static final String NSZM1209E = "NSZM1209E";
    /** Cannot register Service Pricing information because declined it. */
    public static final String NSZM1210E = "NSZM1210E";
    /** Unit Periodic Base must be 0 or a positive number. */
    public static final String NSZM1211E = "NSZM1211E";
    /** Excess Per Image Charge must be 0 or a positive number. */
    public static final String NSZM1212E = "NSZM1212E";
    /** Periodic Service Price(Accessory Charge) must be 0 or a positive number. */
    public static final String NSZM1213E = "NSZM1213E";
    /** Periodic Service Price(Rental Eq Charge Base & Accessory) must be 0 or a positive number. */
    public static final String NSZM1214E = "NSZM1214E";
    /** Periodic Service Price must be 0 or a positive number. */
    public static final String NSZM1215E = "NSZM1215E";
    /** Rental order is required with all accessory items by accessory charge. */
    public static final String NSZM1216E = "NSZM1216E";
    /** Rental order is required with all accessory items by Rental Equipment Charge Base & Accessory. */
    public static final String NSZM1217E = "NSZM1217E";
    /** The entered item cannot create the shell. Because it is not a main machine. */
    public static final String NSZM1218E = "NSZM1218E";
    /** The item code which entered to "Accessory Charge" is invalid. */
    public static final String NSZM1219E = "NSZM1219E";
    /** Invalid contract#. There is no relationship between contract and order configuration. */
    public static final String NSZM1220E = "NSZM1220E";
    /** Data delete failure. (table name is [SCHD_CRAT_TMPL]) */
    public static final String NSZM1221E = "NSZM1221E";
    /** Data delete failure. (table name is [SCHD_CRAT_TMPL_LINE]) */
    public static final String NSZM1222E = "NSZM1222E";
    /** Data insert failure. (table name is [DS_CONTR]) */
    public static final String NSZM1223E = "NSZM1223E";
    /** Data update failure. (table name is [DS_CONTR]) */
    public static final String NSZM1224E = "NSZM1224E";
    /** Data delete failure. (table name is [DS_CONTR]) */
    public static final String NSZM1225E = "NSZM1225E";
    /** Data has been updated by another user. (table name is [DS_CONTR]) */
    public static final String NSZM1226E = "NSZM1226E";
    /** Data insert failure. (table name is [DS_CONTR_DTL]) */
    public static final String NSZM1227E = "NSZM1227E";
    /** Data update failure. (table name is [DS_CONTR_DTL]) */
    public static final String NSZM1228E = "NSZM1228E";
    /** Data delete failure. (table name is [DS_CONTR_DTL]) */
    public static final String NSZM1229E = "NSZM1229E";
    /** Data has been updated by another user. (table name is [DS_CONTR_DTL]) */
    public static final String NSZM1230E = "NSZM1230E";
    /** Data insert failure. (table name is [DS_CONTR_BLLG_MTR]) */
    public static final String NSZM1231E = "NSZM1231E";
    /** Data delete failure. (table name is [DS_CONTR_BLLG_MTR]) */
    public static final String NSZM1232E = "NSZM1232E";
    /** Data insert failure. (table name is [CONTR_PHYS_BLLG_MTR_RELN]) */
    public static final String NSZM1233E = "NSZM1233E";
    /** Data delete failure. (table name is [CONTR_PHYS_BLLG_MTR_RELN]) */
    public static final String NSZM1234E = "NSZM1234E";
    /** Data insert failure. (table name is [CONTR_XS_COPY]) */
    public static final String NSZM1235E = "NSZM1235E";
    /** Data delete failure. (table name is [CONTR_XS_COPY]) */
    public static final String NSZM1236E = "NSZM1236E";
    /** Data insert failure. (table name is [DS_CONTR_ADDL_CHRG]) */
    public static final String NSZM1237E = "NSZM1237E";
    /** Data update failure. (table name is [DS_CONTR_ADDL_CHRG]) */
    public static final String NSZM1238E = "NSZM1238E";
    /** Data delete failure. (table name is [DS_CONTR_ADDL_CHRG]) */
    public static final String NSZM1239E = "NSZM1239E";
    /** Data has been updated by another user. (table name is [DS_CONTR_ADDL_CHRG]) */
    public static final String NSZM1240E = "NSZM1240E";
    /** Data insert failure. (table name is [SVC_TERM_COND]) */
    public static final String NSZM1241E = "NSZM1241E";
    /** Data delete failure. (table name is [SVC_TERM_COND]) */
    public static final String NSZM1242E = "NSZM1242E";
    /** Data insert failure. (table name is [DS_CONTR_CR_CARD_PO]) */
    public static final String NSZM1243E = "NSZM1243E";
    /** Data update failure. (table name is [DS_CONTR_CR_CARD_PO]) */
    public static final String NSZM1244E = "NSZM1244E";
    /** Data delete failure. (table name is [DS_CONTR_CR_CARD_PO]) */
    public static final String NSZM1245E = "NSZM1245E";
    /** Data insert failure. (table name is [DS_CONTR_RNW_ESCL]) */
    public static final String NSZM1246E = "NSZM1246E";
    /** Data update failure. (table name is [DS_CONTR_RNW_ESCL]) */
    public static final String NSZM1247E = "NSZM1247E";
    /** Data delete failure. (table name is [DS_CONTR_RNW_ESCL]) */
    public static final String NSZM1248E = "NSZM1248E";
    /** Data insert failure. (table name is [DS_CONTR_TAX_DTL]) */
    public static final String NSZM1249E = "NSZM1249E";
    /** Data delete failure. (table name is [DS_CONTR_TAX_DTL]) */
    public static final String NSZM1250E = "NSZM1250E";
    /** Data insert failure. (table name is [SVC_MEMO]) */
    public static final String NSZM1251E = "NSZM1251E";
    /** Data update failure. (table name is [SVC_MEMO]) */
    public static final String NSZM1252E = "NSZM1252E";
    /** Data delete failure. (table name is [SVC_MEMO]) */
    public static final String NSZM1253E = "NSZM1253E";
    /** Additional Charge Category Code of the parameter is not set. */
    public static final String NSZM1254E = "NSZM1254E";
    /** Data has been updated by another user. (table name is [DS_CONTR_BLLG_MTR]) */
    public static final String NSZM1255E = "NSZM1255E";
    /** Data has been updated by another user. (table name is [CONTR_PHYS_BLLG_MTR_RELN]) */
    public static final String NSZM1256E = "NSZM1256E";
    /** Data has been updated by another user. (table name is [CONTR_XS_COPY]) */
    public static final String NSZM1257E = "NSZM1257E";
    /** Data has been updated by another user. (table name is [DS_CONTR_CR_CARD_PO]) */
    public static final String NSZM1258E = "NSZM1258E";
    /** Data has been updated by another user. (table name is [SVC_TERM_COND]) */
    public static final String NSZM1259E = "NSZM1259E";
    /** Data has been updated by another user. (table name is [SVC_MEMO]) */
    public static final String NSZM1260E = "NSZM1260E";
    /** Data has been updated by another user. (table name is [DS_CONTR_RNW_ESCL]) */
    public static final String NSZM1261E = "NSZM1261E";
    /** Data has been updated by another user. (table name is [DS_CONTR_TAX_DTL]) */
    public static final String NSZM1262E = "NSZM1262E";
    /** Data insert failure. (table name is [DS_CONTR_REC]) */
    public static final String NSZM1263E = "NSZM1263E";
    /** Data insert failure. (table name is [DS_CONTR_DTL_REC]) */
    public static final String NSZM1264E = "NSZM1264E";
    /** Data insert failure. (table name is [DS_CONTR_ADDL_CHRG_REC]) */
    public static final String NSZM1265E = "NSZM1265E";
    /** Data insert failure. (table name is [SVC_MEMO_REC]) */
    public static final String NSZM1266E = "NSZM1266E";
    /** DS Contract Class Code cannot be obtained. */
    public static final String NSZM1267E = "NSZM1267E";
    /** Payment Term Cash Discount Code cannot be obtained. */
    public static final String NSZM1268E = "NSZM1268E";
    /** Service Contract Branch Code cannot be obtained. */
    public static final String NSZM1269E = "NSZM1269E";
    /** Service Line of Business Code cannot be obtained. */
    public static final String NSZM1270E = "NSZM1270E";
    /** Contract Admin Person Code cannot be obtained. */
    public static final String NSZM1271E = "NSZM1271E";
    /** Billing Meter Label Code cannot be obtained. */
    public static final String NSZM1272E = "NSZM1272E";
    /** Contract Billing Day is not set to DS Contract Interface Default Rule. */
    public static final String NSZM1273E = "NSZM1273E";
    /** Renew Price Method Code is not set to DS Contract Interface Default Rule. */
    public static final String NSZM1274E = "NSZM1274E";
    /** If you selected Markup Percent, it is required to enter Base Price Up Ratio. */
    public static final String NSZM1275E = "NSZM1275E";
    /** Base Price Up Ratio cannot be set if you don't select Markup Percent. */
    public static final String NSZM1276E = "NSZM1276E";
    /** If you selected Markup Percent, it is required to enter Meter Price Up Ratio. */
    public static final String NSZM1277E = "NSZM1277E";
    /** Meter Price Up Ratio cannot be set if you don't select Markup Percent. */
    public static final String NSZM1278E = "NSZM1278E";
    /** Contract Upliftment Type Code is not set to DS Contract Interface Default Rule. */
    public static final String NSZM1279E = "NSZM1279E";
    /** Upliftment Price Method Code is not set to DS Contract Interface Default Rule. */
    public static final String NSZM1280E = "NSZM1280E";
    /** If you selected Markup Percent, it is required to enter Upliftment Base Price Up Ratio. */
    public static final String NSZM1281E = "NSZM1281E";
    /** Upliftment Base Price Up Ratio cannot be set if you don't select Markup Percent. */
    public static final String NSZM1282E = "NSZM1282E";
    /** If you selected Markup Percent, it is required to enter Upliftment Meter Price Up Ratio. */
    public static final String NSZM1283E = "NSZM1283E";
    /** Upliftment Meter Price Up Ratio cannot be set if you don't select Markup Percent. */
    public static final String NSZM1284E = "NSZM1284E";
    /** Before End Renew Days Amount of Time is not set to DS Contract Interface Default Rule. */
    public static final String NSZM1285E = "NSZM1285E";
    /** The status of this Contract Detail cannot delete. */
    public static final String NSZM1286E = "NSZM1286E";
    /** Ship To Cust Code cannot be obtained. */
    public static final String NSZM1287E = "NSZM1287E";
    /** Data update failure. (table name is [SVC_TERM_COND]) */
    public static final String NSZM1288E = "NSZM1288E";
    /** Data update failure. (table name is [DS_CONTR_TAX_DTL]) */
    public static final String NSZM1289E = "NSZM1289E";
    /** Data update failure. (table name is [SCHD_CRAT_TMPL]) */
    public static final String NSZM1290E = "NSZM1290E";
    // START 2017/08/25 U.Kim [QC#18345, ADD]
    /** It can not be Add Contract, because the Contract has already been invoiced. */
    public static final String NSZM1294E = "NSZM1294E";
    // END 2017/08/25 U.Kim [QC#18345, ADD]
    // S21_NA Add Start QC#21393
    /** Data has been updated by another user. (table name is [SCHD_CRAT_TMPL]) */
    public static final String NSZM1303E = "NSZM1303E";
    /** Data insert failure. (table name is [SCHD_CRAT_TMPL]) */
    public static final String NSZM1304E = "NSZM1304E";
    /** Data has been updated by another user. (table name is [SCHD_CRAT_TMPL_LINE]) */
    public static final String NSZM1305E = "NSZM1305E";
    /** Data insert failure. (table name is [SCHD_CRAT_TMPL_LINE]) */
    public static final String NSZM1306E = "NSZM1306E";
    // S21_NA Add End QC#21393
    // add start 2018/08/22 QC#23920
    /** Billing meter does not exists in contract. */
    public static final String NSZM1345E = "NSZM1345E";
    // add end 2018/08/22 QC#23920
    // START 2023/11/08 R.Jin [QC#62108, DEL]
    // START 2023/07/05 T.Kojima [QC#61472, ADD]
//    /** Contract detail status associated with entered contract# and Config ID is inactive. Please reject this order and correct/resend it from Deal Config. */
//    public static final String NSZM1406E = "NSZM1406E";
    // END 2023/07/05 T.Kojima [QC#61472, ADD]
    // END 2023/11/08 R.Jin [QC#62108, DEL]

    // -- Error evaluation value --
    /** value 1 */
    public static final int ERR_NSZM1171E = 1;
    /** value 2 */
    public static final int ERR_NSZM1173E = 2;
    /** value 3 */
    public static final int ERR_NSZM1174E = 3;

    // -- Business Process Log Parameters --
    /** Sub System ID */
    public static final String SUB_SYS_ID = "NWZ";
    /** Process ID */
    public static final String PROC_ID = "OM";
    /** Document ID */
    public static final String DOC_TP = "OM";

    /** Event : Create */
    public static final String EVENT_NEW = "Create";
    /** Event : Change */
    public static final String EVENT_MOD = "Change";
    /** Event : Cancel */
    public static final String EVENT_DEL = "Cancel";

    /** Event : CPO Svc Dtl */
    public static final String EVENT_CPO_SVC_DTL = "CPO Svc Dtl";
    /** Event : CPO Svc Prc */
    public static final String EVENT_CPO_SVC_PRC = "CPO Svc Prc";
    /** Event : CPO Svc Config Ref */
    public static final String EVENT_CPO_SVC_CONFIG_REF = "CPO Svc Config Ref";
    /** Event : CPO Svc Addl Base Prc */
    public static final String EVENT_CPO_SVC_ADDL_BASE_PRC = "CPO Svc Addl Base Prc";
    /** Event : CPO Svcc Addl Chrg Prc */
    public static final String EVENT_CPO_SVC_ADDL_CHRG_PRC = "CPO Svc Addl Chrg Prc";

    // -- Other ----------------------------
    /** initial size of tMsg. */
    public static final int INIT_TMSG_SIZE = 200;
    /** Line Config */
    public static final String LINE_CONFIG = "01";
    /** check for Multiplier */
    public static final String NSAL0320_MTR_MULT_RATE_FCT_NUM = "NSAL0320_MTR_MULT_RATE_FCT_NUM";
    /** check for Multiplier */
    public static final String NSAL0320_MTR_MULT_RATE_MIN_NUM = "NSAL0320_MTR_MULT_RATE_MIN_NUM";
    /** check for Multiplier */
    public static final String NSAL0320_MTR_MULT_RATE_MAX_NUM = "NSAL0320_MTR_MULT_RATE_MAX_NUM";
    //
    /** max value of unit periodic base : 10,000,000 */
    public static final BigDecimal MAX_BASE_PRC = new BigDecimal(10000000);
    /** max value of tier count : 999,999,999 */
    public static final BigDecimal MAX_TIER_CNT = new BigDecimal(999999999);
    /** max value of xs meter amt rate : 9.99999 */
    public static final BigDecimal MAX_XS_MTR_AMT = new BigDecimal(9.99999);
    /** number of months of one year */
    public static final BigDecimal MTH_12 = BigDecimal.valueOf(12);

    /** Timestamp store pattern */
    public static final String TS_STORE_PATTERN = "yyyyMMddHHmmssSSS";

    /** Zero Padding */
    public static final String STR_ZERO = "0";
    /** length of Shell Line Number */
    public static final int LENGTH_OF_SHELL_LINE_NUM = 10;
    /** length of Model Id */
    public static final int LENGTH_OF_MODEL_ID = 22;
    /** length of PK */
    public static final int LENGTH_OF_PK = 28;
    /** length of Person Code */
    public static final int LENGTH_OF_PSN_CD = 8;

    /** ORD_CATG_BIZ_CTX.ORD_CATG_CTX_TP_CD: EXCL_RENEWAL_MARKUP */
    public static final String ORD_CATG_CTX_TP_CD_EXCL_RENEWAL_MARKUP = "EXCL_RENEWAL_MARKUP";

    /** ORD_CATG_BIZ_CTX.ORD_CATG_CTX_TP_CD: RENTAL_SHELL_REQUIRED */
    public static final String ORD_CATG_CTX_TP_CD_RENTAL_SHELL_REQUIRED = "RENTAL_SHELL_REQUIRED";

    /** ORD_CATG_BIZ_CTX.ORD_CATG_CTX_TP_CD: LEASE_ORDER */
    public static final String ORD_CATG_CTX_TP_CD_LEASE_ORDER = "LEASE_ORDER";

    /** SPCL_RENTAL_ADDL_CHRG_TP_CD_MACH */
    public static final String SPCL_RENTAL_ADDL_CHRG_TP_MACH = "SPCL_RENTAL_ADDL_CHRG_TP_MACH";

    /** SPCL_RENTAL_ADDL_CHRG_TP_CD_ACC */
    public static final String SPCL_RENTAL_ADDL_CHRG_TP_ACC = "SPCL_RENTAL_ADDL_CHRG_TP_ACC";

    // START 2018/05/24 [QC#26062, ADD]
    /** SPCL_DO_NOT_RNW_UPLIFT_SVC_PGM_TP */
    public static final String SPCL_DO_NOT_RNW_UPLIFT_SVC_PGM_TP = "SPCL_DO_NOT_RNW_UPLIFT_SVC_PGM";

    /** SVC_PGM_TP Delimiter */
    public static final String SVC_PGM_TP_DLMT = ",";

    public static final String ORD_CATG_CTX_TP_EMSD_ORDER = "EMSD_ORDER";

    // END   2018/05/24 [QC#26062, ADD]

    /** DOC_ID Delimiter */
    public static final String DOC_ID_DLMT = ".";

    // START 2017/06/19 K.Kitachi [QC#18855, ADD]
    /** Dummy CPO Detail Line Number : 999 */
    public static final String DUMMY_CPO_DTL_LINE_NUM = "999";

    /** Dummy CPO Detail Line Sub Number : 999 */
    public static final String DUMMY_CPO_DTL_LINE_SUB_NUM = "999";
    // END 2017/06/19 K.Kitachi [QC#18855, ADD]

    // START 2017/06/21 K.Kitachi [QC#18068, ADD]
    /** DS_COND_CONST_GRP_ID : NSZC1150_SHELL_UPLFT */
    public static final String NSZC1150_SHELL_UPLFT = "NSZC1150_SHELL_UPLFT";
    // END 2017/06/21 K.Kitachi [QC#18068, ADD]

    // START 2017/06/23 K.Kitachi [QC#18068, ADD]
    /** NumConst : DEF_CONTR_UPLFT_TERM_AOT */
    public static final String NUM_CONST_DEF_CONTR_UPLFT_TERM_AOT = "DEF_CONTR_UPLFT_TERM_AOT";

    /** NumConst Default Value: DEF_CONTR_UPLFT_TERM_AOT */
    public static final BigDecimal DEF_CONTR_UPLFT_TERM_AOT = new BigDecimal(5);
    // END 2017/06/23 K.Kitachi [QC#18068, ADD]

    // START 2017/07/28 [QC#20088, ADD]
    public static final String SVC_MEMO_TRX_NM_CPO_ORD_NUM = "CPO_ORD_NUM";
    // END   2017/07/28 [QC#20088, ADD]

    // START 2019/01/18 [QC#29081, DEL]
//    // START 2017/08/02 [QC#19792, ADD]
//    public static final int MAX_LENGTH_CUST_ISS_PO_NUM = 25;
//    // END   2017/08/02 [QC#19792, ADD]
    // END   2019/01/18 [QC#29081, DEL]

    // add start 2017/08/09 QC#18799
    public static final String ORD_CATG_CTX_TP_SVC_CONTR_CATG = "SVC_CONTR_CATG";
    // add end 2017/08/09 QC#18799

    // START 2017/08/09 K.Kim [QC#20228, ADD]
    public static final String STR_CONST_SCHD_ORD_RSN_MAP = "SCHD_ORD_RSN_MAP";
    // END 2017/08/09 K.Kim [QC#20228, ADD]

    // START 2017/11/20 K.Ochiai [QC#21698, ADD]
    public static final String ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP = "EMSD_CONTR_BR_REP";
    // END 2017/11/20 K.Ochiai [QC#21698, ADD]
}
