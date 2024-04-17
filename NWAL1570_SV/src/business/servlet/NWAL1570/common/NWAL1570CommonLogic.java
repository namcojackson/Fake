/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570.common;

import static business.servlet.NWAL1570.constant.NWAL1570Constant.ALL_MODE;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.ALL_REF_AUTH;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.BIZ_ID;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.DPLY_BY_DEF;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.EVENT_NAME_SERACH_ORDER;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_0;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_1;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_10;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_13;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_2;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_20;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_3;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_30;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_33;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_4;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_5;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_50;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_6;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_7;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_8;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.IDX_9;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.NWAM0223E;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.NWAM0224E;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.NWAM0765E;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.NWAM0785E;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.NWAM0915E;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.PERCENT;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.REF_ONLY_SELF_RGTN_AUTH;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.REF_ONLY_TEAM_AUTH;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SCRN_ID_01;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SCRN_ID_02;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.SCRN_ID_OTH;
import static business.servlet.NWAL1570.constant.NWAL1570Constant.Y;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDCommonFunc.trimTail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBDateItem;
import parts.common.EZDBItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDFieldErrorException;
import business.blap.NWAL1570.NWAL1570CMsg;
import business.servlet.NWAL1570.NWAL1570BMsg;
import business.servlet.NWAL1570.NWAL1570_ABMsgArray;
import business.servlet.NWAL1570.constant.NWAL1570Constant.DPLY_MODE;
import business.servlet.NWAL1570.constant.NWAL1570Constant.HDR_STS;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NWAL1570CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2016/06/17   Fujitsu         K.Sato          Update          QC#7861
 * 2016/07/26   Fujitsu         K.Sato          Update          QC#11581
 * 2016/08/04   Fujitsu         R.Nakamura      Update          QC#9078
 * 2016/09/27   Fujitsu         K.Sato          Update          QC#13415
 * 2016/10/18   Fujitsu         K.Sato          Update          QC#15164
 * 2016/10/24   Fujitsu         S.Iidaka        Update          QC#14607
 * 2016/11/22   Fujitsu         K.Sato          Update          QC#15760
 * 2016/12/13   Fujitsu         T.Aoi           Update          QC#16591
 * 2017/06/22   Fujitsu         H.Sugawara      Update          QC#17893
 * 2017/09/19   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 * 2017/11/21   Fujitsu         T.Aoi           Update          QC#22550
 * 2018/02/22   Fujitsu         T.Aoi           Update          QC#21611
 * 2018/08/01   Fujitsu         T.Aoi           Update          QC#26304
 * 2018/10/09   Fujitsu         Mz.Takahashi    Update          QC#22893
 * 2018/12/07   Fujitsu         Mz.Takahashi    Update          QC#29024(RollBack QC#22893)
 * 2018/12/14   Fujitsu         Mz.Takahashi    Update          QC#29286
 * 2021/04/21   CITS            F.Deola         Update          QC#58707
 * 2022/04/18   CITS            J.Evangelista   Update          QC#59934
 *</pre>
 */
public class NWAL1570CommonLogic {

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    private static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    private static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    private static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    private static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    private static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    private static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    private static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    private static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    private static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    private static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

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
     * @param scrnMsg     NWAL1570BMsg
     * @param scrnAMsgAry NWAL1570_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NWAL1570BMsg scrnMsg, NWAL1570_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NWAL1570BMsg
     * @param scrnAMsgAry NWAL1570_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NWAL1570BMsg scrnMsg, NWAL1570_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        String scrnId = null;
        if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())) {
            scrnId = SCRN_ID_01;
        } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())) {
            scrnId = SCRN_ID_02;
        }

        S21TableColorController tblColor = new S21TableColorController(scrnId, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NWAL1570BMsg
     * @param scrnAMsgAry NWAL1570_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NWAL1570BMsg scrnMsg, NWAL1570_ABMsgArray scrnAMsgAry, String tblId) {

        String scrnId = null;
        if (RSLT_MODE.ORDER_INQUIRY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())) {
            scrnId = SCRN_ID_01;
        } else if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())) {
            scrnId = SCRN_ID_02;
        }

        S21TableColorController tblColor = new S21TableColorController(scrnId, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NWAL1570BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptNm_H1);

        scrnMsg.addCheckItem(scrnMsg.xxCpoOrdNumSrchTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxCpoOrdNumSrchTxt_H2);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxLeasePoNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSoldToAcctNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSoldToAcctCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSoldToLocCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxShipToAcctNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxShipToAcctCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxShipToLocCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxBillToAcctNmSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxBillToAcctCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxBillToLocCdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCoaExtnSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCoaBrSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSlsRepTocSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCpoSrcTpSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxDsBizLineSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxDsOrdCatgSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxDsOrdTpSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxDsOrdRsnSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCsmpContrNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxPrcContrNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdSrcRefNumSrchTxt);
        // 2018/08/01 QC#26304 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxAquNumSrchTxt);
        // 2018/08/01 QC#26304 Add End
        scrnMsg.addCheckItem(scrnMsg.xxMdseSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.mdseForSlsSmrySrchTxt);
        scrnMsg.addCheckItem(scrnMsg.zerothProdCtrlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.firstProdCtrlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.scdProdCtrlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.thirdProdCtrlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.frthProdCtrlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCoaProdSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCoaMdseTpSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxMdlSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSerNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.rtrnRsnCd);
        scrnMsg.addCheckItem(scrnMsg.xxLineCatgSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdLineSrcSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxRtlWhSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxRtlSwhSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxVndSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCpoOrdNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.soNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.invNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxDsContrNumSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxSvcConfigMstrSrchTxt);
        // QC#15760 Add Start
        scrnMsg.addCheckItem(scrnMsg.xxSvcMachMstrSrchTxt);
        // QC#15760 Add End
        scrnMsg.addCheckItem(scrnMsg.xxOrdTeamSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdZnSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.xxCratByUsrSrchTxt);
        scrnMsg.addCheckItem(scrnMsg.ordFromDt);
        // Add Start 2017/06/22 QC#17893
        checkFutureDate(scrnMsg, scrnMsg.ordFromDt);
        // Add End 2017/06/22 QC#17893
        scrnMsg.addCheckItem(scrnMsg.ordToDt);
        scrnMsg.addCheckItem(scrnMsg.xxBookFromDt);
        // Add Start 2017/06/22 QC#17893
        checkFutureDate(scrnMsg, scrnMsg.xxBookFromDt);
        // Add End 2017/06/22 QC#17893
        scrnMsg.addCheckItem(scrnMsg.xxBookToDt);
        scrnMsg.addCheckItem(scrnMsg.xxActlShipFromDt);
        // Add Start 2017/06/22 QC#17893
        checkFutureDate(scrnMsg, scrnMsg.xxActlShipFromDt);
        // Add End 2017/06/22 QC#17893
        scrnMsg.addCheckItem(scrnMsg.xxActlShipToDt);
        scrnMsg.addCheckItem(scrnMsg.invFromDt);
        // Add Start 2017/06/22 QC#17893
        checkFutureDate(scrnMsg, scrnMsg.invFromDt);
        // Add End 2017/06/22 QC#17893
        scrnMsg.addCheckItem(scrnMsg.invToDt);
        scrnMsg.addCheckItem(scrnMsg.xxOrdSrcImptFromDt);
        // Add Start 2017/06/22 QC#17893
        checkFutureDate(scrnMsg, scrnMsg.xxOrdSrcImptFromDt);
        // Add End 2017/06/22 QC#17893
        scrnMsg.addCheckItem(scrnMsg.xxOrdSrcImptToDt);

        scrnMsg.putErrorScreen();

    }

    // Add Start 2017/06/22 QC#17893
    /**
     * @param scrnMsg NWAL1570BMsg
     * @param fromDt EZDBDateItem
     */
    public static void checkFutureDate(NWAL1570BMsg scrnMsg, EZDBDateItem fromDt) {
        int errCode = fromDt.getErrorCode();

        if (hasValue(fromDt) && errCode == 0) {
            String strFromDate = fromDt.getValue();
            String strSalesDate = scrnMsg.slsDt.getValue();

            int cmpr = ZYPDateUtil.compare(strSalesDate, strFromDate);
            if (cmpr < 0) {
                fromDt.setErrorInfo(1, NWAM0765E, new String[] {"From Date", "Sales Date" });
            }
        }
    }
    // Add End 2017/06/22 QC#17893

    /**
     * initialize attribute of GUI on NWAL1570Scrn00.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiAttrScrn00(S21CommonHandler handler, NWAL1570BMsg scrnMsg) {

        // ++++++++++++++++++++++++++++++
        // + Order Criteria
        // ++++++++++++++++++++++++++++++
        guiAttrScrn00(scrnMsg);

        // ++++++++++++++++++++++++++++++
        // + Common Button Area
        // ++++++++++++++++++++++++++++++
        initGuiAttrScrn00Btn(handler);
    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn00.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiAttrScrn00Btn(S21CommonHandler handler) {

        // ++++++++++++++++++++++++++++++
        // + Common Button Area
        // ++++++++++++++++++++++++++++++
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        // START 2022/04/18 J.Evangelista [QC#59934,MOD]
//        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        // END 2022/04/18 J.Evangelista [QC#59934,MOD]
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn01/02.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiAttrScrnRsltBtn(S21CommonHandler handler, NWAL1570BMsg scrnMsg) {

        // ++++++++++++++++++++++++++++++
        // + Common Button Area
        // ++++++++++++++++++++++++++++++
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn00.
     * @param scrnMsg NWAL1570BMsg
     */
   public static void guiAttrScrn00(NWAL1570BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.H.no(i).ordHdrStsNm_HS.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            scrnMsg.L.no(i).ordLineStsNm_LS.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            scrnMsg.R.no(i).rtrnLineStsNm_RS.setInputProtected(true);
        }
    }

   /**
    * initialize attribute of GUI on NWAL1570Scrn00.
    * @param scrnMsg NWAL1570BMsg
    */
    public static void selectHdrSts(NWAL1570BMsg scrnMsg) {

        if (!hasValue(scrnMsg.xxOrdHdrStsAllSelFlg.getValue())) {
            scrnMsg.xxOrdHdrStsAllSelFlg.setValue(ZYPConstant.CHKBOX_ON_Y);
        } else {
            scrnMsg.xxOrdHdrStsAllSelFlg.setValue("");
        }

        for (int i = 0; i <  scrnMsg.H.length(); i++) {
            if (hasValue(scrnMsg.H.no(i).ordHdrStsNm_HS.getValue())) {
                scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setValue(scrnMsg.xxOrdHdrStsAllSelFlg.getValue());
            }
        }

    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn00.
     * @param scrnMsg NWAL1570BMsg
     */
    public static void selectLineSts(NWAL1570BMsg scrnMsg) {

        if (!hasValue(scrnMsg.xxLineStsAllSelFlg.getValue())) {
            scrnMsg.xxLineStsAllSelFlg.setValue(ZYPConstant.CHKBOX_ON_Y);
        } else {
            scrnMsg.xxLineStsAllSelFlg.setValue("");
        }

        for (int i = 0; i <  scrnMsg.L.length(); i++) {
            if (hasValue(scrnMsg.L.no(i).ordLineStsNm_LS.getValue())) {
                scrnMsg.L.no(i).xxLineStsSelFlg_LS.setValue(scrnMsg.xxLineStsAllSelFlg.getValue());
            }
        }

    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn00.
     * @param scrnMsg NWAL1570BMsg
     */
    public static void selectRtrnLineSts(NWAL1570BMsg scrnMsg) {

        if (!hasValue(scrnMsg.xxRtrnStsAllSelFlg.getValue())) {
            scrnMsg.xxRtrnStsAllSelFlg.setValue(ZYPConstant.CHKBOX_ON_Y);
        } else {
            scrnMsg.xxRtrnStsAllSelFlg.setValue("");
        }

        for (int i = 0; i <  scrnMsg.R.length(); i++) {
            if (hasValue(scrnMsg.R.no(i).rtrnLineStsNm_RS.getValue())) {
                scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.setValue(scrnMsg.xxRtrnStsAllSelFlg.getValue());
            }
        }

    }

    /**
     * Set Screen Protect By Authority
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL1570BMsg
     */
    public static void setProtectByAuthority(S21CommonHandler handler, NWAL1570BMsg scrnMsg) {

        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            funcList.add(scrnMsg.F.no(i).xxFuncId.getValue());
        }

        // Team/Zone
        if (funcList.contains(ALL_REF_AUTH)) {

            handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, true);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            scrnMsg.xxOrdTeamSrchTxt_LK.setInputProtected(false);
            scrnMsg.xxOrdZnSrchTxt_LK.setInputProtected(false);
            scrnMsg.xxOrdTeamSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxOrdZnSrchTxt_LK.setValue(ZYPConstant.FLG_ON_Y);

        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {

            scrnMsg.xxOrdTeamSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdTeamSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdTeamSrchTxt_LK.clear();
            scrnMsg.xxOrdZnSrchTxt_LK.clear();

            if (hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, true);
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            } else {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, false);
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            }

        } else if (funcList.contains(REF_ONLY_SELF_RGTN_AUTH)) {

            scrnMsg.xxOrdTeamSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt.setInputProtected(true);
            scrnMsg.xxCratByUsrSrchTxt.setInputProtected(true);
            scrnMsg.xxOrdTeamSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdZnSrchTxt_LK.setInputProtected(true);
            scrnMsg.xxOrdTeamSrchTxt_LK.clear();
            scrnMsg.xxOrdZnSrchTxt_LK.clear();

            if (hasValue(scrnMsg.xxOrdTeamSrchTxt)) {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, true);
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            } else {
                handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, false);
                handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            }

        } else {
            handler.setButtonEnabled(EVENT_NAME_SERACH_ORDER, false);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        }

    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn01 or 02.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiAttrScrnRslt(S21CommonHandler handler, NWAL1570BMsg scrnMsg) {

        // ++++++++++++++++++++++++++++++
        // + Result Area
        // ++++++++++++++++++++++++++++++
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxDplyByItemNm_01.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyByItemNm_02.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyByItemNm_03.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyOrdInqRefNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).srcRefOrCpoOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDplyOrdLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCpoLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsCpoLineSubNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxHdrDplyStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxLineDplyStsNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            // 2018/12/14 QC#29024 Add Start
            scrnMsg.A.no(i).rtlWhDescTxt_A1.setInputProtected(true);
            // 2018/12/14 QC#29024 Add End
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).soldToCustAcctCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).soldToCustAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).soldToCustLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustAcctCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).billToCustAcctCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).billToCustAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).billToCustLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCpoOrdDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBookDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rddDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).psdDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).pddDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).actlShipDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).istlDt_A1.setInputProtected(true);
            // 2018/12/07 QC#29024 Mod Start
            // 2018/10/10 QC#22893 Del Start
            scrnMsg.A.no(i).invDt_A1.setInputProtected(true);
            // 2018/10/10 QC#22893 Del End
            // 2018/12/07 QC#29024 Mod End
            scrnMsg.A.no(i).dsOrdCatgCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdRsnCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdRsnDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).cpoSrcTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).cpoSrcTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordSrcRefNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineCatgCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordLineSrcCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordLineSrcNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcCatgCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).custIssPoNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).leaseCmpyPoNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaExtnCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaExtnDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaBrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaBrDescTxt_A1.setInputProtected(true);
            // 2018/02/22 QC#21611 Mod Start
            //scrnMsg.A.no(i).slsRepTocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).slsRepPsnNum_A1.setInputProtected(true);
            // 2018/02/22 QC#21611 Mod End
            scrnMsg.A.no(i).tocNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).csmpContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcContrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).zerothProdCtrlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).zerothProdCtrlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).firstProdCtrlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).firstProdCtrlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).scdProdCtrlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).scdProdCtrlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).thirdProdCtrlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).thirdProdCtrlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).frthProdCtrlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).frthProdCtrlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdlId_A1.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaProdDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).coaMdseTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsContrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).prchReqNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).poNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).soNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).invNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).invtyLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).vndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dplyVndNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).lineBizTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).lineBizTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineCatgCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).aquNum_A1.setInputProtected(true); // 2018/08/01 QC#26304 Add

            scrnMsg.A.no(i).xxPendImptSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxEntSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDiHldSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxVldSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPrftSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCrHldSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxSplyAbuseSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendReSubmtSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBookSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAwaitDropShipSmryAmt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Add
            //scrnMsg.A.no(i).xxSoCancSmryAmt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Del
            scrnMsg.A.no(i).xxPendReAllocSmryAmt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Add
            scrnMsg.A.no(i).xxPoCancSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendFuflSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendAllocSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBoSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendPickSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDelyToShopSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInShopConfigSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendShipSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxShipSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendDelyConfSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendIstlSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxOnLoanSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxWaitRcptSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendRtrnSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendInspSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxRwsCancSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPrtlRcvSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendInvSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBllgHldSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendDlrIstlSmryAmt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Add
            scrnMsg.A.no(i).xxShipCloSmryAmt_A1.setInputProtected(true);
            // QC#11581 DEL Start
