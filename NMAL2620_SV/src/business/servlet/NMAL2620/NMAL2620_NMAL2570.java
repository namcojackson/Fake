/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NMAL2620_NMAL2570 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;

        // Territory Name
        if (scrnMsg.xxScrEventNm.getValue().equals("LINK_RESRC_NUM")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_H, scrnMsg.xxPopPrm_0);
        } else if (scrnMsg.xxScrEventNm.getValue().equals("LINK_EMP_ID")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnCd, scrnMsg.xxPopPrm_1);
        } else if (scrnMsg.xxScrEventNm.getValue().equals("LINK_RESRC_NM")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_H, scrnMsg.xxPopPrm_3);
        } else if (scrnMsg.xxScrEventNm.getValue().equals("LINK_MOVE_TERR")) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPsnNm_D, scrnMsg.xxPopPrm_3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_D, scrnMsg.xxPopPrm_0);
        }
    }
}
