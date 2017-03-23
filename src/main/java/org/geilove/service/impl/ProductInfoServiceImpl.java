package org.geilove.service.impl;

import org.geilove.dao.ProductInfoMapper;
import org.geilove.pojo.ProductInfo;
import org.geilove.service.ProductInfoService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mfhj-dz-001-424 on 17/3/22.
 */
public class ProductInfoServiceImpl implements ProductInfoService {
    @Resource
    private ProductInfoMapper  productInfoMapper;
    public List<ProductInfo> getProductInfoList(Map<String,Objects> map){



    }
}
