package Bataille_de_cartes;

public class Carte {
    public static String[] SYMBOLES = {"coeur", "pique", "carreau", "trèfle"};
    public static int[] VALEURS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    private String symbole;
    private int valeur;

    public Carte(String symbole, int valeur) {
        this.symbole = symbole;
        this.valeur = valeur;
    }

    public String getSymbole() {
        return symbole;
    }

    public int getValeur() {
        return valeur;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public static int Comparer(Joueur joueur1, Joueur joueur2, Carte carteJ1, Carte carteJ2) {
        if (carteJ1.getValeur() > carteJ2.getValeur()) {
            System.out.println(joueur1.getNom() + " a gagné ce tour avec la carte " + carteJ1.getValeur() +" de "+ carteJ1.getSymbole() + " contre le " + carteJ2.getValeur()+ " de " + carteJ2.getSymbole());
            return 1; // Joueur 1 gagne
        } else if (carteJ2.getValeur() > carteJ1.getValeur()) {
            System.out.println(joueur2.getNom() + " a gagné ce tour avec la carte " + carteJ2.getValeur()+" de "+ carteJ2.getSymbole() + " contre le " + carteJ1.getValeur()+ " de " + carteJ1.getSymbole());
            return 2; // Joueur 2 gagne
        } else {
            System.out.println("Égalité entre " + joueur1.getNom() + " et " + joueur2.getNom() + " ! La valeur des cartes étaient de " + carteJ1.getValeur());
            return 0; // Égalité
        }
    }

    @Override
    public String toString() {
        return "Carte{" +
                "symbole='" + symbole + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
