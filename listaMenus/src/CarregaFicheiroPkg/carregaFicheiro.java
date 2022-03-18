/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarregaFicheiroPkg;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iago
 */
public class carregaFicheiro {
    public String Nomeficheiro;
    public String f[][];
    int NumLinhas;
    public carregaFicheiro(String ficheiro){
        NumLinhas = 0;
        Nomeficheiro = ficheiro;
        String str;
// determinação do numero de linhas
// código da TP2
        try {
            BufferedReader in = new BufferedReader(new FileReader(Nomeficheiro));
            str = "";
            while ((str = in.readLine()) != null) {
                NumLinhas++;
//System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println("Erro de entrada 1\n");
        }
// sabendo o numero de linhas teremos NumLinhas/3 linhas por 3 colunas
// que serão Numero, Nome e Data de nascimento
// construção do f com vector bidimensional de Object’s

        f = new String[NumLinhas / 3][3];
        int i, j;
        int NumReg = NumLinhas / 3;
// E agora a carga do ficheiro para o f[][]
        try {
            BufferedReader in = new BufferedReader(new FileReader(Nomeficheiro));
            for (i = 0; i < NumReg; i++) {
                for (j = 0; j < 3; j++) {
                    str = in.readLine();
                    f[i][j] = str;

                }
            }
        } catch (IOException e) {
            System.out.println("Erro de entrada 2\n");
        }
// Este bloco só é funcional se o programa for evocado
// numa janela consola (cmd)
        System.out.println("Numero de Registos:" + NumReg);
        for (i = 0; i < NumReg; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println("");
        }
}
}
