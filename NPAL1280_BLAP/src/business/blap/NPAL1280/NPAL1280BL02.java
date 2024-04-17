/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1280;

import static business.blap.NPAL1280.constant.NPAL1280Constant.CMN_SAVE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.CMN_SUBMIT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.CSV_FILE_NAME;
import static business.blap.NPAL1280.constant.NPAL1280Constant.DATE_NEEDED;
import static business.blap.NPAL1280.constant.NPAL1280Constant.DISPLAY_PRNT_VND_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.EVENT_NM_GET_SUPPLIER_NAME;
import static business.blap.NPAL1280.constant.NPAL1280Constant.EVENT_NM_GET_SUPPLIER_SITE_NAME;
import static business.blap.NPAL1280.constant.NPAL1280Constant.EVENT_NM_NPAL1280_GET_SHIP_TO_INFO;
import static business.blap.NPAL1280.constant.NPAL1280Constant.EVENT_NM_NPAL1280_GET_SHIP_TO_INFO_CUSTOMER;
import static business.blap.NPAL1280.constant.NPAL1280Constant.EXTN_CSV;
import static business.blap.NPAL1280.constant.NPAL1280Constant.FETCH_SIZE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.MAX_DOWNLOAD_CNT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.MSG_ERR_CD_NORMAL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NAMM0027E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NLAM0077E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NMAM0039E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_ADD_NEW_LINE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_APPLY;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_RQSTSHIPDTAPPLY;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_AUTO_CREATE_PO;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_CLEAR;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_COL_CLEAR;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_COL_SAVE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_DOWNLOAD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_RESET;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_COPY;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_GET_ADDRESS;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_GET_MDSE_INFO;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_HEADER_CANCEL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_INIT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_LINE_CANCEL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NEXT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NMAL2550;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NMAL6760;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NMAL6800;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NPAL0170;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NPAL1010;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_NWAL1130;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_ON_BLUR_ITEM_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_ON_BLUR_ORDER_QTY;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_ON_BLUR_UNIT_PRICE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_ON_CHANGE_LINE_TYPE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_PREV;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_SEARCH;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_TAB_DETAIL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_TAB_HEADER;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0046E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0049E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0076E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0079E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1360E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1544E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ORDER_QTY_D;
import static business.blap.NPAL1280.constant.NPAL1280Constant.PO_ACRL_ACCT_TP;
import static business.blap.NPAL1280.constant.NPAL1280Constant.REQNUM_OR_ORDER_REL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.RS_CARR_NM;
import static business.blap.NPAL1280.constant.NPAL1280Constant.RS_PRF_CARR_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.UNIT_PRICE_D;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VENDOR_SHIP_DATE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZM9000E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZM9037E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZZM9003I;
import static business.blap.NPAL1280.constant.NPAL1280Constant.MDSE_TP_SET;
import static business.blap.NPAL1280.constant.NPAL1280Constant.PERIOD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_ACCT_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_AFFL_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_BR_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_CH_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_CMPY_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_EXTN_CD;
import static business.blap.NPAL1280.constant.NPAL1280Constant.VAR_CHAR_EXP_SPLY_COA_PROJ_CD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1280.common.NPAL1280CommonLogic;
import business.blap.NPAL1280.NPAL1280Query;
import business.db.MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS         K.Ogino            Create          N/A
 * 02/25/2016   CITS         K.Ogino            Update          QC#4687
 * 03/01/2016   CITS         K.Ogino            Update          QC#4734
 * 03/01/2016   CITS         K.Ogino            Update          QC#4737
 * 03/01/2016   CITS         K.Ogino            Update          QC#4641
 * 03/08/2016   CITS         K.Ogino            Update          QC#5156
 * 04/04/2016   CITS         K.Ogino            Update          QC#5964
 * 05/24/2016   CITS         K.Lee              Update          QC#8841
 * 10/24/2016   CITS         K.Ogino            Update          QC#15325
 * 02/08/2018   CITS         K.Ogino            Update          QC#21169
 * 04/04/2018   CITS         T.Wada             Update          QC#21170
 * 04/20/2018   CITS         Y.Iwasaki          Update          QC#25019
 * 07/20/2018   CITS         K.Kameoka          Update          QC#26990
 * 08/16/2018   CITS         K.Ogino            Update          QC#27819
 * 08/20/2018   CITS         K.Ogino            Update          QC#27846
 * 09/14/2018   CITS         K.Ogino            Update          QC#28216/QC#28143
 * 10/24/2018   CITS         K.Kameoka          Update          QC#27770
 * 11/15/2018   CITS         T.Tokutomi         Update          QC#29155
 * 12/17/2018   Fujitsu      S.Takami           Update          QC#29456
 * 12/21/2018   CITS         K.Ogino            Update          QC#29729
 * 01/11/2019   CITS         T.Tokutomi         Update          QC#28709
 * 2019/01/15   Fujitsu      S.Takami           Update          QC#29778
 * 2019/03/20   Fujitsu      T.Ogura            Update          QC#30769
 * 2019/04/09   CITS         K.Ogino            Update          QC#30994
 * 2019/09/24   CITS         R.Shimamoto        Update          QC#53422
 * 2019/10/04   CITS         R.Shimamoto        Update          QC#53300
 * 2020/03/19   CITS         M.Furugoori        Update          QC#56122
 * 2022/10/31   Hitachi      N.Takatsu          Update          QC#60604
 * 2023/02/02   Hitachi      T.Kuroda           Update          QC#60966
 * 2023/04/28   Hitachi      S.Dong             Update          QC#60966
 * 2024/03/04   CITS         S.Okamoto          Update          QC#62443
 *</pre>
 */
