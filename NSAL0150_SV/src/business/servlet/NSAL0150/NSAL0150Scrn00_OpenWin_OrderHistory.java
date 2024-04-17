/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.util.ArrayList;
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
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2016/12/06   Hitachi         Y.Takeno        Update          QC#15200
 *</pre>
 */
public class NSAL0150Scrn00_OpenWin_OrderHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/12/06 [QC#15200, MOD]
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2016/12/06 [QC#15200, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/12/06 [QC#15200, ADD]
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

        List<Object> prmList = createNWAL1570Parameters(scrnMsg);
        Object[] prm = prmList.toArray() ;
        setArgForSubScreen(prm);
        // END 2016/12/06 [QC#15200, ADD]
    }

    private List<Object> createNWAL1570Parameters(NSAL0150BMsg scrnMsg) {

        List<Object> param = new ArrayList<Object>();

        // [0]Real Time Inquiry
        param.add(ZYPConstant.FLG_OFF_N);
        // [1]Result Mode
        param.add(NSAL0150Constant.NWAL1570_PRM_RESULT_MODE);
        // [2]Display Mode
        param.add(NSAL0150Constant.NWAL1570_PRM_DISP_MODE);
        // [3]Display By Item Name
        param.add(NSAL0150Constant.NWAL1570_PRM_DISP_BY_ITEM_NM);
        // [4]Header Status
        param.add(new ArrayList<String>());
        // [5]Line Status
        param.add(new ArrayList<String>());
        // [6]Return Line Status
        param.add(new ArrayList<String>());
        // [7]Invoice Date From
        param.add(null);
        // [8]Invoice Date To
        param.add(null);
        // [9]Aging
        param.add(null);
        // [10]Order Source
        param.add(null);
        // [11]Include Pending Import Order Flag
        param.add(ZYPConstant.FLG_OFF_N);
        // [12]Order Date From
        param.add(null);
        // [13]Order Date To
        param.add(null);
        // [14]Team
        param.add(null);
        // [15]Zone
        param.add(null);
        // [16]Create By User
        param.add(null);
        // [17]Order Category
        param.add(scrnMsg.dsOrdCatgDescTxt_OH.getValue());
        // [18]Service Machine Master PK
        if (DS_CONTR_CATG.REGULAR.equals(scrnMsg.dsContrCatgCd_OH.getValue()) ||
                DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd_OH.getValue())) {
            param.add(scrnMsg.svcMachMstrPk.getValue().toPlainString());
        } else {
            param.add(null);
        }
        // [19]Contract Number
        param.add(scrnMsg.dsContrNum_OH.getValue());

        return param;
    }
}
