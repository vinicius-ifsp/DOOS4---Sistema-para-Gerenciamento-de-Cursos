package utils;


import models.Discipline;
import models.Student;
import models.StudentRemainingDiscipline;

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
                int semIngresso = Integer.parseInt(data[6]);
                int anoIngresso = Integer.parseInt(data[3]);
                String prontuario = data[1].substring(2, 9);
                String nome = data[2].toUpperCase();
                students.add(new Student(semIngresso, anoIngresso,
                        prontuario, nome, null));
            }
            return students;
        } catch (IOException e) {
            throw (e);
        }
    }

    public static HashMap<String, Discipline> loadDisciplines(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            HashMap<String, Discipline> disciplines = new HashMap<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                String[] data = strCurrentLine.split(";");
                String code = data[0];
                String name = data[1];
                Double workload = Double.parseDouble(data[2]);
                int module = Integer.parseInt(data[3]);
                Discipline d = new Discipline(code, name, workload, module);
                if (data.length == 5)
                    for (String dependencyCode : data[4].split(","))
                        d.addDependency(disciplines.get(dependencyCode));
                disciplines.put(code, d);
            }
            return disciplines;
        } catch (IOException e) {
            throw (e);
        }
    }

    public static List<StudentRemainingDiscipline> loadStudentRemainingDisciplines(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<StudentRemainingDiscipline> studentRemainingDisciplines = new ArrayList<>();
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                String[] data = strCurrentLine.split(";");
                String prontuario = data[1].substring(2, 9);
                String codigo = data[3].substring(11, 16);
//                int modulo = Integer.parseInt(data[4]);
//                String nome = data[5].toUpperCase();
                studentRemainingDisciplines.add(new StudentRemainingDiscipline(new Discipline(codigo), new Student(prontuario)));
            }
            return studentRemainingDisciplines;
        } catch (IOException e) {
            throw (e);
        }
    }
}