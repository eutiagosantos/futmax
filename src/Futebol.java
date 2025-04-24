package src;

import java.util.Scanner;
import java.util.Arrays;
/*
 * Problema:
 * 
 * "Dado um array de skills de jogadores de futebol, determinar o 
 * maior time balanceado possível em que a diferença de skills
 * entre quaisquer pares de jogadores no mesmo time não seja
 * maior que 5."
 * 
 * Exemplos de input/output: tests/
 * 
 * Não é permitido o uso da biblioteca Arrays.
 * É permitido o uso de códigos próprios
 * desenvolvidas em aulas anteriores.
 * 
 * Ver especificação em pdf para mais detalhes.
*/
public class Futebol {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        // Leitura do número de jogadores
        int n = leitor.nextInt();
        int[] skills = new int[n];
        
        // Leitura das skills
        for (int i = 0; i < n; i++) {
            skills[i] = leitor.nextInt();
        }
        leitor.close();
        
        // Cálculo do maior time possível
        int maxTeamSize = maiorTimePossivel(skills);

        System.out.println(maxTeamSize);
    }
  
    /*
                                            EXPLICAÇÃO COMPLEXIDADE

      Essa proposta de solução tem uma complexidade de tempo O(n log n), o mergesort foi o algoritimo implementado
      dos que eu conheço é o unico que no pior caso a complexidade de tempo ia ser menor que n^2

      Ordenei o array de skills e usei uma tecnica para definir subarrays com restrição (janela deslizante)
      Foi um dos unicos que eu achei pesquisando que faria sentido nessa pratica
    */
    
    private static void sort(int[] arr){
      if(arr == null || arr.length < 2) return;
      
      int mid = arr.length / 2;
      int left[] = new int[mid];
      int right[] = new int[arr.length - mid];

      sort(left);
      sort(right);
      
      merge(arr, left, right);
    }
    
    private static void merge(int[] arr,int[] left,int[] right){
        int k=0, j = 0, i = 0;

        while (i < left.length && j < right.length) {
          if (left[i] <= right[j]) {
            arr[k] = left[i];
            i++;
          } else {
            arr[k] = right[j];
            j++;
          }
          k++;
        }
        while (i < left.length) {
          arr[k] = left[i];
          i++;
          k++;
        }
        while (j < right.length) {
          arr[k] = right[j];
          j++;
          k++;
        }
	}
  
    private static int maiorTimePossivel(int[] skills) {
        
        sort(skills);

        int amountOfTeams = 0;
        int j = 0;
        
        for(int i=0;i<skills.length;i++){
          while(Math.abs(skills[j] - skills[i]) > 5){
            j++;
          }
          amountOfTeams = Math.max(amountOfTeams, i - j + 1);
        }
        return amountOfTeams;
    }
}
