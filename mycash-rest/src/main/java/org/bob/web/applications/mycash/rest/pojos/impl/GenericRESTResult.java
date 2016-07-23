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
    
    
}
