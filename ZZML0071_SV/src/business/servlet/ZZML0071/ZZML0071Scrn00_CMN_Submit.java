/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZML0071;

import java.util.Vector;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZML0071.ZZML0071CMsg;
import business.servlet.ZZML0071.common.ZZML0071Scrn00CommonLogic;
import business.servlet.ZZML0071.constant.ZZML0071Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZML0071Scrn00_CMN_Submit extends S21CommonHandler implements ZZML0071Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.mlUsrId);
        scrnMsg.addCheckItem(scrnMsg.mlUsrAddr);
        scrnMsg.addCheckItem(scrnMsg.mlUsrNm);
        scrnMsg.addCheckItem(scrnMsg.mlUsrLocId);
        scrnMsg.addCheckItem(scrnMsg.mlUsrDescTxt);
        
        if ( scrnMsg.mlUsrId.getValue().length() == 0 && scrnMsg.mlUsrAddr.getValue().length() == 0 ) {
            scrnMsg.mlUsrId.setErrorInfo( 1, "ZZM9000E", new String[] { "User ID or User Address" } );
            scrnMsg.mlUsrAddr.setErrorInfo( 1, "ZZM9000E", new String[] { "User ID or User Address" } );
        }
        if (scrnMsg.mlUsrId.getValue().length() == 0) {
            if (scrnMsg.mlUsrNm.getValue().length() == 0) {
                scrnMsg.mlUsrNm.setErrorInfo( 1, "ZZM9000E", new String[] { "User name" } );
            }
            if (scrnMsg.mlUsrLocId.getValue().length() == 0) {
                // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
                // scrnMsg.mlUsrLocId.setErrorInfo( 1, "ZZM9000E", new String[] { "Language" } );
                scrnMsg.mlUsrLocId.setErrorInfo( 1, "ZZM9000E", new String[] { "Locale" } );
                // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            }
        }

        Vector<String> vc = new Vector<String>();
        String bond = null;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mlGrpId_A);
            
            if ( scrnMsg.A.no(i).mlGrpId_A.getValue().length() == 0) {
                scrnMsg.A.no(i).mlGrpId_A.setErrorInfo( 1, "ZZM9000E", new String[] { "Mail Group ID" } );
            }
            
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mlKeyFirstCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mlKeyScdCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mlKeyThirdCd_A);
            
            bond = scrnMsg.A.no(i).mlGrpId_A.getValue() + "\r" +
                    scrnMsg.A.no(i).mlKeyFirstCd_A.getValue() + "\r" +
                    scrnMsg.A.no(i).mlKeyScdCd_A.getValue() + "\r" +
                    scrnMsg.A.no(i).mlKeyThirdCd_A.getValue();
            
            if ( !vc.contains(bond) ) {
                vc.add(bond);
            } else {
                scrnMsg.A.no(i).mlGrpId_A.setErrorInfo( 1, "ZYPM0119E", new String[] { "Mail Group ID" } );                
            }
            
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;

        ZZML0071CMsg bizMsg = new ZZML0071CMsg();
        bizMsg.setBusinessID("ZZML0071");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZML0071BMsg scrnMsg = (ZZML0071BMsg) bMsg;
        ZZML0071CMsg bizMsg = (ZZML0071CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        ZZML0071Scrn00CommonLogic.setTabFocusRule( scrnMsg );

        if ( scrnMsg.A.getValidCount() > 0 ) {
            setButtonEnabled("AddGroup", false);
        }

        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.mlUsrId);
        scrnMsg.addCheckItem(scrnMsg.mlUsrAddr);
        scrnMsg.addCheckItem(scrnMsg.mlUsrNm);
        scrnMsg.addCheckItem(scrnMsg.mlUsrLocId);
        scrnMsg.addCheckItem(scrnMsg.mlUsrDescTxt);
        for (int i=0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mlGrpId_A);
        }
        scrnMsg.putErrorScreen();

    }
}
