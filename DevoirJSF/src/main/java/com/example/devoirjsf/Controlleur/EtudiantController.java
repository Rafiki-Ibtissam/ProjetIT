package com.example.devoirjsf.Controlleur;

import com.example.devoirjsf.Dao.EtudiantDaoImpl;
import com.example.devoirjsf.Model.Etudiant;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ManagedBean
@SessionScoped
public class EtudiantController implements Serializable {
    private List<Etudiant> etudiants;
    private Etudiant etudiant = new Etudiant();
    private EtudiantDaoImpl etudiantDAO = new EtudiantDaoImpl();

    public List<Etudiant> getEtudiants() {
        etudiants = etudiantDAO.findAll();
        return etudiants;
    }


    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


    public boolean isEmailUnique(String email) {
        List<Etudiant> allEtudiants = etudiantDAO.findAll();
        for (Etudiant etudiant : allEtudiants) {
            if (etudiant.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }
    public String ajouterEtudiant() {

        if (!isEmailUnique(etudiant.getEmail())) {
            FacesContext.getCurrentInstance().addMessage("form:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Cet email existe déjà."));
            return null;
        }

        etudiantDAO.save(etudiant);
        actualiserEtudiants();
        etudiant = new Etudiant();


        return "etudiant";
    }

    private void actualiserEtudiants() {

        etudiants = etudiantDAO.findAll();
    }


    public void supprimerEtudiant(int id) {
        etudiantDAO.supprimer(id);
        actualiserEtudiants();

    }
    public Etudiant getEtudiantById(int id) {
        return etudiantDAO.getEtudiant(id);
    }

    public void initModifierEtudiant() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> paramMap = externalContext.getRequestParameterMap();
        String idParam = paramMap.get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            etudiant = etudiantDAO.getEtudiant(id); // Charger l'étudiant depuis la base de données
        }
    }


    public String modifierEtudiant() {

        etudiantDAO.modifier(this.etudiant);
        actualiserEtudiants();
        //pour vider l'etudiant
        etudiant.setId(0);
        etudiant.setNom("");
        etudiant.setPrenom("");
        etudiant.setEmail("");
        etudiant.setNaissance("");
        //redirection vers page etudiant.xhtml

        return "etudiant";
    }


    public String changeLocale(String language) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
        return null;
    }




}
