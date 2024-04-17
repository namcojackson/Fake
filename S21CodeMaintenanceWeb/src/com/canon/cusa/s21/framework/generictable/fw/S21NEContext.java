package com.canon.cusa.s21.framework.generictable.fw;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

public class S21NEContext implements Serializable {

    private static final long serialVersionUID = 1L;

    HttpSession session;

    public S21NEContext(HttpSession session) {
        this.session = session;
    }

    public void setAttribute(String key, Object obj) {
        session.setAttribute(key, obj);
    }

    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }
}
