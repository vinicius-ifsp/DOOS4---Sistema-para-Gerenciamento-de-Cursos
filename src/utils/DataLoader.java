package utils;


import models.Discipline;
import models.Student;
import models.StudentClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataLoader {

    public static List<Student> loadStudents(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<Student> students = new ArrayList<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                String[] data = strCurrentLine.split(";");
                students.add(new Student(Integer.parseInt(data[6]), Integer.parseInt(data[3]),
                        data[1].substring(2, 9), data[2].toUpperCase(), null));
            }
            return students;
        } catch (IOException e) {
            throw (e);
        }
    }

    public static HashMap<String, Discipline> loadDisciplines(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Vinicius\\Documents\\DOOS4\\DOOS4-Sistema-para-Gerenciamento-de-Cursos\\disciplinas.csv"))) {
            HashMap<String, Discipline> disciplines = new HashMap<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                String[] data = strCurrentLine.split(";");
                String code = data[0];
                String name = data[1];
                Double workload = Double.parseDouble(data[2]);
                Discipline d = new Discipline(code, name, workload);
                if (data.length == 4)
                    for (String dependencyCode : data[3].split(","))
                        d.addDependency(disciplines.get(dependencyCode));
                disciplines.put(code, d);
            }
            return disciplines;
        } catch (IOException e) {
            throw (e);
        }
    }

    public static List<StudentClass> loadStudentClasses(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Vinicius\\Desktop\\FakeRelatorio.csv"))) {
            List<StudentClass> studentClasses = new ArrayList<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                String[] data = strCurrentLine.split(";");
                String prontuario = data[1].substring(2, 9);
                String codigo = data[3].substring(11, 16);
                int modulo = Integer.parseInt(data[4]);
                String nome = data[5].toUpperCase();
                studentClasses.add(new StudentClass(nome, codigo, prontuario, modulo));
            }
            return studentClasses;
        } catch (IOException e) {
            throw (e);
        }
    }
}