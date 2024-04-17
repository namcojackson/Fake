/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770.common;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_ARV_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_ARV_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_ARV_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.COMMA;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.CR_CARD_MODE_READ_ONLY;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.EIGHT_DIGIT_MODE;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.EMAIL_FORMAT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.EXT_MAX_LENGTH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.HYPHEN;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_0;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_1;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_10;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_100;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_11;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_12;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_13;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_2;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_20;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_3;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_30;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_33;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_4;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_5;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_50;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_6;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_7;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_8;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.IDX_9;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.KEY_NWAL1770_ATT_LIMIT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.KEY_NWAL1770_AUTHORIZE_FILE_EXT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.KEY_NWAL1770_AUTHORIZE_FILE_VOL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.KEY_QUOTE_ATT_DOC_TP;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.MSG_PARAM_BIZ_NM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.MSG_PARAM_QUOTE_NUM;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0664E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0763E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0796E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0832E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0964E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM8471W;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM8473W;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.PERCENT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.PERIOD;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SLASH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SPACE;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_ADDITIONAL;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_CUSTOMER;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_DELIVERY;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.TAB_ITEMS;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.ZZM9000E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.VALIDATION_TARGET;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.ALL_RGTN_AUTH;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CMN_ADD_BTN_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CMN_ADD_EVENT_NM;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.BTN_CMN_ADD_LABEL;
import static business.servlet.NWAL1770.constant.NWAL1770ConstantForScrnFields.REF_AUTH;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NWAL1660_MODE_REFERENCE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL1600.constant.NWAL1600Constant;
import business.servlet.NWAL1770.NWAL1770BMsg;
import business.servlet.NWAL1770.NWAL1770Bean;
import business.servlet.NWAL1770.NWAL1770_ABMsg;
import business.servlet.NWAL1770.NWAL1770_BBMsg;
import business.servlet.NWAL1770.NWAL1770_DBMsg;
import business.servlet.NWAL1770.NWAL1770_TBMsg;
import business.servlet.NWAL1770.NWAL1770_UBMsg;
import business.servlet.NWAL1770.NWAL1770_UBMsgArray;
import business.servlet.NWAL1790.constant.NWAL1790Constant;
import business.servlet.NWAL1810.constant.NWAL1810Constant;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/07   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_AN#7861
 * 2016/05/19   Fujitsu         T.Murai         Update          S21_AN#7659
 * 2016/05/23   Fujitsu         T.Murai         Update          S21_AN#8544
 * 2016/05/30   Fujitsu         T.Murai         Update          S21_AN#8393
 * 2016/06/21   Fujitsu         T.Yoshida       Update          S21_AN#8210
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/26   Fujitsu         M.Hara          Update          S21_NA#8412
 * 2016/08/04   Fujitsu         R.Nakamura      Update          S21_NA#9078
 * 2016/08/31   Fujitsu         T.Murai         Update          S21_AN#11547,14020
 * 2016/09/06   Fujitsu         H.Ikeda         Update          S21_AN#13917
 * 2016/09/29   Fujitsu         H.Ikeda         Update          S21_AN#11655
 * 2016/10/04   Fujitsu         H.Ikeda         Update          S21_AN#13958
 * 2016/10/24   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2016/11/07   Fujitsu         Y.Kanefusa      Update          S21_NA#14143
 * 2016/11/25   Fujitsu         M.Ohno          Update          S21_NA#15733
 * 2017/07/31   Fujitsu         S.Takami        Update          S21_NA#20331
 * 2017/08/01   Hitachi         E.Kameishi      Update          QC#20295
 * 2017/08/02   Fujitsu         S.Takami        Update          S21_NA#20331-2
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/08/09   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/10/17   Hitachi         J.Kim           Update          QC#21760
 * 2017/10/18   Fujitsu         W.Honda         Create          S21_NA#20246-1(L3 Sol#454)
 * 2017/11/27   Fujitsu         M.Ohno          Update          S21_NA#21155
 * 2017/12/20   Fujitsu         Mz.Takahashi    Update          S21_NA#20164(L3 Sol#356)
 * 2018/02/13   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/03/05   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/05/13   Fujitsu         S.Takami        Update          S21_NA#25251
 * 2018/06/21   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 2018/09/19   Fujitsu         S.Kosaka        Update          S21_NA#9700
 * 2018/09/28   Fujitsu         M.Yamada        Update          S21_NA#28297
 * 2018/10/18   Fujitsu         K.Ishizuka      Update          S21_NA#28712
 * 2018/10/23   Fujitsu         M.Ohno          Update          S21_NA#28425
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          S21_NA#24022
 * 2018/12/12   Fujitsu         K.Kato          Update          S21_NA#29315
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 * </pre>
 */
public class NWAL1770CommonLogic {

