package com.zidi.banking_application.mapper;

import com.zidi.banking_application.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM users WHERE email = #{email}")
    boolean existsByEmail(@Param("email") String email);

    @Insert("INSERT INTO users (first_name, last_name, other_name, gender, email, address, state_of_origin, account_number, account_balance, phone_number, alternative_phone_number, status, created_at, modified_at) " +
            "VALUES (#{firstName}, #{lastName}, #{otherName}, #{gender}, #{email}, #{address}, #{stateOfOrigin}, #{accountNumber}, #{accountBalance}, #{phoneNumber}, #{alternativePhoneNumber}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);
}
