/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6820;

import static business.blap.NMAL6820.constant.NMAL6820Constant.DB_PARAM_CMSG;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_RTRN_TO_PROCR_TP_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_RTRN_TO_PROCR_TP_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_SRC_PROCR_TP_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.DEF_SRC_PROCR_TP_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RTRN_TO_CODE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_CODE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_WH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_CMN_CLEAR;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_CMN_RESET;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_CMN_SUBMIT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_GETADDRESS_RETURNTO;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_GETADDRESS_SHIPTO;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONBLUR_WHCATG;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_ALTOWNRNM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_BRANCH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_CLEAR_RTRN_TO;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_CLEAR_SHIP_TO;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_MGRNM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_SEARCH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_SET_RTRN_TO;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_ONCLICK_SET_SHIP_TO;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820_INIT;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820_NMAL6050;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820_NMAL6760;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820_NPAL1010;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820_NWAL1130;
import static business.blap.NMAL6820.constant.NMAL6820Constant.FLG_ON;
import static business.blap.NMAL6820.constant.NMAL6820Constant.INVTY_ACCT_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.INVTY_ACCT_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.INVTY_OWNR_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.INVTY_OWNR_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_CTY_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_RETURNTO_ST_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_CTY_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.LINK_OPENWIN_SHIPTO_ST_EVENT_NM;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_ALT_OWNER;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_BRANCH;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_OWNER;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0009E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM8383E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NZZM0000E;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NZZM0002I;
import static business.blap.NMAL6820.constant.NMAL6820Constant.PLN_ITEM_INSRC_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.PLN_ITEM_INSRC_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.PO_RCPT_RTE_TP_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.PO_RCPT_RTE_TP_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RMA_RCPT_RTE_TP_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RMA_RCPT_RTE_TP_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_WH_CATG_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTL_WH_CATG_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTRN_TO_CNTY_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTRN_TO_CNTY_PK_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTRN_TO_CTRY_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.RTRN_TO_CTRY_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.SHIP_TO_CNTY_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.SHIP_TO_CNTY_PK_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.SHIP_TO_CTRY_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.SHIP_TO_CTRY_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.SRC_ZN_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.TCH_EMR_PROCR_TP_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.TCH_EMR_PROCR_TP_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.WH_END_DATE;
import static business.blap.NMAL6820.constant.NMAL6820Constant.WH_OWNR_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.WH_OWNR_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.WH_SYS_TP_CD_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.WH_SYS_TP_NM_DBCOLUMN;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_GET_SUPPLIER_NAME;
import static business.blap.NMAL6820.constant.NMAL6820Constant.EVENT_NM_NMAL6820SCRN00_GET_SUPPLIER_SITE_NAME;
import static business.blap.NMAL6820.constant.NMAL6820Constant.NMAM0039E;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6820.common.NMAL6820CommonLogic;
import business.db.COA_BRTMsg;
import business.db.RTL_WH_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : search business process
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#1590
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC#2369
 * 02/29/2016   CSAI            D.Fukaya        Update          QC#1596/2363/2365
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#7652
 * 08/02/2016   CITS            S.Endo          Update          QC#10838
 *</pre>
 */
