package matrice;
import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// étape 1 : Si on veut entrer automatiquement la matrice
		// saisir le nombre de lignes et de colonnes
		
		// Scanner scan = new Scanner(System.in);
		//int ligne = EquationSolver.saisirInt(scan, "Saisir le nombre de lignes :");
		//int colonne = EquationSolver.saisirInt(scan, "Saisir le nombre de colonnes :");
		
		// étape 2 : Définir les variables matricielles et vectorielles
		// double [] B = new double[ligne];
		//double [][] A = new double [ligne][colonne];
		
		// étape 3 : saisir le vecteur à droite et la matrice
		
		//EquationSolver.inputMatrix(scan, A, ligne, colonne);
		//double [] B = EquationSolver.inputList(scan, ligne);
		
		
		// Étape 1 : si on veut entrer manuellement la matrice et résoudre l'équation
		
		// matrice d'ordre 3
		int ligne=3, colonne=3;
		double [][] A = {{2,1,-1},{-1,2,2},{-3,-1,1}};
		double [] B = {3,12,-5};
		
		// étape 4 : Résoudre l'équation
		EquationSolver.solveMatriceFull(A, B, ligne, colonne);
		
	}
}
