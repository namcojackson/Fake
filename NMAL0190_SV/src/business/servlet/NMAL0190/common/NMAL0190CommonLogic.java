/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0190.common;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BIZ_ID;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_APL;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_APR;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_CLR;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_DEL;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_DWL;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_RJT;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_RST;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_RTN;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_SAV;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.BTN_CMN_SUB;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.NMAM8383W;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.SCRN_ID_00;
import static business.servlet.NMAL0190.constant.NMAL0190Constant.ZZSM4300E;

import java.util.List;

import parts.common.EZDFieldErrorException;
import business.servlet.NMAL0190.NMAL0190BMsg;
import business.servlet.NMAL0190.NMAL0190_ABMsg;
import business.servlet.NMAL0190.NMAL0190_ABMsgArray;
import business.servlet.NMAL0190.NMAL0190_BBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL0190CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         T.Arima          Create          N/A
 * 2016/01/04   Fujitsu         M.Nakamura       Update          QC#2528
 * 2016/03/02   CITS            S.Tanikawa      UPDATE          QC#2669
 *</pre>
 */
public class NMAL0190CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        boolean isUpdate = hasUpdateFuncId();
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        if (isUpdate) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        }

    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL0190BMsg
     * @param scrnAMsgAry NMAL0190_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL0190BMsg scrnMsg, NMAL0190_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL0190BMsg
     * @param scrnAMsgAry NMAL0190_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL0190BMsg scrnMsg, NMAL0190_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL0190BMsg
     * @param scrnAMsgAry NMAL0190_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL0190BMsg scrnMsg, NMAL0190_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Set protect input field.
     *
     * @param scrnMsg NMAL0170BMsg
     */
    public static void setScrnLineProtected(NMAL0190BMsg scrnMsg) {
        // UPDATE START 2016/03/02 QC#2669
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL0190_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnLineMsg.supdToMdseCd.setInputProtected(true);
            scrnLineMsg.mdseDescShortTxt_TO.setInputProtected(true);
            scrnLineMsg.supdFromMdseCd.setInputProtected(true);
            scrnLineMsg.mdseDescShortTxt_FR.setInputProtected(true);
            // DELETE START 2016/01/04 QC#2528
            // scrnLineMsg.dsctnFlg.setInputProtected(true);
            // DELETE END   2016/01/04 QC#2528
            scrnLineMsg.supdRelnStageDt.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NMAL0190_BBMsg scrnLineMsg = scrnMsg.B.no(i);

            scrnLineMsg.relnMdseCd.setInputProtected(true);
            scrnLineMsg.xxAuthByNm.setInputProtected(true);
            scrnLineMsg.ezUpTime_F.setInputProtected(true);
            scrnLineMsg.ezUpUserID_F.setInputProtected(true);
        }
        // UPDATE START 2016/03/02 QC#2669
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException(ZZSM4300E, new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains("NMAL0190T020")) {
            return true;
        }

        return false;
    }

    /**
     * addCheckAllInput
     * @param scrnMsg NMAL0190BMsg
     */
    public static void addCheckAllInput(NMAL0190BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.supdRelnStagePk_P);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseItemStsCd_SL);
            // ADD START 2016/03/02 QC#2669
            scrnMsg.addCheckItem(scrnMsg.A.no(i).supdToMdseCd);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).supdFromMdseCd);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).supdRelnStageDt);
            // ADD END 2016/03/02 QC#2669
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxSupdFlg_FW);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxSupdFlg_BW);
        }
    }

    /**
     * set Warning Info when try to delete compatible relation
     * @param scrnMsg NMAL0190BMsg
     */
    public static void checkCompatibleDelete(NMAL0190BMsg scrnMsg) {
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxAdvcFlg.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxSupdFlg_BH.getValue())
                        && !ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxSupdFlg_BW.getValue())) {
                    scrnMsg.B.no(i).xxSupdFlg_BW.setErrorInfo(2, NMAM8383W, new String[] {"Compatible Setting" });
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxAdvcFlg, ZYPConstant.FLG_ON_Y);
                }
                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxSupdFlg_FH.getValue())
                        && !ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.B.no(i).xxSupdFlg_FW.getValue())) {
                    scrnMsg.B.no(i).xxSupdFlg_FW.setErrorInfo(2, NMAM8383W, new String[] {"Compatible Setting" });
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxAdvcFlg, ZYPConstant.FLG_ON_Y);
                }
            }
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxAdvcFlg.getValue())) {
                addCheckAllInput(scrnMsg);
                scrnMsg.putErrorScreen();

                throw new EZDFieldErrorException();
            }
        }
    }
}
