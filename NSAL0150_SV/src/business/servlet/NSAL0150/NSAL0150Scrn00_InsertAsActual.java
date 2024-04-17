/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0150.NSAL0150CMsg;
import business.servlet.NSAL0150.common.NSAL0150CommonLogic;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2018/08/13   Hitachi         K.Kojima        Update          QC#27604
 * 2022/08/12   Hitachi         H.Watanabe      Update          QC#60046
 *</pre>
 */
public class NSAL0150Scrn00_InsertAsActual extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        int rowNum = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(rowNum));

        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        //DEL START 2022/08/12 H.Watanabe [QC#60046]
//        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_UPD);
        //DEL END 2022/08/12 H.Watanabe [QC#60046]
        //ADD START 2022/08/12 H.Watanabe [QC#60046]
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        //ADD END 2022/08/12 H.Watanabe [QC#60046]
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CMsg bizMsg = (NSAL0150CMsg) cMsg;

        // START 2018/08/13 K.Kojima [QC#27604,DEL]
        // List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        // NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);
        // END 2018/08/13 K.Kojima [QC#27604,DEL]

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/08/13 K.Kojima [QC#27604,ADD]
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);
        // END 2018/08/13 K.Kojima [QC#27604,ADD]

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
