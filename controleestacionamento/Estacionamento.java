/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleestacionamento;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
/**
 *
 * @author PC
 */
//FACADE
public class Estacionamento implements Serializable{
    double valorCarro, valorMoto, valorCaminhonete;
    ArrayList<VagaCarro>       vagasCarros       = new ArrayList();
    ArrayList<VagaMoto>        vagasMotos        = new ArrayList();
    ArrayList<VagaCaminhonete> vagasCaminhonetes = new ArrayList();    
    ArrayList<Contabilidade>   conta             = new ArrayList();
    
    private static Estacionamento uniqueInstance;
    
    public static synchronized Estacionamento getInstance(){
        if (uniqueInstance == null)
            uniqueInstance = new Estacionamento();
 
        return uniqueInstance;
    }
    private Estacionamento(){
        //valor default
        valorCarro = 5;
        valorMoto = 2.5;
        valorCaminhonete = 15;
        
        //inicializa arrays list
        for (int ano = 2000; ano < 2019; ano++) {
            for (int mes = 1; mes < 13; mes++) {
                for (int dia = 1; dia < 32; dia++) { 
                    Contabilidade c = new Contabilidade(dia, mes, ano);
                    conta.add(c);
                }
            }
        }
        
        for (int i=0; i<160; i++) { 
            VagaCarro vaga = new VagaCarro(i, null); 
            vagasCarros.add(vaga);
        }
        
        for (int i=0; i<20; i++) { 
            VagaMoto vagaMoto = new VagaMoto(i, null); 
            vagasMotos.add(vagaMoto);
        }
        
        for (int i=0; i<20; i++) { 
            VagaCaminhonete vagaCaminhonete = new VagaCaminhonete(i, null); 
            vagasCaminhonetes.add(vagaCaminhonete);
        }
        
        
        
        
        
        
    }
    
    public String placaCarro(int i){
        return vagasCarros.get(i).carro.placa;
    }
    public String placaMoto(int i){
        return vagasMotos.get(i).moto.placa;
    }
    public String placaCam(int i){
        return vagasCaminhonetes.get(i).caminhonete.placa;
    }
    
    public Date dataCarro(int i){
        return vagasCarros.get(i).carro.entrada;
    }
    public Date dataMoto(int i){
        return vagasMotos.get(i).moto.entrada;
    }
    public Date dataCam(int i){
        return vagasCaminhonetes.get(i).caminhonete.entrada;
    }
    
    
    
    public void configuracao(double valorMoto, double valorCarro, double valorCaminhonete) throws Exception{
        //valor eh numero            
        if((valorMoto>0)&&(valorCarro>0)&&(valorCaminhonete>0)){
            this.valorMoto = valorMoto;
            this.valorCarro = valorCarro;
            this.valorCaminhonete = valorCaminhonete;
        }else{
            //valor negativo
            throw new NumeroNegativoException("Preço do estacionamento não pode ser negativo.");
        }
        
    }
    //retorna a vaga do carro
    public int buscarCarro(String placa){
        int n = vagasCarros.size();
        for (int i=0; i<n; i++) {
            if(vagasCarros.get(i).carro != null ){
                if(vagasCarros.get(i).carro.placa == null ? placa == null : vagasCarros.get(i).carro.placa.equals(placa)){
                    return i;
                }
            }
        }
        return -1;
    }
    
    //retorna a vaga da Moto
    public int buscarMoto(String placa){
        int n = vagasMotos.size();
        for (int i=0; i<n; i++) {
            if(vagasMotos.get(i).moto != null ){
                if(vagasMotos.get(i).moto.placa == null ? placa == null : vagasMotos.get(i).moto.placa.equals(placa)){
                    return i;
                }
            }
        }
        return -1;
    }
    
    //retorna a vaga da caminhonte
    public int buscarCaminhonete(String placa){
        int n = vagasCaminhonetes.size();
        for (int i=0; i<n; i++) {
            if(vagasCaminhonetes.get(i).caminhonete != null ){
                if(vagasCaminhonetes.get(i).caminhonete.placa == null ? placa == null : vagasCaminhonetes.get(i).caminhonete.placa.equals(placa)){
                    return i;
                }
            }
        }
        return -1;
    }
    
    //retorna a vaga (posicao no array) em que o carro se encontra
    public int entrarCarro(String placa, Date horario) throws Exception{

        
        int vaga, n = vagasCarros.size();
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        
        if(data.after(horario)){
            //System.out.println("n "+n);
            if(buscarCarro(placa)==-1){
                vaga = proxVaga();
                if(vaga < n){
                    //System.out.println("vaga = "+vaga);
                    Carro carroAtual = new Carro(placa, horario, valorCarro); 
                    vagasCarros.get(vaga).setCarro(carroAtual);
                        
                    return vaga;
                    //System.out.println("vagasCarros.get(vaga) = "+vagasCarros.get(vaga));
                }else{
                    //nao cabem mais carros
                    throw new NoSpaceException("Não é possível inserir mais carros, estacionamento lotado.");
                }
            }else{
                //carro ja esta no estacionamento
                throw new AlreadyExistException("Carro placa " + placa +" já está no estacionamento.");
            }
        }else{
            //hora nao pode ser maior que hora atual
            throw new NotValidDateException("Carro placa " + placa +" não pode ser inserido em data futura a data atual.");
        }
    }
    
