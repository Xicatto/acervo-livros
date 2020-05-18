package com.alexxicatto.acervodelivros;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// Classe que permite a criação de um usuário
public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Juntei o email e senha como campos obrigatórios
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static String validarNomeUsuario(String nome) {

        do {

            nome = JOptionPane.showInputDialog(null, "Nome", Gerenciador.PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esse campo é obrigatório", Gerenciador.PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            } else if (!nome.matches(Gerenciador.REGEX_NAME)) {
                JOptionPane.showMessageDialog(null, "Nome inválido: utilize somente letras", Gerenciador.PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            }
        } while (!nome.matches(Gerenciador.REGEX_NAME));

        return nome;
    }
    
    public static int validarUsuarioID(int id, String mensagem) {
        String idString;

        do {

            idString = JOptionPane.showInputDialog(null, mensagem, Gerenciador.PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            if (idString.matches(Gerenciador.REGEX_NUMBER)) {
                id = Integer.valueOf(idString);
            } else if (idString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID inválido: campo obrigatório", Gerenciador.PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "ID inválido: utilize somente números", Gerenciador.PANEL_TITLE, JOptionPane.PLAIN_MESSAGE);
            }
        } while (!idString.matches(Gerenciador.REGEX_NUMBER));

        return id;
    }
    
    public static boolean existeUsuarioID(int id, List<Usuario> usuarios) {

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id) {
                return true;
            }
        }

        return false;
    }
    
    public static List<Usuario> filtrarUsuarioPorNome(String nome, List<Usuario> usuarios) {

        List<Usuario> usersByName = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase().contains(nome)) {
                usersByName.add(usuario);
            }
        }

        return usersByName;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + '}';
    }
}