public class NPAL1280BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
            NPAL1280SMsg glblMsg = (NPAL1280SMsg) sMsg;
            String glblCmpyCd = getGlobalCompanyCode();
            String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
            String loginUserId = getUserProfileService().getContextUserInfo().getUserId();
            String loginUserNm = getUserProfileService().getContextUserInfo().getFullName();

            if (NPAL1280_INIT.equals(screenAplID)) {
                doProcess_NPAL1280_INIT(bizMsg, glblMsg, glblCmpyCd, salesDate);
                ZYPGUITableColumn.getColData((NPAL1280CMsg) cMsg, (NPAL1280SMsg) sMsg);
            } else if (NPAL1280_SEARCH.equals(screenAplID)) {
                doProcess_NPAL1280_SEARCH(bizMsg, glblMsg, glblCmpyCd);
            } else if (NPAL1280_TAB_HEADER.equals(screenAplID) || NPAL1280_TAB_DETAIL.equals(screenAplID)) {
                return;
            } else if (NPAL1280_NPAL1010.equals(screenAplID)) {
                doProcess_NPAL1280_NPAL1010(bizMsg, glblMsg, glblCmpyCd);
            } else if (NPAL1280_NEXT.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_Next(bizMsg, glblMsg);
            } else if (NPAL1280_PREV.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_Prev(bizMsg, glblMsg);
            } else if (NPAL1280_COPY.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_Copy(bizMsg, glblMsg, glblCmpyCd, salesDate, loginUserId, loginUserNm);
            } else if (NPAL1280_CMN_RESET.equals(screenAplID)) {
                if (ZYPCommonFunc.hasValue(bizMsg.prchReqNum_HD)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.prchReqNum, bizMsg.prchReqNum_HD);
                    doProcess_NPAL1280_SEARCH(bizMsg, glblMsg, glblCmpyCd);
                } else {
                    doProcess_NPAL1280_INIT(bizMsg, glblMsg, glblCmpyCd, salesDate);
                }
            } else if (NPAL1280_CMN_CLEAR.equals(screenAplID)) {
                doProcess_NPAL1280_Clear(bizMsg, glblMsg, glblCmpyCd, salesDate);
            } else if (CMN_SAVE.equals(screenAplID)) {
                // QC#29155 Update.
                if(bizMsg.shpgSvcLvlCd_SL.getErrorCode() == MSG_ERR_CD_NORMAL){
                    doProcess_NPAL1280_SEARCH(bizMsg, glblMsg, glblCmpyCd);
                }
            } else if (CMN_SUBMIT.equals(screenAplID)) {
                // QC#29155 Update.
                if (bizMsg.shpgSvcLvlCd_SL.getErrorCode() == MSG_ERR_CD_NORMAL) {
                    doProcess_NPAL1280_SEARCH(bizMsg, glblMsg, glblCmpyCd);
                }
            } else if (NPAL1280_HEADER_CANCEL.equals(screenAplID) || NPAL1280_LINE_CANCEL.equals(screenAplID)) {
                doProcess_NPAL1280_HederOrLineCancel(bizMsg, glblMsg, glblCmpyCd);
            } else if (NPAL1280_ADD_NEW_LINE.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_AddNewLine(bizMsg, glblMsg, glblCmpyCd);
            } else if (NPAL1280_ON_BLUR_ITEM_CD.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_OnBlur_ItemCode(bizMsg, glblMsg, glblCmpyCd, salesDate);
            } else if (NPAL1280_ON_BLUR_ORDER_QTY.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_OnBlur_OrderQty(bizMsg, glblMsg);
            } else if (NPAL1280_ON_BLUR_UNIT_PRICE.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_OnBlur_UnitPrice(bizMsg, glblMsg);
            } else if (NPAL1280_NMAL6800.equals(screenAplID)) {
                // QC#30994
                doProcess_NPAL1280Scrn00_Get_MdseInfo(bizMsg, glblMsg, glblCmpyCd, salesDate);
            } else if (NPAL1280_AUTO_CREATE_PO.equals(screenAplID)) {
                doProcess_NPAL1280_AutoCreatePO(bizMsg, glblMsg, glblCmpyCd, salesDate);
            } else if (NPAL1280_ON_CHANGE_LINE_TYPE.equals(screenAplID)) {
                // QC#14858 MOD START
                doProcess_OnChangeLineType(bizMsg, glblMsg, glblCmpyCd, salesDate);
                //return;
                // QC#14858 MOD END
            } else if (NPAL1280_CMN_DOWNLOAD.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_CMN_Download(bizMsg, glblCmpyCd);
            } else if (NPAL1280_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if (NPAL1280_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if (NPAL1280_NPAL0170.equals(screenAplID)) {
                doProcess_NPAL1280_NPAL0170(bizMsg, glblMsg);
            } else if (NPAL1280_GET_MDSE_INFO.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_Get_MdseInfo(bizMsg, glblMsg, glblCmpyCd, salesDate);
            // QC#21169
            } else if (NPAL1280_APPLY.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_Apply(bizMsg, glblMsg);
            // START 2023/02/02 T.Kuroda [QC#60966, ADD]
            } else if (NPAL1280_RQSTSHIPDTAPPLY.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_RqstShipDtApply(bizMsg, glblMsg);
            // END   2023/02/02 T.Kuroda [QC#60966, ADD]
            //QC#26990 Add Start
            } else if (NPAL1280_NMAL6760.equals(screenAplID)) {
                doProcess_NPAL1280Scrn00_Get_ShipToInfoCustomer(bizMsg, glblMsg, glblCmpyCd);
            // QC#26990 Add End
            // QC#28216
            } else if (NPAL1280_NWAL1130.equals(screenAplID)) {
                doProcess_setMultipleHeader(bizMsg, glblMsg);
            } else if (NPAL1280_NMAL2550.equals(screenAplID)) { // 2019/01/15 QC#29778 Add Start
                doProcess_NPAL1280_NMAL2550(bizMsg, glblMsg); // 2019/01/15 QC#29778 Add End
            } else if (NPAL1280_GET_ADDRESS.equals(screenAplID)) {
                // QC#28709 Add Event.
                doProcess_GetAddress(bizMsg, glblMsg);
            // QC#53300 2019/10/04 Add Start
            } else if ((EVENT_NM_GET_SUPPLIER_NAME.equals(screenAplID))) {
            	doProcess_Get_SupplierName(bizMsg, glblMsg);
            } else if ((EVENT_NM_GET_SUPPLIER_SITE_NAME.equals(screenAplID))) {
            	doProcess_Get_SupplierSiteName(bizMsg, glblMsg, glblCmpyCd, salesDate);
            } else if ((EVENT_NM_NPAL1280_GET_SHIP_TO_INFO.equals(screenAplID))) {
            	doProcess_NPAL1280Scrn00_Get_ShipToInfo(bizMsg, glblMsg, glblCmpyCd);
            	doProcess_NPAL1280_NPAL1010(bizMsg, glblMsg, glblCmpyCd);
            } else if ((EVENT_NM_NPAL1280_GET_SHIP_TO_INFO_CUSTOMER.equals(screenAplID))) {
            	doProcess_NPAL1280Scrn00_Get_ShipToInfoCustomer(bizMsg, glblMsg, glblCmpyCd);
            // QC#53300 2019/10/04 Add End
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
            // START 2022/10/31 N.Takatsu[QC#60604, ADD]
            if (NPAL1280_SEARCH.equals(screenAplID) || NPAL1280_CMN_RESET.equals(screenAplID) || NPAL1280_CMN_CLEAR.equals(screenAplID)) {
                bizMsg.xxWrnSkipFlg_A.clear();
            }
            // END 2022/10/31 N.Takatsu[QC#60604, ADD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * Init Event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     */
    private void doProcess_NPAL1280_INIT(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        String prchReqNum = cMsg.prchReqNum_HD.getValue();
        cMsg.clear();
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, prchReqNum);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_HD, prchReqNum);
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        // Set Pulldown Lists
        NPAL1280CommonLogic.setRequisitionTypePulldown(glblCmpyCd, cMsg);
        NPAL1280CommonLogic.setBusinessUnitPulldown(glblCmpyCd, cMsg);
        NPAL1280CommonLogic.setFreightTermPulldown(cMsg);
        NPAL1280CommonLogic.setShipMethodPulldown(cMsg);
        NPAL1280CommonLogic.setAmPmPulldownList(cMsg);
        //QC#28400 mod start
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_GOODS, cMsg);
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_ASSETS, cMsg);
        NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, cMsg);
        // QC#28400 mod start
        if (ZYPCommonFunc.hasValue(prchReqNum)) {
            doProcess_NPAL1280_SEARCH(cMsg, sMsg, glblCmpyCd);
        } else {
            // QC#15325
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, salesDate);
            ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, salesDate);
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, salesDate);
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.PO_REQUISITION_ENTRY);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpNm, ZYPCodeDataUtil.getName(PRCH_REQ_SRC_TP.class, glblCmpyCd, PRCH_REQ_SRC_TP.PO_REQUISITION_ENTRY));
            String fullName = getUserProfileService().getContextUserInfo().getFullName();
            if (ZYPCommonFunc.hasValue(fullName)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, fullName);
            } else {
                cMsg.fullPsnNm.clear();
            }
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, getUserProfileService().getContextUserInfo().getUserId());
        }
    }

    /**
     * Clear button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     */
    private void doProcess_NPAL1280_Clear(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate) {
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        String prchReqNum = cMsg.prchReqNum_HD.getValue();
        cMsg.clear();
        // START 2022/10/31 N.Takatsu[QC#60604, ADD]
        cMsg.glblCmpyCd.setValue(glblCmpyCd);
        // END 2022/10/31 N.Takatsu[QC#60604, ADD]
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);

        // Set Pulldown Lists
        NPAL1280CommonLogic.setRequisitionTypePulldown(glblCmpyCd, cMsg);
        NPAL1280CommonLogic.setBusinessUnitPulldown(glblCmpyCd, cMsg);
        NPAL1280CommonLogic.setFreightTermPulldown(cMsg);
        NPAL1280CommonLogic.setShipMethodPulldown(cMsg);
        NPAL1280CommonLogic.setAmPmPulldownList(cMsg);
        //QC#28400 mod start
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_GOODS, cMsg);
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_ASSETS, cMsg);
        NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, cMsg);
        // QC#28400 mod start
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_HD, prchReqNum);
        // QC#15325
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt, salesDate);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt, salesDate);
        // START 2023/02/03 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.rqstShipDt, salesDate);
        // END   2023/02/03 T.Kuroda [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpCd, PRCH_REQ_SRC_TP.PO_REQUISITION_ENTRY);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpNm, ZYPCodeDataUtil.getName(PRCH_REQ_SRC_TP.class, glblCmpyCd, PRCH_REQ_SRC_TP.PO_REQUISITION_ENTRY));
        String fullName = getUserProfileService().getContextUserInfo().getFullName();
        if (ZYPCommonFunc.hasValue(fullName)) {
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, fullName);
        } else {
            cMsg.fullPsnNm.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqRqstByPsnCd, getUserProfileService().getContextUserInfo().getUserId());
    }

    /**
     * concatinate charge account
     * @param cMsg NPAL1280CMsg
     * @param glblCmpyCd String
     * @return String xx.xx.xx.xx.xx
     */
    private String concatChgAccount(NPAL1280CMsg cMsg, String prchReqLineTpCd) {
        StringBuilder sb = new StringBuilder();
        if (PRCH_REQ_LINE_TP.GOODS.equals(prchReqLineTpCd)) {
            sb.append(cMsg.coaCmpyCd_GO.getValue());
            sb.append(".");
            sb.append(cMsg.coaAfflCd_GO.getValue());
            sb.append(".");
            sb.append(cMsg.coaCcCd_GO.getValue());
            sb.append(".");
            sb.append(cMsg.coaProdCd_GO.getValue());
            sb.append(".");
            sb.append(cMsg.coaChCd_GO.getValue());
            sb.append(".");
            sb.append(cMsg.coaProjCd_GO.getValue());
            sb.append(".");
            sb.append(cMsg.coaExtnCd_GO.getValue());
        } else {
            sb.append(cMsg.coaCmpyCd_AS.getValue());
            sb.append(".");
            sb.append(cMsg.coaAfflCd_AS.getValue());
            sb.append(".");
            sb.append(cMsg.coaCcCd_AS.getValue());
            sb.append(".");
            sb.append(cMsg.coaProdCd_AS.getValue());
            sb.append(".");
            sb.append(cMsg.coaChCd_AS.getValue());
            sb.append(".");
            sb.append(cMsg.coaProjCd_AS.getValue());
            sb.append(".");
            sb.append(cMsg.coaExtnCd_AS.getValue());
        }
        // QC#28143
        if ("......".equals(sb.toString())) {
            return "";
        }
        return sb.toString();
    }

    /**
     * Search button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     */
    private void doProcess_NPAL1280_SEARCH(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd) {
        NPAL1280CommonLogic.setRequisitionTypePulldown(glblCmpyCd, cMsg);
        //QC#28400 mod start
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_GOODS, cMsg);
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_ASSETS, cMsg);
        NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, cMsg);
        // QC#28400 mod start
        NPAL1280CommonLogic.getPoReqList(cMsg, sMsg, glblCmpyCd);
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        NPAL1280CommonLogic.getPoMsg(cMsg, glblCmpyCd);
    }

    /**
     * Header cancel button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     */
    private void doProcess_NPAL1280_HederOrLineCancel(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd) {
        if (ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            String prchReqNum = cMsg.prchReqNum.getValue();
            //QC#28400 mod start
            //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_GOODS, cMsg);
            //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_ASSETS, cMsg);
            NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, cMsg);
            // QC#28400 mod start
            if (ZYPConstant.FLG_OFF_N.equals(sMsg.xxSrchTrfFlg_HD.getValue())) {
                return;
            } else {
                cMsg.prchReqNum.setValue(prchReqNum);
                NPAL1280CommonLogic.getPoReqList(cMsg, sMsg, glblCmpyCd);
                // QC#28216
                NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
            }
        }
    }

    /**
     * NPAL1010 Popup return event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     */
    private void doProcess_NPAL1280_NPAL1010(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd) {
        if (!(ZYPCommonFunc.hasValue(cMsg.locNm_HF))) {
            S21SsmEZDResult ssmResult = NPAL1280Query.getInstance().getPreferedCarrier(glblCmpyCd, cMsg.destRtlWhCd.getValue());
            if (ssmResult.isCodeNormal()) {
                List<Map<String, String>> list = (List) ssmResult.getResultObject();
                for (int i = 0; i < list.size(); i++) {
                    Map<String, String> map = list.get(i);
                    ZYPEZDItemValueSetter.setValue(cMsg.carrCd_HF, map.get(RS_PRF_CARR_CD));
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, map.get(RS_CARR_NM));
                }
            }
        }
    }

    /**
     * Page next event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_Next(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length());
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Page prev event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_Prev(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Copy button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     * @param loginUserNm String
     */
    private void doProcess_NPAL1280Scrn00_Copy(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId, String loginUserNm) {
        if (!(ZYPCommonFunc.hasValue(cMsg.prchReqNum)) && !(ZYPCommonFunc.hasValue(cMsg.cpoOrdNum))) {
            cMsg.setMessageInfo(ZZM9037E);
            cMsg.prchReqNum.setErrorInfo(1, NAMM0027E, new String[] {REQNUM_OR_ORDER_REL });
            return;
        }
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        String prchReqNum = cMsg.prchReqNum.getValue();
        String cpoOrdNum = cMsg.cpoOrdNum.getValue();
        // QC#27846
        String dsOrdPosnNum = cMsg.dsOrdPosnNum.getValue();
        String prchReqNumHd = cMsg.prchReqNum_HD.getValue();
        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        cMsg.prchReqNum_HD.setValue(prchReqNumHd);
        // Set Pulldown Lists
        NPAL1280CommonLogic.setRequisitionTypePulldown(glblCmpyCd, cMsg);
        NPAL1280CommonLogic.setBusinessUnitPulldown(glblCmpyCd, cMsg);
        NPAL1280CommonLogic.setFreightTermPulldown(cMsg);
        NPAL1280CommonLogic.setShipMethodPulldown(cMsg);
        NPAL1280CommonLogic.setAmPmPulldownList(cMsg);
        //QC#28400 mod start
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_GOODS, cMsg);
        //NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, APP_FUNC_ID_ASSETS, cMsg);
        NPAL1280CommonLogic.getDefAccInfo(glblCmpyCd, cMsg);
        // QC#28400 mod start
        if (ZYPCommonFunc.hasValue(prchReqNum)) {
            // PR# Search
            cMsg.prchReqNum.setValue(prchReqNum);
            NPAL1280CommonLogic.copyPoReqList(cMsg, sMsg, glblCmpyCd, salesDate, loginUserId, loginUserNm);
        } else {
            // Copy
            cMsg.cpoOrdNum.setValue(cpoOrdNum);
            // QC#27846
            cMsg.dsOrdPosnNum.setValue(dsOrdPosnNum);
            NPAL1280CommonLogic.copyCpoNumList(cMsg, sMsg, glblCmpyCd, salesDate, loginUserId, loginUserNm, ZYPCodeDataUtil.getVarCharConstValue(PO_ACRL_ACCT_TP, glblCmpyCd));
        }
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
    }

    /**
     * Add new line event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     */
    private void doProcess_NPAL1280Scrn00_AddNewLine(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd) {
        // QC#21170 Start
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        NPAL1280CommonLogic.getHeaderRqstRcvDt(cMsg, sMsg, salesDate) ;
        // QC#21170 End
        // START 2023/02/03 T.Kuroda [QC#60966, ADD]
        NPAL1280CommonLogic.getHeaderRqstShipDt(cMsg, sMsg, salesDate);
        // END   2023/02/03 T.Kuroda [QC#60966, ADD]
        //QC#26990 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_CP) && !cMsg.shipToCustCd_CP.getValue().equals(cMsg.shipToCustCd.getValue())) {
            NPAL1280CommonLogic.setManualDropWh(cMsg, sMsg, glblCmpyCd);
            String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.shipToCustCd.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
            }
        } else if (!ZYPCommonFunc.hasValue(cMsg.shipToCustCd_CP)) {
            NPAL1280CommonLogic.setManualDropWh(cMsg, sMsg, glblCmpyCd);
            String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.shipToCustCd.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
            }
        }
        //QC#26990 Add End
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1280CommonLogic.addNewLine(cMsg, sMsg, glblCmpyCd, concatChgAccount(cMsg, PRCH_REQ_LINE_TP.GOODS));
        // QC#28216

        // START 03/19/2020 [QC#56122,ADD]
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if(!NPAL1280CommonLogic.checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, i)){
                cMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                return;
            }
        }
        // END 03/19/2020 [QC#56122,ADD]

        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * OnBlue item event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     */
    private void doProcess_NPAL1280Scrn00_OnBlur_ItemCode(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate) {
        int lineIndex = Integer.parseInt(cMsg.xxLineNum.getValue());
        if (!(ZYPCommonFunc.hasValue(sMsg.A.no(lineIndex).mdseCd_A1)) && cMsg.A.no(lineIndex).mdseCd_A1.getValue().equals(cMsg.A.no(lineIndex).mdseCd_HD.getValue())) {
            return;
        }
        cMsg.A.no(lineIndex).aslMdseCd_A1.clear();
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1280CommonLogic.onBlurItemCode(cMsg, sMsg, glblCmpyCd, salesDate, Integer.parseInt(cMsg.xxLineNum.getValue()));
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * OnBlue order qty event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_OnBlur_OrderQty(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        int lineNum = Integer.parseInt(cMsg.xxLineNum.getValue());

        if (ZYPCommonFunc.hasValue(sMsg.A.no(lineNum).prchReqDispQty_A1) && !(sMsg.A.no(lineNum).prchReqDispQty_A1.getValue().equals(sMsg.A.no(lineNum).prchReqDispQty_HD.getValue()))) {
            if (sMsg.A.no(lineNum).prchReqDispQty_A1.getValueInt() <= 0) {
                sMsg.A.no(lineNum).prchReqDispQty_A1.setErrorInfo(1, NPAM0046E, new String[] {ORDER_QTY_D });
            }
            if (sMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.getValueInt() < 0) {
                sMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
            }
        }
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * OnBlue unit price event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_OnBlur_UnitPrice(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        int lineNum = Integer.parseInt(cMsg.xxLineNum.getValue());

        if (ZYPCommonFunc.hasValue(sMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1) && !(sMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.getValue().equals(sMsg.A.no(lineNum).entDealNetUnitPrcAmt_HD.getValue()))) {
            if (sMsg.A.no(lineNum).prchReqDispQty_A1.getValueInt() <= 0) {
                sMsg.A.no(lineNum).prchReqDispQty_A1.setErrorInfo(1, NPAM0046E, new String[] {ORDER_QTY_D });
            }
            if (sMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.getValueInt() < 0) {
                sMsg.A.no(lineNum).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
            }
        }
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Auto create button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param salesDate String
     */
    private void doProcess_NPAL1280_AutoCreatePO(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate) {
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        int chkCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {

                // validate PR Approval Status
                if (NPAL1280CommonLogic.checkPrApvlStatus(cMsg, glblCmpyCd, i)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).poSchdRelDt_A1, salesDate);
                    chkCount++;
                } else {
                    cMsg.setMessageInfo(NPAM1544E);
                    return;
                }
            }
        }
        if (chkCount == 0) {
            cMsg.setMessageInfo(NPAM0049E);
        }
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * CSV Download event
     * @param bizMsg NPAL1280CMsg
     * @param glblCmpyCd String
     */
    private void doProcess_NPAL1280Scrn00_CMN_Download(NPAL1280CMsg bizMsg, String glblCmpyCd) {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), EXTN_CSV);

        boolean isNormalEnd = NPAL1280Query.getInstance().getPoReqListForCsv(bizMsg, glblCmpyCd, MAX_DOWNLOAD_CNT);

        // nothing data.
        if (!isNormalEnd) {
            return;
        }
    }

    /**
     * return from NPAL0170 event
     * @param bizMsg NPAL1280CMsg
     * @param glblCmpyCd String
     */
    private void doProcess_NPAL1280_NPAL0170(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
    }

    /**
     * doProcess_NPAL1280Scrn00_Get_MdseInfo
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     */
    private void doProcess_NPAL1280Scrn00_Get_MdseInfo(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate) {

        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());

        // QC#27770 MOD START
        int idx = Integer.valueOf(cMsg.xxLineNum.getValue());

        // Index of SMsg Line
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;
        // QC#27770 MOD END

        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        //QC#62443 2024/03/04 Add Start
        NPAL1280CommonLogic.checkItemCode(cMsg, sMsg, glblCmpyCd, salesDate, Integer.valueOf(cMsg.xxLineNum.getValue()));

        MDSETMsg mdseTMsg = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(indexS).mdseCd_A1.getValue());
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
        //QC#62443 2024/03/04 Add End
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        NPAL1280CommonLogic.checkLineType(cMsg, sMsg, indexS, glblCmpyCd);
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        //QC#62443 2024/03/04 Add Start
        if (mdseTMsg != null) {
            String varCharExpSplyCoaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CMPY_CD, cMsg.glblCmpyCd.getValue());
            String varCharExpSplyCoaBrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_BR_CD, cMsg.glblCmpyCd.getValue());
            String varCharExpSplyCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_ACCT_CD, cMsg.glblCmpyCd.getValue());
            String varCharExpSplyCoaChCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CH_CD, cMsg.glblCmpyCd.getValue());
            String varCharExpSplyCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_AFFL_CD, cMsg.glblCmpyCd.getValue());
            String varCharExpSplyCoaProjCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_PROJ_CD, cMsg.glblCmpyCd.getValue());
            String varCharExpSplyCoaExtnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_EXTN_CD, cMsg.glblCmpyCd.getValue());

            S21SsmEZDResult result = NPAL1280Query.getInstance().getCoaCcCd(cMsg.glblCmpyCd.getValue(), sMsg.A.no(indexS).mdseCd_A1.getValue());

            String splyCoaCcCd = (String) result.getResultObject();

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).coaCcCd_A1, splyCoaCcCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).coaProdCd_A1, mdseTMsg.coaProdCd.getValue());

            StringBuffer sb = new StringBuffer();
            if (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(indexS).prchReqLineTpCd_A1.getValue())) {

                sb.append(varCharExpSplyCoaCmpyCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaBrCd);
                sb.append(PERIOD);
                sb.append(sMsg.A.no(indexS).coaCcCd_A1.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAcctCd);
                sb.append(PERIOD);
                sb.append(sMsg.A.no(indexS).coaProdCd_A1.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaChCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAfflCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaProjCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaExtnCd);

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).xxScrItem130Txt_A1, sb.toString());

            } else if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue()) && !MDSE_TP_SET.equals(mdseTMsg.mdseTpCd.getValue())) {

                sb.append(varCharExpSplyCoaCmpyCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaBrCd);
                sb.append(PERIOD);
                sb.append(sMsg.A.no(indexS).coaCcCd_A1.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAcctCd);
                sb.append(PERIOD);
                sb.append(sMsg.A.no(indexS).coaProdCd_A1.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaChCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAfflCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaProjCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaExtnCd);

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).xxScrItem130Txt_A1, sb.toString());
            }
        }
        //QC#62443 2024/03/04 Add End
        // QC#27770 MOD START
        // 2019/09/24 QC#53422 Add Start
        //QC#62443 2024/3/4 Del Start
