/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaDuplaPKG;

/**
 *
 * @author Iago
 *
    TP2 - AED
    Aluno: Iago Rocha Gomes
    Nº: 66453
*/
import java.io.*;
import java.util.Scanner;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.text.*;

class REG {
    // Tipo abstrato que será utilizado para por os registos na fila
    String Nome;
    String data;
    String telefone;
    REG prox;
    REG ant;

    public REG(String nom, String dat, String telef) {
        // construtor da classe REG
        Nome = nom;
        data = dat;
        telefone = telef;
        prox = null;
        ant = null;
    }
}


public class listaDupla {
    // Definição do tipo lista dupla
    REG Esq;
    REG Direita;

    public listaDupla() {
        // Construtor da lista
        Esq = null;
        Direita = null;
    }

    public int preencherLista(String str) {
        // metodo que realiza a leitura de um csv ou binario e preenche a lista com base nos dados 
        //nele contidos
        int tipo = 0;
        String ext = "";
        int index;
        String nom_fich=str;
        String fich="";
        BufferedReader br = null;
        String linha = "";
        String fichSplitBy = ".";
        String cvsSplitBy = ";";
        String binario = "bin";
        String csv = "csv";
        String texto = "txt";
        

        try {
            fich=nom_fich;
            index = fich.lastIndexOf(fichSplitBy);
            if(index > 0) {
                ext = fich.substring(index + 1);
                
              }

            if (ext.equals(binario)) {
                tipo = 1;
                
            }
            if (ext.equals(csv) || ext.equals(texto)) {
                tipo=2;
            }
            
            /*if(tipo == 1){
                try {
                    FileInputStream fpin = new FileInputStream (nom_fich);
                    DataInputStream Din = new DataInputStream (fpin);
                    byte n_char;
                    byte [] Nome;
                    int dia;
                    int mes;
                    int ano;
                    int telefone;
                    
                    while((Din.available()) > 0){
                        n_char= Din.readByte();
                        Nome = new byte[n_char];
                        int a=Din.read(Nome, 0, n_char);
                        String aux=new String(Nome);
        
                        dia=Din.readInt();
                        mes=Din.readInt();
                        ano=Din.readInt();
                        telefone=Din.readInt();

                        List.ColocaElementoDirLista(aux, dia, mes, ano, telefone);
                    }
                    
                    Din.close();
                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Problemas na abertura, leitura ou escrita. \n");
                }

            }*/

            if (tipo == 2) {
                String Nome;
                String data;
                String telefone;

                br = new BufferedReader(new FileReader(nom_fich));
                while ((Nome = br.readLine()) != null) {
                    data = br.readLine();
                    telefone = br.readLine();
                    ColocaElementoDirLista(Nome, data, telefone);
                }
            }
            

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problemas na abertura, leitura ou escrita. \n");
        }
        return tipo;
    }

    public void ColocaElementoEsqLista(String n, String d, String t) {
        // Metodo que põe os elementos na esquerda da lista
        REG aux = new REG(n, d, t);
        aux.prox = Esq;
        if (Esq != null) {
            Esq.ant = aux;
        }
        Esq = aux;
        if (Esq != null && Direita == null) {
            Direita = Esq;
        }

    }

    public void ColocaElementoDirLista(String n, String d, String t) {
        // Metodo que põe os elementos na direita da lista
        REG aux = new REG(n, d, t);
        aux.ant = Direita;
        if (Direita != null) {
            Direita.prox = aux;
        }
        Direita = aux;
        if (Direita != null && Esq == null) {
            Esq = Direita;
        }
    }

    public void Mostra() {
        // Metodo que exibe a lista
        REG w = Esq;
        System.out.println(" ");
        System.out.println("Nome: \t Data de Nascimento: \t Telefone:");
        while (w != null) {
            System.out.print(w.Nome);
            System.out.print("\t");
            System.out.print(w.data);
            System.out.print("\t");
            System.out.println(w.telefone);
            w = w.prox;
        }

    }

    /*public void GravaBin(listaDupla List, int s){
        // Metodo que grava a lista para bin
        String nom_fich;
        try{
            if (s == 0) {
                nom_fich = "Lista.bin";
            } else if (s == 1) {
                nom_fich = "Maiores_que_18.bin";
            } else {
                nom_fich = "Menores_que_18.bin";
            }

            FileOutputStream fpout = new FileOutputStream(nom_fich);
            DataOutputStream out = new DataOutputStream(fpout);
            REG w = Esq;
            byte n_char;
            String name;
            int d;
            int m;
            int a;
            int t;
            String str = "";
            while (w != null) {
                str=w.Nome;
                n_char = (byte) str.length();
                name = new String(str);
                d = w.dia;
                m = w.mes;
                a = w.ano;
                t = w.telefone;

                out.writeByte(n_char);
                out.writeBytes(name); 
                out.writeInt(d); 
                out.writeInt(m); 
                out.writeInt(a); 
                out.writeInt(t); 
                w = w.prox;
            }
            out.close();


        }catch (IOException e) {
            System.out.println("Problemas na abertura, leitura ou escrita. \n");
        }
        
    }*/
    
    public int conta(){
        int contador=0;
        REG w = Esq;
        while(w != null){
            w = w.prox;
            contador++;
        }
        return contador;
    }
    
