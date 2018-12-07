package com.lixavier.manage.validate;

import com.lixavier.manage.model.GoodsComment;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import org.apache.commons.collections4.CollectionUtils;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层商品评论验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public class GoodsCommentValidate {

    public static boolean validateForCreate(GoodsComment goodsComment, Result<?> message) {

        if (isNull(message, GoodsComment.ORDER_GOODS_ID, goodsComment.getOrderGoodsId())) {
            return false;
        }

        if (isMoreThan(message, GoodsComment.CONTENT, goodsComment.getContent(), 500)) {
            return false;
        }

        if (CollectionUtils.isNotEmpty(goodsComment.getUrls()) && goodsComment.getUrls().size() > 5) {
            message.setErrorCode(ResultCode.COMMON_MESSAGE, "上传文件个数超出限制");
            return false;
        }
        return true;
    }

    public static boolean validateForReply(GoodsComment goodsComment, Result<?> message) {
        if (isNull(message, GoodsComment.ID, goodsComment.getId())) {
            return false;
        }

        if (isMoreThan(message, GoodsComment.REPLY_CONTENT, goodsComment.getReplyContent(), 500)) {
            return false;
        }
        return true;
    }
}