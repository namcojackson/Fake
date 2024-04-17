/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import static business.servlet.NSBL0010.constant.NSBL0010Constant.OPENWIN_SHIPTO;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.BUSINESS_ID;
import static business.servlet.NSBL0010.constant.NSBL0010Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0010.NSBL0010CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/25   Hitachi         N.Arai          Create          QC#13901
 *</pre>
 */
public class NSBL0010_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        if (!"CMN_Close".equals(getLastGuard())) {
            NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
            if (OPENWIN_SHIPTO.equals(scrnMsg.xxScrEventNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd, scrnMsg.xxPopPrm_PQ);

                scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
                scrnMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {
            NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
            NSBL0010CMsg bizMsg = new NSBL0010CMsg();
    
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(FUNC_CD_20);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
    
            return bizMsg;
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;

        getArgForSubScreen();
        if (!"CMN_Close".equals(getLastGuard())) {
            if (OPENWIN_SHIPTO.equals(scrnMsg.xxScrEventNm.getValue())) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }
        }
        scrnMsg.xxScrEventNm.clear();

        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.putErrorScreen();
    }
}
