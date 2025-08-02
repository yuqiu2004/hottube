package org.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ht.model.entity.Category;
import org.ht.service.CategoryService;
import org.ht.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * 分类Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
implements CategoryService {

}
