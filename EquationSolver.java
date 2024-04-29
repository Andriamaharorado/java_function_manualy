package matrice;
import java.util.Scanner;

public class EquationSolver {
	 public static int saisirInt(Scanner scan, String message) {
		int valeur;
		System.out.print(message);
		valeur = scan.nextInt();
		return valeur;
	 }
	 public static double[] inputList(Scanner scan, int ligne) {
        double[] b = new double[ligne];
        System.out.println("Entrez maintenant les valeurs pour le vecteur de résultats b :");
        for (int k = 0; k < ligne; k++) {
            System.out.println(String.format("Entrez b[%d] :", k));
            b[k] = scan.nextDouble();
        }
        return b;
     }
	 public static void printMatrix(double[][] matrix) {
		System.out.println("Voici la matrice obtenue :");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
     }
	 public static void printVector(double[] vector) {
	    System.out.println("Voici le vecteur :");
	    for (int i = 0; i < vector.length; i++) {
	        System.out.println(String.format("b[%d] = %f", i, vector[i]));
	    }
	 }
	 public static void inputMatrix(Scanner scanner, double[][] matrix, int ligne, int colonne) {
		System.out.println("Nous allons commencer à saisir les données dans la matrice :");
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                System.out.print(String.format("Entrez a[%d][%d] : ", i, j));
                matrix[i][j] = scanner.nextDouble();
            }
        }
	 }
	 public static double calculateDeterminant(double [][]mat) {
		 double det = 1;
		 for (int i = 0; i < mat.length; i++) {
			 det *= mat[i][i];
		 }
		 return det;
	 }
	 public static double[] calculateResolutionMatrixTriangulaire(double [][] A, double [] b) {
		 double [] resolution = new double [A[0].length];
	        
	        for (int i = A[0].length - 1; i >= 0; i--) {
	        	double sum = 0;
	        	for (int j = A[0].length - 1; j >= i + 1; j--) {
	        		sum += A[i][j] * resolution[j];
	        	}
	        	resolution[i] = 1 / A[i][i] * (b[i] - sum);
	     }
	     return resolution;
	 }
	 public static double[][] copyMatrix(double [][]A, int ligne_keep){
		double [][] newMatrice = new double [A[0].length][A[1].length];
		// Copier la première ligne de la matrice d'origine dans la nouvelle matrice
		for (int i = 0; i < A[0].length; i++) {
        	for (int j = 0; j < A[1].length; j++) {
                newMatrice[i][j] = A[i][j];
            }
        }
		return newMatrice;
	 }
	 public static double[] copyVector(double []B, int ligne_keep) {
		 double [] newVector = new double [B.length];
		 for (int i = 0; i < ligne_keep; i++) {
			 newVector[i] = B[i];
		 }
		 return newVector;
	 }
	 public static double[][] matricePermutationZero(double [][]C){
		 for (int j = 0; j < C[0].length - 1; j++) {
	        	for (int i = 0; i < C[0].length - 1; i++) {
	        		double res = i - j;
	            	if (C[i][0] == 0 || C[(int) (res = 0)][(int) (res = 0)] == 0) {
	            		double [] copy = copyVector(C[i], C[0].length);
	            		C[i] = C[i + 1];
	            		C[i + 1] = copy;
	            	}
	            	else {
	            		C[i] = C[i];
	            	}
	            }
	        }
		 return C;
	 }
	 public static void solveMatriceFull( double [][] A, double [] B, int ligne, int colonne ) {
		 // Première étape : Permuter les lignes dans la matrice pour que 0 ne se trouve pas 
		 // sur la diagonale de la matrice
		 
		 for (int j = 0; j < A[0].length - 1; j++) {
        	for (int i = 0; i < A[0].length - 1; i++) {
        		double res = i - j;
        		if (A[i][0] == 0 || A[(int) (res = 0)][(int) (res = 0)] == 0) {
            		double [] copy = copyVector(A[i], A[0].length);
            		double [] copy_vec = copyVector(B, B.length);
            		A[i] = A[i + 1]; 
            		B[i] = B[i + 1];
            		A[i + 1] = copy;
            		B[i + 1] = copy_vec[i];
            	}
            	else {
            		A[i] = A[i];
            		B[i] = B[i];
            	}
            }
	     }
		 System.out.println("La nouvelle matrice après permutation des lignes :");
		 printMatrix(A);
		 System.out.println("Le nouveau vecteur après permutation des lignes en fonction de la matrice :");
		 printVector(B);
		 
		 // Deuxième étape : Copier les premières lignes du vecteur et 
		 // de la matrice dans respectivement un nouveau vecteur et une nouvelle matrice
		 
		 double [] newVecteur = copyVector(B, 1);
		 double [][] newMatrice = copyMatrix(A, 1);
		 
		 // Troisième étape : Construire la matrice triangulaire
		 for(int k = 0; k < colonne - 1; k++) {
	        for (int i = k + 1; i < ligne; i++) {
        		double vecteur = A[k][k] * B[i] - A[i][k] * B[k];
        		newVecteur[i] = vecteur;
        		for (int j = colonne-1; j >=0; j--) {
        			double resultat = A[k][k] * A[i][j] - A[i][k] * A[k][j];
        			newMatrice[i][j] = resultat;
        		}
        	}
        	B = newVecteur;
        	A = newMatrice;
	     }
		 System.out.println("La matrice triangulaire :");
		 printMatrix(A);
		 System.out.println("La nouvelle forme du vecteur en fonction de la matrice triangulaire :");
		 printVector(B);
		 
		 double determinant = calculateDeterminant(A);
		 System.out.println("Le déterminant de la matrice est de " + determinant);
		 
		 if (determinant != 0) {
			 double [] solution = calculateResolutionMatrixTriangulaire(A, B);
			 printVector(solution);
		 }
		 else {
			 System.out.print("La matrice n'a pas de solution");
		 }
	 }
}