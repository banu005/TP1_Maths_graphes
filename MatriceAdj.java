public class MatriceAdj {

     int[][] matrice = new int[4][4];
  //constructeur
    public MatriceAdj(int[][] matrice) {
        this.matrice = matrice;
    }

    //getter et setters
    public int[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(int[][] matrice) {
        this.matrice = matrice;
    }


    //fonction qui attend en entrée une représentation d’un graphe et retourne
    //’vrai’ si le graphe est orienté, ’faux ’ sinon.
static boolean estOriente(int[][] matrice){
    int n = matrice.length;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            // on fait un if pour vérifier si le graphe est orienté en fonction des valeurs de i et j
            if (matrice[i][j] != matrice[j][i]) {
                return true;
            }else {
                return false;
            }
        }
 }
    return false;
}

//fonction qui attend une entrée une matrice adjacence et retourne ’vrai’ si le graphe est valué, ’faux ’ sinon.
static boolean estValue(int[][] matrice){
    int n = matrice.length;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            // on fait un if pour vérifier si le graphe est valué en fonction des valeurs de i et j
            if (matrice[i][j] != 0 && matrice[j][i] != 1) {
                return true;
            }else {
                return false;
            }
        }
 }
    return false;

}

}

