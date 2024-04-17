/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_17;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.OPEN_WIN_SHIP_TO_CUST_D;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.OPEN_WIN_SHIP_TO_CUST_H;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Return Action from NMAL6760
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/05/2016   CITS            K.Ogino         Create          N/A
 * 02/08/2016   CITS            K.Ogino         Update          QC#17483
 * 12/12/2018   Fujitsu         S.Takami        Update          QC#29456
 * 09/26/2019   Fujitsu         T.Ogura         Update          QC#52362
 *</pre>
 */
public class NPBL0020_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            String scrEventNm = scrnMsg.xxScrEventNm.getValue();

            if (OPEN_WIN_SHIP_TO_CUST_H.equals(scrEventNm)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_EO, scrnMsg.P.no(IDX_17).xxPopPrm);
                // START 2019/09/26 T.Ogura [QC#52362,MOD]
//                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_EO, scrnMsg.P.no(IDX_2).xxPopPrm);
                String shipToCustLocNm = "";
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(IDX_2).xxPopPrm)) {
                    shipToCustLocNm = scrnMsg.P.no(IDX_2).xxPopPrm.getValue();
                    if (shipToCustLocNm.length() > 60) {
                        shipToCustLocNm = shipToCustLocNm.substring(0, 60);
                    }
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_EO, shipToCustLocNm);
                // END   2019/09/26 T.Ogura [QC#52362,MOD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, BigDecimal.valueOf(-1));
            } else if (OPEN_WIN_SHIP_TO_CUST_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                int index = getButtonSelectNumber();
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).shipToCustCd_E1, scrnMsg.P.no(IDX_17).xxPopPrm);
                // START 2019/09/26 T.Ogura [QC#52362,MOD]
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).shipToLocNm_E1, scrnMsg.P.no(IDX_2).xxPopPrm);
                String shipToCustLocNm = "";
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(IDX_2).xxPopPrm)) {
                    shipToCustLocNm = scrnMsg.P.no(IDX_2).xxPopPrm.getValue();
                    if (shipToCustLocNm.length() > 60) {
                        shipToCustLocNm = shipToCustLocNm.substring(0, 60);
                    }
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).shipToLocNm_E1, shipToCustLocNm);
                // END   2019/09/26 T.Ogura [QC#52362,MOD]
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, BigDecimal.valueOf(index));
            }
            NPBL0020CMsg bizMsg = new NPBL0020CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;

        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                if (OPEN_WIN_SHIP_TO_CUST_H.equals(scrnMsg.xxScrEventNm.getValue())) {
                    // START 2019/09/26 T.Ogura [QC#52362,DEL]
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_EO, scrnMsg.P.no(IDX_17).xxPopPrm);
//                    ZYPEZDItemValueSetter.setValue(scrnMsg.shipToLocNm_EO, scrnMsg.P.no(IDX_2).xxPopPrm);
                    // END   2019/09/26 T.Ogura [QC#52362,DEL]
                    scrnMsg.setFocusItem(scrnMsg.shipToCustCd_EO);
                } else if (OPEN_WIN_SHIP_TO_CUST_D.equals(scrnMsg.xxScrEventNm.getValue())) {
                    int index = getButtonSelectNumber();
                    scrnMsg.setFocusItem(scrnMsg.A.no(index).shipToLocNm_E1);
                    // 2018/12/12 QC#29456 Add Start
                    scrnMsg.addCheckItem(scrnMsg.A.no(index).xxScrItem50Txt_A1);
                    scrnMsg.putErrorScreen();
                    // 2018/12/12 QC#29456 Add End
                }
            }
        }
    }
}
