package org.glsid.metier;



import org.glsid.entities.Compte;

public interface CompteMetier {
   public Compte saveCompte(Compte cp);
   public Compte getCompte(String code);
}