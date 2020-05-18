package com.alexxicatto.acervodelivros;

/**
 * @author Alex Di Vennet Xicatto
 * @codigo 815049
 * @descricao O código possui conceitos que eu não utilizava com muita frequência
 * como expressões regulares (REGEX). As telas de cada menu estão separadas por função
 * para facilitar a leitura do código. Três construtores foram criados para cada classe
 * menos a Emprestimo pois assumi que todos os valores são obrigatorios. 
 * 
 * Somente o método toString() da classe Emprestimo foi utilizada, pois todos os valores
 * estáo sendo usados. As outras classes utilizam um get para trazer suas informações.
 * 
 * Algumas funções foram transferidas da classe Gerenciador para suas respectivas classes.
 * Ao analisar essas funções, achei que o contexto delas cabiam melhor em suas classes.
 * 
 * Apesar de validações serem opcionais, algumas foram feitas mas outras por falta de tempo
 * não deram tempo ex: Data de emprestimo e devolução, ano de publicação.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Gerenciador {

    // Constante que define o titulo do JOptionPane
    public static final String PANEL_TITLE = "Gerenciador Acervo de Livros";
    // Declaração das constantes Regex
    public static final String REGEX_NAME = "[\\p{L} .'-]+$";
    public static final String REGEX_NUMBER = "\\d+";
    public static final String REGEX_WORD = "\\w+";
    public static final String REGEX_ANO_PUBLICACAO = "\\d{4}";
    
    // Variável estatica que funciona como se fosse uma chave primaria, a cada
    // criação de um emprestimo o id é incrementado.
    private static int idEmprestimo = 0;

    public static void main(String[] args) {

        // Criação das listas dinânimcas
        List<Usuario> usuarios = new ArrayList<>();
        List<Livro> livros = new ArrayList<>();
        List<Emprestimo> emprestimos = new ArrayList<>();

        String optionSelected;

        do {
            // A UI será sempre composta por um StringBuilder
            StringBuilder mainSB = new StringBuilder();
            mainSB.append("[1] Usuários\n");
            mainSB.append("[2] Livros\n");
            mainSB.append("[3] Empréstimo\n");
            mainSB.append("[0] Sair");

            optionSelected = JOptionPane.showInputDialog(null, mainSB.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);

            // Menu principal
            switch (optionSelected) {
                case "1":
                    StringBuilder userSB = new StringBuilder();
                    userSB.append("[1] Adicionar usuário\n");
                    userSB.append("[2] Pesquisar usuário pelo nome\n");
                    userSB.append("[3] Listar todos os usuários\n");
                    userSB.append("[4] Voltar\n");
                    userSB.append("[0] Sair");
                    optionSelected = JOptionPane.showInputDialog(null, userSB.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    telaSelecionadaUsuario(optionSelected, usuarios);
                    break;
                case "2":
                    StringBuilder bookSB = new StringBuilder();
                    bookSB.append("[1] Adicionar um novo livro\n");
                    bookSB.append("[2] Exibir dados de um livro a partir do id\n");
                    bookSB.append("[3] Listar todos os livros\n");
                    bookSB.append("[4] Voltar\n");
                    bookSB.append("[0] Sair");
                    optionSelected = JOptionPane.showInputDialog(null, bookSB.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    telaSelecionadaLivros(optionSelected, livros);
                    break;
                case "3":
                    StringBuilder empresitmoSB = new StringBuilder();
                    empresitmoSB.append("[1] Realizar empréstimo\n");
                    empresitmoSB.append("[2] Listar livros emprestados não devolvidos\n");
                    empresitmoSB.append("[3] Listar livros devolvidos\n");
                    empresitmoSB.append("[4] Voltar\n");
                    empresitmoSB.append("[0] Sair");
                    optionSelected = JOptionPane.showInputDialog(null, empresitmoSB.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    telaSelecionadoEmprestimo(optionSelected, usuarios, livros, emprestimos);
                    break;
                case "0":
                    break;
                case "":
                    JOptionPane.showMessageDialog(null, "Selecione uma opção", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    break;
            }
        } while (!optionSelected.equals("0"));
    }

    // Lógica da tela de Emprestimo
    private static void telaSelecionadoEmprestimo(
            String optionSelected,
            List<Usuario> usuarios,
            List<Livro> livros,
            List<Emprestimo> emprestimos) {

        int idUsuario = 0;
        int idLivro = 0;
        LocalDate dataEmprestimo = null;
        LocalDate dataDevolucao = null;
        boolean devolvido = false;

        switch (optionSelected) {

            case "1":

                idUsuario = validarID(idUsuario, "ID Usuario");
                boolean idExiste = Usuario.existeUsuarioID(idUsuario, usuarios);

                if (!idExiste) {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    break;
                }

                idLivro = validarID(idLivro, "ID Livro");
                idExiste = Livro.existeLivroID(idLivro, livros);

                if (!idExiste) {
                    JOptionPane.showMessageDialog(null, "Livro não encontrado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    break;
                }
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String validarDataEmprestimo;
                String validarDataDevolucao;
 
                JOptionPane.showMessageDialog(null, "A data tem que ser no formato dd/MM/yyyy", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                
                do {
                    validarDataEmprestimo = JOptionPane.showInputDialog(null, "Data de emprestimo:", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                } while (validarDataEmprestimo.isEmpty());
                
                do {
                    validarDataDevolucao = JOptionPane.showInputDialog(null, "Data de devolução:", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                } while (validarDataDevolucao.isEmpty());
                
                dataEmprestimo = LocalDate.parse(validarDataEmprestimo, dtf);
                dataDevolucao = LocalDate.parse(validarDataDevolucao, dtf);   
                
                StringBuilder sb = new StringBuilder();
                sb.append("[1] Emprestimo\n");
                sb.append("[2] Devolução");
                
                devolvido = !JOptionPane.showInputDialog(null, sb, PANEL_TITLE, JOptionPane.PLAIN_MESSAGE).equals("1");
                
                criarNovoEmprestimo(idUsuario, idLivro, dataEmprestimo, dataDevolucao, devolvido, emprestimos);
                break;
            case "2":
                
                List<Emprestimo> livrosNaoDevolvidos = new ArrayList<>();
                
                for (Emprestimo emprestimo : emprestimos) {
                    if (!emprestimo.isDevolvido()) {
                        livrosNaoDevolvidos.add(emprestimo);
                    }
                }
                
                if (!livrosNaoDevolvidos.isEmpty()) {
                    for (Emprestimo emprestimo : livrosNaoDevolvidos) {
                        JOptionPane.showMessageDialog(null, emprestimo.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum livro emprestado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                }
                break;
            case "3":
                
                List<Emprestimo> livrosDevolvidos = new ArrayList<>();
                
                for (Emprestimo emprestimo : emprestimos) {
                    if (emprestimo.isDevolvido()) {
                        livrosDevolvidos.add(emprestimo);
                    }
                }
                
                if (!livrosDevolvidos.isEmpty()) {
                    for (Emprestimo emprestimo : livrosDevolvidos) {
                        JOptionPane.showMessageDialog(null, emprestimo.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    } 
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum livro devolvido", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                }
                break;
        }
    }

    // Lógica da tela de Livros
    private static void telaSelecionadaLivros(String optionSelected, List<Livro> livros) {

        int id = 0;
        int anoPublicacao = 0;
        String idString = "";
        String anoPublicacaoString = "";
        String titulo = "";
        String autor = "";
        String edicao = "";
        String editora = "";
        String cidade = "";

        switch (optionSelected) {

            case "1":
                idString = validarParametrosLivro(idString, "ID:", REGEX_NUMBER);
                id = Integer.valueOf(idString);

                titulo = validarParametrosLivro(titulo, "Titulo:", REGEX_WORD);
                autor = validarParametrosLivro(autor, "Autor:", REGEX_NAME);

                edicao = JOptionPane.showInputDialog(null, "Edição:", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (!edicao.isEmpty()) {
                    do {
                        editora = JOptionPane.showInputDialog(null, "Editora", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                    } while (editora.isEmpty());
                }

                cidade = JOptionPane.showInputDialog(null, "Cidade:", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (!cidade.isEmpty()) {
                    do {

                        anoPublicacaoString = JOptionPane.showInputDialog(null, "Ano de Publicação", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                        if (!anoPublicacaoString.matches(REGEX_ANO_PUBLICACAO)) {
                            JOptionPane.showMessageDialog(null, "Ano de publicação inválido: utilize somente 4 digitos numéricos", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                        }
                    } while (!anoPublicacaoString.matches(REGEX_ANO_PUBLICACAO));

                    anoPublicacao = Integer.valueOf(anoPublicacaoString);
                }
                criarNovoLivro(id, anoPublicacao, titulo, autor, edicao, editora, cidade, livros);
                break;
            case "2":

                if (livros.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum livro adicionado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                } else {
                    String result = "";
                    do {
                        result = JOptionPane.showInputDialog(null, "ID:", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                        if (!result.matches(REGEX_NUMBER)) {
                            JOptionPane.showMessageDialog(null, "ID inválido: utilize somente números", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                        }
                    } while (!result.matches(REGEX_NUMBER));

                    List<Livro> bookById = Livro.getLivroPeloID(Integer.valueOf(result), livros);
                    construirListaLivros(bookById);
                }
                break;
            case "3":
                if (livros.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum livro adicionado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                } else {
                    construirListaLivros(livros);
                }
            case "4":
                break;
        }
    }

    // // Lógica da tela de Usuário
    private static void telaSelecionadaUsuario(String optionSelected, List<Usuario> usuarios) {

        int id = 0;
        String nome = "";
        String email = "";
        String senha = "";

        switch (optionSelected) {
            case "1":
                id = Usuario.validarUsuarioID(id, "ID");
                nome = Usuario.validarNomeUsuario(nome);

                email = JOptionPane.showInputDialog(null, "Email", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);

                if (!email.isEmpty()) {
                    do {

                        senha = JOptionPane.showInputDialog(null, "Senha", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                        if (senha.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Senha inválida: digite uma senha", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                        }

                    } while (senha.isEmpty());
                }

                criarNovoUsuario(id, nome, email, senha, usuarios);
                break;
            case "2":
                nome = JOptionPane.showInputDialog(null, "Pesquisar pelo nome:", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                nome = nome.toLowerCase();
                List<Usuario> usersByName = Usuario.filtrarUsuarioPorNome(nome, usuarios);

                if (usersByName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                } else {
                    construirListaUsuarios(usersByName);
                }

                break;
            case "3":
                if (usuarios.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Nenhum usuário adicionado", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                } else {

                    construirListaUsuarios(usuarios);
                }

                break;
            case "4":
                break;
        }
    }
    
    // Lógica de validação dos campos obrigatórios para inserir um novo livro
    private static String validarParametrosLivro(String value, String message, String regex) {

        do {
            value = JOptionPane.showInputDialog(null, message, PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            if (value.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo obrigatório", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            } else if (regex.equals(REGEX_NUMBER)) {
                if (!value.matches(REGEX_NUMBER)) {
                    JOptionPane.showMessageDialog(null, "Digite apenas números", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
                }
            }
        } while (!value.matches(regex));

        return value;
    }
    
    // Função que faz a validação do ID, que permite apenas números e não pode estar vazio
    private static int validarID(int id, String mensagem) {
        String idString;

        do {

            idString = JOptionPane.showInputDialog(null, mensagem, PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            if (idString.matches(REGEX_NUMBER)) {
                id = Integer.valueOf(idString);
            } else if (idString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID inválido: campo vazio", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ID inválido: utilize somente números", PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            }
        } while (!idString.matches(REGEX_NUMBER));

        return id;
    }

    // UI do resultado da lista de livros
    private static void construirListaLivros(List<Livro> livros) {

        StringBuilder sb = new StringBuilder();

        for (Livro livro : livros) {
            sb.append("ID: ");
            sb.append(livro.getId());
            sb.append("\nTitulo: ");
            sb.append(livro.getTitulo());
            sb.append("\nAutor: ");
            sb.append(livro.getAutor());

            if (livro.getEdicao() != null) {
                sb.append("\nEdição: ");
                sb.append(livro.getEdicao());
                sb.append("\nEditora: ");
                sb.append(livro.getEditora());
            }

            if (livro.getCidade() != null) {
                sb.append("\nCidade: ");
                sb.append(livro.getCidade());
                sb.append("\nAno de Publicação: ");
                sb.append(livro.getAnoPublicacao());
            }

            sb.append("\n\n");
        }
        
        JOptionPane.showMessageDialog(null, sb.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
    }

    // UI do resultado da lista de usuarios
    private static void construirListaUsuarios(List<Usuario> usuarios) {

        StringBuilder sb = new StringBuilder();

        for (Usuario user : usuarios) {

            if (user.getId() != 0) {
                sb.append("ID: ");
                sb.append(String.valueOf(user.getId()));
                sb.append("\n");
            }

            sb.append("Nome: ");
            sb.append(user.getNome());

            if (user.getEmail() != null) {

                sb.append("\nEmail: ");
                sb.append(user.getEmail());
                sb.append("\nSenha: ");
                sb.append(user.getSenha());
            }

            sb.append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
    }

    /*  As funções abaixo serverem para construir um novo Livro, Usuario e Emprestimo.
    *   A construção do livro e do usuário possui valores não obrigatórios
    *   que, dependendo dos dados recebidos, a contrução da classe é diferente.
    *   
    *   Se um determinado valor for vazio a contrução muda.
    */
    
    private static void criarNovoEmprestimo(
            int idUsuario, 
            int idLivro,
            LocalDate dataEmprestimo,
            LocalDate dataDevolucao,
            boolean devolvido,
            List<Emprestimo> emprestimos) {
        
        idEmprestimo++;
        emprestimos.add(new Emprestimo(idEmprestimo, idUsuario, idLivro, dataEmprestimo, dataDevolucao, devolvido));
    }
    
    private static void criarNovoLivro(
            int id,
            int anoPublicacao,
            String titulo,
            String autor,
            String edicao,
            String editora,
            String cidade,
            List<Livro> livros) {

        if (id != 0
                && anoPublicacao != 0
                && !titulo.isEmpty()
                && !autor.isEmpty()
                && !edicao.isEmpty()
                && !editora.isEmpty()
                && !cidade.isEmpty()) {

            livros.add(new Livro(id, anoPublicacao, titulo, autor, edicao, editora, cidade));

        } else if (id != 0
                && !titulo.isEmpty()
                && !autor.isEmpty()
                && !edicao.isEmpty()
                && !editora.isEmpty()) {

            livros.add(new Livro(id, titulo, autor, edicao, editora));

        } else if (id != 0
                && anoPublicacao != 0
                && !titulo.isEmpty()
                && !autor.isEmpty()
                && !cidade.isEmpty()) {
            
            livros.add(new Livro(id, anoPublicacao, titulo, autor, cidade));
            
        } else {
            
            livros.add(new Livro(id, titulo, autor));
        }
    }

    private static void criarNovoUsuario(
            int id,
            String nome,
            String email,
            String senha,
            List<Usuario> usuarios) {

        if (id != 0 && !nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {

            usuarios.add(new Usuario(id, nome, email, senha));
        } else if (id != 0 && !nome.isEmpty()) {

            usuarios.add(new Usuario(id, nome));
        }
    }
}
