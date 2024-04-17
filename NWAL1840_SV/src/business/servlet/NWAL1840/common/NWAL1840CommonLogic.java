/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840.common;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.ALL_RGTN_AUTH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_ADD_CTAC_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_ADD_ITEM_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_ADD_SCHD_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_ATT_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CANC_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_DEL_CTAC_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_LINE_COLLAPSE_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_LINE_EXPAND_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_NOTES_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_PRICE_CHANGE_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_SCHD_ASSIST_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_SHPG_DTL_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_SLS_CREDIT_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_SRCH_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.BTN_VIEW_CHNG_LOG_EVENT_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.CODE_TABLE_SCHD_ATT_DOC_TP;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.COMMA;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.EIGHT_DIGIT_MODE;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.EMAIL_FORMAT;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.EXT_MAX_LENGTH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.HYPHEN;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_0;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_1;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_10;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_100;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_11;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_12;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_13;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_2;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_20;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_22;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_26;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_27;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_3;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_30;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_31;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_33;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_4;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_5;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_50;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_6;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_7;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_8;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_80;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.IDX_9;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.KEY_NWAL1840_ATT_LIMIT;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.KEY_NWAL1840_AUTHORIZE_FILE_EXT;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.KEY_NWAL1840_AUTHORIZE_FILE_VOL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MEMO_ENTRY_ITEM_TITLE;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MEMO_ENTRY_KEY_TRX_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MEMO_ENTRY_KEY_TRX_NUM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MEMO_ENTRY_VAL_TRX_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MSG_PARAM_BIZ_NM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.MSG_PARAM_SCHD_NUM;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0664E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0763E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0832E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.NWAM0964E;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.PERCENT;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.PERIOD;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.REF_AUTH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SCREEN_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SLASH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SPACE;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_ADDITIONAL;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_COMMENTS;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_CUSTOMER;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_HEADER;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_LINES;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.ZZM9000E;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NSAL1240_MODE_02;
import static com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG.SCHD_AGNT_MEMO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1600.constant.NWAL1600Constant;
import business.servlet.NWAL1810.constant.NWAL1810Constant;
import business.servlet.NWAL1840.NWAL1840BMsg;
import business.servlet.NWAL1840.NWAL1840_ABMsg;
import business.servlet.NWAL1840.NWAL1840_BBMsg;
import business.servlet.NWAL1840.NWAL1840_CBMsg;
import business.servlet.NWAL1840.NWAL1840_DBMsg;
import business.servlet.NWAL1840.NWAL1840_FBMsg;
import business.servlet.NWAL1840.NWAL1840_GBMsg;
import business.servlet.NWAL1840.NWAL1840_UBMsg;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SCHD_AGMT_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         T.Murai         Create          N/A
 * 2016/05/13   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/19   Fujitsu         T.Murai         Update          S21_NA#7659
 * 2016/05/23   Fujitsu         T.Murai         Update          S21_NA#8544
 * 2016/07/26   Fujitsu         M.Hara          Update          S21_NA#8412
 * 2016/08/04   Fujitsu         R.Nakamura      Update          S21_NA#9078
 * 2016/09/05   Fujitsu         T.Murai         Update          S21_NA#11752
 * 2016/09/07   Fujitsu         T.Murai         Update          S21_NA#13035
 * 2016/09/14   Fujitsu         R.Nakamura      Update          S21_NA#11614
 * 2016/09/16   Fujitsu         T.Murai         Update          S21_NA#13040
 * 2016/09/28   Fujitsu         T.Murai         Update          S21_NA#11655
 * 2016/10/24   Fujitsu         Y.Kanefusa      Update          S21_NA#14604
 * 2016/11/07   Fujitsu         Y.Kanefusa      Update          S21_NA#14143
 * 2016/11/15   Fujitsu         H.Ikeda         Update          S21_NA#15875
 * 2016/11/25   Fujitsu         M.Ohno          Update          S21_NA#15733
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/06/13   Fujitsu         A.Kosai         Update          QC#18667
 * 2017/08/04   Fujitsu         W.Honda         Update          S21_NA#20228
 * 2017/08/04   Fujitsu         H.Nagashima     Update          S21_NA#16452(L3)
 * 2017/10/04   Fujitsu         K.Ishizuka      Update          S21_NA#21500
 * 2017/10/18   Fujitsu         S.Yamamoto      Update          S21_NA#20246
 * 2017/11/20   Fujitsu         A.Kosai         Update          S21_NA#22388
 * 2017/12/04   Fujitsu         K.Ishizuka      Update          S21_NA#22553
 * 2018/01/12   Fujitsu         K.Ishizuka      Update          S21_NA#20164
 * 2018/02/22   Fujitsu         K.Ishizuka      Update          S21_NA#22399
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/04/16   Fujitsu         H.Nagashima     Update          S21_NA#22965
 * 2018/05/13   Fujitsu         S.Takami        Update          S21_NA#25251
 * 2018/06/27   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#14307
 * 2018/07/26   Fujitsu         H.Kumagai       Update          QC#26661,26713
 * 2018/09/14   Fujitsu         M.Ohno          Update          QC#9700
 * 2018/10/23   Fujitsu         M.Ohno          Update          S21_NA#28425
 * 2018/12/11   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 * 2022/06/01   Hitachi         D.Yoshida       Update          QC#59973
 * 2023/10/10   Hitachi         T.Fukuta        Update          CSA-QC#61730
 * </pre>
 */
public class NWAL1840CommonLogic {

