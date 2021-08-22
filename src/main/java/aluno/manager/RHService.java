package aluno.manager;

import aluno.base.Funcionario;
import aluno.base.Professor;
import aluno.base.STA;
import cliente.IRHService;

import java.util.*;

public class RHService implements IRHService {

    private final Map<String, Funcionario> cadastros;
    private final List<Funcionario> funcionariosTotais;

    private final List<Funcionario> professores;
    private final List<Funcionario> servidores;
    private final List<Funcionario> terceirizados;

    public RHService() {
        cadastros = new HashMap<>();
        funcionariosTotais = new ArrayList<>();
        professores = new ArrayList<>();
        servidores = new ArrayList<>();
        terceirizados = new ArrayList<>();
    }

    @Override
    public boolean cadastrar(Funcionario funcionario) {
        if(!funcionario.deuCerto())
            return false;
        if(cadastros.containsKey(funcionario.getCpf()))
            return false;
        cadastros.put(funcionario.getCpf(), funcionario);
        funcionariosTotais.add(funcionario);
        if (funcionario.getTipo() == Tipo.PROF)
            professores.add(funcionario);
        else if(funcionario.getTipo() == Tipo.STA)
            servidores.add(funcionario);
        else
            terceirizados.add(funcionario);
        return true;
    }

    @Override
    public boolean remover(String cpf) {
        var funcionario = cadastros.get(cpf);
        if(!cadastros.containsKey(cpf))
            return false;
        if(funcionario.getTipo() == Tipo.PROF)
            professores.remove(funcionario);
        else if(funcionario.getTipo() == Tipo.STA)
            servidores.remove(funcionario);
        else
            terceirizados.remove(funcionario);
        funcionariosTotais.remove(funcionario);
        cadastros.remove(cpf);
        return true;
    }

    
    @Override
    public Funcionario obterFuncionario(String cpf) {
        return cadastros.get(cpf);
    }

    @Override
    public List<Funcionario> getFuncionarios() {
        funcionariosTotais.sort(Comparator.comparing(Funcionario::getNome));
        return funcionariosTotais;
    }

    @Override
    public List<Funcionario> getFuncionariosPorCategoria(Tipo tipo) {
        if (tipo == Tipo.PROF) {
            professores.sort(Comparator.comparing(Funcionario::getNome));
            return professores;
        }
        else if (tipo == Tipo.STA) {
            servidores.sort(Comparator.comparing(Funcionario::getNome));
            return servidores;
        }
        else {
            terceirizados.sort(Comparator.comparing(Funcionario::getNome));
            return terceirizados;
        }
    }

    @Override
    public int getTotalFuncionarios() {
        return funcionariosTotais.size();
    }

    @Override
    public boolean solicitarDiaria(String cpf) {
        var funcionario = cadastros.get(cpf);
        return funcionario.adicionarDiaria();
    }

    @Override
    public boolean partilharLucros(double valor) {
        if(funcionariosTotais.isEmpty())
            return false;
        var valorIndividual = valor / funcionariosTotais.size();
        for(Funcionario funcionario : funcionariosTotais){
            funcionario.lucroPartilhado(valorIndividual);
        }
        return true;
    }

    @Override
    public void iniciarMes() {
        for(Funcionario funcionario : funcionariosTotais)
            funcionario.iniciaMes();
    }

    @Override
    public Double calcularSalarioDoFuncionario(String cpf) {
        if (cadastros.containsKey(cpf))
            return cadastros.get(cpf).getSalario();
        return null;
    }

    @Override
    public double calcularFolhaDePagamento() {
        var soma = 0.0;
        for(Funcionario funcionario : funcionariosTotais)
            soma += funcionario.getSalario();
        return soma;
    }
}