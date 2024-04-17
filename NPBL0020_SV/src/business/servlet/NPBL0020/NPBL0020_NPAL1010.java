/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DESTINATION_SUB_WAREHOUSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DESTINATION_SUB_WAREHOUSE_FOR_LINE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DESTINATION_WAREHOUSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.DESTINATION_WAREHOUSE_FOR_LINE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_12;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCREEN_CTRL_VALUE_MULTIPLE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SOURCE_SUB_WAREHOUSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SOURCE_SUB_WAREHOUSE_FOR_LINE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SOURCE_WAREHOUSE;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.SOURCE_WAREHOUSE_FOR_LINE;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPBL0020.NPBL0020CMsg;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Return Action from NPAL1010
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/16/2016   CITS            Makoto Okigami  Create          N/A
 * 02/08/2016   CITS            K.Ogino         Update          QC#17483
 *</pre>
 */
public class NPBL0020_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if ((SOURCE_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue()) || SOURCE_SUB_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) && SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.srcRtlWhCd.getValue())
                    && SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.srcRtlSwhCd.getValue())) {
                return null;
            }

            if ((DESTINATION_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue()) || DESTINATION_SUB_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) && SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.destRtlWhCd.getValue())
                    && SCREEN_CTRL_VALUE_MULTIPLE.equals(scrnMsg.destRtlSwhCd.getValue())) {
                return null;
            }

            int eventRowIndex = getButtonSelectNumber();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(eventRowIndex));

            NPBL0020CMsg bizMsg = new NPBL0020CMsg();
            bizMsg.setBusinessID(BIZ_APP_ID);
            bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        } else {
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        NPBL0020CMsg bizMsg = (NPBL0020CMsg) cMsg;

        if (bizMsg != null) {

            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
                if (SOURCE_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.srcRtlWhCd);
                } else if (SOURCE_SUB_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.srcRtlSwhCd);
                } else if (DESTINATION_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.destRtlWhCd);
                } else if (DESTINATION_SUB_WAREHOUSE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.destRtlSwhCd);
                } else if (SOURCE_WAREHOUSE_FOR_LINE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    int eventRowIndex = getButtonSelectNumber();
                    scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlWhNm_A1);
                } else if (DESTINATION_WAREHOUSE_FOR_LINE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    int eventRowIndex = getButtonSelectNumber();
                    scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlWhNm_A2);
                } else if (SOURCE_SUB_WAREHOUSE_FOR_LINE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    int eventRowIndex = getButtonSelectNumber();
                    scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlSwhNm_A1);
                } else if (DESTINATION_SUB_WAREHOUSE_FOR_LINE.equals(scrnMsg.P.no(IDX_12).xxPopPrm.getValue())) {
                    int eventRowIndex = getButtonSelectNumber();
                    scrnMsg.setFocusItem(scrnMsg.A.no(eventRowIndex).rtlSwhNm_A2);
                }

                NPBL0020CommonLogic.setCtrlScrnItemDispDetailTable(this, scrnMsg, false);
            }
        }

    }
}
