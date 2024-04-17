/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1140.common;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_APPLY;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_APPROVE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_CLEAR;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_DELETE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_DOWNLOAD;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_REJECT;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_RESET;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_RETURN;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_SAVE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_GUARD_SUBMIT;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_APPLY;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_APPROVE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_CLEAR;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_DELETE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_DOWNLOAD;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_REJECT;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_RESET;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_RETURN;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_SAVE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_LABEL_SUBMIT;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_ACK_CANCEL;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_ACK_REPROCESS;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_APPLY;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_APPROVE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_CLEAR;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_DELETE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_DOWNLOAD;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_PO_ENTRY;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_REFRESH;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_REJECT;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_RESET;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_RETURN;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_SAVE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_SEARCH;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_SELECT_ALL;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_SUBMIT;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.BUTTON_NAME_UN_SELECT_ALL;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.DATE_LENGTH;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.FUNCID_UPDATE;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.SCREEN_ID;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.TAB_HEADER;
import static business.servlet.NPAL1140.constant.NPAL1140Constant.TS_LENGTH;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NPAL1140.NPAL1140CMsg;
import business.servlet.NPAL1140.NPAL1140BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/26/2013   Hitachi         T.Kawazu        Update          1388
 * 07/30/2013   Hitachi         A.Kohinata      Update          1388
 * 10/28/2013   Hitachi         T.Aoyagi        Update          QC2852
 * 12/02/2016   CITS            Y.IWASAKI       Update          QC#15172
 * 12/02/2016   CITS            Y.IWASAKI       Update          QC#15173
 * 01/31/2017   CITS            Y.IWASAKI       Update          QC#15172
 * 04/11/2017   CITS            R.Shimamoto     Update          QC#18205
 * 2017/10/27   CITS            M.Naito         Update          QC#20380
 *</pre>
 */
public class NPAL1140CommonLogic {
    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NPAL1140BMsg
     * @param rtnOnlyType boolean
     */
    public static void initialize(EZDCommonHandler scrnAppli, NPAL1140BMsg scrnMsg, boolean rtnOnlyType) {

        scrnAppli.setButtonProperties(BUTTON_NAME_SAVE, BUTTON_GUARD_SAVE, BUTTON_LABEL_SAVE, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_SUBMIT, BUTTON_GUARD_SUBMIT, BUTTON_LABEL_SUBMIT, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_APPLY, BUTTON_GUARD_APPLY, BUTTON_LABEL_APPLY, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_APPROVE, BUTTON_GUARD_APPROVE, BUTTON_LABEL_APPROVE, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_REJECT, BUTTON_GUARD_REJECT, BUTTON_LABEL_REJECT, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_DOWNLOAD, BUTTON_GUARD_DOWNLOAD, BUTTON_LABEL_DOWNLOAD, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_DELETE, BUTTON_GUARD_DELETE, BUTTON_LABEL_DELETE, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_RESET, BUTTON_GUARD_RESET, BUTTON_LABEL_RESET, 0, null);
        scrnAppli.setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_RETURN, BUTTON_LABEL_RETURN, 1, null);

        scrnAppli.setButtonEnabled(BUTTON_NAME_SEARCH, rtnOnlyType);
        scrnAppli.setButtonEnabled(BUTTON_NAME_ACK_REPROCESS, false);
        scrnAppli.setButtonEnabled(BUTTON_NAME_ACK_CANCEL, false);
        scrnAppli.setButtonEnabled(BUTTON_NAME_RESET, true);
        scrnAppli.setButtonEnabled(BUTTON_NAME_SELECT_ALL, false);
        scrnAppli.setButtonEnabled(BUTTON_NAME_UN_SELECT_ALL, false);
        scrnAppli.setButtonEnabled(BUTTON_NAME_PO_ENTRY, false);

        scrnMsg.itrlIntfcId.setInputProtected(true);
        scrnMsg.ackEdiProcStsNm.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_HR.setInputProtected(true);
        scrnMsg.ediPoOrdNum_HT.setInputProtected(true);
        scrnMsg.poOrdNum_HT.setInputProtected(true);
        scrnMsg.poAckNum_HT.setInputProtected(true);
        scrnMsg.vndCpoOrdNum.setInputProtected(true);
        scrnMsg.batErrMsgTxt_HV.setInputProtected(true);
        // 2017/10/27 QC20380 M.Naito Mod Start
//        scrnMsg.vndNm_HT.setInputProtected(true);
        scrnMsg.dplyVndNm_HT.setInputProtected(true);
        // 2017/10/27 QC20380 M.Naito Mod End

