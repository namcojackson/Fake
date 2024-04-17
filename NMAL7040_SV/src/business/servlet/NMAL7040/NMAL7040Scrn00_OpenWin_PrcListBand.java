/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7040.NMAL7040CMsg;
//import business.servlet.NMAL7040.constant.NMAL7040Constant;

import business.servlet.NMAL7040.common.NMAL7040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 *</pre>
 */
public class NMAL7040Scrn00_OpenWin_PrcListBand extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_PrcListBand");

        setArgForSubScreen(NMAL7040CommonLogic.setArgumentNWAL1130(scrnMsg, scrnMsg.xxScrEventNm.getValue(), getButtonSelectNumber(), getGlobalCompanyCode()));

    }
}
