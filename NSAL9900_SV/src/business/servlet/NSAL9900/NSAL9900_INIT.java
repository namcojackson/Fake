/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9900;

import static business.servlet.NSAL9900.constant.NSAL9900Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.lang.reflect.Field;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL9900.NSAL9900CMsg;
import business.servlet.NSAL9900.common.NSAL9900CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class NSAL9900_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg = NSAL9900CommonLogic.createCMsgForSearch();

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length > 0) {
                EZDBStringItem tblCd = (EZDBStringItem) params[0];
                setValue(scrnMsg.tblCd, tblCd);
            }
        } else {
            scrnMsg.tblCd.clear();
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;
        NSAL9900CMsg bizMsg = (NSAL9900CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL9900CommonLogic.screenControlProcess(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.tblNm);
        setNameForMessage(bMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;

        // Search Condition
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL9900_ABMsg aBMsg = scrnMsg.A.no(i);

            NSAL9900_CBMsg cBMsg = scrnMsg.C.no(aBMsg.xxRowNum_A.getValueInt());
            String searchItemNm = cBMsg.searchItemNm.getValue();
            Field f = null;
            try {
                f = NSAL9900_ABMsg.class.getField(searchItemNm);
                ((EZDBItem) f.get(aBMsg)).setNameForMessage(cBMsg.logicMaintTrgtColNm.getValue());
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
        }

        // Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NSAL9900_BBMsg bBMsg = scrnMsg.B.no(i);

            for (int j = 0; j < scrnMsg.C.getValidCount(); j++) {
                NSAL9900_CBMsg cBMsg = scrnMsg.C.no(j);
                String detailItemNm = cBMsg.detailItemNm.getValue();
                Field f = null;
                try {
                    f = NSAL9900_BBMsg.class.getField(detailItemNm);
                    ((EZDBItem) f.get(bBMsg)).setNameForMessage(cBMsg.logicMaintTrgtColNm.getValue());
                } catch (Exception ex) {
                    throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
                }
            }
        }
    }
}
