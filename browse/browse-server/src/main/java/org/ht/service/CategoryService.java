package org.ht.service;

import org.ht.model.response.CategoryQueryResponse;

/**
 * 分类Service接口
 * 
 * @author HotTube
 * @since 2024-01-01
 */
public interface CategoryService {

    CategoryQueryResponse getAllCategories();
}
