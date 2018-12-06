package com.lixavier.manage.async;

import com.lixavier.manage.biz.LetterBiz;
import com.lixavier.manage.constant.LetterStatusEnum;
import com.lixavier.manage.constant.LetterTypeEnum;
import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.model.Letter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service("letterAsync")
public class LetterAsync {
    private final Logger logger = LoggerFactory.getLogger(LetterAsync.class);

    @Autowired
    private LetterBiz letterBiz;


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Async
    public Boolean createGiftCardLetter(GiftCard card) {
        Letter letter = new Letter();
        letter.setTitle("礼品卡激活成功");
        letter.setContent("您的礼品卡" + card.getId() + "已激活成功，金额" + card.getPrice() + "元，快去使用吧！");
        letter.setType(LetterTypeEnum.ACCOUNT.getValue());
        letter.setCommonStatus(LetterStatusEnum.SENDED.getValue());
        letter.setSendResult("\"total\":1,\"success\":1,\"error\":0");
        letter.setIsDeleted(false);

        letterBiz.createLetter(letter, card.getUserId());
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Async
    public Boolean createRefundLetter(Long userId, BigDecimal amount) {
        Letter letter = new Letter();
        letter.setTitle("退款成功");
        letter.setContent("您的退款已成功，金额" + amount + "元。");
        letter.setType(LetterTypeEnum.ACCOUNT.getValue());
        letter.setCommonStatus(LetterStatusEnum.SENDED.getValue());
        letter.setSendResult("\"total\":1,\"success\":1,\"error\":0");

        letterBiz.createLetter(letter, userId);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Async
    public Boolean createRefusedRefundLetter(Long userId, String message) {
        Letter letter = new Letter();
        letter.setTitle("退款驳回");
        letter.setContent("您的退款申请已被驳回，原因：" + message);
        letter.setType(LetterTypeEnum.ACCOUNT.getValue());
        letter.setCommonStatus(LetterStatusEnum.SENDED.getValue());
        letter.setSendResult("\"total\":1,\"success\":1,\"error\":0");

        letterBiz.createLetter(letter, userId);
        return true;
    }


}