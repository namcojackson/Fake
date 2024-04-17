/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200.common;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.*;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_ADDR_MASS_APLY_RMA;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_ADD_LINE_DETAIL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_APPROVAL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_BUYOUT;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_APL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_APR;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_RST;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CMN_SUB;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CONFIG_CHECK_ALL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_CONFIG_UNCHECK_ALL;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_DELIVERY_INFO;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_IMPORT_ATTRIBUTE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_LINE_ALL_COLLAPSED;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_LINE_ALL_EXPAND;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_PRICE_CHANGE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_PROFITABILITY;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_SALES_CREDIT;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_SEARCH;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_SERVICE_PRICING;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_SOM;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_SPECIAL_INSTRUCTION;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.BTN_VALIDATE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.FUNC_ID_READ;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.FUNC_ID_UPDATE;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0750W;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_ADDL_HEADER;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_ERRORS;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_HEADER;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_RMA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;

import business.servlet.NWAL2200.NWAL2200BMsg;
import business.servlet.NWAL2200.NWAL2200_ABMsg;
import business.servlet.NWAL2200.NWAL2200_BBMsg;
import business.servlet.NWAL2200.NWAL2200_CBMsg;
import business.servlet.NWAL2200.NWAL2200_DBMsg;
import business.servlet.NWAL2200.NWAL2200_EBMsg;
import business.servlet.NWAL2200.constant.NWAL2200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL2200CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2016/08/31   Fujitsu         T.Murai         Update          QC#14020
 * 2016/08/31   CITS            S.Tanikawa      Update          Unit Test#202
 * 2016/09/28   Fujitsu         S.Ohki          Update          S21_NA#14302
 * 2016/10/27   SRAA            K.Aratani       Update          S21_NA#15638
 * 2017/07/13   Fujitsu         K.Ishizuka      Update          S21_NA#18806(L3#372)
 * 2017/08/30   Fujitsu         S.Iidaka        Update          S21_NA#20792
 * 2017/09/19   Fujitsu         R.Nakamura      Update          S21_NA#21118
 * 2017/09/26   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/01/12   Fujitsu         A.Kosai         Update          S21_NA#22372
 * 2018/01/23   Fujitsu         T.Aoi           Update          QC#18798(L3#173)
 * 2018/06/27   Fujitsu         H.Nagashima     Update          QC#23726
 * 2018/07/17   Fujitsu         W.Honda         Update          QC#26188
 * 2019/12/23   Fujitsu         Y.Kanefusa      Update          S21_NA#54727
 * 2023/04/04   Hitachi         T.Doi           Update          QC#60254
 *</pre>
 */
public class NWAL2200CommonLogicForScreenFields {

    /**
     * ITEM_PROPERTY_NAME
     */
    private static final int ITEM_PROPERTY_NAME = 0;

    /**
     * ITEM_PROPERTY_EDITABLE
     */
    private static final int ITEM_PROPERTY_EDITABLE = 1;

    /**
     * ITEM_PROPERTY_AMOUNT
     */
    private static final int ITEM_PROPERTY_AMOUNT = 2;

    /**
     * ITEM_PROPERTY_LINK
     */
    private static final int ITEM_PROPERTY_LINK = 3;

    /**
     * ITEM_PROPERTY_FUNCTION_ID
     */
    private static final int ITEM_PROPERTY_FUNCTION_ID = 4;

    /**
     * ITEM_PROPERTY_INITIAL_ENABLE
     */
    private static final int ITEM_PROPERTY_INITIAL_ENABLE = 5;

    /**
     * ITEM_PROPERTY_STATUS
     */
    private static final int ITEM_PROPERTY_STATUS = 6;

    /**
     * ITEM_PROPERTY_FORCE_EDITABLE
     */
    private static final int ITEM_PROPERTY_FORCE_EDITABLE = 7;

    /**
     * ITEM_PROPERTY_MESSAGE_CODE
     */
    private static final int ITEM_PROPERTY_MESSAGE_CODE = 8;

    /**
     * BUTTON_PROPERTY_EDITABLE
     */
    private static final int BUTTON_PROPERTY_EDITABLE = 0;

    /**
     * BUTTON_PROPERTY_FUNCTION_ID
     */
    private static final int BUTTON_PROPERTY_FUNCTION_ID = 1;

    /**
     * BUTTON_PROPERTY_INITIAL_ENABLE
     */
    private static final int BUTTON_PROPERTY_INITIAL_ENABLE = 2;

    /**
     * BUTTON_PROPERTY_STATUS
     */
    private static final int BUTTON_PROPERTY_STATUS = 3;

    /**
     * BUTTON_PROPERTY_SRC_TP
     */
    private static final int BUTTON_PROPERTY_SRC_TP = 4;

