package com.lixavier.manage.convertor;

import com.lixavier.manage.model.Category;
import com.lixavier.manage.req.CategoryDTO;
import com.lixavier.manage.resp.CategoryODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层类目转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class CategoryConvertor {

    private static final BeanCopier beanCopierForCategoryODTO = BeanCopier.create(Category.class, CategoryODTO.class, false);
    private static final BeanCopier beanCopierForCategory = BeanCopier.create(CategoryDTO.class, Category.class, false);

    public static CategoryODTO toCategoryODTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryODTO categoryODTO = new CategoryODTO();
        beanCopierForCategoryODTO.copy(category, categoryODTO, null);
        categoryODTO.setChildren(toCategoryODTOList(category.getChildren()));
        return categoryODTO;
    }

    public static Category toCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        beanCopierForCategory.copy(categoryDTO, category, null);
        return category;
    }

    public static List<CategoryODTO> toCategoryODTOList(List<Category> categoryList) {
        if (categoryList == null || categoryList.isEmpty()) {
            return null;
        }
        List<CategoryODTO> categoryInfoList = new ArrayList<CategoryODTO>(categoryList.size());
        for (Category category : categoryList) {
            categoryInfoList.add(toCategoryODTO(category));
        }
        return categoryInfoList;
    }

    public static List<Category> toCategoryList(List<CategoryDTO> categoryDTOList) {
        if (categoryDTOList == null || categoryDTOList.isEmpty()) {
            return null;
        }
        List<Category> categoryList = new ArrayList<Category>(categoryDTOList.size());
        for (CategoryDTO categoryDTO : categoryDTOList) {
            categoryList.add(toCategory(categoryDTO));
        }
        return categoryList;
    }

    public static QueryResultODTO<CategoryODTO> toQueryResult(QueryResultODTO<Category> queryResult) {
        QueryResultODTO<CategoryODTO> queryResultInfo = new QueryResultODTO<CategoryODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toCategoryODTOList(queryResult.getRecords()));
        return queryResultInfo;
    }
}