        NPAL1140CommonLogic.tabDispCtrl(scrnMsg);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NPAL1140BMsg
     * @param bizMsg NPAL1140CMsg
     */
    public static void itemCtrlDataDisp(EZDCommonHandler scrnAppli, NPAL1140BMsg scrnMsg, NPAL1140CMsg bizMsg) {

        boolean activeFlg = true;

        if (scrnMsg.A.getValidCount() == 0) {

            activeFlg = false;

        } else {

            itemCtrlAArray(scrnMsg);

        }

        if (!hasUpdateAuthority(bizMsg.getUserID())) {
            activeFlg = false;
        }

        scrnAppli.setButtonEnabled(BUTTON_NAME_ACK_REPROCESS, activeFlg);
        scrnAppli.setButtonEnabled(BUTTON_NAME_ACK_CANCEL, activeFlg);
        scrnAppli.setButtonEnabled(BUTTON_NAME_SELECT_ALL, activeFlg);
        scrnAppli.setButtonEnabled(BUTTON_NAME_UN_SELECT_ALL, activeFlg);

        if (Boolean.TRUE.equals(activeFlg) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.openPoAckWrkFlg.getValue())) {

            scrnAppli.setButtonEnabled(BUTTON_NAME_SAVE, true);

        } else {

            scrnAppli.setButtonEnabled(BUTTON_NAME_SAVE, false);

        }

        activeFlg = true;
        boolean poActiveFlg = true;
        if (ZYPCommonFunc.hasValue(scrnMsg.ediPoOrdNum_HT)) {
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.openPoAckWrkFlg.getValue())) {
                activeFlg = false;
            }
        } else {
            poActiveFlg = false;
            activeFlg = false;
        }
        scrnAppli.setButtonEnabled(BUTTON_NAME_PO_ENTRY, poActiveFlg);
        scrnAppli.setButtonEnabled(BUTTON_NAME_REFRESH, poActiveFlg);
        scrnMsg.poOrdNum_HT.setInputProtected(!activeFlg);

        NPAL1140CommonLogic.tabDispCtrl(scrnMsg);
    }

    /**
     * @param scrnMsg NPAL1140BMsg
     */
    public static void tabDispCtrl(NPAL1140BMsg scrnMsg) {

        scrnMsg.clearGUIAttribute(SCREEN_ID, "B");
        if (ZYPCommonFunc.hasValue(scrnMsg.ediPoOrdNum_HT)) {
            if (TAB_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.xxLinkAncr_T0.setInputProtected(true);
                scrnMsg.xxLinkAncr_T1.setInputProtected(false);
            } else { //TAB_DETAIL
                scrnMsg.xxLinkAncr_T0.setInputProtected(false);
                scrnMsg.xxLinkAncr_T1.setInputProtected(true);
                S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
                tblColor.setAlternateRowsBG("B", scrnMsg.B, 2);
            }
        } else {
            scrnMsg.xxLinkAncr_T0.setInputProtected(true);
            scrnMsg.xxLinkAncr_T1.setInputProtected(true);
        }
    }

    private static void itemCtrlAArray(NPAL1140BMsg scrnMsg) {
        scrnMsg.clearGUIAttribute(SCREEN_ID, "A");
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(idx).openPoAckWrkFlg_A0.getValue())) {
                scrnMsg.A.no(idx).xxChkBox_A0.setInputProtected(false);
            } else {
                scrnMsg.A.no(idx).xxChkBox_A0.setInputProtected(true);
            }
            scrnMsg.A.no(idx).ediPoOrdNum_A0.setInputProtected(true);
            scrnMsg.A.no(idx).poAckNum_A0.setInputProtected(true);
            scrnMsg.A.no(idx).ackEdiProcStsNm_A0.setInputProtected(true);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).batErrMsgTxt_AD.no(0))) {
                scrnMsg.A.no(idx).batErrMsgTxt_AV.setInputProtected(false);
            } else {
                scrnMsg.A.no(idx).batErrMsgTxt_AV.setInputProtected(true);
            }
            // 2017/10/27 QC20380 M.Naito Mod Start
