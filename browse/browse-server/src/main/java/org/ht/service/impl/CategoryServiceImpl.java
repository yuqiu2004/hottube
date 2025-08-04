package org.ht.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ht.mapper.CategoryMapper;
import org.ht.model.entity.Category;
import org.ht.model.response.CategoryQueryResponse;
import org.ht.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类Service实现类
 * 
 * @author HotTube
 * @since 2024-01-01
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public CategoryQueryResponse getAllCategories() {
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<>());
        return CategoryQueryResponse.builder().categories(categories).build();
    }
}
