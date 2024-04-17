/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZLL0010;


import parts.common.*;
import parts.servletcommon.*;

import business.servlet.ZZLL0010.common.ZZLL0010CommonLogic;
import business.servlet.ZZLL0010.constant.ZZLL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

public class ZZLL0010_INIT extends S21INITCommonHandler implements ZZLL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	    //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        
        S21TableColorController tblColor = new S21TableColorController(ZZLL0010Constant.pageID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        ZZLL0010CommonLogic.initCommonButton(this);
        ZZLL0010CommonLogic.initPullDown00(scrnMsg);

        // User Company Code
        S21UserInfo userInfo = getContextUserInfo();
        scrnMsg.glblCmpyCd.setValue(userInfo.getUserCompanyCode());
        //scrnMsg.glblCmpyCd.setInputProtected(true);
        
        // Focus Controll
        scrnMsg.setFocusItem(scrnMsg.ezInUserID);
	}

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        ZZLL0010BMsg scrnMsg = (ZZLL0010BMsg) bMsg;
        
        scrnMsg.glblCmpyCd.setNameForMessage("User Company Code");
        scrnMsg.ezInUserID.setNameForMessage("User ID");
        scrnMsg.ezInAplID.setNameForMessage("Application ID");
        scrnMsg.mntTrxTp.setNameForMessage("Access Type");
        scrnMsg.mntTblNm.setNameForMessage("Table ID");
        scrnMsg.mntColumnNm.setNameForMessage("Column");
        scrnMsg.mntOldVal.setNameForMessage("Old Value");
        scrnMsg.mntNewVal.setNameForMessage("New Value");
        scrnMsg.mntPrimaryKey.setNameForMessage("Primary Key");
        scrnMsg.xxFromDt.setNameForMessage("From Date");
        scrnMsg.xxHrs_FS.setNameForMessage("From Hour");
        scrnMsg.xxMn_FS.setNameForMessage("From Minutes");
        scrnMsg.xxToDt.setNameForMessage("To Date");
        scrnMsg.xxHrs_TS.setNameForMessage("To Hour");
        scrnMsg.xxMn_TS.setNameForMessage("To Minutes");
    }

}