    /**
     * Inactive Common Button
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ARV_BTN_NM, BTN_CMN_ARV_EVENT_NM, BTN_CMN_ARV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);

    }

    /**
     * Inactive SAVE/SUBMIT Button
     * @param handler EZCommandHandler
     */
    public static void inactiveRegistrationButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
    }

    /**
     * Inactive SAVE Button
     * @param handler EZCommandHandler
     */
    public static void inactiveSaveButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
    }

    /**
     * Set All Item Protect
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1840BMsg
     */
    public static void setProtect(EZDCommonHandler handler, NWAL1840BMsg scrnMsg) {
        // Set All Active Field
        setControlFields(scrnMsg);
        setAllActiveFields(handler, scrnMsg);

        // Set Protect By Inherent Control
        setProtectByInherentControl(handler, scrnMsg);

        // Set Protect By Authority
        setProtectByAuthority(handler, scrnMsg);

        // Set Protect By Status
        setProtectByStatus(handler, scrnMsg);

    }

    private static void setControlFields(NWAL1840BMsg scrnMsg) {

        setControlFieldsForLink(scrnMsg);
        setControlFieldsForDigit(scrnMsg);
    }

    /**
     * Set All Active Fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1840BMsg
     */
    private static void setAllActiveFields(EZDCommonHandler handler, NWAL1840BMsg scrnMsg) {

        // Header
        scrnMsg.schdAgmtNum.setInputProtected(false);
        scrnMsg.dsOrdCatgDescTxt_LK.setInputProtected(false);
        scrnMsg.dsOrdCatgDescTxt.setInputProtected(false);
        scrnMsg.dsOrdTpCd.setInputProtected(false);
        scrnMsg.dsOrdTpCd.setInputProtected(false);
        scrnMsg.prcCatgNm_LK.setInputProtected(false);
        scrnMsg.prcCatgNm.setInputProtected(false);
        scrnMsg.mdlNm_LK.setInputProtected(false);
        scrnMsg.mdlId.setInputProtected(false);
        scrnMsg.serNum_LK.setInputProtected(false);
        scrnMsg.serNum.setInputProtected(false);
        scrnMsg.svcConfigMstrPk_LK.setInputProtected(false);
        scrnMsg.svcConfigMstrPk.setInputProtected(false);
        scrnMsg.dsContrNum_LK.setInputProtected(false);
        scrnMsg.dsContrNum.setInputProtected(false);
        scrnMsg.contrVrsnEffThruDt.setInputProtected(true);

        scrnMsg.schdAgmtVldFromDt.setInputProtected(false);
        scrnMsg.schdAgmtVldThruDt.setInputProtected(false);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(false);
        scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(false);
        scrnMsg.cpoSrcTpDescTxt.setInputProtected(true);
        //scrnMsg.ordSrcRefNum.setInputProtected(true);
        scrnMsg.refCpoOrdNum.setInputProtected(true); // S21_NA MOD QC#21500
        scrnMsg.schdAgmtCratDt.setInputProtected(true);
        handler.setButtonEnabled(BTN_ATT_EVENT_NM, true);
        scrnMsg.schdAgmtStsDescTxt.setInputProtected(true); // S21_NA ADD QC#22553

        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            NWAL1840_GBMsg itemMsg = scrnMsg.G.no(i);
            itemMsg.mdseDescShortTxt_G.setInputProtected(true);
            itemMsg.schdAllwQty_G.setInputProtected(false);
            itemMsg.ordQty_G.setInputProtected(false);
            itemMsg.shipQty_G.setInputProtected(false);
        }

        scrnMsg.xxSubTotCalcPrcAmt.setInputProtected(true);
        scrnMsg.xxTotChrgPrcAmt.setInputProtected(true);
        scrnMsg.xxTotTaxAmt.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);

        // Customer / Contact Tab
        scrnMsg.billToCustAcctCd.setInputProtected(false);
        scrnMsg.billToCustAcctCd_LK.setInputProtected(false);
        scrnMsg.billToCustLocCd.setInputProtected(false);
        scrnMsg.billToCustLocCd_LK.setInputProtected(false);
        scrnMsg.billToCustAcctNm.setInputProtected(false);
        scrnMsg.billToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_BT.setInputProtected(true);
        scrnMsg.shipToCustAcctCd_LK.setInputProtected(false);
        scrnMsg.shipToCustLocCd.setInputProtected(false);
        scrnMsg.shipToCustLocCd_LK.setInputProtected(false);
        scrnMsg.dropShipFlg_LK.setInputProtected(false);
        scrnMsg.shipToCustAcctNm.setInputProtected(false);
        scrnMsg.shipToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_SH.setInputProtected(true);
        scrnMsg.sellToFirstRefCmntTxt.setInputProtected(false);// S21_NA ADD QC#20246
        scrnMsg.shipToLocNm_DS.setInputProtected(true); // 2018/02/22 S21_NA#22399 Add
        scrnMsg.sellToCustCd.setInputProtected(false);
        scrnMsg.sellToCustCd_LK.setInputProtected(false);
        scrnMsg.soldToCustLocCd.setInputProtected(false);
        scrnMsg.soldToCustLocCd_LK.setInputProtected(false);
        scrnMsg.soldToCustAcctNm.setInputProtected(false);
        scrnMsg.soldToCustAcctNm_LK.setInputProtected(false);
        scrnMsg.xxAllLineAddr_SE.setInputProtected(true);

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1840_DBMsg contactMsg = scrnMsg.D.no(i);
            contactMsg.xxChkBox_D.setInputProtected(false);
            contactMsg.ctacPsnTpCd_D.setInputProtected(false);
            contactMsg.ctacPsnFirstNm_D.setInputProtected(false);
            contactMsg.ctacPsnFirstNm_LK.setInputProtected(false);
            contactMsg.ctacPsnLastNm_D.setInputProtected(false);
            contactMsg.ctacPsnTelNum_D.setInputProtected(false);
            contactMsg.ctacPsnExtnNum_D.setInputProtected(false);
            contactMsg.ctacPsnEmlAddr_D.setInputProtected(false);
            contactMsg.ctacPsnFaxNum_D.setInputProtected(false);
            contactMsg.ctacCustRefTpCd_D.setInputProtected(false);
        }

        handler.setButtonEnabled(BTN_ADD_CTAC_EVENT_NM, true);
        handler.setButtonEnabled(BTN_DEL_CTAC_EVENT_NM, true);

        if (scrnMsg.D.getValidCount() == 0) {
            handler.setButtonEnabled(BTN_DEL_CTAC_EVENT_NM, false);
        } else if (scrnMsg.D.getValidCount() == scrnMsg.D.length()) {
            handler.setButtonEnabled(BTN_ADD_CTAC_EVENT_NM, false);
        }

        // Header
        scrnMsg.custIssPoNum.setInputProtected(false);
        scrnMsg.custIssPoDt.setInputProtected(false);
        scrnMsg.slsRepTocCd.setInputProtected(false);
        // S21_NA#7861 Mod Start
        // scrnMsg.slsRepPsnCd_LK.setInputProtected(false);
        // scrnMsg.slsRepPsnCd.setInputProtected(false);
        scrnMsg.psnNum_LK.setInputProtected(false);
        scrnMsg.psnNum.setInputProtected(false);
        // S21_NA#7861 Mod End
        scrnMsg.slsRepTocNm_LK.setInputProtected(false);
        scrnMsg.slsRepTocNm.setInputProtected(false);
        scrnMsg.xxScrItem54Txt_CB.setInputProtected(true);
        scrnMsg.xxScrItem54Txt_CE.setInputProtected(true);

        handler.setButtonEnabled(BTN_SLS_CREDIT_EVENT_NM, true);

        // Delivery / Payment Tab
        scrnMsg.frtCondDescTxt_LK.setInputProtected(false);
        scrnMsg.frtCondDescTxt.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
        scrnMsg.carrAcctNum.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd.setInputProtected(false);
        scrnMsg.spclHdlgTpCd.setInputProtected(false);

        // Item Tab
        handler.setButtonEnabled(BTN_LINE_EXPAND_EVENT_NM, true);
        handler.setButtonEnabled(BTN_LINE_COLLAPSE_EVENT_NM, true);

        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            handler.setButtonEnabled(BTN_ADD_ITEM_EVENT_NM, false);
        }
        if (scrnMsg.B.getValidCount() == scrnMsg.B.length()) {
            handler.setButtonEnabled(BTN_ADD_SCHD_EVENT_NM, false);
        }

        handler.setButtonEnabled(BTN_ADD_ITEM_EVENT_NM, true);
        handler.setButtonEnabled(BTN_ADD_SCHD_EVENT_NM, true);
        handler.setButtonEnabled(BTN_CANC_EVENT_NM, true);
        handler.setButtonEnabled(BTN_SCHD_ASSIST_EVENT_NM, true);
        handler.setButtonEnabled(BTN_PRICE_CHANGE_EVENT_NM, true);
        handler.setButtonEnabled(BTN_SHPG_DTL_EVENT_NM, true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1840_ABMsg itemLineMsg = scrnMsg.A.no(i);
            itemLineMsg.xxChkBox_A.setInputProtected(false);
            itemLineMsg.xxLineNum_A.setInputProtected(true);
            itemLineMsg.mdseCd_A.setInputProtected(false);
            itemLineMsg.mdseCd_LK.setInputProtected(false); // Add 2016/09/07 S21_NA13035
            itemLineMsg.mdseDescShortTxt_A.setInputProtected(true);
            itemLineMsg.pkgUomDescTxt_A.setInputProtected(true);
            itemLineMsg.supdLockFlg_A.setInputProtected(false);
            itemLineMsg.sbstMdseCd_A.setInputProtected(false);
            itemLineMsg.sbstMdseCd_LK.setInputProtected(false); // Add 2016/09/07 S21_NA13035
            itemLineMsg.schdAllwQty_A.setInputProtected(false);
            itemLineMsg.ordQty_DE.setInputProtected(true);
            itemLineMsg.ordQty_SC.setInputProtected(true);
            itemLineMsg.dealPrcListPrcAmt_A.setInputProtected(true);
            itemLineMsg.xxTotDiscAmt_A.setInputProtected(true);
            itemLineMsg.dealNetUnitPrcAmt_A.setInputProtected(true);
            itemLineMsg.schdAgmtLineDealFrtAmt_A.setInputProtected(true);
            itemLineMsg.schdAgmtLineDealTaxAmt_A.setInputProtected(true);
            itemLineMsg.schdAgmtLineDealNetAmt_A.setInputProtected(true);
            itemLineMsg.xxTotAmt_A.setInputProtected(true);
            // START 2022/06/01 [QC#59973, ADD]
            itemLineMsg.shpgIntvlCd_A.setInputProtected(true);
            itemLineMsg.otmShipQty_A.setInputProtected(true);
            // END   2022/06/01 [QC#59973, ADD]

        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1840_BBMsg schdLineMsg = scrnMsg.B.no(i);

            schdLineMsg.xxLineNum_B.setInputProtected(true);
            schdLineMsg.rddDt_B.setInputProtected(false);
            schdLineMsg.ordQty_B.setInputProtected(false);
            schdLineMsg.ordQty_BD.setInputProtected(true);
            schdLineMsg.ordQty_BS.setInputProtected(true);
            schdLineMsg.ordMdseCd_B.setInputProtected(true);

            if (ZYPCommonFunc.hasValue(schdLineMsg.cpoOrdNum_B)) {

                schdLineMsg.cpoOrdNum_LK.setInputProtected(false);
                schdLineMsg.cpoOrdNum_LK.setValue(ZYPConstant.FLG_ON_Y);
            } else {

                schdLineMsg.cpoOrdNum_LK.setInputProtected(true);
                schdLineMsg.cpoOrdNum_LK.clear();
            }
        }

        // Comments Tab
        scrnMsg.shpgCmntTxt.setInputProtected(false);
        scrnMsg.itrlOrdCmntTxt.setInputProtected(false);
        scrnMsg.invCmntTxt.setInputProtected(false);

        // Additional Data Tab
        scrnMsg.xxPsnNm_SV.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_SV.setInputProtected(true);
        scrnMsg.xxPsnNm_SB.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_SB.setInputProtected(true);
        scrnMsg.prcContrNum.setInputProtected(false);
        scrnMsg.dsAcctClsDescTxt.setInputProtected(true);
        scrnMsg.xxScrItem54Txt_GL.setInputProtected(true);
        handler.setButtonEnabled(BTN_VIEW_CHNG_LOG_EVENT_NM, true);

        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            NWAL1840_FBMsg addlMsg = scrnMsg.F.no(i);
            addlMsg.mdseCd_F.setInputProtected(true);
            addlMsg.mdseDescShortTxt_F.setInputProtected(true);
            addlMsg.xxTotUnitNetWt_F.setInputProtected(true);
            addlMsg.coaMdseTpDescTxt_F.setInputProtected(true);
            addlMsg.coaProdDescTxt_F.setInputProtected(true);
            addlMsg.zerothProdCtrlNm_F.setInputProtected(true);
            addlMsg.firstProdCtrlNm_F.setInputProtected(true);
            addlMsg.scdProdCtrlNm_F.setInputProtected(true);
            addlMsg.thirdProdCtrlNm_F.setInputProtected(true);
            addlMsg.frthProdCtrlNm_F.setInputProtected(true);
        }
    }

    /**
     * Set Control Fields For Link
     * @param scrnMsg Screen Msg
     */
    private static void setControlFieldsForLink(NWAL1840BMsg scrnMsg) {

        // Header
        scrnMsg.dsOrdCatgDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.prcCatgNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.mdlNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.serNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.svcConfigMstrPk_LK.setValue(BigDecimal.ONE);
        scrnMsg.dsContrNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.serNum_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.pmtTermCashDiscDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Customer / Contact Tab
        scrnMsg.billToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.billToCustLocCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.billToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustAcctCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustLocCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.shipToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.sellToCustCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.soldToCustLocCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.soldToCustAcctNm_LK.setValue(ZYPConstant.FLG_ON_Y);

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1840_DBMsg contactMsg = scrnMsg.D.no(i);
            contactMsg.ctacPsnFirstNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        }

        // Header TAB
        scrnMsg.psnNum_LK.setValue(ZYPConstant.FLG_ON_Y); // S21_NA#7861 Mod slsRepPsnCd_PK -> psnNum_PK
        scrnMsg.slsRepTocNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.frtCondDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        // Items Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1840_ABMsg itemLineMsg = scrnMsg.A.no(i);
            itemLineMsg.mdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
            itemLineMsg.sbstMdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1840_BBMsg schdLineMsg = scrnMsg.B.no(i);
            if (ZYPCommonFunc.hasValue(schdLineMsg.cpoOrdNum_B)) {
                schdLineMsg.cpoOrdNum_LK.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                schdLineMsg.cpoOrdNum_LK.clear();
            }
        }
    }

    /**
     * Set Control Fields For Digit
     * @param scrnMsg Screen Msg
     */
    private static void setControlFieldsForDigit(NWAL1840BMsg scrnMsg) {

        // Header
        scrnMsg.xxSubTotCalcPrcAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotChrgPrcAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotTaxAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        scrnMsg.xxTotAmt.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());

        // Items Tab
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1840_ABMsg itemLineMsg = scrnMsg.A.no(i);
            itemLineMsg.dealPrcListPrcAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.dealNetUnitPrcAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxTotDiscAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.schdAgmtLineDealNetAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.schdAgmtLineDealFrtAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.schdAgmtLineDealTaxAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            itemLineMsg.xxTotAmt_A.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

        // Items Tab
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            NWAL1840_FBMsg addLineMsg = scrnMsg.F.no(i);
            addLineMsg.xxTotUnitNetWt_F.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }

    }

    /**
     * Set Screen Protect By Status
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1840BMsg
     */
    private static void setProtectByStatus(EZDCommonHandler handler, NWAL1840BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.schdAgmtStsCd.getValue())) {

            // Mod Start 2017/08/04 S21_NA#20228
//            if (!SCHD_AGMT_STS.SAVED.equals(scrnMsg.schdAgmtStsCd.getValue())) {
            if (!SCHD_AGMT_STS.SAVED.equals(scrnMsg.schdAgmtStsCd.getValue())
                    && !SCHD_AGMT_STS.ERROR.equals(scrnMsg.schdAgmtStsCd.getValue())) {
            // Mod End 2017/08/04 S21_NA#20228

                inactiveColumn(scrnMsg);
                // set Button Protect
                if (SCHD_AGMT_STS.ACTIVE.equals(scrnMsg.schdAgmtStsCd.getValue())) {
                    inactiveSaveButton(handler);
                } else if (SCHD_AGMT_STS.CANCELLED.equals(scrnMsg.schdAgmtStsCd.getValue())) {
                    inactiveRegistrationButton(handler);
                } else if (SCHD_AGMT_STS.ENDED.equals(scrnMsg.schdAgmtStsCd.getValue())) {
                    inactiveSaveButton(handler);
                }
            }
        // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
            handler.setButtonEnabled(BTN_NOTES_EVENT_NM, true);
        } else {
            handler.setButtonEnabled(BTN_NOTES_EVENT_NM, false);
        // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        }

        // set Item Line Protect
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINES.equals(dplyTab)) {
            setProtectByItemLineSts(scrnMsg);
            setProtectBySchdLineSts(scrnMsg);
        }
    }

    /**
     * Inactive column
     * @param scrnMsg NWAL1840BMsg
     */
    public static void inactiveColumn(NWAL1840BMsg scrnMsg) {

        scrnMsg.dsOrdCatgDescTxt.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.setInputProtected(true);
        scrnMsg.dsOrdCatgDescTxt_LK.clear();
        scrnMsg.dsOrdTpCd.setInputProtected(true);
    }

    /**
     * Set Screen Protect By Item Line Status
     * @param scrnMsg NWAL1840BMsg
     */
    private static void setProtectByItemLineSts(NWAL1840BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1840_ABMsg itemLineMsg = scrnMsg.A.no(i);
            final String cancelFlgA = itemLineMsg.schdAgmtLineCancFlg_A.getValue();

            if (ZYPConstant.FLG_ON_Y.equals(cancelFlgA)) {
                setLineItemProtectForCanceled(itemLineMsg);
                continue;
            }

            setLineItemProtectForUnregistered(itemLineMsg);
        }
    }

    /**
     * Set Additional Tab Item Protect [Header Status : Canceled]
     * @param scrnMsg NWAL1840BMsg
     */
    private static void setLineItemProtectForCanceled(NWAL1840_ABMsg itemLineMsg) {

        itemLineMsg.xxChkBox_A.setInputProtected(true);
        itemLineMsg.mdseCd_A.setInputProtected(true);
        itemLineMsg.mdseCd_LK.clear();
        itemLineMsg.supdLockFlg_A.setInputProtected(true);
        itemLineMsg.sbstMdseCd_A.setInputProtected(true);
        itemLineMsg.sbstMdseCd_LK.clear();
        itemLineMsg.schdAllwQty_A.setInputProtected(true);
        // START 2022/06/01 [QC#59973, ADD]
        itemLineMsg.shpgIntvlCd_A.setInputProtected(true);
        itemLineMsg.otmShipQty_A.setInputProtected(true);
        // END   2022/06/01 [QC#59973, ADD]
    }

    /**
     * Set Line Item Protect [Line Status : UnSaved]
     * @param lineMsg NWAL1840_BBMsg
     */
    private static void setLineItemProtectForUnregistered(NWAL1840_ABMsg itemLineMsg) {

        itemLineMsg.xxChkBox_A.setInputProtected(false);
        itemLineMsg.mdseCd_A.setInputProtected(false);
        itemLineMsg.mdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        itemLineMsg.supdLockFlg_A.setInputProtected(false);
        itemLineMsg.sbstMdseCd_A.setInputProtected(false);
        itemLineMsg.sbstMdseCd_LK.setValue(ZYPConstant.FLG_ON_Y);
        itemLineMsg.schdAllwQty_A.setInputProtected(false);
        // START 2022/06/01 [QC#59973, ADD]
        itemLineMsg.shpgIntvlCd_A.setInputProtected(false);
        itemLineMsg.otmShipQty_A.setInputProtected(false);
        // END   2022/06/01 [QC#59973, ADD]
    }

    /**
     * Set Screen Protect By Item Line Status
     * @param scrnMsg NWAL1840BMsg
     */
    private static void setProtectBySchdLineSts(NWAL1840BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1840_BBMsg schdLineMsg = scrnMsg.B.no(i);
            final String cancelFlgB = schdLineMsg.schdAgmtPlnCancFlg_B.getValue();
            final String exstFlgB = schdLineMsg.xxExstFlg_B.getValue();
            // Add 2016/08/29 QC#11883
            final String OrderCancFlg = schdLineMsg.cancFlg_B.getValue();
            
            if (ZYPConstant.FLG_ON_Y.equals(cancelFlgB)) {
                setSchdLineProtectForCanceled(schdLineMsg);
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(exstFlgB)) {
                setSchdLineProtectForOrdered(schdLineMsg);
                continue;
            }
            // Add Start 2016/08/29 QC#11883
            if (ZYPConstant.FLG_ON_Y.equals(OrderCancFlg)) {
                setSchdLineProtectForOrdered(schdLineMsg);
                continue;
            }
            // Add End 2016/08/29 QC#11883
            setSchdLineProtectForUnregistered(schdLineMsg);
        }
    }

    /**
     * Set Schedule Line Protect [Line Status : UnSaved]
     * @param itemLineMsg NWAL1840_BBMsg
     */
    private static void setSchdLineProtectForCanceled(NWAL1840_BBMsg itemLineMsg) {

        itemLineMsg.xxChkBox_B.setInputProtected(true);
        itemLineMsg.rddDt_B.setInputProtected(true);
        itemLineMsg.ordQty_B.setInputProtected(true);
    }

    /**
     * Set Schedule Line Protect [Line Status : Ordered]
     * @param itemLineMsg NWAL1840_BBMsg
     */
    private static void setSchdLineProtectForOrdered(NWAL1840_BBMsg itemLineMsg) {

        itemLineMsg.xxChkBox_B.setInputProtected(false);
        itemLineMsg.rddDt_B.setInputProtected(true);
        itemLineMsg.ordQty_B.setInputProtected(true);
    }

    /**
     * Set Schedule Line Protect [Line Status : UnSaved]
     * @param itemLineMsg NWAL1840_BBMsg
     */
    private static void setSchdLineProtectForUnregistered(NWAL1840_BBMsg itemLineMsg) {

        itemLineMsg.xxChkBox_B.setInputProtected(false);
        itemLineMsg.rddDt_B.setInputProtected(false);
        itemLineMsg.ordQty_B.setInputProtected(false);
    }

    /**
     * Set Screen Protect By Inherent Control
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1840BMsg
     */
    public static void setProtectByInherentControl(EZDCommonHandler handler, NWAL1840BMsg scrnMsg) {

        // Set Protect By FrtCond
        // QC#23726 2018/06/26 mod Start
        scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
        scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        if (FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            scrnMsg.carrAcctNum.setInputProtected(false);
//            scrnMsg.carrSvcLvlDescTxt.setInputProtected(false);
//            scrnMsg.carrSvcLvlDescTxt_LK.setInputProtected(false);
//            scrnMsg.carrSvcLvlDescTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.carrAcctNum.setInputProtected(true);
//            scrnMsg.carrSvcLvlDescTxt.setInputProtected(true);
            scrnMsg.carrAcctNum.clear();
//            scrnMsg.carrSvcLvlDescTxt.clear();
//            scrnMsg.carrSvcLvlDescTxt_LK.clear();
        }
        // QC#2372 2018/06/26 mod End
        // QC#17474 2017/02/21 Add Start
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.ovrdPmtTermFlg.getValue())) {
            scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(false);
            scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(false);
        } else {
            scrnMsg.pmtTermCashDiscDescTxt.setInputProtected(true);
            scrnMsg.pmtTermCashDiscDescTxt_LK.setInputProtected(true);
        }
        // QC#17474 2017/02/21 Add End

        // set Protect by flag
        if (ZYPCommonFunc.hasValue(scrnMsg.dropShipAvalFlg) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.dropShipAvalFlg.getValue())) {
            scrnMsg.dropShipFlg_LK.setInputProtected(false);
            scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.dropShipFlg_LK.setInputProtected(true);
            scrnMsg.dropShipFlg_LK.clear();
        }

        // set Protect by searchNumflag
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSrchNum)) {
            scrnMsg.schdAgmtNum.setInputProtected(true);
            handler.setButtonEnabled(BTN_SRCH_EVENT_NM, false);
            handler.setButtonEnabled(BTN_ATT_EVENT_NM, true);
        } else {
            scrnMsg.schdAgmtNum.setInputProtected(false);
            handler.setButtonEnabled(BTN_SRCH_EVENT_NM, true);
            handler.setButtonEnabled(BTN_ATT_EVENT_NM, false);
        }

        setProtectByContact(scrnMsg);        // QC#16452 add
    }

    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1840BMsg
     */
    private static void setProtectByAuthority(EZDCommonHandler handler, NWAL1840BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            String funcId = scrnMsg.Z.no(i).xxFuncId.getValue();

            // Only Reference Authority
            if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(funcId)) {
                NWAL1840CommonLogic.inactiveRegistrationButton(handler);
            }

            // Registration Authority
            if (ALL_RGTN_AUTH.equals(funcId)) {
                setEnableByAllRgtnAuth(handler, scrnMsg);
            }
        }
    }

    /**
     * Set Enable By All Registration Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1840BMsg
     */
    public static void setEnableByAllRgtnAuth(EZDCommonHandler handler, NWAL1840BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_PRICE_CHANGE_EVENT_NM, true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1840_ABMsg itemLineMsg = scrnMsg.A.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(itemLineMsg.schdAgmtLineCancFlg_A.getValue())) {

                // Mod Start 2016/09/05 #11752
                // itemLineMsg.dealNetUnitPrcAmt_A.setInputProtected(false);
                if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.xxErrFlg_A.getValue())) {
                    itemLineMsg.dealNetUnitPrcAmt_A.setInputProtected(true);
                } else {
                    itemLineMsg.dealNetUnitPrcAmt_A.setInputProtected(false);
                }
                // Mod End 2016/09/05 #11752
            }
        }
    }

    /**
     * set Table's Back Color(For Contact Line)
     * @param scrnMsg NWAL1840BMsg
     */
    public static final void setTblBackColorForContact(NWAL1840BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("D", scrnMsg.D);
        tblColor.setAlternateRowsBG("D", scrnMsg.D);
    }

    /**
     * set Table's Back Color(For Summary Line)
     * @param scrnMsg NWAL1840BMsg
     */
    public static final void setTblBackColorForSummary(NWAL1840BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("G", scrnMsg.G);
        tblColor.setAlternateRowsBG("G", scrnMsg.G);
    }

    /**
     * set Table's Back Color(For Additional Line)
     * @param scrnMsg NWAL1840BMsg
     */
    public static final void setTblBackColorForAddLine(NWAL1840BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("F", scrnMsg.F);
        tblColor.setAlternateRowsBG("F", scrnMsg.F);
    }

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /**
     * set Table's Back Color(For SA History)
     * @param scrnMsg NWAL1840BMsg
     */
    public static final void setTblBackColorForSaHistory(NWAL1840BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("H", scrnMsg.H);
        tblColor.setAlternateRowsBG("H", scrnMsg.H);
    }
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]

    // Add Start 2016/11/15 #15875
    /**
     * Set Param NSAL0110
     * 
     * @param scrnMsg NWAL1840BMsg
     * @return Param NSAL0110
     */
    public static Object[] setParamNSAL0110(NWAL1840BMsg scrnMsg) {

        clearPopupParmNSAL0110(scrnMsg);

        List<Object> paramList = new ArrayList<Object>(12);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.dsContrNum);
        paramList.add(scrnMsg.xxPopPrm_00); // Contract # 
        paramList.add(scrnMsg.xxPopPrm_01); // Contract Control Status Code 
        paramList.add(scrnMsg.xxPopPrm_02); // Contract Name
        paramList.add(scrnMsg.xxPopPrm_03); // Contract Category Code
        paramList.add(scrnMsg.xxPopPrm_04); // Account Code
        paramList.add(scrnMsg.xxPopPrm_05); // Contract Detail Type Code
        paramList.add(scrnMsg.xxPopPrm_06); // Contract Control Status Code (Detail)
        paramList.add(scrnMsg.xxPopPrm_07); // Serial #
        paramList.add(scrnMsg.xxPopPrm_08); // Model Name
        paramList.add(scrnMsg.xxPopPrm_09); // Desplay Mode
        // output
        paramList.add(scrnMsg.dsContrPk);    // DS Contract PK
        paramList.add(scrnMsg.dsContrDtlPk); // DS Contract Detail PK 

        return paramList.toArray(new Object[paramList.size()]);
    }

    /**
     * Clear Pop param for NSAL1240
     * @param scrnMsg
     */
    private static void clearPopupParmNSAL0110(NWAL1840BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.dsContrPk.clear();
        scrnMsg.dsContrDtlPk.clear();
    }
    // Add End 2016/11/15 #15875
    
    /**
     * Get Param NSAL1240 For Configuration ID
     * @param scrnMsg NWAL1840BMsg
     * @return Param NSAL1240
     */
    public static Object[] getParamNSAL1240(NWAL1840BMsg scrnMsg) {

        clearPopupParmNSAL1240(scrnMsg);

        List<Object> parameters = new ArrayList<Object>(IDX_31);

        // [0]: CONFIG_EXST_MODE_CD
        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL1240Constant.MODE_02);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NSAL1240_MODE_02);
        // Mod End 2016/08/04 QC#9078
        parameters.add(scrnMsg.xxPopPrm_00);

        // [1]: SVC_CONFIG_MSTR_PK
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_Q, scrnMsg.svcConfigMstrPk);
        parameters.add(scrnMsg.svcConfigMstrPk_Q);

        // [2]: SER_NUM
        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.serNum_Q);

        // [3]: SVC_MACH_MSTR_PK
        // ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk_Q, scrnMsg.svcMachMstrPk_Q);
        parameters.add(scrnMsg.svcMachMstrPk_Q);

        // [4]: MDSE_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_Q, scrnMsg.mdseCd);
        parameters.add(scrnMsg.mdseCd_Q);

        // [5]: MDL_NM
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_Q, scrnMsg.mdlNm);
        parameters.add(scrnMsg.mdlNm_Q);

        // [6]: SHIP_FEOM_DT
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_01);

        // [7]: SHIP_THRU_DT
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_02);

        // [8]: SVC_MACH_USG_STS_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.svcMachUsgStsCd_Q);

        // [9]: SVC_MACH_MSTR_STS_CD, Array
        parameters.add((EZDBMsgArray) scrnMsg.Y);

        // [10]: SVC_MACH_MSTR_STS_EDIT_FLG
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxEdtModeFlg_Q1);

        // [11]: MACH_ALLOC_MODE_CODE
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_03);

        // [12]: MAIN_UNIT_FLG
        // ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.xxEdtModeFlg_Q2);

        // [13]: STK_STS_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_04);

        // [14]: WH_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_05);

        // [15]: SUB_WH_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_06);

        // [16]: SVC_SLN_SQ
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_07);

        // [17]: SVC_SLN_NM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_08);

        // [18]: CONTR_NUM
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, scrnMsg.dsContrNum);
        parameters.add(scrnMsg.xxPopPrm_09);

        // [19]: DS_OWNR_ACCT_NUM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0A, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0A);

        // [20]: OWNR_LOC_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0B, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0B);

        // [21]: DS_BILL_TO_ACCT_NUM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0C, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0C);

        // [22]: BILL_TO_LOC_CD
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0D, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0D);

        // [23]: DS_CUR_LOC_ACCT_NUM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0E, scrnMsg.serNum);
        parameters.add(scrnMsg.xxPopPrm_0E);

        // [24]: CUR_LOC_NUM
        // ZYPEZDItemValueSetter.setValue(scrnMsg.curLocNum_Q, scrnMsg.serNum);
        parameters.add(scrnMsg.curLocNum_Q);

        // Output Parameter
        parameters.add(scrnMsg.mdlId_QO); // [25]: MDL_ID
        parameters.add(scrnMsg.mdlNm_QO); // [26]: MDL_NM
        parameters.add(scrnMsg.contrPk_QO); // [27]: CONTR_PK
        parameters.add(scrnMsg.contrNum_QO); // [28]: CONTR_NUM
        parameters.add(scrnMsg.svcConfigMstrPk_QO); // [29]: SVC_CONFIG_MSTR_PK
        parameters.add((EZDBMsgArray) scrnMsg.Q); // [30]:
        return parameters.toArray(new Object[parameters.size()]);
    }

    /**
     * Clear Pop param for NSAL1240
     * @param scrnMsg
     */
    private static void clearPopupParmNSAL1240(NWAL1840BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_0A.clear();
        scrnMsg.xxPopPrm_0B.clear();
        scrnMsg.xxPopPrm_0C.clear();
        scrnMsg.xxPopPrm_0D.clear();
        scrnMsg.xxPopPrm_0E.clear();
        scrnMsg.Y.clear();
        scrnMsg.mdlId_Q.clear();
        scrnMsg.mdlNm_Q.clear();
        scrnMsg.serNum_Q.clear();
        scrnMsg.contrPk_Q.clear();
        scrnMsg.contrNum_Q.clear();
        scrnMsg.svcConfigMstrPk_Q.clear();
        scrnMsg.mdseCd_Q.clear();
        scrnMsg.curLocNum_Q.clear();
        scrnMsg.locNm_Q.clear();
        scrnMsg.svcMachUsgStsCd_Q.clear();
        scrnMsg.svcMachMstrPk_Q.clear();
        scrnMsg.xxEdtModeFlg_Q1.clear();
        scrnMsg.xxEdtModeFlg_Q2.clear();
        scrnMsg.mdlId_QO.clear();
        scrnMsg.mdlNm_QO.clear();
        scrnMsg.contrPk_QO.clear();
        scrnMsg.contrNum_QO.clear();
        scrnMsg.svcConfigMstrPk_QO.clear();
        scrnMsg.Q.clear();
    }
    
    /**
     * Get Param NMAL6050 For Payment Term
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6050ForPmtTerm(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_11);

        // [0]:TBL_NM
        scrnMsg.xxPopPrm_00.setValue("PMT_TERM_CASH_DISC");
        paramList.add(scrnMsg.xxPopPrm_00);

        // [1]:TBL_CD_COL_NM
        scrnMsg.xxPopPrm_01.setValue("PMT_TERM_CASH_DISC_CD");
        paramList.add(scrnMsg.xxPopPrm_01);

        // [2]:TBL_NM_COL_NM
        scrnMsg.xxPopPrm_02.setValue("PMT_TERM_CASH_DISC_DESC_TXT");
        paramList.add(scrnMsg.xxPopPrm_02);

        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxPopPrm_03.setValue("PMT_TERM_CASH_DISC_SORT_NUM");
        paramList.add(scrnMsg.xxPopPrm_03);

        // [4]:SCR_NM
        scrnMsg.xxPopPrm_04.setValue("Payment Term Search");
        paramList.add(scrnMsg.xxPopPrm_04);

        // [5]:HDR_CD_LB_NM
        scrnMsg.xxPopPrm_05.setValue("Payment Term Code");
        paramList.add(scrnMsg.xxPopPrm_05);

        // [6]:HDR_NM_LB_NM
        scrnMsg.xxPopPrm_06.setValue("Payment Term Name");
        paramList.add(scrnMsg.xxPopPrm_06);

        // [7]:DTL_CD_LB_NM
        scrnMsg.xxPopPrm_07.setValue("Payment Term Code");
        paramList.add(scrnMsg.xxPopPrm_07);

        // [8]:DTL_NM_LB_NM
        scrnMsg.xxPopPrm_08.setValue("Payment Term Name");
        paramList.add(scrnMsg.xxPopPrm_08);

        // [9]:COND_CD
        scrnMsg.xxPopPrm_09.clear();
        paramList.add(scrnMsg.xxPopPrm_09);

        // [10]:COND_NM
        paramList.add(scrnMsg.pmtTermCashDiscDescTxt);

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NMAL6770 For Bill To
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6770 For Bill To
     */
    public static Object[] getParamNMAL6760ForBillTo(NWAL1840BMsg scrnMsg) {

        // 2018/06/21 QC#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_0A.getValue();
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_0C.getValue();
        // 2018/07/10 S21_NA#26661,26713 Add End

        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        clearPopUpParam(scrnMsg);

        String billToAcctNum = getBillToAcctNum(scrnMsg);

        // Mod Start 2016/08/04 QC#9028
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod End 2016/08/04 QC#9028
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(billToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, billToAcctNum);

        // 2018/07/26 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0A, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0B, BIZ_ID);
        // 2018/07/26 QC#14307 Add End
        // 2018/07/26 QC#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0C, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0D, scrnMsg.lineBizTpCd);
        // 2018/07/26 QC#26661,26713 Add End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_27);
        paramList.add(scrnMsg.xxPopPrm_06);
        paramList.add(scrnMsg.billToCustAcctNm);
        // 2018/03/09 S21_NA#22387 Mod Start
//        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_08); // Location
        // 2018/03/09 S21_NA#22387 Mod End
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Active Only
        paramList.add(scrnMsg.xxPopPrm_02); // Bill To's Only
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_07); // Bill To Location
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_03); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.xxPopPrm_04); // Account Number Active Flag
        paramList.add(scrnMsg.xxPopPrm_05); // Status Active Flag

        // 2018/07/26 QC#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_00); // [27]
        paramList.add(scrnMsg.xxPopPrm_00); // [28]
        paramList.add(scrnMsg.xxPopPrm_00); // [29]
        paramList.add(scrnMsg.xxPopPrm_00); // [30]
        paramList.add(scrnMsg.xxPopPrm_00); // [31]
        paramList.add(scrnMsg.xxPopPrm_00); // [32]
        paramList.add(scrnMsg.xxPopPrm_00); // [33]
        paramList.add(scrnMsg.xxPopPrm_00); // [34]
        paramList.add(scrnMsg.xxPopPrm_00); // [35]
        paramList.add(scrnMsg.xxPopPrm_09); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_0A); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_00); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_0B); // [39] Function ID
        // 2018/07/26 QC#14307 Add End

        // 2018/07/26 QC#26661,26713 Add Start
        paramList.add(scrnMsg.xxPopPrm_0C); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_0D); // [41] Line of Business Code
        // 2018/07/26 QC#26661,26713 Add End

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NMAL6770 For Ship To
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6770 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL1840BMsg scrnMsg) {

        // 2018/07/26 QC#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_0A.getValue();
        // 2018/07/261 QC#14307 Add End
        // 2018/07/26 QC#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_0C.getValue();
        // 2018/07/26 QC#26661,26713 Add End

        scrnMsg.firstRefCmntTxt.clear();
        scrnMsg.scdRefCmntTxt.clear();
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.cntyNm.clear();
        scrnMsg.provNm.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        scrnMsg.ctryCd.clear();
        clearPopUpParam(scrnMsg);

        String shipToAcctNum = getShipToAcctNum(scrnMsg);

        // Mod Start 2016/08/04 QC#9028
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        // Mod Start 2016/08/04 QC#9028
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(shipToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, shipToAcctNum);

        // 2018/07/26 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0A, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0B, BIZ_ID);
        // 2018/07/26 QC#14307 Add End
        // 2018/07/26 QC#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0C, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0D, scrnMsg.lineBizTpCd);
        // 2018/07/26 QC#26661,26713 Add End
 
        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_33);
        paramList.add(scrnMsg.xxPopPrm_06); // Ship To Account
        paramList.add(scrnMsg.shipToCustAcctNm);
        paramList.add(scrnMsg.xxPopPrm_08); // Location Number
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Active Only
        paramList.add(scrnMsg.xxPopPrm_02); // Ship To's Only
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_07); // Ship To Location
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.cntyNm);
        paramList.add(scrnMsg.provNm);
        paramList.add(scrnMsg.ctryCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_03); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.xxPopPrm_04); // Account Number Active Flag
        paramList.add(scrnMsg.xxPopPrm_05); // Status Active Flag
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.firstRefCmntTxt); // First Ref Comment
        paramList.add(scrnMsg.scdRefCmntTxt); // Second Ref Comment

        // 2018/07/26 QC#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_00); // [33]
        paramList.add(scrnMsg.xxPopPrm_00); // [34]
        paramList.add(scrnMsg.xxPopPrm_00); // [35]
        paramList.add(scrnMsg.xxPopPrm_09); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_0A); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_00); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_0B); // [39] Function ID
        // 2018/07/26 QC#14307 Add End

        // 2018/07/26 QC#26661,26713 Add Start
        paramList.add(scrnMsg.xxPopPrm_0C); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_0D); // [41] Line of Business Code
        // 2018/07/26 QC#26661,26713 Add End

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NMAL6770 For Sold To
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6770 For Sold To
     */
    public static Object[] getParamNMAL6760ForSoldTo(NWAL1840BMsg scrnMsg) {

        // 2018/07/26 QC#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_0A.getValue();
        // 2018/07/26 QC#14307 Add End
        // 2018/07/26 QC#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_0C.getValue();
        // 2018/07/26 QC#26661,26713 Add End

        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        clearPopUpParam(scrnMsg);

        String soldToAcctNum = getSoldToAcctNum(scrnMsg);

        // Mod Start 2016/08/04 QC#9028
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod End 2016/08/04 QC#9028
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(soldToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, soldToAcctNum);

        // 2018/07/26 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0A, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0B, BIZ_ID);
        // 2018/07/26 QC#14307 Add End
        // 2018/07/26 QC#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0C, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0D, scrnMsg.lineBizTpCd);
        // 2018/07/26 QC#26661,26713 Add End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_26);
        paramList.add(scrnMsg.xxPopPrm_06); // Sold To Account
        paramList.add(scrnMsg.soldToCustAcctNm);
        // 2018/03/09 S21_NA#22387 Mod Start
//        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_08); // Location
        // 2018/03/09 S21_NA#22387 Mod End
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Active Only
        paramList.add(scrnMsg.xxPopPrm_02); // Bill To's Only
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_07); // Sold To Location
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_03); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.xxPopPrm_04); // Account Number Active Flag
        paramList.add(scrnMsg.xxPopPrm_05); // Status Active Flag

        // 2018/07/26 QC#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_00); // [27]
        paramList.add(scrnMsg.xxPopPrm_00); // [28]
        paramList.add(scrnMsg.xxPopPrm_00); // [29]
        paramList.add(scrnMsg.xxPopPrm_00); // [30]
        paramList.add(scrnMsg.xxPopPrm_00); // [31]
        paramList.add(scrnMsg.xxPopPrm_00); // [32]
        paramList.add(scrnMsg.xxPopPrm_00); // [33]
        paramList.add(scrnMsg.xxPopPrm_00); // [34]
        paramList.add(scrnMsg.xxPopPrm_00); // [35]
        paramList.add(scrnMsg.xxPopPrm_09); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_0A); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_00); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_0B); // [39] Function ID
        // 2018/07/26 QC#14307 Add End

        // 2018/07/26 QC#26661,26713 Add Start
        paramList.add(scrnMsg.xxPopPrm_0C); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_0D); // [41] Line of Business Code
        // 2018/07/26 QC#26661,26713 Add End
        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NMAL6770
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6770
     */
    public static Object[] getParamNMAL6770(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        // QC#16452 del Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, scrnMsg.shipToCustAcctCd);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.locNum);
        // QC#16452 del End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, ZYPConstant.FLG_OFF_N);
        // QC#16452 del Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, ZYPConstant.FLG_ON_Y);
//        }
        // QC#16452 del End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);  // QC#16542 add

        NWAL1840_DBMsg contactLineMsg = scrnMsg.D.no(scrnMsg.xxCellIdx.getValueInt());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, contactLineMsg.ctacPsnTpCd_D); // QC#16452 add

        // 2018/03/09 S21_NA#22387 Add Start
        if (CTAC_CUST_REF_TP.SOLD_TO.equals(contactLineMsg.ctacCustRefTpCd_D.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.sellToLocNum.getValue());
        } else if (CTAC_CUST_REF_TP.BILL_TO.equals(contactLineMsg.ctacCustRefTpCd_D.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.billToLocNum.getValue());
        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(contactLineMsg.ctacCustRefTpCd_D.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.shipToLocNum.getValue());
        }
        // 2018/03/09 S21_NA#22387 Add End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_22);
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Role
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        // QC#16452 mod Start
//        paramList.add(scrnMsg.xxPopPrm_06); // Ship To Account
//        paramList.add(scrnMsg.xxPopPrm_07); // Location Number
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        // 2018/03/09 S21_NA#22387 Mod Start
//        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_07);
        // 2018/03/09 S21_NA#22387 Mod End
        // QC#16452 mod End
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(contactLineMsg.ctacPsnFirstNm_D);
        paramList.add(contactLineMsg.ctacPsnLastNm_D);
        paramList.add(scrnMsg.xxPopPrm_02); // Phone
        paramList.add(scrnMsg.xxPopPrm_03); // Email
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_04); // Ext
        paramList.add(scrnMsg.xxPopPrm_05); // Fax
        paramList.add(contactLineMsg.ctacPsnPk_D); // no used
        paramList.add(contactLineMsg.ctacPsnPk_D); // no used
        paramList.add(contactLineMsg.ctacPsnPk_D); // no used
        paramList.add(contactLineMsg.ctacPsnPk_D); // no used
        paramList.add(contactLineMsg.ctacPsnPk_D); // Contact PK
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_08); // Disable to Account Number
        // QC#16452 add Start
        paramList.add(null); // 22 no used
        paramList.add(null); // 23 no used
        paramList.add(null); // 24 no used
        paramList.add(null); // 25 no used
        paramList.add(null); // 26 no used
        paramList.add(null); // 27 no used
        paramList.add(null); // 28 no used
        paramList.add(null); // 29 no used
        paramList.add(null); // 30 no used
        paramList.add(scrnMsg.xxPopPrm_09); // 31.Disable to Include Location
        // QC#16452 add End
        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, EIGHT_DIGIT_MODE);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_10);
        paramList.add(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_A);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_00);

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    // Add Start 2016/09/28 S21_NA#11655
    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800ForMan(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, EIGHT_DIGIT_MODE);

        String MnfCode = scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_A.getValue();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, MnfCode);
        scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_A.clear();

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_13);
        paramList.add(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_A);
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_02); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Manufacture

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }
    // Add End 2016/09/28 S21_NA#11655

    /**
     * Get Param NWAL0140
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL0140
     */
    public static Object[] getParamNWAL0140(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, ZYPConstant.FLG_OFF_N);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_22);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.shipToCustLocCd);
        paramList.add(scrnMsg.shipToLocNm);
        paramList.add(scrnMsg.shipToAddlLocNm);
        paramList.add(scrnMsg.shipToFirstLineAddr);
        paramList.add(scrnMsg.shipToScdLineAddr);
        paramList.add(scrnMsg.shipToThirdLineAddr);
        paramList.add(scrnMsg.shipToFrthLineAddr);
        paramList.add(scrnMsg.shipTo01RefCmntTxt);
        paramList.add(scrnMsg.shipTo02RefCmntTxt);
        paramList.add(scrnMsg.shipToCtyAddr);
        paramList.add(scrnMsg.shipToStCd);
        paramList.add(scrnMsg.shipToPostCd);
        paramList.add(scrnMsg.shipToCtryCd);
        paramList.add(scrnMsg.shipToCntyNm);
        paramList.add(scrnMsg.dropShipFlg);
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.billToCustLocCd);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.shipToProvNm);

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    /**
     * Get Param NSBL0160
     * @param scrnMsg NWAL1840BMsg
     * @return Param NSBL0160
     */
    public static Object[] getParamNSBL0160(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, SCHD_AGNT_MEMO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, MEMO_ENTRY_ITEM_TITLE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.schdAgmtNum.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0E, MEMO_ENTRY_KEY_TRX_NUM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_0F, scrnMsg.schdAgmtNum.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_10, MEMO_ENTRY_KEY_TRX_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, MEMO_ENTRY_VAL_TRX_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_16, ZYPConstant.FLG_OFF_N);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_00);     //  [0]:"09"
        paramList.add(scrnMsg.xxPopPrm_01);     //  [1]:not used
        paramList.add(scrnMsg.xxPopPrm_02);     //  [2]:"SA Number"
        paramList.add(scrnMsg.xxPopPrm_03);     //  [3]:SCHD_AGMT_NUM
        paramList.add(scrnMsg.xxPopPrm_04);     //  [4]:not used
        paramList.add(scrnMsg.xxPopPrm_05);     //  [5]:not used
        paramList.add(scrnMsg.xxPopPrm_06);     //  [6]:not used
        paramList.add(scrnMsg.xxPopPrm_07);     //  [7]:not used
        paramList.add(scrnMsg.xxPopPrm_08);     //  [8]:not used
        paramList.add(scrnMsg.xxPopPrm_09);     //  [9]:not used
        paramList.add(scrnMsg.xxPopPrm_0A);     // [10]:not used
        paramList.add(scrnMsg.xxPopPrm_0B);     // [11]:not used
        paramList.add(scrnMsg.xxPopPrm_0C);     // [12]:not used
        paramList.add(scrnMsg.xxPopPrm_0D);     // [13]:not used
        paramList.add(scrnMsg.xxPopPrm_0E);     // [14]:"SVC_MEMO_TRX_NUM"
        paramList.add(scrnMsg.xxPopPrm_0F);     // [15]:SCHD_AGMT_NUM
        paramList.add(scrnMsg.xxPopPrm_10);     // [16]:"SVC_MEMO_TRX_NM"
        paramList.add(scrnMsg.xxPopPrm_11);     // [17]:"SCHD_AGMT_NUM"
        paramList.add(scrnMsg.xxPopPrm_12);     // [18]:not used
        paramList.add(scrnMsg.xxPopPrm_13);     // [19]:not used
        paramList.add(scrnMsg.xxPopPrm_13);     // [20]:not used
        paramList.add(scrnMsg.xxPopPrm_15);     // [21]:not used
        paramList.add(scrnMsg.xxPopPrm_16);     // [22]:"N"

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }
    // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]

    /**
     * Combine Customer Address
     * @param scrnMsg NWAL1840BMsg
     * @param connectValue Connect Value
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1840BMsg scrnMsg, String connectValue) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToFirstLineAddr)) {
            return null;
        }
        
        // 2018/02/23 S21_NA#22399 Mod Start
        //StringBuilder addr = new StringBuilder(scrnMsg.shipToFirstLineAddr.getValue());
        StringBuilder addr = new StringBuilder("");        
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_DS, scrnMsg.shipToLocNm);
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToAddlLocNm)) {
                addr.append(scrnMsg.shipToAddlLocNm.getValue());
                addr.append(connectValue);
            }
        }
        addr.append(scrnMsg.shipToFirstLineAddr.getValue());
        // 2018/02/23 S21_NA#22399 Mod Start

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToScdLineAddr)) {
            addr.append(connectValue);
            addr.append(scrnMsg.shipToScdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToThirdLineAddr)) {
            addr.append(connectValue);
            addr.append(scrnMsg.shipToThirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToFrthLineAddr)) {
            addr.append(connectValue);
            addr.append(scrnMsg.shipToFrthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCtyAddr)) {
            addr.append(connectValue);
            addr.append(scrnMsg.shipToCtyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToStCd)) {
            addr.append(COMMA);
            addr.append(scrnMsg.shipToStCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToPostCd.getValue());
        }

        return addr.toString();
    }

    /**
     * Get Param NWAL1130 For Carrier Service Level
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1130 Carrier Service Level
     */
    public static Object[] getParamNWAL1130ForCarrSvcLvl(NWAL1840BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Carrier Service Level Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    CSL.GLBL_CMPY_CD ");
        sb.append("   ,CSL.EZCANCELFLAG ");
        sb.append("   ,CSL.CARR_SVC_LVL_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_DESC_TXT ");
        sb.append("   ,CSL.CARR_SVC_LVL_SORT_NUM ");
        sb.append("FROM  ");
        sb.append("    FRT_COND_SVC_LVL_RELN RELN ");
        sb.append("   ,DS_ORD_TP_PROC_DFN OTD ");
        sb.append("   ,CARR_SVC_LVL CSL ");
        // 2018/12/11 S21_NA#29315 Add Start
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("   ,SHPG_SVC_LVL_CARR_RELN SSL ");
            sb.append(",(");
            sb.append(" SELECT");
            sb.append("     DAC.VND_CD");
            sb.append(" FROM");
            sb.append("     DS_ACCT_CARR DAC");
            sb.append(" WHERE");
            sb.append("         DAC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("     AND DAC.DS_ACCT_NUM IS NULL");
            sb.append("     AND DAC.LOC_NUM      = '").append(scrnMsg.shipToLocNum.getValue()).append("' ");
            sb.append("     AND DAC.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("     AND (DAC.EFF_THRU_DT >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("      OR DAC.EFF_THRU_DT IS NULL)");
            sb.append("     AND DAC.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("     AND DAC.DS_BIZ_AREA_CD = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("     AND DAC.EZCANCELFLAG = '0'");

            sb.append(" UNION ALL");
            sb.append(" SELECT");
            sb.append("     DAC.VND_CD");
            sb.append(" FROM");
            sb.append("     DS_ACCT_CARR DAC");
            sb.append(" WHERE");
            sb.append("         DAC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("     AND DAC.DS_ACCT_NUM = '").append(scrnMsg.shipToCustAcctCd.getValue()).append("' ");
            sb.append("     AND DAC.LOC_NUM      IS NULL");
            sb.append("     AND DAC.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("     AND (DAC.EFF_THRU_DT >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("      OR DAC.EFF_THRU_DT IS NULL)");
            sb.append("     AND DAC.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("     AND DAC.DS_BIZ_AREA_CD = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("     AND DAC.EZCANCELFLAG = '0'");
            sb.append("     AND NOT EXISTS (");
            sb.append("         SELECT");
            sb.append("             1");
            sb.append("         FROM");
            sb.append("             DS_ACCT_CARR DAC2");
            sb.append("         WHERE");
            sb.append("                DAC2.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("            AND DAC2.DS_ACCT_NUM IS NULL");
            sb.append("            AND DAC2.LOC_NUM      = '").append(scrnMsg.shipToLocNum.getValue()).append("' ");
            sb.append("            AND DAC2.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("            AND (DAC2.EFF_THRU_DT >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("             OR DAC2.EFF_THRU_DT IS NULL)");
            sb.append("            AND DAC2.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("            AND DAC2.DS_BIZ_AREA_CD = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("            AND DAC2.EZCANCELFLAG    = '0'");
            sb.append("         )");
            sb.append(" )DACV ");
        }
        // 2018/12/11 S21_NA#29315 Add End
        sb.append("WHERE ");
        sb.append("    RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND RELN.FRT_COND_CD     = '").append(scrnMsg.frtCondCd.getValue()).append("' ");
        sb.append("AND RELN.SHPG_SVC_LVL_CD = '").append(scrnMsg.shpgSvcLvlCd.getValue()).append("' ");
        sb.append("AND RELN.EZCANCELFLAG    = '0' ");
        sb.append("AND RELN.GLBL_CMPY_CD    = OTD.GLBL_CMPY_CD ");
        sb.append("AND RELN.LINE_BIZ_TP_CD  = OTD.LINE_BIZ_TP_CD ");
        sb.append("AND OTD.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("' ");
        sb.append("AND OTD.EZCANCELFLAG     = '0' ");
        sb.append("AND RELN.GLBL_CMPY_CD    = CSL.GLBL_CMPY_CD ");
        sb.append("AND RELN.CARR_SVC_LVL_CD = CSL.CARR_SVC_LVL_CD ");
        sb.append("AND CSL.EZCANCELFLAG     = '0' ");
        // 2018/12/11 S21_NA#29315 Add Start
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("     AND RELN.GLBL_CMPY_CD    = SSL.GLBL_CMPY_CD");
            sb.append("     AND RELN.SHPG_SVC_LVL_CD = SSL.SHPG_SVC_LVL_CD");
            sb.append("     AND RELN.CARR_SVC_LVL_CD = SSL.CARR_SVC_LVL_CD");
            sb.append("     AND SSL.EZCANCELFLAG    = '0'");
            sb.append("     AND SSL.CARR_CD = DACV.VND_CD");
        }
        // 2018/12/11 S21_NA#29315 Add End

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_1);
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Carr Svc Lvl Nm";
        whereArray[IDX_1] = "CARR_SVC_LVL_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.carrSvcLvlDescTxt)) {
            whereArray[IDX_2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_2);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Carr Svc Lvl Cd";
        columnArray0[IDX_1] = "CARR_SVC_LVL_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Carr Svc Lvl Nm";
        columnArray1[IDX_1] = "CARR_SVC_LVL_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_1);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CARR_SVC_LVL_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NWAL1840BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Order Category Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOC.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,DOC.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,DOC.DS_ORD_CATG_CD       AS DS_ORD_CATG_CD");
        sb.append("   ,DOC.DS_ORD_CATG_DESC_TXT AS DS_ORD_CATG_DESC_TXT");
        sb.append("   ,DOC.DS_ORD_CATG_SORT_NUM AS DS_ORD_CATG_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_CATG DOC ");
        sb.append("WHERE");
        sb.append("    DOC.DS_ORD_CATG_CD IN (");
        sb.append("        SELECT");
        sb.append("            DOT.DS_ORD_CATG_CD AS DS_ORD_CATG_CD");
        sb.append("        FROM");
        sb.append("            AVAL_DS_ORD_TP_APP_ID AD");
        sb.append("           ,DS_ORD_TP             DOT");
        sb.append("           ,DS_ORD_TP_PROC_DFN    DOTPD"); // S21_NA#8544 Add
        sb.append("        WHERE");
        sb.append("            AD.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("            AND AD.BIZ_APP_ID    = '").append(BIZ_ID).append("' ");
        sb.append("            AND AD.EZCANCELFLAG  = '0'");
        sb.append("            AND AD.GLBL_CMPY_CD  = DOT.GLBL_CMPY_CD");
        sb.append("            AND AD.DS_ORD_TP_CD  = DOT.DS_ORD_TP_CD");
        sb.append("            AND DOT.EZCANCELFLAG = '0'");
        // S21_NA#8544 Add Start
        sb.append("            AND DOT.GLBL_CMPY_CD = DOTPD.GLBL_CMPY_CD");
        sb.append("            AND DOT.DS_ORD_TP_CD = DOTPD.DS_ORD_TP_CD");
        sb.append("            AND DOTPD.ACTV_FLG   = 'Y'");
        sb.append("            AND DOTPD.EZCANCELFLAG = '0'");
        // S21_NA#8544 Add End
        sb.append("        GROUP BY");
        sb.append("            DOT.DS_ORD_CATG_CD )");
        sb.append("    AND DOC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_1);
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Category Name";
        whereArray[IDX_1] = "DS_ORD_CATG_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_2);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Category Code";
        columnArray0[IDX_1] = "DS_ORD_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Category Name";
        columnArray1[IDX_1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_1);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NWAL1840BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sales Rep Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM"); // S21_NA#7861 Mod 
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO "); // S21_NA#7861 Add
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        // S21_NA#7861 Add Start
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        sb.append("    AND SO.EZCANCELFLAG       = '0'");
        sb.append("    AND SO.RGTN_STS_CD        <> '").append(RGTN_STS.TERMINATED).append("'"); // QC#14604 2016/10/24 Add
        // S21_NA#7861 Add End
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_2);
        Object[] whereArray0 = new Object[IDX_5];
        // S21_NA#7861 Mod Start
        // whereArray0[IDX_0] = "Employee ID";
        // whereArray0[IDX_1] = "PSN_CD";
        // whereArray0[IDX_2] = scrnMsg.slsRepPsnCd.getValue();
        // whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        // whereList.add(whereArray0);
        whereArray0[IDX_0] = "Resource Number";
        whereArray0[IDX_1] = "PSN_NUM";

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.psnNum.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.psnNum) || ZYPCommonFunc.hasValue(scrnMsg.slsRepTocNm)) {
            whereArray0[IDX_2] = scrnMsg.psnNum.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add Start
        whereArray0[IDX_4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28425 Add End
        whereList.add(whereArray0);
        // S21_NA#7861 Mod End

        Object[] whereArray1 = new Object[IDX_5];
        whereArray1[IDX_0] = "Name";
        whereArray1[IDX_1] = "TOC_NM";
        whereArray1[IDX_2] = scrnMsg.slsRepTocNm.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add Start
        whereArray1[IDX_4] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add End
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_6);
        // S21_NA#7861 Mod Start
        // Object[] columnArray0 = new Object[IDX_4];
        // columnArray0[IDX_0] = "Employee ID";
        // columnArray0[IDX_1] = "PSN_CD";
        // columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        // columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        // columnList.add(columnArray0);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Resource Number";
        columnArray0[IDX_1] = "PSN_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_12);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);
        // S21_NA#7861 Mod End

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Name";
        columnArray1[IDX_1] = "TOC_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Line of Bussiness";
        columnArray2[IDX_1] = "LINE_BIZ_TP_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Branch";
        columnArray3[IDX_1] = "COA_BR_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role";
        columnArray4[IDX_1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "TOC_CD";
        columnArray5[IDX_1] = "TOC_CD";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_0);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_1);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM"; // S21_NA#7861 Mod PSN_CD-> PSN_NUM
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Freight Terms
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1130 For Freight Terms
     */
    public static Object[] getParamNWAL1130ForFrtTerm(NWAL1840BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Freight Terms Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("     FC.FRT_COND_CD");
        sb.append("    ,FC.FRT_COND_DESC_TXT");
        sb.append("    ,FC.EZCANCELFLAG");
        sb.append("    ,FC.GLBL_CMPY_CD ");
        sb.append("FROM");
        sb.append("     FRT_COND              FC");
        sb.append("    ,FRT_COND_SVC_LVL_RELN FCSLR ");
        sb.append("WHERE");
        sb.append("    FC.GLBL_CMPY_CD          = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND FC.EZCANCELFLAG      = 0");
        sb.append("    AND FC.GLBL_CMPY_CD      = FCSLR.GLBL_CMPY_CD");
        sb.append("    AND FC.FRT_COND_CD       = FCSLR.FRT_COND_CD ");
        if (ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd)) {
            sb.append("AND FCSLR.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("'");
        }
        sb.append("    AND FCSLR.EZCANCELFLAG   = 0");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Freight Terms Name";
        whereArray0[IDX_1] = "FRT_COND_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.frtCondDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
            whereArray0[IDX_2] = scrnMsg.frtCondDescTxt.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Freight Terms Code";
        columnArray0[IDX_1] = "FRT_COND_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Freight Terms Name";
        columnArray1[IDX_1] = "FRT_COND_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "FRT_COND_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Substitute Item
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1130 For Substitute Item
     */
    public static Object[] getParamNWAL1130ForSbstItem(NWAL1840BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        String mdseCd = scrnMsg.A.no(selectIndex).mdseCd_A.getValue();
        String sbstMdseCd = scrnMsg.A.no(selectIndex).sbstMdseCd_A.getValue();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sup/Relation Item Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    V.GLBL_CMPY_CD");
        sb.append("   ,V.EZCANCELFLAG");
        sb.append("   ,V.MDSE_CD");
        // Mod Start 2016/09/14 QC#11614
//        sb.append("   ,V.MDSE_NM ");
        sb.append("   ,V.MDSE_DESC_SHORT_TXT ");
        // Mod End 2016/09/14 QC#11614
        sb.append("FROM");
        sb.append("    ALL_MDSE_V V ");
        sb.append("WHERE");
        sb.append("    V.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND V.RGTN_STS_CD = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        sb.append("    AND V.MDSE_CD IN (");
        sb.append("        SELECT");
        sb.append("            SUPD.SUPD_TO_MDSE_CD");
        sb.append("        FROM");
        sb.append("            SUPD_RELN SUPD");
        sb.append("        WHERE");
        sb.append("            SUPD.GLBL_CMPY_CD          = V.GLBL_CMPY_CD");
        sb.append("            AND SUPD.SUPD_FROM_MDSE_CD = '").append(mdseCd).append("'");
        sb.append("            AND SUPD.EZCANCELFLAG      = '0'");
        sb.append("        UNION");
        sb.append("        SELECT");
        sb.append("            SUPD.SUPD_TO_MDSE_CD");
        sb.append("        FROM");
        sb.append("            ORD_TAKE_MDSE OTM");
        sb.append("           ,SUPD_RELN SUPD");
        sb.append("        WHERE");
        sb.append("            OTM.GLBL_CMPY_CD         = V.GLBL_CMPY_CD");
        sb.append("            AND OTM.ORD_TAKE_MDSE_CD = '").append(mdseCd).append("'");
        sb.append("            AND OTM.EZCANCELFLAG     = '0'");
        sb.append("            AND OTM.GLBL_CMPY_CD     = SUPD.GLBL_CMPY_CD");
        sb.append("            AND OTM.MDSE_CD          = SUPD.SUPD_FROM_MDSE_CD");
        sb.append("            AND SUPD.EZCANCELFLAG    = '0'");
        sb.append("        UNION");
        sb.append("        SELECT");
        sb.append("            NVL(OTM.ORD_TAKE_MDSE_CD, FLIP.RELN_MDSE_CD)");
        sb.append("        FROM");
        sb.append("            MDSE_ITEM_FLIP_SET FLIP ,");
        sb.append("            ORD_TAKE_MDSE OTM");
        sb.append("        WHERE");
        sb.append("            FLIP.GLBL_CMPY_CD     = V.GLBL_CMPY_CD");
        sb.append("            AND FLIP.MDSE_CD      = '").append(mdseCd).append("'");
        sb.append("            AND FLIP.EZCANCELFLAG = '0'");
        sb.append("            AND OTM.GLBL_CMPY_CD(+)     = FLIP.GLBL_CMPY_CD");
        sb.append("            AND OTM.ORD_TAKE_MDSE_CD(+) = SUBSTR(FLIP.RELN_MDSE_CD, 1, 8)");
        sb.append("            AND OTM.EZCANCELFLAG(+)     = '0'");
        sb.append("        UNION");
        sb.append("        SELECT");
        sb.append("            OTM.ORD_TAKE_MDSE_CD");
        sb.append("        FROM");
        sb.append("            MDSE_ITEM_FLIP_SET FLIP");
        sb.append("           ,ORD_TAKE_MDSE OTM");
        sb.append("        WHERE");
        sb.append("            FLIP.GLBL_CMPY_CD        = V.GLBL_CMPY_CD");
        sb.append("            AND FLIP.MDSE_CD LIKE SUBSTR('").append(mdseCd).append("', 1, 8) || '%'");
        sb.append("            AND FLIP.EZCANCELFLAG    = '0'");
        sb.append("            AND OTM.GLBL_CMPY_CD     = FLIP.GLBL_CMPY_CD");
        sb.append("            AND OTM.ORD_TAKE_MDSE_CD = SUBSTR(FLIP.RELN_MDSE_CD, 1, 8)");
        sb.append("            AND OTM.EZCANCELFLAG     = '0')");
        sb.append("    AND V.EZCANCELFLAG    = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>(IDX_2);
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Sup/Relation Item Cd";
        whereArray0[IDX_1] = "MDSE_CD";

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = sbstMdseCd;
        if (ZYPCommonFunc.hasValue(sbstMdseCd)) {
            whereArray0[IDX_2] = sbstMdseCd;
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Sup/Relation Item Nm";
        // Mod Start 2016/09/14 QC#11614
//        whereArray1[IDX_1] = "MDSE_NM";
        whereArray1[IDX_1] = "MDSE_DESC_SHORT_TXT";
        // Mod End 2016/09/14 QC#11614
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>(IDX_2);
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Sup/Relation Item Cd";
        columnArray0[IDX_1] = "MDSE_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Sup/Relation Item Nm";
        // Mod Start 2016/09/14 QC#11614
//        columnArray1[IDX_1] = "MDSE_NM";
        columnArray1[IDX_1] = "MDSE_DESC_SHORT_TXT";
        // Mod StEndart 2016/09/14 QC#11614
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>(IDX_1);
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "MDSE_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Association Program
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1130 For Association Program
     */
    public static Object[] getParamNWAL1130ForAssnProgram(NWAL1840BMsg scrnMsg) {

        String dateFormat = ZYPDateUtil.getDateFormatString(true);

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Association Program Search";

        StringBuilder sb = new StringBuilder();
        // S21_NA#8412
//        sb.append("SELECT ");
//        sb.append("     PC.GLBL_CMPY_CD");
//        sb.append("    ,PC.EZCANCELFLAG");
//        sb.append("    ,PC.PRC_CONTR_NUM");
//        sb.append("    ,PC.PRC_CONTR_NM ");
//        sb.append("    ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
//        sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
//        sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
//        sb.append("FROM");
//        sb.append("    PRC_CONTR PC ");
//        sb.append("WHERE");
//        sb.append("    PC.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append("    AND PC.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("'");
//        sb.append("    AND PC.EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
//        sb.append("    AND (PC.EFF_THRU_DT  >= '").append(scrnMsg.slsDt.getValue()).append("' OR PC.EFF_THRU_DT IS NULL)");
//        sb.append("    AND PC.EZCANCELFLAG   = 0");

        sb.append("SELECT ");
        sb.append("    DISTINCT ");
        sb.append("    CPCV.GLBL_CMPY_CD");
        sb.append("    ,CPCV.EZCANCELFLAG");
        sb.append("    ,CPCV.PRC_CONTR_NUM");
        sb.append("    ,CPCV.PRC_CONTR_NM");
        sb.append("    ,DECODE(CPCV.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
        sb.append("    ,TO_CHAR(TO_DATE(CPCV.PRC_CONTR_EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
        sb.append("    ,TO_CHAR(TO_DATE(CPCV.PRC_CONTR_EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
        sb.append("FROM ");
        sb.append("    CUST_PRC_CONTR_V CPCV ");
        sb.append("WHERE ");
        sb.append("    CPCV.EZCANCELFLAG   = '0'");
        sb.append("    AND CPCV.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND CPCV.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("'");
        sb.append("    AND CPCV.DS_ACCT_NUM = '").append(scrnMsg.sellToCustCd.getValue()).append("'");
        sb.append("    AND CPCV.PRC_CONTR_EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND NVL(CPCV.PRC_CONTR_EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND CPCV.DS_PRC_CATG_EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND NVL(CPCV.DS_PRC_CATG_EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND ");
        sb.append("    (");
        sb.append("        CPCV.CSMP_NUM IS NULL");
        sb.append("        OR");
        sb.append("        (");
        sb.append("            CPCV.CSMP_NUM IS NOT NULL");
        sb.append("            AND CPCV.CSMP_CONTR_EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("            AND NVL(CPCV.CSMP_CONTR_EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("        )");
        sb.append("    )");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Price Contract #";
        whereArray0[IDX_1] = "PRC_CONTR_NUM";

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.prcContrNum.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNum)) {
            whereArray0[IDX_2] = scrnMsg.prcContrNum.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Price Contract #";
        columnArray0[IDX_1] = "PRC_CONTR_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_33);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Price Contract Name";
        columnArray1[IDX_1] = "PRC_CONTR_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_33);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Status";
        columnArray2[IDX_1] = "ACTV_FLG";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_8);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Eff From Date";
        columnArray3[IDX_1] = "EFF_FROM_DT";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_9);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Eff Thru Date";
        columnArray4[IDX_1] = "EFF_THRU_DT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_9);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PRC_CONTR_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRC_CONTR_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1600
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1600
     */
    public static Object[] getParamNWAL1600(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        EZDBMsgArray slsCrList = null;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NWAL1600Constant.MODE_EDIT_ALL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.schdAgmtNum.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, "U");
        getSlsCreditList(scrnMsg);
        slsCrList = scrnMsg.U;

        Object[] params = new Object[IDX_6];
        params[IDX_0] = scrnMsg.xxPopPrm_00;
        params[IDX_1] = scrnMsg.xxPopPrm_01;
        params[IDX_2] = scrnMsg.xxPopPrm_02; // no use
        params[IDX_3] = scrnMsg.xxPopPrm_03;
        params[IDX_4] = slsCrList;
        params[IDX_5] = scrnMsg.xxPopPrm_04;
        return params;
    }

    /**
     * Get Param NWAL1660
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1660
     */
    public static Object[] getParamNWAL1660(NWAL1840BMsg scrnMsg) {

        List<Object> paramList = new ArrayList<Object>(IDX_80);
        paramList.add(scrnMsg.xxModeCd_N); // 0
        paramList.add(scrnMsg.xxModeCd_PM);  //1 QC#22965 add
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxViewChngLogSrcCd_N, "3"); // 2 QC#9700 add
        paramList.add(scrnMsg.xxViewChngLogSrcCd_N); // 2 QC#9700 add
        paramList.add(scrnMsg.trxHdrNum_N); // 3
        paramList.add(scrnMsg.prcBaseDt_N); // 4
        paramList.add(scrnMsg.prcCalcDt_N); // 5
        paramList.add(scrnMsg.dsOrdTpCd_N); // 6
        paramList.add(scrnMsg.dsOrdCatgCd_N); // 7
        paramList.add(scrnMsg.lineBizTpCd_N); // 8
        paramList.add(scrnMsg.cpoSrcTpCd_N); // 9
        paramList.add(scrnMsg.custIssPoNum_N); // 10
        paramList.add(scrnMsg.dsPmtMethCd_N); // 11
        paramList.add(scrnMsg.spclHdlgTpCd_N); // 12
        paramList.add(scrnMsg.leasePrchOptCd_N); // 13
        paramList.add(scrnMsg.dsOrdPosnNum_N); // 14
        paramList.add(scrnMsg.trxLineNum_N); // 15
        paramList.add(scrnMsg.trxLineSubNum_N); // 16
        paramList.add(scrnMsg.configCatgCd_N); // 17
        paramList.add(scrnMsg.inEachQty_N); // 18
        paramList.add(scrnMsg.shipToFirstLineAddr_N); // 19
        paramList.add(scrnMsg.shipToScdLineAddr_N); // 20
        paramList.add(scrnMsg.shipToCtyAddr_N); // 21
        paramList.add(scrnMsg.shipToStCd_N); // 22
        paramList.add(scrnMsg.shipToCntyNm_N); // 23
        paramList.add(scrnMsg.shipToPostCd_N); // 24
        paramList.add(scrnMsg.shipToCtryCd_N); // 25
        paramList.add(scrnMsg.prcCatgCd_N); // 26
        paramList.add(scrnMsg.csmpNum_N); // 27
        paramList.add(scrnMsg.dlrRefNum_N); // 28
        paramList.add(scrnMsg.prcContrNum_N); // 29
        paramList.add(scrnMsg.coaBrCd_N); // 30
        paramList.add(scrnMsg.mdseCd_N); // 31
        paramList.add(scrnMsg.billToCustLocCd_N); // 32
        paramList.add(scrnMsg.billToCustAcctCd_N); // 33
        paramList.add(scrnMsg.sellToCustCd_N); // 34
        paramList.add(scrnMsg.soldToCustLocCd_N); // 35
        paramList.add(scrnMsg.shipToCustLocCd_N); // 36
        paramList.add(scrnMsg.shipToCustAcctCd_N); // 37
        paramList.add(scrnMsg.frtCondCd_N); // 38
        paramList.add(scrnMsg.shpgSvcLvlCd_N); // 39
        paramList.add(scrnMsg.ccyCd_N); // 40
        paramList.add(scrnMsg.uomCd_N); // 41
        paramList.add(scrnMsg.ordCustUomQty_N); // 42
        paramList.add(scrnMsg.dsCpoLineCatgCd_N); // 43
        paramList.add(scrnMsg.invQty_N); // 44
        paramList.add(scrnMsg.mdlId_N); // 45
        paramList.add(scrnMsg.rtrnRsnCd_N); // 46
        paramList.add(scrnMsg.coaExtnCd_N); // 47
        paramList.add(scrnMsg.slsRepOrSlsTeamTocCd_N); // 48
        paramList.add(scrnMsg.rtlWhCd_N); // 49
        paramList.add(scrnMsg.xxTotBaseAmt_N); // 50
        paramList.add(scrnMsg.xxSubTotCalcPrcAmt_N); // 5`
        paramList.add(scrnMsg.xxTotChrgPrcAmt_N); // 51
        paramList.add(scrnMsg.xxTotDiscAmt_N); // 52
        paramList.add(scrnMsg.xxTotSpclPrcAmt_N); // 53
        paramList.add(scrnMsg.xxTotNetDiscAmt_N); // 54
        paramList.add(scrnMsg.xxUnitNetPrcAmt_N); // 55
        paramList.add(scrnMsg.xxUnitGrsPrcAmt_N); // 56
        paramList.add(scrnMsg.xxTotNetPrcAmt_N); // 57
        paramList.add(scrnMsg.xxGrsAmt_N); // 58
        paramList.add(scrnMsg.xxUnitFrtAmt_N); // 60
        paramList.add(scrnMsg.xxTotFrtAmt_N); // 61
        paramList.add(scrnMsg.xxUnitSpclChrgAmt_N); // 62
        paramList.add(scrnMsg.xxTotSpclChrgAmt_N); // 63
        paramList.add(scrnMsg.xxTotFrtSubAmt_N); // 64
        paramList.add(scrnMsg.xxUnitRestkFeeAmt_N); // 65
        paramList.add(scrnMsg.xxTotRestkFeeAmt_N); // 66
        paramList.add(scrnMsg.xxTotNetPrcAmt_N2); // 67
        paramList.add(scrnMsg.xxTotTaxAmt_N); // 68
        paramList.add(scrnMsg.xxUnitPrc_N); // 69
        paramList.add(scrnMsg.xxTotAmt_N); // 70
        paramList.add(scrnMsg.dealPrcListPrcAmt_N); // 71
        paramList.add(scrnMsg.dsCpoLineNum_N); // 72
        paramList.add(scrnMsg.dsCpoLineSubNum_N); // 73
        paramList.add(scrnMsg.dealSvcRevTrnsfAmt_N); // 74
        paramList.add(scrnMsg.dealSvcCostTrnsfAmt_N); // 75
        paramList.add(scrnMsg.dealManFlAdjAmt_N); // 76
        paramList.add(scrnMsg.dealManRepRevAdjAmt_N); // 77
        paramList.add(scrnMsg.xxTotUnitNetWt_N);  // 78 QC#22965 add
        scrnMsg.xxSfxKeyTxt_N.setValue("NL");
        paramList.add(scrnMsg.xxSfxKeyTxt_N); // 79
        paramList.add(scrnMsg.N); // 80
        // 2018/05/13 S21_NA#25251 Add Start
        // 2018/05/13 S21_NA#25251 Add Start
        String prcChangeAuth = ZYPConstant.FLG_OFF_N;
        if (hasPriceChangeAuth(scrnMsg)) {
            prcChangeAuth = ZYPConstant.FLG_ON_Y;
        }
        paramList.add(prcChangeAuth); // 81
        // 2018/05/13 S21_NA#25251 Add End
        // 2018/05/13 S21_NA#25251 Add End

        return paramList.toArray(new Object[paramList.size()]);
    }

    /**
     * Get Param NWAL1760
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1760
     */
    public static Object[] getParamNWAL1760(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, PRC_CTX_TP.SALES_PRICING);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_13);
        paramList.add(scrnMsg.slsDt);
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.dsOrdCatgCd);
        paramList.add(scrnMsg.dsOrdTpCd);
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.xxPopPrm_01);
        paramList.add(scrnMsg.xxPopPrm_02);
        paramList.add(scrnMsg.prcContrNum);
        paramList.add(scrnMsg.coaBrCd);
        paramList.add(scrnMsg.xxPopPrm_03);
        paramList.add(scrnMsg.xxPopPrm_04);
        paramList.add(scrnMsg.xxPopPrm_05);
        paramList.add(scrnMsg.xxPopPrm_06);

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NWAL1810
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1810
     */
    public static Object[] getParamNWAL1810(NWAL1840BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.schdAgmtNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NWAL1810Constant.SOURCE_ID_SCHEDULE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NWAL1810Constant.LVL_CD_ALL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, NWAL1810Constant.DEF_CD_DETAIL);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_5);
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.xxPopPrm_01);
        paramList.add(scrnMsg.xxPopPrm_02);
        paramList.add(scrnMsg.xxPopPrm_03);
        paramList.add(scrnMsg.xxPopPrm_04); // no use

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    /**
     * Get Param NWAL1860
     * @param scrnMsg NWAL1840BMsg
     * @return Param NWAL1860
     */
    public static Object[] getParamNWAL1860(NWAL1840BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.R);
        scrnMsg.ordQty_S.clear();
        scrnMsg.shpgIntvlCd_S.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtVldFromDt_S, scrnMsg.schdAgmtVldFromDt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtVldThruDt_S, scrnMsg.schdAgmtVldThruDt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rddDt_S, getMaxRddDt(scrnMsg));

        List<EZDBBigDecimalItem> lineNumList = getLineNumList(scrnMsg);

        List<Object> paramList = new ArrayList<Object>(IDX_8);
        paramList.add(scrnMsg.R);
        paramList.add(scrnMsg.ordQty_S);
        paramList.add(scrnMsg.shpgIntvlCd_S);
        paramList.add(scrnMsg.schdAgmtVldFromDt_S);
        paramList.add(scrnMsg.schdAgmtVldThruDt_S);
        paramList.add(lineNumList);
        paramList.add(scrnMsg.rddDt_S);
        paramList.add("R");

        return paramList.toArray(new Object[paramList.size()]);
    }

    /**
     * get param by outPut param
     * @param scrnMsg NWAL1840BMsg
     */
    public static void getOutputParamNSAL1240(NWAL1840BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.mdlId, scrnMsg.mdlId_QO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm, scrnMsg.mdlNm_QO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, scrnMsg.contrNum_QO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk, scrnMsg.svcConfigMstrPk_QO);
        if (scrnMsg.Q.getValidCount() != 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.Q.no(0).serNum_O);
        }
    }

    // S21_NA#15875 Add Start
    /**
     * get param by outPut param
     * @param scrnMsg NWAL1840BMsg
     */
    public static void getOutputParamNSAL0110(NWAL1840BMsg scrnMsg) {

        // 2017/06/13 QC#18667 Mod Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.dsContrPk)) {
//            BigDecimal dsContrPk = scrnMsg.dsContrPk.getValue();
//            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, dsContrPk.toString());
//        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPopPrm_00)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, scrnMsg.xxPopPrm_00);
        }
        // 2017/06/13 QC#18667 Mod End
    }
    // S21_NA#15875 Add End
    
    /**
     * Get Param ZYPL0310
     * @param scrnMsg NWAL1840BMsg
     * @return Param ZYPL0310
     */
    public static Object[] getParamZYPL0310(NWAL1840BMsg scrnMsg) {

        String dispMode = ZYPL0310Constant.DISPLAY_MODE_MODIFICATION;
        // Only Reference Authority
        if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(scrnMsg.Z.no(0).xxFuncId.getValue())) {
            dispMode = ZYPL0310Constant.DISPLAY_MODE_READ_ONLY;
        }

        List<Object> paramList = new ArrayList<Object>(IDX_9);
        paramList.add(dispMode);
        paramList.add(BIZ_ID);
        paramList.add(scrnMsg.schdAgmtNum.getValue());
        paramList.add(MSG_PARAM_BIZ_NM);
        paramList.add(MSG_PARAM_SCHD_NUM);
        paramList.add(CODE_TABLE_SCHD_ATT_DOC_TP); // S21_NA#7659 Mod
        paramList.add(KEY_NWAL1840_ATT_LIMIT);
        paramList.add(KEY_NWAL1840_AUTHORIZE_FILE_EXT);
        paramList.add(KEY_NWAL1840_AUTHORIZE_FILE_VOL);

        return paramList.toArray(new Object[paramList.size()]);
    }

    /**
     * get selected line Number List
     * @param scrnMsg
     * @return List<EZDBBigDecimalItem>
     */
    private static List<EZDBBigDecimalItem> getLineNumList(NWAL1840BMsg scrnMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        List<EZDBBigDecimalItem> lineNumList = new ArrayList<EZDBBigDecimalItem>(selectedRows.size());

        for (int selectRowNum : selectedRows) {
            lineNumList.add(scrnMsg.A.no(selectRowNum).schdAgmtLineNum_A);
        }
        return lineNumList;
    }

    /**
     * get Max rddDt
     * @param scrnMsg NWAL1840BMsg
     * @return String
     */
    private static String getMaxRddDt(NWAL1840BMsg scrnMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        String maxRddDt = "";
        for (Integer selectLineNum : selectedRows) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                int lineNumB = scrnMsg.B.no(i).schdAgmtLineNum_B.getValueInt();

                if (lineNumB == scrnMsg.A.no(selectLineNum.intValue()).schdAgmtLineNum_A.getValueInt()) {
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).schdAgmtPlnCancFlg_B.getValue())) {

                        String rddDtB = scrnMsg.B.no(i).rddDt_B.getValue();
                        if (rddDtB.compareTo(maxRddDt) > 0) {
                            maxRddDt = rddDtB;
                        }
                    }
                }

            }
        }
        return maxRddDt;
    }

    /**
     * Get Bill To Account Number For Popup Parameter
     * @param scrnMsg NWAL1840BMsg
     * @return Bill To Account Number For Popup Parameter
     */
    private static String getBillToAcctNum(NWAL1840BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd)) {
            return scrnMsg.billToCustAcctCd.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return scrnMsg.shipToCustAcctCd.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return scrnMsg.sellToCustCd.getValue();
        }

        return null;
    }

    /**
     * Get Ship To Account Number For Popup Parameter
     * @param scrnMsg NWAL1840BMsg
     * @return Ship To Account Number For Popup Parameter
     */
    private static String getShipToAcctNum(NWAL1840BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return scrnMsg.shipToCustAcctCd.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return scrnMsg.sellToCustCd.getValue();
        }

        return null;
    }

    /**
     * Get Sold To Account Number For Popup Parameter
     * @param scrnMsg NWAL1840BMsg
     * @return Sold To Account Number For Popup Parameter
     */
    private static String getSoldToAcctNum(NWAL1840BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return scrnMsg.sellToCustCd.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return scrnMsg.shipToCustAcctCd.getValue();
        }

        return null;
    }

    /**
     * Clear Popup Parameters
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1840BMsg scrnMsg) {

        scrnMsg.xxPopPrm_00.clear();
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_0A.clear();
     // 2018/07/26 QC#14307 Add Start
        scrnMsg.xxPopPrm_0B.clear();
     // 2018/07/26 QC#14307 Add Start
     // 2018/07/26 QC#26661,26713 Add Start
        scrnMsg.xxPopPrm_0C.clear();
        scrnMsg.xxPopPrm_0D.clear();
     // 2018/07/26 QC#26661,26713 Add Start
        // START 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
        scrnMsg.xxPopPrm_0E.clear();
        scrnMsg.xxPopPrm_0F.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        // END 2023/10/10 T.Fukuta [CSA-QC#61730,ADD]
    }

    /**
     * Get Sales Credit List
     * @param scrnMsg NWAL1840BMsg
     */
    public static void getSlsCreditList(NWAL1840BMsg scrnMsg) {

        String slsRepTocCd = scrnMsg.slsRepTocCd.getValue();
        ZYPTableUtil.clear(scrnMsg.U);

        if (ZYPCommonFunc.hasValue(slsRepTocCd)) {
            boolean chkAllDelete = true;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NWAL1840_CBMsg slsCreditMsg = scrnMsg.C.no(i);
                String xxRqstTpCd = slsCreditMsg.xxRqstTpCd_C.getValue();
                BigDecimal splyQuoteSlsCrPk = slsCreditMsg.schdAgmtSlsCrPk_C.getValue();

                if (!ZYPCommonFunc.hasValue(xxRqstTpCd) && !ZYPCommonFunc.hasValue(splyQuoteSlsCrPk)) {
                    xxRqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
                } else if (!ZYPCommonFunc.hasValue(xxRqstTpCd) && ZYPCommonFunc.hasValue(splyQuoteSlsCrPk)) {
                    xxRqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
                }

                if (!NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                    chkAllDelete = false;
                    break;
                }
            }

            if (scrnMsg.C.getValidCount() > 0 && !chkAllDelete) {
                for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                    NWAL1840_CBMsg slsRepMsg = scrnMsg.C.no(i);
                    String lineBizRoleTpCd = slsRepMsg.slsRepRoleTpCd_C.getValue();

                    if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd)) {
                        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_C, slsRepTocCd);
                        String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, LINE_BIZ_ROLE_TP.ESS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_C, LINE_BIZ_ROLE_TP.ESS_WRITER);
                        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, LINE_BIZ_ROLE_TP.PPS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_C, LINE_BIZ_ROLE_TP.PPS_WRITER);
                        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, LINE_BIZ_ROLE_TP.LFS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_C, LINE_BIZ_ROLE_TP.LFS_WRITER);
                        }
                        break;
                    }
                }
            } else {
                NWAL1840_UBMsg paramSlsCreditMsg = scrnMsg.U.no(0);
                ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.slsRepTocCd_U, slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.slsCrQuotFlg_U, ZYPConstant.FLG_ON_Y);
                paramSlsCreditMsg.slsRepCrPct_U.setValue(BigDecimal.valueOf(IDX_100));

                String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.lineBizRoleTpCd_U, LINE_BIZ_ROLE_TP.ESS_WRITER);
                } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.lineBizRoleTpCd_U, LINE_BIZ_ROLE_TP.PPS_WRITER);
                } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.lineBizRoleTpCd_U, LINE_BIZ_ROLE_TP.LFS_WRITER);
                }
                scrnMsg.U.setValidCount(1);
            }
        }

        if (scrnMsg.U.getValidCount() == 0) {
            int paramSlsCrCnt = 0;
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NWAL1840_CBMsg slsCreditMsg = scrnMsg.C.no(i);
                String xxRqstTpCd = slsCreditMsg.xxRqstTpCd_C.getValue();
                if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                    continue;
                }

                NWAL1840_UBMsg paramSlsCreditMsg = scrnMsg.U.no(paramSlsCrCnt);
                EZDMsg.copy(slsCreditMsg, "C", paramSlsCreditMsg, "U");
                ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.dsCpoSlsCrPk_U, slsCreditMsg.schdAgmtSlsCrPk_C);
                paramSlsCrCnt++;
            }
            scrnMsg.U.setValidCount(paramSlsCrCnt);
        }

        return;
    }

    /**
     * Check Item All Fields
     * @param scrnMsg NWAL1840BMsg
     */
    public static void checkItemAllFields(NWAL1840BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.mdlNm);
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.addCheckItem(scrnMsg.configCatgCd_N);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.contrVrsnEffThruDt);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtVldFromDt);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtVldThruDt);
        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscDescTxt);
        scrnMsg.addCheckItem(scrnMsg.cpoSrcTpCd);
        scrnMsg.addCheckItem(scrnMsg.cpoSrcTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.schdAgmtCratDt);
        scrnMsg.addCheckItem(scrnMsg.cpoSrcTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm);
        scrnMsg.addCheckItem(scrnMsg.sellToFirstRefCmntTxt); // 2018/01/12 S21_NA#20164 ADD

        String curDispTabName = scrnMsg.xxDplyTab.getValue();
        scrnMsg.xxDplyTab.clear();
        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();

        // Customer / Contact Tab
        itemList.add(scrnMsg.billToCustAcctCd);
        itemList.add(scrnMsg.billToCustLocCd);
        itemList.add(scrnMsg.billToCustAcctNm);
        itemList.add(scrnMsg.shipToCustAcctCd);
        itemList.add(scrnMsg.shipToCustLocCd);
        itemList.add(scrnMsg.shipToCustAcctNm);
        itemList.add(scrnMsg.sellToCustCd);
        itemList.add(scrnMsg.soldToCustLocCd);
        itemList.add(scrnMsg.soldToCustAcctNm);
        itemList.add(scrnMsg.sellToFirstRefCmntTxt);

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1840_DBMsg ctacLineMsg = scrnMsg.D.no(i);
            itemList.add(ctacLineMsg.ctacPsnFirstNm_D);
            itemList.add(ctacLineMsg.ctacPsnLastNm_D);
            itemList.add(ctacLineMsg.ctacPsnCellPhoNum_D);
            itemList.add(ctacLineMsg.ctacPsnExtnNum_D);
            itemList.add(ctacLineMsg.ctacPsnEmlAddr_D);
            itemList.add(ctacLineMsg.ctacPsnFaxNum_D);
        }

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
                scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                NWAL1840CommonLogic.setTblBackColorForContact(scrnMsg);
            }
        }

        // Header Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            itemList.add(scrnMsg.custIssPoNum);
            itemList.add(scrnMsg.custIssPoDt);
            itemList.add(scrnMsg.slsRepTocNm);
            itemList.add(scrnMsg.psnNum); // S21_NA#7861 Mod slsRepPsnCd -> psnNum
            itemList.add(scrnMsg.frtCondDescTxt);
            itemList.add(scrnMsg.shpgSvcLvlCd);
            itemList.add(scrnMsg.carrSvcLvlDescTxt);
            itemList.add(scrnMsg.carrAcctNum);
            itemList.add(scrnMsg.pmtTermCashDiscDescTxt);

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_HEADER);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                }
            }

            // 2017/11/20 S21_NA#22388 Add Start
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
                for (EZDBItem item : itemList) {
                    scrnMsg.addCheckItem(item);
                    if (isWarn(item)) {
                        scrnMsg.xxDplyTab.setValue(TAB_HEADER);
                        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                    }
                }
            }
            // 2017/11/20 S21_NA#22388 Add End
        }

        // Items Tab
        Set<EZDBItem> itemSchdList; // Add 2016/09/16 S21_NA#13040
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                itemSchdList = new LinkedHashSet<EZDBItem>(); // Add 2016/09/16 S21_NA#13040

                NWAL1840_ABMsg itemLineMsg = scrnMsg.A.no(i);
                itemList.add(itemLineMsg.xxChkBox_A);
                itemList.add(itemLineMsg.mdseCd_A);
                itemList.add(itemLineMsg.pkgUomDescTxt_A);
                itemList.add(itemLineMsg.sbstMdseCd_A);
                itemList.add(itemLineMsg.schdAllwQty_A);
                itemList.add(itemLineMsg.dealNetUnitPrcAmt_A);
                itemList.add(itemLineMsg.dealPrcListPrcAmt_A);
                itemList.add(itemLineMsg.xxTotDiscAmt_A);
                itemList.add(itemLineMsg.schdAgmtLineDealFrtAmt_A);
                itemList.add(itemLineMsg.schdAgmtLineDealNetAmt_A);
                itemList.add(itemLineMsg.schdAgmtLineDealTaxAmt_A);
                itemList.add(itemLineMsg.xxTotAmt_A);
                // START 2022/06/01 [QC#59973, ADD]
                itemList.add(itemLineMsg.shpgIntvlCd_A);
                itemList.add(itemLineMsg.otmShipQty_A);
                // END   2022/06/01 [QC#59973, ADD]

                // Add Start 2016/09/16 S21_NA#13040
                for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
                    NWAL1840_BBMsg itemSchdLineMsg = scrnMsg.B.no(j);
                    if (itemLineMsg.schdAgmtLineNum_A.getValueInt() != itemSchdLineMsg.schdAgmtLineNum_B.getValueInt()) {
                        continue;
                    }
                    itemSchdList.add(itemSchdLineMsg.xxChkBox_B);
                    itemSchdList.add(itemSchdLineMsg.rddDt_B);
                    itemSchdList.add(itemSchdLineMsg.ordQty_B);
                }

                for (EZDBItem schdItem : itemSchdList) {
                    scrnMsg.addCheckItem(schdItem);
                    if (schdItem.isError()) {
                        itemLineMsg.xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);
                        scrnMsg.xxDplyTab.setValue(TAB_LINES);
                        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                    }
                }
                // Add End 2016/09/16 S21_NA#13040
            }
            // Del Start 2016/09/16 S21_NA#13040
            // for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            // NWAL1840_BBMsg itemLineMsg = scrnMsg.B.no(i);
            // itemList.add(itemLineMsg.xxChkBox_B);
            // itemList.add(itemLineMsg.rddDt_B);
            // itemList.add(itemLineMsg.ordQty_B);
            // }
            // Del End 2016/09/16 S21_NA#13040

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_LINES);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                }
            }
            
        }

        // Commnets Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            itemList.add(scrnMsg.shpgCmntTxt);
            itemList.add(scrnMsg.itrlOrdCmntTxt);
            itemList.add(scrnMsg.invCmntTxt);

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_COMMENTS);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                }
            }
        }

        // Additional Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            itemList.add(scrnMsg.prcContrNum);

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_ADDITIONAL);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                    NWAL1840CommonLogic.setTblBackColorForAddLine(scrnMsg);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, curDispTabName);
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * Check Customer PO Field
     * @param scrnMsg NWAL1840BMsg
     */
    public static void checkCustPoField(NWAL1840BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.custIssPoDt)) {
                scrnMsg.custIssPoDt.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.custIssPoDt.getNameForMessage() });
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.custIssPoDt)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum)) {
                scrnMsg.custIssPoNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.custIssPoNum.getNameForMessage() });
            }
        }

        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.custIssPoDt);
        scrnMsg.putErrorScreen();
    }

    /**
     * Check Contact Field
     * @param scrnMsg NWAL1840BMsg
     */
    public static void checkContactField(NWAL1840BMsg scrnMsg) {

        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1840_DBMsg ctacLineMsg = scrnMsg.D.no(i);
            itemList.add(ctacLineMsg.ctacPsnFirstNm_D);
            itemList.add(ctacLineMsg.ctacPsnLastNm_D);
            itemList.add(ctacLineMsg.ctacPsnTelNum_D);
            itemList.add(ctacLineMsg.ctacPsnExtnNum_D);
            itemList.add(ctacLineMsg.ctacPsnEmlAddr_D);
            itemList.add(ctacLineMsg.ctacPsnFaxNum_D);

            if (!ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnFirstNm_D)) {
                ctacLineMsg.ctacPsnFirstNm_D.setErrorInfo(1, ZZM9000E, new String[] {ctacLineMsg.ctacPsnFirstNm_D.getNameForMessage() });
            }

            if (!ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnLastNm_D)) {
                ctacLineMsg.ctacPsnLastNm_D.setErrorInfo(1, ZZM9000E, new String[] {ctacLineMsg.ctacPsnLastNm_D.getNameForMessage() });
            }

            if (!ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnTelNum_D) && !ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnEmlAddr_D) && !ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnFaxNum_D)) {
                ctacLineMsg.ctacPsnTelNum_D.setErrorInfo(1, NWAM0832E);
                ctacLineMsg.ctacPsnExtnNum_D.setErrorInfo(1, NWAM0832E);  // QC#14143 2016/11/07 Add
                ctacLineMsg.ctacPsnEmlAddr_D.setErrorInfo(1, NWAM0832E);
                ctacLineMsg.ctacPsnFaxNum_D.setErrorInfo(1, NWAM0832E);
            }

            // Mod Start 2016/11/25 M.Ohno S21_NA#15733
