/*登录请求*/
function loginFun() {
    let obj = {};
    $.each($('#fromlogin').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })
    $.ajax({
        type: "post",
        dataType: "text",
        url: "user/login",
        data: JSON.stringify(obj),
        contentType: 'application/json;charset=utf-8',
        sync: false,
        success: function (result) {
            localStorage.setItem("token", result);

            let parseArray = result.split('.')

            let data = Base64.decode(parseArray[1])
            const parse = JSON.parse(data);

            /*展示用户名*/
            UsernameDisplay(parse.username);

            /*根据角色不同 ，给不同的菜单*/
            showOffMenuByRole(parse.userRole);


            alert("登陆成功！")
            $('#register').modal('hide');

        },
        error: function (result) {
            alert("登录失败！")
        }
    })
}

/*注销登录*/
$('#logout_a').click(function logoutFun() {
    localStorage.clear();
    location.reload();
})

/*注册请求*/
function registerFun() {

    let obj = {};
    $.each($('#fromregister').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })
    $.ajax({
        type: "post",
        dataType: "json",
        url: "user/register",
        data: JSON.stringify(obj),
        contentType: 'application/json;charset=utf-8',
        sync: false,
        success: function (result) {
            alert("注册成功！请登录！");
            $('#login').modal('hide');
        },
        error: function (result) {
            alert("注册失败！")
        }
    })
}

/*tab控制函数*/
function Tab(num) {
    /*将所有的子tab隐藏*/
    for (var elementsByClassNameElement of document.getElementsByClassName("child_page")) {
        elementsByClassNameElement.style.display = "none";
    }

    /*选中显示*/
    $("#select_tab a").each(function () {
        var $1 = $(this);
        $1.removeAttr("class")

    })

    /*显示选中的tab*/
    document.getElementById(num).style.display = "block"
    document.getElementById(num + "_tab").setAttribute("class", "active")
}

/*展示用户名*/
function UsernameDisplay(username) {
    //先隐藏登录 注册按钮
    $('#login_a').css('display', 'none');
    $('#register_a').css('display', 'none');
    $('#welcome_a').css('display', 'block');
    $('#welcome_username').html(username);
    $('#logout_a').css('display', 'block');
}

/*根据角色，展示不同菜单*/
function showOffMenuByRole(userRole) {
    switch (userRole) {
        case "ordinary": {
            $("#business_application_li").css('display', 'block');
            break;
        }
        case "audit": {
            $("#application_review_li").css('display', 'block');
            break;
        }
        case "admin": {
            $("#info_Release_li").css('display', 'block');
            $("#application_review_li").css('display', 'block');
            $('#public_info_li').css('display','block');
            break;
        }
    }
}


/*获得公开信息列表 */
function  getPublicMessage() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/allMessage",
        sync: false,
        success: function (result) {
            let i;
            //成功后，将得到json数组循环添加进页面
            let lis = '';
            for (i = 0; i < result.length; i++) {
                lis += "<li class=\"list-group-item\" style=\"overflow: hidden\" ><a  href=\"publicMessage.html?aid=";
                lis+=result[i].mid;
                lis+="\" target=\"_blank\"  >";
                lis += result[i].title;
                lis += "</a><span class=\"pull-right \">";
                lis += result[i].date;
                lis += "</span></li>";
            }
            $('#openMessageUl').html(lis);
        }
    })
}

/*获得所有公司展示
* */
function getEnterprise() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/allEnterprise",
        sync: false,
        success: function (result) {

            let i;
            //成功后，将得到json数组循环添加进页面
            let lis = '';
            for (i = 0; i < result.length; i++) {
                lis += "<li class=\"list-group-item\" style=\"overflow: hidden\"><a href=\"enterprise.html?eid=";
                lis+=result[i].eid;
                lis+="\">";
                lis += result[i].enterpriseName;
                lis += "</a></li>";
            }
            $('#enterpriseUl').html(lis);
        }
    })
}

/*提交申请*/
function putApplicaiton(){
    let obj = {};
    $.each($('#application_form').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })
    obj['applicant']=$('#welcome_username').text();
    $.ajax({
        type:"put",
        dataType:"json",
        url:"addApplication",
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(obj),
        success:function (result){
            alert(result.message);
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }

    })
}

