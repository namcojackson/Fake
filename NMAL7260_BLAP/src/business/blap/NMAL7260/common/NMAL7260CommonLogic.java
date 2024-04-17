/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7260.common;

import static business.blap.NMAL7260.constant.NMAL7260Constant.COMMA;
import static business.blap.NMAL7260.constant.NMAL7260Constant.DF_AMT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.HDR_NM_MNF_NUM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.HIGH_VAL_DT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0010E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0072E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0163E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0179E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0836E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8020E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8054E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8115E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8186E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8216E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8259E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8320E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8332E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8369E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8538E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NUM_100;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NZZM0003E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.SLASH;
import static business.blap.NMAL7260.constant.NMAL7260Constant.DATE_FORMAT_PADDING_ZERO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.PERCENT_DIGIT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ACCNT_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BILL_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BILL_TO_ACCT_CHANNEL;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BRANCH;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BUSINESS_UNIT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CALL_DATE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CALL_TYPE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CHECK_BOX;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CSMP;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CSV_MAX_CNT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_CLS_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_PO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_DEF;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_EFFECTIVE_DATE_FROM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_EFFECTIVE_DATE_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_FREIGHT_TERM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_FREIGHT_ZONE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ITEM_CD;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_LINE_AMOUNT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_LINE_CATEGORY_LINE_TYPE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_LINE_QTY;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_LINE_QTY_QTYBRK;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MARKETING_MODEL_NAME;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_1;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_2;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_3;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MATERIAL_PRICE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MATERIAL_PRICE_QTYBRK;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MDSE_TYPE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MNF_ITEM_CD;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_MODEL_SEGMENT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ORDER_CATEGORY;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ORDER_LINE_OF_BUSINESS;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ORDER_REASON;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ORDER_SOURCE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ORDER_SUBTOTAL;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PAYMENT_TYPE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PERCENT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PRICE_LIST;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PRICING_DATE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PRODUCT_CD;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PROD_CTRL_1;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PROD_CTRL_2;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PROD_CTRL_3;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PROD_CTRL_4;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_PROD_CTRL_5;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_P_AUDIT_INFO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_QTYDISC;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_REQUEST_DATE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_RETURN_REASON_CODE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_SERVICE_LEVEL;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_SERVICE_MODEL;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_SERVICE_ZONE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_SHIP_TO_ACCT_CLASSIFICATION;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_SPECIAL_HANDLING_TYPE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_TOTAL_QTY;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_TRANSACTION_GROUP;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_TRANSACTION_WEIGHT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_VALUE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.ZZM9015E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.ZZM9004E;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDFStringItem;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.blap.NMAL7260.NMAL7260Query;
import business.blap.NMAL7260.NMAL7260SMsg;
import business.blap.NMAL7260.NMAL7260_BCMsgArray;
import business.blap.NMAL7260.NMAL7260_BSMsg;
import business.blap.NMAL7260.NMAL7260_CCMsg;
import business.blap.NMAL7260.NMAL7260_TSMsg;
import business.blap.NMAL7260.constant.NMAL7260Constant;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.MDSETMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.db.PRC_ADJ_CONDTMsg;
import business.db.PRC_ADJ_DTLTMsg;
import business.db.PRC_FMLATMsg;
import business.db.PRC_RULE_ADJ_TPTMsg;
import business.db.PRC_RULE_CATGTMsg;
import business.db.PRC_RULE_HDRTMsg;
import business.db.SPEC_COND_PRCTMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL7260CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#5934
 * 2016/06/28   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/09/02   Fujitsu         R.Nakamura      Update          QC#6097
 * 2016/09/29   Fujitsu         R.Nakamura      Update          QC#6924
 * 2016/10/03   Fujitsu         R.Nakamura      Update          QC#6931
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/11/09   Fujitsu         T.Murai         Update          QC#6599
 * 2017/08/15   Fujitsu         K.Ishizuka      Update          QC#18237(L3#161)
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20729-2
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/04/12   Fujitsu         H.Nagashima     Update          QC#22593
 * 2018/04/19   Fujitsu         M.Ohno          Update          QC#22569 
 * 2018/05/17   Fujitsu         T.Noguchi       Update          QC#26125
 * 2018/06/06   Fujitsu         T.Noguchi       Update          QC#26491
 * 2018/06/07   Fujitsu         T.Noguchi       Update          QC#26099
 * 2018/07/18   Fujitsu         W.Honda         Update          QC#20267
 * 2018/09/03   Fujitsu         M.Ohno          Update          QC#22600
 * 2018/09/20   Fujitsu         H.Kumagai       Update          QC#9700
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 * 2019/03/06   Fujitsu         S.Kosaka        Update          QC#30643
 * 2019/12/18   Fujitsu         C.Hara          Update          QC#55108
 * 2019/12/26   Fujitsu         C.Hara          Update          QC#55204
 * 2020/01/06   Fujitsu         C.Hara          Update          QC#55303
 * 2020/01/14   Fujitsu         C.Hara          Update          QC#54227-1
 *</pre>
 */
public class NMAL7260CommonLogic {

