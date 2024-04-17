package business.servlet.ZYPL0230;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZYPL0230.constant.ZYPL0230Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * @author Administrator
 */
public class ZYPL0230_INIT extends S21CommonHandler implements ZYPL0230Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZYPL0230BMsg scrnMsg = (ZYPL0230BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            scrnMsg.upldCsvRqstPk.setValue(((EZDBBigDecimalItem) params[0]).getValue());
            scrnMsg.xxUpldCsvDt.setValue(((EZDBDateItem) params[1]).getValue());
            scrnMsg.xxUpldCsvTm.setValue(((EZDBStringItem) params[2]).getValue());
            scrnMsg.xxUpldCsvUsrTxt.setValue(((EZDBStringItem) params[3]).getValue());
            scrnMsg.upldCsvStsNm.setValue(((EZDBStringItem) params[4]).getValue());
            scrnMsg.upldCsvRsltNm.setValue(((EZDBStringItem) params[5]).getValue());

            setUpldCsvMsgTxt(scrnMsg, ((EZDBStringItem) params[6]).getValue());
            setUpldCsvFileNm(scrnMsg, ((EZDBStringItem) params[7]).getValue());
            setUpldCsvFileDescTxt(scrnMsg, ((EZDBStringItem) params[8]).getValue());
        }

        // Common Button
        setButtonProperties(BTN_CMN_BTN8[0], BTN_CMN_BTN8[1], BTN_CMN_BTN8[2], 0, null);
        setButtonProperties(BTN_CMN_BTN10[0], BTN_CMN_BTN10[1], BTN_CMN_BTN10[2], 1, null);

    }

    /**
     * @param scrnMsg
     * @param targetStr
     */
    private void setUpldCsvMsgTxt(ZYPL0230BMsg scrnMsg, String targetStr) {
        int i = 0;
        while (targetStr.length() > LINE_LEN) {

            String part = null;
            int endIndex = targetStr.lastIndexOf(' ', LINE_LEN);
            if (endIndex < 0) {
                part = targetStr.substring(0, LINE_LEN);
                targetStr = targetStr.substring(LINE_LEN);
            } else {
                part = targetStr.substring(0, endIndex + 1);
                targetStr = targetStr.substring(endIndex + 1);
            }
            scrnMsg.A.no(i).upldCsvMsgTxt.setValue(part);
            i++;
        }
        scrnMsg.A.no(i).upldCsvMsgTxt.setValue(targetStr);
        scrnMsg.A.setValidCount(i + 1);
    }

    /**
     * @param scrnMsg
     * @param targetStr
     */
    private void setUpldCsvFileNm(ZYPL0230BMsg scrnMsg, String targetStr) {
        int i = 0;
        while (targetStr.length() > LINE_LEN) {

            String part = null;
            int endIndex = targetStr.lastIndexOf(' ', LINE_LEN);
            if (endIndex < 0) {
                part = targetStr.substring(0, LINE_LEN);
                targetStr = targetStr.substring(LINE_LEN);
            } else {
                part = targetStr.substring(0, endIndex + 1);
                targetStr = targetStr.substring(endIndex + 1);
            }
            scrnMsg.B.no(i).upldCsvFileNm.setValue(part);
            i++;
        }
        scrnMsg.B.no(i).upldCsvFileNm.setValue(targetStr);
        scrnMsg.B.setValidCount(i + 1);
    }

    /**
     * @param scrnMsg
     * @param targetStr
     */
    private void setUpldCsvFileDescTxt(ZYPL0230BMsg scrnMsg, String targetStr) {
        int i = 0;
        while (targetStr.length() > LINE_LEN) {

            String part = null;
            int endIndex = targetStr.lastIndexOf(' ', LINE_LEN - 1);
            if (endIndex < 0) {
                part = targetStr.substring(0, LINE_LEN);
                targetStr = targetStr.substring(LINE_LEN);
            } else {
                part = targetStr.substring(0, endIndex + 1);
                targetStr = targetStr.substring(endIndex + 1);
            }
            scrnMsg.C.no(i).upldCsvFileDescTxt.setValue(part);
            i++;
        }
        scrnMsg.C.no(i).upldCsvFileDescTxt.setValue(targetStr);
        scrnMsg.C.setValidCount(i + 1);
    }
}
