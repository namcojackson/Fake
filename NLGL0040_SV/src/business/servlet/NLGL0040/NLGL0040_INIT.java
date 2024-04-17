/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0040.NLGL0040CMsg;
import business.servlet.NLGL0040.common.NLGL0040CommonLogic;
import business.servlet.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040_INIT extends S21INITCommonHandler implements NLGL0040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = NLGL0040CommonLogic.setRequestData_NLGL0040Scrn00_Function_20();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) bMsg;
        NLGL0040CMsg bizMsg = (NLGL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ID_LIST);
        NLGL0040CommonLogic.commonBtnControl_NLGL0040Scrn00_CMN_LIST(this);
        NLGL0040CommonLogic.btnControl_NLGL0040Scrn00_Prev_Disable(this);
    }

    /**
     * @param arg0
     */
    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NLGL0040BMsg scrnMsg = (NLGL0040BMsg) arg0;

        scrnMsg.whCd_H1.setNameForMessage(NAME_FOR_MESSAGE_WH);
        scrnMsg.wmsShipViaTpCd_D2.setNameForMessage(NAME_FOR_MESSAGE_SHIPVIACD);
        scrnMsg.wmsDescTxt_D2.setNameForMessage(NAME_FOR_MESSAGE_WMSDESCTXT);
        scrnMsg.mdBreakTpCd_P2.setNameForMessage(NAME_FOR_MESSAGE_MDBREAKTPCD);
        scrnMsg.rteGuideNum_D2.setNameForMessage(NAME_FOR_MESSAGE_RTEGUIDENUM);
        scrnMsg.pclCarrCd_D2.setNameForMessage(NAME_FOR_MESSAGE_PCLCARRCD);
        scrnMsg.pclMaxCapNum_D2.setNameForMessage(NAME_FOR_MESSAGE_PCLMAXCAPNUM);
        scrnMsg.pclPrtyCd_D2.setNameForMessage(NAME_FOR_MESSAGE_PCLPRTYCD);
        scrnMsg.ltlCarrCd_D2.setNameForMessage(NAME_FOR_MESSAGE_LTLCARRCD);
        scrnMsg.ltlMaxCapNum_D2.setNameForMessage(NAME_FOR_MESSAGE_LTLMAXCAPNUM);
        scrnMsg.ltlPrtyCd_D2.setNameForMessage(NAME_FOR_MESSAGE_LTLPRTYCD);
        scrnMsg.tlCarrCd_D2.setNameForMessage(NAME_FOR_MESSAGE_TLCARRCD);
        scrnMsg.tlMaxCapNum_D2.setNameForMessage(NAME_FOR_MESSAGE_TLMAXCAPNUM);
        scrnMsg.tlPrtyCd_D2.setNameForMessage(NAME_FOR_MESSAGE_TLPRTYCD);
    }
}
