/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bob.web.applications.mycash.rest.pojos.impl;

import org.bob.web.applications.mycash.rest.pojos.RestObjectBean;

/**
 *
 * @author roberto.gatti
 */
public class GenericRESTResult implements RestObjectBean {
    
    private int code;
    
    private String message;
    
    private Object data;
    
    public GenericRESTResult(int code, String message, Object data)
    {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public GenericRESTResult(String message, Object data)
    {
        this.setCode(0);
        this.setMessage(message);
        this.setData(data);
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public final void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public final void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public final void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
