/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0020.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import business.servlet.NSAL0020.NSAL0020BMsg;
import business.servlet.NSAL0020.NSAL0020_ABMsg;
import business.blap.NSAL0020.NSAL0020CMsg;
import static business.servlet.NSAL0020.constant.NSAL0020Constant.*;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/04/2013   Hitachi         T.Yonekura      Create          N/A
 * 08/28/2013   Hitachi         A.Kohinata      Update          QC1830
 * 2015/10/19   Hitachi         Y.Tsuchimoto    Update          N/A(No Mark up comment)
 * 2015/12/08   Hitachi         K.Kasai         Update          CSA QC1644
 * 2016/02/18   Hitachi         A.Kohinata      Update          CSA QC#956
 * 2016/03/02   Hitachi         K.Kasai         Update          CSA QC#3586
 * 2016/04/20   Hitachi         M.Gotou         Update          QC6749
 * 2016/05/12   Hitachi         M.Gotou         Update          QC7856
 * 2016/05/13   Hitachi         T.Tomita        Update          QC#4578
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/12/13   Hitachi         K.Ochiai        Update          QC#16563
 * 2018/06/01   Fujitsu         T.Murai         Update          QC#25167
 * 2019/01/16   Hitachi         E.Kameishi      Update          QC#29891
 *</pre>
 */
public class NSAL0020CommonLogic implements ZYPConstant {

    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * setRequestData_SearchCommon
     * @return bizMsg NSAL0020CMsg
     */
    public static NSAL0020CMsg setRequestData_SearchCommon() {

        NSAL0020CMsg bizMsg = new NSAL0020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * screenControlProcess
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0020BMsg
     */
    public static void screenControlProcess(EZDCommonHandler handler, NSAL0020BMsg scrnMsg) {

        initialize(handler, scrnMsg);
        screenControlByFuncId(handler, scrnMsg);
        screenControlByStatus(handler, scrnMsg);
        screenControlByItemValue(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.serNum);
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0020BMsg
     */
    public static void initialize(EZDCommonHandler handler, NSAL0020BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.setInputProtected(false);
        //scrnMsg.A.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL0020_ABMsg detail = scrnMsg.A.no(i);
            // START 2016/02/18 [QC#956, MOD]
            disableItems(detail.svcMachMstrStsDescTxt_A0, detail.xxComnScrColValTxt_AO, detail.ownrAcctNum_A0, detail.xxComnScrColValTxt_AB, detail.billToAcctNum_A0, detail.xxComnScrColValTxt_AC, detail.curLocAcctNum_A0);
            // END 2016/02/18 [QC#956, MOD]
            //TODO delete following statements after completion development of transition screen.
            disableLineButtons(handler, i, "OpenWin_NSAL0150");
        }

        // set button property
        // common button
        List<String[]> cmnEnableButtons = Arrays.asList(SAVE, SUBMIT, APPLY, APPROVE, REJECT, DOWNLOAD, DELETE, CLEAR, RESET, RETURN);
        for (String[] button : cmnEnableButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
            EZDGUIAttribute xxbtn = new EZDGUIAttribute(SCREEN_ID, button[0]);
            xxbtn.setVisibility(true);
            scrnMsg.addGUIAttribute(xxbtn);
        }

        // optional button
        // MOD START 2015/12/08 K.Kasai [QC1644]
//        List<String[]> optButtons = Arrays.asList(SEARCH, MACHINE_MASTER_MAINTENANCE, CONFIG_MAINTENANCE, CONTRACT_MAINTENANCE, REPAIR_HISTORY, DISPATCH_SUMMARY, INVOICE, BENCHMARK, VERSION_HISTORY);
        List<String[]> optButtons = Arrays.asList(SEARCH, MACHINE_MASTER_MAINTENANCE, CONFIG_MAINTENANCE, METER_ENTRY, CONTRACT_MAINTENANCE, REPAIR_HISTORY, DISPATCH_SUMMARY, INVOICE, BENCHMARK, VERSION_HISTORY);
        // ADD END 2015/12/08 K.Kasai [QC1644]
        for (String[] button : optButtons) {
            handler.setButtonProperties(button[0], button[1], button[2], BTN_INACTIVE, null);
        }

    }

    /**
     * addCheckItemForAllHeader
     * @param scrnMsg NSAL0020BMsg
     */
    public static void addCheckItemForAllHeader(NSAL0020BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);

        scrnMsg.addCheckItem(scrnMsg.serNum);
        // mod start 2016/04/20 CSA Defect#6749
        scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt_01);
        scrnMsg.addCheckItem(scrnMsg.svcSlnNm);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt_02);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt_03);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox);
        scrnMsg.addCheckItem(scrnMsg.istlDt_FR);
        scrnMsg.addCheckItem(scrnMsg.istlDt_TO);
        scrnMsg.addCheckItem(scrnMsg.svcMachMstrStsCd_PS);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem30Txt_04);
        // mod end 2016/04/20 CSA Defect#6749
        // START 2016/05/13 T.Tomita [QC#4578, MOD]
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_O);
        scrnMsg.addCheckItem(scrnMsg.ownrAcctNum);