/*业务申请记录*/
function apllicationHistoryFun(){
    $.ajax({
        type:"get",
        dataType:"json",
        url:"/getMyApplications/"+$('#welcome_username').text(),
        sync:false,
        headers: {'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        success:function (result){
            let trs ="<tr>\n" +
                "                <td>申请编号</td>\n" +
                "                <td>申请物品</td>\n" +
                "                <td>审核状态</td>\n" +
                "                <td>是否通过</td>\n" +
                "            </tr>";
            for (i=0;i<result.length;i++){
                trs+="<tr>\n" +
                    "    <td>";
                trs+=result[i].aid;
                trs+="</td>\n" +
                    "    <td>";
                trs+=result[i].applicationItem;
                trs+="</td>\n";
                //判断是否审核过
                if (result[i].applicationStatus==='u'){
                    //u代表未审核
                    trs+="<td><button class=\"btn btn-warning\">未审核</button></td>\n";
                }else {
                    //审核过
                    trs+="<td><button class=\"btn btn-info\">已审核</button></td>\n"
                    //已经审核了，才显示是否通过
                    if (result[i].applicationPass==='n'){
                        //未通过
                        trs+="<td><button class=\"btn btn-danger\">未通过</button></td>\n"
                    }else {
                        //通过
                        trs+="<td><button class=\"btn btn-success\">通过</button></td>\n"
                    }
                }
                trs+="</tr>";
                $('#application_history_table').html(trs)
            }
        }
    })
}

