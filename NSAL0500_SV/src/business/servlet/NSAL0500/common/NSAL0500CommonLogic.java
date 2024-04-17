/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0500.common;

import static business.servlet.NSAL0500.constant.NSAL0500Constant.*;

import java.util.Arrays;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL0500.NSAL0500CMsg;
import business.servlet.NSAL0500.NSAL0500BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/06   Hitachi         T.Tsuchida      Update          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/01/13   Hitachi         T.Tsuchida      Update          QC#2820
 * 2016/02/10   Hitachi         K.Kasai         Update          QC#3704
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2017/01/19   Hitachi         K.Ochiai        Update          QC#16331
 * 2018/12/10   Hitachi         K.Fujimoto      Update          QC#29079
 *</pre>
 */
public class NSAL0500CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * createCMsgForSearch
     * @return NSAL0500CMsg
     */
    public static NSAL0500CMsg createCMsgForSearch() {
        NSAL0500CMsg bizMsg = new NSAL0500CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    /**
     * createCMsgForUpdate
     * @return NSAL0500CMsg
     */
    public static NSAL0500CMsg createCMsgForUpdate() {
        NSAL0500CMsg bizMsg = new NSAL0500CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0500BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL0500BMsg scrnMsg) {

        initialize(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        screenControlByItemValue(handler, scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0500BMsg
     */
    @SuppressWarnings("unchecked")
    private static void initialize(EZDCommonHandler handler, NSAL0500BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        // set button property
        // common button
        List<List<String>> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN);
        for (List<String> button : cmnEnableButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
        // START 2017/01/19 K.Ochiai [QC#16331,MOD]
        disableButtons(handler, SAVE, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR);
        // END 2017/01/19 K.Ochiai [QC#16331,MOD]

        // optional button
        List<List<String>> optButtons = Arrays.asList(SEARCH_VNDNM, FILEATTACH, CREATENEW);
        for (List<String> button : optButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0500BMsg
     */
    @SuppressWarnings("unchecked")
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL0500BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Sub Contract Maintenance(NSAL0500). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        if (!funcIdList.contains(FUNC_ID_T020)) {
            // This user does not have Submit authority.
            disableButtons(handler, SUBMIT);
        }
    }

    /**
     * Control items and buttons on the screen by Item value.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0500BMsg
     */
    private static void screenControlByItemValue(EZDCommonHandler handler, NSAL0500BMsg scrnMsg) {

        disableItems(scrnMsg.serNum
                        , scrnMsg.mdlNm
                        , scrnMsg.prntVndNm
                        , scrnMsg.dsContrNum
                        , scrnMsg.dsContrCtrlStsNm
                        , scrnMsg.dsAcctNum
                        , scrnMsg.dsAcctNm);
        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.A.setInputProtected(true);
            setRowColors(scrnMsg, TABLE_A);
        }
        if (scrnMsg.B.getValidCount() > 0) {
            scrnMsg.B.setInputProtected(true);
            setRowColors(scrnMsg, TABLE_B);
        }
        if (scrnMsg.C.getValidCount() > 0) {
            scrnMsg.C.setInputProtected(true);
            setRowColors(scrnMsg, TABLE_C);
        }

        if (FLG_ON_Y.equals(scrnMsg.dlrFleetFlg.getValue())) {
            scrnMsg.dlrFleetNum.setInputProtected(false);
        } else {
            scrnMsg.dlrFleetNum.setInputProtected(true);
        }
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        for(int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.E.no(i).mtrLbDescTxt_E0.setInputProtected(true);
        }
        // Add End   2018/12/10 K.Fujimoto QC#29079
    }

    /**
     * clear popup parameter value
     * @param scrnMsg NSAL0500BMsg
     */
    public static void clearPopupParam(NSAL0500BMsg scrnMsg) {
        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // scrnMsg.xxPopPrm_01.clear();
        // scrnMsg.xxPopPrm_02.clear();
        // scrnMsg.xxPopPrm_03.clear();
        // scrnMsg.xxPopPrm_04.clear();
        // scrnMsg.xxPopPrm_05.clear();
        // scrnMsg.xxPopPrm_06.clear();
        // scrnMsg.xxPopPrm_07.clear();
        // scrnMsg.xxPopPrm_08.clear();
        // scrnMsg.xxPopPrm_09.clear();
        // scrnMsg.xxPopPrm_10.clear();
        // END 2016/12/14 K.Kojima [QC#15653,DEL]
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        ZYPTableUtil.clear(scrnMsg.D);
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
    }

    /**
     * addCheckAllItem without search criteria.
     * @param scrnMsg NSAL0500BMsg
     */
    public static void addCheckAllItem(NSAL0500BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.techTocCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_AD);
        scrnMsg.addCheckItem(scrnMsg.basePrcDealAmt);
        scrnMsg.addCheckItem(scrnMsg.adminPrcDealAmt);
        // Del Start 2018/12/10 K.Fujimoto QC#29079
        // scrnMsg.addCheckItem(scrnMsg.bwMtrAlwncCnt);
        // scrnMsg.addCheckItem(scrnMsg.bwMtrPrcAmtRate);
        // scrnMsg.addCheckItem(scrnMsg.colorMtrAlwncCnt);
        // scrnMsg.addCheckItem(scrnMsg.colorMtrPrcAmtRate);
        // Del End   2018/12/10 K.Fujimoto QC#29079
        scrnMsg.addCheckItem(scrnMsg.dlrFleetNum);
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        for(int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.E.no(i).mtrAlwncCnt_E0);
            scrnMsg.addCheckItem(scrnMsg.E.no(i).prcMtrRate_E0);
        }
        // Add End   2018/12/10 K.Fujimoto QC#29079
    }

    /**
     * Protect items
     * @param items EZDBItem
     */
    private static void disableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(true);
        }
    }

    /**
     * Protect buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm List<String>
     */
    private static void disableButtons(EZDCommonHandler handler, List<String>... btnHtmlNm) {
        for (List<String> btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm.get(0), false);
        }
    }
    /**
     * getTableId
     * @param ctx EZDApplicationContext
     * @return String
     */
    public static String getTableId(EZDApplicationContext ctx) {
        String rtnVal = "";
        String tblNm = (String) (((HttpDispatchContext) ctx.getDispatchContext()).getParameters().get("xxPagenationTableNm"))[0];
        if (TABLE_A.equals(tblNm)) {
            rtnVal = TABLE_A;
        } else if (TABLE_B.equals(tblNm)) {
            rtnVal = TABLE_B;
        } else {
            rtnVal = TABLE_C;
        }
        return rtnVal;
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0500BMsg
     * @param tableId String
     */
    public static void setRowColors(NSAL0500BMsg scrnMsg, String tableId) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField(tableId).get(scrnMsg);
            tblColor.setAlternateRowsBG(tableId, table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // add start 2016/02/10 CSA Defect#3704
    /**
     * protect Item Based On Contract Status
     * @param handler
     * @param scrnMsg
     */
    @SuppressWarnings("unchecked")
    public static void protectItemBasedOnContractStatus(EZDCommonHandler handler, NSAL0500BMsg scrnMsg) {
        if (DISPLAY_MODE_FREEZE.equals(scrnMsg.xxModeCd_FU.getValue())) {
            handler.setButtonEnabled(SUBMIT.get(0), false);
            // optional button
            List<List<String>> optButtons = Arrays.asList(SEARCH_VNDNM, FILEATTACH, CREATENEW);
            for (List<String> button : optButtons) {
                handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_INACTIVE, null);
            }
            //field item
            scrnMsg.vndCd_AC.setInputProtected(true);
            scrnMsg.vndCd.setInputProtected(true);
            scrnMsg.techTocCd_AC.setInputProtected(true);
            scrnMsg.techTocCd.setInputProtected(true);
            scrnMsg.contrTrmnFlg.setInputProtected(true);
            scrnMsg.effFromDt.setInputProtected(true);
            scrnMsg.effThruDt.setInputProtected(true);
            scrnMsg.svcCmntTxt_AD.setInputProtected(true);
            scrnMsg.basePrcDealAmt.setInputProtected(true);
            scrnMsg.adminPrcDealAmt.setInputProtected(true);
            scrnMsg.prepdMaintFlg.setInputProtected(true);
            // Del Start 2018/12/10 K.Fujimoto QC#29079
            // scrnMsg.bwMtrAlwncCnt.setInputProtected(true);
            // scrnMsg.bwMtrPrcAmtRate.setInputProtected(true);
            // scrnMsg.colorMtrAlwncCnt.setInputProtected(true);
            // scrnMsg.colorMtrPrcAmtRate.setInputProtected(true);
            // Del End   2018/12/10 K.Fujimoto QC#29079
            scrnMsg.splyInclFlg.setInputProtected(true);
            scrnMsg.dlrFleetFlg.setInputProtected(true);
            scrnMsg.bllgCycleCd.setInputProtected(true);
            scrnMsg.dlrFleetNum.setInputProtected(true);
            // Add Start 2018/12/10 K.Fujimoto QC#29079
            if (scrnMsg.E.getValidCount() > 0) {
                scrnMsg.E.setInputProtected(true);
            }
            // Add End   2018/12/10 K.Fujimoto QC#29079
        }
    }
    // add end 2016/02/10 CSA Defect#3704
}
