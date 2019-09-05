package com.zhongwang.support.base.mapper;

import com.zhongwang.support.base.mapper.provider.ZWSpecialProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZWBatchMapper<T> {

    @DeleteProvider(type = ZWSpecialProvider.class, method = "dynamicSQL")
    int deleteBatchLogically(List<T> list);

    @DeleteProvider(type = ZWSpecialProvider.class, method = "dynamicSQL")
    int deleteBatchLogicallyByIds(@Param("list")List<String> list, @Param("modifyUser")String modifyUser);
}
