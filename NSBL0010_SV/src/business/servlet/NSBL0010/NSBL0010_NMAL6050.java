/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import static business.servlet.NSBL0010.constant.NSBL0010Constant.ITEM_NM_BRANCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSBL0010.NSBL0010CMsg;
//import business.servlet.NSBL0010.constant.NSBL0010Constant;

import business.blap.NSBL0010.NSBL0010CMsg;
import business.servlet.NSBL0010.NSBL0010BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/10/19   Hitachi         N.Arai          Update          QC#13901
 *</pre>
 */
public class NSBL0010_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

        //NSBL0010CMsg bizMsg = new NSBL0010CMsg();
        //bizMsg.setBusinessID("NSBL0010");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
        //NSBL0010CMsg bizMsg  = (NSBL0010CMsg) cMsg;

// START 2016/10/19 N.Arai [QC#13901, MOD]
        if (!"CMN_Close".equals(getLastGuard())) {
            NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;
            NSBL0010CMsg bizMsg = (NSBL0010CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }

            String scrEventNm = scrnMsg.xxScrEventNm.getValue();

            if (ITEM_NM_BRANCH.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrCd, scrnMsg.xxCondCd);
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcContrBrDescTxt, scrnMsg.xxCondNm);
                scrnMsg.setFocusItem(scrnMsg.svcContrBrCd);

            }
        }
// END 2016/10/19 N.Arai [QC#13901, MOD]

    }
}