    /**
     * createTableData
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @param ssmResult S21SsmEZDResult
     */
// QC#28433 2018/10/03 Mod Start
//    public static void createTableData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, S21SsmEZDResult ssmResult) {
//        if (!ssmResult.isCodeNotFound()) {
//            EZDCStringItem fromCStrTxt = bizMsg.prcRuleCondFromTxt_S1;
//            EZDCStringItem toCStrTxt = bizMsg.xxRecNmTxt_S1;
//            EZDCBigDecimalItem fromCBDAmt = bizMsg.prcFmlaPk_S1;
//            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
//                NMAL7260_BSMsg msg = glblMsg.B.no(i);
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_04)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_04.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).csmpNumCmntTxt_04.getValue());
//                    getMstData(PRC_RULE_ATTRB.CSMP_NUM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_04, glblMsg.B.no(i).csmpNumCmntTxt_04, "CSMP#");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_05)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_05.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prcGrpNm_05.getValue());
//                    getMstData(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_05, glblMsg.B.no(i).prcGrpNm_05, "Material Price Group");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_06)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_06.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prodCtrlNm_06.getValue());
//                    getMstData(PRC_RULE_ATTRB.PROD_CTRL_1_BU, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_06, glblMsg.B.no(i).prodCtrlNm_06, "Prod Ctrl -1(BU)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_07)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_07.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prodCtrlNm_07.getValue());
//                    getMstData(PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_07, glblMsg.B.no(i).prodCtrlNm_07, "Prod Ctrl -2(Model Series)CSMP#");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_08)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_08.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).coaMdseTpDescTxt_08.getValue());
//                    getMstData(PRC_RULE_ATTRB.MDSE_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_08, glblMsg.B.no(i).coaMdseTpDescTxt_08, "Mdse Type");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_09)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_09.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).coaProdDescTxt_09.getValue());
//                    getMstData(PRC_RULE_ATTRB.PRODUCT_CODE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_09, glblMsg.B.no(i).coaProdDescTxt_09, "Product Code");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_10)) {
//                    // 2018/07/17 QC#20267 Mod Start
////                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_10.getValue());
////                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).mdseDescShortTxt_10.getValue());
////                    EZDSStringItem mdseCd = glblMsg.B.no(i).prcRuleCondFromTxt_10;
////                    if (getMstData(PRC_RULE_ATTRB.ITEM_CODE, fromCStrTxt, toCStrTxt, mdseCd, glblMsg.B.no(i).mdseDescShortTxt_10, "Item Code")) {
////                        S21SsmEZDResult mdseResult = NMAL7260Query.getInstance().getMdseNm(mdseCd.getValue());
////                        if (!mdseResult.isCodeNotFound()) {
////                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).mdseDescShortTxt_10, (String) mdseResult.getResultObject());
////                        }
////                    }
//                    EZDSStringItem inputMdseCd = glblMsg.B.no(i).prcRuleCondFromTxt_10;
//                    EZDSStringItem mdseDescShortTxt = glblMsg.B.no(i).mdseDescShortTxt_10;
//                    EZDSStringItem mnfItemCd = glblMsg.B.no(i).mnfItemCd_10;
//                    searchMdseInfo(bizMsg, inputMdseCd, mdseDescShortTxt, mnfItemCd);
//                    // 2018/07/17 QC#20267 Mod End
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_11)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_11.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsOrdCatgDescTxt_11.getValue());
//                    getMstData(PRC_RULE_ATTRB.ORDER_CATEGORY, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_11, glblMsg.B.no(i).dsOrdCatgDescTxt_11, "Order Category");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_12)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_12.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsOrdTpDescTxt_12.getValue());
//                    getMstData(PRC_RULE_ATTRB.ORDER_REASON, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_12, glblMsg.B.no(i).dsOrdTpDescTxt_12, "Order Reason");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_13)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_13.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).lineBizTpDescTxt_13.getValue());
//                    getMstData(PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_13, glblMsg.B.no(i).lineBizTpDescTxt_13, "Order Line of Business");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_14)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_14.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prcGrpNm_14.getValue());
//                    getMstData(PRC_RULE_ATTRB.TRANSACTION_GROUP, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_14, glblMsg.B.no(i).prcGrpNm_14, "Transaction Group");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_16)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_16.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).billToAcctNm_16.getValue());
//                    getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_NUM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_16, glblMsg.B.no(i).billToAcctNm_16, "Bill To (Acct#)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_17)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_17.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).coaChDescTxt_17.getValue());
//                    getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_17, glblMsg.B.no(i).coaChDescTxt_17, "Bill To Acct (Channel)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_18)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_18.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsAcctClsDescTxt_18.getValue());
//                    getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_18, glblMsg.B.no(i).dsAcctClsDescTxt_18, "Bill To Acct (Classification)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_19)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_19.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).coaBrDescTxt_19.getValue());
//                    getMstData(PRC_RULE_ATTRB.BRANCH, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_19, glblMsg.B.no(i).coaBrDescTxt_19, "Bill To Acct (Classification)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_20)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_20.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).svcCallTpDescTxt_20.getValue());
//                    getMstData(PRC_RULE_ATTRB.CALL_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_20, glblMsg.B.no(i).svcCallTpDescTxt_20, "Call Type");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_25)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_25.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsOrdLineCatgDescTxt_25.getValue());
//                    getMstData(PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_25, glblMsg.B.no(i).dsOrdLineCatgDescTxt_25, "Line Category (Line Type)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_27)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_27.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).mktMdlDescTxt_27.getValue());
//                    getMstData(PRC_RULE_ATTRB.MARKETING_MODEL_NAME, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_27, glblMsg.B.no(i).mktMdlDescTxt_27, "Marketing Model Name");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_28)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_28.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).mktMdlSegDescTxt_28.getValue());
//                    getMstData(PRC_RULE_ATTRB.MODEL_SEGMENT, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_28, glblMsg.B.no(i).mktMdlSegDescTxt_28, "Model Segment");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_29)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_29.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).cpoSrcTpDescTxt_29.getValue());
//                    getMstData(PRC_RULE_ATTRB.ORDER_SOURCE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_29, glblMsg.B.no(i).cpoSrcTpDescTxt_29, "Order Source");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_31)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_31.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsPmtMethDescTxt_31.getValue());
//                    getMstData(PRC_RULE_ATTRB.PAYMENT_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_31, glblMsg.B.no(i).dsPmtMethDescTxt_31, "Payment Type");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_32)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_32.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prcCatgNm_32.getValue());
//                    getMstData(PRC_RULE_ATTRB.PRICE_LIST, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_32, glblMsg.B.no(i).prcCatgNm_32, "Price List");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_34)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_34.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prodCtrlNm_34.getValue());
//                    getMstData(PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_34, glblMsg.B.no(i).prodCtrlNm_34, "Prod Ctrl -3(Product)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_35)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_35.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prodCtrlNm_35.getValue());
//                    getMstData(PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_35, glblMsg.B.no(i).prodCtrlNm_35, "Prod Ctrl -4(Product-Group)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_36)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_36.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prodCtrlNm_36.getValue());
//                    getMstData(PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_36, glblMsg.B.no(i).prodCtrlNm_36, "Prod Ctrl -5(Product-Type)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_38)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_38.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).rtrnRsnDescTxt_38.getValue());
//                    getMstData(PRC_RULE_ATTRB.RETURN_REASON_CODE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_38, glblMsg.B.no(i).rtrnRsnDescTxt_38, "Return Reason Code");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_39)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_39.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).shpgSvcLvlDescTxt_39.getValue());
//                    getMstData(PRC_RULE_ATTRB.SERVICE_LEVEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_39, glblMsg.B.no(i).shpgSvcLvlDescTxt_39, "Service Level");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_40)) {
//                    // Add Start 2017/09/01 QC#20729-2
//                    EZDSStringItem setValueItem = glblMsg.B.no(i).prcRuleCondFromTxt_40;
//                    if (ZYPCommonFunc.hasValue(setValueItem)) {
//                        NMXC105001PriceMasterUtil.getMoldelIdName(bizMsg.glblCmpyCd.getValue(), setValueItem.getValue(), true, setValueItem);
//                    }
//                    // Add End 2017/09/01 QC#20729-2
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_40.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).mdlDescTxt_40.getValue());
//                    getMstData(PRC_RULE_ATTRB.SERVICE_MODEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_40, glblMsg.B.no(i).mdlDescTxt_40, "Service Model"); // 2017/08/24 QC#20729 Mod
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_41)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_41.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prcSvcZoneDescTxt_41.getValue());
//                    getMstData(PRC_RULE_ATTRB.SERVICE_ZONE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_41, glblMsg.B.no(i).prcSvcZoneDescTxt_41, "Service Zone");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_42)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_42.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsAcctClsDescTxt_42.getValue());
//                    getMstData(PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_42, glblMsg.B.no(i).dsAcctClsDescTxt_42, "Ship To Acct (Classification)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_44)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_44.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).spclHdlgTpDescTxt_44.getValue());
//                    getMstData(PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_44, glblMsg.B.no(i).spclHdlgTpDescTxt_44, "Special Handling Type");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_46)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_46.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).coaExtnDescTxt_46.getValue());
//                    getMstData(PRC_RULE_ATTRB.BUSINESS_UNIT, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_46, glblMsg.B.no(i).coaExtnDescTxt_46, "Business Unit");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_48)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_48.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).frtCondDescTxt_48.getValue());
//                    getMstData(PRC_RULE_ATTRB.FREIGHT_TERM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_48, glblMsg.B.no(i).frtCondDescTxt_48, "Freight Term");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_49)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_49.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).fill40Txt_49.getValue());
//                    getMstData(PRC_RULE_ATTRB.FREIGHT_ZONE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_49, glblMsg.B.no(i).fill40Txt_49, "Freight Zone");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_53)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_53.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prcGrpNm_53.getValue());
//                    getMstData(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_53, glblMsg.B.no(i).prcGrpNm_53, "Customer Price Group(Sold To)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_54)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_54.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsAcctNm_54.getValue());
//                    getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_54, glblMsg.B.no(i).dsAcctNm_54, "Sold To (Acct#)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_55)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_55.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).coaChDescTxt_55.getValue());
//                    getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_55, glblMsg.B.no(i).coaChDescTxt_55, "Sold To Acct (Channel)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_56)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_56.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).dsAcctClsDescTxt_56.getValue());
//                    getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_56, glblMsg.B.no(i).dsAcctClsDescTxt_56, "Sold To Acct (Classification)");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_57)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_57.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).prcGrpNm_57.getValue());
//                    getMstData(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_57, glblMsg.B.no(i).prcGrpNm_57, "Material Price Group(Qty Break)");
//                }
//                // 2018/04/19 QC#22569 add start
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_59)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_59.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).slsMatGrpDescTxt_59.getValue());
//                    getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_1, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_59, glblMsg.B.no(i).slsMatGrpDescTxt_59, "Material Group 1");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_60)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_60.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).slsMatGrpDescTxt_60.getValue());
//                    getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_2, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_60, glblMsg.B.no(i).slsMatGrpDescTxt_60, "Material Group 2");
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_61)) {
//                    ZYPEZDItemValueSetter.setValue(fromCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_61.getValue());
//                    ZYPEZDItemValueSetter.setValue(toCStrTxt, glblMsg.B.no(i).slsMatGrpDescTxt_61.getValue());
//                    getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_3, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_61, glblMsg.B.no(i).slsMatGrpDescTxt_61, "Material Group 3");
//                }
//                // 2018/04/19 QC#22569 add end
//                if (ZYPCommonFunc.hasValue(msg.prcRuleDtlRate_B1)) {
//                    BigDecimal rate = msg.prcRuleDtlRate_B1.getValue().multiply(NUM_100);
//                    ZYPEZDItemValueSetter.setValue(msg.prcRuleDtlRate_B1, rate.setScale(PERCENT_DIGIT, BigDecimal.ROUND_HALF_DOWN));
//                }
//                if (ZYPCommonFunc.hasValue(msg.prcFmlaPk_B1)) {
//                    getMstData(PRC_RULE_ATTRB.FORMULA, fromCBDAmt, toCStrTxt, glblMsg.B.no(i).prcFmlaPk_B1, glblMsg.B.no(i).prcFmlaNm_B1, "Formula");
//                }
//                String cratNm = msg.xxRecHistCratByNm_B1.getValue();
//                String updNm = msg.xxRecHistUpdByNm_B1.getValue();
//                ZYPEZDItemValueSetter.setValue(msg.xxRecHistCratByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(cratNm));
//                ZYPEZDItemValueSetter.setValue(msg.xxRecHistUpdByNm_B1, ZYPRecHistUtil.getFullNameForRecHist(updNm));
//
//                // 2018/06/07 QC#26099 Add Start
//                ZYPEZDItemValueSetter.setValue(msg.prcRuleDtlTxt_B1, formatAmount(msg.prcRuleDtlTxt_B1));
//                // 2018/06/07 QC#26099 Add End
//            }
//        }
//    }
    public static void createTableData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, S21SsmEZDResult ssmResult) {
        if (!ssmResult.isCodeNotFound()) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                NMAL7260_BSMsg msg = glblMsg.B.no(i);
                if (ZYPCommonFunc.hasValue(msg.prcRuleCondFromTxt_40)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).prcRuleCondFromTxt_40, glblMsg.B.no(i).mdlDescTxt_40.getValue());
                }
                // ZYPEZDItemValueSetter.setValue(msg.prcRuleDtlTxt_B1, formatAmount(msg.prcRuleDtlTxt_B1)); // 2019/12/18 QC#55108 Del
            }
        }
    }
    // QC#28433 2018/10/03 Add End

    public static void createTableDataQtyBrk(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, S21SsmEZDResult ssmResult) {
        if (ssmResult == null || ssmResult.isCodeNotFound()) {
            return;
        }
        for (int i = 0; i < glblMsg.T.getValidCount(); i++) {
            NMAL7260_TSMsg msg = glblMsg.T.no(i);
            if (ZYPCommonFunc.hasValue(msg.prcRuleDtlRate_T)) {
                BigDecimal rate = msg.prcRuleDtlRate_T.getValue().multiply(NUM_100);
                ZYPEZDItemValueSetter.setValue(msg.prcRuleDtlRate_T, rate.setScale(PERCENT_DIGIT, BigDecimal.ROUND_HALF_DOWN));
            }
        }
    }

    /**
     * createSaveData
     * @param data String
     * @param nextItem String
     * @return String
     */
    public static String createSaveData(String data, String nextItem) {

        StringBuilder sb = new StringBuilder();
        sb.append(data);
        sb.append(nextItem);
        sb.append(",");

        return new String(sb);
    }

    /**
     * deleteCheck
     * @param bizMsg NMAL7260CMsg
     * @return boolean;
     */
    public static boolean deleteCheck(NMAL7260CMsg bizMsg) {

        Integer cnt = NMAL7260Query.getInstance().getCountTblData(bizMsg);
        if (cnt > 0) {
            bizMsg.setMessageInfo(NMAM8538E, new String[] {"Table Data" });
            return false;
        }
        return false;
    }

    /**
     * deleteRowCheck
     * @param bizMsg NMAL7260CMsg
     * @param selectRows List<Integer>
     * @return boolean
     */
    public static boolean deleteRowCheck(NMAL7260CMsg bizMsg, List<Integer> selectRows) {

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return true;
        }
        // delete(new)
        if (!ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK.getValue())) {
            ZYPTableUtil.deleteRows(bizMsg.C, selectRows);
            setColumn(bizMsg);
            return true;
        }

        for (int idx : selectRows) {
            String prcRuleAttrbCd = bizMsg.C.no(idx).prcRuleAttrbCd_C1.getValue();
            if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
                bizMsg.setMessageInfo(NMAM8054E);
                return true;
            }
        }

        return false;
    }

    public static boolean deletePrcRuleHdr(NMAL7260CMsg bizMsg, String globalCompanyCode, BigDecimal prcRuleHdrPk, String upTime, String upTimeZone) {
        PRC_RULE_HDRTMsg tMsg = new PRC_RULE_HDRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.prcRuleHdrPk, prcRuleHdrPk);
        tMsg = (PRC_RULE_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return true;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(upTime, upTimeZone, tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return true;
            }
        }
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM8020E);
            return true;
        }
        return false;
    }

    /**
     * @param bizMsg
     * @param globalCompanyCode
     * @param prcRuleTrxCondPk
     * @param upTime
     * @param upTimeZone
     * @return
     */
    public static boolean deleteAdjTrxCond(NMAL7260CMsg bizMsg, String globalCompanyCode, BigDecimal prcAdjCondPk, String upTime, String upTimeZone) {
        PRC_ADJ_CONDTMsg tMsg = new PRC_ADJ_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.prcAdjCondPk, prcAdjCondPk);
        tMsg = (PRC_ADJ_CONDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return true;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(upTime, upTimeZone, tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return true;
            }
        }
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM8020E);
            return true;
        }
        return false;
    }

    /**
     * deletePrcAdjDtlTbl
     * @param bizMsg NMAL7260CMsg
     * @param globalCompanyCode String
     * @param prcAdjDtlPk BigDecimal
     * @param upTime String
     * @param upTimeZone String
     * @return Boolean
     */
    public static boolean deletePrcAdjDtlTbl(NMAL7260CMsg bizMsg, String globalCompanyCode, BigDecimal prcAdjDtlPk, String upTime, String upTimeZone) {
        PRC_ADJ_DTLTMsg tMsg = new PRC_ADJ_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.prcAdjDtlPk, prcAdjDtlPk);
        tMsg = (PRC_ADJ_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return true;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(upTime, upTimeZone, tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return true;
            }
        }
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM8020E);
            return true;
        }
        return false;
    }

    /**
     * deletePrcRuleCondGrpTbl
     * @param bizMsg NMAL7260CMsg
     * @param globalCompanyCode String
     * @param specCondPrcPk BigDecimal
     * @param upTime String
     * @param upTimeZone String
     * @return Boolean
     */
    public static boolean deleteSpecCondPrcTbl(NMAL7260CMsg bizMsg, String globalCompanyCode, BigDecimal specCondPrcPk, String upTime, String upTimeZone) {
        SPEC_COND_PRCTMsg tMsg = new SPEC_COND_PRCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(tMsg.specCondPrcPk, specCondPrcPk);
        tMsg = (SPEC_COND_PRCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NZZM0003E);
            return true;
        } else {
            if (!ZYPDateUtil.isSameTimeStamp(upTime, upTimeZone, tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                bizMsg.setMessageInfo(NZZM0003E);
                return true;
            }
        }
        EZDTBLAccessor.logicalRemove(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            bizMsg.setMessageInfo(NMAM8020E);
            return true;
        }
        return false;
    }

    /**
     * isExistRuleNm errorCheck(No.7)
     * @param bizMsg NMAL7260CMsg
     * @return boolean
     */
    public static boolean isExistRuleNm(NMAL7260CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().isExistRuleNm(bizMsg.prcRuleHdrPk_BK.getValue(), bizMsg.prcRuleNm_H1.getValue());
        if (!S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(ssmResult.getResultCode())) {
            bizMsg.prcRuleNm_H1.setErrorInfo(1, NMAM0010E, new String[] {bizMsg.prcRuleNm_H1.getValue() });
            return true;
        }
        return false;
    }

    /**
     * isExistPrcRuleCatgCd errorCheck(No.9)
     * @param bizMsg NMAL7260CMsg
     * @param globalCompanyCode String
     * @return boolean
     */
    public static boolean isExistPrcRuleCatgCd(NMAL7260CMsg bizMsg, String globalCompanyCode) {
        boolean dataFlg = true;
        PRC_RULE_CATGTMsg inTMsg = new PRC_RULE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(inTMsg.prcRuleCatgCd, bizMsg.prcRuleCatgCd_H1);
        PRC_RULE_CATGTMsg outTMsg = (PRC_RULE_CATGTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {bizMsg.prcRuleCatgCd_H1.getValue(), "Price Rule Category" });
            dataFlg = false;
            return dataFlg;
        }
        String[] catgAvalArray = outTMsg.avalPrcRuleModTpTxt.getValue().split(COMMA);
        if (!isExistAvalModTp(catgAvalArray, bizMsg.prcRuleModTpCd_H1.getValue())) {
            bizMsg.prcRuleModTpCd_H1.setErrorInfo(1, NMAM8259E, new String[] {"Price Rule Category" });
            dataFlg = false;
        }

        return dataFlg;
    }

    /**
     * isExistPrcRuleAdjTpCd errorCheck(No.10)
     * @param bizMsg NMAL7260CMsg
     * @param globalCompanyCode String
     * @return boolean
     */
    public static boolean isExistPrcRuleAdjTpCd(NMAL7260CMsg bizMsg, String globalCompanyCode) {
        boolean dataFlg = true;
        PRC_RULE_ADJ_TPTMsg inAdjToTMsg = new PRC_RULE_ADJ_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inAdjToTMsg.glblCmpyCd, globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(inAdjToTMsg.prcRuleAdjTpCd, bizMsg.prcRuleAdjTpCd_H1);
        PRC_RULE_ADJ_TPTMsg outAdjToTMsg = (PRC_RULE_ADJ_TPTMsg) ZYPCodeDataUtil.findByKey(inAdjToTMsg);
        if (outAdjToTMsg == null) {
            bizMsg.prcRuleAdjTpCd_H1.setErrorInfo(1, NMAM0163E, new String[] {bizMsg.prcRuleAdjTpCd_H1.getValue(), "Price Rule Adjustment Type" });
            dataFlg = false;
            return dataFlg;
        }

        String[] adjAvalArray = outAdjToTMsg.avalPrcRuleModTpTxt.getValue().split(COMMA);
        if (!isExistAvalModTp(adjAvalArray, bizMsg.prcRuleModTpCd_H1.getValue())) {
            bizMsg.prcRuleAdjTpCd_H1.setErrorInfo(1, NMAM8259E, new String[] {"Price Rule Adjustment Type" });
            dataFlg = false;
        }

        return dataFlg;
    }

    // isExistAvalModTp
    private static boolean isExistAvalModTp(String[] valArray, String modTpCd) {
        for (String val : valArray) {
            if (modTpCd.equals(val)) {
                return true;
            }
        }
        return false;
    }

    /**
     * isExistMdseNm errorCheck(No.13)
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @param glblCmpyCd String
     * @param cnt int
     * @return boolean
     */
    public static boolean isExistMdseNm(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, String glblCmpyCd, int cnt) {
        boolean dataFlg = true;
        String mdseCd = "";
        boolean isOver = false;

        EZDSStringItem mdseVal = glblMsg.B.no(cnt).prcRuleCondFromTxt_10;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();

        if (mdseVal.getValue().length() > 8) {
            mdseCd = mdseVal.getValue().substring(0, 8);
            isOver = true;
        } else {
            mdseCd = mdseVal.getValue();
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, mdseCd);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg == null) {
            mdseCd = mdseVal.getValue();
        } else {
            if (isOver) {
                glblMsg.B.no(cnt).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM8216E);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
                dataFlg = false;
                if (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > cnt) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(cnt));
                }
                return dataFlg;
            }
            mdseCd = ordTakeOutTMsg.mdseCd.getValue();
        }

        MDSETMsg mdseInTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, mdseCd);
        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        if (mdseOutTMsg == null) {
            // 2018/07/17 QC#20267 Mod Start
//            glblMsg.B.no(cnt).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM0163E, new String[] {mdseCd, "Merchandise" });
//            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
            dataFlg = false;
//            if (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > cnt) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(cnt));
//            }
            return dataFlg;
            // 2018/07/17 QC#20267 Mod End
        }

        return dataFlg;
    }

    // 2018/07/17 QC#20267 Add Start
    /**
     * isExistMdseNm errorCheck(No.13)
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @param glblCmpyCd String
     * @param cnt int
     * @return boolean
     */
    public static boolean isExistMnfItemCd(NMAL7260SMsg glblMsg, String glblCmpyCd, int cnt, S21SsmEZDResult ssmResult) {

        EZDSStringItem mdseVal = glblMsg.B.no(cnt).prcRuleCondFromTxt_10;
        String mdseCd = mdseVal.getValue();
        String baseMdseCd = null;
        List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (1 < ssmResult.getQueryResultCount()) {

            Set<String> mdse8Set = new HashSet<String>();
            for (Map<String, Object> mnfItem : mnfItemList) {
                String mdseCdFromMnfItem = (String) mnfItem.get("MDSE_CD");
                if (8 < mdseCdFromMnfItem.length()) {
                    mdseCdFromMnfItem = mdseCdFromMnfItem.substring(0, 8);
                }
                mdse8Set.add(mdseCdFromMnfItem);
            }
            if (1 < mdse8Set.size()) {
                return false;

            } else {
                // Found one MnfItemCd
                for (String mdse8 : mdse8Set) {
                    baseMdseCd = mdse8;
                    break;
                }
                ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdseTMsg(glblCmpyCd, baseMdseCd);
                if (tMsg == null) {
                    return false;
                }
            }
        } else {
            // Found one MnfItemCd
            baseMdseCd = (String) mnfItemList.get(0).get("MDSE_CD");
        }

        ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdseTMsgArray(glblCmpyCd, baseMdseCd);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(mdseVal, baseMdseCd);

        return true;
    }
    // 2018/07/17 QC#20267 Add End

    /**
     * checkTableData
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @param selectRows List<Integer>
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkTableData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, String glblCmpyCd, List<Integer> selectRows) {
        Map<String, List<String[]>> keyListRec = new HashMap<String, List<String[]>>(glblMsg.B.getValidCount());
        StringBuilder keyInfo = new StringBuilder();
        boolean isError = false;
        List<Integer> rows = new ArrayList<Integer>();

        String[] itemStr = bizMsg.xxComnColOrdTxt.getValue().split(":");
        EZDCStringItem modAttrbItem = null;
        Map<String, String> ruleCmbnExclAvalMap = new HashMap<String, String>();
        Map<BigDecimal, PRC_FMLATMsg> ruleFmlaMap = new HashMap<BigDecimal, PRC_FMLATMsg>();

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            if (PRC_RULE_ATTRB.FORMULA.equals(bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue())) {
                modAttrbItem = bizMsg.C.no(i).prcRuleAttrbCd_C1;
            } else if (PRC_RULE_ATTRB.PERCENT.equals(bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue())) {
                modAttrbItem = bizMsg.C.no(i).prcRuleAttrbCd_C1;
            } else if (PRC_RULE_ATTRB.VALUE.equals(bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue())) {
                modAttrbItem = bizMsg.C.no(i).prcRuleAttrbCd_C1;
            }
        }

        if (isErrPrcRuleCmbnExcl(bizMsg, glblMsg, modAttrbItem, 0, ruleCmbnExclAvalMap, ruleFmlaMap, glblCmpyCd)) {
            isError = true;
        }

        List<BigDecimal> valuePKList = new ArrayList<BigDecimal>();
        for (int i = 0, k = 0; i < glblMsg.B.getValidCount(); i++) {

            if (ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_H1)) {
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcAdjDtlPk_B1.getValue())) {
                    valuePKList.add(glblMsg.B.no(i).prcAdjDtlPk_B1.getValue());
                }
                String prcRuleAttrbCd = NMAL7260CommonLogic.judgeQtyBreak(bizMsg);
                if (prcRuleAttrbCd != null) {
                    rows = getQtyDiscountData(glblMsg, glblMsg.B.no(i).prcAdjDtlPk_B1.getValue());
                }
                for (Integer j : rows) {
                    if (ZYPCommonFunc.hasValue(glblMsg.T.no(j).prcAdjDtlPk_T.getValue())) {
                        valuePKList.add(glblMsg.T.no(j).prcAdjDtlPk_T.getValue());
                    }
                }
            }

            if (k < selectRows.size() && i == selectRows.get(k)) {
                k++;
                continue;
            } else {
                keyInfo = setKeyInfo(glblMsg, itemStr, i);

                if (isErrPrcRuleCmbnExcl(bizMsg, glblMsg, modAttrbItem, i, ruleCmbnExclAvalMap, ruleFmlaMap, glblCmpyCd)) {
                    isError = true;
                }

                String effThruDt = toHighValDate(glblMsg.B.no(i).effThruDt_B1.getValue());
                if (keyListRec.containsKey(keyInfo.toString())) {
                    List<String[]> extList = keyListRec.get(keyInfo.toString());
                    String[] newStr = new String[] {glblMsg.B.no(i).effFromDt_B1.getValue(), effThruDt };
                    extList.add(newStr);
                    keyListRec.put(keyInfo.toString(), extList);
                } else {
                    String[] newStr = new String[] {glblMsg.B.no(i).effFromDt_B1.getValue(), effThruDt };
                    List<String[]> newList = new ArrayList<String[]>();
                    newList.add(newStr);
                    keyListRec.put(keyInfo.toString(), newList);
                }
            }
        }

        for (int k : selectRows) {

            keyInfo = setKeyInfo(glblMsg, itemStr, k);

            if (isErrPrcRuleCmbnExcl(bizMsg, glblMsg, modAttrbItem, k, ruleCmbnExclAvalMap, ruleFmlaMap, glblCmpyCd)) {
                isError = true;
            }

            String effThruDt = toHighValDate(glblMsg.B.no(k).effThruDt_B1.getValue());
            boolean processFlg = true;
            if (keyListRec.containsKey(keyInfo.toString())) {
                List<String[]> extList = keyListRec.get(keyInfo.toString());

                for (String[] extStr : extList) {
                    if (ZYPDateUtil.compare(extStr[0], effThruDt) <= 0 && ZYPDateUtil.compare(glblMsg.B.no(k).effFromDt_B1.getValue(), extStr[1]) <= 0) {
                        glblMsg.B.no(k).xxChkBox_B1.setErrorInfo(1, NMAM0072E, new String[] {"Validity period" });
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
                        isError = true;
                        processFlg = false;
                        if (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > k) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(k));
                        }
                        break;
                    }
                }

                // Call SQL and Check Data
                if (processFlg && valuePKList.size() > 0) {
                    Map<String, Object> params = setParam(bizMsg, glblMsg, k, valuePKList);
                    int count = (Integer) NMAL7260Query.getInstance().countRuleRange(params).getResultObject();

                    if (count > 0) {
                        isError = true;
                        glblMsg.B.no(k).xxChkBox_B1.setErrorInfo(1, NMAM0072E, new String[] {"Validity period" });
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
                        if ((!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > k)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(k));
                        }
                    }
                }

                String[] newStr = new String[] {glblMsg.B.no(k).effFromDt_B1.getValue(), effThruDt };
                extList.add(newStr);
                keyListRec.put(keyInfo.toString(), extList);
            } else {
                String[] newStr = new String[] {glblMsg.B.no(k).effFromDt_B1.getValue(), effThruDt };
                List<String[]> newList = new ArrayList<String[]>();

                // Call SQL and Check Data
                if (processFlg && valuePKList.size() > 0) {
                    Map<String, Object> params = setParam(bizMsg, glblMsg, k, valuePKList);
                    int count = (Integer) NMAL7260Query.getInstance().countRuleRange(params).getResultObject();

                    if (count > 0) {
                        isError = true;
                        glblMsg.B.no(k).xxChkBox_B1.setErrorInfo(1, NMAM0072E, new String[] {"Validity period" });
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
                        if ((!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > k)) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(k));
                        }
                    }
                }

                newList.add(newStr);
                keyListRec.put(keyInfo.toString(), newList);
            }

            // 2018/06/07 QC#26099 Add Start
            if (!checkDecimalDigit(glblMsg.B.no(k).prcRuleDtlTxt_B1, 2)){
                glblMsg.B.no(k).prcRuleDtlTxt_B1.setErrorInfo(1, ZZM9015E, new String[] {"Value" });
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
                isError = true;
                if ((!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > k)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(k));
                }
            }
            // 2018/06/07 QC#26099 Add End
        }
        return isError;
    }

    public static StringBuilder setKeyInfo(NMAL7260SMsg glblMsg, String[] itemStr, int i) {
        StringBuilder keyInfo = new StringBuilder();
        keyInfo.setLength(0);

        for (int j = 0; j < itemStr.length; j++) {
            if (TABLE_ITEM_CSMP.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_04.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MATERIAL_PRICE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_05.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PROD_CTRL_1.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_06.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PROD_CTRL_2.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_07.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MDSE_TYPE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_08.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PRODUCT_CD.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_09.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ITEM_CD.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_10.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ORDER_CATEGORY.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_11.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ORDER_REASON.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_12.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ORDER_LINE_OF_BUSINESS.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_13.getValue()).append(COMMA);
            } else if (TABLE_ITEM_TRANSACTION_GROUP.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_14.getValue()).append(COMMA);
            } else if (TABLE_ITEM_TRANSACTION_WEIGHT.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_15.getValue()).append(COMMA);
            } else if (TABLE_ITEM_BILL_TO.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_16.getValue()).append(COMMA);
            } else if (TABLE_ITEM_BILL_TO_ACCT_CHANNEL.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_17.getValue()).append(COMMA);
            } else if (TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_18.getValue()).append(COMMA);
            } else if (TABLE_ITEM_BRANCH.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_19.getValue()).append(COMMA);
            } else if (TABLE_ITEM_CALL_TYPE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_20.getValue()).append(COMMA);
            } else if (TABLE_ITEM_CALL_DATE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).xxSvcCallDt_FR.getValue()).append(COMMA);
            } else if (TABLE_ITEM_CUSTOMER_PO.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_22.getValue()).append(COMMA);
            } else if (TABLE_ITEM_LINE_AMOUNT.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_24.getValue()).append(COMMA);
            } else if (TABLE_ITEM_LINE_CATEGORY_LINE_TYPE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_25.getValue()).append(COMMA);
            } else if (TABLE_ITEM_LINE_QTY.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_26.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MARKETING_MODEL_NAME.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_27.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MODEL_SEGMENT.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_28.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ORDER_SOURCE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_29.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ORDER_SUBTOTAL.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_30.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PAYMENT_TYPE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_31.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PRICE_LIST.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_32.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PRICING_DATE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcDt_FR.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PROD_CTRL_3.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_34.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PROD_CTRL_4.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_35.getValue()).append(COMMA);
            } else if (TABLE_ITEM_PROD_CTRL_5.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_36.getValue()).append(COMMA);
            } else if (TABLE_ITEM_REQUEST_DATE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).xxRqstDt_FR.getValue()).append(COMMA);
            } else if (TABLE_ITEM_RETURN_REASON_CODE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_38.getValue()).append(COMMA);
            } else if (TABLE_ITEM_SERVICE_LEVEL.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_39.getValue()).append(COMMA);
            } else if (TABLE_ITEM_SERVICE_MODEL.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_40.getValue()).append(COMMA);
            } else if (TABLE_ITEM_SERVICE_ZONE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_41.getValue()).append(COMMA);
            } else if (TABLE_ITEM_SHIP_TO_ACCT_CLASSIFICATION.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_42.getValue()).append(COMMA);
            } else if (TABLE_ITEM_SPECIAL_HANDLING_TYPE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_44.getValue()).append(COMMA);
            } else if (TABLE_ITEM_TOTAL_QTY.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_45.getValue()).append(COMMA);
            } else if (TABLE_ITEM_BUSINESS_UNIT.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_46.getValue()).append(COMMA);
            } else if (TABLE_ITEM_FREIGHT_TERM.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_48.getValue()).append(COMMA);
            } else if (TABLE_ITEM_FREIGHT_ZONE.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_49.getValue()).append(COMMA);
            } else if (TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_53.getValue()).append(COMMA);
            } else if (TABLE_ITEM_ACCNT_SOLD_TO.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_54.getValue()).append(COMMA);
            } else if (TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_55.getValue()).append(COMMA);
            } else if (TABLE_ITEM_CUSTOMER_CLS_SOLD_TO.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_56.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MATERIAL_PRICE_QTYBRK.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_57.getValue()).append(COMMA);
            } else if (TABLE_ITEM_LINE_QTY_QTYBRK.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_58.getValue()).append(COMMA);
            // 2018/04/19 QC#22569 add start
            } else if (TABLE_ITEM_MATERIAL_GRP_1.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_59.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MATERIAL_GRP_2.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_60.getValue()).append(COMMA);
            } else if (TABLE_ITEM_MATERIAL_GRP_3.equals(itemStr[j])) {
                keyInfo = keyInfo.append(glblMsg.B.no(i).prcRuleCondFromTxt_61.getValue()).append(COMMA);
            }
            // 2018/04/19 QC#22569 add end
        }

        return keyInfo;
    }

    public static Map<String, Object> setParam(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, int selectRow, List<BigDecimal> valuePKList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            String attrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
            if (PRC_RULE_ATTRB.FORMULA.equals(attrbCd) || PRC_RULE_ATTRB.PERCENT.equals(attrbCd) || PRC_RULE_ATTRB.VALUE.equals(attrbCd)) {
                continue;
            }

            if (PRC_RULE_ATTRB.CSMP_NUM.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_04", glblMsg.B.no(selectRow).prcRuleCondFromTxt_04);
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_05", glblMsg.B.no(selectRow).prcRuleCondFromTxt_05);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_06", glblMsg.B.no(selectRow).prcRuleCondFromTxt_06);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_07", glblMsg.B.no(selectRow).prcRuleCondFromTxt_07);
            } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_08", glblMsg.B.no(selectRow).prcRuleCondFromTxt_08);
            } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_09", glblMsg.B.no(selectRow).prcRuleCondFromTxt_09);
            } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_10", glblMsg.B.no(selectRow).prcRuleCondFromTxt_10);
            } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_11", glblMsg.B.no(selectRow).prcRuleCondFromTxt_11);
            } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_12", glblMsg.B.no(selectRow).prcRuleCondFromTxt_12);
            } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_13", glblMsg.B.no(selectRow).prcRuleCondFromTxt_13);
            } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_14", glblMsg.B.no(selectRow).prcRuleCondFromTxt_14);
            } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_15", glblMsg.B.no(selectRow).prcRuleCondFromTxt_15);
                params.put("prcRuleCondThruTxt_15", glblMsg.B.no(selectRow).prcRuleCondThruTxt_15);
            } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_16", glblMsg.B.no(selectRow).prcRuleCondFromTxt_16);
            } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_17", glblMsg.B.no(selectRow).prcRuleCondFromTxt_17);
            } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_18", glblMsg.B.no(selectRow).prcRuleCondFromTxt_18);
            } else if (PRC_RULE_ATTRB.BRANCH.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_19", glblMsg.B.no(selectRow).prcRuleCondFromTxt_19);
            } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_20", glblMsg.B.no(selectRow).prcRuleCondFromTxt_20);
            } else if (PRC_RULE_ATTRB.CALL_DATE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_21", glblMsg.B.no(selectRow).xxSvcCallDt_FR);
                params.put("prcRuleCondThruTxt_21", glblMsg.B.no(selectRow).xxSvcCallDt_TH);
            } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_22", glblMsg.B.no(selectRow).prcRuleCondFromTxt_22);
                params.put("prcRuleCondThruTxt_22", glblMsg.B.no(selectRow).prcRuleCondFromTxt_22);
            } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_24", glblMsg.B.no(selectRow).prcRuleCondFromTxt_24);
                params.put("prcRuleCondThruTxt_24", glblMsg.B.no(selectRow).prcRuleCondFromTxt_24);
            } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_25", glblMsg.B.no(selectRow).prcRuleCondFromTxt_25);
            } else if (PRC_RULE_ATTRB.LINE_QTY.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_26", glblMsg.B.no(selectRow).prcRuleCondFromTxt_26);
                params.put("prcRuleCondThruTxt_26", glblMsg.B.no(selectRow).prcRuleCondFromTxt_26);
            } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_27", glblMsg.B.no(selectRow).prcRuleCondFromTxt_27);
            } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_28", glblMsg.B.no(selectRow).prcRuleCondFromTxt_28);
            } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_29", glblMsg.B.no(selectRow).prcRuleCondFromTxt_29);
            } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_30", glblMsg.B.no(selectRow).prcRuleCondFromTxt_30);
                params.put("prcRuleCondThruTxt_30", glblMsg.B.no(selectRow).prcRuleCondFromTxt_30);
            } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_31", glblMsg.B.no(selectRow).prcRuleCondFromTxt_31);
            } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_32", glblMsg.B.no(selectRow).prcRuleCondFromTxt_32);
            } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_33", glblMsg.B.no(selectRow).prcDt_FR);
                params.put("prcRuleCondThruTxt_33", glblMsg.B.no(selectRow).prcDt_TH);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_34", glblMsg.B.no(selectRow).prcRuleCondFromTxt_34);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_35", glblMsg.B.no(selectRow).prcRuleCondFromTxt_35);
            } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_36", glblMsg.B.no(selectRow).prcRuleCondFromTxt_36);
            } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_37", glblMsg.B.no(selectRow).xxRqstDt_FR);
                params.put("prcRuleCondThruTxt_37", glblMsg.B.no(selectRow).xxRqstDt_TH);
            } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_38", glblMsg.B.no(selectRow).prcRuleCondFromTxt_38);
            } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_39", glblMsg.B.no(selectRow).prcRuleCondFromTxt_39);
            } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_40", glblMsg.B.no(selectRow).prcRuleCondFromTxt_40);
            } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_41", glblMsg.B.no(selectRow).prcRuleCondFromTxt_41);
            } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_42", glblMsg.B.no(selectRow).prcRuleCondFromTxt_42);
            } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_44", glblMsg.B.no(selectRow).prcRuleCondFromTxt_44);
            } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_45", glblMsg.B.no(selectRow).prcRuleCondFromTxt_45);
                params.put("prcRuleCondThruTxt_45", glblMsg.B.no(selectRow).prcRuleCondFromTxt_45);
            } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_46", glblMsg.B.no(selectRow).prcRuleCondFromTxt_46);
            } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_48", glblMsg.B.no(selectRow).prcRuleCondFromTxt_48);
            } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_49", glblMsg.B.no(selectRow).prcRuleCondFromTxt_49);
            } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_53", glblMsg.B.no(selectRow).prcRuleCondFromTxt_53);
            } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_54", glblMsg.B.no(selectRow).prcRuleCondFromTxt_54);
            } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_55", glblMsg.B.no(selectRow).prcRuleCondFromTxt_55);
            } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_56", glblMsg.B.no(selectRow).prcRuleCondFromTxt_56);
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_57", glblMsg.B.no(selectRow).prcRuleCondFromTxt_57);
            } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_58", glblMsg.B.no(selectRow).prcRuleCondFromTxt_58);
                params.put("prcRuleCondThruTxt_58", glblMsg.B.no(selectRow).prcRuleCondThruTxt_58);
            // 2018/04/19 QC#22569 add start
            } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_59", glblMsg.B.no(selectRow).prcRuleCondFromTxt_59);
            } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_60", glblMsg.B.no(selectRow).prcRuleCondFromTxt_60);
            } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(attrbCd)) {
                params.put("prcRuleCondFromTxt_61", glblMsg.B.no(selectRow).prcRuleCondFromTxt_61);
            }
            // 2018/04/19 QC#22569 add end
        }

        params.put("effFromDt", glblMsg.B.no(selectRow).effFromDt_B1.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.B.no(selectRow).effThruDt_B1.getValue())) {
            params.put("effThruDt", glblMsg.B.no(selectRow).effThruDt_B1.getValue());
        } else {
            params.put("effThruDt", HIGH_VAL_DT);
        }
        BigDecimal[] paramValuePKList = new BigDecimal[valuePKList.size()];
        valuePKList.toArray(paramValuePKList);
        params.put("paramValuePKList", paramValuePKList);

        return params;
    }

    /**
     * checkCommonTableData. errorCheck(No.8)
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @return boolean
     */
    public static boolean checkCommonTableData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {

        String qtyDisc = NMAL7260CommonLogic.judgeQtyBreak(bizMsg);
        boolean isError = false;

        // required check
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            String inpReqFlg = bizMsg.C.no(i).inpReqFlg_C1.getValue();
            String prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
            boolean requiredErr = false;
            for (int j : selectRows) {
                if ("Y".equals(inpReqFlg)) {
                    if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
                        if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcFmlaPk_B1)) {
                            glblMsg.B.no(j).prcFmlaPk_B1.setErrorInfo(1, NMAM0836E, new String[] {"Formula" });
                            requiredErr = true;
                        }
                    } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
                        // if (!ZYPCommonFunc.hasValue(qtyDisc)) { // QC#22334 2018/01/12 Del
                        if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleDtlRate_B1)) {
                            glblMsg.B.no(j).prcRuleDtlRate_B1.setErrorInfo(1, NMAM0836E, new String[] {"Percent" });
                            requiredErr = true;
                        }
                        // }
                    } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
                        // if (!ZYPCommonFunc.hasValue(qtyDisc)) { // QC#22334 2018/01/12 Del
                        if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleDtlTxt_B1)) {
                            glblMsg.B.no(j).prcRuleDtlTxt_B1.setErrorInfo(1, NMAM0836E, new String[] {"Value" });
                            requiredErr = true;
                        }
                        // }
                    } else {
                        if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_04)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_04.setErrorInfo(1, NMAM0836E, new String[] {"CSMP#" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_05)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_05.setErrorInfo(1, NMAM0836E, new String[] {"Material Price Group" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_06)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_06.setErrorInfo(1, NMAM0836E, new String[] {"Prod Ctrl-1(BU)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_07)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_07.setErrorInfo(1, NMAM0836E, new String[] {"Prod Ctrl-2(Model Series)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_08)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_08.setErrorInfo(1, NMAM0836E, new String[] {"Mdse Type" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_09)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_09.setErrorInfo(1, NMAM0836E, new String[] {"Product Code" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_10)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM0836E, new String[] {"Item Code" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_11)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_12.setErrorInfo(1, NMAM0836E, new String[] {"Order Category" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_12)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_12.setErrorInfo(1, NMAM0836E, new String[] {"Order Reason" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_13)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_13.setErrorInfo(1, NMAM0836E, new String[] {"Order Line of Business" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_14)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_14.setErrorInfo(1, NMAM0836E, new String[] {"Transaction Group" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_15)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_15.setErrorInfo(1, NMAM0836E, new String[] {"Total Transaction Weight From" });
                                requiredErr = true;
                            }
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_15)) {
                                glblMsg.B.no(j).prcRuleCondThruTxt_15.setErrorInfo(1, NMAM0836E, new String[] {"Total Transaction Weight To" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_16)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_16.setErrorInfo(1, NMAM0836E, new String[] {"Bill To (Acct#)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_17)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_17.setErrorInfo(1, NMAM0836E, new String[] {"Bill To Acct (Channel)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_18)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_18.setErrorInfo(1, NMAM0836E, new String[] {"Bill To Acct (Classification)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_19)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_19.setErrorInfo(1, NMAM0836E, new String[] {"Branch" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_20)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_20.setErrorInfo(1, NMAM0836E, new String[] {"Call Type" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).xxSvcCallDt_FR)) {
                                glblMsg.B.no(j).xxSvcCallDt_FR.setErrorInfo(1, NMAM0836E, new String[] {"Call Date From" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_22)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_22.setErrorInfo(1, NMAM0836E, new String[] {"Customer PO From" });
                                requiredErr = true;
                            }
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_22)) {
                                glblMsg.B.no(j).prcRuleCondThruTxt_22.setErrorInfo(1, NMAM0836E, new String[] {"Customer PO To" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_24)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_24.setErrorInfo(1, NMAM0836E, new String[] {"Line Amount From" });
                                requiredErr = true;
                            }
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_24)) {
                                glblMsg.B.no(j).prcRuleCondThruTxt_24.setErrorInfo(1, NMAM0836E, new String[] {"Line Amount Name" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_25)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_25.setErrorInfo(1, NMAM0836E, new String[] {"Line Category (Line Type)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(qtyDisc)) {
                                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_26)) {
                                    glblMsg.B.no(j).prcRuleCondFromTxt_26.setErrorInfo(1, NMAM0836E, new String[] {"Line Qty From" });
                                    requiredErr = true;
                                }
                                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_26)) {
                                    glblMsg.B.no(j).prcRuleCondThruTxt_26.setErrorInfo(1, NMAM0836E, new String[] {"Line Qty To" });
                                    requiredErr = true;
                                }
                            }
                        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_27)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_27.setErrorInfo(1, NMAM0836E, new String[] {"Marketing Model Name" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_28)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_28.setErrorInfo(1, NMAM0836E, new String[] {"Model Segment" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_29)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_29.setErrorInfo(1, NMAM0836E, new String[] {"Order Source" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_30)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_30.setErrorInfo(1, NMAM0836E, new String[] {"Order Subtotal From" });
                                requiredErr = true;
                            }
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_30)) {
                                glblMsg.B.no(j).prcRuleCondThruTxt_30.setErrorInfo(1, NMAM0836E, new String[] {"Order Subtotal To" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_31)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_31.setErrorInfo(1, NMAM0836E, new String[] {"Payment Type" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_32)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_32.setErrorInfo(1, NMAM0836E, new String[] {"Price List" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcDt_FR)) {
                                glblMsg.B.no(j).prcDt_FR.setErrorInfo(1, NMAM0836E, new String[] {"Pricing Date From" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_34)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_34.setErrorInfo(1, NMAM0836E, new String[] {"Prod Ctrl -3(Product)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_35)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_35.setErrorInfo(1, NMAM0836E, new String[] {"Prod Ctrl -4(Product-Group)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_36)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_36.setErrorInfo(1, NMAM0836E, new String[] {"Prod Ctrl -5(Product-Type)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).xxRqstDt_TH)) {
                                glblMsg.B.no(j).xxRqstDt_TH.setErrorInfo(1, NMAM0836E, new String[] {"Request Date From" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_38)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_38.setErrorInfo(1, NMAM0836E, new String[] {"Return Reason Code" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_39)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_39.setErrorInfo(1, NMAM0836E, new String[] {"Service Level" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_40)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_40.setErrorInfo(1, NMAM0836E, new String[] {"Service Model" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_41)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_41.setErrorInfo(1, NMAM0836E, new String[] {"Service Zone" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_42)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_42.setErrorInfo(1, NMAM0836E, new String[] {"Ship To Acct (Classification)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_44)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_44.setErrorInfo(1, NMAM0836E, new String[] {"Special Handling Type" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_45)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_45.setErrorInfo(1, NMAM0836E, new String[] {"Total Qty From" });
                                requiredErr = true;
                            }
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_45)) {
                                glblMsg.B.no(j).prcRuleCondThruTxt_45.setErrorInfo(1, NMAM0836E, new String[] {"Total Qty To" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_46)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_46.setErrorInfo(1, NMAM0836E, new String[] {"Business Unit" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_48)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_48.setErrorInfo(1, NMAM0836E, new String[] {"Freight Term" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_49)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_49.setErrorInfo(1, NMAM0836E, new String[] {"Freight Zone" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_53)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_53.setErrorInfo(1, NMAM0836E, new String[] {"Customer Price Group(Sold To)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_54)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_54.setErrorInfo(1, NMAM0836E, new String[] {"Sold To (Acct#)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_55)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_55.setErrorInfo(1, NMAM0836E, new String[] {"Sold To Acct (Channel)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_56)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_56.setErrorInfo(1, NMAM0836E, new String[] {"Sold To Acct (Classification)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_57)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_57.setErrorInfo(1, NMAM0836E, new String[] {"Material Price Group(QtyBrk)" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(qtyDisc)) {
                                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_58)) {
                                    glblMsg.B.no(j).prcRuleCondFromTxt_58.setErrorInfo(1, NMAM0836E, new String[] {"Line Qty From(QtyBrk)" });
                                    requiredErr = true;
                                }
                                if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondThruTxt_58)) {
                                    glblMsg.B.no(j).prcRuleCondThruTxt_58.setErrorInfo(1, NMAM0836E, new String[] {"Line Qty To(QtyBrk)" });
                                    requiredErr = true;
                                }
                            }
                        // 2018/04/19 QC#22569 add start
                        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_59)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_59.setErrorInfo(1, NMAM0836E, new String[] {"Material Group 1" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_60)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_60.setErrorInfo(1, NMAM0836E, new String[] {"Material Group 2" });
                                requiredErr = true;
                            }
                        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd)) {
                            if (!ZYPCommonFunc.hasValue(glblMsg.B.no(j).prcRuleCondFromTxt_61)) {
                                glblMsg.B.no(j).prcRuleCondFromTxt_61.setErrorInfo(1, NMAM0836E, new String[] {"Material Group 3" });
                                requiredErr = true;
                            }
                        }
                        // 2018/04/19 QC#22569 add start
                    }
                }

                // Effective Date From To Check(Call Date)
                if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
                    String callFromBDt = glblMsg.B.no(j).xxSvcCallDt_FR.getValue();
                    String callToBDt = glblMsg.B.no(j).xxSvcCallDt_TH.getValue();
                    if (!ZYPCommonFunc.hasValue(callToBDt)) {
                        callToBDt = HIGH_VAL_DT;
                    }
                    if (!ZYPCommonFunc.hasValue(callFromBDt)) {
                        glblMsg.B.no(j).xxSvcCallDt_FR.setErrorInfo(1, NMAM0836E, new String[] {"Call Date From" });
                        requiredErr = true;
                    } else if (callFromBDt.compareTo(callToBDt) > 0) {
                        glblMsg.B.no(j).xxSvcCallDt_FR.setErrorInfo(1, NMAM8115E);
                        requiredErr = true;
                    }
                }

                // Effective Date From To Check(Pricing Date)
                if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
                    String prcFromBDt = glblMsg.B.no(j).prcDt_FR.getValue();
                    String prcToBDt = glblMsg.B.no(j).prcDt_TH.getValue();
                    if (!ZYPCommonFunc.hasValue(prcToBDt)) {
                        prcToBDt = HIGH_VAL_DT;
                    }
                    if (!ZYPCommonFunc.hasValue(prcFromBDt)) {
                        glblMsg.B.no(j).prcDt_FR.setErrorInfo(1, NMAM0836E, new String[] {"Pricing Date From" });
                        requiredErr = true;
                    } else if (prcFromBDt.compareTo(prcToBDt) > 0) {
                        glblMsg.B.no(j).prcDt_FR.setErrorInfo(1, NMAM8115E);
                        requiredErr = true;
                    }
                }

                // Effective Date From To Check(Request Date)
                if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
                    String rqstFromBDt = glblMsg.B.no(j).xxRqstDt_FR.getValue();
                    String rqstToBDt = glblMsg.B.no(j).xxRqstDt_TH.getValue();
                    if (!ZYPCommonFunc.hasValue(rqstToBDt)) {
                        rqstToBDt = HIGH_VAL_DT;
                    }
                    if (!ZYPCommonFunc.hasValue(rqstFromBDt)) {
                        glblMsg.B.no(j).xxRqstDt_FR.setErrorInfo(1, NMAM0836E, new String[] {"Request Date From" });
                        requiredErr = true;
                    } else if (rqstFromBDt.compareTo(rqstToBDt) > 0) {
                        glblMsg.B.no(j).xxRqstDt_FR.setErrorInfo(1, NMAM8115E);
                        requiredErr = true;
                    }
                }
                if (requiredErr && (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > j)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(j));
                    isError = true;
                }
            }
        }

        // 2019/12/18 QC#55108 Add Start
        boolean checkNumericErr = false;
        for (int l : selectRows) {
            EZDSStringItem target;
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_05)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_05;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Material Price Group" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_14)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_14;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Transaction Group" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_15)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_15;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Total Transaction Weight From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_15)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_15;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Total Transaction Weight To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_24)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_24;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Line Amount From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_24)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_24;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Line Amount To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_26)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_26;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Line Qty From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_26)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_26;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Line Qty To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_30)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_30;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Order Subtotal From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_30)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_30;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Order Subtotal To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_45)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_45;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Total Qty From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_45)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_45;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Total Qty To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_45)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_45;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Total Qty From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_45)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_45;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Total Qty To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_49)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_49;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Freight Zone" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_53)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_53;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Customer Price Group(Sold To)" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_57)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_57;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Material Price Group(Qty Break)" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondFromTxt_58)) {
                target =  glblMsg.B.no(l).prcRuleCondFromTxt_58;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Line Qty(Qty Break) From" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleCondThruTxt_58)) {
                target =  glblMsg.B.no(l).prcRuleCondThruTxt_58;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Line Qty(Qty Break) To" });
                    checkNumericErr = true;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(l).prcRuleDtlTxt_B1)) {
                target =  glblMsg.B.no(l).prcRuleDtlTxt_B1;
                target.setValue(target.getValue().replace(",", ""));
                if (checkItemTypeBigDecimal(target.getValue())){
                    target.setErrorInfo(1, ZZM9004E, new String[] {"Value" });
                    checkNumericErr = true;
                }
            }
            if (checkNumericErr && (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > l)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(l));
                isError = true;
                return isError;
            }
        }
        // 2019/12/18 QC#55108 Add End

        boolean checkErr = false;
        for (int k : selectRows) {
            // WEIGHTCheck
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_15) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_15)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_15.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_15.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_15.setErrorInfo(1, NMAM8332E, new String[] {"Total Transaction Weight To" });
                    checkErr = true;
                }
            }
            // Customer PO Check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_22) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_22)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_22.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_22.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_22.setErrorInfo(1, NMAM8332E, new String[] {"Customer PO To" });
                    checkErr = true;
                }
            }
            // Line Amount Check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_24) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_24)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_24.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_24.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_24.setErrorInfo(1, NMAM8332E, new String[] {"Line Amount Name" });
                    checkErr = true;
                }
            }
            // Line Qty Check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_26) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_26)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_26.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_26.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_26.setErrorInfo(1, NMAM8332E, new String[] {"Line Qty To" });
                    checkErr = true;
                }
            }
            // Order Subtotal Check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_30) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_30)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_30.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_30.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_30.setErrorInfo(1, NMAM8332E, new String[] {"Order Subtotal To" });
                    checkErr = true;
                }
            }
            // Total Qty Check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_45) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_45)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_45.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_45.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_45.setErrorInfo(1, NMAM8332E, new String[] {"Total Qty To" });
                    checkErr = true;
                }
            }
            // Line Qty(QtyBrk) Check
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondFromTxt_58) && ZYPCommonFunc.hasValue(glblMsg.B.no(k).prcRuleCondThruTxt_58)) {
                String fromWt = glblMsg.B.no(k).prcRuleCondFromTxt_58.getValue();
                String toWt = glblMsg.B.no(k).prcRuleCondThruTxt_58.getValue();

                if ((new BigDecimal(fromWt)).compareTo(new BigDecimal(toWt)) > 0) {
                    glblMsg.B.no(k).prcRuleCondThruTxt_26.setErrorInfo(1, NMAM8332E, new String[] {"Line Qty To(QtyBrk)" });
                    checkErr = true;
                }
            }

            // Effective Date From To Check(Table)
            String fromBDt = glblMsg.B.no(k).effFromDt_B1.getValue();
            String toBDt = glblMsg.B.no(k).effThruDt_B1.getValue();
            if (!ZYPCommonFunc.hasValue(toBDt)) {
                toBDt = HIGH_VAL_DT;
            }
            if (!ZYPCommonFunc.hasValue(fromBDt)) {
                glblMsg.B.no(k).effFromDt_B1.setErrorInfo(1, NMAM0836E, new String[] {"Effective Date From" });
                checkErr = true;
            } else if (fromBDt.compareTo(toBDt) > 0) {
                glblMsg.B.no(k).effFromDt_B1.setErrorInfo(1, NMAM8115E);
                checkErr = true;
            }

            if (checkErr && (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > k)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(k));
                isError = true;
            }
        }
        //QC#22593 add Start
        if (isError) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
        }
        //QC#22593 add End

        return isError;
    }

    private static boolean isErrPrcRuleCmbnExcl(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, EZDCStringItem modAttrbItem, int idx, Map<String, String> ruleCmbnExclAvalMap, Map<BigDecimal, PRC_FMLATMsg> ruleFmlaMap, String glblCmpyCd) {
        PRC_FMLATMsg prcFmlaInTMsg = new PRC_FMLATMsg();
        PRC_FMLATMsg prcFmlaTMsg = null;
        String prcFmlaTpCd = "";
        String prcFuncTpCd = "";
        String ruleCmbnExclExistsFlg = null;
        String ruleCmbnExclKey = null;

        if (!PRC_RULE_ATTRB.FORMULA.equals(modAttrbItem.getValue())) {
            ruleCmbnExclKey = S21StringUtil.concatStrings(bizMsg.prcRuleModTpCd_H1.getValue(), COMMA, bizMsg.prcRuleAdjTpCd_H1.getValue(), COMMA, bizMsg.prcRuleAdjLvlCd_H1.getValue(), COMMA, modAttrbItem.getValue(), COMMA, prcFmlaTpCd,
                    COMMA, prcFuncTpCd);
            if (ruleCmbnExclAvalMap.containsKey(ruleCmbnExclKey)) {
                ruleCmbnExclExistsFlg = ruleCmbnExclAvalMap.get(ruleCmbnExclKey);
            } else {
                if (isExistsPrcRuleCmbnExcl(bizMsg, modAttrbItem.getValue(), prcFmlaTpCd, prcFuncTpCd)) {
                    ruleCmbnExclExistsFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    ruleCmbnExclExistsFlg = ZYPConstant.FLG_OFF_N;
                }
            }
        } else {
            prcFmlaTMsg = null;
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(idx).prcFmlaPk_B1)) {
                if (ruleFmlaMap.containsKey(glblMsg.B.no(idx).prcFmlaPk_B1.getValue())) {
                    prcFmlaTMsg = ruleFmlaMap.get(glblMsg.B.no(idx).prcFmlaPk_B1.getValue());
                    if (prcFmlaTMsg == null) {
                        glblMsg.B.no(idx).prcFmlaPk_B1.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(idx).prcFmlaPk_B1.getValue().toString(), "Price Formula" });
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
                        return true;
                    }
                } else {
                    prcFmlaInTMsg.clear();
                    ZYPEZDItemValueSetter.setValue(prcFmlaInTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(prcFmlaInTMsg.prcFmlaPk, glblMsg.B.no(idx).prcFmlaPk_B1);
                    prcFmlaTMsg = (PRC_FMLATMsg) EZDTBLAccessor.findByKey(prcFmlaInTMsg);
                    if (prcFmlaTMsg == null) {
                        glblMsg.B.no(idx).prcFmlaPk_B1.setErrorInfo(1, NMAM0163E, new String[] {glblMsg.B.no(idx).prcFmlaPk_B1.getValue().toString(), "Price Formula" });
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
                        return true;
                    }
                    ruleFmlaMap.put(glblMsg.B.no(idx).prcFmlaPk_B1.getValue(), prcFmlaTMsg);
                }
            }

            if (prcFmlaTMsg == null) {
                prcFmlaTpCd = "";
                prcFuncTpCd = "";
            } else {
                prcFmlaTpCd = prcFmlaTMsg.prcFmlaTpCd.getValue();
                prcFuncTpCd = prcFmlaTMsg.prcFuncTpCd.getValue();
            }

            ruleCmbnExclKey = S21StringUtil.concatStrings(bizMsg.prcRuleModTpCd_H1.getValue(), COMMA, bizMsg.prcRuleAdjTpCd_H1.getValue(), COMMA, bizMsg.prcRuleAdjLvlCd_H1.getValue(), COMMA, modAttrbItem.getValue(), COMMA, prcFmlaTpCd,
                    COMMA, prcFuncTpCd);

            if (ruleCmbnExclAvalMap.containsKey(ruleCmbnExclKey)) {
                ruleCmbnExclExistsFlg = ruleCmbnExclAvalMap.get(ruleCmbnExclKey);
            } else {
                if (isExistsPrcRuleCmbnExcl(bizMsg, modAttrbItem.getValue(), prcFmlaTpCd, prcFuncTpCd)) {
                    ruleCmbnExclExistsFlg = ZYPConstant.FLG_ON_Y;
                } else {
                    ruleCmbnExclExistsFlg = ZYPConstant.FLG_OFF_N;
                }
            }
        }

        ruleCmbnExclAvalMap.put(ruleCmbnExclKey, ruleCmbnExclExistsFlg);

        if (ZYPConstant.FLG_ON_Y.equals(ruleCmbnExclExistsFlg)) {
            bizMsg.prcRuleModTpCd_H1.setErrorInfo(1, NMAM8369E);
            bizMsg.prcRuleAdjTpCd_H1.setErrorInfo(1, NMAM8369E);
            bizMsg.prcRuleAdjLvlCd_H1.setErrorInfo(1, NMAM8369E);
            modAttrbItem.setErrorInfo(1, NMAM8369E);
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DFN);     //QC#22593 add
            // 2018/06/06 QC#26491 Mod Start
            //if (PRC_RULE_ATTRB.FORMULA.equals(modAttrbItem.getValue())) {
            if (PRC_RULE_ATTRB.FORMULA.equals(modAttrbItem.getValue()) && glblMsg.B.getValidCount() > 0) {
            // 2018/06/06 QC#26491 Mod End
                glblMsg.B.no(idx).prcFmlaPk_B1.setErrorInfo(1, NMAM8369E);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);    //QC#22593 add
            }
            return true;
        }

        return false;
    }

    private static boolean isExistsPrcRuleCmbnExcl(NMAL7260CMsg bizMsg, String prcRuleAttrbCd, String prcFmlaTpCd, String prcFuncTpCd) {
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().isExistsPrcRuleCmbnExcl(bizMsg, prcRuleAttrbCd, prcFmlaTpCd, prcFuncTpCd);
        if (S21SsmEZDResult.RESULT_CODE_NORMAL.equals(ssmResult.getResultCode())) {
            return true;
        }
        return false;
    }

    /**
     * toHighValDate.
     * @param dateVal String
     * @return String
     */
    private static String toHighValDate(String dateVal) {
        if (ZYPCommonFunc.hasValue(dateVal)) {
            return dateVal;
        }
        return HIGH_VAL_DT;
    }

    /**
     * isExistCodeTbl. errorCheck(No.8)
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     * @param selectRows List<Integer>
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean isExistCodeTbl(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows, String glblCmpyCd) {
        boolean dataFlg = true;
        EZDCStringItem fromCStrTxt = bizMsg.prcRuleCondFromTxt_S1;
        EZDCBigDecimalItem fromCBDAmt = bizMsg.prcFmlaPk_S1;
        EZDCStringItem toCStrTxt = bizMsg.xxRecNmTxt_S1;

        for (int i : selectRows) {
            // CSMP#
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_04)) {
                if (!getMstData(PRC_RULE_ATTRB.CSMP_NUM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_04, glblMsg.B.no(i).csmpNumCmntTxt_04, "CSMP#")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_04.setErrorInfo(1, NMAM8186E, new String[] {"CSMP#" });
                    dataFlg = false;
                }
            }
            // Material Price Group
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_05)) {
                if (!getMstData(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_05, glblMsg.B.no(i).prcGrpNm_05, "Material Price Group")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_05.setErrorInfo(1, NMAM8186E, new String[] {"Material Price Group" });
                    dataFlg = false;
                }
            }
            // Prod Ctrl -1(BU)
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_06)) {
                if (!getMstData(PRC_RULE_ATTRB.PROD_CTRL_1_BU, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_06, glblMsg.B.no(i).prodCtrlNm_06, "Prod Ctrl -1(BU)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_06.setErrorInfo(1, NMAM8186E, new String[] {"Prod Ctrl -1(BU)" });
                    dataFlg = false;
                }
            }
            // Prod Ctrl -2(Model Series)CSMP#
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_07)) {
                if (!getMstData(PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_07, glblMsg.B.no(i).prodCtrlNm_07, "Prod Ctrl -2(Model Series)CSMP#")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_07.setErrorInfo(1, NMAM8186E, new String[] {"Prod Ctrl -2(Model Series)CSMP#" });
                    dataFlg = false;
                }
            }
            // Mdse Type
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_08)) {
                if (!getMstData(PRC_RULE_ATTRB.MDSE_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_08, glblMsg.B.no(i).coaMdseTpDescTxt_08, "Mdse Type")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_08.setErrorInfo(1, NMAM8186E, new String[] {"Mdse Type" });
                    dataFlg = false;
                }
            }
            // Product Code
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_09)) {
                if (!getMstData(PRC_RULE_ATTRB.PRODUCT_CODE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_09, glblMsg.B.no(i).coaProdDescTxt_09, "Product Code")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_09.setErrorInfo(1, NMAM8186E, new String[] {"Product Code" });
                    dataFlg = false;
                }
            }
            // Item Code
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_10)) {
                if (glblMsg.B.no(i).prcRuleCondFromTxt_10.getErrorCode() == 0 && !getMstData(PRC_RULE_ATTRB.ITEM_CODE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_10, glblMsg.B.no(i).mdseDescShortTxt_10, "Item Code")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM8186E, new String[] {"Item Code" });
                    dataFlg = false;
                }
            }
            // Order Category
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_11)) {
                if (!getMstData(PRC_RULE_ATTRB.ORDER_CATEGORY, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_11, glblMsg.B.no(i).dsOrdCatgDescTxt_11, "Order Category")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_11.setErrorInfo(1, NMAM8186E, new String[] {"Order Category" });
                    dataFlg = false;
                }
            }
            // Order Type
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_12)) {
                if (!getMstData(PRC_RULE_ATTRB.ORDER_REASON, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_12, glblMsg.B.no(i).dsOrdTpDescTxt_12, "Order Type")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_12.setErrorInfo(1, NMAM8186E, new String[] {"Order Type" });
                    dataFlg = false;
                }
            }
            // Order Line of Business
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_13)) {
                if (!getMstData(PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_13, glblMsg.B.no(i).lineBizTpDescTxt_13, "Order Line of Business")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_13.setErrorInfo(1, NMAM8186E, new String[] {"Order Line of Business" });
                    dataFlg = false;
                }
            }
            // Transaction Group
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_14)) {
                if (!getMstData(PRC_RULE_ATTRB.TRANSACTION_GROUP, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_14, glblMsg.B.no(i).prcGrpNm_14, "Transaction Group")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_14.setErrorInfo(1, NMAM8186E, new String[] {"Transaction Group" });
                    dataFlg = false;
                }
            }
            // PrcFmlaNm
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcFmlaPk_B1)) {
                if (!getMstData(PRC_RULE_ATTRB.FORMULA, fromCBDAmt, toCStrTxt, glblMsg.B.no(i).prcFmlaPk_B1, glblMsg.B.no(i).prcFmlaNm_B1, "Formula")) {
                    glblMsg.B.no(i).prcFmlaPk_B1.setErrorInfo(1, NMAM8186E, new String[] {"Formula" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_16)) {
                if (!getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_NUM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_16, glblMsg.B.no(i).billToAcctNm_16, "Bill To (Acct#)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_16.setErrorInfo(1, NMAM8186E, new String[] {"Bill To (Acct#)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_17)) {
                if (!getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_17, glblMsg.B.no(i).coaChDescTxt_17, "Bill To Acct (Channel)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_17.setErrorInfo(1, NMAM8186E, new String[] {"Bill To Acct (Channel)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_18)) {
                if (!getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_18, glblMsg.B.no(i).dsAcctClsDescTxt_18, "Bill To Acct (Classification)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_18.setErrorInfo(1, NMAM8186E, new String[] {"Bill To Acct (Classification)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_19)) {
                if (!getMstData(PRC_RULE_ATTRB.BRANCH, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_19, glblMsg.B.no(i).coaBrDescTxt_19, "Branch")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_19.setErrorInfo(1, NMAM8186E, new String[] {"Branch" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_20)) {
                if (!getMstData(PRC_RULE_ATTRB.CALL_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_20, glblMsg.B.no(i).svcCallTpDescTxt_20, "Call Type")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_20.setErrorInfo(1, NMAM8186E, new String[] {"Call Type" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_25)) {
                if (!getMstData(PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_25, glblMsg.B.no(i).dsOrdLineCatgDescTxt_25, "Line Category (Line Type)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_25.setErrorInfo(1, NMAM8186E, new String[] {"Line Category (Line Type)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_27)) {
                if (!getMstData(PRC_RULE_ATTRB.MARKETING_MODEL_NAME, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_27, glblMsg.B.no(i).mktMdlDescTxt_27, "Marketing Model Name")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_27.setErrorInfo(1, NMAM8186E, new String[] {"Marketing Model Name" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_28)) {
                if (!getMstData(PRC_RULE_ATTRB.MODEL_SEGMENT, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_28, glblMsg.B.no(i).mktMdlSegDescTxt_28, "Model Segment")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_28.setErrorInfo(1, NMAM8186E, new String[] {"Model Segment" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_29)) {
                if (!getMstData(PRC_RULE_ATTRB.ORDER_SOURCE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_29, glblMsg.B.no(i).cpoSrcTpDescTxt_29, "Order Source")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_29.setErrorInfo(1, NMAM8186E, new String[] {"Order Source" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_31)) {
                // 2020/01/14 QC#54227-1 Mod Start
                //if (!getMstData(PRC_RULE_ATTRB.PAYMENT_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_31, glblMsg.B.no(i).prcRuleCondFromTxt_31, "Payment Type")) {
                if (!getMstData(PRC_RULE_ATTRB.PAYMENT_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_31, glblMsg.B.no(i).dsPmtMethDescTxt_31, "Payment Type")) {
                // 2020/01/14 QC#54227-1 Mod End
                    glblMsg.B.no(i).prcRuleCondFromTxt_31.setErrorInfo(1, NMAM8186E, new String[] {"Payment Type" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_32)) {
                if (!getMstData(PRC_RULE_ATTRB.PRICE_LIST, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_32, glblMsg.B.no(i).prcCatgNm_32, "Price List")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_32.setErrorInfo(1, NMAM8186E, new String[] {"Price List" });
                    dataFlg = false;
                }
            }
            // Prod Ctrl -3(Product)
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_34)) {
                if (!getMstData(PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_34, glblMsg.B.no(i).prodCtrlNm_34, "Prod Ctrl -3(Product)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_34.setErrorInfo(1, NMAM8186E, new String[] {"Prod Ctrl -3(Product)" });
                    dataFlg = false;
                }
            }
            // Prod Ctrl -4(Product-Group)
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_35)) {
                if (!getMstData(PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_35, glblMsg.B.no(i).prodCtrlNm_35, "Prod Ctrl -4(Product-Group)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_35.setErrorInfo(1, NMAM8186E, new String[] {"Prod Ctrl -4(Product-Group)" });
                    dataFlg = false;
                }
            }
            // Prod Ctrl -5(Product-Type)
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_36)) {
                if (!getMstData(PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_36, glblMsg.B.no(i).prodCtrlNm_36, "Prod Ctrl -5(Product-Type)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_36.setErrorInfo(1, NMAM8186E, new String[] {"Prod Ctrl -5(Product-Type)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_38)) {
                if (!getMstData(PRC_RULE_ATTRB.RETURN_REASON_CODE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_38, glblMsg.B.no(i).rtrnRsnDescTxt_38, "Return Reason Code")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_38.setErrorInfo(1, NMAM8186E, new String[] {"Return Reason Code" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_39)) {
                if (!getMstData(PRC_RULE_ATTRB.SERVICE_LEVEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_39, glblMsg.B.no(i).shpgSvcLvlDescTxt_39, "Service Level")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_39.setErrorInfo(1, NMAM8186E, new String[] {"Service Level" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_40)) {
                if (!getMstData(PRC_RULE_ATTRB.SERVICE_MODEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_40, glblMsg.B.no(i).mdlDescTxt_40, "Service Model")) { // 2017/08/24
                                                                                                                                                                                // QC#20729
                                                                                                                                                                                // Mod
                    glblMsg.B.no(i).prcRuleCondFromTxt_40.setErrorInfo(1, NMAM8186E, new String[] {"Service Model" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_41)) {
                if (!getMstData(PRC_RULE_ATTRB.SERVICE_ZONE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_41, glblMsg.B.no(i).prcSvcZoneDescTxt_41, "Service Zone")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_41.setErrorInfo(1, NMAM8186E, new String[] {"Service Zone" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_42)) {
                if (!getMstData(PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_42, glblMsg.B.no(i).dsAcctClsDescTxt_42, "Ship To Acct (Classification)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_42.setErrorInfo(1, NMAM8186E, new String[] {"Ship To Acct (Classification)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_44)) {
                if (!getMstData(PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_44, glblMsg.B.no(i).spclHdlgTpDescTxt_44, "Special Handling Type")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_44.setErrorInfo(1, NMAM8186E, new String[] {"Special Handling Type" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_46)) {
                if (!getMstData(PRC_RULE_ATTRB.BUSINESS_UNIT, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_46, glblMsg.B.no(i).coaExtnDescTxt_46, "Business Unit")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_46.setErrorInfo(1, NMAM8186E, new String[] {"Business Unit" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_48)) {
                if (!getMstData(PRC_RULE_ATTRB.FREIGHT_TERM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_48, glblMsg.B.no(i).frtCondDescTxt_48, "Freight Term")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_48.setErrorInfo(1, NMAM8186E, new String[] {"Freight Term" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_49)) {
                if (!getMstData(PRC_RULE_ATTRB.FREIGHT_ZONE, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_49, glblMsg.B.no(i).fill40Txt_49, "Freight Zone")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_49.setErrorInfo(1, NMAM8186E, new String[] {"Freight Zone" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_53)) {
                if (!getMstData(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_53, glblMsg.B.no(i).prcGrpNm_53, "Customer Price Group(Sold To)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_53.setErrorInfo(1, NMAM8186E, new String[] {"Customer Price Group(Sold To)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_54)) {
                if (!getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_54, glblMsg.B.no(i).dsAcctNm_54, "Sold To (Acct#)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_54.setErrorInfo(1, NMAM8186E, new String[] {"Sold To (Acct#)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_55)) {
                if (!getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_55, glblMsg.B.no(i).coaChDescTxt_55, "Sold To Acct (Channel)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_55.setErrorInfo(1, NMAM8186E, new String[] {"Sold To Acct (Channel)" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_56)) {
                if (!getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_56, glblMsg.B.no(i).dsAcctClsDescTxt_56, "Sold To Acct (Classification)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_56.setErrorInfo(1, NMAM8186E, new String[] {"Sold To Acct (Classification)" });
                    dataFlg = false;
                }
            }
            // Material Price Group(QtyBrk)
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_57)) {
                if (!getMstData(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_57, glblMsg.B.no(i).prcGrpNm_57, "Material Price Group(QtyBrk)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_57.setErrorInfo(1, NMAM8186E, new String[] {"Material Price Group(QtyBrk)" });
                    dataFlg = false;
                }
            }
            // 2018/04/19 QC#22569 add start
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_59)) {
                if (!getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_1, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_59, glblMsg.B.no(i).slsMatGrpDescTxt_59, "Material Group 1)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_59.setErrorInfo(1, NMAM8186E, new String[] {"Material Group 1" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_60)) {
                if (!getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_2, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_60, glblMsg.B.no(i).slsMatGrpDescTxt_60, "Material Group 2)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_60.setErrorInfo(1, NMAM8186E, new String[] {"Material Group 2" });
                    dataFlg = false;
                }
            }
            if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).prcRuleCondFromTxt_61)) {
                if (!getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_3, fromCStrTxt, toCStrTxt, glblMsg.B.no(i).prcRuleCondFromTxt_61, glblMsg.B.no(i).slsMatGrpDescTxt_61, "Material Group 3)")) {
                    glblMsg.B.no(i).prcRuleCondFromTxt_61.setErrorInfo(1, NMAM8186E, new String[] {"Material Group 3" });
                    dataFlg = false;
                }
            }
            // 2018/04/19 QC#22569 add end

            if (!dataFlg && (!ZYPCommonFunc.hasValue(bizMsg.xxRowNum) || bizMsg.xxRowNum.getValueInt() > i)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(i));
            }
        }
        //QC#22593 add Start
        if (!dataFlg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
        }
        //QC#22593 add End
        return dataFlg;
    }

    /**
     * getMstData
     * @param item String
     * @param fromItem EZDSStringItem
     * @param toItem EZDSStringItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean getMstData(String item, EZDCStringItem fromItem, EZDCStringItem toItem, String itemNm) {
        boolean isGet = true;

        if (!ZYPCommonFunc.hasValue(fromItem.getValue())) {
            toItem.clear();
            return isGet;
        }

        isGet = NMXC105001PriceMasterUtil.getRuleAttrbNm(item, fromItem, toItem, itemNm);

        return isGet;
    }

    /**
     * getMstData
     * @param item String
     * @param fromItem EZDSBigDecimalItem
     * @param toItem EZDSStringItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean getMstData(String item, EZDCBigDecimalItem fromItem, EZDCStringItem toItem, String itemNm) {

        return NMXC105001PriceMasterUtil.getRuleAttrbFmlaNm(item, fromItem, toItem, itemNm);
    }

    /**
     * getMstData
     * @param item String
     * @param fromCItem EZDCStringItem
     * @param toCItem EZDCStringItem
     * @param fromSItem EZDSStringItem
     * @param toSItem EZDSStringItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean getMstData(String item, EZDCStringItem fromCItem, EZDCStringItem toCItem, EZDSStringItem fromSItem, EZDSStringItem toSItem, String itemNm) {
        boolean isGet = true;

        // 2020/01/06 QC#55303 Del Start
        // if (!ZYPCommonFunc.hasValue(fromCItem.getValue())) {
        //    return isGet;
        // }
        // 2020/01/06 QC#55303 Del End

        ZYPEZDItemValueSetter.setValue(fromCItem, fromSItem.getValue());
        ZYPEZDItemValueSetter.setValue(toCItem, toSItem.getValue());

        isGet = NMXC105001PriceMasterUtil.getRuleAttrbNm(item, fromCItem, toCItem, itemNm);

        ZYPEZDItemValueSetter.setValue(fromSItem, fromCItem.getValue());
        ZYPEZDItemValueSetter.setValue(toSItem, toCItem.getValue());

        return isGet;
    }

    /**
     * getMstData
     * @param item String
     * @param fromCItem EZDCBigDecimalItem
     * @param toCItem EZDCStringItem
     * @param fromItem EZDSBigDecimalItem
     * @param toItem EZDSStringItem
     * @param itemNm String
     * @return boolean
     */
    public static boolean getMstData(String item, EZDCBigDecimalItem fromCItem, EZDCStringItem toCItem, EZDSBigDecimalItem fromSItem, EZDSStringItem toSItem, String itemNm) {
        boolean isGet = true;

        ZYPEZDItemValueSetter.setValue(fromCItem, fromSItem.getValue());
        ZYPEZDItemValueSetter.setValue(toCItem, toSItem.getValue());

        isGet = NMXC105001PriceMasterUtil.getRuleAttrbFmlaNm(item, fromCItem, toCItem, itemNm);

        ZYPEZDItemValueSetter.setValue(fromSItem, fromCItem.getValue());
        ZYPEZDItemValueSetter.setValue(toSItem, toCItem.getValue());

        return isGet;
    }

    // Add End 2016/10/21 QC#14936
    // Add Start 2018/07/18 QC#20267
    /**
     * Check Merchandise Code
     * @param bizMsg NWAL1500CMsg
     * @param inputMdseCd Merchandise Code
     * @param custMdseCd Customer Merchandise Code
     * @param mnfItemCd Manufacturer Item Code
     * @param origMdseCd Original Merchandise Code
     * @param srchOrigItemFlg Search Original Item Flag
     * @return Checked Merchandise Code
     */
    public static boolean setMdseInfo(NMAL7260CMsg bizMsg, EZDCStringItem inputMdseCd, EZDCStringItem mdseDescShortTxt, EZDCStringItem mnfItemCd, EZDCStringItem srchOrigItemFlg) { 

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (srchOrigItemFlg != null && !ZYPConstant.FLG_ON_Y.equals(bizMsg.srchOrigItemFlg_MF.getValue())) {
            ZYPEZDItemValueSetter.setValue(srchOrigItemFlg, ZYPConstant.FLG_OFF_N);
        }
        boolean isOver = false;
        String inputMdseCdValue = null;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();
        if (inputMdseCd.getValue().length() > 8) {
            inputMdseCdValue = inputMdseCd.getValue().substring(0, 8);
            isOver = true;
        } else {
            inputMdseCdValue = inputMdseCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, inputMdseCdValue);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg == null) {
            inputMdseCdValue = inputMdseCd.getValue();
        } else {
            if (isOver) {
                inputMdseCd.setErrorInfo(1, NMAM8216E);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
                return false;
            }
            inputMdseCdValue = ordTakeOutTMsg.mdseCd.getValue();

            ZYPEZDItemValueSetter.setValue(inputMdseCd, ordTakeOutTMsg.ordTakeMdseCd);
        }

        MDSETMsg mdseInTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, inputMdseCdValue);
        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        if (mdseOutTMsg != null) {
            if (ordTakeOutTMsg == null) {
                ZYPEZDItemValueSetter.setValue(inputMdseCd, mdseOutTMsg.mdseCd);
             }
             ZYPEZDItemValueSetter.setValue(mdseDescShortTxt, mdseOutTMsg.mdseDescShortTxt);
             ZYPEZDItemValueSetter.setValue(mnfItemCd, mdseOutTMsg.mnfItemCd);

             return true;
        }

        String baseMdseCd = null;
        String baseMdseDescShortTxt = null;
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, inputMdseCdValue);
        if (ssmResult.isCodeNotFound()) {
            inputMdseCd.setErrorInfo(1, NMAM0179E, new String[] {inputMdseCd.getValue(), "No Service Item" });
            return false;

        } else {
            List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (srchOrigItemFlg != null) {
                ZYPEZDItemValueSetter.setValue(srchOrigItemFlg, ZYPConstant.FLG_ON_Y); // 2016/09/29 S21_NA#14805 ADD
            }
            if (1 < ssmResult.getQueryResultCount()) {

                Set<String> mdse8Set = new HashSet<String>();
                for (Map<String, Object> mnfItem : mnfItemList) {
                    String mdseCd = (String) mnfItem.get("MDSE_CD");
                    if (8 < mdseCd.length()) {
                        mdseCd = mdseCd.substring(0, 8);
                    }
                    mdse8Set.add(mdseCd);
                }
                if (1 < mdse8Set.size()) {
                    // Open Popup
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                    return false;

                } else {
                    // Found one MnfItemCd
                    for (String mdse8 : mdse8Set) {
                        baseMdseCd = mdse8;
                        break;
                    }
                    ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdseTMsg(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
                    if (tMsg == null) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                        return false;
                    }
                    for(Map<String, Object> mnfItem : mnfItemList) {
                        if (tMsg.mdseCd.getValue().equals((String) mnfItem.get("MDSE_CD"))) {
                            baseMdseDescShortTxt = (String) mnfItem.get("MDSE_DESC_SHORT_TXT");
                        }
                    }
                }
            } else {
                // Found one MnfItemCd
                baseMdseCd = (String) mnfItemList.get(0).get("MDSE_CD");
                baseMdseDescShortTxt = (String) mnfItemList.get(0).get("MDSE_DESC_SHORT_TXT");
            }
        }

        ZYPEZDItemValueSetter.setValue(mnfItemCd, inputMdseCd);
        ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdseTMsgArray(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
            baseMdseDescShortTxt = tMsgArray.no(0).ordTakeMdseCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(inputMdseCd, baseMdseCd);
        ZYPEZDItemValueSetter.setValue(mdseDescShortTxt, baseMdseDescShortTxt);

        return true;
    }
    /**
     * Check Merchandise Code
     * @param bizMsg NWAL1500CMsg
     * @param inputMdseCd Merchandise Code
     * @param custMdseCd Customer Merchandise Code
     * @param mnfItemCd Manufacturer Item Code
     * @param origMdseCd Original Merchandise Code
     * @param srchOrigItemFlg Search Original Item Flag
     * @return Checked Merchandise Code
     */
    public static boolean checkMdse(NMAL7260CMsg bizMsg, EZDSStringItem inputMdseCd, EZDSStringItem mdseDescShortTxt, EZDSStringItem mnfItemCd, EZDSStringItem srchOrigItemFlg) { 

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_OFF_N);
        boolean isOver = false;
        String inputMdseCdValue = null;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();
        if (inputMdseCd.getValue().length() > 8) {
            inputMdseCdValue = inputMdseCd.getValue().substring(0, 8);
            isOver = true;
        } else {
            inputMdseCdValue = inputMdseCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, inputMdseCdValue);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg == null) {
            inputMdseCdValue = inputMdseCd.getValue();
        } else {
            if (isOver) {
                inputMdseCd.setErrorInfo(1, NMAM8216E);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
                return false;
            }
            inputMdseCdValue = ordTakeOutTMsg.mdseCd.getValue();

            ZYPEZDItemValueSetter.setValue(inputMdseCd, ordTakeOutTMsg.ordTakeMdseCd);
        }

        MDSETMsg mdseInTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, inputMdseCdValue);
        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        if (mdseOutTMsg != null) {
            if (ordTakeOutTMsg == null) {
                ZYPEZDItemValueSetter.setValue(inputMdseCd, mdseOutTMsg.mdseCd);
             }
             ZYPEZDItemValueSetter.setValue(mdseDescShortTxt, mdseOutTMsg.mdseDescShortTxt);
             ZYPEZDItemValueSetter.setValue(mnfItemCd, mdseOutTMsg.mnfItemCd);

             return true;
        }


        String baseMdseCd = null;
        String baseMdseDescShortTxt = null;
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getBaseMdseCdFromMnfItemCd(bizMsg, inputMdseCdValue);
        if (ssmResult.isCodeNotFound()) {
            inputMdseCd.setErrorInfo(1, NMAM0179E, new String[] {inputMdseCd.getValue(), "No Service Item" });
            return false;

        } else {
            List<Map<String, Object>> mnfItemList = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (srchOrigItemFlg != null) {
                ZYPEZDItemValueSetter.setValue(srchOrigItemFlg, ZYPConstant.FLG_ON_Y); // 2016/09/29 S21_NA#14805 ADD
            }
            if (1 < ssmResult.getQueryResultCount()) {

                Set<String> mdse8Set = new HashSet<String>();
                for (Map<String, Object> mnfItem : mnfItemList) {
                    String mdseCd = (String) mnfItem.get("MDSE_CD");
                    if (8 < mdseCd.length()) {
                        mdseCd = mdseCd.substring(0, 8);
                    }
                    mdse8Set.add(mdseCd);
                }
                if (1 < mdse8Set.size()) {
                    // Open Popup
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                    return false;

                } else {
                    // Found one MnfItemCd
                    for (String mdse8 : mdse8Set) {
                        baseMdseCd = mdse8;
                        break;
                    }
                    ORD_TAKE_MDSETMsg tMsg = getOrdTakeMdseTMsg(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
                    if (tMsg == null) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(bizMsg.srchOrigItemFlg_MF, ZYPConstant.FLG_ON_Y);
                        return false;
                    }
                    for(Map<String, Object> mnfItem : mnfItemList) {
                        if (tMsg.mdseCd.getValue().equals((String) mnfItem.get("MDSE_CD"))) {
                            baseMdseDescShortTxt = (String) mnfItem.get("MDSE_DESC_SHORT_TXT");
                        }
                    }
                }
            } else {
                // Found one MnfItemCd
                baseMdseCd = (String) mnfItemList.get(0).get("MDSE_CD");
                baseMdseDescShortTxt = (String) mnfItemList.get(0).get("MDSE_DESC_SHORT_TXT");
            }
        }

        ZYPEZDItemValueSetter.setValue(mnfItemCd, inputMdseCd);
        ORD_TAKE_MDSETMsgArray tMsgArray = getOrdTakeMdseTMsgArray(bizMsg.glblCmpyCd.getValue(), baseMdseCd);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            baseMdseCd = tMsgArray.no(0).ordTakeMdseCd.getValue();
            baseMdseDescShortTxt = tMsgArray.no(0).ordTakeMdseCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(inputMdseCd, baseMdseCd);
        ZYPEZDItemValueSetter.setValue(mdseDescShortTxt, baseMdseDescShortTxt);

        return true;
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static boolean searchMdseInfo(NMAL7260CMsg bizMsg, EZDSStringItem inputMdseCd, EZDSStringItem mdseDescShortTxt, EZDSStringItem mnfItemCd) {
        boolean isOver = false;
        String inputMdseCdValue = null;

        ORD_TAKE_MDSETMsg ordTakeInTMsg = new ORD_TAKE_MDSETMsg();
        if (inputMdseCd.getValue().length() > 8) {
            inputMdseCdValue = inputMdseCd.getValue().substring(0, 8);
            isOver = true;
        } else {
            inputMdseCdValue = inputMdseCd.getValue();
        }

        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ordTakeInTMsg.ordTakeMdseCd, inputMdseCdValue);
        ORD_TAKE_MDSETMsg ordTakeOutTMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(ordTakeInTMsg);
        if (ordTakeOutTMsg == null) {
            inputMdseCdValue = inputMdseCd.getValue();
        } else {
            if (isOver) {
                inputMdseCd.setErrorInfo(1, NMAM8216E);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
                return false;
            }
            inputMdseCdValue = ordTakeOutTMsg.mdseCd.getValue();

            ZYPEZDItemValueSetter.setValue(inputMdseCd, ordTakeOutTMsg.ordTakeMdseCd);
        }

        MDSETMsg mdseInTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseInTMsg.mdseCd, inputMdseCdValue);
        MDSETMsg mdseOutTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseInTMsg);
        if (mdseOutTMsg != null) {
            if (ordTakeOutTMsg == null) {
                ZYPEZDItemValueSetter.setValue(inputMdseCd, mdseOutTMsg.mdseCd);
             }
             ZYPEZDItemValueSetter.setValue(mdseDescShortTxt, mdseOutTMsg.mdseDescShortTxt);
             ZYPEZDItemValueSetter.setValue(mnfItemCd, mdseOutTMsg.mnfItemCd);

             return true;
        }

        return false;
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ALL_MDSE_VTMsgArray getAllMdseViewArray(NMAL7260CMsg bizMsg, String mdseCd) {

        final ALL_MDSE_VTMsg condition = new ALL_MDSE_VTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        condition.setConditionValue("mdseCd01", mdseCd);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get ALL_MDSE_V Array
     * @param bizMsg NWAL1500CMsg
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsgArray getOrdTakeMdseTMsgArray(String glblCmpyCd, String mdseCd) {

        final ORD_TAKE_MDSETMsg condition = new ORD_TAKE_MDSETMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condition.setConditionValue("mdseCd01", mdseCd);

        return (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * Get Ord Take Mdse
     * @param glblCmpyCd String
     * @param mdseCd Merchandise Code
     * @return ALL_MDSE_V Array
     */
    private static ORD_TAKE_MDSETMsg getOrdTakeMdseTMsg(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdse = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdse.ordTakeMdseCd, mdseCd);

        return (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdse); // 2018/03/08 S21_NA#19808
    }

    /**
     * select merchandise data from merchandise master using NWXMdseTMsgThreadLocalCache#get()
     * @param glblCmpyCd global company code
     * @param mdseCd merchandise code
     * @return merchandise master data
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg queryMdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, mdseCd);

        MDSETMsg mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
        if (mdseTMsg == null) {

            ORD_TAKE_MDSETMsg ordTakeMdseMsg = new ORD_TAKE_MDSETMsg();
            ordTakeMdseMsg.setSQLID("002");
            ordTakeMdseMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            ordTakeMdseMsg.setConditionValue("ordTakeMdseCd01", mdseCd);

            ORD_TAKE_MDSETMsgArray ordTakeMdseMsgArray = (ORD_TAKE_MDSETMsgArray) EZDTBLAccessor.findByCondition(ordTakeMdseMsg);
            if (ordTakeMdseMsgArray != null && ordTakeMdseMsgArray.getValidCount() > 0) {
                ZYPEZDItemValueSetter.setValue(queryMdseTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(queryMdseTMsg.mdseCd, ordTakeMdseMsgArray.no(0).mdseCd);

                mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(queryMdseTMsg);
            }
        }
        return mdseTMsg;
    }
    // Add End 2018/07/18 QC#20267

    /**
     * checkModifer
     * @param prcRuleAttrbCd String
     * @return boolean
     */
    public static boolean checkModifer(String prcRuleAttrbCd) {
        if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * setColumn
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     */
    public static void setColumn(NMAL7260CMsg bizMsg) {
        // column Numbering
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            int cnt = i + 1;
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).prcRuleCondNum_C1, new BigDecimal(cnt));
        }
    }

    /**
     * setColumnTableData
     * @param bizMsg NMAL7260CMsg
     */
    public static void setColumnTableData(NMAL7260CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(bizMsg.xxComnColOrdTxt, getColumnTableData(bizMsg));
    }

    /**
     * getColumnTableData
     * @param bizMsg NMAL7260CMsg
     * @return String
     */
    public static String getColumnTableData(NMAL7260CMsg bizMsg) {
        BigDecimal bd = BigDecimal.ONE;
        String prcRuleAttrbCd = NMAL7260CommonLogic.judgeQtyBreak(bizMsg);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < bizMsg.C.getValidCount(); i++) {
            if (i == 0) {
                sb.append("BH0:");
            }
            setColumnTableData(bizMsg, bd.add(new BigDecimal(i)), sb, prcRuleAttrbCd);
        }
        if (i > 0) {
            if (ZYPCommonFunc.hasValue(prcRuleAttrbCd)) {
                sb.append("BH90:");
            }
            sb.append("BH97:");
            sb.append("BH98:");
            sb.append("BH99");
        }
        return sb.toString();
    }

    /**
     * setColumnTableData
     * @param bizMsg NMAL7260CMsg
     * @param bd BigDecimal
     * @param sb StringBuilder
     */
    public static void setColumnTableData(NMAL7260CMsg bizMsg, BigDecimal bd, StringBuilder sb, String prcRuleAttrbCd) {
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            BigDecimal column = bizMsg.C.no(i).prcRuleCondNum_C1.getValue();
            if (bd.compareTo(column) == 0) {
                String attCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                if (PRC_RULE_ATTRB.CSMP_NUM.equals(attCd)) {
                    sb.append("BH4:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(attCd)) {
                    sb.append("BH5:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(attCd)) {
                    sb.append("BH6:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(attCd)) {
                    sb.append("BH7:");
                    break;
                } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(attCd)) {
                    sb.append("BH8:");
                    break;
                } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(attCd)) {
                    sb.append("BH9:");
                    break;
                } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(attCd)) {
                    sb.append("BH10:");
                    // 2018/07/17 QC#20267 Add Start
                    sb.append("BH62:");
                    // 2018/07/17 QC#20267 Add End
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(attCd)) {
                    sb.append("BH11:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(attCd)) {
                    sb.append("BH12:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(attCd)) {
                    sb.append("BH13:");
                    break;
                } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(attCd)) {
                    sb.append("BH14:");
                    break;
                } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(attCd)) {
                    sb.append("BH15:");
                    break;
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(attCd)) {
                    sb.append("BH16:");
                    break;
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(attCd)) {
                    sb.append("BH17:");
                    break;
                } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(attCd)) {
                    sb.append("BH18:");
                    break;
                } else if (PRC_RULE_ATTRB.BRANCH.equals(attCd)) {
                    sb.append("BH19:");
                    break;
                } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(attCd)) {
                    sb.append("BH20:");
                    break;
                } else if (PRC_RULE_ATTRB.CALL_DATE.equals(attCd)) {
                    sb.append("BH21:");
                    break;
                } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(attCd)) {
                    sb.append("BH22:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(attCd)) {
                    sb.append("BH24:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(attCd)) {
                    sb.append("BH25:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_QTY.equals(attCd)) {
                    if (!ZYPCommonFunc.hasValue(prcRuleAttrbCd)) {
                        sb.append("BH26:");
                    }
                    break;
                } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(attCd)) {
                    sb.append("BH27:");
                    break;
                } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(attCd)) {
                    sb.append("BH28:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(attCd)) {
                    sb.append("BH29:");
                    break;
                } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(attCd)) {
                    sb.append("BH30:");
                    break;
                } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(attCd)) {
                    sb.append("BH31:");
                    break;
                } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(attCd)) {
                    sb.append("BH32:");
                    break;
                } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(attCd)) {
                    sb.append("BH33:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(attCd)) {
                    sb.append("BH34:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(attCd)) {
                    sb.append("BH35:");
                    break;
                } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(attCd)) {
                    sb.append("BH36:");
                    break;
                } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(attCd)) {
                    sb.append("BH37:");
                    break;
                } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(attCd)) {
                    sb.append("BH38:");
                    break;
                } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(attCd)) {
                    sb.append("BH39:");
                    break;
                } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(attCd)) {
                    sb.append("BH40:");
                    break;
                } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(attCd)) {
                    sb.append("BH41:");
                    break;
                } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(attCd)) {
                    sb.append("BH42:");
                    break;
                } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(attCd)) {
                    sb.append("BH44:");
                    break;
                } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(attCd)) {
                    sb.append("BH45:");
                    break;
                } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(attCd)) {
                    sb.append("BH46:");
                    break;
                } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(attCd)) {
                    sb.append("BH48:");
                    break;
                } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(attCd)) {
                    sb.append("BH49:");
                    break;
                } else if (PRC_RULE_ATTRB.FORMULA.equals(attCd)) {
                    sb.append("BH50:");
                    break;
                } else if (PRC_RULE_ATTRB.PERCENT.equals(attCd)) {
                    sb.append("BH51:");
                    break;
                } else if (PRC_RULE_ATTRB.VALUE.equals(attCd)) {
                    sb.append("BH52:");
                    break;
                } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(attCd)) {
                    sb.append("BH53:");
                    break;
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(attCd)) {
                    sb.append("BH54:");
                    break;
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(attCd)) {
                    sb.append("BH55:");
                    break;
                } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(attCd)) {
                    sb.append("BH56:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(attCd)) {
                    sb.append("BH57:");
                    break;
                } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(attCd)) {
                    if (!ZYPCommonFunc.hasValue(prcRuleAttrbCd)) {
                        sb.append("BH58:");
                    }
                    break;
                // 2018/04/19 QC#22569 add start
                } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(attCd)) {
                    sb.append("BH59:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(attCd)) {
                    sb.append("BH60:");
                    break;
                } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(attCd)) {
                    sb.append("BH61:");
                    break;
                }
                // 2018/04/19 QC#22569 add end
            }
        }
    }

    /**
     * createUploadData
     * @param bizMsg NMAL7260CMsg
     * @param upLoadDataList List<List<String>>
     * @return boolean
     */
    public static boolean createUploadData(NMAL7260CMsg bizMsg, List<List<String>> upLoadDataList) {
        LineNumberReader lnr = null;
        int uploadCnt = 0;
        StringBuffer sb = new StringBuffer();
        List<String[]> strList = new ArrayList<String[]>();
        try {
            // 2018/05/17 QC#26125 Mod Start
            //lnr = new LineNumberReader(new FileReader(bizMsg.xxFileData.getTempFilePath()));
            String csvPath = bizMsg.xxFileData.getTempFilePath();
            String fileEx = "." + ZYPFileNameUtil.getFileEx(csvPath);
            if (ZYPExcelUtil.EXCEL_EXTENSION_XLS.equals(fileEx) || ZYPExcelUtil.EXCEL_EXTENSION_XLSX.equals(fileEx)) {
                csvPath = ZYPExcelUtil.excelToCsvFile(bizMsg.xxFileData.getTempFilePath());
            }
            lnr = new LineNumberReader(new FileReader(csvPath));
            // 2018/05/17 QC#26125 Mod End
            String line = "";
            boolean dataFlg = true;
            while ((line = lnr.readLine()) != null) {
                sb.setLength(0);
                if (dataFlg) {
                    dataFlg = false;
                } else {
                    // update data create
                    boolean strFlg = false;
                    int lineLength = line.length();
                    String[] dataListStr = new String[TABLE_ITEM_CSV_MAX_CNT];
                    int itemCnt = 0;
                    for (int i = 0; i < lineLength; i++) {
                        char ch = line.charAt(i);
                        if (strFlg) {
                            if ('\"' == ch) {
                                dataListStr[itemCnt] = sb.toString();
                                strFlg = false;
                            } else {
                                if (i == (lineLength - 1)) {
                                    if (0 == sb.length()) {
                                        dataListStr[itemCnt] = "";
                                        strList.add(dataListStr);
                                    } else {
                                        dataListStr[itemCnt] = sb.toString();
                                        sb.setLength(0);
                                    }
                                    strFlg = false; //
                                }
                                sb.append(ch);
                            }
                        } else {
                            if ('\"' == ch) {
                                strFlg = true;
                            } else if (',' == ch) {
                                if (0 == sb.length()) {
                                    dataListStr[itemCnt] = "";
                                } else {
                                    dataListStr[itemCnt] = sb.toString();
                                    sb.setLength(0);
                                }
                                itemCnt++;
                            } else {
                                sb.append(ch);
                                if (i == (lineLength - 1)) {
                                    if (0 == sb.length()) {
                                        dataListStr[itemCnt] = "";
                                        strList.add(dataListStr);
                                    } else {
                                        dataListStr[itemCnt] = sb.toString();
                                        sb.setLength(0);
                                    }
                                }
                            }
                        }
                    }
                    List<String> list = new ArrayList<String>(Arrays.asList(dataListStr));
                    for (int j = (TABLE_ITEM_CSV_MAX_CNT - 1); j > itemCnt; j--) {
                        list.remove(j);
                    }
                    upLoadDataList.add(list);
                    uploadCnt++;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            return true;
        } finally {
            if (lnr != null) {
                try {
                    lnr.close();
                } catch (IOException e) {
                    return true;
                }
            }
        }

        if (1 >= lnr.getLineNumber()) {
            bizMsg.setMessageInfo(NMAM8320E);
            return true;
        }
        return false;
    }

    /**
     * checkUploadItemCnt
     * @param bizMsg NMAL7260CMsg
     * @param upLoadDataList List<List<String>>
     * @return boolean
     */
    public static boolean checkUploadItemCnt(NMAL7260CMsg bizMsg, List<List<String>> upLoadDataList) {
        String cntStr = bizMsg.xxComnColOrdTxt.getValue();
        int chkDataCnt = 0;
        if (TABLE_ITEM_DEF.equals(cntStr)) {
            chkDataCnt = 2;
        } else {
            String[] chkDataItem = cntStr.split(":");
            for (int k = 0; k < chkDataItem.length; k++) {
                // 2018/06/06 QC#26491 Mod Start
                //if (TABLE_ITEM_FORMULA.equals(chkDataItem[k]) || TABLE_ITEM_PERCENT.equals(chkDataItem[k]) || TABLE_ITEM_VALUE.equals(chkDataItem[k]) || TABLE_ITEM_EFFECTIVE_DATE_FROM.equals(chkDataItem[k])
                //        || TABLE_ITEM_EFFECTIVE_DATE_TO.equals(chkDataItem[k])) {
                if (TABLE_ITEM_PERCENT.equals(chkDataItem[k]) || TABLE_ITEM_VALUE.equals(chkDataItem[k]) || TABLE_ITEM_EFFECTIVE_DATE_FROM.equals(chkDataItem[k])
                            || TABLE_ITEM_EFFECTIVE_DATE_TO.equals(chkDataItem[k])) {
                // 2018/06/06 QC#26491 Mod End
                    chkDataCnt += 1;
                } else if (TABLE_ITEM_CHECK_BOX.equals(chkDataItem[k]) || TABLE_ITEM_P_AUDIT_INFO.equals(chkDataItem[k])) {
                    continue;
                } else if (TABLE_ITEM_QTYDISC.equals(chkDataItem[k])) {
                    continue;
                // 2018/07/19 QC#20267 Mod Start
                } else if (TABLE_ITEM_MNF_ITEM_CD.equals(chkDataItem[k])) {
                    // Mod Start 2019/01/08 QC#29751
                    //chkDataCnt += 1;
                    continue;
                    // Mod End 2019/01/08 QC#29751
                // 2018/07/19 QC#20267 Mod End
                    // Mod Start 2018/12/04 QC#29321
                //} else {
                //    chkDataCnt += 2;
                } else if (TABLE_ITEM_TRANSACTION_WEIGHT.equals(chkDataItem[k]) || TABLE_ITEM_CALL_DATE.equals(chkDataItem[k]) || //
                        TABLE_ITEM_CUSTOMER_PO.equals(chkDataItem[k]) || TABLE_ITEM_LINE_AMOUNT.equals(chkDataItem[k]) || //
                        TABLE_ITEM_ORDER_SUBTOTAL.equals(chkDataItem[k]) || TABLE_ITEM_PRICING_DATE.equals(chkDataItem[k]) || //
                        TABLE_ITEM_REQUEST_DATE.equals(chkDataItem[k]) || TABLE_ITEM_TOTAL_QTY.equals(chkDataItem[k]) || //
                        TABLE_ITEM_LINE_QTY.equals(chkDataItem[k]) || // 2019/12/18 QC#55108 Add
                        TABLE_ITEM_LINE_QTY_QTYBRK.equals(chkDataItem[k])) {
                    chkDataCnt += 2;
                } else {
                    chkDataCnt += 1;
                    // Mod End 2018/12/04 QC#29321
                }
            }
        }
        // 2018/09/12 QC#9700 mod start
//        chkDataCnt = chkDataCnt + 24;
        // Mod Start 2018/12/04 QC#29321
        //chkDataCnt = chkDataCnt + 25;
        chkDataCnt = chkDataCnt + 1;
        // Mod End 2018/12/04 QC#29321
        // 2018/09/12 QC#9700 mod end

        for (int i = 0; i < upLoadDataList.size(); i++) {
            List<String> upLoadData = upLoadDataList.get(i);
            if (chkDataCnt != upLoadData.size()) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkItemCnt
     * @param item String
     * @param itemCnt integer
     * @return boolean
     */
    public static boolean checkItemCnt(String item, int itemCnt) {
        if (itemCnt < item.length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checkItemTypeBigDecimal
     * @param item String
     * @return boolean
     */
    public static boolean checkItemTypeBigDecimal(String item) {

        if (ZYPCommonFunc.isNumberCheck(item)) {
            return false;
        }
        return true;
    }

    /**
     * checkItemTypeDate
     * @param item String
     * @return boolean
     */
    public static boolean checkItemTypeDate(String item) {

        if (ZYPDateUtil.isValidDate(item, ZYPDateUtil.TYPE_YYYYMMDD)) {
            return false;
        }
        return true;
    }

    /**
     * checkPrcPrcdGrp
     * @param bizMsg NMAL7260CMsg
     * @return boolean
     */
    public static boolean checkPrcPrcdGrp(NMAL7260CMsg bizMsg) {
        boolean rtnValue = false;
        if (!ZYPCommonFunc.hasValue(bizMsg.prcRulePrcdPk_H1)) {
            int count = NMAL7260Query.getInstance().getCountDefPrcd(bizMsg);
            if (count > 1) {
                rtnValue = true;
            }
        }
        return rtnValue;
    }

    /**
     * setTableDataHeader
     * @param bizMsg NMAL7260CMsg
     * @param prcRuleAttrbCd String
     * @param prcRuleAttrbDesctxt String
     * @return boolean
     */
    public static void setTableDataHeader(NMAL7260CMsg bizMsg, String prcRuleAttrbCd, String prcRuleAttrbDesctxt) {
        if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_1, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_2, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_3, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_4, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_5, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_6, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_7, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_8, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_9, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_10, prcRuleAttrbDesctxt);
            // 2018/07/17 QC#20267 Add Start
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_62, HDR_NM_MNF_NUM);
            // 2018/07/17 QC#20267 Add End

        } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_11, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_12, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_13, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_14, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_15, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_16, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_17, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_18, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_21, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_22, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_23, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_24, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_25, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_26, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_27, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_28, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_29, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_30, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_31, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_32, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.CALL_DATE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_33, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_34, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_35, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_36, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.LINE_QTY.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_37, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_38, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_39, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_40, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_41, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_42, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_43, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.PRICING_DATE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_44, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.REQUEST_DATE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_45, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_46, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_47, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_48, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_49, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_50, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_51, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.TOTAL_QRY.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_52, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_53, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_54, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_55, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_56, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_57, prcRuleAttrbDesctxt);
        // 2018/04/19 QC#22569 add start
        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_59, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_60, prcRuleAttrbDesctxt);

        } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_61, prcRuleAttrbDesctxt);

        }
        // 2018/04/19 QC#22569 add end
    }

    public static String convertDateFormat(String dt) {
        return dt == null ? null : dt.replaceAll(NMAL7260Constant.DT_CONV_FORMAT[0], NMAL7260Constant.DT_CONV_FORMAT[1]);
    }

    public static void setStringItem(EZDFStringItem strItem, String setValue) {
        if (ZYPCommonFunc.hasValue(setValue)) {
            ZYPEZDItemValueSetter.setValue(strItem, setValue);
        } else {
            strItem.clear();
        }
    }

    public static List<Integer> setColNum(List<Integer> csvColumNo, int[] arrayNum) {
        for (int num : arrayNum) {
            csvColumNo.add(num);
        }

        return csvColumNo;
    }

    public static List<String> setColHeaderData(List<String> csvColumHeder, String[] arrayData) {
        for (String data : arrayData) {
            csvColumHeder.add(data);
        }

        return csvColumHeder;
    }

    // Add End 2016/10/03 QC#6931

    // Add Start 2016/10/14 QC#14936
    // public static BigDecimal setPrcRuleTrxCondPK(String setCd, int
    // i, NMAL7260CMsg bizMsg) {
    //
    // BigDecimal setPrcRuleTrxCondPK = BigDecimal.ZERO;;
    //
    // if (PRC_RULE_ATTRB.CUSTOMER_CHANNEL_SHIP_TO.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z1.getValue();
    // }
    // if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO.equals(setCd))
    // {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z2.getValue();
    // }
    // if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z3.getValue();
    // }
    // if (PRC_RULE_ATTRB.CSMP_NUM.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z4.getValue();
    // }
    // if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z5.getValue();
    // }
    // if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z6.getValue();
    // }
    // if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z7.getValue();
    // }
    // if (PRC_RULE_ATTRB.MDSE_TYPE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z8.getValue();
    // }
    // if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_Z9.getValue();
    // }
    // if (PRC_RULE_ATTRB.ITEM_CODE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZA.getValue();
    // }
    // if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZB.getValue();
    // }
    // if (PRC_RULE_ATTRB.ORDER_REASON.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZC.getValue();
    // }
    // if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZD.getValue();
    // }
    // if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZE.getValue();
    // }
    // if (PRC_RULE_ATTRB.TOTAL_TRANSACTION_WEIGHT.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZF.getValue();
    // }
    // if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZG.getValue();
    // }
    // if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZH.getValue();
    // }
    // if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZI.getValue();
    // }
    // if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(setCd))
    // {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZJ.getValue();
    // }
    // if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZK.getValue();
    // }
    // if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZL.getValue();
    // }
    // if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_ZM.getValue();
    // }
    // if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W1.getValue();
    // }
    // if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W2.getValue();
    // }
    // if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W3.getValue();
    // }
    // if (PRC_RULE_ATTRB.BRANCH.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W4.getValue();
    // }
    // if (PRC_RULE_ATTRB.CALL_TYPE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W5.getValue();
    // }
    // if (PRC_RULE_ATTRB.CALL_DATE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W6.getValue();
    // }
    // if (PRC_RULE_ATTRB.CUSTOMER_PO.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W7.getValue();
    // }
    // if (PRC_RULE_ATTRB.LINE_AMOUNT.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W8.getValue();
    // }
    // if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_W9.getValue();
    // }
    // if (PRC_RULE_ATTRB.LINE_QTY.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WA.getValue();
    // }
    // if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WB.getValue();
    // }
    // if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WC.getValue();
    // }
    // if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WD.getValue();
    // }
    // if (PRC_RULE_ATTRB.ORDER_SUBTOTAL.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WE.getValue();
    // }
    // if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WF.getValue();
    // }
    // if (PRC_RULE_ATTRB.PRICE_LIST.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WG.getValue();
    // }
    // if (PRC_RULE_ATTRB.PRICING_DATE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WH.getValue();
    // }
    // if (PRC_RULE_ATTRB.REQUEST_DATE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WI.getValue();
    // }
    // if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WJ.getValue();
    // }
    // if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WK.getValue();
    // }
    // if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WL.getValue();
    // }
    // if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WM.getValue();
    // }
    // if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WN.getValue();
    // }
    // if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WO.getValue();
    // }
    // if (PRC_RULE_ATTRB.TOTAL_QRY.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WP.getValue();
    // }
    // if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WQ.getValue();
    // }
    // if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WR.getValue();
    // }
    // if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(setCd)) {
    // setPrcRuleTrxCondPK =
    // bizMsg.B.no(i).prcRuleTrxCondPk_WS.getValue();
    // }
    //
    // return setPrcRuleTrxCondPK;
    // }
    // // Add End 2016/10/14 QC#14936

    // Add Start 2016/10/18 QC#14936
    public static EZDCStringItem setEZDCStringItem(EZDSStringItem getItem) {

        EZDCStringItem setItem = new EZDCStringItem();

        if (ZYPCommonFunc.hasValue(getItem)) {
            setItem.setValue(getItem.getValue());
        }

        return setItem;
    }

    public static EZDCBigDecimalItem setEZDCBigDecimalItem(EZDSBigDecimalItem getItem) {

        EZDCBigDecimalItem setItem = new EZDCBigDecimalItem();

        if (ZYPCommonFunc.hasValue(getItem)) {
            setItem.setValue(getItem.getValue());
        }

        return setItem;
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param pageIndex int
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, int pageIndex) {

        NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (pageIndex / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + maxDisplayRows; i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
                // 2019/12/18 QC#55108 Add Start
                NMAL7260_BCMsgArray bizMsgDtl = (NMAL7260_BCMsgArray) cMsgArray;
                // 2019/12/26 QC#55204 Mod Start
                // if (bizMsgDtl.no(indexOfCMsg).prcRuleDtlTxt_B1.getErrorCode() == 0) {
                //     ZYPEZDItemValueSetter.setValue(bizMsgDtl.no(indexOfCMsg).prcRuleDtlTxt_B1, formatAmount(bizMsg.B.no(indexOfCMsg).prcRuleDtlTxt_B1));
                // }
                EZDMessageInfo ezdMessageInfo = getErrorInfo(bizMsgDtl.no(indexOfCMsg), "prcRuleDtlTxt_B1");
                ZYPEZDItemValueSetter.setValue(bizMsgDtl.no(indexOfCMsg).prcRuleDtlTxt_B1, formatAmount(bizMsg.B.no(indexOfCMsg).prcRuleDtlTxt_B1));
                if (ezdMessageInfo != null) {
                    bizMsgDtl.no(indexOfCMsg).prcRuleDtlTxt_B1.setErrorInfo(ezdMessageInfo.getErrorKbn(), ezdMessageInfo.getCode(), ezdMessageInfo.getParameter());
                }
                // 2019/12/26 QC#55204 Mod End
            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL7260CMsg
     * @param glblMsg NMAL7260SMsg
     */
    public static void updateGlblMsg(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            if (checkChangeMsgValue(bizMsg, glblMsg, i, ixG)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).xxRowId_B1, ZYPConstant.FLG_ON_Y);
            }
            EZDMsg.copy(bizMsg.B.no(i), null, glblMsg.B.no(ixG + i), null);
        }

        bizMsg.setCommitSMsg(true);
    }

    public static void updateGlblMsgForDtl(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, List<Integer> selectRows) {
        int k = 0;
        BigDecimal prcAdjDtlPk = BigDecimal.ZERO;
        String PrcRuleAttrb = judgeQtyBreak(bizMsg);
        List<Integer> rows = new ArrayList<Integer>();
        List<BigDecimal> qtyPool = new ArrayList<BigDecimal>();
        NMAL7260_BSMsg bsRow = null;
        NMAL7260_TSMsg tsRow = null;

        ZYPTableUtil.clear(glblMsg.U);

        for (Integer i : selectRows) {
            bsRow = glblMsg.B.no(i);

            prcAdjDtlPk = bsRow.prcAdjDtlPk_B1.getValue();
            rows = getQtyDiscountData(glblMsg, prcAdjDtlPk);

            if (rows.size() > 0) {
                for (Integer j : rows) {
                    tsRow = glblMsg.T.no(j);
                    if (!ZYPConstant.FLG_ON_Y.equals(tsRow.delFlg_T.getValue())) {
                        qtyPool.add(tsRow.qtyDiscDtlQty_T.getValue());
                    }
                }
                Collections.sort(qtyPool);

                EZDMsg.copy(bsRow, null, glblMsg.U.no(k), null);
                glblMsg.U.no(k).prntPrcAdjDtlPk_B1.clear();
                glblMsg.U.no(k).prcRuleCondFromTxt_26.clear();
                glblMsg.U.no(k).prcRuleCondThruTxt_26.clear();
                glblMsg.U.no(k).prcRuleCondFromTxt_58.clear();
                glblMsg.U.no(k).prcRuleCondThruTxt_58.clear();
                if (qtyPool.size() > 0) {
                    if (PRC_RULE_ATTRB.LINE_QTY.equals(PrcRuleAttrb)) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondFromTxt_26, NMAL7260Constant.LOW_VAL_QTY.toString());
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondThruTxt_26, getThruQty(BigDecimal.ONE, qtyPool));
                    } else {
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondFromTxt_58, NMAL7260Constant.LOW_VAL_QTY.toString());
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondThruTxt_58, getThruQty(BigDecimal.ONE, qtyPool));
                    }
                }
                k++;
                for (Integer j : rows) {
                    tsRow = glblMsg.T.no(j);
                    EZDMsg.copy(bsRow, null, glblMsg.U.no(k), null);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).delFlg_B1, tsRow.delFlg_T);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcAdjDtlPk_B1, tsRow.prcAdjDtlPk_T);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prntPrcAdjDtlPk_B1, prcAdjDtlPk);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleDtlRate_B1, tsRow.prcRuleDtlRate_T);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleDtlTxt_B1, tsRow.prcRuleDtlTxt_T);
                    if (PRC_RULE_ATTRB.LINE_QTY.equals(PrcRuleAttrb)) {
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondFromTxt_26, tsRow.qtyDiscDtlQty_T.getValue().toString());
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondThruTxt_26, getThruQty(tsRow.qtyDiscDtlQty_T.getValue(), qtyPool));
                    } else {
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondFromTxt_58, tsRow.qtyDiscDtlQty_T.getValue().toString());
                        ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleCondThruTxt_58, getThruQty(tsRow.qtyDiscDtlQty_T.getValue(), qtyPool));
                    }
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).prcRuleDtlPk_B1, tsRow.prcRuleDtlPk_T);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).ezUpTime_B1, tsRow.ezUpTime_T1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).ezUpTimeZone_B1, tsRow.ezUpTimeZone_T1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).specCondPrcPk_B1, tsRow.specCondPrcPk_T);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).ezUpTime_B3, tsRow.ezUpTime_T3);
                    ZYPEZDItemValueSetter.setValue(glblMsg.U.no(k).ezUpTimeZone_B3, tsRow.ezUpTimeZone_T3);
                    k++;
                }
            } else {
                EZDMsg.copy(bsRow, null, glblMsg.U.no(k), null);
                glblMsg.U.no(k).prntPrcAdjDtlPk_B1.clear();

                k++;
            }
            glblMsg.U.setValidCount(k);
        }
    }

    private static String getThruQty(BigDecimal qty, List<BigDecimal> pool) {
        if (qty == null) {
            return null;
        }
        for (BigDecimal poolQty : pool) {
            if (poolQty.compareTo(qty) > 0) {
                return poolQty.subtract(BigDecimal.ONE).toString();
            }
        }
        return NMAL7260Constant.HIGH_VAL_QTY.toString();
    }

    public static boolean checkChangeMsgValue(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, int i, int ixG) {

        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_04.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_04.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_05.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_05.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_06.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_06.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_07.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_07.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_08.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_08.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_09.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_09.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_10.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_10.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_11.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_11.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_12.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_12.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_13.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_13.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_14.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_14.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_15.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_15.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_15.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_15.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_16.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_16.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_17.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_17.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_18.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_18.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_19.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_19.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_20.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_20.getValue())) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).xxSvcCallDt_FR.getValue(), glblMsg.B.no(i + ixG).xxSvcCallDt_FR.getValue()) != 0) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).xxSvcCallDt_TH.getValue(), glblMsg.B.no(i + ixG).xxSvcCallDt_TH.getValue()) != 0) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_22.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_22.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_22.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_22.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_24.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_24.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_24.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_24.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_25.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_25.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_26.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_26.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_26.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_26.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_27.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_27.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_28.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_28.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_29.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_29.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_30.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_30.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_30.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_30.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_31.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_31.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_32.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_32.getValue())) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).prcDt_FR.getValue(), glblMsg.B.no(i + ixG).prcDt_FR.getValue()) != 0) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).prcDt_TH.getValue(), glblMsg.B.no(i + ixG).prcDt_TH.getValue()) != 0) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_34.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_34.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_35.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_35.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_36.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_36.getValue())) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).xxRqstDt_FR.getValue(), glblMsg.B.no(i + ixG).xxRqstDt_FR.getValue()) != 0) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).xxRqstDt_TH.getValue(), glblMsg.B.no(i + ixG).xxRqstDt_TH.getValue()) != 0) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_38.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_38.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_39.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_39.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_40.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_40.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_41.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_41.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_42.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_42.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_44.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_44.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_45.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_45.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_45.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_45.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_46.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_46.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_48.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_48.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_49.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_49.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_53.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_53.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_54.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_54.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_55.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_55.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_56.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_56.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_57.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_57.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_58.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_58.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondThruTxt_58.getValue(), glblMsg.B.no(i + ixG).prcRuleCondThruTxt_58.getValue())) {
            return true;
        }
        // 2018/04/19 QC#22569 add start
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_59.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_59.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_60.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_60.getValue())) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleCondFromTxt_61.getValue(), glblMsg.B.no(i + ixG).prcRuleCondFromTxt_61.getValue())) {
            return true;
        }
        // 2018/04/19 QC#22569 add end
        if (checkChangeValue(bizMsg.B.no(i).prcFmlaPk_B1, glblMsg.B.no(i + ixG).prcFmlaPk_B1)) {
            return true;
        }
        if (checkChangeValue(bizMsg.B.no(i).prcRuleDtlRate_B1, glblMsg.B.no(i + ixG).prcRuleDtlRate_B1)) {
            return true;
        }
        if (!S21StringUtil.isEquals(bizMsg.B.no(i).prcRuleDtlTxt_B1.getValue(), glblMsg.B.no(i + ixG).prcRuleDtlTxt_B1.getValue())) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).effFromDt_B1.getValue(), glblMsg.B.no(i + ixG).effFromDt_B1.getValue()) != 0) {
            return true;
        }
        if (ZYPDateUtil.compare(bizMsg.B.no(i).effThruDt_B1.getValue(), glblMsg.B.no(i + ixG).effThruDt_B1.getValue()) != 0) {
            return true;
        }

        return false;
    }

    public static boolean checkChangeValue(EZDCBigDecimalItem cItem, EZDSBigDecimalItem sItem) {

        if (ZYPCommonFunc.hasValue(cItem) && !ZYPCommonFunc.hasValue(sItem)) {
            return true;
        } else if (!ZYPCommonFunc.hasValue(cItem) && ZYPCommonFunc.hasValue(sItem)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(cItem) && ZYPCommonFunc.hasValue(sItem) && cItem.getValue().compareTo(sItem.getValue()) != 0) {
            return true;
        }

        return false;
    }

    // Add End 2016/10/18 QC#14936

    // Add Start S21_NA QC#18237(Sol#161)
    /**
     * set Prameters for SQL through Filter
     */
    public static void setFilterParam(Map<String, Object> params, NMAL7260CMsg bizMsg) {
        params.put("prcRuleCondFromTxt_04", bizMsg.D.no(0).prcRuleCondFromTxt_04.getValue());
        params.put("prcRuleCondFromTxt_05", bizMsg.D.no(0).prcRuleCondFromTxt_05.getValue());
        params.put("prcGrpNm_05", bizMsg.D.no(0).prcGrpNm_05.getValue());
        params.put("prcRuleCondFromTxt_06", bizMsg.D.no(0).prcRuleCondFromTxt_06.getValue());
        params.put("prcRuleCondFromTxt_07", bizMsg.D.no(0).prcRuleCondFromTxt_07.getValue());
        params.put("prcRuleCondFromTxt_08", bizMsg.D.no(0).prcRuleCondFromTxt_08.getValue());
        params.put("prcRuleCondFromTxt_09", bizMsg.D.no(0).prcRuleCondFromTxt_09.getValue());
        params.put("prodCtrlNm_07", bizMsg.D.no(0).prodCtrlNm_07.getValue());
        // Mod Start 2018/12/04 QC#29321
        //params.put("prcRuleCondFromTxt_10", bizMsg.D.no(0).prcRuleCondFromTxt_10.getValue());
        //params.put("mdseDescShortTxt_10", bizMsg.D.no(0).mdseDescShortTxt_10.getValue());
        //params.put("mnfItemCd_10", bizMsg.D.no(0).mnfItemCd_10.getValue()); // QC#20267 2018/07/20 Add
        String xxPrcQlfyValSrchTxt_J1 = bizMsg.D.no(0).xxPrcQlfyValSrchTxt_J1.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_J1)) {
            params.put("xxPrcQlfyValSrchTxt_J1", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_J1, COMMA));
        }

        String xxPrcQlfyValSrchTxt_J2 = bizMsg.D.no(0).xxPrcQlfyValSrchTxt_J2.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_J2)) {
            params.put("xxPrcQlfyValSrchTxt_J2", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_J2, COMMA));
        }

        String xxPrcQlfyValSrchTxt_J3 = bizMsg.D.no(0).xxPrcQlfyValSrchTxt_J3.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_J3)) {
            params.put("xxPrcQlfyValSrchTxt_J3", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_J3, COMMA));
        }
        // Mod End 2018/12/04 QC#29321
        params.put("prcRuleCondFromTxt_11", bizMsg.D.no(0).prcRuleCondFromTxt_11.getValue());
        params.put("prcRuleCondFromTxt_12", bizMsg.D.no(0).prcRuleCondFromTxt_12.getValue());
        params.put("prcRuleCondFromTxt_13", bizMsg.D.no(0).prcRuleCondFromTxt_13.getValue());
        params.put("prcRuleCondFromTxt_14", bizMsg.D.no(0).prcRuleCondFromTxt_14.getValue());
        params.put("prcRuleCondFromTxt_AF", bizMsg.D.no(0).prcRuleCondFromTxt_AF.getValue());
        params.put("prcRuleCondFromTxt_AT", bizMsg.D.no(0).prcRuleCondFromTxt_AT.getValue());
        params.put("prcRuleCondThruTxt_AF", bizMsg.D.no(0).prcRuleCondThruTxt_AF.getValue());
        params.put("prcRuleCondThruTxt_AT", bizMsg.D.no(0).prcRuleCondThruTxt_AT.getValue());
        params.put("prcRuleCondFromTxt_16", bizMsg.D.no(0).prcRuleCondFromTxt_16.getValue());
        params.put("billToAcctNm_16", bizMsg.D.no(0).billToAcctNm_16.getValue());
        params.put("prcRuleCondFromTxt_17", bizMsg.D.no(0).prcRuleCondFromTxt_17.getValue());
        params.put("prcRuleCondFromTxt_18", bizMsg.D.no(0).prcRuleCondFromTxt_18.getValue());
        params.put("prcRuleCondFromTxt_19", bizMsg.D.no(0).prcRuleCondFromTxt_19.getValue());
        params.put("coaBrDescTxt_19", bizMsg.D.no(0).coaBrDescTxt_19.getValue());
        params.put("prcRuleCondFromTxt_20", bizMsg.D.no(0).prcRuleCondFromTxt_20.getValue());
        params.put("xxSvcCallDt_FF", bizMsg.D.no(0).xxSvcCallDt_FF.getValue());
        params.put("xxSvcCallDt_FT", bizMsg.D.no(0).xxSvcCallDt_FT.getValue());
        params.put("xxSvcCallDt_TF", bizMsg.D.no(0).xxSvcCallDt_TF.getValue());
        params.put("xxSvcCallDt_TT", bizMsg.D.no(0).xxSvcCallDt_TT.getValue());
        params.put("prcRuleCondFromTxt_BF", bizMsg.D.no(0).prcRuleCondFromTxt_BF.getValue());
        params.put("prcRuleCondFromTxt_BT", bizMsg.D.no(0).prcRuleCondFromTxt_BT.getValue());
        params.put("prcRuleCondThruTxt_BF", bizMsg.D.no(0).prcRuleCondThruTxt_BF.getValue());
        params.put("prcRuleCondThruTxt_BT", bizMsg.D.no(0).prcRuleCondThruTxt_BT.getValue());
        params.put("prcRuleCondFromTxt_CF", bizMsg.D.no(0).prcRuleCondFromTxt_CF.getValue());
        params.put("prcRuleCondFromTxt_CT", bizMsg.D.no(0).prcRuleCondFromTxt_CT.getValue());
        params.put("prcRuleCondThruTxt_CF", bizMsg.D.no(0).prcRuleCondThruTxt_CF.getValue());
        params.put("prcRuleCondThruTxt_CT", bizMsg.D.no(0).prcRuleCondThruTxt_CT.getValue());
        params.put("prcRuleCondFromTxt_25", bizMsg.D.no(0).prcRuleCondFromTxt_25.getValue());
        params.put("prcRuleCondFromTxt_DF", bizMsg.D.no(0).prcRuleCondFromTxt_DF.getValue());
        params.put("prcRuleCondFromTxt_DT", bizMsg.D.no(0).prcRuleCondFromTxt_DT.getValue());
        params.put("prcRuleCondThruTxt_DF", bizMsg.D.no(0).prcRuleCondThruTxt_DF.getValue());
        params.put("prcRuleCondThruTxt_DT", bizMsg.D.no(0).prcRuleCondThruTxt_DT.getValue());
        params.put("prcRuleCondFromTxt_27", bizMsg.D.no(0).prcRuleCondFromTxt_27.getValue());
        params.put("prcRuleCondFromTxt_28", bizMsg.D.no(0).prcRuleCondFromTxt_28.getValue());
        params.put("prcRuleCondFromTxt_29", bizMsg.D.no(0).prcRuleCondFromTxt_29.getValue());
        params.put("prcRuleCondFromTxt_EF", bizMsg.D.no(0).prcRuleCondFromTxt_EF.getValue());
        params.put("prcRuleCondFromTxt_ET", bizMsg.D.no(0).prcRuleCondFromTxt_ET.getValue());
        params.put("prcRuleCondThruTxt_EF", bizMsg.D.no(0).prcRuleCondThruTxt_EF.getValue());
        params.put("prcRuleCondThruTxt_ET", bizMsg.D.no(0).prcRuleCondThruTxt_ET.getValue());
        params.put("prcRuleCondFromTxt_31", bizMsg.D.no(0).prcRuleCondFromTxt_31.getValue());
        params.put("prcRuleCondFromTxt_32", bizMsg.D.no(0).prcRuleCondFromTxt_32.getValue());
        params.put("prcCatgNm_32", bizMsg.D.no(0).prcCatgNm_32.getValue());
        params.put("prcDt_FF", bizMsg.D.no(0).prcDt_FF.getValue());
        params.put("prcDt_FT", bizMsg.D.no(0).prcDt_FT.getValue());
        params.put("prcDt_TF", bizMsg.D.no(0).prcDt_TF.getValue());
        params.put("prcDt_TT", bizMsg.D.no(0).prcDt_TT.getValue());
        params.put("prcRuleCondFromTxt_34", bizMsg.D.no(0).prcRuleCondFromTxt_34.getValue());
        params.put("prcRuleCondFromTxt_35", bizMsg.D.no(0).prcRuleCondFromTxt_35.getValue());
        params.put("prcRuleCondFromTxt_36", bizMsg.D.no(0).prcRuleCondFromTxt_36.getValue());
        params.put("xxRqstDt_FF", bizMsg.D.no(0).xxRqstDt_FF.getValue());
        params.put("xxRqstDt_FT", bizMsg.D.no(0).xxRqstDt_FT.getValue());
        params.put("xxRqstDt_TF", bizMsg.D.no(0).xxRqstDt_TF.getValue());
        params.put("xxRqstDt_TT", bizMsg.D.no(0).xxRqstDt_TT.getValue());
        params.put("prcRuleCondFromTxt_38", bizMsg.D.no(0).prcRuleCondFromTxt_38.getValue());
        params.put("prcRuleCondFromTxt_39", bizMsg.D.no(0).prcRuleCondFromTxt_39.getValue());
        params.put("prcRuleCondFromTxt_40", bizMsg.D.no(0).prcRuleCondFromTxt_40.getValue());
        params.put("prcRuleCondFromTxt_41", bizMsg.D.no(0).prcRuleCondFromTxt_41.getValue());
        params.put("prcRuleCondFromTxt_42", bizMsg.D.no(0).prcRuleCondFromTxt_42.getValue());
        params.put("prcRuleCondFromTxt_44", bizMsg.D.no(0).prcRuleCondFromTxt_44.getValue());
        params.put("prcRuleCondFromTxt_FF", bizMsg.D.no(0).prcRuleCondFromTxt_FF.getValue());
        params.put("prcRuleCondFromTxt_FT", bizMsg.D.no(0).prcRuleCondFromTxt_FT.getValue());
        params.put("prcRuleCondThruTxt_FF", bizMsg.D.no(0).prcRuleCondThruTxt_FF.getValue());
        params.put("prcRuleCondThruTxt_FT", bizMsg.D.no(0).prcRuleCondThruTxt_FT.getValue());
        params.put("prcRuleCondFromTxt_46", bizMsg.D.no(0).prcRuleCondFromTxt_46.getValue());
        params.put("prcRuleCondFromTxt_48", bizMsg.D.no(0).prcRuleCondFromTxt_48.getValue());
        params.put("prcRuleCondFromTxt_49", bizMsg.D.no(0).prcRuleCondFromTxt_49.getValue());

        // Mod Start 2019/01/08 QC#29751
        //params.put("prcRuleCondFromTxt_53", bizMsg.D.no(0).prcRuleCondFromTxt_53.getValue());
        String xxPrcQlfyValSrchTxt_53 = bizMsg.D.no(0).xxPrcQlfyValSrchTxt_53.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_53)) {
            params.put("xxPrcQlfyValSrchTxt_53", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_53, COMMA));
        }
        // Mod End 2019/01/08 QC#29751

        // Mod Start 2018/12/04 QC#29321
        //params.put("prcRuleCondFromTxt_54", bizMsg.D.no(0).prcRuleCondFromTxt_54.getValue());
        //params.put("dsAcctNm_54", bizMsg.D.no(0).dsAcctNm_54.getValue());
        String xxPrcQlfyValSrchTxt_K1 = bizMsg.D.no(0).xxPrcQlfyValSrchTxt_K1.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_K1)) {
            params.put("xxPrcQlfyValSrchTxt_K1", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_K1, COMMA));
        }

        String xxPrcQlfyValSrchTxt_K2 = bizMsg.D.no(0).xxPrcQlfyValSrchTxt_K2.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_K2)) {
            params.put("xxPrcQlfyValSrchTxt_K2", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_K2, COMMA));
        }
        // Mod End 2018/12/04 QC#29321
        params.put("prcRuleCondFromTxt_55", bizMsg.D.no(0).prcRuleCondFromTxt_55.getValue());
        params.put("prcRuleCondFromTxt_56", bizMsg.D.no(0).prcRuleCondFromTxt_56.getValue());
        params.put("prcRuleCondFromTxt_57", bizMsg.D.no(0).prcRuleCondFromTxt_57.getValue());
        params.put("prcRuleCondFromTxt_GF", bizMsg.D.no(0).prcRuleCondFromTxt_GF.getValue());
        params.put("prcRuleCondFromTxt_GT", bizMsg.D.no(0).prcRuleCondFromTxt_GT.getValue());
        params.put("prcRuleCondThruTxt_GF", bizMsg.D.no(0).prcRuleCondThruTxt_GF.getValue());
        params.put("prcRuleCondThruTxt_GT", bizMsg.D.no(0).prcRuleCondThruTxt_GT.getValue());
        // 2018/04/19 QC#22569 add start
        params.put("prcRuleCondFromTxt_59", bizMsg.D.no(0).prcRuleCondFromTxt_59.getValue());
        params.put("prcRuleCondFromTxt_60", bizMsg.D.no(0).prcRuleCondFromTxt_60.getValue());
        params.put("prcRuleCondFromTxt_61", bizMsg.D.no(0).prcRuleCondFromTxt_61.getValue());
        // 2018/04/19 QC#22569 add end
        params.put("prcFmlaPk", bizMsg.D.no(0).prcFmlaPk.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.D.no(0).prcRuleDtlRate.getValue())) {
            params.put("prcRuleDtlRate", bizMsg.D.no(0).prcRuleDtlRate.getValue().divide(new BigDecimal(100)));
        } else {

        }
        params.put("prcRuleDtlTxt", bizMsg.D.no(0).prcRuleDtlTxt.getValue());
        params.put("effFromDt_AF", bizMsg.D.no(0).effFromDt_AF.getValue());
        params.put("effFromDt_AT", bizMsg.D.no(0).effFromDt_AT.getValue());
        params.put("effThruDt_AF", bizMsg.D.no(0).effThruDt_AF.getValue());
        params.put("effThruDt_AT", bizMsg.D.no(0).effThruDt_AT.getValue());
        params.put("xxFullNm_PC", bizMsg.D.no(0).xxFullNm_PC.getValue());
        params.put("cratDt_BF", bizMsg.D.no(0).cratDt_BF.getValue());
        params.put("cratDt_BT", bizMsg.D.no(0).cratDt_BT.getValue());
        params.put("xxFullNm_PU", bizMsg.D.no(0).xxFullNm_PU.getValue());
        params.put("lastUpdDt_CF", bizMsg.D.no(0).lastUpdDt_CF.getValue());
        params.put("lastUpdDt_CT", bizMsg.D.no(0).lastUpdDt_CT.getValue());
        //QC#22593 mod Start
        if (ZYPCommonFunc.hasValue(bizMsg.prcAdjDtlPk_BK.getValue())) {
            params.put("prcAdjDtlPk", bizMsg.prcAdjDtlPk_BK.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcRuleDtlPk_BK.getValue())) {
            params.put("prcRuleDtlPk", bizMsg.prcRuleDtlPk_BK.getValue());
        }
        //QC#22593 mod End
        
    }

    // Add End S21_NA QC#18237(Sol#161)
    // QC#20249 2017/11/10 Add Start
    public static List<Integer> getQtyDiscountData(NMAL7260SMsg glblMsg, BigDecimal prcAdjDtlPk) {
        List<Integer> list = new ArrayList<Integer>();
        if (prcAdjDtlPk == null) {
            return list;
        }
        for (int i = 0; i < glblMsg.T.getValidCount(); i++) {
            if (prcAdjDtlPk.compareTo(glblMsg.T.no(i).prntPrcAdjDtlPk_T.getValue()) == 0) {
                list.add(i);
            }
        }
        return list;
    }

    public static String judgeQtyBreak(NMAL7260CMsg bizMsg) {
        boolean flg26 = false;
        boolean flg50 = false;
        boolean flg57 = false;
        boolean flg58 = false;
        for (int i = 0; i < bizMsg.C.length(); i++) {
            NMAL7260_CCMsg line = bizMsg.C.no(i);
            if (PRC_RULE_ATTRB.LINE_QTY.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg26 = true;
            } else if (PRC_RULE_ATTRB.FORMULA.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg50 = true;
            } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg57 = true;
            } else if (PRC_RULE_ATTRB.LINE_QTY_QTYBREAK.equals(line.prcRuleAttrbCd_C1.getValue())) {
                flg58 = true;
            }
        }
        if (flg26 && flg58) {
            return null;
        }
        if (flg50) {
            return null;
        }
        if (flg26) {
            return PRC_RULE_ATTRB.LINE_QTY;
        }
        if (flg58) {
            return PRC_RULE_ATTRB.LINE_QTY_QTYBREAK;
        }
        if (flg57) {
            return PRC_RULE_ATTRB.LINE_QTY_QTYBREAK;
        }
        return PRC_RULE_ATTRB.LINE_QTY;
    }
    // QC#20249 2017/11/10 Add End

    // 2018/06/07 QC#26099 Add Start
    public static boolean checkDecimalDigit(EZDSStringItem item, int scale) {

        if (!ZYPCommonFunc.hasValue(item)) {
            return true;
        }

        try {
            BigDecimal dec = new BigDecimal(item.getValue());
            int curScale = dec.scale();
            if (curScale > scale) {
                return false;
            }
            dec = dec.setScale(scale);
            item.setValue(dec.toString());
        }
        catch (NumberFormatException e) {
            // do nothing
        }
        return true;
    }

    // 2019/12/18 QC#55108 Mod Start
    //public static String formatAmount(EZDSStringItem item) {
    public static String formatAmount(EZDCStringItem item) {
    // 2019/12/18 QC#55108 Mod End
        String retValue = item.getValue();
        
        try {
            BigDecimal dec = new BigDecimal(item.getValue());

            retValue = DF_AMT.format(dec);
        }
        catch (NumberFormatException e) {
            // do nothing
        }
        catch (IllegalArgumentException e) {
            // do nothing
        }
        return retValue;
    }
    // 2018/06/07 QC#26099 Add End
    
    // 2018/09/03 QC#22600 add start
    public static void setDownloadParam(Map<String, Object> params, NMAL7260CMsg bizMsg) {
        for (int i=0; i < bizMsg.C.length(); i++) {
            String prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
            params.put("attrb_" + prcRuleAttrbCd, "Y");

        }
        params.put("rate", NUM_100);
        params.put("mdseStruElmntTpCd06", MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP);
        params.put("mdseStruElmntTpCd07", MDSE_STRU_ELMNT_TP.PRODUCT_LINE);
        params.put("prcGrpTpCd14", PRC_GRP_TP.ORDER_CATEGORY_OR_REASON);
        params.put("dsAcctTpCd16", DS_ACCT_TP.CUSTOMER);
        params.put("prcGrpCd23", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        params.put("mdseStruElmntTpCd34", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
        params.put("mdseStruElmntTpCd35", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3);
        params.put("mdseStruElmntTpCd36", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4);
        params.put("prcGrpTpCd53", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        params.put("dsAcctTpCd54", DS_ACCT_TP.CUSTOMER);
        params.put("prcGrpTpCd57", PRC_GRP_TP.MATERIAL_PRICING_GROUP_QTY_BREAKS);
        params.put("slsMatGrpSqNum59", "1");
        params.put("slsMatGrpSqNum60", "2");
        params.put("slsMatGrpSqNum61", "3");
    }
    
    /**
     * set Prameters for SQL through Filter
     */
    public static void setFilterParamForSMsg(Map<String, Object> params, NMAL7260SMsg glblMsg) {
        params.put("prcRuleCondFromTxt_04", glblMsg.D.no(0).prcRuleCondFromTxt_04.getValue());
        params.put("prcRuleCondFromTxt_05", glblMsg.D.no(0).prcRuleCondFromTxt_05.getValue());
        params.put("prcGrpNm_05", glblMsg.D.no(0).prcGrpNm_05.getValue());
        params.put("prcRuleCondFromTxt_06", glblMsg.D.no(0).prcRuleCondFromTxt_06.getValue());
        params.put("prcRuleCondFromTxt_07", glblMsg.D.no(0).prcRuleCondFromTxt_07.getValue());
        params.put("prcRuleCondFromTxt_08", glblMsg.D.no(0).prcRuleCondFromTxt_08.getValue());
        params.put("prcRuleCondFromTxt_09", glblMsg.D.no(0).prcRuleCondFromTxt_09.getValue());
        params.put("prodCtrlNm_07", glblMsg.D.no(0).prodCtrlNm_07.getValue());
        // Mod Start 2018/12/04 QC#29321
        //params.put("prcRuleCondFromTxt_10", glblMsg.D.no(0).prcRuleCondFromTxt_10.getValue());
        //params.put("mdseDescShortTxt_10", glblMsg.D.no(0).mdseDescShortTxt_10.getValue());
        //params.put("mnfItemCd_10", glblMsg.D.no(0).mnfItemCd_10.getValue());
        String xxPrcQlfyValSrchTxt_J1 = glblMsg.D.no(0).xxPrcQlfyValSrchTxt_J1.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_J1)) {
            params.put("xxPrcQlfyValSrchTxt_J1", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_J1, COMMA));
        }

        String xxPrcQlfyValSrchTxt_J2 = glblMsg.D.no(0).xxPrcQlfyValSrchTxt_J2.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_J2)) {
            params.put("xxPrcQlfyValSrchTxt_J2", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_J2, COMMA));
        }

        String xxPrcQlfyValSrchTxt_J3 = glblMsg.D.no(0).xxPrcQlfyValSrchTxt_J3.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_J3)) {
            params.put("xxPrcQlfyValSrchTxt_J3", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_J3, COMMA));
        }
        // Mod End 2018/12/04 QC#29321
        params.put("prcRuleCondFromTxt_11", glblMsg.D.no(0).prcRuleCondFromTxt_11.getValue());
        params.put("prcRuleCondFromTxt_12", glblMsg.D.no(0).prcRuleCondFromTxt_12.getValue());
        params.put("prcRuleCondFromTxt_13", glblMsg.D.no(0).prcRuleCondFromTxt_13.getValue());
        params.put("prcRuleCondFromTxt_14", glblMsg.D.no(0).prcRuleCondFromTxt_14.getValue());
        params.put("prcRuleCondFromTxt_AF", glblMsg.D.no(0).prcRuleCondFromTxt_AF.getValue());
        params.put("prcRuleCondFromTxt_AT", glblMsg.D.no(0).prcRuleCondFromTxt_AT.getValue());
        params.put("prcRuleCondThruTxt_AF", glblMsg.D.no(0).prcRuleCondThruTxt_AF.getValue());
        params.put("prcRuleCondThruTxt_AT", glblMsg.D.no(0).prcRuleCondThruTxt_AT.getValue());
        params.put("prcRuleCondFromTxt_16", glblMsg.D.no(0).prcRuleCondFromTxt_16.getValue());
        params.put("billToAcctNm_16", glblMsg.D.no(0).billToAcctNm_16.getValue());
        params.put("prcRuleCondFromTxt_17", glblMsg.D.no(0).prcRuleCondFromTxt_17.getValue());
        params.put("prcRuleCondFromTxt_18", glblMsg.D.no(0).prcRuleCondFromTxt_18.getValue());
        params.put("prcRuleCondFromTxt_19", glblMsg.D.no(0).prcRuleCondFromTxt_19.getValue());
        params.put("coaBrDescTxt_19", glblMsg.D.no(0).coaBrDescTxt_19.getValue());
        params.put("prcRuleCondFromTxt_20", glblMsg.D.no(0).prcRuleCondFromTxt_20.getValue());
        params.put("xxSvcCallDt_FF", glblMsg.D.no(0).xxSvcCallDt_FF.getValue());
        params.put("xxSvcCallDt_FT", glblMsg.D.no(0).xxSvcCallDt_FT.getValue());
        params.put("xxSvcCallDt_TF", glblMsg.D.no(0).xxSvcCallDt_TF.getValue());
        params.put("xxSvcCallDt_TT", glblMsg.D.no(0).xxSvcCallDt_TT.getValue());
        params.put("prcRuleCondFromTxt_BF", glblMsg.D.no(0).prcRuleCondFromTxt_BF.getValue());
        params.put("prcRuleCondFromTxt_BT", glblMsg.D.no(0).prcRuleCondFromTxt_BT.getValue());
        params.put("prcRuleCondThruTxt_BF", glblMsg.D.no(0).prcRuleCondThruTxt_BF.getValue());
        params.put("prcRuleCondThruTxt_BT", glblMsg.D.no(0).prcRuleCondThruTxt_BT.getValue());
        params.put("prcRuleCondFromTxt_CF", glblMsg.D.no(0).prcRuleCondFromTxt_CF.getValue());
        params.put("prcRuleCondFromTxt_CT", glblMsg.D.no(0).prcRuleCondFromTxt_CT.getValue());
        params.put("prcRuleCondThruTxt_CF", glblMsg.D.no(0).prcRuleCondThruTxt_CF.getValue());
        params.put("prcRuleCondThruTxt_CT", glblMsg.D.no(0).prcRuleCondThruTxt_CT.getValue());
        params.put("prcRuleCondFromTxt_25", glblMsg.D.no(0).prcRuleCondFromTxt_25.getValue());
        params.put("prcRuleCondFromTxt_DF", glblMsg.D.no(0).prcRuleCondFromTxt_DF.getValue());
        params.put("prcRuleCondFromTxt_DT", glblMsg.D.no(0).prcRuleCondFromTxt_DT.getValue());
        params.put("prcRuleCondThruTxt_DF", glblMsg.D.no(0).prcRuleCondThruTxt_DF.getValue());
        params.put("prcRuleCondThruTxt_DT", glblMsg.D.no(0).prcRuleCondThruTxt_DT.getValue());
        params.put("prcRuleCondFromTxt_27", glblMsg.D.no(0).prcRuleCondFromTxt_27.getValue());
        params.put("prcRuleCondFromTxt_28", glblMsg.D.no(0).prcRuleCondFromTxt_28.getValue());
        params.put("prcRuleCondFromTxt_29", glblMsg.D.no(0).prcRuleCondFromTxt_29.getValue());
        params.put("prcRuleCondFromTxt_EF", glblMsg.D.no(0).prcRuleCondFromTxt_EF.getValue());
        params.put("prcRuleCondFromTxt_ET", glblMsg.D.no(0).prcRuleCondFromTxt_ET.getValue());
        params.put("prcRuleCondThruTxt_EF", glblMsg.D.no(0).prcRuleCondThruTxt_EF.getValue());
        params.put("prcRuleCondThruTxt_ET", glblMsg.D.no(0).prcRuleCondThruTxt_ET.getValue());
        params.put("prcRuleCondFromTxt_31", glblMsg.D.no(0).prcRuleCondFromTxt_31.getValue());
        params.put("prcRuleCondFromTxt_32", glblMsg.D.no(0).prcRuleCondFromTxt_32.getValue());
        params.put("prcCatgNm_32", glblMsg.D.no(0).prcCatgNm_32.getValue());
        params.put("prcDt_FF", glblMsg.D.no(0).prcDt_FF.getValue());
        params.put("prcDt_FT", glblMsg.D.no(0).prcDt_FT.getValue());
        params.put("prcDt_TF", glblMsg.D.no(0).prcDt_TF.getValue());
        params.put("prcDt_TT", glblMsg.D.no(0).prcDt_TT.getValue());
        params.put("prcRuleCondFromTxt_34", glblMsg.D.no(0).prcRuleCondFromTxt_34.getValue());
        params.put("prcRuleCondFromTxt_35", glblMsg.D.no(0).prcRuleCondFromTxt_35.getValue());
        params.put("prcRuleCondFromTxt_36", glblMsg.D.no(0).prcRuleCondFromTxt_36.getValue());
        params.put("xxRqstDt_FF", glblMsg.D.no(0).xxRqstDt_FF.getValue());
        params.put("xxRqstDt_FT", glblMsg.D.no(0).xxRqstDt_FT.getValue());
        params.put("xxRqstDt_TF", glblMsg.D.no(0).xxRqstDt_TF.getValue());
        params.put("xxRqstDt_TT", glblMsg.D.no(0).xxRqstDt_TT.getValue());
        params.put("prcRuleCondFromTxt_38", glblMsg.D.no(0).prcRuleCondFromTxt_38.getValue());
        params.put("prcRuleCondFromTxt_39", glblMsg.D.no(0).prcRuleCondFromTxt_39.getValue());
        params.put("prcRuleCondFromTxt_40", glblMsg.D.no(0).prcRuleCondFromTxt_40.getValue());
        params.put("prcRuleCondFromTxt_41", glblMsg.D.no(0).prcRuleCondFromTxt_41.getValue());
        params.put("prcRuleCondFromTxt_42", glblMsg.D.no(0).prcRuleCondFromTxt_42.getValue());
        params.put("prcRuleCondFromTxt_44", glblMsg.D.no(0).prcRuleCondFromTxt_44.getValue());
        params.put("prcRuleCondFromTxt_FF", glblMsg.D.no(0).prcRuleCondFromTxt_FF.getValue());
        params.put("prcRuleCondFromTxt_FT", glblMsg.D.no(0).prcRuleCondFromTxt_FT.getValue());
        params.put("prcRuleCondThruTxt_FF", glblMsg.D.no(0).prcRuleCondThruTxt_FF.getValue());
        params.put("prcRuleCondThruTxt_FT", glblMsg.D.no(0).prcRuleCondThruTxt_FT.getValue());
        params.put("prcRuleCondFromTxt_46", glblMsg.D.no(0).prcRuleCondFromTxt_46.getValue());
        params.put("prcRuleCondFromTxt_48", glblMsg.D.no(0).prcRuleCondFromTxt_48.getValue());
        params.put("prcRuleCondFromTxt_49", glblMsg.D.no(0).prcRuleCondFromTxt_49.getValue());

        // Mod Start 2019/01/08 QC#29751
        //params.put("prcRuleCondFromTxt_53", glblMsg.D.no(0).prcRuleCondFromTxt_53.getValue());
        String xxPrcQlfyValSrchTxt_53 = glblMsg.D.no(0).xxPrcQlfyValSrchTxt_53.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_53)) {
            params.put("xxPrcQlfyValSrchTxt_53", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_53, COMMA));
        }
        // Mod End 2019/01/08 QC#29751

        // Mod Start 2018/12/04 QC#29321
        //params.put("prcRuleCondFromTxt_54", glblMsg.D.no(0).prcRuleCondFromTxt_54.getValue());
        //params.put("dsAcctNm_54", glblMsg.D.no(0).dsAcctNm_54.getValue());
        String xxPrcQlfyValSrchTxt_K1 = glblMsg.D.no(0).xxPrcQlfyValSrchTxt_K1.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_K1)) {
            params.put("xxPrcQlfyValSrchTxt_K1", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_K1, COMMA));
        }

        String xxPrcQlfyValSrchTxt_K2 = glblMsg.D.no(0).xxPrcQlfyValSrchTxt_K2.getValue();
        if (ZYPCommonFunc.hasValue(xxPrcQlfyValSrchTxt_K2)) {
            params.put("xxPrcQlfyValSrchTxt_K2", S21StringUtil.toStringArray(xxPrcQlfyValSrchTxt_K2, COMMA));
        }
        // Mod End 2018/12/04 QC#29321
        params.put("prcRuleCondFromTxt_55", glblMsg.D.no(0).prcRuleCondFromTxt_55.getValue());
        params.put("prcRuleCondFromTxt_56", glblMsg.D.no(0).prcRuleCondFromTxt_56.getValue());
        params.put("prcRuleCondFromTxt_57", glblMsg.D.no(0).prcRuleCondFromTxt_57.getValue());
        params.put("prcRuleCondFromTxt_GF", glblMsg.D.no(0).prcRuleCondFromTxt_GF.getValue());
        params.put("prcRuleCondFromTxt_GT", glblMsg.D.no(0).prcRuleCondFromTxt_GT.getValue());
        params.put("prcRuleCondThruTxt_GF", glblMsg.D.no(0).prcRuleCondThruTxt_GF.getValue());
        params.put("prcRuleCondThruTxt_GT", glblMsg.D.no(0).prcRuleCondThruTxt_GT.getValue());
        // 2018/04/19 QC#22569 add start
        params.put("prcRuleCondFromTxt_59", glblMsg.D.no(0).prcRuleCondFromTxt_59.getValue());
        params.put("prcRuleCondFromTxt_60", glblMsg.D.no(0).prcRuleCondFromTxt_60.getValue());
        params.put("prcRuleCondFromTxt_61", glblMsg.D.no(0).prcRuleCondFromTxt_61.getValue());
        // 2018/04/19 QC#22569 add end
        params.put("prcFmlaPk", glblMsg.D.no(0).prcFmlaPk.getValue());
        if (ZYPCommonFunc.hasValue(glblMsg.D.no(0).prcRuleDtlRate.getValue())) {
            params.put("prcRuleDtlRate", glblMsg.D.no(0).prcRuleDtlRate.getValue().divide(new BigDecimal(100)));
        } else {

        }
        params.put("prcRuleDtlTxt", glblMsg.D.no(0).prcRuleDtlTxt.getValue());
        params.put("effFromDt_AF", glblMsg.D.no(0).effFromDt_AF.getValue());
        params.put("effFromDt_AT", glblMsg.D.no(0).effFromDt_AT.getValue());
        params.put("effThruDt_AF", glblMsg.D.no(0).effThruDt_AF.getValue());
        params.put("effThruDt_AT", glblMsg.D.no(0).effThruDt_AT.getValue());
        params.put("xxFullNm_PC", glblMsg.D.no(0).xxFullNm_PC.getValue());
        params.put("cratDt_BF", glblMsg.D.no(0).cratDt_BF.getValue());
        params.put("cratDt_BT", glblMsg.D.no(0).cratDt_BT.getValue());
        params.put("xxFullNm_PU", glblMsg.D.no(0).xxFullNm_PU.getValue());
        params.put("lastUpdDt_CF", glblMsg.D.no(0).lastUpdDt_CF.getValue());
        params.put("lastUpdDt_CT", glblMsg.D.no(0).lastUpdDt_CT.getValue());
    }
    // 2018/09/03 QC#22600 add end

    // Add Start 2018/12/04 QC#29321
    /**
     * Select Unselect check Flag.
     * @param dplyTabD1 String
     * @param glblMsg NMAL7010SMsg
     * @param chkFlg String
     */
    public static void selectUnselect(NMAL7260SMsg glblMsg, String chkFlg) {
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(chkFlg)) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(i).xxChkBox_B1, chkFlg);
            } else {
                glblMsg.B.no(i).xxChkBox_B1.clear();
            }
        }
    }
    // Add End 2018/12/04 QC#29321

    // 2019/03/06 QC#30643 add start
    /**
     * formatDateStr
     * @param baseStr String of format -M/-D/YYYY
     * @return String of formated MM/DD/YYYY
     */
    public static String formatDateStr(String baseStr) {
        String[] devStr = baseStr.split(SLASH);
        StringBuilder sb = new StringBuilder();

        // Illegal case
        if (devStr.length != 3 || devStr[0] == null || devStr[1] == null || devStr[2] == null || devStr[0].length() < 1 || devStr[1].length() < 1 || devStr[2].length() != 4) {
            return baseStr;
        } else {
            if (devStr[0].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[0]);
            sb.append(SLASH);
            if (devStr[1].length() == 1) {
                sb.append(DATE_FORMAT_PADDING_ZERO);
            }
            sb.append(devStr[1]);
            sb.append(SLASH);
            sb.append(devStr[2]);
            return sb.toString();
        }
    }
    // 2019/03/06 QC#30643 add end

    // 2019/12/26 QC#55204 Add Start
    /**
     * <pre>
     * Get Error information from item
     * @param msg EZDMessage
     * @param key item name
     * @return Error Information
     * </pre>
     */
    public static EZDMessageInfo getErrorInfo(EZDMsg msg, String key) {

        EZDMessageInfo info = null;
        try {
            // Invoke EZDSMsg#getErrorInfo(String, int)
            Method method = null;
            if (msg instanceof EZDSMsg) {
                method = EZDSMsg.class.getDeclaredMethod("getErrorInfo", String.class, int.class);
            } else if (msg instanceof EZDCMsg) {
                method = EZDCMsg.class.getDeclaredMethod("getErrorInfo", String.class, int.class);
            } else {
                return null;
            }
            method.setAccessible(true);
            info = (EZDMessageInfo) method.invoke(msg, key, -1);
        } catch (SecurityException e) {
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        }
        return info;
    }
    // 2019/12/26 QC#55204 Add End
}
