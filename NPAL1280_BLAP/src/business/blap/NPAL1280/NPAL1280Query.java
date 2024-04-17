/**
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1280;

import static business.blap.NPAL1280.constant.NPAL1280Constant.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.file.NPAL1280F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS         K.Ogino            Create          N/A
 * 02/29/2016   CITS         K.Ogino            Update          QC#4636
 * 03/01/2016   CITS         K.Ogino            Update          QC#4636
 * 03/03/2016   CITS         K.Ogino            Update          QC#4981
 * 03/22/2016   CITS         K.Ogino            Update          QC#5824
 * 05/26/2016   CITS         K.Ogino            Update          QC#8939
 * 11/02/2016   CITS         Y.IWASAKI          Update          QC#15072
 * 03/01/2017   CITS         Y.IWASAKI          Update          QC#17487
 * 08/02/2017   CITS         R.Shimamoto        Update          QC#18200
 * 08/03/2017   CITS         R.Shimamoto        Update          QC#18671
 * 08/23/2017   CITS         H.Naoi             Update          Sol#097(QC#18398)
 * 12/19/2017   CITS         K.Kameoka          Update          Sol#060(QC#14858)
 * 04/04/2018   CITS         T.Wada             Update          QC#21170
 * 04/20/2018   CITS         Y.Iwasaki          Update          QC#25019
 * 07/03/2018   CITS         T.Tokutomi         Update          QC#23726
 * 07/20/2018   CITS         K.Kameoka          Update          QC#26990
 * 08/20/2018   CITS         K.Ogino            Update          QC#27846
 * 08/23/2018   CITS         K.Ogino            Update          QC#27911
 * 10/31/2018   CITS         K.Kameoka          Update          QC#28941
 * 11/15/2018   CITS         K.Ogino            Update          QC#29254,QC#29255
 * 12/17/2018   Fujitsu      S.Takami           Update          QC#29397
 * 01/11/2019   CITS         T.Tokutomi         Update          QC#28709
 * 10/04/2019   CITS         R.Shimamoto        Update          QC#53300
 * 2022/10/31   Hitachi      N.Takatsu          Update          QC#60604
 * 2022/11/18   Hitachi      M.Kikushima        Update          QC#60605
 * 2022/12/15   CITS         F.Fadriquela       Update          QC#60917
 * 2023/01/06   Hitachi      M.Kikushima        Update          QC#60605
 * 2023/02/06   Hitachi      T.Kuroda           Update          QC#60966
 * 2024/03/04   CITS         S.Okamoto          Update          QC#62443
 *</pre>
 */
public final class NPAL1280Query extends S21SsmEZDQuerySupport {

    /**
     * MY_INSTANCE
     */
    private static final NPAL1280Query MY_INSTANCE = new NPAL1280Query();

    /**
     * constructor
     */
    private NPAL1280Query() {
        super();
    }

