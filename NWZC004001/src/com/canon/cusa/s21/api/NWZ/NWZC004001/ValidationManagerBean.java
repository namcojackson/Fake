/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC004001;

import java.math.BigDecimal;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.SHPG_PLNTMsg;
import business.parts.NWXC005001PMsg;
//import business.db.DS_CPOTMsg;
//import business.db.DS_CPO_DTLTMsg;

import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2009   Fujitsu         S.Sugino        Create          N/A
 * 07/20/2012   Fujitsu         S.Iidaka        Update          WDS#110 Delivery Block
 * 2015/12/02   FUjitsu         S.Takami        Update          S21_NA#1368
 * 2017/03/29   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.1 S21 5714, 5716, 5717, 5718, 5719, 5720
 * </pre>
 */
public class ValidationManagerBean extends NWXC005001ValidationBean {

    public ValidationManagerBean(//
            NWXC005001PMsg inputPMsg //
            , CPOTMsg cTMsg //
            , CPO_DTLTMsg cdTMsg //
            , SHPG_PLNTMsg spTMsg) {
        super(inputPMsg, cTMsg, cdTMsg, spTMsg);
    }

    public ValidationManagerBean(//
            NWXC005001PMsg inputPMsg //
            , CPOTMsg cTMsg //
            , CPO_DTLTMsg cdTMsg //
            , SHPG_PLNTMsg spTMsg //
//            , DS_CPOTMsg dscTMsg // 2017/03/29 S21_NA#Review structure Lv.1 Del
//            , DS_CPO_DTLTMsg dscdTMsg // 2017/03/29 S21_NA#Review structure Lv.1
            , DS_CPO_CONFIGTMsg dccTMsg) {
        // super(inputPMsg, cTMsg, cdTMsg, spTMsg, dscTMsg, dscdTMsg);2015/12/02 S21_NA#1368 add dccTMsg
        // 2017/03/29 S21_NA#Review structure Lv.1 Mod Start
//        super(inputPMsg, cTMsg, cdTMsg, spTMsg, dscTMsg, dscdTMsg, dccTMsg);
        super(inputPMsg, cTMsg, cdTMsg, spTMsg, dccTMsg);
        // 2017/03/29 S21_NA#Review structure Lv.1 Mod End
    }

    private BigDecimal ordQty = null;

    public BigDecimal getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

}