//        scrnMsg.addCheckItem(scrnMsg.ownrLocNum);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_B);
        scrnMsg.addCheckItem(scrnMsg.billToAcctNum);
        scrnMsg.addCheckItem(scrnMsg.indBillToLocNum);
        scrnMsg.addCheckItem(scrnMsg.xxComnScrColValTxt_C);
        scrnMsg.addCheckItem(scrnMsg.curLocAcctNum);
        scrnMsg.addCheckItem(scrnMsg.indCurLocNum);
        // END 2016/05/13 T.Tomita [QC#4578, MOD]
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.mdseCd);
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // scrnMsg.addCheckItem(scrnMsg.mdseNm);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt);
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd_PS);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.custMachCtrlNum);
        // START 2016/12/13 K.Ochiai [QC#16563, MOD]
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        // END 2016/12/13 K.Ochiai [QC#16563, MOD]
        scrnMsg.addCheckItem(scrnMsg.dsContrNum);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnLastNm);
        scrnMsg.addCheckItem(scrnMsg.svcCtacTpCd_PS);
        scrnMsg.addCheckItem(scrnMsg.tocNm);
    }

    /**
     * Control items and buttons on the screen by Function ID.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0020BMsg
     * @return
     */
    private static void screenControlByFuncId(EZDCommonHandler handler, NSAL0020BMsg scrnMsg) {

        List<String> funcIdList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcIdList == null || funcIdList.isEmpty()) {
            throw new S21AbendException("You can't operate Machine Master Inquiry Screen(NSAL0020). UserID is -> " + getUserProfileService().getContextUserInfo().getUserId());
        }

        // START 2016/05/12 M.Gotou [QC7856, MOD]
        if (funcIdList.contains(FUNC_ID_T010)) {
            enableButtons(handler, CLEAR[0], DOWNLOAD[0], RETURN[0], SEARCH[0]);
            disableButtons(handler, NEW_MACHINE[0]);
        }
        if (funcIdList.contains(FUNC_ID_T020)) {
            enableButtons(handler, NEW_MACHINE[0]);
        }
        // END 2016/05/12 M.Gotou [QC7856, MOD]

    }

    /**
     * Control items and buttons on the screen by Status.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0020BMsg
     */
    private static void screenControlByStatus(EZDCommonHandler handler, NSAL0020BMsg scrnMsg) {
        S21UserProfileService profile = getUserProfileService();
        String userId = profile.getContextUserInfo().getUserId();

        if (profile.isBusinessAppGranted(userId, NSAL0010)) {
            enableButtons(handler, MACHINE_MASTER_MAINTENANCE[0]);
        } else {
            disableButtons(handler, MACHINE_MASTER_MAINTENANCE[0]);
        }

        if (profile.isBusinessAppGranted(userId, NSAL0040)) {
            enableButtons(handler, CONFIG_MAINTENANCE[0]);
        } else {
            disableButtons(handler, CONFIG_MAINTENANCE[0]);
        }
        // ADD START 2015/12/08 K.Kasai [QC1644]
        if (profile.isBusinessAppGranted(userId, NSAL0150)) {
            enableButtons(handler, METER_ENTRY[0]);
        } else {
            disableButtons(handler, METER_ENTRY[0]);
        }
        // ADD END 2015/12/08 K.Kasai [QC1644]

        if (profile.isBusinessAppGranted(userId, NSAL0080)) {
            enableButtons(handler, CONTRACT_MAINTENANCE[0]);
        } else {
            disableButtons(handler, CONTRACT_MAINTENANCE[0]);
        }

        // QC1830 Mod Start
        // if (profile.isBusinessAppGranted(userId, NSBL0060)) {
        // enableButtons(handler, REPAIR_HISTORY[0]);
        // } else {
        // disableButtons(handler, REPAIR_HISTORY[0]);
        // }
        enableButtons(handler, REPAIR_HISTORY[0]);
        // QC1830 Mod End

        // QC1830 Mod Start
        // if (profile.isBusinessAppGranted(userId, NSAL0140)) {
        // enableButtons(handler, DISPATCH_SUMMARY[0]);
        // } else {
        // disableButtons(handler, DISPATCH_SUMMARY[0]);
        // }
        // TODO
        disableButtons(handler, DISPATCH_SUMMARY[0]);
        // QC1830 Mod End

        // TODO need security Check
        disableButtons(handler, INVOICE[0]);
        disableButtons(handler, BENCHMARK[0]);
        disableButtons(handler, VERSION_HISTORY[0]);

    }

    /**
     * Control items and buttons on the screen by item value.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0020BMsg
     */
    private static void screenControlByItemValue(EZDCommonHandler handler, NSAL0020BMsg scrnMsg) {

        //disableItems(scrnMsg.firstOrgNm, scrnMsg.scdOrgNm, scrnMsg.thirdOrgNm, scrnMsg.frthOrgNm, scrnMsg.fifthOrgNm);

        if (scrnMsg.A.getValidCount() == 0) {
            // MOD START 2015/12/08 K.Kasai [QC1644]
//            disableButtons(handler, MACHINE_MASTER_MAINTENANCE[0], CONFIG_MAINTENANCE[0], CONTRACT_MAINTENANCE[0], REPAIR_HISTORY[0], DISPATCH_SUMMARY[0], INVOICE[0], BENCHMARK[0], VERSION_HISTORY[0]);
            disableButtons(handler, MACHINE_MASTER_MAINTENANCE[0], CONFIG_MAINTENANCE[0], METER_ENTRY[0], CONTRACT_MAINTENANCE[0], REPAIR_HISTORY[0], DISPATCH_SUMMARY[0], INVOICE[0], BENCHMARK[0], VERSION_HISTORY[0]);
            // MOD END 2015/12/08 K.Kasai [QC1644]
        }
    }