    //retorna a vaga (posicao no array) em que a moto se encontra
    public int entrarMoto(String placa, Date horario) throws Exception{
        int vaga, n = vagasMotos.size();
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        
        if(data.after(horario)){
            if(buscarMoto(placa)==-1){
                vaga = proxVagaMoto();
                if(vaga < n){
                    Moto motoAtual = new Moto(placa, horario, valorMoto); 
                    vagasMotos.get(vaga).setMoto(motoAtual);
                        
                    return vaga;
                }else{
                    //nao cabem mais motos
                    throw new NoSpaceException("Não é possível inserir mais motos, estacionamento lotado.");
                }
            }else{
                //moto ja esta no estacionamento
                throw new AlreadyExistException("Moto placa " + placa +" já está no estacionamento.");
            }
        }else{
            //hora nao pode ser maior que hora atual
            throw new NotValidDateException("Moto placa " + placa +" não pode ser inserido em data futura a data atual.");
        }
    }
    
    //retorna a vaga (posicao no array) em que o caminhonete se encontra
    public int entrarCaminhonete(String placa, Date horario) throws Exception{
        int vaga, n = vagasCaminhonetes.size();
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        
        if(data.after(horario)){
            if(buscarCaminhonete(placa) == -1){
                vaga = proxVagaCaminhonete();
                if(vaga < n){
                    Caminhonete caminhoneteAtual = new Caminhonete(placa, horario, valorCaminhonete); 
                    vagasCaminhonetes.get(vaga).setCaminhonete(caminhoneteAtual);
                        
                    return vaga;
                }else{
                    //nao cabem mais caminhonetes
                    throw new NoSpaceException("Não éh possível inserir mais caminhonetes, estacionamento lotado.");
                }
            }else{
                //caminhonete ja esta no estacionamento
                throw new AlreadyExistException("Caminhonete placa " + placa +" já está no estacionamento.");
            }
        }else{
            //hora nao pode ser maior que hora atual
            throw new NotValidDateException("Caminhonete placa " + placa +" não pode ser inserido em data futura a data atual.");
        }
    }
    
    //retorna valor a ser pago quando carro sai (tira carro do array)
    public double sairCarro(String placa, Date saida) throws Exception{
        int vaga = buscarCarro(placa);
        double valor = 0;
        if(vaga != -1){        
           Carro carroAtual = vagasCarros.get(vaga).carro;
           carroAtual.setSaida(saida);
           valor = carroAtual.calculaValor();
            System.out.println("valor = "+valor);
            if(valor >= 0){
                vagasCarros.get(vaga).carro = null;
            
                int posicaoDia = buscarData(saida);
                if( posicaoDia != -1){
                    conta.get(posicaoDia).l.valor += valor;
                    conta.get(posicaoDia).l.qtdCarro++;
                }else{
                    //Data nao esta contabilizada
                    throw new InvalidDateException("Data invalida.");
                }
            }else{
                //Carro nao pode sair se nao estiver entrado
                throw new InvalidDateException("Data anterior a data de entrada do carro.");
            }
        }else{
            //carro nao existe
            throw new VeiculoNotFoundException("Carro placa "+ placa +" não foi encontrado no estacionamento.");
        }
        return valor;
    }
    
    //retorna valor a ser pago quando moto sai (tira moto do array)
    public double sairMoto(String placa, Date saida) throws Exception{
        int vaga = buscarMoto(placa);
        double valor = 0;
        
        if(vaga != -1){        
            Moto motoAtual = vagasMotos.get(vaga).moto;
            motoAtual.setSaida(saida);
            valor = motoAtual.calculaValor();
            
            if(valor >= 0){
                vagasMotos.get(vaga).moto = null;
                
                int posicaoDia = buscarData(saida);
                if( posicaoDia != -1){
                    conta.get(posicaoDia).l.valor += valor;
                    conta.get(posicaoDia).l.qtdMoto++;
                }else{
                    //Data nao esta contabilizada
                    throw new InvalidDateException("Data invalida.");
                }
            }else{
                //Moto nao pode sair se nao estiver entrado
                throw new InvalidDateException("Data anterior a data de entrada da moto.");
            }
        }else{
            //Moto nao existe no estacionamento
            throw new VeiculoNotFoundException("Moto placa " + placa + " não foi encontrado no estacionamento.");
        }
        return valor;
    }
    
