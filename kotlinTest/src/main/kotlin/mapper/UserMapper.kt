package org.example.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.example.vo.User
@Mapper
interface UserMapper: BaseMapper<User> {

}