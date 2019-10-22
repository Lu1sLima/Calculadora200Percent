import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
public class Calculadora{
    private Pilha pilha;
    private int maxNumPilha = 0;

    public Calculadora(){
        pilha = new Pilha();
    }

    public void ler(){
        Scanner input = new Scanner(System.in);
        System.out.print("Informe o nome do arquivo que você quer ler: ");
        String arq = input.nextLine();
        Path path1 = Paths.get(arq+".txt");
        String where = path1.toString();
        try(BufferedReader br = new BufferedReader(new FileReader(where))){
            String linha = br.readLine();
            while(linha != null){
                try{
                    Double n = Double.parseDouble(linha);
                    System.out.println(n);
                    pilha.push(n);
                }
                catch(NumberFormatException e){
                    System.out.println(linha);
                    try{
                        switch (linha){
                            case "+":
                                pilha.push(Aritmeticas.soma(pilha.pop(), pilha.pop()));
                                break;
                            case "-":
                                pilha.push(Aritmeticas.sub(pilha.pop(), pilha.pop()));
                                break;
                            case "/":
                                pilha.push(Aritmeticas.div(pilha.pop(), pilha.pop()));
                                break;
                            case "*":
                                pilha.push(Aritmeticas.mult(pilha.pop(), pilha.pop()));
                                break;
                            case "pop":
                                pilha.pop();
                                break;
                            case "dup":
                                if(!pilha.isEmpty()){
                                    pilha.push(pilha.top());
                                }
                                else{
                                    throw new IndexOutOfBoundsException();
                                }
                                break;
                            case "swap":
                                if(pilha.size() >= 2){
                                    Double op1 = pilha.pop();
                                    Double op2 = pilha.pop();
                                    pilha.push(op1);
                                    pilha.push(op2);
                                    break;
                                }
                            case "chs":
                                pilha.push(-(pilha.pop()));
                                break;
                            case "sqrt":
                                pilha.push(Math.sqrt(pilha.pop()));
                                break;
                            default:
                                throw new IllegalArgumentException("Caractere invalido!");
                        }
                    }catch(IndexOutOfBoundsException f){
                        System.out.println("Nao tem numeros para concluir essa operacao!");
                        System.exit(0);
                    }
                    catch(IllegalArgumentException h){
                        System.out.println("Operador invalido: "+h.getMessage());
                        System.exit(0);
                    }
                    catch(ArithmeticException p){
                        System.out.println("Erro: "+p.getMessage());
                        System.exit(0);
                    }
                }
                if(maxNumPilha < pilha.size()){
                    maxNumPilha = pilha.size();
                }
                linha = br.readLine();
            }
            if(pilha.size() > 1){
                throw new RuntimeException("Resultado inválido, faltam operadores para completar a operacao. Tamanho da pilha: "+pilha.size());
            }
        }catch(IOException e){
            System.out.println("Error :"+e.getMessage());
        }catch(RuntimeException u){
            System.out.println("Error: "+u.getMessage());
            System.exit(0);
        }
        System.out.println("Resultado armazenado no topo da pilha: "+pilha.top());
        System.out.println("Tamanho máximo da pilha: "+maxNumPilha);
    }

    static class Aritmeticas{

        public static Double soma(Double a, Double b){
           // System.out.println("Soma: "+(a+b));
            return a+b;
        }

        public static Double sub(Double a, Double b){
           // System.out.println("Subtracao: "+(a-b));
            return a-b;
        }

        public static Double div(Double a, Double b){
            if(b == 0){
                throw new ArithmeticException("Dividendo eh zero!");
            }
           // System.out.println("Divisao: "+(a/b));
            return a/b;
        }

        public static Double mult(Double a, Double b){
           // System.out.println("Mult: "+(a*b));
            return a*b;
        }
    }

    public static void main(String[]args){ // main para testar só!
        Calculadora c1 = new Calculadora();
        c1.ler();
    }
}