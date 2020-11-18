package cn.ezs.fta.service;

import cn.ezs.fta.pojo.Message;

import java.util.List;

/**
 * 信息相关操作类
 * @author 1998Gang
 */
public interface MessageService {
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
    boolean addNewMessage(Message message);

    /**
     * 删除某一条信息
     * @param mid 信息id
     * @return boolean
     */
    boolean deleteMessage(int mid);

    /**
     * 通过关键词获取公告列表
     * @param keyWord 关键词
     * @return List Message
     */
    List<Message> getMessageByKeyWord(String keyWord);

    /**
     * 更新公告
     * @param message 公告
     */
    void updateMessage(Message message);


}
