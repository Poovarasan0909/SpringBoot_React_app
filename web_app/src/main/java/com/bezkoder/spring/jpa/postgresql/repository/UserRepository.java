package com.bezkoder.spring.jpa.postgresql.repository;

import com.bezkoder.spring.jpa.postgresql.model.User;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQueries;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = ?1 and u.age = ?2")
    List<User> findNameAndAge(String name, String age);

    @Query("select u from User u where u.name = ?1")
    List<User> getCollection(String name);

    @Query("select u from User u")
    List<User> getAllUser();

//    @NamedNativeQueries(  "SELECT json_build_object(" +
//                            " 'schema_name', table_schema," +
//                            "    'table_name', table_name," +
//                            "    'column_name', column_name," +
//                            "    'data_type', data_type" +
//                            ") AS json_obj" +
//                            "FROM information_schema.columns" +
//                                  "WHERE table_schema = 'public'")
//    List<User> getDatabase();


    User findById(long k);


//    SELECT json_build_object(" +
//                                     "    'schema_name', table_schema," +
//                                     "    'table_name', table_name," +
//                                     "    'column_name', column_name," +
//                                     "    'data_type', data_type" +
//                                     ") AS json_obj" +
//                                     "FROM information_schema.columns" +
//                                     "WHERE table_schema = 'public'

    @Query("SELECT u FROM User u WHERE u.id = :id AND DATE(u.createdAt) = :date")
    User findByIdAndCreatedAt(Long id, Date date);

//    User findByCreatedAtMonth(int mon);
}