    public listaDupla BubbleSort() {
        int i=0, j;
        listaDupla ListaO = new listaDupla();
        int numero=conta();
        REG w = Esq;
        REG v[]=new REG[numero];
        while(w != null){
            v[i]=w;
            w = w.prox;
            i++;
        }
        REG tmp;
        for (i = 0; i < numero - 1; i++) {
            for (j = 0; j < numero - 1 - i; j++) {
                if (v[j].Nome.compareTo( v[j + 1].Nome)>0) {
                    tmp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = tmp;
                }
            }
            
        }
        for(i=0;i<numero;i++){
            ListaO.ColocaElementoDirLista(v[i].Nome, v[i].data, v[i].telefone);
        }
        return ListaO;
    }
    
    public String[][] carregaLista(){
        String f[][]={};
        int NumLinhas;
        NumLinhas= conta();
        REG w = Esq;
        f = new String[NumLinhas][3];
        int i, j;
        int NumReg = NumLinhas;
            for (i = 0; i < NumReg; i++) {
               
                for (j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            f[i][j] = w.Nome;
                            break;
                        case 1:
                            f[i][j] = w.data;
                            break;
                        default:
                            f[i][j] = w.telefone;
                            break;
                    }
                }
                w = w.prox;
            }
        System.out.println("Numero de Registos:" + NumReg);
        for (i = 0; i < NumReg; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println("");
        }
        return f;
}
    
    public void GravaSorted(listaDupla List){
        String nom_fich="sorted.txt";
        try{
            //FileWriter fpout = new FileWriter(nom_fich);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nom_fich));
            REG w = Esq;
            while(w != null){
                bw.write(w.Nome);
                bw.newLine();
                bw.write(w.data);
                bw.newLine();
                bw.write(w.telefone);
                bw.newLine();
                w = w.prox;
            }
            //fpout.close();
            bw.close();
        }catch(IOException E){
            E.printStackTrace();
            System.out.println("Problemas na abertura ou leitura \n");
        }
        
    }

    public void Grava(listaDupla List, int s) {
        // Metodo que grava a lista para csv
        String nom_fich;
        try {
            if (s == 0) {
                nom_fich = "Lista.csv";
            } else if (s == 1) {
                nom_fich = "Maiores_que_18.txt";
            } else {
                nom_fich = "Menores_que_18.txt";
            }
            //FileWriter fpout = new FileWriter(nom_fich);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nom_fich));
            REG w = Esq;
            String d;
            String m;
            String a;
            String t;

            while (w != null) {
                bw.write(w.Nome);
                bw.newLine();
                bw.write(w.data);
                bw.newLine();
                bw.write(w.telefone);
                bw.newLine();
                w = w.prox;
            }
            //fpout.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problemas na abertura ou leitura \n");
        }

    }
    
    public void Grava18Mais(listaDupla List) {
        // Metodo que grava a lista para csv
        String nom_fich;
        try {
                nom_fich = "Maiores_que_18.txt";
            //FileWriter fpout = new FileWriter(nom_fich);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nom_fich));
            REG w = Esq;
            String d;
            String m;
            String a;
            String t;

            while (w != null) {
                bw.write(w.Nome);
                bw.newLine();
                bw.write(w.data);
                bw.newLine();
                bw.write(w.telefone);
                bw.newLine();
                w = w.prox;
            }
            //fpout.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problemas na abertura ou leitura \n");
        }

    }
    
    public void Grava18Menos(listaDupla List) {
        // Metodo que grava a lista para csv
        String nom_fich;
        try {
                nom_fich = "Menores_que_18.txt";
            //FileWriter fpout = new FileWriter(nom_fich);
            BufferedWriter bw = new BufferedWriter(new FileWriter(nom_fich));
            REG w = Esq;
            String d;
            String m;
            String a;
            String t;

            while (w != null) {
                bw.write(w.Nome);
                bw.newLine();
                bw.write(w.data);
                bw.newLine();
                bw.write(w.telefone);
                bw.newLine();
                w = w.prox;
            }
            //fpout.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problemas na abertura ou leitura \n");
        }

    }

    public boolean deltaData(String data){
        boolean maior=false;
        String nascimento= data;

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date date = new Date();
            Date firstDate = sdf.parse(nascimento);
            Date secondDate = date;
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff >= (18*365)){
                maior=true;
            }
        }catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Problemas na conversão de datas \n");
        }
        
        
        return maior;
    }
    
    

    public listaDupla Maior18() {
        // metodo que seleciona aqueles que tem idade maior ou igual a 18 na lista e os
        // coloca em outra lista
        listaDupla Lista2 = new listaDupla();
        REG w = Esq;
        boolean m18;
        while (w != null) {
            m18 = deltaData(w.data);
            if (m18) {
                Lista2.ColocaElementoDirLista(w.Nome, w.data, w.telefone);
            }
            w = w.prox;
        }
        return Lista2;
    }

    public listaDupla Menor18() {
        // metodo que seleciona aqueles que tem inferior a 18 na lista e os coloca em
        // uma outra lista
        listaDupla Lista3 = new listaDupla();
        REG w = Esq;
        boolean m18;
        while (w != null) {
            m18 = deltaData(w.data);
            if (m18 == false) {
                Lista3.ColocaElementoDirLista(w.Nome, w.data, w.telefone);
            }
            w = w.prox;
        }
        return Lista3;
    }
}