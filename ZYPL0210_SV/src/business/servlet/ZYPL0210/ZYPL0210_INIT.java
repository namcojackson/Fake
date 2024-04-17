package business.servlet.ZYPL0210;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZYPL0210.ZYPL0210CMsg;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.fujitsu.uji.util.Parameters;
import com.fujitsu.uji.http.HttpDispatchContext;
/**
 * ZYPL0210_INIT
 * @author A.Hosono@CommonTeam
 */
public class ZYPL0210_INIT extends S21CommonHandler implements ZYPL0210Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // none
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        ZYPL0210CMsg bizMsg = new ZYPL0210CMsg();
        bizMsg.setBusinessID(UPLD_CSV_BIZ_APP);
        bizMsg.setFunctionCode("20");

        // set Upload CSV ID from prev screen.
        Object[] args = (Object[]) getArgForSubScreen();
        if (args != null && args.length > 0) {
            EZDBStringItem inUpldCsvId = (EZDBStringItem) args[0];
            bizMsg.upldCsvId_0H.setValue(inUpldCsvId.getValue());
        }

        String beforeBizId = getSubmitedBusinessAplId(ctx);
        //[MOD] FWDEF273 - C.Kim EXCEL Convert - C.Kim
        boolean fromMenu = (beforeBizId == null || beforeBizId.length() == 0 || beforeBizId.startsWith("AYEL") || beforeBizId.startsWith("DYEL") || beforeBizId.startsWith("NYEL"));

        if (SOURCE_EVENT_NAME_UPPER_TAB.equals(ctx.getEventName()) || fromMenu || SOURCE_EVENT_NAME_MULTI_SCREEN.equals(ctx.getEventName())) {
            //String procName = ctx.getAttributeStr(CONTEXTKEY_BIZ_PROC_NM);
            //bizMsg.upldCsvRstProcNm.setValue(procName);

        	// From Upper Tab
            S21SelectedProcessInfo selectInfo = (S21SelectedProcessInfo) ctx.getAttribute(CONTEXTKEY_PROCESS_INFO);
            if (selectInfo != null && selectInfo.getProcessId() != null) {
                bizMsg.menuProcId.setValue(selectInfo.getProcessId());
            }
            // From Common Menu
            Parameters param   = ((HttpDispatchContext)ctx.getDispatchContext()).getParameters();
            String selectedMenuProcessId = param.getSingleValue( "selectedMenuProcessIDbyMS" );
            if (!S21StringUtil.isEmpty(selectedMenuProcessId)){
                bizMsg.menuProcId.setValue(selectedMenuProcessId);
            }
            bizMsg.xxUpldCsvRstCd.setValue(UPLD_RST_PROC_NM);
        } else {
            String lastBizId = this.getSubmitedBusinessAplId(ctx);
            bizMsg.upldCsvRstBizAppId.setValue(lastBizId);
            bizMsg.xxUpldCsvRstCd.setValue(UPLD_RST_BIZ_APP_ID);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;
        ZYPL0210CMsg bizMsg = (ZYPL0210CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Common Button
        setButtonProperties(BTN_CMN_BTN1[0], BTN_CMN_BTN1[1], BTN_CMN_BTN1[2], 0, null);
        setButtonProperties(BTN_CMN_BTN2[0], BTN_CMN_BTN2[1], BTN_CMN_BTN2[2], 0, null);
        setButtonProperties(BTN_CMN_BTN3[0], BTN_CMN_BTN3[1], BTN_CMN_BTN3[2], 0, null);
        setButtonProperties(BTN_CMN_BTN4[0], BTN_CMN_BTN4[1], BTN_CMN_BTN4[2], 0, null);
        setButtonProperties(BTN_CMN_BTN5[0], BTN_CMN_BTN5[1], BTN_CMN_BTN5[2], 0, null);
        setButtonProperties(BTN_CMN_BTN6[0], BTN_CMN_BTN6[1], BTN_CMN_BTN6[2], 0, null);
        setButtonProperties(BTN_CMN_BTN7[0], BTN_CMN_BTN7[1], BTN_CMN_BTN7[2], 0, null);
        setButtonProperties(BTN_CMN_BTN8[0], BTN_CMN_BTN8[1], BTN_CMN_BTN8[2], 0, null);
        setButtonProperties(BTN_CMN_BTN9[0], BTN_CMN_BTN9[1], BTN_CMN_BTN9[2], 0, null);
        setButtonProperties(BTN_CMN_BTN10[0], BTN_CMN_BTN10[1], BTN_CMN_BTN10[2], 1, null);

        scrnMsg.upldCsvId_0H.setNameForMessage("Upload Name");
        scrnMsg.xxFileData_CU.setNameForMessage("Upload Requset");

        if (scrnMsg.upldCsvId_0H.getErrorCode() != 0) {
            scrnMsg.upldCsvId_0H.setInputProtected(true);

            setButtonEnabled(BTN_SEARCH, false);
            setButtonEnabled(BTN_UPLOAD, false);
            setButtonEnabled(BTN_REFRESH, false);

            scrnMsg.xxFileData_CU.setInputProtected(true);
            scrnMsg.upldCsvFileDescTxt_0H.setInputProtected(true);

            scrnMsg.xxUpldCsvProtFlg.setInputProtected(true);

            scrnMsg.xxUpldCsvUsrTxt_0H.setInputProtected(true);
            
            scrnMsg.addCheckItem(scrnMsg.upldCsvId_0H);
            scrnMsg.putErrorScreen();
        }

        if (!scrnMsg.upldCsvId_0H.isClear()) {
            scrnMsg.upldCsvId_0H.setInputProtected(true);
        }
    }
}
