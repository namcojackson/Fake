/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLGL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0040.NLGL0040CMsg;
import business.servlet.NLGL0040.common.NLGL0040CommonLogic;
import business.servlet.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040Scrn00_CMN_Submit extends S21CommonHandler implements NLGL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.wmsShipViaTpCd_D2);
        scrnMsg.addCheckItem(scrnMsg.rteGuideNum_D2);
        scrnMsg.addCheckItem(scrnMsg.wmsDescTxt_D2);
        scrnMsg.addCheckItem(scrnMsg.mdBreakTpCd_P2);
        scrnMsg.addCheckItem(scrnMsg.pclCarrCd_D2);
        scrnMsg.addCheckItem(scrnMsg.pclMaxCapNum_D2);
        scrnMsg.addCheckItem(scrnMsg.pclPrtyCd_D2);
        scrnMsg.addCheckItem(scrnMsg.ltlCarrCd_D2);
        scrnMsg.addCheckItem(scrnMsg.ltlMaxCapNum_D2);
        scrnMsg.addCheckItem(scrnMsg.ltlPrtyCd_D2);
        scrnMsg.addCheckItem(scrnMsg.tlCarrCd_D2);
        scrnMsg.addCheckItem(scrnMsg.tlMaxCapNum_D2);
        scrnMsg.addCheckItem(scrnMsg.tlPrtyCd_D2);

        if (NLGL0040CommonLogic.inuptField_Minus_Check(scrnMsg.pclMaxCapNum_D2.getValue())) {
            scrnMsg.setMessageInfo(ZZM9004E, new String[] {NAME_FOR_MESSAGE_PCLMAXCAPNUM });
        }

        if (NLGL0040CommonLogic.inuptField_Minus_Check(scrnMsg.ltlMaxCapNum_D2.getValue())) {
            scrnMsg.setMessageInfo(ZZM9004E, new String[] {NAME_FOR_MESSAGE_LTLMAXCAPNUM });
        }

        if (NLGL0040CommonLogic.inuptField_Minus_Check(scrnMsg.tlMaxCapNum_D2.getValue())) {
            scrnMsg.setMessageInfo(ZZM9004E, new String[] {NAME_FOR_MESSAGE_TLMAXCAPNUM });
        }
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = NLGL0040CommonLogic.setRequestData_NLGL0040Scrn00_Function_40();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode()) && scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            if (NLGM0050E.equals(scrnMsg.getMessageCode())) {
                scrnMsg.addCheckItem(scrnMsg.wmsShipViaTpCd_D2);
                scrnMsg.putErrorScreen();
            }
            return;
        }
        NLGL0040CommonLogic.inputFieldControl_NLGL0040Scrn00_Edit(this, scrnMsg);
        NLGL0040CommonLogic.commonBtnControl_NLGL0040Scrn00_SUBMIT(this);
    }
}
