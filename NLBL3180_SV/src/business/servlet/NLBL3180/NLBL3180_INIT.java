/*
 * <pre>Copyright (c) 2021 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3180;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3180.NLBL3180CMsg;
import business.servlet.NLBL3180.common.NLBL3180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Ship Detail Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2021/06/18   CITS            J.Evangelista   Create          QC#58876
 * </pre>
 */
public class NLBL3180_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3180BMsg scrnMsg = (NLBL3180BMsg) bMsg;

        scrnMsg.clear();
        ZYPTableUtil.clear(scrnMsg.A);

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            for (int i = 0; i < params.length; i++) {
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, (String) params[i]);
                } else if (i == 1) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum, (String) params[i]);
                } else if (i == 2) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineNum, (String) params[i]);
                } else if (i == 3) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqLineSubNum, (BigDecimal) params[i]);
                } else if (i == 4) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.trxRefNum, (String) params[i]);
                } else if (i == 5) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_H1, (String) params[i]);
                }
            }
        }

        NLBL3180CMsg bizMsg = new NLBL3180CMsg();
        bizMsg.setBusinessID("NLBL3180");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3180BMsg scrnMsg = (NLBL3180BMsg) bMsg;
        NLBL3180CMsg bizMsg = (NLBL3180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3180CommonLogic.setTableBGColor(scrnMsg);
        NLBL3180CommonLogic.initDisplayInfo(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3180BMsg scrnMsg = (NLBL3180BMsg) bMsg;

        scrnMsg.soNum_H1.setNameForMessage("SO#");
        scrnMsg.bolVchNum_H1.setNameForMessage("MNX#");
    }
}
