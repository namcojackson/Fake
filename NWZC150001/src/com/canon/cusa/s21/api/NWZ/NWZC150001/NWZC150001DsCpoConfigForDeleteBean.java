/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import parts.common.EZDMsg;
import business.parts.NWZC150001_cpoConfigPMsg;

/**
 * <pre>
 * DS CPO Update API: DsCpoConfig For Delete Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/06   Fujitsu         S.Takami        Create          S21_NA#7821
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * </pre>
 */
public class NWZC150001DsCpoConfigForDeleteBean {

    /** Current (On Database) DS_ORD_POSN_NUM */
    private String curDsOrdPosnNum;

    // 2018/06/05 S21_NA#25151 Add Start
    /**  Modified Flag */
    private boolean isModified = false;
    // 2018/06/05 S21_NA#25151 Add End

    /** DS CPO Update API parameter: cpoConfigPMsg */
    private NWZC150001_cpoConfigPMsg cpoConfigPMsg;

    /**
     * <pre>
     * Constructor
     * @param cpoConfigPMsg
     * </pre>
     */
    public NWZC150001DsCpoConfigForDeleteBean(NWZC150001_cpoConfigPMsg cpoConfigPMsg) {

        this.cpoConfigPMsg = new NWZC150001_cpoConfigPMsg();
        EZDMsg.copy(cpoConfigPMsg, null, this.cpoConfigPMsg, null);
    }

    /**
     * get current ds_ord_posn_num
     * @return current ds_ord_posn_num
     */
    public String getCurDsOrdPosnNum() {
        return curDsOrdPosnNum;
    }

    /**
     * set current ds_ord_posn_num
     * @param curDsOrdPosnNum current ds ord position number
     */
    public void setCurDsOrdPosnNum(String curDsOrdPosnNum) {
        this.curDsOrdPosnNum = curDsOrdPosnNum;
    }

    /**
     * get cpo config pmsg
     * @return cpo config pmsg
     */
    public NWZC150001_cpoConfigPMsg getCpoConfigPMsg() {
        return cpoConfigPMsg;
    }

    // 2018/06/05 S21_NA#25151 Add Start
    /**
     * @return Modified Flag
     */
    public boolean isModified() {
        return isModified;
    }

    /**
     * @param isModifiedPos Modified Position
     */
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
    // 2018/06/05 S21_NA#25151 Add End
}
