/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2760;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFCL2760.NFCL2760CMsg;
import business.servlet.NFCL2760.common.NFCL2760CommonLogic;
import business.servlet.NFCL2760.constant.NFCL2760Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2018/01/18   Fujitsu         H.Ikeda         Update          QC#23136
 * 2018/08/24   CITS            K.Kameoka       Update          QC#25955
 *</pre>
 */
public class NFCL2760Scrn00_OpenWin_New extends S21CommonHandler implements NFCL2760Constant {
	   @Override
	    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	        // START 2018/01/18 H.Ikeda [QC#23136, DEL]
	        //NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

	        //NFCL2760CommonLogic.commonInputCheck(scrnMsg);
            // END   2018/01/18 H.Ikeda [QC#23136, DEL]
	    }

	    @Override
	    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

	        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;
	        NFCL2760CMsg bizMsg = null;

	        bizMsg = NFCL2760CommonLogic.setRequestData_NFCL2760Scrn00_OpenWin_New();

	        EZDMsg.copy(scrnMsg, null, bizMsg, null);

	        bizMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt());
	        bizMsg.xxPageShowToNum.clear();
	        bizMsg.xxPageShowOfNum.clear();
	        ZYPTableUtil.clear(scrnMsg.A);

	        return bizMsg;
	    }

	    @Override
	    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

	        NFCL2760CMsg bizMsg = (NFCL2760CMsg) cMsg;
	        NFCL2760BMsg scrnMsg = (NFCL2760BMsg) bMsg;

	        EZDMsg.copy(bizMsg, null, scrnMsg, null);

	        NFCL2760CommonLogic.transMsgCheck(scrnMsg);
	        scrnMsg.putErrorScreen();

//            NFCL2760CommonLogic.initialize(this, scrnMsg);  //QC#25955 delete

//	          NFCL2760CommonLogic.commonBtnControl_NFCL2760Scrn00_OpenWin_New(this, scrnMsg); //QC#25955 delete

	        Object[] params = NFCL2760CommonLogic.otherBusConnectTo_NFCL2760Scrn00_OpenWin_New(scrnMsg);

	        setArgForSubScreen(params);

//            NFCL2760CommonLogic.setEntryScreen_NFCL2760(this, scrnMsg);  //QC#25955 delete

	        setButtonConfirmMsg("CMN_Return", "NZZM0004W", null, 1);
//            NFCL2760CommonLogic.protectModeOne(scrnMsg, this); //QC#25955 delete

//            NFCL2760CommonLogic.protectAddDetailLine(scrnMsg, this);  //QC#25955 delete
	    }
}
