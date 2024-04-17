/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC015001;

import parts.common.EZDItem;
import parts.common.EZDMsg;

/**
 * <pre>
 * </pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2009   Fujitsu         H.Haga          Create          N/A
 *</pre>
 */
class NLXC015005 {

    static EZDItem getEZDItem(EZDMsg ezdMsg, String itemNm) throws IllegalAccessException, NoSuchFieldException {
        EZDItem ezdItem = null;
        ezdItem = (EZDItem) ezdMsg.getClass().getField(itemNm).get(ezdMsg);
        return ezdItem;
    }

    static int getItemType(EZDMsg ezdMsg, String itemNm) {
        return ezdMsg.getSchema().getType(itemNm);
    }

    static int getItemJavaType(EZDMsg ezdMsg, String itemNm) {
        return ezdMsg.getSchema().getJavaType(itemNm);
    }

}