//            scrnMsg.A.no(i).xxInvdSmryAmt_A1.setInputProtected(true);
            // QC#11581 DEL End
            scrnMsg.A.no(i).xxCloSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCloLoanRtrnSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCloLoanSoldSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCancSmryAmt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendImptSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxEntSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDiHldSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxVldSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPrftSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCrHldSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxSplyAbuseSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendReSubmtSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBookSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAwaitDropShipSmryCnt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Add
            //scrnMsg.A.no(i).xxSoCancSmryCnt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Del
            scrnMsg.A.no(i).xxPendReAllocSmryCnt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Add
            scrnMsg.A.no(i).xxPoCancSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendFuflSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendAllocSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBoSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendPickSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxDelyToShopSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxInShopConfigSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendShipSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxShipSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendDelyConfSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendIstlSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxOnLoanSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxWaitRcptSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendRtrnSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendInspSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxRwsCancSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPrtlRcvSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendInvSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxBllgHldSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPendDlrIstlSmryCnt_A1.setInputProtected(true); // 2017/11/21 QC#22550 Add
            scrnMsg.A.no(i).xxShipCloSmryCnt_A1.setInputProtected(true);
            // QC#11581 DEL Start
//            scrnMsg.A.no(i).xxInvdSmryCnt_A1.setInputProtected(true);
            // QC#11581 DEL End
            scrnMsg.A.no(i).xxCloSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCloLoanRtrnSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCloLoanSoldSmryCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxCancSmryCnt_A1.setInputProtected(true);
        }

        // ++++++++++++++++++++++++++++++
        // + Common Button Area
        // ++++++++++++++++++++++++++++++
        initGuiAttrScrnRsltBtn(handler, scrnMsg);

    }

    /**
     * initialize attribute of GUI on NWAL1570Scrn.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiAttrScrn01(S21CommonHandler handler, NWAL1570BMsg scrnMsg) {
    }

    /**
     * initialize value of GUI on NWAL1570Scrn00.
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiValueScrn00(NWAL1570BMsg scrnMsg) {

        // Saved Search Options
        scrnMsg.srchOptPk_H1.clear();
        // Search Option Name
        scrnMsg.srchOptNm_H1.clear();

        //++++++++++++++++++++++++++++++++++++++++
        //+++++ Header Search Criteria
        //++++++++++++++++++++++++++++++++++++++++
        //Order#
        scrnMsg.xxCpoOrdNumSrchTxt_H1.clear();
        //Orig. Order#
        scrnMsg.xxCpoOrdNumSrchTxt_H2.clear();
        //Cust. PO#
        scrnMsg.custIssPoNumSrchTxt.clear();
        //Lease PO#
        scrnMsg.xxLeasePoNumSrchTxt.clear();
        //Sold To
        scrnMsg.xxSoldToAcctNmSrchTxt.clear();
        scrnMsg.xxSoldToAcctCdSrchTxt.clear();
        scrnMsg.xxSoldToLocCdSrchTxt.clear();
        //Ship To
        scrnMsg.xxShipToAcctNmSrchTxt.clear();
        scrnMsg.xxShipToAcctCdSrchTxt.clear();
        scrnMsg.xxShipToLocCdSrchTxt.clear();
        //Bill To
        scrnMsg.xxBillToAcctNmSrchTxt.clear();
        scrnMsg.xxBillToAcctCdSrchTxt.clear();
        scrnMsg.xxBillToLocCdSrchTxt.clear();
        //Business Unit
        scrnMsg.xxCoaExtnSrchTxt.clear();
        //Branch
        scrnMsg.xxCoaBrSrchTxt.clear();
        //Sales Rep
        scrnMsg.xxSlsRepTocSrchTxt.clear();
        //Order Source
        scrnMsg.xxCpoSrcTpSrchTxt.clear();
        //LOB
        scrnMsg.xxDsBizLineSrchTxt.clear();
        //Order Category
        scrnMsg.xxDsOrdCatgSrchTxt.clear();
        //Order Reason
        scrnMsg.xxDsOrdTpSrchTxt.clear();
        //Sub Reason
        scrnMsg.xxDsOrdRsnSrchTxt.clear();
        //CSMP#
        scrnMsg.xxCsmpContrNumSrchTxt.clear();
        //Price Contract#
        scrnMsg.xxPrcContrNumSrchTxt.clear();
        //Import Source#
        scrnMsg.xxOrdSrcRefNumSrchTxt.clear();
        // 2018/08/01 QC#26304 Add Start
        //Acquisition#
        scrnMsg.xxAquNumSrchTxt.clear();
        // 2018/08/01 QC#26304 Add End

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Line Search Criteria
        //++++++++++++++++++++++++++++++++++++++++
        //Item Name
        scrnMsg.xxMdseSrchTxt.clear();
        //Item Code
        scrnMsg.mdseForSlsSmrySrchTxt.clear();
        //PL Group
        scrnMsg.zerothProdCtrlSrchTxt.clear();
        //PL1
        scrnMsg.firstProdCtrlSrchTxt.clear();
        //PL2
        scrnMsg.scdProdCtrlSrchTxt.clear();
        //PL3
        scrnMsg.thirdProdCtrlSrchTxt.clear();
        //PL4
        scrnMsg.frthProdCtrlSrchTxt.clear();
        //COA Product
        scrnMsg.xxCoaProdSrchTxt.clear();
        //COA MDSE
        scrnMsg.xxCoaMdseTpSrchTxt.clear();
        //Model
        scrnMsg.xxMdlSrchTxt.clear();
        //Serial Number
        scrnMsg.xxSerNumSrchTxt.clear();
        //Return Reason
        scrnMsg.rtrnRsnCd.clear();
        //Line Category
        scrnMsg.xxLineCatgSrchTxt.clear();
        //Line Source
        scrnMsg.xxOrdLineSrcSrchTxt.clear();
        //WH
        scrnMsg.xxRtlWhSrchTxt.clear();
        //SUB WH
        scrnMsg.xxRtlSwhSrchTxt.clear();
        //PO Vendor
        scrnMsg.xxVndSrchTxt.clear();
        //P/O#
        scrnMsg.xxCpoOrdNumSrchTxt.clear();
        //S/O#
        scrnMsg.soNumSrchTxt.clear();
        //Invoice#
        scrnMsg.invNumSrchTxt.clear();
        //Contract#
        scrnMsg.xxDsContrNumSrchTxt.clear();
        //Config#
        scrnMsg.xxSvcConfigMstrSrchTxt.clear();
        // QC#15760 Add Start
        //Install Base ID
        scrnMsg.xxSvcMachMstrSrchTxt.clear();
        // QC#15760 Add End
        //++++++++++++++++++++++++++++++++++++++++
        //+++++Order Team
        //++++++++++++++++++++++++++++++++++++++++
        List<String> funcList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            funcList.add(scrnMsg.F.no(i).xxFuncId.getValue());
        }
        if (funcList.contains(ALL_REF_AUTH)) {
            //Team
            scrnMsg.xxOrdTeamSrchTxt.clear();
            //Zone
            scrnMsg.xxOrdZnSrchTxt.clear();
            //Created By
            scrnMsg.xxCratByUsrSrchTxt.clear();
        } else if (funcList.contains(REF_ONLY_TEAM_AUTH)) {
            //Created By
            scrnMsg.xxCratByUsrSrchTxt.clear();
        }

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Mode
        //++++++++++++++++++++++++++++++++++++++++
        //Mode
        scrnMsg.xxRsltModeCd.clear();
        //Include Import
        clearIncludeImport(scrnMsg);
        //Sales
        scrnMsg.xxOnlySlsOrdFlg.clear();

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Status
        //++++++++++++++++++++++++++++++++++++++++
        clearStatus(scrnMsg);
        // START 2021/04/21 F.Deola [QC#58707, ADD]
        selectAllHeaderStatus(scrnMsg);
        // END 2021/04/21 F.Deola [QC#58707, ADD]

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Date Criteria
        //++++++++++++++++++++++++++++++++++++++++
        clearDateCriteria(scrnMsg);

        //++++++++++++++++++++++++++++++++++++++++
        //+++++Display Option
        //++++++++++++++++++++++++++++++++++++++++
        //Display By
        //Display By - 1
        scrnMsg.dplyBy01ItemNm.clear();
        //Display By - 2
        scrnMsg.dplyBy02ItemNm.clear();
        //Display By - 3
        scrnMsg.dplyBy03ItemNm.clear();
        //Display Mode
        scrnMsg.grpByDnldCd.clear();
        //Real Time Inquiry
        // START 2021/04/21 F.Deola [QC#58707, MOD]
        // 2018/12/14 QC#29024 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.xxPgFlg, scrnMsg.xxPgFlg_DF);
        scrnMsg.xxPgFlg.clear();
        // 2018/12/14 QC#29024 Mod End
        // END 2021/04/21 F.Deola [QC#58707, MOD]

        //Aging
        scrnMsg.ordAgingBcktDescTxt.clear();

        // Result Mode
        scrnMsg.xxRsltModeCd.setValue(RSLT_MODE.ORDER_INQUIRY.getRsltModeCd());
        // Display By
        scrnMsg.dplyBy01ItemNm.setValue(DPLY_BY_DEF);
        // Display Mode
        scrnMsg.grpByDnldCd.setValue(DPLY_MODE.LINE.getDplyModeCd());
    }

    /**
     * setAppFracDigit
     * @param scrnMsg NWAL1570BMsg
     */
    public static void setAppFracDigit(NWAL1570BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxTotAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendImptSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxEntSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxDiHldSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxVldSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPrftSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxCrHldSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxSplyAbuseSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendReSubmtSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxBookSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxAwaitDropShipSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); // 2017/11/21 QC#22550 Add
            //scrnMsg.A.no(i).xxSoCancSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); // 2017/11/21 QC#22550 Del
            scrnMsg.A.no(i).xxPendReAllocSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); // 2017/11/21 QC#22550 Add
            scrnMsg.A.no(i).xxPoCancSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendFuflSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendAllocSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxBoSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendPickSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxDelyToShopSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxInShopConfigSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendShipSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxShipSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendDelyConfSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendIstlSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxOnLoanSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxWaitRcptSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendRtrnSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendInspSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxRwsCancSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPrtlRcvSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendInvSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxBllgHldSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxPendDlrIstlSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt()); // 2017/11/21 QC#22550 Add
            scrnMsg.A.no(i).xxShipCloSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            // QC#11581 DEL Start
