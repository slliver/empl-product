package com.zhongwang.support.base.mapper;

import com.zhongwang.support.base.mapper.provider.ZWSpecialProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.Mapper;

/**
 * 我们自己的基类Mapper
 * @param <T>
 */
public interface ZWBaseMapper<T> extends Mapper<T>, ZWBatchMapper<T> {

    @DeleteProvider(type = ZWSpecialProvider.class, method = "dynamicSQL")
    int deleteLogically(@Param("pkid")String pkid, @Param("modifyUser")String modifyUser);

    @UpdateProvider(type = ZWSpecialProvider.class, method = "dynamicSQL")
    int updateAuditInfo(T o);

}
