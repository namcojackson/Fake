package business.servlet.ZYPL0210;

import java.util.ArrayList;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDItem;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0210.constant.ZYPL0210Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author Administrator
 */
public class ZYPL0210Scrn00_More_Row extends S21CommonHandler implements ZYPL0210Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // none
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0210BMsg scrnMsg = (ZYPL0210BMsg) bMsg;

        int index = getButtonSelectNumber();
        
        if("Abend".equals(scrnMsg.A.no(index).upldCsvRsltNm.getValue()) && S21StringUtil.isEmpty(scrnMsg.A.no(index).upldCsvMsgTxt.getValue())){
            scrnMsg.A.no(index).upldCsvMsgTxt.setValue("System problem has been occurred during upload. Please contact system administrator.");
        }

        ArrayList<EZDItem> list = new ArrayList<EZDItem>();
        list.add(scrnMsg.A.no(index).upldCsvRqstPk);
        list.add(scrnMsg.A.no(index).xxUpldCsvDt);
        list.add(scrnMsg.A.no(index).xxUpldCsvTm);
        list.add(scrnMsg.A.no(index).xxUpldCsvUsrTxt);
        list.add(scrnMsg.A.no(index).upldCsvStsNm);
        list.add(scrnMsg.A.no(index).upldCsvRsltNm);
        list.add(scrnMsg.A.no(index).upldCsvMsgTxt);
        list.add(scrnMsg.A.no(index).upldCsvFileNm);
        list.add(scrnMsg.A.no(index).upldCsvFileDescTxt_0D);
        Object[] params = list.toArray(new Object[list.size()]);

        setArgForSubScreen(params);

    }

}
