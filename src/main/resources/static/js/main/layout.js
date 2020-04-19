<!--註冊 end-->
<!--轮播图 end-->
var ip = initIP()
//初始化
$(function() {
    //验证是否登录，登录则显示用户信息，否则显示登录信息
    init()


});
function init() {
    ifLogin();
    initUserImg()
    returnParentListen()
}
var userData;
function initUserImg() {
    //获取user信息

    let userId = sessionStorage.getItem("userId");
    if(userId){
        let userInfo ={
            url:ip+"user/"+userId,
            method:"GET"
        }
        axios(userInfo).then(data=>{
            userData = data.data.data
            let row = data.data.data;
            let dom = document.getElementById("userImg");
            dom.src=row.icon;
        })
    }


}
function showPerson(){
    let s = document.getElementById("showLogout")
    s.style.visibility = ""
    // parentListen()
}
function hiddenPerson(){
    let s = document.getElementById("showLogout")
    s.style.visibility = "hidden"
    //如果没有弹出层才能返回zindex为0
    // if(){}
    // returnParentListen();
}

function logout(){
    parentListen()
    realLogout();
}
function realLogout(){
    let logoutMethod={
        url:ip+"user/logout",
        method:"GET",
        params:""
    }
    axios(logoutMethod).then(()=>{
        // alertLayer("","退出成功",successLogout);
        alertLayer("提示","退出成功",successLogout);

    })
}

