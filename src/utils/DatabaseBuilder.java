package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public static void main(String[] args) {
        clear();
        build();
        populate();
    }

    private static void clear(){
        System.out.println("Cleaning up...");
        try {
            Files.deleteIfExists(Paths.get("database.db"));}
        catch (IOException e) {e.printStackTrace();}
    }

    private static void build(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("CREATE TABLE curso (\n" +
                    "\tcode INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tnome TEXT,\n" +
                    "\tqtdPeriodos INTEGER,\n" +
                    "\tppc TEXT,\n" +
                    "\tworkload REAL);\n");

            stmt.addBatch("CREATE TABLE disciplina (\n" +
                    "\tcode TEXT NOT NULL,\n" +
                    "\tnome TEXT,\n" +
                    "\tworkload REAL,\n" +
                    "\tmodulo INTEGER,\n" +
                    "\tcurso INTEGER,\n" +
                    "\tPRIMARY KEY (code, curso),\n" +
                    "\tFOREIGN KEY('curso') REFERENCES 'curso'('code')\n);");

            stmt.addBatch("CREATE TABLE aluno (\n" +
                    "\tprontuario TEXT NOT NULL PRIMARY KEY,\n" +
                    "\tnome TEXT,\n" +
                    "\tsemIngresso INTEGER,\n" +
                    "\tsemAtual INTEGER,\n" +
                    "\tanoIngresso INTEGER,\n" +
                    "\tcurso INTEGER,\n" +
                    "\tFOREIGN KEY('curso') REFERENCES 'curso'('code')\n);");

            stmt.addBatch("CREATE TABLE disciplinaDependencia (\n" +
                    "\tdisciplina TEXT,\n" +
                    "\tdependencia TEXT,\n" +
                    "\tPRIMARY KEY (disciplina, dependencia),\n" +
                    "\tFOREIGN KEY('disciplina') REFERENCES 'disciplina'('code'),\n" +
                    "\tFOREIGN KEY('dependencia') REFERENCES 'disciplina'('code')\n);");

            stmt.addBatch("CREATE TABLE disciplinaRestanteAluno (\n" +
                    "\tdisciplina TEXT,\n" +
                    "\taluno TEXT,\n" +
                    "\tatraso INTEGER,\n" +
                    "\tPRIMARY KEY (disciplina, aluno),\n" +
                    "\tFOREIGN KEY('disciplina') REFERENCES 'disciplina'('code'),\n" +
                    "\tFOREIGN KEY('aluno') REFERENCES 'aluno'('prontuario')\n);");
            stmt.executeBatch();

            System.out.println("Database has been created ...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void populate(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt = conn.createStatement()) {
            stmt.addBatch("INSERT INTO curso(code, nome, qtdPeriodos, ppc, workload) VALUES (1, " +
                    "'Técnologia em Análise e Desenvolvimento de Sistemas', 6, '2018', 1200);\n");
            stmt.executeBatch();
            stmt.close();
            System.out.println("Database has been populated ...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
