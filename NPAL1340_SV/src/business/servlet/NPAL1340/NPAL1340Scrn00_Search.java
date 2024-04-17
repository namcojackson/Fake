/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1340.NPAL1340CMsg;
//import business.servlet.NPAL1340.constant.NPAL1340Constant;

import business.blap.NPAL1340.NPAL1340CMsg;
import business.servlet.NPAL1340.common.NPAL1340CommonLogic;
import business.servlet.NPAL1340.constant.NPAL1340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1340 Drop Ship Release
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/29   CSAI            K.Lee           Create          N/A
 * 2020/06/25   CITS            M.Furugoori     Update          QC#56979
 *</pre>
 */
public class NPAL1340Scrn00_Search extends S21CommonHandler implements NPAL1340Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.poOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm_H1);
        scrnMsg.addCheckItem(scrnMsg.vndNm_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToAcctNm_H1);

        if (!ZYPCommonFunc.hasValue(scrnMsg.poOrdNum_H1) 
            && !ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H1)
            && !scrnMsg.xxChkBox_H1.getValue().equals(ZYPConstant.FLG_ON_Y)
            && !ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.vndNm_H1)
            && !ZYPCommonFunc.hasValue(scrnMsg.shipToAcctNm_H1)) {

            StringBuilder param1 = new StringBuilder();
            param1.append(scrnMsg.poOrdNum_H1.getNameForMessage());
            param1.append(SPLIT_NPAM1235E);
            param1.append(scrnMsg.cpoOrdNum_H1.getNameForMessage());
            param1.append(SPLIT_NPAM1235E);
            param1.append(scrnMsg.xxChkBox_H1.getNameForMessage());
            param1.append(SPLIT_NPAM1235E);
            param1.append(scrnMsg.prntVndNm_H1.getNameForMessage());
            param1.append(SPLIT_NPAM1235E);
            param1.append(scrnMsg.vndNm_H1.getNameForMessage());
            String param2 = scrnMsg.shipToAcctNm_H1.getNameForMessage();

            scrnMsg.poOrdNum_H1.setErrorInfo(1, NPAM1235E, new String[] {param1.toString(), param2 });
            scrnMsg.cpoOrdNum_H1.setErrorInfo(1, NPAM1235E, new String[] {param1.toString(), param2 });
            scrnMsg.xxChkBox_H1.setErrorInfo(1, NPAM1235E, new String[] {param1.toString(), param2 });
            scrnMsg.prntVndNm_H1.setErrorInfo(1, NPAM1235E, new String[] {param1.toString(), param2 });
            scrnMsg.vndNm_H1.setErrorInfo(1, NPAM1235E, new String[] {param1.toString(), param2 });
            scrnMsg.shipToAcctNm_H1.setErrorInfo(1, NPAM1235E, new String[] {param1.toString(), param2 });
       }
       scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;

        // START 2020/06/25 [QC#56979,ADD]
        // clear html attribute
        scrnMsg.clearAllGUIAttribute("NPAL1340Scrn00");
        // END 2020/06/25 [QC#56979,ADD]

        NPAL1340CMsg bizMsg = new NPAL1340CMsg();
        bizMsg.setBusinessID("NPAL1340");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1340BMsg scrnMsg = (NPAL1340BMsg) bMsg;
        NPAL1340CMsg bizMsg  = (NPAL1340CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1340CommonLogic.initialize(this, scrnMsg);

        // START 2020/06/25 [QC#56979,ADD]
        int length = scrnMsg.A.getValidCount();
        for (int i = 0; i < length; i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrTrkUrl_A1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).proNum_A1.getValue())) {
                scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(true);
                EZDGUIAttribute link = new EZDGUIAttribute("NPAL1340Scrn00","carrTrk"+i);
                link.setStyleAttribute("color", "black");
                link.setStyleAttribute("text-decoration", "none");
                scrnMsg.addGUIAttribute(link);
            }
        }
        // END 2020/06/25 [QC#56979,ADD]
    }
}
