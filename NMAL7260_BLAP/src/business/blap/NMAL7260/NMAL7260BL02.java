/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7260;

import static business.blap.NMAL7260.constant.NMAL7260Constant.CSV_DELIMITER;
import static business.blap.NMAL7260.constant.NMAL7260Constant.CSV_DOWNLOAD_COMMON_HEADER;
import static business.blap.NMAL7260.constant.NMAL7260Constant.CSV_DOWNLOAD_COMMON_HEADER_NUM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7260.constant.NMAL7260Constant.CSV_FILE_NM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.GLBLMSG_MAX_LENGTH;
import static business.blap.NMAL7260.constant.NMAL7260Constant.HDR_NM_MNF_NUM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.HIGH_VAL_DT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.HIGH_VAL_TM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.MAX_FETCH_SIZE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0050E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM0163E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8054E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8186E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8321E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8328E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NMAM8329E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NZZM0000E;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NZZM0001W;
import static business.blap.NMAL7260.constant.NMAL7260Constant.NZZM0002I;
import static business.blap.NMAL7260.constant.NMAL7260Constant.SLASH;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_DEF_MAX_CNT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_ACCNT_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BILL_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BILL_TO_ACCT_CHANNEL;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BRANCH;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_BUSINESS_UNIT;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CALL_DATE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CALL_TYPE;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CSMP;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_CLS_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_PO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_DEF;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_EFFECTIVE_DATE_FROM;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_EFFECTIVE_DATE_TO;
import static business.blap.NMAL7260.constant.NMAL7260Constant.TABLE_ITEM_FORMULA;
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
import static business.blap.NMAL7260.constant.NMAL7260Constant.ZZZM9011E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSchemaInfo;
import business.blap.NMAL7260.common.NMAL7260CommonLogic;
import business.blap.NMAL7260.constant.NMAL7260Constant;
import business.db.PRC_RULE_CATGTMsg;
import business.file.NMAL7260F00FMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DISP_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ADJ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_MOD_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * NMAL7260BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/04/06   Fujitsu         Y.Kanefusa      Update          QC#6397
 * 2016/04/14   Fujitsu         Y.Kanefusa      Update          QC#6173
 * 2016/04/19   Fujitsu         W.Honda         Update          QC#6606
 * 2016/05/19   Fujitsu         Y.Kanefusa      Update          QC#5934
 * 2016/06/28   Fujitsu         Y.Kanefusa      Update          S21_NA#9694
 * 2016/07/22   Fujitsu         W.Honda         Update          QC#8201
 * 2016/08/25   Fujitsu         R.Nakamura      Update          QC#3934
 * 2016/09/02   Fujitsu         R.Nakamura      Update          QC#6097
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/09/28   Fujitsu         R.Nakamura      Update          QC#6924
 * 2016/10/11   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/11/11   Fujitsu         R.Nakamura      Update          QC#5940
 * 2017/08/15   Fujitsu         K.Ishizuka      Update          QC#18237(L3#161)
 * 2017/08/24   Fujitsu         S.Ohki          Update          QC#20729
 * 2017/09/12   Fujitsu         H.Nagashima     Update          QC#20968
 * 2017/11/10   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 * 2018/04/09   Fujitsu         H.Nagashima     Update          QC#22593
 * 2018/04/20   Fujitsu         M.Ohno          Update          QC#22569 
 * 2018/05/17   Fujitsu         T.Noguchi       Update          QC#26125
 * 2018/06/06   Fujitsu         T.Noguchi       Update          QC#26491
 * 2018/06/18   Fujitsu         M.Ishii         Update          QC#22594
 * 2018/06/25   Fujitsu         M.Ishii         Update          QC#26100
 * 2018/08/18   Fujitsu         W.Honda         Update          QC#20267
 * 2018/09/03   Fujitsu         M.Ohno          Update          QC#22600
 * 2018/09/12   Fujitsu         M.Ohno          Update          QC#9700
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2018/12/18   Fujitsu         Hd.Sugawara     Update          QC#29661
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 * 2019/03/06   Fujitsu         S.Kosaka        Update          QC#30643
 * 2019/12/05   Fujitsu         C.Hara          Update          QC#54227
 * 2020/01/14   Fujitsu         C.Hara          Update          QC#54227-1
 *</pre>
 */
