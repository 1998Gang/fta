package cn.ezs.fta.controller;

import cn.ezs.fta.annotation.TokenRequired;
import cn.ezs.fta.pojo.Message;
import cn.ezs.fta.service.MessageService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <h3>fta</h3>
 * <p>信息相关</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-10 17:37
 **/
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;


    /**
     * 获取所有的信息，以json数组格式返回
     * @return Json
     */
    @RequestMapping(value = "/allMessage", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Message>> getAllMessage() {
        try {

            List<Message> allMessage = messageService.getAllMessage();
            return ResponseEntity.status(HttpStatus.OK).body(allMessage);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 通过信息id获取数据
     * @param mid 信息id
     * @return Message
     */
    @RequestMapping(value = "/message/{mid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public ResponseEntity<Message> getMessageByMid(@PathVariable int mid) {

        try {
            Message messageByMid = messageService.getMessageByMid(mid);
            return ResponseEntity.status(HttpStatus.OK).body(messageByMid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 添加新的信息
     * @param message Message
     * @return 提升信息
     */
    @TokenRequired
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String, String>> addMessage(@RequestBody Message message) {
        HashMap<String, String> messageMap = new HashMap<>();
        try {
            boolean b = messageService.addNewMessage(message);
            messageMap.put("message", "新公共发布成功");
            return ResponseEntity.status(HttpStatus.OK).body(messageMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }
    }

    /**
     * 删除一条公告
     * @param mid 公告id
     * @return Map
     */
    @TokenRequired
    @DeleteMapping(value = "/message/{mid}",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> deleteMessage(@PathVariable int mid){
        Map<String,String> message=new HashMap<>(1);
        try {
            messageService.deleteMessage(mid);
            message.put("message","删除公告成功,公告id："+mid);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message.put("message","服务端出现未知错误");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 通过关键词获取公告信息
     * @param keyWord 关键词
     * @return Json数组
     */
    @GetMapping(value = "/allMessage/{keyWord}")
    public ResponseEntity<List<Message>> getMessageByKeyword(@PathVariable String keyWord){
        try {
            List<Message> messageByKeyWord = messageService.getMessageByKeyWord(keyWord);
            return ResponseEntity.status(HttpStatus.OK).body(messageByKeyWord);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }


    /**
     * 更新公告
     * @param message 公告主体
     * @return 返回提升信息
     */
    @PutMapping(value = "/updateMessage",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> updateMessage(@RequestBody Message message){
        //todo
        System.out.println(message);
        HashMap<String,String> messageMap=new HashMap<>(1);
        try {
            messageService.updateMessage(message);
            messageMap.put("message","更新成功");
            return ResponseEntity.status(HttpStatus.OK).body(messageMap);
        }catch (Exception e){
            messageMap.put("message","更新失败");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(messageMap);
        }
    }

}
