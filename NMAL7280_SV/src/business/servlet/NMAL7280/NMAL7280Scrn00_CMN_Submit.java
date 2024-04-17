/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7280;

import static business.servlet.NMAL7280.constant.NMAL7280Constant.BIZ_ID;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_01;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_02;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_03;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_04;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_05;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_06;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_07;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_08;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_09;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_10;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_11;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_NO_12;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.CONDITION_SIZE;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.DETAIL_LIST_SIZE;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM0011E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM0070E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM0072E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM0836E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM8115E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NMAM8294E;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.NZZM0002I;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.OPR_AND;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7280.NMAL7280CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NMAL7280.constant.NMAL7280Constant.MESSAGE_KIND_ERROR;


/**
 *<pre>
 * NMAL7280Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7280Scrn00_CMN_Submit extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        checkInputValue(scrnMsg);
        addDetailCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;

        NMAL7280CMsg bizMsg = new NMAL7280CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7280BMsg scrnMsg = (NMAL7280BMsg) bMsg;
        NMAL7280CMsg bizMsg = (NMAL7280CMsg) cMsg;
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

    }

    private void checkInputValue(NMAL7280BMsg scrnMsg) {
        Hashtable<BigDecimal, String> conditiontable = new Hashtable<BigDecimal, String>();
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            conditiontable.put(scrnMsg.B.no(i).prcRuleCondNum_B.getValue()
                    , scrnMsg.B.no(i).prcRuleAttrbCd_B.getValue());
        }
        ArrayList<String> sameConditioncheckList = new ArrayList<String>(DETAIL_LIST_SIZE);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            checkMandatoryValue(scrnMsg, i);
            checkSameConditionNo(scrnMsg, i);
            checkDateFromTo(scrnMsg, i);
            checkExistCondition(scrnMsg, i);
            checkSameCodition(scrnMsg, sameConditioncheckList, i);
            checkSameConditionAttrb(scrnMsg, conditiontable, i);
        }
    }

    private void checkSameConditionAttrb(NMAL7280BMsg scrnMsg, Hashtable<BigDecimal, String> conditiontable, int i) {
        if (scrnMsg.A.no(i).prcRuleOpTpCd_A1.getValue().equals(OPR_AND)) {
            ArrayList<BigDecimal> list = new ArrayList<BigDecimal>(CONDITION_SIZE);

            list.add(scrnMsg.A.no(i).prcRuleCondNum_A1.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A2.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A3.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A4.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A5.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A6.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A7.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A8.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_A9.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_AA.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_AB.getValue());
            list.add(scrnMsg.A.no(i).prcRuleCondNum_AC.getValue());

            for (int j = 0; j < list.size() - 1; j++) {
                BigDecimal num1 = list.get(j);
                if (!ZYPCommonFunc.hasValue(num1)) {
                    continue;
                }
                String attr1 = (String) conditiontable.get(num1);
                if (!ZYPCommonFunc.hasValue(attr1)) {
                    continue;
                }

                for (int k = j + 1; k < list.size(); k++) {
                    BigDecimal num2 = list.get(k);
                    if (ZYPCommonFunc.hasValue(num2)) {
                        String attr2 = (String) conditiontable.get(num2);
                        if (!ZYPCommonFunc.hasValue(attr2)) {
                            continue;
                        }
                        if (attr1.equals(attr2)) {
                            setErr(i, j + 1, num1.toString(), num2.toString(), scrnMsg);
                            setErr(i, k + 1, num1.toString(), num2.toString(), scrnMsg);
                        }
                    }

                }
            }
        }
    }

    private void checkSameCodition(NMAL7280BMsg scrnMsg, ArrayList<String> sameConditioncheckList, int i) {
        ArrayList<BigDecimal> condList = new ArrayList<BigDecimal>(CONDITION_SIZE);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A1)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A1.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A2)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A2.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A3)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A3.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A4)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A4.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A5)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A5.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A6)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A6.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A7)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A7.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A8)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A8.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A9)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_A9.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AA)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_AA.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AB)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_AB.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AC)) {
            condList.add(scrnMsg.A.no(i).prcRuleCondNum_AC.getValue());
        }

        Collections.sort(condList);
        String stringValue = condList.toString();
        if (sameConditioncheckList.contains(stringValue)) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A1)) {
                scrnMsg.A.no(i).prcRuleCondNum_A1.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A1.getNameForMessage()});
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A2)) {
                scrnMsg.A.no(i).prcRuleCondNum_A2.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A2.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A3)) {
                scrnMsg.A.no(i).prcRuleCondNum_A3.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A3.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A4)) {
                scrnMsg.A.no(i).prcRuleCondNum_A4.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A4.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A5)) {
                scrnMsg.A.no(i).prcRuleCondNum_A5.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A5.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A6)) {
                scrnMsg.A.no(i).prcRuleCondNum_A6.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A6.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A7)) {
                scrnMsg.A.no(i).prcRuleCondNum_A7.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A7.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A8)) {
                scrnMsg.A.no(i).prcRuleCondNum_A8.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A8.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A9)) {
                scrnMsg.A.no(i).prcRuleCondNum_A9.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A9.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AA)) {
                scrnMsg.A.no(i).prcRuleCondNum_AA.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_AA.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AB)) {
                scrnMsg.A.no(i).prcRuleCondNum_AB.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_AB.getNameForMessage()});
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AC)) {
                scrnMsg.A.no(i).prcRuleCondNum_AC.setErrorInfo(1, NMAM0072E
                        , new String[]{scrnMsg.A.no(i).prcRuleCondNum_AC.getNameForMessage()});
            }
        } else {
            sameConditioncheckList.add(stringValue);
        }
    }

    private void checkExistCondition(NMAL7280BMsg scrnMsg, int i) {
        boolean  checkFlag1 = false;
        boolean  checkFlag2 = false;
        boolean  checkFlag3 = false;
        boolean  checkFlag4 = false;
        boolean  checkFlag5 = false;
        boolean  checkFlag6 = false;
        boolean  checkFlag7 = false;
        boolean  checkFlag8 = false;
        boolean  checkFlag9 = false;
        boolean  checkFlag10 = false;
        boolean  checkFlag11 = false;
        boolean  checkFlag12 = false;
        for (int j = 0; j < scrnMsg.B.getValidCount(); j++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A1)
                   && scrnMsg.A.no(i).prcRuleCondNum_A1.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag1 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A2)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_A2.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag2 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A3)
                   && scrnMsg.A.no(i).prcRuleCondNum_A3.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag3 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A4)
                  && scrnMsg.A.no(i).prcRuleCondNum_A4.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag4 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A5)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_A5.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag5 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A6)
                  && scrnMsg.A.no(i).prcRuleCondNum_A6.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag6 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A7)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_A7.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag7 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A8)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_A8.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag8 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A9)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_A9.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag9 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AA)
                 &&  scrnMsg.A.no(i).prcRuleCondNum_AA.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag10 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AB)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_AB.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag11 = true;
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AC)
                  &&  scrnMsg.A.no(i).prcRuleCondNum_AC.getValue().equals(scrnMsg.B.no(j).prcRuleCondNum_B.getValue())
                    ) {
                checkFlag12 = true;
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A1)
                && !checkFlag1) {
            scrnMsg.A.no(i).prcRuleCondNum_A1.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A1.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A2)
                && !checkFlag2) {
            scrnMsg.A.no(i).prcRuleCondNum_A2.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A2.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A3)
                && !checkFlag3) {
            scrnMsg.A.no(i).prcRuleCondNum_A3.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A3.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A4)
                && !checkFlag4) {
            scrnMsg.A.no(i).prcRuleCondNum_A4.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A4.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A5)
                && !checkFlag5) {
            scrnMsg.A.no(i).prcRuleCondNum_A5.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A5.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A6)
                && !checkFlag6) {
            scrnMsg.A.no(i).prcRuleCondNum_A6.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A6.getValue().toString()});
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A7)
                && !checkFlag7) {
            scrnMsg.A.no(i).prcRuleCondNum_A7.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A7.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A8)
                && !checkFlag8) {
            scrnMsg.A.no(i).prcRuleCondNum_A8.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A8.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A9)
                && !checkFlag9) {
            scrnMsg.A.no(i).prcRuleCondNum_A9.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_A9.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AA)
                && !checkFlag10) {
            scrnMsg.A.no(i).prcRuleCondNum_AA.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_AA.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AB)
                && !checkFlag11) {
            scrnMsg.A.no(i).prcRuleCondNum_AB.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_AB.getValue().toString()});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AC)
                && !checkFlag12) {
            scrnMsg.A.no(i).prcRuleCondNum_AC.setErrorInfo(1, NMAM0011E
                    , new String[]{scrnMsg.A.no(i).prcRuleCondNum_AC.getValue().toString()});
        }
    }

    private void checkDateFromTo(NMAL7280BMsg scrnMsg, int i) {
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A)
                && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A)) {

            if (ZYPDateUtil.compare(scrnMsg.A.no(i).effThruDt_A.getValue()
                    , scrnMsg.A.no(i).effFromDt_A.getValue()) < 0) {
                scrnMsg.A.no(i).effFromDt_A.setErrorInfo(1, NMAM8115E);
                scrnMsg.A.no(i).effThruDt_A.setErrorInfo(1, NMAM8115E);

            }
        }
    }

    private void checkSameConditionNo(NMAL7280BMsg scrnMsg, int i) {
        ArrayList<BigDecimal> checkList = new ArrayList<BigDecimal>(CONDITION_SIZE);
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A1)) {
            checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A1.getValue());
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A2)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A2.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A2.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A2.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A2.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A3)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A3.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A3.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A3.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A3.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A4)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A4.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A4.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A4.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A4.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A5)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A5.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A5.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A5.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A5.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A6)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A6.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A6.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A6.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A6.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A7)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A7.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A7.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A7.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A7.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A8)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A8.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A8.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A8.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A8.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A9)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_A9.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_A9.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A9.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_A9.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AA)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_AA.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_AA.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_AA.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_AA.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AB)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_AB.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_AB.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_AB.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_AB.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_AC)) {
            if (checkList.contains(scrnMsg.A.no(i).prcRuleCondNum_AC.getValue())) {
                scrnMsg.A.no(i).prcRuleCondNum_AC.setErrorInfo(1, NMAM8294E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_AC.getNameForMessage() });
            } else {
                checkList.add(scrnMsg.A.no(i).prcRuleCondNum_AC.getValue());
            }
        }
    }

    private void checkMandatoryValue(NMAL7280BMsg scrnMsg, int i) {
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcRuleCondNum_A1)) {
            scrnMsg.A.no(i).prcRuleCondNum_A1.setErrorInfo(1, NMAM0836E, new String[] {scrnMsg.A.no(i).prcRuleCondNum_A1.getNameForMessage()});
        }
    }

    private static void setErr(int detailNo, int positionNo, String value1, String value2 , NMAL7280BMsg scrnMsg) {

        if (positionNo == CONDITION_NO_01) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A1.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_02) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A2.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_03) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A3.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_04) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A4.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_05) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A5.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_06) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A6.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_07) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A7.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_08) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A8.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_09) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_A9.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_10) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_AA.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_11) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_AB.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        } else if (positionNo == CONDITION_NO_12) {
            scrnMsg.A.no(detailNo).prcRuleCondNum_AC.setErrorInfo(1, NMAM0070E, new String[] {value1, value2});
        }
    }

    private void addDetailCheckItem(NMAL7280BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A4);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A5);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A6);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A7);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A8);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_A9);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_AA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_AB);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcRuleCondNum_AC);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A);
        }
    }

}
