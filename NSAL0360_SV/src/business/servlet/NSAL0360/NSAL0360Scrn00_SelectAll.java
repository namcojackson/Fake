/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0360;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0360.common.NSAL0360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/03   CUSA            Fujitsu         Create          N/A
 * 2016/05/12   Hitachi         T.Kanasaka      Update          QC#7916
 *</pre>
 */
public class NSAL0360Scrn00_SelectAll extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0360BMsg scrnMsg = (NSAL0360BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.srcTblNm_P1)) {
            return;
        }

        String tblNm = scrnMsg.srcTblNm_P1.getValue();

        EZDMsgArray tblArray = NSAL0360CommonLogic.getTableArrayFromEZDMsg(scrnMsg, tblNm);
        int cnt = tblArray.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg ezdMsg = tblArray.get(i);
            // START 2016/05/12 T.Kanasaka [QC#7916, MOD]
//          ZYPEZDItemValueSetter.setValue(NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1"), ZYPConstant.FLG_ON_Y);
            EZDBStringItem checkItem = NSAL0360CommonLogic.getStringValueFromEZDMsg(ezdMsg, tblNm, "xxChkBox_A1");
            if (!checkItem.isInputProtected()) {
                ZYPEZDItemValueSetter.setValue(checkItem, ZYPConstant.FLG_ON_Y);
            }
            // END 2016/05/12 T.Kanasaka [QC#7916, MOD]
        }
    }
}
