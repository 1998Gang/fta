package cn.ezs.fta.service.impl;

import cn.ezs.fta.mapper.MessageMapper;
import cn.ezs.fta.pojo.Message;
import cn.ezs.fta.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <h3>fta</h3>
 * <p></p>
 *
 * @author : 1998Gang
 * @date : 2020-08-10 17:55
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired(required = false)
    private MessageMapper messageMapper;


    /**
     * 获取所有的公开信息
     *
     * @return List
     */
    @Override
    public List<Message> getAllMessage() {
        return messageMapper.getAllMessage();
    }

    /**
     * 通过信息id获取一条信息
     *
     * @param mid 信息id
     * @return Message
     */
    @Override
    public Message getMessageByMid(int mid) {
        return messageMapper.getMessageByMid(mid);
    }

    /**
     * 添加新信息
     *
     * @param message 信息
     * @return boolean
     */
    @Override
    public boolean addNewMessage(Message message) {
       try {
           message.setDate(new Date());
           messageMapper.addNewMessage(message);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    /**
     * 删除某一条信息
     *
     * @param mid 信息id
     * @return boolean
     */
    @Override
    public boolean deleteMessage(int mid) {

        try {
            messageMapper.deleteMessage(mid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 通过关键词获取公告列表
     *
     * @param keyWord 关键词
     * @return List Message
     */
    @Override
    public List<Message> getMessageByKeyWord(String keyWord) {
        return messageMapper.queryMwssageByKeyWord(keyWord);
    }

    /**
     * 更新公告
     *
     * @param message 公告
     */
    @Override
    public void updateMessage(Message message) {
        messageMapper.updateMessage(message);
    }
}
