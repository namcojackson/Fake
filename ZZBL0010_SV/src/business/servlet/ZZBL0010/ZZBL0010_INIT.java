/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
package business.servlet.ZZBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZBL0010.ZZBL0010CMsg;
import business.servlet.ZZBL0010.common.ZZBL0010CommonLogic;
import business.servlet.ZZBL0010.constant.ZZBL0010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

public class ZZBL0010_INIT extends S21INITCommonHandler implements ZZBL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
	    //security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;

		ZZBL0010CMsg bizMsg = new ZZBL0010CMsg();
		bizMsg.setBusinessID("ZZBL0010");
		bizMsg.setFunctionCode("20");
		EZDMsg.copy(scrnMsg, null, bizMsg, null);

 		return bizMsg;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

		ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) bMsg;
		ZZBL0010CMsg bizMsg  = (ZZBL0010CMsg) cMsg;

		EZDMsg.copy(bizMsg, null, scrnMsg, null);

		// Set up initial buttons
        scrnMsg.setFocusItem(scrnMsg.glblCmpyCd);
		ZZBL0010CommonLogic.initCommonButton(this);
	}
    
    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        ZZBL0010BMsg scrnMsg = (ZZBL0010BMsg) arg0;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        String glblCmpyCd = labelConv.convLabel2i18nLabel(SCREEN_ID, "Global Company Code");
        String batchProcKey = labelConv.convLabel2i18nLabel(SCREEN_ID, "Batch Process Key");
        String subSystemCd = labelConv.convLabel2i18nLabel(SCREEN_ID, "Subsystem CD");
        String jobNetId = labelConv.convLabel2i18nLabel(SCREEN_ID, "JOB Net ID");
        String jobId = labelConv.convLabel2i18nLabel(SCREEN_ID, "Job ID");
        String programId = labelConv.convLabel2i18nLabel(SCREEN_ID, "Program ID");
        String status = labelConv.convLabel2i18nLabel(SCREEN_ID, "Status");
        String startDate = labelConv.convLabel2i18nLabel(SCREEN_ID, "Start Date");
        String endDate = labelConv.convLabel2i18nLabel(SCREEN_ID, "End Date");
        
        scrnMsg.glblCmpyCd.setNameForMessage(glblCmpyCd);
        scrnMsg.batProcLogPk.setNameForMessage(batchProcKey);
        scrnMsg.batProcSubSysCd.setNameForMessage(subSystemCd);
        scrnMsg.batProcJbntId.setNameForMessage(jobNetId);
        scrnMsg.batProcJobId.setNameForMessage(jobId);
        scrnMsg.batProcPgmId.setNameForMessage(programId);
        scrnMsg.batProcTrmCd.setNameForMessage(status);
        scrnMsg.effFromDt.setNameForMessage(startDate);
        scrnMsg.effToDt.setNameForMessage(endDate);
    }
}