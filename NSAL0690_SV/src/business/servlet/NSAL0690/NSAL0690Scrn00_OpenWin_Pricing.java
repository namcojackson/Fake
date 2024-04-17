/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0690;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0690.NSAL0690CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/26   Hitachi         T.Tsuchida      Create          QC#1225
 * 2017/09/27   Hitachi         K.Kojima        Update          QC#18376
 *</pre>
 */
public class NSAL0690Scrn00_OpenWin_Pricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        NSAL0690CMsg bizMsg = new NSAL0690CMsg();
        int rowNum = getButtonSelectNumber() - 1;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.B.no(rowNum).dsContrPk_B1.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A1.getValue()) == 0) {
                rowNum = i;
                break;
            }
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.rowSqNum_H1, BigDecimal.valueOf(rowNum));
        bizMsg.setBusinessID("NSAL0690");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        NSAL0690CMsg bizMsg = (NSAL0690CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        int hdrRowNum = scrnMsg.rowSqNum_H1.getValueInt();
        int dtlRowNum = getButtonSelectNumber();
        // START 2017/09/27 K.Kojima [QC#18376,MOD]
        // Object[] prm = new Object[3];
        Object[] prm = new Object[4];
        // END 2017/09/27 K.Kojima [QC#18376,MOD]
        prm[0] = scrnMsg.B.no(dtlRowNum).dsContrDtlPk_B1;
        prm[1] = scrnMsg.A.no(hdrRowNum).dsContrCatgCd_A1;
        prm[2] = scrnMsg.B.no(dtlRowNum).xxDiscRatio_B1;
        // START 2017/09/27 K.Kojima [QC#18376,ADD]
        prm[3] = scrnMsg.B.no(dtlRowNum).rnwPrcMethCd_B1;
        // END 2017/09/27 K.Kojima [QC#18376,ADD]

        setArgForSubScreen(prm);
    }
}
