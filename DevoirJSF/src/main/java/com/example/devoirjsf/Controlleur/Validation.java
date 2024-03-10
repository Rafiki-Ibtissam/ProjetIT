package com.example.devoirjsf.Controlleur;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

public class Validation implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String) value;
        String date = (String) value;

        // Vérification du format de l'email
        if (component.getId().equals("email")) {
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Format d'email invalide.", null));
            }
        }

        // Vérification du format de la date
        if (component.getId().equals("naissance")) {
            if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Format de date invalide (YYYY-MM-DD).", null));
            }
        }
    }
}
