package Bataille_de_cartes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Bataille {

    public static void main(String[] args) {
        ArrayList<Carte> jeuDeCartes = new ArrayList();
        for (String symbole : Carte.SYMBOLES){
            for (int valeur: Carte.VALEURS){
                jeuDeCartes.add(new Carte(symbole, valeur));
            }
        }


        Collections.shuffle(jeuDeCartes);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le pseudo du Joueur 1 : ");
        String pseudoJoueur1 = scanner.nextLine();
        System.out.print("Entrez le pseudo du Joueur 2 : ");
        String pseudoJoueur2 = scanner.nextLine();

        // ETAPE 3 : créer les 2 joueurs

        Joueur JOUEUR1 = new Joueur(pseudoJoueur1);
        Joueur JOUEUR2 = new Joueur(pseudoJoueur2);



        // ETAPE 4 : distribuer le jeu de carte mélangé au 2 joueurs
        // indice : chaque joueur possède un attribut "paquet" qui correspond à un tableau (type: Carte[])
        // ou une ArrayList (type: ArrayList<Carte>)) c'est à cette étape qu'on initialise la valeur de
        // la variable d'instance "paquet" de chaque joueur

        for (int i = 0; i < jeuDeCartes.size(); i++){
            if (i % 2 == 0) {
                JOUEUR1.addCarte(jeuDeCartes.get(i));  // Ajouter une carte à Joueur 1
            } else {
                JOUEUR2.addCarte(jeuDeCartes.get(i));  // Ajouter une carte à Joueur 2
            }
        }







        // ETAPE 5 : la partie commence, tant qu'il reste des cartes à au moins un joueur (boucle), on a :
        //  1. chaque joueur tire et joue une carte
        //  2. on compare les 2 cartes c1 et c2 (on doit avoir une méthode public compare(Carte c) dans la classe Carte
        //  3. le joueur avec la carte la plus forte remporte le pli donc on met à jour l'attribut compteur de points
        //  du joueur qui récupère les cartes
        //  4. afficher quel joueur remporte le pli et les scores
        // indice : on peut utiliser un autre tableau (ou ArrayList) pour stocker temporairement les cartes remportées
        // et le redéfinir en paquet principal une fois que celui-ci est vide (i.e. n'a plus de Carte)
        // help : si vous n'arrivez pas à gérer la récupération des cartes et la mise à jour des paquets,
        // vous pouvez aussi faire une solution plus simple qui consiste à retirer les cartes jouées du jeu
        // à ce moment là, la partie se termine une fois que les 52 cartes du paquet ont été jouées


        int delai = 1000;
        while (!JOUEUR1.getPaquet().isEmpty() || !JOUEUR2.getPaquet().isEmpty() ||
                !JOUEUR1.getPaquetTemporaire().isEmpty() || !JOUEUR2.getPaquetTemporaire().isEmpty()) {

            // Vérifie si les paquets principaux sont vides
            if (JOUEUR1.getPaquet().isEmpty()) {
                if (!JOUEUR1.getPaquetTemporaire().isEmpty()) {
                    JOUEUR1.temporaireToPrincipal();
                } else {
                    System.out.println(JOUEUR1.getNom() + " n'a plus de cartes !");
                }
            }

            if (JOUEUR2.getPaquet().isEmpty()) {
                if (!JOUEUR2.getPaquetTemporaire().isEmpty()) {
                    JOUEUR2.temporaireToPrincipal();
                } else {
                    System.out.println(JOUEUR2.getNom() + " n'a plus de cartes !");
                }
            }

            // Condition de sortie de la boucle
            if ((JOUEUR1.getPaquet().isEmpty() && JOUEUR1.getPaquetTemporaire().isEmpty()) ||
                    (JOUEUR2.getPaquet().isEmpty() && JOUEUR2.getPaquetTemporaire().isEmpty())) {
                break; // Sortir de la boucle si les deux joueurs n'ont plus de cartes
            }

            // Chaque joueur tire une carte s'ils ont encore des cartes
            if (!JOUEUR1.getPaquet().isEmpty() || !JOUEUR1.getPaquetTemporaire().isEmpty()) {
                Carte CarteJoueur1 = JOUEUR1.pullOutCarte();
                // Si JOUEUR2 a encore des cartes, tirer une carte
                Carte CarteJoueur2 = JOUEUR2.pullOutCarte();

                // Comparer les cartes et récupérer le résultat
                int resultat = Carte.Comparer(JOUEUR1, JOUEUR2, CarteJoueur1, CarteJoueur2);

                // Le gagnant récupère les cartes
                if (resultat == 1) {
                    JOUEUR1.addToTemporaire(CarteJoueur2);
                    JOUEUR1.augmenterScore();
                    System.out.println(JOUEUR1.getNom() + " a gagné cette manche !");
                } else if (resultat == 2) {
                    JOUEUR2.addToTemporaire(CarteJoueur1);
                    JOUEUR2.augmenterScore();
                    System.out.println(JOUEUR2.getNom() + " a gagné cette manche !");
                } else {
                    System.out.println("Égalité ! Aucune carte n'est ajoutée aux paquets temporaires.");
                }
            }

            // Mettre en pause entre chaque tour
            try {
                Thread.sleep(delai); // Délai de 1,5 seconde
            } catch (InterruptedException e) {
                System.err.println("Le thread a été interrompu : " + e.getMessage());
            }
        }




        // ETAPE 6 : la boucle s'est terminée car on est arrivé à la condition d'arrêt et on affiche
        // le vainqueur de la partie avec son nombre de point
        // indice : il faut réimplémenter la méthode toString() dans la classe Joueur

        if (JOUEUR1.getScore() > JOUEUR2.getScore()) {
            System.out.println(JOUEUR1.getNom() + " a gagné avec " + JOUEUR1.getScore() +" points." );
        }
        else if (JOUEUR2.getScore() > JOUEUR1.getScore()) {
            System.out.println(JOUEUR2.getNom() + " a gagné "+ JOUEUR2.getScore() +" points.");
        }
        else {
            System.out.println("Egalité ? Comment c'est possible");
            System.out.println("Score de Nassim : " + JOUEUR1.getScore());
            System.out.println("Score de Sara : " + JOUEUR2.getScore());
        }
    }
}