    /**
     * Initial Common Button
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

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

        //START 2017/08/01 E.Kameishi [QC#20295,ADD]
        handler.setButtonProperties(BTN_CMN_ADD_BTN_NM, BTN_CMN_ADD_EVENT_NM, BTN_CMN_ADD_LABEL, 0, null);
        //END 2017/08/01 E.Kameishi [QC#20295,ADD]
    }

    /**
     * Inactive Add Button
     * @param handler EZCommandHandler
     */
    public static void inactiveRegistrationButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
    }

    /**
     * set Table's Back Color(For Contact Line)
     * @param scrnMsg NWAL1770BMsg
     */
    public static final void setTblBackColorForContact(NWAL1770BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(NWAL1770Bean.A, scrnMsg.A);
        tblColor.setAlternateRowsBG(NWAL1770Bean.A, scrnMsg.A);
    }

    /**
     * set Table's Back Color(For Item Line)
     * @param scrnMsg NWAL1770BMsg
     */
    public static final void setTblBackColorForItem(NWAL1770BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(NWAL1770Bean.B, scrnMsg.B);
        tblColor.setAlternateRowsBG(NWAL1770Bean.B, scrnMsg.B);
    }

    /**
     * set Table's Back Color(For Additional Line)
     * @param scrnMsg NWAL1770BMsg
     */
    public static final void setTblBackColorForAddl(NWAL1770BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(NWAL1770Bean.C, scrnMsg.C);
        tblColor.setAlternateRowsBG(NWAL1770Bean.C, scrnMsg.C);
    }

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /**
     * set Table's Back Color(For Order History)
     * @param scrnMsg NWAL1770BMsg
     */
    public static final void setTblBackColorForOrderHistory(NWAL1770BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(NWAL1770Bean.H, scrnMsg.H);
        tblColor.setAlternateRowsBG(NWAL1770Bean.H, scrnMsg.H);
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

    /**
     * Clear Popup Parameters
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1770BMsg scrnMsg) {

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
        scrnMsg.xxPopPrm_10.clear();
        // 2018/06/21 QC#14307 Add Start
        scrnMsg.xxPopPrm_11.clear();
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        // 2018/07/10 S21_NA#26661,26713 Add End
    }

    /**
     * Get Param NLCL1000
     * @param scrnMsg NWAL1770BMsg
     * @return Param NLCL1000
     */
    public static Object[] getParamNLCL1000(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_08, ZYPConstant.FLG_OFF_N);

        NWAL1770_UBMsgArray detailList = scrnMsg.U;
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);

        int validCnt = 0;
        for (; validCnt < checkList.size(); validCnt++) {
            int slctLineNum = checkList.get(validCnt);
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(slctLineNum);

            // Set Detail List
            NWAL1770_UBMsg detailMsg = detailList.no(validCnt);
            ZYPEZDItemValueSetter.setValue(detailMsg.xxScrItem20Txt_P1, String.valueOf(slctLineNum));
            ZYPEZDItemValueSetter.setValue(detailMsg.rtlWhCd_P1, itemLineMsg.rtlWhCd_B);
            ZYPEZDItemValueSetter.setValue(detailMsg.rtlSwhCd_P1, itemLineMsg.rtlSwhCd_B);
            ZYPEZDItemValueSetter.setValue(detailMsg.invtyLocCd_P1, itemLineMsg.invtyLocCd_B);
            ZYPEZDItemValueSetter.setValue(detailMsg.shipToCustCd_P1, scrnMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd_P1, itemLineMsg.mdseCd_B);
            ZYPEZDItemValueSetter.setValue(detailMsg.ordQty_P1, itemLineMsg.ordQty_B);
            ZYPEZDItemValueSetter.setValue(detailMsg.rddDt_P1, scrnMsg.rddDt);
        }
        detailList.setValidCount(validCnt);

        Object[] params = new Object[IDX_10];
        params[IDX_0] = scrnMsg.xxPopPrm_00;
        params[IDX_1] = scrnMsg.xxPopPrm_01;
        params[IDX_2] = scrnMsg.xxPopPrm_02;
        params[IDX_3] = scrnMsg.xxPopPrm_03;
        params[IDX_4] = scrnMsg.xxPopPrm_04;
        params[IDX_5] = scrnMsg.xxPopPrm_05;
        params[IDX_6] = scrnMsg.xxPopPrm_06;
        params[IDX_7] = scrnMsg.xxPopPrm_07;
        params[IDX_8] = scrnMsg.xxPopPrm_08;
        params[IDX_9] = detailList;
        return params;
    }

    /**
     * Get Param NMAL3300
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL3300
     */
    public static Object[] getParamNMAL3300(NWAL1770BMsg scrnMsg) {

        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_02.getValue();
        // 2018/07/10 S21_NA#26661,26713 Add End
        String trxRuleTp = scrnMsg.xxPopPrm_03.getValue();
        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, BIZ_ID);
        // 2018/07/10 S21_NA#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, funcCatgId);
        // 2018/07/10 S21_NA#26661,26713 Add End
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
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, scrnMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");

        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, scrnMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");

        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
        ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, scrnMsg.shipToCustCd);
        scrnMsg.V.setValidCount(index);
        paramList.add(scrnMsg.V);

        // 2018/07/10 S21_NA#26661,26713 Add Start
        // Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.lineBizTpCd);
        paramList.add(scrnMsg.xxPopPrm_05);
        // 2018/07/10 S21_NA#2666,267131 Add End

        return paramList.toArray(new Object[0]);
    }

    // Add Start 2016/08/31 QC#11547
    /**
     * Get Param NMAL6050 For Payment Term
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6050
     */
    public static Object[] getParamNMAL6050ForPmtTerm(NWAL1770BMsg scrnMsg) {

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
    // Add End 2016/08/31 QC#11547
    
    /**
     * Get Param NMAL6770 For Bill To
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6770 For Bill To
     */
    public static Object[] getParamNMAL6760ForBillTo(NWAL1770BMsg scrnMsg) {

        // 2018/06/21 QC#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_10.getValue();
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_12.getValue();
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

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod End 2016/08/04 QC#9078
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(billToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, billToAcctNum);

        // 2018/06/21 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_10, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, BIZ_ID);
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_13, scrnMsg.lineBizTpCd);
        // 2018/07/10 S21_NA#26661,26713 Add End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
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

        // 2018/06/21 QC#14307 Add Start
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
        paramList.add(scrnMsg.xxPopPrm_10); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_00); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_11); // [39] Function ID
        // 2018/06/21 QC#14307 Add End

        // 2018/07/10 S21_NA#26661,26713 Add Start
        paramList.add(scrnMsg.xxPopPrm_12); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_13); // [41] Line of Business Code
        // 2018/07/10 S21_NA#26661,26713 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770 For Ship To
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6770 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL1770BMsg scrnMsg) {

        // 2018/06/21 QC#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_10.getValue();
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_12.getValue();
        // 2018/07/10 S21_NA#26661,26713 Add End

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

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        // Mod Start 2016/08/04 QC#9078
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(shipToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, shipToAcctNum);

        // 2018/06/21 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_10, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, BIZ_ID);
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_13, scrnMsg.lineBizTpCd);
        // 2018/07/10 S21_NA#26661,26713 Add End
 
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
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

        // 2018/06/21 QC#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_00); // [33]
        paramList.add(scrnMsg.xxPopPrm_00); // [34]
        paramList.add(scrnMsg.xxPopPrm_00); // [35]
        paramList.add(scrnMsg.xxPopPrm_09); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_10); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_00); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_11); // [39] Function ID
        // 2018/06/21 QC#14307 Add End

        // 2018/07/10 S21_NA#26661,26713 Add Start
        paramList.add(scrnMsg.xxPopPrm_12); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_13); // [41] Line of Business Code
        // 2018/07/10 S21_NA#26661,26713 Add End
 
        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770 For Sold To
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6770 For Sold To
     */
    public static Object[] getParamNMAL6760ForSoldTo(NWAL1770BMsg scrnMsg) {

        // 2018/06/21 QC#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_10.getValue();
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_12.getValue();
        // 2018/07/10 S21_NA#26661,26713 Add End

        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        clearPopUpParam(scrnMsg);

        String soldToAcctNum = getSoldToAcctNum(scrnMsg);

        // Mod Start 2016/08/04 QC#9078
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // Mod End 2016/08/04 QC#9078
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(soldToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_06, soldToAcctNum);

        // 2018/06/21 QC#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_10, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_11, BIZ_ID);
        // 2018/06/21 QC#14307 Add End
        // 2018/07/10 S21_NA#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_13, scrnMsg.lineBizTpCd);
        // 2018/07/10 S21_NA#26661,26713 Add End
 
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
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

        // 2018/06/21 QC#14307 Add Start
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
        paramList.add(scrnMsg.xxPopPrm_10); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_00); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_11); // [39] Function ID
        // 2018/06/21 QC#14307 Add End

        // 2018/07/10 S21_NA#26661,26713 Add Start
        paramList.add(scrnMsg.xxPopPrm_12); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_13); // [41] Line of Business Code
        // 2018/07/10 S21_NA#26661,26713 Add End
        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6770
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6770
     */
    public static Object[] getParamNMAL6770(NWAL1770BMsg scrnMsg) {

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

        NWAL1770_ABMsg contactLineMsg = scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, contactLineMsg.ctacPsnTpCd_A); // QC#16452 add

        // 2018/03/07 S21_NA#22387 Add Start
        if (CTAC_CUST_REF_TP.SOLD_TO.equals(contactLineMsg.ctacCustRefTpCd_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.sellToLocNum);
        } else if (CTAC_CUST_REF_TP.BILL_TO.equals(contactLineMsg.ctacCustRefTpCd_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.billToLocNum);
        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(contactLineMsg.ctacCustRefTpCd_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_07, scrnMsg.shipToLocNum);
        }
        // 2018/03/07 S21_NA#22387 Add End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // Role
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        // QC#16452 mod Start
//        paramList.add(scrnMsg.xxPopPrm_06); // Ship To Account
//        paramList.add(scrnMsg.xxPopPrm_07); // Location Number
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        // 2018/03/07 S21_NA#22387 Mod Start
//        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_07);
        // 2018/03/07 S21_NA#22387 Mod End
        // QC#16452 mod End
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(contactLineMsg.ctacPsnFirstNm_A);
        paramList.add(contactLineMsg.ctacPsnLastNm_A);
        paramList.add(scrnMsg.xxPopPrm_02); // Phone
        paramList.add(scrnMsg.xxPopPrm_03); // Email
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_00); // no used
        paramList.add(scrnMsg.xxPopPrm_04); // Ext
        paramList.add(scrnMsg.xxPopPrm_05); // Fax
        paramList.add(contactLineMsg.dsCtacPntPk_A); // no used
        paramList.add(contactLineMsg.dsCtacPntPk_A); // no used
        paramList.add(contactLineMsg.dsCtacPntPk_A); // no used
        paramList.add(contactLineMsg.dsCtacPntPk_A); // no used
        paramList.add(contactLineMsg.ctacPsnPk_A); // Contact PK
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
        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, EIGHT_DIGIT_MODE);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_B);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.xxPopPrm_00);

        return paramList.toArray(new EZDBItem[0]);
    }

    // Add Start 2016/09/29 S21_NA#11655
    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800ForMan(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, EIGHT_DIGIT_MODE);

        String MnfCode = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_B.getValue();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, MnfCode);
        scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_B.clear();

        List<EZDBItem> paramList = new ArrayList<EZDBItem>(IDX_13);
        paramList.add(scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt()).mdseCd_B);
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
    // Add End 2016/09/29 S21_NA#11655
    
    /**
     * Get Param NWAL0140
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL0140
     */
    public static Object[] getParamNWAL0140(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        String splyQuoteStsCd = scrnMsg.splyQuoteStsCd.getValue();
        if (SPLY_QUOTE_STS.SUBMITTED.equals(splyQuoteStsCd) || SPLY_QUOTE_STS.CANCELLED.equals(splyQuoteStsCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, ZYPConstant.FLG_OFF_N);
        }

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.shipToCustCd);
        paramList.add(scrnMsg.shipToLocNm);
        paramList.add(scrnMsg.shipToAddlLocNm);
        paramList.add(scrnMsg.shipToFirstLineAddr);
        paramList.add(scrnMsg.shipToScdLineAddr);
        paramList.add(scrnMsg.shipToThirdLineAddr);
        paramList.add(scrnMsg.shipToFrthLineAddr);
        paramList.add(scrnMsg.shipToFirstRefCmntTxt);
        paramList.add(scrnMsg.shipToScdRefCmntTxt);
        paramList.add(scrnMsg.shipToCtyAddr);
        paramList.add(scrnMsg.shipToStCd);
        paramList.add(scrnMsg.shipToPostCd);
        paramList.add(scrnMsg.shipToCtryCd);
        paramList.add(scrnMsg.shipToCntyNm);
        paramList.add(scrnMsg.dropShipFlg);
        paramList.add(scrnMsg.xxPopPrm_00); // Read Only Flag
        paramList.add(scrnMsg.billToCustCd);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.xxPopPrm_01); // no used
        paramList.add(scrnMsg.shipToProvNm);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NWAL1770BMsg scrnMsg) {

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

        List<Object[]> whereList = new ArrayList<Object[]>();
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

        List<Object[]> columnList = new ArrayList<Object[]>();
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

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
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
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NWAL1770BMsg scrnMsg) {

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
        // 2016/10/24 S21_NA#14607 Add Start
        sb.append("    AND SO.RGTN_STS_CD        != '").append(RGTN_STS.TERMINATED).append("'");
        // 2016/10/24 S21_NA#14607 Add End
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
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

        List<Object[]> whereList = new ArrayList<Object[]>();
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

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        // S21_NA#7861 Mod Start
        // columnArray0[IDX_0] = "Employee ID";
        // columnArray0[IDX_1] = "PSN_CD";
        // columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        // columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        // columnList.add(columnArray0);
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
        columnArray2[IDX_0] = "Line of Business";
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

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM"; // S21_NA#7861 Mod
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Freight Terms
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 For Freight Terms
     */
    public static Object[] getParamNWAL1130ForFrtTerm(NWAL1770BMsg scrnMsg) {

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
     * Get Param NWAL1130 For Carrier Service Level
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 Carrier Service Level
     */
    public static Object[] getParamNWAL1130ForCarrSvcLvl(NWAL1770BMsg scrnMsg) {

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
        // 2018/12/12 QC#29315 Add Start
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("   ,SHPG_SVC_LVL_CARR_RELN SSL ");
            sb.append("   , ( SELECT ");
            sb.append("           DAC.VND_CD ");
            sb.append("       FROM ");
            sb.append("           DS_ACCT_CARR DAC ");
            sb.append("       WHERE ");
            sb.append("               DAC.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("           AND DAC.DS_ACCT_NUM    IS NULL ");
            sb.append("           AND DAC.LOC_NUM        = '").append(scrnMsg.shipToLocNum.getValue()).append("' ");
            sb.append("           AND DAC.EFF_FROM_DT    <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("           AND (DAC.EFF_THRU_DT   >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("            OR DAC.EFF_THRU_DT    IS NULL) ");
            sb.append("           AND DAC.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("           AND DAC.DS_BIZ_AREA_CD = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("           AND DAC.EZCANCELFLAG   = '0' ");
            sb.append("       UNION ALL ");
            sb.append("       SELECT ");
            sb.append("           DAC.VND_CD ");
            sb.append("       FROM ");
            sb.append("           DS_ACCT_CARR DAC ");
            sb.append("       WHERE ");
            sb.append("               DAC.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("           AND DAC.DS_ACCT_NUM    = '").append(scrnMsg.shipToCustAcctCd.getValue()).append("' ");
            sb.append("           AND DAC.LOC_NUM        IS NULL ");
            sb.append("           AND DAC.EFF_FROM_DT    <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("           AND (DAC.EFF_THRU_DT   >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("            OR DAC.EFF_THRU_DT    IS NULL) ");
            sb.append("           AND DAC.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("           AND DAC.DS_BIZ_AREA_CD = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("           AND DAC.EZCANCELFLAG   = '0' ");
            sb.append("           AND NOT EXISTS ( ");
            sb.append("               SELECT ");
            sb.append("                   1 ");
            sb.append("               FROM ");
            sb.append("                   DS_ACCT_CARR DAC2 ");
            sb.append("               WHERE ");
            sb.append("                       DAC2.GLBL_CMPY_CD   = DAC.GLBL_CMPY_CD ");
            sb.append("                   AND DAC2.LOC_NUM        = '").append(scrnMsg.shipToLocNum.getValue()).append("' ");
            sb.append("                   AND DAC2.EFF_FROM_DT    >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("                   AND (DAC2.EFF_THRU_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("                    OR DAC2.EFF_THRU_DT    IS NULL) ");
            sb.append("                   AND DAC2.LINE_BIZ_TP_CD = DAC.LINE_BIZ_TP_CD ");
            sb.append("                   AND DAC2.DS_BIZ_AREA_CD = DAC.DS_BIZ_AREA_CD ");
            sb.append("                   AND DAC2.EZCANCELFLAG   = DAC.EZCANCELFLAG ");
            sb.append("               ) ");
            sb.append("       )DACV ");
        }
        // 2018/12/12 QC#29315 Add End
        sb.append("WHERE ");
        sb.append("    RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND RELN.FRT_COND_CD     = '").append(scrnMsg.frtCondCd.getValue()).append("' ");
        sb.append("AND RELN.SHPG_SVC_LVL_CD = '").append(scrnMsg.shpgSvcLvlCd.getValue()).append("' ");
        // 2018/12/12 QC#29315 Add Start
        sb.append("AND RELN.LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
        // 2018/12/12 QC#29315 Add End
        sb.append("AND RELN.EZCANCELFLAG    = '0' ");
        sb.append("AND RELN.GLBL_CMPY_CD    = OTD.GLBL_CMPY_CD ");
        sb.append("AND RELN.LINE_BIZ_TP_CD  = OTD.LINE_BIZ_TP_CD ");
        sb.append("AND OTD.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("' ");
        sb.append("AND OTD.EZCANCELFLAG     = '0' ");
        sb.append("AND RELN.GLBL_CMPY_CD    = CSL.GLBL_CMPY_CD ");
        sb.append("AND RELN.CARR_SVC_LVL_CD = CSL.CARR_SVC_LVL_CD ");
        sb.append("AND CSL.EZCANCELFLAG     = '0' ");
        // 2018/12/12 QC#29315 Add Start
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("AND RELN.GLBL_CMPY_CD    = SSL.GLBL_CMPY_CD ");
            sb.append("AND RELN.SHPG_SVC_LVL_CD = SSL.SHPG_SVC_LVL_CD ");
            sb.append("AND RELN.CARR_SVC_LVL_CD = SSL.CARR_SVC_LVL_CD ");
            sb.append("AND SSL.EZCANCELFLAG     = '0' ");
            sb.append("AND SSL.CARR_CD          = DACV.VND_CD ");
        }
        // 2018/12/12 QC#29315 Add End

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
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

        List<Object[]> columnList = new ArrayList<Object[]>();
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

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
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
     * Get Param NWAL1130 For Warehouse
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 For Warehouse
     */
    public static Object[] getParamNWAL1130ForWh(NWAL1770BMsg scrnMsg) {

        NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
        String rtlWhNm = itemLineMsg.rtlWhNm_B.getValue();
        // S21_NA#14020 Del
//        String rtlSubWhNm = itemLineMsg.rtlSwhNm_B.getValue();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Warehouse Search";
        params[IDX_2] = getSlctTblNmForWh(scrnMsg);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Warehouse Name";
        whereArray0[IDX_1] = "UPPER(RTL_WH_NM)";
        whereArray0[IDX_2] = rtlWhNm;

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = rtlWhNm;
        // S21_NA#14020 Mod 
//        if (ZYPCommonFunc.hasValue(rtlWhNm) || ZYPCommonFunc.hasValue(rtlSubWhNm)) {
            if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            whereArray0[IDX_2] = rtlWhNm;
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        // S21_NA#14020 Del Start 
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Sub Warehouse Name";
//        whereArray1[IDX_1] = "UPPER(RTL_SWH_NM)";
//        whereArray1[IDX_2] = rtlSubWhNm;
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
        // S21_NA#14020 Del End 

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Warehouse Code";
        columnArray0[IDX_1] = "RTL_WH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Warehouse Name";
        columnArray1[IDX_1] = "RTL_WH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        // S21_NA#14020 Del Start 
//        Object[] columnArray2 = new Object[IDX_4];
//        columnArray2[IDX_0] = "Sub Warehouse Code";
//        columnArray2[IDX_1] = "RTL_SWH_CD";
//        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_20);
//        columnArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray2);
//
//        Object[] columnArray3 = new Object[IDX_4];
//        columnArray3[IDX_0] = "Sub Warehouse Name";
//        columnArray3[IDX_1] = "RTL_SWH_NM";
//        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_22);
//        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray3);
        // S21_NA#14020 Del End 

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_WH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        // S21_NA#14020 Del Start 
//        Object[] sortConditionArray1 = new Object[IDX_2];
//        sortConditionArray1[IDX_0] = "RTL_SWH_CD";
//        sortConditionArray1[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray1);
        // S21_NA#14020 Del End 

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Sub Warehouse
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 For Sub Warehouse
     */
    public static Object[] getParamNWAL1130ForSubWh(NWAL1770BMsg scrnMsg) {

        NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
        String rtlWhCd = itemLineMsg.rtlWhCd_B.getValue();
        String rtlSubWhNm = itemLineMsg.rtlSwhNm_B.getValue();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sub Warehouse Search";
        params[IDX_2] = getSlctTblNmForSubWh(scrnMsg, rtlWhCd);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Sub Warehouse Name";
        whereArray[IDX_1] = "UPPER(RTL_SWH_NM)";

        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = rtlSubWhNm;
        if (ZYPCommonFunc.hasValue(rtlSubWhNm)) {
            whereArray[IDX_2] = rtlSubWhNm;
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Sub Warehouse Code";
        columnArray0[IDX_1] = "RTL_SWH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Sub Warehouse Name";
        columnArray1[IDX_1] = "RTL_SWH_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "RTL_SWH_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Y);
        params[IDX_6] = scrnMsg.Y;

        return params;
    }

    /**
     * Get Param NWAL1130 For Association Program
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1130 For Association Program
     */
    public static Object[] getParamNWAL1130ForAssnProgram(NWAL1770BMsg scrnMsg) {

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
     * Get Select Table Name For Werehouse
     * @param scrnMsg NWAL1770BMsg
     * @return Select Table Name For Werehouse
     */
    private static String getSlctTblNmForWh(NWAL1770BMsg scrnMsg) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String slsDt = scrnMsg.slsDt.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_WH_CD");
        sb.append("   ,WH.RTL_WH_NM ");
        // S21_NA#14020 Del
        //sb.append("   ,WH.RTL_SWH_CD");
        //sb.append("   ,WH.RTL_SWH_NM ");
        
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/18 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/18 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }

        return sb.toString();
    }

    /**
     * Get Select Table Name For Sub Werehouse
     * @param scrnMsg NWAL1770BMsg
     * @param rtlWhCd Retail Werehouse Code
     * @return Select Table Name For Sub Werehouse
     */
    private static String getSlctTblNmForSubWh(NWAL1770BMsg scrnMsg, String rtlWhCd) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String slsDt = scrnMsg.slsDt.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_SWH_CD");
        sb.append("   ,WH.RTL_SWH_NM ");
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/18 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/18 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }

        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            sb.append("    AND WH.RTL_WH_CD                     = '").append(rtlWhCd).append("'");
        }

        return sb.toString();
    }

    /**
     * Get Param NWAL1600
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1600
     */
    public static Object[] getParamNWAL1600(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        String splyQuoteStsCd = scrnMsg.splyQuoteStsCd.getValue();
        if (SPLY_QUOTE_STS.SUBMITTED.equals(splyQuoteStsCd) || SPLY_QUOTE_STS.CANCELLED.equals(splyQuoteStsCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NWAL1600Constant.MODE_REFERENCE);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NWAL1600Constant.MODE_QUOTE_ONLY);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, "T");
        getSlsCreditList(scrnMsg);

        Object[] params = new Object[IDX_6];
        params[IDX_0] = scrnMsg.xxPopPrm_00;
        params[IDX_1] = scrnMsg.xxPopPrm_01;
        params[IDX_2] = scrnMsg.xxPopPrm_02; // no use
        params[IDX_3] = scrnMsg.xxPopPrm_03;
        params[IDX_4] = scrnMsg.T;
        params[IDX_5] = scrnMsg.xxPopPrm_04;
        return params;
    }

    /**
     * Get Param NWAL1660
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1660
     */
    public static Object[] getParamNWAL1660(NWAL1770BMsg scrnMsg) {

        String splyQuoteStsCd = scrnMsg.splyQuoteStsCd.getValue();
        if (SPLY_QUOTE_STS.SUBMITTED.equals(splyQuoteStsCd) || SPLY_QUOTE_STS.CANCELLED.equals(splyQuoteStsCd)) {
            // Mod Start 2016/08/04 QC#9078
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_S, NWAL1660Constant.MODE_REFERENCE);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_S, NWAL1660_MODE_REFERENCE);
            // Mod End 2016/08/04 QC#9078
        }

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.xxModeCd_S); // 0
        paramList.add(scrnMsg.xxModeCd_PM);  // 1 S21_NA#22965 Add
        paramList.add(scrnMsg.xxViewChngLogSrcCd_S);  // 2 QC#9700 Add
        paramList.add(scrnMsg.trxHdrNum_S); // 3
        paramList.add(scrnMsg.prcBaseDt_S); // 4
        paramList.add(scrnMsg.prcCalcDt_S); // 5
        paramList.add(scrnMsg.dsOrdTpCd_S); // 6
        paramList.add(scrnMsg.dsOrdCatgCd_S); // 7
        paramList.add(scrnMsg.lineBizTpCd_S); // 8
        paramList.add(scrnMsg.cpoSrcTpCd_S); // 9
        paramList.add(scrnMsg.custIssPoNum_S); // 10
        paramList.add(scrnMsg.dsPmtMethCd_S); // 11
        paramList.add(scrnMsg.spclHdlgTpCd_S); // 12
        paramList.add(scrnMsg.leasePrchOptCd_S); // 13
        paramList.add(scrnMsg.dsOrdPosnNum_S); // 14
        paramList.add(scrnMsg.trxLineNum_S); // 15
        paramList.add(scrnMsg.trxLineSubNum_S); // 16
        paramList.add(scrnMsg.configCatgCd_S); // 17
        paramList.add(scrnMsg.inEachQty_S); // 18
        paramList.add(scrnMsg.shipToFirstLineAddr_S); // 19
        paramList.add(scrnMsg.shipToScdLineAddr_S); // 20
        paramList.add(scrnMsg.shipToCtyAddr_S); // 21
        paramList.add(scrnMsg.shipToStCd_S); // 22
        paramList.add(scrnMsg.shipToCntyNm_S); // 23
        paramList.add(scrnMsg.shipToPostCd_S); // 24
        paramList.add(scrnMsg.shipToCtryCd_S); // 25
        paramList.add(scrnMsg.prcCatgCd_S); // 26
        paramList.add(scrnMsg.csmpNum_S); // 27
        paramList.add(scrnMsg.dlrRefNum_S); // 28
        paramList.add(scrnMsg.prcContrNum_S); // 29
        paramList.add(scrnMsg.coaBrCd_S); // 30
        paramList.add(scrnMsg.mdseCd_S); // 31
        paramList.add(scrnMsg.billToCustCd_S); // 32
        paramList.add(scrnMsg.billToCustAcctCd_S); // 33
        paramList.add(scrnMsg.sellToCustCd_S); // 34
        paramList.add(scrnMsg.soldToCustLocCd_S); // 35
        paramList.add(scrnMsg.shipToCustCd_S); // 36
        paramList.add(scrnMsg.shipToCustAcctCd_S); // 37
        paramList.add(scrnMsg.frtCondCd_S); // 38
        paramList.add(scrnMsg.shpgSvcLvlCd_S); // 39
        paramList.add(scrnMsg.ccyCd_S); // 40
        paramList.add(scrnMsg.uomCd_S); // 41
        paramList.add(scrnMsg.ordCustUomQty_S); // 42
        paramList.add(scrnMsg.dsCpoLineCatgCd_S); // 43
        paramList.add(scrnMsg.invQty_S); // 44
        paramList.add(scrnMsg.mdlId_S); // 45
        paramList.add(scrnMsg.rtrnRsnCd_S); // 46
        paramList.add(scrnMsg.coaExtnCd_S); // 47
        paramList.add(scrnMsg.slsRepOrSlsTeamTocCd_S); // 48
        paramList.add(scrnMsg.rtlWhCd_S); // 49
        paramList.add(scrnMsg.xxTotBaseAmt_S); // 50
        paramList.add(scrnMsg.xxSubTotCalcPrcAmt_S); // 51
        paramList.add(scrnMsg.xxTotChrgPrcAmt_S); // 52
        paramList.add(scrnMsg.xxTotDiscAmt_S); // 53
        paramList.add(scrnMsg.xxTotSpclPrcAmt_S); // 54
        paramList.add(scrnMsg.xxTotNetDiscAmt_S); // 55
        paramList.add(scrnMsg.xxUnitNetPrcAmt_S); // 56
        paramList.add(scrnMsg.xxUnitGrsPrcAmt_S); // 57
        paramList.add(scrnMsg.xxTotNetPrcAmt_S); // 58
        paramList.add(scrnMsg.xxGrsAmt_S); // 59
        paramList.add(scrnMsg.xxUnitFrtAmt_S); // 60
        paramList.add(scrnMsg.xxTotFrtAmt_S); // 61
        paramList.add(scrnMsg.xxUnitSpclChrgAmt_S); // 62
        paramList.add(scrnMsg.xxTotSpclChrgAmt_S); // 63
        paramList.add(scrnMsg.xxTotFrtSubAmt_S); // 64
        paramList.add(scrnMsg.xxUnitRestkFeeAmt_S); // 65
        paramList.add(scrnMsg.xxTotRestkFeeAmt_S); // 66
        paramList.add(scrnMsg.xxTotNetPrcAmt_S2); // 67
        paramList.add(scrnMsg.xxTotTaxAmt_S); // 68
        paramList.add(scrnMsg.xxUnitPrc_S); // 69
        paramList.add(scrnMsg.xxTotAmt_S); // 70
        paramList.add(scrnMsg.dealPrcListPrcAmt_S); // 71
        paramList.add(scrnMsg.dsCpoLineNum_S); // 72
        paramList.add(scrnMsg.dsCpoLineSubNum_S); // 73
        paramList.add(scrnMsg.dealSvcRevTrnsfAmt_S); // 74
        paramList.add(scrnMsg.dealSvcCostTrnsfAmt_S); // 75
        paramList.add(scrnMsg.dealManFlAdjAmt_S); // 76
        paramList.add(scrnMsg.dealManRepRevAdjAmt_S); // 77
        paramList.add(scrnMsg.xxTotUnitNetWt_S);  // 78 S21_NA#22965 Add
        scrnMsg.xxSfxKeyTxt_S.setValue("CB");
        paramList.add(scrnMsg.xxSfxKeyTxt_S); // 79
        paramList.add(scrnMsg.S); // 80
        // 2018/05/13 S21_NA#25251 Add Start
        String prcChangeAuth = ZYPConstant.FLG_OFF_N;
        if (hasPriceChangeAuth(scrnMsg)) {
            prcChangeAuth = ZYPConstant.FLG_ON_Y;
        }
        paramList.add(prcChangeAuth); // 81
        // 2018/05/13 S21_NA#25251 Add End

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Param NWAL1760
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1760
     */
    public static Object[] getParamNWAL1760(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, PRC_CTX_TP.SALES_PRICING);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        // QC#9437 2016/06/21 Mod Start
        // paramList.add(scrnMsg.splyQuoteDt);
        paramList.add(scrnMsg.slsDt);
        // QC#9437 2016/06/21 Mod End
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.splyQuoteCatgCd);
        paramList.add(scrnMsg.dsOrdTpCd);
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.xxPopPrm_00); // no use
        paramList.add(scrnMsg.xxPopPrm_00); // no use
        paramList.add(scrnMsg.prcContrNum);
        paramList.add(scrnMsg.coaBrCd);
        paramList.add(scrnMsg.xxPopPrm_01); // Price Category Code
        paramList.add(scrnMsg.xxPopPrm_02); // Price Category Name
        paramList.add(scrnMsg.xxPopPrm_00); // no use
        paramList.add(scrnMsg.xxPopPrm_00); // no use

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1790
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1790
     */
    public static Object[] getParamNWAL1790(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPTableUtil.clear(scrnMsg.W);

        if ("OpenWin_Confirmation".equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NWAL1790Constant.CONFIRMATION_FLAG);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, NWAL1790Constant.TRACKING_FLAG);
        }

        int validCnt = 0;
        for (; validCnt < scrnMsg.A.getValidCount(); validCnt++) {
            EZDMsg.copy(scrnMsg.A.no(validCnt), "A", scrnMsg.W.no(validCnt), "W");
        }
        scrnMsg.W.setValidCount(validCnt);

        Object[] params = new Object[IDX_5];
        params[IDX_0] = scrnMsg.xxPopPrm_00; // Process Mode
        params[IDX_1] = scrnMsg.W;
        params[IDX_2] = "W"; // Suffix
        params[IDX_3] = scrnMsg.xxRqstFlg_ML; // Mail Send Flag
        params[IDX_4] = scrnMsg.xxRqstFlg_PR; // Print Flag

        return params;
    }

    /**
     * Get Param NWAL1800
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1800
     */
    public static Object[] getParamNWAL1800(NWAL1770BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.X);

        int i = 0;
        for (; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(i).mdseCd_X, itemLineMsg.mdseCd_B);
        }
        scrnMsg.X.setValidCount(i);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.X);
        paramList.add("X");

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Param NWAL1810
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1810
     */
    public static Object[] getParamNWAL1810(NWAL1770BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, NWAL1810Constant.SOURCE_ID_QUOTE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, NWAL1810Constant.LVL_CD_ALL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, NWAL1810Constant.DEF_CD_DETAIL);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_00);
        paramList.add(scrnMsg.xxPopPrm_01);
        paramList.add(scrnMsg.xxPopPrm_02);
        paramList.add(scrnMsg.xxPopPrm_03);
        paramList.add(scrnMsg.xxPopPrm_04); // no use

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL2010
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL2010
     */
    public static Object[] getParamNWAL2010(NWAL1770BMsg scrnMsg) {

        // S21_NA#8210 Mod Start
        // ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd_R, scrnMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.sellToCustCd_R, scrnMsg.billToCustAcctCd);
        // S21_NA#8210 Mod End

        ZYPEZDItemValueSetter.setValue(scrnMsg.crCardTrxTpCd_R, CR_CARD_TRX_TP.CPO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.firstTrxInfoTxt_R, scrnMsg.splyQuoteNum);
        scrnMsg.scdTrxInfoTxt_R.clear();
        scrnMsg.thirdTrxInfoTxt_R.clear();
        scrnMsg.frthTrxInfoTxt_R.clear();
        scrnMsg.fifthTrxInfoTxt_R.clear();
        scrnMsg.firstTrxInfoNum_R.clear();
        scrnMsg.scdTrxInfoNum_R.clear();
        scrnMsg.thirdTrxInfoNum_R.clear();
        scrnMsg.frthTrxInfoNum_R.clear();
        scrnMsg.fifthTrxInfoNum_R.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCrCardPk_R, scrnMsg.dsCrCardPk);
        // 2016/10/04 S21_NA#13958 ADD START
        if (ZYPCommonFunc.hasValue(scrnMsg.splyQuoteStsCd) && SPLY_QUOTE_STS.SUBMITTED.equals(scrnMsg.splyQuoteStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_R, CR_CARD_MODE_READ_ONLY);
        } else{
            scrnMsg.xxModeCd_R.clear();
        }
        // 2016/10/04 S21_NA#13958 ADD END

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.sellToCustCd_R);
        paramList.add(scrnMsg.crCardTrxTpCd_R);
        paramList.add(scrnMsg.firstTrxInfoTxt_R);
        paramList.add(scrnMsg.scdTrxInfoTxt_R);
        paramList.add(scrnMsg.scdTrxInfoTxt_R);
        paramList.add(scrnMsg.frthTrxInfoTxt_R);
        paramList.add(scrnMsg.fifthTrxInfoTxt_R);
        paramList.add(scrnMsg.firstTrxInfoNum_R);
        paramList.add(scrnMsg.scdTrxInfoNum_R);
        paramList.add(scrnMsg.thirdTrxInfoNum_R);
        paramList.add(scrnMsg.frthTrxInfoNum_R);
        paramList.add(scrnMsg.fifthTrxInfoNum_R);
        paramList.add(scrnMsg.dsCrCardPk_R);
        // 2016/10/04 S21_NA#13958 ADD START
        paramList.add(scrnMsg.xxModeCd_R);
        // 2016/10/04 S21_NA#13958 ADD END

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Param NWAL1870
     * @param scrnMsg NWAL1770BMsg
     * @return Param NWAL1870
     */
    public static Object[] getParamNWAL1870(NWAL1770BMsg scrnMsg) {

        NWAL1770_BBMsg lineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
        scrnMsg.xxPopPrm_00.clear();

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(lineMsg.mdseCd_B);
        paramList.add(lineMsg.mdseNm_B);
        paramList.add(scrnMsg.mdseCd_SS);
        paramList.add(scrnMsg.mdseNm_SS);
        paramList.add(scrnMsg.xxPopPrm_00);

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Param ZYPL0310
     * @param scrnMsg NWAL1770BMsg
     * @return Param ZYPL0310
     */
    public static Object[] getParamZYPL0310(NWAL1770BMsg scrnMsg) {

        String dispMode = ZYPL0310Constant.DISPLAY_MODE_MODIFICATION;
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            // Only Reference Authority
            if (scrnMsg.Z.getValidCount() == 1 && REF_AUTH.equals(scrnMsg.Z.no(i).xxFuncId.getValue())) {
                dispMode = ZYPL0310Constant.DISPLAY_MODE_READ_ONLY;
            }
        }

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(dispMode);
        paramList.add(BIZ_ID);
        paramList.add(scrnMsg.splyQuoteNum.getValue());
        paramList.add(MSG_PARAM_BIZ_NM);
        paramList.add(MSG_PARAM_QUOTE_NUM);
        paramList.add(KEY_QUOTE_ATT_DOC_TP); // S21_NA#7659 Mod
        paramList.add(KEY_NWAL1770_ATT_LIMIT);
        paramList.add(KEY_NWAL1770_AUTHORIZE_FILE_EXT);
        paramList.add(KEY_NWAL1770_AUTHORIZE_FILE_VOL);

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Sales Credit List
     * @param scrnMsg NWAL1770BMsg
     */
    public static void getSlsCreditList(NWAL1770BMsg scrnMsg) {

        String slsRepTocCd = scrnMsg.slsRepTocCd.getValue();
        ZYPTableUtil.clear(scrnMsg.T);

        if (ZYPCommonFunc.hasValue(slsRepTocCd)) {
            boolean chkAllDelete = true;
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1770_DBMsg slsCreditMsg = scrnMsg.D.no(i);
                String xxRqstTpCd = slsCreditMsg.xxRqstTpCd_D.getValue();
                BigDecimal splyQuoteSlsCrPk = slsCreditMsg.splyQuoteSlsCrPk_D.getValue();

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

            if (scrnMsg.D.getValidCount() > 0 && !chkAllDelete) {
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    NWAL1770_DBMsg slsRepMsg = scrnMsg.D.no(i);
                    String lineBizRoleTpCd = slsRepMsg.slsRepRoleTpCd_D.getValue();

                    if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd)) {
                        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_D, slsRepTocCd);
                        String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_D, LINE_BIZ_ROLE_TP.ESS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_D, LINE_BIZ_ROLE_TP.ESS_WRITER);
                        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_D, LINE_BIZ_ROLE_TP.PPS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_D, LINE_BIZ_ROLE_TP.PPS_WRITER);
                        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_D, LINE_BIZ_ROLE_TP.LFS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_D, LINE_BIZ_ROLE_TP.LFS_WRITER);
                        }
                        break;
                    }
                }
            } else {
                NWAL1770_TBMsg paramSlsCreditMsg = scrnMsg.T.no(0);
                ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.slsRepTocCd_T, slsRepTocCd);
                ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.slsCrQuotFlg_T, ZYPConstant.FLG_ON_Y);
                paramSlsCreditMsg.slsRepCrPct_T.setValue(BigDecimal.valueOf(IDX_100));

                String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.lineBizRoleTpCd_T, LINE_BIZ_ROLE_TP.ESS_WRITER);
                } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.lineBizRoleTpCd_T, LINE_BIZ_ROLE_TP.PPS_WRITER);
                } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.lineBizRoleTpCd_T, LINE_BIZ_ROLE_TP.LFS_WRITER);
                }
                scrnMsg.T.setValidCount(1);
            }
        }

        if (scrnMsg.T.getValidCount() == 0) {
            int paramSlsCrCnt = 0;
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1770_DBMsg slsCreditMsg = scrnMsg.D.no(i);
                String xxRqstTpCd = slsCreditMsg.xxRqstTpCd_D.getValue();
                if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                    continue;
                }

                NWAL1770_TBMsg paramSlsCreditMsg = scrnMsg.T.no(paramSlsCrCnt);
                EZDMsg.copy(slsCreditMsg, "D", paramSlsCreditMsg, "T");
                ZYPEZDItemValueSetter.setValue(paramSlsCreditMsg.dsCpoSlsCrPk_T, slsCreditMsg.splyQuoteSlsCrPk_D);
                paramSlsCrCnt++;
            }
            scrnMsg.T.setValidCount(paramSlsCrCnt);
        }

        return;
    }

    /**
     * Combine Customer Address
     * @param scrnMsg NWAL1770BMsg
     * @param connectValue Connect Value
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1770BMsg scrnMsg, String connectValue) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToFirstLineAddr)) {
            return null;
        }

        // 2017/08/02 S21_NA#20331-2 Mod Start (Replaced action "20331")
        // 2017/07/31 S21_NA#20331 Mod Start
//        StringBuilder addr = new StringBuilder(scrnMsg.shipToFirstLineAddr.getValue());
        StringBuilder addr = new StringBuilder("");
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.dropShipFlg.getValue())) {
            // 2017/11/27 S21_NA#21155 mod start
//            addr = addr.append(scrnMsg.shipToLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_DS, scrnMsg.shipToLocNm);
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToAddlLocNm)) {
                // addr.append(connectValue);
                // 2017/11/27 S21_NA#21155 mod end
                addr.append(scrnMsg.shipToAddlLocNm.getValue());
                addr.append(connectValue);
            }
        }
        addr.append(scrnMsg.shipToFirstLineAddr.getValue());
        // 2017/07/31 S21_NA#20331 Mod End
        // 2017/08/02 S21_NA#20331-2 Mod End

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
     * Check Item All Fields
     * @param scrnMsg NWAL1770BMsg
     */
    public static void checkItemAllFields(NWAL1770BMsg scrnMsg) {

        // Header
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.splyQuoteSrcTpCd);
        scrnMsg.addCheckItem(scrnMsg.splyQuoteDt);
        scrnMsg.addCheckItem(scrnMsg.splyQuoteVldDaysAot);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.custIssPoDt);
        scrnMsg.addCheckItem(scrnMsg.slsRepTocNm);
        scrnMsg.addCheckItem(scrnMsg.psnNum); // S21_NA#7861 Mod
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm);

        String curDispTabName = scrnMsg.xxDplyTab.getValue();
        scrnMsg.xxDplyTab.clear();
        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();

        // Customer / Contact Tab
        itemList.add(scrnMsg.billToCustAcctCd);
        itemList.add(scrnMsg.billToCustCd);
        itemList.add(scrnMsg.billToCustAcctNm);
        itemList.add(scrnMsg.shipToCustAcctCd);
        itemList.add(scrnMsg.shipToCustCd);
        itemList.add(scrnMsg.shipToCustAcctNm);
        itemList.add(scrnMsg.sellToCustCd);
        itemList.add(scrnMsg.soldToCustLocCd);
        itemList.add(scrnMsg.soldToCustAcctNm);
        // Add Start 2017/12/10 QC#20164(L3 Sol#356)
        itemList.add(scrnMsg.sellToFirstRefCmntTxt);
        // Add End 2017/12/10 QC#20164(L3 Sol#356)

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg ctacLineMsg = scrnMsg.A.no(i);
            // Add Start 2017/12/10 QC#20164(L3 Sol#356)
            itemList.add(ctacLineMsg.ctacPsnTpCd_A);
            itemList.add(ctacLineMsg.ctacCustRefTpCd_AP);
            // Add End 2017/12/10 QC#20164(L3 Sol#356)

            itemList.add(ctacLineMsg.ctacPsnFirstNm_A);
            itemList.add(ctacLineMsg.ctacPsnLastNm_A);
        }

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
                scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                NWAL1770CommonLogic.setTblBackColorForContact(scrnMsg);
            }
        }

        // Delivery / Payment Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            itemList.add(scrnMsg.frtCondDescTxt);
            itemList.add(scrnMsg.shpgSvcLvlCd);
            itemList.add(scrnMsg.carrSvcLvlDescTxt);
            itemList.add(scrnMsg.carrAcctNum);
            itemList.add(scrnMsg.pmtTermCashDiscDescTxt);
            itemList.add(scrnMsg.dsPmtMethCd);
            itemList.add(scrnMsg.spclHdlgTpCd);
            itemList.add(scrnMsg.rddDt);

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_DELIVERY);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                }
            }
        }

        // Items Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
          //START 2024/04/03 [CSA-QC#63691,ADD]
            HashMap<Integer, String> mapIndex = new HashMap<Integer, String>();
            boolean valueFlag = false;
            boolean valueNextBlankFlag = false;
          //END 2024/04/03 [CSA-QC#63691,ADD]

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
              //START 2024/04/03 [CSA-QC#63691,ADD]
                boolean isItemBlank = !ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B);
                boolean isQtyBlank = !ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B);
                boolean isBlank = isItemBlank && isQtyBlank;
                    if (isBlank) {
                        if (valueFlag) {
                            // The next line is not blank.
                            valueNextBlankFlag = true;
                        }
                    } else {
                            // The current line has a value.
                            valueFlag = true;
                            mapIndex.put(i, VALIDATION_TARGET);
                            if (valueNextBlankFlag) {
                                // While there are incomplete line.
                                for (int j = i - 1; j >= 0; j--) {
                                    NWAL1770_BBMsg itemPrev = scrnMsg.B.no(j);
                                      boolean isItemBlankPrev = !ZYPCommonFunc.hasValue(itemPrev.mdseCd_B);
                                      boolean isQtyBlankPrev = !ZYPCommonFunc.hasValue(itemPrev.ordCustUomQty_B);
                                      boolean isBlankPrev = isItemBlankPrev && isQtyBlankPrev;
                                    if (isBlankPrev) {
                                        // Set incomplete line into mapIndex.
                                        mapIndex.put(j, VALIDATION_TARGET);
                                    } else {
                                        // When the previous line has a value, stop setting the valiation target.
                                        valueNextBlankFlag = false;
                                        break;
                                    }
                                }
                            }
                        }
                        //Ensure all the indexes.
                    if (!mapIndex.containsKey(i)) {
                        mapIndex.put(i, "");
                    }
                }
            for (Map.Entry<Integer, String> entry : mapIndex.entrySet()) {
                if (entry.getKey() == 0 && entry.getValue().isEmpty()) {
                    NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(entry.getKey());
                    itemList.add(itemLineMsg.mdseCd_B);
                    itemList.add(itemLineMsg.ordCustUomQty_B);
                    itemList.add(itemLineMsg.custUomCd_B);
                    itemList.add(itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
                    itemList.add(itemLineMsg.prcCatgNm_B);
                    itemList.add(itemLineMsg.rddDt_B); // 2018/02/13 QC#21165 Add
                    itemList.add(itemLineMsg.prcBaseDt_B); // QC#10347 2017/07/24 Add
                    itemList.add(itemLineMsg.dsOrdLineCatgCd_B);
                    itemList.add(itemLineMsg.ordLineSrcCd_B);
                    itemList.add(itemLineMsg.supdLockFlg_B);
                    itemList.add(itemLineMsg.custMdseCd_B);
                    itemList.add(itemLineMsg.rtlWhNm_B);
                    itemList.add(itemLineMsg.rtlSwhNm_B);
                }
                if (entry.getValue().equals(VALIDATION_TARGET)) {
                    NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(entry.getKey());
              //END 2024/04/03 [CSA-QC#63691,ADD]
                itemList.add(itemLineMsg.mdseCd_B);
                itemList.add(itemLineMsg.ordCustUomQty_B);
                itemList.add(itemLineMsg.custUomCd_B);
                itemList.add(itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
                itemList.add(itemLineMsg.prcCatgNm_B);
                itemList.add(itemLineMsg.rddDt_B); // 2018/02/13 QC#21165 Add
                itemList.add(itemLineMsg.prcBaseDt_B); // QC#10347 2017/07/24 Add
                itemList.add(itemLineMsg.dsOrdLineCatgCd_B);
                itemList.add(itemLineMsg.ordLineSrcCd_B);
                itemList.add(itemLineMsg.supdLockFlg_B);
                itemList.add(itemLineMsg.custMdseCd_B);
                itemList.add(itemLineMsg.rtlWhNm_B);
                itemList.add(itemLineMsg.rtlSwhNm_B);
            }
          //START 2024/04/03 [CSA-QC#63691,ADD]
         }
          //END 2024/04/03 [CSA-QC#63691,ADD]
            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_ITEMS);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                    NWAL1770CommonLogic.setTblBackColorForItem(scrnMsg);
                }
            }
        }

        // Comments Tab QC#28297
        scrnMsg.addCheckItem(scrnMsg.quotePrintCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.ordPrintCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.shpgCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.splyQuoteCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.invCmntTxt);

        // Additional Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            itemList.add(scrnMsg.prcContrNum);
            // Add Start 2016/08/31 QC#13917
            itemList.add(scrnMsg.firstBllgAttrbValTxt);
            itemList.add(scrnMsg.scdBllgAttrbValTxt);
            itemList.add(scrnMsg.thirdBllgAttrbValTxt);
            itemList.add(scrnMsg.frthBllgAttrbValTxt);
            itemList.add(scrnMsg.fifthBllgAttrbValTxt);
            itemList.add(scrnMsg.sixthBllgAttrbValTxt);
            // Add End 2016/08/31 QC#13917

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_ADDITIONAL);
                    scrnMsg.clearAllGUIAttribute(SCREEN_ID);
                    NWAL1770CommonLogic.setTblBackColorForAddl(scrnMsg);
                }
            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, curDispTabName);
        }

        scrnMsg.putErrorScreen();
    }

    /**
     * Check Payment Terms Field
     * @param scrnMsg NWAL1770BMsg
     */
    public static void checkPmtTermsField(NWAL1770BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.pmtTermCashDiscDescTxt)) {
            scrnMsg.setMessageInfo(NWAM0796E, new String[] {scrnMsg.billToCustCd.getValue() });
            throw new EZDFieldErrorException();
        }
    }

    /**
     * Check Customer PO Field
     * @param scrnMsg NWAL1770BMsg
     */
    public static void checkCustPoField(NWAL1770BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1770BMsg
     */
    public static void checkContactField(NWAL1770BMsg scrnMsg) {

        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1770_ABMsg ctacLineMsg = scrnMsg.A.no(i);
            itemList.add(ctacLineMsg.ctacPsnFirstNm_A);
            itemList.add(ctacLineMsg.ctacPsnLastNm_A);
            itemList.add(ctacLineMsg.ctacPsnTelNum_A);
            itemList.add(ctacLineMsg.ctacPsnExtnNum_A);
            itemList.add(ctacLineMsg.ctacPsnEmlAddr_A);
            itemList.add(ctacLineMsg.ctacPsnFaxNum_A);

            if (!ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnFirstNm_A)) {
                ctacLineMsg.ctacPsnFirstNm_A.setErrorInfo(1, ZZM9000E, new String[] {ctacLineMsg.ctacPsnFirstNm_A.getNameForMessage() });
            }

            if (!ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnLastNm_A)) {
                ctacLineMsg.ctacPsnLastNm_A.setErrorInfo(1, ZZM9000E, new String[] {ctacLineMsg.ctacPsnLastNm_A.getNameForMessage() });
            }

            if (!ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnTelNum_A) && !ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnEmlAddr_A) && !ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnFaxNum_A)) {
                ctacLineMsg.ctacPsnTelNum_A.setErrorInfo(1, NWAM0832E);
                ctacLineMsg.ctacPsnExtnNum_A.setErrorInfo(1, NWAM0832E); // QC#14143 2016/11/07 Add
                ctacLineMsg.ctacPsnEmlAddr_A.setErrorInfo(1, NWAM0832E);
                ctacLineMsg.ctacPsnFaxNum_A.setErrorInfo(1, NWAM0832E);
            }

            // Mos Start 2016/11/25 M.Ohno S21_NA#15733
