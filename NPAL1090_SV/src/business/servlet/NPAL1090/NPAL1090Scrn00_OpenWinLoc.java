/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1090.NPAL1090CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NPAL1090 Service Parts Request Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/11   CSAI            K.Lee           Create          N/A
 * 2013/05/23   Hitachi         T.Tomita        Update          QC1209
 * 2015/03/13   Fujitsu         T.Nishikawa     Update          CSA
 *</pre>
 */
public class NPAL1090Scrn00_OpenWinLoc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2013/05/23 QC1209 T.Tomita Update Start
        // return null;
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        // bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        // bizMsg.setFunctionCode("20");
        // EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2013/05/23 QC1209 T.Tomita Update End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2013/05/23 QC1209 T.Tomita Update Start
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // scrnMsg.P.clear();
        //        
        // Start Mod 2015/03/13 CSA T.Nishikawa
        // scrnMsg.P.no(0).xxPopPrm_H1.setValue(scrnMsg.toInvtyLocCd_H1.getValue());
        // scrnMsg.P.no(1).xxPopPrm_H1.clear();
        // scrnMsg.P.no(2).xxPopPrm_H1.clear();
        // scrnMsg.P.no(3).xxPopPrm_H1.setValue(ZYPConstant.FLG_OFF_N);
        // scrnMsg.P.no(4).xxPopPrm_H1.setValue(ZYPConstant.FLG_ON_Y);
        // scrnMsg.P.no(5).xxPopPrm_H1.clear();

        // Object[] param = new Object[6];
        // param[0] = scrnMsg.P.no(0).xxPopPrm_H1;
        // param[1] = scrnMsg.P.no(1).xxPopPrm_H1;
        // param[2] = scrnMsg.xxLocRoleTpCdListTxt;
        // param[3] = scrnMsg.P.no(3).xxPopPrm_H1;
        // param[4] = scrnMsg.P.no(4).xxPopPrm_H1;
        // param[5] = scrnMsg.P.no(5).xxPopPrm_H1;
        // // 2013/05/23 QC1209 T.Tomita Update End
        //        
        // int idx = 0;
        //        
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(scrnMsg.rqstRtlWhCd_H1.getValue());
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(NMXC100001EnableWH.getLocRoleTpForPopup(getGlobalCompanyCode(),
        // LOC_TP.TECHNICIAN));
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(ZYPConstant.FLG_OFF_N);
        // scrnMsg.P.no(idx++).xxPopPrm_H1.setValue(ZYPConstant.FLG_OFF_N);
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.no(idx++).xxPopPrm_H1.clear();
        // scrnMsg.P.setValidCount(idx);
        //        
        // Object[] param = new Object[idx];
        // for (int i = 0; i < idx; i++) {
        // param[i] = scrnMsg.P.no(i).xxPopPrm_H1;
        // }
        //        
        // // End Mod 2015/03/13 CSA T.Nishikawa
        //        
        // setArgForSubScreen(param);

    }

}