public class NMAL6820BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NMAL6820_INIT.equals(screenAplID)) {
                doProcess_NMAL6820_INIT((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_SEARCH.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_Search((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_Search((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_MGRNM.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_MgrNm((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_ALTOWNRNM.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_AltOwnrNm((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_BRANCH.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_Branch((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_CMN_RESET.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_CMN_Reset((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_CMN_Clear((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820_NMAL6050.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_NMAL6050((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONBLUR_WHCATG.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnBlur_WHCategory((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_SET_SHIP_TO.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_SetShipTo((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_CLEAR_SHIP_TO.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_ClearShipTo((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_SET_RTRN_TO.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_SetReturnTo((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820SCRN00_ONCLICK_CLEAR_RTRN_TO.equals(screenAplID)) {
                doProcess_NMAL6820Scrn00_OnClick_ClearReturnTo((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820_NMAL6760.equals(screenAplID)) {
                doProcess_NMAL6820_NMAL6760((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);

            } else if (EVENT_NM_NMAL6820_NPAL1010.equals(screenAplID)) {
                doProcess_NMAL6820_NPAL1010((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            } else if (EVENT_NM_NMAL6820_NWAL1130.equals(screenAplID)) {
                doProcess_NM_NMAL6820_NWAL1130((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            } else if (EVENT_NM_NMAL6820SCRN00_GETADDRESS_SHIPTO.equals(screenAplID)) {
                doProcess_NMAL6820_GetAddressShipTo((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            } else if (EVENT_NM_NMAL6820SCRN00_GETADDRESS_RETURNTO.equals(screenAplID)) {
                doProcess_NMAL6820_GetAddressReturnTo((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            } else if ((EVENT_NM_NMAL6820SCRN00_GET_SUPPLIER_NAME.equals(screenAplID))) {
                doProcess_Get_SupplierName((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            } else if ((EVENT_NM_NMAL6820SCRN00_GET_SUPPLIER_SITE_NAME.equals(screenAplID))) {
                doProcess_Get_SupplierSiteName((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6820_GetAddressReturnTo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {
        List<Map<String, Object>> list = null;
        String postCd = cMsg.rtrnToPostCd_R1.getValue();

        S21SsmEZDResult res = NMAL6820Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NMAL6820Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            cMsg.rtrnToPostCd_R1.setErrorInfo(1, NMAM0039E, new String[] {"Return to Location Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");
            List<BigDecimal> listCntyPk = getDistinctDecimalValueList(list, "CNTY_PK");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.rtrnToCtyAddr_R1, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.rtrnToStCd_R1, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_R1, listCntyNm.get(0));
            }
            if (listCntyPk.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_R1, listCntyPk.get(0));
            }
        }
    }

    private void doProcess_NMAL6820_GetAddressShipTo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {
        List<Map<String, Object>> list = null;
        String postCd = cMsg.postCd_S1.getValue();

        S21SsmEZDResult res = NMAL6820Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NMAL6820Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            cMsg.postCd_S1.setErrorInfo(1, NMAM0039E, new String[] {"Ship to Location Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");
            List<BigDecimal> listCntyPk = getDistinctDecimalValueList(list, "CNTY_PK");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_S1, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.stCd_S1, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_S1, listCntyNm.get(0));
            }
            if (listCntyPk.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_S1, listCntyPk.get(0));
            }
        }
    }

    private void doProcess_NM_NMAL6820_NWAL1130(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {
        S21SsmEZDResult ssmResult = null;
        boolean shipTo = true;
        if (LINK_OPENWIN_SHIPTO_ST_EVENT_NM.equals(cMsg.xxPopPrm_21)) {
            shipTo = true;
        } else if (LINK_OPENWIN_SHIPTO_POSTCD_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = true;
        } else if (LINK_OPENWIN_SHIPTO_CTY_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = true;
        } else if (LINK_OPENWIN_SHIPTO_CNTY_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = true;
        } else if (LINK_OPENWIN_RETURNTO_ST_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = false;
        } else if (LINK_OPENWIN_RETURNTO_POSTCD_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = false;
        } else if (LINK_OPENWIN_RETURNTO_CTY_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = false;
        } else if (LINK_OPENWIN_RETURNTO_CNTY_EVENT_NM.equals(cMsg.xxPopPrm_21.getValue())) {
            shipTo = false;
        }
        if (shipTo) {
            ssmResult = NMAL6820Query.getInstance().getAddrInfoShipTo(cMsg, getGlobalCompanyCode());
        } else {
            ssmResult = NMAL6820Query.getInstance().getAddrInfoReturnTo(cMsg, getGlobalCompanyCode());
        }
        if (ssmResult.isCodeNormal()) {
            BigDecimal result = (BigDecimal) ssmResult.getResultObject();
            if (result != null) {
                if (shipTo) {
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_S1, result);
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_R1, result);
                }
            }
        }
    }

    /**
     * The method explanation: The method explanation: It is a method
     * of the execution when the event[INIT] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820_INIT(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820_INIT]", null);

        // =============================================
        // Acquisition of global company code
        // =============================================
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd_G1, getGlobalCompanyCode());

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1)) {

            doProcess_NMAL6820Scrn00_OnClick_Search(cMsg, sMsg);
        } else {

            NMAL6820CommonLogic.clearMsg(cMsg, sMsg);

            // =================================================
            // Search SWH WH Category Relation
            // =================================================
            NMAL6820CommonLogic.searchSwhWhCatgRel(cMsg, sMsg, this);

            // set value for Effective From Date
            ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_H1, ZYPDateUtil.getSalesDate());
            ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_H1, WH_END_DATE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_R1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_S1, PROCR_TP.SUPPLIER);
            ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_E1, PROCR_TP.SUPPLIER);
            ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_R1, PROCR_TP.SUPPLIER);
        }

        createPulldownList(cMsg, sMsg);

        // Set Default Account Info
        if (NMAL6820CommonLogic.getDefDsAcctInfo(cMsg, sMsg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_S1, sMsg.dsAcctNum_G1.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_S1, sMsg.dsAcctNm_G1.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_R1, sMsg.dsAcctNum_G1.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_R1, sMsg.dsAcctNm_G1.getValue());
        } else {
            cMsg.setMessageInfo(NMAM8383E);
            return;
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820_INIT]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_Search] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_Search(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_Search]", null);

        cMsg.fullPsnNm_H1.clear();
        cMsg.fullPsnNm_H2.clear();
        cMsg.effFromDt_H1.clear();

        // Details information initialization of cMsg
        ZYPTableUtil.clear(cMsg.A);

        // sMsg initialization
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.C);
        // ZYPTableUtil.clear(sMsg.D);

        EZDMsg.copy(cMsg, null, sMsg, null);

        // Set Default Account Info
        if (!NMAL6820CommonLogic.getDefDsAcctInfo(cMsg, sMsg)) {
            cMsg.setMessageInfo(NMAM8383E);
            return;
        }
        // =================================================
        // Search SWH WH Category Relation
        // =================================================
        NMAL6820CommonLogic.searchSwhWhCatgRel(cMsg, sMsg, this);

        // =================================================
        // Search Retail Warehouse Process
        // =================================================
        S21SsmEZDResult ssmResult = NMAL6820CommonLogic.searchRtlWh(cMsg, sMsg, this);

        if (ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo(NZZM0002I);
        } else {
            String rtlWhCdForSearch = cMsg.rtlWhCd_H1.getValue();
            cMsg.rtlWhCd_H1.clear();
            doProcess_NMAL6820_INIT((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H1, rtlWhCdForSearch);
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.prntVndCd)) {
            S21SsmEZDResult result = NMAL6820Query.getInstance().getPrtVndNm(getGlobalCompanyCode(), cMsg.prntVndCd.getValue());
            if (result.isCodeNormal()) {
                String prntName = (String) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, prntName);
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndCd)) {
            S21SsmEZDResult result = NMAL6820Query.getInstance().getVndNm(getGlobalCompanyCode(), cMsg.vndCd.getValue());
            if (result.isCodeNormal()) {
                String vndName = (String) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.vndNm, vndName);
            }
        }

        createPulldownListForSearch(cMsg, sMsg);

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_Search]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_AltOwnrNm] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_AltOwnrNm(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_AltOwnrNm]", null);

        cMsg.fullPsnNm_H2.clear();

        if (ZYPCommonFunc.hasValue(cMsg.altPsnCd_H1)) {

            S21SsmEZDResult ssmResult = NMAL6820Query.getInstance().getAltOwnrNm(cMsg);

            if (ssmResult.isCodeNotFound()) {
                cMsg.altPsnCd_H1.setErrorInfo(1, NMAM0009E, new String[] {NAME_FOR_MESSAGE_ALT_OWNER });
            }
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_AltOwnrNm]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_MgrNm] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_MgrNm(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_MgrNm]", null);

        cMsg.fullPsnNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd_H1)) {

            S21SsmEZDResult mgrName = NMAL6820Query.getInstance().getMgrNm(cMsg);

            if (mgrName.isCodeNotFound()) {
                cMsg.whMgrPsnCd_H1.setErrorInfo(1, NMAM0009E, new String[] {NAME_FOR_MESSAGE_OWNER });
            }
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_MgrNm]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_TechNm] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_Branch(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_Branch]", null);

        cMsg.coaBrNm_H1.clear();

        if (ZYPCommonFunc.hasValue(cMsg.coaBrCd_H1)) {

            COA_BRTMsg coaBrTMsg = new COA_BRTMsg();
            ZYPEZDItemValueSetter.setValue(coaBrTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
            ZYPEZDItemValueSetter.setValue(coaBrTMsg.coaBrCd, cMsg.coaBrCd_H1);
            coaBrTMsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(coaBrTMsg);

            if (coaBrTMsg == null) {
                cMsg.coaBrCd_H1.setErrorInfo(1, NMAM0009E, new String[] {NAME_FOR_MESSAGE_BRANCH });
                return;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.coaBrNm_H1, coaBrTMsg.coaBrNm);
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_Branch]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[CMN_Reset] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_CMN_Reset(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        doProcess_NMAL6820_INIT((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[CMN_Clear] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_CMN_Clear(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        doProcess_NMAL6820_INIT((NMAL6820CMsg) cMsg, (NMAL6820SMsg) sMsg);
    }

    /**
     * The method explanation: create Pulldown List.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownList(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(FLG_ON, ZYPConstant.FLG_ON_Y);

        // Warehouse Category
        S21SsmEZDResult whCatgPulldownList = NMAL6820Query.getInstance().getWhCatgPulldownList(ssmParam);

        if (whCatgPulldownList.isCodeNormal()) {

            List<Map> whCatgList = (List<Map>) whCatgPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.rtlWhCatgCd_L1, cMsg.rtlWhCatgNm_L1, whCatgList, new String[] {RTL_WH_CATG_CD_DBCOLUMN, RTL_WH_CATG_NM_DBCOLUMN });
        }

        // WH Ownership
        S21SsmEZDResult whOwnPulldownList = NMAL6820Query.getInstance().getWhOwnPulldownList(ssmParam);

        if (whOwnPulldownList.isCodeNormal()) {

            List<Map> whList = (List<Map>) whOwnPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.whOwnrCd_L1, cMsg.whOwnrNm_L1, whList, new String[] {WH_OWNR_CD_DBCOLUMN, WH_OWNR_NM_DBCOLUMN });
        }

        // Warehouse System Type
        S21SsmEZDResult whSysTpPulldownList = NMAL6820Query.getInstance().getWhSysTpPulldownList(ssmParam);

        if (whSysTpPulldownList.isCodeNormal()) {

            List<Map> whList = (List<Map>) whSysTpPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.whSysTpCd_L1, cMsg.whSysTpNm_L1, whList, new String[] {WH_SYS_TP_CD_DBCOLUMN, WH_SYS_TP_NM_DBCOLUMN });
        }

        // Y/N
        NMAL6820CommonLogic.setYesNoPulldownList(cMsg);

        // PO Receipt Routing Type
        S21SsmEZDResult poRcptRteTpPulldownList = NMAL6820Query.getInstance().getPoRcptRteTpPulldownList(ssmParam);

        if (poRcptRteTpPulldownList.isCodeNormal()) {

            List<Map> poRcptRteTpList = (List<Map>) poRcptRteTpPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.poRcptRteTpCd_L1, cMsg.poRcptRteTpNm_L1, poRcptRteTpList, new String[] {PO_RCPT_RTE_TP_CD_DBCOLUMN, PO_RCPT_RTE_TP_NM_DBCOLUMN });
        }

        // RMA Receipt Routing Type
        S21SsmEZDResult rmaRcptRteTpPulldownList = NMAL6820Query.getInstance().getRmaRcptRteTpPulldownList(ssmParam);

        if (rmaRcptRteTpPulldownList.isCodeNormal()) {

            List<Map> rmaRcptRteTpList = (List<Map>) rmaRcptRteTpPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.rmaRcptRteTpCd_L1, cMsg.rmaRcptRteTpNm_L1, rmaRcptRteTpList, new String[] {RMA_RCPT_RTE_TP_CD_DBCOLUMN, RMA_RCPT_RTE_TP_NM_DBCOLUMN });
        }

        // Ship to Country
        S21SsmEZDResult shipToCtryPulldownList = NMAL6820Query.getInstance().getShipToCtryPulldownList(ssmParam);

        if (shipToCtryPulldownList.isCodeNormal()) {

            List<Map> shipToCtryList = (List<Map>) shipToCtryPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.ctryCd_L1, cMsg.ctryNm_L1, shipToCtryList, new String[] {SHIP_TO_CTRY_CD_DBCOLUMN, SHIP_TO_CTRY_NM_DBCOLUMN });
        }

        // Return to Country
        S21SsmEZDResult rtrnToCtryPulldownList = NMAL6820Query.getInstance().getRtrnToCtryPulldownList(ssmParam);

        if (rtrnToCtryPulldownList.isCodeNormal()) {

            List<Map> rtrnToCtryList = (List<Map>) rtrnToCtryPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.ctryCd_L2, cMsg.ctryNm_L2, rtrnToCtryList, new String[] {RTRN_TO_CTRY_CD_DBCOLUMN, RTRN_TO_CTRY_NM_DBCOLUMN });
        }

        // Min-Max Parts Insourcing
        S21SsmEZDResult plnItemInsrcPulldownList = NMAL6820Query.getInstance().getPlnItemInsrcPulldownList(ssmParam);

        if (plnItemInsrcPulldownList.isCodeNormal()) {

            List<Map> plnItemInsrcList = (List<Map>) plnItemInsrcPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.plnItemInsrcCd_L1, cMsg.plnItemInsrcNm_L1, plnItemInsrcList, new String[] {PLN_ITEM_INSRC_CD_DBCOLUMN, PLN_ITEM_INSRC_NM_DBCOLUMN });
        }

        // Zone
        S21SsmEZDResult srcZonePulldownList = NMAL6820Query.getInstance().getSrcZnPulldownList(ssmParam);

        if (srcZonePulldownList.isCodeNormal()) {

            List<Map> srcZoneList = (List<Map>) srcZonePulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.srcZnCd_L1, cMsg.srcZnNm_L1, srcZoneList, new String[] {SRC_ZN_CD_DBCOLUMN, SRC_ZN_CD_DBCOLUMN });
        }

        // Default Sourcing Procurement Type
        S21SsmEZDResult defSrcProcrTpPulldownList = NMAL6820Query.getInstance().getDefSrcProcrTpPulldownList(ssmParam);

        if (defSrcProcrTpPulldownList.isCodeNormal()) {

            List<Map> defSrcProcrTpList = (List<Map>) defSrcProcrTpPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.procrTpCd_L1, cMsg.procrTpNm_L1, defSrcProcrTpList, new String[] {DEF_SRC_PROCR_TP_CD_DBCOLUMN, DEF_SRC_PROCR_TP_NM_DBCOLUMN });
        }

        // Tech Emergency Source List
        S21SsmEZDResult tchEmrProcrTpPulldownList = NMAL6820Query.getInstance().getTchEmrProcrTpPulldownList(ssmParam);

        if (tchEmrProcrTpPulldownList.isCodeNormal()) {

            List<Map> defSrcProcrTpList = (List<Map>) tchEmrProcrTpPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.procrTpCd_L2, cMsg.procrTpNm_L2, defSrcProcrTpList, new String[] {TCH_EMR_PROCR_TP_CD_DBCOLUMN, TCH_EMR_PROCR_TP_NM_DBCOLUMN });
        }

        // Default Return to Procurement Type
        S21SsmEZDResult defRtrntoProcrTpPulldownList = NMAL6820Query.getInstance().getDefRtrnToProcrTpPulldownList(ssmParam);

        if (defRtrntoProcrTpPulldownList.isCodeNormal()) {

            List<Map> defRtrntoProcrTpList = (List<Map>) defRtrntoProcrTpPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.procrTpCd_L3, cMsg.procrTpNm_L3, defRtrntoProcrTpList, new String[] {DEF_RTRN_TO_PROCR_TP_CD_DBCOLUMN, DEF_RTRN_TO_PROCR_TP_NM_DBCOLUMN });
        }
    }

    /**
     * The method explanation: create Pulldown List for search event.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void createPulldownListForSearch(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);

        // Inventory Account
        S21SsmEZDResult invtyAcctPulldownList = NMAL6820Query.getInstance().getInvtyAcctPulldownList(ssmParam);

        if (invtyAcctPulldownList.isCodeNormal()) {

            List<Map> invtyAcctList = (List<Map>) invtyAcctPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.invtyAcctCd_L1, cMsg.invtyAcctNm_L1, invtyAcctList, new String[] {INVTY_ACCT_CD_DBCOLUMN, INVTY_ACCT_NM_DBCOLUMN });
        }

        // Inventory Owner
        S21SsmEZDResult invtyOwnrPulldownList = NMAL6820Query.getInstance().getInvtyOwnrPulldownList(ssmParam);

        if (invtyOwnrPulldownList.isCodeNormal()) {

            List<Map> invtyOwnrList = (List<Map>) invtyOwnrPulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.invtyOwnrCd_L1, cMsg.invtyOwnrNm_L1, invtyOwnrList, new String[] {INVTY_OWNR_CD_DBCOLUMN, INVTY_OWNR_NM_DBCOLUMN });
        }

        // Ship to County
        if (ZYPCommonFunc.hasValue(cMsg.cntyPk_S1.getValue())) {

            S21SsmEZDResult shipToCntyPulldownList = NMAL6820Query.getInstance().getShipToCntyPulldownList(ssmParam);

            if (shipToCntyPulldownList.isCodeNormal()) {

                List<Map> shipToCntyList = (List<Map>) shipToCntyPulldownList.getResultObject();
                NMAL6820CommonLogic.createPulldownList(cMsg.cntyPk_L1, cMsg.cntyNm_L1, shipToCntyList, new String[] {SHIP_TO_CNTY_PK_DBCOLUMN, SHIP_TO_CNTY_NM_DBCOLUMN });
            }
        }

        // Return to County
        if (ZYPCommonFunc.hasValue(cMsg.cntyPk_R1.getValue())) {
            S21SsmEZDResult rtrnToCntyPulldownList = NMAL6820Query.getInstance().getRtrnToCntyPulldownList(ssmParam);

            if (rtrnToCntyPulldownList.isCodeNormal()) {

                List<Map> rtrnToCntyList = (List<Map>) rtrnToCntyPulldownList.getResultObject();
                NMAL6820CommonLogic.createPulldownList(cMsg.cntyPk_L2, cMsg.cntyNm_L2, rtrnToCntyList, new String[] {RTRN_TO_CNTY_PK_DBCOLUMN, RTRN_TO_CNTY_NM_DBCOLUMN });
            }
        }

        // re-setup WH System Type List
        S21SsmEZDResult whSystemTypePulldownList = NMAL6820Query.getInstance().getReSetupWhSysTpPulldownList(ssmParam);

        if (EZDTBLAccessor.RTNCD_NOT_FOUND.equals(whSystemTypePulldownList.getResultCode())) {

            whSystemTypePulldownList = NMAL6820Query.getInstance().getWhSysTpPulldownList(ssmParam);
        }
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(whSystemTypePulldownList.getResultCode())) {

            cMsg.whSysTpCd_L1.clear();
            cMsg.whSysTpNm_L1.clear();

            List<Map> whSystemTypeList = (List<Map>) whSystemTypePulldownList.getResultObject();
            NMAL6820CommonLogic.createPulldownList(cMsg.whSysTpCd_L1, cMsg.whSysTpNm_L1, whSystemTypeList, new String[] {WH_SYS_TP_CD_DBCOLUMN, WH_SYS_TP_NM_DBCOLUMN });
        }
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[NMAL6820Scrn00_NMAL6050] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_NMAL6050(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_NMAL6050]", null);

        EZDMsg.copy(cMsg, null, sMsg, null);

        if (EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR.equals(cMsg.xxPopPrm_EV.getValue()) && LOC_TP.TECHNICIAN.equals(cMsg.locTpCd_H1.getValue())) {
            NMAL6820CommonLogic.searchTechInfo(cMsg, sMsg, this);
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_NMAL6050]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[TAB_SWH] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnBlur_WHCategory(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnBlur_WHCategory]", null);

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCatgCd_H1)) {

            NMAL6820CommonLogic.getSwhWhCatgRel(cMsg, sMsg, this);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);

            // Inventory Account
            S21SsmEZDResult invtyAcctPulldownList = NMAL6820Query.getInstance().getInvtyAcctPulldownList(ssmParam);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(invtyAcctPulldownList.getResultCode())) {

                cMsg.invtyAcctCd_L1.clear();
                cMsg.invtyAcctNm_L1.clear();
                List<Map> invtyAcctList = (List<Map>) invtyAcctPulldownList.getResultObject();
                NMAL6820CommonLogic.createPulldownList(cMsg.invtyAcctCd_L1, cMsg.invtyAcctNm_L1, invtyAcctList, new String[] {INVTY_ACCT_CD_DBCOLUMN, INVTY_ACCT_NM_DBCOLUMN });
                NMAL6820CommonLogic.setDefaultForPulldownList(cMsg.invtyAcctCd_H1, invtyAcctList, INVTY_ACCT_CD_DBCOLUMN);
            }

            // Inventory Owner
            S21SsmEZDResult invtyOwnrPulldownList = NMAL6820Query.getInstance().getInvtyOwnrPulldownList(ssmParam);

            if (EZDTBLAccessor.RTNCD_NORMAL.equals(invtyOwnrPulldownList.getResultCode())) {

                cMsg.invtyOwnrCd_L1.clear();
                cMsg.invtyOwnrNm_L1.clear();
                List<Map> invtyOwnrList = (List<Map>) invtyOwnrPulldownList.getResultObject();
                NMAL6820CommonLogic.createPulldownList(cMsg.invtyOwnrCd_L1, cMsg.invtyOwnrNm_L1, invtyOwnrList, new String[] {INVTY_OWNR_CD_DBCOLUMN, INVTY_OWNR_NM_DBCOLUMN });
                NMAL6820CommonLogic.setDefaultForPulldownList(cMsg.invtyOwnrCd_H1, invtyOwnrList, INVTY_OWNR_CD_DBCOLUMN);
            }

            // re-setup WH Ownership List
            S21SsmEZDResult whOwnershipPulldownList = NMAL6820Query.getInstance().getReSetupWhOwnPulldownList(ssmParam);

            if (EZDTBLAccessor.RTNCD_NOT_FOUND.equals(whOwnershipPulldownList.getResultCode())) {

                whOwnershipPulldownList = NMAL6820Query.getInstance().getWhOwnPulldownList(ssmParam);
            }
            if (EZDTBLAccessor.RTNCD_NORMAL.equals(whOwnershipPulldownList.getResultCode())) {

                cMsg.whOwnrCd_L1.clear();
                cMsg.whOwnrNm_L1.clear();

                List<Map> whOwnershipList = (List<Map>) whOwnershipPulldownList.getResultObject();
                NMAL6820CommonLogic.createPulldownList(cMsg.whOwnrCd_L1, cMsg.whOwnrNm_L1, whOwnershipList, new String[] {WH_OWNR_CD_DBCOLUMN, WH_OWNR_NM_DBCOLUMN });
                NMAL6820CommonLogic.setDefaultForPulldownList(cMsg.whOwnrCd_H1, whOwnershipList, WH_OWNR_CD_DBCOLUMN);
            }

            // re-setup WH System Type List
            S21SsmEZDResult whSystemTypePulldownList = NMAL6820Query.getInstance().getReSetupWhSysTpPulldownList(ssmParam);

            if (EZDTBLAccessor.RTNCD_NOT_FOUND.equals(whSystemTypePulldownList.getResultCode())) {

                whSystemTypePulldownList = NMAL6820Query.getInstance().getWhSysTpPulldownList(ssmParam);
            }
            if (EZDTBLAccessor.RTNCD_NORMAL.equals(whSystemTypePulldownList.getResultCode())) {

                cMsg.whSysTpCd_L1.clear();
                cMsg.whSysTpNm_L1.clear();

                List<Map> whSystemTypeList = (List<Map>) whSystemTypePulldownList.getResultObject();
                NMAL6820CommonLogic.createPulldownList(cMsg.whSysTpCd_L1, cMsg.whSysTpNm_L1, whSystemTypeList, new String[] {WH_SYS_TP_CD_DBCOLUMN, WH_SYS_TP_NM_DBCOLUMN });
                NMAL6820CommonLogic.setDefaultForPulldownList(cMsg.whSysTpCd_H1, whSystemTypeList, WH_SYS_TP_CD_DBCOLUMN);
            }

            RTL_WH_CATGTMsg rtlWhCatgTMsg = new RTL_WH_CATGTMsg();
            ZYPEZDItemValueSetter.setValue(rtlWhCatgTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);
            ZYPEZDItemValueSetter.setValue(rtlWhCatgTMsg.rtlWhCatgCd, cMsg.rtlWhCatgCd_H1);
            try {
                rtlWhCatgTMsg = (RTL_WH_CATGTMsg) EZDTBLAccessor.findByKey(rtlWhCatgTMsg);
            } catch (EZDDBRecordLockedException e) {
                // nothing
            }
            if (ZYPCommonFunc.hasValue(rtlWhCatgTMsg.locTpCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.locTpCd_H1, rtlWhCatgTMsg.locTpCd);
            }
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnBlur_WHCategory]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_SetShipTo] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_SetShipTo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_SetShipTo]", null);

        if (ZYPCommonFunc.hasValue(cMsg.locNum_S1)) {
            NMAL6820CommonLogic.setShipToCustInfo(cMsg);
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_SetShipTo]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_ClearShipTo] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_ClearShipTo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_ClearShipTo]", null);

        cMsg.xxSetFlg_S1.clear();
        cMsg.shipToCustCd_S1.clear();
        cMsg.locNum_S1.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_S1, sMsg.dsAcctNum_G1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_S1, sMsg.dsAcctNm_G1.getValue());
        cMsg.dsLocNm_S1.clear();
        cMsg.addlLocNm_S1.clear();
        cMsg.firstLineAddr_S1.clear();
        cMsg.scdLineAddr_S1.clear();
        cMsg.thirdLineAddr_S1.clear();
        cMsg.frthLineAddr_S1.clear();
        cMsg.ctyAddr_S1.clear();
        cMsg.cntyPk_S1.clear();
        cMsg.cntyNm_S1.clear();
        cMsg.stCd_S1.clear();
        cMsg.provNm_S1.clear();
        cMsg.postCd_S1.clear();
        cMsg.ctryCd_S1.clear();

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_ClearShipTo]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[_OnClick_SetReturnTo] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_SetReturnTo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_SetReturnTo]", null);

        if (ZYPCommonFunc.hasValue(cMsg.locNum_R1)) {
            NMAL6820CommonLogic.setRtrnToCustInfo(cMsg);
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_SetShipTo]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[OnClick_ClearReturnTo] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820Scrn00_OnClick_ClearReturnTo(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820Scrn00_OnClick_ClearReturnTo]", null);

        cMsg.xxSetFlg_R1.clear();
        cMsg.rtrnToCustCd_R1.clear();
        cMsg.locNum_R1.clear();
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_R1, sMsg.dsAcctNum_G1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_R1, sMsg.dsAcctNm_G1.getValue());
        cMsg.dsLocNm_R1.clear();
        cMsg.rtrnToAddlLocNm_R1.clear();
        cMsg.rtrnToFirstLineAddr_R1.clear();
        cMsg.rtrnToScdLineAddr_R1.clear();
        cMsg.rtrnToThirdLineAddr_R1.clear();
        cMsg.rtrnToFrthLineAddr_R1.clear();
        cMsg.rtrnToCtyAddr_R1.clear();
        cMsg.cntyPk_R1.clear();
        cMsg.cntyNm_R1.clear();
        cMsg.rtrnToStCd_R1.clear();
        cMsg.rtrnToProvNm_R1.clear();
        cMsg.rtrnToPostCd_R1.clear();
        cMsg.ctryCd_R1.clear();

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820Scrn00_OnClick_ClearReturnTo]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[NMAL6820_NMAL6760] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820_NMAL6760(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820_NMAL6760]", null);

        EZDMsg.copy(cMsg, null, sMsg, null);

        if (EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_CODE.equals(cMsg.xxPopPrm_EV.getValue())) {
            doProcess_NMAL6820Scrn00_OnClick_SetShipTo(cMsg, sMsg);

        } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_RTRN_TO_CODE.equals(cMsg.xxPopPrm_EV.getValue())) {
            doProcess_NMAL6820Scrn00_OnClick_SetReturnTo(cMsg, sMsg);
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820_NMAL6760]", null);
    }

    /**
     * The method explanation: It is a method of the execution when
     * the event[NMAL6820_NPAL1010] is generated.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NMAL6820_NPAL1010(NMAL6820CMsg cMsg, NMAL6820SMsg sMsg) {

        EZDDebugOutput.println(1, "PerformanceNMAL6820 start[doProcess_NMAL6820_NPAL1010]", null);

        EZDMsg.copy(cMsg, null, sMsg, null);

        if (EVENT_NM_AL6820SCRN00_OPEN_WIN_WH.equals(cMsg.xxPopPrm_EV.getValue())) {
            doProcess_NMAL6820Scrn00_OnClick_Search(cMsg, sMsg);
        }

        EZDDebugOutput.println(1, "PerformanceNMAL6820 end[doProcess_NMAL6820_NPAL1010]", null);
    }
    /**
     * doProcess_Get_SupplierName
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_Get_SupplierName(NMAL6820CMsg bizMsg, NMAL6820SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) {
            S21SsmEZDResult result = NMAL6820Query.getInstance().getPrtVndNm(getGlobalCompanyCode(), bizMsg.prntVndCd.getValue());

            if (result.isCodeNormal()) {
                String prntName = (String) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, prntName);
            } else {
                bizMsg.prntVndCd.setErrorInfo(1, "NPAM0076E", new String[] {"Supplier" });
            }
        }
    }
    private void doProcess_Get_SupplierSiteName(NMAL6820CMsg bizMsg, NMAL6820SMsg glblMsg) {

        NMAL6820CommonLogic.getSupplierName(bizMsg, glblMsg);
    }
    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }

    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<BigDecimal> getDistinctDecimalValueList(List<Map<String, Object>> list, String colNm) {
        List<BigDecimal> listDistinct = new ArrayList<BigDecimal>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            BigDecimal value = (BigDecimal) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }
}
