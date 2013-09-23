/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import com.sos.fso.cdoc.insc.services.MailerBean;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
public class SendMail {
    @Inject
    protected MailerBean mailerBean;
    protected String email;
    protected String status;
    private static final Logger logger = Logger.getLogger(SendMail.class.getName());
    private Future<String> mailStatus;

    public SendMail() {
    }
    
    
    
    public void SendEmail(String email, String key){
        String response = "response?faces-redirect=true";
        
        try {
            mailStatus = mailerBean.sendVerificationMail(email, key);
            this.setStatus("Envoie en cours ...(veuillez rafraishir !!!)");
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
        }
        
        //return response;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
