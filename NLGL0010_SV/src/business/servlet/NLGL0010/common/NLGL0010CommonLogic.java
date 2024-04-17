/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010.common;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.blap.NLGL0010.NLGL0010CMsg;
import business.servlet.NLGL0010.NLGL0010BMsg;
import business.servlet.NLGL0010.constant.NLGL0010Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_TP;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 09/20/2013     CSAI            Y.Miyauchi        Create            MW Replace Initial
 * 01/17/2017     CITS            T.Kikuhara        Update            QC#16256
 * 05/29/2017     CITS            S.Endo            Update            RS#3168
 * 07/03/2017     CITS            S.Endo            Update            QC#19042
 *</pre>
 */
public class NLGL0010CommonLogic implements NLGL0010Constant {

    /**
     * The method explanation: This method sets BusinessId and
     * FunctionCode checked data of cMsg.(Search)
     * @return bizMsg NLGL0010CMsg
     */
    public static NLGL0010CMsg setScrnBizFun_20() {

        NLGL0010CMsg bizMsg;
        bizMsg = new NLGL0010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        return bizMsg;
    }

    /**
     * The method explanation: This method sets BusinessId and
     * FunctionCode checked data of cMsg.(Update/Insert/Delete)
     * @return bizMsg NLGL0010CMsg
     */
    public static NLGL0010CMsg setScrnBizFun_40() {

        NLGL0010CMsg bizMsg;
        bizMsg = new NLGL0010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        return bizMsg;
    }