//            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnTelNum_A)) {
//                if (!ctacLineMsg.ctacPsnTelNum_A.getValue().matches(CHK_PHONE_PATTERN)) {
//                    ctacLineMsg.ctacPsnTelNum_A.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
//                }
//            }
            checkTelFormat(ctacLineMsg.ctacPsnTelNum_A);

//            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnExtnNum_A)) {
//                if (!ctacLineMsg.ctacPsnExtnNum_A.getValue().matches(CHK_EXT_PATTERN)) {
//                    ctacLineMsg.ctacPsnExtnNum_A.setErrorInfo(1, NWAM0664E, new String[] {EXT_FORMAT });
//                }
//            }
            checkExtFormat(ctacLineMsg.ctacPsnExtnNum_A);
            // Mod End   2016/11/25 M.Ohno S21_NA#15733

            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnEmlAddr_A)) {
                // 2016/05/13 QC#1443 Add Start --------------
                boolean ret =NMXC106001CommonCheckUtil.checkEmailFormat(ctacLineMsg.ctacPsnEmlAddr_A.getValue());

                if (!ret){
                    ctacLineMsg.ctacPsnEmlAddr_A.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                }
                // 2016/05/13 QC#1443 Add End --------------
                //if (!ctacLineMsg.ctacPsnEmlAddr_A.getValue().matches(CHK_EMAIL_PATTERN)) {
                //    ctacLineMsg.ctacPsnEmlAddr_A.setErrorInfo(1, NWAM0664E, new String[] {EMAIL_FORMAT });
                //}
            }
            // Mos Start 2016/11/25 M.Ohno S21_NA#15733
