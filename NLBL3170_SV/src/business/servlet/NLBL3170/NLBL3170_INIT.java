/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3170;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3170.NLBL3170CMsg;
import business.servlet.NLBL3170.common.NLBL3170CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 * 2018/08/17   CITS            K.Ogino         Update          QC#27601
 * 2019/07/30   CITS            R.Shimamoto     Update          QC#52078
 * 2020/02/18   Fujitsu         R.Nakamura      Update          QC#55897
 * 2020/06/17   CITS            M.Furugoori     Update          QC#56914
 * 2020/11/18   CITS            M.Furugoori     Update          QC#57659
 *</pre>
 */
public class NLBL3170_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        scrnMsg.clear();

        ZYPTableUtil.clear(scrnMsg.T);
        ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, getGlobalCompanyCode());

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            for (int i = 0; i < params.length; i++) {

                if (i == 0) {

                    // [0] : TRX Header Number
                    // Mod Start 2020/02/18 QC#55897
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum, (String) params[0]);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyOrdNum_TH, (String) params[0]);
                    // Mod End 2020/02/18 QC#55897

                } else if (i == 1) {

                    // [1] : Edit Flag
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxValUpdFlg, (String) params[1]);

                } else if (i == 2) {

                    // [2] : Tracking Number
                    if (params[2] != null) {
                        EZDMsg.copy((EZDMsgArray) params[2], "", scrnMsg.T, "T1");
                    }
                // QC#27601
                } else if (i == 3) {
                    // Mode 1: CSA SHPG_ORD
                    // Mode 2: ASN
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (String) params[3]);

                }

            }
        }

        // QC:52078 Start
        // QC#27601
//        if (!ZYPCommonFunc.hasValue(scrnMsg.xxModeCd)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, "1"); // CSA SHPG_ORD
//        }
        // QC:52078 End

        NLBL3170CMsg bizMsg = new NLBL3170CMsg();
        bizMsg.setBusinessID("NLBL3170");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;
        NLBL3170CMsg bizMsg = (NLBL3170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLBL3170CommonLogic.setTableBGColor(scrnMsg);
        NLBL3170CommonLogic.initDisplayInfo(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).mstrProNum_A1);

         // START 2020/06/17 [QC#56914, ADD]
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxValUpdFlg.getValue())) {
            int length = scrnMsg.A.getValidCount();
            for (int i = 0; i < length; i++) {
                // START 2020/11/18 [QC#57659, MOD]
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrTrkUrl_A1.getValue()) || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrTrkUrl_A2.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrEncTrkUrl_A1.getValue()) || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).carrEncTrkUrl_A2.getValue())) {
//                    scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(false);
//                    scrnMsg.A.no(i).carrTrkUrl_A2.setInputProtected(false);
                    scrnMsg.A.no(i).carrEncTrkUrl_A1.setInputProtected(false);
                    scrnMsg.A.no(i).carrEncTrkUrl_A2.setInputProtected(false);
                } else {
//                    scrnMsg.A.no(i).carrTrkUrl_A1.setInputProtected(true);
//                    scrnMsg.A.no(i).carrTrkUrl_A2.setInputProtected(true);
                    scrnMsg.A.no(i).carrEncTrkUrl_A1.setInputProtected(true);
                    scrnMsg.A.no(i).carrEncTrkUrl_A2.setInputProtected(true);
                    // END 2020/11/18 [QC#57659, MOD]
                    EZDGUIAttribute link = new EZDGUIAttribute("NLBL3170Scrn00","carrTrk1"+i);
                    link.setStyleAttribute("color", "black");
                    link.setStyleAttribute("text-decoration", "none");
                    scrnMsg.addGUIAttribute(link);
                    EZDGUIAttribute link2 = new EZDGUIAttribute("NLBL3170Scrn00","carrTrk2"+i);
                    link2.setStyleAttribute("color", "black");
                    link2.setStyleAttribute("text-decoration", "none");
                    scrnMsg.addGUIAttribute(link2);
                }
            }
        }
        // END 2020/06/17 [QC#56914, ADD]
    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL3170BMsg scrnMsg = (NLBL3170BMsg) bMsg;
        // Mod Start 2020/02/18 QC#55897
//        scrnMsg.trxHdrNum.setNameForMessage("Header#");
        scrnMsg.xxDplyOrdNum_TH.setNameForMessage("Header#");
        scrnMsg.wmsCarrCd.setNameForMessage("Carrier Code");
        scrnMsg.xxMsgTxt.setNameForMessage("Warehouse");
//        scrnMsg.soNum.setNameForMessage("Source Number");
        scrnMsg.xxDplyOrdNum_SN.setNameForMessage("Source Number");
        // Mod End 2020/02/18 QC#55897
    }
}
