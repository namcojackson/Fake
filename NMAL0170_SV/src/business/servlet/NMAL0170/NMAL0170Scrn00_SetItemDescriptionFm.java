/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.NMAM0041E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import business.blap.NMAL0170.NMAL0170CMsg;
// import business.servlet.NMAL0170.constant.NMAL0170Constant;

import business.blap.NMAL0170.NMAL0170CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL0170 Supersession Staging Info Inquiry
 * Function Name : set Item Description
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/21/2016   CITS            S.Tanikawa      Create          QC#6176
 *</pre>
 */
public class NMAL0170Scrn00_SetItemDescriptionFm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        int idx = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).supdFromMdseCd_A1)) {
            scrnMsg.A.no(idx).supdFromMdseCd_A1.setErrorInfo(1, NMAM0041E, new String[] {"Supersedes" });
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).supdFromMdseCd_A1);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));

        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).supdFromMdseCd_A1);
        scrnMsg.putErrorScreen();

    }
}
