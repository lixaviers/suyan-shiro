package com.lixavier.manage.convertor;

import com.lixavier.manage.model.Letter;
import com.lixavier.manage.req.LetterDTO;
import com.lixavier.manage.resp.LetterODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层站内信转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class LetterConvertor {

    private static final BeanCopier beanCopierForLetterODTO = BeanCopier.create(Letter.class, LetterODTO.class, false);
    private static final BeanCopier beanCopierForLetter = BeanCopier.create(LetterDTO.class, Letter.class, false);

    public static LetterODTO toLetterODTO(Letter letter) {
        if (letter == null) {
            return null;
        }
        LetterODTO letterODTO = new LetterODTO();
        beanCopierForLetterODTO.copy(letter, letterODTO, null);
        return letterODTO;
    }

    public static Letter toLetter(LetterDTO letterDTO) {
        Letter letter = new Letter();
        beanCopierForLetter.copy(letterDTO, letter, null);
        return letter;
    }

    public static List<LetterODTO> toLetterODTOList(List<Letter> letterList) {
        if (letterList == null || letterList.isEmpty()) {
            return null;
        }
        List<LetterODTO> letterInfoList = new ArrayList<LetterODTO>(letterList.size());
        for (Letter letter : letterList) {
            letterInfoList.add(toLetterODTO(letter));
        }
        return letterInfoList;
    }

    public static List<Letter> toLetterList(List<LetterDTO> letterDTOList) {
        if (letterDTOList == null || letterDTOList.isEmpty()) {
            return null;
        }
        List<Letter> letterList = new ArrayList<Letter>(letterDTOList.size());
        for (LetterDTO letterDTO : letterDTOList) {
            letterList.add(toLetter(letterDTO));
        }
        return letterList;
    }

    public static QueryResultODTO<LetterODTO> toQueryResult(QueryResultODTO<Letter> queryResult) {
        QueryResultODTO<LetterODTO> queryResultInfo = new QueryResultODTO<LetterODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toLetterODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}