    /**
     * setItemPropertyForHeader
     * @param properties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setItemPropertyForHeader(Map<EZDBItem, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setItemProperty(properties, scrnMsg.ordSrcRefNum, "Source Reference Num", true, false, false, Arrays.asList(new String[] {FUNC_ID_UPDATE, FUNC_ID_READ }), true, null, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.imptStsDescTxt, "Import Status", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.cpoSrcTpDescTxt, "Source Name", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxDtTm, "Import Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dsOrdCatgDescTxt, "Category", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dsOrdCatgDescTxt_LK, "Category", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dsOrdTpCd, "Reason Code", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dsOrdRsnCd, "Sub Reason Code", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setItemProperty(properties, scrnMsg.cpoOrdNum_PL, "Order Number", true, false, true, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
        } else {
            setItemProperty(properties, scrnMsg.cpoOrdNum_PL, "Order Number", false, false, true, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
        }
        // 2018/01/23 QC#18798 Add End
        setItemProperty(properties, scrnMsg.cpoOrdNum_LK, "Order Number", true, false, true, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxDsMultMsgDplyTxt, "Errors", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));

        // 2017/08/30 S21_NA#20792 Add Start
        setItemProperty(properties, scrnMsg.xxTotBaseAmt, "Sub Total", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxTotFrtAmt, "Charges", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxTotTaxAmt, "Tax", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxTotAmt, "Total", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxTotBaseAmt_LN, "Lines", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxTotBaseAmt_MT, "Maintenance", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxTotBaseAmt_RT, "RMA", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // 2017/08/30 S21_NA#20792 Add End

    }

    /**
     * setItemPropertyForHeaderTab
     * @param properties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setItemPropertyForHeaderTab(Map<EZDBItem, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        // location
        setItemProperty(properties, scrnMsg.billToCustAcctNm, "Bill To Name", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.billToCustAcctNm_LK, "Bill To Name", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.billToCustAcctCd, "Bill To Number", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.billToCustAcctCd_LK, "Bill To Number", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxAllLineAddr_BT, "Bill To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // Mod Start 2017/09/26 QC#19922
        //setItemProperty(properties, scrnMsg.billToCustCd, "Bill To Location", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        //setItemProperty(properties, scrnMsg.billToCustCd_LK, "Bill To Location", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.billToCustCd, "Bill To Code", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.billToCustCd_LK, "Bill To Code", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // Mod End 2017/09/26 QC#19922
        setItemProperty(properties, scrnMsg.shipToCustAcctNm, "Ship To Name", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.shipToCustAcctNm_LK, "Ship To Name", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dropShipFlg_LK, "DS", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.shipToCustAcctCd, "Ship To Number", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.shipToCustAcctCd_LK, "Ship To Number", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxAllLineAddr_SH, "Ship To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // Mod Start 2017/09/26 QC#19922
        //setItemProperty(properties, scrnMsg.shipToCustCd, "Ship To Location", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        //setItemProperty(properties, scrnMsg.shipToCustCd_LK, "Ship To Location", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.shipToCustCd, "Ship To Code", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.shipToCustCd_LK, "Ship To Code", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // Mod End 2017/09/26 QC#19922
        setItemProperty(properties, scrnMsg.soldToCustAcctNm, "Sold To Name", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.soldToCustAcctNm_LK, "Sold To Name", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.sellToCustCd, "Sold To Number", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.sellToCustCd_LK, "Sold To Number", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxAllLineAddr_SE, "Sold To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // Mod Start 2017/09/26 QC#19922
        //setItemProperty(properties, scrnMsg.soldToCustLocCd, "Sold To Location", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        //setItemProperty(properties, scrnMsg.soldToCustLocCd_LK, "Sold To Location", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.soldToCustLocCd, "Sold To Code", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.soldToCustLocCd_LK, "Sold To Code", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // Mod End 2017/09/26 QC#19922
        setItemProperty(properties, scrnMsg.negoDealAmt, "Negotiated Deal", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.slsRepTocNm, "Salesrep - Name", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, true, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.slsRepPsnNum_LK, "Sales Rep", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, true, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.slsRepPsnNum, "Resource Id", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, true, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxScrItem54Txt_CB, "Salesrep Branch", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.xxScrItem54Txt_CE, "Salesrep Bus Unit", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.prcCatgNm_LK, "Price List", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.prcCatgNm, "Price List", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.custIssPoNum, "Customer PO", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.leaseCmpyPoNum, "Leasing Company PO", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.custIssPoDt, "Customer PO Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.aquNum, "Acquisition Number", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.ordLogTpCd, "Log Type", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, true, Arrays.asList(new String[] {"" }));//S21_NA#18806(L3#372) MOD
        setItemProperty(properties, scrnMsg.xxCratDt, "Interface Creation Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.invCmntTxt, "Invoice Comments", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
    }

    /**
     * setItemPropertyForAddlTab
     * @param properties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setItemPropertyForAddlTab(Map<EZDBItem, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setItemProperty(properties, scrnMsg.frtCondDescTxt, "Freight Terms", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.frtCondDescTxt_LK, "Freight Terms", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.spclHdlgTpCd, "Special Handling", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.shpgSvcLvlCd, "Service Level", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // QC#23726 2018/06/27 mod Start
//        // QC#13688 2017/02/24 Add Start
//        // setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt, "Carrier Service Level", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
//        // setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt_LK, "Carrier Service Level", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
//        // setItemProperty(properties, scrnMsg.carrAcctNum, "Carrier Account Num", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt, "Carrier Service Level", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt_LK, "Carrier Service Level", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
//            setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt, "Carrier Service Level", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
//            setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt_LK, "Carrier Service Level", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, scrnMsg.carrAcctNum, "Carrier Account Num", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        } else {
//            setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt, "Carrier Service Level", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
//            setItemProperty(properties, scrnMsg.carrSvcLvlDescTxt_LK, "Carrier Service Level", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, scrnMsg.carrAcctNum, "Carrier Account Num", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        }
//        // QC#13688 2017/02/24 Add End
        // QC#23726 2018/06/27 mod End
        // QC#17474 2017/02/21 Mod Start
        // setItemProperty(properties, scrnMsg.pmtTermCashDiscDescTxt, "Payment Terms", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // setItemProperty(properties, scrnMsg.pmtTermCashDiscDescTxt_LK, "Payment Terms", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        if(S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.ovrdPmtTermFlg.getValue())){
            setItemProperty(properties, scrnMsg.pmtTermCashDiscDescTxt, "Payment Terms", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, scrnMsg.pmtTermCashDiscDescTxt_LK, "Payment Terms", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        }else{
            setItemProperty(properties, scrnMsg.pmtTermCashDiscDescTxt, "Payment Terms", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, scrnMsg.pmtTermCashDiscDescTxt_LK, "Payment Terms", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        }
        // QC#17474 2017/02/21 Mod End
        setItemProperty(properties, scrnMsg.dsPmtMethCd, "Payment Method", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.prePmtChkNum, "Check Number", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.prePmtAmt, "Check Amount", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.prePmtTpCd, "Pre Payment Type", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.rddDt, "Requested Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.prcContrNum, "Association Program", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.flPrcListDescTxt, "Floor Price List", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.flPrcListDescTxt_LK, "Floor Price List", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.loanPerDaysAot, "Loan Period(Days)", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.csmpContrNum, "CSMP Number", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.csmpContrNum_LK, "CSMP Number", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dlrRefNum, "CSA Number(Dealer Ref#)", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.dlrRefNum_LK, "CSA Number(Dealer Ref#)", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.ordSgnDt, "Customer Signed Order Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // 09/28/2016 S21_NA#14302 add Start
        setItemProperty(properties, scrnMsg.dclnSvcCd, "Customer Declines Equipment Maintenance", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // 09/28/2016 S21_NA#14302 add End
        setItemProperty(properties, scrnMsg.leasePrchOptCd, "End of Term Purchase Option", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.leaseTermMthAot, "Term", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.leasePmtFreqCd, "Payment Frequency", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // 2018/01/23 QC#18798 Add Start
        setItemProperty(properties, scrnMsg.leaseTermMthAot_EM, "Term", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.psnNum_GS, "Sales Rep", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.tocNm_GS, "Sales Rep", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.coaExtnCd_GS, "Bus Unit", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.coaExtnDescTxt_GS, "Bus Unit", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.coaBrCd_GS, "Branch", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.coaBrDescTxt_GS, "Branch", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.coaCcCd_GS, "Cost Center", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        setItemProperty(properties, scrnMsg.coaCcDescTxt_GS, "Cost Center", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        // 2018/01/23 QC#18798 Add End
    }

    /**
     * setItemPropertyForConfigTab
     * @param properties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setItemPropertyForConfigTab(Map<EZDBItem, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setItemProperty(properties, scrnMsg.dsOrdPosnNum_DL, "Config#", true, false, false, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2200_ABMsg row = scrnMsg.A.no(i);
            setItemProperty(properties, row.xxChkBox_LC, "Config Line Select", true, false, false, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dsOrdPosnNum_LC, "Config Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.configTpDescTxt_LC, "Config Type", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.svcConfigMstrPk_LC, "Config ID", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.t_MdlNm_LC, "Model", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.addrLbTxt_LC, "Address Group", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // QC#15638
            if (CPO_SRC_TP.INVENTORY_REQUEST_ENTRY.equals(scrnMsg.cpoSrcTpCd.getValue())) {
                setItemProperty(properties, row.shipToCustLocCd_LC, "Ship To", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
                setItemProperty(properties, row.xxImageTxt_AS, "", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
                setItemProperty(properties, row.dropShipFlg_LC, "DS", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
                setItemProperty(properties, row.xxImageTxt_AD, "DS", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            } else {
                setItemProperty(properties, row.shipToCustLocCd_LC, "Ship To", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
                setItemProperty(properties, row.xxImageTxt_AS, "", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
                setItemProperty(properties, row.dropShipFlg_LC, "DS", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
                setItemProperty(properties, row.xxImageTxt_AD, "DS", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            }
            setItemProperty(properties, row.billToCustLocCd_LC, "Bill To", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_AB, "Bill To", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // 09/28/2016 S21_NA#14302 add Start
            setItemProperty(properties, row.dclnSvcCd_LC, "Decline Maint", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // 09/28/2016 S21_NA#14302 add End
            // Add Start 2017/09/19 QC#21118
            setItemProperty(properties, row.xxAllLineAddr_LB, "Bill To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxAllLineAddr_LS, "Ship To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // Add End 2017/09/19 QC#21118
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NWAL2200_BBMsg row = scrnMsg.B.no(i);
            setItemProperty(properties, row.xxChkBox_LL, "Line Select", true, false, false, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxLineNum_LL, "Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxScrItem130Txt_LL, "Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.mdseCd_LL, "Item#", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, true, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.mdseDescShortTxt_LL, "Item Description", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.ordCustUomQty_LL, "Qty", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.pkgUomDescTxt_LL, "UOM", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.ordQty_LL, "Each Qty", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.cpoDtlDealSlsAmt_LL, "Sell Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.entDealNetUnitPrcAmt_LL, "Net Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.prcCatgNm_LL, "Sell Price List", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_BP, "Sell Price List", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.prcListEquipConfigNum_LL, "Price Config Number", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dsOrdLineCatgCd_LL, "Line Category", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.ordLineSrcCd_LL, "Source", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rtlWhNm_LL, "Warehouse", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_BW, "Warehouse", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // START 2023/04/04 T.Doi [QC#60254, MOD]
            // setItemProperty(properties, row.rtlSwhNm_LL, "Sub Warehouse", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rtlSwhNm_LL, "Sub Warehouse", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // END 2023/04/04 T.Doi [QC#60254, MOD]
            // Add 2016/08/31 QC#14020
            setItemProperty(properties, row.xxImageTxt_BS, "Sub Warehouse", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));

            setItemProperty(properties, row.serNum_LL, "Sub Warehouse", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.flPrcListDescTxt_LL, "Floor Price List", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // 2018/01/12 S21_NA#22372 Add Start
            setItemProperty(properties, row.funcUnitFlPrcAmt_LL, "Floor Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // 2018/01/12 S21_NA#22372 Add End
            setItemProperty(properties, row.xxImageTxt_BF, "Floor Price List", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dealPrcListPrcAmt_LL, "List Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.lineDealSubTotAmt_LL, "Subtotal", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.coaMdseTpDescTxt_LL, "Merch Type", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.coaProdDescTxt_LL, "Product Code", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dplyLineRefNum_LL, "Line Reference", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.prcBaseDt_LL, "Price Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.custMdseCd_LL, "Customer Item#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rddDt_LL, "Requested Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.imptLineFlg_LL, "Import Line", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.finItemLineFlg_LL, "Financed Item", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        }
    }

    /**
     * setItemPropertyForRMATab
     * @param properties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setItemPropertyForRMATab(Map<EZDBItem, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setItemProperty(properties, scrnMsg.dsOrdPosnNum_DR, "Config#", true, false, false, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL2200_CBMsg row = scrnMsg.C.no(i);
            setItemProperty(properties, row.xxChkBox_RC, "Config Line Select", true, false, false, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dsOrdPosnNum_RC, "Config Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.configTpDescTxt_RC, "Config Type", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.svcConfigMstrPk_RC, "Config ID", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.t_MdlNm_RC, "Model", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.addrLbTxt_RC, "Address Group", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.shipToCustLocCd_RC, "Ship To", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_CS, "", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dropShipFlg_RC, "DS", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_CD, "DS", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.billToCustLocCd_RC, "Bill To", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_CB, "Bill To", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // Add Start 2017/09/19 QC#21118
            setItemProperty(properties, row.xxAllLineAddr_RB, "Bill To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxAllLineAddr_RS, "Ship To Address", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // Add End 2017/09/19 QC#21118
        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            NWAL2200_DBMsg row = scrnMsg.D.no(i);
            setItemProperty(properties, row.xxChkBox_RL, "Line Select", true, false, false, validFunctionId, isInitialEditable, null, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxLineNum_RL, "Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxScrItem130Txt_RL, "Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.mdseCd_RL, "Item#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.mdseDescShortTxt_RL, "Item Description", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.ordCustUomQty_RL, "Qty", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.pkgUomDescTxt_RL, "UOM", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.ordQty_RL, "Each Qty", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.cpoDtlDealSlsAmt_RL, "Sell Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.entDealNetUnitPrcAmt_RL, "Net Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.prcCatgNm_RL, "Sell Price List", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_DP, "Sell Price List", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dsCpoLineCatgCd_RL, "Line Category", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.ordLineSrcCd_RL, "Source", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rtlWhNm_RL, "Warehouse", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxImageTxt_DW, "Warehouse", true, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rtlSwhNm_RL, "Sub Warehouse", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.serNum_RL, "Serial#", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.flPrcListDescTxt_RL, "Floor Price List", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // 2018/01/12 S21_NA#22372 Add Start
            setItemProperty(properties, row.funcUnitFlPrcAmt_RL, "Floor Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            // 2018/01/12 S21_NA#22372 Add End
            setItemProperty(properties, row.xxImageTxt_DF, "Floor Price List", false, false, true, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dealPrcListPrcAmt_RL, "List Price", false, true, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.lineDealSubTotAmt_RL, "Subtotal", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.coaMdseTpDescTxt_RL, "Merch Type", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.coaProdDescTxt_RL, "Product Code", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dplyLineRefNum_RL, "Line Reference", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.prcBaseDt_RL, "Price Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.custMdseCd_RL, "Customer Item#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rqstPickUpDt_RL, "Requested Pick Up Date", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.rtrnRsnCd_RL, "Return Reason Code", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.hddRmvCd_RL, "HDD Removal", true, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        }
    }

    /**
     * setItemPropertyForErrorsTab
     * @param properties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setItemPropertyForErrorsTab(Map<EZDBItem, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        for (int i = 0; i < scrnMsg.E.length(); i++) {
            NWAL2200_EBMsg row = scrnMsg.E.no(i);
            setItemProperty(properties, row.xxLineNum_EL, "Line#", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.svcConfigMstrPk_EL, "Config ID", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.xxScrItem30Txt_EL, "Level", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
            setItemProperty(properties, row.dsImptOrdErrTxt_EL, "Error Text", false, false, false, validFunctionId, isInitialEditable, validImptOrdStsCd, false, Arrays.asList(new String[] {"" }));
        }
    }

    private static void setButtonPropertyForHeader(Map<String, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        List<String> validReferFunctionId = Arrays.asList(new String[] {FUNC_ID_READ, FUNC_ID_UPDATE }); // QC#54727 2019/12/23 Add
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setButtonProperty(properties, BTN_CMN_SAV[0], true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);
        
        // 2018/01/23 QC#18798 Mod Start
        //setButtonProperty(properties, BTN_CMN_SUB[0], true, validFunctionId, isInitialEditable, Arrays.asList(new String[] {IMPT_STS.VALIDATED }), null);
        if (!CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setButtonProperty(properties, BTN_CMN_SUB[0], true, validFunctionId, isInitialEditable, Arrays.asList(new String[] {IMPT_STS.VALIDATED }), null);
        } else {
            setButtonProperty(properties, BTN_CMN_SUB[0], true, validFunctionId, isInitialEditable, Arrays.asList(new String[] {IMPT_STS.VALIDATED, IMPT_STS.ERROR }), null);
        }
        // 2018/01/23 QC#18798 Mod End
        setButtonProperty(properties, BTN_CMN_RJT[0], true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);

        setButtonProperty(properties, BTN_SEARCH, true, validFunctionId, true, null, null);
        setButtonProperty(properties, BTN_DELIVERY_INFO, true, validFunctionId, isInitialEditable, null, null);
        // QC#54727 2019/12/23 Mod Start
        // setButtonProperty(properties, BTN_SERVICE_PRICING, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_SERVICE_PRICING, true, validReferFunctionId, isInitialEditable, null, null);
        // QC#54727 2019/12/23 Mod End
        setButtonProperty(properties, BTN_PROFITABILITY, true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);
        setButtonProperty(properties, BTN_SOM, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));

        // 2018/01/23 QC#18798 Mod Start
        //setButtonProperty(properties, BTN_APPROVAL, true, validFunctionId, isInitialEditable, null, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));
        //setButtonProperty(properties, BTN_PROFITABILITY, true, validFunctionId, isInitialEditable, null, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));
        setButtonProperty(properties, BTN_APPROVAL, true, validFunctionId, isInitialEditable, null, Arrays.asList(new String[] {CPO_SRC_TP.SOM, CPO_SRC_TP.EOPS }));
        setButtonProperty(properties, BTN_PROFITABILITY, true, validFunctionId, isInitialEditable, null, Arrays.asList(new String[] {CPO_SRC_TP.SOM, CPO_SRC_TP.EOPS }));
        // 2018/01/23 QC#18798 Mod End

        setButtonProperty(properties, BTN_VALIDATE, true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);
        setButtonProperty(properties, BTN_IMPORT_ATTRIBUTE, true, validFunctionId, isInitialEditable, null, Arrays.asList(new String[] {CPO_SRC_TP.EDI }));
    }

    private static void setButtonPropertyForHeaderTab(Map<String, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setButtonProperty(properties, BTN_SPECIAL_INSTRUCTION, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_SALES_CREDIT, true, validFunctionId, isInitialEditable, null, null);
    }

    private static void setButtonPropertyForAddlTab(Map<String, Object[]> properties, NWAL2200BMsg scrnMsg) {
        // no process
    }

    private static void setButtonPropertyForConfigTab(Map<String, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setButtonProperty(properties, BTN_LINE_ALL_EXPAND, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_LINE_ALL_COLLAPSED, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_SALES_CREDIT, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_BUYOUT, true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);
        setButtonProperty(properties, BTN_PRICE_CHANGE, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_ADD_LINE_DETAIL, true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);

        // 2018/01/23 QC#18798 Mod Start
        //// UPDATE START 2016/08/31 Unit Test#202
        //setButtonProperty(properties, BTN_ADDR_MASS_APLY_DTL, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));
        //setButtonProperty(properties, BTN_ADDR_MASS_APLY_RMA, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));
        //// UPDATE END 2016/08/31 Unit Test#202
        setButtonProperty(properties, BTN_ADDR_MASS_APLY_DTL, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM, CPO_SRC_TP.EOPS }));
        setButtonProperty(properties, BTN_ADDR_MASS_APLY_RMA, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM, CPO_SRC_TP.EOPS }));
        // 2018/01/23 QC#18798 Mod End

        // 2018/01/23 QC#18798 Add Start
        setButtonProperty(properties, BTN_CONFIG_CHECK_ALL, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_CONFIG_UNCHECK_ALL, true, validFunctionId, isInitialEditable, null, null);
        // 2018/01/23 QC#18798 Add End
        if (scrnMsg.A.getValidCount() > 1) {
            setButtonProperty(properties, BTN_CONFIG_JUMP, true, validFunctionId, isInitialEditable, null, null);
        } else {
            setButtonProperty(properties, BTN_CONFIG_JUMP, false, validFunctionId, isInitialEditable, null, null);
        }
    }

    private static void setButtonPropertyForRmaTab(Map<String, Object[]> properties, NWAL2200BMsg scrnMsg) {

        List<String> validFunctionId = Arrays.asList(new String[] {FUNC_ID_UPDATE });
        boolean isInitialEditable = false;
        // 09/28/2016 S21_NA#14302 mod Start
        // List<String> validImptOrdStsCd = Arrays.asList(new String[]
        // {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR,
        // IMPT_STS.NOT_PROCESSED, IMPT_STS.VALIDATED });
        List<String> validImptOrdStsCd = Arrays.asList(new String[] {IMPT_STS.PENDING_OM_REVIEW, IMPT_STS.ERROR, IMPT_STS.VALIDATED });
        // 09/28/2016 S21_NA#14302 mod End

        setButtonProperty(properties, BTN_LINE_ALL_EXPAND, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_LINE_ALL_COLLAPSED, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_SALES_CREDIT, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_PRICE_CHANGE, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_ADD_LINE_DETAIL, true, validFunctionId, isInitialEditable, validImptOrdStsCd, null);

        // 2018/01/23 QC#18798 Mod Start
        //// UPDATE START 2016/08/31 Unit Test#202
        //setButtonProperty(properties, BTN_ADDR_MASS_APLY_DTL, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));
        //setButtonProperty(properties, BTN_ADDR_MASS_APLY_RMA, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM }));
        //// UPDATE END 2016/08/31 Unit Test#202
        setButtonProperty(properties, BTN_ADDR_MASS_APLY_DTL, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM, CPO_SRC_TP.EOPS }));
        setButtonProperty(properties, BTN_ADDR_MASS_APLY_RMA, true, validFunctionId, isInitialEditable, validImptOrdStsCd, Arrays.asList(new String[] {CPO_SRC_TP.SOM, CPO_SRC_TP.EOPS }));
        // 2018/01/23 QC#18798 Mod End

        // 2018/01/23 QC#18798 Add Start
        setButtonProperty(properties, BTN_CONFIG_CHECK_ALL, true, validFunctionId, isInitialEditable, null, null);
        setButtonProperty(properties, BTN_CONFIG_UNCHECK_ALL, true, validFunctionId, isInitialEditable, null, null);
        // 2018/01/23 QC#18798 Add End
        if (scrnMsg.C.getValidCount() > 1) {
            setButtonProperty(properties, BTN_CONFIG_JUMP, true, validFunctionId, isInitialEditable, null, null);
        } else {
            setButtonProperty(properties, BTN_CONFIG_JUMP, false, validFunctionId, isInitialEditable, null, null);
        }
    }

    private static void setButtonPropertyForErrorsTab(Map<String, Object[]> properties, NWAL2200BMsg scrnMsg) {
        // no process
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * setButtonMessage
     * @param handler Event Handler
     */
    public static void setButtonMessage(S21CommonHandler handler) {

        handler.setButtonConfirmMsgEx(BTN_CMN_RJT[1], NWAM0750W, null, 0);
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this
            // application.
            throw new S21AbendException("ZZSM4300E", new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains("NWAL2200T020")) {
            return true;
        }

        return false;
    }

