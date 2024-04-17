/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 * 09/04/2018   Fujitsu         T.Noguchi       Update          QC#28019
 *</pre>
 */
package business.servlet.NMAL6350;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6350.NMAL6350CMsg;
import business.servlet.NMAL6350.common.NMAL6350CommonLogic;
import business.servlet.NMAL6350.constant.NMAL6350Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class NMAL6350_INIT extends S21INITCommonHandler implements NMAL6350Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	    checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL6350BMsg scrnMsg = (NMAL6350BMsg) bMsg;

        NMAL6350CMsg bizMsg = NMAL6350CommonLogic.setBizFunction("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;

	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL6350BMsg scrnMsg = (NMAL6350BMsg) bMsg;
		NMAL6350CMsg bizMsg  = (NMAL6350CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).pkgUomSortNum_A1);
        NMAL6350CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
	}
    
	protected void setNameForMessage(EZDBMsg bMsg) {
        
        NMAL6350BMsg scrnMsg = (NMAL6350BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).pkgUomSortNum_A1.setNameForMessage("Sort Num");
            scrnMsg.A.no(i).pkgUomCd_A1.setNameForMessage("Code");
            scrnMsg.A.no(i).pkgUomNm_A1.setNameForMessage("Name");
            scrnMsg.A.no(i).pkgUomDescTxt_A1.setNameForMessage("Description Text");
            scrnMsg.A.no(i).pkgUomPkgLvlNum_A1.setNameForMessage("Package Level Number");
            // 2018/09/04 QC#28019 Add Start
            scrnMsg.A.no(i).pkgUomClsCd_A1.setNameForMessage("Unit of Measure Class");
            // 2018/09/04 QC#28019 Add Start
        }
    }

}
