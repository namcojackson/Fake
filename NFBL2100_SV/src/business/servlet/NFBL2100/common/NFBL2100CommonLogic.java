/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFBL2100.common;

import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_1;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_10;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_2;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_3;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_4;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_5;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_6;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_7;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_8;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BTN_CMN_BTN_9;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.BUSINESS_ID;
import static business.servlet.NFBL2100.constant.NFBL2100Constant.SCREEN_ID;

import java.util.List;

import parts.common.EZDGUIAttribute;
import business.servlet.NFBL2100.NFBL2100BMsg;
import business.servlet.NFBL2100.constant.NFBL2100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Lease Buyout Approve List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/04   Hitachi         K.Kojima        Create          N/A
 * 2016/10/07   Hitachi         K.Kojima        Update          QC#11613
 * 2016/10/12   Hitachi         K.Kojima        Update          QC#13088
 * 2016/10/25   Hitachi         K.Kojima        Update          QC#13088
 * 2017/11/16   CITS            K.Ogino         Update          QC#22334
 *</pre>
 */
public class NFBL2100CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NFBL2100BMsg
     */
    public static void addCheckItem(NFBL2100BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.apDsWfStsCd_SV);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
    }

    /**
     * setupScreenItems
     * @param handler S21CommonHandler
     * @param scrnMsg NFBL2100BMsg
     */
    public static void setupScreenItems(S21CommonHandler handler, NFBL2100BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        S21UserProfileService profile = S21UserProfileServiceFactory.getInstance().getService();
        List<String> funcIdList = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Complete Contract. UserID is -> " + profile.getContextUserInfo().getUserId());
        }

        scrnMsg.setInputProtected(false);
        handler.setButtonEnabledAll();
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        // START 2016/10/12 K.Kojima [QC#13088,MOD]
        // scrnMsg.locNm.setInputProtected(true);
        scrnMsg.dplyVndNm.setInputProtected(true);
        // END 2016/10/12 K.Kojima [QC#13088,MOD]

        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1170BMsg
     */
    public static void commonAddCheckItem(NFBL2100BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.apDsWfStsCd_SV);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
    }

    /**
     * setupListTable
     * @param scrnMsg NSAL1150BMsg
     */
    public static void setupListTable(NFBL2100BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        S21TableColorController lineTblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        lineTblColor.clearRowsBG("A", scrnMsg.A);
        lineTblColor.setAlternateRowsBG("A", scrnMsg.A);
        for (int num = 0; num < scrnMsg.A.getValidCount(); num++) {
            scrnMsg.A.no(num).apDsWfStsDescTxt_A.setInputProtected(true);
            // QC#22334 Start
            scrnMsg.A.no(num).cpoOrdNum_A.setInputProtected(false);
            // QC#22334 End
            scrnMsg.A.no(num).invNum_A.setInputProtected(true);
            scrnMsg.A.no(num).xxToDt_A.setInputProtected(true);
            // START 2016/10/25 K.Kojima [QC#15483,MOD]
            // scrnMsg.A.no(num).cpoOrdTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(num).dsOrdCatgDescTxt_A.setInputProtected(true);
            // END 2016/10/25 K.Kojima [QC#15483,MOD]
            // START 2016/10/12 K.Kojima [QC#13088,MOD]
            // scrnMsg.A.no(num).cpoDtlLineNum_A.setInputProtected(true);
            scrnMsg.A.no(num).dplyLineNum_A.setInputProtected(true);
            // END 2016/10/12 K.Kojima [QC#13088,MOD]
            scrnMsg.A.no(num).cpoDtlFuncNetAmt_A.setInputProtected(true);
            scrnMsg.A.no(num).invBolNum_A.setInputProtected(true);
            scrnMsg.A.no(num).invLineNum_A.setInputProtected(true);
            scrnMsg.A.no(num).mdseCd_A.setInputProtected(true);
            // START 2016/10/07 K.Kojima [QC#11613,MOD]
            // scrnMsg.A.no(num).mdseNm_A.setInputProtected(true);
            scrnMsg.A.no(num).mdseDescShortTxt_A.setInputProtected(true);
            // END 2016/10/07 K.Kojima [QC#11613,MOD]
            scrnMsg.A.no(num).apvlUsrNm_A.setInputProtected(true);
            scrnMsg.A.no(num).apvlRspbNm_A.setInputProtected(true);
            scrnMsg.A.no(num).apvlLimitAmt_A.setInputProtected(true);
            scrnMsg.A.no(num).apWfMlNtfyDt_A.setInputProtected(true);
            scrnMsg.A.no(num).apWfMlNtfyNum_A.setInputProtected(true);
            scrnMsg.A.no(num).apWfRqstCmntTxt_A.setInputProtected(true);
            scrnMsg.A.no(num).apWfRqstRspDt_A.setInputProtected(true);
            scrnMsg.A.no(num).vndCd_A.setInputProtected(true);
            scrnMsg.A.no(num).crArInvNum_A.setInputProtected(true);
            // START 2016/10/12 K.Kojima [QC#13088,MOD]
            // scrnMsg.A.no(num).crApInvNum_A.setInputProtected(true);
            // QC#22334 Start
            if (AP_DS_WF_STS.COMPLETED.equals(scrnMsg.A.no(num).apDsWfStsCd_A.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(num).xxLinkAncr_A, scrnMsg.A.no(num).apInvNum_A);
                scrnMsg.A.no(num).apInvNum_A.setInputProtected(false);
                scrnMsg.A.no(num).xxLinkAncr_A.setInputProtected(false);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(num).xxLinkAncr_A, scrnMsg.A.no(num).apInvNum_A);
                scrnMsg.A.no(num).apInvNum_A.setInputProtected(true);
                scrnMsg.A.no(num).xxLinkAncr_A.setInputProtected(true);
                EZDGUIAttribute ancher = new EZDGUIAttribute(NFBL2100Constant.SCREEN_ID, NFBL2100Constant.HTML_ID_AP_INV + num);
                ancher.setStyleAttribute("color", "black");
                ancher.setStyleAttribute("text-decoration", "none");
                ancher.setStyleAttribute("cursor", "text");
                scrnMsg.addGUIAttribute(ancher);
            }
            // QC#22334 End
            // END 2016/10/12 K.Kojima [QC#13088,MOD]

            scrnMsg.A.no(num).cpoDtlFuncNetAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(num).apvlLimitAmt_A.setAppFracDigit(2);
        }
    }

}