//    /**
//     * Set table background color.
//     * @param scrnMsg NSAL0020BMsg
//     */
//    private static void setTableBGColor(NSAL0020BMsg scrnMsg) {
//
//        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
//        if (scrnMsg.A.getValidCount() > 0) {
//            tblColor.clearRowsBG("A1", scrnMsg.A);
//            tblColor.clearRowsBG("A2", scrnMsg.A);
//            tblColor.setAlternateRowsBG("A1", scrnMsg.A);
//            tblColor.setAlternateRowsBG("A2", scrnMsg.A);
//        } else {
//            tblColor.clearRowsBG("A1", scrnMsg.A);
//            tblColor.clearRowsBG("A2", scrnMsg.A);
//        }
//    }

    /**
     * is Entered Check Box.
     * @param scrnMsg NSAL0020BMsg
     * @return flg bolean
     */
    public static boolean isEnteredCheckBox(NSAL0020BMsg scrnMsg) {

//        boolean hasValue = hasValue(scrnMsg.xxChkBox_01, scrnMsg.xxChkBox_02, scrnMsg.xxChkBox_03, scrnMsg.xxChkBox_04);
        boolean flg = true;
//
//        if (!hasValue) {
//            scrnMsg.xxChkBox_01.setErrorInfo(1, NSAL0020Constant.NSAM0015E);
//            scrnMsg.xxChkBox_02.setErrorInfo(1, NSAL0020Constant.NSAM0015E);
//            scrnMsg.xxChkBox_03.setErrorInfo(1, NSAL0020Constant.NSAM0015E);
//            scrnMsg.xxChkBox_04.setErrorInfo(1, NSAL0020Constant.NSAM0015E);
//            flg = false;
//        }
//
//        hasValue = hasValue(scrnMsg.xxChkBox_05, scrnMsg.xxChkBox_06);
//
//        if (!hasValue) {
//            scrnMsg.xxChkBox_05.setErrorInfo(1, NSAL0020Constant.NSAM0015E);
//            scrnMsg.xxChkBox_06.setErrorInfo(1, NSAL0020Constant.NSAM0015E);
//            flg = false;
//        }

        return flg;
    }