    /**
     * @return NPAL1510Query
     */
    public static NPAL1280Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get S21SsmBatchClient for CSV Download
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Requistion pulldown query
     * @param glblCmpyCd String
     * @param scrEntAvalFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRequisitionList(String glblCmpyCd, String scrEntAvalFlg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);
        params.put(BIND_SCR_ENT_AVAL_FLG, scrEntAvalFlg);
        return getSsmEZDClient().queryObjectList("getRequisitionList", params);
    }

    /**
     * Business unit pulldown query
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBusinessUnitList(String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getBusinessUnitList", params);
    }

    /**
     * PR line type pulldown query
     * @param glblCmpyCd String
     * @param scrEntAvalFlg String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrLineTypeList(String glblCmpyCd, String scrEntAvalFlg) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.PO_REQUISITION);
        params.put(BIND_SCR_ENT_AVAL_FLG, scrEntAvalFlg);
        return getSsmEZDClient().queryObjectList("getPrLineTypeList", params);
    }

    /**
     * Return to address query
     * @param glblCmpyCd String
     * @param shipToStCd String
     * @param fromShipToPostCd String
     * @param toShipToPostCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getReturnToAddress(String glblCmpyCd, String shipToStCd, String fromShipToPostCd, String toShipToPostCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_SHIP_TO_ST_CD, shipToStCd);
        params.put(BIND_FROM_SHIP_TP_POST_CD, fromShipToPostCd);
        params.put(BIND_TO_SHIP_TP_POST_CD, toShipToPostCd);
        return getSsmEZDClient().queryObjectList("getReturnToAddress", params);
    }

    /**
     * Default return to address query
     * @param glblCmpyCd String
     * @param shipToStCd String
     * @param fromShipToPostCd String
     * @param toShipToPostCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultReturnToAddress(String glblCmpyCd, String shipToStCd, String fromShipToPostCd, String toShipToPostCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_SHIP_TO_ST_CD, shipToStCd);
        params.put(BIND_FROM_SHIP_TP_POST_CD, fromShipToPostCd);
        params.put(BIND_TO_SHIP_TP_POST_CD, toShipToPostCd);
        return getSsmEZDClient().queryObjectList("getDefaultReturnToAddress", params);
    }

    /**
     * Default Return to whrehouse query
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefaultReturnToWh(String glblCmpyCd, String rtlWhCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_RTL_WH_CD, rtlWhCd);
        return getSsmEZDClient().queryObjectList("getDefaultReturnToWh", params);
    }

    // QC#14858(Sol#060) Start
    /**
     * Default account info query
     * @param glblCmpyCd String
     * @param appFuncId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefAccInfo(String glblCmpyCd, NPAL1280CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        params.put(BIND_LINE_BIZ_TP_CD, cMsg.prchGrpCd_SL.getValue());
        params.put(BIND_SHIP_TO_CUST_CD, cMsg.destRtlWhCd.getValue()); //shipToCustCd<=destRtlWhCd
        return getSsmEZDClient().queryObject("getDefAccInfo", params);
    }
    // QC#14858(Sol#060) End

    /**
     * PO Message info query
     * @param glblCmpyCd String
     * @param prchReqNum String
     * @param poMsgTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoMsg(String glblCmpyCd, String prchReqNum, String poMsgTpCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRCH_REQ_NUM, prchReqNum);
        params.put(BIND_PO_MSG_TP_CD, poMsgTpCd);
        return getSsmEZDClient().queryObjectList("getPoMsg", params);
    }

    /**
     * Bill to location query
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToLocation(String glblCmpyCd, String billToCustCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_BILL_TO_CUST_CD, billToCustCd);
        return getSsmEZDClient().queryObjectList("getBillToLocation", params);
    }

    /**
     * Check parente vendar code query
     * @param glblCmpyCd String
     * @param prntVndCd String
     * @param prntVndNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkPrntVndCd(String glblCmpyCd, String prntVndCd, String prntVndNm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRNT_VND_CD, prntVndCd);
        params.put(BIND_PRNT_VND_NM, prntVndNm);
        return getSsmEZDClient().queryObjectList("chkPrntVndCd", params);
    }

    /**
     * Check vender code query
     * @param glblCmpyCd String
     * @param vndCd String
     * @param locNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkVndCd(String glblCmpyCd, String vndCd, String locNm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_VND_CD, vndCd);
        params.put(BIND_LOC_NM, locNm);
        return getSsmEZDClient().queryObjectList("chkVndCd", params);
    }

    /**
     * Check po vender query
     * @param glblCmpyCd String
     * @param prntVndCd String
     * @param vndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkPoVnd(String glblCmpyCd, String prntVndCd, String vndCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRNT_VND_CD, prntVndCd);
        params.put(BIND_VND_CD, vndCd);
        return getSsmEZDClient().queryObjectList("chkPoVnd", params);
    }

    /**
     * Check item aval flg query
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param destWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkPrchAvalFlg(String glblCmpyCd, String mdseCd, String destWhCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_MDSE_CD, mdseCd);
        params.put(BIND_PO_ACCT_TP_CD, PO_ACCT_TP.ACCRUAL);
        if (ZYPCommonFunc.hasValue(destWhCd)) {
            params.put(BIND_DEST_WH_CD, destWhCd);
        }
        params.put(BIND_ROW_NUM, "1");
        return getSsmEZDClient().queryObjectList("chkPrchAvalFlg", params);
    }

    /**
     * Item component find query
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param salesDate String
     * @param prchReqLineTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getComponent(String glblCmpyCd, String mdseCd, String salesDate, String prchReqLineTpCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_MDSE_CD, mdseCd);
        params.put(BIND_SALES_DATE, salesDate);
        params.put(BIND_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put(BIND_PRCH_REQ_LINE_TP_CD , prchReqLineTpCd);
        return getSsmEZDClient().queryObjectList("getComponent", params);
    }

    /**
     * Asl detail find query
     * @param glblCmpyCd String
     * @param vndCd String
     * @param mdseCd String
     * @param salesDate String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findAslDtl(String glblCmpyCd, String vndCd, String mdseCd, String salesDate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_VND_CD, vndCd);
        params.put(BIND_MDSE_CD, mdseCd);
        params.put(BIND_SALES_DATE, salesDate);
        return getSsmEZDClient().queryObjectList("findAslDtl", params);
    }

    /**
     * Preferd carrier query
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPreferedCarrier(String glblCmpyCd, String rtlWhCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_RTL_WH_CD, rtlWhCd);
        return getSsmEZDClient().queryObjectList("getPreferedCarrier", params);
    }

    /**
     * Max PR line number query
     * @param prchReqNum String
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxPrLineNum(String prchReqNum, String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRCH_REQ_NUM, prchReqNum);
        return getSsmEZDClient().queryObjectList("getMaxPrLineNum", params);
    }

    /**
     * Contact person query
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param dsCpoConfigPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactPerson(String glblCmpyCd, String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_CPO_ORD_NUM, cpoOrdNum);
        params.put(BIND_DS_CPO_CONFIG_PK, dsCpoConfigPk);
        return getSsmEZDClient().queryObjectList("getContactPerson", params);
    }

    /**
     * Check frt condision releation query
     * @param glblCmpyCd String
     * @param frtCondCd String
     * @param shpgSvcLvlCd String
     * @param carrSvcLvlCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkFrtCondSvcLvlReln(String glblCmpyCd, String frtCondCd, String shpgSvcLvlCd, String carrSvcLvlCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_LINE_BIZ_TP_CD, LINE_BIZ_TP.ALL);
        params.put(BIND_FRT_COND_CD, frtCondCd);
        params.put(BIND_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
        // QC#18200 Mod.
        if (!ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            params.put(BIND_CARR_SVC_LVL_CD, ASTARISK);
        } else {
            params.put(BIND_CARR_SVC_LVL_CD, carrSvcLvlCd);
        }
        return getSsmEZDClient().queryObjectList("chkFrtCondSvcLvlReln", params);
    }

    /**
     * Vendor name query
     * @param glblCmpyCd String
     * @param vndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVendorName(String glblCmpyCd, String vndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_VND_CD, vndCd);
        return getSsmEZDClient().queryObjectList("getVendorName", params);
    }

    /**
     * Vendor name query
     * @param glblCmpyCd String
     * @param prntVndCd String
     * @param prntVndNm String
     * @param vndCd String
     * @param vndNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVendorName(String glblCmpyCd, String prntVndCd, String prntVndNm, String vndCd, String vndNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRNT_VND_CD, prntVndCd);
        params.put(BIND_PRNT_VND_NM, prntVndNm);
        params.put(BIND_VND_CD, vndCd);
        params.put(BIND_VND_NM, vndNm);
        return getSsmEZDClient().queryObjectList("getVendorName", params);
    }

    /**
     * find cpo number list
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param coaProjAcctTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findCpoNumberList(NPAL1280CMsg cMsg, String glblCmpyCd, String coaProjAcctTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_COA_CMPY_CD, cMsg.coaCmpyCd_GO.getValue());
        params.put(BIND_COA_AFFL_CD, cMsg.coaAfflCd_GO.getValue());
        params.put(BIND_COA_CC_CD, cMsg.coaCcCd_GO.getValue());
        params.put(BIND_COA_PROD_CD, cMsg.coaProdCd_GO.getValue());
        params.put(BIND_COA_CH_CD, cMsg.coaChCd_GO.getValue());
        params.put(BIND_COA_PROJ_CD, cMsg.coaProjCd_GO.getValue());
        params.put(BIND_COA_EXTN_CD, cMsg.coaExtnCd_GO.getValue());
        params.put(BIND_CPO_ORD_NUM, cMsg.cpoOrdNum.getValue());
        params.put(BIND_PRNT_CMPY_SET_MDSE_FLG, ZYPConstant.FLG_OFF_N);
        params.put(BIND_COA_PROJ_ACCT_TP_CD, coaProjAcctTpCd);
        // START 2022/11/18 M.Kikushima[QC#60605, MOD]
        params.put(BIND_COA_MDSE_TP_CD, COA_MDSE_TP.PROF_SERVICES);
        params.put(BIND_INVTY_CTRL_FLG, ZYPConstant.FLG_OFF_N);
        params.put(BIND_LINE_TP_EXPENSE, PRCH_REQ_LINE_TP.EXPENSE);
        params.put(BIND_LINE_TP_GOODS, PRCH_REQ_LINE_TP.GOODS);
        // END 2022/11/18 M.Kikushima[QC#60605, MOD]
        // START 2023/01/06 M.Kikushima[QC#60605, ADD]
        params.put(BIND_PRNT_CMPY_SET_MDSE_FLG_Y, ZYPConstant.FLG_ON_Y);
        // END 2023/01/06 M.Kikushima[QC#60605, ADD]
        // QC#27846
        if (ZYPCommonFunc.hasValue(cMsg.dsOrdPosnNum)) {
            params.put(BIND_DS_ORD_POSN_NUM, cMsg.dsOrdPosnNum.getValue());
        }
        return getSsmEZDClient().queryObjectList("findCpoNumberList", params);
    }

    /**
     * find set item list. Mod QC#29255
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param cpoDtlLineNum String
     * @param cpoDtlLineSubNum cpoDtlLineSubNum
     * @param coaProjAcctTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSetItemList(NPAL1280CMsg cMsg, String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum, String coaProjAcctTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_COA_CMPY_CD, cMsg.coaCmpyCd_GO.getValue());
        params.put(BIND_COA_AFFL_CD, cMsg.coaAfflCd_GO.getValue());
        params.put(BIND_COA_CC_CD, cMsg.coaCcCd_GO.getValue());
        params.put(BIND_COA_PROD_CD, cMsg.coaProdCd_GO.getValue());
        params.put(BIND_COA_CH_CD, cMsg.coaChCd_GO.getValue());
        params.put(BIND_COA_PROJ_CD, cMsg.coaProjCd_GO.getValue());
        params.put(BIND_COA_EXTN_CD, cMsg.coaExtnCd_GO.getValue());
        params.put(BIND_CPO_ORD_NUM, cpoOrdNum);
        params.put(BIND_CPO_DTL_LINE_NUM, cpoDtlLineNum);
        params.put(BIND_CPO_DTL_LINE_SUB_NUM, cpoDtlLineSubNum);
        params.put(BIND_COA_PROJ_ACCT_TP_CD, coaProjAcctTpCd);
        params.put("CPO_ROW_NUMBER", BigDecimal.ZERO);
        return getSsmEZDClient().queryObjectList("findSetItemList", params);
    }

    /**
     * find po req list
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPoReqList(NPAL1280CMsg cMsg, String glblCmpyCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_COA_CMPY_CD, cMsg.coaCmpyCd_GO.getValue());
        params.put(BIND_COA_AFFL_CD, cMsg.coaAfflCd_GO.getValue());
        params.put(BIND_COA_CC_CD, cMsg.coaCcCd_GO.getValue());
        params.put(BIND_COA_PROD_CD, cMsg.coaProdCd_GO.getValue());
        params.put(BIND_COA_CH_CD, cMsg.coaChCd_GO.getValue());
        params.put(BIND_COA_PROJ_CD, cMsg.coaProjCd_GO.getValue());
        params.put(BIND_COA_EXTN_CD, cMsg.coaExtnCd_GO.getValue());
        params.put(BIND_PRCH_REQ_NUM, cMsg.prchReqNum.getValue());
        params.put(BIND_PRCH_REQ_LINE_TO_CD_GOODS, PRCH_REQ_LINE_TP.GOODS);
        params.put(BIND_PRCH_REQ_LINE_TO_CD_ASSETS, PRCH_REQ_LINE_TP.EXPENSE);
        params.put(BIND_COA_PROJ_ACCT_TP_CD, ZYPCodeDataUtil.getVarCharConstValue(PO_ACRL_ACCT_TP, glblCmpyCd));
        params.put(BIND_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);
        params.put(BIND_ROW_NUM, rowNum);
        params.put(BIND_PO_ACCT_TP_CD, PO_ACCT_TP.ACCRUAL);
        // QC#28939 Add param.
        params.put(BIND_MDSE_ITEM_TP_TEXTITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // QC#53271 Add Start
        params.put("prchReqLineTpCdExpenseW", PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT);
        // QC#53271 Add End

        return getSsmEZDClient().queryObjectList("getPoReqList", params);
    }

    /**
     * CSV Download query
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    boolean getPoReqListForCsv(NPAL1280CMsg cMsg, String glblCmpyCd, int rowNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_COA_CMPY_CD, cMsg.coaCmpyCd_GO.getValue());
        params.put(BIND_COA_AFFL_CD, cMsg.coaAfflCd_GO.getValue());
        params.put(BIND_COA_CC_CD, cMsg.coaCcCd_GO.getValue());
        params.put(BIND_COA_PROD_CD, cMsg.coaProdCd_GO.getValue());
        params.put(BIND_COA_CH_CD, cMsg.coaChCd_GO.getValue());
        params.put(BIND_COA_PROJ_CD, cMsg.coaProjCd_GO.getValue());
        params.put(BIND_COA_EXTN_CD, cMsg.coaExtnCd_GO.getValue());
        params.put(BIND_PRCH_REQ_NUM, cMsg.prchReqNum.getValue());
        params.put(BIND_PRCH_REQ_LINE_TO_CD_GOODS, PRCH_REQ_LINE_TP.GOODS);
        params.put(BIND_PRCH_REQ_LINE_TO_CD_ASSETS, PRCH_REQ_LINE_TP.EXPENSE);
        params.put(BIND_COA_PROJ_ACCT_TP_CD, ZYPCodeDataUtil.getVarCharConstValue(PO_ACRL_ACCT_TP, glblCmpyCd));
        params.put(BIND_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);
        params.put(BIND_ROW_NUM, rowNum);
        params.put(BIND_PO_ACCT_TP_CD, PO_ACCT_TP.ACCRUAL);
        // QC#28939 Add param.
        params.put(BIND_MDSE_ITEM_TP_TEXTITEM, MDSE_ITEM_TP.TEXT_ITEM);
        // QC#53271 Add Start
        params.put("prchReqLineTpCdExpenseW", PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT);
        // QC#53271 Add End

        return (Boolean) getSsmBatchClient().queryObject("getPoReqList", params, new CsvCreator(cMsg));
    }

    /**
     * Create FMsg using ResultSet
     */
    private static class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** */
        private NPAL1280CMsg bizMsg;

        public CsvCreator(NPAL1280CMsg bizMsg) {
            this.bizMsg = bizMsg;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();
            int cnt = rs.getRow();

            if (cnt == 0) {
                bizMsg.setMessageInfo("NZZM0000E");
                return false;
            }

            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

            NPAL1280F00FMsg fMsg = new NPAL1280F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            String[] csvHeader = getCsvHdrColum();
            csvOutFile.writeHeader(csvHeader);

            rs.first();
            do {
                if (rs.getRow() >= MAX_DOWNLOAD_CNT) {
                    bizMsg.setMessageInfo("NZZM0001W", null);
                    break;
                }
                setCsvData(fMsg, rs);
                csvOutFile.write();
            } while (rs.next());
            csvOutFile.close();
            return true;
        }

        private String[] getCsvHdrColum() {
            List<String> csvHeaderList = new ArrayList<String>();
            csvHeaderList.add(CSV_HDR_H1);
            csvHeaderList.add(CSV_HDR_H2);
            csvHeaderList.add(CSV_HDR_H3);
            csvHeaderList.add(CSV_HDR_H4);
            csvHeaderList.add(CSV_HDR_H24);
            csvHeaderList.add(CSV_HDR_H5);
            csvHeaderList.add(CSV_HDR_H6);
            // START 2023/02/06 T.Kuroda [QC#60966, ADD]
            csvHeaderList.add(CSV_HDR_H27);
            // END   2023/02/06 T.Kuroda [QC#60966, ADD]
            csvHeaderList.add(CSV_HDR_H7);
            csvHeaderList.add(CSV_HDR_H8);
            csvHeaderList.add(CSV_HDR_H9);
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            csvHeaderList.add(CSV_HDR_H26);
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            csvHeaderList.add(CSV_HDR_H10);
            csvHeaderList.add(CSV_HDR_H11);
            csvHeaderList.add(CSV_HDR_H12);
            //08/22/2017 CITS H.Naoi Chg Sol#097(QC#18398) START
            csvHeaderList.add(CSV_HDR_H13);
            //08/22/2017 CITS H.Naoi Chg Sol#097(QC#18398) END
            csvHeaderList.add(CSV_HDR_H14);
            csvHeaderList.add(CSV_HDR_H15);
            csvHeaderList.add(CSV_HDR_H16);
            csvHeaderList.add(CSV_HDR_H17);
            csvHeaderList.add(CSV_HDR_H18);
            csvHeaderList.add(CSV_HDR_H19);
            csvHeaderList.add(CSV_HDR_H20);
            csvHeaderList.add(CSV_HDR_H21);
            csvHeaderList.add(CSV_HDR_H22);
            csvHeaderList.add(CSV_HDR_H25);
            csvHeaderList.add(CSV_HDR_H23);
            csvHeaderList.add(CSV_HDR_2);
            csvHeaderList.add(CSV_HDR_3);
            csvHeaderList.add(CSV_HDR_4);
            csvHeaderList.add(CSV_HDR_5);
            csvHeaderList.add(CSV_HDR_6);
            csvHeaderList.add(CSV_HDR_7);
            csvHeaderList.add(CSV_HDR_8);
            csvHeaderList.add(CSV_HDR_9);
            csvHeaderList.add(CSV_HDR_10);
            csvHeaderList.add(CSV_HDR_11);
            csvHeaderList.add(CSV_HDR_12);
            csvHeaderList.add(CSV_HDR_13);
            csvHeaderList.add(CSV_HDR_14);
            csvHeaderList.add(CSV_HDR_15);
            csvHeaderList.add(CSV_HDR_16);
            csvHeaderList.add(CSV_HDR_17);
            csvHeaderList.add(CSV_HDR_18);
            csvHeaderList.add(CSV_HDR_19);
            csvHeaderList.add(CSV_HDR_20);
            csvHeaderList.add(CSV_HDR_21);
            csvHeaderList.add(CSV_HDR_22);
            csvHeaderList.add(CSV_HDR_23);
            csvHeaderList.add(CSV_HDR_24);
            String[] csvHeader = (String[]) csvHeaderList.toArray(new String[csvHeaderList.size()]);
            return csvHeader;
        }

        private void setCsvData(NPAL1280F00FMsg fMsg, ResultSet rs) throws SQLException {
            // header
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqNum, rs.getString(RS_PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqTpNm, rs.getString(RS_PRCH_REQ_TP_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqStsNm, rs.getString(RS_PRCH_REQ_STS_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqApvlStsNm, rs.getString(RS_PRCH_REQ_APVL_STS_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCratDt, rs.getString(RS_PRCH_REQ_CRAT_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvDt, rs.getString(RS_RQST_RCV_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.rqstRcvTm, rs.getString(RS_RQST_RCV_TM));
            // START 2023/02/06 T.Kuroda [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.rqstShipDt, rs.getString(RS_DTL_RQST_SHIP_DT));
            // END   2023/02/06 T.Kuroda [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqSrcTpNm, rs.getString(RS_PRCH_REQ_SRC_TP_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.trxRefNum, rs.getString(RS_TRX_REF_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.poQlfyCd, rs.getString(RS_PO_QLFY_CD));
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            ZYPEZDItemValueSetter.setValue(fMsg.mrpPlnNm, rs.getString(RS_MRP_PLN_NM));
            //08/22/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCratByNm, rs.getString(RS_PRCH_REQ_CRAT_BY_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqRqstByPsnCd, rs.getString(RS_PRCH_REQ_RQST_BY_PSN_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prchGrpCd, rs.getString(RS_PRCH_GRP_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqCmntTxt, rs.getString(RS_PRCH_REQ_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.spclInstnCmntTxt, rs.getString(RS_SPCL_INSTN_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlWhCd, rs.getString(RS_DEST_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString(RS_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.destRtlSwhCd, rs.getString(RS_DEST_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlSwhNm, rs.getString(RS_RTL_SWH_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.xxLocNm, rs.getString(RS_LOC_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.ccyCd, rs.getString(RS_CCY_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndCd, rs.getString(RS_PRNT_VND_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndNm_H, rs.getString(RS_PRNT_VND_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCd, rs.getString(RS_VND_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.locNm_H, rs.getString(RS_LOC_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.xxLocNm, rs.getString(RS_XX_LOC_NM));
            // detail
            ZYPEZDItemValueSetter.setValue(fMsg.xxLineNum, rs.getString(RS_XX_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineTpCd, (String) rs.getString(RS_PRCH_REQ_LINE_TP_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, (String) rs.getString(RS_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.aslMdseCd, (String) rs.getString(RS_ASL_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, (String) rs.getString(RS_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqDispQty, (BigDecimal) rs.getBigDecimal(RS_PRCH_REQ_DISP_QTY));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqDsplUomCd, (String) rs.getString(RS_PRCH_REQ_DSPL_UOM_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prntVndNm_D, (String) rs.getString(RS_PRNT_VND_NM_A1));
            ZYPEZDItemValueSetter.setValue(fMsg.locNm_D, (String) rs.getString(RS_LOC_NM_A1));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineStsNm, (String) rs.getString(RS_PRCH_REQ_LINE_STS_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.entDealNetUnitPrcAmt, (BigDecimal) rs.getBigDecimal(RS_ENT_DEAL_NET_UNIT_PRC_AMT));
            ZYPEZDItemValueSetter.setValue(fMsg.xxUnitPrc, (BigDecimal) rs.getBigDecimal(RS_XX_EXT_PRICE));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt, (String) rs.getString(RS_XX_CHG_ACCOUNT));
            ZYPEZDItemValueSetter.setValue(fMsg.coaMdseTpCd, (String) rs.getString(RS_COA_MDSE_TP_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.coaProdCd, (String) rs.getString(RS_COA_PROD_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqLineCmntTxt, (String) rs.getString(RS_PRCH_REQ_LINE_CMNT_TXT));
            // QC#27911
            // QC#29254
            ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, (String) rs.getString(RS_DPLY_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.relRqstToPoOrdNum, (String) rs.getString(RS_REL_RQST_TO_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.poSchdRelDt, (String) rs.getString(RS_PO_SCHD_REL_DT));
            ZYPEZDItemValueSetter.setValue(fMsg.poOrdNum, (String) rs.getString(RS_PO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.poOrdDtlLineNum, (String) rs.getString(RS_PO_ORD_DTL_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqRelDtTmTs, ZYPDateUtil.formatDisp8ToEzd((String) rs.getString(RS_PRCH_REQ_REL_DT_TM_TS)));
            ZYPEZDItemValueSetter.setValue(fMsg.prchReqRelErrMsgTxt, (String) rs.getString(RS_PRCH_REQ_REL_ERR_MSG_TXT));
        }
    }

    /**
     * check Technician warehouse code
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkTechnicianWh(String glblCmpyCd, String rtlWhCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_RTL_WH_CD, rtlWhCd);
        params.put(BIND_LOC_TP_CD, LOC_TP.TECHNICIAN);

        return getSsmEZDClient().queryObjectList("chkTechnicianWh", params);
    }

    /**
    * check PR Approval Status for Auto PO Button
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @param lineIndex int
    * @return S21SsmEZDResult
    */
   public S21SsmEZDResult checkPrApvlStatus(NPAL1280CMsg cMsg, String glblCmpyCd, int lineIndex) {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
       params.put(BIND_PRCH_REQ_NUM, cMsg.prchReqNum.getValue());
       params.put(BIND_PRCH_REQ_APVL_FLG, ZYPConstant.FLG_ON_Y);

       return getSsmEZDClient().queryObjectList("checkPrApvlStatus", params);
   }

    /**
     * countRtlWhInWhOwnr
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param whOwnrCdArray String[]
     * @return int
     */
    public int countRtlWhInWhOwnr(String glblCmpyCd, String rtlWhCd, String[] whOwnrCdArray) {
        HashMap<String, Object> bindParams = new HashMap<String, Object>();
        bindParams.put("glblCmpyCd", glblCmpyCd);
        bindParams.put("rtlWhCd", rtlWhCd);
        bindParams.put("whOwnrCdList", whOwnrCdArray);

        S21SsmEZDResult ezResult = getSsmEZDClient().queryObject("countRtlWhInWhOwnr", bindParams);
        return (Integer) ezResult.getResultObject();
    }

    /**QC#18671 Add.
     * getMdseFromSupplierItem
     * @param sMsg
     * @param glblCmpyCd
     * @param salesDate
     * @param lineIndex
     * @return
     */
    public S21SsmEZDResult getMdseFromSupplierItem(NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, int lineIndex) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_VND_CD, sMsg.A.no(lineIndex).vndCd_A1.getValue());
        params.put(BIND_SALES_DATE, salesDate);
        params.put(BIND_MDSE_CD, sMsg.A.no(lineIndex).mdseCd_A1.getValue());

        return getSsmEZDClient().queryObjectList("getMdseFromSupplierItem", params);
    }
    
    // QC#14858(Sol#060) Start
    /**
     * Default account info query
     * @param glblCmpyCd String
     * @param appFuncId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefAccInfoExp(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, int lineIndex) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_RTL_WH_CD, cMsg.destRtlWhCd.getValue());
        params.put(BIND_LINE_BIZ_TP_CD, cMsg.prchGrpCd_SL.getValue());
        params.put(BIND_MDSE_CD, cMsg.A.no(lineIndex).mdseCd_A1.getValue());
        return getSsmEZDClient().queryObjectList("getDefAccInfoExp", params);
    }
    // QC#14858(Sol#060) End
    // QC#21170 Start
    /**
     * getdefPrchReqTpDaysNum
     */
    public S21SsmEZDResult getDefPrchReqTpDaysNum(String glblCmpyCd, String prchReqTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqTpCd", prchReqTpCd);
        params.put("prchReqRecTpCd", PRCH_REQ_REC_TP.PO_REQUISITION);
        return getSsmEZDClient().queryObjectList("getDefPrchReqTpDaysNum", params);
    }
    // QC#21170 End

    /**
     * getCarrSvcLvlCd
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param carrCd String
     * @param shpgSvcLvlCd String
     * @return carrier service level code S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrSvcLvlCd(String glblCmpyCd, String carrCd, String shpgSvcLvlCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("carrCd", carrCd);
        params.put("shpgSvcLvlCd", shpgSvcLvlCd);
        return getSsmEZDClient().queryObject("getCarrSvcLvlCd", params);
    }

    /**
     * getDsAcctNum
     * QC#23726 Add method.
     * @param glblCmpyCd String
     * @param prchReqNum String
     * @return DsAcctNum S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNum(String glblCmpyCd, String prchReqNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prchReqNum", prchReqNum);
        return getSsmEZDClient().queryObject("getDsAcctNum", params);
    }
    
    //QC#26990 Add Start
    /**
     * get Ship To Address Info
     * @param bizMsg NPAL1280CMsg
     * @return Ship To Address Info
     */
    public S21SsmEZDResult getShipToAddressFromShipToCustomer(NPAL1280CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());

        return getSsmEZDClient().queryObject("getShipToAddressFromShipToCustomer", params);
    }

    // QC#28962 Add Start
    public S21SsmEZDResult gettShipToAddressFromS21Wh(NPAL1280CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());
        params.put("rgtnStsCd",RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObject("gettShipToAddressFromS21Wh", params);
    }
    // QC#28962 Add End
    /**
     * <pre>
     * get RetailWarehouse Name
     * </pre>
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return RTL_WHTMsg (if not exists, null)
     */
    public S21SsmEZDResult getRtlWhNm(String glblCmpyCd, String rtlWhCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtlWhCd", rtlWhCd);
        params.put("salesDate", ZYPDateUtil.getSalesDate());
        params.put("prtyLocFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getRtlWhNm", params);
    }
    //QC#26990 Add End
    
    //QC#28941 Add Start
    /**
     * Carrier Code query
     * @param glblCmpyCd String
     * @param locNm_HF String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrierCd(String glblCmpyCd, String locNm_HF) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_CARR_NM_01, locNm_HF);
        return getSsmEZDClient().queryObjectList("getCarrierCd", params);
    }
    //QC#28941 Add End
    /**
     * getTextItemList
	 * QC#28939 Add method.
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTextItemList(String glblCmpyCd){
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_MDSE_ITEM_TP_TEXTITEM, MDSE_ITEM_TP.TEXT_ITEM);
        return getSsmEZDClient().queryObjectList("getTextItemList", params);
    }

    // 2018/12/17 QC#29397 Add Start
    /**
     * checkPrntCmpySetMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult checkPrntCmpySetMdse(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        List<String> mdseCmpsnTpList = new ArrayList<String>();
        mdseCmpsnTpList.add(MDSE_CMPSN_TP.SET_MDSE);
        mdseCmpsnTpList.add(MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        params.put("mdseCmpsnTpCd_setMdse", mdseCmpsnTpList);
        params.put("salesDate", ZYPDateUtil.getSalesDate());
        params.put("effThruMaxDt", "99991231");

        return getSsmEZDClient().queryObject("checkPrntCmpySetMdse", params);
    }
    // 2018/12/17 QC#29397 Add End
    
    // QC#28709 Add method.
    /**
     * getAddrByPost
     * @param glblCmpyCd String
     * @param postCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrByPost(String glblCmpyCd, String postCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_POST_CD, postCd);
        return getSsmEZDClient().queryObjectList("getAddrByPost", params);
    }

    // QC#53300 2019/10/04 Add Start
    /**
     * getPrtVndNm
     * @param glblCmpyCd String
     * @param prtVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrtVndNm(String glblCmpyCd, String prtVndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("prntVndCd", prtVndCd);
        params.put("salesDate", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getPrtVndNm", params);
    }

    /**
     * <pre>
     * get Vendor info
     * </pre>
     * @param cMsg NPAL1280CMsg
     * @return S21SsmEZDResult Vendor info
     */
    public S21SsmEZDResult getVendorInfo(NPAL1280CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("vndCd", cMsg.vndCd.getValue());
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return getSsmEZDClient().queryObjectList("getVendorInfo", params);
    }

    /**
     * get Ship To Address Info
     * @param bizMsg NPAL1280CMsg
     * @return Ship To Address Info
     */
    public S21SsmEZDResult getShipToAddress(NPAL1280CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("shipToCustCd", bizMsg.destRtlWhCd.getValue());

        return getSsmEZDClient().queryObject("getShipToAddress", params);
    }

    /**
     * count Ship To Address
     * @param bizMsg NPAL1280CMsg
     * @param cusaGlblCmpyCd String
     * @return count of Ship To Address
     */
    public S21SsmEZDResult countShipToCustAddr(NPAL1280CMsg bizMsg, String cusaGlblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd",cusaGlblCmpyCd); // Search by CUSA Global Company Code
        params.put("shipToCustCd", bizMsg.shipToCustCd.getValue());

        return getSsmEZDClient().queryObject("countShipToCustAddr", params);
    }
    // QC#53300 2019/10/04 Add End

    // START 2022/10/31 N.Takatsu[QC#60604, ADD]
    /**
     * @param prmMap Map<String, Object>
     * @return count of CSA Warehouse
     */
    public S21SsmEZDResult getCsaWhCount(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObject("getCsaWhCount", prmMap);
    }
    // END 2022/10/31 N.Takatsu[QC#60604, ADD]

    // START 2022/12/15 F.Fadriquela [QC#60917, ADD]
    /**
     * getHeaderFrghtInfo
     * @param glblCmpyCd String
     * @param prchReqNum String
     * @param prchReqLineStsCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderFrghtInfo(String glblCmpyCd, String prchReqNum, String prchReqLineStsCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        params.put(BIND_PRCH_REQ_NUM, prchReqNum);
        params.put(BIND_PRCH_REQ_LINE_STS_CD, prchReqLineStsCd);
        return getSsmEZDClient().queryObjectList("getHeaderFrghtInfo", params);
    }
    // END 2022/12/15 F.Fadriquela [QC#60917, ADD]
    
    //QC#62443 2024/3/4 Add Start
    /**
     * getCoaCcCd
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoaCcCd(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getCoaCcCd", params);
    }
    //QC#62443 2024/3/4 Add End
}
