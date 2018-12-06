package com.lixavier.manage.service;

import com.lixavier.manage.req.LetterDTO;
import com.lixavier.manage.req.LetterQueryDTO;
import com.lixavier.manage.resp.LetterODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 站内信管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface ILetterService {

    /**
     * 
     * 删除站内信
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<Integer> deleteLetter(Long id);
    
    /**
     * 
     * 创建站内信
     * 
     * @author lixavier
     * @version 1.0.0
     * @param letterDTO
     * @return
     */
    Result<Long> createLetter(LetterDTO letterDTO);

    /**
     *
     * 批量创建站内信
     *
     * @author lixavier
     * @version 1.0.0
     * @param letterDTOs
     * @return
     */
    Result<Integer> batchCreate(List<LetterDTO> letterDTOs);

    /**
     * 
     * 更新站内信
     * 
     * @author lixavier
     * @version 1.0.0
     * @param letterDTO
     * @return
     */
    Result<Integer> updateLetter(LetterDTO letterDTO);
    
    /**
     * 
     * 根据ID获取站内信信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<LetterODTO> getLetter(Long id);

    /**
     * 
     * 分页查询站内信信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param letterQueryDTO
     * @return
     */
    Result<QueryResultODTO<LetterODTO>> queryLetter(LetterQueryDTO letterQueryDTO);

    /**
    * The following custom service interface about dubbo
    */
}