//    private static boolean hasValue(EZDBItem... items) {
//        boolean hasValue = false;
//
//        for (EZDBItem item : items) {
//            if (ZYPCommonFunc.hasValue(item)) {
//                hasValue = true;
//                break;
//            }
//        }
//        return hasValue;
//    }

    /**
     * Activate items
     * @param items EZDBItem
     */
    public static void enableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(false);
        }
    }

    /**
     * Activate buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String
     */
    public static void enableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, true);
        }
    }

    /**
     * Activate buttons for line
     * @param handler EZDCommonHandler
     * @param index int
     * @param btnHtmlNm String
     */
    public static void enableLineButtons(EZDCommonHandler handler, int index, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, index, true);
        }
    }

    /**
     * Protect items
     * @param items EZDBItem
     */
    public static void disableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(true);
        }
    }

    /**
     * Protect buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String
     */
    public static void disableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, false);
        }
    }

    /**
     * Protect buttons for line
     * @param handler EZDCommonHandler
     * @param index int
     * @param btnHtmlNm String
     */
    public static void disableLineButtons(EZDCommonHandler handler, int index, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, index, false);
        }
    }

    /**
     * set Where List
     * @param scrnMsg NSAL0020BMsg
     * @return whereList
     */
    public static List<Object[]> setWhereList(NSAL0020BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[4];
//
//        whereArray0[0] = "First Org Cd";
//        whereArray0[1] = "FIRST_ORG_CD";
//        if (ZYPCommonFunc.hasValue(scrnMsg.firstOrgCd)) {
//            whereArray0[2] = scrnMsg.firstOrgCd.getValue();
//        }
//        whereArray0[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[4];
//        whereArray1[0] = "Second Org Cd";
//        whereArray1[1] = "SCD_ORG_CD";
//        if (ZYPCommonFunc.hasValue(scrnMsg.scdOrgCd)) {
//            whereArray1[2] = scrnMsg.scdOrgCd.getValue();
//        }
//        whereArray1[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        Object[] whereArray2 = new Object[4];
//        whereArray2[0] = "Third Org Cd";
//        whereArray2[1] = "THIRD_ORG_CD";
//        if (ZYPCommonFunc.hasValue(scrnMsg.thirdOrgCd)) {
//            whereArray2[2] = scrnMsg.thirdOrgCd.getValue();
//        }
//        whereArray2[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray2);
//
//        Object[] whereArray3 = new Object[4];
//        whereArray3[0] = "Fourth Org Cd";
//        whereArray3[1] = "FRTH_ORG_CD";
//        if (ZYPCommonFunc.hasValue(scrnMsg.frthOrgCd)) {
//            whereArray3[2] = scrnMsg.frthOrgCd.getValue();
//        }
//        whereArray3[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray3);
//
//        Object[] whereArray4 = new Object[4];
//        whereArray4[0] = "Fifth Org Cd";
//        whereArray4[1] = "FIFTH_ORG_CD";
//        if (ZYPCommonFunc.hasValue(scrnMsg.fifthOrgCd)) {
//            whereArray4[2] = scrnMsg.fifthOrgCd.getValue();
//        }
//        whereArray4[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray4);

        return whereList;
    }

    /**
     * set Tbl Column List
     * @param scrnMsg NSAL0020BMsg
     * @return columnList
     */
    public static List<Object[]> setTblColumnList(NSAL0020BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "1st Cd";
        columnArray0[1] = "FIRST_ORG_CD";
        columnArray0[2] = BigDecimal.valueOf(5);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "1st Org Nm";
        columnArray1[1] = "FIRST_ORG_NM";
        columnArray1[2] = BigDecimal.valueOf(11);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "2nd Cd";
        columnArray2[1] = "SCD_ORG_CD";
        columnArray2[2] = BigDecimal.valueOf(5);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "2nd Org Nm";
        columnArray3[1] = "SCD_ORG_NM";
        columnArray3[2] = BigDecimal.valueOf(13);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "3rd Cd";
        columnArray4[1] = "THIRD_ORG_CD";
        columnArray4[2] = BigDecimal.valueOf(6);
        columnArray4[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "3rd Org Nm";
        columnArray5[1] = "THIRD_ORG_NM";
        columnArray5[2] = BigDecimal.valueOf(13);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "4th Cd";
        columnArray6[1] = "FRTH_ORG_CD";
        columnArray6[2] = BigDecimal.valueOf(6);
        columnArray6[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[4];
        columnArray7[0] = "4th Org Nm";
        columnArray7[1] = "FRTH_ORG_NM";
        columnArray7[2] = BigDecimal.valueOf(13);
        columnArray7[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray7);

        Object[] columnArray8 = new Object[4];
        columnArray8[0] = "5th Cd";
        columnArray8[1] = "FIFTH_ORG_CD";
        columnArray8[2] = BigDecimal.valueOf(7);
        columnArray8[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray8);

        Object[] columnArray9 = new Object[4];
        columnArray9[0] = "5th Org Nm";
        columnArray9[1] = "FIFTH_ORG_NM";
        columnArray9[2] = BigDecimal.valueOf(13);
        columnArray9[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray9);

        return columnList;
    }

    /**
     * set Sort List
     * @param scrnMsg NSAL0020BMsg
     * @return sortList
     */
    public static List<Object[]> setSortList(NSAL0020BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "FIRST_ORG_CD NULLS FIRST";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "SCD_ORG_CD NULLS FIRST";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        Object[] sortCondArray2 = new Object[2];
        sortCondArray2[0] = "THIRD_ORG_CD NULLS FIRST";
        sortCondArray2[1] = "";
        sortCondList.add(sortCondArray2);

        Object[] sortCondArray3 = new Object[2];
        sortCondArray3[0] = "FRTH_ORG_CD NULLS FIRST";
        sortCondArray3[1] = "";
        sortCondList.add(sortCondArray3);

        Object[] sortCondArray4 = new Object[2];
        sortCondArray4[0] = "FIFTH_ORG_CD NULLS FIRST";
        sortCondArray4[1] = "";
        sortCondList.add(sortCondArray4);

        return sortCondList;
    }

    /**
     * clear popup parameter value
     * @param scrnMsg NSAL0020BMsg
     */
    public static void clearPopupParam(NSAL0020BMsg scrnMsg) {
        // mod start 2016/03/02 CSA Defect#3586
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
        // mod end 2016/03/02 CSA Defect#3586
    }

    // ADD START 2018/06/01 T.Murai [QC#25167]
    /**
     * @param bMsg NSAL0020BMsg
     * @return boolean
     */
    public static boolean isErrorSearchCondition(NSAL0020BMsg bMsg) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(bMsg.serNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxComnScrColValTxt_O)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxScrItem30Txt_01)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.ownrAcctNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.svcSlnNm)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxComnScrColValTxt_B)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxScrItem30Txt_02)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.billToAcctNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxScrItem30Txt_03)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.indBillToLocNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxComnScrColValTxt_C)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.istlDt_FR)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.istlDt_TO)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.curLocAcctNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.svcMachMstrStsCd_PS)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.indCurLocNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.xxScrItem30Txt_04)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.t_MdlNm)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.custIssPoNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.mdseCd)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.dsContrNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.mdseDescShortTxt)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.ctacPsnLastNm)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.coaMdseTpCd_PS)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.svcCtacTpCd_PS)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.cpoOrdNum)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.tocNm)) {
            result = false;
        } else if (ZYPCommonFunc.hasValue(bMsg.custMachCtrlNum)) {
            result = false;
        } else {
            result = true;
        }
        if (result) {
            bMsg.setMessageInfo(NSAM0017E);
        }
        return result;
    }
    // ADD END 2018/06/01 T.Murai [QC#25167]
    // START 2019/01/16 E.Kameishi [QC#29891,ADD]
    public static boolean configNumFormatCheck(NSAL0020BMsg bMsg) {
        Pattern pattern = Pattern.compile("^[0-9%]*$");
        Matcher matcher = pattern.matcher(bMsg.xxScrItem30Txt_02.getValue());
        return matcher.matches();
    }
    // END 2019/01/16 E.Kameishi [QC#29891,ADD]
}
