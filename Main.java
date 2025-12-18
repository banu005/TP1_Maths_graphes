

public class Main {
    public static void main(String[] args) {

    //1.Orientation des graphes
    System.out.println("implementation des valeurs estOriente pour MatriceAdj et ListeAdj");
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

//affichage des résultats
    System.out.println("MatriceAdj estOriente :");
    System.out.println(MatriceAdj.estOriente(matrice));

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
    
        //affichage des résultats
    System.out.println("ListeAdj estOriente :");
    System.out.println(ListeAdj.estOriente(listeAdj));

    //2.Les arcs n'ont pas tous la même valeur

    System.out.println("La matrice estValue :");
    System.out.println(MatriceAdj.estValue(matrice));

}
}