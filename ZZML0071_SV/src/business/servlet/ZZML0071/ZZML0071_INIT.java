/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0071;


import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0071.ZZML0071CMsg;
import business.servlet.ZZML0071.common.ZZML0071Scrn00CommonLogic;
import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

public class ZZML0071_INIT extends S21INITCommonHandler implements ZZML0071Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
               Object[] params = (Object[])arg;
               
               scrnMsg.glblCmpyCd.setValue((String) params[0]);
               
               if (params.length == 2) {
                   BigDecimal mlUsrAddrPk = (BigDecimal) params[1];
                   if ( mlUsrAddrPk != null) {
                       scrnMsg.A.no(0).mlUsrAddrPk_A.setValue(mlUsrAddrPk);
                       scrnMsg.A.setValidCount(1);
                   }
               } else if (params.length == 3) {
                   String mlGrpId = (String) params[2];
                   if ( mlGrpId != null && mlGrpId.toString().length() != 0) {
                       scrnMsg.A.no(0).mlGrpId_A.setValue(mlGrpId);
                       scrnMsg.A.setValidCount(1);
                   }
               }
            }
        } else {
            String gcc = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            if (S21StringUtil.isNotEmpty(gcc)) {
                scrnMsg.glblCmpyCd.setValue(gcc);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

        ZZML0071CMsg bizMsg = new ZZML0071CMsg();
        bizMsg.setBusinessID("ZZML0071");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
        ZZML0071CMsg bizMsg  = (ZZML0071CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.glblCmpyCd.setNameForMessage("Global Company CD");
        scrnMsg.glblCmpyCd.setInputProtected(scrnMsg.A.getValidCount() > 0);
        scrnMsg.mlUsrId.setNameForMessage(     "User ID");
        scrnMsg.mlUsrAddr.setNameForMessage(   "User Address");
        scrnMsg.mlUsrNm.setNameForMessage(     "User Name");
        // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
        // scrnMsg.mlUsrLocId.setNameForMessage(  "Language");
        scrnMsg.mlUsrLocId.setNameForMessage(  "Locale");
        // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
        scrnMsg.mlUsrDescTxt.setNameForMessage("User Description");

        // Previous preparations
        if ( scrnMsg.A.getValidCount() == 0 ) {
            scrnMsg.A.setValidCount(1);
//            scrnMsg.A.no(0).mlGrpId_A.setInputProtected(true);
        } else {
//            setButtonEnabled("AddGroup", false);
        }

        // table setting
        for ( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
            scrnMsg.A.no(i).mlGrpId_A.setNameForMessage("Mail Group ID");
            scrnMsg.A.no(i).mlGrpId_A.setIndispensable(true);
            scrnMsg.A.no(i).mlGrpDescTxt_A.setInputProtected(true);
        }
        
        Serializable arg = getArgForSubScreen();
        if (arg != null) {
            if (arg instanceof Object[]) {
               Object[] params = (Object[])arg;
               if ( params[1] != null || (params[2] != null && params[2] != "")) {
                   for ( int i = 0; i < scrnMsg.A.getValidCount(); i++ ) {
//                       scrnMsg.A.no(i).mlGrpId_A.setInputProtected(true);
                   }
               }
            }
        }
        
        ZZML0071Scrn00CommonLogic.setButtonPropertiesInit(this);
        ZZML0071Scrn00CommonLogic.setTabFocusRule( scrnMsg );
        
        scrnMsg.setFocusItem( scrnMsg.mlUsrId );
        scrnMsg.glblCmpyCd.setInputProtected(true);
//        if (scrnMsg.langCd_L1.get(1).isClear()) {
//            ZZML0071Scrn00CommonLogic.setDisableSubmitButton(this);
//        }
        for (int i = 0;i < scrnMsg.A.getValidCount(); i++) {
            if (scrnMsg.A.no(i).mlGrpId_A.isInputProtected()) {
//                this.setButtonEnabled("MailGroupLookup", false);
            }
        }

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        
    }
}
