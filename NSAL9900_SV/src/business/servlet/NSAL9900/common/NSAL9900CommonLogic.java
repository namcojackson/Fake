/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL9900.common;

import static business.servlet.NSAL9900.constant.NSAL9900Constant.APPLY;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.APPROVE;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.BTN_ACTIVE;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.BUSINESS_ID;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.CLEAR;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.COL_TP_NUMBER;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.COPYROW;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.DELETE;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.DELETEROW;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.DOWNLOAD;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.FUNC_ID_T010;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.INSERTROW;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.MSG_ID_NSAM0219E;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.REJECT;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.RESET;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.RETURN;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.SAVE;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.SCREEN_ID;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.SEARCH;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.SELECTALL;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.SUBMIT;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.UNSELECTALL;
import static business.servlet.NSAL9900.constant.NSAL9900Constant.UPLOAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL9900.NSAL9900CMsg;
import business.servlet.NSAL9900.NSAL9900BMsg;
import business.servlet.NSAL9900.NSAL9900_ABMsg;
import business.servlet.NSAL9900.NSAL9900_BBMsg;
import business.servlet.NSAL9900.NSAL9900_CBMsg;
import business.servlet.NSAL9900.NSAL9900_CBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Master Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/29   Hitachi         T.Aoyagi        Create          N/A
 * 2016/06/14   Hitachi         T.Aoyagi        Update          QC#9682
 * 2016/06/15   Hitachi         T.Aoyagi        Update          QC#9685
 * 2018/06/04   CITS            M.Naito         Update          QC#24320
 * 2018/06/15   CITS            M.Naito         Update          QC#24320
 * 2018/06/21   Hitachi         K.Kojima        Update          QC#24320
 * 2018/11/26   Fujitsu         K.Ishizuka      Update          QC#29126
 *</pre>
 */
public class NSAL9900CommonLogic implements ZYPConstant {

    /**
     * createCMsgForSearch
     * @return NSAL9900CMsg
     */
    public static NSAL9900CMsg createCMsgForSearch() {
        NSAL9900CMsg bizMsg = new NSAL9900CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        return bizMsg;
    }

    /**
     * createCMsgForUpdate
     * @return NSAL9900CMsg
     */
    public static NSAL9900CMsg createCMsgForUpdate() {
        NSAL9900CMsg bizMsg = new NSAL9900CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL9900BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL9900BMsg scrnMsg) {

        initialize(handler, scrnMsg);
        // START 2018/06/04 M.Naito [QC#24320, MOD]
        screenControlByFuncId(handler, scrnMsg);
        // START 2018/06/04 M.Naito [QC#24320, MOD]
        screenControlByItemValue(handler, scrnMsg);
        setNumberScale(handler, scrnMsg);
        setTableBGColor(scrnMsg);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL9900BMsg
     */
    @SuppressWarnings("unchecked")
    private static void initialize(EZDCommonHandler handler, NSAL9900BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);

        // set button property
        // common button
        List<List<String>> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN);
        for (List<String> button : cmnEnableButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
        disableButtons(handler, SAVE, APPLY, APPROVE, REJECT, DELETE, CLEAR);

        // set Optional Button Property
        List<List<String>> optButtons = Arrays.asList(SEARCH, SELECTALL, UNSELECTALL, INSERTROW, COPYROW, DELETEROW);
        for (List<String> button : optButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }
    }

