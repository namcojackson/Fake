/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 11/10/2015     CSAI            K.Lee             Update             S21NA Initial
 * 05/25/2017     CITS            S.Endo            Update             RS#3173
 * 07/03/2017     CITS            S.Endo            Update             QC#19042
 *</pre>
 */
public class NLGL0020_INIT extends S21INITCommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_LIST);
        NLGL0020CommonLogic.commonBtnControl_NLGL0020Scrn00_POLIST_TAB(this);
        NLGL0020CommonLogic.inputFieldControl_NLGL0020Scrn00_Header_Unprotect(this, scrnMsg);
        NLGL0020CommonLogic.btnControl_NLGL0020Scrn00_Prev_Disable(this);
    }

    /**
     * @param arg0
     */
    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) arg0;

        scrnMsg.whCd_02.setNameForMessage(NAME_FOR_MESSAGE_WH);
        scrnMsg.rtlWhCd_01.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH);
        scrnMsg.invtyOwnrCd_01.setNameForMessage(NAME_FOR_MESSAGE_INVTY_OWNER);
        scrnMsg.wmsPoId_01.setNameForMessage(NAME_FOR_MESSAGE_PO_ID);
        scrnMsg.xxSrchRqstDtTpCd_02.setNameForMessage(NAME_FOR_MESSAGE_DT_TYP);
        scrnMsg.xxSoSrchFromDt_01.setNameForMessage(NAME_FOR_MESSAGE_FROM_DT);
        scrnMsg.xxSoSrchThruDt_01.setNameForMessage(NAME_FOR_MESSAGE_TO_DT);
        scrnMsg.wmsMdseCd_01.setNameForMessage(NAME_FOR_MESSAGE_ITEM_CD);
        scrnMsg.sceOrdTpCd_02.setNameForMessage(NAME_FOR_MESSAGE_PUR_TYP);
        scrnMsg.wmsTrxCd_02.setNameForMessage(NAME_FOR_MESSAGE_TRX_CD);

        scrnMsg.xxSrchRqstDtTpCd_E2.setNameForMessage(NAME_FOR_MESSAGE_SUBMIT_TYPE);
        scrnMsg.whCd_E2.setNameForMessage(NAME_FOR_MESSAGE_WH);
        scrnMsg.rtlWhCd_E1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH);
        scrnMsg.invtyOwnrCd_E1.setNameForMessage(NAME_FOR_MESSAGE_INVTY_OWNER);
        scrnMsg.svcConfigMstrPk_E1.setNameForMessage(NAME_FOR_MESSAGE_CONFIG_ID);
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).wmsLineNum_F1.setNameForMessage(NAME_FOR_MESSAGE_LINE);
            scrnMsg.F.no(i).wmsMdseCd_F1.setNameForMessage(NAME_FOR_MESSAGE_ITEM);
            scrnMsg.F.no(i).rwsQty_F1.setNameForMessage(NAME_FOR_MESSAGE_QTY_UNIT);
            scrnMsg.F.no(i).wmsUomCd_F2.setNameForMessage(NAME_FOR_MESSAGE_UOM);
            scrnMsg.F.no(i).wmsStkStsCd_F2.setNameForMessage(NAME_FOR_MESSAGE_STOCK_STS);
            scrnMsg.F.no(i).packCdTxt_F1.setNameForMessage(NAME_FOR_MESSAGE_PACK_CD);
            scrnMsg.F.no(i).serApvlReqFlg_F1.setNameForMessage(NAME_FOR_MESSAGE_SER_APVL_REQ_FLG);
            scrnMsg.F.no(i).usrCdRefTxt_F1.setNameForMessage(NAME_FOR_MESSAGE_DPS_CD);
            scrnMsg.F.no(i).serNum_F1.setNameForMessage(NAME_FOR_MESSAGE_STOCK_STS);
        }

        for (int i = 0; i < scrnMsg.I.length(); i++) {
            scrnMsg.I.no(i).xxProcFlgNm_I1.setNameForMessage(NAME_FOR_MESSAGE_PROC);
            scrnMsg.I.no(i).wmsRecId_I1.setNameForMessage(NAME_FOR_MESSAGE_REC_ID);
            scrnMsg.I.no(i).otbdOrdLineNum_I2.setNameForMessage(NAME_FOR_MESSAGE_LINE);
            scrnMsg.I.no(i).wmsTaskCd_I2.setNameForMessage(NAME_FOR_MESSAGE_TASK);
            scrnMsg.I.no(i).wmsMdseCd_I1.setNameForMessage(NAME_FOR_MESSAGE_ITEM);
            scrnMsg.I.no(i).wmsStkStsCd_I2.setNameForMessage(NAME_FOR_MESSAGE_HLD_CD);
            scrnMsg.I.no(i).wmsOrdQty_I1.setNameForMessage(NAME_FOR_MESSAGE_QNTY);
            scrnMsg.I.no(i).wmsOrdTpCd_I2.setNameForMessage(NAME_FOR_MESSAGE_PUR_TYP);
            scrnMsg.I.no(i).wmsTrxDtTmTs_I1.setNameForMessage(NAME_FOR_MESSAGE_TRN_DT);
            scrnMsg.I.no(i).inbdOrdTpCd_I1.setNameForMessage(NAME_FOR_MESSAGE_INBD_TYP);
            scrnMsg.I.no(i).inbdOrdLineNum_I1.setNameForMessage(NAME_FOR_MESSAGE_INBD_NUM);
            scrnMsg.I.no(i).packCdTxt_I1.setNameForMessage(NAME_FOR_MESSAGE_PACK_CD);
        }
    }
}
