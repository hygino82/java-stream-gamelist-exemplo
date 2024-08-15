package br.dev.hygino.solid;

interface IDBSalvar {
    void salvarDados();
}

interface ILogin {
    void logarUsuario(Usuario usuario);
}

record Usuario(
        String username,
        String password) {
}

class LogarYoutube implements ILogin {

    @Override
    public void logarUsuario(Usuario usuario) {
        System.out.println("Usuario logado no Youtube com o usuario");
        System.out.println(usuario);
    }
}

class SalvarDadosMySql implements IDBSalvar {

    @Override
    public void salvarDados() {
        System.out.println("Dados salvos no MySql");
    }
}

public class ResponsabilidadeUnica {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("jupirinha", "senhaDaJupirinha");
        IDBSalvar salvar = new SalvarDadosMySql();
        ILogin logar = new LogarYoutube();

        ComposicaoClasses composicaoClasses = new ComposicaoClasses(salvar, logar, usuario);
        composicaoClasses.logar();
        composicaoClasses.salvarDados();
    }
}

class ComposicaoClasses {
    private final IDBSalvar salvar;
    private final ILogin logar;
    private final Usuario usuario;

    public ComposicaoClasses(IDBSalvar salvar, ILogin logar, Usuario usuario) {
        this.salvar = salvar;
        this.logar = logar;
        this.usuario = usuario;
    }

    public void salvarDados() {
        this.salvar.salvarDados();
    }

    public void logar() {
        this.logar.logarUsuario(usuario);
    }
}
