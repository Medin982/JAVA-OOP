package WorkingwithAbstraction.Lab.StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public Map<String, Student> getRepo() {
        return this.repo;
    }

    public void add(Student student) {
        repo.putIfAbsent(student.getName(), student);
    }

    public String showStudent(String name) {
        StringBuilder out = new StringBuilder();
        if (repo.containsKey(name)) {
            Student current = repo.get(name);
            out.append(String.format("%s is %s years old.", current.getName(), current.getAge()));
            if (current.getGrade() >= 5.00) {
                out.append(" Excellent student.");
            } else if (current.getGrade() < 5.00 && current.getGrade() >= 3.50) {
               out.append(" Average student.");
            } else {
                out.append(" Very nice person.");
            }
        }
        return out.toString();
    }
}
