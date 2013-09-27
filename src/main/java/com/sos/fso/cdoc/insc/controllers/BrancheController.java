/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.controllers;

import com.sos.fso.cdoc.insc.entities.Branche;
import com.sos.fso.cdoc.insc.services.BrancheFacade;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author mab.salhi
 */
@Named(value = "brancheController")
@Dependent
public class BrancheController {

    @Inject
    private BrancheFacade brancheService;
    private List<Branche> branches;
    private Branche branche;
    /**
     * Creates a new instance of BrancheController
     */
    public BrancheController() {
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }
    
    
    
    
public Branche getBranche(java.lang.Integer id) {
        return brancheService.find(id);
    }
    
     @FacesConverter(forClass = Branche.class)
    public static class BrancheControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BrancheController controller = (BrancheController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "brancheController");
            return controller.getBranche(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Branche) {
                Branche o = (Branche) object;
                return getStringKey(o.getIdBranche());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Branche.class.getName());
            }
        }
    
}
    
}