//            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnTelNum_D)) {
//                if (!ctacLineMsg.ctacPsnTelNum_D.getValue().matches(CHK_PHONE_PATTERN)) {
//                    ctacLineMsg.ctacPsnTelNum_D.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
//                }
//            }
            checkTelFormat(ctacLineMsg.ctacPsnTelNum_D);

//            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnExtnNum_D)) {
//                if (!ctacLineMsg.ctacPsnExtnNum_D.getValue().matches(CHK_EXT_PATTERN)) {
//                    ctacLineMsg.ctacPsnExtnNum_D.setErrorInfo(1, NWAM0664E, new String[] {EXT_FORMAT });
//                }
//            }
            checkExtFormat(ctacLineMsg.ctacPsnExtnNum_D);
            // Mod End   2016/11/25 M.Ohno S21_NA#15733

            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnEmlAddr_D)) {
                // 2016/10/06 QC#1443 Add Start --------------
                boolean ret = NMXC106001CommonCheckUtil.checkEmailFormat(ctacLineMsg.ctacPsnEmlAddr_D.getValue());

                if (!ret) {
                    ctacLineMsg.ctacPsnEmlAddr_D.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                }
                 // 2016/10/06 QC#1443 Add End --------------

                //if (!ctacLineMsg.ctacPsnEmlAddr_D.getValue().matches(CHK_EMAIL_PATTERN)) {
                //    ctacLineMsg.ctacPsnEmlAddr_D.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                //}
            }

            // Mod Start 2016/11/25 M.Ohno S21_NA#15733
