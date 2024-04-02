package com.example.hellospringboot.mapper;


import com.example.hellospringboot.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> list();
}
