/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenaRegistosPkg;

import CarregaFicheiroPkg.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Iago
 */

class registo{
    String nome;
    String data;
    String telefone;
    public registo(String nom, String dat, String tel){
        nome=nom;
        data=dat;
        telefone=tel;
    }
}
public class orderTable {
    public registo v[];
    public String mat[][];
    public String Nomfich="";
    static String nom_fich= "Sorted.txt";
    int nregs;
    public orderTable(String str){
        int NumeroLinhas=0;
        String ficheiro= str;
        String line="";
        // determinação do numero de linhas
        // código da TP2
        try {
            BufferedReader in = new BufferedReader(new FileReader(ficheiro));
            str = "";
            while ((line = in.readLine()) != null) {  
                NumeroLinhas++;
            }
            nregs=(NumeroLinhas/3);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro de entrada 1\n");
        }
        v = new registo[nregs];
        mat = new String[NumeroLinhas / 3][3];
        try {
            BufferedReader in = new BufferedReader(new FileReader(str));
            for (int i = 0; i < nregs; i++) {
                for (int j = 0; j < 3; j++) {
                    str = in.readLine();
                    mat[i][j] = str;

                }
            }
        } catch (IOException e) {
            System.out.println("Erro de entrada 2\n");
        }
        int i=0,j=0;
        String aux;
        for(i=0;i<nregs;i++){
            aux=mat[i][j];
            v[i].nome=aux;
            for(j=0;j<3;j++){
                if(j==1){
                    aux=mat[i][j];
                    v[i].data=aux;
                }
                if(j==2){
                    aux=String.valueOf(mat[i][j]);
                    v[i].telefone=aux;
                }
            }
        }
        
        String tmp;
        for (i = 0; i < nregs - 1; i++) {
            for (j = 0; j < nregs - 1 - i; j++) {
                if (v[j].nome.compareTo(v[j + 1].nome)<0) {
                    tmp = v[j].nome;
                    v[j].nome = v[j + 1].nome;
                    v[j + 1].nome = tmp;
                }
            }
        }
        
        
        try{
            FileWriter fpout = new FileWriter(nom_fich);
            for(i=0;i<nregs;i++){
                fpout.write(v[i].nome);
                fpout.write("\n");
                fpout.write(v[i].data);
                fpout.write("\n");
                fpout.write(v[i].telefone);
                fpout.write("\n");
            }
            fpout.close();
            
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Problemas na abertura ou escrita \n");
        }
        
    }
    public static String sorted(){
        return nom_fich;
    } 
    
   
}
