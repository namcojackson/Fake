/**
 * <pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0010;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0010.NLGL0010CMsg;
import business.servlet.NLGL0010.common.NLGL0010CommonLogic;
import business.servlet.NLGL0010.constant.NLGL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * SO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 09/20/2013     CSAI            Y.Miyauchi        Create            MW Replace Initial
 * 05/29/2017     CITS            S.Endo            Update            RS#3168
 * 07/03/2017     CITS            S.Endo            Update            QC#19042
 *</pre>
 */
public class NLGL0010Scrn00_CMN_Submit extends S21CommonHandler implements NLGL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;

        if (TAB_SO_DNLD_EDT.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.addCheckItem(scrnMsg.xxTpCd_J2);
            scrnMsg.addCheckItem(scrnMsg.whCd_J2);
            scrnMsg.addCheckItem(scrnMsg.xxRsdDt_J1);
            scrnMsg.addCheckItem(scrnMsg.xxRddDt_J1);
            scrnMsg.addCheckItem(scrnMsg.custOrdNum_J1);
            scrnMsg.addCheckItem(scrnMsg.tplSvcLvlCd_J2);
            scrnMsg.addCheckItem(scrnMsg.wmsFrtOutCd_J2);
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_J1);
            scrnMsg.addCheckItem(scrnMsg.invtyOwnrCd_J1);
            scrnMsg.addCheckItem(scrnMsg.schdDelyDt_J1);
            scrnMsg.addCheckItem(scrnMsg.carrCd_J1);
            scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_J1);
            scrnMsg.addCheckItem(scrnMsg.rtrnItemInclFlg_J1);
            scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_J1);
            scrnMsg.addCheckItem(scrnMsg.asmReqFlg_J1);

            if (TP_CD_COPY.equals(scrnMsg.xxTpCd_J2.getValue())) {

                for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).wmsMdseCd_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).wmsShipQty_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).wmsUomCd_K2);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).wmsStkStsCd_K2);
                    if (WMS_ORD_TP.OUTBOUND_STOCK_STATUS_CHANGE.equals(scrnMsg.I.no(0).fill40Txt_I1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.K.no(i).wmsStkStsCd_K4)) {
                        scrnMsg.K.no(i).wmsStkStsCd_K4.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_STK_STS_TO});
                        scrnMsg.addCheckItem(scrnMsg.K.no(i).wmsStkStsCd_K4);
                    }
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).packCdTxt_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).svcConfigMstrPk_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).backOrdFlg_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).backOrdImpctTpCd_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).rmvConfigFlg_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).indConfigFlg_K1);
                    scrnMsg.addCheckItem(scrnMsg.K.no(i).serNum_K1);
                }
            }
        } else {

            List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.O, FIELD_NAME_XXCHKBOX_O1, ZYPConstant.CHKBOX_ON_Y);

            if (outGetSelectedRows.isEmpty()) {

                for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.O.no(i).xxChkBox_O1);
                    scrnMsg.O.no(i).xxChkBox_O1.setErrorInfo(FLD_VALUE_INT_1, NLGM0036E);
                }
            }

            for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {

                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsRecId_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).otbdOrdLineNum_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).inbdOrdLineNum_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsTaskCd_O2);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsOrdStsCd_O2);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsOrdQty_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsTrxDtTmTs_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsTotWt_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).wmsFrtChrgAmt_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).xxDt10Dt_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).otbdOrdNum_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).inbdOrdNum_O1);
                scrnMsg.addCheckItem(scrnMsg.O.no(i).tmsFrtChrgAmt_O1);
            }
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = NLGL0010CommonLogic.setScrnBizFun_40();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0010BMsg scrnMsg = (NLGL0010BMsg) bMsg;
        NLGL0010CMsg bizMsg = (NLGL0010CMsg) cMsg;

        if (TAB_SO_UPLD_EDT.equals(scrnMsg.xxDplyTab.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) //
                    && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_WARNING) {
                if (NLGM0076W.equals(scrnMsg.getMessageCode())) {

                    for (int i = 0; i < scrnMsg.O.getValidCount(); i++) {
                        scrnMsg.addCheckItem(scrnMsg.O.no(i).inbdOrdNum_O1);
                        scrnMsg.addCheckItem(scrnMsg.O.no(i).otbdOrdNum_O1);
                        if (!ZYPCommonFunc.hasValue(scrnMsg.O.no(i).otbdOrdNum_O1) //
                                && !ZYPCommonFunc.hasValue(scrnMsg.O.no(i).inbdOrdNum_O1)) {
                            scrnMsg.O.no(i).otbdOrdNum_O1.setErrorInfo(2, NLGM0075W);
                            scrnMsg.O.no(i).inbdOrdNum_O1.setErrorInfo(2, NLGM0075W);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
                return;
            }

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            // When Tab is Update, do same as onclick upload edit tab
            NLGL0010CommonLogic.busUPDControl_DataList(scrnMsg);
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
                if (NLGM0077E.equals(scrnMsg.getMessageCode())) {
                    EZDMsg.copy(bizMsg, null, scrnMsg, null);
                    for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                        scrnMsg.addCheckItem(scrnMsg.K.no(i).wmsMdseCd_K1);
                    }
                    scrnMsg.putErrorScreen();
                } else if (ZZM9037E.equals(scrnMsg.getMessageCode())) {
                    EZDMsg.copy(bizMsg, null, scrnMsg, null);
                    scrnMsg.addCheckItem(scrnMsg.tplSvcLvlCd_J2);
                    scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_J1);
                    scrnMsg.addCheckItem(scrnMsg.carrCd_J1);
                    scrnMsg.putErrorScreen();
                }
                return;
            }
            NLGL0010CommonLogic.commonBtnControl_NLGL0010Scrn00_SUBMIT(this);
        }
    }
}
