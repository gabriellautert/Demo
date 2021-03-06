package controllers;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static connection.Conexao.getConnection;
import models.Pessoa;

/**
 *
 * @author Vinicius
 */
public class PessoaController {

    public Pessoa buscar(int id) {

        try {
            Conexao.abreConexao();
            ResultSet rs = null;

            String sql = " SELECT * FROM pessoas WHERE id = " + id;
            rs = Conexao.stmt.executeQuery(sql);

            Pessoa objeto = new Pessoa();

            if (rs.next()) {
                objeto.setId(rs.getInt("id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setEmail(rs.getString("email"));
                objeto.setTelefone(rs.getString("telefone"));

                return objeto;

            }

            return null;

        } catch (SQLException ex) {
            return null;
        }
    }

//    public Conta validarLogin(String conta, String senha) {
//
//        try {
//            Conexao.abreConexao();
//            ResultSet rs = null;
//
//            String sql = " SELECT * FROM contas WHERE conta = '" + conta + "' AND senha = md5('" + senha + "') ";
//            rs = Conexao.stmt.executeQuery(sql);
//
//            Conta objeto = new Conta();
//
//            if (rs.next()) {
//                objeto.setId(rs.getInt("id"));
//                objeto.setConta(rs.getString("conta"));
//                objeto.setNome(rs.getString("nome"));
//                objeto.setCheque_especial(rs.getFloat("cheque_especial"));
//
//                return objeto;
//
//            }
//
//            return null;
//
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
//    public float buscarSaldo(int id_conta) {
//
//        try {
//            Conexao.abreConexao();
//            ResultSet rs = null;
//
//            String sql = " SELECT SUM (CASE operacao WHEN 'D' THEN valor ELSE -valor END) as saldo "
//                    + "FROM movimentos WHERE id_conta = " + id_conta;
//            rs = Conexao.stmt.executeQuery(sql);
//
//            if (rs.next()) {
//
//                return rs.getFloat("saldo");
//            }
//
//            return 0;
//
//        } catch (SQLException ex) {
//            return 0;
//        }
//    }
    public boolean cadastrar(int id, String nome, String email, String telefone) {
        Conexao.abreConexao();
        Connection con = getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(" INSERT INTO pessoas VALUES(DEFAULT, nome, , ?)");
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, telefone);

            stmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            Conexao.closeConnection(con, stmt);

        }

        // this.saldo += valor;
    }

//    public boolean credito(float valor) {
//        if (this.saldo + cheque_especial >= valor) {
//            this.saldo -= valor;
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}
