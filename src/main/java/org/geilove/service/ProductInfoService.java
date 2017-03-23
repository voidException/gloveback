package org.geilove.service;

import org.geilove.pojo.ProductInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by mfhj-dz-001-424 on 17/3/22.
 */
public interface ProductInfoService {

     public List<ProductInfo> getProductInfoList(Map<String,Objects>  map);
}
