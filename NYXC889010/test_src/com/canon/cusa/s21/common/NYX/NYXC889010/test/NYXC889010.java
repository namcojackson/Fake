package com.canon.cusa.s21.common.NYX.NYXC889010.test;

import com.canon.cusa.s21.framework.common.logger.S21Logger;
import com.canon.cusa.s21.framework.common.logger.S21LoggerFactory;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfAuthException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;

/**
 * Dummy Business Logic
 * @author q09079
 *
 */
public class NYXC889010 implements S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {
    private static final S21Logger logger = S21LoggerFactory.getLogger(NYXC889010.class);

    @Override
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfAuthException, S21NwfBizException, S21NwfException {

        // Log
        logger.debug("$$$$$$ Process End API Start $$$$$$");
        logger.debug("Event Name:" + name);

        if (token.getTokenObj() != null) {
            logger.debug("TokenObject:" + token.getTokenObj());
        }
        logger.debug("$$$$$$ Process End API End $$$$$$");
    }

}
