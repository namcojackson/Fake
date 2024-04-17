/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   SRA             T.Chijimatsu    Create          N/A
 *</pre>
 */
package business.servlet.NMAL4500;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL4500.NMAL4500CMsg;
import business.servlet.NMAL4500.common.NMAL4500CommonLogic;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL4500Scrn00_Search_Vendor_CD extends S21CommonHandler implements NMAL4500Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // (2009/10/19 Y.Chen) Trac No.457 Add-Beg
        NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
        
        NMAL4500CommonLogic.initScreenClearExceptVenderCode(this, scrnMsg);
        // (2009/10/19 Y.Chen) Trac No.457 Add-Beg
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;

		NMAL4500CMsg bizMsg = new NMAL4500CMsg();
		bizMsg.setBusinessID("NMAL4500");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		NMAL4500BMsg scrnMsg = (NMAL4500BMsg) bMsg;
		NMAL4500CMsg bizMsg  = (NMAL4500CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Check item error
        scrnMsg.addCheckItem(scrnMsg.vndCd_01);
        scrnMsg.putErrorScreen();

        /** QC Defect ID : 2537 2009/12/11 takamura UIControl is New or Edit mode CHG-START **/
//        // Screen control
//        /** CHG-START 2009/12/08 takamura Program making condition No.3 **/
////        NMAL4500CommonLogic.doScreenControl(this, scrnMsg, getUserProfileService());
//        NMAL4500CommonLogic.doScreenControl_VndCdSearchResult(this, scrnMsg, getUserProfileService());
//        /** CHG-END **/
//
//        // Focus
//        scrnMsg.setFocusItem( scrnMsg.vndCd_01 );

        if (NMAL4500CommonLogic.isErrMessage(scrnMsg)) {
            return;
        }

        // Screen Control
        if (NMAL4500CommonLogic.isInfoMessage(scrnMsg)) {

            // New Vendor
            NMAL4500CommonLogic.doScreenControl(this, scrnMsg, getUserProfileService());
            scrnMsg.setFocusItem(scrnMsg.locNm_01);

        } else {

            // Edit Vendor
            NMAL4500CommonLogic.doScreenControl_VndCdSearchResult(this, scrnMsg, getUserProfileService());
            scrnMsg.setFocusItem(scrnMsg.dbaNm_01);

        }
        /** CHG-END **/

	}

}
