package com.example.MyLittleProject.repository;

// A repo is all about perfming crud operations directly to your database

import com.example.MyLittleProject.Gender;
import com.example.MyLittleProject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /*
    // basically CRUD with an ID
    * What I get automatically , Save or update Student save(student)
    * findAll() -> returns a list of students
    * findById(studentNumber) -> find student by id/primary key
    * deleteById(studentNumber)
    * existsById(studentNumber) // checks if a student exists or not
    * */

    //Optional is for none or one value, we not sure it exists
    Optional<Student> findByEmail(String email);

    // u must use syntax,so that the JPA gets exactly wat u want ur crud to be like
    /*
    *• countBy... – Returns a long count of matching rows (e.g., long countByGender(Gender gender)).
    • existsBy... – Returns a boolean (true/false) if a match exists (e.g., boolean existsByEmail(String email)).
    *
    • deleteBy... – Deletes matching rows (e.g., void deleteByLastName(String lastName)).
    * findByAgeBetween etc etc
    *
    *
    *  */

    // this one is basically a safe to use..it returns one,none or many unlike optional
    List<Student> findByAgeBetween(int min , int max) ;

    List<Student> findByGender(Gender gender);

    List <Student> findByDateOfBirthBetween (LocalDate start,LocalDate end);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

}