    // START 2018/06/04 M.Naito [QC#24320, MOD]
    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL9900BMsg
     */
    @SuppressWarnings("unchecked")
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL9900BMsg scrnMsg) {

//        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(scrnMsg.bizFuncId.getValue());
        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Master Maintenance(NSAL9900). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        boolean editCtrlFlg = false;
        for (String funcId : funcIdList) {
            // START 2018/06/15 M.Naito [QC#24320, MOD]
//            if (funcId.endsWith(FUNC_ID_T010)) {
            // START 2018/06/21 K.Kojima [QC#24320,MOD]
            // if (funcId.endsWith(scrnMsg.bizFuncId.getValue())) {
            if (funcId.endsWith(FUNC_ID_T010)) {
            // END 2018/06/21 K.Kojima [QC#24320,MOD]
                editCtrlFlg = true;
            }
            // END 2018/06/15 M.Naito [QC#24320, MOD]
        }

        if (!editCtrlFlg) {
            // This user does not have Submit authority.
            disableButtons(handler, SUBMIT);
            // START 2018/06/21 K.Kojima [QC#24320,ADD]
            disableButtons(handler, UPLOAD);
            // END 2018/06/21 K.Kojima [QC#24320,ADD]
        }
    }

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }
    // START 2018/06/04 M.Naito [QC#24320, MOD]

    /**
     * Control items and buttons on the screen by Item value.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL9900BMsg
     */
    @SuppressWarnings("unchecked")
    public static void screenControlByItemValue(EZDCommonHandler handler, NSAL9900BMsg scrnMsg) {

        disableItems(scrnMsg.tblNm);
        List<String> disableItemKeyList = getDisableItemKey(scrnMsg.C);
        List<String> uniqItemKeyList = getUniqItemKey(scrnMsg.C);

        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            scrnMsg.B.no(idx).setInputProtected(false);

            if (hasValue(scrnMsg.B.no(idx).ezUpTime)) {
                setDisableItems(scrnMsg.B.no(idx), uniqItemKeyList);
                setDisableItems(scrnMsg.B.no(idx), disableItemKeyList);
                // START 2016/06/14 T.Aoyagi [QC#9682, ADD]
                if (FLG_OFF_N.equals(scrnMsg.delFuncAvalFlg.getValue())) {
                    disableItems(scrnMsg.B.no(idx).xxChkBox);
                }
                // END 2016/06/14 T.Aoyagi [QC#9682, ADD]
            } else {
                // New line
                setDisableItems(scrnMsg.B.no(idx), disableItemKeyList);
            }
            setMandatoryItems(scrnMsg.B.no(idx), scrnMsg.C);
        }

        List<List<String>> optButtons = Arrays.asList(SELECTALL, UNSELECTALL, COPYROW, DELETEROW);
        for (List<String> button : optButtons) {
            handler.setButtonProperties(button.get(0), button.get(1), button.get(2), BTN_ACTIVE, null);
        }

        if (scrnMsg.B.getValidCount() == 0) {
            disableButtons(handler, SELECTALL, UNSELECTALL, COPYROW, DELETEROW);
        }
        
        // 2018/11/26 S21_NA#29126 Add Start
        if (S21StringUtil.isEquals(ZYPConstant.FLG_OFF_N, scrnMsg.insrFuncAvalFlg.getValue())) {
            disableButtons(handler, COPYROW, INSERTROW);
        }
        // 2018/11/26 S21_NA#29126 Add End
    }

    /**
     * set Disable Items
     * @param lineMsg NSAL9900_BBMsg
     * @param keyList List<String>
     */
    private static void setDisableItems(NSAL9900_BBMsg lineMsg, List<String> keyList) {
        try {
            for (String key : keyList) {
                Field f = lineMsg.getClass().getField(key);
                    ((EZDBItem) f.get(lineMsg)).setInputProtected(true);
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
    }

    /**
     * set Mandatory Items
     * @param lineMsg NSAL9900_BBMsg
     * @param keyList List<String>
     */
    private static void setMandatoryItems(NSAL9900_BBMsg lineMsg, NSAL9900_CBMsgArray colList) {

        for (int i = 0; i < colList.getValidCount(); i++) {
            try {
                String mndFlg = colList.no(i).mndFlg.getValue();
                String detailItemNm = colList.no(i).detailItemNm.getValue();
                Field f = lineMsg.getClass().getField(detailItemNm);

                if (ZYPConstant.FLG_ON_Y.equals(mndFlg)) {
                    ((EZDBItem) f.get(lineMsg)).setIndispensable(true);
                } else {
                    ((EZDBItem) f.get(lineMsg)).setIndispensable(false);
                }
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
        }
    }

    /**
     * get Unique Items Key
     * @param colList NSAL9900_CBMsgArray
     * @return List
     */
    private static List<String> getUniqItemKey(NSAL9900_CBMsgArray colList) {
        List<String> uniqItemKeyList = new ArrayList<String>();

        for (int i = 0; i < colList.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(colList.no(i).uniqKeyFlg.getValue())) {
                uniqItemKeyList.add(colList.no(i).detailItemNm.getValue());
            }
        }
        return uniqItemKeyList;
    }

    /**
     * get Disable Items Key
     * @param colList NSAL9900_CBMsgArray
     * @return List
     */
    private static List<String> getDisableItemKey(NSAL9900_CBMsgArray colList) {
        List<String> disableItemKeyList = new ArrayList<String>();

        for (int i = 0; i < colList.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(colList.no(i).itemInacFlg.getValue())) {
                disableItemKeyList.add(colList.no(i).detailItemNm.getValue());
            }
        }
        return disableItemKeyList;
    }

    /**
     * Set Number Scale
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL9900BMsg
     */
    private static void setNumberScale(EZDCommonHandler handler, NSAL9900BMsg scrnMsg) {

        // Search Condition
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            setNumberScaleItems(scrnMsg.A.no(i), scrnMsg.C);
        }
        // Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            setNumberScaleItems(scrnMsg.B.no(i), scrnMsg.C);
        }
    }

    /**
     * Set Number Scale Items
     * @param lineMsg NSAL9900_ABMsg
     * @param colInfoList NSAL9900_CBMsgArray
     */
    private static void setNumberScaleItems(NSAL9900_ABMsg lineMsg, NSAL9900_CBMsgArray colInfoList) {

        try {
            for (int i = 0; i < colInfoList.length(); i++) {
                if (!hasValue(colInfoList.no(i).dplySortNum)) {
                    continue;
                }
                if (!hasValue(colInfoList.no(i).srchItemFlg) || ZYPConstant.FLG_OFF_N.equals(colInfoList.no(i).srchItemFlg.getValue())) {
                    continue;
                }
                if (!COL_TP_NUMBER.equals(colInfoList.no(i).colTpCd.getValue())) {
                    continue;
                }
                String itemNm = colInfoList.no(i).searchItemNm.getValue();
                int sclNum = 0;
                if (hasValue(colInfoList.no(i).itemSclNum)) {
                    sclNum = colInfoList.no(i).itemSclNum.getValueInt();
                }

                Field f = lineMsg.getClass().getField(itemNm);
                ((EZDBBigDecimalItem) f.get(lineMsg)).setAppFracDigit(sclNum);
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
    }

    /**
     * Set Number Scale Items
     * @param lineMsg NSAL9900_ABMsg
     * @param colInfoList NSAL9900_CBMsgArray
     */
    private static void setNumberScaleItems(NSAL9900_BBMsg lineMsg, NSAL9900_CBMsgArray colInfoList) {

        try {
            for (int i = 0; i < colInfoList.length(); i++) {
                if (!hasValue(colInfoList.no(i).dplySortNum)) {
                    continue;
                }
                if (!COL_TP_NUMBER.equals(colInfoList.no(i).colTpCd.getValue())) {
                    continue;
                }
                String itemNm = colInfoList.no(i).detailItemNm.getValue();
                int sclNum = 0;
                if (hasValue(colInfoList.no(i).itemSclNum)) {
                    sclNum = colInfoList.no(i).itemSclNum.getValueInt();
                }

                Field f = lineMsg.getClass().getField(itemNm);
                ((EZDBBigDecimalItem) f.get(lineMsg)).setAppFracDigit(sclNum);
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }
    }

    /**
     * Set table background color.
     * @param scrnMsg NSAL9900BMsg
     */
    private static void setTableBGColor(NSAL9900BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.B.getValidCount() > 0) {
            tblColor.clearRowsBG("B1", scrnMsg.B);
            tblColor.setAlternateRowsBG("B1", scrnMsg.B);
        } else {
            tblColor.clearRowsBG("B1", scrnMsg.B);
        }
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
     * add check item
     * @param scrnMsg NSAL9900BMsg
     */
    public static void addCheckItem(NSAL9900BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL9900_BBMsg bBMsg = scrnMsg.B.no(i);

            // START 2016/06/15 T.Aoyagi [QC#9685, ADD]
            scrnMsg.addCheckItem(bBMsg.xxChkBox);
            // END 2016/06/15 T.Aoyagi [QC#9685, ADD]
            for (int j = 0; j < scrnMsg.C.getValidCount(); j++) {
                NSAL9900_CBMsg cBMsg = scrnMsg.C.no(j);
                if (!hasValue(cBMsg.dplyCtrlCd) || !hasValue(cBMsg.dplySortNum)) {
                    continue;
                }
                String detailItemNm = cBMsg.detailItemNm.getValue();
                Field f = null;
                try {
                    f = NSAL9900_BBMsg.class.getField(detailItemNm);
                    scrnMsg.addCheckItem((EZDBItem) f.get(bBMsg));
                } catch (Exception ex) {
                    throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
                }
            }
        }
    }
}
