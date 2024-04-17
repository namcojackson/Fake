/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1230;

import static business.servlet.NPAL1230.constant.NPAL1230Constant.BIZ_APP_ID;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.MAX_DATE;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.MIN_DATE;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NLAM0028E;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NMAM0072E;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NPAL1498E;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NPAL1499E;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NPAM1590E;
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NPZM0041E;
// START 2023/01/26 S.Dong [QC#60966, ADD]
import static business.servlet.NPAL1230.constant.NPAL1230Constant.NPAM1656E;
// END 2023/01/26 S.Dong [QC#60966, ADD]

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1230.NPAL1230CMsg;
import business.servlet.NPAL1230.common.NPAL1230CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 2017/01/05   CITS            K.Kameoka       Update          QC#15834
 * 2018/04/11   CITS            K.Fukumura      Update          QC#21170
 * 2018/06/15   CITS            Y.Iwasaki       Update          QC#18390
 * 2021/01/15   CITS            J.Evangelista   Update          QC#58165
 * 2023/01/25   Hitachi         S.Dong          Update          QC#60966
 *</pre>
 */
public class NPAL1230Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        // checks the mandatory fields.
        scrnMsg.addCheckItem(scrnMsg.aslNm);
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.aslStartDt);
        // Date check
        if (ZYPCommonFunc.hasValue(scrnMsg.aslStartDt) && ZYPCommonFunc.hasValue(scrnMsg.aslEndDt)) {
            if (ZYPDateUtil.compare(scrnMsg.aslStartDt.getValue(), scrnMsg.aslEndDt.getValue()) > 0) {
                scrnMsg.aslEndDt.setErrorInfo(1, NLAM0028E, new String[] {"Start Date", "End Date" });
            }
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.aslHdrPk)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.aslStartDt)) {
                if (ZYPDateUtil.compare(scrnMsg.aslStartDt.getValue(), ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())) < 0) {
                    scrnMsg.aslStartDt.setErrorInfo(1, NPZM0041E);
                }
            }
        }

        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(index).mdseCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).unitPrcAmt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndUomQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).baseOrdQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndIncrOrdQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndMinOrdQty_A);
            // QC#21170 2018/04/11 Start
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndLtDaysNum_A);
            // QC#21170 2018/04/11 End
            // START 2023/01/25 S.Dong [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(index).vndShipLtDaysNum_A);
            // END 2023/01/25 S.Dong [QC#60966, ADD]
            // Item Number duplicate check
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).mdseCd_A)) {
                for (int j = index + 1; j < scrnMsg.A.getValidCount(); j++) {
                    if (scrnMsg.A.no(index).mdseCd_A.getValue().equals(scrnMsg.A.no(j).mdseCd_A.getValue()) && scrnMsg.A.no(index).vndCd_A.getValue().equals(scrnMsg.A.no(j).vndCd_A.getValue())) {

                        String srcEffFromDt;
                        String srcEffThruDt;
                        String trgEffFromDt;
                        String trgEffThruDt;

                        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).effFromDt_A)) {
                            srcEffFromDt = scrnMsg.A.no(index).effFromDt_A.getValue();
                        } else {
                            srcEffFromDt = MIN_DATE;
                        }

                        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).effThruDt_A)) {
                            srcEffThruDt = scrnMsg.A.no(index).effThruDt_A.getValue();
                        } else {
                            srcEffThruDt = MAX_DATE;
                        }

                        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(j).effFromDt_A)) {
                            trgEffFromDt = scrnMsg.A.no(j).effFromDt_A.getValue();
                        } else {
                            trgEffFromDt = MIN_DATE;
                        }

                        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(j).effThruDt_A)) {
                            trgEffThruDt = scrnMsg.A.no(j).effThruDt_A.getValue();
                        } else {
                            trgEffThruDt = MAX_DATE;
                        }

                        if ((ZYPDateUtil.compare(srcEffFromDt, trgEffFromDt) >= 0 && ZYPDateUtil.compare(srcEffFromDt, trgEffThruDt) <= 0)
                                || (ZYPDateUtil.compare(srcEffThruDt, trgEffFromDt) >= 0 && ZYPDateUtil.compare(srcEffThruDt, trgEffThruDt) <= 0)) {
                            scrnMsg.A.no(index).mdseCd_A.setErrorInfo(1, NMAM0072E, new String[] {"Item Number" });
                            scrnMsg.A.no(j).mdseCd_A.setErrorInfo(1, NMAM0072E, new String[] {"Item Number" });
                        }
                    }
                }
            }

            // Date check
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).effFromDt_A) && ZYPCommonFunc.hasValue(scrnMsg.A.no(index).effThruDt_A)) {
                if (ZYPDateUtil.compare(scrnMsg.A.no(index).effFromDt_A.getValue(), scrnMsg.A.no(index).effThruDt_A.getValue()) > 0) {
                    scrnMsg.A.no(index).effFromDt_A.setErrorInfo(1, NLAM0028E, new String[] {"Effectivity From Date", "Effectivity To Date" });
                }
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).aslDtlPk_A)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).effFromDt_A)) {
                    if (ZYPDateUtil.compare(scrnMsg.A.no(index).effFromDt_A.getValue(), ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())) < 0) {
                        scrnMsg.A.no(index).effFromDt_A.setErrorInfo(1, NPZM0041E);
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).effThruDt_A) && ZYPCommonFunc.hasValue(scrnMsg.aslEndDt)) {
                if (ZYPDateUtil.compare(scrnMsg.A.no(index).effThruDt_A.getValue(), scrnMsg.aslEndDt.getValue()) > 0) {
                    scrnMsg.A.no(index).effThruDt_A.setErrorInfo(1, NLAM0028E, new String[] {"Effectivity To Date", "ASL End Date" });
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).vndUomQty_A)) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(index).vndUomQty_A.getValue()) >= 0) {
                    scrnMsg.A.no(index).vndUomQty_A.setErrorInfo(1, NPAL1498E, new String[] {"Vnd UOM Qty" });
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).baseOrdQty_A)) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(index).baseOrdQty_A.getValue()) >= 0) {
                    scrnMsg.A.no(index).baseOrdQty_A.setErrorInfo(1, NPAL1498E, new String[] {"Base PO Qty" });
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).vndIncrOrdQty_A)) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(index).vndIncrOrdQty_A.getValue()) >= 0) {
                    scrnMsg.A.no(index).vndIncrOrdQty_A.setErrorInfo(1, NPAL1498E, new String[] {"Incr Ord Qty" });
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).vndMinOrdQty_A)) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(index).vndMinOrdQty_A.getValue()) >= 0) {
                    scrnMsg.A.no(index).vndMinOrdQty_A.setErrorInfo(1, NPAL1498E, new String[] {"Min Ord Qty" });
                }
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).vndMinOrdQty_A) && ZYPCommonFunc.hasValue(scrnMsg.A.no(index).vndIncrOrdQty_A)) {
                if (BigDecimal.ZERO.compareTo(scrnMsg.A.no(index).vndIncrOrdQty_A.getValue()) < 0) {
                    if (scrnMsg.A.no(index).vndMinOrdQty_A.getValueInt() % scrnMsg.A.no(index).vndIncrOrdQty_A.getValueInt() != 0) {
                        scrnMsg.A.no(index).vndMinOrdQty_A.setErrorInfo(1, NPAL1499E, new String[] {"Min Ord Qty", "Incr Ord Qty" });
                    }
                }
            }

            // Check Vendor UOM Quantity and Base Order Quantity
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).vndUomQty_A) && ZYPCommonFunc.hasValue(scrnMsg.A.no(index).baseOrdQty_A)) {
                if (!scrnMsg.A.no(index).vndUomQty_A.getValue().equals(BigDecimal.ONE) && !scrnMsg.A.no(index).baseOrdQty_A.getValue().equals(BigDecimal.ONE)) {
                    scrnMsg.A.no(index).vndUomQty_A.setErrorInfo(1, NPAM1590E);
                }
            }
            // QC#21170 2018/04/11 Start
            // Check Lead Time : Minus is error
            BigDecimal vndLtDaysNum = scrnMsg.A.no(index).vndLtDaysNum_A.getValue();
            if (ZYPCommonFunc.hasValue(vndLtDaysNum)) {
                if (BigDecimal.ZERO.compareTo(vndLtDaysNum) > 0) {
                    scrnMsg.A.no(index).vndLtDaysNum_A.setErrorInfo(1, NPAL1498E, new String[] {"Lead Time" });
                }
            }
            // QC#21170 2018/04/11 End

            // START 2023/02/16 S.Dong [QC#60966, ADD]
            // Check Vendor Ship Lead Time : Vendor Ship Lead Time should be less than Lead Time.
            BigDecimal vndShipLtDaysNum = scrnMsg.A.no(index).vndShipLtDaysNum_A.getValue();
            if (ZYPCommonFunc.hasValue(vndShipLtDaysNum) && ZYPCommonFunc.hasValue(vndLtDaysNum)) {
                if (vndShipLtDaysNum.compareTo(vndLtDaysNum) > 0) {
                    scrnMsg.A.no(index).vndShipLtDaysNum_A.setErrorInfo(1, NPAM1656E);
                }
                if (BigDecimal.ZERO.compareTo(vndShipLtDaysNum) > 0) {
                    scrnMsg.A.no(index).vndShipLtDaysNum_A.setErrorInfo(1, NPAL1498E, new String[] {"Vendor Ship Lead Time" });
                }
            }
            if (ZYPCommonFunc.hasValue(vndShipLtDaysNum) && !ZYPCommonFunc.hasValue(vndLtDaysNum)) {
                if (BigDecimal.ZERO.compareTo(vndShipLtDaysNum) < 0) {                    
                    scrnMsg.A.no(index).vndShipLtDaysNum_A.setErrorInfo(1, NPAM1656E);
                }
                if (BigDecimal.ZERO.compareTo(vndShipLtDaysNum) > 0) {
                    scrnMsg.A.no(index).vndShipLtDaysNum_A.setErrorInfo(1, NPAL1498E, new String[] {"Vendor Ship Lead Time" });
                }
            }
            // END 2023/02/16 S.Dong [QC#60966, ADD]
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;

        NPAL1230CMsg bizMsg = new NPAL1230CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1230BMsg scrnMsg = (NPAL1230BMsg) bMsg;
        NPAL1230CMsg bizMsg = (NPAL1230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {

            scrnMsg.addCheckItem(scrnMsg.aslNm);
            scrnMsg.addCheckItem(scrnMsg.prntVndCd);

            // Add Check Item for ASL Detail Validation
            for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(index).primSplyFlg_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).mdseCd_A);
                // START 2021/01/15 J.Evangelista [QC#58165,ADD]
                scrnMsg.addCheckItem(scrnMsg.A.no(index).splyItemNum_A);
                // END 2021/01/15 J.Evangelista [QC#58165,ADD]                
                scrnMsg.addCheckItem(scrnMsg.A.no(index).vndCd_A);
            }

            scrnMsg.putErrorScreen();
            return;
        }

        NPAL1230CommonLogic.ctrlScrnItemDispInit(this, scrnMsg);
        //QC#15834 Start
        NPAL1230CommonLogic.setTableScreen(scrnMsg);
        //QC#15834 End

        // set focus
        scrnMsg.setFocusItem(scrnMsg.aslNm);
    }
}
