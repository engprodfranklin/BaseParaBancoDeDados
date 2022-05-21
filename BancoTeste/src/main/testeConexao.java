package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JPanel;

public class testeConexao {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	final private String host = "localhost";
	final private String user = "root";
	final private String passwd = "";
	private String database = "estudo"; // NOME DA DATABASE
	private String tabela1 = "testes"; // NOME DA TABLE

	public void conexao() throws Exception {
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Configure a conex�o com o banco de dados
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

			if (connect != null) {
				System.out.println("Banco de Dados Conectado com sucesso!");
			} else {
				System.out.println("N�o foi poss�vel conectar com o Banco de Dados!");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	// Finalizar (FECHAR) TODAS AS INFORMA��ES
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}

// INSERIR INFORMA��ES NO BANCO DE DADOS
	public void InserirBanco(int id, String nome) {
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);
			// As instru��es permitem emitir consultas SQL ao banco de dados
			statement = connect.createStatement();

			// Inserir DADOS
			statement.executeUpdate("insert into " + tabela1 + " values ('" + id + "','" + nome + "')");

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel inserir dados no Banco \nERRO: " + e.getMessage());
		} finally {
			close();
		}
	}

// ALTERARANDO A ESTRUTURA DA TABELA

// ADICIONANDO UMA COLUNA
	public void adicionarColuna() {
		String tabela = "testes";
		String colunaExistente = "nome";
		// Informa��es da Nova Tabela
		String novaColuna = "cpf";
		String tipo = "varchar(30) not null";

		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

			// As instru��es permitem emitir consultas SQL ao banco de dados
			Statement stmt = connect.createStatement();

			// Adicionando uma coluna
			stmt.execute(
					"alter table " + tabela + " add column " + novaColuna + " " + tipo + " after " + colunaExistente);

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel adcionar uma coluna \nERRO: " + e.getMessage());
		} finally {
			close();
		}

	}

// DELETAR UMA LINHA DA TABELA
	public void deletarUmaLinha() {
		int id = 2; // ID para selecionar a linha
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);
			// As instru��es permitem emitir consultas SQL ao banco de dados com uma
			// vari�vel
			Statement stmt = connect.createStatement();

			// Deletar definido pelo ID
			stmt.executeUpdate("delete from " + tabela1 + " where id = " + id);

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel deletar uma linha da Tabela \nERRO: " + e.getMessage());
		} finally {
			close();
		}
	}

//DELETAR TODOS OS DADOS DA TABELA (TRUNCATE)
	public void deletarDados() {
		String tabelaQueSeraApagada = tabela1;
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);
			// As instru��es permitem emitir consultas SQL ao banco de dados com uma
			// vari�vel
			Statement stmt = connect.createStatement();

			// Deletar todos os Dados da TABELA
			stmt.executeUpdate("truncate " + tabelaQueSeraApagada);

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel deletar os dados da Tabela: " + tabelaQueSeraApagada + "\nERRO: "
					+ e.getMessage());
		} finally {
			close();
		}

	}

//SUBSTITUIR UM VALOR DO BANCO DE DADOS
	public void subtituicaoDado() {
		int id = 2; // ID para selecionar a linha
		String subNome = "Fl�via"; // Nome que eu desejo colocar no lugar
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

			// As instru��es permitem emitir consultas SQL ao banco de dados com uma
			// vari�vel
			Statement stmt = connect.createStatement();

			// Comando Substitui��o de informa��o
			stmt.executeUpdate("UPDATE " + tabela1 + " set nome = '" + subNome + "' where id = '" + id + "'");

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel substituir o dado da Tabela \nERRO: " + e.getMessage());
		} finally {
			close();
		}
	}

// ADICIONAR UMA TABELA (TABLE)
	public void adicionarTabela(String novaTabela) {
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

			// As instru��es permitem emitir consultas SQL ao banco de dados
			Statement stmt = connect.createStatement();

			stmt.execute(
					"create table if not exists " + novaTabela + "(" + "id int not null auto_increment primary key,"
							+ "nome varchar(30) not null," + "descricao text not null," + "ano year not null" + ")");

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel adicionar uma tabela \nERRO: " + e.getMessage());
		} finally {
			close();
		}

	}

// EXCLUS�O DE UMA TABELA (TABLE)
	public void excluirTabela(String apagarTabela) {
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

			// As instru��es permitem emitir consultas SQL ao banco de dados
			Statement stmt = connect.createStatement();

			// Comando para APAGAR TABELA
			stmt.executeUpdate("drop table if exists " + apagarTabela);

		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel excluir a tabela \nERRO: " + e.getMessage());
		} finally {
			close();
		}
	}

// USO DO SELECT * FROM (IMPRESS�O NA TELA)
	public void impressaoSelect(String tabela) {
		try {
			// Conectar no Banco com Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/" + database + "?" + "user=" + user + "&password=" + passwd);

			// As instru��es permitem emitir consultas SQL ao banco de dados
			statement = connect.createStatement();

			// Conjunto de resultados obt�m o resultado da consulta SQL
			resultSet = statement.executeQuery("select * from " + tabela);
			escrevaResultSet(resultSet);
		} catch (Exception e) {
			System.out.println("N�o foi Poss�vel Visualizar os dados do Banco \nERRO: " + e.getMessage());
		} finally {
			close();
		}
	}

	private void escrevaResultSet(ResultSet resultSet) throws SQLException {
		// Como ir� mostrar na tela do console com o select * from
		while (resultSet.next()) {

			String meuID = resultSet.getString("id");
			String meuNome = resultSet.getString("nome");
			String meuCpf = resultSet.getString("cpf");

			System.out.println("id: " + meuID);
			System.out.println("nome: " + meuNome);
			System.out.println("cpf: " + meuCpf);
		}
	}
}