//            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnFaxNum_D)) {
//                if (!ctacLineMsg.ctacPsnFaxNum_D.getValue().matches(CHK_PHONE_PATTERN)) {
//                    ctacLineMsg.ctacPsnFaxNum_D.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
//                }
//            }
            checkTelFormat(ctacLineMsg.ctacPsnFaxNum_D);
            // Mod End   2016/11/25 M.Ohno S21_NA#15733
        }

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
            }
        }

        scrnMsg.putErrorScreen();
    }

    // Add Start 2016/09/16 S21_NA#13040
    /**
     * expand SchdLine By SchdPln
     * @param scrnMsg NWAL1840BMsg
     * @param schdLineNum int
     */
    public static void expandSchdLineBySchdPln(NWAL1840BMsg scrnMsg, int schdLineNum) {
        
        for (int i = 0; i < scrnMsg.A.getValidCount() ; i++) {
            if (schdLineNum == scrnMsg.A.no(i).schdAgmtLineNum_A.getValueInt()) {
                scrnMsg.A.no(i).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);
                break;
            }
        }
    }
    // Add End 2016/09/16 S21_NA#13040

    // Add Start 2016/11/25 M.Ohno S21_NA#15733
    private static void checkTelFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String telNum = item.getValue();
            if (telNum.length() < 7 || telNum.length() > 20) {
                item.setErrorInfo(1, NWAM0763E);
            } else {
                telNum = telNum.replaceAll(SLASH, HYPHEN);
                telNum = telNum.replaceAll(PERIOD, HYPHEN);
                ZYPEZDItemValueSetter.setValue(item, telNum);
            }
        }
    }

    private static void checkExtFormat(EZDBStringItem item) {

        if (ZYPCommonFunc.hasValue(item)) {
            String extNum = item.getValue();
            // Mod Start 2018/12/14 QC#24022
            //if (extNum.length() > 4) {
            //    item.setErrorInfo(1, NWAM0764E);
            if (extNum.length() > EXT_MAX_LENGTH) {
                item.setErrorInfo(1, NWAM0964E, new String[] {item.getNameForMessage(), String.valueOf(EXT_MAX_LENGTH) });
                // Mod End 2018/12/14 QC#24022
            }
        }
    }
    // Add End   2016/11/25 M.Ohno S21_NA#15733

    // QC#16452 add Start
    /**
     * Set Screen Protect By Contact
     * @param scrnMsg NWAL1840BMsg
     */
    public static void setProtectByContact(NWAL1840BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            NWAL1840_DBMsg ctacMsg = scrnMsg.D.no(i);

            if (ZYPCommonFunc.hasValue(ctacMsg.schdAgmtCtacPsnPk_D)) {
                ctacMsg.ctacPsnTpCd_D.setInputProtected(true);
                ctacMsg.ctacPsnFirstNm_D.setInputProtected(true);
                ctacMsg.ctacPsnFirstNm_LK.clear();
                ctacMsg.ctacPsnLastNm_D.setInputProtected(true);
                ctacMsg.ctacCustRefTpCd_D.setInputProtected(true);

            } else if (ZYPCommonFunc.hasValue(ctacMsg.ctacPsnPk_D)) {
                if (ZYPCommonFunc.hasValue(ctacMsg.ctacCustRefTpCd_DP)) {
                    ctacMsg.ctacPsnTpCd_D.setInputProtected(true);
                    ctacMsg.ctacPsnFirstNm_D.setInputProtected(true);
                    ctacMsg.ctacPsnFirstNm_LK.clear();
                    ctacMsg.ctacPsnLastNm_D.setInputProtected(true);
                    ctacMsg.ctacCustRefTpCd_D.setInputProtected(true);
                }
            }
        }
    }
    // QC#16452 add End

    // S21_NA ADD QC#20246
    /**
     * Set Contact to Attention
     * @param scrnMsg NWAL1840BMsg
     * @param int row
     */
    public static void setContactToAttention(NWAL1840BMsg scrnMsg, int row) {
        if (CTAC_TP.ORDER_CONTACT.equals(scrnMsg.D.no(row).ctacPsnTpCd_D.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.sellToFirstRefCmntTxt)) {
                String ctacPsnFirstNm = scrnMsg.D.no(row).ctacPsnFirstNm_D.getValue();
                String ctacPsnLastNm = scrnMsg.D.no(row).ctacPsnLastNm_D.getValue();
                if (ZYPCommonFunc.hasValue(ctacPsnFirstNm)
                        && ZYPCommonFunc.hasValue(ctacPsnLastNm)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.sellToFirstRefCmntTxt, ctacPsnFirstNm + " " + ctacPsnLastNm);
                }
            }
        }
    }

    // 2017/11/20 S21_NA#22388 Add Start
    private static boolean isWarn(EZDBItem item) {
        int errCode = item.getErrorCode();
        if (errCode == 2) {
            return true;
        } else {
            return false;
        }
    }
    // 2017/11/20 S21_NA#22388 Add End

    // 2018/05/13 S21_NA#25251 Add Start
    private static boolean hasPriceChangeAuth(NWAL1840BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (S21StringUtil.isEquals(ALL_RGTN_AUTH, scrnMsg.Z.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    // 2018/07/16 QC#14307 Add Start
    /**
     * Get Param NMAL3300
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL3300
     */
    public static Object[] getParamNMAL3300(NWAL1840BMsg scrnMsg) {

        // 2018/07/26 QC#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_02.getValue();
        // 2018/07/26 QC#26661,26713 Add End
        String trxRuleTp = scrnMsg.xxPopPrm_03.getValue();
        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, BIZ_ID);
        // 2018/07/26 QC#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, funcCatgId);
        // 2018/07/26 QC#26661,26713 Add End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, trxRuleTp);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.xxPopPrm_01);
        paramList.add(scrnMsg.xxPopPrm_02);
        paramList.add(scrnMsg.xxPopPrm_03);
        paramList.add(scrnMsg.xxPopPrm_04);
        paramList.add("V"); // Suffix

        // Customer Special Instruction Line
        int index = 0;
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, scrnMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustLocCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, scrnMsg.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustLocCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, scrnMsg.shipToCustLocCd);
        }
        scrnMsg.V.setValidCount(index);
        paramList.add(scrnMsg.V);

        // 2018/07/26 QC#26661,26713 Add Start
        // Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.lineBizTpCd);
        paramList.add(scrnMsg.xxPopPrm_05);
        // 2018/07/26 QC#2666,267131 Add End

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Param NMAL3300 for On Blur
     * @param scrnMsg NWAL1840BMsg
     * @return Param NMAL3300
     */
    public static Object[] getParamNMAL3300ForOnBlur(NWAL1840BMsg scrnMsg) {

        // 2018/07/26 QC#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_02.getValue();
        // 2018/07/26 QC#26661,26713 Add End
        String trxRuleTp = scrnMsg.xxPopPrm_03.getValue();
        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, BIZ_ID);
        // 2018/07/26 QC#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, funcCatgId);
        // 2018/07/26 QC#26661,26713 Add End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, trxRuleTp);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.xxPopPrm_01);
        paramList.add(scrnMsg.xxPopPrm_02);
        paramList.add(scrnMsg.xxPopPrm_03);
        paramList.add(scrnMsg.xxPopPrm_04);
        paramList.add("V"); // Suffix

        // Customer Special Instruction Line
        int index = 0;

        scrnMsg.V.clear();

        // Sold To
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, scrnMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");
        }

        // Bill To
        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustLocCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, scrnMsg.billToCustLocCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustLocCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, scrnMsg.shipToCustLocCd);
        }
        scrnMsg.V.setValidCount(index);
        paramList.add(scrnMsg.V);

        // 2018/07/26 QC#26661,26713 Add Start
        // Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.lineBizTpCd);
        paramList.add(scrnMsg.xxPopPrm_05);
        // 2018/07/26 QC#26661,26713 Add End

        return paramList.toArray(new Object[0]);
    }
    /**
     * Check Special Instruction Data
     * @param scrnMsg NWAL1840BMsg
     * @return boolean
     */
    public static boolean checkSpecialInstrctionData(NWAL1840BMsg scrnMsg) {
    
    if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_SP) || 
        ZYPCommonFunc.hasValue(scrnMsg.billToCustLocCd_SP) || 
        ZYPCommonFunc.hasValue(scrnMsg.shipToCustLocCd_SP)) {

        return true;
    }
    return false;
}
    // 2018/07/16 QC#14307 Add End
}