//            scrnMsg.A.no(i).xxInvdSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            // QC#11581 DEL End
            scrnMsg.A.no(i).xxCloSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxCloLoanRtrnSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxCloLoanSoldSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxCancSmryAmt_A1.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }
    }

    /**
     * all Open Order attribute of GUI on NWAL1570Scrn00
     * @param scrnMsg NWAL1570BMsg
     * @param attrFlg boolean
     */
    public static void setStatusGuiAttrScrn00(NWAL1570BMsg scrnMsg, boolean attrFlg) {

        scrnMsg.xxOrdHdrStsAllSelFlg.setInputProtected(attrFlg);
        scrnMsg.xxLineStsAllSelFlg.setInputProtected(attrFlg);
        scrnMsg.xxRtrnStsAllSelFlg.setInputProtected(attrFlg);
        scrnMsg.xxInclPendImptOrdFlg.setInputProtected(attrFlg);

        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setInputProtected(attrFlg);
        }
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            scrnMsg.L.no(i).xxLineStsSelFlg_LS.setInputProtected(attrFlg);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.setInputProtected(attrFlg);
        }

    }

    /**
     * clearStatus
     * @param scrnMsg NWAL1570BMsg
     */
    public static void clearStatus(NWAL1570BMsg scrnMsg) {

        //Header Status
        scrnMsg.xxOrdHdrStsAllSelFlg.clear();
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.clear();
        }
        //Line Status
        scrnMsg.xxLineStsAllSelFlg.clear();
        for (int i = 0; i < scrnMsg.L.length(); i++) {
            scrnMsg.L.no(i).xxLineStsSelFlg_LS.clear();
        }
        //Return Line Status
        scrnMsg.xxRtrnStsAllSelFlg.clear();
        for (int i = 0; i < scrnMsg.R.length(); i++) {
            scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.clear();
        }
        //Include Pending Import Order
        scrnMsg.xxInclPendImptOrdFlg.clear();
        //All Open Order
        scrnMsg.xxAllOpenOrdFlg.clear();

        setStatusGuiAttrScrn00(scrnMsg, false);

    }

    // START 2021/04/21 F.Deola [QC#58707, ADD]
    /**
     * selectAllHeaderStatus
     * @param scrnMsg NWAL1570BMsg
     */
    public static void selectAllHeaderStatus(NWAL1570BMsg scrnMsg) {

        scrnMsg.xxOrdHdrStsAllSelFlg.setValue(ZYPConstant.CHKBOX_ON_Y);
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setValue(ZYPConstant.CHKBOX_ON_Y);
        }
    }
    // END 2021/04/21 F.Deola [QC#58707, MOD]

    /**
     * clearDateCriteria
     * @param scrnMsg NWAL1570BMsg
     */
    public static void clearDateCriteria(NWAL1570BMsg scrnMsg) {

        //Order Date
        scrnMsg.ordFromDt.clear();
        scrnMsg.ordToDt.clear();
        //Booked Date
        scrnMsg.xxBookFromDt.clear();
        scrnMsg.xxBookToDt.clear();
        //Shipped Date
        scrnMsg.xxActlShipFromDt.clear();
        scrnMsg.xxActlShipToDt.clear();
        //Invoice Date
        scrnMsg.invFromDt.clear();
        scrnMsg.invToDt.clear();
        //Import Date
        scrnMsg.xxOrdSrcImptFromDt.clear();
        scrnMsg.xxOrdSrcImptToDt.clear();
    }

    /**
     * setStatusByAllOpenOrder
     * @param scrnMsg NWAL1570BMsg
     */
    public static void setStatusByAllOpenOrder(NWAL1570BMsg scrnMsg) {

        clearStatus(scrnMsg);
        scrnMsg.xxAllOpenOrdFlg.setValue(ZYPConstant.CHKBOX_ON_Y);

        //Header Status
        scrnMsg.xxOrdHdrStsAllSelFlg.clear();
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            if (HDR_STS.CANC.getHdrStsNm().equals(scrnMsg.H.no(i).ordHdrStsNm_HS.getValue())
                    || HDR_STS.CLO.getHdrStsNm().equals(scrnMsg.H.no(i).ordHdrStsNm_HS.getValue())) {
                scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.clear();
            } else {
                scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setValue(ZYPConstant.CHKBOX_ON_Y);
            }
        }
    }

    /**
     * clearIncludeImport
     * @param scrnMsg NWAL1570BMsg
     */
    public static void clearIncludeImport(NWAL1570BMsg scrnMsg) {

        scrnMsg.xxInclImptOrdFlg.clear();
    }

    /**
     * initialize Value of GUI on NWAL1570ScrnRslt.
     * @param scrnMsg NWAL1570BMsg
     */
    public static void initGuiValueScrnRslt(NWAL1570BMsg scrnMsg) {
    }

    /**
     * create NWAL1570CMsg to call "Search Function BLAP".
     *
     * <pre>
     * setBusinessID( "NWAL1570" );
     * setFunctionCode( "20" );
     * </pre>
     *
     * @return NWAL1570CMsg
     */
    public static NWAL1570CMsg createSearchCMsg() {
        NWAL1570CMsg cMsg = new NWAL1570CMsg();
        cMsg.setBusinessID(BIZ_ID);
        cMsg.setFunctionCode("20");
        return cMsg;
    }

    /**
     * setDisplayByItem
     * @param scrnMsg NWAL1570BMsg
     */
   public static void setDisplayByItem(NWAL1570BMsg scrnMsg) {

        // Case : When selected display by_02
        if (hasValue(scrnMsg.dplyBy02ItemNm.getValue())) {
            if (hasValue(scrnMsg.dplyBy03ItemNm.getValue())) {
                // 1 = 2
                if (scrnMsg.dplyBy01ItemNm.getValue().equals(scrnMsg.dplyBy02ItemNm.getValue())) {
                    // 1 = 2 = 3
                    if (scrnMsg.dplyBy02ItemNm.getValue().equals(scrnMsg.dplyBy03ItemNm.getValue())) {
                        scrnMsg.dplyBy02ItemNm.clear();
                        scrnMsg.dplyBy03ItemNm.clear();
                        // 1 = 2 <> 3
                    } else {
                        scrnMsg.dplyBy02ItemNm.setValue(scrnMsg.dplyBy03ItemNm.getValue());
                        scrnMsg.dplyBy03ItemNm.clear();
                    }
                    // 1 <> 2
                } else {
                    // 1 <> 2 = 3
                    if (scrnMsg.dplyBy02ItemNm.getValue().equals(scrnMsg.dplyBy03ItemNm.getValue())) {
                        scrnMsg.dplyBy02ItemNm.setValue(scrnMsg.dplyBy03ItemNm.getValue());
                        scrnMsg.dplyBy03ItemNm.clear();
                        // 1 <> 2 , 1 = 3
                    } else if (scrnMsg.dplyBy01ItemNm.getValue().equals(scrnMsg.dplyBy03ItemNm.getValue())) {
                        scrnMsg.dplyBy03ItemNm.clear();
                        // 1 <> 2 <> 3
                    }
                }
            } else {
                // 1 = 2
                if (scrnMsg.dplyBy01ItemNm.getValue().equals(scrnMsg.dplyBy02ItemNm.getValue())) {
                    scrnMsg.dplyBy02ItemNm.clear();
                }
            }

            // Case : When selected display by_03 and not selected
            // display by_02
        } else {
            if (hasValue(scrnMsg.dplyBy03ItemNm.getValue())) {
                // 1 = 3
                if (scrnMsg.dplyBy01ItemNm.getValue().equals(scrnMsg.dplyBy03ItemNm.getValue())) {
                    scrnMsg.dplyBy03ItemNm.clear();
                    // 1 <> 3
                } else {
                    scrnMsg.dplyBy02ItemNm.setValue(scrnMsg.dplyBy03ItemNm.getValue());
                    scrnMsg.dplyBy03ItemNm.clear();
                }
            }
        }
    }

   /**
    * check inquiry criteria values on NWAL1570Scrn00.
    *
    * <pre>
    * 1. check inquiry status check-boxes.
    * 2. check EZDeveloper item attribute values.
    * 3. check "Ordered Date".
    *
    * If has any errors, throws EZDFieldErrorException and view NWAL1570Scrn00 with error message.
    * </pre>
    *
    * @param scrnMsg NWAL1570BMsg
    * @throws EZDFieldErrorException
    */
   public static void checkInqCriteria(NWAL1570BMsg scrnMsg) {

       // 1. check EZDeveloper item attribute values.
       addCheckItemBizLayerErr(scrnMsg);

       // 2. check search criteria.
       checkSearchCriteria(scrnMsg);

       // 3. check inquiry status check-boxes.
       checkInqStsChkBoxes(scrnMsg);

       // 4. check Date.
       checkDate(scrnMsg, scrnMsg.ordFromDt, scrnMsg.ordToDt);
       checkDate(scrnMsg, scrnMsg.xxBookFromDt, scrnMsg.xxBookToDt);
       checkDate(scrnMsg, scrnMsg.xxActlShipFromDt, scrnMsg.xxActlShipToDt);
       checkDate(scrnMsg, scrnMsg.invFromDt, scrnMsg.invToDt);
       checkDate(scrnMsg, scrnMsg.xxOrdSrcImptFromDt, scrnMsg.xxOrdSrcImptToDt);

   }

