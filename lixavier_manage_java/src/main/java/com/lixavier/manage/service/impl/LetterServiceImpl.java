package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.LetterBiz;
import com.lixavier.manage.convertor.LetterConvertor;
import com.lixavier.manage.model.Letter;
import com.lixavier.manage.req.LetterDTO;
import com.lixavier.manage.req.LetterQueryDTO;
import com.lixavier.manage.resp.LetterODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.ILetterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.LetterValidate.validateForCreate;
import static com.lixavier.manage.validate.LetterValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 站内信管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("letterService")
public class LetterServiceImpl implements ILetterService {
    private final Logger logger = LoggerFactory.getLogger(LetterServiceImpl.class);

    @Autowired
    private LetterBiz letterBiz;

    /**
     * 
     * 分页查询站内信信息
     * 
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     * @param letterQueryDTO
     * @return
     */
    @Override
    public Result<QueryResultODTO<LetterODTO>> queryLetter(LetterQueryDTO letterQueryDTO) {
        Result<QueryResultODTO<LetterODTO>> result = Result.newSuccess();

        QueryResultODTO<Letter> resultInfo = letterBiz.queryLetter(letterQueryDTO);
        result.setDataMap(LetterConvertor.toQueryResult(resultInfo));

        return result;
    }
}