//            scrnMsg.A.no(idx).vndNm_A0.setInputProtected(true);
            scrnMsg.A.no(idx).dplyVndNm_A0.setInputProtected(true);
            // 2017/10/27 QC20380 M.Naito Mod End
        }
    }

    /**
     * @param scrnMsg NPAL1140BMsg
     * @param bizMsg NPAL1140CMsg
     */
    public static void setUnderTabHeader(NPAL1140BMsg scrnMsg, NPAL1140CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.batErrMsgTxt_HD.no(0))) {
            scrnMsg.batErrMsgTxt_HV.setInputProtected(false);
        } else {
            scrnMsg.batErrMsgTxt_HV.setInputProtected(true);
        }
    }

    /**
     * @param scrnMsg NPAL1140BMsg
     * @param bizMsg NPAL1140CMsg
     */
    public static void setUnderTabDateTsItem(NPAL1140BMsg scrnMsg, NPAL1140CMsg bizMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTsDsp19Txt_HR, NPAL1140CommonLogic.formatDateTs(bizMsg.ediRcvDateTs.getValue()));

        String prevLineNum = "";
        for (int idx = 0; idx < scrnMsg.B.getValidCount(); idx++) {
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.openPoAckWrkFlg.getValue())) {
                // QC#18205 Mod.
//                scrnMsg.B.no(idx).poOrdDtlLineNum_B0.setInputProtected(true);
                scrnMsg.B.no(idx).dispPoDtlLineNum_B0.setInputProtected(true);
            } else if (isSameEdiLineNum(prevLineNum, scrnMsg.B.no(idx).ediPoOrdDtlLineNum_B0.getValue())) {
//                scrnMsg.B.no(idx).poOrdDtlLineNum_B0.setInputProtected(true);
                scrnMsg.B.no(idx).dispPoDtlLineNum_B0.setInputProtected(true);
            } else {
//                scrnMsg.B.no(idx).poOrdDtlLineNum_B0.setInputProtected(false);
                scrnMsg.B.no(idx).dispPoDtlLineNum_B0.setInputProtected(false);
            }

            prevLineNum = scrnMsg.B.no(idx).ediPoOrdDtlLineNum_B0.getValue();

            scrnMsg.B.no(idx).mdseCd_B0.setInputProtected(true);
            scrnMsg.B.no(idx).mdseDescShortTxt_B0.setInputProtected(true);
            scrnMsg.B.no(idx).origCusaMdseCd_B0.setInputProtected(true);
            scrnMsg.B.no(idx).ordQty_B0.setInputProtected(true);

            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).xxDtTxt_BD, NPAL1140CommonLogic.formatDate(bizMsg.B.no(idx).etdDt_B0.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).xxDtTxt_BA, NPAL1140CommonLogic.formatDate(bizMsg.B.no(idx).etaDt_B0.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).xxDtTxt_BU, NPAL1140CommonLogic.formatDate(bizMsg.B.no(idx).ordDtlLastUpdTs_B0.getValue()));
        }
    }

    /**
     * @param scrnMsg NPAL1140BMsg
     */
    public static void addCheckItemHeader(NPAL1140BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.ediPoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.batErrMsgTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_H2);
    }

    /**
     * @param inStr String
     * @return String
     */
    public static String formatDate(String inStr) {
        String rtnStr = "";
        if (!ZYPCommonFunc.hasValue(inStr)) {
            return rtnStr;
        }
        if (inStr.length() < DATE_LENGTH) {
            return rtnStr;
        }
        String dateStr = inStr.substring(0, DATE_LENGTH);

        return ZYPDateUtil.formatEzd8ToDisp(dateStr,true);
    }

    /**
     * @param inStr String
     * @return String
     */
    public static String formatDateTs(String inStr) {
        String rtnStr = "";
        if (!ZYPCommonFunc.hasValue(inStr)) {
            return rtnStr;
        }
        if (inStr.length() < TS_LENGTH) {
            return rtnStr;
        }
        String dateStr = inStr.substring(0, TS_LENGTH);

        return ZYPDateUtil.formatEzd14ToDisp(dateStr,true,true,true);
    }

    /**
     * Return true if userId have update authority.
     * @param userId user Id
     * @return has Update Authority
     */
    public static boolean hasUpdateAuthority(String userId) {

        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNCID_UPDATE })) {
            return true;
        }
        return false;
    }

    private static boolean isSameEdiLineNum(String preStr, String curStr) {
        if (ZYPCommonFunc.hasValue(preStr)) {
            if (!preStr.equals(curStr)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * clearParamNPAL6050
     * @param scrnMsg NPAL1140BMsg
     */
    public static void clearParamNPAL6050(NPAL1140BMsg scrnMsg) {
        scrnMsg.xxTblNm.clear();
        scrnMsg.xxTblCdColNm.clear();
        scrnMsg.xxTblNmColNm.clear();
        scrnMsg.xxTblSortColNm.clear();
        scrnMsg.xxScrNm.clear();
        scrnMsg.xxHdrCdLbNm.clear();
        scrnMsg.xxHdrNmLbNm.clear();
        scrnMsg.xxDtlCdLbNm.clear();
        scrnMsg.xxDtlNmLbNm.clear();
    }

    /**
     * <pre>
     * underTabClear
     * Clear data Under tab Item
     * </pre>
     * @param scrnMsg NPAL1140BMsg
     */
    public static void underTabClear(NPAL1140BMsg scrnMsg) {
        // 2017/10/27 QC20380 M.Naito Mod Start
//        scrnMsg.vndNm_HT.clear();
        scrnMsg.dplyVndNm_HT.clear();
        // 2017/10/27 QC20380 M.Naito Mod End
        scrnMsg.ackEdiProcStsNm.clear();
        scrnMsg.ediRcvDateTs.clear();
        scrnMsg.ediPoOrdNum_HT.clear();
        scrnMsg.poOrdNum_HT.clear();
        scrnMsg.poAckNum_HT.clear();
        scrnMsg.vndCpoOrdNum.clear();
        scrnMsg.batErrMsgTxt_HD.clear();
        scrnMsg.batErrMsgTxt_HC.clear();
    }
}