//    	boolean checkSegment = false;
//    	checkSegment = NPAL1280CommonLogic.checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, indexS);
        // QC#27770 MOD END
//        if (checkSegment) {
//        	NPAL1280CommonLogic.checkItemCode(cMsg, sMsg, glblCmpyCd, salesDate, Integer.valueOf(cMsg.xxLineNum.getValue()));
//        }
        // 2019/09/24 QC#53422 Add End
        // START 03/24/2020 [QC#56122,ADD]
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//            if (!NPAL1280CommonLogic.checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, i)) {
//                if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
//                    cMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
//                }
//                return;
//            }
//        }
        //QC#62443 2024/3/4 Del End
        // END 03/24/2020 [QC#56122,ADD]

        // QC#14858(Sol#060) MOD START
        // 2018/12/17 QC#29456 Mod Start
//        NPAL1280CommonLogic.getMdseAccount(cMsg, sMsg, glblCmpyCd, Integer.valueOf(cMsg.xxLineNum.getValue()));
        // 2019/01/15 QC#29778 Del Start
//        if (!sMsg.A.no(indexS).mdseCd_A1.isError()) {
//            NPAL1280CommonLogic.setDefaultChargAccount(sMsg.A.no(indexS), glblCmpyCd, cMsg.shipToCustCd.getValue());
//        }
        // 2019/01/15 QC#29778 Del End
        // 2018/12/17 QC#29456 Mod End
        // QC#14858(Sol#060) MOD END
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        // QC#21170
        NPAL1280CommonLogic.getHeaderRqstRcvDt(cMsg, sMsg, salesDate) ;
        // START 2023/02/03 T.Kuroda [QC#60966, ADD]
        NPAL1280CommonLogic.getHeaderRqstShipDt(cMsg, sMsg, salesDate);
        // END   2023/02/03 T.Kuroda [QC#60966, ADD]
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * doProcess_OnChangeLineType
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     */
    private void doProcess_OnChangeLineType(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate) {
        // QC#29729
        int index = Integer.valueOf(cMsg.xxLineNum.getValue());

        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
     // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + index;
        //QC#62443 2024/03/04 Add Start
        String varCharExpSplyCoaCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CMPY_CD, cMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaBrCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_BR_CD, cMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_ACCT_CD, cMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaChCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_CH_CD, cMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaAfflCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_AFFL_CD, cMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaProjCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_PROJ_CD, cMsg.glblCmpyCd.getValue());
        String varCharExpSplyCoaExtnCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_EXP_SPLY_COA_EXTN_CD, cMsg.glblCmpyCd.getValue());

        StringBuffer sb = new StringBuffer();
        //QC#62443 2024/03/04 Add End
        NPAL1280CommonLogic.checkLineType(cMsg, sMsg, indexS, glblCmpyCd);
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        //QC#62443 2024/03/04 Add Start
        if (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(indexS).prchReqLineTpCd_A1.getValue())) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(indexS).mdseCd_A1.getValue())) {

                MDSETMsg mdseTMsg = new MDSETMsg();

                ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, cMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, sMsg.A.no(indexS).mdseCd_A1.getValue());
                mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

                S21SsmEZDResult result = NPAL1280Query.getInstance().getCoaCcCd(cMsg.glblCmpyCd.getValue(), sMsg.A.no(indexS).mdseCd_A1.getValue());

                String splyCoaCcCd = (String) result.getResultObject();
                sb.append(varCharExpSplyCoaCmpyCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaBrCd);
                sb.append(PERIOD);
                sb.append(splyCoaCcCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAcctCd);
                sb.append(PERIOD);
                sb.append(mdseTMsg.coaProdCd.getValue());
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaChCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaAfflCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaProjCd);
                sb.append(PERIOD);
                sb.append(varCharExpSplyCoaExtnCd);

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).xxScrItem130Txt_A1, sb.toString());

        } else if (!ZYPCommonFunc.hasValue(sMsg.A.no(indexS).mdseCd_A1.getValue())) {

            sb.append(varCharExpSplyCoaCmpyCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaBrCd);
            sb.append(PERIOD);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAcctCd);
            sb.append(PERIOD);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaChCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaAfflCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaProjCd);
            sb.append(PERIOD);
            sb.append(varCharExpSplyCoaExtnCd);

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(indexS).xxScrItem130Txt_A1, sb.toString());
        }
        }
        //QC#62443 2024/03/04 Add End
        // QC#27770 MOD START
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // QC#29729
            if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prchReqLineTpCd_A1, sMsg.A.no(i - 1).prchReqLineTpCd_A1);
            }

            if (!(ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineTpCd_A1) //
                    && (PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue()) // 
                    || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(sMsg.A.no(i).prchReqLineTpCd_A1.getValue())))) {

                sMsg.A.no(i).coaCmpyCd_A1.clear();
                sMsg.A.no(i).coaBrCd_A1.clear();
                sMsg.A.no(i).coaCcCd_A1.clear();
                sMsg.A.no(i).coaAcctCd_A1.clear();
                sMsg.A.no(i).coaProdCd_A1.clear();
                sMsg.A.no(i).coaChCd_A1.clear();
                sMsg.A.no(i).coaAfflCd_A1.clear();
                sMsg.A.no(i).coaProjCd_A1.clear();
                sMsg.A.no(i).coaExtnCd_A1.clear();
                sMsg.A.no(i).xxScrItem130Txt_A1.clear();
            }

          //QC#62443 2024/03/04 Del Start