    /**
     * setNameForMessage
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setNameForMessage(NWAL2200BMsg scrnMsg) {

        Map<EZDBItem, Object[]> itemPropertyMap = getItemProperty(scrnMsg, null);
        Iterator<Map.Entry<EZDBItem, Object[]>> entries = itemPropertyMap.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<EZDBItem, Object[]> itemProperty = entries.next();
            EZDBItem item = itemProperty.getKey();
            item.setNameForMessage(getNameForMessage(itemProperty.getValue()));
        }
    }

    /**
     * setLabel
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setLabel(NWAL2200BMsg scrnMsg) {

        Map<EZDBItem, Object[]> itemPropertyMap = getItemProperty(scrnMsg, null);
        Iterator<Map.Entry<EZDBItem, Object[]>> entries = itemPropertyMap.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<EZDBItem, Object[]> itemProperty = entries.next();
            EZDBItem item = itemProperty.getKey();
            if (!editable(itemProperty.getValue())) {
                // 2017/01/26 S21_NA#17119 Mod Start
                // item.setInputProtected(true);
                if (isLink(itemProperty.getValue())) {
                    item.clear();
                    item.setInputProtected(false);
                } else {
                    item.setInputProtected(true);
                }
                // 2017/01/26 S21_NA#17119 Mod End
            }
        }
    }

    /**
     * setAmount
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setAmount(NWAL2200BMsg scrnMsg) {

        Map<EZDBItem, Object[]> itemPropertyMap = getItemProperty(scrnMsg, null);
        Iterator<Map.Entry<EZDBItem, Object[]>> entries = itemPropertyMap.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<EZDBItem, Object[]> itemProperty = entries.next();
            EZDBItem item = itemProperty.getKey();
            if (isAmount(itemProperty.getValue()) && item instanceof EZDBBigDecimalItem) {
                ((EZDBBigDecimalItem) item).setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            }
        }
    }

    /**
     * @param scrnMsg NWAL2200BMsg
     * @param needsMandatoryCheck boolean
     * @param xxDplyTab String
     */
    public static void addCheckItem(NWAL2200BMsg scrnMsg, Boolean needsMandatoryCheck, String xxDplyTab) {

        if (S21StringUtil.isEquals(scrnMsg.imptStsCd.getValue(), IMPT_STS.REJECT)) {
            return;
        }

        List<String> tabNameList = new ArrayList<String>();
        if (xxDplyTab == null) {

            tabNameList.add(TAB_HEADER);
            tabNameList.add(TAB_ADDL_HEADER);
            tabNameList.add(TAB_LINE_CONFIG);
            tabNameList.add(TAB_RMA);
            tabNameList.add(TAB_ERRORS);
        } else {

            tabNameList.add(xxDplyTab);
        }

        String moveTabName = xxDplyTab;
        for (String tabName : tabNameList) {

            Map<EZDBItem, Object[]> itemPropertyMap = getItemProperty(scrnMsg, tabName);
            Iterator<Map.Entry<EZDBItem, Object[]>> entries = itemPropertyMap.entrySet().iterator();

            while (entries.hasNext()) {

                Map.Entry<EZDBItem, Object[]> itemProperty = entries.next();
                EZDBItem item = itemProperty.getKey();
                if (needsMandatoryCheck == null || ZYPCommonFunc.hasValue(item)) {
                    scrnMsg.addCheckItem(item);
                    if (item.isError()) {
                        moveTabName = tabName;
                    }
                }
            }
        }
        if (moveTabName != null) {

            scrnMsg.xxDplyTab.setValue(moveTabName);
        }
    }

