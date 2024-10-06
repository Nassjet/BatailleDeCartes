package Bataille_de_cartes;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Carte> paquet;
    private int score;
    private ArrayList<Carte> paquetTemporaire;

    // Constructeur avec un paquet vide au départ
    public Joueur(String nom) {
        this.nom = nom;
        this.paquet = new ArrayList<>();
        this.score = 0;
        this.paquetTemporaire = new ArrayList<>();
    }

    public ArrayList<Carte> getPaquet() {
        return paquet;
    }

    public int getScore() {
        return score;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Carte> getPaquetTemporaire() {
        return paquetTemporaire;
    }

    public void setPaquet(ArrayList<Carte> paquet) {
        this.paquet = paquet;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPaquetTemporaire(ArrayList<Carte> paquetTemporaire) {
        this.paquetTemporaire = paquetTemporaire;
    }

    // Retire la première carte du paquet et la retourne
    public Carte pullOutCarte() {
        if (paquet.size() > 0) {
            return paquet.remove(0); // Retire la carte en tête de liste
        }
        return null; // Aucune carte à retirer
    }

    // Ajoute une carte au paquet
    public void addCarte(Carte carte) {
        paquet.add(carte);
    }

    // Incrémente le score du joueur
    public void augmenterScore() {
        this.score++;
    }
    // Méthode qui ajoute une carte au paquet temporaire
    public void addToTemporaire(Carte carte) {
        paquetTemporaire.add(carte);
    }

    // Si tu veux afficher les cartes sous forme de chaîne dans le Joueur
    public String afficherPaquetTemporaire() {
        StringBuilder sb = new StringBuilder();
        for (Carte carte : paquetTemporaire) {
            sb.append(carte.toString()).append(", "); // Ajoute chaque carte à la chaîne
        }
        return sb.toString();
    }
    public String afficherPaquetPrincipal() {
        StringBuilder sb = new StringBuilder();
        for (Carte carte : paquet) {
            sb.append(carte.toString()).append(", "); // Ajoute chaque carte à la chaîne
        }
        return sb.toString();
    }
    public void temporaireToPrincipal() {
        if (!paquetTemporaire.isEmpty()) {
            paquet.addAll(paquetTemporaire);  // Ajoute toutes les cartes du paquet temporaire au paquet principal
            paquetTemporaire.clear();  // Vide le paquet temporaire
            System.out.println("Le paquet temporaire de " + nom + " a été transféré au paquet principal.");
        } else {
            System.out.println("Le paquet temporaire de " + nom + " est vide. Aucun transfert effectué.");
        }
    }

}