public class NMAL7260BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;
            NMAL7260SMsg glblMsg = (NMAL7260SMsg) sMsg;

            if ("NMAL7260Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_CMN_Delete(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_Download_Template".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_Download_Template(bizMsg);

                // Add Start 2018/12/18 QC#29661
            } else if ("NMAL7260Scrn00_MassUpd_PrcAdjTbl".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_MassUpd_PrcAdjTbl(bizMsg, glblMsg);

                // Add End 2018/12/18 QC#29661
            } else if ("NMAL7260Scrn00_OnChange_Attribute".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnChange_Attribute(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_OnChange_Category".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnChange_Category(bizMsg);

                // Add Start 2018/12/04 QC#29321
            } else if ("NMAL7260Scrn00_OnChange_PrcDispRecTp".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnChange_PrcDispRecTp(bizMsg, glblMsg);

                // Add End 2018/12/04 QC#29321
            } else if ("NMAL7260Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_Search(bizMsg, glblMsg);

                // Add Start 2018/12/04 QC#29321
            } else if ("NMAL7260Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_SelectAll(bizMsg, glblMsg);

                // Add End 2018/12/04 QC#29321
            } else if ("NMAL7260Scrn00_InsertRow_TblDef".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_InsertRow_TblDef(bizMsg);

            } else if ("NMAL7260Scrn00_InsertRow_TblData".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_InsertRow_TblData(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_CoaMdseNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_CoaMdseNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_CoaProdNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_CoaProdNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_CsmpNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_CsmpNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_DSOrdCatgNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_DSOrdCatgNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_DSOrdTpNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_DSOrdTpNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ItemNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ItemNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_LineBizTpNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_LineBizTpNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_PrcFmlaNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcFmlaNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_PrcGrpMatNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcGrpMatNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ProGrpTrxNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ProGrpTrxNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm2".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm2(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm3".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm3(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm4".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm4(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm5".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm5(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_PrcGrpCustNmSold".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcGrpCustNmSold(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_AcctNmSold".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_AcctNmSold(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_CoaChNmSold".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_CoaChNmSold(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_DsAcctClsNameSold".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_DsAcctClsNameSold(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_BillTo".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_BillTo(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_BillToAcctChnl".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_BillToAcctChnl(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_BillToAcctClss".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_BillToAcctClss(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_Branch".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_Branch(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_CallType".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_CallType(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_LineCatg".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_LineCatg(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_MarketMdlNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_MarketMdlNm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ModelSeg".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ModelSeg(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_OrderSrc".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_OrderSrc(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_PaymentTp".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_PaymentTp(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_PrcList".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcList(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_RtnRsnCd".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_RtnRsnCd(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ServiceLv".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ServiceLv(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ServiceMdl".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ServiceMdl(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ServiceZone".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ServiceZone(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_ShipToAcct".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_ShipToAcct(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_SpecialHandTp".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_SpecialHandTp(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_BizUnit".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_BizUnit(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_FreightTerm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_FreightTerm(bizMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_FreightZone".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_FreightZone(bizMsg);

            } else if ("NMAL7260Scrn00_Show".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_Show(bizMsg, glblMsg);

                // Add Start 2018/12/04 QC#29321
            } else if ("NMAL7260Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_UnSelectAll(bizMsg, glblMsg);

                // Add End 2018/12/04 QC#29321
            } else if ("NMAL7260Scrn00_Upload_CSV".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_Upload_CSV(bizMsg, glblMsg);

            } else if ("NMAL7260_INIT".equals(screenAplID)) {
                doProcess_NMAL7260_INIT(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_PagePrev(bizMsg, glblMsg);

            } else if ("NMAL7260_NMAL7420".equals(screenAplID)) {
//                doProcess_NMAL7260Scrn00_Search(bizMsg, glblMsg);
                doProcess_NMAL7260_NMAL7420(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_TBLColumnSort".equals(screenAplID)) { // Add S21_NA QC#18237(Sol#161) 
                doProcess_NMAL7260Scrn00_TBLColumnSort(bizMsg, glblMsg); // Add S21_NA QC#18237(Sol#161) 

            } else if ("NMAL7260Scrn00_OpenWin_QtyBreak".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OpenWin_QtyBreak(bizMsg, glblMsg);

            } else if ("NMAL7260_NMAL7300".equals(screenAplID)) {
                doProcess_NMAL7260_NMAL7300(bizMsg, glblMsg);

            } else if ("NMAL7260Scrn00_OnBlur_Setting_PrcGrpMatQtyBrkNm".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcGrpMatQtyBrkNm(bizMsg);
            // 2018/04/20 QC#22569 add start
            } else if ("NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt1".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt1(bizMsg);
            } else if ("NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt2".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt2(bizMsg);
            } else if ("NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt3".equals(screenAplID)) {
                doProcess_NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt3(bizMsg);
            // 2018/04/20 QC#22569 add end
            // 2018/07/17 QC#20267 Mod Start
            } else if ("NMAL7260_NMAL6800".equals(screenAplID)) {
                doProcess_NMAL7260_NMAL6800(bizMsg);
            // 2018/07/17 QC#20267 Mod End
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_CMN_Download(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENSION);
        NMAL7260F00FMsg fMsg = new NMAL7260F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String columStr = bizMsg.xxComnColOrdTxt.getValue();
        String[] orderStr = columStr.split(CSV_DELIMITER);
        List<Integer> csvColumNo = new ArrayList<Integer>();
        List<String> csvColumHeder = new ArrayList<String>();
        csvColumNo = NMAL7260CommonLogic.setColNum(csvColumNo, CSV_DOWNLOAD_COMMON_HEADER_NUM);
        csvColumHeder = NMAL7260CommonLogic.setColHeaderData(csvColumHeder, CSV_DOWNLOAD_COMMON_HEADER);
        for (int i = 0; i < orderStr.length; i++) {
            String item = orderStr[i];
            // 2020/01/14 QC#54227-1 Mod Start
            if (TABLE_ITEM_CSMP.equals(item)) {
                csvColumNo.add(2);
                csvColumNo.add(3);
                csvColumHeder.add("CSMP#");
                csvColumHeder.add("CSMP# Name");
            } else if (TABLE_ITEM_MATERIAL_PRICE.equals(item)) {
                csvColumNo.add(4);
                csvColumNo.add(5);
                csvColumHeder.add("Material Price Group");
                csvColumHeder.add("Material Price Group Name");
            } else if (TABLE_ITEM_PROD_CTRL_1.equals(item)) {
                csvColumNo.add(6);
                csvColumNo.add(7);
                csvColumHeder.add("Prod Ctrl -1(BU)");
                csvColumHeder.add("Prod Ctrl -1(BU) Name");
            } else if (TABLE_ITEM_PROD_CTRL_2.equals(item)) {
                csvColumNo.add(8);
                csvColumNo.add(9);
                csvColumHeder.add("Prod Ctrl -2(Model Series)");
                csvColumHeder.add("Prod Ctrl -2(Model Series) Name");
            } else if (TABLE_ITEM_MDSE_TYPE.equals(item)) {
                csvColumNo.add(10);
                csvColumNo.add(11);
                csvColumHeder.add("Mdse Type");
                csvColumHeder.add("Mdse Type Name");
            } else if (TABLE_ITEM_PRODUCT_CD.equals(item)) {
                csvColumNo.add(12);
                csvColumNo.add(13);
                csvColumHeder.add("Product Code");
                csvColumHeder.add("Product Code Name");
            } else if (TABLE_ITEM_ITEM_CD.equals(item)) {
                csvColumNo.add(14);
                csvColumNo.add(15);
                csvColumHeder.add("Item Code");
                csvColumHeder.add("Item Code Name");
            // 2019/12/05 QC#54227 Add Start
            } else if (TABLE_ITEM_MNF_ITEM_CD.equals(item)) {
                csvColumNo.add(16);
                csvColumHeder.add(HDR_NM_MNF_NUM);
            // 2019/12/05 QC#54227 Add End
            // 2019/12/05 QC#54227 Mod Start
            } else if (TABLE_ITEM_ORDER_CATEGORY.equals(item)) {
            	csvColumNo.add(17);
                csvColumNo.add(18);
                csvColumHeder.add("Order Category");
                csvColumHeder.add("Order Category Name");
            } else if (TABLE_ITEM_ORDER_REASON.equals(item)) {
            	csvColumNo.add(19);
                csvColumNo.add(20);
                csvColumHeder.add("Order Reason");
                csvColumHeder.add("Order Reason Name");
            } else if (TABLE_ITEM_ORDER_LINE_OF_BUSINESS.equals(item)) {
            	csvColumNo.add(21);
                csvColumNo.add(22);
                csvColumHeder.add("Order Line of Business");
                csvColumHeder.add("Order Line of Business Name");
            } else if (TABLE_ITEM_TRANSACTION_GROUP.equals(item)) {
            	csvColumNo.add(23);
                csvColumNo.add(24);
                csvColumHeder.add("Transaction Group");
                csvColumHeder.add("Transaction Group Name");
            } else if (TABLE_ITEM_TRANSACTION_WEIGHT.equals(item)) {
                csvColumNo.add(25);
                csvColumNo.add(26);
                csvColumHeder.add("Total Transaction Weight From");
                csvColumHeder.add("Total Transaction Weight To");
            } else if (TABLE_ITEM_BILL_TO.equals(item)) {
            	csvColumNo.add(27);
                csvColumNo.add(28);
                csvColumHeder.add("Bill To (Acct#)");
                csvColumHeder.add("Bill To (Acct#) Name");
            } else if (TABLE_ITEM_BILL_TO_ACCT_CHANNEL.equals(item)) {
            	csvColumNo.add(29);
                csvColumNo.add(30);
                csvColumHeder.add("Bill To Acct (Channel)");
                csvColumHeder.add("Bill To Acct (Channel) Name");
            } else if (TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION.equals(item)) {
            	csvColumNo.add(31);
                csvColumNo.add(32);
                csvColumHeder.add("Bill To Acct (Classification)");
                csvColumHeder.add("Bill To Acct (Classification) Name");
            } else if (TABLE_ITEM_BRANCH.equals(item)) {
            	csvColumNo.add(33);
                csvColumNo.add(34);
                csvColumHeder.add("Branch");
                csvColumHeder.add("Branch Name");
            } else if (TABLE_ITEM_CALL_TYPE.equals(item)) {
            	csvColumNo.add(35);
                csvColumNo.add(36);
                csvColumHeder.add("Call Type");
                csvColumHeder.add("Call Type Name");
            } else if (TABLE_ITEM_CALL_DATE.equals(item)) {
                csvColumNo.add(37);
                csvColumNo.add(38);
                csvColumHeder.add("Call Date From");
                csvColumHeder.add("Call Date To");
            } else if (TABLE_ITEM_CUSTOMER_PO.equals(item)) {
                csvColumNo.add(39);
                csvColumNo.add(40);
                csvColumHeder.add("Customer PO From");
                csvColumHeder.add("Customer PO To");
            } else if (TABLE_ITEM_LINE_AMOUNT.equals(item)) {
                csvColumNo.add(41);
                csvColumNo.add(42);
                csvColumHeder.add("Line Amount From");
                csvColumHeder.add("Line Amount To");
            } else if (TABLE_ITEM_LINE_CATEGORY_LINE_TYPE.equals(item)) {
            	csvColumNo.add(43);
                csvColumNo.add(44);
                csvColumHeder.add("Line Category (Line Type)");
                csvColumHeder.add("Line Category (Line Type) Name");
            } else if (TABLE_ITEM_LINE_QTY.equals(item)) {
                csvColumNo.add(45);
                csvColumNo.add(46);
                csvColumHeder.add("Line Qty From");
                csvColumHeder.add("Line Qty To");
            } else if (TABLE_ITEM_MARKETING_MODEL_NAME.equals(item)) {
            	csvColumNo.add(47);
                csvColumNo.add(48);
                csvColumHeder.add("Marketing Model");
                csvColumHeder.add("Marketing Model Name");
            } else if (TABLE_ITEM_MODEL_SEGMENT.equals(item)) {
            	csvColumNo.add(49);
                csvColumNo.add(50);
                csvColumHeder.add("Model Segment");
                csvColumHeder.add("Model Segment Name");
            } else if (TABLE_ITEM_ORDER_SOURCE.equals(item)) {
            	csvColumNo.add(51);
                csvColumNo.add(52);
                csvColumHeder.add("Order Source");
                csvColumHeder.add("Order Source Name");
            } else if (TABLE_ITEM_ORDER_SUBTOTAL.equals(item)) {
                csvColumNo.add(53);
                csvColumNo.add(54);
                csvColumHeder.add("Order Subtotal From");
                csvColumHeder.add("Order Subtotal To");
            } else if (TABLE_ITEM_PAYMENT_TYPE.equals(item)) {
            	csvColumNo.add(55);
                csvColumNo.add(56);
                csvColumHeder.add("Payment Type");
                csvColumHeder.add("Payment Type Name");
            } else if (TABLE_ITEM_PRICE_LIST.equals(item)) {
            	csvColumNo.add(57);
                csvColumNo.add(58);
                csvColumHeder.add("Price List");
                csvColumHeder.add("Price List Name");
            } else if (TABLE_ITEM_PRICING_DATE.equals(item)) {
                csvColumNo.add(59);
                csvColumNo.add(60);
                csvColumHeder.add("Pricing Date From");
                csvColumHeder.add("Pricing Date To");
            } else if (TABLE_ITEM_PROD_CTRL_3.equals(item)) {
            	csvColumNo.add(61);
                csvColumNo.add(62);
                csvColumHeder.add("Prod Ctrl -3(Product)");
                csvColumHeder.add("Prod Ctrl -3(Product) Name");
            } else if (TABLE_ITEM_PROD_CTRL_4.equals(item)) {
            	csvColumNo.add(63);
                csvColumNo.add(64);
                csvColumHeder.add("Prod Ctrl -4(Product-Group)");
                csvColumHeder.add("Prod Ctrl -4(Product-Group) Name");
            } else if (TABLE_ITEM_PROD_CTRL_5.equals(item)) {
            	csvColumNo.add(65);
                csvColumNo.add(66);
                csvColumHeder.add("Prod Ctrl -5(Product-Type)");
                csvColumHeder.add("Prod Ctrl -5(Product-Type) Name");
            } else if (TABLE_ITEM_REQUEST_DATE.equals(item)) {
                csvColumNo.add(67);
                csvColumNo.add(68);
                csvColumHeder.add("Request Date From");
                csvColumHeder.add("Request Date To");
            } else if (TABLE_ITEM_RETURN_REASON_CODE.equals(item)) {
            	csvColumNo.add(69);
                csvColumNo.add(70);
                csvColumHeder.add("Return Reason Code");
                csvColumHeder.add("Return Reason Code Name");
            } else if (TABLE_ITEM_SERVICE_LEVEL.equals(item)) {
            	csvColumNo.add(71);
                csvColumNo.add(72);
                csvColumHeder.add("Service Level");
                csvColumHeder.add("Service Level Name");
            } else if (TABLE_ITEM_SERVICE_MODEL.equals(item)) {
            	csvColumNo.add(73);
                csvColumNo.add(74);
                csvColumHeder.add("Service Model");
                csvColumHeder.add("Service Model Name");
            } else if (TABLE_ITEM_SERVICE_ZONE.equals(item)) {
            	csvColumNo.add(75);
                csvColumNo.add(76);
                csvColumHeder.add("Service Zone");
                csvColumHeder.add("Service Zone Name");
            } else if (TABLE_ITEM_SHIP_TO_ACCT_CLASSIFICATION.equals(item)) {
            	csvColumNo.add(77);
                csvColumNo.add(78);
                csvColumHeder.add("Ship To Acct (Classification)");
                csvColumHeder.add("Ship To Acct (Classification) Name");
            } else if (TABLE_ITEM_SPECIAL_HANDLING_TYPE.equals(item)) {
            	csvColumNo.add(79);
                csvColumNo.add(80);
                csvColumHeder.add("Special Handling Type");
                csvColumHeder.add("Special Handling Type Name");
            } else if (TABLE_ITEM_TOTAL_QTY.equals(item)) {
                csvColumNo.add(81);
                csvColumNo.add(82);
                csvColumHeder.add("Total Qty From");
                csvColumHeder.add("Total Qty To");
            } else if (TABLE_ITEM_BUSINESS_UNIT.equals(item)) {
            	csvColumNo.add(83);
                csvColumNo.add(84);
                csvColumHeder.add("Business Unit");
                csvColumHeder.add("Business Unit Name");
            } else if (TABLE_ITEM_FREIGHT_TERM.equals(item)) {
            	csvColumNo.add(85);
                csvColumNo.add(86);
                csvColumHeder.add("Freight Term");
                csvColumHeder.add("Freight Term Name");
            } else if (TABLE_ITEM_FREIGHT_ZONE.equals(item)) {
            	csvColumNo.add(87);
                csvColumNo.add(88);
                csvColumHeder.add("Freight Zone");
                csvColumHeder.add("Freight Zone Name");
            } else if (TABLE_ITEM_FORMULA.equals(item)) {
            	csvColumNo.add(89);
                csvColumNo.add(90);
                csvColumHeder.add("Formula");
                csvColumHeder.add("Formula Name");
            } else if (TABLE_ITEM_PERCENT.equals(item)) {
                csvColumNo.add(91);
                csvColumHeder.add("Percent");
            } else if (TABLE_ITEM_VALUE.equals(item)) {
                csvColumNo.add(92);
                csvColumHeder.add("Value");
            } else if (TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO.equals(item)) {
            	csvColumNo.add(93);
                csvColumNo.add(94);
                csvColumHeder.add("Customer Price Group(Sold To)");
                csvColumHeder.add("Customer Price Group(Sold To) Name");
            } else if (TABLE_ITEM_ACCNT_SOLD_TO.equals(item)) {
            	csvColumNo.add(95);
                csvColumNo.add(96);
                csvColumHeder.add("Sold To (Acct#)");
                csvColumHeder.add("Sold To (Acct#) Name");
            } else if (TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO.equals(item)) {
            	csvColumNo.add(97);
                csvColumNo.add(98);
                csvColumHeder.add("Sold To Acct (Channel)");
                csvColumHeder.add("Sold To Acct (Channel) Name");
            } else if (TABLE_ITEM_CUSTOMER_CLS_SOLD_TO.equals(item)) {
            	csvColumNo.add(99);
                csvColumNo.add(100);
                csvColumHeder.add("Sold To Acct (Classification)");
                csvColumHeder.add("Sold To Acct (Classification) Name");
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_PRICE_QTYBRK.equals(item)) {
            	csvColumNo.add(101);
                csvColumNo.add(102);
                csvColumHeder.add("Material Price Group(QtyBrk)");
                csvColumHeder.add("Material Price Group Name(QtyBrk)");
            } else if (NMAL7260Constant.TABLE_ITEM_LINE_QTY_QTYBRK.equals(item)) {
                csvColumNo.add(103);
                csvColumNo.add(104);
                csvColumHeder.add("Line Qty From(QtyBrk)");
                csvColumHeder.add("Line Qty To(QtyBrk)");
                // 2018/04/19 QC#2269 add start
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_1.equals(item)) {
            	csvColumNo.add(105);
                csvColumNo.add(106);
                csvColumHeder.add("Material Group 1");
                csvColumHeder.add("Material Group Name 1");
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_2.equals(item)) {
            	csvColumNo.add(107);
                csvColumNo.add(108);
                csvColumHeder.add("Material Group 2");
                csvColumHeder.add("Material Group Name 2");
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_3.equals(item)) {
            	csvColumNo.add(109);
                csvColumNo.add(110);
                csvColumHeder.add("Material Group 3");
                csvColumHeder.add("Material Group Name 3");
                // 2018/04/19 QC#2269 add end
            } else if (TABLE_ITEM_EFFECTIVE_DATE_FROM.equals(item)) {
                csvColumNo.add(111);
                csvColumHeder.add("Effective Date From");
            } else if (TABLE_ITEM_EFFECTIVE_DATE_TO.equals(item)) {
                csvColumNo.add(112);
                // 2019/12/05 QC#54227 Mod End
                // Mod End 2019/01/08 QC#29751
                csvColumHeder.add("Effective Date To");
                // 2018/07/17 QC#20267 Mod End
            }
            // 2020/01/14 QC#54227-1 Mod End
        }

        // Del Start 2018/12/04 QC#29321
        //csvColumNo = NMAL7260CommonLogic.setColNum(csvColumNo, CSV_DOWNLOAD_COMMON_DATA_NUM);
        //csvColumHeder = NMAL7260CommonLogic.setColHeaderData(csvColumHeder, CSV_DOWNLOAD_COMMON_DATA);
        // Del End 2018/12/04 QC#29321

        int[] orders = new int[csvColumNo.size()];
        for (int j = 0; j < csvColumNo.size(); j++) {
            orders[j] = csvColumNo.get(j);
        }
        fMsg.setItemOrder(orders);
        String[] csvHeders = new String[csvColumHeder.size()];
        for (int k = 0; k < csvColumHeder.size(); k++) {
            csvHeders[k] = csvColumHeder.get(k);
        }
        csvOutFile.writeHeader(csvHeders);

        // Del Start 2018/12/04 QC#29321
//        S21SsmEZDResult ssmResult = null;
//        String lineBizTpDescTxt = null;
//        String prcRuleCatgDescTxt = null;
//        String prcRuleAdjLvlDescTxt = null;
//        String prcRuleModTpDescTxt = null;
//        String prcRuleAdjTpDescTxt = null;
//        // 2018/09/12 QC#9700 add start
//        String prcGrpTrxTpDescTxt = null;
//        // 2018/09/12 QC#9700 add end
//        ssmResult = NMAL7260Query.getInstance().getAnyColmn("LINE_BIZ_TP_DESC_TXT", "LINE_BIZ_TP", "LINE_BIZ_TP_CD", bizMsg.lineBizTpCd_H1.getValue());
//        if (ssmResult.isCodeNormal()) {
//            lineBizTpDescTxt = (String) ssmResult.getResultObject();
//        }
//        ssmResult = NMAL7260Query.getInstance().getAnyColmn("PRC_RULE_CATG_DESC_TXT", "PRC_RULE_CATG", "PRC_RULE_CATG_CD", bizMsg.prcRuleCatgCd_H1.getValue());
//        if (ssmResult.isCodeNormal()) {
//            prcRuleCatgDescTxt = (String) ssmResult.getResultObject();
//        }
//        ssmResult = NMAL7260Query.getInstance().getAnyColmn("PRC_RULE_ADJ_LVL_DESC_TXT", "PRC_RULE_ADJ_LVL", "PRC_RULE_ADJ_LVL_CD", bizMsg.prcRuleAdjLvlCd_H1.getValue());
//        if (ssmResult.isCodeNormal()) {
//            prcRuleAdjLvlDescTxt = (String) ssmResult.getResultObject();
//        }
//        ssmResult = NMAL7260Query.getInstance().getAnyColmn("PRC_RULE_MOD_TP_DESC_TXT", "PRC_RULE_MOD_TP", "PRC_RULE_MOD_TP_CD", bizMsg.prcRuleModTpCd_H1.getValue());
//        if (ssmResult.isCodeNormal()) {
//            prcRuleModTpDescTxt = (String) ssmResult.getResultObject();
//        }
//        ssmResult = NMAL7260Query.getInstance().getAnyColmn("PRC_RULE_ADJ_TP_DESC_TXT", "PRC_RULE_ADJ_TP", "PRC_RULE_ADJ_TP_CD", bizMsg.prcRuleAdjTpCd_H1.getValue());
//        if (ssmResult.isCodeNormal()) {
//            prcRuleAdjTpDescTxt = (String) ssmResult.getResultObject();
//        }
//        // 2018/09/12 QC#9700 add start
//        ssmResult = NMAL7260Query.getInstance().getAnyColmn("PRC_GRP_TRX_TP_DESC_TXT", "PRC_GRP_TRX_TP", "PRC_GRP_TRX_TP_CD", bizMsg.prcGrpTrxTpCd_H1.getValue());
//        if (ssmResult.isCodeNormal()) {
//            prcGrpTrxTpDescTxt = (String) ssmResult.getResultObject();
//        }
//        // 2018/09/12 QC#9700 add end
        // Del End 2018/12/04 QC#29321

        // Del Start 2018/12/04 QC#29321
        //String headEffFromDt = NMAL7260CommonLogic.convertDateFormat(bizMsg.effFromDt_H1.getValue());
        //String headEffThruDt = NMAL7260CommonLogic.convertDateFormat(bizMsg.effThruDt_H1.getValue());
        //String headCratDt = NMAL7260CommonLogic.convertDateFormat(bizMsg.cratDt_H1.getValue());
        //String headLastUpdDt = NMAL7260CommonLogic.convertDateFormat(bizMsg.lastUpdDt_H1.getValue());
        // Del End 2018/12/04 QC#29321

        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7260Query.getInstance().getClass());

            BigDecimal rowNum = ZYPCodeDataUtil.getNumConstValue("NMAL7260_MAX_CSV_DL_SIZE",getGlobalCompanyCode());
            if (rowNum == null) {
                rowNum = new BigDecimal(9999999);
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("prcRuleHdrPk", bizMsg.prcRuleHdrPk_H1.getValue());
            // params.put("rowNum", glblMsg.B.length());
            params.put("highValDt", HIGH_VAL_DT);
            params.put("highValTm", HIGH_VAL_TM);
            params.put("rowNum", rowNum.intValue());
            // Add Start 2018/12/04 QC#29321
            params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            params.put("prcDispRecTpCd", bizMsg.prcDispRecTpCd_H1.getValue());
            // Add End 2018/12/04 QC#29321

            // 2018/09/03 QC#22600 mod start
            NMAL7260CommonLogic.setDownloadParam(params, bizMsg);

            NMAL7260CommonLogic.setFilterParamForSMsg(params, glblMsg);
            ps = ssmLLClient.createPreparedStatement("searchTblDataForDownload", params, execParam);
            rs = ps.executeQuery();

            while (rs.next()) {
                EZDMsg.copy(bizMsg, null, fMsg, null);

                // Del Start 2018/12/04 QC#29321
                //NMAL7260CommonLogic.setStringItem(fMsg.lineBizTpDescTxt_H1, lineBizTpDescTxt);
                //NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCatgDescTxt_H1, prcRuleCatgDescTxt);
                //NMAL7260CommonLogic.setStringItem(fMsg.prcRuleAdjLvlDescTxt_H1, prcRuleAdjLvlDescTxt);
                //NMAL7260CommonLogic.setStringItem(fMsg.prcRuleModTpDescTxt_H1, prcRuleModTpDescTxt);
                //NMAL7260CommonLogic.setStringItem(fMsg.prcRuleAdjTpDescTxt_H1, prcRuleAdjTpDescTxt);
                //// 2018/09/12 QC#9700 add start
                //NMAL7260CommonLogic.setStringItem(fMsg.prcGrpTrxTpDescTxt_H1, prcGrpTrxTpDescTxt);
                //// 2018/09/12 QC#9700 add end
                
                //NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H1, headEffFromDt);
                //NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H2, headEffThruDt);
                //NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H3, headCratDt);
                //NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H4, headLastUpdDt);
                // Del End 2018/12/04 QC#29321

                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_04, rs.getString("PRC_RULE_COND_FROM_TXT_04"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_05, rs.getString("PRC_RULE_COND_FROM_TXT_05"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_06, rs.getString("PRC_RULE_COND_FROM_TXT_06"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_07, rs.getString("PRC_RULE_COND_FROM_TXT_07"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_08, rs.getString("PRC_RULE_COND_FROM_TXT_08"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_09, rs.getString("PRC_RULE_COND_FROM_TXT_09"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_10, rs.getString("PRC_RULE_COND_FROM_TXT_10"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_11, rs.getString("PRC_RULE_COND_FROM_TXT_11"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_12, rs.getString("PRC_RULE_COND_FROM_TXT_12"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_13, rs.getString("PRC_RULE_COND_FROM_TXT_13"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_14, rs.getString("PRC_RULE_COND_FROM_TXT_14"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_15, rs.getString("PRC_RULE_COND_FROM_TXT_15"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_15, rs.getString("PRC_RULE_COND_THRU_TXT_15"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_16, rs.getString("PRC_RULE_COND_FROM_TXT_16"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_17, rs.getString("PRC_RULE_COND_FROM_TXT_17"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_18, rs.getString("PRC_RULE_COND_FROM_TXT_18"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_19, rs.getString("PRC_RULE_COND_FROM_TXT_19"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_20, rs.getString("PRC_RULE_COND_FROM_TXT_20"));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E1, NMAL7260CommonLogic.convertDateFormat(rs.getString("PRC_RULE_COND_FROM_TXT_21")));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E2, NMAL7260CommonLogic.convertDateFormat(rs.getString("PRC_RULE_COND_THRU_TXT_21")));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_22, rs.getString("PRC_RULE_COND_FROM_TXT_22"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_22, rs.getString("PRC_RULE_COND_THRU_TXT_22"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_24, rs.getString("PRC_RULE_COND_FROM_TXT_24"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_24, rs.getString("PRC_RULE_COND_THRU_TXT_24"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_25, rs.getString("PRC_RULE_COND_FROM_TXT_25"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_26, rs.getString("PRC_RULE_COND_FROM_TXT_26"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_26, rs.getString("PRC_RULE_COND_THRU_TXT_26"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_27, rs.getString("PRC_RULE_COND_FROM_TXT_27"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_28, rs.getString("PRC_RULE_COND_FROM_TXT_28"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_29, rs.getString("PRC_RULE_COND_FROM_TXT_29"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_30, rs.getString("PRC_RULE_COND_FROM_TXT_30"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_30, rs.getString("PRC_RULE_COND_THRU_TXT_30"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_31, rs.getString("PRC_RULE_COND_FROM_TXT_31"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_32, rs.getString("PRC_RULE_COND_FROM_TXT_32"));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E3, NMAL7260CommonLogic.convertDateFormat(rs.getString("PRC_RULE_COND_FROM_TXT_33")));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E4, NMAL7260CommonLogic.convertDateFormat(rs.getString("PRC_RULE_COND_THRU_TXT_33")));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_34, rs.getString("PRC_RULE_COND_FROM_TXT_34"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_35, rs.getString("PRC_RULE_COND_FROM_TXT_35"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_36, rs.getString("PRC_RULE_COND_FROM_TXT_36"));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E5, NMAL7260CommonLogic.convertDateFormat(rs.getString("PRC_RULE_COND_FROM_TXT_37")));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E6, NMAL7260CommonLogic.convertDateFormat(rs.getString("PRC_RULE_COND_THRU_TXT_37")));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_38, rs.getString("PRC_RULE_COND_FROM_TXT_38"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_39, rs.getString("PRC_RULE_COND_FROM_TXT_39"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_40, rs.getString("PRC_RULE_COND_FROM_TXT_40"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_41, rs.getString("PRC_RULE_COND_FROM_TXT_41"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_42, rs.getString("PRC_RULE_COND_FROM_TXT_42"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_44, rs.getString("PRC_RULE_COND_FROM_TXT_44"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_45, rs.getString("PRC_RULE_COND_FROM_TXT_45"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_45, rs.getString("PRC_RULE_COND_THRU_TXT_45"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_46, rs.getString("PRC_RULE_COND_FROM_TXT_46"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_48, rs.getString("PRC_RULE_COND_FROM_TXT_48"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_49, rs.getString("PRC_RULE_COND_FROM_TXT_49"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_53, rs.getString("PRC_RULE_COND_FROM_TXT_53"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_54, rs.getString("PRC_RULE_COND_FROM_TXT_54"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_55, rs.getString("PRC_RULE_COND_FROM_TXT_55"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_56, rs.getString("PRC_RULE_COND_FROM_TXT_56"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_57, rs.getString("PRC_RULE_COND_FROM_TXT_57"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_58, rs.getString("PRC_RULE_COND_FROM_TXT_58"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondThruTxt_58, rs.getString("PRC_RULE_COND_THRU_TXT_58"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_59, rs.getString("PRC_RULE_COND_FROM_TXT_59"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_60, rs.getString("PRC_RULE_COND_FROM_TXT_60"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCondFromTxt_61, rs.getString("PRC_RULE_COND_FROM_TXT_61"));

                ZYPEZDItemValueSetter.setValue(fMsg.prcFmlaPk_B1, rs.getBigDecimal("PRC_FMLA_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcRuleDtlRate_B1, rs.getBigDecimal("PRC_RULE_DTL_RATE"));
                NMAL7260CommonLogic.setStringItem(fMsg.prcRuleDtlTxt_B1, rs.getString("PRC_RULE_DTL_TXT"));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_B1, NMAL7260CommonLogic.convertDateFormat(rs.getString("EFF_FROM_DT")));
                NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_B2, NMAL7260CommonLogic.convertDateFormat(rs.getString("EFF_THRU_DT")));

                // Del Start 2018/12/04 QC#29321
                //NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E7, NMAL7260CommonLogic.convertDateFormat(rs.getString("CRAT_DT")));
                //NMAL7260CommonLogic.setStringItem(fMsg.xxFullNm_E1, rs.getString("CRAT_USR_NM"));
                //NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E8, NMAL7260CommonLogic.convertDateFormat(rs.getString("UPD_DT")));
                //NMAL7260CommonLogic.setStringItem(fMsg.xxFullNm_E2, rs.getString("UPD_USR_NM"));
                // Del End 2018/12/04 QC#29321

                // 2020/01/14 QC#54227-1 Mod Start
                //for (int i = 0; i < bizMsg.C.length(); i++) {
                //    String prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                //
                //    // Mod Start 2019/01/08 QC#29751
                //    if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
                //       if (ZYPCommonFunc.hasValue(fMsg.prcRuleCondFromTxt_40)) {
                //            ZYPEZDItemValueSetter.setValue(fMsg.prcRuleCondFromTxt_40, rs.getString("NAME_TXT_40"));
                //        }
                //    // 2019/12/05 QC#54227 Add Start
                //    }else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
                //            NMAL7260CommonLogic.setStringItem(fMsg.mnfItemCd_10, rs.getString("MNF_ITEM_CD_10"));
                //    // 2019/12/05 QC#54227 Add End
                //    }
                //    // Mod End 2019/01/08 QC#29751
                //}
                for (int i = 0; i < bizMsg.C.length(); i++) {
                    String prcRuleAttrbCd = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
                    if (PRC_RULE_ATTRB.CSMP_NUM.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.csmpNumCmntTxt_04, rs.getString("NAME_TXT_04"));
                    } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prcGrpNm_05, rs.getString("NAME_TXT_05"));
                    } else if (PRC_RULE_ATTRB.PROD_CTRL_1_BU.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prodCtrlNm_06, rs.getString("NAME_TXT_06"));
                    } else if (PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prodCtrlNm_07, rs.getString("NAME_TXT_07"));
                    } else if (PRC_RULE_ATTRB.MDSE_TYPE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.coaMdseTpDescTxt_08, rs.getString("NAME_TXT_08"));
                    } else if (PRC_RULE_ATTRB.PRODUCT_CODE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.coaProdDescTxt_09, rs.getString("NAME_TXT_09"));
                    } else if (PRC_RULE_ATTRB.ITEM_CODE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.mdseDescShortTxt_10, rs.getString("NAME_TXT_10"));
                        NMAL7260CommonLogic.setStringItem(fMsg.mnfItemCd_10, rs.getString("MNF_ITEM_CD_10"));
                    } else if (PRC_RULE_ATTRB.ORDER_CATEGORY.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsOrdCatgDescTxt_11, rs.getString("NAME_TXT_11"));
                    } else if (PRC_RULE_ATTRB.ORDER_REASON.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsOrdTpDescTxt_12, rs.getString("NAME_TXT_12"));
                    } else if (PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.lineBizTpDescTxt_13, rs.getString("NAME_TXT_13"));
                    } else if (PRC_RULE_ATTRB.TRANSACTION_GROUP.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prcGrpNm_14, rs.getString("NAME_TXT_14"));
                    } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.billToAcctNm_16, rs.getString("NAME_TXT_16"));
                    } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.coaChDescTxt_17, rs.getString("NAME_TXT_17"));
                    } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsAcctClsDescTxt_18, rs.getString("NAME_TXT_18"));
                    } else if (PRC_RULE_ATTRB.BRANCH.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.coaBrDescTxt_19, rs.getString("NAME_TXT_19"));
                    } else if (PRC_RULE_ATTRB.CALL_TYPE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.svcCallTpDescTxt_20, rs.getString("NAME_TXT_20"));
                    } else if (PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsOrdLineCatgDescTxt_25, rs.getString("NAME_TXT_25"));
                    } else if (PRC_RULE_ATTRB.MARKETING_MODEL_NAME.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.mktMdlDescTxt_27, rs.getString("NAME_TXT_27"));
                    } else if (PRC_RULE_ATTRB.MODEL_SEGMENT.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.mktMdlSegDescTxt_28, rs.getString("NAME_TXT_28"));
                    } else if (PRC_RULE_ATTRB.ORDER_SOURCE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.cpoSrcTpDescTxt_29, rs.getString("NAME_TXT_29"));
                    } else if (PRC_RULE_ATTRB.PAYMENT_TYPE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsPmtMethDescTxt_31, rs.getString("NAME_TXT_31"));
                    } else if (PRC_RULE_ATTRB.PRICE_LIST.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prcCatgNm_32, rs.getString("NAME_TXT_32"));
                    } else if (PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prodCtrlNm_34, rs.getString("NAME_TXT_34"));
                    } else if (PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prodCtrlNm_35, rs.getString("NAME_TXT_35"));
                    } else if (PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prodCtrlNm_36, rs.getString("NAME_TXT_36"));
                    } else if (PRC_RULE_ATTRB.RETURN_REASON_CODE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.rtrnRsnDescTxt_38, rs.getString("NAME_TXT_38"));
                    } else if (PRC_RULE_ATTRB.SERVICE_LEVEL.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.shpgSvcLvlDescTxt_39, rs.getString("NAME_TXT_39"));
                    } else if (PRC_RULE_ATTRB.SERVICE_MODEL.equals(prcRuleAttrbCd)) {
                        if (ZYPCommonFunc.hasValue(fMsg.prcRuleCondFromTxt_40)) {
                            ZYPEZDItemValueSetter.setValue(fMsg.prcRuleCondFromTxt_40, rs.getString("NAME_TXT_40"));
                        }
                        NMAL7260CommonLogic.setStringItem(fMsg.mdlDescTxt_40, rs.getString("NAME_TXT_40"));
                    } else if (PRC_RULE_ATTRB.SERVICE_ZONE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prcSvcZoneDescTxt_41, rs.getString("NAME_TXT_41"));
                    } else if (PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsAcctClsDescTxt_42, rs.getString("NAME_TXT_42"));
                    } else if (PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.spclHdlgTpDescTxt_44, rs.getString("NAME_TXT_44"));
                    } else if (PRC_RULE_ATTRB.BUSINESS_UNIT.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.coaExtnDescTxt_46, rs.getString("NAME_TXT_46"));
                    } else if (PRC_RULE_ATTRB.FREIGHT_TERM.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.frtCondDescTxt_48, rs.getString("NAME_TXT_48"));
                    } else if (PRC_RULE_ATTRB.FREIGHT_ZONE.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.fill40Txt_49, rs.getString("NAME_TXT_49"));
                    } else if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCd)) {
                        ZYPEZDItemValueSetter.setValue(fMsg.prcFmlaNm_B1, rs.getString("NAME_TXT_50"));
                    } else if (PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prcGrpNm_53, rs.getString("NAME_TXT_53"));
                    } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsAcctNm_54, rs.getString("NAME_TXT_54"));
                    } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.coaChDescTxt_55, rs.getString("NAME_TXT_55"));
                    } else if (PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.dsAcctClsDescTxt_56, rs.getString("NAME_TXT_56"));
                    } else if (PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.prcGrpNm_57, rs.getString("NAME_TXT_57"));
                    } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_1.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.slsMatGrpDescTxt_59, rs.getString("NAME_TXT_59"));
                    } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_2.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.slsMatGrpDescTxt_60, rs.getString("NAME_TXT_60"));
                    } else if (PRC_RULE_ATTRB.MATERIAL_GROUP_3.equals(prcRuleAttrbCd)) {
                        NMAL7260CommonLogic.setStringItem(fMsg.slsMatGrpDescTxt_61, rs.getString("NAME_TXT_61"));
                    }
                }
                // 2020/01/14 QC#54227-1 Mod End
                csvOutFile.write();
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

        // for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
        // EZDMsg.copy(bizMsg, null, fMsg, null);
        //
        // NMAL7260CommonLogic.setStringItem(fMsg.lineBizTpDescTxt_H1,
        // lineBizTpDescTxt);
        // NMAL7260CommonLogic.setStringItem(fMsg.prcRuleCatgDescTxt_H1,
        // prcRuleCatgDescTxt);
        // NMAL7260CommonLogic.setStringItem(fMsg.prcRuleAdjLvlDescTxt_H1,
        // prcRuleAdjLvlDescTxt);
        // NMAL7260CommonLogic.setStringItem(fMsg.prcRuleModTpDescTxt_H1,
        // prcRuleModTpDescTxt);
        // NMAL7260CommonLogic.setStringItem(fMsg.prcRuleAdjTpDescTxt_H1,
        // prcRuleAdjTpDescTxt);
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H1,
        // headEffFromDt);
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H2,
        // headEffThruDt);
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H3,
        // headCratDt);
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_H4,
        // headLastUpdDt);
        //
        // EZDMsg.copy(glblMsg.B.no(i), null, fMsg, null);
        //
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_B1,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).effFromDt_B1.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_B2,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).effThruDt_B1.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E1,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).xxSvcCallDt_FR.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E2,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).xxSvcCallDt_TH.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E3,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).prcDt_FR.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E4,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).prcDt_TH.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E5,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).xxRqstDt_FR.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E6,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).xxRqstDt_TH.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E7,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).cratDt_E1.getValue()));
        // NMAL7260CommonLogic.setStringItem(fMsg.xxDtTxt_E8,
        // NMAL7260CommonLogic.convertDateFormat(glblMsg.B.no(i).lastUpdDt_E1.getValue()));
        //
        // csvOutFile.write();
        // }
        csvOutFile.close();
     // 2018/09/03 QC#22600 mod start
    }


    /**
     * CMN_Delete Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_CMN_Delete(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        if (ZYPConstant.FLG_ON_1.equals(bizMsg.xxWrnSkipFlg_H0.getValue())) {
            return;
        }
        doProcess_NMAL7260Scrn00_CMN_Clear(bizMsg, glblMsg);
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_CMN_Save(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        // Add Start 2018/06/25 QC#26100
        EZDMsg.copy(glblMsg.D.no(0), null, bizMsg.D.no(0), null);
        // Add End 2018/06/25 QC#26100
        // Mod Start 2018/06/18 QC#22594
//        search(bizMsg, glblMsg);
        search(bizMsg, glblMsg, true);
        // Mod End 2018/06/18 QC#22594
    }

    /**
     * OnChange_Attribute Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_OnChange_Attribute(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        String prcRuleAttrbCdData = bizMsg.xxLocRoleTpCdListTxt_H1.getValue();
        String[] prcRuleAttrbCdListData = prcRuleAttrbCdData.split(",");
        String ruleAttrbDesctxtData = bizMsg.xxLocRoleTpCdListTxt_H2.getValue();
        String[] ruleAttrbDesctxtDataList = ruleAttrbDesctxtData.split(",");
        String inpReqFlgData = bizMsg.xxLocRoleTpCdListTxt_H3.getValue();
        String[] inpReqFlgDataList = inpReqFlgData.split(",");

        // Screen Data(C) Create
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            String prcRuleAttrbCdC = bizMsg.C.no(i).prcRuleAttrbCd_C1.getValue();
            for (int j = 0; j < prcRuleAttrbCdListData.length; j++) {
                if (prcRuleAttrbCdC.equals(prcRuleAttrbCdListData[j])) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).inpObjTpDescTxt_C1, ruleAttrbDesctxtDataList[j]);
                    if (PRC_RULE_ATTRB.FORMULA.equals(prcRuleAttrbCdC) || PRC_RULE_ATTRB.PERCENT.equals(prcRuleAttrbCdC) || PRC_RULE_ATTRB.VALUE.equals(prcRuleAttrbCdC)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).inpReqFlg_C1, ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(i).inpReqFlg_C1, inpReqFlgDataList[j]);
                    }
                    break;
                }
            }
        }

    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_Download_Template(NMAL7260CMsg bizMsg) {
        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXTENSION);
        NMAL7260F00FMsg fMsg = new NMAL7260F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        String columStr = bizMsg.xxComnColOrdTxt.getValue();
        String[] orderStr = columStr.split(CSV_DELIMITER);
        List<Integer> csvColumNo = new ArrayList<Integer>();
        List<String> csvColumHeder = new ArrayList<String>();

        // 2018/05/17 QC#26125 Mod Start.
        csvColumNo = NMAL7260CommonLogic.setColNum(csvColumNo, CSV_DOWNLOAD_COMMON_HEADER_NUM);
        csvColumHeder = NMAL7260CommonLogic.setColHeaderData(csvColumHeder, CSV_DOWNLOAD_COMMON_HEADER);
        // 2018/05/17 QC#26125 Mod End.
        for (int i = 0; i < orderStr.length; i++) {
            String item = orderStr[i];
            if (TABLE_ITEM_CSMP.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(25);
                //csvColumNo.add(26);
                csvColumNo.add(1);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("CSMP#");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("CSMP# Name");
                // Del End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_MATERIAL_PRICE.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(27);
                //csvColumNo.add(28);
                csvColumNo.add(2);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("Material Price Group");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("Material Price Group Name");
                // Del End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_PROD_CTRL_1.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(29);
                //csvColumNo.add(30);
                csvColumNo.add(3);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("Prod Ctrl -1(BU)");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("Prod Ctrl -1(BU) Name");
                // Del End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_PROD_CTRL_2.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(31);
                //csvColumNo.add(32);
                csvColumNo.add(4);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("Prod Ctrl -2(Model Series)");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("Prod Ctrl -2(Model Series) Name");
                // Del End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_MDSE_TYPE.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(33);
                //csvColumNo.add(34);
                csvColumNo.add(5);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("Mdse Type");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("Mdse Type Name");
                // Del End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_PRODUCT_CD.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(35);
                //csvColumNo.add(36);
                csvColumNo.add(6);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("Product Code");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("Product Code Name");
                // Del End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_ITEM_CD.equals(item)) {
                // Mod Start 2018/12/04 QC#29321
                //csvColumNo.add(37);
                //csvColumNo.add(38);
                csvColumNo.add(7);
                // Mod End 2018/12/04 QC#29321
                csvColumHeder.add("Item Code");
                // Del Start 2018/12/04 QC#29321
                //csvColumHeder.add("Item Code Name");
                // Del End 2018/12/04 QC#29321
                // Mod Start 2019/01/08 QC#29751
            //    // Add Start 2018/12/04 QC#29321
            //} else if (TABLE_ITEM_MNF_ITEM_CD.equals(item)) {
            //    csvColumNo.add(8);
            //    csvColumHeder.add(HDR_NM_MNF_NUM);
            //    // Add End 2018/12/04 QC#29321
            } else if (TABLE_ITEM_ORDER_CATEGORY.equals(item)) {
                csvColumNo.add(8);
                csvColumHeder.add("Order Category");
            } else if (TABLE_ITEM_ORDER_REASON.equals(item)) {
                csvColumNo.add(9);
                csvColumHeder.add("Order Reason");
            } else if (TABLE_ITEM_ORDER_LINE_OF_BUSINESS.equals(item)) {
                csvColumNo.add(10);
                csvColumHeder.add("Order Line of Business");
            } else if (TABLE_ITEM_TRANSACTION_GROUP.equals(item)) {
                csvColumNo.add(11);
                csvColumHeder.add("Transaction Group");
            } else if (TABLE_ITEM_TRANSACTION_WEIGHT.equals(item)) {
                csvColumNo.add(12);
                csvColumNo.add(13);
                csvColumHeder.add("Total Transaction Weight From");
                csvColumHeder.add("Total Transaction Weight To");
            } else if (TABLE_ITEM_BILL_TO.equals(item)) {
                csvColumNo.add(14);
                csvColumHeder.add("Bill To (Acct#)");
            } else if (TABLE_ITEM_BILL_TO_ACCT_CHANNEL.equals(item)) {
                csvColumNo.add(15);
                csvColumHeder.add("Bill To Acct (Channel)");
            } else if (TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION.equals(item)) {
                csvColumNo.add(16);
                csvColumHeder.add("Bill To Acct (Classification)");
            } else if (TABLE_ITEM_BRANCH.equals(item)) {
                csvColumNo.add(17);
                csvColumHeder.add("Branch");
            } else if (TABLE_ITEM_CALL_TYPE.equals(item)) {
                csvColumNo.add(18);
                csvColumHeder.add("Call Type");
            } else if (TABLE_ITEM_CALL_DATE.equals(item)) {
                csvColumNo.add(19);
                csvColumNo.add(20);
                csvColumHeder.add("Call Date From");
                csvColumHeder.add("Call Date To");
            } else if (TABLE_ITEM_CUSTOMER_PO.equals(item)) {
                csvColumNo.add(21);
                csvColumNo.add(22);
                csvColumHeder.add("Customer PO From");
                csvColumHeder.add("Customer PO To");
            } else if (TABLE_ITEM_LINE_AMOUNT.equals(item)) {
                csvColumNo.add(23);
                csvColumNo.add(24);
                csvColumHeder.add("Line Amount From");
                csvColumHeder.add("Line Amount To");
            } else if (TABLE_ITEM_LINE_CATEGORY_LINE_TYPE.equals(item)) {
                csvColumNo.add(25);
                csvColumHeder.add("Line Category (Line Type)");
            } else if (TABLE_ITEM_LINE_QTY.equals(item)) {
                csvColumNo.add(26);
                csvColumNo.add(27);
                csvColumHeder.add("Line Qty From");
                csvColumHeder.add("Line Qty To");
            } else if (TABLE_ITEM_MARKETING_MODEL_NAME.equals(item)) {
                csvColumNo.add(28);
                csvColumHeder.add("Marketing Model Name");
            } else if (TABLE_ITEM_MODEL_SEGMENT.equals(item)) {
                csvColumNo.add(29);
                csvColumHeder.add("Model Segment");
            } else if (TABLE_ITEM_ORDER_SOURCE.equals(item)) {
                csvColumNo.add(30);
                csvColumHeder.add("Order Source");
            } else if (TABLE_ITEM_ORDER_SUBTOTAL.equals(item)) {
                csvColumNo.add(31);
                csvColumNo.add(32);
                csvColumHeder.add("Order Subtotal From");
                csvColumHeder.add("Order Subtotal To");
            } else if (TABLE_ITEM_PAYMENT_TYPE.equals(item)) {
                csvColumNo.add(33);
                csvColumHeder.add("Payment Type");
            } else if (TABLE_ITEM_PRICE_LIST.equals(item)) {
                csvColumNo.add(34);
                csvColumHeder.add("Price List");
            } else if (TABLE_ITEM_PRICING_DATE.equals(item)) {
                csvColumNo.add(35);
                csvColumNo.add(36);
                csvColumHeder.add("Pricing Date From");
                csvColumHeder.add("Pricing Date To");
            } else if (TABLE_ITEM_PROD_CTRL_3.equals(item)) {
                csvColumNo.add(37);
                csvColumHeder.add("Prod Ctrl -3(Product)");
            } else if (TABLE_ITEM_PROD_CTRL_4.equals(item)) {
                csvColumNo.add(38);
                csvColumHeder.add("Prod Ctrl -4(Product-Group)");
            } else if (TABLE_ITEM_PROD_CTRL_5.equals(item)) {
                csvColumNo.add(39);
                csvColumHeder.add("Prod Ctrl -5(Product-Type)");
            } else if (TABLE_ITEM_REQUEST_DATE.equals(item)) {
                csvColumNo.add(40);
                csvColumNo.add(41);
                csvColumHeder.add("Request Date From");
                csvColumHeder.add("Request Date To");
            } else if (TABLE_ITEM_RETURN_REASON_CODE.equals(item)) {
                csvColumNo.add(42);
                csvColumHeder.add("Return Reason Code");
            } else if (TABLE_ITEM_SERVICE_LEVEL.equals(item)) {
                csvColumNo.add(43);
                csvColumHeder.add("Service Level");
            } else if (TABLE_ITEM_SERVICE_MODEL.equals(item)) {
                csvColumNo.add(44);
                csvColumHeder.add("Service Model");
            } else if (TABLE_ITEM_SERVICE_ZONE.equals(item)) {
                csvColumNo.add(45);
                csvColumHeder.add("Service Zone");
            } else if (TABLE_ITEM_SHIP_TO_ACCT_CLASSIFICATION.equals(item)) {
                csvColumNo.add(46);
                csvColumHeder.add("Ship To Acct (Classification)");
            } else if (TABLE_ITEM_SPECIAL_HANDLING_TYPE.equals(item)) {
                csvColumNo.add(47);
                csvColumHeder.add("Special Handling Type");
            } else if (TABLE_ITEM_TOTAL_QTY.equals(item)) {
                csvColumNo.add(48);
                csvColumNo.add(49);
                csvColumHeder.add("Total Qty From");
                csvColumHeder.add("Total Qty To");
            } else if (TABLE_ITEM_BUSINESS_UNIT.equals(item)) {
                csvColumNo.add(50);
                csvColumHeder.add("Business Unit");
            } else if (TABLE_ITEM_FREIGHT_TERM.equals(item)) {
                csvColumNo.add(51);
                csvColumHeder.add("Freight Term");
            } else if (TABLE_ITEM_FREIGHT_ZONE.equals(item)) {
                csvColumNo.add(52);
                csvColumHeder.add("Freight Zone");
            } else if (TABLE_ITEM_FORMULA.equals(item)) {
                csvColumNo.add(53);
                csvColumHeder.add("Formula");
            } else if (TABLE_ITEM_PERCENT.equals(item)) {
                csvColumNo.add(54);
                csvColumHeder.add("Percent");
            } else if (TABLE_ITEM_VALUE.equals(item)) {
                csvColumNo.add(55);
                csvColumHeder.add("Value");
            } else if (TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO.equals(item)) {
                csvColumNo.add(56);
                csvColumHeder.add("Customer Price Group(Sold To)");
            } else if (TABLE_ITEM_ACCNT_SOLD_TO.equals(item)) {
                csvColumNo.add(57);
                csvColumHeder.add("Sold To (Acct#)");
            } else if (TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO.equals(item)) {
                csvColumNo.add(58);
                csvColumHeder.add("Sold To Acct (Channel)");
            } else if (TABLE_ITEM_CUSTOMER_CLS_SOLD_TO.equals(item)) {
                csvColumNo.add(59);
                csvColumHeder.add("Sold To Acct (Classification)");
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_PRICE_QTYBRK.equals(item)) {
                csvColumNo.add(60);
                csvColumHeder.add("Material Price Group(QtyBrk)");
            } else if (NMAL7260Constant.TABLE_ITEM_LINE_QTY_QTYBRK.equals(item)) {
                csvColumNo.add(61);
                csvColumNo.add(62);
                csvColumHeder.add("Line Qty From(QtyBrk)");
                csvColumHeder.add("Line Qty To(QtyBrk)");
            // 2018/04/20 QC#22569 add start
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_1.equals(item)) {
                csvColumNo.add(63);
                csvColumHeder.add("Material Group 1");
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_2.equals(item)) {
                csvColumNo.add(64);
                csvColumHeder.add("Material Group 2");
            } else if (NMAL7260Constant.TABLE_ITEM_MATERIAL_GRP_3.equals(item)) {
                csvColumNo.add(65);
                csvColumHeder.add("Material Group 3");
            // 2018/04/20 QC#22569 add end
            } else if (TABLE_ITEM_EFFECTIVE_DATE_FROM.equals(item)) {
                csvColumNo.add(66);
                csvColumHeder.add("Effective Date From");
            } else if (TABLE_ITEM_EFFECTIVE_DATE_TO.equals(item)) {
                csvColumNo.add(67);
                csvColumHeder.add("Effective Date To");
                // Mod End 2019/01/08 QC#29751
            }
        }
        // 2018/05/17 QC#26125 Mod Start.
        // Del Start 2018/12/04 QC#29321
        //csvColumNo = NMAL7260CommonLogic.setColNum(csvColumNo, CSV_DOWNLOAD_COMMON_DATA_NUM);
        //csvColumHeder = NMAL7260CommonLogic.setColHeaderData(csvColumHeder, CSV_DOWNLOAD_COMMON_DATA);
        // Del End 2018/12/04 QC#29321
        // 2018/05/17 QC#26125 Mod End.

        int[] orders = new int[csvColumNo.size()];
        for (int j = 0; j < csvColumNo.size(); j++) {
            orders[j] = csvColumNo.get(j);
        }
        fMsg.setItemOrder(orders);
        String[] csvHeders = new String[csvColumHeder.size()];
        for (int k = 0; k < csvColumHeder.size(); k++) {
            csvHeders[k] = csvColumHeder.get(k);
        }
        csvOutFile.writeHeader(csvHeders);

        csvOutFile.write();

        csvOutFile.close();

    }

    /**
     * OnChange_Category Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnChange_Category(NMAL7260CMsg bizMsg) {

        PRC_RULE_CATGTMsg inTMsg = new PRC_RULE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(inTMsg.prcRuleCatgCd, bizMsg.prcRuleCatgCd_H1);
        PRC_RULE_CATGTMsg outTMsg = (PRC_RULE_CATGTMsg) ZYPCodeDataUtil.findByKey(inTMsg);
        if (outTMsg == null) {
            bizMsg.setMessageInfo(NMAM0163E, new String[] {getGlobalCompanyCode(), "Price Rule Category" });
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.defRulePrcdNum_H1, outTMsg.defRulePrcdNum);
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_Search(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.D);       // QC#20968 add
     // Mod Start 2018/06/18 QC#22594
//        search(bizMsg, glblMsg);
        search(bizMsg, glblMsg, false);
     // Mod End 2018/06/18 QC#22594
    }

    /**
     * InsertRow_TblDef Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_InsertRow_TblDef(NMAL7260CMsg bizMsg) {
        if (bizMsg.C.getValidCount() >= TABLE_DEF_MAX_CNT) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(TABLE_DEF_MAX_CNT) });
        }

        String ruleAttrbDesctxtData = bizMsg.xxLocRoleTpCdListTxt_H2.getValue();
        String[] ruleAttrbDesctxtDataList = ruleAttrbDesctxtData.split(",");
        String inpReqFlgData = bizMsg.xxLocRoleTpCdListTxt_H3.getValue();
        String[] inpReqFlgDataList = inpReqFlgData.split(",");

        int curIdxScreen = bizMsg.C.getValidCount();
        if (curIdxScreen != 0) {
            BigDecimal nowMax = bizMsg.C.no(curIdxScreen - 1).prcRuleCondNum_C1.getValue();
            if (nowMax.intValue() >= bizMsg.C.length()) {
                bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.C.length()) });
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(curIdxScreen).prcRuleCondNum_C1, nowMax.add(BigDecimal.ONE));
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.C.no(curIdxScreen).prcRuleCondNum_C1, BigDecimal.ONE);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(curIdxScreen).inpObjTpDescTxt_C1, ruleAttrbDesctxtDataList[0]);
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(curIdxScreen).inpReqFlg_C1, inpReqFlgDataList[0]);
        bizMsg.C.setValidCount(curIdxScreen + 1);

    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_InsertRow_TblData(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        if (glblMsg.B.getValidCount() >= glblMsg.B.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(glblMsg.B.length()) });
            return;
        }

        int curIdx = bizMsg.B.getValidCount();
        int curIdxS = 0;

        if (curIdx > 0) {
            NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);
            curIdxS = glblMsg.B.getValidCount();
        }
        glblMsg.B.setValidCount(curIdxS + 1);
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, curIdxS);
        curIdx = curIdxS % bizMsg.B.length();
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(curIdx).effFromDt_B1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(curIdx).xxRowId_B1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(curIdx).xxLinkAncr_B1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdxS).xxRowId_B1, ZYPConstant.FLG_ON_Y);

        bizMsg.B.setValidCount(curIdx + 1);
    }

    /**
     * OnBlur_Setting_CoaMdseNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_CoaMdseNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.MDSE_TYPE, bizMsg.B.no(idx).prcRuleCondFromTxt_08, bizMsg.B.no(idx).coaMdseTpDescTxt_08, "Mdse Type")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Mdse Type" });
        }
    }

    /**
     * OnBlur_Setting_CoaProdNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_CoaProdNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.PRODUCT_CODE, bizMsg.B.no(idx).prcRuleCondFromTxt_09, bizMsg.B.no(idx).coaProdDescTxt_09, "Product Code")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Product Code" });
        }
    }

    /**
     * OnBlur_Setting_CsmpNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_CsmpNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.CSMP_NUM, bizMsg.B.no(idx).prcRuleCondFromTxt_04, bizMsg.B.no(idx).csmpNumCmntTxt_04, "CSMP#")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"CSMP#" });
        }
    }

    /**
     * OnBlur_Setting_DSOrdCatgNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_DSOrdCatgNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.ORDER_CATEGORY, bizMsg.B.no(idx).prcRuleCondFromTxt_11, bizMsg.B.no(idx).dsOrdCatgDescTxt_11, "Order Category")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Order Category" });
        }
    }

    /**
     * OnBlur_Setting_DSOrdTpNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_DSOrdTpNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.ORDER_REASON, bizMsg.B.no(idx).prcRuleCondFromTxt_12, bizMsg.B.no(idx).dsOrdTpDescTxt_12, "Order Type")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Order Reason" });
        }
    }

    /**
     * OnBlur_Setting_ItemNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ItemNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        EZDCStringItem mdseCdFld = bizMsg.B.no(idx).prcRuleCondFromTxt_10;
        // 2018/07/17 QC#20267 Mod Start
//        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.ITEM_CODE, mdseCd, bizMsg.B.no(idx).mdseDescShortTxt_10, "Item Code")) {
//            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Item Code" });
//        } else {
//            S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getMdseNm(mdseCd.getValue());
//            if (ssmResult.isCodeNotFound()) {
//                mdseCd.setErrorInfo(1, NMAM0179E, new String[] {mdseCd.getValue(), "No Service Item" });
//            } else {
//                String getMdseNm = (String) ssmResult.getResultObject();
//                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).mdseDescShortTxt_10, getMdseNm);
//            }
//        }

        EZDCStringItem mnfItemCdFld = bizMsg.B.no(idx).mnfItemCd_10;
        EZDCStringItem mdseDescShortTxtFld = bizMsg.B.no(idx).mdseDescShortTxt_10;
        EZDCStringItem srchOrigItemFlgFld = bizMsg.srchOrigItemFlg_MF;

        NMAL7260CommonLogic.setMdseInfo(bizMsg, mdseCdFld, mdseDescShortTxtFld, mnfItemCdFld, srchOrigItemFlgFld);
        // 2018/07/17 QC#20267 Mod End
    }

    /**
     * OnBlur_Setting_LineBizTpNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_LineBizTpNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.ORDER_LINE_OF_BUSINESS, bizMsg.B.no(idx).prcRuleCondFromTxt_13, bizMsg.B.no(idx).lineBizTpDescTxt_13, "Order Line of Business")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Order Line of Business" });
        }
    }

    /**
     * OnBlur_Setting_PrcFmlaNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcFmlaNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        NMXC105001PriceMasterUtil.getRuleAttrbFmlaNm(PRC_RULE_ATTRB.FORMULA, bizMsg.B.no(idx).prcFmlaPk_B1, bizMsg.B.no(idx).prcFmlaNm_B1, "Formula");
    }

    /**
     * OnBlur_Setting_PrcGrpMatNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcGrpMatNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP, bizMsg.B.no(idx).prcRuleCondFromTxt_05, bizMsg.B.no(idx).prcGrpNm_05, "Material Price Group")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Material Price Group" });
        }
    }

    /**
     * OnBlur_Setting_PrcGrpMatNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcGrpMatQtyBrkNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK, bizMsg.B.no(idx).prcRuleCondFromTxt_57, bizMsg.B.no(idx).prcGrpNm_57, "Material Price Group(Qty Break)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Material Price Group(Qty Break)" });
        }
    }

    /**
     * OnBlur_Setting_ProGrpTrxNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ProGrpTrxNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.TRANSACTION_GROUP, bizMsg.B.no(idx).prcRuleCondFromTxt_14, bizMsg.B.no(idx).prcGrpNm_14, "Transaction Group")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Transaction Group" });
        }
    }

    /**
     * OnBlur_Setting_ProdCtrlNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.PROD_CTRL_1_BU, bizMsg.B.no(idx).prcRuleCondFromTxt_06, bizMsg.B.no(idx).prodCtrlNm_06, "Prod Ctrl -1(BU)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Prod Ctrl -1(BU)" });
        }
    }

    /**
     * OnBlur_Setting_ProdCtrlNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm2(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.PROD_CTRL_2_MODEL_SERIES, bizMsg.B.no(idx).prcRuleCondFromTxt_07, bizMsg.B.no(idx).prodCtrlNm_07, "Prod Ctrl -2(Model Series)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Prod Ctrl -2(Model Series)" });
        }
    }

    /**
     * OnBlur_Setting_ProdCtrlNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm3(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT, bizMsg.B.no(idx).prcRuleCondFromTxt_34, bizMsg.B.no(idx).prodCtrlNm_34, "Prod Ctrl -3(Product)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Prod Ctrl -3(Product)" });
        }
    }

    /**
     * OnBlur_Setting_ProdCtrlNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm4(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.PROD_CTRL_4_PRODUCT_GROUP, bizMsg.B.no(idx).prcRuleCondFromTxt_35, bizMsg.B.no(idx).prodCtrlNm_35, "Prod Ctrl -4(Product-Group)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Prod Ctrl -4(Product-Group)" });
        }
    }

    /**
     * OnBlur_Setting_ProdCtrlNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ProdCtrlNm5(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.PROD_CTRL_5_PRODUCT_TYPE, bizMsg.B.no(idx).prcRuleCondFromTxt_36, bizMsg.B.no(idx).prodCtrlNm_36, "Prod Ctrl -5(Product-Type)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Prod Ctrl -5(Product-Type)" });
        }
    }

    /**
     * OnBlur_Setting_PrcGrpCustNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcGrpCustNmSold(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMXC105001PriceMasterUtil.getRuleAttrbNm(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO, bizMsg.B.no(idx).prcRuleCondFromTxt_53, bizMsg.B.no(idx).prcGrpNm_53, "Customer Price Group(Sold To)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Customer Price Group(Sold To)" });
        }
    }

    /**
     * OnBlur_Setting_AcctNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_AcctNmSold(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_NUM, bizMsg.B.no(idx).prcRuleCondFromTxt_54, bizMsg.B.no(idx).dsAcctNm_54, "Accnt#(Sold To)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Accnt#(Sold To)" });
        }
    }

    /**
     * OnBlur_Setting_CoaChNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_CoaChNmSold(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_CHANNEL, bizMsg.B.no(idx).prcRuleCondFromTxt_55, bizMsg.B.no(idx).coaChDescTxt_55, "Customer Channel(Sold To)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Customer Channel(Sold To)" });
        }
    }

    /**
     * OnBlur_Setting_CoaChNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_DsAcctClsNameSold(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SOLD_TO_ACCT_CLASSIFICATION, bizMsg.B.no(idx).prcRuleCondFromTxt_56, bizMsg.B.no(idx).dsAcctClsDescTxt_56, "Customer Classification(Sold To)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Account Classification(Sold To)" });
        }
    }

    /**
     * OnBlur_Setting_BillTo Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_BillTo(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_NUM, bizMsg.B.no(idx).prcRuleCondFromTxt_16, bizMsg.B.no(idx).billToAcctNm_16, "Bill To (Acct#)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Bill To (Acct#)" });
        }
    }

    /**
     * OpenWin_BillToAcctChnl Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_BillToAcctChnl(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_CHANNEL, bizMsg.B.no(idx).prcRuleCondFromTxt_17, bizMsg.B.no(idx).coaChDescTxt_17, "Bill To Acct (Channel)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Bill To Acct (Channel)" });
        }
    }

    /**
     * OpenWin_BillToAcctClss Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_BillToAcctClss(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.BILL_TO_ACCT_CLASSIFICATION, bizMsg.B.no(idx).prcRuleCondFromTxt_18, bizMsg.B.no(idx).dsAcctClsDescTxt_18, "Bill To Acct (Classification)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Bill To Acct (Classification)" });
        }
    }

    /**
     * OpenWin_Branch Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_Branch(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.BRANCH, bizMsg.B.no(idx).prcRuleCondFromTxt_19, bizMsg.B.no(idx).coaBrDescTxt_19, "Branch")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Branch" });
        }
    }

    /**
     * OpenWin_CallType Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_CallType(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.CALL_TYPE, bizMsg.B.no(idx).prcRuleCondFromTxt_20, bizMsg.B.no(idx).svcCallTpDescTxt_20, "Call Type")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Call Type" });
        }
    }

    /**
     * OpenWin_LineCatg Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_LineCatg(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.LINE_CATEGORY_LINE_TYPE, bizMsg.B.no(idx).prcRuleCondFromTxt_25, bizMsg.B.no(idx).dsOrdLineCatgDescTxt_25, "Line Category (Line Type)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Line Category (Line Type)" });
        }
    }

    /**
     * OpenWin_MarketMdlNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_MarketMdlNm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.MARKETING_MODEL_NAME, bizMsg.B.no(idx).prcRuleCondFromTxt_27, bizMsg.B.no(idx).mktMdlDescTxt_27, "Marketing Model Name")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Marketing Model Name" });
        }
    }

    /**
     * OpenWin_ModelSeg Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ModelSeg(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.MODEL_SEGMENT, bizMsg.B.no(idx).prcRuleCondFromTxt_28, bizMsg.B.no(idx).mktMdlSegDescTxt_28, "Model Segment")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Model Segment" });
        }
    }

    /**
     * OpenWin_OrderSrc Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_OrderSrc(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.ORDER_SOURCE, bizMsg.B.no(idx).prcRuleCondFromTxt_29, bizMsg.B.no(idx).cpoSrcTpDescTxt_29, "Order Source")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Order Source" });
        }
    }

    /**
     * OpenWin_PaymentTp Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_PaymentTp(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.PAYMENT_TYPE, bizMsg.B.no(idx).prcRuleCondFromTxt_31, bizMsg.B.no(idx).dsPmtMethDescTxt_31, "Payment Type")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Payment Type" });
        }
    }

    /**
     * OpenWin_PrcList Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_PrcList(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.PRICE_LIST, bizMsg.B.no(idx).prcRuleCondFromTxt_32, bizMsg.B.no(idx).prcCatgNm_32, "Price List")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Price List" });
        }
    }

    /**
     * OpenWin_RtnRsnCd Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_RtnRsnCd(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.RETURN_REASON_CODE, bizMsg.B.no(idx).prcRuleCondFromTxt_38, bizMsg.B.no(idx).rtrnRsnDescTxt_38, "Return Reason Code")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Return Reason Code" });
        }
    }

    /**
     * OpenWin_ServiceLv Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ServiceLv(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SERVICE_LEVEL, bizMsg.B.no(idx).prcRuleCondFromTxt_39, bizMsg.B.no(idx).shpgSvcLvlDescTxt_39, "Service Level")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Service Level" });
        }
    }

    /**
     * OpenWin_ServiceMdl Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ServiceMdl(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SERVICE_MODEL, bizMsg.B.no(idx).prcRuleCondFromTxt_40, bizMsg.B.no(idx).mdlDescTxt_40, "Service Model")) { // 2017/08/24 QC#20729 Mod
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Service Model" });
        }
    }

    /**
     * OpenWin_ServiceZone Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ServiceZone(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SERVICE_ZONE, bizMsg.B.no(idx).prcRuleCondFromTxt_41, bizMsg.B.no(idx).prcSvcZoneDescTxt_41, "Service Zone")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Service Zone" });
        }
    }

    /**
     * OpenWin_ShipToAcct Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_ShipToAcct(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SHIP_TO_ACCT_CLASSIFICATION, bizMsg.B.no(idx).prcRuleCondFromTxt_42, bizMsg.B.no(idx).dsAcctClsDescTxt_42, "Ship To Acct (Classification)")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Ship To Acct (Classification)" });
        }
    }

    /**
     * OpenWin_SpecialHandTp Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_SpecialHandTp(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.SPECIAL_HANDLING_TYPE, bizMsg.B.no(idx).prcRuleCondFromTxt_44, bizMsg.B.no(idx).spclHdlgTpDescTxt_44, "Special Handling Type")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Special Handling Type" });
        }
    }

    /**
     * OpenWin_BizUnit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_BizUnit(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.BUSINESS_UNIT, bizMsg.B.no(idx).prcRuleCondFromTxt_46, bizMsg.B.no(idx).coaExtnDescTxt_46, "Business Unit")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Business Unit" });
        }
    }

    /**
     * OpenWin_FreightTerm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_FreightTerm(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.FREIGHT_TERM, bizMsg.B.no(idx).prcRuleCondFromTxt_48, bizMsg.B.no(idx).frtCondDescTxt_48, "Freight Term")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Freight Term" });
        }
    }

    /**
     * OpenWin_FreightZone Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_FreightZone(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.FREIGHT_ZONE, bizMsg.B.no(idx).prcRuleCondFromTxt_49, bizMsg.B.no(idx).fill40Txt_49, "Freight Zone")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Freight Zone" });
        }
    }

    // 2018/04/20 QC#22569 add start
    /**
     * OnBlur_Setting_SlsMatGrpDescTxt1 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt1(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_1, bizMsg.B.no(idx).prcRuleCondFromTxt_59, bizMsg.B.no(idx).slsMatGrpDescTxt_59, "Material Group 1")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Material Group 1" });
        }
    }

    /**
     * OnBlur_Setting_SlsMatGrpDescTxt2 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt2(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_2, bizMsg.B.no(idx).prcRuleCondFromTxt_60, bizMsg.B.no(idx).slsMatGrpDescTxt_60, "Material Group 2")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Material Group 2" });
        }
    }

    /**
     * OnBlur_Setting_SlsMatGrpDescTxt3 Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260Scrn00_OnBlur_Setting_SlsMatGrpDescTxt3(NMAL7260CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (!NMAL7260CommonLogic.getMstData(PRC_RULE_ATTRB.MATERIAL_GROUP_3, bizMsg.B.no(idx).prcRuleCondFromTxt_61, bizMsg.B.no(idx).slsMatGrpDescTxt_61, "Material Group 3")) {
            bizMsg.setMessageInfo(NMAM8186E, new String[] {"Material Group 3" });
        }
    }
    // 2018/04/20 QC#22569 add end

    // 2018/07/17 QC#20267 Mod Start
    /**
     * OnBlur_Setting_ItemNm Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7260_NMAL6800(NMAL7260CMsg bizMsg) {
         doProcess_NMAL7260Scrn00_OnBlur_Setting_ItemNm(bizMsg);
    }
    // 2018/07/17 QC#20267 Mod End

    /**
     * Show Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_Show(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        NMAL7260CommonLogic.setColumnTableData(bizMsg);
        ZYPTableUtil.clear(bizMsg.T);
        ZYPTableUtil.clear(glblMsg.T);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, NMAL7260Constant.TAB_ADJ_TBL_DATA);     //QC#22593 add

    }

    private void doProcess_NMAL7260Scrn00_Upload_CSV(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        // get validCount
        int curIdx = glblMsg.B.getValidCount();
        bizMsg.xxRowNum.clear();
        // upload data create
        List<List<String>> upLoadDataList = new ArrayList<List<String>>();
        if (NMAL7260CommonLogic.createUploadData(bizMsg, upLoadDataList)) {
            bizMsg.setMessageInfo(NMAM8329E, new String[] {bizMsg.xxUpldCsvFilePathTxt.getValue() });
            return;
        }

        if (NMAL7260CommonLogic.checkUploadItemCnt(bizMsg, upLoadDataList)) {
            bizMsg.setMessageInfo(NMAM8321E);
            return;
        }

        EZDSchemaInfo ezdSchemaInfo = new NMAL7260_BCMsg().getSchema();
        String[] chkDataItem = null;
        String cntStr = bizMsg.xxComnColOrdTxt.getValue();
        if (TABLE_ITEM_DEF.equals(cntStr)) {
            chkDataItem = new String[2];
            chkDataItem[0] = TABLE_ITEM_EFFECTIVE_DATE_FROM;
            chkDataItem[1] = TABLE_ITEM_EFFECTIVE_DATE_TO;
        } else {
            chkDataItem = cntStr.split(CSV_DELIMITER);
        }

        int afterDataSize = glblMsg.B.getValidCount() + upLoadDataList.size();
        if (afterDataSize > glblMsg.B.length()) {
            StringBuilder sbMsg = new StringBuilder();
            sbMsg.append(glblMsg.B.length());
            bizMsg.setMessageInfo(ZZZM9011E, new String[] {sbMsg.toString() });
            return;
        }
        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        boolean errFlg = false;
        for (int i = 0; i < upLoadDataList.size(); i++) {
            List<String> upLoadData = upLoadDataList.get(i);
            // 1Item
            int j = 1;
            // Mod Start 2018/12/04 QC#29321
            //int k = 21;
            //while (j < chkDataItem.length && k <= upLoadData.size()) {
            int k = 1;
            while (j < chkDataItem.length && k < upLoadData.size()) {
                // Mod End 2018/12/04 QC#29321
                String data = upLoadData.get(k);
                if (TABLE_ITEM_CSMP.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_04").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_04, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_04.setErrorInfo(1, NMAM8328E, new String[] {"CSMP#", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_MATERIAL_PRICE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_05").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_05, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_05.setErrorInfo(1, NMAM8328E, new String[] {"Material Price Group", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PROD_CTRL_1.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_06").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_06, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_06.setErrorInfo(1, NMAM8328E, new String[] {"Prod Level 1- Business Unit", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PROD_CTRL_2.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_07").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_07, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_07.setErrorInfo(1, NMAM8328E, new String[] {"Prod Level 2- Prod Group", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_MDSE_TYPE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_08").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_08, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_08.setErrorInfo(1, NMAM8328E, new String[] {"Mdse Type", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PRODUCT_CD.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_09").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_09, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_09.setErrorInfo(1, NMAM8328E, new String[] {"Product Code", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ITEM_CD.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_10").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_10, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_10.setErrorInfo(1, NMAM8328E, new String[] {"Item Code", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ORDER_CATEGORY.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_11").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_11, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_11.setErrorInfo(1, NMAM8328E, new String[] {"Order Categor", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ORDER_REASON.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_12").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_12, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_12.setErrorInfo(1, NMAM8328E, new String[] {"Order Type", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ORDER_LINE_OF_BUSINESS.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_13").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_13, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_13.setErrorInfo(1, NMAM8328E, new String[] {"Order Line of Business", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_TRANSACTION_GROUP.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_14").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_14, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_14.setErrorInfo(1, NMAM8328E, new String[] {"Transaction Group", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_TRANSACTION_WEIGHT.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_15").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_15, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_15.setErrorInfo(1, NMAM8328E, new String[] {"Total Transaction Weight", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_15").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_15.setErrorInfo(1, NMAM8328E, new String[] {"Total Transaction Weight", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_15, data);
                        }
                    }
                } else if (TABLE_ITEM_BILL_TO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_16").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_16, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_16.setErrorInfo(1, NMAM8328E, new String[] {"Bill To (Acct#)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_BILL_TO_ACCT_CHANNEL.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_17").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_17, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_17.setErrorInfo(1, NMAM8328E, new String[] {"Bill To Acct (Channel)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_BILL_TO_ACCT_CLASSIFICATION.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_18").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_18, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_18.setErrorInfo(1, NMAM8328E, new String[] {"Bill To Acct (Classification)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_BRANCH.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_19").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_19, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_19.setErrorInfo(1, NMAM8328E, new String[] {"Branch", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_CALL_TYPE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_20").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_20, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_20.setErrorInfo(1, NMAM8328E, new String[] {"Call Type", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_CALL_DATE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("xxSvcCallDt_FR").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).xxSvcCallDt_FR, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).xxSvcCallDt_FR.setErrorInfo(1, NMAM8328E, new String[] {"Call Date From", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("xxSvcCallDt_TH").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).xxSvcCallDt_TH, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).xxSvcCallDt_TH.setErrorInfo(1, NMAM8328E, new String[] {"Call Date To", tmpData });
                            errFlg = true;
                        }
                    }
                } else if (TABLE_ITEM_CUSTOMER_PO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_22").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_22, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_22.setErrorInfo(1, NMAM8328E, new String[] {"Customer PO", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_22").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_22.setErrorInfo(1, NMAM8328E, new String[] {"Customer PO", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_22, data);
                        }
                    }
                } else if (TABLE_ITEM_LINE_AMOUNT.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_24").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_24, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_24.setErrorInfo(1, NMAM8328E, new String[] {"Line Amount", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_24").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_24.setErrorInfo(1, NMAM8328E, new String[] {"Line Amount", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_24, data);
                        }
                    }
                } else if (TABLE_ITEM_LINE_CATEGORY_LINE_TYPE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_25").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_25, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_25.setErrorInfo(1, NMAM8328E, new String[] {"Line Category (Line Type)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_LINE_QTY.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_26").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_26, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_26.setErrorInfo(1, NMAM8328E, new String[] {"Line Qty", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_26").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_26.setErrorInfo(1, NMAM8328E, new String[] {"Line Qty", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_26, data);
                        }
                    }
                } else if (TABLE_ITEM_MARKETING_MODEL_NAME.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_27").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_27, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_27.setErrorInfo(1, NMAM8328E, new String[] {"Marketing Model Name", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_MODEL_SEGMENT.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_28").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_28, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_28.setErrorInfo(1, NMAM8328E, new String[] {"Model Segment", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ORDER_SOURCE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_29").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_29, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_29.setErrorInfo(1, NMAM8328E, new String[] {"Order Source", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ORDER_SUBTOTAL.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_30").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_30, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_30.setErrorInfo(1, NMAM8328E, new String[] {"Order Subtotal", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_30").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_30.setErrorInfo(1, NMAM8328E, new String[] {"Order Subtotal", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_30, data);
                        }
                    }
                } else if (TABLE_ITEM_PAYMENT_TYPE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        // Mod Start 2018/12/04 QC#29321
                        //if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_EF").getDigit())) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_31").getDigit())) {
                            // Mod End 2018/12/04 QC#29321
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_31, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_31.setErrorInfo(1, NMAM8328E, new String[] {"Payment Type", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PRICE_LIST.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_32").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_32, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_32.setErrorInfo(1, NMAM8328E, new String[] {"Price List", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PRICING_DATE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcDt_FR").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcDt_FR, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcDt_FR.setErrorInfo(1, NMAM8328E, new String[] {"Pricing Date From", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcDt_TH").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcDt_TH, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcDt_TH.setErrorInfo(1, NMAM8328E, new String[] {"Pricing Date To", tmpData });
                            errFlg = true;
                        }
                    }
                } else if (TABLE_ITEM_REQUEST_DATE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("xxRqstDt_FR").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).xxRqstDt_FR, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).xxRqstDt_FR.setErrorInfo(1, NMAM8328E, new String[] {"Request Date From", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("xxRqstDt_TH").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).xxRqstDt_TH, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).xxRqstDt_TH.setErrorInfo(1, NMAM8328E, new String[] {"Request Date To", tmpData });
                            errFlg = true;
                        }
                    }
                } else if (TABLE_ITEM_PROD_CTRL_3.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_34").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_34, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_34.setErrorInfo(1, NMAM8328E, new String[] {"Prod Level 3- Prod Family", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PROD_CTRL_4.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_35").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_35, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_35.setErrorInfo(1, NMAM8328E, new String[] {"Prod Level 4- Prod Line", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_PROD_CTRL_5.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_36").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_36, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_36.setErrorInfo(1, NMAM8328E, new String[] {"Prod Level 5- Prod Series", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_RETURN_REASON_CODE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_38").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_38, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_38.setErrorInfo(1, NMAM8328E, new String[] {"Return Reason Code", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_SERVICE_LEVEL.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_39").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_39, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_39.setErrorInfo(1, NMAM8328E, new String[] {"Service Level", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_SERVICE_MODEL.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_40").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_40, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_40.setErrorInfo(1, NMAM8328E, new String[] {"Service Model", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_SERVICE_ZONE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_41").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_41, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_41.setErrorInfo(1, NMAM8328E, new String[] {"Service Zone", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_SHIP_TO_ACCT_CLASSIFICATION.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_42").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_42, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_42.setErrorInfo(1, NMAM8328E, new String[] {"Ship To Acct (Classification)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_SPECIAL_HANDLING_TYPE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_44").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_44, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_44.setErrorInfo(1, NMAM8328E, new String[] {"Special Handling Type", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_TOTAL_QTY.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_45").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_45, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_45.setErrorInfo(1, NMAM8328E, new String[] {"Total Qty", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_45").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_45.setErrorInfo(1, NMAM8328E, new String[] {"Total Qty", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_45, data);
                        }
                    }
                } else if (TABLE_ITEM_BUSINESS_UNIT.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_46").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_46, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_46.setErrorInfo(1, NMAM8328E, new String[] {"Business Unit", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_FREIGHT_TERM.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_48").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_48, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_48.setErrorInfo(1, NMAM8328E, new String[] {"Freight Term", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_FREIGHT_ZONE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_49").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_49, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_49.setErrorInfo(1, NMAM8328E, new String[] {"Freight Zone", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_CUSTOMER_PRICE_GROUP_SOLD_TO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_53").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_53, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_53.setErrorInfo(1, NMAM8328E, new String[] {"Customer Price Group(Sold To)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_ACCNT_SOLD_TO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_54").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_54, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_54.setErrorInfo(1, NMAM8328E, new String[] {"Sold To (Acct#)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_CUSTOMER_CHANNEL_SOLD_TO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_55").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_55, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_55.setErrorInfo(1, NMAM8328E, new String[] {"Sold To Acct (Channel)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_CUSTOMER_CLS_SOLD_TO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_56").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_56.setErrorInfo(1, NMAM8328E, new String[] {"Sold To Acct (Classification)", data });
                            errFlg = true;
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_56, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_56.setErrorInfo(1, NMAM8328E, new String[] {"Sold To Acct (Classification)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_MATERIAL_PRICE_QTYBRK.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_57").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_57.setErrorInfo(1, NMAM8328E, new String[] {"Material Price Group(Qty Break)", data });
                            errFlg = true;
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_57, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_57.setErrorInfo(1, NMAM8328E, new String[] {"Material Price Group(Qty Break)", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_LINE_QTY_QTYBRK.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_58").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_58, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_58.setErrorInfo(1, NMAM8328E, new String[] {"Line Qty(Qty Break)", tmpData });
                            errFlg = true;
                        }
                    }
                    k++;
                    data = upLoadData.get(k);
                    if (ZYPCommonFunc.hasValue(data)) {
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondThruTxt_58").getDigit())) {
                            glblMsg.B.no(curIdx).prcRuleCondThruTxt_58.setErrorInfo(1, NMAM8328E, new String[] {"Line Qty(Qty Break)", data });
                            errFlg = true;
                            data = "";
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondThruTxt_58, data);
                        }
                    }
                // 2018/04/20 QC#22569 add start
                } else if (TABLE_ITEM_MATERIAL_GRP_1.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_59").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_59, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_59.setErrorInfo(1, NMAM8328E, new String[] {"Material Group 1", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_MATERIAL_GRP_2.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_60").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_60, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_60.setErrorInfo(1, NMAM8328E, new String[] {"Material Group 2", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                } else if (TABLE_ITEM_MATERIAL_GRP_3.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleCondFromTxt_61").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleCondFromTxt_61, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleCondFromTxt_61.setErrorInfo(1, NMAM8328E, new String[] {"Material Group 3", tmpData });
                            errFlg = true;
                        }
                    }
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                // 2018/04/20 QC#22569 add end
                } else if (TABLE_ITEM_FORMULA.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcFmlaPk_B1").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeBigDecimal(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcFmlaPk_B1, new BigDecimal(data));
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcFmlaPk_B1.setErrorInfo(1, NMAM8328E, new String[] {"Formula", tmpData });
                            errFlg = true;
                        }
                    }
                    // 2018/06/06 QC#26491 Add Start
                    // Del Start 2018/12/04 QC#29321
                    //k++;
                    // Del End 2018/12/04 QC#29321
                    // 2018/06/06 QC#26491 Add End
                } else if (TABLE_ITEM_PERCENT.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleDtlRate_B1").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeBigDecimal(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleDtlRate_B1, new BigDecimal(data));
                        // 2018/06/06 QC#26491 Del Start
                        // j++;
                        // 2018/06/06 QC#26491 Del End
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleDtlRate_B1.setErrorInfo(1, NMAM8328E, new String[] {"Percent", tmpData });
                            errFlg = true;
                        }
                    }
                } else if (TABLE_ITEM_VALUE.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;
                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("prcRuleDtlTxt_B1").getDigit())) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).prcRuleDtlTxt_B1, data);
                       // j++;
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).prcRuleDtlTxt_B1.setErrorInfo(1, NMAM8328E, new String[] {"Value", tmpData });
                            errFlg = true;
                        }
                    }
                } else if (TABLE_ITEM_EFFECTIVE_DATE_FROM.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("effFromDt_B1").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).effFromDt_B1, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).effFromDt_B1.setErrorInfo(1, NMAM8328E, new String[] {"Effective Date From", tmpData });
                            errFlg = true;
                        }
                    }
                } else if (TABLE_ITEM_EFFECTIVE_DATE_TO.equals(chkDataItem[j])) {
                    if (ZYPCommonFunc.hasValue(data)) {
                        String tmpData = data;

                        // 2019/03/06 QC#30643 add start
                        data = NMAL7260CommonLogic.formatDateStr(data);
                        // 2019/03/06 QC#30643 add end
                        // for MM/DD/YYYY
                        if (data.contains(SLASH)) {
                            data = data.replaceAll(SLASH, "");
                            data = ZYPDateUtil.formatDisp8ToEzd(data);
                        }

                        if (NMAL7260CommonLogic.checkItemCnt(data, ezdSchemaInfo.getAttr("effThruDt_B1").getDigit())) {
                            data = "";
                        } else if (NMAL7260CommonLogic.checkItemTypeDate(data)) {
                            data = "";
                        }
                        ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).effThruDt_B1, data);
                        if (!ZYPCommonFunc.hasValue(data)) {
                            glblMsg.B.no(curIdx).effThruDt_B1.setErrorInfo(1, NMAM8328E, new String[] {"Effective Date To", tmpData });
                            errFlg = true;
                        }
                    }
                }
                // Mod Start 2019/01/08 QC#29751
                //if (!TABLE_ITEM_QTYDISC.equals(chkDataItem[j])) {
                if (!TABLE_ITEM_QTYDISC.equals(chkDataItem[j]) && !TABLE_ITEM_MNF_ITEM_CD.equals(chkDataItem[j])) {
                    // Mod End 2019/01/08 QC#29751
                    k++;
                }
                j++;
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).xxRowId_B1, ZYPConstant.FLG_ON_Y);
            if (errFlg && !ZYPCommonFunc.hasValue(bizMsg.xxRowNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.valueOf(curIdx));
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.B.no(curIdx).xxLinkAncr_B1, ZYPConstant.FLG_OFF_N);
            curIdx++;
        }

        // set vallidCount
        glblMsg.B.setValidCount(curIdx);
        int pageIndex = -1;
        if (ZYPCommonFunc.hasValue(bizMsg.xxRowNum)) {
            pageIndex = bizMsg.xxRowNum.getValueInt();
        } else {
            pageIndex = curIdx - 1;
        }
        //QC#22593 add Start
        if (errFlg) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1,  NMAL7260Constant.TAB_ADJ_TBL_DATA);
        }
        //QC#22593 add End
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, pageIndex);
        bizMsg.setCommitSMsg(true);
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260_INIT(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        glblMsg.D.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode()); // QC#9694 2016/06/28 Add 
        // Create Pull Down.
        createPullDown(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, NMAL7260Constant.TAB_ADJ_TBL_DFN);     //QC#22593 add

        if (ZYPCommonFunc.hasValue(bizMsg.prcRuleHdrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleHdrPk_H1, bizMsg.prcRuleHdrPk_BK);
            doProcess_NMAL7260Scrn00_Search(bizMsg, glblMsg);
        } else {
            // Initial Data Setup
            S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getDefRulePrcdNumData(bizMsg.prcRuleCatgCd_L1.no(0).getValue());
            String defRulePrcdNum = (String) ssmResult.getResultObject();
            if (ZYPCommonFunc.hasValue(defRulePrcdNum)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.defRulePrcdNum_H1, new BigDecimal(defRulePrcdNum));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.applyAutoFlg_H1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.ovrdFlg_H1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_H1, ZYPConstant.FLG_ON_Y);

            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

            ZYPTableUtil.clear(bizMsg.B);
            ZYPTableUtil.clear(bizMsg.C);
            ZYPTableUtil.clear(glblMsg.B);
            ZYPTableUtil.clear(bizMsg.D);       // QC#20968 add
            ZYPTableUtil.clear(bizMsg.T);
            ZYPTableUtil.clear(glblMsg.T);
            bizMsg.xxRowNum.clear();

            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.valueOf(0));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum, BigDecimal.valueOf(0));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowOfNum, BigDecimal.valueOf(0));

            NMAL7260CommonLogic.setColumnTableData(bizMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_CMN_Clear(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        bizMsg.prcRuleHdrPk_BK.clear();
        bizMsg.prcRuleHdrPk_H1.clear();
        bizMsg.prcRuleNm_H1.clear();
        bizMsg.effThruDt_H1.clear();
        bizMsg.lineBizTpCd_H1.clear();
        bizMsg.prcRuleCatgCd_H1.clear();
        bizMsg.prcRuleAdjLvlCd_H1.clear();
        bizMsg.prcRuleModTpCd_H1.clear();
        bizMsg.prcRuleAdjTpCd_H1.clear();
        bizMsg.prcRulePrcdPk_H1.clear();
        bizMsg.prcRulePrcdGrpNm_H1.clear();
        bizMsg.xxFullNm_H1.clear();
        bizMsg.cratDt_H1.clear();
        bizMsg.xxFullNm_H2.clear();
        bizMsg.lastUpdDt_H1.clear();
        bizMsg.prcRuleHdrDescTxt_H1.clear();
        bizMsg.xxFldValTxt_H1.clear();
        bizMsg.xxRowNum.clear();
        bizMsg.xxPageShowFromNum.clear();
        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        doProcess_NMAL7260_INIT(bizMsg, glblMsg);

    }

    /**
     * Page Next Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_PageNext(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        int page = bizMsg.xxPageShowToNum.getValueInt() + 1;
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, page);

    }

    /**
     * Page Previous Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_PagePrev(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {

        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        // copy data from glblMsg onto bizMsg
        int page = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.B.length();
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, page);

    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global Component Interface Message
     */
    public void search(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg, boolean afterSave) {

        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(glblMsg.B);
        ZYPTableUtil.clear(bizMsg.T);
        ZYPTableUtil.clear(glblMsg.T);
        ZYPTableUtil.clear(bizMsg.C);
        bizMsg.xxRowNum.clear();

        // Head Search
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().searchHead(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        } else {
            ssmResult = NMAL7260Query.getInstance().searchTblDefData(bizMsg);

            if (!ssmResult.isCodeNotFound()) {
                // Table Definition Search
                ssmResult = NMAL7260Query.getInstance().searchTblData(bizMsg, glblMsg);
                NMAL7260CommonLogic.createTableData(bizMsg, glblMsg, ssmResult);
                NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, 0);
                String prcRuleAttrbCd = NMAL7260CommonLogic.judgeQtyBreak(bizMsg);
                ssmResult = NMAL7260Query.getInstance().searchTblDataQtyBrk(bizMsg, glblMsg, prcRuleAttrbCd);
                NMAL7260CommonLogic.createTableDataQtyBrk(bizMsg, glblMsg, ssmResult);
            }
        }

        NMAL7260CommonLogic.setColumnTableData(bizMsg);
        bizMsg.setCommitSMsg(true);

        BigDecimal count = NMAL7260Query.getInstance().searchTblDataCount(bizMsg, glblMsg);
        // Mod Start 2018/06/18 QC#22594
        // if (count.compareTo(new BigDecimal(glblMsg.B.length())) > 0) {
        if (!afterSave) {
            if (count.compareTo(new BigDecimal(GLBLMSG_MAX_LENGTH)) > 0) {
        // Mod End 2018/06/18 QC#22594
                bizMsg.setMessageInfo(NZZM0001W);
                return;
            }
        }
        if (NMAL7260CommonLogic.checkPrcPrcdGrp(bizMsg)) {
            bizMsg.prcRulePrcdGrpNm_H1.setErrorInfo(2, NMAL7260Constant.NMAM8627W);
            return;
        }
        bizMsg.setMessageInfo(NZZM0002I);