    /**
     * setConditionForCurrentTab
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     * @param xxDplyTab String
     */
    public static void setConditionForCurrentTab(S21CommonHandler handler, NWAL2200BMsg scrnMsg, String xxDplyTab) {

        setConditionForHeader(handler, scrnMsg);

        if (S21StringUtil.isEmpty(xxDplyTab) || S21StringUtil.isEquals(xxDplyTab, TAB_HEADER)) {

            setConditionForHeaderTab(handler, scrnMsg);
        }

        if (S21StringUtil.isEmpty(xxDplyTab) || S21StringUtil.isEquals(xxDplyTab, TAB_ADDL_HEADER)) {

            setConditionForAddlTab(handler, scrnMsg);
        }

        if (S21StringUtil.isEmpty(xxDplyTab) || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG)) {

            setConditionForConfigTab(handler, scrnMsg);
        }

        if (S21StringUtil.isEmpty(xxDplyTab) || S21StringUtil.isEquals(xxDplyTab, TAB_RMA)) {

            setConditionForRmaTab(handler, scrnMsg);
        }

        if (S21StringUtil.isEmpty(xxDplyTab) || S21StringUtil.isEquals(xxDplyTab, TAB_ERRORS)) {

            setConditionForErrorsTab(handler, scrnMsg);
        }
    }

    /**
     * setConditionForHeader
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setConditionForHeader(S21CommonHandler handler, NWAL2200BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);

        // button
        Map<String, Object[]> buttonProperties = new HashMap<String, Object[]>();
        setButtonPropertyForHeader(buttonProperties, scrnMsg);
        setButtonCondition(handler, buttonProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.cpoSrcTpCd.getValue());

        // item
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForHeader(itemProperties, scrnMsg);
        setItemCondition(handler, itemProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.dsImptOrdFrceEdtFlg.getValue());

        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setItemConditionHeaderForEOPS(scrnMsg);
        }
        // 2018/01/23 QC#18798 Add End
    }

    /**
     * setConditionForHeader
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setConditionForHeaderTab(S21CommonHandler handler, NWAL2200BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);

        // button
        Map<String, Object[]> buttonProperties = new HashMap<String, Object[]>();
        setButtonPropertyForHeaderTab(buttonProperties, scrnMsg);
        setButtonCondition(handler, buttonProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.cpoSrcTpCd.getValue());

        // item
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForHeaderTab(itemProperties, scrnMsg);
        setItemCondition(handler, itemProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.dsImptOrdFrceEdtFlg.getValue());

        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setItemConditionHeaderTabForEOPS(scrnMsg);
        }
        // 2018/01/23 QC#18798 Add End
        // 2018/07/17 QC#26188 Add Start
        handler.setButtonEnabled(BTN_DELIVERY_INFO, false);
        // 2018/07/17 QC#26188 Add End
    }

    /**
     * setConditionForAddlTab
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setConditionForAddlTab(S21CommonHandler handler, NWAL2200BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);

        // button
        Map<String, Object[]> buttonProperties = new HashMap<String, Object[]>();
        setButtonPropertyForAddlTab(buttonProperties, scrnMsg);
        setButtonCondition(handler, buttonProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.cpoSrcTpCd.getValue());

        // item
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForAddlTab(itemProperties, scrnMsg);
        setItemCondition(handler, itemProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.dsImptOrdFrceEdtFlg.getValue());

        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setItemConditionAddlTabForEOPS(scrnMsg);
        }
        // 2018/01/23 QC#18798 Add End
        // 2018/07/17 QC#26188 Add Start
        handler.setButtonEnabled(BTN_DELIVERY_INFO, false);
        // 2018/07/17 QC#26188 Add End
    }

    /**
     * setConditionForConfigTab
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setConditionForConfigTab(S21CommonHandler handler, NWAL2200BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);

        // button
        Map<String, Object[]> buttonProperties = new HashMap<String, Object[]>();
        setButtonPropertyForConfigTab(buttonProperties, scrnMsg);
        setButtonCondition(handler, buttonProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.cpoSrcTpCd.getValue());

        // item
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForConfigTab(itemProperties, scrnMsg);
        setItemCondition(handler, itemProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.dsImptOrdFrceEdtFlg.getValue());
        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setItemConditionConfigTabForEOPS(scrnMsg);
        }
        // 2018/01/23 QC#18798 Add End
        // 2018/07/17 QC#26188 Add Start
        handler.setButtonEnabled(BTN_DELIVERY_INFO, true);
        // 2018/07/17 QC#26188 Add End
    }

    /**
     * setConditionForRmaTab
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setConditionForRmaTab(S21CommonHandler handler, NWAL2200BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);

        // button
        Map<String, Object[]> buttonProperties = new HashMap<String, Object[]>();
        setButtonPropertyForRmaTab(buttonProperties, scrnMsg);
        setButtonCondition(handler, buttonProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.cpoSrcTpCd.getValue());

        // item
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForRMATab(itemProperties, scrnMsg);
        setItemCondition(handler, itemProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.dsImptOrdFrceEdtFlg.getValue());
        // 2018/01/23 QC#18798 Add Start
        if (CPO_SRC_TP.EOPS.equals(scrnMsg.cpoSrcTpCd.getValue())) {
            setItemConditionRmaTabForEOPS(scrnMsg);
        }
        // 2018/01/23 QC#18798 Add End
        // 2018/07/17 QC#26188 Add Start
        handler.setButtonEnabled(BTN_DELIVERY_INFO, true);
        // 2018/07/17 QC#26188 Add End
    }

    /**
     * setConditionForErrorsTab
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2200BMsg
     */
    public static void setConditionForErrorsTab(S21CommonHandler handler, NWAL2200BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(NWAL2200Constant.BIZ_ID);

        // button
        Map<String, Object[]> buttonProperties = new HashMap<String, Object[]>();
        setButtonPropertyForErrorsTab(buttonProperties, scrnMsg);
        setButtonCondition(handler, buttonProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.cpoSrcTpCd.getValue());

        // item
        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForErrorsTab(itemProperties, scrnMsg);
        setItemCondition(handler, itemProperties, funcList, scrnMsg.imptStsCd.getValue(), scrnMsg.dsImptOrdFrceEdtFlg.getValue());
        // 2018/07/17 QC#26188 Add Start
        handler.setButtonEnabled(BTN_DELIVERY_INFO, false);
        // 2018/07/17 QC#26188 Add End
    }

    /**
     * setErrorItem
     * @param itemProperties Map<EZDBItem, Object[]>
     * @param scrnMsg NWAL2200BMsg
     * @param messageCode String
     * @return target item has found
     */
    public static boolean setErrorItem(Map<EZDBItem, Object[]> itemProperties, NWAL2200BMsg scrnMsg, String messageCode, String message) {

        if (itemProperties == null) {
            return false;
        }

        boolean foundItem = false;
        Iterator<Map.Entry<EZDBItem, Object[]>> entries = itemProperties.entrySet().iterator();

        String[] parameter = getMessageParameters(messageCode, message);

        while (entries.hasNext()) {
            Map.Entry<EZDBItem, Object[]> itemProperty = entries.next();
            EZDBItem item = itemProperty.getKey();
            if (isMessageTarget(itemProperty.getValue(), messageCode)) {
                item.setErrorInfo(1, messageCode, parameter);
                foundItem = true;
                continue;
            }
        }
        return foundItem;
    }

    /**
     * get message parameter
     * @param messageCode String
     * @param message String
     * @return message parameter String[]
     */
    public static String[] getMessageParameters(String messageCode, String message) {

        String originalMessage = S21MessageFunc.clspGetMessage(messageCode);

        if (S21StringUtil.isEmpty(originalMessage)) {
            return null;
        }

        // separated
        String[] separatedMessageArray = originalMessage.split("@");

        if (separatedMessageArray == null || separatedMessageArray.length <= 1) {
            return null;
        }

        List<String> parmeters = new ArrayList<String>();
        String parmeterMessage = message;
        for (int i = 0; i < separatedMessageArray.length; i++) {
            String separatedMessage = separatedMessageArray[i];
            int startIndex = separatedMessage.length();
            if (startIndex >= parmeterMessage.length()) {
                break;
            }
            parmeterMessage = parmeterMessage.substring(startIndex);
            if (S21StringUtil.isEmpty(parmeterMessage)) {
                break;
            }
            if (i + 1 >= separatedMessageArray.length) {
                parmeters.add(parmeterMessage);
                break;
            }
            int lastIndex = parmeterMessage.indexOf(separatedMessageArray[i + 1]);
            if (lastIndex >= parmeterMessage.length()) {
                lastIndex = parmeterMessage.length() - 1;
            }
            parmeters.add(parmeterMessage.substring(0, lastIndex));
            parmeterMessage = parmeterMessage.substring(lastIndex);
        }

        return parmeters.toArray(new String[0]);
    }

    private static void setItemProperty(Map<EZDBItem, Object[]> properties, // properties
            EZDBItem item, // screen item
            String name, // 0 name for message
            boolean editable, // 1 editable item
            boolean isAmount, // 2 amount item
            boolean isLink, // 3 link item
            List<String> validFunctionId, // 4 valid function id
            boolean isInitialEditable, // 5 initial editable item
            List<String> validImptOrdStsCd, // 6 valid status
            boolean forceEditable, // 7 force editable
            List<String> messageCode // 8 message code
    ) {
        setItemPropertyLocal(properties, item, (Object) name, (Object) editable, (Object) isAmount, (Object) isLink, (Object) validFunctionId, (Object) isInitialEditable, (Object) validImptOrdStsCd, (Object) forceEditable,
                (Object) messageCode);
    }

    private static void setItemPropertyLocal(Map<EZDBItem, Object[]> itemProperties, EZDBItem item, Object... properties) {
        itemProperties.put(item, properties);
    }

    private static Map<EZDBItem, Object[]> getItemProperty(NWAL2200BMsg scrnMsg, String xxDplyTab) {

        Map<EZDBItem, Object[]> itemProperties = new HashMap<EZDBItem, Object[]>();
        setItemPropertyForHeader(itemProperties, scrnMsg);
        if (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_HEADER)) {
            setItemPropertyForHeaderTab(itemProperties, scrnMsg);
        }
        if (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_ADDL_HEADER)) {
            setItemPropertyForAddlTab(itemProperties, scrnMsg);
        }
        if (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_LINE_CONFIG)) {
            setItemPropertyForConfigTab(itemProperties, scrnMsg);
        }
        if (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_RMA)) {
            setItemPropertyForRMATab(itemProperties, scrnMsg);
        }
        if (xxDplyTab == null || S21StringUtil.isEquals(xxDplyTab, TAB_ERRORS)) {
            setItemPropertyForErrorsTab(itemProperties, scrnMsg);
        }
        return itemProperties;
    }

    private static void setButtonProperty(Map<String, Object[]> properties, // properties
            String buttonName, // button Name
            boolean editable, // 1 editable item
            List<String> validFunctionId, // 2 valid function id
            boolean isInitialEditable, // 3 initial editable item
            List<String> validImptOrdStsCd, // 4 valid status
            List<String> validCpoSrcTp // 5 source type
    ) {
        setButtonPropertyLocal(properties, buttonName, (Object) editable, (Object) validFunctionId, (Object) isInitialEditable, (Object) validImptOrdStsCd, (Object) validCpoSrcTp);
    }

    private static void setButtonPropertyLocal(Map<String, Object[]> itemProperties, String buttonName, Object... properties) {
        itemProperties.put(buttonName, properties);
    }

    private static void setItemCondition(S21CommonHandler handler, Map<EZDBItem, Object[]> itemProperties, List<String> funcList, String imptStsCd, String dsImptOrdFrceEdtFlg) {

        if (itemProperties == null) {
            return;
        }

        Iterator<Map.Entry<EZDBItem, Object[]>> entries = itemProperties.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<EZDBItem, Object[]> itemProperty = entries.next();
            EZDBItem item = itemProperty.getKey();
            if (!editable(itemProperty.getValue())) {
                if (isLink(itemProperty.getValue())) {
                    item.clear();
                } else {
                    item.setInputProtected(true);
                }
                continue;
            }
            if (isLink(itemProperty.getValue())) {
                ((EZDBStringItem) item).setValue(ZYPConstant.FLG_ON_Y);
            } else {
                item.setInputProtected(false);
            }
            if (!isInitialEnable(itemProperty.getValue())) {
                if (S21StringUtil.isEmpty(imptStsCd)) {
                    if (isLink(itemProperty.getValue())) {
                        item.clear();
                    } else {
                        item.setInputProtected(true);
                    }
                    continue;
                }
            } else {
                if (S21StringUtil.isNotEmpty(imptStsCd)) {
                    if (isLink(itemProperty.getValue())) {
                        item.clear();
                    } else {
                        item.setInputProtected(true);
                    }
                    continue;
                }
            }
            if (!validStatus(itemProperty.getValue(), imptStsCd)) {
                if (isLink(itemProperty.getValue())) {
                    item.clear();
                } else {
                    item.setInputProtected(true);
                }
                continue;
            }

            if (isForceEditable(itemProperty.getValue())) {
                if (!S21StringUtil.isEquals(dsImptOrdFrceEdtFlg, ZYPConstant.FLG_ON_Y)) {
                    if (isLink(itemProperty.getValue())) {
                        item.clear();
                    } else {
                        item.setInputProtected(true);
                    }
                    continue;
                }
            }
            if (!hasFunction(itemProperty.getValue(), funcList)) {
                if (isLink(itemProperty.getValue())) {
                    item.clear();
                } else {
                    item.setInputProtected(true);
                }
                continue;
            }
        }
    }

    // 2018/01/23 QC#18798 Add Start
    private static void setItemConditionHeaderForEOPS(NWAL2200BMsg scrnMsg) {

        List<String> emsdImptStsCdList = new ArrayList<String>();
        emsdImptStsCdList = getEmsdImptStsCdList(scrnMsg, emsdImptStsCdList);

        if (emsdImptStsCdList.size() > 0) {
            if (emsdImptStsCdList.contains(IMPT_STS.SUCCESS)) {

                scrnMsg.ordSrcRefNum.setInputProtected(true);
                scrnMsg.imptStsDescTxt.setInputProtected(true);
                scrnMsg.cpoSrcTpDescTxt.setInputProtected(true);
                scrnMsg.xxDtTm.setInputProtected(true);
                scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
                scrnMsg.dsOrdCatgDescTxt_LK.setInputProtected(true);
                scrnMsg.dsOrdTpCd.setInputProtected(true);
                scrnMsg.dsOrdRsnCd.setInputProtected(true);
                scrnMsg.xxDsMultMsgDplyTxt.setInputProtected(true);
                scrnMsg.xxTotBaseAmt.setInputProtected(true);
                scrnMsg.xxTotFrtAmt.setInputProtected(true);
                scrnMsg.xxTotTaxAmt.setInputProtected(true);
                scrnMsg.xxTotAmt.setInputProtected(true);
                scrnMsg.xxTotBaseAmt_LN.setInputProtected(true);
                scrnMsg.xxTotBaseAmt_MT.setInputProtected(true);
                scrnMsg.xxTotBaseAmt_RT.setInputProtected(true);
            }
        }
    }

    private static void setItemConditionHeaderTabForEOPS(NWAL2200BMsg scrnMsg) {

        List<String> emsdImptStsCdList = new ArrayList<String>();
        emsdImptStsCdList = getEmsdImptStsCdList(scrnMsg, emsdImptStsCdList);

        if (emsdImptStsCdList.size() > 0) {
            if (emsdImptStsCdList.contains(IMPT_STS.SUCCESS)) {

                scrnMsg.billToCustAcctNm.setInputProtected(true);
                scrnMsg.billToCustAcctNm_LK.setInputProtected(true);
                scrnMsg.billToCustAcctCd.setInputProtected(true);
                scrnMsg.billToCustAcctCd_LK.setInputProtected(true);
                scrnMsg.xxAllLineAddr_BT.setInputProtected(true);
                scrnMsg.billToCustCd.setInputProtected(true);
                scrnMsg.billToCustCd_LK.setInputProtected(true);
                scrnMsg.shipToCustAcctNm.setInputProtected(true);
                scrnMsg.shipToCustAcctNm_LK.setInputProtected(true);
                scrnMsg.dropShipFlg_LK.setInputProtected(true);
                scrnMsg.shipToCustAcctCd.setInputProtected(true);
                scrnMsg.shipToCustAcctCd_LK.setInputProtected(true);
                scrnMsg.xxAllLineAddr_SH.setInputProtected(true);
                scrnMsg.shipToCustCd.setInputProtected(true);
                scrnMsg.shipToCustCd_LK.setInputProtected(true);
                scrnMsg.soldToCustAcctNm.setInputProtected(true);
                scrnMsg.soldToCustAcctNm_LK.setInputProtected(true);
                scrnMsg.sellToCustCd.setInputProtected(true);
                scrnMsg.sellToCustCd_LK.setInputProtected(true);
                scrnMsg.xxAllLineAddr_SE.setInputProtected(true);
                scrnMsg.soldToCustLocCd.setInputProtected(true);
                scrnMsg.soldToCustLocCd_LK.setInputProtected(true);
                scrnMsg.negoDealAmt.setInputProtected(true);
                scrnMsg.slsRepTocNm.setInputProtected(true);
                scrnMsg.slsRepPsnNum_LK.setInputProtected(true);
                scrnMsg.slsRepPsnNum.setInputProtected(true);
                scrnMsg.xxScrItem54Txt_CB.setInputProtected(true);
                scrnMsg.xxScrItem54Txt_CE.setInputProtected(true);
                scrnMsg.prcCatgNm_LK.setInputProtected(true);
                scrnMsg.prcCatgNm.setInputProtected(true);
                scrnMsg.custIssPoNum.setInputProtected(true);
                scrnMsg.leaseCmpyPoNum.setInputProtected(true);
                scrnMsg.custIssPoDt.setInputProtected(true);
                scrnMsg.aquNum.setInputProtected(true);
                scrnMsg.ordLogTpCd.setInputProtected(true);
                scrnMsg.xxCratDt.setInputProtected(true);
                scrnMsg.invCmntTxt.setInputProtected(true);
            }
        }
    }

    private static void setItemConditionAddlTabForEOPS(NWAL2200BMsg scrnMsg) {

        List<String> emsdImptStsCdList = new ArrayList<String>();
        emsdImptStsCdList = getEmsdImptStsCdList(scrnMsg, emsdImptStsCdList);

        if (emsdImptStsCdList.size() > 0) {
            if (emsdImptStsCdList.contains(IMPT_STS.SUCCESS)) {

                scrnMsg.frtCondDescTxt.setInputProtected(true);
                scrnMsg.frtCondDescTxt_LK.setInputProtected(true);
                scrnMsg.spclHdlgTpCd.setInputProtected(true);
                scrnMsg.shpgSvcLvlCd.setInputProtected(true);
                scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
                scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(true);
                scrnMsg.carrAcctNum.setInputProtected(true);
                scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
                scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(true);
                scrnMsg.dsPmtMethCd.setInputProtected(true);
                scrnMsg.prePmtChkNum.setInputProtected(true);
                scrnMsg.prePmtAmt.setInputProtected(true);
                scrnMsg.prePmtTpCd.setInputProtected(true);
                scrnMsg.rddDt.setInputProtected(true);
                scrnMsg.prcContrNum.setInputProtected(true);
                scrnMsg.flPrcListDescTxt.setInputProtected(true);
                scrnMsg.flPrcListDescTxt_LK.setInputProtected(true);
                scrnMsg.loanPerDaysAot.setInputProtected(true);
                scrnMsg.csmpContrNum.setInputProtected(true);
                scrnMsg.csmpContrNum_LK.setInputProtected(true);
                scrnMsg.dlrRefNum.setInputProtected(true);
                scrnMsg.dlrRefNum_LK.setInputProtected(true);
                scrnMsg.ordSgnDt.setInputProtected(true);
                scrnMsg.dclnSvcCd.setInputProtected(true);
                scrnMsg.leasePrchOptCd.setInputProtected(true);
                scrnMsg.leaseTermMthAot.setInputProtected(true);
                scrnMsg.leasePmtFreqCd.setInputProtected(true);
                scrnMsg.leaseTermMthAot_EM.setInputProtected(true);
                scrnMsg.psnNum_GS.setInputProtected(true);
                scrnMsg.tocNm_GS.setInputProtected(true);
                scrnMsg.coaExtnCd_GS.setInputProtected(true);
                scrnMsg.coaExtnDescTxt_GS.setInputProtected(true);
                scrnMsg.coaBrCd_GS.setInputProtected(true);
                scrnMsg.coaBrDescTxt_GS.setInputProtected(true);
                scrnMsg.coaCcCd_GS.setInputProtected(true);
                scrnMsg.coaCcDescTxt_GS.setInputProtected(true);
            }
        }
    }

    private static void setItemConditionConfigTabForEOPS(NWAL2200BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWAL2200_ABMsg row = scrnMsg.A.no(i);
            String imptStsCd = row.emsdImptStsCd_LC.getValue();

            if (imptStsCd != null && (IMPT_STS.REJECT.equals(imptStsCd) || IMPT_STS.SUCCESS.equals(imptStsCd))) {
                row.xxChkBox_LC.setInputProtected(true);
                row.dsOrdPosnNum_LC.setInputProtected(true);
                row.configTpDescTxt_LC.setInputProtected(true);
                row.svcConfigMstrPk_LC.setInputProtected(true);
                row.t_MdlNm_LC.setInputProtected(true);
                row.addrLbTxt_LC.setInputProtected(true);
                row.shipToCustLocCd_LC.setInputProtected(true);
                row.xxImageTxt_AS.setInputProtected(true);
                row.dropShipFlg_LC.setInputProtected(true);
                row.xxImageTxt_AD.setInputProtected(true);
                row.billToCustLocCd_LC.setInputProtected(true);
                row.xxImageTxt_AB.setInputProtected(true);
                row.dclnSvcCd_LC.setInputProtected(true);
                row.xxAllLineAddr_LB.setInputProtected(true);
                row.xxAllLineAddr_LS.setInputProtected(true);

                for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {

                    NWAL2200_BBMsg lineRow = scrnMsg.B.no(j);
                    if (ZYPCommonFunc.hasValue(lineRow.dsImptOrdConfigPk_LL.getValue())) {
                        if (row.dsImptOrdConfigPk_LC.getValue().equals(lineRow.dsImptOrdConfigPk_LL.getValue())) {
                            lineRow.xxChkBox_LL.setInputProtected(true);
                            lineRow.xxLineNum_LL.setInputProtected(true);
                            lineRow.xxScrItem130Txt_LL.setInputProtected(true);
                            lineRow.mdseCd_LL.setInputProtected(true);
                            lineRow.mdseDescShortTxt_LL.setInputProtected(true);
                            lineRow.ordCustUomQty_LL.setInputProtected(true);
                            lineRow.pkgUomDescTxt_LL.setInputProtected(true);
                            lineRow.ordQty_LL.setInputProtected(true);
                            lineRow.cpoDtlDealSlsAmt_LL.setInputProtected(true);
                            lineRow.entDealNetUnitPrcAmt_LL.setInputProtected(true);
                            lineRow.prcCatgNm_LL.setInputProtected(true);
                            lineRow.xxImageTxt_BP.setInputProtected(true);
                            lineRow.prcListEquipConfigNum_LL.setInputProtected(true);
                            lineRow.dsOrdLineCatgCd_LL.setInputProtected(true);
                            lineRow.ordLineSrcCd_LL.setInputProtected(true);
                            lineRow.rtlWhNm_LL.setInputProtected(true);
                            lineRow.xxImageTxt_BW.setInputProtected(true);
                            lineRow.rtlSwhNm_LL.setInputProtected(true);
                            lineRow.xxImageTxt_BS.setInputProtected(true);
                            lineRow.serNum_LL.setInputProtected(true);
                            lineRow.flPrcListDescTxt_LL.setInputProtected(true);
                            lineRow.xxImageTxt_BF.setInputProtected(true);
                            lineRow.dealPrcListPrcAmt_LL.setInputProtected(true);
                            lineRow.lineDealSubTotAmt_LL.setInputProtected(true);
                            lineRow.coaMdseTpDescTxt_LL.setInputProtected(true);
                            lineRow.coaProdDescTxt_LL.setInputProtected(true);
                            lineRow.dplyLineRefNum_LL.setInputProtected(true);
                            lineRow.prcBaseDt_LL.setInputProtected(true);
                            lineRow.custMdseCd_LL.setInputProtected(true);
                            lineRow.rddDt_LL.setInputProtected(true);
                            lineRow.imptLineFlg_LL.setInputProtected(true);
                            lineRow.finItemLineFlg_LL.setInputProtected(true);
                        }
                    }
                }
            }
        }
    }

    private static void setItemConditionRmaTabForEOPS(NWAL2200BMsg scrnMsg) {

        // RMA Tab
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

            NWAL2200_CBMsg row = scrnMsg.C.no(i);
            String imptStsCd = row.emsdImptStsCd_RC.getValue();

            if (imptStsCd != null && (IMPT_STS.REJECT.equals(imptStsCd) || IMPT_STS.SUCCESS.equals(imptStsCd))) {
                row.xxChkBox_RC.setInputProtected(true);
                row.dsOrdPosnNum_RC.setInputProtected(true);
                row.configTpDescTxt_RC.setInputProtected(true);
                row.svcConfigMstrPk_RC.setInputProtected(true);
                row.t_MdlNm_RC.setInputProtected(true);
                row.addrLbTxt_RC.setInputProtected(true);
                row.shipToCustLocCd_RC.setInputProtected(true);
                row.xxImageTxt_CS.setInputProtected(true);
                row.dropShipFlg_RC.setInputProtected(true);
                row.xxImageTxt_CD.setInputProtected(true);
                row.billToCustLocCd_RC.setInputProtected(true);
                row.xxImageTxt_CB.setInputProtected(true);
                row.xxAllLineAddr_RB.setInputProtected(true);
                row.xxAllLineAddr_RS.setInputProtected(true);

                for (int j = 0; j < scrnMsg.D.getValidCount(); j++) {

                    NWAL2200_DBMsg lineRow = scrnMsg.D.no(j);
                    if (ZYPCommonFunc.hasValue(lineRow.dsImptOrdConfigPk_RL.getValue())) {
                        if (row.dsImptOrdConfigPk_RC.getValue().equals(lineRow.dsImptOrdConfigPk_RL.getValue())) {
                            lineRow.xxChkBox_RL.setInputProtected(true);
                            lineRow.xxLineNum_RL.setInputProtected(true);
                            lineRow.xxScrItem130Txt_RL.setInputProtected(true);
                            lineRow.mdseCd_RL.setInputProtected(true);
                            lineRow.mdseDescShortTxt_RL.setInputProtected(true);
                            lineRow.ordCustUomQty_RL.setInputProtected(true);
                            lineRow.pkgUomDescTxt_RL.setInputProtected(true);
                            lineRow.ordQty_RL.setInputProtected(true);
                            lineRow.cpoDtlDealSlsAmt_RL.setInputProtected(true);
                            lineRow.entDealNetUnitPrcAmt_RL.setInputProtected(true);
                            lineRow.prcCatgNm_RL.setInputProtected(true);
                            lineRow.xxImageTxt_DP.setInputProtected(true);
                            lineRow.dsCpoLineCatgCd_RL.setInputProtected(true);
                            lineRow.ordLineSrcCd_RL.setInputProtected(true);
                            lineRow.rtlWhNm_RL.setInputProtected(true);
                            lineRow.xxImageTxt_DW.setInputProtected(true);
                            lineRow.rtlSwhNm_RL.setInputProtected(true);
                            lineRow.serNum_RL.setInputProtected(true);
                            lineRow.flPrcListDescTxt_RL.setInputProtected(true);
                            lineRow.xxImageTxt_DF.setInputProtected(true);
                            lineRow.dealPrcListPrcAmt_RL.setInputProtected(true);
                            lineRow.lineDealSubTotAmt_RL.setInputProtected(true);
                            lineRow.coaMdseTpDescTxt_RL.setInputProtected(true);
                            lineRow.coaProdDescTxt_RL.setInputProtected(true);
                            lineRow.dplyLineRefNum_RL.setInputProtected(true);
                            lineRow.prcBaseDt_RL.setInputProtected(true);
                            lineRow.custMdseCd_RL.setInputProtected(true);
                            lineRow.rqstPickUpDt_RL.setInputProtected(true);
                            lineRow.rtrnRsnCd_RL.setInputProtected(true);
                            lineRow.hddRmvCd_RL.setInputProtected(true);
                        }
                    }
                }
            }
        }
    }

    private static List<String> getEmsdImptStsCdList(NWAL2200BMsg scrnMsg, List<String> emsdImptStsCdList) {

        // Config
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            emsdImptStsCdList.add(scrnMsg.A.no(i).emsdImptStsCd_LC.getValue());
        }

        // RMA
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            emsdImptStsCdList.add(scrnMsg.C.no(i).emsdImptStsCd_RC.getValue());
        }

        return emsdImptStsCdList;
    }
    // 2018/01/23 QC#18798 Add End

    private static void setButtonCondition(S21CommonHandler handler, Map<String, Object[]> buttonProperties, List<String> funcList, String imptStsCd, String cpoSrcTpCd) {

        if (buttonProperties == null) {
            return;
        }
        Iterator<Map.Entry<String, Object[]>> entries = buttonProperties.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, Object[]> itemProperty = entries.next();
            String item = itemProperty.getKey();
            if (!buttonEditable(itemProperty.getValue())) {
                handler.setButtonEnabled(item, false);
                continue;
            }
            handler.setButtonEnabled(item, true);
            if (!isButtonInitialEnable(itemProperty.getValue())) {
                if (S21StringUtil.isEmpty(imptStsCd)) {
                    handler.setButtonEnabled(item, false);
                    continue;
                }
            } else {
                if (S21StringUtil.isNotEmpty(imptStsCd)) {
                    handler.setButtonEnabled(item, false);
                    continue;
                }
            }
            if (!validButtonStatus(itemProperty.getValue(), imptStsCd)) {
                handler.setButtonEnabled(item, false);
                continue;
            }
            if (!validButtonSourceType(itemProperty.getValue(), cpoSrcTpCd)) {
                handler.setButtonEnabled(item, false);
                continue;
            }
            if (!hasButtonFunction(itemProperty.getValue(), funcList)) {
                handler.setButtonEnabled(item, false);
                continue;
            }
        }
    }

    private static boolean validStatus(Object[] value, String imptStsCd) {

        if (getStatus(value).size() == 0) {
            return true;
        } else {
            return getStatus(value).contains(imptStsCd);
        }
    }

    private static boolean hasFunction(Object[] value, List<String> funcList) {

        List<String> functionIdList = functionId(value);
        for (String functionId : functionIdList) {
            if (funcList.contains(functionId)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMessageTarget(Object[] value, String messageCode) {

        if (getMessageCode(value).size() == 0) {
            return false;
        } else {
            return getMessageCode(value).contains(messageCode);
        }
    }

    private static boolean validButtonStatus(Object[] value, String imptStsCd) {

        if (getButtonStatus(value).size() == 0) {
            return true;
        } else {
            return getButtonStatus(value).contains(imptStsCd);
        }
    }

    private static boolean validButtonSourceType(Object[] value, String cpoSrcTpCd) {

        if (getButtonSourceType(value).size() == 0) {
            return true;
        } else {
            return getButtonSourceType(value).contains(cpoSrcTpCd);
        }
    }

    private static boolean hasButtonFunction(Object[] value, List<String> funcList) {

        List<String> functionIdList = buttonFunctionId(value);
        for (Object functionId : functionIdList) {
            if (funcList.contains(functionId)) {
                return true;
            }
        }
        return false;
    }

    private static String getNameForMessage(Object[] value) {
        return (String) value[ITEM_PROPERTY_NAME];
    }

    private static boolean editable(Object[] value) {
        return (Boolean) value[ITEM_PROPERTY_EDITABLE];
    }

    private static boolean isAmount(Object[] value) {
        return (Boolean) value[ITEM_PROPERTY_AMOUNT];
    }

    private static boolean isLink(Object[] value) {
        return (Boolean) value[ITEM_PROPERTY_LINK];
    }

    private static List<String> functionId(Object[] value) {

        if (value[ITEM_PROPERTY_FUNCTION_ID] instanceof List) {
            return (List<String>) value[ITEM_PROPERTY_FUNCTION_ID];
        } else {
            return new ArrayList<String>();
        }
    }

    private static boolean isInitialEnable(Object[] value) {
        return (Boolean) value[ITEM_PROPERTY_INITIAL_ENABLE];
    }

    private static List<String> getStatus(Object[] value) {
        if (value[ITEM_PROPERTY_STATUS] instanceof List) {
            return (List<String>) value[ITEM_PROPERTY_STATUS];
        } else {
            return new ArrayList<String>();
        }
    }

    private static List<String> getMessageCode(Object[] value) {
        if (value[ITEM_PROPERTY_MESSAGE_CODE] instanceof List) {
            return (List<String>) value[ITEM_PROPERTY_MESSAGE_CODE];
        } else {
            return new ArrayList<String>();
        }
    }

    private static boolean isForceEditable(Object[] value) {
        return (Boolean) value[ITEM_PROPERTY_FORCE_EDITABLE];
    }

    private static boolean buttonEditable(Object[] value) {
        return (Boolean) value[BUTTON_PROPERTY_EDITABLE];
    }

    private static List<String> buttonFunctionId(Object[] value) {

        if (value[BUTTON_PROPERTY_FUNCTION_ID] instanceof List) {
            return (List<String>) value[BUTTON_PROPERTY_FUNCTION_ID];
        } else {
            return new ArrayList<String>();
        }
    }

    private static boolean isButtonInitialEnable(Object[] value) {
        return (Boolean) value[BUTTON_PROPERTY_INITIAL_ENABLE];
    }

    private static List<String> getButtonStatus(Object[] value) {
        if (value[BUTTON_PROPERTY_STATUS] instanceof List) {
            return (List<String>) value[BUTTON_PROPERTY_STATUS];
        } else {
            return new ArrayList<String>();
        }
    }

    private static List<String> getButtonSourceType(Object[] value) {
        if (value[BUTTON_PROPERTY_SRC_TP] instanceof List) {
            return (List<String>) value[BUTTON_PROPERTY_SRC_TP];
        } else {
            return new ArrayList<String>();
        }
    }

    // 2018/01/23 QC#18798 Add Start
    public static void setProtectByOrdCatgBizCtx(EZDCommonHandler handler, NWAL2200BMsg scrnMsg) {

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_LD.getValue())) {
            scrnMsg.leasePrchOptCd.clear();
            scrnMsg.leaseTermMthAot.clear();
            scrnMsg.leasePmtFreqCd.clear();
        }

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_EM.getValue())) {
            scrnMsg.leaseTermMthAot_EM.clear();
        }

        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_GS.getValue())) {
            scrnMsg.psnNum_GS.clear();
            scrnMsg.tocNm_GS.clear();
            scrnMsg.coaExtnCd_GS.clear();
            scrnMsg.coaExtnDescTxt_GS.clear();
            scrnMsg.coaBrCd_GS.clear();
            scrnMsg.coaBrDescTxt_GS.clear();
            scrnMsg.coaCcCd_GS.clear();
            scrnMsg.coaCcDescTxt_GS.clear();
        }
    }
    // 2018/01/23 QC#18798 Add End
}
