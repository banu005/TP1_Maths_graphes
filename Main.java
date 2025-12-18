import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        
        int[][] matrice = new int[4][4];
        
        // Sommet x1 
        matrice[0][0] = 0;
        matrice[0][1] = 1;
        matrice[0][2] = 1;
        matrice[0][3] = 0;
        
        // Sommet x2
        matrice[1][0] = 0;
        matrice[1][1] = 0;
        matrice[1][2] = 1;
        matrice[1][3] = 1;
        
        // Sommet x3 
        matrice[2][0] = 0;
        matrice[2][1] = 0;
        matrice[2][2] = 0;
        matrice[2][3] = 0;
        
        // Sommet x4
        matrice[3][0] = 0;
        matrice[3][1] = 0;
        matrice[3][2] = 1;
        matrice[3][3] = 0;
        

        ListeAdj[] listeAdj = new ListeAdj[4];
        
        // Sommet x1
        listeAdj[0] = new ListeAdj(1, null);
        listeAdj[0].suivant = new ListeAdj(1, null);
        listeAdj[0].suivant.suivant = new ListeAdj(3, null);
        // Sommet x2
        listeAdj[1] = new ListeAdj(2, null);
        listeAdj[1].suivant = new ListeAdj(2, null);
        listeAdj[1].suivant.suivant = new ListeAdj(4, null);
        // Sommet x3
        listeAdj[2] = new ListeAdj(3, null);
        // Sommet x4
        listeAdj[3] = new ListeAdj(4, null);
        listeAdj[3].suivant = new ListeAdj(4, null);
        
        //1.Orientation des graphes
        System.out.println("1. Orientation des graphes");
        System.out.println("MatriceAdj estOriente : " + MatriceAdj.estOriente(matrice));
        System.out.println("ListeAdj estOriente : " + ListeAdj.estOriente(listeAdj));
        
        //2. vérifier si le graphe est valué
        // Test avec une matrice d'adjacence non valuée
        System.out.println("\n2. Les arcs n'ont pas tous la même valeur");
        System.out.println("MatriceAdj estValue : " + MatriceAdj.estValue(matrice));
        
        
        // Test avec une liste d'adjacence valuée
        ListeAdj[] listeAdjValuee = new ListeAdj[4];
        listeAdjValuee[0] = new ListeAdj(1, 3, new ListeAdj(2, 5, null));
        listeAdjValuee[1] = new ListeAdj(2, 2, null);
        listeAdjValuee[2] = null;
        listeAdjValuee[3] = null;
        System.out.println("ListeAdj valuée estValue : " + ListeAdj.estValue(listeAdjValuee));
        
        //3.Les degrés atteignent des sommets
        System.out.println("\n3. Degrés sortants et entrants");
        System.out.println("Degré sortant du sommet 0 (MatriceAdj) : " + MatriceAdj.degreeSortant(matrice, 0));
        System.out.println("Degré entrant du sommet 2 (MatriceAdj) : " + MatriceAdj.degreeEntrant(matrice, 2));
        System.out.println("Degré sortant du sommet 0 (ListeAdj) : " + ListeAdj.degreeSortant(listeAdj, 0));
        System.out.println("Degré entrant du sommet 2 (ListeAdj) : " + ListeAdj.degreeEntrant(listeAdj, 2));
        
        // 4.Plus haut et plus bas degré
        System.out.println("\n4. Degrés minimum et maximum");
        System.out.println("Degré sortant min (MatriceAdj) : " + MatriceAdj.degreeSortantMin(matrice));
        System.out.println("Degré sortant max (MatriceAdj) : " + MatriceAdj.degreeSortantMax(matrice));
        System.out.println("Degré sortant min (ListeAdj) : " + ListeAdj.degreeSortantMin(listeAdj));
        System.out.println("Degré sortant max (ListeAdj) : " + ListeAdj.degreeSortantMax(listeAdj));
        
        // 5.Je précède mon successeur
        System.out.println("\n5. Successeurs et prédécesseurs");
        int[] successeurs0 = MatriceAdj.successeurs(matrice, 0);
        System.out.print("Successeurs du sommet 0 (MatriceAdj) : ");
        afficherTableau(successeurs0);
        
        int[] predecesseurs2 = MatriceAdj.predecesseurs(matrice, 2);
        System.out.print("Prédécesseurs du sommet 2 (MatriceAdj) : ");
        afficherTableau(predecesseurs2);
        
        int[] successeurs0Liste = ListeAdj.successeurs(listeAdj, 0);
        System.out.print("Successeurs du sommet 0 (ListeAdj) : ");
        afficherTableau(successeurs0Liste);
        
        int[] predecesseurs2Liste = ListeAdj.predecesseurs(listeAdj, 2);
        System.out.print("Prédécesseurs du sommet 2 (ListeAdj) : ");
        afficherTableau(predecesseurs2Liste);
        
        // 6. Des connaissances communes
        System.out.println("\n6. Voisins communs");
        System.out.println("Sommets 0 et 1 ont un voisin commun (MatriceAdj) : " 
                          + MatriceAdj.voisinCommun(matrice, 0, 1));
        System.out.println("Sommets 0 et 3 ont un voisin commun (MatriceAdj) : " 
                          + MatriceAdj.voisinCommun(matrice, 0, 3));
        System.out.println("Sommets 0 et 1 ont un voisin commun (ListeAdj) : " 
                          + ListeAdj.voisinCommun(listeAdj, 0, 1));

        // 7. Où est la sortie (entrée) ?
        System.out.println("\n7. Entrées et sorties");
        System.out.println("Il existe une entrée (MatriceAdj) : " + MatriceAdj.entree(matrice));
        System.out.println("Il existe une sortie (MatriceAdj) : " + MatriceAdj.sortie(matrice));
        System.out.println("Il existe une entrée (ListeAdj) : " + ListeAdj.entree(listeAdj));
        System.out.println("Il existe une sortie (ListeAdj) : " + ListeAdj.sortie(listeAdj));
        
        // 8. Un sommet pour les gouverner tous
        System.out.println("\n8. Sommet universel");
        System.out.println("Il existe un sommet universel (MatriceAdj) : " + MatriceAdj.universel(matrice));
        System.out.println("Il existe un sommet universel (ListeAdj) : " + ListeAdj.universel(listeAdj));
        
        //9. Qu'importe les graphes s'exportent
        System.out.println("\n9. Export vers fichier");
        MatriceAdj.exporter(matrice, "graphe_matrice.txt");
        ListeAdj.exporter(listeAdj, "graphe_liste.txt");
        
        //10. Qu'importe les graphes
        System.out.println("\n10. Import depuis fichier");
        int[][] matriceImportee = MatriceAdj.importer("graphe_matrice.txt");
        if (matriceImportee != null) {
            System.out.println("Matrice importée - estOriente : " + MatriceAdj.estOriente(matriceImportee));
        }
        
        ListeAdj[] listeImportee = ListeAdj.importer("graphe_liste.txt");
        if (listeImportee != null) {
            System.out.println("Liste importée - estOriente : " + ListeAdj.estOriente(listeImportee));
        }
        
    }

    // Fonction pour afficher un tableau
    private static void afficherTableau(int[] tableau) {
        if (tableau.length == 0) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (int i = 0; i < tableau.length; i++) {
                System.out.print(tableau[i]);
                if (i < tableau.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
    
} 