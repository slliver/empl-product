package com.zhongwang.support.base.service;

import com.zhongwang.support.base.entity.BaseDomain;
import com.zhongwang.support.base.mapper.ZWBaseMapper;
import com.zhongwang.support.constants.Constants;
import com.zhongwang.support.exception.ZWServiceException;
import com.zhongwang.support.util.ShiroUtil;
import com.zhongwang.support.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * @Description: Web服务基类
 * @author: slliver
 * @date: 2019/9/3 16:04
 * @version: 1.0
 */
public class WebBaseService<T extends BaseDomain> extends BaseService {

    @Autowired
    protected ZWBaseMapper<T> baseMapper;

    public int insert(T obj) {
        if (obj == null) {
            logger.error("插入的实体为空！");
            return 0;
        } else if (obj instanceof BaseDomain) {
            if (StringUtil.isEmpty(obj.getPkid())) {
                obj.setPkid(UUIDUtil.get32UUID());
            }

            if (null == obj.getFlagDelete()) {
                obj.setFlagDelete((short) 0);
            }

            Date makeTime = new Date();
            if (null == obj.getMakeTime()) {
                obj.setMakeTime(makeTime);
            }

            if (StringUtil.isEmpty(obj.getMakeUser())) {
                obj.setMakeUser(ShiroUtil.getUserId());
            }

            if (null == obj.getModifyTime()) {
                obj.setModifyTime(makeTime);
            }

            if (StringUtil.isEmpty(obj.getModifyUser())) {
                obj.setModifyUser(ShiroUtil.getUserId());
            }

            if (obj.getFlagVersion() == null) {
                obj.setFlagVersion(Long.valueOf(System.currentTimeMillis()));
            }

            if (obj.getFlagSort() == null) {
                obj.setFlagSort((short) 999);
            }
            if (obj.getCompPkid() == null) {
                obj.setCompPkid(ShiroUtil.getCompPkid());
            }
            return this.baseMapper.insert(obj);
        } else {
            throw new RuntimeException("插入失败，此类未继承BaseDomain类！");
        }
    }

    public int update(T obj) {
        if (obj == null) {
            logger.error("修改的实体为空！");
            return 0;
        } else {
            if (null == obj.getModifyTime()) {
                obj.setModifyTime(new Date());
            }

            if (StringUtil.isEmpty(obj.getModifyUser())) {
                obj.setModifyUser(ShiroUtil.getUserId());
            }

            int resCount = this.baseMapper.updateByPrimaryKeySelective(obj);
            if (resCount > 0) {
                return resCount;
            } else {
                throw new RuntimeException("修改0条数据，修改失败！请检查数据是否已被其他人修改！");
            }
        }
    }


    /**
     * 按照主键进行选择
     *
     * @param pkid
     * @return
     */
    public T selectByPkid(String pkid) {
        return baseMapper.selectByPrimaryKey(pkid);
    }

    /**
     * 获得对象列表
     *
     * @param o 对象
     * @return List
     */
    public List<T> select(T o) {
        return baseMapper.select(o);
    }

    /**
     * 获得对象列表
     *
     * @param example 对象
     * @return List
     */
    public List<T> selectByExample(Example example) {
        if (example == null) {
            throw new ZWServiceException("用于查询的数据对象参数为空");
        }
        List<T> selectedRes = baseMapper.selectByExample(example);
        return selectedRes;
    }

    /**
     * 物理删除一条记录,根据实体属性作为条件进行删除，查询条件使用等号
     */
    public int deleteLogically(T obj) {
        if (obj == null) {
            throw new ZWServiceException("要删除的数据为空");
        }
        obj.setFlagDelete(Constants.SYS_COMMON_STATUS.DELETE);

        if (null == obj.getModifyTime()) {
            obj.setModifyTime(new Date());
        }
        if (StringUtil.isEmpty(obj.getModifyUser())) {
            obj.setModifyUser(ShiroUtil.getCurrentUserPkid());
        }
        return baseMapper.updateByPrimaryKeySelective(obj);
    }

    /**
     * 逻辑删除一个对象,非物理删除，只是改变对象的标识位
     */
    public int deleteLogically(String pkid) {
        return baseMapper.deleteLogically(pkid, ShiroUtil.getCurrentUserPkid());
    }

    /**
     * 物理删除一个对象
     */
    public int deletePhysically(T obj) {
        if (obj == null) {
            throw new ZWServiceException("要删除的数据对象为空");
        }
        return baseMapper.delete(obj);
    }


    public int deletePhysically(String pkid) {
        return baseMapper.deleteByPrimaryKey(pkid);
    }

    /**
     * 批量逻辑删除多个对象
     *
     * @param objList (主键)数组
     */
    public int deleteBatchLogically(List<T> objList) {
        //批量删除时如果list为空则忽略
        if (objList != null && objList.size() != 0) {
            for (T obj : objList) {
                if (obj != null) {
                    if (null == obj.getModifyTime()) {
                        obj.setModifyTime(new Date());
                    }
                    if (StringUtil.isEmpty(obj.getModifyUser())) {
                        obj.setModifyUser(ShiroUtil.getCurrentUserPkid());
                    }
                }
            }
            return baseMapper.deleteBatchLogically(objList);
        }
        return 0;
    }

    /**
     * 批量逻辑删除多个对象
     *
     * @param objList (主键)数组
     */
    public int deleteBatchLogicallyByIDs(List<String> objList) {
        //批量删除时如果list为空则忽略
        if (objList != null && objList.size() != 0) {
            return baseMapper.deleteBatchLogicallyByIds(objList, ShiroUtil.getCurrentUserPkid());
        }
        return 0;
    }

}
