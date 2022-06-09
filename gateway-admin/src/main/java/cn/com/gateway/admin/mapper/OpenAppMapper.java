package cn.com.gateway.admin.mapper;

import cn.com.gateway.admin.domain.OpenAppDO;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenAppMapper {

    int insert(OpenAppDO openAppDO);
}