/*审核员审核*/
function audit_applicaotnFun(passchar,aid){
    $.ajax({
        type:"put",
        dataType:"json",
        url:"/audit/"+passchar+"/"+aid,
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'appclication/json;charset=utf-8',
        success:function (result){
            alert(result.message);
            $('#noaudit_applicaiton_tr_'+aid).remove();
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*显示审核员需要审核的申请*/
function need_audit_applicatino(){

    $.ajax({
        type:"get",
        dataType:"json",
        url:"/getAllApplication",
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        success:function (result){

            let trs_noAudit = '<tr>\n' +
                '                <td class="col-lg-1">申请编号</td>\n' +
                '                <td class="col-lg-1">申请人</td>\n' +
                '                <td class="col-lg-1">申请物品</td>\n' +
                '                <td class="col-lg-5"> 申请原因</td>\n' +
                '                <td class="col-lg-1">申请时间</td>\n' +
                '                <td class="col-lg-1"></td>\n' +
                '            </tr>';

            let trs_auditEd='<tr>\n' +
                '                <td class="col-lg-1">申请编号</td>\n' +
                '                <td class="col-lg-1">申请人</td>\n' +
                '                <td class="col-lg-1">申请物品</td>\n' +
                '                <td class="col-lg-5"> 申请原因</td>\n' +
                '                <td class="col-lg-1">申请时间</td>\n' +
                '                <td class="col-lg-1">是否通过</td>\n' +
                '            </tr>';

            for (i=0;i<result.length;i++){

                if (result[i].applicationStatus==='u'){
                    //还未审核
                    trs_noAudit+='<tr id="noaudit_applicaiton_tr_';
                    trs_noAudit+=result[i].aid;
                    trs_noAudit+='">\n' +
                        '                <td>';
                    trs_noAudit+=result[i].aid;
                    trs_noAudit+='</td>\n' +
                        '                <td>';
                    trs_noAudit+=result[i].applicant;
                    trs_noAudit+='</td>\n' +
                        '                <td>';
                    trs_noAudit+=result[i].applicationItem;
                    trs_noAudit+='</td>\n' +
                        '                <td>';
                    trs_noAudit+=result[i].applicationReason;
                    trs_noAudit+='</td>\n' +
                        '                <td>';
                    trs_noAudit+=result[i].applicationDate;
                    trs_noAudit+='</td>\n' +
                        '                <td>\n' +
                        '                    <button class="btn btn-success btn-sm" onclick="audit_applicaotnFun(\'p\',';
                    trs_noAudit+=result[i].aid;
                    trs_noAudit+=')">通过</button>\n' +
                        '                    <button class="btn btn-danger btn-sm" onclick="audit_applicaotnFun(\'n\',';
                    trs_noAudit+=result[i].aid;
                    trs_noAudit+=')">不通过</button>\n' +
                        '                </td>\n' +
                        '            </tr>';
                }else {
                    //已经审核
                    trs_auditEd+='<tr id="audited_applicaiton_tr_';
                    trs_auditEd+=result[i].aid;
                    trs_auditEd+='">\n' +
                        '                <td>';
                    trs_auditEd+=result[i].aid;
                    trs_auditEd+='</td>\n' +
                        '                <td>';
                    trs_auditEd+=result[i].applicant;
                    trs_auditEd+='</td>\n' +
                        '                <td>';
                    trs_auditEd+=result[i].applicationItem;
                    trs_auditEd+='</td>\n' +
                        '                <td>';
                    trs_auditEd+=result[i].applicationReason;
                    trs_auditEd+='</td>\n' +
                        '                <td>';
                    trs_auditEd+=result[i].applicationDate;
                    trs_auditEd+='</td>\n' +
                        '                <td>';
                    if (result[i].applicationPass==='n'){
                        //未通过审核
                        trs_auditEd+='<button class="btn btn-danger btn-sm" >不通过</button>\n' +
                            '                </td>\n' +
                            '            </tr>';
                    }else {
                        //通过审核
                        trs_auditEd+='<button class="btn btn-success btn-sm" >通过</button>\n' +
                            '                </td>\n' +
                            '            </tr>';
                    }
                }
            }
            $('#application_noaudit_table').html(trs_noAudit);
            $('#application_audited_table').html(trs_auditEd);
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*发布新的公告*/
function postPublicMaessageFun(){
    //公告信息
    let obj={};
    $.each($('#addPublicInfo_form').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })
    $.ajax({
        type:'post',
        dataType:'json',
        url:'/addMessage',
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset-utf-8',
        data:JSON.stringify(obj),
        success:function (result){
            alert(result.message);
            /*发布成功后情况表单*/
            $('#public_info_title').val('');
            $('#public_info_content').val('');
            adminGetPublicInfoFun();
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*管理员界面获得公告 */
function  adminGetPublicInfoFun() {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/allMessage",
        sync: true,
        success: function (result) {
            let trs='<tr>\n' +
                '                    <td>公告id</td>\n' +
                '                    <td>公告title</td>\n' +
                '                    <td>操作</td>\n' +
                '                </tr>';
            for (let i=0;i<result.length;i++){
                trs+='<tr id="admin_public_info_tr_'
                trs+=result[i].mid;
                trs+='">\n' +
                    '                    <td>';
                trs+=result[i].mid;
                trs+='</td>\n' +
                    '                    <td>';
                trs+=result[i].title;
                trs+='</td>\n' +
                    '                    <td><button class="btn btn-info" data-toggle="modal" data-target="#changeMessage" onclick="changeMessage(';
                trs+=result[i].mid;
                trs+=')">修改公告</button><button class="btn btn-danger" onclick="deletePublicMessageFun(';
                trs+=result[i].mid;
                trs+=')">删除该公告</button></td>\n' +
                    '                </tr>';
            }
            $('#delete_publicInfo_table').html(trs);
        }
    })
}

/*删除一条公告*/
function deletePublicMessageFun(mid){
    $.ajax({
        type:'delete',
        dataType:'json',
        url:'/message/'+mid,
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        success:function (result){
            alert(result.message);
            $('#admin_public_info_tr_'+mid).remove();
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })

}

/*修改一条公告,获取公告数据*/
function changeMessage(mid){
    $.ajax({
        type:'get',
        dataType:'json',
        url:'/message/'+mid,
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        success:function (result){
            $('#changeMessageTitle').val(result.title);
            $('#changeMessageContent').val(result.content);
            $('#changeMessageMid').val(result.mid);
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*保存修改后的公告*/
function saveMessageChange(){
    let obj={};
    $.each($('#changeMessageForm').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })
    $.ajax({
        type:'put',
        dataType:'json',
        data:JSON.stringify(obj),
        contentType:'application/json;charset=utf-8',
        url:'/updateMessage',
        headers:{'token':localStorage.getItem('token')},
        sync:false,
        success:function (result){
            alert(result.message);
            adminGetPublicInfoFun();
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*添加新的公司信息*/
function postEnterpriseFun(){
    let obj={};
    $.each($('#addEnterprise_form').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })

    $.ajax({
        type:"put",
        dataType:'json',
        url:'/enterprise',
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(obj),
        success:function (result){
            alert(result.message);
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*管理员获取公司名单*/
function adminGetEnterpriseFun(){
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/allEnterprise",
        sync: false,
        success: function (result) {
            let trs_enterprise='<tr>\n' +
                '                    <td>公司名</td>\n' +
                '                    <td>操作</td>\n' +
                '                </tr>';
            for (i=0;i<result.length;i++){
                trs_enterprise+='<tr id="admin_enterprise_tr_';
                trs_enterprise+=result[i].eid;
                trs_enterprise+='">\n' +
                    '                    <td>';
                trs_enterprise+=result[i].enterpriseName;
                trs_enterprise+='</td>\n' +
                    '                    <td><button class="btn btn-info" data-toggle="modal" data-target="#changeEnterprise" onclick="changeEnterpriseInfo(';
                trs_enterprise+=result[i].eid;
                trs_enterprise+= ')">修改公司信息</button><button class="btn btn-danger" onclick="deleteEntrpriseFun(';
                trs_enterprise+=result[i].eid;
                trs_enterprise+=')">删除</button> </td>\n' +
                    '                </tr>';
            }
            $('#delete_enterprise_table').html(trs_enterprise);
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*删除公司*/
function deleteEntrpriseFun(eid){
    $.ajax({
        type:'delete',
        dataType:'json',
        url:'/deleteEnterprise/'+eid,
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        success:function (result){
            alert(result.message);
            $('#admin_enterprise_tr_'+eid).remove();
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}

/*获取要修改的公司信息*/
function changeEnterpriseInfo(eid){
    $.ajax({
        type:'get',
        dataType:'json',
        url:'/enterprise/'+eid,
        sync:false,
        headers:{'token':localStorage.getItem('token')},
        contentType:'application/json;charset=utf-8',
        success:function (result){
            $('#changeEnterpriseMid').val(result.eid);
            $('#changeEnterpriseName').val(result.enterpriseName);
            $('#changeEnterpriseDescription').val(result.enterpriseDescription);
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }

    })

}

/*保存修改后的公司信息*/
function saveEnterpriseInfo(){
    let obj={};
    $.each($('#changeEnterpriseForm').serializeArray(), function (index, filed) {
        obj[filed.name] = filed.value;
    })
    $.ajax({
        type:'put',
        dataType:'json',
        data:JSON.stringify(obj),
        contentType:'application/json;charset=utf-8',
        url:'/updateEnterprise',
        headers:{'token':localStorage.getItem('token')},
        sync:false,
        success:function (result){
         alert(result.message);
         adminGetEnterpriseFun();
        },
        error:function (result){
            alert(result.message);
            localStorage.clear();
            location.reload();
        }
    })
}


/*根据公司名检索*/
function getEnterpriseByKeyWord(){
    $.ajax({
        type:'get',
        dataType:'json',
        url:'/allEnterprise/'+$('#enterprise_keyWord').val(),
        sync:true,
        success:function (result){
            let i;
            //成功后，将得到json数组循环添加进页面
            let lis = '';
            for (i = 0; i < result.length; i++) {
                lis += "<li class=\"list-group-item\" style=\"overflow: hidden\"><a href=\"enterprise.html?eid=";
                lis+=result[i].eid;
                lis+="\">";
                lis += result[i].enterpriseName;
                lis += "</a></li>";
            }
            $('#enterpriseUl').html(lis);
        }

    })
}

/*通过关键词获取公告*/
function getMessageByKeyWord(){
    $.ajax({
        type:'get',
        dataType:'json',
        url:'/allMessage/'+$('#message_keyword').val(),
        sync:true,
        success:function (result) {
            let i;
            //成功后，将得到json数组循环添加进页面
            let lis = '';
            for (i = 0; i < result.length; i++) {
                lis += "<li class=\"list-group-item\" style=\"overflow: hidden\" ><a  href=\"publicMessage.html?aid=";
                lis+=result[i].mid;
                lis+="\" target=\"_blank\"  >";
                lis += result[i].title;
                lis += "</a><span class=\"pull-right \">";
                lis += result[i].date;
                lis += "</span></li>";
            }
            $('#openMessageUl').html(lis);
        }
    })
}




