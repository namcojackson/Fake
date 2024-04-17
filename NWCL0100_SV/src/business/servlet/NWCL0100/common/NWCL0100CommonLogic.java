/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100.common;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.LINK_NAME_LIST_ACCT_GRP;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BIZ_ID;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_APL;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_APR;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_CLR;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_DEL;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_DWL;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_RJT;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_RST;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_RTN;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_SAV;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.BTN_CMN_SUB;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_CURRENT;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_FUTURE;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_PAST;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.DISP_CTRL_CD_SALESDATE;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.SCRN_ID_00;

import java.util.List;

import business.servlet.NWCL0100.NWCL0100BMsg;
import business.servlet.NWCL0100.NWCL0100_ABMsg;
import business.servlet.NWCL0100.NWCL0100_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWCL0100CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/09/06   Hitachi         Y.Takeno        Update          QC#13315
 * 2017/11/14   Fujitsu         Mz.Takahashi    Update          Sol#265(QC#18788)
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWCL0100CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Common Button properties.
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
     * @param scrnMsg NWCL0100BMsg
     * @param scrnAMsgAry NWCL0100_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWCL0100BMsg scrnMsg, NWCL0100_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NWCL0100BMsg
     * @param scrnAMsgAry NWCL0100_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NWCL0100BMsg scrnMsg, NWCL0100_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NWCL0100BMsg
     * @param scrnAMsgAry NWCL0100_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWCL0100BMsg scrnMsg, NWCL0100_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Control Item
     * @param handler EZDCommonHandler
     * @param scrnMsg NWCL0100BMsg
     */
    public static void controlItem(S21CommonHandler handler, NWCL0100BMsg scrnMsg) {

        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            controlDtlItem(scrnMsg);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);

            handler.setButtonEnabled("InsertLine", false);
            handler.setButtonEnabled("DeleteLine", false);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(true);
                scrnMsg.A.no(i).billToCustAcctCd_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_A0.setInputProtected(true);
                scrnMsg.A.no(i).prcGrpPk_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkProt_A0.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setInputProtected(true);
                scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setInputProtected(true);
                scrnMsg.A.no(i).autoDrCratFlg_A0.setInputProtected(true);
                scrnMsg.A.no(i).dsAcctNm_A0.setInputProtected(true);
                scrnMsg.A.no(i).prcGrpNm_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxPsnNm_A0.setInputProtected(true);
                scrnMsg.A.no(i).contrCratDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).contrUpdDt_A0.setInputProtected(true);
            }
        }
    }

    /**
     * Has Update FuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Reprocess Print Request(" + BIZ_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        if (funcList.contains("NWCL0100T020")) {
            return true;
        }

        return false;
    }

    /**
     * Control Item
     * @param scrnMsg NWCL0100BMsg
     */
    public static void controlDtlItem(NWCL0100BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (DISP_CTRL_CD_PAST.equals(scrnMsg.A.no(i).dplyCtrlCd_A0.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(true);
                scrnMsg.A.no(i).billToCustAcctCd_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_A0.setInputProtected(true);
                // 2017/11/14 Sol#265(QC#18788) Add Start
                scrnMsg.A.no(i).prcGrpPk_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkProt_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkProt_A0.clear();
                // 2017/11/14 Sol#265(QC#18788) Add End
                scrnMsg.A.no(i).effFromDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setInputProtected(true);
                scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setInputProtected(true);
                scrnMsg.A.no(i).autoDrCratFlg_A0.setInputProtected(true);
            } else if (DISP_CTRL_CD_CURRENT.equals(scrnMsg.A.no(i).dplyCtrlCd_A0.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(true);
                scrnMsg.A.no(i).billToCustAcctCd_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_A0.setInputProtected(true);
                // 2017/11/14 Sol#265(QC#18788) Add Start
                scrnMsg.A.no(i).prcGrpPk_A0.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkProt_A0.setInputProtected(false);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxLinkProt_A0, LINK_NAME_LIST_ACCT_GRP);
                // 2017/11/14 Sol#265(QC#18788) Add End
                scrnMsg.A.no(i).effFromDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setInputProtected(true);
                scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setInputProtected(true);
                scrnMsg.A.no(i).autoDrCratFlg_A0.setInputProtected(true);
            } else if (DISP_CTRL_CD_FUTURE.equals(scrnMsg.A.no(i).dplyCtrlCd_A0.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(false);
                scrnMsg.A.no(i).billToCustAcctCd_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_A0.setInputProtected(true);
                // 2017/11/14 Sol#265(QC#18788) Add Start
                scrnMsg.A.no(i).prcGrpPk_A0.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkProt_A0.setInputProtected(false);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxLinkProt_A0, LINK_NAME_LIST_ACCT_GRP);
                // 2017/11/14 Sol#265(QC#18788) Add End
                scrnMsg.A.no(i).effFromDt_A0.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setInputProtected(false);
                scrnMsg.A.no(i).autoDrCratFlg_A0.setInputProtected(false);
            } else if (DISP_CTRL_CD_SALESDATE.equals(scrnMsg.A.no(i).dplyCtrlCd_A0.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(true);
                scrnMsg.A.no(i).billToCustAcctCd_A0.setInputProtected(true);
                scrnMsg.A.no(i).xxLinkAncr_A0.setInputProtected(true);
                // 2017/11/14 Sol#265(QC#18788) Add Start
                scrnMsg.A.no(i).prcGrpPk_A0.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkProt_A0.setInputProtected(false);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxLinkProt_A0, LINK_NAME_LIST_ACCT_GRP);
                // 2017/11/14 Sol#265(QC#18788) Add End
                scrnMsg.A.no(i).effFromDt_A0.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setInputProtected(false);
                scrnMsg.A.no(i).autoDrCratFlg_A0.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).xxChkBox_A0.setInputProtected(false);
                scrnMsg.A.no(i).billToCustAcctCd_A0.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkAncr_A0.setInputProtected(false);
                // 2017/11/14 Sol#265(QC#18788) Add Start
                scrnMsg.A.no(i).prcGrpPk_A0.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkProt_A0.setInputProtected(false);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxLinkProt_A0, LINK_NAME_LIST_ACCT_GRP);
                // 2017/11/14 Sol#265(QC#18788) Add End
                scrnMsg.A.no(i).effFromDt_A0.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmUnitAmtRate_A0.setInputProtected(false);
                scrnMsg.A.no(i).splyPgmMlyQuotQty_A0.setInputProtected(false);
                scrnMsg.A.no(i).autoDrCratFlg_A0.setInputProtected(false);
            }

            scrnMsg.A.no(i).dsAcctNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).prcGrpNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).contrCratDt_A0.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrUpdDt_A0.setInputProtected(true);
        }
    }

    /**
     * addCheckItemBizLayerErr
     * @param scrnMsg NWCL0100BMsg
     */
    public static void addCheckItemBizLayerErr(NWCL0100BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            NWCL0100_ABMsg scrnLineMsg = scrnMsg.A.no(i);
            // 2016/09/06 QC#13315 Add Start
            scrnMsg.addCheckItem(scrnLineMsg.billToCustAcctCd_A0);
            // 2016/09/06 QC#13315 Add End

            // 2017/11/14 Sol#265(QC#18788) Add Start
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpPk_A0);
            // 2017/11/14 Sol#265(QC#18788) Add End

            scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A0);
            scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A0);
            scrnMsg.addCheckItem(scrnLineMsg.splyPgmUnitAmtRate_A0);
            scrnMsg.addCheckItem(scrnLineMsg.splyPgmMlyQuotQty_A0);
        }
    }

}