private static void checkDate(NWAL1570BMsg scrnMsg, EZDBDateItem fromDt, EZDBDateItem toDt) {

    if (hasValue(fromDt) && hasValue(toDt)) {
        int compareRes = ZYPDateUtil.compare(fromDt.getValue(), toDt.getValue());
        if (compareRes == 1) {

            String[] msgParam = {fromDt.getNameForMessage(), toDt.getNameForMessage() };

            fromDt.setErrorInfo(1, NWAM0223E, msgParam);
            scrnMsg.addCheckItem(fromDt);

            toDt.setErrorInfo(1, NWAM0223E, msgParam);
            scrnMsg.addCheckItem(toDt);

            scrnMsg.putErrorScreen();
        }

    }
}

   private static void checkSearchCriteria(NWAL1570BMsg scrnMsg) {

       boolean hasChecked = false;

       // QC#15164 Add Start
       if (S21StringUtil.isEquals(RSLT_MODE.STATUS_SUMMARY.getRsltModeCd(), scrnMsg.xxRsltModeCd.getValue())) {
           return;
       }
       // QC#15164 Add End

       if (hasValue(scrnMsg.xxCpoOrdNumSrchTxt_H1)
               || hasValue(scrnMsg.xxCpoOrdNumSrchTxt_H2)
               || hasValue(scrnMsg.custIssPoNumSrchTxt)
               || hasValue(scrnMsg.xxLeasePoNumSrchTxt)
               || hasValue(scrnMsg.xxSoldToAcctNmSrchTxt)
               || hasValue(scrnMsg.xxSoldToAcctCdSrchTxt)
               || hasValue(scrnMsg.xxSoldToLocCdSrchTxt)
               || hasValue(scrnMsg.xxShipToAcctNmSrchTxt)
               || hasValue(scrnMsg.xxShipToAcctCdSrchTxt)
               || hasValue(scrnMsg.xxShipToLocCdSrchTxt)
               || hasValue(scrnMsg.xxBillToAcctNmSrchTxt)
               || hasValue(scrnMsg.xxBillToAcctCdSrchTxt)
               || hasValue(scrnMsg.xxBillToLocCdSrchTxt)
               || hasValue(scrnMsg.xxCoaExtnSrchTxt)
               || hasValue(scrnMsg.xxCoaBrSrchTxt)
               || hasValue(scrnMsg.xxSlsRepTocSrchTxt)
               || hasValue(scrnMsg.xxCpoSrcTpSrchTxt)
               || hasValue(scrnMsg.xxDsBizLineSrchTxt)
               || hasValue(scrnMsg.xxDsOrdCatgSrchTxt)
               || hasValue(scrnMsg.xxDsOrdTpSrchTxt)
               || hasValue(scrnMsg.xxDsOrdRsnSrchTxt)
               || hasValue(scrnMsg.xxCsmpContrNumSrchTxt)
               || hasValue(scrnMsg.xxPrcContrNumSrchTxt)
               || hasValue(scrnMsg.xxOrdSrcRefNumSrchTxt)
               // 2018/08/01 QC#26304 Add Start
               || hasValue(scrnMsg.xxAquNumSrchTxt)
               // 2018/08/01 QC#26304 Add End
               || hasValue(scrnMsg.xxMdseSrchTxt)
               || hasValue(scrnMsg.mdseForSlsSmrySrchTxt)
               || hasValue(scrnMsg.zerothProdCtrlSrchTxt)
               || hasValue(scrnMsg.firstProdCtrlSrchTxt)
               || hasValue(scrnMsg.scdProdCtrlSrchTxt)
               || hasValue(scrnMsg.thirdProdCtrlSrchTxt)
               || hasValue(scrnMsg.frthProdCtrlSrchTxt)
               || hasValue(scrnMsg.xxCoaProdSrchTxt)
               || hasValue(scrnMsg.xxCoaMdseTpSrchTxt)
               || hasValue(scrnMsg.xxMdlSrchTxt)
               || hasValue(scrnMsg.xxSerNumSrchTxt)
               || hasValue(scrnMsg.rtrnRsnCd)
               || hasValue(scrnMsg.xxLineCatgSrchTxt)
               || hasValue(scrnMsg.xxOrdLineSrcSrchTxt)
               || hasValue(scrnMsg.xxRtlWhSrchTxt)
               || hasValue(scrnMsg.xxRtlSwhSrchTxt)
               || hasValue(scrnMsg.xxVndSrchTxt)
               || hasValue(scrnMsg.xxCpoOrdNumSrchTxt)
               || hasValue(scrnMsg.soNumSrchTxt)
               || hasValue(scrnMsg.invNumSrchTxt)
               || hasValue(scrnMsg.xxDsContrNumSrchTxt)
               || hasValue(scrnMsg.xxSvcConfigMstrSrchTxt)
               // QC#15760 Add Start
               || hasValue(scrnMsg.xxSvcMachMstrSrchTxt)
               // QC#15760 Add End
               || hasValue(scrnMsg.xxOrdTeamSrchTxt)
               || hasValue(scrnMsg.xxOrdZnSrchTxt)
               || hasValue(scrnMsg.xxCratByUsrSrchTxt)
               || hasValue(scrnMsg.ordFromDt)
               || hasValue(scrnMsg.ordToDt)
               || hasValue(scrnMsg.xxBookFromDt)
               || hasValue(scrnMsg.xxBookToDt)
               || hasValue(scrnMsg.xxActlShipFromDt)
               || hasValue(scrnMsg.xxActlShipToDt)
               || hasValue(scrnMsg.invFromDt)
               || hasValue(scrnMsg.invToDt)
               || hasValue(scrnMsg.xxOrdSrcImptFromDt)
               || hasValue(scrnMsg.xxOrdSrcImptToDt)) {
           hasChecked = true;
       }

       if (!hasChecked) {
           scrnMsg.xxCpoOrdNumSrchTxt_H1.setErrorInfo(1, NWAM0785E);
           scrnMsg.addCheckItem(scrnMsg.xxCpoOrdNumSrchTxt_H1);
           scrnMsg.putErrorScreen();
       }

       // Numeric check
       chkNumeric(scrnMsg, scrnMsg.xxSvcMachMstrSrchTxt);

   }

   private static void chkNumeric(NWAL1570BMsg scrnMsg, EZDBStringItem chkEZDBStringItem) {

       if (!ZYPCommonFunc.hasValue(chkEZDBStringItem)) {
           return;
       }

       String splCharTxt = scrnMsg.xxSplCharTxt.getValue();
       String[] srchTxtArray = getSrchTxt(trimTail(chkEZDBStringItem.getValue()), splCharTxt);

       // Numeric check
       String numeric = "[0-9]+";
       if (srchTxtArray == null) {
           String chkItem = chkEZDBStringItem.getValue();
           if (hasValue(chkItem)) {
               if (!chkItem.matches(numeric)) {
                   String[] msgParam = {chkEZDBStringItem.getNameForMessage()};
                   chkEZDBStringItem.setErrorInfo(1, NWAM0915E, msgParam);
                   scrnMsg.addCheckItem(chkEZDBStringItem);
                   scrnMsg.putErrorScreen();
               }
           }
       } else {
           for (int i = 0; i < srchTxtArray.length; i++) {

               String chkItem = srchTxtArray[i];

               if (hasValue(chkItem)) {
                   if (!chkItem.matches(numeric)) {
                       String[] msgParam = {chkEZDBStringItem.getNameForMessage()};
                       chkEZDBStringItem.setErrorInfo(1, NWAM0915E, msgParam);
                       scrnMsg.addCheckItem(chkEZDBStringItem);
                       scrnMsg.putErrorScreen();
                   }
               }
           }
       }
   }

   /**
    * get array from search text if it has splCharTxt in text field.
    *
    * <pre>
    * statementId = "getSrchTxt"
    * </pre>
    *
    * @param String item
    * @return String[]
    */
   private static String[] getSrchTxt(String item, String splCharTxt) {

       if (item != null && item.length() > 0) {
           if (item.indexOf(splCharTxt) != -1) {
               String[] itemArray = item.split(splCharTxt);
               boolean isExists = false;
               for (int i = 0; i < itemArray.length; i++) {
                   if (!itemArray[i].trim().equals("") && itemArray[i].length() > 0) {
                       isExists = true;
                       break;
                   }
               }
               if (isExists == true) {
                   return itemArray;
               }
           }
       }
       return null;
   }

   public static void backUpSearchCriteria(NWAL1570BMsg scrnMsg) {

       // Back Up
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_B1, scrnMsg.xxCpoOrdNumSrchTxt_H1);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_B2, scrnMsg.xxCpoOrdNumSrchTxt_H2);
       ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNumSrchTxt_BK, scrnMsg.custIssPoNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxLeasePoNumSrchTxt_BK, scrnMsg.xxLeasePoNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctNmSrchTxt_BK, scrnMsg.xxSoldToAcctNmSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt_BK, scrnMsg.xxSoldToAcctCdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt_BK, scrnMsg.xxSoldToLocCdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctNmSrchTxt_BK, scrnMsg.xxShipToAcctNmSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt_BK, scrnMsg.xxShipToAcctCdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt_BK, scrnMsg.xxShipToLocCdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctNmSrchTxt_BK, scrnMsg.xxBillToAcctNmSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt_BK, scrnMsg.xxBillToAcctCdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt_BK, scrnMsg.xxBillToLocCdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaExtnSrchTxt_BK, scrnMsg.xxCoaExtnSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaBrSrchTxt_BK, scrnMsg.xxCoaBrSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSlsRepTocSrchTxt_BK, scrnMsg.xxSlsRepTocSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt_BK, scrnMsg.xxCpoSrcTpSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsBizLineSrchTxt_BK, scrnMsg.xxDsBizLineSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdCatgSrchTxt_BK, scrnMsg.xxDsOrdCatgSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdTpSrchTxt_BK, scrnMsg.xxDsOrdTpSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdRsnSrchTxt_BK, scrnMsg.xxDsOrdRsnSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCsmpContrNumSrchTxt_BK, scrnMsg.xxCsmpContrNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcContrNumSrchTxt_BK, scrnMsg.xxPrcContrNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt_BK, scrnMsg.xxOrdSrcRefNumSrchTxt);
       // 2018/08/01 QC#26304 Add Start
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxAquNumSrchTxt_BK, scrnMsg.xxAquNumSrchTxt);
       // 2018/08/01 QC#26304 Add End
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt_BK, scrnMsg.xxMdseSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.mdseForSlsSmrySrchTxt_BK, scrnMsg.mdseForSlsSmrySrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlSrchTxt_BK, scrnMsg.zerothProdCtrlSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlSrchTxt_BK, scrnMsg.firstProdCtrlSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlSrchTxt_BK, scrnMsg.scdProdCtrlSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlSrchTxt_BK, scrnMsg.thirdProdCtrlSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlSrchTxt_BK, scrnMsg.frthProdCtrlSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaProdSrchTxt_BK, scrnMsg.xxCoaProdSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaMdseTpSrchTxt_BK, scrnMsg.xxCoaMdseTpSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt_BK, scrnMsg.xxMdlSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt_BK, scrnMsg.xxSerNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd_BK, scrnMsg.rtrnRsnCd);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineCatgSrchTxt_BK, scrnMsg.xxLineCatgSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdLineSrcSrchTxt_BK, scrnMsg.xxOrdLineSrcSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt_BK, scrnMsg.xxRtlWhSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt_BK, scrnMsg.xxRtlSwhSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxVndSrchTxt_BK, scrnMsg.xxVndSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_BK, scrnMsg.xxCpoOrdNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.soNumSrchTxt_BK, scrnMsg.soNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.invNumSrchTxt_BK, scrnMsg.invNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsContrNumSrchTxt_BK, scrnMsg.xxDsContrNumSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcConfigMstrSrchTxt_BK, scrnMsg.xxSvcConfigMstrSrchTxt);
       // QC#15760 Add Start
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcMachMstrSrchTxt_BK, scrnMsg.xxSvcMachMstrSrchTxt);
       // QC#15760 Add End
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt_BK, scrnMsg.xxOrdTeamSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdZnSrchTxt_BK, scrnMsg.xxOrdZnSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCratByUsrSrchTxt_BK, scrnMsg.xxCratByUsrSrchTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclPendImptOrdFlg_BK, scrnMsg.xxInclPendImptOrdFlg);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclImptOrdFlg_BK, scrnMsg.xxInclImptOrdFlg);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOnlySlsOrdFlg_BK, scrnMsg.xxOnlySlsOrdFlg);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdHdrStsAllSelFlg_BK, scrnMsg.xxOrdHdrStsAllSelFlg);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineStsAllSelFlg_BK, scrnMsg.xxLineStsAllSelFlg);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnStsAllSelFlg_BK, scrnMsg.xxRtrnStsAllSelFlg);
       ZYPEZDItemValueSetter.setValue(scrnMsg.ordFromDt_BK, scrnMsg.ordFromDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.ordToDt_BK, scrnMsg.ordToDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookFromDt_BK, scrnMsg.xxBookFromDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookToDt_BK, scrnMsg.xxBookToDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipFromDt_BK, scrnMsg.xxActlShipFromDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipToDt_BK, scrnMsg.xxActlShipToDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.invFromDt_BK, scrnMsg.invFromDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.invToDt_BK, scrnMsg.invToDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.ordAgingBcktDescTxt_BK, scrnMsg.ordAgingBcktDescTxt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptFromDt_BK, scrnMsg.xxOrdSrcImptFromDt);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptToDt_BK, scrnMsg.xxOrdSrcImptToDt);

       scrnMsg.S.clear();
       scrnMsg.T.clear();
       scrnMsg.U.clear();
       for (int i = 0; i <  scrnMsg.H.length(); i++) {
           if (scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.equals(Y)) {
               scrnMsg.S.no(i).xxOrdHdrStsSelFlg_BK.setValue(Y);
           }
       }
       for (int i = 0; i <  scrnMsg.L.length(); i++) {
           if (scrnMsg.L.no(i).xxLineStsSelFlg_LS.equals(Y)) {
               scrnMsg.T.no(i).xxLineStsSelFlg_BK.setValue(Y);
           }
       }
       for (int i = 0; i <  scrnMsg.R.length(); i++) {
           if (scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.equals(Y)) {
               scrnMsg.U.no(i).xxRtrnLineStsSelFlg_BK.setValue(Y);
           }
       }
   }

   public static void retrieveSearchCriteria(NWAL1570BMsg scrnMsg) {

       // retrieve
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H1, scrnMsg.xxCpoOrdNumSrchTxt_B1);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt_H2, scrnMsg.xxCpoOrdNumSrchTxt_B2);
       ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNumSrchTxt, scrnMsg.custIssPoNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxLeasePoNumSrchTxt, scrnMsg.xxLeasePoNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctNmSrchTxt, scrnMsg.xxSoldToAcctNmSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToAcctCdSrchTxt, scrnMsg.xxSoldToAcctCdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSoldToLocCdSrchTxt, scrnMsg.xxSoldToLocCdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctNmSrchTxt, scrnMsg.xxShipToAcctNmSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToAcctCdSrchTxt, scrnMsg.xxShipToAcctCdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxShipToLocCdSrchTxt, scrnMsg.xxShipToLocCdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctNmSrchTxt, scrnMsg.xxBillToAcctNmSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt, scrnMsg.xxBillToAcctCdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToLocCdSrchTxt, scrnMsg.xxBillToLocCdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaExtnSrchTxt, scrnMsg.xxCoaExtnSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaBrSrchTxt, scrnMsg.xxCoaBrSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSlsRepTocSrchTxt, scrnMsg.xxSlsRepTocSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoSrcTpSrchTxt, scrnMsg.xxCpoSrcTpSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsBizLineSrchTxt, scrnMsg.xxDsBizLineSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdCatgSrchTxt, scrnMsg.xxDsOrdCatgSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdTpSrchTxt, scrnMsg.xxDsOrdTpSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsOrdRsnSrchTxt, scrnMsg.xxDsOrdRsnSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCsmpContrNumSrchTxt, scrnMsg.xxCsmpContrNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPrcContrNumSrchTxt, scrnMsg.xxPrcContrNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcRefNumSrchTxt, scrnMsg.xxOrdSrcRefNumSrchTxt_BK);
       // 2018/08/01 QC#26304 Add Start
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxAquNumSrchTxt, scrnMsg.xxAquNumSrchTxt_BK);
       // 2018/08/01 QC#26304 Add End
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdseSrchTxt, scrnMsg.xxMdseSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.mdseForSlsSmrySrchTxt, scrnMsg.mdseForSlsSmrySrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.zerothProdCtrlSrchTxt, scrnMsg.zerothProdCtrlSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.firstProdCtrlSrchTxt, scrnMsg.firstProdCtrlSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.scdProdCtrlSrchTxt, scrnMsg.scdProdCtrlSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.thirdProdCtrlSrchTxt, scrnMsg.thirdProdCtrlSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.frthProdCtrlSrchTxt, scrnMsg.frthProdCtrlSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaProdSrchTxt, scrnMsg.xxCoaProdSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCoaMdseTpSrchTxt, scrnMsg.xxCoaMdseTpSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxMdlSrchTxt, scrnMsg.xxMdlSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSerNumSrchTxt, scrnMsg.xxSerNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd, scrnMsg.rtrnRsnCd_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineCatgSrchTxt, scrnMsg.xxLineCatgSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdLineSrcSrchTxt, scrnMsg.xxOrdLineSrcSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlWhSrchTxt, scrnMsg.xxRtlWhSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtlSwhSrchTxt, scrnMsg.xxRtlSwhSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxVndSrchTxt, scrnMsg.xxVndSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCpoOrdNumSrchTxt, scrnMsg.xxCpoOrdNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.soNumSrchTxt, scrnMsg.soNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.invNumSrchTxt, scrnMsg.invNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxDsContrNumSrchTxt, scrnMsg.xxDsContrNumSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcConfigMstrSrchTxt, scrnMsg.xxSvcConfigMstrSrchTxt_BK);
       // QC#15760 Add Start
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxSvcMachMstrSrchTxt, scrnMsg.xxSvcMachMstrSrchTxt_BK);
       // QC#15760 Add End
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdTeamSrchTxt, scrnMsg.xxOrdTeamSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdZnSrchTxt, scrnMsg.xxOrdZnSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxCratByUsrSrchTxt, scrnMsg.xxCratByUsrSrchTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclPendImptOrdFlg, scrnMsg.xxInclPendImptOrdFlg_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxInclImptOrdFlg, scrnMsg.xxInclImptOrdFlg_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOnlySlsOrdFlg, scrnMsg.xxOnlySlsOrdFlg_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdHdrStsAllSelFlg, scrnMsg.xxOrdHdrStsAllSelFlg_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxLineStsAllSelFlg, scrnMsg.xxLineStsAllSelFlg_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnStsAllSelFlg, scrnMsg.xxRtrnStsAllSelFlg_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.ordFromDt, scrnMsg.ordFromDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.ordToDt, scrnMsg.ordToDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookFromDt, scrnMsg.xxBookFromDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxBookToDt, scrnMsg.xxBookToDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipFromDt, scrnMsg.xxActlShipFromDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxActlShipToDt, scrnMsg.xxActlShipToDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.invFromDt, scrnMsg.invFromDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.invToDt, scrnMsg.invToDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.ordAgingBcktDescTxt, scrnMsg.ordAgingBcktDescTxt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptFromDt, scrnMsg.xxOrdSrcImptFromDt_BK);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxOrdSrcImptToDt, scrnMsg.xxOrdSrcImptToDt_BK);

       for (int i = 0; i <  scrnMsg.S.length(); i++) {
           if (scrnMsg.S.no(i).xxOrdHdrStsSelFlg_BK.equals(Y)) {
               scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setValue(Y);
           } else {
               scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.clear();
           }
       }
       for (int i = 0; i <  scrnMsg.T.length(); i++) {
           if (scrnMsg.T.no(i).xxLineStsSelFlg_BK.equals(Y)) {
               scrnMsg.L.no(i).xxLineStsSelFlg_LS.setValue(Y);
           } else {
               scrnMsg.L.no(i).xxLineStsSelFlg_LS.clear();
           }
       }
       for (int i = 0; i <  scrnMsg.U.length(); i++) {
           if (scrnMsg.U.no(i).xxRtrnLineStsSelFlg_BK.equals(Y)) {
               scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.setValue(Y);
           } else {
               scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.clear();
           }
       }
   }

   private static void checkInqStsChkBoxes(NWAL1570BMsg scrnMsg) {

       if (RSLT_MODE.STATUS_SUMMARY.getRsltModeCd().equals(scrnMsg.xxRsltModeCd.getValue())
               || hasValue(scrnMsg.xxCpoOrdNumSrchTxt_H1)) {
           return;
       }

       boolean hasChecked = false;
       for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
           if (scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
               hasChecked = true;
               break;
           }
       }
       for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
           if (scrnMsg.L.no(i).xxLineStsSelFlg_LS.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
               hasChecked = true;
               break;
           }
       }
       for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
           if (scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
               hasChecked = true;
               break;
           }
       }

       if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxInclPendImptOrdFlg.getValue())) {
           hasChecked = true;
       }

       if (!hasChecked) {
           for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
               scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS.setErrorInfo(1, NWAM0224E, new String[] {"Status", "1" });
               scrnMsg.addCheckItem(scrnMsg.H.no(i).xxOrdHdrStsSelFlg_HS);
           }
           for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
               scrnMsg.L.no(i).xxLineStsSelFlg_LS.setErrorInfo(1, NWAM0224E, new String[] {"Status", "1" });
               scrnMsg.addCheckItem(scrnMsg.L.no(i).xxLineStsSelFlg_LS);
           }
           for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
               scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS.setErrorInfo(1, NWAM0224E, new String[] {"Status", "1" });
               scrnMsg.addCheckItem(scrnMsg.R.no(i).xxRtrnLineStsSelFlg_RS);
           }
           scrnMsg.putErrorScreen();
       }
   }

   /**
    * Get Param NMAL6760 For Bill To
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6760 For Bill To
    */
   public static Object[] getParamNMAL6760ForBillTo(NWAL1570BMsg scrnMsg) {

       // clear
       clearPopUpParam(scrnMsg);

       // Mod Start 2016/08/04 QC#9078
//       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760Constant.STATUS_CD_ACTIVE);
//       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760_STATUS_CD_ACTIVE);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
       // Mod End 2016/08/04 QC#9078
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
       if (ZYPCommonFunc.hasValue(scrnMsg.xxBillToAcctCdSrchTxt)) {
           ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
       }
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.xxBillToAcctCdSrchTxt);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       paramList.add(scrnMsg.xxPopPrm_P6);
       paramList.add(scrnMsg.xxBillToAcctNmSrchTxt);

       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P1); // Active Only
       paramList.add(scrnMsg.xxPopPrm_P2); // Bill To's Only
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P7); // Bill To Location
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P3); // Display Hierarkey Active Flag
       paramList.add(scrnMsg.xxPopPrm_P4); // Account Number Active Flag
       paramList.add(scrnMsg.xxPopPrm_P5); // Status Active Flag

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6760 For Ship To
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6760 For Ship To
    */
   public static Object[] getParamNMAL6760ForShipTo(NWAL1570BMsg scrnMsg) {

       // clear
       clearPopUpParam(scrnMsg);

//       String shipToAcctNum = getShipToAcctNum(scrnMsg);

       // Mod Start 2016/08/04 QC#9078
//       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760Constant.STATUS_CD_ACTIVE);
//       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760_STATUS_CD_ACTIVE);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
       // Mod End 2016/08/04 QC#9078
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
       if (ZYPCommonFunc.hasValue(scrnMsg.xxShipToAcctCdSrchTxt)) {
           ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
       }
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.xxShipToAcctCdSrchTxt.getValue());

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       paramList.add(scrnMsg.xxPopPrm_P6); // Ship To Account
       paramList.add(scrnMsg.xxShipToAcctNmSrchTxt);

       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P1); // Active Only
       paramList.add(scrnMsg.xxPopPrm_P2); // Ship To's Only
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P7); // Ship To Location
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P3); // Display Hierarkey Active Flag
       paramList.add(scrnMsg.xxPopPrm_P4); // Account Number Active Flag
       paramList.add(scrnMsg.xxPopPrm_P5); // Status Active Flag

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6760 For Sold To
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6760 For Sold To
    */
   public static Object[] getParamNMAL6760ForSoldTo(NWAL1570BMsg scrnMsg) {

       // clear
       clearPopUpParam(scrnMsg);

//       String soldToAcctNum = getSoldToAcctNum(scrnMsg);

       // Mod Start 2016/08/04 QC#9078
//       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760Constant.STATUS_CD_ACTIVE);
//       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760_STATUS_CD_ACTIVE);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
       // Mod End 2016/08/04 QC#9078
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
       if (ZYPCommonFunc.hasValue(scrnMsg.xxSoldToAcctCdSrchTxt)) {
           ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
       }
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.xxSoldToAcctCdSrchTxt);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();
       paramList.add(scrnMsg.xxPopPrm_P6); // Sold To Account
       paramList.add(scrnMsg.xxSoldToAcctNmSrchTxt);
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P1); // Active Only
       paramList.add(scrnMsg.xxPopPrm_P2); // Bill To's Only
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P7); // Sold To Location
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P3); // Display Hierarkey Active Flag
       paramList.add(scrnMsg.xxPopPrm_P4); // Account Number Active Flag
       paramList.add(scrnMsg.xxPopPrm_P5); // Status Active Flag

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6800
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6800
    */
   public static Object[] getParamNMAL6800ForItemCode(NWAL1570BMsg scrnMsg) {

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();
       clearPopUpParam(scrnMsg);

       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, ALL_MODE);

       paramList.add(scrnMsg.mdseForSlsSmrySrchTxt); // Item Number
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // Merchandise Description Short Text(no used)
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P0); // Mode Code

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6800
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6800
    */
   public static Object[] getParamNMAL6800ForItemName(NWAL1570BMsg scrnMsg) {

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();
       clearPopUpParam(scrnMsg);

       ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, ALL_MODE);

       paramList.add(scrnMsg.xxPopPrm_PA); // Item Number(no used)
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxMdseSrchTxt); // Merchandise Description Short Text
       paramList.add(scrnMsg.xxPopPrm_PA); // no used
       paramList.add(scrnMsg.xxPopPrm_P0); // Mode Code

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * <pre>
    * The pop up parameter is cleared.
    * </pre>
    * @param scrnMsg Screen Msg
    */
   public static void clearPopUpParam(NWAL1570BMsg scrnMsg) {

       scrnMsg.xxPopPrm_P0.clear();
       scrnMsg.xxPopPrm_P1.clear();
       scrnMsg.xxPopPrm_P2.clear();
       scrnMsg.xxPopPrm_P3.clear();
       scrnMsg.xxPopPrm_P4.clear();
       scrnMsg.xxPopPrm_P5.clear();
       scrnMsg.xxPopPrm_P6.clear();
       scrnMsg.xxPopPrm_P7.clear();
       scrnMsg.xxPopPrm_P8.clear();
       scrnMsg.xxPopPrm_P9.clear();
       scrnMsg.xxPopPrm_PA.clear();
       scrnMsg.xxPopPrm_PB.clear();
       scrnMsg.xxPopPrm_PC.clear();
       scrnMsg.xxPopPrm_PD.clear();
       scrnMsg.xxPopPrm_PE.clear();
       scrnMsg.xxPopPrm_PF.clear();
       scrnMsg.xxPopPrm_PG.clear();
       scrnMsg.xxPopPrm_PH.clear();
       scrnMsg.xxPopPrm_PI.clear();
       scrnMsg.xxPopPrm_PJ.clear();

   }

   /**
     * Get Param NWAL1130 For PLGroup
     * @param scrnMsg NWAL1570BMsg
     * @return Param NWAL1130 For PLGroup
     */
    public static Object[] getParamNWAL1130ForPLGroup(NWAL1570BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Zeroth Product Hierarchy Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS ZEROTH_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS ZEROTH_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS ZEROTH_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Product Level 1";
        whereArray0[IDX_1] = "ZEROTH_PROD_HRCH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Description";
        whereArray1[IDX_1] = "ZEROTH_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scrnMsg.zerothProdCtrlSrchTxt)) {
            whereArray1[IDX_2] = scrnMsg.zerothProdCtrlSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = PERCENT;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Product Level 1";
        columnArray0[IDX_1] = "ZEROTH_PROD_HRCH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Description";
        columnArray1[IDX_1] = "ZEROTH_PROD_HRCH_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "ZEROTH_PROD_HRCH_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For PL1
     * @param scrnMsg NWAL1570BMsg
     * @return Param NWAL1130 For PL1
     */
    public static Object[] getParamNWAL1130ForPL1(NWAL1570BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "First Product Hierarchy Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS FIRST_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS FIRST_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS FIRST_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Product Level 2";
        whereArray0[IDX_1] = "FIRST_PROD_HRCH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Description";
        whereArray1[IDX_1] = "FIRST_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scrnMsg.firstProdCtrlSrchTxt)) {
            whereArray1[IDX_2] = scrnMsg.firstProdCtrlSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = PERCENT;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Product Level 2";
        columnArray0[IDX_1] = "FIRST_PROD_HRCH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Description";
        columnArray1[IDX_1] = "FIRST_PROD_HRCH_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "FIRST_PROD_HRCH_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }


    /**
     * Get Param NWAL1130 For PL2
     * @param scrnMsg NWAL1570BMsg
     * @return Param NWAL1130 For PL2
     */
    public static Object[] getParamNWAL1130ForPL2(NWAL1570BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Second Product Hierarchy Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS SCD_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS SCD_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS SCD_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Product Level 3";
        whereArray0[IDX_1] = "SCD_PROD_HRCH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Description";
        whereArray1[IDX_1] = "SCD_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scrnMsg.scdProdCtrlSrchTxt)) {
            whereArray1[IDX_2] = scrnMsg.scdProdCtrlSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = PERCENT;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Product Level 3";
        columnArray0[IDX_1] = "SCD_PROD_HRCH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Description";
        columnArray1[IDX_1] = "SCD_PROD_HRCH_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "SCD_PROD_HRCH_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For PL3
     * @param scrnMsg NWAL1570BMsg
     * @return Param NWAL1130 For PL3
     */
    public static Object[] getParamNWAL1130ForPL3(NWAL1570BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Third Product Hierarchy Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS THIRD_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS THIRD_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS THIRD_PROD_HRCH_SORT_NUM");
        sb.append("     ,PC.SCD_PROD_HRCH_CD");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Product Level 4";
        whereArray0[IDX_1] = "THIRD_PROD_HRCH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Description";
        whereArray1[IDX_1] = "THIRD_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scrnMsg.thirdProdCtrlSrchTxt)) {
            whereArray1[IDX_2] = scrnMsg.thirdProdCtrlSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = PERCENT;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Product Level 4";
        columnArray0[IDX_1] = "THIRD_PROD_HRCH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Description";
        columnArray1[IDX_1] = "THIRD_PROD_HRCH_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "THIRD_PROD_HRCH_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }


    /**
     * Get Param NWAL1130 For PL4
     * @param scrnMsg NWAL1570BMsg
     * @return Param NWAL1130 For PL4
     */
    public static Object[] getParamNWAL1130ForPL4(NWAL1570BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Fourth Product Hierarchy Search";

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS FRTH_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS FRTH_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS FRTH_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4).append("' ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Product Level 5";
        whereArray0[IDX_1] = "FRTH_PROD_HRCH_CD";
        whereArray0[IDX_2] = null;
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Description";
        whereArray1[IDX_1] = "FRTH_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scrnMsg.frthProdCtrlSrchTxt)) {
            whereArray1[IDX_2] = scrnMsg.frthProdCtrlSrchTxt.getValue();
        } else {
            whereArray1[IDX_2] = PERCENT;
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Product Level 5";
        columnArray0[IDX_1] = "FRTH_PROD_HRCH_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Description";
        columnArray1[IDX_1] = "FRTH_PROD_HRCH_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "FRTH_PROD_HRCH_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }
   /**
    * Get Param NMAL6050 For COA Branch
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6800
    */
   public static Object[] getParamNMAL6050ForCoaBr(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("COA_BR");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("COA_BR_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("COA_BR_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("COA_BR_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("COA Branch Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("COA Branch Code");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("COA Branch Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxCoaBrSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For COA Product
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6800
    */
   public static Object[] getParamNMAL6050ForCoaProd(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("COA_PROD");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("COA_PROD_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("COA_PROD_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("COA_PROD_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("COA Product Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("COA Product Code");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("COA Product Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxCoaProdSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For COA Merchandise
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6800
    */
   public static Object[] getParamNMAL6050ForCoaMdse(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       //Mod Start 2016/12/13 T.Aoi S21_NA#16591
       /*
       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("COA_MDSE_TP");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("COA_MDSE_TP_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("COA_MDSE_TP_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("COA_MDSE_TP_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);
       */

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("COA_PROJ");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("COA_PROJ_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("COA_PROJ_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("COA_PROJ_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);
       //Mod End   2016/12/13 T.Aoi S21_NA#16591

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("COA Merchandise Type Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("COA Merchandise Type");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("COA Merchandise Type");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxCoaMdseTpSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For Business Unit
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1570
    */
   public static Object[] getParamNMAL6050ForCoaExtnCd(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("COA_EXTN");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("COA_EXTN_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("COA_EXTN_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("COA_EXTN_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("COA Business Unit Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("COA Business Unit Code");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("COA Business Unit Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxCoaExtnSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For Line Category
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1570
    */
   public static Object[] getParamNMAL6050ForLineCategory(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("DS_ORD_LINE_CATG");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("DS_ORD_LINE_CATG_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("DS_ORD_LINE_CATG_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("DS_ORD_LINE_CATG_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("Order Line Category Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("Order Line Category Code");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("Order Line Category Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxLineCatgSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For Line Sounrce
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1570
    */
   public static Object[] getParamNMAL6050ForLineSource(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("ORD_LINE_SRC");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("ORD_LINE_SRC_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("ORD_LINE_SRC_NM");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("ORD_LINE_SRC_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("Order Line Source Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("Order Line Source Code");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("Order Line Source Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxOrdLineSrcSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For WH
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1570
    */
   public static Object[] getParamNMAL6050ForWH(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("RTL_WH");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("RTL_WH_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("RTL_WH_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("RTL_WH_CD");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("WH Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("WH Code");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("WH Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxRtlWhSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NMAL6050 For COA Merchandise
    * @param scrnMsg NWAL1570BMsg
    * @return Param NMAL6800
    */
   public static Object[] getParamNMAL6050ForZone(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<EZDBItem> paramList = new ArrayList<EZDBItem>();

       // [0]:TBL_NM
       scrnMsg.xxPopPrm_P0.setValue("ORD_ZN");
       paramList.add(scrnMsg.xxPopPrm_P0);

       // [1]:TBL_CD_COL_NM
       scrnMsg.xxPopPrm_P1.setValue("ORD_ZN_CD");
       paramList.add(scrnMsg.xxPopPrm_P1);

       // [2]:TBL_NM_COL_NM
       scrnMsg.xxPopPrm_P2.setValue("ORD_ZN_DESC_TXT");
       paramList.add(scrnMsg.xxPopPrm_P2);

       // [3]:TBL_SORT_COL_NM
       scrnMsg.xxPopPrm_P3.setValue("ORD_ZN_SORT_NUM");
       paramList.add(scrnMsg.xxPopPrm_P3);

       // [4]:SCR_NM
       scrnMsg.xxPopPrm_P4.setValue("Order Zone Search");
       paramList.add(scrnMsg.xxPopPrm_P4);

       // [5]:HDR_CD_LB_NM
       scrnMsg.xxPopPrm_P5.setValue("Order Zone");
       paramList.add(scrnMsg.xxPopPrm_P5);

       // [6]:HDR_NM_LB_NM
       scrnMsg.xxPopPrm_P6.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P6);

       // [7]:DTL_CD_LB_NM
       scrnMsg.xxPopPrm_P7.setValue("Order Zone Code");
       paramList.add(scrnMsg.xxPopPrm_P7);

       // [8]:DTL_NM_LB_NM
       scrnMsg.xxPopPrm_P8.setValue("Description");
       paramList.add(scrnMsg.xxPopPrm_P8);

       // [9]:COND_CD
       scrnMsg.xxPopPrm_P9.clear();
       paramList.add(scrnMsg.xxPopPrm_P9);

       // [10]:COND_NM
       paramList.add(scrnMsg.xxOrdZnSrchTxt);

       return paramList.toArray(new EZDBItem[paramList.size()]);
   }

   /**
    * Get Param NWAL1130 For Salesrep
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For Salesrep
    */
   public static Object[] getParamNWAL1130ForSlsrep(NWAL1570BMsg scrnMsg) {

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "Sales Rep Search";

       // QC#7861 Modify Start
       StringBuilder sb = new StringBuilder();
       sb.append("SELECT");
       sb.append("    T.GLBL_CMPY_CD");
       sb.append("   ,T.EZCANCELFLAG");
       sb.append("   ,SP.PSN_NUM");
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
       sb.append("   ,COA_BR CB");
       sb.append("   ,S21_ORG SO ");
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
       sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
       sb.append("    AND T.TOC_CD              = SO.TOC_CD");
       sb.append("    AND SO.EZCANCELFLAG        = '0'");
       sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
       sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
       // 2016/10/24 QC#14607 Del Start
//       sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
//       sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
       // 2016/10/24 QC#14607 Del End
       sb.append("    AND SP.EZCANCELFLAG       = '0'");
       sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
       sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
       sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

       params[IDX_2] = sb.toString();

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "Resource Number";
       whereArray0[IDX_1] = "PSN_NUM";
       whereArray0[IDX_2] = null;
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       Object[] whereArray1 = new Object[IDX_4];
       whereArray1[IDX_0] = "Name";
       whereArray1[IDX_1] = "TOC_NM";
       if (hasValue(scrnMsg.xxSlsRepTocSrchTxt.getValue())) {
           whereArray1[IDX_2] = scrnMsg.xxSlsRepTocSrchTxt.getValue();
       } else {
           whereArray1[IDX_2] = PERCENT;
       }
       whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray1);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();
       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "Resource Number";
       columnArray0[IDX_1] = "PSN_NUM";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
       columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "Name";
       columnArray1[IDX_1] = "TOC_NM";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
       columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       columnList.add(columnArray1);

       Object[] columnArray2 = new Object[IDX_4];
       columnArray2[IDX_0] = "Line of Bussines";
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
       sortConditionArray0[IDX_0] = "PSN_NUM";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray0);

       params[IDX_5] = sortConditionList;

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;
       // QC#7861 Modify End

       return params;
   }

   /**
    * Get Param NWAL1130 For Model
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For Model
    */
   public static Object[] getParamNWAL1130ForModel(NWAL1570BMsg scrnMsg) {

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "Model Search";

       StringBuilder sb = new StringBuilder();
       sb.append("SELECT");
       sb.append("    MN.T_GLBL_CMPY_CD AS GLBL_CMPY_CD");
       sb.append("   ,MN.EZCANCELFLAG");
       sb.append("   ,MN.T_MDL_ID");
       sb.append("   ,MN.T_MDL_NM");
       sb.append(" FROM");
       sb.append("    MDL_NM MN");
       sb.append(" WHERE");
       sb.append("        MN.T_GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
       sb.append("    AND MN.EZCANCELFLAG        = '0'");

       params[IDX_2] = sb.toString();

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "Model Name";
       whereArray0[IDX_1] = "T_MDL_NM";
       if (hasValue(scrnMsg.xxMdlSrchTxt.getValue())) {
           whereArray0[IDX_2] = scrnMsg.xxMdlSrchTxt.getValue();
       } else {
           whereArray0[IDX_2] = PERCENT;
       }
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();
       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "Model ID";
       columnArray0[IDX_1] = "T_MDL_ID";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
       columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "Model Name";
       columnArray1[IDX_1] = "T_MDL_NM";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
       columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       columnList.add(columnArray1);

       params[IDX_4] = columnList;

       List<Object[]> sortConditionList = new ArrayList<Object[]>();
       Object[] sortConditionArray0 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "T_MDL_ID";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray0);

       params[IDX_5] = sortConditionList;

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;

       return params;
   }

   /**
    * Get Param NWAL1130 For Sub Warehouse
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For Sub Warehouse
    */
   public static Object[] getParamNWAL1130ForSubWh(NWAL1570BMsg scrnMsg) {

       String rtlWhNm = null;
       String rtlSubWhNm = null;

       rtlSubWhNm = scrnMsg.xxRtlSwhSrchTxt.getValue();

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "Ship From Sub Warehouse Search";
       params[IDX_2] = getSlctTblNmForWh(scrnMsg);

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "Warehouse Name";
       whereArray0[IDX_1] = "RTL_WH_DESC_TXT";
       whereArray0[IDX_2] = rtlWhNm;
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       Object[] whereArray1 = new Object[IDX_4];
       whereArray1[IDX_0] = "Sub Warehouse Name";
       whereArray1[IDX_1] = "RTL_SWH_NM";
       if (hasValue(rtlSubWhNm)) {
           whereArray1[IDX_2] = rtlSubWhNm;
       } else {
           whereArray1[IDX_2] = PERCENT;
       }
       whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray1);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();

       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "Warehouse Code";
       columnArray0[IDX_1] = "RTL_WH_CD";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
       columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "Warehouse Name";
       columnArray1[IDX_1] = "RTL_WH_DESC_TXT";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
       columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray1);

       Object[] columnArray2 = new Object[IDX_4];
       columnArray2[IDX_0] = "Sub Warehouse Code";
       columnArray2[IDX_1] = "RTL_SWH_CD";
       columnArray2[IDX_2] = BigDecimal.valueOf(IDX_13);
       columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray2);

       Object[] columnArray3 = new Object[IDX_4];
       columnArray3[IDX_0] = "Sub Warehouse Name";
       columnArray3[IDX_1] = "RTL_SWH_NM";
       columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
       columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
       columnList.add(columnArray3);

       params[IDX_4] = columnList;

       List<Object[]> sortConditionList = new ArrayList<Object[]>();
       Object[] sortConditionArray0 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "RTL_WH_CD";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray0);

       Object[] sortConditionArray1 = new Object[IDX_2];
       sortConditionArray1[IDX_0] = "RTL_SWH_CD";
       sortConditionArray1[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray1);

       params[IDX_5] = sortConditionList;

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;

       return params;
   }

   /**
    * Get Select Table Name For Sub Werehouse
    * @param scrnMsg NWAL1570BMsg
    * @param isWh WH:true SWH:false
    * @param rtlWhCd WHEN SWH == false THEN rtlWhCd
    * @return Select Table Name For Sub Werehouse
    */
   private static String getSlctTblNmForWh(NWAL1570BMsg scrnMsg) {

       String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
       String slsDt = scrnMsg.slsDt.getValue();

       StringBuilder sb = new StringBuilder();
       sb.append("SELECT");
       sb.append("    WH.GLBL_CMPY_CD");
       sb.append("   ,WH.EZCANCELFLAG");
       sb.append("   ,WH.RTL_WH_CD");
       // 2018/12/14 QC#29286 Mod Start
       sb.append("   ,RW.RTL_WH_DESC_TXT");
       //sb.append("   ,WH.RTL_WH_NM");
       // 2018/12/14 QC#29286 Mod Start
       sb.append("   ,WH.RTL_SWH_CD");
       sb.append("   ,WH.RTL_SWH_NM");
       sb.append(" FROM");
       sb.append("    DS_INVTY_LOC_V                       WH ");
       sb.append("   ,RTL_WH                               RW ");
       sb.append(" WHERE");
       sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
       sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
       sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
       sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
       sb.append("    AND WH.EZCANCELFLAG                  = '0'");
       // 2018/12/14 QC#29286 Add Start
       sb.append("    AND WH.RTL_WH_CD                     = RW.RTL_WH_CD");
       sb.append("    AND RW.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
       sb.append("    AND RW.EZCANCELFLAG                  = '0'");
       // 2018/12/14 QC#29286 Add End

       return sb.toString();
   }

   /**
    * Get Param NWAL1130 For CSMP Number
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For CSMP Number
    */
   public static Object[] getParamNWAL1130ForCSMPNumber(NWAL1570BMsg scrnMsg) {

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "CSMP# Search";

       StringBuilder sb = new StringBuilder();
       sb.append("SELECT");
       sb.append("    DISTINCT");
       sb.append("    CC.EZCANCELFLAG");
       sb.append("   ,CC.GLBL_CMPY_CD");
       sb.append("   ,CC.CSMP_NUM");
       sb.append("   ,CC.DLR_REF_NUM");
       sb.append("   ,CC.CSMP_NUM_CMNT_TXT ");
       sb.append("FROM ");
       sb.append("    CSMP_CONTR CC ");
       sb.append("WHERE ");
       sb.append("    CC.GLBL_CMPY_CD            = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
       sb.append("    AND CC.EZCANCELFLAG        = '0'");
       sb.append("    AND CC.EFF_FROM_DT         <= '").append(scrnMsg.slsDt.getValue()).append("'");
       sb.append("    AND (CC.EFF_THRU_DT        >= '").append(scrnMsg.slsDt.getValue()).append("' OR CC.EFF_THRU_DT IS NULL)");
       sb.append("    AND CC.CSMP_CONTR_ACTV_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
       sb.append("    AND (CC.DLR_REF_NUM IS NOT NULL OR CC.CSMP_NUM IS NOT NULL)");
       params[IDX_2] = sb.toString();

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "CSMP Number";
       whereArray0[IDX_1] = "CSMP_NUM";
       if (hasValue(scrnMsg.xxCsmpContrNumSrchTxt.getValue())) {
           whereArray0[IDX_2] = scrnMsg.xxCsmpContrNumSrchTxt.getValue();
       } else {
           whereArray0[IDX_2] = PERCENT;
       }
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       Object[] whereArray1 = new Object[IDX_4];
       whereArray1[IDX_0] = "CSA Number";
       whereArray1[IDX_1] = "DLR_REF_NUM";
       whereArray1[IDX_2] = null;
       whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray1);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();
       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "CSMP Number";
       columnArray0[IDX_1] = "CSMP_NUM";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
       columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "CSA Number";
       columnArray1[IDX_1] = "DLR_REF_NUM";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
       columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray1);

       Object[] columnArray2 = new Object[IDX_4];
       columnArray2[IDX_0] = "CSMP Number Comment";
       columnArray2[IDX_1] = "CSMP_NUM_CMNT_TXT";
       columnArray2[IDX_2] = BigDecimal.valueOf(IDX_50);
       columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray2);

       params[IDX_4] = columnList;

       List<Object[]> sortConditionList = new ArrayList<Object[]>();
       Object[] sortConditionArray0 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "CSMP_NUM";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray0);

       Object[] sortConditionArray1 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "DLR_REF_NUM";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray1);

       params[IDX_5] = sortConditionList;

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;

       return params;
   }

   /**
    * Get Param NWAL1130 For Association Program
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For Association Program
    */
   public static Object[] getParamNWAL1130ForAssnProgram(NWAL1570BMsg scrnMsg) {

       String dateFormat = ZYPDateUtil.getDateFormatString(true);

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "Association Program Search";

       StringBuilder sb = new StringBuilder();
       sb.append("SELECT ");
       sb.append("     PC.GLBL_CMPY_CD");
       sb.append("    ,PC.EZCANCELFLAG");
       sb.append("    ,PC.PRC_CONTR_NUM");
       sb.append("    ,PC.PRC_CONTR_NM ");
       sb.append("    ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
       sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
       sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
       sb.append("FROM");
       sb.append("    PRC_CONTR PC ");
       sb.append("WHERE");
       sb.append("    PC.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
       sb.append("    AND PC.EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
       sb.append("    AND (PC.EFF_THRU_DT  >= '").append(scrnMsg.slsDt.getValue()).append("' OR PC.EFF_THRU_DT IS NULL)");
       sb.append("    AND PC.EZCANCELFLAG   = 0");

       params[IDX_2] = sb.toString();

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "Price Contract #";
       whereArray0[IDX_1] = "PRC_CONTR_NUM";
       whereArray0[IDX_2] = null;
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       Object[] whereArray1 = new Object[IDX_4];
       whereArray1[IDX_0] = "Price Contract Name";
       whereArray1[IDX_1] = "PRC_CONTR_NM";
       if (hasValue(scrnMsg.xxPrcContrNumSrchTxt.getValue())) {
           whereArray1[IDX_2] = scrnMsg.xxPrcContrNumSrchTxt.getValue();
       } else {
           whereArray1[IDX_2] = PERCENT;
       }
       whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray1);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();
       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "Price Contract #";
       columnArray0[IDX_1] = "PRC_CONTR_NUM";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_33);
       columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "Price Contract Name";
       columnArray1[IDX_1] = "PRC_CONTR_NM";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_33);
       columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
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

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;

       return params;
   }

   /**
    * Get Param NWAL1130 For Team
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For CSMP Number
    */
   public static Object[] getParamNWAL1130ForTeam(NWAL1570BMsg scrnMsg) {

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "Order Team Search";

       StringBuilder sb = new StringBuilder();
       sb.append("SELECT");
       sb.append("    OZN.GLBL_CMPY_CD ");
       sb.append("    ,OZN.EZCANCELFLAG ");
       sb.append("    ,OZN.ORD_ZN_DESC_TXT ");
       sb.append("    ,OTH.ORD_TEAM_MSTR_NM ");
       sb.append("FROM ");
       sb.append("    ORD_TEAM_MSTR_HDR  OTH ");
       sb.append("    ,ORD_ZN            OZN ");
       sb.append("WHERE ");
       sb.append("    OTH.GLBL_CMPY_CD           = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
       sb.append("    AND OTH.EZCANCELFLAG       = '0'");
       sb.append("    AND OTH.EFF_FROM_DT       <= '").append(scrnMsg.slsDt.getValue()).append("'");
       sb.append("    AND (OTH.EFF_THRU_DT      >= '").append(scrnMsg.slsDt.getValue()).append("' OR OTH.EFF_THRU_DT IS NULL)");
       sb.append("    AND OTH.GLBL_CMPY_CD       = OZN.GLBL_CMPY_CD");
       sb.append("    AND OTH.ORD_ZN_CD          = OZN.ORD_ZN_CD");
       sb.append("    AND OZN.EZCANCELFLAG       = '0'");
       params[IDX_2] = sb.toString();

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "Order Zone Name";
       whereArray0[IDX_1] = "ORD_ZN_DESC_TXT";
       whereArray0[IDX_2] = scrnMsg.xxOrdZnSrchTxt.getValue();
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       Object[] whereArray1 = new Object[IDX_4];
       whereArray1[IDX_0] = "Order Team Name";
       whereArray1[IDX_1] = "ORD_TEAM_MSTR_NM";
       if (hasValue(scrnMsg.xxOrdTeamSrchTxt.getValue())) {
           whereArray1[IDX_2] = scrnMsg.xxOrdTeamSrchTxt.getValue();
       } else {
           whereArray1[IDX_2] = PERCENT;
       }
       whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray1);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();
       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "Order Zone Name";
       columnArray0[IDX_1] = "ORD_ZN_DESC_TXT";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
       columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "Order Team Name";
       columnArray1[IDX_1] = "ORD_TEAM_MSTR_NM";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
       columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       columnList.add(columnArray1);

       params[IDX_4] = columnList;

       List<Object[]> sortConditionList = new ArrayList<Object[]>();
       Object[] sortConditionArray0 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "ORD_ZN_DESC_TXT";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray0);

       Object[] sortConditionArray1 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "ORD_TEAM_MSTR_NM";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray1);

       params[IDX_5] = sortConditionList;

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;

       return params;
   }

   // QC#13415 ADD Start
   /**
    * Get Param NWAL1130 For POVendor
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1130 For POVendor
    */
   public static Object[] getParamNWAL1130ForPOVendor(NWAL1570BMsg scrnMsg) {

       Object[] params = new Object[IDX_7];
       params[IDX_0] = "";
       params[IDX_1] = "PO Vendor Search";

       StringBuilder sb = new StringBuilder();
       sb.append("SELECT");
       sb.append("    PVV.GLBL_CMPY_CD");
       sb.append("   ,PVV.EZCANCELFLAG");
       sb.append("   ,PVV.VND_CD");
       sb.append("   ,PVV.DPLY_VND_NM");
       sb.append(" FROM");
       sb.append("    PO_VND_V PVV");
       sb.append(" WHERE");
       sb.append("        PVV.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
       sb.append("    AND PVV.EZCANCELFLAG        = '0'");

       params[IDX_2] = sb.toString();

       List<Object[]> whereList = new ArrayList<Object[]>();
       Object[] whereArray0 = new Object[IDX_4];
       whereArray0[IDX_0] = "Vendor Code";
       whereArray0[IDX_1] = "VND_CD";
       whereArray0[IDX_2] = null;
       whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray0);

       Object[] whereArray1 = new Object[IDX_4];
       whereArray1[IDX_0] = "Vendor Name";
       whereArray1[IDX_1] = "DPLY_VND_NM";
       if (hasValue(scrnMsg.xxVndSrchTxt.getValue())) {
           whereArray1[IDX_2] = scrnMsg.xxVndSrchTxt.getValue();
       } else {
           whereArray1[IDX_2] = PERCENT;
       }
       whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       whereList.add(whereArray1);

       params[IDX_3] = whereList;

       List<Object[]> columnList = new ArrayList<Object[]>();
       Object[] columnArray0 = new Object[IDX_4];
       columnArray0[IDX_0] = "Vender Code";
       columnArray0[IDX_1] = "VND_CD";
       columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
       columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
       columnList.add(columnArray0);

       Object[] columnArray1 = new Object[IDX_4];
       columnArray1[IDX_0] = "Vendor Name";
       columnArray1[IDX_1] = "DPLY_VND_NM";
       columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
       columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
       columnList.add(columnArray1);

       params[IDX_4] = columnList;

       List<Object[]> sortConditionList = new ArrayList<Object[]>();
       Object[] sortConditionArray0 = new Object[IDX_2];
       sortConditionArray0[IDX_0] = "VND_CD";
       sortConditionArray0[IDX_1] = "ASC";
       sortConditionList.add(sortConditionArray0);

       params[IDX_5] = sortConditionList;

       ZYPTableUtil.clear(scrnMsg.Z);
       params[IDX_6] = scrnMsg.Z;

       return params;
   }
   // QC#13415 ADD End

   /**
    * Get Param NSAL1570 For Contract
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1570
    */
   public static  Object[] getParamNSAL1240ForContract(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       List<Object> parameters = new ArrayList<Object>();
       parameters.add(scrnMsg.xxPopPrm_P0); //[0]: CONFIG_EXST_MODE_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[1]: SVC_CONFIG_MSTR_PK
       parameters.add(scrnMsg.xxPopPrm_P0); //[2]: SER_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[3]: SVC_MACH_MSTR_PK
       parameters.add(scrnMsg.xxPopPrm_P0); //[4]: MDSE_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[5]: MDL_NM
       parameters.add(scrnMsg.xxPopPrm_P0); //[6]: SHIP_FEOM_DT
       parameters.add(scrnMsg.xxPopPrm_P0); //[7]: SHIP_THRU_DT
       parameters.add(scrnMsg.xxPopPrm_P0); //[8]: SVC_MACH_USG_STS_CD
       parameters.add((EZDBMsgArray) scrnMsg.Z); //[9]: SVC_MACH_MSTR_STS_CD, Array
       parameters.add(scrnMsg.xxPopPrm_P0); //[10]: SVC_MACH_MSTR_STS_EDIT_FLG
       parameters.add(scrnMsg.xxPopPrm_P0); //[11]: MACH_ALLOC_MODE_CODE
       parameters.add(scrnMsg.xxPopPrm_P0); //[12]: MAIN_UNIT_FLG
       parameters.add(scrnMsg.xxPopPrm_P0); //[13]: STK_STS_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[14]: WH_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[15]: SUB_WH_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[16]: SVC_SLN_SQ
       parameters.add(scrnMsg.xxPopPrm_P0); //[17]: SVC_SLN_NM
       parameters.add(scrnMsg.xxDsContrNumSrchTxt); //[18]: CONTR_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[19]: DS_OWNR_ACCT_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[20]: OWNR_LOC_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[21]: DS_BILL_TO_ACCT_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[22]: BILL_TO_LOC_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[23]: DS_CUR_LOC_ACCT_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[24]: CUR_LOC_NUM

       // Output Parameter
       parameters.add(scrnMsg.xxPopPrm_P0); //[25]: MDL_ID
       parameters.add(scrnMsg.xxPopPrm_P0); //[26]: MDL_NM
       parameters.add(scrnMsg.xxPopPrm_P0); //[27]: CONTR_PK
       parameters.add(scrnMsg.xxDsContrNumSrchTxt); //[28]: CONTR_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[29]: SVC_CONFIG_MSTR_PK
       parameters.add((EZDBMsgArray) scrnMsg.Z); //[30]: 
       return parameters.toArray(new Object[0]);
   }

   /**
    * Get Param NSAL1570 For Config
    * @param scrnMsg NWAL1570BMsg
    * @return Param NWAL1570
    */
   public static  Object[] getParamNSAL1240ForConfig(NWAL1570BMsg scrnMsg) {

       clearPopUpParam(scrnMsg);

       try {
           scrnMsg.svcConfigMstrPk.setValue(new BigDecimal(scrnMsg.xxSvcConfigMstrSrchTxt.getValue()));
       } catch (NumberFormatException e) {
           scrnMsg.svcConfigMstrPk.clear();
           scrnMsg.xxSvcConfigMstrSrchTxt.clear();
       }
       List<Object> parameters = new ArrayList<Object>();
       parameters.add(scrnMsg.xxPopPrm_P0); //[0]: CONFIG_EXST_MODE_CD
       parameters.add(scrnMsg.svcConfigMstrPk); //[1]: SVC_CONFIG_MSTR_PK
       parameters.add(scrnMsg.xxPopPrm_P0); //[2]: SER_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[3]: SVC_MACH_MSTR_PK
       parameters.add(scrnMsg.xxPopPrm_P0); //[4]: MDSE_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[5]: MDL_NM
       parameters.add(scrnMsg.xxPopPrm_P0); //[6]: SHIP_FEOM_DT
       parameters.add(scrnMsg.xxPopPrm_P0); //[7]: SHIP_THRU_DT
       parameters.add(scrnMsg.xxPopPrm_P0); //[8]: SVC_MACH_USG_STS_CD
       parameters.add((EZDBMsgArray) scrnMsg.Z); //[9]: SVC_MACH_MSTR_STS_CD, Array
       parameters.add(scrnMsg.xxPopPrm_P0); //[10]: SVC_MACH_MSTR_STS_EDIT_FLG
       parameters.add(scrnMsg.xxPopPrm_P0); //[11]: MACH_ALLOC_MODE_CODE
       parameters.add(scrnMsg.xxPopPrm_P0); //[12]: MAIN_UNIT_FLG
       parameters.add(scrnMsg.xxPopPrm_P0); //[13]: STK_STS_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[14]: WH_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[15]: SUB_WH_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[16]: SVC_SLN_SQ
       parameters.add(scrnMsg.xxPopPrm_P0); //[17]: SVC_SLN_NM
       parameters.add(scrnMsg.xxPopPrm_P0); //[18]: CONTR_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[19]: DS_OWNR_ACCT_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[20]: OWNR_LOC_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[21]: DS_BILL_TO_ACCT_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[22]: BILL_TO_LOC_CD
       parameters.add(scrnMsg.xxPopPrm_P0); //[23]: DS_CUR_LOC_ACCT_NUM
       parameters.add(scrnMsg.xxPopPrm_P0); //[24]: CUR_LOC_NUM

       // Output Parameter
       parameters.add(scrnMsg.xxPopPrm_P0); //[25]: MDL_ID
       parameters.add(scrnMsg.xxPopPrm_P0); //[26]: MDL_NM
       parameters.add(scrnMsg.xxPopPrm_P0); //[27]: CONTR_PK
       parameters.add(scrnMsg.xxPopPrm_P0); //[28]: CONTR_NUM
       parameters.add(scrnMsg.svcConfigMstrPk); //[29]: SVC_CONFIG_MSTR_PK
       parameters.add((EZDBMsgArray) scrnMsg.Z); //[30]: 
       return parameters.toArray(new Object[0]);
   }

   /**
    * check error in EZDCMsg. Does EZDCMsg have Error?
    * @param cMsg NWAL1570CMsg
    * @return true/has Error, false/don't have Error.
    */
   public static boolean hasMsgError(NWAL1570CMsg cMsg) {
       return cMsg != null && "E".equals(cMsg.getMessageKind());
   }

   /**
    * check screen called me. called by Menu screen?
    * @param argForSubScreen value of
    * EZDCommonHandler.getArgForSubScreen().
    * @return true/called by Menu screen, false/called by other
    * screen.
    */
   public static boolean isTransitedFromMenuScrn(Serializable argForSubScreen) {
       return argForSubScreen == null;
   }

   /**
    * setInitRequestData
    * @param scrnMsg NWAL1570BMsg
    * @param params initial parameters
    */
   public static void setInitRequestData(NWAL1570BMsg scrnMsg, Object[] params) {

       S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

       scrnMsg.clear();
       ZYPTableUtil.clear(scrnMsg.A);

       setValue(scrnMsg.glblCmpyCd, userProfSvc.getGlobalCompanyCode());
       setValue(scrnMsg.slsDt, ZYPDateUtil.getSalesDate());
       if (params != null && params.length > 0) {
           // set screen ID(Other Screen)
           scrnMsg.xxScrId.setValue(SCRN_ID_OTH);

           // [0]Real Time Inquiry
           setValue(scrnMsg.xxPgFlg, (String) params[0]);
           // [1]Result Mode
           setValue(scrnMsg.xxRsltModeCd, (String) params[1]);
           // [2]Display Mode
           setValue(scrnMsg.grpByDnldCd, (String) params[2]);
           // [3]Display By Item Name
           setValue(scrnMsg.dplyBy01ItemNm, (String) params[3]);
           // [4]Header Status
           List<String> hdrStsList = (ArrayList<String>)params[4];
           int i = 0;
           for (String ordHdrStsNm : hdrStsList) {
               setValue(scrnMsg.V.no(i).ordHdrStsNm_PR, ordHdrStsNm);
               i++;
           }
           scrnMsg.V.setValidCount(hdrStsList.size());
           // [5]Line Status
           List<String> lineStsList = (ArrayList<String>)params[5];
           i = 0;
           for (String ordLineStsNm : lineStsList) {
               setValue(scrnMsg.W.no(i).ordLineStsNm_PR, ordLineStsNm);
               i++;
           }
           scrnMsg.W.setValidCount(lineStsList.size());
           // [6]Return Line Status
           List<String> rtrnLineStsList = (ArrayList<String>)params[6];
           i = 0;
           for (String rtrnLineStsNm : rtrnLineStsList) {
               setValue(scrnMsg.X.no(i).rtrnLineStsNm_PR, rtrnLineStsNm);
               i++;
           }
           scrnMsg.W.setValidCount(rtrnLineStsList.size());
           // [7]Invoice Date From
           setValue(scrnMsg.invFromDt, (String) params[7]);
           // [8]Invoice Date To
           setValue(scrnMsg.invToDt, (String) params[8]);
           // [9]Aging
           setValue(scrnMsg.ordAgingBcktDescTxt, (String) params[9]);
           // [10]Order Source
           setValue(scrnMsg.xxCpoSrcTpSrchTxt, (String) params[10]);
           // [11]Include Pending Import Order Flag
           setValue(scrnMsg.xxInclPendImptOrdFlg, (String) params[11]);
           // [12]Order Date From
           setValue(scrnMsg.ordFromDt, (String) params[12]);
           // [13]Order Date To
           setValue(scrnMsg.ordToDt, (String) params[13]);
           // [14]Team
           setValue(scrnMsg.xxOrdTeamSrchTxt, (String) params[14]);
           // [15]Zone
           setValue(scrnMsg.xxOrdZnSrchTxt, (String) params[15]);
           // [16]Create By User
           setValue(scrnMsg.xxCratByUsrSrchTxt, (String) params[16]);
           // QC#15760 Add Start
           // [17]Order Category Code
           setValue(scrnMsg.xxDsOrdCatgSrchTxt, (String) params[17]);
           // [18]Service Machine Master PK
           setValue(scrnMsg.xxSvcMachMstrSrchTxt, (String) params[18]);
           // [19]Contract Number
           setValue(scrnMsg.xxDsContrNumSrchTxt, (String) params[19]);
           // QC#15760 Add End
           // START 2017/09/19 S.Katsuma [Sol#032(QC#13117),ADD]
           // [20]Ship To Name
           if (params.length > 20) {
               setValue(scrnMsg.xxShipToAcctNmSrchTxt, (String) params[20]);
           }
           // [21]Ship To Acct#
           if (params.length > 21) {
               setValue(scrnMsg.xxShipToAcctCdSrchTxt, (String) params[21]);
           }
           // [22]Ship To Loc#
           if (params.length > 22) {
               setValue(scrnMsg.xxShipToLocCdSrchTxt, (String) params[22]);
           }
           // [23]All Open Order
           if (params.length > 23) {
               setValue(scrnMsg.xxAllOpenOrdFlg, (String) params[23]);
               if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxAllOpenOrdFlg.getValue())) {
                   NWAL1570CommonLogic.setStatusByAllOpenOrder(scrnMsg);
                   NWAL1570CommonLogic.setStatusGuiAttrScrn00(scrnMsg, true);
               }
           }
           // END 2017/09/19 S.Katsuma [Sol#032(QC#13117),ADD]
       }
   }

}
