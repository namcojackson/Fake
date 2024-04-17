/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL1570.constant.NWAL1570Constant;
import business.servlet.NWAL1570.constant.NWAL1570Constant.LINE_STS;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL1570Scrn01_TransitionByDetailStatus extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        //NWAL1570CMsg bizMsg = new NWAL1570CMsg();
        //bizMsg.setBusinessID("NWAL1570");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        NWAL1570_ABMsg recordMsg = scrnMsg.A.no(getButtonSelectNumber());

        List<Object> objParam = null;
        String resultId = "";
        if (LINE_STS.BO.getLineStsNm().equals(recordMsg.xxLineDplyStsNm_A1.getValue())
                || LINE_STS.PEND_ALLOC.getLineStsNm().equals(recordMsg.xxLineDplyStsNm_A1.getValue())) {
            objParam = createNLBL3080Parameters(bMsg, recordMsg);
            resultId = "GoToBackOrder";
        } else if (LINE_STS.BLLG_HLD.getLineStsNm().equals(recordMsg.xxLineDplyStsNm_A1.getValue())) {
            objParam = createNWAL1520Parameters(recordMsg);
            resultId = "GoToHoldRelease";
        }
        setArgForSubScreen(objParam.toArray(new Object[0]));

        setResult(resultId);

    }

    private List<Object> createNLBL3080Parameters(EZDBMsg bMsg, NWAL1570_ABMsg recordMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        List<Object> param = new ArrayList<Object>();

        // [0]:Sales Order Number
        param.add(recordMsg.srcRefOrCpoOrdNum_A1);
        // [1]:Retail Warehouse Code
        param.add(recordMsg.rtlWhCd_A1);
        // [2]:Ship To Customer Code
        param.add(recordMsg.shipToCustLocCd_A1);
        // [3]:Configuration ID
        param.add(recordMsg.svcConfigMstrPk_A1);
        // [4]:Model Name
        param.add(recordMsg.t_MdlNm_A1);
        // [5]:Tab
        scrnMsg.xxDplyTab.setValue(NWAL1570Constant.TAB_PARAM_ORD_LINE);
        param.add(scrnMsg.xxDplyTab);

        return param;
    }

    private List<Object> createNWAL1520Parameters(NWAL1570_ABMsg recordMsg) {

        List<Object> param = new ArrayList<Object>();

        // [0]:cpoOrdNum
        param.add(recordMsg.srcRefOrCpoOrdNum_A1);
        // [1]:configCatgCd
        param.add(recordMsg.configCatgCd_A1);
        // [2]:LineNum
        param.add(recordMsg.xxDplyOrdLineNum_A1.getValue());

        return param;
    }

}
