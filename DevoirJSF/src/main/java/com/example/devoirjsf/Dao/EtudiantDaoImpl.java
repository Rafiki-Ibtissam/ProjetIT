package com.example.devoirjsf.Dao;

import com.example.devoirjsf.Model.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoImpl implements IEtudiantDao{
    @Override
    public Etudiant save(Etudiant s) {
        Connection connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps=connection.prepareStatement
                    ("INSERT INTO Etudiant (ID,NOM,PRENOM,EMAIL,NAISSANCE) VALUES (?,?,?,?,?)");
            ps.setInt(1,s.getId());
            ps.setString(2, s.getNom());
            ps.setString(3, s.getPrenom());
            ps.setString(4, s.getEmail());
            ps.setString(5,  s.getNaissance());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }



    @Override
    public List<Etudiant> findAll() {
        List <Etudiant> Etudiants = new ArrayList<Etudiant>();
        Connection connection = SingletonConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement
                    ("SELECT * FROM Etudiant ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etudiant s = new Etudiant();
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("nom"));
                s.setPrenom(rs.getString("prenom"));
                s.setEmail(rs.getString("email"));
                s.setNaissance(rs.getString("naissance"));
                Etudiants.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Etudiants;
    }

    @Override
    public Etudiant getEtudiant(int id) {
        Etudiant s = null;
        Connection connection = SingletonConnection.getConnection();

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement
                    ("SELECT * FROM Etudiant WHERE ID=?");
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                s = new Etudiant();
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("nom"));
                s.setPrenom(rs.getString("prenom"));
                s.setEmail(rs.getString("email"));
                s.setNaissance(rs.getString("naissance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Etudiant modifier(Etudiant s) {
        Connection  connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps=connection.prepareStatement
                    ("UPDATE  Etudiant SET NOM=?,PRENOM=?,EMAIL=?,NAISSANCE=?  WHERE ID=? ");

            ps.setString(1, s.getNom());
            ps.setString(2, s.getPrenom());
            ps.setString(3, s.getEmail());
            ps.setString(4,  s.getNaissance());
            ps.setInt(5,  s.getId());

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public void supprimer(int id) {
        Connection  connection = SingletonConnection.getConnection();
        try {
            PreparedStatement ps=connection.prepareStatement
                    ("DELETE FROM Etudiant WHERE ID=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }




}