//            if(!NPAL1280CommonLogic.checkManualSegmentElementForSMsg(cMsg, sMsg, glblCmpyCd, i)){
//                cMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
//                return;
//            }
          //QC#62443 2024/03/04 Del End
        }
        // QC#27770 MOD END
        //QC#62443 2024/03/04 Add Start
        // QC#14858(Sol#060) MOD START
        // QC#29729 Mod
        if (!PRCH_REQ_LINE_TP.EXPENSE.equals(sMsg.A.no(indexS).prchReqLineTpCd_A1.getValue())) {
            NPAL1280CommonLogic.getDefaultAccount(cMsg, sMsg, glblCmpyCd, index);
            for (int i = index + 1; i < sMsg.A.getValidCount(); i++) {
                if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                    NPAL1280CommonLogic.getDefaultAccount(cMsg, sMsg, glblCmpyCd, i);
                } else {
                    break;
                }
            }
        }
        // QC#14858(Sol#060) MOD END
        //QC#62443 2024/03/04 Add End
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * Apply Add QC#21169
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_Apply(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.rqstRcvDt.getValue())) {

            bizMsg.rqstRcvDt.setErrorInfo(1, ZZM9000E, new String[] {DATE_NEEDED });
            return;
        }

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NPAM1360E);
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (ZYPCommonFunc.hasValue(bizMsg.rqstRcvDt)) {
            String rqstRcvDt = bizMsg.rqstRcvDt.getValue();
            if (ZYPDateUtil.compare(rqstRcvDt, slsDt) < 0) {
                bizMsg.rqstRcvDt.setErrorInfo(1, NPAM0079E, new String[] {DATE_NEEDED });
                return;
            }
            // START 2019/03/20 T.Ogura [QC#30769,DEL]
//            if (!(ZYPDateUtil.isBusinessDay(glblCmpyCd, rqstRcvDt))) {
//                bizMsg.rqstRcvDt.setErrorInfo(1, NPAM0094E, new String[] {DATE_NEEDED });
//                return;
//            }
            // END   2019/03/20 T.Ogura [QC#30769,DEL]
        }

        // Copy Message
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue()) //
                    && !(ZYPCommonFunc.hasValue(glblMsg.A.no(i).openStsFlg_D) // 
                    && ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openStsFlg_D.getValue())) ) {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).rqstRcvDt_A1, bizMsg.rqstRcvDt);
            }
            // START 2023/04/28 S.Dong [QC#60966, ADD]
            if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).vndCd_A1)) {
                S21SsmEZDResult aslResult = NPAL1280Query.getInstance().findAslDtl(glblCmpyCd, glblMsg.A.no(i).vndCd_A1.getValue(), glblMsg.A.no(i).mdseCd_A1.getValue(), slsDt);
                if (aslResult.isCodeNormal()) {
                    List<Map<String, Object>> aslResultList = (List) aslResult.getResultObject();
                    if (aslResultList != null && aslResultList.size() > 0) {
                        Map<String, Object> aslMap = aslResultList.get(0);
                        NPAL1280CommonLogic.setRqstShipDtRddApply(glblMsg, aslMap, glblCmpyCd, i);
                    }
                }
            }
            // END 2023/04/28 S.Dong [QC#60966, ADD]
        }
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(bizMsg, glblMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Apply" });
    }

    // START 2023/02/02 T.Kuroda [QC#60966, ADD]
    /**
     * Vendor Ship Date Apply
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_RqstShipDtApply(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.rqstShipDt.getValue())) {
            bizMsg.rqstShipDt.setErrorInfo(1, ZZM9000E, new String[] {VENDOR_SHIP_DATE });
            return;
        }

        if (bizMsg.A.getValidCount() == 0) {
            bizMsg.setMessageInfo(NPAM1360E);
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        if (ZYPCommonFunc.hasValue(bizMsg.rqstShipDt)) {
            String rqstShipDt = bizMsg.rqstShipDt.getValue();
            if (ZYPDateUtil.compare(rqstShipDt, slsDt) < 0) {
                bizMsg.rqstShipDt.setErrorInfo(1, NPAM0079E, new String[] {VENDOR_SHIP_DATE });
                return;
            }
        }

        // Copy Message
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            if (!PO_MDSE_CMPSN_TP.CHILD.equals(glblMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())
                    && !(ZYPCommonFunc.hasValue(glblMsg.A.no(i).openStsFlg_D)
                    && ZYPConstant.FLG_OFF_N.equals(glblMsg.A.no(i).openStsFlg_D.getValue())) ) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).rqstShipDt_A1, bizMsg.rqstShipDt);
            }
        }

        NPAL1280CommonLogic.setMultipleHeader(bizMsg, glblMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        bizMsg.setMessageInfo(ZZZM9003I, new String[] {"Vendor Ship Date Apply"});
    }
    // END   2023/02/02 T.Kuroda [QC#60966, ADD]

    //QC#26990 Add Start
    /**
     * doProcess_NPAL1280Scrn00_Get_ShipToInfoLocation
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_Get_ShipToInfoCustomer(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd) {
    	ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);
    	if (NPAL1280CommonLogic.setShipToAddressFromShipToCustomer(bizMsg, glblMsg)) {
    		NPAL1280CommonLogic.setManualDropWh(bizMsg, glblMsg, glblCmpyCd);
            String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(bizMsg.shipToCustCd.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                NPAL1280CommonLogic.getBillToLocation(bizMsg, glblCmpyCd, null, sellToCustCd);
            }
    	}
    }
    //QC#26990 Add End

    /**
     * doProcess_setMultipleHeader. Add QC#28216
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     */
    private void doProcess_setMultipleHeader(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg) {
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    // 2019/01/15 QC#29778 Add Start
    /**
     * doProcess_NPAL1280_NMAL2550. Add QC#29778
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NPAL1280_NMAL2550(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {

        int idx = Integer.valueOf(bizMsg.xxLineNum.getValue());

        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

        // Index of SMsg Line
        int pageShowFromNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        int indexS = pageShowFromNum + idx;

        // check SetItem
        if (PO_MDSE_CMPSN_TP.PARENT.equals(bizMsg.A.no(idx).poMdseCmpsnTpCd_A1.getValue())) {
            // Set sMsg
            setChargeAccount(glblMsg, indexS);
        }

        EZDMsg.copy(glblMsg.A.no(indexS), null, bizMsg.A.no(idx), null);
        // renew details
        NPAL1280CommonLogic.setMultipleHeader(bizMsg, glblMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
    }

    /**
     * <pre>
     * setChargeAccount for set child
     * @param glblMsg NPAL1280SMsg
     * @param indexS index of Set Parent line in Global Message "A" table.
     * </pre>
     */
    private void setChargeAccount(NPAL1280SMsg glblMsg, int indexS) {

        NPAL1280_ASMsg prntSMsg = glblMsg.A.no(indexS);
        String lineNum = prntSMsg.prchReqLineNum_A1.getValue();
        String prchReqLineTp = prntSMsg.prchReqLineTpCd_A1.getValue();

        for (int i = indexS + 1; i < glblMsg.A.getValidCount(); i++) {
            String chkLine = glblMsg.A.no(i).prchReqLineNum_A1.getValue();

            if (S21StringUtil.isEquals(chkLine, lineNum)) {

                if (PRCH_REQ_LINE_TP.EXPENSE.equals(prchReqLineTp) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(prchReqLineTp)) {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaBrCd_A1, prntSMsg.coaBrCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaChCd_A1, prntSMsg.coaChCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAfflCd_A1, prntSMsg.coaAfflCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaExtnCd_A1, prntSMsg.coaExtnCd_A1);

                    NPAL1280CommonLogic.fillChrgAccountText(glblMsg.A.no(i));
                } else {
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaCmpyCd_A1, prntSMsg.coaCmpyCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAfflCd_A1, prntSMsg.coaAfflCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaBrCd_A1, prntSMsg.coaBrCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaCcCd_A1, prntSMsg.coaCcCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaChCd_A1, prntSMsg.coaChCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaAcctCd_A1, prntSMsg.coaAcctCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProdCd_A1, prntSMsg.coaProdCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProjCd_A1, prntSMsg.coaProjCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaExtnCd_A1, prntSMsg.coaExtnCd_A1);
                    ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxScrItem130Txt_A1, prntSMsg.xxScrItem130Txt_A1);
                }
            } else {
                break;
            }
        }
    }
    // 2019/01/15 QC#29778 Add End
    
    /**
     * doProcess_GetAddress QC#28709 Add
     * @param bizMsg
     * @param glblMsg
     */
    private void doProcess_GetAddress(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
        List<Map<String, Object>> list = null;
        String postCd = bizMsg.shipToPostCd_HS.getValue();

        S21SsmEZDResult res = NPAL1280Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NPAL1280Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));

                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            bizMsg.shipToPostCd_HS.setErrorInfo(1, NMAM0039E, new String[] {"Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr_HS, listCtyAddr.get(0));
            } else {
                bizMsg.shipToCtyAddr_HS.clear();
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd_HS, listStCd.get(0));
            } else {
                bizMsg.shipToStCd_HS.clear();
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm_HS, listCntyNm.get(0));
            } else {
                bizMsg.shipToCntyNm_HS.clear();
            }
        }
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

    // QC#53300 2019/10/04 Add Start
    /**
     * doProcess_Get_SupplierName
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     */
    private void doProcess_Get_SupplierName(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.prntVndCd)) {
            S21SsmEZDResult result = NPAL1280Query.getInstance().getPrtVndNm(getGlobalCompanyCode(), bizMsg.prntVndCd.getValue());

            if (result.isCodeNormal()) {
                String prntName = (String) result.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.prntVndNm, prntName);
            } else {
                bizMsg.prntVndCd.setErrorInfo(1, NPAM0076E, new String[] {DISPLAY_PRNT_VND_CD });
            }
        }
    }

    /**
     * doProcess_Get_SupplierSiteName
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     * @param glblCmpyCd String
     */
    private void doProcess_Get_SupplierSiteName(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd, String salesDate) {

        if (NPAL1280CommonLogic.getSupplierName(bizMsg, glblMsg, glblCmpyCd)) {
        	// Detail
        	int maxDisplayRows = bizMsg.A.length();
        	int errScrnInex = -1;
        	boolean isErr = false;
            NPAL1280CommonLogic.copyFromCmsgOntoSmsg(bizMsg, glblMsg);

            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            	ZYPEZDItemValueSetter.setValue(bizMsg.xxLineNum, String.valueOf(i));
            	if (!NPAL1280CommonLogic.changeSupplierApplyToDetail(bizMsg, glblMsg, glblCmpyCd, salesDate, i)) {
                    if (errScrnInex == -1) {
                        errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }

                // START 03/24/2020 [QC#56122,ADD]
                if (!NPAL1280CommonLogic.checkManualSegmentElementForSMsg(bizMsg, glblMsg, glblCmpyCd, i)) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.getMessageCode())) {
                        bizMsg.setMessageInfo(NLAM0077E, new String[] {"Charge ACC" });
                    }
                    if (errScrnInex == -1) {
                        errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                // END 03/24/2020 [QC#56122,ADD]
            }

            if (isErr) {
            	bizMsg.xxPageShowFromNum.setValue(errScrnInex);
            }
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(bizMsg, glblMsg);
        }
    }

    /**
     * doProcess_NPAL1280Scrn00_Get_ShipToInfo
     * @param bizMsg NPAL1280CMsg
     * @param glblMsg NPAL1280SMsg
     */
    private void doProcess_NPAL1280Scrn00_Get_ShipToInfo(NPAL1280CMsg bizMsg, NPAL1280SMsg glblMsg, String glblCmpyCd) {

    	ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, glblCmpyCd);

        // Get WH Name & Address
        if (!ZYPCommonFunc.hasValue(bizMsg.destRtlWhCd)) {
            return;
        }
        NPAL1280CommonLogic.getDestWhInfo(bizMsg, glblMsg);
        if (bizMsg.destRtlWhCd.isError()) {
            return;
        }

        if (!NPAL1280CommonLogic.isManualDropShipWHCd(bizMsg.glblCmpyCd.getValue(), bizMsg.destRtlWhCd.getValue()) //
                && !(RTL_WH_CATG.TECH_CAR_STOCK.equals(bizMsg.rtlWhCatgCd.getValue()) || RTL_WH_CATG.CUSTOMER.equals(bizMsg.rtlWhCatgCd.getValue()))) {

        	NPAL1280CommonLogic.setShipToAddressFromDestinationWH(bizMsg, glblMsg, glblCmpyCd);
        }

    }

}
