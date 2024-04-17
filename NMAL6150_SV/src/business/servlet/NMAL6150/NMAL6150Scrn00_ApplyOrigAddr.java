/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6150;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6150.NMAL6150CMsg;
//import business.servlet.NMAL6150.constant.NMAL6150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   SRAA            Y.Chen          Create          QC#4504
 *</pre>
 */
public class NMAL6150Scrn00_ApplyOrigAddr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6150BMsg scrnMsg = (NMAL6150BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_O1);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_O1);
        scrnMsg.addCheckItem(scrnMsg.thirdLineAddr_O1);
        scrnMsg.addCheckItem(scrnMsg.frthLineAddr_O1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_O1);
        scrnMsg.addCheckItem(scrnMsg.stCd_O1);
        scrnMsg.addCheckItem(scrnMsg.postCd_O1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_O1);
        scrnMsg.addCheckItem(scrnMsg.provNm_O1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6150BMsg scrnMsg = (NMAL6150BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[0], scrnMsg.firstLineAddr_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[1], scrnMsg.scdLineAddr_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[2], scrnMsg.thirdLineAddr_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[3], scrnMsg.frthLineAddr_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[4], scrnMsg.ctyAddr_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[5], scrnMsg.stCd_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[6], scrnMsg.postCd_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[7], scrnMsg.cntyNm_O1);
        ZYPEZDItemValueSetter.setValue((EZDBStringItem)params[8], scrnMsg.provNm_O1);
    }
}