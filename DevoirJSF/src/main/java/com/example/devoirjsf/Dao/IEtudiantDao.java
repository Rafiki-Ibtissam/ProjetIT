package com.example.devoirjsf.Dao;

import com.example.devoirjsf.Model.Etudiant;

import java.util.List;

public interface IEtudiantDao {

    public Etudiant save(Etudiant s);
    public List<Etudiant> findAll();
    public Etudiant getEtudiant(int id );
    public Etudiant modifier(Etudiant p) ;
    public void supprimer(int id);

}
