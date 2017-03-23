package org.geilove.controller;

/**
 * 提供众筹商品列表获取，但商品获取，增加等功能
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;

@Controller
@RequestMapping("/productInfoController")
public class ProductInfoController {
    @Resource
    private ProductInfoService  productInfoService;

}