//        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, NMAL7260Constant.TAB_ADJ_TBL_DATA);     //QC#22593 add
    }

    private void createPullDown(NMAL7260CMsg bizMsg) {
        // Pull down
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_L1, bizMsg.lineBizTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_CATG.class, bizMsg.prcRuleCatgCd_L1, bizMsg.prcRuleCatgDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_ADJ_LVL.class, bizMsg.prcRuleAdjLvlCd_L1, bizMsg.prcRuleAdjLvlDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_MOD_TP.class, bizMsg.prcRuleModTpCd_L1, bizMsg.prcRuleModTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_RULE_ADJ_TP.class, bizMsg.prcRuleAdjTpCd_L1, bizMsg.prcRuleAdjTpDescTxt_L1);
        // 2018/09/12 QC#9700 add start
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TRX_TP.class, bizMsg.prcGrpTrxTpCd_L1, bizMsg.prcGrpTrxTpDescTxt_L1);
        // 2018/09/12 QC#9700 add end
        // Add Start 2018/12/04 QC#29321
        ZYPCodeDataUtil.createPulldownList(PRC_DISP_REC_TP.class, bizMsg.prcDispRecTpCd_L1, bizMsg.prcDispRecTpDescTxt_L1);
        ZYPEZDItemValueSetter.setValue(bizMsg.prcDispRecTpCd_H1, PRC_DISP_REC_TP.ACTIVE_ONLY);
        // Add End 2018/12/04 QC#29321

        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        S21SsmEZDResult ssmResult = NMAL7260Query.getInstance().getPriceRuleAttributeList(getGlobalCompanyCode(), ZYPConstant.FLG_ON_Y);
        if (ssmResult.isCodeNormal()) {
            listMap = (List<Map<String, String>>) ssmResult.getResultObject();
        }
        String prcRuleAttrbCdData = "";
        String inpObjTpDescTxtData = "";
        String modFldFlgData = "";
        for (int i = 0; i < listMap.size(); i++) {
            String prcRuleAttrbCd = (String) listMap.get(i).get("PRC_RULE_ATTRB_CD");
            String prcRuleAttrbDesctxt = (String) listMap.get(i).get("PRC_RULE_ATTRB_DESC_TXT");

            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbCd_LA.no(i), prcRuleAttrbCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.prcRuleAttrbDescTxt_LA.no(i), prcRuleAttrbDesctxt);

            String modFldFlg = (String) listMap.get(i).get("MOD_FLD_FLG");
            String inpObjTpDescTxt = (String) listMap.get(i).get("INP_OBJ_TP_DESC_TXT");

            NMAL7260CommonLogic.setTableDataHeader(bizMsg, prcRuleAttrbCd, prcRuleAttrbDesctxt);
            prcRuleAttrbCdData = NMAL7260CommonLogic.createSaveData(prcRuleAttrbCdData, prcRuleAttrbCd);
            inpObjTpDescTxtData = NMAL7260CommonLogic.createSaveData(inpObjTpDescTxtData, inpObjTpDescTxt);
            modFldFlgData = NMAL7260CommonLogic.createSaveData(modFldFlgData, modFldFlg);

        }

        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(0).prcRuleAttrbCd_C1, bizMsg.prcRuleAttrbCd_LA.no(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.C.no(0).inpObjTpDescTxt_C1, (String) listMap.get(0).get("INP_OBJ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_H1, prcRuleAttrbCdData);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_H2, inpObjTpDescTxtData);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_H3, modFldFlgData);
    }

    // Add Start S21_NA QC#18237(Sol#161) 
    /**
     * execute column sort function
     * @param cMsg NMAL7260CMsg
     * @param sMsg NMAL7260SMsg
     */
    private void doProcess_NMAL7260Scrn00_TBLColumnSort(NMAL7260CMsg cMsg, NMAL7260SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:B
        if ("B".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.B.getValidCount());
        }
    }
    // Add END S21_NA QC#18237(Sol#161) 
    // QC#20968 add Start
    /**
     * NMAL7260_NMAL7420 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260_NMAL7420(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
     // Mod Start 2018/06/18 QC#22594
//        search(bizMsg, glblMsg);
        search(bizMsg, glblMsg, false);
     // Mod End 2018/06/18 QC#22594
     // Add Start 2018/06/25 QC#26100
        EZDMsg.copy(bizMsg.D.no(0), null, glblMsg.D.no(0), null);
     // Add Start 2018/06/25 QC#26100
    }

    // QC#20968 add End

    private void doProcess_NMAL7260Scrn00_OpenWin_QtyBreak(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        ZYPTableUtil.clear(bizMsg.T);
        int idx = bizMsg.xxCellIdx.getValueInt();
        BigDecimal prcAdjDtlPk = bizMsg.B.no(idx).prcAdjDtlPk_B1.getValue();
        List<Integer> list = NMAL7260CommonLogic.getQtyDiscountData(glblMsg, prcAdjDtlPk);

        int j = 0;
        for (Integer i : list) {
            EZDMsg.copy(glblMsg.T.no(i), null, bizMsg.T.no(j), null);
            ZYPEZDItemValueSetter.setValue(bizMsg.T.no(j).ezUpTime_T1, glblMsg.T.no(i).ezUpTime_T1);
            ZYPEZDItemValueSetter.setValue(bizMsg.T.no(j).ezUpTimeZone_T1, glblMsg.T.no(i).ezUpTimeZone_T1);
            ZYPEZDItemValueSetter.setValue(bizMsg.T.no(j).ezUpTime_T3, glblMsg.T.no(i).ezUpTime_T3);
            ZYPEZDItemValueSetter.setValue(bizMsg.T.no(j).ezUpTimeZone_T3, glblMsg.T.no(i).ezUpTimeZone_T3);
            j++;
        }
        bizMsg.T.setValidCount(j);
    }

    private void doProcess_NMAL7260_NMAL7300(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        BigDecimal prcAdjDtlPk = bizMsg.B.no(idx).prcAdjDtlPk_B1.getValue();
        int cSize = bizMsg.T.getValidCount();
        int sSize = glblMsg.T.getValidCount();
        List<Integer> list = NMAL7260CommonLogic.getQtyDiscountData(glblMsg, prcAdjDtlPk);
        if (sSize - list.size() + cSize > glblMsg.T.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(glblMsg.T.length()) });
            return;
        }
        Map<String, NMAL7260_TSMsg> pool = new HashMap<String, NMAL7260_TSMsg>();
        for (Integer i : list) {
            if (ZYPCommonFunc.hasValue(glblMsg.T.no(i).prcRuleDtlPk_T.getValue())) {
                pool.put(glblMsg.T.no(i).prcRuleDtlPk_T.getValue().toString(), (NMAL7260_TSMsg) glblMsg.T.no(i).clone());
            }
        }
        if (list.size() > 0) {
            ZYPTableUtil.deleteRows(glblMsg.T, list);
        }
        int j = glblMsg.T.getValidCount();
        NMAL7260_TSMsg oldRow = null;
        int count = 0;
        for (int i = 0; i < bizMsg.T.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.T.no(i), null, glblMsg.T.no(j), null);
            ZYPEZDItemValueSetter.setValue(glblMsg.T.no(j).ezUpTime_T1, bizMsg.T.no(i).ezUpTime_T1);
            ZYPEZDItemValueSetter.setValue(glblMsg.T.no(j).ezUpTimeZone_T1, bizMsg.T.no(i).ezUpTimeZone_T1);
            ZYPEZDItemValueSetter.setValue(glblMsg.T.no(j).ezUpTime_T3, bizMsg.T.no(i).ezUpTime_T3);
            ZYPEZDItemValueSetter.setValue(glblMsg.T.no(j).ezUpTimeZone_T3, bizMsg.T.no(i).ezUpTimeZone_T3);
            ZYPEZDItemValueSetter.setValue(glblMsg.T.no(j).prntPrcAdjDtlPk_T, prcAdjDtlPk);
            if (!ZYPCommonFunc.hasValue(glblMsg.T.no(j).prcRuleDtlPk_T)) {
                glblMsg.T.no(j).xxModeCd_T.setValue(NMAL7260Constant.MODE_NEW);
            } else {
                oldRow = pool.get(glblMsg.T.no(i).prcRuleDtlPk_T.getValue().toString());
                if (oldRow == null) {
                    glblMsg.T.no(j).xxModeCd_T.setValue(NMAL7260Constant.MODE_NEW);
                } else if (isChanged(glblMsg.T.no(j), oldRow)) {
                    glblMsg.T.no(j).xxModeCd_T.setValue(NMAL7260Constant.MODE_MODIFY);
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.T.no(j).xxModeCd_T, oldRow.xxModeCd_T);
                }
            }
            if (!ZYPConstant.FLG_ON_Y.equals(glblMsg.T.no(j).delFlg_T.getValue())) {
                count++;
            }
            j++;
        }
        glblMsg.T.setValidCount(j);
        if (count > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).xxLinkAncr_B1, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).xxLinkAncr_B1, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(idx).xxRowId_B1, ZYPConstant.FLG_ON_Y);
    }

    // Add Start 2018/12/04 QC#29321
    /**
     * doProcess_NMAL7260Scrn00_OnChange_PrcDispRecTp
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_OnChange_PrcDispRecTp(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        EZDMsg.copy(glblMsg.D.no(0), null, bizMsg.D.no(0), null);
        search(bizMsg, glblMsg, false);
    }

    /**
     * doProcess_NMAL7260Scrn00_SelectAll
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_SelectAll(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7260CommonLogic.selectUnselect(glblMsg, ZYPConstant.FLG_ON_Y);

        int pageIndex = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, pageIndex);
    }

    /**
     * doProcess_NMAL7260Scrn00_UnSelectAll
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_UnSelectAll(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);
        NMAL7260CommonLogic.selectUnselect(glblMsg, ZYPConstant.FLG_OFF_N);

        int pageIndex = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, pageIndex);
    }
    // Add End 2018/12/04 QC#29321

    // Add Start 2018/12/18 QC#29661
    /**
     * doProcess_NMAL7260Scrn00_MassUpd_PrcAdjTbl
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7260Scrn00_MassUpd_PrcAdjTbl(NMAL7260CMsg bizMsg, NMAL7260SMsg glblMsg) {
        NMAL7260CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(glblMsg.B, NMAL7260Constant.CHK_B, ZYPConstant.FLG_ON_Y);

        if (selectRows.isEmpty()) {
            bizMsg.setMessageInfo(NMAM8054E);
            return;
        }

        for (int idx : selectRows) {
            if (ZYPDateUtil.compare(glblMsg.B.no(idx).effThruDt_B1.getValue(), bizMsg.effThruDt_MU.getValue()) != 0) {
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).effThruDt_B1, bizMsg.effThruDt_MU);
                ZYPEZDItemValueSetter.setValue(glblMsg.B.no(idx).xxRowId_B1, ZYPConstant.FLG_ON_Y);
            }
        }

        int pageIndex = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL7260CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.B, pageIndex);
    }
    // Add End 2018/12/18 QC#29661

    private boolean isChanged(NMAL7260_TSMsg newData, NMAL7260_TSMsg oldData) {
        if(oldData == null){
            return false;
        }
        if(ZYPConstant.FLG_ON_Y.equals(newData.delFlg_T.getValue())){
            return true;
        }
        if(!isEqual(newData.qtyDiscDtlQty_T.getValue(), oldData.qtyDiscDtlQty_T.getValue())){
            return true;
        } else if(!isEqual(newData.prcRuleDtlRate_T.getValue(), oldData.prcRuleDtlRate_T.getValue())){
            return true;
        } else if(!isEqual(newData.prcRuleDtlTxt_T.getValue(), oldData.prcRuleDtlTxt_T.getValue())){
            return true;
        }
        return false;
    }

    private boolean isEqual(BigDecimal val1, BigDecimal val2) {

        if (val1 != null && val2 != null) {
            return val1.compareTo(val2) == 0;
        } else if (val1 == null && val2 != null) {
            return false;
        } else if (val1 != null && val2 == null) {
            return false;
        }
        return true;
    }

    private boolean isEqual(String val1, String val2) {
        
        if (val1 != null && val2 != null) {
            return val1.equals(val2);
        } else if (val1 == null && val2 != null) {
            return false;
        } else if (val1 != null && val2 == null) {
            return false;
        }
        return true;
    }
}
