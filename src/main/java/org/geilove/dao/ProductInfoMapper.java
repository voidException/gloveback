package org.geilove.dao;

import org.geilove.pojo.ProductInfo;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Long productid);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Long productid);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}