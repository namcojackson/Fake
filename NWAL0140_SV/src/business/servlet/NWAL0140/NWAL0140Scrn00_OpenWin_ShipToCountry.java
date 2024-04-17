/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/6/23   CUSA            Fujitsu         Update          N/A
 *</pre>
 */
package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDDebugOutput;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWAL0140Scrn00_OpenWin_ShipToCountry extends S21CommonHandler implements NWAL0140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        if (scrnMsg.ctryCd.isError()) {

            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NWAL0140Bean.ctryCd);

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {

                scrnMsg.ctryCd.clearErrorInfo();

            } else {
                EZDDebugOutput.println(1, "★Other errors", this);
            }
        }

        scrnMsg.addCheckItem(scrnMsg.ctryCd);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(EVENT_OPEN_WIN_SHIP_TO_COUNTRY);

        scrnMsg.xxPopPrm.setValue("CTRY");
        scrnMsg.xxPopPrm_W1.setValue("CTRY_CD");
        scrnMsg.xxPopPrm_W2.setValue("CTRY_NM");
        scrnMsg.xxPopPrm_W3.setValue("CTRY_SORT_NUM");
        scrnMsg.xxPopPrm_W4.setValue("Country Search");
        scrnMsg.xxPopPrm_W5.setValue("Country Code");
        scrnMsg.xxPopPrm_W6.setValue("Country Name");
        scrnMsg.xxPopPrm_W7.setValue("");

        EZDBStringItem[] params = new EZDBStringItem[11];
        // 1.TBL_NM
        params[0] = scrnMsg.xxPopPrm;
        // 2.TBL_CD_COLUMN_CD
        params[1] = scrnMsg.xxPopPrm_W1;
        // 3.TBL_CD_COLUMN_NM
        params[2] = scrnMsg.xxPopPrm_W2;
        // 4.TBL_SORT_NUM_COLUMN_NM
        params[3] = scrnMsg.xxPopPrm_W3;
        // 5.SCR_NM
        params[4] = scrnMsg.xxPopPrm_W4;
        // 6.HDR_CD_LABEL
        params[5] = scrnMsg.xxPopPrm_W5;
        // 7.HDR_NM_LABEL
        params[6] = scrnMsg.xxPopPrm_W6;
        // 8.DTL_CD_LABEL
        params[7] = scrnMsg.xxPopPrm_W5;
        // 9.DTL_NM_LABEL
        params[8] = scrnMsg.xxPopPrm_W6;
        // 10.CONDITION_CD
        params[9] = scrnMsg.ctryCd;
        // 11.CONDITION_NM
        params[10] = scrnMsg.ctryNm;

        setArgForSubScreen(params);
    }
}
