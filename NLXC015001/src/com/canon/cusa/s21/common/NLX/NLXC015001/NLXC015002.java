/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import parts.common.EZDItem;
import parts.common.EZDItemAttrDefines;
import parts.common.EZDMsgArray;

/**
 * <pre>
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
public class NLXC015002 {

    /** */
    private Set<String> targetItemNmSet = new HashSet<String>();

    /** */
    private EZDMsgArray ezdMsgArray;

    /**
     * Constructor
     * 
     * <pre>
     *
     * S21SortTarget sortTarget = new S21SortTarget( scrnMsg.A, scrnMsg.A.no( 0 ).getBaseContents() );
     * </pre>
     * 
     * @param baseContents
     */
    public NLXC015002(EZDMsgArray ezdMsgArray, String[][] baseContents) {
        this.ezdMsgArray = ezdMsgArray;
        for (int i = 0; i < baseContents.length; i++) {
            targetItemNmSet.add(baseContents[i][0]);
        }
    }

    EZDItem getEZDItem(String itemNm, int index) throws IllegalAccessException, NoSuchFieldException {
        return NLXC015005.getEZDItem(ezdMsgArray.get(index), itemNm);
    }

    int getItemType(String itemNm, int index) {
        return NLXC015005.getItemType(ezdMsgArray.get(index), itemNm);
    }

    String getValueString(String itemNm, int index) {
        String rtnValue = "";
        switch (NLXC015005.getItemJavaType(ezdMsgArray.get(index), itemNm)) {
            case EZDItemAttrDefines.JT_BIGDECIMAL:
                BigDecimal bc = ezdMsgArray.get(index).getValueBigDecimal(itemNm, -1);
                if (bc != null) {
                    rtnValue = bc.toPlainString();
                }
                break;
            case EZDItemAttrDefines.JT_STRING:
                rtnValue = ezdMsgArray.get(index).getValueString(itemNm, -1);
                break;

            default:
                break;
        }
        return rtnValue;
    }

    EZDMsgArray getEzdMsgArray() {
        return ezdMsgArray;
    }

    Set<String> getTargetItemNmSet() {
        return targetItemNmSet;
    }

}