//            if (ZYPCommonFunc.hasValue(ctacLineMsg.ctacPsnFaxNum_A)) {
//                if (!ctacLineMsg.ctacPsnFaxNum_A.getValue().matches(CHK_PHONE_PATTERN)) {
//                    ctacLineMsg.ctacPsnFaxNum_A.setErrorInfo(1, NWAM0664E, new String[] {TEL_FORMAT });
//                }
//            }
            checkTelFormat(ctacLineMsg.ctacPsnFaxNum_A);
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

    // START 2017/10/17 J.Kim [QC#21760,DEL]
    ///**
    // * Check Service Level Field
    // * @param scrnMsg NWAL1770BMsg
    // */
    //public static void checkRddField(NWAL1770BMsg scrnMsg) {
    //
    //    String rddDt = scrnMsg.rddDt.getValue();
    //
    //    if (ZYPCommonFunc.hasValue(rddDt)) {
    //        if (ZYPDateUtil.compare(rddDt, scrnMsg.slsDt.getValue()) < 0) {
    //            scrnMsg.rddDt.setErrorInfo(1, NWAM0834E);
    //            scrnMsg.xxDplyTab.setValue(TAB_DELIVERY);
    //            scrnMsg.addCheckItem(scrnMsg.rddDt);
    //            scrnMsg.putErrorScreen();
    //        }
    //
    //        if (!SHPG_SVC_LVL.GROUND_SERVICE.equals(scrnMsg.shpgSvcLvlCd.getValue())) {
    //            scrnMsg.shpgSvcLvlCd.setErrorInfo(1, NWAM0835E);
    //            scrnMsg.xxDplyTab.setValue(TAB_DELIVERY);
    //            scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd);
    //            scrnMsg.putErrorScreen();
    //        }
    //    }
    // }
    // END 2017/10/17 J.Kim [QC#21760,DEL]

    /**
     * Get Bill To Account Number For Popup Parameter
     * @param scrnMsg NWAL1770BMsg
     * @return Bill To Account Number For Popup Parameter
     */
    private static String getBillToAcctNum(NWAL1770BMsg scrnMsg) {

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
     * @param scrnMsg NWAL1770BMsg
     * @return Ship To Account Number For Popup Parameter
     */
    private static String getShipToAcctNum(NWAL1770BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return scrnMsg.shipToCustAcctCd.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return scrnMsg.sellToCustCd.getValue();
        }

        return null;
    }

    /**
     * Get Sold To Account Number For Popup Parameter
     * @param scrnMsg NWAL1770BMsg
     * @return Sold To Account Number For Popup Parameter
     */
    private static String getSoldToAcctNum(NWAL1770BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
            return scrnMsg.sellToCustCd.getValue();
        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            return scrnMsg.shipToCustAcctCd.getValue();
        }

        return null;
    }

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

    // Del Start 2017/12/20 QC#20164(L3 Sol#356)
    // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)  
    //public static void contactToAttention(NWAL1770BMsg scrnMsg, int rowCnt) {
    //    if (!ZYPCommonFunc.hasValue(scrnMsg.sellToFirstRefCmntTxt)) {

    //        if (CTAC_TP.ORDER_CONTACT.equals(scrnMsg.A.no(rowCnt).ctacPsnTpCd_A.getValue())
    //                && ZYPCommonFunc.hasValue(scrnMsg.A.no(rowCnt).ctacPsnFirstNm_A)
    //                && ZYPCommonFunc.hasValue(scrnMsg.A.no(rowCnt).ctacPsnLastNm_A)) {
    //            ZYPEZDItemValueSetter.setValue(scrnMsg.sellToFirstRefCmntTxt, scrnMsg.A.no(rowCnt).ctacPsnFirstNm_A.getValue()
    //                    + NWAL1770Constant.SPACE
    //                    + scrnMsg.A.no(rowCnt).ctacPsnLastNm_A.getValue());
    //        }

    //    }
    //}
    // Add End 2017/10/13 QC#20246-1(L3 Sol#454)
    // Del End 2017/12/20 QC#20164(L3 Sol#356)

    // 2018/05/13 S21_NA#25251 Add Start
    private static boolean hasPriceChangeAuth(NWAL1770BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (S21StringUtil.isEquals(ALL_RGTN_AUTH, scrnMsg.Z.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }
    // 2018/05/13 S21_NA#25251 Add End

    // 2018/06/21 QC#14307 Add Start
    /**
     * Check Special Instruction Data
     * @param scrnMsg NWAL1770BMsg
     * @return boolean
     */
    public static boolean checkSpecialInstrctionData(NWAL1770BMsg scrnMsg) {
        
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_SP) || 
            ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SP) || 
            ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SP)) {

            return true;
        }
        return false;
    }

    /**
     * Get Param NMAL3300 for On Blur
     * @param scrnMsg NWAL1770BMsg
     * @return Param NMAL3300
     */
    public static Object[] getParamNMAL3300ForOnBlur(NWAL1770BMsg scrnMsg) {

        // 2018/07/10 S21_NA#26661,26713 Add Start
        String funcCatgId = scrnMsg.xxPopPrm_02.getValue();
        // 2018/07/10 S21_NA#26661,26713 Add End
        String trxRuleTp = scrnMsg.xxPopPrm_03.getValue();
        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, BIZ_ID);
        // 2018/07/10 S21_NA#26661,26713 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, funcCatgId);
        // 2018/07/10 S21_NA#26661,26713 Add End
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
        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, scrnMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, "");
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).dsAcctNum_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index).billToCustCd_V, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.V.no(index++).shipToCustCd_V, scrnMsg.shipToCustCd);
        }
        scrnMsg.V.setValidCount(index);
        paramList.add(scrnMsg.V);

        // 2018/07/10 S21_NA#26661,26713 Add Start
        // Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_05, scrnMsg.lineBizTpCd);
        paramList.add(scrnMsg.xxPopPrm_05);
        // 2018/07/10 S21_NA#26661,26713 Add End

        return paramList.toArray(new Object[0]);
    }
    // 2018/06/21 QC#14307 Add End

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /**
     * Check Item Line Warning for Event Handler
     * @param scrnMsg NWAL1770BMsg
     */
    public static void checkItemLineWarning(NWAL1770BMsg scrnMsg) {

        if (scrnMsg == null) {
            return;
        }

        if (!TAB_ITEMS.equals(scrnMsg.xxDplyTab.getValue())) {
            return;
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            scrnMsg.addCheckItem(itemLineMsg.mdseDescShortTxt_B);
            scrnMsg.addCheckItem(itemLineMsg.hazMatFlg_B);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * Check Item Line Warning for Event Handler
     * @param scrnMsg NWAL1770BMsg
     */
    public static void checkItemLineWarningOnlyBMsg(NWAL1770BMsg scrnMsg) {

        if (scrnMsg == null) {
            return;
        }

        if (!TAB_ITEMS.equals(scrnMsg.xxDplyTab.getValue())) {
            return;
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            String serNum = itemLineMsg.serNum_B.getValue();
            String dsContrNum = itemLineMsg.dsContrNum_B.getValue();
            if (!S21StringUtil.isEmpty(serNum) && !S21StringUtil.isEmpty(dsContrNum)) {
                String[] messageArgs = new String[]{serNum, dsContrNum};
                itemLineMsg.mdseDescShortTxt_B.setErrorInfo(2, NWAM8471W, messageArgs);
            }
            scrnMsg.addCheckItem(itemLineMsg.mdseDescShortTxt_B);
            if (ZYPConstant.FLG_ON_Y.equals(itemLineMsg.hazMatFlg_B.getValue())) {
                itemLineMsg.hazMatFlg_B.setErrorInfo(2, NWAM8473W);
            }
            scrnMsg.addCheckItem(itemLineMsg.hazMatFlg_B);
        }
        scrnMsg.putErrorScreen();
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
