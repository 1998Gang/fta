package cn.ezs.fta.mapper;

import cn.ezs.fta.pojo.Message;

import java.util.List;

/**
 * 信息对应Mapper
 * @author 1998Gang
 */
public interface MessageMapper {
    /**
     * 获取所有的公开信息
     * @return List
     */
    List<Message> getAllMessage();

    /**
     * 通过信息id获取一条信息
     * @param mid 信息id
     * @return Message
     */
    Message getMessageByMid(int mid);

    /**
     * 添加新信息
     * @param message 信息
     * @return boolean
     */
    void addNewMessage(Message message);

    /**
     * 删除某一条信息
     * @param mid 信息id
     * @return boolean
     */
    void deleteMessage(int mid);

    /**
     * 根据关键词查询公告
     * @param keyWord 关键词
     * @return List Message
     */
    List<Message> queryMwssageByKeyWord(String keyWord);

    /**
     * 更新公告
     * @param message 公告
     */
    void updateMessage(Message message);
}
