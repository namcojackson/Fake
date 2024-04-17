/*
 * Copyright (c) 2008 Canon USA Inc. All rights reserved.
 * Original Author: ${user}
 * Company: Fujitsu Limited
 * Date: ${date}
 */

package business.servlet.ZZSL0000;

import parts.common.EZDBMsgArray;
import parts.common.EZDMsg;

/**
 * ZZSL0000BMsgArray is a Screen Data Message Array class.
 * @author $Author: Arti Kumari $ Arti Kumari
 * @version $Revision: 1.2 $ $Date: 2008/12/23 16:28:48 $
 */
public class ZZSL0000BMsgArray extends EZDBMsgArray {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * get ZZSL0000BMsg of the specified element number.
     * @param n Index Number
     * @return ZZSL0000BMsg
     */
    public ZZSL0000BMsg no(int n) {
        return (ZZSL0000BMsg) get(n);
    }

    /**
     * The message career type in the array is acquired.
     * @return ZZSL0000BMsg
     */
    public EZDMsg getMyComponent() {
        return new ZZSL0000BMsg();
    }
}
