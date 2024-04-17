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
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZML0010.common.ZZML0010CommonLogic;
import business.servlet.ZZML0010.constant.ZZML0010Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * @author Q02673
 */
public class ZZML0010_INIT extends S21CommonHandler implements ZZML0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;
        scrnMsg.glblCmpyCd_S.setNameForMessage("Global Company CD");
        scrnMsg.mlSmtpHostNm.setNameForMessage("SMTP Server Name");
        scrnMsg.mlSmtpPortNum.setNameForMessage("SMTP Server Port");
        scrnMsg.mlSmtpUsrNm.setNameForMessage("SMTP User Name");
        scrnMsg.mlSmtpPassTxt.setNameForMessage("SMTP User Password");
        scrnMsg.mlDefFromNm.setNameForMessage("Default From Address");
        scrnMsg.mlDefRpyToNm_01.setNameForMessage("Default ReplyTo Address 1");
        scrnMsg.mlDefRpyToNm_02.setNameForMessage("Default ReplyTo Address 2");
        scrnMsg.mlDefRpyToNm_03.setNameForMessage("Default ReplyTo Address 3");
        scrnMsg.mlDefRpyToNm_04.setNameForMessage("Default ReplyTo Address 4");
        scrnMsg.mlDefRpyToNm_05.setNameForMessage("Default ReplyTo Address 5");
        scrnMsg.mlCharSetNm.setNameForMessage("Default Charaset");
        scrnMsg.mlRtryNum.setNameForMessage("Default Retry Count");
        // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        // scrnMsg.langCd.setNameForMessage("Default Language");
        scrnMsg.mlLocId.setNameForMessage("Default Locale");
        // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        scrnMsg.mlSendMaxNum.setNameForMessage("Default Send Max Count");
        scrnMsg.mlDefDtFmtTxt.setNameForMessage("Default Date Format");
        scrnMsg.mlDefNumFmtTxt.setNameForMessage("Default Number Format");
        scrnMsg.mlSysStopFlg.setNameForMessage("Mail Send Stop Flag");
    }

     @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
         return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0010BMsg scrnMsg = (ZZML0010BMsg) bMsg;

        String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        if (S21StringUtil.isNotEmpty(gcc)) {
            scrnMsg.glblCmpyCd_S.setValue(gcc);
        }

        ZZML0010CommonLogic.setLanguageList(scrnMsg);
        
        scrnMsg.xxScrNm_S.setValue("ZZML0010Scrn00");

        ZZML0010CommonLogic.setFormControleProtectCondition(scrnMsg, true);

        ZZML0010CommonLogic.setButtonPropertiesInit(this);

        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd_S);
    }
}
