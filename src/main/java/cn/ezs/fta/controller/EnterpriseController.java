package cn.ezs.fta.controller;

import cn.ezs.fta.pojo.Enterprise;
import cn.ezs.fta.pojo.Message;
import cn.ezs.fta.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>fta</h3>
 * <p>名企</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-13 09:23
 **/
@Controller
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 获取所有的公司名单
     * @return List<Enterprsie></>
     */
    @GetMapping(value = "/allEnterprise",produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Enterprise>> allEnterprise(){
        try {
            List<Enterprise> allEnterprise = enterpriseService.getAllEnterprise();
            return ResponseEntity.status(HttpStatus.OK).body(allEnterprise);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 得到某个公司的描述信息
     * @param eid 公司id
     * @return enterprsie
     */
    @GetMapping(value = "/enterprise/{eid}")
    public ResponseEntity<Enterprise> Enterprise(@PathVariable int eid){
        try {
            Enterprise enterpriseByEid = enterpriseService.getEnterpriseByEid(eid);
            return ResponseEntity.status(HttpStatus.OK).body(enterpriseByEid);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 删除某个公司信息
     * @param eid 公司id
     * @return Map
     */
    @DeleteMapping(value = "/deleteEnterprise/{eid}",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> deleteEnterprise(@PathVariable int eid){
        Map<String,String> message=new HashMap<>(1);
        try {
            enterpriseService.deleteEnterpriseByEid(eid);
            message.put("message","删除成功");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 添加一条公司信息
     * @param enterprise Enterprise
     * @return message
     */
    @PutMapping(value = "/enterprise",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> addEnterprise(@RequestBody Enterprise enterprise){
        Map<String,String> message=new HashMap<>(1);
        try {
            enterpriseService.addNewEnterprise(enterprise);
            message.put("message","添加成功");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message.put("message","服务端出现未知错误");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
        }
    }

    /**
     * 通过关键词获取公司列表
     * @param keyWord 关键词
     * @return List
     */
    @GetMapping(value = "/allEnterprise/{keyWord}",produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Enterprise>> enterpriseByKeyWord(@PathVariable String keyWord){
        try {
            List<Enterprise> enterpriseByKeyWord = enterpriseService.getEnterpriseByKeyWord(keyWord);
            return ResponseEntity.status(HttpStatus.OK).body(enterpriseByKeyWord);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }

    /**
     * 保存修改后的企业数据
     * @param enterprise Enterprise
     * @return Map
     */
    @PutMapping(value = "/updateEnterprise",produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String,String>> updateEnterprise(@RequestBody Enterprise enterprise){
        Map<String,String> message=new HashMap<>();
        try {
            //todo
            System.out.println(enterprise);
            enterpriseService.updateEnterperise(enterprise);
            message.put("message","更新企业数据成功");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message.put("message","服务端出现位置错误");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);

        }
    }

}
