package com.canon.cusa.s21.mail.imap;

import java.util.Date;

public class S21ImapMessage {
    
    private boolean validMessageFlag;
    
    private String[] from;
    
    private String subject;
    
    private Date date;
    
    private String body;
    
    
    public void setValidMessageFlag(boolean validMessageFlag) {
        this.validMessageFlag = validMessageFlag;
    }
    
    public boolean getValidMessageFlag() {
        return this.validMessageFlag;
    }
    
    public void setFrom(String[] from) {
        this.from = from;
    }
    
    public String[] getFrom() {
        // QC#19549 [IT Nightly JOB Abend]S21SWD_SZWJ0050010 Mail Clean Batch
        if (this.from == null || this.from.length == 0) {
            this.from = new String[]{""};
        }

        return this.from;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getSubject() {
        // QC#19549 [IT Nightly JOB Abend]S21SWD_SZWJ0050010 Mail Clean Batch
        if (this.subject == null) {
            this.subject = "";
        }

        return this.subject;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getBody() {
        // QC#19549 [IT Nightly JOB Abend]S21SWD_SZWJ0050010 Mail Clean Batch
        if (this.body == null) {
            this.body = "";
        }

        return this.body;
    }

}
