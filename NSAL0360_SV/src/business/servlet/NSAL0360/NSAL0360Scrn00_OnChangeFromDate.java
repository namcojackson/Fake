/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0360;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0360.NSAL0360CMsg;
import business.servlet.NSAL0360.common.NSAL0360CommonLogic;
import business.servlet.NSAL0360.constant.NSAL0360Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2016/03/01   Hitachi         T.Tomita        Update          QC#4131
 * 2016/05/13   Hitachi         T.Kanasaka      Update          QC#7916
 *</pre>
 */
public class NSAL0360Scrn00_OnChangeFromDate extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;

//        NSAL0360CommonLogic.checkSchdDt(scrnMsg);
        // START 2016/05/13 T.Kanasaka [QC#7916, MOD]
//        NSAL0360CommonLogic.addDetailCheckItem(scrnMsg);

        int rowNum = getButtonSelectNumber();
        String tblNm = scrnMsg.srcTblNm_P1.getValue();
        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
        EZDMsg ezdMsg = tblArray.get(rowNum);
        EZDBBigDecimalItem perSchdNum = NSAL0360CommonLogic.getBigDecimalValueFromEZDMsg(ezdMsg, tblNm, "perSchdNum_A1");
        EZDBStringItem perBllgCycleCd = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "perBllgCycleCd_A1");
        EZDBDateItem bllgSchdFromDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdFromDt_A1");
        EZDBDateItem bllgSchdThruDt = NSAL0360CommonLogic.getDateValueFromEZDMsg(ezdMsg, tblNm, "bllgSchdThruDt_A1");
        if (ZYPCommonFunc.hasValue(perSchdNum)) {
            scrnMsg.addCheckItem(perSchdNum);
        }
        if (ZYPCommonFunc.hasValue(perBllgCycleCd)) {
            scrnMsg.addCheckItem(perBllgCycleCd);
        }
        if (ZYPCommonFunc.hasValue(bllgSchdFromDt)) {
            scrnMsg.addCheckItem(bllgSchdFromDt);
        }
        if (ZYPCommonFunc.hasValue(bllgSchdThruDt)) {
            scrnMsg.addCheckItem(bllgSchdThruDt);
        }
        // START 2016/05/13 T.Kanasaka [QC#7916, MOD]

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;

        scrnMsg.xxSelNum_H1.setValue(String.valueOf(getButtonSelectNumber()));

        NSAL0360CMsg bizMsg = new NSAL0360CMsg();
        bizMsg.setBusinessID(NSAL0360Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;
        NSAL0360CMsg bizMsg = (NSAL0360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0360CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
    }
}