    /**
     * The method explanation: This method sets Footer Button
     * Propterties when Tab is List.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_Tab_SO_LIST(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets Footer Button
     * Propterties when Tab is Status.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_Tab_SO_STATUS(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets Footer Button
     * Propterties when Tab is DownLoad.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_Tab_SO_DNL(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets Footer Button
     * Propterties when Tab is Upload.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_Tab_SO_UPL(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busHdrControl_Enabel(EZDCommonHandler scrnAppli, NLGL0010BMsg scrnMsg) {

        scrnMsg.whCd_02.setInputProtected(true);
        scrnMsg.wmsSoId_01.setInputProtected(true);
        scrnMsg.custOrdNum_01.setInputProtected(true);
        scrnMsg.dateEntryL14If_02.setInputProtected(true);
        scrnMsg.xxSoSrchFromDt_01.setInputProtected(true);
        scrnMsg.xxSoSrchThruDt_01.setInputProtected(true);
        scrnMsg.sceOrdTpCd_02.setInputProtected(true);
        scrnMsg.wmsFrtOutCd_02.setInputProtected(true);
        scrnMsg.shipToCustCd_01.setInputProtected(true);
        scrnMsg.billToCustCd_01.setInputProtected(true);
        scrnMsg.wmsTrxCd_02.setInputProtected(true);
        scrnMsg.wmsMdseCd_01.setInputProtected(true);
        scrnMsg.tplSvcLvlCd_J2.setInputProtected(true);
        scrnMsg.wmsShipId_01.setInputProtected(true);
        scrnMsg.bolNum_01.setInputProtected(true);
        scrnMsg.wmsWaveId_01.setInputProtected(true);
        scrnMsg.xxChkBox_01.setInputProtected(true);
        scrnMsg.rtlWhCd_01.setInputProtected(true);
        scrnMsg.rtlWhNm_01.setInputProtected(true);
        scrnMsg.invtyOwnrCd_01.setInputProtected(true);
        scrnAppli.setButtonEnabled(BTN_SEARCH[0], false);
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busHdrControl_UnEnabel(EZDCommonHandler scrnAppli, NLGL0010BMsg scrnMsg) {

        scrnMsg.whCd_02.setInputProtected(false);
        scrnMsg.wmsSoId_01.setInputProtected(false);
        scrnMsg.custOrdNum_01.setInputProtected(false);
        scrnMsg.dateEntryL14If_02.setInputProtected(false);
        scrnMsg.xxSoSrchFromDt_01.setInputProtected(false);
        scrnMsg.xxSoSrchThruDt_01.setInputProtected(false);
        scrnMsg.sceOrdTpCd_02.setInputProtected(false);
        scrnMsg.wmsFrtOutCd_02.setInputProtected(false);
        scrnMsg.shipToCustCd_01.setInputProtected(false);
        scrnMsg.billToCustCd_01.setInputProtected(false);
        scrnMsg.wmsTrxCd_02.setInputProtected(false);
        scrnMsg.wmsMdseCd_01.setInputProtected(false);
        scrnMsg.tplSvcLvlCd_J2.setInputProtected(false);
        scrnMsg.wmsShipId_01.setInputProtected(false);
        scrnMsg.bolNum_01.setInputProtected(false);
        scrnMsg.wmsWaveId_01.setInputProtected(false);
        scrnMsg.xxChkBox_01.setInputProtected(false);
        scrnMsg.rtlWhCd_01.setInputProtected(false);
        scrnMsg.invtyOwnrCd_01.setInputProtected(false);
        scrnAppli.setButtonEnabled(BTN_SEARCH[0], true);
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busDNLDControl_Enabel(EZDCommonHandler scrnAppli, NLGL0010BMsg scrnMsg) {

        scrnMsg.whCd_J2.setInputProtected(false);
        scrnMsg.xxRsdDt_J1.setInputProtected(false);
        scrnMsg.xxRddDt_J1.setInputProtected(false);
        scrnMsg.custOrdNum_J1.setInputProtected(false);
        scrnMsg.tplSvcLvlCd_J2.setInputProtected(false);
        scrnMsg.wmsFrtOutCd_J2.setInputProtected(false);
        scrnMsg.rtlWhCd_J1.setInputProtected(false);
        scrnMsg.invtyOwnrCd_J1.setInputProtected(false);
        scrnMsg.schdDelyDt_J1.setInputProtected(false);
        scrnMsg.carrCd_J1.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_J1.setInputProtected(false);
        scrnMsg.rtrnItemInclFlg_J1.setInputProtected(false);
        scrnMsg.svcConfigMstrPk_J1.setInputProtected(false);
        scrnMsg.asmReqFlg_J1.setInputProtected(false);

        scrnMsg.K.setInputProtected(false);
        if (!WMS_ORD_TP.OUTBOUND_STOCK_STATUS_CHANGE.equals(scrnMsg.I.no(0).fill40Txt_I1.getValue())) {
            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                scrnMsg.K.no(i).wmsStkStsCd_K4.setInputProtected(true);
            }
        }

        scrnAppli.setButtonEnabled(BTN_CHECK_ALL_OF_DNLD[0], true);
        scrnAppli.setButtonEnabled(BTN_UNCHECK_ALL_OF_DNLD[0], true);
        scrnAppli.setButtonEnabled(BTN_INSERT_ROW_OF_DNLD[0], true);
        scrnAppli.setButtonEnabled(BTN_DELETE_ROW_OF_DNLD[0], true);
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busDNLDControl_Disenabel(EZDCommonHandler scrnAppli, NLGL0010BMsg scrnMsg) {

        scrnMsg.whCd_J2.setInputProtected(true);
        scrnMsg.xxRsdDt_J1.setInputProtected(true);
        scrnMsg.xxRddDt_J1.setInputProtected(true);
        scrnMsg.custOrdNum_J1.setInputProtected(true);
        scrnMsg.tplSvcLvlCd_J2.setInputProtected(true);
        scrnMsg.wmsFrtOutCd_J2.setInputProtected(true);
        scrnMsg.rtlWhCd_J1.setInputProtected(true);
        scrnMsg.invtyOwnrCd_J1.setInputProtected(true);
        scrnMsg.schdDelyDt_J1.setInputProtected(true);
        scrnMsg.carrCd_J1.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_J1.setInputProtected(true);
        scrnMsg.rtrnItemInclFlg_J1.setInputProtected(true);
        scrnMsg.svcConfigMstrPk_J1.setInputProtected(true);
        scrnMsg.asmReqFlg_J1.setInputProtected(true);
        scrnMsg.K.setInputProtected(true);

        scrnAppli.setButtonEnabled(BTN_CHECK_ALL_OF_DNLD[0], false);
        scrnAppli.setButtonEnabled(BTN_UNCHECK_ALL_OF_DNLD[0], false);
        scrnAppli.setButtonEnabled(BTN_INSERT_ROW_OF_DNLD[0], false);
        scrnAppli.setButtonEnabled(BTN_DELETE_ROW_OF_DNLD[0], false);
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busSoListControl_DataList(NLGL0010BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).altDocNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).wmsRqstDtTmTs_A1.setInputProtected(true);
            scrnMsg.A.no(i).custOrdNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).chrgToCustCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipToCustCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).wmsShipToNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).firstLineAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxMsgTxt_A1.setInputProtected(true);
        }
        scrnMsg.setFocusItem(scrnMsg.whCd_02);
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busSoStsControl_DataList(NLGL0010BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).fill40Txt_B1.setInputProtected(true);
            scrnMsg.B.no(i).wmsOrdNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).altDocNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).chrgToCustCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).shipToCustCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).custOrdNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).totShipPrcAmtNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).wmsResrcTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).mixPltPltNoteTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).asgShipViaCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).conslSoId_B1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).wmsShipToNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).wmsShipToNm_C2.setInputProtected(true);
            scrnMsg.C.no(i).wmsShipToCtacNm_C1.setInputProtected(true);
            scrnMsg.C.no(i).fill256Txt_C1.setInputProtected(true);
            scrnMsg.C.no(i).ctyAddr_C1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.D.no(i).xxMsgTxt_D1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            scrnMsg.E.no(i).wmsMdseCd_E1.setInputProtected(true);
            scrnMsg.E.no(i).mdseDescShortTxt_E2.setInputProtected(true);
            scrnMsg.E.no(i).mdseCdSetCd_E1.setInputProtected(true);
            scrnMsg.E.no(i).custMdseCd_E1.setInputProtected(true);
            scrnMsg.E.no(i).wmsUpcCd_E1.setInputProtected(true);
            scrnMsg.E.no(i).fill40Txt_E1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
            scrnMsg.F.no(i).wmsRecId_F1.setInputProtected(true);
            scrnMsg.F.no(i).otbdOrdLineNum_F1.setInputProtected(true);
            scrnMsg.F.no(i).inbdOrdLineNum_F1.setInputProtected(true);
            scrnMsg.F.no(i).wmsMdseCd_F1.setInputProtected(true);
            scrnMsg.F.no(i).wmsOpId_F1.setInputProtected(true);
            scrnMsg.F.no(i).wmsCarrCd_F1.setInputProtected(true);
            scrnMsg.F.no(i).proNum_F1.setInputProtected(true);
            scrnMsg.F.no(i).modByTxt_F1.setInputProtected(true);
            scrnMsg.F.no(i).serNum_F1.setInputProtected(true);
            scrnMsg.F.no(i).otbdOrdNum_F1.setInputProtected(true);
            scrnMsg.F.no(i).inbdOrdNum_F1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
            scrnMsg.G.no(i).wmsBolNum_G1.setInputProtected(true);
            scrnMsg.G.no(i).mstrBolNum_G1.setInputProtected(true);
            scrnMsg.G.no(i).carrProId_G1.setInputProtected(true);
            scrnMsg.G.no(i).altDocNum_G1.setInputProtected(true);
            scrnMsg.G.no(i).wmsTrkNum_G1.setInputProtected(true);
            scrnMsg.G.no(i).wmsMdseCd_G1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            scrnMsg.H.no(i).wmsShipId_H1.setInputProtected(true);
            scrnMsg.H.no(i).bolNum_H1.setInputProtected(true);
            scrnMsg.H.no(i).xxLogDtlTxt_H1.setInputProtected(true);
        }
    }

    /**
     * The method explanation: This method sets enable setting when
     * radiobutton is copy.
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busDNLDControl_DataList(EZDCommonHandler scrnAppli, NLGL0010BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
            scrnMsg.I.no(i).fill40Txt_I1.setInputProtected(true);
            scrnMsg.I.no(i).wmsOrdNum_I1.setInputProtected(true);
            scrnMsg.I.no(i).altDocNum_I1.setInputProtected(true);
            scrnMsg.I.no(i).chrgToCustCd_I1.setInputProtected(true);
            scrnMsg.I.no(i).shipToCustCd_I1.setInputProtected(true);
            scrnMsg.I.no(i).custOrdNum_I1.setInputProtected(true);
            scrnMsg.I.no(i).totShipPrcAmtNum_I1.setInputProtected(true);
            scrnMsg.I.no(i).conslSoId_I1.setInputProtected(true);
            scrnMsg.I.no(i).wmsResrcTxt_I1.setInputProtected(true);
            scrnMsg.I.no(i).mixPltPltNoteTxt_I1.setInputProtected(true);
            scrnMsg.I.no(i).asgShipViaCd_I1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            scrnMsg.L.no(i).wmsShipToNm_L1.setInputProtected(true);
            scrnMsg.L.no(i).wmsShipToNm_L2.setInputProtected(true);
            scrnMsg.L.no(i).wmsShipToCtacNm_L1.setInputProtected(true);
            scrnMsg.L.no(i).fill256Txt_L1.setInputProtected(true);
            scrnMsg.L.no(i).ctyAddr_L1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.M.getValidCount(); i++) {
            scrnMsg.M.no(i).xxMsgTxt_M1.setInputProtected(true);
        }
        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
            scrnMsg.N.no(i).wmsMdseCd_N1.setInputProtected(true);
            scrnMsg.N.no(i).mdseDescShortTxt_N1.setInputProtected(true);
            scrnMsg.N.no(i).mdseCdSetCd_N1.setInputProtected(true);
            scrnMsg.N.no(i).custMdseCd_N1.setInputProtected(true);
            scrnMsg.N.no(i).wmsUpcCd_N1.setInputProtected(true);
            scrnMsg.N.no(i).fill40Txt_N1.setInputProtected(true);
        }

        scrnMsg.whCd_J2.setInputProtected(false);
        scrnMsg.xxRsdDt_J1.setInputProtected(false);
        scrnMsg.xxRddDt_J1.setInputProtected(false);
        scrnMsg.custOrdNum_J1.setInputProtected(false);
        scrnMsg.tplSvcLvlCd_J2.setInputProtected(false);
        scrnMsg.wmsFrtOutCd_J2.setInputProtected(false);
        scrnMsg.K.setInputProtected(false);

        scrnAppli.setButtonEnabled(BTN_CHECK_ALL_OF_DNLD[0], true);
        scrnAppli.setButtonEnabled(BTN_UNCHECK_ALL_OF_DNLD[0], true);
        scrnAppli.setButtonEnabled(BTN_INSERT_ROW_OF_DNLD[0], true);
        scrnAppli.setButtonEnabled(BTN_DELETE_ROW_OF_DNLD[0], true);
    }

    /**
     * The method explanation: This method sets enable setting when
     * radio button is copy.
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busUPDControl_DataList(NLGL0010BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
            scrnMsg.O.no(i).xxChkBox_O1.setInputProtected(false);
            scrnMsg.O.no(i).otbdOrdLineNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).inbdOrdLineNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsTaskCd_O2.setInputProtected(false);
            scrnMsg.O.no(i).wmsOrdStsCd_O2.setInputProtected(false);
            scrnMsg.O.no(i).wmsMdseCd_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsStkStsCd_O2.setInputProtected(false);
            scrnMsg.O.no(i).wmsOrdQty_O1.setInputProtected(false);
            scrnMsg.O.no(i).otbdOrdNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).otbdOrdTpCd_O2.setInputProtected(false);
            scrnMsg.O.no(i).inbdOrdNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).inbdOrdTpCd_O2.setInputProtected(false);
            scrnMsg.O.no(i).wmsTrxDtTmTs_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsTotWt_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsCarrCd_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsTrailId_O1.setInputProtected(false);
            scrnMsg.O.no(i).proNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).bolNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsShipId_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsCntnrId_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsOutCntnrNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).wmsFrtChrgAmt_O1.setInputProtected(false);
            scrnMsg.O.no(i).tmsFrtChrgAmt_O1.setInputProtected(false);
            scrnMsg.O.no(i).serNum_O1.setInputProtected(false);
            scrnMsg.O.no(i).xxDt10Dt_O1.setInputProtected(false);
            scrnMsg.O.no(i).xxDtTm_O1.setInputProtected(true);
        }
    }

    /**
     * The method explanation: This method sets enable setting when
     * copy btn click.
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busUPDControl_Copy(NLGL0010BMsg scrnMsg) {

        int newRow = scrnMsg.O.getValidCount() - 1;
        scrnMsg.O.no(newRow).otbdOrdLineNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).inbdOrdLineNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTaskCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsOrdStsCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsMdseCd_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsStkStsCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsOrdQty_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).otbdOrdNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).otbdOrdTpCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).inbdOrdNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).inbdOrdTpCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTrxDtTmTs_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTotWt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsCarrCd_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTrailId_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).proNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).bolNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsShipId_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsCntnrId_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsOutCntnrNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsFrtChrgAmt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).tmsFrtChrgAmt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).serNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).xxDt10Dt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).xxDtTm_O1.setInputProtected(true);
    }

    /**
     * The method explanation: This method sets enable setting when
     * insert btn click.
     * @param scrnMsg NLGL0010BMsg
     */
    public static void busUPDControl_Insert(NLGL0010BMsg scrnMsg) {

        int newRow = 0;
        if (scrnMsg.O.getValidCount() == 0) {
            newRow = 0;
        } else {
            newRow = scrnMsg.O.getValidCount() - 1;
        }
        scrnMsg.O.no(newRow).otbdOrdLineNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).inbdOrdLineNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTaskCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsOrdStsCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsMdseCd_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsStkStsCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsOrdQty_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).otbdOrdNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).otbdOrdTpCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).inbdOrdNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).inbdOrdTpCd_O2.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTrxDtTmTs_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTotWt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsCarrCd_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsTrailId_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).proNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).bolNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsShipId_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsCntnrId_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsOutCntnrNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).wmsFrtChrgAmt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).tmsFrtChrgAmt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).serNum_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).xxDt10Dt_O1.setInputProtected(false);
        scrnMsg.O.no(newRow).xxDtTm_O1.setInputProtected(true);
    }

    /**
     * Button Control for EDIT Tab diplayed and after SUBMIT process
     * end.
     * @param scrnAppli EZDCommonHandler
     */
    public static void commonBtnControl_NLGL0010Scrn00_SUBMIT(EZDCommonHandler scrnAppli) {

        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        } else {
            scrnAppli.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
            scrnAppli.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        }
        // QC#16256 add end
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BIZ_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

}
