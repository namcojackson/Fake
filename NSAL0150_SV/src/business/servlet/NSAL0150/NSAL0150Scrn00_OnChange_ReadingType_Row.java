/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0150.NSAL0150CMsg;
import business.servlet.NSAL0150.common.NSAL0150CommonLogic;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2018/06/26   CITS            T.Wada          Update          QC#25419
 *</pre>
 */
public class NSAL0150Scrn00_OnChange_ReadingType_Row extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        // QC#25419 Add Start
        int selectedRow = getButtonSelectNumber();
        if (NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL.equals(scrnMsg.D.no(selectedRow).dsMtrReadTpGrpCd_D.getValue())) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.D.no(selectedRow).xxChkBox_D.getValue())) {

                for(int i=0; i<scrnMsg.D.getValidCount(); i++) {

                    if (!(NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL.equals(scrnMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue()))) {
                        scrnMsg.D.no(i).xxChkBox_D.setValue(ZYPConstant.FLG_OFF_N);
                    }

                }
            }
        } else {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.D.no(selectedRow).xxChkBox_D.getValue())) {

                for(int i=0; i<scrnMsg.D.getValidCount(); i++) {

                    if (NSAL0150Constant.PSEUDO_MTR_LB_CD_ALL.equals(scrnMsg.D.no(i).dsMtrReadTpGrpCd_D.getValue())) {
                        scrnMsg.D.no(i).xxChkBox_D.setValue(ZYPConstant.FLG_OFF_N);
                    }

                }
            }
        }
        // QC#25419 Add End

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CMsg bizMsg = (NSAL0150CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0150CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0150CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0150CommonLogic.focusItem(scrnMsg);
    }
}
