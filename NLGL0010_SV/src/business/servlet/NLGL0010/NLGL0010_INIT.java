/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

import business.blap.NLGL0010.NLGL0010CMsg;
import business.servlet.NLGL0010.common.NLGL0010CommonLogic;
import business.servlet.NLGL0010.constant.NLGL0010Constant;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 09/20/2013     CSAI            Y.Miyauchi        Create            MW Replace Initial
 * 11/10/2015     CSAI            K.Lee             Update            S21NA Initial
 * 05/29/2017     CITS            S.Endo            Update            RS#3168
 *</pre>
 */
public class NLGL0010_INIT extends S21INITCommonHandler implements NLGL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = NLGL0010CommonLogic.setScrnBizFun_20();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLGL0010CommonLogic.commonBtnControl_Tab_SO_LIST(this);
        NLGL0010CommonLogic.busHdrControl_UnEnabel(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) arg0;
        // Header
        scrnMsg.whCd_02.setNameForMessage(NAME_FOR_MESSAGE_WH);
        scrnMsg.dateEntryL14If_02.setNameForMessage(NAME_FOR_MESSAGE_DT_TYP);
        scrnMsg.xxSoSrchFromDt_01.setNameForMessage(NAME_FOR_MESSAGE_FROM_DT);
        scrnMsg.xxSoSrchThruDt_01.setNameForMessage(NAME_FOR_MESSAGE_TO_DT);

        // Download Tab
        scrnMsg.xxTpCd_J2.setNameForMessage(NAME_FOR_MESSAGE_SUBMIT_TP);
        scrnMsg.whCd_J2.setNameForMessage(NAME_FOR_MESSAGE_WH);
        scrnMsg.xxRsdDt_J1.setNameForMessage(NAME_FOR_MESSAGE_RSD);
        scrnMsg.xxRddDt_J1.setNameForMessage(NAME_FOR_MESSAGE_RDD);
        scrnMsg.custOrdNum_J1.setNameForMessage(NAME_FOR_MESSAGE_CUST_PO);
        scrnMsg.tplSvcLvlCd_J2.setNameForMessage(NAME_FOR_MESSAGE_SHIP_VIA);
        scrnMsg.wmsFrtOutCd_J2.setNameForMessage(NAME_FOR_MESSAGE_FRT);

        for (int i = 0; i < scrnMsg.K.length(); i++) {
            scrnMsg.K.no(i).wmsMdseCd_K1.setNameForMessage(NAME_FOR_MESSAGE_ITEM);
            scrnMsg.K.no(i).wmsShipQty_K1.setNameForMessage(NAME_FOR_MESSAGE_QTY_UNITS);
            scrnMsg.K.no(i).wmsUomCd_K2.setNameForMessage(NAME_FOR_MESSAGE_UOM);
            scrnMsg.K.no(i).wmsStkStsCd_K2.setNameForMessage(NAME_FOR_MESSAGE_STK_STS);
        }

        // Upload Tab
        for (int i = 0; i < scrnMsg.O.length(); i++) {
            scrnMsg.O.no(i).wmsRecId_O1.setNameForMessage(NAME_FOR_MESSAGE_RECORDID);
            scrnMsg.O.no(i).otbdOrdLineNum_O1.setNameForMessage(NAME_FOR_MESSAGE_LINE_OTBD);
            scrnMsg.O.no(i).inbdOrdLineNum_O1.setNameForMessage(NAME_FOR_MESSAGE_LINE_INBD);
            scrnMsg.O.no(i).wmsTaskCd_O2.setNameForMessage(NAME_FOR_MESSAGE_TASK);
            scrnMsg.O.no(i).wmsOrdStsCd_O2.setNameForMessage(NAME_FOR_MESSAGE_ORD_STS);
            scrnMsg.O.no(i).wmsMdseCd_O1.setNameForMessage(NAME_FOR_MESSAGE_ITEM_UP);
            scrnMsg.O.no(i).wmsStkStsCd_O2.setNameForMessage(NAME_FOR_MESSAGE_HOLD_CODE);
            scrnMsg.O.no(i).wmsOrdQty_O1.setNameForMessage(NAME_FOR_MESSAGE_QUANTITY);
            scrnMsg.O.no(i).otbdOrdTpCd_O2.setNameForMessage(NAME_FOR_MESSAGE_ORD_TP_OTBD);
            scrnMsg.O.no(i).inbdOrdTpCd_O2.setNameForMessage(NAME_FOR_MESSAGE_ORD_TP_INBD);
            scrnMsg.O.no(i).wmsTrxDtTmTs_O1.setNameForMessage(NAME_FOR_MESSAGE_TRX_DT);
            scrnMsg.O.no(i).wmsTotWt_O1.setNameForMessage(NAME_FOR_MESSAGE_WT);
            scrnMsg.O.no(i).wmsCarrCd_O1.setNameForMessage(NAME_FOR_MESSAGE_CARR);
            scrnMsg.O.no(i).wmsTrailId_O1.setNameForMessage(NAME_FOR_MESSAGE_TRAILER_ID);
            scrnMsg.O.no(i).proNum_O1.setNameForMessage(NAME_FOR_MESSAGE_PRO_BILL);
            scrnMsg.O.no(i).bolNum_O1.setNameForMessage(NAME_FOR_MESSAGE_BOL);
            scrnMsg.O.no(i).wmsShipId_O1.setNameForMessage(NAME_FOR_MESSAGE_SHIPMENT);
            scrnMsg.O.no(i).wmsCntnrId_O1.setNameForMessage(NAME_FOR_MESSAGE_CONTAINER);
            scrnMsg.O.no(i).wmsOutCntnrNum_O1.setNameForMessage(NAME_FOR_MESSAGE_OUTERMOST_CONTAINER);
            scrnMsg.O.no(i).wmsFrtChrgAmt_O1.setNameForMessage(NAME_FOR_MESSAGE_FREIGHT_CHARGE);
            scrnMsg.O.no(i).tmsFrtChrgAmt_O1.setNameForMessage(NAME_FOR_MESSAGE_TMS_FREIGHT_CHARGE);
            scrnMsg.O.no(i).serNum_O1.setNameForMessage(NAME_FOR_MESSAGE_SERIAL_NUMBER);
            scrnMsg.O.no(i).estDockDtTmTs_O1.setNameForMessage(NAME_FOR_MESSAGE_ESTIMATED_DOCK_DATE);
            scrnMsg.O.no(i).xxDtTm_O1.setNameForMessage(NAME_FOR_MESSAGE_ESTIMATED_DOCK_DATE);
            scrnMsg.O.no(i).xxDtTm_O2.setNameForMessage(NAME_FOR_MESSAGE_CREATED_ON);
        }
    }
}
