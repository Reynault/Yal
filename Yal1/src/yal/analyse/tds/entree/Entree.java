package yal.analyse.tds.entree;

import java.util.Objects;

/**
 * Classe qui modélise une entrée dans la table des symboles
 */
public abstract class Entree {
    // Nom de l'entrée
    protected String nom;

    /**
     * Constructeur
     * @param nom le nom de l'entrée
     */
    public Entree(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode de récupération du nom de l'entrée
     * @return le nom
     */
    public abstract String getNom();
}
