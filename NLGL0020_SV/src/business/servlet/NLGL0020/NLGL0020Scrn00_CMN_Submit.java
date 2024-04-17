/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update      Defect No
 * ------------------------------------------------------------------------------------
 * 08/30/2013     CSAI            N.Sekine          Create             MW Replace Initial
 * 07/03/2017     CITS            S.Endo            Update             QC#19042
 *</pre>
 */
public class NLGL0020Scrn00_CMN_Submit extends S21CommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;

        if (scrnMsg.xxDplyTab.getValue().equals(TAB_ID_DNLD)) {
            scrnMsg.addCheckItem(scrnMsg.whCd_E2);
            scrnMsg.addCheckItem(scrnMsg.xxSrchRqstDtTpCd_E2);
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_E1);
            scrnMsg.addCheckItem(scrnMsg.invtyOwnrCd_E1);
            scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_E1);

            scrnMsg.putErrorScreen();

            if (FLD_VALUE_COPY.equals(scrnMsg.xxSrchRqstDtTpCd_E2.getValue())) {

                for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).wmsMdseCd_F1);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).rwsQty_F1);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).wmsUomCd_F2);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).wmsStkStsCd_F2);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).wmsStkStsCd_F2);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).packCdTxt_F1);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).usrCdRefTxt_F1);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).serApvlReqFlg_F1);
                    scrnMsg.addCheckItem(scrnMsg.F.no(i).serNum_F1);
                    
                }
            }
        } else {

            List<Integer> outGetSelectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.I, FIELD_NAME_XXCHKBOX_I1, ZYPConstant.CHKBOX_ON_Y);

            if (outGetSelectedRows.isEmpty()) {
                for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.I.no(i).xxChkBox_I1);
                    scrnMsg.I.no(i).xxChkBox_I1.setErrorInfo(FLD_VALUE_INT_1, NLGM0036E);
                }
            }

            for (int i = 0; i < scrnMsg.I.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsRecId_I1);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).otbdOrdLineNum_I2);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsTaskCd_I2);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsMdseCd_I1);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsStkStsCd_I2);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsOrdQty_I1);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsOrdTpCd_I2);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).wmsTrxDtTmTs_I1);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).inbdOrdTpCd_I1);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).inbdOrdLineNum_I1);
                scrnMsg.addCheckItem(scrnMsg.I.no(i).packCdTxt_I1);
            }
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_40();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.F.no(i).wmsMdseCd_F1);
            }
            scrnMsg.putErrorScreen();
            return;
        }
        NLGL0020CommonLogic.inputFieldControl_NLGL0020Scrn00_Header_Protect(this, scrnMsg);
        if (TAB_ID_DNLD.equals(scrnMsg.xxDplyTab.getValue())) {
            NLGL0020CommonLogic.commonBtnControl_NLGL0020Scrn00_SUBMIT(this);
        }
    }
}
