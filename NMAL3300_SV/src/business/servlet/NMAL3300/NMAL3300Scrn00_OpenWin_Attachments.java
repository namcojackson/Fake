/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3300;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/24   SRAA            Y.Chen          Create          QC#4482
 *</pre>
 */
public class NMAL3300Scrn00_OpenWin_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL3300BMsg scrnMsg = (NMAL3300BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        BigDecimal pk = scrnMsg.D.no(selectIdx).dsCustSpclMsgPk_D1.getValue();

        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.Z.no(0).xxPopPrm_Z.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);               //[0]:display mode
        scrnMsg.Z.no(1).xxPopPrm_Z.setValue(scrnMsg.D.no(selectIdx).ezBusinessID_D1.getValue());    //[1]:business application id
        scrnMsg.Z.no(2).xxPopPrm_Z.setValue(pk.toPlainString());                                    //[2]:attachments grouping text
        scrnMsg.Z.no(3).xxPopPrm_Z.setValue("Customer Instructions Attachments");                   //[3]:function name
        scrnMsg.Z.no(4).xxPopPrm_Z.setValue("Customer Instructions");                               //[4]:primary key
        scrnMsg.Z.no(5).xxPopPrm_Z.setValue("DS_ACCT_ATT_DOC_TP");                                  //[5]:document type list //Code Table Name
        Object[] params = new Object[6];
        for (int i = 0; i < params.length; i++) {
            params[i] = scrnMsg.Z.no(i).xxPopPrm_Z.getValue();
        }
        setArgForSubScreen(params);
    }
}
