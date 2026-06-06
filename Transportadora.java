import java.util.Scanner;

interface Transporte {
    double calcularFrete(double peso, double altura, double largura, double distancia);
}

abstract class Veiculo implements Transporte {

    private int anoFabricacao;
    private String marca;
    private String modelo;
    private String propulsao;

    public int getAnoFabricacao()  { return anoFabricacao; }
    public String getMarca()       { return marca; }
    public String getModelo()      { return modelo; }
    public String getPropulsao()   { return propulsao; }

    public void setAnoFabricacao(int ano)      { anoFabricacao = ano; }
    public void setMarca(String m)             { marca = m; }
    public void setModelo(String m)            { modelo = m; }
    public void setPropulsao(String p)         { propulsao = p; }
}

class VeiculoTerrestre extends Veiculo {

    private int qtdeRodas;
    private int qtdePortas;
    private String placa;
    private String chassi;

    public int getQtdeRodas()    { return qtdeRodas; }
    public int getQtdePortas()   { return qtdePortas; }
    public String getPlaca()     { return placa; }
    public String getChassi()    { return chassi; }

    public void setQtdeRodas(int q)    { qtdeRodas = q; }
    public void setQtdePortas(int q)   { qtdePortas = q; }
    public void setPlaca(String p)     { placa = p; }
    public void setChassi(String c)    { chassi = c; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return 0;
    }
}

class VeiculoAereo extends Veiculo {

    private String rab; // Registro Aeronáutico Brasileiro
    private int qtdeMotores;

    public String getRab()         { return rab; }
    public int getQtdeMotores()    { return qtdeMotores; }

    public void setRab(String r)           { rab = r; }
    public void setQtdeMotores(int q)      { qtdeMotores = q; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return 0;
    }
}

class VeiculoFluvial extends Veiculo {

    private String registroMarinha;
    private double boca;    // largura do navio
    private double calado;  // altura submersa

    public String getRegistroMarinha()  { return registroMarinha; }
    public double getBoca()             { return boca; }
    public double getCalado()           { return calado; }

    public void setRegistroMarinha(String r)  { registroMarinha = r; }
    public void setBoca(double b)             { boca = b; }
    public void setCalado(double c)           { calado = c; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return 0;
    }
}

class Caminhao extends VeiculoTerrestre {

    private int qtdeEixos;
    private double capacidade;
    private String carroceria; // Basculante, Graneleiro, Baú, etc

    public int getQtdeEixos()       { return qtdeEixos; }
    public double getCapacidade()   { return capacidade; }
    public String getCarroceria()   { return carroceria; }

    public void setQtdeEixos(int q)         { qtdeEixos = q; }
    public void setCapacidade(double c)     { capacidade = c; }
    public void setCarroceria(String c)     { carroceria = c; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        double precoDiesel = 6.99;
        return (peso + (altura * largura)) * (distancia * precoDiesel);
    }
}

class Aviao extends VeiculoAereo {

    private double capacidadeCarga;
    private double mtow; // Maximum Take-Off Weight (Peso Máximo de Decolagem)

    public double getCapacidadeCarga()  { return capacidadeCarga; }
    public double getMtow()             { return mtow; }

    public void setCapacidadeCarga(double c)  { capacidadeCarga = c; }
    public void setMtow(double m)             { mtow = m; }

    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        double precoQuerosene = 9.99;
        return ((peso * peso) * (altura * largura)) * (distancia * precoQuerosene);
    }
}

class PortaContainer extends VeiculoFluvial {

    private double capacidadeCarga;
    private String categoria;

    public double getCapacidadeCarga()  { return capacidadeCarga; }
    public String getCategoria()      { return categoria; }

    public void setCapacidadeCarga(double c)  { capacidadeCarga = c; }
    public void setCategoria(String c)      { categoria = c; }


    @Override
    public double calcularFrete(double peso, double altura, double largura, double distancia) {
        return peso + (altura * largura) * distancia;
    }
}

public class Transportadora {

    static double calcularFrete(Transporte veiculo, double peso, double altura, double largura, double distancia) {
        return veiculo.calcularFrete(peso, altura, largura, distancia);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== SISTEMA DE FRETE ===");
        System.out.println();

        System.out.print("Peso da encomenda (kg): ");
        double peso = sc.nextDouble();

        System.out.print("Altura da encomenda (m): ");
        double altura = sc.nextDouble();

        System.out.print("Largura da encomenda (m): ");
        double largura = sc.nextDouble();

        System.out.print("Distância do frete (km): ");
        double distancia = sc.nextDouble();


        Caminhao caminhao = new Caminhao();
        Aviao aviao = new Aviao();
        PortaContainer portaContainer = new PortaContainer();

        double freteCaminhao       = calcularFrete(caminhao, peso, altura, largura, distancia);
        double freteAviao          = calcularFrete(aviao, peso, altura, largura, distancia);
        double fretePortaContainer = calcularFrete(portaContainer, peso, altura, largura, distancia);

        System.out.println();
        System.out.println("=== RESULTADO ===");
        System.out.printf("Caminhao:       R$ %.2f%n", freteCaminhao);
        System.out.printf("Aviao:          R$ %.2f%n", freteAviao);
        System.out.printf("Porta Container: R$ %.2f%n", fretePortaContainer);

        sc.close();
    }
}