//用户是否登录  status：0.未登录  1.已登陆
function ifLogin(){
    // sessionStorage.setItem("status", 0);
    var value = sessionStorage.getItem("status");
    if(value==1||value=="1"){
        document.getElementById("notLogin").style.display="none";//隐
        document.getElementById("logged").style.display="";//显
        initUserImg();
        checkAdmin();
        // layer.msg('登錄成功', {icon: 1})
    }else{
        document.getElementById("notLogin").style.display="";//显
        document.getElementById("logged").style.display="none";//隱
        // layer.msg('你已退出登錄', {icon: 2})

    }
}
function checkAdmin() {
    let checkAdminMethod={
        url:ip+"user/check_identity",
        method:"GET",
        params:{},
    }
    axios(checkAdminMethod).then(data=>{
        if(data.data.data){
            let s = document.getElementById("houtai")
            s.style.display = ""
        }else{
            let s = document.getElementById("houtai")
            s.style.display = "none"
        }
        if (data.data.code==403){
            let logoutMethod={
                url:ip+"user/logout",
                method:"GET",
                params:""
            }
            axios(logoutMethod).then(()=>{
                // alertLayer("","退出成功",successLogout);
                // alertLayer("提示","退出成功",successLogout);
                parentListen();
            })
            alertLayer("提示",data.data.msg,closeForm2)
        }
    })
}
function closeForm2() {
    sessionStorage.clear();
    parent.location.href=ip+"index.html"
}
//登錄表單驗證
function validateLogin(form){
    var form = layui.form;
    form.verify({
        lusername:function(value){
            if(value.length==0){
                return '用戶名不能为空'
            }
        },
        lpassword: function(value) {
            if(value.length==0){
                return '密码不能为空'
            }
        }
    });
}
function toAdmin() {
    parent.location.href=ip+"admin.html"
}
//註冊表單驗證
function validateRegister(form){
    form.verify({
        rname:function(value){
            if(value.length==0){
                return '用戶名不能为空'
            }
        },
        rpassword:function(value){
            if(value.length<6){
                return '密码不能小于6位'
            }
        },
        raccount:function (value) {
            if(value.length==0){
                return '账户不能为空'
            }
        }
        // rpassword:  [
        //   /^[\S]{6,12}$/,
        //   '密码必须6到12位，且不能出现空格'
        // ],
    });
}
function pjUser() {
    var nHtml = '<div class="layui-form-item" style="display: none">'
    nHtml+='<label class="layui-form-label">id</label>'
    nHtml+='<div class="layui-input-inline">'
    nHtml+=' <input type="text" name="id" id="id" autocomplete="off" class="layui-input">'
    nHtml+=' </div>'
    nHtml+='</div>'
    nHtml+='<input type="file" id="file" name="file" class="chooseImg"><br/><img id="img" class="img-class2">'
    nHtml+='<div class="layui-form-item">'
    nHtml+='<label class="layui-form-label">用戶名</label>'
    nHtml+='<div class="layui-input-block">'
    nHtml+='<input type="text" name="name" id="name"  placeholder="请输入用戶名" autocomplete="off" class="layui-input">'
    nHtml+='</div>'
    nHtml+='</div>'
    nHtml+='<div class="layui-form-item">'
    nHtml+='<label class="layui-form-label">密码</label>'
    nHtml+='<div class="layui-input-inline">'
    nHtml+=' <input type="password" name="password" id="password" placeholder="请输入密码" autocomplete="off" class="layui-input">'
    nHtml+=' </div>'
    nHtml+='</div>'
    nHtml+='<div class="layui-form-item">'
    nHtml+='<label class="layui-form-label">账号</label>'
    nHtml+='<div class="layui-input-inline">'
    nHtml+='<input type="text" name="account" id="account"  placeholder="请输入账号" autocomplete="off" class="layui-input">'
    nHtml+='</div>'
    nHtml+='</div>'

    nHtml+='<div class="layui-form-item">'
    nHtml+='<label class="layui-form-label">身份</label>'
    nHtml+='<div class="layui-input-inline">'
    nHtml+='<input type="number" name="identity" id="identity" placeholder="请输入身份" autocomplete="off" class="layui-input">'
    nHtml+='</div>'
    nHtml+='</div>'
    return nHtml;
}
function clickLogin(){
    alertcomfirm("loginLayer","登录","#loginId",'loginVerify',validateLogin,ajaxLogin)
}
// function clickRegister(){
//
//   alertcomfirm("registerLayer","註冊","#registerId",'registerVerify',validateRegister,ajaxRegister)
// }
function  clickRegister() {
    let rowList = {
        name: "",
        password:"",
        account:"",
        icon:"",
        identity:""
    }
    let html = pjUser();
    alertcomfirm4("RegiaterLayer","注册",html,"RegiaterVerify",null,validateAddUser,ajaxAddUser,rowList,getUserData)
}
function updateUser() {
    let html = pjUser();
    //获取用户信息
    alertcomfirm4("updateUserLayer2","修改信息",html,"updateUserVerify2",null,validateAddUser,ajaxUpdateUser,userData,getUserData)
}
function validateAddUser(form) {
    var form = layui.form;
    form.verify({
        identity:function (value) {
            if(typeof value !== "number"){
                return "身份必须是数字"
            }
        }
    });
}
var fileImage;
function getUserData(rowList) {
    $("#file").change(function (event) {
        var input = event.target;
        fileImage = event.target.files[0];
        $("#img").attr("src",URL.createObjectURL(fileImage))
    })
    document.getElementById("id").value=rowList.id;
    document.getElementById("name").value=rowList.name;
    document.getElementById("password").value=rowList.password;
    document.getElementById("account").value=rowList.account;
    if(rowList.icon){
        document.getElementById('img').src=rowList.icon;
    }
    document.getElementById("identity").value=rowList.identity;

}
function ajaxAddUser(form) {
    let data = new FormData();
    for(let key of Object.keys(form)){
        //避免新增时id插入
        if(form[key]!="undefined"&&form[key]!=undefined&&key!=="file"){
            data.append(key,form[key])
        }
        if(key=="file"){
            data.append("file",fileImage)
        }

    }
    let addUser = {
        url:ip+"user/register",
        method:"POST",
        data:data
    }
    axios(addUser).then((data)=>{

        if(data.data.code!=0||data.data.code!=200){
            alertLayer2("编辑用户",data.data.msg)
        }else{
            alertLayer("编辑用户","新增成功",closeForm);
        }
    }).catch(err=>{alertLayer2("编辑用户",err)})
}
function ajaxUpdateUser(form) {
    let data = new FormData();
    for(let key of Object.keys(form)){
        //避免新增时id插入
        if(form[key]!="undefined"&&form[key]!=undefined&&key!=="file"){
            data.append(key,form[key])
        }
        if(key=="file"){
            data.append("file",fileImage)
        }

    }
    let updateUser={
        url:ip+"user/",
        method:"put",
        data:data
    }
    axios(updateUser).then(()=>{
        alertLayer("编辑用户","修改成功",closeForm);
    }).catch(err=>{alertLayer2("编辑用户",err)})
}
function closeForm() {
    layer.closeAll();
    init();
    returnParentListen();
}
function ajaxLogin(data){
    let params={
        account:data.lusername,
        password:data.lpassword
    }
    let login={
        url:ip+"user/login",
        method:"POST",
        data:params
    }
    axios(login).then((data)=>{
        if(data.data.code==0||data.data.code=="0"){
            sessionStorage.setItem("userId", data.data.data.id);
            alertLayer("登录","登录成功",successLogin);

            // returnParentListen();
        }else{
            alertLayer2("登录",data.data.msg)
        }

        // layer.closeAll()
        // ifLogin()
    }).catch(err=>{alertLayer2("登录",err)})
    //如果成功，將status寫入session
    // sessionStorage.setItem("status", 1);

}
function successLogin() {
    sessionStorage.setItem("status", 1);
    returnParentListen();
    closeForm();

}
function successLogout() {
    sessionStorage.setItem("status", 0);
    sessionStorage.clear();
    //退出登录后返回首页
    let href = parent.location.href;
    // if(href.indexOf("MyShelf")!=-1){
    parent.location.href=ip+"index.html"
    // }else{
    //     // returnParentListen();
    //     closeForm();
    // }
    // returnParentListen();

}
function ajaxRegister(form){
    let data ={};
    for(let key of Object.keys(form)){
        let keyArr = key.split("");
        let keyArr2 = keyArr.splice(0,1);
        let key2 = keyArr.join("");
        data[key2] = form[key]
    }
    if(data.password!==data.checkPassword){
        layer.msg('两次输入的密码不一致', {icon: 5});
        return false;
    }else{
        let register={
            url:ip+"user/register",
            method:"POST",
            data:data
        }
        axios(register).then(()=>{
            alertLayer("注册","注册成功",closeForm);
        }).catch(err=>{alertLayer("注册","注册失败",closeForm)})

    }
}
function readrecords() {
    parent.location.href=ip+"history.html"
}
function testId(){
    $("#test").toggleClass("blue")
}
function clickTitle(){
    let indexUrl = ip+"index.html"
    if(parent.location.href != indexUrl){
        parent.location.href = indexUrl
    }else{
        layer.msg('已在當前頁面', {icon: 3})
    }
}
function toMyShelf(){
    let myShelfUrl = ip+"MyShelf.html";
    if(parent.location.href != myShelfUrl){
        parent.location.href = myShelfUrl
    } else{
        layer.msg('已在當前頁面', {icon: 3})
    }
}
layui.use('element', function(){
    var element = layui.element;
});
