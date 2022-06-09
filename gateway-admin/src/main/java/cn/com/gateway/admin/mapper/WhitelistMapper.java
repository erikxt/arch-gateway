package cn.com.gateway.admin.mapper;

import cn.com.gateway.admin.domain.WhitelistDO;
import org.springframework.stereotype.Repository;

@Repository
public interface WhitelistMapper {

    int insert(WhitelistDO whitelistDO);
}