     //retorna valor a ser pago quando Caminhonete sai (tira Caminhonete do array)
    public double sairCaminhonete(String placa, Date saida) throws Exception{
        int vaga = buscarCaminhonete(placa);
        double valor = 0;
        
        if(vaga != -1){        
            Caminhonete caminhoneteAtual = vagasCaminhonetes.get(vaga).caminhonete;
            caminhoneteAtual.setSaida(saida);
            valor = caminhoneteAtual.calculaValor();
            
            if(valor >= 0){
                vagasCaminhonetes.get(vaga).caminhonete = null;
            
                int posicaoDia = buscarData(saida);
                if( posicaoDia != -1){
                    conta.get(posicaoDia).l.valor += valor;
                    conta.get(posicaoDia).l.qtdCaminhonete++;
                }else{
                    //Data nao esta contabilizada
                    throw new InvalidDateException("Data invalida.");
                }
            }else{
                //Caminhonete nao pode sair se nao estiver entrado
                throw new InvalidDateException("Data anterior a data de entrada do caminhonete.");
            }
        }else{
            //Caminhonete nao existe
            throw new VeiculoNotFoundException("Caminhonete placa "+ placa +" não foi encontrado no estacionamento.");
        }
        
        return valor;
    }
    
    //imprime array de vagas de carros
    public void visualizarCarros(){
        int n = vagasCarros.size();
        for (int i = 0; i < n; i++) {
            if(vagasCarros.get(i).carro != null){
                System.out.println(vagasCarros.get(i).imprime());    
            }
        }   
    }
   
    //retorna se uma vaga de carro esta ocupada
    public int vagaCarroOcupada(int posicao){
        if(vagasCarros.get(posicao).carro != null){
                return 1; //esta ocupada
        }
        return 0; //vaga vazia
    }
    
    //retorna se uma vaga de moto esta ocupada
    public int vagaMotoOcupada(int posicao){
        if(vagasMotos.get(posicao).moto != null){
                return 1; //esta ocupada
        }
        return 0; //vaga vazia
    }
    
    //retorna se uma vaga de Caminhonete esta ocupada
    public int vagaCaminhoneteOcupada(int posicao){
        if(vagasCaminhonetes.get(posicao).caminhonete != null){
                return 1; //esta ocupada
        }
        return 0; //vaga vazia
    }
    
    //retorna proxima vaga vazia para carros
    public int proxVaga(){
        int n = vagasCarros.size();
        int i = 0;
        for (i=0; i<n; i++) {
            if(vagasCarros.get(i).carro == null){
                return i;
            }       
        } 
        return i;
    }
 
    //retorna proxima vaga vazia para motos
    public int proxVagaMoto(){
        int n = vagasMotos.size();
        int i = -1; //nao existe mais vagas
        for (i=0; i<n; i++) {
            if(vagasMotos.get(i).moto == null){
                return i;
            }       
        } 
        return i;
    }
 
    //retorna proxima vaga vazia para caminhonetes
    public int proxVagaCaminhonete(){
        int n = vagasCaminhonetes.size();
        int i = -1;//nao existe mais vagas
        for (i=0; i<n; i++) {
            if(vagasCaminhonetes.get(i).caminhonete == null){
            return i;
            }       
        } 
        return i;
    }
 
    public int buscarData(Date data){
        //System.out.println("data = "+data);
        int n = conta.size();
        LocalDate localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        
        for (int i=0; i<n; i++) {
            //System.out.println(conta.get(i).imprime());
            //System.out.println("data = "+data);
            //System.out.println("data.getDay() = "+day);
            //System.out.println("data.getMonth() = "+month);
            //System.out.println("data.getYear() = "+year);
            if((conta.get(i).dia == day)&&
               (conta.get(i).mes == month)&&
               (conta.get(i).ano == year)){   
                return i;
           }     
        } 
        return -1;
    }
    
    //retorna o lucro e qtd de veiculos entre duas datas
    public LucroDiario contabilizar(Date data1, Date data2) throws InvalidDateException{
        double valorTotal = 0;
        int posDt1 = buscarData(data1);
        int posDt2 = buscarData(data2);
        int qtdC = 0, qtdCam = 0, qtdM = 0;
        
        if(posDt1 <= posDt2){
            if((posDt1 != -1)&&(posDt2 != -1)){
                for (int i = posDt1; i <= posDt2; i++) {
                    valorTotal += conta.get(i).l.valor;
                    qtdC       += conta.get(i).l.qtdCarro;
                    qtdCam     += conta.get(i).l.qtdCaminhonete;
                    qtdM       += conta.get(i).l.qtdMoto;
                }
            }else{
                //uma das datas nao esta contabilizada
                throw new InvalidDateException("Data invalida.");
            }
        }else{
            //data 1 eh menor que data 2
            throw new InvalidDateException("Data 1 menor que data 2.");
        }
        
        LucroDiario l = new LucroDiario(valorTotal, qtdC, qtdCam, qtdM);
        return l;
    }
}
