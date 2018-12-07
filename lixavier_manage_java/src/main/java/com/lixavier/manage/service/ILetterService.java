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
     * 分页查询站内信信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param letterQueryDTO
     * @return
     */
    Result<QueryResultODTO<LetterODTO>> queryLetter(LetterQueryDTO letterQueryDTO);

}