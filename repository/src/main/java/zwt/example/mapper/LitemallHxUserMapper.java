package zwt.example.mapper;

import zwt.example.entity.LitemallHxUser;

public interface LitemallHxUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LitemallHxUser record);

    int insertSelective(LitemallHxUser record);

    LitemallHxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LitemallHxUser record);

    int updateByPrimaryKey(LitemallHxUser record);
}