/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0010;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0010.ZZML0010CMsg;
import business.servlet.ZZML0010.constant.ZZML0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author Q02673
 */
public class ZZML0010Scrn00_CMN_Submit extends S21CommonHandler implements ZZML0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;
        //scrnMsg.addCheckItem(scrnMsg.glblCmpyCd_S);
        scrnMsg.addCheckItem(scrnMsg.mlSmtpHostNm);
        scrnMsg.addCheckItem(scrnMsg.mlSmtpPortNum);
        scrnMsg.addCheckItem(scrnMsg.mlSmtpUsrNm);
        scrnMsg.addCheckItem(scrnMsg.mlSmtpPassTxt);

        scrnMsg.addCheckItem(scrnMsg.mlDefFromNm);
        scrnMsg.addCheckItem(scrnMsg.mlDefRpyToNm_01);
        scrnMsg.addCheckItem(scrnMsg.mlDefRpyToNm_02);
        scrnMsg.addCheckItem(scrnMsg.mlDefRpyToNm_03);
        scrnMsg.addCheckItem(scrnMsg.mlDefRpyToNm_04);
        scrnMsg.addCheckItem(scrnMsg.mlDefRpyToNm_05);

        scrnMsg.addCheckItem(scrnMsg.mlCharSetNm);
        scrnMsg.addCheckItem(scrnMsg.mlRtryNum);
        // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        // scrnMsg.addCheckItem(scrnMsg.langCd);
        scrnMsg.addCheckItem(scrnMsg.mlLocId);
        // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        scrnMsg.addCheckItem(scrnMsg.mlSendMaxNum);
        scrnMsg.addCheckItem(scrnMsg.mlDefDtFmtTxt);
        scrnMsg.addCheckItem(scrnMsg.mlDefNumFmtTxt);

        scrnMsg.addCheckItem(scrnMsg.mlSysStopFlg);
        scrnMsg.putErrorScreen();
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;

        scrnMsg.glblCmpyCd_S.setValue(scrnMsg.glblCmpyCd_S.getValue());

        ZZML0010CMsg bizMsg = new ZZML0010CMsg();
        bizMsg.setBusinessID("ZZML0010");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;
        ZZML0010CMsg bizMsg  = (ZZML0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
